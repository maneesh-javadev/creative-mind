package in.nic.pes.lgd.forms;

import java.util.Date;

/**
 * DownloadDirectoryForm for New Download Directory
 * @author Maneesh Kumar
 *
 */
public class DownloadDirectoryForm {

	
	private String downloadOption;
	
	private String rptFileName;
	
	private String rptFileNameMod;

	private Integer entityCode;

	private String downloadType;

	private String captchaAnswer;
	
	private String entityCodes;
	
	private String multiRptFileNames;
	
	private Date fromDate;
	
	private Date toDate;
	
	private String blockName;
	
	private String districtName;
	
	private String stateName;

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getRptFileName() {
		return rptFileName;
	}

	public void setRptFileName(String rptFileName) {
		this.rptFileName = rptFileName;
	}

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public String getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	public String getDownloadOption() {
		return downloadOption;
	}

	public void setDownloadOption(String downloadOption) {
		this.downloadOption = downloadOption;
	}

	

	public String getMultiRptFileNames() {
		return multiRptFileNames;
	}

	public void setMultiRptFileNames(String multiRptFileNames) {
		this.multiRptFileNames = multiRptFileNames;
	}

	public String getEntityCodes() {
		return entityCodes;
	}

	public void setEntityCodes(String entityCodes) {
		this.entityCodes = entityCodes;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getRptFileNameMod() {
		return rptFileNameMod;
	}

	public void setRptFileNameMod(String rptFileNameMod) {
		this.rptFileNameMod = rptFileNameMod;
	}
	
	

}
