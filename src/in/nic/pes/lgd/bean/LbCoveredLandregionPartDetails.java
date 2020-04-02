 
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

/**
 * LbCoveredLandregionPartDetails  
 */
@Entity
@Table(name = "lb_covered_landregion_part_details")
public class LbCoveredLandregionPartDetails implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8573992243254492446L;
	private int lbclrpdId;
 	private LbCoveredLandregion lbCoveredLandregion;
	//private int lbCoveredRegionCode;
	private int landRegionCode;
	private int landRegionVersion;
	private char landRegionType;
	private char coverageType;
	private boolean isactive;

	public LbCoveredLandregionPartDetails() {
	}

// TODO Remove unused code found by UCDetector
// 	public LbCoveredLandregionPartDetails(int lbclrpdId,
// 			int landRegionCode,LbCoveredLandregion lbCoveredLandregion,
// 			int landRegionVersion, char landRegionType, char coverageType,
// 			boolean isactive) {
// 		this.lbclrpdId = lbclrpdId;
// 		 this.lbCoveredLandregion = lbCoveredLandregion;
// 		this.landRegionCode = landRegionCode;
// 		this.landRegionVersion = landRegionVersion;
// 		this.landRegionType = landRegionType;
// 		this.coverageType = coverageType;
// 		this.isactive = isactive;
// 	}

	@GenericGenerator(name = "generator", strategy = "increment", parameters = {})
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "lbclrpd_id", unique = true, nullable = false)
	public int getLbclrpdId() {
		return this.lbclrpdId;
	}

	public void setLbclrpdId(int lbclrpdId) {
		this.lbclrpdId = lbclrpdId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lbclr_code", nullable = false)
	public LbCoveredLandregion getLbCoveredLandregion() {
		return this.lbCoveredLandregion;
	}

	public void setLbCoveredLandregion(LbCoveredLandregion lbCoveredLandregion) {
		this.lbCoveredLandregion = lbCoveredLandregion;
	}

	
	
	@Column(name = "land_region_code", nullable = false)
	public int getLandRegionCode() {
		return this.landRegionCode;
	}
	

	public void setLandRegionCode(int landRegionCode) {
		this.landRegionCode = landRegionCode;
	}

	@Column(name = "land_region_version", nullable = false)
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

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

}
