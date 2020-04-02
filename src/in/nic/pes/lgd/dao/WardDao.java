package in.nic.pes.lgd.dao;

import java.util.List;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;

import in.nic.pes.lgd.bean.DRAFTWARDCOVERAGE;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.WardCoverageDetail;
import in.nic.pes.lgd.bean.localbodywardtemp;
import in.nic.pes.lgd.forms.WardForm;

public interface WardDao {

public List<LBTypeHierarchy>  getLBHierarchyList(Integer stateCode ,String PANCHAYAT_TYPE)throws Exception;
	
	boolean saveTempWards(List<localbodywardtemp> insertListWard,List<localbodywardtemp> updateListWard,List<localbodywardtemp>deleteListWard) throws Exception;
	
	boolean publishWards(WardForm wardForm) throws Exception;
	
	List<localbodywardtemp> getLocalbodyWardTempList(Integer lblc)throws Exception;
	
	List<LocalbodyWard> getLocalbodyWardList(Integer lblc)throws Exception;
	
	List<LBTypeDetails> getLBTypeDetailsList(Integer stateCode,String PANCHAYAT_TYPE) throws Exception;	
	
	Integer getlblcCodeofLocalbody(Integer localBodyCode) throws Exception;
	
	List<LocalbodyWard> getwardDetail(Integer wardCode) throws Exception;
	
	Object[] getLocalbodyWardListbyLoclbodyCode(Integer localBodyCode) throws Exception;
	
	boolean checkLocalbodtSetupbyState(Integer stateCode,String PANCHAYAT_TYPE)throws Exception;
	
	List<LocalbodyWard> getWardDetails(Integer lblc) throws Exception;
	
	/**
	 *   These Methods add for 'Add n Manage  Ward Coverage '
	 *   @author Maneesh Kumar
	 *   @since  01DEc2014
	 */
	Object[] getWardCoverageDetail(Integer lblc,Integer wardCode,String selLevel,boolean isUrban) throws Exception ;
	
	List<WardCoverageDetail> getWardCoverageDetailbyLevel(Integer lblc, Integer wardCode,Character level,String entityCodeList,boolean wardCovergaeFlag) throws Exception;
	
	boolean saveWardCoverageDetail(Integer wardCode,String selLevel,Object wardCoverageDistrictList[],Object wardCoverageSubdistrictList[],Object wardCoverageVillageList[],Integer userId,String category) throws Exception;
	
	/**
	 * End of 'Add n Manage  Ward Coverage ' methods.
	 */
	Object[] getBasicDetailofLocalbody(Integer localBodyCode,String PANCHAYAT_TYPE) throws Exception;
	
	List<LocalbodyWard> getDeleteWardList(Integer lblc)throws Exception;
	
	String restoreDeleteWard(Integer wardCode,Integer wardVersion,Integer userId)throws Exception;
	
	boolean checkWardDuplicateInDraft(Integer lblc,String wardNameEnglish)throws Exception;
	
	boolean checkWardCoverageExistinLocalbody(Integer lblc) throws Exception;
	
	List<LocalbodyWard> fetchDraftWardCoverage(Integer lblc) throws Exception;
	
	boolean publishWardCoverage(WardForm wardForm) throws Exception;
	
	boolean checkWardDuplicateInDeletedWard(Integer lblc,String wardNameEnglish)throws Exception;
	
	boolean saveLocalNameOfWard(String arrayupdate ,Integer userCode) throws Exception;
	
	List<DRAFTWARDCOVERAGE> getDraftWardCoverageDetails(Integer wardCode,Integer wardVersion,boolean isDraft);

}
