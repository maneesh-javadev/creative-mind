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
@NamedNativeQuery(query = "select district_code,cast(trim(district_name_english)as character varying)district_name_english from district where isactive and dlc=(select dlc from village where village_code=:villageCode and isactive) ", name = "District_LGD_Detail_RD_DEPT", resultClass = DistrictEntity.class)})

@XmlRootElement(name = "DistrictEntity")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"districtCode","districtNameEnglish"})
public class DistrictEntity {
	
	@Id
	@XmlElement(name="district-code")
	@Column(name="district_code")
	private Integer districtCode;
	
	@XmlElement(name="district-name-english")
	@Column(name="district_name_english")
	private String districtNameEnglish;

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
	

}
