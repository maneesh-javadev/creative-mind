package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;



@Entity
@NamedNativeQuery(query="SELECT * FROM  pescommon.get_pes_applicationnameandurl_by_id(:applicationId)",name="getLGDRegistration",resultClass=PesApplicationMaster.class)
@Table(name = "pes_application_master", schema = "pescommon")
public class PesApplicationMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@Column(name = "pes_application_name")
	private String pesApplicationName;
	
	
	
	@Column(name = "pes_application_url")
	private String pesApplicationUrl;







	public String getPesApplicationName() {
		return pesApplicationName;
	}



	public void setPesApplicationName(String pesApplicationName) {
		this.pesApplicationName = pesApplicationName;
	}



	public String getPesApplicationUrl() {
		return pesApplicationUrl;
	}



	public void setPesApplicationUrl(String pesApplicationUrl) {
		this.pesApplicationUrl = pesApplicationUrl;
	}
	
}
