package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GetHierarchyOfEntity")
@XmlAccessorType (XmlAccessType.FIELD)

public class GetHierarchyOfEntityXmlList {

	
	@XmlElement(name = "GetHierarchyOfEntity")
	private List<GetHierarchyOfEntityXML> getHierarchyOfEntityXMLList = null;

	public List<GetHierarchyOfEntityXML> getGetHierarchyOfEntityXMLList() {
		return getHierarchyOfEntityXMLList;
	}

	public void setGetHierarchyOfEntityXMLList(List<GetHierarchyOfEntityXML> getHierarchyOfEntityXMLList) {
		this.getHierarchyOfEntityXMLList = getHierarchyOfEntityXMLList;
	}

	

	
}
