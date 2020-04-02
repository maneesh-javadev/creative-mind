package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "reported_issues")
public class ReportingIssue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "reported_issues_sr_no_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "sr_no", unique = true, nullable = false)
	private Integer serialNumber;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="registered_user_id", referencedColumnName="srno", nullable=true)
	private UserRegistration registration;
	
	@Column(name = "submitted_to_state")
	private int SuubmittedToState;
	
	@Column(name = "submitted_to_type")
	private Character submittedToType;
	
	@Column(name = "submitted_on")
	private Date submittedOn;
	
	@Column(name="reported_issue_text")
	private String reportedIssueText;
	
	@Column(name="reply_text")
	private String replyText;
	
	@Column(name = "replied_on")
	private Date repliedOn;

	@Column(name="submitted_from_ip")
	private String submittedFromIp;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="submitted_to", referencedColumnName="nodal_officer_id", nullable=true)
	private NodalOfficerState nodalOfficer;
	
	@Column(name = "submitted_to_center")
	private Integer submittedToCenter;
	
	
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public UserRegistration getRegistration() {
		return registration;
	}

	public void setRegistration(UserRegistration registration) {
		this.registration = registration;
	}


	

	public NodalOfficerState getNodalOfficer() {
		return nodalOfficer;
	}

	public void setNodalOfficer(NodalOfficerState nodalOfficer) {
		this.nodalOfficer = nodalOfficer;
	}

	public Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getReportedIssueText() {
		return reportedIssueText;
	}

	public void setReportedIssueText(String reportedIssueText) {
		this.reportedIssueText = reportedIssueText;
	}
	
	
	public enum Level {
		Urban,
		BLOCK,
		PANCHYAT
	}
	
	
	@Transient
	private int stateCode;
	
	@Transient
	private boolean state;
	

	@Transient
	private boolean center;
	
	@Transient
	private int stateType;
	
	
	
	@Transient
	private int userId;
	
	
	@Transient
	private int pageStart=0;
	
	@Transient
	private int rowCount;

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean isCenter() {
		return center;
	}

	public void setCenter(boolean center) {
		this.center = center;
	}

	public int getStateType() {
		return stateType;
	}

	public void setStateType(int stateType) {
		this.stateType = stateType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public Date getRepliedOn() {
		return repliedOn;
	}

	public void setRepliedOn(Date repliedOn) {
		this.repliedOn = repliedOn;
	}

	public String getSubmittedFromIp() {
		return submittedFromIp;
	}

	public void setSubmittedFromIp(String submittedFromIp) {
		this.submittedFromIp = submittedFromIp;
	}

	public int getSuubmittedToState() {
		return SuubmittedToState;
	}

	public void setSuubmittedToState(int suubmittedToState) {
		SuubmittedToState = suubmittedToState;
	}

	public Character getSubmittedToType() {
		return submittedToType;
	}

	public void setSubmittedToType(Character submittedToType) {
		this.submittedToType = submittedToType;
	}

	public Integer getSubmittedToCenter() {
		return submittedToCenter;
	}

	public void setSubmittedToCenter(Integer submittedToCenter) {
		this.submittedToCenter = submittedToCenter;
	}



	
	
	
	
}
