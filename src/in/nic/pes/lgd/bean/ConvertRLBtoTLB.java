package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select * from convert_rlb_tlb_fn(:source_rlb_type,:p_user_id,:p_slc,:p_order_no,:p_order_date,:p_effective_date,:type_of_operation,:dest_local_body_type_code,:p_operation_code,:p_flag_code,:source_full_fisrt_tier_code_list,:source_part_fisrt_tier_code_list,:source_full_second_tier_code_list,:p_local_body_name_english,:p_local_body_name_local,:p_alias_english,:p_alias_local,:local_body_code_for_merger,:parent_lblc_code,:child_localbody_type_code,:p_gaz_pub_date,:p_order_path)", name ="ConvertRLBtoTLBforMerge",resultClass=ConvertRLBtoTLB.class),
@NamedNativeQuery(query = "select * from convert_rlb_tlb_fn(:source_rlb_type,:p_user_id,:p_slc,:p_order_no,:p_order_date,:p_effective_date,:type_of_operation,:dest_local_body_type_code,:p_operation_code,:p_flag_code,:source_full_fisrt_tier_code_list,:source_part_fisrt_tier_code_list,:source_full_second_tier_code_list,:p_local_body_name_english,:p_local_body_name_local,:p_alias_english,:p_alias_local,:local_body_code_for_merger,:parent_lblc_code,:child_localbody_type_code,:p_gaz_pub_date,:p_order_path)", name ="ConvertRLBtoTLBforNew",resultClass=ConvertRLBtoTLB.class)
})

public class ConvertRLBtoTLB 
{
	@Id
	private String convert_rlb_tlb_fn;

	@Column(name="convert_rlb_tlb_fn",nullable = false)
	public String getConvert_rlb_tlb_fn()
	{
		return convert_rlb_tlb_fn;
	}

	public void setConvert_rlb_tlb_fn(String convert_rlb_tlb_fn)
	{
		this.convert_rlb_tlb_fn = convert_rlb_tlb_fn;
	}
}






