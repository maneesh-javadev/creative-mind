package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT * FROM get_land_region_history_fn_for_citizen(:villageNameEnglish, :villageCode) order by land_region_version",name="getVillageHistoryDetails",resultClass=VillageHistory.class)
public class VillageHistory {

	private Integer villageCode;
	private String villageNameEnglish; 
	private int villageVersion;
	private Integer flagCode;
	private String lrReplaces;
	private String lrReplacedby;
	private String description;
	private String timeStatmp;
	private String fileLocation;
	
	
	private String active;
	@Column(name="active")
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	

	@Column(name="land_region_code")
	public Integer getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}
	@Column(name="land_region_name_english")
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}
	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}
	@Id
	@Column(name="land_region_version")
	public int getVillageVersion() {
		return villageVersion;
	}
	public void setVillageVersion(int villageVersion) {
		this.villageVersion = villageVersion;
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
	
/*	@Column(name="replaced_by_english")
	public String getLrReplacedby() {
		return lrReplacedby;
	}
	public void setLrReplacedby(String lrReplacedby) {
		this.lrReplacedby = lrReplacedby;
	}*/
	
	
	
	@Column(name = "replaced_by_local")
	public String getTimeStatmp() {
		return timeStatmp;
	}

	public void setTimeStatmp(String timeStatmp) {
		this.timeStatmp = timeStatmp;
	}

	@Column(name = "replace_local")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "replaced_by_english")
	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

}
