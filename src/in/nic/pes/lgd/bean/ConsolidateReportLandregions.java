package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
//@NamedNativeQuery(query="select state_code,state_name_english,census_2001_code,census_2011_code,(select count(*) from district b  where a.slc=b.slc  and a.isactive=true and b.isactive=true) DISTRICT_COUNT,(select count(*)from district b ,subdistrict c where a.slc=b.slc and b.dlc=c.dlc and a.isactive=true and b.isactive=true and c.isactive=true) SUB_DISTRICT_COUNT,(select count(*) from district b ,subdistrict c ,village d where a.slc=b.slc and b.dlc=c.dlc and c.tlc=d.tlc and a.isactive=true and b.isactive=true and c.isactive=true and d.isactive=true) VILLAGE_COUNT from state a where a.isactive=true order by state_name_english",name="getConsolidateReportLandregion",resultClass=ConsolidateReportLandregions.class)
@NamedNativeQuery(query="select state_code,state_name_english,census_2001_code,census_2011_code,(select count(*) from district b  where a.slc=b.slc  and a.isactive=true and b.isactive=true) DISTRICT_COUNT,(select count(*)from district b ,subdistrict c where a.slc=b.slc and b.dlc=c.dlc and a.isactive=true and b.isactive=true and c.isactive=true) SUB_DISTRICT_COUNT,(select count(*) from village d where a.slc=d.slc and a.isactive=true and d.isactive=true) VILLAGE_COUNT from state a where a.isactive=true order by state_name_english",name="getConsolidateReportLandregion",resultClass=ConsolidateReportLandregions.class)

public class ConsolidateReportLandregions {


	private int stateCode;	
	private String stateNameEnglish;
	private Integer noofdistricts;
	private Integer noofsubdistricts;
	private Integer noofvillages;
	private String census_2001_code;
	private String census_2011_code;
	
	
	@Id
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
	
	
	@Column(name="district_count")
	public Integer getNoofdistricts() {
		return noofdistricts;
	}

	public void setNoofdistricts(Integer noofdistricts) {
		this.noofdistricts = noofdistricts;
	}
	@Column(name="sub_district_count")
	public Integer getNoofsubdistricts() {
		return noofsubdistricts;
	}

	public void setNoofsubdistricts(Integer noofsubdistricts) {
		this.noofsubdistricts = noofsubdistricts;
	}
	@Column(name="village_count")
	public Integer getNoofvillages() {
		return noofvillages;
	}

	public void setNoofvillages(Integer noofvillages) {
		this.noofvillages = noofvillages;
	}
	@Column(name="census_2001_code")
	public String getCensus_2001_code() {
		return census_2001_code;
	}

	public void setCensus_2001_code(String census_2001_code) {
		this.census_2001_code = census_2001_code;
	}
	@Column(name="census_2011_code")
	public String getCensus_2011_code() {
		return census_2011_code;
	}

	public void setCensus_2011_code(String census_2011_code) {
		this.census_2011_code = census_2011_code;
	}
	
	
	
	
}
