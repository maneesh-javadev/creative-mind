package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="SELECT * FROM get_covered_land_region_ward_fn(:ward_code) where land_region_type='D'",name="landregionnameforwardDistrict",resultClass=GetLandRegionNameforWard.class),
@NamedNativeQuery(query="SELECT * FROM get_covered_land_region_ward_fn(:ward_code) where land_region_type='T'",name="landregionnameforwardSubDistrict",resultClass=GetLandRegionNameforWard.class),
@NamedNativeQuery(query="SELECT * FROM get_covered_land_region_ward_fn(:ward_code) where land_region_type='V'",name="landregionnameforwardVillage",resultClass=GetLandRegionNameforWard.class)
})

public class GetLandRegionNameforWard
{
	private Integer landRegionCode;
	private String landRegionName;
	private char landRegionType;
	
	@Id
	@Column(name="land_region_code") 
	public Integer getLandRegionCode() 
	{
		return landRegionCode;
	}
	public void setLandRegionCode(Integer landRegionCode) 
	{
		this.landRegionCode = landRegionCode;
	}
	@Column(name="land_region_name") 
	public String getLandRegionName() 
	{
		return landRegionName;
	}
	public void setLandRegionName(String landRegionName) 
	{
		this.landRegionName = landRegionName;
	}
	@Column(name="land_region_type") 
	public char getLandRegionType() 
	{
		return landRegionType;
	}
	public void setLandRegionType(char landRegionType) 
	{
		this.landRegionType = landRegionType;
	}
	
}
