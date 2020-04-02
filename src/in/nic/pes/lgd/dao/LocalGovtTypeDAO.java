package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.CheckLocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTypeHistory;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;

import java.util.List;

import org.hibernate.Session;

public interface LocalGovtTypeDAO {

	public int saveLocalGovtType(LocalBodyType localbodyType, Session session)
			throws Exception;

	public void saveLocalGovtTypeHistory(
			LocalBodyTypeHistory localBodyTypeHistory,Session session) throws Exception;
  
	 public boolean updateLocalGovtType(LocalBodyType localBodyType,Session session)
				throws Exception;

// TODO Remove unused code found by UCDetector
// 	public void SetGovermentOrderEntity(String entityCode, char entityType)
// 			throws Exception;

	public boolean updateIsActive(LocalBodyType localBodyType) throws Exception; // NO_UCD (unused code)

	public List<LocalBodyTiersSetup> lgsetup() throws Exception;

	public List<LocalBodyType> getlocalBodyTypelist() throws Exception; // NO_UCD (unused code)

	public List<LocalGovtTypeDataForm> getLocalBodyTypeDetailall(
			int localBodyTypeCode) throws Exception;

	public List<LocalBodyType> getLocalBodyTypeDetails(
			int localBodyTypeCode) throws Exception;

	public boolean checkgovtTypeDependency(String hql) throws Exception;
	public List<CheckLocalBodyType> checkLocalBodyTypeDAO(Integer localBodyTypeCode) throws Exception;

}
