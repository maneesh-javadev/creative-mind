/**
 * @author Maneesh Kumar
 * Download Directory Validator for all server side validation of download directory
 */

package in.nic.pes.lgd.validator;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.common.CaptchaValidator;
import in.nic.pes.lgd.forms.DownloadDirectoryForm;

@Component("DownloadDirectoryValidator")
public class DownloadDirectoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DownloadDirectoryForm.class.isAssignableFrom(clazz);

	}

	public void validate(Object object, Errors errors, HttpSession session) {
		DownloadDirectoryForm downloadDirectoryForm = (DownloadDirectoryForm) object;
		CaptchaValidator captchaValidator = new CaptchaValidator();
		if (downloadDirectoryForm.getCaptchaAnswer() != null && !("").equals(downloadDirectoryForm.getCaptchaAnswer())) {
			if (!captchaValidator.validateCaptcha(session, downloadDirectoryForm.getCaptchaAnswer())) {
				errors.rejectValue("captchaAnswer", "captcha.errorMessage", "The CAPTCHA image code was entered incorrectly. Enter the text shown above.");
			}
		} else {
			errors.rejectValue("captchaAnswer", "error.enter.captcha", "Please Enter the captcha text show above Image");
		}

		if (downloadDirectoryForm.getDownloadOption() != null && ("DFD").equals(downloadDirectoryForm.getDownloadOption())) {
			String entitySelect = downloadDirectoryForm.getRptFileName();
			if (entitySelect != null && !("-1").equals(entitySelect)) {
				if (downloadDirectoryForm.getEntityCodes() != null && downloadDirectoryForm.getEntityCodes().indexOf(",") != -1 && entitySelect.indexOf("@") != -1) {
					String entityCodeArr[] = downloadDirectoryForm.getEntityCodes().split(",");
					for (int i = 0; i < entityCodeArr.length; i++) {
						if (("-1").equals(entityCodeArr[i])) {
							errors.rejectValue("entityCodes", "entityCodes.errormsg", "Select entity wrong.");
						}
					}
				}
			} else {
				errors.rejectValue("rptFileName", "error.select.ENTITYLEVEL", "Please select Entity");
			}
		}else if (downloadDirectoryForm.getDownloadOption() != null && ("DMO").equals(downloadDirectoryForm.getDownloadOption())) {
			String entitySelect = downloadDirectoryForm.getRptFileNameMod();
			if (entitySelect != null && !("-1").equals(entitySelect)) {
				if (downloadDirectoryForm.getEntityCodes() != null && downloadDirectoryForm.getEntityCodes().indexOf(",") != -1 && entitySelect.indexOf("@") != -1) {
					String entityCodeArr[] = downloadDirectoryForm.getEntityCodes().split(",");
					for (int i = 0; i < entityCodeArr.length; i++) {
						if (("-1").equals(entityCodeArr[i])) {
							errors.rejectValue("entityCodes", "entityCodes.errormsg", "Select entity wrong.");
						}
					}
				}
			} else {
				errors.rejectValue("rptFileName", "error.select.ENTITYLEVEL", "Please select Entity");
			}
		} 
		
		else if (downloadDirectoryForm.getDownloadOption() != null && ("DSWD").equals(downloadDirectoryForm.getDownloadOption())) {
			if (!(downloadDirectoryForm.getEntityCode() != null && downloadDirectoryForm.getEntityCode() != -1)) {
				errors.rejectValue("entityCode", "error.select.SELECTSTATENAME", "Please select State");
			}
			if (downloadDirectoryForm.getMultiRptFileNames() != null && !("").equals(downloadDirectoryForm.getMultiRptFileNames())) {
				String multiRptFileArr[] = downloadDirectoryForm.getMultiRptFileNames().split(",");
				int count = 0;
				for (int i = 0; i < multiRptFileArr.length; i++) {
					count++;
				}
				if (count < 2) {
					errors.rejectValue("multiRptFileNames", "error.multifile.minimum", "Please Select at least two Option for download zip");
				}

			} else {
				errors.rejectValue("multiRptFileNames", "error.multifile.null", "Please Select Option");
			}

		} else {
			errors.rejectValue("downloadOption", "error.download.option", "Please select download option");
		}

	}

	@Override
	public void validate(Object arg0, Errors arg1) {
	}

}
