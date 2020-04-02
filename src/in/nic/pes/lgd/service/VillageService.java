package in.nic.pes.lgd.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

import com.cmc.lgd.localbody.entities.LocalBodyForm;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetLBDetailsBySubDistricts;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Habitation;
import in.nic.pes.lgd.bean.HabitationConfiguration;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.PesApplicationMaster;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillageDraft;
import in.nic.pes.lgd.bean.VillageHistory;
import in.nic.pes.lgd.bean.VillagePartsBySurveyno;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.HabitationForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import pes.attachment.util.AttachedFilesForm;

 public interface VillageService {
	//  boolean addVillage(VillageForm villageForm, HttpServletRequest
	// request);

	

	//  List<VillageDataForm> getVillageDetails();
	//  List<VillageDataForm> viewVillageDetails();
	 List<Village> getVillageList(int villageCode)throws Exception;

	 List<Village> getVillListbySubDistCodeShift(int subDistrictCode)throws Exception;


	/* List<VillageDataForm> getVillageDetailsModify();*/

/*	 List<VillageDataForm> getVillageDetailsModify();*/



	 List<VillageDataForm> getVillageDetailsModify(int villageCode)throws Exception;

	/* List<MapLandRegion> getMapDetailsModify(int mapLandregionCode,
			Session session)throws Exception;*/

	 List getSubType()throws Exception;

	 List getDesignation()throws Exception;

	 List getSubTier()throws Exception;

	 String saveAsDraft(VillageForm addVillageNew)throws Exception;

	 VillageForm getDraftVillage(String fileName)throws Exception;

	 List<VillagePartsBySurveyno> getSurveyList(int villageCode)throws Exception;

	 List<Village> getVillageListByVillCode(int villageCode)throws Exception;

	 List<Village> getVillageViewList(String query, int limit, int offset)throws Exception;

	 List<Village> getVillageViewList(String query)throws Exception;

	 List<GovernmentOrder> getGovermentOrderDetail(int orderCode)throws Exception;

	 boolean invalidateVillage(VillageForm villageform,
			HttpSession httpSession)throws Exception;
	
	// created by kamlesh
	/**
	 * Change by Ripunj for Invalidate Draft Mode
	 * @param villCode
	 * @param userId
	 * @param orderNumber
	 * @param orderDate
	 * @param effectiveDate
	 * @param govtOrder
	 * @param gzbDate
	 * @param destinationVillage
	 * @param distinationSubdistrict
	 * @param ulbCode
	 * @return
	 * @throws Exception
	 */
		 String invalidateVillageNew(String villCode,Integer userId,String orderNumber,Date orderDate,
				Date effectiveDate,String govtOrder,Date gzbDate,String destinationVillage,Integer distinationSubdistrict,Integer ulbCode,Integer orderCode,String isExistGovtOrder,Character operationState)throws Exception;

	 abstract List<Village> getVillageListConctSDName(
			int subDistrictCode, String name)throws Exception;

	 List<VillageHistory> getVillageHistoryDetail(
			char villageNameEnglish, int villageCode)throws Exception;

	 List<StateWiseEntityDetails> getStateWiseVillageList(
			Integer statecode, char EntityType, Integer villageCode,
			String villageName, Integer limit, Integer offset)throws Exception;

	// ///////////
	 int getSubdistrict()throws Exception;

	 String[] getSelectedVillages()throws Exception;

	 abstract List<Village> getBlockVillagebyBlockCode(int blockCode)throws Exception;

	 List<List<Village>> getVillageViewList(VillageForm villageForm)throws Exception;

	boolean writeMap(MultipartFile filePath, HttpServletRequest request)throws Exception;

	boolean addVillage(VillageForm villageForm)throws Exception;

	void saveAsDraftInvalidateVillage(VillageForm villageForm)throws Exception;

	VillageForm getInvalidateDraftVillage(String xmlPath)throws Exception;

	boolean addVillage(VillageForm villageForm, HttpServletRequest request,
			HttpSession httpSession)throws Exception;

	 List<Village> getVillageListByVillageCodes(String query,int type)throws Exception;

	 abstract List<Village> getVillageListbySubDistrictWithSDName(
			int subDistrictCode)throws Exception;

	

	List<StateWiseEntityDetails> getParentWiseList(char entitytype,
			Integer parentCode, Integer limit, Integer offset)throws Exception;
	 boolean changeVillage(VillageForm villageForm,
			GovernmentOrderForm govtOrderForm,
			List<AttachedFilesForm> attachedList, HttpServletRequest request,int uid)throws Exception;

	 boolean changeVillagegenerate(VillageForm villageForm,
			GovernmentOrderForm govtOrderForm,
			List<AttachedFilesForm> attachedList, HttpServletRequest request,int uid,HttpSession httpSession)throws Exception;
	 boolean changeVillageforTemplate(VillageForm villageForm,
			GovernmentOrderForm govtOrderForm, HttpServletRequest request)throws Exception;
	
	/* boolean modifyVillageCrInfo(VillageForm villageForm,
			HttpServletRequest request, List<AttachedFilesForm> attachedList,
			HttpSession httpSession)throws Exception;*/
	
	 boolean modifyVillageCrInfo(VillageForm villageForm,
			HttpServletRequest request,HttpSession httpSession)throws Exception;
	
	 boolean modifyVillageInfo(VillageForm villageForm,
					HttpServletRequest request, HttpSession httpSession)throws Exception;
	
	 boolean modify(VillageForm villageForm,
			HttpServletRequest request, HttpSession httpSession)throws Exception;
	 void saveDataInAttachment(GovernmentOrder govtOrder,
			List<AttachedFilesForm> attachedList, Session session)throws Exception;
	/**
	 * Add param @stateCode to save also the draft village details.
	 */
	 String insertVillage(Integer stateCode,VillageForm villageForm,
			HttpServletRequest request, HttpSession session);
	
	

	 List<VillagePartsBySurveyno> getSurveyNumber(String surveyList)
			throws Exception;
	 boolean uploadMapOrLat(VillageForm villageForm, int villageCode,
			HttpServletRequest request) throws Exception;
	
	 boolean saveDataInMap(VillageForm villageForm,
			List<AttachedFilesForm> attachedList, HttpSession session)
			throws Exception ;

	 boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm,
			List<AttachedFilesForm> attachedList, int vilcode,HttpSession session)
			throws Exception;
	/*Retrieve files from the attachment table*/
	 List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws Exception;
	
	/*Retrieving the Map details attachment from the database*/
	 List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode) throws Exception;
	
	/*modified by pooja on 22-07-2015*/
	 Character VilageExist(int b,String c);
	
	 int saveDataInAttach(GovernmentOrderForm governmentOrderForm,
			List<AttachedFilesForm> validFileGovtUpload, HttpSession session,
			Session session2);
	 PesApplicationMaster getApplicationURL(int applicationId);
	
	 List<Village> getVillageByBlockList(String blockcode) throws Exception;
	
	 boolean addvillagegenerate(VillageForm villageForm, HttpServletRequest request,
			HttpSession httpSession,GovernmentOrderForm governmentOrderForm)throws Exception;
	
	 List<Village> getmapUnmapVillageList(int slc,int dlc, int tlc,int type)throws Exception;
	 boolean modifyVillageCrInfo(VillageForm villageForm,
			HttpServletRequest request, 
			List<AttachedFilesForm> attachedList,
			List<AttachedFilesForm> attachedMapList,
			boolean delflag,
			String deleteAttachmentId[],
			HttpSession httpSession) 
			throws Exception;
	
	 List<Village> getVillageSuerveryNo(String vCode) throws Exception;
	 List<Village> getVillagebysubdisrictCodes(String vCode) throws Exception;
	
	/**
	 * To get the contributing villages with its opearion state (modify,save and publish state).
	 */
	 List<Village> getVillageListWithOperationState(int subDistrictCode) throws Exception;
	/**
	 *  To get the contributing villages for draft Village with its opearion state (modify,save and publish state).
	 */
	 List<Village> getDraftVillageListWithOperationState(int subDistrictCode,int draftVilCode) throws Exception;
	/**
	 * To get the draft village details statewise.
	 */
	 List<VillageDraft> getVillageDraftList(int stateCode) throws Exception;
	/**
	 * To get the draft village details.
	 */
	 List<VillageDraft> getVillageDraftDetails(int villageCode)throws Exception;
	/**
	 * To get the draft village details for manage draft Village Form.
	 */
	 List<VillageDataForm> getVillageDraftDetailsModify(int villageCode)throws Exception;
	/**
	 * To save the draft village upload map details in Create Village Form.
	 */
	 boolean saveDataInMapDraft(VillageForm villageForm,
			List<AttachedFilesForm> attachedList, HttpSession session)
			throws Exception ;
	/**
	 * To save the draft village upload map details in Manage Draft Village Form.
	 */
	 boolean saveDataInMapDraftVilModify(VillageDataForm villageForm, HttpSession session)
			throws Exception ;
	/**
	 * To save the draft village govt order details in Create Village Form.
	 */
	 boolean saveDataInAttachDraftVilCreate(VillageForm vilForm,
			List<AttachedFilesForm> attachedList, int vilcode,HttpSession session)
			throws Exception;
    /**
     * To publish the Draft Village Details.
     */
	 String publishDraftVillageModify(Integer stateCode,VillageDraft villageDraft,
			HttpServletRequest request, HttpSession session) throws Exception;
	/**
	 * To delete the Draft Village Details for a particular village.
	 */
	 String deleteDraftVillageModify(int villageCode,HttpServletRequest request, HttpSession session) throws Exception;
	/**
	 * For save or publish the draft Village details in manage draft village Form.
	 */
	 String insertVillageModify(Integer stateCode,VillageForm villageForm,
			HttpServletRequest request, HttpSession session)  throws Exception;
	/**
	 * To save the draft village govt order details in Modify Village Form.
	 */
	 boolean saveDataInAttachDraftVillageModify(GovernmentOrderForm governmentOrderForm,
			List<Attachment> attachedList, int vilcode,HttpSession session)
			throws Exception;
	/**
	 * To save the draft village upload map details in Modify Village Form.
	 */
	 boolean saveDataInMapDraftVillageModify(VillageForm villageForm,List<MapAttachment> attachedList, HttpSession session)	throws Exception;

	/**
	 * Get Village List Details with operationState for Extended Functionality of Department.
	 * @author Ripunj on 06-10-2014
	 * @param subDistrictCode
	 * @param orgCode
	 * @param locatedLevelCode
	 * @return
	 * @throws Exception
	 */
	 List<Village> getVillageListbySubDistrictWithSDNameExtended(int subDistrictCode, int orgCode, int locatedLevelCode)throws Exception;
		
	
	
	/*
	 * added on 11/12/2014 for localbody impact
	 * 
	 */
	
	 List<Village> getVillagebysubdisrictCodesForLocalBody(String vCode)	throws Exception;
	
	/**
	 * Add to get Villages from selected block for localbody Draft Mode.
	 * @author Ripunj on 07-01-2015 
	 * @param blockcode
	 * @return
	 * @throws Exception
	 */
	 List<Village> getVillageByBlockListForLocalBody(String blockcode)
			throws Exception;
	
	/**
	 * added on 07/01/2015 by kirandeep for localbody impact issue.
	 */
	 List<Village> getVillageListbySubDistrictCodeCov(int subDistrictCode) throws Exception;
	
	
	/**
	 * 
	 * added by kirandeep on 08/01/2015 for local body impact
	 * @param subDistrictCode
	 * @return
	 * @throws Exception
	 */
	
	 List<Village> getVillageListbySubDistrictWithSDNameForLocalbody(int subDistrictCode) throws Exception; 
	/**
	 * To save the draft rename village govt order details in Create Village Form.
	 */
	boolean saveDataInAttachDraftRenameVilCreate(VillageForm villageForm,
			List<AttachedFilesForm> attachedList, int vilcode,
			HttpSession session) throws Exception;
	
	/**
	 * For Rename Draft Mode
	 * Added by Ripunj on 09-03-2015
	 * @param villageForm
	 * @param govtOrderForm
	 * @param attachedList
	 * @param request
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	 boolean changeVillageModify(VillageForm villageForm,
			GovernmentOrderForm govtOrderForm,
			List<AttachedFilesForm> attachedList, HttpServletRequest request,int uid)throws Exception;

	/**
	 * For Publish Rename Draft Mode
	 * Added by Ripunj on 10-03-2015
	 * @param villageForm
	 * @param govtOrderForm
	 * @param attachedList
	 * @param request
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	 boolean changeVillageModifyPublish(VillageForm villageForm,
			GovernmentOrderForm govtOrderForm,
			List<AttachedFilesForm> attachedList, HttpServletRequest request,
			int uid) throws Exception;
	
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
	 * To modify Invalidate Draft Mode Villages.
	 * @author Ripunj on 17-03-2015
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
	
	 List<Village> getVillageListbyCreteria(String districtCodes, String subDistrictCodes,String villageCodes,Integer subDistrictCode) throws Exception;
	
	/**
	 * Get Invalidate Village is mapped or unmapped
	 * @param invalidateVillageCodeLIst
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
	
	 String getISPesaFlag(String villageCodes)throws Exception;
	
	 String getMappedVillageForGIS(Integer subdistrictCode,Integer villageCode,String villageName,String coverage,String isShowOnlyBoundary) throws IOException;
	
	 String finalizeVillageGIS(Integer villageCode) throws Exception;
	
	 String getUnMappedVillageForGIS(Integer subdistrictCode, String isShowOnlyBoundary) throws IOException;
	
	 Boolean checkUnMappedPolygonByState(Integer stateCode);
	
	 List<District> getUnMappedPolygonDistrictByState(Integer stateCode);
	
	 List<Subdistrict> getUnMappedPolygonSubDistrictByDistrict(Integer districtCode);
	
	 String insertVillageGisMapStatus(Integer villageCode)throws HibernateException;
	
	 HabitationConfiguration getStateWiseHabitationConfiguration(Integer slc)throws Exception;
	
	 boolean habitationNameUniquewithParent(String habitationNameEng,Integer parentCode,String parentType)throws Exception;
	
	 List<Habitation> getHabitationList(String villageCodes)throws Exception;
	
	 void saveHabitationLocalbody(LocalBodyForm localBodyForm)throws Exception;
	
	 List<Habitation> getMappedHabitationList(Integer localBodyCode)throws Exception;
	
	 void saveHabitation(HabitationForm habitationForm)throws Exception;
	
	 boolean validateParentCode(Integer parentCode,String parentType,Integer slc)throws Exception;
	
	 void saveHabitationConfiguration(HabitationConfiguration habitationConfiguration)throws Exception;
	
	 boolean validateStateWiseHabitation(Integer stateCode)throws Exception;
		
	 List<Habitation> getHabitationList(int parentCode,int stateCode);
	
	 Habitation getHabitationDetails(int habitationId,int habitationVersion);
	
	 boolean updateHabitation(Habitation habitation);
	
	 String getHabitationConfigrationLocalbody(Integer slc)throws Exception;
	 
	 Map<String,Object> getEntityEffeactiveDate(Integer entityCode,Character entityType) throws HibernateException;
	
	
	 Response saveEffectiveDateEntity(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId);
	
	 List<GetEntityEffectiveDate> getEntityEffeactiveDateByVersion(GetEntityEffectiveDate getEntityEffectiveDate) throws HibernateException;
	
	 boolean saveVillageStatus(Integer villageCode,String villageStatus,Long userId)throws Exception;
	 
	 boolean validateNewEffectiveDate(Integer villageCode,List<GetEntityEffectiveDate> getEntityEffectiveDateList,List<GetEntityEffectiveDate> effectiveDateListOld);
}

	
	
	
	


