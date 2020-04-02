package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "root")
@XmlAccessorType (XmlAccessType.FIELD)
public class WardDetailListXML {
	
	@XmlElement(name = "Ward")
	private List<WardDetailList> wardDetailList=null;

	public List<WardDetailList> getWardDetailList() {
		return wardDetailList;
	}

	public void setWardDetailList(List<WardDetailList> wardDetailList) {
		this.wardDetailList = wardDetailList;
	}

}
