package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.LandregionReplaces;
import in.nic.pes.lgd.dao.LandRegionReplacesDAO;

import org.apache.log4j.Logger;
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
public class LandRegionReplacesDAOImp implements LandRegionReplacesDAO{
	
	private static final Logger log = Logger.getLogger(LandRegionReplacesDAOImp.class);
	
	@Autowired
    private SessionFactory sessionFactory;
	@Override
	public boolean save(LandregionReplaces landregionReplacesbean)throws Exception {
	 
		Session session = null ;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			//System.out.println("Connected :: "+session.isConnected());
			tx=session.beginTransaction();
			session.save(landregionReplacesbean);
			tx.commit();
		}
		catch (Exception e) {
			throw e;
		}
		
		finally
		{
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	public int save(LandregionReplaces landregionReplacesbean, Session session)throws Exception {
		int lrReplaces=0;
		try
		{
			session.saveOrUpdate(landregionReplacesbean);
			session.flush();
			session.refresh(landregionReplacesbean);
			lrReplaces=Integer.parseInt(session.createSQLQuery("select max(lr_replaces) from landregion_replaces").list().get(0).toString());
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		session.saveOrUpdate(landregionReplacesbean);
		session.flush();
		session.refresh(landregionReplacesbean);
		lrReplaces=Integer.parseInt(session.createSQLQuery("select COALESCE(max(lr_replaces),1) from landregion_replaces").list().get(0).toString());
		return lrReplaces;	
	}
	
	@Override
	public int getMaxRecords(String query)throws Exception {
		int MaxCode = 0;
		try {
			MaxCode = Integer
					.parseInt(sessionFactory
							.getCurrentSession()
							.createSQLQuery(query)
							.list().get(0).toString());
		} catch (Exception e) {
			throw e;
		}
		//System.out.println("Max Code--" + MaxCode);
		return MaxCode+1;
	}

}
