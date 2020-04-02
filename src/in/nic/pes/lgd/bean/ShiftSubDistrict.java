package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query = "select * from shift_subdistrict_fn(:source_district_code,:destination_district_code,:subdistrict_list,:userid,:effective_date,:order_no,:order_date,:gaz_pub_date,:order_path,:description,:entity_type)", name ="ShiftSubDistrictQuery",resultClass=ShiftSubDistrict.class)
public class ShiftSubDistrict {
	
	@Id
	private String shift_subdistrict_fn;

	@Column(name = "shift_subdistrict_fn", nullable = false)
	public String getShift_subdistrict_fn() {
		return shift_subdistrict_fn;
	}

	public void setShift_subdistrict_fn(String shift_subdistrict_fn) {
		this.shift_subdistrict_fn = shift_subdistrict_fn;
	}
}
