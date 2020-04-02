package ws.in.nic.pes.lgdws.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "block")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"blockCode","blockNameEnglish","blockNameLocal"})
public class WSBlock {
	
	@XmlElement(name="block-code")
	private int blockCode;
	
	@XmlElement(name="block-name-english")
	private String blockNameEnglish;
	
	@XmlElement(name="block-name-local")
	private String blockNameLocal;

	public int getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(int blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}

	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}

	public String getBlockNameLocal() {
		return blockNameLocal;
	}

	public void setBlockNameLocal(String blockNameLocal) {
		this.blockNameLocal = blockNameLocal;
	}
	 
	
}
