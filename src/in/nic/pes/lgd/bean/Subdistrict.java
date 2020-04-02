package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
 * Subdistrict Pooja
 * 
 * @modification by :Chandan Soni
 */
@Entity
@FilterDef(name = "subdistrictFilter", parameters = @ParamDef(name = "isactive", type = "boolean"))
@Table(name = "subdistrict", schema = "public")
public class Subdistrict implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private SubdistrictPK subdistrictPK;
	private District district;
	private String subdistrictNameEnglish;
	private String subdistrictNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private String census2001Code;
	private String census2011Code;
	private String sscode;
	private Integer lrReplaces;
	private Integer lrReplacedby;
	private Date effectiveDate;
	private Date lastupdated;
	private String coordinates;

	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private boolean isactive;
	private int subdistrictCode;
	private int subdistrictVersion;
	// private int districtCode;
	// private int districtVersion;
	private Integer flagCode;
	// private Set<Village> village;
	private int dlc;
	private int tlc;
	private String districtNameEnglish;
	private Boolean warningFlag;

	/**
	 * Added by ripunj on 01-10-2014
	 * For Extend Department Functionality
	 */
	private Character operation_state;
	
	private Character operation_extend_flag;
	
	
	
	
	/**
	 * Draft entry
	 * @return
	 */
	private Character isPart;
	
	private String coverageType;
	
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

	public Subdistrict() {
	}

// TODO Remove unused code found by UCDetector
// 	public Subdistrict(SubdistrictPK subdistrictPK, District district, String subdistrictNameEnglish, Date effectiveDate, Date lastupdated, long lastupdatedby, Date createdon, long createdby, boolean isactive, int tlc) {
// 		this.subdistrictPK = subdistrictPK;
// 		this.district = district;
// 		this.subdistrictNameEnglish = subdistrictNameEnglish;
// 		this.effectiveDate = effectiveDate;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.isactive = isactive;
// 		this.tlc = tlc;
// 	}

// TODO Remove unused code found by UCDetector
// 	public Subdistrict(SubdistrictPK subdistrictPK, District district, String subdistrictNameEnglish, String subdistrictNameLocal, String aliasEnglish, String aliasLocal, Integer mapLandregionCode, String census2001Code, Integer census2011Code,
// 			String sscode, Integer lrReplaces, Integer lrReplacedby, Date effectiveDate, Date lastupdated, long lastupdatedby, Date createdon, long createdby, boolean isactive, int subdistrictCode, int subdistrictVersion, Integer flagCode, int dlc,
// 			int tlc) {
// 		super();
// 		this.subdistrictPK = subdistrictPK;
// 		this.district = district;
// 		this.subdistrictNameEnglish = subdistrictNameEnglish;
// 		this.subdistrictNameLocal = subdistrictNameLocal;
// 		this.aliasEnglish = aliasEnglish;
// 		this.aliasLocal = aliasLocal;
// 		this.mapLandregionCode = mapLandregionCode;
// 		this.census2001Code = census2001Code;
// 		this.census2011Code = census2011Code;
// 		this.sscode = sscode;
// 		this.lrReplaces = lrReplaces;
// 		this.lrReplacedby = lrReplacedby;
// 		this.effectiveDate = effectiveDate;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.isactive = isactive;
// 		this.subdistrictCode = subdistrictCode;
// 		this.subdistrictVersion = subdistrictVersion;
// 		this.flagCode = flagCode;
// 		// this.village = village;
// 		this.dlc = dlc;
// 		this.tlc = tlc;
// 	}

// TODO Remove unused code found by UCDetector
// 	public Subdistrict(int subdistrictCode, int subdistrictVersion) {
// 		this.subdistrictPK = new SubdistrictPK(subdistrictCode, subdistrictVersion);
// 		this.subdistrictCode = subdistrictCode;
// 		this.subdistrictVersion = subdistrictVersion;
// 	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "subdistrictCode", column = @Column(name = "subdistrict_code", nullable = false)),
		@AttributeOverride(name = "subdistrictVersion", column = @Column(name = "subdistrict_version", nullable = false)) ,
		@AttributeOverride(name = "minorVersion", column = @Column(name = "minor_version", nullable = false))})
	public SubdistrictPK getSubdistrictPK() {
		return subdistrictPK;
	}

	public void setSubdistrictPK(SubdistrictPK subdistrictPK) {
		this.subdistrictPK = subdistrictPK;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@Filter(name = "districtFilter", condition = "isactive = :isactive")
	@JoinColumns({ @JoinColumn(name = "dlc", referencedColumnName = "dlc", nullable = false, insertable = false, updatable = false),
	@JoinColumn(name = "isactive", referencedColumnName = "isactive", nullable = false, insertable = false, updatable = false) })
	
	// @Where(clause="isactive = true")
	public District getDistrict() {
		return this.district;
	}

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "subdistrict")
	 * 
	 * @Filter(name = "villageFilter",condition="isactive = :isactive") public
	 * Set<Village> getVillage() { return village; }
	 * 
	 * public void setVillage(Set<Village> village) { this.village = village; }
	 */

	public void setDistrict(District district) {
		this.district = district;
	}

	@Column(name = "subdistrict_code", nullable = false, insertable = false, updatable = false)
	public int getSubdistrictCode() {
		return this.subdistrictCode;
	}

	public void setSubdistrictCode(int subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	@Column(name = "subdistrict_version", nullable = false, insertable = false, updatable = false)
	public int getSubdistrictVersion() {
		return this.subdistrictVersion;
	}

	public void setSubdistrictVersion(int subdistrictVersion) {
		this.subdistrictVersion = subdistrictVersion;
	}
	
	
	@Column(name = "minor_version", nullable = false, insertable = false, updatable = false)
	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "subdistrict_name_english", nullable = false, length = 50)
	public String getSubdistrictNameEnglish() {
		return this.subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	@Column(name = "subdistrict_name_local", length = 50)
	public String getSubdistrictNameLocal() {
		return this.subdistrictNameLocal;
	}

	public void setSubdistrictNameLocal(String subdistrictNameLocal) {
		this.subdistrictNameLocal = subdistrictNameLocal;
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

	

	@Column(name = "census_2001_code", length = 4)
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

	/**
	 * Modified on 05-11-2014 by ripunj
	 * @return
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastupdated", nullable = true, length = 35)
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

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	/*
	 * @Column(name = "district_code", nullable =
	 * false,insertable=false,updatable=false) public int getDistrictCode() {
	 * return districtCode; }
	 * 
	 * public void setDistrictCode(int districtCode) { this.districtCode =
	 * districtCode; }
	 * 
	 * @Column(name = "district_version", nullable =
	 * false,insertable=false,updatable=false) public int getDistrictVersion() {
	 * return districtVersion; }
	 * 
	 * public void setDistrictVersion(int districtVersion) {
	 * this.districtVersion = districtVersion; }
	 */
	@Column(name = "dlc", nullable = false,  updatable = false)
	public int getDlc() {
		return dlc;
	}

	public void setDlc(int dlc) {
		this.dlc = dlc;
	}

	@Column(name = "tlc", nullable = false)
	public int getTlc() {
		return tlc;
	}

	public void setTlc(int tlc) {
		this.tlc = tlc;
	}

	@Column(name = "coordinates")
	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	@Transient
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public Boolean getWarningFlag() {
		return warningFlag;
	}

	public void setWarningFlag(Boolean warningFlag) {
		this.warningFlag = warningFlag;
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
	public Character getIsPart() {
		return isPart;
	}

	public void setIsPart(Character isPart) {
		this.isPart = isPart;
	}

	@Transient
	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	
	
	
	
}