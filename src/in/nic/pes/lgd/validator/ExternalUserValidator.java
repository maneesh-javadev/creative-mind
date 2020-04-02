package in.nic.pes.lgd.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.menu.LoginForm;
import in.nic.pes.lgd.service.InitialService;


/**
 * This Class is Use for External User 
 * @param loginForm
 * @author Maneesh Kumar
 * @since 01-10-2019
 * @return
 */
public class ExternalUserValidator  implements Validator {
	
	@Autowired
	InitialService initialService;

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object object, Errors errors) {
		LoginForm loginForm = (LoginForm) object;
		if(!(loginForm.getUsername()!=null && loginForm.getUsername().trim()!="")) {
			errors.rejectValue("username", "External User Id Can't be blank");
		}else {
			
			if(!initialService.isUserLoginNameExist(loginForm.getUsername())){
				errors.rejectValue("username", "The User Id you provided cannot be Exist in Exernal User.");
			}else {
				
				if(!(loginForm.getPassword()!=null && loginForm.getPassword().trim()!="")) {
					errors.rejectValue("password", "Password  Can't be blank");
					
				}else if(!initialService.isPasswordValid(loginForm.getUsername(),loginForm.getEnpassword())){
					errors.rejectValue("password", "The credentials you provided cannot be determined to be authentic.");
				}
			}
			
		}
		
	}

}
