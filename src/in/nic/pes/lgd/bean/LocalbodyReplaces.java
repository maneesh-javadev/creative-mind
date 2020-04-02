/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "localbody_replaces", schema = "public")
public class LocalbodyReplaces implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7935533254866060956L;
	private int lbpId;
	private int lbReplaces;
	private int replaces;
	private int replacesVersion;

	public LocalbodyReplaces() {
	}

	public LocalbodyReplaces(int lbpId, int lbReplaces, int replaces,
			int replacesVersion) {
		this.lbpId = lbpId;
		this.lbReplaces = lbReplaces;
		this.replaces = replaces;
		this.replacesVersion = replacesVersion;
	}
	@GenericGenerator(name = "generator", strategy = "increment", parameters = {})
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "lbp_id", unique = true, nullable = false)
	public int getLbpId() {
		return this.lbpId;
	}

	public void setLbpId(int lbpId) {
		this.lbpId = lbpId;
	}

	@Column(name = "lb_replaces", nullable = false)
	public int getLbReplaces() {
		return this.lbReplaces;
	}

	public void setLbReplaces(int lbReplaces) {
		this.lbReplaces = lbReplaces;
	}

	@Column(name = "replaces", nullable = false)
	public int getReplaces() {
		return this.replaces;
	}

	public void setReplaces(int replaces) {
		this.replaces = replaces;
	}

	@Column(name = "replaces_version", nullable = false)
	public int getReplacesVersion() {
		return this.replacesVersion;
	}

	public void setReplacesVersion(int replacesVersion) {
		this.replacesVersion = replacesVersion;
	}

}
