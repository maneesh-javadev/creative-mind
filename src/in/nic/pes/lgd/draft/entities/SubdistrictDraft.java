package in.nic.pes.lgd.draft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="subdistrict_draft") 
public class SubdistrictDraft {

	
	@Id
	@Column(name="draft_code")
	@SequenceGenerator(name = "draft_code_seq_generator", initialValue=1, allocationSize=1,  sequenceName = "subdistrict_draft_draft_code_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "draft_code_seq_generator")
	private Integer draftCode;
	
	@Column(name="district_code")
	private Integer districtCode;
	
	@Column(name="subdistrict_name_english")
	private String subdistrictNameEnglish;
	
	@Column(name="change_subdistrict_name_english")
	private String changeSubdistrictNameEnglish;
	
	@Column(name="subdistrict_name_local")
	private String subdistrictNameLocal;
	
	@Column(name="alias_english")
	private String aliasEnglish;
	
	@Column(name="alias_local")
	private String aliasLocal;
		
	@Column(name="census_2011_code")
	private String census2011Code;
	
	@Column(name="sscode")
	private String sscode;
	
	@Column(name="coordinates")
	private String coordinates;
	
	@Column(name="headquarter_name_english")
	private String headquarterNameEnglish;
	
	@Column(name="headquarter_local_name")
	private String headquarterLocalName;
	
	@Column(name="list_of_subdistrict_part")
	private String listofSubdistrictPart;
	
	@Column(name="list_of_subdistrict_full")
	private String listofSubdistrictFull;
	
	@Column(name="list_of_village_full")
	private String listofVillageFull;
	
	@Column(name="map_attachment_code")
	private Integer mapAttachmentCode;
	
	@Column(name="opeartion_flag")
	private String opeartionFlag;
	
		
	@Column(name="group_id")
	private Integer groupId;
	
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="subdistrict_code")
	private Integer subdistrictCode;
	
	
	@Transient
	private boolean multipleFlag;
	
	@Transient
	private String draftCodeText;

	public Integer getDraftCode() {
		return draftCode;
	}

	public void setDraftCode(Integer draftCode) {
		this.draftCode = draftCode;
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}

	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}

	public String getSubdistrictNameLocal() {
		return subdistrictNameLocal;
	}

	public void setSubdistrictNameLocal(String subdistrictNameLocal) {
		this.subdistrictNameLocal = subdistrictNameLocal;
	}

	public String getAliasEnglish() {
		return aliasEnglish;
	}

	public void setAliasEnglish(String aliasEnglish) {
		this.aliasEnglish = aliasEnglish;
	}

	public String getAliasLocal() {
		return aliasLocal;
	}

	public void setAliasLocal(String aliasLocal) {
		this.aliasLocal = aliasLocal;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}

	public String getSscode() {
		return sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getHeadquarterNameEnglish() {
		return headquarterNameEnglish;
	}

	public void setHeadquarterNameEnglish(String headquarterNameEnglish) {
		this.headquarterNameEnglish = headquarterNameEnglish;
	}

	public String getHeadquarterLocalName() {
		return headquarterLocalName;
	}

	public void setHeadquarterLocalName(String headquarterLocalName) {
		this.headquarterLocalName = headquarterLocalName;
	}

	public String getListofSubdistrictPart() {
		return listofSubdistrictPart;
	}

	public void setListofSubdistrictPart(String listofSubdistrictPart) {
		this.listofSubdistrictPart = listofSubdistrictPart;
	}

	public String getListofSubdistrictFull() {
		return listofSubdistrictFull;
	}

	public void setListofSubdistrictFull(String listofSubdistrictFull) {
		this.listofSubdistrictFull = listofSubdistrictFull;
	}

	

	public String getListofVillageFull() {
		return listofVillageFull;
	}

	public void setListofVillageFull(String listofVillageFull) {
		this.listofVillageFull = listofVillageFull;
	}

	public Integer getMapAttachmentCode() {
		return mapAttachmentCode;
	}

	public void setMapAttachmentCode(Integer mapAttachmentCode) {
		this.mapAttachmentCode = mapAttachmentCode;
	}

	

	public String getChangeSubdistrictNameEnglish() {
		return changeSubdistrictNameEnglish;
	}

	public void setChangeSubdistrictNameEnglish(String changeSubdistrictNameEnglish) {
		this.changeSubdistrictNameEnglish = changeSubdistrictNameEnglish;
	}

	public String getOpeartionFlag() {
		return opeartionFlag;
	}

	public void setOpeartionFlag(String opeartionFlag) {
		this.opeartionFlag = opeartionFlag;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getSubdistrictCode() {
		return subdistrictCode;
	}

	public void setSubdistrictCode(Integer subdistrictCode) {
		this.subdistrictCode = subdistrictCode;
	}
	
	
	public boolean isMultipleFlag() {
		return multipleFlag;
	}

	public void setMultipleFlag(boolean multipleFlag) {
		this.multipleFlag = multipleFlag;
	}
	
	
	public String getDraftCodeText() {
		return draftCodeText;
	}

	public void setDraftCodeText(String draftCodeText) {
		this.draftCodeText = draftCodeText;
	}
	
	
	
	
}
