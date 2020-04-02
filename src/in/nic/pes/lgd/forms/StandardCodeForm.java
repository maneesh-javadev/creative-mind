package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.StandardCodes;

import java.util.List;


public class StandardCodeForm {
	
	 private int entity_code;
	 private String entityTpye; 
	 private String entity_name_english;
	 private String entity_name_local;
	 private String entity_2001_code;
	 private String sscode;
	 private String entityName;
	 private String stateCode;
	 private String districtCode;
	 private String subdistrictCodes;
	 private String intermediatePanchyat;
	 private String disPanchyat;
	 private String Tiertype;
	 private String districtPanchyat;
	 private List<StandardCodes> standardCodesDetails;
	 private List<StandardCodeDataForm> standardCodeDataDetails;
	 private Integer adminCode;
	 private Integer parentAdminCode;
	 private String entityCodes;
	 private String category;
	 private Integer deptList;
	 private Integer orgCode;
	 private boolean entityTypeFlag;
	 private Integer userId;
	 
	public int getEntity_code() {
		return entity_code;
	}
	public void setEntity_code(int entity_code) {
		this.entity_code = entity_code;
	}
	public String getEntity_name_english() {
		return entity_name_english;
	}
	public void setEntity_name_english(String entity_name_english) {
		this.entity_name_english = entity_name_english;
	}
	public String getEntity_name_local() {
		return entity_name_local;
	}
	public void setEntity_name_local(String entity_name_local) {
		this.entity_name_local = entity_name_local;
	}
	public String getEntity_2001_code() {
		return entity_2001_code;
	}
	public void setEntity_2001_code(String entity_2001_code) {
		this.entity_2001_code = entity_2001_code;
	}
	public String getSscode() {
		return sscode;
	}
	public void setSscode(String sscode) {
		this.sscode = sscode;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getSubdistrictCodes() {
		return subdistrictCodes;
	}
	public void setSubdistrictCodes(String subdistrictCodes) {
		this.subdistrictCodes = subdistrictCodes;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public List<StandardCodes> getStandardCodesDetails() {
		return standardCodesDetails;
	}
	public void setStandardCodesDetails(List<StandardCodes> standardCodesDetails) {
		this.standardCodesDetails = standardCodesDetails;
	}
	public List<StandardCodeDataForm> getStandardCodeDataDetails() {
		return standardCodeDataDetails;
	}
	public void setStandardCodeDataDetails(List<StandardCodeDataForm> standardCodeDataDetails) {
		this.standardCodeDataDetails = standardCodeDataDetails;
	}
	public String getIntermediatePanchyat() {
		return intermediatePanchyat;
	}
	public void setIntermediatePanchyat(String intermediatePanchyat) {
		this.intermediatePanchyat = intermediatePanchyat;
	}
	public String getDisPanchyat() {
		return disPanchyat;
	}
	public void setDisPanchyat(String disPanchyat) {
		this.disPanchyat = disPanchyat;
	}
	public String getTiertype() {
		return Tiertype;
	}
	public void setTiertype(String tiertype) {
		Tiertype = tiertype;
	}
	public String getDistrictPanchyat() {
		return districtPanchyat;
	}
	public void setDistrictPanchyat(String districtPanchyat) {
		this.districtPanchyat = districtPanchyat;
	}
	public Integer getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(Integer adminCode) {
		this.adminCode = adminCode;
	}
	public Integer getParentAdminCode() {
		return parentAdminCode;
	}
	public void setParentAdminCode(Integer parentAdminCode) {
		this.parentAdminCode = parentAdminCode;
	}
	public String getEntityTpye() {
		return entityTpye;
	}
	public void setEntityTpye(String entityTpye) {
		this.entityTpye = entityTpye;
	}
	public String getEntityCodes() {
		return entityCodes;
	}
	public void setEntityCodes(String entityCodes) {
		this.entityCodes = entityCodes;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getDeptList() {
		return deptList;
	}
	public void setDeptList(Integer deptList) {
		this.deptList = deptList;
	}
	public Integer getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}
	public boolean isEntityTypeFlag() {
		return entityTypeFlag;
	}
	public void setEntityTypeFlag(boolean entityTypeFlag) {
		this.entityTypeFlag = entityTypeFlag;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
  	

}
