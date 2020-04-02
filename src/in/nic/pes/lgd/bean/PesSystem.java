/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author 
 */
@Entity
@Table(name = "pes_system")
public class PesSystem { // NO_UCD (use default)
  //private static final long serialVersionUID = 1L;
  //@NotNull
    @Column(name = "system_code")
    private Integer systemCode;
  //@NotNull
    @Column(name = "system_name")
    private String systemName;
  //@NotNull
    @Column(name = "url")
    private String url;
  //NotNull
    @Column(name = "user_id")
    private long userId;
  //NotNull
    @Column(name = "password")
    private String password;
  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "systemCode")
    private Collection<GovernmentOrderStatus> governmentOrderStatusCollection;

    public PesSystem() {
    }

// TODO Remove unused code found by UCDetector
//     public PesSystem(Integer systemCode) {
//         this.systemCode = systemCode;
//     }

// TODO Remove unused code found by UCDetector
//     public PesSystem(Integer systemCode, String systemName, String url, long userId, String password) {
//         this.systemCode = systemCode;
//         this.systemName = systemName;
//         this.url = url;
//         this.userId = userId;
//         this.password = password;
//     }

    public Integer getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(Integer systemCode) {
        this.systemCode = systemCode;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<GovernmentOrderStatus> getGovernmentOrderStatusCollection() {
        return governmentOrderStatusCollection;
    }

    public void setGovernmentOrderStatusCollection(Collection<GovernmentOrderStatus> governmentOrderStatusCollection) {
        this.governmentOrderStatusCollection = governmentOrderStatusCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (systemCode != null ? systemCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PesSystem)) {
            return false;
        }
        PesSystem other = (PesSystem) object;
        if ((this.systemCode == null && other.systemCode != null) || (this.systemCode != null && !this.systemCode.equals(other.systemCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.PesSystem[ systemCode=" + systemCode + " ]";
    }
    
}
