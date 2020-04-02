package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query="  select row_number() OVER () as id,* FROM get_land_region_history_fn(:blockNameEnglish, :blockCode) order by land_region_version ",name="getBlockHistoryDetails",resultClass=BlockHistory.class)
public class BlockHistory {
	
	private int id;
	private int blockCode;
	private String blockNameEnglish;
	private int blockVersion;
	private int minorVersion;
	private Integer flagCode;
	private String lrReplaces;
	private String lrReplacedby;
	
	private String active;
	
	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="active")
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	} 
	
	
	@Column(name="land_region_code")
	public int getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(int blockCode) {
		this.blockCode = blockCode;
	}
	@Column(name="land_region_name_english")
	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}
	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}
	
	@Column(name="land_region_version")
	public int getBlockVersion() {
		return blockVersion;
	}
	public void setBlockVersion(int blockVersion) {
		this.blockVersion = blockVersion;
	}
	
	
	@Column(name="minor_version")
	public int getMinorVersion() {
		return minorVersion;
	}
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
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
	
}
