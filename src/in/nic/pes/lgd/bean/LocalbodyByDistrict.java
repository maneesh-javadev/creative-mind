package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
/**
*
* @author chandan
*/
@Entity
@Table(name = "localbody", catalog = "lgd_test", schema = "public" )
//@SqlResultSetMapping(name = "explicit", entities = @EntityResult(entityClass =  LocalbodyByDistrict.class))
@NamedNativeQuery(query=" SELECT case when u.local_body_code  in (select * from get_draft_used_lb_lr_temp(u.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_ulb_list_by_district_code_fn(:districtCode)u",name="getULBList", resultClass = LocalbodyByDistrict.class)

public class LocalbodyByDistrict {

  //private static final long serialVersionUID = 1L;
/*   @EmbeddedId
    protected LocalbodyPK localbodyPK;*/
  //@NotNull
	private int localBodyCode;
    private String localBodyNameEnglish;
    private String localBodyNameLocal;
    private Character operation_state;
/*    private Integer parentLocalBodyCode;
    private Integer parentLocalBodyVersion;
  //@NotNull
    private String aliasEnglish;
    private String aliasLocal;
    private Integer lbReplaces;
    private Integer lbReplacedby;
    private Integer flag_code;
	//@NotNull
    private int lbCoveredRegionCode;
    private Boolean isdisturbed;
  //@NotNull
  //@Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
  //@NotNull
    private boolean isactive;
  //@NotNull
  //@Temporal(TemporalType.TIMESTAMP)
    private Date lastupdated;
  //@NotNull
    private long lastupdatedby;
  //@NotNull
  //@Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
  //@NotNull
    private long createdby;*/
/*    
    private int localBodyVersion;
    private int mapLocalBodyCode;
    private int stateCode;
    private int stateVersion;
    private int localGovtTypeCode;
    private int localGovtSubtypeCode;*/
  //@OneToMany(mappedBy = "localbody")
  //  private Collection<Headquarters> headquartersCollection;
  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "localbody")
  //  private Collection<LocalbodyWard> localbodyWardCollection;
    
//  @JoinColumns({
  //    @JoinColumn(name = "state_code", referencedColumnName = "state_code"),
    //  @JoinColumn(name = "state_version", referencedColumnName = "state_version")})
  //@ManyToOne(optional = false)
  //  private State state;
  //@JoinColumn(name = "local_govt_type_code", referencedColumnName = "local_govt_type_code")
  //@ManyToOne(optional = false)
 //   private LocalBodyType localGovtTypeCode;
  //@JoinColumn(name = "local_govt_subtype_code", referencedColumnName = "local_govt_subtype_code")
  //@ManyToOne
 //   private LocalBodySubtype localGovtSubtypeCode;

  
    @Id
//    @Column(name = "local_body_code",insertable=false,updatable=false)
    @Column(name = "local_body_code")
    public int getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	
	 @Column(name = "local_body_name_english")
	public String getLocalBodyNameEnglish() {
        return localBodyNameEnglish;
    }

    public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
        this.localBodyNameEnglish = localBodyNameEnglish;
    }

    @Column(name = "local_body_name_local")
    public String getLocalBodyNameLocal() {
        return localBodyNameLocal;
    }

    public void setLocalBodyNameLocal(String localBodyNameLocal) {
        this.localBodyNameLocal = localBodyNameLocal;
    }
    @Column(name = "operation_state")
	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
    
    
    
   
}
