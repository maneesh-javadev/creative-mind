package in.nic.pes.lgd.bean;
 

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Localbody
 */
@Entity
@Table(name = "localbody")
public class Localbody implements java.io.Serializable {

   /**
	 * 
	 */
	private static final long serialVersionUID = 7886400112947725024L;
	// private LocalbodyPK id;
    private State state;
    private LocalBodyType localBodyType;
    private LocalBodySubtype localBodySubtype;
    private String localBodyNameEnglish;
    private String localBodyNameLocal;
   /* private Integer parentLocalBodyCode;
    private Integer parentLocalBodyVersion;*/
    private String aliasEnglish;
    private String aliasLocal;
    private Integer lbReplaces;
    private Integer lbCoveredRegionCode;
    private Date effectiveDate;
    private Date lastupdated;
    private Long lastupdatedby;
    private Date createdon;
    private long createdby;
    private boolean isdisturbed;
    private boolean isactive;
    private boolean warningflag;
   // private Integer mapLocalbodyCode;
    private Integer map_attachment_code;
    private String coordinates;
    private Integer flagCode;
    private Integer lbReplacedby;
    private Integer localBodyCode;  
    private int localBodyTypeCode;
    private Integer localBodyVersion;
    private Integer lblc;
    private Integer slc;
    private Integer parentlblc;
    private String stateSpecificCode; 
    private boolean ispesa;
   
    /**
     * Added by ripunj for Draft Mode of LocalBody on 20-11-2014
     */
    private Character operation_state;
    
    @Column(name = "parent_lblc")
    public Integer getParentlblc() {
		return parentlblc;
	}

	public void setParentlblc(Integer parentlblc) {
		this.parentlblc = parentlblc;
	}

	private Set<MapLocalbody> mapLocalbodies = new HashSet<MapLocalbody>(0);
   // private Set<LocalbodyWard> localbodyWards = new HashSet<LocalbodyWard>(0);
    private Set<Headquarters> headquarterses = new HashSet<Headquarters>(0);

    public Localbody() {
    }

// TODO Remove unused code found by UCDetector
//     public Localbody(State state, LocalBodyType localBodyType,
//             String localBodyNameEnglish, Date effectiveDate, Date createdon,
//             long createdby, boolean isdisturbed, boolean isactive) {
//         //this.id = id;
//         this.state = state;
//         this.localBodyType = localBodyType;
//         this.localBodyNameEnglish = localBodyNameEnglish;
//         this.effectiveDate = effectiveDate;
//         this.createdon = createdon;
//         this.createdby = createdby;
//         this.isdisturbed = isdisturbed;
//         this.isactive = isactive;
//     }

// TODO Remove unused code found by UCDetector
//     public Localbody(State state, LocalBodyType localBodyType,
//             LocalBodySubtype localBodySubtype, String localBodyNameEnglish,
//             String localBodyNameLocal, String aliasEnglish,
//             String aliasLocal, Integer lbReplaces, Integer lbCoveredRegionCode,
//             Date effectiveDate, Date lastupdated, Long lastupdatedby,
//             Date createdon, long createdby, boolean isdisturbed,
//             boolean isactive, Integer mapLocalbodyCode, Integer flagCode,Integer lblc,Integer map_attachment_code,
//             Integer lbReplacedby, Set<MapLocalbody> mapLocalbodies,
//             Set<LocalbodyWard> localbodyWards, Set<Headquarters> headquarterses,Integer parentlblc) {
//         //this.id = id;
//         this.state = state;
//         this.localBodyType = localBodyType;
//         this.localBodySubtype = localBodySubtype;
//         this.localBodyNameEnglish = localBodyNameEnglish;
//         this.localBodyNameLocal = localBodyNameLocal;
//        /* this.parentLocalBodyCode = parentLocalBodyCode;
//         this.parentLocalBodyVersion = parentLocalBodyVersion;*/
//         this.aliasEnglish = aliasEnglish;
//         this.aliasLocal = aliasLocal;
//         this.lbReplaces = lbReplaces;
//         this.lbCoveredRegionCode = lbCoveredRegionCode;
//         this.effectiveDate = effectiveDate;
//         this.lastupdated = lastupdated;
//         this.lastupdatedby = lastupdatedby;
//         this.createdon = createdon;
//         this.createdby = createdby;
//         this.isdisturbed = isdisturbed;
//         this.isactive = isactive;
//         this.map_attachment_code = map_attachment_code;
//         this.flagCode = flagCode;
//         this.lbReplacedby = lbReplacedby;
//         this.mapLocalbodies = mapLocalbodies;
//       //  this.localbodyWards = localbodyWards;
//         this.headquarterses = headquarterses;
//         this.lblc=lblc;
//         this.parentlblc=parentlblc;
//     }

   /* @EmbeddedId  
    @AttributeOverrides({
            @AttributeOverride(name = "localBodyCode", column = @Column(name = "local_body_code", nullable = false)),
            @AttributeOverride(name = "localBodyVersion", column = @Column(name = "local_body_version", nullable = false)) })
    public LocalbodyPK getId() {
        return this.id;
    }

    public void setId(LocalbodyPK id) {
        this.id = id;
    }*/

    @Id
    @Column(name = "local_body_code", nullable = false,insertable=false,updatable=false)
    public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	@Column(name = "local_body_version", nullable = false,insertable=false,updatable=false)
	public Integer getLocalBodyVersion() {
		return localBodyVersion;
	}

	public void setLocalBodyVersion(Integer localBodyVersion) {
		this.localBodyVersion = localBodyVersion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "slc", referencedColumnName = "slc", nullable = false),
            @JoinColumn(name = "isactive", referencedColumnName = "isactive", nullable = false)
            })
    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_body_type_code", nullable = false,insertable=false,updatable=false)
    public LocalBodyType getLocalBodyType() {
        return this.localBodyType;
    }

    public void setLocalBodyType(LocalBodyType localBodyType) {
        this.localBodyType = localBodyType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_body_subtype_code")
    public LocalBodySubtype getLocalBodySubtype() {
        return this.localBodySubtype;
    }

    public void setLocalBodySubtype(LocalBodySubtype localBodySubtype) {
        this.localBodySubtype = localBodySubtype;
    }

    @Column(name = "local_body_name_english", nullable = false, length = 50)
    public String getLocalBodyNameEnglish() {
        return this.localBodyNameEnglish;
    }

    public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
        this.localBodyNameEnglish = localBodyNameEnglish;
    }

    @Column(name = "local_body_name_local", length = 50)
    public String getLocalBodyNameLocal() {
        return this.localBodyNameLocal;
    }

    public void setLocalBodyNameLocal(String localBodyNameLocal) {
        this.localBodyNameLocal = localBodyNameLocal;
    }

  /*  @Column(name = "parent_local_body_code")
    public Integer getParentLocalBodyCode() {
        return this.parentLocalBodyCode;
    }

    public void setParentLocalBodyCode(Integer parentLocalBodyCode) {
        this.parentLocalBodyCode = parentLocalBodyCode;
    }

    @Column(name = "parent_local_body_version")
    public Integer getParentLocalBodyVersion() {
        return this.parentLocalBodyVersion;
    }

    public void setParentLocalBodyVersion(Integer parentLocalBodyVersion) {
        this.parentLocalBodyVersion = parentLocalBodyVersion;
    }
*/
    @Column(name = "alias_english", length = 50)
    public String getAliasEnglish() {
        return this.aliasEnglish;
    }

    public void setAliasEnglish(String aliasEnglish) {
        this.aliasEnglish = aliasEnglish;
    }

    @Column(name = "alias_local", length = 50)
    public String getAliasLocal() {
        return this.aliasLocal;
    }

    public void setAliasLocal(String aliasLocal) {
        this.aliasLocal = aliasLocal;
    }

    @Column(name = "lb_replaces")
    public Integer getLbReplaces() {
        return this.lbReplaces;
    }

    public void setLbReplaces(Integer lbReplaces) {
        this.lbReplaces = lbReplaces;
    }

    @Column(name = "lb_covered_region_code")
    public Integer getLbCoveredRegionCode() {
        return this.lbCoveredRegionCode;
    }

    public void setLbCoveredRegionCode(Integer lbCoveredRegionCode) {
        this.lbCoveredRegionCode = lbCoveredRegionCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "effective_date", nullable = false, length = 35)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastupdated", length = 35)
    public Date getLastupdated() {
        return this.lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    @Column(name = "lastupdatedby")
    public Long getLastupdatedby() {
        return this.lastupdatedby;
    }

    public void setLastupdatedby(Long lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdon", nullable = false, length = 35)
    public Date getCreatedon() {
        return this.createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    @Column(name = "createdby", nullable = false)
    public long getCreatedby() {
        return this.createdby;
    }

    public void setCreatedby(long createdby) {
        this.createdby = createdby;
    }

    @Column(name = "isdisturbed", nullable = false)
    public boolean isIsdisturbed() {
        return this.isdisturbed;
    }

    public void setIsdisturbed(boolean isdisturbed) {
        this.isdisturbed = isdisturbed;
    }

    @Column(name = "isactive", nullable = false,insertable=false,updatable=false)
    public boolean isIsactive() {
        return this.isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

   /* @Column(name = "map_localbody_code")
    public Integer getMapLocalbodyCode() {
        return this.mapLocalbodyCode;
    }

    public void setMapLocalbodyCode(Integer mapLocalbodyCode) {
        this.mapLocalbodyCode = mapLocalbodyCode;
    }*/

    @Column(name = "flag_code")
    public Integer getFlagCode() {
        return this.flagCode;
    }

    public void setFlagCode(Integer flagCode) {
        this.flagCode = flagCode;
    }

    @Column(name = "lb_replacedby")
    public Integer getLbReplacedby() {
        return this.lbReplacedby;
    }

    public void setLbReplacedby(Integer lbReplacedby) {
        this.lbReplacedby = lbReplacedby;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "localbody")
    public Set<MapLocalbody> getMapLocalbodies() {
        return this.mapLocalbodies;
    }

    public void setMapLocalbodies(Set<MapLocalbody> mapLocalbodies) {
        this.mapLocalbodies = mapLocalbodies;
    }

/*    @OneToMany(fetch = FetchType.LAZY, mappedBy = "localbody")
    public Set<LocalbodyWard> getLocalbodyWards() {
        return this.localbodyWards;
    }

    public void setLocalbodyWards(Set<LocalbodyWard> localbodyWards) {
        this.localbodyWards = localbodyWards;
    }
*/
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "localbody")
    public Set<Headquarters> getHeadquarterses() {
        return this.headquarterses;
    }

    public void setHeadquarterses(Set<Headquarters> headquarterses) {
        this.headquarterses = headquarterses;
    }

    @Column(name = "local_body_type_code")
	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	@Column(name = "lblc")
	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}
	@Column(name = "map_attachment_code")
	public Integer getMap_attachment_code() {
		return map_attachment_code;
	}

	public void setMap_attachment_code(Integer map_attachment_code) {
		this.map_attachment_code = map_attachment_code;
	}
	 @Column(name = "slc", nullable = false,insertable=false,updatable=false)
	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	public boolean isWarningflag() {
		return warningflag;
	}
	@Column(name = "warningflag")
	public void setWarningflag(boolean warningflag) {
		this.warningflag = warningflag;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
    
	@Column(name="sscode")
	public String getStateSpecificCode() {
		return stateSpecificCode;
	}

	public void setStateSpecificCode(String stateSpecificCode) {
		this.stateSpecificCode = stateSpecificCode;
	}
    @Column(name="is_pesa")
	public boolean isIspesa() {
		return ispesa;
	}

	public void setIspesa(boolean ispesa) {
		this.ispesa = ispesa;
	}
    @Transient
	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}

	
	
	
	
    
	
}