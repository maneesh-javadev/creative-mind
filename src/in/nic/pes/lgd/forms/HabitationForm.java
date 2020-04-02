package in.nic.pes.lgd.forms;

import java.util.Date;

public class HabitationForm {

	private Integer habitationCode;
	private Integer habitationVersion;
	private String habitationNameEnglish;
	private String habitationNameLocal;
	private String parentType;
	private String parentTypeName;
	private Integer parentCode;
	private boolean isactive;
	private Date effectiveDate;
	private long createdby;
	private Date createdon;
	private long lastupdatedby;
	private Date lastupdated;
	private Integer flagcode;
	private Integer transactionId;
	private String sscode;
	private String setErrorMsg;
	private Integer vparentCode;
	private String captchaAnswer;
	private String paramLocalBodyTypeCode;
	private Integer paramVillageCode;
	private String localBodyLevelCodes;
	private Integer slc;
	
	
	public String getCaptchaAnswer() {
		return captchaAnswer;
	}
	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}
	public Integer getVparentCode() {
		return vparentCode;
	}
	public void setVparentCode(Integer vparentCode) {
		this.vparentCode = vparentCode;
	}
	public Integer getHabitationCode() {
		return habitationCode;
	}
	public void setHabitationCode(Integer habitationCode) {
		this.habitationCode = habitationCode;
	}
	public Integer getHabitationVersion() {
		return habitationVersion;
	}
	public void setHabitationVersion(Integer habitationVersion) {
		this.habitationVersion = habitationVersion;
	}
	public String getHabitationNameEnglish() {
		return habitationNameEnglish;
	}
	public void setHabitationNameEnglish(String habitationNameEnglish) {
		this.habitationNameEnglish = habitationNameEnglish;
	}
	public String getHabitationNameLocal() {
		return habitationNameLocal;
	}
	public void setHabitationNameLocal(String habitationNameLocal) {
		this.habitationNameLocal = habitationNameLocal;
	}
	
	
	
	public Integer getParentCode() {
		return parentCode;
	}
	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public long getCreatedby() {
		return createdby;
	}
	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public long getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	public Integer getFlagcode() {
		return flagcode;
	}
	public void setFlagcode(Integer flagcode) {
		this.flagcode = flagcode;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public String getSscode() {
		return sscode;
	}
	public void setSscode(String sscode) {
		this.sscode = sscode;
	}
	public String getSetErrorMsg() {
		return setErrorMsg;
	}
	public void setSetErrorMsg(String setErrorMsg) {
		this.setErrorMsg = setErrorMsg;
	}
	public String getParamLocalBodyTypeCode() {
		return paramLocalBodyTypeCode;
	}
	public void setParamLocalBodyTypeCode(String paramLocalBodyTypeCode) {
		this.paramLocalBodyTypeCode = paramLocalBodyTypeCode;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public String getParentTypeName() {
		return parentTypeName;
	}
	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}
	public Integer getParamVillageCode() {
		return paramVillageCode;
	}
	public void setParamVillageCode(Integer paramVillageCode) {
		this.paramVillageCode = paramVillageCode;
	}
	public String getLocalBodyLevelCodes() {
		return localBodyLevelCodes;
	}
	public void setLocalBodyLevelCodes(String localBodyLevelCodes) {
		this.localBodyLevelCodes = localBodyLevelCodes;
	}
	public Integer getSlc() {
		return slc;
	}
	public void setSlc(Integer slc) {
		this.slc = slc;
	}
}
