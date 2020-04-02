package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.DesignationElected;
import in.nic.pes.lgd.bean.DesignationOfficial;
import in.nic.pes.lgd.bean.DesignationReporting;
import in.nic.pes.lgd.bean.StandardCodes;
import in.nic.pes.lgd.bean.Subtype;
import in.nic.pes.lgd.bean.Tier;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillageHistory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class VillageForm {
	private int villageCode;
	@NotEmpty(message = "Please Enter Village Name")
	private String renameNameVillageList;
	private String renameIdVillageList;
	private String withOutRenameIdVillageList;
	private String newVillageNameEnglish;
	private String newVillageNameLocal;
	private String newVillageAliasEnglish;
	private String newVillageAliasLocal;
	private int census2001Code;
	private String census2011Code;
	private boolean isCensusTown;
	private String stateSpecificCode;
	private String villageType;
	private String villageFormedType;
	private String sscode;
	private String coordinates;
	private int operationCode;
	private String villageNameLocalCh;
	private String aliasEnglishCh;
	private String aliasLocalCh;
	private String[] surveyNumber;
	private String latitude;
	private String longitude;
	private String lati;
	private String longi;
	private String fileMapUpLoad;
	private MultipartFile urlpath;
	private MultipartFile filePathcr;
	// private String urlpath;
	private Integer subdistrictCode;
	private String villageStatus;
	@NotEmpty(message = "Please Select Sub-District")
	@Pattern(regexp = "([1-9][0-9]*)$", message = "Please Select Sub-District")
	private String subdistrictNameEnglish;
	private String ddSubdistrictCode;
	@NotEmpty(message = "Please Select District")
	@Pattern(regexp = "([1-9][0-9]*)$", message = "Please Select District")
	private String districtNameEnglish;
	private boolean correction;
	private String villageList;
	private String contributedVillages;
	private String surveyList;
	private String contributedSurvey;
	private int villageVersion;
	private boolean createFromExistingVillages;
	private boolean createFromNewLand;
	private boolean createFromCoverageOfUrbanLocalBody;
	private String ulbList;
	private String coveredLandRegionByULB;
	private String selectedCoveredLandRegionByULB;
	private String noOrderRecord;
	private String noAttachmentRecord;
	private String noMapRecord;
	// Code by Arnab Start
	private Integer globalvillageId;
	private Integer villageId;
	// Code by Arnab End
	private String villageListMerge;
	// governmentOrder
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date effectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;
	private String templateList;
	private String govtOrderConfig;
	private char operation;
	private String subdistrictCodes;
	private int pageRows;
	private int limit;
	private int offset;
	private String districtCode;
	private int direction;
	private String pageType;
	private String orderNocr;
	private String villageNameEnglishCh;
	private Date orderDatecr;
	private Date ordereffectiveDatecr;
	private Date gazPubDatecr;
	private String orderPathcr;
	private List<StandardCodes> StandardCodes = new ArrayList<StandardCodes>();
	private String gazettePublicationUpload;
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

	private String requiredTitle1;
	private String maxFileLimit1;
	private String[] fileTitle1;
	private List<CommonsMultipartFile> attachFile1;
	private List<CommonsMultipartFile> attachFile2;// Attached File List
	private String templateBodySrc;
	private List<VillageDataForm> listVillageDetails = new ArrayList<VillageDataForm>();
	//Element added for Village Draft Form Details 
	private List<VillageForm> listVillageDraftDetails = new ArrayList<VillageForm>();
	private List<Village> listVillageDetail = new ArrayList<Village>();
	private List<Tier> commonTier = new ArrayList<Tier>();
	private List<DesignationReporting> designationTier = new ArrayList<DesignationReporting>();
	private List<Subtype> subType = new ArrayList<Subtype>();
	private List<DesignationElected> designationElected = new ArrayList<DesignationElected>();
	private List<DesignationOfficial> designationOfficial = new ArrayList<DesignationOfficial>();
	private List<VillageHistory> villageHistoryDetail = new ArrayList<VillageHistory>();
	private boolean rdoVillageDelete;
	@NotNull(message = "Please Select Villages")
	// @Pattern(regexp="([1-9][0-9]*)$", message="Please Select Sub-District")
	private String invalidateVillageList;
	private Boolean warningFlag;
	private String govtfilecount;
	private Integer errorflag; 
	//Add attribute to get the count of uploaded map attachment files.
	private String mapfilecount;
	//Add attribute to get the state of submit button is of Save State or Publish State.
	private Character buttonClicked;
	
	private String description;
	private String timeStatmp;
	private String fileLocation;
	/**
	 * For Create Draft Village Functionality.
	 * @author Riounj on 24-09-2014
	 */
	private String isExistingGovtOrder;
	
	/**
	 * For Mark Pesa Land regions
	 * Added on 05-11-2014 
	 * @author Ripunj
	 */
	private String isPesaStatus;
	
	
	/**
	 * For Rename Draft Village
	 * Added on 04-03-2015
	 * @return
	 */
	private Integer renameVillageCode;
	
	private Integer draftVillageCode;
	
	/**
	 * added by pooja on 17-11-2015
	 */
	private String preVersionMaxEffDate;
	
	private Integer userId;
	
	// Setters and Getters
	public int getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getSscode() {
		return sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	public String getVillageStatus() {
		return villageStatus;
	}

	public void setVillageStatus(String villageStatus) {
		this.villageStatus = villageStatus;
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

	public int getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
	}

	public String getNewVillageNameEnglish() {
		return newVillageNameEnglish;
	}

	public void setNewVillageNameEnglish(String newVillageNameEnglish) {
		this.newVillageNameEnglish = newVillageNameEnglish;
	}

	public String getNewVillageNameLocal() {
		return newVillageNameLocal;
	}

	public void setNewVillageNameLocal(String newVillageNameLocal) {
		this.newVillageNameLocal = newVillageNameLocal;
	}

	public String getNewVillageAliasEnglish() {
		return newVillageAliasEnglish;
	}

	public void setNewVillageAliasEnglish(String newVillageAliasEnglish) {
		this.newVillageAliasEnglish = newVillageAliasEnglish;
	}

	public String getNewVillageAliasLocal() {
		return newVillageAliasLocal;
	}

	public void setNewVillageAliasLocal(String newVillageAliasLocal) {
		this.newVillageAliasLocal = newVillageAliasLocal;
	}

	public int getCensus2001Code() {
		return census2001Code;
	}

	public void setCensus2001Code(int census2001Code) {
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

	public String[] getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(String[] surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	public int getVillageVersion() {
		return villageVersion;
	}

	public void setVillageVersion(int villageVersion) {
		this.villageVersion = villageVersion;
	}

	public String getVillageListMerge() {
		return villageListMerge;
	}

	public void setVillageListMerge(String villageListMerge) {
		this.villageListMerge = villageListMerge;
	}

	public boolean isRdoVillageDelete() {
		return rdoVillageDelete;
	}

	public void setRdoVillageDelete(boolean rdoVillageDelete) {
		this.rdoVillageDelete = rdoVillageDelete;
	}

	public String getInvalidateVillageList() {
		return invalidateVillageList;
	}

	public void setInvalidateVillageList(String invalidateVillageList) {
		this.invalidateVillageList = invalidateVillageList;
	}

	public String getSelectedCoveredLandRegionByULB() {
		return selectedCoveredLandRegionByULB;
	}

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
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

	public void setSelectedCoveredLandRegionByULB(
			String selectedCoveredLandRegionByULB) {
		this.selectedCoveredLandRegionByULB = selectedCoveredLandRegionByULB;
	}

	public String getCoveredLandRegionByULB() {
		return coveredLandRegionByULB;
	}

	public void setCoveredLandRegionByULB(String coveredLandRegionByULB) {
		this.coveredLandRegionByULB = coveredLandRegionByULB;
	}

	public List<Tier> getCommonTier() {
		return commonTier;
	}

	public void setCommonTier(List<Tier> commonTier) {
		this.commonTier = commonTier;
	}

	public List<DesignationReporting> getDesignationTier() {
		return designationTier;
	}

	public void setDesignationTier(List<DesignationReporting> designationTier) {
		this.designationTier = designationTier;
	}

	public List<Subtype> getSubType() {
		return subType;
	}

	public List<DesignationElected> getDesignationElected() {
		return designationElected;
	}

	public void setDesignationElected(
			List<DesignationElected> designationElected) {
		this.designationElected = designationElected;
	}

	public List<DesignationOfficial> getDesignationOfficial() {
		return designationOfficial;
	}

	public void setDesignationOfficial(
			List<DesignationOfficial> designationOfficial) {
		this.designationOfficial = designationOfficial;
	}

	public void setSubType(List<Subtype> subType) {
		this.subType = subType;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public Integer getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(Integer subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
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

	public boolean isCensusTown() {
		return isCensusTown;
	}

	public void setCensusTown(boolean isCensusTown) {
		this.isCensusTown = isCensusTown;
	}

	public VillageForm() {
		super();
	}

	public List<VillageDataForm> getListVillageDetails() {
		return listVillageDetails;
	}

	public void setListVillageDetails(List<VillageDataForm> listVillageDetails) {
		this.listVillageDetails = listVillageDetails;
	}

	public List<Village> getListVillageDetail() {
		return listVillageDetail;
	}

	public void setListVillageDetail(List<Village> listVillageDetail) {
		this.listVillageDetail = listVillageDetail;
	}

	public boolean isCorrection() {
		return correction;
	}

	public void setCorrection(boolean correction) {
		this.correction = correction;
	}

	public String getVillageList() {
		return villageList;
	}

	public void setVillageList(String villageList) {
		this.villageList = villageList;
	}

	public String getContributedVillages() {
		return contributedVillages;
	}

	public void setContributedVillages(String contributedVillages) {
		this.contributedVillages = contributedVillages;
	}

	public String getSurveyList() {
		return surveyList;
	}

	public void setSurveyList(String surveyList) {
		this.surveyList = surveyList;
	}

	public String getContributedSurvey() {
		return contributedSurvey;
	}

	public void setContributedSurvey(String contributedSurvey) {
		this.contributedSurvey = contributedSurvey;
	}

	public boolean isCreateFromExistingVillages() {
		return createFromExistingVillages;
	}

	public void setCreateFromExistingVillages(boolean createFromExistingVillages) {
		this.createFromExistingVillages = createFromExistingVillages;
	}

	public boolean isCreateFromNewLand() {
		return createFromNewLand;
	}

	public void setCreateFromNewLand(boolean createFromNewLand) {
		this.createFromNewLand = createFromNewLand;
	}

	public boolean isCreateFromCoverageOfUrbanLocalBody() {
		return createFromCoverageOfUrbanLocalBody;
	}

	public void setCreateFromCoverageOfUrbanLocalBody(
			boolean createFromCoverageOfUrbanLocalBody) {
		this.createFromCoverageOfUrbanLocalBody = createFromCoverageOfUrbanLocalBody;
	}

	public String getUlbList() {
		return ulbList;
	}

	public void setUlbList(String ulbList) {
		this.ulbList = ulbList;
	}

	public List<VillageHistory> getVillageHistoryDetail() {
		return villageHistoryDetail;
	}

	public void setVillageHistoryDetail(
			List<VillageHistory> villageHistoryDetail) {
		this.villageHistoryDetail = villageHistoryDetail;
	}

	/**
	 * @return the urlpath
	 */
	public MultipartFile getUrlpath() {
		return urlpath;
	}

	/**
	 * @param urlpath
	 *            the urlpath to set
	 */
	public void setUrlpath(MultipartFile urlpath) {
		this.urlpath = urlpath;
	}

	public String getFileMapUpLoad() {
		return fileMapUpLoad;
	}

	public void setFileMapUpLoad(String fileMapUpLoad) {
		this.fileMapUpLoad = fileMapUpLoad;
	}

	public MultipartFile getFilePathcr() {
		return filePathcr;
	}

	public void setFilePathcr(MultipartFile filePathcr) {
		this.filePathcr = filePathcr;
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

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the templateList
	 */
	public String getTemplateList() {
		return templateList;
	}

	/**
	 * @param templateList
	 *            the templateList to set
	 */
	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}

	/**
	 * @return the templateBodySrc
	 */
	public String getTemplateBodySrc() {
		return templateBodySrc;
	}

	/**
	 * @param templateBodySrc
	 *            the templateBodySrc to set
	 */
	public void setTemplateBodySrc(String templateBodySrc) {
		this.templateBodySrc = templateBodySrc;
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

	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}

	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}

	public char getOperation() {
		return operation;
	}

	public void setOperation(char operation) {
		this.operation = operation;
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

	public List<StandardCodes> getStandardCodes() {
		return StandardCodes;
	}

	public void setStandardCodes(List<StandardCodes> standardCodes) {
		StandardCodes = standardCodes;
	}

	public String getSubdistrictCodes() {
		return subdistrictCodes;
	}

	public void setSubdistrictCodes(String subdistrictCodes) {
		this.subdistrictCodes = subdistrictCodes;
	}

	public String getDdSubdistrictCode() {
		return ddSubdistrictCode;
	}

	public void setDdSubdistrictCode(String ddSubdistrictCode) {
		this.ddSubdistrictCode = ddSubdistrictCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getOrderNocr() {
		return orderNocr;
	}

	public void setOrderNocr(String orderNocr) {
		this.orderNocr = orderNocr;
	}

	public String getVillageNameEnglishCh() {
		return villageNameEnglishCh;
	}

	public void setVillageNameEnglishCh(String villageNameEnglishCh) {
		this.villageNameEnglishCh = villageNameEnglishCh;
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

	public String getVillageType() {
		return villageType;
	}

	public void setVillageType(String villageType) {
		this.villageType = villageType;
	}

	public String getVillageFormedType() {
		return villageFormedType;
	}

	public void setVillageFormedType(String villageFormedType) {
		this.villageFormedType = villageFormedType;
	}

	public String getRenameNameVillageList() {
		return renameNameVillageList;
	}

	public void setRenameNameVillageList(String renameNameVillageList) {
		this.renameNameVillageList = renameNameVillageList;
	}

	public String getRenameIdVillageList() {
		return renameIdVillageList;
	}

	public void setRenameIdVillageList(String renameIdVillageList) {
		this.renameIdVillageList = renameIdVillageList;
	}

	public String getWithOutRenameIdVillageList() {
		return withOutRenameIdVillageList;
	}

	public void setWithOutRenameIdVillageList(String withOutRenameIdVillageList) {
		this.withOutRenameIdVillageList = withOutRenameIdVillageList;
	}

	public List<CommonsMultipartFile> getAttachFile2() {
		return attachFile2;
	}

	public void setAttachFile2(List<CommonsMultipartFile> attachFile2) {
		this.attachFile2 = attachFile2;
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

	public Integer getErrorflag() {
		return errorflag;
	}

	public void setErrorflag(Integer errorflag) {
		this.errorflag = errorflag;
	}

	public Character getButtonClicked() {
		return buttonClicked;
	}

	public void setButtonClicked(Character buttonClicked) {
		this.buttonClicked = buttonClicked;
	}

	public List<VillageForm> getListVillageDraftDetails() {
		return listVillageDraftDetails;
	}

	public void setListVillageDraftDetails(List<VillageForm> listVillageDraftDetails) {
		this.listVillageDraftDetails = listVillageDraftDetails;
	}

	public String getGazettePublicationUpload() {
		return gazettePublicationUpload;
	}

	public void setGazettePublicationUpload(String gazettePublicationUpload) {
		this.gazettePublicationUpload = gazettePublicationUpload;
	}

	public String getMapfilecount() {
		return mapfilecount;
	}

	public void setMapfilecount(String mapfilecount) {
		this.mapfilecount = mapfilecount;
	}

	public String getIsExistingGovtOrder() {
		return isExistingGovtOrder;
	}

	public void setIsExistingGovtOrder(String isExistingGovtOrder) {
		this.isExistingGovtOrder = isExistingGovtOrder;
	}

	public String getIsPesaStatus() {
		return isPesaStatus;
	}

	public void setIsPesaStatus(String isPesaStatus) {
		this.isPesaStatus = isPesaStatus;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeStatmp() {
		return timeStatmp;
	}

	public void setTimeStatmp(String timeStatmp) {
		this.timeStatmp = timeStatmp;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public Integer getRenameVillageCode() {
		return renameVillageCode;
	}

	public void setRenameVillageCode(Integer renameVillageCode) {
		this.renameVillageCode = renameVillageCode;
	}

	public Integer getDraftVillageCode() {
		return draftVillageCode;
	}

	public void setDraftVillageCode(Integer draftVillageCode) {
		this.draftVillageCode = draftVillageCode;
	}

	public String getPreVersionMaxEffDate() {
		return preVersionMaxEffDate;
	}

	public void setPreVersionMaxEffDate(String preVersionMaxEffDate) {
		this.preVersionMaxEffDate = preVersionMaxEffDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
		
}
