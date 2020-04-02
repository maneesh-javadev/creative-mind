package in.nic.pes.lgd.bean;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "localbody", schema = "public") // When go for live change lgd_test to lgd_db
@NamedNativeQuery(query = " SELECT * FROM getlblist(:localBodyCode, :stateCode)",name="getLocalBodylist",resultClass=LocalGovtBody.class)
public class LocalGovtBody{
	
	@Id
	@Column(name = "local_body_code")
	private int localBodyCode;
	
	@Column(name = "local_body_version")
	private int localBodyVersion;
	
	@Column(name="alias_english")
	private String aliasEnglish;

	@Column(name="alias_local")
	private String aliasLocal;

	@Column(name="census_2001_code")
	private String census2001Code;

	@Column(name="census_2011_code")
	private String census2011Code;

	private String coordinates;

	private Long createdby;

	private Timestamp createdon;

	@Column(name="effective_date")
	private Timestamp effectiveDate;

	@Column(name="flag_code")
	private Integer flagCode;

	@Column(name="is_pesa")
	private Boolean isPesa;

	private Boolean isactive;

	private Boolean isdisturbed;

	private Boolean islocked;

	private Timestamp lastupdated;

	private Long lastupdatedby;

	@Column(name="lb_covered_region_code")
	private Integer lbCoveredRegionCode;

	@Column(name="lb_replacedby")
	private Integer lbReplacedby;

	@Column(name="lb_replaces")
	private Integer lbReplaces;

	private Integer lblc;

	@Column(name="local_body_name_english")
	private String localBodyNameEnglish;

	@Column(name="local_body_name_local")
	private String localBodyNameLocal;

	@Column(name="local_body_subtype_code")
	private Integer localBodySubtypeCode;

	@Column(name="local_body_type_code")
	private Integer localBodyTypeCode;

	@Column(name="map_attachment_code")
	private Integer mapAttachmentCode;

	@Column(name="parent_lblc")
	private Integer parentLblc;

	private Integer slc;

	private String sscode;

	@Column(name="transaction_id")
	private Integer transactionId;

	private Boolean warningflag;
	
	@Transient
	private String mapFileName;

	@Transient
	private String mapFileLocation;

	@Transient
	private String mapEntityType;

	@Transient
	private String govtOrderFileName;
	
	@Transient
	private String govtOrderFileLocation;
	
	@Transient
	private String orderNo;
	
	@Transient
	private Integer orderCode;
	
	@Transient
	private Date orderDate;
	
	@Transient
	private Date govOrderEffectiveDate;
	
	@Transient
	private Date gazPubDate;


	public LocalGovtBody() {
	}

	public int getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public String getAliasEnglish() {
		return this.aliasEnglish;
	}

	public void setAliasEnglish(String aliasEnglish) {
		this.aliasEnglish = aliasEnglish;
	}

	public String getAliasLocal() {
		return this.aliasLocal;
	}

	public void setAliasLocal(String aliasLocal) {
		this.aliasLocal = aliasLocal;
	}

	public String getCensus2001Code() {
		return this.census2001Code;
	}

	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}

	public String getCensus2011Code() {
		return this.census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	public String getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public Long getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	public Timestamp getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Integer getFlagCode() {
		return this.flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	public Boolean getIsPesa() {
		return this.isPesa;
	}

	public void setIsPesa(Boolean isPesa) {
		this.isPesa = isPesa;
	}

	public Boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Boolean getIsdisturbed() {
		return this.isdisturbed;
	}

	public void setIsdisturbed(Boolean isdisturbed) {
		this.isdisturbed = isdisturbed;
	}

	public Boolean getIslocked() {
		return this.islocked;
	}

	public void setIslocked(Boolean islocked) {
		this.islocked = islocked;
	}

	public Timestamp getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public Long getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(Long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Integer getLbCoveredRegionCode() {
		return this.lbCoveredRegionCode;
	}

	public void setLbCoveredRegionCode(Integer lbCoveredRegionCode) {
		this.lbCoveredRegionCode = lbCoveredRegionCode;
	}

	public Integer getLbReplacedby() {
		return this.lbReplacedby;
	}

	public void setLbReplacedby(Integer lbReplacedby) {
		this.lbReplacedby = lbReplacedby;
	}

	public Integer getLbReplaces() {
		return this.lbReplaces;
	}

	public void setLbReplaces(Integer lbReplaces) {
		this.lbReplaces = lbReplaces;
	}

	public Integer getLblc() {
		return this.lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}

	public String getLocalBodyNameEnglish() {
		return this.localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	public String getLocalBodyNameLocal() {
		return this.localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

	public Integer getLocalBodySubtypeCode() {
		return this.localBodySubtypeCode;
	}

	public void setLocalBodySubtypeCode(Integer localBodySubtypeCode) {
		this.localBodySubtypeCode = localBodySubtypeCode;
	}

	public Integer getLocalBodyTypeCode() {
		return this.localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public Integer getMapAttachmentCode() {
		return this.mapAttachmentCode;
	}

	public void setMapAttachmentCode(Integer mapAttachmentCode) {
		this.mapAttachmentCode = mapAttachmentCode;
	}

	public Integer getParentLblc() {
		return this.parentLblc;
	}

	public void setParentLblc(Integer parentLblc) {
		this.parentLblc = parentLblc;
	}

	public Integer getSlc() {
		return this.slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	public String getSscode() {
		return this.sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	public Integer getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Boolean getWarningflag() {
		return this.warningflag;
	}

	public void setWarningflag(Boolean warningflag) {
		this.warningflag = warningflag;
	}

	public int getLocalBodyVersion() {
		return localBodyVersion;
	}

	public void setLocalBodyVersion(int localBodyVersion) {
		this.localBodyVersion = localBodyVersion;
	}

	public String getMapFileName() {
		return mapFileName;
	}

	public void setMapFileName(String mapFileName) {
		this.mapFileName = mapFileName;
	}

	public String getMapFileLocation() {
		return mapFileLocation;
	}

	public void setMapFileLocation(String mapFileLocation) {
		this.mapFileLocation = mapFileLocation;
	}

	public String getMapEntityType() {
		return mapEntityType;
	}

	public void setMapEntityType(String mapEntityType) {
		this.mapEntityType = mapEntityType;
	}

	public String getGovtOrderFileName() {
		return govtOrderFileName;
	}

	public void setGovtOrderFileName(String govtOrderFileName) {
		this.govtOrderFileName = govtOrderFileName;
	}

	public String getGovtOrderFileLocation() {
		return govtOrderFileLocation;
	}

	public void setGovtOrderFileLocation(String govtOrderFileLocation) {
		this.govtOrderFileLocation = govtOrderFileLocation;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getGovOrderEffectiveDate() {
		return govOrderEffectiveDate;
	}

	public void setGovOrderEffectiveDate(Date govOrderEffectiveDate) {
		this.govOrderEffectiveDate = govOrderEffectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

}