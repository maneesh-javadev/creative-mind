package in.nic.pes.lgd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cmc.lgd.localbody.entities.LocalBodyForm;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GetLBDetailsBySubDistricts;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Habitation;
import in.nic.pes.lgd.bean.HabitationConfiguration;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.PesApplicationMaster;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillageDraft;
import in.nic.pes.lgd.bean.VillageHistory;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.bean.VillagePartsBySurveyno;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.HabitationForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import pes.attachment.util.AttachedFilesForm;

public interface VillageDAO {
	 boolean save(Village village)throws Exception;
	 int getMaxVillageCode(String query)throws Exception;
	 int getMaxVillageVersionbyCode(int villageCode)throws Exception;
//	 List<VillageDataForm> getVillageDetails() throws Exception;
//	 List<VillageDataForm> getVillageDetailsModify() throws Exception;
	//Modify Village detail of village & Map
	 List<Village> getVillageDetailsModify(int villageCode,Session session)throws Exception;
	 List<MapLandRegion> getMapDetailsModify(int mapLandregionCode,Session session)throws Exception;
//	 List<VillageDataForm> viewVillageDetails() throws Exception;
	 List<Village> getVillageListbySubDistrictCode(int subDistrictCode)throws Exception;
	 List<VillagePartsBySurveyno> getSurveyListbyVillageCode(int villageCode)throws Exception;
	 List<Village> getVillageViewList(String query, int limit, int offset)throws Exception;
	 List<Village> getVillageViewList(String query)throws Exception;
	//Modify Village
	 boolean modifyVillageInfo(VillageForm villageForm, VillagePK villagePk, int map_landRegionCode,Session session,HttpSession httpSession)throws Exception;
	 List<GovernmentOrder> getGovermentOrderDetail(int orderCode)throws Exception;
	 List<GetGovernmentOrderDetail> GovOrderDetail(char entityType,int entityCode,int entityVersion,int minorVersion,Session session)throws Exception;
	 List<Village> getVillageListDetails(String query)throws Exception;
	 List<VillagePartsBySurveyno> getListofSurvey(String query)throws Exception;
     List getSubType()throws Exception;
	 List getDesignation()throws Exception;
	 List getSubTier()throws Exception;
	
	 boolean update(Village village, Session session) throws Exception;
	
     List<Village> getListVillageDetails(String query)throws Exception;
	
	 List<Subdistrict> getSubdistrictDetails(int subdistrictCode)throws Exception;
	
	//updateIsActive
	 boolean updateIsActive(VillagePK villagePk)throws Exception;
	
	 boolean updateLReplaces(int lrReplacedby, VillagePK villagePk)throws Exception;
	
	 boolean updateLReplacedBy(int lrReplacedby, VillagePK villagePk)throws Exception; 
	
	
	 boolean updateMapLanRegion(int mapCode, String coordinates, int villageCode,Session session)throws Exception;
	
	 List<Village> getVillageListByVillCode(int villageCode)throws Exception;
	 abstract boolean update(String query, Session session)throws Exception;
	 abstract boolean save(Village village, Session session)throws Exception;
	
	 List<Subdistrict> getSubdistrictDetails(String query)throws Exception;
	 int getVillageVersion(int VillageCode) throws Exception ;
	 int getVillageVersion(int VillageCode,Session session)throws Exception;

	 List<Village> getSubDistrictListbyDistrict(int districtCode)throws Exception;
	
	
	
	 List<VillageHistory> getVillageHistoryDetail(char villageNameEnglish,int villageCode)throws Exception;
	/**
	 * Add Some Parameters for Rename Draft Village Mode on 10-03-2015
	 * @param villageCode
	 * @param userId
	 * @param villageNameEnglish
	 * @param date
	 * @param villageNameLocal
	 * @param aliasEnglish
	 * @param aliasLocal
	 * @param session
	 * @param orderno
	 * @param orderdate
	 * @param gazpubdate
	 * @param operationState
	 * @param isExistGovtOrder
	 * @param orderCode
	 * @return
	 * @throws Exception
	 */
	/**
	 * Add parameter govtOrderConfig by Pooja on 27-08-2015
	 * */
	 String	ChangeVillage(int villageCode,int userId,String villageNameEnglish,Date date, String villageNameLocal,String aliasEnglish,String aliasLocal,Session session,String orderno,Date orderdate,Date gazpubdate,Character operationState,String isExistGovtOrder,Integer orderCode,String govtOrderConfig)throws Exception;
	 boolean	invalidateVillage(int villageCodetobeMerge, String VillagestoInvalidated,int roleCode)throws Exception;
	 List<Village> getVillageListByMaxVersion(int villageCode)throws Exception;
	 void SetGovermentOrderEntity(String entityCode,char entityType)throws Exception;
	boolean saveSurveynos(String surveyNos, int villageCode, int villageVersion)throws Exception;

	 List<Village> getVillageList(String query,int type)throws Exception;

	 abstract List<Village> getVillageListbySubDistrictWithSDName(int subDistrictCode)throws Exception;
	 List<Village> getVillageListbySubDistrictCode(int subDistrictCode, Session session)throws Exception;
	 List<Village> getVillageList(Integer villagecode)throws Exception;
	boolean insertBulkVillage(String query, Session session)throws Exception;
	
	
	 List<StateWiseEntityDetails> getStateWiseVillageList(Integer statecode,char EntityType,Integer villageCode,String villageName, Integer limit, Integer offset)throws Exception;
	 List<StateWiseEntityDetails> getParentWiseList(char entitytype,
			Integer parentCode, Integer limit, Integer offset)throws Exception;
	 int getMaxvillageVersion(int code, Session session)throws Exception;
	int getMaxVillageVersionbyVillageCode(int villageCode)throws Exception;
	 String insertVillage(Integer stateCode,VillageForm villageForm,
			HttpServletRequest request, HttpSession session);
	
	List<VillagePartsBySurveyno> getSurveyNumber(String surveyList)
			throws Exception;
	 boolean saveDataInMap(VillageForm villageForm,
			List<AttachedFilesForm> attachedList, HttpSession session);
	 boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm,
			List<AttachedFilesForm> attachedList,int vilcode, HttpSession session);
	/*Retrieve files from the attachment table*/
	 List<Attachment> getAttacmentdetail(int orderCode) throws Exception;
	/*Retrieving the Map details attachment from the database*/
	 List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType,int entityCode, Session session) throws Exception;
	
	/*modified by pooja on 22-07-2015*/
	 Character VilageExist(int b, String c);
	// added by kamlesh for invalidate village
	/**
	 * Change by Ripunj For Invalidate Draft Mode
	 * @param villCode
	 * @param userId
	 * @param orderNumber
	 * @param orderDate
	 * @param effectiveDate
	 * @param govtOrder
	 * @param gzbDate
	 * @param destinationVillage
	 * @param distinationSubdistrict
	 * @param session
	 * @param ulbCode
	 * @return
	 * @throws Exception
	 */
		 String invalidateVillageDAO(String villCode, Integer userId,String orderNumber, Date orderDate, Date effectiveDate,String govtOrder, Date gzbDate,
				String destinationVillage,Integer distinationSubdistrict, Session session,Integer ulbCode,Integer orderCode,String isExistGovtOrder,Character operationState) throws Exception;
		
		 int saveDataInAttachment(GovernmentOrderForm governmentOrderForm,
				List<AttachedFilesForm> validFileGovtUpload, HttpSession session,
				Session session2);
		
		 PesApplicationMaster getApplicationURL(int applicationId);
		
		 List<Village> getVillageByBlockList(String blockcode) throws Exception;
		
		 List<Village> getmapUnmapVillageList(int slc, int dlc, int tlc,
				int type) throws Exception;
		
		 List<Village> getVillageSuerveryNo(String vCode) throws Exception;
		
		 List<Village> getVillagebysubdisrictCodes(String vCode) throws Exception;
		/*
		 * Get the Village List Details with its state (save, publish or modify) 
		 */
		 List<Village> getVillageListbySubDistrictCodeWithOperationState(int subDistrictCode) throws Exception;
		/*
		 * Get Village Draft Details.
		 * param @stateCode
		 */
		 List<VillageDraft> getVillageDraftList(int stateCode) throws Exception;
		/*
		 * Get Village Draft Details.
		 * param @villageCode
		 */
		 List<VillageDraft> getVillageDraftDetails(int villageCode)throws Exception;
		
		/*
		 * Save the  Upload Map Details of VillageForm.
		 * param @attachedList
		 */
		 boolean saveDataInMapDraft(VillageForm villageForm,
				List<AttachedFilesForm> attachedList, HttpSession session);
		/*
		 * Get village draft details based on operation state(save or publish).
		 * param  @subDistrictCode
		 * param  @draftVilCode - village draft Code
		 */
		 List<Village> getDraftVillageListbySubDistrictCodeWithOperationState(int subDistrictCode,int draftVilCode) throws Exception;
		/*
		 * Add on 29-08-2014
		 * Save Upload Map Details of Village Draft. 
		 */
		 boolean saveDataInMapDraftVilModify(VillageDataForm villageDataForm, HttpSession session) throws Exception;	
		/*
		 * Add on 29-08-2014
		 * Save GovtOrder Details while creation of Village Draft. 
		 */
		 boolean saveDataInAttachDraftVilCreate(VillageForm villageForm,
				List<AttachedFilesForm> attachedList,int vilcode, HttpSession session)  throws Exception;	
		/*
		 * Add on 01-09-2014
		 * Publish Village Draft Details in Village Entity.
		 * param @stateCode
		 * param @villageDraft - Village Draft Details
		 */
		 String publishDraftVillageModify(Integer stateCode,VillageDraft villageDraft,
				HttpServletRequest request, HttpSession session) throws Exception;	
		
		/*
		 * Add on 01-09-2014
		 * Delete Village Draft Details.
		 * param @villageCode - Village Draft Code
		 */
		 String deleteDraftVillageModify(int villageCode,HttpServletRequest request, HttpSession session) throws Exception;	

		/*
		 * Add on 04-09-2014
		 * Save or Publish modified village details.
		 * param @stateCode
		 * param @villageForm - Village Form Details
		 */
		 String insertVillageModify(Integer stateCode,VillageForm villageForm,
				HttpServletRequest request, HttpSession session);
		/*
		 * Add on 29-08-2014
		 * Save GovtOrder Details while modification of Village Draft. 
		 */
		boolean saveDataInAttachDraftVillageModify(	GovernmentOrderForm governmentOrderForm,
				List<Attachment> attachedList, int vilcode, HttpSession session)
				throws Exception;
		/*
		 * Add on 29-08-2014
		 * Save Map Upload Details while modification of Village Draft. 
		 */
		boolean saveDataInMapDraftVillageModify(VillageForm villageForm,List<MapAttachment> attachedList, HttpSession session)	throws Exception;
		/**
		 * Get Village List Details with operationState for Extended Functionality of Department.
		 * @param subDistrictCode
		 * @param orgCode
		 * @param locatedLevelCode
		 * @return
		 * @throws Exception
		 */
		 abstract List<Village> getVillageListbySubDistrictWithSDNameExtended(int subDistrictCode,int orgCode,int locatedLevelCode)throws Exception;
		/**
		 * Added by Ripunj on 06-12-2014 for LocalBodyImpact
		 * @param subDistrictCode
		 * @return
		 * @throws Exception
		 */
		 List<Village> getVillageListbySubDistrictCodeCov(int subDistrictCode)throws Exception;
		
		/*
		 * added on 11/12/2015 for localbody impact
		 * 
		 */
		
		 List<Village> getVillagebysubdisrictCodesForLocalBody(String vCode)
				throws Exception;
		/**
		 * Add to get Villages from selected block for localbody Draft Mode.
		 * @author Ripunj on 07-01-2015 
		 * @param blockcode
		 * @return
		 * @throws Exception
		 */
		 List<Village> getVillageByBlockListForLocalBody(List<Integer> blockCodeList) throws Exception;
		/**
		 * 
		 * added by kirandeep on 08/01/2015 for local body impact
		 * @param subDistrictCode
		 * @return
		 * @throws Exception
		 */
		
		 List<Village> getVillageListbySubDistrictWithSDNameForLocalbody(int subDistrictCode) throws Exception; 
		
		/*
		 * Add on 29-08-2014
		 * Save GovtOrder Details while creation of Village Rename Draft. 
		 */
		 boolean saveDataInAttachDraftRenameVilCreate(VillageForm villageForm,
				List<AttachedFilesForm> attachedList,int vilcode, HttpSession session)  throws Exception;
		
		/**
		 * For Invalidate Draft Mode 
		 * @author Ripunj on 17-03-2015
		 * @param slc
		 * @param dlc
		 * @param tlc
		 * @param type
		 * @return
		 * @throws Exception
		 */
		 List<Village> getmapUnmapVillageListInvalidateDraftMode(int slc,int dlc, int tlc,int type,int draftVillageCode)throws Exception;
		
		/**
		 *  For Invalidate Draft Mode .
		 *  @author Ripunj on 17-03-2015
		 * @param villCode
		 * @param userId
		 * @param orderNumber
		 * @param orderDateCR
		 * @param effectiveDateCR
		 * @param govtOrder
		 * @param gzbDateCR
		 * @param destinationVillage
		 * @param distinationSubdistrict
		 * @param session
		 * @param ulb
		 * @param orderCode
		 * @param isExistGovtOrder
		 * @param operationState
		 * @param draftVillageCode
		 * @return
		 * @throws Exception
		 */
		 String invalidateVillageDAOModify(String villCode, Integer userId,
				String orderNumber, Date orderDateCR, Date effectiveDateCR,
				String govtOrder, Date gzbDateCR, String destinationVillage,
				Integer distinationSubdistrict, Session session, Integer ulb,
				Integer orderCode, String isExistGovtOrder,
				Character operationState, Integer draftVillageCode)
				throws Exception;
		/**
		 * Get Draft Village Entities by DistrictCode
		 * @author Ripunj on 09-04-2015
		 * @param districtCode
		 * @return
		 * @throws Exception
		 */
		 List<VillageDraft> getVillageDraftListByDistrictCode(int districtCode) throws Exception;
		
		/**
		 * author Anju Gupta on 30/03/2015
		 * for villageList for Pesa Land Regions
		 */
		 List<Village> villageListbySlctoIspesa(int slc) throws Exception ;
		

		 List<Village> getVillageListbyCreteria(List<Integer> villageList,List<Integer>  districtList,List<Integer> subDistrictCodeList,Integer subDistrictCode) throws Exception; 
		
		/**
		 * Get Invalidate Village is mapped or unmapped
		 * @param invalidateVillageCodeList
		 * @author Pooja Sharma 29-07-2015
		 */
		 Boolean[] isMappedOrUnmapped(Integer[] invalidateVilllageCodeList) throws Exception;
		
		/**
		 * Get LocalBodyDetails by SubdistrictCodes
		 * @param subDistricts
		 * @author Pooja Sharma on 02-09-2015
		 */
		 List<GetLBDetailsBySubDistricts> getLBDetailsBySubDistricts(String subDistricts) throws Exception;
		
		 String getMaxPreVersionEffDateOfVillages(String villages) throws Exception;
		
		 String getISPesaFlag(List<Integer> villageCodeList)throws Exception;
		
		 String finalizeVillageGIS(Integer villageCode) throws Exception;
		
		 Boolean checkUnMappedPolygonByState(Integer stateCode);
		
		 List<District> getUnMappedPolygonDistrictByState(Integer stateCode);
		
		 List<Subdistrict> getUnMappedPolygonSubDistrictByDistrict(Integer districtCode);
		
		 String insertVillageGisMapStatus(Integer villageCode)throws HibernateException;
		
		 String checkVillageNewforMap(Integer villageCode)throws Exception;
		
		 String getContributingLandregion(Integer entityCode,Character entityType)throws HibernateException;
		
		 HabitationConfiguration getStateWiseHabitationConfiguration(Integer slc)throws Exception;
		
		 boolean habitationNameUniquewithParent(String habitationNameEng,Integer parentCode,String parentType)throws Exception;
		
		//Added by abhishek gusain
		 void saveHabitationConfiguration(HabitationConfiguration habitationConfiguration)throws Exception;
		
		 boolean validateStateWiseHabitation(Integer stateCode)throws Exception;
		
		 List<Habitation> getHabitationList(List<Integer> villageCodeList)throws Exception;
		
		 void saveHabitationLocalbody(LocalBodyForm localBodyForm)throws Exception;
		
		 List<Habitation> getMappedHabitationList(Integer localBodyCode)throws Exception;
		
		 void saveHabitation(HabitationForm habitationForm)throws Exception;
		
		 boolean validateParentCode(Integer parentCode,String parentType,Integer slc)throws Exception;
		
		 List<Habitation> getHabitationList(int parentCode,int stateCode);
		
		 Habitation getHabitationDetails(int habitationId,int habitationVersion);
		
		 boolean updateHabitation(Habitation habitation);
		 
		 Map<String,Object> getEntityEffeactiveDate(Integer entityCode,Character entityType) throws HibernateException;
		
		
		 Response saveEffectiveDateEntity(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId);
		
		 List<GetEntityEffectiveDate> getEntityEffeactiveDateByVersion(GetEntityEffectiveDate getEntityEffectiveDate) throws HibernateException;
		 
		 boolean saveVillageStatus(Integer villageCode,String villageStatus,Long userId)throws Exception;
		
		 boolean validateNewEffectiveDate(Integer villageCode,List<GetEntityEffectiveDate> getEntityEffectiveDateList,List<GetEntityEffectiveDate> effectiveDateListOld);
	}
