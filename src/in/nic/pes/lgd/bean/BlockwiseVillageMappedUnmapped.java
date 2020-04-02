package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT * FROM get_blockwise_mapped_unmaaped_village_list(:blockCode,:listType)",name="getBlockwiseVillageMappedUnmapped",resultClass=BlockwiseVillageMappedUnmapped.class)
public class BlockwiseVillageMappedUnmapped {

	@Id
	@Column(name="villageCode")
	private Integer villageCode;
	
	@Column(name="villageVersion")
	private Integer villageVersion;
	
	@Column(name="villageNameEnglish")
	private String villageNameEnglish;
	
	@Column(name="subdistrictNameEnglish")
	private String subdistrictNameEnglish;
	
	@Column(name="mappedVillageGramPanchayatName")
	private String mappedVillageGramPanchayatName;
	
	@Column(name="operation_state")
	private Character operation_state;
	
	
	@Column(name="coverage_type")
	private String coverageType;
	
	public Integer getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}

	public Integer getVillageVersion() {
		return villageVersion;
	}

	public void setVillageVersion(Integer villageVersion) {
		this.villageVersion = villageVersion;
	}

	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}

	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	public String getMappedVillageGramPanchayatName() {
		return mappedVillageGramPanchayatName;
	}

	public void setMappedVillageGramPanchayatName(String mappedVillageGramPanchayatName) {
		this.mappedVillageGramPanchayatName = mappedVillageGramPanchayatName;
	}

	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}

	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	
	
}
