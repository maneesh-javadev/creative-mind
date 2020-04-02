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
@Table(name = "configuration_map")
public class ConfigurationMap { // NO_UCD (unused code)
  //private static final long serialVersionUID = 1L;
  //@NotNull
    @Column(name = "id")
    private Integer id;
  //@NotNull
    @Column(name = "ismapupload")
    private boolean ismapupload;
    @Column(name = "base_url")
    private String baseUrl;
  //@JoinColumns({
  //    @JoinColumn(name = "state_code", referencedColumnName = "state_code"),
  //    @JoinColumn(name = "state_version", referencedColumnName = "state_version")})
  //@ManyToOne(optional = false)
    private State state;
  //@JoinColumn(name = "role_code", referencedColumnName = "role_code")
  //@ManyToOne(optional = false)
//    private Roles roleCode;
  //@JoinColumn(name = "operation_code", referencedColumnName = "operation_code")
  //@ManyToOne(optional = false)
    private Operations operationCode;

    public ConfigurationMap() {
    }

    public ConfigurationMap(Integer id) {
        this.id = id;
    }

    public ConfigurationMap(Integer id, boolean ismapupload) {
        this.id = id;
        this.ismapupload = ismapupload;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getIsmapupload() {
        return ismapupload;
    }

    public void setIsmapupload(boolean ismapupload) {
        this.ismapupload = ismapupload;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }



    public Operations getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(Operations operationCode) {
        this.operationCode = operationCode;
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
        if (!(object instanceof ConfigurationMap)) {
            return false;
        }
        ConfigurationMap other = (ConfigurationMap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.ConfigurationMap[ id=" + id + " ]";
    }
    
}
