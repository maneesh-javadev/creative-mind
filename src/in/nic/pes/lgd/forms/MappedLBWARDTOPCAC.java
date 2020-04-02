package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.StatePK;

public class MappedLBWARDTOPCAC 
{
private Integer localBodyCode;
private String localBodyName;
private String localBodyTypeName;
private Integer wardCode;
private String wardName;
private String parlimentNameEnglish;
private String acNameEnglish;
private String covergaeType;
private String stateNameEnglish;
private StatePK statePK;
private String captchaAnswer;
private String captchaAnswers;
private Boolean pcCheck;

public StatePK getStatePK() {
	return statePK;
}
public void setStatePK(StatePK statePK) {
	this.statePK = statePK;
}
public String getCaptchaAnswer() {
	return captchaAnswer;
}
public void setCaptchaAnswer(String captchaAnswer) {
	this.captchaAnswer = captchaAnswer;
}
public String getCaptchaAnswers() {
	return captchaAnswers;
}
public void setCaptchaAnswers(String captchaAnswers) {
	this.captchaAnswers = captchaAnswers;
}
public String getStateNameEnglish() {
	return stateNameEnglish;
}
public void setStateNameEnglish(String stateNameEnglish) {
	this.stateNameEnglish = stateNameEnglish;
}
public Integer getStateCode() {
	return stateCode;
}
public void setStateCode(Integer stateCode) {
	this.stateCode = stateCode;
}
private Integer stateCode;

public Integer getLocalBodyCode() {
	return localBodyCode;
}
public void setLocalBodyCode(Integer localBodyCode) {
	this.localBodyCode = localBodyCode;
}
public String getLocalBodyName() {
	return localBodyName;
}
public void setLocalBodyName(String localBodyName) {
	this.localBodyName = localBodyName;
}
public String getLocalBodyTypeName() {
	return localBodyTypeName;
}
public void setLocalBodyTypeName(String localBodyTypeName) {
	this.localBodyTypeName = localBodyTypeName;
}
public Integer getWardCode() {
	return wardCode;
}
public void setWardCode(Integer wardCode) {
	this.wardCode = wardCode;
}
public String getWardName() {
	return wardName;
}
public void setWardName(String wardName) {
	this.wardName = wardName;
}
public String getCovergaeType() {
	return covergaeType;
}
public void setCovergaeType(String covergaeType) {
	this.covergaeType = covergaeType;
}
public String getParlimentNameEnglish() {
	return parlimentNameEnglish;
}
public void setParlimentNameEnglish(String parlimentNameEnglish) {
	this.parlimentNameEnglish = parlimentNameEnglish;
}
public String getAcNameEnglish() {
	return acNameEnglish;
}
public void setAcNameEnglish(String acNameEnglish) {
	this.acNameEnglish = acNameEnglish;
}
public Boolean getPcCheck() {
	return pcCheck;
}
public void setPcCheck(Boolean pcCheck) {
	this.pcCheck = pcCheck;
}
}
