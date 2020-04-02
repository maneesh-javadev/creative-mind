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
@NamedNativeQuery(query = "select state_code,cast(trim(state_name_english)as character varying)state_name_english from state s where isactive and slc=(select slc from village where village_code=:villageCode and isactive)  ", name = "State_LGD_Detail_RD_DEPT", resultClass = StateEntity.class)})

@XmlRootElement(name = "StateEntity")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"stateCode","stateNameEnglish"})

public class StateEntity {
	
	@Id
	@XmlElement(name="state-code")
	@Column(name="state_code")
	private Integer stateCode;
	
	@XmlElement(name="state-name-english")
	@Column(name="state_name_english")
	private String stateNameEnglish;

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	
	

}
