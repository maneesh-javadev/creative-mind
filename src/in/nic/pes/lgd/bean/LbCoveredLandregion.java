/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 * @author
 */
@Entity
@Table(name = "lb_covered_landregion")
public class LbCoveredLandregion implements Serializable {
	private static final long serialVersionUID = 1L;

	private int lbclrCode;
	private int lbCoveredRegionCode;
	private int landRegionCode;
	private int landRegionVersion;
	private char landRegionType;
	private char coverageType;
	private boolean ismainregion;
	private boolean isactive;

	// private Set<LbCoveredLandregionPartDetails>
	// lbCoveredLandregionPartDetailses = new
	// HashSet<LbCoveredLandregionPartDetails>(0);
	// private int lrlcversion;

	/*
	 * private Set<CoveredLbVillageParts> coveredLbVillagePartses = new
	 * HashSet<CoveredLbVillageParts>( 0);
	 */
	public LbCoveredLandregion() {
	}

	// TODO Remove unused code found by UCDetector
	// public LbCoveredLandregion(int lbclrCode, int lbCoveredRegionCode,
	// int landRegionCode, int landRegionVersion, char landRegionType,
	// char coverageType, boolean ismainregion, boolean isactive) {
	// this.lbclrCode = lbclrCode;
	// this.lbCoveredRegionCode = lbCoveredRegionCode;
	// this.landRegionCode = landRegionCode;
	// this.landRegionVersion = landRegionVersion;
	// this.landRegionType = landRegionType;
	// this.coverageType = coverageType;
	// this.ismainregion = ismainregion;
	// this.isactive = isactive;
	// //this.lrlcversion=lrlcversion;
	// }

	// TODO Remove unused code found by UCDetector
	// public LbCoveredLandregion(
	// int lbclrCode,
	// int lbCoveredRegionCode,
	// int landRegionCode,
	// int landRegionVersion,
	// char landRegionType,
	// char coverageType,
	// boolean ismainregion,
	// boolean isactive,
	// //Set<LbCoveredLandregionPartDetails>
	// lbCoveredLandregionPartDetailses,int lrlcversion,
	// Set<CoveredLbVillageParts> coveredLbVillagePartses) {
	// this.lbclrCode = lbclrCode;
	// this.lbCoveredRegionCode = lbCoveredRegionCode;
	// this.landRegionCode = landRegionCode;
	// this.landRegionVersion = landRegionVersion;
	// this.landRegionType = landRegionType;
	// this.coverageType = coverageType;
	// this.ismainregion = ismainregion;
	// this.isactive = isactive;
	// //this.lbCoveredLandregionPartDetailses =
	// lbCoveredLandregionPartDetailses;
	// //this.lrlcversion=lrlcversion;
	// // this.coveredLbVillagePartses = coveredLbVillagePartses;
	// }

	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "lbcoveredlandregion_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	/*
	 * @GenericGenerator(name = "generator", strategy = "increment", parameters
	 * = {})
	 * 
	 * @Id
	 * 
	 * @GeneratedValue(generator = "generator")
	 */
	@Column(name = "lbclr_code", unique = true, nullable = false)
	public int getLbclrCode() {
		return this.lbclrCode;
	}

	public void setLbclrCode(int lbclrCode) {
		this.lbclrCode = lbclrCode;
	}

	@Column(name = "lb_covered_region_code", nullable = false)
	public int getLbCoveredRegionCode() {
		return this.lbCoveredRegionCode;
	}

	public void setLbCoveredRegionCode(int lbCoveredRegionCode) {
		this.lbCoveredRegionCode = lbCoveredRegionCode;
	}

	@Column(name = "lrlc", nullable = false)
	public int getLandRegionCode() {
		return this.landRegionCode;
	}

	public void setLandRegionCode(int landRegionCode) {
		this.landRegionCode = landRegionCode;
	}

	@Column(name = "lrlc_version", nullable = false)
	public int getLandRegionVersion() {
		return this.landRegionVersion;
	}

	public void setLandRegionVersion(int landRegionVersion) {
		this.landRegionVersion = landRegionVersion;
	}

	@Column(name = "land_region_type", nullable = false, length = 1)
	public char getLandRegionType() {
		return this.landRegionType;
	}

	public void setLandRegionType(char landRegionType) {
		this.landRegionType = landRegionType;
	}

	@Column(name = "coverage_type", nullable = false, length = 1)
	public char getCoverageType() {
		return this.coverageType;
	}

	public void setCoverageType(char coverageType) {
		this.coverageType = coverageType;
	}

	@Column(name = "ismainregion", nullable = false)
	public boolean isIsmainregion() {
		return this.ismainregion;
	}

	public void setIsmainregion(boolean ismainregion) {
		this.ismainregion = ismainregion;
	}

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "lbCoveredLandregion")
	 * public Set<LbCoveredLandregionPartDetails>
	 * getLbCoveredLandregionPartDetailses() { return
	 * this.lbCoveredLandregionPartDetailses; }
	 * 
	 * public void setLbCoveredLandregionPartDetailses(
	 * Set<LbCoveredLandregionPartDetails> lbCoveredLandregionPartDetailses) {
	 * this.lbCoveredLandregionPartDetailses = lbCoveredLandregionPartDetailses;
	 * }
	 */

	/*
	 * public int getLrlcversion() { return lrlcversion; }
	 * 
	 * @Column(name = "lrlc_version", nullable = false) public void
	 * setLrlcversion(int lrlcversion) { this.lrlcversion = lrlcversion; }
	 */

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "lbCoveredLandregion")
	 * public Set<CoveredLbVillageParts> getCoveredLbVillagePartses() { return
	 * this.coveredLbVillagePartses; }
	 * 
	 * public void setCoveredLbVillagePartses( Set<CoveredLbVillageParts>
	 * coveredLbVillagePartses) { this.coveredLbVillagePartses =
	 * coveredLbVillagePartses; }
	 */

}
