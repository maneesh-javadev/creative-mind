/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

// default package
// Generated Nov 24, 2011 12:27:08 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AssemblyConstituencyId generated by hbm2java
 */
@Embeddable
public class AssemblyConstituencyId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3239344832064021692L;
	private int acCode;
	private int acVersion;

	public AssemblyConstituencyId() {
	}

	public AssemblyConstituencyId(int acCode, int acVersion) {
		this.acCode = acCode;
		this.acVersion = acVersion;
	}

	@Column(name = "ac_code", nullable = false)
	public int getAcCode() {
		return this.acCode;
	}

	public void setAcCode(int acCode) {
		this.acCode = acCode;
	}

	@Column(name = "ac_version", nullable = false)
	public int getAcVersion() {
		return this.acVersion;
	}

	public void setAcVersion(int acVersion) {
		this.acVersion = acVersion;
	}

	public boolean equals(Object other) {
		if ((this == other)){
			return true;
		}	
		if ((other == null)){
			return false;
		}	
		if (!(other instanceof AssemblyConstituencyId)){
			return false;
		}	
		AssemblyConstituencyId castOther = (AssemblyConstituencyId) other;

		return (this.getAcCode() == castOther.getAcCode())
				&& (this.getAcVersion() == castOther.getAcVersion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getAcCode();
		result = 37 * result + this.getAcVersion();
		return result;
	}

}
