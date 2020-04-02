package in.nic.pes.lgd.forms;

import in.nic.pes.common.beans.SubscribeApplication;

import java.util.Date;
import java.util.List;



public class SubscribeApplicationForm {
	
	
	private Integer applicationId;
	private Integer userId;
	private String applicationName;
	private String url;
	private Long createBy;
	private Date createdon;
	private Date lastupdated;
	private String lastupdatedby;
	private String flag;

	private List<SubscribeApplication> scbscribeDetails;
	
	SubscribeApplicationForm()
	{
	
	}
// TODO Remove unused code found by UCDetector
// 	SubscribeApplicationForm(Integer applicationId,Integer userId,String applicationName,String url,Long createBy, Date createdon,Date lastupdated,String lastupdatedby,String flag)
// 	{
// 	this.applicationId=applicationId;
// 	this.userId=userId;
// 	this.applicationName=applicationName;
// 	this.url=url;
// 	this.createBy=createBy;
// 	this.createdon=createdon;
// 	this.lastupdated=lastupdated;
// 	this.lastupdatedby=lastupdatedby;
// 
// 	}
	
	public Integer getApplicationId() {
		return applicationId;
		
		
	}
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	public String getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public List<SubscribeApplication> getScbscribeDetails() {
		return scbscribeDetails;
	}
	public void setScbscribeDetails(List<SubscribeApplication> scbscribeDetails) {
		this.scbscribeDetails = scbscribeDetails;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	

}
