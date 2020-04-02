package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.StatePK;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ShiftDistrictForm {

	
	private StatePK statePK;
	private int stateVersion;
	private int stateCode;
	private String stateNameEnglish;
	private DistrictPK districtPK;
	private int districtCode;
	private int districtVersion;
	private String districtNameEnglish;
	private MultipartFile filePath;
	private String stateNameEnglishDest;
	private String stateNameEnglishSource;
	private String stateSName;
	private String stateDName;
	
	private String districtName;
	private int operationCode;
	/*Add Attachment*/
	private String showTitle;
	private int allowedNoOfAttachment;
	private long allowedFileSize;
	private String allowedFileType;
	private String location;
	private List<CommonsMultipartFile> attachedFile;
	
	private String govtOrderConfig;	
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date effectiveDate;
	private Date gazPubDate;
	private String templateList;
	private String orderPath;
	private String finalsourceStateName;
	public String getFinalsourceStateName() {
		return finalsourceStateName;
	}

	public void setFinalsourceStateName(String finalsourceStateName) {
		this.finalsourceStateName = finalsourceStateName;
	}

	public String getFinaltargetStateName() {
		return finaltargetStateName;
	}

	public void setFinaltargetStateName(String finaltargetStateName) {
		this.finaltargetStateName = finaltargetStateName;
	}

	public String getFinalDestName() {
		return finalDestName;
	}

	public void setFinalDestName(String finalDestName) {
		this.finalDestName = finalDestName;
	}

	private String finaltargetStateName;
	private String finalDestName;
	

	public String getOrderPath() {
		return orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
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

	public int getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public int getAllowedNoOfAttachment() {
		return allowedNoOfAttachment;
	}

	public void setAllowedNoOfAttachment(int allowedNoOfAttachment) {
		this.allowedNoOfAttachment = allowedNoOfAttachment;
	}

	public long getAllowedFileSize() {
		return allowedFileSize;
	}

	public void setAllowedFileSize(long allowedFileSize) {
		this.allowedFileSize = allowedFileSize;
	}

	public String getAllowedFileType() {
		return allowedFileType;
	}

	public void setAllowedFileType(String allowedFileType) {
		this.allowedFileType = allowedFileType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<CommonsMultipartFile> getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(List<CommonsMultipartFile> attachedFile) {
		this.attachedFile = attachedFile;
	}
	
	/*Add Attachment*/

	public String getStateSName() {
		return stateSName;
	}

	public void setStateSName(String stateSName) {
		this.stateSName = stateSName;
	}

	public String getStateDName() {
		return stateDName;
	}

	public void setStateDName(String stateDName) {
		this.stateDName = stateDName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateNameEnglishDest() {
		return stateNameEnglishDest;
	}

	public void setStateNameEnglishDest(String stateNameEnglishDest) {
		this.stateNameEnglishDest = stateNameEnglishDest;
	}

	public String getStateNameEnglishSource() {
		return stateNameEnglishSource;
	}

	public void setStateNameEnglishSource(String stateNameEnglishSource) {
		this.stateNameEnglishSource = stateNameEnglishSource;
	}

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

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
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

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	


}
