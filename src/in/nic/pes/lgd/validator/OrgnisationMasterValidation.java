package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class OrgnisationMasterValidation implements Validator {

	@Override
	public boolean supports(Class<?> clas) {
		return LocalGovtBodyForm.class.isAssignableFrom(clas);
	}

	public void validate(Object model, Errors errors) {
		
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		LgdDesignation designationForm = (LgdDesignation) model;
		String flowName = designationForm.getFlowName();
		if("designationCenter".equals(flowName) || "designationState".equals(flowName)){
			Integer orgType = designationForm.getOrgType();
			if(orgType == null || orgType.equals(0)){
				errors.rejectValue("orgType","Organization Type is Invalid.");
				return;
			}
			Integer olc = designationForm.getOlc();
			if(olc == null || olc.equals(0)){
				errors.rejectValue("olc","Organization is Invalid.");
				return;
			}
			/*comment by pooja.. as orgLocatedLevelCode not now in LgdDesignation*/
			
			/*Integer locatedLevel = designationForm.getOrgLocatedLevelCode();
			if(locatedLevel == null || locatedLevel.equals(0)){
				errors.rejectValue("orgLocatedLevelCode","Organization Level is Invalid.");
				return;
			}*/
		}else if("designationEROfficial".equals(flowName)){
			Integer tiersetupcode = designationForm.getTierSetupCode();
			if(tiersetupcode == null || tiersetupcode.equals(-1)){
				errors.rejectValue("orgType","Organization Type is Invalid.");
				return;
			}
		}else{
			errors.rejectValue("designationName","Designation Flow does'nt Mapped with any Valid Page.");
			return;
		}
		
		String designName = designationForm.getDesignationName();
		String desigNameLocal= designationForm.getDesignationNameLocal();
		
		if(designName == null || "".equals(designName)){
			errors.rejectValue("designationName","Designation Name is required");
			return;
		}
		
		if (!customValidator.checkforAlphabetWithSpaceDotSlashesandBrackets(designName)){
			errors.rejectValue("designationName","Designation Name is allow only (a-z)(A-Z), Space, Dot and Brackets.");
			return;
		}
		
		if(designName.length() > 50) {
			errors.rejectValue("designationName","Designation Name length must be less then and equal to 50 characters.");
			return;
		}
				
		if (!customValidator.validateSpecialCharactersHyphen(desigNameLocal)){
			errors.rejectValue("designationNameLocal","Invalid Designation Name in local.");
			return;
		}
		String otherdesg = designationForm.getOtherDesignations();
		if(otherdesg != null && !"".equals(otherdesg)){
			String[] designationOthers = otherdesg.split("\\@@");
			int otherDesigLength = designationOthers.length;
			for(int i = 0; i < otherDesigLength; i++){
				String[] columns =  designationOthers[i].split("\\##");
				String designationNameOther = columns[1];
				String designationNameLocalOther = columns[2];
				
				if(designationNameOther == null || "".equals(designationNameOther)){
					errors.rejectValue("designationName","Designation Name is required");
					return;
				}
				if (!customValidator.checkforAlphabetWithSpaceDotSlashesandBrackets(designationNameOther)){
					errors.rejectValue("designationName","Designation Name is allow only (a-z)(A-Z), Space, Dot and Brackets.");
					return;
				}
				if(designationNameOther.length() > 50) {
					errors.rejectValue("designationName","Designation Name length must be less then and equal to 50 characters.");
					return;
				}
				if (!customValidator.validateSpecialCharactersHyphen(designationNameLocalOther)){
					errors.rejectValue("designationNameLocal","Invalid Designation Name other in local.");
					return;
				}
				if (designName.equals(designationNameOther)) {
					errors.rejectValue("designationName","Designation Name already referenced, Please change designation.");
					return;
				}
				
				for (int j = 0; j < designationOthers.length; j++) {
					if (i != j) {
						if (designationNameOther.equals(designationOthers[j].split("\\##")[1])) {
							errors.rejectValue("designationName","Designation Name already referenced, Please change designation.");
							return;
						}
					}
				}
			}
		}
	}	
}
