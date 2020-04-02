package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
/*
 * @Author Pooja
 * on 22-05-2015
 */
public class ManageDesignationDeptValidator implements Validator {

	@Override
	public boolean supports(Class<?> clas) {
		return LocalGovtBodyForm.class.isAssignableFrom(clas);
	}
	
	public void validate(Object model, Errors errors) {

		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		LgdDesignation designationForm = (LgdDesignation) model;
		String allDesignations = designationForm.getOtherDesignations();
			if (allDesignations != null && !"".equals(allDesignations)) {

				String[] designations = allDesignations.split("\\@@");
				int otherDesigLength = designations.length;
				for (int i = 0; i < otherDesigLength; i++) {
					String[] columns = designations[i].split("\\##");
					String designationName = columns[1];
					String designationNameLocal = columns[2];

					if (designationName == null || "".equals(designationName)) {
						errors.rejectValue("designationName", "Designation Name is required");
						return;
					}
					if (!customValidator.checkforAlphabetWithSpaceDotSlashesandBrackets(designationName)) {
						errors.rejectValue("designationName", "Designation Name is allow only (a-z)(A-Z), Space, Dot and Brackets.");
						return;
					}
					if (designationName.length() > 50) {
						errors.rejectValue("designationName", "Designation Name length must be less then and equal to 50 characters.");
						return;
					}
					if (designationNameLocal == null || "".equals(designationNameLocal)) {
						errors.rejectValue("designationNameLocal", "Designation Name Local is required");
						return;
					}
					if (!customValidator.validateSpecialCharactersHyphen(designationNameLocal)) {
						errors.rejectValue("designationNameLocal", "Invalid Designation Name other in local.");
						return;
					}
					if (designationNameLocal.length() > 50) {
						errors.rejectValue("designationNameLocal", "Designation Name Local length must be less then and equal to 50 characters.");
						return;
					}
					String locatedLevels = columns[3];
					String levels[] = locatedLevels.split(",");
					for(int j=0;j<levels.length;j++)
					{
						if(levels[j] == null || "".equals(levels[j]))
						{
							errors.rejectValue("designationLevelList","Please Select organization levels at which designation exist");
							return;
						}
					}
					

				}
			}
			else
			{
				errors.rejectValue("otherDesignations","Please Enter atleast one designation detail !");
				return;
			}
		}
	}

