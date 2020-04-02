package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "localbodies")
@XmlAccessorType (XmlAccessType.FIELD)
public class LocalbodyListXML {
	
	@XmlElement(name = "localbody")
	private List<LocalbodyXML> localbodyXMLList = null;

	public List<LocalbodyXML> getLocalbodyXMLList() {
		return localbodyXMLList;
	}

	public void setLocalbodyXMLList(List<LocalbodyXML> localbodyXMLList) {
		this.localbodyXMLList = localbodyXMLList;
	}
	
	

}
