package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
//@NamedNativeQuery(query="Select * from create_localbody_fn(:local_body_name_english,:local_body_name_local,:local_body_type_code,:state_code,:alias_english,:alias_local,:createdby,:is_pesa,:coordinates,:replaces,:land_region_code,:replacedby,:land_region_type,:coverage_type,:order_no,:order_date,:effective_date,:gaz_pub_date,:order_path,:file_location,:local_body_subtype_code,:parent_local_body_code)",name="insertLocalBody",resultClass=InsertLocalGovtBody.class)
@NamedNativeQuery(query="Select * from create_localbody_fn(:local_body_name_english,:local_body_name_local,:local_body_type_code,:state_code,:alias_english,:alias_local,:createdby,:is_pesa,:coordinates,:replaces,:order_no,:order_date,:effective_date,:gaz_pub_date,:order_path,:operation_code,:land_region_list_mapped,:local_body_subtype_code,:parent_local_body_code,:sscode,:flag_code,:description)",name="insertLocalBody",resultClass=InsertLocalGovtBody.class)


public class InsertLocalGovtBody
{
	@Id 
	private String create_localbody_fn;

	@Column(name = "create_localbody_fn", nullable = false)
	public String getCreate_localbody_fn() {
		return create_localbody_fn;
	}

	public void setCreate_localbody_fn(String create_localbody_fn) {
		this.create_localbody_fn = create_localbody_fn;
	}
}



