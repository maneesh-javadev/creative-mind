package com.cmc.lgd.localbody.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;

import com.cmc.lgd.localbody.entities.CompletedCoverageDetails;
import com.cmc.lgd.localbody.entities.CoverageDetailsLocalBody;
import com.cmc.lgd.localbody.entities.CriteriaDraftedEntities;
import com.cmc.lgd.localbody.entities.DraftChangeCoverageTemp;
import com.cmc.lgd.localbody.entities.DraftChangeGovtOrderLBTemp;
import com.cmc.lgd.localbody.entities.DraftChangeParentLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftChangeTypeLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftLocalbodyTemp;
import com.cmc.lgd.localbody.entities.DraftRenameLocalbodyTemp;
import com.cmc.lgd.localbody.entities.GovernmentOrderDetails;
import com.cmc.lgd.localbody.entities.LBAttributes;
import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeDetailsWithCategory;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;
import com.cmc.lgd.localbody.entities.LocalBodyEntityDetails;
import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.cmc.lgd.localbody.entities.ManageLBDetails;
import com.cmc.lgd.localbody.entities.UnmappedLandregions;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.draft.form.LocalBodyDetailDto;
import in.nic.pes.lgd.forms.Response;

public interface LocalBodyDao {
	
	/**
	 * 
	 * @param stateCode
	 * @param PANCHAYAT_TYPE
	 * @return
	 */
	public LBAttributes onLoadLocalBody(Integer stateCode, String PANCHAYAT_TYPE, String processName) throws HibernateException;
	
	/**
	 * 
	 * @param stateCode
	 * @param PANCHAYAT_TYPE
	 * @return
	 * @throws HibernateException
	 */
	public LBAttributes onLoadDraftedSearchLocalBody(Integer stateCode, String PANCHAYAT_TYPE) throws HibernateException;
	
	/**
	 * 
	 * @param setupCode
	 * @param setupVersion
	 * @return
	 */
	public List<LBTypeDetails> buildLocalBodyHierarchy(Integer setupCode, Integer setupVersion) throws HibernateException;
	
	/**
	 * 
	 * @param lbTypeCode
	 * @param stateCode
	 * @param processId
	 * @return
	 */
	public List<LocalBodyEntityDetails> getDistrictPanchayatList(Integer lbTypeCode, Integer stateCode, Integer draftTempCode, Integer processId) throws HibernateException;
	
	/**
	 * 
	 * @param lbTypeCode
	 * @param districtCode
	 * @param processId
	 * @return
	 * @throws HibernateException
	 */
	public List<LocalBodyEntityDetails> getDistrictPanchayatListForDistrictUser(Integer lbTypeCode, Integer districtCode, Integer draftTempCode, Integer processId) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyCode
	 * @param draftTempCode
	 * @param processId
	 * @return
	 * @throws HibernateException
	 */
	public List<LocalBodyEntityDetails> getParentwiseLocalBody(Integer localBodyCode, Integer draftTempCode, Integer processId) throws HibernateException;
	
	/**
	 * 
	 * @param lbTypeLevel
	 * @param stateCode
	 * @param districtCode
	 * @param draftTempCode
	 * @param processId
	 * @return
	 * @throws HibernateException
	 */
	public List<UnmappedLandregions> getUnmappedLandRegions(String lbTypeLevel, Integer stateCode, Integer districtCode, Integer draftTempCode, Integer processId) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyCodes
	 * @param lbTypeLevel
	 * @return
	 */
	public List<CoverageDetailsLocalBody> fetchCoverageDetailsLocalBody(String localBodyCodes, String lbTypeLevel) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyCodes
	 * @param lbTypeLevel
	 * @param landRegionCodes
	 * @return
	 * @throws HibernateException
	 */
	public List<CoverageDetailsLocalBody> fetchCoverageLBLandRegion(String localBodyCodes, String lbTypeLevel, List<Integer> landRegionCodes) throws HibernateException;
	
	/**
	 * 
	 * @param unmappedCoverageLevel
	 * @param localBodyCodes
	 * @param localBodyType
	 * @return
	 * @throws HibernateException
	 */
	public List<UnmappedLandregions> fetchCoverageDetailsLandRegion(String unmappedCoverageLevel, String localBodyCodes, String localBodyType) throws HibernateException;
	
	/**
	 *
	 * @param orderNo
	 * @param rangeFrom
	 * @param rangeTo
	 * @param stateCode
	 * @return
	 * @throws HibernateException
	 */
	public List<GovernmentOrderDetails> fetchExistingGovernmentOrder(String orderNo, String rangeFrom, String rangeTo, Integer stateCode) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws Exception
	 */
	public String publishLocalBody(LocalBodyForm localBodyForm) throws Exception;
	
	/**
	 * 
	 * @param draftLocalbodyTemp
	 * @return
	 * @throws HibernateException
	 */
	public Boolean saveLocalBodyAsDraft(DraftLocalbodyTemp draftLocalbodyTemp) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyCode
	 * @return
	 */
	public LBAttributes getLocalBodyDetailsForView(Integer localBodyCode);
	
	/**
	 * 
	 * @param stateCode
	 * @param panchayatType
	 * @return
	 * @throws HibernateException
	 */
	public LBAttributes onLoadSearchCriteria(Integer stateCode, String panchayatType) throws HibernateException;
	
	/**
	 * 
	 * @param tempLocalBodyCode
	 * @param isDeleteStatus
	 * @return
	 * @throws Exception
	 */
	public Boolean publishOrDeleteDraftToTransaction(Integer tempLocalBodyCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	 * @param tempLBCode
	 * @return
	 * @throws HibernateException
	 */
	public Map<String, Object> getDraftedTempLBDetails(Integer tempLBCode) throws HibernateException;
	
	/**
	 * 
	 * @param fileMasterId
	 * @return
	 * @throws HibernateException
	 */
	public AttachmentMaster getUploadFileConfigurationDetails(Long fileMasterId) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyName
	 * @param localBodyType
	 * @param parentCode
	 * @param stateCode
	 * @return
	 */
	public String checkLocalBodyNameExist(String localBodyName,  Integer localBodyType, Integer parentCode, Integer stateCode, Integer draftTempId);
	
	/**
	 * 
	 * @param tierSetupCode
	 * @return
	 * @throws HibernateException
	 */
	public Boolean checkMapUpload(Integer tierSetupCode) throws HibernateException;
	
	/**
	 * 
	 * @param templateCode
	 * @return
	 * @throws HibernateException
	 */
	public LBAttributes getGovernmentOrderTemplate(Integer templateCode) throws HibernateException;
	
	/**
	 * 
	 * @param lbTempCode
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getLBFormforModification(Integer lbTempCode) throws Exception;
	
	/**
	 * 
	 * @param lbCreationType
	 * @param lbTypeCode
	 * @param stateCode
	 * @param parentLBCode
	 * @param districtCode
	 * @return
	 */
	public List<ManageLBDetails> getLocalBodiesForManage(String lbCreationType, Integer lbTypeCode, Integer stateCode, Integer parentLBCode, Integer districtCode);
	
	/**
	 * 
	 * @param localBodyCode
	 * @return
	 * @throws HibernateException
	 */
	public Map<String, Object> viewLocalBodyDetails(Integer localBodyCode) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyCode
	 * @return
	 * @throws HibernateException
	 */
	public LocalBodyTable getLocalBodyFormObject(Integer localBodyCode) throws HibernateException;
	
	/**
	 * 
	 * @param parentCode
	 * @return
	 * @throws HibernateException
	 */
	public Integer getLBCodeByParentCode(Integer parentCode) throws HibernateException;
	
	/**
	 * 
	 * @param entityTempId
	 * @return
	 * @throws HibernateException
	 */
	public DraftRenameLocalbodyTemp getDraftRenameLocalbodyTemp(Integer entityTempId) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public Boolean saveRenameLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws HibernateException
	 */
	public Boolean publishRenameLocalBody(LocalBodyForm localBodyForm) throws HibernateException;
	
	/**
	 * 
	 * @param PANCHAYAT_TYPE
	 * @param localBodyTypeCode
	 * @param stateCode
	 * @param localBodyName
	 * @param processCode
	 * @param districtCode
	 * @return
	 * @throws HibernateException
	 */
	public List<CriteriaDraftedEntities> fetchDraftedEntities(String PANCHAYAT_TYPE, Integer localBodyTypeCode, Integer stateCode,  String localBodyName, Integer processCode, Integer districtCode) throws HibernateException;
	
	/**
	 * 
	 * @param tempEntityCode
	 * @return
	 * @throws HibernateException
	 */
	public java.util.Map<String, Object> fetchDraftedRenamedLB(Integer tempEntityCode) throws HibernateException;
	
	/**
	 * 
	 * @param tempEntityCode
	 * @param isDeleteStatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Boolean publishOrDeleteDraftRenameLBToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception;

	/**
	 * 
	 * @param lbTypeCode
	 * @param parentLBCode
	 * @param stateCode
	 * @return
	 * @throws HibernateException
	 */
	public LBAttributes getLBDetailsForModifyParent(Integer lbTypeCode, Integer parentLBCode, Integer stateCode) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public Boolean saveChangeParentLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws Exception
	 */
	public String publishChangeParentLocalBody(LocalBodyForm localBodyForm) throws Exception;
	
	/**
	 * 
	 * @param tempEntityCode
	 * @return
	 * @throws HibernateException
	 */
	public java.util.Map<String, Object> fetchDraftedChangeParentLB(Integer tempEntityCode) throws HibernateException;
	
	/**
	 * 
	 * @param entityTempId
	 * @return
	 * @throws HibernateException
	 */
	public DraftChangeParentLocalbodyTemp getDraftChangeParentLocalbodyTemp(Integer entityTempId) throws HibernateException;
	
	/**
	 * 
	 * @param tempEntityCode
	 * @param isDeleteStatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String publishOrDeleteDraftChangeParentLBToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	 * @param lbTypeCode
	 * @param stateCode
	 * @return
	 * @throws HibernateException
	 */
	public java.util.Map<String, Object> getLBDetailsForModifyType(Integer lbTypeCode, Integer stateCode) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public Boolean saveChangeTypeLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws HibernateException
	 */
	public Boolean publishChangeTypeLocalBody(LocalBodyForm localBodyForm) throws HibernateException;
	
	/**
	 * 
	 * @param tempEntityCode
	 * @return
	 * @throws HibernateException
	 */
	public Map<String, Object> fetchDraftedChangeTypeLB(Integer tempEntityCode) throws HibernateException;
	
	/**
	 * 
	 * @param entityTempId
	 * @return
	 * @throws HibernateException
	 */
	public DraftChangeTypeLocalbodyTemp getDraftChangeTypeLocalbodyTemp(Integer entityTempId) throws HibernateException;
	
	/**
	 * 
	 * @param tempEntityCode
	 * @param isDeleteStatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Boolean publishOrDeleteDraftChangeTypeLBToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	 * @param localBodyCode
	 * @param stateCode
	 * @param PANCHAYAT_TYPE_CONSTANT
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getLBDetailsForModifyGovOrder(Integer localBodyCode, Integer stateCode, String PANCHAYAT_TYPE_CONSTANT) throws Exception;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public Boolean saveChangeGOLocalBodyAsDraft(LocalBodyForm localBodyForm) throws NumberFormatException, Exception;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws HibernateException
	 */
	public Boolean publishChangeGOLocalBody(LocalBodyForm localBodyForm) throws HibernateException;
	
	/**
	 * 
	 * @param tempEntityCode
	 * @return
	 * @throws HibernateException
	 */
	public DraftChangeGovtOrderLBTemp getDraftChangeGovtOrderLBTemp(Integer tempEntityCode) throws HibernateException;
	
	/**
	 * 
	 * @param tempEntityCode
	 * @param isDeleteStatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Boolean publishOrDeleteDraftChangeGOToTransaction(Integer tempEntityCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws Exception
	 */
	public LocalBodyForm setGOandMapFileAttributes(LocalBodyForm localBodyForm) throws Exception;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws HibernateException
	 */
	public Map<String, Object> changeCoverageDefaultDetails(LocalBodyForm localBodyForm) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws Exception
	 */
	public String publishChangeCoveredArea(LocalBodyForm localBodyForm) throws Exception;
	
	/**
	 * 
	 * @param draftChangeCoverageTemp
	 * @return
	 * @throws Exception
	 */
	public Boolean saveChangeCoveredAreaAsDraft(DraftChangeCoverageTemp draftChangeCoverageTemp) throws Exception;
	
	/**
	 * 
	 * @param tempChangeCoveredAreaCode
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getDraftedChangeCoverageDetails(Integer tempChangeCoverageCode) throws Exception;
	
	/**
	 * 
	 * @param tempChangeCoverageCode
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getDraftedChangeCoverageModification(Integer tempChangeCoverageCode) throws Exception;
	
	/**
	 * 
	 * @param tempChangeCoverageCode
	 * @param isDeleteStatus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Boolean publishOrDeleteDraftToTransactionChangeCoverage(Integer tempChangeCoverageCode, boolean isDeleteStatus, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	 * @param landRegionCode
	 * @param lbtypecode
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getLBListOfSelectedLR(Integer landRegionCode, Integer lbtypecode) throws Exception;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws HibernateException
	 */
	public Map<String, Object> mapCoveredAreaDefaultDetails(LocalBodyForm localBodyForm) throws HibernateException;
	
	/**
	 * 
	 * @param localBodyForm
	 * @return
	 * @throws Exception
	 */
	public String updateMappedCoveredArea(LocalBodyForm localBodyForm) throws Exception;
	
	/**
	 * 
	 * @return
	 */
	public List<String> fetchMimeType();
	
	/**
	 * 
	 * @param localBodyCode
	 * @return
	 */
	public String getMappedLBsForGIS(Integer localBodyCode,String panchayatName,boolean isShowOnlyBoundary,boolean updateApprovedGP) throws IOException;
	
	/**
	 * 
	 * @param statusFromGIS
	 * @return
	 */
	public String saveGISStatus(String statusFromGIS);
	
	/**
	 * 
	 * @param localbodyCode
	 * @return
	 * @throws HibernateException
	 */
	public List<String> fetchPreviousNames(Integer localbodyCode) throws HibernateException;
	
	/**
	 * 
	 * @param stateCode
	 * @return
	 * @throws HibernateException
	 */
	public List<LBTypeHierarchy> getLBTypeHierarchyStateWiseDetials(Integer stateCode) throws HibernateException;
	
	/**
	 * 
	 * @param setupCode
	 * @param setupVersion
	 * @return
	 * @throws HibernateException
	 */
	public List<LBTypeDetailsWithCategory> buildLocalBodyHierarchyWithCategory(Integer setupCode, Integer setupVersion) throws HibernateException;
	
	
	public Boolean validateDistrictContainSubdistrict(Integer districtCode);
	
	
	/*
	  *  For Preview GIS Coverage#started @author Maneesh Kumar 30-12-2016
	 */
	public String getMappedLBsForGISPreview(Integer localBodyCode,String panchayatName,String isShowOnlyBoundary,boolean updateApprovedGP,String deleteCode,String insertCode) throws IOException;
	
	public List<LBTypeDetails> buildLBTypeList(Integer stateCode) throws HibernateException;
	
	public List<LocalBodyEntityDetails> getParentwiseLocalBodyRPT(Integer localBodyCode, Integer draftTempCode, Integer processId) throws HibernateException;
	
	public List<LocalBodyEntityDetails> getDistrictPanchayatListRPT(Integer lbTypeCode, Integer stateCode, Integer draftTempCode, Integer processId) throws HibernateException;
	
	public String checkLocalbodyChangeParentInSameDistrict(Integer localBodyCode,Integer parentLblc)throws HibernateException;
	
	public List<UnmappedLandregions> fetchCoverageDetailsVillage(String unmappedCoverageLevel, String seltlcCode,List<Integer> existVillage, String localBodyType) throws HibernateException;
	
	public boolean lbPartCoverageExistState(Integer stateCode,Character landRegionType,Integer lrlc);
	
	public List<CompletedCoverageDetails> fetchExistingCoverage(Integer localBodyCode) throws HibernateException;

	public List<LocalBodyDetailDto> getPreviousAttachedFilesbyLblc(Integer localBodyCode);

	public Boolean saveLocalBodyForm(LocalBodyForm localBoadyCode);
	
	public List<Object[]> getOrderCodeThroughLblc(Integer localBoadyCode);

	List<Object[]> getLocalbodiesCoveredlrlc(Integer landRegionCode, String landRegionType) throws Exception;
	
	void saveLocalbodyCoverageType(Integer lbCode, String coverageList,Integer userId) throws Exception;
	
	Response saveEffectiveDateEntityLB(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId);
	
}
