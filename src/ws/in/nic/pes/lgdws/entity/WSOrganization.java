package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries(
{
	@NamedNativeQuery(query="select * from get_organization_list_fn(:orgTypeCode,:isCenter,:stateCode)", name="getOrganizationList",resultClass=WSOrganization.class),
	
})

public class WSOrganization {
	
	@Id
	@Column(name="org_code")
	private Integer OrgCode;
	
	@Column(name="org_name")
	private String OrgName;

	public Integer getOrgCode() {
		return OrgCode;
	}

	public void setOrgCode(Integer orgCode) {
		OrgCode = orgCode;
	}

	public String getOrgName() {
		return OrgName;
	}

	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	
	

}
