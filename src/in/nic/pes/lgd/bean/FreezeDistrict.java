package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "district_freeze")
public class FreezeDistrict {
	@Id
	@Column(name = "id ")
	private Integer id;

	@Column(name = "slc ")
	private Integer slc;

	@Column(name = "entity_code")
	private Integer entitycode;

	@Column(name = "status")
	private Integer status;

	@Column(name = "reason")
	private String reason;

	@Column(name = "updatedby")
	private Integer updatedby;

	@Column(name = "updateddate")
	private Date updateddate;

	@Column(name = "updated_from_ip")
	private String updatedfromip;

	private transient Integer statusState;
	private transient Integer districtCode;
	private transient String districtNameEnglish;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	public Integer getEntitycode() {
		return entitycode;
	}

	public void setEntitycode(Integer entitycode) {
		this.entitycode = entitycode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	public String getUpdatedfromip() {
		return updatedfromip;
	}

	public void setUpdatedfromip(String updatedfromip) {
		this.updatedfromip = updatedfromip;
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

	public Integer getStatusState() {
		return statusState;
	}

	public void setStatusState(Integer statusState) {
		this.statusState = statusState;
	}

}
