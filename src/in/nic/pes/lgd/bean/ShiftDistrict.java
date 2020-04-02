package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query = "select * from shift_district_fn(:source_state_code,:destination_state_code,:district_list,:userid,:effective_date,:order_no,:order_date,:gaz_pub_date,:order_path,:description,:entity_type)", name ="DistrictQuery",resultClass=ShiftDistrict.class)
public class ShiftDistrict {
	@Id
	private String shift_district_fn;    //TO DO :to remove underscores

	@Column(name = "shift_district_fn", nullable = false)
	public String getShift_district_fn() {
		return shift_district_fn;
	}

	public void setShift_district_fn(String shift_district_fn) {
		this.shift_district_fn = shift_district_fn;
	}
}
