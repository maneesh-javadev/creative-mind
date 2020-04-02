package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.bean.OperationsVariables;
import in.nic.pes.lgd.forms.GovtOrderTemplateForm;

import java.util.List;

public interface GovtOrderTemplateService {

	public int createTemplate(GovtOrderTemplateForm govtOrderTemplateForm, int stateCode, long roleCode,int slc)throws Exception;

	public List<Operations> getOperationsList(char category) throws Exception; 

	public String getOperationNamebyOperationCode(int oprCode)throws Exception;

	public List<GovernmentOrderTemplate> getTemplateListByOperationCode(int oprCode)throws Exception;

	public List<GovernmentOrderTemplate> getTemplateDetailsByTemplateCode(
			int templCode)throws Exception;

	public boolean invalidateTemplate(int templCode)throws Exception;

	public boolean updateTemplate(GovtOrderTemplateForm govtOrderTemplateForm,
			int stateCode, long roleCode)throws Exception;

	public List<OperationsVariables> getVariableListonOperation(int operationCode)throws Exception; // NO_UCD (unused code)

}
