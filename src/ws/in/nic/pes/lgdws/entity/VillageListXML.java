package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "village")
@XmlAccessorType (XmlAccessType.FIELD)
public class VillageListXML {
	
	
	@XmlElement(name = "villages")
	private List<VillageXML> villageXMLList = null;

	public List<VillageXML> getVillageXMLList() {
		return villageXMLList;
	}

	public void setVillageXMLList(List<VillageXML> villageXMLList) {
		this.villageXMLList = villageXMLList;
	}

}
