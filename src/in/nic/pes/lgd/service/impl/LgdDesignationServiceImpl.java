package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.DesignationLevelSortorder;
import in.nic.pes.lgd.bean.Designationpojo;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.dao.LgdDesignationDAO;
import in.nic.pes.lgd.service.LgdDesignationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LgdDesignationServiceImpl implements LgdDesignationService{

	@Autowired 
	LgdDesignationDAO lgdDesignationDAO;

	@Override
	public List<LgdDesignation> getExistingDesignations(Integer tierSetupCode, String designationType) {
		// TODO Auto-generated method stub
		return lgdDesignationDAO.getExistingDesignations(tierSetupCode, designationType);
	}

	//Modified by Pooja on 8-5-2015
	@Override
	public List<LgdDesignation> getExistingDesignationsForDepartment(Integer olc) {
		// TODO Auto-generated method stub
		return lgdDesignationDAO.getExistingDesignationsForDepartment(olc);
	}
	
	@Override
	public void saveOrUpdate(LgdDesignation designation) {
		// TODO Auto-generated method stub
		lgdDesignationDAO.saveOrUpdate(designation);
	}

	@Override
	public void remove(LgdDesignation designation) {
		// TODO Auto-generated method stub
		lgdDesignationDAO.remove(designation);
	}

	@Override
	public LgdDesignation getDesignationById(Integer designationId) {
		// TODO Auto-generated method stub
		return lgdDesignationDAO.getDesignationById(designationId);
	}
	
	@Override
	public LgdDesignation getLBReportingDetail(int tierSetupCode) throws Exception {
		// TODO Auto-generated method stub
		return lgdDesignationDAO.getLBReportingDetail(tierSetupCode);
	}

	@Override
	public List<LgdDesignation> getOfficialDesignation(int lgTypeCode) throws Exception {
		// TODO Auto-generated method stub
		return lgdDesignationDAO.getOfficialDesignation(lgTypeCode);
	}

	@Override
	public List<LgdDesignation> getLocalBodyTierSetupParentList(int tierSetupCode) throws Exception {
		// TODO Auto-generated method stub
		return lgdDesignationDAO.getLocalBodyTierSetupParentList(tierSetupCode);
	}

	@Override
	public List<LgdDesignation> getDesignationReporting(int lgTypeCode) throws Exception {
		// TODO Auto-generated method stub
		return lgdDesignationDAO.getDesignationReporting(lgTypeCode);
	}

	@Override
	public List<Designationpojo> recentAddedDesignation(Integer tierSetupCode) {
		// TODO Auto-generated method stub
		return lgdDesignationDAO.recentAddedDesignation(tierSetupCode);
	}

	@Override
	public boolean isDesignationBeingUsed(Integer designationId) {
		// TODO Auto-generated method stub
		return lgdDesignationDAO.isDesignationBeingUsed(designationId);
	}

	@Override
	public void saveReOrder(Integer designationCode,Integer order) {
		lgdDesignationDAO.saveReOrder(designationCode,order);
	}
	//added by pooja on 19-05-2015
	@Override
	public List<DesignationLevelSortorder> getExistingDesignationLevel(LgdDesignation designation)
	{
		return lgdDesignationDAO.getExistingDesignationLevel(designation);
	}
	@Override
	public List<OrgLocatedAtLevels> getOrgLocatedLevelList(Integer olc) throws Exception
	{
		return lgdDesignationDAO.getOrgLocatedLevelList(olc);
	}
	
	@Override
	public Integer getLocatedAtLevel(Integer orgLocatedLevelCode)
	{
		return lgdDesignationDAO.getLocatedAtLevel(orgLocatedLevelCode);
	}
	
	@Override
	public void updateIsActive(Integer designationCode)
	{
		lgdDesignationDAO.updateIsActive(designationCode);
	}
	// added by pooja on 01-06-2015
	@Override
	public List<LgdDesignation> getDepartmentDesignationsForReorder(Integer olc,Integer orgLocatedLevelCode)
	{
		return lgdDesignationDAO.getDepartmentDesignationsForReorder(olc, orgLocatedLevelCode);
	}
	@Override
	public String getOrgSpecificName(Integer olc,Integer orgLocatedLevelCode)
	{
		return lgdDesignationDAO.getOrgSpecificName(olc, orgLocatedLevelCode);
	}
	@Override
	public List<DesignationLevelSortorder> getdesignationLevelsForReorder(Integer orgLocatedLevelCode)
	{
		return lgdDesignationDAO.getdesignationLevelsForReorder(orgLocatedLevelCode);
	}
	@Override
	public void saveOrUpdateReorderLevel(DesignationLevelSortorder desigLevelSortOrder)
	{
		lgdDesignationDAO.saveOrUpdateReorderLevel(desigLevelSortOrder);
	}

	@Override
	public Integer getNextDesignationCodeFromLgdDesignation() {
		return lgdDesignationDAO.getNextDesignationCodeFromLgdDesignation();
	}

	@Override
	public List<DesignationLevelSortorder> getdesignationLevelsbyDesigCode(Integer desigCode) {
		return lgdDesignationDAO.getdesignationLevelsbyDesigCode(desigCode);
	}

	@Override
	public Integer saveOrUpdateWebserviceDesignation(LgdDesignation designation) {
		return lgdDesignationDAO.saveOrUpdateWebserviceDesignation(designation);
	}


	
	
}
