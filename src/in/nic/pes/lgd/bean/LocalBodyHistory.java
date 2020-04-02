package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@NamedNativeQuery(query=" SELECT distinct * FROM get_land_region_history_fn(:subdistrictNameEnglish, :subdistrictCode) order by land_region_version",name="getSubdistrictHistoryDetails",resultClass=SubdistrictHistory.class)


public class LocalBodyHistory {

	private int localBodyCode;
	private String localBodyNameEnglish;
	private String localBodyNameLocal;
	private int localBodyVersion;
	
	private String replacesEnglish;
	private String replacesLocal;
	
	private String replacedEnglish;
	private String replacedLocal;
	
	private Integer flagCode;
	private String active;
	
    @Column(name="land_region_code")
	public int getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	@Column(name="land_region_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	@Column(name="land_region_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}
	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
	@Id
	@Column(name="land_region_version")
	public int getLocalBodyVersion() {
		return localBodyVersion;
	}
	public void setLocalBodyVersion(int localBodyVersion) {
		this.localBodyVersion = localBodyVersion;
	}
	@Column(name="replaces_english")
	public String getReplacesEnglish() {
		return replacesEnglish;
	}
	public void setReplacesEnglish(String replacesEnglish) {
		this.replacesEnglish = replacesEnglish;
	}
	@Column(name="replace_local")
	public String getReplacesLocal() {
		return replacesLocal;
	}
	public void setReplacesLocal(String replacesLocal) {
		this.replacesLocal = replacesLocal;
	}
	@Column(name="replaced_by_english")
	public String getReplacedEnglish() {
		return replacedEnglish;
	}
	public void setReplacedEnglish(String replacedEnglish) {
		this.replacedEnglish = replacedEnglish;
	}
	@Column(name="replaced_by_local")
	public String getReplacedLocal() {
		return replacedLocal;
	}
	public void setReplacedLocal(String replacedLocal) {
		this.replacedLocal = replacedLocal;
	}
	
	@Column(name="flag_code")
	public Integer getFlagCode() {
		return flagCode;
	}
	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}
	
	@Column(name="active")
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	
	
}
