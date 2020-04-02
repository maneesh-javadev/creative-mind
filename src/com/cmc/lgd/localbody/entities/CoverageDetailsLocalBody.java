package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "SELECT * FROM get_coverage_lb_list_forlb_fn_hierarchy(:localBodyCodes) where case when :lbTypeLevel is null then true else land_region_type = :lbTypeLevel end;", name = "Fetch_Coverage_Details_LB", resultClass = CoverageDetailsLocalBody.class),
	@NamedNativeQuery(query = "SELECT *, null as local_body_name_english, null as local_body_with_coverage_code,cast(null as character varying)entity_hierarchy FROM get_coverage_lb_list_fn_new(:localBodyCodes) where land_region_type=:lbTypeLevel and land_region_code in(select tlc from subdistrict where dlc in(:landRegionCodes))", name = "Fetch_Coverage_Subdistrict_LB", resultClass = CoverageDetailsLocalBody.class),
	@NamedNativeQuery(query = "SELECT *, null as local_body_name_english, null as local_body_with_coverage_code,cast(null as character varying)entity_hierarchy FROM get_coverage_lb_list_fn_new(:localBodyCodes) where land_region_type=:lbTypeLevel and land_region_code in(select vlc from village where tlc in(:landRegionCodes))", name = "Fetch_Coverage_Village_LB", resultClass = CoverageDetailsLocalBody.class),
	@NamedNativeQuery(query = "SELECT *, null as local_body_name_english, null as local_body_with_coverage_code,cast(null as character varying)entity_hierarchy FROM get_coverage_lb_list_fn_new(:localBodyCode)", name = "View_Land_Region_Details", resultClass = CoverageDetailsLocalBody.class), 
})
public class CoverageDetailsLocalBody {
	
	

	@Id
    @Column(name="land_region_code")
	private Integer landRegionCode;
    
	@Column(name="land_region_version")
	private Integer landRegionVersion;
    
	@Column(name="land_region_name_english")
	private String landRegionNameEnglish;
    
	@Column(name="land_region_type")
	private String landRegionType;
    
	@Column(name="coverage_type")
	private String coverageType;
   
	@Column(name="local_body_name_english")
	private String localbodyNameEnglish;
    
	@Column(name="local_body_with_coverage_code")
	private String localbodyCoverage;
	
	@Column(name = "entity_hierarchy")
	private String entityHierarchy;

	public Integer getLandRegionCode() {
		return landRegionCode;
	}

	public void setLandRegionCode(Integer landRegionCode) {
		this.landRegionCode = landRegionCode;
	}

	public Integer getLandRegionVersion() {
		return landRegionVersion;
	}

	public void setLandRegionVersion(Integer landRegionVersion) {
		this.landRegionVersion = landRegionVersion;
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

	public String getLocalbodyNameEnglish() {
		return localbodyNameEnglish;
	}

	public void setLocalbodyNameEnglish(String localbodyNameEnglish) {
		this.localbodyNameEnglish = localbodyNameEnglish;
	}

	public String getLocalbodyCoverage() {
		return localbodyCoverage;
	}

	public void setLocalbodyCoverage(String localbodyCoverage) {
		this.localbodyCoverage = localbodyCoverage;
	}
	
	public String getEntityHierarchy() {
		return entityHierarchy;
	}

	public void setEntityHierarchy(String entityHierarchy) {
		this.entityHierarchy = entityHierarchy;
	}
}
