/**
 * 
 */
package in.nic.pes.lgd.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * @author Vinay Yadav
 * @since February 2014
 *
 */
public class DepratmentURLInterceptor extends HandlerInterceptorAdapter {
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception {
	    try{
	    	boolean isForward = false, isOrganizationFlow = false;
	    	String path = request.getRequestURI().substring(request.getContextPath().length() + 1);
	    	
	    	if("createDepartmentState.htm".equalsIgnoreCase(path)){
				isForward = true;
			}else if("createDepartmentCentral.htm".equalsIgnoreCase(path)){
				isForward = true;
			}
			// will be used for organization flow 
			else if("createOrganizationState.htm".equalsIgnoreCase(path)){
				isForward = true; 
				isOrganizationFlow = true;
			}else if("createOrganizationCentral.htm".equalsIgnoreCase(path)){
				isForward = true;
				isOrganizationFlow = true;
			}
			if(isForward){
				HttpSession session = request.getSession(false);
				session.setAttribute("isOrganizationFlow", isOrganizationFlow);
				response.sendRedirect("startDepartmentCreatetion.htm");
			}
		}catch (Exception e) {
			// TODO: handle exception
	    	throw e;
		}
	 }
}
