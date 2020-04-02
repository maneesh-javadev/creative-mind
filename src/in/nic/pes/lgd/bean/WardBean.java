package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="Select * from create_localbody_ward_fn(:local_body_code,:userID,:ward_name_english,:ward_number,:ward_name_local)",name="InsertWardQuery",resultClass=WardBean.class)
/*query changed by kirandeep on 05/08/2014*/
public class WardBean
{
	@Id 
	private int create_localbody_ward_fn;

	@Column(name = "create_localbody_ward_fn", nullable = false)
	public int isCreate_localbody_ward_fn() {
		return create_localbody_ward_fn;
	}

	public void setCreate_localbody_ward_fn(int create_localbody_ward_fn) {
		this.create_localbody_ward_fn = create_localbody_ward_fn;
	}
}

