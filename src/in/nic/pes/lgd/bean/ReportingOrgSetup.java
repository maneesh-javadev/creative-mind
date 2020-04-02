package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "reporting_org_setup")
public class ReportingOrgSetup 
{
	@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="seq_reporting_org_setup")})
	@GeneratedValue(generator = "sequence")
    @Id
	@Column(name = "id")
    private Integer id;
	
	/*private OrganizationDesignation orgDesignation;*/
	@Column(name = "designation_code")
	private int designationCode;
    @Column(name = "report_to")
    private Integer reportTo;
    
    public ReportingOrgSetup() {
		super();
	}
    
// TODO Remove unused code found by UCDetector
//    	public ReportingOrgSetup(Integer id, int designationCode, Integer reportTo)
//    	{
// 		super();
// 		this.id = id;
// 		this.designationCode = designationCode;
// 		this.reportTo = reportTo;
// 	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDesignationCode() {
		return designationCode;
	}

	public void setDesignationCode(int designationCode) {
		this.designationCode = designationCode;
	}

	public Integer getReportTo() {
		return reportTo;
	}

	public void setReportTo(Integer reportTo) {
		this.reportTo = reportTo;
	}
	
/*	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_code", referencedColumnName = "designation_code")
	public OrganizationDesignation getOrgDesignation() 
	{
		return orgDesignation;
	}

	public void setOrgDesignation(OrganizationDesignation orgDesignation) 
	{
		this.orgDesignation = orgDesignation;
	}*/
}
