package in.nic.pes.lgd.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.jboss.resteasy.spi.ApplicationException;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LocalBodyEntityDetails;
import com.cmc.lgd.localbody.entities.LocalBodyTable;

import in.nic.pes.lgd.bean.ChildExtendDept;
import in.nic.pes.lgd.bean.Country;
import in.nic.pes.lgd.bean.DepartmentMapping;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.bean.ExistDeptHierarchy;
import in.nic.pes.lgd.bean.ExistingDeptMapping;
import in.nic.pes.lgd.bean.FetchOrgLocatedLevel;
import in.nic.pes.lgd.bean.LgdOrganisationListByOrgType;
import in.nic.pes.lgd.bean.LgdOrganizationAtLevels;
import in.nic.pes.lgd.bean.LgdSubordinateOrgUnits;
import in.nic.pes.lgd.bean.LgdUpperHierarchyLevel;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.draft.form.DepartmentOrgListDto;
import in.nic.pes.lgd.forms.AdminOrgDeptForm;
import pes.attachment.util.AttachedFilesForm;

 public interface AdministrativeDepratmentDAO {

	/**
	 * 
	 * @param isCenterUser
	 * @return
	 */
	 List<DeptAdminUnit> getLandRegionUnitLevel(boolean isCenterUser);

	/**
	 * 
	 * @param parentAdminCode
	 * @return
	 */
	 List<DeptAdminUnit> getAdministrativeUnitLevel(Integer parentAdminCode, Integer stateCode);

	/**
	 * 
	 * @param unitCodes
	 * @return
	 */
	 List<Object[]> getUnitLevelNames(Set<Integer> unitCodes);

	/**
	 * 
	 * @param unitLevelCode
	 * @return
	 */
	 List<DeptAdminUnitEntity> getAdminEntities(Integer unitLevelCode);

	/**
	 * 
	 * @param storedFormList
	 * @param stateCode
	 * @param isCenterUser
	 * @return
	 */
	 boolean saveAdminDepartmentProcess(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, boolean isCenterUser,Long userId,Boolean isOrganization);

	/**
	 * 
	 * @param stateCode
	 * @param orgTypeCode
	 * @return
	 */
	 List<Organization> getDepartmentList(Integer stateCode, Integer orgTypeCode);

	/**
	 * 
	 * @param stateCode
	 * @return
	 */
	 List<DeptAdminUnitEntity> getAdminUnitListbyStateCode(int stateCode);

	/**
	 * 
	 * @param stateCode
	 * @param departmentName
	 * @param httpSession
	 *            (null from DWR)
	 * @return
	 */
	 boolean checkExistingDepartmentName(Integer stateCode, String departmentName, HttpSession httpSession);

	/**
	 * 
	 * @param orgCode
	 * @param unitLevelCode
	 * @return
	 */
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
	 * To Check Existing Dept name for Extended Functionality
	 * 
	 * @author Ripunj on 09-10-2014
	 * @param stateCode
	 * @param departmentName
	 * @param orgLocatedLevelCode
	 * @param httpSession
	 * @return
	 */
	 boolean checkExistingDepartmentNameForExtendedDept(Integer stateCode, String departmentName, Integer orgLocatedLevelCode, HttpSession httpSession);

	 List<DeptAdminUnit> getAdminUnitLevelList(int stateCode);

	 List<DeptAdminUnitEntity> getAdminUnitListbyStateCodeandLevel(int stateCode, int level);

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @author Ripunj on 23-01-2015
// 	 * @param orgUnitCode
// 	 * @return
// 	 */
// 	 LgdDepartmentDetail getDepartmentDetails(Long orgUnitCode);

	/**
	 * @author Ripunj on 23-01-2015
	 * @param orgCode
	 * @return
	 */
	 List<LgdOrganizationAtLevels> getOrganizationAtLevels(int orgCode);

	/**
	 * @author Ripunj on 27-01-2015
	 * @param deparmentLvl
	 * @param stateLvl
	 * @param stateCode
	 * @return
	 */
	 List<LgdOrganisationListByOrgType> getOrganizationListByOrgTypeFn(int deparmentLvl, int stateLvl, int stateCode);

	/**
	 * @author Ripunj on 29-01-2015
	 * @param orgCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	 List<LgdSubordinateOrgUnits> getUnmappedSubordinateOrgUnits(int orgCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode);

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
	 Boolean checkIfDeptMappingExists(Integer stateID, String mappingType, Integer toLevelTypeCode, String fromLevelCategory, Integer fromLevelTypeCode, Integer fromLevelCode);

	/**
	 * @author Ripunj on 02-02-2015
	 * @param localBodyTypeCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	 List<LocalbodyListbyState> getUnmappedULBList(Integer localBodyTypeCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode);

	/**
	 * @author Ripunj on 02-02-2015
	 * @param localBodyCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	 List<LocalbodyListbyState> getUnmappedParentwiseLBList(int localBodyCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode);

	/**
	 * @author Ripunj on 02-02-2015
	 * @param orgCode
	 * @return
	 */
	 List<LgdSubordinateOrgUnits> getSubordinateOrgUnits(int orgCode);

	/**
	 * @author Ripunj on 03-02-2015
	 * @param deptMapping
	 * @return
	 */
	 DepartmentMapping merge(DepartmentMapping deptMapping,String fromCategory,Integer fromLevelTypeCode, String frmLevelCode, String wardcode);

	/**
	 * @author Ripunj on 03-02-2015
	 * @param stateID
	 * @param mappingType
	 * @param toLevelCategory
	 * @param toLevelCode
	 * @return
	 * @throws Exception
	 */
	 DepartmentMapping getDeptMappingByMappingType(Integer stateID, String mappingType, String toLevelCategory, Long toLevelCode) throws Exception;

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
	 * @author Maneesh Kumar 15-May-2015
	 * @param parentAdminLvlCode
	 * @param slc
	 * @param adminLvlCodeList
	 * @param ExistLvlFlag
	 * @return
	 * @throws Exception
	 */
	// List<DeptAdminUnit> getChildofExistingParentLevelbyOrganization(Integer parentAdminLvlCode, Integer slc, List<Integer> adminLvlCodeList,boolean ExistLvlFlag,Integer ExistLvlCode ) throws Exception; 
	
	
	
	
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
	 List<DeptAdminUnit> createtHierarchybyadminUnitCode(Integer olc, List<Integer> adminUnitCode,List<Integer> localBodyCode,Integer statecode) throws Exception;

	/**
	 * To Get List of Exiting Department by Admin unit code
	 * 
	 * @author Maneesh Kumar 20-May-2015
	 * @param olc
	 * @param adminUnitCode
	 * @return
	 * @throws Exception
	 */
	 List<OrgLocatedAtLevels> getExistDeptbyOrganization(Integer olc, Integer adminUnitCode) throws Exception;

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
	
	/**
	 * author Maneesh Kumar 11-June-2015
	 * @param parentOrgLocatedLevelCode
	 * @param level
	 * @return
	 *//*
	 boolean checkParentOrgLocatedLevelCode(Integer parentOrgLocatedLevelCode,Integer level);*/
	
	/**
	 *  author Maneesh Kumar 12-June-2015
	 * @param level
	 * @return
	 */
	/* Integer getEqualUnitLevelCode(Integer level);*/
	
	
	/**
	 *  author Maneesh Kumar 18-June-2015
	 * @param olc
	 * @param level
	 * @return
	 */
	  List<DeptAdminUnit> getOfficeNamebyLevel(Integer olc,Integer level);
	
	 
	 /* for Organization Unit Start */
		
		 String getStateName(int stateCode) throws Exception;
		
		 Object[] getOrgUnitEntityData(Integer orgDeptCode,Integer stateCode) throws Exception;
		
		 Boolean saveOrganizationUnit(List<OrganizationUnit> orgUnitList) throws Exception;
		
		 List<OrganizationUnit> getOrganizationUnitList(Integer orgDeptCode,Integer stateCode) throws Exception;
		/* for Organization Unit Ends here */
		/**
		 * author Maneesh Kumar 07-July-2015
		 * this Method is used to get extend organization units basic details
		 * @param OrgLocatedLevelCode
		 * @return
		 * @throws Exception
		 */
		 Object[] getExtendOrganizationBasicDetail(Integer OrgLocatedLevelCode,Integer stateCode) throws Exception;
		
		/* boolean checkDeptCoverageAreaChangeotNot(Integer orgLocatedAtLevel,List<Integer> newItems,Integer orgLocatedLevelCode) throws Exception;*/
		
		 boolean saveOrganizationUnits(AdminOrgDeptForm adminOrgDeptForm) throws Exception;
		
		 boolean validateEntityListforFullCoverage(List<Integer> entityList,Integer entityType,Integer parentEntityCode) throws Exception;
		
	/**
	 * Code used to get Organization Located Level List except top level for
	 * Rename Specific Name of organization Levels
	 * @author Pooja @since 17-09-2015
	 * @param olc
	 */
	 List<OrgLocatedAtLevels> getOrgLocatedLevelListExceptTop(Integer olc) throws Exception;

	/**
	 * code for saving attachment details in Update Organization Levels
	 * @author Pooja @since 21-09-2015
	 * @param attachedList
	 * @param orgLocatedLevelCode
	 */
	 Boolean saveDataInAttachment(List<AttachedFilesForm> attachedList, Integer orgLocatedLevelCode) throws Exception;
	
	 List<DeptAdminUnit> getChildofExistingParentLevelbyOrganization(Integer parentAdminLvlCode, Integer olc, Integer landRegionSlc, Integer slc, List<Integer> adminLvlCodeList, List<Integer> localBodyCode, String existAdminCode, Integer childAdminCode) throws Exception;
	
	 List<DeptAdminUnit> getUnitLevelbyOrganization(Integer olc) throws Exception;
	
	 List<Organization> getCenterOrganizationbyCriteria(Integer orgTypeCode,Integer parentOrgCode) throws Exception;

	 List<LgdOrganizationAtLevels> getOrganizationAtLevelsForDeptLBMapping(int orgCode);
	
	 List<LBTypeDetails> getLocalBodyTypesDetails(Integer stateCode);

	 List<LocalBodyTable> getLocalBodyEntities(Integer unitLevelCode, Integer stateCode);
	
      List<LocalBodyTable> getLocalbodyUnitLevelListbyCriteria(List<Integer> localbodyEntitylist,Integer localbodyUnitLevelCode,Integer stateCode,Boolean flag);
	
	 List<LgdUpperHierarchyLevel> getUpperHierarchyLevel(int org_located_level_code);

	 List<DeptAdminUnit> getExistingDepartmentSetUp(boolean isCenterUser, Integer olc, Integer slc,
			String entityType);
	//By Abhishek dated 01-12-16
		 List<Organization> getAllOrganizationDetailsBySC(Integer slc);
		
		  String[] checkOrgUnit(Integer orgVal);
		
		  boolean invokeScriptCall(String level,String queryTypes,Integer selectedOrgVal);
		//till here
		
	 List<DeptAdminUnit> getOfficeNamebyLevelWithLocalbody(Integer olc, Integer level);
	
	 List<ChildExtendDept> getChildofParentAdminCode(Integer slc,Integer parentAdminLevelCode,List<Integer> existAdminCodeList)throws Exception;
	
	 List<ExistDeptHierarchy> getExistDeptHierarchy(Integer olc,Integer slc,Boolean isHierarchy)throws Exception;
	
	 String[]  getLevelWiseTreeDetails(Integer stateCode,String parentHierarchy)throws Exception;
		
	 List<LocalBodyEntityDetails> getDistrictPanchayatList( Integer lbTypeCode, Integer stateCode,List<Integer> existlbList,boolean existFlag) throws HibernateException;
	
	 List<LocalBodyEntityDetails> getParentwiseLocalBody(Integer localBodyCode,List<Integer> existlbList,boolean existFlag) throws HibernateException;
	
	 List<OrgLocatedAtLevels> getOfficeDetailbyParent(Integer olc,Integer parentOrgLocaltedCode,Integer locatedAtLevel)throws Exception;
	
	 List<LocalBodyEntityDetails> getlbListbylbCode(List<Integer> existlbList) throws HibernateException;
	
	 List<LocalBodyEntityDetails> getlbListbyCreteria(List<Integer> existDPlbList,List<Integer> existIPlbList,List<Integer> existVPlbList) throws HibernateException ;

	 List<OrgLocatedAtLevels> getOrgLocatedbyOrgCodeNew(int orgCode) throws Exception;
	
	 List<FetchOrgLocatedLevel> fetchOfficeDetailbyParent(Integer olc,Integer parentOrgLocaltedCode,Integer locatedAtLevel)throws Exception;

	  List<DepartmentOrgListDto> getDeptOrgList(Integer adminLevel ,Integer parentEntity ) ;
	
	 List<EntityTransactionsNew> getTranctionDtlsChangeEntity(Integer stateCode)throws Exception;
	
	 List<Object> getScriptThroughTxId(Integer transactionId)throws Exception;
	 
	 List<Country> getCountryList() throws Exception;
	 
	 List<ExistingDeptMapping> getExistingDeptMapping(Integer orgUnitCode,Integer orgLocatedCode,Integer slc, String mappingType) throws Exception;
	 
	// boolean deleteExistingDeptMapping(Integer mappingFromId);
	  boolean deleteExistingDeptMapping(Integer mappingFromId, Integer mappingId,Integer userid,Integer stateID);
	  
	
	  
}
