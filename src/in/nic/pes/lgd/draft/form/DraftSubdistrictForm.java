package in.nic.pes.lgd.draft.form;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;

public class DraftSubdistrictForm {
	
	private Integer draftCode;
	
	private Integer subDistrictCode;
	
	private Integer districtCode;
	
	private Integer selectDistrictCode;
	
	private Integer userId;
	
	private String subdistrictNameEnglish;
	
	private String subdistrictNameLocal;
	
	private String aliasEnglish;
	
	private String aliasLocal;
	
	private String census2011Code;
	
	private String sscode;
	
	private String coordinates;
	
	private String headquarterNameEnglish;
	
	private String headquarterNameLocal;
	
	
	private String listofSubdistrictFull;
	
	private String listofSubdistrictPart;
	
	private String listofVillageFull;
	
	private String listofAllSubdistrictFull;
	
	private String listofAllSubdistrictPart;
	
	private String listofAllVillageFull;
	
	private String listofSubdistrictNameEng;
	

	
	
	private String mapLandregionCode;
	
	private String entityType;
	
	private Integer entityCode;
	
	private String longitude;

	private String latitude;
	
	private String mapUploadPath;
	
	

	
	private List<CommonsMultipartFile> mapUpload;
	
	private String contibutingSubdistrict;
	
	private String contibutingVillage;
	
	private Integer operationCode;
	
	private String formAction;
	
	private Integer stateCode;
	
	private Integer paramCode;
	
	private Integer groupId;
	
	List<Subdistrict> draftSubDistrictList;
	
	List<Subdistrict> contDraftSubDistrictList;
	
	List<Village> draftVillageList;
	
	List<Village> contdraftVillageList;
	
	private String opeartionFlag;
	
	private Boolean deleteFlag;
	
	private String invalidateSubdistrictcodes;

	public Integer getDraftCode() {
		return draftCode;
	}

	public void setDraftCode(Integer draftCode) {
		this.draftCode = draftCode;
	}

	public Integer getSubDistrictCode() {
		return subDistrictCode;
	}

	public void setSubDistrictCode(Integer subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getSelectDistrictCode() {
		return selectDistrictCode;
	}

	public void setSelectDistrictCode(Integer selectDistrictCode) {
		this.selectDistrictCode = selectDistrictCode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getHeadquarterNameLocal() {
		return headquarterNameLocal;
	}

	public void setHeadquarterNameLocal(String headquarterNameLocal) {
		this.headquarterNameLocal = headquarterNameLocal;
	}

	public String getListofSubdistrictFull() {
		return listofSubdistrictFull;
	}

	public void setListofSubdistrictFull(String listofSubdistrictFull) {
		this.listofSubdistrictFull = listofSubdistrictFull;
	}

	public String getListofSubdistrictPart() {
		return listofSubdistrictPart;
	}

	public void setListofSubdistrictPart(String listofSubdistrictPart) {
		this.listofSubdistrictPart = listofSubdistrictPart;
	}

	public String getListofVillageFull() {
		return listofVillageFull;
	}

	public void setListofVillageFull(String listofVillageFull) {
		this.listofVillageFull = listofVillageFull;
	}

	public String getMapLandregionCode() {
		return mapLandregionCode;
	}

	public void setMapLandregionCode(String mapLandregionCode) {
		this.mapLandregionCode = mapLandregionCode;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getMapUploadPath() {
		return mapUploadPath;
	}

	public void setMapUploadPath(String mapUploadPath) {
		this.mapUploadPath = mapUploadPath;
	}

	public List<CommonsMultipartFile> getMapUpload() {
		return mapUpload;
	}

	public void setMapUpload(List<CommonsMultipartFile> mapUpload) {
		this.mapUpload = mapUpload;
	}

	public String getContibutingSubdistrict() {
		return contibutingSubdistrict;
	}

	public void setContibutingSubdistrict(String contibutingSubdistrict) {
		this.contibutingSubdistrict = contibutingSubdistrict;
	}

	public String getContibutingVillage() {
		return contibutingVillage;
	}

	public void setContibutingVillage(String contibutingVillage) {
		this.contibutingVillage = contibutingVillage;
	}

	public Integer getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}

	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	public String getListofSubdistrictNameEng() {
		return listofSubdistrictNameEng;
	}

	public void setListofSubdistrictNameEng(String listofSubdistrictNameEng) {
		this.listofSubdistrictNameEng = listofSubdistrictNameEng;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getParamCode() {
		return paramCode;
	}

	public void setParamCode(Integer paramCode) {
		this.paramCode = paramCode;
	}

	public List<Subdistrict> getDraftSubDistrictList() {
		return draftSubDistrictList;
	}

	public void setDraftSubDistrictList(List<Subdistrict> draftSubDistrictList) {
		this.draftSubDistrictList = draftSubDistrictList;
	}

	

	public List<Subdistrict> getContDraftSubDistrictList() {
		return contDraftSubDistrictList;
	}

	public void setContDraftSubDistrictList(List<Subdistrict> contDraftSubDistrictList) {
		this.contDraftSubDistrictList = contDraftSubDistrictList;
	}

	public List<Village> getDraftVillageList() {
		return draftVillageList;
	}

	public void setDraftVillageList(List<Village> draftVillageList) {
		this.draftVillageList = draftVillageList;
	}

	public List<Village> getContdraftVillageList() {
		return contdraftVillageList;
	}

	public void setContdraftVillageList(List<Village> contdraftVillageList) {
		this.contdraftVillageList = contdraftVillageList;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getListofAllSubdistrictFull() {
		return listofAllSubdistrictFull;
	}

	public void setListofAllSubdistrictFull(String listofAllSubdistrictFull) {
		this.listofAllSubdistrictFull = listofAllSubdistrictFull;
	}

	public String getListofAllSubdistrictPart() {
		return listofAllSubdistrictPart;
	}

	public void setListofAllSubdistrictPart(String listofAllSubdistrictPart) {
		this.listofAllSubdistrictPart = listofAllSubdistrictPart;
	}

	public String getListofAllVillageFull() {
		return listofAllVillageFull;
	}

	public void setListofAllVillageFull(String listofAllVillageFull) {
		this.listofAllVillageFull = listofAllVillageFull;
	}

	public String getOpeartionFlag() {
		return opeartionFlag;
	}

	public void setOpeartionFlag(String opeartionFlag) {
		this.opeartionFlag = opeartionFlag;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getInvalidateSubdistrictcodes() {
		return invalidateSubdistrictcodes;
	}

	public void setInvalidateSubdistrictcodes(String invalidateSubdistrictcodes) {
		this.invalidateSubdistrictcodes = invalidateSubdistrictcodes;
	}



	
	

	
	

}
