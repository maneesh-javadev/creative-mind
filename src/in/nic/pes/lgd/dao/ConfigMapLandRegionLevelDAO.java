package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.ConfigurationMapLandregion;
import in.nic.pes.lgd.bean.ConfigurationMapLandregionLevel;

import java.util.List;

import org.hibernate.Session;

public interface ConfigMapLandRegionLevelDAO {
	
	
	
	/*public int getMaxRecords(String query)throws Exception; */
	
	public int getMaxRecordsForLevel()throws Exception;
	
	//new
			public List<ConfigurationMapLandregionLevel> getConfigurationMapLandLevelDetails(String HQL)throws Exception;
			// TODO Remove unused code found by UCDetector
// 			public boolean update(ConfigurationMapLandregionLevel configurationMapLandregionLevel)throws Exception;


//			boolean updateIsActiveLevel(ConfigurationMapLandregionLevel configurationMapLandregionLevel, Session session);
			public boolean savewithsession(ConfigurationMapLandregionLevel  configurationMapLandregionLevel,Session session)throws Exception;


			boolean updateIsActiveLevel(int id, Session session)throws Exception;


			boolean save(
					ConfigurationMapLandregionLevel configurationMapLandregionLevel)throws Exception;

			public List<ConfigurationMapLandregion> getMapConfigofState (
					int stateCode)throws Exception;
}
