package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.LocalBodyDetails;
import in.nic.pes.lgd.bean.LocalBodyHistory;
import in.nic.pes.lgd.bean.LocalGovtBodyWard;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.ParentWiseLocalBodies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class LocalGovtBodyForm implements Serializable {
	private static final long serialVersionUID = -7853801497720991537L;
	private int operationCode;
	private String govtOrderConfig;
	private char lbType;
	private char lbtypeLevel;
	private String hiddenLevel;
	private String parentLB;
	private String hiddenLbType;
	private String parentLBCode;
	private List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
	private List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
	private List<LocalbodyListbyState> viewlocalbodyDetails = new ArrayList<LocalbodyListbyState>();
	private List<Localbody> localbodyDetails = new ArrayList<Localbody>();
	/* Modified by sushil on 24-11-2014 */
	private Integer localBodyCode;
	private String lgd_LBorderNo;
	private Date lgd_LBorderDate;
	private Integer slc;
	private Date lgd_LBeffectiveDate;
	private Date lgd_LBgazPubDate;
	private MultipartFile lgd_LBfilePath;

	private String lgd_LBNameInEn;
	private String lgd_LBNameInEnh;
	
	
	private String lgd_LBDistPDestListhiddenforHeadQuarter;
	private String lgd_LBNameInLocal;
	private String lgd_LBAliasInEn;
	private String lgd_LBAliasInLocal;
	private String lgd_LBstateSpecificCode;

	private String latitude;
	private String longitude;
	private String fileMapUpLoad;

	private String hdnDistrictPanchayat;
	private String hdnIntermediatePanchayat;
	private String hdnType;

	private String lgd_LBTypeName;
	private String lgd_LBDistrictAtInter;
	private String lgd_LBDistrictAtVillage;
	private String lgd_LBIntermediateAtVillage;
	private String lgd_LBPesaAct;

	private String lgd_LBNomenclatureDist;
	private String lgd_LBNomenclatureInter;
	private String lgd_LBNomenclatureVillage;
	private String lgd_LBNomenclatureUrban;

	private boolean lgd_LBExistCheck;
	private boolean lgd_LBUnmappedCheck;
	
	private boolean lgd_LBModNameCheckDisturb;
	private boolean lgd_LBModParentCheckDisturb;
	
	private String lgd_LBDistPSourceList;
	private String lgd_LBDistPDestList;

	private String lgd_LBDistCAreaSourceList;
	private String lgd_LBDistCAreaDestList;
	
	private String lgd_LBDistListAtInterCA;
	private String lgd_LBInterPDestList;
	private String lgd_LBInterPSourceList;
	private String lgd_LBInterCAreaDestList;
	private String lgd_LBInterCAreaSourceList;

	private String lgd_LBDistListAtVillageCA;
	private String lgd_LBInterListAtVillageCA;
	private String lgd_LBVillageAtVillageCA;
	private String lgd_LBVillageCAreaAtVillageCA;
	private String lgd_LBVillageDestAtVillageCA;
	private String lgd_LBVillageCAreaSourceL;
	private String lgd_LBVillageCAreaDestL;

	private String lgd_LBDistPUnMappedList;
	private String lgd_LBDistPUnMappedDestList;

	private String lgd_LBVillageSourceAtVillageCA;

	private String lgd_LBVillageDestLatICA;
	private String lgd_LBVillageSourceLatICA;

	private String lgd_LBSubDistrictSourceLatDCA;
	private String lgd_LBSubDistrictDestLatDCA;
	private String lgd_LBVillageSourceLatDCA;
	private String lgd_LBVillageDestLatDCA;

	// For Unmapped

	private String lgd_LBDistCAreaDestListUmapped;
	private String lgd_LBDistCAreaSourceListUmapped;
	private String lgd_LBSubDistrictSourceLatDCAUmapped;
	private String lgd_LBSubDistrictDestLatDCAUmapped;
	private String lgd_LBVillageDestLatDCAUmapped;
	private String lgd_LBVillageSourceLatDCAUmapped;

	private String lgd_LBInterCAreaSourceListUmapped;
	private String lgd_LBInterCAreaDestListUmapped;

	private String lgd_LBVillageCAreaDestLUnmapped;
	private String lgd_LBVillageCAreaSourceLUnmapped;
	private String lgd_LBVillageSourceLatICAUmapped;
	private String lgd_LBVillageDestLatICAUmapped;

	// View Variable Define Here
	private String localBodyNameEnglishListForSubDist;
	private String localBodyNameEnglishListSource;
	private List<LocalbodyListbyState> lgdLocalGovtBodyList = new ArrayList<LocalbodyListbyState>();
	private List<ParentWiseLocalBodies> localBodyViewChild = new ArrayList<ParentWiseLocalBodies>();
	private String districtPanchayatName;
	private String intermediatePanchayat;
	private String villagePanchayat;
	private String headQuarterName;
	private String headQuarterCode;
    private String lgd_hiddenLbcList;
	
	private String pageType;
	private int offset;
	private Integer limit;
	private int pageRows;
	private int pageNo;
    private int direction;
    private String newAddLocalBody;
 
	private String requiredTitle1;
	private String maxFileLimit1;
	private String[] fileTitle1;
	private transient List<CommonsMultipartFile> attachFile1;
	private transient List<CommonsMultipartFile> attachFile2;
	private String requiredTitle2;
	private String maxFileLimit2;
	private String[] fileTitle2;
	//Modify Local Body variable For Binding
	private String localBodyNameEnglish;
    private String localBodyNameLocal;
    private String aliasNameEnglish;
    private String alisNameLocal;
    private List<LocalbodyWard> listLBWard = new ArrayList<LocalbodyWard>();
    
    private Integer localbodySubtype;
    private Integer Map_attachment_code;
    private String coordinates;
    private Integer localBodyVersion;
    private Date effectiveDate;
    private Date createdon;
    private long createdby;
    private Date lastupdatedon;
    private long lastupdatedby;
    private String wardCcode;
    private String newlocalbodyParentNamehidden;
    
    private String lati;
    private String longi;
	private String availlgd_LBDistCAreaSourceListUmapped;
    private String availlgd_LBSubDistrictSourceLatDCAUmapped;
    private String availlgd_LBVillageSourceLatDCAUmapped;
    
    private String availlgd_LBDistCAreaSourceListUmappedHidden;
    private String availlgd_LBInterCAreaSourceListUmappedHidden;
    private String availlgd_LBVillageCAreaSourceLUnmappedHidden;
    private String availlgd_LBInterCAreaSourceListUmappedUrbanHidden;
	private boolean isdisturbed;
    private boolean isactive;
    private boolean warningflag;
    private Integer parentLocalBodyCode;
    private Integer parent_lblc;
    private Integer lblc;
    private int flagCode;
    
  //Tanuj
  	private String correctionRadio;
  	private Integer globallocalbodyId;
  	private String code;
  	private String captchaAnswer;
  	private String captchaAnswers;
  	private String noOrderRecord;
  	private String noAttachmentRecord;
  	private String noMapRecord;
  	private String localBodyName;
  	private List<Localbody> listLocalBodyDetail = new ArrayList<Localbody>();
  	private List<LocalGovtBodyDataForm> listLocalBodyDetails = new ArrayList<LocalGovtBodyDataForm>();
  	//Tanuj
  	
  	private String contDistrict;
  	private String contSubDistrict;
  	private String contVillage;
  	private String lgd_LBDistPDestListhidden;
  	private String lgd_LBInterSubDestListhidden;
  	private String lgd_LBInterVillageListhidden;
  	private String choosenlb;
  	private String listformat;
  	private boolean lboption;
  	private String oldlocalbodyTypeNamehidden;
  	private String newlocalbodyTypeNamehidden;

	private String requiredTitle;//Title Required Flag
	private String uniqueTitle;//Unique Title Required Flag
	private int allowedNoOfAttachment;//Allowed Number Of Attachment
	private long allowedTotalFileSize;//Allowed Total File Size
	private long allowedIndividualFileSize;//Allowed Individual File Size.
	private String allowedFileType;//Allowed File Type
	private String uploadLocation;//File Upload Location
	private String showTitle;//Entered Title Value
	private List<CommonsMultipartFile> attachedFile;//Attached File List
	private String localBodyNameEnglishList;
	private String localBodyNameEnglishListForUrban;
	private String localBodyTypeCode;
	private List<LocalGovtBodyWard> localGovtBodyWardList = new ArrayList<LocalGovtBodyWard>();
	// invalidate
	private String lgTypeName;
	// for modify Local Body
	private boolean lgd_LBchkModifyName;
	private boolean lgd_LBchkModifyType;
	private boolean lgd_LBchkModifyParent;
	private boolean lgd_LBchkModifyCoverage;
	private char lgd_LBlevelChk;
	private String lgd_LBCoveredName;
	private boolean lgd_LBExistingLbChk;
	private String lgd_LBNameInEnCovered;
	private String lgd_LBNameInEnDest;
	private String localGovtBodyNameEnglishDestDist;
	private String lgd_LBNameInEnDestForSubDist;
	private String lgd_LBNameInEnSubDistListSource;
	private String localGovtBodyNameEnglishSubDist;
	private String localBodyDistListforVillage;
	private String localBodyNameEnglishVillageList;
	private String localGovtBodyVillageList;
	private String localGovtBodyNameEnglishDestVillage;
	private String localBodyLandRegionArea;
	private String localBodyNameEnglishDestFinalPart;
	private String unmappedLocalBody;
	private String localBodyNameEnglishListUnMapped;
	private String localBodyNameEnglishDestUnmapped;
	private String localBodyNameEnglishVillageListSource;

	private List<LocalBodyDetails> localBodyDetails = new ArrayList<LocalBodyDetails>();

	private String lgd_UrbanLBSubdistrictLUmappedDest;
	private String lgd_UrbanLBSubdistrictLUmappedSource;
	private String lgd_UrbanLBDistUmappedDest;
	private String lgd_UrbanLBDistUmappedSource;
	private String lgd_UrbanLBDistExistDest;
	private String lgd_UrbanLBSubdistrictListSource;
	private String lgd_UrbanLBSubdistrictListDest;
	private String lgd_UrbanLBDistExistSource;
	private List<LocalbodyListbyState> lgd_LGdistrictPanchayatList;
	private String lgd_lbCategory;
	private String lgd_lbTypeCode;
	
	private String localBodyNameEnglishListSourceVillg;

	// Create Ward Varaibles
	
	private int ward_code;
	private String ward_number;
	private int ward_version;
	private String ward_Name;
	private String ward_Namecr;
	private String ward_NameLocal;
	private String lgd_LBSubDistrictList;
	private int map_code;
	private String lb_lgdLocalBodyType;
	

	private char ward_landRegiontype;
	

	private String lb_lgdPanchayatName;

	private boolean correction;
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date ordereffectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private MultipartFile filePath;

	private String rurallbTypeName;

	private String stateNameEnglish;
	private String districtsourcecode;
	private String districtNameEnglish;
	private String contributedParliament;

	private String mergeRLBtoTLB;
	private String declarenewTLB;
	private String traditionalLbTypeName;
	private String lgd_LBDistrictforExist;
	private String lgd_LBDistrictPatInterforExist;
	private String contributedAssembly;

	
	private String lgd_LBVillageSurveyDestLatDCA;
	private String lgd_LBVillageSurveySourceLatDCA;
	
	private String lgd_LBVillageSurveyDestLatICA;
	private String lgd_LBVillageSurveySourceLatICA;
	
	private String lgd_LBVillageSurveyDestLatVCA;
	private String lgd_LBVillageSurveySourceLatVCA;
	private String lgd_LBVillageCAreaSourceList;
	private String rdoSubdistrictDelete;
	
	 private String ddLgdLBDistPList;
	 private String ddStateParliamnetSource;
	 private String lgd_LBDistCAreaforintermediate;
	 private String lgd_LBintermedaitelist;
     private String ddLgdLBVillageDestLatDCA;
     
     private String lgd_LBDistCArinterList;
     private String lgd_LBDistCArea;
     
     private String lgd_LBInterPDestListforvillage;
     private String lgd_LBSubDistrictDestLatDCAforvillage;
     private String lgd_LBWardCArea;
     private String lgd_LBwardDestLatDCA;
     private String templateList;
     private String parentLocalbodyName;
     @Transient
     private String parentList;
     @Transient
     private String gtaList;
     
     private Integer userId;
     
     @Transient
     private String GtaInterPanch;
    
 	 private String lbTypeHierarchy;
 	 
 	private String paramLocalBodyTypeCode;
 	 
 	 private String localBodyLevelCodes;
     /*added by kirandeep on 05/08/2014*/
     
     @Transient
     private String listofwards;
     
     
     private String lbFullMap;
     
     private String lbPartMap;
     
     private String villageMap;
     
     private String wardMap;
     
     private String deleteMap;
     
     private Integer ccCodeMap;
     
     private Date iParamEffectiveDate;
     
     private List<CommonsMultipartFile> gazettePublicationUpload;
     
     private boolean isMandatory;
     
     private Integer lbTypeCode;
     
     private Integer minorVersion;
     
     private boolean unResolvedFlag; 
     
     public String getLbFullMap() {
		return lbFullMap;
	}

	public void setLbFullMap(String lbFullMap) {
		this.lbFullMap = lbFullMap;
	}

	public String getLbPartMap() {
		return lbPartMap;
	}

	public void setLbPartMap(String lbPartMap) {
		this.lbPartMap = lbPartMap;
	}

	public String getVillageMap() {
		return villageMap;
	}

	public void setVillageMap(String villageMap) {
		this.villageMap = villageMap;
	}

	public String getWardMap() {
		return wardMap;
	}

	public void setWardMap(String wardMap) {
		this.wardMap = wardMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getListofwards() {
		return listofwards;
	}

	public void setListofwards(String listofwards) {
		this.listofwards = listofwards;
	}

	public String getGtaInterPanch() {
		return GtaInterPanch;
	}

	public void setGtaInterPanch(String gtaInterPanch) {
		GtaInterPanch = gtaInterPanch;
	}

	public String getParentList() {
		return parentList;
	}

	public void setParentList(String parentList) {
		this.parentList = parentList;
	}

	public String getGtaList() {
		return gtaList;
	}

	public void setGtaList(String gtaList) {
		this.gtaList = gtaList;
	}

	
	
	public String getLgd_LBDistPDestListhiddenforHeadQuarter() {
		return lgd_LBDistPDestListhiddenforHeadQuarter;
	}

	public void setLgd_LBDistPDestListhiddenforHeadQuarter(
			String lgd_LBDistPDestListhiddenforHeadQuarter) {
		this.lgd_LBDistPDestListhiddenforHeadQuarter = lgd_LBDistPDestListhiddenforHeadQuarter;
	}

	private String lgd_ChCovType;

	public String getLgd_ChCovType() {
		return lgd_ChCovType;
	}

	public void setLgd_ChCovType(String lgd_ChCovType) {
		this.lgd_ChCovType = lgd_ChCovType;
	}
	
	private String hiddenavaillgd_LBMappedLocalbody;
	    
	public String getHiddenavaillgd_LBMappedLocalbody() {
		return hiddenavaillgd_LBMappedLocalbody;
	}

	public void setHiddenavaillgd_LBMappedLocalbody(
				String hiddenavaillgd_LBMappedLocalbody) {
		this.hiddenavaillgd_LBMappedLocalbody = hiddenavaillgd_LBMappedLocalbody;
	}
	
	private String landregiondetails;
	     
	public String getLandregiondetails() {
			return landregiondetails;
	}

	public void setLandregiondetails(String landregiondetails) {
			this.landregiondetails = landregiondetails;
	}


	public String getParentLBCode() {
		return parentLBCode;
	}

	public void setParentLBCode(String parentLBCode) {
		this.parentLBCode = parentLBCode;
	}

	public String getHiddenLbType() {
		return hiddenLbType;
	}

	public void setHiddenLbType(String hiddenLbType) {
		this.hiddenLbType = hiddenLbType;
	}

	public String getHiddenLevel() {
		return hiddenLevel;
	}

	public void setHiddenLevel(String hiddenLevel) {
		this.hiddenLevel = hiddenLevel;
	}

	public String getParentLB() {
		return parentLB;
	}

	public void setParentLB(String parentLB) {
		this.parentLB = parentLB;
	}

	//These two List Variables Are Using For ComboBox Validation
	
	public char getLbtypeLevel() {
		return lbtypeLevel;
	}

	public void setLbtypeLevel(char lbtypeLevel) {
		this.lbtypeLevel = lbtypeLevel;
	}

	
	public String getLgd_LBNameInEnh() {
		return lgd_LBNameInEnh;
	}

	public void setLgd_LBNameInEnh(String lgd_LBNameInEnh) {
		this.lgd_LBNameInEnh = lgd_LBNameInEnh;
	}


	

	public boolean isLgd_LBModNameCheckDisturb() {
		return lgd_LBModNameCheckDisturb;
	}

	public void setLgd_LBModNameCheckDisturb(boolean lgd_LBModNameCheckDisturb) {
		this.lgd_LBModNameCheckDisturb = lgd_LBModNameCheckDisturb;
	}

	public boolean isLgd_LBModParentCheckDisturb() {
		return lgd_LBModParentCheckDisturb;
	}

	public void setLgd_LBModParentCheckDisturb(boolean lgd_LBModParentCheckDisturb) {
		this.lgd_LBModParentCheckDisturb = lgd_LBModParentCheckDisturb;
	}



    
    public String getLati() {
		return lati;
	}

	public void setLati(String lati) {
		this.lati = lati;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	private String districtCode;
	private String subDistrictCode;
	private String villageCode;
    
    public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	private String lgd_LBTypeNamehidden;
    
    public String getLgd_LBTypeNamehidden() {
		return lgd_LBTypeNamehidden;
	}

	public void setLgd_LBTypeNamehidden(String lgd_LBTypeNamehidden) {
		this.lgd_LBTypeNamehidden = lgd_LBTypeNamehidden;
	}

	public String getNewlocalbodyParentNamehidden() {
		return newlocalbodyParentNamehidden;
	}

	public void setNewlocalbodyParentNamehidden(String newlocalbodyParentNamehidden) {
		this.newlocalbodyParentNamehidden = newlocalbodyParentNamehidden;
	}

	private String existingCovLanRegion;
    
    private String existingDistrict;
    private String existingSubDistrict;
    private String ExistingVillage;
    private String oldlocalbodyParentNamehidden;
    public String getOldlocalbodyParentNamehidden() {
		return oldlocalbodyParentNamehidden;
	}

	public void setOldlocalbodyParentNamehidden(String oldlocalbodyParentNamehidden) {
		this.oldlocalbodyParentNamehidden = oldlocalbodyParentNamehidden;
	}

	public String getFinalLandRegion() {
		return finalLandRegion;
	}

	public void setFinalLandRegion(String finalLandRegion) {
		this.finalLandRegion = finalLandRegion;
	}

	private String finalLandRegion;
    
    //Available Lbodies
    
    public String getExistingDistrict() {
		return existingDistrict;
	}

	public void setExistingDistrict(String existingDistrict) {
		this.existingDistrict = existingDistrict;
	}

	public String getExistingSubDistrict() {
		return existingSubDistrict;
	}

	public void setExistingSubDistrict(String existingSubDistrict) {
		this.existingSubDistrict = existingSubDistrict;
	}

	public String getExistingVillage() {
		return ExistingVillage;
	}

	public void setExistingVillage(String existingVillage) {
		ExistingVillage = existingVillage;
	}

	public String getExistingCovLanRegion() {
		return existingCovLanRegion;
	}

	public void setExistingCovLanRegion(String existingCovLanRegion) {
		this.existingCovLanRegion = existingCovLanRegion;
	}
	

    public String getAvaillgd_LBInterCAreaSourceListUmappedUrbanHidden() {
		return availlgd_LBInterCAreaSourceListUmappedUrbanHidden;
	}

	public void setAvaillgd_LBInterCAreaSourceListUmappedUrbanHidden(
			String availlgd_LBInterCAreaSourceListUmappedUrbanHidden) {
		this.availlgd_LBInterCAreaSourceListUmappedUrbanHidden = availlgd_LBInterCAreaSourceListUmappedUrbanHidden;
	}

	public String getAvaillgd_LBVillageCAreaSourceLUnmappedHidden() {
		return availlgd_LBVillageCAreaSourceLUnmappedHidden;
	}

	public void setAvaillgd_LBVillageCAreaSourceLUnmappedHidden(
			String availlgd_LBVillageCAreaSourceLUnmappedHidden) {
		this.availlgd_LBVillageCAreaSourceLUnmappedHidden = availlgd_LBVillageCAreaSourceLUnmappedHidden;
	}

	public String getAvaillgd_LBInterCAreaSourceListUmappedHidden() {
		return availlgd_LBInterCAreaSourceListUmappedHidden;
	}

	public void setAvaillgd_LBInterCAreaSourceListUmappedHidden(
			String availlgd_LBInterCAreaSourceListUmappedHidden) {
		this.availlgd_LBInterCAreaSourceListUmappedHidden = availlgd_LBInterCAreaSourceListUmappedHidden;
	}

	public String getAvaillgd_LBDistCAreaSourceListUmappedHidden() {
		return availlgd_LBDistCAreaSourceListUmappedHidden;
	}

	public void setAvaillgd_LBDistCAreaSourceListUmappedHidden(
			String availlgd_LBDistCAreaSourceListUmappedHidden) {
		this.availlgd_LBDistCAreaSourceListUmappedHidden = availlgd_LBDistCAreaSourceListUmappedHidden;
	}

	private String availlgd_LBInterCAreaSourceListUmapped;
    private String availlgd_LBVillageSourceLatICAUmapped;

    private String availlgd_LBVillageCAreaSourceLUnmapped;

    private String availlgd_LBInterCAreaSourceListUmappedUrban;
    
    
    public String getAvaillgd_LBDistCAreaSourceListUmapped() {
		return availlgd_LBDistCAreaSourceListUmapped;
	}

	public void setAvaillgd_LBDistCAreaSourceListUmapped(
			String availlgd_LBDistCAreaSourceListUmapped) {
		this.availlgd_LBDistCAreaSourceListUmapped = availlgd_LBDistCAreaSourceListUmapped;
	}

	public String getAvaillgd_LBSubDistrictSourceLatDCAUmapped() {
		return availlgd_LBSubDistrictSourceLatDCAUmapped;
	}

	public void setAvaillgd_LBSubDistrictSourceLatDCAUmapped(
			String availlgd_LBSubDistrictSourceLatDCAUmapped) {
		this.availlgd_LBSubDistrictSourceLatDCAUmapped = availlgd_LBSubDistrictSourceLatDCAUmapped;
	}

	public String getAvaillgd_LBVillageSourceLatDCAUmapped() {
		return availlgd_LBVillageSourceLatDCAUmapped;
	}

	public void setAvaillgd_LBVillageSourceLatDCAUmapped(
			String availlgd_LBVillageSourceLatDCAUmapped) {
		this.availlgd_LBVillageSourceLatDCAUmapped = availlgd_LBVillageSourceLatDCAUmapped;
	}

	public String getAvaillgd_LBInterCAreaSourceListUmapped() {
		return availlgd_LBInterCAreaSourceListUmapped;
	}

	public void setAvaillgd_LBInterCAreaSourceListUmapped(
			String availlgd_LBInterCAreaSourceListUmapped) {
		this.availlgd_LBInterCAreaSourceListUmapped = availlgd_LBInterCAreaSourceListUmapped;
	}

	public String getAvaillgd_LBVillageSourceLatICAUmapped() {
		return availlgd_LBVillageSourceLatICAUmapped;
	}

	public void setAvaillgd_LBVillageSourceLatICAUmapped(
			String availlgd_LBVillageSourceLatICAUmapped) {
		this.availlgd_LBVillageSourceLatICAUmapped = availlgd_LBVillageSourceLatICAUmapped;
	}

	public String getAvaillgd_LBVillageCAreaSourceLUnmapped() {
		return availlgd_LBVillageCAreaSourceLUnmapped;
	}

	public void setAvaillgd_LBVillageCAreaSourceLUnmapped(
			String availlgd_LBVillageCAreaSourceLUnmapped) {
		this.availlgd_LBVillageCAreaSourceLUnmapped = availlgd_LBVillageCAreaSourceLUnmapped;
	}

	public String getAvaillgd_LBInterCAreaSourceListUmappedUrban() {
		return availlgd_LBInterCAreaSourceListUmappedUrban;
	}

	public void setAvaillgd_LBInterCAreaSourceListUmappedUrban(
			String availlgd_LBInterCAreaSourceListUmappedUrban) {
		this.availlgd_LBInterCAreaSourceListUmappedUrban = availlgd_LBInterCAreaSourceListUmappedUrban;
	}

	

	public String getWardCcode() {
		return wardCcode;
	}

	public void setWardCcode(String wardCcode) {
		this.wardCcode = wardCcode;
	}

	public Date getLastupdatedon() {
		return lastupdatedon;
	}

	public void setLastupdatedon(Date lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

	public long getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	  	
  	public String getNewlocalbodyTypeNamehidden() {
		return newlocalbodyTypeNamehidden;
	}

	public void setNewlocalbodyTypeNamehidden(String newlocalbodyTypeNamehidden) {
		this.newlocalbodyTypeNamehidden = newlocalbodyTypeNamehidden;
	}

	public String getOldlocalbodyTypeNamehidden() {
		return oldlocalbodyTypeNamehidden;
	}

	public void setOldlocalbodyTypeNamehidden(String oldlocalbodyTypeNamehidden) {
		this.oldlocalbodyTypeNamehidden = oldlocalbodyTypeNamehidden;
	}

	private Integer wardId;
  	private String districtpanlbid;
  	private String intermediatepanlbid;
  	
	private String grampanlbid;
	private String invalidatedlbcode;
	
  	
  	public String getInvalidatedlbcode() {
		return invalidatedlbcode;
	}

	public void setInvalidatedlbcode(String invalidatedlbcode) {
		this.invalidatedlbcode = invalidatedlbcode;
	}

	private String localbodyNameEnghidden;
  	public String getLocalbodyNameEnghidden() {
		return localbodyNameEnghidden;
	}

	public void setLocalbodyNameEnghidden(String localbodyNameEnghidden) {
		this.localbodyNameEnghidden = localbodyNameEnghidden;
	}

	public String getLocalbodyNameAliasEnghidden() {
		return localbodyNameAliasEnghidden;
	}

	public void setLocalbodyNameAliasEnghidden(String localbodyNameAliasEnghidden) {
		this.localbodyNameAliasEnghidden = localbodyNameAliasEnghidden;
	}

	private String localbodyNameAliasEnghidden;
  
	

	public String getDistrictpanlbid() {
		return districtpanlbid;
	}

	public void setDistrictpanlbid(String districtpanlbid) {
		this.districtpanlbid = districtpanlbid;
	}

	public String getIntermediatepanlbid() {
		return intermediatepanlbid;
	}

	public void setIntermediatepanlbid(String intermediatepanlbid) {
		this.intermediatepanlbid = intermediatepanlbid;
	}

	public String getGrampanlbid() {
		return grampanlbid;
	}

	public void setGrampanlbid(String grampanlbid) {
		this.grampanlbid = grampanlbid;
	}

	public Integer getWardId() {
		return wardId;
	}

	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	
  	
	public String getChoosenlb() {
		return choosenlb;
	}

	public void setChoosenlb(String choosenlb) {
		this.choosenlb = choosenlb;
	}

	public String getListformat() {
		return listformat;
	}

	public void setListformat(String listformat) {
		this.listformat = listformat;
	}

	public boolean isLboption() {
		return lboption;
	}

	public void setLboption(boolean lboption) {
		this.lboption = lboption;
	}

	public String getLgd_LBInterVillageListhidden() {
		return lgd_LBInterVillageListhidden;
	}

	public void setLgd_LBInterVillageListhidden(String lgd_LBInterVillageListhidden) {
		this.lgd_LBInterVillageListhidden = lgd_LBInterVillageListhidden;
	}

	public String getLgd_LBInterSubDestListhidden() {
		return lgd_LBInterSubDestListhidden;
	}

	public void setLgd_LBInterSubDestListhidden(String lgd_LBInterSubDestListhidden) {
		this.lgd_LBInterSubDestListhidden = lgd_LBInterSubDestListhidden;
	}

	public String getLgd_LBDistPDestListhidden() {
		return lgd_LBDistPDestListhidden;
	}

	public void setLgd_LBDistPDestListhidden(String lgd_LBDistPDestListhidden) {
		this.lgd_LBDistPDestListhidden = lgd_LBDistPDestListhidden;
	}

	public String getContSubDistrict() {
		return contSubDistrict;
	}

	public void setContSubDistrict(String contSubDistrict) {
		this.contSubDistrict = contSubDistrict;
	}

	public String getContVillage() {
		return contVillage;
	}

	public void setContVillage(String contVillage) {
		this.contVillage = contVillage;
	}

	private String stateName; 
    public String getContDistrict() {
		return contDistrict;
	}

	public void setContDistrict(String contDistrict) {
		this.contDistrict = contDistrict;
	}
  	
    public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	private String lgd_LocalBodyTypeName;
    
    public String getLgd_LocalBodyTypeName() {
		return lgd_LocalBodyTypeName;
	}

	public void setLgd_LocalBodyTypeName(String lgd_LocalBodyTypeName) {
		this.lgd_LocalBodyTypeName = lgd_LocalBodyTypeName;
	}

	
  	
    //Code added by Arnab  Start
    
    public int getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(int flagCode) {
		this.flagCode = flagCode;
	}

	public Integer getLocalbodySubtype() {
		return localbodySubtype;
	}

	public void setLocalbodySubtype(Integer localbodySubtype) {
		this.localbodySubtype = localbodySubtype;
	}

	private Integer urbanwardId;
	private Integer parentwiseId;
	public Integer getParentwiseId()
	{
		return parentwiseId;
	}

	public void setParentwiseId(Integer parentwiseId) 
	{
		this.parentwiseId = parentwiseId;
	}

	private char parentCategory;
    public char getParentCategory() 
    {
		return parentCategory;
	}

	public void setParentCategory(char parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Integer getUrbanwardId() 
    {
		return urbanwardId;
	}

	public void setUrbanwardId(Integer urbanwardId) 
	{
		this.urbanwardId = urbanwardId;
	}

    //Code added by Arnab End
	
	
	public List<LocalbodyWard> getListLBWard() {
		return listLBWard;
	}

	public void setListLBWard(List<LocalbodyWard> listLBWard) {
		this.listLBWard = listLBWard;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

	public String getAliasNameEnglish() {
		return aliasNameEnglish;
	}

	public void setAliasNameEnglish(String aliasNameEnglish) {
		this.aliasNameEnglish = aliasNameEnglish;
	}

	public String getAlisNameLocal() {
		return alisNameLocal;
	}

	public void setAlisNameLocal(String alisNameLocal) {
		this.alisNameLocal = alisNameLocal;
	}

	public String getCorrectionRadio() {
		return correctionRadio;
	}

	public void setCorrectionRadio(String correctionRadio) {
		this.correctionRadio = correctionRadio;
	}

	public Integer getGloballocalbodyId() {
		return globallocalbodyId;
	}

	public void setGloballocalbodyId(Integer globallocalbodyId) {
		this.globallocalbodyId = globallocalbodyId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	public String getCaptchaAnswers() {
		return captchaAnswers;
	}

	public void setCaptchaAnswers(String captchaAnswers) {
		this.captchaAnswers = captchaAnswers;
	}

	public String getNoOrderRecord() {
		return noOrderRecord;
	}

	public void setNoOrderRecord(String noOrderRecord) {
		this.noOrderRecord = noOrderRecord;
	}

	public String getNoAttachmentRecord() {
		return noAttachmentRecord;
	}

	public void setNoAttachmentRecord(String noAttachmentRecord) {
		this.noAttachmentRecord = noAttachmentRecord;
	}

	public String getNoMapRecord() {
		return noMapRecord;
	}

	public void setNoMapRecord(String noMapRecord) {
		this.noMapRecord = noMapRecord;
	}

	public String getLocalBodyName() {
		return localBodyName;
	}

	public void setLocalBodyName(String localBodyName) {
		this.localBodyName = localBodyName;
	}

	public List<Localbody> getListLocalBodyDetail() {
		return listLocalBodyDetail;
	}

	public void setListLocalBodyDetail(List<Localbody> listLocalBodyDetail) {
		this.listLocalBodyDetail = listLocalBodyDetail;
	}

	public List<LocalGovtBodyDataForm> getListLocalBodyDetails() {
		return listLocalBodyDetails;
	}

	public void setListLocalBodyDetails(
			List<LocalGovtBodyDataForm> listLocalBodyDetails) {
		this.listLocalBodyDetails = listLocalBodyDetails;
	}

	public List<CommonsMultipartFile> getAttachFile2() {
		return attachFile2;
	}

	public void setAttachFile2(List<CommonsMultipartFile> attachFile2) {
		this.attachFile2 = attachFile2;
	}

	public String getRequiredTitle2() {
		return requiredTitle2;
	}

	public void setRequiredTitle2(String requiredTitle2) {
		this.requiredTitle2 = requiredTitle2;
	}

	public String getMaxFileLimit2() {
		return maxFileLimit2;
	}

	public void setMaxFileLimit2(String maxFileLimit2) {
		this.maxFileLimit2 = maxFileLimit2;
	}

	public String[] getFileTitle2() {
		return fileTitle2;
	}

	public void setFileTitle2(String[] fileTitle2) {
		this.fileTitle2 = fileTitle2;
	}

	public String getRequiredTitle1() {
		return requiredTitle1;
	}

	public void setRequiredTitle1(String requiredTitle1) {
		this.requiredTitle1 = requiredTitle1;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getMaxFileLimit1() {
		return maxFileLimit1;
	}

	public void setMaxFileLimit1(String maxFileLimit1) {
		this.maxFileLimit1 = maxFileLimit1;
	}

	public String[] getFileTitle1() {
		return fileTitle1;
	}

	public void setFileTitle1(String[] fileTitle1) {
		this.fileTitle1 = fileTitle1;
	}

	public List<CommonsMultipartFile> getAttachFile1() {
		return attachFile1;
	}

	public void setAttachFile1(List<CommonsMultipartFile> attachFile1) {
		this.attachFile1 = attachFile1;
	}

	public String getLgd_hiddenLbcList() {
		return lgd_hiddenLbcList;
	}

	public void setLgd_hiddenLbcList(String lgd_hiddenLbcList) {
		this.lgd_hiddenLbcList = lgd_hiddenLbcList;
	}

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public char getLbType() {
		return lbType;
	}

	public void setLbType(char lbType) {
		this.lbType = lbType;
	}

	public String getHeadQuarterCode() {
		return headQuarterCode;
	}

	public void setHeadQuarterCode(String headQuarterCode) {
		this.headQuarterCode = headQuarterCode;
	}

	public String getHeadQuarterName() {
		return headQuarterName;
	}

	public void setHeadQuarterName(String headQuarterName) {
		this.headQuarterName = headQuarterName;
	}

	private String operation;//operation P:Saving data for PRI Local Body
							//operation U:Saving data for Urban Local Body
	

     
     
	public String getParentLocalbodyName() {
		return parentLocalbodyName;
	}

	public void setParentLocalbodyName(String parentLocalbodyName) {
		this.parentLocalbodyName = parentLocalbodyName;
	}

	public String getLgd_LBVillageSurveyDestLatDCA() {
		return lgd_LBVillageSurveyDestLatDCA;
	}

	public void setLgd_LBVillageSurveyDestLatDCA(
			String lgd_LBVillageSurveyDestLatDCA) {
		this.lgd_LBVillageSurveyDestLatDCA = lgd_LBVillageSurveyDestLatDCA;
	}

	public String getLgd_LBVillageSurveySourceLatDCA() {
		return lgd_LBVillageSurveySourceLatDCA;
	}

	public void setLgd_LBVillageSurveySourceLatDCA(
			String lgd_LBVillageSurveySourceLatDCA) {
		this.lgd_LBVillageSurveySourceLatDCA = lgd_LBVillageSurveySourceLatDCA;
	}

	public String getLgd_LBVillageSurveyDestLatICA() {
		return lgd_LBVillageSurveyDestLatICA;
	}

	public void setLgd_LBVillageSurveyDestLatICA(
			String lgd_LBVillageSurveyDestLatICA) {
		this.lgd_LBVillageSurveyDestLatICA = lgd_LBVillageSurveyDestLatICA;
	}

	public String getLgd_LBVillageSurveySourceLatICA() {
		return lgd_LBVillageSurveySourceLatICA;
	}

	public void setLgd_LBVillageSurveySourceLatICA(
			String lgd_LBVillageSurveySourceLatICA) {
		this.lgd_LBVillageSurveySourceLatICA = lgd_LBVillageSurveySourceLatICA;
	}

	public String getLgd_LBVillageSurveyDestLatVCA() {
		return lgd_LBVillageSurveyDestLatVCA;
	}

	public void setLgd_LBVillageSurveyDestLatVCA(
			String lgd_LBVillageSurveyDestLatVCA) {
		this.lgd_LBVillageSurveyDestLatVCA = lgd_LBVillageSurveyDestLatVCA;
	}

	public String getLgd_LBVillageSurveySourceLatVCA() {
		return lgd_LBVillageSurveySourceLatVCA;
	}

	public void setLgd_LBVillageSurveySourceLatVCA(
			String lgd_LBVillageSurveySourceLatVCA) {
		this.lgd_LBVillageSurveySourceLatVCA = lgd_LBVillageSurveySourceLatVCA;
	}

	public char getWard_landRegiontype() {
		return ward_landRegiontype;
	}

	public void setWard_landRegiontype(char ward_landRegiontype) {
		this.ward_landRegiontype = ward_landRegiontype;
	}

	public String getLocalBodyNameEnglishListForUrban() {
		return localBodyNameEnglishListForUrban;
	}

	public void setLocalBodyNameEnglishListForUrban(
			String localBodyNameEnglishListForUrban) {
		this.localBodyNameEnglishListForUrban = localBodyNameEnglishListForUrban;
	}

	public String getLocalBodyNameEnglishListSourceVillg() {
		return localBodyNameEnglishListSourceVillg;
	}

	public void setLocalBodyNameEnglishListSourceVillg(
			String localBodyNameEnglishListSourceVillg) {
		this.localBodyNameEnglishListSourceVillg = localBodyNameEnglishListSourceVillg;
	}

	public String getLgd_LBSubDistrictList() {
		return lgd_LBSubDistrictList;
	}

	public void setLgd_LBSubDistrictList(String lgd_LBSubDistrictList) {
		this.lgd_LBSubDistrictList = lgd_LBSubDistrictList;
	}

	public String getLb_lgdPanchayatName() {
		return lb_lgdPanchayatName;
	}

	public void setLb_lgdPanchayatName(String lb_lgdPanchayatName) {
		this.lb_lgdPanchayatName = lb_lgdPanchayatName;
	}

	public String getHdnIntermediatePanchayat() {
		return hdnIntermediatePanchayat;
	}

	public void setHdnIntermediatePanchayat(String hdnIntermediatePanchayat) {
		this.hdnIntermediatePanchayat = hdnIntermediatePanchayat;
	}

	public String getHdnType() {
		return hdnType;
	}

	public void setHdnType(String hdnType) {
		this.hdnType = hdnType;
	}

	public String getHdnDistrictPanchayat() {
		return hdnDistrictPanchayat;
	}

	public void setHdnDistrictPanchayat(String hdnDistrictPanchayat) {
		this.hdnDistrictPanchayat = hdnDistrictPanchayat;
	}

	
	public String getLgd_lbTypeCode() {
		return lgd_lbTypeCode;
	}

	public void setLgd_lbTypeCode(String lgd_lbTypeCode) {
		this.lgd_lbTypeCode = lgd_lbTypeCode;
	}

	public List<LocalGovtBodyWard> getLocalGovtBodyWardList() {
		return localGovtBodyWardList;
	}

	public void setLocalGovtBodyWardList(
			List<LocalGovtBodyWard> localGovtBodyWardList) {
		this.localGovtBodyWardList = localGovtBodyWardList;
	}

	public String getLgd_lbCategory() {
		return lgd_lbCategory;
	}

	public void setLgd_lbCategory(String lgd_lbCategory) {
		this.lgd_lbCategory = lgd_lbCategory;
	}

	public List<LocalbodyListbyState> getLgd_LGdistrictPanchayatList() {
		return lgd_LGdistrictPanchayatList;
	}

	public void setLgd_LGdistrictPanchayatList(
			List<LocalbodyListbyState> lgd_LGdistrictPanchayatList) {
		this.lgd_LGdistrictPanchayatList = lgd_LGdistrictPanchayatList;
	}

	public String getLgd_UrbanLBSubdistrictLUmappedDest() {
		return lgd_UrbanLBSubdistrictLUmappedDest;
	}

	public void setLgd_UrbanLBSubdistrictLUmappedDest(
			String lgd_UrbanLBSubdistrictLUmappedDest) {
		this.lgd_UrbanLBSubdistrictLUmappedDest = lgd_UrbanLBSubdistrictLUmappedDest;
	}

	public String getLgd_UrbanLBSubdistrictLUmappedSource() {
		return lgd_UrbanLBSubdistrictLUmappedSource;
	}

	public void setLgd_UrbanLBSubdistrictLUmappedSource(
			String lgd_UrbanLBSubdistrictLUmappedSource) {
		this.lgd_UrbanLBSubdistrictLUmappedSource = lgd_UrbanLBSubdistrictLUmappedSource;
	}

	public String getLgd_UrbanLBDistUmappedDest() {
		return lgd_UrbanLBDistUmappedDest;
	}

	public void setLgd_UrbanLBDistUmappedDest(String lgd_UrbanLBDistUmappedDest) {
		this.lgd_UrbanLBDistUmappedDest = lgd_UrbanLBDistUmappedDest;
	}

	public String getLgd_UrbanLBDistUmappedSource() {
		return lgd_UrbanLBDistUmappedSource;
	}

	public void setLgd_UrbanLBDistUmappedSource(
			String lgd_UrbanLBDistUmappedSource) {
		this.lgd_UrbanLBDistUmappedSource = lgd_UrbanLBDistUmappedSource;
	}

	public String getLgd_UrbanLBDistExistSource() {
		return lgd_UrbanLBDistExistSource;
	}

	public void setLgd_UrbanLBDistExistSource(String lgd_UrbanLBDistExistSource) {
		this.lgd_UrbanLBDistExistSource = lgd_UrbanLBDistExistSource;
	}

	public String getLgd_UrbanLBDistExistDest() {
		return lgd_UrbanLBDistExistDest;
	}

	public void setLgd_UrbanLBDistExistDest(String lgd_UrbanLBDistExistDest) {
		this.lgd_UrbanLBDistExistDest = lgd_UrbanLBDistExistDest;
	}

	public String getLgd_UrbanLBSubdistrictListSource() {
		return lgd_UrbanLBSubdistrictListSource;
	}

	public void setLgd_UrbanLBSubdistrictListSource(
			String lgd_UrbanLBSubdistrictListSource) {
		this.lgd_UrbanLBSubdistrictListSource = lgd_UrbanLBSubdistrictListSource;
	}

	public String getLgd_UrbanLBSubdistrictListDest() {
		return lgd_UrbanLBSubdistrictListDest;
	}

	public void setLgd_UrbanLBSubdistrictListDest(
			String lgd_UrbanLBSubdistrictListDest) {
		this.lgd_UrbanLBSubdistrictListDest = lgd_UrbanLBSubdistrictListDest;
	}

	public String getLocalBodyNameEnglishVillageListSource() {
		return localBodyNameEnglishVillageListSource;
	}

	public void setLocalBodyNameEnglishVillageListSource(
			String localBodyNameEnglishVillageListSource) {
		this.localBodyNameEnglishVillageListSource = localBodyNameEnglishVillageListSource;
	}

	public String getLocalBodyNameEnglishVillageList() {
		return localBodyNameEnglishVillageList;
	}

	public void setLocalBodyNameEnglishVillageList(
			String localBodyNameEnglishVillageList) {
		this.localBodyNameEnglishVillageList = localBodyNameEnglishVillageList;
	}

	public String getLocalGovtBodyVillageList() {
		return localGovtBodyVillageList;
	}

	public void setLocalGovtBodyVillageList(String localGovtBodyVillageList) {
		this.localGovtBodyVillageList = localGovtBodyVillageList;
	}

	public String getLocalGovtBodyNameEnglishDestVillage() {
		return localGovtBodyNameEnglishDestVillage;
	}

	public void setLocalGovtBodyNameEnglishDestVillage(
			String localGovtBodyNameEnglishDestVillage) {
		this.localGovtBodyNameEnglishDestVillage = localGovtBodyNameEnglishDestVillage;
	}

	public String getLocalBodyLandRegionArea() {
		return localBodyLandRegionArea;
	}

	public void setLocalBodyLandRegionArea(String localBodyLandRegionArea) {
		this.localBodyLandRegionArea = localBodyLandRegionArea;
	}

	public String getLocalBodyNameEnglishDestFinalPart() {
		return localBodyNameEnglishDestFinalPart;
	}

	public void setLocalBodyNameEnglishDestFinalPart(
			String localBodyNameEnglishDestFinalPart) {
		this.localBodyNameEnglishDestFinalPart = localBodyNameEnglishDestFinalPart;
	}

	public String getUnmappedLocalBody() {
		return unmappedLocalBody;
	}

	public void setUnmappedLocalBody(String unmappedLocalBody) {
		this.unmappedLocalBody = unmappedLocalBody;
	}

	public String getLocalBodyNameEnglishListUnMapped() {
		return localBodyNameEnglishListUnMapped;
	}

	public void setLocalBodyNameEnglishListUnMapped(
			String localBodyNameEnglishListUnMapped) {
		this.localBodyNameEnglishListUnMapped = localBodyNameEnglishListUnMapped;
	}

	public String getLocalBodyNameEnglishDestUnmapped() {
		return localBodyNameEnglishDestUnmapped;
	}

	public void setLocalBodyNameEnglishDestUnmapped(
			String localBodyNameEnglishDestUnmapped) {
		this.localBodyNameEnglishDestUnmapped = localBodyNameEnglishDestUnmapped;
	}

	public String getLocalBodyDistListforVillage() {
		return localBodyDistListforVillage;
	}

	public void setLocalBodyDistListforVillage(
			String localBodyDistListforVillage) {
		this.localBodyDistListforVillage = localBodyDistListforVillage;
	}

	public String getLocalGovtBodyNameEnglishSubDist() {
		return localGovtBodyNameEnglishSubDist;
	}

	public void setLocalGovtBodyNameEnglishSubDist(
			String localGovtBodyNameEnglishSubDist) {
		this.localGovtBodyNameEnglishSubDist = localGovtBodyNameEnglishSubDist;
	}

	public String getLgd_LBNameInEnDestForSubDist() {
		return lgd_LBNameInEnDestForSubDist;
	}

	public void setLgd_LBNameInEnDestForSubDist(
			String lgd_LBNameInEnDestForSubDist) {
		this.lgd_LBNameInEnDestForSubDist = lgd_LBNameInEnDestForSubDist;
	}

	public String getLgd_LBNameInEnSubDistListSource() {
		return lgd_LBNameInEnSubDistListSource;
	}

	public void setLgd_LBNameInEnSubDistListSource(
			String lgd_LBNameInEnSubDistListSource) {
		this.lgd_LBNameInEnSubDistListSource = lgd_LBNameInEnSubDistListSource;
	}

	public String getLocalGovtBodyNameEnglishDestDist() {
		return localGovtBodyNameEnglishDestDist;
	}

	public void setLocalGovtBodyNameEnglishDestDist(
			String localGovtBodyNameEnglishDestDist) {
		this.localGovtBodyNameEnglishDestDist = localGovtBodyNameEnglishDestDist;
	}

	public String getLgd_LBNameInEnCovered() {
		return lgd_LBNameInEnCovered;
	}

	public void setLgd_LBNameInEnCovered(String lgd_LBNameInEnCovered) {
		this.lgd_LBNameInEnCovered = lgd_LBNameInEnCovered;
	}

	public String getLgd_LBNameInEnDest() {
		return lgd_LBNameInEnDest;
	}

	public void setLgd_LBNameInEnDest(String lgd_LBNameInEnDest) {
		this.lgd_LBNameInEnDest = lgd_LBNameInEnDest;
	}

	public boolean isLgd_LBExistingLbChk() {
		return lgd_LBExistingLbChk;
	}

	public void setLgd_LBExistingLbChk(boolean lgd_LBExistingLbChk) {
		this.lgd_LBExistingLbChk = lgd_LBExistingLbChk;
	}

	public String getLgd_LBCoveredName() {
		return lgd_LBCoveredName;
	}

	public void setLgd_LBCoveredName(String lgd_LBCoveredName) {
		this.lgd_LBCoveredName = lgd_LBCoveredName;
	}

	public List<LocalBodyDetails> getLocalBodyDetails() {
		return localBodyDetails;
	}

	public void setLocalBodyDetails(List<LocalBodyDetails> lbDetails) {
		this.localBodyDetails = lbDetails;
	}

	public boolean isLgd_LBchkModifyType() {
		return lgd_LBchkModifyType;
	}

	public void setLgd_LBchkModifyType(boolean lgd_LBchkModifyType) {
		this.lgd_LBchkModifyType = lgd_LBchkModifyType;
	}

	public boolean isLgd_LBchkModifyParent() {
		return lgd_LBchkModifyParent;
	}

	public void setLgd_LBchkModifyParent(boolean lgd_LBchkModifyParent) {
		this.lgd_LBchkModifyParent = lgd_LBchkModifyParent;
	}

	public boolean isLgd_LBchkModifyCoverage() {
		return lgd_LBchkModifyCoverage;
	}

	public void setLgd_LBchkModifyCoverage(boolean lgd_LBchkModifyCoverage) {
		this.lgd_LBchkModifyCoverage = lgd_LBchkModifyCoverage;
	}

	public char getLgd_LBlevelChk() {
		return lgd_LBlevelChk;
	}

	public void setLgd_LBlevelChk(char lgd_LBlevelChk) {
		this.lgd_LBlevelChk = lgd_LBlevelChk;
	}

	public boolean isLgd_LBchkModifyName() {
		return lgd_LBchkModifyName;
	}

	public void setLgd_LBchkModifyName(boolean lgd_LBchkModifyName) {
		this.lgd_LBchkModifyName = lgd_LBchkModifyName;
	}

	public String getLocalBodyNameEnglishList() {
		return localBodyNameEnglishList;
	}

	public void setLocalBodyNameEnglishList(String localBodyNameEnglishList) {
		this.localBodyNameEnglishList = localBodyNameEnglishList;
	}

	public String getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(String localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public String getIntermediatePanchayat() {
		return intermediatePanchayat;
	}

	public void setIntermediatePanchayat(String intermediatePanchayat) {
		this.intermediatePanchayat = intermediatePanchayat;
	}

	public String getVillagePanchayat() {
		return villagePanchayat;
	}

	public void setVillagePanchayat(String villagePanchayat) {
		this.villagePanchayat = villagePanchayat;
	}

	public String getDistrictPanchayatName() {
		return districtPanchayatName;
	}

	public void setDistrictPanchayatName(String districtPanchayatName) {
		this.districtPanchayatName = districtPanchayatName;
	}

	public List<ParentWiseLocalBodies> getLocalBodyViewChild() {
		return localBodyViewChild;
	}

	public void setLocalBodyViewChild(
			List<ParentWiseLocalBodies> localBodyViewChild) {
		this.localBodyViewChild = localBodyViewChild;
	}

	public List<LocalbodyListbyState> getLgdLocalGovtBodyList() {
		return lgdLocalGovtBodyList;
	}

	public void setLgdLocalGovtBodyList(
			List<LocalbodyListbyState> lgdLocalGovtBodyList) {
		this.lgdLocalGovtBodyList = lgdLocalGovtBodyList;
	}
	public String getLocalBodyNameEnglishListSource() {
		return localBodyNameEnglishListSource;
	}

	public void setLocalBodyNameEnglishListSource(
			String localBodyNameEnglishListSource) {
		this.localBodyNameEnglishListSource = localBodyNameEnglishListSource;
	}

	public String getLocalBodyNameEnglishListForSubDist() {
		return localBodyNameEnglishListForSubDist;
	}

	public void setLocalBodyNameEnglishListForSubDist(
			String localBodyNameEnglishListForSubDist) {
		this.localBodyNameEnglishListForSubDist = localBodyNameEnglishListForSubDist;
	}

	public String getLgd_LBDistCAreaDestListUmapped() {
		return lgd_LBDistCAreaDestListUmapped;
	}

	public void setLgd_LBDistCAreaDestListUmapped(
			String lgd_LBDistCAreaDestListUmapped) {
		this.lgd_LBDistCAreaDestListUmapped = lgd_LBDistCAreaDestListUmapped;
	}

	public String getLgd_LBInterCAreaSourceListUmapped() {
		return lgd_LBInterCAreaSourceListUmapped;
	}

	public void setLgd_LBInterCAreaSourceListUmapped(
			String lgd_LBInterCAreaSourceListUmapped) {
		this.lgd_LBInterCAreaSourceListUmapped = lgd_LBInterCAreaSourceListUmapped;
	}

	public String getLgd_LBInterCAreaDestListUmapped() {
		return lgd_LBInterCAreaDestListUmapped;
	}

	public void setLgd_LBInterCAreaDestListUmapped(
			String lgd_LBInterCAreaDestListUmapped) {
		this.lgd_LBInterCAreaDestListUmapped = lgd_LBInterCAreaDestListUmapped;
	}

	public String getLgd_LBVillageCAreaDestLUnmapped() {
		return lgd_LBVillageCAreaDestLUnmapped;
	}

	public void setLgd_LBVillageCAreaDestLUnmapped(
			String lgd_LBVillageCAreaDestLUnmapped) {
		this.lgd_LBVillageCAreaDestLUnmapped = lgd_LBVillageCAreaDestLUnmapped;
	}

	public String getLgd_LBVillageCAreaSourceLUnmapped() {
		return lgd_LBVillageCAreaSourceLUnmapped;
	}

	public void setLgd_LBVillageCAreaSourceLUnmapped(
			String lgd_LBVillageCAreaSourceLUnmapped) {
		this.lgd_LBVillageCAreaSourceLUnmapped = lgd_LBVillageCAreaSourceLUnmapped;
	}

	public String getLgd_LBVillageSourceLatICAUmapped() {
		return lgd_LBVillageSourceLatICAUmapped;
	}

	public void setLgd_LBVillageSourceLatICAUmapped(
			String lgd_LBVillageSourceLatICAUmapped) {
		this.lgd_LBVillageSourceLatICAUmapped = lgd_LBVillageSourceLatICAUmapped;
	}

	public String getLgd_LBVillageDestLatICAUmapped() {
		return lgd_LBVillageDestLatICAUmapped;
	}

	public void setLgd_LBVillageDestLatICAUmapped(
			String lgd_LBVillageDestLatICAUmapped) {
		this.lgd_LBVillageDestLatICAUmapped = lgd_LBVillageDestLatICAUmapped;
	}

	public String getLgd_LBDistCAreaSourceListUmapped() {
		return lgd_LBDistCAreaSourceListUmapped;
	}

	public void setLgd_LBDistCAreaSourceListUmapped(
			String lgd_LBDistCAreaSourceListUmapped) {
		this.lgd_LBDistCAreaSourceListUmapped = lgd_LBDistCAreaSourceListUmapped;
	}

	public String getLgd_LBSubDistrictSourceLatDCAUmapped() {
		return lgd_LBSubDistrictSourceLatDCAUmapped;
	}

	public void setLgd_LBSubDistrictSourceLatDCAUmapped(
			String lgd_LBSubDistrictSourceLatDCAUmapped) {
		this.lgd_LBSubDistrictSourceLatDCAUmapped = lgd_LBSubDistrictSourceLatDCAUmapped;
	}

	public String getLgd_LBSubDistrictDestLatDCAUmapped() {
		return lgd_LBSubDistrictDestLatDCAUmapped;
	}

	public void setLgd_LBSubDistrictDestLatDCAUmapped(
			String lgd_LBSubDistrictDestLatDCAUmapped) {
		this.lgd_LBSubDistrictDestLatDCAUmapped = lgd_LBSubDistrictDestLatDCAUmapped;
	}

	public String getLgd_LBVillageDestLatDCAUmapped() {
		return lgd_LBVillageDestLatDCAUmapped;
	}

	public void setLgd_LBVillageDestLatDCAUmapped(
			String lgd_LBVillageDestLatDCAUmapped) {
		this.lgd_LBVillageDestLatDCAUmapped = lgd_LBVillageDestLatDCAUmapped;
	}

	public String getLgd_LBVillageSourceLatDCAUmapped() {
		return lgd_LBVillageSourceLatDCAUmapped;
	}

	public void setLgd_LBVillageSourceLatDCAUmapped(
			String lgd_LBVillageSourceLatDCAUmapped) {
		this.lgd_LBVillageSourceLatDCAUmapped = lgd_LBVillageSourceLatDCAUmapped;
	}

	public String getLgd_LBSubDistrictSourceLatDCA() {
		return lgd_LBSubDistrictSourceLatDCA;
	}

	public void setLgd_LBSubDistrictSourceLatDCA(
			String lgd_LBSubDistrictSourceLatDCA) {
		this.lgd_LBSubDistrictSourceLatDCA = lgd_LBSubDistrictSourceLatDCA;
	}

	public String getLgd_LBSubDistrictDestLatDCA() {
		return lgd_LBSubDistrictDestLatDCA;
	}

	public void setLgd_LBSubDistrictDestLatDCA(
			String lgd_LBSubDistrictDestLatDCA) {
		this.lgd_LBSubDistrictDestLatDCA = lgd_LBSubDistrictDestLatDCA;
	}

	public String getLgd_LBVillageSourceLatDCA() {
		return lgd_LBVillageSourceLatDCA;
	}

	public void setLgd_LBVillageSourceLatDCA(String lgd_LBVillageSourceLatDCA) {
		this.lgd_LBVillageSourceLatDCA = lgd_LBVillageSourceLatDCA;
	}

	public String getLgd_LBVillageDestLatDCA() {
		return lgd_LBVillageDestLatDCA;
	}

	public void setLgd_LBVillageDestLatDCA(String lgd_LBVillageDestLatDCA) {
		this.lgd_LBVillageDestLatDCA = lgd_LBVillageDestLatDCA;
	}

	public String getLgd_LBVillageDestLatICA() {
		return lgd_LBVillageDestLatICA;
	}

	public void setLgd_LBVillageDestLatICA(String lgd_LBVillageDestLatICA) {
		this.lgd_LBVillageDestLatICA = lgd_LBVillageDestLatICA;
	}

	public String getLgd_LBVillageSourceLatICA() {
		return lgd_LBVillageSourceLatICA;
	}

	public void setLgd_LBVillageSourceLatICA(String lgd_LBVillageSourceLatICA) {
		this.lgd_LBVillageSourceLatICA = lgd_LBVillageSourceLatICA;
	}

	public String getLgd_LBVillageSourceAtVillageCA() {
		return lgd_LBVillageSourceAtVillageCA;
	}

	public void setLgd_LBVillageSourceAtVillageCA(
			String lgd_LBVillageSourceAtVillageCA) {
		this.lgd_LBVillageSourceAtVillageCA = lgd_LBVillageSourceAtVillageCA;
	}

	public String getLgd_LBVillageCAreaAtVillageCA() {
		return lgd_LBVillageCAreaAtVillageCA;
	}

	public void setLgd_LBVillageCAreaAtVillageCA(
			String lgd_LBVillageCAreaAtVillageCA) {
		this.lgd_LBVillageCAreaAtVillageCA = lgd_LBVillageCAreaAtVillageCA;
	}

	public String getLgd_LBVillageDestAtVillageCA() {
		return lgd_LBVillageDestAtVillageCA;
	}

	public void setLgd_LBVillageDestAtVillageCA(
			String lgd_LBVillageDestAtVillageCA) {
		this.lgd_LBVillageDestAtVillageCA = lgd_LBVillageDestAtVillageCA;
	}

	public String getLgd_LBVillageCAreaSourceL() {
		return lgd_LBVillageCAreaSourceL;
	}

	public void setLgd_LBVillageCAreaSourceL(String lgd_LBVillageCAreaSourceL) {
		this.lgd_LBVillageCAreaSourceL = lgd_LBVillageCAreaSourceL;
	}

	public String getLgd_LBVillageCAreaDestL() {
		return lgd_LBVillageCAreaDestL;
	}

	public void setLgd_LBVillageCAreaDestL(String lgd_LBVillageCAreaDestL) {
		this.lgd_LBVillageCAreaDestL = lgd_LBVillageCAreaDestL;
	}

	public String getLgd_LBDistPUnMappedDestList() {
		return lgd_LBDistPUnMappedDestList;
	}

	public void setLgd_LBDistPUnMappedDestList(
			String lgd_LBDistPUnMappedDestList) {
		this.lgd_LBDistPUnMappedDestList = lgd_LBDistPUnMappedDestList;
	}

	public String getLgd_LBDistPUnMappedList() {
		return lgd_LBDistPUnMappedList;
	}

	public void setLgd_LBDistPUnMappedList(String lgd_LBDistPUnMappedList) {
		this.lgd_LBDistPUnMappedList = lgd_LBDistPUnMappedList;
	}

	public String getLgd_LBVillageAtVillageCA() {
		return lgd_LBVillageAtVillageCA;
	}

	public void setLgd_LBVillageAtVillageCA(String lgd_LBVillageAtVillageCA) {
		this.lgd_LBVillageAtVillageCA = lgd_LBVillageAtVillageCA;
	}

	public String getLgd_LBInterListAtVillageCA() {
		return lgd_LBInterListAtVillageCA;
	}

	public void setLgd_LBInterListAtVillageCA(String lgd_LBInterListAtVillageCA) {
		this.lgd_LBInterListAtVillageCA = lgd_LBInterListAtVillageCA;
	}

	public String getLgd_LBDistListAtVillageCA() {
		return lgd_LBDistListAtVillageCA;
	}

	public void setLgd_LBDistListAtVillageCA(String lgd_LBDistListAtVillageCA) {
		this.lgd_LBDistListAtVillageCA = lgd_LBDistListAtVillageCA;
	}

	public String getLgd_LBDistListAtInterCA() {
		return lgd_LBDistListAtInterCA;
	}

	public void setLgd_LBDistListAtInterCA(String lgd_LBDistListAtInterCA) {
		this.lgd_LBDistListAtInterCA = lgd_LBDistListAtInterCA;
	}

	public String getLgd_LBInterPDestList() {
		return lgd_LBInterPDestList;
	}

	public void setLgd_LBInterPDestList(String lgd_LBInterPDestList) {
		this.lgd_LBInterPDestList = lgd_LBInterPDestList;
	}

	public String getLgd_LBInterPSourceList() {
		return lgd_LBInterPSourceList;
	}

	public void setLgd_LBInterPSourceList(String lgd_LBInterPSourceList) {
		this.lgd_LBInterPSourceList = lgd_LBInterPSourceList;
	}

	public String getLgd_LBInterCAreaDestList() {
		return lgd_LBInterCAreaDestList;
	}

	public void setLgd_LBInterCAreaDestList(String lgd_LBInterCAreaDestList) {
		this.lgd_LBInterCAreaDestList = lgd_LBInterCAreaDestList;
	}

	public String getLgd_LBInterCAreaSourceList() {
		return lgd_LBInterCAreaSourceList;
	}

	public void setLgd_LBInterCAreaSourceList(String lgd_LBInterCAreaSourceList) {
		this.lgd_LBInterCAreaSourceList = lgd_LBInterCAreaSourceList;
	}

	public String getLgd_LBDistCAreaSourceList() {
		return lgd_LBDistCAreaSourceList;
	}

	public void setLgd_LBDistCAreaSourceList(String lgd_LBDistCAreaSourceList) {
		this.lgd_LBDistCAreaSourceList = lgd_LBDistCAreaSourceList;
	}

	public String getLgd_LBDistCAreaDestList() {
		return lgd_LBDistCAreaDestList;
	}

	public void setLgd_LBDistCAreaDestList(String lgd_LBDistCAreaDestList) {
		this.lgd_LBDistCAreaDestList = lgd_LBDistCAreaDestList;
	}

	public String getLgd_LBDistPSourceList() {
		return lgd_LBDistPSourceList;
	}

	public void setLgd_LBDistPSourceList(String lgd_LBDistPSourceList) {
		this.lgd_LBDistPSourceList = lgd_LBDistPSourceList;
	}

	public String getLgd_LBDistPDestList() {
		return lgd_LBDistPDestList;
	}

	public void setLgd_LBDistPDestList(String lgd_LBDistPDestList) {
		this.lgd_LBDistPDestList = lgd_LBDistPDestList;
	}

	public String getLgd_LBNameInEn() {
		return lgd_LBNameInEn;
	}

	public void setLgd_LBNameInEn(String lgd_LBNameInEn) {
		this.lgd_LBNameInEn = lgd_LBNameInEn;
	}

	public String getLgd_LBNameInLocal() {
		return lgd_LBNameInLocal;
	}

	public void setLgd_LBNameInLocal(String lgd_LBNameInLocal) {
		this.lgd_LBNameInLocal = lgd_LBNameInLocal;
	}

	public String getLgd_LBAliasInEn() {
		return lgd_LBAliasInEn;
	}

	public void setLgd_LBAliasInEn(String lgd_LBAliasInEn) {
		this.lgd_LBAliasInEn = lgd_LBAliasInEn;
	}

	public String getLgd_LBAliasInLocal() {
		return lgd_LBAliasInLocal;
	}

	public void setLgd_LBAliasInLocal(String lgd_LBAliasInLocal) {
		this.lgd_LBAliasInLocal = lgd_LBAliasInLocal;
	}

	public String getLgd_LBstateSpecificCode() {
		return lgd_LBstateSpecificCode;
	}

	public void setLgd_LBstateSpecificCode(String lgd_LBstateSpecificCode) {
		this.lgd_LBstateSpecificCode = lgd_LBstateSpecificCode;
	}

	public String getfileMapUpLoad() {
		return fileMapUpLoad;
	}

// TODO Remove unused code found by UCDetector
// 	public void setfileMapUpLoad(String fileMapUpLoad) {
// 		this.fileMapUpLoad = fileMapUpLoad;
// 	}

	public String getLgd_LBTypeName() {
		return lgd_LBTypeName;
	}

	public void setLgd_LBTypeName(String lgd_LBTypeName) {
		this.lgd_LBTypeName = lgd_LBTypeName;
	}

	public String getLgd_LBDistrictAtInter() {
		return lgd_LBDistrictAtInter;
	}

	public void setLgd_LBDistrictAtInter(String lgd_LBDistrictAtInter) {
		this.lgd_LBDistrictAtInter = lgd_LBDistrictAtInter;
	}

	public String getLgd_LBDistrictAtVillage() {
		return lgd_LBDistrictAtVillage;
	}

	public void setLgd_LBDistrictAtVillage(String lgd_LBDistrictAtVillage) {
		this.lgd_LBDistrictAtVillage = lgd_LBDistrictAtVillage;
	}

	public String getLgd_LBIntermediateAtVillage() {
		return lgd_LBIntermediateAtVillage;
	}

	public void setLgd_LBIntermediateAtVillage(
			String lgd_LBIntermediateAtVillage) {
		this.lgd_LBIntermediateAtVillage = lgd_LBIntermediateAtVillage;
	}

	public String getLgd_LBPesaAct() {
		return lgd_LBPesaAct;
	}

	public void setLgd_LBPesaAct(String lgd_LBPesaAct) {
		this.lgd_LBPesaAct = lgd_LBPesaAct;
	}

	public String getlatitude() {
		return latitude;
	}

// TODO Remove unused code found by UCDetector
// 	public void setlatitude(String latitude) {
// 		this.latitude = latitude;
// 	}

	public String getlongitude() {
		return longitude;
	}

// TODO Remove unused code found by UCDetector
// 	public void setlongitude(String longitude) {
// 		this.longitude = longitude;
// 	}

	public String getLgd_LBorderNo() {
		return lgd_LBorderNo;
	}

	public void setLgd_LBorderNo(String lgd_LBorderNo) {
		this.lgd_LBorderNo = lgd_LBorderNo;
	}

	public Date getLgd_LBorderDate() {
		return lgd_LBorderDate;
	}

	public void setLgd_LBorderDate(Date lgd_LBorderDate) {
		this.lgd_LBorderDate = lgd_LBorderDate;
	}

	public Date getLgd_LBeffectiveDate() {
		return lgd_LBeffectiveDate;
	}

	public void setLgd_LBeffectiveDate(Date lgd_LBeffectiveDate) {
		this.lgd_LBeffectiveDate = lgd_LBeffectiveDate;
	}

	public Date getLgd_LBgazPubDate() {
		return lgd_LBgazPubDate;
	}

	public void setLgd_LBgazPubDate(Date lgd_LBgazPubDate) {
		this.lgd_LBgazPubDate = lgd_LBgazPubDate;
	}

	public MultipartFile getLgd_LBfilePath() {
		return lgd_LBfilePath;
	}

	public void setLgd_LBfilePath(MultipartFile lgd_LBfilePath) {
		this.lgd_LBfilePath = lgd_LBfilePath;
	}

	public String getLgd_LBNomenclatureDist() {
		return lgd_LBNomenclatureDist;
	}

	public void setLgd_LBNomenclatureDist(String lgd_LBNomenclatureDist) {
		this.lgd_LBNomenclatureDist = lgd_LBNomenclatureDist;
	}

	public String getLgd_LBNomenclatureInter() {
		return lgd_LBNomenclatureInter;
	}

	public void setLgd_LBNomenclatureInter(String lgd_LBNomenclatureInter) {
		this.lgd_LBNomenclatureInter = lgd_LBNomenclatureInter;
	}

	public String getLgd_LBNomenclatureVillage() {
		return lgd_LBNomenclatureVillage;
	}

	public void setLgd_LBNomenclatureVillage(String lgd_LBNomenclatureVillage) {
		this.lgd_LBNomenclatureVillage = lgd_LBNomenclatureVillage;
	}

	public String getLgd_LBNomenclatureUrban() {
		return lgd_LBNomenclatureUrban;
	}

	public void setLgd_LBNomenclatureUrban(String lgd_LBNomenclatureUrban) {
		this.lgd_LBNomenclatureUrban = lgd_LBNomenclatureUrban;
	}

	public boolean isLgd_LBExistCheck() {
		return lgd_LBExistCheck;
	}

	public void setLgd_LBExistCheck(boolean lgd_LBExistCheck) {
		this.lgd_LBExistCheck = lgd_LBExistCheck;
	}

	public boolean isLgd_LBUnmappedCheck() {
		return lgd_LBUnmappedCheck;
	}

	public void setLgd_LBUnmappedCheck(boolean lgd_LBUnmappedCheck) {
		this.lgd_LBUnmappedCheck = lgd_LBUnmappedCheck;
	}

	/**
	 * @return the lgTypeName
	 */
	public String getLgTypeName() {
		return lgTypeName;
	}

	/**
	 * @param lgTypeName
	 *            the lgTypeName to set
	 */
	public void setLgTypeName(String lgTypeName) {
		this.lgTypeName = lgTypeName;
	}

	public String getWard_Name() {
		return ward_Name;
	}

	public void setWard_Name(String ward_Name) {
		this.ward_Name = ward_Name;
	}

	public String getWard_NameLocal() {
		return ward_NameLocal;
	}

	public void setWard_NameLocal(String ward_NameLocal) {
		this.ward_NameLocal = ward_NameLocal;
	}

	public String getWard_Namecr() {
		return ward_Namecr;
	}

	public void setWard_Namecr(String ward_Namecr) {
		this.ward_Namecr = ward_Namecr;
	}

	public int getWard_version() {
		return ward_version;
	}

	public void setWard_version(int ward_version) {
		this.ward_version = ward_version;
	}

	public boolean isCorrection() {
		return correction;
	}

	public void setCorrection(boolean correction) {
		this.correction = correction;
	}

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrdereffectiveDate() {
		return ordereffectiveDate;
	}

	public void setOrdereffectiveDate(Date ordereffectiveDate) {
		this.ordereffectiveDate = ordereffectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	public String getOrderPath() {
		return orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}

	public int getWard_code() {
		return ward_code;
	}

	public void setWard_code(int ward_code) {
		this.ward_code = ward_code;
	}

	public String getWard_number() {
		return ward_number;
	}

	public void setWard_number(String ward_number) {
		this.ward_number = ward_number;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	public String getDistrictsourcecode() {
		return districtsourcecode;
	}

	public void setDistrictsourcecode(String districtsourcecode) {
		this.districtsourcecode = districtsourcecode;
	}

	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}

	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}

	public String getContributedParliament() {
		return contributedParliament;
	}

	public void setContributedParliament(String contributedParliament) {
		this.contributedParliament = contributedParliament;
	}

	public String getRurallbTypeName() {
		return rurallbTypeName;
	}

	public void setRurallbTypeName(String rurallbTypeName) {
		this.rurallbTypeName = rurallbTypeName;
	}

	public String getMergeRLBtoTLB() {
		return mergeRLBtoTLB;
	}

	public void setMergeRLBtoTLB(String mergeRLBtoTLB) {
		this.mergeRLBtoTLB = mergeRLBtoTLB;
	}

	public String getDeclarenewTLB() {
		return declarenewTLB;
	}

	public void setDeclarenewTLB(String declarenewTLB) {
		this.declarenewTLB = declarenewTLB;
	}

	public String getTraditionalLbTypeName() {
		return traditionalLbTypeName;
	}

	public void setTraditionalLbTypeName(String traditionalLbTypeName) {
		this.traditionalLbTypeName = traditionalLbTypeName;
	}

	public String getLgd_LBDistrictforExist() {
		return lgd_LBDistrictforExist;
	}
	public void setLgd_LBDistrictforExist(String lgd_LBDistrictforExist) {
		this.lgd_LBDistrictforExist = lgd_LBDistrictforExist;
	}
	public String getLgd_LBDistrictPatInterforExist() {
		return lgd_LBDistrictPatInterforExist;
	}
	public String getLgd_LBVillageCAreaSourceList() {
		return lgd_LBVillageCAreaSourceList;
	}
	public void setLgd_LBVillageCAreaSourceList(String lgd_LBVillageCAreaSourceList) {
		this.lgd_LBVillageCAreaSourceList = lgd_LBVillageCAreaSourceList;
	}
	public void setLgd_LBDistrictPatInterforExist(
			String lgd_LBDistrictPatInterforExist) {
		this.lgd_LBDistrictPatInterforExist = lgd_LBDistrictPatInterforExist;
	}

	public String getContributedAssembly() {
		return contributedAssembly;
	}

	public void setContributedAssembly(String contributedAssembly) {
		this.contributedAssembly = contributedAssembly;
	}
	public String getRdoSubdistrictDelete() {
		return rdoSubdistrictDelete;
	}
	public String getDdLgdLBDistPList() {
		return ddLgdLBDistPList;
	}

	public void setDdLgdLBDistPList(String ddLgdLBDistPList) {
		this.ddLgdLBDistPList = ddLgdLBDistPList;
	}

	public void setRdoSubdistrictDelete(String rdoSubdistrictDelete) {
		this.rdoSubdistrictDelete = rdoSubdistrictDelete;
	}


	public int getMap_code() {
		return map_code;
	}

	public void setMap_code(int map_code) {
		this.map_code = map_code;
	}

	public String getLb_lgdLocalBodyType() {
		return lb_lgdLocalBodyType;
	}

	public void setLb_lgdLocalBodyType(String lb_lgdLocalBodyType) {
		this.lb_lgdLocalBodyType = lb_lgdLocalBodyType;
	}


	public String getDdStateParliamnetSource() {
		return ddStateParliamnetSource;
	}

	public void setDdStateParliamnetSource(String ddStateParliamnetSource) {
		this.ddStateParliamnetSource = ddStateParliamnetSource;
	}




	public String getLgd_LBDistCAreaforintermediate() {
		return lgd_LBDistCAreaforintermediate;
	}

	public void setLgd_LBDistCAreaforintermediate(
			String lgd_LBDistCAreaforintermediate) {
		this.lgd_LBDistCAreaforintermediate = lgd_LBDistCAreaforintermediate;
	}

	public String getLgd_LBintermedaitelist() {
		return lgd_LBintermedaitelist;
	}

	public String getDdLgdLBVillageDestLatDCA() {
		return ddLgdLBVillageDestLatDCA;
	}

	public void setDdLgdLBVillageDestLatDCA(String ddLgdLBVillageDestLatDCA) {
		this.ddLgdLBVillageDestLatDCA = ddLgdLBVillageDestLatDCA;
	}

	public void setLgd_LBintermedaitelist(String lgd_LBintermedaitelist) {
		this.lgd_LBintermedaitelist = lgd_LBintermedaitelist;
	}

	public String getLgd_LBDistCArinterList() {
		return lgd_LBDistCArinterList;
	}

	public void setLgd_LBDistCArinterList(String lgd_LBDistCArinterList) {
		this.lgd_LBDistCArinterList = lgd_LBDistCArinterList;
	}

	public String getLgd_LBDistCArea() {
		return lgd_LBDistCArea;
	}

	public void setLgd_LBDistCArea(String lgd_LBDistCArea) {
		this.lgd_LBDistCArea = lgd_LBDistCArea;
	}

	public String getLgd_LBInterPDestListforvillage() {
		return lgd_LBInterPDestListforvillage;
	}

	public void setLgd_LBInterPDestListforvillage(
			String lgd_LBInterPDestListforvillage) {
		this.lgd_LBInterPDestListforvillage = lgd_LBInterPDestListforvillage;
	}

	public String getLgd_LBSubDistrictDestLatDCAforvillage() {
		return lgd_LBSubDistrictDestLatDCAforvillage;
	}

	public void setLgd_LBSubDistrictDestLatDCAforvillage(
			String lgd_LBSubDistrictDestLatDCAforvillage) {
		this.lgd_LBSubDistrictDestLatDCAforvillage = lgd_LBSubDistrictDestLatDCAforvillage;
	}

	public String getLgd_LBWardCArea() {
		return lgd_LBWardCArea;
	}

	public void setLgd_LBWardCArea(String lgd_LBWardCArea) {
		this.lgd_LBWardCArea = lgd_LBWardCArea;
	}

	public String getLgd_LBwardDestLatDCA() {
		return lgd_LBwardDestLatDCA;
	}

	public void setLgd_LBwardDestLatDCA(String lgd_LBwardDestLatDCA) {
		this.lgd_LBwardDestLatDCA = lgd_LBwardDestLatDCA;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getFileMapUpLoad() {
		return fileMapUpLoad;
	}

	public void setFileMapUpLoad(String fileMapUpLoad) {
		this.fileMapUpLoad = fileMapUpLoad;
	}

	

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getRequiredTitle() {
		return requiredTitle;
	}

	public void setRequiredTitle(String requiredTitle) {
		this.requiredTitle = requiredTitle;
	}

	public String getUniqueTitle() {
		return uniqueTitle;
	}

	public void setUniqueTitle(String uniqueTitle) {
		this.uniqueTitle = uniqueTitle;
	}

	public int getAllowedNoOfAttachment() {
		return allowedNoOfAttachment;
	}

	public void setAllowedNoOfAttachment(int allowedNoOfAttachment) {
		this.allowedNoOfAttachment = allowedNoOfAttachment;
	}

	public long getAllowedTotalFileSize() {
		return allowedTotalFileSize;
	}

	public void setAllowedTotalFileSize(long allowedTotalFileSize) {
		this.allowedTotalFileSize = allowedTotalFileSize;
	}

	public long getAllowedIndividualFileSize() {
		return allowedIndividualFileSize;
	}

	public void setAllowedIndividualFileSize(long allowedIndividualFileSize) {
		this.allowedIndividualFileSize = allowedIndividualFileSize;
	}

	public String getAllowedFileType() {
		return allowedFileType;
	}

	public void setAllowedFileType(String allowedFileType) {
		this.allowedFileType = allowedFileType;
	}

	public String getUploadLocation() {
		return uploadLocation;
	}

	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public List<CommonsMultipartFile> getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(List<CommonsMultipartFile> attachedFile) {
		this.attachedFile = attachedFile;
	}

	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}

	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}

	public int getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}

	public String getTemplateList() {
		return templateList;
	}

	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}

	public String getNewAddLocalBody() {
		return newAddLocalBody;
	}

	public void setNewAddLocalBody(String newAddLocalBody) {
		this.newAddLocalBody = newAddLocalBody;
	}

	public List<LocalbodyforStateWise> getLocalBodyTypelist() {
		return localBodyTypelist;
	}

	public void setLocalBodyTypelist(List<LocalbodyforStateWise> localBodyTypelist) {
		this.localBodyTypelist = localBodyTypelist;
	}

	public List<LocalbodyListbyState> getDistrictPanchayatList() {
		return districtPanchayatList;
	}

	public void setDistrictPanchayatList(
			List<LocalbodyListbyState> districtPanchayatList) {
		this.districtPanchayatList = districtPanchayatList;
	}

	public Integer getMap_attachment_code() {
		return Map_attachment_code;
	}

	public void setMap_attachment_code(Integer map_attachment_code) {
		Map_attachment_code = map_attachment_code;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public Integer getLocalBodyVersion() {
		return localBodyVersion;
	}

	public void setLocalBodyVersion(Integer localBodyVersion) {
		this.localBodyVersion = localBodyVersion;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	public boolean isIsdisturbed() {
		return isdisturbed;
	}

	public void setIsdisturbed(boolean isdisturbed) {
		this.isdisturbed = isdisturbed;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public boolean isWarningflag() {
		return warningflag;
	}

	public void setWarningflag(boolean warningflag) {
		this.warningflag = warningflag;
	}

	public Integer getParentLocalBodyCode() {
		return parentLocalBodyCode;
	}

	public void setParentLocalBodyCode(Integer parentLocalBodyCode) {
		this.parentLocalBodyCode = parentLocalBodyCode;
	}

	public Integer getParent_lblc() {
		return parent_lblc;
	}

	public void setParent_lblc(Integer parent_lblc) {
		this.parent_lblc = parent_lblc;
	}

	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}

	public List<LocalbodyListbyState> getViewlocalbodyDetails() {
		return viewlocalbodyDetails;
	}

	public void setViewlocalbodyDetails(List<LocalbodyListbyState> viewlocalbodyDetails) {
		this.viewlocalbodyDetails = viewlocalbodyDetails;
	}

	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<Localbody> getLocalbodyDetails() {
		return localbodyDetails;
	}

	public void setLocalbodyDetails(List<Localbody> localbodyDetails) {
		this.localbodyDetails = localbodyDetails;
	}

	@Override
	public String toString() {
		return "LocalGovtBodyForm [lbType=" + lbType + ", localBodyCode=" + localBodyCode + ", lgd_LBNameInEn=" + lgd_LBNameInEn + ", lgd_LBNameInLocal="
				+ lgd_LBNameInLocal + ", lgd_LBAliasInEn=" + lgd_LBAliasInEn + ", lgd_LBAliasInLocal=" + lgd_LBAliasInLocal + ", orderCode=" + orderCode
				+ ", orderNo=" + orderNo + ", orderDate=" + orderDate + ", ordereffectiveDate=" + ordereffectiveDate + ", gazPubDate=" + gazPubDate
				+ ", orderPath=" + orderPath + "]";
	}
	
	private List<LocalBodyHistory> localBodyHistoryList;



	public List<LocalBodyHistory> getLocalBodyHistoryList() {
		return localBodyHistoryList;
	}

	public void setLocalBodyHistoryList(List<LocalBodyHistory> localBodyHistoryList) {
		this.localBodyHistoryList = localBodyHistoryList;
	}

	public String getLbTypeHierarchy() {
		return lbTypeHierarchy;
	}

	public void setLbTypeHierarchy(String lbTypeHierarchy) {
		this.lbTypeHierarchy = lbTypeHierarchy;
	}

	public String getParamLocalBodyTypeCode() {
		return paramLocalBodyTypeCode;
	}

	public void setParamLocalBodyTypeCode(String paramLocalBodyTypeCode) {
		this.paramLocalBodyTypeCode = paramLocalBodyTypeCode;
	}

	public String getLocalBodyLevelCodes() {
		return localBodyLevelCodes;
	}

	public void setLocalBodyLevelCodes(String localBodyLevelCodes) {
		this.localBodyLevelCodes = localBodyLevelCodes;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDeleteMap() {
		return deleteMap;
	}

	public void setDeleteMap(String deleteMap) {
		this.deleteMap = deleteMap;
	}

	public Integer getCcCodeMap() {
		return ccCodeMap;
	}

	public void setCcCodeMap(Integer ccCodeMap) {
		this.ccCodeMap = ccCodeMap;
	}

	public Date getiParamEffectiveDate() {
		return iParamEffectiveDate;
	}

	public void setiParamEffectiveDate(Date iParamEffectiveDate) {
		this.iParamEffectiveDate = iParamEffectiveDate;
	}

	public List<CommonsMultipartFile> getGazettePublicationUpload() {
		return gazettePublicationUpload;
	}

	public void setGazettePublicationUpload(List<CommonsMultipartFile> gazettePublicationUpload) {
		this.gazettePublicationUpload = gazettePublicationUpload;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getSubDistrictCode() {
		return subDistrictCode;
	}

	public void setSubDistrictCode(String subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	public Integer getLbTypeCode() {
		return lbTypeCode;
	}

	public void setLbTypeCode(Integer lbTypeCode) {
		this.lbTypeCode = lbTypeCode;
	}

	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	public boolean isUnResolvedFlag() {
		return unResolvedFlag;
	}

	public void setUnResolvedFlag(boolean unResolvedFlag) {
		this.unResolvedFlag = unResolvedFlag;
	}


	


	
}