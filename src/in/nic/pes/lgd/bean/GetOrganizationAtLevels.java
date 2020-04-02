package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "Select * from get_organization_at_levels(:orgCode,:orgLevel)", name = "getOrgAtLevels", resultClass = GetOrganizationAtLevels.class)
public class GetOrganizationAtLevels {

	private Integer org_located_level_code;
	private String located_at_level;

    @Id
    @Column(name ="key")
	public Integer getOrg_located_level_code() {
		return org_located_level_code;
	}

	public void setOrg_located_level_code(Integer org_located_level_code) {
		this.org_located_level_code = org_located_level_code;
	}

	@Column(name ="value")
	public String getLocated_at_level() {
		return located_at_level;
	}

	public void setLocated_at_level(String located_at_level) {
		this.located_at_level = located_at_level;
	}

}
