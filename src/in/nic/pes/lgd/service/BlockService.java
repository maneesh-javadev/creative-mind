package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.BlockHistory;
import in.nic.pes.lgd.bean.BlockPanchayatSyncBlock;
import in.nic.pes.lgd.bean.BlockVillage;
import in.nic.pes.lgd.bean.BlockWiseVillagesAndUlb;
import in.nic.pes.lgd.bean.BlockwiseVillageMappedUnmapped;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.LocalBodyDto;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.forms.BlockDataForm;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

 public interface BlockService {

	 List<Block> getBlockList(int districtCode)throws Exception;
	
	 List<Block> getTargetBlockList(int blockCode,int districtCode)throws Exception;	 // NO_UCD (unused code)
	 List<Block> getTargetBlockListforSVillBlock(int districtCode,int sourceBlock) throws Exception;
	 List<Block> getTargetBlockListforDistUser(int Sblockcode,int districtCode) throws Exception; // NO_UCD (unused code)
	
	 List<Block> getBlockViewList(String query)throws Exception;
	
	 List<District> getDistrictListbyStateCode(int stateCode)throws Exception;
	
	/* abstract void saveHeadquarters(BlockForm blockForm, int maxHCode,
			int regionCode, int regionVersion, Session session)throws Exception;*/
	
// TODO Remove unused code found by UCDetector
// 	 abstract int saveMapLandRegion(BlockForm blockForm, int blockCode,
// 			int blockVersion, Session session)throws Exception;
	
	 abstract void saveBlock(BlockForm blockForm, int blockCode, int blockVersion, int blockDistrictCode,
			int mapCd, Session session,HttpSession httpSession)throws Exception;
	
	 abstract boolean publishBlock(BlockForm blockForm,HttpSession httpSession)throws Exception;

	 abstract int getDistrictVersion(int districtCode)throws Exception;
	
	//Modify
// TODO Remove unused code found by UCDetector
// 	  List<GovernmentOrder> getGovermentOrderDetail(int orderCode)throws Exception;
// TODO Remove unused code found by UCDetector
// 	  List<MapAttachment> getMapDetailsModify(int mapLandregionCode)throws Exception;
	  List<BlockDataForm> getBlockDetailsModify(int blockCode)throws Exception;
// TODO Remove unused code found by UCDetector
// 	  boolean modifyBlockInfo(BlockForm blockForm,HttpServletRequest request)throws Exception;
// TODO Remove unused code found by UCDetector
// 	 abstract List<Block> getBlockListbyDistrictCode(int districtCode)throws Exception;
	 abstract boolean saveReplaces(int id, int lrReplaces, int blockCode,
			int blockVersion, Session session)throws Exception;
	 abstract boolean saveReplacedBy(int id, int lrReplacedBy,
			int blockCode, int blockVersion, Session session)throws Exception;
	 abstract void saveBlockVillage(int blockCode, int blockVersion, int newBlockCode, int newBlockVersion, Session session)throws Exception;
	 abstract List<Village> getBlockVillagebyBlockCode(int blockCode)throws Exception; 	  // NO_UCD (unused code)
	
	 List<BlockHistory> getBlockHistoryDetail(char blockNameEnglish ,int blockCode)throws Exception;

	 abstract boolean renameContributedBlock(BlockForm blockForm)throws Exception;

// TODO Remove unused code found by UCDetector
// 	 abstract boolean publishBlockandRenamedContriBlocks(BlockForm blockForm,
// 			List<BlockForm> listContriBlockForm)throws Exception;

	 abstract void saveBlockVillagePart(int villCode, int newBlockCode,
			int newBlockVersion, Session session)throws Exception;

	 abstract void saveBlockVillageFull(int villCode, int newBlockCode,
			int newBlockVersion, Session session)throws Exception;

	 boolean invalidateBlock(int stateCode ,int userId,int operationCode,int flagCode,String bvList,BlockForm blockForm,HttpSession session,List<AttachedFilesForm>AttachedFileList)throws Exception;
			
	

	boolean invalidateBlockLoop(String blockCode, String villageCodes, // NO_UCD (unused code)
			HttpSession httpSession)throws Exception;

	 Block getSingleBlockDetailsMaxVersion(int parseInt)throws Exception;
	 void saveDataInAttachment(GovernmentOrder govtOrder,BlockForm govtForm,List<AttachedFilesForm> attachedList,Session session)throws Exception;
	 GovernmentOrder saveDataInGovtOrder(BlockForm govtForm,Session session)throws Exception;
	//new modify Block correction
/*	 boolean modifyBlockCrInfo(BlockForm blockForm,
			HttpServletRequest request, List<AttachedFilesForm> attachedList,List<AttachedFilesForm> attachedMapList,
			HttpSession httpSession) throws Exception;*/
	 List<StateWiseEntityDetails> getStateWiseBlockList(
			Integer statecode, char EntityType, Integer blockCode,
			String blockName, Integer limit, Integer offset)throws Exception;
	 List<StateWiseEntityDetails> getParentWiseList(char entitytype,
			Integer parentCode, Integer limit, Integer offset)throws Exception;
	 int saveDataInMap(BlockForm blockForm,
			List<AttachedFilesForm> attachedList, HttpSession session,Session ses)
			throws Exception ;
	 String insertBlock(HttpServletRequest request,
			HttpSession httpSession, BlockForm blockForm);
// TODO Remove unused code found by UCDetector
// 	 boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm,
// 			List<AttachedFilesForm> attachedList, int blockcode,HttpSession session,Session ses)
// 			throws Exception;
// TODO Remove unused code found by UCDetector
// 	  List<Village> getUnmapVillages(int disCode)throws Exception; 
	 List<Localbody> getUlbbyDistrictCode(int disCode)throws Exception; // NO_UCD (unused code)
// TODO Remove unused code found by UCDetector
// 	 List<Localbody> getblockULb(String  blc)throws Exception;
	 List<BlockwiseVillageMappedUnmapped> getUnmapVillagesbyBlockCode(Integer blockCode, Character listType)throws Exception;  // NO_UCD (unused code)
	 List<BlockWiseVillagesAndUlb> getBlockWiseVillagesAndUlbDetail(Integer blc) throws Exception;
// TODO Remove unused code found by UCDetector
// 	 boolean BlockExist(int disCode,String blockName);
	 List<Block> getBlockListExtrend(Integer districtCode,Integer olc) throws Exception;//Maneesh 9 Mar2013 // NO_UCD (unused code)
	
	 boolean changeBlock(BlockForm blkForm,
			GovernmentOrderForm govtOrderForm,
			List<AttachedFilesForm> attachedList, HttpServletRequest request,int uid)throws Exception;
	
	 boolean modifyBlockCrInfo(BlockForm blockForm,
			HttpServletRequest request, 
			List<AttachedFilesForm> attachedList,
			List<AttachedFilesForm> attachedMapList,boolean delflag,
			String deleteAttachmentId[], HttpSession httpSession)
			throws Exception; 
	
	 boolean changeBlockforTemplate(
			BlockForm blockForm, GovernmentOrderForm govtOrderForm,
			HttpServletRequest request,HttpSession httpSession) throws Exception;

	/**
	 * Get Block Details with their operation state for extended functionality of Department.
	 * @author Ripunj on 06-10-2014
	 * @param districtCode
	 * @param orgCode
	 * @param locatedLevelCode
	 * @return
	 * @throws Exception
	 */
	 List<Block> getBlockListExtended(int districtCode,int orgCode,int locatedLevelCode) throws Exception; // NO_UCD (unused code)
	
	/**
	 * Add For LocalBody Impact Draft Mode on 04-01-2015
	 * @author Ripunj
	 * @param blockCode
	 * @return
	 * @throws Exception
	 */
	 abstract List<Village> getBlockVillagebyBlockCodeForLocalBody(int blockCode)throws Exception; // NO_UCD (unused code)
	/**
	 * Add For LocalBody Impact Draft Mode to get ULBs of Block
	 * @author Ripunj on 04-01-2015 
	 * @param blc
	 * @return
	 * @throws Exception
	 */
	 List<Localbody> getblockULbForLocalBody(String  blc)throws Exception; // NO_UCD (unused code)
	
	/**
	 * Add for getting Villages for selected districts for Localbody impact draft mode 
	 * @author Ripunj on 06-01-2015
	 * @param disCode
	 * @return
	 * @throws Exception
	 */
	 List<Village> getUnmapVillagesForLocalBody(int disCode) throws Exception; // NO_UCD (unused code)
	/**
	 * added by kirandeep on 07/01/2015 for localbody impact
	 * @param districtCode
	 * @param sourceBlock
	 * @return
	 */
	 List<Block> getTargetBlockListforSVillBlockForLocalbody(int districtCode,int sourceBlock); // NO_UCD (unused code)
	
	/**
	 * added by kirandeep for block using statecode on 12/01/2015
	 * for local body impact
	 * 
	 * 
	 */
	 List<Block> getBlockStateWise(int stateCode);
	/**
	 * Add for getting blocks for selected districts 
	 * @author Ripunj on 13-01-2015
	 * @param districtCode
	 * @return
	 * @throws Exception
	 */
	 List<Block> getBlockListbyDistrictCodeForLocalBody(int districtCode)
			throws Exception;
	
	 List<Block> getBlockListbyCreteria(String  districtCodes,String blockCodes,Integer districtCode) throws Exception; // NO_UCD (unused code)
	
	/**
	 * The {@code onloadAttributesBPMapping} used to fetch list of block panchayat needs to be 
	 * mapped with blocks.
	 * @param districtCode
	 * @return
	 * @throws HibernateException
	 * @author Vinay Yadav (Created on 30/12/2015)
	 */
	 BlockPanchayatSyncBlock onloadAttributesBPMapping(Integer stateCode, Integer districtCode) throws HibernateException; // NO_UCD (unused code)
	
	/**
	 * 
	 * @param districtCode
	 * @param bpCode
	 * @return
	 * @throws HibernateException
	 */
	 String createNewBlockForSync(Integer districtCode, Integer bpCode) throws HibernateException; // NO_UCD (unused code)
	
	/**
	 * 
	 * @param districtCode
	 * @param paramSyncCodes
	 * @return
	 * @throws HibernateException
	 */
	 String syncProcessBlockWithBP(Integer districtCode, String paramSyncCodes) throws HibernateException; // NO_UCD (unused code)
	
	 List<Block> getBlockListbyDistrict(Integer districtCode)throws HibernateException;
	
	 boolean saveBlockVillageMapping(BlockVillage blockVillage)throws Exception;
	
	 boolean validateBlockVillageMapping(Integer blockCode,Integer blockVersion,String villageCodes)throws Exception;
	
	// new Block Service shivam
		 Character newBlockNameIsUnique(String newBlock,Integer districtCode ,Integer statecode) throws Exception;
		
		 Boolean getConfigurationBlockVillageMapping(Character isUserType) throws Exception;
		
		 List<LocalBodyDto> getVillageCoverageDetailBlock(Integer blockCode, String villageCode) throws Exception;

		 Boolean saveBlockVillageMappingLb(BlockVillage blockVillage ,String listOfVlc, String unMappListVlc) throws Exception;
		
		 List<LocalBodyDto> getLbCoveredSelected(Integer blockCode, String villageCode) throws Exception; 
		
		 Response saveEffectiveDateEntityBlock(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId,Integer stateCode);
}
