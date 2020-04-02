package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity

@NamedNativeQuery(name="FETCH_HierarchyOfEntity",
query="select * from get_parenthierarchy_of_entity(:entityType ,:entityCode)",resultClass=GetHierarchyOfEntity.class)


public class GetHierarchyOfEntity {
	
	@Id
	/*@GeneratedValue(strategy=GenerationType.IDENTITY)*/
	@Column(name ="row_number")
	private Integer id;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name="state_code")
	private Integer stateCode;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name ="state_name")
	private String stateNameEnglish;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name="district_code")
	private Integer districtCode;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name="district_name")
	private String districtNameEnglish;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name ="subdistrict_code")
	private Integer subDistrictCode;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name="subdistrict_name")
	private String subDistrictNameEnglish;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name="block_code")
	private Integer blockCode;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name= "block_name")
	private String blockNameEnglish;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name ="localbody_code")
	private Integer localBodyCode;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name ="localbody_type")
	private Integer localBodyTypeCode;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name="localbodytype_name")
	private String localBodyTypeName;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name="localbody_name")
	private String localBodyNameEnglish;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name ="village_code")
	private Integer  villageCode;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name ="village_name")
	private String villageNameEnglish;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
