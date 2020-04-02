package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@NamedNativeQueries({
	@NamedNativeQuery(query = " Select * from parentwise_count_of_BPandGP(:districtCode,:parentType,:userType,:userId) ", name = "freezeunfreezeulbentity", resultClass = FreezeLocalBodyEntity.class)})






@Entity
public class FreezeLocalBodyEntity {
	
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "district_code")
	private Integer districtCode;


	@Column(name = "district_name")
	private String districtNameEnglish;	
	
	@Column(name = "no_of_ulb")
	private Integer noOfUlb;
	
	@Column(name = "no_of_ward")
	private Integer noOfWards;
	
	@Column(name = "district_status")
	private String districtStatus;	
	
	@Column(name = "state_status")
	private String stateStatus;	
	
	@Column(name = "remark")
	private String remarks;	
	
	@Column(name = "entity_name")
	private String entityName;	
	
	@Column(name = "entity_code")
	private Integer entityCode;
	
	@Column(name = "count_of_bp")
	private Integer noOfBps;
	
	@Column(name = "count_of_gp")
	private Integer noOfGps;
	
	@Column(name = "entity_type_code")
	private Integer ulbTypeCode;
	
	@Column(name = "entity_type_name")
	private String ulbTypeName;	
	
	@Column(name = "census_2011_code")
	private String census2011Code;
	
	@Column(name = "userid")
	private Integer userId;
	
	
	@Transient
	private Character unfreezeStatus;
    
	@Column(name="count_of_DP")
	private Integer noofDps;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public Integer getNoOfUlb() {
		return noOfUlb;
	}

	public void setNoOfUlb(Integer noOfUlb) {
		this.noOfUlb = noOfUlb;
	}

	public Integer getNoOfWards() {
		return noOfWards;
	}

	public void setNoOfWards(Integer noOfWards) {
		this.noOfWards = noOfWards;
	}

	public String getDistrictStatus() {
		return districtStatus;
	}

	public void setDistrictStatus(String districtStatus) {
		this.districtStatus = districtStatus;
	}

	public String getStateStatus() {
		return stateStatus;
	}

	public void setStateStatus(String stateStatus) {
		this.stateStatus = stateStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public Integer getNoOfBps() {
		return noOfBps;
	}

	public void setNoOfBps(Integer noOfBps) {
		this.noOfBps = noOfBps;
	}

	public Integer getNoOfGps() {
		return noOfGps;
	}

	public void setNoOfGps(Integer noOfGps) {
		this.noOfGps = noOfGps;
	}

	public Integer getUlbTypeCode() {
		return ulbTypeCode;
	}

	public void setUlbTypeCode(Integer ulbTypeCode) {
		this.ulbTypeCode = ulbTypeCode;
	}

	public String getUlbTypeName() {
		return ulbTypeName;
	}

	public void setUlbTypeName(String ulbTypeName) {
		this.ulbTypeName = ulbTypeName;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	public Character getUnfreezeStatus() {
		return unfreezeStatus;
	}

	public void setUnfreezeStatus(Character unfreezeStatus) {
		this.unfreezeStatus = unfreezeStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNoofDps() {
		return noofDps;
	}

	public void setNoofDps(Integer noofDps) {
		this.noofDps = noofDps;
	}

	
	
	
	
	
	
	
	
	
}
