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
	@NamedNativeQuery(query="select district_code,district_name_english,district_name_local,census_2001_code," +
							"census_2011_code,sscode from district where isactive=true and slc=:slc order by district_name_english",
						 name="getDistrictList",resultClass=WSDistrict.class),
	@NamedNativeQuery(query="select district_code,district_name_english,district_name_local,census_2001_code," +
					    "census_2011_code,sscode from district where isactive=true and dlc=:dlc "
					    ,name="getDistrict",resultClass=WSDistrict.class)
})
@XmlRootElement
@XmlAccessorType
@XmlType(propOrder={"districtCode","districtNameEnglish","districtNameLocal","census2001Code","census2011Code","ssCode"})

public class WSDistrict implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="district_code")
	private Integer districtCode;
	@Column(name="district_name_english")
    private String districtNameEnglish;
	@Column(name="district_name_local")
    private String districtNameLocal;
	@Column(name="census_2001_code")
    private String census2001Code;
	@Column(name="census_2011_code")
	private String census2011Code;
	@Column(name="sscode")
    private String ssCode;
	public Integer getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictNameEnglish() {
		return (districtNameEnglish!=null?districtNameEnglish.trim():null);
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	public String getDistrictNameLocal() {
		return (districtNameLocal!=null?districtNameLocal.trim():null);
	}
	public void setDistrictNameLocal(String districtNameLocal) {
		this.districtNameLocal = districtNameLocal;
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
