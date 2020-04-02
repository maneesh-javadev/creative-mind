package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = " SELECT case when ul.land_region_code in (select local_body_code from get_draft_used_lb_lr_temp(ul.land_region_code,:localBodyType)) then cast('F' as character)  else cast('A' as character) end as operation_state,* FROM get_unmapped_land_region_list_localbody_levelwise_fn(:localBodyType, :stateCode, :level) ul", name = "getLocalGovtBodyforUnmappedLocalBodyListLevelWise", resultClass = LocalbodyUnMappedBodyLevelWise.class)
public class LocalbodyUnMappedBodyLevelWise {

	private String localBodyNameEnglish;
	private String localBodyNameLocal;
	private Integer landRegionVersion;
	private Integer landRegionCode;
	/**
	 * Added by ripunj 0n 21-11-2014 for Localbody Draft Mode
	 */
	private Character operation_state;

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

	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	
	

}
