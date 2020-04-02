package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.ChildExtendDept;
import in.nic.pes.lgd.bean.Country;
import in.nic.pes.lgd.bean.DepartmentMapping;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.bean.ExistDeptHierarchy;
import in.nic.pes.lgd.bean.ExistingDeptMapping;
import in.nic.pes.lgd.bean.FetchOrgLocatedLevel;
import in.nic.pes.lgd.bean.LgdOrganizationAtLevels;
import in.nic.pes.lgd.bean.LgdSubordinateOrgUnits;
import in.nic.pes.lgd.bean.LgdUpperHierarchyLevel;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.draft.form.DepartmentOrgListDto;
import in.nic.pes.lgd.forms.AdminOrgDeptForm;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.batik.svggen.font.table.ScriptList;
import org.hibernate.HibernateException;
import org.springframework.ui.ModelMap;

import pes.attachment.util.AttachedFilesForm;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;
import com.cmc.lgd.localbody.entities.LocalBodyEntityDetails;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.org.ep.exception.BaseAppException;

 public interface AdministrativeDepratmentService {

	 List<DeptAdminUnit> getLandRegionUnitLevel(boolean isCenterUser);

	 List<DeptAdminUnit> getAdministrativeUnitLevel(Integer parentAdminCode, Integer stateCode);

	 List<Object[]> getUnitLevelNames(Set<Integer> unitCodes);

	 List<DeptAdminUnitEntity> getAdminEntities(Integer unitLevelCode);

	 boolean saveAdminDepartmentProcess(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, boolean isCenterUser,Long userId,Boolean isOrganization);

	 List<Organization> getDepartmentList(Integer stateCode, Integer orgTypeCode);

	 List<DeptAdminUnitEntity> getDeptAdminUnitList(int stateCode) throws BaseAppException; // NO_UCD (unused code)

	 boolean checkExistingDepartmentName(Integer stateCode, String departmentName, HttpSession httpSession); // NO_UCD (unused code)

	 List<DeptAdminUnitEntity> getAdminEntitiesExtended(Integer orgCode, Integer unitLevelCode);

	/**
	 * Save Extended Admin Department Details.
	 * 
	 * @author Ripunj on 07-10-2014
	 * 
	 * @param storedFormList
	 * @param stateCode
	 * @param orgLocatedLevelCode
	 * @param isCenterUser
	 * @return
	 */
	 boolean saveExtendedAdminDepartmentProcess(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, Integer orgLocatedLevelCode, boolean isCenterUser);

	/**
	 * 
	 * @param stateCode
	 * @param departmentName
	 * @param orgLocatedLevelCode
	 * @param httpSession
	 * @return
	 */
	 boolean checkExistingDepartmentNameForExtendedDept(Integer stateCode, String departmentName, Integer orgLocatedLevelCode, HttpSession httpSession); // NO_UCD (unused code)

	 List<DeptAdminUnit> getAdminUnitLevelList(int stateCode); // NO_UCD (unused code)

	 List<DeptAdminUnitEntity> getAdminUnitListbyStateCodeandLevel(int stateCode, int level); // NO_UCD (unused code)

	/**
	 * Add for Department/Local Body Mapping
	 * 
	 * @author Ripunj on 23-01-2015
	 * @param model
	 * @param request
	 * @param session
	 */
	 void setMappingBasedOnRole(ModelMap model, HttpServletRequest request, Integer stateCode);

	/**
	 * To get Organization Details with their level office name
	 * 
	 * @author Ripunj 0n 28-01-2015
	 * @param orgCode
	 * @return
	 */
	 List<LgdOrganizationAtLevels> getOrganizationAtLevels(int orgCode); // NO_UCD (unused code)
	
	/**
	 * @author Ripunj on 29-01-2015
	 * @param orgCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	
	 List<LgdOrganizationAtLevels> getOrganizationAtLevelsForDeptLBMapping(int orgCode);
	/**
	 * @author Pranav on 24-10-2016
	 * @param orgCode
	 * @return
	 */
	 List<LgdUpperHierarchyLevel> getUpperHierarchyLevel(int org_located_level_code);
	/**
	 * @author Pranav on 26-10-2016
	 * @param org_located_level_code
	 * @return
	 */
	 List<LgdSubordinateOrgUnits> getUnmappedSubordinateOrgUnits(int orgCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode); // NO_UCD (unused code)

	/**
	 * @author Ripunj on 29-01-2015
	 * @param stateID
	 * @param mappingType
	 * @param toLevelTypeCode
	 * @param fromLevelCategory
	 * @param fromLevelTypeCode
	 * @param fromLevelCode
	 * @return
	 * @throws ApplicationException
	 */
	 Boolean checkIfDeptMappingExists(Integer stateID, String mappingType, Integer toLevelTypeCode, String fromLevelCategory, Integer fromLevelTypeCode, Integer fromLevelCode); // NO_UCD (unused code)

	/**
	 * @author Ripunj on 02-02-2015
	 * @param localBodyTypeCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	 List<LocalbodyListbyState> getUnmappedULBList(Integer localBodyTypeCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode); // NO_UCD (unused code)

	/**
	 * @author Ripunj on 02-02-2015
	 * @param localBodyCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	 List<LocalbodyListbyState> getUnmappedParentwiseLBList(int localBodyCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode); // NO_UCD (unused code)

	/**
	 * @author Ripunj on 02-02-2015
	 * @param orgCode
	 * @return
	 */
	 List<LgdSubordinateOrgUnits> getSubordinateOrgUnits(int orgCode); // NO_UCD (unused code)

	/**
	 * @author Ripunj on 03-02-2015
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	 boolean saveDepartmentMapping(ModelMap model, HttpServletRequest request);

	/**
	 * To Get Organization List filter by stateCode and organization type
	 * 
	 * @author Maneesh Kumar 07-May-2015
	 * @param stateCode
	 * @param orgTypeCode
	 * @return
	 * @throws Exception
	 */
	 List<Organization> getOrganizationbyCriteria(Integer slc, Integer orgTypeCode) throws Exception;

	/**
	 * To Get Land region list of existing organization
	 * 
	 * @author Maneesh Kumar 13-May-2015
	 * @param isCenterUser
	 * @param olc
	 * @return
	 * @throws Exception
	 */
	 List<DeptAdminUnit> getExistLandRegionUnitLevelbyOrganization(boolean isCenterUser, Integer olc, Integer slc,String entityType) throws Exception;

	/**
	 * To Get Land region list by organization
	 * 
	 * @author Maneesh Kumar 13-May-2015
	 * @param isCenterUser
	 * @param olc
	 * @return
	 * @throws Exception
	 */
	 List<DeptAdminUnit> getLandRegionUnitLevelbyOrganization(boolean isCenterUser, Integer olc, Integer landRegionSlc, Integer adminUnitCode) throws Exception;

	/**
	 * To Get Existing child list of parent level by orgnaization
	 * 
	 * @author Maneesh Kumar 15-May-2015
	 * @param parentAdminLvlCode
	 * @param olc
	 * @param landRegionSlc
	 * @param slc
	 * @param adminLvlCodeList
	 * @param existAdminCode
	 * @param childAdminCode
	 * @return
	 * @throws Exception
	 */
	// List<DeptAdminUnit> getChildofExistingParentLevelbyOrganization(Integer parentAdminLvlCode, Integer slc, String adminLvlCodeList,boolean ExistLvlFlag,Integer ExistLvlCode) throws Exception;   // NO_UCD (unused code)
	
	/**
	 * To Get parent List of Exiting Department
	 * 
	 * @author Maneesh Kumar 20-May-2015
	 * @param olc
	 * @param parentOrgLocatedLevelCode
	 * @return
	 * @throws Exception
	 */

	 List<OrgLocatedAtLevels> getParentDeptListbyOrganization(Integer olc, Integer parentLevelCode) throws Exception;

	/**
	 * To create Hierarchy of existing and new level
	 * 
	 * @author Maneesh Kumar 20-May-2015
	 * @param olc
	 * @param adminUnitCode
	 * @return
	 * @throws Exception
	 */
	 List<DeptAdminUnit> createtHierarchybyadminUnitCode(Integer olc, String adminUnitCodes,Integer stateCode) throws Exception;

	/**
	 * To Get List of Exiting Department by Admin unit code
	 * 
	 * @author Maneesh Kumar 20-May-2015
	 * @param olc
	 * @param adminUnitCode
	 * @return
	 * @throws Exception
	 */
	 List<OrgLocatedAtLevels> getExistDeptbyOrganization(Integer olc, Integer adminUnitCode) throws Exception; // NO_UCD (unused code)

	/**
	 * To save restructure department level
	 * 
	 * @author Maneesh Kumar 25-May-2015
	 * @param storedFormList
	 * @param stateCode
	 * @param isCenterUser
	 * @return
	 * @throws Exception
	 */
	/**
	 * add paramameter userId by pooja 
	 * on 28-09-2015
	 */
	 boolean saveRestructureDepartmentLevel(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, boolean isCenterUser, Integer olc,Long userId) throws Exception;

	/**
	 * To get DeptAdminUnit by state code
	 * 
	 * @param isCenterUser
	 * @param slc
	 * @return
	 * @throws Exception
	 */
	 List<DeptAdminUnit> getStateWiseAdminUnit(boolean isCenterUser, Integer slc) throws Exception;

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * author Maneesh Kumar 11-June-2015
// 	 * 
// 	 * @param parentOrgLocatedLevelCode
// 	 * @param level
// 	 * @return
// 	 */
// 	 boolean checkParentOrgLocatedLevelCode(Integer parentOrgLocatedLevelCode, Integer level);

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * author Maneesh Kumar 12-June-2015
// 	 * 
// 	 * @param level
// 	 * @return
// 	 */
// 	 Integer getEqualUnitLevelCode(Integer level);
	
	/**
	 *  author Maneesh Kumar 18-June-2015
	 * @param olc
	 * @param level
	 * @return
	 */
	  List<DeptAdminUnit> getOfficeNamebyLevel(Integer olc,Integer level);
	 
	 /* for Organization Unit Start */
		
		 String getStateName(int stateCode) throws Exception;
		
		 Boolean saveOrganisationUnit(List<OrganizationUnit>organisationUnitList) throws Exception;
		
		 List<OrganizationUnit> getOrganizationUnitList(Integer orgDeptCode,Integer stateCode) throws Exception; // NO_UCD (unused code)
		
		 Object[] getOrgUnitEntityData(Integer orgDeptCode,Integer stateCode) throws Exception;
		/* for Organization Unit Ends here */
		
		/**
		 * author Maneesh Kumar 07-July-2015
		 * this Method is used to get extend organization units basic details
		 * @param OrgLocatedLevelCode
		 * @return
		 * @throws Exception
		 */
		 Object[] getExtendOrganizationBasicDetail(Integer OrgLocatedLevelCode,Integer stateCode) throws Exception;
		
		 boolean saveOrganizationUnits(AdminOrgDeptForm adminOrgDeptForm) throws Exception;
		
		 boolean validateEntityListforFullCoverage(String entityCodes, Integer entityType, Integer parentEntityCode) throws Exception; // NO_UCD (unused code)
		
		/**
		 * Code used to get Organization Located Level List except top level for
		 * Rename Specific Name of organization Levels
		 * @author Pooja @since 17-09-2015
		 * @param olc
		 */
		 List<OrgLocatedAtLevels> getOrgLocatedLevelListExceptTop(Integer olc) throws Exception; // NO_UCD (unused code)

		/**
		 * code for saving attachment details in Update Organization Levels
		 * @author Pooja @since 21-09-2015
		 * @param attachedList
		 * @param orgLocatedLevelCode
		 */
		 Boolean saveDataInAttachment(List<AttachedFilesForm> attachedList, Integer orgLocatedLevelCode) throws Exception;
		
		 List<DeptAdminUnit> getChildofExistingParentLevelbyOrganization(Integer parentAdminLvlCode, Integer olc, Integer landRegionSlc, Integer slc, String adminUnitCodes, String existCode,Integer childAdminCode) throws Exception;
		
		 List<DeptAdminUnit> getUnitLevelbyOrganization(Integer olc) throws Exception;
		
		 List<Organization> getCenterOrganizationbyCriteria(Integer orgTypeCode,Integer parentOrgCode) throws Exception;
		/**
		 * code for saving attachment details in Update Organization Levels
		 * @author SANJAI  @since 21-09-2015
		 * @param attachedList
		 * @param orgLocatedLevelCode
		 */
		 List<LBTypeDetails> getLocalBodyTypesDetails(Integer stateCode);

		 List<LocalBodyTable> getLocalBodyEntities(Integer unitLevelCode, Integer stateCode);

		List<DeptAdminUnit> getExistingDepartmentSetUp(boolean isCenterUser, Integer olc, Integer slc,
				String entityType) throws Exception;
		//By abhishek
		 List<Organization> getAllOrganizationDetailsBySC(Integer slc);
		  String[] checkOrgUnit(Integer orgVal);
		 boolean invokeScriptCall(String level,String queryTypes,Integer selectedOrgVal);
		 List<DeptAdminUnit> getOfficeNamebyLevelWithLocalbody(Integer olc, Integer level);
		
		 List<ChildExtendDept> getChildofParentAdminCode(Integer slc,Integer parentAdminLevelCode,String existAdminCodes)throws Exception;
		
		 List<ExistDeptHierarchy> getExistDeptHierarchy(Integer olc,Integer slc,Boolean isHierarchy)throws Exception;
		
		 String[]  getLevelWiseTreeDetails(Integer stateCode,String parentHierarchy)throws Exception;
		
		 List<LocalBodyEntityDetails> getDistrictPanchayatList(Integer lbTypeCode, Integer stateCode,String existlbcodes) throws HibernateException;
		
		 List<LocalBodyEntityDetails> getParentwiseLocalBody(Integer localBodyCode,String existlbcodes) throws HibernateException;
		
		 List<OrgLocatedAtLevels> getOfficeDetailbyParent(Integer olc,Integer parentOrgLocaltedCode,Integer locatedAtLevel)throws Exception;
		
		 List<LocalBodyEntityDetails> getlbListbyCreteria(String existDPlbCodes, String existIPlbCodes, String existVPlbCodes,String accessLevel) throws HibernateException;
		
		 List<OrgLocatedAtLevels> getOrgLocatedbyOrgCodeNew(int orgCode) throws Exception;
		
		 List<FetchOrgLocatedLevel> fetchOfficeDetailbyParent(Integer olc,Integer parentOrgLocaltedCode,Integer locatedAtLevel)throws Exception;

		  List<DepartmentOrgListDto> getDeptOrgList(Integer adminLevel ,Integer parentEntity ) throws Exception;
			
		 List<EntityTransactionsNew> getTranctionDtlsChangeEntity(Integer stateCode)throws Exception;
		
		 List<Object> getScriptThroughTxId(Integer transactionId)throws Exception;

		 List<Country> getCountryList() throws Exception;
		 
		 List<ExistingDeptMapping> getExistingDeptMapping(Integer orgUnitCode,Integer orgLocatedCode,Integer slc, String mappingType) throws Exception;
		 
		 //boolean deleteExistingDeptMapping(Integer mappingFromId);
		 
		  boolean deleteExistingDeptMapping(Integer mappingFromId, Integer mappingId,Integer userid,Integer stateID);
		  
		  
		  
}
