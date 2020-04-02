package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQuery(query = "select row_number() OVER () as id, * from get_dashboard_lb_details(:stateCode,:flag)", name = "DashboardLBDetails", resultClass = GETDashboardLBDetails.class)
public class GETDashboardLBDetails {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="state_name_english")
	private String stateNameEnglish;
	
	@Column(name="district_detail")
	private String districtDetail;
	
	@Column(name="local_body_code")
	private Integer localbodyCode;
	
	@Column(name="local_body_name_english")
	private String localbodyCodeNameEnglish;
	
	@Column(name="hierarchy")
	private String hierarchy;
	
	@Transient
	private String flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	

	public Integer getLocalbodyCode() {
		return localbodyCode;
	}

	public void setLocalbodyCode(Integer localbodyCode) {
		this.localbodyCode = localbodyCode;
	}

	public String getLocalbodyCodeNameEnglish() {
		return localbodyCodeNameEnglish;
	}

	public void setLocalbodyCodeNameEnglish(String localbodyCodeNameEnglish) {
		this.localbodyCodeNameEnglish = localbodyCodeNameEnglish;
	}

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getDistrictDetail() {
		return districtDetail;
	}

	public void setDistrictDetail(String districtDetail) {
		this.districtDetail = districtDetail;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
	

}
