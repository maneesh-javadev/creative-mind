package ws.in.nic.pes.lgdws.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "subdistrict")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"subdistrictCode","subdistrictNameEnglish","subdistrictNameLocal","census2001Code","census2011Code","ssCode"})
public class SubdistrictXML {
	
	
	@XmlElement(name="subdistrict_code")
	private Integer subdistrictCode;
	@XmlElement(name="subdistrict_name_english")
    private String subdistrictNameEnglish;
	@XmlElement(name="subdistrict_name_local")
    private String subdistrictNameLocal;
	@XmlElement(name="census_2001_code")
    private String census2001Code;
	@XmlElement(name="census_2011_code")
	private String census2011Code;
	@XmlElement(name="sscode")
    private String ssCode;
	public Integer getSubdistrictCode() {
		return subdistrictCode;
	}
	public void setSubdistrictCode(Integer subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}
	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}
	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}
	public String getSubdistrictNameLocal() {
		return subdistrictNameLocal;
	}
	public void setSubdistrictNameLocal(String subdistrictNameLocal) {
		this.subdistrictNameLocal = subdistrictNameLocal;
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
