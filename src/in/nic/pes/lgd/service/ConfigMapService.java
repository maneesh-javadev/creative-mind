package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.ConfigurationMapConstituency;
import in.nic.pes.lgd.bean.ConfigurationMapLocalbody;
import in.nic.pes.lgd.bean.ViewConfigMapLandRegion;
import in.nic.pes.lgd.forms.ConfigureMapForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public interface ConfigMapService {
	//public boolean configureLand(ConfigureMapForm configuremapform,int stateCode)throws Exception;
	//public boolean addValuesBlock(ConfigureMapForm configuremapform,int stateCode)throws Exception;
	public boolean addvaluesbody(ConfigureMapForm configuremapform,int stateCode)throws Exception;
	public List<ConfigurationMapConstituency> getConfigureMapConstituencyDetail(int stateCode)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public List<ConfigurationMapConstituency> getLocalBodyTypeDetails(int code)throws Exception;
	//new
// TODO Remove unused code found by UCDetector
// 	public List <ConfigurationMapLandregion> checkMapLRMConfig(int stateCode)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public List <ConfigurationMapConstituency> checkMapLRMLevelConfig(int stateCode)throws Exception;
	
	public boolean lgdmUpdate(ConfigureMapForm configureMapForm,int stateCode, HttpSession session)throws Exception;
	public boolean lgdmconstituencyMapUpdate(ConfigureMapForm configureMapForm,int stateCode, HttpSession session)throws Exception;
	public boolean blockUpdate(ConfigureMapForm configureMapForm,int stateCode, HttpSession session)throws Exception;
	public List<ViewConfigMapLandRegion>	checkMapConfiguration(int stateCode)throws Exception;
	//public List<ConfigureMapForm> getMapLandDetail(char landregiontype);
	public boolean saveLGDMMapConfiguration(
			ConfigureMapForm configMapLGDMForm, HttpServletRequest request,
			HttpSession httpSession)throws Exception;
	boolean updateLGDMMapConfiguration(ConfigureMapForm configMapLGDMForm,
			int stateCode,HttpServletRequest request, HttpSession httpSession)throws Exception;
	public List<ViewConfigMapLandRegion> checkMapConfigurationModify(int stateCode,int operationCode, char type)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public boolean configureBlock(ConfigureMapForm configuremapform,int stateCode,HttpSession httpSession) throws Exception;
	public boolean configureLand(ConfigureMapForm configuremapform,int stateCode,HttpSession httpSession) throws Exception;
	public boolean blockInsert(ConfigureMapForm configureMapForm,int stateCode, HttpSession session) throws Exception;
// TODO Remove unused code found by UCDetector
// 	public Map<String,String> checkMapConfigurationforConstituency(int stateCode,char constituencyType) throws Exception;
	
	/**
	 * The {@code save} used to insert data into configuration_map_local_body table using
	 * hibernate entity ConfigurationMapLocalbody. 
	 *@author vinay yadav 
	 */
	public boolean save(ConfigurationMapLocalbody configurationMapLocalbody )throws Exception;
}
