package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CoveredVillagesOfBlock ")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"villageCode","villageNameEnglish","villageNameLocal","census2011Code"})



public class CoveredVillagesOfBlockXML {
	
	
	@Id
	@XmlElement(name="village_code")
    private Integer villageCode;
	
	@XmlElement(name="village_name_english")
    private String villageNameEnglish;

	@XmlElement(name="village_name_local")
	private String villageNameLocal;

	
	@XmlElement(name="census_2011_code")
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
