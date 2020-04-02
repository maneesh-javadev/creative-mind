package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from get_details_of_entity_according_to_createdon(:fromDate,:toDate,:slc,:district,:subdistrict,:village,:localBodyType,:block)",name ="entityChangesInGivenTime",resultClass=EntityChangesInGivenTime.class)
public class EntityChangesInGivenTime {

	@Id
	@Column(name="row_id")
	private Integer srNumber;
	
	@Column(name="entity_type")
	private String entityType;
	
	@Column(name="entity_code")
	private Integer entityCode;
	
	@Column(name="entity_version")
	private Integer entityVersion;
	
	@Column(name="entity_name_english")
	private String entityNameEnglish;
	
	@Column(name="entity_name_local")
	private String entityNameLocal;
	
	@Column(name="census_2011_code")
	private String census2011Code;
	
	@Column(name="operation_performed_on_entity")
	private String operationPerformedOnEntity;

	public Integer getSrNumber() {
		return srNumber;
	}

	public void setSrNumber(Integer srNumber) {
		this.srNumber = srNumber;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

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

	public String getEntityNameLocal() {
		return entityNameLocal;
	}

	public void setEntityNameLocal(String entityNameLocal) {
		this.entityNameLocal = entityNameLocal;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	public String getOperationPerformedOnEntity() {
		return operationPerformedOnEntity;
	}

	public void setOperationPerformedOnEntity(String operationPerformedOnEntity) {
		this.operationPerformedOnEntity = operationPerformedOnEntity;
	}
	
	
	
	
	
	
	
}
