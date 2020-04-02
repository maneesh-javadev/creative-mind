package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ws_allowed_ips")
public class WsAllowedIpEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "ws_allowed_ips_srno_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "srno", unique = true, nullable = false)
	private Integer srno;

	

	@Column(name = "ip_address")
	private String ipAddress;
	
	@ManyToOne
	@JoinColumn(name="registration_srno", referencedColumnName="srno", nullable=false)
	private UserRegistration userRegistration;


	public UserRegistration getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(UserRegistration userRegistration) {
		this.userRegistration = userRegistration;
	}

	public Integer getSrno() {
		return srno;
	}

	public void setSrno(Integer srno) {
		this.srno = srno;
	}


	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


}
