package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query = "select * from shift_village_fn(:source_subdistrict_code,:destination_subdistrict_code,:village_list,:userid,:effective_date,:order_no,:order_date,:gaz_pub_date,:order_path,:description,:entity_type)", name ="VillageSubDistrictQuery",resultClass=ShiftVillageSD.class)
public class ShiftVillageSD {
	@Id
	private String shift_village_fn;

	@Column(name = "shift_village_fn", nullable = false)
	public String getShift_village_fn() {
		return shift_village_fn;
	}

	public void setShift_village_fn(String shift_village_fn) {
		this.shift_village_fn = shift_village_fn;
	}
	
	
}
