package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries(
		{
@NamedNativeQuery(query=" SELECT * FROM get_land_region_parent_list_by_search_text_fn(:entityName,:entityCode) ",name="getStateSearchDetails",resultClass=Search.class),
@NamedNativeQuery(query=" SELECT * FROM get_land_region_parent_list_by_code_fn(:entityCodeForSearch,:entityCode) ",name="getStateSearchDetailsByCode",resultClass=Search.class)
		}
)
public class Search {
	
	private Integer id;
	private Integer stateCode;
	private String stateNameEnglish;
	private Integer districtCode;
	private String districtNameEnglish;
	private Integer subdistrictCode;
	private String subdistrictNameEnglish;
	private Integer villageCode;
	private String villageNameEnglish;
	private char type;
	
	@Id
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		if (id!=null){
			this.id = id;
		}	
	}
	@Column(name="type")
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}	
	@Column(name="state_code")
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		if (stateCode!=null){
			this.stateCode = stateCode;
		}	
	}
	@Column(name="state_name_english")
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	@Column(name="district_code")
	public Integer getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Integer districtCode) {
		if (districtCode!=null){
			this.districtCode = districtCode;
		}	
	}
	@Column(name="district_name_english")
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	@Column(name="subdistrict_code")
	public Integer getSubdistrictCode() {
		return subdistrictCode;
	}
	public void setSubdistrictCode(Integer subdistrictCode) {
		if (subdistrictCode!=null){
			this.subdistrictCode = subdistrictCode;
		}	
	}
	@Column(name="subdistrict_name_english")
	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}
	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}
	@Column(name="village_code")
	public Integer getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(Integer villageCode) {
		if (villageCode!=null){
			this.villageCode = villageCode;
		}	
	}	
	@Column(name="village_name_english")
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}
	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}
	
}
