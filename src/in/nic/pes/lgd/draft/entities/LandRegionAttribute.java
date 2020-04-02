package in.nic.pes.lgd.draft.entities;

import java.util.List;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;

public class LandRegionAttribute {
	
	private Boolean isGovernmentOrderUpload;
	
	private List<GovernmentOrderTemplate> governmentOrderTemplates;
	
	private Boolean isMapUpload;
	
	private String systemConfigMessage;
	
	private AttachmentMaster attachmentMasterGO;
	
	private AttachmentMaster attachmentMasterMap;

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
	
	

}
