package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT * FROM get_statewise_organization_list_by_local_body_type_fn(:stateCode, :lBTypeCode,:levelCode) where org_type_code=2",name="getLBTWiseDepartmentDetails",resultClass=LocalBodyTypeWiseDepartment.class),
@NamedNativeQuery(query=" SELECT * FROM get_statewise_organization_list_by_local_body_fn(:stateCode, :lBCode,:levelCode) where org_type_code=2",name="getLBWiseDepartmentDetails",resultClass=LocalBodyTypeWiseDepartment.class)
})
public class LocalBodyTypeWiseDepartment {

	private String orgName;
	private String shortOrgName;
	private int orgCode;
	
	@Column(name="org_level_specific_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name="org_level_specific_short_name")
	public String getShortOrgName() {
		return shortOrgName;
	}
	public void setShortOrgName(String shortOrgName) {
		this.shortOrgName = shortOrgName;
	}
	@Id
	@Column(name="org_code")
	public int getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}
	
	
}
