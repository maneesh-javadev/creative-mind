package com.cmc.lgd.localbody.entities;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;

import java.util.ArrayList;
import java.util.List;

public class LBAttributes {

	private List<LBTypeHierarchy> lbTypeHierarchy = new ArrayList<LBTypeHierarchy>();
	
	private List<LBTypeDetails> localBodyTypes = new ArrayList<LBTypeDetails>();
	
	private Boolean isGovernmentOrderUpload;
	
	private List<GovernmentOrderTemplate> governmentOrderTemplates;
	
	private Boolean isMapUpload;
	
	private String systemConfigMessage;

	private List<CoverageDetailsLocalBody> landRegionCovergeDetails = new ArrayList<CoverageDetailsLocalBody>();
	
	private LocalBodyEntityDetails localBodyDetails;
	
	private GovernmentOrderTemplate templateSource;
	
	private AttachmentMaster attachmentMasterGO;
	
	private AttachmentMaster attachmentMasterMap;
	
	private List<DraftOperations> draftOperations = new ArrayList<DraftOperations>();
	
	private String localbodyTypeName;
	
	private String parentLocalBodyName;
	
	private List<LocalBodyEntityDetails> listLocalBodyEntities = new ArrayList<LocalBodyEntityDetails>();
			
		
	public List<LBTypeHierarchy> getLbTypeHierarchy() {
		return lbTypeHierarchy;
	}

	public void setLbTypeHierarchy(List<LBTypeHierarchy> lbTypeHierarchy) {
		this.lbTypeHierarchy = lbTypeHierarchy;
	}

	public List<LBTypeDetails> getLocalBodyTypes() {
		return localBodyTypes;
	}

	public void setLocalBodyTypes(List<LBTypeDetails> localBodyTypes) {
		this.localBodyTypes = localBodyTypes;
	}

	public Boolean getIsGovernmentOrderUpload() {
		return isGovernmentOrderUpload;
	}

	public void setIsGovernmentOrderUpload(Boolean isGovernmentOrderUpload) {
		this.isGovernmentOrderUpload = isGovernmentOrderUpload;
	}
	
	public List<GovernmentOrderTemplate> getGovernmentOrderTemplates() {
		return governmentOrderTemplates;
	}

	public void setGovernmentOrderTemplates(List<GovernmentOrderTemplate> governmentOrderTemplates) {
		this.governmentOrderTemplates = governmentOrderTemplates;
	}

	public Boolean getIsMapUpload() {
		return isMapUpload;
	}

	public void setIsMapUpload(Boolean isMapUpload) {
		this.isMapUpload = isMapUpload;
	}

	public String getSystemConfigMessage() {
		return systemConfigMessage;
	}

	public void setSystemConfigMessage(String systemConfigMessage) {
		this.systemConfigMessage = systemConfigMessage;
	}

	public List<CoverageDetailsLocalBody> getLandRegionCovergeDetails() {
		return landRegionCovergeDetails;
	}

	public void setLandRegionCovergeDetails(
			List<CoverageDetailsLocalBody> landRegionCovergeDetails) {
		this.landRegionCovergeDetails = landRegionCovergeDetails;
	}

	public LocalBodyEntityDetails getLocalBodyDetails() {
		return localBodyDetails;
	}

	public void setLocalBodyDetails(LocalBodyEntityDetails localBodyDetails) {
		this.localBodyDetails = localBodyDetails;
	}

	public GovernmentOrderTemplate getTemplateSource() {
		return templateSource;
	}

	public void setTemplateSource(GovernmentOrderTemplate templateSource) {
		this.templateSource = templateSource;
	}

	public AttachmentMaster getAttachmentMasterGO() {
		return attachmentMasterGO;
	}

	public void setAttachmentMasterGO(AttachmentMaster attachmentMasterGO) {
		this.attachmentMasterGO = attachmentMasterGO;
	}

	public AttachmentMaster getAttachmentMasterMap() {
		return attachmentMasterMap;
	}

	public void setAttachmentMasterMap(AttachmentMaster attachmentMasterMap) {
		this.attachmentMasterMap = attachmentMasterMap;
	}

	public List<DraftOperations> getDraftOperations() {
		return draftOperations;
	}

	public void setDraftOperations(List<DraftOperations> draftOperations) {
		this.draftOperations = draftOperations;
	}

	public String getLocalbodyTypeName() {
		return localbodyTypeName;
	}

	public void setLocalbodyTypeName(String localbodyTypeName) {
		this.localbodyTypeName = localbodyTypeName;
	}

	public String getParentLocalBodyName() {
		return parentLocalBodyName;
	}

	public void setParentLocalBodyName(String parentLocalBodyName) {
		this.parentLocalBodyName = parentLocalBodyName;
	}

	public List<LocalBodyEntityDetails> getListLocalBodyEntities() {
		return listLocalBodyEntities;
	}

	public void setListLocalBodyEntities(List<LocalBodyEntityDetails> listLocalBodyEntities) {
		this.listLocalBodyEntities = listLocalBodyEntities;
	}
}
