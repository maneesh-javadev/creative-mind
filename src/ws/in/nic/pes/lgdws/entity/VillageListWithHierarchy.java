package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity

@NamedNativeQuery(name="FETCH_VillageListWithHierarchy",
query="select * from subdistrictwise_village_hierarchy(:subDistrictCode)",resultClass=VillageListWithHierarchy.class)


public class VillageListWithHierarchy {
	
	
	@Id
	/*@GeneratedValue(strategy=GenerationType.IDENTITY)*/
	@Column(name ="id")
	private Integer id;
	
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name ="state_name_english")
	private String stateNameEnglish;
	
	@Column(name="district_code")
	private Integer districtCode;
	
	@Column(name="district_name_english")
	private String districtNameEnglish;
	
	@Column(name ="subdistrict_code")
	private Integer subDistrictCode;
	
	@Column(name="subdistrict_name_english")
	private String subDistrictNameEnglish;
	
	@Column(name="block_code")
	private Integer blockCode;
	
	@Column(name= "block_name_english")
	private String blockNameEnglish;
	
	@Column(name ="local_body_code")
	private Integer localBodyCode;
	
	@Column(name ="local_body_type_code")
	private Integer localBodyTypeCode;
	
	
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	@Column(name="local_body_name_english")
	private String localBodyNameEnglish;
	
	
	
	@Column(name ="village_code")
	private Integer  villageCode;
	
	@Column(name ="village_name_english")
	private String villageNameEnglish;

	@Column(name="village_status")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}