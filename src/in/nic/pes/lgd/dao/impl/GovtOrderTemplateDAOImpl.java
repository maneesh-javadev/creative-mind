package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.bean.OperationsVariables;
import in.nic.pes.lgd.dao.GovtOrderTemplateDAO;

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
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GovtOrderTemplateDAOImpl implements GovtOrderTemplateDAO {
	
	private static final Logger log = Logger.getLogger(GovtOrderTemplateDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int save(GovernmentOrderTemplate governmentOrderTemplateBean)
			throws Exception {
		Session session = null;
		Transaction tx = null;
		int code = 0;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			code = (Integer) session.save(governmentOrderTemplateBean);
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return code;
		}

		finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return code;

	}

/*	@Override
	public List<Operations> getOpeartionsList(String hql) throws Exception {
		List<Operations> oprList = null;
		Session session = null;
		try {
			oprList = new ArrayList<Operations>();
			session = sessionFactory.openSession();
			oprList = session.createQuery(hql).list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			return null;
		}

		finally {
			if (session != null && session.isOpen())
			session.close();
		}
		return oprList;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Operations> getOperationsList(char category) throws Exception 
	{
		List<Operations> oprList = null;
		Session session = null;
		Query criteria = null;
		try 
		{
			oprList = new ArrayList<Operations>();
			session = sessionFactory.openSession();
			criteria=session.createQuery("from Operations where isactive=true and category =:categ order by operationName");
			criteria.setParameter("categ", category, Hibernate.CHARACTER);
			oprList = criteria.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			return null;
		}
		finally 
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return oprList;
	}

	@Override
	public String getOperationNamebyOperationCode(int oprCode) throws Exception {
		Query criteria = null;
		Session session = null;
		String temp = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from Operations where operationCode=:oprCode and isactive=true");
			criteria.setParameter("oprCode", oprCode, Hibernate.INTEGER);
			temp = ((Operations) criteria.list().get(0)).getOperationName();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return temp;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GovernmentOrderTemplate> getTemplateListByOperationCode(
			int oprCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<GovernmentOrderTemplate> governmentOrderTemplateList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from GovernmentOrderTemplate got where got.operations.operationCode=:oprCode and got.isactive=true");
			criteria.setParameter("oprCode", oprCode, Hibernate.INTEGER);
			governmentOrderTemplateList = criteria.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return governmentOrderTemplateList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GovernmentOrderTemplate> getTemplateDetailsByTemplateCode(
			int templCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<GovernmentOrderTemplate> governmentOrderTemplateList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from GovernmentOrderTemplate got where templateCode=:templCode and got.isactive=true");
			criteria.setParameter("templCode", templCode, Hibernate.INTEGER);
			governmentOrderTemplateList = criteria.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return governmentOrderTemplateList;

	}

	@Override
	public boolean updateIsactiveTemplate(int templCode) throws Exception {

		boolean success = false;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();

			tx = session.beginTransaction();
			GovernmentOrderTemplate governmentOrderTemplateBean = new GovernmentOrderTemplate();
			governmentOrderTemplateBean = (GovernmentOrderTemplate) session
					.get(GovernmentOrderTemplate.class, templCode);
			governmentOrderTemplateBean.setIsactive(false);
			session.update(governmentOrderTemplateBean);
			session.flush();
			tx.commit();
			success = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			success = false;
		}

		finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return success;

	}

	@Override
	public boolean update(GovernmentOrderTemplate governmentOrderTemplateBean)
			throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(governmentOrderTemplateBean);
			tx.commit();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<OperationsVariables> getVariableListOpr(int operationCode) throws Exception 
	{
		Query criteria = null;
		Session session = null;
		List<OperationsVariables> operationsVariablesList = null;
		try
		{
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OperationsVariables where operationCode=:operationCode and isactive=true order by variableValue");
			criteria.setParameter("operationCode", operationCode,Hibernate.INTEGER);
			operationsVariablesList = criteria.list();
		} 
		catch (HibernateException e) 
		{
			log.debug("Exception" + e);
		}
		finally
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return operationsVariablesList;
	}
}
