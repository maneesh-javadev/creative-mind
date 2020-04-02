package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity

@NamedNativeQueries({ 
	@NamedNativeQuery(query = "select row_number() over() as count, *,cast(null as character varying)mapped_localbody," + 
			"case when land_region_type='T' then (select district_name_english from district where isactive and dlc=parent_code)" + 
			" when land_region_type='V' then (select subdistrict_name_english from subdistrict where isactive and tlc=parent_code) end  parent_name,"
			+ "cast(case when land_region_type='T' then (select  district_name_english||'(District)' from district where isactive and dlc=parent_code)"
			+ " when land_region_type='V' then ( select subdistrict_name_english||'(Sub-District),'||d.district_name_english||'(District)' from subdistrict t"
			+ " inner join district d on t.dlc=d.dlc and d.isactive where t.tlc=parent_code and t.isactive) end as character varying ) entity_hierarchy "
			+ " from get_renew_lb_coverage_complete_list_fn(:localBodyCode)", name = "Fetch_Completed_Coverage_Details", resultClass = CompletedCoverageDetails.class),
	@NamedNativeQuery(query = "select *,cast(null as character varying)parent_name,cast(null as character varying)entity_hierarchy from get_gis_draft_localbody_coverage (:localBodyCode)", name = "Fetch_GIS_Completed_Coverage_Details", resultClass = CompletedCoverageDetails.class),
	
})
public class CompletedCoverageDetails {

	@Id
	@Column(name = "count", nullable = false)
	private Integer count;
	
	@Column(name = "land_region_code")
	private Integer landRegionCode;
	
	@Column(name = "land_region_name_english")
	private String landRegionNameEnglish;
	
	@Column(name = "land_region_type")
	private String landRegionType;

	@Column(name = "coverage_type")
	private String coverageType;
	
	/*@Column(name = "combined_lb_type")
	private String combinedLBType;*/
	
	@Column(name = "parent_code")
	private Integer parentCode;

	
	@Column(name = "mapped_localbody")
	private String mappedLocalbody;

	@Column(name = "parent_name")
	private String parentName;
	
	@Column(name = "entity_hierarchy")
	private String entityHierarchy;
	/**
	 * Default Constructor
	 */
	public CompletedCoverageDetails() {
		super();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	/*public String getCombinedLBType() {
		return combinedLBType;
	}

	public void setCombinedLBType(String combinedLBType) {
		this.combinedLBType = combinedLBType;
	}*/	

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}
	
	

	public String getMappedLocalbody() {
		return mappedLocalbody;
	}

	public void setMappedLocalbody(String mappedLocalbody) {
		this.mappedLocalbody = mappedLocalbody;
	}
	
	

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	

	public String getEntityHierarchy() {
		return entityHierarchy;
	}

	public void setEntityHierarchy(String entityHierarchy) {
		this.entityHierarchy = entityHierarchy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((landRegionNameEnglish == null) ? 0 : landRegionNameEnglish.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompletedCoverageDetails other = (CompletedCoverageDetails) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (landRegionNameEnglish == null) {
			if (other.landRegionNameEnglish != null)
				return false;
		} else if (!landRegionNameEnglish.equals(other.landRegionNameEnglish))
			return false;
		return true;
	}


}
