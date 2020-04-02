package in.nic.pes.lgd.common;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.org.ep.exception.BaseAppException;

import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.FreezeUnfreezeStatus;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.dao.CommonDAO;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService 
{
	private static final Logger log = Logger.getLogger(CommonServiceImpl.class);
	
	@Autowired
	CommonDAO commonDAO;
	
	@Override
	public List<CheckAuthorization> checkAuthorization(char entityType, String parentEntityType, Integer parentEntityCode, Integer entityCode) throws BaseAppException
	{		
		return commonDAO.checkAuthorization(entityType, parentEntityType, parentEntityCode, entityCode);
	}

	@Override
	public Integer getSlc(Integer stateId) {
		return commonDAO.getSlc(stateId);
	}	
	
	@Override
	public Integer getdlc(Integer districtId) {
		return commonDAO.getdlc(districtId);
	}	
	
	//Get key value from the Message properties file from the real path
	
	public String getApplicationBundle(String key, Object... params) {
		String message = null;
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		
		@SuppressWarnings("deprecation")
		String webRoot = request.getRealPath("/");

		String strPath = webRoot + "/WEB-INF/messages";
		String newPath = strPath.replace("\\", "/");
		//System.out.println("The real path would be as printed::::" + newPath);

		Properties p = new Properties();
		try {
			p.load(new FileInputStream(newPath + "/messages_en.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e1);
		}
		message = p.getProperty(key);
		//System.out.println(message);
		return message;
	}
	
	/*public List<FaqData> getFAQ(Integer appid) throws Exception
	{		
		return commonDAO.getFAQDAO(appid);
	}*/
	
	/*public List<ResponseData> getResponse(Integer appid) throws Exception
	{		
		return commonDAO.getResponseDAO(appid);
	}*/
	
	
	public boolean existEntityName(Integer entityParentCode,
								   char entityParentType,
								   String entityName) 
								   throws Exception{
		return commonDAO.existEntityName(entityParentCode,entityParentType,entityName);
	}
	/* Code given by vinay on 24-09-2014*/
	/*@Override
	public java.util.Map<String, Object> getFAQandRespose(Integer appId) {
		return commonDAO.getFAQandRespose(appId);
	}*/

	/**
	 * The {@code getFreezeUnfreezeStatus} used to check freeze/un-freeze status for 
	 * land regions and local bodies.  
	 * @param entityCode
	 * @param entityType
	 * @return
	 */
	@Override
	public FreezeUnfreezeStatus getFreezeUnfreezeStatus(Integer entityCode, String entityType) {
		// TODO Auto-generated method stub
		return commonDAO.getFreezeUnfreezeStatus(entityCode, entityType);
	}
	
	
	public  List<Integer> createListFormString(String items) throws Exception{
		List<Integer> object=null;
		if (items.contains(",")) {
			object = new ArrayList<Integer>();
			Scanner scanner = new Scanner(items);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				object.add(Integer.parseInt(scanner.next()));
			}
			scanner.close();
		}else if(items.length()>0){
			object = new ArrayList<Integer>();
			object.add(Integer.parseInt(items));
		}
		
		return object;
	}

	@Override
	public List<Object> getDistrictDetailsByStateCode(Integer stateCode) {
		return commonDAO.getDistrictDetailsByStateCode(stateCode);
	}
	
	/**
	 * The {@code checkWsAllowedIpAddress} used to check allowed IP address  
	 * to use some restricted web services.  
	 * @param ipAddress
	 * @return boolean
	 */
	@Override
	public boolean checkWsAllowedIpAddress(String ipAddress) {
		return commonDAO.checkWsAllowedIpAddress(ipAddress);
	}

	@Override
	public boolean mobileTokenAuthentication(String token){
		return commonDAO.mobileTokenAuthentication(token);
	}
	
	
	/**
	 * The {@code checkWsUrl} used to check restricted web services 
	 * @param requestURI
	 * @return boolean
	 */
	@Override
	public boolean checkWsUrl(String requestURI) {

		InputStream is = null;
		ArrayList<String> list = new ArrayList<String>();

		try {
			is = this.getClass().getResourceAsStream("/wsAllowedUrl.properties");
			Properties properties = new Properties();
			properties.load(is);
			is.close();
			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				list.add(value);
			}
		} catch (FileNotFoundException e) {
			log.debug("FileNotFoundException" + e);
		} catch (IOException e) {
			log.debug("IOException" + e);
		}

		if (list.contains(requestURI))
			return true;
		else
			return false;
	}

	@Override
	public boolean isAssignNodelOfficer(Long userId) throws Exception {
		return commonDAO.isAssignNodelOfficer(userId);
	}

	@Override
	public NodalOfficerState getNodalOfficerDetails(Long userId) {
		// TODO Auto-generated method stub
		return commonDAO.getNodalOfficerDetails(userId);
	}

	@Override
	public Response sendMailSMS(String mailIds, String pnos) {
		return commonDAO.sendMailSMS(mailIds, pnos);
	}
}
