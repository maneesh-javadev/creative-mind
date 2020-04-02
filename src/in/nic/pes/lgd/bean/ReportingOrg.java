package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
*
* @Author: Ram
*/

@Entity
@Table(name = "reporting_lb_setup")
public class ReportingOrg { // NO_UCD (use default)
	
    private Integer id;
	private OrganizationDesignation orgDesignation;
	private Integer designationCode;
    private int reportTo;
    private OrgLocatedAtLevels orgLocatedAtLevels;
    private boolean isactive;
    
    @GenericGenerator
  	(name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="map_reporting_org")})
	@Id
	@GeneratedValue(generator = "sequence")
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
    
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "designation_code", insertable=false, updatable=false)
	public Integer getDesignationCode() {
		return designationCode;
	}
	
	public void setDesignationCode(Integer designationCode) {
		this.designationCode = designationCode;
	}
	
	@Column(name = "report_to", nullable=true)
	public int getReportTo() {
		return reportTo;
	}
	
	public void setReportTo(int reportTo) {
		this.reportTo = reportTo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	      @JoinColumn(name = "org_located_level_code", referencedColumnName = "org_located_level_code")
	public OrgLocatedAtLevels getOrgLocatedAtLevels() {
		return orgLocatedAtLevels;
	}

	public void setOrgLocatedAtLevels(OrgLocatedAtLevels orgLocatedAtLevels) {
		this.orgLocatedAtLevels = orgLocatedAtLevels;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_code", referencedColumnName = "designation_code")
	public OrganizationDesignation getOrgDesignation() {
		return orgDesignation;
	}

	public void setOrgDesignation(OrganizationDesignation orgDesignation) {
		this.orgDesignation = orgDesignation;
	}

	@Column(name = "isactive")
	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
