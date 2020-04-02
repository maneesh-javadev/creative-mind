package in.nic.pes.lgd.bean;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ 
@NamedNativeQuery(query = "select * from get_dashboard_entity_change_count(:stateCode,:userType,:finYear)", name = "DashboardChangeEntityCountWithFinYearDTO", resultClass = GetDashboardChangeEntityCount.class),
@NamedNativeQuery(query = "select * from get_dashboard_entity_change_count(:stateCode,:userType)", name = "DashboardChangeEntityCountWithoutFinYearDTO", resultClass = GetDashboardChangeEntityCount.class)
})
public class GetDashboardChangeEntityCount {
	
	
	@Id
	@Column(name="entity_name")
	private String entityName;
	
	@Column(name="no_of_entity_created")
	private Integer noofEntityCreated;
	
	@Column(name="no_of_entity_modified")
	private Integer noofEntityModified;
	
	@Column(name="no_of_entity_shifted")
	private Integer noofEntityShift;
	
	@Column(name="entity_type")
	private Character entityType;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getNoofEntityCreated() {
		return noofEntityCreated;
	}

	public void setNoofEntityCreated(Integer noofEntityCreated) {
		this.noofEntityCreated = noofEntityCreated;
	}

	public Integer getNoofEntityModified() {
		return noofEntityModified;
	}

	public void setNoofEntityModified(Integer noofEntityModified) {
		this.noofEntityModified = noofEntityModified;
	}

	public Integer getNoofEntityShift() {
		return noofEntityShift;
	}

	public void setNoofEntityShift(Integer noofEntityShift) {
		this.noofEntityShift = noofEntityShift;
	}

	public Character getEntityType() {
		return entityType;
	}

	public void setEntityType(Character entityType) {
		this.entityType = entityType;
	}

	
	
	

}
