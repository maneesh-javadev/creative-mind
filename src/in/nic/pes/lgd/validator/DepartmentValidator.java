package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.dao.OrganizationDAO;
import in.nic.pes.lgd.forms.DepartmentAdminForm;
import in.nic.pes.lgd.forms.DepartmentForm;
import in.nic.pes.lgd.forms.OrganizationTypeForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("departmentValidator")
public class DepartmentValidator implements Validator {
	private static final Logger LOG = Logger.getLogger(DepartmentValidator.class);
	
	@Autowired
	private OrganizationDAO organizationDAO;

	public boolean supports(Class<?> clazz) {
		return DepartmentForm.class.isAssignableFrom(clazz);
	}
	
	/* modified by sushil on 21-05-2013*/

	public void validateViewDepartment(Object object, Errors errors) {
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		DepartmentForm departmentForm = (DepartmentForm) object;

		String strName = departmentForm.getDeptNamecr();
		if (departmentForm.getDeptNamecr() != null && !departmentForm.getDeptNamecr().isEmpty()) {
			if (!customValidator.checkforAlphabetsandSpace(strName)) {
				errors.rejectValue("deptNamecr", "error.invalid.format");
			} 
			if (departmentForm.getDeptNamecr().length() > 200) {
				errors.rejectValue("deptNamecr", "error.DeptName");
			}
		}
		
		String deptNameLc=departmentForm.getDeptNameLocal();
		if ( deptNameLc!= null ) {
			if(!deptNameLc.trim().isEmpty())
			{
				if (!customValidator.validateSpecialCharacters(departmentForm.getDeptNameLocal())) {
					errors.rejectValue("deptNameLocal", "error.invalid.format");
				}
			}
			
		} 
			
		/*try {
			if (checkExistingDepartment(departmentForm.getDeptNamecr(), departmentForm.getOrgLevel())) {
				errors.rejectValue("deptNamecr", "error.DeptName");
			}
		} catch (Exception e) {
			errors.rejectValue("deptNamecr", "error.DeptName");
		}*/
				
		String strShortName = departmentForm.getShortDeptName();
		if (strShortName != null) {
			if(!strShortName.trim().isEmpty())
			{
				if (!customValidator.checkforShortName(strShortName)) {
					errors.rejectValue("shortDeptName", "error.common.short.name");
				}	
			}
			
		}
	}
	
	public void checkExistingOrgnizationNameforView(Integer stateCode, char level, OrganizationTypeForm orgTypeForm, Errors errors)  {

		try {
			String deptNamech = orgTypeForm.getOrgName();
			String deptName = orgTypeForm.getOrgNamech();
			String delptNameLc = orgTypeForm.getOrgNameLocal();
			String shortName = orgTypeForm.getShortName();
			CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
			if (deptNamech != null) {
				if (deptNamech != "") {
					boolean flag = organizationDAO.checkExistNameDeparmentModify(stateCode, level, deptName, deptNamech);
					if (flag == false) {
						errors.rejectValue("orgName", "error.DeptNameDuplicate");
					}
					
					
					boolean ckeckfornumber = checkForNumeric(deptNamech);
					if (ckeckfornumber) {
						errors.rejectValue("orgName","error.NumberNotAllowed","Numbers Not Allowed");
						return;
					}

					boolean checkforspecialchar = validateSpecialCharacters(deptNamech);
					if (checkforspecialchar) {
						errors.rejectValue("orgName","error.SpecialCharNotAllowed ","Special Characters Not Allowed");
						return;
					}
					
					/*if (!customValidator.checkforAlphabetsandSpaceBrackets(deptNamech)) {
						errors.rejectValue("orgName", "error.DeptNameContainAZ");
					}*/
				} else {
					errors.rejectValue("orgName", "error.DeptNameBlank");
				}
			} else {
				errors.rejectValue("orgName", "error.DeptNameBlank");
			}

			if (delptNameLc != "" && delptNameLc != null) {
				if (!customValidator.validateSpecialCharacters(delptNameLc)) {
					errors.rejectValue("orgNameLocal", "error.invalid.format");
				}
			}

			if (shortName != "" && shortName != null) {
				if (!customValidator.checkforShortName(shortName)) {
					errors.rejectValue("shortName", "error.common.short.name");
				}
			}
		} catch (Exception e) {
			throw new HibernateException(e);
		}
	}
	
	
	public void validateforState(Object object, Errors errors) {

		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		DepartmentForm departmentForm = (DepartmentForm) object;

		String strName = departmentForm.getDeptName();
		if (departmentForm.getDeptName() != null
				&& !departmentForm.getDeptName().isEmpty()) {
			if (!customValidator.checkforAlphabetsandSpace(strName)) {
				errors.rejectValue("deptName", "Please input value in correct format");
			}
			if (departmentForm.getDeptName().length() > 60) {
				errors.rejectValue("deptName", "error.DeptName");
			}
			/*if(departmentForm.getStateName()!=null)
			{
				try {
					if(organizationDAO.checkExistNameDeparment(Integer.parseInt(departmentForm.getStateName()),departmentForm.getSpecificLevel().charAt(0),strName))
					{
						errors.rejectValue("deptName", "error.DeptNameDuplicate");
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					errors.rejectValue("deptName", "error.DeptNameDuplicate");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errors.rejectValue("deptName", "error.DeptNameDuplicate");
				}
			}*/
		}

		if (departmentForm.getDeptNameLocal() != null
				&& !departmentForm.getDeptNameLocal().isEmpty()) {
			if (!customValidator.validateSpecialCharacters(departmentForm
					.getDeptNameLocal())) {
				errors.rejectValue("deptNameLocal", "Please input value in correct format");
			}
		}

		try {
			if (checkExistingOrgName(departmentForm.getDeptName(), 2)) {
				errors.rejectValue("deptName", "error.DeptName");
			}
		} catch (Exception e) {
			errors.rejectValue("deptName", "error.DeptName");
		}

		String strShortName = departmentForm.getShortDeptName();
		if (strShortName != null && !strShortName.isEmpty()) {
			if (!customValidator.checkforShortName(strShortName)) {
				errors.rejectValue("shortDeptName", "error.common.short.name");
			}
		}
		
		/*String specifyLevel=departmentForm.getSpecificLevel();
		
		if(specifyLevel!=null)
		{
			if(specifyLevel.length()<=0)
			{
				errors.rejectValue("specificLevel", "Please Select One of Level atleast");
			}
			
		}
		else
		{
			errors.rejectValue("specificLevel", "Please Select One of Level atleast");
		}*/
	}
	
	public void validateforLowLevel(Object object, Errors errors) {

		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		DepartmentForm departmentForm = (DepartmentForm) object;

		String strName = departmentForm.getDeptName();
		if (departmentForm.getDeptName() != null
				&& !departmentForm.getDeptName().isEmpty()) {
			if (!customValidator.checkforAlphabetsandSpace(strName)) {
				errors.rejectValue("deptName", "error.invalid.format");
			}
			if (departmentForm.getDeptName().length() > 60) {
				errors.rejectValue("deptName", "error.DeptName");
			}
		}

		if (departmentForm.getDeptNameLocal() != null
				&& !departmentForm.getDeptNameLocal().isEmpty()) {
			if (!customValidator.validateSpecialCharacters(departmentForm
					.getDeptNameLocal())) {
				errors.rejectValue("deptNameLocal", "error.invalid.format");
			}
		}

		try {
			if (checkExistingOrgName(departmentForm.getDeptName(), 2)) {
				errors.rejectValue("deptName", "error.DeptName");
			}
		} catch (Exception e) {
			errors.rejectValue("deptName", "error.DeptName");
		}

		String strShortName = departmentForm.getShortDeptName();
		if (strShortName != null && !strShortName.isEmpty()) {
			if (!customValidator.checkforShortName(strShortName)) {
				errors.rejectValue("shortDeptName", "error.common.short.name");
			}
		}
		
		String parent=departmentForm.getParent();
		if(parent!=null)
		{
			if(parent.length()<=0)
			{
				errors.rejectValue("parent", "Kindly select the Parent from the list");
			}
		}
		String districtList=departmentForm.getDistrictName();
		String levelRadio=departmentForm.getLevelradio();
		
		if(levelRadio.equalsIgnoreCase("P"))
		{
			if(districtList!=null)
			{
				if(districtList.length()<=0)
				{
					errors.rejectValue("districtName", "Kindly select District from the list.");
				}
			}
		}
	}
	
	/* added by sushil on 11-05-2013*/
	@Override
	public void validate(Object object, Errors errors) {
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		DepartmentForm departmentForm = (DepartmentForm) object;

		if(errors.getObjectName() != null && "createOrganization".equalsIgnoreCase(errors.getObjectName())) {
			if (departmentForm.getMinistryName() == null) {
				errors.rejectValue("ministryName", "error.select.MINISTRYFRMLIST");
			}
		}
		
		/* for organization only rest all for department*/
		if(errors.getObjectName() != null && "createOrganization".equalsIgnoreCase(errors.getObjectName())) {
			if (departmentForm.getDeptOrgCode() == null) {
				errors.rejectValue("deptOrgCode", "error.select.DEPTFRMLIST");
			} 
			if (departmentForm.getOrgType() == null) {
				errors.rejectValue("orgType", "error.select.ORGTYPEFRMLIST");
			} 
		}
		
		String strName = departmentForm.getDeptName();
		if (departmentForm.getDeptName() != null && !departmentForm.getDeptName().isEmpty()) {
			if (!customValidator.checkforAlphabetsandSpace(strName)) {
				errors.rejectValue("deptName", "error.invalid.format");
			} 
			if (departmentForm.getDeptName().length() > 60) {
				errors.rejectValue("deptName", "error.DeptName");
			}
		}
		
		if (departmentForm.getDeptNameLocal() != null && !departmentForm.getDeptNameLocal().isEmpty()) {
			if (!customValidator.validateSpecialCharacters(departmentForm.getDeptNameLocal())) {
				errors.rejectValue("deptNameLocal", "error.invalid.format");
			}
		} 
		/* Added by sushil on 29-05-2013*/
		if(departmentForm.getOrgLevel() == 'C') {
			try {
				if (checkExistingOrgName(departmentForm.getDeptName(), (departmentForm.getOrgType() != null ? Integer.valueOf(departmentForm.getOrgType()) : 0), departmentForm.getOrgLevel())) {
					errors.rejectValue("deptName", "error.DeptName");
				}
			} catch (Exception e) {
				errors.rejectValue("deptName", "error.DeptName");
			}
		}
		
		if(departmentForm.getOrgType() != null) {
			try {
				if (checkExistingOrgName(departmentForm.getDeptName(), Integer.valueOf(departmentForm.getOrgType()))) {
					errors.rejectValue("deptName", "error.DeptName");
				}
			} catch (Exception e) {
				errors.rejectValue("deptName", "error.DeptName");
			}
		} else if(departmentForm.getOrgLevel() != 0 && departmentForm.getOrgLevel() != 'C') {
			if (checkExistingOrganizationByLevel(departmentForm.getDeptName(), departmentForm.getOrgLevel())) {
				errors.rejectValue("deptName", "error.DeptName");
			}
		}
				
		String strShortName = departmentForm.getShortDeptName();
		if (strShortName != null && !strShortName.isEmpty()) {
			if (!customValidator.checkforShortName(strShortName)) {
				errors.rejectValue("shortDeptName", "error.common.short.name");
			}
		}
	}

	private boolean checkExistingOrgName(String orgName, int ... orgTypeCode) throws Exception {
		int count = organizationDAO.getRecordsforDeptValidation(orgName, orgTypeCode);
		boolean flag = false;
		if (count >= 1) {
			flag = true;
		} 
		return flag;
	}

	public void checkExistingOrgNameAUM(String orgName, int orgTypeCode, Errors errors, int stateCode) {

		try {
			if(orgName.length()>60){
				errors.rejectValue("deptName", "error.DeptNameSize");
			}
			int count = organizationDAO.getRecordsforDeptValidation(orgName, orgTypeCode, stateCode);
			if (count >= 1) {
				errors.rejectValue("deptName", "error.DeptNameDuplicate");
			}
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
		} 
	}
	
	
	public void checkExistingDeptNameforView(Integer stateCode, char level, DepartmentForm modifyDepartmentCmd, Errors errors) throws Exception {
		String deptNamech = modifyDepartmentCmd.getDeptNamecr();
		String deptName = modifyDepartmentCmd.getDeptName();
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		if (deptNamech != null) {
			if (deptNamech != "") {
				boolean flag = organizationDAO.checkExistNameDeparmentModify(stateCode, level, deptName, deptNamech);
				if (flag == false) {
					errors.rejectValue("deptNamecr", "error.DeptNameDuplicate");
				}

				if (!customValidator.checkforAlphabetsandSpace(deptName)) {
					errors.rejectValue("deptNamecr", "error.DeptNameContainAZ");
				}
			} else {
				errors.rejectValue("deptNamecr", "error.DeptNameBlank");
			}
		} else {
			errors.rejectValue("deptNamecr", "error.DeptNameBlank");
		}

	}
	
	public void checkExistingDeptName(Integer stateCode, char level, String deptName, Errors errors) throws Exception {
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		if (deptName != null) {
			if (deptName != "") {
				boolean flag = organizationDAO.checkExistNameDeparment(stateCode, level, deptName);
				if (flag == false) {
					errors.rejectValue("deptName", "error.DeptNameDuplicate");
				}

				if (!customValidator.checkforAlphabetsandSpace(deptName)) {
					errors.rejectValue("deptName", "error.DeptNameContainAZ");
				}
			} else {
				errors.rejectValue("deptName", "error.DeptNameBlank");
			}
		} else {
			errors.rejectValue("deptName", "error.DeptNameBlank");
		}

	}
	
// TODO Remove unused code found by UCDetector
// 	public void viewValidateViewDepartment(Object object, Errors errors) {
// 		MinistryForm viewDept = (MinistryForm) object;
// 	
// 		 
// 	if(viewDept.getMinistryName() == null || viewDept.getMinistryName().isEmpty())
// 	{
// 		errors.rejectValue("ministryName", 
// 				"Please select Ministry");
// 	}
// 	else{
// 		if(viewDept.getMinistryName() != null && !viewDept.getMinistryName().isEmpty())
// 		{
// 			try {
// 				int code = Integer.parseInt(viewDept.getMinistryName());
// 				if(code <1)
// 				{
// 					errors.rejectValue("ministryName",
// 							"Please select Ministry");
// 				}
// 			} catch (Exception e) {
// 				errors.rejectValue("ministryName",
// 						"Please select Ministry");
// 			}
// 		}
// 	}
// 	
// /*	if(viewDept.getCategory() == null || viewDept.getCategory().isEmpty())
// 	{
// 		errors.rejectValue("category", "error.ms",
// 				"Please select Category");
// 	}
// 	else{
// 		if(viewDept.getCategory() != null || !viewDept.getCategory().isEmpty())
// 		{
// 			try {
// 				int code = Integer.parseInt(viewDept.getCategory());
// 				if(code <1)
// 				{
// 					errors.rejectValue("category", "error.ms",
// 							"Please select Category");
// 				}
// 			} catch (Exception e) {
// 				errors.rejectValue("category", "error.ms",
// 						"Please select Category");
// 			}
// 		}
// 	}*/
// 	
// /*	if(viewDept.getDistrictCode() <= 0)
// 	{
// 		errors.rejectValue("districtCode", "error.ms",
// 				"Please select Category");
// 	}
// 	else{
// 		if(viewDept.getDistrictCode() <= 0)
// 		{
// 			try {
// 				int code = viewDept.getDistrictCode();
// 				if(code <1)
// 				{
// 					errors.rejectValue("districtCode", "error.ms",
// 							"Please select Category");
// 				}
// 			} catch (Exception e) {
// 				errors.rejectValue("districtCode", "error.ms",
// 						"Please select Category");
// 			}
// 		}
// 	}*/
// }
	/**
	 * @author Sushil Shakya
	 * @since 20-5-2013
	 * @param object
	 * @param errors
	 */
	public void validateCenterOrganization(Object object, Errors errors) {
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		OrganizationTypeForm organizationForm = (OrganizationTypeForm) object;

		if (organizationForm.getOrgName() == null) {
			errors.rejectValue("orgName", "error.required.ORGNAME");
		}

		String strName = organizationForm.getOrgName();
		if (organizationForm.getOrgName() != null && !organizationForm.getOrgName().isEmpty()) {
			if (!customValidator.checkforAlphabetsandSpace(strName)) {
				errors.rejectValue("orgName", "error.invalid.format");
			}
			if (organizationForm.getOrgName().length() > 200) {
				errors.rejectValue("orgName", "error.required.ORGNAME");
			}
		}

		if (organizationForm.getOrgNameLocal() != null && !organizationForm.getOrgNameLocal().isEmpty()) {
			if (!customValidator.validateSpecialCharacters(organizationForm.getOrgNameLocal())) {
				errors.rejectValue("orgNameLocal", "error.invalid.format");
			}
		}

		String strShortName = organizationForm.getShortName();
		if (strShortName != null && !strShortName.isEmpty()) {
			if (!customValidator.checkforShortName(strShortName)) {
				errors.rejectValue("shortName", "error.common.short.name");
			}
		}

		try {
			if(organizationForm.getOrgNamech() != null && ! "".equals(organizationForm.getOrgNamech())) {
				if (checkExistingOrgName(organizationForm.getOrgName(), organizationForm.getOrgTypeCode())) {
					errors.rejectValue("orgName", "error.DeptName");
				}
			}
		} catch (Exception e) {
			errors.rejectValue("orgName", "error.DeptName");
		}
	}
	
	
	
	/**
	 * @author Sushil 
	 */
	private boolean checkExistingOrganizationByLevel(String orgName, char orgLevel) {
		return organizationDAO.checkExistingOrganizationByLevel(orgName, orgLevel);
	}
	 
	public void validateAdminUnitLevelEntity(DepartmentAdminForm departmentAdminForm,Errors error) 
	{
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		String entityType = departmentAdminForm.getEntityType();
		boolean entityExist=false;
		if(entityType.equals("levelNew"))
		{
		if(departmentAdminForm.getAdminLevelNameEnglish().equalsIgnoreCase(""))
		{
			
			error.rejectValue("adminLevelNameEng", "error.empty.ENTITYNAME");
		}
		else if(!customValidator.checkforAlphabetWithSpaceDotandSlash(departmentAdminForm.getAdminLevelNameEnglish()))
		{  
			error.rejectValue("adminLevelNameEng", "error.invalid.format");
		}
		entityExist=organizationDAO.adminUnitOradminChildExist(departmentAdminForm.getSlc(),departmentAdminForm.getAdminLevelNameEnglish(),0,'A',0);
		if(!entityExist)
		{
			
			error.rejectValue("adminLevelNameEng", "Administrative Unit already exists with same name");
		}
		if(!departmentAdminForm.getAdminLevelNameLocal().equalsIgnoreCase(""))
		if(!customValidator.validateSpecialCharacters(departmentAdminForm.getAdminLevelNameLocal()))
		{  
			error.rejectValue("adminLevelNameLocal", "error.invalid.format");	
		}
		
		/*modify by sunita on 10-07-2015 */
		if(departmentAdminForm.getParentAdminCode()==-1)
		{
			error.rejectValue("parentAdminCode", "errorMessage.notselectedoption");	
			
		}
        }
		else if(entityType.equals("entityNew"))
		{
          if(departmentAdminForm.getAdminLevelNameEnglish().equalsIgnoreCase(""))
		{
			
			error.rejectValue("adminEntityNameEnglish", "error.empty.ENTITYNAME");
		}
		else if(!customValidator.checkforAlphabetNumbericHypenSpace(departmentAdminForm.getAdminLevelNameEnglish()))
		{  
			error.rejectValue("adminEntityNameEnglish", "error.valid.COMMONALPHABETREQUIRED");
		}
         entityExist=organizationDAO.adminUnitOradminChildExist(departmentAdminForm.getSlc(),departmentAdminForm.getAdminLevelNameEnglish(),departmentAdminForm.getParentAdminUnitEntityCode(),'E',0);
  		if(!entityExist)
  		{
  			
  			error.rejectValue("adminEntityNameEnglish", "Administrative Entity already exists with same name");
  		}
  		if(!departmentAdminForm.getAdminEntityNameLocal().equalsIgnoreCase(""))
  			if(!customValidator.validateSpecialCharacters(departmentAdminForm.getAdminEntityNameLocal()))
  			{  
  				error.rejectValue("adminEntityNameLocal", "error.invalid.format");	
  			}
         if(departmentAdminForm.getAdminUnitLevelCode() == -1)
		{
			error.rejectValue("adminUnitLevelCode", "errorMessage.notselectedoption");	
			
		}
		}
		else if(entityType.equals("levelMod"))
		{
		if(departmentAdminForm.getAdminLevelNameEnglish().equalsIgnoreCase(""))
		{
			
			error.rejectValue("adminLevelNameEng", "error.empty.ENTITYNAME");
		}
		else if(!customValidator.checkforAlphabetWithSpaceDotSlashandBrackets(departmentAdminForm.getAdminLevelNameEnglish()))
		{  
			error.rejectValue("adminLevelNameEng", "error.valid.COMMONALPHABETREQUIRED");
		}
		if(!departmentAdminForm.getAdminLevelNameLocal().equalsIgnoreCase(""))
		if(!customValidator.validateSpecialCharacters(departmentAdminForm.getAdminLevelNameLocal()))
		{  
			error.rejectValue("adminLevelNameLocal", "error.invalid.format");	
		}
		
	
        }
		else if(entityType.equals("entityMod"))
		{
		if(departmentAdminForm.getAdminLevelNameEnglish().equalsIgnoreCase(""))
		{
			
			error.rejectValue("adminEntityNameEnglish", "error.empty.ENTITYNAME");
		}
		else if(!customValidator.checkforAlphabetNumbericHypenSpace(departmentAdminForm.getAdminLevelNameEnglish()))
		{  
			error.rejectValue("adminEntityNameEnglish", "error.valid.COMMONALPHABETREQUIRED");
		}
		if(!departmentAdminForm.getAdminLevelNameLocal().equalsIgnoreCase(""))
  			if(!customValidator.validateSpecialCharacters(departmentAdminForm.getAdminLevelNameLocal()))
  			{  
  				error.rejectValue("adminEntityNameLocal", "error.invalid.format");	
  			}
		 if(departmentAdminForm.getAdminUnitLevelCode()!=null && departmentAdminForm.getAdminUnitLevelCode()==-1)
			{
				error.rejectValue("adminUnitLevelCode", "errorMessage.notselectedoption");	
				
			}
	
        }
			
	}


/**
 * {@code validateSpecialCharacters} method validation for special characters,
 * If any special charater exist in string {@code method} return false, true
 * returns for valid string.
 * 
 * @return boolean {true, false}
 * @author vinay Yadav
 */
private boolean validateSpecialCharacters(String inputValue){
	String regexPattern = "[\\#\\%\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\[\\\\\\]\\|\\:\\;\\'\\\\\"\\<\\>\\?\\\\]";
	Pattern pattern 		= Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
	Matcher matcher 		= pattern.matcher(inputValue);
	boolean hasSpecialChars = matcher.find();
	return hasSpecialChars;
}


private boolean checkForNumeric(String strName){ 
	String regexPattern = "[0-9]+$";
	Pattern pattern 		= Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
	Matcher matcher 		= pattern.matcher(strName);
	boolean hasSpecialChars = matcher.find();
	return hasSpecialChars;
}

}