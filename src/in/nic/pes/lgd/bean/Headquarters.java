
package in.nic.pes.lgd.bean;

import javax.persistence.Basic;
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
 * Headquarters  
 */
@Entity
@Table(name = "headquarters", schema = "public")
public class Headquarters implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private int headquarterCode;
	private Localbody localbody;	
	private int headquarterVersion;
	//private int regionCode;
	private Integer lblc;
	private char regionType;
	private String headquarterNameEnglish;
	private String headquarterLocalName;
	private String remarks;
	private boolean isactive;
	private Integer lrlc;
	
	/*headquarter_code integer NOT NULL,
	  headquarter_version integer NOT NULL,
	  lrlc integer NOT NULL,
	  region_type character(1) NOT NULL,
	  headquarter_name_english character varying(50) NOT NULL,
	  headquarter_local_name character varying(50),
	  lblc integer,
	  remarks character varying(1000),
	  isactive boolean NOT NULL DEFAULT true,*/

	public Headquarters() {
	}

	
	

// TODO Remove unused code found by UCDetector
// 	public Headquarters(int headquarterCode, int headquarterVersion,
// 			int regionCode, int lblc, char regionType,
// 			String headquarterNameEnglish, boolean isactive) {
// 		this.headquarterCode = headquarterCode;
// 		this.headquarterVersion = headquarterVersion;
// 		//this.regionCode = regionCode;
// 		this.setLblc(lblc);
// 		this.regionType = regionType;
// 		this.headquarterNameEnglish = headquarterNameEnglish;
// 		this.isactive = isactive;
// 	}

// TODO Remove unused code found by UCDetector
// 	public Headquarters(int headquarterCode, Localbody localbody,
// 			int headquarterVersion, int regionCode, int lblc,
// 			char regionType, String headquarterNameEnglish,
// 			String headquarterLocalName, String remarks, boolean isactive) {
// 		this.headquarterCode = headquarterCode;
// 		this.localbody = localbody;
// 		this.headquarterVersion = headquarterVersion;
// 		//this.regionCode = regionCode;
// 		this.setLblc(lblc);
// 		this.regionType = regionType;
// 		this.headquarterNameEnglish = headquarterNameEnglish;
// 		this.headquarterLocalName = headquarterLocalName;
// 		this.remarks = remarks;
// 		this.isactive = isactive;
// 	}
	
	
	
	
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "public.headquarter_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
    @Basic(optional = false)
	@Column(name = "headquarter_code", unique = true, nullable = false)
	public int getHeadquarterCode() {
		return this.headquarterCode;
	}

	public void setHeadquarterCode(int headquarterCode) {
		this.headquarterCode = headquarterCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "lblc", referencedColumnName = "lblc")
			//@JoinColumn(name = "local_body_version", referencedColumnName = "local_body_version") 
			})
	public Localbody getLocalbody() {
		return this.localbody;
	}

	public void setLocalbody(Localbody localbody) {
		this.localbody = localbody;
	}
	

	@Column(name = "headquarter_version", nullable = false)
	public int getHeadquarterVersion() {
		return this.headquarterVersion;
	}

	public void setHeadquarterVersion(int headquarterVersion) {
		this.headquarterVersion = headquarterVersion;
	}

	/*@Column(name = "lrlc", nullable = true)
	public int getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}
*/
	/*@Column(name = "region_version", nullable = false)
	public int getRegionVersion() {
		return this.regionVersion;
	}

	public void setRegionVersion(int regionVersion) {
		this.regionVersion = regionVersion;
	}*/

	@Column(name = "region_type", nullable = false, length = 1)
	public char getRegionType() {
		return this.regionType;
	}

	public void setRegionType(char regionType) {
		this.regionType = regionType;
	}

	@Column(name = "headquarter_name_english", nullable = false, length = 50)
	public String getHeadquarterNameEnglish() {
		return this.headquarterNameEnglish;
	}

	public void setHeadquarterNameEnglish(String headquarterNameEnglish) {
		this.headquarterNameEnglish = headquarterNameEnglish;
	}

	@Column(name = "headquarter_local_name", length = 50)
	public String getHeadquarterLocalName() {
		return this.headquarterLocalName;
	}

	public void setHeadquarterLocalName(String headquarterLocalName) {
		this.headquarterLocalName = headquarterLocalName;
	}

	@Column(name = "remarks", length = 1000)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Column(name = "lblc", nullable = true,insertable=false,updatable=false)
	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}

	@Column(name = "lrlc")
	public Integer getLrlc() {
		return lrlc;
	}

	public void setLrlc(Integer lrlc) {
		this.lrlc = lrlc;
	}


	

	

}
