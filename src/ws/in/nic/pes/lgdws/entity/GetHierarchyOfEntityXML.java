package ws.in.nic.pes.lgdws.entity;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GetHierarchyOfEntityXML")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"stateCode","stateNameEnglish","districtCode","districtNameEnglish","subDistrictCode","subDistrictNameEnglish","blockCode","blockNameEnglish","localBodyCode","localBodyNameEnglish", "localBodyTypeCode","localBodyTypeName","villageCode","villageNameEnglish"})

public class GetHierarchyOfEntityXML {
		
	
	
	@XmlElement(name="state_code")
	private Integer stateCode;
	
	@XmlElement(name ="state_name")
	private String stateNameEnglish;
	
	@XmlElement(name="district_code")
	private Integer districtCode;
	
	@XmlElement(name="district_name")
	private String districtNameEnglish;
	
	@XmlElement(name ="subdistrict_code")
	private Integer subDistrictCode;
	
	@XmlElement(name="subdistrict_name")
	private String subDistrictNameEnglish;
	
	@XmlElement(name="block_code")
	private Integer blockCode;
	
	@XmlElement(name= "block_name")
	private String blockNameEnglish;
	
	@XmlElement(name ="localbody_code")
	private Integer localBodyCode;
	
	@XmlElement(name="localbody_name")
	private String localBodyNameEnglish;
	
	
	@XmlElement(name ="localbody_type")
	private Integer localBodyTypeCode;
	
		
	@XmlElement(name="localbodytype_name")
	private String localBodyTypeName;
	
	
	@XmlElement(name ="village_code")
	private Integer  villageCode;
	
	@XmlElement(name ="village_name")
	private String villageNameEnglish;

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public Integer getSubDistrictCode() {
		return subDistrictCode;
	}

	public void setSubDistrictCode(Integer subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}

	public String getSubDistrictNameEnglish() {
		return subDistrictNameEnglish;
	}

	public void setSubDistrictNameEnglish(String subDistrictNameEnglish) {
		this.subDistrictNameEnglish = subDistrictNameEnglish;
	}

	public Integer getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(Integer blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}

	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
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

	public Integer getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}

	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}

	

}
