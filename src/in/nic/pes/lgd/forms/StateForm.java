package in.nic.pes.lgd.forms;


import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateHistory;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class StateForm {
	private StatePK statePK;
	private String stateNameEnglish;
	private String stateNameEnglishch;
	private String stateNameEnglishMain;
	private String stateNameLocal;
	private String shortName;
	private String aliasEnglish;
	private String aliasLocal;
	private String census2001Code;
	private String census2011Code;
	private char stateOrUt;
    private Integer lrReplaces;
	private Integer lrReplacedby;
    private Integer flagCode;
	private Integer mapCode;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date effectiveDate;
	private boolean isactive;
	private int stateCode;
	private int stateVersion;
	private boolean correction;
	private String districtNameInEn;
	private String districtNameInLcl;
	private String districtAliasInEn;
	private String districtHeadquarters;
	private String stateSpecificCode;
	private String districtAliasInLcl;
	private String subDistrictList;
	private String contributedSubDistricts;
	private String contributedVillages;
	private String STATENAMEENGLISH;
	private String latitude;
	private String longitude;
	private String lati;
	private String longi;
	private String headquarterName;
	private String headquarterNameLocal;
	private String DistrictNameEnglish ;
	private int districtCode;
	private String subdistrictNameEnglish;
	private String[] fileTitle1;
	private String cordinate;
	private Boolean warningFlag;
	private String govtfilecount;
	private String description;
	private String  governmentOrder;
	private Integer userId;
	
	public String[] getFileTitle1() {
		return fileTitle1;
	}

	public void setFileTitle1(String[] fileTitle1) {
		this.fileTitle1 = fileTitle1;
	}

	private List<CommonsMultipartFile> attachFile1;
	   public List<CommonsMultipartFile> getAttachFile1() {
		return attachFile1;
	}

	public void setAttachFile1(List<CommonsMultipartFile> attachFile1) {
		this.attachFile1 = attachFile1;
	}
	
	private List<CommonsMultipartFile> attachFile2;
	public List<CommonsMultipartFile> getAttachFile2() {
		return attachFile2;
	}

	public void setAttachFile2(List<CommonsMultipartFile> attachFile2) {
		this.attachFile2 = attachFile2;
	}

	private String subdistrictNameLocal;
	   private String sscode;
	   private String filePathMapUpLoad;
	   private MultipartFile filePathcr;
	   
	   private String addAnotherSD;
	   private String reorganized;
	   private boolean contriSD;
	   private boolean newVillage;
	   private String modifyVillage;
	   private String modifySubDistrict;
	   private String modifyDistrict;
	   private boolean  ShiftDistrict;
		 
	   private boolean newDistrict;
	   private String newSubdistrict;
	   private String fileMapUpLoad;
	   private String noOrderRecord;
	   private String noAttachmentRecord;
	   private String noMapRecord;
	   private String code;
	   private String correctionRadio;
	   private int pageRows;
		private int limit;
		private int offset;
		
		private Integer orderCodecr;
		private String orderNocr;
		private Date orderDatecr;
		private Date ordereffectiveDatecr;
		private Date gazPubDatecr;
		private String orderPathcr;
		private Integer errorflag; 
     
		private Integer stateId;
		public Integer getStateId() {
			return stateId;
		}

		public void setStateId(Integer stateId) {
			this.stateId = stateId;
		}
	   
	   public int getPageRows() {
			return pageRows;
		}

		public void setPageRows(int pageRows) {
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

	public String getCorrectionRadio() {
		return correctionRadio;
	}

	public void setCorrectionRadio(String correctionRadio) {
		this.correctionRadio = correctionRadio;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private List<StateWiseEntityDetails> stateWiseEntityDetails = new ArrayList<StateWiseEntityDetails>();
	   //Added by Arnab  Start
		  
	   

	public List<StateWiseEntityDetails> getStateWiseEntityDetails() {
		return stateWiseEntityDetails;
	}

	public void setStateWiseEntityDetails(
			List<StateWiseEntityDetails> stateWiseEntityDetails) {
		this.stateWiseEntityDetails = stateWiseEntityDetails;
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

	private Integer globalstateId;
		  
	   public Integer getGlobalstateId() 
	   {
			return globalstateId;
	   }

	   public void setGlobalstateId(Integer globalstateId) 
	   {
			this.globalstateId = globalstateId;
	   }

		//Added by Arnab  End
		 
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
			
			private MultipartFile filePath;
			
			private Integer orderCode;
			private String orderNo;
			@DateTimeFormat(pattern = "dd-MM-yyyy")
			private Date orderDate;
			private Date ordereffectiveDate;
			@DateTimeFormat(pattern = "dd-MM-yyyy")
			private Date gazPubDate;
			private String orderPath;
			private char operation;
			private String templateList;
			private String govtOrderConfig;
			
		private List<StateHistory> stateHistoryDetail = new ArrayList<StateHistory>();
		
	public List<StateHistory> getStateHistoryDetail() {
			return stateHistoryDetail;
		}

		public void setStateHistoryDetail(List<StateHistory> stateHistoryDetail) {
			this.stateHistoryDetail = stateHistoryDetail;
		}

	public String getFilePathMapUpLoad() {
		return filePathMapUpLoad;
	}

	public void setFilePathMapUpLoad(String filePathMapUpLoad) {
		this.filePathMapUpLoad = filePathMapUpLoad;
	}

	public String getSubDistrictList() {
		return subDistrictList;
	}

	public void setSubDistrictList(String subDistrictList) {
		this.subDistrictList = subDistrictList;
	}

	public String getDistrictAliasInLcl() {
		return districtAliasInLcl;
	}

	public void setDistrictAliasInLcl(String districtAliasInLcl) {
		this.districtAliasInLcl = districtAliasInLcl;
	}

public String getDistrictNameInEn() {
		return districtNameInEn;
	}

	public void setDistrictNameInEn(String districtNameInEn) {
		this.districtNameInEn = districtNameInEn;
	}


	
	private List<State> listStateDetail = new ArrayList<State>();
    private List<StateDataForm> listStateDetails = new ArrayList<StateDataForm>();
    
	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}

	public StatePK getStatePK() {
		return statePK;
	}

	public void setStatePK(StatePK statePK) {
		this.statePK = statePK;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	public String getStateNameLocal() {
		return stateNameLocal;
	}

	public void setStateNameLocal(String stateNameLocal) {
		this.stateNameLocal = stateNameLocal;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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


	public char getStateOrUt() {
		return stateOrUt;
	}

	public void setStateOrUt(char stateOrUt) {
		this.stateOrUt = stateOrUt;
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

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public int getStateVersion() {
		return stateVersion;
	}

	public void setStateVersion(int stateVersion) {
		this.stateVersion = stateVersion;
	}

	public boolean isCorrection() {
		return correction;
	}

	public void setCorrection(boolean correction) {
		this.correction = correction;
	}

	public List<State> getListStateDetail() {
		return listStateDetail;
	}

	public void setListStateDetail(List<State> listStateDetail) {
		this.listStateDetail = listStateDetail;
	}

	public List<StateDataForm> getListStateDetails() {
		return listStateDetails;
	}

	public void setListStateDetails(List<StateDataForm> listStateDetails) {
		this.listStateDetails = listStateDetails;
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

	public String getDistrictHeadquarters() {
		return districtHeadquarters;
	}

	public void setDistrictHeadquarters(String districtHeadquarters) {
		this.districtHeadquarters = districtHeadquarters;
	}

	public String getStateSpecificCode() {
		return stateSpecificCode;
	}

	public void setStateSpecificCode(String stateSpecificCode) {
		this.stateSpecificCode = stateSpecificCode;
	}

	public String getContributedSubDistricts() {
		return contributedSubDistricts;
	}

	public void setContributedSubDistricts(String contributedSubDistricts) {
		this.contributedSubDistricts = contributedSubDistricts;
	}

	public String getContributedVillages() {
		return contributedVillages;
	}

	public void setContributedVillages(String contributedVillages) {
		this.contributedVillages = contributedVillages;
	}

	public String getStateNameEnglishMain() {
		return stateNameEnglishMain;
	}

	public void setStateNameEnglishMain(String stateNameEnglishMain) {
		this.stateNameEnglishMain = stateNameEnglishMain;
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

	public int getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
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

	public String getSscode() {
		return sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

		public String getReorganized() {
		return reorganized;
	}

	public String isReorganized() {
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

	public boolean isNewDistrict() {
		return newDistrict;
	}

	public void setNewDistrict(boolean newDistrict) {
		this.newDistrict = newDistrict;
	}

	public String getAddAnotherSD() {
		return addAnotherSD;
	}

	public void setAddAnotherSD(String addAnotherSD) {
		this.addAnotherSD = addAnotherSD;
	}

	public String getDistrictNameEnglish() {
		return DistrictNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		DistrictNameEnglish = districtNameEnglish;
	}

	public String getSTATENAMEENGLISH() {
		return STATENAMEENGLISH;
	}

	public void setSTATENAMEENGLISH(String sTATENAMEENGLISH) {
		STATENAMEENGLISH = sTATENAMEENGLISH;
	}

	public String getModifyDistrict() {
		return modifyDistrict;
	}

	public void setModifyDistrict(String modifyDistrict) {
		this.modifyDistrict = modifyDistrict;
	}

	public boolean isShiftDistrict() {
		return ShiftDistrict;
	}

	public void setShiftDistrict(boolean shiftDistrict) {
		ShiftDistrict = shiftDistrict;
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

	/*public String getLati() {
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
	}*/

	/*public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}*/

	public String getfileMapUpLoad() {
		return fileMapUpLoad;
	}

	public void setfileMapUpLoad(String fileMapUpLoad) {
		this.fileMapUpLoad = fileMapUpLoad;
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

	public String getCordinate() {
		return cordinate;
	}

	public void setCordinate(String cordinate) {
		this.cordinate = cordinate;
	}

	public String getStateNameEnglishch() {
		return stateNameEnglishch;
	}

	public void setStateNameEnglishch(String stateNameEnglishch) {
		this.stateNameEnglishch = stateNameEnglishch;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGovernmentOrder() {
		return governmentOrder;
	}

	public void setGovernmentOrder(String governmentOrder) {
		this.governmentOrder = governmentOrder;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
