package in.nic.pes.lgd.draft.form;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class DraftManageSubdistrictForm {
	
	private Integer draftCode;
	
	private Integer stateCode;
	
	private Integer districtCode;
	
	private Integer subdistrictCode;
	
	private Integer subdistrictVersion;
	
	private String subdistrictNameEnglish;
	
	private String changeSubdistrictNameEnglish;
	
	private String subdistrictNameLocal;
	
	private String aliasEnglish;
	
	private String aliasLocal;
	
	private Integer operationCode;
	
	private String entityType;
	
	private Integer entityCode;
	
	private boolean editMode;
	
	private Integer paramCode;
	
	private Integer userId;
	
	private String opeartionFlag;
	
	private Integer groupId;
	/**
	 * variable to store goverment details in draft 
	 * 
	 */
	
	private List<CommonsMultipartFile> gazettePublicationUpload;
	
	private String orderNo;
	
	private Date orderDate;
	
	private Date effectiveDate;
	
	private Date gazPubDate;
	
	private Integer orderCode;
	
	private String fileName;
	
	private Long fileSize;
	
	private String fileContentType;
	
	private String fileLocation;
	
	private String fileTimestamp;
	
	private Date iParamEffectiveDate;
	
	private String operationType;

	private Integer templateId;
	
	private String editorTemplateDetails;
	
	private String formAction;
	
	private boolean directMode;

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

	public Integer getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(Integer subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	public String getChangeSubdistrictNameEnglish() {
		return changeSubdistrictNameEnglish;
	}

	public void setChangeSubdistrictNameEnglish(String changeSubdistrictNameEnglish) {
		this.changeSubdistrictNameEnglish = changeSubdistrictNameEnglish;
	}

	public String getSubdistrictNameLocal() {
		return subdistrictNameLocal;
	}

	public void setSubdistrictNameLocal(String subdistrictNameLocal) {
		this.subdistrictNameLocal = subdistrictNameLocal;
	}

	public String getAliasEnglish() {
		return aliasEnglish;
	}

	public void setAliasEnglish(String aliasEnglish) {
		this.aliasEnglish = aliasEnglish;
	}

	public String getAliasLocal() {
		return aliasLocal;
	}

	public void setAliasLocal(String aliasLocal) {
		this.aliasLocal = aliasLocal;
	}

	public Integer getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public Integer getParamCode() {
		return paramCode;
	}

	public void setParamCode(Integer paramCode) {
		this.paramCode = paramCode;
	}

	

	public String getOpeartionFlag() {
		return opeartionFlag;
	}

	public void setOpeartionFlag(String opeartionFlag) {
		this.opeartionFlag = opeartionFlag;
	}

	public List<CommonsMultipartFile> getGazettePublicationUpload() {
		return gazettePublicationUpload;
	}

	public void setGazettePublicationUpload(List<CommonsMultipartFile> gazettePublicationUpload) {
		this.gazettePublicationUpload = gazettePublicationUpload;
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

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileTimestamp() {
		return fileTimestamp;
	}

	public void setFileTimestamp(String fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}

	public Date getiParamEffectiveDate() {
		return iParamEffectiveDate;
	}

	public void setiParamEffectiveDate(Date iParamEffectiveDate) {
		this.iParamEffectiveDate = iParamEffectiveDate;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
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

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public Integer getSubdistrictVersion() {
		return subdistrictVersion;
	}

	public void setSubdistrictVersion(Integer subdistrictVersion) {
		this.subdistrictVersion = subdistrictVersion;
	}

	public boolean isDirectMode() {
		return directMode;
	}

	public void setDirectMode(boolean directMode) {
		this.directMode = directMode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDraftCode() {
		return draftCode;
	}

	public void setDraftCode(Integer draftCode) {
		this.draftCode = draftCode;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	
	
	

}
