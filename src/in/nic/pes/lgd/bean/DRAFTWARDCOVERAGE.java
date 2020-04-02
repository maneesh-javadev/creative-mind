package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQueries({
	@NamedNativeQuery(query="SELECT row_number() OVER () as rid,ward_code, land_region_lc, land_region_type,coverage_type,(case when land_region_type='D' then (select district_name_english from district where dlc=land_region_lc and isactive)  when land_region_type='T' then (select subdistrict_name_english from subdistrict where tlc=land_region_lc and isactive )   when land_region_type='V' then (select village_name_english from village where vlc=land_region_lc and isactive ) end) land_region_name_english from temp_covered_ward_landregion  where isactive and  ward_code=:wardCode and ward_version=:wardVersion",name="GET_DRAFT_WARD_COVERAGE",resultClass=DRAFTWARDCOVERAGE.class),
	@NamedNativeQuery(query="SELECT row_number() OVER () as rid,ward_code, lrlc as land_region_lc, land_region_type,coverage_type,(case when land_region_type='D' then (select district_name_english from district where dlc=lrlc and isactive)  when land_region_type='T' then (select subdistrict_name_english from subdistrict where tlc=lrlc and isactive )   when land_region_type='V' then (select village_name_english from village where vlc=lrlc and isactive ) end) land_region_name_english from ward_covered_landregion , localbody_ward  where localbody_ward.ward_covered_region_code =ward_covered_landregion.ward_covered_region_code and ward_covered_landregion.isactive and localbody_ward.isactive and  ward_code=:wardCode and ward_version=:wardVersion",name="GET_WARD_COVERAGE",resultClass=DRAFTWARDCOVERAGE.class),
})
public class DRAFTWARDCOVERAGE {
	
	
	@Id
	@Column(name="rid")
	private Integer rid;
	
	@Column(name="ward_code")
	private Integer wardCode;
	
	@Column(name="land_region_lc")
	private Integer landRegionCode;

	
	@Column(name="land_region_name_english")
	private String landRegionNameEnglish;
	
	@Column(name="land_region_type")
	private String landRegionType;
	
	@Column(name="coverage_type")
	private String coverageType;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getWardCode() {
		return wardCode;
	}

	public void setWardCode(Integer wardCode) {
		this.wardCode = wardCode;
	}

	public Integer getLandRegionCode() {
		return landRegionCode;
	}

	public void setLandRegionCode(Integer landRegionCode) {
		this.landRegionCode = landRegionCode;
	}

	public String getLandRegionNameEnglish() {
		return landRegionNameEnglish;
	}

	public void setLandRegionNameEnglish(String landRegionNameEnglish) {
		this.landRegionNameEnglish = landRegionNameEnglish;
	}

	public String getLandRegionType() {
		return landRegionType;
	}

	public void setLandRegionType(String landRegionType) {
		this.landRegionType = landRegionType;
	}

	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

	

}
