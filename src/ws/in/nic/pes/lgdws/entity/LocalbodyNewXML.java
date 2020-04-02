package ws.in.nic.pes.lgdws.entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Localbody")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"localbodyCode","localBodyTypeCode","localBodyTypeName","localBodyNameEnglish","localBodyNameLocal"})
public class LocalbodyNewXML {
	
   @XmlElement(name= "local_body_code")
   private Integer localbodyCode;
   
   @XmlElement(name= "local_body_type_code")
   private Integer localBodyTypeCode;
   
   @XmlElement(name="local_body_type_name")
   private String localBodyTypeName;  
   
   @XmlElement(name="local_body_name_english")
   private String localBodyNameEnglish;
   
   @XmlElement(name = "local_body_name_local")
   private String localBodyNameLocal;

	public Integer getLocalBodyCode() {
		return localbodyCode;
	}
	
	public void setLocalBodyCode(Integer localBodyCode) {
		this.localbodyCode = localBodyCode;
	}
	
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	
	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}
	
	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
	   
   
   
}
 
   
 

