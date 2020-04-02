package in.nic.pes.lgd.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT row_number() OVER () as rid,  * from get_DP_wardCounstituency_wise_VP(:state_code)",name="dpWardConstituencyWiseVPBean",resultClass=DPwardConstituencyWiseVP.class)
public class DPwardConstituencyWiseVP {

	@Id
	@Column(name = "rid", nullable = false)
	private Integer rid;
	
	
	/**
	 * LGD Code add in Report no. 24 by Maneesh 19Sep2014
	 */
	@Column(name = "district_panchayat_code")
	private Integer dpCode;
	
	@Column(name = "district_panchayat_name", nullable = false)
	private String dpname;
	
	@Column(name = "ward_no", nullable = false)
	private String wardnumber;
	
	@Column(name = "ward_name_english", nullable = false)
	private String wardname;
	
	@Column(name = "village_panchayat_code", nullable = false)
	private Integer vpcode;
	
	@Column(name = "village_panchayat_name", nullable = false)
	private String vpname;

	@Column(name = "entity_type", nullable = false)
	private String entitytype;
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getDpname() {
		return dpname;
	}

	public void setDpname(String dpname) {
		this.dpname = dpname;
	}

	public String getWardnumber() {
		return wardnumber;
	}

	public void setWardnumber(String wardnumber) {
		this.wardnumber = wardnumber;
	}

	public String getWardname() {
		return wardname;
	}

	public void setWardname(String wardname) {
		this.wardname = wardname;
	}

	public Integer getVpcode() {
		return vpcode;
	}

	public void setVpcode(Integer vpcode) {
		this.vpcode = vpcode;
	}

	public String getVpname() {
		return vpname;
	}

	public void setVpname(String vpname) {
		this.vpname = vpname;
	}

	public String getEntitytype() {
		return entitytype;
	}

	public void setEntitytype(String entitytype) {
		this.entitytype = entitytype;
	}

	public Integer getDpCode() {
		return dpCode;
	}

	public void setDpCode(Integer dpCode) {
		this.dpCode = dpCode;
	}

}
