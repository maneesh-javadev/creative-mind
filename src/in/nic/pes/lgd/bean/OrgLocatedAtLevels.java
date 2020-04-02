package in.nic.pes.lgd.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Where;
import java.util.Date;
/**
 * @author Ram
 */

@SequenceGenerator(name = "org_located_at_levels_seq", initialValue=1, allocationSize=1, sequenceName = "map_org_located_at_levels")
@Entity
@Table(name = "org_located_at_levels", schema = "public")
public class OrgLocatedAtLevels implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int orgLocatedLevelCode;
	private Organization organization;
	private Integer locatedAtLevel;
	private String unitLevelNameEnglish;
	private String orgLevelSpecificName;
	
	

	private String orgLevelSpecificNameLocal;
	private String orgLevelSpecificShortName;
	private Integer parentOrgLocatedLevelCode;
	private boolean isactive;
	private boolean orguntDone;
	
	private String parentOrgLevelSpecificName;
	//private int orgCode;
	//private int orgVersion;
	private int olc;
	/**
	 * new Column add in table  
	 */
	private Integer sortOrder; 
	

	private String pageAccessLevel;
	
	
	private String unitLevelName;
	
	private Set<ReportingOrg> reportingOrg=new HashSet<ReportingOrg>(0);  
	
	/*New Columns Added for Manage Organization Levels on 16-09-2015 by Pooja*/
	
	
	private Integer orgLocatedLevelVersion;
	private Date effectiveDate;
	private Long lastUpdatedBy;
	private Integer transactionId;
	private Long createdBy;
	private Date lastupdated;
	private String locatedLevel;
	
	public OrgLocatedAtLevels() {
		super();
	}
	
// TODO Remove unused code found by UCDetector
// 	public OrgLocatedAtLevels(int orgLocatedLevelCode,
// 			Organization organization, int locatedAtLevel,
// 			String orgLevelSpecificName, String orgLevelSpecificNameLocal,
// 			String orgLevelSpecificShortName, int parentOrgLocatedLevelCode,
// 			boolean isactive,boolean orguntDone) {
// 		super();
// 		this.orgLocatedLevelCode = orgLocatedLevelCode;
// 		this.organization = organization;
// 		this.locatedAtLevel = locatedAtLevel;
// 		this.orgLevelSpecificName = orgLevelSpecificName;
// 		this.orgLevelSpecificNameLocal = orgLevelSpecificNameLocal;
// 		this.orgLevelSpecificShortName = orgLevelSpecificShortName;
// 		this.parentOrgLocatedLevelCode = parentOrgLocatedLevelCode;
// 		this.isactive = isactive;
// 		this.orguntDone=orguntDone;
// 	}


	//@GeneratedValue(generator = "sequence")
    @Id
    @GeneratedValue(generator = "org_located_at_levels_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="org_located_level_code", unique = true, nullable = false)
	public int getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}
	public void setOrgLocatedLevelCode(int orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
	      @JoinColumn(name = "olc", referencedColumnName = "olc")
	      //,@JoinColumn(name = "isactive", referencedColumnName = "isactive")
	      })
	      @Where(clause="isactive IS true ")
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@Column(name="located_at_level")
	public int getLocatedAtLevel() {
		return locatedAtLevel;
	}
	public void setLocatedAtLevel(int locatedAtLevel) {
		this.locatedAtLevel = locatedAtLevel;
	}
	@Transient
	//@Column(name="unit_level_name_english")
	public String getUnitLevelNameEnglish() {
		return unitLevelNameEnglish;
	}

	public void setUnitLevelNameEnglish(String unitLevelNameEnglish) {
		this.unitLevelNameEnglish = unitLevelNameEnglish;
	}
	@Column(name="org_level_specific_name")
	public String getOrgLevelSpecificName() {
		return orgLevelSpecificName;
	}
	public void setOrgLevelSpecificName(String orgLevelSpecificName) {
		this.orgLevelSpecificName = orgLevelSpecificName;
	}
	/*@Column(name = "local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}*/
	@Column(name="org_level_specific_name_local")
	public String getOrgLevelSpecificNameLocal() {
		return orgLevelSpecificNameLocal;
	}
	public void setOrgLevelSpecificNameLocal(String orgLevelSpecificNameLocal) {
		this.orgLevelSpecificNameLocal = orgLevelSpecificNameLocal;
	}
	
	@Column(name="org_level_specific_short_name")
	public String getOrgLevelSpecificShortName() {
		return orgLevelSpecificShortName;
	}
	public void setOrgLevelSpecificShortName(String orgLevelSpecificShortName) {
		this.orgLevelSpecificShortName = orgLevelSpecificShortName;
	}
	@Column(name="parent_org_located_level_code")
	public Integer getParentOrgLocatedLevelCode() {
		return parentOrgLocatedLevelCode;
	}
	public void setParentOrgLocatedLevelCode(Integer parentOrgLocatedLevelCode) {
		this.parentOrgLocatedLevelCode = parentOrgLocatedLevelCode;
	}
	
	/**
	 * Remove insertable and updateble false from this column
	 * @author Maneesh Kumar 21-July-2015
	 */
	@Column(name = "isactive", nullable=false)
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	/*@Column(name = "org_code",insertable=false,updatable=false, nullable=false)
	public int getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "org_version",insertable=false,updatable=false, nullable=false)
	public int getOrgVersion() {
		return orgVersion;
	}

	public void setOrgVersion(int orgVersion) {
		this.orgVersion = orgVersion;
	}*/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orgLocatedAtLevels")
	public Set<ReportingOrg> getReportingOrg() {
		return reportingOrg;
	}

	public void setReportingOrg(Set<ReportingOrg> reportingOrg) {
		this.reportingOrg = reportingOrg;
	}
	@Column(name = "olc",insertable=false,updatable=false, nullable=false)
	public int getOlc() {
		return olc;
	}

	public void setOlc(int olc) {
		this.olc = olc;
	}

	@Column(name = "org_unt_done", nullable=false)
	public boolean isOrguntDone() {
		return orguntDone;
	}

	public void setOrguntDone(boolean orguntDone) {
		this.orguntDone = orguntDone;
	}

	@Transient
	public String getParentOrgLevelSpecificName() {
		return parentOrgLevelSpecificName;
	}

	public void setParentOrgLevelSpecificName(String parentOrgLevelSpecificName) {
		this.parentOrgLevelSpecificName = parentOrgLevelSpecificName;
	}

	@Column(name="sort_order")
	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Transient
	public String getUnitLevelName() {
		return unitLevelName;
	}

	public void setUnitLevelName(String unitLevelName) {
		this.unitLevelName = unitLevelName;
	}

	@Transient
	public String getPageAccessLevel() {
		return pageAccessLevel;
	}

	public void setPageAccessLevel(String pageAccessLevel) {
		this.pageAccessLevel = pageAccessLevel;
	}

	@Column(name="org_located_level_version")
	public Integer getOrgLocatedLevelVersion() {
		return orgLocatedLevelVersion;
	}

	public void setOrgLocatedLevelVersion(Integer orgLocatedLevelVersion) {
		this.orgLocatedLevelVersion = orgLocatedLevelVersion;
	}

	@Column(name="effective_date")
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name="lastupdatedby")
	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name="transaction_id")
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	@Transient
	public String getLocatedLevel() {
		return locatedLevel;
	}

	public void setLocatedLevel(String locatedLevel) {
		this.locatedLevel = locatedLevel;
	}

	@Column(name="createdby")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="lastupdated")
	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	
}
