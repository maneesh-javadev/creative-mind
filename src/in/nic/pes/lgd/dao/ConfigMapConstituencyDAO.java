package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.ConfigurationMapConstituency;

import java.util.List;

import org.hibernate.Session;

public interface ConfigMapConstituencyDAO {
	public boolean save(ConfigurationMapConstituency configurationMapConstituency)throws Exception;
	public int getMaxRecords(String query)throws Exception;
	//get data
	public List<ConfigurationMapConstituency> getConfigureMapConstituencyDetail(String Hql)throws Exception;
	//public List<ConfigurationMapConstituency> getConstituencyDetails(int code)throws Exception;
	//new
	public List<ConfigurationMapConstituency> getConfigurationMapConstituencyDetails(String HQL)throws Exception;
	public boolean update(ConfigurationMapConstituency configurationMapConstituency)throws Exception; // NO_UCD (unused code)
	public boolean savewithsession(ConfigurationMapConstituency  configurationMapConstituency,Session session)throws Exception;
	boolean updateIsActiveLevel(int id, Session session)throws Exception;
}
