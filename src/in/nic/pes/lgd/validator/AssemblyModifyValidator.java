package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.forms.AssemblyForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AssemblyModifyValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clas) {
		 
		return AssemblyForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
		/*contributedParliament*/	
		AssemblyForm lbForm = (AssemblyForm)target;
		
	 	
		 	if(lbForm.getSelectedAssembly()==0){
				errors.rejectValue("selectedAssembly", "notmatch.password","Field is required.");
			}
		
		/*if(lbForm.getSubdistrictCodes()<=0){
				errors.rejectValue("subdistrictCodes", "notmatch.password","Field is required.");
			}*/
				
		}
	}

 
