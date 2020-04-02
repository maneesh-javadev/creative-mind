package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.ConfigurationMapConstituency;
import in.nic.pes.lgd.bean.ConfigurationMapLandregion;
import in.nic.pes.lgd.bean.ConfigurationMapLandregionLevel;
import in.nic.pes.lgd.bean.ConfigurationMapLocalbody;
import in.nic.pes.lgd.bean.GetMapConfigLocalBody;
import in.nic.pes.lgd.bean.ViewConfigMapLandRegion;

import java.util.ArrayList;
import java.util.List;

public class ConfigureMapForm {

	private Integer code;

	private boolean ismapuploadState;
	private String baseUrlState;
	private String base_url;

	private boolean ismapuploadDistrict;
	private String baseUrlDistrict;

	private boolean ismapuploadSubDist;
	private String baseUrlSubDist;

	private boolean ismapuploadVillage;
	private String baseUrlVillage;

	private boolean ismapupload;
	private String baseUrl;

	private boolean ismapuploadParliament;
	private String baseUrlParliament;

	private boolean ismapuploadAssembly;
	private String baseUrlAssembly;

	private boolean ismapuploadBlock;
	private String baseUrlBlock;
	private String isbaseUrl;

	private char landregionTypeDistrict;
	private char landregionTypeSubDist;
	private char landregionTypeVillage;
	private char landregionTypeBlock;

	private char constituencyTypeParliament;
	private char constituencyTypeAssembly;

	private int parentTypeCode;
	private char type;
	private String nomenEnglish;
	private String nomenLocal;
	private char category;

	// private String[] radioLGDM;

	public char getCategory() {
		return category;
	}

	public void setCategory(char category) {
		this.category = category;
	}

	private int tierSetupSize;

	private String tierSetupCode;

	private List<GetMapConfigLocalBody> lstdetail = new ArrayList<GetMapConfigLocalBody>();
	private List<GetMapConfigLocalBody> viewmaplstdetail = new ArrayList<GetMapConfigLocalBody>();
	private List<ConfigurationMapLocalbody> getLocalGovtSetupList = new ArrayList<ConfigurationMapLocalbody>();// cofigmap
																										// local
																										// body
	private List<ConfigurationMapLandregion> lrmStateData = new ArrayList<ConfigurationMapLandregion>();
	private List<ConfigurationMapLandregionLevel> lrmLandData = new ArrayList<ConfigurationMapLandregionLevel>();
	private List<ViewConfigMapLandRegion> viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
	private List<ConfigurationMapConstituency> listConfigurationMapConstituency = new ArrayList<ConfigurationMapConstituency>();
	private List<ConfigureMapForm> formlist = new ArrayList<ConfigureMapForm>();

	public boolean isIsmapuploadState() {
		return ismapuploadState;
	}

	public void setIsmapuploadState(boolean ismapuploadState) {
		this.ismapuploadState = ismapuploadState;
	}

	public String getBaseUrlState() {
		return baseUrlState;
	}

	public void setBaseUrlState(String baseUrlState) {
		this.baseUrlState = baseUrlState;
	}

	public boolean isIsmapuploadDistrict() {
		return ismapuploadDistrict;
	}

	public void setIsmapuploadDistrict(boolean ismapuploadDistrict) {
		this.ismapuploadDistrict = ismapuploadDistrict;
	}

	public String getBaseUrlDistrict() {
		return baseUrlDistrict;
	}

	public void setBaseUrlDistrict(String baseUrlDistrict) {
		this.baseUrlDistrict = baseUrlDistrict;
	}

	public boolean isIsmapuploadSubDist() {
		return ismapuploadSubDist;
	}

	public void setIsmapuploadSubDist(boolean ismapuploadSubDist) {
		this.ismapuploadSubDist = ismapuploadSubDist;
	}

	public String getBaseUrlSubDist() {
		return baseUrlSubDist;
	}

	public void setBaseUrlSubDist(String baseUrlSubDist) {
		this.baseUrlSubDist = baseUrlSubDist;
	}

	public boolean isIsmapuploadVillage() {
		return ismapuploadVillage;
	}

	public void setIsmapuploadVillage(boolean ismapuploadVillage) {
		this.ismapuploadVillage = ismapuploadVillage;
	}

	public String getBaseUrlVillage() {
		return baseUrlVillage;
	}

	public void setBaseUrlVillage(String baseUrlVillage) {
		this.baseUrlVillage = baseUrlVillage;
	}

	public boolean isIsmapupload() {
		return ismapupload;
	}

	public void setIsmapupload(boolean ismapupload) {
		this.ismapupload = ismapupload;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public char getLandregionTypeDistrict() {
		return landregionTypeDistrict;
	}

	public void setLandregionTypeDistrict(char landregionTypeDistrict) {
		this.landregionTypeDistrict = landregionTypeDistrict;
	}

	public char getLandregionTypeSubDist() {
		return landregionTypeSubDist;
	}

	public void setLandregionTypeSubDist(char landregionTypeSubDist) {
		this.landregionTypeSubDist = landregionTypeSubDist;
	}

	public char getLandregionTypeVillage() {
		return landregionTypeVillage;
	}

	public void setLandregionTypeVillage(char landregionTypeVillage) {
		this.landregionTypeVillage = landregionTypeVillage;
	}

	public char getLandregionTypeBlock() {
		return landregionTypeBlock;
	}

	public void setLandregionTypeBlock(char landregionTypeBlock) {
		this.landregionTypeBlock = landregionTypeBlock;
	}

	public char getConstituencyTypeParliament() {
		return constituencyTypeParliament;
	}

	public void setConstituencyTypeParliament(char constituencyTypeParliament) {
		this.constituencyTypeParliament = constituencyTypeParliament;
	}

	public char getConstituencyTypeAssembly() {
		return constituencyTypeAssembly;
	}

	public void setConstituencyTypeAssembly(char constituencyTypeAssembly) {
		this.constituencyTypeAssembly = constituencyTypeAssembly;
	}

	public boolean isIsmapuploadParliament() {
		return ismapuploadParliament;
	}

	public void setIsmapuploadParliament(boolean ismapuploadParliament) {
		this.ismapuploadParliament = ismapuploadParliament;
	}

	public String getBaseUrlParliament() {
		return baseUrlParliament;
	}

	public void setBaseUrlParliament(String baseUrlParliament) {
		this.baseUrlParliament = baseUrlParliament;
	}

	public boolean isIsmapuploadAssembly() {
		return ismapuploadAssembly;
	}

	public void setIsmapuploadAssembly(boolean ismapuploadAssembly) {
		this.ismapuploadAssembly = ismapuploadAssembly;
	}

	public String getBaseUrlAssembly() {
		return baseUrlAssembly;
	}

	public void setBaseUrlAssembly(String baseUrlAssembly) {
		this.baseUrlAssembly = baseUrlAssembly;
	}

	public boolean isIsmapuploadBlock() {
		return ismapuploadBlock;
	}

	public void setIsmapuploadBlock(boolean ismapuploadBlock) {
		this.ismapuploadBlock = ismapuploadBlock;
	}

	public String getBaseUrlBlock() {
		return baseUrlBlock;
	}

	public void setBaseUrlBlock(String baseUrlBlock) {
		this.baseUrlBlock = baseUrlBlock;
	}

	public List<ConfigurationMapLandregion> getLrmStateData() {
		return lrmStateData;
	}

	public void setLrmStateData(List<ConfigurationMapLandregion> lrmStateData) {
		this.lrmStateData = lrmStateData;
	}

	public List<ConfigurationMapLandregionLevel> getLrmLandData() {
		return lrmLandData;
	}

	public void setLrmLandData(List<ConfigurationMapLandregionLevel> lrmLandData) {
		this.lrmLandData = lrmLandData;
	}

	public List<ConfigurationMapConstituency> getListConfigurationMapConstituency() {
		return listConfigurationMapConstituency;
	}

	public void setListConfigurationMapConstituency(
			List<ConfigurationMapConstituency> listConfigurationMapConstituency) {
		this.listConfigurationMapConstituency = listConfigurationMapConstituency;
	}

	public List<ViewConfigMapLandRegion> getViewConfigMapLandRegion() {
		return viewConfigMapLandRegion;
	}

	public void setViewConfigMapLandRegion(
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion) {
		this.viewConfigMapLandRegion = viewConfigMapLandRegion;
	}

	public List<ConfigureMapForm> getFormlist() {
		return formlist;
	}

	public void setFormlist(List<ConfigureMapForm> formlist) {
		this.formlist = formlist;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public int getParentTypeCode() {
		return parentTypeCode;
	}

	public void setParentTypeCode(int parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getNomenEnglish() {
		return nomenEnglish;
	}

	public void setNomenEnglish(String nomenEnglish) {
		this.nomenEnglish = nomenEnglish;
	}

	public String getNomenLocal() {
		return nomenLocal;
	}

	public void setNomenLocal(String nomenLocal) {
		this.nomenLocal = nomenLocal;
	}

	public List<ConfigurationMapLocalbody> getGetLocalGovtSetupList() {
		return getLocalGovtSetupList;
	}

	public void setGetLocalGovtSetupList(
			List<ConfigurationMapLocalbody> getLocalGovtSetupList) {
		this.getLocalGovtSetupList = getLocalGovtSetupList;
	}

	public int getTierSetupSize() {
		return tierSetupSize;
	}

	public void setTierSetupSize(int tierSetupSize) {
		this.tierSetupSize = tierSetupSize;
	}

	public String getTierSetupCode() {
		return tierSetupCode;
	}

	public void setTierSetupCode(String tierSetupCode) {
		this.tierSetupCode = tierSetupCode;
	}

	public List<GetMapConfigLocalBody> getViewmaplstdetail() {
		return viewmaplstdetail;
	}

	public void setViewmaplstdetail(List<GetMapConfigLocalBody> viewmaplstdetail) {
		this.viewmaplstdetail = viewmaplstdetail;
	}

	public List<GetMapConfigLocalBody> getLstdetail() {
		return lstdetail;
	}

	public void setLstdetail(List<GetMapConfigLocalBody> lstdetail) {
		this.lstdetail = lstdetail;
	}

	public String getBase_url() {
		return base_url;
	}

	public void setBase_url(String base_url) {
		this.base_url = base_url;
	}

	public String getIsbaseUrl() {
		return isbaseUrl;
	}

	public void setIsbaseUrl(String isbaseUrl) {
		this.isbaseUrl = isbaseUrl;
	}

}