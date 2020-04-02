package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "localbodyCoverageList")
@XmlAccessorType (XmlAccessType.FIELD)
public class LocalbodyCoverageListXML {

	@XmlElement(name = "Localbodycovergae")
	private List<LocalbodyCoverageXML> localbodyCoverageXMLList = null;

	public List<LocalbodyCoverageXML> getLocalbodyCoverageXMLList() {
		return localbodyCoverageXMLList;
	}

	public void setLocalbodyCoverageXMLList(List<LocalbodyCoverageXML> localbodyCoverageXMLList) {
		this.localbodyCoverageXMLList = localbodyCoverageXMLList;
	}
	
	
	
}


