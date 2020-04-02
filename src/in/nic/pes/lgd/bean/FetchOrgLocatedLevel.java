package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="select org_located_level_code,org_level_specific_name  from  org_located_at_levels where olc=:olc and isactive=:isactive  and  parent_org_located_level_code=:parentOrgLocatedLevelCode  and located_at_level =:locatedAtLevel ",
name="FETCH_ORG_LOCATED_LEVEL",resultClass=FetchOrgLocatedLevel.class)
public class FetchOrgLocatedLevel {
	
	@Id
	@Column(name="org_located_level_code")
	private int orgLocatedLevelCode;
	
	@Column(name="org_level_specific_name")
	private String orgLevelSpecificName;

	public int getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}

	public void setOrgLocatedLevelCode(int orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}

	public String getOrgLevelSpecificName() {
		return orgLevelSpecificName;
	}

	public void setOrgLevelSpecificName(String orgLevelSpecificName) {
		this.orgLevelSpecificName = orgLevelSpecificName;
	}
	
	

}
