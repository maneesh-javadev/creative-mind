package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author nic
 *
 */
@Entity
@Table(name = "user_registration") 
public class UserRegistration implements Serializable {
	private static final long serialVersionUID = 1L;
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "user_registration_srno_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "srno", unique = true, nullable = false)
	private Integer userRegistrationId;

	@Column(name = "ur_name")
	private String name;

	@Column(name = "ur_designation")
	private String designation;

	@Column(name = "ur_email")
	private String email;

	@Column(name = "ur_mobile")
	private String mobile;
	
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
	
	@Column(name = "otp")
	private String generatedOTP;
	
	
	
	@Column(name = "uploaded_filename")
	private String uploadFileName;
	
	@Column(name = "mobile_web_token")
	private String mobileWebToken;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch=FetchType.EAGER,mappedBy="userRegistration")
	private List<WsRegisteredIpEntity> ipAddressList;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch=FetchType.EAGER,mappedBy="userRegistration")
	private List<WsSubscribedApplicationEntity> applicationName;

	
	@Transient
	private int stateOrgUnitCode;
	
	@Transient
	private String stateDepartment;
	
	
	@Transient
	private boolean mobileAppCheck;
	
	@Transient
	private int rowCount;
	
	
	@Transient
	private int pageStart=0;
	
	@Transient
	private String fileName;
	
	

	@Transient
	WsUserRegistrationForm registrationForm;
	
	@Transient
	private List<WsRegisteredIpEntity> wsRegisteredIpEntity;
	
	@Transient
	private List<WsSubscribedApplicationEntity> wsSubscribedApplicationEntity;
	
	
	
	public String getStateDepartment() {
		return stateDepartment;
	}

	public void setStateDepartment(String stateDepartment) {
		this.stateDepartment = stateDepartment;
	}

	public int getStateOrgUnitCode() {
		return stateOrgUnitCode;
	}

	public void setStateOrgUnitCode(int stateOrgUnitCode) {
		this.stateOrgUnitCode = stateOrgUnitCode;
	}

	public Integer getUserRegistrationId() {
		return userRegistrationId;
	}

	public void setUserRegistrationId(Integer userRegistrationId) {
		this.userRegistrationId = userRegistrationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getGeneratedOTP() {
		return generatedOTP;
	}

	public void setGeneratedOTP(String generatedOTP) {
		this.generatedOTP = generatedOTP;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getMobileWebToken() {
		return mobileWebToken;
	}

	public void setMobileWebToken(String mobileWebToken) {
		this.mobileWebToken = mobileWebToken;
	}

	public boolean isMobileAppCheck() {
		return mobileAppCheck;
	}

	public void setMobileAppCheck(boolean mobileAppCheck) {
		this.mobileAppCheck = mobileAppCheck;
	}

	public WsUserRegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(WsUserRegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
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

	public List<WsRegisteredIpEntity> getIpAddressList() {
		return ipAddressList;
	}

	public void setIpAddressList(List<WsRegisteredIpEntity> ipAddressList) {
		this.ipAddressList = ipAddressList;
	}

	public List<WsSubscribedApplicationEntity> getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(List<WsSubscribedApplicationEntity> applicationName) {
		this.applicationName = applicationName;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
	
}
