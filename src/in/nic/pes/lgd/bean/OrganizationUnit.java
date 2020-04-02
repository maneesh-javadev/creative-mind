/*
 * 
 * added by 10/1/2015
 * Anju Gupta*/

package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "org_units")
public class OrganizationUnit implements Serializable {

	@Id
	@Column(name = "org_unit_code ")
	private Integer orgUnitCode;

	@Column(name = "org_located_level_code ")
	private Integer orgLocatedLevelCode;

	@Column(name = "entity_lc", nullable = false)
	private Integer entityLc;

	@Column(name = "entity_type", nullable = false)
	private Integer entityType;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "address3")
	private String address3;

	@Column(name = "localaddress1")
	private String localAddress1;

	@Column(name = "localaddress2")
	private String localAddress2;

	@Column(name = "localaddress3")
	private String localAddress3;

	@Column(name = "phoneno")
	private String phoneNo;

	@Column(name = "email")
	private String email;

	@Column(name = "isactive")
	private boolean isActive;

	@Column(name = "org_unit_name")
	private String orgUnitName;

	@Column(name = "parent_org_unit_code")
	private Integer parentOrgUnitCode;

	@Column(name = "org_spec_code")
	private String orgSpecCode;

	@Column(name = "pin_code")
	private Integer pinCode;

	@Column(name = "level")
	private String level;

	@Column(name = "code")
	private Integer code;
	
	@Column(name = "headoffice")
	private Character headOffice;
	
	@Column(name="org_unit_name_local")
	private String orgUnitNameLocal;
	/*@Column(name = "org_unit_version")
	private Integer orgUnitVersion;
	
	@Column(name = "org_located_level_version")
	private Integer orgLocatedLevelVersion;
	
	@Column(name = "effective_date")
	private Date effectiveDate;
	
	@Column(name = "createdby")
	private Long createdBy;
	
	@Column(name = "lastupdatedby")
	private Long lastUpdateBy;
	
	@Column(name = "lastupdated")
	private Date lastUpdated;
	
	@Column(name = "transaction_id")
	private Integer transactionId;*/
	
	private transient String villageListRLB;
	private transient String ulbList;

	public String getVillageListRLB() {
		return villageListRLB;
	}

	public void setVillageListRLB(String villageListRLB) {
		this.villageListRLB = villageListRLB;
	}

	public String getUlbList() {
		return ulbList;
	}

	public void setUlbList(String ulbList) {
		this.ulbList = ulbList;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(Integer orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	public Integer getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}

	public void setOrgLocatedLevelCode(Integer orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}

	public Integer getEntityLc() {
		return entityLc;
	}

	public void setEntityLc(Integer entityLc) {
		this.entityLc = entityLc;
	}

	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
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

	public String getLocalAddress1() {
		return localAddress1;
	}

	public void setLocalAddress1(String localAddress1) {
		this.localAddress1 = localAddress1;
	}

	public String getLocalAddress2() {
		return localAddress2;
	}

	public void setLocalAddress2(String localAddress2) {
		this.localAddress2 = localAddress2;
	}

	public String getLocalAddress3() {
		return localAddress3;
	}

	public void setLocalAddress3(String localAddress3) {
		this.localAddress3 = localAddress3;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public Integer getParentOrgUnitCode() {
		return parentOrgUnitCode;
	}

	public void setParentOrgUnitCode(Integer parentOrgUnitCode) {
		this.parentOrgUnitCode = parentOrgUnitCode;
	}

	public String getOrgSpecCode() {
		return orgSpecCode;
	}

	public void setOrgSpecCode(String orgSpecCode) {
		this.orgSpecCode = orgSpecCode;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public Character getHeadOffice() {
		return headOffice;
	}

	public void setHeadOffice(Character headOffice) {
		this.headOffice = headOffice;
	}

	public String getOrgUnitNameLocal() {
		return orgUnitNameLocal;
	}

	public void setOrgUnitNameLocal(String orgUnitNameLocal) {
		this.orgUnitNameLocal = orgUnitNameLocal;
	}

	
	
	
	/*public Integer getOrgUnitVersion() {
		return orgUnitVersion;
	}

	public void setOrgUnitVersion(Integer orgUnitVersion) {
		this.orgUnitVersion = orgUnitVersion;
	}

	public Integer getOrgLocatedLevelVersion() {
		return orgLocatedLevelVersion;
	}

	public void setOrgLocatedLevelVersion(Integer orgLocatedLevelVersion) {
		this.orgLocatedLevelVersion = orgLocatedLevelVersion;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}*/
	

}
