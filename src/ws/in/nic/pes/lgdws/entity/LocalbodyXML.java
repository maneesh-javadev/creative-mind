package ws.in.nic.pes.lgdws.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "localbody")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"localbodyCode","localbodyNameEnglish","localbodyNameLocal"})
public class LocalbodyXML {
	
	@XmlElement(name="local_body_code")
	private Integer localbodyCode;
	
	@XmlElement(name="local_body_name_english")
	private String localbodyNameEnglish;
	
	@XmlElement(name="local_body_name_local")
	private String localbodyNameLocal;
	

	public Integer getLocalbodyCode() {
		return localbodyCode;
	}

	public String getLocalbodyNameEnglish() {
		return localbodyNameEnglish;
	}

	public void setLocalbodyNameEnglish(String localbodyNameEnglish) {
		this.localbodyNameEnglish = localbodyNameEnglish;
	}

	public String getLocalbodyNameLocal() {
		return localbodyNameLocal;
	}

	public void setLocalbodyNameLocal(String localbodyNameLocal) {
		this.localbodyNameLocal = localbodyNameLocal;
	}

	

	public void setLocalbodyCode(Integer localbodyCode) {
		this.localbodyCode = localbodyCode;
	}

	

	
	
	

}
