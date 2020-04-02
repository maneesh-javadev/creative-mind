package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.LandregionReplacedby;
import in.nic.pes.lgd.dao.LandRegionReplacedByDAO;

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
public class LandRegionReplacedByDAOImpl implements LandRegionReplacedByDAO{

	@Autowired
    private SessionFactory sessionFactory;
	@Override
	public boolean save(LandregionReplacedby LandregionReplacedbyBean)throws Exception {
 		Session session = null ;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			//System.out.println("Connected :: "+session.isConnected());
			tx=session.beginTransaction();
			session.saveOrUpdate(LandregionReplacedbyBean);
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
	
	@Override
	public int save(LandregionReplacedby landregionReplacedbyBean, Session session) throws Exception{
		int lrReplacedBy=0;
		try
		{
			session.saveOrUpdate(landregionReplacedbyBean);
			session.flush();
			session.refresh(landregionReplacedbyBean);
			lrReplacedBy=Integer.parseInt(session.createSQLQuery("select max(lr_replacedby) from landregion_replacedby").list().get(0).toString());
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			throw e;
		}
		session.saveOrUpdate(landregionReplacedbyBean);
		session.flush();
		session.refresh(landregionReplacedbyBean);
		lrReplacedBy=Integer.parseInt(session.createSQLQuery("select COALESCE(max(lr_replacedby),1) from landregion_replacedby").list().get(0).toString());
		
		return lrReplacedBy;
	}
	
	@Override
	public int getMaxRecords(String query) throws Exception{
		
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
