package in.nic.pes.lgd.bean;

public class ConsolidateReportLBLandregion 
{
	private int state_code;
	private String state_name_english;
	private int d_count;
	private int sd_count;
	private int v_count;
	private int trad_dp_count;
	private int trad_ip_count;
	private int trad_vp_count;

	private String district_name_english;

	
	private int childCount;
	private int grandChildCount;
	
	private int totalDCount;
	private int totalsdCount;
	private int totalVCount;
	private int totalTradDPCount;
	private int totalTradIPCount;
	private int totalTradVPCount;
	private Integer noofdistricts;
	private Integer noofsubdistricts;
	private Integer noofvillages;
	private int district_code;
	private int subdistrict_code;
	private String subdistrict_name_english;
	private String census_2001_code;
	private String census_2011_code;
	private String entityNames;

	
	
	
	private String captchaAnswer;
	
	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}
	
	
	private int stateId;
	private int districtId;
	/*private char statetype;
	private char statelevel;*/
	
	
	
	
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	/*public char getStatetype() {
		return statetype;
	}
	public void setStatetype(char statetype) {
		this.statetype = statetype;
	}
	public char getStatelevel() {
		return statelevel;
	}
	public void setStatelevel(char statelevel) {
		this.statelevel = statelevel;
	}
		*/
	public int getTotalDCount() {
		return totalDCount;
	}
	public void setTotalDCount(int totalDCount) {
		this.totalDCount = totalDCount;
	}
	public int getTotalsdCount() {
		return totalsdCount;
	}
	public void setTotalsdCount(int totalsdCount) {
		this.totalsdCount = totalsdCount;
	}
	public int getTotalVCount() {
		return totalVCount;
	}
	public void setTotalVCount(int totalVCount) {
		this.totalVCount = totalVCount;
	}
	public int getTotalTradDPCount() {
		return totalTradDPCount;
	}
	public void setTotalTradDPCount(int totalTradDPCount) {
		this.totalTradDPCount = totalTradDPCount;
	}
	public int getTotalTradIPCount() {
		return totalTradIPCount;
	}
	public void setTotalTradIPCount(int totalTradIPCount) {
		this.totalTradIPCount = totalTradIPCount;
	}
	public int getTotalTradVPCount() {
		return totalTradVPCount;
	}
	public void setTotalTradVPCount(int totalTradVPCount) {
		this.totalTradVPCount = totalTradVPCount;
	}
	
	public int getChildCount() {
		return childCount;
	}
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	public int getGrandChildCount() {
		return grandChildCount;
	}
	public void setGrandChildCount(int grandChildCount) {
		this.grandChildCount = grandChildCount;
	}
	public int getState_code() {
		return state_code;
	}
	public void setState_code(int state_code) {
		this.state_code = state_code;
	}
	public String getState_name_english() {
		return state_name_english;
	}
	public void setState_name_english(String state_name_english) {
		this.state_name_english = state_name_english;
	}
	public int getd_count() { // NO_UCD (unused code)
		return d_count;
	}
	public void setd_count(int d_count) {
		this.d_count = d_count;
	}
	public int getsd_count() { // NO_UCD (unused code)
		return sd_count;
	}
	public void setsd_count(int sd_count) {
		this.sd_count = sd_count;
	}
	public int getV_count() {
		return v_count;
	}
	public void setV_count(int v_count) {
		this.v_count = v_count;
	}
	public int getTrad_dp_count() {
		return trad_dp_count;
	}
	public void setTrad_dp_count(int trad_dp_count) {
		this.trad_dp_count = trad_dp_count;
	}
	public int getTrad_ip_count() {
		return trad_ip_count;
	}
	public void setTrad_ip_count(int trad_ip_count) {
		this.trad_ip_count = trad_ip_count;
	}
	public int getTrad_vp_count() {
		return trad_vp_count;
	}
	public void setTrad_vp_count(int trad_vp_count) {
		this.trad_vp_count = trad_vp_count;
	}

	public Integer getNoofdistricts() {
		return noofdistricts;
	}

	public void setNoofdistricts(Integer noofdistricts) {
		this.noofdistricts = noofdistricts;
	}

	public Integer getNoofsubdistricts() {
		return noofsubdistricts;
	}

	public void setNoofsubdistricts(Integer noofsubdistricts) {
		this.noofsubdistricts = noofsubdistricts;
	}

	public Integer getNoofvillages() {
		return noofvillages;
	}

	public void setNoofvillages(Integer noofvillages) {
		this.noofvillages = noofvillages;
	}

	public int getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(int district_code) {
		this.district_code = district_code;
	}

// TODO Remove unused code found by UCDetector
// 	public String getdistrict_name_english() {
// 		return district_name_english;
// 	}

	public void setdistrict_name_english(String district_name_english) {
		this.setDistrict_name_english(district_name_english);
	}

	public int getSubdistrict_code() {
		return subdistrict_code;
	}

	public void setSubdistrict_code(int subdistrict_code) {
		this.subdistrict_code = subdistrict_code;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getSubdistrict_name_english() {
		return subdistrict_name_english;
	}

	public void setSubdistrict_name_english(String subdistrict_name_english) {
		this.subdistrict_name_english = subdistrict_name_english;
	}

	public String getCensus_2001_code() {
		return census_2001_code;
	}

	public void setCensus_2001_code(String census_2001_code) {
		this.census_2001_code = census_2001_code;
	}

	public String getCensus_2011_code() {
		return census_2011_code;
	}

	public void setCensus_2011_code(String census_2011_code) {
		this.census_2011_code = census_2011_code;
	}

	private char parentlevel;

	public char getParentlevel() {
		return parentlevel;
	}

	public void setParentlevel(char parentlevel) {
		this.parentlevel = parentlevel;
	}

	public String getDistrict_name_english() {
		return district_name_english;
	}

	public void setDistrict_name_english(String district_name_english) {
		this.district_name_english = district_name_english;
	}

	public String getEntityNames() {
		return entityNames;
	}

	public void setEntityNames(String entityNames) {
		this.entityNames = entityNames;
	}

	
}
