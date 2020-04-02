package in.nic.pes.lgd.bean;

import java.util.Date;

public class ReportingForm {
	
	private int issueId;
	
	private Date submittedOn;
	
	private String reportedIssueText;
	
	private String replyText;
	
	private Date repliedOn;
	
	private int registrationId;
	
	private String stateName;
	
	private Character userType;

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
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

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Character getUserType() {
		return userType;
	}

	public void setUserType(Character userType) {
		this.userType = userType;
	}
	
	
	

}
