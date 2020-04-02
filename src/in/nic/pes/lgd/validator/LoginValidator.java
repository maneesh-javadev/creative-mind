package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.menu.LoginForm;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {
	private static final Logger log = Logger.getLogger(LoginValidator.class);
	
	@Override
	public boolean supports(Class<?> clas) {

		return LoginForm.class.isAssignableFrom(clas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		try {
			LoginForm loginForm = (LoginForm) target;
			CustomRegexValidator customValidator = CustomRegexValidator
					.getInstance();
			if(!customValidator.checkforOnlyNumeric(loginForm.getStateNameEnglish())){
					errors.rejectValue("stateNameEnglish", " ",	"Please give valid State Name ");
			}		
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

	}
}
