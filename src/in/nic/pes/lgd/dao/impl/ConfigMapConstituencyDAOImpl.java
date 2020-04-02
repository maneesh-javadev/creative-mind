package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.ConfigurationMapConstituency;
import in.nic.pes.lgd.common.LgdSession;
import in.nic.pes.lgd.dao.ConfigMapConstituencyDAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ConfigMapConstituencyDAOImpl implements ConfigMapConstituencyDAO {
	
	private static final Logger log = Logger.getLogger(ConfigMapConstituencyDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean save(
			ConfigurationMapConstituency configurationMapConstituency)throws Exception {
		Session session = null;
		Transaction tx = null;
		
			
		try {
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.save(configurationMapConstituency);
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);

			return false;
		}finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	@Override
	public int getMaxRecords(String query) throws Exception{
		// TODO Auto-generated method stub

		int MaxCode = 0;
		try {
			MaxCode = Integer.parseInt(new LgdSession().getSession(sessionFactory)
					.createSQLQuery(query).list().get(0).toString());
		} catch (Exception e) {
			//System.out.println("MaxCode----------------" + MaxCode);
			log.debug("Exception" + e);
		}
	 
		return MaxCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationMapConstituency> getConfigureMapConstituencyDetail(
			String Hql)throws Exception {
		Session session = null;
		List<ConfigurationMapConstituency> configList = null;
		try {
			session = sessionFactory.openSession();

			Query criteria = session.createQuery(Hql);
			configList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {

			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return configList;

	}

	/*@Override
	public List<ConfigurationMapConstituency> getConstituencyDetails(int code)throws Exception
	  {
		
		Query criteria = null;
		Session session = null ;
		List<ConfigurationMapConstituency> configurationMapConstituencyList=null;
		try {
			session=sessionFactory.openSession();
			criteria = session.createQuery("from ConfigurationMapConstituency where isactive=true and configurationMapConstituencyCode=:code");
			criteria.setParameter("code",code,Hibernate.INTEGER);
			configurationMapConstituencyList=criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		finally
		{
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return configurationMapConstituencyList;
 
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationMapConstituency> getConfigurationMapConstituencyDetails(
			String HQL)throws Exception {
		Session session = null;
		List<ConfigurationMapConstituency> getConfigurationMapConstituencyDetails = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(HQL);
			getConfigurationMapConstituencyDetails = query.list();
			 
		} catch (Exception e) {
			log.debug("Exception" + e);
		}finally{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return getConfigurationMapConstituencyDetails;
	}

	@Override
	public boolean update(
			ConfigurationMapConstituency configurationMapConstituency)throws Exception {
		 
		Session session = null;
		Transaction tx = null;
		try {
			session = new LgdSession().getSession(sessionFactory);

			tx = session.beginTransaction();
			session.update(configurationMapConstituency);
			tx.commit();

		} catch (Exception e) {
			 
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return false;
		}
	
		return true;

	}

	@Override
	public boolean savewithsession(
			ConfigurationMapConstituency configurationMapConstituency,
			Session session)throws Exception {
	 
		//int code = 0;
		try {
			//code = (Integer) 
			session.save(configurationMapConstituency);
			session.flush();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public boolean updateIsActiveLevel(int id, Session session)throws Exception {
		try {
			ConfigurationMapConstituency configurationMapConstituencyinactive = (ConfigurationMapConstituency) session
					.load(ConfigurationMapConstituency.class, id);
			configurationMapConstituencyinactive.setIsactive(false);
			session.update(configurationMapConstituencyinactive);
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

}
