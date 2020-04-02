package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.CitizenFeedback;
import in.nic.pes.lgd.common.CustomRegexValidator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component("FeedbackValidator")
public class FeedbackValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CitizenFeedback.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors result) {
		CitizenFeedback citizenFeedback=(CitizenFeedback)obj;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();

		String citizenName=citizenFeedback.getCitizenName();
		String citizenEmail=citizenFeedback.getCitizenEmail();
		String feedback=citizenFeedback.getFeedback();
		
		if(citizenName==null || "".equals(citizenName.trim())){
			result.rejectValue("citizenName", "Name is mandatory!");
		}else if(citizenName.length()>50){
			result.rejectValue("citizenName", "Name length must be less than 50 character");
		}else if(!customValidator.checkforAlphabetsandSpace(citizenName)){
			result.rejectValue("citizenName", "Name must be allow [a-z][A-Z] and space");
		}
			
		
		if(citizenEmail==null || "".equals(citizenEmail.trim())){
			result.rejectValue("citizenEmail", "Email is mandatory!");
		}else if(citizenEmail.length()>50){
				result.rejectValue("citizenEmail", "Email length must be less than 50 character");
		}else if(!customValidator.checkEmail(citizenEmail)){
			result.rejectValue("citizenEmail", "Email is not proper format");
		}
		
		if(feedback==null || "".equals(feedback.trim())){
			result.rejectValue("feedback", "Feedback is mandatory!");
		}else if(feedback.length()>500){
			result.rejectValue("feedback", "Feedback length must be less than 500 character");
		}
	}

}
