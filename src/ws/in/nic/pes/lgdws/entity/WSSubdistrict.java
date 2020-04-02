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
	@NamedNativeQuery(query="select subdistrict_code,subdistrict_name_english,subdistrict_name_local,census_2001_code," +
							"census_2011_code,sscode from subdistrict where isactive=true and dlc=:dlc order by subdistrict_name_english",
						 name="getSubdistrictList",resultClass=WSSubdistrict.class),
	@NamedNativeQuery(query="select subdistrict_code,subdistrict_name_english,subdistrict_name_local,census_2001_code," +
					    "census_2011_code,sscode from subdistrict where isactive=true and tlc=:tlc "
					    ,name="getSubDistrict",resultClass=WSSubdistrict.class)
})
@XmlRootElement
@XmlAccessorType
@XmlType(propOrder={"subdistrictCode","subdistrictNameEnglish","subdistrictNameLocal","census2001Code","census2011Code","ssCode"})

public class WSSubdistrict  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="subdistrict_code")
	private Integer subdistrictCode;
	@Column(name="subdistrict_name_english")
    private String subdistrictNameEnglish;
	@Column(name="subdistrict_name_local")
    private String subdistrictNameLocal;
	@Column(name="census_2001_code")
    private String census2001Code;
	@Column(name="census_2011_code")
	private String census2011Code;
	@Column(name="sscode")
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
