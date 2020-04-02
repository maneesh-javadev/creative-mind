package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subdistricts")
@XmlAccessorType (XmlAccessType.FIELD)
public class SubdistrictListXML {
	
	@XmlElement(name = "subdistrict")
	private List<SubdistrictXML> subdistrictXMLList = null;

	public List<SubdistrictXML> getSubdistrictXMLList() {
		return subdistrictXMLList;
	}

	public void setSubdistrictXMLList(List<SubdistrictXML> subdistrictXMLList) {
		this.subdistrictXMLList = subdistrictXMLList;
	}

	

}
