package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.VillagePartsBySurveyno;
import in.nic.pes.lgd.dao.VillagePartsBySurveynoDAO;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class VillagePartsBySurveynoDAOImpl implements VillagePartsBySurveynoDAO{
	
	private static final Logger log = Logger.getLogger(VillagePartsBySurveynoDAOImpl.class);

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public boolean save(VillagePartsBySurveyno villagePartsBySurveyno) throws Exception{
		 
		Session session = null ;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
		 
			tx=session.beginTransaction();
			session.saveOrUpdate(villagePartsBySurveyno);
			tx.commit();
		}
		catch (Exception e) {
		 
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return false;
		}
		
		finally
		{
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	@Override
	public int getMaxRecords(String query) throws Exception{
		int MaxCode = 0;
		Session session=null;
		try {
			session=sessionFactory.getCurrentSession();
			MaxCode = Integer
					.parseInt(session.createSQLQuery(query)
							.list().get(0).toString());
		} catch (Exception e) {
			 
			log.debug("Exception" + e);
		}
		finally
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		} 
		return MaxCode;
	}

}
