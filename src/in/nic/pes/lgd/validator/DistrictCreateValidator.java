package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.forms.DistrictForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("DistrictCreateValidator")
public class DistrictCreateValidator implements Validator  { // NO_UCD (unused code)
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return DistrictForm.class.isAssignableFrom(clazz);
		
	}
	
	@Override
	public void validate(Object object, Errors errors) {	
		//modify Block
        DistrictForm districtForm=(DistrictForm)object;
		
		if(districtForm.isCorrection()==false)
		{		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderNo", "orderNo.required", "Order Number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderDate", "orderDate.required", "Order date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gazPubDate", "gazPubDate.required", "Gazette Publication Date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ordereffectiveDate", "ordereffectiveDate.required", "Order Effective date is required.");
		
	}
		
		if (districtForm.getDistrictNameEnglish()==null) {
			try {
				errors.rejectValue("districtNameInEn", "error.blank.districtNameInEn");
			} catch (Exception e) {
				errors.rejectValue("districtNameInEn", "error.blank.districtNameInEn");
			}
		} 
		
	}
}
