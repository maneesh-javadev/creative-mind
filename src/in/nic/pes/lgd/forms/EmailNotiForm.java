package in.nic.pes.lgd.forms;

public class EmailNotiForm {
	private Integer id;
	private Integer userid;
	private Boolean district;
	private Boolean subdistrict;
	private Boolean village;
	private Boolean urbanbody;
	private Boolean ruralbody;
	private Boolean tradionbody;
	private Boolean block;
	private String user;
	private String email;
	private boolean active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Boolean getDistrict() {
		return district;
	}

	public void setDistrict(Boolean district) {
		this.district = district;
	}

	public Boolean getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(Boolean subdistrict) {
		this.subdistrict = subdistrict;
	}

	public Boolean getVillage() {
		return village;
	}

	public void setVillage(Boolean village) {
		this.village = village;
	}

	public Boolean getUrbanbody() {
		return urbanbody;
	}

	public void setUrbanbody(Boolean urbanbody) {
		this.urbanbody = urbanbody;
	}

	public Boolean getRuralbody() {
		return ruralbody;
	}

	public void setRuralbody(Boolean ruralbody) {
		this.ruralbody = ruralbody;
	}

	public Boolean getTradionbody() {
		return tradionbody;
	}

	public void setTradionbody(Boolean tradionbody) {
		this.tradionbody = tradionbody;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

}
