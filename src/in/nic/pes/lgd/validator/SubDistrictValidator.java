package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.service.CommonService;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("subDistrictValidator")
public class SubDistrictValidator implements Validator {

	@Autowired
	private CommonService commonService;
	
	

	@Override
	public boolean supports(Class<?> clazz) {
		return SubDistrictForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		// Modify SubDistrict correction
		SubDistrictForm subDistrictForm = (SubDistrictForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		List<SubdistrictDataForm> subDistForm = subDistrictForm.getListSubdistrictDetails();
		String govtOrderConfig = subDistrictForm.getGovtOrderConfig();
		Date today = new Date();

		if (!subDistForm.isEmpty()) {
			/*
			 * if (subForm.getCensus2011Code()== null) {
			 * errors.rejectValue("census2011Code", "Error.Census2011Code"); }
			 */
			SubdistrictDataForm subForm = subDistForm.get(0);
			if (subDistrictForm.getLati() != null) {
				if (!subDistrictForm.getLati().isEmpty()) {
					if (!customValidator.checkforLatiandLongi(subDistrictForm.getLati())) {
						errors.rejectValue("cordinate", "error.ms", "Latitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
					}
					else if(!customValidator.checkLatitudeRange(subDistrictForm.getLati()))
					{
						errors.rejectValue("cordinate", "error.ms", "latitude range between 6 to 38 north");
					}
				}
			}

			if (subDistrictForm.getLongi() != null) {
				if (!subDistrictForm.getLongi().isEmpty()) {
					if (!customValidator.checkforLatiandLongi(subDistrictForm.getLongi())) {
						errors.rejectValue("cordinate", "error.ms", "Longitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
					}
					else if(!customValidator.checkLongitudeRange(subDistrictForm.getLongi()))
					{
						errors.rejectValue("cordinate", "error.ms", "longitude range between 32 to 98 east ");
					}
				}

			}

			if (subForm.getHeadquarterName() != null) {
				if (!customValidator.checkforAlphabetsandSpace(subForm.getHeadquarterName()) && !(subForm.getHeadquarterName().isEmpty())) {
					errors.rejectValue("headquarterName", "error.ms", "Headquarter Name Alias contains invalid characters");
				}
			}

			if (subForm.getSscode() != null) {
				if (!subForm.getSscode().isEmpty()) {
					if (subForm.getSscode().length() > 5) {
						errors.rejectValue("sscode", "State specific code length must be 5");
					}
					if (!customValidator.checkforAlphaNumericand(subForm.getSscode())) {
						errors.rejectValue("sscode", "Please enter alphanumerics only");
					}
				}
			}
			
			String strFileCount=subDistrictForm.getGovtfilecount();
			Integer fileCount=0;
			if(strFileCount!=null)
			{
				fileCount=Integer.parseInt(strFileCount);
			}

			if (!(subForm.getOrderNocr().equalsIgnoreCase("")) || subForm.getOrderDatecr() != null || subForm.getOrdereffectiveDatecr() != null || subForm.getGazPubDatecr() != null || (subDistrictForm.getAttachFile1()!=null)) {

				if (!(subForm.getOrderNocr().equalsIgnoreCase("")) || subForm.getOrderDatecr() != null || subForm.getOrdereffectiveDatecr() != null || subForm.getGazPubDatecr() != null || (!subDistrictForm.getAttachFile1().get(0).isEmpty())) 
				 {
					if (subForm.getOrderNocr() == null || subForm.getOrderNocr().isEmpty() || subForm.getOrderNocr().toString().equals("0")) {
						try {
							errors.rejectValue("orderNocr", "ORDERNO.REQUIRED");

						} catch (Exception e) {
							errors.rejectValue("orderNocr", "ORDERNO.REQUIRED");
						}

					} else if (!customValidator.checkforOrderNum(subForm.getOrderNocr())) {
						errors.rejectValue("orderNocr", "error.ms", "Order Number contains invalid characters");
					}

					if (subForm.getOrderDatecr() == null) {
						try {
							errors.rejectValue("orderDatecr", "orderDate.required");
						} catch (Exception e) {
							errors.rejectValue("orderDatecr", "orderDate.required");
						}

					} else {
						if (!customValidator.checkforDate(subForm.getOrderDatecr())) {
							errors.rejectValue("orderDatecr", "error.valid.ORDERDATE");
						}

						Date ordDate = subForm.getOrderDatecr();

						if (ordDate.compareTo(today) > 0) {
							errors.rejectValue("orderDatecr", "error.valid.morecurrentDate");
						}
					}

					if (subForm.getOrdereffectiveDatecr() == null) {
						try {
							errors.rejectValue("ordereffectiveDatecr", "ordereffectiveDate.required");
						} catch (Exception e) {
							errors.rejectValue("ordereffectiveDatecr", "ordereffectiveDate.required");
						}

					} else if (!customValidator.checkforDate(subForm.getOrdereffectiveDatecr())) {
						errors.rejectValue("ordereffectiveDatecr", "error.valid.EFFECTIVEDATE");
					}

					if (subForm.getGazPubDatecr() != null) {
						if (!customValidator.checkforDate(subForm.getGazPubDatecr())) // will
																						// only
																						// matches
																						// alphabets
						{
							errors.rejectValue("gazPubDatecr", "error.valid.GAZPUBDATE");
						} else if (subForm.getGazPubDatecr().before(subForm.getOrderDatecr()) && !subForm.getGazPubDatecr().equals(subForm.getOrderDatecr())) {
							errors.rejectValue("gazPubDatecr", "error.INCORECT.GAZPUBDATE");
						}
					}
					
					
					if(fileCount<=0)
					{
					if (govtOrderConfig.equals("govtOrderUpload")) {

						if (subDistrictForm.getAttachFile1().isEmpty()) {
							try {
								errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
							} catch (Exception e) {
								errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
							}

						}
						else if(subDistrictForm.getAttachFile1().get(0).isEmpty())
						{
							errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
						}

					} else if (govtOrderConfig.equals("govtOrderGenerate")) {
						if (subDistrictForm.getTemplateList() != null) {
							int templateCode = Integer.parseInt(subDistrictForm.getTemplateList());
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

	public SubDistrictForm validateChange(Object object, Errors errors) throws Exception {
		SubDistrictForm subDistrictForm = (SubDistrictForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
	if(!subDistrictForm.getListSubdistrictDetails().isEmpty())
	{
		SubdistrictDataForm element=subDistrictForm.getListSubdistrictDetails().get(0);
		String subdistrictNameEngch = element.getSubdistrictNameEnglishch();
		String subdistrictNameEng=element.getSubdistrictNameEnglish();
		
		Integer districtCode=element.getDistrict_code();
		String strNameAlias = element.getAliasEnglishch();
		String strNameLocal = element.getSubdistrictNameLocalch();
		String strNameLocalAlias = element.getAliasLocalch();
		
		if(subdistrictNameEngch=="" || subdistrictNameEngch.isEmpty())
		{
			errors.rejectValue("subdistrictNameEnglishch",
					"Error.blank.subdistrictNameInEn");
			subDistrictForm.setErrorflag(2);
		}
		else if (!customValidator.checkforAlphabetNumbericWithSpaceDotandSlash(subdistrictNameEngch)) {
			errors.rejectValue("subdistrictNameEnglishch","Error.data.SubDistrictNameEng");
		}		else if(subdistrictNameEngch.equalsIgnoreCase(subdistrictNameEng))
		{
			errors.rejectValue("errorflag","error.change.commonAlert");
			subDistrictForm.setErrorflag(1);
		}
		else if(districtCode!=null)
		{
			if(commonService.existEntityName(districtCode, 'T', subdistrictNameEngch))
			{
				errors.rejectValue("subdistrictNameEnglishch","Error.alredyExist.SubDistrictNameEng");
			}
		}
		
		if (!strNameAlias.isEmpty()
				&& !customValidator.checkforAlphabetsandSpace(strNameAlias)) {
			errors.rejectValue("aliasEnglishch","Error.data.SubDistrictAliasNameEng");
		}
		if (!strNameLocal.isEmpty()
	                && !customValidator.validateSpecialCharacters(strNameLocal)) {
				errors.rejectValue("subdistrictNameLocalch","Error.data.SubDistrictNameLocal");
		}
		if (!strNameLocalAlias.isEmpty()
						&& !customValidator
								.validateSpecialCharacters(strNameLocalAlias)) {
					errors.rejectValue("aliasLocalch","Error.data.SubDistrictAliasNameLocal");
				}
	   
		
	
		
	}
	
	return subDistrictForm;
	}

	

	public void validateCensusCode(Object object, Errors errors) {
		SubDistrictForm subDistrictForm = (SubDistrictForm) object;
		if (!subDistrictForm.getCensus2011Code().isEmpty()) {
			String pattern = "^[0-9]+$";
			Pattern r = Pattern.compile(pattern);

			// Now create matcher object.
			Matcher m = r.matcher(subDistrictForm.getCensus2011Code());
			if (!m.matches()) {
				errors.rejectValue("census2011Code", "Error.Census2011Code");
			}
		}
		if (!subDistrictForm.getSscode().isEmpty()) {
			if (subDistrictForm.getSscode().length() > 5) {
				errors.rejectValue("sscode", "Error.Sscode");
			}
		}
	}

	public void validateInvalidateSubDistrict(SubDistrictForm subDistrictForm,
			BindingResult result) {
		if (subDistrictForm != null) {

			if (subDistrictForm.getDistrictNameEnglish() == null
					|| subDistrictForm.getDistrictNameEnglish().isEmpty()
					|| subDistrictForm.getDistrictNameEnglish().toString()
							.equals("0")) {
				result.rejectValue("districtNameEnglish", "error.PSDT");
			}
			if (subDistrictForm.getSubdistrictNameEnglish() == null
					|| subDistrictForm.getSubdistrictNameEnglish().isEmpty()
					|| subDistrictForm.getSubdistrictNameEnglish().toString()
							.equals("0")) {
				result.rejectValue("subdistrictNameEnglish", "error.PSSDT");
			}
			/*Modified by kirandeep for Invalidate Subdistrict on 16/03/2015 */
			if(subDistrictForm.getFlagSubDistrictInvalid() != -1){
				if (!subDistrictForm.isRdoSubdistrictDelete()) {
					if (subDistrictForm.getTargetSubdistrictYes() == null
							|| subDistrictForm.getTargetSubdistrictYes().isEmpty()
							|| subDistrictForm.getTargetSubdistrictYes().toString()
									.equals("0")) {
						result.rejectValue("targetSubdistrictYes", "error.PSSDT");
					}
				}
			}
			
			if (subDistrictForm.isRdoSubdistrictDelete()) {
				/*
				 * if (subDistrictForm.getTargetSubdistrictNo() == null ||
				 * subDistrictForm.getTargetSubdistrictNo().isEmpty() ||
				 * subDistrictForm.getTargetSubdistrictNo().toString()
				 * .equals("0")) { result.rejectValue("targetSubdistrictNo",
				 * "error.PSSDT"); }
				 */
				/*
				 * if (subDistrictForm.getContributedVillages() == null) {
				 * result.rejectValue("contributedVillages", "error.PSVTMWSD");
				 * }
				 */
				if (subDistrictForm.getVillList() != null) {
					result.rejectValue("villList", "error.PSRV");
				}

			}

		}

	}

	
	public void viewValidateSubDistrict(Object object, Errors errors) {
		SubDistrictForm subDistrictForm = (SubDistrictForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		if (subDistrictForm.getSubdistrictNameEnglish() != null || subDistrictForm.getCode() != null) {
			if (subDistrictForm.getSubdistrictNameEnglish() != null
					&& !subDistrictForm.getSubdistrictNameEnglish().equals("")) {
				if (!customValidator.checkforAlphabetsandSpace(subDistrictForm
						.getSubdistrictNameEnglish())) {
					errors.rejectValue("subdistrictNameEnglish", "error.ms",
							"This field contains invalid characters.Please use A-Z or a-z");
				}
			}
			if ((subDistrictForm.getCode() != null && !subDistrictForm
					.getCode().equals(""))) {
				if (!customValidator.checkforOnlyNumeric(subDistrictForm
						.getCode())) {
					errors.rejectValue("code", "error.ms",
							"This field contains invalid characters.Please use 0 to 9");

				}
			}
		}
		else
		{
			if(subDistrictForm.getDistrictNameEnglish() == null || subDistrictForm.getDistrictNameEnglish().isEmpty())
			{
				errors.rejectValue("districtNameEnglish", "error.ms",
						"Please select District");
			}
			else{
				if(subDistrictForm.getDistrictNameEnglish() != null)
				{
					try {
						int code = Integer.parseInt(subDistrictForm.getDistrictNameEnglish());
						if(code <1)
						{
							errors.rejectValue("districtNameEnglish", "error.ms",
									"Please select District");
						}
					} catch (Exception e) {
						errors.rejectValue("districtNameEnglish", "error.ms",
								"Please select District");
					}
				}
			}
		}
	}
}
