package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.dao.OrganizationDAO;
import in.nic.pes.lgd.forms.OrganizationTypeForm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("organizationValidator")
public class OrganizationValidator implements Validator {
	
	private static final Logger log = Logger.getLogger(OrganizationValidator.class);

	@Autowired
	private OrganizationDAO organizationDAO;

	public boolean supports(Class<?> clazz) {
		return OrganizationTypeForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		OrganizationTypeForm orgTypeForm = (OrganizationTypeForm) object;
		String str = orgTypeForm.getOrgTypeName();
		if (orgTypeForm.getOrgTypeName().isEmpty()) {
			try {
				errors.rejectValue("orgTypeName", "error.blank.orgTypeName");
			} catch (Exception e) {
				errors.rejectValue("orgTypeName", "error.blank.orgTypeName");
			}
		}
	
		else if (!customValidator.checkforAlphaNumericandSpace(str)) // will only matches alphabets
		 {
			errors.rejectValue("orgTypeName1", "error.valid.orgTypeName");
		 }
		
		else {
			try {
				if (checkExistingOrgTypeName(orgTypeForm.getOrgTypeName())) {
					try {
						errors.rejectValue("orgTypeName",
								"error.exist.orgTypeName");
					} catch (Exception e) {
						errors.rejectValue("orgTypeName",
								"error.exist.orgTypeName");
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.debug("Exception" + e);
			}

		}
		if (orgTypeForm.getOrgTypeName().isEmpty()) {
			try {
				errors.rejectValue("orgTypeName", "error.blank.orgTypeName");
			} catch (Exception e) {
				errors.rejectValue("orgTypeName", "error.blank.orgTypeName");
			}
		}

	}
	
	

	private boolean checkExistingOrgTypeName(String orgTypeName) throws Exception {

		int count = organizationDAO.getRecordsforOrganizationType(orgTypeName);
		if (count >= 1) {

			return true;
		} else {
			return false;
		}
	}

}
