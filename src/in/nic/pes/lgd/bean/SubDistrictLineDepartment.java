package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT * FROM get_landregionwise_department_list_fn(:subDistrictCode,:level)",name="getLineDepartmentDetail",resultClass=SubDistrictLineDepartment.class)

public class SubDistrictLineDepartment { // NO_UCD (unused code)
	/*private Integer stateCode;*/
	private String orgName;
	private String orgNameInLocal;
	private String shortDeptName;
	private int orgCode;

	@Column(name="org_level_specific_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name="org_level_specific_short_name")
	public String getShortDeptName() {
		return shortDeptName;
	}
	public void setShortDeptName(String shortDeptName) {
		this.shortDeptName = shortDeptName;
	}
	@Id
	@Column(name="org_located_level_code")
	public int getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name="org_level_specific_name_local")
	public String getOrgNameInLocal() {
		return orgNameInLocal;
	}
	public void setOrgNameInLocal(String orgNameInLocal) {
		this.orgNameInLocal = orgNameInLocal;
	}	
}

