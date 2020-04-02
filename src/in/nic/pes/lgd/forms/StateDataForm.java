package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class StateDataForm {
	private StatePK statePK;
	private String stateNameEnglish;
	private String stateNameEnglishch;
	private String stateNameLocal;
	private String stateNameLocalch;
	private String shortName;
	private String aliasEnglish;
	private String aliasEnglishch;
	private String aliasLocal;
	private String aliasLocalch;
	private String census2001Code;
	private String census2011Code;
	private char stateOrUt;
	private char stateOrUtch;
    private Integer lrReplaces;
	private Integer lrReplacedby;
	private Integer mapLandregionCode;
	private Integer slc;
	private String fileName;
	private String filelocation;
	private boolean warningflag;
	private Integer minorVersion;
	
    public String getCorrectionRadio() {
		return correctionRadio;
	}

	public void setCorrectionRadio(String correctionRadio) {
		this.correctionRadio = correctionRadio;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	private Integer flagCode;
	private Integer mapCode;
	private Date effectiveDate;
	private boolean isactive;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private int stateCode;
	private int stateVersion;
	private String code;
	private int recordLimit;
	private String cordinate;
	private String latitude;
	private String longitude;
	private String lati;
    private String longi;
	private MultipartFile filePath;
	private String captchaAnswer;
	private String correctionRadio;
	private int pageRows;
	private int limit;
	private int offset;
	
	private String headquarterName;
	private Integer headquarterCode;
	private String headquarterNameLocal;
	private List<StateWiseEntityDetails> stateWiseEntityDetails = new ArrayList<StateWiseEntityDetails>();

	
	public List<StateWiseEntityDetails> getStateWiseEntityDetails() {
		return stateWiseEntityDetails;
	}

	public void setStateWiseEntityDetails(
			List<StateWiseEntityDetails> stateWiseEntityDetails) {
		this.stateWiseEntityDetails = stateWiseEntityDetails;
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

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	private String pageType;
	
	
	   //Added by Arnab  Start
	  
	   public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	
	
	
	private Integer stateId;
	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
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

			
	//government Order
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
		private List<State> listStateDetail = new ArrayList<State>();
		private List<GovernmentOrder> lst = new ArrayList<GovernmentOrder>();
	public List<GovernmentOrder> getLst() {
			return lst;
		}
		public void setLst(List<GovernmentOrder> lst) {
			this.lst = lst;
		}
	public String getCordinate() {
		return cordinate;
	}
	public void setCordinate(String cordinate) {
		this.cordinate = cordinate;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getRecordLimit() {
		return recordLimit;
	}
	public void setRecordLimit(int recordLimit) {
		this.recordLimit = recordLimit;
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
	public String getCensus2011Code() {
		return census2011Code;
	}
	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
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
	public List<State> getListStateDetail() {
		return listStateDetail;
	}
	public void setListStateDetail(List<State> listStateDetail) {
		this.listStateDetail = listStateDetail;
	}
	public String getStateNameEnglishch() {
		return stateNameEnglishch;
	}
	public void setStateNameEnglishch(String stateNameEnglishch) {
		this.stateNameEnglishch = stateNameEnglishch;
	}
	public String getStateNameLocalch() {
		return stateNameLocalch;
	}
	public void setStateNameLocalch(String stateNameLocalch) {
		this.stateNameLocalch = stateNameLocalch;
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

	public String getHeadquarterName() {
		return headquarterName;
	}

	public void setHeadquarterName(String headquarterName) {
		this.headquarterName = headquarterName;
	}

	public Integer getHeadquarterCode() {
		return headquarterCode;
	}

	public void setHeadquarterCode(Integer headquarterCode) {
		this.headquarterCode = headquarterCode;
	}

	public String getHeadquarterNameLocal() {
		return headquarterNameLocal;
	}

	public void setHeadquarterNameLocal(String headquarterNameLocal) {
		this.headquarterNameLocal = headquarterNameLocal;
	}

	public Integer getMapLandregionCode() {
		return mapLandregionCode;
	}

	public void setMapLandregionCode(Integer mapLandregionCode) {
		this.mapLandregionCode = mapLandregionCode;
	}

	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilelocation() {
		return filelocation;
	}

	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}

	public boolean isWarningflag() {
		return warningflag;
	}

	public void setWarningflag(boolean warningflag) {
		this.warningflag = warningflag;
	}

	public char getStateOrUtch() {
		return stateOrUtch;
	}

	public void setStateOrUtch(char stateOrUtch) {
		this.stateOrUtch = stateOrUtch;
	}

	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	
	

}
