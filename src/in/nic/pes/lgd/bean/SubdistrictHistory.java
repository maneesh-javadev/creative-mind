package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT distinct * FROM get_land_region_history_fn(:subdistrictNameEnglish, :subdistrictCode) order by land_region_version",name="getSubdistrictHistoryDetails",resultClass=SubdistrictHistory.class)

public class SubdistrictHistory {

	private int subdistrictCode;
	private String subdistrictNameEnglish;
	private int subdistrictVersion;
	private int minorVersion;
	private Integer flagCode;
	private String lrReplaces;
	private String lrReplacedby;
	
	private String description;
	
	private String active;
	
	private String replacedByLocal;
	
	@Column(name="active")
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	@Column(name="land_region_code")
	public int getSubdistrictCode() {
		return subdistrictCode;
	}
	public void setSubdistrictCode(int subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}
	@Column(name="land_region_name_english")
	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}
	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}
	@Id
	@Column(name="land_region_version")
	public int getSubdistrictVersion() {
		return subdistrictVersion;
	}
	public void setSubdistrictVersion(int subdistrictVersion) {
		this.subdistrictVersion = subdistrictVersion;
	}
	@Column(name="flag_code")
	public Integer getFlagCode() {
		return flagCode;
	}
	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}
	@Column(name="replaces_english")
	public String getLrReplaces() {
		return lrReplaces;
	}
	public void setLrReplaces(String lrReplaces) {
		this.lrReplaces = lrReplaces;
	}
	
	@Column(name="replaced_by_english")
	public String getLrReplacedby() {
		return lrReplacedby;
	}
	public void setLrReplacedby(String lrReplacedby) {
		this.lrReplacedby = lrReplacedby;
	}
	
	@Column(name="replace_local")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="replaced_by_local")
	
	public String getReplacedByLocal() {
		return replacedByLocal;
	}
	public void setReplacedByLocal(String replacedByLocal) {
		this.replacedByLocal = replacedByLocal;
	}
	
	@Column(name="minor_version")
	public int getMinorVersion() {
		return minorVersion;
	}
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
	
	

	
}
