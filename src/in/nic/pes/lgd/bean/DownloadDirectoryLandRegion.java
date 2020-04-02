package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(query = "SELECT row_number() OVER () as rid, *,cast(null as character varying(100))land_Region_code FROM get_entitywise_details(:entity_type,:entity_parent_type,:entity_code) order by census_2011_code,entity_name_english ", name = "getDownloadDirectory", resultClass = DownloadDirectoryLandRegion.class),
		@NamedNativeQuery(query = "SELECT row_number() OVER () as rid, * FROM get_ulb_wards_by_state_code_fn(:entity_code) ", name = "StateWiseUlbWardQuery", resultClass = DownloadDirectoryLandRegion.class),
		@NamedNativeQuery(query = "SELECT row_number() OVER () as rid, *,cast(null as character varying(100))land_Region_code FROM get_entitywise_details_block(:entity_type,:entity_parent_type,:entity_code) order by alias_local,entity_name_english ", name = "getDownloadDirectoryblock", resultClass = DownloadDirectoryLandRegion.class),
		@NamedNativeQuery(query = "SELECT row_number() OVER () as rid, *,cast(null as character varying(100))land_Region_code FROM get_entitywise_details_by_date(:entity_type,:fromDate,:toDate,:entity_parent_type,:entity_code) order by entity_code,entity_name_english, entity_version ", name = "getDownloadDirectorybyDate", resultClass = DownloadDirectoryLandRegion.class) })
public class DownloadDirectoryLandRegion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "rid", nullable = false)
	private Integer rid;
	
	@Column(name = "entity_code", nullable = false)
	private Integer entityCode;
	
	@Column(name = "entity_version")
	private Integer entityVersion;
	
	@Column(name = "entity_name_english")
	private String entityNameEnglish;
	
	@Column(name = "census_2001_code")
	private String census2001Code;
	
	@Column(name = "census_2011_code")
	private String census2011Code;
	
	@Column(name = "state_or_ut")
	private String stateOrUt;
	
	@Column(name = "state_code")
	private Integer stateCode;
	
	@Column(name = "district_code")
	private Integer districtCode;
	
	@Column(name = "subdistrict_code")
	private Integer subDistrictCode;

	@Column(name = "entity_name_local")
	private String entityNameEnglishLocal;
	
	@Column(name="alias_local")
	private String aliasLocal;
	
	/*	Added by ashish dhupia for change in function column added maped with dist/sub district*/
	@Column(name="land_Region_code")
	private String landRegioncode;
	

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public Integer getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(Integer entityVersion) {
		this.entityVersion = entityVersion;
	}

	public String getEntityNameEnglish() {
		return entityNameEnglish;
	}

	public void setEntityNameEnglish(String entityNameEnglish) {
		this.entityNameEnglish = entityNameEnglish;
	}

	public String getCensus2001Code() {
		if (census2001Code != null){
			return census2001Code;
		}	
		census2001Code = "";
		return census2001Code;
	}

	public void setCensus2001Code(String census2001Code) {

		this.census2001Code = census2001Code;

	}

	public String getCensus2011Code() {
		if (census2011Code != null){
			return census2011Code;
		}	
		census2011Code = "";
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	public String getStateOrUt() {
		return stateOrUt;
	}

	public void setStateOrUt(String stateOrUt) {
		this.stateOrUt = stateOrUt;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getSubDistrictCode() {
		return subDistrictCode;
	}

	public void setSubDistrictCode(Integer subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	public String getEntityNameEnglishLocal() {
		return entityNameEnglishLocal;
	}

	public void setEntityNameEnglishLocal(String entityNameEnglishLocal) {
		this.entityNameEnglishLocal = entityNameEnglishLocal;
	}
	
	public String getAliasLocal() {
		return aliasLocal;
	}

	public void setAliasLocal(String aliasLocal) {
		this.aliasLocal = aliasLocal;
	}

	public String getLandRegioncode() {
		return landRegioncode;
	}

	public void setLandRegioncode(String landRegioncode) {
		this.landRegioncode = landRegioncode;
	}
}
