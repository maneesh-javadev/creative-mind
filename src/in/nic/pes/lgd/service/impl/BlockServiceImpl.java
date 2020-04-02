package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.BlockDistricts;
import in.nic.pes.lgd.bean.BlockHistory;
import in.nic.pes.lgd.bean.BlockPK;
import in.nic.pes.lgd.bean.BlockPanchayatSyncBlock;
import in.nic.pes.lgd.bean.BlockVillage;
import in.nic.pes.lgd.bean.BlockWiseVillagesAndUlb;
import in.nic.pes.lgd.bean.BlockwiseVillageMappedUnmapped;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.LandregionReplacedby;
import in.nic.pes.lgd.bean.LandregionReplaces;
import in.nic.pes.lgd.bean.LocalBodyDto;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.common.ReleaseResources;
import in.nic.pes.lgd.dao.BlockDAO;
import in.nic.pes.lgd.dao.GovernmentOrderDAO;
import in.nic.pes.lgd.dao.HeadquartersDAO;
import in.nic.pes.lgd.dao.LandRegionReplacedByDAO;
import in.nic.pes.lgd.dao.LandRegionReplacesDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.MapLandRegionDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.forms.BlockDataForm;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.DistrictDataForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.service.BlockService;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.ShiftService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.CurrentDateTime;
import in.nic.pes.lgd.utils.DatePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exception.BaseAppException;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

public class BlockServiceImpl implements BlockService {
	private static final Logger log = Logger.getLogger(BlockServiceImpl.class);

	Session session = null;
	Transaction tx = null;

	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	BlockDAO blockDAO;

	@Autowired
	StateService stateService;

	@Autowired
	VillageService villageService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	private MapLandRegionDAO mapLandRegionDAO;

	@Autowired
	private HeadquartersDAO headquartersDAO;

	@Autowired
	private LandRegionReplacedByDAO landRegionReplacedByDAO;

	@Autowired
	private LandRegionReplacesDAO landRegionReplacesDAO;

	@Autowired
	private VillageDAO villageDAO;

	@Autowired
	ShiftService shiftService;

	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	EmailService emailService;

	@Autowired
	private SubDistrictDAO subdistrictDAO;

	@Autowired
	GovernmentOrderDAO govtOrderDAO;

	@Autowired
	CommonService commonService;

	@Autowired
	CurrentDateTime dateTimeUtil;
	
	@Autowired
	private DistrictService districtService;

	Date timestamp = DatePicker.getDate("2011-10-20");
	int mapCd;
	int userId = 1000;
	long createdBy = 1000;
	Map<String, String> bVillMap = new HashMap<String, String>();

	@Override
	public boolean publishBlock(BlockForm blockForm, HttpSession httpSession) throws Exception {
		int blockCode = blockDAO.getMaxBlockCode() + 1;
		int blockVersion = 1;
		String[] selectedBlocks = null;

		Session session = null;
		Transaction tx = null;

		List<Block> fullContributedList = null;
		List<Block> partContributedList = null;
		List<Village> vFullList = null;
		List<Village> vPartList = null;

		fullContributedList = new ArrayList<Block>();
		partContributedList = new ArrayList<Block>();
		vFullList = new ArrayList<Village>();
		vPartList = new ArrayList<Village>();

		int lrReplacedBy = landRegionReplacedByDAO
				.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
		int lrReplaces = landRegionReplacesDAO
				.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
		int rByid = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
		int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
		int bdid = landRegionReplacesDAO
				.getMaxRecords("select COALESCE(max(block_district_code),1) from block_districts");
		if (blockForm.getContributedBlocks() != null) {
			selectedBlocks = blockForm.getContributedBlocks().split(",");
			for (int i = 0; i < selectedBlocks.length; i++) {
				int sCodeFull = Integer.parseInt(selectedBlocks[i].substring(0, selectedBlocks[i].length() - 4));

				List<Block> lstTemp = null;
				lstTemp = new ArrayList<Block>();

				lstTemp = blockDAO.viewBlockDetails("from Block b where blockCode=" + sCodeFull + " and isactive=true");

				if (selectedBlocks[i].contains("FULL")) {
					fullContributedList.add(lstTemp.get(0));
				} else if (selectedBlocks[i].contains("PART")) {
					partContributedList.add(lstTemp.get(0));
				}
			}
		}
		if (blockForm.getContributedVillages() != null && blockForm.getContributedVillages().length() > 0) {
			String[] selectedVillages = blockForm.getContributedVillages().split(",");
			for (int v = 0; v < selectedVillages.length; v++) {
				int vCode = Integer.parseInt(selectedVillages[v].substring(0, selectedVillages[v].length() - 4));
				List<Village> lstTemp = null;
				lstTemp = new ArrayList<Village>();

				lstTemp = villageDAO
						.getListVillageDetails("from Village where village_code=" + vCode + " and isactive=true");
				if (selectedVillages[v].contains("FULL")) {
					vFullList.add(lstTemp.get(0));
				} else if (selectedVillages[v].contains("PART")) {
					vPartList.add(lstTemp.get(0));
				}
			}
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			/*
			 * mapCd = this.saveMapLandRegion(blockForm, blockCode,
			 * blockVersion, session);
			 */
			this.saveBlock(blockForm, blockCode, blockVersion, bdid, mapCd, session, httpSession);
			/*
			 * this.saveHeadquarters(blockForm, headquarterCode, blockCode,
			 * blockVersion, session);
			 */
			tx.commit();

			if (fullContributedList.size() > 0 && partContributedList.size() == 0) {
				// Land Region Replaced By
				this.saveReplacedBy(rByid, lrReplacedBy, blockCode, blockVersion, session);
				for (int k = 0; k < fullContributedList.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, fullContributedList.get(k).getBlockCode(),
							fullContributedList.get(k).getBlockVersion(), session);
					BlockPK blockPK = new BlockPK(fullContributedList.get(k).getBlockCode(),
							fullContributedList.get(k).getBlockVersion());
					blockDAO.updateIsActive(false, blockPK, session);
					// Village impacting code
					this.saveBlockVillage(fullContributedList.get(k).getBlockCode(),
							fullContributedList.get(k).getBlockVersion(), blockCode, blockVersion, session);
					id++;
				}
				BlockPK blockPK = new BlockPK(blockCode, blockVersion);
				blockDAO.updateLrReplaces(lrReplaces, blockPK, session);// corresponding
																		// detail
																		// in
																		// sub
																		// district
																		// table
				tx.commit();
			}

			if (fullContributedList.size() == 0 && partContributedList.size() > 0) {

				this.saveReplacedBy(rByid, lrReplacedBy, blockCode, blockVersion, session);
				for (int k = 0; k < partContributedList.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, partContributedList.get(k).getBlockCode(),
							partContributedList.get(k).getBlockVersion(), session);
					BlockPK blockPK = new BlockPK(partContributedList.get(k).getBlockCode(),
							partContributedList.get(k).getBlockVersion());

					// code to get new block version
					this.saveNewBlockVersion(partContributedList.get(k).getBlockCode(),
							partContributedList.get(k).getBlockVersion(), lrReplacedBy, session);
					// --------------------

					// Village impacting code
					for (int i = 0; i < vFullList.size(); i++) {
						this.saveBlockVillageFull(vFullList.get(i).getVlc(), blockCode, blockVersion, session);
					}
					for (int i = 0; i < vPartList.size(); i++) {
						this.saveBlockVillagePart(vPartList.get(i).getVlc(), blockCode, blockVersion, session);
					}
					// --------------------

					blockDAO.updateIsActive(false, blockPK, session);
					id++;
				}
				BlockPK blockPK = new BlockPK(blockCode, blockVersion);
				blockDAO.updateLrReplaces(lrReplaces, blockPK, session);// corresponding
																		// detail
																		// in
																		// sub
																		// district
																		// table

				tx.commit();
			} else if (fullContributedList.size() > 0 && partContributedList.size() > 0) {
				this.saveReplacedBy(rByid, lrReplacedBy, blockCode, blockVersion, session);
				for (int k = 0; k < fullContributedList.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, fullContributedList.get(k).getBlockCode(),
							fullContributedList.get(k).getBlockVersion(), session);
					BlockPK blockPK = new BlockPK(fullContributedList.get(k).getBlockCode(),
							fullContributedList.get(k).getBlockVersion());
					blockDAO.updateIsActive(false, blockPK, session);
					// Village impacting code
					this.saveBlockVillage(fullContributedList.get(k).getBlockCode(),
							fullContributedList.get(k).getBlockVersion(), blockCode, blockVersion, session);
					id++;
				}

				for (int k = 0; k < partContributedList.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, partContributedList.get(k).getBlockCode(),
							partContributedList.get(k).getBlockVersion(), session);
					BlockPK blockPK = new BlockPK(partContributedList.get(k).getBlockCode(),
							partContributedList.get(k).getBlockVersion());

					// code to get new block version
					this.saveNewBlockVersion(partContributedList.get(k).getBlockCode(),
							partContributedList.get(k).getBlockVersion(), lrReplacedBy, session);
					// --------------------

					// Village impacting code
					for (int i = 0; i < vFullList.size(); i++) {
						this.saveBlockVillageFull(vFullList.get(i).getVlc(), blockCode, blockVersion, session);
					}
					for (int i = 0; i < vPartList.size(); i++) {
						this.saveBlockVillagePart(vPartList.get(i).getVlc(), blockCode, blockVersion, session);
					}
					// --------------------

					blockDAO.updateIsActive(false, blockPK, session);
					id++;
				}
				BlockPK blockPK = new BlockPK(blockCode, blockVersion);
				blockDAO.updateLrReplaces(lrReplaces, blockPK, session);// corresponding
																		// detail
																		// in
																		// sub
																		// district
																		// table
				tx.commit();

			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
			return false;
		}

		finally {
			ReleaseResources.doReleaseResources(session);
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	@Override
	public void saveBlock(BlockForm blockForm, int blockCode, int blockVersion, int blockDistrictCode, int mapCd,
			Session session, HttpSession ses) throws Exception {
		int distVersion = 0;
		int minorVersion=0;
		District dist = null;
		BlockPK blockPK = new BlockPK(blockCode, blockVersion);
		Block blockBean = new Block();
		DistrictPK dPK = null;

		String[] temp = blockForm.getDistrictCode().split(",");
		if (temp.length > 1) {
			for (int i = 0; i < temp.length; i++) {
				List<District> districtList=districtService.getDistrictListByDistCode(Integer.parseInt(temp[i]));
				
				if(districtList!=null && !districtList.isEmpty()) {
					District element=districtList.get(0);
					distVersion=element.getDistrictVersion();
					minorVersion=element.getMinorVersion();
				}
				
				dPK = new DistrictPK(Integer.parseInt(temp[i]), distVersion,minorVersion);
				dist = null;
				dist = new District();
				dist.setDistrictPK(dPK);

				BlockDistricts blockDistrict = new BlockDistricts();
				blockDistrict.setBlockDistrictCode(blockDistrictCode);
				blockDistrict.setDistrict(dist);
				blockDAO.saveBlockDistrict(blockDistrict, session);
			}
			blockBean.setBlockDistrictCode(blockDistrictCode);
			// blockBean.setDistrict(dist);
		} else {
			distVersion = this.getDistrictVersion(Integer.parseInt(blockForm.getDistrictCode()));
			dPK = new DistrictPK(Integer.parseInt(blockForm.getDistrictCode()), distVersion,minorVersion);
			dist = null;
			dist = new District();
			dist.setDistrictPK(dPK);
			// blockBean.setDistrict(dist);
		}
		String lati = blockForm.getLatitude();
		String longi = blockForm.getLongitude();
		String[] arrayLati = lati.split(",");
		String[] arrayLong = longi.split(",");
		StringBuffer sb = new StringBuffer();
		int count = 1;
		for (int i = 0; i < arrayLati.length; i++) {
			for (int j = 0; j < arrayLong.length; j++) {
				if (i == j) {
					if (count == 1) {
						String temp1 = arrayLati[i] + ":" + arrayLong[i];
						sb.append(temp1);
						count++;
					} else {
						sb.append(",");
						String temp1 = arrayLati[i] + ":" + arrayLong[i];
						sb.append(temp1);
					}
				}
			}
		}
		String gisNodes = sb.toString();
		blockBean.setBlockNameEnglish(blockForm.getBlockNameEnglish());
		blockBean.setBlockNameLocal(blockForm.getBlockNameLocal());
		blockBean.setAliasEnglish(blockForm.getAliasEnglish());
		blockBean.setAliasLocal(blockForm.getAliasLocal());
		blockBean.setSsCode(blockForm.getSsCode());
		//blockBean.setMapCode(mapCd);

		blockBean.setBlockPK(blockPK);

		blockBean.setCreatedby(12345);
		blockBean.setCreatedon(timestamp);
		blockBean.setEffectiveDate(timestamp);
		blockBean.setIsactive(true);
		blockBean.setLastupdated(timestamp);
		blockBean.setLastupdatedby(12345);
		blockBean.setBlc(blockPK.getBlockCode());
		blockBean.setDlc(dPK.getDistrictCode());
		blockBean.setCoordinates(gisNodes);
		blockDAO.saveBlock(blockBean, session);
		BlockPK pk = blockBean.getBlockPK();
		ses.setAttribute("pk", pk);

	}

	/*
	 * @Override public int saveMapLandRegion(BlockForm blockForm, int
	 * blockCode, int blockVersion, Session session) throws Exception { String
	 * cord = null; if (blockForm.getLatitude().split(",").length > 1) {
	 * String[] tempLati = blockForm.getLatitude().split(","); String[]
	 * tempLongi = blockForm.getLongitude().split(","); cord = tempLati[0] + ","
	 * + tempLongi[0] + ":"; if (tempLati.length > 1) { for (int i = 1; i <
	 * tempLati.length; i++) { cord += tempLati[i] + "," + tempLongi[i] + ":"; }
	 * } cord = cord.substring(0, cord.length() - 1); } // cord =
	 * blockForm.getLati() + ":" + blockForm.getLongi();
	 * 
	 * MapLandRegion mapbean = new MapLandRegion();
	 * mapbean.setLandregionCode(blockCode);
	 * mapbean.setLandregionVersion(blockVersion);
	 * mapbean.setLandregionType('B'); mapbean.setCoordinates(cord);
	 * mapbean.setImagePath(blockForm.getMapUrl());
	 * mapbean.setLastupdated(timestamp); mapbean.setEffectiveDate(timestamp);
	 * mapbean.setLastupdatedby(1010101); mapbean.setCreatedby(1010101);
	 * mapbean.setCreatedon(timestamp); mapbean.setWarningflag(false); mapCd =
	 * mapLandRegionDAO.configMap(mapbean, session); return mapCd; }
	 */

	/*
	 * @Override public void saveHeadquarters(BlockForm blockForm, int maxHCode,
	 * int regionCode, int regionVersion, Session session) throws Exception {
	 * 
	 * Headquarters headquarters = new Headquarters();
	 * 
	 * headquarters.setHeadquarterCode(maxHCode);
	 * headquarters.setHeadquarterVersion(1);
	 * headquarters.setHeadquarterNameEnglish(blockForm.getHeadquarterName());
	 * headquarters.setHeadquarterLocalName(blockForm
	 * .getHeadquarterNameLocal()); headquarters.setIsactive(true);
	 * //headquarters.setRegionCode(regionCode);
	 * //headquarters.setRegionVersion(regionVersion);
	 * headquarters.setRegionType('B');
	 * 
	 * headquartersDAO.save(headquarters, session); }
	 */

	@Override
	public boolean saveReplacedBy(int id, int lrReplacedBy, int blockCode, int blockVersion, Session session)
			throws Exception {
		LandregionReplacedby landregionReplacedbyBean = null;
		landregionReplacedbyBean = new LandregionReplacedby();
		landregionReplacedbyBean.setId(id);
		landregionReplacedbyBean.setLrReplacedby(lrReplacedBy);
		landregionReplacedbyBean.setEntityCode(blockCode);
		landregionReplacedbyBean.setEntityVersion(blockVersion);
		landregionReplacedbyBean.setEntityType('B');
		landRegionReplacedByDAO.save(landregionReplacedbyBean, session);
		return true;
	}

	@Override
	public boolean saveReplaces(int id, int lrReplaces, int blockCode, int blockVersion, Session session)
			throws Exception {
		LandregionReplaces landregionReplacesBean = null;
		landregionReplacesBean = new LandregionReplaces();
		landregionReplacesBean.setId(id);
		landregionReplacesBean.setLrReplaces(lrReplaces);
		landregionReplacesBean.setEntityCode(blockCode);
		landregionReplacesBean.setEntityVersion(blockVersion);
		landregionReplacesBean.setEntityType('B');
		landRegionReplacesDAO.save(landregionReplacesBean, session);
		session.flush();
		session.refresh(landregionReplacesBean);
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int getDistrictVersion(int districtCode) throws Exception {
		Session session = sessionFactory.openSession();
		int distVers = 0;
		try {
			List<District> dist = session
					.createQuery("from District d where isactive=true and districtCode=" + districtCode).list();
			distVers = dist.get(0).getDistrictPK().getDistrictVersion();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distVers;
	}

	public List<Block> getBlockList(int districtCode) throws Exception {
		return blockDAO.getBlockListbydistrictCode(districtCode);

	}

	public List<Block> getTargetBlockList(int blockCode, int districtCode) throws Exception {
		return blockDAO.getTargetBlockList(blockCode, districtCode);
	}

	public List<Block> getTargetBlockListforSVillBlock(int districtCode, int sourceBlock) throws Exception {
		return blockDAO.getTargetBlockListforSVillBlock(districtCode, sourceBlock);
	}

	public List<Block> getTargetBlockListforDistUser(int Sblockcode, int districtCode) throws Exception {
		return blockDAO.getTargetBlockListforDistUser(Sblockcode, districtCode);
	}

	public List<Block> getBlockViewList(String query) throws Exception {
		return blockDAO.getBlockViewList(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDistrictListbyStateCode(int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<District> list = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from District d where d.isactive='true' and d.slc=" + stateCode
					+ "order by d.districtNameEnglish");
			list = query.list();
			return list;

		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * @Override public List<GovernmentOrder> getGovermentOrderDetail(int
	 * orderCode) throws Exception {
	 * 
	 * List<GovernmentOrder> govOrderDetail = blockDAO
	 * .getGovermentOrderDetail(orderCode);
	 * 
	 * return govOrderDetail; }
	 */

	/*
	 * @Override public List<MapAttachment> getMapDetailsModify(int
	 * mapLandregionCode) throws Exception { // TODO Auto-generated method stub
	 * List<MapAttachment> mapDetail =
	 * blockDAO.getMapDetailsModify(mapLandregionCode);
	 * 
	 * return mapDetail; }
	 */

	/*
	 * @Override public boolean modifyBlockInfo(BlockForm blockForm,
	 * HttpServletRequest request) throws Exception { // TODO Auto-generated
	 * method stub Session session1 = null; Transaction tx1 = null;
	 * 
	 * try { session1 = sessionFactory.openSession(); tx1 =
	 * session1.beginTransaction(); if (blockForm.isCorrection() == true) {
	 * Block blockBean = new Block(); GovernmentOrder governmentOrderbean = new
	 * GovernmentOrder(); List<BlockDataForm> listBlock = new
	 * ArrayList<BlockDataForm>(); listBlock = blockForm.getListBlockDetails();
	 * Iterator<BlockDataForm> itr = listBlock.iterator(); while (itr.hasNext())
	 * { BlockDataForm element = (BlockDataForm) itr.next(); try { BlockPK
	 * blockPK = new BlockPK(); blockPK.setBlockCode(element.getBlockCode());
	 * blockPK.setBlockVersion(element.getBlockVersion());
	 * blockBean.setBlockNameEnglish(element .getBlockNameEnglish().trim()); if
	 * (element.getBlockNameLocal() == null) {
	 * blockBean.setBlockNameLocal(element .getBlockNameLocal()); } else {
	 * blockBean.setBlockNameLocal(element .getBlockNameLocal().trim()); } if
	 * (element.getAliasEnglish() == null) { blockBean
	 * .setAliasEnglish(element.getAliasEnglish()); } else {
	 * blockBean.setAliasEnglish(element.getAliasEnglish() .trim()); } if
	 * (element.getAliasLocal() == null) {
	 * blockBean.setAliasLocal(element.getAliasLocal()); } else {
	 * 
	 * blockBean.setAliasLocal(element.getAliasLocal() .trim()); } if
	 * (element.getSsCode() == null) { blockBean.setSsCode(element.getSsCode());
	 * } else { blockBean.setSsCode(element.getSsCode().trim()); }
	 * blockBean.setMapCode(element.getMapCode());
	 * 
	 * governmentOrderbean.setOrderCode(element .getOrderCodecr());
	 * governmentOrderbean.setOrderDate(element .getOrderDatecr());
	 * governmentOrderbean.setEffectiveDate(element .getOrdereffectiveDatecr());
	 * governmentOrderbean.setGazPubDate(element .getGazPubDatecr()); if
	 * (element.getOrderNocr() == null) { governmentOrderbean.setOrderNo(element
	 * .getOrderNocr()); } else { governmentOrderbean.setOrderNo(element
	 * .getOrderNocr().trim()); }
	 * 
	 * blockBean.setBlockVersion(element.getBlockVersion()); String longitude =
	 * ""; String latitude = ""; int mapCode = 0;
	 * blockBean.setMapCode(element.getMapCode()); String cordinate = "";
	 * blockBean.setIsactive(true); if (element.getMapCode() == null) { mapCode
	 * = 0; } else { mapCode = element.getMapCode(); }
	 * 
	 * if (element.getLati() == null) { latitude = ""; } else { latitude =
	 * element.getLati(); } if (element.getLongi() == null) { latitude = ""; }
	 * else { longitude = element.getLongi(); } cordinate = longitude + ":" +
	 * latitude; String cord1 = null; if (blockForm.getLati() != null) { if
	 * (blockForm.getLati().split(",").length > 1) { String[] tempLati =
	 * blockForm.getLati().split( ","); String[] tempLongi =
	 * blockForm.getLongi() .split(","); cord1 = tempLati[0] + "," +
	 * tempLongi[0] + ":"; if (tempLati.length > 1) { for (int i = 1; i <
	 * tempLati.length; i++) { cord1 += tempLati[i] + "," + tempLongi[i] + ":";
	 * } } cord1 = cord1.substring(0, cord1.length() - 1); } } try { int
	 * map_landRegionCode = 0; if (mapCode == 0) { map_landRegionCode = blockDAO
	 * .getMaxMapCode(
	 * "select COALESCE(max(map_landregion_code),1) from map_landregion");
	 * 
	 * if (map_landRegionCode == 0) { map_landRegionCode = 1; } else {
	 * map_landRegionCode = map_landRegionCode + 1; } String cord = null; if
	 * (blockForm.getLati() != null) { if (blockForm.getLati().split(",").length
	 * > 1) { String[] tempLati = blockForm.getLati() .split(","); String[]
	 * tempLongi = blockForm .getLongi().split(","); cord = tempLati[0] + "," +
	 * tempLongi[0] + ":"; if (tempLati.length > 1) { for (int i = 1; i <
	 * tempLati.length; i++) { cord += tempLati[i] + "," + tempLongi[i] + ":"; }
	 * } cord = cord.substring(0, cord.length() - 1); } } try {
	 * 
	 * Timestamp time = CurrentDateTime .getCurrentTimestamp(); MapLandRegion
	 * mapLandRegion = null; mapLandRegion = new MapLandRegion(); mapLandRegion
	 * .setEffectiveDate(CurrentDateTime .getDate("2011-12-12"));
	 * mapLandRegion.setCreatedon(time); mapLandRegion.setCreatedby(100);
	 * mapLandRegion.setLandregionType('B');
	 * mapLandRegion.setLandregionVersion(element .getBlockVersion());
	 * mapLandRegion.setCoordinates(cord);
	 * mapLandRegion.setLandregionCode(element .getBlockCode());
	 * mapLandRegion.setWarningflag(true); mapLandRegionDAO.save(mapLandRegion);
	 * try { blockBean .setMapCode(map_landRegionCode);
	 * System.out.println("getMapCode=====" + element.getMapCode());
	 * blockDAO.modifyBlockInfo(blockForm, blockPK, map_landRegionCode,
	 * session1); } catch (Exception e) { log.debug("Exception" + e); } // }
	 * catch (Exception e) { // TODO Auto-generated catch block
	 * log.debug("Exception" + e); } } else {
	 * 
	 * blockDAO.updateMapLanRegion(mapCode, cord1, element.getBlockCode(),
	 * session1);
	 * 
	 * blockDAO.modifyBlockInfo(blockForm, blockPK, mapCode, session1); }
	 * govtOrderService.updateGovernmentOrder( element.getOrderNocr(),
	 * element.getOrderCodecr(), element.getOrderDatecr(),
	 * element.getOrdereffectiveDatecr(), element.getGazPubDatecr(), "update",
	 * element.getOrderPath(), blockForm.getFilePathcr(), request); }
	 * 
	 * catch (Exception e) { log.debug("Exception" + e); }
	 * 
	 * } catch (Exception e) { log.debug("Exception" + e); // tx.rollback();
	 * return false; }
	 * 
	 * } return true; } else if (blockForm.isCorrection() == false) {
	 * 
	 * Block blockbean = null; blockbean = new Block(); District district = new
	 * District(); List<BlockDataForm> listBlock = new
	 * ArrayList<BlockDataForm>(); listBlock = blockForm.getListBlockDetails();
	 * 
	 * Iterator<BlockDataForm> itr = listBlock.iterator();
	 * 
	 * while (itr.hasNext()) { BlockDataForm element = (BlockDataForm)
	 * itr.next(); int blockVersion = 1; blockVersion =
	 * blockDAO.getMaxBlockVersionbyCode(element .getDistrictCode()); if
	 * (blockVersion == 1) { blockVersion = blockVersion + 1; } else {
	 * blockVersion = blockVersion + 1; } BlockPK bpk = new
	 * BlockPK(element.getBlockCode(), blockVersion); blockbean.setBlockPK(bpk);
	 * blockbean.setBlockVersion(blockVersion);
	 * 
	 * blockbean.setBlockNameEnglish(element.getBlockNameEnglish() .trim()); if
	 * (element.getBlockNameLocal() == null) { blockbean
	 * .setBlockNameLocal(element.getBlockNameLocal()); } else {
	 * blockbean.setBlockNameLocal(element.getBlockNameLocal() .trim()); } if
	 * (element.getAliasEnglish() == null) {
	 * blockbean.setAliasEnglish(element.getAliasEnglish()); } else {
	 * blockbean.setAliasEnglish(element.getAliasEnglish() .trim()); } if
	 * (element.getAliasEnglish() == null) {
	 * blockbean.setAliasEnglish(element.getAliasEnglish()); } else {
	 * blockbean.setAliasLocal(element.getAliasLocal().trim()); }
	 * 
	 * // blockbean.setCensus2011Code(element.getCensus2011Code());
	 * blockbean.setMapCode(element.getMapCode());
	 * blockbean.setSsCode(element.getSsCode());
	 * blockbean.setEffectiveDate(timestamp);
	 * blockbean.setLastupdated(timestamp); blockbean.setCreatedon(timestamp);
	 * blockbean.setCreatedby(1010101); blockbean.setLastupdatedby(1010101);
	 * blockbean.setIsactive(true); DistrictPK dPK = new
	 * DistrictPK(element.getDistrictCode(), element.getDistrictVersion());
	 * district.setDistrictPK(dPK); //blockbean.setDistrict(district);
	 * 
	 * try { String blockCode = Integer.toString(element .getBlockCode());
	 * bpk.setBlockCode(element.getBlockCode());
	 * bpk.setBlockVersion(blockVersion);
	 * blockDAO.ChangeBlock(element.getBlockCode(),
	 * element.getBlockNameEnglishch(), 444, element.getBlockNameLocalch(),
	 * element.getAliasEnglishch(), element.getAliasLocalch());
	 * 
	 * List<String> allowedMimeTypeList = govtOrderService .getMimeTypeList();
	 * AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
	 * addAttachmentBean.setCurrentlyUploadFileList(blockForm
	 * .getAttachedFile()); addAttachmentBean.setUploadLocation(blockForm
	 * .getUploadLocation()); addAttachmentBean
	 * .setAllowedTotalNoOfAttachment(blockForm .getAllowedNoOfAttachment());
	 * addAttachmentBean.setAllowedTotalFileSize(blockForm
	 * .getAllowedTotalFileSize()); addAttachmentBean
	 * .setAllowedIndividualFileSize(blockForm .getAllowedIndividualFileSize());
	 * addAttachmentBean.setFileTitle(request .getParameterValues("fileTitle"));
	 * addAttachmentBean.setDeletedFileList(request
	 * .getParameterValues("fileSize"));
	 * addAttachmentBean.setNoOFAlreadyAttachedFiles(0);
	 * addAttachmentBean.setTotalSizeOFAlreadyAttachedFiles(0);
	 * addAttachmentBean.setMimeTypeList(allowedMimeTypeList);
	 * addAttachmentBean.setDeletedFileID(request
	 * .getParameterValues("deletedFileID2"));
	 * addAttachmentBean.setDeletedFileName(request
	 * .getParameterValues("deletedFileName2"));
	 * addAttachmentBean.setDeletedFileList(request
	 * .getParameterValues("deletedFileSizeList2"));
	 * addAttachmentBean.setNoOFMandatoryFile(1);
	 * addAttachmentBean.setMimeTypeList(allowedMimeTypeList);
	 * 
	 * AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
	 * attachmentHandler.validateAttachment(addAttachmentBean);
	 * List<AttachedFilesForm> metaInfoOfToBeAttachedFileList =
	 * attachmentHandler .getMetaDataListOfFiles(addAttachmentBean);
	 * attachmentHandler.saveMetaDataINFileSystem(
	 * metaInfoOfToBeAttachedFileList, addAttachmentBean);
	 * 
	 * GovernmentOrder govtOrder = saveDataInGovtOrder( blockForm, session1);
	 * saveDataInAttachment(govtOrder, blockForm,
	 * metaInfoOfToBeAttachedFileList, session1);
	 * shiftService.saveDataInGovtOrderEntityWise(govtOrder, blockCode, 'B',
	 * session1); } catch (Exception e) { // TODO Auto-generated catch block
	 * log.debug("Exception" + e); }
	 * 
	 * } } tx1.commit(); } catch (Exception e) { // TODO Auto-generated catch
	 * block log.debug("Exception" + e); if(tx1 != null){ tx1.rollback(); } }
	 * finally { if(session1 != null && session1.isOpen()) { session1.close(); }
	 * } return true;
	 * 
	 * }
	 */
	@Override
	public List<BlockDataForm> getBlockDetailsModify(int blockCode) throws Exception {
		BlockDataForm blockDataForm = new BlockDataForm();
		List<BlockDataForm> listBlockDetails = new ArrayList<BlockDataForm>();
		try {
			List<Block> listBlockDetail = blockDAO.getBlockDetailsModify(blockCode);
            Integer minorVersion=null;
			if (!listBlockDetail.isEmpty()) {
				Block element = listBlockDetail.get(0);
				int blockversion = blockDAO.getMaxBlockVersionbyCode(blockCode);
				blockDataForm.setAliasEnglish(element.getAliasEnglish());
				blockDataForm.setAliasEnglishch(element.getAliasEnglish());
				blockDataForm.setAliasLocal(element.getAliasLocal());
				blockDataForm.setAliasLocalch(element.getAliasLocal());
				if (element.getSsCode() == null) {
					blockDataForm.setSsCode(element.getSsCode());
				} else {
					blockDataForm.setSsCode(element.getSsCode().trim());
				}
				minorVersion=element.getMinorVersion();
				blockDataForm.setBlockCode(element.getBlockCode());
				blockDataForm.setBlockVersion(element.getBlockVersion());
				blockDataForm.setMinorVersion(minorVersion);
				blockDataForm.setBlockNameEnglish(element.getBlockNameEnglish().trim());
				blockDataForm.setBlockNameLocal(element.getBlockNameLocal());
				blockDataForm.setBlockNameEnglish(element.getBlockNameEnglish().trim());
				blockDataForm.setBlockNameEnglishch(element.getBlockNameEnglish().trim());
				blockDataForm.setBlockNameLocalch(element.getBlockNameLocal());
				blockDataForm.setCordinate(element.getCoordinates());
				blockDataForm.setLrReplacedby(element.getLrReplacedby());
				blockDataForm.setLrReplaces(element.getLrReplaces());
				//blockDataForm.setMapCode(element.getMapCode());

				String Cordinate = null;
				if (element.getCoordinates() != null) {
					if (!element.getCoordinates().trim().equals("")) {
						Cordinate = element.getCoordinates();
					}
				}
				blockDataForm.setCordinate(Cordinate);
				blockDataForm.setWarningFlag(element.getWarningFlag());

				List<Headquarters> headquartersDetails = blockDAO.getHeadquarterModify(blockCode, blockversion);
				if (!headquartersDetails.isEmpty()) {
					Headquarters headquarters = headquartersDetails.get(0);
					blockDataForm.setHeadquarterName(headquarters.getHeadquarterNameEnglish());
					blockDataForm.setHeadquarterNameLocal(headquarters.getHeadquarterLocalName());
					blockDataForm.setHeadquarterCode(headquarters.getHeadquarterCode());
				}

				List<GetGovernmentOrderDetail> govtOrderValue =blockDAO.GovOrderDetail('B', blockCode, blockversion,minorVersion );
				if (!govtOrderValue.isEmpty()) {
					GetGovernmentOrderDetail governmentOrder = govtOrderValue.get(0);

					blockDataForm.setOrderCodecr(governmentOrder.getOrderCode());
					if (governmentOrder.getOrderNo() != null) {
						blockDataForm.setOrderNocr(governmentOrder.getOrderNo().trim());
						blockDataForm.setOrderNo(governmentOrder.getOrderNo().trim());
					}
					blockDataForm.setOrderDatecr(governmentOrder.getOrderDate());
					blockDataForm.setGazPubDatecr(governmentOrder.getGazPubDate());

				}
				blockDataForm.setOrdereffectiveDatecr(element.getEffectiveDate());
				blockDataForm.setOrdereffectiveDate(element.getEffectiveDate());
				listBlockDetails.add(blockDataForm);

			}
			return listBlockDetails;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return listBlockDetails;
		}
	}

	/*
	 * @Override
	 * 
	 * @SuppressWarnings("unchecked") public List<Block>
	 * getBlockListbyDistrictCode(int districtCode) throws Exception { Session
	 * session = sessionFactory.openSession(); try { List<Block> blockList = new
	 * ArrayList<Block>(); Query query = session .createQuery(
	 * "from Block b where b.isactive=true and b.dlc=:districtCode order by b.blockNameEnglish"
	 * ); query.setParameter("districtCode", districtCode); blockList =
	 * query.list(); return blockList; } catch (HibernateException e) {
	 * log.debug("Exception" + e); return null; } finally { if (session != null
	 * && session.isOpen()) { session.close(); } } }
	 */

	/*
	 * un comment for Handling Blocks falling under 2 districts (manage
	 * block) @Ashish Dhupia @date 09-09-2014
	 */
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Block> getBlockListbyDistrictCode(int districtCode)
	 * throws Exception { SQLQuery criteria = null; Session session = null; try
	 * { session = sessionFactory.openSession(); List<Block> blockList = new
	 * ArrayList<Block>(); criteria = session.createSQLQuery(
	 * " select b.block_Code as blockCode,b.block_Name_English as blockNameEnglish from "
	 * + " Block b where b.isactive=true and b.dlc=:districtCode" + " UNION" +
	 * " select b.block_Code as blockCode,b.block_Name_English as blockNameEnglish "
	 * +
	 * " from Block b,district,block_districts where block_districts.dlc = district.dlc "
	 * + " AND block_districts.district_version=district.district_version " +
	 * " AND district.isactive = TRUE AND block_districts.block_version=b.block_version "
	 * +
	 * " AND b.isactive = TRUE AND b.block_district_code=block_districts.block_district_code "
	 * +
	 * " AND district.district_code = COALESCE(:districtCode, district.district_code) "
	 * + " AND b.dlc=0"); criteria.setParameter("districtCode",
	 * districtCode,Hibernate.INTEGER);
	 * criteria.addScalar("blockCode").addScalar("blockNameEnglish");
	 * criteria.setResultTransformer(Transformers.aliasToBean(Block.class));
	 * blockList =criteria.list();
	 * 
	 * 
	 * return blockList; } catch (HibernateException e) { log.debug("Exception"
	 * + e); return null; } finally { session.close(); } }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void saveBlockVillage(int oldBlockCode, int oldBlockVersion, int newBlockCode, int newBlockVersion,
			Session session) throws Exception {
		/*
		 * List<Object[]> temp = session.createSQLQuery(
		 * "select village_code from block_village b where block_code=" +
		 * oldBlockCode + " and " + "block_version=" + oldBlockVersion).list();
		 */
		BlockVillage blockVillage = null;
		Block block = null;
		block = new Block();
		Village vill = null;
		VillagePK villPK = null;
		List<Village> listBVillage = new ArrayList<Village>();
		listBVillage = session
				.createSQLQuery("Select * from Village where village_code in ("
						+ "select village_code from block_village b where block_code=" + oldBlockCode + " and "
						+ "block_version=" + oldBlockVersion + ") and isactive=true")
				.addEntity("Village", Village.class).list();
		BlockPK blockPK = new BlockPK(newBlockCode, newBlockVersion);
		block.setBlockPK(blockPK);
		try {
			session.createSQLQuery("update block_village set isactive=false where block_code=" + oldBlockCode
					+ " and block_version=" + oldBlockVersion).executeUpdate();

			for (int i = 0; i < listBVillage.size(); i++) {
				blockVillage = new BlockVillage();
				vill = new Village();
				villPK = new VillagePK(listBVillage.get(i).getVlc(), listBVillage.get(i).getVlc(),listBVillage.get(i).getMinorVersion());
				vill.setVillagePK(villPK);

				// blockVillage.setBlock(block);
				// blockVillage.setVillage(vill);
				blockVillage.setIsactive(true);
				blockDAO.saveBlockVillage(blockVillage, session);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void saveBlockVillagePart(int villCode, int newBlockCode, int newBlockVersion, Session session)
			throws Exception {
		BlockVillage blockVillage = null;
		Block block = null;
		block = new Block();
		Village vill = null;
		VillagePK villPK = null;
		List<Village> listBVillage = new ArrayList<Village>();
		listBVillage = session
				.createSQLQuery("Select * from Village where village_code=" + villCode + " and isactive=true")
				.addEntity("Village", Village.class).list();
		BlockPK blockPK = new BlockPK(newBlockCode, newBlockVersion);
		block.setBlockPK(blockPK);
		try {
			for (int i = 0; i < listBVillage.size(); i++) {
				blockVillage = new BlockVillage();
				vill = new Village();
				villPK = new VillagePK(listBVillage.get(i).getVlc(), listBVillage.get(i).getVlc(),listBVillage.get(i).getMinorVersion());
				vill.setVillagePK(villPK);

				// blockVillage.setBlock(block);
				// blockVillage.setVillage(vill);
				blockVillage.setIsactive(true);
				blockDAO.saveBlockVillage(blockVillage, session);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void saveBlockVillageFull(int villCode, int newBlockCode, int newBlockVersion, Session session)
			throws Exception {
		BlockVillage blockVillage = null;
		Block block = null;
		block = new Block();
		Village vill = null;
		VillagePK villPK = null;
		List<Village> listBVillage = new ArrayList<Village>();
		listBVillage = session
				.createSQLQuery("Select * from Village where village_code=" + villCode + " and isactive=true")
				.addEntity("Village", Village.class).list();
		BlockPK blockPK = new BlockPK(newBlockCode, newBlockVersion);
		block.setBlockPK(blockPK);
		try {
			session.createSQLQuery("update block_village set isactive=false where village_code=" + villCode)
					.executeUpdate();
			for (int i = 0; i < listBVillage.size(); i++) {
				blockVillage = new BlockVillage();
				vill = new Village();
				villPK = new VillagePK(listBVillage.get(i).getVlc(), listBVillage.get(i).getVlc(),listBVillage.get(i).getMinorVersion());
				vill.setVillagePK(villPK);

				// blockVillage.setBlock(block);
				// blockVillage.setVillage(vill);
				blockVillage.setIsactive(true);
				blockDAO.saveBlockVillage(blockVillage, session);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}

	public boolean saveNewBlockVersion(int blockCode, int blockVersion, int lrReplacedBy, Session session)
			throws Exception {
		try {
			BlockPK blockpk = null;
			Block blockBeanLocal = null;
			blockpk = new BlockPK(blockCode, blockVersion);
			blockBeanLocal = new Block();
			blockBeanLocal = blockDAO.getBlockDetail(blockpk, session);
			Block blockBean = new Block();
			blockpk = null;
			blockpk = new BlockPK(blockCode, blockVersion + 1);
			// District dist = blockBeanLocal.getDistrict();
			blockBean.setBlockPK(blockpk);
			blockBean.setBlockNameEnglish(blockBeanLocal.getBlockNameEnglish());
			blockBean.setBlockNameLocal(blockBeanLocal.getBlockNameLocal());
			blockBean.setAliasEnglish(blockBeanLocal.getAliasEnglish());
			blockBean.setAliasLocal(blockBeanLocal.getAliasLocal());
			blockBean.setCreatedby(blockBeanLocal.getCreatedby());
			blockBean.setCreatedon(blockBeanLocal.getCreatedon());
			// blockBean.setDistrict(dist);
			blockBean.setEffectiveDate(blockBeanLocal.getEffectiveDate());
			blockBean.setFlagCode(2);
			blockBean.setIsactive(true);
			blockBean.setLastupdated(blockBeanLocal.getLastupdated());
			blockBean.setLrReplacedby(lrReplacedBy);
			blockBean.setLrReplaces(blockBeanLocal.getLrReplaces());
			//blockBean.setMapCode(blockBeanLocal.getMapCode());
			blockBean.setSsCode(blockBeanLocal.getSsCode());

			blockDAO.saveBlock(blockBean, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public List<Village> getBlockVillagebyBlockCode(int blockCode) throws Exception {
		List<Village> village = null;
		try {
			village = blockDAO.getBlockVillagebyBlockCode(blockCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return village;
	}

	public List<BlockHistory> getBlockHistoryDetail(char blockNameEnglish, int blockCode) throws Exception {
		List<BlockHistory> BlockHistoryDetail = new ArrayList<BlockHistory>();
		BlockHistoryDetail = blockDAO.getBlockHistoryDetail(blockNameEnglish, blockCode);
		return BlockHistoryDetail;
	}

	/*
	 * @Override public boolean publishBlockandRenamedContriBlocks(BlockForm
	 * blockForm, List<BlockForm> listContriBlockForm) throws Exception {
	 * boolean isCommited = false; HttpSession ses =null; try {
	 * this.publishBlock(blockForm,ses);
	 * 
	 * for (int i = 0; i < listContriBlockForm.size(); i++) {
	 * this.renameContributedBlock(listContriBlockForm.get(i)); } isCommited =
	 * true; } catch (Exception e) { isCommited = false; } return isCommited; }
	 */
	@Override
	public boolean renameContributedBlock(BlockForm blockForm) throws Exception {
		int blockCode = 0;
		String blockName = null;
		String blockLocal = null;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		blockCode = blockForm.getBlockCode();
		blockName = blockForm.getBlockNameEnglish();
		blockLocal = blockForm.getBlockNameLocal();

		try {
			session.createSQLQuery("update block set block_name_english='" + blockName + "', block_name_local='"
					+ blockLocal + "' where block_code=" + blockCode + " and isactive=true").executeUpdate();
			tx.commit();
			return true;

		} catch (HibernateException e) {
			log.debug("Exception" + e);
			tx.rollback();
			return false;
		} finally {
			ReleaseResources.doReleaseResources(session);
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean invalidateBlockLoop(String blockCode, String villageCodes, HttpSession httpSession)
			throws Exception {
		// TODO invalidateLoop
		// System.out.println("welcome world");
		try {
			bVillMap.put(blockCode, villageCodes);
			if (!blockCode.isEmpty()) {
				httpSession.setAttribute("bVillMap", bVillMap);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	@Override
	public Block getSingleBlockDetailsMaxVersion(int parseInt) throws Exception {
		Block b = new Block();
		try {
			String sQuery = "SELECT * FROM  block where block_code=" + parseInt
					+ " and block_version=(select COALESCE(max(block_version),1) from block where block_code="
					+ parseInt + ")";
			b = blockDAO.getSingleDistrictDetailsMaxVersion(sQuery);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return b;
	}

	public GovernmentOrder saveDataInGovtOrder(BlockForm govtForm, Session session) throws Exception {

		GovernmentOrder governmentOrder = new GovernmentOrder();
		try {
			governmentOrder.setOrderDate(govtForm.getOrderDate());
			governmentOrder.setEffectiveDate(govtForm.getOrdereffectiveDate());
			governmentOrder.setGazPubDate(govtForm.getGazPubDate());
			governmentOrder.setCreatedon(new Date());
			governmentOrder.setDescription("LGD DETAILS");
			governmentOrder.setIssuedBy("GOVERNOR");
			governmentOrder.setCreatedby(createdBy);
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby(createdBy);
			governmentOrder.setLevel("B");
			governmentOrder.setOrderNo(govtForm.getOrderNo());
			governmentOrder.setStatus('A');
			governmentOrder.setUserId(userId);
			localGovtBodyDAO.saveOrderDetails(governmentOrder, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return governmentOrder;
	}

	public void saveDataInAttachment(GovernmentOrder govtOrder, BlockForm govtForm,
			List<AttachedFilesForm> attachedList, Session session) throws Exception {

		Attachment attachment = null;
		try {
			Iterator<AttachedFilesForm> it = attachedList.iterator();
			while (it.hasNext()) {
				AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
				attachment = new Attachment();
				attachment.setGovernmentOrder(govtOrder);
				attachment.setFileName(filesForm.getFileName());
				attachment.setFileSize(filesForm.getFileSize());
				attachment.setFileContentType(filesForm.getFileType());
				attachment.setFileLocation(filesForm.getFileLocation());
				attachment.setFileTimestamp(filesForm.getFileTimestampName());
				session.save(attachment);
				session.flush();
				if (session.contains(attachment)) {
					session.evict(attachment);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
	}

	@Override
	public boolean modifyBlockCrInfo(BlockForm blockForm, HttpServletRequest request,
			List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, boolean delflag,
			String deleteAttachmentId[], HttpSession httpSession) throws Exception {

		Session session1 = sessionFactory.openSession();
		Transaction tx1 = session1.beginTransaction();

		try {
			
			List<BlockDataForm> listblock = blockForm.getListBlockDetails();
			
			if (!listblock.isEmpty()) {

					BlockDataForm element = listblock.get(0);
					Integer blockCode = element.getBlockCode();
					Integer blockVersion = element.getBlockVersion();
					BlockPK blockPK = new BlockPK();
					blockPK.setBlockCode(blockCode);
					blockPK.setBlockVersion(blockVersion);
					Date currentDate = dateTimeUtil.getCurrentDate();

				

				Integer mapCode = element.getMapCode();
				if (delflag == true) {
					govtOrderService.deleteMapAttachementforLandRegion(blockCode, 'B', session1);
					mapCode = null;
				}

				if (attachedMapList != null) {
					mapCode = govtOrderService.saveDatainMapAttachmentforLandregion(attachedMapList, blockCode, 'B',
							session1);

				}
				
				int filecount =blockForm.getGovtfilecount()!=null && blockForm.getGovtfilecount().length()>0?Integer.parseInt(blockForm.getGovtfilecount()):0;
				
				
				Query query = session1.createSQLQuery("select * from change_block_basic_details_fn(:blockCode,:userId,:aliasEnglish,:aliasLocal,:sscode,:order_no,:order_date,:effectiveDate,:gaz_pubdate,:govtOrderConfig,:headquarterEnglish,:headquarterLocal,:uploadedFileFlag)");
				 query.setParameter("blockCode"	      ,blockCode, Hibernate.INTEGER)
					.setParameter("userId"	    	  ,userId				  			  , Hibernate.INTEGER)
					.setParameter("aliasEnglish"  	  ,element.getAliasEnglish()		  , Hibernate.STRING)
					.setParameter("aliasLocal"		  ,element.getAliasLocal()			  , Hibernate.STRING)
					.setParameter("sscode"			  ,element.getSsCode()				  , Hibernate.STRING)
					.setParameter("order_no"		  ,element.getOrderNocr()			  , Hibernate.STRING)
					.setParameter("order_date"		  ,element.getOrderDatecr()			  , Hibernate.TIMESTAMP)
					.setParameter("effectiveDate"	  ,element.getOrdereffectiveDatecr()  , Hibernate.TIMESTAMP)
					.setParameter("gaz_pubdate"		  ,element.getGazPubDatecr()		  , Hibernate.TIMESTAMP)
					.setParameter("govtOrderConfig"   ,"upload"			                  , Hibernate.STRING)
					.setParameter("headquarterEnglish",element.getHeadquarterName()		  , Hibernate.STRING)
					.setParameter("headquarterLocal"  ,element.getHeadquarterNameLocal()  , Hibernate.STRING)
					.setParameter("uploadedFileFlag"  ,filecount  , Hibernate.INTEGER);
				String returnedValue = (String) query.uniqueResult();
				
				if (returnedValue != null) {
					String[] ldata = returnedValue.split(",");
					String orderCode = ldata[0];
					String tid = ldata[1];
				
				
					

					int Transactionid = Integer.parseInt(tid);
					int ocode = Integer.parseInt(orderCode);
					
					
					GovernmentOrder govtOrder=(GovernmentOrder)session1.get(GovernmentOrder.class, ocode);
					
			
	
					if (deleteAttachmentId != null) {
						govtOrderDAO.deleteAttachmentForLandRegion(deleteAttachmentId, session1);
	
					}
	
					if (attachedList != null) {
						villageService.saveDataInAttachment(govtOrder, attachedList, session1);
					}
				}
			}

				tx1.commit();
			

		} catch (Exception e) {

			tx1.rollback();
			throw new Exception(e);

		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
		}
		return true;

	}

	@Override
	public List<StateWiseEntityDetails> getStateWiseBlockList(Integer statecode, char entitytype, Integer blockCode,
			String blockName, Integer limit, Integer offset) throws Exception {
		List<StateWiseEntityDetails> StateWiseVillageList = new ArrayList<StateWiseEntityDetails>();
		StateWiseVillageList = blockDAO.getStateWiseBlockList(statecode, entitytype, blockCode, blockName, limit,
				offset);
		return StateWiseVillageList;
	}

	@Override
	public List<StateWiseEntityDetails> getParentWiseList(char entitytype, Integer parentCode, Integer limit,
			Integer offset) throws Exception {
		List<StateWiseEntityDetails> StateWiseVillageList = new ArrayList<StateWiseEntityDetails>();
		StateWiseVillageList = blockDAO.getParentWiseList(entitytype, parentCode, limit, offset);
		return StateWiseVillageList;
	}

	@Override
	public int saveDataInMap(BlockForm blockForm, List<AttachedFilesForm> attachedList, HttpSession session,
			Session ses) throws Exception {
		return blockDAO.saveDataInMap(blockForm, attachedList, session, ses);

	}

	@Override
	public String insertBlock(HttpServletRequest request, HttpSession httpSession, BlockForm blockForm) {
		return blockDAO.insertBlock(request, httpSession, blockForm);

	}
	/*
	 * @Override public boolean saveDataInAttach(GovernmentOrderForm
	 * governmentOrderForm, List<AttachedFilesForm> attachedList,int blockCode,
	 * HttpSession session,Session ses) throws Exception { return
	 * blockDAO.saveDataInAttach(governmentOrderForm,attachedList,blockCode,
	 * session,ses);
	 * 
	 * }
	 */

	@Override
	public boolean invalidateBlock(int stateCode, int userId, int operationCode, int flagCode, String bvList,
			BlockForm blockForm, HttpSession session, List<AttachedFilesForm> metaInfoOfToBeAttachedFileList)
			throws Exception {

		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		Integer ordercode = null;
		String blockdata = null;
		// GovernmentOrderForm govtOrderForm = null;
		int Transactionid = 0;
		char t_type = 'B';
		boolean success = false;
		try {

			// orderCode=localGovtBodyDAO.invalidatePRIDAO(LBForm,hsession,userId,httpSession);

			blockdata = blockDAO.invalidateBlock(stateCode, userId, operationCode, flagCode, bvList, blockForm);
			if (blockdata != null) {
				String[] ldata = blockdata.split(",");
				String vc = ldata[1];
				String tid = ldata[0];
				ordercode = Integer.parseInt(vc);
				Transactionid = Integer.parseInt(tid);
				if (ordercode != null) {
					// govtOrderForm = null;
					GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
					GovernmentOrder govtOrder = new GovernmentOrder();
					if (blockForm.getGovtOrderConfig().equals("govtOrderUpload")) {
						govtOrder.setOrderDate(govtOrderForm.getOrderDate());
						govtOrder.setEffectiveDate(govtOrderForm.getEffectiveDate());
						govtOrder.setGazPubDate(govtOrderForm.getGazPubDate());
						govtOrder.setCreatedon(new Date());
						govtOrder.setDescription("LGD DETAILS");
						govtOrder.setIssuedBy("GOVERNOR");
						govtOrder.setCreatedby(createdBy);
						govtOrder.setLastupdated(new Date());
						govtOrder.setLastupdatedby(createdBy);
						govtOrder.setLevel("U");
						govtOrder.setOrderNo(govtOrderForm.getOrderNo());
						govtOrder.setStatus('A');
						govtOrder.setUserId(userId);
						govtOrder.setOrderCode(ordercode);

					} else if (blockForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
						if (ordercode != 0) {
							govtOrder.setOrderCode(ordercode);

						}
					}

					convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm,
							metaInfoOfToBeAttachedFileList, hsession);
					tx.commit();
					success = true;
					EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
					est.start();

				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);

			tx.rollback();
			return false;
		} finally {
			if (hsession != null && hsession.isOpen()) {
				hsession.close();
				sessionFactory.close();
			}

		}
		return success;

	}

	/*
	 * @Override public List<Village> getUnmapVillages(int disCode) throws
	 * Exception { // TODO Auto-generated method stub return
	 * blockDAO.getUnmapVillages(disCode); }
	 */

	public List<Localbody> getUlbbyDistrictCode(int disCode) throws Exception {

		return blockDAO.getULBbyDistrictCode(disCode);
	}

	/*
	 * public List<Localbody> getblockULb(String blc) throws Exception {
	 * 
	 * return blockDAO.getblockULb(blc); }
	 */

	@Override
	public List<BlockwiseVillageMappedUnmapped> getUnmapVillagesbyBlockCode(Integer blockCode, Character listType)
			throws Exception {
		return blockDAO.getUnmapVillagesbyBlockCode(blockCode, listType);
	}

	/*
	 * @Override public boolean BlockExist(int disCode,String blockName) { //
	 * TODO Auto-generated method stub return
	 * blockDAO.BlockExist(disCode,blockName); }
	 */

	@Override
	public List<BlockWiseVillagesAndUlb> getBlockWiseVillagesAndUlbDetail(Integer blc) throws Exception {
		// TODO Auto-generated method stub
		return blockDAO.getBlockWiseVillagesAndUlbDetailDAO(blc);
	}

	@Override
	public List<Block> getBlockListExtrend(Integer districtCode, Integer olc) throws Exception {
		return blockDAO.getBlockListExtrend(districtCode, olc);

	}

	@Override
	public synchronized boolean changeBlock(BlockForm blkForm, GovernmentOrderForm govtOrderForm,
			List<AttachedFilesForm> attachedList, HttpServletRequest request, int uid) throws Exception {
		char t_type = 'B';
		int Transactionid = 0;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<BlockDataForm> listblocks = new ArrayList<BlockDataForm>();
			listblocks = blkForm.getListBlockDetails();
			Iterator<BlockDataForm> itr = listblocks.iterator();
			boolean result = false;
			while (itr.hasNext()) {

				BlockDataForm element = (BlockDataForm) itr.next();

				String ordercode = blockDAO.ChangeBlock(element.getBlockCode(), element.getBlockNameEnglishch(),
						element.getBlockNameLocalch(), uid, blkForm.getBlockNameEnglish(), element.getAliasEnglishch(),
						element.getAliasLocalch(), session, blkForm.getOrderNo(), blkForm.getOrderDate(),
						blkForm.getEffectiveDate(), blkForm.getGazPubDate(),blkForm.getStateCode());

				if (ordercode != null) {
					String[] ldata = ordercode.split(",");
					String orderCode = ldata[1];
					String tid = ldata[0];

					Transactionid = Integer.parseInt(tid);
					int ocode = Integer.parseInt(orderCode);
					GovernmentOrder govtOrder = new GovernmentOrder();
					govtOrder.setOrderDate(blkForm.getOrderDate());
					govtOrder.setEffectiveDate(blkForm.getEffectiveDate());
					govtOrder.setGazPubDate(blkForm.getGazPubDate());
					govtOrder.setCreatedon(new Date());
					govtOrder.setDescription("LGD DETAILS");
					govtOrder.setIssuedBy("GOVERNOR");
					govtOrder.setCreatedby(createdBy);
					govtOrder.setLastupdated(new Date());
					govtOrder.setLastupdatedby(createdBy);
					govtOrder.setLevel("U");
					govtOrder.setOrderNo(blkForm.getOrderNo());
					govtOrder.setStatus('A');
					govtOrder.setUserId(userId);
					govtOrder.setOrderCode(ocode);
					convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm, attachedList, session);
					tx.commit();
					result = true;
					EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
					est.start();
				}
			}
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			return false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public boolean changeBlockforTemplate(BlockForm blockForm, GovernmentOrderForm govtOrderForm,
			HttpServletRequest request, HttpSession httpSession) throws Exception {
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// Integer stateCode=null,slc=null;
			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			Integer userId = sessionObject.getUserId() != null ? sessionObject.getUserId().intValue() : null;

			/*
			 * if(request.getAttribute("stateCode")!=null) {
			 * stateCode=Integer.parseInt(request.getAttribute("stateCode").
			 * toString()); }
			 */

			List<BlockDataForm> listblocks = blockForm.getListBlockDetails();

			if (!listblocks.isEmpty()) {

				BlockDataForm element = listblocks.get(0);

				String ordercode = blockDAO.ChangeBlock(element.getBlockCode(), element.getBlockNameEnglishch(),
						element.getBlockNameLocalch(), userId, blockForm.getBlockNameEnglish(),
						element.getAliasEnglishch(), element.getAliasLocalch(), session, blockForm.getOrderNo(),
						blockForm.getOrderDate(), blockForm.getEffectiveDate(), blockForm.getGazPubDate(), 9);

				GenerateDetails generatedetails = (GenerateDetails) httpSession
						.getAttribute("validGovermentGenerateUpload");

				if (ordercode != null) {
					String[] ldata = ordercode.split(",");
					String orderCode = ldata[0];
					String tid = ldata[1];

					int Transactionid = Integer.parseInt(tid);
					int ocode = Integer.parseInt(orderCode);
					GovernmentOrder govtOrder = new GovernmentOrder();
					govtOrder.setOrderDate(blockForm.getOrderDate());
					govtOrder.setEffectiveDate(blockForm.getEffectiveDate());
					govtOrder.setGazPubDate(blockForm.getGazPubDate());
					govtOrder.setCreatedon(new Date());
					govtOrder.setDescription("LGD DETAILS");
					govtOrder.setIssuedBy("GOVERNOR");
					govtOrder.setCreatedby(createdBy);
					govtOrder.setLastupdated(new Date());
					govtOrder.setLastupdatedby(createdBy);
					govtOrder.setLevel("U");
					govtOrder.setOrderNo(blockForm.getOrderNo());
					govtOrder.setStatus('A');
					govtOrder.setUserId(userId);
					govtOrder.setOrderCode(ocode);
					convertLocalbodyService.saveDataInAttachmentWithOrderCodeGenrate(Integer.parseInt(orderCode),
							generatedetails, session);

					tx.commit();

					EmailSmsThread est = new EmailSmsThread(Transactionid, 'B', emailService);
					est.start();
				}
			} else
				tx.rollback();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			@SuppressWarnings("unused")
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// ModelAndView mav = new ModelAndView(redirectPath);
			return false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * Get Block Details with their operation state for extended functionality
	 * of Department
	 * 
	 * @param districtCode
	 * @param orgCode
	 * @param locatedLevelCode
	 * @return
	 * @throws Exception
	 */
	public List<Block> getBlockListExtended(int districtCode, int orgCode, int locatedLevelCode) throws Exception {
		return blockDAO.getBlockListExtended(districtCode, orgCode, locatedLevelCode);

	}

	@Override
	public List<Village> getBlockVillagebyBlockCodeForLocalBody(int blockCode) throws Exception {
		// TODO Auto-generated method stub
		return blockDAO.getBlockVillagebyBlockCodeForLocalBody(blockCode);
	}

	@Override
	public List<Localbody> getblockULbForLocalBody(String blc) throws Exception {
		// TODO Auto-generated method stub
		return blockDAO.getblockULbForLocalBody(blc);
	}

	/**
	 * Add for getting Villages for selected districts for Localbody impact
	 * draft mode
	 * 
	 * @author Ripunj on 06-01-2015
	 * @param disCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Village> getUnmapVillagesForLocalBody(int disCode) throws Exception {
		// TODO Auto-generated method stub
		return blockDAO.getUnmapVillagesForLocalBody(disCode);
	}

	/**
	 * added by kirandeep on 07/01/2015 for localbody impact
	 * 
	 * @param districtCode
	 * @param sourceBlock
	 * @return
	 */

	public List<Block> getTargetBlockListforSVillBlockForLocalbody(int districtCode, int sourceBlock) {
		return blockDAO.getTargetBlockListforSVillBlockForLocalbody(districtCode, sourceBlock);
	}

	/**
	 * added by kirandeep for block using statecode on 12/01/2015 for local body
	 * impact
	 * 
	 * 
	 */

	public List<Block> getBlockStateWise(int stateCode) {
		return blockDAO.getBlockStateWise(stateCode);
	}

	/**
	 * Add for getting blocks for selected districts
	 * 
	 * @author Ripunj on 13-01-2015
	 * @param districtCode
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Block> getBlockListbyDistrictCodeForLocalBody(int districtCode) throws Exception {
		Session session = sessionFactory.openSession();
		SQLQuery criteria = null;
		try {
			List<Block> blockList = new ArrayList<Block>();
			criteria = session.createSQLQuery(
					" select case when b.block_Code  in (select * from get_draft_used_lb_lr_temp(b.block_Code,'B')) then  cast ('F' as character)"
							+ " else cast('A' as character) end as operation_state,"
							+ " b.block_Code as blockCode,b.block_version as blockVersion,b.block_Name_English as blockNameEnglish,b.block_name_local as blockNameLocal, b.dlc as dlc from "
							+ " Block b where b.isactive=true and b.dlc=:districtCode " + " UNION "
							+ " select case when b.block_Code  in (select * from get_draft_used_lb_lr_temp(b.block_Code,'B')) then  cast ('F' as character)"
							+ " else cast('A' as character) end as operation_state,"
							+ " b.block_Code as blockCode,b.block_version as blockVersion,b.block_Name_English as blockNameEnglish,b.block_name_local as blockNameLocal, "
							+ " b.dlc as dlc from "
							+ " Block b, block_districts bd where b.isactive=true and bd.dlc=:districtCode and b.block_district_code=bd.block_district_code order by blockNameEnglish");
			criteria.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			criteria.addScalar("operation_state").addScalar("blockCode").addScalar("blockVersion")
					.addScalar("blockNameEnglish").addScalar("blockNameLocal").addScalar("dlc");
			criteria.setResultTransformer(Transformers.aliasToBean(Block.class));
			blockList = criteria.list();
			return blockList;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * Using DWR Block List populate base on Creteria for extend organigation
	 * Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 */
	@Override
	public List<Block> getBlockListbyCreteria(String districtCodes, String blockCodes, Integer districtCode)
			throws Exception {
		List<Integer> districtList = districtCodes != null ? commonService.createListFormString(districtCodes) : null;
		List<Integer> blockCodeList = blockCodes != null ? commonService.createListFormString(blockCodes) : null;
		return blockDAO.getBlockListbyCreteria(districtList, blockCodeList, districtCode);
	}

	/**
	 * The {@code onloadAttributesBPMapping} used to fetch list of block
	 * panchayat needs to be mapped with blocks.
	 * 
	 * @param districtCode
	 * @return
	 * @throws HibernateException
	 * @author Vinay Yadav (Created on 30/12/2015)
	 */
	@Override
	public BlockPanchayatSyncBlock onloadAttributesBPMapping(Integer stateCode, Integer districtCode)
			throws HibernateException {
		// TODO Auto-generated method stub
		return blockDAO.onloadAttributesBPMapping(stateCode, districtCode);
	}

	@Override
	public String createNewBlockForSync(Integer districtCode, Integer bpCode) throws HibernateException {
		// TODO Auto-generated method stub
		return blockDAO.createNewBlockForSync(districtCode, bpCode);
	}

	@Override
	public String syncProcessBlockWithBP(Integer districtCode, String paramSyncCodes) throws HibernateException {
		// TODO Auto-generated method stub
		return blockDAO.syncProcessBlockWithBP(districtCode, paramSyncCodes);
	}

	@Override
	public List<Block> getBlockListbyDistrict(Integer districtCode) throws HibernateException {
		return blockDAO.getBlockListbyDistrict(districtCode);
	}

	@Override
	public boolean saveBlockVillageMapping(BlockVillage blockVillage) throws Exception {
		return blockDAO.saveBlockVillageMapping(blockVillage);
	}

	@Override
	public boolean validateBlockVillageMapping(Integer blockCode, Integer blockVersion, String villageCodes)
			throws Exception {

		List<Integer> villageList = new ArrayList<Integer>();
		List<Integer> villageDelete = new ArrayList<Integer>();
		Scanner scanner = new Scanner(villageCodes);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			int value = Integer.parseInt(scanner.next());
			villageList.add(value);
		}
		scanner.close();

		return blockDAO.validateBlockVillageMapping(blockCode, blockVersion, villageList);
	}

	// shivam
		@Override
		public Character newBlockNameIsUnique(String newBlock, Integer districtCode,Integer statecode) throws Exception {
			return blockDAO.newblockNameIsUnique(newBlock, districtCode,statecode);
		}
		
		@Override
		public Boolean getConfigurationBlockVillageMapping(Character isUserType) throws Exception {
			return blockDAO.getConfigurationBlockVillageMapping(isUserType);
		}
		@Override
		public List<LocalBodyDto> getVillageCoverageDetailBlock(Integer blockCode, String villageCode) throws Exception {
			return blockDAO.getVillageCoverageDetailBlock(blockCode ,villageCode);
		}
		 
		@Override
		public Boolean saveBlockVillageMappingLb(BlockVillage blockVillage ,String listOfVlc ,String unMappListVlc) throws Exception {
			return blockDAO.saveBlockVillageMappingLb(blockVillage ,listOfVlc,unMappListVlc);
		}
		
		@Override
		public List<LocalBodyDto> getLbCoveredSelected(Integer blockCode, String villageCode) throws Exception {
			return blockDAO.getLbCoveredSelected(blockCode ,villageCode);
		}

		@Override
		public Response saveEffectiveDateEntityBlock(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId,Integer stateCode) {
			return blockDAO.saveEffectiveDateEntityBlock(getEntityEffectiveDateList, userId,stateCode);
		}
		
}
