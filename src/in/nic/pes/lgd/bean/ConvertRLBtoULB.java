package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select * from convert_rlb_ulb_fn(:dest_localbodyCode,:user_id,:localbodyTypeCode,:orderEffectiveDate,:type,:stateCode,:order_no,:order_date,:gaz_pub_date,:order_path,:source_full_localbodyCode,:source_part_localbodyCode,:source_part_coveredAreas,:localbodyEnglishName,:localbodyEnglishName_local,:alias_english,:alias_local,:lbCode)", name ="ConvertRLBtoULBforMerge",resultClass=ConvertRLBtoULB.class),
@NamedNativeQuery(query = "select * from convert_rlb_ulb_fn(:dest_localbodyCode,:user_id,:localbodyTypeCode,:orderEffectiveDate,:type,:stateCode,:order_no,:order_date,:gaz_pub_date,:order_path,:source_full_localbodyCode,:source_part_localbodyCode,:source_part_coveredAreas,:localbodyEnglishName,:localbodyEnglishName_local,:alias_english,:alias_local,:lbCode)", name ="ConvertRLBtoULBforNew",resultClass=ConvertRLBtoULB.class)
})
public class ConvertRLBtoULB {
	@Id
	private Integer convert_rlb_ulb_fn;
	@Column(name="convert_rlb_ulb_fn")

	public Integer getConvert_rlb_ulb_fn() {
		return convert_rlb_ulb_fn;
	}

	public void setConvert_rlb_ulb_fn(Integer convert_rlb_ulb_fn) {
		this.convert_rlb_ulb_fn = convert_rlb_ulb_fn;
	}
	
	
	
	
}
