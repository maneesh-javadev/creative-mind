package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "Select * from create_localbody_fn(:local_body_name_english,"
														  + ":local_body_name_local,"
														  + ":local_body_type_code,"
														  + ":state_code,"
														  + ":alias_english,"
														  + ":alias_local,"
														  + ":createdby,"
														  + ":is_pesa,"
														  + ":coordinates,"
														  + ":replaces,"
														  + ":order_no,"
														  + ":order_date,"
														  + ":effective_date,"
														  + ":gaz_pub_date,"
														  + ":order_path,"
														  + ":operation_code,"
														  + ":land_region_list_mapped,"
														  + ":local_body_subtype_code,"
														  + ":parent_local_body_code,"
														  + ":sscode,"
														  + ":flag_code,"
														  + ":description,"
														  + ":goordercode,"
														  + ":gofilename,"
														  + ":gofilesize,"
														  + ":gofiletype,"
														  + ":mapfilename,"
														  + ":mapfilesize,"
														  + ":mapfiletype)", name = "Insert_Local_Body_Details", resultClass = InsertionLocalBodyFn.class),
@NamedNativeQuery(query=" SELECT change_local_body_name_fn as create_localbody_fn  FROM change_local_body_name_fn(:local_body_code, :local_body_name_name_english, :user_id, :order_no, :order_date, :effective_date, :order_path, :gaz_pub_date, :local_body_name_name_local, :alias_english, :alias_local, :goordercode, :gofilename, :gofilesize, :gofiletype)", name="Change_Local_Body_Name_Fn", resultClass=InsertionLocalBodyFn.class),
@NamedNativeQuery(query=" SELECT change_local_body_parent_fn as create_localbody_fn FROM change_local_body_parent_fn(:local_body_code,:parent_lblc,:user_id,:local_body_version,:order_no,:order_date,:effective_date,:gaz_pub_date,:order_path, :goordercode, :gofilename, :gofilesize, :gofiletype)",name="Change_Local_Body_Parent_Fn", resultClass = InsertionLocalBodyFn.class),
@NamedNativeQuery(query=" SELECT change_type_for_local_body_fn as create_localbody_fn FROM change_type_for_local_body_fn(:local_body_code,:local_body_name_english,:description,:local_body_type_code,:local_body_subtype_code,:parent_local_body_code,:state_code,:effective_date,:createdby,:order_no,:order_date,:gaz_pub_date,:order_path, :goordercode, :gofilename, :gofilesize, :gofiletype)",name="Change_Local_Body_Type_Fn",resultClass=InsertionLocalBodyFn.class),
@NamedNativeQuery(query=" SELECT change_coverage_of_local_body_fn as create_localbody_fn FROM change_coverage_of_local_body_fn("
					  + " :local_body_code,"
					  + " :land_region_list,"
					  + " :user_id,"
					  + " :order_no,"
					  + " :order_date,"
					  + " :effective_date,"
					  + " :gaz_pub_date,"
					  + " :order_path,"
					  + " :contributing_lb_list,"
					  + " :contributing_f_lb_list,"
					  + " :goordercode,"
					  + " :gofilename,"
					  + " :gofilesize,"
					  + " :gofiletype)", name="Change_Local_Body_Covered_Area_Fn", resultClass=InsertionLocalBodyFn.class)														  
})														  
public class InsertionLocalBodyFn {
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
