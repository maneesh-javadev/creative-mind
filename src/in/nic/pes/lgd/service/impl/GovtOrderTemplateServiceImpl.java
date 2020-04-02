package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.bean.OperationsVariables;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.dao.GovtOrderTemplateDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.GovtOrderTemplateForm;
import in.nic.pes.lgd.service.GovtOrderTemplateService;
import in.nic.pes.lgd.utils.CurrentDateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Repository
public class GovtOrderTemplateServiceImpl implements GovtOrderTemplateService{
	@Autowired
	private GovtOrderTemplateDAO govtOrderTemplateDAO;

	@Autowired
	StateDAO stateDao;

	@Override
	public int createTemplate(GovtOrderTemplateForm govtOrderTemplateForm,
			int stateCode, long roleCode,int slc) throws Exception {
		// TODO createTemplate
		String srcCodeTemplate=null;
		//boolean success=false;
		Timestamp time = null;
		int stateVersion=0;
		int templateCode=0;
		time = CurrentDateTime.getCurrentTimestamp();
		srcCodeTemplate=govtOrderTemplateForm.getTemplateBodySrc();
		/*char orderType=' ';
		if (govtOrderTemplateForm.getOrderType()!=null) {
			orderType = govtOrderTemplateForm.getOrderType().charAt(0);
		}*/
		String templateLanguage=null;
		if (govtOrderTemplateForm.getTemplateLanguage()!=null) {
			templateLanguage=govtOrderTemplateForm.getTemplateLanguage();
		}
		int operationCode=0;
		if (govtOrderTemplateForm.getOperations()!=null) {
			operationCode = Integer.parseInt(govtOrderTemplateForm
					.getOperations());
		}
		stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		try {
			Operations operationsBean=new Operations();
			operationsBean.setOperationCode(operationCode);
			State state=null;
			state = new State();
			StatePK statePKBean=null;
			statePKBean=new StatePK(stateCode, stateVersion);
			state.setStatePK(statePKBean);
			GovernmentOrderTemplate governmentOrderTemplateBean=null;
			governmentOrderTemplateBean = new GovernmentOrderTemplate();
			governmentOrderTemplateBean.setCreatedby(roleCode);
			governmentOrderTemplateBean.setCreatedon(time);
			governmentOrderTemplateBean.setOperations(operationsBean);
			governmentOrderTemplateBean.setSlc(slc);
			//governmentOrderTemplateBean.setRoleCode(roleCode);
			//governmentOrderTemplateBean.setState(state);
			if (templateLanguage != null && templateLanguage.equals("English")) {
				governmentOrderTemplateBean
						.setTemplateDescription(srcCodeTemplate);
			}
			governmentOrderTemplateBean.setTemplateNameEnglish(govtOrderTemplateForm.getTemplateNameEnglish().trim());
			if (templateLanguage != null && templateLanguage.equals("Local")) {
				governmentOrderTemplateBean
						.setTemplateRegional(srcCodeTemplate);
			}
			if (govtOrderTemplateForm.getOrderType()!=null) {
				governmentOrderTemplateBean
						.setTemplateType(govtOrderTemplateForm.getOrderType()
								.charAt(0));
			}
			governmentOrderTemplateBean.setIsactive(true);
			
			templateCode=govtOrderTemplateDAO.save(governmentOrderTemplateBean);
		} catch (Exception e) {
			 templateCode=0;
		}
		return templateCode;
		
	}

	@Override
	public List<Operations> getOperationsList(char category) throws Exception 
	{
		// TODO getOpeartionsList
		List<Operations> oprList = null; 
		oprList= new ArrayList<Operations>();
		//String hql="from Operations where isactive=true and category =:categ order by operationName";
		
		oprList=govtOrderTemplateDAO.getOperationsList(category);
		return oprList;
	}

	@Override
	public String getOperationNamebyOperationCode(int oprCode) throws Exception {
		// TODO getOperationNamebyOperationCode
		String operationName=null;
		operationName=govtOrderTemplateDAO.getOperationNamebyOperationCode(oprCode);
		return operationName;
	}

	public List<GovernmentOrderTemplate> getTemplateListByOperationCode(int oprCode) {
		
		List<GovernmentOrderTemplate> templateList = new ArrayList<GovernmentOrderTemplate>();
		try {
			templateList=govtOrderTemplateDAO.getTemplateListByOperationCode(oprCode);
		} catch (Exception e) {
			return null;
		}
		return templateList;
	}

	@Override
	public List<GovernmentOrderTemplate> getTemplateDetailsByTemplateCode(
			int templCode) {
		// TODO getTemplateDetailsByOperationCode
		List<GovernmentOrderTemplate> templateList = null; 
		templateList= new ArrayList<GovernmentOrderTemplate>();
		try {
			templateList=govtOrderTemplateDAO.getTemplateDetailsByTemplateCode(templCode);
		} catch (Exception e) {
			return null;
		}
		return templateList;
	}

	@Override
	public boolean invalidateTemplate(int templCode) throws Exception {
		boolean success=false;
		success=govtOrderTemplateDAO.updateIsactiveTemplate(templCode);
		return success;
	}

	@Override
	public boolean updateTemplate(GovtOrderTemplateForm govtOrderTemplateForm,
			int stateCode, long roleCode) throws Exception {
		// TODO updateTemplate
		String srcCodeTemplate=null;
		boolean success=false;
		Timestamp time = null;
		int stateVersion=0;
		time = CurrentDateTime.getCurrentTimestamp();
		srcCodeTemplate=govtOrderTemplateForm.getTemplateBodySrc();
		/*char orderType=' ';
		if (govtOrderTemplateForm.getOrderType()!=null) {
			orderType = govtOrderTemplateForm.getOrderType().charAt(0);
		}*/
		String templateLanguage=null;
		if (govtOrderTemplateForm.getTemplateLanguage()!=null) {
			templateLanguage=govtOrderTemplateForm.getTemplateLanguage();
		}
		int operationCode=0;
		if (govtOrderTemplateForm.getOperations()!=null) {
			operationCode = Integer.parseInt(govtOrderTemplateForm
					.getOperations());
		}
		stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		try {
			Operations operationsBean=new Operations();
			operationsBean.setOperationCode(operationCode);
			State state=null;
			state = new State();
			StatePK statePKBean=null;
			statePKBean=new StatePK(stateCode, stateVersion);
			state.setStatePK(statePKBean);
			GovernmentOrderTemplate governmentOrderTemplateBean=null;
			governmentOrderTemplateBean = new GovernmentOrderTemplate();
			governmentOrderTemplateBean.setTemplateCode(govtOrderTemplateForm.getTemplateCode());
			governmentOrderTemplateBean.setCreatedby(roleCode);
			governmentOrderTemplateBean.setCreatedon(time);
			governmentOrderTemplateBean.setOperations(operationsBean);
			//governmentOrderTemplateBean.setRoleCode(roleCode);
			//governmentOrderTemplateBean.setState(state);
			if (templateLanguage !=null && templateLanguage.equals("English")) {
				governmentOrderTemplateBean
						.setTemplateDescription(srcCodeTemplate);
			}
			governmentOrderTemplateBean.setTemplateNameEnglish(govtOrderTemplateForm.getTemplateNameEnglish().trim());
			if (templateLanguage.equals("Local")) {
				governmentOrderTemplateBean
						.setTemplateRegional(srcCodeTemplate);
			}
			if (govtOrderTemplateForm.getOrderType()!=null) {
				governmentOrderTemplateBean
						.setTemplateType(govtOrderTemplateForm.getOrderType()
								.charAt(0));
			}
			governmentOrderTemplateBean.setIsactive(true);
			
			success=govtOrderTemplateDAO.update(governmentOrderTemplateBean);
		} catch (Exception e) {
			 success=false;
		}
		return success;
	}
	@Override
	public List<OperationsVariables> getVariableListonOperation(int operationCode) throws Exception
	{
		List<OperationsVariables> lstOperationsVariables =null;
		lstOperationsVariables=new ArrayList<OperationsVariables>();
		lstOperationsVariables=govtOrderTemplateDAO.getVariableListOpr(operationCode);
		return lstOperationsVariables;
	}

}
