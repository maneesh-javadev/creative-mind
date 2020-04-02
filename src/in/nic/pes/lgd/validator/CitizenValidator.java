package in.nic.pes.lgd.validator;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.SearchForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageDataForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component("CitizenValidator")
public  class CitizenValidator implements Validator {

      
// TODO Remove unused code found by UCDetector
// 	public void validateforglobal(Object model, Errors errors) 
// 	{
// 		StateDataForm stateDataForm = (StateDataForm) model;
// 		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
// 		
// 		if (stateDataForm.getCode()!=null && !stateDataForm.getCode().equals(""))
// 		{
// 			if (!customValidator.checkforOnlyNumeric(stateDataForm.getCode()))
// 			{
// 				errors.rejectValue("code", "error.ms","This field contains invalid characters.Please use 0 to 9");
// 
// 			}
// 		}
// 		
// 		if (stateDataForm.getStateNameEnglish() != null	&& !stateDataForm.getStateNameEnglish().equals("")) {
// 			if (!customValidator.checkforAlphabetsandSpace(stateDataForm.getStateNameEnglish())) {
// 				errors.rejectValue("stateNameEnglish", "error.ms","This field contains invalid characters.Please use A-Z or a-z");
// 			}
// 		}
// 	}
	
	public void validateforgdistic(Object model, Errors errors) 
	{
		SubDistrictForm subdistrictbean = (SubDistrictForm) model;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		
		if (subdistrictbean.getCode()!=null && !subdistrictbean.getCode().equals(""))
		{
			if (!customValidator.checkforOnlyNumeric(subdistrictbean.getCode()))
			{
				errors.rejectValue("code", "error.ms","This field contains invalid characters.Please use 0 to 9");

			}
		}
	}

	public void validateforgsearch(Object model, Errors errors) 
	{
		SearchForm searchView = (SearchForm) model;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		
		if (searchView.getEntityName()!=null && !searchView.getEntityName().equals(""))
		{
			if (!customValidator.checkforAlphaNumericandSpace(searchView.getEntityName())) {
				errors.rejectValue("entityName", "error.ms","This field contains invalid characters.Please use A-Z or a-z or 0-9");
			}
		}
	}
	public void validateforgvillage(Object model, Errors errors) 
	{
		VillageDataForm villagebean= (VillageDataForm) model;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		
		if (villagebean.getCode()!=null && !villagebean.getCode().equals(""))
		{
			if (!customValidator.checkforOnlyNumeric(villagebean.getCode()))
			{
				errors.rejectValue("code", "error.ms","This field contains invalid characters.Please use 0 to 9");

			}
		}
	}
	
	public void validateforgsubdistic(Object model, Errors errors) 
	{
		DistrictForm districtbean = (DistrictForm) model;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		
		if (districtbean.getCode()!=null && !districtbean.getCode().equals(""))
		{
			if (!customValidator.checkforOnlyNumeric(districtbean.getCode()))
			{
				errors.rejectValue("code", "error.ms","This field contains invalid characters.Please use 0 to 9");

			}
		}
	}

	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}


}
