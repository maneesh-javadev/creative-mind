package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;



@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "select * from get_entity_effective_date_details(:entityCode,:entityType,'E')", name = "GET_ENTITY_EFFECTIVE_DATE", resultClass = GetEntityEffectiveDate.class),
	
	@NamedNativeQuery(query = " select * from get_entity_effective_date_details(:entityCode,:entityType,'D')", name = "GET_ENTITY_DETAILS", resultClass = GetEntityEffectiveDate.class),
	
	@NamedNativeQuery(query = "select * from get_entity_effective_date_details(:entityCode,:entityType,'V',:entityVersion)", name = "GET_ENTITY_EFFECTIVE_DATE_BY_ENTITY_VERSION", resultClass = GetEntityEffectiveDate.class),
	
	@NamedNativeQuery(query = " select * from get_entity_effective_date_details(:entityCode,:entityType,'H') ",	name = "GET_ENTITY_HISTORY", resultClass = GetEntityEffectiveDate.class),
	
	
})

public class GetEntityEffectiveDate {
	
	
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "entity_code")
	private Integer entityCode;

	@Column(name = "entity_name_english")
	private String entityNameEnglish;
	
	@Column(name = "entity_name_local")
	private String entityNameLocal;
	
	@Column(name = "parent_code")
	private Integer parentCode;
	
	@Column(name = "parent_name")
	private String parentName;
	
	@Column(name = "entity_version")
	private Integer entityVersion;
	
	@Column(name = "entity_minor_version")
	private Integer entityMinorVersion;
	
	@Column(name = "effective_date")
	private Date effectiveDate;
	
	@Column(name = "new_effective_date")
	private Date newEffectiveDate;
	
	@Transient
	private Character entityType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(Integer entityVersion) {
		this.entityVersion = entityVersion;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getNewEffectiveDate() {
		return newEffectiveDate;
	}

	public void setNewEffectiveDate(Date newEffectiveDate) {
		this.newEffectiveDate = newEffectiveDate;
	}

	public Integer getEntityMinorVersion() {
		return entityMinorVersion;
	}

	public void setEntityMinorVersion(Integer entityMinorVersion) {
		this.entityMinorVersion = entityMinorVersion;
	}

	public Character getEntityType() {
		return entityType;
	}

	public void setEntityType(Character entityType) {
		this.entityType = entityType;
	}
	
	

}
