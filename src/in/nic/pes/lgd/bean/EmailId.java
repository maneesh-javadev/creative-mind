package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;



@Entity(name = "in.nic.pes.lgd.bean.EmailId")
@Table(catalog = "pes", schema = "pescommon")
@NamedNativeQuery(query=" SELECT * FROM pescommon.get_userdetails(:UserId)",name="getEmails",resultClass=EmailId.class)
public class EmailId {
	
	
	
	private String user_login_id;
	private String email;
	private String phoneno;
	
	

	@Id
	public String getUser_login_id() {
		return user_login_id;
	}

	public void setUser_login_id(String user_login_id) {
		this.user_login_id = user_login_id;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="mobile")
	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	
	
}