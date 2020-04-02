package in.nic.pes.lgd.section.rule;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "section")
public class Section {
	
	@Id
	private SectionPK sectionPK;
	
	@Column(name="Section_name_english")
	private String sectionNameEnglish;
	
	
	@Column(name="Section_name_local")
	private String sectionNameLocal;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="address3")
	private String address3;
	
	@Column(name="pin_code")
	private Integer pinCode;
	
	@Column(name="localaddress1")
	private String localaddress1;
	
	@Column(name="localaddress2")
	private String localaddress2;
	
	@Column(name="localaddress3")
	private String localaddress3;
	
	@Column(name="Parent_code")
	private Integer parentCode;
	
	@Column(name="Parent_type")
	private String parentType;
	
	@Column(name="effective_date")
	private Date effectiveDate;
	
	@Column(name="isactive")
	private boolean isactive;
	
	@Column(name="createdby")
	private Long createdby;
	
	@Column(name="createdon")
	private Date createdon;
	
	
	@Column(name="lastupdatedby")
	private Long lastupdatedby;
	
	@Column(name="lastupdated")
	private Date lastupdated;
	
	@Column(name="slc")
	private Integer slc;
	
	@Column(name="flag_code")
	private Integer flagCode;
	
	@Column(name="transaction_id")
	private Integer transactionId;
	
	@Transient
	private Integer sectionCode;
	
	@Transient
	private Integer sectionVersion;

	public SectionPK getSectionPK() {
		return sectionPK;
	}

	public void setSectionPK(SectionPK sectionPK) {
		this.sectionPK = sectionPK;
	}

	public String getSectionNameEnglish() {
		return sectionNameEnglish;
	}

	public void setSectionNameEnglish(String sectionNameEnglish) {
		this.sectionNameEnglish = sectionNameEnglish;
	}

	public String getSectionNameLocal() {
		return sectionNameLocal;
	}

	public void setSectionNameLocal(String sectionNameLocal) {
		this.sectionNameLocal = sectionNameLocal;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getLocaladdress1() {
		return localaddress1;
	}

	public void setLocaladdress1(String localaddress1) {
		this.localaddress1 = localaddress1;
	}

	public String getLocaladdress2() {
		return localaddress2;
	}

	public void setLocaladdress2(String localaddress2) {
		this.localaddress2 = localaddress2;
	}

	public String getLocaladdress3() {
		return localaddress3;
	}

	public void setLocaladdress3(String localaddress3) {
		this.localaddress3 = localaddress3;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Long getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(Long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	public Integer getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(Integer sectionCode) {
		this.sectionCode = sectionCode;
	}

	public Integer getSectionVersion() {
		return sectionVersion;
	}

	public void setSectionVersion(Integer sectionVersion) {
		this.sectionVersion = sectionVersion;
	}


	

	
	

}
