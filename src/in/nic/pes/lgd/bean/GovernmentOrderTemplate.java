/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author
 */
@Entity
@Table(name = "government_order_template")
public class GovernmentOrderTemplate {
    @GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="govtordertemplateseq")})
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "template_code")
    private Integer templateCode;
    @Column(name = "template_name_english")
    private String templateNameEnglish;
    @Column(name = "template_regional")
    private String templateRegional;
    @Column(name = "template_description")
    private String templateDescription;
  /*@Column(name = "role_code")
    private long roleCode;*/
    @Column(name = "lastupdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupdated;
    @Column(name = "lastupdatedby")
    private long lastupdatedby;
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Column(name = "createdby")
    private long createdby;
    @Column(name = "isactive",nullable=false)
    private boolean isactive;
    @Column(name = "slc")
    private int slc;
    
 	@Column(name = "template_type",nullable=false)
    private char templateType;


	/*@JoinColumns({
      @JoinColumn(name = "state_code", referencedColumnName = "state_code"),
      @JoinColumn(name = "state_version", referencedColumnName = "state_version")})
    @ManyToOne
    private State state;*/
    
    @JoinColumn(name = "operation_code", referencedColumnName = "operation_code")
    @ManyToOne
    private Operations operations;
    
/*    @OneToMany(mappedBy = "templateCode")
    private Collection<GovernmentOrder> governmentOrderCollection;*/


	public GovernmentOrderTemplate() {
    }

// TODO Remove unused code found by UCDetector
//     public GovernmentOrderTemplate(Integer templateCode,
// 			String templateNameEnglish, String templateRegional,
// 			String templateDescription, Date lastupdated,
// 			long lastupdatedby, Date createdon, long createdby, 
// 			Operations operations) {
// 		super();
// 		this.templateCode = templateCode;
// 		this.templateNameEnglish = templateNameEnglish;
// 		this.templateRegional = templateRegional;
// 		this.templateDescription = templateDescription;
// 		//this.roleCode = roleCode;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		//this.state = state;
// 		this.operations = operations;
// 	}

// TODO Remove unused code found by UCDetector
//     public GovernmentOrderTemplate(Integer templateCode,
// 			String templateNameEnglish, String templateRegional,
// 			String templateDescription, Date lastupdated,
// 			long lastupdatedby, Date createdon, long createdby,
// 			boolean isactive, Operations operations) {
// 		super();
// 		this.templateCode = templateCode;
// 		this.templateNameEnglish = templateNameEnglish;
// 		this.templateRegional = templateRegional;
// 		this.templateDescription = templateDescription;
// 		//this.roleCode = roleCode;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.isactive = isactive;
// 		//this.state = state;
// 		this.operations = operations;
// 	}
// TODO Remove unused code found by UCDetector
// 	public GovernmentOrderTemplate(Integer templateCode) {
//         this.templateCode = templateCode;
//     }

    public Integer getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateNameEnglish() {
        return templateNameEnglish;
    }

    public void setTemplateNameEnglish(String templateNameEnglish) {
        this.templateNameEnglish = templateNameEnglish;
    }

    public String getTemplateRegional() {
        return templateRegional;
    }

    public void setTemplateRegional(String templateRegional) {
        this.templateRegional = templateRegional;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    public long getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(long lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(long createdby) {
        this.createdby = createdby;
    }

   /* public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }*/

/*    public Collection<GovernmentOrder> getGovernmentOrderCollection() {
        return governmentOrderCollection;
    }

    public void setGovernmentOrderCollection(Collection<GovernmentOrder> governmentOrderCollection) {
        this.governmentOrderCollection = governmentOrderCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (templateCode != null ? templateCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GovernmentOrderTemplate)) {
            return false;
        }
        GovernmentOrderTemplate other = (GovernmentOrderTemplate) object;
        if ((this.templateCode == null && other.templateCode != null) || (this.templateCode != null && !this.templateCode.equals(other.templateCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.GovernmentOrderTemplate[ templateCode=" + templateCode + " ]";
    }

	/**
	 * @return the roleCode
	 */
/*	public long getRoleCode() {
		return roleCode;
	}
*/
	/**
	 * @param roleCode the roleCode to set
	 */
	/*public void setRoleCode(long roleCode) {
		this.roleCode = roleCode;
	}*/

	/**
	 * @return the operations
	 */
	public Operations getOperations() {
		return operations;
	}

	/**
	 * @param operations the operations to set
	 */
	public void setOperations(Operations operations) {
		this.operations = operations;
	}

	/**
	 * @return the isactive
	 */
	public boolean isIsactive() {
		return isactive;
	}

	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	public int getSlc() {
			return slc;
	}

	public void setSlc(int slc) {
			this.slc = slc;
	}

	/**
	 * @return the templateType
	 */
	public char getTemplateType() {
		return templateType;
	}

	/**
	 * @param templateType the templateType to set
	 */
	public void setTemplateType(char templateType) {
		this.templateType = templateType;
	}


}
