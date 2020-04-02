/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 
 */
@Entity
@Table(name = "local_body_subtype")
public class LocalBodySubtype implements Serializable {
   

	private static final long serialVersionUID = 1L;

    private Integer localBodySubtypeCode;
    
    private String subtypeNameEng;

    private String subtypeNameLocal;

    private Integer tierSetupCode;

    private Date lastupdated;

    private long lastupdatedby;

    private Date createdon;

    private long createdby;
    
    private boolean isactive;
    
    private LocalBodyTiersSetup localBodyTiersSetup;
    
    //  private LocalBodyType localBodyType;
    
	/**
	 * @return the localBodyTiersSetup
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tier_setup_code", referencedColumnName = "tier_setup_code", nullable = false)
	public LocalBodyTiersSetup getLocalBodyTiersSetup() {
		return localBodyTiersSetup;
	}
	/**
	 * @param localBodyTiersSetup the localBodyTiersSetup to set
	 */
	public void setLocalBodyTiersSetup(LocalBodyTiersSetup localBodyTiersSetup) {
		this.localBodyTiersSetup = localBodyTiersSetup;
	}
    




    @Basic(optional = false)
    @Column(name = "tier_setup_code", nullable = false,insertable=false,updatable=false)
	public Integer getTierSetupCode() {
		return tierSetupCode;
	}

	public void setTierSetupCode(Integer tierSetupCode) {
		this.tierSetupCode = tierSetupCode;
	}
   
    public LocalBodySubtype() {
    }

// TODO Remove unused code found by UCDetector
//     public LocalBodySubtype(Integer localBodySubtypeCode) {
//         this.localBodySubtypeCode = localBodySubtypeCode;
//     }

// TODO Remove unused code found by UCDetector
//     public LocalBodySubtype(Integer localBodySubtypeCode, String subtypeNameEng, Date lastupdated, long lastupdatedby, Date createdon, long createdby, boolean isactive) {
//         this.localBodySubtypeCode = localBodySubtypeCode;
//         this.subtypeNameEng = subtypeNameEng;
//         this.lastupdated = lastupdated;
//         this.lastupdatedby = lastupdatedby;
//         this.createdon = createdon;
//         this.createdby = createdby;
//         this.isactive = isactive;
//     }

// TODO Remove unused code found by UCDetector
//     public LocalBodySubtype(Integer localBodySubtypeCode,
// 			String subtypeNameEng, String subtypeNameLocal,
// 			Integer tierSetupCode, Date lastupdated, long lastupdatedby,
// 			Date createdon, long createdby, boolean isactive,
// 			LocalBodyTiersSetup localBodyTiersSetup) {
// 		super();
// 		this.localBodySubtypeCode = localBodySubtypeCode;
// 		this.subtypeNameEng = subtypeNameEng;
// 		this.subtypeNameLocal = subtypeNameLocal;
// 		this.tierSetupCode = tierSetupCode;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.isactive = isactive;
// 		this.setLocalBodyTiersSetup(localBodyTiersSetup);
// 	}
    
// TODO Remove unused code found by UCDetector
//     public LocalBodySubtype(Integer localBodySubtypeCode,
// 			String subtypeNameEng, String subtypeNameLocal,
// 			Integer tierSetupCode, Date lastupdated, long lastupdatedby,
// 			Date createdon, long createdby, boolean isactive) {
// 		super();
// 		this.localBodySubtypeCode = localBodySubtypeCode;
// 		this.subtypeNameEng = subtypeNameEng;
// 		this.subtypeNameLocal = subtypeNameLocal;
// 		this.tierSetupCode = tierSetupCode;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.isactive = isactive;
// 		
// 	}

    
/*    @GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="localbodysubtype")})*/
    @Id
//    @GeneratedValue(generator = "sequence")
    @GeneratedValue(strategy=GenerationType.AUTO) 
//    @Basic(optional = false)
    @Column(name = "local_body_subtype_code", nullable = false)
	public Integer getLocalBodySubtypeCode() {
        return localBodySubtypeCode;
    }

    public void setLocalBodySubtypeCode(Integer localBodySubtypeCode) {
        this.localBodySubtypeCode = localBodySubtypeCode;
    }

/*    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_body_type_code")
	public LocalBodyType getLocalBodyType() {
		return this.localBodyType;
	}

	public void setLocalBodyType(LocalBodyType localBodyType) {
		this.localBodyType = localBodyType;
	}*/


    @Column(name = "subtype_name_eng", nullable = false, length = 50)
    @Basic(optional = false)
    public String getSubtypeNameEng() {
        return subtypeNameEng;
    }

    public void setSubtypeNameEng(String subtypeNameEng) {
        this.subtypeNameEng = subtypeNameEng;
    }

    @Column(name = "subtype_name_local", length = 50)
    public String getSubtypeNameLocal() {
        return subtypeNameLocal;
    }

    public void setSubtypeNameLocal(String subtypeNameLocal) {
        this.subtypeNameLocal = subtypeNameLocal;
    }
    
// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}
	
    @Column(name = "lastupdated", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
	public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    @Basic(optional = false)
    @Column(name = "lastupdatedby", nullable = true)
    public long getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(long lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    @Column(name = "createdon", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }
    
    @Basic(optional = false)
    @Column(name = "createdby", nullable = false)
    public long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(long createdby) {
        this.createdby = createdby;
    }
    
    @Basic(optional = false)
    @Column(name = "isactive", nullable = false)
    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }


}
