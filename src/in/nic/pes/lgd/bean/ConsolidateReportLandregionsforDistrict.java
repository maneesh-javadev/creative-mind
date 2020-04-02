package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity

//@NamedNativeQuery(query="select district_code,district_name_english,district_name_local,b.census_2001_code,b.census_2011_code,(select count(*) from subdistrict c where a.slc=b.slc and b.dlc=c.dlc and a.isactive=true and b.isactive=true and c.isactive=true) SUB_DISTRICT_COUNT,(select count(*) from subdistrict c ,village d where a.slc=b.slc and b.dlc=c.dlc and c.tlc=d.tlc and a.isactive=true and b.isactive=true and c.isactive=true and d.isactive=true) VILLAGE_COUNT from state a,district b  where a.slc=b.slc and a.isactive=true and b.isactive=true and a.slc=:slc order by district_name_english",name="getConsolidateReportLandregionforDistrict",resultClass=ConsolidateReportLandregionsforDistrict.class)
@NamedNativeQuery(query="select district_code,district_name_english,district_name_local,b.census_2001_code,b.census_2011_code,(select count(*) from subdistrict c where a.slc=b.slc and b.dlc=c.dlc and a.isactive=true and b.isactive=true and c.isactive=true) SUB_DISTRICT_COUNT,(select count(*) from village d where  a.slc=d.slc and d.dlc=b.dlc and b.isactive=true  and d.isactive=true and a.isactive=true) VILLAGE_COUNT from state a,district b  where a.slc=b.slc and a.isactive=true and b.isactive=true and a.slc=:slc order by district_name_english",name="getConsolidateReportLandregionforDistrict",resultClass=ConsolidateReportLandregionsforDistrict.class)

public class ConsolidateReportLandregionsforDistrict {


	private int district_code;	
	private String district_name_english;
	private String district_name_local;
	private Integer noofsubdistricts;
	private Integer noofvillages;
	private String census_2001_code;
	private String census_2011_code;
	@Id
	@Column(name="district_code")
	public int getDistrict_code() {
		return district_code;
	}
	public void setDistrict_code(int district_code) {
		this.district_code = district_code;
	}
	@Column(name="district_name_english")
	public String getDistrict_name_english() {
		return district_name_english;
	}
	public void setDistrict_name_english(String district_name_english) {
		this.district_name_english = district_name_english;
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
	@Column(name="district_name_local")
	public String getDistrict_name_local() {
		return district_name_local;
	}
	public void setDistrict_name_local(String district_name_local) {
		this.district_name_local = district_name_local;
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
