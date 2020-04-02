package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;



/***
 * 
 * @author Maneesh Kumar
 * @since  01Dec2014
 * 
 */
@Entity
@NamedNativeQueries({
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:level,:listType,null,:selLevel)", name = "localbodyCoverageDistrictList", resultClass = WardCoverageDetail.class),
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:level,:listType,null,:selLevel)", name = "wardCoverageDistrictList", resultClass = WardCoverageDetail.class),
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:level,:listType,null,:selLevel)", name = "localbodyCoverageSubdistrictList", resultClass = WardCoverageDetail.class),
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:level,:listType,null,:selLevel)", name = "wardCoverageSubdistrictList", resultClass = WardCoverageDetail.class),
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:level,:listType,null,:selLevel)", name = "localbodyCoverageVillageList", resultClass = WardCoverageDetail.class),
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:level,:listType,null,:selLevel)", name = "wardCoverageVillageList", resultClass = WardCoverageDetail.class),
		
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:selLevel,:listType,:entityList)", name = "localbodyCoverageSubdistrictListbyDistrict", resultClass = WardCoverageDetail.class),
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:selLevel,:listType,:entityList) ", name = "wardCoverageSubdistrictListbyDistrict", resultClass = WardCoverageDetail.class),
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:selLevel,:listType,:entityList)", name = "localbodyCoverageVillageListbySubdistrict", resultClass = WardCoverageDetail.class),
		@NamedNativeQuery(query = "select * from get_ward_coverage_details(:wardCode,:lblc,:selLevel,:listType,:entityList) ", name = "wardCoverageVillageListbySubdistrict", resultClass = WardCoverageDetail.class),
		
		
		
	})
public class WardCoverageDetail {

	@Id
	@Column(name = "entity_code")
	private String entityCode;

	@Column(name = "entity_name")
	private String entityName;

	@Column(name = "parent_code")
	private Integer parentCode;
	
	@Column(name = "coverage_type")
	private Character coverageType;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public Character getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(Character coverageType) {
		this.coverageType = coverageType;
	}

	

	
	

}
