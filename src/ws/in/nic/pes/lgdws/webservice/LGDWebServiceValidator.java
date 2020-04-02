package ws.in.nic.pes.lgdws.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.utils.ApplicationConstant;
import ws.in.nic.pes.lgdws.services.WSService;

public class LGDWebServiceValidator implements Validator{ // NO_UCD (use default)
	
	@Autowired
	private WSService wsServiceImpl;
	
	
	

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String validateEntityWithoutOutputFormat(LGDWebServiceForm lgdWebServiceForm) throws Exception { // NO_UCD (use default)
		StringBuffer errorMsg=null;
		/*if(!checkKeyAuthorization(lgdWebServiceForm.getAuthKey())){
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append("You are not Authorized User");
		}else{*/
			/*if(checkOutputFormat(lgdWebServiceForm.getOutputFormat())){
				errorMsg=new StringBuffer();
				errorMsg.append("Output format must be xml or json only");
			}*/
			
			
			if(lgdWebServiceForm.getEntityCode()!=null){
				if(!isInteger(lgdWebServiceForm.getEntityCode())){
					errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
					errorMsg.append(WordUtils.capitalizeFully(getNameofEntity(lgdWebServiceForm.getEntityType().charAt(0))));
					errorMsg.append(" Code value must be numeric[0-9] only");
				}else{
					if(!wsServiceImpl.checkValidEntityCode(Integer.parseInt(lgdWebServiceForm.getEntityCode()),getNameofEntity(lgdWebServiceForm.getEntityType().charAt(0)))){
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Entered ");
						errorMsg.append(WordUtils.capitalizeFully(getNameofEntity(lgdWebServiceForm.getEntityType().charAt(0))));
						errorMsg.append(" Code ");
						errorMsg.append(lgdWebServiceForm.getEntityCode());
						errorMsg.append(" does not exist in LGD");
						
						
					}
				}
			}
		/*}*/
		return errorMsg!=null?errorMsg.toString():null;
	}
	
	
	public String validateEntity(LGDWebServiceForm lgdWebServiceForm) throws Exception { // NO_UCD (use default)
		StringBuffer errorMsg=null;
		/*if(!checkKeyAuthorization(lgdWebServiceForm.getAuthKey())){
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append("You are not Authorized User");
		}else{*/
			
		
		
			if(lgdWebServiceForm.getOutputFormat()!=null && checkOutputFormat(lgdWebServiceForm.getOutputFormat())){
				errorMsg=new StringBuffer();
				errorMsg.append("Output format must be xml or json only");
			}
			
			
			if(lgdWebServiceForm.getEntityCode()!=null){
				if(!isInteger(lgdWebServiceForm.getEntityCode())){
					errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
					errorMsg.append(WordUtils.capitalizeFully(getNameofEntity(lgdWebServiceForm.getEntityType().charAt(0))));
					errorMsg.append(" Code value must be numeric[0-9] only");
				}else{
					if(!wsServiceImpl.checkValidEntityCode(Integer.parseInt(lgdWebServiceForm.getEntityCode()),getNameofEntity(lgdWebServiceForm.getEntityType().charAt(0)))){
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Entered ");
						errorMsg.append(WordUtils.capitalizeFully(getNameofEntity(lgdWebServiceForm.getEntityType().charAt(0))));
						errorMsg.append(" Code ");
						errorMsg.append(lgdWebServiceForm.getEntityCode());
						errorMsg.append(" does not exist in LGD");
						
						
					}
				}
			}
		/*}*/
		return errorMsg!=null?errorMsg.toString():null;
	}
	
	
	public String validateLbTypeCode(LGDWebServiceForm lgdWebServiceForm)throws Exception{
		StringBuffer errorMsg=null;
		String error=validateEntity(lgdWebServiceForm);
		
		if(lgdWebServiceForm.getLbTypeCode() != null) {
			if(!isInteger(lgdWebServiceForm.getLbTypeCode())){
				errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
				errorMsg.append("Localbody Type Code value must be numeric[0-9] only");
			}else{
				if(!wsServiceImpl.validateLbTypeCode(Integer.parseInt(lgdWebServiceForm.getLbTypeCode()))){
					errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
					errorMsg.append("Entered localbody type code ");
					errorMsg.append(lgdWebServiceForm.getLbTypeCode());
					errorMsg.append(" does not exist in LGD");
					
					
				}
			}
		}
		
		if(error!=null){
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append(" and ");
			errorMsg.append(error);
		}
		
		return errorMsg!=null?errorMsg.toString():null;
	}
	
	public String showOutputEmptyError(LGDWebServiceForm lgdWebServiceForm) throws Exception{
		StringBuffer errorMsg=new StringBuffer();
		errorMsg.append(WordUtils.capitalizeFully(getNameofEntity(lgdWebServiceForm.getOutputType().charAt(0))));
		errorMsg.append(" are not available for provided ");
		errorMsg.append(WordUtils.capitalizeFully(getNameofEntity(lgdWebServiceForm.getEntityType().charAt(0))));
		errorMsg.append(" Code "+lgdWebServiceForm.getEntityCode());
		return errorMsg.toString();
	}
	
	
	public String showOutputEmptyErrorforOrganization()throws Exception{
		return "No organization found for given parameters.";
	}
	
	
	
	
	
/*	public String validateStateList(LGDWebServiceForm lgdWebServiceForm) throws Exception { // NO_UCD (use default)
		StringBuffer errorMsg=null;
		if(!checkKeyAuthorization(lgdWebServiceForm.getAuthKey())){
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append("You are not Authorized User");
		}else{
			if(checkOutputFormat(lgdWebServiceForm.getOutputFormat())){
				errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
				errorMsg.append("Output format must be xml or json only");
			}
			
		}
		
		return errorMsg!=null?errorMsg.toString():null;
	}
	
	public String validateDistrictList(LGDWebServiceForm lgdWebServiceForm) throws Exception { // NO_UCD (use default)
		StringBuffer errorMsg=null;
		if(!checkKeyAuthorization(lgdWebServiceForm.getAuthKey())){
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append("You are not Authorized User");
		}else{
			if(checkOutputFormat(lgdWebServiceForm.getOutputFormat())){
				errorMsg=new StringBuffer();
				errorMsg.append("Output format must be xml or json only");
			}
			
			if(!isInteger(lgdWebServiceForm.getEntityCode())){
				errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
				errorMsg.append("State Code value must be mumbric[0-9] only");
			}else{
				if(!wsServiceImpl.checkValidStateCode(Integer.parseInt(lgdWebServiceForm.getEntityCode()))){
					errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
					errorMsg.append("Enter stateCode does not Exist in LGD");
				}
			}
		}
		return errorMsg!=null?errorMsg.toString():null;
	}
	
	public String validateDistrictDetails(LGDWebServiceForm lgdWebServiceForm) throws Exception {
		StringBuffer errorMsg=null;
		if(!checkKeyAuthorization(lgdWebServiceForm.getAuthKey())){
		errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
		errorMsg.append("You are not Authorized User");
	}else{
		if(checkOutputFormat(lgdWebServiceForm.getOutputFormat())){
			errorMsg=new StringBuffer();
			errorMsg.append("Output format must be xml or json only");
		}
		
		if(!isInteger(lgdWebServiceForm.getStateCode())){
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append("State Code value must be mumbric[0-9] only");
		}else{
			if(!wsServiceImpl.checkValidStateCode(Integer.parseInt(lgdWebServiceForm.getStateCode()))){
				errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
				errorMsg.append("Enter stateCode does not Exist in LGD");
			}
		}
	}
		return errorMsg!=null?errorMsg.toString():null;
	}*/
	
	
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	private static boolean checkOutputFormat(String outputFormat) throws Exception{
		if(outputFormat!=null && !("xml|json").contains((outputFormat))){
			return true;
		}
		return false;
	}


	private static boolean checkKeyAuthorization(String authKey) throws Exception {
		if(authKey!=null){
		for(String key:ApplicationConstant.registerUserKeyList){
			if(authKey.equals(key)){
				return true;
			}
		}
		return false;
		}
		return false;
	}
	
	public static String getNameofEntity(Character entityType)throws Exception{
		String entityName=null;
		switch(entityType){
		case 'S': 
		entityName=WebServiceConstant.ENTITY_NAME_STATE.toString();
		break;
		case 'D': 
			entityName=WebServiceConstant.ENTITY_NAME_DISTRICT.toString();
			break;
		case 'T': 
			entityName=WebServiceConstant.ENTITY_NAME_SUBDISTRICT.toString();
			break;
		case 'B': 
			entityName=WebServiceConstant.ENTITY_NAME_BLOCK.toString();
			break;
		case 'V': 
			entityName=WebServiceConstant.ENTITY_NAME_VILLAGE.toString();
			break;
		case 'L': 
			entityName=WebServiceConstant.ENTITY_NAME_LOCALBODY.toString();
			break;	
		}
		return entityName;
	}
	
	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private static boolean isBoolean(String s) {
	if(WebServiceConstant.STRING_BOOLEAN_FALSE.toString().equals(s) || WebServiceConstant.STRING_BOOLEAN_TRUE.toString().equals(s)){
			 return true;
		}
		return false;
	   
	}
	
	public String validateOganization(LGDWebServiceOrgForm lgdWebServiceOrgForm)throws Exception{
		StringBuffer errorMsg=null;
		if(checkOutputFormat(lgdWebServiceOrgForm.getOutputFormat())){
			errorMsg=new StringBuffer();
			errorMsg.append("Output format must be xml or json only");
		}
		
		if(!WebServiceConstant.NULL_STRING.toString().equals(lgdWebServiceOrgForm.getOrgTypeCode())){
			 if(!isInteger(lgdWebServiceOrgForm.getOrgTypeCode())){
					errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
					errorMsg.append("Organization Type Code value must be numeric[0-9] only");
				}else{
					if(!wsServiceImpl.validateOrgTypeCode(Integer.parseInt(lgdWebServiceOrgForm.getOrgTypeCode()))){
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Entered Organization type code ");
						errorMsg.append(lgdWebServiceOrgForm.getOrgTypeCode());
						errorMsg.append(" does not exist in LGD");
						
						
					}
				}
		}
		
		
		if(!isBoolean(lgdWebServiceOrgForm.getIsCenter())){
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append("isCenter value must be boolean[true,false] only");
		}
		
		
		
		if(!isInteger(lgdWebServiceOrgForm.getStateCode())){
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append(WordUtils.capitalizeFully(getNameofEntity('S')));
			errorMsg.append(" Code value must be numeric[0-9] only");
		}else{
			if(!wsServiceImpl.checkValidEntityCode(Integer.parseInt(lgdWebServiceOrgForm.getStateCode()),"state")){
				errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
				errorMsg.append("Entered ");
				errorMsg.append(WordUtils.capitalizeFully(getNameofEntity('S')));
				errorMsg.append(" Code ");
				errorMsg.append(lgdWebServiceOrgForm.getStateCode());
				errorMsg.append(" does not exist in LGD");
				
				
			}
		}
		
		return errorMsg!=null?errorMsg.toString():null;
	}
	
	public String validateDepartmentDesignation(String paramString,Integer olc) throws Exception { // NO_UCD (use default)
		StringBuffer errorMsg=null;
		
		if(wsServiceImpl.isOrganizationExist(olc)){
			CustomRegexValidator customValidator = CustomRegexValidator.getInstance();

			if (paramString != null && !"".equals(paramString)) {

				String[] designations = paramString.split("\\@@");
				int otherDesigLength = designations.length;
				for (int i = 0; i < otherDesigLength; i++) {
					String[] columns = designations[i].split("\\##");
					String designationName = columns[1];
					String designationNameLocal = columns[2];

					if (designationName == null || "".equals(designationName)) {
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Designation Name is required");
						
					}
					else if (designationName != null && !customValidator.checkforAlphabetWithSpaceDotSlashesandBrackets(designationName)) {
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Designation Name is allow only (a-z)(A-Z), Space, Dot and Brackets.");
						
					}else if (designationName.length() > 50) {
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Designation Name length must be less then and equal to 50 characters.");
						
					}
					if (designationNameLocal == null || "".equals(designationNameLocal)) {
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Designation Name Local is required");
						
					}else if (!customValidator.validateSpecialCharactersHyphen(designationNameLocal)) {
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Invalid Designation Name other in local.");
					}else if (designationNameLocal.length() > 50) {
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Designation Name Local length must be less then and equal to 50 characters.");
					}
					String locatedLevels = columns[3];
					String levels[] = locatedLevels.split(",");
					for(int j=0;j<levels.length;j++)
					{
						if(!(levels[j] == null || "".equals(levels[j]))){
							if(!wsServiceImpl.isOrgLocatedLevelCodeExist(olc, Integer.parseInt(levels[j]))){
								errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
								errorMsg.append("pass orgLocatedLevelCode "+levels[j]+" not exist in pass organization in LGD");
							}
						}
					}
					

				}
			}
			else
			{
				errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
				errorMsg.append("Please Enter atleast one designation detail !");
				
			}
		}else{
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append("Organization does not exist in LGD");
		}
		
		
			return errorMsg!=null?errorMsg.toString():null;
	}
	
	public String validateDepartmentDesignationReorder(String paramString,Integer olc,Integer orgLocatedLevelCode) throws Exception { // NO_UCD (use default)
		StringBuffer errorMsg=null;
		
		if(wsServiceImpl.isOrganizationExist(olc)){
			

			if (paramString != null && !"".equals(paramString)) {

				Scanner scanner = new Scanner(paramString);
				scanner.useDelimiter("##");
				List<Integer> desiginationCodeList=new ArrayList<Integer>();
				int desigCount=0;
				while(scanner.hasNext()){
					desiginationCodeList.add(Integer.parseInt(scanner.next()));
					desigCount++;
				}
				if(!(desigCount==wsServiceImpl.isDesinationListExist(desiginationCodeList, olc))){
					errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
					errorMsg.append("Pass designation code does not exist in LGD!");
				}
				
			}
			else
			{
				errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
				errorMsg.append("Please Enter atleast one designation detail !");
				
			}
			
			if(!( wsServiceImpl.isOrgLocatedLevelCodeExist(olc, orgLocatedLevelCode))){
				errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
				errorMsg.append("Pass orgLocatedLevelCode does not exist in LGD!");
			}
			
			
		}else{
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append("Organization does not exist in LGD!");
		}
		
		
			return errorMsg!=null?errorMsg.toString():null;
	}
	
	
	public Boolean validateGetHierarchyOfEntity(LGDWebServiceForm lgdWebServiceForm) throws Exception { // NO_UCD (use default)
		StringBuffer errorMsg=null;
		Boolean flage =false;
		/*if(!checkKeyAuthorization(lgdWebServiceForm.getAuthKey())){
			errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
			errorMsg.append("You are not Authorized User");
		}else{*/
			
		
		
			if(lgdWebServiceForm.getOutputFormat()!=null && checkOutputFormat(lgdWebServiceForm.getOutputFormat())){
				errorMsg=new StringBuffer();
				errorMsg.append("Output format must be xml or json only");
				flage=true;
			}
			
			
			if(lgdWebServiceForm.getEntityCode()!=null){
				if(!isInteger(lgdWebServiceForm.getEntityCode())){
					errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
					errorMsg.append(WordUtils.capitalizeFully(getNameofEntity(lgdWebServiceForm.getEntityType().charAt(0))));
					errorMsg.append(" Code value must be numeric[0-9] only");
				}else{
					flage=true;
					/*if(!wsServiceImpl.checkValidEntityCode(Integer.parseInt(lgdWebServiceForm.getEntityCode()),lgdWebServiceForm.getEntityType().charAt(0))){
						errorMsg=errorMsg!=null?errorMsg:(errorMsg=new StringBuffer());
						errorMsg.append("Entered ");
						errorMsg.append(WordUtils.capitalizeFully(getNameofEntity(lgdWebServiceForm.getEntityType().charAt(0))));
						errorMsg.append(" Code ");
						errorMsg.append(lgdWebServiceForm.getEntityCode());
						errorMsg.append(" does not exist in LGD");
						
						
					}*/
				}
			}
		/*}*/
		return flage;
	}
}
