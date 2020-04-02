package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "SELECT distinct *, null as land_region_type, null as category,(select case when  count(1)>0 then true else false end from subdistrict where dlc=land_region_code and isactive) as validateParentContainChild,cast(null as varchar)parent_name FROM  get_statewise_draft_unmapped_land_region_list_fn(:lbTypeLevel, :stateCode, :draftTempCode, :processId) order by land_region_name_english", name = "Unmapped_Land_Regions", resultClass = UnmappedLandregions.class),
	@NamedNativeQuery(query = "SELECT distinct *, null as land_region_type, null as category ,(select case when  count(1)>0 then true else false end from subdistrict where dlc=land_region_code and isactive) as validateParentContainChild,cast(null as varchar)parent_name FROM  get_statewise_draft_unmapped_land_region_list_fn_district(:lbTypeLevel, :stateCode, :districtCode, :draftTempCode, :processId) order by land_region_name_english", name = "Unmapped_Land_Regions_For_District_User", resultClass = UnmappedLandregions.class),
	@NamedNativeQuery(query = "select distinct land_region_code, land_region_version,land_region_name_english ,land_region_name_local , land_region_type, null as isused,(select case when  count(1)>0 then true else false end from subdistrict where dlc=land_region_code and isactive) as validateParentContainChild, case when land_region_type='T' then (select district_name_english from district where dlc=(select dlc from subdistrict where tlc=land_region_code and isactive) and isactive) else cast(null as varchar)end parent_name,entity_hierarchy from get_partly_unmapped_land_region_list_levelwise_fn(:unmappedCoverageLevel, :localBodyCodes, :localBodyType)  order by land_region_name_english", name = "Partially_Unmapped_List_At_LR_Levels", resultClass = UnmappedLandregions.class),
	@NamedNativeQuery(query = "select *, null as isused,(select case when  count(1)>0 then true else false end from subdistrict where dlc=land_region_code and isactive) as validateParentContainChild, case when land_region_type='V' then (select subdistrict_name_english from subdistrict where tlc=(select tlc from village where vlc=land_region_code and isactive) and isactive)  when land_region_type='T' then  (select district_name_english from district where dlc=(select dlc from subdistrict where tlc=land_region_code and isactive) and isactive) end parent_name,entity_hierarchy from get_partly_unmapped_land_region_list_levelwise_fn(:unmappedCoverageLevel, :seltlcCode, :localBodyType) where land_region_code not in(:existVillage)  order by land_region_name_english", name = "Partially_Unmapped_List_At_TLC_Levels", resultClass = UnmappedLandregions.class),
})

public class UnmappedLandregions {

	@Id
	@Column(name = "land_region_code")
	private Integer landRegionCode;
	
	@Column(name = "land_region_name_english")
	private String landRegionNameEnglish;
	
	@Column(name = "land_region_name_local")
	private String landRegionNameLocal;
	
	@Column(name = "land_region_version")
	private Integer landRegionVersion;

	@Column(name = "land_region_type")
	private String landRegionType;

	/*@Column(name = "category")
	private String category;*/
	
	@Column(name = "isused")
	private Boolean isUsed;
	
	
	@Column(name = "validateParentContainChild")
	private Boolean validateParentContainChild;
	
	@Column(name = "parent_name")
	private String parentName;
	
	@Column(name = "entity_hierarchy")
	private String entityHierarchy;
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public String getLandRegionNameLocal() {
		return landRegionNameLocal;
	}

	public void setLandRegionNameLocal(String landRegionNameLocal) {
		this.landRegionNameLocal = landRegionNameLocal;
	}

	public Integer getLandRegionVersion() {
		return landRegionVersion;
	}

	public void setLandRegionVersion(Integer landRegionVersion) {
		this.landRegionVersion = landRegionVersion;
	}

	public String getLandRegionType() {
		return landRegionType;
	}

	public void setLandRegionType(String landRegionType) {
		this.landRegionType = landRegionType;
	}

	/*public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}*/

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public Boolean getValidateParentContainChild() {
		return validateParentContainChild;
	}

	public void setValidateParentContainChild(Boolean validateParentContainChild) {
		this.validateParentContainChild = validateParentContainChild;
	}

	public String getEntityHierarchy() {
		return entityHierarchy;
	}

	public void setEntityHierarchy(String entityHierarchy) {
		this.entityHierarchy = entityHierarchy;
	}
	
	
	
}
