package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;



@Entity
@NamedNativeQuery(query=" SELECT * FROM check_authorization_fn(:entity_type,:entity_parent_type,:entity_parent_code,:entity_code)",name="getcheckAuthorization",resultClass=CheckAuthorization.class)

public class CheckAuthorization {
	@Id 
	private int key;
	private String Value;
	
	
	@Column(name = "key", nullable = false)
	public int getKey() {
		return key;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	@Column(name = "value", nullable = false)
	public String getValue() {
		return Value;
	}
	
	public void setValue(String value) {
		Value = value;
	}
	
	
	
	

	
	
	
	

	
}
