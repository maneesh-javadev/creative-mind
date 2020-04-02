package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity

@NamedNativeQueries({
@NamedNativeQuery(query="select *,null as gis_map_status FROM get_local_gov_setup_wise_localbody_list_fn(:stateCode,:localBodyType,:parentLblc,:localBodyCode,:localBodyName) a left join public.localbody_gismap_status b on a.local_body_code=b.local_body_code order by a.local_body_name_english",name="getLocalBodylistStateCategoryWiseFinal",resultClass=LocalbodyforStateWiseFinal.class),
@NamedNativeQuery(query="select case when lb.local_body_code in (select local_body_code from localbody_gismap_status where local_body_code=lb.local_body_code and (ismapfinalized )) then 'A' else 'D' end as gis_map_status,  * FROM get_local_gov_setup_wise_localbody_list_fn_RPT(:stateCode,:localBodyType,:parentLblc,:localBodyCode,:localBodyName)lb order by local_body_name_english",name="viewLocalbodyGisStatus",resultClass=LocalbodyforStateWiseFinal.class),

})

public class LocalbodyforStateWiseFinal implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 188691630159706950L;
	private int localBodyTypeCode;
	private String nomenclatureEnglish; 
	private String localBodyTypeName;
	private String nomenclatureLocal; 
	private String category;
	private String level;
	private String localBodyNameEng;
	private int localBodyCode;
	private String census2011Code;
	private String census2001Code;
	private String fileLocation;
	private String fileName;
	private String gisMapStatus;
	
	@Column(name="local_body_name_english")
	public String getLocalBodyNameEng() {
		return localBodyNameEng;
	}
	public void setLocalBodyNameEng(String localBodyNameEng) {
		this.localBodyNameEng = localBodyNameEng;
	}
	private int parentLocalBodyTypeCode;
	
	
	@Column(name="local_body_type_code")
	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	
	@Column(name="local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	@Column(name="nomenclature_english")
	public String getNomenclatureEnglish() {
		return nomenclatureEnglish;
	}
	public void setNomenclatureEnglish(String nomenclatureEnglish) {
		this.nomenclatureEnglish = nomenclatureEnglish;
	}
	
	@Column(name="nomenclature_local")
	public String getNomenclatureLocal() {
		return nomenclatureLocal;
	}
	public void setNomenclatureLocal(String nomenclatureLocal) {
		this.nomenclatureLocal = nomenclatureLocal;
	}
	
	@Column(name="category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name="level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name="parent_tier_setup_code")
	public int getParentLocalBodyTypeCode() {
		return parentLocalBodyTypeCode;
	}
	public void setParentLocalBodyTypeCode(int parentLocalBodyTypeCode) {
		this.parentLocalBodyTypeCode = parentLocalBodyTypeCode;
	}
	@Id
	@Column(name="local_body_code")
	public int getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	@Column(name="census_2011_code")
	public String getCensus2011Code() {
		return census2011Code;
	}
	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
	@Column(name="census_2001_code")
	public String getCensus2001Code() {
		return census2001Code;
	}
	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
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
	
	@Column(name="gis_map_status")
	public String getGisMapStatus() {
		return gisMapStatus;
	}
	public void setGisMapStatus(String gisMapStatus) {
		this.gisMapStatus = gisMapStatus;
	}
}
