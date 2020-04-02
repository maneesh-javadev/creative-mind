/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * @author Pooja
 */
@Entity
@FilterDef(name = "villageFilter", parameters = @ParamDef(name = "isactive", type = "boolean"))
@Table(name = "village")

public class Village implements Serializable {
	private static final long serialVersionUID = 1L;

	private VillagePK villagePK;
	
	private String villageNameEnglish;
	private String villageNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private String villageStatus;
	private String census2001Code;
	private String census2011Code;
	private String sscode;
	// private String remarks;
	private Integer lrReplaces;
	private Integer lrReplacedby;
	private Integer mapCode;
	private Date effectiveDate;
	private boolean isactive;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	// private Subdistrict subdistrict;
	private int villageCode;
	private int villageVersion;
	private int slc;
	private int dlc;
	private Integer flagCode;
	private int tlc;
	private int vlc;
	private String coordinate;
	private Boolean warningFlag;
	private String subdistrict;
	private String subdistrictNameEnglish;//add by anju Gupta for LGD 0003817
	private String mappedLbs;
	
	
	/**
	 * Add some entities for for draft village form on 20-08-2014. 
	 */
	private String opeartion_state;
	private String withoutRenameNameVillageList;
	private String fullContributedVillage;
	private String partFullFlag;
	private String renameNameVillageList;
	
	/**
	 * Add entity for Extended Department Functionality.
	 * @author Ripunj on 06-10-2014
	 */
	@Transient
	private Character operation_state;
	

	
	private Character operation_extend_flag;
	
	/**
	 * Draf Entry
	 * @return
	 */
	
	private String coverageType;
	
	
	private String partSubdistrict;
	
	
	private Integer minorVersion;
	
	

    @Column(name = "parent_hierarchy")
	@Type(type = "in.nic.pes.lgd.types.hibernate.JsonType")
	public JsonNode parentHierarchy;
	
	
	
	@Column(name = "warningflag")
	public Boolean getWarningFlag() {
		return warningFlag;
	}

	public void setWarningFlag(Boolean warningFlag) {
		this.warningFlag = warningFlag;
	}

	@Column(name = "coordinates")
	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	@Column(name = "flag_code")
	public Integer getFlagCode() {
		return flagCode;

	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	/*private Set<VillagePartsBySurveyno> villagePartsBySurveyno;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "village")
	public Set<VillagePartsBySurveyno> getVillagePartsBySurveyno() {
		return villagePartsBySurveyno;
	}

	public void setVillagePartsBySurveyno(
			Set<VillagePartsBySurveyno> villagePartsBySurveyno) {
		this.villagePartsBySurveyno = villagePartsBySurveyno;
	}*/

// TODO Remove unused code found by UCDetector
// 	public Village(VillagePK villagePK, String villageNameEnglish,
// 			String villageNameLocal, Date effectiveDate, boolean isactive,
// 			Date lastupdated, long lastupdatedby, Date createdon, long createdby) {
// 		super();
// 		this.villagePK = villagePK;
// 		this.villageNameEnglish = villageNameEnglish;
// 		this.villageNameLocal = villageNameLocal;
// 
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 	}

	public Village() {
	}

// TODO Remove unused code found by UCDetector
// 	public Village(int villageCode, int villageVersion) {
// 		this.villagePK = new VillagePK(villageCode, villageVersion);
// 	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "villageCode", column = @Column(name = "village_code", nullable = false)),
			@AttributeOverride(name = "villageVersion", column = @Column(name = "village_version", nullable = false)),
			@AttributeOverride(name = "minorVersion", column = @Column(name = "minor_version", nullable = false))})
	public VillagePK getVillagePK() {
		return villagePK;
	}

	public void setVillagePK(VillagePK villagePK) {
		this.villagePK = villagePK;
	}

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @Filter(name = "subdistrictFilter",condition="isactive = :isactive")
	 * 
	 * @JoinColumns({
	 * 
	 * @JoinColumn(name = "tlc", referencedColumnName = "tlc", nullable =
	 * false,insertable=false,updatable=false),
	 * 
	 * @JoinColumn(name = "isactive", referencedColumnName = "isactive",
	 * nullable = false,insertable=false,updatable=false) })
	 * //@Where(clause="subdistrict.isactive = true") public Subdistrict
	 * getSubdistrict() { return subdistrict; }
	 * 
	 * public void setSubdistrict(Subdistrict subdistrict) { this.subdistrict =
	 * subdistrict; }
	 */

	@Column(name = "village_code", insertable = false, updatable = false)
	public int getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
	}

	@Column(name = "village_version", insertable = false, updatable = false)
	public int getVillageVersion() {
		return villageVersion;
	}

	public void setVillageVersion(int villageVersion) {
		this.villageVersion = villageVersion;
	}
	
	
	@Column(name="minor_version", nullable = false,insertable = false, updatable = false)
	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	@Column(name = "village_name_english")
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}

	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}

	@Column(name = "village_name_local")
	public String getVillageNameLocal() {
		return villageNameLocal;
	}

	public void setVillageNameLocal(String villageNameLocal) {
		this.villageNameLocal = villageNameLocal;
	}

	@Column(name = "alias_english")
	public String getAliasEnglish() {
		return aliasEnglish;
	}

	public void setAliasEnglish(String aliasEnglish) {
		this.aliasEnglish = aliasEnglish;
	}

	@Column(name = "alias_local")
	public String getAliasLocal() {
		return aliasLocal;
	}

	public void setAliasLocal(String aliasLocal) {
		this.aliasLocal = aliasLocal;
	}

	@Basic(optional = false)
	@Column(name = "village_status")
	public String getVillageStatus() {
		return villageStatus;
	}

	public void setVillageStatus(String villageStatus) {
		this.villageStatus = villageStatus;
	}

	@Column(name = "census_2001_code")
	public String getCensus2001Code() {
		return census2001Code;
	}

	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}

	@Column(name = "census_2011_code")
	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	@Column(name = "sscode")
	public String getSscode() {
		return sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	/*
	 * @Column(name = "remarks") public String getRemarks() { return remarks; }
	 * 
	 * public void setRemarks(String remarks) { this.remarks = remarks; }
	 */
	/*
	 * @OneToOne
	 * 
	 * @JoinTable(name = "MapAttachment", joinColumns = {@JoinColumn(name =
	 * "mapCode")})
	 */

	@Transient
	public Integer getMapCode() {
		return mapCode;
	}

	public void setMapCode(Integer mapCode) {
		this.mapCode = mapCode;
	}

	/* @Basic(optional = false) */
	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effective_date", nullable = false)
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/* @Basic(optional = false) 
	@Column(name = "isactive", nullable = false, insertable = false, updatable = false)
	public boolean getIsactive() {
		return isactive;
	}*/

	// Added by Sushma Singh 22 oct 2019
	
	@Column(name = "isactive", nullable = false)
	public boolean getIsactive() {
		return isactive;
	}
	
	
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	/* @Basic(optional = false) */
	// @Temporal(TemporalType.TIMESTAMP)
	/**
	 * Modified on 05-11-2014 by ripunj
	 * @return
	 */
	@Column(name = "lastupdated", nullable = true)
	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	/* @Basic(optional = false) */
	@Column(name = "lastupdatedby", nullable = true)
	public long getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	/* @Basic(optional = false) */
	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", nullable = false)
	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	/* @Basic(optional = false) */
	@Column(name = "createdby")
	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	/*
	 * @Column(name = "subdistrict_code",insertable=false,updatable=false)
	 * public int getSubDistrictCode() { return subDistrictCode; }
	 * 
	 * public void setSubDistrictCode(int subDistrictCode) {
	 * this.subDistrictCode = subDistrictCode; }
	 * 
	 * @Column(name = "subdistrict_version",insertable=false,updatable=false)
	 * public int getSubDistrictVersion() { return subDistrictVersion; }
	 * 
	 * public void setSubDistrictVersion(int subDistrictVersion) {
	 * this.subDistrictVersion = subDistrictVersion; }
	 */

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

// TODO Remove unused code found by UCDetector
// 	public Village(VillagePK villagePK, String villageNameEnglish,
// 			String villageNameLocal, String aliasEnglish, String aliasLocal,
// 			String villageStatus, String census2001Code,
// 			Integer census2011Code, String sscode, Integer lrReplaces,
// 			Integer mapCode, Date effectiveDate, boolean isactive,
// 			Date lastupdated, long lastupdatedby, Date createdon,
// 			long createdby, Integer lrReplacedby, int tlc, int vlc) {
// 		super();
// 		this.villagePK = villagePK;
// 		this.villageNameEnglish = villageNameEnglish;
// 		this.villageNameLocal = villageNameLocal;
// 		this.aliasEnglish = aliasEnglish;
// 		this.aliasLocal = aliasLocal;
// 		this.villageStatus = villageStatus;
// 		this.census2001Code = census2001Code;
// 		this.census2011Code = census2011Code;
// 		this.sscode = sscode;
// 		this.lrReplaces = lrReplaces;
// 		this.mapCode = mapCode;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		// this.subdistrict = subdistrict;
// 		this.lrReplacedby = lrReplacedby;
// 		this.tlc = tlc;
// 		this.vlc = vlc;
// 	}

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

	@Column(name = "tlc")
	public int getTlc() {
		return tlc;
	}

	public void setTlc(int tlc) {
		this.tlc = tlc;
	}

	@Column(name = "vlc", nullable = false)
	public int getVlc() {
		return vlc;
	}

	public void setVlc(int vlc) {
		this.vlc = vlc;
	}

	@Column(name = "slc")
	public int getSlc() {
		return slc;
	}

	public void setSlc(int slc) {
		this.slc = slc;
	}

	@Column(name = "dlc")
	public int getDlc() {
		return dlc;
	}

	public void setDlc(int dlc) {
		this.dlc = dlc;
	}
	
	@Transient
	public String getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}
	@Transient
	public String getOpeartion_state() {
		return opeartion_state;
	}

	public void setOpeartion_state(String opeartion_state) {
		this.opeartion_state = opeartion_state;
	}
	@Transient
	public String getWithoutRenameNameVillageList() {
		return withoutRenameNameVillageList;
	}

	public void setWithoutRenameNameVillageList(String withoutRenameNameVillageList) {
		this.withoutRenameNameVillageList = withoutRenameNameVillageList;
	}
	@Transient
	public String getFullContributedVillage() {
		return fullContributedVillage;
	}

	public void setFullContributedVillage(String fullContributedVillage) {
		this.fullContributedVillage = fullContributedVillage;
	}
	@Transient
	public String getPartFullFlag() {
		return partFullFlag;
	}

	public void setPartFullFlag(String partFullFlag) {
		this.partFullFlag = partFullFlag;
	}
	@Transient
	public String getRenameNameVillageList() {
		return renameNameVillageList;
	}

	public void setRenameNameVillageList(String renameNameVillageList) {
		this.renameNameVillageList = renameNameVillageList;
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
	
	
	/*
	 * @OneToOne(optional = false, cascade = CascadeType.ALL, fetch =
	 * FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "map_attachment_code") public VillageMapAttachment
	 * getMapAttachCode() { return mapAttachCode; }
	 * 
	 * public void setMapAttachCode(VillageMapAttachment mapAttachCode) {
	 * this.mapAttachCode = mapAttachCode; }
	 */

	/*Added by Anju on 30/3/2015 for LGD 0003817*/
	@Transient
	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}
	/*end by ANju Gupta*/
	
	@Transient
	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

	@Transient
	public String getPartSubdistrict() {
		return partSubdistrict;
	}

	public void setPartSubdistrict(String partSubdistrict) {
		this.partSubdistrict = partSubdistrict;
	}
	
	@Transient
	public String getMappedLbs() {
		return mappedLbs;
	}

	public void setMappedLbs(String mappedLbs) {
		this.mappedLbs = mappedLbs;
	}
}
