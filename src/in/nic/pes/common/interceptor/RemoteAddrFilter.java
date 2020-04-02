package in.nic.pes.common.interceptor;
/**
 * @author Alok K
 * File Name : RemoteAddrFilter.java
 * @Date 09 July 2017
 */
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import in.nic.pes.lgd.controllers.WsUserController;
import in.nic.pes.lgd.service.CommonService;
import sun.misc.BASE64Decoder;

public class RemoteAddrFilter implements Filter{

	
	CommonService bean = null;
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest request =  (HttpServletRequest) req;
		HttpServletResponse response =  (HttpServletResponse) res;
		System.out.println("URL  "+request.getRequestURI());
		System.out.println("remoteaddr  "+request.getRemoteAddr());
		System.out.println("user agent   "+request.getHeader("User-agent"));
		if(bean.checkWsUrl(request.getRequestURI()) && request.getParameter("token") != null && !request.getParameter("token").isEmpty() ){
			System.out.println("inside mobile app");
			request.getParameter("token");
			System.out.println("Token   "+request.getParameter("token"));
			String sentToken=request.getParameter("token").replaceAll("'", "");
			String tokendec = WsUserController.base64decode(sentToken);
			System.out.println("Token decode  "+tokendec);
			if(bean.mobileTokenAuthentication(tokendec)){
				System.out.println("inside mobile app");
				chain.doFilter(req, res);
			}
			else{
				response.sendRedirect("/webservice/lgdws/errorMessage");
				
			}
		  }
		else{
		if(bean.checkWsUrl(request.getRequestURI()) && !bean.checkWsAllowedIpAddress(request.getRemoteAddr())){
			System.out.println("inside desktop");
			response.sendRedirect("/webservice/lgdws/errorMessage");
			/*String requestURI = request.getRequestURI();
			String toReplace = requestURI.substring(requestURI.lastIndexOf("/"));
			String newURI = requestURI.replace(toReplace, "/errorMessage");
			
			response.sendRedirect(newURI);*/
			//req.getRequestDispatcher(newURI).forward(req, res);
		}
		else
		chain.doFilter(req, res);
	}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		bean = WebApplicationContextUtils.
				  getRequiredWebApplicationContext(arg0.getServletContext()).
				  getBean(CommonService.class);
	}
	@Override
	public void destroy() {
		
	}

}
