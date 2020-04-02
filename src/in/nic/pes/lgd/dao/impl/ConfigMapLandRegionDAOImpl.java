package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.ConfigurationMapLandregion;
import in.nic.pes.lgd.bean.ViewConfigMapLandRegion;
import in.nic.pes.lgd.dao.ConfigMapLandRegionDAO;
import in.nic.pes.lgd.service.CommonService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Service
public class ConfigMapLandRegionDAOImpl implements ConfigMapLandRegionDAO { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(ConfigMapLandRegionDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired CommonService commonservice;

	@Override
	public int saveOrUpdate(
			ConfigurationMapLandregion ConfigurationMapLandregion)
			throws Exception {
		Session session = null;
		Transaction tx = null;
		int max = 0;
		//Object obj = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();

			session.saveOrUpdate(ConfigurationMapLandregion);
			tx.commit();
			max = getMaxRecordsForLevel();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return max;
		} finally {
			if(session != null && session.isOpen())
			{	
				session.close();	
			}
		}	
		return max;

	}

	@Override
	public int save(ConfigurationMapLandregion ConfigurationMapLandregion)
			throws Exception {
		Session session = null;
		Transaction tx = null;
		int max = 0;

		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			session.save(ConfigurationMapLandregion);
			tx.commit();
			max = getMaxRecordsForLevel();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return max;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return max;

	}

	@Override
	public int saveWithSession(
			ConfigurationMapLandregion configurationMapLandregion,
			Session session) throws Exception {

		int code = 0;
		//Object obj;

		try {
			code = (Integer) session.save(configurationMapLandregion);
			// session.flush();
			// session.refresh(configurationMapLandregion);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return code;

	}

	@Override
	public int getMaxRecords(String sqlquery) throws Exception {
		int MaxCode = 0;
		Session session = null;
		Query query = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery(sqlquery);
			list = query.list();
			MaxCode = Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return MaxCode + 1;
	}

	@Override
	public int getMaxRecordsForLevel() throws Exception {
		int maxCode = 0;
		Session session = null;
		Query query = null;
		//List list = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("select COALESCE(max(configuration_map_landregion_code),1) from configuration_map_landregion");
			//list = query.list();
			//maxCode = Integer.parseInt(list.get(0).toString());
			maxCode = (Integer) query.uniqueResult();
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return maxCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationMapLandregion> getConfigurationMapLandDetails(
			String HQL) throws Exception {
		Session session = null;
		Query query = null;
		List<ConfigurationMapLandregion> getConfigurationMapLandDetails = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(HQL);
			getConfigurationMapLandDetails = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return getConfigurationMapLandDetails;
	}

	@Override
	public boolean update(ConfigurationMapLandregion configurationMapLandregion)
			throws Exception {

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			session.update(configurationMapLandregion);
			tx.commit();

		} catch (Exception e) {

			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}
			return false;
		}

		finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewConfigMapLandRegion> GovOrderDetail(int stateCode)
			throws Exception {

		Session session = null;
		Query criteria = null;
		List<ViewConfigMapLandRegion> GovOrderDetail = null;
		try {
			session = sessionFactory.openSession();
			//int slc=commonservice.getSlc(stateCode);
			criteria = session.getNamedQuery("getConfigMapdetail")  
					.setParameter("stateCode", stateCode);

			GovOrderDetail = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}

		return GovOrderDetail;
	}

	
	/*@Override
	public Map<String,String> checkMapConfigurationforConstituency(int stateCode,char constituencyType) throws Exception
	{
		Map<String,String> hMap= new HashMap<String, String>();
		Session session = null;
		Query criteria = null;
		List<ConfigurationMapConstituency> configurationMapConstituencyList = null;
		try {
			session = sessionFactory.openSession();
			int slc=commonservice.getSlc(stateCode);
			criteria = session.createQuery("from ConfigurationMapConstituency where slc=:slc and isactive=:isactive and constituencyType=:constituencyType")  
					.setParameter("slc", slc,Hibernate.INTEGER)
					.setParameter("isactive", true,Hibernate.BOOLEAN)
					.setParameter("constituencyType", constituencyType,Hibernate.CHARACTER);

			configurationMapConstituencyList = criteria.list();
			if(configurationMapConstituencyList.size()>0)
			{
				hMap.put("mapUpload", "true");
			}
			else
			{
				hMap.put("mapUpload", "false");
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			hMap.put("mapUpload", "false");
		}

		finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}

		return hMap;
	}*/
	
	@Override
	public boolean updateisActive(
			ConfigurationMapLandregion configurationMapLandregionupdate,
			Session session) throws Exception {

		try {
			session.update(configurationMapLandregionupdate);
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationMapLandregion> getConfigurationDetails(
			int stateCode) throws Exception {
		Session session = null;
		Query criteria = null;
		//Transaction tx = null;
		List<ConfigurationMapLandregion> configurationMapLandregionList = null;
		configurationMapLandregionList = new ArrayList<ConfigurationMapLandregion>();

		try {
			session = sessionFactory.openSession();

			criteria = session
					.createQuery("from ConfigurationMapLandregion where state.statePK.stateCode=:stateCode and isactive=true");
			criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			configurationMapLandregionList = criteria.list();

		} catch (HibernateException e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return configurationMapLandregionList;

	}

	@Override
	public int getPrimaryKeyfromLandRegionConfig(int slc)
			throws Exception {
		int MaxCode = 0;
		Session session = null;
		Query criteria = null;

		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("select configurationMapLandregionCode from ConfigurationMapLandregion where slc=:slc and isactive=true");
			criteria.setParameter("slc", slc, Hibernate.INTEGER);
			MaxCode = Integer.parseInt(criteria.list().get(0).toString());
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return MaxCode;
	}

}
