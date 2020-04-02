package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.LandregionReplaces;

import org.hibernate.Session;

public interface LandRegionReplacesDAO {
	public boolean save(LandregionReplaces landregionReplacesbean)throws Exception;
	public int save(LandregionReplaces landregionReplacesbean, Session Session)throws Exception;
	public int getMaxRecords(String query)throws Exception;

}
