package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "districts")
@XmlAccessorType (XmlAccessType.FIELD)
public class DistrictListXML {
	
	@XmlElement(name = "district")
	private List<DistrictXML> districtXMLList = null;

	public List<DistrictXML> getDistrictXMLList() {
		return districtXMLList;
	}

	public void setDistrictXMLList(List<DistrictXML> districtXMLList) {
		this.districtXMLList = districtXMLList;
	}

}
