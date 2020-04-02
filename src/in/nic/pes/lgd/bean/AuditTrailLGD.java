package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="select id,annotation as action_annotation,url as action_url,date as action_date,user_login_id,ipaddress,user_agent from logs",
name="getAuditTrailLGD",resultClass=AuditTrailLGD.class)
public class AuditTrailLGD {

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="action_annotation")
	private String actionAnnotation;
	
	@Column(name="action_url")
	private String actionUrl;
	
	@Column(name="action_date")
	private Date actionDate;
	
	@Column(name="user_login_id")
	private String userLoginId;
	
	@Column(name="ipaddress")
	private String ipaddress;
	
	
	@Column(name="user_agent")
	private String userAgent;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getActionAnnotation() {
		return actionAnnotation;
	}


	public void setActionAnnotation(String actionAnnotation) {
		this.actionAnnotation = actionAnnotation;
	}


	public String getActionUrl() {
		return actionUrl;
	}


	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}


	public Date getActionDate() {
		return actionDate;
	}


	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}


	


	public String getUserLoginId() {
		return userLoginId;
	}


	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}


	public String getIpaddress() {
		return ipaddress;
	}


	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}


	public String getUserAgent() {
		return userAgent;
	}


	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	
	
	


	
	
}
