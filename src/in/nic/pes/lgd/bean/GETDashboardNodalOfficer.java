package in.nic.pes.lgd.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from get_dashboard_nodal_officer_details(:stateCode,:userType)",	name = "GETDashboardNodalOfficerDTO", resultClass = GETDashboardNodalOfficer.class)
public class GETDashboardNodalOfficer {
	
	@Id
	@Column(name="no_name")
	private String noName;
	
	@Column(name="no_mobile")
	private String noMobile;
	
	@Column(name="no_email")
	private String noEmail;
	
	@Column(name="status")
	private String noStatus;
	
	@Column(name="updated_on")
	private Timestamp updatedOn;

	
	public String getNoName() {
		return noName;
	}

	public void setNoName(String noName) {
		this.noName = noName;
	}

	public String getNoMobile() {
		return noMobile;
	}

	public void setNoMobile(String noMobile) {
		this.noMobile = noMobile;
	}

	public String getNoEmail() {
		return noEmail;
	}

	public void setNoEmail(String noEmail) {
		this.noEmail = noEmail;
	}

	

	
	public String getNoStatus() {
		return noStatus;
	}

	public void setNoStatus(String noStatus) {
		this.noStatus = noStatus;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	

}
