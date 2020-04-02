package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.BlockDistricts;
import in.nic.pes.lgd.bean.BlockHistory;
import in.nic.pes.lgd.bean.BlockPK;
import in.nic.pes.lgd.bean.BlockPanchayatSyncBlock;
import in.nic.pes.lgd.bean.BlockVillage;
import in.nic.pes.lgd.bean.BlockWiseVillagesAndUlb;
import in.nic.pes.lgd.bean.BlockwiseVillageMappedUnmapped;
import in.nic.pes.lgd.bean.ChangeBlock;
import in.nic.pes.lgd.bean.CreateBlockBean;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.InvalidateBlockBean;
import in.nic.pes.lgd.bean.LocalBodyDto;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.dao.BlockDAO;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.entities.SubdistrictDraft;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.interceptor.LanguageListener;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.restructure.reporting.dao.impl.ViewEntityDetailsDaoImpl;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.service.VillageService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pes.attachment.util.AttachedFilesForm;

@Repository
@Transactional
public class BlockDAOImpl implements BlockDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private VillageService villageService;
	
	private static final Logger log = Logger.getLogger(BlockDAOImpl.class);

	@Override
	public boolean saveBlock(Block block, Session session) {
		try {
			session.save(block);
			return true;
		} catch (HibernateException e) {
			log.error(e);
			return false;
		}
	}

	
	@Override
	public int getMaxBlockCode() {
		Session session = sessionFactory.openSession();
		int MaxCode = 0;
		try {
			MaxCode = Integer.parseInt(session.createSQLQuery("select COALESCE(max(block_code),1) from block").list().get(0).toString());
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return MaxCode;
	}

	/*
	 * for Handling Blocks falling under 2 districts @Ashish Dhupia @date
	 * 09-09-2014
	 */
	/**
	 * Modified by Ripunj on 06-01-2015 For LocalBody Impact Draft Mode
	 */
	@SuppressWarnings("unchecked")
	public List<Block> getBlockListbydistrictCode(int districtCode) {
		// Session session = sessionFactory.openSession();
		SQLQuery criteria = null;
		Session session = null;
		List<Block> blockList = new ArrayList<Block>();
		try {
			session = sessionFactory.openSession();

			criteria = session.createSQLQuery(" select case when b.block_Code  in (select * from get_draft_used_lb_lr_temp(b.block_Code,'B')) then  cast ('F' as character)  else cast('A' as character) "
					+ "end as operation_state, b.block_Code as blockCode,b.block_Name_English as blockNameEnglish from   Block b where b.isactive=true and b.dlc=:districtCode  "
					+ " UNION "
					+ " select case when b.block_Code  in (select * from get_draft_used_lb_lr_temp(b.block_Code,'B')) then  cast ('F' as character)  else cast('A' as character) end as operation_state,"
					+ " b.block_Code as blockCode,b.block_Name_English as blockNameEnglish   from  block_districts bd,block b where bd.dlc=:districtCode and bd.block_district_code=b.block_district_code and"
					+ " b.isactive and b.dlc=0 order by blockNameEnglish");
			criteria.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			criteria.addScalar("operation_state").addScalar("blockCode").addScalar("blockNameEnglish");
			criteria.setResultTransformer(Transformers.aliasToBean(Block.class));
			blockList = criteria.list();

			// query.setParameter("districtCode", districtCode,
			// Hibernate.INTEGER);
			
			// blockList = query.list();
			return blockList;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<Block> getTargetBlockList(int blockCode, int districtCode) {

		Query criteria = null;
		Session session = null;
		List<Block> blockList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Block b where b.isactive=true and b.dlc =:districtCode and b.blockPK.blockCode !=:blockCode order by b.blockNameEnglish");
			criteria.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			criteria.setParameter("blockCode", blockCode, Hibernate.INTEGER);
			blockList = criteria.list();
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return blockList;

		/*
		 * Session session=sessionFactory.openSession(); try { return
		 * sessionFactory.getCurrentSession().createQuery(
		 * "from Block b where b.isactive=true and b.district.districtPK.districtCode ="
		 * +districtCode + " and b.blockPK.blockCode !="+
		 * blockCode+" order by b.blockNameEnglish").list(); } catch
		 * (HibernateException e) {
		 * log.error(e);
		 * return null; } finally{ session.close(); }
		 */

	}

	@SuppressWarnings("unchecked")
	public List<Block> getTargetBlockListforSVillBlock(int districtCode, int sourceBlock) {
		Query criteria = null;
		Session session = null;
		List<Block> blockList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Block b where b.isactive=true and b.dlc =:districtCode and b.blc !=:sourceBlock order by b.blockNameEnglish");
			criteria.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			criteria.setParameter("sourceBlock", sourceBlock, Hibernate.INTEGER);
			blockList = criteria.list();
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return blockList;
	}

	@SuppressWarnings("unchecked")
	public List<Block> getTargetBlockListforDistUser(int sblockcode, int districtCode) {
		Query criteria = null;
		Session session = null;
		List<Block> blockList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Block b where b.isactive=true and b.dlc =:districtCode and blc !=:sblockcode order by b.blockNameEnglish");
			criteria.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			criteria.setParameter("sblockcode", sblockcode, Hibernate.INTEGER);

			blockList = criteria.list();
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return blockList;
	}

	@SuppressWarnings("unchecked")
	public List<Block> getBlockViewList(String query) {
		Session session = null;
		Query sqlQuery = null;
		List<Block> list = null;
		try {
			session = sessionFactory.openSession();
			sqlQuery = session.createQuery(query);
			list = sqlQuery.list();
			return list;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getBlockDetailsModify(int blockCode) {
		// TODO Auto-generated method stub

		Query criteria = null;
		Session session = null;
		List<Block> blockList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Block where isactive=true and blockCode=:blockCode order by blockNameEnglish");
			criteria.setParameter("blockCode", blockCode, Hibernate.INTEGER);
			blockList = criteria.list();
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return blockList;

	}

	@Override
	public int getMaxBlockVersionbyCode(int blockCode) {

		Query criteria = null;
		Session session = null;
		int MaxVersionCode = 0;
		try {

			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select COALESCE(max(block_version),1) from block where block_code=:blockCode");
			criteria.setParameter("blockCode", blockCode, Hibernate.INTEGER);
			MaxVersionCode = Integer.parseInt(criteria.list().get(0).toString());
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxVersionCode;

	}

	/*@Override
	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) {

		Query criteria = null;
		Session session = null;
		List<GovernmentOrder> governmentOrderList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from GovernmentOrder where order_code=:orderCode");
			criteria.setParameter("orderCode", orderCode, Hibernate.INTEGER);
			governmentOrderList = criteria.list();
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return governmentOrderList;

	}*/

	/*@Override
	public List<MapAttachment> getMapDetailsModify(int mapLandregionCode) {

		Query criteria = null;
		Session session = null;
		List<MapAttachment> mapLandRegionList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from MapAttachment where map_attachment_code=:mapLandregionCode");
			criteria.setParameter("mapLandregionCode", mapLandregionCode, Hibernate.INTEGER);
			mapLandRegionList = criteria.list();
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mapLandRegionList;

	}
*/
	@Override
	public boolean updateMapLanRegion(int mapCode, String coordinates, int blockCode, Session session) {

		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			// mapCode=35;
			tx = session.beginTransaction();
			MapLandRegion mapLandRegion = (MapLandRegion) session.load(MapLandRegion.class, mapCode);

			mapLandRegion.setCoordinates(coordinates);
			mapLandRegion.setLandregionCode(blockCode);
			session.update(mapLandRegion);
			tx.commit();
		} catch (Exception e) {

			log.error(e);
			if (tx != null) {
				tx.rollback();
			}
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	/*@Override
	public boolean modifyBlockInfo(BlockForm blockForm, BlockPK blockPk, int maplandRegionCode, Session session) {
		// TODO Auto-generated method stub

		// Transaction tx = null;

		try {

			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			Block block = new Block();
			List<BlockDataForm> listBlock = new ArrayList<BlockDataForm>();
			listBlock = blockForm.getListBlockDetails();
			Iterator<BlockDataForm> itr = listBlock.iterator();
			while (itr.hasNext()) {
				BlockDataForm element = (BlockDataForm) itr.next();
				session.flush();
				block = (Block) session.get(Block.class, blockPk);

				block.setBlockNameEnglish(element.getBlockNameEnglish().trim());
				block.setBlockNameLocal(element.getBlockNameLocal().trim());
				block.setAliasEnglish(element.getAliasEnglish().trim());
				block.setAliasLocal(element.getAliasLocal());
				block.setSsCode(element.getSsCode().trim());
				block.setIsactive(true);
				block.setMapCode(maplandRegionCode);
				block.setLrReplaces(1);
				block.setLrReplacedby(2);
				block.setBlockPK(blockPk);
				block.setCoordinates(blockForm.getContributedBlocks());
				session.update(block);
				// tx.commit();
			}
		} catch (Exception e) {

			log.error(e);
			// if(tx != null)
			// tx.rollback();
			return false;
		}

		
		 * finally { if(session != null && session.isOpen() ) session.close(); }
		 
		return true;
	}
*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Block> viewBlockDetails(String sql) {
		Session session = null;
		List<Block> lstSd = new ArrayList<Block>();
		try {
			session = sessionFactory.openSession();
			lstSd = session.createQuery(sql).list();
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return lstSd;
	}

	@Override
	public boolean updateIsActive(boolean isActive, BlockPK blockPK, Session session) {

		try {
			Block block = (Block) session.load(Block.class, blockPK);
			block.setIsactive(isActive);
			session.update(block);
			return true;
		} catch (HibernateException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public boolean updateLrReplaces(int lrReplaces, BlockPK blockPK, Session session) {

		try {
			Block block = (Block) session.load(Block.class, blockPK);
			block.setLrReplaces(lrReplaces);
			session.update(block);
			return true;
		} catch (HibernateException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public int getMaxMapCode(String query) {
		// TODO Auto-generated method stub
		int MaxCode = 0;
		Session session = null;
		Query sqlQuery = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			session = sessionFactory.openSession();
			sqlQuery = session.createSQLQuery(query);
			list = sqlQuery.list();
			MaxCode = Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxCode;
	}

	@Override
	public boolean saveBlockVillage(BlockVillage blockVillage, Session session) {
		try {
			session.save(blockVillage);
			session.flush();
			session.refresh(blockVillage);
			return true;
		} catch (HibernateException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	public Block getBlockDetail(BlockPK blockPK, Session session) {
		Block block = (Block) session.load(Block.class, blockPK);
		return block;
	}

	@Override
	public void ChangeBlock(int blockCode, String blockNameEnglish, int userId, String blockNameLocal, String aliasEnglish, String aliasLocal) {
		Session session = null;
		//Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("BlockChangeQuery").setParameter("blockCode", blockCode, Hibernate.INTEGER).setParameter("blockNameEnglish", blockNameEnglish, Hibernate.STRING).setParameter("userId", userId, Hibernate.INTEGER)
					.setParameter("blockNameLocal", blockNameLocal, Hibernate.STRING).setParameter("aliasEnglish", aliasEnglish, Hibernate.STRING).setParameter("aliasLocal", aliasLocal, Hibernate.STRING);
			query.list();

		} catch (Exception e) {
			log.error(e);
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	/*@Override
	public boolean getNewBlockVillageSet(int oldBlockCode, int oldBlockVersion, int newBlockCode, int newBlockVersion, Session session) {
		Query criteria = null;

		try {
			criteria = session.createSQLQuery("update block_village set block_code=" + newBlockCode + ", block_version=" + newBlockVersion + " where block_code=" + oldBlockCode + " and block_version=" + oldBlockVersion);
			criteria.setParameter("newBlockCode", newBlockCode, Hibernate.INTEGER);
			criteria.setParameter("newBlockVersion", newBlockVersion, Hibernate.INTEGER);
			criteria.setParameter("oldBlockCode", oldBlockCode, Hibernate.INTEGER);
			criteria.setParameter("oldBlockVersion", oldBlockVersion, Hibernate.INTEGER);
			criteria.executeUpdate();
			return true;
		} catch (HibernateException e) {
			log.error(e);
			return false;
		}

	}*/

	@Override
	public boolean saveBlockDistrict(BlockDistricts blockDistrict, Session session) {
		try {
			session.save(blockDistrict);
			session.flush();
			session.refresh(blockDistrict);
			return true;
		} catch (HibernateException e) {
			log.error(e);
			return false;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Village> getBlockVillagebyBlockCode(int blockCode) {
		Session session = null;
		//int blockVersion = 0;
		//blockVersion = this.getMaxBlockVersionbyCode(blockCode);
		List<Village> villList = new ArrayList<Village>();
		try {
			session = sessionFactory.openSession();

			villList = session.createSQLQuery("select v.* from village v inner join block_village bv on v.village_code=bv.vlc where bv.blc= " + blockCode + "and v.isactive=true").addEntity("v", Village.class).list();
			return villList;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion,int minorVersion) {
		Session session = null;
		//Transaction txn = null;
		Query criteria = null;
		List list = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("GovOrderDetailMinor").setParameter("entityType", entityType).setParameter("entityCode", entityCode).setParameter("entityVersion", entityVersion).setParameter("minorVersion", minorVersion);
			list = criteria.list();

		} catch (Exception e) {
			log.error(e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Headquarters> getHeadquarterModify(int blockCode, int blockversion) {
		Query criteria = null;
		Session session = null;
		List<Headquarters> hdList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Headquarters where lrlc=:blockCode and  isactive=true");
			criteria.setParameter("blockCode", blockCode, Hibernate.INTEGER);
			/*
			 * criteria.setParameter("blockversion", blockversion,
			 * Hibernate.INTEGER);
			 */
			hdList = criteria.list();
		} catch (HibernateException e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return hdList;

	}

	@SuppressWarnings("unchecked")
	public List<BlockHistory> getBlockHistoryDetail(char blockNameEnglish, int blockCode) {
		Session session = null;

		Query query = null;
		List<BlockHistory> blockList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getBlockHistoryDetails").setParameter("blockNameEnglish", blockNameEnglish).setParameter("blockCode", blockCode);
			blockList = query.list();
		} catch (HibernateException e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return blockList;
	}

	/*@Override
	public void SetGovermentOrderEntity(String entityCode, char entityType) {
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = sessionFactory.getCurrentSession().getNamedQuery("GovOrderEntityWiseQuery").setParameter("entityCode", entityCode, Hibernate.STRING).setParameter("entityType", entityType, Hibernate.CHARACTER);
			query.list();

		} catch (Exception e) {
			log.error(e);
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}*/

	/*
	 * @Override public boolean invalidateFunctionCall(String blockCodes, int
	 * blockCode, int roleCode, String villageCodes) { Session session = null;
	 * Query query = null; int rCode = 0; rCode = (int) roleCode; try { session
	 * = sessionFactory.openSession(); query =
	 * sessionFactory.getCurrentSession().getNamedQuery( "invalidateBlockFn");
	 * query.setParameter("districtCodes", blockCodes, Hibernate.STRING);
	 * query.setParameter("districtCode", blockCode, Hibernate.INTEGER);
	 * query.setParameter("roleCode", rCode, Hibernate.INTEGER);
	 * query.setParameter("subDistrictCodes", villageCodes, Hibernate.STRING);
	 * query.list(); return true;
	 * 
	 * } catch (Exception e) {
	 * log.error(e); return
	 * false; }
	 * 
	 * finally { if(session != null && session.isOpen()){ session.close(); } } }
	 */

	@Override
	public Block getSingleDistrictDetailsMaxVersion(String sQuery) {
		Session session = null;
		Block b = new Block();
		try {
			session = sessionFactory.openSession();
			b = (Block) session.createSQLQuery(sQuery).addEntity("Block", Block.class).list().get(0);
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	public List<StateWiseEntityDetails> getStateWiseBlockList(Integer statecode, char entityType, Integer blockCode, String blockName, Integer limit, Integer offset) throws Exception {
		Session session = null;

		Query query = null;
		List<StateWiseEntityDetails> villagelist = null;

		try {

			session = sessionFactory.openSession();
			query = session.getNamedQuery("getStateWiseEntityDetails").setParameter("statecode", statecode, Hibernate.INTEGER).setParameter("entitytype", entityType, Hibernate.CHARACTER).setParameter("villageCode", blockCode, Hibernate.INTEGER)
					.setParameter("villageName", blockName, Hibernate.STRING).setParameter("limit", limit, Hibernate.INTEGER).setParameter("offset", offset, Hibernate.INTEGER);
			villagelist = query.list();
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villagelist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StateWiseEntityDetails> getParentWiseList(char entitytype, Integer parentCode, Integer limit, Integer offset) throws Exception {
		Session session = null;

		Query query = null;
		List<StateWiseEntityDetails> villagelist = null;

		try {

			session = sessionFactory.openSession();
			query = session.getNamedQuery("getParentWiseEntityDetails").setParameter("entitytype", entitytype, Hibernate.CHARACTER).setParameter("parentCode", parentCode, Hibernate.INTEGER).setParameter("limit", limit, Hibernate.INTEGER)
					.setParameter("offset", offset, Hibernate.INTEGER);
			villagelist = query.list();
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villagelist;
	}

	@Override
	public int saveDataInMap(BlockForm blockForm, List<AttachedFilesForm> attachedList, HttpSession session, Session ses) {

		//Transaction tx1 = null;

		MapAttachment attachment = null;
		Query query = null;
		//boolean flag = true;
		//BlockPK vpk = null;
		int bid = 0;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new MapAttachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					attachment.setMapEntityCode(blockForm.getBlockCode());
					attachment.setMapEntityType('B');
					ses.save(attachment);
					ses.flush();
					int atid = (int) attachment.getAttachmentId();
					bid = blockForm.getBlockCode();

					query = ses.createSQLQuery("UPDATE block set map_attachment_code=" + atid + "where block_code=" + bid + "and isactive=true");
					query.executeUpdate();

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			//flag = false;
		}

		return bid;

	}

	@Override
	public String insertBlock(HttpServletRequest request, HttpSession httpSession, BlockForm blockForm) {
		Integer disCode = null;
		if (blockForm.getDistrictCode() != null && !blockForm.getDistrictCode().equals("")) {
			disCode = Integer.parseInt(blockForm.getDistrictCode());
		}
		Session hibsession = null;
		String block_name_english = blockForm.getBlockNameEnglish();
		String block_name_local = blockForm.getBlockNameLocal();
		String block_alias_english = blockForm.getAliasEnglish();
		String block_alias_local = blockForm.getAliasLocal();
		Integer p_census_2011_code = null;
		String resultFlag = null;
		int operationCode = 56;
		int flagCode = 49;

		String unmapVilList = null;
		String unmapUlbList = null;
		if (blockForm.getVillagesList() != null) {
			unmapVilList = blockForm.getVillagesList();
		}
		if (blockForm.getUlbLists() != null) {
			unmapUlbList = blockForm.getUlbLists();
		}
		

		String list_of_Block_ulb = null;
		String p_sscode = blockForm.getSsCode();
		StringBuffer sb =null;
		String lati = blockForm.getLati();
		String longi = blockForm.getLongi();
		if(lati!=null && longi!=null && !("").equals(lati) && !("").equals(longi)){
			String[] arrayLati = lati.split(",");
			String[] arrayLong = longi.split(",");
			sb= new StringBuffer();
			int count = 1;
			for (int i = 0; i < arrayLati.length; i++) {
				for (int j = 0; j < arrayLong.length; j++) {
					if (i == j) {
						if (count == 1) {
							String temp = arrayLati[i] + ":" + arrayLong[i];
							sb.append(temp);
							count++;
						} else {
							sb.append(",");
							String temp = arrayLati[i] + ":" + arrayLong[i];
							sb.append(temp);
						}
					}
				}
			}
		}
	

		String p_coordinates =sb!=null? sb.toString():null;// 321:321,2132:32132
		String p_headquarter_name_english = blockForm.getHeadquarterName();
		String headquarter_local_name = blockForm.getHeadquarterNameLocal();
		String p_order_no = blockForm.getOrderNo();

		Date effectiveDate = blockForm.getEffectiveDate();
		Date gazPubDate = blockForm.getGazPubDate();
		Date orderDate = blockForm.getOrderDate();
		java.sql.Timestamp effectivetimeStampDate = null;
		java.sql.Timestamp gazPubtimeStampDateTemp = null;
		java.sql.Timestamp ordertimeStampDate = null;
		if (effectiveDate != null) {
			effectivetimeStampDate = new Timestamp(effectiveDate.getTime());
		}
		if (gazPubDate != null) {
			gazPubtimeStampDateTemp = new Timestamp(gazPubDate.getTime());
		}
		if (orderDate != null) {
			ordertimeStampDate = new Timestamp(orderDate.getTime());
		}
		int fullCount = 1;
		int partCount = 1;
		String fullblock = "";
		String partblock = "";
		String contributedBlocks = blockForm.getContributedBlocks();
		if (contributedBlocks != null) {

			String[] subDistrictListArray = contributedBlocks.split(",");
			for (int i = 0; i < subDistrictListArray.length; i++) {
				String string = subDistrictListArray[i];
				if (string.contains("FULL")) {

					string = string.replaceAll("FULL", "");
					if (fullCount == 1) {
						fullblock = string;
						fullCount++;
					} else {
						fullblock = fullblock + "," + string;
					}
				}
				if (string.contains("PART")) {
					string = string.replaceAll("PART", "");
					if (partCount == 1) {
						partblock = string;
						partCount++;
					} else {
						partblock = partblock + "," + string;
					}
				}
			}
			if (fullblock.equals("")) {
				fullblock = null;
			}
			if (partblock.equals("")) {
				partblock = null;
			}
		} else {
			fullblock = null;
			partblock = null;
		}
		int villCount = 1;
		String list_of_villages_full_temp = null;
		String contriVillagesList = blockForm.getContributedVillages();
		if (contriVillagesList != null) {
			list_of_villages_full_temp = "";
			String[] contVillagesArray = contriVillagesList.split(",");
			for (int i = 0; i < contVillagesArray.length; i++) {
				String string = contVillagesArray[i];
				if (string.contains("FULL")) {
					string = string.replaceAll("FULL", "");
					if (villCount == 1) {
						list_of_villages_full_temp = string;
						villCount++;
					} else {
						list_of_villages_full_temp = list_of_villages_full_temp + "," + string;
					}
				}

			}
		}
		String block_ulb_temp = "";
		String contriBlockList = blockForm.getContributedblockUlb();
		if (contriBlockList != null) {
			String[] contulbsArray = contriBlockList.split(",");
			for (int i = 0; i < contulbsArray.length; i++) {
				String string = contulbsArray[i];
				if (string.contains("FULL")) {
					string = string.replaceAll("FULL", "");
					if (villCount == 1) {
						block_ulb_temp = string;
						villCount++;
					} else {
						block_ulb_temp = block_ulb_temp + "," + string;
					}
				}

			}
			if (block_ulb_temp.indexOf(',') == 0) {
				block_ulb_temp = block_ulb_temp.substring(block_ulb_temp.indexOf(',') + 1, block_ulb_temp.length());
			}
			list_of_Block_ulb = block_ulb_temp;
		}

		String list_of_block_full = fullblock;
		String list_of_block_part = partblock;
		String list_of_Block_villages = list_of_villages_full_temp;

		/*
		 * System.out.println("\n\nData\n\n");
		 * System.out.println("disCode="+disCode);
		 * System.out.println("userId "+userId);
		 * System.out.println("block_name_english "+block_name_english);
		 * System.out.println("p_order_no "+p_order_no);
		 * System.out.println("ordertimeStampDate "+ordertimeStampDate);
		 * System.out.println("effectivetimeStampDate "+effectivetimeStampDate);
		 * System.out.println("operationCode "+operationCode);
		 * System.out.println("flagCode "+flagCode);
		 * System.out.println("block_name_local "+block_name_local);
		 * System.out.println("block_alias_english "+block_alias_english);
		 * System.out.println("block_alias_local "+block_alias_local);
		 * System.out.println("p_census_2011_code "+p_census_2011_code);
		 * System.out.println("p_sscode "+p_sscode);
		 * System.out.println("p_coordinates"+p_coordinates);
		 * System.out.println(
		 * "p_headquarter_name_english "+p_headquarter_name_english);
		 * System.out.println("headquarter_local_name "+headquarter_local_name);
		 * System
		 * .out.println("gazPubtimeStampDateTemp "+gazPubtimeStampDateTemp);
		 * System.out.println("unmapVilList "+unmapVilList);
		 * System.out.println("unmapUlbList "+unmapUlbList);
		 * System.out.println("list_of_block_full "+list_of_block_full);
		 * System.out.println("list_of_block_part "+list_of_block_part);
		 * System.out.println("list_of_Block_villages "+list_of_Block_villages);
		 * System.out.println("list_of_Block_ulb "+list_of_Block_ulb);
		 * System.out.println("getMapCode "+BlockForm.getMapCode());
		 * System.out.println("\n\n");
		 */
		if (p_sscode.equals("")) {
			p_sscode = null;
		}
		if (block_name_local.equals("")) {
			block_name_local = null;
		}
		if (block_alias_english.equals("")) {
			block_alias_english = null;
		}
		if (p_headquarter_name_english.equals("")) {
			p_headquarter_name_english = null;
		}
		if (headquarter_local_name.equals("")) {
			headquarter_local_name = null;
		}
		if (list_of_block_full == null) {
			list_of_block_full = null;
		}

		try {
			hibsession = sessionFactory.openSession();
			Query query = hibsession.getNamedQuery("createBlockQuery").setParameter("p_district_code", disCode, Hibernate.INTEGER).setParameter("p_userid", blockForm.getUserId(), Hibernate.INTEGER)
					.setParameter("p_block_name_english", block_name_english, Hibernate.STRING).setParameter("p_order_no", p_order_no, Hibernate.STRING).setParameter("p_order_date", ordertimeStampDate, Hibernate.TIMESTAMP)
					.setParameter("p_effective_date", effectivetimeStampDate, Hibernate.TIMESTAMP).setParameter("p_operation_code", operationCode, Hibernate.INTEGER).setParameter("p_flag_code", flagCode, Hibernate.INTEGER)
					.setParameter("p_block_name_local", block_name_local, Hibernate.STRING).setParameter("p_alias_english", block_alias_english, Hibernate.STRING).setParameter("p_alias_local", block_alias_local, Hibernate.STRING)
					.setParameter("p_census_2011_code", p_census_2011_code, Hibernate.INTEGER).setParameter("p_sscode", p_sscode, Hibernate.STRING).setParameter("p_coordinates", p_coordinates, Hibernate.STRING)
					.setParameter("p_headquarter_name_english", p_headquarter_name_english, Hibernate.STRING).setParameter("headquarter_local_name", headquarter_local_name, Hibernate.STRING)
					.setParameter("p_gaz_pub_date", gazPubtimeStampDateTemp, Hibernate.TIMESTAMP).setParameter("list_of_villages", unmapVilList, Hibernate.STRING).setParameter("list_of_ulbs", unmapUlbList, Hibernate.STRING)
					.setParameter("list_of_block_full", list_of_block_full, Hibernate.STRING).setParameter("list_of_block_part", list_of_block_part, Hibernate.STRING)
					.setParameter("list_of_block_villages_full", list_of_Block_villages, Hibernate.STRING).setParameter("list_of_block_ulb_full", list_of_Block_ulb, Hibernate.STRING)
					.setParameter("p_map_attachment_code", blockForm.getMapCode(), Hibernate.INTEGER);
			CreateBlockBean blockBean = (CreateBlockBean) query.list().get(0);
			hibsession.flush();
			if (hibsession.contains(query)) {
				hibsession.evict(query);
			}
			return blockBean.getCreate_block_fn();
		} catch (Exception e) {
			log.error(e);
			resultFlag = null;

		} finally {
			if (hibsession != null && hibsession.isOpen()){
			hibsession.close();
			}

		}
		return resultFlag;
	}

	/*@Override
	public boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm, List<AttachedFilesForm> attachedList, int vilcode, HttpSession session, Session ses) {

		
		 * Transaction tx1 = null; Session sessionObj =
		 * sessionFactory.openSession(); tx1 = sessionObj.beginTransaction();
		 
		Attachment attachment = null;
		Query query = null;
		boolean flag = true;
		GovernmentOrderEntityWise goe = null;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					query = ses.createQuery("from GovernmentOrderEntityWise where entityCode=:VilCode and entityVersion=:ver and entityType=:type").setParameter("VilCode", vilcode, Hibernate.INTEGER).setParameter("ver", 1, Hibernate.INTEGER)
							.setParameter("type", 'B', Hibernate.CHARACTER);
					goe = (GovernmentOrderEntityWise) query.list().get(0);
					if (goe != null) {
						GovernmentOrder govorder = new GovernmentOrder();
						govorder.setOrderCode(goe.getOrderCode());
						attachment.setGovernmentOrder(govorder);
					}
					ses.save(attachment);
					// tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			// tx1.rollback();
			flag = false;
		}
		
		 * finally {
		 * 
		 * if (sessionObj != null && sessionObj.isOpen()) sessionObj.clear();
		 * sessionObj.close(); }
		 
		return flag;
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public String invalidateBlock(int stateCode, int userId, int operationCode, int flagCode, String bvList, BlockForm blockForm) throws Exception {
		Session session = null;
		Query query = null;

		String success = null;
		String ynFlag = null;
		String blkULb = blockForm.getUlbListFormat();
		if (blkULb.isEmpty())
			blkULb = null;
		else
			blkULb = blkULb.substring(0, blkULb.length() - 1);
		bvList = blockForm.getListformat();
		if (bvList.isEmpty())
			bvList = null;

		Integer targetBlock = 0;
		String sblock = blockForm.getBlockList();
		int sourceBlock = Integer.parseInt(sblock);
		String tblock = blockForm.getBlockYes();
		if (tblock != null) {
			targetBlock = Integer.parseInt(tblock);
		}
		List<InvalidateBlockBean> result = new ArrayList<InvalidateBlockBean>();
		if (bvList != null && !bvList.isEmpty()) {
			ynFlag = "N";
			targetBlock = null;
			bvList = bvList.substring(0, bvList.length() - 1);

		} else if (blkULb != null && !blkULb.isEmpty()) {
			ynFlag = "N";
			targetBlock = null;

		} else {
			ynFlag = "Y";

		}
		/*
		 * System.out.println("\n\n\n Details \n");
		 * System.out.println("stateCode = "+stateCode);
		 * System.out.println("userId = "+userId);
		 * System.out.println("operationCode = "+operationCode);
		 * System.out.println("flagCode = "+flagCode); System.out.println(
		 * "Order NO = "+blockForm.getOrderNo());
		 * System.out.println("Order Date = "+blockForm.getOrderDate());
		 * System.out.println("Effective date = "+blockForm.getEffectiveDate());
		 * System.out.println("Y N Flag = "+ynFlag);
		 * System.out.println("sourceBlock = "+sourceBlock);
		 * System.out.println("targetBlock = "+targetBlock);
		 * System.out.println("Gaz date = "+blockForm.getGazPubDate());
		 * System.out.println("block village List = "+bvList);
		 * System.out.println("block ulb List = "+blkULb);
		 */
		try {
			session = sessionFactory.openSession();
			query = sessionFactory.getCurrentSession().getNamedQuery("invalidateBlockFn");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("userId", userId, Hibernate.INTEGER);
			query.setParameter("operationCode", operationCode, Hibernate.INTEGER);
			query.setParameter("flagCode", flagCode, Hibernate.INTEGER);
			query.setParameter("orderNo", blockForm.getOrderNo(), Hibernate.STRING);
			query.setParameter("orderDate", blockForm.getOrderDate(), Hibernate.DATE);
			query.setParameter("effectiveDate", blockForm.getEffectiveDate(), Hibernate.DATE);
			query.setParameter("ynFlag", ynFlag, Hibernate.STRING);
			query.setParameter("sourceBlock", sourceBlock, Hibernate.INTEGER);
			query.setParameter("targetBlock", targetBlock, Hibernate.INTEGER);
			query.setParameter("gazpubDate", blockForm.getGazPubDate(), Hibernate.DATE);
			query.setParameter("blockVillages", bvList, Hibernate.STRING);
			query.setParameter("blockULB", blkULb, Hibernate.STRING);
			result = query.list();
			if (result.size() > 0) {
				success = result.get(0).getInvalidatePRILB();
			}
			return success;

		} catch (Exception e) {
			log.error(e);
			return success;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<Village> getUnmapVillages(int disCode) throws Exception {
		Session session = null;

		String distictCode = Integer.toString(disCode);
		List<Village> villList = new ArrayList<Village>();
		try {
			session = sessionFactory.openSession();

			villList = session.createSQLQuery("select * from village a where a.isactive=true and a.dlc=" + distictCode + " and not exists" + " ( select vlc from block_village b where a.vlc=b.vlc)")
			
			 * "select v.* from village v inner join block_village bv on v.village_code=bv.vlc where bv.blc="
			 * + blockCode + "and v.isactive=true")
			 
			.addEntity("v", Village.class).list();
			return villList;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getULBbyDistrictCode(int disCode) throws Exception {
		Session session = null;
		List<Localbody> lblist = null;
		lblist = new ArrayList<Localbody>();

		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("UnmapUlbListByDistrictCode");
			query.setParameter("districtCode", disCode, Hibernate.INTEGER);
			lblist = query.list();
			/*
			 * Iterator<LocalbodyByDistrict> itr = query.list().iterator();
			 * while (itr.hasNext()) { LocalbodyByDistrict element =
			 * (LocalbodyByDistrict) itr.next();
			 * 
			 * localbodybean = new Localbody();
			 * localbodybean.setLocalBodyNameEnglish(element
			 * .getLocalBodyNameEnglish());
			 * localbodybean.setLocalBodyCode(element.getLocalBodyCode());
			 * lblist.add(localbodybean); }
			 */
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lblist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getblockULb(String blc) throws Exception {
		Session session = null;

		// String blockCode = Integer.toString(blc);
		List<Localbody> lblist = null;
		lblist = new ArrayList<Localbody>();

		try {
			session = sessionFactory.openSession();

			lblist = session.createSQLQuery("select * from localbody a, block_ulb b where a.lblc=b.lblc and a.isactive=true and b.isactive=true and b.blc in (" + blc + ")").addEntity("u", Localbody.class).list();

			return lblist;

		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lblist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlockwiseVillageMappedUnmapped> getUnmapVillagesbyBlockCode(Integer blockCode, Character listType) throws Exception {
		
		Session session = null;
		List<BlockwiseVillageMappedUnmapped> blockwiseVillageMappedUnmappedList=null;
		
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getBlockwiseVillageMappedUnmapped");
			query.setParameter("blockCode", blockCode, Hibernate.INTEGER);
			query.setParameter("listType", listType, Hibernate.CHARACTER);
			blockwiseVillageMappedUnmappedList = query.list();
		} catch (Exception e) {
			log.error(e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return blockwiseVillageMappedUnmappedList;
	}

	@Override
	public boolean saveVillagesinBlock(String blkCode, String vilCode, String unmapVillageCode) throws Exception {
		int blockCode = Integer.parseInt(blkCode);
		int villageCode = 0;
		String[] villages = null;
		Session session = null;
		Transaction tx = null;
		int i = 0;
		try {
			List<BlockVillage> blockVillageList=this.getBlockVillageMapping(blockCode);
			
			/*session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (vilCode != null) {
				villages = vilCode.split(",");
				int len = villages.length;
				BlockVillage[] blvil = new BlockVillage[len];
				for (i = 0; i < len; i++) {
					blvil[i] = new BlockVillage();
					blvil[i].setBlc(blockCode);
					villageCode = Integer.parseInt(villages[i]);
					blvil[i].setVlc(villageCode);
					blvil[i].setIsactive(true);
					blvil[i].setBlc_version(1);
					blvil[i].setVlc_version(1);
					session.save(blvil[i]);
				}
			}
			if (unmapVillageCode != null) {
				Query query = session.createSQLQuery("delete from block_village bv where bv.vlc in(" + unmapVillageCode + ") and bv.blc= " + blkCode + " and isactive");
				query.executeUpdate();
			}*/
			tx.commit();
		} catch (Exception e) {
			log.error(e);
			tx.rollback();
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	
	
	
	/*@Override
	public boolean BlockExist(int discode, String blockName) {
		Query criteria = null;
		Session session = null;
		blockName = blockName.trim().toUpperCase();
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Block b where b.dlc=:dc and isactive=true and  UPPER(TRIM(b.blockNameEnglish)) =:blockName");
			criteria.setParameter("dc", discode, Hibernate.INTEGER);
			criteria.setParameter("blockName", blockName, Hibernate.STRING);
			List list = criteria.list();
			int size = list.size();
			if (size > 0) {
				return false;
			}
		} catch (HibernateException e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}*/

	@SuppressWarnings("unchecked")
	public List<BlockWiseVillagesAndUlb> getBlockWiseVillagesAndUlbDetailDAO(Integer blc) throws Exception {
		Session session = null;
		Query query = null;
		//String squery = null;
		List<BlockWiseVillagesAndUlb> blockwiseList = null;
		try {
			blockwiseList = new ArrayList<BlockWiseVillagesAndUlb>();
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getBlockWiseVillagesNUlb").setParameter("blc", blc, Hibernate.INTEGER);

			blockwiseList = query.list();

		} catch (Exception e) {
			log.error(e);
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return blockwiseList;
	}

	@SuppressWarnings("unchecked")
	public List<Block> getBlockListExtrend(Integer districtCode, Integer olc) throws Exception {
		Session session = null;
		String blc = "";
		Integer dlc = null;
		Query query = null;
		try {
			List<Block> blockList = new ArrayList<Block>();
			blc = organizationService.getExtendDetaildofEntity(olc, 'B', 'B');

			session = sessionFactory.openSession();

			dlc = commonService.getdlc(districtCode);

			if (blc != "") {
				query = session.createQuery("from Block b where b.isactive=true and b.dlc=" + dlc + " and blc not in(" + blc + ") order by b.blockNameEnglish");
			} else {
				query = session.createQuery("from Block b where b.isactive=true and b.dlc=" + dlc + "  order by b.blockNameEnglish");
			}

			blockList = query.list();
			return blockList;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public String ChangeBlock(int blkCode, String blKNameEnglish, String blkNameLocal, int userId, String blKNameEarlier, String aliasEnglish, String aliasLocal, Session session, String orderNo, Date orderDate, Date effectiveDate, Date govPubDate,
			Integer slc) throws Exception {
		String temp = null;

		try {

			List<ChangeBlock> result = new ArrayList<ChangeBlock>();
			Query query = session.getNamedQuery("BlockChangeQuery").setParameter("blkCode", blkCode, Hibernate.INTEGER).setParameter("userId", userId, Hibernate.INTEGER).setParameter("blkNameEnglishch", blKNameEnglish, Hibernate.STRING)
					.setParameter("orderNo", orderNo, Hibernate.STRING).setParameter("orderDate", orderDate, Hibernate.DATE).setParameter("effectiveDate", effectiveDate, Hibernate.DATE).setParameter("order_path", null, Hibernate.STRING)
					.setParameter("PeeviousBlkName", blKNameEarlier, Hibernate.STRING).setParameter("gazPubDate", govPubDate, Hibernate.DATE).setParameter("blkNameLocal", blkNameLocal, Hibernate.STRING)
					.setParameter("aliasEnglish", aliasEnglish, Hibernate.STRING).setParameter("aliasLocal", aliasLocal, Hibernate.STRING).setParameter("slc", slc, Hibernate.INTEGER);
			result = query.list();
			if (result.size() > 0) {
				temp = result.get(0).getChange_block_fn().toString();

			}

		} catch (Exception e) {
			log.error(e);
		}

		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getBlockListExtended(int districtCode, int orgCode, int locatedLevelCode) throws Exception {

		List<Block> blockList = new ArrayList<Block>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			blockList = ((SQLQuery) session
					.createSQLQuery(
							" select case when b.block_code =a.entity_code then cast('F' as character) else cast('A' as character) end as operation_state,"
									+ " b.block_code as blockCode, b.block_name_english as blockNameEnglish from block b left outer join" + " (select entity_code from org_coverage_detail where isactive and coverage_code in "
									+ " (select coverage_detail_code from org_coverage where org_coverage_entity_type=5 and isactive and org_located_level_code in "
									+ " (select org_located_level_code from org_located_at_levels  where olc=:orgCode and isactive and located_at_level=:locatedAtLevelCode)))as a" + " ON b.block_code =a.entity_code "
									+ " where dlc=:districtCode and isactive  order by b.block_name_english").setParameter("districtCode", districtCode, Hibernate.INTEGER).setParameter("orgCode", orgCode, Hibernate.INTEGER)
					.setParameter("locatedAtLevelCode", locatedLevelCode, Hibernate.INTEGER)).addScalar("blockCode").addScalar("blockNameEnglish").addScalar("operation_state").setResultTransformer(Transformers.aliasToBean(Block.class)).list();

			return blockList;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * For LocalBody Draft Mode Impact Add on 06-01-2015
	 * 
	 * @author Ripunj
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Village> getBlockVillagebyBlockCodeForLocalBody(int blockCode) {
		Session session = null;
		//int blockVersion = 0;
		//blockVersion = this.getMaxBlockVersionbyCode(blockCode);
		List<Village> villList = new ArrayList<Village>();
		try {
			session = sessionFactory.openSession();

			villList = session
					.createSQLQuery(
							"select case when v.village_code  in (select * from get_draft_used_lb_lr_temp(v.village_code,'V')) then  cast ('F' as character)"
									+ " else cast('A' as character) end as operation_state,v.village_code  as villageCode,v.village_name_english as villageNameEnglish from village v "
									+ " inner join block_village bv on v.village_code=bv.vlc where bv.blc= " + blockCode + "and v.isactive=true and bv.isactive ").addScalar("villageCode").addScalar("villageNameEnglish").addScalar("operation_state")
					.setResultTransformer(Transformers.aliasToBean(Village.class)).list();
			return villList;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * For LocalBody Draft Mode Impact Add on 06-01-2014
	 * 
	 * @author Ripunj
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getblockULbForLocalBody(String blc) throws Exception {
		Session session = null;

		// String blockCode = Integer.toString(blc);
		List<Localbody> lblist = null;
		lblist = new ArrayList<Localbody>();

		try {
			session = sessionFactory.openSession();

			lblist = session
					.createSQLQuery(
							"select case when a.local_body_code  in (select * from get_draft_used_lb_lr_temp(a.local_body_code,'G')) then  cast ('F' as character)"
									+ " else cast('A' as character) end as operation_state,a.local_body_code as localBodyCode,a.local_body_name_english as localBodyNameEnglish"
									+ " from localbody a, block_ulb b where a.lblc=b.lblc and a.isactive=true and b.isactive=true and b.blc in (" + blc + ")").addScalar("localBodyCode").addScalar("localBodyNameEnglish").addScalar("operation_state")
					.setResultTransformer(Transformers.aliasToBean(Localbody.class)).list();

			return lblist;

		} catch (Exception e) {
			log.error(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lblist;
	}

	/**
	 * For LocalBody Draft Mode Impact to Get Unmap Villages from Districts Add
	 * on 06-01-2014
	 * 
	 * @author Ripunj
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getUnmapVillagesForLocalBody(int disCode) throws Exception {
		Session session = null;

		String distictCode = Integer.toString(disCode);
		List<Village> villList = new ArrayList<Village>();
		try {
			session = sessionFactory.openSession();

			villList = session
					.createSQLQuery(
							"select case when a.village_code  in (select * from get_draft_used_lb_lr_temp(a.village_code,'V')) then "
									+ " cast ('F' as character) else cast('A' as character) end as operation_state,a.village_code  as villageCode,a.village_name_english as villageNameEnglish" + " from village a where a.isactive=true and a.dlc="
									+ distictCode + " and not exists" + " ( select vlc from block_village b where a.vlc=b.vlc and isactive)").addScalar("villageCode").addScalar("villageNameEnglish").addScalar("operation_state")
					.setResultTransformer(Transformers.aliasToBean(Village.class)).list();
			return villList;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * added by kirandeep on 07/01/2015 for localbody impact
	 * 
	 * @param districtCode
	 * @param sourceBlock
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getTargetBlockListforSVillBlockForLocalbody(int districtCode, int sourceBlock) {

		SQLQuery criteria = null;
		Session session = null;
		List<Block> blockList = new ArrayList<Block>();
		try {
			session = sessionFactory.openSession();

			criteria = session.createSQLQuery("select * from  ( select case when b.block_Code  in (select * from get_draft_used_lb_lr_temp(b.block_Code,'B')) then  cast ('F' as character)   else cast('A' as character) end"
					+ " as operation_state, b.block_Code as blockCode,b.block_Name_English as blockNameEnglish from    Block b where b.isactive=true and b.dlc=:districtCode "
					+ " union "
					+ " select case when b.block_Code  in (select * from get_draft_used_lb_lr_temp(b.block_Code,'B')) then  cast ('F' as character)  else cast('A' as character) end as operation_state,"
					+ " b.block_Code as blockCode,b.block_Name_English as blockNameEnglish   from  block_districts bd,block b where bd.dlc=:districtCode and bd.block_district_code=b.block_district_code "
					+ "and b.isactive and b.dlc=0)t where t.blockCode!= :sourceBlock order by blockNameEnglish");
			criteria.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			criteria.setParameter("sourceBlock", sourceBlock, Hibernate.INTEGER);
			criteria.addScalar("operation_state").addScalar("blockCode").addScalar("blockNameEnglish");
			criteria.setResultTransformer(Transformers.aliasToBean(Block.class));
			blockList = criteria.list();

			// query.setParameter("districtCode", districtCode,
			// Hibernate.INTEGER);
			// blockList = query.list();
			return blockList;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getBlockStateWise(int stateCode) {

		SQLQuery criteria = null;
		Session session = null;
		List<Block> blockList = new ArrayList<Block>();
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select case when b.block_Code  in (select * from get_draft_used_lb_lr_temp(b.block_Code,'B')) then  cast ('F' as character)"
					+ " else cast('A' as character) end as operation_state,b.block_code as blockCode, b.block_name_english as blockNameEnglish	from block b,district d,state s	WHERE  s.slc = d.slc AND"
					+ "	d.dlc = b.dlc AND s.isactive = TRUE AND  d.isactive = TRUE AND  b.isactive = TRUE AND s.state_code = :stateCode");
			criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			criteria.addScalar("operation_state").addScalar("blockCode").addScalar("blockNameEnglish");
			criteria.setResultTransformer(Transformers.aliasToBean(Block.class));
			blockList = criteria.list();

			return blockList;
		} catch (HibernateException e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	/**
	 * Get List<Block> base on Creteria for extend organigation Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 */
	@SuppressWarnings("unchecked")
	public List<Block> getBlockListbyCreteria(List<Integer> districtList, List<Integer> blockList, Integer districtCode) throws Exception {
		Session session = null;
		List<Block> BlockList = null;
		try {

			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select case when b.block_Code  in (select * from get_draft_used_lb_lr_temp(b.block_Code,'B')) then  cast ('F' as character) else "
					+ "cast('A' as character) end as operation_state,b.block_code as blockCode, (btrim(b.block_name_english)||'('||btrim(d.district_name_english)||')') as blockNameEnglish "
					+ "from block b left join district d on d.dlc=b.dlc where b.isactive and d.isactive ");
			if (blockList != null && !blockList.isEmpty() && districtCode != null) {
				queryBuild.append("	and b.dlc=:districtCode and  b.blc not in(:blockList) ");
			} else if (districtList != null && !districtList.isEmpty() && blockList != null && !blockList.isEmpty()) {
				queryBuild.append("	and b.dlc not in(:districtList) and  b.blc in(:blockList) ");
			}else if(blockList != null && !blockList.isEmpty() && districtCode == null){
				queryBuild.append("	and  b.blc in(:blockList) ");
			}
			queryBuild.append("	order by blockNameEnglish ");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			if (blockList != null && !blockList.isEmpty()) {
				query.setParameterList("blockList", blockList);
			}
			if (districtList != null && !districtList.isEmpty()) {
				query.setParameterList("districtList", districtList);
			}
			if (districtCode != null) {
				query.setParameter("districtCode", districtCode);
			}
			query.addScalar("blockCode");
			query.addScalar("blockNameEnglish");
			query.addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(Block.class));
			BlockList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return BlockList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BlockPanchayatSyncBlock onloadAttributesBPMapping(Integer stateCode, Integer districtCode) throws HibernateException {
		Session session = null;
		final BlockPanchayatSyncBlock onloadAttributes = new BlockPanchayatSyncBlock();
		try {
			session = sessionFactory.openSession();
			final Query query = session.getNamedQuery("Fetch_Block_Panchayats");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			final List<BlockPanchayatSyncBlock> blockPalchayats = query.list();
			session.flush();
			
			SQLQuery queryS = session.createSQLQuery(" select block_code as blockCode, block_name_english as blockNameEnglish from block, district "
										 + " where block.dlc = district.dlc and block.isactive and district.isactive and "
										 + " district.district_code=:districtCode");
			queryS.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			queryS.addScalar("blockCode").addScalar("blockNameEnglish");
			queryS.setResultTransformer(Transformers.aliasToBean(Block.class));
			final List<Block> blocks= queryS.list();
			session.flush();
			
			queryS = session.createSQLQuery("select lb.local_body_code, lb.block_code, b.block_name_english from synced_block_with_bp lb, block b where "
										   +"lb.block_code = b.block_code and b.isactive and lb.district_code =:districtCode");
			queryS.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			final List<Object[]> syncedBlocks = queryS.list();
			
			onloadAttributes.setListOfBlockPanchayats(blockPalchayats);
			onloadAttributes.setListOfBlocks(blocks);
			onloadAttributes.setListSyncedBlocks(syncedBlocks);
		} catch (HibernateException e) {
			log.error(e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return onloadAttributes;
	}

	@Override
	public String createNewBlockForSync(Integer districtCode, Integer bpCode) throws HibernateException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			final WebContext ctx = WebContextFactory.get();
			SessionObject sessionObject = (SessionObject) ctx.getHttpServletRequest().getSession().getAttribute("sessionObject");
			final Long userId = sessionObject.getUserId();
			session = sessionFactory.openSession();
			final SQLQuery query = session.createSQLQuery("select * from create_block_forsyncwithbp(:districtCode, :bpCode, :userId);");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.setParameter("bpCode", bpCode, Hibernate.INTEGER);
			query.setParameter("userId", new Integer (userId.intValue()), Hibernate.INTEGER);
			final String blockDetails = (String) query.uniqueResult();
			return blockDetails;
		} catch (HibernateException e) {
			log.error(e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public String syncProcessBlockWithBP(Integer districtCode, String paramSyncCodes) throws HibernateException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			final WebContext ctx = WebContextFactory.get();
			SessionObject sessionObject = (SessionObject) ctx.getHttpServletRequest().getSession().getAttribute("sessionObject");
			final Long userId = sessionObject.getUserId();
			session = sessionFactory.openSession();
			final SQLQuery query = session.createSQLQuery("select * from sync_block_panchayat_with_block(:districtCode, :paramSyncCodes, :userId);");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.setParameter("paramSyncCodes", paramSyncCodes, Hibernate.STRING);
			query.setParameter("userId", new Integer (userId.intValue()), Hibernate.INTEGER);
			final String syncOutput = (String) query.uniqueResult();
			return syncOutput;
		} catch (HibernateException e) {
			log.error(e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getBlockListbyDistrict(Integer districtCode) throws HibernateException {
		Session session=null;
		List<Block> blockList=null;
		SQLQuery sqlQuery = null;
		try{
		session=sessionFactory.openSession();
		sqlQuery=session.createSQLQuery("select block_code as blockCode , block_name_english as blockNameEnglish from block where dlc =:districtCode and isactive "
				+ " union "
				+ " select b.block_code as blockCode , b.block_name_english as blockNameEnglish from block b,block_districts bd "
				+ " where bd.dlc=:districtCode and b.block_district_code=bd.block_district_code and  b.dlc=:zeroParameter and b.isactive "
				+ " order by blockNameEnglish ");
		sqlQuery.setParameter("zeroParameter", 0);
		sqlQuery.setParameter("districtCode", districtCode);
		sqlQuery.addScalar("blockCode").addScalar("blockNameEnglish");
		sqlQuery.setResultTransformer(Transformers.aliasToBean(Block.class));
		blockList = sqlQuery.list();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return blockList;
	}
	
	
	public List<BlockVillage> getBlockVillageMapping(Integer blockCode)throws Exception{
		Session session=null;
		List<BlockVillage> blockVillageList=null;
		try{
		session=sessionFactory.openSession();
		Query query=session.createQuery("from BlockVillage where blc= :blockCode");
		query.setParameter("blockCode", blockCode);
		blockVillageList=query.list();
		} catch (HibernateException e) {
			log.error(e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return blockVillageList;
	}
	
	
	@Override
	public boolean saveBlockVillageMapping(BlockVillage blockVillage) throws Exception {
		Session session = null;
		boolean SuccessFlag = false;
		Transaction transaction = null;
		try {

			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String blkDet[] = blockVillage.getBlockCode().split("-");
			Integer blc = Integer.parseInt(blkDet[0]);
			Integer blc_version = Integer.parseInt(blkDet[1]);
			Character coverageType;

			Integer vlc = null;
			Integer vlc_version = null;

			BlockVillage blkVillage = null;

			Scanner scanner = null;

			String addBlkVillMapping = blockVillage.getVillageMappedListNew();
			
			if (addBlkVillMapping != null && addBlkVillMapping.length() > 0) {
				if (addBlkVillMapping.indexOf(",") > -1) {
					addBlkVillMapping=removeDuplicate(addBlkVillMapping);
					
					scanner = new Scanner(addBlkVillMapping);
					scanner.useDelimiter(",");

					while (scanner.hasNext()) {
						String villDet[] = scanner.next().split("-");
						vlc = Integer.parseInt(villDet[0]);
						vlc_version = Integer.parseInt(villDet[1]);
						blkVillage = new BlockVillage();
						blkVillage.setBlc(blc);
						blkVillage.setBlc_version(blc_version);
						blkVillage.setVlc(vlc);
						blkVillage.setVlc_version(vlc_version);
						blkVillage.setIsactive(Boolean.TRUE);
						blkVillage.setMappedOn(new Date());
						blkVillage.setMappedBy(blockVillage.getUserId());
						blkVillage.setUnmappedOn(null);
						blkVillage.setUnmappedBy(0);
						blkVillage.setCoverageType('F');
						session.save(blkVillage);
						session.flush();
					}

					scanner.close();
				} else {
					String villDet[] = addBlkVillMapping.split("-");
					//String[] s =blockVillage.getLbCoverageVillageList().split("-");
					//String lblc=s[0];
					vlc = Integer.parseInt(villDet[0]);
					String villDet1[] = villDet[1].split("_");
					vlc_version = Integer.parseInt(villDet1[0]);
					//coverageType =villDet1[1].charAt(0);
					blkVillage = new BlockVillage();
					blkVillage.setBlc(blc);
					blkVillage.setBlc_version(blc_version);
					blkVillage.setVlc(vlc);
					blkVillage.setVlc_version(vlc_version);
					blkVillage.setIsactive(Boolean.TRUE);
					blkVillage.setMappedOn(new Date());
					blkVillage.setMappedBy(blockVillage.getUserId());
					blkVillage.setUnmappedOn(null);
					blkVillage.setUnmappedBy(0);
					blkVillage.setCoverageType('F');
					//blkVillage.setLblc(Integer.valueOf(lblc));
					//blkVillage.setLbMappedBy(blockVillage.getUserId());
					//blkVillage.setLbMappedOn(new Date());
					//blkVillage.setLbUnmappedOn(null);
					//blkVillage.setLbUnmappedBy(0);
					session.save(blkVillage);
					session.flush();
				}

			}

			List<Integer> delMapVillage = new ArrayList<Integer>();
			String delBlkVillMapping = blockVillage.getVillageMappedListDel();
			if (delBlkVillMapping != null && delBlkVillMapping.length() > 0) {

				delMapVillage.add(-1);
				if (delBlkVillMapping.indexOf(",") > -1) {
					scanner = new Scanner(delBlkVillMapping);
					scanner.useDelimiter(",");
					while (scanner.hasNext()) {
						delMapVillage.add(Integer.parseInt(scanner.next().split("-")[0]));
					}
					scanner.close();

				} else {
					delMapVillage.add(Integer.parseInt(delBlkVillMapping.split("-")[0]));
				}
				
				Query query = session.createQuery(
						"FROM BlockVillage bv WHERE bv.vlc IN (:delMapVillage) and bv.isactive=:isactive and bv.blc=:blc");
				query.setParameterList("delMapVillage", delMapVillage);
				query.setParameter("blc", blc);
				query.setParameter("isactive", Boolean.TRUE);
				List<BlockVillage> delBlkVillagMap = query.list();
				for (BlockVillage delObj : delBlkVillagMap) {
					
					
					delObj.setIsactive(Boolean.FALSE);
					delObj.setUnmappedOn(new Date());
					delObj.setUnmappedBy(blockVillage.getUserId());
					session.update(delObj);
					session.flush();
				}

			}
			List<Integer> lbCoverageMappList = new ArrayList<Integer>();
			String lbCoverageMapp = blockVillage.getLbCoverageVillageList();
			int i=0;
			if (lbCoverageMapp != null && lbCoverageMapp.length() > 0) {

				//delMapVillage.add(-1);
				if (lbCoverageMapp.indexOf(",") > -1) {
					scanner = new Scanner(lbCoverageMapp);
					scanner.useDelimiter(",");
					while (scanner.hasNext()) {
						lbCoverageMappList.add(Integer.parseInt(scanner.next().split("-")[1]));
					}
					scanner.close();

				} else {
					
					lbCoverageMappList.add(Integer.parseInt(lbCoverageMapp.split("-")[1]));
				}
				
				Query query = session.createQuery(
						"FROM BlockVillage bv WHERE bv.vlc IN (:lbCoverageMappList) and bv.isactive=:isactive and bv.blc=:blc");
				query.setParameterList("lbCoverageMappList", lbCoverageMappList);
				query.setParameter("blc", blc);
				query.setParameter("isactive", Boolean.TRUE);
				List<BlockVillage> updateLbDetails = query.list();
				for (BlockVillage updtObj : updateLbDetails) {
					
					String s =blockVillage.getLbCoverageVillageList().split("-")[i];
					String lblc=s;
					i++;
					updtObj.setLblc(Integer.valueOf(lblc));
					updtObj.setLbMappedBy(blockVillage.getUserId());
					updtObj.setLbMappedOn(new Date());
						session.update(updtObj);
					session.flush();
				}

			}
			List<Integer> lbCoverageUnmappList = new ArrayList<Integer>();
			String lbCoverageUnmapp = blockVillage.getLbCoverageVillageDel();
			if (lbCoverageUnmapp != null && lbCoverageUnmapp.length() > 0) {

				//delMapVillage.add(-1);
				if (lbCoverageUnmapp.indexOf(",") > -1) {
					scanner = new Scanner(delBlkVillMapping);
					scanner.useDelimiter(",");
					while (scanner.hasNext()) {
						lbCoverageUnmappList.add(Integer.parseInt(scanner.next().split("-")[1]));
					}
					scanner.close();

				} else {
					lbCoverageUnmappList.add(Integer.parseInt(lbCoverageUnmapp.split("-")[1]));
				}
				
				Query query = session.createQuery(
						"FROM BlockVillage bv WHERE bv.vlc IN (:lbCoverageUnmappList) and bv.isactive=:isactive and bv.blc=:blc");
				query.setParameterList("lbCoverageUnmappList", lbCoverageUnmappList);
				query.setParameter("blc", blc);
				query.setParameter("isactive", Boolean.TRUE);
				List<BlockVillage> lbCoverageUnmappListMap = query.list();
				for (BlockVillage delObjMap : lbCoverageUnmappListMap) {
					
					
					/*delObjMap.setLblc(Integer.valueOf(blockVillage.getLbCoverageVillageList()));*/
					delObjMap.setLbMappedBy(blockVillage.getUserId());
					delObjMap.setLbMappedOn(new Date());
					session.update(delObjMap);
					session.flush();
				}

			}

			

			transaction.commit();

			SuccessFlag = true;

		} catch (Exception e) {
			log.error(e);
			
			transaction.rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return SuccessFlag;
	}
	
	private String removeDuplicate(String newBlockcodes)throws Exception{
		StringBuilder FinalList=new StringBuilder();
		Scanner scanner = new Scanner(newBlockcodes);
		scanner.useDelimiter(",");
		Set<Integer> hs = new HashSet<>();
		List<Integer> al = new ArrayList<>();
		Integer vlc=null;
		Map<Integer,Integer> m= new HashMap<Integer,Integer>();
		while (scanner.hasNext()) {
			String villDet[] = scanner.next().split("-");
			vlc=Integer.parseInt(villDet[0]);
			al.add(vlc);
			if(!m.containsKey(vlc)){
				m.put(vlc, Integer.parseInt(villDet[1]));
			}else{
				if( m.get(vlc)<Integer.parseInt(villDet[1])){
					m.remove(vlc);
					m.put(vlc, Integer.parseInt(villDet[1]));
				}
			}
			
		}
		scanner.close();
		hs.addAll(al);
		al.clear();
		al.addAll(hs);
		int i=1;
		for(Integer a:al){
			FinalList.append(a+"-"+m.get(a));
			if(al.size()>i){
				FinalList.append(",");
			}
			i++;
		}
		
		return FinalList.toString();
		
	}
	
	@Override
	public boolean validateBlockVillageMapping(Integer blockCode,Integer blockVersion,List<Integer> villageList)throws Exception{
		boolean isInvalidate=true;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select case when count(1)>0 then false else true end as flag from "
			+ "block_village  where   blc =:blockCode  and vlc in (:villageList) and isactive and coverage_type ='F'");
			query.setParameter("blockCode", blockCode);
			//query.setParameter("blockVersion", blockVersion);
			query.setParameterList("villageList", villageList);
			isInvalidate = (boolean) query.uniqueResult();
			

		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return isInvalidate;
	}
	
	//shivam
		public Character newblockNameIsUnique(String newblck,Integer districtCode,Integer statecode) throws Exception {
			Character uniqueFlag='F';
			
			// F=not found , D=district Found, S=state found
			Session session=null;
			session = sessionFactory.openSession();
			try{
				StringBuilder sb=new StringBuilder();
				sb.append(" select bl.block_name_english,bl.dlc,dt.slc from block bl inner join district dt on dt.dlc=bl.dlc "
						+ "where UPPER(bl.block_name_english) ilike UPPER(:block_name_english) ");
						 /*"and bl.dlc=:dlc"*/ 
				Query query=session.createSQLQuery(sb.toString());
				 query.setParameter("block_name_english", '%'+newblck.trim()+'%');
				// query.setParameter("dlc", districtCode);
				
				List alist=query.list();
				if(alist!=null && !alist.isEmpty()) 
				{
					newblck=matchcase(newblck);
					for(Iterator itr=alist.iterator(); itr.hasNext();)
					{
						Object []  obj= (Object []) itr.next();
						if(obj!=null )
						{
							String str=matchcase(obj[0].toString());
							if(newblck!="" && str!="" && str.equalsIgnoreCase(newblck) && obj[2].toString().equals(statecode.toString()) )
							{
								uniqueFlag='S';
							}
							if(newblck!="" && str!="" && str.equalsIgnoreCase(newblck) &&  obj[1].toString().equals(districtCode.toString()))
							{
								return uniqueFlag='D' ;
							}
						}
					} 
			 }
			}catch(Exception e){
				throw e;
			}finally{
				if
				(session!=null && session.isOpen()){
					session.close();
				}
			}
			return uniqueFlag;
		}

		public String matchcase(String str) {
			String string="";
			if(str!=null) {
				for(int i=0; i<str.length(); i++)
				{
					if(str.charAt(i)!= ' ')
					{
						string=string+str.charAt(i);
					}
				}
			}
			return string;
		}
		
		public Boolean getConfigurationBlockVillageMapping(Character isUserType) throws Exception{
			
			boolean isInvalidate = false;
			Session session = null;
			try {
				session = sessionFactory.openSession();
				Query query = session.createSQLQuery("select is_partially_covered from configuration_block_village  where   user_type =:isUserType");
				query.setParameter("isUserType", isUserType);
				//query.setParameter("blockVersion", blockVersion);
				
				isInvalidate = (Boolean) query.uniqueResult();
				

			} 
			catch (Exception e) {
				log.error(e);
			}
			finally {
				if(session!=null && session.isOpen())
					session.close();
			}
			return isInvalidate;
		}
		
		public List<LocalBodyDto> getVillageCoverageDetailBlock(Integer blockCode, String villageCode){
			Session session = null;
			Query query = null;
			//String squery = null;
			List<LocalBodyDto>  LocalBodyDto = null;
			try {
				session = sessionFactory.openSession();
				query = session.getNamedQuery("getLocalBodyDetail").setParameter("blockCode", blockCode, Hibernate.INTEGER).setParameter("villageCode", villageCode);

				LocalBodyDto = query.list();
				if(LocalBodyDto != null) {
				System.out.println("Size Equal"+LocalBodyDto.size());
				}else {
					System.out.println("fosdfhweferhfwefrhwefruiowefth");
				}
			} catch (Exception e) {
				log.error(e);
				if (session != null && session.isOpen()) {
					session.close();
				}
			}

			finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		
		return LocalBodyDto;
		}


		@Override
		public boolean saveBlockVillageMappingLb(BlockVillage blockVillage ,String listOfVlc,String unMappListVlc) throws Exception {
			// TODO Auto-generated method stub
			boolean isInvalidate=false;
			Session session = null;
			try {
				String lblcVlist =blockVillage.getLbCoverageVillageList();
				if(lblcVlist ==null ) {
					lblcVlist="0";
				}
				if("".equals(unMappListVlc)) {
					unMappListVlc="0";
				}
				if(listOfVlc==null || "".equals(listOfVlc)) {
					listOfVlc ="0";
				}
				session = sessionFactory.openSession();
				Query query = session.createSQLQuery("select * from save_specific_lb_to_block_in_blockvillage(:blc,:listOfVlc,:listOfLblc,:userId,:unMappListVlc)");
				query.setParameter("blc", blockVillage.getBlc());
				query.setParameter("listOfVlc", listOfVlc);
				query.setParameter("listOfLblc",lblcVlist);
				query.setParameter("userId", blockVillage.getUserId());
				query.setParameter("unMappListVlc",unMappListVlc);
				
				isInvalidate = (boolean) query.uniqueResult();
				

			}catch(Exception e) {
				e.printStackTrace();
			}finally {
			
				if(session!=null && session.isOpen())
					session.close();
			}
			return isInvalidate;
			
			
			
		}
		
		public List<LocalBodyDto> getLbCoveredSelected(Integer blockCode, String villageCode){
			Session session = null;
			Query query = null;
			//String squery = null;
			List<LocalBodyDto>  LocalBodyDto = null;
			try {
				session = sessionFactory.openSession();
				query = session.getNamedQuery("getLbCoveredSelectedValue").setParameter("blockCode", blockCode, Hibernate.INTEGER).setParameter("villageCode", villageCode);

				LocalBodyDto = query.list();
				if(LocalBodyDto != null) {
				System.out.println("Size Equal"+LocalBodyDto.size());
				}else {
					System.out.println("fosdfhweferhfwefrhwefruiowefth");
				}
			} catch (Exception e) {
				log.error(e);
				if (session != null && session.isOpen()) {
					session.close();
				}
			}

			finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		
		return LocalBodyDto;
		}
		
		
		@Override
		public Response saveEffectiveDateEntityBlock(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId,Integer stateCode) {
			Response response=new Response();
			Session session = null;
			String parameter1=null;
			Integer villageCode=null;
			try {
				session = sessionFactory.openSession();
				
				
				if(getEntityEffectiveDateList!=null && !getEntityEffectiveDateList.isEmpty()) {
					 
					villageCode=getEntityEffectiveDateList.get(0).getEntityCode();
					Character entityType=getEntityEffectiveDateList.get(0).getEntityType();
					Query query = session.getNamedQuery("GET_ENTITY_EFFECTIVE_DATE");
					query.setParameter("entityCode", villageCode);
					query.setParameter("entityType", "B");
					List<GetEntityEffectiveDate> effectiveDateListOld= query.list();
					
					if(villageService.validateNewEffectiveDate(villageCode, getEntityEffectiveDateList, effectiveDateListOld))	{
						 List<String> pArr=new ArrayList<String>();
							for(GetEntityEffectiveDate obj: getEntityEffectiveDateList) {
								if(obj.getEffectiveDate()!=obj.getNewEffectiveDate()) {
									pArr.add(obj.getEntityCode()+"#"+obj.getEntityVersion()+"#"+obj.getEntityMinorVersion()+"#"+obj.getNewEffectiveDate());
									
								}
							}
							parameter1=pArr.toString();
							if(parameter1.indexOf("[")>-1) {
								parameter1 = parameter1.replaceAll("[\\[\\](){}]","");
							}
							
							
							query = session.createSQLQuery("select * from change_block_effective_date_fn(:parameter1,:userId,:stateCode)");
							
							//query = session.createSQLQuery("select * from get_entity_effective_date_details(:parameter1,:userId)");
							query.setParameter("parameter1", parameter1.toString(), Hibernate.STRING);
							query.setParameter("userId", userId, Hibernate.INTEGER);
							query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
						    query.uniqueResult();
							session.flush();
							session.clear();
							response.setResponseCode(200);
					 }else {
						 response.setResponseCode(300);
						 response.setReponseObject("Effective Date not in Sequence");
					 }
				
				
			}else {
				response.setResponseCode(300);
			}
				
				
		
		}catch(Exception e){
			response.setResponseCode(400);
			response.setReponseObject(e);
		}finally {
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}
			return response;
		}
		
}

