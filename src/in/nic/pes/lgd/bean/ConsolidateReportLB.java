package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(query=" SELECT * FROM get_state_wise_local_body_rpt() order by state_name_english",name="getConsolidateReportLB",resultClass=ConsolidateReportLB.class),
	@NamedNativeQuery(query=" SELECT * FROM get_state_wise_local_body_rpt_new() order by state_name_english",name="getConsolidateReportLBnew", resultClass=ConsolidateReportLB.class) 
	}
)


public class ConsolidateReportLB {

	private int id;
	private int stateCode;	
	private String stateNameEnglish;
	private String localbodyTypeName;	
	private char category;
	private char level;	
	private int count;
	
	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="state_code")
	public int getStateCode() {
		return stateCode;
	}
	
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	
	@Column(name="state_name_english")
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	
	@Column(name="local_body_type_name")
	public String getLocalbodyTypeName() {
		return localbodyTypeName;
	}
	public void setLocalbodyTypeName(String localbodyTypeName) {
		this.localbodyTypeName = localbodyTypeName;
	}
	
	@Column(name="category")
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	
	@Column(name="level")
	public char getLevel() {
		return level;
	}
	public void setLevel(char level) {
		this.level = level;
	}
	
	@Column(name="count")
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
