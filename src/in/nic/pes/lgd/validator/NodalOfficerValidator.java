package in.nic.pes.lgd.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.StateService;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;

public class NodalOfficerValidator implements Validator {
	
	@Autowired
	private GovernmentOrderService govtOrderService;
	
	private CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
	
	@Autowired
	private StateService stateService;

	@Override
	public boolean supports(Class<?> arg0) {
	return false;
	}
	
	 private Pattern pattern;  
	 private Matcher matcher;
	 private String MOBILE_PATTERN = "[1-9][0-9]{9}";
	 private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; 

	@Override
	public void validate(Object target, Errors errors) {
		NodalOfficerState nodalOfficerState=(NodalOfficerState)target;
		validateNameEnglish(nodalOfficerState,errors);
		validateDegination(nodalOfficerState,errors);
		validateMobileNumber(nodalOfficerState,errors);
		validateEmailId(nodalOfficerState,errors);
	}
	
	private void validateNameEnglish(NodalOfficerState nodalOfficerState, Errors errors){
		String officerName=nodalOfficerState.getNodalOfficerName();
		if (StringUtils.isBlank(officerName)) {	
				errors.rejectValue("nodalOfficerName","error.ms", "Please enter Nodal Officer Name");
		}else if (!customValidator.checkforAlphabetNumbericWithSpaceDotandSlash(officerName)){
			errors.rejectValue("nodalOfficerName","error.ms", "Invalid character(s) are not allowed.");
		}else if(validateLength(officerName,200)){
			errors.rejectValue("nodalOfficerName","error.ms", "Length must be less then 200");
		}
	}
	
	private void validateDegination(NodalOfficerState nodalOfficerState, Errors errors){
		String degination=nodalOfficerState.getDesignation();
		if (StringUtils.isBlank(degination)) {	
				errors.rejectValue("designation","error.ms", "Please enter Nodal Officer designation");
		}else if (!customValidator.checkforAlphabetNumbericWithSpaceDotandSlash(degination)){
			errors.rejectValue("designation","error.ms", "Invalid character(s) are not allowed.");
		}else if(validateLength(degination,200)){
			errors.rejectValue("designation","error.ms", "Length must be less then 200");
		}
	}
	
	private void validateMobileNumber(NodalOfficerState nodalOfficerState, Errors errors){
		String mobileNo=nodalOfficerState.getMobileNo();
		if (StringUtils.isBlank(mobileNo)) {	
				errors.rejectValue("mobileNo","error.ms", "Please enter nodal officer mobile number.");
		}else {
			 pattern = Pattern.compile(MOBILE_PATTERN);  
			 matcher = pattern.matcher(mobileNo);  
			 if (!matcher.matches()) { 
				 errors.rejectValue("mobileNo","error.ms", "Mobile number not valid.");
			}else if(validateLength(mobileNo,10)){
				errors.rejectValue("mobileNo","error.ms", "Length must be less then 10");
			}
		}
	}
	
	private void validateEmailId(NodalOfficerState nodalOfficerState, Errors errors){
		String emailId=nodalOfficerState.getEmailId();
		if (StringUtils.isBlank(emailId)) {	
				errors.rejectValue("emailId","error.ms", "Please enter Nodal officer email id.");
		}else {
			 pattern = Pattern.compile(EMAIL_PATTERN);  
			 matcher = pattern.matcher(emailId);  
			 if (!matcher.matches()) { 
				 errors.rejectValue("emailId","error.ms", "Email-Id not valid.");
			}else if(validateLength(emailId,100)){
				errors.rejectValue("emailId","error.ms", "Length must be less then 100");
			}
		}
	}
	
	
	public void isValidMime(Errors errors, HttpServletRequest request, List<CommonsMultipartFile> file) {
		boolean isFileUpload=true;
		try {
			AddAttachmentBean addAttachmentBean2 = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			List<CommonsMultipartFile> attachedFile = file;
			// Tanuj changed
			if (attachedFile != null) {
				if (attachedFile.get(0).getSize() > 0) {
					addAttachmentBean2 = govtOrderService.getAttachmentBeanforMap(attachedFile, null, request);

					if (addAttachmentBean2 != null) {
					String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
					attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
						if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) {
						request.setAttribute("validationErrorOne1", validateAttachment1);
							errors.rejectValue("fileUpload","error.ms", validateAttachment1);
					}
					} else {
					attachmentHandler.deleteMetaDataINFileSystem(addAttachmentBean2);
				}	
			 }else{
				 isFileUpload=false;
			 }
			}else{
				 isFileUpload=false;
			}
			
			/*if(!isFileUpload){
				errors.rejectValue("fileUpload","error.ms", "Kindly Attach Attachment Document");
			}*/
			
		} catch (Exception e) {
			errors.rejectValue("fileUpload","error.ms", "errorMessage.addAttachment.allowedFileMimeType");
		}
	}
	
	private boolean validateLength(String value,Integer length){
		return (!(value.length()<=length));
	}
	
	public void validateTokenError(Errors errors,String userotp,Long userId){
		Character tokenFlag=null;
		if(userotp!=null){
			
			if(userotp.length()<6){
				errors.rejectValue("userOTP","error.ms", "OTP is must be 6 length");
			}else if (!customValidator.checkforOnlyNumeric(userotp)){
				errors.rejectValue("userOTP","error.ms", "OTP value must be numberic");
			}
			else{
				try{
					tokenFlag=stateService.validateOTP(userotp, userId);
					}catch(Exception e){
						
					}
				if(tokenFlag!=null){
					if(tokenFlag=='W') {
						errors.rejectValue("userOTP","error.ms", "Entered OTP "+userotp+" is Wrong");
					}
					else if(tokenFlag=='E'){
						errors.rejectValue("userOTP","error.ms", "Enter OTP "+userotp+" is Expired");
					}
				}else{
					errors.rejectValue("userOTP","error.ms", "First click of OTP Genrate button");
				}
					
			}
		}else{
			errors.rejectValue("userOTP","error.ms", "OTP is required");
		}
		
		
		
	}
	

}
