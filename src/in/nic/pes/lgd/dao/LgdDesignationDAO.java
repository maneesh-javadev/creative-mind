package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.DesignationLevelSortorder;
import in.nic.pes.lgd.bean.Designationpojo;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;

import java.util.List;

public interface LgdDesignationDAO {

	public List<LgdDesignation> getExistingDesignations(Integer tierSetupCode, String designationType);
	
	public List<LgdDesignation> getExistingDesignationsForDepartment(Integer olc); //Modified by pooja on 14-05-2015
	
	public void saveOrUpdate(LgdDesignation designation);
	
	public void remove(LgdDesignation designation);
	
	public LgdDesignation getDesignationById(Integer designationId);
	
	public List<OrgLocatedAtLevels> getOrgLocatedLevelList(Integer olc) throws Exception;//added by pooja on 14-05-2015
	
	public Integer getLocatedAtLevel(Integer orgLocatedLevelCode); // added by pooja on 19-05-2015
	
	public List<DesignationLevelSortorder> getExistingDesignationLevel(LgdDesignation designation); // added by pooja on 19-05-2015
	
	public void updateIsActive(Integer designationCode); // added by pooja on 21-05-2015
	
	public List<LgdDesignation> getDepartmentDesignationsForReorder(Integer olc,Integer orgLocatedLevelCode); // added by pooja on 01-06-2015
	
	public String getOrgSpecificName(Integer olc,Integer orgLocatedLevelCode); // added by pooja on 01-06-2015
	
	public List<DesignationLevelSortorder> getdesignationLevelsForReorder(Integer orgLocatedLevelCode);// added by pooja on 02-06-2015
	
	public void saveOrUpdateReorderLevel(DesignationLevelSortorder desigLevelSortOrder); // added by pooja on 02-06-2015
	/**
	 * LGD Reporting Methods
	 */
	public abstract LgdDesignation getLBReportingDetail(int tierSetupCode) throws Exception;
	
	public List<LgdDesignation> getOfficialDesignation(int lgTypeCode) throws Exception;
	
	public abstract List<LgdDesignation> getLocalBodyTierSetupParentList(int tierSetupCode)throws Exception;
	
	public List<LgdDesignation> getDesignationReporting(int lgTypeCode)throws Exception;
	
	public List<Designationpojo> recentAddedDesignation(Integer tierSetupCode);
	
	public boolean isDesignationBeingUsed(Integer designationId);
	
	public void saveReOrder(Integer designationCode,Integer order);
	
	public Integer getNextDesignationCodeFromLgdDesignation();
	
	public Integer getNextTransactionsId();
	
	public List<DesignationLevelSortorder> getdesignationLevelsbyDesigCode(Integer desigCode);
	
	public Integer saveOrUpdateWebserviceDesignation(LgdDesignation designation);
	
	public Integer isDesinationListExist(List<Integer> desiginationCodeList,Integer olc) throws Exception;
}
