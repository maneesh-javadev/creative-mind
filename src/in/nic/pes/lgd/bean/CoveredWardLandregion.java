package in.nic.pes.lgd.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * CoveredWardLandregion Created by Pooja
 */
@Entity
@Table(name = "covered_ward_landregion", schema = "public")
public class CoveredWardLandregion implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6562424915669625036L;
	private int cwvCode;
	private LocalbodyWard localbodyWard;
	private int landRegionCode;
	private char landRegionType;
	private boolean isactive;
	private char coverageType;
	
	private Set<CoveredWardVillageparts> coveredWardVillagepartses = new HashSet<CoveredWardVillageparts>(
			0);

	public CoveredWardLandregion() {
	}

// TODO Remove unused code found by UCDetector
// 	public CoveredWardLandregion(int cwvCode, LocalbodyWard localbodyWard,
// 			int landRegionCode, int landRegionVersion, char landRegionType,
// 			boolean isactive, char coverageType) {
// 		this.cwvCode = cwvCode;
// 		this.localbodyWard = localbodyWard;
// 		this.landRegionCode = landRegionCode;
// 		this.landRegionType = landRegionType;
// 		this.isactive = isactive;
// 		this.coverageType = coverageType;
// 	}

// TODO Remove unused code found by UCDetector
// 	public CoveredWardLandregion(int cwvCode, LocalbodyWard localbodyWard,
// 			int landRegionCode, int landRegionVersion, char landRegionType,
// 			boolean isactive, char coverageType,
// 			Set<CoveredWardVillageparts> coveredWardVillagepartses) {
// 		this.cwvCode = cwvCode;
// 		this.localbodyWard = localbodyWard;
// 		this.landRegionCode = landRegionCode;
// 		this.landRegionType = landRegionType;
// 		this.isactive = isactive;
// 		this.coverageType = coverageType;
// 		this.coveredWardVillagepartses = coveredWardVillagepartses;
// 	}

	@GenericGenerator(name = "generator", strategy = "increment", parameters = {})
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cwv_code", unique = true, nullable = false)
	public int getCwvCode() {
		return this.cwvCode;
	}

	public void setCwvCode(int cwvCode) {
		this.cwvCode = cwvCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ward_code", referencedColumnName = "ward_code", nullable = false),
			@JoinColumn(name = "ward_version", referencedColumnName = "ward_version", nullable = false) })
	public LocalbodyWard getLocalbodyWard() {
		return this.localbodyWard;
	}

	public void setLocalbodyWard(LocalbodyWard localbodyWard) {
		this.localbodyWard = localbodyWard;
	}

	@Column(name = "land_region_lc", nullable = false)
	public int getLandRegionCode() {
		return this.landRegionCode;
	}

	public void setLandRegionCode(int landRegionCode) {
		this.landRegionCode = landRegionCode;
	}

	

	@Column(name = "land_region_type", nullable = false, length = 1)
	public char getLandRegionType() {
		return this.landRegionType;
	}

	public void setLandRegionType(char landRegionType) {
		this.landRegionType = landRegionType;
	}

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Column(name = "coverage_type", nullable = false, length = 1)
	public char getCoverageType() {
		return this.coverageType;
	}

	public void setCoverageType(char coverageType) {
		this.coverageType = coverageType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coveredWardLandregion")
	public Set<CoveredWardVillageparts> getCoveredWardVillagepartses() {
		return this.coveredWardVillagepartses;
	}

	public void setCoveredWardVillagepartses(
			Set<CoveredWardVillageparts> coveredWardVillagepartses) {
		this.coveredWardVillagepartses = coveredWardVillagepartses;
	}

}
