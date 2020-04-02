package in.nic.pes.lgd.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "configuration_block_village", schema = "public")

public class ConfigurationBlockVillageMapping implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 1563167740443561806L;
	  @GenericGenerator(name="sequence", strategy="sequence", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="configuration_blockvillagseq")})

	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name="id")
	private Integer id;

	@Column(name="state_code")
	private Integer stateCode;
	  
	 @Column(name="user_type")
	private Character userType;
		 
	@Column(name="lastupdated")
	private Timestamp lastupdated;
	  
	@Column(name="lastupdatedby")
	private Long lastupdatedby;
	  
	  
	@Column(name="is_partially_covered")
	private Boolean coverageType;

	
	@Column(name="createdon")
	private Timestamp createdon;

	
	@Column(name="createdby")
	private Long createdby;


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


	

	

	public Character getUserType() {
		return userType;
	}


	public void setUserType(Character userType) {
		this.userType = userType;
	}


	




	public Timestamp getLastupdated() {
		return lastupdated;
	}


	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}


	public Long getLastupdatedby() {
		return lastupdatedby;
	}


	public void setLastupdatedby(Long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}


	public Boolean getCoverageType() {
		return coverageType;
	}


	public void setCoverageType(Boolean coverageType) {
		this.coverageType = coverageType;
	}


	public Timestamp getCreatedon() {
		return createdon;
	}


	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}


	public Long getCreatedby() {
		return createdby;
	}


	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
}
	
	
	
	