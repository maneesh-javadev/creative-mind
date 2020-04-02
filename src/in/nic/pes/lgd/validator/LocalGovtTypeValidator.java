package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;
import in.nic.pes.lgd.forms.LocalGovtTypeForm;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;




@Component("localGovtTypeValidator")
public class LocalGovtTypeValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
		return LocalGovtTypeForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
	
		LocalGovtTypeForm localgovt=(LocalGovtTypeForm)object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		String strName = localgovt.getLocalBodyTypeName();
		if(localgovt.getLocalBodyTypeName().isEmpty())
		{
			try {
				errors.rejectValue("localBodyTypeName","error.blank.localBodyTypeName");
			} catch (Exception e) {
				errors.rejectValue("localBodyTypeName","error.blank.localBodyTypeName");
			}
			
		}
		else if(!customValidator.checkforAlphabetsandSpace(strName))
		{
			errors.rejectValue("localBodyTypeName1","error.valid.lbTypeName");
			
		}
				
		if(localgovt.getCategoryRadio().equals("0"))
		{
			try {
				errors.rejectValue("categoryRadio","error.blank.category");		
				
			} catch (Exception e) {
				errors.rejectValue("categoryRadio","error.blank.category");
			}
		}
		else if(localgovt.getCategoryRadio().equals("R"))
		{
			if(localgovt.getRuralCategory().equals("S"))
			{
				try {
					errors.rejectValue("ruralCategory","error.blank.Rcategory");
				} catch (Exception e) {
					errors.rejectValue("ruralCategory","error.blank.Rcategory");
				}				
				
			}
			
			if(localgovt.getLevel() == null || localgovt.getLevel().isEmpty())
			{
				errors.rejectValue("level","error.blank.level");
			}
			else 
			{
				if(localgovt.getLevel().equalsIgnoreCase("S"))
				{
					try {
						errors.rejectValue("level","error.blank.level");
					} catch (Exception e) {
						errors.rejectValue("level","error.blank.level");
					}				
					
				}
			}
			
		}
		
		
	}
	public void validateChange(Object object, Errors errors)
	{
		LocalGovtTypeForm localgovt=(LocalGovtTypeForm)object;
		 CustomRegexValidator customValidator = CustomRegexValidator
					.getInstance();
		 List<LocalGovtTypeDataForm> listdetail = localgovt.getListdetail();
		 for(LocalGovtTypeDataForm lsDetail:listdetail)
		 {
		 
		 
			if(lsDetail.getLocalBodyTypeName() == null || lsDetail.getLocalBodyTypeName().isEmpty())
			{
				try {
					errors.rejectValue("localBodyTypeName","error.blank.localBodyTypeName");
				} catch (Exception e) {
					errors.rejectValue("localBodyTypeName","error.blank.localBodyTypeName");
				}
				
			}
			else if (!customValidator.checkforAlphabetsandSpace(lsDetail.getLocalBodyTypeName())) {
				errors.rejectValue("localBodyTypeName", "error.ms",
						"Local Body Type Name contains invalid characters");
			}
		 }
	}
	
	public void validateCorrection(Object object, Errors errors)
	{
		//LocalGovtTypeForm localgovt=(LocalGovtTypeForm)object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderNocr", "orderNo.required", "Order Number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderDatecr", "orderDate.required", "Order date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gazPubDatecr", "gazPubDate.required", "Gazette Publication Date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ordereffectiveDatecr", "ordereffectiveDate.required", "Order Effective date is required.");
	}
	
}



