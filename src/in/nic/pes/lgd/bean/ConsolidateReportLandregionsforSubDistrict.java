package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
//@NamedNativeQuery(query="select subdistrict_code,subdistrict_name_english,subdistrict_name_local,c.census_2001_code,c.census_2011_code,(select count(*) from village d  where a.slc=b.slc and b.dlc=c.dlc and c.tlc=d.tlc and a.isactive=true and b.isactive=true and c.isactive=true and d.isactive=true) VILLAGE_COUNT from state a,district b,subdistrict c where a.slc=b.slc and b.dlc=c.dlc and a.isactive=true and b.isactive=true and c.isactive=true and a.slc=:slc and b.dlc=:dlc order by subdistrict_name_english",name="getConsolidateReportLandregionforSubDistrict",resultClass=ConsolidateReportLandregionsforSubDistrict.class)
@NamedNativeQuery(query="select subdistrict_code,subdistrict_name_english,subdistrict_name_local,c.census_2001_code,c.census_2011_code,(select count(*) from village d  where a.slc=d.slc and b.dlc=d.dlc and d.tlc=c.tlc and a.isactive=true and b.isactive=true and d.isactive=true and c.isactive=true) VILLAGE_COUNT from state a,district b,subdistrict c where a.slc=b.slc and b.dlc=c.dlc and a.isactive=true and b.isactive=true and c.isactive=true and a.slc=:slc and b.dlc=:dlc order by subdistrict_name_english",name="getConsolidateReportLandregionforSubDistrict",resultClass=ConsolidateReportLandregionsforSubDistrict.class)

public class ConsolidateReportLandregionsforSubDistrict {


	private int subdistrict_code;	
	private String subdistrict_name_local;
	private String subdistrict_name_english;
	private Integer noofvillages;
	private String census_2001_code;
	private String census_2011_code;
	@Id
	@Column(name="subdistrict_code")
	public int getSubdistrict_code() {
		return subdistrict_code;
	}
	public void setSubdistrict_code(int subdistrict_code) {
		this.subdistrict_code = subdistrict_code;
	}
		
	@Column(name="village_count")
	public Integer getNoofvillages() {
		return noofvillages;
	}
	public void setNoofvillages(Integer noofvillages) {
		this.noofvillages = noofvillages;
	}
	@Column(name="subdistrict_name_local")
	public String getSubdistrict_name_local() {
		return subdistrict_name_local;
	}
	public void setSubdistrict_name_local(String subdistrict_name_local) {
		this.subdistrict_name_local = subdistrict_name_local;
	}
	@Column(name="subdistrict_name_english")
	public String getSubdistrict_name_english() {
		return subdistrict_name_english;
	}
	public void setSubdistrict_name_english(String subdistrict_name_english) {
		this.subdistrict_name_english = subdistrict_name_english;
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
