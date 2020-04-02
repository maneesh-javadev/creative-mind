package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;



@Entity



@NamedNativeQuery(query="select * from get_constituency_list_fn(:statecode,:constituencyType,:pcCode,:limit,:offset)",name="getParliamentConstituencymodify",resultClass=ParliamentConstituencymodify.class)

public class ParliamentConstituencymodify {
	
	private Integer constituency_code;
	private Integer parent_constituency_code;
	private String constituency_name_english;
	private String constituency_name_local;
	private Integer eci_code;
	private String constituency_replacedby;
	private String constituency_replaces;
	@Id
	@Column(name="constituency_code")

	public Integer getConstituency_code() {
		return constituency_code;
	}
	public void setConstituency_code(Integer constituency_code) {
		this.constituency_code = constituency_code;
	}
	@Column(name="parent_constituency_code")

	public Integer getParent_constituency_code() {
		return parent_constituency_code;
	}
	public void setParent_constituency_code(Integer parent_constituency_code) {
		this.parent_constituency_code = parent_constituency_code;
	}
	@Column(name="constituency_name_english")

	public String getConstituency_name_english() {
		return constituency_name_english;
	}
	public void setConstituency_name_english(String constituency_name_english) {
		this.constituency_name_english = constituency_name_english;
	}
	@Column(name="constituency_name_local")

	public String getConstituency_name_local() {
		return constituency_name_local;
	}
	public void setConstituency_name_local(String constituency_name_local) {
		this.constituency_name_local = constituency_name_local;
	}
	@Column(name="eci_code", nullable = true)
	public Integer getEci_code() {
		return eci_code;
	}
	public void setEci_code(Integer eci_code) {
		this.eci_code = eci_code;
	}
	@Column(name="constituency_replacedby")

	public String getConstituency_replacedby() {
		return constituency_replacedby;
	}
	public void setConstituency_replacedby(String constituency_replacedby) {
		this.constituency_replacedby = constituency_replacedby;
	}
	@Column(name="constituency_replaces")

	public String getConstituency_replaces() {
		return constituency_replaces;
	}
	public void setConstituency_replaces(String constituency_replaces) {
		this.constituency_replaces = constituency_replaces;
	}
	   
	
		
		
		
}
