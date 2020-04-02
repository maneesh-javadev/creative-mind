package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

/**
 * 
 * @Author Vinay Yadav
 * 
 */

@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(query = " select d.district_code, d.district_name_english, " +
							  " CASE WHEN(COALESCE((SELECT df.status from district_freeze df where df.entity_code=d.dlc and lb_lr_type=:lblrType), 0)) = 0 THEN 'Unfreeze' WHEN(COALESCE((SELECT df.status from district_freeze df where df.entity_code=d.dlc and lb_lr_type=:lblrType),0)) = 1 THEN 'Freeze' END  AS status, " +
							  " CASE WHEN(COALESCE((SELECT sf.status_by_state_user from state_freeze sf where sf.entity_code=d.dlc and sf.entity_type='D' and lb_lr_type=:lblrType),0)) = 0 THEN 'Unfreeze' WHEN  (COALESCE((SELECT sf.status_by_state_user from state_freeze sf where sf.entity_code=d.dlc and sf.entity_type='D' and lb_lr_type=:lblrType),0))  =1 THEN 'Freeze' END AS status_state " +
							  " from district d where d.dlc = :dlc and d.isactive", name = "DISTRICT_FREEZE_DETAILS", resultClass = FreezeDistrictBean.class), 
    })
public class FreezeDistrictBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "district_code")
	private Integer districtCode;

	@Column(name = "district_name_english")
	private String districtNameEnglish;
	
	@Column(name = "status")
	private String districtStatus;
	
	@Column(name = "status_state")
	private String stateStatus;
	
	@Transient
	private String reason;
	
	@Transient
	private String processAction;
	
	@Transient
	private Integer userId;
	
	@Transient
	private String ipAddress;
	
	@Transient
	private String lbLRType;
	
	@Transient
	private String userOTP; 
	/**
	 * Default Constructor
	 */
	public FreezeDistrictBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public String getDistrictStatus() {
		return districtStatus;
	}

	public void setDistrictStatus(String districtStatus) {
		this.districtStatus = districtStatus;
	}

	public String getStateStatus() {
		return stateStatus;
	}

	public void setStateStatus(String stateStatus) {
		this.stateStatus = stateStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProcessAction() {
		return processAction;
	}

	public void setProcessAction(String processAction) {
		this.processAction = processAction;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLbLRType() {
		return lbLRType;
	}

	public void setLbLRType(String lbLRType) {
		this.lbLRType = lbLRType;
	}

	public String getUserOTP() {
		return userOTP;
	}

	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}

	
	
	
}
