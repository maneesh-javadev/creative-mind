package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.LocalBodyDetails;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("cValidatorModify")
public class CustomValidatorLBModify implements Validator  {
	
	private static final Logger log = Logger.getLogger(CustomValidatorLBModify.class);
	
	
	
	@Autowired
	private LocalGovtBodyDAO localGovtBodyDAO;
	
	@Autowired
	private AbstractValidator abstractValidator;
	
	@Override
	public boolean supports(Class<?> clas) {

		return LocalGovtBodyForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

// TODO Remove unused code found by UCDetector
// 	public void modifyValidation(Object target, Errors errors) throws Exception {
// 		try {
// 			LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
// 			CustomRegexValidator customRegValidator = CustomRegexValidator
// 					.getInstance();
// 
// 			if (!lbForm.getlatitude().isEmpty()) {
// 				if (!customRegValidator.checkforLatiandLongi(lbForm.getlatitude())) {
// 					errors.rejectValue("latitude","error.valid.COMMONLONGANDLATVALUE",
// 							"Latitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
// 				}
// 			}
// 			if (!lbForm.getlongitude().isEmpty()) {
// 				if (!customRegValidator.checkforLatiandLongi(lbForm.getlongitude())) {
// 					errors.rejectValue("longitude","error.valid.COMMONLONGANDLATVALUE",
// 							"Longitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
// 				}
// 			}
// 
// 			if (lbForm.isLgd_LBchkModifyName()) {
// 				List<LocalBodyDetails> lbDetails = lbForm.getLocalBodyDetails();
// 				for (LocalBodyDetails lBDetails : lbDetails) {
// 					if (!customRegValidator.checkforAlphabetsandSpace(lBDetails.getLocalBodyNameEnglish())) {
// 						errors.rejectValue("localBodyNameEnglish","error.valid.COMMONALPHABETREQUIRED",
// 								"LocalBody Name Local contains Alphabets only");
// 					}
// 					if (!lBDetails.getLocalBodyNameLocal().isEmpty()) {
// 						if (!customRegValidator.checkforAlphabetsandSpace(lBDetails.getLocalBodyNameLocal())) {
// 							errors.rejectValue("localBodyNameLocal","error.valid.COMMONALPHABETREQUIRED","LocalBody Name Local contains Alphabets only");
// 						}
// 					}
// 
// 					if (!lBDetails.getAliasNameEnglish().isEmpty()) {
// 						if (!customRegValidator.checkforAlphabetsandSpace(lBDetails.getAliasNameEnglish())) {
// 							errors.rejectValue("aliasNameEnglish","error.valid.COMMONALPHABETREQUIRED","LocalBody Name Local contains Alphabets only");
// 						}
// 					}
// 					if (!lBDetails.getAlisNameLocal().isEmpty()) {
// 						if (!customRegValidator.checkforAlphabetsandSpace(lBDetails.getAlisNameLocal())) {
// 							errors.rejectValue("alisNameLocal","error.valid.COMMONALPHABETREQUIRED","LocalBody Name Local contains Alphabets only");
// 						}
// 					}
// 
// 				}
// 			}
// 	
// 			if (!lbForm.isLgd_LBchkModifyName()	&& !lbForm.isLgd_LBchkModifyType()	&& !lbForm.isLgd_LBchkModifyParent()) {
// 				errors.rejectValue("lgd_LBchkModifyName", " s",	"Please Select at least one checBox.");
// 				errors.rejectValue("lgd_LBchkModifyType", "d","Please Select at least one checBox.");
// 				errors.rejectValue("lgd_LBchkModifyParent", "d","Please Select at least one checBox.");
// 			}
// 			List<LocalBodyDetails> localBodyDetails = lbForm.getLocalBodyDetails();
// 			if (localBodyDetails != null && localBodyDetails.get(0) != null) {
// 
// 				for (LocalBodyDetails lbDetail : localBodyDetails) {
// 					String strName = lbDetail.getLocalBodyNameEnglish();
// 					if (lbDetail.getLocalBodyNameEnglish().isEmpty()) {
// 						errors.rejectValue("lgd_LBNameInEn", " s",
// 								"Local Body Name can not be blank.");
// 					} else if (!customRegValidator
// 							.checkforAlphabetsandSpace(strName)) {
// 						errors.rejectValue("lgd_LBNameInEn", "error.ms",
// 								"LocalBody Name contains invalid characters");
// 					}
// 				}
// 			}
// 
// 			if (lbForm.isLgd_LBchkModifyType()
// 					&& lbForm.getLgd_lbTypeCode().isEmpty()) {
// 				errors.rejectValue("lgd_lbTypeCode", "error.sd",
// 						"Field is required.");
// 			}
// 			
// 			if (lbForm.isLgd_LBchkModifyType() && lbForm.getLgd_lbTypeCode() != null && 
// 					 !lbForm.getLgd_lbTypeCode().trim().isEmpty() && Integer.parseInt(lbForm.getLgd_lbTypeCode()) > 0) {
// 				validateLBModifyTypeUrban(lbForm);
// 				
// 			}
// 
// 			if (lbForm.isLgd_LBchkModifyParent()
// 					&& Integer.parseInt(lbForm.getLocalBodyNameEnglishList()) == 0) {
// 				errors.rejectValue("localBodyNameEnglishList", "error.we",
// 						"Please Select New Parent.");
// 			}
// 
// 			if (lbForm.isLgd_LBchkModifyParent()
// 					&& lbForm.getLocalBodyNameEnglishList() != null
// 					&& !lbForm.getLocalBodyNameEnglishList().trim().isEmpty()
// 					&& Integer.parseInt(lbForm.getLocalBodyNameEnglishList()) > 0) {
// 				boolean value = validateLBParent(lbForm);
// 				if (!value){
// 					throw new Exception();
// 				}	
// 			}
// 
// 			if (lbForm.getLgd_LBorderNo().isEmpty()) {
// 				try {
// 					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
// 				} catch (Exception e) {
// 					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
// 				}
// 
// 			} else if (!customRegValidator.checkforOrderNum(lbForm
// 					.getLgd_LBorderNo())) {
// 				errors.rejectValue("lgd_LBorderNo", "error.valid.ORDERNO");
// 			}
// 
// 			if (lbForm.getLgd_LBorderDate() == null) {
// 				try {
// 					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
// 				} catch (Exception e) {
// 					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
// 				}
// 			} else if (!customRegValidator.checkforDate(lbForm
// 					.getLgd_LBorderDate())) // will only matches alphabets
// 			{
// 				errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
// 			}
// 
// 			if (lbForm.getLgd_LBeffectiveDate() == null) {
// 				try {
// 					errors.rejectValue("lgd_LBeffectiveDate",
// 							"Msg.EFFECTIVEDATE");
// 				} catch (Exception e) {
// 					errors.rejectValue("lgd_LBeffectiveDate",
// 							"Msg.EFFECTIVEDATE");
// 				}
// 
// 			} 
// 			
// 			else if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
// 			{
// 				errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
// 			}
// 			
// 			else if(lbForm. getLgd_LBgazPubDate().before(lbForm.getLgd_LBorderDate()) && !lbForm. getLgd_LBgazPubDate().equals(lbForm.getLgd_LBorderDate()))
// 			{
// 				errors.rejectValue("lgd_LBgazPubDate", "error.INCORECT.GAZPUBDATE");
// 			}
// 			
// 			else if (!customRegValidator.checkforDate(lbForm
// 					.getLgd_LBeffectiveDate())) // will only matches alphabets
// 			{
// 				errors.rejectValue("lgd_LBeffectiveDate",
// 						"error.valid.EFFECTIVEDATE");
// 			}
// 
// 		} catch (Exception e) {
// 			throw e;
// 		}
// 	}
	
	public void modifyValidationChangeName(Object target, Errors errors) throws Exception {
		try {
			LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
			CustomRegexValidator customRegValidator = CustomRegexValidator.getInstance();
			//CommonUtil commonutil=new CommonUtil();
			boolean retValue=true;
			Date today = new Date();
			//Code added for including uniqueness of validation of Local Body Name English 
			//String lbType=null;
			String lbTypeName=null;
			String lbTypeNameArray[] =null;
			String parentlbName=null;
			String lbTypeCode=null; 
			//char lbTypeULB=lbForm.getLbType();
			//String strName = lbForm.getLgd_LBNameInEn();
			//lbType=lbForm.getHiddenLbType();

			lbTypeName=lbForm.getLgd_lbTypeCode();// code is here
			lbTypeNameArray=lbTypeName.split(":");			
			lbTypeCode=lbTypeNameArray[0]; //spliting the value here for localbodycodetype 14/1/2014
			
			
		/*	if(lbForm.getLgd_LBTypeName() !=null)
			{
				lbTypeName=lbForm.getLgd_LBTypeName();
				lbTypeNameArray=lbTypeName.split(":");
				lbType=lbTypeNameArray[1];
			}*/
			
			if(lbForm.getHiddenLevel().equals("D") || lbForm.getLgd_LBlevelChk()=='U')
			{
				parentlbName=lbForm.getSlc().toString();
			}
			else if(lbForm.getHiddenLevel().equals("I"))
			{
				parentlbName=lbForm.getParentLBCode();
			}
			else if(lbForm.getHiddenLevel().equals("V"))
			{
				parentlbName=lbForm.getParentLBCode();
			}
			
			String govtOrderConfig = lbForm.getGovtOrderConfig();
			List<LocalBodyDetails> localBodyDetails = lbForm.getLocalBodyDetails();
			if (localBodyDetails != null && localBodyDetails.get(0) != null) {

				for (LocalBodyDetails lbDetail : localBodyDetails) {
					String strName = lbDetail.getLocalBodyNameEnglish();
					if (lbDetail.getLocalBodyNameEnglish().trim().isEmpty() || lbDetail.getLocalBodyNameEnglish().trim()=="") {
						errors.rejectValue("lgd_LBNameInEn", "s", "Local Body Name can not be blank.");
					} 
					else if (!customRegValidator.validateAlphabetDigitSpaceDot(lbDetail.getLocalBodyNameEnglish().trim())) {
						errors.rejectValue("localBodyNameEnglish","error.valid.COMMONALPHABETREQUIRED",	"LocalBody Name Local contains Alphabets only");
					}
					else if (!customRegValidator.validateAlphabetDigitSpaceDot(strName)) {
						errors.rejectValue("lgd_LBNameInEn", "error.ms","LocalBody Name contains invalid characters");
					}
					else if(!parentlbName.equals(""))
					{	
						if (checkExistingLocalBodyName(strName,parentlbName,lbForm.getHiddenLevel(),lbForm.getHiddenLbType().charAt(0), lbTypeCode)) //added a parameter for dao by kirandeep 14/1/2014
						{
							try 
							{
								errors.rejectValue("lgd_LBNameInEn","error.exist.lgd_LBNameInEn","LocalBody Name Already Exist");
								errors.rejectValue("lgd_LBNameInEnh","error.exist.lgd_LBNameInEnh","Please change Local Body name. Otherwise you wouldn't be able to save the data");
							}
							catch (Exception e)
							{
								errors.rejectValue("lgd_LBNameInEn","error.exist.lgd_LBNameInEn");
							}
						}
					}
				}
			}
			
			List<LocalBodyDetails> lbDetails = lbForm.getLocalBodyDetails();
			for (LocalBodyDetails lBDetails : lbDetails) {
				
				if(!lBDetails.getLocalBodyNameLocal().trim().isEmpty() || lBDetails.getLocalBodyNameLocal().trim() !="")
				{
					retValue=customRegValidator.validateSpecialCharacters(lBDetails.getLocalBodyNameLocal().trim());
					if(retValue==false)
					{
						errors.rejectValue("localBodyNameLocal", "error.ms","LocalBody Name in Local contains invalid characters");
					}
				}


				if (!lBDetails.getAliasNameEnglish().isEmpty()) {
					if (!customRegValidator.validateAlphabetDigitSpaceDot(lBDetails.getAliasNameEnglish())) {
						errors.rejectValue("aliasNameEnglish","error.valid.COMMONALPHABETREQUIRED","LocalBody Name Local contains Alphabets only");
					}
				}
				
				if(!lBDetails.getAlisNameLocal().trim().isEmpty() || lBDetails.getAlisNameLocal().trim() !="")
				{
					retValue=customRegValidator.validateSpecialCharacters(lBDetails.getAlisNameLocal().trim());
					if(retValue==false)
					{
						errors.rejectValue("alisNameLocal", "error.ms","LocalBody Name in Alias Local contains invalid characters");
					}
				}
		
			}
			
			if (lbForm.getLgd_LBorderNo().isEmpty()) {
				try {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				}

			} else if (!customRegValidator.checkforOrderNum(lbForm.getLgd_LBorderNo())) {
				errors.rejectValue("lgd_LBorderNo", "error.valid.ORDERNO");
			}

			if (lbForm.getLgd_LBorderDate() == null) {
				try {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				}
			}
			else if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
			{
				errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
			}
			
			if(lbForm.getLgd_LBorderDate() != null)
			{
			  if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
				 }
			  else if(lbForm.getLgd_LBorderDate().after(today) && !lbForm.getLgd_LBorderDate().equals(today))
				{
					errors.rejectValue("lgd_LBorderDate", "error.INCORECT.ORDERDATE");
				}
			}
			
			if (lbForm.getLgd_LBeffectiveDate() == null) {
				try {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				}
			} 
			
			if(lbForm.getLgd_LBeffectiveDate() != null)
			{
				 if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEDATE");
				 }
				 
				 if(lbForm.getLgd_LBeffectiveDate().after(new Date())){
					 errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEFUTUREDATE"); 
				 }
				 
				 else if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				 
			}
			
			if(lbForm.getLgd_LBeffectiveDate() !=null && lbForm.getLgd_LBeffectiveDate() !=null)
			{
				if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				{
					errors.rejectValue("lgd_LBeffectiveDate","error.valid.EFFECTIVEDATE");
				}
			}	
	
			if(govtOrderConfig.equals("govtOrderUpload"))
			{
			
				if(lbForm.getLgd_LBgazPubDate() != null)
				{ 
					if (!customRegValidator.checkforDate(lbForm.getLgd_LBgazPubDate())) // will only matches alphabets
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.valid.GAZPUBDATE");
					}
					else if(lbForm.getLgd_LBgazPubDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBgazPubDate().equals(lbForm.getLgd_LBorderDate()))				 
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.INCORECT.GAZPUBDATE");
					}
				}
				
				if(lbForm.getAttachFile1().isEmpty())
				{		
					try {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
					} catch (Exception e) {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");		
					}
				}
			
				else if(lbForm.getAttachFile1().size() >0 && lbForm.getAttachFile1().get(0).getOriginalFilename() == null || lbForm.getAttachFile1().get(0).getOriginalFilename().equals(""))
				{
					errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
				}
			}
			else if(govtOrderConfig.equals("govtOrderGenerate"))
			{ 
			
				if(lbForm.getTemplateList() != null)
				{
					int templateCode = Integer.parseInt(lbForm.getTemplateList());
					if(templateCode <1)
					{
						errors.rejectValue("templateList","Msg.GovtOrderGenerate");	
					}
				}
			}
		
			/* Added By Sushil Shakya on 24-11-2014 */
			Date dateEffective = abstractValidator.getEffectiveDateByEntity(target);
			log.info("Effective Date from db-->"+dateEffective);
			if (dateEffective != null && lbForm.getLgd_LBeffectiveDate() != null && lbForm.getLgd_LBeffectiveDate().before(dateEffective)) {
				errors.rejectValue("lgd_LBeffectiveDate", "Msg.EFFECTIVEDATE");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void modifyValidationChangeNameAndParentforDisturb(Object target, Errors errors) throws Exception {
		try {
			//LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
			//CustomRegexValidator customRegValidator = CustomRegexValidator.getInstance();
			//CommonUtil commonutil=new CommonUtil();
			//boolean retValue=true;
			//Date today = new Date();
			//Code added for including uniqueness of validation of Local Body Name English 
			//String lbType=null;
			//String lbTypeName=null;
			//String lbTypeNameArray[] =null;
			//String parentlbName=null;
			//char lbTypeULB=lbForm.getLbType();
			//String strName = lbForm.getLgd_LBNameInEn();
			//lbType=lbForm.getHiddenLbType();
		
			
		/*	if(lbForm.getHiddenLevel().equals("D") || lbForm.getLgd_LBlevelChk()=='U')
			{
				parentlbName=lbForm.getSlc().toString();
			}
			else if(lbForm.getHiddenLevel().equals("I"))
			{
				parentlbName=lbForm.getParentLBCode();
			}
			else if(lbForm.getHiddenLevel().equals("V"))
			{
				parentlbName=lbForm.getParentLBCode();
			}
			
			String govtOrderConfig = lbForm.getGovtOrderConfig();
			List<LocalBodyDetails> localBodyDetails = lbForm.getLocalBodyDetails();
			if (localBodyDetails != null && localBodyDetails.get(0) != null) {

				for (LocalBodyDetails lbDetail : localBodyDetails) {
					String strName = lbDetail.getLocalBodyNameEnglish();
					if (lbDetail.getLocalBodyNameEnglish().trim().isEmpty() || lbDetail.getLocalBodyNameEnglish().trim()=="") {
						errors.rejectValue("lgd_LBNameInEn", "s", "Local Body Name can not be blank.");
					} 
					else if (!customRegValidator.validateAlphabetDigitSpaceDot(lbDetail.getLocalBodyNameEnglish().trim())) {
						errors.rejectValue("localBodyNameEnglish","error.valid.COMMONALPHABETREQUIRED",	"LocalBody Name Local contains Alphabets only");
					}
					else if (!customRegValidator.validateAlphabetDigitSpaceDot(strName)) {
						errors.rejectValue("lgd_LBNameInEn", "error.ms","LocalBody Name contains invalid characters");
					}
					else if(!parentlbName.equals(""))
					{	
						if (checkExistingLocalBodyName(strName,parentlbName,lbForm.getHiddenLevel(),lbForm.getHiddenLbType().charAt(0)))
						{
							try 
							{
								errors.rejectValue("lgd_LBNameInEn","error.exist.lgd_LBNameInEn","LocalBody Name Already Exist");
								errors.rejectValue("lgd_LBNameInEnh","error.exist.lgd_LBNameInEnh","Please change Local Body name. Otherwise you wouldn't be able to save the data");
							}
							catch (Exception e)
							{
								errors.rejectValue("lgd_LBNameInEn","error.exist.lgd_LBNameInEn");
							}
						}
					}
				}
			}
			
			List<LocalBodyDetails> lbDetails = lbForm.getLocalBodyDetails();
			for (LocalBodyDetails lBDetails : lbDetails) {
				
				if(!lBDetails.getLocalBodyNameLocal().trim().isEmpty() || lBDetails.getLocalBodyNameLocal().trim() !="")
				{
					retValue=customRegValidator.validateSpecialCharacters(lBDetails.getLocalBodyNameLocal().trim());
					if(retValue==false)
					{
						errors.rejectValue("localBodyNameLocal", "error.ms","LocalBody Name in Local contains invalid characters");
					}
				}


				if (!lBDetails.getAliasNameEnglish().isEmpty()) {
					if (!customRegValidator.validateAlphabetDigitSpaceDot(lBDetails.getAliasNameEnglish())) {
						errors.rejectValue("aliasNameEnglish","error.valid.COMMONALPHABETREQUIRED","LocalBody Name Local contains Alphabets only");
					}
				}
				
				if(!lBDetails.getAlisNameLocal().trim().isEmpty() || lBDetails.getAlisNameLocal().trim() !="")
				{
					retValue=customRegValidator.validateSpecialCharacters(lBDetails.getAlisNameLocal().trim());
					if(retValue==false)
					{
						errors.rejectValue("alisNameLocal", "error.ms","LocalBody Name in Alias Local contains invalid characters");
					}
				}
		
			}*/
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	private boolean checkExistingLocalBodyName(String localBodyName,String parentlbName,String lbType,char lbtypeULB,String lbTypeCode ) throws Exception
	{
		try
		{
			
			
	
			int count = localGovtBodyDAO.getRecordsforLocalBody(localBodyName,parentlbName,lbType,lbtypeULB,lbTypeCode);// we have make here change
			if (count >= 1)
			{
				return true;
			}

		} 
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		return false;
	}
	
	public void modifyValidationLBTypeULB(Object target, Errors errors,HttpServletRequest request,HttpSession session) throws Exception {
		try {
			LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
			CustomRegexValidator customRegValidator = CustomRegexValidator.getInstance();
			
			//commonValidator.isValidMimeLBforViewMLB(errors,request,lbForm.getAttachFile1(),session);
			Date today = new Date();
			String govtOrderConfig = lbForm.getGovtOrderConfig();
			
			List<LocalBodyDetails> localBodyDetails = lbForm.getLocalBodyDetails();
			if (localBodyDetails != null && localBodyDetails.get(0) != null) {

				for (LocalBodyDetails lbDetail : localBodyDetails) {
					String strName = lbDetail.getLocalBodyNameEnglish();
					if (lbDetail.getLocalBodyNameEnglish().trim().isEmpty() || lbDetail.getLocalBodyNameEnglish().trim()=="") {
						errors.rejectValue("lgd_LBNameInEn", "s", "Local Body Name can not be blank.");
					} 
					else if (!customRegValidator.checkforAlphabetsandSpace(lbDetail.getLocalBodyNameEnglish().trim())) {
						errors.rejectValue("localBodyNameEnglish","error.valid.COMMONALPHABETREQUIRED",	"LocalBody Name Local contains Alphabets only");
					}
					else if (!customRegValidator.checkforAlphabetsandSpace(strName)) {
						errors.rejectValue("lgd_LBNameInEn", "error.ms","LocalBody Name contains invalid characters");
					}
				}
			}
			
			if (lbForm.getLocalBodyTypeCode().equals("0"))
			{
				errors.rejectValue("localBodyTypeCode","error.SOURCESELECTLOCALBODYTYPE","Field is required.");
			} 
			
			if (lbForm.getLgd_LBorderNo().isEmpty()) {
				try {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				}

			} else if (!customRegValidator.checkforOrderNum(lbForm.getLgd_LBorderNo())) {
				errors.rejectValue("lgd_LBorderNo", "error.valid.ORDERNO");
			}

			if (lbForm.getLgd_LBorderDate() == null) {
				try {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				}
			}
			else if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
			{
				errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
			}
			
			if(lbForm.getLgd_LBorderDate() != null)
			{
			  if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
				 }
			  else if(lbForm.getLgd_LBorderDate().after(today) && !lbForm.getLgd_LBorderDate().equals(today))
				{
					errors.rejectValue("lgd_LBorderDate", "error.INCORECT.ORDERDATE");
				}
			}
			
			if (lbForm.getLgd_LBeffectiveDate() == null) {
				try {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				}
			} 
			
			if(lbForm.getLgd_LBeffectiveDate() != null)
			{
				 if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEDATE");
				 }
				 
				 if(lbForm.getLgd_LBeffectiveDate().after(new Date())){
					 errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEFUTUREDATE"); 
				 }
				 
				 else if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				 
			}
			
			if(lbForm.getLgd_LBeffectiveDate() !=null && lbForm.getLgd_LBeffectiveDate() !=null)
			{
				if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				{
					errors.rejectValue("lgd_LBeffectiveDate","error.valid.EFFECTIVEDATE");
				}
			}	
	
			if(govtOrderConfig.equals("govtOrderUpload"))
			{
			
				if(lbForm.getLgd_LBgazPubDate() != null)
				{ 
					if (!customRegValidator.checkforDate(lbForm.getLgd_LBgazPubDate())) // will only matches alphabets
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.valid.GAZPUBDATE");
					}
					else if(lbForm.getLgd_LBgazPubDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBgazPubDate().equals(lbForm.getLgd_LBorderDate()))				 
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.INCORECT.GAZPUBDATE");
					}
				}
				
				if(lbForm.getAttachFile1().isEmpty())
				{		
					try {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
					} catch (Exception e) {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");		
					}
				}
			
				else if(lbForm.getAttachFile1().size() >0 && lbForm.getAttachFile1().get(0).getOriginalFilename() == null || lbForm.getAttachFile1().get(0).getOriginalFilename().equals(""))
				{
					errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
				}
			}
			else if(govtOrderConfig.equals("govtOrderGenerate"))
			{ 
			
				if(lbForm.getTemplateList() != null)
				{
					int templateCode = Integer.parseInt(lbForm.getTemplateList());
					if(templateCode <1)
					{
						errors.rejectValue("templateList","Msg.GovtOrderGenerate");	
					}
				}
			}
			
			/* Added By Sushil Shakya on 26-11-2014 */
			Date dateEffective = abstractValidator.getEffectiveDateByEntity(target);
			log.info("Effective Date from db-->"+dateEffective);
			if (dateEffective != null && lbForm.getLgd_LBeffectiveDate() != null && lbForm.getLgd_LBeffectiveDate().before(dateEffective)) {
				errors.rejectValue("lgd_LBeffectiveDate", "Msg.EFFECTIVEDATE");
			}
		} catch (Exception e) {
			throw e;
		}
	}		
	
	public void modifyValidationLBTopTier(Object target, Errors errors,HttpServletRequest request,HttpSession session) throws Exception {
		try {
			LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
			CustomRegexValidator customRegValidator = CustomRegexValidator.getInstance();
			
			//commonValidator.isValidMimeLBforViewMLB(errors,request,lbForm.getAttachFile1(),session);
			Date today = new Date();
			String govtOrderConfig = lbForm.getGovtOrderConfig();
			
			List<LocalBodyDetails> localBodyDetails = lbForm.getLocalBodyDetails();
			if (localBodyDetails != null && localBodyDetails.get(0) != null) {

				for (LocalBodyDetails lbDetail : localBodyDetails) {
					String strName = lbDetail.getLocalBodyNameEnglish();
					if (lbDetail.getLocalBodyNameEnglish().trim().isEmpty() || lbDetail.getLocalBodyNameEnglish().trim()=="") {
						errors.rejectValue("lgd_LBNameInEn", "s", "Local Body Name can not be blank.");
					} 
					else if (!customRegValidator.validateAlphabetDigitSpaceDot(lbDetail.getLocalBodyNameEnglish().trim())) {
						errors.rejectValue("localBodyNameEnglish","error.valid.COMMONALPHABETREQUIRED",	"LocalBody Name Local contains Alphabets only");
					}
					else if (!customRegValidator.validateAlphabetDigitSpaceDot(strName)) {
						errors.rejectValue("lgd_LBNameInEn", "error.ms","LocalBody Name contains invalid characters");
					}
				}
			}
			
			if (lbForm.getLocalBodyNameEnglishList().equals("0"))
			{
				errors.rejectValue("localBodyNameEnglishList","Label.PARENTOFLOCALBODY","Field is required.");
			} 
			
			if (lbForm.getLgd_LBorderNo().isEmpty()) {
				try {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				}

			} else if (!customRegValidator.checkforOrderNum(lbForm.getLgd_LBorderNo())) {
				errors.rejectValue("lgd_LBorderNo", "error.valid.ORDERNO");
			}

			if (lbForm.getLgd_LBorderDate() == null) {
				try {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				}
			}
			else if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
			{
				errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
			}
			
			if(lbForm.getLgd_LBorderDate() != null)
			{
			  if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
				 }
			  else if(lbForm.getLgd_LBorderDate().after(today) && !lbForm.getLgd_LBorderDate().equals(today))
				{
					errors.rejectValue("lgd_LBorderDate", "error.INCORECT.ORDERDATE");
				}
			}
			
			if (lbForm.getLgd_LBeffectiveDate() == null) {
				try {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				}
			} 
			
			if(lbForm.getLgd_LBeffectiveDate() != null)
			{
				 if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEDATE");
				 }
				 
				 if(lbForm.getLgd_LBeffectiveDate().after(new Date())){
					 errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEFUTUREDATE"); 
				 }
				 
				 else if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				 
			}
			
			if(lbForm.getLgd_LBeffectiveDate() !=null && lbForm.getLgd_LBeffectiveDate() !=null)
			{
				if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				{
					errors.rejectValue("lgd_LBeffectiveDate","error.valid.EFFECTIVEDATE");
				}
			}	
	
			if(govtOrderConfig.equals("govtOrderUpload"))
			{
			
				if(lbForm.getLgd_LBgazPubDate() != null)
				{ 
					if (!customRegValidator.checkforDate(lbForm.getLgd_LBgazPubDate())) // will only matches alphabets
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.valid.GAZPUBDATE");
					}
					else if(lbForm.getLgd_LBgazPubDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBgazPubDate().equals(lbForm.getLgd_LBorderDate()))				 
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.INCORECT.GAZPUBDATE");
					}
				}
				
				if(lbForm.getAttachFile1().isEmpty())
				{		
					try {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
					} catch (Exception e) {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");		
					}
				}
			
				else if(lbForm.getAttachFile1().size() >0 && lbForm.getAttachFile1().get(0).getOriginalFilename() == null || lbForm.getAttachFile1().get(0).getOriginalFilename().equals(""))
				{
					errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
				}
			}
			else if(govtOrderConfig.equals("govtOrderGenerate"))
			{ 
			
				if(lbForm.getTemplateList() != null)
				{
					int templateCode = Integer.parseInt(lbForm.getTemplateList());
					if(templateCode <1)
					{
						errors.rejectValue("templateList","Msg.GovtOrderGenerate");	
					}
				}
			}
			/* Added By Sushil Shakya on 24-11-2014 */
			Date dateEffective = abstractValidator.getEffectiveDateByEntity(target);
			log.info("Effective Date from db-->"+dateEffective);
			if (dateEffective != null && lbForm.getLgd_LBeffectiveDate() != null && lbForm.getLgd_LBeffectiveDate().before(dateEffective)) {
				errors.rejectValue("lgd_LBeffectiveDate", "Msg.EFFECTIVEDATE");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void modifyCoverageLB(Object target, Errors errors,HttpServletRequest request,HttpSession session) throws Exception {
		try {
			LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
			CustomRegexValidator customRegValidator = CustomRegexValidator.getInstance();
			
			//commonValidator.isValidMimeLBforViewMLB(errors,request,lbForm.getAttachFile1(),session);
			Date today = new Date();
			String govtOrderConfig = lbForm.getGovtOrderConfig();
			
			if (lbForm.getLgd_LBorderNo().isEmpty()) {
				try {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				}

			} else if (!customRegValidator.checkforOrderNum(lbForm.getLgd_LBorderNo())) {
				errors.rejectValue("lgd_LBorderNo", "error.valid.ORDERNO");
			}

			if (lbForm.getLgd_LBorderDate() == null) {
				try {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				}
			}
			else if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
			{
				errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
			}
			
			if(lbForm.getLgd_LBorderDate() != null)
			{
			  if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
				 }
			  else if(lbForm.getLgd_LBorderDate().after(today) && !lbForm.getLgd_LBorderDate().equals(today))
				{
					errors.rejectValue("lgd_LBorderDate", "error.INCORECT.ORDERDATE");
				}
			}
			
			if (lbForm.getLgd_LBeffectiveDate() == null) {
				try {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				}
			} 
			
			if(lbForm.getLgd_LBeffectiveDate() != null)
			{
				 if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEDATE");
				 }
				 
				 if(lbForm.getLgd_LBeffectiveDate().after(new Date())){
					 errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEFUTUREDATE"); 
				 }
				 
				 else if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				 
			}
			
			if(lbForm.getLgd_LBeffectiveDate() !=null && lbForm.getLgd_LBeffectiveDate() !=null)
			{
				if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				{
					errors.rejectValue("lgd_LBeffectiveDate","error.valid.EFFECTIVEDATE");
				}
			}	
	
			if(govtOrderConfig.equals("govtOrderUpload"))
			{
			
				if(lbForm.getLgd_LBgazPubDate() != null)
				{ 
					if (!customRegValidator.checkforDate(lbForm.getLgd_LBgazPubDate())) // will only matches alphabets
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.valid.GAZPUBDATE");
					}
					else if(lbForm.getLgd_LBgazPubDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBgazPubDate().equals(lbForm.getLgd_LBorderDate()))				 
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.INCORECT.GAZPUBDATE");
					}
				}
				
				if(lbForm.getAttachFile1().isEmpty())
				{		
					try {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
					} catch (Exception e) {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");		
					}
				}
			
				else if(lbForm.getAttachFile1().size() >0 && lbForm.getAttachFile1().get(0).getOriginalFilename() == null || lbForm.getAttachFile1().get(0).getOriginalFilename().equals(""))
				{
					errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
				}
			}
			else if(govtOrderConfig.equals("govtOrderGenerate"))
			{ 
			
				if(lbForm.getTemplateList() != null)
				{
					int templateCode = Integer.parseInt(lbForm.getTemplateList());
					if(templateCode <1)
					{
						errors.rejectValue("templateList","Msg.GovtOrderGenerate");	
					}
				}
			}
			/* Added By Sushil Shakya on 15-10-2014 */
			Date dateEffective = abstractValidator.getEffectiveDateByEntity(target);
			log.info("Effective Date from db-->"+dateEffective);
			if (dateEffective != null && lbForm.getLgd_LBeffectiveDate() != null && lbForm.getLgd_LBeffectiveDate().before(dateEffective)) {
				errors.rejectValue("lgd_LBeffectiveDate", "Msg.EFFECTIVEDATE");
			}
		} catch (Exception e) {
			throw e;
		}
	}	
	
	
	public void modifyCoverageLBDisturbed(Object target, Errors errors,HttpServletRequest request,HttpSession session) throws Exception {
		try {
			//LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
			//CustomRegexValidator customRegValidator = CustomRegexValidator.getInstance();
			
			//commonValidator.isValidMimeLBforViewMLB(errors,request,lbForm.getAttachFile1(),session);
			//Date today = new Date();
			//String govtOrderConfig = lbForm.getGovtOrderConfig();
			
			/*if (lbForm.getLgd_LBorderNo().isEmpty()) {
				try {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderNo", "Msg.ORDERNO");
				}

			} else if (!customRegValidator.checkforOrderNum(lbForm.getLgd_LBorderNo())) {
				errors.rejectValue("lgd_LBorderNo", "error.valid.ORDERNO");
			}

			if (lbForm.getLgd_LBorderDate() == null) {
				try {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBorderDate", "Msg.ORDERDATE");
				}
			}
			else if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
			{
				errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
			}
			
			if(lbForm.getLgd_LBorderDate() != null)
			{
			  if (!customRegValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
				 }
			  else if(lbForm.getLgd_LBorderDate().after(today) && !lbForm.getLgd_LBorderDate().equals(today))
				{
					errors.rejectValue("lgd_LBorderDate", "error.INCORECT.ORDERDATE");
				}
			}
			
			if (lbForm.getLgd_LBeffectiveDate() == null) {
				try {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				} catch (Exception e) {
					errors.rejectValue("lgd_LBeffectiveDate","Msg.EFFECTIVEDATE");
				}
			} 
			
			if(lbForm.getLgd_LBeffectiveDate() != null)
			{
				 if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				 {
					errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEDATE");
				 }
				 
				 if(lbForm.getLgd_LBeffectiveDate().after(new Date())){
					 errors.rejectValue("lgd_LBeffectiveDate", "error.valid.EFFECTIVEFUTUREDATE"); 
				 }
				 
				 else if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				 
			}
			
			if(lbForm.getLgd_LBeffectiveDate() !=null && lbForm.getLgd_LBeffectiveDate() !=null)
			{
				if(lbForm.getLgd_LBeffectiveDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBeffectiveDate().equals(lbForm.getLgd_LBorderDate()))
				{
					errors.rejectValue("lgd_LBeffectiveDate", "error.INCORECT.EFFECTIVEDATE");
				}
				if (!customRegValidator.checkforDate(lbForm.getLgd_LBeffectiveDate())) // will only matches alphabets
				{
					errors.rejectValue("lgd_LBeffectiveDate","error.valid.EFFECTIVEDATE");
				}
			}	
	
			if(govtOrderConfig.equals("govtOrderUpload"))
			{
			
				if(lbForm.getLgd_LBgazPubDate() != null)
				{ 
					if (!customRegValidator.checkforDate(lbForm.getLgd_LBgazPubDate())) // will only matches alphabets
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.valid.GAZPUBDATE");
					}
					else if(lbForm.getLgd_LBgazPubDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBgazPubDate().equals(lbForm.getLgd_LBorderDate()))				 
					{
						errors.rejectValue("lgd_LBgazPubDate", "error.INCORECT.GAZPUBDATE");
					}
				}
				
				if(lbForm.getAttachFile1().isEmpty())
				{		
					try {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
					} catch (Exception e) {
						errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");		
					}
				}
			
				else if(lbForm.getAttachFile1().size() >0 && lbForm.getAttachFile1().get(0).getOriginalFilename() == null || lbForm.getAttachFile1().get(0).getOriginalFilename().equals(""))
				{
					errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
				}
			}
			else if(govtOrderConfig.equals("govtOrderGenerate"))
			{ 
			
				if(lbForm.getTemplateList() != null)
				{
					int templateCode = Integer.parseInt(lbForm.getTemplateList());
					if(templateCode <1)
					{
						errors.rejectValue("templateList","Msg.GovtOrderGenerate");	
					}
				}
			}*/
			
		} catch (Exception e) {
			throw e;
		}
	}	
	
	
	
	/*private boolean validateLBParent(LocalGovtBodyForm lbForm) {
		try {
			List<LocalbodyListbyState> districtPanchayatList = lbForm
					.getDistrictPanchayatList();

			for (LocalbodyListbyState lbStateW : districtPanchayatList) {
				if (lbForm.getLocalBodyNameEnglishList() != null
						&& !lbForm.getLocalBodyNameEnglishList().trim()
								.isEmpty()
						&& lbStateW.getLocalBodyCode() == Integer
								.parseInt(lbForm.getLocalBodyNameEnglishList())) {
					return true;
				} 
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return false;
	}*/
	
	/*private boolean validateLBModifyTypeUrban(LocalGovtBodyForm lbForm)
			throws Exception {
		try {
			List<LocalbodyforStateWise> localBodyTypelist = lbForm
					.getLocalBodyTypelist();

			for (LocalbodyforStateWise lbStateW : localBodyTypelist) {
				if (lbStateW.getLocalBodyTypeCode() == Integer.parseInt(lbForm.getLgd_lbTypeCode())){
					return true;
				}	
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}*/

}
