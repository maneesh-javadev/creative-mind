package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.DistrictDataForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.service.CommonService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("DistrictValidator")
public class DistrictValidator implements Validator {
	//private static final Logger LOG = Logger.getLogger(DistrictValidator.class);
	
	@Autowired
	private CommonService commonService;

	public boolean supports(Class<?> clazz) {
		return DistrictForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		// modify Block
		DistrictForm districtForm = (DistrictForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		List<DistrictDataForm> distFormList = districtForm.getListDistrictDetails();
		String govtOrderConfig = districtForm.getGovtOrderConfig();
		Date today = new Date();

		if (!distFormList.isEmpty()) {

			DistrictDataForm distForm = distFormList.get(0);
			String aliasNameInEn = distForm.getAliasEnglish();
			String aliasNameInLc = distForm.getAliasLocal();
			Date ordDate = distForm.getOrderDatecr();
			if (aliasNameInEn != null) {
				if (!(aliasNameInEn.isEmpty())) {
					if (!customValidator.checkforAlphabetsandSpace(aliasNameInEn)) {
						errors.rejectValue("aliasEnglishch", "error.ms", "District Name Alias contains invalid characters");
					}
				}
			}

			if (aliasNameInLc != null) {
				if (!(aliasNameInLc.isEmpty())) {
					if (!customValidator.validateSpecialCharacters(aliasNameInLc)) {
						errors.rejectValue("aliasLocalch", "error.ms", "District Name Alias Local contains invalid characters");
					}
				}
			}
			if (districtForm.getLati() != null && !districtForm.getLati().isEmpty()) {
				if (!customValidator.checkforLatiandLongi(districtForm.getLati())) {
					errors.rejectValue("coordinates", "error.ms", "Latitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
				} else if (!customValidator.checkLatitudeRange(districtForm.getLati())) {
					errors.rejectValue("coordinates", "error.ms", "latitude range between 6 to 38 north");
				}
			}
			if (districtForm.getLongi() != null && !districtForm.getLongi().isEmpty()) {
				if (!customValidator.checkforLatiandLongi(districtForm.getLongi())) {
					errors.rejectValue("coordinates", "error.ms", "Longitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
				} else if (!customValidator.checkLongitudeRange(districtForm.getLongi())) {
					errors.rejectValue("coordinates", "error.ms", "longitude range between 32 to 98 east ");
				}
			}
			if (!customValidator.checkforAlphabetsandSpace(distForm.getHeadquarterName()) && !(distForm.getHeadquarterName().isEmpty())) {
				errors.rejectValue("headquarterName", "error.ms", "Headquarter Name Alias contains invalid characters");
			}
			if (!distForm.getSscode().isEmpty()) {
				if (distForm.getSscode().length() > 5) {
					errors.rejectValue("sscode", "Error.Sscode");
				}
				if (!customValidator.checkforAlphaNumericand(distForm.getSscode())) {
					errors.rejectValue("sscode", "State specific code must be alpha numeric");
				}
			}

			String strFileCount = districtForm.getGovtfilecount();
			Integer fileCount = 0;
			if (strFileCount != null) {
				fileCount = Integer.parseInt(strFileCount);
			}
			if (!(distForm.getOrderNocr().equalsIgnoreCase("")) || distForm.getOrderDatecr() != null || distForm.getOrdereffectiveDatecr() != null || distForm.getGazPubDatecr() != null || (districtForm.getAttachFile1() != null)) {
				if (!(distForm.getOrderNocr().equalsIgnoreCase("")) || distForm.getOrderDatecr() != null || distForm.getOrdereffectiveDatecr() != null || distForm.getGazPubDatecr() != null || (!districtForm.getAttachFile1().get(0).isEmpty())) {

					if (distForm.getOrderNocr() == null || districtForm.getListDistrictDetails().get(0).getOrderNocr().isEmpty() || districtForm.getListDistrictDetails().get(0).getOrderNocr().toString().equals("0")) {
						try {
							errors.rejectValue("orderNocr", "ORDERNO.REQUIRED");
						} catch (Exception e) {
							errors.rejectValue("orderNocr", "ORDERNO.REQUIRED");
						}

					} else if (!customValidator.checkforOrderNum(distForm.getOrderNocr())) {
						errors.rejectValue("orderNocr", "error.ms", "Order Number contains invalid characters");
					}
					if (distForm.getOrderDatecr() == null) {
						try {
							errors.rejectValue("orderDatecr", "orderDate.required");
						} catch (Exception e) {
							errors.rejectValue("orderDatecr", "orderDate.required");
						}

					} else {
						if (!customValidator.checkforDate(districtForm.getListDistrictDetails().get(0).getOrderDatecr())) {

							errors.rejectValue("orderDatecr", "error.valid.ORDERDATE");
						}

						if (ordDate.compareTo(today) > 0) {
							errors.rejectValue("orderDatecr", "error.valid.morecurrentDate");
						}
					}
					if (distForm.getOrdereffectiveDatecr() == null) {
						try {
							errors.rejectValue("ordereffectiveDatecr", "ordereffectiveDate.required");
						} catch (Exception e) {
							errors.rejectValue("ordereffectiveDatecr", "ordereffectiveDate.required");
						}

					} else if (distForm.getOrdereffectiveDatecr().before(distForm.getOrderDatecr()) && !distForm.getOrdereffectiveDatecr().equals(distForm.getOrderDatecr())) {
						errors.rejectValue("ordereffectiveDatecr", "error.INCORECT.EFFECTIVEDATE");
					} else if (!customValidator.checkforDate(distForm.getOrdereffectiveDatecr())) {
						errors.rejectValue("ordereffectiveDatecr", "error.valid.EFFECTIVEDATE");
					}

					if (fileCount <= 0) {
						if (govtOrderConfig.equals("govtOrderUpload")) {

							if (districtForm.getAttachFile1().isEmpty()) {
								try {
									errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
								} catch (Exception e) {
									errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
								}

							} else if (districtForm.getAttachFile1().get(0).isEmpty()) {
								errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
							}

						} else if (govtOrderConfig.equals("govtOrderGenerate")) {

							if (districtForm.getTemplateList() != null) {
								int templateCode = Integer.parseInt(districtForm.getTemplateList());
								if (templateCode < 1) {
									errors.rejectValue("templateList", "Msg.GovtOrderGenerate");
								}
							}
						}

					}
				}

			}

		}

	}

	public DistrictForm validateChange(Object object, Errors errors) throws Exception {
		DistrictForm districtForm = (DistrictForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		List<DistrictDataForm> districtDataForm = districtForm.getListDistrictDetails();
		if (!districtDataForm.isEmpty()) {
			DistrictDataForm element = districtDataForm.get(0);
			String districtNameEngch = element.getDistrictNameEnglishch();
			String districtNameEng = element.getDistrictNameEnglish();
			String strNameAlias = element.getAliasEnglish();
			String strNameLocal = element.getDistrictNameLocalch();
			String strNameLocalAlias = element.getAliasLocalch();
			Integer stateCode = element.getStateCode();

			if (districtNameEngch == "" || districtNameEngch.isEmpty()) {
				errors.rejectValue("districtNameEnglishch", "error.blank.districtNameInEn");
				districtForm.setErrorflag(2);
			} else if (!customValidator.checkforAlphaNumericandSpace(districtNameEngch)) {
				errors.rejectValue("districtNameEnglishch",  "Error.data.DistrictNameEng");
			} else if (districtNameEngch.equalsIgnoreCase(districtNameEng)) {
				errors.rejectValue("errorflag", "error.change.commonAlert");
				districtForm.setErrorflag(1);
			} else if (stateCode != null) {
				if (commonService.existEntityName(stateCode, 'D', districtNameEngch)) {
					errors.rejectValue("districtNameEnglishch","error.alredyExist.districtNameEnglish");
				}
			}

			if (!strNameAlias.isEmpty() && !customValidator.checkforAlphabetsandSpace(strNameAlias)) {
				errors.rejectValue("aliasEnglishch","Error.data.DistrictAliasNameEng");
			}
			if (!strNameLocal.isEmpty() && !customValidator.validateSpecialCharacters(strNameLocal)) {
				errors.rejectValue("districtNameLocalch","Error.data.DistrictNameLocal");
			}
			if (!strNameLocalAlias.isEmpty() && !customValidator.validateSpecialCharacters(strNameLocalAlias)) {
				errors.rejectValue("aliasLocalch","Error.data.DistrictAliasNameLocal");
			}

		}

		return districtForm;

	}

	public void validateInvalidateDistrict(DistrictForm districtForm, BindingResult result) {

		if (districtForm != null) {
			if (districtForm.getDistrictNameEnglish() == null || districtForm.getDistrictNameEnglish().isEmpty() || districtForm.getDistrictNameEnglish().toString().equals("0")) {
				result.rejectValue("districtNameEnglish", "error.PSDT");
			}
			if (!districtForm.isRdoDistrictDelete()) {
				if (districtForm.getTargetDistrictYes() == null || districtForm.getTargetDistrictYes().isEmpty() || districtForm.getTargetDistrictYes().toString().equals("0")) {
					result.rejectValue("targetDistrictYes", "error.PSTDT");
				}
			}
			if (districtForm.isRdoDistrictDelete()) {
				if (districtForm.getSubDistrictList() != null && districtForm.getTargetDistrictNo().toString().equals("0")) {

					result.rejectValue("targetDistrictNo", "error.PSTDT");
					if (districtForm.getSubDistrictList() != null) {
						result.rejectValue("subDistrictList", "error.PMASTTD");
					}
				}

			}
		}
	}

	public void viewValidateDistrict(Object object, Errors errors) {
		DistrictForm districtForm = (DistrictForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		if (districtForm.getDistrictNameEnglish() != null && !districtForm.getDistrictNameEnglish().equals("")) {
			if (!customValidator.checkforAlphabetsandSpace(districtForm.getDistrictNameEnglish())) {
				errors.rejectValue("districtNameEnglish", "error.ms", "This field contains invalid characters.Please use A-Z or a-z");
			}
		}
		if ((districtForm.getCode() != null && !districtForm.getCode().equals(""))) {
			if (!customValidator.checkforOnlyNumeric(districtForm.getCode())) {
				errors.rejectValue("code", "error.ms", "This field contains invalid characters.Please use 0 to 9");

			}
		}
	}

}
