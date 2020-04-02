package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.bean.EntityTransactionsNewPES;
import in.nic.pes.lgd.common.persistencecontext.HibernateSessionEmail;
import in.nic.pes.lgd.dao.PESTransactionDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;





public class PESTransactionDAOImpl implements PESTransactionDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntityTransactionsNew> getLGDTransactions(Integer statecode) {
		// TODO Auto-generated method stub
		Session session = null;
		List<EntityTransactionsNew> listTrns = new ArrayList<EntityTransactionsNew>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = " select trans from EntityTransactionsNew trans where trans.stateCode = :statecode and trans.statusFlg in ('P','S')" +
								  " and trans.description is not null order by trans.statusFlg desc"; 
			listTrns = session.createQuery(queryBuilder).setParameter("statecode", statecode).list();
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return listTrns;
	}

	@Override
	public EntityTransactionsNew getEntityTransactionById(Integer tid) {
		// TODO Auto-generated method stub
		Session session = null;
		EntityTransactionsNew entity = null;
		try {
			session = sessionFactory.openSession();
			entity =  (EntityTransactionsNew) session.get(EntityTransactionsNew.class, tid);
		}catch (Exception e) {
			// TODO: handle exception
			entity = null;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return entity;
	}

	@Override
	public boolean update(EntityTransactionsNew transaction, boolean isRequiredForPES) {
		// TODO Auto-generated method stub
		boolean status = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(transaction);
			if(isRequiredForPES) {
				/* Invocation of this mathod removed because data needs to be populated  via 
				 * web-services.
				 * @since 18 Nov 2015 ( New Impact has been  released )
				 */
				status = true; //updateTrnasactionsPES(transaction);
			} else {
				/*
				 * The{@isCompletedTransactionPES} called is no more in use as this method is being accessed from scheduler.
				 */
				status = isCompletedTransactionPES(transaction.gettId());
			}
			
			if(status){
				tx.commit();
			}
		}catch(Exception e){
			status = false;
		}finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	
	@SuppressWarnings("unused")
	private boolean updateTrnasactionsPES(EntityTransactionsNew transaction){
		try {
			EntityTransactionsNewPES entityPES = new EntityTransactionsNewPES(); 
			entityPES.settId(transaction.gettId());
			entityPES.setOperationCode(transaction.getOperationCode());
			entityPES.setLevel(transaction.getLevel());
			entityPES.setEffectiveDate(transaction.getEffectiveDate());
			entityPES.setIsactive(transaction.isIsactive());
			entityPES.setHasError(transaction.isHasError());
			entityPES.setDescription(transaction.getDescription());
			entityPES.setStateCode(transaction.getStateCode());
			entityPES.setStatusFlg(transaction.getStatusFlg());
			entityPES.setScheduledDate(transaction.getScheduledDate());
			entityPES.setTaskCreatedOn(transaction.getTaskCreatedOn());
			entityPES.setTaskCreatedBy(transaction.getTaskCreatedBy());
			
			Session session = HibernateSessionEmail.email();
			Transaction tx = session.beginTransaction();
			session.save(entityPES);
			tx.commit();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	private boolean isCompletedTransactionPES(Integer transactionId){
		// TODO Auto-generated method stub
		boolean status = false;
		Session session = null;
		try {
			session = HibernateSessionEmail.email();
			Transaction tx = session.beginTransaction();
			EntityTransactionsNewPES pesEntity = (EntityTransactionsNewPES) session.get(EntityTransactionsNewPES.class, transactionId);
			pesEntity.setStatusFlg("C");
			session.update(pesEntity);
			tx.commit();
			status = true;
		}catch(Exception e){
			status = false;
		}
		return status;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityTransactionsNew> getScheduledTransactions() {
		// TODO Auto-generated method stub
		Session session = null;
		List<EntityTransactionsNew> listTransactions = new ArrayList<EntityTransactionsNew>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = " select trans from EntityTransactionsNew trans where trans.statusFlg in ('S')" +
								  " and trans.description is not null"; 
			listTransactions = session.createQuery(queryBuilder).list();
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return listTransactions;
	}

	@Override
	public boolean isPerformedActionforPES(Integer transactionId) {
		// TODO Auto-generated method stub
		boolean status = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "select push_lgd_impact_by_transaction_id from push_LGD_Impact_by_transaction_id(:transactionId)";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("transactionId", transactionId);
			status = (Boolean) query.uniqueResult();
		}catch (Exception e) {
			// TODO: handle exception
			status = false;
		} 
		finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}

}
