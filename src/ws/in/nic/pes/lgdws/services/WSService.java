package ws.in.nic.pes.lgdws.services;



import java.util.List;

import org.hibernate.HibernateException;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.LandRegion;
import in.nic.pes.lgd.bean.LgdOrganisationListByLocation;
import in.nic.pes.lgd.bean.LocalbodyListbyStateold;
import in.nic.pes.lgd.bean.ParentWiseLBList;
import in.nic.pes.lgd.bean.ReportingIssue;
import in.nic.pes.lgd.bean.UserRegistration;
import in.nic.pes.lgd.bean.WsUserRegistrationForm;
import ws.in.nic.pes.lgdws.entity.CoveredVillagesOfBlock;
import ws.in.nic.pes.lgdws.entity.GetHierarchyOfEntity;
import ws.in.nic.pes.lgdws.entity.LevelwiseofRuralLbCount;
import ws.in.nic.pes.lgdws.entity.TotalNoofPriLbnVillageCount;
import ws.in.nic.pes.lgdws.entity.VillageListWithHierarchy;
import ws.in.nic.pes.lgdws.entity.WSDistrict;
import ws.in.nic.pes.lgdws.entity.WSLocalbody;
import ws.in.nic.pes.lgdws.entity.WSOrganization;
import ws.in.nic.pes.lgdws.entity.WSState;
import ws.in.nic.pes.lgdws.entity.WSSubdistrict;
import ws.in.nic.pes.lgdws.entity.WSVillage;
import ws.in.nic.pes.lgdws.entity.WardDetailList;
import ws.in.nic.pes.lgdws.webservice.StateWiseGPVillageMappedEntity;

 public interface WSService {
	 List<WSState> getStateList()throws Exception;
	 List<WSDistrict> getDistrictList(String stateCode)throws Exception;
	 List<WSDistrict> getDistrict(String districtCode)throws Exception;
     List<WSSubdistrict> getSubdistrictList(String districtCode)throws Exception;
     List<WSSubdistrict> getSubdistrict(String subDistrictCode)throws Exception;
     List<WSVillage> getVillageList(String subdistrictCode)throws Exception;
     List<WSVillage> getVillage(String villageCode)throws Exception;
     boolean checkAuthorizedKey(String authKey)throws Exception;
     boolean checkValidEntityCode(Integer entityCode,String entity) throws Exception;
     List<WSLocalbody> getLBListbySubdistrict(String landRegionType,Integer entityCode,Integer lbTypeCode)throws Exception;
     boolean validateLbTypeCode(Integer lbTypeCode) throws Exception;
     List<WSLocalbody> getLocalbodyListbyDistrictnLbTypeCode(Integer districtCode,Integer lbTypeCode) throws Exception;
    /**
     * 
     * @param stateCode
     * @return
     * @throws HibernateException
     * @author Vinay Yadav
     */
    // Map<String, Collection<?>> getImpactDetails(Integer stateCode) throws Exception;
     boolean validateOrgTypeCode(Integer orgTypeCode) throws Exception;
     List<WSOrganization> getOrganizationList(Integer lbTypeCode, boolean isCenter, Integer stateCode) throws Exception;
     List<LevelwiseofRuralLbCount> getLevelwiseRuralLBCount(Integer stateCode) throws Exception;
     TotalNoofPriLbnVillageCount getTotalNoofPriLbnVillageCount(String QueryName)throws Exception;
     Object[] villageWiseLGDDetails(Integer villageCode)throws Exception;
     List<WardDetailList> getWardDetailsbyLocalbodyCode(Integer localbodyCode) throws Exception;
     List<LocalbodyListbyStateold> getExistingPanchayatList(Integer localbodyTypeCode, Integer stateCode) throws Exception;
     List<ParentWiseLBList> getParentWiseLBList(Integer localbodyCode) throws Exception;
     List<LandRegion> getcoveredLandregionByLocalbodyCode(Integer localbodyCode)throws Exception;
     List<Block> getBlockListbyDistrictCode(Integer districtCode,boolean isactive) throws Exception;
     List<WSLocalbody> getDistrictwiseBlockandGramPanchyat(Integer blockCode) throws Exception;
     List<LgdOrganisationListByLocation> getOrganizationListByLocation(Integer entity_lc,Integer entity_type) throws Exception;
     WsUserRegistrationForm insertWsUserRegistrationDetails(WsUserRegistrationForm wsUser) throws Exception;
	 List<UserRegistration> getWsUserRegistrations();
	 UserRegistration approveUserRegistration(int userId,String type);
	 String captachAuthentication(String captchaAnswer);
	
	 void saveUserRegistrationDetails(UserRegistration registration);
	
	 UserRegistration getRegistrationDetails(String emilOrnumber);
	
	 UserRegistration getRegistration(int userId);
	
	 void saveRequestWebService(UserRegistration registration);
	
	 void saveReportingIssue(ReportingIssue reportingIssue);
	
	 List<ReportingIssue> reportingIssueList(int userid);
	
	 List<ReportingIssue> stateReportedIssue(int nodelId);
	
	 void replyReportedIssue(ReportingIssue reportingIssue);
	
	 List<ReportingIssue> stateReportedIssue(int stateCode, Character userType);
	
	 void saveEmailSmsNotification(EmailNotification emailNotification);
	
	 EmailNotification getSubscriberUserDetails(int userId);
	
	 boolean isOrganizationExist(Integer olc) throws Exception;
	
	 boolean isOrgLocatedLevelCodeExist(Integer olc,Integer orgLocatedLevelCode) throws Exception;
	
	 Integer isDesinationListExist(List<Integer> desiginationCodeList,Integer olc) throws Exception;
	
	 List<WSLocalbody> getUrbanLocalbodyBasedOnLandRegiontypeAndCode(String landRegionType,Integer landRegionTypeCode) throws Exception;
	
	 List<VillageListWithHierarchy> fetchVillageListWithHierarchy(Integer subDistrictCode) throws Exception ;
	
	 StateWiseGPVillageMappedEntity getStateBlocWiseMapped(Integer stateCode, String finYear,Character queryFlag);
	
	 List<CoveredVillagesOfBlock> fetchCoveredVillagesOfBlock(Integer blockCode) throws Exception ;
	
	 List<GetHierarchyOfEntity> getHierarchyOfEntity(String entityType,Integer entityCode) throws Exception ;
	 
	 List<WSVillage> getVillageListALL(Integer page)throws Exception;

}
