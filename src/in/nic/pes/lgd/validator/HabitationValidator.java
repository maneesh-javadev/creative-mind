package in.nic.pes.lgd.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.constant.CommanConstant;
import in.nic.pes.lgd.forms.HabitationForm;
import in.nic.pes.lgd.service.VillageService;

public class HabitationValidator implements Validator {

	@Autowired
	VillageService villageService;

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	public void validate(Object target, Errors errors) {
		HabitationForm habitationForm = (HabitationForm) target;
		String entityType = habitationForm.getParentType();
		if (validateEntityType(entityType)) {
			errors.rejectValue("parentType", "error.parent.entity.type.not.exist");
		} else {
			if ((CommanConstant.HABITATION_PARENT_TYPE_GRAMPANCHAYAT.toString()).equals(entityType)) {

				if (!validateLbHierarchy(habitationForm.getParamLocalBodyTypeCode(), false)) {
					habitationForm.setParentCode(Integer.parseInt(habitationForm.getLocalBodyLevelCodes().split(",")[2]));
				}
			} else {
				habitationForm.setParentCode(habitationForm.getParamVillageCode());
			}

			validateHabitationNameEnglish(habitationForm, errors, false);

			if (habitationForm.getEffectiveDate() != null) {
				validateEffectiveDate(habitationForm, errors);
			}

			if (habitationForm.getSscode() != null && !("").equals(habitationForm.getSscode())) {
				validateSscode(habitationForm, errors);
			}
			
			if(habitationForm.getHabitationNameLocal()!=null && !("").equals(habitationForm.getHabitationNameLocal())){
				
				
				
				validateLocalName(habitationForm, errors);
			}
			try {
				if (validateParentCode(habitationForm)) {
					throw new Exception("Request has been intrupted, Requested action is not available.");
				}

			} catch (Exception e) {

			}

		}
	}

	public boolean validateParentCode(HabitationForm habitationForm) throws Exception {
		return villageService.validateParentCode(habitationForm.getParentCode(), habitationForm.getParentType(),
				habitationForm.getSlc());
	}

	public void validateEffectiveDate(HabitationForm habitationForm, Errors errors) {
		CustomRegexValidator validator = CustomRegexValidator.getInstance();
		if (!validator.checkforDate(habitationForm.getEffectiveDate())) {
			errors.rejectValue("effectiveDate", "error.valid.EFFECTIVEDATE");
		}
	}

	public void validateSscode(HabitationForm habitationForm, Errors errors) {
		CustomRegexValidator validator = CustomRegexValidator.getInstance();
		if (!validator.checkforOnlyNumeric(habitationForm.getSscode())) {
			errors.rejectValue("sscode", "Error.SscodeNumeric");
		} else if (habitationForm.getSscode().length() > 10) {
			errors.rejectValue("sscode", "Error.Sizevalid", new String[] { "10" }, null);
		}
	}
	
	public void validateLocalName(HabitationForm habitationForm, Errors errors){
		 if (habitationForm.getHabitationNameLocal().length() > 200) {
				errors.rejectValue("habitationNameLocal", "Error.Sizevalid", new String[] { "200" }, null);
			}
	}

	public void validateHabitationNameEnglish(HabitationForm habitationForm, Errors errors, boolean isupdate) {
		String habitaionNameEng = isupdate ? habitationForm.getHabitationNameEnglish()
				: habitationForm.getHabitationNameEnglish();
		String errorField = isupdate ? "habitationNameEnglishChange" : "habitationNameEnglish";
		if (StringUtils.isBlank(habitaionNameEng)) {
			errors.rejectValue(errorField, "error.blank.HabitationInEn");
		} else if (unSupportedCharactersEnglish(habitaionNameEng)) {
			errors.rejectValue(errorField, "Error.invalidchar");
		} else if (validateSpace(habitaionNameEng)) {
			errors.rejectValue(errorField, "Error.invalidspace");
		} else if (habitaionNameEng.length() > 200) {
			errors.rejectValue(errorField, "Error.Sizevalid", new String[] { "200" }, null);
		}

		try {
			if (!validateHabitatationNameUnique(habitationForm)) {
				errors.rejectValue(errorField, "Error.habitation.name.unique");
			}
		} catch (Exception e) {
			errors.rejectValue(errorField, e.toString());
		}

		if (isupdate) {
			/*
			 * if(habitaionNameEng.toLowerCase().trim().equals(habitationForm.
			 * getSectionNameEnglish().toLowerCase().trim())){
			 * errors.rejectValue(errorField,
			 * "error.must.change.section.name.of.previous"); }else{ try{
			 * if(!validateHabitatationNameUnique(habitationForm)){
			 * errors.rejectValue(errorField, "Error.habitation.name.unique"); }
			 * }catch(Exception e){ errors.rejectValue(errorField,
			 * e.toString()); } }
			 */

		}
	}

	public boolean validateHabitatationNameUnique(HabitationForm habitationForm) throws Exception {
		return villageService.habitationNameUniquewithParent(habitationForm.getHabitationNameEnglish(),
				habitationForm.getParentCode(), habitationForm.getParentType());
	}

	public boolean validateSpace(String SectionNameEng) {
		Integer key;
		Integer spaceCount = 0;
		for (int i = 0; i < SectionNameEng.length(); i++) {
			key = (int) SectionNameEng.charAt(i);
			if (key == 32)/* Space Key */
			{
				if (spaceCount > 0) {

					return true;
				}
				spaceCount++;
			} else {
				spaceCount = 0;
			}
		}
		return false;
	}

	private boolean unSupportedCharactersEnglish(String inputValue) {
		String regexPattern = "[#%&\\%&\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\,\\\\\\-\\|\\\\:\\;\\'\\\"\\<\\>\\?\\\\]";
		return executeRegEx(regexPattern, inputValue);
	}

	private boolean executeRegEx(String regexPattern, String input) {
		Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}

	public boolean validateEntityType(String entityType) {
		return (!((CommanConstant.HABITATION_PARENT_TYPE_GRAMPANCHAYAT.toString()).equals(entityType)
				|| (CommanConstant.HABITATION_PARENT_TYPE_VILLAGE.toString()).equals(entityType)));
	}

	public boolean validateLbHierarchy(String localBodyLevelCodes, boolean isManage) {
		boolean flag = false;
		if (isManage) {
			if (StringUtils.isBlank(localBodyLevelCodes)) {
				flag = true;
			} else {
				String lbCodeArray[] = localBodyLevelCodes.split(",");
				for (int i = 0; i < lbCodeArray.length; i++) {
					if (StringUtils.isBlank(lbCodeArray[i])) {
						flag = true;
						break;
					}
				}

			}
		}

		return flag;
	}

}
