package in.nic.pes.common.beans;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




	@Entity
	@Table(name="subscribing_application",schema="public")
	public class SubscribeApplication implements Serializable
	
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -3320885227133117983L;


		@Id
		@Column(name="application_id",nullable=false)
		private Integer applicationId;
		
	
		@Column(name="userid",nullable=false)
		private Integer userId;
		
		@Column(name="name_of_application",nullable=false)
		private String applicationName;
		
		
		@Column(name="url")
		private String url;
		
		@Column(name="email_id")
		private String emailid;
		
		@Column(name="mobile_number")
		private Integer mobileno;
		
		@Column(name="passkey")
		private String passkey;
		
		@Column(name="createdby")
		private Long createBy;
		
		@Column(name="createdon")
		private Date createdon;
		
		@Column(name="lastupdated")
		private Date lastupdated;
		
		@Column(name="lastupdatedby")
		private Long lastupdatedby;
		
		@Column(name="flag")
		private String flag;
		
		
        
		/*public SubscribeApplication(Integer applicationId,Integer userId,String applicationName,String url
				,Long createBy,Date createdon,Date lastupdated,Long lastupdatedby)
		{
		
		this.applicationId=applicationId;
		this.userId=userId;
		this.applicationName=applicationName;
		this.url=url;
		this.createBy=createBy;
		this.createdon=createdon;
		this.lastupdated=lastupdated;
		this.setLastupdatedby(lastupdatedby);
		
		}*/
		
// TODO Remove unused code found by UCDetector
// 		public SubscribeApplication(Integer applicationId,Integer userId,String applicationName,String url
// 				,String emailid,Integer mobileno,String passkey)
// 		{
// 		
// 		this.applicationId=applicationId;
// 		this.userId=userId;
// 		this.applicationName=applicationName;
// 		this.url=url;
// 		this.emailid=emailid;
// 		this.mobileno=mobileno;
// 		this.passkey=passkey;
// 		
// 		}
		
		public SubscribeApplication()
		{
		
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
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

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}



		public Integer getApplicationId() {
			return applicationId;
		}



		public void setApplicationId(Integer applicationId) {
			this.applicationId = applicationId;
		}



		public String getApplicationName() {
			return applicationName;
		}



		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}
		
		public String getEmailid() {
			return emailid;
		}



		public void setEmailid(String emailId) {
			this.emailid = emailId;
		}
		
		public Integer getMobilno() {
			return mobileno;
		}

       public void setMobileno(Integer mobileNo) {
			this.mobileno = mobileNo;
		}
       public String getPasskey() {
			return passkey;
		}

      public void setPasskey(String passKey) {
			this.passkey = passKey;
		}

		public Long getLastupdatedby() {
			return lastupdatedby;
		}

		public void setLastupdatedby(Long lastupdatedby) {
			this.lastupdatedby = lastupdatedby;
		}
		
		

    }
