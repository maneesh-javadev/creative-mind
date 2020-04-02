package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT * FROM get_state_wise_rural_local_body_rpt_by_finyear(:financialYear) order by state_name_english",name="getConsolidateReportForRuralLB",resultClass=ConsolidateReportForRuralLB.class),
@NamedNativeQuery(query=" SELECT * FROM get_state_wise_rural_local_body_rpt_by_finyear() order by state_name_english",name="getConsolidateReportForRuralLBWithoutFinYear",resultClass=ConsolidateReportForRuralLB.class),
@NamedNativeQuery(query=" SELECT * FROM get_state_wise_rural_local_body_rpt_by_finyear_pes(:financialYear) order by state_name_english",name="getConsolidateReportForRuralLBPES",resultClass=ConsolidateReportForRuralLB.class),
@NamedNativeQuery(query=" SELECT * FROM get_state_wise_rural_local_body_rpt_by_finyear_pes() order by state_name_english",name="getConsolidateReportForRuralLBWithoutFinYearPES",resultClass=ConsolidateReportForRuralLB.class)
})

public class ConsolidateReportForRuralLB {

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



















