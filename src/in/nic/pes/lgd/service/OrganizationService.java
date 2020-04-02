package in.nic.pes.lgd.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

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
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationByCentreLevel;
import in.nic.pes.lgd.bean.OrganizationCoverage;
import in.nic.pes.lgd.bean.OrganizationCoverageDetail;
import in.nic.pes.lgd.bean.OrganizationDesignation;
import in.nic.pes.lgd.bean.OrganizationType;
import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateLineDepartment;
import in.nic.pes.lgd.forms.DepartmentAdminForm;
import in.nic.pes.lgd.forms.DepartmentDataForm;
import in.nic.pes.lgd.forms.DepartmentForm;
import in.nic.pes.lgd.forms.DesignationForm;
import in.nic.pes.lgd.forms.DesignationReportingForm;
import in.nic.pes.lgd.forms.MinistryForm;
import in.nic.pes.lgd.forms.OrgLocatedAtLevelsForm;
import in.nic.pes.lgd.forms.OrganizationTypeForm;
import in.nic.pes.lgd.forms.OrganizationUnitForm;
import pes.attachment.util.AttachedFilesForm;

public interface OrganizationService {

	public List<Organization> getMinistryList() throws Exception;

	public List<DepartmentByCentreLevel> getDepartmentListByCentreLevel(char entityCode) throws Exception;

	public List<OrganizationByCentreLevel> getOrganizationListByCentreLevel(char entityCode) throws Exception;

	public List<Organization> getDepartmentList() throws Exception;

	public List<OrganizationType> getOrgTypeList() throws Exception;

	public List<Organization> getMinistryDetailList(String query) throws Exception;

	public int saveMinistryDetails(MinistryForm ministryForm) throws Exception;

	public List<DesignationMaster> getDesignationName(String designationName) throws Exception;

	public void saveOrganisationType(OrganizationTypeForm orgTypeForm) throws Exception;

	public abstract List<Organization> getOrganization() throws Exception;

	public abstract boolean saveOrganizationDesignation(DesignationForm designationForm) throws Exception;

	public List<OrganizationDesignation> getDesignationByOrgCode(int orgCode, int locatedAtLevelCode) throws Exception;

	public List<OrganizationDesignation> getDesignationByOrgCodeNonTop(int orgCode, int locatedAtLevelCode) throws Exception;

	public abstract boolean saveOrganizationReporting(DesignationReportingForm designationReportingForm) throws Exception;

	public abstract List<Organization> getOrganizationReporting(String orgType) throws Exception;

	public List<Organization> getDepartmentList(String query) throws Exception;

	public List<Organization> getOrganizationList(String query) throws Exception;

	public List<Organization> getMinistryListbyState(int stateCode) throws Exception;

	public List<Organization> getDepartmentListByMinistry(int orgCode) throws Exception;
	// modify ministry
	public List<Organization> getMinistryDetail(int orgCode) throws Exception;

	public List<MinistryForm> getMinistryDetails(int orgCode) throws Exception;

	public List<Organization> getOrganizationDetails(int orgCode) throws Exception;

	public int getMaxOrganizationVersion(int orgCode) throws Exception;

	public boolean ministryUpdate(MinistryForm ministryForm, int orgCode, HttpSession session, HttpServletRequest request) throws Exception;

	public boolean departmentUpdate(DepartmentForm departmentForm, int orgCode, HttpSession session, HttpServletRequest request) throws Exception;

	public boolean saveDepartment(DepartmentForm departmentFormCentralL, List<DepartmentForm> listDepartmentFormStateL, List<DepartmentForm> listDepartmentFormDistrictL, DepartmentForm departmentFormSubDistrictL, DepartmentForm departmentFormBlockL,
			DepartmentForm departmentFormVillageL, int stateCode) throws Exception;

	public abstract boolean saveOrgCoverage(char coverageLevel, int coverageDetailCode, char coverageEntityType, int orgLocatedCode, Session session) throws Exception;

	public abstract boolean saveOrgCoverageDetail(int maxCoverageCode, int entityCode, Session session) throws Exception;

	public abstract boolean abolishDepartment(int orgCode) throws Exception;

	public boolean orgTypeUpdate(OrganizationTypeForm organizationTypeForm, int orgTypeCode, HttpSession session, HttpServletRequest request) throws Exception;

	public List<OrganizationType> getOrganizationType() throws Exception;

	public List<Organization> getDepartmentListforOrg(String orgCode) throws Exception;

	public List<StateLineDepartment> getStateLineDepartmentList(int stateCode, int deptCode, char entityType) throws Exception;

	public List<DistrictLineDepartment> getDistrictLineDepartmentList(int districtCode, char level) throws Exception;

	public List<DistrictLineDepartment> getSubDistrictLineDepartmentList(int subDistrictCode, char level) throws Exception;

	public List<DistrictLineDepartment> getBlockLineDepartmentList(int blockCode, char level) throws Exception;

	public List<DistrictLineDepartment> getVillageLineDepartmentList(int villageCode, char level) throws Exception;

	public Integer getOrgLocatedbyOrgCodeforTopReporting(int orgCode, int olc) throws Exception;

	public abstract List<OrganizationType> getOrganizationTypeforAddDesig() throws Exception;

	public abstract List<Organization> getOrgbyOrgTypeForReporting(int orgTypeCode, int orgCode) throws Exception;

	public abstract List<OrganizationDesignation> getReportingDesignationForTopDesignation(int orgCode) throws Exception;

	public int getRecordsforOrganization(int orgTypeCode) throws Exception;

	public List<Organization> getOrgbyOrgType(int orgTypeCode) throws Exception;

	public List<OrganizationTypeForm> getOrgTypeDetails(int orgtypeCode) throws Exception;

	public abstract OrganizationDesignation getOrgDesignationDetails(int orgCode) throws Exception;

	// modify department
	public boolean orgTypeDelete(OrganizationTypeForm organizationTypeForm, int orgTypeCode, HttpSession session, HttpServletRequest request) throws Exception;

	public List<OrgLocatedAtLevels> getOrgLocatedbyOrgCodemodify(int orgCode) throws Exception;
	
	public List<OrgLocatedAtLevels> getOrgLocatedbyOrgForExtendDepartment(boolean isCenterUser,int orgCode) throws Exception;

	public List<OrganizationCoverage> getOrgCoveragemodify(int orgLevelCode) throws Exception;

	public List<OrganizationCoverageDetail> getOrgCoverageDetailmodify(int coverageDetailCode) throws Exception;

	public boolean updateDepartment(DepartmentForm departmentFormCentralL, List<DepartmentForm> listDepartmentFormStateL, List<DepartmentForm> listDepartmentFormDistrictL, DepartmentForm departmentFormSubDistrictL,
			DepartmentForm departmentFormVillageL) throws Exception;

	public abstract boolean updateOrgCoverageDetail(int maxCoverageCode, int entityCode, Session session) throws Exception;

	public boolean updateOrgCoverage(char coverageLevel, int coverageDetailCode, char coverageEntityType, int orgLocatedCode, Session session) throws Exception;

	public void saveDataInAttachment(GovernmentOrder govtOrder, MinistryForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws Exception;

	public GovernmentOrder saveDataInGovtOrder(MinistryForm govtForm, Session session) throws Exception;

	public MinistryForm getgovernmentOrder(Integer orgCode);

	public List<DepartmentDataForm> getOrgDetailsModify() throws Exception;

	public boolean orgUpdate(OrganizationTypeForm organizationTypeForm, int orgCode, HttpSession session, HttpServletRequest request) throws Exception;

	public List<GetOrganizationAtLevels> getOrgAtLevels(int orgTypeCode, Integer orgLevel) throws Exception;

	public List<GetOrganizationListFn> getOrgbyTypeCodeAtlevel(int orgTypeCode, Integer orgLevel, int stateCode) throws Exception;

	public boolean saveOrganizationReporting(DesignationForm designationForm) throws Exception;

	public List<State> getSlc(int stateCode) throws Exception;

	public List<OrganizationDesignation> getDesignationReporting(int olc, int orglevel) throws Exception;

	public LgdDesignation getDesignationReportingOrganizDetail(int orglevel, int olc) throws Exception;
	
	public List<LgdDesignation> getDesignationReportingOrganiz(int orglevel, int olc) throws Exception;

	public List<LgdDesignation> getTopDesignationReporting(int orglevel, int olc) throws Exception;
	
	public List<OrganizationDesignation> getReportTo(int orglevel, int olc) throws Exception;

	public boolean getParentOrgLevel(int orglevel, int olc) throws Exception;

	public List<OrganizationDesignation> getTopLevelReportTo(int orglevel, int olc) throws Exception;

	public boolean saveDataInGovtOrderfordeletedepartment(MinistryForm viewDept);

	public List<OrganizationType> getOrganizationTypeListbyOrgTypeCode(Integer OrgTypeCode) throws Exception;

	public List<OrganizationType> getOrganizationTypeListbyOrgType(String OrgType) throws Exception;

	public boolean ChecktOrganizationTypeExist(Integer OrgTypeCode) throws Exception;

	public boolean deleteOrganizationType(Integer OrgTypeCode) throws Exception;

	public List<OrganizationType> getOrganizationType(Integer OrgTypeCode) throws Exception;

	public boolean updateOrganizationType(OrganizationType organizationType) throws Exception;

	public boolean deleteDeparmentTransaction(Session session, int maxOrgCode1);

	public List<Organization> getOrganizationDetailbystateCode(Integer stateCode) throws Exception;

	public String getExtendDepatmentInformation(Integer orgCode) throws Exception;

	public ExtendDepartment getExtendBasicDetails(Integer orgCode, char level) throws Exception;

	public List<District> getDistrictListforExtendDep(int stateCode, String dlc) throws Exception;

	public boolean extendDepartment(DepartmentForm departmentFormCentralL, List<DepartmentForm> listDepartmentFormStateL, List<DepartmentForm> listDepartmentFormDistrictL, DepartmentForm departmentFormSubDistrictL,
			DepartmentForm departmentFormBlockL, DepartmentForm departmentFormVillageL, int stateCode, Integer orgCode) throws Exception;

	// public boolean deleteEntityinExtendDepatment( String entityCode, int
	// coverageDetailCode, Session session)throws Exception;
	public boolean extendOrgCoverageDetail(int maxCoverageCode, int entityCode, Session session) throws Exception;

	public List<ExtendDepartment> checkExtendDetail(Integer orgCode) throws Exception;

	public String getExtendDetaildofEntity(Integer olc, char located_at_level, char org_coverage_entity_type) throws Exception;

	public String checkExtendDetailforDelete(Integer orgCode, char level) throws Exception;

	public boolean deleteEntityinExtendDepatment(String coverageDetailCode, Session session) throws Exception;

	public boolean deleteCoverageExtendDepatment(Integer orgLocatedLevelCode, Session session) throws Exception;

	public boolean checkExistNameDeparment(Integer stateCode, char level, String deptName) throws Exception;

	public List<OrgLocatedAtLevels> getDepartMentLowLevelDetails(Integer orgUnitCode) throws Exception;

	public boolean abolishDepartmentforState(int olc) throws Exception;

	public boolean checktOrganizationTypeNameExist(String OrgTypeName) throws Exception;

	public List<DistrictLineDepartment> getLandRegionLineDepartmentListforOrganization(int entityCode, char level, List<Organization> orgList) throws Exception;

	public List<OrgLocatedAtLevels> getOrganizationDetailsLowLevel(int orgCode) throws Exception;

	public List<DistrictLineDepartment> getLandRegionLineDepartmentDetailforOrganization(Integer orgUnitCode) throws Exception;

	public List<DistrictLineDepartment> getLandRegionLineDepartmentList(Integer entityCode, Integer deptCode, char level) throws Exception;
	
	public GovernmentOrder getGovtOrderObject(MinistryForm ministryForm) throws Exception;
	public String getOrgTypeName(Integer typeCode)throws Exception;
	
	public List<DeptAdminUnit> getDeptAdminUnits(int slc,int type) throws Exception;
	
	public boolean saveDeptAdmitUnit(DeptAdminUnit deptAdminUnit) throws Exception;
	
	public boolean adminUnitOradminChildExist(int slc,String deptAdminUnitName,int adminUnitCode,char choiceType,int parentCode)throws Exception;
	
	public DepartmentAdminForm getDeptAdminUnitDetail(int adminUnitCode , char adminType) throws Exception;
	
	public boolean deleteAdminUnitEntity(Integer slc, Integer adminUnitCode,int entityType) throws Exception;
	
	public boolean saveDeptAdmitEntity(DepartmentAdminForm departmentAdminForm) throws Exception;
	
	public List<DeptAdminUnitEntity> getDeptAdminEntity(int slc) throws Exception;
	
	public boolean modifyDepartmentAdminDetail(DepartmentAdminForm dAdminForm,char parentModify) throws Exception;
	
	public List<DeptAdminUnit> getUnitLevelNames(Integer adminUnitCode,Integer slc) throws Exception;
	
	public List<AdministrativeEntityCoverage> getEntityCoverageDetail(Integer entityCode) throws Exception;
	
	public List<DeptAdminUnitEntity> getUnityEntityByParentLevel(Integer slc,int levelCode, int entityCode) throws Exception;
	
	public List<AdministrativeEntityCoverage> adminEntityCoveredArea(int entityCode,int slc,char coverageType) throws Exception;
	
	public boolean modifyEntityCoverage(DepartmentAdminForm departmentAdminForm) throws Exception;
	
	
	public List<Organization> getOrganizationDetailbystateandType(Integer stateCode,int type) throws Exception;
	
	public List<Organization> getOrganizationParent(Integer orgCode,int orgLevel) throws Exception;
	
	public List<Organization> getOrganizationChilds(Integer parentOrgCode) throws Exception;
	
	public boolean updateOrgParentChildUnits(int parentCode,String childOrgUnits) throws Exception;
	
	public List<Organization> getOrganizationSpecificChilds(Integer parentOrgCode) throws Exception;
	
	/*added by chandra on 11-09-14*/
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

	public boolean getUpdateMethod(OrganizationUnitForm orggunit)throws Exception;
	
	public List<OrgLocatedAtLevels> getOrganizationnUnitLevelByOrgLevel(Integer olc)
			throws Exception;
	
	/**
	 * added on 11/01/2015 for getDepartmentDetails by parentorgcode
	 * added locatedAtLevel parameter on 18-06-2015 by pooja
	 */
	public List<Organization> getOrganizationParentName(int orgLevel,int locatedAtLevel) throws Exception;
	/**
	 * added on 11/01/2015 for getDepartmentDetails by parentorgcode
	 */
	
	public List<Organization> getDepartmentDetails(Integer parentorgcode)
			throws Exception;
	/**
	 * added on 11/01/2015 for getOrganizationDetailbySlcCode
	 */
	public List<Organization> getOrganizationDetailbySlcCode(Integer slcCode)
			throws Exception;
	/**
	 * added on 11/01/2015 for tOrganizationeUnitLevel
	 */
	public List <OrganizationUnit>getOrganizationeUnitLevelByorglocatedlevelcode(Integer orglocatedlevelcode)throws Exception;

	/**
	 * added on 18/03/2015 for tOrganizationeUnitLevel
	 */
	List<ExtendDepartment> getOrg_located_at_levelsByOrgCode(Integer orgCode,
			String isCenterFlag) throws Exception;
		
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
	public boolean deleteAdminUnitLevel(Integer slc, Integer adminUnitCode,int entityType) throws Exception;
	
	/**
	 * To update active flag false in case of delete administrative unit level ,entities and their coverages
	 * @author Ripunj on 22-04-2015
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return 
	 * @throws Exception
	 */
	public boolean deleteAdminUnitEntitysetFlagFalse(Integer slc,Integer adminUnitCode, int entityType) throws Exception;
	
	public String modifyDepartmentAdminEntityForDraft(DepartmentAdminForm dAdminForm,char parentModify) throws Exception;
	
	public List<AdministrativeEntityCoverage> getEntityCoverageDetailForDraft(Integer entityCode) throws Exception;
	
	
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
	public boolean modifyEntityCoverageForDraft(DepartmentAdminForm departmentAdminForm) throws Exception;

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
	public List<DeptAdminUnitEntity> getDeptAdminEntityForDistrict(Integer slc,Integer districtCode)throws Exception;
	
	/**
	 * added by pooja on 23-06-2015 for capture Org Unit Details
	 * */
	public List<OrganizationUnit> getOrganizationUnitDetails(Integer orgUnitCode) throws Exception;
	public Object[] getVlcTlcDlc(Integer villageCode) throws Exception;
	public Integer getSlcByLbOrVillageCode(Integer code) throws Exception;
	public boolean checkExistingOrgUnitName(Integer stateCode, String orgUnitName) throws Exception;
	
	/**
	 *  added by pooja on 05-08-2015 
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
	
	public  Map<String, Object> getOrgUnitswiseDeptDetails(Integer orgUnitCode,boolean isParentOrgUnit) throws Exception;
	
	public List<Organization> getOrganizationListbyOrgType(Integer orgTypeCode,Integer stateCode) throws Exception;
	
	public List<Object> getOrganizationAtLevelbyOrgCode(int orgCode) throws Exception;
	
	public boolean publishAdminEntityAll(String adminEntityCodes) throws Exception;
	

	public List<Organization> getOrganizationDetails() throws Exception;

	
	public boolean updateParentAdminEntityDetailsCall(DepartmentAdminForm adminUnitView ,Long userId) throws Exception;
}
