package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.StatePK;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;



public class ShiftVillageForm {
	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date effectiveDate;
	private Date gazPubDate;
	private String orderPath;
	private StatePK statePK;
	private int stateVersion;
	private int stateCode;

	private String villageNameEnglish;
	private String stateNameEnglish;
	private String districtNameEnglish;
	private String subdistrictNameEnglish;
	private MultipartFile filePath;
	private String blockNameEnglish;

	private char operation;
	private String govtOrderConfig;	
	private int operationCode;
	private String templateList;
	
	private String districtSName;
	private String blockSName;
	private String blockDName;
	private String blockNameEnglishSource;
	private String blockNameEnglishDest;
	private String villageName;
	
	private String stateSName;
	private String stateTName;
	private int districtCode=0;
	
	public int getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}

	public String getStateTName() {
		return stateTName;
	}

	public void setStateTName(String stateTName) {
		this.stateTName = stateTName;
	}
	private String districtDName;	
	private String subdistrictSName;
	private String subdistrictDName;
	private String districtNameEnglishSource;
	private String districtNameEnglishDest;
	private String subdistrictNameEnglishSource;
	private String subdistrictNameEnglishDest;
	
	private String subdistrictNameEnglishTarget;
	
	private String finalsourceDistName;
	private String finaltargetDistName;
	private String finalsourceSubdistName;
	private String finalVillageName;
	private String finalsourceBlockName;
	private String finalsourceStateName;
	private String finaltargetStateName;
	public String getFinalsourceStateName() {
		return finalsourceStateName;
	}

	public void setFinalsourceStateName(String finalsourceStateName) {
		this.finalsourceStateName = finalsourceStateName;
	}

	public String getFinaltargetStateName() {
		return finaltargetStateName;
	}

	public void setFinaltargetStateName(String finaltargetStateName) {
		this.finaltargetStateName = finaltargetStateName;
	}

	public String getFinalsourceBlockName() {
		return finalsourceBlockName;
	}

	public void setFinalsourceBlockName(String finalsourceBlockName) {
		this.finalsourceBlockName = finalsourceBlockName;
	}

	public String getFinaltargetBlockName() {
		return finaltargetBlockName;
	}

	public void setFinaltargetBlockName(String finaltargetBlockName) {
		this.finaltargetBlockName = finaltargetBlockName;
	}
	private String finaltargetBlockName;
	
	public String getFinalVillageName() {
		return finalVillageName;
	}

	public void setFinalVillageName(String finalVillageName) {
		this.finalVillageName = finalVillageName;
	}

	public String getFinalsourceDistName() {
		return finalsourceDistName;
	}

	public void setFinalsourceDistName(String finalsourceDistName) {
		this.finalsourceDistName = finalsourceDistName;
	}

	public String getFinaltargetDistName() {
		return finaltargetDistName;
	}

	public void setFinaltargetDistName(String finaltargetDistName) {
		this.finaltargetDistName = finaltargetDistName;
	}

	public String getFinalsourceSubdistName() {
		return finalsourceSubdistName;
	}

	public void setFinalsourceSubdistName(String finalsourceSubdistName) {
		this.finalsourceSubdistName = finalsourceSubdistName;
	}

	public String getFinaltargetSubdistName() {
		return finaltargetSubdistName;
	}

	public void setFinaltargetSubdistName(String finaltargetSubdistName) {
		this.finaltargetSubdistName = finaltargetSubdistName;
	}
	private String finaltargetSubdistName;
	
	public String getSubdistrictNameEnglishTarget() {
		return subdistrictNameEnglishTarget;
	}

	public void setSubdistrictNameEnglishTarget(String subdistrictNameEnglishTarget) {
		this.subdistrictNameEnglishTarget = subdistrictNameEnglishTarget;
	}

	public String getSubdistrictDName() {
		return subdistrictDName;
	}

	public void setSubdistrictDName(String subdistrictDName) {
		this.subdistrictDName = subdistrictDName;
	}

	public String getDistrictNameEnglishSource() {
		return districtNameEnglishSource;
	}

	public void setDistrictNameEnglishSource(String districtNameEnglishSource) {
		this.districtNameEnglishSource = districtNameEnglishSource;
	}

	public String getDistrictNameEnglishDest() {
		return districtNameEnglishDest;
	}

	public void setDistrictNameEnglishDest(String districtNameEnglishDest) {
		this.districtNameEnglishDest = districtNameEnglishDest;
	}

	public String getSubdistrictNameEnglishSource() {
		return subdistrictNameEnglishSource;
	}

	public void setSubdistrictNameEnglishSource(String subdistrictNameEnglishSource) {
		this.subdistrictNameEnglishSource = subdistrictNameEnglishSource;
	}

	public String getSubdistrictNameEnglishDest() {
		return subdistrictNameEnglishDest;
	}

	public void setSubdistrictNameEnglishDest(String subdistrictNameEnglishDest) {
		this.subdistrictNameEnglishDest = subdistrictNameEnglishDest;
	}

	public String getStateSName() {
		return stateSName;
	}

	public void setStateSName(String stateSName) {
		this.stateSName = stateSName;
	}

	public String getDistrictDName() {
		return districtDName;
	}

	public void setDistrictDName(String districtDName) {
		this.districtDName = districtDName;
	}

	public String getSubdistrictSName() {
		return subdistrictSName;
	}

	public void setSubdistrictSName(String subdistrictSName) {
		this.subdistrictSName = subdistrictSName;
	}

	
	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getDistrictSName() {
		return districtSName;
	}

	public void setDistrictSName(String districtSName) {
		this.districtSName = districtSName;
	}

	public String getBlockSName() {
		return blockSName;
	}

	public void setBlockSName(String blockSName) {
		this.blockSName = blockSName;
	}

	public String getBlockDName() {
		return blockDName;
	}

	public void setBlockDName(String blockDName) {
		this.blockDName = blockDName;
	}

	public String getBlockNameEnglishSource() {
		return blockNameEnglishSource;
	}

	public void setBlockNameEnglishSource(String blockNameEnglishSource) {
		this.blockNameEnglishSource = blockNameEnglishSource;
	}

	public String getBlockNameEnglishDest() {
		return blockNameEnglishDest;
	}

	public void setBlockNameEnglishDest(String blockNameEnglishDest) {
		this.blockNameEnglishDest = blockNameEnglishDest;
	}

	public char getOperation() {
		return operation;
	}

	public void setOperation(char operation) {
		this.operation = operation;
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


	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}
	

	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}
	public StatePK getStatePK() {
		return statePK;
	}
	public void setStatePK(StatePK statePK) {
		this.statePK = statePK;
	}
	public int getStateVersion() {
		return stateVersion;
	}
	public void setStateVersion(int stateVersion) {
		this.stateVersion = stateVersion;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
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
	public String getOrderPath() {
		return orderPath;
	}
	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}
	

	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}
	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}
	
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}
	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}
	
}
