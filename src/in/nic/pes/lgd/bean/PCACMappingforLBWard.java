package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQuery(query="SELECT row_number() OVER () as rid, * from Mapped_LBWard_toPCAC(:entityCode,:entityType)",name="getPCACMappingforLBWardDetails",resultClass=PCACMappingforLBWard.class)


public class PCACMappingforLBWard {
	
	@Id
	@Column(name = "rid", nullable = false)
	private Integer rid;
	
	@Column(name = "parliament_constituency")
	private String parliamentConstituency;
	
	@Column(name = "assembly_constituency")
	private String assemblyConstituency;
	
	@Column(name = "local_body_code" ,nullable = false)
	private Integer localBodyCode;
	
	@Column(name = "local_body_name_english")
	private String localBodyNameEnglish;
	
/*	@Column(name = "local_body_type_name")
	private String localBodyTypeName;*/
	
	@Column(name = "ward_code")
	private Integer wardCode;
	
	@Column(name = "ward_name_english")
	private String wardNameEnglish;
	
/*	@Column(name = "coverage_type")
	private String coverageType;*/
	
	@Column(name="district_code")
	private String districtCode;
	
	@Column(name="District_name_english")
	private String districtNameEnglish;
	
	@Column(name="subdistrict_code")
	private String subdistrictCode;
	
	@Column(name="subdistrict_name_english")
	private String subdistrictNameEnglish;
	
	@Column(name="village_code")
	private String villageCode;
	
	@Column(name="village_name_english")
	private String villageNameEnglish;
	
	/*@Column(name="block_code")
	private String blockCode;*/
	
	/*@Column(name="block_name_english")
	private String blockNameEnglish;
	*/

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

/*public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
*/
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

/*public String getCoverageType() {
		return coverageType;
	}

public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}*/

public Integer getRid() {
	return rid;
}

public void setRid(Integer rid) {
	this.rid = rid;
}

public String getDistrictCode() {
	return districtCode;
}

public void setDistrictCode(String districtCode) {
	this.districtCode = districtCode;
}

public String getDistrictNameEnglish() {
	return districtNameEnglish;
}

public void setDistrictNameEnglish(String districtNameEnglish) {
	this.districtNameEnglish = districtNameEnglish;
}

public String getSubdistrictCode() {
	return subdistrictCode;
}

public void setSubdistrictCode(String subdistrictCode) {
	this.subdistrictCode = subdistrictCode;
}

public String getSubdistrictNameEnglish() {
	return subdistrictNameEnglish;
}

public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
	this.subdistrictNameEnglish = subdistrictNameEnglish;
}

public String getVillageCode() {
	return villageCode;
}

public void setVillageCode(String villageCode) {
	this.villageCode = villageCode;
}

public String getVillageNameEnglish() {
	return villageNameEnglish;
}

public void setVillageNameEnglish(String villageNameEnglish) {
	this.villageNameEnglish = villageNameEnglish;
}

/*public String getBlockCode() {
	return blockCode;
}

public void setBlockCode(String blockCode) {
	this.blockCode = blockCode;
}*/

/*public String getBlockNameEnglish() {
	return blockNameEnglish;
}

public void setBlockNameEnglish(String blockNameEnglish) {
	this.blockNameEnglish = blockNameEnglish;
}*/
	
}



