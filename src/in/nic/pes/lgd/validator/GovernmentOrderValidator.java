package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import pes.attachment.util.AddAttachmentBean;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.services.LocalBodyService;
/**
 * Modified by Sushil Shakya on 09-10-2014
 */
@Component("govtOrderValidator")
public class GovernmentOrderValidator extends AbstractValidator implements Validator {
	
	@Autowired
	private LocalBodyService localBodyService;
	
	public boolean supports(Class<?> clazz) {
		return GovernmentOrderForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		GovernmentOrderForm govtForm = (GovernmentOrderForm) object;
		String govtOrderConfig = govtForm.getGovtOrderConfig();
		Date today = new Date();
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();

		if (govtForm.getOrderNo().isEmpty()) {
			try {
				errors.rejectValue("orderNo", "Msg.ORDERNO");
			} catch (Exception e) {
				errors.rejectValue("orderNo", "Msg.ORDERNO");
			}

		} else if (!customValidator.checkforOrderNum(govtForm.getOrderNo())) {
			errors.rejectValue("orderNo", "error.valid.ORDERNO");
		}

		if (govtForm.getOrderDate() == null) {
			try {
				errors.rejectValue("orderDate2", "Msg.ORDERDATE");
			} catch (Exception e) {
				errors.rejectValue("orderDate2", "Msg.ORDERDATE");
			}
		} else if (govtForm.getOrderDate() != null) {
			if (!customValidator.checkforDate(govtForm.getOrderDate())) // will
																		// only
																		// matches
																		// alphabets
			{
				errors.rejectValue("orderDate1", "error.valid.ORDERDATE");
			} else if (govtForm.getOrderDate().after(today) && !govtForm.getOrderDate().equals(today)) {
				errors.rejectValue("orderDate1", "error.INCORECT.ORDERDATE");
			}
		}

		if (govtForm.getEffectiveDate() == null) {
			try {
				errors.rejectValue("effectiveDate2", "Msg.EFFECTIVEDATE");
			} catch (Exception e) {
				errors.rejectValue("effectiveDate2", "Msg.EFFECTIVEDATE");
			}

		} else if (govtForm.getEffectiveDate() != null) {
			if (!customValidator.checkforDate(govtForm.getEffectiveDate())) // will
																			// only
																			// matches
																			// alphabets
			{
				errors.rejectValue("effectiveDate1", "error.valid.EFFECTIVEDATE");
			}

			if (govtForm.getEffectiveDate().after(new Date())) {
				errors.rejectValue("effectiveDate1", "error.valid.EFFECTIVEFUTUREDATE");
			} else if (govtForm.getEffectiveDate().before(govtForm.getOrderDate()) && !govtForm.getEffectiveDate().equals(govtForm.getOrderDate())) {
				errors.rejectValue("effectiveDate1", "error.INCORECT.EFFECTIVEDATE");
			}

		}

		if (govtOrderConfig.equals("govtOrderUpload")) {

			if (govtForm.getGazPubDate() != null) {
				if (!customValidator.checkforDate(govtForm.getGazPubDate())) // will
																				// only
																				// matches
																				// alphabets
				{
					errors.rejectValue("gazPubDate2", "error.valid.GAZPUBDATE");
				} else if (govtForm.getGazPubDate().before(govtForm.getOrderDate()) && !govtForm.getGazPubDate().equals(govtForm.getOrderDate())) {
					errors.rejectValue("gazPubDate2", "error.INCORECT.GAZPUBDATE");
				}
			}

			if (govtForm.getAttachFile1().isEmpty()) {
				try {
					errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
				} catch (Exception e) {
					errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
				}

			}

			else if (govtForm.getAttachFile1().size() > 0 && govtForm.getAttachFile1().get(0).getOriginalFilename() == null || govtForm.getAttachFile1().get(0).getOriginalFilename().equals("")) {
				errors.rejectValue("attachFile1", "Msg.UPLOADGOVTORDER");
			}
		} else if (govtOrderConfig.equals("govtOrderGenerate")) {

			if (govtForm.getTemplateList() != null) {
				int templateCode = Integer.parseInt(govtForm.getTemplateList());
				if (templateCode < 1) {
					errors.rejectValue("templateList", "Msg.GovtOrderGenerate");
				}
			}
		}

	}
	
	
	
	public void validateGovermentnotMandatory(Object object, Errors errors) {
		LocalGovtBodyForm govtForm = (LocalGovtBodyForm) object;
		governmentOrderFields(govtForm,errors);
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
	
	
	
	
	private void governmentOrderFields(LocalGovtBodyForm lbForm, org.springframework.validation.Errors errors){
		
		if(!(lbForm.getOrderNo()==null || lbForm.getEffectiveDate()==null || lbForm.getOrderDate()==null || lbForm.getGazettePublicationUpload() == null || lbForm.getGazettePublicationUpload() != null && lbForm.getGazettePublicationUpload().isEmpty()) )
		{
		if(lbForm.getOrderCode() == null){
			if(lbForm.getOrderNo()!=null && unSupportedCharactersOrderNo(lbForm.getOrderNo())){
					errors.rejectValue("orderNo", "Error.invalidegovordrno");
				}
				
				if(lbForm.getOrderDate() != null && lbForm.getOrderDate().after(new Date())){
						errors.rejectValue("orderDate", "error.valid.morecurrentDate");
				}
				
				 if(lbForm.getEffectiveDate() != null) {
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
				if( true){
					
						if(lbForm.getGazettePublicationUpload() != null && !lbForm.getGazettePublicationUpload().isEmpty()){
							
								CommonsMultipartFile f = lbForm.getGazettePublicationUpload().get(0);
								if(f.isEmpty()){
									errors.rejectValue("gazettePublicationUpload", "error.upload.GOVTORDER");
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
			}else{
				if(lbForm.getOrderNo()!=null && StringUtils.isBlank(lbForm.getOrderNo())){
					errors.rejectValue("orderNo", "ORDERNUMBER.REQUIRED");
				}
				if(lbForm.getOrderNo()!=null &&  unSupportedCharactersOrderNo(lbForm.getOrderNo())){
					errors.rejectValue("orderNo", "Error.invalidegovordrno");
				}
				
					if(lbForm.getOrderDate() != null && lbForm.getOrderDate().after(new Date())){
						errors.rejectValue("orderDate", "error.valid.morecurrentDate");
				
				if(lbForm.getEffectiveDate() != null){
				 
					if( lbForm.getEffectiveDate().after(new Date())){
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
				if( true ){
					
						if(lbForm.getGazettePublicationUpload() != null && !lbForm.getGazettePublicationUpload().isEmpty()){
							
								CommonsMultipartFile f = lbForm.getGazettePublicationUpload().get(0);
								if(f.isEmpty()){
									errors.rejectValue("gazettePublicationUpload", "error.upload.GOVTORDER");
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
