package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * from get_unmapped_land_region_list_fn(:category,:stateCode)",name="getUnmappedLBList",resultClass=UnmappedLBList.class)
public class UnmappedLBList {

	private int land_region_code;
	private int land_region_version;
	private String land_region_name_english;
	private String land_region_name_local;
	
	@Id
	@Column(name="land_region_code")
	public int getLand_region_code() {
		return land_region_code;
	}
	public void setLand_region_code(int land_region_code) {
		this.land_region_code = land_region_code;
	}
	@Column(name="land_region_version")
	public int getLand_region_version() {
		return land_region_version;
	}
	public void setLand_region_version(int land_region_version) {
		this.land_region_version = land_region_version;
	}
	@Column(name="land_region_name_english")
	public String getLand_region_name_english() {
		return land_region_name_english;
	}
	public void setLand_region_name_english(String land_region_name_english) {
		this.land_region_name_english = land_region_name_english;
	}
	@Column(name="land_region_name_local")
	public String getLand_region_name_local() {
		return land_region_name_local;
	}
	public void setLand_region_name_local(String land_region_name_local) {
		this.land_region_name_local = land_region_name_local;
	}
	
	
}
