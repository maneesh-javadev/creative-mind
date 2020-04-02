package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query="select a.village_code,a.flag_code,a.village_version,b.flag_description from village a,flags b where a.flag_code=b.flag_code and a.vlc in (select lrlc from lb_covered_landregion where lb_covered_region_code in (select lb_covered_region_code from localbody where lblc=:localBodyCode and isactive=true)) and a.isactive=true",name="getlocalbodydistibedlist",resultClass=ViewLandRegionDisturbedlist.class)

public class ViewLandRegionDisturbedlist {

	
	@Id  
	@Column(name="village_code")
     private int village_code;
	@Column(name="flag_code")
	private  int flag_code;
	@Column(name="flag_description")
	private String  flag_description;
	@Column(name="village_version")
	private Integer village_version;
	public int getVillage_code() {
		return village_code;
	}
	public void setVillage_code(int village_code) {
		this.village_code = village_code;
	}
	public int getFlag_code() {
		return flag_code;
	}
	public void setFlag_code(int flag_code) {
		this.flag_code = flag_code;
	}
	public String getFlag_description() {
		return flag_description;
	}
	public void setFlag_description(String flag_description) {
		this.flag_description = flag_description;
	}
	public Integer getVillage_version() {
		return village_version;
	}
	public void setVillage_version(Integer village_version) {
		this.village_version = village_version;
	}
	
	
    
 	
	
}
