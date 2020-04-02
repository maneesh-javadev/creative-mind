package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from set_configuration_map_localbody_fn(:tierSetupCode,:mapURL,:userId)", name ="configMapLgdm",resultClass=ConfigMapLgdm.class)
public class ConfigMapLgdm {
	
	@Id
	private boolean set_configuration_map_localbody_fn;
     
	@Column(name = "set_configuration_map_localbody_fn", nullable = false)
	public boolean isSet_configuration_map_localbody_fn() {
		return set_configuration_map_localbody_fn;
	}

	public void setSet_configuration_map_localbody_fn(
			boolean set_configuration_map_localbody_fn) {
		this.set_configuration_map_localbody_fn = set_configuration_map_localbody_fn;
	}
	
	
	
	
	

}
