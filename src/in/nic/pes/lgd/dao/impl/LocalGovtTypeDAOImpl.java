package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.CheckLocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTypeHistory;
import in.nic.pes.lgd.dao.LocalGovtTypeDAO;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;

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

@Transactional
@Repository
@Service
public class LocalGovtTypeDAOImpl implements LocalGovtTypeDAO {
	private static final Logger log = Logger.getLogger(LocalGovtTypeDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public int saveLocalGovtType(LocalBodyType localbodyType, Session session)
			throws Exception {

		int code = 0;
		try {

			code = (Integer) session.save(localbodyType);
			session.flush();
			if (session.contains(localbodyType)) {
				session.evict(localbodyType);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return code;
	}

	 
	 public void saveLocalGovtTypeHistory(
			LocalBodyTypeHistory localBodyTypeHistory,Session session) throws Exception {
		 
		try {		 
		 
			session.save(localBodyTypeHistory);
			 session.flush();
			 if(session.contains(localBodyTypeHistory)){
				 session.evict(localBodyTypeHistory);
			 } 

		} catch (Exception e) {
			log.debug("Exception" + e);
			 
		}

	} 

	@Override
	public boolean updateLocalGovtType(LocalBodyType localBodyType,Session session)
			throws Exception { 
		 
		try {
		 
			session.update(localBodyType);
			session.flush();
		 if(session.contains(localBodyType)){
			 session.evict(localBodyType);
		 }	 

		} catch (Exception e) {
			log.debug("Exception" + e);			 
			return false;
		}
 
		return true;
	}

	/*@Override
	public void SetGovermentOrderEntity(String entityCode, char entityType)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<SetGovermentOrderEntity> govtOrderEntity =null;
		
		Session session = null;
		Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			query = sessionFactory
					.getCurrentSession()
					.getNamedQuery("GovOrderEntityWiseQuery")
					.setParameter("entityCode", entityCode, Hibernate.STRING)
					.setParameter("entityType", entityType, Hibernate.CHARACTER);
			govtOrderEntity=query.list();
			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(txn != null){
				txn.rollback();
			}	
		}

		finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}

	}*/

	@Override
	public boolean updateIsActive(LocalBodyType localBodyType) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();

			localBodyType.setIsactive(false);

			session.update(localBodyType);
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
	public List<LocalBodyTiersSetup> lgsetup() throws Exception {
		Query criteria = null;
		List<LocalBodyTiersSetup> lgsetup = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from LocalBodyTiersSetup where isactive=true");
			lgsetup = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return lgsetup;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalGovtTypeDataForm> getLocalBodyTypeDetailall(
			int localBodyTypeCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<LocalGovtTypeDataForm> localGovtTypeDataFormList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from LocalBodyType where isactive=true and localBodyTypeCode=:localBodyTypeCode");
			criteria.setParameter("localBodyTypeCode", localBodyTypeCode,
					Hibernate.INTEGER);
			localGovtTypeDataFormList = criteria.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return localGovtTypeDataFormList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyType> getLocalBodyTypeDetails(
			int localBodyTypeCode) throws Exception {
		List<LocalBodyType> localgovt = null;
		Query query = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							"SELECT distinct local_body_type.* FROM "
									+ "public.local_body_type,public.local_body_tiers_setup WHERE local_body_type.local_body_type_code = "
									+ "local_body_tiers_setup.local_body_type_code and local_body_tiers_setup.local_body_type_code ="
									+ "" + localBodyTypeCode).addEntity(
							"local_body_type", LocalBodyType.class);
			localgovt = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return localgovt;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyType> getlocalBodyTypelist() throws Exception {
		Query criteria = null;
		Session session = null;
		List<LocalBodyType> getlocalBodyTypelist = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from LocalBodyType where category='R' and  ispartixgovt=true and isactive=true");
			getlocalBodyTypelist = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return getlocalBodyTypelist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkgovtTypeDependency(String hql) throws Exception {
		List<LocalBodyType> lstLocalBodyType = null;
		Session session = null;
		try {
			lstLocalBodyType = new ArrayList<LocalBodyType>();
			session = sessionFactory.openSession();
			Query criteria = session.createQuery(hql);
			lstLocalBodyType = criteria.list();
			if (lstLocalBodyType.isEmpty()) {
				return false;
			} else if (!lstLocalBodyType.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CheckLocalBodyType> checkLocalBodyTypeDAO(Integer localBodyTypeCode) throws Exception
	{
		List<CheckLocalBodyType> checkLocalBodyType=null;
		checkLocalBodyType=new ArrayList<CheckLocalBodyType>();
		Session session = null;
		Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			query = sessionFactory
					.getCurrentSession()
					.getNamedQuery("checkLocalBodyType")
					.setParameter("localBodyTypeCode", localBodyTypeCode, Hibernate.INTEGER);
			checkLocalBodyType=query.list();
			
			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(txn != null){
				txn.rollback();
			}	
		}

		finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		
		return checkLocalBodyType;
	}

}
