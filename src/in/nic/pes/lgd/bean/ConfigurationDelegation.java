/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author
 */
@Entity
@Table(name = "configuration_delegation")
@NamedQueries({
    @NamedQuery(name = "ConfigurationDelegation.findAll", query = "SELECT c FROM ConfigurationDelegation c")})
public class ConfigurationDelegation { // NO_UCD (unused code)
    //@NotNull
    @Column(name = "id")
    private Integer id;
  //@NotNull
    @Column(name = "delegated_to_role_code")
    private int delegatedToRoleCode;
  //@NotNull
    @Column(name = "delegated_by_role_code")
    private int delegatedByRoleCode;
  //@JoinColumns({
  //    @JoinColumn(name = "state_code", referencedColumnName = "state_code"),
  //    @JoinColumn(name = "state_version", referencedColumnName = "state_version")})
  //@ManyToOne(optional = false)
    private State state;
  //@JoinColumn(name = "operation_code", referencedColumnName = "operation_code")
  //@ManyToOne(optional = false)
    private Operations operationCode;

    public ConfigurationDelegation() {
    }

    public ConfigurationDelegation(Integer id) {
        this.id = id;
    }

    public ConfigurationDelegation(Integer id, int delegatedToRoleCode, int delegatedByRoleCode) {
        this.id = id;
        this.delegatedToRoleCode = delegatedToRoleCode;
        this.delegatedByRoleCode = delegatedByRoleCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDelegatedToRoleCode() {
        return delegatedToRoleCode;
    }

    public void setDelegatedToRoleCode(int delegatedToRoleCode) {
        this.delegatedToRoleCode = delegatedToRoleCode;
    }

    public int getDelegatedByRoleCode() {
        return delegatedByRoleCode;
    }

    public void setDelegatedByRoleCode(int delegatedByRoleCode) {
        this.delegatedByRoleCode = delegatedByRoleCode;
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
        if (!(object instanceof ConfigurationDelegation)) {
            return false;
        }
        ConfigurationDelegation other = (ConfigurationDelegation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.ConfigurationDelegation[ id=" + id + " ]";
    }
    
}
