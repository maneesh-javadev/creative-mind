package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "SELECT  case when lb.localbody_code  in (select * from get_draft_used_lb_lr_temp(lb.localbody_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state, " +
		"* from get_lb_details_by_subdistrict_fn(:subDistricts) lb", name = "getLbDetailsBySubDistricts", resultClass = GetLBDetailsBySubDistricts.class)
public class GetLBDetailsBySubDistricts {

	@Id
	@Column(name = "localbody_code")
	private Integer localBodyCode;
	@Column(name = "localbody_name")
	private String localBodyName;
	@Column(name = "subdistrict_code")
	private String subDistrictCode;
	@Column(name = "subdistrictname")
	private String subDistrictName;
	@Column(name = "operation_state")
	private Character operation_state;

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public String getLocalBodyName() {
		return localBodyName;
	}

	public void setLocalBodyName(String localBodyName) {
		this.localBodyName = localBodyName;
	}

	public String getSubDistrictName() {
		return subDistrictName;
	}

	public void setSubDistrictName(String subDistrictName) {
		this.subDistrictName = subDistrictName;
	}

	public String getSubDistrictCode() {
		return subDistrictCode;
	}

	public void setSubDistrictCode(String subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}

	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}

	
}
