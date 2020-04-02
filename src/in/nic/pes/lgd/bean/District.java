package in.nic.pes.lgd.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Pooja
 */
@Entity
@FilterDef(name = "districtFilter", parameters = @ParamDef(name = "isactive", type = "boolean"))
@Table(name = "district")
public class District implements java.io.Serializable {

	/**
	 * @modification by :Chandan Soni
	 */
	private static final long serialVersionUID = 1L;
	private DistrictPK districtPK;
	// private State state;
	private String districtNameEnglish;
	private String districtNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private Integer slc;
	private Integer dlc;
	private String coordinates;
	private Boolean warningflag;
	private String census2001Code;
	private String census2011Code;
	private String sscode;
	private Integer lrReplaces;
	private Integer lrReplacedby;
	private Date effectiveDate;
	private boolean isactive;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private Set<Subdistrict> subdistricts;

	private int districtCode;

	private int districtVersion;
	
	
	private Character operation_state;
	
	
	
	private Character operation_extend_flag;

	// private int stateCode;

	// private int stateVersion;

	private String stateNameEnglish;
	
	private Integer minorVersion;
	
	@Column(name = "parent_hierarchy")
	@Type(type = "in.nic.pes.lgd.types.hibernate.JsonType")
	public JsonNode parentHierarchy;
	
	@Column(name = "lr_replaces")
	public Integer getLrReplaces() {
		return lrReplaces;
	}

	public void setLrReplaces(Integer lrReplaces) {
		this.lrReplaces = lrReplaces;
	}

	@Column(name = "lr_replacedby")
	public Integer getLrReplacedby() {
		return lrReplacedby;
	}

	public void setLrReplacedby(Integer lrReplacedby) {
		this.lrReplacedby = lrReplacedby;
	}

	@Column(name = "flag_code")
	public Integer getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	@Column(name = "flag_code")
	private Integer flagCode;

	public District() {
	}

// TODO Remove unused code found by UCDetector
// 	public District(DistrictPK districtPK, State state,
// 			String districtNameEnglish, Date effectiveDate, boolean isactive,
// 			Date lastupdated, long lastupdatedby, Date createdon, long createdby) {
// 		this.districtPK = districtPK;
// 		// this.state = state;
// 		this.districtNameEnglish = districtNameEnglish;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 	}

// TODO Remove unused code found by UCDetector
// 	public District(DistrictPK districtPK, State state,
// 			String districtNameEnglish, String districtNameLocal,
// 			String aliasEnglish, String aliasLocal, Integer mapCode,
// 			String census2001Code, Integer census2011Code, String sscode,
// 			Integer lrReplaces, Integer lrReplacedby, Integer flagCode,
// 			Date effectiveDate, boolean isactive, Date lastupdated,
// 			long lastupdatedby, Date createdon, long createdby,
// 			Set<Subdistrict> subdistricts, int districtCode,
// 			int districtVersion, int slc) {
// 		super();
// 		this.districtPK = districtPK;
// 		// this.state = state;
// 		this.districtNameEnglish = districtNameEnglish;
// 		this.districtNameLocal = districtNameLocal;
// 		this.aliasEnglish = aliasEnglish;
// 		this.aliasLocal = aliasLocal;
// 		this.mapCode = mapCode;
// 		this.census2001Code = census2001Code;
// 		this.census2011Code = census2011Code;
// 		this.sscode = sscode;
// 		this.lrReplaces = lrReplaces;
// 		this.lrReplacedby = lrReplacedby;
// 		this.flagCode = flagCode;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.subdistricts = subdistricts;
// 		this.districtCode = districtCode;
// 		this.districtVersion = districtVersion;
// 		// this.stateCode = stateCode;
// 		// this.stateVersion = stateVersion;
// 
// 		this.slc = slc;
// 	}

	public District(int districtCode, int districtVersion,int minorVersion) {
		this.districtPK = new DistrictPK(districtCode, districtVersion,minorVersion);
		this.districtCode = districtCode;
		this.districtVersion = districtVersion;
		this.minorVersion = minorVersion;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "districtCode", column = @Column(name = "district_code", nullable = false)),
			@AttributeOverride(name = "districtVersion", column = @Column(name = "district_version", nullable = false)),
			@AttributeOverride(name = "minorVersion", column = @Column(name = "minor_version", nullable = false))
			})
	public DistrictPK getDistrictPK() {
		return this.districtPK;
	}

	public void setDistrictPK(DistrictPK districtPK) {
		this.districtPK = districtPK;
	}

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @Filter(name = "stateFilter",condition="isactive = :isactive")
	 * 
	 * @JoinColumns({
	 * 
	 * @JoinColumn(name = "slc", referencedColumnName = "slc", nullable =
	 * false,insertable=false,updatable=false),
	 * 
	 * @JoinColumn(name = "isactive", referencedColumnName = "isactive",
	 * nullable = false,insertable=false,updatable=false) }) public State
	 * getState() { return this.state; }
	 * 
	 * public void setState(State state) { this.state = state; }
	 */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	@Filter(name = "subdistrictFilter", condition = "isactive = :isactive")
	public Set<Subdistrict> getSubdistricts() {
		return subdistricts;
	}

	public void setSubdistricts(Set<Subdistrict> subdistricts) {
		this.subdistricts = subdistricts;
	}

	@Column(name = "district_code", nullable = false, insertable = false, updatable = false)
	public int getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}

	@Column(name = "district_version", nullable = false, insertable = false, updatable = false)
	public int getDistrictVersion() {
		return districtVersion;
	}

	public void setDistrictVersion(int districtVersion) {
		this.districtVersion = districtVersion;
	}
	
	@Column(name = "minor_version", nullable = false, insertable = false, updatable = false)
	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}


	/*
	 * @Column(name = "state_code", insertable = false, updatable = false)
	 * public int getStateCode() { return stateCode; }
	 * 
	 * public void setStateCode(int stateCode) { this.stateCode = stateCode; }
	 * 
	 * @Column(name = "state_version", insertable = false, updatable = false)
	 * public int getStateVersion() { return stateVersion; }
	 * 
	 * public void setStateVersion(int stateVersion) { this.stateVersion =
	 * stateVersion; }
	 */

	@Column(name = "district_name_english", nullable = false, length = 50)
	public String getDistrictNameEnglish() {
		return this.districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	@Column(name = "district_name_local", length = 50)
	public String getDistrictNameLocal() {
		return this.districtNameLocal;
	}

	public void setDistrictNameLocal(String districtNameLocal) {
		this.districtNameLocal = districtNameLocal;
	}

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

	

	@Column(name = "census_2001_code", length = 2)
	public String getCensus2001Code() {
		return this.census2001Code;
	}

	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}

	@Column(name = "census_2011_code")
	public String getCensus2011Code() {
		return this.census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	@Column(name = "sscode", length = 5)
	public String getSscode() {
		return this.sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effective_date", nullable = false, length = 35)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastupdated", nullable = false, length = 35)
	public Date getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Column(name = "lastupdatedby", nullable = false)
	public long getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(long lastupdatedby) {
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

	@Column(name = "slc")
	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	@Column(name = "dlc")
	public Integer getDlc() {
		return dlc;
	}

	public void setDlc(Integer dlc) {
		this.dlc = dlc;
	}

	@Column(name = "coordinates")
	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	@Column(name = "warningflag")
	public Boolean isWarningflag() {
		return warningflag;
	}

	public void setWarningflag(Boolean warningflag) {
		this.warningflag = warningflag;
	}
	
	@Transient
	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}

	
	@Transient
	public Character getOperation_extend_flag() {
		return operation_extend_flag;
	}

	public void setOperation_extend_flag(Character operation_extend_flag) {
		this.operation_extend_flag = operation_extend_flag;
	}
	@Transient
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	
	

	
	
}
