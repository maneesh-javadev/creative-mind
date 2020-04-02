package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="configure_habitations", schema = "public")
public class HabitationConfiguration {
	
	
	@Id
	@Column(name="id")
	@SequenceGenerator(name = "habitation_seq_generator", initialValue=1, allocationSize=1,  sequenceName = "habitation_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habitation_seq_generator")
	private Integer id;
	
	@Column(name="slc")
	private Integer slc;
	
		
	@Column(name="isactive")
	private boolean isactive;
	
	@Column(name="habitation_parent_type")
	private String  parentType;
	
	@Column(name="createdby")
	private Long createdby;
	
	@Column(name="createdon")
	private Date createdon;
	
	
	@Column(name="lastupdatedby")
	private Long lastupdatedby;
	
	@Column(name="lastupdated")
	private Date lastupdated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	
	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Long getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(Long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	
	
	

}
