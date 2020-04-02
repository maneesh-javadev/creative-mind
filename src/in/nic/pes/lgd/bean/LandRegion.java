package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
/**
 * 
 * @author Chandan Soni
 */

@Entity
@Table(name="lb_covered_landregion")
@NamedNativeQuery(query=" SELECT * FROM get_covered_area_of_lb_fn(:localbodyCode)",name="getCoveredRegion",resultClass=LandRegion.class)
public class LandRegion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int landRegionCode;
	private String landRegionNameEnglish;
	private String landRegionNameLocal;
	private char landRegionType;
	
	@Id
	@Column(name = "land_region_code")
	public int getLandRegionCode() {
		return landRegionCode;
	}
	public void setLandRegionCode(int landRegionCode) {
		this.landRegionCode = landRegionCode;
	}
	@Column(name = "land_region_name_english")
	public String getLandRegionNameEnglish() {
		return landRegionNameEnglish;
	}
	public void setLandRegionNameEnglish(String landRegionNameEnglish) {
		this.landRegionNameEnglish = landRegionNameEnglish;
	}
	@Column( name = "land_region_name_local")
	public String getLandRegionNameLocal() {
		return landRegionNameLocal;
	}
	public void setLandRegionNameLocal(String landRegionNameLocal) {
		this.landRegionNameLocal = landRegionNameLocal;
	}
	@Column(name = "land_region_type")
	public char getLandRegionType() {
		return landRegionType;
	}
	public void setLandRegionType(char landRegionType) {
		this.landRegionType = landRegionType;
	}

}

