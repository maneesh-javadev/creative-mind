/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author
 */
@Embeddable
public class NomenclatureSubdistrictPK implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 8597677585824070708L;
	//@NotNull
    @Column(name = "nsd_code")
    private int nsdCode;
  //@NotNull
    @Column(name = "nsd_version")
    private int nsdVersion;

    public NomenclatureSubdistrictPK() {
    }

    public NomenclatureSubdistrictPK(int nsdCode, int nsdVersion) {
        this.nsdCode = nsdCode;
        this.nsdVersion = nsdVersion;
    }

    public int getNsdCode() {
        return nsdCode;
    }

    public void setNsdCode(int nsdCode) {
        this.nsdCode = nsdCode;
    }

    public int getNsdVersion() {
        return nsdVersion;
    }

    public void setNsdVersion(int nsdVersion) {
        this.nsdVersion = nsdVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nsdCode;
        hash += (int) nsdVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NomenclatureSubdistrictPK)) {
            return false;
        }
        NomenclatureSubdistrictPK other = (NomenclatureSubdistrictPK) object;
        if (this.nsdCode != other.nsdCode) {
            return false;
        }
        if (this.nsdVersion != other.nsdVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.NomenclatureSubdistrictPK[ nsdCode=" + nsdCode + ", nsdVersion=" + nsdVersion + " ]";
    }
    
}
