package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "blocks")
@XmlAccessorType (XmlAccessType.FIELD)
public class WSBlockXMLList {
	
	@XmlElement(name = "block")
	private List<WSBlock> wsBlockList = null;

	public List<WSBlock> getWsBlockList() {
		return wsBlockList;
	}

	public void setWsBlockList(List<WSBlock> wsBlockList) {
		this.wsBlockList = wsBlockList;
	}
	
	

}
