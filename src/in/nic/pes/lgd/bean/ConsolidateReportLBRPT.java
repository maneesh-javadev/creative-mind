package in.nic.pes.lgd.bean;

import in.nic.pes.common.beans.ConsolidatePanchaytReportVariable;
import in.nic.pes.common.beans.MyStackList;

public class ConsolidateReportLBRPT 
{
	private int state_code;
	private String state_name_english;
	private String lb_hierarchy;
	private int dp_count;
	private int ip_count;
	private int vp_count;
	private int trad_dp_count;
	private int trad_ip_count;
	private int trad_vp_count;
	private int urban_count;
	private String captchaAnswers;	
	private int localBodyCode;
	private String localbodyNameEnglish;
	private String localbodyNameLocal;
	private String localbodyTypeName;
	private int childCount;
	private int grandChildCount;
	private String sscode;
	private int totalDPCount;
	private int totalIPCount;
	private int totalVPCount;
	private int totalTradDPCount;
	private int totalTradIPCount;
	private int totalTradVPCount;
	private int totalUrbanCount;
	private char lbtype;
	private String districtPName;
	private String interMpName;
	private String captchaAnswer;

    private int parentCode;
    private char parentLevel;
    private int stateId;
	private char statetype;
	private char statelevel;
	private int localbodycode;
	private int iPLbCode;
	private int vPLbCode;
	private String lbLevel;
	private Boolean otherInformationFlag;
	private Boolean isVillageCouncil=false;
	private Boolean VillageCouncilBack;
	private String financialYear;
	
	private String searchCriteriaType;
	
	
	private MyStackList<ConsolidatePanchaytReportVariable> stack;
	
	
	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}
	//Code added by Arnab   Start
	
	
	
	
	public int getLocalbodycode() {
		return localbodycode;
	}
	public void setLocalbodycode(int localbodycode) {
		this.localbodycode = localbodycode;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public char getStatetype() {
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
	
	//Code added by Arnab
	
	public String getLb_hierarchy() {
		return lb_hierarchy;
	}
	public void setLb_hierarchy(String lb_hierarchy) {
		this.lb_hierarchy = lb_hierarchy;
	}

	public String getDistrictPName() {
		return districtPName;
	}
	public void setDistrictPName(String districtPName) {
		this.districtPName = districtPName;
	}
	public String getInterMpName() {
		return interMpName;
	}
	public void setInterMpName(String interMpName) {
		this.interMpName = interMpName;
	}
	public char getLbtype() {
		return lbtype;
	}
	public void setLbtype(char lbtype) {
		this.lbtype = lbtype;
	}
	public int getTotalDPCount() {
		return totalDPCount;
	}
	public void setTotalDPCount(int totalDPCount) {
		this.totalDPCount = totalDPCount;
	}
	public int getTotalIPCount() {
		return totalIPCount;
	}
	public void setTotalIPCount(int totalIPCount) {
		this.totalIPCount = totalIPCount;
	}
	public int getTotalVPCount() {
		return totalVPCount;
	}
	public void setTotalVPCount(int totalVPCount) {
		this.totalVPCount = totalVPCount;
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
	public int getTotalUrbanCount() {
		return totalUrbanCount;
	}
	public void setTotalUrbanCount(int totalUrbanCount) {
		this.totalUrbanCount = totalUrbanCount;
	}
	public int getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	public String getLocalbodyNameEnglish() {
		return localbodyNameEnglish;
	}
	public void setLocalbodyNameEnglish(String localbodyNameEnglish) {
		this.localbodyNameEnglish = localbodyNameEnglish;
	}
	public String getLocalbodyNameLocal() {
		return localbodyNameLocal;
	}
	public void setLocalbodyNameLocal(String localbodyNameLocal) {
		this.localbodyNameLocal = localbodyNameLocal;
	}
	public String getLocalbodyTypeName() {
		return localbodyTypeName;
	}
	public void setLocalbodyTypeName(String localbodyTypeName) {
		this.localbodyTypeName = localbodyTypeName;
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
	public int getDp_count() {
		return dp_count;
	}
	public void setDp_count(int dp_count) {
		this.dp_count = dp_count;
	}
	public int getIp_count() {
		return ip_count;
	}
	public void setIp_count(int ip_count) {
		this.ip_count = ip_count;
	}
	public int getVp_count() {
		return vp_count;
	}
	public void setVp_count(int vp_count) {
		this.vp_count = vp_count;
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
	public int getUrban_count() {
		return urban_count;
	}
	public void setUrban_count(int urban_count) {
		this.urban_count = urban_count;
	}

	public String getCaptchaAnswers() {
		return captchaAnswers;
	}

	public void setCaptchaAnswers(String captchaAnswers) {
		this.captchaAnswers = captchaAnswers;
	}

	public String getSscode() {
		return sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public MyStackList<ConsolidatePanchaytReportVariable> getStack() {
		return stack;
	}

	public void setStack(MyStackList<ConsolidatePanchaytReportVariable> stack) {
		this.stack = stack;
	}

	public char getParentLevel() {
		return parentLevel;
	}

	public void setParentLevel(char parentLevel) {
		this.parentLevel = parentLevel;
	}

	public int getiPLbCode() {
		return iPLbCode;
	}

	public void setiPLbCode(int iPLbCode) {
		this.iPLbCode = iPLbCode;
	}

	public int getvPLbCode() {
		return vPLbCode;
	}

	public void setvPLbCode(int vPLbCode) {
		this.vPLbCode = vPLbCode;
	}

	public String getLbLevel() {
		return lbLevel;
	}

	public void setLbLevel(String lbLevel) {
		this.lbLevel = lbLevel;
	}

	public Boolean getOtherInformationFlag() {
		return otherInformationFlag;
	}

	public void setOtherInformationFlag(Boolean otherInformationFlag) {
		this.otherInformationFlag = otherInformationFlag;
	}

	public Boolean getIsVillageCouncil() {
		return isVillageCouncil;
	}

	public void setIsVillageCouncil(Boolean isVillageCouncil) {
		this.isVillageCouncil = isVillageCouncil;
	}

	public Boolean getVillageCouncilBack() {
		return VillageCouncilBack;
	}

	public void setVillageCouncilBack(Boolean villageCouncilBack) {
		VillageCouncilBack = villageCouncilBack;
	}
	
	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getSearchCriteriaType() {
		return searchCriteriaType;
	}

	public void setSearchCriteriaType(String searchCriteriaType) {
		this.searchCriteriaType = searchCriteriaType;
	}


	
	
}
