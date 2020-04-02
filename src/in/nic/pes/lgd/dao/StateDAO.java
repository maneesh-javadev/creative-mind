package in.nic.pes.lgd.dao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.bean.AllSearch;
import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AuditTrailLGD;
import in.nic.pes.lgd.bean.ConfigurationBlockVillageMapping;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictFreezeEntity;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.EntityFreezeStatus;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.FreezeLocalBodyEntity;
import in.nic.pes.lgd.bean.FreezeUnfreezeStateConfigEntity;
import in.nic.pes.lgd.bean.GeneratedFileDetails;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.LgdDataConfirmation;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationByCentreLevel;
import in.nic.pes.lgd.bean.ParentwiseChildDetials;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.Search;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateHistory;
import in.nic.pes.lgd.bean.StateNamed;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.SubdistrictPK;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.StateForm;
import pes.attachment.util.AttachedFilesForm;

 public interface StateDAO {

	/*
	 *  String ChangeCrState(int stateCode, String stateNameEnglish,
	 * String stateNameEnglishch, int userId, String orderno, Date orderDate,
	 * Date efectivDate, Date gazpubDate, String orderPath, String stateOrUt,
	 * String stateNameLocal, String aliasEnglish, String aliasLocal, Session
	 * session, Integer slc) throws Exception;
	 */

	 List<StateNamed> createStateDAO(int userid, String state_name_english, String p_order_no, Date order_date, Date effective_date, char STATE_OR_UT, String census_2011_code, Date gaz_pub_date, String list_of_state_full,
			String list_of_state_part, String list_of_district_full, String p_short_name, String headQuarterName, Session session) throws Exception;

	/*
	 * added by kamlesh for finding duplicate state used in dwr on 18-march_13
	 */
	 int findDuplicate(String stateName);

	 List<AssemblyConstituency> getAllassembly() throws Exception;

	 List<State> getAllNotInStates(String stateList) throws Exception;

	 List<ParliamentConstituency> getAllparliament(int statecode) throws Exception;

	 List<AssemblyConstituency> getAllparliamentassembly(int statecode) throws Exception;

	 List<State> getAllStates() throws Exception;

	// modify

	 List<State> getAllStates(int stateCode) throws Exception;

	/* Retrieve files from the attachment table */
	 List<Attachment> getAttacmentdetail(int orderCode) throws Exception;

	 List<GeneratedFileDetails> getCBTHtml(String fName, String documentType, int documentId);

	 int getCurrentVersionbyStateCode(int stateCode) throws Exception;

	 int getCurrentVersionbyStateCode(int stateCode, Session session) throws Exception;

	 List<District> getdistrictListbyDistrict(int districtCode) throws Exception;

	 String getDistrictNameEnglishbyStateCode(int district_code) throws Exception;

	 List<State> getDistrictViewList(String query) throws Exception;

	/* Retrieving the Map details attachment from the database */
	 List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode, Session session) throws Exception;

	 List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws Exception;

	 List<Object[]> getGovtOrderByEntityCode(int entityCode, char type);

	 String getLBHierarchy(int parentLBCode) throws Exception;

	//  void ChangeState(int stateCode,String stateNameEnglish,int
	// userId,String Orderno,Date orderdate,String orderpath,Date
	// gazPubDate,Date effectivedate,String stateNameLocal,String
	// aliasEnglish,String aliasLocal, Session session)throws Exception;
	 List<MapLandRegion> getMapDetailsModify(int mapLandregionCode) throws Exception;

	 int getMaxStateCode(String query) throws Exception;

	 int getMaxVersionbyStateCode(int stateCode) throws Exception;

	 List<State> getStatebyname(String name) throws Exception;

	 List<State> getStateDetailsModify(int stateCode) throws Exception;

	 List<StateHistory> getStateHistoryDetail(char stateNameEnglish, int stateCode) throws Exception;

	 List<State> getStateList(String query) throws Exception;

	 String getStateNameEnglishbyStateCode(int stateCode) throws Exception;

	 List<Search> getStateSearchDetail(String entityName, String entityCode) throws Exception;

	 List<State> getStateTargetList(int stateCode) throws Exception;

	 List<State> getStateViewList(String query) throws Exception;

	 String getSubDistricNameEnglishbySubdistriccode(int subdistrict_code) throws Exception;

	 abstract State getSubDistrictDetail(StatePK statePK, Session session) throws Exception;

	 int getVersionByActiveState(int stateCode) throws Exception;

	 boolean modifyStateInfo(StateForm StateForm, StatePK statePk, int map_landRegionCode, Session session) throws Exception;

	 boolean modifyStateInfo(StateForm stateForm, String cord1, int stateCode, int map_landRegionCode, Session session) throws Exception;

	 boolean save(MapLandRegion mapLandRegion, Session session) throws Exception;

	boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm, List<AttachedFilesForm> attachedList, int vilcode, HttpSession session, Session ses);

	 boolean saveDataInAttach(StateForm stateForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception;

	 boolean saveDataInAttachGenerateState(GovernmentOrderForm govtOrderForm, GenerateDetails genDetails, int orderCode, Session ses);

	 boolean saveDataInAttachMap(StateForm stateForm, List<AttachedFilesForm> attachedList, HttpSession session);

	boolean saveDataInAttachWithOrderCode(GovernmentOrderForm governmentOrderForm, List<AttachedFilesForm> attachedList, int orderCode, Session ses);

	 int saveDataInMap(StateForm stateForm, List<AttachedFilesForm> attachedList, Session session) throws Exception;

	boolean saveState(State State, Session session) throws Exception;

	 void SetGovermentOrderEntity(String entityCode, char entityType) throws Exception;

	 boolean updatedistrict(boolean b, DistrictPK districtpk, Session session) throws Exception;

	 boolean updateIsActive(boolean isActive, StatePK statePK, Session session) throws Exception;

	 boolean updateIsActive(StatePK statePK, Session session) throws Exception;

	 boolean updateLReplaces(int lrReplacedby, StatePK statePK, Session session) throws Exception;

	 boolean updateLrReplaces(int lrReplaces, StatePK statePK, Session session) throws Exception;

	/*
	 *  Integer ChangeCrState(int stateCode, String stateNameEnglish, int
	 * userId, String orderno,Date orderDate,Date efectivDate,Date
	 * gazpubDate,String orderPath,String stateNameLocal, String aliasEnglish,
	 * String aliasLocal, Session session,Integer slc) throws Exception;
	 */

	/*
	 *  Integer ChangeCrState(int stateCode, String stateNameEnglish, int
	 * userId, String orderno,Date orderDate,Date efectivDate,Date
	 * gazpubDate,String orderPath,String stateOrUt,String stateNameLocal,
	 * String aliasEnglish, String aliasLocal, Session session,Integer slc)
	 * throws Exception;
	 */

	 boolean updateMapLanRegion(int mapCode, String coordinates, int stateCode, Session session1) throws Exception;

	 boolean updatesubdistrict(boolean b, SubdistrictPK subdistrictpk, Session session) throws Exception;

	 boolean updatevillage(boolean b, VillagePK villagepk, Session session) throws Exception;

	 String ChangeCrState(int stateCode, String stateNameEnglish, String stateNameEnglishch, int userId, String orderno, Date orderDate, Date efectivDate, Date gazpubDate, String orderPath, String stateOrUt, String stateNameLocal,
			String sortName, Session session, Integer slc) throws Exception;

	 List<State> getStateViewListSQL(String query) throws Exception;

	 String getStateNameEnglish(Integer stateCode);

	 boolean checkStateExist(Integer stateCode);

	/**
	 * Get All States For Extended Department Functionality.
	 * 
	 * @author Ripunj on 10-10-2014
	 * @return
	 * @throws Exception
	 */
	 List<State> getAllStatesExtended(Integer orgCode, Integer orgLocatedLevelCode) throws Exception;

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

	List<AllSearch> getAllSearchDetailByCode(String entityCodeForSearch, String entityCode, boolean isByCode) throws Exception;
	
	 boolean saveNodalOfficerDetail(NodalOfficerState nodalOfficerState)throws Exception;
	
	 boolean updateNodalOfficerDetail(NodalOfficerState nodalOfficerState,NodalOfficerState existNodalOfficerState)throws Exception;
	
	 Object[] getNodalOfficerDetails(Integer entityCode,Character entityType,Long userId,Boolean isNodalOfficeDet,Integer stateCode)throws Exception;
	
	 boolean sendOTPForLGDDataConfirmation(Long userId)throws Exception;
	
	 Character validateOTP(String userOTP,Long userId)throws Exception;
	
	 boolean saveLGDDataConfirmation(LgdDataConfirmation lgdDataConfirmation)throws Exception;
	
	 List<DistrictFreezeEntity> getDistrictwiseFreezeStatus(Integer stateCode,Character userType)throws Exception;
	
	 boolean saveDistrictUnfreezebyState(LgdDataConfirmation lgdDataConfirmation)throws Exception;
	
	 boolean getFreezeStatusbyState(Integer stateCode,Character userType,Character checkLevel)throws Exception;
	
	 boolean saveStateFreezeUnfreezebyState(LgdDataConfirmation lgdDataConfirmation)throws Exception;
	
	 Character getUserTypeofNodalOfficer(Long lUserId)throws Exception;
	
	 boolean getFreezeStatusbyUserId(Long userId,Character userType,Character level,Integer stateCode)throws Exception;
	
	 List<EntityFreezeStatus> getEntityFreezeStatus(Integer stateCode)throws Exception;
	
	 String getGisTokenValue()throws Exception;
	
	 List<AuditTrailLGD> getAuditTrailLGD()throws Exception;
	
	
	
	 String getStateSetupType(Integer stateCode) throws Exception;
	 List<ParentwiseChildDetials> getparentwisecountofBPandGP(Integer districtCode) throws Exception;
	 List<DistrictFreezeEntity> getDistrictwiseFreezeStatusULB(Integer stateCode,Character userType)throws Exception;
	
	
	 List<FreezeLocalBodyEntity> freezeUnfreezeLocalBodyEntity (Integer districtCode,Character parentType,Character userType,Long userId) throws Exception;
	
	 boolean saveConfigurationLGDUpdation(FreezeUnfreezeStateConfigEntity  freezeUnfreezeStateConfigEntity,LgdDataConfirmation lgdDataConfirmation)throws Exception;
	
	FreezeUnfreezeStateConfigEntity getConfigurationOfLGDDataConfirmation(Integer stateCode,Character userType) throws Exception;
	
	boolean saveStateFreezeUnfreezebyStateOnly(Integer stateCode, Character userType,Character status,Long userId,String fileName) throws Exception ;
	
	List<OrganizationByCentreLevel> getOrganisationList(Integer stateCode) throws Exception ;
	
	  boolean saveConfigurationLGDUpdation(ConfigurationBlockVillageMapping saveconfigBlockVillageMapping) throws Exception;

	ConfigurationBlockVillageMapping getconfigureBlockVillage(Long userId, Integer stateCode);
	
	public Response saveEffectiveDateEntityState(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Long userId)throws Exception;
}
