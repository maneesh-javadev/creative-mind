package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The persistent class for the designation_level_sortorder database table used for Designation Multiple Levels
 * @author Pooja Sharma
 * @since 07-05-2015
 */

@Entity
@Table(name = "designation_level_sortorder")
public class DesignationLevelSortorder{
	
	
	
	
	@GenericGenerator(name 	= "sequence",   strategy 	= "sequence", parameters={@Parameter(name="sequence",value="desgnation_level_sortorder_seq")})
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "sr_no", unique=true, nullable=false)
	private Integer srNo;
	
	/*@ManyToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name = "lgd_designation")*/
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "designation_code", referencedColumnName = "designation_code", nullable = false),
			@JoinColumn(name = "designation_version", referencedColumnName = "designation_version", nullable = false) 
			})
	private LgdDesignation lgdDesignationCode;
	
	@Column(name="org_located_level_code")
	private Integer orgLocatedLevelCode;
	
	@Column(name="sort_order")
	private Integer sortOrder;
	
	@Column(name="located_at_level")
	private Integer locatedAtLevel;
	
	@Column(name="isactive")
	private Boolean isActive;

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public LgdDesignation getLgdDesignationCode() {
		return lgdDesignationCode;
	}

	public void setLgdDesignationCode(LgdDesignation lgdDesignationCode) {
		this.lgdDesignationCode = lgdDesignationCode;
	}

	public Integer getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}

	public void setOrgLocatedLevelCode(Integer orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getLocatedAtLevel() {
		return locatedAtLevel;
	}

	public void setLocatedAtLevel(Integer locatedAtLevel) {
		this.locatedAtLevel = locatedAtLevel;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
