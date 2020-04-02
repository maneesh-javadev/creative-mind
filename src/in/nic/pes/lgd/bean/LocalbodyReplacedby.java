package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "localbody_replacedby", schema = "public")
public class LocalbodyReplacedby implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2585970727336002025L;
	private int lbpId;
	private int lbReplacedby;
	private int replacedby;
	private int replacedbyVersion;

	public LocalbodyReplacedby() {
	}

// TODO Remove unused code found by UCDetector
// 	public LocalbodyReplacedby(int lbpId, int lbReplacedby, int replacedby,
// 			int replacedbyVersion) {
// 		this.lbpId = lbpId;
// 		this.lbReplacedby = lbReplacedby;
// 		this.replacedby = replacedby;
// 		this.replacedbyVersion = replacedbyVersion;
// 	}

	//@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "lbreplacedby_seq") })
	@GenericGenerator(name = "generator", strategy = "increment", parameters = {})
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "lbp_id")
	public int getLbpId() {
		return this.lbpId;
	}

	public void setLbpId(int lbpId) {
		this.lbpId = lbpId;
	}

	@Column(name = "lb_replacedby", nullable = false)
	public int getLbReplacedby() {
		return this.lbReplacedby;
	}

	public void setLbReplacedby(int lbReplacedby) {
		this.lbReplacedby = lbReplacedby;
	}

	@Column(name = "replacedby", nullable = false)
	public int getReplacedby() {
		return this.replacedby;
	}

	public void setReplacedby(int replacedby) {
		this.replacedby = replacedby;
	}

	@Column(name = "replacedby_version", nullable = false)
	public int getReplacedbyVersion() {
		return this.replacedbyVersion;
	}

	public void setReplacedbyVersion(int replacedbyVersion) {
		this.replacedbyVersion = replacedbyVersion;
	}

}

 