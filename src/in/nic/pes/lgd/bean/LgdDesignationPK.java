package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LgdDesignationPK implements java.io.Serializable {
	
	
	@Column(name = "designation_code")
	private Integer designationCode;
	
	@Column(name = "designation_version")
	private Integer designationVersion;

	public Integer getDesignationCode() {
		return designationCode;
	}

	public void setDesignationCode(Integer designationCode) {
		this.designationCode = designationCode;
	}

	public Integer getDesignationVersion() {
		return designationVersion;
	}

	public void setDesignationVersion(Integer designationVersion) {
		this.designationVersion = designationVersion;
	}
	
   

}
