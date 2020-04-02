package in.nic.pes.lgd.forms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({ @NamedNativeQuery(query = "SELECT * from get_statewise_entity_details_for_globalview(:statecode, :entitytype, :entityCode, :entityName, null, null)", name = "globalViewEntityNew", resultClass = DistrictEntity.class) })
public class DistrictEntity implements Serializable { // NO_UCD (unused code)

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
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

	@Transient
	private String searchCriteriaType;

	@Transient
	private Integer paramEntityCode;

	@Transient
	private String paramEntityName;
	
	@Transient
	private Integer paramStateCode;

	@Transient
	private String captchaAnswer;

	public DistrictEntity() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	
	
}
