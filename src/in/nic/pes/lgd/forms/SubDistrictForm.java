package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictHistory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author Ram
 *
 */
public class SubDistrictForm {
    
	private int subdistrictCode;
    private int subDistrictVersion; 
    @NotEmpty(message = "Subdistrict name must not not be blank.")
    @Pattern(regexp="^[0-9a-zA-Z\\.\\- ]+$", message="Subdistrict name can be formed with alphanumerics , . , - and spaces Only")   
	private String subdistrictNameEnglish;
    private String subdistrictNameLocal;
    private String subdistrictNameLocalch;
    private String aliasLocalch;
    private String stateNameEnglish;
    @NotEmpty(message = "District name must not not be blank.")
    private String districtCode;
    private String aliasEnglish;
    private String aliasEnglishch;
    private String aliasLocal;
    private String headquarterName;
    private String headquarterNameLocal;
    private int mapCode;
    private String census2011Code;
    private String sscode;
    private String orderNocr;
    private String subdistrictNameEnglishch;
	private Date orderDatecr;
	private Date ordereffectiveDatecr;
	private Date gazPubDatecr;
	private String orderPathcr;
	private Integer tlc;
   
   
    private String subDistrictList;
    private MultipartFile filePathcr;
	private String contributedSubDistricts;
    private boolean createFromExistingSubDistrict;
	private boolean createFromNewLand;
    private String methodToCall;
    private boolean correction;
	private String code;
	private int recordsLimit;
	private String contributedVillages;
	private String reorganized;
	private String contributedVillagesReorg;
	private boolean newVillage;
	private String modifyVillage;
	private String addAnotherSD;
	private boolean contriSD;
	private String districtNameEnglish;
	private String correctionRadio;
	private boolean rdoSubdistrictDelete;
	private String targetSubdistrictYes;
	private String targetSubdistrictNo;
	private String villList;
	private String cordinate;
	//governmentOrder
	private String ddSubdistrict;
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;
	private String ddSubdistrictforsubdistrict;
	private boolean mapUploaded;
	//new
	private String requiredTitle;//Title Required Flag
	private String uniqueTitle;//Unique Title Required Flag
	private int allowedNoOfAttachment;//Allowed Number Of Attachment
	private long allowedTotalFileSize;//Allowed Total File Size
	private long allowedIndividualFileSize;//Allowed Individual File Size.
	private String allowedFileType;//Allowed File Type
	private String uploadLocation;//File Upload Location
	private String showTitle;//Entered Title Value
	private List<CommonsMultipartFile> attachedFile;//Attached File List
	//correction
	private String requiredTitlecr;//Title Required Flag
	private String uniqueTitlecr;//Unique Title Required Flag
	private int allowedNoOfAttachmentcr;//Allowed Number Of Attachment
	private long allowedTotalFileSizecr;//Allowed Total File Size
	private long allowedIndividualFileSizecr;//Allowed Individual File Size.
	private String allowedFileTypecr;//Allowed File Type
	private String uploadLocationcr;//File Upload Location
	private String showTitlecr;//Entered Title Value
	private List<CommonsMultipartFile> attachedFilecr;//Attached File List
	private String captchaAnswer;
	private String captchaAnswers;
	private String noOrderRecord;
	private String noAttachmentRecord;
	private String noMapRecord;
	private String previousAddedVillageCodes;
	
	private String buttonClicked;
	private String villageNameList;
	private Integer errorflag; 
	
	/*Modified by kirandeep for Invalidate Subdistrict on 16/03/2015 */
	private Integer flagSubDistrictInvalid;
	private String action;
	
	/**
	 * For Mark Pesa Land regions
	 * Added on 05-11-2014 
	 * @author Ripunj
	 */
	private String isPesaStatus;
	/* Added by Sushil on 01-06-2015 */
	private Integer operationCode;
	
	public String getPreviousAddedVillageCodes() {
		return previousAddedVillageCodes;
	}
	public void setPreviousAddedVillageCodes(String previousAddedVillageCodes) {
		this.previousAddedVillageCodes = previousAddedVillageCodes;
	}

		private String subDistrictListForSession;
		private String contsubDistrictListForSession;
		private String villagesListForSession;
		private String contvillagesListForSession;
		private String govtfilecount;
		private Boolean warningFlag;
		
		private Integer stateCode;
		
		private Integer userId;
	
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

	
	
	//Code by Arnab   Start
	
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

	private Integer subdistrictId;
	
	public Integer getSubdistrictId() {
		return subdistrictId;
	}
	public void setSubdistrictId(Integer subdistrictId) {
		this.subdistrictId = subdistrictId;
	}
	
	//Code by Arnab   End
	
	

	private Date effectiveDate;

	private String templateList;
	private char operation;
	private String latitude;
	private String longitude;
	private String lati;
	private String longi;
	
	private String requiredTitle1;
	private String maxFileLimit1;
	private String[] fileTitle1;
	private List<CommonsMultipartFile> attachFile1;
	private List<CommonsMultipartFile> attachFile2;
	private String requiredTitle2;
	private String maxFileLimit2;
	private String[] fileTitle2;
	
	private String fileMapUpLoad;
	 
	 private String govtOrderConfig;
	 
  // List<Subdistrict> listSubdistrictDetails = new ArrayList<Subdistrict>();
    private List<Subdistrict> listSubdistrictDetail = new ArrayList<Subdistrict>();
    private List<SubdistrictDataForm> listSubdistrictDetails = new ArrayList<SubdistrictDataForm>(); 
    private List<SubdistrictHistory> subDistrictHistoryDetail = new ArrayList<SubdistrictHistory>();
    
    //Added by Arnab  Start
	  
	private Integer globalsubdistrictId;
		  
    public Integer getGlobalsubdistrictId() 
    {
		return globalsubdistrictId;
	}
	public void setGlobalsubdistrictId(Integer globalsubdistrictId) 
	{
		this.globalsubdistrictId = globalsubdistrictId;
	}
	
	//Added by Arnab  End
	
	private int pageRows;
	private int limit;
	private int offset;
	private int direction;
	private String pageType;
	
	private List<StateWiseEntityDetails> stateWiseEntityDetails = new ArrayList<StateWiseEntityDetails>();
    
    public List<StateWiseEntityDetails> getStateWiseEntityDetails() {
		return stateWiseEntityDetails;
	}
	public void setStateWiseEntityDetails(
			List<StateWiseEntityDetails> stateWiseEntityDetails) {
		this.stateWiseEntityDetails = stateWiseEntityDetails;
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
	public String getCorrectionRadio() {
		return correctionRadio;
	}
	public void setCorrectionRadio(String correctionRadio) {
		this.correctionRadio = correctionRadio;
	}
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
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
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
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
	public int getMapCode() {
		return mapCode;
	}
	public void setMapCode(int mapCode) {
		this.mapCode = mapCode;
	}
	public void setAliasLocal(String aliasLocal) {
		this.aliasLocal = aliasLocal;
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
	public String getMethodToCall() {
		return methodToCall;
	}
	public void setMethodToCall(String methodToCall) {
		this.methodToCall = methodToCall;
	}
	
	public String getSubDistrictList() {
		return subDistrictList;
	}
	public boolean isCorrection() {
		return correction;
	}
	public void setCorrection(boolean correction) {
		this.correction = correction;
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
	public String getHeadquarterName() {
		return headquarterName;
	}
	public void setHeadquarterName(String headquarterName) {
		this.headquarterName = headquarterName;
	}
	
	
	public List<Subdistrict> getListSubdistrictDetail() {
		return listSubdistrictDetail;
	}
	public void setListSubdistrictDetail(List<Subdistrict> listSubdistrictDetail) {
		this.listSubdistrictDetail = listSubdistrictDetail;
	}
	public List<SubdistrictDataForm> getListSubdistrictDetails() {
		return listSubdistrictDetails;
	}
	public void setListSubdistrictDetails(
			List<SubdistrictDataForm> listSubdistrictDetails) {
		this.listSubdistrictDetails = listSubdistrictDetails;
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
	public String getContributedVillages() {
		return contributedVillages;
	}
	public void setContributedVillages(String contributedVillages) {
		this.contributedVillages = contributedVillages;
	}
	public String isReorganized() {
		return reorganized;
	}
	public void setReorganized(String reorganized) {
		this.reorganized = reorganized;
	}
	public String getContributedVillagesReorg() {
		return contributedVillagesReorg;
	}
	public void setContributedVillagesReorg(String contributedVillagesReorg) {
		this.contributedVillagesReorg = contributedVillagesReorg;
	}
	public boolean isNewVillage() {
		return newVillage;
	}
	public void setNewVillage(boolean newVillage) {
		this.newVillage = newVillage;
	}
	public String getModifyVillage() {
		return modifyVillage;
	}
	public void setModifyVillage(String modifyVillage) {
		this.modifyVillage = modifyVillage;
	}
	public String getAddAnotherSD() {
		return addAnotherSD;
	}
	public void setAddAnotherSD(String addAnotherSD) {
		this.addAnotherSD = addAnotherSD;
	}
	public boolean isContriSD() {
		return contriSD;
	}
	public void setContriSD(boolean contriSD) {
		this.contriSD = contriSD;
	}
	public List<SubdistrictHistory> getSubDistrictHistoryDetail() {
		return subDistrictHistoryDetail;
	}
	public void setSubDistrictHistoryDetail(
			List<SubdistrictHistory> subDistrictHistoryDetail) {
		this.subDistrictHistoryDetail = subDistrictHistoryDetail;
	}

	public String getDdSubdistrict() {
		return ddSubdistrict;
	}
	public void setDdSubdistrict(String ddSubdistrict) {
		this.ddSubdistrict = ddSubdistrict;
	}
	public String getDdSubdistrictforsubdistrict() {
		return ddSubdistrictforsubdistrict;
	}
	public void setDdSubdistrictforsubdistrict(String ddSubdistrictforsubdistrict) {
		this.ddSubdistrictforsubdistrict = ddSubdistrictforsubdistrict;
	}
	/**
	 * @return the rdoSubdistrictDelete
	 */
	public boolean isRdoSubdistrictDelete() {
		return rdoSubdistrictDelete;
	}
	/**
	 * @param rdoSubdistrictDelete the rdoSubdistrictDelete to set
	 */
	public void setRdoSubdistrictDelete(boolean rdoSubdistrictDelete) {
		this.rdoSubdistrictDelete = rdoSubdistrictDelete;
	}
	/**
	 * @return the targetSubdistrictYes
	 */
	public String getTargetSubdistrictYes() {
		return targetSubdistrictYes;
	}
	/**
	 * @param targetSubdistrictYes the targetSubdistrictYes to set
	 */
	public void setTargetSubdistrictYes(String targetSubdistrictYes) {
		this.targetSubdistrictYes = targetSubdistrictYes;
	}
	/**
	 * @return the targetSubdistrictNo
	 */
	public String getTargetSubdistrictNo() {
		return targetSubdistrictNo;
	}
	/**
	 * @param targetSubdistrictNo the targetSubdistrictNo to set
	 */
	public void setTargetSubdistrictNo(String targetSubdistrictNo) {
		this.targetSubdistrictNo = targetSubdistrictNo;
	}
	/**
	 * @return the villList
	 */
	public String getVillList() {
		return villList;
	}
	/**
	 * @param villList the villList to set
	 */
	public void setVillList(String villList) {
		this.villList = villList;
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
	public String getReorganized() {
		return reorganized;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public char getOperation() {
		return operation;
	}
	public void setOperation(char operation) {
		this.operation = operation;
	}
	public String getFileMapUpLoad() {
		return fileMapUpLoad;
	}
	public void setFileMapUpLoad(String fileMapUpLoad) {
		this.fileMapUpLoad = fileMapUpLoad;
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
		public List<CommonsMultipartFile> getAttachedFilecr() {
			return attachedFilecr;
		}
		public void setAttachedFilecr(List<CommonsMultipartFile> attachedFilecr) {
			this.attachedFilecr = attachedFilecr;
		}
		public String getRequiredTitlecr() {
			return requiredTitlecr;
		}
		public void setRequiredTitlecr(String requiredTitlecr) {
			this.requiredTitlecr = requiredTitlecr;
		}
		public String getUniqueTitlecr() {
			return uniqueTitlecr;
		}
		public void setUniqueTitlecr(String uniqueTitlecr) {
			this.uniqueTitlecr = uniqueTitlecr;
		}
		public int getAllowedNoOfAttachmentcr() {
			return allowedNoOfAttachmentcr;
		}
		public void setAllowedNoOfAttachmentcr(int allowedNoOfAttachmentcr) {
			this.allowedNoOfAttachmentcr = allowedNoOfAttachmentcr;
		}
		public long getAllowedTotalFileSizecr() {
			return allowedTotalFileSizecr;
		}
		public void setAllowedTotalFileSizecr(long allowedTotalFileSizecr) {
			this.allowedTotalFileSizecr = allowedTotalFileSizecr;
		}
		public long getAllowedIndividualFileSizecr() {
			return allowedIndividualFileSizecr;
		}
		public void setAllowedIndividualFileSizecr(long allowedIndividualFileSizecr) {
			this.allowedIndividualFileSizecr = allowedIndividualFileSizecr;
		}
		public String getAllowedFileTypecr() {
			return allowedFileTypecr;
		}
		public void setAllowedFileTypecr(String allowedFileTypecr) {
			this.allowedFileTypecr = allowedFileTypecr;
		}
		public String getUploadLocationcr() {
			return uploadLocationcr;
		}
		public void setUploadLocationcr(String uploadLocationcr) {
			this.uploadLocationcr = uploadLocationcr;
		}
		public String getShowTitlecr() {
			return showTitlecr;
		}
		public void setShowTitlecr(String showTitlecr) {
			this.showTitlecr = showTitlecr;
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
	public String getSubdistrictNameEnglishch() {
		return subdistrictNameEnglishch;
	}
	public void setSubdistrictNameEnglishch(String subdistrictNameEnglishch) {
		this.subdistrictNameEnglishch = subdistrictNameEnglishch;
	}
	public String getAliasEnglishch() {
		return aliasEnglishch;
	}
	public void setAliasEnglishch(String aliasEnglishch) {
		this.aliasEnglishch = aliasEnglishch;
	}
	public String getSubdistrictNameLocalch() {
		return subdistrictNameLocalch;
	}
	public void setSubdistrictNameLocalch(String subdistrictNameLocalch) {
		this.subdistrictNameLocalch = subdistrictNameLocalch;
	}
	public String getAliasLocalch() {
		return aliasLocalch;
	}
	public void setAliasLocalch(String aliasLocalch) {
		this.aliasLocalch = aliasLocalch;
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
	
	
	public String getVillageNameList() {
		return villageNameList;
	}
	public void setVillageNameList(String villageNameList) {
		this.villageNameList = villageNameList;
	}
	public String getSubDistrictListForSession() {
		return subDistrictListForSession;
	}
	public void setSubDistrictListForSession(String subDistrictListForSession) {
		this.subDistrictListForSession = subDistrictListForSession;
	}
	public String getContsubDistrictListForSession() {
		return contsubDistrictListForSession;
	}
	public void setContsubDistrictListForSession(
			String contsubDistrictListForSession) {
		this.contsubDistrictListForSession = contsubDistrictListForSession;
	}
	public String getVillagesListForSession() {
		return villagesListForSession;
	}
	public void setVillagesListForSession(String villagesListForSession) {
		this.villagesListForSession = villagesListForSession;
	}
	public String getContvillagesListForSession() {
		return contvillagesListForSession;
	}
	public void setContvillagesListForSession(String contvillagesListForSession) {
		this.contvillagesListForSession = contvillagesListForSession;
	}
	public String getButtonClicked() {
		return buttonClicked;
	}
	public void setButtonClicked(String buttonClicked) {
		this.buttonClicked = buttonClicked;
	}
	public boolean isMapUploaded() {
		return mapUploaded;
	}
	public void setMapUploaded(boolean mapUploaded) {
		this.mapUploaded = mapUploaded;
	}
	public String getCordinate() {
		return cordinate;
	}
	public void setCordinate(String cordinate) {
		this.cordinate = cordinate;
	}
	public String getGovtfilecount() {
		return govtfilecount;
	}
	public void setGovtfilecount(String govtfilecount) {
		this.govtfilecount = govtfilecount;
	}
	public Boolean getWarningFlag() {
		return warningFlag;
	}
	public void setWarningFlag(Boolean warningFlag) {
		this.warningFlag = warningFlag;
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
	public Integer getFlagSubDistrictInvalid() {
		return flagSubDistrictInvalid;
	}
	public void setFlagSubDistrictInvalid(Integer flagSubDistrictInvalid) {
		this.flagSubDistrictInvalid = flagSubDistrictInvalid;
	}
	public Integer getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
