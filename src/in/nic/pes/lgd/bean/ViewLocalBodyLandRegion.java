package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select * from get_lb_coverage_complete_list_fn(:localBodyCode) where land_region_type='D'",name="getDistrictNameListforcoveredarea",resultClass=ViewLocalBodyLandRegion.class),

@NamedNativeQuery(query="select * from get_lb_coverage_complete_list_fn(:localBodyCode) where land_region_type='T'",name="getSubdisticnameListforcoveredarea",resultClass=ViewLocalBodyLandRegion.class),
@NamedNativeQuery(query="select * from get_lb_coverage_complete_list_fn(:localBodyCode) where land_region_type='V'",name="getVillageNameListforcoveredarea",resultClass=ViewLocalBodyLandRegion.class),
@NamedNativeQuery(query="select * from get_lb_coverage_complete_list_fn(:localBodyCode)",name="getListforcoveredarea",resultClass=ViewLocalBodyLandRegion.class)
})
public class ViewLocalBodyLandRegion {

	
	@Id  
	@Column(name="land_region_name_english")
    private String land_region_name_english;
	@Column(name="land_region_type")
	private  String land_region_type;
	@Column(name="coverage_type")
	private String  coverage_type;
	@Column(name="land_region_code")
	private Integer land_region_code;
	
	@Column(name="combined_lb_type")
	private String combineLR;
	
	public String getCombineLR() {
		return combineLR;
	}
	public void setCombineLR(String combineLR) {
		this.combineLR = combineLR;
	}
	
	public String getLand_region_name_english() {
		return land_region_name_english;
	}
	public void setLand_region_name_english(String land_region_name_english) {
		this.land_region_name_english = land_region_name_english;
	}
	public String getLand_region_type() {
		return land_region_type;
	}
	public void setLand_region_type(String land_region_type) {
		this.land_region_type = land_region_type;
	}
	public String getCoverage_type() {
		return coverage_type;
	}
	public void setCoverage_type(String coverage_type) {
		this.coverage_type = coverage_type;
	}
	public Integer getLand_region_code() {
		return land_region_code;
	}
	public void setLand_region_code(Integer land_region_code) {
		this.land_region_code = land_region_code;
	}
	
	
    
 	
	
}
