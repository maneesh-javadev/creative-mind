package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query=" SELECT * FROM get_organization_list_fn(:entityCode)",name="getCentreLevelDepartmentDetails",resultClass=DepartmentByCentreLevel.class)

public class DepartmentByCentreLevel {

	private int orgCode;
	private String orgName;
	
	@Id
	@Column(name="org_code")
	public int getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name="org_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
	
	
	
}
