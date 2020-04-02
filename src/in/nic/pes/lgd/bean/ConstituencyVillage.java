package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select v.village_code,v.village_name_english,gp.local_body_code as gp_code,gp.local_body_name_english as gp_name,"
				  + " ip.local_body_code as ip_code,ip.local_body_name_english as ip_name,dp.local_body_code as dp_code,dp.local_body_name_english as dp_name,"
				  + " case when v.vlc  in (select * from get_draft_used_lb_lr_temp(v.vlc,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state "
				  + " FROM localbody gp left join localbody ip on ip.lblc=gp.parent_lblc and  ip.isactive  left join localbody dp on dp.lblc=ip.parent_lblc and dp.isactive left join"
				  + " lb_covered_landregion lbcd  on  gp.lb_covered_region_code = lbcd.lb_covered_region_code  left join village v on lbcd.lrlc=v.vlc "
				  + " WHERE gp.isactive=TRUE AND lbcd.isactive = TRUE AND  gp.local_body_code IN (:lbPart) and lbcd.land_region_type='V' AND  v.isactive=true "
				  ,name="getConstituencyVillage",resultClass=ConstituencyVillage.class),





})

public class ConstituencyVillage {
	
	@Id
	@Column(name="village_code")
	private Integer villageCode; 
	
	
	@Column(name="village_name_english")
	private String villageNameEnglish;
	
	@Column(name="gp_code")
	private Integer gpCode; 
	
	@Column(name="gp_name")
	private String gpName;
	
	@Column(name="ip_code")
	private Integer ipCode; 
	
	@Column(name="ip_name")
	private String ipName;
	
	@Column(name="dp_code")
	private Integer dpCode; 
	
	@Column(name="dp_name")
	private String dpName;
	
	@Column(name="operation_state")
	private Character operation_state;
	
	public Integer getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}

	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}

	public Integer getGpCode() {
		return gpCode;
	}

	public void setGpCode(Integer gpCode) {
		this.gpCode = gpCode;
	}

	public String getGpName() {
		return gpName;
	}

	public void setGpName(String gpName) {
		this.gpName = gpName;
	}

	public Integer getIpCode() {
		return ipCode;
	}

	public void setIpCode(Integer ipCode) {
		this.ipCode = ipCode;
	}

	public String getIpName() {
		return ipName;
	}

	public void setIpName(String ipName) {
		this.ipName = ipName;
	}

	

	public Integer getDpCode() {
		return dpCode;
	}

	public void setDpCode(Integer dpCode) {
		this.dpCode = dpCode;
	}

	public String getDpName() {
		return dpName;
	}

	public void setDpName(String dpName) {
		this.dpName = dpName;
	}

	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}

	
	
	

}
