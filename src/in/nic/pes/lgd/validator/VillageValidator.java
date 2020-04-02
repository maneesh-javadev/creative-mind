package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.HabitationForm;
import in.nic.pes.lgd.forms.LBFreezeForm;
import in.nic.pes.lgd.forms.OrganizationUnitForm;
import in.nic.pes.lgd.forms.SearchForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.VillageService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("VillageValidator")
public class VillageValidator implements Validator {
	
	private static final Logger log = Logger.getLogger(VillageValidator.class);
	
	@Autowired
	private CommonValidatorImpl commonValidatorImpl;
	
	@Autowired
	private VillageService villageService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return VillageForm.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object object, Errors errors) {
		VillageForm villageForm = (VillageForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		String govtOrderConfig = villageForm.getGovtOrderConfig();
		if (!villageForm.getListVillageDetails().isEmpty()) {
			VillageDataForm villageDataForm = villageForm.getListVillageDetails().get(0);

			Date today = new Date();
			Date ordDate = villageDataForm.getOrderDatecr();
			Date gazDate = villageDataForm.getGazPubDatecr();
			if (villageDataForm.getLati() != null) {
				if (!villageDataForm.getLati().isEmpty()) {
					if (!customValidator.checkforLatiandLongi(villageForm.getLati())) {
						errors.rejectValue("lati", "error.valid.COMMONLONGANDLATVALUE", "Latitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
					}
				}
			}

			if (villageDataForm.getLongi() != null) {
				if (!villageDataForm.getLongi().isEmpty()) {
					if (!customValidator.checkforLatiandLongi(villageForm.getLongi())) {
						errors.rejectValue("longi", "error.valid.COMMONLONGANDLATVALUE", "Longitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
					}
				}
			}

			if (villageDataForm.getSscode() != null) {
				if (!villageDataForm.getSscode().isEmpty()) {
					if (villageDataForm.getSscode().length() > 5) {
						errors.rejectValue("sscode", "Error.Sscode");
					}
					if (!customValidator.checkforAlphaNumericand(villageDataForm.getSscode())) {
						errors.rejectValue("sscode", "Error.SscodeAlphaNumeric");
					}
				}
			}

			String strFileCount=villageForm.getGovtfilecount();
			Integer fileCount=0;
			if(strFileCount!=null)
			{
				fileCount=Integer.parseInt(strFileCount);
			}
			if (!(villageDataForm.getOrderNocr().equalsIgnoreCase("")) || villageDataForm.getOrderDatecr() != null || villageDataForm.getOrdereffectiveDatecr() != null || villageDataForm.getGazPubDatecr() != null
					|| (villageForm.getAttachFile1()!=null)) {
			
				if (!(villageDataForm.getOrderNocr().equalsIgnoreCase("")) || villageDataForm.getOrderDatecr() != null || villageDataForm.getOrdereffectiveDatecr() != null || villageDataForm.getGazPubDatecr() != null
						|| (!villageForm.getAttachFile1().get(0).isEmpty())) {		
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderNocr", "orderNo.required", "Order Number is required.");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderDatecr", "orderDate.required", "Order date is required.");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ordereffectiveDatecr", "ordereffectiveDate.required", "Order Effective date is required.");
					if (villageDataForm.getOrderNocr() == null) {
						try {
							errors.rejectValue("orderNocr", "ORDERNO.REQUIRED");

						} catch (Exception e) {
							errors.rejectValue("orderNocr", "ORDERNO.REQUIRED");
						}

					} else if (!customValidator.checkforOrderNum(villageDataForm.getOrderNocr())) {
						errors.rejectValue("orderNocr", "error.ms", "Order Number contains invalid characters");
					}

					if (villageDataForm.getOrderDatecr() == null) {
						try {
							errors.rejectValue("orderDatecr", "orderDate.required");
						} catch (Exception e) {
							errors.rejectValue("orderDatecr", "orderDate.required");
						}

					} else {
						if (!customValidator.checkforDate(villageDataForm.getOrderDatecr())) {

							errors.rejectValue("orderDatecr", "error.valid.ORDERDATE");
						}

						if (ordDate.compareTo(today) > 0) {
							errors.rejectValue("orderDatecr", "error.valid.morecurrentDate");
						}
					}

					if (villageDataForm.getOrdereffectiveDatecr() == null) {
						try {
							errors.rejectValue("ordereffectiveDatecr", "ordereffectiveDate.required");
						} catch (Exception e) {
							errors.rejectValue("ordereffectiveDatecr", "ordereffectiveDate.required");
						}

					} else if (!customValidator.checkforDate(villageDataForm.getOrdereffectiveDatecr())) {
						errors.rejectValue("ordereffectiveDatecr", "error.valid.EFFECTIVEDATE");
					}

					if (villageDataForm.getGazPubDatecr() != null) {

						if (gazDate.compareTo(ordDate) < 0) {
							errors.rejectValue("gazPubDatecr", "error.INCORECT.GAZPUBDATE");
						}
					}
					
					if(fileCount<=0)
					{
					if (govtOrderConfig.equals("govtOrderUpload")) {

						if (villageForm.getAttachFile1().isEmpty()) {
							try {
								errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
							} catch (Exception e) {
								errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
							}

						}
						else if(villageForm.getAttachFile1().get(0).isEmpty())
						{
							errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
						}

					} else if (govtOrderConfig.equals("govtOrderGenerate")) {

						if (villageForm.getTemplateList() != null) {
							int templateCode = Integer.parseInt(villageForm.getTemplateList());
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

	public VillageForm validateChange(Object object, Errors errors, HttpServletRequest request) throws Exception {
		VillageForm villageForm = (VillageForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		List<VillageDataForm> villageDataForm = villageForm.getListVillageDetails();
		commonValidatorImpl.isValidMimeForVilCreate(errors, request, villageForm.getAttachFile2());
		if (!villageDataForm.isEmpty()) {
			VillageDataForm element = villageDataForm.get(0);
			String villageNameEngch = element.getVillageNameEnglishCh();
			String VillageNameEng = element.getVillageNameEnglish();
			String villageNameLocal = element.getVillageNameLocalCh();
			String strNameAlias = element.getAliasEnglishCh();
			String strNameLocalAlias = element.getAliasLocalCh();
			Integer subDistrictCode = element.getSubdistrictCode();

			if (villageNameEngch == "" || villageNameEngch.isEmpty()) {
				errors.rejectValue("villageNameEnglishCh", "VILLAGENAME.REQUIRED", "Error.blank.VillageNameInEng");
				villageForm.setErrorflag(2);
			} else if (!customValidator.checkforAlphabetWithSpaceDotSlashandBrackets(villageNameEngch)) {
				errors.rejectValue("villageNameEnglishCh", "Error.data.villageNameEng");
			} else if (villageNameEngch.equalsIgnoreCase(VillageNameEng)) {
				errors.rejectValue("errorflag", "error.ms", "error.change.commonAlert");
				villageForm.setErrorflag(1);
			} else if (subDistrictCode != null) {
				/* Modified by Pooja on 23-07-2015 */
				Character existVillageMode = villageService.VilageExist(subDistrictCode, villageNameEngch);
				if (existVillageMode.equals('P')) {
					errors.rejectValue("villageNameEnglishCh", "error.ms", "Same Village Name already exist.");
				} else if (existVillageMode.equals('S')) {
					errors.rejectValue("villageNameEnglishCh", "error.ms", "Same Village Name already exist in Draft mode. Plz go to View n Manage Draft entities and Publish the village or delete the Draft version.");
				}
			}

			if (!villageNameLocal.isEmpty() && !customValidator.validateSpecialCharacters(villageNameLocal)) {
				errors.rejectValue("villageNameLocalCh", "Error.data.VillageNameLocal");
			}

			if (!strNameAlias.isEmpty() && !customValidator.checkforAlphabetsandSpace(strNameAlias)) {
				errors.rejectValue("aliasEnglishCh", "Error.data.villageAliasNameEng");
			}

			if (!strNameLocalAlias.isEmpty() && !customValidator.validateSpecialCharacters(strNameLocalAlias)) {
				errors.rejectValue("aliasLocalCh", "Error.data.villageAliasNameLocal");
			}

		}
		return villageForm;
	}

	/**
	 * Modified on 04-09-2014 Add Server Side Check Validfation for govt order
	 * attachment and upload map attachment
	 */
	public void villageCreationValidation(VillageForm villageForm, Errors errors, HttpServletRequest request) {

		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		String strNameLocal = null;
		try {
			if (villageForm != null) {

				commonValidatorImpl.isValidMime(errors, request, villageForm.getAttachFile1());
				commonValidatorImpl.isValidMimeForVilCreate(errors, request, villageForm.getAttachFile2());

				/*
				 * try { if (villageForm.getDistrictNameEnglish() == null ||
				 * villageForm.getDistrictNameEnglish().isEmpty() ||
				 * villageForm.getDistrictNameEnglish().toString() .equals("0"))
				 * { errors.rejectValue("districtNameEnglish", "error.PSDT"); }
				 * else { if (!customValidator.checkforOnlyNumeric(villageForm
				 * .getDistrictNameEnglish())) {
				 * errors.rejectValue("districtNameEnglish", "error.PSDT"); }
				 * 
				 * } } catch (Exception e) {
				 * errors.rejectValue("districtNameEnglish", "error.PSDT"); }
				 */
				try {
					if (villageForm.getSubdistrictNameEnglish() == null || villageForm.getSubdistrictNameEnglish().isEmpty() || villageForm.getSubdistrictNameEnglish().toString().equals("0")) {
						errors.rejectValue("subdistrictNameEnglish", "error.PSSDT");
					} else {
						if (!customValidator.checkforOnlyNumeric(villageForm.getSubdistrictNameEnglish())) {
							errors.rejectValue("subdistrictNameEnglish", "error.PSSDT");
						}

					}
				} catch (Exception e) {
					errors.rejectValue("subdistrictNameEnglish", "error.PSSDT");
				}
				try {
					if (villageForm.getNewVillageNameEnglish() == null || villageForm.getNewVillageNameEnglish().trim().isEmpty()) {
						errors.rejectValue("newVillageNameEnglish", "error.blank.villageNameInEn");
					}
					if (villageForm.getNewVillageNameEnglish() != null && !villageForm.getNewVillageNameEnglish().trim().isEmpty()) {
						//modified by pooja on 19-10-2015 for allowing brackets and hyphen in village name
						if (!customValidator.validateSpecialCharacters(villageForm.getNewVillageNameEnglish())) {
							errors.rejectValue("newVillageNameEnglish", "Error.data.villageNameEng");
						}
					}
				} catch (Exception e) {
					errors.rejectValue("newVillageNameEnglish", "error.blank.villageNameInEn");
				}

				try {
					if (villageForm.getSubdistrictNameEnglish() != null && !villageForm.getSubdistrictNameEnglish().isEmpty() && !villageForm.getSubdistrictNameEnglish().toString().equals("0")) {
						{
							/* Modified by Pooja on 22-07-2015 */
							Character existVillageMode = villageService.VilageExist(Integer.parseInt(villageForm.getSubdistrictNameEnglish()), villageForm.getNewVillageNameEnglish());
							if (existVillageMode.equals('P')) {
								errors.rejectValue("newVillageNameEnglish", "error.ms", "Same Village Name already exist.");
							} else if (existVillageMode.equals('S')) {
								errors.rejectValue("newVillageNameEnglish", "error.ms", "Same Village Name already exist in Draft mode. Plz go to View n Manage Draft entities and Publish the village or delete the Draft version.");
							}
						}
					}
				} catch (Exception e) {
					errors.rejectValue("newVillageNameEnglish", "Error.alredyExist.villageNameEng");
				}
				strNameLocal = villageForm.getNewVillageNameLocal();

				if (!strNameLocal.isEmpty() && !customValidator.validateSpecialCharacters(strNameLocal)) {
					errors.rejectValue("newVillageNameLocal", "error.ms", "Village Name Local contains invalid characters");
				}

				if (villageForm.getNewVillageAliasEnglish() != null && !villageForm.getNewVillageAliasEnglish().trim().isEmpty()) {
					if (!customValidator.checkforAlphabetsandSpace(villageForm.getNewVillageAliasEnglish())) {
						errors.rejectValue("newVillageAliasEnglish", "error.valid.COMMONALPHABETREQUIRED");
					}
				}

				strNameLocal = villageForm.getNewVillageAliasLocal();

				if (!strNameLocal.isEmpty() && !customValidator.validateSpecialCharacters(strNameLocal)) {
					errors.rejectValue("newVillageAliasLocal", "error.ms", "Village Name Alias Local contains invalid characters");
				}

				if (villageForm.getStateSpecificCode() != null && !villageForm.getStateSpecificCode().trim().isEmpty()) {
					if (!customValidator.checkforAlphaNumericand(villageForm.getStateSpecificCode())) {
						errors.rejectValue("stateSpecificCode", "error.valid.COMMONNUMERICREQUIRED");
					}
				}
				/*
				 * if (villageForm.getNewVillageNameLocal() != null ||
				 * !villageForm.getNewVillageNameLocal().trim().isEmpty()) { if
				 * (!customValidator.checkforAlphabetsandSpace(villageForm.
				 * getNewVillageNameLocal())) {
				 * errors.rejectValue("newVillageNameLocal",
				 * "error.valid.COMMONALPHABETREQUIRED"); } }
				 */

				if (!villageForm.isCreateFromCoverageOfUrbanLocalBody() && !villageForm.isCreateFromNewLand() && !villageForm.isCreateFromExistingVillages()) {
					errors.rejectValue("createFromExistingVillages", "error.PSAMTCV");
				}
				if (villageForm.isCreateFromCoverageOfUrbanLocalBody()) {
					if (villageForm.getSelectedCoveredLandRegionByULB() == null) {
						errors.rejectValue("selectedCoveredLandRegionByULB", "error.PSULB");
					}
				}
				if (villageForm.isCreateFromExistingVillages()) {
					if (villageForm.getContributedVillages() == null) {
						errors.rejectValue("contributedVillages", "error.PSCV");
					}
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}

	/**
	 * Added on 11-09-2014
	 * Check Manage Draft Village Server Side Validation.
	 */
	public void villageDraftModifyValidation(VillageForm villageForm, VillageDataForm villageDataForm, Errors errors, HttpServletRequest request, String villagePreviousName) {

		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		String strNameLocal = null;
		try {
			if (villageForm != null) {

				commonValidatorImpl.isValidMime(errors, request, villageForm.getAttachFile1());
				commonValidatorImpl.isValidMime(errors, request, villageForm.getAttachFile2());

				try {
					if (villageDataForm.getVillageNameEnglish() == null || villageDataForm.getVillageNameEnglish().trim().isEmpty()) {
						errors.rejectValue("newVillageNameEnglish", "error.blank.villageNameInEn");
					}
					if (villageDataForm.getVillageNameEnglish() != null && !villageDataForm.getVillageNameEnglish().trim().isEmpty()) {
						if (!customValidator.validateSpecialCharacters(villageDataForm.getVillageNameEnglish())) {
							errors.rejectValue("newVillageNameEnglish", "Error.data.villageNameEng");
						}
					}
					/* added by pooja on 22-07-2015 */
					if (villageForm.getSubdistrictNameEnglish() != null && !villageForm.getSubdistrictNameEnglish().isEmpty() && !villageForm.getSubdistrictNameEnglish().toString().equals("0")) {
						if (villageDataForm.getVillageNameEnglish() != null && !villageDataForm.getVillageNameEnglish().trim().isEmpty() && !("").equals(villagePreviousName) && villagePreviousName != null) {
							if (!villagePreviousName.equalsIgnoreCase(villageDataForm.getVillageNameEnglish())) {
								Character existVillageMode = villageService.VilageExist(Integer.parseInt(villageForm.getSubdistrictNameEnglish()), villageDataForm.getVillageNameEnglish());
								if (existVillageMode.equals('P')) {
									errors.rejectValue("newVillageNameEnglish", "error.ms", "Same Village Name already exist.");
								} else if (existVillageMode.equals('S')) {
									errors.rejectValue("newVillageNameEnglish", "error.ms", "Same Village Name already exist in Draft mode. Plz go to View n Manage Draft entities and Publish the village or delete the Draft version.");
								}
							}
						}
					}
				} catch (Exception e) {
					errors.rejectValue("newVillageNameEnglish", "error.blank.villageNameInEn");
				}
				strNameLocal = villageDataForm.getVillageNameLocal();

				if (!strNameLocal.isEmpty() && !customValidator.validateSpecialCharacters(strNameLocal)) {
					errors.rejectValue("newVillageNameLocal", "error.ms", "Village Name Local contains invalid characters");
				}

				if (villageDataForm.getAliasEnglish() != null && !villageDataForm.getAliasEnglish().trim().isEmpty()) {
					if (!customValidator.checkforAlphabetsandSpace(villageDataForm.getAliasEnglish())) {
						errors.rejectValue("newVillageAliasEnglish", "error.valid.COMMONALPHABETREQUIRED");
					}
				}

				strNameLocal = villageDataForm.getAliasLocal();

				if (!strNameLocal.isEmpty() && !customValidator.validateSpecialCharacters(strNameLocal)) {
					errors.rejectValue("newVillageAliasLocal", "error.ms", "Village Name Alias Local contains invalid characters");
				}

				if (villageDataForm.getSscode() != null && !villageDataForm.getSscode().trim().isEmpty()) {
					if (!customValidator.checkforAlphaNumericand(villageDataForm.getSscode())) {
						errors.rejectValue("stateSpecificCode", "error.valid.COMMONNUMERICREQUIRED");
					}
				}
				if (!villageForm.isCreateFromCoverageOfUrbanLocalBody() && !villageForm.isCreateFromNewLand() && !villageForm.isCreateFromExistingVillages()) {
					errors.rejectValue("createFromExistingVillages", "error.PSAMTCV");
				}
				if (villageForm.isCreateFromCoverageOfUrbanLocalBody()) {
					if (villageForm.getSelectedCoveredLandRegionByULB() == null) {
						errors.rejectValue("selectedCoveredLandRegionByULB", "error.PSULB");
					}
				}
				if (villageForm.isCreateFromExistingVillages()) {
					if (villageForm.getContributedVillages() == null) {
						errors.rejectValue("contributedVillages", "error.PSCV");
					}
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}
	
	public void villageInvalidationValidation(VillageForm villageForm,
			BindingResult result,HttpServletRequest request) {
		if (villageForm != null) {
			if(villageForm.getAttachFile2()!=null){
				commonValidatorImpl.isValidMimeForVilCreate(result, request, villageForm.getAttachFile2());
			}
			if(villageForm.getAttachFile1()!=null){
				commonValidatorImpl.isValidMimeForVilCreate(result, request, villageForm.getAttachFile1());
			}
			if (villageForm.getDistrictNameEnglish() == null
					|| villageForm.getDistrictNameEnglish().isEmpty()
					|| villageForm.getDistrictNameEnglish().toString()
							.equals("0")) {
				if (villageForm.getSelectedCoveredLandRegionByULB() == null) {
					result.rejectValue("districtNameEnglish", "error.PSDT");
				}
			}
			if (villageForm.getSubdistrictNameEnglish() == null
					|| villageForm.getSubdistrictNameEnglish().isEmpty()
					|| villageForm.getSubdistrictNameEnglish().toString()
							.equals("0")) {
				if (villageForm.getSelectedCoveredLandRegionByULB() == null) {
					result.rejectValue("subdistrictNameEnglish", "error.PSSDT");
				}
			}
			if (villageForm.getInvalidateVillageList() == null
					|| villageForm.getInvalidateVillageList().isEmpty()) {
				if (villageForm.getSelectedCoveredLandRegionByULB() == null) {
					result.rejectValue("invalidateVillageList", "error.PSVTI");
				}
			}
			if (villageForm.isRdoVillageDelete()) {
				if (villageForm.getDdSubdistrictCode() == null
						|| villageForm.getDdSubdistrictCode().isEmpty()
						|| villageForm.getDdSubdistrictCode().toString()
								.equals("0")) {
					result.rejectValue("ddSubdistrictCode", "error.PSSDT");
				}
				if (villageForm.getVillageListMerge() == null
						|| villageForm.getVillageListMerge().isEmpty()
						|| villageForm.getVillageListMerge().toString()
								.equals("0")) {
					result.rejectValue("villageListMerge", "error.PSV");
				}
			}

		}

	}

	public void viewValidateVillage(Object object, Errors errors) {

		VillageDataForm villageForm = (VillageDataForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		if (villageForm.getCorrectionRadio().equals("N")) {
			if (villageForm.getVillageNameEnglish() != null
					&& !villageForm.getVillageNameEnglish().equals("")) {
				if (!customValidator.checkforAlphabetsandSpace(villageForm
						.getVillageNameEnglish())) {
					errors.rejectValue("villageNameEnglish", "error.ms",
							"This field contains invalid characters.Please use A-Z or a-z");
				}
				if(villageForm.getVillageNameEnglish().length()<3){
					errors.rejectValue("villageNameEnglish", "error.ms",
							"Please enter minimum 3 letter of village name start");
				}
			}
			if ((villageForm.getCode() != null && !villageForm.getCode()
					.equals(""))) {
				if (!customValidator.checkforOnlyNumeric(villageForm.getCode())) {
					errors.rejectValue("code", "error.ms",
							"This field contains invalid characters.Please use 0 to 9");

				}
			}
		}
		if (villageForm.getCorrectionRadio().equals("P")) {
			if (villageForm.getDistrictNameEnglish() == null
					|| villageForm.getDistrictNameEnglish().isEmpty()) {
				errors.rejectValue("districtNameEnglish", "error.ms",
						"Please select District");
			} else {
				if (villageForm.getDistrictNameEnglish() != null
						&& !villageForm.getDistrictNameEnglish().isEmpty()) {
					try {
						int code = Integer.parseInt(villageForm
								.getDistrictNameEnglish());
						if (code < 1) {
							errors.rejectValue("districtNameEnglish",
									"error.ms", "Please select District");
						}
					} catch (Exception e) {
						errors.rejectValue("districtNameEnglish", "error.ms",
								"Please select District");
					}
				}
			}

			if (villageForm.getSubdistrictNameEnglish() == null
					|| villageForm.getSubdistrictNameEnglish().isEmpty()) {
				errors.rejectValue("subdistrictNameEnglish", "error.ms",
						"Please select SubDistrict");
			} else {
				if (villageForm.getSubdistrictNameEnglish() != null
						&& !villageForm.getSubdistrictNameEnglish().isEmpty()) {
					try {
						int code = Integer.parseInt(villageForm
								.getSubdistrictNameEnglish());
						if (code < 1) {
							errors.rejectValue("subdistrictNameEnglish",
									"error.ms", "Please select SubDistrict");
						}
					} catch (Exception e) {
						errors.rejectValue("subdistrictNameEnglish",
								"error.ms", "Please select SubDistrict");
					}
				}
			}
		}
	}
	public void validateSearch(Object object, Errors errors) 
	{
		SearchForm searchView = (SearchForm) object;
		int flag=0;
		if(searchView.getEntityName() == null || searchView.getEntityCode() == null)
			flag=1;
		if(flag==1)
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "entityName","Please enter search details Entity Name/Entity Code");
		}
		
		/*if(searchView.getEntityName() == null)
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "entityName","search.entityname.required");
		}*/
		
		if (searchView.isDistrictChecked()==false && searchView.isPanchayatChecked()==false && searchView.isBlockChecked()==false && searchView.isStateChecked()==false && searchView.isTraditionalChecked()==false && searchView.isUrbanChecked()==false && searchView.isVillageChecked()==false && searchView.isSubDistrictChecked()==false && searchView.isPcChecked()==false && searchView.isAcChecked()==false && searchView.isRuralChecked()==false)
		{
			errors.rejectValue("selectedVal",	"errorMessage.notselectedoption");
		}
		
	}
	/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
	public boolean validateHabitationForm(Object object, Errors errors,String parent_type) {

		HabitationForm habitationobj = (HabitationForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		if (object != null) {
			if (habitationobj.getHabitationNameEnglish()== null	|| habitationobj.getHabitationNameEnglish().isEmpty() || habitationobj.getHabitationNameEnglish().toString().equals("0")) {
				errors.rejectValue("habitationNameEnglish", "Please Enter a valid District Name");
				return false;
			}
			if (!customValidator.checkforAlphabetWithSpaceDotSlashandBrackets(habitationobj.getHabitationNameEnglish())) {
				errors.rejectValue("habitationNameEnglish","This field contains invalid value.Please use A-Z ,a-z,0-9 ,Dot(.),(),[]");
				return false;
			}
			if(habitationobj.getEffectiveDate() == null || habitationobj.getEffectiveDate().toString() == ""){
				errors.rejectValue("effectiveDate","Please Enter a valid Date");
				return false;
			}
			if(parent_type == "G" || parent_type == "V"){
				if("".equals(habitationobj.getParentType())){
					errors.rejectValue("parentCode", "Please Select a Parent");
					return false;
				}
			}			
			String parent= String.valueOf(habitationobj.getParentType());
			if(parent == null){
				errors.rejectValue("parentType", "Please Select a parent type");
				return false;
			}
			return true;
		}
		else
		{
		return false;
		}
	}
	
	
	
	//Added By Anju Gupta  on 12/1/2015 for Organization unit */
	public boolean viewdepartmentorgunit(Object object,Errors errors){
		
		OrganizationUnitForm organizationUnit =(OrganizationUnitForm) object;
		
		/*if(organizationUnit.getPhoneNo()==""){
			errors.rejectValue("phoneNo","","Please Enter a Valid field");
			return false;
		}
		
		else */
		/*
		if(organizationUnit.getAddress1()==""){
			errors.rejectValue("address1","","please enter a Address1");
			return false;
		}*/
	
		return true;
	}

	/**
	 * Added by Ripunj for Rename Draft Mode on 11-03-2015 
	 * @param object
	 * @param errors
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public VillageForm validateChangeModify(Object object, Errors errors,HttpServletRequest request) throws Exception {
		VillageForm villageForm = (VillageForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		List<VillageDataForm> villageDataForm=villageForm.getListVillageDetails();
		commonValidatorImpl.isValidMime(errors, request, villageForm.getAttachFile1());
		if(!villageDataForm.isEmpty())
		{
			VillageDataForm	element=villageDataForm.get(0);
			String villageNameEngch = element.getVillageNameEnglishCh();
			String VillageNameEng=element.getVillageNameEnglish();
			String villageNameLocal = element.getVillageNameLocalCh();
			String strNameAlias = element.getAliasEnglishCh();
			String strNameLocalAlias = element.getAliasLocalCh();
			Integer subDistrictCode=element.getSubdistrictCode();
			
			
			
			
			if(villageNameEngch=="" || villageNameEngch.isEmpty())   
			{
				errors.rejectValue("villageNameEnglishCh", "VILLAGENAME.REQUIRED","Error.blank.VillageNameInEng");
				villageForm.setErrorflag(2);
			}
			else 	if (!customValidator.checkforAlphabetWithSpaceDotSlashandBrackets(villageNameEngch)) {
				errors.rejectValue("villageNameEnglishCh", "Error.data.villageNameEng");
			}
			/*else if(villageNameEngch.equalsIgnoreCase(VillageNameEng))
			{
				errors.rejectValue("errorflag", "error.ms",
						"error.change.commonAlert");
				villageForm.setErrorflag(1);
			}*/
			else if (subDistrictCode != null) {
				/* Modified by Pooja on 22-07-2015 */
				if (!villageNameEngch.equalsIgnoreCase(VillageNameEng)) {
					Character existVillageMode = villageService.VilageExist(subDistrictCode, villageNameEngch);
					if (existVillageMode.equals('P')) {
						errors.rejectValue("villageNameEnglishCh", "error.ms", "Same Village Name already exist.");
					} else if (existVillageMode.equals('S')) {
						errors.rejectValue("villageNameEnglishCh", "error.ms", "Same Village Name already exist in Draft mode. Plz go to View n Manage Draft entities and Publish the village or delete the Draft version.");
					}
				}
			}
			
			if (!villageNameLocal.isEmpty()
					&& !customValidator.validateSpecialCharacters(villageNameLocal)) {
				errors.rejectValue("villageNameLocalCh","Error.data.VillageNameLocal");
			}
			
			
			if (!strNameAlias.isEmpty()
					&& !customValidator.checkforAlphabetsandSpace(strNameAlias)) {
				errors.rejectValue("aliasEnglishCh","Error.data.villageAliasNameEng");
			}
			
			if (!strNameLocalAlias.isEmpty()
					&& !customValidator
							.validateSpecialCharacters(strNameLocalAlias)) {
				errors.rejectValue("aliasLocalCh", "Error.data.villageAliasNameLocal");
			}
		
			
			
		}
		return villageForm;
	}
	/*------- Govt Order Form validation on 11-03-2014-----------*/
	public void validateGovOrderForm(Object object, Errors errors) {
		//Modify State
		LBFreezeForm lbfreezeform=(LBFreezeForm)object;
		String govtOrderConfig = lbfreezeform.getGovtOrderConfig();
		
		Date today = new Date();
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();

		 
			if(lbfreezeform.getOrderNo().isEmpty())
			{			
				try {
					errors.rejectValue("orderNo","Msg.ORDERNO");	
				} catch (Exception e) {
					errors.rejectValue("orderNo","Msg.ORDERNO");	
				}			
								
			}
			else if(!customValidator.checkforOrderNum(lbfreezeform.getOrderNo()))
			{
				errors.rejectValue("orderNo","error.valid.ORDERNO");	
			}
	 		
			if(lbfreezeform.getOrderDate()==null)
			{
				try {
					errors.rejectValue("orderDate","Msg.ORDERDATE");		
				} catch (Exception e) {
					errors.rejectValue("orderDate","Msg.ORDERDATE");		
				}			
			}	
			else if(lbfreezeform.getOrderDate() != null)
			{
			  if (!customValidator.checkforDate(lbfreezeform.getOrderDate())) // will only matches alphabets
				 {
					errors.rejectValue("orderDate", "error.valid.ORDERDATE");
				 }
			  else if(lbfreezeform.getOrderDate().after(today) && !lbfreezeform.getOrderDate().equals(today))
				{
					errors.rejectValue("orderDate", "error.INCORECT.ORDERDATE");
				}
			}
			
			if(lbfreezeform.getEffectiveDate()==null)
			{		
				try {
					errors.rejectValue("effectiveDate","Msg.EFFECTIVEDATE");				
				} catch (Exception e) {
					errors.rejectValue("effectiveDate","Msg.EFFECTIVEDATE");			
				}		
							
			}
			else if(lbfreezeform.getEffectiveDate() != null)
			{
				 if (!customValidator.checkforDate(lbfreezeform.getEffectiveDate())) // will only matches alphabets
				 {
					errors.rejectValue("effectiveDate", "error.valid.EFFECTIVEDATE");
				 }
				 
				 if(lbfreezeform.getEffectiveDate().after(new Date())){
					 errors.rejectValue("effectiveDate", "error.valid.EFFECTIVEFUTUREDATE"); 
				 }
				 
				 else if(lbfreezeform.getEffectiveDate().before(lbfreezeform.getOrderDate()) && !lbfreezeform.getEffectiveDate().equals(lbfreezeform.getOrderDate()))
				{
					errors.rejectValue("effectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				 
			}
		
			if(govtOrderConfig.equals("govtOrderUpload"))
			{
			
				/*if(stateForm.getGazPubDate() != null)
				{ 
					if (!customValidator.checkforDate(stateForm.getGazPubDate())) // will only matches alphabets
					 {
						errors.rejectValue("gazPubDate2", "error.valid.GAZPUBDATE");
					 }
					else if(stateForm.getGazPubDate().before(stateForm.getOrderDate()) && !stateForm.getGazPubDate().equals(stateForm.getOrderDate()))				 
					{
						errors.rejectValue("gazPubDate2", "error.INCORECT.GAZPUBDATE");
					}
				}*/
				
				if(lbfreezeform.getAttachFile2().isEmpty())
				{		
					try {
						errors.rejectValue("attachFile2","Msg.UPLOADGOVTORDER");	
					} catch (Exception e) {
						errors.rejectValue("attachFile2","Msg.UPLOADGOVTORDER");		
					}
						
					
				}
			
				/*
				else if(stateForm.getAttachFile1().size() >0 && stateForm.getAttachFile1().get(0).getOriginalFilename() == null || stateForm.getAttachFile1().get(0).getOriginalFilename().equals(""))
				{
					errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
				}*/
			}
			else if(govtOrderConfig.equals("govtOrderGenerate"))
			{ 
			
				if(lbfreezeform.getTemplateList() != null)
				{
					int templateCode = Integer.parseInt(lbfreezeform.getTemplateList());
					if(templateCode <1)
					{
						errors.rejectValue("templateList","Msg.GovtOrderGenerate");	
					}
				}
			}
			
		}
}
