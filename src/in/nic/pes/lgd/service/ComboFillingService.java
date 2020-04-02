package in.nic.pes.lgd.service;



public interface ComboFillingService {
	
	public boolean getComboValuesforApp(char entityType,String entityParentType,Integer entityParentCode,Integer entityCode) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<CheckAuthorization> getComboValueListforApp(char entityType,
// 			String entityParentType, Integer entityParentCode,
// 			Integer entityCode) throws Exception;
// TODO Remove unused code found by UCDetector
// 	public boolean getComboValuesforAppforward(char entityType,
// 			String entityParentType, Integer entityParentCode, Integer entityCode) throws Exception ;

// TODO Remove unused code found by UCDetector
// 	boolean getComboValuesforList(char entityType,String entityParentType, String entityparentcodeslist , String entitycodeslist) throws Exception;

}
