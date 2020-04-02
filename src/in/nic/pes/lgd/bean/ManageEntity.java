package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
/*@NamedNativeQueries({
	query modified by sunita on 28-june-2015
@NamedNativeQuery(query="Select n,* from get_statewise_entity_details_for_manage(:statecode,:entitytype,:villageCode,:villageName,:limit,:offset)",name="manageEntity",resultClass=ManageEntity.class),
@NamedNativeQuery(query="SELECT * from get_statewise_entity_details_for_globalview(:statecode,:entitytype,:villageCode,:villageName,:limit,:offset)",name="globalViewEntity",resultClass=ManageEntity.class)
})*/
public class ManageEntity {
	private int villageCode;
	private String villageNameEnglish;
	private String villageNameLocal;
	private String census2011Code;
	private String census2001Code;
	private Boolean mapupload;
	private String isPesa;
	private String fileLocation;
	private String fileName;
	private String villageNameShort;
	
	
	
	
	@Id
	@Column(name="entity_code")
	public int getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
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
	
	@Column(name="entity_name_short")
	public String getVillageNameShort() {
		return villageNameShort;
	}
	public void setVillageNameShort(String villageNameShort) {
		this.villageNameShort = villageNameShort;
	}
	
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
