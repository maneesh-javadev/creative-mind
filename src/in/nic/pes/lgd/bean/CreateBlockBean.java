package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
/*@NamedNativeQuery(query="Select * from create_block_fn(:district_code,:userid,:block_name_english,:block_name_local,:alias_english,:alias_local,:census_2011_code,:sscode,:coordinates,:headquarter_name_english,:headquarter_local_name,:order_no,:order_date,:effective_date,:gaz_pub_date,:list_of_block_full,:list_of_block_part,:list_of_villages_full,:map_attachment_code)",resultClass=CreateBlockBean.class, name = "createBlockQuery")*/

@NamedNativeQuery(query="Select * from create_block_fn(:p_district_code,:p_userid,:p_block_name_english,:p_order_no,:p_order_date,:p_effective_date,:p_operation_code,:p_flag_code,:p_block_name_local,:p_alias_english,:p_alias_local,:p_census_2011_code,:p_sscode, :p_coordinates,:p_headquarter_name_english,:headquarter_local_name,:p_gaz_pub_date,:list_of_villages,:list_of_ulbs,:list_of_block_full,:list_of_block_part,:list_of_block_villages_full,:list_of_block_ulb_full,:p_map_attachment_code)",resultClass=CreateBlockBean.class, name = "createBlockQuery")
public class CreateBlockBean
{
	@Id 
	private String create_block_fn;
	@Column(name = "create_block_fn", nullable = false)
	public String getCreate_block_fn() {
		return create_block_fn;
	}

	public void setCreate_block_fn(String create_block_fn) {
		this.create_block_fn = create_block_fn;
	}

		
}