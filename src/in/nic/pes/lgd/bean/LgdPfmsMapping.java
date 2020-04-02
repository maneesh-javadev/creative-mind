package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@NamedNativeQuery(name="FETCH_LDG_PFMS_MAPPING",query="select * from lgd_pfms_mapping where lgd_district_code=:districtCode order by pfms_id",resultClass=LgdPfmsMapping.class)
@Table(name="lgd_pfms_mapping",schema="public")
public class LgdPfmsMapping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pfms_id")
	private Integer lgdPfmsId; 
	
	@Column(name="pfms_state_name")
	private String pfmsStateName;
	
	@Column(name="lgd_state_name")
	private String lgdStateName;
	
	@Column(name="lgd_state_code")
	private Integer lgdStateCode;
	
	@Column(name="pfms_district_name")
	private String pfmsDistrictName;
	
	@Column(name="lgd_district_name")
	private String lgdDistrictName;
	
	@Column(name="lgd_district_code")
	private Integer lgdDistrictCode;
	
	@Column(name="pfms_block_name")
	private String pfmsBlockName;
	
	@Column(name="lgd_block_name")
	private String lgdBlockName;
	
	@Column(name="lgd_block_code")
	private Integer lgdBlockCode;
	
	@Column(name="pfms_panchayat_name")
	private String pfmsPanchayatName;
	
	@Column(name="lgd_panchayat_name")
	private String lgdPanchayatName;
	
	@Column(name="lgd_panchayat_code")
	private Integer lgdPanchayatCode;
	
	@Column(name="panchayat_version")
	private Integer lgdPanchayatVersion;
	
	@Column(name="verified_on")
	private Date verifiedOn;
	
	@Column(name="verified_by")
	private Integer verifiedBy;
	
	@Column(name="verified_status")
	private boolean verifiedStatus;
	
	public String getPfmsStateName() {
		return pfmsStateName;
	}
	
	public void setPfmsStateName(String pfmsStateName) {
		this.pfmsStateName = pfmsStateName;
	}
	
	public String getLgdStateName() {
		return lgdStateName;
	}
	
	public void setLgdStateName(String lgdStateName) {
		this.lgdStateName = lgdStateName;
	}
	
	public Integer getLgdStateCode() {
		return lgdStateCode;
	}
	
	public void setLgdStateCode(Integer lgdStateCode) {
		this.lgdStateCode = lgdStateCode;
	}
	
	public String getPfmsDistrictName() {
		return pfmsDistrictName;
	}
	
	public void setPfmsDistrictName(String pfmsDistrictName) {
		this.pfmsDistrictName = pfmsDistrictName;
	}
	
	public String getLgdDistrictName() {
		return lgdDistrictName;
	}
	
	public void setLgdDistrictName(String lgdDistrictName) {
		this.lgdDistrictName = lgdDistrictName;
	}
	
	public Integer getLgdDistrictCode() {
		return lgdDistrictCode;
	}
	
	public void setLgdDistrictCode(Integer lgdDistrictCode) {
		this.lgdDistrictCode = lgdDistrictCode;
	}
	
	public String getPfmsBlockName() {
		return pfmsBlockName;
	}
	
	public void setPfmsBlockName(String pfmsBlockName) {
		this.pfmsBlockName = pfmsBlockName;
	}
	
	public String getLgdBlockName() {
		return lgdBlockName;
	}
	
	public void setLgdBlockName(String lgdBlockName) {
		this.lgdBlockName = lgdBlockName;
	}
	
	public Integer getLgdBlockCode() {
		return lgdBlockCode;
	}
	
	public void setLgdBlockCode(Integer lgdBlockCode) {
		this.lgdBlockCode = lgdBlockCode;
	}
	
	public String getPfmsPanchayatName() {
		return pfmsPanchayatName;
	}
	
	public void setPfmsPanchayatName(String pfmsPanchayatName) {
		this.pfmsPanchayatName = pfmsPanchayatName;
	}
	
	public String getLgdPanchayatName() {
		return lgdPanchayatName;
	}
	
	public void setLgdPanchayatName(String lgdPanchayatName) {
		this.lgdPanchayatName = lgdPanchayatName;
	}
	
	public Integer getLgdPanchayatCode() {
		return lgdPanchayatCode;
	}
	
	public void setLgdPanchayatCode(Integer lgdPanchayatCode) {
		this.lgdPanchayatCode = lgdPanchayatCode;
	}
	
	public Integer getLgdPanchayatVersion() {
		return lgdPanchayatVersion;
	}
	
	public void setLgdPanchayatVersion(Integer lgdPanchayatVersion) {
		this.lgdPanchayatVersion = lgdPanchayatVersion;
	}
	
	public Date getVerifiedOn() {
		return verifiedOn;
	}
	
	public void setVerifiedOn(Date verifiedOn) {
		this.verifiedOn = verifiedOn;
	}
	
	public Integer getVerifiedBy() {
		return verifiedBy;
	}
	
	public void setVerifiedBy(Integer verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Integer getLgdPfmsId() {
		return lgdPfmsId;
	}

	public void setLgdPfmsId(Integer lgdPfmsId) {
		this.lgdPfmsId = lgdPfmsId;
	}

	public boolean isVerifiedStatus() {
		return verifiedStatus;
	}

	public void setVerifiedStatus(boolean verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
	}
}
