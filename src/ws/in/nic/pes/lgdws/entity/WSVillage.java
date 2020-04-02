package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedNativeQueries(
{
	@NamedNativeQuery(query="select village_code,village_name_english, village_name_local,census_2001_code," +
							"census_2011_code,sscode from village where isactive=true and tlc=:tlc order by  village_name_english",
						 name="getVillageList",resultClass=WSVillage.class),
	@NamedNativeQuery(query="select village_code,village_name_english,village_name_local,census_2001_code," +
					    "census_2011_code,sscode from village where isactive=true and vlc=:vlc "
					    ,name="getVillage",resultClass=WSVillage.class),
	@NamedNativeQuery(query="select village_code,village_name_english,village_name_local,census_2001_code," +
		    "census_2011_code,sscode from village where isactive=true order by vlc offset :poffset limit 100000"
		    ,name="getVillageALl",resultClass=WSVillage.class)
})
@XmlRootElement
@XmlAccessorType
@XmlType(propOrder={"villageCode","villageNameEnglish","villageNameLocal","census2001Code","census2011Code","ssCode"})
public class WSVillage {
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="village_code")
	private Integer villageCode;
	@Column(name="village_name_english")
    private String villageNameEnglish;
	@Column(name="village_name_local")
    private String villageNameLocal;
	@Column(name="census_2001_code")
    private String census2001Code;
	@Column(name="census_2011_code")
	private String census2011Code;
	@Column(name="sscode")
    private String ssCode;
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
	public String getCensus2001Code() {
		return census2001Code;
	}
	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}
	public String getCensus2011Code() {
		return census2011Code;
	}
	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
	public String getSsCode() {
		return ssCode;
	}
	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
