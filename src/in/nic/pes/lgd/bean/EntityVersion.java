package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="Select * from get_entity_version(:entityType,:entityLevel,:entityCode)",resultClass=EntityVersion.class, name = "getEntityVersion")

public class EntityVersion {
	
	@Id 
	private Integer entity_code;
	private Integer entity_version;
	
	@Column(name = "entity_code", nullable = false)
	public Integer getEntity_code() {
		return entity_code;
	}
	public void setEntity_code(Integer entity_code) {
		this.entity_code = entity_code;
	}
	
	@Column(name = "entity_version", nullable = false)
	public Integer getEntity_version() {
		return entity_version;
	}
	public void setEntity_version(Integer entity_version) {
		this.entity_version = entity_version;
	}
	
	
	
	

}
