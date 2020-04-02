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
public class DistrictPK implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 5704278472410652432L;
	//@NotNull
    @Column(name = "district_code")
    private int districtCode;
  //@NotNull
    @Column(name = "district_version")
    private int districtVersion;
    
    @Column(name="minor_version")
    private Integer minorVersion;

    public DistrictPK() {
    }

    public DistrictPK(int districtCode, int districtVersion,int minorVersion) {
        this.districtCode = districtCode;
        this.districtVersion = districtVersion;
        this.minorVersion = minorVersion;
    }

    public int getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(int districtCode) {
        this.districtCode = districtCode;
    }

    public int getDistrictVersion() {
        return districtVersion;
    }

    public void setDistrictVersion(int districtVersion) {
        this.districtVersion = districtVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) districtCode;
        hash += (int) districtVersion;
        hash += (int) minorVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistrictPK)) {
            return false;
        }
        DistrictPK other = (DistrictPK) object;
        if (this.districtCode != other.districtCode) {
            return false;
        }
        if (this.districtVersion != other.districtVersion) {
            return false;
        }
        if (this.minorVersion != other.minorVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.DistrictPK[ districtCode=" + districtCode + ", districtVersion=" + districtVersion+ ", minorVersion=" + minorVersion  + " ]";
    }
    
}
