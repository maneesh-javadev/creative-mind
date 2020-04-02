package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

/*@Entity
@NamedNativeQuery(query=" SELECT * FROM get_land_region_history_fn_for_state(:stateNameEnglish, :stateCode) order by land_region_version",name="getStateHistoryDetails",resultClass=StateHistory.class)
*/

/**
 * @author NICSI_2
 *
 */
@Entity
@NamedNativeQuery(query=" SELECT * FROM get_land_region_history_fn_for_citizen(:stateNameEnglish, :stateCode) order by land_region_version",name="getStateHistoryDetails",resultClass=StateHistory.class)


public class StateHistory {

	private int stateCode;
	private String stateNameEnglish;
	private int stateVersion;
	private Integer flagCode;
	private String lrReplaces;
	private String lrReplacedby;
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
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	@Column(name="land_region_name_english")
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	@Id
	@Column(name="land_region_version")
	public int getStateVersion() {
		return stateVersion;
	}
	public void setStateVersion(int stateVersion) {
		this.stateVersion = stateVersion;
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
	
	
	@Column(name="replaced_by_local")
	public String getTimeStatmp() {
		return timeStatmp;
	}
	public void setTimeStatmp(String timeStatmp) {
		this.timeStatmp = timeStatmp;
	}
	
	@Column(name="replaced_by_english")
	public String getFileLocation() {
		return fileLocation;
	}
	
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	
	
	
	
}
