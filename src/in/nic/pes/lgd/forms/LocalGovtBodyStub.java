package in.nic.pes.lgd.forms;

import in.nic.pes.common.beans.Category;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.LocalBodyDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import pes.attachment.util.AttachedFilesForm;

public class LocalGovtBodyStub implements Serializable, Cloneable {
	private static final long serialVersionUID = -36643944205523952L;
	private LocalGovtBodyForm localGovtBodyForm;
	private List<LocalBodyDetails> localBodyDetails;
	private List<Attachment> listAttachments;
	private List<AttachedFilesForm> attachedFilesList;
	private String lgd_LBorderNo;
	private Date lgd_LBorderDate;
	private Date lgd_LBeffectiveDate;
	private Date lgd_LBgazPubDate;
	private String govtOrderConfig;
	private char category;
	private int operationCode;
	
	private Long userId;
	private String loginId;
	private Boolean isActive;
	private Integer stateId;
	private Integer assignUnit;
	private Category categoryCode;    
	
	
	public List<LocalBodyDetails> getLocalBodyDetails() {
		return localBodyDetails;
	}
	public void setLocalBodyDetails(List<LocalBodyDetails> localBodyDetails) {
		this.localBodyDetails = localBodyDetails;
	}
	
	public String getLgd_LBorderNo() {
		return lgd_LBorderNo;
	}
	public void setLgd_LBorderNo(String lgd_LBorderNo) {
		this.lgd_LBorderNo = lgd_LBorderNo;
	}
	public Date getLgd_LBorderDate() {
		return lgd_LBorderDate;
	}
	public void setLgd_LBorderDate(Date lgd_LBorderDate) {
		this.lgd_LBorderDate = lgd_LBorderDate;
	}
	public Date getLgd_LBeffectiveDate() {
		return lgd_LBeffectiveDate;
	}
	public void setLgd_LBeffectiveDate(Date lgd_LBeffectiveDate) {
		this.lgd_LBeffectiveDate = lgd_LBeffectiveDate;
	}
	public Date getLgd_LBgazPubDate() {
		return lgd_LBgazPubDate;
	}
	public void setLgd_LBgazPubDate(Date lgd_LBgazPubDate) {
		this.lgd_LBgazPubDate = lgd_LBgazPubDate;
	}
	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}
	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	public int getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}
	public LocalGovtBodyForm getLocalGovtBodyForm() {
		return localGovtBodyForm;
	}
	public void setLocalGovtBodyForm(LocalGovtBodyForm localGovtBodyForm) {
		this.localGovtBodyForm = localGovtBodyForm;
	}
	public final Long getUserId() {
		return userId;
	}
	public final void setUserId(Long userId) {
		this.userId = userId;
	}
	public final String getLoginId() {
		return loginId;
	}
	public final void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public final Boolean getIsActive() {
		return isActive;
	}
	public final void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public final Integer getStateId() {
		return stateId;
	}
	public final void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public final Integer getAssignUnit() {
		return assignUnit;
	}
	public final void setAssignUnit(Integer assignUnit) {
		this.assignUnit = assignUnit;
	}
	public final Category getCategoryCode() {
		return categoryCode;
	}
	public final void setCategoryCode(Category categoryCode) {
		this.categoryCode = categoryCode;
	}
	public final List<Attachment> getListAttachments() {
		return listAttachments;
	}
	public final void setListAttachments(List<Attachment> listAttachments) {
		this.listAttachments = listAttachments;
	}
	public final List<AttachedFilesForm> getAttachedFilesList() {
		return attachedFilesList;
	}
	public final void setAttachedFilesList(List<AttachedFilesForm> attachedFilesList) {
		this.attachedFilesList = attachedFilesList;
	}	
}