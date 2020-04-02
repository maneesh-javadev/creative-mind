/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Designation 
 */
@Entity
@Table(name = "designation", schema = "public")
public class Designation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@GenericGenerator
	(name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="seqdesignation")})
    @Id
    @GeneratedValue(generator = "sequence")
	@Column(name = "designation_code")
	private int designationCode;
	//private LocalBodyTiersSetup localBodyTiersSetup;
	private String designationName;
	private boolean islocalbody;
	private String designationNameLocal;
	private boolean istopdesignation;
	private boolean ismultiple;
	private int tierSetupCode;
	//private int tierSetupCode;
	
	
	public Designation() {
	}

// TODO Remove unused code found by UCDetector
// 	public Designation(
// 			int designationCode, int tierSetupCode, String designationName,
// 			boolean islocalbody, String designationNameLocal,
// 			boolean istopdesignation, boolean ismultiple) {
// 		this.designationCode=designationCode;
// 		this.tierSetupCode=tierSetupCode;
// 		this.designationName = designationName;
// 		this.islocalbody = islocalbody;
// 		this.designationNameLocal = designationNameLocal;
// 		this.istopdesignation = istopdesignation;
// 		this.ismultiple = ismultiple;
// 	}

	@Id
	@Column(name = "designation_code", unique = true, nullable = false)
	public int getDesignationCode() {
		return this.designationCode;
	}

	public void setDesignationCode(int designationCode) {
		this.designationCode = designationCode;
	}

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "tier_setup_code", referencedColumnName = "tier_setup_code", nullable = false),
			@JoinColumn(name = "tier_version_version", referencedColumnName = "tier_setup_version", nullable = false) })
	public LocalBodyTiersSetup getLocalBodyTiersSetup() {
		return this.localBodyTiersSetup;
	}

	public void setLocalBodyTiersSetup(LocalBodyTiersSetup localBodyTiersSetup) {
		this.localBodyTiersSetup = localBodyTiersSetup;
	}*/

	@Column(name = "designation_name", nullable = false, length = 50)
	public String getDesignationName() {
		return this.designationName;
	}

	public void setDesignationName(String designationName) {
		
		this.designationName = designationName;
	}

	@Column(name = "islocalbody", nullable = false)
	public boolean isIslocalbody() {
		return this.islocalbody;
	}

	public void setIslocalbody(boolean islocalbody) {
		this.islocalbody = islocalbody;
	}

	@Column(name = "designation_name_local", length = 60)
	public String getDesignationNameLocal() {
		return this.designationNameLocal;
	}

	public void setDesignationNameLocal(String designationNameLocal) {
		/*if(designationNameLocal==null)
		{
			designationNameLocal="";
		}*/
		this.designationNameLocal = designationNameLocal;
	}

	@Column(name = "istopdesignation", nullable = false)
	public boolean isIstopdesignation() {
		return this.istopdesignation;
	}

	public void setIstopdesignation(boolean istopdesignation) {
		this.istopdesignation = istopdesignation;
	}

	@Column(name = "ismultiple", nullable = false)
	public boolean isIsmultiple() {
		return this.ismultiple;
	}

	public void setIsmultiple(boolean ismultiple) {
		this.ismultiple = ismultiple;
	}
	
	@Column(name = "tier_setup_code")
	public int getTierSetupCode() {
		return tierSetupCode;
	}

	public void setTierSetupCode(int tierSetupCode) {
		this.tierSetupCode = tierSetupCode;
	}

}
