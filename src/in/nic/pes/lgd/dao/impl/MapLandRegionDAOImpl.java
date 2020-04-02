package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.dao.MapLandRegionDAO;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @Author: Ram
*/

public class MapLandRegionDAOImpl implements MapLandRegionDAO {
	
	private static final Logger log = Logger.getLogger(MapLandRegionDAOImpl.class);
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public int configMap(MapLandRegion map, Session session) throws Exception{
		int mapCode=0;
		try{
				session.save(map);
				session.flush();
				session.refresh(map);
				mapCode=map.getMapLandregionCode();
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		return mapCode;
	}

	@Override
	public boolean save(MapLandRegion mapLandRegion) throws Exception {
		Session session = null ;
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.save(mapLandRegion);
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
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
		return true;
	}

	@Override
	public boolean saveMap(MapLandRegion mapLandRegion, Session session1) throws Exception{
		// TODO Auto-generated method stub
		try {
			session1.save(mapLandRegion);
			session1.flush();
			
		} catch (Exception e) {
			log.debug("Exception" + e);
			 return false;
		}
		return true;
	}

	

}