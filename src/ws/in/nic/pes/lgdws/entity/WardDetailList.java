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
@NamedNativeQuery(query = "select ward_code,ward_number,ward_name_english from localbody_ward  where lblc=(select lblc from localbody where local_body_code=:localbodyCode and isactive)"
						,  name = "WARD_DETAIL_LIST_BY_LOCALBODY",resultClass = WardDetailList.class),

})


@XmlRootElement(name = "Ward")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"wardCode","wardNumber","wardNameEnglish"})
public class WardDetailList {
	
@Id
@Column(name="ward_code")
@XmlElement(name="Ward-Code")
private Integer wardCode;

@Column(name="ward_number")
@XmlElement(name="Ward-Number")
private String wardNumber;

@Column(name="ward_name_english")
@XmlElement(name="Ward-Name-English")
private String wardNameEnglish;

public Integer getWardCode() {
	return wardCode;
}

public void setWardCode(Integer wardCode) {
	this.wardCode = wardCode;
}

public String getWardNumber() {
	return wardNumber;
}

public void setWardNumber(String wardNumber) {
	this.wardNumber = wardNumber;
}

public String getWardNameEnglish() {
	return wardNameEnglish;
}

public void setWardNameEnglish(String wardNameEnglish) {
	this.wardNameEnglish = wardNameEnglish;
}





	
}
