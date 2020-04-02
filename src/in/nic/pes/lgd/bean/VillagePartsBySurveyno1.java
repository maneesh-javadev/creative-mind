/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author
 */
@Entity
@Table(name = "village_parts_by_surveyno")
public class VillagePartsBySurveyno1 { // NO_UCD (use default)
  //private static final long serialVersionUID = 1L;
  //@NotNull
    @Column(name = "survey_code")
    private Integer surveyCode;
  //@NotNull
  @Column(name = "survey_number")
    private String surveyNumber;
  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyCode")
/*    private Collection<CoveredWardVillageparts> coveredWardVillagepartsCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyCode")
   private Collection<CoveredLbVillageParts> coveredLbVillagePartsCollection;*/
 /* @JoinColumns({
      @JoinColumn(name = "village_code", referencedColumnName = "village_code"),
     @JoinColumn(name = "village_version", referencedColumnName = "village_version")})
  @ManyToOne(optional = false)
    private Village village;*/

  @Column(name = "village_code")
  private int villageCode;
  @Basic(optional = false)
//@NotNull
  @Column(name = "village_version")
  private int villageVersion;
  
    public int getVillageCode() {
	return villageCode;
    }

	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
	}
	
	public int getVillageVersion() {
		return villageVersion;
	}
	
	public void setVillageVersion(int villageVersion) {
		this.villageVersion = villageVersion;
	}

	public VillagePartsBySurveyno1() {
    }

// TODO Remove unused code found by UCDetector
//     public VillagePartsBySurveyno1(Integer surveyCode) {
//         this.surveyCode = surveyCode;
//     }

// TODO Remove unused code found by UCDetector
//     public VillagePartsBySurveyno1(Integer surveyCode, String surveyNumber) {
//         this.surveyCode = surveyCode;
//         this.surveyNumber = surveyNumber;
//     }

    public Integer getSurveyCode() {
        return surveyCode;
    }

    public void setSurveyCode(Integer surveyCode) {
        this.surveyCode = surveyCode;
    }

    public String getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

  /*  public Collection<CoveredWardVillageparts> getCoveredWardVillagepartsCollection() {
        return coveredWardVillagepartsCollection;
    }

    public void setCoveredWardVillagepartsCollection(Collection<CoveredWardVillageparts> coveredWardVillagepartsCollection) {
        this.coveredWardVillagepartsCollection = coveredWardVillagepartsCollection;
    }

    public Collection<CoveredLbVillageParts> getCoveredLbVillagePartsCollection() {
        return coveredLbVillagePartsCollection;
    }

    public void setCoveredLbVillagePartsCollection(Collection<CoveredLbVillageParts> coveredLbVillagePartsCollection) {
        this.coveredLbVillagePartsCollection = coveredLbVillagePartsCollection;
    }*/
/*
    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }
*/
       
}
