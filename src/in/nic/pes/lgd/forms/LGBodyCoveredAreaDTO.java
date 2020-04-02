package in.nic.pes.lgd.forms;
/**
 * @author Sushil Shakya
 * @created on 14-03-2013
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ @NamedNativeQuery(query = "SELECT * FROM  get_covered_area_of_lb_fn_with_census_2011_code(:localBodyCode)", name = "getLGBodyCoveredAreaDetail", resultClass = LGBodyCoveredAreaDTO.class) })
public class LGBodyCoveredAreaDTO {

	@Column(name="land_region_type")
	private String landRegionType;
	
	@Id
	@Column(name="lrlc")
	private Integer langRegionLinkCode;
	
	@Column(name="census_2011_code")
	private Integer cencusCode;
	
	@Column(name="land_region_name_english")
	private String landRegionNameEn;
	
	@Column(name="land_region_name_local")
	private String langRegionNameLoc;
		
	public final String getLandRegionType() {
		return landRegionType;
	}

	public final void setLandRegionType(String landRegionType) {
		this.landRegionType = landRegionType;
	}

	public final Integer getLangRegionLinkCode() {
		return langRegionLinkCode;
	}

	public final void setLangRegionLinkCode(Integer langRegionLinkCode) {
		this.langRegionLinkCode = langRegionLinkCode;
	}

	public final Integer getCencusCode() {
		return cencusCode;
	}

	public final void setCencusCode(Integer cencusCode) {
		this.cencusCode = cencusCode;
	}

	public final String getLandRegionNameEn() {
		return landRegionNameEn;
	}

	public final void setLandRegionNameEn(String landRegionNameEn) {
		this.landRegionNameEn = landRegionNameEn;
	}

	public final String getLangRegionNameLoc() {
		return langRegionNameLoc;
	}

	public final void setLangRegionNameLoc(String langRegionNameLoc) {
		this.langRegionNameLoc = langRegionNameLoc;
	}

	public String getLandRegionTypeName() {
		if("D".equals(getLandRegionType())) {
			return "District";
		} else if("P".equals(getLandRegionType())) {
			return "Panchayat";
		} else if("T".equals(getLandRegionType())) {
			return "Tehsil";
		} else if("V".equals(getLandRegionType())) {
			return "Village";
		} else {
			return getLandRegionType();
		}
	}
}