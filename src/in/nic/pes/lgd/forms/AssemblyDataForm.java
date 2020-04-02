package in.nic.pes.lgd.forms;


import java.util.Date;

import org.springframework.web.multipart.MultipartFile;



public class AssemblyDataForm {
	    private String acNameEnglish;
	    private String acNameLocal;
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
		private String orderDate;
		private String effectiveDateorder;
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
		private String gazPubDate;
	    private String districtNameEnglish;
	    private String subdistrictNameEnglish;
	    
	    private String newAssemblyNameEnglish;
	    private String newAssemblyNameLocal;
	    private String newAssemblyAliasEnglish;
	    private String  newAssemblyAliasLocal;
	    private int  AssemblyECICODE;
	    private String  AssemblyC;
		private MultipartFile filePath;
		private String ParliamentList;
		private String contributedParliament;
		private String stateNameEnglish;
		private String STATENAMEENGLISH;
		private String contributedAssembly;
		private String AssemblyList;
		private String districtHeadquarters;
		private String statecode;
		private String lati;
		private String longi;
		private String mapUrl;
		private String latitude;
		private String longitude;
		private String fileMapUpLoad;
		private int acCode;
	    private int acVersion;
		
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
		
		public String getOrderDate() {
			return orderDate;
		}
		public void setOrderDate(String orderDate) {
			this.orderDate = orderDate;
		}
		public String getEffectiveDateorder() {
			return effectiveDateorder;
		}
		public void setEffectiveDateorder(String effectiveDateorder) {
			this.effectiveDateorder = effectiveDateorder;
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
		
		public String getGazPubDate() {
			return gazPubDate;
		}
		public void setGazPubDate(String gazPubDate) {
			this.gazPubDate = gazPubDate;
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
		public String getNewAssemblyNameEnglish() {
			return newAssemblyNameEnglish;
		}
		public void setNewAssemblyNameEnglish(String newAssemblyNameEnglish) {
			this.newAssemblyNameEnglish = newAssemblyNameEnglish;
		}
		public String getNewAssemblyNameLocal() {
			return newAssemblyNameLocal;
		}
		public void setNewAssemblyNameLocal(String newAssemblyNameLocal) {
			this.newAssemblyNameLocal = newAssemblyNameLocal;
		}
		public String getNewAssemblyAliasEnglish() {
			return newAssemblyAliasEnglish;
		}
		public void setNewAssemblyAliasEnglish(String newAssemblyAliasEnglish) {
			this.newAssemblyAliasEnglish = newAssemblyAliasEnglish;
		}
		public String getNewAssemblyAliasLocal() {
			return newAssemblyAliasLocal;
		}
		public void setNewAssemblyAliasLocal(String newAssemblyAliasLocal) {
			this.newAssemblyAliasLocal = newAssemblyAliasLocal;
		}
		public int getAssemblyECICODE() {
			return AssemblyECICODE;
		}
		public void setAssemblyECICODE(int assemblyECICODE) {
			AssemblyECICODE = assemblyECICODE;
		}
		public String getAssemblyC() {
			return AssemblyC;
		}
		public void setAssemblyC(String assemblyC) {
			AssemblyC = assemblyC;
		}
		public void setSTATENAMEENGLISH(String sTATENAMEENGLISH) {
			STATENAMEENGLISH = sTATENAMEENGLISH;
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
		public String getAcNameEnglish() {
			return acNameEnglish;
		}
		public void setAcNameEnglish(String acNameEnglish) {
			this.acNameEnglish = acNameEnglish;
		}
		public String getAcNameLocal() {
			return acNameLocal;
		}
		public void setAcNameLocal(String acNameLocal) {
			this.acNameLocal = acNameLocal;
		}
		public int getAcCode() {
			return acCode;
		}
		public void setAcCode(int acCode) {
			this.acCode = acCode;
		}
		public int getAcVersion() {
			return acVersion;
		}
		public void setAcVersion(int acVersion) {
			this.acVersion = acVersion;
		}
}