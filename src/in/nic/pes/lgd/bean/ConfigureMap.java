package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configuration_map")
public class ConfigureMap implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Column(name = "ismapupload")
	private Boolean ismapupload;
	@Column(name = "isbase_url")
    private Boolean isbaseUrl;
	@Column(name ="state_code")
	private Integer state_code;
	@Column (name = "state_version")
	private Integer state_version;
	@Column (name = "operation_code")
	private Integer operation_code;
 
	
	 public Integer getState_code() {
		return state_code;
	}

	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}

	public Integer getState_version() {
		return state_version;
	}

	public void setState_version(Integer state_version) {
		this.state_version = state_version;
	}

	public Integer getOperation_code() {
		return operation_code;
	}

	public void setOperation_code(Integer operation_code) {
		this.operation_code = operation_code;
	}

	public Boolean getIsmapupload() {
		return ismapupload;
	}

	public void setIsmapupload(Boolean ismapupload) {
		this.ismapupload = ismapupload;
	}
	 public Boolean getIsbaseUrl() {
	        return isbaseUrl;
	    }

	    public void setIsbaseUrl(Boolean isbaseUrl) {
	        this.isbaseUrl = isbaseUrl;
	    }

}
