package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="in.nic.pes.lgd.bean.StatewisePesaPanchyat")
public class StatewisePesaPanchyat {
	
	@Id
	@Column(name="vp_code")
	private Integer vpCode;
	
	@Column(name="vp_name_eng")
	private String vpNameEng;
	
	@Column(name="vp_name_loc")
	private String vpNameLoc;
	
	@Column(name="dp_code")
	private Integer dpCode;
	
	@Column(name="dp_name")
	private String dpName;
	
	@Column(name="ip_code")
	private Integer ipCode;
	
	@Column(name="ip_name")
	private String ipName;
	
	//added by pooja on 19-05-2016
	@Column(name="coverage_type")
	private Character coverageType;

	public Integer getVpCode() {
		return vpCode;
	}

	public void setVpCode(Integer vpCode) {
		this.vpCode = vpCode;
	}

	public String getVpNameEng() {
		return vpNameEng;
	}

	public void setVpNameEng(String vpNameEng) {
		this.vpNameEng = vpNameEng;
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

	public String getVpNameLoc() {
		return vpNameLoc;
	}

	public void setVpNameLoc(String vpNameLoc) {
		this.vpNameLoc = vpNameLoc;
	}

	public Character getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(Character coverageType) {
		this.coverageType = coverageType;
	}

}
