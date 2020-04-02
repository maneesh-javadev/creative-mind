package in.nic.pes.lgd.restructure.reporting.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(query="select * from lb_mapped_multiple_district(:stateCode)",
			name = "localBodyMappedToDistricts",resultClass = LocalBodyMappedToDistrictsEntity.class)
	
})

public class LocalBodyMappedToDistrictsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "dp_code")
	private Integer dpcode;
	
	@Column(name= "dp_name")
	private String dpname;
	
	@Column(name = "ip_code")
	private Integer ipcode;
	
	@Column(name = "ip_name")
	private String ipname;
	
	@Id
	@Column(name= "gp_code")
	private Integer gpcode;
	
	@Column(name = "gp_name")
    private String gpname;
	
	@Column(name = "mapped_district")
	private String mappedDistrict;

	@Transient
	private String captchaAnswer;
	
	@Transient
	private String entitesForMessage;
	
	@Transient
	private Integer paramStateCode;
	
	
	
	public Integer getDpcode() {
		return dpcode;
	}

	public void setDpcode(Integer dpcode) {
		this.dpcode = dpcode;
	}

	public String getDpname() {
		return dpname;
	}

	public void setDpname(String dpname) {
		this.dpname = dpname;
	}

	public Integer getIpcode() {
		return ipcode;
	}

	public void setIpcode(Integer ipcode) {
		this.ipcode = ipcode;
	}

	public String getIpname() {
		return ipname;
	}

	public void setIpname(String ipname) {
		this.ipname = ipname;
	}

	public Integer getGpcode() {
		return gpcode;
	}

	public void setGpcode(Integer gpcode) {
		this.gpcode = gpcode;
	}

	public String getGpname() {
		return gpname;
	}

	public void setGpname(String gpname) {
		this.gpname = gpname;
	}

	public String getMappedDistrict() {
		return mappedDistrict;
	}

	public void setMappedDistrict(String mappedDistrict) {
		this.mappedDistrict = mappedDistrict;
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	public String getEntitesForMessage() {
		return entitesForMessage;
	}

	public void setEntitesForMessage(String entitesForMessage) {
		this.entitesForMessage = entitesForMessage;
	}

	public Integer getParamStateCode() {
		return paramStateCode;
	}

	public void setParamStateCode(Integer paramStateCode) {
		this.paramStateCode = paramStateCode;
	}
	
	
	
	

}
