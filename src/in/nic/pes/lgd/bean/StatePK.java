/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//default package
//Generated Nov 24, 2011 12:27:08 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
* StateId generated by hbm2java
*/
@Embeddable
public class StatePK implements java.io.Serializable {

	private int stateCode;
	private int stateVersion;
	private int minorVersion;

	public StatePK() {
	}

	public StatePK(int stateCode, int stateVersion) {
		this.stateCode = stateCode;
		this.stateVersion = stateVersion;
		this.minorVersion= minorVersion;
	}

	@Column(name = "state_code", nullable = false)
	public int getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	@Column(name = "state_version", nullable = false)
	public int getStateVersion() {
		return this.stateVersion;
	}

	public void setStateVersion(int stateVersion) {
		this.stateVersion = stateVersion;
	}
	@Column(name = "minor_version", nullable = false)
	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	/*public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StateId))
			return false;
		StateId castOther = (StateId) other;

		return (this.getStateCode() == castOther.getStateCode())
				&& (this.getStateVersion() == castOther.getStateVersion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getStateCode();
		result = 37 * result + this.getStateVersion();
		return result;
	}*/

}
