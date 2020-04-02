package in.nic.pes.lgd.bean;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "village_draft")
public class VillageDraft implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "village_code")
	private int villageCode;
	
	private Integer subDistrict;
	private Integer userId;
	private String villageNameEnglish;
	private String orderNo;
	private Timestamp  ordertimeStampDate;
	private Timestamp  effectivetimeStampDate;
	private String govtOrder;
	private Timestamp  gazPubtimeStampDate;
	private String villageNameLocale;
	private String villageNameAliasEn;
	private String villageNameAliasLocal;
	private String villageType;
	private String census;
	private String ssCode;
	private String survayNumber;
	private String gisNodes;
	private String upLoadMap;
	private String formationTypeString;
	private String ulbCodeValid;
	private String fullContributedVillage;
	private String surveyNumberList;
	private String surveyNumberListOld;
	private String renameNameVillageList;
	private String withoutRenameNameVillageList;
	private String renameIdVillageList;
	private Integer operationCode;
	private Integer flagCode;
	private Integer stateCode;
	private Integer districtCode;
	
	private String map_upload_file_name;
	private Long map_upload_file_size;
	private String map_upload_file_content_type;
	private String map_upload_file_location;
	private String map_upload_file_timestamp;
	
	
	private String govt_order_file_name;
	private Long govt_order_file_size;
	private String govt_order_file_content_type;
	private String govt_order_file_location;
	private String govt_order_file_timestamp;
	
	
	
	/**
	 * For Draft Village Functionality
	 * @author Ripunj on 24-09-2014
	 */
	private String isExistGovtOrder;
	private Integer ordercode;
	
	/**
	 * For Draft Rename Village Functionality
	 * @author Ripunj on 04-03-2015
	 */
	private Integer village_rename_code;
	/**
	 * For Draft Invalidate Village Functionality
	 * @author Ripunj on 16-03-2015
	 */
	private String invalidateVillageList;
	
	@Column(name="is_pesa")
	private String isPesa;
	
	@Transient
	private String invalidateVillageListNameEnglish;
	
	@Transient
	private String operationDesc;
	
	public int getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
	}
	public Integer getSubDistrict() {
		return subDistrict;
	}
	public void setSubDistrict(Integer subDistrict) {
		this.subDistrict = subDistrict;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}
	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}
	/*public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}*/
	
	public Timestamp getOrdertimeStampDate() {
		return ordertimeStampDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setOrdertimeStampDate(Timestamp ordertimeStampDate) {
		this.ordertimeStampDate = ordertimeStampDate;
	}
	public Timestamp getEffectivetimeStampDate() {
		return effectivetimeStampDate;
	}
	public void setEffectivetimeStampDate(Timestamp effectivetimeStampDate) {
		this.effectivetimeStampDate = effectivetimeStampDate;
	}
	public String getGovtOrder() {
		return govtOrder;
	}
	public void setGovtOrder(String govtOrder) {
		this.govtOrder = govtOrder;
	}
	public Timestamp getGazPubtimeStampDate() {
		return gazPubtimeStampDate;
	}
	public void setGazPubtimeStampDate(Timestamp gazPubtimeStampDate) {
		this.gazPubtimeStampDate = gazPubtimeStampDate;
	}
	public String getVillageNameLocale() {
		return villageNameLocale;
	}
	public void setVillageNameLocale(String villageNameLocale) {
		this.villageNameLocale = villageNameLocale;
	}
	public String getVillageNameAliasEn() {
		return villageNameAliasEn;
	}
	public void setVillageNameAliasEn(String villageNameAliasEn) {
		this.villageNameAliasEn = villageNameAliasEn;
	}
	public String getVillageNameAliasLocal() {
		return villageNameAliasLocal;
	}
	public void setVillageNameAliasLocal(String villageNameAliasLocal) {
		this.villageNameAliasLocal = villageNameAliasLocal;
	}
	public String getVillageType() {
		return villageType;
	}
	public void setVillageType(String villageType) {
		this.villageType = villageType;
	}
	public String getCensus() {
		return census;
	}
	public void setCensus(String census) {
		this.census = census;
	}
	public String getSsCode() {
		return ssCode;
	}
	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}
	public String getSurvayNumber() {
		return survayNumber;
	}
	public void setSurvayNumber(String survayNumber) {
		this.survayNumber = survayNumber;
	}
	public String getGisNodes() {
		return gisNodes;
	}
	public void setGisNodes(String gisNodes) {
		this.gisNodes = gisNodes;
	}
	public String getUpLoadMap() {
		return upLoadMap;
	}
	public void setUpLoadMap(String upLoadMap) {
		this.upLoadMap = upLoadMap;
	}
	public String getFormationTypeString() {
		return formationTypeString;
	}
	public void setFormationTypeString(String formationTypeString) {
		this.formationTypeString = formationTypeString;
	}
	public String getUlbCodeValid() {
		return ulbCodeValid;
	}
	public void setUlbCodeValid(String ulbCodeValid) {
		this.ulbCodeValid = ulbCodeValid;
	}
	public String getFullContributedVillage() {
		return fullContributedVillage;
	}
	public void setFullContributedVillage(String fullContributedVillage) {
		this.fullContributedVillage = fullContributedVillage;
	}
	public String getSurveyNumberList() {
		return surveyNumberList;
	}
	public void setSurveyNumberList(String surveyNumberList) {
		this.surveyNumberList = surveyNumberList;
	}
	public String getSurveyNumberListOld() {
		return surveyNumberListOld;
	}
	public void setSurveyNumberListOld(String surveyNumberListOld) {
		this.surveyNumberListOld = surveyNumberListOld;
	}
	public String getRenameNameVillageList() {
		return renameNameVillageList;
	}
	public void setRenameNameVillageList(String renameNameVillageList) {
		this.renameNameVillageList = renameNameVillageList;
	}
	public String getWithoutRenameNameVillageList() {
		return withoutRenameNameVillageList;
	}
	public void setWithoutRenameNameVillageList(String withoutRenameNameVillageList) {
		this.withoutRenameNameVillageList = withoutRenameNameVillageList;
	}
	public String getRenameIdVillageList() {
		return renameIdVillageList;
	}
	public void setRenameIdVillageList(String renameIdVillageList) {
		this.renameIdVillageList = renameIdVillageList;
	}
	public Integer getOperationCode() {
		if(operationCode == 14) {
			operationDesc = "Invalidate Village";
		} else if(operationCode == 11) {
			operationDesc = "Change Village";
		} else if(operationCode == 10) {
			operationDesc = "Create Village";
		} else if(operationCode == 3) {
			operationDesc = "Create district";
		} else if(operationCode == 7) {
			operationDesc = "Change Subdistrict";
		}
		return operationCode;
	}
	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}
	public Integer getFlagCode() {
		return flagCode;
	}
	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public Integer getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}
	public String getMap_upload_file_name() {
		return map_upload_file_name;
	}
	public void setMap_upload_file_name(String map_upload_file_name) {
		this.map_upload_file_name = map_upload_file_name;
	}
	public Long getMap_upload_file_size() {
		return map_upload_file_size;
	}
	public void setMap_upload_file_size(Long map_upload_file_size) {
		this.map_upload_file_size = map_upload_file_size;
	}
	public String getMap_upload_file_content_type() {
		return map_upload_file_content_type;
	}
	public void setMap_upload_file_content_type(String map_upload_file_content_type) {
		this.map_upload_file_content_type = map_upload_file_content_type;
	}
	public String getMap_upload_file_location() {
		return map_upload_file_location;
	}
	public void setMap_upload_file_location(String map_upload_file_location) {
		this.map_upload_file_location = map_upload_file_location;
	}
	public String getGovt_order_file_name() {
		return govt_order_file_name;
	}
	public void setGovt_order_file_name(String govt_order_file_name) {
		this.govt_order_file_name = govt_order_file_name;
	}
	public Long getGovt_order_file_size() {
		return govt_order_file_size;
	}
	public void setGovt_order_file_size(Long govt_order_file_size) {
		this.govt_order_file_size = govt_order_file_size;
	}
	public String getGovt_order_file_content_type() {
		return govt_order_file_content_type;
	}
	public void setGovt_order_file_content_type(String govt_order_file_content_type) {
		this.govt_order_file_content_type = govt_order_file_content_type;
	}
	public String getGovt_order_file_location() {
		return govt_order_file_location;
	}
	public void setGovt_order_file_location(String govt_order_file_location) {
		this.govt_order_file_location = govt_order_file_location;
	}
	public String getMap_upload_file_timestamp() {
		return map_upload_file_timestamp;
	}
	public void setMap_upload_file_timestamp(String map_upload_file_timestamp) {
		this.map_upload_file_timestamp = map_upload_file_timestamp;
	}
	public String getGovt_order_file_timestamp() {
		return govt_order_file_timestamp;
	}
	public void setGovt_order_file_timestamp(String govt_order_file_timestamp) {
		this.govt_order_file_timestamp = govt_order_file_timestamp;
	}
	public String getIsExistGovtOrder() {
		return isExistGovtOrder;
	}
	public void setIsExistGovtOrder(String isExistGovtOrder) {
		this.isExistGovtOrder = isExistGovtOrder;
	}
	public Integer getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(Integer ordercode) {
		this.ordercode = ordercode;
	}
	public Integer getVillage_rename_code() {
		return village_rename_code;
	}
	public void setVillage_rename_code(Integer village_rename_code) {
		this.village_rename_code = village_rename_code;
	}
	public String getInvalidateVillageList() {
		return invalidateVillageList;
	}
	public void setInvalidateVillageList(String invalidateVillageList) {
		this.invalidateVillageList = invalidateVillageList;
	}
	
	public String getInvalidateVillageListNameEnglish() {
		return invalidateVillageListNameEnglish;
	}
	public void setInvalidateVillageListNameEnglish(
			String invalidateVillageListNameEnglish) {
		this.invalidateVillageListNameEnglish = invalidateVillageListNameEnglish;
	}
	
	public String getOperationDesc() {
		return operationDesc;
	}
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}
	public String getIsPesa() {
		return isPesa;
	}
	public void setIsPesa(String isPesa) {
		this.isPesa = isPesa;
	}
	
}