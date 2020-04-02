package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.ConfigurationMapLocalbody;
import in.nic.pes.lgd.bean.GetMapConfigLocalBody;
import in.nic.pes.lgd.dao.ConfigMapLocalBodyDAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ConfigMapLocalBodyDAOImpl implements ConfigMapLocalBodyDAO { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(ConfigMapLocalBodyDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean save(ConfigurationMapLocalbody configurationMapLocalbody)
			throws Exception {

		Session session = null;
		Transaction tx = null;
		//Object obj;
		try {
			session = sessionFactory.openSession();
			//System.out.println("Connected :: " + session.isConnected());
			tx = session.beginTransaction();
			try {
				session.save(configurationMapLocalbody);
				tx.commit();
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return false;
		} finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
		return true;

	}

	/*@Override
	public int getMaxRecords(String query) throws Exception {
		// TODO Auto-generated method stub
		int MaxCode = 0;
		try {
			MaxCode = Integer
					.parseInt(sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"select COALESCE(max(configuration_map_localbody_code),1) from configuration_map_localbody")
							.list().get(0).toString());
		} catch (Exception e) {
			//System.out.println("MaxCode----------------" + MaxCode);
			log.debug("Exception" + e);
		}
		//System.out.println("Max Code--" + MaxCode);
		return MaxCode;
	}*/

	@SuppressWarnings("unchecked")
	// TODO Map Local Body DJ
	@Override
	public List<ConfigurationMapLocalbody> getConfigureMapLocalBody(
			String mapConfig) throws Exception {
	 
		Query criteria = null;
		Session session = null;
		List<ConfigurationMapLocalbody> getConfigureMapLocalBody = null;
		try {
			String query = "from ConfigurationMapLocalbody where tier_setup_code in("
					+ mapConfig + ") and isactive=true";
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			getConfigureMapLocalBody = criteria.list();
		 
		} catch (Exception e) {
			log.debug("Exception" + e);
		}finally{
			if(session != null && session.isOpen()){
				session.close();
			}	
		}

		return getConfigureMapLocalBody;
	}

	/*@Override
	public boolean savewithsession(
			ConfigurationMapLocalbody configMapLocalBody, Session session)
			throws Exception {
		// TODO savewithsession
		int code = 0;
		try {
			code = (Integer) session.save(configMapLocalBody);
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return true;
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<GetMapConfigLocalBody> configMapLocalBodyDetail(int stateCode,
			char category) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Query criteria = null;
		List<GetMapConfigLocalBody> configureMapList = null;
		try {
			session = sessionFactory.openSession();

			criteria = session.getNamedQuery("configMaptier")
					.setParameter("stateCode", stateCode)
					.setParameter("category", category);
			configureMapList = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return configureMapList;

	}

	/*@Override
	public boolean update(ConfigurationMapLocalbody configurationMapLocalbody)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		Object obj;
		try {
			session = sessionFactory.openSession();
			//System.out.println("Connected :: " + session.isConnected());
			tx = session.beginTransaction();
			try {

				session.saveOrUpdate(configurationMapLocalbody);
				session.flush();

				return true;

			} catch (HibernateException e) {
				log.debug("Exception" + e);
				return false;

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return false;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}

	}*/

	@Override
	public boolean ConfigMapLgdm(String tierSetupCode, String mapURL, int userId)
			throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.getNamedQuery("configMapLgdm")
					.setParameter("tierSetupCode", tierSetupCode,
							Hibernate.STRING)
					.setParameter("mapURL", mapURL, Hibernate.STRING)
					.setParameter("userId", userId, Hibernate.INTEGER);
			query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){				
				session.close();
			}	
		}
		return true;
	}

}
