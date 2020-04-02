package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.Block;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BlockDataForm {
	private Integer globalvillageId;
	private Integer globallocalbodyId;
	private String stateNameEnglish;
	private String districtNameEnglish;
	

	private String captchaAnswers;
	private String blockNameEnglish;
	private String blockNameEnglishch;
	private String blockNameLocal;
	private String blockNameLocalch;
	private String aliasEnglish;
	private String aliasEnglishch;
	private String aliasLocal;
	private String aliasLocalch;
	private String census2011Code;
	private String headquarterName;
	private Integer lrReplaces;
	private Integer lrReplacedby;
	private Integer flagCode;
	private Integer mapCode;
	private Integer blockDistrictCode;
	private String ssCode;
	private boolean isactive;
	private int blockCode;
	private int blockVersion;
	private int districtCode;
	private int districtVersion;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private Date effectiveDate;
	private int recordsLimit;
	private Integer headquarterCode;
	
	private String lati;
    private String longi;
    private String latitude;
   	private String longitude;
    private String mapUrl;
    
    private boolean correction;
    private String cordinate;
    private Integer orderCodecr;
	private String orderNocr;
	private Date orderDatecr;
	private Date ordereffectiveDatecr;
	private Date gazPubDatecr;
	private String orderPathcr;
	private MultipartFile filePathcr;
    private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;
	private String headquarterNameLocal;
	private Boolean warningFlag;
	private Integer minorVersion;
	
	public String getHeadquarterNameLocal() {
		return headquarterNameLocal;
	}
	public void setHeadquarterNameLocal(String headquarterNameLocal) {
		this.headquarterNameLocal = headquarterNameLocal;
	}
	private List<Block> listBlockDetail = new ArrayList<Block>();
	
	public int getRecordsLimit() {
		return recordsLimit;
	}
	public void setRecordsLimit(int recordsLimit) {
		this.recordsLimit = recordsLimit;
	}
	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}
	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}
	public String getBlockNameLocal() {
		return blockNameLocal;
	}
	public void setBlockNameLocal(String blockNameLocal) {
		this.blockNameLocal = blockNameLocal;
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
	public Integer getMapCode() {
		return mapCode;
	}
	public void setMapCode(Integer mapCode) {
		this.mapCode = mapCode;
	}
	public Integer getBlockDistrictCode() {
		return blockDistrictCode;
	}
	public void setBlockDistrictCode(Integer blockDistrictCode) {
		this.blockDistrictCode = blockDistrictCode;
	}
    
	public String getSsCode() {
		return ssCode;
	}
	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public int getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(int blockCode) {
		this.blockCode = blockCode;
	}
	public int getBlockVersion() {
		return blockVersion;
	}
	public void setBlockVersion(int blockVersion) {
		this.blockVersion = blockVersion;
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public List<Block> getListBlockDetail() {
		return listBlockDetail;
	}
	public void setListBlockDetail(List<Block> listBlockDetail) {
		this.listBlockDetail = listBlockDetail;
	}



	public String getCensus2011Code() {
		return census2011Code;
	}
	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
	public String getHeadquarterName() {
		return headquarterName;
	}
	public void setHeadquarterName(String headquarterName) {
		this.headquarterName = headquarterName;
	}
	public String getBlockNameEnglishch() {
		return blockNameEnglishch;
	}
	public void setBlockNameEnglishch(String blockNameEnglishch) {
		this.blockNameEnglishch = blockNameEnglishch;
	}
	public String getBlockNameLocalch() {
		return blockNameLocalch;
	}
	public void setBlockNameLocalch(String blockNameLocalch) {
		this.blockNameLocalch = blockNameLocalch;
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
	public String getCordinate() {
		return cordinate;
	}
	public void setCordinate(String cordinate) {
		this.cordinate = cordinate;
	}
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
	public MultipartFile getFilePathcr() {
		return filePathcr;
	}
	public void setFilePathcr(MultipartFile filePathcr) {
		this.filePathcr = filePathcr;
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
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	public String getCaptchaAnswers() {
		return captchaAnswers;
	}
	public void setCaptchaAnswers(String captchaAnswers) {
		this.captchaAnswers = captchaAnswers;
	}
	public Integer getGlobalvillageId() {
		return globalvillageId;
	}
	public void setGlobalvillageId(Integer globalvillageId) {
		this.globalvillageId = globalvillageId;
	}
	public Integer getGloballocalbodyId() {
		return globallocalbodyId;
	}
	public void setGloballocalbodyId(Integer globallocalbodyId) {
		this.globallocalbodyId = globallocalbodyId;
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
