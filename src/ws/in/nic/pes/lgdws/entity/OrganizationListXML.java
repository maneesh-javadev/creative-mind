package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "organiztions")
@XmlAccessorType (XmlAccessType.FIELD)
public class OrganizationListXML {

	@XmlElement(name = "organization")
	private List<OrganiztionXML> organizationXMLList = null;

	public List<OrganiztionXML> getOrganizationXMLList() {
		return organizationXMLList;
	}

	public void setOrganizationXMLList(List<OrganiztionXML> organizationXMLList) {
		this.organizationXMLList = organizationXMLList;
	}
	
	

}
