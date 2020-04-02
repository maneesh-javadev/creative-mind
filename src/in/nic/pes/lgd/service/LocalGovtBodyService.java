package in.nic.pes.lgd.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import com.cmc.lgd.localbody.entities.LocalBodyTable;

import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.ConfigurationMapLocalbody;
import in.nic.pes.lgd.bean.ConstituencyLocalbody;
import in.nic.pes.lgd.bean.ConstituencyMapDetailsbyacCode;
import in.nic.pes.lgd.bean.ConstituencyVillage;
import in.nic.pes.lgd.bean.ConstituencyWard;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GetLandRegionNameforWard;
import in.nic.pes.lgd.bean.GetLandRegionWise;
import in.nic.pes.lgd.bean.GetLocalBodyTypeCode;
import in.nic.pes.lgd.bean.GetLocalGovtBodyTypeList;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.GetSubTypeList;
import in.nic.pes.lgd.bean.Habitation;
import in.nic.pes.lgd.bean.LBCoverageWard;
import in.nic.pes.lgd.bean.LgdPfmsMapping;
import in.nic.pes.lgd.bean.LocalBodyCoveredArea;
import in.nic.pes.lgd.bean.LocalBodyCoveredAreaLB;
import in.nic.pes.lgd.bean.LocalBodyDetails;
import in.nic.pes.lgd.bean.LocalBodyParent;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTypeHistory;
import in.nic.pes.lgd.bean.LocalBodyTypeWiseDepartment;
import in.nic.pes.lgd.bean.LocalBodyViewChild;
import in.nic.pes.lgd.bean.LocalGovtBody;
import in.nic.pes.lgd.bean.LocalGovtBodyForSelectedBody;
import in.nic.pes.lgd.bean.LocalGovtBodyNameList;
import in.nic.pes.lgd.bean.LocalGovtBodyWard;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyUnMappedBody;
import in.nic.pes.lgd.bean.LocalbodyUnMappedBodyLevelWise;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.LocalbodyforStateWiseFinal;
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
import in.nic.pes.lgd.bean.UnmappedLBList;
import in.nic.pes.lgd.bean.ViewLandRegionDisturbedlist;
import in.nic.pes.lgd.bean.ViewLocalBodyLandRegion;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.localbodywardtemp;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtBodyDataForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;
import in.nic.pes.lgd.forms.NodalOfficerForm;
import pes.attachment.util.AttachedFilesForm;

public interface LocalGovtBodyService {

	
// TODO Remove unused code found by UCDetector
// 	public List<District> getUnmappedLocalbodyDistList(String query) throws Exception;

	public List<District> getDistrictList(int stateCode) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyType> getLGTypeForGovtBody() throws Exception;

	public List<Localbody> getULBList(int districtCode) throws Exception; // NO_UCD (unused code)

	public List<ParentWiseLocalBodies> getLocalGovtBodySubDistList(int localBodyCode) throws Exception; // NO_UCD (unused code)

	//public List<Localbody> getLocalGovtBodyDistList(int stateCode) throws Exception;
	
	public List<Localbody> getLocalGovtBodyMappedVillageList(int landRegionCode,int lbtypecode) throws Exception; // NO_UCD (unused code)

	public List<Localbody> getLocalGovtBodyList(int localBodyCode, int stateCode) throws Exception; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<LocalGovtBody> getLocalBodySubList(int localBodyCode, int stateCode) throws Exception;

	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcode(int localbodyCode) throws Exception;
	
	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcodeChangeCoverage(int localbodyCode,int lbCode) throws Exception; // NO_UCD (unused code)

	public List<ParentWiseLocalBodies> getchildlocalbodiesWithoutCountByParentcode(int localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<Localbody> getLocalBodyNamebyLBID(String formvalue) throws Exception;

	public List<Localbody> getLocalBodyNamebyLBIDforHeiRChange(String formvalue) throws Exception;

	public List<LocalBodyType> getLocalBodyTypeNamebyLBTID(String formvalue) throws Exception;

	public List<LocalBodyType> getLocalBodyTypeNamebyChangeLBN(int formvalue) throws Exception;

	public List<Village> getVillageNamebyVillID(String formvalue) throws Exception;

	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcodeInter(String localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<Localbody> getLocalGovtBodyVillageList(int localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<Localbody> getLocalGovtBodyParentList(int localBodyCode) throws Exception; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<Localbody> getLocalBodyViewList(String query) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<Subdistrict> getUnmappedSubDistLocalbodyViewList(String query) throws Exception;
	
	public List<Subdistrict> getDeleteSubDistMapped(String distlist,String subdistlist)	throws Exception; // NO_UCD (unused code)
	
	public List<Village> getDeleteVillageMappedDP(String subdistlist,String villageList) throws Exception; // NO_UCD (unused code)
	
// TODO Remove unused code found by UCDetector
// 	public List<Village> getDeleteVillageMapped(String subdistlist)	throws Exception;
	
	public List<Village> getDeleteVillageMappedforSubDist(String subdistlist,String villagelist) throws Exception; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<Village> getUnmappedVillageLocalbodyViewList(String query) throws Exception;

	public List<LocalbodyListbyState> getPanchayatListbyStateandCategory(int stateCode, char category, char level) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryNewWard(int stateCode, char category, char lblevel) throws Exception;

	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryNewWardF(int lbtypeCd, int stateCode) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryParentInter(int stateCode, char category, char lblevel, int parentyresetupCd) // NO_UCD (unused code)
			throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryNewWardParentInter(int stateCode, char category, char lblevel) throws Exception;

	public List<Subdistrict> getSubDistrictListforLocalBody(String districtCode) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageList(String localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredVillageListforLB(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredVillageListforLBforMCov(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforModify(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforModifyCoverage(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforWard(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforname(String localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforrlbtoulb(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<Village> getVillageListforLocalBody(String subdistrictCode) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictList(String localBodyCode) throws Exception; // NO_UCD (unused code)
	
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListforHeadQuarter(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistList(String localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistListFinal(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistListFinalforCov(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LBCoverageWard> getLocalGovtBodyforCoveredSubDistListforWard(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<ParentWiseLocalBodiesWithoutChildCount> getIntermediatePanchayatbyParentCodeWithoutChild(String localBodyCode) throws Exception; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<ParentWiseLocalBodies> getIntermediatePanchayatbyParentCodes(String localBodyCode) throws Exception;

	public List<LocalbodyforStateWise> getLocalBodyListStateWise(char localBodyType, int stateCode) throws Exception;
	
	public List<LocalbodyforStateWise> getLocalBodyListStateWiseChangeType(char localBodyType, int stateCode,int lbtypecode) throws Exception;

	public List<LocalbodyforStateWise> getLocalBodyListStateWiseTierSet(char localBodyType, int stateCode, int parentyresetupCd) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyforStateWise> getLocalBodyListStateWiseTierSetF(char localBodyType, int stateCode, int parentyresetupCd) throws Exception; // NO_UCD (unused code)

	public List<State> getStateName(int stateCode) throws Exception;

	public List<Localbody> getLocalbodyname(String parentLBodyCode) throws Exception;

	public List<District> getDistrictNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception;
	public List<District> getDistrictNamebyDisIDCovChange(LocalGovtBodyForm localGovtBodyForm) throws Exception; 

	public List<District> getUnmappedDistrictNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getUnmappedSubDistrictNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getUnmappedSubDistrictbyInterNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getUnmappedSubDistrictNamebyDisIDforIP(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconunmappedVillageNamebyVillageID(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconunmappedVillageNamebyVillageIDforIP(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconunmappedVillageNamebyVillageIDforVP(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconunmappedVillageNamebyInterVillageID(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconunmappedVillageNamebyVPancVillageID(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getSubDistrictNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception;
	
	public List<Subdistrict> getSubDistrictNamebyDisIDCovChange(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getSubDistrictNamebyDisIDInter(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconVillageNamebyVillID(LocalGovtBodyForm localGovtBodyForm) throws Exception;
	
	public List<Village> getconVillageNamebyVillIDforChCov(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconVillageNamebyVillIDVill(LocalGovtBodyForm localGovtBodyForm) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<Village> getconVillageNamebyVillIDVP(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconVillageNamebyVillIDVPFin(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getSubDistrictNamebyInterSubDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconVillageNamebyInterVillID(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getconVillageNamebyVillageID(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getSubDistrictNamebyDisIDULB(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getSubDistrictNamebyDisIDULBFinal(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getSubDistrictNamebyCovChangeULB(LocalGovtBodyForm localGovtBodyForm) throws Exception;
	
	/**
	 * The {@code getDistrictNamebyCovChangeULB} return all District Names based on {@param availlgdLBInterCAreaSourceListUmappedUrban}.  
	 * @param availlgdLBInterCAreaSourceListUmappedUrban
	 * @return String
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	public String getDistrictNamebyCovChangeULB(String availlgdLBInterCAreaSourceListUmappedUrban) throws Exception;

	public List<District> getDistrictNamebyCovChangePRITrad(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getSubDistrictNamebyCovChangePRITrad(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getSubDistrictNamebyCovChangePRITradInter(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Subdistrict> getunMappedSubDistrictNamebyDisIDULB(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getVillageNamebyCovChangePRITrad(LocalGovtBodyForm localGovtBodyForm) throws Exception;

	public List<Village> getVillageNamebyCovChangePRITradInter(LocalGovtBodyForm localGovtBodyForm) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<Village> getVillageNamebyCovChangePRITradVill(LocalGovtBodyForm localGovtBodyForm) throws Exception;
	
	public List<Village> getVillageNamebyCovChangePRITradVillFinal(LocalGovtBodyForm localGovtBodyForm) throws Exception; 

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListFinalTo(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)
	
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListFinalToDistrict(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistrictListFinalTo(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)
	
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistrictListInterTo(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredVillageListFinalTo(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalbodyforStateWise> getLocalBodystatelist(char localBodyType, int stateCode) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<LocalBodyUrbanType> getLocalBodyUrbanName(int localBodyCode, String urbanTypeBodyCode, int stateCode, int govtLocalBodyType) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<LocalBodyTypeForStateWiseView> getLocalBodyListStateWiseView(int stateCode) throws Exception;

	public List<LocalGovtBodyForSelectedBody> getLocalBodyListForSelectedBody(int localBodyTypeCode, int stateCode) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyViewChild> getLocalBodyListForSubDistBody(int localBodyTypeCode, int stateCode, int localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<LocalGovtTypeDataForm> getLocalBodyTypeDetails(int localBodyTypeCode) throws Exception;

	public List<LocalBodyType> getGovtTypeViewList(String query) throws Exception;

	public List<LocalBodyTypeHistory> getLocalBodyTypeHistory(String query) throws Exception;

	public List<LocalbodyUnMappedBody> getLocalGovtBodyforUnmappedLocalBodyList(char localBodyType, int localBodyCode) throws Exception; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<ChildLocalGovtBody> getLocalGovtBodyDetail(int localBodyTypeCode) throws Exception;

	public List<LocalbodyListbyState> getUrbanLocalBodyList(int lbTypeCode, int stateCode) throws Exception; // NO_UCD (unused code)
	
	public List<LocalbodyListbyState> getUrbanLocalBodyListChangeCoverage(int stateCode,int localbodyTypeCode,int lbCode) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyListbyState> getPanchayatListbyStateandlbTypeCode(int stateCode, int lbtypeCode) throws Exception;

	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeD(int stateCode, char lbtypeCode) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeI(int stateCode, char lbtypeCode) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeV(int stateCode, char lbtypeCode) throws Exception; // NO_UCD (unused code)

	public List<Localbody> getGovtLocalBodyTypeDetails(int localBodyCode) throws Exception;

	public List<LocalBodyDetails> getGovtLocalBodyDetails(int localBodyCode) throws Exception;

	public List<SearchLocalGovtBody> getLocalGovtBodySearchDetail(String entityName, String entityCode) throws Exception;

	public String saveLocalGovtBody(LocalGovtBodyForm localGovtBodyForm, int stateCode, GovernmentOrderForm govtOrderForm,
			List<AttachedFilesForm> attachedList, List<AttachedFilesForm> metaInfoOfToBeAttachedMapList, HttpServletRequest request, Integer userID)
			throws Exception;

	public String saveLocalGovtBodyULB(LocalGovtBodyForm localGovtBodyForm, int stateCode, GovernmentOrderForm govtOrderForm,
			List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, HttpServletRequest request, Integer userId) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public int saveUrbanLocalGovtBody(LocalGovtBodyForm localGovtBodyForm, int stateCode, GovernmentOrderForm govtOrderForm,
// 			List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, HttpServletRequest request) throws Exception;

	public List<LocalBodyCoveredArea> getCoveredAreaforULB(String localBodyCode) throws Exception; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<LocalBodyCoveredArea> getCoveredAreaforULBFinal(String localBodyCode) throws NumberFormatException, Exception;

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBFinalforLB(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBFinalforLBforM(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBFinalforLBforMF(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<GetLocalBodyTypeCode> getUrbanLocalBodyTypes1() throws Exception;

// TODO Remove unused code found by UCDetector
// 	public String getlocalbodyNamebyCode(int localbodyCode) throws Exception;

	public List<LocalBodyCoveredArea> getCoveredSubDistrictListforLB(int localBodyCode) throws Exception;

	public List<LocalBodyCoveredArea> getCoveredDistrictListforLB(int localBodyCode) throws Exception;

	public List<LBCoverageWard> getLGBforCoveredDistListExWard(int localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getCoveredVillageListforLB(int localBodyCode) throws Exception;

	/*
	 * public Integer modifyLocalBodyForName(LocalGovtBodyForm
	 * localGovtBodyForm, List<AttachedFilesForm> attachedList,
	 * List<AttachedFilesForm> metaInfoOfToBeAttachedMapList, HttpServletRequest
	 * request,HttpSession httpsession)throws Exception;
	 */
	public String modifyLocalBodyForName(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession) throws Exception;

	boolean modifyLocalBody(LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> metaInfoOfToBeAttachedMapList, // NO_UCD (unused code)
			HttpServletRequest request) throws Exception;

	public List<LocalGovtBodyWard> getlocalGovtBodyWardList(int localBodyCode) throws Exception;

	public boolean invalidateWard(int wardCode, int userid) throws Exception; // NO_UCD (unused code)

	public boolean deleteWard(int wardCode, int userid) throws Exception;

	public List<Localbody> getLocalBodyList(int localBodyCode, int stateCode) throws Exception; // NO_UCD (unused code)

	public List<UnmappedLBList> getUnmappedLBDistList(char type, int stateCode) throws Exception;

	public List<PartillyMappedLRList> getPartillymappedLBDistList(char c, int stateCode, char category) throws Exception;

	public List<LocalBodyCoveredArea> getCoveredSubDistrictLocalBody(int localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<GetLocalBodyTypeCode> getTypeListbylevel(char typeCode, char category) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyWard> getWardDetail(int wardCode) throws Exception;

	public List<GetLandRegionNameforWard> getDistrictNameWard(int wardCode) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<CoveredWardLandregion> getCoverageLangRegion(int wardCode) throws Exception;

	public List<GetLandRegionNameforWard> getSubDistrictNameWard(int wardCode) throws Exception;

	public List<GetLandRegionNameforWard> getVillageNameWard(int wardCode) throws Exception;

	public boolean createNewWard(LocalGovtBodyForm localGovtBodyForm, int stateCode, HttpServletRequest request, int userid) throws Exception;

	public List<LocalbodyforStateWise> getLocalBodyListStat(char localBodyType, int stateCode) throws Exception; // NO_UCD (unused code)

	public List<LBCoverageWard> getLGBforCoveredVillListExWard(int localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<LBCoverageWard> getLGBforCoveredVillListforWard(String localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<LBCoverageWard> getLGBforCoveredVillageListExWard(int localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<LBCoverageWard> getLGBforCoveredSubDistListExWard(int localBodyCode) throws Exception; // NO_UCD (unused code)

	List<LBCoverageWard> getLGBforCoveredSubDistUrbanListExWard(int localBodyCode) throws Exception; // NO_UCD (unused code)

	// modify Ward
	//public boolean modifyWard(LocalGovtBodyForm localGovtBodyForm, int wardCode, Session session, HttpServletRequest request) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public int getWardCurrentVersion(int wardCode) throws Exception;

	public List<LocalBodyTypeWiseDepartment> getLocalBodyTypeWiseDeptList(int stateCode, int lBTypeCode, char levelCode) throws Exception;

	public List<LocalBodyTypeWiseDepartment> getLocalBodyWiseDeptList(int stateCode, int lBCode, char levelCode) throws Exception;

	public List<GetSubTypeList> getLBSubTypeList(int stateCode, int lBTypeCode) throws Exception; // NO_UCD (unused code)

	public Collection<LocalBodyCoveredArea> getLBCoveredAreaList(int localBodyCode) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyListbyState> getExistingLBListbyStateandCategory(int stateCode, char category) throws Exception;

	public List<LocalbodyListbyState> getExistingLBListbyStateandCategoryInter(int stateCode, char category) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<LocalbodyListbyState> getExistingLBListbyStateandCategoryVillage(int stateCode, char category) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<LocalbodyUnMappedBody> getUnmappedDistList(char localBodyType, int stateCode) throws Exception;

	public List<LocalbodyUnMappedBody> getUnmappedSubDistOrVillList(char localBodyType, String distCode) throws Exception; // NO_UCD (unused code)
	
	public List<LocalbodyUnMappedBodyLevelWise> getUnmappedSubDistOrVillListFinal(char type,String distCode,char level) throws Exception;  // NO_UCD (unused code)
	
	public List<LocalbodyUnMappedBodyLevelWise> getUnmappedSubDistOrVillIPListFinal(char type,List<String> distCode,char level) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyUnMappedBody> getUnmappedSubDistList(char type, String distCode) throws Exception; // NO_UCD (unused code)
	
	public List<LocalbodyUnMappedBodyLevelWise> getUnmappedSubDistListLevelWise(char type,String distCode,char level) throws Exception; // NO_UCD (unused code)
	
	public List<LocalbodyUnMappedBodyLevelWise> getUnmappedSubDistListLevelWiseFinal(char type,List<String> distCode,char level) throws Exception; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<PartillyMappedLRList> getPartUnmappedDistListByStateCode(char localBodyType, int stateCode, char category) throws Exception;

	public List<UnLRSWiseList> getUnMapLRStaWiseList(char type, int stateCode) throws Exception;

	public List<UnLRDistrictWiseList> getUnMapLRDistWiseList(char type, int distictCode) throws Exception;

	public List<PartillyMappedLRList> getPartlyMapLRStaWiseList(char type, int stateCode) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredArea> getLocalGovtBodySubDistrictListFinal(String districtcode, String coveragecode) throws NumberFormatException, Exception; // NO_UCD (unused code)
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodySubDistrictListFinalChangeCov(String districtcode,String coveragecode) throws NumberFormatException,Exception;  // NO_UCD (unused code)
	
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredDistrictListFinalforLBforMCov(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)
	
	public List<CheckAuthorization> getLocalGovtBodySubDistrictListChangeCoverage(String districtcode) throws Exception;  // NO_UCD (unused code)

	public List<LocalBodyCoveredAreaLB> getVillageListforDCAFinal(String subdistrictcode, String coveragecode) throws NumberFormatException, Exception; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<LocalBodyCoveredArea> getVillageListforDCACovChngFinal(String subdistrictcode, String coveragecode) throws NumberFormatException, Exception;

	List<PartillyMappedLRList> getPartlyMappedSubDistOrVillList(char type, List<String> districtCode, char category) throws Exception; // NO_UCD (unused code)
	
	public List<PartillyMappedLRListLevelWise> getPartlyMappedSubDistOrVillListFinal(char type, List<String> districtCode,char category,char level) throws Exception;  // NO_UCD (unused code)

	List<LocalbodyWard> getWardByWardCode(int wardCode) throws Exception;

// TODO Remove unused code found by UCDetector
// 	List<Localbody> getULBListByDistrict(int districtCode) throws Exception;

	List<CheckAuthorization> getLocalGovtBodySubDistrictList(String districtcode) throws Exception; // NO_UCD (unused code)

	List<CheckAuthorization> getVillageListforDCA(String villagecode) throws Exception; // NO_UCD (unused code)

	public List<ConfigurationMapLocalbody> getMapD(int stateCode, char level) throws Exception; // NO_UCD (unused code)
	
	public List<ConfigurationMapLocalbody> getMapDLBody(int stateCode,char level,char lbType) throws Exception; // NO_UCD (unused code)

	public List<ConfigurationMapLocalbody> getUrbanMapD(int stateCode, int lbtypecd) throws Exception; // NO_UCD (unused code)

	public String modifyLocalBodyForUrbanType(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, Integer stateCode,Integer userId);

	public boolean modifyLocalBodyForCorrection(LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, HttpServletRequest request,String coodinates,String mapfilename,boolean delFlag) throws Exception;

	public String modifyLocalBodyForPriType(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, Integer userId) throws Exception;
		
	public boolean modifyLocalBodyForCorrectionGenerate(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession)
			throws Exception;

	public boolean saveDataInMapLocalBody(LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception;

	public boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList,
			HttpSession session) throws Exception;

	public boolean saveDataInAttachGenerateLocalBody(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm localGovtBodyForm, HttpSession session)
			throws Exception;

	public boolean saveDataInAttachGenerateLocalBodyUrbanTypeModify(LocalGovtBodyForm localGovtBodyForm, HttpSession session, int getordercode)
			throws Exception;

	public List<LocalGovtBody> viewlocalbodyDetails(Integer localBodyCode);

// TODO Remove unused code found by UCDetector
// 	public GovernmentOrder viewgovernmetorderDetails(Integer orderCode);

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListFinal(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredDistrictListFinalforLB(String localBodyCode) throws NumberFormatException, Exception; // NO_UCD (unused code)

	/**
	 * @param localGovtBodyForm
	 * @param request
	 * @param httpsession
	 * @param isULBDisttLevel (New Added Parameter)
	 * @return
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	public String modifyLocalBodyForcoverageArea(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession, boolean isULBDisttLevel) throws Exception;
	
	public String modifyDisturbedLocalBodyForcoverageAreaNameParent(LocalGovtBodyForm localGovtBodyForm,LocalGovtBodyForm localGovtBodyFormChngeName,GovernmentOrderForm govtOrderForm,HttpServletRequest request,HttpSession httpsession,Integer userId) throws Exception; 


	public boolean saveDataInAttachCoverageLBody(List<AttachedFilesForm> attachedList, HttpSession session, int getordercode) throws Exception;

	public List<GetLandRegionWise> getLandRegionWise(Integer localbodytypecode, char code, Integer districtcode, String lbtype) throws Exception;

	public List<UlbBean> getLandRegionWiseUrban(Integer localbodytypecode, Integer districtcode) throws Exception; // NO_UCD (unused code)

	/*
	 * public List<GetLandRegionWise> getLandRegionWiseInter(Integer
	 * localbodytypecode,Integer districtcode) throws Exception; public
	 * List<GetLandRegionWise> getLandRegionWiseVill(Integer
	 * localbodytypecode,Integer districtcode) throws Exception;
	 */

	public List<ViewLocalBodyLandRegion> viewLandRegionDistrictName(int localBodyCode);

	public List<ViewLocalBodyLandRegion> viewLandRegionsubDistrictName(int localBodyCode);

	public List<ViewLocalBodyLandRegion> viewLandRegionvillageName(int localBodyCode);
	public List<ViewLocalBodyLandRegion> viewLandRegionvillageNameF(int localBodyCode) throws Exception;

	public List<LocalbodyWard> getWardListFromContributingMunicipality(String localBodyCode) throws Exception; // NO_UCD (unused code)

	// Tanuj
	public List<GetLocalGovtBodyTypeList> getLBGTypeList(int stateCode) throws Exception; // NO_UCD (unused code)

	public String getCategoryFromLocalBodyTypeCode(int localBodyType) throws Exception;

	public List<LocalbodyforStateWiseFinal> getLocalBodyListStateCategoryWise(Integer localBodyType, Integer stateCode,Integer plblc,Integer lbCode,String lbName) throws Exception;

	public List<ParentWiseLBList> getParentWiseLBList(int localbodyCode) throws Exception;

	public int getLBTypeCodeFromLBCode(int localBodyCode) throws Exception;

	public List<Localbody> getLocalGovSetupWiseLocalbodyList(int localBodyCode) throws Exception;

	public List<Localbody> getLocalGovSetupWiseLocalbodyListByName(String localBodyName) throws Exception;

	public List<LocalGovtBodyDataForm> getLocalBodyDetailsModify(int localBodyCode) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char c, int localBodyCode) throws Exception;

	public List<Attachment> getAttachmentsbyOrderCode(int orderValue) throws Exception;

	public List<LocalbodyWard> getWardDetails(int lblc/* , int offset ,int limit */);

// TODO Remove unused code found by UCDetector
// 	public int countWardDetails(int lblc);

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
	public boolean correctLocalBodyForcoverageArea(LocalGovtBodyForm localGovtBodyForm, Integer maxlbcode, HttpServletRequest request, HttpSession httpsession, String catagery, boolean isULBDisttLevel) throws Exception;

	public List<ViewLandRegionDisturbedlist> viewGpdisturbedlist(int localBodyCode);

	public String invalidatePRILB(LocalGovtBodyForm LBForm, HttpSession httpSession);

	public List<GetLandRegionWise> getLandRegionWise(Character localbodytypecode, int districtcode, String lbtype) throws Exception;

	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryforselected(int stateCode, char category, int localBodyCode) throws Exception;

	public String invalidateTRILB(LocalGovtBodyForm LBForm, HttpSession httpSession);

	public String invalidateURBLB(LocalGovtBodyForm LBForm, HttpSession httpSession);

	public List<LocalbodyListbyState> getLandRegionWisedistrict(String type, Integer districtcode, Integer lbtype) throws Exception;

	public int countLocalBodyDetails(Integer lbtype, int stateCode);

	public List<Localbody> getPanchayatListbylocalbody(int stateCode, int lbtypeCode, int offset, int limit);

	public List<UlbBean> getPanchayatListbylocalbodyUrban(int districtCode, int lbtypeCode, int offset, int limit) throws Exception;

	public List<Localbody> getLocalbodylist(int stateCode); // NO_UCD (unused code)

	public List<Localbody> getPanchayatListbylocalbodyVillagepanchayat(int stateCode, int lbtypeCode, String intermediatePanchayatCodes, int offset, int limit);

	public int countLocalBodyDetailsforVillagePanchayat(Integer lbtype, int stateCode, String intermediatePanchayatCodes);

	public List<LocalGovtBodyWard> getlocalGovtBodyWardListforpagination(int parseInt, int offset, int limit);

	public int countwardDetails(int parseInt);

	public boolean modifyWardData(LocalGovtBodyForm modifyWardCmd, HttpServletRequest request) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public Integer getmaxlandregioncode();

	public Integer getlbcoverdregioncode(int lbcode) throws Exception;
	
	public Integer getParentLblccode(int lbcode) throws Exception;

	public List<LocalbodyListbyState> getPanchayatListbylblcCode(int stateCode, int lbTypeCode) throws Exception;

	public String getlocalbodyname(int lblc) throws Exception; // NO_UCD (unused code)

	public String isVillageExist(int[] localbodyCode, char lbType); // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<LocalbodyListbyState> getPanchayatListbyParentCategory(int stateCode, char category, char level) throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public List<LocalbodyListbyState> getPanchayatListbyParentCategoryF(int stateCode,char category,char level,int parentLbCode) throws Exception; 
	public List<LocalbodyListbyState> getPanchayatListbyParentCategoryFChangeTier(int stateCode,char category,char level,int parentLbCode) throws Exception;

	public String getLocalBodyTypeMessagebyLocalbodyCode(Integer localbodyCode);

	public List<GetLocalGovtSetup> getLocalbodyDetailbyCode(int stateCode, int lblc) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyListbyState> getExistingLBListTwoTier(int stateCode) throws Exception;

	public List<PushLBtoPES> saveLocalBodyinPES(int localbodycode) throws Exception;

	public List<Pushcoveragetopes> saveCoverageinPES(int localbodycode) throws Exception;

	public List<LocalbodyListbyState> getLandRegionByDistricCode(Integer localBodyTypeCode, Integer districtCode, String lbType) throws Exception;

	public String saveLocalBodyDraft(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, UserSelection userSelection);

	public List<LocalbodyListbyState> getmappedlbforPConsituency(int stateCode, int lbtypeCode, int pcCode,char type) throws Exception; // NO_UCD (unused code)

	public List<LocalbodyListbyState> getunmappedlbforPConsituency(int stateCode, int lbtypeCode, int pcCode,char type) throws Exception; // NO_UCD (unused code)

	public boolean ULBExistforConvert(int slc, int type, String name); // NO_UCD (unused code)

	public int getlblc(int localbodyCd) throws Exception;
	
	public String getdisturblbReason(int lbCode,char type)throws Exception;
	
	public List<LocalbodyListbyState> getMappedVillageWardofConsituency(int PcCode,char consituencyType, char entityType,int slc)throws Exception; // NO_UCD (unused code)
	
	public String mergeULB(LocalGovtBodyForm lbForm,HttpSession httpSession);
	
	public boolean mapZpConsituencyDetail(String wardNo, String lbList,String vilList,String deltedLb, String deletedVil ) throws Exception;
	
	public List<ParentWiseLocalBodies>  mappedZpWardConsituencyDetail(String wardNo ,char type,boolean wholeData ) throws Exception; // NO_UCD (unused code)
	
	/*Added by Kirandeep on 01/09/2014 */
	public Integer WardExist(int lblc,String wardName,int type); // NO_UCD (unused code)
	
    public List<Localbody> getChildLbByParentLb(int parentlblc,int slc) throws Exception;
	
	public boolean updateLbPesaStatus(String lbcode,int slc,String deletedlbcode) throws Exception;
	
	 /**
	  * Change For LGD CODE by Maneesh Kumar 19Sep2014
	  */
	public List<StatewisePesaPanchyat> getactiveLbPesa(Integer slc);
	
	
// TODO Remove unused code found by UCDetector
// 	public List<Localbody>getactiveLbPesa1(int slc);
	
	public List<State>getStateWisePesa();
	
	public Integer getLblc(Integer localBodyCode);
	
	public List<LocalBodyParent> getStateTopHierarchy(int slc); // NO_UCD (unused code)
	
	public List<Localbody> getStateTopHierarchyforGta(int slc, int localBodyTypeCode); // NO_UCD (unused code)
	
	public List<LocalbodyListbyState> getPanchayatListbyParentCategoryFChangeTierforGta(int stateCode,char category,char level,int parentLbCode,int parentLBCode1) throws Exception;
	
	public Integer getlocalbodycodeByLblc(int parentLBCode);
	
	public boolean checkLocalbodyExist(Integer lbCode);
	
	/*Added by Kirandeep on 01/09/2014 */
	public boolean createwarddetailsdelete(List<LocalbodyWard> ward);
    
	public List<localbodywardtemp> getlocalGovtBodyWardListforpaginationtemp(int parseInt, int offset, int limit);
	public boolean managewarddeletetemp(Integer tempWardid);
	public boolean createwardformanage( localbodywardtemp tempward);
	public boolean createwardformanageUpdate(localbodywardtemp tempward,String isUpdate);

	public List<LocalbodyWard> getlocalGovtBodyWardListforpaginationtempdelete(
			int intValue, int offset, int i);
	
	public boolean getexistingwardinTempTable(Integer lblc); // NO_UCD (unused code)
	
	public Integer restoreWard(Integer wardCd);
	
	
	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListValid( // NO_UCD (unused code)
			String localBodyCode ,String mappedList) throws NumberFormatException, Exception ;
	
	/*added by Kirandeep on 1/11/2014 for ward*/
	public List<Localbody> checkForExistingGp(String str); // NO_UCD (unused code)
    /**
     *  
     * @param subdistrictCode
     * @return
     * @throws Exception
     */
	public List<Village> getVillageListforLocalBodyBySubdistCode(String subdistrictCode) throws Exception; // NO_UCD (unused code)
	/**
	 * Add for LocalBody Impact Draft Mode
	 * @author Ripunj on 20-12-2014
	 * @param districtcode
	 * @return
	 * @throws Exception
	 */
	public List<Subdistrict> getLocalGovtBodySubDistrictListChangeCover(String districtcode) throws Exception; // NO_UCD (unused code)
	/**
	 * Add for LocalBody Impact Draft Mode
	 * @author Ripunj on 12-01-2015 
	 * @param districtcode
	 * @return
	 * @throws Exception
	 */
	public List<Subdistrict> getLocalGovtBodySubDistrictListForLocalBody( // NO_UCD (unused code)
			String districtcode) throws Exception;
	
	/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
	public boolean saveHabitation( Habitation habitationDet);
	
	public List<LocalbodyforStateWise> gethierarchyforGP(char lbTypeCode, int stateCode) throws Exception; // NO_UCD (unused code)
	
	
	
// TODO Remove unused code found by UCDetector
// 	public List<LocalBodyHistory> getLocalBodyHistoryReport(
// 			char localBodyNameEnglish, int localBodyCode) throws BaseAppException;
		
	public List<SearchLocalGovtBody> getLocalGovtBodySearchDetailByCode(
			int entityCodeForSearch, String entityCode) throws Exception;
	
	public List<LocalbodyListbyState> getTopLocalbodyListforDistrictUser(Integer stateCode,Integer lbTypeCode,Integer districtCode) throws Exception; // NO_UCD (unused code)
	public List<LocalbodyListbyState> getchildlocalbodiesWithoutCountByParentcodeDistrict(Integer lbCode,Integer districtCode) throws Exception; // NO_UCD (unused code)
	/**
	 * For getting Ward List by LocalBodyCode
	 * @author Pooja 29-07-2015
	 * @param localBodyCode
	 */
	public List<LocalbodyWard> getWardListByLbCode(Integer localBodyCode) throws Exception; // NO_UCD (unused code)
	
	/**
	 * For getting CoveredArea List of LocalBodyCodes
	 * @author Pooja Sharma (02-09-2015)
	 * @param localBodyCodes
	 */
	public List<LocalGovtBodyNameList> getCoveredAreaListByLbCodes(String localBodyCodes) throws Exception; // NO_UCD (unused code)
	
// TODO Remove unused code found by UCDetector
// 	public List<LBTypeHierarchy>  getLBHierarchyList(Integer stateCode ,String PANCHAYAT_TYPE)throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public boolean saveTempWards(List<localbodywardtemp> insertListWard,List<localbodywardtemp> updateListWard,List<localbodywardtemp>deleteListWard) throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public boolean publishWards(WardForm wardForm) throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public List<localbodywardtemp> getLocalbodyWardTempList(Integer lblc)throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public List<LocalbodyWard> getLocalbodyWardList(Integer lblc)throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public List<LBTypeDetails> getLBTypeDetailsList(Integer stateCode,String PANCHAYAT_TYPE) throws Exception;
	
	public List<LocalbodyListbyState> getUrbanListbyLocalbodyTypeList(String lbTypeCode, int stateCode) throws Exception;
	
	public Object[] getUrbanListbyAdminEntity(Integer adminEntityCode,Integer stateCode,String LbTypeCategory)throws Exception;
	
	public List<ConstituencyLocalbody> getlbListforConstituencyMap(String lbType,Integer lbTypeCode, Integer stateCode, Integer parentlbCode,Integer districtCode, String lbFullCodes,String lbPartCodes,final Boolean forDropdown) throws Exception ;
			
	public List<ConstituencyVillage> getVillageListforConstituencyMap(String lbPartCodes) throws Exception;
	
	public List<ConstituencyMapDetailsbyacCode> getConstituencyMapDetails(Integer acCode) throws Exception;
	
	public List<ConstituencyWard> getWardListforConstituencyMap(String lbPartCodes,String wardCodes) throws Exception ;
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
	
	public List<LgdPfmsMapping> fetchLgdPfmsMapping(Integer districtCode) throws Exception;
	
	public boolean updateLgdPfmsMappingStatus(Integer lgdPfmsId,Integer userId) throws Exception;
}