
package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="Select * from create_district_fn(:p_state_code,:p_userid,:p_district_name_english,:p_order_no,:p_order_date,:p_effective_date,:p_district_name_local,:p_alias_english,:p_alias_local,:p_census_2011_code,:p_sscode,:p_coordinates,:p_gaz_pub_date,:list_of_district_full,:list_of_district_part,:list_of_subdistrict_full,:list_of_old_subdistrict_village,:list_of_new_subdistrict_village,:p_map_attachment_code,:p_headquarter_name_english,:p_headquarter_local_name)",resultClass=DistrictBean.class, name = "createDistrictQuery")
public class DistrictBean
{
	@Id 
	private String create_district_fn;

	@Column(name = "create_district_fn", nullable = false)
	public String getCreate_district_fn() {
		return create_district_fn;
	}

	public void setCreate_district_fn(String create_district_fn) {
		this.create_district_fn = create_district_fn;
	}

	
	
}