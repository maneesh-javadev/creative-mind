package ws.in.nic.pes.lgdws.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
@XmlAccessorType (XmlAccessType.FIELD)
public class LevelwiseofRuralLbCountListXML {
	
	@XmlElement(name = "levelwiseofRuralLbCountXML")
	private List<LevelwiseofRuralLbCount> levelwiseofRuralLbCountListXML=null;

	public List<LevelwiseofRuralLbCount> getLevelwiseofRuralLbCountListXML() {
		return levelwiseofRuralLbCountListXML;
	}

	public void setLevelwiseofRuralLbCountListXML(List<LevelwiseofRuralLbCount> levelwiseofRuralLbCountListXML) {
		this.levelwiseofRuralLbCountListXML = levelwiseofRuralLbCountListXML;
	}
	
	
	

}
