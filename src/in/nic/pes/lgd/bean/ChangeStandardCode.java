package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query = "select * from set_land_region_other_codes_fn(:landregionType,:landregionCode,:cencouscode1,:cencouscode,:sscode)", name ="changeStandardCodesQuery",resultClass=ChangeStandardCode.class)
public class ChangeStandardCode {
	@Id
	
	private boolean set_land_region_other_codes_fn;
	@Column(name = "set_land_region_other_codes_fn", nullable = false)

	public boolean isSet_land_region_other_codes_fn() {
		return set_land_region_other_codes_fn;
	}

	public void setSet_land_region_other_codes_fn(
			boolean set_land_region_other_codes_fn) {
		this.set_land_region_other_codes_fn = set_land_region_other_codes_fn;
	}
	
	
	
	
	
	
	
	
}
