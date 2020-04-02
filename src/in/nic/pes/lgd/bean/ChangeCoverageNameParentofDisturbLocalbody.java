package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * FROM change_coverage_name_parent_of_disturb_localbody(:local_body_code,:land_region_list,:user_id,:order_no,:order_date,:effective_date,:gaz_pub_date,:order_path,:lb_landregionlist,:local_body_name_name_english,:local_body_name_name_local,:alias_english,:alias_local,:parent_code,:flag_code,:lb_list_Full)",name="changelocalbodycoveredAreaNameParentofDisturbLocalbody",resultClass=ChangeCoverageNameParentofDisturbLocalbody.class)

public class ChangeCoverageNameParentofDisturbLocalbody {
	
	@Id 
	@Column(name = "change_coverage_name_parent_of_disturb_localbody", nullable = false)
	private String change_coverage_name_parent_of_disturb_localbody;
	
	public String getChange_coverage_name_parent_of_disturb_localbody() {
		return change_coverage_name_parent_of_disturb_localbody;
	}

	public void setChange_coverage_name_parent_of_disturb_localbody(
			String change_coverage_name_parent_of_disturb_localbody) {
		this.change_coverage_name_parent_of_disturb_localbody = change_coverage_name_parent_of_disturb_localbody;
	}
	
	

}
