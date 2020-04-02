package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

/**
 * @author Kirandeep 
 * added on 1 Feb 2015 for stateFreeeze
 * 
 * 
 */


@Entity
@NamedNativeQuery(query =" select distinct d.district_code,d.district_name_english,(SELECT df.status from district_freeze df where df.entity_code=d.dlc and df.lb_lr_type=:lb_lr_type) as status,(SELECT df.updateddate from district_freeze df where df.entity_code=d.dlc and df.lb_lr_type=:lb_lr_type) as updateddate,"+

						 " (SELECT sf.reason from state_freeze sf where sf.entity_code=d.dlc and sf.lb_lr_type=:lb_lr_type) as reason, "+
						 " (SELECT sf.status_by_state_user from state_freeze sf where sf.entity_code=d.dlc and sf.entity_type='D' and sf.lb_lr_type=:lb_lr_type) as status_by_state_user from district d, state s where d.slc=s.slc and s.state_code=:state_code and d.isactive order by 2", name = "getStateFreezeDistList", resultClass = StateFreezeDistrictListDTO.class) 
public class StateFreezeDistrictListDTO {
	@Id
	@Column(name="district_code")
	private Integer districtCode;
	

	@Column(name="district_name_english")
	private String district_name_english;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="updateddate")
	private Date updateddate;
	
	@Column(name="reason")
	private String reason;
	
	@Column(name="status_by_state_user")
	private Integer status_by_state_user;

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrict_name_english() {
		return district_name_english;
	}

	public void setDistrict_name_english(String district_name_english) {
		this.district_name_english = district_name_english;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getStatus_by_state_user() {
		return status_by_state_user;
	}

	public void setStatus_by_state_user(Integer status_by_state_user) {
		this.status_by_state_user = status_by_state_user;
	}
		
	
}