package in.nic.pes.lgd.dao;

import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.ChangeCoverageNameParentofDisturbLocalbody;
import in.nic.pes.lgd.bean.ChangeLocalBodyCoveredArea;
import in.nic.pes.lgd.bean.ChangeLocalBodyName;
import in.nic.pes.lgd.bean.ChangeLocalBodyUrbanType;
import in.nic.pes.lgd.bean.ChangeLocalBodypriType;
import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.ChildLocalGovtBody;
import in.nic.pes.lgd.bean.ConstituencyLocalbody;
import in.nic.pes.lgd.bean.ConstituencyMapDetailsbyacCode;
import in.nic.pes.lgd.bean.ConstituencyVillage;
import in.nic.pes.lgd.bean.ConstituencyWard;
import in.nic.pes.lgd.bean.CoveredWardLandregion;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GetLandRegionNameforWard;
import in.nic.pes.lgd.bean.GetLandRegionWise;
import in.nic.pes.lgd.bean.GetLocalBodyTypeCode;
import in.nic.pes.lgd.bean.GetLocalGovtBodyTypeList;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.Habitation;
import in.nic.pes.lgd.bean.InsertLocalGovtBody;
import in.nic.pes.lgd.bean.LBCoverageWard;
import in.nic.pes.lgd.bean.LbCoveredLandregion;
import in.nic.pes.lgd.bean.LgdPfmsMapping;
import in.nic.pes.lgd.bean.LocalBodyCoveredArea;
import in.nic.pes.lgd.bean.LocalBodyCoveredAreaLB;
import in.nic.pes.lgd.bean.LocalBodyDetails;
import in.nic.pes.lgd.bean.LocalBodyHistory;
import in.nic.pes.lgd.bean.LocalBodyParent;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTypeForStateWiseView;
import in.nic.pes.lgd.bean.LocalBodyTypeHistory;
import in.nic.pes.lgd.bean.LocalBodyTypeWiseDepartment;
import in.nic.pes.lgd.bean.LocalBodyUrbanType;
import in.nic.pes.lgd.bean.LocalBodyViewChild;
import in.nic.pes.lgd.bean.LocalGovtBody;
import in.nic.pes.lgd.bean.LocalGovtBodyForSelectedBody;
import in.nic.pes.lgd.bean.LocalGovtBodyNameList;
import in.nic.pes.lgd.bean.LocalGovtBodyWard;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyReplaces;
import in.nic.pes.lgd.bean.LocalbodyUnMappedBody;
import in.nic.pes.lgd.bean.LocalbodyUnMappedBodyLevelWise;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.LocalbodyWardId;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.LocalbodyforStateWiseFinal;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.NodalOfficer;
import in.nic.pes.lgd.bean.ParentWiseLBList;
import in.nic.pes.lgd.bean.ParentWiseLocalBodies;
import in.nic.pes.lgd.bean.ParentWiseLocalBodiesWithoutChildCount;
import in.nic.pes.lgd.bean.PartillyMappedLRList;
import in.nic.pes.lgd.bean.PartillyMappedLRListLevelWise;
import in.nic.pes.lgd.bean.PushLBtoPES;
import in.nic.pes.lgd.bean.Pushcoveragetopes;
import in.nic.pes.lgd.bean.SearchLocalGovtBody;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatewisePesaPanchyat;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.UlbBean;
import in.nic.pes.lgd.bean.UnLRDistrictWiseList;
import in.nic.pes.lgd.bean.UnLRSWiseList;
import in.nic.pes.lgd.bean.ViewLandRegionDisturbedlist;
import in.nic.pes.lgd.bean.ViewLocalBodyLandRegion;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.localbodywardtemp;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LGBodyCoveredAreaDTO;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;
import in.nic.pes.lgd.forms.NodalOfficerForm;
import in.nic.pes.lgd.forms.WardForm;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.org.ep.exception.BaseAppException;

public interface LocalGovtBodyDAO {

	
	public int getMaxRecords(String query) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public int saveLocalBodyDetails(Localbody localbody, Session session) throws Exception; //

// TODO Remove unused code found by UCDetector
// 	public int saveLocalBodyDetailsFinal(Localbody localbody, Session session) throws Exception;

	// public int getRecordsforLocalBodyName(String localBodyName);

	public int saveOrderDetails(GovernmentOrder governmentOrder, Session session) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<Localbody> getLocalGovtBodyforCoveredLocalbodyList(int localBodyCode, Session session) throws Exception;

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistrictList(int localBodyCode) throws Exception;
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistrictListInter(int localBodyCode) throws Exception;

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredVillageFinalList(int localBodyCode) throws Exception;
	
	public List<Localbody> getLocalGovtBodyMappedVillageList(int landRegionCode,int lbtypecode) throws Exception;

	public List<District> getDistrictNamebyDisID(int distCode) throws Exception;

	public List<Village> getconVillageNamebyVillID(int villageCode) throws Exception;

	public List<Localbody> getLocalBodyNamebyLBID(int localbodyCode) throws Exception;
	
	public int getLocalBodybyLBID(int localbodyCode) throws Exception;  

	public List<LocalBodyType> getLocalBodyTypeNamebyLBTID(int localbodytypeCode) throws Exception;

	public List<Subdistrict> getSubDistrictNamebyDisID(int subdisCode) throws Exception;
	
	/**
	 * The {@code getDistrictNamebyCovChangeULB} return all District Names based on {@param availlgdLBInterCAreaSourceListUmappedUrban}.  
	 * @param availlgdLBInterCAreaSourceListUmappedUrban
	 * @return
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	public String getDistrictNamebyCovChangeULB(String availlgdLBInterCAreaSourceListUmappedUrban) throws Exception;

	public boolean saveOrderDetailsEntityWise(GovernmentOrderEntityWise governmentOrderEntityWise, Session session) throws Exception;

	public boolean saveLandRegionReplacesDetails(LocalbodyReplaces localbodyReplaces, Session session) throws Exception;

	public boolean saveLbLandRegionCoveredDetails(LbCoveredLandregion lbCoveredLandregion, Session session) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public boolean saveLandRegionReplacedByDetails(LocalbodyReplacedby localbodyReplacedby, Session session) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public boolean updateLocalBodyDetails(Session session, Localbody lbObj) throws Exception;

	public List<District> getDistrictListbyStateCode(int stateCode) throws Exception;

	public List<LocalBodyType> getLGTypeForGovtBody() throws Exception;

// TODO Remove unused code found by UCDetector
// 	public boolean saveMapLandRegion(MapLandRegion mapLandRegion, Session session) throws Exception;

	public boolean updateMapLanRegion(int mapCode, String coordinates, int villageCode) throws Exception; // NO_UCD (unused code)

	public List<Localbody> getULBListINDistrict(int districtCode) throws Exception;

	public List<Localbody> getLocalbodyViewList(String query) throws Exception;

	public List<District> getUnmappedLocalbodyDistList(String query) throws Exception;

	public List<Subdistrict> getUnmappedSubDistLocalbodyViewList(String query) throws Exception;
	
	public List<Subdistrict> getDeleteSubDistMapped(int distlist,int subdistlist) throws Exception;
	
	public List<Village> getDeleteVillageMapped(int subdistlist) throws Exception;
	public List<Village> getDeleteVillageMappedDP(int subdistlist,int villagelist) throws Exception;
	
	public List<Village> getDeleteVillageMappedforSubDist(int subdistlist,int villagelist) throws Exception;

	public List<Village> getUnmappedVillageLocalbodyViewList(String query) throws Exception;

	public List<ParentWiseLocalBodies> getLocalGovtBodySubDistList(int localBodyCode, int stateCode) throws Exception;

	// Localbody
	// Added 1-1-2011
	public List<Localbody> getLGBodySubDistListByString(String localBodyCodeList) throws Exception; // NO_UCD (unused code)

	public List<Localbody> getLocalBodyList(int localBodyCode, int stateCode) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<Localbody> getLBListByState(int stateCode) throws Exception;

	public List<LocalGovtBody> getLocalBodySubList(int localBodyCode, int stateCode) throws Exception;

	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcode(int localBodyCode) throws Exception;
	
	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcodeChangeCoverage(int localBodyCode,int lbCode) throws Exception;

	public List<ParentWiseLocalBodies> getchildlocalbodiesWithoutCountByParentcode(int localBodyCode) throws Exception;

	public List<ParentWiseLocalBodiesWithoutChildCount> getchildlocalbodiesByParentcodeWithoutChild(int localBodyCode) throws Exception;

	public List<Localbody> getLocalGovtBodyVillageList(int localBodyCode) throws Exception;

	public List<Localbody> getLocalGovtBodyParentList(int localBodyCode) throws Exception;

	public List<LocalbodyUnMappedBody> getLocalGovtBodyforUnmappedLocalBodyList(char type, int localBodyCode) throws Exception;
	public List<LocalbodyUnMappedBodyLevelWise> getLocalGovtBodyforUnmappedLocalBodyListLevelWise(char localBodyType, int stateCode,char level) throws Exception;

	public int getCurrentLocalbodyVersion(int localbodyCode) throws Exception;

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredVillageList(int localBodyCode) throws Exception;

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredVillageListforLB(int localBodyCode) throws Exception;

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredVillageListforLBforMCov(int localBodyCode) throws Exception;
	
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredDistrictListforLBforMCov(int localBodyCode) throws Exception;

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredVillageListFinal(int localBodyCode) throws Exception;

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictList(int localBodyCode) throws Exception;
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListDistrict(int localBodyCode) throws Exception;

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredDistrictListFinalforLB(int localBodyCode) throws Exception;
	
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistrictListFinalforLBbyDIstCode(int localBodyCode,int districtCode) throws Exception;
	
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredVillageListFinalforLBbySubDIstCode(int localBodyCode,int subdistrictCode) throws Exception;

	public List<LocalBodyCoveredArea> getSubDistrictListFinal(int coverageCode, int districtCode) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<Localbody> getLocalGovtBodyforCoveredLocalbodyList(int localBodyCode) throws Exception; //

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistList(int localBodyCode) throws Exception;
	
	public List<LocalbodyUnMappedBodyLevelWise> getLocalGovtBodyforUnmappedLocalBodyListLevelWiseFinal(char localBodyType, List<String> arraySubDistrict,char level) throws Exception;

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistListFinal(int localBodyCode) throws Exception;
	
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistListFinalChangeCov(int localBodyCode) throws Exception;

	public List<LBCoverageWard> getLocalGovtBodyforCoveredSubDistListforWard(String localBodyCode) throws Exception;

	public List<LBCoverageWard> getLGBforCoveredVillageListExWard(int localBodyCode) throws Exception;

	public List<LocalbodyforStateWise> getLocalBodyListStateWise(char localBodyType, int stateCode) throws Exception;
	
	public List<LocalbodyforStateWise> getLocalBodyListStateWiseChangeType(char localBodyType, int stateCode,int lbtypecode) throws Exception;
	

	public List<LocalbodyforStateWise> getLocalBodyListStateWiseTierSet(char localBodyType, int stateCode, int parentyresetupCd) throws Exception;

	public List<LocalbodyforStateWise> getLocalBodystatelist(char localBodyType, int stateCode) throws Exception;

	public List<LocalBodyTypeForStateWiseView> getLocalBodyListStateWiseView(int stateCode) throws Exception;

	public List<State> getStateName(int stateCode) throws Exception;

	public List<Localbody> getLocalbodyname(String parentLBodyCode) throws Exception;

	public List<LocalGovtBodyForSelectedBody> getLocalBodyListForSelectedBody(int localBodyTypeCode, int stateCode) throws Exception;

	public List<LocalBodyViewChild> getLocalBodyListForSubDistBody(int localBodyTypeCode, int stateCode, int localBodyCode) throws Exception;

	public List<LocalBodyUrbanType> getLocalBodyUrbanName(int localBodyCode, String urbanTypeBodyCode, int stateCode, int govtLocalBodyType) throws Exception;

	public List<LocalBodyType> getGovtTypeViewList(String query) throws Exception;

	public List<LocalGovtTypeDataForm> getLocalBodyTypeDetails(int localBodyTypeCode) throws Exception;

	public List<ChildLocalGovtBody> getChildLocalBodyListForSelectedBody(int localBodyTypeCode) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<LocalbodyforStateWise> getLocalBodyListAtVillageLevel(int stateCode) throws Exception;

	public int getLocalBodyTypeCode(char category) throws Exception;

	public String getLocalBodyTypeCode1(char category) throws Exception;

	public String getLocalBodyTypeCodeNewWard(char category, char level) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public String getLocalBodyTypeCodeNewWardParentInter(char category, char lblevel) throws Exception;

	public String getLocalBodyTypeCodeNewWardParentInterDistrict(char category, char lblevel) throws Exception;

	public String getLocalBodyTypeCode1Inter(char category) throws Exception;

	public String getLocalBodyTypeCode1Village(char category) throws Exception;

	public List<LocalbodyListbyState> getdistrictPanchayatList(int lbTypeCode, int stateCode) throws Exception;
	
	public List<LocalbodyListbyState> getdistrictPanchayatListChangeTier(int lbTypeCode,int stateCode,int parentLbCode) throws Exception;
	
	public List<LocalbodyListbyState> getdistrictPanchayatListChangeCoverage(int lbTypeCode,int stateCode,int lbCode) throws Exception;

	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeD(char lbTypeCode, int stateCode) throws Exception;

	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeI(char lbTypeCode, int stateCode) throws Exception;

	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeV(char lbTypeCode, int stateCode) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public String getUrbanLocalBodyTypes(char category) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public boolean updateLocalBodyDetailsUpdate(Session session, LocalbodyPK localBodyPK, int localBodyReplacedByCode) throws Exception;

	public List<Localbody> getGovtLocalBodyTypeDetails(int localBodyCode) throws Exception;

	public List<LocalBodyDetails> getGovtLocalBodyDetails(int localBodyCode) throws Exception;

	public List<SearchLocalGovtBody> getLocalGovtBodySearchDetail(String entityName, String entityCode) throws Exception;

	public List<LocalBodyTypeHistory> getLocalBodyTypeHistory(String query) throws Exception;

	public List<GetLocalBodyTypeCode> getUrbanLocalBodyTypes1(char category) throws Exception;

	public List<LocalBodyCoveredArea> getCoveredAreaforULB(int localbodyCode) throws Exception;

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBforLB(int localbodyCode) throws Exception;

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBforLBforMF(int localbodyCode) throws Exception;

	public String getlocalbodyNamebyCode(int localbodyCode) throws Exception;

	public List<LocalGovtBodyWard> getlocalGovtBodyWardList(int localBodyCode) throws Exception;

	public abstract boolean invalidateWard(int wardCode, int userid) throws Exception;

	public boolean deleteWard(int wardCode, int userid) throws Exception;

	@SuppressWarnings("rawtypes")
	public List getUnmappedLBDistList(char category, int distCode) throws Exception;

	public List<PartillyMappedLRList> getPartillymappedLBDistList(char c, int stateCode, char category) throws Exception;

	public List<PartillyMappedLRList> getPartillymappedLBDistListFinal(char landRegionType, List<String> districtCode, char category) throws Exception;
	
	public List<PartillyMappedLRListLevelWise> getPartillymappedLBDistListFinalLevelWise(char landRegionType, List<String> arraySubDistrict, char category,char level) throws Exception;

	public List<LocalBodyCoveredArea> getCoveredSubDistrictLocalBody(int localBodyCode) throws Exception;

	public List<GetLocalBodyTypeCode> getTypeListbylevel(char typeCode, char category) throws Exception;

	// modify ward
	public List<LocalbodyWard> getwardDetail(int wardCode) throws Exception;

	public List<CoveredWardLandregion> getCoverageLangRegion(int wardCode) throws Exception;

	public List<GetLandRegionNameforWard> getDistrictNameWard(int wardCode) throws Exception;

	public List<GetLandRegionNameforWard> getSubDistrictNameWard(int wardCode) throws Exception;

	public List<GetLandRegionNameforWard> getVillageNameWard(int wardCode) throws Exception;

	//public boolean update(LocalGovtBodyForm localGovtBodyForm, LocalbodyWardId wardpk, Session session1) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public int getMaxWardVersion(int wardCode) throws Exception;

	public int getWardCurrentVersion(int wardCode) throws Exception;

	public boolean updateisActive(LocalbodyWard organizationbeanisActive, LocalbodyWardId wardpk, Session session1) throws Exception; // NO_UCD (unused code)

	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<MapLocalbody> getMapDetails(int mapLandregionCode) throws Exception;

	public List<LBCoverageWard> getLGBforCoveredDistListExWard(int localBodyCode) throws Exception;

	public List<LBCoverageWard> getLGBforCoveredSubDistListExWard(int localBodyCode) throws Exception;

	public List<LBCoverageWard> getLGBforCoveredVillListExWard(int localBodyCode) throws Exception;

	public List<LBCoverageWard> getLGBforCoveredSubDistUrbanListExWard(int localBodyCode) throws Exception;

	public List<LocalBodyTypeWiseDepartment> getLocalBodyTypeWiseDeptList(int stateCode, int lBTypeCode, char levelCode) throws Exception;

	public List<LocalBodyTypeWiseDepartment> getLocalBodyWiseDeptList(int stateCode, int lBCode, char levelCode) throws Exception;

	public boolean updateOrderDetails(GovernmentOrder governmentOrder, Session session) throws Exception;

	public List<LocalbodyListbyState> getExistingPanchayatList(int lbTypeCode, int stateCode) throws Exception;

	public List<UnLRSWiseList> getUnMapLRStaWiseList(char type, int stateCode) throws Exception;

	public List<UnLRDistrictWiseList> getUnMapLRDistWiseList(char type, int districtCode) throws Exception;

	public List<PartillyMappedLRList> getPartlyMapLRStaWiseList(char type, int stateCode) throws Exception;

	public GovernmentOrder viewgovernmentOrder(int orderCode);

	public List<LocalbodyWard> getwardByWardCode(int wardCode);

	public int getRecordsforLocalBody(String localBodyName, String parentlbName, String lbType, char lbtypeULB,String lbTypeCode) throws Exception; // added the one more parameter for checking the existing name 14/1/2014

	public int getRecordsforWardNumber(String wardNumber, int lbName) throws Exception;

	public int getlblc(int localbodyCd) throws Exception;

	public int getRecordsforWardNumberModify(String wardNumber, int lblc) throws Exception;

	public List<Localbody> getULBListByDistrict(int districtCode) throws Exception;

	public List<CheckAuthorization> getSubDistrictList(int districtcode) throws Exception;

	public List<CheckAuthorization> getvillageList(int villagecode) throws Exception;

	public List<LocalBodyCoveredArea> getvillageListFinal(int coverageCode, int districtCode) throws Exception;

	public boolean saveDataInAttachGenerateLocalBodyCoverageChange(LocalGovtBodyForm localGovtBodyForm, HttpSession httpsession, GovernmentOrder governmentOrder)
			throws Exception;

	public List<InsertLocalGovtBody> saveLocalBody(LocalGovtBodyForm localGovtBodyForm, String lgTypeName, String lgtypecode, int stateCode, Integer userId,
			String districtPanchayatListforDP) throws Exception;

	public List<InsertLocalGovtBody> saveLocalBodyULB(LocalGovtBodyForm localGovtBodyForm, String lgtypecode, int stateCode, Integer userId, String panchayatDP)
			throws Exception;

	public List<PushLBtoPES> saveLocalBodyinPES(int localbodycode) throws Exception;

	public List<Pushcoveragetopes> saveCoverageinPES(int localbodycode) throws Exception;

	public List<ChangeLocalBodyUrbanType> modifylocalbodyurbantype(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, Integer stateCode,Integer userId);

	public boolean saveLocalBodyWard(List<LocalBodyDetails> lbDetails, LocalGovtBodyForm localGovtBodyForm, Session session, HttpServletRequest request,
			Integer local_body_code, String coverageList, int userid);

// TODO Remove unused code found by UCDetector
// 	public boolean saveDataInMapLB(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, HttpSession httpsession,
// 			List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList);

	public List<ChangeLocalBodypriType> modifylocalbodypritype(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, Integer userId)
			throws Exception;

	public boolean saveDataInMapLocalBody(LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, HttpSession session);

	public boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList,
			HttpSession session) throws Exception;

	public boolean saveDataInAttachCoverageLBody(List<AttachedFilesForm> attachedList, HttpSession session, int getordercode) throws Exception;

	public boolean saveDataInAttachGenerateLocalBodyUrbanTypeModify(LocalGovtBodyForm localGovtBodyForm, HttpSession session, int getordercode)
			throws Exception;

	public boolean saveDataInAttachGenerateLocalBody(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm localGovtBodyForm, HttpSession session)
			throws Exception;

	public List<LocalGovtBody> viewLocalbodyDetails(Integer localBodyCode);

	public GovernmentOrder viewgovernmetorderDetails(Integer orderCode);

	
	public List<GovernmentOrder> GovtOrederDetails();

	
	/**
	 * @param localGovtBodyForm
	 * @param request
	 * @param httpsession
	 * @param isULBDisttLevel (New Added Parameter)
	 * @return
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	public List<ChangeLocalBodyCoveredArea> modifylocalbodyforCoveredArea(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession, boolean isULBDisttLevel) throws Exception;
	
	public List<ChangeCoverageNameParentofDisturbLocalbody> modifyDisturbedLocalBodyForcoverageAreaNameParent(LocalGovtBodyForm localGovtBodyForm,LocalGovtBodyForm localGovtBodyFormChngeName,GovernmentOrderForm govtOrderForm,HttpServletRequest request,HttpSession httpsession) throws Exception;
	
	public List<GetLandRegionWise> getLandRegionWise(Integer localbodytypecode, char code, Integer districtcode, String lbtype) throws Exception;

	/*
	 * public List<GetLandRegionWise> getLandRegionWiseInter(Integer
	 * localbodytypecode,Integer districtcode) throws Exception; public
	 * List<GetLandRegionWise> getLandRegionWiseVill(Integer
	 * localbodytypecode,Integer districtcode) throws Exception;
	 */

	public List<UlbBean> getLandRegionWiseUrban(Integer localbodytypecode, Integer districtcode) throws Exception;

	public List<UlbBean> getPanchayatListbylocalbodyUrban(Integer districtcode, Integer localbodytypecode, int offset, int limit) throws Exception;

	public List<ViewLocalBodyLandRegion> viewLandRegionDistrictName(int localBodyCode);

	public List<ViewLocalBodyLandRegion> viewLandRegionsubDistrictName(int localBodyCode);

	public List<ViewLocalBodyLandRegion> viewLandRegionvillageName(int localBodyCode);
	public List<ViewLocalBodyLandRegion> viewLandRegionvillageNameF(int localBodyCode); 

	public List<LocalbodyWard> getWardListFromContributingMunicipality(int localBodyCode);

	// Tanuj
	public String getCategoryFromLocalBodyTypeCode(int localBodyType, Session session) throws Exception;

	public List<LocalbodyforStateWiseFinal> getLocalBodyListStateCategoryWise(Integer localBodyType, Integer stateCode,Integer  plblc,Integer lbCode,String lbName) throws Exception;

	public List<ParentWiseLBList> getParentWiseLBList(int localBodyCode) throws Exception;

	public int getLBTypeCodeFromLBCode(int localBodyCode) throws Exception;

	public List<Localbody> getLocalGovSetupWiseLocalbodyList(int localBodyCode) throws Exception;

	public List<Localbody> getLocalGovSetupWiseLocalbodyListByName(String localBodyName) throws Exception;

	public List<Localbody> getLocalBodyDetailsModify(int localBodyCode) throws Exception;

	public int getMaxLocalBodyVersionbyCode(int localBodyCode) throws Exception;

	public List<Attachment> getAttacmentdetail(int orderCode) throws Exception;

	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode, Session session) throws Exception;

	public List<GetLocalGovtBodyTypeList> getLBGTypeList(int stateCode, Session session) throws Exception;

	/*
	 * public List<LocalbodyWard> getWardDetailsDAO(int lblc,int offset,int
	 * limit);
	 */
	public int countWardDetailsDAO(int lblc);

	public Integer getmaxgovtordercode();

// TODO Remove unused code found by UCDetector
// 	public boolean saveDataIngovtorderentity(LocalGovtBodyForm localGovtBodyForm, GovernmentOrderEntityWise governmentOrderEntityWise, Session httpsession);
	
	public boolean saveDataIngovtorderentityD(LocalGovtBodyForm localGovtBodyForm,GovernmentOrderEntityWise governmentOrderEntityWise,Session httpsession) throws Exception;
	
	public boolean saveDataIngovtorderentityGenerate(LocalGovtBodyForm localGovtBodyForm,
			GovernmentOrderEntityWise governmentOrderEntityWise,
			HttpSession httpsession) throws Exception;
	
	public MapAttachment saveDataInMapLBGovtOrderCorrect(LocalGovtBodyForm localGovtBodyForm,GovernmentOrder governmentOrder,Session session, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList) throws Exception; 
	
// TODO Remove unused code found by UCDetector
// 	public boolean saveDataInMapLBGovtOrderCorrectWithoutMap(LocalGovtBodyForm localGovtBodyForm,GovernmentOrder governmentOrder,Session session, List<AttachedFilesForm> attachedList) throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public String saveDataInMapLBGovtOrderCorrectWithoutOrderCode(LocalGovtBodyForm localGovtBodyForm,GovernmentOrder governmentOrder,Session session, List<AttachedFilesForm> attachedList,List<AttachedFilesForm> attachedMapList) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public int saveDataInMapLBGovtOrderCorrectWithoutOrderCodeAndMap(LocalGovtBodyForm localGovtBodyForm,GovernmentOrder governmentOrder,Session session, List<AttachedFilesForm> attachedList) throws Exception;
	
	public int saveDataInMapLBGovtOrderCorrectWithMap(LocalGovtBodyForm localGovtBodyForm,GovernmentOrder governmentOrder,Session session, List<AttachedFilesForm> attachedList,List<AttachedFilesForm> attachedMapList) throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public int saveDataInMapLBGovtOrderCorrectWithMapMerge(LocalGovtBodyForm localGovtBodyForm,GovernmentOrder governmentOrder,Session session, List<AttachedFilesForm> attachedList,List<AttachedFilesForm> attachedMapList) throws Exception;
	
	public boolean deleteMapAttachementforLB(Integer entityCode,char entityType,Session session) throws Exception;
		
	public int saveDataInMapLBGovtOrderCorrectWithoutOrderCodeM(LocalGovtBodyForm localGovtBodyForm,GovernmentOrder governmentOrder,Session session, List<AttachedFilesForm> attachedList) throws Exception;

	public int saveDataGovtOrder(LocalGovtBodyForm localGovtBodyForm,GovernmentOrder governmentOrder,Session session, List<AttachedFilesForm> attachedList) throws Exception;
	
	public int saveDataGovtOrderwithoutMap(LocalGovtBodyForm localGovtBodyForm,GovernmentOrder governmentOrder,Session session, List<AttachedFilesForm> attachedList) throws Exception;
	
	public boolean saveLatLongDet(LocalGovtBodyForm localGovtBodyForm,Session session) throws Exception;
	public boolean saveLatLongDetMapSame(LocalGovtBodyForm localGovtBodyForm,Session session) throws Exception;
	
	public List<ViewLocalBodyLandRegion> viewCoveragearea(int localBodyCode);

	/**
	 * @param localGovtBodyForm
	 * @param maxlbcode
	 * @param request
	 * @param httpsession
	 * @param catagery
	 * @param isULBDisttLevel (New Added Parameter)
	 * @return
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	public boolean correctlocalbodyforCoveredArea(LocalGovtBodyForm localGovtBodyForm, Integer maxlbcode, HttpServletRequest request, HttpSession httpsession, String catagery, boolean isULBDisttLevel) throws Exception;

	public List<ViewLandRegionDisturbedlist> viewGpdisturbedlist(int localBodyCode);

	/*
	 * public Integer modifylocalbodyforname(LocalGovtBodyForm
	 * localGovtBodyForm,List<AttachedFilesForm> attachedList,
	 * List<AttachedFilesForm> attachedMapList, HttpServletRequest request,
	 * HttpSession httpsession);
	 */

	public List<ChangeLocalBodyName> modifylocalbodyforname(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession)
			throws Exception;

	public String invalidatePRIDAO(LocalGovtBodyForm districtForm, Session hsession, int uid, HttpSession httpsession);

	public String invalidateTRIDAO(LocalGovtBodyForm districtForm, Session hsession, int uid, HttpSession httpsession);

// TODO Remove unused code found by UCDetector
// 	public String invalidateURBDAO(LocalGovtBodyForm districtForm, Session hsession, int uid, HttpSession httpsession);

	public List<GetLandRegionWise> getLandRegionWise(Character localbodytypecode, int districtcode, String lbtype);

	public boolean saveOrderDetailsfordeletedepartment(GovernmentOrder governmentOrder);

// TODO Remove unused code found by UCDetector
// 	public boolean saveDataIngovtorderentityfordeletedepartment(GovernmentOrderEntityWise governmentOrderEntityWise);

	public List<LocalbodyListbyState> getdistrictPanchayatListforselected(int lbTypeCode, int stateCode, int localBodyCode) throws Exception;

	public List<LocalbodyListbyState> getdistrictPanchayatListbydistrictcode(String type, Integer districtcode, Integer lbtype) throws Exception;

	public boolean saveDataInAttachmentGenerate(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm LBForm, HttpSession session, int getordercode,
			Session hsession) throws Exception;

	public int countLocalBodyDetails(Integer lbtype, int stateCode);

	public List<Localbody> getPanchayatListbylocalbody(int lbtypeCode, int stateCode, int offset, int limit);

	public List<Localbody> getLocalbodylist(int stateCode);

	public List<Localbody> getPanchayatListbylocalbodyVillagepanchayat(int lbtypeCode, int stateCode, String intermediatePanchayatCodes, int offset, int limit);

	public int countLocalBodyDetailsforVillagePanchayat(Integer lbtype, int stateCode, String intermediatePanchayatCodes);

	public List<LocalGovtBodyWard> getlocalGovtBodyWardListforpagination(int localBodyCode, int offset, int limit);

	public int countwardDetails(int lblc);

	public boolean modifyWardData(LocalbodyWard lbward, HttpServletRequest request, Session session) throws Exception;

	public Integer getmaxlandregioncode();

	public Integer getlbcoverdregioncode(int lbcode) throws Exception;
	
	public Integer getParentLblccode(int lbcode) throws Exception;

	// public List<LocalbodyWard> getWardDetailsDAO(int localbodyCode);
	public String getLocalBodyName(int lblc) throws Exception;

	public String isVillageExist(int[] localbodyCode, char lbType);

	public String getLocalBodyTypeCode(char category, char level) throws Exception;

	public int getLocalBodyParentTypeCode(char category, char level);

	public List<LocalbodyWard> getWardDetailsDAO(int lblc);

	public String getLocalBodyTypeMessagebyLocalbodyCode(Integer localbodyCode);

	public List<GetLocalGovtSetup> getLocalbodyDetailDAO(int stateCode) throws Exception;

	public List<LocalbodyListbyState> getLandRegionByDistricCode(Integer localBodyTypeCode, Integer districtCode, String lbType) throws Exception;

	public List<ChangeLocalBodyName> saveLocalBodyDraft(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, UserSelection userSelection);

	public List<LocalbodyListbyState> getmappedlbforPConsituency(int lbTypeCode, int stateCode, int pcCode,char type) throws Exception;

	public List<LocalbodyListbyState> getunmappedlbforPConsituency(int lbTypeCode, int stateCode, int pcCode,char type) throws Exception;

	public boolean ULBExistforConvert(int slc, int type, String ulbName);

	public List<LGBodyCoveredAreaDTO> getLGBodyCoveredAreaDetail(Integer localBodyCode); // added by sushil on 13-03-2013
	
	public String getdisturblbReasonVP(int lbCode,char type)throws Exception;
	
	public String getdisturblbReasonIP(int lbCode,char type)throws Exception;
	
	public String getdisturblbReasonDP(int lbCode,char type)throws Exception;
	
	public List<LocalbodyListbyState> getMappedVillageWardofConsituency(int PcCode,char consituencyType, char entityType,int slc)throws Exception;
	
	public String mergeULB(LocalGovtBodyForm lbForm,Integer userid ,Session session);
	
	public boolean mapZpConsituencyDetail(String wardNo, String lbList,String vilList,String deltedLb, String deletedVil ) throws Exception;
	
	public List<ParentWiseLocalBodies>  mappedZpWardConsituencyDetail(String wardNo ,char type,boolean wholeData) throws Exception;
	
	/*Changed by Kirandeep on 1/09/2014 */
	public Integer WardExist(int lblc,String wardName,int type);
	
	public List<LocalBodyCoveredArea> getNewWardVillageCoverage(String entityCode) throws Exception;
	
    public List<Localbody> getChildLbByParentLb(int parentlblc,int slc) throws Exception;
	
	public boolean updateLbPesaStatus(String lbcode,int slc,String deletedlbcode) throws Exception;
	
	/*public List<Localbody> getactiveLbPesa(int slc);*/
	 /**
	  * Change For LGD CODE by Maneesh Kumar 19Sep2014
	  */
	public List<StatewisePesaPanchyat> getactiveLbPesa(Integer slc);
	
	public List<Localbody> getactiveLbPesa1(int slc);
	
	public List<State> getStateWisePesa();
	
	public Integer getLblc(Integer localBodyCode);

	public List<LocalBodyParent> getStateTopHierarchy(int slc);
	
	public List<Localbody> getStateTopHierarchyforGta(int slc, int localBodyTypeCode);
	
	public List<LocalbodyListbyState> getPanchayatListbyParentCategoryFChangeTierforGta(int lbTypeCode,int stateCode,int parentLbCode) throws Exception;
	
	public Integer getlocalbodycodeByLblc(int parentLBCode);
	
	public boolean checkLocalbodyExist(Integer lbCode);
	
	public boolean createwarddetailsdelete(List<LocalbodyWard> ward);
	
	/*added by Kirandeep on 1/09/2014 for ward*/
	public List<localbodywardtemp> getlocalGovtBodyWardListforpaginationtemp(int localBodyCode, int offset, int limit);
	public boolean managewarddeletetemp(Integer tempWardid);
	public  boolean createwardformanage( localbodywardtemp tempward);
	public boolean createwardformanageUpdate( localbodywardtemp tempward,String isUpdate);
	public List<LocalbodyWard> getlocalGovtBodyWardListforpaginationtempdelete(int localBodyCode, int offset, int limit);
	public boolean getexistingwardinTempTable(Integer lblc);
	public Integer restoreWard(Integer wardCd);
	
	/*added by Kirandeep on 1/11/2014 for ward*/
	public List<Localbody> checkForExistingGp(String str);
	
	/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
	public boolean saveHabitation( Habitation habitation);
	
	public List<LocalbodyforStateWise> gethierarchyforGP(char lbTypeCode, int stateCode) throws Exception;

	
// TODO Remove unused code found by UCDetector
// 	public List<GetLocalGovtSetup> getLocalGovSetupbyParentTierSetupCode(Integer stateCode,String category,Integer parentTierSetupCode);
	
	
	public List<SearchLocalGovtBody> getLocalGovtBodySearchDetailByCode(
			int entityCodeForSearch, String entityCode) throws Exception;
	
	public List<LocalBodyHistory> getLocalBodyHistoryReport(
			char localBodyNameEnglish, int localBodyCode) throws BaseAppException;
	public List<LocalbodyListbyState> getTopLocalbodyListforDistrictUser(Integer stateCode,Integer lbTypeCode,Integer districtCode) throws Exception;
	public List<LocalbodyListbyState> getchildlocalbodiesWithoutCountByParentcodeDistrict(Integer lbCode,Integer districtCode) throws Exception;
	/**
	 * For getting Ward List by LocalBodyCode
	 * @author Pooja 29-07-2015
	 * @param localBodyCode
	 */
	public List<LocalbodyWard> getWardListByLbCode(Integer localBodyCode) throws Exception;
	
	/**
	 * For getting CoveredArea List of LocalBodyCodes
	 * @author Pooja Sharma (02-09-2015)
	 * @param localBodyCodes
	 */
	public List<LocalGovtBodyNameList> getCoveredAreaListByLbCodes(String localBodyCodes) throws Exception;
	
	public List<LBTypeHierarchy>  getLBHierarchyList(Integer stateCode ,String PANCHAYAT_TYPE)throws Exception;
	
	public boolean saveTempWards(List<localbodywardtemp> insertListWard,List<localbodywardtemp> updateListWard,List<localbodywardtemp>deleteListWard) throws Exception;
	
	public boolean publishWards(WardForm wardForm) throws Exception;
	
	public List<localbodywardtemp> getLocalbodyWardTempList(Integer lblc)throws Exception;
	
	public List<LocalbodyWard> getLocalbodyWardList(Integer lblc)throws Exception;
	
	public List<LBTypeDetails> getLBTypeDetailsList(Integer stateCode,String PANCHAYAT_TYPE) throws Exception;
	
	public List<LocalbodyListbyState> getUrbanListbyLocalbodyTypeList(String lbTypeCode, int stateCode) throws Exception;
	
	public Object[] getUrbanListbyAdminEntity(Integer adminEntityCode,Integer stateCode,String LbTypeCategory)throws Exception;
	
	public List<ConstituencyLocalbody> getlbListforConstituencyMap(String lbType,Integer lbTypeCode, Integer stateCode, Integer parentlbCode,Integer districtCode, List<String> lbFull,List<Integer> lbPart,final Boolean forDropdown) throws Exception;
	
	public List<ConstituencyVillage> getVillageListforConstituencyMap(List<Integer> lbPart) throws Exception;
	
	public List<ConstituencyMapDetailsbyacCode> getConstituencyMapDetails(Integer acCode) throws Exception;
	
	public List<ConstituencyWard> getWardforConstituencyMap(List<Integer> lblcCodeList,List<Integer> wardCodeList) throws Exception;
	/**
	 * @author Sunita Dagar
	 * @Date 18-11-2016
	 */
	public boolean saveNodalOfficerDetails(NodalOfficerForm nodalOfficerForm)throws Exception;

	public List<NodalOfficer> getNodalOfficerDetails(Integer userId, Integer districtCode);
	
	public boolean UpdateIsactiveStatue(int createdBy, Integer districtCode);
	
	public List<LocalBodyCoveredArea> getCoveredVillagesofLocalbodies(String localBodyCodes)throws Exception;
	
	
	public List<LocalbodyforStateWiseFinal> getLocalBodyListforGisStatus(Integer localBodyType, Integer stateCode, Integer plblc, Integer lbCode, String lbName) throws Exception;
	
	public Date getExistingLBEffectiveDate(Integer localBodyCode)throws HibernateException;
	
	public String InvalidateUrbanLocalbodyProcess(LocalGovtBodyForm lbForm)throws Exception;
	
	public LocalBodyTable getMaxVersionLocabody(Integer localbodyCode)throws Exception;

	public List<LgdPfmsMapping> getLdgPfmsMapping(Integer districtCode) throws Exception;

	public boolean updateVerifiedStatus(Integer lgdPfmsId,Integer userId);
	
	}