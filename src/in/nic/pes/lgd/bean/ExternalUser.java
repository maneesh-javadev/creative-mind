package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
	 * This Entity is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */

@Entity
@Table(name="external_user",schema="public")

public class ExternalUser {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_login_id")
	private String userLoginId;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="district_code")
	private Integer distrctCode;
	
	@Column(name="user_type")
	private Character userType;
	
	@Column(name="otp_token")
	private String otpToken;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="en_password")
	private String enPassword;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
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

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getDistrctCode() {
		return distrctCode;
	}

	public void setDistrctCode(Integer distrctCode) {
		this.distrctCode = distrctCode;
	}

	public Character getUserType() {
		return userType;
	}

	public void setUserType(Character userType) {
		this.userType = userType;
	}

	public String getOtpToken() {
		return otpToken;
	}

	public void setOtpToken(String otpToken) {
		this.otpToken = otpToken;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEnPassword() {
		return enPassword;
	}

	public void setEnPassword(String enPassword) {
		this.enPassword = enPassword;
	}
	
	

}
