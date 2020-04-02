package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictHistory;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class DistrictForm {

	private String mergeVillageListId;
	private String mergeSelectedVillageListId;
	private String mergeSubDistId;
	private String storedCombiValues;
	private String storedNewCombiValues;
	private String districtNameEnglishTemp;
	private String contributedSubDistrictsTemp;
	private String buttonClicked;
	private String historyDistrictList;
	private String histrorySubDistrictList;
	private String histroryNewSubDistrictList;
	private String histroryMergeSubDistrictList;
	private String histroryNewSubDistrict;
	private String preSubDistrictsTemp;
	private String preVillageTemp;
	private String preDistrict;
	private String totalVillage;
	private String districtNameList;
	private String govtfilecount;
	private String districtNameInEn;
	private String districtNameInLcl;
	private String districtAliasInEn;
	private String districtAliasInLcl;
	private String districtHeadquarters;
	private String census2011Code;
	private String stateSpecificCode;
	// ////////////////////
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date effectiveDate;
	private String orderNocr;
	private String districtNameEnglishch;
	private String districtNameLocalch;
	private String aliasLocalch;
	private String aliasEnglishch;
	private Date orderDatecr;
	private Date ordereffectiveDatecr;
	private Date gazPubDatecr;
	private String coordinates;
	/*
	 * private MultipartFile filePathMapUpLoad;
	 */
	private Date gazPubDate;
	private String orderPath;
	private StatePK statePK;
	private int stateVersion;
	private int stateCode;
	private String stateNameEnglish;
	private DistrictPK districtPK;
	private int districtCode;
	private int districtVersion;
	private String sscode;
	private int mapCode;
	private MultipartFile filePathcr;
	private String districtNameEnglish;
	private boolean createFromExistingDistrict;
	private String latitude;
	private String longitude;
	private String districtNameEnglishDest;
	private int recordsLimit;
	private String code;
	// List<District> listDistrictDetails;
	private String subDistrictList;
	private String contributedSubDistricts;
	private String contributedSubDistrictsFull;
	private boolean correction;
	private MultipartFile filePath;
	private String contributedVillages;
	private String correctionRadio;

	private int subdistrictCode;
	private int subDistrictVersion;
	private String subdistrictNameEnglish;
	private String subdistrictNameLocal;

	private String aliasEnglish;
	private String aliasLocal;
	private Integer headquarterCode;
	private String headquarterName;
	private String headquarterNameLocal;
	private boolean modifyVillagestate;
	private String MODIFYSUBDISTRIT;
	private String newSubdistritVillage;
	private String ddSubdistrictforsubdistrict;
	private String districtsourcecode;
	private boolean modifySubDistrictstate;
	private boolean rdoDistrictDelete;
	private String targetDistrictYes;
	private String targetDistrictNo;
	private String lati;
	private String longi;
	private String mapUrl;
	private String addAnotherSD;
	private String reorganized;
	private boolean contriSD;
	private boolean newVillage;
	private boolean mapUploaded;
	private String newSubdistrict;
	private String ModifyVillagesub;

	private boolean createFromExistingSubDistrict;
	private boolean createFromNewLand;
	private String methodToCall;
	private boolean newSubdistrictfordistrict;
	private boolean newVillagefordistrict;

	private String addAnothervillage;

	private String modifyVillage;
	private String fileMapUpLoad;
	private String captchaAnswer;
	private String captchaAnswers;
	private String noOrderRecord;
	private String noAttachmentRecord;
	private String noMapRecord;
	private String listformat;
	private String statusDist;
	private Boolean warningflag;
	private Integer errorflag; 
	private Integer userId;
	
	/**
	 * For Mark Pesa Land regions
	 * Added on 05-11-2014 
	 * @author Ripunj
	 */
	private String isPesaStatus;
	private Character operation_state;

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

	// Added by Arnab Start
	private Integer globaldistrictId;
	private Integer districtId;

	private String[] fileTitle2;

	public String[] getFileTitle2() {
		return fileTitle2;
	}

	public void setFileTitle2(String[] fileTitle2) {
		this.fileTitle2 = fileTitle2;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getGlobaldistrictId() {
		return globaldistrictId;
	}

	public void setGlobaldistrictId(Integer globaldistrictId) {
		this.globaldistrictId = globaldistrictId;
	}

	// Added by Arnab End

	// new
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

	private String templateList;
	private String govtOrderConfig;
	private int subdistrictCodes;

	private int pageRows;
	private int limit;
	private int offset;
	private int direction;
	private String pageType;

	// new for file upload
	private String requiredTitle1;
	private String maxFileLimit1;
	private String[] fileTitle1;
	private List<CommonsMultipartFile> attachFile1;
	private List<CommonsMultipartFile> attachFile2;

	private List<StateWiseEntityDetails> stateWiseEntityDetails = new ArrayList<StateWiseEntityDetails>();

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.limit = pageRows;
		this.pageRows = pageRows;
	}

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

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public List<StateWiseEntityDetails> getStateWiseEntityDetails() {
		return stateWiseEntityDetails;
	}

	public void setStateWiseEntityDetails(
			List<StateWiseEntityDetails> stateWiseEntityDetails) {
		this.stateWiseEntityDetails = stateWiseEntityDetails;
	}

	public String getFileMapUpLoad() {
		return fileMapUpLoad;
	}

	public void setFileMapUpLoad(String fileMapUpLoad) {
		this.fileMapUpLoad = fileMapUpLoad;
	}

	private String modifySubDistrict;
	private char operation;

	public String getDistrictsourcecode() {
		return districtsourcecode;
	}

	public void setDistrictsourcecode(String districtsourcecode) {
		this.districtsourcecode = districtsourcecode;
	}

	public String getAddAnotherSD() {
		return addAnotherSD;
	}

	public void setAddAnotherSD(String addAnotherSD) {
		this.addAnotherSD = addAnotherSD;
	}

// TODO Remove unused code found by UCDetector
// 	public String isReorganized() {
// 		return reorganized;
// 	}

	public String getReorganized() {
		return reorganized;
	}

	public void setReorganized(String reorganized) {
		this.reorganized = reorganized;
	}

	public boolean isContriSD() {
		return contriSD;
	}

	public void setContriSD(boolean contriSD) {
		this.contriSD = contriSD;
	}

	public boolean isNewVillage() {
		return newVillage;
	}

	public void setNewVillage(boolean newVillage) {
		this.newVillage = newVillage;
	}

	// private boolean newLand;

	public String getCorrectionRadio() {
		return correctionRadio;
	}

	public void setCorrectionRadio(String correctionRadio) {
		this.correctionRadio = correctionRadio;
	}

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}

	private List<District> listDistrictDetail = new ArrayList<District>();
	private List<DistrictDataForm> listDistrictDetails = new ArrayList<DistrictDataForm>();
	private List<DistrictHistory> districtHistoryDetail = new ArrayList<DistrictHistory>();

	public int getMapCode() {
		return mapCode;
	}

	public void setMapCode(int mapCode) {
		this.mapCode = mapCode;
	}

	public String getSscode() {
		return sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	public boolean isCorrection() {
		return correction;
	}

	public void setCorrection(boolean correction) {
		this.correction = correction;
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

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
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

	public StatePK getStatePK() {
		return statePK;
	}

	public void setStatePK(StatePK statePK) {
		this.statePK = statePK;
	}

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

	public DistrictPK getDistrictPK() {
		return districtPK;
	}

	public void setDistrictPK(DistrictPK districtPK) {
		this.districtPK = districtPK;
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

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	public String getDistrictNameInEn() {
		return districtNameInEn;
	}

	public void setDistrictNameInEn(String districtNameInEn) {
		this.districtNameInEn = districtNameInEn;
	}

	public String getDistrictNameInLcl() {
		return districtNameInLcl;
	}

	public void setDistrictNameInLcl(String districtNameInLcl) {
		this.districtNameInLcl = districtNameInLcl;
	}

	public String getDistrictAliasInEn() {
		return districtAliasInEn;
	}

	public void setDistrictAliasInEn(String districtAliasInEn) {
		this.districtAliasInEn = districtAliasInEn;
	}

	public String getDistrictAliasInLcl() {
		return districtAliasInLcl;
	}

	public void setDistrictAliasInLcl(String districtAliasInLcl) {
		this.districtAliasInLcl = districtAliasInLcl;
	}

	public String getDistrictHeadquarters() {
		return districtHeadquarters;
	}

	public void setDistrictHeadquarters(String districtHeadquarters) {
		this.districtHeadquarters = districtHeadquarters;
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

	public boolean isCreateFromExistingDistrict() {
		return createFromExistingDistrict;
	}

	public void setCreateFromExistingDistrict(boolean createFromExistingDistrict) {
		this.createFromExistingDistrict = createFromExistingDistrict;
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

	public String getDistrictNameEnglishDest() {
		return districtNameEnglishDest;
	}

	public void setDistrictNameEnglishDest(String districtNameEnglishDest) {
		this.districtNameEnglishDest = districtNameEnglishDest;
	}

	public List<District> getListDistrictDetail() {
		return listDistrictDetail;
	}

	public void setListDistrictDetail(List<District> listDistrictDetail) {
		this.listDistrictDetail = listDistrictDetail;
	}

	public List<DistrictDataForm> getListDistrictDetails() {
		return listDistrictDetails;
	}

	public void setListDistrictDetails(
			List<DistrictDataForm> listDistrictDetails) {
		this.listDistrictDetails = listDistrictDetails;
	}

	public String getSubDistrictList() {
		return subDistrictList;
	}

	public void setSubDistrictList(String subDistrictList) {
		this.subDistrictList = subDistrictList;
	}

	public String getContributedSubDistricts() {
		return contributedSubDistricts;
	}

	public void setContributedSubDistricts(String contributedSubDistricts) {
		this.contributedSubDistricts = contributedSubDistricts;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getContributedVillages() {
		return contributedVillages;
	}

	public void setContributedVillages(String contributedVillages) {
		this.contributedVillages = contributedVillages;
	}

	public int getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(int subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public int getSubDistrictVersion() {
		return subDistrictVersion;
	}

	public void setSubDistrictVersion(int subDistrictVersion) {
		this.subDistrictVersion = subDistrictVersion;
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

	public boolean isCreateFromExistingSubDistrict() {
		return createFromExistingSubDistrict;
	}

	public void setCreateFromExistingSubDistrict(
			boolean createFromExistingSubDistrict) {
		this.createFromExistingSubDistrict = createFromExistingSubDistrict;
	}

	public boolean isCreateFromNewLand() {
		return createFromNewLand;
	}

	public void setCreateFromNewLand(boolean createFromNewLand) {
		this.createFromNewLand = createFromNewLand;
	}

	public String getMethodToCall() {
		return methodToCall;
	}

	public void setMethodToCall(String methodToCall) {
		this.methodToCall = methodToCall;
	}

	public List<DistrictHistory> getDistrictHistoryDetail() {
		return districtHistoryDetail;
	}

	public void setDistrictHistoryDetail(
			List<DistrictHistory> districtHistoryDetail) {
		this.districtHistoryDetail = districtHistoryDetail;
	}

	/*
	 * public MultipartFile getFilePathMapUpLoad() { return filePathMapUpLoad; }
	 * public void setFilePathMapUpLoad(MultipartFile filePathMapUpLoad) {
	 * this.filePathMapUpLoad = filePathMapUpLoad; }
	 */

	public String getMODIFYSUBDISTRIT() {
		return MODIFYSUBDISTRIT;
	}

	public void setMODIFYSUBDISTRIT(String mODIFYSUBDISTRIT) {
		MODIFYSUBDISTRIT = mODIFYSUBDISTRIT;
	}

// TODO Remove unused code found by UCDetector
// 	public String isNewSubdistritVillage() {
// 		return newSubdistritVillage;
// 	}

	public void setNewSubdistritVillage(String newSubdistritVillage) {
		this.newSubdistritVillage = newSubdistritVillage;
	}

// TODO Remove unused code found by UCDetector
// 	public String isModifyVillagesub() {
// 		return ModifyVillagesub;
// 	}

	public void setModifyVillagesub(String modifyVillagesub) {
		ModifyVillagesub = modifyVillagesub;
	}

	public String getDdSubdistrictforsubdistrict() {
		return ddSubdistrictforsubdistrict;
	}

	public void setDdSubdistrictforsubdistrict(
			String ddSubdistrictforsubdistrict) {
		this.ddSubdistrictforsubdistrict = ddSubdistrictforsubdistrict;
	}

	/**
	 * @return the rdoDistrictDelete
	 */
	public boolean isRdoDistrictDelete() {
		return rdoDistrictDelete;
	}

	/**
	 * @param rdoDistrictDelete
	 *            the rdoDistrictDelete to set
	 */
	public void setRdoDistrictDelete(boolean rdoDistrictDelete) {
		this.rdoDistrictDelete = rdoDistrictDelete;
	}

	/**
	 * @return the targetDistrictYes
	 */
	public String getTargetDistrictYes() {
		return targetDistrictYes;
	}

	/**
	 * @param targetDistrictYes
	 *            the targetDistrictYes to set
	 */
	public void setTargetDistrictYes(String targetDistrictYes) {
		this.targetDistrictYes = targetDistrictYes;
	}

	/**
	 * @return the targetDistrictNo
	 */
	public String getTargetDistrictNo() {
		return targetDistrictNo;
	}

	/**
	 * @param targetDistrictNo
	 *            the targetDistrictNo to set
	 */
	public void setTargetDistrictNo(String targetDistrictNo) {
		this.targetDistrictNo = targetDistrictNo;
	}

	public boolean isNewSubdistrictfordistrict() {
		return newSubdistrictfordistrict;
	}

	public void setNewSubdistrictfordistrict(boolean newSubdistrictfordistrict) {
		this.newSubdistrictfordistrict = newSubdistrictfordistrict;
	}

	public boolean isNewVillagefordistrict() {
		return newVillagefordistrict;
	}

	public void setNewVillagefordistrict(boolean newVillagefordistrict) {
		this.newVillagefordistrict = newVillagefordistrict;
	}

	public String getAddAnothervillage() {
		return addAnothervillage;
	}

	public void setAddAnothervillage(String addAnothervillage) {
		this.addAnothervillage = addAnothervillage;
	}

	public String getModifyVillage() {
		return modifyVillage;
	}

	public void setModifyVillage(String modifyVillage) {
		this.modifyVillage = modifyVillage;
	}

	public String getModifySubDistrict() {
		return modifySubDistrict;
	}

	public void setModifySubDistrict(String modifySubDistrict) {
		this.modifySubDistrict = modifySubDistrict;
	}

	public boolean isModifyVillagestate() {
		return modifyVillagestate;
	}

	public void setModifyVillagestate(boolean modifyVillagestate) {
		this.modifyVillagestate = modifyVillagestate;
	}

	public boolean isModifySubDistrictstate() {
		return modifySubDistrictstate;
	}

	public void setModifySubDistrictstate(boolean modifySubDistrictstate) {
		this.modifySubDistrictstate = modifySubDistrictstate;
	}

	public String getNewSubdistrict() {
		return newSubdistrict;
	}

	public void setNewSubdistrict(String newSubdistrict) {
		this.newSubdistrict = newSubdistrict;
	}

	public MultipartFile getFilePathcr() {
		return filePathcr;
	}

	public void setFilePathcr(MultipartFile filePathcr) {
		this.filePathcr = filePathcr;
	}

	public char getOperation() {
		return operation;
	}

	public void setOperation(char operation) {
		this.operation = operation;
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

	public String getNewSubdistritVillage() {
		return newSubdistritVillage;
	}

	public String getModifyVillagesub() {
		return ModifyVillagesub;
	}

	public String getTemplateList() {
		return templateList;
	}

	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}

	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}

	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
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

	public Integer getHeadquarterCode() {
		return headquarterCode;
	}

	public void setHeadquarterCode(Integer headquarterCode) {
		this.headquarterCode = headquarterCode;
	}

	public int getSubdistrictCodes() {
		return subdistrictCodes;
	}

	public void setSubdistrictCodes(int subdistrictCodes) {
		this.subdistrictCodes = subdistrictCodes;
	}

	public String getOrderNocr() {
		return orderNocr;
	}

	public void setOrderNocr(String orderNocr) {
		this.orderNocr = orderNocr;
	}

	public String getDistrictNameEnglishch() {
		return districtNameEnglishch;
	}

	public void setDistrictNameEnglishch(String districtNameEnglishch) {
		this.districtNameEnglishch = districtNameEnglishch;
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

	public String getDistrictNameLocalch() {
		return districtNameLocalch;
	}

	public void setDistrictNameLocalch(String districtNameLocalch) {
		this.districtNameLocalch = districtNameLocalch;
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

	public List<CommonsMultipartFile> getAttachFile2() {
		return attachFile2;
	}

	public void setAttachFile2(List<CommonsMultipartFile> attachFile2) {
		this.attachFile2 = attachFile2;
	}

	public String getContributedSubDistrictsFull() {
		return contributedSubDistrictsFull;
	}

	public void setContributedSubDistrictsFull(
			String contributedSubDistrictsFull) {
		this.contributedSubDistrictsFull = contributedSubDistrictsFull;
	}

	public String getMergeVillageListId() {
		return mergeVillageListId;
	}

	public void setMergeVillageListId(String mergeVillageListId) {
		this.mergeVillageListId = mergeVillageListId;
	}

	public String getMergeSelectedVillageListId() {
		return mergeSelectedVillageListId;
	}

	public void setMergeSelectedVillageListId(String mergeSelectedVillageListId) {
		this.mergeSelectedVillageListId = mergeSelectedVillageListId;
	}

	public String getMergeSubDistId() {
		return mergeSubDistId;
	}

	public void setMergeSubDistId(String mergeSubDistId) {
		this.mergeSubDistId = mergeSubDistId;
	}

	public String getStoredCombiValues() {
		return storedCombiValues;
	}

	public void setStoredCombiValues(String storedCombiValues) {
		this.storedCombiValues = storedCombiValues;
	}

	public String getStoredNewCombiValues() {
		return storedNewCombiValues;
	}

	public void setStoredNewCombiValues(String storedNewCombiValues) {
		this.storedNewCombiValues = storedNewCombiValues;
	}

	public String getDistrictNameEnglishTemp() {
		return districtNameEnglishTemp;
	}

	public void setDistrictNameEnglishTemp(String districtNameEnglishTemp) {
		this.districtNameEnglishTemp = districtNameEnglishTemp;
	}

	public String getContributedSubDistrictsTemp() {
		return contributedSubDistrictsTemp;
	}

	public void setContributedSubDistrictsTemp(
			String contributedSubDistrictsTemp) {
		this.contributedSubDistrictsTemp = contributedSubDistrictsTemp;
	}

	public String getButtonClicked() {
		return buttonClicked;
	}

	public void setButtonClicked(String buttonClicked) {
		this.buttonClicked = buttonClicked;
	}

	public String getHistoryDistrictList() {
		return historyDistrictList;
	}

	public void setHistoryDistrictList(String historyDistrictList) {
		this.historyDistrictList = historyDistrictList;
	}

	public String getHistrorySubDistrictList() {
		return histrorySubDistrictList;
	}

	public void setHistrorySubDistrictList(String histrorySubDistrictList) {
		this.histrorySubDistrictList = histrorySubDistrictList;
	}

	public String getHistroryNewSubDistrictList() {
		return histroryNewSubDistrictList;
	}

	public void setHistroryNewSubDistrictList(String histroryNewSubDistrictList) {
		this.histroryNewSubDistrictList = histroryNewSubDistrictList;
	}

	public String getHistroryMergeSubDistrictList() {
		return histroryMergeSubDistrictList;
	}

	public void setHistroryMergeSubDistrictList(
			String histroryMergeSubDistrictList) {
		this.histroryMergeSubDistrictList = histroryMergeSubDistrictList;
	}

	public String getHistroryNewSubDistrict() {
		return histroryNewSubDistrict;
	}

	public void setHistroryNewSubDistrict(String histroryNewSubDistrict) {
		this.histroryNewSubDistrict = histroryNewSubDistrict;
	}

	public String getPreSubDistrictsTemp() {
		return preSubDistrictsTemp;
	}

	public void setPreSubDistrictsTemp(String preSubDistrictsTemp) {
		this.preSubDistrictsTemp = preSubDistrictsTemp;
	}

	public String getPreVillageTemp() {
		return preVillageTemp;
	}

	public void setPreVillageTemp(String preVillageTemp) {
		this.preVillageTemp = preVillageTemp;
	}

	public String getPreDistrict() {
		return preDistrict;
	}

	public void setPreDistrict(String preDistrict) {
		this.preDistrict = preDistrict;
	}

	public String getTotalVillage() {
		return totalVillage;
	}

	public void setTotalVillage(String totalVillage) {
		this.totalVillage = totalVillage;
	}

	public boolean isMapUploaded() {
		return mapUploaded;
	}

	public void setMapUploaded(boolean mapUploaded) {
		this.mapUploaded = mapUploaded;
	}

	public String getDistrictNameList() {
		return districtNameList;
	}

	public void setDistrictNameList(String districtNameList) {
		this.districtNameList = districtNameList;
	}

	public String getListformat() {
		return listformat;
	}

	public void setListformat(String listformat) {
		this.listformat = listformat;
	}

	public String getStatusDist() {
		return statusDist;
	}

	public void setStatusDist(String statusDist) {
		this.statusDist = statusDist;
	}

	public String getGovtfilecount() {
		return govtfilecount;
	}

	public void setGovtfilecount(String govtfilecount) {
		this.govtfilecount = govtfilecount;
	}

	public Boolean isWarningflag() {
		return warningflag;
	}

	public void setWarningflag(Boolean warningflag) {
		this.warningflag = warningflag;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public Integer getErrorflag() {
		return errorflag;
	}

	public void setErrorflag(Integer errorflag) {
		this.errorflag = errorflag;
	}

	public String getIsPesaStatus() {
		return isPesaStatus;
	}

	public void setIsPesaStatus(String isPesaStatus) {
		this.isPesaStatus = isPesaStatus;
	}

	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
