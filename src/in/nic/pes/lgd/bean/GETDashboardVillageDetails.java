package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select row_number() OVER () as id,  * from get_dashboard_invalidate_village_details(:stateCode,:flag)", name = "DashboardVillageDetails", resultClass = GETDashboardVillageDetails.class)
public class GETDashboardVillageDetails {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="state_name_english")
	private String stateNameEnglish;
	
	@Column(name="district_code")
	private Integer districtCode;
	
	@Column(name="district_name_english")
	private String districtNameEnglish;
	
	@Column(name="subdistrict_code")
	private Integer subdistrictCode;
	
	@Column(name="subdistrict_name_english")
	private String subdistrictNameEnglish;
	
	
	@Column(name="village_code")
	private Integer villagCode;
	
	@Column(name="village_name_english")
	private String villagNameEnglish;

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
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

	public Integer getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(Integer subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	public Integer getVillagCode() {
		return villagCode;
	}

	public void setVillagCode(Integer villagCode) {
		this.villagCode = villagCode;
	}

	public String getVillagNameEnglish() {
		return villagNameEnglish;
	}

	public void setVillagNameEnglish(String villagNameEnglish) {
		this.villagNameEnglish = villagNameEnglish;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
