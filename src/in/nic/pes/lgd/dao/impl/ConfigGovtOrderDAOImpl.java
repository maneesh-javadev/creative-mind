package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.ConfigurationGovernmentOrder;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.dao.ConfigGovtOrderDAO;

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
public class ConfigGovtOrderDAOImpl implements ConfigGovtOrderDAO {
	private static final Logger log = Logger.getLogger(ConfigGovtOrderDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	Session session = null;
	List<ConfigurationGovernmentOrder> getConfigurationGovernmentOrderDetails = null;
	Query criteria = null;
	
	@Override
	public boolean save(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			/*System.out.println("Connected :configurationGovernmentOrder: "
					+ session.isConnected());*/
			tx = session.beginTransaction();
			try {
				session.save(configurationGovernmentOrder);
				tx.commit();
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			tx.rollback();
			if(tx != null){
				return false;
			}	
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	@Override
	public int getMaxRecords(String query) throws Exception {
		// TODO Auto-generated method stub

		int MaxCode = 0;
		Session session = null;
		Query sqlQuery = null;
		try {
			session = sessionFactory.openSession();
			sqlQuery = session.createSQLQuery(query);
			MaxCode = Integer.parseInt(sqlQuery.list().get(0).toString());
		} catch (Exception e) {

			log.debug("Exception" + e);
			return MaxCode;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return MaxCode;
	}

	@Override
	public boolean setIsActiveDAO(String SQL,Session hsession)throws Exception 
	{
		try
		{
			hsession.createSQLQuery(SQL).executeUpdate();
			
		}catch(Exception e)
		{
			
		}
		
		return true;
	}
	
	@Override
	public synchronized boolean saveAdminPRI(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			try {

				session.save(configurationGovernmentOrder);
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
	
	@Override
	public synchronized boolean saveAdminTrade(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			try {

				session.save(configurationGovernmentOrder);
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
	
	@Override
	public synchronized boolean saveAdminUrban(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			try {

				session.save(configurationGovernmentOrder);
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
	
	@Override
	public synchronized boolean saveAdminLandRegion(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			try {

				session.save(configurationGovernmentOrder);
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

	@Override
	public synchronized boolean saveAdminCenter(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			try {

				session.save(configurationGovernmentOrder);
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
	
	@Override
	public synchronized boolean saveAdminConstituency(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			try {

				session.save(configurationGovernmentOrder);
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
	
	@Override
	public synchronized boolean saveAdminBlock(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			try {

				session.save(configurationGovernmentOrder);
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
	public boolean saveLRM(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		return true;
	}*/

	/*@Override
	public boolean saveLGDM(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		return false;
	}*/

	/*@Override
	public boolean saveConstituency(
			ConfigurationGovernmentOrder configurationGovernmentOrder)
			throws Exception {
		return false;
	}
*/
	
	
	//Maneesh
		@SuppressWarnings("unchecked")
		@Override
		public List<Operations> getOperationDetailDAO(
				String HQL) throws Exception {
			Session session = null;

			List<Operations> OperationDetailList=null;
			Query criteria = null;
			try {
				session = sessionFactory.openSession();
				criteria = session.createQuery(HQL);
				OperationDetailList = criteria.list();

			} catch (Exception e) {
				log.debug("Exception" + e);
			} finally {
				if (session != null && session.isOpen()){
					session.close();
				}	
			}
			return OperationDetailList;

		}
		
		//Maneesh
		
	//Maneesh
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationGovernmentOrder> getConfigurationGovernmentOrderDetails(
			String HQL) throws Exception {
		Session session = null;

		List<ConfigurationGovernmentOrder> getConfigurationGovernmentOrderDetails = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(HQL);
			getConfigurationGovernmentOrderDetails = criteria.list();

			
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return getConfigurationGovernmentOrderDetails;

	}
	
	//Maneesh
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationGovernmentOrder> getConfigurationGovernmentOrderDetailsPRI(
			String HQL) throws Exception {
		
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(HQL);
			getConfigurationGovernmentOrderDetails = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return getConfigurationGovernmentOrderDetails;

	}

	
	/*@Override
	public List<ConfigurationGovernmentOrder> getConfigurationGovernmentOrderDetailsTrade(
			String HQL) throws Exception {
		Session session = null;

		List<ConfigurationGovernmentOrder> getConfigurationGovernmentOrderDetails = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(HQL);
			getConfigurationGovernmentOrderDetails = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return getConfigurationGovernmentOrderDetails;

	}
//Maneesh
*/
	
	
	
	@Override
	public boolean UpdateLRM(
			ConfigurationGovernmentOrder configurationGovernmentOrderBean)
			throws Exception {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			session.update(configurationGovernmentOrderBean);
			tx.commit();

		} catch (Exception e) {
		
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return false;
		}

		finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
		return true;
	}

}
