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
@Table(name = "covered_constituency_body")
public class CoveredConstituencyBody { // NO_UCD (unused code)
  //private static final long serialVersionUID = 1L;
  //@NotNull
    @Column(name = "id")
    private Integer id;
  //@NotNull
    @Column(name = "coverage_body_code")
    private int coverageBodyCode;
  //@JoinColumn(name = "cc_code", referencedColumnName = "cc_code")
  //@ManyToOne(optional = false)
    private ConstituencyCoverage ccCode;

    public CoveredConstituencyBody() {
    }

    public CoveredConstituencyBody(Integer id) {
        this.id = id;
    }

    public CoveredConstituencyBody(Integer id, int coverageBodyCode) {
        this.id = id;
        this.coverageBodyCode = coverageBodyCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCoverageBodyCode() {
        return coverageBodyCode;
    }

    public void setCoverageBodyCode(int coverageBodyCode) {
        this.coverageBodyCode = coverageBodyCode;
    }

    public ConstituencyCoverage getCcCode() {
        return ccCode;
    }

    public void setCcCode(ConstituencyCoverage ccCode) {
        this.ccCode = ccCode;
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
        if (!(object instanceof CoveredConstituencyBody)) {
            return false;
        }
        CoveredConstituencyBody other = (CoveredConstituencyBody) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.CoveredConstituencyBody[ id=" + id + " ]";
    }
    
}
