package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT * FROM get_statewise_entity_details_fn(:statecode,:entitytype,:villageCode,:villageName,:limit,:offset)",name="getStateWiseEntityDetails",resultClass=StateWiseEntityDetails.class),
@NamedNativeQuery(query=" SELECT * FROM get_parent_land_region_wise_entity_details_fn(:entitytype,:parentCode,:limit,:offset)",name="getParentWiseEntityDetails",resultClass=StateWiseEntityDetails.class)
})
public class StateWiseEntityDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer villageCode;
	private int villageVersion;
	private String villageNameEnglish;
	private String villageNameLocal;
	private String aliasEnglish;
	
/*	private Integer districtCode;
	private int districtVersion;
	private String districtNameEnglish;
	private String districtrNameLocal;
	private Integer subdistrictCode;
	private int subdistrictVersion;
	private String subdistrictNameEnglish;
	private String subdistrictNameLocal;
	private Integer blockCode;
	private int blockVersion;
	private String blockNameEnglish;
	private String blockNameLocal;*/
	
	private String census2011Code;
	private String census2001Code;
	private Boolean mapupload;
	private String isPesa;
	private String fileLocation;
	private String fileName;
	@Id
	@Column(name="entity_code")
	public Integer getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}
	@Column(name="entity_version")
	public int getVillageVersion() {
		return villageVersion;
	}
	public void setVillageVersion(int villageVersion) {
		this.villageVersion = villageVersion;
	}
	@Column(name="entity_name_english")
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}
	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}
	@Column(name="entity_name_local")
	public String getVillageNameLocal() {
		return villageNameLocal;
	}
	public void setVillageNameLocal(String villageNameLocal) {
		this.villageNameLocal = villageNameLocal;
	}
	@Column(name="alias_english")
	public String getAliasEnglish() {
		return aliasEnglish;
	}
	public void setAliasEnglish(String aliasEnglish) {
		this.aliasEnglish = aliasEnglish;
	}
/*	@Column(name="entity_code")
	public Integer getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}
	@Column(name="entity_version")
	public int getDistrictVersion() {
		return districtVersion;
	}
	public void setDistrictVersion(int districtVersion) {
		this.districtVersion = districtVersion;
	}
	@Column(name="entity_name_english")
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	@Column(name="entity_name_local")
	public String getDistrictrNameLocal() {
		return districtrNameLocal;
	}
	public void setDistrictrNameLocal(String districtrNameLocal) {
		this.districtrNameLocal = districtrNameLocal;
	}
	@Column(name="entity_code")
	public Integer getSubdistrictCode() {
		return subdistrictCode;
	}
	public void setSubdistrictCode(Integer subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}
	@Column(name="entity_version")
	public int getSubdistrictVersion() {
		return subdistrictVersion;
	}
	public void setSubdistrictVersion(int subdistrictVersion) {
		this.subdistrictVersion = subdistrictVersion;
	}
	@Column(name="entity_name_english")
	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}
	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}
	@Column(name="entity_name_local")
	public String getSubdistrictNameLocal() {
		return subdistrictNameLocal;
	}
	public void setSubdistrictNameLocal(String subdistrictNameLocal) {
		this.subdistrictNameLocal = subdistrictNameLocal;
	}
	@Column(name="entity_code")
	public Integer getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(Integer blockCode) {
		this.blockCode = blockCode;
	}
	@Column(name="entity_version")
	public int getBlockVersion() {
		return blockVersion;
	}
	public void setBlockVersion(int blockVersion) {
		this.blockVersion = blockVersion;
	}
	@Column(name="entity_name_english")
	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}
	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}
	@Column(name="entity_name_local")
	public String getBlockNameLocal() {
		return blockNameLocal;
	}
	public void setBlockNameLocal(String blockNameLocal) {
		this.blockNameLocal = blockNameLocal;
	}*/
	
	@Column(name="census_2011_code")
	public String getCensus2011Code() {
		return census2011Code;
	}
	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
	
	@Column(name="ismapupload")
	public Boolean getMapupload() {
		return mapupload;
	}
	public void setMapupload(Boolean mapupload) {
		this.mapupload = mapupload;
	}
	
	@Column(name="census_2001_code")
	public String getCensus2001Code() {
		return census2001Code;
	}
	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}
	
	@Column(name="is_pesa")
	public String getIsPesa() {
		return isPesa;
	}
	public void setIsPesa(String isPesa) {
		this.isPesa = isPesa;
	}
	
	@Column(name="filelocation")
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	@Column(name="file_timestamp")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
