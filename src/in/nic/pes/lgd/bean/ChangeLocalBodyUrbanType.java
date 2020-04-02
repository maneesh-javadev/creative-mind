package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;



@Entity
@NamedNativeQuery(query=" SELECT * FROM change_type_for_local_body_fn(:local_body_code,:local_body_name_english,:description,:local_body_type_code,:local_body_subtype_code,:parent_local_body_code,:state_code,:effective_date,:createdby,:order_no,:order_date,:gaz_pub_date,:order_path)",name="getchangelocalbodyurbantype",resultClass=ChangeLocalBodyUrbanType.class)

public class ChangeLocalBodyUrbanType 
{
	@Id 
	private String change_type_for_local_body_fn;
	
	@Column(name = "change_type_for_local_body_fn", nullable = false)
	public String getChange_type_for_local_body_fn() {
		return change_type_for_local_body_fn;
	}

	public void setChange_type_for_local_body_fn(
			String change_type_for_local_body_fn) {
		this.change_type_for_local_body_fn = change_type_for_local_body_fn;
	}
	
}

