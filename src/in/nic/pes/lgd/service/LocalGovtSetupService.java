package in.nic.pes.lgd.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import in.nic.pes.lgd.bean.Designation;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.LocalBodySetup;
import in.nic.pes.lgd.bean.LocalBodySubtype;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalbodyTypeInState;
import in.nic.pes.lgd.bean.OrganizationDesignation;
import in.nic.pes.lgd.forms.DesignationForm;
import in.nic.pes.lgd.forms.DesignationReportingForm;
import in.nic.pes.lgd.forms.LGSetupForm;
 

public interface LocalGovtSetupService {
	
	public List<LocalBodyType> getLGBList(int localBodyTypeCode)throws Exception; // NO_UCD (unused code)
	
	public boolean saveLocalGovSetup(List<List<LocalBodyType>> localBodyTypeListToSave,int stateCode)throws Exception;
	public boolean saveLocalGovSetupUrban(List<List<LocalBodyType>> localBodyTypeListToSave,int stateCode)throws Exception;
	
	public int saveDesignation(DesignationForm designationForm)throws Exception;

	public List<LocalBodyType> getLgDetails(int statecode, char category)throws Exception;
	
	public boolean saveReportingLBSetup(DesignationReportingForm designationReportingForm)throws Exception;
	
	public List<GetLocalGovtSetup> isStateSetup(int stateCode, char category)throws Exception;
	
	public List<GetLocalGovtSetup> isStateSetupDisturbed(int stateCode, char category, char level) throws Exception;

	public List<LocalBodyType> getLGTypeForDesignation(int statecode, char category)throws Exception;

	public List<Designation> getDesignation(int lgTypeCode)throws Exception; // NO_UCD (unused code)
	
	public List<Integer> getParentCode(LGSetupForm lgSetupForm)throws Exception; // NO_UCD (unused code)
	
	public List<OrganizationDesignation> getOrganizationDesignationDetails(int olcCode, int olcLevel) throws Exception; // NO_UCD (unused code)
	
	@SuppressWarnings("rawtypes")
	public List getSubType()throws Exception;
	@SuppressWarnings("rawtypes")
	public List getDesignation()throws Exception;
	@SuppressWarnings("rawtypes")
	public List getSubTier()throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List getDesignationElected()throws Exception;
	@SuppressWarnings("rawtypes")
	public List getDesignationOfficail()throws Exception;
	
	
	public List<LGSetupForm> getLocalGovtTypeDBForModify()throws Exception;
	public List<LocalBodySubtype> getSubTypeDetails(int tierSetupCode)throws Exception;

	public abstract List<Designation> getDesignationDetails(boolean isElected, int tierSetupCode)throws Exception; // NO_UCD (unused code)
	public boolean saveLocalBodySubType(LGSetupForm lGSetupForm,int stateCode, HttpSession session)throws Exception;
	public List<LocalBodyType> loopThroughTiers(LGSetupForm lGSetupForm)throws Exception;
	//Maneesh
	public boolean modify(int stateCode, List<List<LocalBodyType>> localBodyTypeListToSave,char category)throws Exception;
	//Maneesh
	boolean modifyUrban(int stateCode,List<List<LocalBodyType>> localBodyTypeListToSave,char category)throws Exception;

	List<LocalbodyTypeInState> getActiveLBTinState(int stateCode, char category)throws Exception;

	public boolean getParentTierSetupList(int tierSetupCode) throws Exception;  // NO_UCD (unused code)
	
// TODO Remove unused code found by UCDetector
// 	public abstract List<Designation> getTierPanchayat(List<GetLocalGovtSetup> tierSetupList) throws Exception; 
	
// TODO Remove unused code found by UCDetector
// 	public abstract List<Designation> getTierTraditional(List<GetLocalGovtSetup> tierSetupList) throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public abstract List<Designation> getTierUrban(List<GetLocalGovtSetup> tierSetupList) throws Exception;
	public List<LGSetupForm> getLocalGovtTypeDB(char category,Session session) throws Exception;
	
	public List<GetLocalGovtSetup> getLocalbodyDetail(int stateCode)throws Exception;
	
	/**
	 * 
	 * @param removedCodes , comma separated
	 * @param stateCode
	 * @return is updated local bodies for urban
	 * @author vinay yadav 15-11-2012
	 */
	public boolean updateUrbanLocalBodies(String removedCodes, Integer stateCode, Integer userId);
	
	/**
	 * 
	 * @param opration
	 * @param localBodyTypeCodes , comma separated
	 * @param stateCode
	 * @return is updated local bodies for panchyat as per operation (added / removed)
	 * @author vinay yadav 15-11-2012
	 */
	public boolean updateRuralLocalBodies(String opration, String localBodyTypeCodes, Integer stateCode, Integer userId);
	public  List<GetLocalGovtSetup> getLocalbodyDetailbyCode(int stateCode,int lblc)throws Exception; // NO_UCD (unused code)
	public boolean checkDesignationPesDelete(Integer designationCode) throws Exception;  // NO_UCD (unused code)
	public boolean checkOrganDesignationDelete(Integer designationCode) throws Exception;  // NO_UCD (unused code)
	public boolean checkDesignationPesDeleteM(Integer designationCode) throws Exception; // NO_UCD (unused code)
	public List<Designation> isLBReportingExist(Integer designationCode)throws Exception;  // NO_UCD (unused code)
	public List<LgdDesignation> isOrganizationReportingExist(Integer designationCode) throws Exception; // NO_UCD (unused code)
	public List<OrganizationDesignation> isLBReportingOrganExist(Integer designationCode) throws Exception; // NO_UCD (unused code)
	public List<Designation> getTopDesignation(int lgTypeCode) throws Exception; // NO_UCD (unused code)
	public boolean checkTierPanchayatforDesignation(List<GetLocalGovtSetup> tierSetupList,Integer stateCode) throws Exception;
	public Designation getParentTierSetupCodeforDes(int desCode) throws Exception; // NO_UCD (unused code)
	public List<Designation> isLbReportRefExist(Integer designationCode)throws Exception; // NO_UCD (unused code)
	
	public LocalBodyTiersSetup loadTierSetUp(Integer localBodyTypeCode, Integer tiresetupCode);
	
	public Integer maxTierSetupCode();
	
	public Object[] localbodySetupCodeandVersion(Integer statecode, Character category);
	
	public boolean saveOrUpdateTierSetup(LocalBodyTiersSetup setup);

	public LocalBodySetup getLocalbodySetup(Integer statecode, Integer localBodySetupCode);
	
	public List<Object[]> getLocalBodyTypesRural();
	
	public List<Object[]> getDefinedTiersSetup(Integer statecode);
	
	public LocalBodySetup addNewSetup(Integer stateCode, Integer userId);
	
	/**
	 * The {@code invalidateExistingSetup} invalidate all existing records for 
	 * setup code and state code.
	 * @param setupCode
	 * @param stateCode
	 * @param userId
	 * @return Local Body Setup Instance
	 */
	public LocalBodySetup invalidateExistingSetup(Integer setupCode, Integer stateCode, Integer userId);
	
	public Integer getParentCodeTierSetup(Integer localbodyType, Integer setupcode, Integer setupversion);
	
// TODO Remove unused code found by UCDetector
// 	public boolean saveOrUpdateLBSetup(LocalBodySetup lbsetup);
	
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

	public List<GetLocalGovtSetup> getLocalbodyDetail(int stateCode, String localGovtListFlag) throws Exception;
	
	public List<GetLocalGovtSetup> getLocalbodyDetailCategory(int stateCode,Character category);
}
