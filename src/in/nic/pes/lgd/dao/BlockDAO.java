package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.BlockDistricts;
import in.nic.pes.lgd.bean.BlockHistory;
import in.nic.pes.lgd.bean.BlockPK;
import in.nic.pes.lgd.bean.BlockPanchayatSyncBlock;
import in.nic.pes.lgd.bean.BlockVillage;
import in.nic.pes.lgd.bean.BlockWiseVillagesAndUlb;
import in.nic.pes.lgd.bean.BlockwiseVillageMappedUnmapped;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.LocalBodyDto;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.Response;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

 public interface BlockDAO {
    //view
	List<Block> getBlockListbydistrictCode(int districtCode) throws Exception;
	
	 List<Block> getTargetBlockList(int blockCode,int districtCode) throws Exception;
	
	 List<Block> getTargetBlockListforSVillBlock(int districtCode,int sourceBlock) throws Exception;
	 List<Block> getTargetBlockListforDistUser(int Sblockcode,int districtCode) throws Exception;
	List<Block> getBlockViewList(String query) throws Exception;

	//Modify
	 int getMaxBlockVersionbyCode(int blockCode) throws Exception;
	 int getMaxMapCode(String query)throws Exception;
	/* List<GovernmentOrder> getGovermentOrderDetail(int orderCode)throws Exception;*/
	/* List<MapAttachment> getMapDetailsModify(int mapLandregionCode)throws Exception;*/
	 void	ChangeBlock(int blockCode,String blockNameEnglish,int userId,String blockNameLocal,String aliasEnglish,String aliasLocal)throws Exception;
	 boolean updateMapLanRegion(int mapCode, String coordinates, int blockCode,Session session)throws Exception;
	
	 List<Block> viewBlockDetails(String sql)throws Exception;
	
	 List<Block> getBlockDetailsModify(int blockCode)throws Exception;
	/* boolean modifyBlockInfo(BlockForm blockForm, BlockPK blockPk, int map_landRegionCode,Session session)throws Exception;*/
	 abstract int getMaxBlockCode()throws Exception;
	 abstract boolean saveBlock(Block block, Session session)throws Exception;
	 abstract boolean updateIsActive(boolean isActive, BlockPK blockPK, Session session)throws Exception;
	 abstract boolean updateLrReplaces(int lrReplaces, BlockPK blockPK,
			Session session)throws Exception;
	 abstract boolean saveBlockVillage(BlockVillage blockVillage, Session session)throws Exception;
	 abstract Block getBlockDetail(BlockPK blockPK, Session session)throws Exception;
// TODO Remove unused code found by UCDetector
// 	 abstract boolean getNewBlockVillageSet(int oldBlockCode, int oldBlockVersion,
// 			int newBlockCode, int newBlockVersion, Session session)throws Exception;
	 abstract boolean saveBlockDistrict(BlockDistricts blockDistrict, Session session)throws Exception;
	 abstract List<Village> getBlockVillagebyBlockCode(int blockCode)throws Exception;
	
	 List<BlockHistory> getBlockHistoryDetail(char blockNameEnglish,int blockCode)throws Exception;
	 List<Headquarters> getHeadquarterModify(int blockCode,int blockversion)throws Exception;
// TODO Remove unused code found by UCDetector
// 	 void SetGovermentOrderEntity(String entityCode,char entityType)throws Exception;

	/*boolean invalidateFunctionCall(String blockCodes, int blockCode,
			int roleCode, String villageCodes)throws Exception;*/
	 String invalidateBlock(int stateCode ,int userId,int operationCode,int flagCode,
			 String bvList,BlockForm blockForm)throws Exception;

	Block getSingleDistrictDetailsMaxVersion(String sQuery)throws Exception;
	 List<StateWiseEntityDetails> getStateWiseBlockList(Integer statecode,char EntityType,Integer blockCode,String blockName, Integer limit, Integer offset)throws Exception;
	 List<StateWiseEntityDetails> getParentWiseList(char entitytype,
			Integer parentCode, Integer limit, Integer offset)throws Exception;
	 int saveDataInMap(BlockForm BlockForm,
			List<AttachedFilesForm> attachedList, HttpSession session,Session ses);
	 String insertBlock(HttpServletRequest request,
			HttpSession httpSession, BlockForm BlockForm);
	/* boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm,
			List<AttachedFilesForm> attachedList,int blockcode, HttpSession session,Session ses);*/
	/*  List<Village> getUnmapVillages(int disCode)throws Exception; */
	 List<Localbody> getULBbyDistrictCode(int disCode)throws Exception;
	 List<Localbody> getblockULb(String  blc)throws Exception;
	  List<BlockwiseVillageMappedUnmapped> getUnmapVillagesbyBlockCode(Integer blockCode, Character listType)throws Exception; 
	 boolean saveVillagesinBlock(String blkCode,String vilCode,String unmapVillageCode) throws Exception;
	 List<BlockWiseVillagesAndUlb> getBlockWiseVillagesAndUlbDetailDAO(Integer blc) throws Exception;
	/* boolean BlockExist(int disCode, String BlockName);*/
	 List<Block> getBlockListExtrend(Integer districtCode,Integer olc) throws Exception; //Maneesh 9Mar2013
	
	 String	ChangeBlock(int blkCode,
			String blKNameEnglish,String blKNameEnglishch, int userId,
			String blKNameLocal, String aliasEnglish,
			String aliasLocal, Session session,String orderNo,Date orderDate,Date effectiveDate,Date govPubDate,Integer slc)throws Exception;
	/**
	 * @author Ripunj on 06-10-2014
	 * @param districtCode
	 * @param orgCode
	 * @param locatedLevelCode
	 * @return
	 * @throws Exception
	 */
	 List<Block> getBlockListExtended(int districtCode,int orgCode,int locatedLevelCode) throws Exception;
	/**
	 * Add for getting villages for selected blocks for localbody impact draft mode 
	 * @author Ripunj on 06-01-2015
	 * @param blockCode
	 * @return
	 */
	 List<Village> getBlockVillagebyBlockCodeForLocalBody(int blockCode);
	/**
	 * added by kirandeep on 07/01/2015 for localbody impact
	 * @param districtCode
	 * @param sourceBlock
	 * @return
	 */
	
	 List<Block> getTargetBlockListforSVillBlockForLocalbody(int districtCode,int sourceBlock);
	
	
	/**
	 * Add for getting ULBs for selected blocks for localbody impact draft mode 
	 * @author Ripunj on 06-01-2015
	 * @param blc
	 * @return
	 * @throws Exception
	 */
	 List<Localbody> getblockULbForLocalBody(String  blc)throws Exception;
	/**
	 * Add for getting Villages for selected districts for Localbody impact draft mode 
	 * @author Ripunj on 06-01-2015
	 * @param disCode
	 * @return
	 * @throws Exception
	 */
	 List<Village> getUnmapVillagesForLocalBody(int disCode) throws Exception;
	
	/**
	 * added by kirandeep for block using statecode on 12/01/2015
	 * for local body impact
	 * 
	 * 
	 */
	 List<Block> getBlockStateWise(int stateCode);
	
	 List<Block> getBlockListbyCreteria(List<Integer>  districtList,List<Integer> blockList,Integer districtCode) throws Exception;

	/**
	 * The {@code onloadAttributesBPMapping} used to fetch list of block panchayat needs to be 
	 * mapped with blocks.
	 * @param districtCode
	 * @return
	 * @throws HibernateException
	 * @author Vinay Yadav (Created on 30/12/2015)
	 */
	 BlockPanchayatSyncBlock onloadAttributesBPMapping(Integer stateCode, Integer districtCode) throws HibernateException;
	
	/**
	 * 
	 * @param districtCode
	 * @param bpCode
	 * @return
	 * @throws HibernateException
	 */
	 String createNewBlockForSync(Integer districtCode, Integer bpCode) throws HibernateException;
	
	/**
	 * 
	 * @param districtCode
	 * @param paramSyncCodes
	 * @return
	 * @throws HibernateException
	 */
	 String syncProcessBlockWithBP(Integer districtCode, String paramSyncCodes) throws HibernateException;
	
	
	/**
	 * @author Maneesh Kumar
	 * @since 17Mar2016 
	 * @param districtCode
	 * @return
	 * @throws HibernateException
	 */
	 List<Block> getBlockListbyDistrict (Integer districtCode)throws HibernateException;
	
	 boolean saveBlockVillageMapping(BlockVillage blockVillage)throws Exception;
	
	 boolean validateBlockVillageMapping(Integer blockCode,Integer blockVersion,List<Integer> villageList)throws Exception;

	// for unique block shivam
	 Character newblockNameIsUnique(String newBlck, Integer districtCode, Integer statecode) throws Exception;
	
	 Boolean getConfigurationBlockVillageMapping(Character isUserType) throws Exception;
	
	 List<LocalBodyDto> getVillageCoverageDetailBlock(Integer blockCode, String villageCode) throws Exception;
	
	 boolean saveBlockVillageMappingLb(BlockVillage blockVillage ,String listOfVlc,String unMappListVlc) throws Exception;
	
	 List<LocalBodyDto> getLbCoveredSelected(Integer blockCode, String villageCode) throws Exception;
	
	 List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion,int minorVersion);
	 Response saveEffectiveDateEntityBlock(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId,Integer stateCode);
}
