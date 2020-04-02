package ws.in.nic.pes.lgdws.services;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.LandRegion;
import in.nic.pes.lgd.bean.LgdOrganisationListByLocation;
import in.nic.pes.lgd.bean.LocalbodyListbyStateold;
import in.nic.pes.lgd.bean.ParentWiseLBList;
import in.nic.pes.lgd.bean.ReportingIssue;
import in.nic.pes.lgd.bean.UserRegistration;
import in.nic.pes.lgd.bean.WsUserRegistrationForm;
import in.nic.pes.lgd.common.CaptchaValidator;
import in.nic.pes.lgd.dao.LbCoveredLandregionDAO;
import in.nic.pes.lgd.dao.LgdDesignationDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.OrganizationDAO;
import in.nic.pes.lgd.dao.StateDistrictFreezeDAO;
import ws.in.nic.pes.lgdws.dao.WSDao;
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

@Service
public class WSServiceImpl implements WSService {

	@Autowired
	WSDao wsDaoImpl;
	
	@Autowired
	StateDistrictFreezeDAO stateDistrictFDAO;
	
	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;
	
	@Autowired
	LbCoveredLandregionDAO lbCoveredLandregionDAO;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	@Autowired 
	LgdDesignationDAO lgdDesignationDAO;
	
	@Override
	public List<WSState> getStateList()throws Exception {
	return wsDaoImpl.getStateList();
	}

	@Override
	public List<WSDistrict> getDistrictList(String stateCode)throws Exception {
		return wsDaoImpl.getDistrictList(stateCode);
	}

	@Override
	public List<WSDistrict> getDistrict(String districtCode)throws Exception {
		return wsDaoImpl.getDistrict(districtCode);
	}

	@Override
	public List<WSSubdistrict> getSubdistrictList(String districtCode)throws Exception {
		return wsDaoImpl.getSubdistrictList(districtCode);
	}

	@Override
	public List<WSSubdistrict> getSubdistrict(String subDistrictCode)throws Exception {
		return wsDaoImpl.getSubdistrict(subDistrictCode);
	}

	@Override
	public List<WSVillage> getVillageList(String subdistrictCode)throws Exception {
		return wsDaoImpl.getVillageList(subdistrictCode);
	}

	@Override
	public List<WSVillage> getVillage(String villageCode)throws Exception {
		return wsDaoImpl.getVillage(villageCode);
	}

	@Override
	public boolean checkAuthorizedKey(String authKey) throws Exception {
		return wsDaoImpl.checkAuthorizedKey(authKey);
	}

	@Override
	public boolean checkValidEntityCode(Integer entityCode,String entity) throws Exception {
		return wsDaoImpl.checkValidEntityCode(entityCode, entity);
	}

	@Override
	public List<WSLocalbody> getLBListbySubdistrict(String landRegionType, Integer entityCode, Integer lbTypeCode) throws Exception {
		return wsDaoImpl.getLBListbySubdistrict(landRegionType, entityCode, lbTypeCode);
	}

	@Override
	public boolean validateLbTypeCode(Integer lbTypeCode) throws Exception {
		return wsDaoImpl.validateLbTypeCode(lbTypeCode);
	}

	@Override
	public List<WSLocalbody> getLocalbodyListbyDistrictnLbTypeCode(Integer districtCode, Integer lbTypeCode) throws Exception {
		return wsDaoImpl.getLocalbodyListbyDistrictnLbTypeCode(districtCode, lbTypeCode);
	}

	@Override
	public boolean validateOrgTypeCode(Integer orgTypeCode) throws Exception {
		return wsDaoImpl.validateOrgTypeCode(orgTypeCode);
	}

	@Override
	public List<WSOrganization> getOrganizationList(Integer lbTypeCode, boolean isCenter, Integer stateCode) throws Exception {
		return wsDaoImpl.getOrganizationList(lbTypeCode, isCenter, stateCode);
	}

	@Override
	public List<LevelwiseofRuralLbCount> getLevelwiseRuralLBCount(Integer stateCode) throws Exception {
		return wsDaoImpl.getLevelwiseRuralLBCount(stateCode);
	}

	@Override
	public TotalNoofPriLbnVillageCount getTotalNoofPriLbnVillageCount(String QueryName) throws Exception {
		return wsDaoImpl.getTotalNoofPriLbnVillageCount(QueryName);
	}

	@Override
	public Object[] villageWiseLGDDetails(Integer villageCode) throws Exception {
		return wsDaoImpl.villageWiseLGDDetails(villageCode);
	}

	@Override
	public List<WardDetailList> getWardDetailsbyLocalbodyCode(Integer localbodyCode) throws Exception {
		return wsDaoImpl.getWardDetailsbyLocalbodyCode(localbodyCode);
	}

	@Override
	public List<LocalbodyListbyStateold> getExistingPanchayatList(Integer localbodyTypeCode, Integer stateCode) throws Exception {
		Integer lbTypeCode = localbodyTypeCode;
		return this.stateDistrictFDAO.getExistingPanchayatList(lbTypeCode, stateCode);
	}

	@Override
	public List<ParentWiseLBList> getParentWiseLBList(Integer localbodyCode)throws Exception {
		Integer localBodyCode = localbodyCode;
		return localGovtBodyDAO.getParentWiseLBList(localBodyCode);
	}

	@Override
	public List<LandRegion> getcoveredLandregionByLocalbodyCode(Integer localbodyCode) throws Exception {
		// TODO Auto-generated method stub
		return lbCoveredLandregionDAO.getcoveredLandregionByLocalbodyCode(localbodyCode);
	}

	@Override
	public List<Block> getBlockListbyDistrictCode(Integer districtCode, boolean isactive) throws Exception {
	return wsDaoImpl.getBlockListbyDistrictCode(districtCode, isactive);
	}

	@Override
	public List<WSLocalbody> getDistrictwiseBlockandGramPanchyat(Integer districtCode)throws Exception {
				return wsDaoImpl.getDistrictwiseBlockandGramPanchyat(districtCode);
	}
	
	
	/*@Override
	public Map<String, Collection<?>> getImpactDetails(Integer stateCode) throws Exception {
		// TODO Auto-generated method stub
		return wsDaoImpl.getImpactDetails(stateCode);
	}*/
	
	@Override
	public WsUserRegistrationForm insertWsUserRegistrationDetails(WsUserRegistrationForm wsUser)
			throws Exception {
		return wsDaoImpl.insertWsUserRegistrationDetails(wsUser);
	}

	/* (non-Javadoc)
	 * @see ws.in.nic.pes.lgdws.services.WSService#getWsUserRegistrations()
	 */
	@Override
	public List<UserRegistration> getWsUserRegistrations() {
		// 
		return wsDaoImpl.getWsUserRegistrations();
	}

	/* (non-Javadoc)
	 * @see ws.in.nic.pes.lgdws.services.WSService#approveUserRegistration(int)
	 */
	@Override
	public UserRegistration approveUserRegistration(int userId,String type) {
		return wsDaoImpl.approveUserRegistration(userId,type);
		
	}
	
	@Override
	public String captachAuthentication(String captchaAnswer){
		
		WebContext ctx = WebContextFactory.get();
		HttpSession session = ctx.getSession();
		CaptchaValidator captchaValidator = new CaptchaValidator();
		if(captchaValidator.validateCaptcha(session, captchaAnswer))
			return "success";
		else
			return "fail";
	}
	
	@Override
	public List<LgdOrganisationListByLocation> getOrganizationListByLocation(Integer entity_lc, Integer entity_type)
			throws Exception {
		return wsDaoImpl.getOrganizationListByLocation(entity_lc,entity_type);
	}

	@Override
	public void saveUserRegistrationDetails(UserRegistration registration) {
		wsDaoImpl.saveUserRegistrationDetails(registration);
		
	}

	@Override
	public UserRegistration getRegistrationDetails(String emilOrnumber) {
		// TODO Auto-generated method stub
		return wsDaoImpl.getRegistrationDetails(emilOrnumber);
	}

	@Override
	public UserRegistration getRegistration(int userId) {
		return wsDaoImpl.getRegistration(userId);
	}

	@Override
	public void saveRequestWebService(UserRegistration registration) {
		// TODO Auto-generated method stub
		wsDaoImpl.saveRequestWebService(registration);
	}

	@Override
	public void saveReportingIssue(ReportingIssue reportingIssue) {
		wsDaoImpl.saveReportingIssue(reportingIssue);
		
	}

	@Override
	public List<ReportingIssue> reportingIssueList(int userId) {
		// TODO Auto-generated method stub
		return wsDaoImpl.listOfRptIssues(userId);
	}

	@Override
	public List<ReportingIssue> stateReportedIssue(int nodelId) {
		// TODO Auto-generated method stub
		return wsDaoImpl.stateReportedIssue(nodelId);
	}

	@Override
	public void replyReportedIssue(ReportingIssue reportingIssue) {
		// TODO Auto-generated method stub
		wsDaoImpl.replyReportedIssue(reportingIssue);
	}

	@Override
	public List<ReportingIssue> stateReportedIssue(int stateCode, Character userType) {
		return wsDaoImpl.stateReportedIssue(stateCode,userType);
	}

	@Override
	public void saveEmailSmsNotification(EmailNotification emailNotification) {
		// TODO Auto-generated method stub
		wsDaoImpl.saveEmailSmsNotification(emailNotification);
	}

	@Override
	public EmailNotification getSubscriberUserDetails(int userId) {
		// TODO Auto-generated method stub
		return wsDaoImpl.getSubscriberUserDetails(userId);
	}

	@Override
	public boolean isOrganizationExist(Integer olc) throws Exception {
		return organizationDAO.isOrganizationExist(olc);
	}

	@Override
	public boolean isOrgLocatedLevelCodeExist(Integer olc, Integer orgLocatedLevelCode) throws Exception {
		return organizationDAO.isOrgLocatedLevelCodeExist(olc, orgLocatedLevelCode);
	}

	@Override
	public Integer isDesinationListExist(List<Integer> desiginationCodeList, Integer olc) throws Exception {
		return lgdDesignationDAO.isDesinationListExist(desiginationCodeList, olc);
	}
	
	@Override
	public List<WSLocalbody> getUrbanLocalbodyBasedOnLandRegiontypeAndCode(String landRegionType,Integer landRegionTypeCode) throws Exception {
		return wsDaoImpl.getUrbanLocalbodyBasedOnLandRegiontypeAndCode(landRegionType, landRegionTypeCode);
	}

	

	@Override
	public List<VillageListWithHierarchy> fetchVillageListWithHierarchy(Integer subDistrictCode) throws Exception {
	
		return wsDaoImpl.fetchVillageListWithHierarchy(subDistrictCode);
	}
	
	@Override
	public StateWiseGPVillageMappedEntity getStateBlocWiseMapped(Integer stateCode, String finYear,Character queryFlag) {
		return wsDaoImpl.getStateBlocWiseMapped(stateCode,finYear,queryFlag);
	}
	
	@Override
	public List<CoveredVillagesOfBlock> fetchCoveredVillagesOfBlock ( Integer blockCode) throws Exception
	{
		return wsDaoImpl.fetchCoveredVillagesOfBlock (blockCode);
	}
	
	
	@Override
	public List<GetHierarchyOfEntity> getHierarchyOfEntity(String entityType,Integer entityCode) throws Exception {
	
		return wsDaoImpl.getHierarchyOfEntity(entityType ,entityCode);
	}

	@Override
	public List<WSVillage> getVillageListALL(Integer page) throws Exception {
		return wsDaoImpl.getVillageListALL(page);
	}

	
	
}
