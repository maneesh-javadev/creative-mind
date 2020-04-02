/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

/**
 * 
 * @author Pooja
 */
@Entity
@NamedNativeQuery(query = "select * from local_body_designations_fn(:state)", name = "GetDesignation", resultClass = DesignationReporting.class)

public class DesignationReporting implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nomenclature_english", nullable = true)
	private String nomenclatureEnglish;
	@Column(name = "nomenclature_local", nullable = true)
	private String nomenclatureLocal;
	@Id
	@Column(name = "designation", nullable = true)
	private String designation;
	@Column(name = "report_to", nullable = true)
	private String reportTo;
	
	public String getNomenclatureEnglish() {
		return nomenclatureEnglish;
	}
	public void setNomenclatureEnglish(String nomenclatureEnglish) {
		this.nomenclatureEnglish = nomenclatureEnglish;
	}
	public String getNomenclatureLocal() {
		return nomenclatureLocal;
	}
	public void setNomenclatureLocal(String nomenclatureLocal) {
		this.nomenclatureLocal = nomenclatureLocal;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getReportTo() {
		return reportTo;
	}
	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}
// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}

}