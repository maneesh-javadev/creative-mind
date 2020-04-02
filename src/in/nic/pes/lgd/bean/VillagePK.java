/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author
 */
@Embeddable
public class VillagePK implements Serializable{
  //@NotNull
    @Column(name = "village_code")
    private int villageCode;
    @Basic(optional = false)
  //@NotNull
    @Column(name = "village_version")
    private int villageVersion;
    
    @Column(name="minor_version")
	private Integer minorVersion;

    public VillagePK() {
    }

    public VillagePK(int villageCode, int villageVersion,int minorVersion) {
        this.villageCode = villageCode;
        this.villageVersion = villageVersion;
        this.villageVersion = minorVersion;
    }

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
    
    

    public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (int) villageCode;
        hash += (int) villageVersion;
        hash += (int) minorVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VillagePK)) {
            return false;
        }
        VillagePK other = (VillagePK) object;
        if (this.villageCode != other.villageCode) {
            return false;
        }
        if (this.villageVersion != other.villageVersion) {
            return false;
        }
        
        if (this.minorVersion != other.minorVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.VillagePK[ villageCode=" + villageCode + ", villageVersion=" + villageVersion+ ", minorVersion=" + minorVersion + " ]";
    }
    
}
