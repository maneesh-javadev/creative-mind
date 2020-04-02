package com.cmc.lgd.localbody.rules;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.utils.ApplicationConstant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pes.attachment.util.AddAttachmentBean;

import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.services.LocalBodyService;

public class Errors implements Validator {
	
	private Boolean isGovernmentOrderUpload;
	
	//private Boolean isMapUpload;
	
	
	@Autowired
	private LocalBodyService localBodyService;
	

	private final List<Error> errors = Collections.synchronizedList(new ArrayList<Error>());

	public Collection<Error> getErrors() {
		return Collections.unmodifiableCollection(errors);
	}

	public void addError(Object target, String field, String message) { // NO_UCD (unused code)
		this.errors.add(new Error(target, field, message));
	}

	public boolean hasErrors() { // NO_UCD (use default)
		if (this.errors.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	public void validate(Object target, org.springframework.validation.Errors errors, Boolean isGovernmentOrderUpload) {
		this.isGovernmentOrderUpload = isGovernmentOrderUpload;
		validate(target, errors);
	}
	
	private boolean executeRegEx(String regexPattern, String input){ 
		Pattern pattern 		= Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher 		= pattern.matcher(input);
		return matcher.find();
	}
	
	/**
	 * {@code unSupportedCharactersOrderNo} method validation for special characters,
	 * If any special character exist in string {@code inputValue} return false, true
	 * returns for valid string.
	 * 
	 * @return boolean {true, false}
	 */
	private boolean unSupportedCharactersOrderNo(String inputValue){
		String regexPattern = "[#%&\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\[\\\\\\]\\|\\\\:\\;\\'\\\"\\<\\>\\?\\\\]";
		return executeRegEx(regexPattern, inputValue);
	}
	
	/**
	 * {@code unSupportedCharactersEnglish} method validation for special characters,
	 * If any special character exist in string {@code inputValue} return false, true
	 * returns for valid string.
	 * 
	 * @return boolean {true, false}
	 */
	private boolean unSupportedCharactersEnglish(String inputValue){
		//Allow '(' and ')' brackets in Name of the Localbody (In English) and Name of Localbody (In Local) @Pooja @date 08-10-15
		String regexPattern = "[#%&\\/\\,%&\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\[\\\\\\]\\|\\\\:\\;\\'\\\"\\<\\>\\?\\\\]";
		return executeRegEx(regexPattern, inputValue);
	}
	
	/**
	 * {@code unSupportedCharactersEnglish} method validation for special characters for local languages,
	 * If any special character exist in string {@code inputValue} return false, true
	 * returns for valid string.
	 * 
	 * @return boolean {true, false}
	 */
	private boolean unSupportedCharactersLocal(String inputValue){
		//Allow '(' and ')' brackets in Name of the Localbody (In English) and Name of Localbody (In Local) @Pooja @date 08-10-15
		String regexPattern = "[#%&\\/\\,%&\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\[\\\\\\]\\|\\\\:\\;\\'\\\"\\<\\>\\?\\\\]";
		return executeRegEx(regexPattern, inputValue);
	}
	
	/**
	 * The {@code alphaNumeric} used to validate input only contains alphabets and numeric values.
	 * @param inputValue
	 * @return
	 */
	private boolean alphaNumeric(String inputValue){
		String regexPattern = "^[a-zA-Z0-9]*$";
		return !executeRegEx(regexPattern, inputValue);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return LocalBodyForm.class.equals(clazz);
	}
	
	private static boolean betweenExclusive(Double x, Double min, Double max) {
	    return x >= min && x <= max;    
	}

	/**
	 * 
	 * @param lbForm
	 * @param errors
	 */
	private void localBodyCommonFields(LocalBodyForm lbForm, org.springframework.validation.Errors errors){
		
		String nameEnglish = lbForm.getLocalBodyNameEnglish();
		if (StringUtils.isBlank(nameEnglish)) {
			errors.rejectValue("localBodyNameEnglish", "error.blank.localBodyNameInEn");
		}
		if (unSupportedCharactersEnglish(nameEnglish)) {
			errors.rejectValue("localBodyNameEnglish", "Error.invalidchar");
		}
		if (nameEnglish.length() > 200) {
			errors.rejectValue("localBodyNameEnglish", "Error.Sizevalid", new String[] {"200"}, null);
		}
				
		String lbNameLocal = lbForm.getLocalBodyNameLocal();
		if(StringUtils.isNotBlank(lbNameLocal)){
			if(unSupportedCharactersLocal(lbNameLocal)){
				errors.rejectValue("localBodyNameLocal", "Error.localbodylocal");
			}
			if(lbNameLocal.length() > 100){
				errors.rejectValue("localBodyNameLocal", "Error.Sizevalid", new String[] {"100"}, null);
			}
		}
		String lbNameAliasEnglish = lbForm.getLocalBodyAliasEnglish();
		if(StringUtils.isNotBlank(lbNameAliasEnglish)){
			if(unSupportedCharactersEnglish(lbNameAliasEnglish)){
				errors.rejectValue("localBodyAliasEnglish", "Error.invalidchar");
			}
			if(lbNameAliasEnglish.length() > 50){
				errors.rejectValue("localBodyAliasEnglish", "Error.Sizevalid",new String[] {"50"}, null);
			}
		}
		String lbNameAliasLocal = lbForm.getLocalBodyAliasLocal();
		if(StringUtils.isNotBlank(lbNameAliasLocal)){
			if(unSupportedCharactersLocal(lbNameAliasLocal)){
				errors.rejectValue("localBodyAliasLocal", "Error.invalidchar");
			}
			if(lbNameAliasEnglish.length() > 50){
				errors.rejectValue("localBodyAliasLocal", "Error.Sizevalid",new String[] {"50"}, null);
			}
		}
		String stateSpecificCode = lbForm.getStateSpecificCode();
		if(StringUtils.isNotBlank(stateSpecificCode)){
			if(alphaNumeric(stateSpecificCode)){
				errors.rejectValue("stateSpecificCode", "Error.numberandalphabet");
			}
			if(stateSpecificCode.length() > 7){
				errors.rejectValue("stateSpecificCode", "Error.Sizevalid", new String[] {"7"}, null);
			}
		}
	}
	
	/**
	 * 
	 * @param lbForm
	 * @param errors
	 */
	private void coordinatesFields(LocalBodyForm lbForm, org.springframework.validation.Errors errors){
		String latitude = lbForm.getLatitude() == null ? "" : lbForm.getLatitude();
		String longitude = lbForm.getLongitude() == null ? "" : lbForm.getLongitude();
		if(!"".equals(longitude) || !"".equals(latitude)){
			String[] longitudeArray = longitude.trim().split(",");
			String[] latitudeArray = latitude.trim().split(",");
			if(longitudeArray.length == latitudeArray.length){
				for(int i = 0; i < longitudeArray.length; i++){
					String lon = longitudeArray[i];
					String lat = latitudeArray[i];
					try{
						if(!betweenExclusive(Double.parseDouble(lon), 32d, 98d)){
							errors.rejectValue("longitude", "Error.longituderng", new String[] {"32, 98"}, null);
						}
						if(!betweenExclusive(Double.parseDouble(lat), 6d, 38d)){
							errors.rejectValue("latitude", "Error.latituderng", new String[] {"6, 38"}, null);
						}
					}catch(java.lang.NumberFormatException e){
						errors.rejectValue("longitude", "Error.longitudechk");
						errors.rejectValue("latitude", "Error.latitudechk");
					}
				}
			}else {
				errors.rejectValue("longitude", "Error.lengthmismatch");
			}
		}
	}
	
	/**
	 * 
	 * @param lbForm
	 * @param errors
	 */
	private void governmentOrderFields(LocalBodyForm lbForm, org.springframework.validation.Errors errors){
		if(lbForm.getOrderCode() == null){
			if(StringUtils.isBlank(lbForm.getOrderNo())){
				errors.rejectValue("orderNo", "ORDERNUMBER.REQUIRED");
			}
			if(unSupportedCharactersOrderNo(lbForm.getOrderNo())){
				errors.rejectValue("orderNo", "Error.invalidegovordrno");
			}
			if(lbForm.getOrderDate() == null){
				errors.rejectValue("orderDate", "error.required.ORDERDATE");
			} else {
				if(lbForm.getOrderDate().after(new Date())){
					errors.rejectValue("orderDate", "error.valid.morecurrentDate");
				}
			}
			if(lbForm.getEffectiveDate() == null){
				errors.rejectValue("effectiveDate", "error.required.EFFECTIVEDATE");
			} else {
				if(lbForm.getEffectiveDate().after(new Date())){
					errors.rejectValue("effectiveDate", "error.valid.EFFECTIVEFUTUREDATE");
				}
				if(lbForm.getiParamEffectiveDate() != null && lbForm.getEffectiveDate().before(lbForm.getiParamEffectiveDate())){
					errors.rejectValue("effectiveDate", "error.valid.EFFECTIVEPREVIOUSVERSION");
				}
			}
			if(lbForm.getOrderDate() != null && lbForm.getEffectiveDate() != null){
				if(lbForm.getOrderDate().after(lbForm.getEffectiveDate())){
					errors.rejectValue("orderDate", "error.compare.ORDERDATE");
					errors.rejectValue("effectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
			}
			if( isGovernmentOrderUpload ){
				
					if(lbForm.getGazettePublicationUpload() != null && !lbForm.getGazettePublicationUpload().isEmpty()){
						if( lbForm.getId() == null ){
							CommonsMultipartFile f = lbForm.getGazettePublicationUpload().get(0);
							if(f.isEmpty()){
								errors.rejectValue("gazettePublicationUpload", "error.upload.GOVTORDER");
							}
						}
						try {
							String validateAttachmentMessage = invalidAttchment(lbForm.getGazettePublicationUpload(), 
									  						  					Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString()));
							if(! LocalBodyConstant.SUCCESS_MESSAGE.toString().equals(validateAttachmentMessage)){
								errors.rejectValue("gazettePublicationUpload", validateAttachmentMessage);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							errors.rejectValue("gazettePublicationUpload", "Error.invalidgofile");
						}
						
					}
				
				if(lbForm.getGazPubDate() != null){
					if(lbForm.getOrderDate().after(lbForm.getGazPubDate())){
						errors.rejectValue("gazPubDate", "error.compare.GuzpubDate");
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param lbForm
	 * @param errors
	 */
	private void validateMapUpLoad(LocalBodyForm lbForm, org.springframework.validation.Errors errors){
		if(lbForm.getMapUpload() != null && !lbForm.getMapUpload().isEmpty()) {
			CommonsMultipartFile f = lbForm.getMapUpload().get(0);
			if(!f.isEmpty()){
				try {
					String validateAttachmentMessage = invalidAttchment(lbForm.getMapUpload(), 
			  					Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_MAP.toString()));
					if(! LocalBodyConstant.SUCCESS_MESSAGE.toString().equals(validateAttachmentMessage)){
						errors.rejectValue("mapUpload", validateAttachmentMessage);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errors.rejectValue("mapUpload", "Error.invalidmapfile");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param lbForm
	 * @param errors
	 * @param parentCode
	 */
	private void validateDuplicateName(LocalBodyForm lbForm,  org.springframework.validation.Errors errors, Integer parentCode){
		if(!errors.hasErrors()){
			String status = localBodyService.checkLocalBodyNameExist(lbForm.getLocalBodyNameEnglish(), lbForm.getLocalBodyTypeCode(), parentCode, lbForm.getStateCode(), lbForm.getId());
			if(!LocalBodyConstant.SUCCESS_MESSAGE.toString().equalsIgnoreCase(status)){
				errors.rejectValue("localBodyNameEnglish", status);
			}
		}
	}
	
	/**
	 * 
	 * @param lbForm
	 * @param errors
	 */
	private void mapUnmappedCoverageFields(LocalBodyForm lbForm,  org.springframework.validation.Errors errors) {
		if(lbForm.getIsResetedCoverage() == null || lbForm.getIsResetedCoverage()) {
			if(!lbForm.getCheckboxCoverageLB() && !lbForm.getCheckboxCoverageUnmapped()){
				errors.rejectValue("checkboxCoverageLB", "Error.existinglbcoverarea");
				errors.rejectValue("checkboxCoverageUnmapped", "Error.landrgncoverarea");
			}
			
			if(lbForm.getCheckboxCoverageLB()){
				if(StringUtils.isBlank(lbForm.getContributingLBCodes())){
					errors.rejectValue("contributingLBCodes", "", "Error.contributingpanchayat");
				}
				
				if(StringUtils.contains(lbForm.getContributingLBCodes(), "PART")){
					if(LocalBodyConstant.LB_URBAN.toString().equals(lbForm.getLocalBodyCreationType())){
						if(ApplicationConstant.checkStateLBOnlyDisttWise(lbForm.getStateCode())){
							if(StringUtils.isBlank(lbForm.getContributingLBDistrictCodes())){
								errors.rejectValue("contributingLBDistrictCodes", "Error.dialog-error5");
							}
						} else {
							if(StringUtils.isBlank(lbForm.getContributingLBSubDistrictCodes())){
								errors.rejectValue("contributingLBSubDistrictCodes", "Error.dialog-error6");
							}
						}
					} else {
						String lbTypePanchayat = lbForm.getLocalBodyTypePanchayat();
						if( StringUtils.isNotBlank(lbTypePanchayat)){
					    	String lbTypeLevel = lbTypePanchayat.split("\\_")[3];
					    	if(LocalBodyConstant.DISTRICT_PANCHAYAT_LEVEL.toString().equals(lbTypeLevel)){
					    		if(StringUtils.isBlank(lbForm.getContributingLBDistrictCodes())){
					    			errors.rejectValue("contributingLBDistrictCodes", "Error.dialog-error5");
					    		}
					    	} else if(LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString().equals(lbTypeLevel)){
					    		if(StringUtils.isBlank(lbForm.getContributingLBSubDistrictCodes())){
					    			errors.rejectValue("contributingLBSubDistrictCodes", "Error.dialog-error6");
					    		}
					    	} else if(LocalBodyConstant.VILLAGE_PANCHAYAT_LEVEL.toString().equals(lbTypeLevel)){
					    		if(StringUtils.isBlank(lbForm.getContributingLBVillageCodes())){
					    			errors.rejectValue("contributingLBVillageCodes", "Error.dialog-error8");
					    		}
					    	}
					    }
					}	
				}
			}
			if(lbForm.getCheckboxCoverageUnmapped()){
				if(LocalBodyConstant.LB_URBAN.toString().equals(lbForm.getLocalBodyCreationType())){
					if(ApplicationConstant.checkStateLBOnlyDisttWise(lbForm.getStateCode())){
						if(StringUtils.isBlank(lbForm.getContributingUnmappedDistrictCodes())){
							errors.rejectValue("contributingUnmappedDistrictCodes", "Error.contributingunmapdistrict");
						}
					}else{
						if(!LocalBodyConstant.LB_URBAN.toString().equals(lbForm.getLocalBodyCreationType())){
							if(StringUtils.isBlank(lbForm.getContributingUnmappedSubDistrictCodes())){
								errors.rejectValue("contributingUnmappedSubDistrictCodes", "Error.contributingunmapsubdistrict");
							}
						}
						
					}
				} else {
					String lbTypePanchayat = lbForm.getLocalBodyTypePanchayat();
				    if( StringUtils.isNotBlank(lbTypePanchayat)){
				    	String lbTypeLevel = lbTypePanchayat.split("\\_")[3];
				    	if(LocalBodyConstant.DISTRICT_PANCHAYAT_LEVEL.toString().equals(lbTypeLevel)){
				    		if(StringUtils.isBlank(lbForm.getContributingUnmappedDistrictCodes())){
				    			errors.rejectValue("contributingUnmappedDistrictCodes", "Error.contributingunmapdistrict");
				    		}
				    	} else if(LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString().equals(lbTypeLevel)){
				    		if(StringUtils.isBlank(lbForm.getContributingUnmappedSubDistrictCodes())){
				    			errors.rejectValue("contributingUnmappedSubDistrictCodes", "Error.contributingunmapsubdistrict");
				    		}
				    	} else if(LocalBodyConstant.VILLAGE_PANCHAYAT_LEVEL.toString().equals(lbTypeLevel)){
				    		if(StringUtils.isBlank(lbForm.getContributingUnmappedVillageCodes())){
				    			errors.rejectValue("contributingUnmappedVillageCodes", "Error.contributingunmapvillage");
				    		}
				    	}
				    }
				}
			}
			if(! LocalBodyConstant.LB_URBAN.toString().equals(lbForm.getLocalBodyCreationType())){
				if(StringUtils.isBlank(lbForm.getHeadQuarterCode())){
					errors.rejectValue("headQuarterCode", "Error.headquarter");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param lbForm
	 * @param errors
	 */
	private void validationAllLBFields(LocalBodyForm lbForm,  org.springframework.validation.Errors errors){
		localBodyCommonFields(lbForm, errors);
		Integer parentCode = 0;
		if(LocalBodyConstant.LB_URBAN.toString().equals(lbForm.getLocalBodyCreationType())){
			if(lbForm.getLocalBodyTypeCode() == null){
				errors.rejectValue("localBodyTypeCode", "error.select.LBTYPE");
			}
		}else  {
			if(StringUtils.isBlank(lbForm.getLbTypeHierarchy())){
				errors.rejectValue("lbTypeHierarchy", "Error.heirarchyselect", "Please Select atleast Hierarchy.");
			}
			if(StringUtils.isBlank(lbForm.getLocalBodyTypePanchayat())){
				errors.rejectValue("localBodyTypePanchayat", "error.select.LBTYPE");
			}
			parentCode = localBodyService.getParentLBCode(lbForm.getLocalBodyLevelCodes());
		}
		mapUnmappedCoverageFields(lbForm, errors);
		coordinatesFields(lbForm, errors);
		governmentOrderFields(lbForm, errors);
		validateDuplicateName(lbForm, errors, parentCode);
		validateMapUpLoad(lbForm, errors);
	}
	
	
	/**
	 * 
	 * @param lbForm
	 * @param errors
	 */
	private void mapUnmappedChangeMappedAreaFields(LocalBodyForm lbForm,  org.springframework.validation.Errors errors) {	
		/*if(LocalBodyConstant.LB_URBAN.toString().equals(lbForm.getLocalBodyCreationType())){
			if(ApplicationConstant.checkStateLBOnlyDisttWise(lbForm.getStateCode())){
				if(StringUtils.isBlank(lbForm.getContributingUnmappedDistrictCodes())){
					errors.rejectValue("contributingUnmappedDistrictCodes", "Error.contributingunmapdistrict");
				}
			}else{
				if(StringUtils.isBlank(lbForm.getContributingUnmappedSubDistrictCodes())){
					errors.rejectValue("contributingUnmappedSubDistrictCodes", "Error.contributingunmapsubdistrict");
				}
			}
		} else {
			String lbTypePanchayat = lbForm.getLocalBodyTypePanchayat();
		    if( StringUtils.isNotBlank(lbTypePanchayat)){
		    	if(LocalBodyConstant.DISTRICT_PANCHAYAT_LEVEL.toString().equals(lbTypePanchayat)){
		    		if(StringUtils.isBlank(lbForm.getContributingUnmappedDistrictCodes())){
		    			errors.rejectValue("contributingUnmappedDistrictCodes", "Error.contributingunmapdistrict");
		    		}
		    	} else if(LocalBodyConstant.INTERMEDIATE_PANCHAYAT_LEVEL.toString().equals(lbTypePanchayat)){
		    		if(StringUtils.isBlank(lbForm.getContributingUnmappedSubDistrictCodes())){
		    			errors.rejectValue("contributingUnmappedSubDistrictCodes", "Error.contributingunmapsubdistrict");
		    		}
		    	} else if(LocalBodyConstant.VILLAGE_PANCHAYAT_LEVEL.toString().equals(lbTypePanchayat)){
		    		if(StringUtils.isBlank(lbForm.getContributingUnmappedVillageCodes())){
		    			errors.rejectValue("contributingUnmappedVillageCodes", "Error.contributingunmapvillage");
		    		}
		    	}
		    }
		}*/
		if(!LocalBodyConstant.LB_URBAN.toString().equals(lbForm.getLocalBodyCreationType())){
			if(StringUtils.isBlank(lbForm.getHeadQuarterCode())){
				errors.rejectValue("headQuarterCode", "Error.headquarter");
			}
		}
	}
		
	@Override
	public void validate(Object target, org.springframework.validation.Errors errors) {
		// TODO Auto-generated method stub
		LocalBodyForm lbForm = (LocalBodyForm) target;
		if(LocalBodyConstant.PROCESS_MODIFY_NAME.toString().equals(lbForm.getValidationAction())){
			localBodyCommonFields(lbForm, errors);
			governmentOrderFields(lbForm, errors);
			validateDuplicateName(lbForm, errors, lbForm.getParentLocalBodyCode());
		} else if(LocalBodyConstant.PROCESS_MODIFY_PARENT.toString().equals(lbForm.getValidationAction())){
			if(lbForm.getParentLocalBodyCode() == null){
				errors.rejectValue("localBodyTypeCode","Label.PARENTOFLOCALBODY", "Field is required.");
			}
			governmentOrderFields(lbForm, errors);
		} else if(LocalBodyConstant.PROCESS_MODIFY_TYPE.toString().equals(lbForm.getValidationAction())){
			localBodyCommonFields(lbForm, errors);
			if(lbForm.getLocalBodyTypeCode() == null){
				errors.rejectValue("localBodyTypeCode", "error.select.LBTYPE");
			}
			governmentOrderFields(lbForm, errors);
			validateDuplicateName(lbForm, errors, 0);// 0 parent code for urban local body
		} else if(LocalBodyConstant.PROCESS_MODIFY_GOV_ORDER.toString().equals(lbForm.getValidationAction())){
			governmentOrderFields(lbForm, errors);
			coordinatesFields(lbForm, errors);
		} else if (LocalBodyConstant.PROCESS_CHANGE_COVERAGE.toString().equals(lbForm.getValidationAction())) {
			if(lbForm.getRemovedLangRegionCodes() == null && !(lbForm.getCheckboxCoverageLB() && lbForm.getCheckboxCoverageUnmapped())) {
				mapUnmappedCoverageFields(lbForm, errors);
			}
			governmentOrderFields(lbForm, errors);
		} else if (LocalBodyConstant.PROCESS_MAP_COVERAGE.toString().equals(lbForm.getValidationAction())){
			mapUnmappedChangeMappedAreaFields(lbForm, errors);
		} else{
			validationAllLBFields(lbForm, errors);
		}
	}

	/**
	 * 
	 * @param attachmentList
	 * @param fileMasterId
	 * @return
	 * @throws Exception
	 */
	private String invalidAttchment(List<CommonsMultipartFile> attachmentList, Long fileMasterId) throws Exception{
		AttachmentMaster master = localBodyService.getUploadFileConfigurationDetails(fileMasterId);
		AddAttachmentBean attachmentBean = new AddAttachmentBean();
		attachmentBean.setUploadLocation(master.getFileLocation());
		attachmentBean.setCurrentlyUploadFileList(attachmentList);
		attachmentBean.setAllowedTotalNoOfAttachment(master.getFileLimit());// 4.Allowed Total Number Of Attachment.
		attachmentBean.setAllowedTotalFileSize(master.getTotalFileSize());// 5.Allowed
		attachmentBean.setAllowedIndividualFileSize(master.getIndividualFileSize());// 6.Allowed Individual File Size.
		attachmentBean.setNoOFAlreadyAttachedFiles(0);// 7.Number Of Already Attached File.
		attachmentBean.setTotalSizeOFAlreadyAttachedFiles(0l);// 8.Already attached file total size.
		attachmentBean.setMimeTypeList(localBodyService.fetchMimeType());// 9.File Mime Type List.
		attachmentBean.setNoOFMandatoryFile(0);// 13.Number of Mandatory file.
		
		SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
		String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
		if (!"validationSuccessFullyDone".equals(validateAttachment)) {
			return validateAttachment;
		}
		return LocalBodyConstant.SUCCESS_MESSAGE.toString();
	}
}

class Error {
	
	private final Object target;
	private final String field;
	private final String message;

	public Error(Object target, String field, String message) {
		super();
		this.target = target;
		this.field = field;
		this.message = message;
	}

	public Object getTarget() {
		return target;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
	
	
	
	

	

}
