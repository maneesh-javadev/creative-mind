package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.LandRegion;
import in.nic.pes.lgd.bean.LbCoveredLandregion;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.dao.LbCoveredLandregionDAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

public class LbCoveredLandregionDAOImpl implements LbCoveredLandregionDAO{
	
	private static final Logger log = Logger.getLogger(LbCoveredLandregionDAOImpl.class);

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public boolean save(LbCoveredLandregion lbCoveredLandregion)throws Exception {
 
		Session session = null ;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			//System.out.println("Connected :: "+session.isConnected());
			tx=session.beginTransaction();
			session.save(lbCoveredLandregion);
			tx.commit();
		}
		catch (Exception e) {
			throw e;
		}
		
		finally
		{
			if(session !=null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LandRegion> getcoveredLandregionByLocalbodyCode(int localbodyCode)throws Exception 
	{
		Query query= null;
		Session session =null;
		List<LandRegion> getcoveredLandregionByLocalbodyCode = null;
		try
		{
			session = sessionFactory.openSession();
			query=session.getNamedQuery("getCoveredRegion");
			query.setParameter("localbodyCode", localbodyCode);
			getcoveredLandregionByLocalbodyCode = query.list();
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}finally{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
	    return  getcoveredLandregionByLocalbodyCode;

	}

	@Override
	public String getLandregionName(String sqlquery)throws Exception 
	{
		String retval=null;
		Session session = null;
		Query query = null ;
		//List list = null;
		try
		{
			session = sessionFactory.openSession();
			query = session.createQuery(sqlquery) ;
			retval = (String) query.uniqueResult();
			/*list = query.list();
			if(list !=null && list.get(0) != null){
				retval = list.get(0).toString();
			}*/	
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}finally{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return retval;
	}
	
	

//adding a method here 	getULBList
	@SuppressWarnings("unchecked")
	public List<Localbody> getULBList(int stateCode) {
		Session session = null;
		List<Localbody> ulbList = null;
		
		try {
			session = sessionFactory.openSession();
			SQLQuery query =session.createSQLQuery("select local_body_code as localBodyCode, local_body_name_english as localBodyNameEnglish from get_ulb_list_by_state_code_fn(:stateCode)");
			query.setParameter("stateCode", stateCode);
			query.addScalar("localBodyCode").addScalar("localBodyNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Localbody.class));
			ulbList = query.list();
		}catch(Exception e)
		{
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return ulbList;
	}
}
