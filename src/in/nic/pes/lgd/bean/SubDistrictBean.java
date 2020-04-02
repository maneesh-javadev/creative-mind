
package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="Select * from create_subdistrict_fn(:p_district_code,:p_userid,:p_subdistrict_name_english,:p_subdistrict_name_local,:p_alias_english,:p_alias_local,:p_census_2011_code,:p_sscode,:p_coordinates,:p_headquarter_name_english,:headquarter_local_name,:p_order_no,:p_order_date,:p_effective_date,:p_gaz_pub_date,:list_of_subdistrict_full,:list_of_subdistrict_part,:list_of_villages_full,:mapLandregionCode)",resultClass=SubDistrictBean.class, name = "createSubDistrictQuery")
public class SubDistrictBean
{
	@Id 
	private String create_subdistrict_fn;

	@Column(name = "create_subdistrict_fn", nullable = false)
	public String getCreate_subdistrict_fn() {
		return create_subdistrict_fn;
	}

	public void setCreate_subdistrict_fn(String create_subdistrict_fn) {
		this.create_subdistrict_fn = create_subdistrict_fn;
	}

	
	
}