package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.dao.ComboFillingDAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComboFillingServiceImpl implements ComboFillingService{

	@Autowired
	ComboFillingDAO comboFillingDAO;
	
	/*@Override
	public boolean getComboValuesforAppforward(char entityType,
			String entityParentType, Integer entityParentCode, Integer entityCode) throws Exception {
		
		List<CheckAuthorization> comboList = new ArrayList<CheckAuthorization>();		
		try {
			comboList = comboFillingDAO.populateComboValuesforAppforward(entityType, entityParentType, entityParentCode, entityCode);
		    if(comboList != null && !comboList.isEmpty())
		    {
		    	if(comboList.size() >0)
		    	{
		    		return true;
		    	}
		    }
		} catch (Exception e) {
			 throw e;
		}
		
		 return false;
	}*/

	@Override
	public boolean getComboValuesforApp(char entityType,
			String entityParentType, Integer entityParentCode, Integer entityCode) throws Exception {
		
		List<CheckAuthorization> comboList = new ArrayList<CheckAuthorization>();		
		try {
			comboList = comboFillingDAO.populateComboValuesforApp(entityType, entityParentType, entityParentCode, entityCode);
		    if(comboList != null && !comboList.isEmpty())
		    {
		    	if(comboList.size() >0)
		    	{
		    		return true;
		    	}
		    }
		} catch (Exception e) {
			 throw e;
		}
		
		 return false;
	}
	 
	
	/*@Override
	public List<CheckAuthorization> getComboValueListforApp(char entityType,
			String entityParentType, Integer entityParentCode, Integer entityCode) throws Exception {
		
		List<CheckAuthorization> comboList = new ArrayList<CheckAuthorization>();		
		try {
			comboList = comboFillingDAO.populateComboValuesforApp(entityType, entityParentType, entityParentCode, entityCode);
		   
		} catch (Exception e) {
			 throw e;
		}
		
		 return comboList;
	}*/




///////////////////method for list validation through function by siva/////////////////////////////

/*@Override
public boolean getComboValuesforList(char entityType,String entityParentType, String entityparentcodeslist , String entitycodeslist) throws Exception {

List<CheckAuthorizationforlist> comboList = new ArrayList<CheckAuthorizationforlist>();		
try {
comboList = comboFillingDAO.populateComboValuesforlist(entityType, entityParentType, entityparentcodeslist, entitycodeslist);
if(comboList != null && !comboList.isEmpty())
{
if(comboList.size() >0)
{
	return true;
}
}
} catch (Exception e) {
throw e;
}

return false;
}*/

}
