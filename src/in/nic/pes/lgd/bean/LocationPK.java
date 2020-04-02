/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Column;

/**
 *
 * @author
 */
//@Embeddable
public class LocationPK { // NO_UCD (use default)
  //@NotNull
    @Column(name = "location_code")
    private int locationCode;
  //@NotNull
    @Column(name = "location_version")
    private int locationVersion;
    public LocationPK() {
    }

    public LocationPK(int locationCode, int locationVersion) { // NO_UCD (use default)
        this.locationCode = locationCode;
        this.locationVersion = locationVersion;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }

    public int getLocationVersion() {
        return locationVersion;
    }

    public void setLocationVersion(int locationVersion) {
        this.locationVersion = locationVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) locationCode;
        hash += (int) locationVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationPK)) {
            return false;
        }
        LocationPK other = (LocationPK) object;
        if (this.locationCode != other.locationCode) {
            return false;
        }
        if (this.locationVersion != other.locationVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.LocationPK[ locationCode=" + locationCode + ", locationVersion=" + locationVersion + " ]";
    }
    
}
