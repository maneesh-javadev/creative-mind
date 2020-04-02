package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.service.ComboFillingService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("cValidator")
public class CustomValidatorLB implements Validator {
	
	private static final Logger log = Logger.getLogger(CustomValidatorLB.class);

	@Autowired
	private LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	private ComboFillingService comboFillingService;
	
	@Autowired
	private CommonValidatorImpl commonValidatorImpl;

	@Override
	public boolean supports(Class<?> clas) {

		return LocalGovtBodyForm.class.isAssignableFrom(clas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

	}

	public void validateLB(Object target,Errors errors,HttpServletRequest request,HttpSession session) throws Exception
	{
		try 
		{
			LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
			CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
			//CustomRegexValidator customRegValidator = CustomRegexValidator.getInstance();
			
			commonValidatorImpl.isValidMimeLB(errors, request, lbForm.getAttachFile2(),session);
			
			String strName = lbForm.getLgd_LBNameInEn();
			String lbTypeName=null;
			String lbTypeNameArray[] =null;
			String lbType=null;
			boolean retValue=true;
			String parentlbName=null;
			String lbTypeCode=null;
			char lbTypeULB=lbForm.getLbType();
			
			if(lbForm.getLgd_LBTypeName() !=null)
			{
				
				lbTypeName=lbForm.getLgd_LBTypeName();
				lbTypeNameArray=lbTypeName.split(":");
				lbType=lbTypeNameArray[1];
				lbTypeCode=lbTypeNameArray[0];// spliting the lbcodetypecode for passing to Dao by kirandeep 14/1/2014
			}
			
			if(lbType.equals("D") || lbTypeULB == 'U')
			{
				parentlbName=lbForm.getSlc().toString();
			}
			else if(lbType.equals("I"))
			{
				parentlbName=lbForm.getLgd_LBDistrictAtInter();
			}
			else if(lbType.equals("V"))
			{
				parentlbName=lbForm.getLgd_LBDistrictAtVillage();
			}
			
			if (!lbForm.getlatitude().isEmpty()) 
			{
				if (!customValidator.checkforLatiandLongi(lbForm.getlatitude())) 
				{
					errors.rejectValue("latitude","error.valid.COMMONLONGANDLATVALUE","Latitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
				}
			}
			if (!lbForm.getlongitude().isEmpty()) 
			{
				if (!customValidator.checkforLatiandLongi(lbForm.getlongitude())) 
				{
					errors.rejectValue("longitude","error.valid.COMMONLONGANDLATVALUE","Longitude Contains only 0 to 9 with Dot(.) OR 0 to 9");
				}
			}
			if(!parentlbName.equals(""))
			{	
				if (checkExistingLocalBodyName(strName, parentlbName, lbType, lbTypeULB, lbTypeCode)) // parameter added by kirandeep 14/1/2014
				{
					try 
					{
						errors.rejectValue("lgd_LBNameInEn","error.exist.lgd_LBNameInEn","LocalBody Name Already Exist");
					}
					catch (Exception e)
					{
						errors.rejectValue("lgd_LBNameInEn","error.exist.lgd_LBNameInEn");
					}
				}
			}	

			if (lbForm.getLbType() == 'P' || lbForm.getLbType() == 'T') 
			{
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lgd_LBNameInEn", "Msg.LOCALBODYNAMEEN","Local Body name is required.");

				if(!lbForm.getLgd_LBNameInLocal().trim().isEmpty() || lbForm.getLgd_LBNameInLocal().trim() !="")
				{
					retValue=customValidator.validateSpecialCharacters(lbForm.getLgd_LBNameInLocal().trim());
					if(retValue==false)
					{
						errors.rejectValue("localBodyNameLocal", "error.ms","LocalBody Name in Local contains invalid characters");
					}
				}

				if(!lbForm.getLgd_LBAliasInEn().trim().isEmpty() || lbForm.getLgd_LBAliasInEn().trim() !="")
				{
					retValue=customValidator.validateSpecialCharacters(lbForm.getLgd_LBAliasInEn().trim());
					if(retValue==false)
					{
						errors.rejectValue("alisNameLocal", "error.ms","LocalBody Name in Alias Local contains invalid characters");
					}
				}
				
				if (!lbForm.getLgd_LBstateSpecificCode().isEmpty())
				{
					if (!customValidator.checkforAlphaNumericandSpace(lbForm.getLgd_LBstateSpecificCode())) 
					{
						errors.rejectValue("lgd_LBstateSpecificCode","error.valid.COMMONALPHANUMERICREQUIRED","State Specific Code contains contains Alphabets only");
					}
				}

				if (!customValidator.validateAlphabetDigitSpaceDot(strName))
				{
					errors.rejectValue("lgd_LBNameInEn","error.valid.COMMONALPHABETREQUIRED","LocalBody Name contains Alphabets only");
				}
				if (lbForm.getLgd_LBTypeName().length() == 1)
				{
					errors.rejectValue("lgd_LBTypeName","error.SOURCESELECTLOCALBODYTYPE","Field is required.");
				} 
				/*else if (lbType.equals("I") && !lbForm.getLgd_LBDistrictAtInter().equals("0"))
				{
					if(lbForm.getLgd_LBDistrictAtInter().length() == 1)
					{	
						errors.rejectValue("lgd_LBDistrictAtInter", "error.Msg","Please Select Contributing District/Zilla Panchayat.");
					}	
				}*/
				//else if (lbForm.getLgd_LBTypeName().contains("V") && lbForm.getLgd_LBDistrictAtVillage().length() == 1)// && lbForm.getLgd_LBIntermediateAtVillage().length() == 1)
				
				if (!lbForm.isLgd_LBExistCheck() && !lbForm.isLgd_LBUnmappedCheck()) 
				{
						errors.rejectValue("lgd_LBDistrictAtVillage","error.SOURCESELECTVILLAGEPARENT",	"Field is required.");
						errors.rejectValue("lgd_LBUnmappedCheck","error.EXISTINGLOCALBODY", "Field is required.");
						errors.rejectValue("lgd_LBIntermediateAtVillage","error.SOURCESELECTVILLAGEPARENT","Field is required.");
				}
				if (!lbForm.isLgd_LBExistCheck() && !lbForm.isLgd_LBUnmappedCheck())
				{
					errors.rejectValue("lgd_LBExistCheck","error.EXISTINGLOCALBODY", "Field is required.");
					errors.rejectValue("lgd_LBUnmappedCheck","error.EXISTINGLOCALBODY", "Field is required.");
				}

				
				if (lbType.equals("D") && lbForm.isLgd_LBExistCheck() && lbForm.getLgd_LBDistPDestList() == null) 
				{
					errors.rejectValue("lgd_LBDistPDestList","error.blank.DISTRICTCAatDCA", "Field is required.");
				} 
				else if (lbType.equals("I") && lbForm.isLgd_LBExistCheck() && lbForm.getLgd_LBInterPDestList() == null)
				{
					errors.rejectValue("lgd_LBInterPDestList","error.blank.changeMesssageCAatSDCA","Field is required.");
				} 
				else if (lbType.equals("V") && lbForm.isLgd_LBExistCheck() && lbForm.getLgd_LBVillageDestAtVillageCA() == null) 
				{
					errors.rejectValue("lgd_LBVillageDestAtVillageCA","error.blank.changeMesssageCAatV","Field is required.");
				}
				/*if (lbForm.getLgd_LBTypeName().contains("D") && lbForm.isLgd_LBUnmappedCheck()	&& lbForm.getLgd_LBDistCAreaDestListUmapped() == null)
				{
					errors.rejectValue("lgd_LBDistCAreaDestListUmapped","notmatch.password", "Field is required.");
				}
				else if (lbForm.getLgd_LBTypeName().contains("I")	&& lbForm.isLgd_LBUnmappedCheck() && lbForm.getLgd_LBInterCAreaDestListUmapped() == null)
				{
					errors.rejectValue("lgd_LBInterCAreaDestListUmapped","notmatch.password", "Field is required.");
				}
				else if (lbForm.getLgd_LBTypeName().contains("V")	&& lbForm.isLgd_LBUnmappedCheck() && lbForm.getLgd_LBVillageCAreaDestLUnmapped() == null)
				{
					errors.rejectValue("lgd_LBVillageCAreaDestLUnmapped","notmatch.password", "Field is required.");
				}
*/
				// Check For Combo Fields
				boolean checkType = validateLBTypeList(lbForm);
				if (!checkType){
					throw new Exception();
				}	
				/*if (lbForm.getLgd_LBDistrictAtInter() != null && !lbForm.getLgd_LBDistrictAtInter().trim().isEmpty() && Integer.parseInt(lbForm.getLgd_LBDistrictAtInter()) > 0) {
					boolean checkDistList = validateLBDistP(lbForm);
					if (!checkDistList)
						throw new Exception();
				}
				
				if (lbForm.getLgd_LBDistrictAtVillage() != null && !lbForm.getLgd_LBDistrictAtVillage().trim().isEmpty() && Integer.parseInt(lbForm.getLgd_LBDistrictAtVillage()) > 0)
				{
					boolean checkDistList = validateLBDistP(lbForm);
					if (!checkDistList)
						throw new Exception();
				}*/
				
				if (lbForm.getLgd_LBDistrictAtVillage() != null	&& !lbForm.getLgd_LBDistrictAtVillage().trim().isEmpty() && Integer.parseInt(lbForm.getLgd_LBDistrictAtVillage()) > 0
						&& lbForm.getLgd_LBIntermediateAtVillage() != null
						&& lbForm.getLgd_LBIntermediateAtVillage().trim()
								.isEmpty() && Integer
								.parseInt(lbForm.getLgd_LBIntermediateAtVillage()) > 0){
					validateLBInterPList(lbForm);
				}	

			}
			else if (lbForm.getLbType() == 'U')
			{
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lgd_LBNameInEn", "Msg.LOCALBODYNAMEEN","Local Body name is required.");

				if (!customValidator.validateAlphabetDigitSpaceDot(strName)) 
				{
					errors.rejectValue("lgd_LBNameInEn", "error.ms","LocalBody Name contains invalid characters");
				}
				
				if(!lbForm.getLgd_LBNameInLocal().trim().isEmpty() || lbForm.getLgd_LBNameInLocal().trim() !="")
				{
					retValue=customValidator.validateSpecialCharacters(lbForm.getLgd_LBNameInLocal().trim());
					if(retValue==false)
					{
						errors.rejectValue("localBodyNameLocal", "error.ms","LocalBody Name in Local contains invalid characters");
					}
				}

				if(!lbForm.getLgd_LBAliasInEn().trim().isEmpty() || lbForm.getLgd_LBAliasInEn().trim() !="")
				{
					retValue=customValidator.validateSpecialCharacters(lbForm.getLgd_LBAliasInEn().trim());
					if(retValue==false)
					{
						errors.rejectValue("alisNameLocal", "error.ms","LocalBody Name in Alias Local contains invalid characters");
					}
				}
				
				/*if (Integer.parseInt(lbForm.getLgd_LBTypeName()) == 0) */
				if (lbForm.getLgd_LBTypeName() == null)
				{
					errors.rejectValue("lgd_LBTypeName","error.SOURCESELECTLOCALBODYTYPE","Field is required.");
				}

				if (!lbForm.isLgd_LBExistCheck() && !lbForm.isLgd_LBUnmappedCheck())
				{
					errors.rejectValue("lgd_LBExistCheck","error.EXISTINGLOCALBODY", "Field is required.");
					errors.rejectValue("lgd_LBUnmappedCheck","error.EXISTINGLOCALBODY", "Field is required.");
				}

				if (lbForm.isLgd_LBExistCheck()	&& lbForm.getLgd_UrbanLBDistExistDest() == null) 
				{
					errors.rejectValue("lgd_UrbanLBDistExistDest", " ","Contributing Urban Local Body List is required.");
				}

				if (lbForm.isLgd_LBUnmappedCheck() && lbForm.getLgd_UrbanLBDistUmappedDest() == null) 
				{
					errors.rejectValue("lgd_UrbanLBDistUmappedDest", " ","Contributing SubDistrict List is required.");
				}

				// Check For Combo Fields
				boolean checkType = validateLBTypeUrban(lbForm);
				if (!checkType){
					throw new Exception();
				}	
			}

		}
		catch (Exception e)
		{
			throw e;
		}

	}
	
	public void validateLBCovChange(Object target,Errors errors,HttpServletRequest request,HttpSession session) throws Exception
	{
		try 
		{
			LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
			
			//GovernmentOrderForm govtForm=(GovernmentOrderForm)object;
			String govtOrderConfig = lbForm.getGovtOrderConfig();
			Date today = new Date();
			CustomRegexValidator customValidator = CustomRegexValidator.getInstance();

			if(!lbForm.getLgd_LBorderNo().trim().isEmpty() || lbForm.getLgd_LBorderDate() !=null || lbForm.getLgd_LBeffectiveDate() !=null || lbForm.getLgd_LBgazPubDate() !=null)
			{	
				if(lbForm.getLgd_LBorderNo().trim().isEmpty() || lbForm.getLgd_LBorderNo()=="")
				{			
					try {
						errors.rejectValue("lgd_LBorderNo","Msg.ORDERNO");	
					} catch (Exception e) {
						errors.rejectValue("lgd_LBorderNo","Msg.ORDERNO");	
					}			
									
				}
				else if(!customValidator.checkforOrderNum(lbForm.getLgd_LBorderNo()))
				{
					errors.rejectValue("lgd_LBorderNo","error.valid.ORDERNO");	
				}
		 		
				if(lbForm.getLgd_LBorderDate()==null)
				{
					try {
						errors.rejectValue("lgd_LBorderDate","Msg.ORDERDATE");		
					} catch (Exception e) {
						errors.rejectValue("lgd_LBorderDate","Msg.ORDERDATE");		
					}			
				}	
				else if(lbForm.getLgd_LBorderDate() != null)
				{
				  if (!customValidator.checkforDate(lbForm.getLgd_LBorderDate())) // will only matches alphabets
					 {
						errors.rejectValue("lgd_LBorderDate", "error.valid.ORDERDATE");
					 }
				  else if(lbForm.getLgd_LBorderDate().after(today) && !lbForm.getLgd_LBorderDate().equals(today))
					{
						errors.rejectValue("lgd_LBorderDate", "error.INCORECT.ORDERDATE");
					}
				}
				
				if(govtOrderConfig.equals("govtOrderUpload"))
				{
				
					if(lbForm.getLgd_LBgazPubDate() != null)
					{ 
						if (!customValidator.checkforDate(lbForm.getLgd_LBgazPubDate())) // will only matches alphabets
						 {
							errors.rejectValue("gazPubDate2", "error.valid.GAZPUBDATE");
						 }
						else if(lbForm.getLgd_LBgazPubDate().before(lbForm.getLgd_LBorderDate()) && !lbForm.getLgd_LBgazPubDate().equals(lbForm.getLgd_LBorderDate()))				 
						{
							errors.rejectValue("gazPubDate2", "error.INCORECT.GAZPUBDATE");
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
			}	
			
		}catch (Exception e)
		{
			throw e;
		}

	}
	
	private boolean validateLBInterPList(LocalGovtBodyForm lbForm)
			throws Exception {
		try {

			comboFillingService.getComboValuesforApp('L', "L",
					Integer.parseInt(lbForm.getLgd_LBDistrictAtVillage()),
					Integer.parseInt(lbForm.getLgd_LBIntermediateAtVillage()));

		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	private boolean checkExistingLocalBodyName(String localBodyName,String parentlbName,String lbType,char lbtypeULB,String lbTypeCode) throws Exception // parameter added and passed to Dao
	{
		try
		{
			int count = localGovtBodyDAO.getRecordsforLocalBody(localBodyName,parentlbName,lbType,lbtypeULB, lbTypeCode);
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

	private boolean validateLBTypeList(LocalGovtBodyForm lbForm)
			throws Exception {
		try {
			List<LocalbodyforStateWise> localBodyTypelist = lbForm
					.getLocalBodyTypelist();
			String lbTypeName[] = lbForm.getLgd_LBTypeName().split(":");
			for (LocalbodyforStateWise lbStateW : localBodyTypelist) {
				if (lbTypeName.length > 1
						&& lbStateW.getLocalBodyTypeCode() == Integer
								.parseInt(lbTypeName[0])
						&& lbStateW.getLevel().contentEquals(lbTypeName[1])){
					return true;
				}	
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	private boolean validateLBTypeUrban(LocalGovtBodyForm lbForm) throws Exception 
	{
		try 
		{
			List<LocalbodyforStateWise> localBodyTypelist = lbForm.getLocalBodyTypelist();

			for (LocalbodyforStateWise lbStateW : localBodyTypelist)
			{
				String lgTypeNameId = lbForm.getLgd_LBTypeName();
				String[] lgTypeNameArr = lgTypeNameId.split(":");
				String localGovtType = lgTypeNameArr[0];
				
				if (lbStateW.getLocalBodyTypeCode() == Integer.parseInt(localGovtType)){
					return true;
				}	
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		return false;
	}

// TODO Remove unused code found by UCDetector
// 	public boolean validateLBDistP(LocalGovtBodyForm lbForm) {
// 		try {
// 			List<LocalbodyListbyState> districtPanchayatList = lbForm
// 					.getDistrictPanchayatList();
// 
// 			for (LocalbodyListbyState lbStateW : districtPanchayatList) {
// 				if (lbForm.getLgd_LBDistrictAtInter() != null
// 						&& !lbForm.getLgd_LBDistrictAtInter().isEmpty()
// 						&& lbStateW.getLocalBodyCode() == Integer
// 								.parseInt(lbForm.getLgd_LBDistrictAtInter())) {
// 					return true;
// 				} else if (lbForm.getLgd_LBDistrictAtVillage() != null
// 						&& !lbForm.getLgd_LBDistrictAtVillage().isEmpty()
// 						&& lbStateW.getLocalBodyCode() == Integer
// 								.parseInt(lbForm.getLgd_LBDistrictAtVillage())) {
// 					return true;
// 				}
// 			}
// 
// 		} catch (Exception e) {
// 			log.debug("Exception" + e);
// 		}
// 		return false;
// 	}

}
