package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select state_code,state_name_english,s.census_2011_code as s_census_2011_code,district_code,district_name_english,d.census_2011_code as d_census_2011_code,subdistrict_code,subdistrict_name_english,t.census_2011_code as t_census_2011_code,village_code,village_name_english,v.census_2011_Code as v_census_2011_Code, block_code,block_name_english from state s, district d, subdistrict t, village v left join  block_village bv on bv.vlc=v.vlc and bv.isactive left join  block b on bv.blc=b.blc and b.isactive  where s.isactive and s.slc=d.slc and d.isactive and d.dlc=t.dlc and t.isactive and t.tlc=v.tlc and v.isactive\r\n" + 
		" union "
		+ "select state_code,state_name_english,s.census_2011_code as s_census_2011_code,district_code,district_name_english,d.census_2011_code as d_census_2011_code,subdistrict_code,subdistrict_name_english,t.census_2011_code as t_census_2011_code,village_code,village_name_english,v.census_2011_Code as v_census_2011_Code,block_code,block_name_english\r\n" + 
		" from state s, district d, subdistrict t, village v left join  block_village bv on bv.vlc=v.vlc and bv.isactive left join  block b on bv.blc=b.blc and b.isactive left join block_districts bd on b.dlc = bd.dlc where s.isactive and s.slc=d.slc and d.isactive and d.dlc=t.dlc and t.isactive and t.tlc=v.tlc and v.isactive  order by state_name_english,district_name_english,subdistrict_name_english,village_name_english",name ="subdistrictVillageBlockMapping",resultClass=SubDistrictBlockMapEntities.class)
public class SubDistrictBlockMapEntities {

	@Id
	@Column(name="village_code")
	private Integer villageCode;
	
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="district_code")
	private Integer districtCode;
	
	@Column(name="subdistrict_code")
	private Integer subdistrictCode;
	
	@Column(name="block_code")
	private Integer blockCode;
	
	@Column(name="state_name_english")
	private String stateNameEnglish;
	
	@Column(name="district_name_english")
	private String districtNameEnglish;
	
	@Column(name="subdistrict_name_english")
	private String subdistrictNameEnglish;
	
	@Column(name="village_name_english")
	private String villageNameEnglish;
	
	@Column(name="block_name_english")
	private String blockNameEnglish;
	
	@Column(name="s_census_2011_code")
	private String stateCensus2011code;
	
	@Column(name="d_census_2011_code")
	private String dCensus2011code;
	
	@Column(name="t_census_2011_code")
	private String tCensus2011code;
	
	@Column(name="v_census_2011_code")
	private String vCensus2011code;

	public Integer getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(Integer subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public Integer getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(Integer blockCode) {
		this.blockCode = blockCode;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}

	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}

	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}

	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}

	public String getStateCensus2011code() {
		return stateCensus2011code;
	}

	public void setStateCensus2011code(String stateCensus2011code) {
		this.stateCensus2011code = stateCensus2011code;
	}

	public String getdCensus2011code() {
		return dCensus2011code;
	}

	public void setdCensus2011code(String dCensus2011code) {
		this.dCensus2011code = dCensus2011code;
	}

	public String gettCensus2011code() {
		return tCensus2011code;
	}

	public void settCensus2011code(String tCensus2011code) {
		this.tCensus2011code = tCensus2011code;
	}

	public String getvCensus2011code() {
		return vCensus2011code;
	}

	public void setvCensus2011code(String vCensus2011code) {
		this.vCensus2011code = vCensus2011code;
	}

	
}
