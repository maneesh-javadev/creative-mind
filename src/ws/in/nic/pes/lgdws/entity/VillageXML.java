package ws.in.nic.pes.lgdws.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "village")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"villageCode","villageNameEnglish","villageNameLocal","census2001Code","census2011Code","ssCode"})
public class VillageXML {

	@XmlElement(name="village_code")
	private Integer villageCode;
	@XmlElement(name="village_name_english")
    private String villageNameEnglish;
	@XmlElement(name="village_name_local")
    private String villageNameLocal;
	@XmlElement(name="census_2001_code")
    private String census2001Code;
	@XmlElement(name="census_2011_code")
	private String census2011Code;
	@XmlElement(name="sscode")
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
	
	
}
