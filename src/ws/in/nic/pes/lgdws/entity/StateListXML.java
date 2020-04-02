package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "states")
@XmlAccessorType (XmlAccessType.FIELD)
public class StateListXML {
	
	@XmlElement(name = "state")
	private List<StateXML> StateXMLList = null;

	public List<StateXML> getStateXMLList() {
		return StateXMLList;
	}

	public void setStateXMLList(List<StateXML> stateXMLList) {
		StateXMLList = stateXMLList;
	}

}
