package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.ConfigurationMapLandregion;
import in.nic.pes.lgd.bean.ViewConfigMapLandRegion;

import java.util.List;

import org.hibernate.Session;

public interface ConfigMapLandRegionDAO {
	
	
	public int getMaxRecords(String query)throws Exception; // NO_UCD (unused code)
	
	public int getMaxRecordsForLevel()throws Exception;
	//new
		public List<ConfigurationMapLandregion> getConfigurationMapLandDetails(String HQL)throws Exception;
		public boolean update(ConfigurationMapLandregion configurationMapLandregion)throws Exception; // NO_UCD (unused code)
		public List<ViewConfigMapLandRegion> GovOrderDetail(int stateCode)throws Exception;
		public int getPrimaryKeyfromLandRegionConfig(int stateCode)throws Exception;
		public boolean updateisActive(ConfigurationMapLandregion configurationMapLandregion,Session session)throws Exception;		
		int saveWithSession(
				ConfigurationMapLandregion ConfigurationMapLandregion,
				Session session)throws Exception;

		List<ConfigurationMapLandregion> getConfigurationDetails(int stateCode)throws Exception;

		int save(ConfigurationMapLandregion ConfigurationMapLandregion)throws Exception;

		int saveOrUpdate(ConfigurationMapLandregion ConfigurationMapLandregion)throws Exception;
		//public Map<String,String> checkMapConfigurationforConstituency(int stateCode,char constituencyType) throws Exception;
}
