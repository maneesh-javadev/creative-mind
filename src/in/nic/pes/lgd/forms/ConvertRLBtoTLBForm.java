package in.nic.pes.lgd.forms;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


public class ConvertRLBtoTLBForm {
	
	private String rurallbTypeName;
	
	private String lgd_LBNomenclatureDist;
	private String lgd_LBNomenclatureInter;
	private String lgd_LBNomenclatureVillage;
	
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
	
			
	private String lgd_LBVillageSourceAtVillageCA;
	
	private String lgd_LBDistrictforExistNew;
	
	public String getLgd_LBDistrictforExistNew() {
		return lgd_LBDistrictforExistNew;
	}
	public void setLgd_LBDistrictforExistNew(String lgd_LBDistrictforExistNew) {
		this.lgd_LBDistrictforExistNew = lgd_LBDistrictforExistNew;
	}


	private String lgd_LBVillageDestLatICA;
	private String lgd_LBVillageSourceLatICA;
	
	private String lgd_LBSubDistrictSourceLatDCA;
	private String lgd_LBSubDistrictDestLatDCA;
	private String lgd_LBVillageSourceLatDCA;
	private String lgd_LBVillageDestLatDCA;
	
	private String lgd_LBNomenclatureDistTrade;
	private String lgd_LBNomenclatureInterTrade;
	private String lgd_LBNomenclatureVillageTrade;
	
	private String contDPForPRI;
  	private String contIPforPRI;
  	private String contVPforPRI;
  	private String contVillageList;
  	private String contPrilbtype;
  	private String contTradlbtypeNew;
  	private String contTradlbtypeMerged;
  	private String contDPForTradMerged;
  	public String getContDPForTradMerged() {
		return contDPForTradMerged;
	}
	public void setContDPForTradMerged(String contDPForTradMerged) {
		this.contDPForTradMerged = contDPForTradMerged;
	}
	public String getContIPforTradMerged() {
		return contIPforTradMerged;
	}
	public void setContIPforTradMerged(String contIPforTradMerged) {
		this.contIPforTradMerged = contIPforTradMerged;
	}
	public String getContVPforTradMerged() {
		return contVPforTradMerged;
	}
	public void setContVPforTradMerged(String contVPforTradMerged) {
		this.contVPforTradMerged = contVPforTradMerged;
	}


	private String contIPforTradMerged;
  	private String contVPforTradMerged;
  	
  	public String getContTradlbtypeMerged() {
		return contTradlbtypeMerged;
	}
	public void setContTradlbtypeMerged(String contTradlbtypeMerged) {
		this.contTradlbtypeMerged = contTradlbtypeMerged;
	}


	private String contDPForTrad;
  	private String contIPforTrad;
  	private String contVPforTrad;
  	
	
	public String getContDPForTrad() {
		return contDPForTrad;
	}
	public void setContDPForTrad(String contDPForTrad) {
		this.contDPForTrad = contDPForTrad;
	}
	public String getContIPforTrad() {
		return contIPforTrad;
	}
	public void setContIPforTrad(String contIPforTrad) {
		this.contIPforTrad = contIPforTrad;
	}
	public String getContVPforTrad() {
		return contVPforTrad;
	}
	public void setContVPforTrad(String contVPforTrad) {
		this.contVPforTrad = contVPforTrad;
	}
	public String getContTradlbtypeNew() {
		return contTradlbtypeNew;
	}
	public void setContTradlbtypeNew(String contTradlbtypeNew) {
		this.contTradlbtypeNew = contTradlbtypeNew;
	}
	public String getContPrilbtype() {
		return contPrilbtype;
	}
	public void setContPrilbtype(String contPrilbtype) {
		this.contPrilbtype = contPrilbtype;
	}
	public String getContVillageList() {
		return contVillageList;
	}
	public void setContVillageList(String contVillageList) {
		this.contVillageList = contVillageList;
	}
	public String getContDPForPRI() {
		return contDPForPRI;
	}
	public void setContDPForPRI(String contDPForPRI) {
		this.contDPForPRI = contDPForPRI;
	}
	public String getContIPforPRI() {
		return contIPforPRI;
	}
	public void setContIPforPRI(String contIPforPRI) {
		this.contIPforPRI = contIPforPRI;
	}
	public String getContVPforPRI() {
		return contVPforPRI;
	}
	public void setContVPforPRI(String contVPforPRI) {
		this.contVPforPRI = contVPforPRI;
	}


	private char lbType;
	
	public char getLbType() {
		return lbType;
	}
	public void setLbType(char lbType) {
		this.lbType = lbType;
	}


	private String govtOrderConfig;
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


	private int operationCode;
	
	public String getLgd_LBNomenclatureDistTrade() {
		return lgd_LBNomenclatureDistTrade;
	}
	public void setLgd_LBNomenclatureDistTrade(String lgd_LBNomenclatureDistTrade) {
		this.lgd_LBNomenclatureDistTrade = lgd_LBNomenclatureDistTrade;
	}
	public String getLgd_LBNomenclatureInterTrade() {
		return lgd_LBNomenclatureInterTrade;
	}
	public void setLgd_LBNomenclatureInterTrade(String lgd_LBNomenclatureInterTrade) {
		this.lgd_LBNomenclatureInterTrade = lgd_LBNomenclatureInterTrade;
	}
	public String getLgd_LBNomenclatureVillageTrade() {
		return lgd_LBNomenclatureVillageTrade;
	}
	public void setLgd_LBNomenclatureVillageTrade(
			String lgd_LBNomenclatureVillageTrade) {
		this.lgd_LBNomenclatureVillageTrade = lgd_LBNomenclatureVillageTrade;
	}

	public String getRurallbTypeName() {
		return rurallbTypeName;
	}
	public void setRurallbTypeName(String rurallbTypeName) {
		this.rurallbTypeName = rurallbTypeName;
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


	private String traditionalLbTypeName;
	private String mergeRLBtoTLB;
	private String declarenewTLB;
	
	
	private String localBodyNameInEn;
	private String localBodyNameInLcl;
	private String localBodyAliasInEn;
	private String localBodyAliasInLcl;
	private String traditionalLgTypeNameNew;

	private String traditionalLocalBodyName;
		
	
	
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date effectiveDate;
	private Date gazPubDate;
	private MultipartFile filePath;
	
	private String orderPath;
	
	public String getOrderPath() {
		return orderPath;
	}
	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}


	private String lgd_LBDistrictforExist;
	
	private String lgd_LBDistrictPatInterforExist;
	private String lgd_LBIntermediatePanchaytforExist;
	private String lgd_LBDistrictAtVillageforExist;
	private String lgd_LBIntermediateAtVillageforExist;
	private String lgd_LBVillagePanchaytforExist;
	private String lgd_LBDistrictAtInterforNew;
	private String lgd_LBDistrictAtVillageforNew;
	private String lgd_LBIntermediateAtVillageforNew;




	
	public String getLgd_LBDistrictAtInterforNew() {
		return lgd_LBDistrictAtInterforNew;
	}
	public void setLgd_LBDistrictAtInterforNew(String lgd_LBDistrictAtInterforNew) {
		this.lgd_LBDistrictAtInterforNew = lgd_LBDistrictAtInterforNew;
	}
	public String getLgd_LBDistrictAtVillageforNew() {
		return lgd_LBDistrictAtVillageforNew;
	}
	public void setLgd_LBDistrictAtVillageforNew(
			String lgd_LBDistrictAtVillageforNew) {
		this.lgd_LBDistrictAtVillageforNew = lgd_LBDistrictAtVillageforNew;
	}
	public String getLgd_LBIntermediateAtVillageforNew() {
		return lgd_LBIntermediateAtVillageforNew;
	}
	public void setLgd_LBIntermediateAtVillageforNew(
			String lgd_LBIntermediateAtVillageforNew) {
		this.lgd_LBIntermediateAtVillageforNew = lgd_LBIntermediateAtVillageforNew;
	}
	public String getLgd_LBDistrictPatInterforExist() {
		return lgd_LBDistrictPatInterforExist;
	}
	public void setLgd_LBDistrictPatInterforExist(
			String lgd_LBDistrictPatInterforExist) {
		this.lgd_LBDistrictPatInterforExist = lgd_LBDistrictPatInterforExist;
	}
	public String getLgd_LBIntermediatePanchaytforExist() {
		return lgd_LBIntermediatePanchaytforExist;
	}
	public void setLgd_LBIntermediatePanchaytforExist(
			String lgd_LBIntermediatePanchaytforExist) {
		this.lgd_LBIntermediatePanchaytforExist = lgd_LBIntermediatePanchaytforExist;
	}
	public String getLgd_LBDistrictAtVillageforExist() {
		return lgd_LBDistrictAtVillageforExist;
	}
	public void setLgd_LBDistrictAtVillageforExist(
			String lgd_LBDistrictAtVillageforExist) {
		this.lgd_LBDistrictAtVillageforExist = lgd_LBDistrictAtVillageforExist;
	}
	public String getLgd_LBIntermediateAtVillageforExist() {
		return lgd_LBIntermediateAtVillageforExist;
	}
	public void setLgd_LBIntermediateAtVillageforExist(
			String lgd_LBIntermediateAtVillageforExist) {
		this.lgd_LBIntermediateAtVillageforExist = lgd_LBIntermediateAtVillageforExist;
	}
	public String getLgd_LBVillagePanchaytforExist() {
		return lgd_LBVillagePanchaytforExist;
	}
	public void setLgd_LBVillagePanchaytforExist(
			String lgd_LBVillagePanchaytforExist) {
		this.lgd_LBVillagePanchaytforExist = lgd_LBVillagePanchaytforExist;
	}
	public String getLgd_LBDistrictforExist() {
		return lgd_LBDistrictforExist;
	}
	public void setLgd_LBDistrictforExist(String lgd_LBDistrictforExist) {
		this.lgd_LBDistrictforExist = lgd_LBDistrictforExist;
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

	
	public String getTraditionalLbTypeName() {
		return traditionalLbTypeName;
	}
	public void setTraditionalLbTypeName(String traditionalLbTypeName) {
		this.traditionalLbTypeName = traditionalLbTypeName;
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
	public String getLocalBodyNameInEn() {
		return localBodyNameInEn;
	}
	public void setLocalBodyNameInEn(String localBodyNameInEn) {
		this.localBodyNameInEn = localBodyNameInEn;
	}
	
	public String getLocalBodyAliasInEn() {
		return localBodyAliasInEn;
	}
	public void setLocalBodyAliasInEn(String localBodyAliasInEn) {
		this.localBodyAliasInEn = localBodyAliasInEn;
	}
	public String getLocalBodyAliasInLcl() {
		return localBodyAliasInLcl;
	}
	public void setLocalBodyAliasInLcl(String localBodyAliasInLcl) {
		this.localBodyAliasInLcl = localBodyAliasInLcl;
	}
	public String getTraditionalLgTypeNameNew() {
		return traditionalLgTypeNameNew;
	}
	public void setTraditionalLgTypeNameNew(String traditionalLgTypeNameNew) {
		this.traditionalLgTypeNameNew = traditionalLgTypeNameNew;
	}
	public String getTraditionalLocalBodyName() {
		return traditionalLocalBodyName;
	}
	public void setTraditionalLocalBodyName(String traditionalLocalBodyName) {
		this.traditionalLocalBodyName = traditionalLocalBodyName;
	}
	public String getLocalBodyNameInLcl() {
		return localBodyNameInLcl;
	}
	public void setLocalBodyNameInLcl(String localBodyNameInLcl) {
		this.localBodyNameInLcl = localBodyNameInLcl;
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
	public String getLgd_LBDistListAtVillageCA() {
		return lgd_LBDistListAtVillageCA;
	}
	public void setLgd_LBDistListAtVillageCA(String lgd_LBDistListAtVillageCA) {
		this.lgd_LBDistListAtVillageCA = lgd_LBDistListAtVillageCA;
	}
	public String getLgd_LBInterListAtVillageCA() {
		return lgd_LBInterListAtVillageCA;
	}
	public void setLgd_LBInterListAtVillageCA(String lgd_LBInterListAtVillageCA) {
		this.lgd_LBInterListAtVillageCA = lgd_LBInterListAtVillageCA;
	}
	public String getLgd_LBVillageAtVillageCA() {
		return lgd_LBVillageAtVillageCA;
	}
	public void setLgd_LBVillageAtVillageCA(String lgd_LBVillageAtVillageCA) {
		this.lgd_LBVillageAtVillageCA = lgd_LBVillageAtVillageCA;
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
	public void setLgd_LBVillageDestAtVillageCA(String lgd_LBVillageDestAtVillageCA) {
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
	public String getLgd_LBVillageSourceAtVillageCA() {
		return lgd_LBVillageSourceAtVillageCA;
	}
	public void setLgd_LBVillageSourceAtVillageCA(
			String lgd_LBVillageSourceAtVillageCA) {
		this.lgd_LBVillageSourceAtVillageCA = lgd_LBVillageSourceAtVillageCA;
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
	public void setLgd_LBSubDistrictDestLatDCA(String lgd_LBSubDistrictDestLatDCA) {
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

	

}
