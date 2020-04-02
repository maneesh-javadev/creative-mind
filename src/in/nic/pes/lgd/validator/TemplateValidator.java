package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.GovtOrderTemplateForm;
import in.nic.pes.lgd.forms.VillageForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("TemplateValidator")
public class TemplateValidator implements Validator 
{

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class<?> clazz)
	{
		return GovtOrderTemplateForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

	}

	private CustomRegexValidator regValidatior = CustomRegexValidator.getInstance();

	public void createTemplateValidation(
			GovtOrderTemplateForm govtOrderTemplateForm, BindingResult result) {

		if (govtOrderTemplateForm != null) {
			if (govtOrderTemplateForm.getTemplateBodySrc() == "") {
				result.rejectValue("templateBodySrc", "error.PETM");
			}
			if (!regValidatior.checkforAlphabetsandSpace(govtOrderTemplateForm
					.getTemplateNameEnglish())) {
				result.rejectValue("templateNameEnglish", "error.PETM");
			}
			if (!regValidatior.checkforOnlyNumeric(govtOrderTemplateForm
					.getOperations())) {
				result.rejectValue("operations", "error.PSO");
			}
			if (!regValidatior.checkforAlphabetsandSpace(govtOrderTemplateForm
					.getOrderType())) {
				result.rejectValue("orderType", "error.PSOT");
			}
			if (!regValidatior.checkforAlphabetsandSpace(govtOrderTemplateForm
					.getTemplateLanguage())) {
				result.rejectValue("templateLanguage", "error.PSTL");
			}
		/*	if (govtOrderTemplateForm.getTemplateBodySrc().toUpperCase()
					.contains("SCRIPT")) {
				result.rejectValue("templateBodySrc", "error.msg.template",
						"Script tag not allowed");
			}
*/
		}
	}

	public void updateTemplateValidation(
			GovtOrderTemplateForm govtOrderTemplateForm, BindingResult result) {
		if (govtOrderTemplateForm != null) {
/*
			if (govtOrderTemplateForm.getTemplateBodySrc().toUpperCase()
					.contains("SCRIPT")) {
				result.rejectValue("templateBodySrc", "error.msg.template",
						"Script tag not allowed");
			}*/
			if (!regValidatior.checkforAlphabetsandSpace(govtOrderTemplateForm
					.getTemplateNameEnglish())) {
				result.rejectValue("templateNameEnglish", "error.PETM");
			}
			if (!regValidatior.checkforOnlyNumeric(govtOrderTemplateForm
					.getOperations())) {
				result.rejectValue("operations", "error.PSO");
			}
			if (!regValidatior.checkforAlphabetsandSpace(govtOrderTemplateForm
					.getOrderType())) {
				result.rejectValue("orderType", "error.PSOT");
			}
			if (!regValidatior.checkforAlphabetsandSpace(govtOrderTemplateForm
					.getTemplateLanguage())) {
				result.rejectValue("templateLanguage", "error.PSTL");
			}

		}
	}

	public void listTemplateValidation(GovtOrderTemplateForm govtOrderTemplateForm, Errors errors) throws Exception
	{
		try
		{
			if (Integer.parseInt(govtOrderTemplateForm.getOperations())==0) 
			{
				errors.rejectValue("operations","error.blank.template", "Field is required.");
			} 
			if (Integer.parseInt(govtOrderTemplateForm.getOperations()) !=0)
			{	
				if (!regValidatior.checkforOnlyNumeric(govtOrderTemplateForm.getOperations())) 
				{
					errors.rejectValue("operations", "error.PSO");
				}
			}
		}
		catch (Exception e) 
		{
				throw e;
		}

	}

	public void previewTemplateValidation(
			GovtOrderTemplateForm govtOrderTemplateForm, BindingResult result) {
		if (!regValidatior.checkforOnlyNumeric(govtOrderTemplateForm
				.getOperations())) {
			result.rejectValue("operations", "error.PSO");
		}

		/*if (govtOrderTemplateForm.getTemplateBodySrc().toUpperCase()
				.contains("SCRIPT")) {
			result.rejectValue("templateBodySrc", "error.msg.template",
					"Script tag not allowed");

		}
*/
	}
}
