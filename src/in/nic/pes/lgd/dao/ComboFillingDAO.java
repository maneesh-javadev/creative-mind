package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.CheckAuthorizationforlist;

import java.util.List;

public interface ComboFillingDAO {

	public List<CheckAuthorization> populateComboValuesforApp(char entityType,
			String entityParentType, Integer entityParentCode, Integer entityCode) throws Exception;

	public List<CheckAuthorization> populateComboValuesforAppforward(char entityType,
			String entityParentType, Integer entityParentCode, Integer entityCode) throws Exception;

	public List<CheckAuthorizationforlist> populateComboValuesforlist(char entityType, String entityParentType, String entityparentcodeslist ,String entitycodeslist)throws Exception;
	 
}
