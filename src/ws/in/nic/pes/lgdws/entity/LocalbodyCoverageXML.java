package ws.in.nic.pes.lgdws.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "LocalbodyCovergae")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"landRegionCode","landRegionNameEnglish","landRegionNameLocal","landRegionType"})
public class LocalbodyCoverageXML {

	@XmlElement(name = "land_region_code")
	private int landRegionCode;
	
	@XmlElement(name = "land_region_name_english")
	private String landRegionNameEnglish;
	
	@XmlElement( name = "land_region_name_local")
	private String landRegionNameLocal;
	
	@XmlElement(name = "land_region_type")
	private String landRegionType;

	public int getLandRegionCode() {
		return landRegionCode;
	}

	public void setLandRegionCode(int landRegionCode) {
		this.landRegionCode = landRegionCode;
	}

	public String getLandRegionNameEnglish() {
		return landRegionNameEnglish;
	}

	public void setLandRegionNameEnglish(String landRegionNameEnglish) {
		this.landRegionNameEnglish = landRegionNameEnglish;
	}

	public String getLandRegionNameLocal() {
		return landRegionNameLocal;
	}

	public void setLandRegionNameLocal(String landRegionNameLocal) {
		this.landRegionNameLocal = landRegionNameLocal;
	}

	public String getLandRegionType() {
		return landRegionType;
	}

	public void setLandRegionType(String landRegionType) {
		this.landRegionType = landRegionType;
	}

	
	
	
	
}


   
   

 


