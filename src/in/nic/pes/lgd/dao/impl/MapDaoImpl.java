package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.ConfigureMap;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.dao.MapDao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MapDaoImpl implements MapDao {
	
	private static final Logger log = Logger.getLogger(MapDaoImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public boolean configmap(ConfigureMap map) throws Exception {
	 
		Session session = null ;
		Transaction tx = null;
		try{
 
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.persist(map);
			tx.commit();
		}
		catch (Exception e) {
			if(tx != null){
				tx.rollback();
			}	
			return false;
		}
		
		finally
		{
			if(session != null && session.isOpen())
			{	
				session.close();	
			}
		}	
		return true;
		
	}

	@SuppressWarnings("unchecked")
	public List<MapAttachment> getUploadFileConfigurationDetails(Session session) throws Exception{
		Query query = null;
		
		List<MapAttachment> mapDetailsList = null;
		try {
			query = session
					.createQuery("from MapAttachment");
			mapDetailsList = query.list();
		} catch (Exception ex) {
			log.debug("Exception" + ex);
		}
		return mapDetailsList;
	}
	
/*	@Override
	public boolean save(MapLandRegion mapLandRegion) {
		System.out.println("------------I m in dao impl method----------------");
		Session session = null ;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			System.out.println("Connected :: "+session.isConnected());
			tx=session.beginTransaction();
			session.save(mapLandRegion);
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("------------I m in dao catch block----------------");
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		
		finally
		{
			session.close();
		}
		return true;
	}*/

	
	/**
	 * The {@code mapConfigurations} in DAO Implementation Layer, Map Configuration related to GIS Server and 
	 * Other server configured in database
	 * @param level
	 * @param localGovBodyType
	 * @param vpFlag
	 * @param vpState
	 * @return
	 */
	@Override
	public List<Object[]> mapConfigurations(Integer inputLevel, String localGovBodyType, String vpFlag, Integer vpState) throws Exception{
		// TODO Auto-generated method stub
		Session session = null ;
		try{
 			session = sessionFactory.openSession();
 			StringBuilder builder = new StringBuilder(" select " +
 													  " model.level, " +
 													  " model.name, " +
 													  " model.baseUrl, " +
 													  " model.parentBaseUrl, " +
 													  " model.paramName, " +
 													  " model.attributeName, " +
 													  " model.token, " +
 													  " model.attributeCodeName " +
 													  " from MapConfiguration model where 1=1 ");
 			if(inputLevel != null){
 				builder.append(" and model.level = :inputLevel");
 			}	
 			
 			Query query = session.createQuery(builder.toString());
 			query.setCacheable(true);
 			query.setCacheMode(CacheMode.GET);
 			query.setCacheRegion("mapconfiguration");
 			if(inputLevel != null){
 				query.setParameter("inputLevel", inputLevel);
 			}	
 			
 			@SuppressWarnings("unchecked")
			List<Object[]> mapconfigs = query.list();
			return mapconfigs;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			if(session != null){session.flush();session.close();};
		}	
	}
}
