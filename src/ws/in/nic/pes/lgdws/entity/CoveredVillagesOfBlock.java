package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*@Entity
@NamedNativeQueries({
@NamedNativeQuery(
name="List_CoveredVillagesOfBlock", 
query="select v.village_code as villageCode, v.village_name_english as villageName, v.village_name_local as villageNameLocal, v.census_2011_code as census2011Code ,"+
		"from block_village bv, village v, localbody l, lb_covered_landregion lb where bv.blc = ? and bv.isactive and bv.vlc=v.vlc and v.isactive and v.vlc=lb.lrlc and lb.land_region_type='V'"+
"and lb.isactive and lb.lb_covered_region_code=l.lb_covered_region_code and l.isactive and l.local_body_type_code = 3 order by village_name_english",
resultClass=CoveredVillagesOfBlock.class)})
*/
@Entity

	@NamedNativeQuery(
	query="select v.village_code as village_code, v.village_name_english as village_name_english, v.village_name_local as village_name_local , "
			+ "v.census_2011_code as census_2011_code from block_village bv, village v "
			+ "where bv.blc =:blockCode  and bv.isactive and bv.vlc=v.vlc and v.isactive order by village_name_english",	name="List_CoveredVillagesOfBlock",	
			resultClass=CoveredVillagesOfBlock.class)


public class CoveredVillagesOfBlock {

	
	@Id
	@Column(name="village_code")
	private Integer villageCode;
	
    @Column(name="village_name_english")
	private String villageNameEnglish;

	@Column(name="village_name_local")
	private String villageNameLocal;

	
    @Column(name="census_2011_code")
	private String census2011Code;

	public Integer getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}

	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}

	public String getVillageNameLocal() {
		return villageNameLocal;
	}

	public void setVillageNameLocal(String villageNameLocal) {
		this.villageNameLocal = villageNameLocal;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
	
	
	

}
