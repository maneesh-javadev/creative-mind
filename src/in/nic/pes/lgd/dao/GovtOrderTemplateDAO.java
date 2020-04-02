package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.bean.OperationsVariables;

import java.util.List;

public interface GovtOrderTemplateDAO {

	int save(GovernmentOrderTemplate governmentOrderTemplateBean)throws Exception;

	/*List<Operations> getOpeartionsList(String hql)throws Exception;*/
	
	public List<Operations> getOperationsList(char category) throws Exception; 

	String getOperationNamebyOperationCode(int oprCode)throws Exception;

	List<GovernmentOrderTemplate> getTemplateListByOperationCode(int oprCode)throws Exception;

	List<GovernmentOrderTemplate> getTemplateDetailsByTemplateCode(
			int templCode)throws Exception;

	boolean updateIsactiveTemplate(int templCode)throws Exception;

	boolean update(GovernmentOrderTemplate governmentOrderTemplateBean)throws Exception;

	List<OperationsVariables> getVariableListOpr(int operationCode)throws Exception;

}
