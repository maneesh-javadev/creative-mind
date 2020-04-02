package ws.in.nic.pes.lgdws.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "state")
@XmlAccessorType (XmlAccessType.FIELD)
public class StateXML {
	
	@XmlElement(name="state-code")
	private Integer stateCode;
	
	@XmlElement(name="state-name-english")
	private String stateNameEnglish;
	
	@XmlElement(name="state-name-local")
	private String stateNameLocal;
	
	@XmlElement(name="census-2001-code")
    private String census2001Code;
	
	@XmlElement(name="census-2011-code")
	private String census2011Code;


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

	public String getStateNameLocal() {
		return stateNameLocal;
	}

	public void setStateNameLocal(String stateNameLocal) {
		this.stateNameLocal = stateNameLocal;
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
	
	

}
