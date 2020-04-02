/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 
 */
@Entity
@Table(name = "operations")
public class Operations implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "operation_code")
    private Integer operationCode;
    
    @Column(name = "operation_name")
    private String operationName;
    
    @Column(name = "isactive")
    private boolean isactive;
    
    @Column(name="category")
    private Character category;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "operations")
    private Set<GovernmentOrderTemplate> governmentOrderTemplateCollection= new HashSet<GovernmentOrderTemplate>(0);
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "operations")
    private Set<OperationsVariables> operationsVariablesCollection= new HashSet<OperationsVariables>(0);
	


    public Operations() {
    }

// TODO Remove unused code found by UCDetector
//     public Operations(Integer operationCode) {
//         this.operationCode = operationCode;
//     }

// TODO Remove unused code found by UCDetector
//     public Operations(Integer operationCode, String operationName, boolean isactive,Character category) {
//         this.operationCode = operationCode;
//         this.operationName = operationName;
//         this.isactive = isactive;
//         this.category=category;
//     }

    public Integer getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(Integer operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operationCode != null ? operationCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operations)) {
            return false;
        }
        Operations other = (Operations) object;
        if ((this.operationCode == null && other.operationCode != null) || (this.operationCode != null && !this.operationCode.equals(other.operationCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.Operations[ operationCode=" + operationCode + " ]";
    }

	/**
	 * @return the governmentOrderTemplateCollection
	 */
	public Set<GovernmentOrderTemplate> getGovernmentOrderTemplateCollection() {
		return governmentOrderTemplateCollection;
	}

	/**
	 * @param governmentOrderTemplateCollection the governmentOrderTemplateCollection to set
	 */
	public void setGovernmentOrderTemplateCollection(
			Set<GovernmentOrderTemplate> governmentOrderTemplateCollection) {
		this.governmentOrderTemplateCollection = governmentOrderTemplateCollection;
	}

	public Set<OperationsVariables> getOperationsVariablesCollection() {
		return operationsVariablesCollection;
	}

	public void setOperationsVariablesCollection(
			Set<OperationsVariables> operationsVariablesCollection) {
		this.operationsVariablesCollection = operationsVariablesCollection;
	}

	public Character getCategory() {
		return category;
	}

	public void setCategory(Character category) {
		this.category = category;
	}

    
}
