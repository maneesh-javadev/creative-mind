package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "subscription_details")
public class EmailNotification {
	private Integer id;
	private Integer userid;
	private boolean district;
	private boolean subdistrict;
	private boolean village;
	private boolean urbanbody;
	private boolean ruralbody;
	private boolean tradionbody;

	private boolean block;
	private String user;
	private String email;
	private boolean active;
	
	private int slc;

	public EmailNotification() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GenericGenerator(name = "generator", strategy = "increment", parameters = {})
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "district_subscribed")
	public boolean getDistrict() {
		return district;
	}

	public void setDistrict(Boolean district) {
		this.district = district;
	}

	@Column(name = "subdistrict_subscribed")
	public boolean getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(Boolean subdistrict) {
		this.subdistrict = subdistrict;
	}

	@Column(name = "village_subscribed")
	public boolean getVillage() {
		return village;
	}

	public void setVillage(Boolean village) {
		this.village = village;
	}

	@Column(name = "ulb_subscribed")
	public boolean getUrbanbody() {
		return urbanbody;
	}

	public void setUrbanbody(Boolean urbanbody) {
		this.urbanbody = urbanbody;
	}

	@Column(name = "plb_subscribed")
	public boolean getRuralbody() {
		return ruralbody;
	}

	public void setRuralbody(Boolean ruralbody) {
		this.ruralbody = ruralbody;
	}

	@Column(name = "tlb_subscribed")
	public boolean getTradionbody() {
		return tradionbody;
	}

	public void setTradionbody(Boolean tradionbody) {
		this.tradionbody = tradionbody;
	}

	@Column(name = "username")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "isapproved")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "block_subscribed")
	public boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	@Column(name = "slc")
	public int getSlc() {
		return slc;
	}

	public void setSlc(int slc) {
		this.slc = slc;
	}

	
	
}
