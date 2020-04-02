package in.nic.pes.lgd.common;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.org.ep.captcha.CaptchaServiceSingleton;

@Service
public class CaptchaServiceImpl {

	
	public String validateCaptchaCode(String formName, String sessionId, String captchaImageValue) {
		// TODO Auto-generated method stub
		String validateMessage = "The CAPTCHA image code was entered incorrectly. Enter the text shown above.";
		try{
			synchronized (validateMessage) {
				if(sessionId!= null && captchaImageValue != null && !"".equals(captchaImageValue.trim())){
					Boolean isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(sessionId, captchaImageValue);
					if(isResponseCorrect){
						validateMessage="success";
						HttpSession session = WebContextFactory.get().getSession();
						session.setAttribute("captchaFormName", formName);
						session.setAttribute("captchaStatus", validateMessage);
					}
					else
					{
					validateMessage = "The CAPTCHA image code was entered incorrectly. Enter the text shown above.";
					}
				}
				else
				{
					validateMessage ="Please Enter the text shown above.";
				}
			}
		}catch (Exception e) {
			validateMessage = e.getMessage();
		}
		return validateMessage;
	}
	
}
