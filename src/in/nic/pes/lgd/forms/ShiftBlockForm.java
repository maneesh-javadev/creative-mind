package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.StatePK;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ShiftBlockForm {

	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date effectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;
	private StatePK statePK;
	private int stateVersion;
	private int stateCode;
	private String stateNameEnglish;	
	private String districtNameEnglish;
	private String districtNameEnglishSource;
	private String districtNameEnglishDest;
	private String districtSName;
	private String districtDName;
	private String blockName;
	
	private String blockNameEnglish;
	private int operationCode;
	private String govtOrderConfig;	
	private String finalsourceDistName;
	private String finaltargetBlockName;
	public String getFinaltargetBlockName() {
		return finaltargetBlockName;
	}

	public void setFinaltargetBlockName(String finaltargetBlockName) {
		this.finaltargetBlockName = finaltargetBlockName;
	}

	public String getFinalsourceDistName() {
		return finalsourceDistName;
	}

	public void setFinalsourceDistName(String finalsourceDistName) {
		this.finalsourceDistName = finalsourceDistName;
	}

	public String getFinaltargetDistName() {
		return finaltargetDistName;
	}

	public void setFinaltargetDistName(String finaltargetDistName) {
		this.finaltargetDistName = finaltargetDistName;
	}

	private String finaltargetDistName;
	
	public String getDistrictNameEnglishSource() {
		return districtNameEnglishSource;
	}

	public void setDistrictNameEnglishSource(String districtNameEnglishSource) {
		this.districtNameEnglishSource = districtNameEnglishSource;
	}

	public String getDistrictNameEnglishDest() {
		return districtNameEnglishDest;
	}

	public void setDistrictNameEnglishDest(String districtNameEnglishDest) {
		this.districtNameEnglishDest = districtNameEnglishDest;
	}

	public String getDistrictSName() {
		return districtSName;
	}

	public void setDistrictSName(String districtSName) {
		this.districtSName = districtSName;
	}

	public String getDistrictDName() {
		return districtDName;
	}

	public void setDistrictDName(String districtDName) {
		this.districtDName = districtDName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
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

	private String templateList;
	

	
	

	public int getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}

	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}

	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
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

	
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	
}
