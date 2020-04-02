package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.LocalBodyType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class LocalGovtTypeForm {
	
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date effectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;			  
    private String localBodyTypeName;   
    private String category;   
    private String level;
    private boolean sixthSchedulGovt;
    private String categoryRadio;
    private String ruralCategory;
	private String localBodyTypeCode; 
	private String code;
	private int recordsLimit;	
	private boolean correction;
	private String govtOrderConfig;
	private String localBodyTypeName1;
	private List<CommonsMultipartFile> attachFile2;
	private char operation;
	 //Code added by Arnab  Start
    private Integer localgovtId;
    
	public Integer getLocalgovtId() {
		return localgovtId;
	}
	public void setLocalgovtId(Integer localgovtId) {
		this.localgovtId = localgovtId;
	}
	//Code added by Arnab  End
	
	  
	public char getOperation() {
		return operation;
	}
	public void setOperation(char operation) {
		this.operation = operation;
	}
	public String getLocalBodyTypeName1() {
		return localBodyTypeName1;
	}
	public void setLocalBodyTypeName1(String localBodyTypeName1) {
		this.localBodyTypeName1 = localBodyTypeName1;
	}
	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}
	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}
	private List<LocalBodyType> listGovtTypeDetail = new ArrayList<LocalBodyType>();
	private List<LocalGovtTypeDataForm> listGovtTypeDetails = new ArrayList<LocalGovtTypeDataForm>();
	private List<LocalGovtTypeDataForm> listdetail = new ArrayList<LocalGovtTypeDataForm>();
	public String getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	public void setLocalBodyTypeCode(String localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public List<LocalGovtTypeDataForm> getListdetail() {
		return listdetail;
	}
	public void setListdetail(List<LocalGovtTypeDataForm> listdetail) {
		this.listdetail = listdetail;
	}
	public boolean isSixthSchedulGovt() {
		return sixthSchedulGovt;
	}
	public void setSixthSchedulGovt(boolean sixthSchedulGovt) {
		this.sixthSchedulGovt = sixthSchedulGovt;
	}
	public List<LocalBodyType> getListGovtTypeDetail() {
		return listGovtTypeDetail;
	}
	public void setListGovtTypeDetail(List<LocalBodyType> listGovtTypeDetail) {
		this.listGovtTypeDetail = listGovtTypeDetail;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCategoryRadio() {
		return categoryRadio;
	}
	public void setCategoryRadio(String categoryRadio) {
		this.categoryRadio = categoryRadio;
	}
	public String getRuralCategory() {
		return ruralCategory;
	}
	public void setRuralCategory(String ruralCategory) {
		this.ruralCategory = ruralCategory;
	}
	public List<CommonsMultipartFile> getAttachFile2() {
		return attachFile2;
	}
	public void setAttachFile2(List<CommonsMultipartFile> attachFile2) {
		this.attachFile2 = attachFile2;
	}
	
	

	
}
