package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
* CoveredWardVillageparts Created by  
*/
@Entity
@Table(name = "covered_ward_villageparts", schema = "public")
public class CoveredWardVillageparts implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7628748231606648683L;
	private int id;
	private CoveredWardLandregion coveredWardLandregion;
	private VillagePartsBySurveyno villagePartsBySurveyno;
	private boolean isactive;
	private char coverageType;

	public CoveredWardVillageparts() {
	}

	/*public CoveredWardVillageparts(int id,
			CoveredWardLandregion coveredWardLandregion,
			VillagePartsBySurveyno villagePartsBySurveyno, boolean isactive,
			char coverageType) {
		this.id = id;
		this.coveredWardLandregion = coveredWardLandregion;
		this.villagePartsBySurveyno = villagePartsBySurveyno;
		this.isactive = isactive;
		this.coverageType = coverageType;
	}*/

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cwv_code", nullable = false)
	public CoveredWardLandregion getCoveredWardLandregion() {
		return this.coveredWardLandregion;
	}

	public void setCoveredWardLandregion(
			CoveredWardLandregion coveredWardLandregion) {
		this.coveredWardLandregion = coveredWardLandregion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_code", nullable = false)
	public VillagePartsBySurveyno getVillagePartsBySurveyno() {
		return this.villagePartsBySurveyno;
	}

	public void setVillagePartsBySurveyno(
			VillagePartsBySurveyno villagePartsBySurveyno) {
		this.villagePartsBySurveyno = villagePartsBySurveyno;
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

}


