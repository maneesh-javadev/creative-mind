package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = " SELECT * FROM get_unmapped_land_region_list_fn(:localBodyType, :stateCode)", name = "getLocalGovtBodyforUnmappedLocalBodyList", resultClass = LocalbodyUnMappedBody.class)
public class LocalbodyUnMappedBody {

	private String localBodyNameEnglish;
	private String localBodyNameLocal;
	private Integer landRegionVersion;
	private Integer landRegionCode;

	@Id
	@Column(name = "land_region_code")
	public Integer getLandRegionCode() {
		return landRegionCode;
	}

	public void setLandRegionCode(Integer landRegionCode) {
		this.landRegionCode = landRegionCode;
	}

	@Column(name = "land_region_version")
	public Integer getLandRegionVersion() {
		return landRegionVersion;
	}

	public void setLandRegionVersion(Integer landRegionVersion) {
		this.landRegionVersion = landRegionVersion;
	}

	@Column(name = "land_region_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	@Column(name = "land_region_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

}
