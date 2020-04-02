package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.LGSetupForm;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LocalGovSetupValidator implements Validator {
	
	private static final Logger log = Logger.getLogger(LocalGovSetupValidator.class);

	@Override
	public boolean supports(Class<?> clas) {

		return LGSetupForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//CustomRegexValidator customValidator = CustomRegexValidator.getInstance();

	}

	public void localGovtSetupValidation(LGSetupForm lGSetupForm, Errors errors) {

		try {
			CustomRegexValidator customValidator = CustomRegexValidator
					.getInstance();
			String nomenEng = lGSetupForm.getNomenEnglish().trim();
			if (nomenEng.contains(",")) {
				String[] nomenEngTemp = nomenEng.split(",");
				for (String lbType : nomenEngTemp) {
					if (lbType == null || lbType.isEmpty()) {
						errors.rejectValue("nomenEnglish",
								"error.valid.PLEASESELECTNOMENCLATUREINENGLISH");
					} else if (!customValidator.checkforAlphabetsandSpace(lbType)) {
						errors.rejectValue("nomenEnglish",
								"error.valid.COMMONALPHABETREQUIRED",
								"LocalBody type contains Alphabets only");
					}
				}
			} else {
				if (nomenEng == null || nomenEng.isEmpty()) {
					errors.rejectValue("nomenEnglish",
							"error.valid.PLEASESELECTNOMENCLATUREINENGLISH");
				} else if (!customValidator.checkforAlphabetsandSpace(nomenEng)) {
					errors.rejectValue("nomenEnglish",
							"error.valid.COMMONALPHABETREQUIRED",
							"LocalBody type contains Alphabets only");
				}
			}

			if (lGSetupForm.getCheck() == null) {
				errors.rejectValue("check", "error.valid.PLEASESELECTLOCALBODYTYPE");
			}
			
			String nomenLocal = lGSetupForm.getNomenLocal();
			if (nomenLocal != null && nomenLocal.contains(",")) {
				String[] nomenLocalTemp = nomenLocal.split(",");
				for (String lbTypeLocal : nomenLocalTemp) {
					if (lbTypeLocal == null || lbTypeLocal.isEmpty()) {
						errors.rejectValue("nomenLocal",
								"error.valid.PLEASESELECTNOMENCLATUREINENGLISH");
					} else if (!customValidator
							.checkforAlphabetsandSpace(lbTypeLocal)) {
						errors.rejectValue("nomenLocal",
								" ",
								"Nomenclature(In English) contains Alphabets only");
					}
				}

			} else {
				if (nomenLocal == null || nomenLocal.isEmpty()) {
					errors.rejectValue("nomenLocal",
							"error.valid.PLEASESELECTNOMENCLATUREINENGLISH");
				} else if (!customValidator.checkforAlphabetsandSpace(nomenEng)) {
					errors.rejectValue("nomenLocal",
							" ",
							"Nomenclature(In Local Language) contains Alphabets only");
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

	}
	
 
}
