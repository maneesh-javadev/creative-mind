package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "SELECT  case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state," +
		"* FROM get_covered_area_of_lb_fn(:localBodyCodes) lb", name = "getCoveredAreaListOfLB", resultClass = LocalGovtBodyNameList.class)
public class LocalGovtBodyNameList {

	private String landRegionNameEnglish;
	private String landRegionNameLocal;
	private String landRegionType;
	private String landRegionCode;
	private Integer localBodyCode;
	private String localBodyNameEnglish;
	private Character operation_state;

	@Id
	@Column(name = "land_region_code")
	public String getLandRegionCode() {
		return landRegionCode;
	}

	public void setLandRegionCode(String landRegionCode) {
		this.landRegionCode = landRegionCode;
	}

	@Column(name = "land_region_name_local")
	public String getLandRegionNameLocal() {
		return landRegionNameLocal;
	}

	public void setLandRegionNameLocal(String landRegionNameLocal) {
		this.landRegionNameLocal = landRegionNameLocal;
	}

	@Column(name = "land_region_name_english")
	public String getLandRegionNameEnglish() {
		return landRegionNameEnglish;
	}

	public void setLandRegionNameEnglish(String landRegionNameEnglish) {
		this.landRegionNameEnglish = landRegionNameEnglish;
	}

	@Column(name = "land_region_type")
	public String getLandRegionType() {
		return landRegionType;
	}

	public void setLandRegionType(String landRegionType) {
		this.landRegionType = landRegionType;
	}

	@Column(name = "local_body_code")
	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	@Column(name = "local_body_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	@Column(name = "operation_state")
	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	

}
