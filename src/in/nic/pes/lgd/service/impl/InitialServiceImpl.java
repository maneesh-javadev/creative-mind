	package in.nic.pes.lgd.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.EntityChangesInGivenTime;
import in.nic.pes.lgd.bean.ExternalUser;
import in.nic.pes.lgd.bean.StatewiseEntitiesCount;
import in.nic.pes.lgd.bean.SubDistrictBlockMapEntities;
import in.nic.pes.lgd.dao.InitialDao;
import in.nic.pes.lgd.forms.FaqData;
import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;
import in.nic.pes.lgd.service.InitialService;

@Service
public class InitialServiceImpl implements InitialService {
	
	@Autowired
	private InitialDao initialDAO;
	

	

	@Override
	public List<String> getDetailsofDocument( String type ) throws MissingResourceException {
		List<String> details = new ArrayList<String>();

		ResourceBundle bundle = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH);

		String directoryLocation = bundle.getString("userManual.directory.location");
		String fileName = bundle.getString(String.format("supporting.doc.file.%s", type));
		String filePath = directoryLocation + File.separator + fileName;
		
		String lastmodified = null;
		File f = new File(filePath);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		lastmodified = sdf.format(f.lastModified());
		
		details.add(FilenameUtils.getExtension(filePath));
		details.add(String.valueOf(f.length() / 1024));
		details.add(lastmodified);

		return details;

	}


	@Override
	public List<FaqData> getAvailableFAQs(Integer appId) {
		return initialDAO.getAvailableFAQs(appId);
	}


	@Override
	public String test() {
		// TODO Auto-generated method stub
		return "DWR RUN STATUS";
	}


	@Override
	public String getSupportDownloadDoc(String link, String filename, String propkey) throws Exception {
		return initialDAO.getSupportDownloadDoc(link, filename, propkey);
	}


	@Override
	public String lgdEntitiesCountFn() throws Exception {
		return initialDAO.lgdEntitiesCountFn();
	}


	@Override
	public List<StatewiseEntitiesCount> lgdEntitiesCountFn(boolean isCenter) throws Exception {
		return initialDAO.lgdEntitiesCountFn(isCenter);
	}


	@Override
	public boolean updateStatewise() throws Exception {
		return initialDAO.updateStatewise();
	}
	
	@Override
	public List<SubDistrictBlockMapEntities> getSubdistrictBlockMaped() throws Exception {
		// TODO Auto-generated method stub
		return initialDAO.getSubdistrictBlockMaped();
	}


	@Override
	public List<StatewiseEntitiesCount> getDataFromJsonFile() throws Exception {
		return initialDAO.getDataFromJsonFile();
	}


	

	@Override
	public List<EmailNotification> getUserInformation() {
		// TODO Auto-generated method stub
		return initialDAO.getUserInformation();
	}


	@Override
	public List<EntityChangesInGivenTime> getEntityChangesInGivenTime(int slc,boolean district,boolean subDis,boolean village,String localBody,boolean block)
			throws Exception {
		// TODO Auto-generated method stub
		return initialDAO.getEntityChangesInGivenTime(slc, district, subDis, village, localBody, block);
	}


	 /**
		 * This Method is Use for External User 
		 * @param loginForm
		 * @author Maneesh Kumar
		 * @since 01-10-2019
		 * @return
		 */
	@Override
	public boolean isUserLoginNameExist(String userLoginName) {
	return initialDAO.isUserLoginNameExist(userLoginName);
	}


	@Override
	public boolean validateCaptchaAnswer(String captchaAnswer) {
		return initialDAO.validateCaptchaAnswer(captchaAnswer);
	}


	@Override
	public boolean isPasswordValid(String userName, String enPassword) {
		return initialDAO.isPasswordValid(userName, enPassword);
	}


	@Override
	public Integer findUserAfterAuth(String userName, String enPassword) {
		return initialDAO.findUserAfterAuth(userName, enPassword);
	}


	@Override
	public ExternalUser getExternalUserEntity(Long userId) {
		return initialDAO.getExternalUserEntity(userId);
	}


	@Override
	public List<MenuProfile> findMenuListforExternalUser(Long userId) {
		return initialDAO.findMenuListforExternalUser(userId);
	}
	/**
	 * end External User
	 */

	
	
	
}
