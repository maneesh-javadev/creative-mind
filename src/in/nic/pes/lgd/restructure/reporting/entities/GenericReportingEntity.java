package in.nic.pes.lgd.restructure.reporting.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "SELECT row_number() OVER () as id,* from get_statewise_entity_details_for_globalview(:statecode, :entitytype, :entityCode, :entityName, null, null)", name = "globalViewEntityGenericReports", resultClass = GenericReportingEntity.class),
	@NamedNativeQuery(query = "SELECT row_number() OVER () as id,entity_code, entity_name_english, entity_name_local, null as entity_name_short, census_2011_code, ismapupload, "
							+ "census_2001_code, is_pesa, filelocation, file_timestamp, entity_hierarchy from get_parent_land_region_wise_entity_details_fn2(:parentEntityType, :parentStateCode,:parentDistrictCode,:parentSubDistrictCode)", name = "globalViewForHierarchyGenericReports", resultClass = GenericReportingEntity.class),
	@NamedNativeQuery(query = " select row_number() OVER () as id,d.state_code as entity_code, d.state_name_english as entity_name_english, d.state_name_local as entity_name_local, state_or_ut as entity_name_short," 
                            + "(select l.ismapupload from configuration_map_landregion m, configuration_map_landregion_level l  where m.configuration_map_landregion_code = l.configuration_map_landregion_code and m.isactive and l.isactive and m.slc=d.state_code and l.landregion_type='S') as ismapupload,"  
                            + "CAST(d.census_2001_code AS varchar(2)),d.Census_2011_code, COALESCE(A.file_location,'')  as filelocation ,A.file_timestamp,null as is_pesa, null as entity_hierarchy  from State d LEFT OUTER JOIN  government_order_entitywise Ge ON (d.state_code =Ge.entity_code AND d.state_version=Ge.entity_version and Ge.entity_type='S' )" 	
                            + "LEFT OUTER JOIN  attachment A ON ge.order_code=A.announcement_id where d.isactive=true order by d.state_Name_English", name = "globalViewForStateReport", resultClass = GenericReportingEntity.class)})
public class GenericReportingEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private Integer id;

	
	@Column(name = "entity_code")
	private Integer entityCode;

	@Column(name = "entity_name_english")
	private String entityNameEnglish;

	@Column(name = "entity_name_local")
	private String entityNameLocal;

	@Column(name = "entity_name_short")
	private String entityNameShort;

	@Column(name = "census_2011_code")
	private String census2011Code;

	@Column(name = "ismapupload")
	private Boolean isMapUpload;

	@Column(name = "census_2001_code")
	private String census2001Code;

	@Column(name = "is_pesa")
	private String isPesa;

	@Column(name = "filelocation")
	private String fileLocation;

	@Column(name = "file_timestamp")
	private String fileTimestamp;
	
	@Column(name = "entity_hierarchy")
	private String entityHierarchy;

	@Transient
	private String searchCriteriaType;

	@Transient
	private Integer paramEntityCode;

	@Transient
	private String paramEntityName;
	
	@Transient
	private Integer paramStateCode;
	
	@Transient
	private Integer paramDistrictCode;

	@Transient
	private Integer paramSubDistrictCode;
	
	@Transient
	private Integer paramBlockCode;
	
	@Transient
	private String localBodyLevelCodes;
	
	@Transient
	private String localBodyTypeParam;
	
	
	@Transient
	private Integer screenWidth;
	
	@Transient
	private Integer screenHeight;
	
	public String getLocalBodyTypeParam() {
		return localBodyTypeParam;
	}

	public void setLocalBodyTypeParam(String localBodyTypeParam) {
		this.localBodyTypeParam = localBodyTypeParam;
	}

	public String getLocalBodyLevelCodes() {
		return localBodyLevelCodes;
	}

	public void setLocalBodyLevelCodes(String localBodyLevelCodes) {
		this.localBodyLevelCodes = localBodyLevelCodes;
	}

	public Integer getParamBlockCode() {
		return paramBlockCode;
	}

	public void setParamBlockCode(Integer paramBlockCode) {
		this.paramBlockCode = paramBlockCode;
	}

	@Transient
	private String captchaAnswer;
	
	@Transient
	private String entitesForMessage;

	public GenericReportingEntity() {
		super();
	}

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityNameEnglish() {
		return entityNameEnglish;
	}

	public void setEntityNameEnglish(String entityNameEnglish) {
		this.entityNameEnglish = entityNameEnglish;
	}

	public String getEntityNameLocal() {
		return entityNameLocal;
	}

	public void setEntityNameLocal(String entityNameLocal) {
		this.entityNameLocal = entityNameLocal;
	}

	public String getEntityNameShort() {
		return entityNameShort;
	}

	public void setEntityNameShort(String entityNameShort) {
		this.entityNameShort = entityNameShort;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	public Boolean getIsMapUpload() {
		return isMapUpload;
	}

	public void setIsMapUpload(Boolean isMapUpload) {
		this.isMapUpload = isMapUpload;
	}

	public String getCensus2001Code() {
		return census2001Code;
	}

	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}

	public String getIsPesa() {
		return isPesa;
	}

	public void setIsPesa(String isPesa) {
		this.isPesa = isPesa;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileTimestamp() {
		return fileTimestamp;
	}

	public void setFileTimestamp(String fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}
	
	public String getEntityHierarchy() {
		return entityHierarchy;
	}

	public void setEntityHierarchy(String entityHierarchy) {
		this.entityHierarchy = entityHierarchy;
	}

	public String getSearchCriteriaType() {
		return searchCriteriaType;
	}

	public void setSearchCriteriaType(String searchCriteriaType) {
		this.searchCriteriaType = searchCriteriaType;
	}

	public Integer getParamEntityCode() {
		return paramEntityCode;
	}

	public void setParamEntityCode(Integer paramEntityCode) {
		this.paramEntityCode = paramEntityCode;
	}

	public String getParamEntityName() {
		return paramEntityName;
	}

	public void setParamEntityName(String paramEntityName) {
		this.paramEntityName = paramEntityName;
	}

	public Integer getParamStateCode() {
		return paramStateCode;
	}

	public void setParamStateCode(Integer paramStateCode) {
		this.paramStateCode = paramStateCode;
	}
	
	public Integer getParamDistrictCode() {
		return paramDistrictCode;
	}

	public void setParamDistrictCode(Integer paramDistrictCode) {
		this.paramDistrictCode = paramDistrictCode;
	}
	
	public Integer getParamSubDistrictCode() {
		return paramSubDistrictCode;
	}

	public void setParamSubDistrictCode(Integer paramSubDistrictCode) {
		this.paramSubDistrictCode = paramSubDistrictCode;
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	public String getEntitesForMessage() {
		return entitesForMessage;
	}

	public void setEntitesForMessage(String entitesForMessage) {
		this.entitesForMessage = entitesForMessage;
	}

	public Integer getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(Integer screenWidth) {
		this.screenWidth = screenWidth;
	}

	public Integer getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(Integer screenHeight) {
		this.screenHeight = screenHeight;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
