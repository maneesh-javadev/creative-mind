package in.nic.pes.lgd.forms;


import in.nic.pes.lgd.bean.ParliamentConstituency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class ParliamentDataForm {
	
	    private int pcCode;
	    private int pcVersion;
        private String pcNameEnglish;
	    private String pcNameLocal;
	    private Integer eciCode;
	    private Boolean isdisturbed;
	    private Date effectiveDate;
	    private boolean isactive;
	    private Date lastupdated;
	    private long lastupdatedby;
	    private Date createdon;
	    private long createdby;
	    private long state_code;
	    private long state_version;
	    private int orderCode;
		private String orderNo;
		private Date orderDate;
		private Date effectiveDateorder;
		private String issuedBy;
		private String userId;
		//private int templateCode;
		//private char status;
		private MultipartFile  file;
		private String xml_order_path;
		private String xml_db_path;
		private int createdbyorder;
		private Date createdonorder;
		private int user_id;
		private Date gazPubDate;
	    private String districtNameEnglish;
	    private String subdistrictNameEnglish;
	    private String newParliamentNameEnglish;
	    private String newParliamentNameLocal;
	    private String newParliamentAliasEnglish;
	    private String  newParliamentAliasLocal;
	    private int  ParliamentECICODE;
	    private String  ParliamentPC;
		private MultipartFile filePath;
		private String ParliamentList;
		private String contributedParliament;
		private String stateNameEnglish;
		private String STATENAMEENGLISH;
		private String contributedAssembly;
		private String AssemblyList;
		private String districtHeadquarters;
		private String statecode;
		private String longi;
		private String mapUrl;
		private String latitude;
		private String longitude;
		private String lati;
		private String cordinate;
	    private String fileMapUpLoad;
	    private Integer mapLandregionCode;
	    private String orderPathcr;
	    private String orderNocr;
	    private MultipartFile filePathcr;
	    
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
	    
		private int pageRows;
		private int limit;
		private int offset;
		private int direction;
		private String pageType;
	    
		private List<ParliamentConstituency> listParliamentConstituencyDetail = new ArrayList<ParliamentConstituency>();
		private List<ParliamentDataForm> listParliamentFormDetail = new ArrayList<ParliamentDataForm>();

		public int getPcCode() {
			return pcCode;
		}
		public void setPcCode(int pcCode) {
			this.pcCode = pcCode;
		}
		public int getPcVersion() {
			return pcVersion;
		}
		public void setPcVersion(int pcVersion) {
			this.pcVersion = pcVersion;
		}
		public String getPcNameEnglish() {
			return pcNameEnglish;
		}
		public void setPcNameEnglish(String pcNameEnglish) {
			this.pcNameEnglish = pcNameEnglish;
		}
		public String getPcNameLocal() {
			return pcNameLocal;
		}
		public void setPcNameLocal(String pcNameLocal) {
			this.pcNameLocal = pcNameLocal;
		}
		public Integer getEciCode() {
			return eciCode;
		}
		public void setEciCode(Integer eciCode) {
			this.eciCode = eciCode;
		}
		public Boolean getIsdisturbed() {
			return isdisturbed;
		}
		public void setIsdisturbed(Boolean isdisturbed) {
			this.isdisturbed = isdisturbed;
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
		public long getState_code() {
			return state_code;
		}
		public void setState_code(long state_code) {
			this.state_code = state_code;
		}
		public long getState_version() {
			return state_version;
		}
		public void setState_version(long state_version) {
			this.state_version = state_version;
		}
		public int getOrderCode() {
			return orderCode;
		}
		public void setOrderCode(int orderCode) {
			this.orderCode = orderCode;
		}
		
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		
		public String getIssuedBy() {
			return issuedBy;
		}
		public void setIssuedBy(String issuedBy) {
			this.issuedBy = issuedBy;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
		
		
		
	
		
		
		
		public MultipartFile getFile() {
			return file;
		}
		public void setFile(MultipartFile file) {
			this.file = file;
		}
		public String getXml_order_path() {
			return xml_order_path;
		}
		public void setXml_order_path(String xml_order_path) {
			this.xml_order_path = xml_order_path;
		}
		public String getXml_db_path() {
			return xml_db_path;
		}
		public void setXml_db_path(String xml_db_path) {
			this.xml_db_path = xml_db_path;
		}
		public int getCreatedbyorder() {
			return createdbyorder;
		}
		public void setCreatedbyorder(int createdbyorder) {
			this.createdbyorder = createdbyorder;
		}
		public Date getCreatedonorder() {
			return createdonorder;
		}
		public void setCreatedonorder(Date createdonorder) {
			this.createdonorder = createdonorder;
		}
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int user_id) {
			this.user_id = user_id;
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
		public String getNewParliamentNameEnglish() {
			return newParliamentNameEnglish;
		}
		public void setNewParliamentNameEnglish(String newParliamentNameEnglish) {
			this.newParliamentNameEnglish = newParliamentNameEnglish;
		}
		public String getNewParliamentNameLocal() {
			return newParliamentNameLocal;
		}
		public void setNewParliamentNameLocal(String newParliamentNameLocal) {
			this.newParliamentNameLocal = newParliamentNameLocal;
		}
		public String getNewParliamentAliasEnglish() {
			return newParliamentAliasEnglish;
		}
		public void setNewParliamentAliasEnglish(String newParliamentAliasEnglish) {
			this.newParliamentAliasEnglish = newParliamentAliasEnglish;
		}
		public String getNewParliamentAliasLocal() {
			return newParliamentAliasLocal;
		}
		public void setNewParliamentAliasLocal(String newParliamentAliasLocal) {
			this.newParliamentAliasLocal = newParliamentAliasLocal;
		}
		public int getParliamentECICODE() {
			return ParliamentECICODE;
		}
		public void setParliamentECICODE(int parliamentECICODE) {
			ParliamentECICODE = parliamentECICODE;
		}
		public String getParliamentPC() {
			return ParliamentPC;
		}
		public void setParliamentPC(String parliamentPC) {
			ParliamentPC = parliamentPC;
		}
		
		public MultipartFile getFilePath() {
			return filePath;
		}
		public void setFilePath(MultipartFile filePath) {
			this.filePath = filePath;
		}
		public String getParliamentList() {
			return ParliamentList;
		}
		public void setParliamentList(String parliamentList) {
			ParliamentList = parliamentList;
		}
		public String getContributedParliament() {
			return contributedParliament;
		}
		public void setContributedParliament(String contributedParliament) {
			this.contributedParliament = contributedParliament;
		}
		public String getStateNameEnglish() {
			return stateNameEnglish;
		}
		public void setStateNameEnglish(String stateNameEnglish) {
			this.stateNameEnglish = stateNameEnglish;
		}
		public String getContributedAssembly() {
			return contributedAssembly;
		}
		public void setContributedAssembly(String contributedAssembly) {
			this.contributedAssembly = contributedAssembly;
		}
		public String getAssemblyList() {
			return AssemblyList;
		}
		public void setAssemblyList(String assemblyList) {
			AssemblyList = assemblyList;
		}
		public String getDistrictHeadquarters() {
			return districtHeadquarters;
		}
		public void setDistrictHeadquarters(String districtHeadquarters) {
			this.districtHeadquarters = districtHeadquarters;
		}
		public String getStatecode() {
			return statecode;
		}
		public void setStatecode(String statecode) {
			this.statecode = statecode;
		}
		public String getSTATENAMEENGLISH() {
			return STATENAMEENGLISH;
		}
		public void setSTATENAMEENGLISH(String sTATENAMEENGLISH) {
			STATENAMEENGLISH = sTATENAMEENGLISH;
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
			public List<ParliamentConstituency> getListParliamentConstituencyDetail() {
				return listParliamentConstituencyDetail;
			}
			public void setListParliamentConstituencyDetail(
					List<ParliamentConstituency> listParliamentConstituencyDetail) {
				this.listParliamentConstituencyDetail = listParliamentConstituencyDetail;
			}
			public Date getOrderDate() {
				return orderDate;
			}
			public void setOrderDate(Date orderDate) {
				this.orderDate = orderDate;
			}
			public Date getEffectiveDateorder() {
				return effectiveDateorder;
			}
			public void setEffectiveDateorder(Date effectiveDateorder) {
				this.effectiveDateorder = effectiveDateorder;
			}
			public Date getGazPubDate() {
				return gazPubDate;
			}
			public String getCordinate() {
				return cordinate;
			}
			public void setCordinate(String cordinate) {
				this.cordinate = cordinate;
			}
			public void setGazPubDate(Date gazPubDate) {
				this.gazPubDate = gazPubDate;
			}
			public List<ParliamentDataForm> getListParliamentFormDetail() {
				return listParliamentFormDetail;
			}
			public void setListParliamentFormDetail(
					List<ParliamentDataForm> listParliamentFormDetail) {
				this.listParliamentFormDetail = listParliamentFormDetail;
			}
			public String getLati() {
				return lati;
			}
			public void setLati(String lati) {
				this.lati = lati;
			}
			public Integer getMapLandregionCode() {
				return mapLandregionCode;
			}
			public void setMapLandregionCode(Integer mapLandregionCode) {
				this.mapLandregionCode = mapLandregionCode;
			}
			public String getOrderPathcr() {
				return orderPathcr;
			}
			public void setOrderPathcr(String orderPathcr) {
				this.orderPathcr = orderPathcr;
			}
			public String getOrderNocr() {
				return orderNocr;
			}
			public void setOrderNocr(String orderNocr) {
				this.orderNocr = orderNocr;
			}
			public MultipartFile getFilePathcr() {
				return filePathcr;
			}
			public void setFilePathcr(MultipartFile filePathcr) {
				this.filePathcr = filePathcr;
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
			public List<CommonsMultipartFile> getAttachedFilecr() {
				return attachedFilecr;
			}
			public void setAttachedFilecr(List<CommonsMultipartFile> attachedFilecr) {
				this.attachedFilecr = attachedFilecr;
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
	    
}