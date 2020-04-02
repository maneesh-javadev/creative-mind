package in.nic.pes.lgd.forms;

//import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictLineDepartment;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.LocalBodyTypeWiseDepartment;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.StateLineDepartment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MinistryForm {

	@NotEmpty(message = "Please enter the name of ministry.")
	private String ministryName;
	private String ministryName1;
	private String ministryNamecr;
	private int ministryCode;
	private String shortministryName;
	private String deptName;
	private int deptCode;
	private String shortdeptName;
	private int districtCode;
	private int subdistrictCode;
	private int villageCode;
	private int blockCode;
	private int orgCode;
	private String orgName;
	private String shortName;
	private int orgTypeCode;
	private int orgVersion;
	private boolean localBodySpecific;
	private String code;
	private String stateNameEnglish;
	private boolean correction;
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;	
	private boolean stateChecked;
	private String selectLevel;
	private int orgLocatedAtLevelCode;
	private int localBodyCode;
	private char category;
	private String districtNameEnglish;
	private String subdistrictNameEnglish;
	private String villageNameEnglish;
	private String blockNameEnglish;
	private int localBodyTypeCode;
	private String reason;
	private int limit;
	private int offset;
	private Integer entityCode;
	private GovernmentOrder governmentOrder;
	private List<CommonsMultipartFile> attachFile1;
	private Integer adminUnitCode;
	//Code added by Arnab  Start

	/**
	 * 
	 * @author Anchal Todariya
	 * 
	 * added for :- org unit report
	 * 
	 * */
	private String unitLvlDept;
	private String captchaAnswer;
	private Integer orgUnitCode;
	private String radioUser;
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	private Integer ministryId;
	private Integer departmentId;
	private Integer organizationId;
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getMinistryId() {
		return ministryId;
	}

	public void setMinistryId(Integer ministryId) {
		this.ministryId = ministryId;
	}
	
	//Code added by Arnab End
/*	private String localBodyTypeName;
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}*/



	private Date effectiveDate;
	
	public String getMinistryName1() {
		return ministryName1;
	}

	public void setMinistryName1(String ministryName1) {
		this.ministryName1 = ministryName1;
	}

	//new
	private String requiredTitle;//Title Required Flag
	private String uniqueTitle;//Unique Title Required Flag
	private int allowedNoOfAttachment;//Allowed Number Of Attachment
	private long allowedTotalFileSize;//Allowed Total File Size
	private long allowedIndividualFileSize;//Allowed Individual File Size.
	private String allowedFileType;//Allowed File Type
	private String uploadLocation;//File Upload Location
	private String showTitle;//Entered Title Value
	private List<CommonsMultipartFile> attachedFile;//Attached File List
		
	private List<MinistryForm> listMinistry = new ArrayList<MinistryForm>();
	private List<Organization> listMinistryDetail = new ArrayList<Organization>();
	private List<Organization> listDeptDetails = new ArrayList<Organization>();
	private List<Organization> listOrgDetails = new ArrayList<Organization>();
	private List<StateLineDepartment> listDept = new ArrayList<StateLineDepartment>();
	private List<DistrictLineDepartment> listDistDept = new ArrayList<DistrictLineDepartment>();
	private List<LocalBodyTypeWiseDepartment> listLBTWiseDeptList = new ArrayList<LocalBodyTypeWiseDepartment>();
	
    
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}

	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}

	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}

	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	public List<DistrictLineDepartment> getListDistDept() {
		return listDistDept;
	}

	public void setListDistDept(List<DistrictLineDepartment> listDistDept) {
		this.listDistDept = listDistDept;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public List<StateLineDepartment> getListDept() {
		return listDept;
	}

	public void setListDept(List<StateLineDepartment> listDept) {
		this.listDept = listDept;
	}

	public char getCategory() {
		return category;
	}

	public void setCategory(char category) {
		this.category = category;
	}

	public int getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public int getOrgLocatedAtLevelCode() {
		return orgLocatedAtLevelCode;
	}

	public void setOrgLocatedAtLevelCode(int orgLocatedAtLevelCode) {
		this.orgLocatedAtLevelCode = orgLocatedAtLevelCode;
	}

	public String getSelectLevel() {
		return selectLevel;
	}

	public void setSelectLevel(String selectLevel) {
		this.selectLevel = selectLevel;
	}

	public boolean isStateChecked() {
		return stateChecked;
	}

	public void setStateChecked(boolean stateChecked) {
		this.stateChecked = stateChecked;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(int deptCode) {
		this.deptCode = deptCode;
	}

	public String getShortdeptName() {
		return shortdeptName;
	}

	public void setShortdeptName(String shortdeptName) {
		this.shortdeptName = shortdeptName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<Organization> getListDeptDetails() {
		return listDeptDetails;
	}

	public void setListDeptDetails(List<Organization> listDeptDetails) {
		this.listDeptDetails = listDeptDetails;
	}

	public int getMinistryCode() {
		return ministryCode;
	}

	public void setMinistryCode(int ministryCode) {
		this.ministryCode = ministryCode;
	}

	public List<Organization> getListMinistryDetail() {
		return listMinistryDetail;
	}

	public void setListMinistryDetail(List<Organization> listMinistryDetail) {
		this.listMinistryDetail = listMinistryDetail;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMinistryName() {
		return ministryName;
	}

	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}

	public String getShortministryName() {
		return shortministryName;
	}

	public void setShortministryName(String shortministryName) {
		this.shortministryName = shortministryName;
	}

	public List<MinistryForm> getListMinistry() {
		return listMinistry;
	}

	public void setListMinistry(List<MinistryForm> listMinistry) {
		this.listMinistry = listMinistry;
	}

	public boolean isCorrection() {
		return correction;
	}

	public void setCorrection(boolean correction) {
		this.correction = correction;
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

	public Date getOrdereffectiveDate() {
		return ordereffectiveDate;
	}

	public void setOrdereffectiveDate(Date ordereffectiveDate) {
		this.ordereffectiveDate = ordereffectiveDate;
	}

	public int getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}

	public int getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(int orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public int getOrgVersion() {
		return orgVersion;
	}

	public void setOrgVersion(int orgVersion) {
		this.orgVersion = orgVersion;
	}

	public boolean isLocalBodySpecific() {
		return localBodySpecific;
	}

	public void setLocalBodySpecific(boolean localBodySpecific) {
		this.localBodySpecific = localBodySpecific;
	}

	public String getMinistryNamecr() {
		return ministryNamecr;
	}

	public void setMinistryNamecr(String ministryNamecr) {
		this.ministryNamecr = ministryNamecr;
	}

	public List<Organization> getListOrgDetails() {
		return listOrgDetails;
	}

	public void setListOrgDetails(List<Organization> listOrgDetails) {
		this.listOrgDetails = listOrgDetails;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}
	
	public int getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(int subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public int getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
	}

	public int getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(int blockCode) {
		this.blockCode = blockCode;
	}

	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public List<LocalBodyTypeWiseDepartment> getListLBTWiseDeptList() {
		return listLBTWiseDeptList;
	}

	public void setListLBTWiseDeptList(
			List<LocalBodyTypeWiseDepartment> listLBTWiseDeptList) {
		this.listLBTWiseDeptList = listLBTWiseDeptList;
	}

	public String getRequiredTitle() {
		return requiredTitle;
	}

	public void setRequiredTitle(String requiredTitle) {
		this.requiredTitle = requiredTitle;
	}

	public String getUniqueTitle() {
		return uniqueTitle;
	}

	public void setUniqueTitle(String uniqueTitle) {
		this.uniqueTitle = uniqueTitle;
	}

	public int getAllowedNoOfAttachment() {
		return allowedNoOfAttachment;
	}

	public void setAllowedNoOfAttachment(int allowedNoOfAttachment) {
		this.allowedNoOfAttachment = allowedNoOfAttachment;
	}

	public long getAllowedTotalFileSize() {
		return allowedTotalFileSize;
	}

	public void setAllowedTotalFileSize(long allowedTotalFileSize) {
		this.allowedTotalFileSize = allowedTotalFileSize;
	}

	public long getAllowedIndividualFileSize() {
		return allowedIndividualFileSize;
	}

	public void setAllowedIndividualFileSize(long allowedIndividualFileSize) {
		this.allowedIndividualFileSize = allowedIndividualFileSize;
	}

	public String getAllowedFileType() {
		return allowedFileType;
	}

	public void setAllowedFileType(String allowedFileType) {
		this.allowedFileType = allowedFileType;
	}

	public String getUploadLocation() {
		return uploadLocation;
	}

	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public List<CommonsMultipartFile> getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(List<CommonsMultipartFile> attachedFile) {
		this.attachedFile = attachedFile;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public GovernmentOrder getGovernmentOrder() {
		return governmentOrder;
	}

	public void setGovernmentOrder(GovernmentOrder governmentOrder) {
		this.governmentOrder = governmentOrder;
	}

	public List<CommonsMultipartFile> getAttachFile1() {
		return attachFile1;
	}

	public void setAttachFile1(List<CommonsMultipartFile> attachFile1) {
		this.attachFile1 = attachFile1;
	}

	public Integer getAdminUnitCode() {
		return adminUnitCode;
	}

	public void setAdminUnitCode(Integer adminUnitCode) {
		this.adminUnitCode = adminUnitCode;
	}

	/**
	 * @return the unitLvlDept
	 */
	public String getUnitLvlDept() {
		return unitLvlDept;
	}

	/**
	 * @param unitLvlDept the unitLvlDept to set
	 */
	public void setUnitLvlDept(String unitLvlDept) {
		this.unitLvlDept = unitLvlDept;
	}

	/**
	 * @return the captchaAnswer
	 */
	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	/**
	 * @param captchaAnswer the captchaAnswer to set
	 */
	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}
	/**
	 * @return the orgUnitCode
	 */
	public Integer getOrgUnitCode() {
		return orgUnitCode;
	}

	/**
	 * @param orgUnitCode the orgUnitCode to set
	 */
	public void setOrgUnitCode(Integer orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}
	public String getRadioUser() {
		return radioUser;
	}

	public void setRadioUser(String radioUser) {
		this.radioUser = radioUser;
	}	
	
	
	
}
