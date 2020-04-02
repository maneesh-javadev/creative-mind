package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.forms.LocalGovtBodyForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MapParliamentValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clas) {
		 
		return LocalGovtBodyForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
		
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newAssemblyNameEnglish",
				"required.userName", "Name of New Assembly Constituency name is required.");*/
		
		
		/*contributedParliament*/	
		LocalGovtBodyForm lbForm = (LocalGovtBodyForm)target;
		
		/*contributedParliament*/
		
			 	
		 	if(lbForm.getContributedParliament().isEmpty()){
				errors.rejectValue("contributedParliament", "notmatch.password","Field is required.");
			}
		 	/*rurallbTypeName*/
		 	if(lbForm.getContributedAssembly()!=null){
		 		if(lbForm.getContributedAssembly().isEmpty()){
		 			errors.rejectValue("contributedParliament", "notmatch.password","Field is required.");
		 		}
		 	}	
		 	
		 	if ( lbForm.getRurallbTypeName().equals("0") )
		    {
				errors.rejectValue("rurallbTypeName", "notmatch.password","Field is required.");
			}
		 	
		 	
		 	/*if (lbForm.getRurallbTypeName().contains("D") && lbForm.getLgd_LBDistPDestList() == null) {

				errors.rejectValue("lgd_LBDistPDestList",
						"error.blank.DistrictPanchayat"
						);
			}
		 	
		 	
		 	else if (lbForm.getRurallbTypeName().contains("I") && lbForm.getLgd_LBInterPDestList() == null) {

				errors.rejectValue("lgd_LBInterPDestList",
						"error.blank.IntermediatePanchayat"
						);
			}
		 	
		 	
		 	
		 	else if (lbForm.getRurallbTypeName().contains("V") && lbForm.getLgd_LBInterPDestListforvillage() == null) {

				errors.rejectValue("lgd_LBInterPDestListforvillage",
						"error.blank.VillagePanchayat"
						);
			}*/
		 
		 	
		 	/*else if (lbForm.getRurallbTypeName().contains("M") && lbForm.getLgd_LBWardCArea() == null) {

				errors.rejectValue("lgd_LBWardCArea",
						"error.blank.MunicipalityPanchayat"
						);
			}*/
		 	
		
		 	/*if (lbForm.getNewAddLocalBody()==null) {

				errors.rejectValue("newAddLocalBody",
						"error.ADDLOCALBODY"
						);
			}*/
		 	
		 	
			/*if(lbForm.getSubdistrictCodes()<=0){
				errors.rejectValue("subdistrictCodes", "notmatch.password","Field is required.");
			}*/
			
			
			
			
		}
	}

 
