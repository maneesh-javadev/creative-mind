package in.nic.pes.common.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.DefaultGatewayResolverImpl;
import org.jasig.cas.client.authentication.GatewayResolver;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;

public class AuthenticationFilter extends AbstractCasFilter
{
  private String casServerLoginUrl;
  private boolean renew;
  private boolean gateway;
  private GatewayResolver gatewayStorage;

  public AuthenticationFilter()
  {
    this.renew = false;

    this.gateway = false;

    this.gatewayStorage = new DefaultGatewayResolverImpl(); }
  
  protected void initInternal(FilterConfig filterConfig) throws ServletException {
    if (!(isIgnoreInitConfiguration())) {
      super.initInternal(filterConfig);
      setCasServerLoginUrl(getPropertyFromInitParams(filterConfig, "casServerLoginUrl", null));
      setRenew(parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")));
	  setGateway(parseBoolean(getPropertyFromInitParams(filterConfig, "gateway", "false")));
      
      String gatewayStorageClass = getPropertyFromInitParams(filterConfig, "gatewayStorageClass", null);

      if (gatewayStorageClass == null) return;
      try {
        this.gatewayStorage = ((GatewayResolver)Class.forName(gatewayStorageClass).newInstance());
      } catch (Exception e) {
        this.log.error(e, e);
        throw new ServletException(e);
      }
    }
  }

  public void init()
  {
    super.init();
    
    CommonUtils.assertNotNull(this.casServerLoginUrl, "casServerLoginUrl cannot be null.");
  }

  public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest)servletRequest;
    HttpServletResponse response = (HttpServletResponse)servletResponse;
    
    HttpSession session = request.getSession(false);
    /**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
    if(session!=null) {
    	Boolean isExternalUser= session.getAttribute("isExternalUserTemp")!=null?(Boolean)session.getAttribute("isExternalUserTemp") :null;
        if(isExternalUser!=null && isExternalUser==true) {
        	 filterChain.doFilter(request, response);
             return;
        }
    }
    /**
	 * end External User
	 */
    
    String annotation = request.getServletPath().replace("/", "");
	if(annotation.contains("welcome.htm"))
		this.gateway = true;
	else
		this.gateway = false;
	
	
   
    Assertion assertion = (session != null) ? (Assertion)session.getAttribute("_const_cas_assertion_") : null;
  
    if (assertion != null) {
      filterChain.doFilter(request, response);
      return;
    }

    String serviceUrl = constructServiceUrl(request, response);
    String ticket = CommonUtils.safeGetParameter(request, getArtifactParameterName());
   
    boolean wasGatewayed = this.gatewayStorage.hasGatewayedAlready(request, serviceUrl);
   
    this.log.info("wasGatewayed"+wasGatewayed);
    if ((CommonUtils.isNotBlank(ticket)) || (wasGatewayed)) {
      filterChain.doFilter(request, response);
      return;
    }

    this.log.info("no ticket and no assertion found");
    String modifiedServiceUrl;
    if (this.gateway) {
    	 this.log.info("setting gateway attribute in session");
      modifiedServiceUrl = this.gatewayStorage.storeGatewayInformation(request, serviceUrl);
    } else {
      modifiedServiceUrl = serviceUrl;
    }

    if (this.log.isDebugEnabled()) {
      this.log.debug("Constructed service url: " + modifiedServiceUrl);
    }

    String urlToRedirectTo = CommonUtils.constructRedirectUrl(this.casServerLoginUrl, getServiceParameterName(), modifiedServiceUrl, this.renew, this.gateway);

    if (this.log.isDebugEnabled()) {
      this.log.debug("redirecting to \"" + urlToRedirectTo + "\"");
    }

    response.sendRedirect(urlToRedirectTo);
  }

  public final void setRenew(boolean renew) {
    this.renew = renew;
  }

  public final void setGateway(boolean gateway) {
    this.gateway = gateway;
  }

  public final void setCasServerLoginUrl(String casServerLoginUrl) {
    this.casServerLoginUrl = casServerLoginUrl;
  }

  public final void setGatewayStorage(GatewayResolver gatewayStorage) {
    this.gatewayStorage = gatewayStorage;
  }
}