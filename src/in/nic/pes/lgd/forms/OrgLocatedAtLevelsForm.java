package in.nic.pes.lgd.forms;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class OrgLocatedAtLevelsForm {

	private Integer orgLocatedLevelCode;
	private Integer locatedAtLevel;
	private String orgLevelSpecificName;
	private String orgLevelSpecificNameLocal;
	private String orgLevelSpecificShortName;
	private Integer parentOrgLocatedLevelCode;
	private boolean isactive;
	private Integer olc;
	private Integer sortOrder;
	private Integer orgLocatedLevelVersion;
	private Date effectiveDate;
	private Long lastUpdatedBy;
	private Integer transactionId;
	private Integer orgType;

	// governmentOrder

	private String govtOrder;
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;

	private String requiredTitle;// Title Required Flag
	private String uniqueTitle;// Unique Title Required Flag
	private int allowedNoOfAttachment;// Allowed Number Of Attachment
	private long allowedTotalFileSize;// Allowed Total File Size
	private long allowedIndividualFileSize;// Allowed Individual File Size.
	private String allowedFileType;// Allowed File Type
	private String uploadLocation;// File Upload Location
	private String showTitle;// Entered Title Value

	private String requiredTitle1;
	private String maxFileLimit1;
	private String[] fileTitle1;
	private List<CommonsMultipartFile> attachFile;// Attached File List

	// Getters and Setters
	public Integer getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}

	public void setOrgLocatedLevelCode(Integer orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}

	public Integer getLocatedAtLevel() {
		return locatedAtLevel;
	}

	public void setLocatedAtLevel(Integer locatedAtLevel) {
		this.locatedAtLevel = locatedAtLevel;
	}

	public String getOrgLevelSpecificName() {
		return orgLevelSpecificName;
	}

	public void setOrgLevelSpecificName(String orgLevelSpecificName) {
		this.orgLevelSpecificName = orgLevelSpecificName;
	}

	public String getOrgLevelSpecificNameLocal() {
		return orgLevelSpecificNameLocal;
	}

	public void setOrgLevelSpecificNameLocal(String orgLevelSpecificNameLocal) {
		this.orgLevelSpecificNameLocal = orgLevelSpecificNameLocal;
	}

	public String getOrgLevelSpecificShortName() {
		return orgLevelSpecificShortName;
	}

	public void setOrgLevelSpecificShortName(String orgLevelSpecificShortName) {
		this.orgLevelSpecificShortName = orgLevelSpecificShortName;
	}

	public Integer getParentOrgLocatedLevelCode() {
		return parentOrgLocatedLevelCode;
	}

	public void setParentOrgLocatedLevelCode(Integer parentOrgLocatedLevelCode) {
		this.parentOrgLocatedLevelCode = parentOrgLocatedLevelCode;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Integer getOlc() {
		return olc;
	}

	public void setOlc(Integer olc) {
		this.olc = olc;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getOrgLocatedLevelVersion() {
		return orgLocatedLevelVersion;
	}

	public void setOrgLocatedLevelVersion(Integer orgLocatedLevelVersion) {
		this.orgLocatedLevelVersion = orgLocatedLevelVersion;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public String getGovtOrder() {
		return govtOrder;
	}

	public void setGovtOrder(String govtOrder) {
		this.govtOrder = govtOrder;
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

	public String getRequiredTitle1() {
		return requiredTitle1;
	}

	public void setRequiredTitle1(String requiredTitle1) {
		this.requiredTitle1 = requiredTitle1;
	}

	public String getMaxFileLimit1() {
		return maxFileLimit1;
	}

	public void setMaxFileLimit1(String maxFileLimit1) {
		this.maxFileLimit1 = maxFileLimit1;
	}

	public String[] getFileTitle1() {
		return fileTitle1;
	}

	public void setFileTitle1(String[] fileTitle1) {
		this.fileTitle1 = fileTitle1;
	}

	public List<CommonsMultipartFile> getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(List<CommonsMultipartFile> attachFile) {
		this.attachFile = attachFile;
	}

}
