package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select * from get_freeze_unfreeze_report_fn()",                
name="statewiseFreezeStatus",resultClass=EntityFreezeStatus.class),
@NamedNativeQuery(query="select * from get_freeze_unfreeze_report_fn(:stateCode)",     
name="districtwiseFreezeStatus",resultClass=EntityFreezeStatus.class)
})

public class EntityFreezeStatus {
	
	@Id
	@Column(name="entity_code")
	private Integer entityCode;
	
	@Column(name="entity_name")
	private String entityName;
	
	@Column(name="status")
	private Character status;
	
	
	@Column(name="entities_status")
	private String entitiesStatus;

	
	@Column(name="census_2011_code")
	private String census2011Code;

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
	
	

	public String getEntitiesStatus() {
		return entitiesStatus;
	}

	public void setEntitiesStatus(String entitiesStatus) {
		this.entitiesStatus = entitiesStatus;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	} 
	
	

}
