package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTypeHistory;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;

import java.util.ArrayList;
import java.util.List;

public class LocalGovtTypeDataForm {

    private String localBodyTypeName;   
    private char category;   
    private char level;
	private int localBodyTypeCode; 
	private boolean isActive;
	private boolean sixthSchedulGovt;
	private boolean ispartixgovt;
	private String code;
	private int recordsLimit;
	private int localBodyTypeVersion;
	private boolean correction;
	private String pageType;
	private int offset;
	private Integer limit;
	private String stateNameEnglish;
	private int pageRows;
    private int direction;
    
    //Code added by Arnab  Start
    private Integer localgovtId;
    
	public Integer getLocalgovtId() {
		return localgovtId;
	}
	public void setLocalgovtId(Integer localgovtId) {
		this.localgovtId = localgovtId;
	}
	//Code added by Arnab  End
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
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public int getPageRows() {
		return pageRows;
	}
	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
		this.limit = pageRows;
	}
	
	
	private List<LocalBodyType> listGovtTypeDetail = new ArrayList<LocalBodyType>();	
	private List<LocalGovtTypeDataForm> listGovtTypeDetails = new ArrayList<LocalGovtTypeDataForm>();
	private List<LocalBodyTypeHistory> listLocalBodyTypeHistory = new ArrayList<LocalBodyTypeHistory>();
	private List<StateWiseEntityDetails> stateWiseEntityDetails = new ArrayList<StateWiseEntityDetails>();
	
	
    public List<LocalBodyTypeHistory> getListLocalBodyTypeHistory() {
		return listLocalBodyTypeHistory;
	}
	public void setListLocalBodyTypeHistory(
			List<LocalBodyTypeHistory> listLocalBodyTypeHistory) {
		this.listLocalBodyTypeHistory = listLocalBodyTypeHistory;
	}
	public List<StateWiseEntityDetails> getStateWiseEntityDetails() {
		return stateWiseEntityDetails;
	}
	public void setStateWiseEntityDetails(
			List<StateWiseEntityDetails> stateWiseEntityDetails) {
		this.stateWiseEntityDetails = stateWiseEntityDetails;
	}
	public int getLocalBodyTypeVersion() {
		return localBodyTypeVersion;
	}
	public void setLocalBodyTypeVersion(int localBodyTypeVersion) {
		this.localBodyTypeVersion = localBodyTypeVersion;
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
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	public char getLevel() {
		return level;
	}
	public void setLevel(char level) {
		this.level = level;
	}
    
	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public List<LocalBodyType> getListGovtTypeDetail() {
		return listGovtTypeDetail;
	}
	public void setListGovtTypeDetail(List<LocalBodyType> listGovtTypeDetail) {
		this.listGovtTypeDetail = listGovtTypeDetail;
	}
	public List<LocalGovtTypeDataForm> getListGovtTypeDetails() {
		return listGovtTypeDetails;
	}
	public void setListGovtTypeDetails(
			List<LocalGovtTypeDataForm> listGovtTypeDetails) {
		this.listGovtTypeDetails = listGovtTypeDetails;
	}
	
	public boolean isCorrection() {
		return correction;
	}
	public void setCorrection(boolean correction) {
		this.correction = correction;
	}
	public boolean isSixthSchedulGovt() {
		return sixthSchedulGovt;
	}
	public void setSixthSchedulGovt(boolean sixthSchedulGovt) {
		this.sixthSchedulGovt = sixthSchedulGovt;
	}
	public boolean isIspartixgovt() {
		return ispartixgovt;
	}
	public void setIspartixgovt(boolean ispartixgovt) {
		this.ispartixgovt = ispartixgovt;
	}
	
	
}
