/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 
 */
@Entity
@Table(name = "nomenclature_subdistrict")
public class NomenclatureSubdistrict implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
  	private NomenclatureSubdistrictPK nomenclatureSubdistrictPK;
  	
	
    private int nsdCode;
	
   
    private int nsdVersion;
    
   
    private String nomenclatureEnglish;
    
   
    private String nomenclatureLocal;
    

    private Date effectiveDate;
    
    
    private boolean isactive;
    

    private Date lastupdated;
    
    
    private long lastupdatedby;
    

    private Date createdon;
    
   
    private long createdby;

/*    private State state;
    
    
    private int stateCode;
    
    
    private int stateVersion;
    */
    
    private Integer slc;
    
    private boolean issubdistrictblocksame;
    
    private boolean isBlockExists;

    /**
	 * @return the isBlockExists
	 */
    @Column(name = "isblockexists",nullable=false)
	public boolean isBlockExists() {
		return isBlockExists;
	}

	/**
	 * @param isBlockExists the isBlockExists to set
	 */
	public void setBlockExists(boolean isBlockExists) {
		this.isBlockExists = isBlockExists;
	}

	/**
	 * @return the issubdistrictblocksame
	 */
    @Column(name = "issubdistrictblocksame")
	public boolean isIssubdistrictblocksame() {
		return issubdistrictblocksame;
	}

	/**
	 * @param issubdistrictblocksame the issubdistrictblocksame to set
	 */
	public void setIssubdistrictblocksame(boolean issubdistrictblocksame) {
		this.issubdistrictblocksame = issubdistrictblocksame;
	}

	public NomenclatureSubdistrict() {
    }

// TODO Remove unused code found by UCDetector
//     public NomenclatureSubdistrict(
// 			NomenclatureSubdistrictPK nomenclatureSubdistrictPK, int nsdCode,
// 			int nsdVersion, String nomenclatureEnglish,
// 			String nomenclatureLocal, Date effectiveDate, boolean isactive,
// 			Date lastupdated, long lastupdatedby, Date createdon,
// 			long createdby,  int slc, 
// 			boolean issubdistrictblocksame) {
// 		super();
// 		this.nomenclatureSubdistrictPK = nomenclatureSubdistrictPK;
// 		this.nsdCode = nsdCode;
// 		this.nsdVersion = nsdVersion;
// 		this.nomenclatureEnglish = nomenclatureEnglish;
// 		this.nomenclatureLocal = nomenclatureLocal;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		//this.state = state;
// 		//this.stateCode = stateCode;
// 		//this.stateVersion = stateVersion;
// 		this.slc=slc;
// 		this.issubdistrictblocksame = issubdistrictblocksame;
// 	}

// TODO Remove unused code found by UCDetector
// 	public NomenclatureSubdistrict(
// 			NomenclatureSubdistrictPK nomenclatureSubdistrictPK,
// 			String nomenclatureEnglish, String nomenclatureLocal,
// 			Date effectiveDate, boolean isactive, Date lastupdated,
// 			long lastupdatedby, Date createdon, long createdby, int slc) {
// 		super();
// 		this.nomenclatureSubdistrictPK = nomenclatureSubdistrictPK;
// 		this.nomenclatureEnglish = nomenclatureEnglish;
// 		this.nomenclatureLocal = nomenclatureLocal;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.slc=slc;
// 	}

// TODO Remove unused code found by UCDetector
// 	public NomenclatureSubdistrict(
// 			NomenclatureSubdistrictPK nomenclatureSubdistrictPK, int nsdCode,
// 			int nsdVersion, String nomenclatureEnglish,
// 			String nomenclatureLocal, Date effectiveDate, boolean isactive,
// 			Date lastupdated, long lastupdatedby, Date createdon,
// 			long createdby, int slc) {
// 		super();
// 		this.nomenclatureSubdistrictPK = nomenclatureSubdistrictPK;
// 		this.nsdCode = nsdCode;
// 		this.nsdVersion = nsdVersion;
// 		this.nomenclatureEnglish = nomenclatureEnglish;
// 		this.nomenclatureLocal = nomenclatureLocal;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.slc=slc;
// 	}
	
// TODO Remove unused code found by UCDetector
// 	public NomenclatureSubdistrict(int nsdCode, int nsdVersion) {
// 		this.nomenclatureSubdistrictPK = new NomenclatureSubdistrictPK(nsdCode, nsdVersion);
// 		this.nsdCode = nsdCode;
// 		this.nsdVersion = nsdVersion;
// 	}


	/**
  	 * @return the nomenclatureSubdistrictPK
  	 */
 	@EmbeddedId
  	@AttributeOverrides({
  			@AttributeOverride(name = "nsdCode", column = @Column(name = "nsd_code", nullable = false)),
  			@AttributeOverride(name = "nsdVersion", column = @Column(name = "nsd_version", nullable = false)) })
  	public NomenclatureSubdistrictPK getNomenclatureSubdistrictPK() {
  		return nomenclatureSubdistrictPK;
  	}

  	/**
  	 * @param nomenclatureSubdistrictPK the nomenclatureSubdistrictPK to set
  	 */
  	public void setNomenclatureSubdistrictPK(
  			NomenclatureSubdistrictPK nomenclatureSubdistrictPK) {
  		this.nomenclatureSubdistrictPK = nomenclatureSubdistrictPK;
  	}
  	
	/**
	 * @return the state
	 */
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "state_code", referencedColumnName = "state_code", nullable = false),
			@JoinColumn(name = "state_version", referencedColumnName = "state_version", nullable = false) })
	public State getState() {
		return state;
	}

	*//**
	 * @param state the state to set
	 *//*
	public void setState(State state) {
		this.state = state;
	}*/
  	
	 @Column(name = "nomenclature_english")
    public String getNomenclatureEnglish() {
        return nomenclatureEnglish;
    }

    public void setNomenclatureEnglish(String nomenclatureEnglish) {
        this.nomenclatureEnglish = nomenclatureEnglish;
    }

    @Column(name = "nomenclature_local")
    public String getNomenclatureLocal() {
        return nomenclatureLocal;
    }

    public void setNomenclatureLocal(String nomenclatureLocal) {
        this.nomenclatureLocal = nomenclatureLocal;
    }

    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Column(name = "isactive",nullable=false)
    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    @Column(name = "lastupdated")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    @Column(name = "lastupdatedby")
    public long getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(long lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    @Column(name = "createdby")
    public long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(long createdby) {
        this.createdby = createdby;
    }

    @Column(name = "nsd_code",nullable = false,insertable=false,updatable=false)
	public int getNsdCode() {
		return nsdCode;
	}


	public void setNsdCode(int nsdCode) {
		this.nsdCode = nsdCode;
	}

	 @Column(name = "nsd_version",nullable = false,insertable=false,updatable=false)
	public int getNsdVersion() {
		return nsdVersion;
	}


	public void setNsdVersion(int nsdVersion) {
		this.nsdVersion = nsdVersion;
	}
	
	
/*	@Column(name = "state_code",insertable=false,updatable=false)
	public int getStateCode() {
		return stateCode;
	}


	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	@Column(name = "state_version",insertable=false,updatable=false)
	public int getStateVersion() {
		return stateVersion;
	}


	public void setStateVersion(int stateVersion) {
		this.stateVersion = stateVersion;
	}*/

	@Column(name = "slc")
	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}



    
    
}
