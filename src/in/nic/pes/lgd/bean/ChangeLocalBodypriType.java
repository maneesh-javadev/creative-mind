package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;



@Entity
@NamedNativeQuery(query=" SELECT * FROM change_local_body_parent_fn(:local_body_code,:parent_lblc,:user_id,:local_body_version,:order_no,:order_date,:effective_date,:gaz_pub_date,:order_path)",name="getchangelocalbodypritype",resultClass=ChangeLocalBodypriType.class)

public class ChangeLocalBodypriType {
	
	@Id 
	private String change_local_body_parent_fn;
	@Column(name = "change_local_body_parent_fn", nullable = false)
	public String getChange_local_body_parent_fn() {
		return change_local_body_parent_fn;
	}

	public void setChange_local_body_parent_fn(
			String change_local_body_parent_fn) {
		this.change_local_body_parent_fn = change_local_body_parent_fn;
	}

	
	
	
	
	
}

