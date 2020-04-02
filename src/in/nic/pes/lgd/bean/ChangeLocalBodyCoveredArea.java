package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * FROM change_coverage_of_local_body_fn(:local_body_code,:land_region_list,:user_id,:order_no,:order_date,:effective_date,:gaz_pub_date,:order_path,:lb_landregionlist,:covered_lbcode_full)",name="changelocalbodycoveredArea",resultClass=ChangeLocalBodyCoveredArea.class)

public class ChangeLocalBodyCoveredArea {
	
	@Id 
	private String change_coverage_of_local_body_fn;
	@Column(name = "change_coverage_of_local_body_fn", nullable = false)

	public String getChange_coverage_of_local_body_fn() {
		return change_coverage_of_local_body_fn;
	}

	public void setChange_coverage_of_local_body_fn(
			String change_coverage_of_local_body_fn) {
		this.change_coverage_of_local_body_fn = change_coverage_of_local_body_fn;
	}
	
}
