package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query=" SELECT row_number() over() as rid, state_code,state_name_english, dp, ip, vp from get_local_gov_setup_rpt_fn(:entity_type)",name="getLocalGovSetupRptFn",resultClass=ReportStatePanchayatPojo.class)

public class ReportStatePanchayatPojo {
		
	@Id
	@Column(name="rid")
	private Integer rid;
	
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name = "state_name_english")
	private String stateNameEnglish;
	
	@Column(name = "dp")
	private String dp;
	
	@Column(name = "ip")
	private String ip;
	
	@Column(name = "vp")
	private String vp;
	
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	
	
	public String getDp() {
		return dp;
	}
	public void setDp(String dp) {
		this.dp = dp;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	public String getVp() {
		return vp;
	}
	public void setVp(String vp) {
		this.vp = vp;
	}
	
}
