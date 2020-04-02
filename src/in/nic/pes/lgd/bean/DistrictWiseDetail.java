package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQuery(query="SELECT row_number() OVER() as row_id, * from districtwise_detail_report_fn(:district_code)",name="rptDistrictWiseDetailFn",resultClass=DistrictWiseDetail.class)
public class DistrictWiseDetail {

	@Id
	@Column(name="row_id")
	private Integer rid;
	@Column(name="district_code")
	private Integer districtCode;
	@Column(name="district_name_english")
	private String districtNameEnglish;
	@Column(name="subdistrict_code")
	private Integer subDistrictCode;
	@Column(name="subdistrict_name_english")
	private String subDistrictNameEnglish;
	@Column(name="village_code")
	private Integer villageCode;
	@Column(name="village_name_english")
	private String villageNameEnglish;
	@Column(name="bp_code")
	private Integer bpCode;
	@Column(name="bp_name_english")
	private String bpNameEnglish;
	@Column(name="gp_code")
	private Integer gpCode;
	@Column(name="gp_name_english")
	private String gpNameEnglish;
	@Column(name="block_code")
	private Integer blockCode;
	@Column(name="block_name_english")
	private String blockNameEnglish;
	@Column(name="district_code_of_block")
	private Integer districtCodeofBlock;
	@Column(name="district_name_of_block")
	private String districtNameofBlock;
	@Column(name="matched_district")
	private Boolean matchDistrict;

	@Transient
	private String captchaAnswer;
	@Transient
	private Integer selectDistrictCode;
	@Transient
	private Integer selectStateCode;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
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
	public Integer getSubDistrictCode() {
		return subDistrictCode;
	}
	public void setSubDistrictCode(Integer subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}
	public String getSubDistrictNameEnglish() {
		return subDistrictNameEnglish;
	}
	public void setSubDistrictNameEnglish(String subDistrictNameEnglish) {
		this.subDistrictNameEnglish = subDistrictNameEnglish;
	}
	public Integer getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}
	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}
	public Integer getBpCode() {
		return bpCode;
	}
	public void setBpCode(Integer bpCode) {
		this.bpCode = bpCode;
	}
	public String getBpNameEnglish() {
		return bpNameEnglish;
	}
	public void setBpNameEnglish(String bpNameEnglish) {
		this.bpNameEnglish = bpNameEnglish;
	}
	public Integer getGpCode() {
		return gpCode;
	}
	public void setGpCode(Integer gpCode) {
		this.gpCode = gpCode;
	}
	public String getGpNameEnglish() {
		return gpNameEnglish;
	}
	public void setGpNameEnglish(String gpNameEnglish) {
		this.gpNameEnglish = gpNameEnglish;
	}
	public Integer getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(Integer blockCode) {
		this.blockCode = blockCode;
	}
	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}
	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}
	public Integer getDistrictCodeofBlock() {
		return districtCodeofBlock;
	}
	public void setDistrictCodeofBlock(Integer districtCodeofBlock) {
		this.districtCodeofBlock = districtCodeofBlock;
	}
	
	public String getDistrictNameofBlock() {
		return districtNameofBlock;
	}
	public void setDistrictNameofBlock(String districtNameofBlock) {
		this.districtNameofBlock = districtNameofBlock;
	}
	public Boolean getMatchDistrict() {
		return matchDistrict;
	}
	public void setMatchDistrict(Boolean matchDistrict) {
		this.matchDistrict = matchDistrict;
	}
	public String getCaptchaAnswer() {
		return captchaAnswer;
	}
	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}
	public Integer getSelectDistrictCode() {
		return selectDistrictCode;
	}
	public void setSelectDistrictCode(Integer selectDistrictCode) {
		this.selectDistrictCode = selectDistrictCode;
	}
	public Integer getSelectStateCode() {
		return selectStateCode;
	}
	public void setSelectStateCode(Integer selectStateCode) {
		this.selectStateCode = selectStateCode;
	}
	
	
	
}
