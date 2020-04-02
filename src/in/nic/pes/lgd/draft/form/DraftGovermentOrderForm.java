package in.nic.pes.lgd.draft.form;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class DraftGovermentOrderForm {
	
	private List<CommonsMultipartFile> gazettePublicationUpload;
	
	private String orderNo;
	
	private Date orderDate;
	
	private Date effectiveDate;
	
	private Date gazPubDate;
	
	private Integer orderCode;
	
	/**
	 * variable to store goverment details in draft 
	 * 
	 */
	
	private String fileName;
	
	private Long fileSize;
	
	private String fileContentType;
	
	private String fileLocation;
	
	private String fileTimestamp;
	
	private long userId;
	
	private Character entityType;
	
	
	
	private String operationType;
	
	private Integer paramCode;
	/*
	 * Variable to hold effective date value to display as label and 
	 * used for validation also.
	 */
	private Date iParamEffectiveDate;
	
	
	private String formAction;
	
	private Integer templateId;
	
	private String editorTemplateDetails;
	
	private boolean editMode;
	
	/**
	 * landregion details
	 * @return
	 */
      private String landRegionType;
      
      private Integer landRegionCode;
      
      private String invalidateSubdistrictcodes;

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


	public String getFormAction() {
		return formAction;
	}


	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public Character getEntityType() {
		return entityType;
	}


	public void setEntityType(Character entityType) {
		this.entityType = entityType;
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


	public Integer getParamCode() {
		return paramCode;
	}


	public void setParamCode(Integer paramCode) {
		this.paramCode = paramCode;
	}


	public boolean isEditMode() {
		return editMode;
	}


	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}


	public String getOperationType() {
		return operationType;
	}


	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}


	public String getLandRegionType() {
		return landRegionType;
	}


	public void setLandRegionType(String landRegionType) {
		this.landRegionType = landRegionType;
	}


	public Integer getLandRegionCode() {
		return landRegionCode;
	}


	public void setLandRegionCode(Integer landRegionCode) {
		this.landRegionCode = landRegionCode;
	}


	public String getInvalidateSubdistrictcodes() {
		return invalidateSubdistrictcodes;
	}


	public void setInvalidateSubdistrictcodes(String invalidateSubdistrictcodes) {
		this.invalidateSubdistrictcodes = invalidateSubdistrictcodes;
	}


	
	
	
	
	

}
