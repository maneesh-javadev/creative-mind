package in.nic.pes.lgd.common;

import in.nic.pes.lgd.utils.Captcha;

import javax.servlet.http.HttpSession;

/**
 * 
 *
 * @since 27-09-2013
 */

public class CaptchaValidator {

	public boolean validateCaptcha(HttpSession session, String captchaAnswer) {
		boolean messageFlag = false;
		String captchaValue = (String) session
				.getAttribute(Captcha.CAPTCHA_KEY);
		if (captchaValue!=null && captchaValue.equals(captchaAnswer)) {
			messageFlag = true;
			session.removeAttribute(Captcha.CAPTCHA_KEY);
		}	
			
		return messageFlag;
	}
	
	 /**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	public boolean validateCaptchaFoExternalLogin(HttpSession session, String captchaAnswer) {
		boolean messageFlag = false;
		String captchaValue = (String) session
				.getAttribute(Captcha.CAPTCHA_KEY);
		if (captchaValue!=null && captchaValue.equals(captchaAnswer)) {
			messageFlag = true;
			
		}	
			
		return messageFlag;
	}
	 /**
	 * end External User
	 */
		 
	
}
