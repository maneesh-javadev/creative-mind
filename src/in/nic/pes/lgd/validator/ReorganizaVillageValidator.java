package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.forms.AssemblyForm;
import in.nic.pes.lgd.forms.VillageForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReorganizaVillageValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clas) {
		 
		return AssemblyForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
		
		
		
		/*contributedParliament*/	
		VillageForm lbForm = (VillageForm)target;
		 	/*if(lbForm.getSelectedAssembly()<=0){
				errors.rejectValue("selectedAssembly", "notmatch.password","Field is required.");
			}*/
		 	
		 	 if (lbForm.getContributedVillages() == null) {

				errors.rejectValue("contributedVillages",
						"error.blank.VILLAGEREORGANIZATION"
						);
			}
		 
		 	
		 	/*if(lbForm.getStatecode().isEmpty()){
				errors.rejectValue("statecode", "notmatch.password","Field is required.");
			}*/
		 	
			/*if(lbForm.getSubdistrictCodes()<=0){
				errors.rejectValue("subdistrictCodes", "notmatch.password","Field is required.");
			}*/
		}
	}

 
