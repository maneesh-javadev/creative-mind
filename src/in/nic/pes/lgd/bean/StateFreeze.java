package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "state_freeze", schema = "public")
public class StateFreeze implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer slc;
	private Integer entityCode;
	private Character entityTtype;
	private Integer statusByStateUser;
	private String reason;
	private Integer updatedBy;
	private Date updatedDate;
	private String updatedFromIp;
	private String dcodes;
	private Character lbLrType;
	
	
	private String userOTP;
	

	@Id
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "slc")
	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	@Column(name = "entity_code")
	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	@Column(name = "entity_type")
	public Character getEntityTtype() {
		return entityTtype;
	}

	public void setEntityTtype(Character entityTtype) {
		this.entityTtype = entityTtype;
	}

	@Column(name = "status_by_state_user")
	public Integer getStatusByStateUser() {
		return statusByStateUser;
	}

	public void setStatusByStateUser(Integer statusByStateUser) {
		this.statusByStateUser = statusByStateUser;
	}

	@Column(name = "reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "updatedby")
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updateddate")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "updated_from_ip")
	public String getUpdatedFromIp() {
		return updatedFromIp;
	}

	public void setUpdatedFromIp(String updatedFromIp) {
		this.updatedFromIp = updatedFromIp;
	}
	
	@Transient
	public String getDcodes() {
		return dcodes;
	}

	public void setDcodes(String dcodes) {
		this.dcodes = dcodes;
	}
	
	@Column(name= "lb_lr_type")
	public Character getLbLrType() {
		return lbLrType;
	}

	public void setLbLrType(Character lbLrType) {
		this.lbLrType = lbLrType;
	}

	@Transient
	public String getUserOTP() {
		return userOTP;
	}

	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}

	
	
	
	

}
