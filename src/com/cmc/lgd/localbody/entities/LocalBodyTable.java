package com.cmc.lgd.localbody.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "localbody", schema = "public")
public class LocalBodyTable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "local_body_code")
	private Integer localBodyCode;

	@Column(name = "local_body_version")
	private Integer localBodyVersion;
	
	@Column(name = "minor_version")
	private Integer minorVersion;

	@Column(name = "local_body_name_english")
	private String localBodyNameEnglish;

	@Column(name = "local_body_name_local")
	private String localBodyNameLocal;

	@Column(name = "local_body_type_code")
	private Integer localBodyTypeCode;

	@Column(name = "local_body_subtype_code")
	private Integer localBodySubtypeCode;

	@Column(name = "parent_lblc")
	private Integer parentLocalBodyCode;

	@Column(name = "slc")
	private Integer slc;

	@Column(name = "alias_english")
	private String localBodyAliasEnglish;

	@Column(name = "alias_local")
	private String localBodyAliasLocal;

	@Column(name = "lb_replaces")
	private Integer lbReplaces;

	@Column(name = "lb_covered_region_code")
	private Integer lbCoveredRegionCode;

	@Column(name = "effective_date")
	private Timestamp effectiveDate;

	@Column(name = "lastupdated")
	private Timestamp lastupdated;

	@Column(name = "lastupdatedby")
	private Long lastupdatedby;

	@Column(name = "createdon")
	private Timestamp createdon;

	@Column(name = "createdby")
	private Long createdby;

	@Column(name = "isdisturbed")
	private Boolean isdisturbed;

	@Column(name = "isactive")
	private Boolean isactive;

	/*
	 * @Column(name = "map_attachment_code") private Integer mapAttachmentCode;
	 */

	@Column(name = "flag_code")
	private Integer flagCode;

	@Column(name = "lb_replacedby")
	private Integer lbReplacedby;

	@Column(name = "is_pesa")
	private Character isPesa;

	@Column(name = "islocked")
	private Boolean islocked;

	@Column(name = "transaction_id")
	private Integer transactionId;

	@Column(name = "lblc")
	private Integer lblc;

	@Column(name = "coordinates")
	private String coordinates;

	@Column(name = "warningflag")
	private Boolean warningflag;

	@Column(name = "census_2001_code")
	private String census2001Code;

	@Column(name = "census_2011_code")
	private String census2011Code;

	@Column(name = "sscode")
	private String stateSpecificCode;
	
	@Column(name = "parent_hierarchy")
	@Type(type = "in.nic.pes.lgd.types.hibernate.JsonType")
	public JsonNode parentHierarchy;

	public LocalBodyTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public Integer getLocalBodyVersion() {
		return localBodyVersion;
	}

	public void setLocalBodyVersion(Integer localBodyVersion) {
		this.localBodyVersion = localBodyVersion;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public Integer getLocalBodySubtypeCode() {
		return localBodySubtypeCode;
	}

	public void setLocalBodySubtypeCode(Integer localBodySubtypeCode) {
		this.localBodySubtypeCode = localBodySubtypeCode;
	}
	
	public Integer getParentLocalBodyCode() {
		return parentLocalBodyCode;
	}

	public void setParentLocalBodyCode(Integer parentLocalBodyCode) {
		this.parentLocalBodyCode = parentLocalBodyCode;
	}

	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	public String getLocalBodyAliasEnglish() {
		return localBodyAliasEnglish;
	}

	public void setLocalBodyAliasEnglish(String localBodyAliasEnglish) {
		this.localBodyAliasEnglish = localBodyAliasEnglish;
	}

	public String getLocalBodyAliasLocal() {
		return localBodyAliasLocal;
	}

	public void setLocalBodyAliasLocal(String localBodyAliasLocal) {
		this.localBodyAliasLocal = localBodyAliasLocal;
	}

	public Integer getLbReplaces() {
		return lbReplaces;
	}

	public void setLbReplaces(Integer lbReplaces) {
		this.lbReplaces = lbReplaces;
	}

	public Integer getLbCoveredRegionCode() {
		return lbCoveredRegionCode;
	}

	public void setLbCoveredRegionCode(Integer lbCoveredRegionCode) {
		this.lbCoveredRegionCode = lbCoveredRegionCode;
	}

	public Timestamp getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public Long getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(Long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Timestamp getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	public Boolean getIsdisturbed() {
		return isdisturbed;
	}

	public void setIsdisturbed(Boolean isdisturbed) {
		this.isdisturbed = isdisturbed;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	/*
	 * public Integer getMapAttachmentCode() { return mapAttachmentCode; }
	 * 
	 * public void setMapAttachmentCode(Integer mapAttachmentCode) {
	 * this.mapAttachmentCode = mapAttachmentCode; }
	 */
	public Integer getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	public Integer getLbReplacedby() {
		return lbReplacedby;
	}

	public void setLbReplacedby(Integer lbReplacedby) {
		this.lbReplacedby = lbReplacedby;
	}

	

	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	public Character getIsPesa() {
		return isPesa;
	}

	public void setIsPesa(Character isPesa) {
		this.isPesa = isPesa;
	}

	public Boolean getIslocked() {
		return islocked;
	}

	public void setIslocked(Boolean islocked) {
		this.islocked = islocked;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public Boolean getWarningflag() {
		return warningflag;
	}

	public void setWarningflag(Boolean warningflag) {
		this.warningflag = warningflag;
	}

	public String getCensus2001Code() {
		return census2001Code;
	}

	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	public String getStateSpecificCode() {
		return stateSpecificCode;
	}

	public void setStateSpecificCode(String stateSpecificCode) {
		this.stateSpecificCode = stateSpecificCode;
	}

// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}
}