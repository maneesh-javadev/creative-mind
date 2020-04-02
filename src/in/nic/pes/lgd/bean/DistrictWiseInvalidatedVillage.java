package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query="SELECT row_number() OVER () as rid, *  from rpt_for_invalidated_village_by_district(:entity_code)",name="getDistrictWiseInvalidatedVillage",resultClass=DistrictWiseInvalidatedVillage.class)
public class DistrictWiseInvalidatedVillage {


	private String village_name_english;
	private Integer village_version;
	private Integer subdistrict_code;
	private String subdistrict_name_english;
	private Integer ulb_code;
	private String ulb_name;
	
	
	@Id
	@Column(name = "rid", nullable = false)
	private Integer rid;
	
	private Integer village_code;
	
	@Column(name = "getVillage_code", nullable = false)
	public Integer getVillage_code() {
		return village_code;
	}
	public void setVillage_code(Integer village_code) {
		this.village_code = village_code;
	}
	//testing
	@Column(name = "getVillage_name_english")
	public String getVillage_name_english() {
		return village_name_english;
	}
	public void setVillage_name_english(String village_name_english) {
		this.village_name_english = village_name_english;
	}
	
	@Column(name = "getVillage_version")
	public Integer getVillage_version() {
		return village_version;
	}
	public void setVillage_version(Integer village_version) {
		this.village_version = village_version;
	}
	
	@Column(name = "getSubdistrict_code")
	public Integer getSubdistrict_code() {
		return subdistrict_code;
	}
	public void setSubdistrict_code(Integer subdistrict_code) {
		this.subdistrict_code = subdistrict_code;
	}
	
	@Column(name = "getSubdistrict_code")
	public String getSubdistrict_name_english() {
		return subdistrict_name_english;
	}
	public void setSubdistrict_name_english(String subdistrict_name_english) {
		this.subdistrict_name_english = subdistrict_name_english;
	}
	
	@Column(name = "getUlb_code")
	public Integer getUlb_code() {
		return ulb_code;
	}
	public void setUlb_code(Integer ulb_code) {
		this.ulb_code = ulb_code;
	}
	
	@Column(name = "getUlb_name")
	public String getUlb_name() {
		return ulb_name;
	}
	public void setUlb_name(String ulb_name) {
		this.ulb_name = ulb_name;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
}
