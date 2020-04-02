package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "organization")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"orgCode","orgName"})
public class OrganiztionXML {
	
	@Column(name="org_code")
	private Integer orgCode;
	
	@Column(name="org_name")
	private String orgName;

	public Integer getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	
	

}
