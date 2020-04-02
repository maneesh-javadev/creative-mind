/*package in.nic.pes.lgd.bean;

public class LandRegionDetail {

}
 */

package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from get_land_region_parent_list_fn(:entity_type,:entity_code)", name = "getLandRegionDetail", resultClass = LandRegionDetail.class)
public class LandRegionDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5182798364532975839L;

	@Id
	@Column(name = "state_code")
	private Integer stateCode;

	@Column(name = "state_name_english")
	private String stateNameEnglish;

	@Column(name = "state_name_local")
	private String stateNameLocal;

	@Column(name = "district_code")
	private Integer districtCode;

	@Column(name = "district_name_english")
	private String districtNameEnglish;

	@Column(name = "district_name_local")
	private String districtNameLocal;

	@Column(name = "subdistrict_code")
	private Integer subDistrictCode;

	@Column(name = "subdistrict_name_english")
	private String subdistrictNameEnglish;

	@Column(name = "subdistrict_name_local")
	private String subdistrictNameLocal;

	@Column(name = "village_code")
	private Integer villageCode;

	@Column(name = "village_name_english")
	private String villageNameEnglish;

	@Column(name = "village_name_local")
	private String villageNameLocal;

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

	public String getStateNameLocal() {
		return stateNameLocal;
	}

	public void setStateNameLocal(String stateNameLocal) {
		this.stateNameLocal = stateNameLocal;
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

	public String getDistrictNameLocal() {
		return districtNameLocal;
	}

	public void setDistrictNameLocal(String districtNameLocal) {
		this.districtNameLocal = districtNameLocal;
	}

	public Integer getSubDistrictCode() {
		return subDistrictCode;
	}

	public void setSubDistrictCode(Integer subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	public String getSubdistrictNameLocal() {
		return subdistrictNameLocal;
	}

	public void setSubdistrictNameLocal(String subdistrictNameLocal) {
		this.subdistrictNameLocal = subdistrictNameLocal;
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

	public String getVillageNameLocal() {
		return villageNameLocal;
	}

	public void setVillageNameLocal(String villageNameLocal) {
		this.villageNameLocal = villageNameLocal;
	}

}
