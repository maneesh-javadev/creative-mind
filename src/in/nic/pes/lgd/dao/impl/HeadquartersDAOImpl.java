package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.dao.HeadquartersDAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HeadquartersDAOImpl implements HeadquartersDAO{
	
	private static final Logger log = Logger.getLogger(HeadquartersDAOImpl.class);

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public boolean save(Headquarters headquarters, Session session)throws Exception {
		try {
			session.save(headquarters);
			//session.flush();
			//session.refresh(headquarters);
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	
	@Override
	public boolean saveOrUpdate(Headquarters headquarters, Session session)throws Exception {
		try {
			session.saveOrUpdate(headquarters);
			session.flush();
			//session.refresh(headquarters);
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
	}
	
	//------------Maneesh----------------
	@SuppressWarnings("unchecked")
	@Override
	public List<Headquarters> getHeadquartersDetailsDAO(String HQL,Session session)throws Exception{
		 List<Headquarters> headquartersDetails = new ArrayList<Headquarters>();
		 Query criteria = null;
		 try {
			 session = sessionFactory.openSession();
				criteria = session.createQuery(HQL);
				headquartersDetails = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			// throw e;
		}
		 finally
		 {
			 if(session != null && session.isOpen()){				 
				 session.close();
			 }	 
			 
		 }
		 return  headquartersDetails;
	}
	
	
	@Override
	public int getMaxHeadquartersCode()throws Exception {
		int MaxCode=0;
		try {
			MaxCode= Integer.parseInt(sessionFactory
					.openSession()
					.createSQLQuery(
							"select COALESCE(max(headquarter_code),1) from headquarters")
							.list().get(0).toString());
		} catch (Exception e) {
			//System.out.println("MaxCode---CatchBlock-------------" + MaxCode);
			log.debug("Exception" + e);
		}
		//System.out.println("Max Code--" + MaxCode);
		return MaxCode+1;
	}

	/*@Override
	public int getMaxHeadquartersVersion(int headquartersCode)throws Exception {
		Query criteria = null;
		Session session = null ;
		
		int MaxVersionCode=0;
		try {
			session=sessionFactory.openSession();
			criteria = session.createSQLQuery("select COALESCE(max(headquarter_version),1) from headquarters where headquarter_code=:headquartersCode");
			criteria.setParameter("headquartersCode",headquartersCode,Hibernate.INTEGER);
			if(criteria!=null)
			{
				MaxVersionCode=Integer.parseInt(criteria.list().get(0).toString());
			}
			MaxVersionCode=Integer.parseInt(session.
					createSQLQuery("" +
							"select max(headquarter_version) from headquarters where headquarter_code=" + headquartersCode )
							.list().get(0).toString());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		finally{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return MaxVersionCode;
	}
*/
	/*@Override
	public boolean update(Headquarters headquarters, Session session)throws Exception
	{
		// TODO Auto-generated method stub
		try {
			session.update(headquarters);
			//session.flush();
			if(session.contains(headquarters))
				session.evict(headquarters);
		
	} catch (Exception e) {
		log.debug("Exception" + e);
	}
		return true;
	}*/

}
