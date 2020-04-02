package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.Designation;
import in.nic.pes.lgd.bean.Designationpojo;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LocalBodySetup;
import in.nic.pes.lgd.bean.LocalBodySubtype;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalbodyTypeInState;
import in.nic.pes.lgd.bean.OrganizationDesignation;
import in.nic.pes.lgd.bean.ReportingSetup;

import java.util.List;

import org.hibernate.Session;

public interface LocalBodySetupDAO {

// TODO Remove unused code found by UCDetector
// 	public boolean saveLGSetupDetails(LGSetupForm lGSetupForm, Session session)throws Exception;

	public int getMaxRecords(String query)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public int getMaxRecords(String query,Session session)throws Exception;

	public boolean saveDesignation(Designation designation, Session session)throws Exception;

	public boolean saveReportingSetup(ReportingSetup reportingSetupList, Session session) throws Exception;

	public boolean saveLocalBodySetup(LocalBodySetup localBodySetup,
			Session session)throws Exception;

	public boolean saveLocalBodyTierSetup(
			LocalBodyTiersSetup localBodyTiersSetup, Session session)throws Exception;
	
	//Maneesh
	public Integer getSlcfromStateDAO(String hql) throws Exception;
	//Maneesh

	public boolean saveLocalBodySubType(LocalBodySubtype localBodySubtype,
			Session session)throws Exception;
	
	public List<OrganizationDesignation> getOrganizationDesignationDetails(int olcCode, int olcLevel) throws Exception;

	public boolean getStateCode(int stateCode)throws Exception; // NO_UCD (unused code)

	public List<GetLocalGovtSetup> isStateSetup(int stateCode, char category)throws Exception;
	
	public List<GetLocalGovtSetup> isStateSetupDisturbed(int stateCode, char category, char level) throws Exception;

	@SuppressWarnings("rawtypes")
	public List getLGType(String sql, int stateCode, int stateVersion,
			char category)throws Exception;

	@SuppressWarnings("rawtypes")
	public List getSubType(int statecode)throws Exception;

	public List<Designationpojo> getDesignation(int tierSetupCode)throws Exception;

	@SuppressWarnings("rawtypes")
	public List getSubTier(int statecode)throws Exception;

	@SuppressWarnings("rawtypes")
	public List getDesignationElected(int statecode, boolean islocalbody)throws Exception;

	@SuppressWarnings("rawtypes")
	public List getDesignationOfficail(int statecode, boolean islocalbody)throws Exception;

	public abstract List<LocalBodySubtype> getSubTypeDetails(int stateCode)throws Exception;

	public abstract List<LocalBodyTiersSetup> getTierSetupDetails(
			int setupCode, int setupVersion)throws Exception;

	
	
	public abstract List<LocalBodySetup> getLocalBodySetupDetails(
			int stateCode, int stateVersion,char category)throws Exception;

// TODO Remove unused code found by UCDetector
// 	public abstract boolean updateIsActiveLocalBodySetup(boolean isActive,
// 			int setupCode, int setupVersion, Session session)throws Exception;

	public boolean updateIsActiveTierSetUp(boolean isActive, int setupCode,
			int setupVersion, Session session)throws Exception;

// TODO Remove unused code found by UCDetector
// 	public boolean updateIsActiveSubType(boolean isActive, int stateCode,
// 			int statepVersion, Session session)throws Exception;

	public List<Designation> getDesignationDetails(boolean isElected,
			int tierSetupCode)throws Exception;
	

// TODO Remove unused code found by UCDetector
// 	public abstract boolean deleteDesignation(int tierSetupCode)throws Exception;

	public List<LocalbodyTypeInState> getLBList(int stateCode, char category)throws Exception;

	public boolean saveLBSubType(LocalBodySubtype localBodySubtypeBean,
			Session session)throws Exception;

	public boolean updateIsActiveSetup(LocalBodySetup localBodySetupBean,
			Session session)throws Exception;
	public boolean updateIsActiveLbSubtype(boolean isActive, int tierSetupCode, Session session)throws Exception;
	
	//public List<Designation> getTierPanchayat(List<GetLocalGovtSetup> tierSetupList, Session session) throws Exception;
	
	//public List<Designation> getTierTraditional(List<GetLocalGovtSetup> tierSetupList, Session session) throws Exception;
	
	//public List<Designation> getTierUrban(List<GetLocalGovtSetup> tierSetupList, Session session) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<LocalBodySetup> getLocalBodySetupDetails(int stateCode,
// 			int stateVersion, Session session) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<State> getStateDetailDAO(String HQL, Session session) throws Exception;

	public List<GetLocalGovtSetup> getLocalbodyDetailDAO(int stateCode)
			throws Exception;
	
	public boolean checkTierPanchayatforDesignation(List<GetLocalGovtSetup> tierSetupList,Integer stateCode) throws Exception;
	/**
	 * For get Local Govt Setup in order of local body type code.
	 * @author Ripunj on 06-02-2015
	 * @param stateCode
	 * @return
	 * @throws Exception
	 */
	public List<GetLocalGovtSetup> getLocalbodyDetailDAOOrderInLocalBodyType(
			int stateCode) throws Exception;
	
	public List<GetLocalGovtSetup> getLocalbodyDetailForVillage(int stateCode)throws Exception;
	
	public List<GetLocalGovtSetup> getLocalbodyDetailForTraditional(int stateCode,char category)throws Exception;

	public List<GetLocalGovtSetup> getLocalbodyDetailDAO(int stateCode, String localGovtListFlag)throws Exception;
	public List<GetLocalGovtSetup> getLocalbodyDetailCategoryDAO(int stateCode,Character category);
	public List<GetLocalGovtSetup> getLocalbodyDetailDAOCantonment(int stateCode) throws Exception;
}
