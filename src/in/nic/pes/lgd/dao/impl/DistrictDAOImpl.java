package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.ChangeDistrict;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictBean;
import in.nic.pes.lgd.bean.DistrictHistory;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.DistrictWiseLBReportBean;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GazettePublication;
import in.nic.pes.lgd.bean.GazettePublicationDateSave;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GetLandRegionWise;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.InvalidateDistrictFn;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.ParentHeirarchyBean;
import in.nic.pes.lgd.bean.ParliamentConstituencymodify;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.StandardCodes;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictPK;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.constant.CommanConstant;
import in.nic.pes.lgd.constant.DepartmentConstent;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.StandardCodeDataForm;
import in.nic.pes.lgd.forms.StandardCodeForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.interceptor.LanguageListener;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.CommonUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exception.BaseAppException;

@Repository
//@Transactional
public class DistrictDAOImpl implements DistrictDAO {
	private static final Logger log = Logger.getLogger(DistrictDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CommonService commonService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	LocalGovtBodyService localGovtBodyService;
	@Autowired
	SubDistrictDAO subDistrictDAO;

	@Autowired
	VillageDAO villageDAO;
	

	 @Autowired
	  private VillageService villageService;

	/*@Override
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubdistrictListbyDistrictCode(int districtCode) throws BaseAppException {
		Session session = null;
		List<Subdistrict> subdistrictList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			subdistrictList = new ArrayList<Subdistrict>();
			query = session.createQuery("from Subdistrict sd where sd.isactive=true " + " sd.district.districtPK.districtCode=:districtCode order by sd.subdistrictNameEnglish");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			subdistrictList = query.list();
			
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		finally{
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrictList;

	}*/

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubdistrictListbyDistrict(int districtCode) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<Subdistrict> fullSubdistrictContributingList = null;
		try {
			fullSubdistrictContributingList = new ArrayList<Subdistrict>();
			session = sessionFactory.getCurrentSession();
			query = session.createQuery("from Subdistrict sd where sd.isactive=true and sd.dlc=:districtCode order by sd.subdistrictNameEnglish");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			fullSubdistrictContributingList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return fullSubdistrictContributingList;

	}

	// /////////////

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCode(int slc) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<District> getDistrictList = null;
		try {
			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createQuery("from District d where d.slc=:slc and d.isactive=true order by districtNameEnglish");
			query.setParameter("slc", slc, Hibernate.INTEGER);
			getDistrictList = query.list();
		}
		/*
		 * } catch (Exception e) { log.error("Exception-->" + e); throw new
		 * HibernateException(e); }
		 */
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getDistrictList;
	}

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCodeGlobal(int stateCode) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<District> getDistrictList = null;
		try {
			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createQuery("from District d where d.slc=:stateCode and d.isactive=true order by districtNameEnglish");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			getDistrictList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getDistrictList;
	}

	@SuppressWarnings("unchecked")
	public List<District> getDistrictViewList(String query) throws BaseAppException {
		return sessionFactory.getCurrentSession().createQuery(query).list();

	}

	// Created by Pravin
	@Override
	public boolean saveDistrict(District district, Session session) throws BaseAppException {
		try {
			session.save(district);
			session.flush();
			if (session.contains(district)) {
				session.evict(district);
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	public int getMaxDistrictVersionbyCode(int districtCode) throws BaseAppException {
		int maxVersionCode = 0;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select COALESCE(max(district_version),1) from district where isactive=true and district_code=:districtCode");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			maxVersionCode = (Integer) query.uniqueResult();
		} catch (Exception e) {
			maxVersionCode = 0;
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return maxVersionCode;
	}

	@Override
	public int getMaxDistrictVersion(int districtCode, Session session) throws BaseAppException {
		int MaxVersionCode = 0;
		Query query = null;
		try {
			query = session.createSQLQuery("select COALESCE(max(district_version),1) from district where isactive=true and district_code=:districtCode");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			MaxVersionCode = Integer.parseInt(query.list().get(0).toString());
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}

		return MaxVersionCode;

	}

	/*@SuppressWarnings("unchecked")
	public List<District> getDistrictDetails(int districtCode, int districtVersion) throws BaseAppException {

		Session session = null;
		Query query = null;
		log.debug(" >> inside method - getDistrictDetails");
		List<District> getDistrictList = null;
		try {
			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createQuery("from District d where d.districtPK.districtCode=:districtCode and d.districtPK.districtVersion=:districtVersion");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.setParameter("districtVersion", districtVersion, Hibernate.INTEGER);
			getDistrictList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getDistrictList;
		// return
		// sessionFactory.getCurrentSession().createQuery("from District d where d.districtPK.districtCode="+
		// districtCode +" and d.districtPK.districtVersion="+
		// districtVersion).list();
	}*/

	@Override
	public int getMaxDistrictCode(String query) throws BaseAppException {
		int MaxCode = 0;
		try {
			MaxCode = Integer.parseInt(sessionFactory.getCurrentSession().createSQLQuery(query).list().get(0).toString());
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return MaxCode;
	}

	/*@Override
	@SuppressWarnings("unchecked")
	public List<State> getSubdistrictDetails(int subdistrictCode) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<State> getSubdistrictDetails = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from State where stateCode=:subdistrictCode and isactive=true");
			criteria.setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER);
			getSubdistrictDetails = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getSubdistrictDetails;
	}*/

	/*@Override
	public boolean save(MapLandRegion mapLandRegion) throws BaseAppException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			session.save(mapLandRegion);
			tx.commit();
		} catch (Exception e) {
			log.error("Exception-->" + e);
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
	}*/

	@Override
	public int getMaxRecords(String query) throws BaseAppException {
		int MaxCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			MaxCode = Integer.parseInt(session.createSQLQuery(query).list().get(0).toString()) + 1;

			tx.commit();

		} catch (Exception e) {
			return 1;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxCode;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<District> getListVillageDetails(String query) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<District> getListVillageDetails = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			getListVillageDetails = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getListVillageDetails;
	}

	@Override
	public boolean updateLrReplaces(int lrReplaces, DistrictPK districtPK, Session session) throws BaseAppException {
		District district = null;
		try {
			district = (District) session.load(District.class, districtPK);
			district.setLrReplaces(lrReplaces);// .setLrPartCode(lrPartCode);
			session.update(district);
			if (session.contains(district)) {
				session.evict(district);
			}
			return true;
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return false;
		}
	}

	// for add village
	/*@Override
	public boolean updateLReplacedBy(int lrReplacedby, DistrictPK districtPK) throws BaseAppException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			District district = (District) session.load(District.class, districtPK);
			district.setLrReplacedby(lrReplacedby);// .setLrPartCode(lrPartCode);
			session.update(district);
			tx.commit();
		} catch (Exception e) {
			log.error("Exception-->" + e);
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
	}*/

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyDistCode(int districtCode) throws BaseAppException {

		Session session = null;
		Query query = null;
		List<District> getDistrictList = null;
		try {
			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createQuery("from District d where d.isactive=true and d.districtPK.districtCode=:districtCode");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			getDistrictList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getDistrictList;

		// return
		// sessionFactory.getCurrentSession().createQuery("from District d where d.isactive=true and d.districtPK.districtCode="+
		// districtCode).list();
	}

	/*@Override
	public List<District> getDistrictListbyDistCode(String districtCode) throws BaseAppException {
		Session session = null;
		Query query = null;

		List<District> getDistrictList = null;
		try {
			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createQuery("from District d where d.isactive=true and d.districtPK.districtCode=:districtCode");
			query.setParameter("districtCode", districtCode, Hibernate.STRING);
			getDistrictList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getDistrictList;

	}*/

	@SuppressWarnings("unchecked")
	public List<District> getTargetDistrictList(int districtCode, int statecode) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<District> districtList = null;

		try {
			districtList = new ArrayList<District>();
			session = sessionFactory.openSession();
			/*
			 * query = session .createQuery(
			 * "from District d where d.isactive=true and d.state.statePK.stateCode=:statecode and d.districtPK.districtCode !=:districtCode"
			 * );
			 */
			/*
			 * query = session .createQuery(
			 * "from District d where d.isactive=true and d.state.statePK.stateCode=:statecode"
			 * );
			 */

			query = session.createQuery("from District d where d.isactive=true and d.slc=:statecode order by districtNameEnglish");
			/*
			 * query.setParameter("districtCode", districtCode,
			 * Hibernate.INTEGER);
			 */
			query.setParameter("statecode", statecode, Hibernate.INTEGER);
			districtList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtList;

	}

	@SuppressWarnings("unchecked")
	public List<District> getTargetDistrictListFinal(int districtCode, int statecode) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<District> districtList = null;

		try {
			districtList = new ArrayList<District>();
			session = sessionFactory.openSession();

			query = session.createQuery("from District d where d.isactive=true and d.slc=:statecode and d.districtPK.districtCode !=:districtCode order by districtNameEnglish");
			query.setParameter("statecode", statecode, Hibernate.INTEGER);
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);

			districtList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtList;

	}

	// for Modify village
	/*@Override
	public boolean updateIsActive(DistrictPK districtPK) throws BaseAppException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();

			District district = (District) session.load(District.class, districtPK);
			district.setIsactive(false);
			session.update(district);
			tx.commit();
		} catch (Exception e) {
			log.error("Exception-->" + e);
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
	}*/

	@Override
	@SuppressWarnings("unchecked")
	public List<District> getDistrictDetailsModify(int districtCode) throws BaseAppException {

		Session session = null;
		Query query = null;
		List<District> districtList = new ArrayList<District>();
		try {
			districtList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createQuery("from District where isactive=true and districtCode=:districtCode");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			districtList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtList;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws BaseAppException {

		Query criteria = null;
		Session session = null;
		List<GovernmentOrder> getGovermentOrderDetail = new ArrayList<GovernmentOrder>();
		try {
			session = sessionFactory.openSession();

			criteria = session.createQuery("from GovernmentOrder where order_code=:orderCode");
			criteria.setParameter("orderCode", orderCode, Hibernate.INTEGER);
			getGovermentOrderDetail = criteria.list();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getGovermentOrderDetail;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<MapLandRegion> getMapDetailsModify = null;
		try {
			session = sessionFactory.openSession();
			/*
			 * criteria = session .createQuery(
			 * "from MapLandRegion where map_attachment_code=:mapLandregionCode"
			 * ); criteria.setParameter("mapLandregionCode", mapLandregionCode,
			 * Hibernate.INTEGER);
			 */
			criteria = session.createQuery("from MapAttachment1 where map_attachment_code=" + mapLandregionCode);
			getMapDetailsModify = criteria.list();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getMapDetailsModify;
	}

	@Override
	public boolean updateMapLanRegion(int mapCode, String coordinates, int subDistrictCode, Session session) throws BaseAppException {

		try {
			MapLandRegion mapLandRegion = (MapLandRegion) session.get(MapLandRegion.class, mapCode);

			mapLandRegion.setCoordinates(coordinates);
			mapLandRegion.setLandregionCode(subDistrictCode);
			session.update(mapLandRegion);

		} catch (Exception e) {

			log.error("Exception-->" + e);
			return false;
		}

		return true;
	}

	@Override
	public boolean modifyDistrictInfo(DistrictForm DistrictForm, DistrictPK districtPk, int map_landRegionCode, Session session) throws BaseAppException {

		try {
			District district = (District) session.get(District.class, districtPk);
			if (DistrictForm.getCensus2011Code() != null && !DistrictForm.getCensus2011Code().isEmpty()) {
				district.setCensus2011Code(DistrictForm.getCensus2011Code());
			}
			if (DistrictForm.getSscode() != null && !DistrictForm.getSscode().isEmpty()) {
				district.setSscode(DistrictForm.getSscode().trim());
			}
			//district.setMapCode(map_landRegionCode);
			session.update(district);
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
		return true;
	}

	@Override
	public void ChangeDistrict(int districtCode, String districtNameEnglish, int userId, String districtNameLocal, String aliasEnglish, String aliasLocal, Session session) throws BaseAppException {
		Query query = null;
		try {
			query = session.getNamedQuery("DistrictChangeQuery").setParameter("districtCode", districtCode, Hibernate.INTEGER).setParameter("districtNameEnglish", districtNameEnglish, Hibernate.STRING)
					.setParameter("userId", userId, Hibernate.INTEGER).setParameter("districtNameLocal", districtNameLocal, Hibernate.STRING).setParameter("aliasEnglish", aliasEnglish, Hibernate.STRING)
					.setParameter("aliasLocal", aliasLocal, Hibernate.STRING);
			query.list();
			session.flush();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
	}

	@Override
	public String ChangeCrDistrict(int districtCode, String districtNameEnglish, String districtNameEnglishch, int userId, String orderno, Date orderDate, Date efectivDate, Date gazpubDate, String orderPath, String districtNameLocal,
			String aliasEnglish, String aliasLocal, Session session, Integer slc) throws BaseAppException {
		Query query = null;

		String data = null;
		try {
			query = session.getNamedQuery("DistrictChangeQuery").setParameter("districtCode", districtCode, Hibernate.INTEGER).setParameter("districtNameEnglish", districtNameEnglish, Hibernate.STRING)
					.setParameter("userId", userId, Hibernate.INTEGER).setParameter("orderNo", orderno, Hibernate.STRING).setParameter("Orderdate", orderDate, Hibernate.DATE).setParameter("effectiveDate", efectivDate, Hibernate.DATE)
					.setParameter("orderpath", orderPath, Hibernate.STRING).setParameter("districtNameEnglishch", districtNameEnglishch).setParameter("gazpubDate", gazpubDate, Hibernate.DATE)
					.setParameter("districtNameLocal", districtNameLocal, Hibernate.STRING).setParameter("aliasEnglish", aliasEnglish, Hibernate.STRING).setParameter("aliasLocal", aliasLocal, Hibernate.STRING).setParameter("slc", slc);
			@SuppressWarnings("unchecked")
			List<ChangeDistrict> changedis = query.list();
			if (changedis.size() > 0) {
				data = changedis.get(0).getChange_district_fn().toString();
				/*
				 * String
				 * temp[]=changedis.get(0).getChange_district_fn().toString
				 * ().split(","); tid=Integer.parseInt(temp[0]);
				 * oderCode=Integer.parseInt(temp[1]); char t_type='D';
				 * EmailSmsThread est= new EmailSmsThread(tid,
				 * t_type,emailService); est.start();
				 */

			}
			// ChangeDistrict changedis=(ChangeDistrict) query.list().get(0);
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
			return data;

		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<District> getDistrict(int districtCode) throws BaseAppException {
		List<District> vlgLst = null;
		Session session = null;
		Query query = null;
		try {
			vlgLst = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createQuery("from District v where districtCode=:districtCode and isactive=true order by v.districtNameEnglish");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			vlgLst = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			vlgLst = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return vlgLst;

	}

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyDistrict(int districtCode) throws BaseAppException {
		List<District> vlgLst = null;
		Session session = null;
		Query query = null;
		try {
			vlgLst = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createQuery("from District v where v.state.statePK.stateCode=:districtCode and isactive=true order by v.districtNameEnglish");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			vlgLst = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			vlgLst = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return vlgLst;

	}

	@Override
	public boolean publishDistrict(District district, Session session) throws BaseAppException {
		try {

			session.saveOrUpdate(district);
			session.flush();
			session.refresh(district);

		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<District> getListDistrictCode(int subDistrictCode) throws BaseAppException {
		List<District> vlgLst = null;
		Session session = null;
		Query query = null;
		try {
			vlgLst = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createQuery("from District v where v.state.statePK.stateCode=:subDistrictCode and isactive=true order by v.districtNameEnglish");
			query.setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER);
			vlgLst = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			vlgLst = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return vlgLst;

	}

	// getVillageDetailsModify for Modify Village
	@SuppressWarnings("unchecked")
	public List<District> getDistrictModify(int villageCode) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<District> getDistrictModify = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from District where district_code=:villageCode and isactive=true");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			getDistrictModify = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getDistrictModify;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getVillageDetailsModify(int villageCode) throws BaseAppException {

		Query criteria = null;
		Session session = null;
		List<Village> getVillageDetailsModify = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Village where village_code=" + villageCode + " and isactive=true");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			getVillageDetailsModify = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getVillageDetailsModify;

	}

	/*@Override
	public boolean save(District district, Session session) throws BaseAppException {
		try {
			session.saveOrUpdate(district);
			session.flush();
			session.refresh(district);
			return true;
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
	}*/

	@Override
	public boolean update(String query, Session session) throws BaseAppException {
		try {
			session.createSQLQuery(query).executeUpdate();
			session.flush();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	@Override
	public District getSubDistrictDetail(DistrictPK districtPK, Session session) throws BaseAppException {
		District district = null;
		try {
			district = (District) session.load(District.class, districtPK);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return district;
	}

	/*@Override
	public boolean saveOrderDetails(GovernmentOrder governmentOrder, Session session) throws BaseAppException {
		try {
			session.save(governmentOrder);
			session.flush();
			session.refresh(governmentOrder);

		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}
*/
	/*@Override
	public boolean save(MapLandRegion mapLandRegion, Session session) throws BaseAppException {

		try {

			session.save(mapLandRegion);
		} catch (Exception e) {

			log.error("Exception-->" + e);

			return false;
		}

		return true;
	}*/

	@Override
	public boolean updateIsActive(boolean isActive, DistrictPK DistrictPK, Session session) throws BaseAppException {
		District district = null;
		try {
			district = (District) session.load(District.class, DistrictPK);
			district.setIsactive(isActive);
			session.update(district);
			session.flush();
			if (session.contains(district)) {
				session.evict(district);
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	@Override
	public boolean updatevillage(boolean isActive, VillagePK villagepk, Session session) throws BaseAppException {
		Village village = null;
		try {
			village = (Village) session.load(Village.class, villagepk);
			village.setIsactive(isActive);
			session.update(village);
			session.flush();
			if (session.contains(village)) {
				session.evict(village);
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	@Override
	public boolean updateActive(boolean isActive, SubdistrictPK DistrictPK, Session session) throws BaseAppException {

		Subdistrict subdistrict = null;
		try {
			subdistrict = (Subdistrict) session.load(Subdistrict.class, DistrictPK);
			subdistrict.setIsactive(isActive);
			session.update(subdistrict);
			session.flush();
			if (session.contains(subdistrict)) {
				session.evict(subdistrict);
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<GazettePublication> getGazettePublication() throws BaseAppException {

		Session session = null;
		Query query = null;
		List<GazettePublication> gazettePublication = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getGazettePublicationDateDetails");

			gazettePublication = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return gazettePublication;
	}

	@SuppressWarnings("unchecked")
	public List<GazettePublicationDateSave> getGazettePublicationsave(String orderCode, Date Gazettedate) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<GazettePublicationDateSave> gazettePublication = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getGazettePublicationDateDetailsSave").setParameter("ordercode", orderCode).setParameter("gazPubDate", Gazettedate);
			gazettePublication = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return gazettePublication;
	}

	@SuppressWarnings("unchecked")
	public List<ParliamentConstituencymodify> getpcCode(Integer stateCode, char constituencyType, Integer pcCode, Integer limit, Integer offset) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<ParliamentConstituencymodify> Parliamentconstituencymodify = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getParliamentConstituencymodify").setParameter("statecode", stateCode, Hibernate.INTEGER).setParameter("constituencyType", constituencyType, Hibernate.CHARACTER)
					.setParameter("pcCode", pcCode, Hibernate.INTEGER).setParameter("limit", limit, Hibernate.INTEGER).setParameter("offset", offset, Hibernate.INTEGER);
			Parliamentconstituencymodify = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return Parliamentconstituencymodify;
	}

	public List<StandardCodeDataForm> updateStandardCode(StandardCodeForm standardCodeForm) throws BaseAppException {
		Session session = sessionFactory.openSession();
	Transaction transaction=null ;
	boolean isMapfinalised=false;
	List<StandardCodeDataForm> standardCodesDetailsupdate = new ArrayList<StandardCodeDataForm>();
		Integer userId =standardCodeForm.getUserId();
	List<StandardCodeDataForm> standardCodeDataDetails = standardCodeForm.getStandardCodeDataDetails();
		
		try {
			String entityName = standardCodeForm.getEntityName();
			
			 if ((entityName.trim().equalsIgnoreCase("block")) || (entityName.trim().equalsIgnoreCase("blockDistUser"))) {
				 
				 if (!standardCodeDataDetails.isEmpty()) {
						List<String> updateEntityArr=new ArrayList<String>();
						for (StandardCodeDataForm element : standardCodeDataDetails) {
							if(element.getEntityNameLocal().hashCode()!=element.getEntityNameLocalch().hashCode() ||element.getSsCode().hashCode()!=element.getSsCodech().hashCode()) {
								standardCodesDetailsupdate.add(element);
								updateEntityArr.add(element.getEntityCode()+"#"+element.getEntityNameLocalch()+"#"+element.getSsCodech());
							}
						}
						
						if(updateEntityArr.size()>0) {
							
							Query query 	= 	session.createSQLQuery("select * from change_block_localname_fn(:updateString,:userId,:stateCode)");
							String updateString= updateEntityArr.toString();
							
							
							String updateStringTrim = updateString.substring(1, updateString.length()-1);
							
							query.setParameter("updateString", updateStringTrim); // updateString=villagecode#newlocalname#sscode,
							query.setParameter("userId", userId);
							query.setParameter("stateCode", Integer.parseInt(standardCodeForm.getStateCode()));
							isMapfinalised = (boolean)query.uniqueResult();
							System.out.println("isMapfinalised"+isMapfinalised);
						}
					}
			
			
			 } else if ((entityName.trim().equalsIgnoreCase("village")) || (entityName.trim().equalsIgnoreCase("villageDistUser"))) {
				
				//List<StandardCodeDataForm> standardCodeDataDetails = standardCodeForm.getStandardCodeDataDetails();
				if (!standardCodeDataDetails.isEmpty()) {
					List<String> updateEntityArr=new ArrayList<String>();
					for (StandardCodeDataForm element : standardCodeDataDetails) {
						if(element.getEntityNameLocal().hashCode()!=element.getEntityNameLocalch().hashCode() ||element.getSsCode().hashCode()!=element.getSsCodech().hashCode()) {
							standardCodesDetailsupdate.add(element);
							updateEntityArr.add(element.getEntityCode()+"#"+element.getEntityNameLocalch()+"#"+element.getSsCodech());
						}
					}
					
					if(updateEntityArr.size()>0) {
						
						Query query 	= 	session.createSQLQuery("select * from change_village_localname_fn(:updateString,:userId)");
						String updateString= updateEntityArr.toString();
						
						
						String updateStringTrim = updateString.substring(1, updateString.length()-1);
						
						query.setParameter("updateString", updateStringTrim); // updateString=villagecode#newlocalname#sscode,
						query.setParameter("userId", userId);
						isMapfinalised = (boolean)query.uniqueResult();
						System.out.println("isMapfinalised"+isMapfinalised);
					}
				}
			}
				// Added By Susham Singh 14 oct 2019
				
				else if ((entityName.trim().equalsIgnoreCase("subdistrict")) || (entityName.trim().equalsIgnoreCase("subdistrictDistUser"))) {
					//boolean isMapfinalised=false;
					//List<StandardCodeDataForm> standardCodeDataDetails = standardCodeForm.getStandardCodeDataDetails();
					if (!standardCodeDataDetails.isEmpty()) {
						List<String> updateEntityArr=new ArrayList<String>();
						for (StandardCodeDataForm element : standardCodeDataDetails) {
							if(element.getEntityNameLocal().hashCode()!=element.getEntityNameLocalch().hashCode() ||element.getSsCode().hashCode()!=element.getSsCodech().hashCode()) {
								standardCodesDetailsupdate.add(element);
								updateEntityArr.add(element.getEntityCode()+"#"+element.getEntityNameLocalch()+"#"+element.getSsCodech());
							}
						}
						
						if(updateEntityArr.size()>0) {
							
							Query query 	= 	session.createSQLQuery("select * from change_subdistrict_localname_fn(:updateString,:userId)");
							String updateString= updateEntityArr.toString();
							
							
							String updateStringTrim = updateString.substring(1, updateString.length()-1);
							
							query.setParameter("updateString", updateStringTrim);
							query.setParameter("userId", userId);
							isMapfinalised = (boolean)query.uniqueResult();
							System.out.println("isMapfinalised"+isMapfinalised);
						}
					}
				
				
			}
				else if ((entityName.trim().equalsIgnoreCase("locabody"))) {
					//boolean isMapfinalised=false;
					//List<StandardCodeDataForm> standardCodeDataDetails = standardCodeForm.getStandardCodeDataDetails();
					if (!standardCodeDataDetails.isEmpty()) {
						List<String> updateEntityArr=new ArrayList<String>();
						for (StandardCodeDataForm element : standardCodeDataDetails) {
							if(element.getEntityNameLocal().hashCode()!=element.getEntityNameLocalch().hashCode() ||element.getSsCode().hashCode()!=element.getSsCodech().hashCode()) {
								standardCodesDetailsupdate.add(element);
								updateEntityArr.add(element.getEntityCode()+"#"+element.getEntityNameLocalch()+"#"+element.getSsCodech());
							}
						}
						
						if(updateEntityArr.size()>0) {
							
							Query query 	= 	session.createSQLQuery("select * from change_localbody_localname_fn(:updateString,:userId)");
							String updateString= updateEntityArr.toString();
							
							
							String updateStringTrim = updateString.substring(1, updateString.length()-1);
							
							query.setParameter("updateString", updateStringTrim);
							query.setParameter("userId", userId);
							isMapfinalised = (boolean)query.uniqueResult();
							System.out.println("isMapfinalised"+isMapfinalised);
						}
					}
				
				
			}
			 
			 
				else if ((entityName.trim().equalsIgnoreCase("state"))){
					//boolean isMapfinalised=false;
					//List<StandardCodeDataForm> standardCodeDataDetails = standardCodeForm.getStandardCodeDataDetails();
					if (!standardCodeDataDetails.isEmpty()) {
						List<String> updateEntityArr=new ArrayList<String>();
						for (StandardCodeDataForm element : standardCodeDataDetails) {
							if(element.getEntityNameLocal().hashCode()!=element.getEntityNameLocalch().hashCode()) {
								standardCodesDetailsupdate.add(element);
								updateEntityArr.add(element.getEntityCode()+"#"+element.getEntityNameLocalch());
							}
						}
						
						if(updateEntityArr.size()>0) {
							
							Query query 	= 	session.createSQLQuery("select * from change_state_localname_fn(:updateString,:userId)");
							String updateString= updateEntityArr.toString();
							
							
							String updateStringTrim = updateString.substring(1, updateString.length()-1);
							
							query.setParameter("updateString", updateStringTrim);
							query.setParameter("userId", userId);
							isMapfinalised = (boolean)query.uniqueResult();
							System.out.println("isMapfinalised"+isMapfinalised);
						}
					}
				
				
			}
			 
			else {
				 transaction = session.beginTransaction();
				//List<StandardCodeDataForm> standardCodeDataDetails = standardCodeForm.getStandardCodeDataDetails();

				

				if (!standardCodeDataDetails.isEmpty()) {

					int i = 0;
					for (StandardCodeDataForm element : standardCodeDataDetails) {
						StringBuilder SQL = new StringBuilder(" update ");
						if (!element.getEntityNameLocal().trim().equalsIgnoreCase(element.getEntityNameLocalch().trim()) || (element.getCensus2011Code()!=null && element.getCensus2011Code().compareTo(element.getCensus2011Codech()) != 0)
								|| (element.getSsCode()!=null && !element.getSsCode().trim().equalsIgnoreCase(element.getSsCodech().trim()))) {

							if ((entityName.trim().equalsIgnoreCase("state"))) {
								SQL.append(" state set state_name_local=:entityNameLocal ");
							} else if ((entityName.trim().equalsIgnoreCase("district")) || (entityName.trim().equalsIgnoreCase("districtDistUser"))) {
								SQL.append(" district set district_name_local=:entityNameLocal ");
							} else if ((entityName.trim().equalsIgnoreCase("subdistrict")) || (entityName.trim().equalsIgnoreCase("subdistrictDistUser"))) {
								SQL.append(" subdistrict set subdistrict_name_local=:entityNameLocal ");
							}
							/* added by pooja on 09-06-2015 */
							else if ((entityName.trim().equalsIgnoreCase("block")) || (entityName.trim().equalsIgnoreCase("blockDistUser"))) {
								SQL.append(" block set block_name_local=:entityNameLocal ");
							} else if ((entityName.trim().equalsIgnoreCase("village")) || (entityName.trim().equalsIgnoreCase("villageDistUser"))) {
								SQL.append(" village set village_name_local=:entityNameLocal ");
							} else if (entityName.trim().equalsIgnoreCase("locabody")) {
								SQL.append(" localbody set local_body_name_local=:entityNameLocal ");
							}else if(entityName.trim().equalsIgnoreCase("adminLevel")){
								SQL.append(" administration_unit_level set unit_level_name_local=:entityNameLocal ");
							}else if(entityName.trim().equalsIgnoreCase("adminEntity")){
								SQL.append(" administration_unit_entity set admin_entity_name_local=:entityNameLocal ");
							}else if(entityName.trim().equalsIgnoreCase("dept") || entityName.trim().equalsIgnoreCase("org")){
								if(("S").equals(standardCodeForm.getCategory())){
									SQL.append(" organization set org_name_local=:entityNameLocal ");
								}else{
									SQL.append(" org_located_at_levels set org_level_specific_name_local=:entityNameLocal ");
								}
								
							}else if(entityName.trim().equalsIgnoreCase("orgunit")){
								SQL.append(" org_units set org_unit_name_local=:entityNameLocal ");
							}
							/* Modified by pooja on 09-06-2015 */
							/*
							 * if(element.getCensus2011Codech()!=null &&
							 * !("").equals(element.getCensus2011Codech().trim()) &&
							 * element.getCensus2011Code().compareTo(element.
							 * getCensus2011Codech()) != 0){
							 * SQL.append(" ,census_2011_code=:census2011Code "); }
							 */
							if (!((entityName.trim().equalsIgnoreCase("block")) || (entityName.trim().equalsIgnoreCase("blockDistUser"))) && element.getCensus2011Codech() != null && !("").equals(element.getCensus2011Codech().trim())
									&& element.getCensus2011Code().compareTo(element.getCensus2011Codech()) != 0) {
								SQL.append(" ,census_2011_code=:census2011Code ");
							}
							if (!(entityName.trim().equalsIgnoreCase("state")) && !(entityName.trim().equalsIgnoreCase("block")) && !(entityName.trim().equalsIgnoreCase("blockDistUser")) && element.getSsCodech() != null
									&& !("").equals(element.getSsCodech().trim()) && !element.getSsCode().trim().equalsIgnoreCase(element.getSsCodech().trim())) {
								SQL.append(" ,sscode=:sscode ");
							}
							if (((entityName.trim().equalsIgnoreCase("block")) || (entityName.trim().equalsIgnoreCase("blockDistUser"))) && element.getSsCodech() != null && !("").equals(element.getSsCodech().trim())
									&& !element.getSsCode().trim().equalsIgnoreCase(element.getSsCodech().trim())) {
								SQL.append(" ,ss_code=:sscode ");
							}

							SQL.append(" where ");
							if ((entityName.trim().equalsIgnoreCase("state"))) {
								SQL.append(" state_code=:entityCode ");
							} else if ((entityName.trim().equalsIgnoreCase("district")) || (entityName.trim().equalsIgnoreCase("districtDistUser"))) {
								SQL.append(" district_code=:entityCode ");
							} else if ((entityName.trim().equalsIgnoreCase("subdistrict")) || (entityName.trim().equalsIgnoreCase("subdistrictDistUser"))) {
								SQL.append(" subdistrict_code=:entityCode ");

							}
							/* added by pooja on 09-06-2015 */
							else if ((entityName.trim().equalsIgnoreCase("block")) || (entityName.trim().equalsIgnoreCase("blockDistUser"))) {
								SQL.append(" block_code=:entityCode ");

							} else if ((entityName.trim().equalsIgnoreCase("village")) || (entityName.trim().equalsIgnoreCase("villageDistUser"))) {
								SQL.append(" village_code=:entityCode ");
							} else if (entityName.trim().equalsIgnoreCase("locabody")) {
								SQL.append(" local_body_code=:entityCode ");
							}else if(entityName.trim().equalsIgnoreCase("adminLevel")){
								SQL.append(" admin_unit_level_code=:entityCode ");
							}else if(entityName.trim().equalsIgnoreCase("adminEntity")){
								SQL.append(" admin_unit_entity_code=:entityCode ");
							}
							else if(entityName.trim().equalsIgnoreCase("dept") || entityName.trim().equalsIgnoreCase("org")){
							 if(("S").equals(standardCodeForm.getCategory())){
								SQL.append(" olc=:entityCode ");
							}else{
								SQL.append(" org_located_level_code=:entityCode");
								}
							}else if(entityName.trim().equalsIgnoreCase("orgunit")){
								SQL.append(" org_unit_code=:entityCode ");
							}
							if(!(entityName.trim().equalsIgnoreCase("dept") || entityName.trim().equalsIgnoreCase("org"))){
							SQL.append(" and isactive=:isactive ");
							}
							SQLQuery query = session.createSQLQuery(SQL.toString());
							query.setParameter("entityNameLocal", element.getEntityNameLocalch().trim(), Hibernate.STRING);
							/* Modified by pooja on 09-06-2015 */
							/*
							 * if(element.getCensus2011Codech()!=null &&
							 * !("").equals(element.getCensus2011Codech().trim()) &&
							 * element.getCensus2011Code().compareTo(element.
							 * getCensus2011Codech()) != 0){
							 * query.setParameter("census2011Code",
							 * Integer.parseInt(
							 * element.getCensus2011Codech()),Hibernate.INTEGER); }
							 */
							if (!((entityName.trim().equalsIgnoreCase("block")) || (entityName.trim().equalsIgnoreCase("blockDistUser"))) && element.getCensus2011Codech() != null && !("").equals(element.getCensus2011Codech().trim())
									&& element.getCensus2011Code().compareTo(element.getCensus2011Codech()) != 0) {
								query.setParameter("census2011Code", element.getCensus2011Codech(), Hibernate.STRING);
							}
							if (!(entityName.trim().equalsIgnoreCase("state")) && element.getSsCodech() != null && !("").equals(element.getSsCodech().trim()) && !element.getSsCode().trim().equalsIgnoreCase(element.getSsCodech().trim())) {
								query.setParameter("sscode", element.getSsCodech().trim(), Hibernate.STRING);
							}
							query.setParameter("entityCode", element.getEntityCode(), Hibernate.INTEGER);
							if(!(entityName.trim().equalsIgnoreCase("dept") || entityName.trim().equalsIgnoreCase("org"))){
							query.setParameter("isactive", true, Hibernate.BOOLEAN);
							}
							int updateflag = query.executeUpdate();
							if (updateflag > 0) {
								standardCodesDetailsupdate.add(i, element);
								i++;
							}
						}

					}
					transaction.commit();
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return standardCodesDetailsupdate;
	}

	@SuppressWarnings("unchecked")
	public List<StandardCodes> getStandardCode(StandardCodeForm standardCodeForm) throws BaseAppException {
		Session session = null;
		Query query = null;
		String squery = null;
		Integer entityCode = null;
		Integer entityType=null;
		int lbType = 0;
		int parentCode = 0;
		int disCode = 0;
		List<GetLandRegionWise> districtPanchayatListDisUser = new ArrayList<GetLandRegionWise>();
		List<StandardCodes> standardCodes = null;
		List<StandardCodes> standardCodesfordistrict = new ArrayList<StandardCodes>();
		String category=standardCodeForm.getCategory();
		try {

			String entity = standardCodeForm.getEntityName();
			if (entity.trim().equalsIgnoreCase("state")) {
				squery = "getStandardCodesState";
				entityCode = Integer.parseInt(standardCodeForm.getStateCode());
			} else if (entity.trim().equalsIgnoreCase("districtDistUser")) {
				squery = "getStandardCodesDistrictforDistUser";
				entityCode = Integer.parseInt(standardCodeForm.getDistrictCode());
			} else if (entity.trim().equalsIgnoreCase("subdistrictDistUser")) {
				squery = "getStandardCodesSubDistrictforDistUser";
				String sdistCode = standardCodeForm.getDistrictCode().toString();
				int index = sdistCode.indexOf(',');
				if (index >= 0) {
					sdistCode = sdistCode.substring(0, index);
				}
				entityCode = Integer.parseInt(sdistCode);
			} else if (entity.trim().equalsIgnoreCase("villageDistUser")) {
				squery = "getStandardCodesVillageforDistUser";
				String sdistCode = standardCodeForm.getSubdistrictCodes().toString();
				int index = sdistCode.indexOf(',');
				if (index >= 0) {
					sdistCode = sdistCode.substring(0, index);
				}
				entityCode = Integer.parseInt(sdistCode);
			} else if (entity.trim().equalsIgnoreCase("district")) {
				squery = "getStandardCodesDistrict";
				entityCode = Integer.parseInt(standardCodeForm.getStateCode());
			} else if (entity.trim().equalsIgnoreCase("subdistrict")) {
				squery = "getStandardCodesSubDistrict";
				String sdistCode = standardCodeForm.getDistrictCode().toString();
				int index = sdistCode.indexOf(',');
				if (index >= 0) {
					sdistCode = sdistCode.substring(0, index);
				}
				entityCode = Integer.parseInt(sdistCode);
			} else if (entity.trim().equalsIgnoreCase("village")) {
				squery = "getStandardCodesVillage";
				String sdistCode = standardCodeForm.getSubdistrictCodes().toString();
				int index = sdistCode.indexOf(',');
				if (index >= 0) {
					sdistCode = sdistCode.substring(0, index);
				}
				entityCode = Integer.parseInt(sdistCode);
			}
			/* Added by pooja on 09-06-2015 */
			else if (entity.trim().equalsIgnoreCase("block") || entity.trim().equalsIgnoreCase("blockDistUser")) {
				squery = "getStandardCodesblock";
				String sdistCode = standardCodeForm.getDistrictCode().toString();
				int index = sdistCode.indexOf(',');
				if (index >= 0) {
					sdistCode = sdistCode.substring(0, index);
				}
				entityCode = Integer.parseInt(sdistCode);

			}
			
			else if(entity.trim().equalsIgnoreCase("adminLevel"))
			{
				squery = "getStandardCodesAdminLevel";
				entityCode = Integer.parseInt(standardCodeForm.getStateCode());
			}
			else if(entity.trim().equalsIgnoreCase("adminEntity"))
			{
				
				if(("D").equals(standardCodeForm.getEntityTpye())){
					squery = "getStandardCodesAdminEntityDistrict";
				}else{
					squery = "getStandardCodesAdminEntityState";
				}
				
				entityCode = standardCodeForm.getEntity_code();
			}
			else if(entity.trim().equalsIgnoreCase("dept"))
			{
				
				if(("S").equals(category)){
					entityCode=Integer.parseInt(standardCodeForm.getStateCode());
				}else{
					entityCode =getEntityCode(standardCodeForm.getEntityCodes());
				}
				
				 squery = "getDeptList"; 
				 entityType= CommonUtil.setCategoryLevel(category.charAt(0));
				 
			}
			else if(entity.trim().equalsIgnoreCase("org"))
			{
				
				if(("S").equals(category)){
					entityCode=standardCodeForm.getDeptList();
					 squery = "getOrgStateList";
					 
					 
				}else{
					entityCode =getEntityCode(standardCodeForm.getEntityCodes());
					 squery = "getOrgLevelList";
				}
				
				 
				 entityType= CommonUtil.setCategoryLevel(category.charAt(0));
				 
			}
			
			else if(entity.trim().equalsIgnoreCase("orgunit"))
			{
				
					entityType= CommonUtil.setCategoryLevel(standardCodeForm.getEntityTpye().charAt(0));
					entityCode=standardCodeForm.getOrgCode();
					 squery = "getOrgUnitsList";
			}
			/* ended by pooja */
			else {

				entityCode = Integer.parseInt(standardCodeForm.getStateCode());
				String[] vc = entity.split(":");
				lbType = Integer.parseInt(vc[0]);
				if (lbType == 2) {
					squery = "getStandardCodesforLBWithParent";
					parentCode = Integer.parseInt(standardCodeForm.getDistrictPanchyat());
				} else if (lbType == 3) {
					squery = "getStandardCodesforLBWithParent";
					if (standardCodeForm.getTiertype().equals("3"))
						parentCode = Integer.parseInt(standardCodeForm.getIntermediatePanchyat());
					else
						parentCode = Integer.parseInt(standardCodeForm.getDisPanchyat());

				} else
					squery = "getStandardCodesforLB";

			}

			session = sessionFactory.openSession();
			if(entity.trim().equalsIgnoreCase("orgunit")){
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER).setParameter("entityType", entityType, Hibernate.INTEGER);
			}else if(entity.trim().equalsIgnoreCase("org") && ("S").equals(category)){
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER);
			}
			else if(entity.trim().equalsIgnoreCase("org") && !("S").equals(category)){
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER).setParameter("entityType", entityType, Hibernate.INTEGER).setParameter("orgCode", standardCodeForm.getDeptList(), Hibernate.INTEGER);
			}
			else if(entity.trim().equalsIgnoreCase("dept")){
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER).setParameter("entityType", entityType, Hibernate.INTEGER);
			}else if(entity.trim().equalsIgnoreCase("adminEntity"))
			query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER).setParameter("adminCode", standardCodeForm.getAdminCode(), Hibernate.INTEGER).setParameter("parentAdminCode", standardCodeForm.getParentAdminCode(), Hibernate.INTEGER);
			else if (lbType == 0)
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER);
			else if (lbType == 3 || lbType == 2)
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER).setParameter("type", lbType, Hibernate.INTEGER).setParameter("parent", parentCode, Hibernate.INTEGER);
			else
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER).setParameter("type", lbType, Hibernate.INTEGER);

			standardCodes = query.list();
			if (lbType == 1 && !standardCodeForm.getDistrictCode().contains(",")) {
				disCode = Integer.parseInt(standardCodeForm.getDistrictCode());
				districtPanchayatListDisUser = localGovtBodyService.getLandRegionWise(1, 'D', disCode, "P");
				if (districtPanchayatListDisUser.size() == 0) {
					districtPanchayatListDisUser = localGovtBodyService.getLandRegionWise(1, 'D', disCode, "T");
				}
				for (StandardCodes obj : standardCodes) {
					lbType = obj.getEntityCode();
					for (GetLandRegionWise val : districtPanchayatListDisUser) {
						if (val.getLocalBodyCode() == lbType) {
							standardCodesfordistrict.add(obj);
							break;
						}

					}
				}
				return standardCodesfordistrict;

			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return standardCodes;
	}
	
	/**
	 * 
	 * @param entityCodes
	 * @return
	 */
	private Integer getEntityCode(String entityCodes) {
		Integer entityCode = 0;
		if (entityCodes != null && !"".equals(entityCodes)) {
			try {
				String[] entityCodeArray = entityCodes.split(",");
				entityCode = Integer.parseInt(entityCodeArray[entityCodeArray.length - 1]);
			} catch (NumberFormatException e) {
				entityCode = 0;
			}
		}
		return entityCode;
	}

	@SuppressWarnings("unchecked")
	public List<DistrictHistory> getDistrictHistoryDetail(char districtNameEnglish, int districtCode) throws BaseAppException {

		Session session = null;
		Query query = null;
		List<DistrictHistory> districtList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getDistrictHistoryDetails").setParameter("districtNameEnglish", districtNameEnglish).setParameter("districtCode", districtCode);
			districtList = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return districtList;
	}

	
	/*@Override
	public boolean saveMapLandRegion(MapLandRegion mapLandRegion, Session session) {

		try {

			session.save(mapLandRegion);
			session.flush();
			session.refresh(mapLandRegion);

		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}*/

	/*@Override
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubdistrictDetails(String query) throws BaseAppException {

		Query criteria = null;
		Session session = null;
		List<Subdistrict> getSubdistrictDetails = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			getSubdistrictDetails = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getSubdistrictDetails;
	}*/

	@Override
	@SuppressWarnings("unchecked")
	public List<Headquarters> getHeadquarterModify(char entityType, int entityCode, int entityversion) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<Headquarters> headquartersList = new ArrayList<Headquarters>();
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Headquarters where lrlc=:entityCode and regionType=:entityType and isactive=true");
			criteria.setParameter("entityType", entityType, Hibernate.CHARACTER);
			criteria.setParameter("entityCode", entityCode, Hibernate.INTEGER);
			headquartersList = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return headquartersList;
	}

	/*@Override
	public void SetGovermentOrderEntity(String entityCode, char entityType) throws BaseAppException {
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = sessionFactory.getCurrentSession().getNamedQuery("GovOrderEntityWiseQuery").setParameter("entityCode", entityCode, Hibernate.STRING).setParameter("entityType", entityType, Hibernate.CHARACTER);
			query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}*/

	@Override
	@SuppressWarnings("unchecked")
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion, int minorVersion) throws BaseAppException {
		Session session = null;
		Query criteria = null;
		List<GetGovernmentOrderDetail> GovOrderDetail = new ArrayList<GetGovernmentOrderDetail>();
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("GovOrderDetailMinor").setParameter("entityType", entityType).setParameter("entityCode", entityCode).setParameter("entityVersion", entityVersion).setParameter("minorVersion", minorVersion);
			GovOrderDetail = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return GovOrderDetail;
	}

	@Override
	public boolean invalidateFunctionCall(String districtCodes, int districtCode, long roleCode, String subDistrictCodes) throws BaseAppException {
		Session session = null;
		Query query = null;
		int rCode = 0;
		try {
			rCode = (int) roleCode;
			session = sessionFactory.openSession();
			query = sessionFactory.getCurrentSession().getNamedQuery("invalidateDistrictFn");
			query.setParameter("districtCodes", districtCodes, Hibernate.STRING);
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.setParameter("roleCode", rCode, Hibernate.INTEGER);
			query.setParameter("subDistrictCodes", subDistrictCodes, Hibernate.STRING);
			query.list();
			return true;
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDistrictListbyStateCode(int stateCode, String districtList) throws BaseAppException {
		List<District> distList = null;
		List<State> stateList = null;
		String stateName = null;
		Session session = null;
		//SQLQuery query1 = null;
		//SQLQuery query2 = null;

		try {
			distList = new ArrayList<District>();
			stateList = new ArrayList<State>();

			session = sessionFactory.openSession();
			stateList = session.createQuery("from State where isactive=true and stateCode=" + stateCode).list();
			stateName = stateList.get(0).getStateNameEnglish();
			distList = session.createQuery("from District d where d.districtCode not in (" + districtList + ") and d.slc=" + stateCode + " and d.isactive=true order by districtNameEnglish").list();
			for (int i = 0; i < distList.size(); i++) {
				distList.get(i).setDistrictNameEnglish(distList.get(i).getDistrictNameEnglish() + " (" + stateName + ")");
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return distList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCodeForListBox(int stateCode) throws BaseAppException {
		List<District> distList = null;
		//List<State> stateList = null;
		//String stateName = null;
		Query query = null;
		Session session = null;
		try {
			distList = new ArrayList<District>();
			//stateList = new ArrayList<State>();
			session = sessionFactory.openSession();
			/*
			 * stateList = session .createQuery(
			 * "from State where isactive=true and stateCode=" +
			 * stateCode).list(); stateName =
			 * stateList.get(0).getStateNameEnglish();
			 */
			query = session.createQuery("from District d where d.slc=:slc and d.isactive=true order by districtCode");
			query.setParameter("slc", stateCode, Hibernate.INTEGER);
			distList = query.list();
			for (int i = 0; i < distList.size(); i++) {
				distList.get(i).getDistrictNameEnglish();
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return distList;
	}

	@Override
	public District getSingleDistrictDetailsMaxVersion(String sQuery) throws BaseAppException {
		District d = null;
		Session session = null;
		try {
			d = new District();
			session = sessionFactory.openSession();
			d = (District) session.createSQLQuery(sQuery).addEntity("District", District.class).list().get(0);
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return d;
	}

	/* Retrieve files from the attachment table */

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getAttacmentdetail(int orderCode) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Attachment where announcement_id=:orderCode").setParameter("orderCode", orderCode, Hibernate.INTEGER);
			attachmentList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
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
	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode, Session session) throws BaseAppException {
		Query query = null;
		List<EntityWiseMapDetailsPojo> EntityWiseMapDetail = null;
		try {
			query = session.getNamedQuery("getEntityWiseMapDetailsFn").setParameter("entityType", entityType).setParameter("entityCode", entityCode);
			EntityWiseMapDetail = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);

		}
		return EntityWiseMapDetail;

	}

	/*@Override
	public boolean modifyDistrictCrInfo(DistrictForm DistrictForm, int distcode, int map_landRegionCode, String cord1, Session session1) throws BaseAppException {

		
		 * session1.createSQLQuery("update district set  alias_english='"
		 * +DistrictForm.getAliasEnglish() +"', alias_local='"
		 * +DistrictForm.getAliasLocal() +"', census_2011_code="
		 * +DistrictForm.getCensus2011Code() +" , sscode='"
		 * +DistrictForm.getSscode() +"' , coordinates='" +cord1
		 * +"' where  subdistrict_code="
		 * +distcode+" and isactive=true").executeUpdate()
		 
		try {
			
			 * Criteria criteria=session.createCriteria(Subdistrict.class);
			 * criteria.add(Restrictions.eq("subdistrictCode",
			 * subdistrictCode)); criteria.add(Restrictions.eq("isactive",
			 * true));
			 

			List<DistrictDataForm> listSubdistrict = new ArrayList<DistrictDataForm>();
			listSubdistrict = DistrictForm.getListDistrictDetails();

			session1.createSQLQuery(
					"update district set  alias_english='" + listSubdistrict.get(0).getAliasEnglish() + "', alias_local='" + listSubdistrict.get(0).getAliasLocal() + "', census_2011_code=" + listSubdistrict.get(0).getCensus2011Code() + " , sscode='"
							+ listSubdistrict.get(0).getSscode() + "' , coordinates='" + cord1 + "', map_attachment_code='" + map_landRegionCode + "' where  district_code=" + distcode + " and isactive=true").executeUpdate();

			
			 * Subdistrict subDist = (Subdistrict)session.get(Subdistrict.class,
			 * subdistrictPK); if(subDistrictForm.getSscode() != null &&
			 * !subDistrictForm.getSscode().isEmpty()) {
			 * subDist.setSscode(subDistrictForm.getSscode().trim()); }
			 * subDist.setIsactive(true);
			 * subDist.setMapLandregionCode(map_landRegionCode);
			 * subDist.setLrReplaces(1); subDist.setLrReplacedby(2);
			 * if(subDistrictForm.getCensus2011Code() != null &&
			 * !subDistrictForm.getCensus2011Code().isEmpty())
			 * subDist.setCensus2011Code
			 * (Integer.parseInt(subDistrictForm.getCensus2011Code()));
			 * session.update(subDist); session.flush();
			 * if(session.contains(subDist)) session.evict(subDist);
			 

		} catch (Exception e) {

			log.error("Exception-->" + e);

			return false;
		}

		return true;
	}*/

	@Override
	public District getById(Integer districtCode, Session session) {
		District district = null;
		try {
			DistrictPK districtPK = new DistrictPK(districtCode, 1,1);
			district = (District) session.get(District.class, districtPK);

		} catch (HibernateException e) {
			log.error("Exception-->" + e);
		}
		return district;
	}

	@Override
	public int saveDataInMap(DistrictForm districtForm, List<AttachedFilesForm> validFileMap, HttpSession httpSession, Session session) {
		MapAttachment attachment = null;
		//boolean flag = true;
		Integer attId = 0;
		try {
			if (validFileMap != null) {
				Iterator<AttachedFilesForm> it = validFileMap.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new MapAttachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					attachment.setMapEntityCode(001);
					attachment.setMapEntityType('D');
					attachment = (MapAttachment) session.merge(attachment);
					Long attachmentId = attachment.getAttachmentId();
					attId = attachmentId.intValue();
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return 0;
		}
		return attId;
	}

	@Override
	public String insertDistrict(HttpServletRequest request, HttpSession httpSession, DistrictForm districtForm, Session session) {

		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
		//Integer p_district_code = null;
		Integer stateId = sessionObject.getStateId();
		Integer p_userid = sessionObject.getUserId().intValue();
		String p_district_name_english = districtForm.getDistrictNameInEn();
		String p_district_name_local = districtForm.getDistrictNameInLcl();
		String p_alias_english = districtForm.getDistrictAliasInEn();
		String p_alias_local = districtForm.getDistrictAliasInLcl();
		String p_census_2011_code = "000";
		/**
		 * remove capturing census 2011 code from ui done by Maneesh kumar 4Aug2016
		 */
		/*if (districtForm.getCensus2011Code() != null && !districtForm.getCensus2011Code().equals("")) {
			p_census_2011_code = Integer.parseInt(districtForm.getCensus2011Code());
		} else
			p_census_2011_code = 0;*/
		String p_sscode = districtForm.getSscode();

		String lati = districtForm.getLati();
		String longi = districtForm.getLongi();
		StringBuffer sb = null;
		if(lati!=null && longi!=null && !("").equals(lati) &&!("").equals(longi)){
			sb=new StringBuffer();
			String[] arrayLati = lati.split(",");
			String[] arrayLong = longi.split(",");
			
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
		
		String p_coordinates = sb!=null?sb.toString():null;// 321:321,2132:32132
		String p_headquarter_name_english = districtForm.getHeadquarterName();
		String headquarter_local_name = districtForm.getHeadquarterNameLocal();
		String p_order_no = districtForm.getOrderNo();

		Date effectiveDate = districtForm.getEffectiveDate();
		Date gazPubDate = districtForm.getGazPubDate();
		Date orderDate = districtForm.getOrderDate();
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
		String fulldistrictList = "";
		String partdistrictList = "";

		String districtList = districtForm.getDistrictNameEnglishTemp();
		String[] districtListArray = districtList.split(",");
		for (int i = 0; i < districtListArray.length; i++) {
			String stringTemp = districtListArray[i];
			String[] string = stringTemp.split(":");
			String fullDist = "";
			String partDist = "";
			if (string[0].contains("FULL")) {
				fullDist = string[0].replaceAll("FULL", "");
				if (fullCount == 1) {
					fulldistrictList = fullDist;
					fullCount++;
				} else {
					fulldistrictList = fulldistrictList + "," + fullDist;
				}
			}
			if (string[0].contains("PART")) {
				partDist = string[0].replaceAll("PART", "");
				if (partCount == 1) {
					partdistrictList = partDist;
					partCount++;
				} else {
					partdistrictList = partdistrictList + "," + partDist;
				}
			}
		}
		int subdistCount = 1;
		String list_of_subdist_full_temp = "";
		String string = "";
		String contriSubDistList = districtForm.getContributedSubDistrictsTemp();
		String[] contSubDistArray = contriSubDistList.split(",");
		for (int i = 0; i < contSubDistArray.length; i++) {
			String[] split = contSubDistArray[i].split(":");
			if (split[0].contains("FULL")) {
				string = split[0].replaceAll("FULL", "");
				if (subdistCount == 1) {
					list_of_subdist_full_temp = string;
					subdistCount++;
				} else {
					list_of_subdist_full_temp = list_of_subdist_full_temp + "," + string;
				}
			}
		}

		String mergeVillage = districtForm.getStoredCombiValues();
		if (mergeVillage != null && !mergeVillage.equals("")) {
			mergeVillage = mergeVillage.replaceAll("FULL", "");
			mergeVillage = mergeVillage.replaceAll("PART", "");
		}

		String addVillage = districtForm.getStoredNewCombiValues();
		if (addVillage != null && !addVillage.equals("")) {
			addVillage = addVillage.replaceAll("FULL", "");
		}

		String list_of_district_full = fulldistrictList;// 420FULL,5965PART
		String list_of_district_part = partdistrictList;
		String list_of_subDist_full = list_of_subdist_full_temp;// Meragon
																// (sasasa)
																// (FULL),Migrata
																// (sasasa)
																// (FULL)


		Query query = session.getNamedQuery("createDistrictQuery").setParameter("p_state_code", stateId, Hibernate.INTEGER).setParameter("p_userid", p_userid, Hibernate.INTEGER)
				.setParameter("p_district_name_english", p_district_name_english, Hibernate.STRING).setParameter("p_order_no", p_order_no, Hibernate.STRING).setParameter("p_order_date", ordertimeStampDate, Hibernate.TIMESTAMP)
				.setParameter("p_effective_date", effectivetimeStampDate, Hibernate.TIMESTAMP).setParameter("p_district_name_local", p_district_name_local, Hibernate.STRING).setParameter("p_alias_english", p_alias_english, Hibernate.STRING)
				.setParameter("p_alias_local", p_alias_local, Hibernate.STRING).setParameter("p_census_2011_code", p_census_2011_code, Hibernate.STRING).setParameter("p_sscode", p_sscode, Hibernate.STRING)
				.setParameter("p_coordinates", p_coordinates, Hibernate.STRING).setParameter("p_gaz_pub_date", gazPubtimeStampDateTemp, Hibernate.TIMESTAMP).setParameter("list_of_district_full", list_of_district_full, Hibernate.STRING)
				.setParameter("list_of_district_part", list_of_district_part, Hibernate.STRING).setParameter("list_of_subdistrict_full", list_of_subDist_full, Hibernate.STRING)
				.setParameter("list_of_old_subdistrict_village", mergeVillage, Hibernate.STRING).setParameter("list_of_new_subdistrict_village", addVillage, Hibernate.STRING)
				.setParameter("p_map_attachment_code", districtForm.getMapCode(), Hibernate.INTEGER).setParameter("p_headquarter_name_english", p_headquarter_name_english, Hibernate.STRING)
				.setParameter("p_headquarter_local_name", headquarter_local_name, Hibernate.STRING);
		DistrictBean districtBean = (DistrictBean) query.list().get(0);
		return districtBean.getCreate_district_fn();
	}

	@Override
	public void saveOrUpdate(int districtId, int mapAttachmentId, Session session) {
		DistrictPK subdistrictPK = new DistrictPK();
		subdistrictPK.setDistrictCode(districtId);
		subdistrictPK.setDistrictVersion(1);
		Query query = session.createQuery("update District set mapLandregionCode = :mapLandregionCode where subdistrictPK = :subdistrictPK");
		query.setParameter("mapLandregionCode", mapAttachmentId);
		query.setParameter("subdistrictPK", subdistrictPK);

		query.executeUpdate();

	}

	@Override
	public int findDuplicate(String districtName, Integer stateCode) {
		Query criteria = null;
		Session session = null;
		districtName = districtName.trim().toUpperCase();
		int recordCount = 0;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select count(*) from District where UPPER(TRIM(district_name_english)) LIKE :districtName and slc=:stateCode");
			criteria.setParameter("districtName", districtName, Hibernate.STRING);
			criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			if (!list.isEmpty() && list.get(0) != null) {
				recordCount = Integer.parseInt(list.get(0).toString());
			}
		} catch (Exception e) {
			return -1;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return recordCount;
	}

	@SuppressWarnings("unchecked")
	public synchronized String saveInvalidateDistrictDAO(DistrictForm districtForm, Session hsession, Long userId) {

		List<InvalidateDistrictFn> result = new ArrayList<InvalidateDistrictFn>();
		Query query = null;
		String orderCode = null;
		Integer userid = new Integer(userId.intValue());

		try {

			query = hsession.getNamedQuery("insertInvalidateDistrictFn")

			/*
			 * :source_district_code,:user_id,:listformat,:effactive_date,:orderno
			 * ,:orderdate,:guz_pub_date)
			 */
			.setParameter("source_district_code", Integer.parseInt(districtForm.getDistrictNameEnglish()), Hibernate.INTEGER).setParameter("user_id", userid, Hibernate.INTEGER)
					.setParameter("listformat", districtForm.getListformat(), Hibernate.STRING).setParameter("effactive_date", districtForm.getEffectiveDate(), Hibernate.DATE).setParameter("orderno", districtForm.getOrderNo(), Hibernate.STRING)
					.setParameter("orderdate", districtForm.getOrderDate(), Hibernate.DATE).setParameter("guz_pub_date", districtForm.getGazPubDate(), Hibernate.DATE).setParameter("order_path", null, Hibernate.STRING);

			result = query.list();
			if (result.size() > 0) {
				orderCode = result.get(0).getOrderCode();
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
			// throw e;
		}
		return orderCode;

	}

	// changed
	@SuppressWarnings("unchecked")
	public List<District> getTargetDistrictShiftSDistrict(int sourceDistrictCode, int statecode) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<District> districtList = new ArrayList<District>();

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from District s where s.isactive =true and s.districtPK.districtCode !=" + sourceDistrictCode + "and slc=" + statecode + "order by districtNameEnglish");
			districtList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return districtList;
	}

	@SuppressWarnings("unchecked")
	public List<District> getTargetDistrictShiftSDistrictFinal(int sourceDistrictCode, int statecode) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<District> districtList = new ArrayList<District>();

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from District s where s.isactive =true and slc=" + statecode + "order by districtNameEnglish");
			districtList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return districtList;
	}

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListExtend(Integer stateCode, Integer orgCode) throws BaseAppException {

		Session session = null;
		Query query = null;
		String dlc = "";
		List<District> getDistrictList = null;
		try {

			dlc = organizationService.getExtendDetaildofEntity(orgCode, 'T', 'D');
			/*
			 * if(pdlc.length()>0) {
			 * pdlc=pdlc.substring(0,pdlc.lastIndexOf(pdlc.length())); }
			 */

			session = null;

			Integer slc = commonService.getSlc(stateCode);

			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			if (dlc != "") {
				/*
				 * if(pdlc!="") dlc+=","+pdlc;
				 */
				query = session.createQuery("from District d where d.slc=" + slc + " and d.dlc not in(" + dlc + ") and  d.isactive=true order by districtNameEnglish");
			} else {
				/*
				 * if(pdlc!="") query =
				 * session.createQuery("from District d where d.slc="
				 * +slc+" and d.dlc not in("
				 * +pdlc+") and  d.isactive=true order by districtNameEnglish");
				 * else
				 */
				query = session.createQuery("from District d where d.slc=" + slc + " and d.isactive=true order by districtNameEnglish");
			}

			getDistrictList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getDistrictList;
	}

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListExtendforBlock(Integer stateCode, Integer orgCode) throws BaseAppException {

		Session session = null;
		Query query = null;
		String dlc = "";
		List<District> getDistrictList = null;
		try {

			dlc = organizationService.getExtendDetaildofEntity(orgCode, 'B', 'D');
			/*
			 * if(pdlc.length()>0) {
			 * pdlc=pdlc.substring(0,pdlc.lastIndexOf(pdlc.length())); }
			 */

			session = null;

			Integer slc = commonService.getSlc(stateCode);

			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			if (dlc != "") {
				/*
				 * if(pdlc!="") dlc+=","+pdlc;
				 */
				query = session.createQuery("from District d where d.slc=" + slc + " and d.dlc not in(" + dlc + ") and  d.isactive=true order by districtNameEnglish");
			} else {
				/*
				 * if(pdlc!="") query =
				 * session.createQuery("from District d where d.slc="
				 * +slc+" and d.dlc not in("
				 * +pdlc+") and  d.isactive=true order by districtNameEnglish");
				 * else
				 */
				query = session.createQuery("from District d where d.slc=" + slc + " and d.isactive=true order by districtNameEnglish");
			}

			getDistrictList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getDistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDistrictListbyStateCodeExtend(Integer stateCode, String districtList, Integer orgCode, char type) throws BaseAppException {
		List<District> distList = null;
		List<State> stateList = null;
		String stateName = null;
		Session session = null;
		Integer slc = null;
		String dlc = "";
		try {
			distList = new ArrayList<District>();
			stateList = new ArrayList<State>();
			if (districtList == null) {
				districtList = "";
			}

			slc = commonService.getSlc(stateCode);
			session = sessionFactory.openSession();
			stateList = session.createQuery("from State where isactive=true and slc=" + slc).list();
			stateName = stateList.get(0).getStateNameEnglish();

			dlc = organizationService.getExtendDetaildofEntity(orgCode, type, 'D');
			if (dlc != "" && districtList != "") {
				dlc = "," + dlc;
			}
			if (dlc != "" || districtList != "") {
				distList = session.createQuery("from District d where d.districtCode not in (" + districtList + dlc + ") and d.slc=" + slc + " and d.isactive=true order by districtNameEnglish").list();
			} else {
				distList = session.createQuery("from District d where  d.slc=" + slc + " and d.isactive=true order by districtNameEnglish").list();
			}

			for (int i = 0; i < distList.size(); i++) {
				distList.get(i).setDistrictNameEnglish(distList.get(i).getDistrictNameEnglish() + " (" + stateName + ")");
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return distList;
	}

	@Override
	public int updateDistrictMap(Session session, int... params) {
		/* Added by sushil on 11-06-2013 */
		try {
			if (params.length > 1) {
				MapAttachment mapAttachment = (MapAttachment) session.get(MapAttachment.class, Long.valueOf(params[1]));
				if (mapAttachment != null) {
					mapAttachment.setMapEntityCode(params[0]);
					session.update(mapAttachment);
				}
				return 0;
			}
		} catch (HibernateException e) {
			return -1;
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DistrictWiseLBReportBean> DistrictWiseLBReportDetail(Integer districtCode) throws BaseAppException {
		Session session = null;
		Query query = null;
		List<DistrictWiseLBReportBean> districtWiseLBReportBeanList = new ArrayList<DistrictWiseLBReportBean>();
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("DistrictWiseLBReportData").setParameter("entity_code", districtCode, Hibernate.INTEGER);

			districtWiseLBReportBeanList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		// TODO Auto-generated method stub
		return districtWiseLBReportBeanList;
	}

	@Override
	public String getDistrictNameEnglishbyDistrictCode(Integer districtCode) {
		Session session = null;
		String distNameEng = "";
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from District d where d.districtPK.districtCode=:districtCode and isactive=true");
			query.setParameter("districtCode", districtCode);
			@SuppressWarnings("unchecked")
			List<District> list = query.list();

			if (list != null && !list.isEmpty() && list.get(0) != null) {
				distNameEng = list.get(0).getDistrictNameEnglish();
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return distNameEng;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCodeForListBoxExtended(int stateCode, int orgCode, int locatedatlevelCode) throws BaseAppException {
		List<District> distList = null;
		//List<State> stateList = null;
		//String stateName = null;
		SQLQuery query = null;
		Session session = null;
		try {
			distList = new ArrayList<District>();
			//stateList = new ArrayList<State>();
			session = sessionFactory.openSession();
			/*
			 * stateList = session .createQuery(
			 * "from State where isactive=true and stateCode=" +
			 * stateCode).list(); stateName =
			 * stateList.get(0).getStateNameEnglish();
			 */
			query = session.createSQLQuery("select case when d.district_code  in (select * from get_draft_used_lb_lr_temp(d.district_code,'D')) then  cast ('F' as character)"
					+ " else cast('A' as character) end as operation_state, case when d.district_code =a.entity_code then cast('F' as character) else cast('A' as character) "
					+ " end as operation_extend_flag,d.district_Code as districtCode,d.district_name_english as districtNameEnglish from district d left outer join "
					+ " (select entity_code from org_coverage_detail where isactive and coverage_code in" + " (select coverage_detail_code from org_coverage where isactive and org_coverage_entity_type=2 and org_located_level_code in "
					+ " (select org_located_level_code from org_located_at_levels  where isactive and olc=:orgCode and located_at_level=:locatedatlevelCode)))as a " + " ON d.district_code =a.entity_code "
					+ " where slc=:stateCode and isactive  order by district_code");

			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("locatedatlevelCode", locatedatlevelCode, Hibernate.INTEGER);
			query.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			query.addScalar("districtCode").addScalar("districtNameEnglish").addScalar("operation_state").addScalar("operation_extend_flag");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			distList = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return distList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDistrictListbyStateCodeExtended(int stateCode, String districtList, int orgCode, int locatedatlevelCode) throws BaseAppException {
		List<District> distList = null;
		List<State> stateList = null;
		String stateName = null;
		Session session = null;
		SQLQuery query = null;

		try {
			distList = new ArrayList<District>();
			stateList = new ArrayList<State>();

			session = sessionFactory.openSession();
			stateList = session.createQuery("from State where isactive=true and stateCode=" + stateCode).list();
			stateName = stateList.get(0).getStateNameEnglish();

			query = session.createSQLQuery("select case when d.district_code  in (select * from get_draft_used_lb_lr_temp(d.district_code,'D')) then  cast ('F' as character)"
					+ " else cast('A' as character) end as operation_state, case when d.district_code =a.entity_code then cast('F' as character) else cast('A' as character) "
					+ " end as operation_extend_flag,d.district_Code as districtCode,d.district_name_english as districtNameEnglish from district d left outer join "
					+ " (select entity_code from org_coverage_detail where isactive and coverage_code in" + " (select coverage_detail_code from org_coverage where isactive and org_coverage_entity_type=2 and org_located_level_code in "
					+ " (select org_located_level_code from org_located_at_levels  where isactive and olc=:orgCode and located_at_level=:locatedatlevelCode)))as a " + " ON d.district_code =a.entity_code "
					+ " where slc=:stateCode and isactive and d.district_Code not in (" + districtList + ") order by d.district_name_english");

			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("locatedatlevelCode", locatedatlevelCode, Hibernate.INTEGER);
			query.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			query.addScalar("districtCode").addScalar("districtNameEnglish").addScalar("operation_state").addScalar("operation_extend_flag");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			distList = query.list();

			for (int i = 0; i < distList.size(); i++) {
				distList.get(i).setDistrictNameEnglish(distList.get(i).getDistrictNameEnglish() + " (" + stateName + ")");
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return distList;
	}

	/**
	 * For Mark PESA Land Regions Created on 05-11-2014
	 * 
	 * @author Ripunj Parameter added by kirandeep on 01/05/2015 for modified
	 *         use case
	 *  Modify parameter by Sunita on 07-04-2016
	 */
	@Override
	public boolean updatePesaStatus(String inputParam, Integer stateCode) throws BaseAppException {

		Session session = null;
		Transaction tx = null;
		boolean statusFlag = false;
		try {
			/* added by kirandeep on 01/05/2015 for modified use case */
			boolean status = updatePesaStatusForLandregions(stateCode);
			if (!status) {
				return false;
			}
			session = sessionFactory.openSession();
			/*added by Sunita on 07/04/2016 for new function Mark PESA Land Region*/
			SQLQuery query = session.createSQLQuery("select * from mark_pesa_for_land_region_fn(:inputParam)");
			query.setParameter("inputParam", inputParam);
			statusFlag = Boolean.parseBoolean(query.uniqueResult().toString());
			if(!statusFlag) {
				return false;
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	/**
	 * New methods for the lgd impact issue
	 * 
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCodeForLocalBody(int slc) throws BaseAppException {
		Session session = null;

		List<District> getDistrictList = null;
		SQLQuery query = null;

		try {
			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createSQLQuery(" select case when d.district_code  in (select local_body_code from get_draft_used_lb_lr_temp(d.district_code,'D')) "
					+ " then cast('F' as character)  else cast('A' as character) end as operation_state , district_code as districtCode," + " district_name_english as districtNameEnglish"
							+ ",district_name_local as districtNameLocal from district d where d.slc=:slc"
					+ " and d.isactive=true order by district_name_english ");
			query.setParameter("slc", slc, Hibernate.INTEGER);
			query.addScalar("operation_state");
			query.addScalar("districtCode");
			query.addScalar("districtNameEnglish");
			query.addScalar("districtNameLocal");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			getDistrictList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getDistrictList;
	}

	/**
	 * added on 06/01/2015 by kirandeep for localbody impact issue.
	 */

	@SuppressWarnings("unchecked")
	public List<District> getTargetDistrictShiftSDistrictForlocalbody(int sourceDistrictCode, int statecode) throws BaseAppException {
		List<District> districtList = null;
		SQLQuery query = null;
		Session session = null;

		try {
			districtList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createSQLQuery(" select case when d.district_code  in (select local_body_code from get_draft_used_lb_lr_temp(d.district_code,'D')) "
					+ " then cast('F' as character)  else cast('A' as character) end as operation_state , district_code as districtCode,"
					+ " district_name_english as districtNameEnglish from district d where d.slc=:statecode and d.district_code != :sourceDistrictCode " + " and d.isactive=true order by district_name_english ");
			query.setParameter("statecode", statecode, Hibernate.INTEGER);
			query.setParameter("sourceDistrictCode", sourceDistrictCode, Hibernate.INTEGER);
			query.addScalar("operation_state");
			query.addScalar("districtCode");
			query.addScalar("districtNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			districtList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return districtList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDistrictListbyStateCodeForLocalbody(int stateCode, String districtList) throws BaseAppException {
		List<District> distList = null;
		List<State> stateList = null;
		String stateName = null;
		Session session = null;
		SQLQuery query1 = null;
		//SQLQuery query2 = null;

		try {
			distList = new ArrayList<District>();
			stateList = new ArrayList<State>();

			session = sessionFactory.openSession();

			stateList = session.createQuery("from State where isactive=true and stateCode=" + stateCode).list();
			stateName = stateList.get(0).getStateNameEnglish();
			query1 = session.createSQLQuery(" select case when d.district_code  in (select local_body_code from get_draft_used_lb_lr_temp(d.district_code,'D')) "
					+ " then cast('F' as character)  else cast('A' as character) end as operation_state , d.district_code as districtCode," + " d.district_name_english as districtNameEnglish from district d where d.district_code not in("
					+ districtList + ") and d.slc=" + stateCode + " and  d.isactive=true order by d.district_name_english ");
			// query1.setParameter("slc", slc, Hibernate.INTEGER);
			query1.addScalar("operation_state");
			query1.addScalar("districtCode");
			query1.addScalar("districtNameEnglish");
			query1.setResultTransformer(Transformers.aliasToBean(District.class));
			distList = query1.list();

			// distList =
			// session.createQuery("from District d where d.districtCode not in ("
			// + districtList + ") and d.slc=" + stateCode +
			// " and d.isactive=true order by districtNameEnglish").list();
			for (int i = 0; i < distList.size(); i++) {
				distList.get(i).setDistrictNameEnglish(distList.get(i).getDistrictNameEnglish() + " (" + stateName + ")");
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return distList;
	}

	/**
	 * For district history report citizen section Created on 03-March-2015
	 * 
	 * @author Pooja Sharma
	 */
	@SuppressWarnings("unchecked")
	public List<DistrictHistory> getDistrictHistoryReport(char districtNameEnglish, int districtCode) throws Exception {
		Session session = null;
		List<DistrictHistory> districtHistoryDetail = null;
		SQLQuery query = null;

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select * from get_land_region_history_fn_for_citizen(?,?)");
			query.setParameter(0, districtNameEnglish);
			query.setParameter(1, districtCode);
			query.addEntity(DistrictHistory.class);
			districtHistoryDetail = query.list();
		} catch (Exception e) {
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtHistoryDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDistrictListbyDistrictCodeForLocalBody(int dlc) throws BaseAppException {
		Session session = null;

		List<District> getDistrictList = null;
		SQLQuery query = null;

		try {
			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createSQLQuery(" select case when d.district_code  in (select local_body_code from get_draft_used_lb_lr_temp(d.district_code,'D')) "
					+ " then cast('F' as character)  else cast('A' as character) end as operation_state , district_code as districtCode," + " district_name_english as districtNameEnglish from district d where d.dlc=:dlc"
					+ " and d.isactive=true order by district_name_english ");
			query.setParameter("dlc", dlc, Hibernate.INTEGER);
			query.addScalar("operation_state");
			query.addScalar("districtCode");
			query.addScalar("districtNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			getDistrictList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getDistrictList;
	}

	public boolean updatePesaStatusForLandregions(Integer stateCode) throws Exception {
		Session session = sessionFactory.openSession();
		Boolean status = false;
		try {
			SQLQuery query = session.createSQLQuery("select * from update_pesa_status_landRegion(:stateCode)");
			query.setParameter("stateCode", stateCode);

			status = Boolean.parseBoolean(query.uniqueResult().toString());
			
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return status;
	}

	/**
	 * @author Kirandeep for landregion is pesa date: 30/04/2015
	 * @param entityCodes
	 * @param type
	 * @return
	 * @throws BaseAppException
	 */
	@SuppressWarnings("unchecked")
	public List<ParentHeirarchyBean> getHeirarchyByParentCodes(String entityCodes, Character type, Character afterPost) throws BaseAppException {

		Session session = null;
		List<ParentHeirarchyBean> parentHeirarchyBeanList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getheirarchyofentity").setParameter("entityCodes", entityCodes, Hibernate.STRING).setParameter("entityType", type, Hibernate.CHARACTER).setParameter("afterPost", afterPost);
			parentHeirarchyBeanList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return parentHeirarchyBeanList;
	}

	/**
	 * Get List<District> base on Creteria for extend organigation Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 */

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyCreteria(Integer stateCode, List<Integer> districtCodeList) throws Exception {
		Session session = null;
		List<District> getDistrictList = null;
		try {
			getDistrictList = new ArrayList<District>();
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select case when d.district_code  in (select local_body_code from get_draft_used_lb_lr_temp(d.district_code,'D'))  then cast('F' as character)  "
					+ "else cast('A' as character) end as operation_state , district_code as districtCode, (btrim(d.district_name_english)||'(' ||btrim(s.state_name_english)||')')  as districtNameEnglish "
					+ "from district d left join state s on s.slc=d.slc  where s.isactive  and d.isactive and ");
			if (stateCode == Integer.parseInt(DepartmentConstent.ZERO_VALUE.toString())) {
				queryBuild.append(" d.district_code in(:districtCodeList)");
			} else {
				queryBuild.append("  d.district_code not in(:districtCodeList) and d.slc=:stateCode");
			}
			queryBuild.append("	order by d.district_name_english ");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			if (stateCode != Integer.parseInt(DepartmentConstent.ZERO_VALUE.toString())) {
				query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			}
			query.setParameterList("districtCodeList", districtCodeList);
			query.addScalar("operation_state");
			query.addScalar("districtCode");
			query.addScalar("districtNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			getDistrictList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getDistrictList;
	}
	
	/**
	 * Code used for getting districtList for multiple districts
	 * @author Pooja
	 * @since 21-10-2015
	 * @param states
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDistrictListbyStates(String states) throws Exception {
		Session session = null;
		List<District> districtList = null;
		SQLQuery query = null;
		try {
			districtList = new ArrayList<District>();
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select case when d.district_code  in (select local_body_code from get_draft_used_lb_lr_temp(d.district_code,'D')) "
					+ "then cast('F' as character)  else cast('A' as character) end as operation_state , d.district_code as districtCode,d.district_name_english as districtNameEnglish, "
					+ "s.state_name_english as stateNameEnglish from district d,state s where s.slc=d.slc and d.slc in(" + states + ") and d.isactive=true and s.isactive order by stateNameEnglish,districtNameEnglish");
			query.addScalar("operation_state");
			query.addScalar("districtCode");
			query.addScalar("districtNameEnglish");
			query.addScalar("stateNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			districtList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtList;
	}
	
	
	@Override
	public List<District> getDistrictListbyCriteria(Integer entityCode,String entityType) throws Exception {
		Session session = null;
		List<District> districtList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder sb=new StringBuilder(" select case when d.district_code  in (select local_body_code from get_draft_used_lb_lr_temp(d.district_code,'D'))  then cast('F' as character)");
			sb.append(" else cast('A' as character) end as operation_state ,district_code as districtCode,district_name_english as districtNameEnglish,district_name_local as districtNameLocal ");
			sb.append(" from district d where");
			if((CommanConstant.ENTITY_TYPE_STATE.toString()).equals(entityType)){
				sb.append(" d.slc=:entityCode ");
			}else {
				sb.append(" d.dlc=:entityCode ");
			}
			sb.append( " and d.isactive=true order by district_name_english ");
			SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("entityCode", entityCode, Hibernate.INTEGER);
			query.addScalar("operation_state");
			query.addScalar("districtCode");
			query.addScalar("districtNameEnglish");
			query.addScalar("districtNameLocal");
			query.setResultTransformer(Transformers.aliasToBean(District.class));
			districtList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return districtList;
	}

/*	@Author Pranav Tiwari
	* Checking Map Finalisation
	* on 21-03-2017
	*/
	@Override
	public boolean districtMapFinalised(Integer districtCode) {
		Session session = null;
		boolean isFinalised = false;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(" select case when count(1)>1 then true else false end from subdistrict s, district d, subdistrict_gismap_status g "
					    + " where d.dlc=:districtCode and d.isactive and s.isactive and d.dlc=s.dlc and s.tlc=g.subdistrict_code and g.ismapfinalized=false ");
			query.setParameter("districtCode", districtCode);
			isFinalised = (boolean) query.uniqueResult();

		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return isFinalised;
		
	}
	
	@Override
	public boolean distInvalFnAfterCreateMulDist(Integer districtCode,Integer userId,Date effectiveDate)throws Exception{
		boolean isInvalidate=false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(" select * from invalidate_district_subdistrict_fn(:districtCode,:userId,:effectiveDate)");
			query.setParameter("districtCode", districtCode);
			query.setParameter("userId", userId);
			query.setParameter("effectiveDate", effectiveDate);
			String retValue = (String) query.uniqueResult();
			if(retValue!=null && ("SUCESS").equals(retValue)){
				isInvalidate=true;
			}

		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return isInvalidate;
	}

	@Override
	public Response saveEffectiveDateEntityDistrict(List<GetEntityEffectiveDate> getEntityEffectiveDateList,
			Integer userId) throws Exception {

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
				query.setParameter("entityType", "D");
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
						
						query = session.createSQLQuery("select * from change_district_effective_date_fn(:parameter1,:userId)");
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
	
	@Override
	public int getMaxDistrictMinorVersion(int districtCode,int districtVersion) throws BaseAppException {
		int maxVersionCode = 0;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select COALESCE(max(minor_version),1) from district where  district_code=:districtCode and district_version=:districtVersion");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.setParameter("districtVersion", districtVersion, Hibernate.INTEGER);
			maxVersionCode = (Integer) query.uniqueResult();
		} catch (Exception e) {
			maxVersionCode = 0;
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return maxVersionCode;
	}

	
	
	
}