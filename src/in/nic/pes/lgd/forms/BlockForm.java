package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.BlockHistory;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class BlockForm {
	@NotEmpty(message = "Name must not not be blank.")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Block Name must be character with or without spaces")
	private String blockNameEnglish;
	private String blockNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private String aliasLocalch;
	private String aliasEnglishch;
	private String blockNameLocalch;
	private Integer lrReplaces;
	private Integer lrReplacedby;
	private Integer flagCode;
	private Integer limit;
	//Code Added by Arnab   Start
	private Integer blockId;
	private String listformat;
	private String noMapRecord;
	private String noAttachmentRecord;
	private String noOrderRecord;
	private String cordinate;
	private Boolean warningFlag;
	private String govtfilecount;
	private Integer dlc;
	private String ulbListFormat;
	private Integer userId;
	private Integer stateCode;
	 public String getListformat() {
		return listformat;
	}

	public void setListformat(String listformat) {
		this.listformat = listformat;
	}

	private String correctionRadio;
	 private Integer globalblockId;
	 private String captchaAnswer;
		private String captchaAnswers;

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

	public String getCorrectionRadio() {
		return correctionRadio;
	}

	public void setCorrectionRadio(String correctionRadio) {
		this.correctionRadio = correctionRadio;
	}

	public Integer getGlobalblockId() {
		return globalblockId;
	}

	public void setGlobalblockId(Integer globalblockId) {
		this.globalblockId = globalblockId;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}
	
	//Code Added by Arnab   End

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
		this.limit = pageRows;
	}

	private Integer mapCode;
	private Integer blockDistrictCode;
	private String ssCode;
	private boolean isactive;
	private int blockCode;
	private int blockVersion;
	@NotEmpty(message = "District name must not not be blank. Select at least one from the list.")
	private String districtCode;
	private String targetDistrictCodeYes;
	private String targetDistrictCodeNo;
	
	private String requiredTitle1;
	private String maxFileLimit1;
	private String[] fileTitle1;
	private List<CommonsMultipartFile> attachFile1;
	private List<CommonsMultipartFile> attachFile2;
	private String requiredTitle2;
	private String maxFileLimit2;
	private String[] fileTitle2;
	private String VillagesList;
	private String UlbLists;
	private String contributedblockUlb;
	
	

	public String getContributedblockUlb() {
		return contributedblockUlb;
	}

	public void setContributedblockUlb(String contributedblockUlb) {
		this.contributedblockUlb = contributedblockUlb;
	}

	public String getVillagesList() {
		return VillagesList;
	}

	public void setVillagesList(String villagesList) {
		VillagesList = villagesList;
	}

	public String getUlbLists() {
		return UlbLists;
	}

	public void setUlbLists(String ulbLists) {
		UlbLists = ulbLists;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	private int districtVersion;

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	private String latitude;
	private String longitude;
	private String mapUrl;
	private boolean correction;
	private Integer orderCode;
	private String orderNo;
	private String orderNocr;
	private Date orderDate;
	private Date effectiveDate;
	private Date orderDatecr;
	private Date ordereffectiveDatecr;
	private String blockNameEnglishch;
	private Date gazPubDatecr;
	private String orderPathcr;
	private char operation;
	private String govtOrderConfig;
	private String templateList;
	private String pageType;
	private String stateNameEnglish;

	private int offset;

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	private Date ordereffectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private String code;
	private int recordsLimit;
	private String fileMapUpLoad;
	private MultipartFile filePathcr;
	private MultipartFile filePath;
	private String census2011Code;
	private String headquarterName;
	private String headquarterNameLocal;
	private String blockList;
	private String contributedBlocks;
	private String contributedVillages;
	private boolean contriBlock;
	private String addAnotherBlock;
	private String districtNameEnglish;
	private boolean rdoBlockDelete;
	private String villageList;
	private String blockYes;
	private String blockNo;
	private int direction;
	private String lati;
    private String longi;

	// new
	private String requiredTitle;// Title Required Flag
	private String uniqueTitle;// Unique Title Required Flag
	private int allowedNoOfAttachment;// Allowed Number Of Attachment
	private long allowedTotalFileSize;// Allowed Total File Size
	private long allowedIndividualFileSize;// Allowed Individual File Size.
	private String allowedFileType;// Allowed File Type
	private String uploadLocation;// File Upload Location
	private String showTitle;// Entered Title Value
	private List<CommonsMultipartFile> attachedFile;// Attached File List
	private int pageRows;
	private Integer errorflag; 
	private List<StateWiseEntityDetails> stateWiseEntityDetails = new ArrayList<StateWiseEntityDetails>();

	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
	}

	private List<Block> listBlockDetail = new ArrayList<Block>();
	private List<BlockDataForm> listBlockDetails = new ArrayList<BlockDataForm>();
	private List<BlockHistory> blockHistoryDetail = new ArrayList<BlockHistory>();

	public List<BlockHistory> getBlockHistoryDetail() {
		return blockHistoryDetail;
	}

	public void setBlockHistoryDetail(List<BlockHistory> blockHistoryDetail) {
		this.blockHistoryDetail = blockHistoryDetail;
	}

	public List<BlockDataForm> getListBlockDetails() {
		return listBlockDetails;
	}

	public void setListBlockDetails(List<BlockDataForm> listBlockDetails) {
		this.listBlockDetails = listBlockDetails;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getRecordsLimit() {
		return recordsLimit;
	}

	public void setRecordsLimit(int recordsLimit) {
		this.recordsLimit = recordsLimit;
	}

	public List<Block> getListBlockDetail() {
		return listBlockDetail;
	}

	public void setListBlockDetail(List<Block> listBlockDetail) {
		this.listBlockDetail = listBlockDetail;
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

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public int getDistrictVersion() {
		return districtVersion;
	}

	public void setDistrictVersion(int districtVersion) {
		this.districtVersion = districtVersion;
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

	public String getHeadquarterNameLocal() {
		return headquarterNameLocal;
	}

	public void setHeadquarterNameLocal(String headquarterNameLocal) {
		this.headquarterNameLocal = headquarterNameLocal;
	}

	public String getBlockList() {
		return blockList;
	}

	public void setBlockList(String blockList) {
		this.blockList = blockList;
	}

	public String getContributedBlocks() {
		return contributedBlocks;
	}

	public void setContributedBlocks(String contributedBlocks) {
		this.contributedBlocks = contributedBlocks;
	}

	public String getContributedVillages() {
		return contributedVillages;
	}

	public void setContributedVillages(String contributedVillages) {
		this.contributedVillages = contributedVillages;
	}

	public boolean isContriBlock() {
		return contriBlock;
	}

	public void setContriBlock(boolean contriBlock) {
		this.contriBlock = contriBlock;
	}

	public String getAddAnotherBlock() {
		return addAnotherBlock;
	}

	public void setAddAnotherBlock(String addAnotherBlock) {
		this.addAnotherBlock = addAnotherBlock;
	}

	/**
	 * @return the districtNameEnglish
	 */
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	/**
	 * @param districtNameEnglish
	 *            the districtNameEnglish to set
	 */
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	/**
	 * @return the rdoBlockDelete
	 */
	public boolean isRdoBlockDelete() {
		return rdoBlockDelete;
	}

	/**
	 * @param rdoBlockDelete
	 *            the rdoBlockDelete to set
	 */
	public void setRdoBlockDelete(boolean rdoBlockDelete) {
		this.rdoBlockDelete = rdoBlockDelete;
	}

	/**
	 * @return the villageList
	 */
	public String getVillageList() {
		return villageList;
	}

	/**
	 * @param villageList
	 *            the villageList to set
	 */
	public void setVillageList(String villageList) {
		this.villageList = villageList;
	}

	/**
	 * @return the blockYes
	 */
	public String getBlockYes() {
		return blockYes;
	}

	/**
	 * @param blockYes
	 *            the blockYes to set
	 */
	public void setBlockYes(String blockYes) {
		this.blockYes = blockYes;
	}

	/**
	 * @return the blockNo
	 */
	public String getBlockNo() {
		return blockNo;
	}

	/**
	 * @param blockNo
	 *            the blockNo to set
	 */
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

	public String getFileMapUpLoad() {
		return fileMapUpLoad;
	}

	public void setFileMapUpLoad(String fileMapUpLoad) {
		this.fileMapUpLoad = fileMapUpLoad;
	}

	public String getRequiredTitle() {
		return requiredTitle;
	}

	public void setRequiredTitle(String requiredTitle) {
		this.requiredTitle = requiredTitle;
	}

	public String getUniqueTitle() {
		return uniqueTitle;
	}

	public void setUniqueTitle(String uniqueTitle) {
		this.uniqueTitle = uniqueTitle;
	}

	public int getAllowedNoOfAttachment() {
		return allowedNoOfAttachment;
	}

	public void setAllowedNoOfAttachment(int allowedNoOfAttachment) {
		this.allowedNoOfAttachment = allowedNoOfAttachment;
	}

	public long getAllowedTotalFileSize() {
		return allowedTotalFileSize;
	}

	public void setAllowedTotalFileSize(long allowedTotalFileSize) {
		this.allowedTotalFileSize = allowedTotalFileSize;
	}

	public long getAllowedIndividualFileSize() {
		return allowedIndividualFileSize;
	}

	public void setAllowedIndividualFileSize(long allowedIndividualFileSize) {
		this.allowedIndividualFileSize = allowedIndividualFileSize;
	}

	public String getAllowedFileType() {
		return allowedFileType;
	}

	public void setAllowedFileType(String allowedFileType) {
		this.allowedFileType = allowedFileType;
	}

	public String getUploadLocation() {
		return uploadLocation;
	}

	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public List<CommonsMultipartFile> getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(List<CommonsMultipartFile> attachedFile) {
		this.attachedFile = attachedFile;
	}

	public char getOperation() {
		return operation;
	}

	public void setOperation(char operation) {
		this.operation = operation;
	}

	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}

	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}

	public String getTemplateList() {
		return templateList;
	}

	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}

	public List<StateWiseEntityDetails> getStateWiseEntityDetails() {
		return stateWiseEntityDetails;
	}

	public void setStateWiseEntityDetails(
			List<StateWiseEntityDetails> stateWiseEntityDetails) {
		this.stateWiseEntityDetails = stateWiseEntityDetails;
	}

	public String getTargetDistrictCodeNo() {
		return targetDistrictCodeNo;
	}

	public void setTargetDistrictCodeNo(String targetDistrictCodeNo) {
		this.targetDistrictCodeNo = targetDistrictCodeNo;
	}

	public String getTargetDistrictCodeYes() {
		return targetDistrictCodeYes;
	}

	public void setTargetDistrictCodeYes(String targetDistrictCodeYes) {
		this.targetDistrictCodeYes = targetDistrictCodeYes;
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

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getBlockNameEnglishch() {
		return blockNameEnglishch;
	}

	public void setBlockNameEnglishch(String blockNameEnglishch) {
		this.blockNameEnglishch = blockNameEnglishch;
	}

	public String getRequiredTitle1() {
		return requiredTitle1;
	}

	public void setRequiredTitle1(String requiredTitle1) {
		this.requiredTitle1 = requiredTitle1;
	}

	public String getMaxFileLimit1() {
		return maxFileLimit1;
	}

	public void setMaxFileLimit1(String maxFileLimit1) {
		this.maxFileLimit1 = maxFileLimit1;
	}

	public String[] getFileTitle1() {
		return fileTitle1;
	}

	public void setFileTitle1(String[] fileTitle1) {
		this.fileTitle1 = fileTitle1;
	}

	public List<CommonsMultipartFile> getAttachFile1() {
		return attachFile1;
	}

	public void setAttachFile1(List<CommonsMultipartFile> attachFile1) {
		this.attachFile1 = attachFile1;
	}

	public List<CommonsMultipartFile> getAttachFile2() {
		return attachFile2;
	}

	public void setAttachFile2(List<CommonsMultipartFile> attachFile2) {
		this.attachFile2 = attachFile2;
	}

	public String getRequiredTitle2() {
		return requiredTitle2;
	}

	public void setRequiredTitle2(String requiredTitle2) {
		this.requiredTitle2 = requiredTitle2;
	}

	public String getMaxFileLimit2() {
		return maxFileLimit2;
	}

	public void setMaxFileLimit2(String maxFileLimit2) {
		this.maxFileLimit2 = maxFileLimit2;
	}

	public String[] getFileTitle2() {
		return fileTitle2;
	}

	public void setFileTitle2(String[] fileTitle2) {
		this.fileTitle2 = fileTitle2;
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

	public String getAliasLocalch() {
		return aliasLocalch;
	}

	public void setAliasLocalch(String aliasLocalch) {
		this.aliasLocalch = aliasLocalch;
	}

	public String getAliasEnglishch() {
		return aliasEnglishch;
	}

	public void setAliasEnglishch(String aliasEnglishch) {
		this.aliasEnglishch = aliasEnglishch;
	}

	public String getBlockNameLocalch() {
		return blockNameLocalch;
	}

	public void setBlockNameLocalch(String blockNameLocalch) {
		this.blockNameLocalch = blockNameLocalch;
	}

	public String getNoMapRecord() {
		return noMapRecord;
	}

	public void setNoMapRecord(String noMapRecord) {
		this.noMapRecord = noMapRecord;
	}

	public String getNoAttachmentRecord() {
		return noAttachmentRecord;
	}

	public void setNoAttachmentRecord(String noAttachmentRecord) {
		this.noAttachmentRecord = noAttachmentRecord;
	}

	public String getNoOrderRecord() {
		return noOrderRecord;
	}

	public void setNoOrderRecord(String noOrderRecord) {
		this.noOrderRecord = noOrderRecord;
	}

	public String getCordinate() {
		return cordinate;
	}

	public void setCordinate(String cordinate) {
		this.cordinate = cordinate;
	}

	public Boolean getWarningFlag() {
		return warningFlag;
	}

	public void setWarningFlag(Boolean warningFlag) {
		this.warningFlag = warningFlag;
	}

	public String getGovtfilecount() {
		return govtfilecount;
	}

	public void setGovtfilecount(String govtfilecount) {
		this.govtfilecount = govtfilecount;
	}

	
	public Integer getDlc() {
		return dlc;
	}

	public void setDlc(Integer dlc) {
		this.dlc = dlc;
	}

	public Integer getErrorflag() {
		return errorflag;
	}

	public void setErrorflag(Integer errorflag) {
		this.errorflag = errorflag;
	}

	public String getUlbListFormat() {
		return ulbListFormat;
	}

	public void setUlbListFormat(String ulbListFormat) {
		this.ulbListFormat = ulbListFormat;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	
	
}
