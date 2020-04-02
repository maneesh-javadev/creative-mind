package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.ConfigurationGovernmentOrder;
import in.nic.pes.lgd.bean.Operations;

import java.util.List;

import org.hibernate.Session;

public interface ConfigGovtOrderDAO {
	public boolean save(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	
	//public boolean saveAdmin(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public boolean saveLRM(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	public boolean UpdateLRM(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public boolean saveLGDM(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	
// TODO Remove unused code found by UCDetector
// 	public boolean saveConstituency(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	public List<ConfigurationGovernmentOrder> getConfigurationGovernmentOrderDetails(String HQL)throws Exception;
	
	//Maneesh
	public List<ConfigurationGovernmentOrder> getConfigurationGovernmentOrderDetailsPRI(String HQL)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public List<ConfigurationGovernmentOrder> getConfigurationGovernmentOrderDetailsTrade(String HQL)throws Exception;
	
	public List<Operations> getOperationDetailDAO(String HQL) throws Exception; 
	//Maneesh
	public boolean setIsActiveDAO(String SQL,Session hsession)throws Exception; 
	
	public boolean saveAdminBlock(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	public boolean saveAdminTrade(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	public boolean saveAdminPRI(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	public boolean saveAdminUrban(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	public boolean saveAdminLandRegion(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	public boolean saveAdminConstituency(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	public boolean saveAdminCenter(ConfigurationGovernmentOrder configurationGovernmentOrder)throws Exception;
	public int getMaxRecords(String query)throws Exception;

}
