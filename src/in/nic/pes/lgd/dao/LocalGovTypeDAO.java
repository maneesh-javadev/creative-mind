package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.LocalBodySetup;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalBodyType;

import java.util.List;

import org.hibernate.Session;
 
public interface LocalGovTypeDAO {

	public List<LocalBodyType> getLocalGoveTypeDetails(char category,Session session) throws Exception;
	public int save(LocalBodyType contact)throws Exception;

	/**
	 * 
	 * @param removedCodes
	 * @param stateCode
	 * @return is updated local bodies for urban
	 * @author vinay yadav 21-11-2012
	 */
	public boolean updateUrbanLocalBodies(String removedCodes, Integer stateCode, Integer userId);
	/**
	 * 
	 * @param opration
	 * @param localBodyTypeCodes , comma separated
	 * @param stateCode
	 * @return is updated local bodies for panchyat as per operation (added / removed)
	 * @author vinay yadav 21-11-2012
	 */
	public boolean updateRuralLocalBodies(String opration, String localBodyTypeCodes, Integer stateCode, Integer userId);
	/**
	 * 
	 * @param localBodyTypeCode
	 * @param tiresetupCode
	 * @return LocalBodyTiersSetup
	 * @author vinay yadav 03-04-2013
	 */
	public LocalBodyTiersSetup loadTierSetUp(Integer localBodyTypeCode, Integer tiresetupCode);
	/**
	 * 
	 * @return Max TierSetupCode 
	 * @author vinay yadav 03-04-2013
	 */
	public Integer maxTierSetupCode();
	/**
	 * 
	 * @param statecode
	 * @param category
	 * @return Localbody Setup Code and Version
	 * @author vinay yadav 03-04-2013
	 */
	public Object[] localbodySetupCodeandVersion(Integer statecode, Character category);
	/**
	 * 
	 * @param setup
	 * @return {true, false}
	 * @author vinay yadav 04-04-2013
	 */
	public boolean saveOrUpdateTierSetup(LocalBodyTiersSetup setup);
	/**
	 * 
	 * @param statecode
	 * @param localBodySetupCode
	 * @return LocalBodySetup
	 * @author vinay yadav 04-04-2013
	 */
	public LocalBodySetup getLocalbodySetup(Integer statecode, Integer localBodySetupCode);
	
	/**
	 * List of all rural local government types
	 * @return
	 */
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
	
	public boolean saveOrUpdateLBSetup(LocalBodySetup lbsetup);
}
