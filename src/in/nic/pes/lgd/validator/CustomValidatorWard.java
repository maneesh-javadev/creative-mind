package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * The Class CustomValidatorWard Validate the Create Ward jsp Page.
 */
@Component("validatorWard")
public class CustomValidatorWard implements Validator {
	
	private static final Logger log = Logger.getLogger(CustomValidatorWard.class);

	
	
	@Autowired
	private LocalGovtBodyDAO localGovtBodyDAO;
	
	/*
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clas) {

		return LocalGovtBodyForm.class.isAssignableFrom(clas);
	}

	/*
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
	}
	
	public void validateviewWard(Object target, Errors errors) throws Exception
	{
		try
		{
			LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
			String lgTypeNameId=null;
			String[] lgTypeNameArr = null;
			String localGovtType = null;
			if(lbForm.getLgd_LBTypeName() !=null)
			{
				lgTypeNameId = lbForm.getLgd_LBTypeName();
				lgTypeNameArr = lgTypeNameId.split(":");
				localGovtType = lgTypeNameArr[1];
			}
			if (lbForm.getLgd_LBTypeName().length() == 1)
			{
				errors.rejectValue("lgd_LBTypeName","error.SOURCESELECTLOCALBODYTYPE","Field is required.");
			} 
			
			//if (localGovtType.contains("D") && lbForm.getLocalBodyNameEnglishList() == "" && lbForm.getLocalBodyNameEnglishList() == null) 
			if (localGovtType.contains("D") && lbForm.getLocalBodyNameEnglishList().length()==0) 
			{
				errors.rejectValue("localBodyNameEnglishList","message","Please select one District Panchayat List.");
			}
			
			//if (localGovtType.contains("I") && lbForm.getLgd_LBDistListAtInterCA() == "" && lbForm.getLgd_LBDistListAtInterCA() == null) 
			if (localGovtType.contains("I") && lbForm.getLgd_LBDistListAtInterCA().length() == 0 && lbForm.getSlc()!=34) 
			{
				errors.rejectValue("lgd_LBDistListAtInterCA","message","Please Select One District Panchayat List");
			}
			
			//if (localGovtType.contains("I") && lbForm.getLgd_LBInterPSourceList() == "" && lbForm.getLgd_LBInterPSourceList() == null) 
			if (localGovtType.contains("I") && lbForm.getLgd_LBInterPSourceList().length() == 0) 
			{
				errors.rejectValue("lgd_LBInterPSourceList","message","Please Select One Intermediate Panchayat List");
			}
			
			//if (localGovtType.contains("V") && lbForm.getLgd_LBDistListAtVillageCA() == "" && lbForm.getLgd_LBDistListAtVillageCA() == null) 
			
		/*	if (localGovtType.contains("V") && lbForm.getLgd_LBDistListAtVillageCA().length() == 0) 
			{
				errors.rejectValue("lgd_LBDistListAtVillageCA","message","Please Select One District Panchayat List");
			}*/
			
			
//			if (localGovtType.contains("V") && lbForm.getLgd_LBInterListAtVillageCA() == "" && lbForm.getLgd_LBInterListAtVillageCA() == null) 
			/*if (localGovtType.contains("V") && lbForm.getLgd_LBInterListAtVillageCA().length() == 0) 	
			{
				errors.rejectValue("lgd_LBInterListAtVillageCA","message","Please Select One Intermediate Panchayat List");
			}*/
			
			//if (localGovtType.contains("V") && lbForm.getLocalBodyNameEnglishListSourceVillg() == "" && lbForm.getLocalBodyNameEnglishListSourceVillg() == null) 
			if (localGovtType.contains("V") && lbForm.getLocalBodyNameEnglishListSourceVillg().length() == 0) 
			{
				errors.rejectValue("localBodyNameEnglishListSourceVillg","message","Please Select One Village Panchayat List");
			}
			
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
	}
	
	public void validateModifyWard(Object target, Errors errors) throws Exception
	{
		LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;

		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		String strName = lbForm.getWard_number();
		
		//String lbTypeName=null;
		//String lbTypeNameArray[] =null;
		//String lbType=null;
		//String lbName=null;
		int lblc=0;
		lblc=lbForm.getLblc();
		/*if(lbForm.getLgd_LBTypeName() !=null)
		{
			lbTypeName=lbForm.getLgd_LBTypeName();
			lbTypeNameArray=lbTypeName.split(":");
			lbType=lbTypeNameArray[1];
		}
		if(lbType.equals("D"))
		{
			 lbName=lbForm.getLocalBodyNameEnglishList();
		}
		else if(lbType.equals("I"))
		{
			lbName=lbForm.getLgd_LBInterPSourceList();
		}
		else if(lbType.equals("V"))
		{
			lbName=lbForm.getLgd_LBVillageSourceAtVillageCA();
		}*/
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ward_Name", "msg","Ward name is required.");
		if (!lbForm.getWard_Name().isEmpty()) 
		{	
			if (!lbForm.getWard_Name().matches("^[a-zA-Z0-9 \\-\\/\\.\\(\\)]+$")) // will only matches alphabets
			{
				errors.rejectValue("ward_Name", "message","The Ward name contains invalid characters.");
			}
			if (!lbForm.getWard_Name().equalsIgnoreCase(lbForm.getLgd_LBNameInEn()))
			if (!checkExistingWardName(lbForm.getWard_Name(),lblc))
			{
				try 
				{
					errors.rejectValue("ward_Name","Ward Name Exist");
				}
				catch (Exception e)
				{
					errors.rejectValue("ward_Name","Ward Name Exist");
				}
			}
			
			
		}	
		if (!lbForm.getWard_number().isEmpty()) 
		{
			if (!customValidator.checkforNumericandSlash(lbForm.getWard_number()))
			{
				errors.rejectValue("ward_number","error.valid.COMMONALPHANUMERICREQUIRED","LocalBody Name Local contains Alphabets only");
			}
			
			if (!lbForm.getWard_number().equalsIgnoreCase(lbForm.getWardCcode()))
			{
			if (checkExistingWardNumberModify(strName,lblc))
			{
			try 
			{
			errors.rejectValue("ward_number","error.exist.lgd_wardNumber","Ward Number Already Exist");
			}
			catch (Exception e)
			{
			errors.rejectValue("ward_number","error.exist.lgd_wardNumber");
			}
			}
			}			
		}
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ward_number", "msg","Ward Number is required.");

		/*if (!lbForm.getWard_NameLocal().isEmpty()) 
		{
			if (!customValidator.checkforAlphabetsandSpace(lbForm.getWard_NameLocal()))
			{
				errors.rejectValue("ward_NameLocal","error.valid.COMMONALPHABETREQUIRED","LocalBody Name Local contains Alphabets only");
			}
		}*/
	}
	
// TODO Remove unused code found by UCDetector
// 	public void validateWard(Object target, Errors errors) throws Exception
// 	{
// 		LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
// 
// 		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
// 		String strName = lbForm.getWard_number();
// 		
// 		String lbTypeName=null;
// 		String lbTypeNameArray[] =null;
// 		String lbType=null;
// 		String lbName=null;
// 		boolean retValue=true;
// 		int lblc=0;
// 		
// 		if(lbForm.getLgd_LBTypeName() !=null)
// 		{
// 			lbTypeName=lbForm.getLgd_LBTypeName();
// 			lbTypeNameArray=lbTypeName.split(":");
// 			lbType=lbTypeNameArray[1];
// 		}
// 		if(lbType.equals("D"))
// 		{
// 			 lbName=lbForm.getLocalBodyNameEnglishList();
// 		}
// 		else if(lbType.equals("I"))
// 		{
// 			lbName=lbForm.getLgd_LBInterPSourceList();
// 		}
// 		else if(lbType.equals("V"))
// 		{
// 			lbName=lbForm.getLgd_LBVillageSourceAtVillageCA();
// 		}
// 		
// 		lblc=getLblc(lbName);
// 		
// 		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ward_Name", "msg","Ward name is required.");
// 		if (!lbForm.getWard_Name().matches("^[a-zA-Z0-9 \\-\\/\\.\\(\\)]+$")) // will only matches alphabets
// 		{
// 			errors.rejectValue("ward_Name", "message","The Ward name contains invalid characters.");
// 		}
// 		if (!lbForm.getWard_number().isEmpty()) 
// 		{
// 			if (!customValidator.checkforNumericandSlash(lbForm.getWard_number()))
// 			{
// 				errors.rejectValue("ward_number","error.valid.COMMONALPHANUMERICREQUIRED","LocalBody Name Local contains Alphabets only");
// 			}
// 			
// 			if (checkExistingWardNumber(strName,lblc))
// 			{
// 				try 
// 				{
// 					errors.rejectValue("ward_number","error.exist.lgd_wardNumber","Ward Number Already Exist");
// 				}
// 				catch (Exception e)
// 				{
// 					errors.rejectValue("ward_number","error.exist.lgd_wardNumber");
// 				}
// 			}
// 			
// 		}
// 
// 		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ward_number", "msg","Ward Number is required.");
// 
// 		if (!lbForm.getWard_NameLocal().isEmpty()) 
// 		{
// 			retValue=customValidator.validateSpecialCharacters(lbForm.getWard_NameLocal().trim());
// 			if(retValue==false)
// 			{
// 				errors.rejectValue("ward_NameLocal", "error.ms","LocalBody Name Local contains invalid characters");
// 			}
// 			
// 		}
// 		/*Modified changed by kirandeep on 05/08/2014*/
// 	/*	
// 		if(lbForm.getLgd_LBTypeName() !=null)
// 		{
// 			if(lbType.equals("D") && lbForm.getLgd_LBDistCAreaDestList() == null)
// 			{
// 				errors.rejectValue("lgd_LBDistCAreaDestList","message","Please Select One District");
// 			}
// 			if(lbType.equals("I") && lbForm.getLgd_LBInterCAreaDestList() == null)
// 			{
// 				errors.rejectValue("lgd_LBInterCAreaDestList","message","Please Select One Sub District");
// 			}
// 			if(lbType.equals("V") && lbForm.getLgd_LBVillageCAreaDestL() == null)
// 			{
// 				errors.rejectValue("lgd_LBVillageCAreaDestL","message","Please Select One Village");
// 			}
// 		}*/
// 		
// 	/*	if (lbForm.getLgd_LBTypeName().contains("D") && lbForm.getLgd_LBDistCAreaDestList() == null) 
// 		{
// 			errors.rejectValue("lgd_LBDistCAreaDestList","message","Please Select One District");
// 		}*/
// 		/*if (lbForm.getLgd_LBTypeName().contains("D") && lbForm.getLgd_LBSubDistrictDestLatDCA() == null) 
// 		{
// 			errors.rejectValue("lgd_LBSubDistrictDestLatDCA","message","Please Select One Sub District");
// 		}
// 		if (lbForm.getLgd_LBTypeName().contains("D") && lbForm.getLgd_LBVillageDestLatDCA() == null) 
// 		{
// 			errors.rejectValue("lgd_LBVillageDestLatDCA","message","Please Select One Village");
// 		}*/
// 	
// 	/*	if (lbForm.getLgd_LBTypeName().contains("I") && lbForm.getLgd_LBInterCAreaDestList() == null) 
// 		{
// 			errors.rejectValue("lgd_LBInterCAreaDestList","message","Please Select One Sub District");
// 		}*/
// 		
// 		/*if (lbForm.getLgd_LBTypeName().contains("I") && lbForm.getLgd_LBVillageDestLatICA() == null) 
// 		{
// 			errors.rejectValue("lgd_LBVillageDestLatICA","message","Please Select One Village");
// 		}*/
// 		
// 	/*	if (lbForm.getLgd_LBTypeName().contains("V") && lbForm.getLgd_LBVillageCAreaDestL() == null) 
// 		{
// 			errors.rejectValue("lgd_LBVillageCAreaDestL","message","Please Select One Village");
// 		}*/
// 	
// 		
// 	/*	if (lbForm.getLgd_lbCategory().contentEquals("U")) {
// 			if (lbForm.getLgd_LBSubDistrictList() == null) {
// 				errors.rejectValue("lgd_LBSubDistrictList", "error.CONTRIBUTINGSUBDISTRICTLISTISREQUIRED",
// 						"Field is required.");
// 			}
// 			if (lbForm.getLgd_LBTypeName().length() == 1) {
// 				errors.rejectValue("lgd_LBTypeName",
// 						"error.SOURCESELECTLOCALBODYTYPE", "Field is required.");
// 			}
// 		} else {
// 			if (lbForm.getLgd_LBTypeName().length() == 1) {
// 				errors.rejectValue("lgd_LBTypeName",
// 						"error.SOURCESELECTLOCALBODYTYPE", "Field is required.");
// 			}
// 			if (lbForm.getLgd_LBDistCAreaDestList() == null
// 					&& (lbForm.getLgd_LBTypeName().contains("D") || lbForm
// 							.getLgd_LBTypeName().contains("T"))) {
// 				errors.rejectValue("lgd_LBDistCAreaDestList", "error.CONTRIBUTINGDISTRICTLISTISREQUIRED",
// 						"Field is required.");
// 			}
// 
// 			if (lbForm.getLgd_LBInterCAreaDestList() == null
// 					&& (lbForm.getLgd_LBTypeName().contains("I"))) {
// 				errors.rejectValue("lgd_LBInterCAreaDestList", "error.CONTRIBUTINGSUBDISTRICTLISTISREQUIRED",
// 						"Field is required.");
// 			}
// 
// 			if (lbForm.getLgd_LBVillageCAreaDestL() == null
// 					&& lbForm.getLgd_LBTypeName().contains("V")) {
// 				errors.rejectValue("lgd_LBVillageCAreaDestL", "error.CONTRIBUTINGVILLAGEISREQUIRED",
// 						"Field is required.");
// 			}
// 		}*/
// 
// 		/*if (lbForm.getLgd_lbCategory().contentEquals("P")
// 				|| lbForm.getLgd_lbCategory().contentEquals("T")) {
// 			try {
// 				boolean value = validateWardTypeList(lbForm);
// 				if (!value)
// 					throw new Exception();
// 				// When User Select Only District Panchayat List
// 				if (lbForm.getLocalBodyNameEnglishList() != null
// 						&& !lbForm.getLocalBodyNameEnglishList().trim()
// 								.isEmpty()
// 						&& Integer.parseInt(lbForm
// 								.getLocalBodyNameEnglishList()) > 0) {
// 					boolean checkDistList = validateWardDistP(lbForm);
// 					if (!checkDistList)
// 						throw new Exception();
// 				}
// 				// When User Select District and Intermediate Panchayat List
// 				if (lbForm.getLgd_LBDistListAtInterCA() != null
// 						&& !lbForm.getLgd_LBDistListAtInterCA().trim()
// 								.isEmpty()
// 						&& Integer
// 								.parseInt(lbForm.getLgd_LBDistListAtInterCA()) > 0) {
// 					boolean checkDistList = validateWardDistP(lbForm);
// 					if (!checkDistList)
// 						throw new Exception();
// 				}
// 				if (lbForm.getLgd_LBInterPSourceList() != null
// 						&& !lbForm.getLgd_LBInterPSourceList().trim().isEmpty()
// 						&& Integer.parseInt(lbForm.getLgd_LBInterPSourceList()) > 0) {
// 					boolean checkInterList = validateDwrWardlist(
// 							lbForm.getLgd_LBDistListAtInterCA(),
// 							lbForm.getLgd_LBInterPSourceList());
// 					if (!checkInterList)
// 						throw new Exception();
// 				}
// 				// //When User Select District, Intermediate and Village
// 				// Panchayat List
// 				if (lbForm.getLgd_LBDistListAtVillageCA() != null
// 						&& !lbForm.getLgd_LBDistListAtVillageCA().trim()
// 								.isEmpty()
// 						&& Integer.parseInt(lbForm
// 								.getLgd_LBDistListAtVillageCA()) > 0) {
// 					boolean checkDistList = validateWardDistP(lbForm);
// 					if (!checkDistList)
// 						throw new Exception();
// 				}
// 				if (lbForm.getLgd_LBInterListAtVillageCA() != null
// 						&& !lbForm.getLgd_LBInterListAtVillageCA().trim()
// 								.isEmpty()
// 						&& Integer.parseInt(lbForm
// 								.getLgd_LBInterListAtVillageCA()) > 0) {
// 					boolean checkInterList = validateDwrWardlist(
// 							lbForm.getLgd_LBDistListAtVillageCA(),
// 							lbForm.getLgd_LBInterListAtVillageCA());
// 					if (!checkInterList)
// 						throw new Exception();
// 				}
// 				if (lbForm.getLgd_LBVillageSourceAtVillageCA() != null
// 						&& !lbForm.getLgd_LBVillageSourceAtVillageCA().trim()
// 								.isEmpty()
// 						&& Integer.parseInt(lbForm
// 								.getLgd_LBVillageSourceAtVillageCA()) > 0) {
// 					boolean checkInterList = validateDwrWardlist(
// 							lbForm.getLgd_LBInterListAtVillageCA(),
// 							lbForm.getLgd_LBVillageSourceAtVillageCA());
// 					if (!checkInterList)
// 						throw new Exception();
// 				}
// 			} catch (Exception e) {
// 				throw e;
// 			}
// 		}*//* else if (lbForm.getLgd_lbCategory().contentEquals("U")) {
// 			try {
// 				boolean value = validateWardUrbanType(lbForm);
// 				if (!value)
// 					throw new Exception();
// 
// 				if (lbForm.getLb_lgdPanchayatName() != null
// 						&& !lbForm.getLb_lgdPanchayatName().trim().isEmpty()
// 						&& Integer.parseInt(lbForm.getLb_lgdPanchayatName()) > 0)
// 					validateDwrWardlist(lbForm.getLgd_LBTypeName(),
// 							lbForm.getLb_lgdPanchayatName());
// 
// 			} catch (Exception e) {
// 				throw e;
// 			}
// 		}*/
//  
// 	}
	
// TODO Remove unused code found by UCDetector
// 	public void validateWardforULB(Object target, Errors errors) throws Exception
// 	{
// 		LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
// 		boolean retValue=true;
// 		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
// 		String lbName=null;
// 		int lblc=0;
// 		String strName = lbForm.getWard_number();
// 		if(lbForm.getLb_lgdPanchayatName() !=null)
// 		{
// 			lbName=lbForm.getLb_lgdPanchayatName();
// 		}
// 		lblc=getLblc(lbName);
// 		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ward_Name", "msg","Ward name is required.");
// 		if (!lbForm.getWard_Name().isEmpty()) 
// 		{	
// 			if (!lbForm.getWard_Name().matches("^[a-zA-Z0-9 \\-\\/\\.\\(\\)]+$")) // will only matches alphabets
// 			{
// 				errors.rejectValue("ward_Name", "message","The Ward name contains invalid characters.");
// 			}
// 			
// 			if (!checkExistingWardName(lbForm.getWard_Name(),lblc))
// 			{
// 				try 
// 				{
// 					errors.rejectValue("ward_Name","Ward Name Exist");
// 				}
// 				catch (Exception e)
// 				{
// 					errors.rejectValue("ward_Name","Ward Name Exist");
// 				}
// 			}
// 			
// 			
// 		}	
// 		
// 		
// 	
// 		if (!lbForm.getWard_number().isEmpty()) 
// 		{
// 			if (!customValidator.checkforNumericandSlash(lbForm.getWard_number()))
// 			{
// 				errors.rejectValue("ward_number","error.valid.COMMONALPHANUMERICREQUIRED","LocalBody Name Local contains Alphabets only");
// 			}
// 			
// 			
// 			if (checkExistingWardNumber(strName,lblc))
// 			{
// 				try 
// 				{
// 					errors.rejectValue("ward_number","error.exist.lgd_wardNumber","Ward Number Already Exist");
// 				}
// 				catch (Exception e)
// 				{
// 					errors.rejectValue("ward_number","error.exist.lgd_wardNumber");
// 				}
// 			}
// 			
// 		}
// 
// 		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ward_number", "msg","Ward Number is required.");
// 
// 		if (!lbForm.getWard_NameLocal().isEmpty()) 
// 		{
// 			retValue=customValidator.validateSpecialCharacters(lbForm.getWard_NameLocal().trim());
// 			if(retValue==false)
// 			{
// 				errors.rejectValue("ward_NameLocal", "error.ms","LocalBody Name Local contains invalid characters");
// 			}
// 			
// 		}
// 		
// 		
// 		if (lbForm.getLgd_LBTypeName().contains("I") && lbForm.getLgd_LBSubDistrictList() == null) 
// 		{
// 			errors.rejectValue("lgd_LBSubDistrictList","message","Please Select One Sub District");
// 		}
//  
// 	}

// TODO Remove unused code found by UCDetector
// 	public boolean validateWardTypeList(LocalGovtBodyForm lbForm)
// 			throws Exception {
// 		try {
// 			List<LocalbodyforStateWise> localBodyTypelist = lbForm
// 					.getLocalBodyTypelist();
// 			String lbTypeName[] = lbForm.getLgd_LBTypeName().split(":");
// 			for (LocalbodyforStateWise lbStateW : localBodyTypelist) {
// 				if (lbTypeName.length > 1 && lbStateW.getLocalBodyTypeCode() == Integer.parseInt(lbTypeName[0])	&& lbStateW.getLevel().contentEquals(lbTypeName[1])){
// 					return true;
// 				}	
// 			}
// 		} catch (Exception e) {
// 			throw e;
// 		}
// 		return false;
// 	}

// TODO Remove unused code found by UCDetector
// 	public boolean validateWardDistP(LocalGovtBodyForm lbForm) {
// 		try {
// 			List<LocalbodyListbyState> districtPanchayatList = lbForm
// 					.getDistrictPanchayatList();
// 
// 			for (LocalbodyListbyState lbStateW : districtPanchayatList) {
// 				if (lbForm.getLocalBodyNameEnglishList() != null
// 						&& !lbForm.getLocalBodyNameEnglishList().isEmpty()
// 						&& lbStateW.getLocalBodyCode() == Integer
// 								.parseInt(lbForm.getLocalBodyNameEnglishList())) {
// 					return true;
// 				} else if (lbForm.getLgd_LBDistListAtInterCA() != null
// 						&& !lbForm.getLgd_LBDistListAtInterCA().isEmpty()
// 						&& lbStateW.getLocalBodyCode() == Integer
// 								.parseInt(lbForm.getLgd_LBDistListAtInterCA())) {
// 					return true;
// 				} else if (lbForm.getLgd_LBDistListAtVillageCA() != null
// 						&& !lbForm.getLgd_LBDistListAtVillageCA().isEmpty()
// 						&& lbStateW.getLocalBodyCode() == Integer
// 								.parseInt(lbForm.getLgd_LBDistListAtVillageCA())) {
// 					return true;
// 				}
// 			}
// 
// 		} catch (Exception e) {
// 			log.debug("Exception" + e);
// 		}
// 		return false;
// 	}

	/*private boolean validateDwrWardlist(String entityParentCode,
			String entityCode) throws Exception {
		try {

			boolean check = true;//comboFillingService.getComboValuesforAppforward('L', "L",Integer.parseInt(entityParentCode.split(":")[0]),Integer.parseInt(entityCode.split(":")[0]));
			return check;

		} catch (Exception e) {
			throw e;
		}
 	}
	
	private boolean checkExistingWardNumber(String wardNumber,int lbName) throws Exception 
	{
		try 
		{
			int count = localGovtBodyDAO.getRecordsforWardNumber(wardNumber,lbName);
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
	}*/
	
	/*private int getLblc(String localbodyCd) throws Exception 
	{
		int lblcCd=0;
		try
		{
			lblcCd=localGovtBodyDAO.getlblc(Integer.parseInt(localbodyCd));
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		return lblcCd;
	}*/
	
	private boolean checkExistingWardNumberModify(String wardNumber,int lblc) throws Exception 
	{
		try 
		{
			int count = localGovtBodyDAO.getRecordsforWardNumberModify(wardNumber,lblc);
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
	
// TODO Remove unused code found by UCDetector
// 	public boolean validateWardUrbanType(LocalGovtBodyForm lbForm)
// 			throws Exception {
// 		try {
// 			List<LocalbodyforStateWise> localBodyTypelist = lbForm
// 					.getLocalBodyTypelist();
// 
// 			for (LocalbodyforStateWise lbStateW : localBodyTypelist) {
// 				if (lbStateW.getLocalBodyTypeCode() == Integer.parseInt(lbForm.getLgd_LBTypeName().indexOf(":")!=-1?lbForm.getLgd_LBTypeName().split(":")[0]:lbForm.getLgd_LBTypeName())){
// 					return true;
// 				}	
// 			}
// 		} catch (Exception e) {
// 			throw e;
// 		}
// 		return false;
// 	}
	
	private boolean checkExistingWardName(String wardName,int lblc) throws Exception 
	{
		boolean status=true;
		Integer checkstatus=0;
		try 
		{
			 checkstatus = localGovtBodyDAO.WardExist(lblc,wardName,1);
			 if(checkstatus==0){
				 status=true;
			 }else{
				 status=false;
			 }
			

		} 
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		return status;
	}
	
	/*Added by kirandeep on 16/03/2015*/
	
// TODO Remove unused code found by UCDetector
// 	public void validateWardDetails(Object target, Errors errors) throws Exception
// 	{
// 		LocalGovtBodyForm lbForm = (LocalGovtBodyForm) target;
// 
// 		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
// 		
// 	//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ward_Name", "msg","Ward name is required.");
// 		if (!lbForm.getWard_Name().isEmpty()) 
// 		{	
// 			if (!lbForm.getWard_Name().matches("^[a-zA-Z0-9 \\-\\/\\.\\(\\)]+$")) // will only matches alphabets
// 			{
// 				errors.rejectValue("ward_Name", "message","The Ward name contains invalid characters.");
// 			}			
// 			
// 		}	
// 		if (!lbForm.getWard_number().isEmpty()) 
// 		{
// 			if (!customValidator.checkforNumericandSlash(lbForm.getWard_number()))
// 			{
// 				errors.rejectValue("ward_number","error.valid.COMMONALPHANUMERICREQUIRED","Ward Name Local contains Alphabets only");
// 			}			
// 			
// 		}
// 			
// 		
// 		
// 	}
}
