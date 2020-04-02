package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.ConfigurationMapLocalbody;
import in.nic.pes.lgd.bean.GetMapConfigLocalBody;

import java.util.List;

public interface ConfigMapLocalBodyDAO {
	public boolean save(ConfigurationMapLocalbody configurationMapLocalbody )throws Exception;
// TODO Remove unused code found by UCDetector
// 	public int getMaxRecords(String query)throws Exception;
	public List<ConfigurationMapLocalbody> getConfigureMapLocalBody(String mapConfig)throws Exception;
// TODO Remove unused code found by UCDetector
// 	boolean savewithsession(ConfigurationMapLocalbody configMapLocalBody,Session session)throws Exception;
	public List<GetMapConfigLocalBody> configMapLocalBodyDetail(int stateCode,char category)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public boolean update(ConfigurationMapLocalbody configurationMapLocalbody )throws Exception;
	public boolean	ConfigMapLgdm(String tierSetupCode,String mapURL ,int userId)throws Exception;
}
