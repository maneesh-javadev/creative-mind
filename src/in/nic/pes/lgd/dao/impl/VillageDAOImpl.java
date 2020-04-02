package in.nic.pes.lgd.dao.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cmc.lgd.localbody.entities.LocalBodyForm;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.ChangeVillage;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.FetchLOCALBODYHABIATION;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GetLBDetailsBySubDistricts;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.Habitation;
import in.nic.pes.lgd.bean.HabitationConfiguration;
import in.nic.pes.lgd.bean.InvalidateVillage;
import in.nic.pes.lgd.bean.InvalidateVillageUlbMerge;
import in.nic.pes.lgd.bean.LocalbodyHabitation;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.PesApplicationMaster;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillageDraft;
import in.nic.pes.lgd.bean.VillageGISMapStatus;
import in.nic.pes.lgd.bean.VillageHistory;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.bean.VillagePartsBySurveyno;
import in.nic.pes.lgd.bean.VillageSaveBean;
import in.nic.pes.lgd.common.EncryptionUtil;
import in.nic.pes.lgd.common.persistencecontext.HibernateSessionLGDRegistration;
import in.nic.pes.lgd.constant.CommanConstant;
import in.nic.pes.lgd.constant.VillageConstant;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.HabitationForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.utils.CurrentDateTime;
import pes.attachment.util.AttachedFilesForm;

@Repository
public class VillageDAOImpl implements VillageDAO {

	private static final Logger log = Logger.getLogger(VillageDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DraftUtils draftUtils;

	@Override
	public boolean save(Village village) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(village);
			tx.commit();
		} catch (Exception e) {
			log.error(e.toString());
			return false;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	@Override
	public boolean save(Village village, Session session) throws Exception {

		try {
			session.saveOrUpdate(village);
			session.flush();
			session.refresh(village);
			if (session.contains(village)) {
				session.evict(village);
			}
		} catch (Exception e) {
			log.error(e.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Village village, Session session) throws Exception {

		try {
		Query query=session.createSQLQuery("update village set village_name_local=:villageNameLocal, census_2001_code=:census2001Code where village_code=:villageCode and isactive=true");
		query.setParameter("villageNameLocal", village.getVillageNameLocal());	
		query.setParameter("census2001Code", village.getCensus2001Code());	
		query.setParameter("villageCode", village.getVillageCode());	
		int i=query.executeUpdate();
		if(i>0) {
			return true;
		}else {
			return false;
		}
		
		} catch (Exception e) {
			log.error(e.toString());
			return false;
		}
		
	}

	@Override
	public int getMaxVillageCode(String query) throws Exception {
		int maxCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query queryObj = session.createSQLQuery(query);
			List list = queryObj.list();
			maxCode = Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return maxCode;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getVillageDetailsModify(int villageCode, Session session) throws Exception {
		Filter filter = session.enableFilter("subdistrictFilter");
		filter.setParameter("isactive", true);
		Filter filter1 = session.enableFilter("districtFilter");
		filter1.setParameter("isactive", true);
		Query criteria = null;
		List<Village> villageList = null;
		try {
			criteria = session.createQuery("from Village where village_code=:villageCode and isactive=true");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			villageList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			session.disableFilter("subdistrictFilter");
			session.disableFilter("districtFilter");
		}
		return villageList;

	}

	@SuppressWarnings("unchecked")
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode, Session session) throws Exception {
		Query criteria = null;
		List<MapLandRegion> getMapDetailsModify = null;
		try {
			criteria = session.createQuery("from MapAttachment1 where map_attachment_code=:mapLandregionCode");
			criteria.setParameter("mapLandregionCode", mapLandregionCode);
			getMapDetailsModify = criteria.list();
		} catch (Exception e) {
			log.error(e.toString());
		}
		return getMapDetailsModify;
	}

	

	@Override
	public boolean update(String query, Session session) throws Exception {
		try {
			Query query1 = session.createSQLQuery(query);
			query1.executeUpdate();
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
			return true;
		} catch (Exception e) {
			log.error(e.toString());
		}

		return false;
	}

	@Override
	public boolean insertBulkVillage(String query, Session session) throws Exception {
		try {
			Query query1 = session.createSQLQuery(query);
			query1.executeUpdate();
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
			return true;
		} catch (Exception e) {
			log.error(e.toString());
		}

		return false;
	}

	// For Modify Village
	@Override
	public boolean modifyVillageInfo(VillageForm villageForm, VillagePK villagePk, int maplandRegionCode, Session session, HttpSession httpSession) throws Exception {
		// TODO Auto-generated method stub
		Timestamp timestamp = CurrentDateTime.getCurrentTimestamp();
		try {

			Village village = new Village();
			String s = null;
			village = (Village) session.get(Village.class, villagePk);
			if (villageForm.getSscode() != null && !villageForm.getSscode().isEmpty()) {
				s = (String) villageForm.getSscode().toString();
				village.setSscode(s);
			}
			if (villageForm.getCensus2011Code() != null) {
				village.setCensus2011Code(villageForm.getCensus2011Code());
			}
			if (villageForm.getCoordinates() != null) {
				village.setCoordinate(villageForm.getCoordinates());
			}
			village.setVillageStatus(villageForm.getVillageType());
			village.setWarningFlag(villageForm.getWarningFlag());

			village.setLastupdated(timestamp);
			session.update(village);
			httpSession.setAttribute("pk", villagePk);

		} catch (Exception e) {
			log.error(e.toString());
			return false;
		}
		return true;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getVillageList(Integer villageCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<Village> villageList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Village v where villageCode=:villageCode and isactive=true order by v.villageNameEnglish");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			villageList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return villageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getVillageListbySubDistrictCode(int subDistrictCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Village> villageList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Village v where v.tlc=:subDistrictCode and isactive=true order by v.villageNameEnglish");
			criteria.setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER);
			villageList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;

	}

	@SuppressWarnings("unchecked")
	public List<Village> getVillageListbySubDistrictCode(int subDistrictCode, Session session) throws Exception {

		Query criteria = null;
		List<Village> getVillageListbySubDistrictCode = null;

		try {
			criteria = session.createQuery("from Village v where v.tlc=:subDistrictCode and isactive=true order by v.villageNameEnglish");
			criteria.setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER);
			getVillageListbySubDistrictCode = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		}
		return getVillageListbySubDistrictCode;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Village> getVillageListDetails(String query) throws Exception {

		List<Village> villageList = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query criteria = session.createQuery(query);
			villageList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<VillagePartsBySurveyno> getListofSurvey(String query) throws Exception {
		Session session = null;
		Query criteria = null;
		List<VillagePartsBySurveyno> getListofSurvey = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			getListofSurvey = criteria.list();
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getListofSurvey;
	}

	@Override
	public List getSubType() throws Exception {
		Session session = null;
		Query criteria = null;
		List getSubType = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("TypeSub");
			getSubType = criteria.list();

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getSubType;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getDesignation() throws Exception {

		Session session = null;
		Query criteria = null;
		List getDesignation = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("GetDesignation");
			getDesignation = criteria.list();

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getDesignation;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getSubTier() throws Exception {

		Session session = null;
		Query criteria = null;
		List getSubTier = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("GetTier");
			getSubTier = criteria.list();

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getSubTier;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getListVillageDetails(String query) throws Exception {
		Query criteria = null;
		Session session = null;
		List<Village> villageList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			villageList = criteria.list();
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return villageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subdistrict> getSubdistrictDetails(int subdistrictCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Subdistrict> subdistrictList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Subdistrict where subdistrictCode=:subdistrictCode and isactive=true");
			criteria.setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER);
			subdistrictList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrictList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillagePartsBySurveyno> getSurveyListbyVillageCode(int villageCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<VillagePartsBySurveyno> villagePartsBySurveynoList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from VillagePartsBySurveyno sn where sn.village.villagePK.villageCode=:villageCode and sn.village.isactive=true");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			villagePartsBySurveynoList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villagePartsBySurveynoList;

	}

	// for add village
	@Override
	public boolean updateLReplaces(int lrReplaces, VillagePK villagePk) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();

			Village village = (Village) session.load(Village.class, villagePk);
			village.setLrReplaces(lrReplaces);// .setLrPartCode(lrPartCode);
			session.update(village);
			tx.commit();

		} catch (Exception e) {
			log.error(e.toString());
			if (tx != null) {
				tx.rollback();
			}
			return false;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	// for add village
	@Override
	public boolean updateLReplacedBy(int lrReplacedby, VillagePK villagePk) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();

			Village village = (Village) session.load(Village.class, villagePk);
			village.setLrReplacedby(lrReplacedby);// .setLrPartCode(lrPartCode);
			session.update(village);
			tx.commit();
		} catch (Exception e) {
			log.error(e.toString());
			if (tx != null) {
				tx.rollback();
			}
			return false;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	// for Modify village
	@Override
	public boolean updateIsActive(VillagePK villagePk) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();

			Village village = (Village) session.load(Village.class, villagePk);
			village.setIsactive(false);
			session.update(village);
			tx.commit();
		} catch (Exception e) {
			log.error(e.toString());
			if (tx != null) {
				tx.rollback();
			}
			return false;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	// for Modify village
	@Override
	public boolean updateMapLanRegion(int mapCode, String coordinates, int villageCode, Session session) throws Exception {
		try {

			MapLandRegion mapLandRegion = (MapLandRegion) session.get(MapLandRegion.class, mapCode);
			mapLandRegion.setCoordinates(coordinates);
			mapLandRegion.setLandregionCode(villageCode);
			session.update(mapLandRegion);
		} catch (Exception e) {
			log.error(e.toString());
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getVillageListByVillCode(int villageCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Village> villageList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Village d where d.isactive=true and d.villagePK.villageCode=:villageCode");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			villageList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;

	}

	@SuppressWarnings("unchecked")
	public List<Village> getVillageViewList(String queryStr, int limit, int offset) throws Exception {
		Query query = null;
		Session session = null;
		List<Village> getVillageViewList = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(queryStr);
			query.setFirstResult((offset - 1) * limit);
			query.setMaxResults(limit);
			getVillageViewList = query.list();
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getVillageViewList;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getVillageViewList(String sqlquery) throws Exception {
		List<Village> village = null;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(sqlquery);
			village = query.list();
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return village;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subdistrict> getSubdistrictDetails(String query) throws Exception {
		Session session = null;
		Query criteria = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			list = criteria.list();
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public int getMaxVillageVersionbyCode(int villageCode) throws Exception {
		// TODO Auto-generated method stub

		int maxVersionCode = 0;

		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select max(village_version) from village where isactive=true and village_code=:villageCode");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			maxVersionCode = Integer.parseInt(criteria.list().get(0).toString());
		} catch (HibernateException e) {
			log.error(e.toString());
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return maxVersionCode;

	}

	@Override
	public int getMaxVillageVersionbyVillageCode(int villageCode) throws Exception {

		int MaxVersionCode = 0;
		Query query = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select max(village_version) from village where isactive=true and village_code=:villageCode").setParameter("villageCode", villageCode, Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxVersionCode = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return MaxVersionCode;

	}

	@Override
	public int getMaxvillageVersion(int villageCode, Session session) throws Exception {
		// TODO Auto-generated method stub

		int MaxVersionCode = 0;

		Query criteria = null;
		try {
			try {
				criteria = session.createSQLQuery("select max(village_version) from village where isactive=true and village_code=:villageCode");
				criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
				@SuppressWarnings("rawtypes")
				List list = criteria.list();
				if (list != null && list.get(0) != null) {
					MaxVersionCode = Integer.parseInt(list.get(0).toString());
				}
			} catch (HibernateException e) {
				log.error(e.toString());
			}

		} catch (Exception e) {
			log.error(e.toString());
		}

		return MaxVersionCode;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<GovernmentOrder> governmentOrderList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from GovernmentOrder where order_code=:orderCode");
			criteria.setParameter("orderCode", orderCode, Hibernate.INTEGER);
			governmentOrderList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return governmentOrderList;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public int getVillageVersion(int vCode) throws Exception {
		int villcode = 0;
		List villageCode;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select village_version from village d where d.isactive=true and d.village_code=:VillageCode");
			query.setParameter("VillageCode", vCode, Hibernate.INTEGER);
			villageCode = query.list();
			if (!villageCode.isEmpty() && villageCode.get(0) != null) {
				villcode = (Integer) villageCode.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return villcode;
	}

	@Override
	public int getVillageVersion(int vCode, Session session) throws Exception {
		int villcode = 0;
		@SuppressWarnings("rawtypes")
		List villageCode;

		try {
			Query query = session.createSQLQuery("select village_version from village d where d.isactive=true and d.village_code=:VillageCode");
			query.setParameter("VillageCode", vCode, Hibernate.INTEGER);
			villageCode = query.list();
			if (!villageCode.isEmpty() && villageCode.get(0) != null) {
				villcode = (Integer) villageCode.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
		}
		return villcode;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getSubDistrictListbyDistrict(int districtCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<Village> villageList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("Village where villageCode=:districtCode and isactive=true");
			criteria.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			villageList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;

	}

	@SuppressWarnings("unchecked")
	public List<VillageHistory> getVillageHistoryDetail(char villageNameEnglish, int villageCode) throws Exception {
		Session session = null;

		Query query = null;
		List<VillageHistory> villagelist = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getVillageHistoryDetails").setParameter("villageNameEnglish", villageNameEnglish).setParameter("villageCode", villageCode);
			villagelist = query.list();
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villagelist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion,int minorVersion, Session session) throws Exception {
		Session session1 = null;
		session1 = sessionFactory.openSession();
		session1.beginTransaction();
		Query query = null;
		Filter filter = session1.enableFilter("subdistrictFilter");
		filter.setParameter("isactive", true);
		Filter filter1 = session1.enableFilter("districtFilter");
		filter1.setParameter("isactive", true);
		Filter filter2 = session1.enableFilter("villageFilter");
		filter2.setParameter("isactive", true);
		Filter filter3 = session1.enableFilter("stateFilter");
		filter3.setParameter("isactive", true);
		List<GetGovernmentOrderDetail> govOrderDetail = null;
		try {
			query = session1.getNamedQuery("GovOrderDetailMinor").setParameter("entityType", entityType).setParameter("entityCode", entityCode).setParameter("entityVersion", entityVersion).setParameter("minorVersion", minorVersion);
			govOrderDetail = query.list();

		} catch (Exception e) {
			log.error(e.toString());

		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
		}
		return govOrderDetail;

	}

	/**
	 * 
	 * Add Some Parameters for Rename Draft Village Mode on 10-03-2015
	 */
	public synchronized String ChangeVillage(int villageCode, int userId, String villageNameEnglish, Date date, String villageNameLocal, String aliasEnglish, String aliasLocal, Session session, String orderno, Date orderdate, Date gazpubdate,
			Character operationState, String isExistGovtOrder, Integer orderCode,String govtOrderConfig) throws Exception {
		int flagcode = 11;
		try {
			
			Query query = session.getNamedQuery("VillageChangeQuery").setParameter("villageCode", villageCode, Hibernate.INTEGER).setParameter("userId", userId, Hibernate.INTEGER)
					.setParameter("villageNameEnglish", villageNameEnglish, Hibernate.STRING).setParameter("effectiveDate", date, Hibernate.DATE).setParameter("order_no", orderno, Hibernate.STRING)
					.setParameter("order_date", orderdate, Hibernate.DATE).setParameter("flag_code", flagcode, Hibernate.INTEGER).setParameter("villageNameLocal", villageNameLocal, Hibernate.STRING)
					.setParameter("aliasEnglish", aliasEnglish, Hibernate.STRING).setParameter("aliasLocal", aliasLocal, Hibernate.STRING).setParameter("gaz_pubdate", gazpubdate, Hibernate.DATE).setParameter("operationState", operationState)
					.setParameter("isExistGovtOrder", isExistGovtOrder).setParameter("orderCode", orderCode).setParameter("govtOrderConfig",govtOrderConfig, Hibernate.STRING);
			ChangeVillage changevil = (ChangeVillage) query.list().get(0);
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
			return changevil.getChange_village_fn();

		} catch (Exception e) {
			log.error(e.toString());
			return null;

		}

	}

	@Override
	public void SetGovermentOrderEntity(String entityCode, char entityType) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction txn = null;
		@SuppressWarnings("unused")
		Query query = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			query = session.getNamedQuery("GovOrderEntityWiseQuery").setParameter("entityCode", entityCode, Hibernate.STRING).setParameter("entityType", entityType, Hibernate.CHARACTER);

			txn.commit();

		} catch (Exception e) {
			log.error(e.toString());
			txn.rollback();
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getVillageListByMaxVersion(int villageCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Village> villageList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Village d where d.villagePK.villageCode=:villageCode and d.villagePK.villageVersion=(select max(d.villagePK.villageVersion)from Village d where d.villagePK.villageCode=:villageCode)");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			villageList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;

	}

	@Override
	public boolean invalidateVillage(int villageCodetobeMerge, String VillagestoInvalidated, int roleCode) throws Exception {

		Session session = null;
		Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			query = session.getNamedQuery("invalidateVillageFn");
			query.setParameter("villageCode", villageCodetobeMerge, Hibernate.INTEGER);
			query.setParameter("villages", VillagestoInvalidated, Hibernate.STRING);
			query.setParameter("roleCode", roleCode, Hibernate.INTEGER);
			txn.commit();
			return true;

		} catch (Exception e) {
			log.error(e.toString());
			txn.rollback();
			return false;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	/**
	 * Changes for invalidate draft mode village by ripunj on 19-03-2015
	 */
	@Override
	public String invalidateVillageDAO(String villCode, Integer userId, String orderNumber, Date orderDate, Date effectiveDate, String govtOrder, Date gzbDate, String destinationVillage, Integer distinationSubdistrict, Session session, Integer ulb,
			Integer orderCode, String isExistGovtOrder, Character operationState) throws Exception {
		Query criteria = null;
		Transaction tx = null;
		String invalidateVillageList = null;
		String[] villages = null;
		int villageCode = 0;
		int vilVersion = 1;
		int ulbVersion = 1;
		int i = 0;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			if (ulb != null && ulb > 0) {
				villages = villCode.split(",");
				int len = villages.length;
				InvalidateVillageUlbMerge[] blvil = new InvalidateVillageUlbMerge[len];
				for (i = 0; i < len; i++) {
					blvil[i] = new InvalidateVillageUlbMerge();
					villageCode = Integer.parseInt(villages[i]);
					criteria = session.createSQLQuery("select max(village_version) from village where isactive=true and village_code=:villageCode");
					criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
					vilVersion = Integer.parseInt(criteria.list().get(0).toString());

					criteria = session.createSQLQuery("select max(local_body_version) from localbody where isactive=true and local_body_code=:ulbCode");
					criteria.setParameter("ulbCode", ulb, Hibernate.INTEGER);
					ulbVersion = Integer.parseInt(criteria.list().get(0).toString());

					blvil[i].setVillageCode(villageCode);
					blvil[i].setUlbCode(ulb);
					blvil[i].setUlbVersion(ulbVersion + 1);
					blvil[i].setVillageVersion(vilVersion + 1);
					session.save(blvil[i]);
				}
			}

			criteria = session.getNamedQuery("invalidate_village_fn");
			criteria.setParameter("villageCode", villCode, Hibernate.STRING);
			criteria.setParameter("userId", userId, Hibernate.INTEGER);
			criteria.setParameter("orderNumber", orderNumber, Hibernate.STRING);
			criteria.setParameter("orderDate", orderDate, Hibernate.DATE);
			criteria.setParameter("effectiveDate", effectiveDate, Hibernate.DATE);
			criteria.setParameter("govtOrder", govtOrder, Hibernate.STRING);
			criteria.setParameter("gzbDate", gzbDate, Hibernate.DATE);
			criteria.setParameter("destinationVillageList", destinationVillage, Hibernate.STRING);
			criteria.setParameter("distinationSubdistrict", distinationSubdistrict, Hibernate.INTEGER);
			criteria.setParameter("orderCode", orderCode, Hibernate.INTEGER);
			criteria.setParameter("isExistGovtOrder", isExistGovtOrder, Hibernate.STRING);
			criteria.setParameter("operationState", operationState, Hibernate.CHARACTER);
			InvalidateVillage changevil = (InvalidateVillage) criteria.list().get(0);
			invalidateVillageList = changevil.getInvalidate_village_fn();

			tx.commit();
			return invalidateVillageList;

		} catch (Exception e) {
			log.error(e.toString());
			if (tx != null) {
				tx.rollback();
			}
			return invalidateVillageList;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean saveSurveynos(String surveyNos, int villageCode, int villageVersion) throws Exception {
		// TODO saveSurveynos
		VillagePartsBySurveyno villagePartsBySurveynoBean = null;
		villagePartsBySurveynoBean = new VillagePartsBySurveyno();
		Village village = null;
		village = new Village();

		Session session = null;
		Transaction tx = null;
		try {
			VillagePK villagePk = new VillagePK(villageCode, villageVersion,1);
			village.setVillagePK(villagePk);
			villagePartsBySurveynoBean.setSurveyNumber(surveyNos);
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(villagePartsBySurveynoBean);
			tx.commit();

		} catch (Exception e) {
			log.error(e.toString());
			if (tx != null) {
				tx.rollback();
			}
			return false;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Village> getVillageListbySubDistrictWithSDName(int subDistrictCode) throws Exception {

		List<Village> villList = null;
		villList = new ArrayList<Village>();
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		String sdName = null;

		Query criteria = null;
		Query criteria1 = null;
		Session session = null;
		try {
			try {
				session = sessionFactory.openSession();
				criteria = session.createQuery("from Subdistrict sd where sd.isactive=true and tlc=:tlc");
				criteria.setParameter("tlc", subDistrictCode, Hibernate.INTEGER);
				sdList = criteria.list();

				sdName = sdList.get(0).getSubdistrictNameEnglish();

				criteria1 = session.createQuery("from Village where isactive=true and tlc=:tlc order by villageNameEnglish");
				criteria1.setParameter("tlc", subDistrictCode, Hibernate.INTEGER);
				villList = criteria1.list();
			} catch (HibernateException e) {

				log.error(e.toString());
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}

			for (int i = 0; i < villList.size(); i++) {
				villList.get(i).setVillageNameEnglish(villList.get(i).getVillageNameEnglish() + " (" + sdName + ")");
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		return villList;
	}

	@Override
	public List<Village> getVillageList(String entityCode, int type) throws Exception {
		List<Village> villList = new ArrayList<Village>();
		Session session = null;
		Query query = null;
		String sql = null;
		try {

			session = sessionFactory.openSession();
			if (type == 1) {
				sql = "SELECT  distinct village_name_english from village where village_code IN (:entityCode )";
			} else if (type == 2) {
				sql = "SELECT  distinct village_name_english from village where village_code IN (:entityCode ) and isactive=true";
			} else if (type == 3) {
				sql = "SELECT  distinct subdistrict_name_english from subdistrict where subdistrict_code IN (:entityCode )";
			} else if (type == 4) {
				sql = "SELECT  distinct subdistrict_name_english from subdistrict where subdistrict_code IN (:entityCode ) and isactive=true";
			} else if (type == 5) {
				sql = "SELECT  distinct district_name_english from district where district_code IN (:entityCode )";
			} else if (type == 6) {
				sql = "SELECT  distinct district_name_english from district where district_code IN (:entityCode ) and isactive=true";
			}
			query = session.createSQLQuery(sql);
			query.setParameter("entityCode", entityCode);
			List<Object[]> temp = query.list();

			for (Object obj : temp) {
				Village vill = new Village();
				vill.setVillageNameEnglish((String) obj);
				villList.add(vill);
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villList;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<StateWiseEntityDetails> getStateWiseVillageList(Integer statecode, char EntityType, Integer villageCode, String villageName, Integer limit, Integer offset) throws Exception {
		Session session = null;
		Query query = null;
		List<StateWiseEntityDetails> villagelist = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("globalViewEntity")
					.setParameter("statecode", statecode, Hibernate.INTEGER)
					.setParameter("entitytype", EntityType, Hibernate.CHARACTER)
					.setParameter("villageCode", villageCode, Hibernate.INTEGER)
					.setParameter("villageName", villageName, Hibernate.STRING)
					.setParameter("limit", limit, Hibernate.INTEGER)
					.setParameter("offset", offset, Hibernate.INTEGER);
			villagelist = query.list();
		} catch (Exception e) {
			log.error(e.toString());
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
			query = session.getNamedQuery("getParentWiseEntityDetails")
					.setParameter("entitytype", entitytype, Hibernate.CHARACTER)
					.setParameter("parentCode", parentCode, Hibernate.INTEGER)
					.setParameter("limit", limit, Hibernate.INTEGER)
					.setParameter("offset", offset, Hibernate.INTEGER);
			villagelist = query.list();
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villagelist;
	}

	/**
	 * Add param @stateCode to save the draft Village Details.
	 */
	@Override
	public String insertVillage(Integer stateCode, VillageForm villageForm, HttpServletRequest request, HttpSession session) {
		Session hibsession = null;
		String resultFlag = null;
		Integer operationCode = Integer.parseInt(VillageConstant.CREATE_VILLAGE_OP_CODE.toString());
		Integer flagCode = null;
		try {

			Date effectiveDate = villageForm.getEffectiveDate();
			Date gazPubDate = villageForm.getGazPubDate();
			Date orderDate = villageForm.getOrderDate();
			hibsession = sessionFactory.openSession();
			final Integer subDistrict = Integer.parseInt(villageForm.getSubdistrictNameEnglish());
			final String villageNameEng = villageForm.getNewVillageNameEnglish();
			final String orderNo = villageForm.getOrderNo();
			final java.sql.Timestamp ordertimeStampDate = new Timestamp(orderDate.getTime());
			final java.sql.Timestamp effectivetimeStampDate = new Timestamp(effectiveDate.getTime());
			final String govtOrder = villageForm.getGovtOrderConfig();
			java.sql.Timestamp gazPubtimeStampDateTemp = null;
			if (gazPubDate != null) {
				gazPubtimeStampDateTemp = new Timestamp(gazPubDate.getTime());
			}
			final java.sql.Timestamp gazPubtimeStampDate = gazPubtimeStampDateTemp;

			final String villageNameLocale = villageForm.getNewVillageNameLocal();
			final String villageNameAliasEn = villageForm.getNewVillageAliasEnglish();
			final String villageNameAliasLocal = villageForm.getNewVillageAliasLocal();
			String villageType = String.valueOf(villageForm.getVillageType());
			

			String census = null;

			final String ssCode = villageForm.getSscode();
			final String[] survayNumberArray = villageForm.getSurveyNumber();
			int countsurvay = 1;
			StringBuffer sbServay = new StringBuffer();
			for (int i = 0; i < survayNumberArray.length; i++) {
				if (countsurvay == 1) {
					String temp = survayNumberArray[i];
					sbServay.append(temp);
					countsurvay++;
				} else {
					sbServay.append(",");
					String temp = survayNumberArray[i];
					sbServay.append(temp);
				}
			}
			final String survayNumber = sbServay.toString();

			String lati = villageForm.getLati();
			String longi = villageForm.getLongi();
			StringBuffer sb=null;
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
			
			int flag = 0;
			String gisNodes = sb!=null?sb.toString():null;
			final String upLoadMap = villageForm.getFileMapUpLoad();
			final boolean byUlb = villageForm.isCreateFromCoverageOfUrbanLocalBody();
			final boolean byNew = villageForm.isCreateFromNewLand();
			final boolean byExiting = villageForm.isCreateFromExistingVillages();
			String formationType = "";
			if (byUlb) {
				formationType = "U";
			}
			if (byNew) {
				formationType = "N";
			}
			if (byExiting) {
				formationType = "V";
			}

			final String formationTypeString = formationType;
			String ulbCode = villageForm.getSelectedCoveredLandRegionByULB();
			if (formationType.equals("U")) {
				ulbCode = ulbCode.trim();
				ulbCode = ulbCode.replaceAll("ART", "");
				ulbCode = ulbCode.replaceAll("ULL", "");
			}
			final String ulbCodeValid = ulbCode;
			String fullContributedVillageTemp = null;
			String partContributedVillageTemp = null;
			if (formationType.equals("V")) {
				StringBuffer fullContributedVillages = new StringBuffer();

				StringBuffer partContributedVillages = new StringBuffer();
				String contributedVillage = villageForm.getContributedVillages();
				contributedVillage = contributedVillage.trim();
				String[] contributedVillageArray = contributedVillage.split(",");
				int countcon = 1;
				for (int i = 0; i < contributedVillageArray.length; i++) {
					if (contributedVillageArray[i].contains("FULL")) {
						if (countcon == 1) {
							String sub = contributedVillageArray[i].replaceAll("FULL", "");
							fullContributedVillages.append(sub);
							countcon++;
						} else {
							fullContributedVillages.append(",");
							String sub = contributedVillageArray[i].replaceAll("FULL", "");
							fullContributedVillages.append(sub);
						}
					}
				}
				countcon = 1;
				for (int i = 0; i < contributedVillageArray.length; i++) {
					if (contributedVillageArray[i].contains("PART")) {
						if (countcon == 1) {
							String sub2 = contributedVillageArray[i].replaceAll("PART", "");
							partContributedVillages.append(sub2);
							countcon++;
						} else {
							partContributedVillages.append(",");
							String sub2 = contributedVillageArray[i].replaceAll("PART", "");
							partContributedVillages.append(sub2);
						}
					}
				}
				fullContributedVillageTemp = fullContributedVillages.toString();
				if (("").equals(fullContributedVillageTemp)) {
					fullContributedVillageTemp = null;
				}
				partContributedVillageTemp = partContributedVillages.toString();
				if (("").equals(partContributedVillageTemp)) {
					partContributedVillageTemp = null;
				}
			}
			if (villageType != null && villageType.isEmpty()) {
				villageType = "I";
			}

			final String fullContributedVillage = fullContributedVillageTemp;
			final String surveyNumberList = villageForm.getContributedSurvey();
			final String renameIdVillageList = villageForm.getRenameIdVillageList();
			String renameNameVillageList = villageForm.getRenameNameVillageList();
			String[] partcontributedVillages = null;
			if (partContributedVillageTemp != null) {
				partcontributedVillages = partContributedVillageTemp.split(",");
			}
			String partUnchangedNameVillages = "";
			if (renameIdVillageList != null) {
				String[] partcontributedVillage = renameIdVillageList.split(",");
				for (String object1 : partcontributedVillages) {
					flag = 0;
					for (String object2 : partcontributedVillage) {
						if (object1.equals(object2)) {
							flag = 1;
							break;
						}

					}
					if (flag == 0) {
						partUnchangedNameVillages = partUnchangedNameVillages + object1 + ",";
					}
				}
				if (partUnchangedNameVillages.length() > 0) {
					partUnchangedNameVillages = partUnchangedNameVillages.substring(0, partUnchangedNameVillages.length() - 1);
				}
			} else
				partUnchangedNameVillages = partContributedVillageTemp;
			if (villageForm.isCreateFromNewLand()) {
				flagCode = Integer.parseInt(VillageConstant.CREATE_VILLAGE_NEW_LAND_FLAG_CODE.toString());
			} else if (villageForm.isCreateFromExistingVillages()) {
				flagCode = Integer.parseInt(VillageConstant.CREATE_VILLAGE_EXISTING_VILLAGE_FLAG_CODE.toString());
			} else if (villageForm.isCreateFromCoverageOfUrbanLocalBody()) {
				flagCode = Integer.parseInt(VillageConstant.CREATE_VILLAGE_URBAN_VILLAGE_FLAG_CODE.toString());
			}
			if (renameNameVillageList != null) {
				String[] vc = renameNameVillageList.split(",");
				String[] vname = renameIdVillageList.split(",");
				renameNameVillageList = null;
				String temp = new String();
				for (int i = 0; i < vc.length; i++) {
					temp = vname[i] + ":" + vc[i] + ",";
					renameNameVillageList = renameNameVillageList + temp;
				}
				renameNameVillageList = renameNameVillageList.substring(4, renameNameVillageList.length() - 1);
			}
			if (partUnchangedNameVillages != null) {
				if (partUnchangedNameVillages.length() == 0) {
					partUnchangedNameVillages = null;
				}
			}
			if(census==null || "".equals(census)){
				census="000000";
			}
			Query query = null;
			VillageSaveBean villageSaveBean = new VillageSaveBean();
			
			query = hibsession.getNamedQuery("VillagePublishQuery")
					.setParameter("subDistrict", subDistrict, Hibernate.INTEGER)
					.setParameter("userId", villageForm.getUserId(), Hibernate.INTEGER)
					.setParameter("villageNameEnglish", villageNameEng, Hibernate.STRING)
					.setParameter("orderNo", orderNo, Hibernate.STRING)
					.setParameter("ordertimeStampDate", ordertimeStampDate, Hibernate.TIMESTAMP)
					.setParameter("effectivetimeStampDate", effectivetimeStampDate, Hibernate.TIMESTAMP)
					.setParameter("govtOrder", govtOrder, Hibernate.STRING)
					.setParameter("operationCode", operationCode, Hibernate.INTEGER)
					.setParameter("flagCode", flagCode, Hibernate.INTEGER)
					.setParameter("gazPubtimeStampDate", gazPubtimeStampDate, Hibernate.TIMESTAMP)
					.setParameter("villageNameLocale", villageNameLocale, Hibernate.STRING)
					.setParameter("villageNameAliasEn", villageNameAliasEn, Hibernate.STRING)
					.setParameter("villageNameAliasLocal", villageNameAliasLocal, Hibernate.STRING)
					.setParameter("villageType", villageType, Hibernate.STRING)
					.setParameter("census", census, Hibernate.STRING)
					.setParameter("ssCode", ssCode, Hibernate.STRING)
					.setParameter("survayNumber", survayNumber, Hibernate.STRING)
					.setParameter("gisNodes", gisNodes, Hibernate.STRING)
					.setParameter("upLoadMap", upLoadMap, Hibernate.STRING)
					.setParameter("formationTypeString", formationTypeString, Hibernate.STRING)
					.setParameter("ulbCodeValid", ulbCodeValid, Hibernate.STRING)
					.setParameter("fullContributedVillage", fullContributedVillage, Hibernate.STRING)
					.setParameter("surveyNumberList", surveyNumberList, Hibernate.STRING)
					.setParameter("surveyNumberListOld", null, Hibernate.STRING)
					.setParameter("renameNameVillageList", renameNameVillageList, Hibernate.STRING)
					.setParameter("withoutRenameNameVillageList", partUnchangedNameVillages, Hibernate.STRING)
					.setParameter("renameIdVillageList", renameIdVillageList, Hibernate.STRING)
					.setParameter("operationState", villageForm.getButtonClicked(), Hibernate.CHARACTER)
					.setParameter("stateCode", stateCode, Hibernate.INTEGER)
					.setParameter("districtCode", Integer.parseInt(villageForm.getDistrictNameEnglish()), Hibernate.INTEGER)
					.setParameter("isExistGovtOrder", villageForm.getIsExistingGovtOrder(), Hibernate.STRING)
					.setParameter("orderCode", villageForm.getOrderCode(), Hibernate.INTEGER)
					.setParameter("isPesa", villageForm.getIsPesaStatus(), Hibernate.STRING)
					;
			villageSaveBean = (VillageSaveBean) query.list().get(0);

			hibsession.flush();
			if (hibsession.contains(query)) {
				hibsession.evict(query);
			}

			return villageSaveBean.getCreate_village_fn();
		} catch (Exception e) {
			log.error(e.toString());
			resultFlag = null;
		} finally {
			try {
					if (hibsession != null && hibsession.isOpen()) {
						hibsession.close();
					}
				
			} catch (Exception e) {
				resultFlag = null;
				log.error(e.toString());
			}
		}
		return resultFlag;
	}

	
	@SuppressWarnings("rawtypes")
	@Override
	public List<VillagePartsBySurveyno> getSurveyNumber(String surveyList) throws Exception {
		Session session = null;
List<VillagePartsBySurveyno> listData = new ArrayList<VillagePartsBySurveyno>();

		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select survey_code,survey_number FROM get_survey_numbers_fn('" + surveyList + "')");
			ArrayList arrayList = (ArrayList) query.list();
			for (Iterator iterator = arrayList.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				Integer surveyCode = (Integer) object[0];
				String surveyNumber = (String) object[1];
				VillagePartsBySurveyno villagePartsBySurveyno = new VillagePartsBySurveyno();
				villagePartsBySurveyno.setSurveyCode(surveyCode);
				villagePartsBySurveyno.setSurveyNumber(surveyNumber);
				listData.add(villagePartsBySurveyno);
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listData;
	}

	@Override
	public boolean saveDataInMap(VillageForm villageForm, List<AttachedFilesForm> attachedList, HttpSession session) {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		MapAttachment attachment = null;
		Query query = null;
		boolean flag = true;
		VillagePK vpk = null;
		int vid;
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
					attachment.setMapEntityCode(villageForm.getVillageCode());
					attachment.setMapEntityType('V');
					sessionObj.save(attachment);
					sessionObj.flush();
					int atid = (int) attachment.getAttachmentId();
					vid = villageForm.getVillageCode();
					if (vid == 0) {
						vpk = (VillagePK) session.getAttribute("pk");
						query = sessionObj.createSQLQuery("UPDATE village set map_attachment_code=:mapCode where village_code=:villageCode and village_version=:villageVersion");
						query.setParameter("villageVersion", vpk.getVillageVersion());
					} else {
						query = sessionObj.createSQLQuery("UPDATE village set map_attachment_code=:mapCode where village_code=:villageCode");
						
					}
					query.setParameter("mapCode", atid);
					query.setParameter("villageCode", vpk.getVillageCode());
					query.executeUpdate();
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;

	}

	/**
	 * Save the attachment Details and update attachment details of entity
	 */
	@Override
	public boolean saveDataInMapDraftVillageModify(VillageForm villageForm, List<MapAttachment> attachedList, HttpSession session) throws Exception {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		MapAttachment attachment = null;
		Query query = null;
		boolean flag = true;
		VillagePK vpk = null;
		int vid;
		try {
			if (attachedList != null) {
				Iterator<MapAttachment> it = attachedList.iterator();
				while (it.hasNext()) {
					MapAttachment filesForm = (MapAttachment) it.next();
					attachment = new MapAttachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileContentType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestamp());
					attachment.setMapEntityCode(villageForm.getVillageCode());
					attachment.setMapEntityType('V');
					sessionObj.save(attachment);
					sessionObj.flush();
					int atid = (int) attachment.getAttachmentId();
					vid = villageForm.getVillageCode();
					if (vid == 0) {
						vpk = (VillagePK) session.getAttribute("pk");
						query = sessionObj.createSQLQuery("UPDATE village set map_attachment_code=:mapCode where village_code=:villageCode and village_version=:villageVersion");
						query.setParameter("villageVersion", vpk.getVillageVersion());
					} else
						{
						query = sessionObj.createSQLQuery("UPDATE village set map_attachment_code=:mapCode where village_code=:villageCode");
						}
					query.setParameter("mapCode", atid);
					query.setParameter("villageCode", vpk.getVillageCode());
					query.executeUpdate();
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
			}
			sessionObj.close();
		}
		return flag;

	}

	@Override
	public boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm, List<AttachedFilesForm> attachedList, int vilcode, HttpSession session) {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Attachment attachment = null;
		boolean flag = true;
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
				
						GovernmentOrder govorder = new GovernmentOrder();
						govorder.setOrderCode(governmentOrderForm.getOrderCode());
						attachment.setGovernmentOrder(govorder);
					
					sessionObj.save(attachment);
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;
	}

	/**
	 * Save Modified Village Govt Order Details.
	 */
	@Override
	public boolean saveDataInAttachDraftVillageModify(GovernmentOrderForm governmentOrderForm, List<Attachment> attachedList, int vilcode, HttpSession session) throws Exception {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Attachment attachment = null;
		Query query = null;
		boolean flag = true;
		GovernmentOrderEntityWise goe = null;
		try {
			if (attachedList != null) {
				Iterator<Attachment> it = attachedList.iterator();
				while (it.hasNext()) {
					Attachment filesForm = (Attachment) it.next();
					attachment = new Attachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileContentType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestamp());
					query = sessionObj.createQuery("from GovernmentOrderEntityWise where entityCode=:VilCode and entityVersion=:ver and entityType=:type")
							.setParameter("VilCode", vilcode, Hibernate.INTEGER)
							.setParameter("ver", 1, Hibernate.INTEGER)
							.setParameter("type", 'V', Hibernate.CHARACTER);
					goe = (GovernmentOrderEntityWise) query.list().get(0);
					if (goe != null) {
						GovernmentOrder govorder = new GovernmentOrder();
						govorder.setOrderCode(goe.getOrderCode());
						attachment.setGovernmentOrder(govorder);
					}
					sessionObj.save(attachment);
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;
	}

	/**
	 * Changes on 16-09-2014 Add condition to check for existing name of village
	 * in village draft entity.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Character VilageExist(int b, String vilName) {
		Query criteria = null;
		Session session = null;
		Character status = '\0';
		try {
			session = sessionFactory.openSession();
			/*remove space remove  condition  REPLACE(v.villageNameEnglish,' ','') from code
			  Old One:.createQuery("from Village v where v.tlc=:b and isactive=true and UPPER(BTRIM(REPLACE(v.villageNameEnglish,' ',''))) =UPPER(BTRIM(REPLACE(:vilName,' ','')))");
			 */
			criteria = session
			.createQuery("from Village v where v.tlc=:b and isactive=true and UPPER(BTRIM(v.villageNameEnglish)) =UPPER(BTRIM(:vilName))");
			criteria.setParameter("b", b, Hibernate.INTEGER);
			criteria.setParameter("vilName", vilName, Hibernate.STRING);
			List list = criteria.list();

			criteria = session
					.createQuery("from VillageDraft v where v.subDistrict=:b and UPPER(BTRIM(v.villageNameEnglish)) =UPPER(BTRIM(:vilName))");
			criteria.setParameter("b", b, Hibernate.INTEGER);
			criteria.setParameter("vilName", vilName, Hibernate.STRING);
			List draftList = criteria.list();

			int size = list.size();
			int sizeDraft = draftList.size();
			/* Modified by Pooja on 21-07-2015 for display difft. error msg */
			if (size > 0) {
				status = 'P';
			}
			if (sizeDraft > 0) {
				status = 'S';
			}
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	/* Retrieve files from the attachment table */

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getAttacmentdetail(int orderCode) throws Exception {
		Session session = null;
		Query query = null;
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Attachment where announcement_id=:orderCode").setParameter("orderCode", orderCode, Hibernate.INTEGER);
			attachmentList = query.list();
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return attachmentList;
	}

	/* Retrieving the Map details attachment from the database */

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode, Session session) throws Exception {
		Query query = null;
		List<EntityWiseMapDetailsPojo> entityWiseMapDetail = null;
		try {
			query = session.getNamedQuery("getEntityWiseMapDetailsFn").setParameter("entityType", entityType).setParameter("entityCode", entityCode);
			entityWiseMapDetail = query.list();

		} catch (Exception e) {
			log.error(e.toString());

		}
		return entityWiseMapDetail;

	}

	@Override
	public int saveDataInAttachment(GovernmentOrderForm governmentOrderForm, List<AttachedFilesForm> validFileGovtUpload, HttpSession session, Session session2) {
		Attachment attachment = null;
		Long attId = null;
		try {
			if (validFileGovtUpload != null) {
				Iterator<AttachedFilesForm> it = validFileGovtUpload.iterator();
				while (it.hasNext()) {
					GovernmentOrder governmentOrder = new GovernmentOrder();
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					governmentOrder.setOrderCode(governmentOrderForm.getOrderCode());
					attachment.setGovernmentOrder(governmentOrder);
					attachment = (Attachment) session2.merge(attachment);
					attId = attachment.getAttachmentId();

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());

		}
		return attId != null ? attId.intValue() : -1;
	}

	public PesApplicationMaster getApplicationURL(int applicationId) {
		// TODO Auto-generated method stub
		Session session2 = null;
		//Transaction tx = null;
		PesApplicationMaster application = new PesApplicationMaster();
		try {
			session2 = HibernateSessionLGDRegistration.lgdReg();
			//tx = session2.beginTransaction();
			Query query = session2.getNamedQuery("getLGDRegistration").setParameter("applicationId", applicationId);
			application = (PesApplicationMaster) query.list().get(0);

		} catch (Exception e) {
			log.error(e.toString());
		}
		return application;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getVillageByBlockList(String blockcode) throws Exception {
		Session session = null;
		Query query = null;
		List<Village> finalVillageList = null;
		List<Object[]> objects = null;
		Integer vilId = null;
		String blkName = null;
		String villageNameEnglish = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select distinct bv.vlc from  block_village bv where bv.blc in (:blockcode) and bv.isactive=true");
			query.setParameter("blockcode", blockcode);
			List<Object> villageBlockList = query.list();
			List<Integer> villList = new ArrayList<Integer>();
			for (Object obj : villageBlockList) {
				vilId = (Integer) obj;
				villList.add(vilId);
			}
			if (villList.size() > 0) {
				query = session.createSQLQuery("select * from  block bv where bv.blc in (:blockcode) and bv.isactive=true");
				query.setParameter("blockcode", blockcode);
				objects = query.list();
				for (Object[] obj : objects) {
					blkName = (String) obj[2];

				}
				Query query1 = session.createQuery("from Village v where v.vlc in(:villcode) and v.isactive=true order by v.villageNameEnglish");
				query1.setParameterList("villcode", villList);
				finalVillageList = query1.list();
				for (int i = 0; i < finalVillageList.size(); i++) {
					villageNameEnglish = finalVillageList.get(i).getVillageNameEnglish() + " [" + blkName + "]";
					finalVillageList.get(i).setVillageNameEnglish(villageNameEnglish);
				}

			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return finalVillageList;
	}

	/**
	 * Modified by Ripunj on 05-01-2015 for LocalBody Draft mode
	 * 
	 */
	@Override
	public List<Village> getmapUnmapVillageList(int slc, int dlc, int tlc, int type) throws Exception {
		Session session = null;
		List<Village> villList = new ArrayList<Village>();
		Query query = null;
		Integer vilcode = null;
		String vilName = null;
		String lbName = null;
		Character operation_state;
		try {
			session = sessionFactory.openSession();
			if (type == 1) {
				query = session
						.createSQLQuery("select distinct case when d.village_code  in (select * from get_draft_used_lb_lr_temp(d.village_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state, "
								+ "d.village_code, d.village_name_english Village_Name,f.local_body_name_english Gram_Panchayat_Name from state a, district b, subdistrict c, village d left outer join lb_covered_landregion e on d.VLC=e.LRLC  "
								+ "and e.land_region_type='V' and e.isactive='t' left outer join localbody f on e.lb_covered_region_code=f.lb_covered_region_code and f.isactive='t' "
								+ "and f.local_body_type_code in (select lbt.local_body_type_code from local_body_type lbt where level = 'V' or category='U') where a.SLC = b.SLC and a.isactive = true and b.isactive='t' and b.DLC=c.DLC and c.isactive='t' and c.TLC=d.TLC and d.isactive='t' "
								+ "and b.district_code=:dlc and c.subdistrict_code=:tlc and a.slc=:slc and e.coverage_type is not null and f.local_body_name_english is not null order by d.village_name_english");
				query.setParameter("slc", slc);
				query.setParameter("dlc", dlc);
				query.setParameter("tlc", tlc);
				@SuppressWarnings("unchecked")
				List<Object[]> objects = query.list();
				for (Object[] obj : objects) {
					operation_state = (Character) obj[0];
					vilcode = (Integer) obj[1];
					vilName = (String) obj[2];
					lbName = (String) obj[3];
					vilName = vilName +"(Village Code-"+vilcode+ ")( GP - " + lbName + ")";
					Village villageObj = new Village();
					villageObj.setVillageCode(vilcode);
					villageObj.setVillageNameEnglish(vilName);
					villageObj.setOperation_state(operation_state);
					villList.add(villageObj);
				}
			} else {
				query = session.createSQLQuery("select distinct  case when d.village_code  in (select * from get_draft_used_lb_lr_temp(d.village_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state, "
						+ " d.village_code, d.village_name_english Village_Name from state a,district b, subdistrict c, village d " + "where a.SLC = b.SLC and a.isactive = true and b.isactive and b.DLC=c.DLC and c.isactive "
						+ "and c.TLC=d.TLC and d.isactive and not exists (select e.vlc from (select distinct a.vlc from village a,localbody b , lb_covered_landregion c "
						+ "where b.lb_covered_region_code=c.lb_covered_region_code and c.lrlc=a.vlc and a.isactive and b.isactive and c.isactive "
						+ "and c.land_region_type='V' and b.slc=:slc and b.local_body_type_code in (select lbt.local_body_type_code from local_body_type lbt where level = 'V' or category='U') ) e "
						+ "where d.vlc=e.vlc ) and b.district_code=:dlc and c.subdistrict_code=:tlc and a.slc=:slc order by d.village_name_english");
				query.setParameter("slc", slc);
				query.setParameter("dlc", dlc);
				query.setParameter("tlc", tlc);
				@SuppressWarnings("unchecked")
				List<Object[]> objects = query.list();
				for (Object[] obj : objects) {
					operation_state = (Character) obj[0];
					vilcode = (Integer) obj[1];
					vilName = (String) obj[2];
					vilName = vilName +"(Village Code-"+vilcode+ ")(Unmapped)";
					Village villageObj = new Village();
					villageObj.setVillageCode(vilcode);
					villageObj.setVillageNameEnglish(vilName);
					villageObj.setOperation_state(operation_state);
					villList.add(villageObj);
				}
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villList;
	}

	@Override
	public List<Village> getVillageSuerveryNo(String vCode) throws Exception {
		Session session = null;
		List<Village> villList = new ArrayList<Village>();
		Query query = null;
		Integer vilcode = null;
		String suerveNo = null;
		String vilName = null;
		try {
			session = sessionFactory.openSession();
query = session.createSQLQuery("select  vp.vlc,v.village_name_english,vp.survey_number from village v, village_parts_by_surveyno vp  where v.vlc=vp.vlc and v.village_code in(" + vCode + ") and v.isactive and vp.isactive");
			@SuppressWarnings("unchecked")
			List<Object[]> objects = query.list();
			for (Object[] obj : objects) {
				vilcode = (Integer) obj[0];
				vilName = (String) obj[1];
				suerveNo = (String) obj[2];

				vilName = vilName + "-" + suerveNo;
				Village villageObj = new Village();
				villageObj.setVillageCode(vilcode);
				villageObj.setVillageNameEnglish(vilName);
				villList.add(villageObj);

			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villList;
	}

	@Override
	public List<Village> getVillagebysubdisrictCodes(String vCode) throws Exception {
		Session session = null;
		List<Village> villList = new ArrayList<Village>();
		Query query = null;
		Integer vilcode = null;
		String subdistirctname = null;
		String vilName = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select distinct  v.village_code,v.village_name_english,sd.subdistrict_name_english from village v, subdistrict sd  where sd.tlc=v.tlc and v.tlc in(" + vCode
					+ ") and v.isactive and sd.isactive order by sd.subdistrict_name_english,v.village_name_english");
			@SuppressWarnings("unchecked")
			List<Object[]> objects = query.list();
			for (Object[] obj : objects) {
				vilcode = (Integer) obj[0];
				vilName = (String) obj[1];
				subdistirctname = (String) obj[2];
				vilName = vilName + "(" + subdistirctname + ")";
				Village villageObj = new Village();
				villageObj.setVillageCode(vilcode);
				villageObj.setVillageNameEnglish(vilName);
				villList.add(villageObj);

			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villList;
	}
	
	@Autowired
	@Qualifier("sessionFactory") org.springframework.orm.hibernate3.LocalSessionFactoryBean sessionFactory1; //Suppose using hibernate 3

	/**
	 * Retrieve Village Details with its operation State(Save or Publish)
	 * 
	 * @param subDistrictCode
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getVillageListbySubDistrictCodeWithOperationState(int subDistrictCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Village> villageList = new ArrayList<Village>();
		Integer vilcode = null;
		String vilName = null;
		Character operation_state;
		String opState = null;
		try {
			session = sessionFactory.openSession();
			System.out.println(sessionFactory1.getHibernateProperties().get("hibernate.dialect"));
			criteria = session.createSQLQuery("select case when v.village_code  in (select * from get_draft_used_lb_lr_temp(v.village_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state,"
					+ " 'S' as opeartion_state,v.* from village_draft ,village v where tlc=:subDistrictCode and "
					+ " (vlc in( select  (CAST(regexp_split_to_table(withoutRenameNameVillageList, E'\\,') AS INT))) or vlc in( select  coalesce(village_rename_code,0) AS INT)"
					+ " or vlc in( select  (CAST(regexp_split_to_table(fullContributedVillage, E'\\,') AS INT)))or vlc in( select  (CAST(regexp_split_to_table(renameidvillagelist, E'\\,') AS INT)))) and subdistrict=tlc and isactive " + " union all"
					+ " select case when v.village_code  in (select * from get_draft_used_lb_lr_temp(v.village_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state,"
					+ " 'P' as opeartion_state,* from village v where tlc=:subDistrictCode and isactive and"
					+ " (vlc not in (select (CAST(regexp_split_to_table(withoutRenameNameVillageList, E'\\,') AS INT)) from village_draft t where subdistrict=:subDistrictCode)"
					+ " and  vlc not in (select (CAST(regexp_split_to_table(fullContributedVillage, E'\\,') AS INT)) from village_draft t where subdistrict=:subDistrictCode)"
					+ " and  vlc not in (select coalesce(village_rename_code,0) from village_draft t where subdistrict=:subDistrictCode) "
					+ " and  vlc not in (select (CAST(regexp_split_to_table(renameidvillagelist, E'\\,') AS INT)) from village_draft t where subdistrict=:subDistrictCode)) order by village_name_english");

			criteria.setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER);
			/*
			 * criteria.setResultTransformer(Transformers.aliasToBean(Village.class
			 * )); villageList = criteria.list();
			 */

			List<Object[]> objects = criteria.list();
			for (Object[] obj : objects) {
				operation_state = (Character) obj[0];
				opState = (String) obj[1];
				vilcode = (Integer) obj[2];
				vilName = (String) obj[4];
				Village villageObj = new Village();
				villageObj.setVillageCode(vilcode);
				villageObj.setVillageNameEnglish(vilName);
				villageObj.setOpeartion_state(opState);
				villageObj.setOperation_state(operation_state);
				villageList.add(villageObj);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;

	}

	/**
	 * Retrieve State Wise Village Draft Details. Changes for invalidate draft
	 * mode village by ripunj on 19-03-2015
	 * 
	 * @param stateCode
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDraft> getVillageDraftList(int stateCode) throws Exception {

		Query criteria = null;
		Query criteria1 = null;
		Session session = null;
		List<VillageDraft> villageList = new ArrayList<VillageDraft>();
		String invalidatedVillageNameEnglish = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(" from VillageDraft v where v.stateCode=:state_Code order by v.villageNameEnglish");
			criteria.setParameter("state_Code", stateCode);
			villageList = criteria.list();
			if (villageList != null && villageList.size() > 0) {
				for (int i = 0; i < villageList.size(); i++) {
					if (villageList.get(i).getOperationCode() == 14) {
						criteria1 = session
								.createSQLQuery("select cast (string_agg(village_name_english, ', ')as character varying) from village where village_code in (select  ((CAST(regexp_split_to_table(:villCode, E'\\,') AS INT)))) and isactive");
						criteria1.setParameter("villCode", villageList.get(i).getInvalidateVillageList(), Hibernate.STRING);
						invalidatedVillageNameEnglish = criteria1.list().get(0)!=null?criteria1.list().get(0).toString():null;
						villageList.get(i).setInvalidateVillageListNameEnglish(invalidatedVillageNameEnglish);
					}
				}
			}

		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageDraft> getVillageDraftDetails(int villageCode) throws Exception {
		Session session = sessionFactory.openSession();
		Filter filter = session.enableFilter("subdistrictFilter");
		filter.setParameter("isactive", true);
		Filter filter1 = session.enableFilter("districtFilter");
		filter1.setParameter("isactive", true);
		Query criteria = null;
		List<VillageDraft> villageList = null;
		try {

			criteria = session.createQuery("from VillageDraft where villageCode=:village_Code");
			criteria.setParameter("village_Code", villageCode, Hibernate.INTEGER);
			villageList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			session.disableFilter("subdistrictFilter");
			session.disableFilter("districtFilter");
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;

	}

	/**
	 * Save Govt Order Details in Village Draft Entity
	 * Query modified by pooja 0n 27-08-2015 for update govtOrder in Manage Village Change draft
	 */
	@Override
	public boolean saveDataInAttachDraftVilCreate(VillageForm villageForm, List<AttachedFilesForm> attachedList, int vilCode, HttpSession session) {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query = null;
		boolean flag = true;
		//int vid;
		try {
			
			for(AttachedFilesForm obj:attachedList ) {
				query = sessionObj.createSQLQuery("UPDATE village_draft set (govtorder,govt_order_file_name,govt_order_file_size,govt_order_file_content_type,govt_order_file_location,govt_order_file_timestamp)"
						+ " =(:govtOrderConfig,:fileName,:fileSize,:fileType,:fileLocation,:fileTimestampName where village_code=:villageCode)");
				query.setParameter("govtOrderConfig",villageForm.getGovtOrderConfig() );
				query.setParameter("fileName",obj.getFileName() );
				query.setParameter("fileSize",obj.getFileSize() );
				query.setParameter("fileType",obj.getFileType() );
				query.setParameter("fileLocation",obj.getFileLocation() );
				query.setParameter("fileTimestampName",obj.getFileTimestampName() );
				query.setParameter("villageCode",vilCode );
				query.executeUpdate();
				tx1.commit();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;

	}

	/**
	 * Changes for invalidate draft mode village by ripunj on 17-03-2015
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getDraftVillageListbySubDistrictCodeWithOperationState(int subDistrictCode, int draftvilCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Village> villageList = new ArrayList<Village>();
		Integer vilcode = null;
		String vilName = null;
		String opState = null;
		String vilPart = null;
		String vilFull = null;
		String renamenamevillageList = null;
		String renameidvillagelist = null;
		Character operation_state;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select case when v.village_code  in (select * from get_draft_used_lb_lr_temp(v.village_code,'V')) then  cast ('F' as character)"
					+ " else cast('A' as character) end as operation_state,'S' as opearation_state,'0' as withoutRenameNameVillageList,'0' as fullContributedVillage,'' as renamenamevillageList,"
					+ " '' as renameidvillagelist, v.* from village_draft vd,village v where tlc=:subDistrictCode and "
					+ "  vd.village_code!=:draftvilCode and (vlc in( select  (CAST(regexp_split_to_table(withoutRenameNameVillageList, E'\\,') AS INT)))"
					+ " or vlc in( select  (CAST(regexp_split_to_table(fullContributedVillage, E'\\,') AS INT))) or vlc in( select  (CAST(regexp_split_to_table(renameidvillagelist, E'\\,') AS INT))))" + " and subdistrict=tlc and isactive "
					+ " union all " + " select case when v.village_code  in (select * from get_draft_used_lb_lr_temp(v.village_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state,"
					+ " 'P' as opearation_state,'0'as withoutRenameNameVillageList,'0' as fullContributedVillage,'' as renamenamevillageList,'' as renameidvillagelist,v.* from village v where tlc=:subDistrictCode and isactive and"
					+ " (vlc not in (select (CAST(regexp_split_to_table(withoutRenameNameVillageList, E'\\,') AS INT)) from village_draft t where subdistrict=:subDistrictCode)"
					+ " and  vlc not in (select (CAST(regexp_split_to_table(fullContributedVillage, E'\\,') AS INT)) from village_draft t where subdistrict=:subDistrictCode)"
					+ " and  vlc not in (select (CAST(regexp_split_to_table(renameidvillagelist, E'\\,') AS INT)) from village_draft t where subdistrict=:subDistrictCode))" + " union all "
					+ " select case when v.village_code  in (select * from get_draft_used_lb_lr_temp(v.village_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state,"
					+ " 'M' as opearation_state,vd.withoutRenameNameVillageList,vd.fullContributedVillage,vd.renamenamevillageList,vd.renameidvillagelist,v.* from village_draft vd ,village v where tlc=:subDistrictCode"
					+ " and vd.village_code=:draftvilCode	and (vlc in( select  (CAST(regexp_split_to_table(withoutRenameNameVillageList, E'\\,') AS INT)))"
					+ " or vlc in( select  (CAST(regexp_split_to_table(fullContributedVillage, E'\\,') AS INT))) "
					+ " or vlc in( select  (CAST(regexp_split_to_table(renameidvillagelist, E'\\,') AS INT)))) and subdistrict=tlc and isactive order by village_name_english");

			criteria.setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER);
			criteria.setParameter("draftvilCode", draftvilCode, Hibernate.INTEGER);
			List<Object[]> objects = criteria.list();
			for (Object[] obj : objects) {
				operation_state = (Character) obj[0];
				opState = (String) obj[1];
				vilPart = (String) obj[2];
				vilFull = (String) obj[3];
				renamenamevillageList = (String) obj[4];
				renameidvillagelist = (String) obj[5];
				vilcode = (Integer) obj[6];
				vilName = (String) obj[8];
				Village villageObj = new Village();
				villageObj.setVillageCode(vilcode);
				villageObj.setOperation_state(operation_state);
				if (opState != null && opState.equalsIgnoreCase("M")) {
					if (vilPart != null && !vilPart.equalsIgnoreCase("")) {
						for (String retval : vilPart.split(",")) {
							if (vilcode == Integer.parseInt(retval)) {
								villageObj.setVillageNameEnglish(vilName + "(PART)");
								villageObj.setPartFullFlag("PART");
							}
						}
					}
					if (vilFull != null && !vilFull.equalsIgnoreCase("")) {
						for (String retval : vilFull.split(",")) {
							if (vilcode == Integer.parseInt(retval)) {
								villageObj.setVillageNameEnglish(vilName + "(FULL)");
								villageObj.setPartFullFlag("FULL");
							}
						}
					}
					if (renameidvillagelist != null && !renameidvillagelist.equalsIgnoreCase("")) {
						for (String retval : renameidvillagelist.split(",")) {
							if (vilcode == Integer.parseInt(retval)) {
								villageObj.setVillageNameEnglish(vilName + "(PART)");
								villageObj.setPartFullFlag("PART");
							}
						}
					}
					if (renamenamevillageList != null && !renamenamevillageList.equalsIgnoreCase("")) {
						villageObj.setRenameNameVillageList(renamenamevillageList);
					}
				} else {
					villageObj.setVillageNameEnglish(vilName);
				}
				villageObj.setOpeartion_state(opState);
				villageList.add(villageObj);
			}

		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;

	}

	@Override
	public boolean saveDataInMapDraftVilModify(VillageDataForm villageDataForm, HttpSession session) throws Exception {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query = null;
		boolean flag = true;
		VillagePK vpk = null;
		int vid;
		try {
			if (villageDataForm.getUpLoadMap() != null) {
				int atid = Integer.parseInt(villageDataForm.getUpLoadMap());
				vid = villageDataForm.getVillageCode();
				if (vid == 0) {
					vpk = (VillagePK) session.getAttribute("pk");
					query = sessionObj.createSQLQuery("UPDATE village set map_attachment_code=:mapCode where village_code=:villageCode and village_version=:villageVersion");
					query.setParameter("villageVersion", vpk.getVillageVersion());
				} else {
					query = sessionObj.createSQLQuery("UPDATE village set map_attachment_code=:mapCode where village_code=:villageCode");
				}
				query.setParameter("mapCode", atid);
				query.setParameter("villageCode", vpk.getVillageCode());	
				query.executeUpdate();
				tx1.commit();
			}
		} catch (Exception e) {
			log.error(e.toString());
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;

	}

	/*
	 * Publish Modify Village Draft Details into Village Entity.
	 * 
	 * @param stateCode
	 * 
	 * @param villageDraft -Village Draft Details
	 */
	@Override
	public String publishDraftVillageModify(Integer stateCode, VillageDraft villageDraft, HttpServletRequest request, HttpSession session) {
		Session hibsession = null;
		String resultFlag = null;
		try {

			Query query = null;
			VillageSaveBean villageSaveBean = new VillageSaveBean();
			hibsession = sessionFactory.openSession();
			query = hibsession.getNamedQuery("VillagePublishQuery")
					.setParameter("subDistrict",villageDraft.getSubDistrict(), Hibernate.INTEGER)
					.setParameter("userId", villageDraft.getUserId(), Hibernate.INTEGER)
					.setParameter("villageNameEnglish", villageDraft.getVillageNameEnglish(), Hibernate.STRING)
					.setParameter("orderNo", villageDraft.getOrderNo().toString(), Hibernate.STRING)
					.setParameter("ordertimeStampDate", villageDraft.getOrdertimeStampDate(), Hibernate.TIMESTAMP)
					.setParameter("effectivetimeStampDate", villageDraft.getEffectivetimeStampDate(), Hibernate.TIMESTAMP)
					.setParameter("govtOrder", villageDraft.getGovtOrder(), Hibernate.STRING)
					.setParameter("operationCode", villageDraft.getOperationCode(), Hibernate.INTEGER)
					.setParameter("flagCode", villageDraft.getFlagCode(), Hibernate.INTEGER)
					.setParameter("gazPubtimeStampDate", villageDraft.getGazPubtimeStampDate(), Hibernate.TIMESTAMP)
					.setParameter("villageNameLocale", villageDraft.getVillageNameLocale(), Hibernate.STRING)
					.setParameter("villageNameAliasEn", villageDraft.getVillageNameAliasEn(), Hibernate.STRING)
					.setParameter("villageNameAliasLocal", villageDraft.getVillageNameAliasLocal(), Hibernate.STRING)
					.setParameter("villageType", villageDraft.getVillageType(), Hibernate.STRING)
					.setParameter("census", villageDraft.getCensus(), Hibernate.INTEGER)
					.setParameter("ssCode", villageDraft.getSsCode(), Hibernate.STRING)
					.setParameter("survayNumber", villageDraft.getSurvayNumber(), Hibernate.STRING)
					.setParameter("gisNodes", villageDraft.getGisNodes(), Hibernate.STRING)
					.setParameter("upLoadMap", villageDraft.getUpLoadMap(), Hibernate.STRING)
					.setParameter("formationTypeString", villageDraft.getFormationTypeString(), Hibernate.STRING)
					.setParameter("ulbCodeValid", villageDraft.getUlbCodeValid(), Hibernate.STRING)
					.setParameter("fullContributedVillage", villageDraft.getFullContributedVillage(), Hibernate.STRING)
					.setParameter("surveyNumberList", villageDraft.getSurveyNumberList(), Hibernate.STRING)
					.setParameter("surveyNumberListOld", null, Hibernate.STRING)
					.setParameter("renameNameVillageList", villageDraft.getRenameNameVillageList(), Hibernate.STRING)
					.setParameter("withoutRenameNameVillageList", villageDraft.getWithoutRenameNameVillageList(), Hibernate.STRING)
					.setParameter("renameIdVillageList", villageDraft.getRenameIdVillageList(), Hibernate.STRING)
					.setParameter("operationState", 'P', Hibernate.CHARACTER)
					.setParameter("stateCode", stateCode, Hibernate.INTEGER)
					.setParameter("districtCode", villageDraft.getDistrictCode(), Hibernate.INTEGER)
					.setParameter("isExistGovtOrder", villageDraft.getIsExistGovtOrder(), Hibernate.STRING)
					.setParameter("orderCode", villageDraft.getOrdercode(), Hibernate.INTEGER)
					.setParameter("isPesa", villageDraft.getIsPesa(), Hibernate.STRING)
					;

			villageSaveBean = (VillageSaveBean) query.list().get(0);
			hibsession.flush();
			if (hibsession.contains(query)) {
				hibsession.evict(query);
			}

			return villageSaveBean.getCreate_village_fn();
		} catch (Exception e) {
			log.error(e.toString());
			resultFlag = null;
		} finally {
			try {
				if (hibsession != null && hibsession.isOpen()) {
					hibsession.close();
				}
			} catch (Exception e) {
				resultFlag = null;
				log.error(e.toString());
			}
		}
		return resultFlag;
	}

	/**
	 * Delete selected draft village
	 * 
	 * @param villageCode
	 */
	public String deleteDraftVillageModify(int villageCode, HttpServletRequest request, HttpSession session1) throws Exception {
		Session session = sessionFactory.openSession();
		Query query = null;
		//List<VillageDraft> villageList = null;
		//int row = 0;
		try {

			String sql = "DELETE FROM VillageDraft WHERE villageCode=:village_Code";
			query = session.createQuery(sql);
			query.setParameter("village_Code", villageCode);
			//row = 
			query.executeUpdate();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return "D";

	}

	/**
	 * To save or publish modify Village Draft Details.
	 * 
	 * @param stateCode
	 * @param vilForm
	 */

	@SuppressWarnings("unchecked")
	@Override
	public String insertVillageModify(Integer stateCode, VillageForm vilForm, HttpServletRequest request, HttpSession session) {
		Session hibsession = null;
		String resultFlag = null;
		Integer operationCode = Integer.valueOf(10);
		Integer flagCode = null;
		try {

			if (vilForm.getListVillageDetails() != null && vilForm.getListVillageDetails().size() > 0) {
				VillageDataForm villageForm = vilForm.getListVillageDetails().get(0);

				Date effectiveDate = villageForm.getOrdereffectiveDatecr();
				Date gazPubDate = villageForm.getGazPubDatecr();
				Date orderDate = villageForm.getOrderDatecr();
				hibsession = sessionFactory.openSession();
				final Integer subDistrict = villageForm.getSubdistrictCode();
				
				final String villageNameEng = villageForm.getVillageNameEnglish();
				final String orderNo = villageForm.getOrderNocr();
				final java.sql.Timestamp ordertimeStampDate = new Timestamp(orderDate.getTime());
				final java.sql.Timestamp effectivetimeStampDate = new Timestamp(effectiveDate.getTime());
				final String govtOrder = vilForm.getGovtOrderConfig();
				java.sql.Timestamp gazPubtimeStampDateTemp = null;
				if (gazPubDate != null) {
					gazPubtimeStampDateTemp = new Timestamp(gazPubDate.getTime());
				}
				final java.sql.Timestamp gazPubtimeStampDate = gazPubtimeStampDateTemp;
				final String villageNameLocale = villageForm.getVillageNameLocal();
				final String villageNameAliasEn = villageForm.getAliasEnglish();
				final String villageNameAliasLocal = villageForm.getAliasLocal();
				String villageType = String.valueOf(villageForm.getVillageStatus());
				int censusCodeTemp = 0;
				try {
					if (villageForm != null && villageForm.getCensus2011Code() != null && Integer.parseInt(villageForm.getCensus2011Code()) > 0) {
						censusCodeTemp = Integer.parseInt(villageForm.getCensus2011Code());
					}
				} catch (Exception e) {
					censusCodeTemp = 0;
				}

				final int census = censusCodeTemp;

				final String ssCode = villageForm.getSscode();
				final String[] survayNumberArray = vilForm.getSurveyNumber();
				int countsurvay = 1;
				StringBuffer sbServay = new StringBuffer();
				for (int i = 0; i < survayNumberArray.length; i++) {
					if (countsurvay == 1) {
						String temp = survayNumberArray[i];
						sbServay.append(temp);
						countsurvay++;
					} else {
						sbServay.append(",");
						String temp = survayNumberArray[i];
						sbServay.append(temp);
					}
				}
				final String survayNumber = sbServay.toString();

				String lati = vilForm.getLati();
				String longi = vilForm.getLongi();
				String gisNodes = null;
				int flag = 0;
				if (lati != null && longi != null) {
					String[] arrayLati = lati.split(",");
					String[] arrayLong = longi.split(",");
					StringBuffer sb = new StringBuffer();
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

					gisNodes = sb.toString();
					if (gisNodes.equals(":")) {
						gisNodes = null;
					}
				}
				final String upLoadMap = vilForm.getFileMapUpLoad();
				final boolean byUlb = vilForm.isCreateFromCoverageOfUrbanLocalBody();
				final boolean byNew = vilForm.isCreateFromNewLand();
				final boolean byExiting = vilForm.isCreateFromExistingVillages();
				String formationType = "";
				if (byUlb) {
					formationType = "U";
				}
				if (byNew) {
					formationType = "N";
				}
				if (byExiting) {
					formationType = "V";
				}

				final String formationTypeString = formationType;
				String ulbCode = vilForm.getSelectedCoveredLandRegionByULB();
				if (formationType.equals("U")) {
					ulbCode = ulbCode.trim();
					ulbCode = ulbCode.replaceAll("ART", "");
					ulbCode = ulbCode.replaceAll("ULL", "");
				}
				final String ulbCodeValid = ulbCode;
				String fullContributedVillageTemp = null;
				String partContributedVillageTemp = null;
				if (formationType.equals("V")) {
					StringBuffer fullContributedVillages = new StringBuffer();

					StringBuffer partContributedVillages = new StringBuffer();
					String contributedVillage = vilForm.getContributedVillages();
					contributedVillage = contributedVillage.trim();
					String[] contributedVillageArray = contributedVillage.split(",");
					int countcon = 1;
					for (int i = 0; i < contributedVillageArray.length; i++) {
						if (contributedVillageArray[i].contains("FULL")) {
							if (countcon == 1) {
								String sub = contributedVillageArray[i].replaceAll("FULL", "");
								fullContributedVillages.append(sub);
								countcon++;
							} else {
								fullContributedVillages.append(",");
								String sub = contributedVillageArray[i].replaceAll("FULL", "");
								fullContributedVillages.append(sub);
							}
						}
					}
					countcon = 1;
					for (int i = 0; i < contributedVillageArray.length; i++) {
						if (contributedVillageArray[i].contains("PART")) {
							if (countcon == 1) {
								String sub2 = contributedVillageArray[i].replaceAll("PART", "");
								partContributedVillages.append(sub2);
								countcon++;
							} else {
								partContributedVillages.append(",");
								String sub2 = contributedVillageArray[i].replaceAll("PART", "");
								partContributedVillages.append(sub2);
							}
						}
					}
					fullContributedVillageTemp = fullContributedVillages.toString();
					if (("").equals(fullContributedVillageTemp)) {
						fullContributedVillageTemp = null;
					}
					partContributedVillageTemp = partContributedVillages.toString();
					if (("").equals(partContributedVillageTemp)) {
						partContributedVillageTemp = null;
					}
				}
				if (villageType != null && villageType.isEmpty()) {
					villageType = "I";
				}

				final String fullContributedVillage = fullContributedVillageTemp;
				final String surveyNumberList = vilForm.getContributedSurvey();
				final String renameIdVillageList = vilForm.getRenameIdVillageList();
				String renameNameVillageList = vilForm.getRenameNameVillageList();
				String[] partcontributedVillages = null;
				if (partContributedVillageTemp != null) {
					partcontributedVillages = partContributedVillageTemp.split(",");
				}
				String partUnchangedNameVillages = "";
				if (renameIdVillageList != null) {
					String[] partcontributedVillage = renameIdVillageList.split(",");
					for (String object1 : partcontributedVillages) {
						flag = 0;
						for (String object2 : partcontributedVillage) {
							if (object1.equals(object2)) {
								flag = 1;
								break;
							}

						}
						if (flag == 0) {
							partUnchangedNameVillages = partUnchangedNameVillages + object1 + ",";
						}
					}
					if (partUnchangedNameVillages.length() > 0) {
						partUnchangedNameVillages = partUnchangedNameVillages.substring(0, partUnchangedNameVillages.length() - 1);
					}
				} else
					partUnchangedNameVillages = partContributedVillageTemp;
				if (vilForm.isCreateFromNewLand()) {
					flagCode = Integer.valueOf(40);
					partUnchangedNameVillages = null;
				} else if (vilForm.isCreateFromExistingVillages()) {
					flagCode = Integer.valueOf(39);
				} else if (vilForm.isCreateFromCoverageOfUrbanLocalBody()) {
					flagCode = Integer.valueOf(41);
					partUnchangedNameVillages = null;
				}
				if (renameNameVillageList != null) {
					String[] vc = renameNameVillageList.split(",");
					String[] vname = renameIdVillageList.split(",");
					renameNameVillageList = null;
					String temp = new String();
					for (int i = 0; i < vc.length; i++) {
						temp = vname[i] + ":" + vc[i] + ",";
						renameNameVillageList = renameNameVillageList + temp;
					}
					renameNameVillageList = renameNameVillageList.substring(4, renameNameVillageList.length() - 1);
				}
				if (partUnchangedNameVillages != null) {
					if (partUnchangedNameVillages.length() == 0) {
						partUnchangedNameVillages = null;
					}
				}
				Query query = null;
				VillageSaveBean villageSaveBean = new VillageSaveBean();
				if (vilForm.getButtonClicked() == 'S') {
					VillageDraft vd = new VillageDraft();
					vd.setVillageCode(villageForm.getVillageCode());
					vd.setSubDistrict(subDistrict);
					vd.setUserId(vilForm.getUserId());
					vd.setVillageNameEnglish(villageNameEng);
					vd.setOrderNo(orderNo.trim());
					vd.setOrdertimeStampDate(ordertimeStampDate);
					vd.setEffectivetimeStampDate(effectivetimeStampDate);
					vd.setGovtOrder(govtOrder);
					vd.setOperationCode(operationCode);
					vd.setFlagCode(flagCode);
					vd.setGazPubtimeStampDate(gazPubtimeStampDate);
					vd.setVillageNameLocale(villageNameLocale);
					vd.setVillageNameAliasEn(villageNameAliasEn);
					vd.setVillageNameAliasLocal(villageNameAliasLocal);
					vd.setVillageType(vilForm.getVillageStatus());
					vd.setCensus(String.valueOf(census));
					vd.setSsCode(ssCode);
					vd.setSurvayNumber(survayNumber);
					vd.setGisNodes(gisNodes);
					vd.setUpLoadMap(upLoadMap);
					vd.setFormationTypeString(formationTypeString);
					vd.setUlbCodeValid(ulbCodeValid);
					vd.setFullContributedVillage(fullContributedVillage);
					vd.setSurveyNumberList(surveyNumberList);
					vd.setSurveyNumberListOld(null);
					vd.setRenameNameVillageList(renameNameVillageList);
					vd.setWithoutRenameNameVillageList(partUnchangedNameVillages);
					vd.setRenameIdVillageList(renameIdVillageList);
					vd.setStateCode(stateCode);
					vd.setDistrictCode(Integer.parseInt(vilForm.getDistrictNameEnglish()));
					vd.setIsExistGovtOrder(vilForm.getIsExistingGovtOrder());
					vd.setOrdercode(vilForm.getOrderCode());
					Attachment atDetails = new Attachment();
					MapAttachment mapAtDetails = new MapAttachment();
					if (request.getAttribute("attachmentList") != null && vilForm.getGovtfilecount().equalsIgnoreCase("1")) {
						List<Attachment> atList = (ArrayList<Attachment>) request.getAttribute("attachmentList");
						atDetails = atList.get(0);
						vd.setGovt_order_file_content_type(atDetails.getFileContentType());
						vd.setGovt_order_file_location(atDetails.getFileLocation());
						vd.setGovt_order_file_name(atDetails.getFileName());
						vd.setGovt_order_file_size(atDetails.getFileSize());
						vd.setGovt_order_file_timestamp(atDetails.getFileTimestamp());
					}
					if (request.getAttribute("mapAttachmentList") != null && vilForm.getMapfilecount().equalsIgnoreCase("1")) {
						List<MapAttachment> mapAtList = (ArrayList<MapAttachment>) request.getAttribute("mapAttachmentList");
						mapAtDetails = mapAtList.get(0);
						vd.setMap_upload_file_content_type(mapAtDetails.getFileContentType());
						vd.setMap_upload_file_location(mapAtDetails.getFileLocation());
						vd.setMap_upload_file_name(mapAtDetails.getFileName());
						vd.setMap_upload_file_size(mapAtDetails.getFileSize());
						vd.setMap_upload_file_timestamp(mapAtDetails.getFileTimestamp());
					}
					vd.setIsPesa(vilForm.getIsPesaStatus());
					hibsession.saveOrUpdate(vd);
					hibsession.flush();
					if (hibsession.contains(query)) {
						hibsession.evict(query);
					}
					return villageForm.getVillageCode() + "";

				} else {
					query = hibsession.getNamedQuery("VillagePublishQuery")
							.setParameter("subDistrict", subDistrict, Hibernate.INTEGER)
							.setParameter("userId", vilForm.getUserId(), Hibernate.INTEGER)
							.setParameter("villageNameEnglish", villageNameEng, Hibernate.STRING)
							.setParameter("orderNo", orderNo, Hibernate.STRING)
							.setParameter("ordertimeStampDate", ordertimeStampDate, Hibernate.TIMESTAMP)
							.setParameter("effectivetimeStampDate", effectivetimeStampDate, Hibernate.TIMESTAMP)
							.setParameter("govtOrder", govtOrder, Hibernate.STRING)
							.setParameter("operationCode", operationCode, Hibernate.INTEGER)
							.setParameter("flagCode", flagCode, Hibernate.INTEGER)
							.setParameter("gazPubtimeStampDate", gazPubtimeStampDate, Hibernate.TIMESTAMP)
							.setParameter("villageNameLocale", villageNameLocale, Hibernate.STRING)
							.setParameter("villageNameAliasEn", villageNameAliasEn, Hibernate.STRING)
							.setParameter("villageNameAliasLocal", villageNameAliasLocal, Hibernate.STRING)
							.setParameter("villageType", vilForm.getVillageStatus(), Hibernate.STRING)
							.setParameter("census", census, Hibernate.INTEGER)
							.setParameter("ssCode", ssCode, Hibernate.STRING)
							.setParameter("survayNumber", survayNumber, Hibernate.STRING)
							.setParameter("gisNodes", gisNodes, Hibernate.STRING)
							.setParameter("upLoadMap", upLoadMap, Hibernate.STRING)
							.setParameter("formationTypeString", formationTypeString, Hibernate.STRING)
							.setParameter("ulbCodeValid", ulbCodeValid, Hibernate.STRING)
							.setParameter("fullContributedVillage", fullContributedVillage, Hibernate.STRING)
							.setParameter("surveyNumberList", surveyNumberList, Hibernate.STRING)
							.setParameter("surveyNumberListOld", null, Hibernate.STRING)
							.setParameter("renameNameVillageList", renameNameVillageList, Hibernate.STRING)
							.setParameter("withoutRenameNameVillageList", partUnchangedNameVillages, Hibernate.STRING)
							.setParameter("renameIdVillageList", renameIdVillageList, Hibernate.STRING)
							.setParameter("operationState", vilForm.getButtonClicked(), Hibernate.CHARACTER)
							.setParameter("stateCode", stateCode, Hibernate.INTEGER)
							.setParameter("districtCode", Integer.parseInt(vilForm.getDistrictNameEnglish()), Hibernate.INTEGER)
							.setParameter("isExistGovtOrder", vilForm.getIsExistingGovtOrder(), Hibernate.STRING)
							.setParameter("orderCode", vilForm.getOrderCode(), Hibernate.INTEGER)
							.setParameter("isPesa", vilForm.getIsPesaStatus(), Hibernate.STRING)
							;

					villageSaveBean = (VillageSaveBean) query.list().get(0);
					hibsession.flush();
					if (hibsession.contains(query)) {
						hibsession.evict(query);
					}
					return villageSaveBean.getCreate_village_fn();
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
			resultFlag = null;
		} finally {
			try {
				if (hibsession != null && hibsession.isOpen()) {
					hibsession.close();
				}
				
				
			} catch (Exception e) {
				resultFlag = null;
				log.error(e.toString());
			}
		}
		return resultFlag;
	}

	/**
	 * Save Upload Map Details in Village Draft Entity.
	 */
	@Override
	public boolean saveDataInMapDraft(VillageForm villageForm, List<AttachedFilesForm> attachedList, HttpSession session) {
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		MapAttachment attachment = null;
		Query query = null;
		boolean flag = true;
		int vid;
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
					attachment.setMapEntityCode(villageForm.getVillageCode());
					attachment.setMapEntityType('V');
					vid = villageForm.getVillageCode();
					query = sessionObj.createSQLQuery("UPDATE village_draft set (map_upload_file_name,map_upload_file_size,map_upload_file_content_type,map_upload_file_location,map_upload_file_timestamp) =('"
					+ ":fileName,:fileSize,:fileType,:fileLocation,:fileTimestampName) where village_code=:villageCode");
					
					query.setParameter("fileName", filesForm.getFileName());
					query.setParameter("fileSize", filesForm.getFileSize());
					query.setParameter("fileType", filesForm.getFileType());
					query.setParameter("fileLocation", filesForm.getFileLocation());
					query.setParameter("fileTimestampName", filesForm.getFileTimestampName());
					query.setParameter("villageCode",vid);
					query.executeUpdate();
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.toString());
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getVillageListbySubDistrictWithSDNameExtended(int subDistrictCode, int orgCode, int locatedLevelCode) throws Exception {

		List<Village> villList = null;
		villList = new ArrayList<Village>();
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		String sdName = null;

		Query criteria = null;
		SQLQuery criteria1 = null;
		Session session = null;
		try {
			try {
				session = sessionFactory.openSession();
				criteria = session.createQuery("from Subdistrict sd where sd.isactive=true and tlc=:tlc");
				criteria.setParameter("tlc", subDistrictCode, Hibernate.INTEGER);
				sdList = criteria.list();

				sdName = sdList.get(0).getSubdistrictNameEnglish();

				criteria1 = session.createSQLQuery("select case when v.village_code  in (select * from get_draft_used_lb_lr_temp(v.village_code,'V')) then  cast ('F' as character)"
						+ " else cast('A' as character) end as operation_state,case when v.village_code =a.entity_code then cast('F' as character)" + " else cast('A' as character) end as operation_extend_flag,"
						+ " v.village_code as villageCode ,v.village_name_english as villageNameEnglish from village v left outer join" + "(select entity_code from org_coverage_detail where  isactive and  coverage_code in"
						+ " (select coverage_detail_code from org_coverage where org_coverage_entity_type=4 and isactive and org_located_level_code in "
						+ " (select org_located_level_code from org_located_at_levels  where olc=:orgCode and located_at_level=:locatedLevelCode and isactive )))as a"
						+ " ON v.village_code =a.entity_code where tlc=:subDistrictCode  and isactive order by village_name_english ");

				criteria1.setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER);
				criteria1.setParameter("orgCode", orgCode, Hibernate.INTEGER);
				criteria1.setParameter("locatedLevelCode", locatedLevelCode, Hibernate.INTEGER);
				criteria1.addScalar("villageCode").addScalar("villageNameEnglish").addScalar("operation_state").addScalar("operation_extend_flag");
				criteria1.setResultTransformer(Transformers.aliasToBean(Village.class));
				villList = criteria1.list();
			} catch (Exception e) {
				log.error(e.toString());
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}

			for (int i = 0; i < villList.size(); i++) {
				villList.get(i).setVillageNameEnglish(villList.get(i).getVillageNameEnglish() + " (" + sdName + ")");
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		return villList;
	}

	/*
	 * added on 11/12/2014 for localbody impact
	 */

	@Override
	public List<Village> getVillagebysubdisrictCodesForLocalBody(String vCode) throws Exception {
		Session session = null;
		List<Village> villList = new ArrayList<Village>();
		Query query = null;
		Integer vilcode = null;
		String subdistirctname = null;
		String vilName = null;
		Character opeartion_state = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select distinct case when v.village_code  in (select local_body_code from get_draft_used_lb_lr_temp(v.village_code,'V')) "
					+ " then cast('F' as character)  else cast('A' as character) end as operation_state ,"
					+ "  v.village_code,v.village_name_english,sd.subdistrict_name_english,v.village_version from village v, subdistrict sd  where sd.tlc=v.tlc and v.tlc in(" + vCode
					+ ") and v.isactive and sd.isactive order by sd.subdistrict_name_english,v.village_name_english");
			@SuppressWarnings("unchecked")
			List<Object[]> objects = query.list();
			for (Object[] obj : objects) {
				vilcode = (Integer) obj[1];
				vilName = (String) obj[2];
				opeartion_state = (Character) obj[0];
				subdistirctname = (String) obj[3];
				vilName = vilName + "[ Sub-District : " + subdistirctname + "]";
				Village villageObj = new Village();
				villageObj.setVillageCode(vilcode);
				villageObj.setVillageNameEnglish(vilName);
				villageObj.setOperation_state(opeartion_state);
				/* added by sushil on 24-06-2015 */
				villageObj.setVillageVersion(Integer.valueOf(obj[4].toString()));
				villList.add(villageObj);

			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villList;
	}

	/**
	 * Get Village list of subdistCodes For localBody Impact
	 * 
	 * @author Ripunj Added on 10-12-2014
	 */
	@SuppressWarnings("unchecked")
	public List<Village> getVillageListbySubDistrictCodeCov(int subDistrictCode) throws Exception {

		SQLQuery criteria = null;
		Session session = null;
		List<Village> villageList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select case when v.village_code  in (select local_body_code from get_draft_used_lb_lr_temp(v.village_code,'V')) then  "
					+ " cast ('F' as character) else cast('A' as character) end as operation_state,v.village_code as villageCode ,v.village_name_english as villageNameEnglish" + " from village v where "
					+ " v.tlc=:subDistrictCode and isactive=true order by v.village_name_english ");
			criteria.setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER);
			criteria.addScalar("operation_state");
			criteria.addScalar("villageCode").addScalar("villageNameEnglish");
			criteria.setResultTransformer(Transformers.aliasToBean(Village.class));
			villageList = criteria.list();
		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;

	}

	/**
	 * Add for LocalBody Draft Mode on 05-01-2015 to get Villages from selected
	 * block
	 * 
	 * @author Ripunj
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getVillageByBlockListForLocalBody(List<Integer> blockCodeList) throws Exception {
		Session session = null;
		List<Village> finalVillageList = null;
		try {
			session = sessionFactory.openSession();
			
				SQLQuery criteria = session.createSQLQuery("select case when v.village_code  in (select local_body_code from get_draft_used_lb_lr_temp(v.village_code,'V')) then "
				+ "cast ('F' as character) else cast('A' as character) end as operation_state,v.village_code as villageCode ,v.village_name_english||'(Village Code-'||"
				+ " v.village_code||')['||btrim(b.block_name_english)||']'  as villageNameEnglish from block b inner join block_village bv on b.blc=bv.blc "
				+ "inner join village v  on bv.vlc=v.vlc where b.block_code    in(:blockCodeList) and b.isactive and bv.isactive and v.isactive order by v.village_name_english ");
				criteria.setParameterList("blockCodeList", blockCodeList);
				criteria.addScalar("operation_state");
				criteria.addScalar("villageCode").addScalar("villageNameEnglish");
				criteria.setResultTransformer(Transformers.aliasToBean(Village.class));
				finalVillageList = criteria.list();
				
				
			
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return finalVillageList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Village> getVillageListbySubDistrictWithSDNameForLocalbody(int subDistrictCode) throws Exception {

		List<Village> villList = null;
		villList = new ArrayList<Village>();
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		String sdName = null;

		Query criteria = null;
		SQLQuery criteria1 = null;
		Session session = null;
		try {
			try {
				session = sessionFactory.openSession();
				criteria = session.createQuery("from Subdistrict sd where sd.isactive=true and tlc=:tlc");
				criteria.setParameter("tlc", subDistrictCode, Hibernate.INTEGER);
				sdList = criteria.list();

				sdName = sdList.get(0).getSubdistrictNameEnglish();

				criteria1 = session.createSQLQuery("select case when v.village_code  in (select local_body_code from get_draft_used_lb_lr_temp(v.village_code,'V')) then  "
						+ " cast ('F' as character) else cast('A' as character) end as operation_state,v.village_code as villageCode ,v.village_name_english as villageNameEnglish" + " from village v where "
						+ " v.tlc=:subDistrictCode and isactive=true order by v.village_name_english ");
				criteria1.setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER);
				criteria1.addScalar("operation_state");
				criteria1.addScalar("villageCode").addScalar("villageNameEnglish");
				criteria1.setResultTransformer(Transformers.aliasToBean(Village.class));
				villList = criteria1.list();

				// criteria1 =
				// session.createQuery("from Village where isactive=true and tlc=:tlc order by villageNameEnglish");
				// criteria1.setParameter("tlc", subDistrictCode,
				// Hibernate.INTEGER);
				// villList = criteria1.list();
			} catch (HibernateException e) {

				log.error(e.toString());
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}

			for (int i = 0; i < villList.size(); i++) {
				villList.get(i).setVillageNameEnglish(villList.get(i).getVillageNameEnglish() + " (" + sdName + ")");
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		return villList;
	}

	/**
	 * Save Govt Order Details in Village Draft Rename Entity
	 */
	@Override
	public boolean saveDataInAttachDraftRenameVilCreate(VillageForm villageForm, List<AttachedFilesForm> attachedList, int vilCode, HttpSession session) {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query = null;
		boolean flag = true;
		//int vid;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					//vid = villageForm.getVillageCode();
					query = sessionObj.createSQLQuery("UPDATE village_draft_rename set (govt_order_file_name,govt_order_file_size,govt_order_file_content_type,govt_order_file_location,govt_order_file_timestamp) =("
							+":fileName,:filesize,:fileType,:fileLocation,:fileTimeStamp)  where village_rename_code=:villageCode");
							query.setParameter("fileName", filesForm.getFileName());
							query.setParameter("filesize", filesForm.getFileSize());
							query.setParameter("fileType", filesForm.getFileType());
							query.setParameter("fileLocation", filesForm.getFileLocation());
							query.setParameter("fileTimeStamp", filesForm.getFileTimestampName());
							query.setParameter("villageCode", filesForm.getFileName());
					query.executeUpdate();
					tx1.commit();
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;

	}

	/**
	 * Added by Ripunj on 17-03-2015 for Invalidate Draft Village mode
	 * 
	 */
	@Override
	public List<Village> getmapUnmapVillageListInvalidateDraftMode(int slc, int dlc, int tlc, int type, int draftVillageCode) throws Exception {
		Session session = null;
		List<Village> villList = new ArrayList<Village>();
		Query query = null;
		Integer vilcode = null;
		String vilName = null;
		String lbName = null;
		Character operation_state;
		String invalidateOperationState;
		try {
			session = sessionFactory.openSession();
			if (type == 1) {
				query = session
						.createSQLQuery("select distinct case when d.village_code  in (select * from get_draft_used_lb_lr_temp(d.village_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state,"
								+ " case when vlc in (select (CAST(regexp_split_to_table(invalidatevillagelist, E'\\,') AS INT)) from village_draft t where subdistrict=:tlc and t.village_code=:draftVillageCode) then 'I' else 'U'  end as opeartion_state,  "
								+ "d.village_code, d.village_name_english Village_Name,f.local_body_name_english Gram_Panchayat_Name from state a, district b, subdistrict c, village d left outer join lb_covered_landregion e on d.VLC=e.LRLC  "
								+ "and e.land_region_type='V' and e.isactive='t' left outer join localbody f on e.lb_covered_region_code=f.lb_covered_region_code and f.isactive='t' "
								+ "and f.local_body_type_code in (select lbt.local_body_type_code from local_body_type lbt where level = 'V') where a.SLC = b.SLC and a.isactive = true and b.isactive='t' and b.DLC=c.DLC and c.isactive='t' and c.TLC=d.TLC and d.isactive='t' "
								+ "and b.district_code=:dlc and c.subdistrict_code=:tlc and a.slc=:slc and e.coverage_type is not null and f.local_body_name_english is not null order by d.village_name_english");
				query.setParameter("slc", slc);
				query.setParameter("dlc", dlc);
				query.setParameter("tlc", tlc);
				query.setParameter("draftVillageCode", draftVillageCode);
				@SuppressWarnings("unchecked")
				List<Object[]> objects = query.list();
				for (Object[] obj : objects) {
					operation_state = (Character) obj[0];
					invalidateOperationState = (String) obj[1];
					vilcode = (Integer) obj[2];
					vilName = (String) obj[3];
					lbName = (String) obj[4];
					vilName = vilName + " ( GP - " + lbName + ")";
					Village villageObj = new Village();
					villageObj.setVillageCode(vilcode);
					villageObj.setVillageNameEnglish(vilName);
					villageObj.setOperation_state(operation_state);
					villageObj.setOpeartion_state(invalidateOperationState);
					villList.add(villageObj);
				}
				
			} else {

				query = session.createSQLQuery("select distinct  case when d.village_code  in (select * from get_draft_used_lb_lr_temp(d.village_code,'V')) then  cast ('F' as character) else cast('A' as character) end as operation_state, "
						+ "case when vlc in (select (CAST(regexp_split_to_table(invalidatevillagelist, E'\\,') AS INT)) from village_draft t where subdistrict=:tlc  and t.village_code=:draftVillageCode) then 'I' else 'U' end as opeartion_state, "
						+ " d.village_code, d.village_name_english Village_Name from state a,district b, subdistrict c, village d " + "where a.SLC = b.SLC and a.isactive = true and b.isactive and b.DLC=c.DLC and c.isactive "
						+ "and c.TLC=d.TLC and d.isactive and not exists (select e.vlc from (select distinct a.vlc from village a,localbody b , lb_covered_landregion c "
						+ "where b.lb_covered_region_code=c.lb_covered_region_code and c.lrlc=a.vlc and a.isactive and b.isactive and c.isactive "
						+ "and c.land_region_type='V' and b.slc=:slc and b.local_body_type_code in (select lbt.local_body_type_code from local_body_type lbt where level = 'V') ) e "
						+ "where d.vlc=e.vlc ) and b.district_code=:dlc and c.subdistrict_code=:tlc and a.slc=:slc order by d.village_name_english");
				query.setParameter("slc", slc);
				query.setParameter("dlc", dlc);
				query.setParameter("tlc", tlc);
				query.setParameter("draftVillageCode", draftVillageCode);
				@SuppressWarnings("unchecked")
				List<Object[]> objects = query.list();
				for (Object[] obj : objects) {
					operation_state = (Character) obj[0];
					invalidateOperationState = (String) obj[1];
					vilcode = (Integer) obj[2];
					vilName = (String) obj[3];
					vilName = vilName + " (Unmapped)";
					Village villageObj = new Village();
					villageObj.setVillageCode(vilcode);
					villageObj.setVillageNameEnglish(vilName);
					villageObj.setOperation_state(operation_state);
					villageObj.setOpeartion_state(invalidateOperationState);
					villList.add(villageObj);
				}
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villList;
	}

	/**
	 * To modify Invalidate Draft Mode Villages
	 * 
	 * @author Ripunj on 17-03-2015
	 */
	@Override
	public String invalidateVillageDAOModify(String villCode, Integer userId, String orderNumber, Date orderDateCR, Date effectiveDateCR, String govtOrder, Date gzbDateCR, String destinationVillage, Integer distinationSubdistrict, Session session,
			Integer ulb, Integer orderCode, String isExistGovtOrder, Character operationState, Integer draftVillageCode) throws Exception {
		Query criteria = null;
		Transaction tx = null;
		String invalidateVillageList = null;
		String[] villages = null;
		int villageCode = 0;
		int vilVersion = 1;
		int ulbVersion = 1;
		//int villageVersion = 0;
		int i = 0;
		String invalidatedVillageNameEnglish = "";
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			if (ulb != null && ulb > 0) {
				villages = villCode.split(",");
				int len = villages.length;
				InvalidateVillageUlbMerge[] blvil = new InvalidateVillageUlbMerge[len];
				for (i = 0; i < len; i++) {
					blvil[i] = new InvalidateVillageUlbMerge();
					villageCode = Integer.parseInt(villages[i]);
					criteria = session.createSQLQuery("select max(village_version) from village where isactive=true and village_code=:villageCode");
					criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
					vilVersion = Integer.parseInt(criteria.list().get(0).toString());

					criteria = session.createSQLQuery("select max(local_body_version) from localbody where isactive=true and local_body_code=:ulbCode");
					criteria.setParameter("ulbCode", ulb, Hibernate.INTEGER);
					ulbVersion = Integer.parseInt(criteria.list().get(0).toString());

					blvil[i].setVillageCode(villageCode);
					blvil[i].setUlbCode(ulb);
					blvil[i].setUlbVersion(ulbVersion + 1);
					blvil[i].setVillageVersion(vilVersion + 1);
					session.save(blvil[i]);
				}
			}
			java.sql.Timestamp gazPubtimeStampDateTemp = null;
			final java.sql.Timestamp ordertimeStampDate = new Timestamp(orderDateCR.getTime());
			final java.sql.Timestamp effectivetimeStampDate = new Timestamp(effectiveDateCR.getTime());
			if (gzbDateCR != null) {
				gazPubtimeStampDateTemp = new Timestamp(gzbDateCR.getTime());
			}
			final java.sql.Timestamp gazPubtimeStampDate = gazPubtimeStampDateTemp;

			if (('P') == operationState) {
				criteria = session.getNamedQuery("invalidate_village_fn");
				criteria.setParameter("villageCode", villCode, Hibernate.STRING);
				criteria.setParameter("userId", userId, Hibernate.INTEGER);
				criteria.setParameter("orderNumber", orderNumber, Hibernate.STRING);
				criteria.setParameter("orderDate", ordertimeStampDate, Hibernate.DATE);
				criteria.setParameter("effectiveDate", effectivetimeStampDate, Hibernate.DATE);
				criteria.setParameter("govtOrder", govtOrder, Hibernate.STRING);
				criteria.setParameter("gzbDate", gazPubtimeStampDate, Hibernate.DATE);
				criteria.setParameter("destinationVillageList", destinationVillage, Hibernate.STRING);
				criteria.setParameter("distinationSubdistrict", distinationSubdistrict, Hibernate.INTEGER);

				criteria.setParameter("orderCode", orderCode, Hibernate.INTEGER);
				criteria.setParameter("isExistGovtOrder", isExistGovtOrder, Hibernate.STRING);
				criteria.setParameter("operationState", operationState, Hibernate.CHARACTER);
				InvalidateVillage changevil = (InvalidateVillage) criteria.list().get(0);
				invalidateVillageList = changevil.getInvalidate_village_fn();
				tx.commit();

			} else {
				VillageDraft vd = new VillageDraft();
				List<VillageDraft> listVillageDetail = getVillageDraftDetails(draftVillageCode);
				vd = listVillageDetail.get(0);
				criteria = session.createSQLQuery("select cast (string_agg(village_name_english, ', ')as character varying) from village where "
						+ " village_code in (select  ((CAST(regexp_split_to_table(:villCode, E'\\,') AS INT)))) and isactive limit 5");
				criteria.setParameter("villCode", villCode, Hibernate.STRING);
				invalidatedVillageNameEnglish = criteria.list().get(0).toString();
				vd.setVillageNameEnglish(invalidatedVillageNameEnglish);
				vd.setVillageCode(vd.getVillageCode());
				vd.setSubDistrict(vd.getSubDistrict());
				vd.setUserId(vd.getUserId());
				vd.setOrderNo(orderNumber.trim());
				vd.setOrdertimeStampDate(ordertimeStampDate);
				vd.setEffectivetimeStampDate(effectivetimeStampDate);
				vd.setGovtOrder(govtOrder);
				vd.setOperationCode(14);
				vd.setGazPubtimeStampDate(gazPubtimeStampDate);
				vd.setIsExistGovtOrder(isExistGovtOrder);
				vd.setInvalidateVillageList(villCode);
				session.saveOrUpdate(vd);
				vd.setOrdercode(orderCode);
				tx.commit();
				invalidateVillageList = "" + draftVillageCode;

			}
			return invalidateVillageList;
		} catch (Exception e) {
			log.error(e.toString());
			if (tx != null) {
				tx.rollback();
			}
			return invalidateVillageList;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * Get Draft Village Entities by DistrictCode
	 * 
	 * @author Ripunj on 09-04-2015
	 * @param districtCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VillageDraft> getVillageDraftListByDistrictCode(int districtCode) throws Exception {

		Query criteria = null;
		Query criteria1 = null;
		Session session = null;
		List<VillageDraft> villageList = new ArrayList<VillageDraft>();
		String invalidatedVillageNameEnglish = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(" from VillageDraft v where v.districtCode=:district_Code order by v.villageNameEnglish");
			criteria.setParameter("district_Code", districtCode);
			villageList = criteria.list();
			if (villageList != null && villageList.size() > 0) {
				for (int i = 0; i < villageList.size(); i++) {
					if (villageList.get(i).getOperationCode() == 14) {
						criteria1 = session
								.createSQLQuery("select cast (string_agg(village_name_english, ', ')as character varying) from village where village_code in (select  ((CAST(regexp_split_to_table(:villCode, E'\\,') AS INT)))) and isactive");
						criteria1.setParameter("villCode", villageList.get(i).getInvalidateVillageList(), Hibernate.STRING);
						List list = criteria1.list();
						if (list != null && list.size() > 0) {
							invalidatedVillageNameEnglish = list.get(0) != null ? list.get(0).toString() : null;
							villageList.get(i).setInvalidateVillageListNameEnglish(invalidatedVillageNameEnglish);
						}
					}
				}
			}

		} catch (HibernateException e) {
			log.error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;
	}

	/**
	 * author Anju Gupta on 30/03/2015 for villageList for Pesa Land Regions
	 */
	@SuppressWarnings("unchecked")
	public List<Village> villageListbySlctoIspesa(int slc) throws Exception {
		Session session = null;

		SQLQuery query = null;
		List<Village> villageList = new ArrayList<Village>();
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("SELECT distinct s.subdistrict_name_english as subdistrictNameEnglish, v.village_code as villageCode,"
					+ "v.is_pesa as is_pesa,v.village_name_english as villageNameEnglish FROM subdistrict s,village v,district d WHERE "
					+ "s.tlc=v.tlc and s.dlc=v.dlc and s.dlc=d.dlc and d.slc=:slc and v.is_pesa!='N' and d.is_pesa='P' and v.isactive");
			query.setParameter("slc", slc, Hibernate.INTEGER);
			query.addScalar("subdistrictNameEnglish");
			query.addScalar("villageCode");
			query.addScalar("is_pesa");
			query.addScalar("villageNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Village.class));
			villageList = query.list();
		} catch (Exception e) {
			log.error(e.toString());

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;
	}

	/**
	 * Get List<Village> base on Creteria for extend organigation Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 */
	@SuppressWarnings("unchecked")
	public List<Village> getVillageListbyCreteria(List<Integer> districtList, List<Integer> subDistrictCodeList, List<Integer> villageList, Integer subDistrictCode) throws Exception {
		Session session = null;
		List<Village> VillageList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select   case when v.village_code  in (select * from get_draft_used_lb_lr_temp(v.village_code,'V')) then  cast ('F' as character) else "
					+ "cast('A' as character) end as operation_state,v.village_code as villageCode,(btrim(v.village_name_english)||'('||btrim(sd.subdistrict_name_english)||')') as villageNameEnglish"
					+ " from village v left join subdistrict sd  on sd.tlc=v.tlc where v.isactive and sd.isactive ");
			if (subDistrictCode != null) {
				queryBuild.append(" and v.vlc not in(:villageList) and v.tlc=:subDistrictCode");
			} else {
				if (villageList != null && !villageList.isEmpty()) {
					queryBuild.append(" and v.vlc in(:villageList)");
				}
				if (districtList != null && subDistrictCodeList != null && !districtList.isEmpty() && !subDistrictCodeList.isEmpty()) {
					queryBuild.append(" and v.dlc not in(:districtList) and v.tlc not in(:subDistrictCodeList)");
				}
			}
			queryBuild.append("	order by villageNameEnglish ");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			if (villageList != null && !villageList.isEmpty()) {
				query.setParameterList("villageList", villageList);
			}
			if (subDistrictCode != null) {
				query.setParameter("subDistrictCode", subDistrictCode);
			}
			if (districtList != null && subDistrictCodeList != null && !districtList.isEmpty() && !subDistrictCodeList.isEmpty()) {
				query.setParameterList("districtList", districtList);
				query.setParameterList("subDistrictCodeList", subDistrictCodeList);
			}
			query.addScalar("villageCode");
			query.addScalar("villageNameEnglish");
			query.addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(Village.class));
			VillageList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return VillageList;
	}
	
	/**
	 * Get Invalidate Village is mapped or unmapped
	 * @param invalidateVillageCodeList
	 * @author Pooja Sharma 29-07-2015
	 */
	@Override
	public Boolean[] isMappedOrUnmapped(Integer[] invalidateVilllageCodeList) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Boolean[] isMapped = {false,false};
		Integer mappedCount = 0;
		try {
			session = sessionFactory.openSession();
			for(int i=0;i<invalidateVilllageCodeList.length;i++){
			query = session.createSQLQuery("SELECT CAST(COUNT(*) AS INT) FROM lb_covered_landregion WHERE lrlc = :invalidateVilllageCode AND isactive");
			query.setParameter("invalidateVilllageCode", invalidateVilllageCodeList[i]);
			mappedCount = (Integer) query.uniqueResult();
			if (mappedCount > 0) {
				isMapped[0] = true;
			}
			else{
				isMapped[1] = true;
			}
			}
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isMapped;
	}

	/**
	 * Get LocalBodyDetails by SubdistrictCodes
	 * @param subDistricts
	 * @author Pooja Sharma on 02-09-2015
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GetLBDetailsBySubDistricts> getLBDetailsBySubDistricts(String subDistricts) throws Exception {
		Session session = null;
		Query query = null;
		List<GetLBDetailsBySubDistricts> lbDetails = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getLbDetailsBySubDistricts").setParameter("subDistricts", subDistricts);
			lbDetails = query.list();
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbDetails;
	}
	
	/**
	 * Code used for getting max Effective Date of all Previous versions of Villages
	 * @author Pooja
	 * @since 16-11-2015
	 * @param villages
	 * @return
	 * @throws Exception
	 */
	public String getMaxPreVersionEffDateOfVillages(String villages) throws Exception {
		Session session = null;
		Query query = null;
		String maxPreVersionEffDate = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select cast(cast(max(effective_date) as date) as character varying) from village where village_code in (" + villages + ")");
			if (query.list() != null)
				maxPreVersionEffDate = (String) query.list().get(0);
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return maxPreVersionEffDate;
	}
	
	
	/**
	 * @author Maneesh Kumar 09MAy2016
	 */
	@Override
	public String getISPesaFlag(List<Integer> villageCodeList)throws Exception{
	Session session=null;
	String isPesaFlag=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select distinct case when (SELECT count(DISTINCT is_pesa) FROM village ");
									 sb.append("where  vlc in(:villageCodeList) and isactive )>1 then 'M' else is_pesa end ");
									 sb.append("FROM   village   where  vlc in(:villageCodeList) and isactive");
			Query query=session.createSQLQuery(sb.toString());
			query.setParameterList("villageCodeList", villageCodeList);
			isPesaFlag=(String)query.uniqueResult().toString();
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}
	
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isPesaFlag;
	}
	
	@Override
	public String finalizeVillageGIS(Integer villageCode) throws Exception{
		Session session=null;
		String gisVillageURL="FAILED";
		try{
			session=sessionFactory.openSession();
			List<Village> villageList=this.getVillageListByVillCode(villageCode);
			if(villageList!=null && !villageList.isEmpty()){
				Village villageObj= villageList.get(0);
				Integer lrReplaces=villageObj.getLrReplaces();
				StringBuilder sb=new StringBuilder("select cast(array_to_string(array(select lrlc||case when ");
				sb.append(" (select count(1)>0 from village vc where vc.village_code =lr.lrlc and vc.isactive) then  'P' else 'F' end");
				sb.append(" from landregion_replaces  lr  where lr_replaces  =     :lrReplaces ),',') as varchar) coverage"); 
				SQLQuery query=session.createSQLQuery(sb.toString());
				query.setParameter("lrReplaces", lrReplaces);
				String coverage=(String)query.uniqueResult();
				
				Properties properties = new Properties();
				InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");
				properties.load(inputStreamPro);
				String gisServerLoc=properties.getProperty("gisMap.server.location");
				if(coverage!=null && !("").equals(coverage.trim())){
				String ensubdistCode=EncryptionUtil.encode(String.valueOf(villageObj.getTlc()));
				String envillageCode=EncryptionUtil.encode(villageCode.toString());
				String envillageName=EncryptionUtil.encode(villageObj.getVillageNameEnglish());
				String encoverage=EncryptionUtil.encode(coverage);
				String enisShowOnlyBoundary=EncryptionUtil.encode("FV");
				gisVillageURL = gisServerLoc+"?subdistCode=" +ensubdistCode  +"&villageCode="+envillageCode+"&villageName="+envillageName+"&coverage="+encoverage+"&isShowOnlyBoundary="+enisShowOnlyBoundary;
				}
				
			}
			
		return 	gisVillageURL;
		}catch(Exception e){
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
	}
	
	public Boolean checkUnMappedPolygonByState(Integer stateCode) {
		Session session = null;
		Boolean isUnMappedPolygonPresent=false;
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("with usubdistrict as (select distinct cast(sdtcode11 as integer) as usubdistrict_code11   from gis_unmapped_polygons_new where "
					+ "not is_mapped) select distinct state_code from state s, district d, subdistrict t, usubdistrict u where s.slc=d.slc and s.isactive and d.isactive "
					+ "and d.dlc=t.dlc and t.isactive and t.census_2011_code=u.usubdistrict_code11 and state_Code=:stateCode");
			query.setParameter("stateCode", stateCode);
			List<Object> unMappedPolygonList = query.list();
			if(unMappedPolygonList!=null && unMappedPolygonList.size()>0){
				isUnMappedPolygonPresent=true;
			}
		} catch (HibernateException e) {
			log.error("LocalBodyDaoImpl.getMappedLBsForGIS : ", e);
			throw new HibernateException(e);
		} finally {
			closeConnection(session);
		}
		return isUnMappedPolygonPresent;
	}
	
	public List<District> getUnMappedPolygonDistrictByState(Integer stateCode) {
		Session session = null;
		List<District> unMappedPolygonDistrict=new ArrayList<District>();
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("with usubdistrict as (select distinct cast(sdtcode11 as integer) as usubdistrict_code11   from gis_unmapped_polygons_new where "
					+ "not is_mapped) select distinct district_code as districtcode,district_name_english as districtnameenglish from district d, subdistrict t, usubdistrict u where d.slc=:stateCode and d.isactive"
					+ " and d.dlc=t.dlc and t.isactive and t.census_2011_code=u.usubdistrict_code11 order by district_name_english");
			query.setParameter("stateCode", stateCode);
			query.addScalar("districtCode").addScalar("districtNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			unMappedPolygonDistrict = query.list();
		} catch (HibernateException e) {
			log.error("LocalBodyDaoImpl.getMappedLBsForGIS : ", e);
			throw new HibernateException(e);
		} finally {
			closeConnection(session);
		}
		return unMappedPolygonDistrict;
	}
	
	public List<Subdistrict> getUnMappedPolygonSubDistrictByDistrict(Integer districtCode) {
		Session session = null;
		List<Subdistrict> unMappedPolygonSubDistrict=new ArrayList<Subdistrict>();
		try{
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("with usubdistrict as (select distinct cast(sdtcode11 as integer) as usubdistrict_code11   from gis_unmapped_polygons_new where"
					+ " not is_mapped) select subdistrict_code as subdistrictcode,subdistrict_name_english as subdistrictnameenglish,t.census_2011_code as census2011Code"
					+ " from district d, subdistrict t, usubdistrict u where d.dlc=:districtCode and d.isactive and d.dlc=t.dlc and t.isactive and "
					+ "t.census_2011_code=u.usubdistrict_code11 order by subdistrict_name_english");
			query.setParameter("districtCode", districtCode);
			query.addScalar("subdistrictCode").addScalar("subdistrictNameEnglish").addScalar("census2011Code");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			unMappedPolygonSubDistrict = query.list();
		} catch (HibernateException e) {
			log.error("LocalBodyDaoImpl.getMappedLBsForGIS : ", e);
			throw new HibernateException(e);
		} finally {
			closeConnection(session);
		}
		return unMappedPolygonSubDistrict;
	}
	
	/**
	 * 
	 * @param session
	 */
	private void closeConnection (Session session){
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
	
	
	@Override
	public String insertVillageGisMapStatus(Integer villageCode)throws HibernateException{
		Session session = null;
		String sucessMsg="";
		try{
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("INSERT_VILLAGE_GISMAP_STATUS");
			query.setParameter("villageCode", villageCode);
			VillageGISMapStatus villGisMap = (VillageGISMapStatus) query.list().get(0);
			sucessMsg=villGisMap.getMessage();
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
			
		} catch (HibernateException e) {
			log.error("LocalBodyDaoImpl.getMappedLBsForGIS : ", e);
			throw new HibernateException(e);
		} finally {
			closeConnection(session);
		}
		return sucessMsg;
	}
	
	@Override
	public String checkVillageNewforMap(Integer villageCode)throws Exception{
		Session session = null;
		String sucessMsg="";
		try{
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select expmessage from village_gismap_status where  village_code =:villageCode");
			query.setParameter("villageCode", villageCode);
			sucessMsg = (String) query.uniqueResult();
			
			
		} catch (HibernateException e) {
			log.error("LocalBodyDaoImpl.getMappedLBsForGIS : ", e);
			throw new HibernateException(e);
		} finally {
			closeConnection(session);
		}
		return sucessMsg;
	}

	
	
	@Override
	public String getContributingLandregion(Integer entityCode,Character entityType)throws HibernateException{
		Session session = null;
		String sucessMsg="";
		try{
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("GET_CONTRIBUTING_LANDREGION");
			query.setParameter("entityCode", entityCode);
			query.setParameter("entityType", entityType);
			VillageGISMapStatus villGisMap = (VillageGISMapStatus) query.list().get(0);
			sucessMsg=villGisMap.getMessage();
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
			
		} catch (HibernateException e) {
			log.error("LocalBodyDaoImpl.getMappedLBsForGIS : ", e);
			throw new HibernateException(e);
		} finally {
			closeConnection(session);
		}
		return sucessMsg;
	}
	
	
	@Override
	public HabitationConfiguration getStateWiseHabitationConfiguration(Integer slc)throws Exception{
		Session session = null;
		HabitationConfiguration habitationConfiguration=null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(HabitationConfiguration.class);
			criteria.add( Restrictions.eq("slc", slc));
			criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
			List<HabitationConfiguration> habitationConfigurationList = criteria.list();
			if(habitationConfigurationList!=null && !habitationConfigurationList.isEmpty()){
				habitationConfiguration=habitationConfigurationList.get(0);
			}
		}finally{
			closeConnection(session);
		}
		return habitationConfiguration;
	}
	
	@Override 
	public boolean habitationNameUniquewithParent(String habitationNameEng,Integer parentCode,String parentType)throws Exception{
		boolean uniqueFlag=false;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createSQLQuery("select case when count(1)>0 then false else true end as uniqueName from habitation where parent_code =:parentCode and   parent_type=:parentType  and habitation_name_english ilike :habitationNameEng");
			query.setParameter("habitationNameEng", habitationNameEng);
			query.setParameter("parentCode", parentCode);
			query.setParameter("parentType", parentType);
			uniqueFlag=(boolean)query.uniqueResult();
		}catch(Exception e){
			log.error("Exception Section Dao#sectionNameUniquewithParent-->" + e);
			throw e;
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return uniqueFlag;
	}
	@Override
	public void saveHabitationConfiguration(HabitationConfiguration habitationConfiguration)throws Exception {
		Session session=null;
		Transaction tx = null;
		try{
			session=sessionFactory.openSession();
			tx = session.beginTransaction();
			Integer id=habitationConfiguration.getId();
			if(id!=null){
				HabitationConfiguration updateHabitationConfiguration	=(HabitationConfiguration)session.get(HabitationConfiguration.class, id);
				habitationConfiguration.setCreatedby(updateHabitationConfiguration.getCreatedby());
				habitationConfiguration.setCreatedon(updateHabitationConfiguration.getCreatedon());
				session.flush();
				session.clear();
			}
			session.saveOrUpdate(habitationConfiguration);
			tx.commit();
		}
		catch (Exception e) {
			if(tx!=null)
			tx.rollback();
			throw e;
	}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
	
		}
	}
	@Override
	public List<Habitation> getHabitationList(List<Integer> villageCodeList)throws Exception{
		
		List<Habitation> habitationList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("select habitation_code as habitationCode,parent_code as parentCode,habitation_name_english as habitationNameEnglish from habitation where parent_type ='V'  and parent_code in(:villageCodeList) and isactive and habitation_code not in(select habitation_code from localbody_habitation where isactive)");
			query.setParameterList("villageCodeList", villageCodeList);
			query.addScalar("habitationCode");
			query.addScalar("parentCode");
			query.addScalar("habitationNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Habitation.class));
			habitationList = query.list();
		}catch(Exception e){
			throw e;
	
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return habitationList;
	}
	
	@Override
	public void saveHabitationLocalbody(LocalBodyForm localBodyForm)throws Exception{
		Session session = null;
		Transaction tx=null;
		try {
			session = sessionFactory.openSession();
			List<Integer> delhabitationList = new ArrayList<Integer>();
			delhabitationList.add(-1);
			String habiationdet=localBodyForm.getAvilableHabitation();
			if(habiationdet!=null)
			if (habiationdet.contains(",")) {
				Scanner scanner = new Scanner(habiationdet);
				
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					String habitationDet[]=scanner.next().split("_");
					if(("E").equals(habitationDet[1].toString()))
						delhabitationList.add(Integer.parseInt(habitationDet[0]));
				}
			scanner.close();
			}else{
				String habitationDet[]=habiationdet.split("_");
				if(("E").equals(habitationDet[1].toString()))
					delhabitationList.add(Integer.parseInt(habitationDet[0]));
				
			}
			
			Query query=session.createQuery("from LocalbodyHabitation where isactive=true and habitationCode in(:delhabitationList)");
			query.setParameterList("delhabitationList", delhabitationList);
			List<LocalbodyHabitation> localbodyHabitationList = query.list();
			
			for(LocalbodyHabitation  delLocalbodyHabitation:localbodyHabitationList){
				delLocalbodyHabitation.setIsactive(Boolean.FALSE);
				session.save(delLocalbodyHabitation);
			}
			
			
			List<Integer> habitationList = new ArrayList<Integer>();
			habitationList.add(-1);
			habiationdet=localBodyForm.getContributingHabiationCodes();
			if(habiationdet!=null)
			if (habiationdet.contains(",")) {
				Scanner scanner = new Scanner(habiationdet);
				
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					String habitationDet[]=scanner.next().split("_");
					if(("N").equals(habitationDet[1].toString()))
					habitationList.add(Integer.parseInt(habitationDet[0]));
				}
			scanner.close();
			}else{
				String habitationDet[]=habiationdet.split("_");
				if(("N").equals(habitationDet[1].toString()))
				habitationList.add(Integer.parseInt(habitationDet[0]));
				
			}
			
			
			 query = session.getNamedQuery("Fetch_LOCALBODY_HABIATION_Details");
			query.setParameterList("habitationList", habitationList);
			query.setParameter("localBodyCode", localBodyForm.getLocalBodyCode());
			List<FetchLOCALBODYHABIATION> completeCoverages = query.list();
			
			session.flush();
			session.clear();
			LocalbodyHabitation localbodyHabitation=null;
			tx = session.beginTransaction();
			for(FetchLOCALBODYHABIATION obj:completeCoverages){
				
				localbodyHabitation=new LocalbodyHabitation();
				draftUtils.copyBeanAttributes(localbodyHabitation, obj);
				session.save(localbodyHabitation);
			}
			
			//LbCoveredLandregion
			
			tx.commit();
			
		
		} catch (HibernateException e) {
			// TODO: handle exception
			if(tx!=null)
			{
				tx.rollback();
			}
			throw e;
		} finally {
			closeConnection(session);
		}
	}
		
	@Override
	public List<Habitation> getMappedHabitationList(Integer localBodyCode)throws Exception{
		
		List<Habitation> habitationList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("select h.habitation_code as habitationCode ,h.habitation_name_english as habitationNameEnglish,h.parent_code as parentCode from lb_covered_landregion lb inner join localbody_habitation lh on "
			+ "lh.lbclr_code=lb.lbclr_code inner join habitation h on h.habitation_code=lh.habitation_code and h.habitation_version=lh.habitation_version where "
			+ "lb.lb_covered_region_code=(select lb_covered_region_code from localbody  where local_body_code =:localBodyCode and isactive) and h.isactive and lh.isactive");
			query.setParameter("localBodyCode", localBodyCode);
			query.addScalar("habitationCode");
			query.addScalar("parentCode");
			query.addScalar("habitationNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Habitation.class));
			habitationList = query.list();
		}catch(Exception e){
			throw e;
	
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return habitationList;
	}
	
	
	@Override
	public void saveHabitation(HabitationForm habitationForm)throws Exception{
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			
			if((CommanConstant.HABITATION_PARENT_TYPE_GRAMPANCHAYAT.toString()).equals(habitationForm.getParentType())){
				habitationForm.setParentCode(Integer.parseInt(habitationForm.getLocalBodyLevelCodes().split("\\,")[2]));
		    }else{
		    	habitationForm.setParentCode(habitationForm.getParamVillageCode());
		    }
			
			Habitation habitation=new Habitation();
			draftUtils.copyBeanAttributes(habitation, habitationForm);
			habitation.setIsactive(Boolean.TRUE);
			habitation.setHabitationVersion(1);
			session.save(habitation);
			transaction.commit();
			}catch(Exception e){
			transaction.rollback();
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
	}
	
	
	@Override
	public boolean validateParentCode(Integer parentCode,String parentType,Integer slc)throws Exception{
		Session session=null;	
		boolean parentFlag=false;
		try{
			session=sessionFactory.openSession();
			if((CommanConstant.HABITATION_PARENT_TYPE_GRAMPANCHAYAT.toString()).equals(parentType)){
				
				Query query=session.createSQLQuery("select case when count(1)>0 then true else false end from localbody where local_body_type_code =3 and   isactive and slc=:slc and local_body_code=:parentCode");
				query.setParameter("slc", slc);
				query.setParameter("parentCode", parentCode);
				parentFlag=(boolean)query.uniqueResult();
			}else{
				
				Query query=session.createSQLQuery("select case when count(1)>0 then true else false end from village where  isactive and slc=:slc and vlc=:parentCode");
				query.setParameter("slc", slc);
				query.setParameter("parentCode", parentCode);
				parentFlag=(boolean)query.uniqueResult();
			}
			
			
		} finally {
			closeConnection(session);
		}
		
		return parentFlag;
	}


	@Override
	public boolean validateStateWiseHabitation(Integer stateCode)throws Exception {
		Session session = null;
		boolean isDisabled = false;

		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(
					"select case when count(1)>0 then true else false end from habitation where slc=:slc and isactive");
			query.setParameter("slc", stateCode);
			isDisabled = (boolean) query.uniqueResult();

		} catch (HibernateException e) {
			throw new HibernateException(e);
		} finally {
			closeConnection(session);
		}
		return isDisabled;
	}
	
	
	
	@Override
	public List<Habitation> getHabitationList(int parentCode, int stateCode) {
		Session session = null;
		List<Habitation> habitations=new ArrayList<Habitation>();
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Habitation where parentCode=:parentCode and slc=:stateCode and isactive is true");
			query.setParameter("parentCode", parentCode, Hibernate.INTEGER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			habitations = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.getHabitationList : ", e);
			throw e;
		} finally {
			closeConnection(session);
		}
		return habitations;
	}

	@Override
	public Habitation getHabitationDetails(int habitationCode,int habitationVersion) {
		Session session=null;
		Habitation habitation=null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Habitation where habitationCode=:habitationCode and habitationVersion=:habitationVersion and isactive is true");
			query.setParameter("habitationCode", habitationCode, Hibernate.INTEGER);
			query.setParameter("habitationVersion", habitationVersion, Hibernate.INTEGER);
			habitation= (Habitation) query.list().get(0);
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		finally{
			closeConnection(session);
		}
		return habitation;
	}

	@Override
	public boolean updateHabitation(Habitation habitation) {
		Session session=null;
		Transaction transaction=null;
		try{
			session = sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.saveOrUpdate(habitation);
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getEntityEffeactiveDate(Integer entityCode,Character entityType) throws HibernateException {
		Session session = null;
		Map<String,Object> data=new HashMap<String,Object>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("GET_ENTITY_EFFECTIVE_DATE");
			query.setParameter("entityCode", entityCode);
			query.setParameter("entityType", entityType);
			data.put("entityEffectiveList", query.list());
			
			query = session.getNamedQuery("GET_ENTITY_DETAILS");
			query.setParameter("entityCode", entityCode);
			query.setParameter("entityType", entityType);
			List<GetEntityEffectiveDate> getEntityEffectiveDateList=query.list();
			if(getEntityEffectiveDateList!=null && !getEntityEffectiveDateList.isEmpty()) {
				data.put("entityDetail",getEntityEffectiveDateList.get(0) );
			}
			
			
			
		} catch (HibernateException e) {
			LGDLogger.getLogger(VillageDAOImpl.class).error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return data;
	}
	
	@Override
	public Response saveEffectiveDateEntity(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId) {
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
				query.setParameter("entityType", "V");
				List<GetEntityEffectiveDate> effectiveDateListOld= query.list();
				
				if(this.validateNewEffectiveDate(villageCode, getEntityEffectiveDateList, effectiveDateListOld))	{
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
						
						
						query = session.createSQLQuery("select * from change_village_effective_date_fn(:parameter1,:userId)");
						query.setParameter("parameter1", parameter1.toString(), Hibernate.STRING);
						query.setParameter("userId", userId, Hibernate.INTEGER);
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
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<GetEntityEffectiveDate> getEntityEffeactiveDateByVersion(GetEntityEffectiveDate getEntityEffectiveDate) throws HibernateException {
		Session session = null;
		List<GetEntityEffectiveDate> getEntityEffectiveDateList=null;
		try {
			session = sessionFactory.openSession();
			String queryName="GET_ENTITY_EFFECTIVE_DATE_BY_ENTITY_VERSION";
			if(getEntityEffectiveDate.getEntityVersion()==null) {
				queryName="GET_ENTITY_HISTORY";
			}
			Query query = session.getNamedQuery(queryName);
			query.setParameter("entityCode",getEntityEffectiveDate.getEntityCode());
			query.setParameter("entityType", getEntityEffectiveDate.getEntityType());
			if(getEntityEffectiveDate.getEntityVersion()!=null) {
			query.setParameter("entityVersion", getEntityEffectiveDate.getEntityVersion());
			}
			getEntityEffectiveDateList=query.list();
	} catch (HibernateException e) {
			LGDLogger.getLogger(VillageDAOImpl.class).error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getEntityEffectiveDateList;
	}
	
	
	@Override
	public boolean validateNewEffectiveDate(Integer villageCode,List<GetEntityEffectiveDate> getEntityEffectiveDateList,List<GetEntityEffectiveDate> effectiveDateListOld) {
		boolean isvalidNewEffectiveDate=true;
		Date preDate,nextDate;
		
		Map<Integer,Date> preEffectiveDateMap=new HashMap<Integer,Date>();
				
		for(GetEntityEffectiveDate obj: effectiveDateListOld) {
			preEffectiveDateMap.put(obj.getEntityVersion(), obj.getEffectiveDate());
		}
		
		for(GetEntityEffectiveDate obj: getEntityEffectiveDateList) {
			preEffectiveDateMap.put(obj.getEntityVersion(),obj.getNewEffectiveDate());
		}
		
		for(GetEntityEffectiveDate obj: getEntityEffectiveDateList) {
		preDate=preEffectiveDateMap.get(obj.getEntityVersion()-1);
		nextDate=preEffectiveDateMap.get(obj.getEntityVersion()+1);
		if((preDate!=null && preDate.compareTo(obj.getNewEffectiveDate())>0) || (nextDate!=null && nextDate.compareTo(obj.getNewEffectiveDate())<0)) {
			isvalidNewEffectiveDate=false;
			break;
		}
	}
		return isvalidNewEffectiveDate;
		
	}
	

	@Override
	public boolean saveVillageStatus(Integer villageCode, String villageStatus, Long userId) throws Exception {
		
		boolean isStatus=false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(" select * from change_village_status_fn(:villageCode,:villageStatus,:userId)");
			query.setParameter("villageCode", villageCode);
			query.setParameter("villageStatus", villageStatus);
			query.setParameter("userId", userId.intValue());
			isStatus = (boolean)query.uniqueResult();
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return isStatus;
	}
	
	
}







