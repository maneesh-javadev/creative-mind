package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "ws_user_registration")
public class WsUserRegistrationForm implements Serializable {
	private static final long serialVersionUID = 1L;
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "ws_user_registration_srno_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "srno", unique = true, nullable = false)
	private Integer wsUserId;

	@Column(name = "dept_official_name")
	private String deptOfficialName;

	@Column(name = "dept_official_designation")
	private String deptOfficialDesignation;

	@Column(name = "dept_official_email")
	private String deptOfficialEmail;

	@Column(name = "dept_official_mobile")
	private String deptOfficialMobile;

	@Column(name = "nic_official_name")
	private String nicOfficialName;

	@Column(name = "nic_official_designation")
	private String nicOfficialDesignation;

	@Column(name = "nic_official_email")
	private String nicOfficialEmail;

	@Column(name = "nic_official_mobile")
	private String nicOfficailMobile;
	
	@Column(name = "org_unit_code")
	private Integer orgUnitCode;
	
	@Column(name = "state_code")
	private Integer stateCode;
	
	@Column(name = "dept_name")
	private String deptName;
	
	@Column(name = "registered_on")
	private Date registeredOn;
	
	@Column(name = "registration_status")
	private Character registrationStatus;
	
	@Column(name = "uploaded_filename")
	private String uploadFileName;
	
	@Column(name = "mobile_web_token")
	private String mobileWebToken;
	
	@OneToMany(mappedBy="wsUserRegistrationForm")
	private Set<WsAllowedIpEntity> wsAllowedIpEntity;
	
	@OneToMany(mappedBy="wsUserRegistrationForm",fetch=FetchType.EAGER)
	private List<WsRegisteredIpEntity> wsRegisteredIpEntity;
	
	@OneToMany(mappedBy="wsUserRegistrationForm")
	private List<WsSubscribedApplicationEntity> wsSubscribedApplicationEntity;
	
	@Transient
	private String captchaAnswer;
	
	@Transient
	private List<CommonsMultipartFile> fileUpload;
	
	@Transient
	private String fileName;
	
	@Transient
	private Long fileSize;
	
	@Transient
	private String fileContentType;
	
	@Transient
	private String fileLocation;
	
	@Transient
	private boolean ipCheck;
	
	@Transient
	private String fileTimestamp;
	
	@Transient
	private Integer templateId;
	
	@Transient
	private String editorTemplateDetails;
	@Transient
	private boolean centerRadio;
	@Transient
	private boolean stateRadio;
	@Transient
	private int rowCount;
	@Transient
	private Integer canterOrgUnitCode;
	@Transient
	private int pageStart=0;
	@Transient
	private Integer stateOrgUnitCode;
	@Transient
	private String attachFile1;
	
	

	public String getAttachFile1() {
		return attachFile1;
	}

	public void setAttachFile1(String attachFile1) {
		this.attachFile1 = attachFile1;
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	public boolean isIpCheck() {
		return ipCheck;
	}

	public void setIpCheck(boolean ipCheck) {
		this.ipCheck = ipCheck;
	}


	
	public String getMobileWebToken() {
		return mobileWebToken;
	}

	public void setMobileWebToken(String mobileWebToken) {
		this.mobileWebToken = mobileWebToken;
	}


	public Integer getCanterOrgUnitCode() {
		return canterOrgUnitCode;
	}

	public void setCanterOrgUnitCode(Integer canterOrgUnitCode) {
		this.canterOrgUnitCode = canterOrgUnitCode;
	}


	public Integer getStateOrgUnitCode() {
		return stateOrgUnitCode;
	}

	public void setStateOrgUnitCode(Integer stateOrgUnitCode) {
		this.stateOrgUnitCode = stateOrgUnitCode;
	}

	public boolean isCenterRadio() {
		return centerRadio;
	}

	public void setCenterRadio(boolean centerRadio) {
		this.centerRadio = centerRadio;
	}

	public boolean isStateRadio() {
		return stateRadio;
	}

	public void setStateRadio(boolean stateRadio) {
		this.stateRadio = stateRadio;
	}

	public String getEditorTemplateDetails() {
		return editorTemplateDetails;
	}

	public void setEditorTemplateDetails(String editorTemplateDetails) {
		this.editorTemplateDetails = editorTemplateDetails;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
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

	public List<CommonsMultipartFile> getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(List<CommonsMultipartFile> fileUpload) {
		this.fileUpload = fileUpload;
	}

	public List<WsRegisteredIpEntity> getWsRegisteredIpEntity() {
		return wsRegisteredIpEntity;
	}

	public void setWsRegisteredIpEntity(List<WsRegisteredIpEntity> wsRegisteredIpEntity) {
		this.wsRegisteredIpEntity = wsRegisteredIpEntity;
	}

	public List<WsSubscribedApplicationEntity> getWsSubscribedApplicationEntity() {
		return wsSubscribedApplicationEntity;
	}

	public void setWsSubscribedApplicationEntity(List<WsSubscribedApplicationEntity> wsSubscribedApplicationEntity) {
		this.wsSubscribedApplicationEntity = wsSubscribedApplicationEntity;
	}


	public Set<WsAllowedIpEntity> getWsAllowedIpEntity() {
		return wsAllowedIpEntity;
	}

	public void setWsAllowedIpEntity(Set<WsAllowedIpEntity> wsAllowedIpEntity) {
		this.wsAllowedIpEntity = wsAllowedIpEntity;
	}

	public Integer getWsUserId() {
		return wsUserId;
	}

	public void setWsUserId(Integer wsUserId) {
		this.wsUserId = wsUserId;
	}

	public String getDeptOfficialName() {
		return deptOfficialName;
	}

	public void setDeptOfficialName(String deptOfficialName) {
		this.deptOfficialName = deptOfficialName;
	}

	

	public String getDeptOfficialDesignation() {
		return deptOfficialDesignation;
	}

	public void setDeptOfficialDesignation(String deptOfficialDesignation) {
		this.deptOfficialDesignation = deptOfficialDesignation;
	}

	public String getDeptOfficialEmail() {
		return deptOfficialEmail;
	}

	public void setDeptOfficialEmail(String deptOfficialEmail) {
		this.deptOfficialEmail = deptOfficialEmail;
	}

	public String getDeptOfficialMobile() {
		return deptOfficialMobile;
	}

	public void setDeptOfficialMobile(String deptOfficialMobile) {
		this.deptOfficialMobile = deptOfficialMobile;
	}

	public String getNicOfficialName() {
		return nicOfficialName;
	}

	public void setNicOfficialName(String nicOfficialName) {
		this.nicOfficialName = nicOfficialName;
	}

	public String getNicOfficialDesignation() {
		return nicOfficialDesignation;
	}

	public void setNicOfficialDesignation(String nicOfficialDesignation) {
		this.nicOfficialDesignation = nicOfficialDesignation;
	}

	public String getNicOfficialEmail() {
		return nicOfficialEmail;
	}

	public void setNicOfficialEmail(String nicOfficialEmail) {
		this.nicOfficialEmail = nicOfficialEmail;
	}

	public String getNicOfficailMobile() {
		return nicOfficailMobile;
	}

	public void setNicOfficailMobile(String nicOfficailMobile) {
		this.nicOfficailMobile = nicOfficailMobile;
	}

	public Integer getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(Integer orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	public Character getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(Character registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * @return the pageStart
	 */
	public int getPageStart() {
		return pageStart;
	}

	/**
	 * @param pageStart the pageStart to set
	 */
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	
	
	
	
	

}
