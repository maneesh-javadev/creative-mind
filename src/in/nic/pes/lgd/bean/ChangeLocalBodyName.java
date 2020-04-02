package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT * FROM change_local_body_name_fn(:local_body_code,:local_body_name_name_english,:user_id,:order_no,:order_date,:effective_date ,:govt_order ,:gaz_pub_date,:local_body_name_name_local,:alias_english,:alias_local)",name="getchangelocalbodyname",resultClass=ChangeLocalBodyName.class)
public class ChangeLocalBodyName {
	
	@Id 
	private String change_local_body_name_fn;
	
	@Column(name = "change_local_body_name_fn", nullable = false)
	public String getChange_local_body_name_fn() {
		return change_local_body_name_fn;
	}

	public void setChange_local_body_name_fn(String change_local_body_name_fn) {
		this.change_local_body_name_fn = change_local_body_name_fn;
	}
	
}