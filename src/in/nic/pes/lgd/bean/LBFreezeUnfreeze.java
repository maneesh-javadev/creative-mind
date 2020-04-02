/**
 * Save Local Body Freeze/Un-freeze.
 * @author Ashish Dhupia on 15-02-2015
 * 
 */

package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "localbody_freeze")
public class LBFreezeUnfreeze implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer lblc;
	private Integer status;
	
	private Integer population;
	
	/*@Transient
	private String coverage;*/
	
	@Id
	@Column(name = "lblc")
	public Integer getLblc() {
		return lblc;
	}
	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	
	
	
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	/*public String getCoverage() {
		return coverage;
	}
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}*/
	
	@Column(name = "population")
	public Integer getPopulation() {
		return population;
	}
	public void setPopulation(Integer population) {
		this.population = population;
	}
	
	
	
	
}
