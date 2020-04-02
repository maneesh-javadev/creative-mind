/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Ram
 */
@Entity
@Table(name = "reporting_lb_setup")
public class ReportingLbSetup {
  //private static final long serialVersionUID = 1L;
	
	@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="seq_reporting_lb_setup")})
	@GeneratedValue(generator = "sequence")
    @Id
	@Column(name = "id")
    private Integer id;
	@Column(name = "designation_code")
	private int designationCode;
    @Column(name = "report_to")
    private Integer reportTo;
    
    public ReportingLbSetup() {
		super();
	}
    
// TODO Remove unused code found by UCDetector
//    	public ReportingLbSetup(Integer id, int designationCode, Integer reportTo) {
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

}
