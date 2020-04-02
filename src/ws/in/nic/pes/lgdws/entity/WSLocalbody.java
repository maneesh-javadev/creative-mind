package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries(
{
	@NamedNativeQuery(query="select local_body_code,local_body_name_english,local_body_name_local,cast(null as int)census_2011_code,cast(null as character varying(8))census_2001_code "
						   + "from get_landregionwise_lb_list_fn(:landRegionType,:entityCode,:lbTypeCode) order by local_body_name_english",
					   name="getLbListbuSubdistrict",resultClass=WSLocalbody.class),
	
	@NamedNativeQuery(query="select l.local_body_code,l.local_body_name_english,l.local_body_name_local,l.census_2011_code,l.census_2001_code from localbody l inner join localbody_districts ld "
						   + " on l.local_body_code=ld.local_body_code  and l.local_body_version=ld.local_body_version "
						   + " where l.isactive and ld.district_code=:districtCode and l.local_body_type_code=:lbTypeCode order by local_body_name_english",
						   name="getLocalbodyListbyDistrictnLbTypeCode",resultClass=WSLocalbody.class),
	
	@NamedNativeQuery(query="select l.local_body_code,l.local_body_name_english,l.local_body_name_local,l.census_2011_code,l.census_2001_code from localbody l where l.lb_covered_region_code in (select lb_covered_region_code from lb_covered_landregion  where  land_region_type ='V' and lrlc" + 
	" in( select vlc from block_village where blc =:blockCode and isactive) and isactive) and l.isactive order by l.local_body_name_english",name="getBlockwiswGP",resultClass=WSLocalbody.class),
	
	@NamedNativeQuery(query="select * from get_landregionwise_lb_list(:landRegionType, :landRegionCode) where local_body_type_code in (4,5,6,7,8,21)",
	name="getUrbanLocalbodyBasedOnLandRegiontypeAndCode",resultClass=WSLocalbody.class)
})
public class WSLocalbody {
	
	@Id
	@Column(name="local_body_code")
	private Integer localbodyCode;
	
	@Column(name="local_body_name_english")
	private String localbodyNameEnglish;
	
	@Column(name="local_body_name_local")
	private String localbodyNameLocal;
	
	@Column(name="census_2011_code")
	private String census2011Code;
	
	@Column(name="census_2001_code")
	private String census2001Code;

	public Integer getLocalbodyCode() {
		return localbodyCode;
	}

	public String getLocalbodyNameEnglish() {
		return localbodyNameEnglish;
	}

	public void setLocalbodyNameEnglish(String localbodyNameEnglish) {
		this.localbodyNameEnglish = localbodyNameEnglish;
	}

	public String getLocalbodyNameLocal() {
		return localbodyNameLocal;
	}

	public void setLocalbodyNameLocal(String localbodyNameLocal) {
		this.localbodyNameLocal = localbodyNameLocal;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	public String getCensus2001Code() {
		return census2001Code;
	}

	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}

	public void setLocalbodyCode(Integer localbodyCode) {
		this.localbodyCode = localbodyCode;
	}

	
	
	
	

}
