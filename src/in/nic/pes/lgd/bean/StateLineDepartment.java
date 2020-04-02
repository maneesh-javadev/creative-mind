package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT * FROM get_line_organizations_list_fn(:stateCode, :deptCode,:entityType)",name="getDepartmentDetails",resultClass=StateLineDepartment.class)

public class StateLineDepartment {
	/*private Integer stateCode;*/
	private String orgName;
	private String shortDeptName;
	private int orgCode;
	private String specificLevel;
	private int orgLocatedLevelCode;
	
	@Column(name="org_located_level_code")
	public int getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}
	public void setOrgLocatedLevelCode(int orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}
	@Column(name="org_name")
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
	@Column(name="org_code")
	public int getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name="located_at_level")
	public String getSpecificLevel() {
		return specificLevel;
	}
	public void setSpecificLevel(String specificLevel) {
		this.specificLevel = specificLevel;
	}
	
	
	
}
