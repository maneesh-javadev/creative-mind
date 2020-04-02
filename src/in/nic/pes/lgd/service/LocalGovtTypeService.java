package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.CheckLocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;
import in.nic.pes.lgd.forms.LocalGovtTypeForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pes.attachment.util.AttachedFilesForm;

public interface LocalGovtTypeService {
	
	public int saveLocalGovtType(LocalGovtTypeForm localgovtType,GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request)throws Exception;
	
	public List<LocalBodyType> getLocalBodyTypeDetails(int localBodyTypeCode)throws Exception;
	public List<LocalGovtTypeDataForm> getLocalBodyTypeDetailsall(int localBodyTypeCode)throws Exception;
	public boolean modifyLocalGovtTypeInfo(LocalGovtTypeForm localgovtTypeForm,GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request)throws Exception;
	List<LocalBodyTiersSetup> getLocalBodyTypeDetails()throws Exception;

	boolean checkgovtTypeDependency(char category, char level)throws Exception; 
	public List<CheckLocalBodyType> checkLocalBodyType(Integer localBodyTypeCode) throws Exception;
}
