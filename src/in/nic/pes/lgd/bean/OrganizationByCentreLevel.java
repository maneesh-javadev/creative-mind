package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT * FROM get_other_organization_list_fn(:entityCode)",name="getCentreLevelOrganizationDetails",resultClass=OrganizationByCentreLevel.class),
@NamedNativeQuery(query=" SELECT  org_code ,org_name from download_organisation_list(:stateCode)",name="getCentreStateOrganization",resultClass=OrganizationByCentreLevel.class)
})
public class OrganizationByCentreLevel {
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
