/**
 * Save Local Body Freeze/Un-freeze.
 * @author Ashish Dhupia on 15-02-2015
 * 
 */

package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.LBFreezeUnfreeze;
import in.nic.pes.lgd.bean.StandardCodes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class LBFreezeForm implements Serializable{

	 private static final long serialVersionUID = 1L;
	 private String stateCode;
	 private int entity_code;
	 private String entity_name_english;
	 private String entity_name_local;
	 private String entityName;
	 private String districtCode;
	 private String subdistrictCodes;
	 private String intermediatePanchyat;
	 private String disPanchyat;
	 private String Tiertype;
	 private String districtPanchyat;
	 private Integer lblc;
	 private String status;
	 private String allData;
	 private String stateName;
	 private String localbType;
	 private String nomenclature_english;
	 private String local_body_name_english;
	 private String parent_name;
	 private String state_name;
	 
	 /**
	  * Added by ripunj for traditional localbody hierarchy
	  */
	 private String intermediatePanchyattrad;
	 private String disPanchyattrad;
	 private String districtPanchyattrad;
	 
	 private String parentList;
	 
	 private String gtaList;
	 private Integer transactionid;
	 private String param;
	 
	// governmentOrder
		private Integer orderCode;
		private String orderNo;
		private Date orderDate;
		private Date ordereffectiveDate;
		private Date effectiveDate;
		private Date gazPubDate;
		private String orderPath;
		private MultipartFile filePath;
		private String templateList;
		private String govtOrderConfig;
		private char operation;
		private int pageRows;
		private int limit;
		private int offset;
		private int direction;
		private String pageType;
		private String orderNocr;
		private String villageNameEnglishCh;
		private Date orderDatecr;
		private Date ordereffectiveDatecr;
		private Date gazPubDatecr;
		private String orderPathcr;
		private List<StandardCodes> StandardCodes = new ArrayList<StandardCodes>();
		private String gazettePublicationUpload;
	// new
		private String requiredTitle;// Title Required Flag
		private String uniqueTitle;// Unique Title Required Flag
		private int allowedNoOfAttachment;// Allowed Number Of Attachment
		private long allowedTotalFileSize;// Allowed Total File Size
		private long allowedIndividualFileSize;// Allowed Individual File Size.
		private String allowedFileType;// Allowed File Type
		private String uploadLocation;// File Upload Location
		private String showTitle;// Entered Title Value
		private List<CommonsMultipartFile> attachedFile;// Attached File List

		private String requiredTitle1;
		private String maxFileLimit1;
		private String[] fileTitle1;
		private List<CommonsMultipartFile> attachFile1;
		private List<CommonsMultipartFile> attachFile2;// Attached File List
		private String templateBodySrc;
		private List<VillageDataForm> listVillageDetails = new ArrayList<VillageDataForm>();
	// to get ulb & rlb data values
		private String ulbsListData;
		private String rlbsListData;
		
		
		private List<LBFreezeUnfreeze> lbFreezeUnfreezes;
		
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public int getEntity_code() {
		return entity_code;
	}

	public void setEntity_code(int entity_code) {
		this.entity_code = entity_code;
	}

	public String getEntity_name_english() {
		return entity_name_english;
	}

	public void setEntity_name_english(String entity_name_english) {
		this.entity_name_english = entity_name_english;
	}

	public String getEntity_name_local() {
		return entity_name_local;
	}

	public void setEntity_name_local(String entity_name_local) {
		this.entity_name_local = entity_name_local;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getIntermediatePanchyat() {
		return intermediatePanchyat;
	}

	public void setIntermediatePanchyat(String intermediatePanchyat) {
		this.intermediatePanchyat = intermediatePanchyat;
	}

	public String getDisPanchyat() {
		return disPanchyat;
	}

	public void setDisPanchyat(String disPanchyat) {
		this.disPanchyat = disPanchyat;
	}

	public String getTiertype() {
		return Tiertype;
	}

	public void setTiertype(String tiertype) {
		Tiertype = tiertype;
	}

	public String getDistrictPanchyat() {
		return districtPanchyat;
	}

	public void setDistrictPanchyat(String districtPanchyat) {
		this.districtPanchyat = districtPanchyat;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getSubdistrictCodes() {
		return subdistrictCodes;
	}

	public void setSubdistrictCodes(String subdistrictCodes) {
		this.subdistrictCodes = subdistrictCodes;
	}

	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAllData() {
		return allData;
	}

	public void setAllData(String allData) {
		this.allData = allData;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getLocalbType() {
		return localbType;
	}

	public void setLocalbType(String localbType) {
		this.localbType = localbType;
	}

	public String getNomenclature_english() {
		return nomenclature_english;
	}

	public void setNomenclature_english(String nomenclature_english) {
		this.nomenclature_english = nomenclature_english;
	}

	public String getLocal_body_name_english() {
		return local_body_name_english;
	}

	public void setLocal_body_name_english(String local_body_name_english) {
		this.local_body_name_english = local_body_name_english;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getIntermediatePanchyattrad() {
		return intermediatePanchyattrad;
	}

	public void setIntermediatePanchyattrad(String intermediatePanchyattrad) {
		this.intermediatePanchyattrad = intermediatePanchyattrad;
	}

	public String getDisPanchyattrad() {
		return disPanchyattrad;
	}

	public void setDisPanchyattrad(String disPanchyattrad) {
		this.disPanchyattrad = disPanchyattrad;
	}

	public String getDistrictPanchyattrad() {
		return districtPanchyattrad;
	}

	public void setDistrictPanchyattrad(String districtPanchyattrad) {
		this.districtPanchyattrad = districtPanchyattrad;
	}
	/**
	 * @return the parentList
	 */
	public String getParentList() {
		return parentList;
	}

	/**
	 * @param parentList the parentList to set
	 */
	public void setParentList(String parentList) {
		this.parentList = parentList;
	}

	/**
	 * @return the gtaList
	 */
	public String getGtaList() {
		return gtaList;
	}

	/**
	 * @param gtaList the gtaList to set
	 */
	public void setGtaList(String gtaList) {
		this.gtaList = gtaList;
	}

	/**
	 * @return the transactionid
	 */
	public Integer getTransactionid() {
		return transactionid;
	}

	/**
	 * @param transactionid the transactionid to set
	 */
	public void setTransactionid(Integer transactionid) {
		this.transactionid = transactionid;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * @return the orderCode
	 */
	public Integer getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the ordereffectiveDate
	 */
	public Date getOrdereffectiveDate() {
		return ordereffectiveDate;
	}

	/**
	 * @param ordereffectiveDate the ordereffectiveDate to set
	 */
	public void setOrdereffectiveDate(Date ordereffectiveDate) {
		this.ordereffectiveDate = ordereffectiveDate;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the gazPubDate
	 */
	public Date getGazPubDate() {
		return gazPubDate;
	}

	/**
	 * @param gazPubDate the gazPubDate to set
	 */
	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	/**
	 * @return the orderPath
	 */
	public String getOrderPath() {
		return orderPath;
	}

	/**
	 * @param orderPath the orderPath to set
	 */
	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}

	/**
	 * @return the filePath
	 */
	public MultipartFile getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the templateList
	 */
	public String getTemplateList() {
		return templateList;
	}

	/**
	 * @param templateList the templateList to set
	 */
	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}

	/**
	 * @return the govtOrderConfig
	 */
	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}

	/**
	 * @param govtOrderConfig the govtOrderConfig to set
	 */
	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}

	/**
	 * @return the operation
	 */
	public char getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(char operation) {
		this.operation = operation;
	}

	/**
	 * @return the pageRows
	 */
	public int getPageRows() {
		return pageRows;
	}

	/**
	 * @param pageRows the pageRows to set
	 */
	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * @return the pageType
	 */
	public String getPageType() {
		return pageType;
	}

	/**
	 * @param pageType the pageType to set
	 */
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/**
	 * @return the orderNocr
	 */
	public String getOrderNocr() {
		return orderNocr;
	}

	/**
	 * @param orderNocr the orderNocr to set
	 */
	public void setOrderNocr(String orderNocr) {
		this.orderNocr = orderNocr;
	}

	/**
	 * @return the villageNameEnglishCh
	 */
	public String getVillageNameEnglishCh() {
		return villageNameEnglishCh;
	}

	/**
	 * @param villageNameEnglishCh the villageNameEnglishCh to set
	 */
	public void setVillageNameEnglishCh(String villageNameEnglishCh) {
		this.villageNameEnglishCh = villageNameEnglishCh;
	}

	/**
	 * @return the orderDatecr
	 */
	public Date getOrderDatecr() {
		return orderDatecr;
	}

	/**
	 * @param orderDatecr the orderDatecr to set
	 */
	public void setOrderDatecr(Date orderDatecr) {
		this.orderDatecr = orderDatecr;
	}

	/**
	 * @return the ordereffectiveDatecr
	 */
	public Date getOrdereffectiveDatecr() {
		return ordereffectiveDatecr;
	}

	/**
	 * @param ordereffectiveDatecr the ordereffectiveDatecr to set
	 */
	public void setOrdereffectiveDatecr(Date ordereffectiveDatecr) {
		this.ordereffectiveDatecr = ordereffectiveDatecr;
	}

	/**
	 * @return the gazPubDatecr
	 */
	public Date getGazPubDatecr() {
		return gazPubDatecr;
	}

	/**
	 * @param gazPubDatecr the gazPubDatecr to set
	 */
	public void setGazPubDatecr(Date gazPubDatecr) {
		this.gazPubDatecr = gazPubDatecr;
	}

	/**
	 * @return the orderPathcr
	 */
	public String getOrderPathcr() {
		return orderPathcr;
	}

	/**
	 * @param orderPathcr the orderPathcr to set
	 */
	public void setOrderPathcr(String orderPathcr) {
		this.orderPathcr = orderPathcr;
	}

	/**
	 * @return the standardCodes
	 */
	public List<StandardCodes> getStandardCodes() {
		return StandardCodes;
	}

	/**
	 * @param standardCodes the standardCodes to set
	 */
	public void setStandardCodes(List<StandardCodes> standardCodes) {
		StandardCodes = standardCodes;
	}

	/**
	 * @return the gazettePublicationUpload
	 */
	public String getGazettePublicationUpload() {
		return gazettePublicationUpload;
	}

	/**
	 * @param gazettePublicationUpload the gazettePublicationUpload to set
	 */
	public void setGazettePublicationUpload(String gazettePublicationUpload) {
		this.gazettePublicationUpload = gazettePublicationUpload;
	}

	/**
	 * @return the requiredTitle
	 */
	public String getRequiredTitle() {
		return requiredTitle;
	}

	/**
	 * @param requiredTitle the requiredTitle to set
	 */
	public void setRequiredTitle(String requiredTitle) {
		this.requiredTitle = requiredTitle;
	}

	/**
	 * @return the uniqueTitle
	 */
	public String getUniqueTitle() {
		return uniqueTitle;
	}

	/**
	 * @param uniqueTitle the uniqueTitle to set
	 */
	public void setUniqueTitle(String uniqueTitle) {
		this.uniqueTitle = uniqueTitle;
	}

	/**
	 * @return the allowedNoOfAttachment
	 */
	public int getAllowedNoOfAttachment() {
		return allowedNoOfAttachment;
	}

	/**
	 * @param allowedNoOfAttachment the allowedNoOfAttachment to set
	 */
	public void setAllowedNoOfAttachment(int allowedNoOfAttachment) {
		this.allowedNoOfAttachment = allowedNoOfAttachment;
	}

	/**
	 * @return the allowedTotalFileSize
	 */
	public long getAllowedTotalFileSize() {
		return allowedTotalFileSize;
	}

	/**
	 * @param allowedTotalFileSize the allowedTotalFileSize to set
	 */
	public void setAllowedTotalFileSize(long allowedTotalFileSize) {
		this.allowedTotalFileSize = allowedTotalFileSize;
	}

	/**
	 * @return the allowedIndividualFileSize
	 */
	public long getAllowedIndividualFileSize() {
		return allowedIndividualFileSize;
	}

	/**
	 * @param allowedIndividualFileSize the allowedIndividualFileSize to set
	 */
	public void setAllowedIndividualFileSize(long allowedIndividualFileSize) {
		this.allowedIndividualFileSize = allowedIndividualFileSize;
	}

	/**
	 * @return the allowedFileType
	 */
	public String getAllowedFileType() {
		return allowedFileType;
	}

	/**
	 * @param allowedFileType the allowedFileType to set
	 */
	public void setAllowedFileType(String allowedFileType) {
		this.allowedFileType = allowedFileType;
	}

	/**
	 * @return the uploadLocation
	 */
	public String getUploadLocation() {
		return uploadLocation;
	}

	/**
	 * @param uploadLocation the uploadLocation to set
	 */
	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}

	/**
	 * @return the showTitle
	 */
	public String getShowTitle() {
		return showTitle;
	}

	/**
	 * @param showTitle the showTitle to set
	 */
	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	/**
	 * @return the attachedFile
	 */
	public List<CommonsMultipartFile> getAttachedFile() {
		return attachedFile;
	}

	/**
	 * @param attachedFile the attachedFile to set
	 */
	public void setAttachedFile(List<CommonsMultipartFile> attachedFile) {
		this.attachedFile = attachedFile;
	}

	/**
	 * @return the requiredTitle1
	 */
	public String getRequiredTitle1() {
		return requiredTitle1;
	}

	/**
	 * @param requiredTitle1 the requiredTitle1 to set
	 */
	public void setRequiredTitle1(String requiredTitle1) {
		this.requiredTitle1 = requiredTitle1;
	}

	/**
	 * @return the maxFileLimit1
	 */
	public String getMaxFileLimit1() {
		return maxFileLimit1;
	}

	/**
	 * @param maxFileLimit1 the maxFileLimit1 to set
	 */
	public void setMaxFileLimit1(String maxFileLimit1) {
		this.maxFileLimit1 = maxFileLimit1;
	}

	/**
	 * @return the fileTitle1
	 */
	public String[] getFileTitle1() {
		return fileTitle1;
	}

	/**
	 * @param fileTitle1 the fileTitle1 to set
	 */
	public void setFileTitle1(String[] fileTitle1) {
		this.fileTitle1 = fileTitle1;
	}

	/**
	 * @return the attachFile1
	 */
	public List<CommonsMultipartFile> getAttachFile1() {
		return attachFile1;
	}

	/**
	 * @param attachFile1 the attachFile1 to set
	 */
	public void setAttachFile1(List<CommonsMultipartFile> attachFile1) {
		this.attachFile1 = attachFile1;
	}

	/**
	 * @return the attachFile2
	 */
	public List<CommonsMultipartFile> getAttachFile2() {
		return attachFile2;
	}

	/**
	 * @param attachFile2 the attachFile2 to set
	 */
	public void setAttachFile2(List<CommonsMultipartFile> attachFile2) {
		this.attachFile2 = attachFile2;
	}

	/**
	 * @return the templateBodySrc
	 */
	public String getTemplateBodySrc() {
		return templateBodySrc;
	}

	/**
	 * @param templateBodySrc the templateBodySrc to set
	 */
	public void setTemplateBodySrc(String templateBodySrc) {
		this.templateBodySrc = templateBodySrc;
	}

	/**
	 * @return the listVillageDetails
	 */
	public List<VillageDataForm> getListVillageDetails() {
		return listVillageDetails;
	}

	/**
	 * @param listVillageDetails the listVillageDetails to set
	 */
	public void setListVillageDetails(List<VillageDataForm> listVillageDetails) {
		this.listVillageDetails = listVillageDetails;
	}

	/**
	 * @return the ulbsListData
	 */
	public String getUlbsListData() {
		return ulbsListData;
	}

	/**
	 * @param ulbsListData the ulbsListData to set
	 */
	public void setUlbsListData(String ulbsListData) {
		this.ulbsListData = ulbsListData;
	}

	/**
	 * @return the rlbsListData
	 */
	public String getRlbsListData() {
		return rlbsListData;
	}

	/**
	 * @param rlbsListData the rlbsListData to set
	 */
	public void setRlbsListData(String rlbsListData) {
		this.rlbsListData = rlbsListData;
	}

	public List<LBFreezeUnfreeze> getLbFreezeUnfreezes() {
		return lbFreezeUnfreezes;
	}

	public void setLbFreezeUnfreezes(List<LBFreezeUnfreeze> lbFreezeUnfreezes) {
		this.lbFreezeUnfreezes = lbFreezeUnfreezes;
	}
	
	
}
