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

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select subdistrict_code,cast(trim(subdistrict_name_english)as character varying)subdistrict_name_english from subdistrict where isactive and tlc=(select tlc from village where village_code=:villageCode and isactive) ", name = "Sub-District_LGD_Detail_RD_DEPT", resultClass = SubdistrictEntity.class)})

@XmlRootElement(name = "SubdistrictEntity")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"subdistrictCode","subdistrictNameEnglish"})
public class SubdistrictEntity {
	
	@Id
	@XmlElement(name="subdistrict-code")
	@Column(name="subdistrict_code")
	private Integer subdistrictCode;
	
	@XmlElement(name="subdistrict-name-english")
	@Column(name="subdistrict_name_english")
    private String subdistrictNameEnglish;

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
	
	
	

}
