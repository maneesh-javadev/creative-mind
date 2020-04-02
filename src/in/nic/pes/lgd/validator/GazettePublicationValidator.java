package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.GovernmentOrderForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class GazettePublicationValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clas) {
		 
		return GovernmentOrderForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		
		/*if(lbForm.getContributedParliament().isEmpty()){
			errors.rejectValue("contributedParliament", "notmatch.password","Field is required.");
		}
	 	
	 	 if (lbForm.getStatecode() == null) {

			errors.rejectValue("statecode",
					"error.blank.AssemblyConstituency"
					);
		}*/
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newAssemblyNameEnglish",
				"required.userName", "Name of New Assembly Constituency name is required.");
	*/	/*gazettePublicationCheckBox*/
		
		/*contributedParliament*/	
		GovernmentOrderForm lbForm = (GovernmentOrderForm)target;
		 	/*if(lbForm.getSelectedAssembly()<=0){
				errors.rejectValue("selectedAssembly", "notmatch.password","Field is required.");
			}*/
	
		if(lbForm.getGazPubDate()==null)
		{		
			try {
				errors.rejectValue("gazPubDate2","Msg.GAZPUBDATE");				
			} catch (Exception e) {
				errors.rejectValue("gazPubDate2","Msg.GAZPUBDATE");			
			}	
						
		}
		else if (!customValidator.checkforDate(lbForm.getGazPubDate())) // will only matches alphabets
		 {
			errors.rejectValue("gazPubDate2", "error.valid.GAZPUBDATE");
		 }
	
	
	/*if(lbForm.getGazpubDate() == null)
	{ 
		if (!customValidator.checkforDate(lbForm.getGazpubDate())) // will only matches alphabets
		 {
			errors.rejectValue("gazpubDate", "error.valid.GAZPUBDATE");
		 }
	}*/
	if (lbForm.getGazettePublicationCheckBox()==null)
		

	{
		errors.rejectValue("gazettePublicationCheckBox",
				"error.blank.GazettePabliactionDate");
		
	}
	
	}}
		 
		 
 
