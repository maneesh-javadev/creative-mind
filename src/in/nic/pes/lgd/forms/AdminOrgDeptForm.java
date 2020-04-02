package in.nic.pes.lgd.forms;

import java.io.Serializable;

public class AdminOrgDeptForm implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private Integer	ministryId;
	
	private Integer	unitLevelCode;
	
	private String	departmentNameEnglish;
	
	private String	departmentNameLocal;
	
	private String	departmentShortName;
	
	private String	formAccessLevel;
	
	private Integer parentDepartmentId;
	
	private String 	parentDepartmentName;
	
	private String 	chooseLevelAllOrSpecific;

	private String 	stateIds;
	
	private String 	districtIds;
	
	private String 	subdistrictIds;
	
	private String 	blockIds;
	
	private String 	villageIds;
	
	private String 	adminUnitEntityIds;
	
	private String dpIds;
	
	private String ipIds;
	
	private String vpIds;
	
	private String urbanIds;
	
	private boolean isOrganization;
	
	private Integer deptOrgCode;
	
	private Integer orgType;
	
	private Integer stateCode;
	
	
	/* Variables for Validation Started */
	
	private String districtChkfull;
	
	private String DistrictChkpart;
	
	private String SubDistrictChkfull;
	
	private String SubDistrictChkpart;
	
	private  String ChkFullCoveragestate;
	
	private String ChkPartialCoverage;
	
	
	private String setErrorMsg;
	
	private boolean sameLevel;
	
	private boolean newLevel;
	
	private Integer olc;
	
	/* Variables for Validation Ends*/
	
	/**added by ripunj on 30-09-2014 For Extended Department Functionality*/
	private String orgName;
	private String adminUnitLevelName;
	
	private Integer	parentUnitLevelCode;
	
	private Integer orgLocatedLevelCode;
	
	private Integer searchOrgLocatedLevelCode;
	
	private boolean topNode;
	
	private Integer sortOrder;
	
	private boolean organizationFlow;
	
	private boolean isMinistry;
	
	private String  parentHierarchy;
	
	private String dpName;
	
	private String ipName;
	
	private String vpName;
	
	private String uName;
	
	private String DPChkfull;
	
	private String hierarchyIds;
	
	private Integer orgTypeCode;
	
	private Boolean isAbroad;
	
	private String countryIds;
	
	public String getDPChkfull() {
		return DPChkfull;
	}

	public void setDPChkfull(String dPChkfull) {
		DPChkfull = dPChkfull;
	}

	public String getParentHierarchy() {
		return parentHierarchy;
	}

	public void setParentHierarchy(String parentHierarchy) {
		this.parentHierarchy = parentHierarchy;
	}

	public String getDpName() {
		return dpName;
	}

	public void setDpName(String dpName) {
		this.dpName = dpName;
	}

	public String getIpName() {
		return ipName;
	}

	public void setIpName(String ipName) {
		this.ipName = ipName;
	}

	public String getVpName() {
		return vpName;
	}

	public void setVpName(String vpName) {
		this.vpName = vpName;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMinistry(boolean isMinistry) {
		this.isMinistry = isMinistry;
	}

	/* for saving updated sub office name in organization unit */
	private String updatedSubOfficeList;
	
	public String getSetErrorMsg() {
		return setErrorMsg;
	}

	public void setSetErrorMsg(String setErrorMsg) {
		this.setErrorMsg = setErrorMsg;
	}

	public Integer getMinistryId() {
		return ministryId;
	}

	public void setMinistryId(Integer ministryId) {
		this.ministryId = ministryId;
	}

	public Integer getUnitLevelCode() {
		return unitLevelCode;
	}

	public void setUnitLevelCode(Integer unitLevelCode) {
		this.unitLevelCode = unitLevelCode;
	}

	public String getDepartmentNameEnglish() {
		return departmentNameEnglish;
	}

	public void setDepartmentNameEnglish(String departmentNameEnglish) {
		this.departmentNameEnglish = departmentNameEnglish;
	}

	public String getDepartmentNameLocal() {
		return departmentNameLocal;
	}

	public void setDepartmentNameLocal(String departmentNameLocal) {
		this.departmentNameLocal = departmentNameLocal;
	}

	public String getDepartmentShortName() {
		return departmentShortName;
	}

	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
	}

	public String getFormAccessLevel() {
		return formAccessLevel;
	}

	public void setFormAccessLevel(String formAccessLevel) {
		this.formAccessLevel = formAccessLevel;
	}

	public Integer getParentDepartmentId() {
		return parentDepartmentId;
	}

	public void setParentDepartmentId(Integer parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}

	public String getParentDepartmentName() {
		return parentDepartmentName;
	}

	public void setParentDepartmentName(String parentDepartmentName) {
		this.parentDepartmentName = parentDepartmentName;
	}

	public String getChooseLevelAllOrSpecific() {
		return chooseLevelAllOrSpecific;
	}

	public void setChooseLevelAllOrSpecific(String chooseLevelAllOrSpecific) {
		this.chooseLevelAllOrSpecific = chooseLevelAllOrSpecific;
	}
	
	public String getDistrictIds() {
		return districtIds;
	}

	public void setDistrictIds(String districtIds) {
		this.districtIds = districtIds;
	}
	

	public String getStateIds() {
		return stateIds;
	}

	public void setStateIds(String stateIds) {
		this.stateIds = stateIds;
	}
	
	

	public String getSubdistrictIds() {
		return subdistrictIds;
	}

	public void setSubdistrictIds(String subdistrictIds) {
		this.subdistrictIds = subdistrictIds;
	}

	
	public String getVillageIds() {
		return villageIds;
	}

	public void setVillageIds(String villageIds) {
		this.villageIds = villageIds;
	}

	public String getBlockIds() {
		return blockIds;
	}

	public void setBlockIds(String blockIds) {
		this.blockIds = blockIds;
	}
	
	public String getAdminUnitEntityIds() {
		return adminUnitEntityIds;
	}

	public void setAdminUnitEntityIds(String adminUnitEntityIds) {
		this.adminUnitEntityIds = adminUnitEntityIds;
	}

	public boolean isOrganization() {
		return isOrganization;
	}

	public void setOrganization(boolean isOrganization) {
		this.isOrganization = isOrganization;
	}

	public Integer getDeptOrgCode() {
		return deptOrgCode;
	}

	public void setDeptOrgCode(Integer deptOrgCode) {
		this.deptOrgCode = deptOrgCode;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	
	public String getChkPartialCoverage() {
		return ChkPartialCoverage;
	}

	public void setChkPartialCoverage(String chkPartialCoverage) {
		ChkPartialCoverage = chkPartialCoverage;
	}

	public String getChkFullCoveragestate() {
		return ChkFullCoveragestate;
	}

	public void setChkFullCoveragestate(String chkFullCoveragestate) {
		ChkFullCoveragestate = chkFullCoveragestate;
	}

	public String getDistrictChkfull() {
		return districtChkfull;
	}

	public void setDistrictChkfull(String districtChkfull) {
		this.districtChkfull = districtChkfull;
	}

	public String getDistrictChkpart() {
		return DistrictChkpart;
	}

	public void setDistrictChkpart(String districtChkpart) {
		DistrictChkpart = districtChkpart;
	}

	public String getSubDistrictChkfull() {
		return SubDistrictChkfull;
	}

	public void setSubDistrictChkfull(String subDistrictChkfull) {
		SubDistrictChkfull = subDistrictChkfull;
	}

	public String getSubDistrictChkpart() {
		return SubDistrictChkpart;
	}

	public void setSubDistrictChkpart(String subDistrictChkpart) {
		SubDistrictChkpart = subDistrictChkpart;
	}

	

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAdminUnitLevelName() {
		return adminUnitLevelName;
	}

	public void setAdminUnitLevelName(String adminUnitLevelName) {
		this.adminUnitLevelName = adminUnitLevelName;
	}

	
	public Integer getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}

	public void setOrgLocatedLevelCode(Integer orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " AdminOrgDeptForm [" +
			   " unitLevelCode =" + unitLevelCode + ", " +
			   " parentDepartmentId =" + parentDepartmentId + ",]";
	}

	public boolean isSameLevel() {
		return sameLevel;
	}

	public void setSameLevel(boolean sameLevel) {
		this.sameLevel = sameLevel;
	}

	public Integer getOlc() {
		return olc;
	}

	public void setOlc(Integer olc) {
		this.olc = olc;
	}

	public boolean isNewLevel() {
		return newLevel;
	}

	public void setNewLevel(boolean newLevel) {
		this.newLevel = newLevel;
	}

	public Integer getParentUnitLevelCode() {
		return parentUnitLevelCode;
	}

	public void setParentUnitLevelCode(Integer parentUnitLevelCode) {
		this.parentUnitLevelCode = parentUnitLevelCode;
	}

	public Integer getSearchOrgLocatedLevelCode() {
		return searchOrgLocatedLevelCode;
	}

	public void setSearchOrgLocatedLevelCode(Integer searchOrgLocatedLevelCode) {
		this.searchOrgLocatedLevelCode = searchOrgLocatedLevelCode;
	}

	public boolean isTopNode() {
		return topNode;
	}

	public void setTopNode(boolean topNode) {
		this.topNode = topNode;
	}

	public String getUpdatedSubOfficeList() {
		return updatedSubOfficeList;
	}

	public void setUpdatedSubOfficeList(String updatedSubOfficeList) {
		this.updatedSubOfficeList = updatedSubOfficeList;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public boolean isOrganizationFlow() {
		return organizationFlow;
	}

	public void setOrganizationFlow(boolean organizationFlow) {
		this.organizationFlow = organizationFlow;
	}

	public boolean getIsMinistry() {
		return isMinistry;
	}

	public void setIsMinistry(boolean isMinistry) {
		this.isMinistry = isMinistry;
	}

	public String getDpIds() {
		return dpIds;
	}

	public void setDpIds(String dpIds) {
		this.dpIds = dpIds;
	}

	public String getIpIds() {
		return ipIds;
	}

	public void setIpIds(String ipIds) {
		this.ipIds = ipIds;
	}

	public String getVpIds() {
		return vpIds;
	}

	public void setVpIds(String vpIds) {
		this.vpIds = vpIds;
	}

	public String getUrbanIds() {
		return urbanIds;
	}

	public void setUrbanIds(String urbanIds) {
		this.urbanIds = urbanIds;
	}

	public String getHierarchyIds() {
		return hierarchyIds;
	}

	public void setHierarchyIds(String hierarchyIds) {
		this.hierarchyIds = hierarchyIds;
	}

	public Integer getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(Integer orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public Boolean getIsAbroad() {
		return isAbroad;
	}

	public void setIsAbroad(Boolean isAbroad) {
		this.isAbroad = isAbroad;
	}

	public String getCountryIds() {
		return countryIds;
	}

	public void setCountryIds(String countryIds) {
		this.countryIds = countryIds;
	}


	



}
