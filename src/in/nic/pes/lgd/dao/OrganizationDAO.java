package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.AdministrativeEntityCoverage;
import in.nic.pes.lgd.bean.DepartmentByCentreLevel;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.DesignationMaster;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictLineDepartment;
import in.nic.pes.lgd.bean.ExtendDepartment;
import in.nic.pes.lgd.bean.GetOrganizationAtLevels;
import in.nic.pes.lgd.bean.GetOrganizationListFn;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationByCentreLevel;
import in.nic.pes.lgd.bean.OrganizationCoverage;
import in.nic.pes.lgd.bean.OrganizationCoverageDetail;
import in.nic.pes.lgd.bean.OrganizationDesignation;
import in.nic.pes.lgd.bean.OrganizationPK;
import in.nic.pes.lgd.bean.OrganizationType;
import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.bean.PushOrgDepToPes;
import in.nic.pes.lgd.bean.ReportingSetup;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateLineDepartment;
import in.nic.pes.lgd.forms.DepartmentAdminForm;
import in.nic.pes.lgd.forms.DepartmentForm;
import in.nic.pes.lgd.forms.MinistryForm;
import in.nic.pes.lgd.forms.OrgLocatedAtLevelsForm;
import in.nic.pes.lgd.forms.OrganizationTypeForm;
import in.nic.pes.lgd.forms.OrganizationUnitForm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;


public interface OrganizationDAO {

	public List<Organization> getMinistryList() throws Exception;

	public List<DepartmentByCentreLevel> getDepartmentListByCentreLevel(
			char entityCode) throws Exception;

	public List<OrganizationByCentreLevel> getOrganizationListByCentreLevel(
			char entityCode) throws Exception;

	public List<Organization> getDepartmentList() throws Exception;

	public List<OrganizationType> getOrgTypeList() throws Exception;

	public List<Organization> getMinistryDetailList(String query)
			throws Exception;

	public void saveMinistryDetails(Organization org) throws Exception;

	public int getRecordsforMinistry(String ministryName) throws Exception;

	public List<DesignationMaster> getDesignationName(String designationName)
			throws Exception;

	public List<Organization> getOrganization() throws Exception;

	public boolean saveDesignationMaster(DesignationMaster designationMaster,
			Session session) throws Exception;

	public boolean saveOrganizationDesignation(
			OrganizationDesignation organizationDesignation, Session session)
			throws Exception;

	public boolean saveReportingOrg(ReportingSetup reportingOrg, Session session) throws Exception;

	public int getRecordsforOrganizationType(String orgTypeName)
			throws Exception;

	public void saveOrganisationType(OrganizationType orgType) throws Exception;

	public abstract DesignationMaster getDesignationCode(String designationName)
			throws Exception;

	public abstract int setDesignationMaster(String designationName)
			throws Exception;

	public List<Organization> getMinistryDetail(int orgCode) throws Exception;

	public List<MinistryForm> getMinistryDetails(int orgCode) throws Exception;

	public abstract Organization getOrganizationDetailByCode(int orgCode)
			throws Exception;

	public abstract List<Organization> getOrgbyOrgType(int orgTypeCode)
			throws Exception;

	public abstract int getMaxOrganizationVersion(int orgCode) throws Exception;

	public List<OrganizationDesignation> getDesignationByOrgCode(int orgCode,
			int locatedAtLevelCode) throws Exception;

	public List<OrganizationDesignation> getDesignationByOrgCodeNonTop(
			int orgCode, int locatedAtLevelCode) throws Exception;

	// modify ministry
	public boolean update(MinistryForm ministryForm, OrganizationPK orgpk,
			Session session1) throws Exception;

	public boolean updateDepartment(DepartmentForm departmentForm,
			OrganizationPK orgpk, Session session1) throws Exception;

	public boolean updateisActive(Organization organizationbeanisActive,
			OrganizationPK orgpk2, Session session1) throws Exception;

	public void saveWithSession(Organization organization, OrganizationPK orgpk2, int tmporgVersion, Session session) throws Exception;

	public int getMaxCode() throws Exception;

	public boolean saveOrganization(Organization organization, Session session)
			throws Exception;

	public int saveOrgLocatedAtLevel(OrgLocatedAtLevels orgLocatedAtLevels,
			Session session) throws Exception;

	public int saveOrgCoverage(OrganizationCoverage organizationCoverage,
			Session session) throws Exception;

	public int saveOrgCoverageDetail(
			OrganizationCoverageDetail organizationCoverageDetail,
			Session session) throws Exception;

	public int getMaxOrgCode() throws Exception;

	public int getMaxCoverageCode() throws Exception;

	public List<Organization> getDepartmentList(String query) throws Exception;

	public List<Organization> getOrganizationList(String query)
			throws Exception;

	public List<Organization> getMinistryListbyState(int stateCode)
			throws Exception;

	public List<Organization> getDepartmentListByMinistry(int orgCode)
			throws Exception;

	public List<StateLineDepartment> getStateLineDepartmentList(int stateCode,
			int deptCode, char entityType) throws Exception;

	public List<DistrictLineDepartment> getDistrictLineDepartmentList(
			int districtCode, char level) throws Exception;

	public List<DistrictLineDepartment> getSubDistrictLineDepartmentList(
			int subDistrictCode, char level) throws Exception;

	public List<DistrictLineDepartment> getBlockLineDepartmentList(
			int blockCode, char level) throws Exception;

	public List<DistrictLineDepartment> getVillageLineDepartmentList(
			int villageCode, char level) throws Exception;

	public abstract List<OrgLocatedAtLevels> getOrgLocatedbyOrgCode(int orgCode)
			throws Exception;
	
	public Integer getOrgLocatedbyOrgCodeforTopReporting(int orgCode, int olc) throws Exception;

	public abstract List<Organization> getOrgbyOrgTypeForReporting(
			int orgTypeCode, int orgCode) throws Exception;

	public abstract List<OrganizationDesignation> getReportingDesignationForTopDesignation(
			int orgCode) throws Exception;

	public boolean updateOrgType(OrganizationTypeForm organizationTypeForm,
			int orgTypeCode, Session session1) throws Exception;

	public int getRecordsforOrganization(int orgTypeCode) throws Exception;

	public List<OrganizationTypeForm> getOrgTypeDetails(int orgtypeCode)
			throws Exception;

	public abstract int getRecordsforDeptValidation(String orgName, int ... orgTypeCode) throws Exception;

	// modify department
	public List<OrgLocatedAtLevels> getOrgLocatedbyOrgCodemodify(int orgCode)
			throws Exception;

	public List<OrganizationCoverage> getOrgCoveragemodify(int orgLevelCode)
			throws Exception;

	public List<OrganizationCoverageDetail> getOrgCoverageDetailmodify(
			int coverageDetailCode) throws Exception;

	public int updateOrgLocatedAtLevel(OrgLocatedAtLevels orgLocatedAtLevels,
			Session session) throws Exception;

	public int updateOrgCoverage(OrganizationCoverage organizationCoverage,
			Session session) throws Exception;

	public int updateOrgCoverageDetail(
			OrganizationCoverageDetail organizationCoverageDetail,
			Session session) throws Exception;

	public boolean updateOrganization(Organization organization, Session session)
			throws Exception;

	public boolean deleteOrgType(OrganizationTypeForm organizationTypeForm,
			int orgTypeCode, Session session1) throws Exception;

	public abstract int getRecordsforDeptValidation(String orgName,
			int orgTypeCode, int stateCode) throws Exception;

	public abstract List<Organization> getOrganizationDetailByOrgCode(
			int orgCode) throws Exception;

	public abstract List<Organization> getOrganizationDetails(int orgCode)
			throws Exception;

	public boolean updateOrg(Organization organization, Session session)
			throws Exception;

	public int getMaxRecords(String query) throws Exception;
	
	public boolean saveDesignation(OrganizationDesignation designation, Session session) throws Exception;
	public boolean saveDesignationData(OrganizationDesignation designation, Session session) throws Exception;

	public int orgVersionByCode(int orgCode, Session session);

	public Organization getOrgnizationObject(int orgCode, Session session)
			throws Exception;

	public List<GetOrganizationListFn> getOrgbyTypeCodeAtlevel(int orgTypeCode,
			Integer orgLevel, int stateCode) throws Exception;

	public List<GetOrganizationAtLevels> getOrgAtLevel(int orgTypeCode,Integer orgType) throws Exception;
	
	public List<State> getSlc(int stateCode) throws Exception; 
	public List<OrganizationDesignation> getDesignationReporting(int orglevel,int olc) throws Exception; 
	
	public LgdDesignation getDesignationReportingOrganizDetail(int orglevel, int olc) throws Exception;
	
	public List<LgdDesignation> getDesignationReportingOrganiz(int orglevel, int olc) throws Exception;
	
	public List<LgdDesignation> getTopDesignationReporting(int orglevel,int olc) throws Exception; 
	
	public List<OrganizationDesignation> getReportTo(int orglevel,int olc) throws Exception; 
	public boolean getParentOrgLevel(int orglevel,int olc) throws Exception; 
	public List<OrganizationDesignation> getTopLevelReportTo(int orglevel,int olc) throws Exception; 
	public boolean updateOrganizationTypeDAO(OrganizationType organizationType,Session session) throws Exception; 
	public List<Organization> getOrganizationDetailbystateCode(Integer stateCode)throws Exception;
	public ExtendDepartment getExtendBasicDetails(int olc,char level) throws Exception;
	public List<District> getDistrictListforExtendDep(int stateCode,String dlc)throws Exception; 
	public List<ExtendDepartment> checkExtendDetail(Integer orgCode) throws Exception;
	public String checkExtendDetailforDelete(Integer orgCode,char level) throws Exception;
	//public boolean checkExistNameDeparment(Integer stateCode,char level,String deptName) throws Exception; 
	public boolean setParentCodeforExtendDepartment(int olc) throws Exception; 
	public List<OrgLocatedAtLevels> getDepartMentLowLevelDetails( Integer orgUnitCode) throws Exception;
	public boolean checktOrganizationTypeNameExist(String OrgTypeName) throws Exception;
	public List<DistrictLineDepartment> getLandRegionLineDepartmentListforOrganization(int entityCode, char level,List<Organization> orgList) throws Exception;
	
	public boolean checkExistingDepartment(String deptName, char specificLevel);		// added by sushil
	public boolean checkExistingOrganizationByLevel(String orgName, char orgLevel) ;
	public List<OrgLocatedAtLevels> getOrganizationDetailsLowLevel(int orgCode)throws Exception ;
	public List<DistrictLineDepartment> getLandRegionLineDepartmentDetailforOrganization(
			Integer orgUnitCode) throws Exception;
	//public boolean checkExistNameDeparment(Integer stateCode, char level,String deptName,String deptNamech ) throws Exception;
	public boolean checkExistNameDeparment(Integer stateCode, char level, String deptName) throws Exception; 
	public boolean checkExistNameDeparmentModify(Integer stateCode, char level,String deptName,String deptNamech ) throws Exception;
	public List<DistrictLineDepartment> getLandRegionLineDepartmentList(Integer entityCode, Integer deptCode, char level) throws Exception;
	public String getOrgTypeName(Integer orgTypeCode)throws Exception;
	
	public List<PushOrgDepToPes> saveOrgDeptIntoPES(int entityCode, Session session) throws Exception;
	
	public List<DeptAdminUnit> getDeptAdminUnits(int slc,int type) throws Exception;
	
	public boolean saveDeptAdmitUnit(DeptAdminUnit deptAdminUnit) throws Exception;
	
	public boolean  adminUnitOradminChildExist(int slc,String deptAdminUnitName,int adminUnitCode,char choiceType,int parentCode) ;
	
	public DepartmentAdminForm getDeptAdminUnitDetail(int adminUnitCode) throws Exception;
	
	public DepartmentAdminForm getDeptAdminEntityDetail(int adminEntityCode) throws Exception;
	
	public boolean deleteAdminUnitEntity(Integer slc, Integer adminUnitCode,int entityType) throws Exception;
    
	public boolean saveDeptAdmitEntity(DeptAdminUnitEntity deptAdminEntity, Session session) throws Exception;
	
	/*Param Modified by Pooja on 13-08-2015*/
	public boolean saveDeptAdminCoverage(String adminCoverage,String wardCoverage,Integer coverageCode,Integer entityCode, Session session) throws Exception;
	
	public List<DeptAdminUnitEntity> getDeptAdminEntity(int slc) throws Exception;
	
	public boolean modifyDepartmentAdminDetail(DepartmentAdminForm dAdminForm,char parentModify) throws Exception;
	
	public List<DeptAdminUnit> getUnitLevelNames(Integer adminUnitCode,Integer slc) throws Exception;
	
	public List<AdministrativeEntityCoverage> getEntityCoverageDetail(Integer entityCode) throws Exception;
	
	public List<DeptAdminUnitEntity> getUnityEntityByParentLevel(Integer slc,int levelCode, int entityCode) throws Exception;
	
	public List<AdministrativeEntityCoverage> adminEntityCoveredArea(int entityCode,int slc,char coverageType) throws Exception;
	
	public List<Organization> getOrganizationDetailbystateandType(Integer stateCode,int type) throws Exception;
	
    public List<Organization> getOrganizationParent(Integer orgCode,int orgLevel) throws Exception;
	
	public List<Organization> getOrganizationChilds(Integer parentOrgCode) throws Exception;
	
	public boolean updateOrgParentChildUnits(int parentCode,String childOrgUnits) throws Exception;
	
	public List<Organization> getOrganizationSpecificChilds(Integer parentOrgCode) throws Exception;
	
	/*added by chandra on 11-09-14 to get previous mapped organization Units */
	public List<Organization> getOrganizationMappedChilds(Integer parentOrgCode) throws Exception;
	/**
	 * Added by ripunj on 29-09-2014 
	 * To get admin units levels
	 *  @param orgCode
	**/
	public List<ExtendDepartment> getAdministrativeUnitLevelByOrgCode(Integer orgCode,String isCenterFlag)throws Exception;
	
	public List<OrgLocatedAtLevels> getAdministrativeUnitLevelByOrgLevel(Integer orgCode,Integer locatedAtLevel)throws Exception;
	
	public List<Object> getAdminEntityOrgUnits(Integer adminEntityCode) throws Exception;
	
	public String updateOrgUnitNames(Integer adminEntityCode,String name) throws Exception;
	
	public String modifyDepartmentAdminEntity(DepartmentAdminForm dAdminForm,char parentModify) throws Exception;
	/**
	 * Get OrgCoverage For Full Coverage Status
	 * @param orgLevelCode
	 * @return
	 * @throws Exception
	 */
	public String getOrgCoverageForFullCoveage(int orgLevelCode)throws Exception;

	
	/**
	 * added on 11/12/2014 for localbody draft
	 */
	
	
	List<DeptAdminUnit> getUnitLevelNamesForLocalBody(Integer adminUnitCode,
			Integer slc) throws Exception;

	/**
	 * 
	 * added by Anju on 6/01/2015 
	 * @Organization Unit Level by orglocatedlevelcode
	 */
	  
	public List<OrganizationUnit> getOrganizationeUnitLevelByorglocatedlevelcode(
			Integer orglocatedlevelcode) throws Exception;

	/**
	 * 
	 * @ get Org located levels Level by orgCode
	 *  on 6/01/2015
	 *  added by Anju  
	 */
	public List<OrgLocatedAtLevels> getOrganizationnUnitLevelByOrgLevel(
			Integer olc)throws Exception;

	
	/**
	 * 
	 *  get get Organization Name  by orgCode
	 *  added by Anju on 6/01/2015
	 *  added locatedAtLevel parameter on 18-06-2015 by pooja
	 */
	List<Organization> getOrganizationParentName(int orgLevel,int locatedAtLevel) throws Exception;
	/**
	 * on 11-01-2015
	 * @ To Update the orggunit details regarding orgunitcode 
	 * @ Anju Gupta
	 */
	public boolean getUpdateMethod(OrganizationUnitForm orggunit);

	/**
	 * on 11-01-2015
	 * @ To Get department details details regarding parentorgcode 
	 * @ Anju Gupta
	 */
	public List<Organization> getDepartmentDetails(Integer parentorgcode)throws Exception;
	
	
	/**
	 * on 11-01-2015
	 * @ To Get Organization Details by SLC Code 
	 * @ Anju Gupta
	 */
	public List<Organization> getOrganizationDetailbySlcCode(Integer slcCode)throws Exception;
	
	/**
	 * on 17-03-2015
	 * @ To Get Organization Details by SLC Code 
	 * @ Anju Gupta
	 */
	public List<ExtendDepartment> getOrg_located_at_levelsByOrgCode(Integer orgCode,String isCenterFlag) throws Exception;


	/**
	 * Added by Anchal Todariya on 16-03-2015 
	 * To get admin units levels for state and center orgs
	 *  @param orgCode
	**/
	public List<ExtendDepartment> getAdministrativeUnitLevelDeptByOrgCode(Integer orgCode) throws Exception;
	
	/**
	 * To get Draft Dept Admin Units
	 * @author Ripunj on 21-04-2015
	 * @param slc
	 * @param type
	 * @param isPublish
	 * @return
	 * @throws Exception
	 */
	public List<DeptAdminUnit> getDraftDeptAdminUnits(Integer slc, Integer type,Character isPublish)
			throws Exception;

	/**
	 * To update Draft Dept Admin Units
	 * @author Ripunj on 21-04-2015
	 * @param deptAdminUnit
	 * @return
	 * @throws Exception
	 */
	public boolean saveorUpdateDeptAdmitUnitDraft(DeptAdminUnit deptAdminUnit)
			throws Exception;

	/**
	 * To delete Draft Dept Admin Units
	 * @author Ripunj on 22-04-2015
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAdminUnitLevel(Integer slc, Integer adminUnitCode,
			int entityType) throws Exception;

	/**
	 * To update active flag false in case of delete administrative unit level ,entities and their coverages
	 * @author Ripunj on 22-04-2015
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return 
	 * @throws Exception
	 */
	public boolean deleteAdminUnitEntitysetFlagFalse(Integer slc,
			Integer adminUnitCode, int entityType) throws Exception;
	
	/**
	 * Added by kirandeep for the geting the parent level for draft in admin unit entity
	 * @param slc
	 * @param levelCode
	 * @param entityCode
	 * @return
	 * @throws Exception
	 */
	public List<DeptAdminUnitEntity> getUnityEntityByParentLevelForDraft(Integer slc,int levelCode, int entityCode) throws Exception;
	
	/**
	 * Added by kirandeep for deleting a admin unit enitity from draft
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAdminUnitEntityForDraft(Integer slc, Integer adminUnitCode,int entityType) throws Exception;
	
	/**
	 * Added by kirandeep for for publishing admin entity
	 * @param adminEntityCode
	 * @return
	 * @throws Exception
	 */
	public boolean publishAdminEntity(Integer adminEntityCode) throws Exception;
	
	/**
	 * Added by kirandeep for modifying admin unit entity in draft
	 * @param dAdminForm
	 * @param parentModify
	 * @return
	 * @throws Exception
	 */
	public String modifyDepartmentAdminEntityForDraft(DepartmentAdminForm dAdminForm,char parentModify) throws Exception ;
	
	/**
	 * added by kirandeep for the getting the details of coveranges of draft admin entity
	 * @param entityCode
	 * @param slc
	 * @return
	 * @throws Exception
	 */
	public List<AdministrativeEntityCoverage> getEntityCoverageDetailForDraft(Integer entityCode) throws Exception ;
	
	/**
	 * added by kirandeep for saving the coverage of draft admin unit entity
	 * @param adminCoverage
	 * @param wardCoverage
	 * @param deletedCoverage deleted by pooja on 14-08-2015
	 * @param coverageCode
	 * @param entityCode
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public boolean saveDeptAdminCoverageForDraft(String adminCoverage,String wardCoverage,Integer coverageCode,Integer entityCode, Session session) throws Exception;
	
	/**
	 * To  Get AdminLevel For District User
	 * @author Ripunj on 24-04-2015
	 * @param slc
	 * @return
	 * @throws Exception
	 */
	public List<DeptAdminUnit> getAdminLevelForDistrictUser(Integer slc)
			throws Exception;
	
	/**
	 * To get Admin Entity  for District User
	 * @author Ripunj on 27-04-2015  
	 * @param entity_code
	 * @param admin_level
	 * @return
	 * @throws Exception
	 */
	public List<DeptAdminUnitEntity> getAdminEntityForDistrictUser(Integer entity_code,
			Integer admin_level) throws Exception;

	/**
	 * To get Unit level names  for District User
	 * @author Ripunj on 27-04-2015 
	 * @param adminUnitCode
	 * @param slc
	 * @return
	 * @throws Exception
	 */
	public List<DeptAdminUnit> getUnitLevelNamesForLocalBodyDistrictUser(
			Integer adminUnitCode, Integer districtCode) throws Exception;

	/**
	 * To get Unit Admin Entities for District User
	 * @author Ripunj on 28-04-2015
	 * @param districtCode
	 * @param levelCode
	 * @param entityCode
	 * @return
	 * @throws Exception
	 */
	public List<DeptAdminUnitEntity> getUnityEntityByParentLevelForDistrictUser(Integer districtCode,int levelCode, int entityCode) throws Exception;
	
	/**
	 * To get Dept Admin Entities By District
	 * @author Ripunj on 28-04-2015
	 * @param slc
	 * @param districtCode
	 * @return
	 * @throws Exception
	 */
	public List<DeptAdminUnitEntity> getDeptAdminEntityForDistrict(Integer slc,
			Integer districtCode) throws Exception; 
	
	/**
	 * added by pooja on 23-06-2015 for capture Org Unit Details
	 * */
	public List<OrganizationUnit> getOrganizationUnitDetails(Integer orgUnitCode) throws Exception;
	public Object[] getVlcTlcDlc(Integer villageCode) throws Exception;
	public Integer getSlcByLbOrVillageCode(Integer code) throws Exception;
	public boolean checkExistingOrgUnitName(Integer stateCode, String orgUnitName) throws Exception;
	
	/**
	 *  added by pooja on 05-08-2015 for Manage Admin Unit Entity
	 */
	public Integer[] getLocalBobyTypeListByAdminUnitCode(Integer adminUnitCode,Integer slc,String status) throws Exception;
	
	/**
	 *  added by pooja on 14-08-2015 
	 */
	public Integer getdistrictByadminUnitCodeAndParentEntityCode(Integer adminUnitCode, Integer parentEntityCode) throws Exception;
	
	/**
	 * added by pooja on 16-09-2015
	 */
	public boolean checkExistOrgLevelSpecificName(Integer stateCode, String orgLevelSpecificName) throws Exception;
	public String changeLvlSpecificNameOrg(OrgLocatedAtLevelsForm orgLocatedAtLevelsForm,HttpSession session) throws Exception;
	
	/**
	 * added by pooja on 20-10-2015
	 */
	public Integer getParentAdmnUnitLvlCode(Integer adminUnitLevelCode) throws Exception;
	public Integer getStateByParentUnitEntityCode(Integer adminUnitLvlCode,Integer parentUnitEntityCode) throws Exception;
	public List<OrganizationUnit> getOrganizationeUnitsByorglocatedlevelcode(Integer orglocatedlevelcode) throws Exception; 
	
	/*added by deepti on 11-05-2016 for unit overlapping exist or not*/
	public Character getOverlappingExist(Integer adminUnitlabelcode) throws Exception;
    
	/*added by deepti on 16-05-2016  for getting entity list */
	public List<DeptAdminUnitEntity> getEntityList(Integer adminUnitLabelCode) throws Exception;
	
	/**
	 * added by Sunita on 24-11-2016
	 */
	public List<DeptAdminUnit> getDeptAdminUnitsRevenue(int slc,int type) throws Exception;
	
	public List<OrgLocatedAtLevels> getOrgLocatedbyOrgForExtendDepartment(boolean isCenterUser,int orgCode) throws Exception;
	
	public  Map<String, Object> getOrgUnitswiseDeptDetails(Integer orgUnitCode,boolean isParentOrgUnit) throws Exception;
	
	public Character getParentCategory(Integer adminUnitLevelCode,Session session)throws Exception;
	
	public boolean isOrganizationExist(Integer olc) throws Exception;
	
	public boolean isOrgLocatedLevelCodeExist(Integer olc,Integer orgLocatedLevelCode) throws Exception;
	
	public List<Organization> getOrganizationListbyOrgType(Integer orgTypeCode,Integer stateCode) throws Exception;
	
	public List<Object> getOrganizationAtLevelbyOrgCode(int orgCode) throws Exception;
	
	public boolean publishAdminEntityAll(List<Integer> adminEntityCodeList) throws Exception;
	
	public List<Organization> getOrganizationDetails() throws HibernateException, Exception;
	
	public boolean updateParentAdminEntityDetailsCall(DepartmentAdminForm adminUnitView ,Long userId) throws HibernateException, Exception;
	
	

}