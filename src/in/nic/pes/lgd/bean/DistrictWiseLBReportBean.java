package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT row_number() OVER () as rid, *  from rpt_for_localbodies_by_district(:entity_code)",name="DistrictWiseLBReportData",resultClass=DistrictWiseLBReportBean.class)

public class DistrictWiseLBReportBean {
	
	private Integer local_body_code;
	private String local_body_name_english;
	private String local_body_type_name;
	private String village_name;
	private String parent_name;
	
	@Id
	@Column(name = "rid", nullable = false)
	private Integer rid;
	
	@Column(name = "local_body_code", nullable = false)
	public Integer getLocal_body_code() {
		return local_body_code;
	}
	public void setLocal_body_code(Integer local_body_code) {
		this.local_body_code = local_body_code;
	}
	
	@Column(name = "local_body_name_english")
	public String getLocal_body_name_english() {
		return local_body_name_english;
	}
	public void setLocal_body_name_english(String local_body_name_english) {
		this.local_body_name_english = local_body_name_english;
	}
	
	@Column(name = "local_body_type_name")
	public String getLocal_body_type_name() {
		return local_body_type_name;
	}
	public void setLocal_body_type_name(String local_body_type_name) {
		this.local_body_type_name = local_body_type_name;
	}
	
	@Column(name = "village_name")
	public String getVillage_name() {
		return village_name;
	}
	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}
	
	@Column(name = "parent_name")
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
	

}
