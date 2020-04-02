package in.nic.pes.lgd.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class SubdistrictDataForm {
	private String subdistrictNameEnglish;
	private String subdistrictNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private String subdistrictNameEnglishch;
	private String subdistrictNameLocalch;
	private String aliasEnglishch;
	private String aliasLocalch;
	private Integer mapLandregionCode;
	private String census2001Code;
	private String census2011Code;
	private String headquarterName;
	private Integer headquarterCode;
	private String headquarterNameLocal;
	private String sscode;
	private Integer lrReplaces;
	private Integer lrReplacedby;
	private Date effectiveDate;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private boolean isactive;
	private int subdistrictCode;
	private int subdistrictVersion;
	private Integer flagCode;
	private String lati;
	private String longi;
	private String latitude;
	private String longitude;
	private String mapUrl;
	private boolean correction;
	private String cordinate;
	private Integer tlc;
	// governmentOrder
	private Integer orderCodecr;
	private String orderNocr;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date orderDatecr;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date ordereffectiveDatecr;
	@DateTimeFormat(pattern="dd-MM-yyyy")

	private Date gazPubDatecr;
	private String orderPathcr;
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;
	private Boolean warningFlag;
	private Integer minorVersion;

	public String getCordinate() {
		return cordinate;
	}

	public void setCordinate(String cordinate) {
		this.cordinate = cordinate;
	}

	public String getSubdistrictNameEnglishch() {
		return subdistrictNameEnglishch;
	}

	public void setSubdistrictNameEnglishch(String subdistrictNameEnglishch) {
		this.subdistrictNameEnglishch = subdistrictNameEnglishch;
	}

	public String getSubdistrictNameLocalch() {
		return subdistrictNameLocalch;
	}

	public String getHeadquarterName() {
		return headquarterName;
	}

	public void setHeadquarterName(String headquarterName) {
		this.headquarterName = headquarterName;
	}

	public void setSubdistrictNameLocalch(String subdistrictNameLocalch) {
		this.subdistrictNameLocalch = subdistrictNameLocalch;
	}

	public String getAliasEnglishch() {
		return aliasEnglishch;
	}

	public void setAliasEnglishch(String aliasEnglishch) {
		this.aliasEnglishch = aliasEnglishch;
	}

	public String getAliasLocalch() {
		return aliasLocalch;
	}

	public void setAliasLocalch(String aliasLocalch) {
		this.aliasLocalch = aliasLocalch;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	public String getSubdistrictNameLocal() {
		return subdistrictNameLocal;
	}

	public void setSubdistrictNameLocal(String subdistrictNameLocal) {
		this.subdistrictNameLocal = subdistrictNameLocal;
	}

	public String getAliasEnglish() {
		return aliasEnglish;
	}

	public void setAliasEnglish(String aliasEnglish) {
		this.aliasEnglish = aliasEnglish;
	}

	public String getAliasLocal() {
		return aliasLocal;
	}

	public void setAliasLocal(String aliasLocal) {
		this.aliasLocal = aliasLocal;
	}

	public Integer getMapLandregionCode() {
		return mapLandregionCode;
	}

	public void setMapLandregionCode(Integer mapLandregionCode) {
		this.mapLandregionCode = mapLandregionCode;
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

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrdereffectiveDate() {
		return ordereffectiveDate;
	}

	public void setOrdereffectiveDate(Date ordereffectiveDate) {
		this.ordereffectiveDate = ordereffectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	public String getOrderPath() {
		return orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	public String getSscode() {
		return sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	public Integer getLrReplaces() {
		return lrReplaces;
	}

	public void setLrReplaces(Integer lrReplaces) {
		this.lrReplaces = lrReplaces;
	}

	public Integer getLrReplacedby() {
		return lrReplacedby;
	}

	public void setLrReplacedby(Integer lrReplacedby) {
		this.lrReplacedby = lrReplacedby;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	public long getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public int getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(int subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public int getSubdistrictVersion() {
		return subdistrictVersion;
	}

	public void setSubdistrictVersion(int subdistrictVersion) {
		this.subdistrictVersion = subdistrictVersion;
	}

	public Integer getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	public String getLati() {
		return lati;
	}

	public void setLati(String lati) {
		this.lati = lati;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public boolean isCorrection() {
		return correction;
	}

	public void setCorrection(boolean correction) {
		this.correction = correction;
	}

	public int getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(int district_code) {
		this.district_code = district_code;
	}

	public int getDistrict_version() {
		return district_version;
	}

	public void setDistrict_version(int district_version) {
		this.district_version = district_version;
	}

	private int district_code;
	private int district_version;

	public Integer getHeadquarterCode() {
		return headquarterCode;
	}

	public void setHeadquarterCode(Integer headquarterCode) {
		this.headquarterCode = headquarterCode;
	}

	public Integer getOrderCodecr() {
		return orderCodecr;
	}

	public void setOrderCodecr(Integer orderCodecr) {
		this.orderCodecr = orderCodecr;
	}

	public String getOrderNocr() {
		return orderNocr;
	}

	public void setOrderNocr(String orderNocr) {
		this.orderNocr = orderNocr;
	}

	public Date getOrderDatecr() {
		return orderDatecr;
	}

	public void setOrderDatecr(Date orderDatecr) {
		this.orderDatecr = orderDatecr;
	}

	public Date getOrdereffectiveDatecr() {
		return ordereffectiveDatecr;
	}

	public void setOrdereffectiveDatecr(Date ordereffectiveDatecr) {
		this.ordereffectiveDatecr = ordereffectiveDatecr;
	}

	public Date getGazPubDatecr() {
		return gazPubDatecr;
	}

	public void setGazPubDatecr(Date gazPubDatecr) {
		this.gazPubDatecr = gazPubDatecr;
	}

	public String getOrderPathcr() {
		return orderPathcr;
	}

	public void setOrderPathcr(String orderPathcr) {
		this.orderPathcr = orderPathcr;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getHeadquarterNameLocal() {
		return headquarterNameLocal;
	}

	public void setHeadquarterNameLocal(String headquarterNameLocal) {
		this.headquarterNameLocal = headquarterNameLocal;
	}

	public Integer getTlc() {
		return tlc;
	}

	public void setTlc(Integer tlc) {
		this.tlc = tlc;
	}

	public Boolean getWarningFlag() {
		return warningFlag;
	}

	public void setWarningFlag(Boolean warningFlag) {
		this.warningFlag = warningFlag;
	}

	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}
	
	

}
