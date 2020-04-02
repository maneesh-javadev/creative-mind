package com.cmc.lgd.localbody.entities;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cmc.lgd.localbody.rules.BusinessRulesConstraint;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.GovernmentOrder;


@BusinessRulesConstraint
public class LocalBodyForm {

	private Integer id;
	
	private Integer localBodyCode;
	
	private Integer localBodyVersion;
	
	private String localBodyCreationType;

	private String localBodyNameEnglish;

	private String localBodyNameLocal;

	private String localBodyAliasEnglish;

	private String localBodyAliasLocal;

	private String stateSpecificCode;

	private Character pesaActImpl;

	private String lbTypeHierarchy;

	private String localBodyTypePanchayat; // Used in PRI and Traditional with concatenated values in Select Option 
	
	private Integer localBodyTypeCode;

	private Integer localBodySubtypeCode;

	private String localBodyLevelCodes;

	private Boolean checkboxCoverageLB;

	private Boolean checkboxCoverageUnmapped;

	private String headQuarterCode;

	private String longitude;

	private String latitude;
	
	private Integer lbCoveredRegionCode;
	
	private Attachment attachments;
	
	private Integer attachmentId;
	
	private GovernmentOrder governmentOrder;
	
	
	private Integer  entityWiseId ;
	

	

	public Integer getEntityWiseId()
	{
		return entityWiseId;
	}

	public void setEntityWiseId(Integer entityWiseId)
	{
		this.entityWiseId = entityWiseId;
	}

	public GovernmentOrder getGovernmentOrder()
	{
		return governmentOrder;
	}

	public void setGovernmentOrder(GovernmentOrder governmentOrder)
	{
		this.governmentOrder = governmentOrder;
	}

	public Attachment getAttachments()
	{
		return attachments;
	}

	public void setAttachments(Attachment attachments)
	{
		this.attachments = attachments;
	}

	/*
	 * Government Order related variables defined
	 */
	private Integer orderCode;

	private String orderNo;

	private Date orderDate;

	private Date effectiveDate;

	private Date gazPubDate;

	private List<CommonsMultipartFile> gazettePublicationUpload;
	
	private List<CommonsMultipartFile> mapUpload;
	
	public MultipartFile file;
		

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	private Integer templateId;
	
	private String editorTemplateDetails;
	
	/*
	 * Existing Local Body and Land Region Coverage related variables defined.
	 */
	private String contributingLBCodes;

	private String contributingLBDistrictCodes;

	private String contributingLBSubDistrictCodes;

	private String contributingLBVillageCodes;

	private String contributingUnmappedDistrictCodes;

	private String contributingUnmappedSubDistrictCodes;

	private String contributingUnmappedVillageCodes;
	
	private String contributingHabiationCodes;
	
	private String avilableHabitation;

	/*
	 * Common Attributes
	 */
	private Integer operationCode;

	private Integer flagCode;

	private Integer createdBy;

	private Integer stateCode;
	
	private Integer districtCode;

	/*
	 * Miscellaneous Attributes, Used when transforming from Temp Table to
	 * LocalBodyForm
	 */
	private String orderPath;
	
	private Long orderFileSize;
	
	private String orderFileContentType;
	
	private String mapUploadPath;
	
	private Long mapFileSize;
	
	private String mapFileContentType;
	
	private String contributingLandRegionCodes;

	private Integer parentLocalBodyCode;

	private String coordinates;

	private String description;
	
	private String processAction;
	
	private Boolean isResetedCoverage;
	
	private String hidContributingLBCodes;
	
	private String hidContributingLandRegionCodes;
	
	private String isdisturbed;
	
	private String validationAction;
	
	private String fileLocation;
	
	private String newFilename;

	/** Primary Key for the table 'draft_rename_localbody_temp' **/
	private Integer renameId;

	/* 
	 * Variable to store Removed Land Regions in Change Coverage
	 */
	private String removedLangRegionCodes;
	

	/* 
	 * Variable to store change coverage type of Land Regions  in Mapped Covered Area 
	 */
	private String changeCoverageTypeLRList;
	
	/*
	 * Variable to hold effective date value to display as label and 
	 * used for validation also.
	 */
	private Date iParamEffectiveDate;
	
	/* 
	 * variable for GIS Map 
	 */
	private String isGISCoverage;
	
	
	private Integer minorVersion;
	
	/*
	 * Default Constructor.
	 */
	public LocalBodyForm() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	public Integer getLocalBodyVersion() {
		return localBodyVersion;
	}

	public void setLocalBodyVersion(Integer localBodyVersion) {
		this.localBodyVersion = localBodyVersion;
	}

	public String getLocalBodyCreationType() {
		return localBodyCreationType;
	}

	public void setLocalBodyCreationType(String localBodyCreationType) {
		this.localBodyCreationType = localBodyCreationType;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

	public String getLocalBodyAliasEnglish() {
		return localBodyAliasEnglish;
	}

	public void setLocalBodyAliasEnglish(String localBodyAliasEnglish) {
		this.localBodyAliasEnglish = localBodyAliasEnglish;
	}

	public String getLocalBodyAliasLocal() {
		return localBodyAliasLocal;
	}

	public void setLocalBodyAliasLocal(String localBodyAliasLocal) {
		this.localBodyAliasLocal = localBodyAliasLocal;
	}

	public String getStateSpecificCode() {
		return stateSpecificCode;
	}

	public void setStateSpecificCode(String stateSpecificCode) {
		this.stateSpecificCode = stateSpecificCode;
	}

	

	public Character getPesaActImpl() {
		return pesaActImpl;
	}

	public void setPesaActImpl(Character pesaActImpl) {
		this.pesaActImpl = pesaActImpl;
	}

	public String getLbTypeHierarchy() {
		return lbTypeHierarchy;
	}

	public void setLbTypeHierarchy(String lbTypeHierarchy) {
		this.lbTypeHierarchy = lbTypeHierarchy;
	}

	public String getLocalBodyTypePanchayat() {
		return localBodyTypePanchayat;
	}

	public void setLocalBodyTypePanchayat(String localBodyTypePanchayat) {
		this.localBodyTypePanchayat = localBodyTypePanchayat;
	}
	
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public Integer getLocalBodySubtypeCode() {
		return localBodySubtypeCode;
	}

	public void setLocalBodySubtypeCode(Integer localBodySubtypeCode) {
		this.localBodySubtypeCode = localBodySubtypeCode;
	}

	public String getLocalBodyLevelCodes() {
		return localBodyLevelCodes;
	}

	public void setLocalBodyLevelCodes(String localBodyLevelCodes) {
		this.localBodyLevelCodes = localBodyLevelCodes;
	}

	public Boolean getCheckboxCoverageLB() {
		return checkboxCoverageLB;
	}

	public void setCheckboxCoverageLB(Boolean checkboxCoverageLB) {
		this.checkboxCoverageLB = checkboxCoverageLB;
	}

	public Boolean getCheckboxCoverageUnmapped() {
		return checkboxCoverageUnmapped;
	}

	public void setCheckboxCoverageUnmapped(Boolean checkboxCoverageUnmapped) {
		this.checkboxCoverageUnmapped = checkboxCoverageUnmapped;
	}

	public String getHeadQuarterCode() {
		return headQuarterCode;
	}

	public void setHeadQuarterCode(String headQuarterCode) {
		this.headQuarterCode = headQuarterCode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	
	public Integer getLbCoveredRegionCode() {
		return lbCoveredRegionCode;
	}

	public void setLbCoveredRegionCode(Integer lbCoveredRegionCode) {
		this.lbCoveredRegionCode = lbCoveredRegionCode;
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

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	public List<CommonsMultipartFile> getGazettePublicationUpload() {
		return gazettePublicationUpload;
	}

	public void setGazettePublicationUpload(List<CommonsMultipartFile> gazettePublicationUpload) {
		this.gazettePublicationUpload = gazettePublicationUpload;
	}
	
	public List<CommonsMultipartFile> getMapUpload() {
		return mapUpload;
	}

	public void setMapUpload(List<CommonsMultipartFile> mapUpload) {
		this.mapUpload = mapUpload;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getEditorTemplateDetails() {
		return editorTemplateDetails;
	}

	public void setEditorTemplateDetails(String editorTemplateDetails) {
		this.editorTemplateDetails = editorTemplateDetails;
	}

	public String getContributingLBCodes() {
		return contributingLBCodes;
	}

	public void setContributingLBCodes(String contributingLBCodes) {
		this.contributingLBCodes = contributingLBCodes;
	}

	public String getContributingLBDistrictCodes() {
		return contributingLBDistrictCodes;
	}

	public void setContributingLBDistrictCodes(
			String contributingLBDistrictCodes) {
		this.contributingLBDistrictCodes = contributingLBDistrictCodes;
	}

	public String getContributingLBSubDistrictCodes() {
		return contributingLBSubDistrictCodes;
	}

	public void setContributingLBSubDistrictCodes(
			String contributingLBSubDistrictCodes) {
		this.contributingLBSubDistrictCodes = contributingLBSubDistrictCodes;
	}

	public String getContributingLBVillageCodes() {
		return contributingLBVillageCodes;
	}

	public void setContributingLBVillageCodes(String contributingLBVillageCodes) {
		this.contributingLBVillageCodes = contributingLBVillageCodes;
	}

	public String getContributingUnmappedDistrictCodes() {
		return contributingUnmappedDistrictCodes;
	}

	public void setContributingUnmappedDistrictCodes(String contributingUnmappedDistrictCodes) {
		this.contributingUnmappedDistrictCodes = contributingUnmappedDistrictCodes;
	}

	public String getContributingUnmappedSubDistrictCodes() {
		return contributingUnmappedSubDistrictCodes;
	}

	public void setContributingUnmappedSubDistrictCodes(String contributingUnmappedSubDistrictCodes) {
		this.contributingUnmappedSubDistrictCodes = contributingUnmappedSubDistrictCodes;
	}

	public String getContributingUnmappedVillageCodes() {
		return contributingUnmappedVillageCodes;
	}

	public void setContributingUnmappedVillageCodes(String contributingUnmappedVillageCodes) {
		this.contributingUnmappedVillageCodes = contributingUnmappedVillageCodes;
	}

	public Integer getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}

	public Integer getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	
	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public String getOrderPath() {
		return orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}
	
	public Long getOrderFileSize() {
		return orderFileSize;
	}

	public void setOrderFileSize(Long orderFileSize) {
		this.orderFileSize = orderFileSize;
	}

	public String getOrderFileContentType() {
		return orderFileContentType;
	}

	public void setOrderFileContentType(String orderFileContentType) {
		this.orderFileContentType = orderFileContentType;
	}

	public String getMapUploadPath() {
		return mapUploadPath;
	}

	public void setMapUploadPath(String mapUploadPath) {
		this.mapUploadPath = mapUploadPath;
	}
	
	public Long getMapFileSize() {
		return mapFileSize;
	}

	public void setMapFileSize(Long mapFileSize) {
		this.mapFileSize = mapFileSize;
	}

	public String getMapFileContentType() {
		return mapFileContentType;
	}

	public void setMapFileContentType(String mapFileContentType) {
		this.mapFileContentType = mapFileContentType;
	}

	public String getContributingLandRegionCodes() {
		return contributingLandRegionCodes;
	}

	public void setContributingLandRegionCodes(
			String contributingLandRegionCodes) {
		this.contributingLandRegionCodes = contributingLandRegionCodes;
	}

	public Integer getParentLocalBodyCode() {
		return parentLocalBodyCode;
	}

	public void setParentLocalBodyCode(Integer parentLocalBodyCode) {
		this.parentLocalBodyCode = parentLocalBodyCode;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProcessAction() {
		return processAction;
	}

	public void setProcessAction(String processAction) {
		this.processAction = processAction;
	}

	public Boolean getIsResetedCoverage() {
		return isResetedCoverage;
	}

	public void setIsResetedCoverage(Boolean isResetedCoverage) {
		this.isResetedCoverage = isResetedCoverage;
	}

	public String getHidContributingLBCodes() {
		return hidContributingLBCodes;
	}

	public void setHidContributingLBCodes(String hidContributingLBCodes) {
		this.hidContributingLBCodes = hidContributingLBCodes;
	}

	public String getHidContributingLandRegionCodes() {
		return hidContributingLandRegionCodes;
	}

	public void setHidContributingLandRegionCodes(String hidContributingLandRegionCodes) {
		this.hidContributingLandRegionCodes = hidContributingLandRegionCodes;
	}
	
	public String getIsdisturbed() {
		return isdisturbed;
	}

	public void setIsdisturbed(String isdisturbed) {
		this.isdisturbed = isdisturbed;
	}

	public String getValidationAction() {
		return validationAction;
	}

	public void setValidationAction(String validationAction) {
		this.validationAction = validationAction;
	}

	public Integer getRenameId() {
		return renameId;
	}

	public void setRenameId(Integer renameId) {
		this.renameId = renameId;
	}

	public String getRemovedLangRegionCodes() {
		return removedLangRegionCodes;
	}

	public void setRemovedLangRegionCodes(String removedLangRegionCodes) {
		this.removedLangRegionCodes = removedLangRegionCodes;
	}

	public String getChangeCoverageTypeLRList() {
		return changeCoverageTypeLRList;
	}

	public void setChangeCoverageTypeLRList(String changeCoverageTypeLRList) {
		this.changeCoverageTypeLRList = changeCoverageTypeLRList;
	}

	public Date getiParamEffectiveDate() {
		return iParamEffectiveDate;
	}

	public void setiParamEffectiveDate(Date iParamEffectiveDate) {
		this.iParamEffectiveDate = iParamEffectiveDate;
	}

	public String getIsGISCoverage() {
		return isGISCoverage;
	}

	public void setIsGISCoverage(String isGISCoverage) {
		this.isGISCoverage = isGISCoverage;
	}

	public String getContributingHabiationCodes() {
		return contributingHabiationCodes;
	}

	public void setContributingHabiationCodes(String contributingHabiationCodes) {
		this.contributingHabiationCodes = contributingHabiationCodes;
	}

	public String getAvilableHabitation() {
		return avilableHabitation;
	}

	public void setAvilableHabitation(String avilableHabitation) {
		this.avilableHabitation = avilableHabitation;
	}

	public String getFileLocation()
	{
		return fileLocation;
	}

	public void setFileLocation(String fileLocation)
	{
		this.fileLocation = fileLocation;
	}

	public Integer getAttachmentId()
	{
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId)
	{
		this.attachmentId = attachmentId;
	}

	public String getNewFilename()
	{
		return newFilename;
	}

	public void setNewFilename(String newFilename)
	{
		this.newFilename = newFilename;
	}

	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}
	
	
}
