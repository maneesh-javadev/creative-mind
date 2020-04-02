package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.ConfigurationGovernmentOrder;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.forms.ConfigGovtOrderForm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface ConfigGovtOrderService {
// TODO Remove unused code found by UCDetector
// 	public List<ConfigurationGovernmentOrder> checkCentralAdminConfig(List<Operations> OperationListCenter,int slc)throws Exception;
	public boolean configGovtOrder(ConfigGovtOrderForm configGovtOrderForm ,int stateCode)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public boolean lrmMethod(ConfigGovtOrderForm configGovtOrderForm)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public boolean adminOrder(ConfigGovtOrderForm configGovtOrderForm)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public boolean constituencyOrder(ConfigGovtOrderForm configGovtOrderForm)throws Exception;
	//public boolean configInsert(ConfigGovtOrderForm configGovtOrderForm,int stateCode)throws Exception;
	//public List <ConfigurationGovernmentOrder> checkLRMConfig(int stateCode)throws Exception;
	public List <ConfigurationGovernmentOrder> checkLRMConfigbyOperationCode(int stateCode,int operationCode)throws Exception;
	public boolean configUpdate(ConfigGovtOrderForm configGovtOrderForm,int stateCode, HttpSession session)throws Exception;
	//public List <ConfigurationGovernmentOrder> checkCentralAdminConfig(int stateCode)throws Exception;
	public List <ConfigurationGovernmentOrder> checkLRMConfig(int slc,List<Operations> OperationListLand) throws Exception;
	public List<ConfigurationGovernmentOrder> checkLGDMConfig(int slc,List<Operations> OperationListUrban) throws Exception ;
	//Maneesh
	public List<ConfigurationGovernmentOrder> checkLGDMConfigPRI(int slc,List<Operations> OperationListUrban) throws Exception;	
	public List<ConfigurationGovernmentOrder> checkLGDMConfigTrade(int slc,List<Operations> OperationListTrade) throws Exception;
	public List <Operations> getOperationDetail(String category)throws Exception;
	//Maneesh
	public List <ConfigurationGovernmentOrder> checkconstituencyMgrConfig(int stateCode)throws Exception;
	Map<String, String> checkMapAndGovtOrderConfiguration(int stateCode,
			int operationCode, char landRegionType)throws Exception;
// TODO Remove unused code found by UCDetector
// 	public abstract Map<String,String> checkMapAndGovtOrderConfigurationforBlock(
// 			int stateCode, int operationCode, char landRegionType)throws Exception;
	public boolean setIsActive(int slc,int opcode) throws Exception;
	//public boolean configInsert(ConfigGovtOrderForm configGovtOrderForm,int slc,HttpSession httpSession) throws Exception;
	public List<ConfigurationGovernmentOrder> checkadministrativeUnitMgrConfig(int slc,List<Operations> OperationList) throws Exception;
	public abstract Map<String,String> checkMapAndGovtOrderConfigurationforCentral(int operationCode) throws Exception;
	public boolean configInsertPRI(ConfigGovtOrderForm configGovtOrderForm,int slc,HttpSession httpSession) throws Exception;
	public boolean configInsertTrade(ConfigGovtOrderForm configGovtOrderForm,int slc,HttpSession httpSession) throws Exception;
	public boolean configInsertUrban(ConfigGovtOrderForm configGovtOrderForm,int slc,HttpSession httpSession) throws Exception;
	public boolean configInsertConstituence(ConfigGovtOrderForm configGovtOrderForm,int slc,HttpSession httpSession) throws Exception;
	public boolean configInsertCenter(ConfigGovtOrderForm configGovtOrderForm,int slc,HttpSession httpSession) throws Exception;
	public boolean configInsertLandRegion(ConfigGovtOrderForm configGovtOrderForm,int slc,HttpSession httpSession) throws Exception;
	public boolean configInsertBlock(ConfigGovtOrderForm configGovtOrderForm,int slc,HttpSession httpSession) throws Exception;

}
