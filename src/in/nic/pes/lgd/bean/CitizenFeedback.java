package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name="citizen_feedback")
public class CitizenFeedback implements Serializable{
	
@Id
@SequenceGenerator(name="FEEDBACK_FBNO_GENERTOR",sequenceName="FEEDBACK_FBNO_SEQ",initialValue=1,allocationSize=1)
@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="FEEDBACK_FBNO_GENERTOR")
@Column(name="fbno")
private Integer fbno;
@Column(name="citizen_name")
private String citizenName;
@Column(name="citizen_email")
private String citizenEmail;
@Column(name="feedback")
private String feedback;
@Column(name="createdon")
private Date createdon;
@Column(name="status")
private String status;
@Transient
private String captchaAnswer;
public Integer getFbno() {
	return fbno;
}
public void setFbno(Integer fbno) {
	this.fbno = fbno;
}
public String getCitizenName() {
	return citizenName;
}
public void setCitizenName(String citizenName) {
	this.citizenName = citizenName;
}
public String getCitizenEmail() {
	return citizenEmail;
}
public void setCitizenEmail(String citizenEmail) {
	this.citizenEmail = citizenEmail;
}
public String getFeedback() {
	return feedback;
}
public void setFeedback(String feedback) {
	this.feedback = feedback;
}

public Date getCreatedon() {
	return createdon;
}
public void setCreatedon(Date createdon) {
	this.createdon = createdon;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getCaptchaAnswer() {
	return captchaAnswer;
}
public void setCaptchaAnswer(String captchaAnswer) {
	this.captchaAnswer = captchaAnswer;
}

}
