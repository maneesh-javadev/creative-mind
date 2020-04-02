package in.nic.pes.lgd.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class DepartmentForm {
	
	private String ministryName;
	@NotEmpty(message = "Please enter the department name.")
	//@Pattern(regexp="^[a-zA-Z 0-9]+$", message="Please use [A-Z], [a-z], space in name.")
	private String deptName;
	private String deptNamecr;
	//@Pattern(regexp="^[a-zA-Z\\s]+$", message="Please use alphabets and spaces only")
	private String deptNameLocal;
	//@Pattern(regexp="^[a-zA-Z\\s]+$", message="Please use alphabets and spaces only")
	private String shortDeptName;
	private String address;
	private String specificLevel;
	private String specificLevelstate;
	private String specificLeveldistrict;
	private String specificLevelsubdistrict;
	private String specificLevelvillage;
	private String levelradio;
	private String stateName;
	private String districtName;
	private String subdistrictName;
	private String BlockName;
	private String villageName;
	private String parent;
	private String addAnother;
	private String orgName;
	private Integer orgCode;
	private Integer orgVersion;
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;	
	private boolean correction;
	private boolean isOrganization;
	private String orgType;
	private String deptOrgCode;
	private boolean isAUM;
	private char isLocalBodyTypeSpecifc;
	private int localBodyTypeCode;
	private int localBodyCode;
	private char isLocalBodySpecifc;
	private boolean levelNew;
	private char orgLevel;
	
	/**added by ripunj on 30-09-2014*/
	
	private String adminUnitLevelName;
	
	 //List<Department> listDepartmentDetail = new ArrayList<Department>();
	private List<DepartmentDataForm> listDepartmentDetails = new ArrayList<DepartmentDataForm>();
	private int orgLocatedlevelCode;
	
	//Code added by Arnab  Start
	
	private Integer departmentId;	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	//Code added by Arnab End
	 
	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getSubdistrictName() {
		return subdistrictName;
	}

	public void setSubdistrictName(String subdistrictName) {
		this.subdistrictName = subdistrictName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getLevelradio() {
		return levelradio;
	}

	public void setLevelradio(String levelradio) {
		this.levelradio = levelradio;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressLine4;
	
	private String phoneNo;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return addressLine4;
	}

	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}



	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getShortDeptName() {
		return shortDeptName;
	}

	public void setShortDeptName(String shortDeptName) {
		this.shortDeptName = shortDeptName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getMinistryName() {
		return ministryName;
	}

	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}

	public List<DepartmentDataForm> getListDepartmentDetails() {
		return listDepartmentDetails;
	}

	public void setListDepartmentDetails(
			List<DepartmentDataForm> listDepartmentDetails) {
		this.listDepartmentDetails = listDepartmentDetails;
	}

	public String getDeptNameLocal() {
		return deptNameLocal;
	}

	public void setDeptNameLocal(String deptNameLocal) {
		this.deptNameLocal = deptNameLocal;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getSpecificLevel() {
		return specificLevel;
	}

	public void setSpecificLevel(String specificLevel) {
		this.specificLevel = specificLevel;
	}

	public String getAddAnother() {
		return addAnother;
	}

	public void setAddAnother(String addAnother) {
		this.addAnother = addAnother;
	}
	public String getDeptNamecr() {
		return deptNamecr;
	}

	public void setDeptNamecr(String deptNamecr) {
		this.deptNamecr = deptNamecr;
	}

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrdereffectiveDate() {
		return ordereffectiveDate;
	}

	public void setOrdereffectiveDate(Date ordereffectiveDate) {
		this.ordereffectiveDate = ordereffectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	public String getOrderPath() {
		return orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}

	

	public boolean isCorrection() {
		return correction;
	}

	public void setCorrection(boolean correction) {
		this.correction = correction;
	}

	public boolean isOrganization() {
		return isOrganization;
	}

	public void setOrganization(boolean isOrganization) {
		this.isOrganization = isOrganization;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getDeptOrgCode() {
		return deptOrgCode;
	}

	public void setDeptOrgCode(String deptOrgCode) {
		this.deptOrgCode = deptOrgCode;
	}

	public boolean isAUM() {
		return isAUM;
	}

	public void setAUM(boolean isAUM) {
		this.isAUM = isAUM;
	}

	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public int getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public char getIsLocalBodySpecifc() {
		return isLocalBodySpecifc;
	}

	public void setIsLocalBodySpecifc(char isLocalBodySpecifc) {
		this.isLocalBodySpecifc = isLocalBodySpecifc;
	}

	public char getIsLocalBodyTypeSpecifc() {
		return isLocalBodyTypeSpecifc;
	}

	public void setIsLocalBodyTypeSpecifc(char isLocalBodyTypeSpecifc) {
		this.isLocalBodyTypeSpecifc = isLocalBodyTypeSpecifc;
	}

	public String getBlockName() {
		return BlockName;
	}

	public void setBlockName(String blockName) {
		BlockName = blockName;
	}

	public String getSpecificLevelstate() {
		return specificLevelstate;
	}

	public void setSpecificLevelstate(String specificLevelstate) {
		this.specificLevelstate = specificLevelstate;
	}

	public String getSpecificLeveldistrict() {
		return specificLeveldistrict;
	}

	public void setSpecificLeveldistrict(String specificLeveldistrict) {
		this.specificLeveldistrict = specificLeveldistrict;
	}

	public String getSpecificLevelsubdistrict() {
		return specificLevelsubdistrict;
	}

	public void setSpecificLevelsubdistrict(String specificLevelsubdistrict) {
		this.specificLevelsubdistrict = specificLevelsubdistrict;
	}

	public String getSpecificLevelvillage() {
		return specificLevelvillage;
	}

	public void setSpecificLevelvillage(String specificLevelvillage) {
		this.specificLevelvillage = specificLevelvillage;
	}

	public int getOrgLocatedlevelCode() {
		return orgLocatedlevelCode;
	}

	public void setOrgLocatedlevelCode(int orgLocatedlevelCode) {
		this.orgLocatedlevelCode = orgLocatedlevelCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	

	public Integer getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getOrgVersion() {
		return orgVersion;
	}

	public void setOrgVersion(Integer orgVersion) {
		this.orgVersion = orgVersion;
	}


	public boolean isLevelNew() {
		return levelNew;
	}

	public void setLevelNew(boolean levelNew) {
		this.levelNew = levelNew;
	}

	public char getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(char orgLevel) {
		this.orgLevel = orgLevel;
	}

	
	public String getAdminUnitLevelName() {
		return adminUnitLevelName;
	}

	public void setAdminUnitLevelName(String adminUnitLevelName) {
		this.adminUnitLevelName = adminUnitLevelName;
	}

	@Override
	public String toString() {
		return "DepartmentForm [ministryName=" + ministryName + ", deptName=" + deptName + ", deptNamecr=" + deptNamecr + ", deptNameLocal=" + deptNameLocal + ", shortDeptName=" + shortDeptName + ", orgName=" + orgName + ", orgCode=" + orgCode
				+ ", orgType=" + orgType + ", orgLevel=" + orgLevel + "]";
	}

}