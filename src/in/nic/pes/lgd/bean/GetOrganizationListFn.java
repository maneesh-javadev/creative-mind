package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "Select * from get_organization_list_fn(:orgTypeCode,:orgLevel)", name = "getOrgbyTypeCodelevelC", resultClass = GetOrganizationListFn.class),
@NamedNativeQuery(query="Select * from get_organization_list_fn(:orgTypeCode,:orgLevel,:stateCode) ",name="getOrgbyTypeCodelevelS",resultClass=GetOrganizationListFn.class)
})

public class GetOrganizationListFn {

	private Integer orgCode;
	private String orgName;
	
	@Id
	@Column(name ="org_code")
	public Integer getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}
	 
	@Column(name ="org_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
   

	 

	
	 

}
