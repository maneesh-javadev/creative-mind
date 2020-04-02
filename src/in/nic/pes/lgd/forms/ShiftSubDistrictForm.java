package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.SubdistrictPK;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;



public class ShiftSubDistrictForm 
{

		private Integer orderCode;
		private String orderNo;
		private Date orderDate;
		private Date effectiveDate;
		private Date gazPubDate;
		private String orderPath;
		private StatePK statePK;
		private int stateVersion;
		private int stateCode;
		private String stateNameEnglish;
		private String stateNameEnglishSource;
		private String stateNameEnglishTarget;
		private DistrictPK districtPK;
		private int districtCode;
		private int districtVersion;
		private String districtNameEnglish;	
		private String districtNameEnglishSource;	
		private String districtNameEnglishTarget;	
		private String subdistrictNameEnglish;
		private SubdistrictPK subdistrictPK;
		private int subdistrictCode;
		private int subdistrictVersion;
		private MultipartFile filePath;

		private String govtOrderConfig;	
		private int operationCode;
		private String templateList;
		private String stateSName;
		private String stateDName;
		private String districtSName;
		private String districtDName;
		private String subDistrictName;
		private String finalsourceDistName;
		private String finaltargetDistName;
		private String finalSubdistName;
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

		public String getFinalSubdistName() {
			return finalSubdistName;
		}

		public void setFinalSubdistName(String finalSubdistName) {
			this.finalSubdistName = finalSubdistName;
		}

		public String getSubDistrictName() {
			return subDistrictName;
		}

		public void setSubDistrictName(String subDistrictName) {
			this.subDistrictName = subDistrictName;
		}

		public String getStateSName() {
			return stateSName;
		}

		public void setStateSName(String stateSName) {
			this.stateSName = stateSName;
		}

		public String getStateDName() {
			return stateDName;
		}

		public void setStateDName(String stateDName) {
			this.stateDName = stateDName;
		}

		public String getDistrictSName() {
			return districtSName;
		}

		public void setDistrictSName(String districtSName) {
			this.districtSName = districtSName;
		}

		public String getDistrictDName() {
			return districtDName;
		}

		public void setDistrictDName(String districtDName) {
			this.districtDName = districtDName;
		}

		public String getStateNameEnglishSource() {
			return stateNameEnglishSource;
		}

		public void setStateNameEnglishSource(String stateNameEnglishSource) {
			this.stateNameEnglishSource = stateNameEnglishSource;
		}

		public String getStateNameEnglishTarget() {
			return stateNameEnglishTarget;
		}

		public void setStateNameEnglishTarget(String stateNameEnglishTarget) {
			this.stateNameEnglishTarget = stateNameEnglishTarget;
		}

		public String getDistrictNameEnglishSource() {
			return districtNameEnglishSource;
		}

		public void setDistrictNameEnglishSource(String districtNameEnglishSource) {
			this.districtNameEnglishSource = districtNameEnglishSource;
		}

		public String getDistrictNameEnglishTarget() {
			return districtNameEnglishTarget;
		}

		public void setDistrictNameEnglishTarget(String districtNameEnglishTarget) {
			this.districtNameEnglishTarget = districtNameEnglishTarget;
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

		public String getTemplateList() {
			return templateList;
		}

		public void setTemplateList(String templateList) {
			this.templateList = templateList;
		}

		public MultipartFile getFilePath() {
			return filePath;
		}

		public void setFilePath(MultipartFile filePath) {
			this.filePath = filePath;
		}

		public String getSubdistrictNameEnglish() {
			return subdistrictNameEnglish;
		}

		public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
			this.subdistrictNameEnglish = subdistrictNameEnglish;
		}

		public SubdistrictPK getSubdistrictPK() {
			return subdistrictPK;
		}

		public void setSubdistrictPK(SubdistrictPK subdistrictPK) {
			this.subdistrictPK = subdistrictPK;
		}

		public int getSubdistrictCode() {
			return subdistrictCode;
		}

		public void setSubdistrictCode(int subdistrictCode) {
			this.subdistrictCode = subdistrictCode;
		}

		public int getSubdistrictVersion() {
			return subdistrictVersion;
		}

		public void setSubdistrictVersion(int subdistrictVersion) {
			this.subdistrictVersion = subdistrictVersion;
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

		public String getStateNameEnglish() {
			return stateNameEnglish;
		}

		public void setStateNameEnglish(String stateNameEnglish) {
			this.stateNameEnglish = stateNameEnglish;
		}

		public DistrictPK getDistrictPK() {
			return districtPK;
		}

		public void setDistrictPK(DistrictPK districtPK) {
			this.districtPK = districtPK;
		}

		public int getDistrictCode() {
			return districtCode;
		}

		public void setDistrictCode(int districtCode) {
			this.districtCode = districtCode;
		}

		public int getDistrictVersion() {
			return districtVersion;
		}

		public void setDistrictVersion(int districtVersion) {
			this.districtVersion = districtVersion;
		}

		public String getDistrictNameEnglish() {
			return districtNameEnglish;
		}

		public void setDistrictNameEnglish(String districtNameEnglish) {
			this.districtNameEnglish = districtNameEnglish;
		}


	}
