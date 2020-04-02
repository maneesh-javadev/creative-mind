package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.ChangeSubDistrict;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.InvalidateSubDistrictReq;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.ParliamentConstituencyId;
import in.nic.pes.lgd.bean.SubDistrictBean;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictHistory;
import in.nic.pes.lgd.bean.SubdistrictPK;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.constant.DepartmentConstent;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.forms.ParliamentDataForm;
import in.nic.pes.lgd.forms.ParliamentForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.interceptor.LanguageListener;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.service.VillageService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pes.attachment.util.AttachedFilesForm;
import com.org.ep.exception.BaseAppException;
import in.nic.pes.lgd.service.VillageService;

@Transactional
public class SubDistrictDAOImpl implements SubDistrictDAO {
	private static final Logger log = Logger.getLogger(SubDistrictDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	CommonService commonService;

	@Autowired
	EmailService emailService;

	@Autowired
	OrganizationService organizationService;

	
	 @Autowired
	  private VillageService villageService;
	@Override
	public boolean publishSubDistrict(Subdistrict subdistric, Session session) throws BaseAppException {
		try {
			try {
				session.save(subdistric);
				session.flush();
				session.refresh(subdistric);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	public int getMaxSubDistrictCode() throws BaseAppException {
		Session session = sessionFactory.openSession();
		int MaxCode = 0;
		try {

			try {
				MaxCode = Integer.parseInt(session.createSQLQuery("select max(subdistrict_code) from subdistrict").list().get(0).toString());
			} catch (Exception e) {
				log.debug("Exception" + e);
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return MaxCode;
	}

	public int getMaxParliamentConstituencyVersion(int ParliamentCode) throws BaseAppException {
		int MaxVersionCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			MaxVersionCode = Integer.parseInt(session.createSQLQuery("" + "select max(pc_version) from parliament_constituency where isactive=true and pc_code=:ParliamentCode").setParameter("ParliamentCode", ParliamentCode, Hibernate.INTEGER).list()
					.get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxVersionCode;
	}

	public int getPcVersion(int pcCode) throws BaseAppException {
		int MaxVersionCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			MaxVersionCode = Integer.parseInt(session.createSQLQuery("" + "select max(pc_version) from parliament_constituency where isactive=true and pc_code=:pcCode").setParameter("pcCode", pcCode, Hibernate.INTEGER).list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxVersionCode;
	}

	public int getMaxSubDistrictVersion(int subDistrictCode) throws BaseAppException {
		int MaxCode = 0;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("Select max(subdistrict_version) from subdistrict where isactive=true and subdistrict_code=:subDistrictCode");
			query.setParameter("subDistrictCode", subDistrictCode);
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxCode = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxCode;
	}

	@SuppressWarnings("unchecked")
	public List<AssemblyConstituency> getAssemblyList(int districtCode) throws BaseAppException {
		Session session = null;
		List<AssemblyConstituency> vlgLst = null;
		try {
			session = sessionFactory.openSession();
			vlgLst = new ArrayList<AssemblyConstituency>();
			vlgLst = session.createQuery("from AssemblyConstituency v where pcCode=:districtCode and isactive=true order by v.acNameEnglish").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			vlgLst = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vlgLst;

	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrict(int districtCode) throws BaseAppException {
		Session session = null;
		List<Subdistrict> vlgLst = null;
		try {
			session = sessionFactory.openSession();
			vlgLst = new ArrayList<Subdistrict>();
			vlgLst = session.createQuery("from Subdistrict v where subdistrict_code=:districtCode and isactive=true order by v.subdistrictNameEnglish").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			vlgLst = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vlgLst;

	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictLists(int districtCode) throws BaseAppException {
		Session session = null;
		List<Subdistrict> vlgLst = null;
		try {
			session = sessionFactory.openSession();
			vlgLst = new ArrayList<Subdistrict>();
			vlgLst = session.createQuery("from Subdistrict v where dlc=:districtCode and isactive=true order by v.subdistrictNameEnglish").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			vlgLst = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vlgLst;

	}

	@SuppressWarnings("unchecked")
	public boolean insertBulkSubdistrict(String query, Session session) throws BaseAppException {
		try {
			Query query1 = session.createSQLQuery(query);
			query1.executeUpdate();
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListbyDistrict(int districtCode) throws BaseAppException {
		Session session = null;
		List<Subdistrict> vlgLst = null;

		try {
			session = sessionFactory.openSession();
			vlgLst = new ArrayList<Subdistrict>();
			int dlc = commonService.getdlc(districtCode);
			vlgLst = session.createQuery("from Subdistrict v where v.dlc=:dlc and isactive=true order by v.subdistrictNameEnglish").setParameter("dlc", dlc, Hibernate.INTEGER).list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			vlgLst = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vlgLst;

	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListbyDistrictCode(int districtCode) throws BaseAppException {
		Session session = null;
		List<Subdistrict> sdList = new ArrayList<Subdistrict>();
		try {
			session = sessionFactory.openSession();

			Query query = session.createQuery("from Subdistrict sd where sd.isactive=true and sd.dlc=:districtCode order by sd.subdistrictNameEnglish");
			query.setParameter("districtCode", districtCode);
			sdList = query.list();
			return sdList;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getTargetSubDistrictList(int subdistrictCode, int districtCode) throws BaseAppException {
		List<Subdistrict> subdistrict = null;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Subdistrict sd where sd.isactive=true and sd.district.districtPK.districtCode=:districtCode  and sd.subdistrictPK.subdistrictCode !=:subdistrictCode  order by sd.subdistrictNameEnglish")
					.setParameter("districtCode", districtCode, Hibernate.INTEGER).setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER);
			subdistrict = query.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrict;
	}

	@Override
	public Subdistrict getSubDistrictDetail(SubdistrictPK subdistrictPK, Session session) throws BaseAppException {
		Subdistrict subdistrict = null;
		try {
			subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		}
		return subdistrict;
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListby(int subDistrictCode) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<Subdistrict> getSubDistrictListby = null;
		try {
			session = sessionFactory.openSession();

			criteria = session.createQuery("from Subdistrict where isactive=true and subdistrictCode=:subDistrictCode order by subdistrictNameEnglish").setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER);
			getSubDistrictListby = criteria.list();

		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return getSubDistrictListby;

	}

	public Subdistrict getById(Integer subDistrictCode) throws BaseAppException {
		Subdistrict subdistrict = null;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			subdistrict = (Subdistrict) session.get(Subdistrict.class, subDistrictCode);
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrict;
	}

	@SuppressWarnings("unchecked")
	public List<SubDistrictForm> viewSubDistrictDetails() throws BaseAppException {

		Query criteria = null;
		Session session = null;
		List<SubDistrictForm> viewSubDistrictDetails = null;

		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Subdistrict");
			viewSubDistrictDetails = criteria.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return viewSubDistrictDetails;
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> viewSubDistrictDetails(String sql) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<Subdistrict> lstSd = new ArrayList<Subdistrict>();
		try {
			session = sessionFactory.openSession();
			lstSd = session.createQuery(sql).list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lstSd;

	}

	@Override
	public boolean updateLrReplaces(int lrReplaces, SubdistrictPK subdistrictPK, Session session) throws BaseAppException {
		Subdistrict subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
		subdistrict.setLrReplaces(lrReplaces);
		try {
			session.update(subdistrict);
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	@Override
	public boolean updateLrReplacedBy(int lrReplacedBy, SubdistrictPK subdistrictPK, Session session) throws BaseAppException {
		Subdistrict subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
		subdistrict.setLrReplacedby(lrReplacedBy);
		try {
			session.update(subdistrict);
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	@Override
	public boolean updateIsActive(boolean isActive, SubdistrictPK subdistrictPK, Session session) throws BaseAppException {
		Subdistrict subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
		subdistrict.setIsactive(isActive);
		try {
			session.update(subdistrict);
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	public boolean addNewVersion(SubdistrictPK subdistrictPK, Session session) throws BaseAppException {

		return false;
	}

	// For Modify Sub District
	@SuppressWarnings("unchecked")
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode, Session session) throws BaseAppException {

		Query criteria = null;
		List<MapLandRegion> getMapDetailsModify = null;
		try {
			/*
			 * criteria = session .createQuery(
			 * "from MapLandRegion where map_landregion_code=:mapLandregionCode"
			 * ) .setParameter("mapLandregionCode", mapLandregionCode,
			 * Hibernate.INTEGER);
			 */
			criteria = session.createQuery("from MapAttachment1 where map_attachment_code=" + mapLandregionCode);
			getMapDetailsModify = criteria.list();
			int a = 0;

		} catch (HibernateException e) {
			log.debug("Exception" + e);

		}

		return getMapDetailsModify;
	}

	// For Modify Sub District
	@Override
	public boolean updateMapLanRegion(int mapCode, String coordinates, int subDistrictCode, Session session) throws BaseAppException {
		try {

			// MapLandRegion mapLandRegion = (MapLandRegion) session1.get(
			// MapLandRegion.class, mapCode);
			// MapLandRegion mapLandRegion =
			// (MapLandRegion)session1.load(MapLandRegion.class, mapCode);
			Criteria criteria = session.createCriteria(Subdistrict.class);
			criteria.add(Restrictions.eq("mapCode", subDistrictCode));
			criteria.add(Restrictions.eq("isactive", true));
			MapLandRegion mapLandRegion = new MapLandRegion();

			mapLandRegion.setCoordinates(coordinates);
			mapLandRegion.setLandregionCode(subDistrictCode);
			session.update(mapLandRegion);

		} catch (Exception e) {
			log.debug("Exception" + e);

			return false;
		}

		return true;
	}

	@Override
	public boolean modifySubDistrictInfo(SubDistrictForm subDistrictForm, String cord1, int subdistrictCode, int map_landRegionCode, Session session) throws BaseAppException {

		Session session1 = null;
		try {
			/*
			 * Criteria criteria=session.createCriteria(Subdistrict.class);
			 * criteria.add(Restrictions.eq("subdistrictCode",
			 * subdistrictCode)); criteria.add(Restrictions.eq("isactive",
			 * true));
			 */

			List<SubdistrictDataForm> listSubdistrict = new ArrayList<SubdistrictDataForm>();
			listSubdistrict = subDistrictForm.getListSubdistrictDetails();

			
			session1 = sessionFactory.openSession();
			session1.createSQLQuery(
					"update subdistrict set  alias_english='" + listSubdistrict.get(0).getAliasEnglish() + "', alias_local='" + listSubdistrict.get(0).getAliasLocal() + "', census_2011_code=" + listSubdistrict.get(0).getCensus2011Code()
							+ " , sscode='" + listSubdistrict.get(0).getSscode() + "' , coordinates='" + cord1 + "' where  subdistrict_code=" + subdistrictCode + " and isactive=true").executeUpdate();

			/*
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
			 */

		} catch (Exception e) {

			log.debug("Exception" + e);

			return false;
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
		}

		return true;
	}

	@Override
	public List<Village> getVillageModify(int subdistrictCode) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<Village> getVillageModify = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Village where isactive=true and id.acCode=:ParliamentCode").setParameter("ParliamentCode", subdistrictCode, Hibernate.INTEGER);
			getVillageModify = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return getVillageModify;
	}

	@Override
	public List<AssemblyConstituency> getAssemblyDetailsModify(int ParliamentCode) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<AssemblyConstituency> getAssemblyDetailsModify = null;
		try {
			session = sessionFactory.openSession();

			criteria = session.createQuery("from AssemblyConstituency where isactive=true and id.acCode=:ParliamentCode").setParameter("ParliamentCode", ParliamentCode, Hibernate.INTEGER);
			getAssemblyDetailsModify = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getAssemblyDetailsModify;
	}

	@Override
	public List<ParliamentConstituency> getParliamentDetailsModify(int ParliamentCode) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<ParliamentConstituency> getParliamentDetailsModify = null;
		try {

			session = sessionFactory.openSession();
			criteria = session.createQuery("from ParliamentConstituency where isactive=true and id.pcCode=:ParliamentCode").setParameter("ParliamentCode", ParliamentCode, Hibernate.INTEGER);
			getParliamentDetailsModify = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getParliamentDetailsModify;
	}

	@Override
	public List<Subdistrict> getSubdistrictDetailsModify(int subdistrictCode, Session session) throws BaseAppException {
		Query query = null;
		List<Subdistrict> getSubdistrictDetailsModify = null;
		try {
			query = session.createQuery("from Subdistrict where isactive=true and subdistrict_code=:subdistrictCode").setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER);
			getSubdistrictDetailsModify = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return getSubdistrictDetailsModify;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubdistrictDataForm> getSubdistrictDetailsModify() throws BaseAppException {

		Query criteria = null;
		Session session = null;
		List<SubdistrictDataForm> getSubdistrictDetailsModify = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Subdistrict where isactive=true and subdistrict_code=+subdistrictCode ");
			getSubdistrictDetailsModify = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getSubdistrictDetailsModify;
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictViewList(String query) throws BaseAppException {
		List<Subdistrict> subdistrict = null;
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			subdistrict = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrict;
	}

	@Override
	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<GovernmentOrder> getGovermentOrderDetail = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from GovernmentOrder where order_code=:orderCode").setParameter("orderCode", orderCode, Hibernate.INTEGER);
			getGovermentOrderDetail = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getGovermentOrderDetail;
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getTargetSubDistrictListbySubDistrict(int subdistrictCode, int districtCode) throws BaseAppException {
		List<Subdistrict> subdisctict = null;
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Subdistrict sd where sd.isactive=true and sd.district.districtPK.districtCode=:districtCode and sd.subdistrictPK.subdistrictCode !=:subdistrictCode")
					.setParameter("districtCode", districtCode, Hibernate.INTEGER).setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER);
			subdisctict = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdisctict;
	}

	@Override
	public int getMaxSubDistrictCode(String query) throws BaseAppException {

		int MaxCode = 0;
		Session session = null;
		Query sqlQuery = null;
		try {
			session = sessionFactory.openSession();
			sqlQuery = session.createSQLQuery(query);
			List list = sqlQuery.list();
			MaxCode = Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return MaxCode;
	}

	@Override
	public synchronized String ChangeSubDistrict(int subdistrictCode, String subdistrictNameEnglish, String subdistrictNameEnglishch, int userId, String subdistrictNameLocal, String aliasEnglish, String aliasLocal, Session session, String orderNo,
			Date orderDate, Date effectiveDate, String govOrder, Date govPubDate, Integer slc) throws BaseAppException {
		String temp = null;

		try {

			// :orderNo,:orderDate,:effectiveDate,:govOrder,:govPubDate

			// (request.setAttribute("stateCode", stateCode);

			List<ChangeSubDistrict> result = new ArrayList<ChangeSubDistrict>();
			Query query = session.getNamedQuery("SubDistrictQuery").setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER).setParameter("subdistrictNameEnglishch", subdistrictNameEnglishch, Hibernate.STRING)
					.setParameter("userId", userId, Hibernate.INTEGER).setParameter("subdistrictNameLocal", subdistrictNameLocal, Hibernate.STRING).setParameter("aliasEnglish", aliasEnglish, Hibernate.STRING)
					.setParameter("aliasLocal", aliasLocal, Hibernate.STRING).setParameter("orderNo", orderNo, Hibernate.STRING).setParameter("orderDate", orderDate, Hibernate.DATE).setParameter("subdistrictNameEnglish", subdistrictNameEnglish)
					.setParameter("effectiveDate", effectiveDate, Hibernate.DATE).setParameter("govOrder", govOrder, Hibernate.STRING).setParameter("govPubDate", govPubDate, Hibernate.DATE).setParameter("slc", slc, Hibernate.INTEGER);

			result = query.list();
			if (result.size() > 0) {
				temp = result.get(0).getChange_subdistrict_fn().toString();
				/*
				 * tid=Integer.parseInt(temp[0]);
				 * orderCode=Integer.parseInt(temp[1]); char t_type='T';
				 * EmailSmsThread est= new EmailSmsThread(tid,
				 * t_type,emailService); est.start();
				 */

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return temp;
	}

	@Override
	public void ChangeStandardCode(char landregionCodes, int pcCode, String cencouscode1, Integer cencouscode, String sscode) throws BaseAppException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("changeStandardCodesQuery").setParameter("landregionType", landregionCodes, Hibernate.CHARACTER).setParameter("landregionCode", pcCode, Hibernate.INTEGER)
					.setParameter("cencouscode1", cencouscode1, Hibernate.STRING).setParameter("cencouscode", cencouscode, Hibernate.INTEGER).setParameter("sscode", sscode, Hibernate.STRING);
			query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public void ChangeParliamentConstituency(char constituencyType, int pcCode, String pcNameEnglish, int userid, String pcNameLocal, Integer eciCode) throws BaseAppException {

		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("ParliamentConstituencyQuery").setParameter("constituencyType", constituencyType, Hibernate.CHARACTER).setParameter("constituencyCode", pcCode, Hibernate.INTEGER)
					.setParameter("constituencyNameEnglish", pcNameEnglish, Hibernate.STRING).setParameter("userId", userid, Hibernate.INTEGER).setParameter("constituencyNameLocal", pcNameLocal, Hibernate.STRING)
					.setParameter("eciCode", eciCode, Hibernate.INTEGER);

			query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public int getMaxDistrictCode() throws BaseAppException {
		int MaxCode = 0;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("Select COALESCE(max(district_code),1) from district");
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxCode = Integer.parseInt(list.get(0).toString());
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return MaxCode;
	}

	@Override
	public boolean save(Subdistrict subdistrict, Session session) throws BaseAppException {
		try {
			session.saveOrUpdate(subdistrict);
			session.flush();
			if (session.contains(subdistrict)) {
				session.evict(subdistrict);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	@Override
	public List<Subdistrict> getListVillageDetails(String query) throws BaseAppException {
		Query criteria = null;
		Session session = null;
		List<Subdistrict> getListVillageDetails = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			getListVillageDetails = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getListVillageDetails;
	}

	@Override
	public boolean saveDistrict(Subdistrict subdistrict) throws BaseAppException {
		Session session = null;
		Transaction txn = null;

		try {

			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			session.saveOrUpdate(subdistrict);
			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if (txn != null) {
				txn.rollback();
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
	public boolean updateLReplaces(int lrReplaces, SubdistrictPK subdistrictPK) throws BaseAppException {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();

			Subdistrict subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
			subdistrict.setLrReplaces(lrReplaces);// .setLrPartCode(lrPartCode);
			session.update(subdistrict);
			tx.commit();

		} catch (Exception e) {

			log.debug("Exception" + e);
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
	public boolean updateIsActive(SubdistrictPK subdistrictPK) throws BaseAppException {

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();

			Subdistrict subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
			subdistrict.setIsactive(false);
			session.update(subdistrict);
			tx.commit();
		} catch (Exception e) {

			log.debug("Exception" + e);
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
	public List<District> getSubdistrictDetails(int subdistrictCode) throws BaseAppException {

		Query criteria = null;
		Session session = null;
		List<District> getSubdistrictDetails = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from District where districtCode=:subdistrictCode and isactive=true").setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER);
			getSubdistrictDetails = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getSubdistrictDetails;
	}

	@Override
	public int getMaxDistrictCode(String query) throws BaseAppException {

		int MaxCode = 0;
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			List list = criteria.list();
			MaxCode = Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return MaxCode;
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getVillageDetailsModify(int districtCode) throws BaseAppException {

		Query criteria = null;
		Session session = null;
		List<Subdistrict> getVillageDetailsModify = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Subdistrict where isactive=true and subdistrictCode=:districtCode").setParameter("districtCode", districtCode, Hibernate.INTEGER);
			getVillageDetailsModify = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getVillageDetailsModify;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getSubdistrictListbyVillageCode(int districtCode) throws BaseAppException {

		List<Village> fullSubdistrictContributingList = new ArrayList<Village>();
		Session session = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Village sd where sd.isactive=true and sd.tlc=:districtCode").setParameter("districtCode", districtCode, Hibernate.INTEGER);
			fullSubdistrictContributingList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return fullSubdistrictContributingList;

	}

	@Override
	public boolean update(String query, Session session) throws BaseAppException {
		try {
			session.createSQLQuery(query).executeUpdate();
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	public List<SubdistrictHistory> getSubDistrictHistoryDetail(char subdistrictNameEnglish, int subdistrictCode) throws BaseAppException {
		Session session = null;

		Query query = null;
		List<SubdistrictHistory> subdistrictList = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getSubdistrictHistoryDetails").setParameter("subdistrictNameEnglish", subdistrictNameEnglish).setParameter("subdistrictCode", subdistrictCode);
			subdistrictList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return subdistrictList;
	}

	public List<SubdistrictHistory> getSubDistHistoryDetailShift(char subdistrictNameEnglish, int subdistrictCode) throws BaseAppException {
		Session session = null;

		Query query = null;
		List<SubdistrictHistory> subdistrictHist = new ArrayList<SubdistrictHistory>();

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getSubdistrictHistoryDetails").setParameter("subdistrictNameEnglish", subdistrictNameEnglish).setParameter("subdistrictCode", subdistrictCode);
			subdistrictHist = query.list();
			return subdistrictHist;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return subdistrictHist;
	}

	@Override
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion) throws BaseAppException {
		Session session = null;
		List<GetGovernmentOrderDetail> govtOrderList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			String NamedQuey="GovOrderDetail";
			if(entityType=='D' || entityType=='T'|| entityType=='V' ) {
				NamedQuey="GovOrderDetailMinor";
			}
			criteria = session.getNamedQuery(NamedQuey).setParameter("entityType", entityType).setParameter("entityCode", entityCode).setParameter("entityVersion", entityVersion);

			govtOrderList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return govtOrderList;

	}

	@Override
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion, Session session) throws BaseAppException {

		List<GetGovernmentOrderDetail> govtOrderList = null;
		Query criteria = null;
		try {
			criteria = session.getNamedQuery("GovOrderDetail").setParameter("entityType", entityType).setParameter("entityCode", entityCode).setParameter("entityVersion", entityVersion);

			govtOrderList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return govtOrderList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Headquarters> getHeadquarterModify(int subdistrictCode, int subdistrictversion, Session session) throws BaseAppException {

		Query query = null;
		List list = null;
		try {
			query = session.createQuery("from Headquarters where lrlc=:subdistrictCode and isactive=true").setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER);
			list = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return list;
	}

	@Override
	public void SetGovermentOrderEntity(String entityCode, char entityType) throws BaseAppException {

		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("GovOrderEntityWiseQuery").setParameter("entityCode", entityCode, Hibernate.STRING).setParameter("entityType", entityType, Hibernate.CHARACTER);

			query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean invalidateFunctionCall(String subdistrictCodes, int subdistrictCode, long roleCode, String villageCodes) throws BaseAppException {
		// TODO invalidateFunctionCall
		Session session = null;
		Transaction txn = null;
		Query query = null;
		int rCode = 0;
		rCode = (int) roleCode;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("invalidateSubdistrictFn");
			query.setParameter("subdistrictCodes", subdistrictCodes, Hibernate.STRING);
			query.setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER);
			query.setParameter("roleCode", rCode, Hibernate.INTEGER);
			query.setParameter("villageCodes", villageCodes, Hibernate.STRING);

			query.list();
			return true;

		} catch (Exception e) {
			log.debug("Exception" + e);

			return false;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public String invalidateFunctionCallSubDistrict(String sourceSubdistCode, int userId, String keyAppend, Date effectiveDate, String orderNo, Date orderDate, Date gazPubDate) throws BaseAppException {
		// TODO invalidateFunctionCall
		Session session = null;
		Transaction txn = null;
		Query query = null;
		List<InvalidateSubDistrictReq> maxCode = null;
		String announceId = null;
		int sourceSubdist = Integer.parseInt(sourceSubdistCode);
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("invalidateSubdistrictFnCall");
			query.setParameter("sourceSubdist", sourceSubdist, Hibernate.INTEGER);
			query.setParameter("userId", userId, Hibernate.INTEGER);
			query.setParameter("keyAppend", keyAppend, Hibernate.STRING);
			query.setParameter("effectiveDate", effectiveDate, Hibernate.DATE);
			query.setParameter("orderNo", orderNo, Hibernate.STRING);
			query.setParameter("orderDate", orderDate, Hibernate.DATE);
			query.setParameter("gazPubDate", gazPubDate, Hibernate.DATE);
			query.setParameter("orderPath", null, Hibernate.STRING);

			maxCode = query.list();

			// maxCode = Integer.parseInt(list.get(0).toString());

			Iterator<InvalidateSubDistrictReq> invalidsubItr = maxCode.iterator();
			InvalidateSubDistrictReq localdata = (InvalidateSubDistrictReq) invalidsubItr.next();
			announceId = localdata.getInvalidate_subdistrict_fn();
			// System.out.println("THE RETURNED Announcement ID::::: " +
			// announceId);

		} catch (Exception e) {
			log.debug("Exception" + e);
			/* Modified by kirandeep for Invalidate Subdistrict on 16/03/2015 */
			throw new BaseAppException(e.getCause().getMessage());
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return announceId;
	}

	/**
	 * Changes as @subdistrictList param not in use for Create Department.
	 * 
	 * @author Ripunj on 15-10-2014
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Subdistrict> getSubDistrictListbyDistrictCode(int districtCode, String subdistrictList) throws BaseAppException {
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		List<District> distList = null;
		distList = new ArrayList<District>();
		String districtName = null;
		Session session1=null;
		Session session2=null;
		try {
			session1=sessionFactory.openSession();
			session2=sessionFactory.openSession();
			distList = session1.createQuery("from District where districtCode=:districtCode").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
			districtName = distList.get(0).getDistrictNameEnglish();
			sdList = session2.createQuery("from Subdistrict sd where sd.isactive=true "
			/*
			 * +
			 * "and sd.subdistrictCode not in :subdistrictList and sd.district.districtPK.districtCode=:districtCode order by sd.subdistrictNameEnglish"
			 * )
			 */
			+ " and sd.subdistrictCode not in (" + subdistrictList + "  ) and sd.district.districtPK.districtCode=:districtCode order by sd.subdistrictNameEnglish").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
			/*
			 * .setParameter("subdistrictList",
			 * subdistrictList,Hibernate.STRING)
			 */
			for (int i = 0; i < sdList.size(); i++) {
				sdList.get(i).setSubdistrictNameEnglish(sdList.get(i).getSubdistrictNameEnglish() + " (" + districtName + ")");
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}  finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
			if (session2 != null && session2.isOpen()) {
				session2.close();
			}
		}
		return sdList;
	}

	@Override
	public int getMaxSubdistrictVersion(int subdistrictCode) throws BaseAppException {
		int MaxVersionCode = 0;
		try {

			MaxVersionCode = Integer.parseInt(sessionFactory.getCurrentSession().createSQLQuery("" + "select max(subdistrict_version) from subdistrict  where subdistrict_code=:subdistrictCode")
					.setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER).list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return MaxVersionCode;
	}

	@Override
	public int getMaxSubdistrictVersion(int subdistrictCode, Session session) throws BaseAppException {
		int MaxVersionCode = 0;

		try {

			MaxVersionCode = Integer.parseInt(session.createSQLQuery("" + "select max(subdistrict_version) from subdistrict where isactive=true and subdistrict_code=:subdistrictCode")
					.setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER).list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return MaxVersionCode;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Subdistrict getSubdistrictDetailsMaxVersionByCode(int subdistrictCode, int subdistrictVersion) throws BaseAppException {

		Subdistrict subdistrictBean = new Subdistrict();
		List<Subdistrict> sdList = new ArrayList<Subdistrict>();
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select * from subdistrict  where subdistrict_code=" + subdistrictCode + " and subdistrict_version=" + subdistrictVersion).addEntity("subdistrict", Subdistrict.class);

			sdList = query.list();
			subdistrictBean = sdList.get(0);
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrictBean;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictName(int districtCode) throws BaseAppException {
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		List<District> distList = null;
		distList = new ArrayList<District>();
		String districtName = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			distList = session.createQuery("from District where districtCode=:districtCode").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
			districtName = distList.get(0).getDistrictNameEnglish();
			sdList = session.createQuery("from Subdistrict sd where sd.isactive=true " + "and sd.district.districtPK.districtCode=:districtCode order by sd.subdistrictNameEnglish").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
			for (Subdistrict subDistBean : sdList) {

				subDistBean.setSubdistrictNameEnglish(subDistBean.getSubdistrictNameEnglish() + " (" + districtName + ")");
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return sdList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subdistrict> getSubDistrictList(String sQuery) throws BaseAppException {
		Session session = null;
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		try {
			session = sessionFactory.openSession();
			sdList = session.createSQLQuery(sQuery).addEntity("Subdistrict", Subdistrict.class).list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return sdList;
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
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return attachmentList;
	}

	@Override
	public boolean updateAssembly(String query, Session session) throws BaseAppException {
		try {
			session.createSQLQuery(query).executeUpdate();
			session.flush();
			if (session.contains(query)) {
				session.evict(query);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public boolean modifyParliamentInfo(ParliamentForm subDistrictForm, ParliamentConstituencyId sdpk, int map_landRegionCode, Session session1) throws BaseAppException {
		// TODO Auto-generated method stub

		try {
			ParliamentConstituency subdistrict = new ParliamentConstituency();
			List<ParliamentDataForm> listSubdistrict = new ArrayList<ParliamentDataForm>();
			listSubdistrict = subDistrictForm.getListParliamentFormDetail();
			Iterator<ParliamentDataForm> itr = listSubdistrict.iterator();
			while (itr.hasNext()) {
				ParliamentDataForm element = (ParliamentDataForm) itr.next();
				session1.flush();
				subdistrict = (ParliamentConstituency) session1.get(ParliamentConstituency.class, sdpk);
				subdistrict.setPcNameEnglish(element.getPcNameEnglish().trim());
				subdistrict.setPcNameLocal(element.getPcNameLocal().trim());
				subdistrict.setEciCode(element.getEciCode());
				subdistrict.setEffectiveDate(element.getEffectiveDate());

				subdistrict.setIsactive(true);

				subdistrict.setId(sdpk);
				session1.update(subdistrict);

			}
		} catch (Exception e) {

			log.debug("Exception" + e);

			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode) throws BaseAppException {

		Query criteria = null;
		Session session = null;
		List<MapLandRegion> getMapDetailsModify = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from MapLandRegion where map_landregion_code=:mapLandregionCode").setParameter("mapLandregionCode", mapLandregionCode, Hibernate.INTEGER);
			getMapDetailsModify = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getMapDetailsModify;
	}

	public int getMaxStandardcodesVersion(int subDistrictCode) throws BaseAppException {
		int MaxVersionCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			MaxVersionCode = Integer.parseInt(session.createSQLQuery("" + "select max(village_version) from village where isactive=true and village_code=:subDistrictCode").setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER).list()
					.get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxVersionCode;
	}

	public int getMaxAssemblyVersion(int subDistrictCode) throws BaseAppException {
		int MaxVersionCode = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			MaxVersionCode = Integer.parseInt(session.createSQLQuery("" + "select max(ac_version) from assembly_constituency where isactive=true and ac_code=:subDistrictCode").setParameter("subDistrictCode", subDistrictCode, Hibernate.INTEGER)
					.list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxVersionCode;
	}

	@Override
	public int getRecordsforSubdistrictName(String subdistrictName) throws BaseAppException {
		// TODO Auto-generated method stub
		Query criteria = null;
		Session session = null;
		subdistrictName = subdistrictName.trim().toUpperCase();
		int recordCount = 0;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select count(*) from Subdistrict where UPPER(TRIM(subdistrict_name_english)) LIKE :subdistrictName");
			criteria.setParameter("subdistrictName", subdistrictName, Hibernate.STRING);
			List list = criteria.list();
			if (!list.isEmpty() && list.get(0) != null) {
				recordCount = Integer.parseInt(list.get(0).toString());
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return recordCount;
	}

	@Override
	public Subdistrict getSubDistrictXml() throws BaseAppException {
		// TODO Auto-generated method stub
		return null;
	}

	/* Retrieving the Map details attachment from the database */

	@Override
	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode, Session session) throws BaseAppException {
		Query query = null;
		List<EntityWiseMapDetailsPojo> EntityWiseMapDetail = null;
		try {
			query = session.getNamedQuery("getEntityWiseMapDetailsFn").setParameter("entityType", entityType).setParameter("entityCode", entityCode);
			EntityWiseMapDetail = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return EntityWiseMapDetail;

	}

	@Override
	public int saveDataInMap(SubDistrictForm subDistrictForm, List<AttachedFilesForm> validFileMap, HttpSession httpSession, Session session) {
		MapAttachment attachment = null;
		boolean flag = true;
		Integer attId = null;
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
					attachment.setMapEntityType('T');
					attachment = (MapAttachment) session.merge(attachment);
					Long attachmentId = attachment.getAttachmentId();
					attId = attachmentId.intValue();

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);

		}

		return attId;
	}

	@Override
	public String insertSubDistrict(HttpServletRequest request, HttpSession httpSession, SubDistrictForm subDistrictForm, Session session) {

		// SessionObject sessionObject =
		// (SessionObject)httpSession.getAttribute("sessionObject");
		Integer p_district_code = null;
		if (subDistrictForm.getDistrictCode() != null && !subDistrictForm.getDistrictCode().equals("")) {
			p_district_code = Integer.parseInt(subDistrictForm.getDistrictCode());
		}

		// Integer p_userid = sessionObject.getUserId().intValue();
		String p_subdistrict_name_english = subDistrictForm.getSubdistrictNameEnglish();
		String p_subdistrict_name_local = subDistrictForm.getSubdistrictNameLocal();
		String p_alias_english = subDistrictForm.getAliasEnglish();
		String p_alias_local = subDistrictForm.getAliasLocal();
		Integer p_census_2011_code = null;
		/*if (subDistrictForm.getCensus2011Code() != null && !subDistrictForm.getCensus2011Code().equals("")) {
			p_census_2011_code = Integer.parseInt(subDistrictForm.getCensus2011Code());
		}*/
		String p_sscode = subDistrictForm.getSscode();

		String lati = subDistrictForm.getLati();
		String longi = subDistrictForm.getLongi();
		StringBuffer sb =null;
		if(lati!=null && longi!=null){
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
		

		String p_coordinates = sb!=null?sb.toString():null;// 321:321,2132:32132
		String p_headquarter_name_english = subDistrictForm.getHeadquarterName();
		String headquarter_local_name = subDistrictForm.getHeadquarterNameLocal();
		String p_order_no = subDistrictForm.getOrderNo();

		Date effectiveDate = subDistrictForm.getEffectiveDate();
		Date gazPubDate = subDistrictForm.getGazPubDate();
		Date orderDate = subDistrictForm.getOrderDate();
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
		String fullsubDistrictList = "";
		String partsubDistrictList = "";
		String subDistrictList = subDistrictForm.getContributedSubDistricts();
		String[] subDistrictListArray = subDistrictList.split(",");
		for (int i = 0; i < subDistrictListArray.length; i++) {
			String string = subDistrictListArray[i];
			if (string.contains("FULL")) {
				string = string.replaceAll("FULL", "");
				if (fullCount == 1) {
					fullsubDistrictList = string;
					fullCount++;
				} else {
					fullsubDistrictList = fullsubDistrictList + "," + string;
				}
			}
			if (string.contains("PART")) {
				string = string.replaceAll("PART", "");
				if (partCount == 1) {
					partsubDistrictList = string;
					partCount++;
				} else {
					partsubDistrictList = partsubDistrictList + "," + string;
				}
			}

		}
		int villCount = 1;
		String list_of_villages_full_temp = "";
		String contriVillagesList = subDistrictForm.getContributedVillages();
		if (contriVillagesList != null) {
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
		String list_of_subdistrict_full = fullsubDistrictList;// 420FULL,5965PART
		String list_of_subdistrict_part = partsubDistrictList;
		String list_of_villages_full = list_of_villages_full_temp;// Meragon
																	// (sasasa)
																	// (FULL),Migrata
																	// (sasasa)
																	// (FULL)

		

		/*
		 * System.out.println("p_district_code "+p_district_code);
		 * System.out.println("p_userid "+12345);
		 * System.out.println("p_subdistrict_name_english "
		 * +p_subdistrict_name_english);
		 * System.out.println("p_subdistrict_name_local "
		 * +p_subdistrict_name_local);
		 * System.out.println("p_alias_english "+p_alias_english);
		 * System.out.println("p_alias_local "+p_alias_local);
		 * System.out.println("p_census_2011_code "+p_census_2011_code);
		 * System.out.println("p_sscode "+p_sscode);
		 * System.out.println("p_coordinates "+p_coordinates);
		 * System.out.println
		 * ("p_headquarter_name_english "+p_headquarter_name_english);
		 * System.out.println("headquarter_local_name "+headquarter_local_name);
		 * System.out.println("p_order_no "+p_order_no);
		 * System.out.println("ordertimeStampDate "+ordertimeStampDate);
		 * System.out.println("effectivetimeStampDate "+effectivetimeStampDate);
		 * System
		 * .out.println("gazPubtimeStampDateTemp "+gazPubtimeStampDateTemp);
		 * System
		 * .out.println("list_of_subdistrict_full "+list_of_subdistrict_full);
		 * System
		 * .out.println("list_of_subdistrict_part "+list_of_subdistrict_part);
		 * System.out.println("list_of_villages_full "+list_of_villages_full);
		 */

		Query query = session.getNamedQuery("createSubDistrictQuery").setParameter("p_district_code", p_district_code, Hibernate.INTEGER).setParameter("p_userid", subDistrictForm.getUserId(), Hibernate.INTEGER)
				.setParameter("p_subdistrict_name_english", p_subdistrict_name_english, Hibernate.STRING).setParameter("p_subdistrict_name_local", p_subdistrict_name_local, Hibernate.STRING)
				.setParameter("p_alias_english", p_alias_english, Hibernate.STRING).setParameter("p_alias_local", p_alias_local, Hibernate.STRING).setParameter("p_census_2011_code", p_census_2011_code, Hibernate.INTEGER)
				.setParameter("p_sscode", p_sscode, Hibernate.STRING).setParameter("p_coordinates", p_coordinates, Hibernate.STRING).setParameter("p_headquarter_name_english", p_headquarter_name_english, Hibernate.STRING)
				.setParameter("headquarter_local_name", headquarter_local_name, Hibernate.STRING).setParameter("p_order_no", p_order_no, Hibernate.STRING).setParameter("p_order_date", ordertimeStampDate, Hibernate.TIMESTAMP)
				.setParameter("p_effective_date", effectivetimeStampDate, Hibernate.TIMESTAMP).setParameter("p_gaz_pub_date", gazPubtimeStampDateTemp, Hibernate.TIMESTAMP)
				.setParameter("list_of_subdistrict_full", list_of_subdistrict_full, Hibernate.STRING).setParameter("list_of_subdistrict_part", list_of_subdistrict_part, Hibernate.STRING)
				.setParameter("list_of_villages_full", list_of_villages_full, Hibernate.STRING).setParameter("mapLandregionCode", subDistrictForm.getMapCode(), Hibernate.INTEGER);
		SubDistrictBean subDistrictBean = (SubDistrictBean) query.list().get(0);
		session.flush();
		if (session.contains(query)) {
			session.evict(query);
		}

		return subDistrictBean.getCreate_subdistrict_fn();
	}

	@Override
	public Subdistrict getById(Integer subDistrictCode, Session session) {
		Subdistrict subdistrict = null;
		try {
			SubdistrictPK subdistrictPK = new SubdistrictPK(subDistrictCode, 1,1);
			subdistrict = (Subdistrict) session.get(Subdistrict.class, subdistrictPK);

		} catch (HibernateException e) {
			log.debug("Exception" + e);
		}
		return subdistrict;
	}

	@Override
	public void saveOrUpdate(int subDistrictId, int mapAttachmentId, Session session) {
		SubdistrictPK subdistrictPK = new SubdistrictPK();
		subdistrictPK.setSubdistrictCode(subDistrictId);
		subdistrictPK.setSubdistrictVersion(1);
		Query query = session.createQuery("update Subdistrict set mapLandregionCode = :mapLandregionCode where subdistrictPK = :subdistrictPK");
		query.setParameter("mapLandregionCode", mapAttachmentId);
		query.setParameter("subdistrictPK", subdistrictPK);

		int result = query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictbySubdistCode(int subdistrictCode) throws BaseAppException {
		Session session = null;
		List<Subdistrict> vlgLst = null;

		try {
			session = sessionFactory.openSession();
			vlgLst = new ArrayList<Subdistrict>();
			// int dlc=commonService.get
			vlgLst = session.createQuery("from Subdistrict v where v.tlc=:tlc and isactive=true order by v.subdistrictNameEnglish").setParameter("tlc", subdistrictCode, Hibernate.INTEGER).list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			vlgLst = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vlgLst;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameExtend(Integer districtCode, Integer orgCode) throws BaseAppException {
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		List<District> distList = null;
		distList = new ArrayList<District>();
		String districtName = null;
		Session session = null;
		Integer dlc = null;
		String tlc = "";
		try {

			tlc = organizationService.getExtendDetaildofEntity(orgCode, 'T', 'T');

			session = sessionFactory.openSession();

			dlc = commonService.getdlc(districtCode);
			distList = session.createQuery("from District where dlc=:dlc").setParameter("dlc", dlc, Hibernate.INTEGER).list();
			districtName = distList.get(0).getDistrictNameEnglish();
			if (tlc != "") {
				sdList = session.createQuery("from Subdistrict sd where sd.isactive=true " + "and dlc=" + dlc + " and tlc not in(" + tlc + ") order by sd.subdistrictNameEnglish").list();
			} else {
				sdList = session.createQuery("from Subdistrict sd where sd.isactive=true " + "and dlc=:dlc order by sd.subdistrictNameEnglish").setParameter("dlc", dlc, Hibernate.INTEGER).list();
			}

			for (Subdistrict subDistBean : sdList) {

				subDistBean.setSubdistrictNameEnglish(subDistBean.getSubdistrictNameEnglish() + " (" + districtName + ")");
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return sdList;
	}

	@Override
	public boolean subDistrictExist(int discode, String subDistrictName) {
		Query criteria = null;
		Session session = null;
		subDistrictName = subDistrictName.trim().toUpperCase();
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Subdistrict sd where sd.dlc=:dc and isactive=true and  UPPER(TRIM(sd.subdistrictNameEnglish)) =:sdName");
			criteria.setParameter("dc", discode, Hibernate.INTEGER);
			criteria.setParameter("sdName", subDistrictName, Hibernate.STRING);
			List list = criteria.list();
			int size = list.size();
			if (size > 0) {
				return false;
			}
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameExtended(int districtCode, int orgCode, int locatedAtLevelCode) throws BaseAppException {
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		List<District> distList = null;
		distList = new ArrayList<District>();
		String districtName = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			distList = session.createQuery("from District where districtCode=:districtCode").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
			districtName = distList.get(0).getDistrictNameEnglish();
			sdList = ((SQLQuery) session
					.createSQLQuery(
							" select case when sd.subdistrict_code  in (select * from get_draft_used_lb_lr_temp(sd.subdistrict_code,'T')) then  cast ('F' as character)" + " else cast('A' as character) end as operation_state,"
									+ " case when sd.subdistrict_code =a.entity_code then cast('F' as character) else cast('A' as character) end as operation_extend_flag,"
									+ " sd.subdistrict_code as subdistrictCode, sd.subdistrict_name_english as subdistrictNameEnglish from subdistrict sd "
									+ " left outer join (select entity_code from org_coverage_detail where isactive and  coverage_code in "
									+ " (select coverage_detail_code from org_coverage where org_coverage_entity_type=3 and isactive and  org_located_level_code in "
									+ " (select org_located_level_code from org_located_at_levels  where olc=:orgCode and isactive and located_at_level=:locatedAtLevelCode)))as a" + " ON sd.subdistrict_code =a.entity_code "
									+ " where dlc=:districtCode and isactive  order by sd.subdistrict_name_english ")

					.setParameter("districtCode", districtCode, Hibernate.INTEGER).setParameter("orgCode", orgCode, Hibernate.INTEGER).setParameter("locatedAtLevelCode", locatedAtLevelCode, Hibernate.INTEGER)).addScalar("subdistrictCode")
					.addScalar("subdistrictNameEnglish").addScalar("operation_state").addScalar("operation_extend_flag").setResultTransformer(Transformers.aliasToBean(Subdistrict.class)).list();
			for (Subdistrict subDistBean : sdList) {

				subDistBean.setSubdistrictNameEnglish(subDistBean.getSubdistrictNameEnglish() + " (" + districtName + ")");
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return sdList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subdistrict> getSubDistrictListbyDistrictCodeExtended(int districtCode, String subdistrictList, Integer orgCode, Integer locatedAtLevelCode) throws BaseAppException {
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		List<District> distList = null;
		distList = new ArrayList<District>();
		String districtName = null;
		Session session1=null;
		Session session2=null;
		
			
		try {
			session1=sessionFactory.openSession();
			session2=sessionFactory.openSession();
			distList = session1.createQuery("from District where districtCode=:districtCode").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
			districtName = distList.get(0).getDistrictNameEnglish();
			sdList = ((SQLQuery) session2
					.createSQLQuery(
							" select case when sd.subdistrict_code  in (select * from get_draft_used_lb_lr_temp(sd.subdistrict_code,'T')) then  cast ('F' as character)"
									+ " else cast('A' as character) end as operation_state,case when sd.subdistrict_code =a.entity_code then cast('F' as character) else cast('A' as character) end as operation_extend_flag,"
									+ " sd.subdistrict_code as subdistrictCode, sd.subdistrict_name_english as subdistrictNameEnglish from subdistrict sd "
									+ " left outer join (select entity_code from org_coverage_detail where isactive and  coverage_code in "
									+ " (select coverage_detail_code from org_coverage where org_coverage_entity_type=3 and isactive and  org_located_level_code in "
									+ " (select org_located_level_code from org_located_at_levels  where olc=:orgCode and isactive and located_at_level=:locatedAtLevelCode)))as a" + " ON sd.subdistrict_code =a.entity_code "
									+ " where dlc=:districtCode and isactive and sd.subdistrict_code not in (" + subdistrictList + "  ) order by sd.subdistrict_name_english ")

					.setParameter("districtCode", districtCode, Hibernate.INTEGER).setParameter("orgCode", orgCode, Hibernate.INTEGER).setParameter("locatedAtLevelCode", locatedAtLevelCode, Hibernate.INTEGER)).addScalar("subdistrictCode")
					.addScalar("subdistrictNameEnglish").addScalar("operation_state").addScalar("operation_extend_flag").setResultTransformer(Transformers.aliasToBean(Subdistrict.class)).list();

			for (int i = 0; i < sdList.size(); i++) {
				sdList.get(i).setSubdistrictNameEnglish(sdList.get(i).getSubdistrictNameEnglish() + " (" + districtName + ")");
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
			if (session2 != null && session2.isOpen()) {
				session2.close();
			}
		}
		return sdList;
	}

	/**
	 * added on 11/12/2014 for localbody impact
	 * Modified BY pooja on 13-08-2015
	 */
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListbyDistrictForLocalBody(String districtCode) {
		Session session = null;
		List<Subdistrict> vlgLst = null;
		SQLQuery query = null;

		try {
			session = sessionFactory.openSession();
			vlgLst = new ArrayList<Subdistrict>();
			query = session.createSQLQuery("select case when v.subdistrict_code  in (select local_body_code from get_draft_used_lb_lr_temp(v.subdistrict_code,'T')) "
					+ " then cast('F' as character)  else cast('A' as character) end as operation_state , v.tlc as tlc ,d.district_name_english as districtNameEnglish,"
					+ " v.subdistrict_name_local as subdistrictNameLocal ,v.subdistrict_code as subdistrictCode , v.subdistrict_name_english  as subdistrictNameEnglish from subdistrict v, "
					+ " district d  where v.dlc=d.district_code and d.district_code in ("+ districtCode +") and v.isactive=true and d.isactive order by d.district_name_english, v. subdistrict_name_english");
			query.addScalar("operation_state");
			query.addScalar("tlc");
			query.addScalar("districtNameEnglish");
			query.addScalar("subdistrictNameLocal");
			query.addScalar("subdistrictCode");
			query.addScalar("subdistrictNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			vlgLst = query.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			vlgLst = null;
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vlgLst;

	}

	/**
	 * Changes for local Body Impact
	 * 
	 * @author Ripunj on 10-12-2014
	 * @param distCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistListbyDistrictCodeCov(int distCode) throws Exception {

		SQLQuery criteria = null;
		Session session = null;
		List<Subdistrict> subdistList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select case when t.subdistrict_code  in (select local_body_code from get_draft_used_lb_lr_temp(t.subdistrict_code,'T')) then  "
					+ " cast ('F' as character) else cast('A' as character) end as operation_state,t.subdistrict_code as subdistrictCode ,t.subdistrict_name_english as subdistrictNameEnglish" + " from subdistrict t where "
					+ " t.subdistrict_code=:distCode and isactive=true order by t.subdistrict_name_english ");
			criteria.setParameter("distCode", distCode, Hibernate.INTEGER);
			criteria.addScalar("operation_state");
			criteria.addScalar("subdistrictCode").addScalar("subdistrictNameEnglish");
			criteria.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			subdistList = criteria.list();
		} catch (HibernateException e) {
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistList;

	}

	/* added on 31/12/2014 for the localbody impact by kirandeep */
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListForLocalbody(int districtCode) throws Exception {

		SQLQuery criteria = null;
		Session session = null;
		List<Subdistrict> subdistList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createSQLQuery("select case when t.subdistrict_code  in (select local_body_code from get_draft_used_lb_lr_temp(t.subdistrict_code,'T')) then   cast ('F' as character) else cast('A' as character) end as operation_state,"
							+ " t.subdistrict_code as subdistrictCode ,t.subdistrict_name_english as subdistrictNameEnglish,subdistrict_name_local as subdistrictNameLocal from subdistrict t where t.dlc=:distCode " + " and t.isactive=true order by t.subdistrict_name_english  ");
			criteria.setParameter("distCode", districtCode, Hibernate.INTEGER);
			criteria.addScalar("operation_state");
			criteria.addScalar("subdistrictCode").addScalar("subdistrictNameEnglish").addScalar("subdistrictNameLocal");
			criteria.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			subdistList = criteria.list();
		} catch (HibernateException e) {
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistList;

	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListbyDistrictInDistrictForLocalBody(int districtCode) {
		Session session = null;
		List<Subdistrict> vlgLst = null;
		SQLQuery query = null;

		try {
			session = sessionFactory.openSession();
			vlgLst = new ArrayList<Subdistrict>();
			int dlc = commonService.getdlc(districtCode);
			query = session.createSQLQuery("select case when v.subdistrict_code  in (select local_body_code from get_draft_used_lb_lr_temp(v.subdistrict_code,'T')) "
					+ " then cast('F' as character)  else cast('A' as character) end as operation_state , v.tlc as tlc ,d.district_name_english as districtNameEnglish,"
					+ " v.subdistrict_name_local as subdistrictNameLocal ,v.subdistrict_code as subdistrictCode , v.subdistrict_name_english  as subdistrictNameEnglish from subdistrict v, "
					+ " district d  where v.dlc=d.district_code and d.district_code=:dlc and" + " v.isactive=true and d.isactive order by v. subdistrict_name_english");// ("from Subdistrict v where v.dlc=:dlc and isactive=true order by v.subdistrictNameEnglish")
			query.setParameter("dlc", dlc, Hibernate.INTEGER);
			query.addScalar("operation_state");
			query.addScalar("tlc");
			query.addScalar("districtNameEnglish");
			query.addScalar("subdistrictNameLocal");
			query.addScalar("subdistrictCode");
			query.addScalar("subdistrictNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			vlgLst = query.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			vlgLst = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vlgLst;

	}

	/* added on 05/01/2015 for local body impact by kirandeep */

	@SuppressWarnings("unchecked")
	public List<Village> getSubdistrictListbyVillageCodeForDistrictForm(int districtCode) throws BaseAppException {

		List<Village> fullSubdistrictContributingList = new ArrayList<Village>();
		Session session = null;

		SQLQuery query = null;

		try {
			session = sessionFactory.openSession();

			query = session
					.createSQLQuery("select case when v.village_code  in (select local_body_code from get_draft_used_lb_lr_temp(v.village_code,'V')) "
							+ " then cast('F' as character)  else cast('A' as character) end as operation_state ,v.vlc as vlc , v.village_name_english as villageNameEnglish , v.village_name_local as villageNameLocal from village v where v.isactive=true and v.tlc =:districtCode ");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.addScalar("operation_state");
			query.addScalar("vlc");
			query.addScalar("villageNameEnglish");
			query.addScalar("villageNameLocal");
			
			query.setResultTransformer(Transformers.aliasToBean(Village.class));
			fullSubdistrictContributingList = query.list();

			// criteria =
			// session.createQuery("from Village sd where sd.isactive=true and sd.tlc=:districtCode").setParameter("districtCode",
			// districtCode, Hibernate.INTEGER);
			// fullSubdistrictContributingList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return fullSubdistrictContributingList;

	}

	/**
	 * 
	 * added by kirandeep on 08/01/2015 for localbody impact
	 * 
	 * @param districtCode
	 * @return
	 * @throws BaseAppException
	 */

	@Override
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameForLocalBody(int districtCode) throws BaseAppException {
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		List<District> distList = null;
		distList = new ArrayList<District>();
		String districtName = null;
		Session session = null;
		SQLQuery query = null;
		try {
			session = sessionFactory.openSession();
			distList = session.createQuery("from District where districtCode=:districtCode").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
			districtName = distList.get(0).getDistrictNameEnglish();

			sdList = new ArrayList<Subdistrict>();

			query = session.createSQLQuery(" select case when sd.subdistrict_code  in (select local_body_code from get_draft_used_lb_lr_temp(sd.subdistrict_code,'T')) "
					+ "then cast('F' as character)  else cast('A' as character) end as operation_state , subdistrict_code as subdistrictCode,subdistrict_name_english as subdistrictNameEnglish "
					+ " from subdistrict sd where  sd.dlc=:districtCode  and sd.isactive order by sd.subdistrict_name_english");
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER); //Missing isactive  Change by Maneesh Kumar 13 June 2017
			// query.setParameter("sourceDistrictCode", sourceDistrictCode,
			// Hibernate.INTEGER);
			query.addScalar("operation_state");
			query.addScalar("subdistrictCode");
			query.addScalar("subdistrictNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			sdList = query.list();

			/*
			 * sdList = session .createQuery(
			 * "from Subdistrict sd where sd.isactive=true " +
			 * "and sd.district.districtPK.districtCode=:districtCode order by sd.subdistrictNameEnglish"
			 * ) .setParameter("districtCode", districtCode,
			 * Hibernate.INTEGER).list();
			 */
			for (Subdistrict subDistBean : sdList) {

				subDistBean.setSubdistrictNameEnglish(subDistBean.getSubdistrictNameEnglish() + " (" + districtName + ")");
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return sdList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subdistrict> getSubDistrictListbyDistrictCodeForLocalBody(int districtCode, String subdistrictList) throws BaseAppException {
		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();
		List<District> distList = null;
		distList = new ArrayList<District>();
		String districtName = null;
		Session session = sessionFactory.openSession();
		Session session1 =sessionFactory.openSession();
		try {
			distList = session1.createQuery("from District where districtCode=:districtCode").setParameter("districtCode", districtCode, Hibernate.INTEGER).list();
			districtName = distList.get(0).getDistrictNameEnglish();

			String queryBuilder = "select case when sd.subdistrict_code  in (select * from get_draft_used_lb_lr_temp(sd.subdistrict_code,'T')) then  "
					+ "	cast ('F' as character) else cast('A' as character) end as operation_state,  sd.subdistrict_code as subdistrictCode , "
					+ " sd.subdistrict_name_english as subdistrictNameEnglish from subdistrict sd inner join district d on  d.district_code =:districtCode and " + " d.dlc=sd.dlc where sd.subdistrict_code not in( " + subdistrictList
					+ ") order by subdistrictNameEnglish ";

			SQLQuery query = session.createSQLQuery(queryBuilder);
			query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			query.addScalar("subdistrictCode").addScalar("subdistrictNameEnglish").addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class)).list();
			sdList = query.list();

			for (int i = 0; i < sdList.size(); i++) {
				sdList.get(i).setSubdistrictNameEnglish(sdList.get(i).getSubdistrictNameEnglish() + " (" + districtName + ")");
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
		}
		return sdList;
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubdistrictList(Integer districtCode) throws Exception {

		Query query = null;
		Session session = null;
		List<Subdistrict> subdistList = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Subdistrict where isactive=true and dlc=:districtCode order by subdistrictNameEnglish");
			query.setParameter("districtCode", districtCode);
			subdistList = query.list();
		} catch (HibernateException e) {
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistList;

	}

	public List<SubdistrictHistory> getSubDistHistoryReport(char subdistrictNameEnglish, int subdistrictCode) throws BaseAppException {
		Session session = null;

		SQLQuery query = null;
		List<SubdistrictHistory> subdistrictHist = new ArrayList<SubdistrictHistory>();

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select * from get_land_region_history_fn_for_citizen(?,?)");

			query.setParameter(0, subdistrictNameEnglish);
			query.setParameter(1, subdistrictCode);

			query.addEntity(SubdistrictHistory.class);
			subdistrictHist = query.list();
			return subdistrictHist;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return subdistrictHist;
	}

	public List<Village> getvillagesInDraftMode(Integer subdistrictCode) throws BaseAppException {

		List<Village> villageList = new ArrayList<Village>();
		Session session = sessionFactory.openSession();

		try {
			String queryBuilder = "select a.village_name_english as villageNameEnglish ,a.village_code as villageCode from village a where a.isactive=true and a.tlc =:subdistrictCode	and exists (select lb_lr_code from draft_master b where a.village_code=b.lb_lr_code);  ";
			SQLQuery query = session.createSQLQuery(queryBuilder);
			query.setParameter("subdistrictCode", subdistrictCode, Hibernate.INTEGER);
			query.addScalar("villageCode").addScalar("villageNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Village.class));
			villageList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return villageList;

	}

	/**
	 * 
	 * added by Anju Gupta on 23/03/2015
	 * 
	 * @return sub District List join with District
	 * @throws Changed
	 *             by kirandeep for Is pesa langregion on 01/05/2015
	 * 
	 */

	public List<Subdistrict> getSubdistrictListBySLCfprMarkPesa(int slc) throws Exception {
		Session session = null;

		SQLQuery query = null;
		List<Subdistrict> subdistrictlist = new ArrayList<Subdistrict>();

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("SELECT s.subdistrict_name_english as subdistrictNameEnglish,s.subdistrict_code as subdistrictCode,"
					+ "s.is_pesa as is_pesa,d.district_name_english as districtNameEnglish FROM subdistrict s, district d WHERE  s.dlc=d.dlc and d.is_pesa='P' and d.slc=:slc");
			query.setParameter("slc", slc, Hibernate.INTEGER);
			query.addScalar("subdistrictCode");
			query.addScalar("subdistrictNameEnglish");
			query.addScalar("is_pesa");
			query.addScalar("districtNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			subdistrictlist = query.list();
			return subdistrictlist;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return subdistrictlist;
	}

	/**
	 * Get List<Subdistrict> base on Creteria for extend organigation Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 */
	public List<Subdistrict> getSubDistrictListbyCreteria(List<Integer> districtList, List<Integer> subDistrictCodeList, Integer districtCode) throws Exception {
		Session session = null;
		List<Subdistrict> SubdistrictList = null;
		try {

			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select case when sd.subdistrict_code  in (select * from get_draft_used_lb_lr_temp(sd.subdistrict_code,'T')) then  cast ('F' as character) else cast('A' as character)"
					+ " end as operation_state, sd.subdistrict_code as subdistrictCode , (btrim(sd.subdistrict_name_english)||'('||btrim(d.district_name_english)||')') as subdistrictNameEnglish from "
					+ "subdistrict sd left join district d on d.dlc=sd.dlc where sd.isactive and d.isactive and  ");
			if (districtList != null && !districtList.isEmpty()) {
				queryBuild.append(" sd.subdistrict_code in(:subDistrictCodeList) and sd.dlc not in(:districtList)");
			} else if (districtCode != null) {
				queryBuild.append(" sd.subdistrict_code not in(:subDistrictCodeList) and sd.dlc=:districtCode");
			} else {
				queryBuild.append(" sd.subdistrict_code in(:subDistrictCodeList)");
			}
			queryBuild.append("	order by subdistrictNameEnglish ");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			if (districtList != null && !districtList.isEmpty()) {
				query.setParameterList("districtList", districtList);
			}
			if (districtCode != null) {
				query.setParameter("districtCode", districtCode);
			}
			query.setParameterList("subDistrictCodeList", subDistrictCodeList);
			query.addScalar("subdistrictCode");
			query.addScalar("subdistrictNameEnglish");
			query.addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			SubdistrictList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return SubdistrictList;
	}

	@Override
	public Response saveEffectiveDateEntitySubdistrict(List<GetEntityEffectiveDate> getEntityEffectiveDateList,
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
				query.setParameter("entityType", "T");
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
						
						query = session.createSQLQuery("select * from change_subdistrict_effective_date_fn(:parameter1,:userId)");
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
	
	
	
	
	
	
	
	
}
