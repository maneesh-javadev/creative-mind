package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "nodal_officer", schema = "public")
@FilterDef(name = "ACTIVE_VERSION_FILTER", parameters=@ParamDef( name="isactive", type="boolean" ) )
public class NodalOfficerState implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
	@Column(name="nodal_officer_id")
	@SequenceGenerator(name = "nodal_officer_id_seq_generator", initialValue=1, allocationSize=1,  sequenceName = "nodal_officer_nodal_officer_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nodal_officer_id_seq_generator")
	private Integer nodalOfficerId;
	
	
	
	@Column(name="nodal_officer_name")
	private String nodalOfficerName;
	
	
	@Column(name = "designation")
	private String designation;
	
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "state_code")
	private Integer stateCode;
	
	@Column(name = "isactive")
	private Boolean isactive;
    
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_on")
	private Timestamp createdOn;
	
	@Column(name = "modified_by")
	private Integer modifiedBy;
	
	@Column(name = "modified_on")
	private Timestamp modifiedOn;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="user_type")
	private Character userType;
	
	@Column(name="token")
	private String token;
	
	@Column(name="expire_duration")
	private Timestamp expireDuration;
	
	@Column(name="level")
	private Character level;
	
	@Column(name = "district_code")
	private Integer DistrictCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	
	@JoinColumns({  
		  @JoinColumn(name = "state_code", referencedColumnName = "slc", nullable =	  false,insertable=false,updatable=false),
		  @JoinColumn(name = "isactive", referencedColumnName = "isactive",	 nullable = false,insertable=false,updatable=false) })
	@Filter(name = "ACTIVE_VERSION_FILTER",condition="isactive = true")
	@Where(clause="state.isactive = true") 
	private State state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Filter(name = "ACTIVE_VERSION_FILTER",condition="isactive = true")
	@JoinColumns({  
		  @JoinColumn(name = "nodal_officer_id", referencedColumnName = "nodal_officer_id", nullable =	  false,insertable=false,updatable=false),
		  @JoinColumn(name = "isactive", referencedColumnName = "isactive",	 nullable = false,insertable=false,updatable=false) })
	@Where(clause="lgdDataConfirmationList.isactive = true") 
	  public LgdDataConfirmation lgdDataConfirmation;
	  
	
	 
	@Transient
	private List<CommonsMultipartFile> fileUpload;
	
	@Transient
	private String fileName; 

	public Integer getNodalOfficerId() {
		return nodalOfficerId;
	}

	public void setNodalOfficerId(Integer nodalOfficerId) {
		this.nodalOfficerId = nodalOfficerId;
	}

	public String getNodalOfficerName() {
		return nodalOfficerName;
	}

	public void setNodalOfficerName(String nodalOfficerName) {
		this.nodalOfficerName = nodalOfficerName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public List<CommonsMultipartFile> getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(List<CommonsMultipartFile> fileUpload) {
		this.fileUpload = fileUpload;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getExpireDuration() {
		return expireDuration;
	}

	public void setExpireDuration(Timestamp expireDuration) {
		this.expireDuration = expireDuration;
	}

	public Character getUserType() {
		return userType;
	}

	public void setUserType(Character userType) {
		this.userType = userType;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public LgdDataConfirmation getLgdDataConfirmation() {
		return lgdDataConfirmation;
	}

	public void setLgdDataConfirmation(LgdDataConfirmation lgdDataConfirmation) {
		this.lgdDataConfirmation = lgdDataConfirmation;
	}

	public Character getLevel() {
		return level;
	}

	public void setLevel(Character level) {
		this.level = level;
	}

	public Integer getDistrictCode() {
		return DistrictCode;
	}

	public void setDistrictCode(Integer districtCode) {
		DistrictCode = districtCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	 
	
	 

}
