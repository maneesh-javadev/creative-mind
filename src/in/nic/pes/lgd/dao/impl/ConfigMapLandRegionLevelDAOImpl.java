package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.ConfigurationMapLandregion;
import in.nic.pes.lgd.bean.ConfigurationMapLandregionLevel;
import in.nic.pes.lgd.dao.ConfigMapLandRegionLevelDAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Service
public class ConfigMapLandRegionLevelDAOImpl implements ConfigMapLandRegionLevelDAO { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(ConfigMapLandRegionLevelDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean save(ConfigurationMapLandregionLevel configurationMapLandregionLevel)throws Exception {
		//int max=0;
		Session session = null;
		Transaction tx = null;
		
			try {
				session = sessionFactory.openSession();
				tx=session.beginTransaction();
				session.save(configurationMapLandregionLevel);
				tx.commit();
			}  catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return true;
	}
	@Override
	public boolean updateIsActiveLevel(int id,Session session)throws Exception{
//		sessionFactory.openSession().createSQLQuery("update configuration_map_landregion_level set isactive='" + isActive + "' where configuration_map_landregion_code=" + configureMapLandRegionCode).executeUpdate();
		ConfigurationMapLandregionLevel configurationMapLandregionLevelinactive =null;
		try {
			configurationMapLandregionLevelinactive = (ConfigurationMapLandregionLevel)session.load(ConfigurationMapLandregionLevel.class, id);
			configurationMapLandregionLevelinactive.setIsactive(false);
			session.update(configurationMapLandregionLevelinactive);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}
	

	/*@Override
	public int getMaxRecords(String query)throws Exception {
		// TODO Auto-generated method stub

		int MaxCode = 0;
		try {
			MaxCode = Integer
					.parseInt(sessionFactory
							.getCurrentSession()
							.createSQLQuery(query)
							.list().get(0).toString());
			if(MaxCode==0)
			{
				MaxCode=1;
			}
			else
			{
				MaxCode=MaxCode+1;
			}
		} catch (Exception e) {
			//System.out.println("MaxCode----------------" + MaxCode);
			log.debug("Exception" + e);
		}
		//System.out.println("Max Code--" + MaxCode);
		return MaxCode;
	}*/
	
	
	@Override
	public int getMaxRecordsForLevel()throws Exception {
		// TODO Auto-generated method stub
		int MaxCode = 0;
		try {
			MaxCode = Integer
					.parseInt(sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"select COALESCE(max(configuration_map_landregion_code),1) from configuration_map_landregion")
							.list().get(0).toString());
		} catch (Exception e) {
			//System.out.println("MaxCode----------------" + MaxCode);
			log.debug("Exception" + e);
		}
		//System.out.println("Max Code--" + MaxCode);
		return MaxCode;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationMapLandregionLevel> getConfigurationMapLandLevelDetails(
			String HQL)throws Exception {
        Session session = null ; 	  
		Query criteria=null;
		List<ConfigurationMapLandregionLevel> getConfigurationMapLandLevelDetails = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(HQL);
			getConfigurationMapLandLevelDetails = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}finally{
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return getConfigurationMapLandLevelDetails;
	}



	/*@Override
	public boolean update(
			ConfigurationMapLandregionLevel configurationMapLandregionLevel)throws Exception {
		 
		Session session = null ;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();			
			tx=session.beginTransaction();
			session.update(configurationMapLandregionLevel);
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
	}*/
	@Override
	public boolean savewithsession(
			ConfigurationMapLandregionLevel configurationMapLandregionLevel,
			Session session)throws Exception {
		// TODO savewithsession
		//int code=0;
				try {
				//code=(Integer)
					session.save(configurationMapLandregionLevel);
				} 
				catch (Exception e) {
				    log.debug("Exception" + e);
		} 
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationMapLandregion> getMapConfigofState(int stateCode)throws Exception {
		// TODO Auto-generated method stub
		
		Query criteria = null;
		Session session=null;
		List<ConfigurationMapLandregion> configurationMapLandregionList=null;
		try {
			
			session=sessionFactory.openSession();
			criteria = session.createQuery("from ConfigurationMapLandregion where slc=:stateCode and isactive=true");
			criteria.setParameter("stateCode",stateCode,Hibernate.INTEGER);
			configurationMapLandregionList=criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		finally
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return configurationMapLandregionList;
	}

}
