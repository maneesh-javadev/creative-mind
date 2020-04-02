package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = " select case when ul.land_region_code  in (select * from get_draft_used_lb_lr_temp(ul.land_region_code,:type)) " +
		                 " then cast('F' as character)  else cast('A' as character) end as operation_state,* from get_districtwise_unmapped_land_region_list_fn(:type, :districtCode)ul", name = "getDistWiseUnMLR", resultClass = UnLRDistrictWiseList.class)
public class UnLRDistrictWiseList 
{
	
	private Integer landRegionCode;
	private Integer land_region_version;
	private String localBodyNameEnglish;
	private String land_region_name_local;
	
	/**
	 * Added by Ripunj for Draft Mode LocalBody on 24-04-2015
	 */
    private Character operation_state;
	
	@Id
	@Column(name = "land_region_code")
	public Integer getLandRegionCode() {
		return landRegionCode;
	}
	public void setLandRegionCode(Integer landRegionCode) {
		this.landRegionCode = landRegionCode;
	}
	@Column(name = "land_region_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	@Column(name = "land_region_name_local")
	public String getLand_region_name_local() {
		return land_region_name_local;
	}
	public void setLand_region_name_local(String land_region_name_local) {
		this.land_region_name_local = land_region_name_local;
	}
	@Column(name = "land_region_version")
	public Integer getLand_region_version() {
		return land_region_version;
	}
	public void setLand_region_version(Integer land_region_version) {
		this.land_region_version = land_region_version;
	}
	@Column(name = "operation_state")
	public Character getOperation_state() {
		return operation_state;
	}
	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	
	
	
	
}
