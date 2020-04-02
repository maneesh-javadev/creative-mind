package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="select village_code,village_name_english,village_name_local,census_2001_code,census_2011_code from village where  tlc=:tlc and isactive=true order by village_name_english ",name="getConsolidateReportLandregionforVillage",resultClass=ConsolidateReportLandregionsforVillage.class)
public class ConsolidateReportLandregionsforVillage {


	
	@Id
	@Column(name="village_code")
	private int village_code;
    private String village_name_english;
    private String village_name_local;
    private String census_2001_code;
	private String census_2011_code;
	public int getVillage_code() {
		return village_code;
	}

	public void setVillage_code(int village_code) {
		this.village_code = village_code;
	}
	@Column(name="village_name_english")
	public String getVillage_name_english() {
		return village_name_english;
	}

	public void setVillage_name_english(String village_name_english) {
		this.village_name_english = village_name_english;
	}
	@Column(name="village_name_local")
	public String getVillage_name_local() {
		return village_name_local;
	}

	public void setVillage_name_local(String village_name_local) {
		this.village_name_local = village_name_local;
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
