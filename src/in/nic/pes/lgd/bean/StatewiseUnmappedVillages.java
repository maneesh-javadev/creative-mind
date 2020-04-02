package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT  * from get_statewise_unmapped_villages(:stateCode,:limit,:offset)",name="getStatewiseUnmappedVillages",resultClass=StatewiseUnmappedVillages.class)

public class StatewiseUnmappedVillages  {
	 
	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;*/
	/**
	 * 
	 */
	
	
	
	@Column(name = "district_code", nullable = false)
	private Integer districtCode;
	@Column(name = "district_name_english", nullable = false)
	private String districtNameEnglish;
	@Column(name = "subdistrict_code", nullable = false)
	private Integer subdistrictCode;
	@Column(name = "subdistrict_name_english", nullable = false)
	private String subdistrictNameEnglish;
	@Id
	@Column(name = "village_code", nullable = false)
	private Integer villageCode;
	@Column(name = "village_name_english", nullable = false)
	private String villageNameEnglish;
	@Column(name = "village_status")
	private String villageStatus;
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
	public String getVillageStatus() {
		return villageStatus;
	}
	public void setVillageStatus(String villageStatus) {
		this.villageStatus = villageStatus;
	}
	

	
}

