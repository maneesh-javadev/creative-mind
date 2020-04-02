package ws.in.nic.pes.lgdws.entity;


import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "VillageListWithHierarchy")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"stateCode","stateNameEnglish","districtCode","districtNameEnglish","subDistrictCode","subDistrictNameEnglish","blockCode","blockNameEnglish","localBodyCode","localBodyNameEnglish", "localBodyTypeCode","villageCode","villageNameEnglish","villageStatus"})


	public class VillageListWithHierarchyXML {
	
	@XmlElement(name="state_code")
	private Integer stateCode;
	
	@XmlElement(name ="state_name_english")
	private String stateNameEnglish;
	
	@XmlElement(name="district_code")
	private Integer districtCode;
	
	@XmlElement(name="district_name_english")
	private String districtNameEnglish;
	
	@XmlElement(name ="subdistrict_code")
	private Integer subDistrictCode;
	
	@XmlElement(name="subdistrict_name_english")
	private String subDistrictNameEnglish;
	
	@XmlElement(name="block_code")
	private Integer blockCode;
	
	@XmlElement(name= "block_name_english")
	private String blockNameEnglish;
	
	@XmlElement(name ="local_body_code")
	private Integer localBodyCode;
	
	
	

	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	@XmlElement(name="local_body_name_english")
	private String localBodyNameEnglish;
	
	@XmlElement(name="local_body_type_code")
	private Integer localBodyTypeCode;
	
	@XmlElement(name="village_code")
	private Integer  villageCode;
	
	@XmlElement(name="village_name_english")
	private String villageNameEnglish;
	
	@XmlElement(name="village_status")
	private String villageStatus;
	
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

	public String getVillageStatus() {
		return villageStatus;
	}

	public void setVillageStatus(String villageStatus) {
		this.villageStatus = villageStatus;
	}
	
	
	
		

	}


