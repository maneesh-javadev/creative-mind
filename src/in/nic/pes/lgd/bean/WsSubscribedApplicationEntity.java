package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ws_subscribing_applications")
public class WsSubscribedApplicationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "ws_subscribing_applications_srno_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "srno", unique = true, nullable = false)
	private Integer srno;

	

	@Column(name = "application_name")
	private String applicationName;
	
	/*@ManyToOne
	@JoinColumn(name="registration_srno", referencedColumnName="srno", nullable=true,insertable=false, updatable=false)
	private WsUserRegistrationForm wsUserRegistrationForm;*/
	
	
	@ManyToOne
	@JoinColumn(name="registration_srno", referencedColumnName="srno", nullable=false)
	private UserRegistration userRegistration;

	/*public WsUserRegistrationForm getWsUserRegistrationForm() {
		return wsUserRegistrationForm;
	}

	public void setWsUserRegistrationForm(WsUserRegistrationForm wsUserRegistrationForm) {
		this.wsUserRegistrationForm = wsUserRegistrationForm;
	}*/

	public Integer getSrno() {
		return srno;
	}

	public void setSrno(Integer srno) {
		this.srno = srno;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public UserRegistration getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(UserRegistration userRegistration) {
		this.userRegistration = userRegistration;
	}


	


}
