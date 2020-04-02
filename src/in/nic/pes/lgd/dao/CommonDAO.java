package in.nic.pes.lgd.dao;

import java.util.List;

import com.org.ep.exception.BaseAppException;

import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.FreezeUnfreezeStatus;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.forms.Response;

public interface CommonDAO 
{
	List<CheckAuthorization> checkAuthorization(char entityType, String parentEntityType, Integer parentEntityCode, Integer entityCode) throws BaseAppException;

	Integer getSlc(Integer stateId);

	Integer getdlc(Integer districtId);
	//public List<FaqData> getFAQDAO(Integer appid) throws Exception;
	//public List<ResponseData> getResponseDAO(Integer appid) throws Exception; 
	public boolean existEntityName(Integer entityParentCode,char entityParentType, String entityName) throws Exception;
	/* Code given by vinay on 24-09-2014*/
	//public java.util.Map<String, Object> getFAQandRespose(Integer appId);
	
	/**
	 * The {@code getFreezeUnfreezeStatus} used to check freeze/un-freeze status for 
	 * land regions and local bodies.  
	 * @param entityCode
	 * @param entityType
	 * @return
	 */
	public FreezeUnfreezeStatus getFreezeUnfreezeStatus(Integer entityCode, String entityType);
	
	public List<Object> getDistrictDetailsByStateCode(Integer stateCode);
	public boolean checkWsAllowedIpAddress(String ipAddress);
	public boolean mobileTokenAuthentication(String token);
	
	public boolean isAssignNodelOfficer(Long userId) throws Exception;
	
	public NodalOfficerState getNodalOfficerDetails(Long userId);
	public Response sendMailSMS(String mailIds,String pnos);
}
