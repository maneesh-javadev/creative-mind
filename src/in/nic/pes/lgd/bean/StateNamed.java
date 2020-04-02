package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = " SELECT * FROM create_state_fn(:userid,:state_name_english,:order_no,:order_date,:effective_date,:StateOrUt,:census_2011_code,:gaz_pub_date,:list_of_state_full,:list_of_state_part,:list_of_district_full,:short_name,:headquarter_name)", name = "createState", resultClass = StateNamed.class)
public class StateNamed {
	@Id
	@Column(name = "create_state_fn")
	private String create_state_fn = "";

	public String getCreate_state_fn() {
		return create_state_fn;
	}

	public void setCreate_state_fn(String create_state_fn) {
		this.create_state_fn = create_state_fn;
	}
}
