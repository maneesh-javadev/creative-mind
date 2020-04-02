package in.nic.pes.lgd.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ws.in.nic.pes.lgdws.entity.CoveredVillagesOfBlockXML;

@XmlRootElement(name = "CoveredVillagesOfBlock")
@XmlAccessorType (XmlAccessType.FIELD)

public class CoveredVillagesOfBlockEntityXML 
{
	@XmlElement(name = "CoveredVillagesOfBlock")
	private List<CoveredVillagesOfBlockXML> coveredVillagesOfBlockEntityXML = null;

	public List<CoveredVillagesOfBlockXML> getCoveredVillagesOfBlockEntityXML() {
		return coveredVillagesOfBlockEntityXML;
	}

	public void setCoveredVillagesOfBlockEntityXML(List<CoveredVillagesOfBlockXML> coveredVillagesOfBlockEntityXML) {
		this.coveredVillagesOfBlockEntityXML = coveredVillagesOfBlockEntityXML;
	}

	
	
	}
