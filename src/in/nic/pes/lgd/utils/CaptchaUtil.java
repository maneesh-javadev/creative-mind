package in.nic.pes.lgd.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CaptchaUtil { // NO_UCD (unused code)

	public static boolean validateCaptcha(HttpServletRequest request){
		boolean flag = true;
		String formName = request.getParameter("formName");
		String statusFlag = request.getParameter("statusFlag");
		HttpSession session = request.getSession();
		String formNameSession = (String) session.getAttribute("captchaFormName");
		String statusFlagSession = (String) session.getAttribute("captchaStatus");
		session.removeAttribute("captchaFormName");
		session.removeAttribute("captchaStatus");
		if(!formName.equals(formNameSession) || !statusFlag.equals(statusFlagSession)){
			flag = false;
		}
		return flag;
	}
	
}
