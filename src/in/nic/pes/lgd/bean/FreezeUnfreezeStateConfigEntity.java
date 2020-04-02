package in.nic.pes.lgd.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="configuration_lgd_data_confirmation",schema="public")
@NamedNativeQueries({
@NamedNativeQuery(query=" ",                 
name="StateConfigQuery",resultClass=FreezeUnfreezeStateConfigEntity .class),	
})



public class FreezeUnfreezeStateConfigEntity 
{
	
	@Id
	@SequenceGenerator(name = "configuaration_lgd_data_seq_generator", initialValue=1, allocationSize=1,  sequenceName = "configuration_lgd_data_confirmation_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "configuaration_lgd_data_seq_generator")
	@Column(name="id")
	private Integer id;
	
	@Column(name="state_Code")
	private Integer stateCode;
	
	@Column(name="lastupdated")
	private Timestamp updated;
	
	@Column(name="lastupdatedby")
	private Long lastUpdatedBy;
	
	@Column(name="createdon")
	private Timestamp createdOn;
	
	
	@Column(name="createdby")
	private Long createdBy;
	
	
	@Column(name="user_type")
	private Character userType;
	
	
	@Column(name="finalize_at_state_level")
	private Boolean finalizeAtStateLevel;
	
	




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getStateCode() {
		return stateCode;
	}


	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}


	public Timestamp getUpdated() {
		return updated;
	}


	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}


	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}


	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}


	public Timestamp getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}


	public Long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


	public Character getUserType() {
		return userType;
	}


	public void setUserType(Character userType) {
		this.userType = userType;
	}


	public Boolean getFinalizeAtStateLevel() {
		return finalizeAtStateLevel;
	}


	public void setFinalizeAtStateLevel(Boolean finalizeAtStateLevel) {
		this.finalizeAtStateLevel = finalizeAtStateLevel;
	}


	
    
	
	

}
