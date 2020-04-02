package in.nic.pes.lgd.common;
/**
 * @author Sushil Shakya
 * File Name : AuthenticationFilter.java
 * @Date 02 March 2012
 * @Modified on 05 March 2012
 */
import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.utils.ApplicationConstant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationFilter implements Filter {
	@Autowired
	CommonService commonService;
	
	private static final Logger log = Logger.getLogger(AuthenticationFilter.class);
	
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        /**/
        String sessionCrossApp=null;
        String ucat = request.getParameter(AuthenticationConstant.STR_CAT);
        log.debug("** ucat-->"+ucat);
        
        /*Get the IP address of client machine. */
        String ipAddress = request.getRemoteAddr();	 
       
        /*Log the IP address and current time stamp. */
        log.debug("** IP "+ipAddress + ", Time "+ new Date().toString());	
   	    
        /*	Get the requested URL. */
        String reqURI = request.getRequestURI();
        reqURI = reqURI.substring(reqURI.lastIndexOf("/")+1, reqURI.length());
        log.debug("** reqURI-->"+reqURI);
        
        InputStream inputStream = AuthenticationFilter.class.getResourceAsStream("/AcrossApplicationAccess.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        sessionCrossApp = properties.getProperty("across.application.access.session_deserialization_identifier");
		
		String hasLussi=request.getParameter(sessionCrossApp);
		UserSelection selection = null;
		if(session != null){
			selection=(UserSelection) session.getAttribute("USERS_SELECTION");
			if((request.getHeader("referer") != null && request.getHeader("referer").contains("switchunit")) || (hasLussi != null)) {
				
				if(selection!=null) {
					session.setAttribute("stateCode",selection.getStateId().toString());
					session.setAttribute("isCenterState",selection.getIsCenterorstate());
					session.setAttribute("stateId",selection.getStateId());
					if(selection.getDistrictCode() !=null) {	
						session.setAttribute("districtId",selection.getDistrictCode());
					} else {
						session.setAttribute("districtId",0);
					}
					session.setAttribute("subDistrictId",selection.getSubDistCode());
					session.setAttribute("localBodyId",selection.getAssignUnit());
					session.setAttribute("lbCode",selection.getAssignUnit());
					session.setAttribute("UserIp", req.getRemoteAddr());
					session.setAttribute("forms", selection.getFormList());
					session.setAttribute("userid", selection.getUserId());
				}
			}
		}
    
		if (reqURI != null && !"".equals(reqURI) && ! AuthenticationConstant.getExcludedURL().contains(reqURI)) {
				if(session == null) {
		        	System.out.println("Session has been inactive in AuthenticationFilter");
		        	response.sendError(501, "session expired..");
		        }
				if (!reqURI.contains(AuthenticationConstant.VALID_APPLICATION_EXT) && ! reqURI.contains(AuthenticationConstant.VALID_CITIZEN_EXT)) {
					log.debug("** Not a valid file extesion..");
					session.invalidate();
					response.sendError(500, AuthenticationConstant.LGD_APPLICATION_MSG);
				} else {
					boolean valid = false;
					
					if(false) {//selection != null
						//Set<Form> formList = selection.getFormList();//loginForm.getFormList();
						List<MenuProfile> formList = selection.getMenuProfile();
						if (formList != null && formList.size() > 0) {
							formList = Collections.unmodifiableList(formList);//Set(formList);
							
							log.debug("** URL not found looking into file system..");
							String tmpReqURL = reqURI.replaceFirst(AuthenticationConstant.VALID_APPLICATION_EXT, "");
							String parentURL = AuthenticationConstant.getAuthenticationBundle(tmpReqURL, null);
							log.debug("** parentURL-->"+parentURL);
							if(parentURL != null && !"".equals(parentURL)) {
								
								if(parentURL.contains(",")) {
									String[] multipleParentURLs = parentURL.split(",");
									for(String individualParentUrl : multipleParentURLs) {
										log.debug("multi parentURL --> " + individualParentUrl);
										individualParentUrl = individualParentUrl.concat(AuthenticationConstant.VALID_APPLICATION_EXT);
										valid = findURL(formList, individualParentUrl);
										if( valid )
											break;
									}
								} else {
									valid = findURL(formList, parentURL + AuthenticationConstant.VALID_APPLICATION_EXT);
								}
							} else {
								valid = findURL(formList, reqURI);
							}
							System.out.println("IS VALID URL IN AUTH FILTER : " + valid);
							if ( !valid ) {
								log.debug("** Not a valid request..");
								session.invalidate();
								response.sendError(403, "Not a valid request..");
							}
						}
					} else {
						log.debug("** UserBean is NULL..");
						if(ucat != null && !"".equals(ucat) && ! ucat.equals(AuthenticationConstant.USER_CAT)) {
							//response.sendError(501, "session expired..");
							request.getRequestDispatcher("/error.html").forward(request, response);
						}
					}
				}
	    }
		
		if(!response.isCommitted()) {
			log.debug("before chain.doFilter....");
			chain.doFilter(req, res);
		}
    }
    
    /**
     * 
     * @param inputList
     * @param inputReqURI
     * @return
     * @since 20-03-2015
	 * @author Vinay Yadav
     */
    //private boolean findURL(Set<?> inputList, final String inputReqURI/*, List<Privilege> privList*/){
    private boolean findURL(List<?> inputList, final String inputReqURI){
		Object found = CollectionUtils.find(inputList, new Predicate() {
			@Override
			public boolean evaluate(Object obj) {
				MenuProfile form = (MenuProfile) obj;
				String menuURL = form.getFormName();
				return  StringUtils.equals(inputReqURI, menuURL);//inputReqURI.equalsIgnoreCase(menuURL);//(menuURL != null && menuURL.equals(inputReqURI))
			}
		});
		return inputList.contains(found);
    }
    
    /**
     * The {@code init} Assigning State code to static list for Local Body District Level.
	 * @since 23-12-2013
	 * @author Vinay Yadav  
     */
    public void init(FilterConfig config) throws ServletException {
    	if(ApplicationConstant.stateLBDisttWise.isEmpty()){
    		ApplicationConstant.stateLBDisttWise.add(19);
    		ApplicationConstant.stateLBDisttWise.add(17);
    		ApplicationConstant.stateLBDisttWise.add(27);
    		ApplicationConstant.stateLBDisttWise.add(33);
    		ApplicationConstant.stateLBDisttWise.add(7);
		}
    }
    
    public void destroy() {

    }
}