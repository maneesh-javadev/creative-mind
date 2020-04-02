package in.nic.pes.lgd.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQuery(query = "select * from get_land_regions_with_warning_fn(:entityType,:parent_entity_type,:parent_entity_code,:category)", name ="GetEntitiesWithWanrning",resultClass=GetEntitiesWithWanrning.class)
public class GetEntitiesWithWanrning 
{
	
	@Column(name = "entity_code")
	private int entityCode;
	@Column(name = "entity_version")
	private int entityVersion;
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "entity_type")
	private String entityType;
	@Column(name = "entity_name_english")
	private String entityNameEnglish;
	@Column(name = "entity_name_local")
	private String entityNameLocal;
	@Column(name = "reason")
	private String reason;	
	@Column(name = "isdisturbed")
	private boolean isdisturbed;	
	
	@Transient
	private String actionInvalidateLB;
	@Transient
	private String actionChangeParent;
	@Transient
	private String actionChangeCoveredArea;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(int entityCode) {
		this.entityCode = entityCode;
	}

	public int getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(int entityVersion) {
		this.entityVersion = entityVersion;
	}
	public boolean isIsdisturbed() {
		return isdisturbed;
	}
	public void setIsdisturbed(boolean isdisturbed) {
		this.isdisturbed = isdisturbed;
	}
	
	public String getActionInvalidateLB() {
		return actionInvalidateLB;
	}
	public void setActionInvalidateLB(String actionInvalidateLB) {
		this.actionInvalidateLB = actionInvalidateLB;
	}
	public String getActionChangeParent() {
		return actionChangeParent;
	}
	public void setActionChangeParent(String actionChangeParent) {
		this.actionChangeParent = actionChangeParent;
	}
	public String getActionChangeCoveredArea() {
		return actionChangeCoveredArea;
	}
	public void setActionChangeCoveredArea(String actionChangeCoveredArea) {
		this.actionChangeCoveredArea = actionChangeCoveredArea;
	}
	
	
}

