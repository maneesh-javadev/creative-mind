package in.nic.pes.lgd.bean;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "notification_log")
public class NotificationLog implements Serializable {
	private static final long serialVersionUID = 1L;
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "notification_log_msg_id_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "msg_id", unique = true, nullable = false)
	private Integer msgId;

	@Column(name = "msg_desc")
	private String msgDesc;

	@Column(name = "msg_type")
	private String msgType;

	@Column(name = "msg_status")
	private String msgStatus;

	@Column(name = "msg_size ")
	private Integer msgSize;

	@Column(name = "module")
	private String module;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "cc_email_id")
	private String ccEmailId;
	
	@Column(name = "bcc_email_id")
	private String bccEmailId;
	
	@Column(name = "reciever_id")
	private Integer recieverid;
	
	@Column(name = "registration_srno")
	private Integer registrationSrno;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_on")
	private Date createdOn;
		
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "host_id")
	private Integer hostId;
	
	@Column(name = "count")
	private Integer count;
	
	@Transient
	private File file;
	

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getMsgDesc() {
		return msgDesc;
	}

	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public Integer getMsgSize() {
		return msgSize;
	}

	public void setMsgSize(Integer msgSize) {
		this.msgSize = msgSize;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCcEmailId() {
		return ccEmailId;
	}

	public void setCcEmailId(String ccEmailId) {
		this.ccEmailId = ccEmailId;
	}

	public String getBccEmailId() {
		return bccEmailId;
	}

	public void setBccEmailId(String bccEmailId) {
		this.bccEmailId = bccEmailId;
	}

	public Integer getRecieverid() {
		return recieverid;
	}

	public void setRecieverid(Integer recieverid) {
		this.recieverid = recieverid;
	}

	public Integer getRegistrationSrno() {
		return registrationSrno;
	}

	public void setRegistrationSrno(Integer registrationSrno) {
		this.registrationSrno = registrationSrno;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getHostId() {
		return hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	
	
	
	
	
	
	
	

}
