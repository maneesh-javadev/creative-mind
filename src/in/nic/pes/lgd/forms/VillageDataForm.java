package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Village;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class VillageDataForm {

	private int villageCode;
	private int villageVersion;
	private String villageNameEnglish;
	private String villageNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private String villageNameEnglishCh;
	private String villageNameLocalCh;
	private String aliasEnglishCh;
	private String aliasLocalCh;
	private String villageStatus;
	private String census2001Code;
	private String census2011Code;
	private String sscode;
	private String remarks;
	private Integer flagCode;
	/* private Integer lrPartCode; */
	private String villageType;
	private Integer lrReplaces;
	private Integer lrReplacedby;
	private Integer mapCode;
	private Date effectiveDate;
	private boolean isactive;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private int subdistrictCode;
	private int subdistrictVersion;
    private String stateNameEnglish;
	private String districtNameEnglish;
	private String subdistrictNameEnglish;
	private String cordinate;
	private String latitude;
	private String lati;
    private String longi;
	private String longitude;
	private String imagePath;
	private boolean correction;
	private String correctionRadio;
	
	// governmentOrder
	
	private String govtOrder;
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;
	private Integer orderCodecr;
	private String orderNocr;
	private Date orderDatecr;
	private Date ordereffectiveDatecr;
	private Date gazPubDatecr;
	private String orderPathcr;
	private int subdistrictCodes;
	private int pageRows;
	private int limit;
	private int offset;
	private int direction;
	private String pageType;
	private String hiddenCoordinates;
	private String captchaAnswer;
	private String captchaAnswers;
	private String noOrderRecord;
	private String noAttachmentRecord;
	private String noMapRecord;
	private String villStat;
	private boolean warningflag;   
	/** Added on 21-08-2014
	 * Add Attribute to Upload Map for Draft Village on 16-08-2014
	 */
	private String upLoadMap;
	/**
	 * Added on 23-08-2014
	 * For check the toggle for Contributing Villages or Ulbs on 16-08-2014
	 */
	private String existVilOrUlbFlag;
	/**
	 * For Draft Village Functionality
	 * @author Ripunj on 24-09-2014
	 */
	private String isExistGovtOrder;
	
	/**
	 * For REname Draft Mode
	 * @author Ripunj on 10-03-2015
	 */
	private Integer renameVillageCode;
	
	/**
	 * For Invalidate Draft Mode
	 * @author Ripunj on 16-03-2015
	 */
	private String invalidateVillageList;
	private Integer draftVillageCode; 
	
	/**
	 * For Manage Draft Mode
	 * @author Pooja on 24-07-2015
	 */
	
	
	private String ulbCodeValid;
	
	
	private String isPesa;
	
	private Integer districtCode;
	
	private List<District> districtList;
	
	private Integer stateCode;
	
	private Boolean isDataDiv;
	
	private Integer minorVersion;
	
	//Element Added to include a new combo in Correction of Village---Arnab--DT:22/01/2013
	public String getVillStat() {
		return villStat;
	}
	public void setVillStat(String villStat) {
		this.villStat = villStat;
	}
	//End
	public String getCaptchaAnswer() {
		return captchaAnswer;
	}
	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}
	public String getCaptchaAnswers() {
		return captchaAnswers;
	}
	public void setCaptchaAnswers(String captchaAnswers) {
		this.captchaAnswers = captchaAnswers;
	}

	//Code by Arnab  Start
	private Integer globalvillageId;
	private Integer villageId;
	
	public Integer getVillageId() {
		return villageId;
	}
	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}
	public Integer getGlobalvillageId() {
		return globalvillageId;
	}
	public void setGlobalvillageId(Integer globalvillageId) {
		this.globalvillageId = globalvillageId;
	}
	
	//Code by Arnab  End
	
	public String getCorrectionRadio() {
		return correctionRadio;
	}

	public void setCorrectionRadio(String correctionRadio) {
		this.correctionRadio = correctionRadio;
	}

	private String code;
	private int recordsLimit;
	private List<Village> listVillageDetail = new ArrayList<Village>();
	private List<StateWiseEntityDetails> stateWiseEntityDetails = new ArrayList<StateWiseEntityDetails>();

	

	
	
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

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	public String getOrderPath() {
		return orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
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

	public String getNoOrderRecord() {
		return noOrderRecord;
	}
	public void setNoOrderRecord(String noOrderRecord) {
		this.noOrderRecord = noOrderRecord;
	}
	public String getNoAttachmentRecord() {
		return noAttachmentRecord;
	}
	public void setNoAttachmentRecord(String noAttachmentRecord) {
		this.noAttachmentRecord = noAttachmentRecord;
	}
	public String getNoMapRecord() {
		return noMapRecord;
	}
	public void setNoMapRecord(String noMapRecord) {
		this.noMapRecord = noMapRecord;
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

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}

	public Integer getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
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

	public List<Village> getListVillageDetail() {
		return listVillageDetail;
	}

	public void setListVillageDetail(List<Village> listVillageDetail) {
		this.listVillageDetail = listVillageDetail;
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

	public String getCordinate() {
		return cordinate;
	}

	public void setCordinate(String cordinate) {
		this.cordinate = cordinate;
		/*if (this.hiddenCoordinates == null)
			this.hiddenCoordinates = cordinate;*/
	}
	
	public void sethiddenCoordinates(String cordinate) {
			this.hiddenCoordinates = cordinate;
	}
	
	public String gethiddenCoordinates()
	{
		return hiddenCoordinates;
	}

	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}

	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}

	public String getVillageNameLocal() {
		return villageNameLocal;
	}

	public void setVillageNameLocal(String villageNameLocal) {
		this.villageNameLocal = villageNameLocal;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/*
	 * public Integer getLrPartCode() { return lrPartCode; }
	 * 
	 * public void setLrPartCode(Integer lrPartCode) { this.lrPartCode =
	 * lrPartCode; }
	 */

	public Integer getMapCode() {
		return mapCode;
	}

	public void setMapCode(Integer mapCode) {
		this.mapCode = mapCode;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean getIsactive() {
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

	public int getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
	}

	public int getVillage_version() {
		return villageVersion;
	}

	public void setVillage_version(int village_version) {
		this.villageVersion = village_version;
	}

	public boolean isCorrection() {
		return correction;
	}

	public void setCorrection(boolean correction) {
		this.correction = correction;
	}

	public String getVillageNameEnglishCh() {
		return villageNameEnglishCh;
	}

	public void setVillageNameEnglishCh(String villageNameEnglishCh) {
		this.villageNameEnglishCh = villageNameEnglishCh;
	}

	public String getVillageNameLocalCh() {
		return villageNameLocalCh;
	}

	public void setVillageNameLocalCh(String villageNameLocalCh) {
		this.villageNameLocalCh = villageNameLocalCh;
	}

	public String getAliasEnglishCh() {
		return aliasEnglishCh;
	}

	public void setAliasEnglishCh(String aliasEnglishCh) {
		this.aliasEnglishCh = aliasEnglishCh;
	}

	public String getAliasLocalCh() {
		return aliasLocalCh;
	}

	public void setAliasLocalCh(String aliasLocalCh) {
		this.aliasLocalCh = aliasLocalCh;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getVillageVersion() {
		return villageVersion;
	}

	public void setVillageVersion(int villageVersion) {
		this.villageVersion = villageVersion;
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

	public int getPageRows() {
		return this.pageRows;
	}

	public void setPageRows(int pageRows) 
	{
		this.setLimit(pageRows); 
		this.pageRows = pageRows;
	}

	public int getOffset() {
		return this.offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	
	

	public List<StateWiseEntityDetails> getStateWiseEntityDetails() {
		return stateWiseEntityDetails;
	}

	public void setStateWiseEntityDetails(
			List<StateWiseEntityDetails> stateWiseEntityDetails) {
		this.stateWiseEntityDetails = stateWiseEntityDetails;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public int getSubdistrictCodes() {
		return subdistrictCodes;
	}

	public void setSubdistrictCodes(int subdistrictCodes) {
		this.subdistrictCodes = subdistrictCodes;
	}
	public boolean isWarningflag() {
		return warningflag;
	}
	public void setWarningflag(boolean warningflag) {
		this.warningflag = warningflag;
	}
	public String getVillageStatus() {
		return villageStatus;
	}
	public void setVillageStatus(String villageStatus) {
		this.villageStatus = villageStatus;
	}
	public String getVillageType() {
		return villageType;
	}
	public void setVillageType(String villageType) {
		this.villageType = villageType;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getGovtOrder() {
		return govtOrder;
	}
	public void setGovtOrder(String govtOrder) {
		this.govtOrder = govtOrder;
	}
	public String getExistVilOrUlbFlag() {
		return existVilOrUlbFlag;
	}
	public void setExistVilOrUlbFlag(String existVilOrUlbFlag) {
		this.existVilOrUlbFlag = existVilOrUlbFlag;
	}
	public String getUpLoadMap() {
		return upLoadMap;
	}
	public void setUpLoadMap(String upLoadMap) {
		this.upLoadMap = upLoadMap;
	}
	public String getHiddenCoordinates() {
		return hiddenCoordinates;
	}
	public void setHiddenCoordinates(String hiddenCoordinates) {
		this.hiddenCoordinates = hiddenCoordinates;
	}
	public String getIsExistGovtOrder() {
		return isExistGovtOrder;
	}
	public void setIsExistGovtOrder(String isExistGovtOrder) {
		this.isExistGovtOrder = isExistGovtOrder;
	}
	public Integer getRenameVillageCode() {
		return renameVillageCode;
	}
	public void setRenameVillageCode(Integer renameVillageCode) {
		this.renameVillageCode = renameVillageCode;
	}
	public String getInvalidateVillageList() {
		return invalidateVillageList;
	}
	public void setInvalidateVillageList(String invalidateVillageList) {
		this.invalidateVillageList = invalidateVillageList;
	}
	public Integer getDraftVillageCode() {
		return draftVillageCode;
	}
	public void setDraftVillageCode(Integer draftVillageCode) {
		this.draftVillageCode = draftVillageCode;
	}
	public String getUlbCodeValid() {
		return ulbCodeValid;
	}
	public void setUlbCodeValid(String ulbCodeValid) {
		this.ulbCodeValid = ulbCodeValid;
	}
	public String getIsPesa() {
		return isPesa;
	}
	public void setIsPesa(String isPesa) {
		this.isPesa = isPesa;
	}
	public Integer getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}
	public List<District> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<District> districtList) {
		this.districtList = districtList;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public Boolean getIsDataDiv() {
		return isDataDiv;
	}
	public void setIsDataDiv(Boolean isDataDiv) {
		this.isDataDiv = isDataDiv;
	}
	public Integer getMinorVersion() {
		return minorVersion;
	}
	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	
	
	

}
