
package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 * @author Sunita Dagar
 * @Date 18-11-2016
 */
@SuppressWarnings("serial")
@Embeddable
public class NodalOfficerPK implements java.io.Serializable {

	@Column(name = "nodal_user_id",nullable = false)
	private Integer nodalUserId;
	
	@Column(name = "nodal_user_version",nullable = false)
	private Integer nodalUserVersion;

	public NodalOfficerPK() {
	}

	public NodalOfficerPK(int nodalUserId, int nodalUserVersion) {
		this.nodalUserId = nodalUserId;
		this.nodalUserVersion = nodalUserVersion;
	}

	public Integer getNodalUserId() {
		return nodalUserId;
	}

	public void setNodalUserId(Integer nodalUserId) {
		this.nodalUserId = nodalUserId;
	}

	public Integer getNodalUserVersion() {
		return nodalUserVersion;
	}

	public void setNodalUserVersion(Integer nodalUserVersion) {
		this.nodalUserVersion = nodalUserVersion;
	}
	
}
