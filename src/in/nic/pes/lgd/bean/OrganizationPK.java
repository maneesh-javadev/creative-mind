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
 * @author Ram
 */
@Embeddable
public class OrganizationPK implements Serializable {
  
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5100792764740754411L;
	private int orgCode;
    private int orgVersion;
    
    public OrganizationPK() {
    }

    public OrganizationPK(int orgCode, int orgVersion) {
        this.orgCode = orgCode;
        this.orgVersion = orgVersion;
    }
    
 
    public OrganizationPK(int orgVersion) { // NO_UCD (use default)
		super();
		this.orgVersion = orgVersion;
	}

	@Column(name = "org_code", nullable = false)
    public int getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(int orgCode) {
        this.orgCode = orgCode;
    }
    
    @Column(name = "org_version", nullable = false)
    public int getOrgVersion() {
        return orgVersion;
    }

    public void setOrgVersion(int orgVersion) {
        this.orgVersion = orgVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orgCode;
        hash += (int) orgVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizationPK)) {
            return false;
        }
        OrganizationPK other = (OrganizationPK) object;
        if (this.orgCode != other.orgCode) {
            return false;
        }
        if (this.orgVersion != other.orgVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.OrganizationPK[ orgCode=" + orgCode + ", orgVersion=" + orgVersion + " ]";
    }
    
}
