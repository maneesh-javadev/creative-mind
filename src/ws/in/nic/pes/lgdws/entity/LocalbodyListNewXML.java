package ws.in.nic.pes.lgdws.entity;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Localbodies")
@XmlAccessorType (XmlAccessType.FIELD)
public class LocalbodyListNewXML {

	@XmlElement(name = "Localbody")
	private List<LocalbodyNewXML> localbodyNewXML = null;

	public List<LocalbodyNewXML> getLocalbodyNewXML() {
		return localbodyNewXML;
	}

	public void setLocalbodyNewXML(List<LocalbodyNewXML> localbodyNewXML) {
		this.localbodyNewXML = localbodyNewXML;
	}
	
	
}
