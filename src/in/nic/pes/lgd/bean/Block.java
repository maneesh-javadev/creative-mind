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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * @author HCL
 * @modification by :Pooja
 */
@Entity
@Table(name = "block")
public class Block implements Serializable{
	private static final long serialVersionUID = 1L;

	private BlockPK blockPK;
	private String blockNameEnglish;
	private String blockNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private Integer lrReplaces;
	private Integer lrReplacedby;
	private Integer flagCode;
	//private Integer mapCode;
	private Integer blockDistrictCode;
	private String ssCode;
	private Date effectiveDate;
	private boolean isactive;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private int blockCode;
	private int blockVersion;
	//private int districtCode;
	//private int districtVersion;
	//private District district;
	private int dlc;
	private int blc;
	private String coordinates;
	private Boolean warningFlag;

	 /**
     * Add entity operationState for extended functionality of department.
     * @since on 06-10-2014
     */
	private Character operation_state;
	private Character operation_extend_flag;
	

	private Integer minorVersion;
	
	@Column(name = "parent_hierarchy")
	@Type(type = "in.nic.pes.lgd.types.hibernate.JsonType")
	public JsonNode parentHierarchy;
	
	@Column(name = "dlc")
	public int getDlc() {
		return dlc;
	}
	@Column(name = "coordinates")
	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public void setDlc(int dlc) {
		this.dlc = dlc;
	}

// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}

	public Block() {
	}

// TODO Remove unused code found by UCDetector
// 	public Block(BlockPK blockPK) {
// 		this.blockPK = blockPK;
// 	}

// TODO Remove unused code found by UCDetector
// 	public Block(BlockPK blockPK, String blockNameEnglish, Date effectiveDate,
// 			boolean isactive, Date lastupdated, long lastupdatedby,
// 			Date createdon, long createdby) {
// 		this.blockPK = blockPK;
// 		this.blockNameEnglish = blockNameEnglish;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 	}
	

// TODO Remove unused code found by UCDetector
// 	public Block(BlockPK blockPK, String blockNameEnglish,
// 			String blockNameLocal, String aliasEnglish, String aliasLocal,
// 			Integer lrReplaces, Integer lrReplacedby, Integer flagCode,
// 			Integer mapCode, Integer blockDistrictCode, String ssCode,
// 			Date effectiveDate, boolean isactive, Date lastupdated,
// 			long lastupdatedby, Date createdon, long createdby,
// 			District district) {
// 		super();
// 		this.blockPK = blockPK;
// 		this.blockNameEnglish = blockNameEnglish;
// 		this.blockNameLocal = blockNameLocal;
// 		this.aliasEnglish = aliasEnglish;
// 		this.aliasLocal = aliasLocal;
// 		this.lrReplaces = lrReplaces;
// 		this.lrReplacedby = lrReplacedby;
// 		this.flagCode = flagCode;
// 		this.mapCode = mapCode;
// 		this.blockDistrictCode = blockDistrictCode;
// 		this.ssCode = ssCode;
// 		this.effectiveDate = effectiveDate;
// 		this.isactive = isactive;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 	
// 		//this.district = district;
// 	}

// TODO Remove unused code found by UCDetector
// 	public Block(int blockCode, int blockVersion) {
// 		this.blockPK = new BlockPK(blockCode, blockVersion);
// 		this.blockCode = blockCode;
// 		this.blockVersion = blockVersion;
// 
// 	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "blockCode", column = @Column(name = "block_code", nullable = false)),
			@AttributeOverride(name = "blockVersion", column = @Column(name = "block_version", nullable = false)),
			@AttributeOverride(name = "minorVersion", column = @Column(name = "minor_version", nullable = false))})
	public BlockPK getBlockPK() {
		return blockPK;
	}

	public void setBlockPK(BlockPK blockPK) {
		this.blockPK = blockPK;
	}

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "district_code", referencedColumnName = "district_code", nullable = false),
			@JoinColumn(name = "district_version", referencedColumnName = "district_version", nullable = false) })
	public District getDistrict() {
		return district;
	}*/

	/*public void setDistrict(District district) {
		this.district = district;
	}
	*/
	
	@Column(name = "block_code", nullable = false,insertable=false,updatable=false)
	public int getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(int blockCode) {
		this.blockCode = blockCode;
	}

	@Column(name = "block_version", nullable = false,insertable=false,updatable=false)
	public int getBlockVersion() {
		return blockVersion;
	}

	public void setBlockVersion(int blockVersion) {
		this.blockVersion = blockVersion;
	}
	
	@Column(name="minor_version", nullable = false,insertable = false, updatable = false)
	public Integer getMinorVersion() {
		return minorVersion;
	}
	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}
	

	@Column(name = "block_name_english")
	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}

	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}

	@Column(name = "block_name_local")
	public String getBlockNameLocal() {
		return blockNameLocal;
	}

	public void setBlockNameLocal(String blockNameLocal) {
		this.blockNameLocal = blockNameLocal;
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

	@Column(name = "lr_replaces")
	public Integer getLrReplaces() {
		return lrReplaces;
	}

	public void setLrReplaces(Integer lrReplaces) {
		this.lrReplaces = lrReplaces;
	}

	@Column(name = "flag_code")
	public Integer getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	@Column(name = "lr_replacedby")
	public Integer getLrReplacedby() {
		return lrReplacedby;
	}

	public void setLrReplacedby(Integer lrReplacedby) {
		this.lrReplacedby = lrReplacedby;
	}

	/*
	 * @Column(name = "map_attachment_code") public Integer getMapCode() { return
	 * mapCode; }
	 * 
	 * public void setMapCode(Integer mapCode) { this.mapCode = mapCode; }
	 * 
	 * @Column(name = "block_district_code") public Integer getBlockDistrictCode() {
	 * return blockDistrictCode; }
	 */
	public void setBlockDistrictCode(Integer blockDistrictCode) {
		this.blockDistrictCode = blockDistrictCode;
	}

	@Column(name = "ss_code")
	public String getSsCode() {
		return ssCode;
	}

	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}

	@Basic(optional = false)
	@Column(name = "effective_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "isactive")
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
    
	@Column(name = "blc")
	public int getBlc() {
		return blc;
	}

	public void setBlc(int blc) {
		this.blc = blc;
	}

	/*public District getDistrict() {
		return district;
	}*/

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
	
	@Column(name = "warningflag")
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
	
	

	

	/*@Column(name = "district_code", nullable = false,insertable=false,updatable=false)
	public int getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}

	@Column(name = "district_version", nullable = false,insertable=false,updatable=false)
	public int getDistrictVersion() {
		return districtVersion;
	}

	public void setDistrictVersion(int districtVersion) {
		this.districtVersion = districtVersion;
	}*/
	
	

}
