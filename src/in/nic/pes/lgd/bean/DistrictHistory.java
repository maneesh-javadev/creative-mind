package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;



@Entity
@NamedNativeQuery(query=" SELECT * FROM get_land_region_history_fn_for_citizen(:districtNameEnglish, :districtCode) order by land_region_version",name="getDistrictHistoryDetails",resultClass=DistrictHistory.class)


public class DistrictHistory {

	private int districtCode;
	private String districtNameEnglish;
	private int districtVersion;
	private Integer flagCode;
	private String lrReplaces;
	//private String lrReplacedby;
	
	private String description;
	//replaced_by_local
	private String fileLocation;
	private String timeStatmp;
	
	
	private String active;
	@Column(name="active")
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	@Column(name="land_region_code")
	public int getDistrictCode() {
		return districtCode;
	}
	
	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}
	@Column(name="land_region_name_english")
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	@Id
	@Column(name="land_region_version")
	public int getDistrictVersion() {
		return districtVersion;
	}
	public void setDistrictVersion(int districtVersion) {
		this.districtVersion = districtVersion;
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
	
	/*@Column(name="replaced_by_english")
	public String getLrReplacedby() {
		return lrReplacedby;
	}
	public void setLrReplacedby(String lrReplacedby) {
		this.lrReplacedby = lrReplacedby;
	}*/
	@Column(name="replace_local")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="replaced_by_english")
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	@Column(name="replaced_by_local")
	public String getTimeStatmp() {
		return timeStatmp;
	}
	public void setTimeStatmp(String timeStatmp) {
		this.timeStatmp = timeStatmp;
	}
	
	
	
	

	
}
