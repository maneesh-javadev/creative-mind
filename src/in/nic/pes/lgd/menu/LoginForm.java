package in.nic.pes.lgd.menu;

import in.nic.pes.common.menu.pojo.Form;
import in.nic.pes.common.menu.pojo.Privilege;

import java.util.List;
import java.util.Set;

public class LoginForm {
	
	private String username;
	private String password;
	private String stateNameEnglish;
	private Integer profilerid=1;
	private String role;
	private Long villageId;
	private String villagename;
	private Set<Form> formList;
	private List<Privilege> privList;
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	private String captchaAnswer;
	private String enpassword;
	 /**
	 * end External User
	 */
	
	public Integer getProfilerid() {
		return profilerid;
	}
	public void setProfilerid(Integer profilerid) {
		this.profilerid = profilerid;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	
	
	public String getVillagename() {
		return villagename;
	}
	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getStateid() {
		return profilerid;
	}
	public void setStateid(Integer stateid) {
		this.profilerid = stateid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	public Set<Form> getFormList() {
		return formList;
	}
	public void setFormList(Set<Form> formList) {
		this.formList = formList;
	}
	
	public List<Privilege> getPrivList() {
		return privList;
	}
	public void setPrivList(List<Privilege> privList) {
		this.privList = privList;
	}
	public String getCaptchaAnswer() {
		return captchaAnswer;
	}
	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}
	public String getEnpassword() {
		return enpassword;
	}
	public void setEnpassword(String enpassword) {
		this.enpassword = enpassword;
	}
	
	
	
	

}
