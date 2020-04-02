/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * LocalBodySetupPK
 */
@Embeddable
public class LocalBodySetupPK implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5405479537335824865L;
	@Column(name = "local_body_setup_code")
	private int localBodySetupCode;
	@Column(name = "local_body_setup_version")
	private int localBodySetupVersion;

	public LocalBodySetupPK() {
	}

	public LocalBodySetupPK(int localBodySetupCode, int localBodySetupVersion) {
		this.localBodySetupCode = localBodySetupCode;
		this.localBodySetupVersion = localBodySetupVersion;
	}

	
	public int getLocalBodySetupCode() {
		return this.localBodySetupCode;
	}

	public void setLocalBodySetupCode(int localBodySetupCode) {
		this.localBodySetupCode = localBodySetupCode;
	}

	
	public int getLocalBodySetupVersion() {
		return this.localBodySetupVersion;
	}

	public void setLocalBodySetupVersion(int localBodySetupVersion) {
		this.localBodySetupVersion = localBodySetupVersion;
	}

	public boolean equals(Object other) {
		if ((this == other)){
			return true;
		}	
		if ((other == null)){
			return false;
		}	
		if (!(other instanceof LocalBodySetupPK)){
			return false;
		}	
		LocalBodySetupPK castOther = (LocalBodySetupPK) other;

		return (this.getLocalBodySetupCode() == castOther
				.getLocalBodySetupCode())
				&& (this.getLocalBodySetupVersion() == castOther
						.getLocalBodySetupVersion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getLocalBodySetupCode();
		result = 37 * result + this.getLocalBodySetupVersion();
		return result;
	}

}

    

