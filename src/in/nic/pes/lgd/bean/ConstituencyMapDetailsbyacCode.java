package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select * from get_mapped_entity_by_ac_code(:acCode) ",name="getConstituencyMapDetailsbyacCode",resultClass=ConstituencyMapDetailsbyacCode.class),

})

public class ConstituencyMapDetailsbyacCode {
	

	@Id
	@Column(name="t_ac_code")
	private Integer acCode; 
	
	@Column(name="t_cc_code")
	private Integer ccCode; 
	
		
	@Column(name="t_lbcode_part")
	private String lbCodePart;
	
	@Column(name="t_lbcode_full")
	private String lbCodeFull;
	
	@Column(name="t_wardcode")
	private String wardCode;
	
	@Column(name="t_wardname")
	private String wardName;
	
	@Column(name="t_ulbname")
	private String ulbName;
	
	
	@Column(name="t_villagecode")
	private String villageCode;
	
	@Column(name="t_dpname")
	private String dpName;
	
	@Column(name="t_ipname")
	private String ipName;
	
	@Column(name="t_vpname")
	private String vpName;
	
	@Column(name="t_village_name")
	private String villageName;
	
	@Column(name="t_district_name")
	private String districtName;

	@Column(name="t_districtcode")
	private String districtCode;
	
	@Column(name="t_subdistrict_name")
	private String subdistrictName;
	
	@Column(name="t_subdistrictcode")
	private String subdistrictCode;

	public Integer getAcCode() {
		return acCode;
	}

	public void setAcCode(Integer acCode) {
		this.acCode = acCode;
	}

	public String getLbCodePart() {
		return lbCodePart;
	}

	public void setLbCodePart(String lbCodePart) {
		this.lbCodePart = lbCodePart;
	}

	public String getLbCodeFull() {
		return lbCodeFull;
	}

	public void setLbCodeFull(String lbCodeFull) {
		this.lbCodeFull = lbCodeFull;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	public String getDpName() {
		return dpName;
	}

	public void setDpName(String dpName) {
		this.dpName = dpName;
	}

	public String getIpName() {
		return ipName;
	}

	public void setIpName(String ipName) {
		this.ipName = ipName;
	}

	public String getVpName() {
		return vpName;
	}

	public void setVpName(String vpName) {
		this.vpName = vpName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Integer getCcCode() {
		return ccCode;
	}

	public void setCcCode(Integer ccCode) {
		this.ccCode = ccCode;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getUlbName() {
		return ulbName;
	}

	public void setUlbName(String ulbName) {
		this.ulbName = ulbName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getSubdistrictName() {
		return subdistrictName;
	}

	public void setSubdistrictName(String subdistrictName) {
		this.subdistrictName = subdistrictName;
	}

	public String getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(String subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}
	
	
	
}
