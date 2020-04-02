package in.nic.pes.lgd.forms;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ConvertRLBtoULBForm {

	private String districtLocalBodies;
	private String districtLocalBodyCode;
	private String intermediateLocalBodies;
	private String interLocalBodyCode;
	private String urbanLgTypeName;
	private String urbanLgTypeNameNew;
	private String villagelocalbodyNameSource;
	private String villagelocalbodyNameDest;

	private String urbanlocalBodyNameEnglish;

	private String localBodyNameEnglishAvailDest;
	private String localBodyNameEnglishAvailSource;

	private String hdnUrbanLgTypeName;
	private String hdnUrbanLgName;
	private Integer local_body_version;

	private String hdnState;

	private String localBodyNameInEn;
	private String localBodyNameInLcl;
	private String localBodyliasInEn;
	private String localBodyliasInLcl;

	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date effectiveDate;
	private Date gazPubDate;
	private MultipartFile filePath;

	private String orderPath;
	private int operationCode;
	private String govtOrderConfig;
	private char lbType;
	private String templateList;

	private String localBodyLandRegionAreaSource;
	private String localBodyLandRegionAreaDest;

	private String mergeRLBtoULB;
	private String declarenewULB;
	private boolean mergeUlbClick;
	private boolean declareUlbClick;
	private String coverage;
	
	private String upgradeType = null;
	
	private char lbFull;

	public String getHdnUrbanLgTypeName() {
		return hdnUrbanLgTypeName;
	}

	public void setHdnUrbanLgTypeName(String hdnUrbanLgTypeName) {
		this.hdnUrbanLgTypeName = hdnUrbanLgTypeName;
	}

	public String getHdnUrbanLgName() {
		return hdnUrbanLgName;
	}

	public void setHdnUrbanLgName(String hdnUrbanLgName) {
		this.hdnUrbanLgName = hdnUrbanLgName;
	}

	public String getDistrictLocalBodyCode() {
		return districtLocalBodyCode;
	}

	public void setDistrictLocalBodyCode(String districtLocalBodyCode) {
		this.districtLocalBodyCode = districtLocalBodyCode;
	}

	public String getInterLocalBodyCode() {
		return interLocalBodyCode;
	}

	public void setInterLocalBodyCode(String interLocalBodyCode) {
		this.interLocalBodyCode = interLocalBodyCode;
	}

	public boolean isMergeUlbClick() {
		return mergeUlbClick;
	}

	public void setMergeUlbClick(boolean mergeUlbClick) {
		this.mergeUlbClick = mergeUlbClick;
	}

	public boolean isDeclareUlbClick() {
		return declareUlbClick;
	}

	public void setDeclareUlbClick(boolean declareUlbClick) {
		this.declareUlbClick = declareUlbClick;
	}

	public String getHdnState() {
		return hdnState;
	}

	public void setHdnState(String hdnState) {
		this.hdnState = hdnState;
	}

	public String getTemplateList() {
		return templateList;
	}

	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}

	public String getOrderPath() {
		return orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}

	public int getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}

	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}

	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}

	public char getLbType() {
		return lbType;
	}

	public void setLbType(char lbType) {
		this.lbType = lbType;
	}

	public String getLocalBodyliasInLcl() {
		return localBodyliasInLcl;
	}

	public void setLocalBodyliasInLcl(String localBodyliasInLcl) {
		this.localBodyliasInLcl = localBodyliasInLcl;
	}

	public String getUrbanLgTypeNameNew() {
		return urbanLgTypeNameNew;
	}

	public void setUrbanLgTypeNameNew(String urbanLgTypeNameNew) {
		this.urbanLgTypeNameNew = urbanLgTypeNameNew;
	}

	public String getVillagelocalbodyNameSource() {
		return villagelocalbodyNameSource;
	}

	public void setVillagelocalbodyNameSource(String villagelocalbodyNameSource) {
		this.villagelocalbodyNameSource = villagelocalbodyNameSource;
	}

	public String getVillagelocalbodyNameDest() {
		return villagelocalbodyNameDest;
	}

	public void setVillagelocalbodyNameDest(String villagelocalbodyNameDest) {
		this.villagelocalbodyNameDest = villagelocalbodyNameDest;
	}

	public String getDistrictLocalBodies() {
		return districtLocalBodies;
	}

	public void setDistrictLocalBodies(String districtLocalBodies) {
		this.districtLocalBodies = districtLocalBodies;
	}

	public String getIntermediateLocalBodies() {
		return intermediateLocalBodies;
	}

	public void setIntermediateLocalBodies(String intermediateLocalBodies) {
		this.intermediateLocalBodies = intermediateLocalBodies;
	}

	public String getUrbanLgTypeName() {
		return urbanLgTypeName;
	}

	public void setUrbanLgTypeName(String urbanLgTypeName) {
		this.urbanLgTypeName = urbanLgTypeName;
	}

	public String getLocalBodyNameEnglishAvailDest() {
		return localBodyNameEnglishAvailDest;
	}

	public void setLocalBodyNameEnglishAvailDest(String localBodyNameEnglishAvailDest) {
		this.localBodyNameEnglishAvailDest = localBodyNameEnglishAvailDest;
	}

	public String getLocalBodyNameEnglishAvailSource() {
		return localBodyNameEnglishAvailSource;
	}

	public void setLocalBodyNameEnglishAvailSource(String localBodyNameEnglishAvailSource) {
		this.localBodyNameEnglishAvailSource = localBodyNameEnglishAvailSource;
	}

	public String getLocalBodyLandRegionAreaSource() {
		return localBodyLandRegionAreaSource;
	}

	public void setLocalBodyLandRegionAreaSource(String localBodyLandRegionAreaSource) {
		this.localBodyLandRegionAreaSource = localBodyLandRegionAreaSource;
	}

	public String getLocalBodyLandRegionAreaDest() {
		return localBodyLandRegionAreaDest;
	}

	public void setLocalBodyLandRegionAreaDest(String localBodyLandRegionAreaDest) {
		this.localBodyLandRegionAreaDest = localBodyLandRegionAreaDest;
	}

	public String getMergeRLBtoULB() {
		return mergeRLBtoULB;
	}

	public void setMergeRLBtoULB(String mergeRLBtoULB) {
		this.mergeRLBtoULB = mergeRLBtoULB;
	}

	public String getDeclarenewULB() {
		return declarenewULB;
	}

	public void setDeclarenewULB(String declarenewULB) {
		this.declarenewULB = declarenewULB;
	}

	public String getUrbanlocalBodyNameEnglish() {
		return urbanlocalBodyNameEnglish;
	}

	public void setUrbanlocalBodyNameEnglish(String urbanlocalBodyNameEnglish) {
		this.urbanlocalBodyNameEnglish = urbanlocalBodyNameEnglish;
	}

	public String getLocalBodyNameInEn() {
		return localBodyNameInEn;
	}

	public void setLocalBodyNameInEn(String localBodyNameInEn) {
		this.localBodyNameInEn = localBodyNameInEn;
	}

	public String getLocalBodyNameInLcl() {
		return localBodyNameInLcl;
	}

	public void setLocalBodyNameInLcl(String localBodyNameInLcl) {
		this.localBodyNameInLcl = localBodyNameInLcl;
	}

	public String getLocalBodyliasInEn() {
		return localBodyliasInEn;
	}

	public void setLocalBodyliasInEn(String localBodyliasInEn) {
		this.localBodyliasInEn = localBodyliasInEn;
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

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}

	public Integer getLocal_body_version() {
		return local_body_version;
	}

	public void setLocal_body_version(Integer local_body_version) {
		this.local_body_version = local_body_version;
	}

	public final String getUpgradeType() {
		return upgradeType;
	}

	public final void setUpgradeType(String upgradeType) {
		this.upgradeType = upgradeType;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public char getLbFull() {
		return lbFull;
	}

	public void setLbFull(char lbFull) {
		this.lbFull = lbFull;
	}
	
	

}
