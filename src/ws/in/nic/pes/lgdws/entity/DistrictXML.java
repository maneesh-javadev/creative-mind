package ws.in.nic.pes.lgdws.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "district")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"districtCode","districtNameEnglish","districtNameLocal","census2001Code","census2011Code","ssCode"})
public class DistrictXML {
	
	@XmlElement(name="district_code")
	private Integer districtCode;
	
	@XmlElement(name="district_name_english")
	private String districtNameEnglish;
	
	@XmlElement(name="district_name_local")
	private String districtNameLocal;
	
	@XmlElement(name="census_2001_code")
    private String census2001Code;
	
	@XmlElement(name="census_2011_code")
	private String census2011Code;
	
	@XmlElement(name="sscode")
    private String ssCode;

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

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public String getDistrictNameLocal() {
		return districtNameLocal;
	}

	public void setDistrictNameLocal(String districtNameLocal) {
		this.districtNameLocal = districtNameLocal;
	}
	
	

}
