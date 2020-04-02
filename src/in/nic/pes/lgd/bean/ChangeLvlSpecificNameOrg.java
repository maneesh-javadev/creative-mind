package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from change_level_specific_name_org_fn(:orgLocatedLevelCode,:userId,:orgLevelSpecificName,:effectiveTimeStampDate,:orderNo,:orderTimeStampDate,:orderCode,:gazPubTimeStampDate,:orderPath)", name = "ChangeOrgLvlSpecificNameQuery", resultClass = ChangeLvlSpecificNameOrg.class)
public class ChangeLvlSpecificNameOrg {
	@Id
	private String change_level_specific_name_org_fn;

	@Column(name = "change_level_specific_name_org_fn", nullable = false)
	public String getChange_level_specific_name_org_fn() {
		return change_level_specific_name_org_fn;
	}

	public void setChange_level_specific_name_org_fn(String change_level_specific_name_org_fn) {
		this.change_level_specific_name_org_fn = change_level_specific_name_org_fn;
	}

}
