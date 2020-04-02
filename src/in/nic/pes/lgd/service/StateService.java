package in.nic.pes.lgd.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AuditTrailLGD;
import in.nic.pes.lgd.bean.ConfigurationBlockVillageMapping;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictFreezeEntity;
import in.nic.pes.lgd.bean.EntityFreezeStatus;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.FreezeLocalBodyEntity;
import in.nic.pes.lgd.bean.FreezeUnfreezeStateConfigEntity;
import in.nic.pes.lgd.bean.GeneratedFileDetails;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.LgdDataConfirmation;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.bean.OrganizationByCentreLevel;
import in.nic.pes.lgd.bean.ParentwiseChildDetials;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.Search;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateHistory;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.forms.StateDataForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageForm;
import pes.attachment.util.AttachedFilesForm;

 public interface StateService {

	//  abstract List<District> getDistrictListbyStateCode(int stateCode);

	 boolean changeStateforTemplate(StateForm stateForm, GovernmentOrderForm govtOrderForm, HttpServletRequest request, HttpSession httpSession) throws Exception;

	 ModelAndView createState(StateForm stateForm, GovernmentOrderForm govtOrderForm, int user_id, List<AttachedFilesForm> validFileMap, HttpServletRequest request, BindingResult bindingResult, HttpSession httpSession) throws Exception;

	/*
	 *  boolean DistrictmodifyReorg(StateForm sDForm, List<DistrictForm>
	 * listdistritNew, List<VillageForm> listModifyVill, HttpServletRequest
	 * request);
	 */
	 boolean DistrcitmodifyReorg(StateForm sDForm, List<DistrictForm> listdistritNew, List<VillageForm> listModifyVill, HttpServletRequest request, HttpSession httpSession) throws Exception;

	/*
	 * added by kamlesh for finding duplicate state used in dwr on 18-march_13
	 */
	 int findDuplicate(String stateName) throws Exception;

	 abstract List<State> getAllNotInStates(String stateList) throws Exception;

	 List<AssemblyConstituency> getAssemblySourceList() throws Exception;

	/* Retrieve files from the attachment table */
	 List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws Exception;

	 List<GeneratedFileDetails> getCBTHtml(String fName, String documentType, int documentId) throws Exception;

	 List<State> getDistrict(String query) throws Exception;

	 List<District> getDistricts(String stateCode) throws Exception;

	/* Retrieving the Map details attachment from the database */
	 List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode) throws Exception;

	// modify
	 List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws Exception;

	 List<StateDataForm> getGovtOrderByEntityCode(int entityCode, char type);

	 List<Headquarters> getHeadquterDetails(Integer regionCode, String region_type) throws Exception;

	 List<MapLandRegion> getMapDetailsModify(int mapLandregionCode) throws Exception;

	 List<AssemblyConstituency> getParliamentSource(int stateCode) throws Exception;

	 List<ParliamentConstituency> getParliamentSourceList(int stateCode) throws Exception;

	List<District> getPartDistricts(String stateCode) throws Exception;

	 List<StateDataForm> getStateDetailsModify(int stateCode) throws Exception;

	 List<StateHistory> getStateHistoryDetail(char stateNameEnglish, int stateCode) throws Exception;

	 List<Search> getStateSearchDetail(String entityName, String entityCode) throws Exception;

	 List<State> getStateSourceList() throws Exception;

	 List<State> getStateSourceList(int stateCode) throws Exception;

	 List<State> getStateTargetList(int stateCode) throws Exception;

	 List<State> getStateViewList(StateDataForm stateView) throws Exception;

	/*
	 *  boolean modifyStateCrInfo(StateForm stateForm, HttpServletRequest
	 * request, List<AttachedFilesForm> attachedList, List<AttachedFilesForm>
	 * attachedMapList, HttpSession httpSession) throws Exception;
	 */

	 boolean modifyStateInfo(StateForm stateForm, HttpServletRequest request) throws Exception;

	 boolean modifyStateInfoNew(StateForm stateForm,List<AttachedFilesForm> attachedList, HttpServletRequest request) throws Exception;

	 String openFile(String filePath) throws IOException;

	 boolean publishState(StateForm stateForm, int sdCode, int sdVersion, int mapCd, Session session) throws Exception;

	 boolean reOrganize(StateForm sDForm, List<VillageForm> listModifyVill, HttpServletRequest request, HttpSession httpSession) throws Exception;

	 boolean reOrganizedistrict(StateForm sDForm, List<DistrictForm> listdistritNew) throws Exception;

	 boolean reOrganizeDistrictModify(StateForm sDForm, List<DistrictForm> listDistrictModify, HttpServletRequest request, HttpSession session) throws Exception;

	 //boolean reOrganizeModify(StateForm sDForm, List<SubDistrictForm> listSubdistrictModify, HttpServletRequest request, HttpSession httpSession) throws Exception;// /modify
																																											// by
																																											// vanisha

	// boolean reOrganizesubdistrict(StateForm sDForm, List<SubDistrictForm> listSubdistritNew) throws Exception;

	 boolean reOrganizeVillage(StateForm sDForm, List<VillageForm> listNewVill, HttpServletRequest request) throws Exception;

	boolean save(StateForm districtForm) throws Exception;

	 boolean saveDataInAttach(StateForm stateForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception;

	 boolean saveDataInAttachMap(StateForm stateForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception;

	 void saveDataInAttachment(GovernmentOrder govtOrder, StateForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws Exception;

	 GovernmentOrder saveDataInGovtOrder(StateForm sDForm, Session session) throws Exception;

	//boolean saveNewDistrictVersion(int subDistrictCode, int newSDCode, int newSDVersion, Session session) throws Exception;

	boolean saveNewDistrictVersionPartContri(int villCode, int newSDCode, int newSDVersion, Session session) throws Exception;

	boolean saveNewDistrictVersionPartContri(int distCode, Session session) throws Exception;


	boolean saveNewSubdistritVersion(int DistrictCode, int districtCode2, int distritVersion, Session session) throws Exception;

	boolean saveNewVillageVersion(int subDistrictCode, int newSDCode, int newSDVersion, Session session) throws Exception;

	boolean saveReplacedBy(int id, int lrReplacedBy, int sdCode, int sdVersionCode, Session session) throws Exception;

	boolean saveReplaces(int id, int lrReplaces, int sdCode, int sdVersionCode, Session session) throws Exception;

	 boolean saveShiftdistrict(StateForm sDForm, List<ShiftDistrictForm> listdistritShift, HttpServletRequest request) throws Exception;

	 boolean saveShiftSubdistrict(StateForm sDForm, List<ShiftSubDistrictForm> listSubdistritShift, HttpServletRequest request) throws Exception;

	 boolean saveShiftvillage(StateForm sDForm, List<ShiftVillageForm> listvillageShift, HttpServletRequest request) throws Exception;

	boolean saveState(StateForm stateForm, Session session) throws Exception;

	 boolean stateDistrictmodifyreorg(StateForm sDForm, List<DistrictForm> listdistritNew, List<SubDistrictForm> listSubdistritNew, List<VillageForm> listNewVill, List<DistrictForm> listDistrictModify,
			List<SubDistrictForm> listSubdistrictModify, HttpServletRequest request) throws Exception;

	 boolean stateDistrictonlymodifyreorg(StateForm sDForm, List<DistrictForm> listdistritNew, List<SubDistrictForm> listSubdistritNew, List<VillageForm> listNewVill, List<DistrictForm> listDistrictModify, HttpServletRequest request,
			HttpSession httpsession) throws Exception;

	 boolean stateDistrictReorg(StateForm sDForm, List<DistrictForm> listdistritNew) throws Exception;

	 boolean stateDistrictSubdistrictReorg(StateForm sDForm, List<DistrictForm> listdistritNew, List<SubDistrictForm> listSubdistritNew) throws Exception;

	 boolean stateDistrictSubdistrictvillagemodifyReorg(StateForm sDForm, List<DistrictForm> listdistritNew, List<SubDistrictForm> listSubdistritNew, List<VillageForm> listNewVill, List<DistrictForm> listDistrictModify,
			List<SubDistrictForm> listSubdistrictModify, List<VillageForm> listModifyVill, HttpServletRequest request, HttpSession httpSession) throws Exception;

	 boolean stateDistrictSubdistrictvillageReorg(StateForm sDForm, List<DistrictForm> listdistritNew, List<SubDistrictForm> listSubdistritNew, List<VillageForm> listNewVill) throws Exception;

	 boolean stateDistrictSubdistrictvillageReorgShift(StateForm sDForm, List<DistrictForm> listdistritNew, List<SubDistrictForm> listSubdistritNew, List<VillageForm> listNewVill, List<ShiftDistrictForm> listdistritShift,
			List<ShiftSubDistrictForm> listSubdistritShift, List<ShiftVillageForm> listvillageShift, HttpServletRequest request) throws Exception;

	 boolean stateDistrictSubdistrictvillageReorgShiftmodify(StateForm sDForm, List<DistrictForm> listdistritNew, List<SubDistrictForm> listSubdistritNew, List<VillageForm> listNewVill, List<ShiftDistrictForm> listdistritShift,
			List<ShiftSubDistrictForm> listSubdistritShift, List<ShiftVillageForm> listvillageShift, List<DistrictForm> listDistrictModify, List<SubDistrictForm> listSubdistrictModify, List<VillageForm> listModifyVill, HttpServletRequest request,
			HttpSession httpSession) throws Exception;// /modify by vanisha

	 boolean statemodifyReorg(StateForm sDForm, List<DistrictForm> listdistritNew, List<DistrictForm> listDistrictModify, HttpServletRequest request, HttpSession httpSession) throws Exception;

	 List<State> statesearch(String s) throws Exception;

	 boolean StateSubdistrictmodifyReorg(StateForm sDForm, List<SubDistrictForm> listSubdistritNew, List<DistrictForm> listDistrictModify, HttpServletRequest request, HttpSession httpsession) throws Exception;

	 boolean SubdistrictmodifyReorg(StateForm sDForm, List<SubDistrictForm> listSubdistritNew, List<SubDistrictForm> listSubdistrictModify, HttpServletRequest request, HttpSession httpSession) throws Exception;

	 boolean villagemodifyReorg(StateForm sDForm, List<VillageForm> listNewVill, List<VillageForm> listModifyVill, HttpServletRequest request, HttpSession httpSession) throws Exception;

	 boolean modifyStateCrInfo(StateForm stateForm, HttpServletRequest request, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, boolean delflag, String deleteAttachmentId[], HttpSession httpSession)
			throws Exception;

	 List<Headquarters> getHeadquterDetailALL(Integer regionCode, String region_type) throws Exception;

	 String getStateNameEnglish(Integer stateCode);

	 boolean checkStateExist(Integer stateCode);

	/**
	 * Get All States For Extended Department Functionality.
	 * 
	 * @author Ripunj on 10-10-2014
	 * @param orgCode
	 * @param orgLocatedLevelCode
	 * @return
	 * @throws Exception
	 */

	 List<State> getStateSourceListExtended(Integer orgCode, Integer orgLocatedLevelCode) throws Exception;

	/**
	 * Get All States For Extended Department Functionality without
	 * selected @stateList.
	 * 
	 * @author Ripunj on 16-10-2014
	 * @param stateList
	 * @param orgCode
	 * @param orgLocatedLevelCode
	 * @return
	 */
	 List<State> getAllNotInStatesExtended(String stateList, Integer orgCode, Integer orgLocatedLevelCode);

	/* Added by Kirandeep for the gigw work on 23/12/2014 */

	//  List<String> getDetailsofDocument(String type);
	/* Added by Anju Gupta for the on 12/1/2015 */
	 List<State> getAllStates() throws Exception;

	/* added by ashish dhupia for menu disable functionality on 4/2/2015 */
	 List<MenuProfile> getDisableMenueList(Integer slc, Integer districtCode) throws Exception;

	 List<Search> getStateSearchDetailByCode(int entityCodeForSearch, String entityCode) throws Exception;

	/**
	 * Get all State List with operation state code used in coverage
	 * Administrative Entity at center level.
	 * 
	 * @author Pooja
	 * @since 21-10-2015
	 * @return
	 * @throws Exception
	 */
	 List<State> getStateListWithOperationState() throws Exception;
	
	/**
	 * Code used to get Is Pesa Act implemented for the state or not.
	 * 
	 * @author Pooja
	 * @since 19-05-2016
	 * @param stateCode
	 * @return
	 * @throws Exception
	 */
	 Boolean isPesaState(Integer stateCode) throws Exception;
	
	 Integer getStateCode(Integer districtCode) throws Exception;


	/*List<AllSearch> getAllSearchDetailByCode(String entityCodeForSearch, String entityCode,
			boolean isByCode) throws Exception;*/
	
	 boolean saveNodalOfficerDetail(NodalOfficerState nodalOfficerState)throws Exception;
	
	 boolean updateNodalOfficerDetail(NodalOfficerState nodalOfficerState,NodalOfficerState existNodalOfficerState)throws Exception;
	
	 Object[] getNodalOfficerDetails(Integer entityCode,Character entityType,Long userId,Boolean isNodalOfficeDet,Integer stateCode)throws Exception;
	
	 boolean sendOTPForLGDDataConfirmation(Long userId)throws Exception;
	
	 Character validateOTP(String userOTP,Long userId)throws Exception;
	
	 boolean saveLGDDataConfirmation(LgdDataConfirmation lgdDataConfirmation)throws Exception;
	
	 List<AuditTrailLGD> getAuditTrailLGD()throws Exception;

	 List<DistrictFreezeEntity> getDistrictwiseFreezeStatus(Integer stateCode,Character userType)throws Exception;
	
	 boolean saveDistrictUnfreezebyState(LgdDataConfirmation lgdDataConfirmation)throws Exception;
	
	 boolean getFreezeStatusbyState(Integer stateCode,Character userType,Character checkLevel)throws Exception;
	
	 boolean saveStateFreezeUnfreezebyState(LgdDataConfirmation lgdDataConfirmation)throws Exception;
	
	 Character getUserTypeofNodalOfficer(Long lUserId)throws Exception;
	
	 boolean getFreezeStatusbyUserId(Long userId,Character userType,Character level,Integer stateCode)throws Exception;
	
	 List<EntityFreezeStatus> getEntityFreezeStatus(Integer stateCode)throws Exception;
	
	 String getGisTokenValue()throws Exception;
	/* List<ParentwiseChildDetials> getUlbDeatilsList (Integer districtCode) throws Exception;*/
	 String getStateSetupType(Integer stateCode) throws Exception;
	 List<ParentwiseChildDetials> getparentwisecountofBPandGP(Integer districtCode) throws Exception;
	 List<DistrictFreezeEntity> getDistrictwiseFreezeStatusULB(Integer stateCode,Character userType)throws Exception;
	
	/* List<FreezeLocalBodyEntity> getListOfUlbs (Integer districtCode) throws Exception;*/
	
	List<FreezeLocalBodyEntity> freezeUnfreezeLocalBodyEntity(Integer districtCode, Character parentType,
			Character userType, Long userId) throws Exception;
	
	boolean saveConfigurationLGDUpdation(FreezeUnfreezeStateConfigEntity  freezeUnfreezeStateConfigEntity,LgdDataConfirmation lgdDataConfirmation)throws Exception;
	
	FreezeUnfreezeStateConfigEntity getConfigurationOfLGDDataConfirmation(Integer stateCode,Character userType) throws Exception;
	
	 boolean saveStateFreezeUnfreezebyStateOnly(Integer stateCode,Character userType,Character status,Long userId,String fileName)throws Exception;
	
	 List<OrganizationByCentreLevel> getOrganisationList(Integer stateCode) throws Exception;
	 
	 boolean saveConfigurationLGDUpdation(ConfigurationBlockVillageMapping saveconfigBlockVillageMapping) throws Exception;

	boolean saveconfigBlockVillageMapping(ConfigurationBlockVillageMapping configBlockVillageMapping) throws Exception;

	ConfigurationBlockVillageMapping getconfigureBlockVillage(Long userId, Integer stateCode) throws Exception;
	
	public Response saveEffectiveDateEntityState(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Long userId)throws Exception;
}
