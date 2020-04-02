package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.MapLandRegion;

import org.hibernate.Session;

/**
*
* @Author: Ram
*/

public interface MapLandRegionDAO {
	public int configMap(MapLandRegion map, Session ses)throws Exception;
	public boolean save(MapLandRegion mapLandRegion)throws Exception;
	public boolean saveMap(MapLandRegion mapLandRegion,Session session1)throws Exception;
}
