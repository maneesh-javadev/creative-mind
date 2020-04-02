package in.nic.pes.lgd.service;

import java.util.List;
import java.util.MissingResourceException;

import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.EntityChangesInGivenTime;
import in.nic.pes.lgd.bean.ExternalUser;
import in.nic.pes.lgd.bean.StatewiseEntitiesCount;
import in.nic.pes.lgd.bean.SubDistrictBlockMapEntities;
import in.nic.pes.lgd.forms.FaqData;

public interface InitialService {

	public List<String> getDetailsofDocument(String type) throws MissingResourceException;
	
	public List<FaqData> getAvailableFAQs(Integer appId);
	
	public String test();
	
	public String getSupportDownloadDoc(String link, String filename, String propkey)throws Exception;
	
	public String lgdEntitiesCountFn() throws Exception;
	
	public List<StatewiseEntitiesCount> lgdEntitiesCountFn(boolean isCenter) throws Exception;
	
	public boolean updateStatewise() throws Exception;
	
	public List<SubDistrictBlockMapEntities> getSubdistrictBlockMaped() throws Exception;
	
	public List<StatewiseEntitiesCount> getDataFromJsonFile() throws Exception;
	
	public List<EntityChangesInGivenTime> getEntityChangesInGivenTime(int slc,boolean district,boolean subDis,boolean village,String localBody,boolean block) throws Exception; 
	
	public List<EmailNotification> getUserInformation();
	
	 /**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	boolean isUserLoginNameExist(String userLoginName);
	
	boolean validateCaptchaAnswer(String captchaAnswer);
	
	boolean isPasswordValid(String userName, String enPassword);
	
	Integer findUserAfterAuth(String userName, String enPassword) ;
	
	 ExternalUser getExternalUserEntity(Long userId);
	 
	 List<MenuProfile> findMenuListforExternalUser(Long userId);
	 /**
	 * end External User
	 */
	 
}
