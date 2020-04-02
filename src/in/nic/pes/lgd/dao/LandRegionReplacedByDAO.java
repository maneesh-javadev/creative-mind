package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.LandregionReplacedby;

import org.hibernate.Session;

public interface LandRegionReplacedByDAO {
	public boolean save(LandregionReplacedby landregionReplacedbyBean)throws Exception;
	public int save(LandregionReplacedby landregionReplacedbyBean, Session session)throws Exception;
	public int getMaxRecords(String query)throws Exception;

}
