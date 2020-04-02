package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(query = "select row_number() over() as count, * from mapped_lbward_topcac_districtwise(:districtCode)", name = "MAPPED_LBWARD_TOPAC_DISTRICTWISE", resultClass = MappedLbWardDisttrictWise.class) })

public class MappedLbWardDisttrictWise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	@Column(name = "count")
	private int count;
	
	@Column(name = "parliament_constituency")
	private String parliamentConstituency;

	@Column(name = "assembly_constituency")
	private String assemblyConstituency;
	
	@Column(name = "District_code")
	private Integer districtCode;

	@Column(name = "District_name_english")
	private String districtNameEnglish;

	@Column(name = "subdistrict_code")
	private Integer subdistrictCode;

	@Column(name = "subdistrict_name_english")
	private String subdistrictNameEnglish;

	@Column(name = "village_code")
	private Integer villageCode;

	@Column(name = "village_name_english")
	private String villageNameEnglish;

	@Column(name = "local_body_code")
	private Integer localBodyCode;

	@Column(name = "local_body_name_english")
	private String localBodyNameEnglish;

	@Column(name = "local_body_type_name")
	private String localBodyTypeName;

	@Column(name = "ward_code")
	private Integer wardCode;

	@Column(name = "ward_name_english")
	private String wardNameEnglish;

	@Column(name = "block_code")
	private Integer blockCode;

	@Column(name = "block_name_english")
	private String blockNameEnglish;

	public String getParliamentConstituency() {
		return parliamentConstituency;
	}

	public void setParliamentConstituency(String parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}

	public String getAssemblyConstituency() {
		return assemblyConstituency;
	}

	public void setAssemblyConstituency(String assemblyConstituency) {
		this.assemblyConstituency = assemblyConstituency;
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

	public Integer getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(Integer subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
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

	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	public Integer getWardCode() {
		return wardCode;
	}

	public void setWardCode(Integer wardCode) {
		this.wardCode = wardCode;
	}

	public String getWardNameEnglish() {
		return wardNameEnglish;
	}

	public void setWardNameEnglish(String wardNameEnglish) {
		this.wardNameEnglish = wardNameEnglish;
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

}
