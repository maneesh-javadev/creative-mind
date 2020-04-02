/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author
 */
@Entity
@Table(name = "covered_lb_village_parts")
public class CoveredLbVillageParts { // NO_UCD (use default)
    //@NotNull
    @Column(name = "id")
    private Integer id;
  //@JoinColumn(name = "survey_code", referencedColumnName = "survey_code")
  //@ManyToOne(optional = false)
    private VillagePartsBySurveyno1 surveyCode;
  //@JoinColumn(name = "lbclr_code", referencedColumnName = "lbclr_code")
  //@ManyToOne(optional = false)
    private LbCoveredLandregion lbclrCode;

    public CoveredLbVillageParts() {
    }

// TODO Remove unused code found by UCDetector
//     public CoveredLbVillageParts(Integer id) {
//         this.id = id;
//     }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VillagePartsBySurveyno1 getSurveyCode() {
        return surveyCode;
    }

    public void setSurveyCode(VillagePartsBySurveyno1 surveyCode) {
        this.surveyCode = surveyCode;
    }

    public LbCoveredLandregion getLbclrCode() {
        return lbclrCode;
    }

    public void setLbclrCode(LbCoveredLandregion lbclrCode) {
        this.lbclrCode = lbclrCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoveredLbVillageParts)) {
            return false;
        }
        CoveredLbVillageParts other = (CoveredLbVillageParts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.CoveredLbVillageParts[ id=" + id + " ]";
    }
    
}
