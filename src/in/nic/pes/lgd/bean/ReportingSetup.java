package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "reporting_setup", schema = "public")
public class ReportingSetup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name 		= "sequence", 
					  strategy 	= "sequence", 
					  parameters={@Parameter(name="sequence",value="seq_reporting_setup")})
    @Id
    @GeneratedValue(generator = "sequence")
	@Column(name = "id", unique=true, nullable=false)
	private Integer id;

	@Column(name = "designation_code")
	private Integer designationCode;
	
	@Column(name = "report_to")
	private Integer reportTo;
	
	/**
	 * Default Constructor
	 */
	public ReportingSetup() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDesignationCode() {
		return designationCode;
	}

	public void setDesignationCode(Integer designationCode) {
		this.designationCode = designationCode;
	}

	public Integer getReportTo() {
		return reportTo;
	}

	public void setReportTo(Integer reportTo) {
		this.reportTo = reportTo;
	}

}

