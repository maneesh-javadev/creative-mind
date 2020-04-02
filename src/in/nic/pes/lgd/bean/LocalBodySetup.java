/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HCL
 */
@Entity
@Table(name = "local_body_setup")//, catalog = "lgd_db", schema = "public")
public class LocalBodySetup implements Serializable {

	private static final long serialVersionUID = 1L;
	//@EmbeddedId
    private LocalBodySetupPK localBodySetupPK;
    private Date effectiveDate;
    private boolean isactive;
    private Integer slc;
 	private Integer localBodySetupCode;
    private Integer localBodySetupVersion;
    private Date createdOn;
    private Long createdBy;
    private Date lastUpdated;
    private Long lastUpdatedBy;
    private Set<LocalBodyTiersSetup> localBodyTiersSetups = new HashSet<LocalBodyTiersSetup>(0);
    
    public LocalBodySetup() {}
    
// TODO Remove unused code found by UCDetector
//     public LocalBodySetup(LocalBodySetupPK localBodySetupPK,
// 			Date effectiveDate, boolean isactive, Date createdOn, Long createdBy, Integer slc) {
// 		super();
// 		this.localBodySetupPK = localBodySetupPK;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.createdOn = createdOn;
// 		this.createdBy = createdBy;
// 		this.slc=slc;
// 	}

// TODO Remove unused code found by UCDetector
// 	public LocalBodySetup(LocalBodySetupPK localBodySetupPK, Date effectiveDate,
// 			boolean isactive,  Date createdOn, Long createdBy,
// 			Date lastUpdated, Long lastUpdatedBy,
// 			Set<LocalBodyTiersSetup> localBodyTiersSetups,Integer slc) {
// 		super();
// 		this.localBodySetupPK = localBodySetupPK;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.createdOn = createdOn;
// 		this.createdBy = createdBy;
// 		this.lastUpdated = lastUpdated;
// 		this.lastUpdatedBy = lastUpdatedBy;
// 		this.localBodyTiersSetups = localBodyTiersSetups;
// 		this.slc=slc;
// 	}

	/**
	 * @return the localBodyTiersSetups
	 */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "localBodySetup")
	public Set<LocalBodyTiersSetup> getLocalBodyTiersSetups() {
		return localBodyTiersSetups;
	}
	/**
	 * @param localBodyTiersSetups the localBodyTiersSetups to set
	 */
	public void setLocalBodyTiersSetups(Set<LocalBodyTiersSetup> localBodyTiersSetups) {
		this.localBodyTiersSetups = localBodyTiersSetups;
	}
	
// TODO Remove unused code found by UCDetector
// 	public LocalBodySetup(Integer localBodySetupCode, Integer localBodySetupVersion) {
// 		this.localBodySetupPK = new LocalBodySetupPK(localBodySetupCode, localBodySetupVersion);
// 	}
// TODO Remove unused code found by UCDetector
//     public LocalBodySetup(LocalBodySetupPK localBodySetupPK) {
// 		//super();
// 		this.localBodySetupPK = localBodySetupPK;
// 	}
    /**
	 * @return the localBodySetupPK
	 */
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "localBodySetupCode", column = @Column(name = "local_body_setup_code", nullable = false)),
			@AttributeOverride(name = "localBodySetupVersion", column = @Column(name = "local_body_setup_version", nullable = false)) })
	public LocalBodySetupPK getLocalBodySetupPK() {
		return localBodySetupPK;
	}



	/**
	 * @param localBodySetupPK the localBodySetupPK to set
	 */
	public void setLocalBodySetupPK(LocalBodySetupPK localBodySetupPK) {
		this.localBodySetupPK = localBodySetupPK;
	}
   
    @Basic(optional = false)
    @Column(name = "effective_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Basic(optional = false)
    @Column(name = "isactive", nullable = false)
    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    // @Column(name = "slc", insertable=false,updatable=false)
    @Column(name = "slc")
	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}
    
	@Column(name = "local_body_setup_code", insertable=false,updatable=false)
	public Integer getLocalBodySetupCode() {
		return localBodySetupCode;
	}

	public void setLocalBodySetupCode(Integer localBodySetupCode) {
		this.localBodySetupCode = localBodySetupCode;
	}

	@Column(name = "local_body_setup_version", insertable=false,updatable=false)
	public Integer getLocalBodySetupVersion() {
		return localBodySetupVersion;
	}

	public void setLocalBodySetupVersion(Integer localBodySetupVersion) {
		this.localBodySetupVersion = localBodySetupVersion;
	}

// TODO Remove unused code found by UCDetector
// 	public static Long getSerialversionuid() {
// 		return serialVersionUID;
// 	}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdon", nullable = false, length = 35)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

    @Column(name="createdby")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="lastupdated")
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


    @Column(name="lastupdatedby")
	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
}
