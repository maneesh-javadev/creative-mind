package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.District;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


public class DistrictDataForm {
	private int stateVersion;
	private int stateCode;
	private int districtCode;
	private int districtVersion;
	private String districtNameEnglish;
	private String districtNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private String districtNameEnglishch;
	private String districtNameLocalch;
	private String aliasEnglishch;
	private String aliasLocalch;
	private Integer mapCode;
	private String census2001Code;
	private String census2011Code;
	private String sscode;
    private Integer lrReplaces;
	private Integer lrReplacedby;
	private Integer flagCode;
	private Date effectiveDate;
	private int recordsLimit;
	private String code;
	private String mapUrl;
	private boolean correction;
	private String cordinate;
	private Integer headquarterCode;
	private Integer headquarterVersion;
	private String headquarterName;
	private String headquarterNameLocal;
	private boolean warningflag;    
	public String getHeadquarterNameLocal() {
		return headquarterNameLocal;
	}
	public void setHeadquarterNameLocal(String headquarterNameLocal) {
		this.headquarterNameLocal = headquarterNameLocal;
	}
	private boolean isactive;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private String latitude;
	private String longitude;
	private String lati;
    private String longi;
	//goverment Order
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
	private Integer tlc;
	private Integer minorVersion;
	public Integer getTlc() {
		return tlc;
	}
	public void setTlc(Integer tlc) {
		this.tlc = tlc;
	}
	private List<District> listDistrictDetail = new ArrayList<District>();
	
	
	
	public int getStateVersion() {
		return stateVersion;
	}
	public void setStateVersion(int stateVersion) {
		this.stateVersion = stateVersion;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public int getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}
	public int getDistrictVersion() {
		return districtVersion;
	}
	public void setDistrictVersion(int districtVersion) {
		this.districtVersion = districtVersion;
	}
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	public String getDistrictNameLocal() {
		return districtNameLocal;
	}
	public void setDistrictNameLocal(String districtNameLocal) {
		this.districtNameLocal = districtNameLocal;
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
	public Integer getMapCode() {
		return mapCode;
	}
	public void setMapCode(Integer mapCode) {
		this.mapCode = mapCode;
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
	public Integer getFlagCode() {
		return flagCode;
	}
	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}
	public Date getOrdereffectiveDate() {
		return ordereffectiveDate;
	}
	public void setOrdereffectiveDate(Date ordereffectiveDate) {
		this.ordereffectiveDate = ordereffectiveDate;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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
	public int getRecordsLimit() {
		return recordsLimit;
	}
	public void setRecordsLimit(int recordsLimit) {
		this.recordsLimit = recordsLimit;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getCordinate() {
		return cordinate;
	}
	public void setCordinate(String cordinate) {
		this.cordinate = cordinate;
	}
	public String getDistrictNameEnglishch() {
		return districtNameEnglishch;
	}
	public void setDistrictNameEnglishch(String districtNameEnglishch) {
		this.districtNameEnglishch = districtNameEnglishch;
	}
	public String getDistrictNameLocalch() {
		return districtNameLocalch;
	}
	public void setDistrictNameLocalch(String districtNameLocalch) {
		this.districtNameLocalch = districtNameLocalch;
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
	public MultipartFile getFilePath() {
		return filePath;
	}
	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}
	public Integer getHeadquarterCode() {
		return headquarterCode;
	}
	public void setHeadquarterCode(Integer headquarterCode) {
		this.headquarterCode = headquarterCode;
	}
	public String getHeadquarterName() {
		return headquarterName;
	}
	public void setHeadquarterName(String headquarterName) {
		this.headquarterName = headquarterName;
	}
	public List<District> getListDistrictDetail() {
		return listDistrictDetail;
	}
	public void setListDistrictDetail(List<District> listDistrictDetail) {
		this.listDistrictDetail = listDistrictDetail;
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
	public Integer getHeadquarterVersion() {
		return headquarterVersion;
	}
	public void setHeadquarterVersion(Integer headquarterVersion) {
		this.headquarterVersion = headquarterVersion;
	}
	public boolean isWarningflag() {
		return warningflag;
	}
	public void setWarningflag(boolean warningflag) {
		this.warningflag = warningflag;
	}
	public Integer getMinorVersion() {
		return minorVersion;
	}
	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}
	
}
