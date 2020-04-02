package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.ConfigurationMapConstituency;
import in.nic.pes.lgd.bean.ConfigurationMapLandregion;
import in.nic.pes.lgd.bean.ConfigurationMapLandregionLevel;
import in.nic.pes.lgd.bean.ConfigurationMapLocalbody;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.ViewConfigMapLandRegion;
import in.nic.pes.lgd.dao.ConfigMapConstituencyDAO;
import in.nic.pes.lgd.dao.ConfigMapLandRegionDAO;
import in.nic.pes.lgd.dao.ConfigMapLandRegionLevelDAO;
import in.nic.pes.lgd.dao.ConfigMapLocalBodyDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.ConfigureMapForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConfigMapService;
import in.nic.pes.lgd.utils.CurrentDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfigMapServiceImpl implements ConfigMapService {
	@Autowired
	ConfigMapConstituencyDAO configMapConstituencyDAO;

	@Autowired
	ConfigMapLandRegionDAO configMapLandRegionDAO;

	@Autowired
	CurrentDateTime dateTimeUtil;

	@Autowired
	ConfigMapLandRegionLevelDAO configMapLandRegionLevelDAO;

	@Autowired
	ConfigMapLocalBodyDAO configMapLocalBodyDAO;

	@Autowired
	StateDAO stateDao;
	
	@Autowired
	CommonService commonService;

	@Autowired
	private SessionFactory sessionFactory;

	int tiersetupCode = 1;
	int tiersetupVersion = 1;
	int configurationLandRegionForLevel = 0;
	
	
	private Long userId=0L;
	
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		userId = sessionObject.getUserId();
		
	}

	
	@Transactional
	public boolean configureLand(ConfigureMapForm configuremapform,int stateCode,HttpSession httpSession) throws Exception {
		// TODO configureLand
		
		Date currentDate = dateTimeUtil.getCurrentDate();
		//int stateVersion = 0;
		//stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		ConfigurationMapLandregion configmaplandregion = new ConfigurationMapLandregion();

		
		//int configureMapLandRegionCode = 0;
		
		configmaplandregion.setIsactive(true);
	
		/*	State state = new State();
		StatePK statePK = new StatePK();
		statePK.setStateCode(stateCode);
		statePK.setStateVersion(stateVersion);
		state.setStatePK(statePK);
		configmaplandregion.setState(state);*/
		int slc=commonService.getSlc(stateCode);
		configmaplandregion.setSlc(slc);
		//System.out.println("state code" + stateCode);
		configmaplandregion.setLastupdated(currentDate);
		configmaplandregion.setLastupdatedby(userId);
		configmaplandregion.setCreatedon(currentDate);
		configmaplandregion.setCreatedby( userId);

		
		ConfigurationMapLandregionLevel configurationMapLandregionLevel1 = new ConfigurationMapLandregionLevel();
		ConfigurationMapLandregionLevel configurationMapLandregionLevel2 = new ConfigurationMapLandregionLevel();
		ConfigurationMapLandregionLevel configurationMapLandregionLevel3 = new ConfigurationMapLandregionLevel();
		ConfigurationMapLandregionLevel configurationMapLandregionLevel4 = new ConfigurationMapLandregionLevel();
		//ConfigurationMapLandregionLevel configurationMapLandregionLevel5 = new ConfigurationMapLandregionLevel();
		
		configurationMapLandregionLevel1.setIsactive(true);
		configurationMapLandregionLevel1.setConfigurationMapLandregion(configmaplandregion);
		configurationMapLandregionLevel1.setLandregionType('S');
		configurationMapLandregionLevel1.setIsmapupload(true);

		if(configuremapform.getBaseUrlState() !=null && configuremapform.getBaseUrlState().length() > 0
				&& configuremapform.isIsmapuploadState() == false)
		{
			configurationMapLandregionLevel1.setBaseUrl(configuremapform.getBaseUrlState());
			configurationMapLandregionLevel1.setIsmapupload(false);
			
		}
		configurationMapLandregionLevel2.setIsactive(true);
		configurationMapLandregionLevel2.setConfigurationMapLandregion(configmaplandregion);
		configurationMapLandregionLevel2.setLandregionType('D');
		configurationMapLandregionLevel2.setIsmapupload(true);

		if(configuremapform.getBaseUrlDistrict() !=null && configuremapform.getBaseUrlDistrict().length() > 0
				&& configuremapform.isIsmapuploadDistrict() == false)
		{
			configurationMapLandregionLevel2.setBaseUrl(configuremapform.getBaseUrlDistrict());
			configurationMapLandregionLevel2.setIsmapupload(false);
			
		}
		configurationMapLandregionLevel3.setIsactive(true);
		configurationMapLandregionLevel3.setConfigurationMapLandregion(configmaplandregion);
		configurationMapLandregionLevel3.setLandregionType('T');
		configurationMapLandregionLevel3.setIsmapupload(true);

		if(configuremapform.getBaseUrlSubDist() !=null && configuremapform.getBaseUrlSubDist().length() > 0
				&& configuremapform.isIsmapuploadSubDist() == false)
		{
			configurationMapLandregionLevel3.setBaseUrl(configuremapform.getBaseUrlSubDist());
			configurationMapLandregionLevel3.setIsmapupload(false);
			
		}
		configurationMapLandregionLevel4.setIsactive(true);
		configurationMapLandregionLevel4.setConfigurationMapLandregion(configmaplandregion);
		configurationMapLandregionLevel4.setLandregionType('V');
		configurationMapLandregionLevel4.setIsmapupload(true);

		if(configuremapform.getBaseUrlVillage() !=null && configuremapform.getBaseUrlVillage().length() > 0
				&& configuremapform.isIsmapuploadVillage() == false)
		{
			configurationMapLandregionLevel4.setBaseUrl(configuremapform.getBaseUrlVillage());
			configurationMapLandregionLevel4.setIsmapupload(false);
			
		}
		/*configurationMapLandregionLevel5.setIsactive(true);
		configurationMapLandregionLevel5.setConfigurationMapLandregion(configmaplandregion);
		configurationMapLandregionLevel5.setLandregionType('B');
		configurationMapLandregionLevel5.setIsmapupload(true);*/

		/*if(configuremapform.getBaseUrlSubDist() !=null && configuremapform.getBaseUrlSubDist().length() > 0
				&& configuremapform.isIsmapuploadSubDist() == false)
		{
			configurationMapLandregionLevel5.setBaseUrl(configuremapform.getBaseUrlSubDist());
			configurationMapLandregionLevel5.setIsmapupload(false);
			
		}*/
		Set<ConfigurationMapLandregionLevel> set = new HashSet<ConfigurationMapLandregionLevel>();
		set.add(configurationMapLandregionLevel1);
		set.add(configurationMapLandregionLevel2);
		set.add(configurationMapLandregionLevel3);
		set.add(configurationMapLandregionLevel4);
		/*set.add(configurationMapLandregionLevel5);*/
		configmaplandregion.setConfigurationMapLandregionLevels(set);
		//configureMapLandRegionCode = 
		configMapLandRegionDAO.save(configmaplandregion);
		
		return true;

	}

	
	
	
	@Transactional
	public boolean addvaluesbody(ConfigureMapForm configuremapform,
			int stateCode) throws Exception {
		Date currentDate = dateTimeUtil.getCurrentDate();
		ConfigurationMapConstituency configurationMapConstituency = new ConfigurationMapConstituency();
		int slc = commonService.getSlc(stateCode);
		
		if (configuremapform.isIsmapuploadParliament() == true) {
			configurationMapConstituency.setConstituencyType(configuremapform
					.getConstituencyTypeParliament());
			/*State state = new State();
			StatePK statePK = new StatePK();
			statePK.setStateCode(stateCode);
			statePK.setStateVersion(stateVersion);
			state.setStatePK(statePK);*/
			//configurationMapConstituency.setState(state);
			configurationMapConstituency.setSlc(slc);
			configurationMapConstituency.setBaseUrl(null);
			configurationMapConstituency.setIsactive(true);
			configurationMapConstituency.setLastupdated(currentDate);
			configurationMapConstituency.setLastupdatedby(userId);
			configurationMapConstituency.setCreatedon(currentDate);
			configurationMapConstituency.setCreatedby(userId);
			configurationMapConstituency.setIsmapupload(configuremapform
					.isIsmapuploadParliament());

			configMapConstituencyDAO.save(configurationMapConstituency);

		} else if (configuremapform.getBaseUrlParliament().length() > 0
				&& configuremapform.isIsmapuploadParliament() == false) {

			configurationMapConstituency.setConstituencyType(configuremapform
					.getConstituencyTypeParliament());
			//State state = new State();
			/*StatePK statePK = new StatePK();
			statePK.setStateCode(stateCode);
			statePK.setStateVersion(stateVersion);
			state.setStatePK(statePK);*/
			//configurationMapConstituency.setState(state);
			configurationMapConstituency.setSlc(slc);
			configurationMapConstituency.setBaseUrl(configuremapform
					.getBaseUrlParliament());
			configurationMapConstituency.setIsactive(true);
			configurationMapConstituency.setLastupdated(currentDate);
			configurationMapConstituency.setLastupdatedby(userId);
			configurationMapConstituency.setCreatedon(currentDate);
			configurationMapConstituency.setCreatedby(userId);
			configurationMapConstituency.setIsmapupload(configuremapform
					.isIsmapuploadParliament());

			configMapConstituencyDAO.save(configurationMapConstituency);
		}

		if (configuremapform.isIsmapuploadAssembly() == true) {
			int configurationMapConstituencyCode = 0;
			configurationMapConstituencyCode = configMapConstituencyDAO
					.getMaxRecords("select COALESCE(max(configuration_map_constituency_code),1) from configuration_map_constituency");
			/*System.out.println("CODE-Assembnly-id--"
					+ configurationMapConstituencyCode);*/
			if (configurationMapConstituencyCode == 0) {
				configurationMapConstituencyCode = 1;
			} else {
				configurationMapConstituencyCode = configurationMapConstituencyCode + 1;
			}

			configurationMapConstituency
					.setConfigurationMapConstituencyCode(configurationMapConstituencyCode);

			configurationMapConstituency.setConstituencyType(configuremapform
					.getConstituencyTypeAssembly());
			/*State state = new State();
			StatePK statePK = new StatePK();
			statePK.setStateCode(stateCode);
			statePK.setStateVersion(stateVersion);
			state.setStatePK(statePK);*/
			//configurationMapConstituency.setState(state);
			configurationMapConstituency.setBaseUrl(null);
			configurationMapConstituency.setSlc(slc);
			configurationMapConstituency.setIsactive(true);
			configurationMapConstituency.setLastupdated(currentDate);
			configurationMapConstituency.setLastupdatedby(userId);
			configurationMapConstituency.setCreatedon(currentDate);
			configurationMapConstituency.setCreatedby(userId);
			configurationMapConstituency.setIsmapupload(configuremapform
					.isIsmapuploadAssembly());

			configMapConstituencyDAO.save(configurationMapConstituency);

		} else if (configuremapform.getBaseUrlAssembly().length() > 0
				&& configuremapform.isIsmapuploadAssembly() == false) {

			int configurationMapConstituencyCode = 0;
			configurationMapConstituencyCode = configMapConstituencyDAO
					.getMaxRecords("select COALESCE(max(configuration_map_constituency_code),1) from configuration_map_constituency");
			/*System.out.println("CODE-configurParliamentituencyCode-id--"
					+ configurationMapConstituencyCode);*/
			if (configurationMapConstituencyCode == 0) {
				configurationMapConstituencyCode = 1;
			} else {
				configurationMapConstituencyCode = configurationMapConstituencyCode + 1;
			}

			configurationMapConstituency
					.setConfigurationMapConstituencyCode(configurationMapConstituencyCode);

			configurationMapConstituency.setConstituencyType(configuremapform
					.getConstituencyTypeAssembly());
			/*State state = new State();
			StatePK statePK = new StatePK();
			statePK.setStateCode(stateCode);
			statePK.setStateVersion(stateVersion);
			state.setStatePK(statePK);*/
			//configurationMapConstituency.setState(state);
			configurationMapConstituency.setSlc(slc);
			configurationMapConstituency.setBaseUrl(configuremapform
					.getBaseUrlAssembly());
			configurationMapConstituency.setIsactive(true);
			configurationMapConstituency.setLastupdated(currentDate);
			configurationMapConstituency.setLastupdatedby(userId);
			configurationMapConstituency.setCreatedon(currentDate);
			configurationMapConstituency.setCreatedby(userId);
			configurationMapConstituency.setIsmapupload(configuremapform
					.isIsmapuploadAssembly());
			configMapConstituencyDAO.save(configurationMapConstituency);
		}

		return true;

	}

	@Override
	public List<ConfigurationMapConstituency> getConfigureMapConstituencyDetail(
			int stateCode) throws Exception {
		List<ConfigurationMapConstituency> listConfigurationMapConstituency = null;
		listConfigurationMapConstituency = new ArrayList<ConfigurationMapConstituency>();
		/*int stateVersion = 0;
		stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);*/
		// List<ConfigurationMapConstituency>
		// listConstituencyDetail=configMapConstituencyDAO.getConfigureMapConstituencyDetail(configurationMapConstituencyCode);
		int slc=commonService.getSlc(stateCode);
		// String
		// Hquery="from configuration_map_constituency where state_code='"+stateCode+"' and state_version='"+stateVersion+"'";
		String Hquery = ("from ConfigurationMapConstituency where slc="
				+ slc
				+ " and isactive=true");
		listConfigurationMapConstituency = configMapConstituencyDAO.getConfigureMapConstituencyDetail(Hquery);
		if (listConfigurationMapConstituency.size() > 0) {
			return listConfigurationMapConstituency;
		} else
			return null;

	}

	

	
	@Override
	public boolean lgdmUpdate(ConfigureMapForm configureMapForm, int stateCode,
			HttpSession httpSession) throws Exception {
		// TODO lgdmUpdate
		//Date currentDate = dateTimeUtil.getCurrentDate();
		//int stateVersion = 0;
		//stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		List<ConfigurationMapLandregion> configurationMapLandregionList=null;
		
		configurationMapLandregionList=configMapLandRegionLevelDAO.getMapConfigofState(stateCode);
		Integer configurationMapLandregionCode=null;
		if(httpSession.getAttribute("configurationMapLandregionCode")!=null)
			configurationMapLandregionCode=Integer.parseInt(httpSession.getAttribute("configurationMapLandregionCode").toString());	
		else
			configurationMapLandregionCode=0;
		
		ConfigurationMapLandregion configmaplandregion = null;
		
		
		if (configurationMapLandregionList!=null) {
			int i=0;
			
			for(ConfigurationMapLandregion configLand : configurationMapLandregionList )
			{
				if(configLand.getConfigurationMapLandregionCode()==configurationMapLandregionCode)
				{
					configmaplandregion = configurationMapLandregionList.get(i);
				}
				i++;
			}
			
		
			Set<ConfigurationMapLandregionLevel> configurationMapLandregionLevelsSet = null;
			configurationMapLandregionLevelsSet = configmaplandregion.getConfigurationMapLandregionLevels();
			
			for(ConfigurationMapLandregionLevel configurationMapLandregionLevel : configurationMapLandregionLevelsSet ){
				configurationMapLandregionLevel.setIsactive(false);
			}
			
			//int configureMapLandRegionCode = 0;
			List<ViewConfigMapLandRegion> viewConfigMapLandRegionList=null;
			viewConfigMapLandRegionList=configureMapForm.getViewConfigMapLandRegion();
			ConfigurationMapLandregionLevel configurationMapLandregionLevel1 = new ConfigurationMapLandregionLevel();
			ConfigurationMapLandregionLevel configurationMapLandregionLevel2 = new ConfigurationMapLandregionLevel();
			ConfigurationMapLandregionLevel configurationMapLandregionLevel3 = new ConfigurationMapLandregionLevel();
			ConfigurationMapLandregionLevel configurationMapLandregionLevel4 = new ConfigurationMapLandregionLevel();
			if (viewConfigMapLandRegionList!=null) {
				configurationMapLandregionLevel1.setIsactive(true);
				configurationMapLandregionLevel1.setConfigurationMapLandregion(configmaplandregion);
				configurationMapLandregionLevel1.setLandregionType('S');
				configurationMapLandregionLevel1.setIsmapupload(true);
				for (ViewConfigMapLandRegion viewConfigMapLandRegion : viewConfigMapLandRegionList) {
					if (viewConfigMapLandRegion.getLandregiontype() == 'S' && viewConfigMapLandRegion.getIsmapupload()==false) {
						configurationMapLandregionLevel1.setBaseUrl(viewConfigMapLandRegion.getIsbaseUrl());
						configurationMapLandregionLevel1.setIsmapupload(false);
					}
				}
				configurationMapLandregionLevel2.setIsactive(true);
				configurationMapLandregionLevel2.setConfigurationMapLandregion(configmaplandregion);
				configurationMapLandregionLevel2.setLandregionType('D');
				configurationMapLandregionLevel2.setIsmapupload(true);
					for (ViewConfigMapLandRegion viewConfigMapLandRegion : viewConfigMapLandRegionList) {
						if (viewConfigMapLandRegion.getLandregiontype() == 'D' && viewConfigMapLandRegion.getIsmapupload()==false) {
							configurationMapLandregionLevel2.setBaseUrl(viewConfigMapLandRegion.getIsbaseUrl());
							configurationMapLandregionLevel2.setIsmapupload(false);
						}
					}
				configurationMapLandregionLevel3.setIsactive(true);
				configurationMapLandregionLevel3.setConfigurationMapLandregion(configmaplandregion);
				configurationMapLandregionLevel3.setLandregionType('T');
				configurationMapLandregionLevel3.setIsmapupload(true);
				for (ViewConfigMapLandRegion viewConfigMapLandRegion : viewConfigMapLandRegionList) {
					if (viewConfigMapLandRegion.getLandregiontype() == 'T' && viewConfigMapLandRegion.getIsmapupload()==false) {
						configurationMapLandregionLevel3.setBaseUrl(viewConfigMapLandRegion.getIsbaseUrl());
						configurationMapLandregionLevel3.setIsmapupload(false);
					}
				}
				
				configurationMapLandregionLevel4.setIsactive(true);
				configurationMapLandregionLevel4.setConfigurationMapLandregion(configmaplandregion);
				configurationMapLandregionLevel4.setLandregionType('V');
				configurationMapLandregionLevel4.setIsmapupload(true);
				for (ViewConfigMapLandRegion viewConfigMapLandRegion : viewConfigMapLandRegionList) {
					if (viewConfigMapLandRegion.getLandregiontype() == 'V' && viewConfigMapLandRegion.getIsmapupload()==false) {
						configurationMapLandregionLevel4.setBaseUrl(viewConfigMapLandRegion.getIsbaseUrl());
						configurationMapLandregionLevel4.setIsmapupload(false);
					}
				}
			}
			configurationMapLandregionLevelsSet.add(configurationMapLandregionLevel1);
			configurationMapLandregionLevelsSet.add(configurationMapLandregionLevel2);
			configurationMapLandregionLevelsSet.add(configurationMapLandregionLevel3);
			configurationMapLandregionLevelsSet.add(configurationMapLandregionLevel4);
			configmaplandregion.setConfigurationMapLandregionLevels(configurationMapLandregionLevelsSet);
			//configureMapLandRegionCode = 
			configMapLandRegionDAO.saveOrUpdate(configmaplandregion);
		}
		return true;
	}
	
	//@Override
	public boolean lgdmUpdate1(ConfigureMapForm configureMapForm, int stateCode,
			HttpSession httpSession) throws Exception {
		// TODO lgdmUpdate
		Date currentDate = dateTimeUtil.getCurrentDate();
		//boolean temp = false;
		//boolean success = false;
		//int stateVersion = 0;
		int configurationMapLandregionCode = 0;
		//stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		//List<State> stateList = null;
		//stateList = new ArrayList<State>();
		int slc=commonService.getSlc(stateCode);
		/*State state = null;
		state = new State();
		StatePK statePK = null;
		statePK = new StatePK(stateCode, stateVersion);*/
		/*stateList = stateDao
				.getStateList("from State s where s.statePK.stateCode="
						+ stateCode + " and s.statePK.stateVersion="
						+ stateVersion + " and isactive=true");*/
		//state.setStatePK(statePK);
		//System.out.println("current date---------------------------"
		//		+ currentDate);
		ConfigurationMapLandregion configurationMapLandregion = null;
		ConfigurationMapLandregion configurationMapLandregioninactive = null;
		//ConfigurationMapLandregionLevel configurationMapLandregionLevelinactive = null;
		//List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
		//viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
		//viewConfigMapLandRegion = (List<ViewConfigMapLandRegion>) httpSession.getAttribute("viewConfigMapLandRegion");

		List<ConfigurationMapLandregion> configurationMapLandregionList = null;
		configurationMapLandregionList = new ArrayList<ConfigurationMapLandregion>();
		configurationMapLandregionList = configMapLandRegionDAO
				.getConfigurationDetails(stateCode);
		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		// state update
		for (int i = 0; i < configurationMapLandregionList.size(); i++) {

			configurationMapLandregionCode = configurationMapLandregionList
					.get(i).getConfigurationMapLandregionCode();

			configurationMapLandregioninactive = new ConfigurationMapLandregion();
			
			configurationMapLandregioninactive.setSlc(slc);
/*			configurationMapLandregioninactive
					.setIsmapupload(configurationMapLandregionList.get(i)
							.isIsmapupload());*/
			configurationMapLandregioninactive
					.setConfigurationMapLandregionCode(configurationMapLandregionCode);
			configurationMapLandregioninactive.setIsactive(false);
			configurationMapLandregioninactive.setLastupdated(currentDate);
			configurationMapLandregioninactive.setLastupdatedby(userId);
			configurationMapLandregioninactive
					.setCreatedon(configurationMapLandregionList.get(i)
							.getCreatedon());
			configurationMapLandregioninactive
					.setCreatedby(configurationMapLandregionList.get(i)
							.getCreatedby());

			//temp = 
			configMapLandRegionDAO.updateisActive(
					configurationMapLandregioninactive, session);
		}

		char type;

		configurationMapLandregion = new ConfigurationMapLandregion();
		try {

			for (int i = 0; i < configureMapForm.getViewConfigMapLandRegion()
					.size(); i++) {
				type = configureMapForm.getViewConfigMapLandRegion().get(i)
						.getLandregiontype();
				if (type == 'S') {
					configurationMapLandregion.setSlc(slc);
/*					configurationMapLandregion
							.setBaseUrl(configureMapForm
									.getViewConfigMapLandRegion().get(i)
									.getIsbaseUrl());
					configurationMapLandregion.setIsmapupload(configureMapForm
							.getViewConfigMapLandRegion().get(i)
							.getIsmapupload());*/
					configurationMapLandregion.setIsactive(true);
					configurationMapLandregion.setLastupdated(currentDate);
					configurationMapLandregion.setLastupdatedby(userId);
					configurationMapLandregion.setCreatedon(currentDate);
					configurationMapLandregion.setCreatedby(userId);
					configurationMapLandregionCode = configMapLandRegionDAO
							.saveWithSession(configurationMapLandregion,
									session);
				}
			}

			// landRegionLevelUpdate
			for (int i = 0; i < configureMapForm.getViewConfigMapLandRegion()
					.size(); i++) {
				type = configureMapForm.getViewConfigMapLandRegion().get(i)
						.getLandregiontype();

				if (type == 'D') {
					//configurationMapLandregionLevelinactive = new ConfigurationMapLandregionLevel();
					configMapLandRegionLevelDAO.updateIsActiveLevel(
							configureMapForm.getViewConfigMapLandRegion()
									.get(i).getId(), session);
				}
				if (type == 'T') {
					//configurationMapLandregionLevelinactive = new ConfigurationMapLandregionLevel();
					configMapLandRegionLevelDAO.updateIsActiveLevel(
							configureMapForm.getViewConfigMapLandRegion()
									.get(i).getId(), session);

				}
				if (type == 'V') {
					//configurationMapLandregionLevelinactive = new ConfigurationMapLandregionLevel();
					configMapLandRegionLevelDAO.updateIsActiveLevel(
							configureMapForm.getViewConfigMapLandRegion()
									.get(i).getId(), session);
				}
			}
			ConfigurationMapLandregionLevel configurationMapLandregionLevel = null;
			for (int i = 0; i < configureMapForm.getViewConfigMapLandRegion()
					.size(); i++) {
				if (configureMapForm.getViewConfigMapLandRegion().get(i)
						.getLandregiontype() == 'D') {
					configurationMapLandregionLevel = new ConfigurationMapLandregionLevel();
					configurationMapLandregionLevel.setIsactive(true);
					configurationMapLandregion = new ConfigurationMapLandregion();
					configurationMapLandregion = (ConfigurationMapLandregion) session
							.get(ConfigurationMapLandregion.class,
									configurationMapLandregionCode);
					configurationMapLandregionLevel
							.setConfigurationMapLandregion(configurationMapLandregion);
					configurationMapLandregionLevel
							.setBaseUrl(configureMapForm
									.getViewConfigMapLandRegion().get(i)
									.getIsbaseUrl());
					configurationMapLandregionLevel
							.setIsmapupload(configureMapForm
									.getViewConfigMapLandRegion().get(i)
									.getIsmapupload());
					configurationMapLandregionLevel.setLandregionType('D');
					configMapLandRegionLevelDAO.savewithsession(
							configurationMapLandregionLevel, session);
				} else if (configureMapForm.getViewConfigMapLandRegion().get(i)
						.getLandregiontype() == 'T') {
					configurationMapLandregionLevel = new ConfigurationMapLandregionLevel();
					configurationMapLandregionLevel.setIsactive(true);
					configurationMapLandregion = new ConfigurationMapLandregion();
					configurationMapLandregion = (ConfigurationMapLandregion) session
							.get(ConfigurationMapLandregion.class,
									configurationMapLandregionCode);
					configurationMapLandregionLevel
							.setConfigurationMapLandregion(configurationMapLandregion);
					configurationMapLandregionLevel
							.setBaseUrl(configureMapForm
									.getViewConfigMapLandRegion().get(i)
									.getIsbaseUrl());
					configurationMapLandregionLevel
							.setIsmapupload(configureMapForm
									.getViewConfigMapLandRegion().get(i)
									.getIsmapupload());
					configurationMapLandregionLevel.setLandregionType('T');
					configMapLandRegionLevelDAO.savewithsession(
							configurationMapLandregionLevel, session);

				} else if (configureMapForm.getViewConfigMapLandRegion().get(i)
						.getLandregiontype() == 'V') {
					configurationMapLandregionLevel = new ConfigurationMapLandregionLevel();
					configurationMapLandregionLevel.setIsactive(true);

					configurationMapLandregion = new ConfigurationMapLandregion();
					configurationMapLandregion = (ConfigurationMapLandregion) session
							.get(ConfigurationMapLandregion.class,
									configurationMapLandregionCode);
					configurationMapLandregionLevel
							.setConfigurationMapLandregion(configurationMapLandregion);
					configurationMapLandregionLevel
							.setBaseUrl(configureMapForm
									.getViewConfigMapLandRegion().get(i)
									.getIsbaseUrl());
					configurationMapLandregionLevel
							.setIsmapupload(configureMapForm
									.getViewConfigMapLandRegion().get(i)
									.getIsmapupload());
					configurationMapLandregionLevel.setLandregionType('V');
					configMapLandRegionLevelDAO.savewithsession(
							configurationMapLandregionLevel, session);
				}
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	// newnew
	@Override
	public List<ViewConfigMapLandRegion> checkMapConfiguration(int stateCode) throws Exception {		
		List<ViewConfigMapLandRegion> getConfigMapLandRegion = null;
		List<ViewConfigMapLandRegion> getConfigMapLandRegion1 = null;
		getConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
		getConfigMapLandRegion = configMapLandRegionDAO.GovOrderDetail(stateCode);
		getConfigMapLandRegion1 = new ArrayList<ViewConfigMapLandRegion>();	
		char typeOrder[]={'S','D','T','V','B'};
		for(int x=0; x<typeOrder.length; x++){
			for (int i = 0; i < getConfigMapLandRegion.size(); i++) {
				ViewConfigMapLandRegion viewConfig = (ViewConfigMapLandRegion) getConfigMapLandRegion.get(i);
				char type = getConfigMapLandRegion.get(i).getLandregiontype();
				if(type == typeOrder[x]){
					getConfigMapLandRegion1.add(viewConfig);
					break;
				}
			}
		}
		
		return getConfigMapLandRegion1;
	}
	
	/*public Map<String,String> checkMapConfigurationforConstituency(int stateCode,char constituencyType) throws Exception
	{
		return configMapLandRegionDAO.checkMapConfigurationforConstituency(stateCode, constituencyType);
	}*/

	@Override
	public List<ViewConfigMapLandRegion> checkMapConfigurationModify(
			int stateCode, int operationCode, char type) throws Exception {
		// TODO checkMapConfiguration
		List<ViewConfigMapLandRegion> getConfigMapLandRegion = null;
		List<ViewConfigMapLandRegion> getConfigMapLandRegion1 = null;
		getConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();

		getConfigMapLandRegion = configMapLandRegionDAO
				.GovOrderDetail(stateCode);
		getConfigMapLandRegion1 = new ArrayList<ViewConfigMapLandRegion>();
		
			for (int i = 0; i < getConfigMapLandRegion.size(); i++) {
				getConfigMapLandRegion1.add(null);
			}
			
		for (int i = 0; i < getConfigMapLandRegion.size(); i++) {
			ViewConfigMapLandRegion viewConfig = (ViewConfigMapLandRegion) getConfigMapLandRegion
					.get(i);
			// char typed = getConfigMapLandRegion.get(i).getLandregiontype();
			if (type ==  viewConfig.getLandregiontype()) {
				getConfigMapLandRegion1.set(0, viewConfig);
			} /*else if (type == typed) {
				getConfigMapLandRegion1.set(1, viewConfig);
			} else if (type ==  typed) {
				getConfigMapLandRegion1.set(2, viewConfig);
			} else if (type ==  typed) {
				getConfigMapLandRegion1.set(3, viewConfig);
			} else if (type ==  typed) {
				getConfigMapLandRegion1.set(4, viewConfig);
			}*/
		}
		
	
		
		return getConfigMapLandRegion1;
	}

	@Override
	public boolean lgdmconstituencyMapUpdate(ConfigureMapForm configureMapForm,
			int stateCode, HttpSession session) throws Exception {
		// TODO lgdmconstituencyMapUpdate
		Date currentDate = dateTimeUtil.getCurrentDate();
		//boolean temp = false;
		//boolean success = false;
		int stateVersion = 0;
		stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		int slc=commonService.getSlc(stateCode);
		//List<State> stateList = null;
		//stateList = new ArrayList<State>();
		State state = null;
		state = new State();
		StatePK statePK = null;
		statePK = new StatePK(stateCode, stateVersion);
		/*stateList = stateDao
				.getStateList("from State s where s.statePK.stateCode="
						+ stateCode + " and s.statePK.stateVersion="
						+ stateVersion + " and isactive=true");*/
		state.setStatePK(statePK);
		state.setSlc(slc);
		//ConfigurationMapConstituency configurationMapConstituencyinactive = null;
		ConfigurationMapConstituency configurationMapConstituency = null;

		Session session2 = null;
		Transaction tx = null;
		session2 = sessionFactory.openSession();
		tx = session2.beginTransaction();
		char type;
		try {
			for (int j = 0; j < configureMapForm
					.getListConfigurationMapConstituency().size(); j++) {
				type = configureMapForm.getListConfigurationMapConstituency()
						.get(j).getConstituencyType();

				if (type == 'P') {
					//configurationMapConstituencyinactive = new ConfigurationMapConstituency();
					configMapConstituencyDAO.updateIsActiveLevel(
							configureMapForm
									.getListConfigurationMapConstituency()
									.get(j)
									.getConfigurationMapConstituencyCode(),
							session2);
				}
				if (type == 'A') {
					//configurationMapConstituencyinactive = new ConfigurationMapConstituency();
					configMapConstituencyDAO.updateIsActiveLevel(
							configureMapForm
									.getListConfigurationMapConstituency()
									.get(j)
									.getConfigurationMapConstituencyCode(),
							session2);

				}
			}
			for (int i = 0; i < configureMapForm
					.getListConfigurationMapConstituency().size(); i++) {
				type = configureMapForm.getListConfigurationMapConstituency()
						.get(i).getConstituencyType();
				if (type == 'P') {
					configurationMapConstituency = new ConfigurationMapConstituency();
					statePK.setStateCode(stateCode);
					statePK.setStateVersion(stateVersion);
					state.setStatePK(statePK);
					//configurationMapConstituency.setState(state);
					configurationMapConstituency.setBaseUrl(configureMapForm
							.getListConfigurationMapConstituency().get(i)
							.getBaseUrl());
					configurationMapConstituency
							.setIsmapupload(configureMapForm
									.getListConfigurationMapConstituency()
									.get(i).isIsmapupload());

					/*
					 * configurationMapConstituencyCode =
					 * configMapConstituencyDAO .getMaxRecords(
					 * "select max(configuration_map_constituency_code) from configuration_map_constituency"
					 * ); System.out.println(
					 * "CODE-configurationMapConstituencyCode-id--" +
					 * configurationMapConstituencyCode); if
					 * (configurationMapConstituencyCode == 0) {
					 * configurationMapConstituencyCode = 1; } else {
					 * configurationMapConstituencyCode =
					 * configurationMapConstituencyCode + 1; }
					 */

					// configurationMapConstituency.setConfigurationMapConstituencyCode(configurationMapConstituencyCode);
					// configurationMapConstituency.setConfigurationMapConstituencyCode(configureMapForm.getListConfigurationMapConstituency().get(i).getConfigurationMapConstituencyCode());
					configurationMapConstituency.setIsactive(true);
					configurationMapConstituency.setLastupdated(currentDate);
					configurationMapConstituency.setConstituencyType('P');
					configurationMapConstituency
							.setLastupdatedby(userId);
					configurationMapConstituency.setCreatedon(currentDate);
					configurationMapConstituency.setCreatedby(userId);
					configurationMapConstituency.setSlc(slc);
					configMapConstituencyDAO.savewithsession(
							configurationMapConstituency, session2);
					// configMapConstituencyDAO.update(configurationMapConstituency);
				}
				if (type == 'A') {
					configurationMapConstituency = new ConfigurationMapConstituency();
					/*statePK.setStateCode(stateCode);
					statePK.setStateVersion(stateVersion);
					state.setStatePK(statePK);*/
					//configurationMapConstituency.setState(state);
					configurationMapConstituency.setBaseUrl(configureMapForm
							.getListConfigurationMapConstituency().get(i)
							.getBaseUrl());
					configurationMapConstituency
							.setIsmapupload(configureMapForm
									.getListConfigurationMapConstituency()
									.get(i).isIsmapupload());
					/*
					 * configurationMapConstituencyCode =
					 * configMapConstituencyDAO .getMaxRecords(
					 * "select max(configuration_map_constituency_code) from configuration_map_constituency"
					 * ); System.out.println(
					 * "CODE-configurationMapConstituencyCode-id--" +
					 * configurationMapConstituencyCode); if
					 * (configurationMapConstituencyCode == 0) {
					 * configurationMapConstituencyCode = 1; } else {
					 * configurationMapConstituencyCode =
					 * configurationMapConstituencyCode + 1; }
					 */
					// configurationMapConstituencyCode++;
					// configurationMapConstituency.setConfigurationMapConstituencyCode(configurationMapConstituencyCode);
					// configurationMapConstituency.setConfigurationMapConstituencyCode(configureMapForm.getListConfigurationMapConstituency().get(i).getConfigurationMapConstituencyCode());
					configurationMapConstituency.setIsactive(true);
					configurationMapConstituency.setLastupdated(currentDate);
					configurationMapConstituency.setConstituencyType('A');
					configurationMapConstituency
							.setLastupdatedby(userId);
					configurationMapConstituency.setCreatedon(currentDate);
					configurationMapConstituency.setCreatedby(userId);
					configurationMapConstituency.setSlc(slc);
					configMapConstituencyDAO.savewithsession(
							configurationMapConstituency, session2);
					// configMapConstituencyDAO.update(configurationMapConstituency);
				}
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			if (session2 != null && session2.isOpen()) {
				session2.close();
			}
		}
		return true;
	}
	
	
	@Override
	public boolean blockInsert(ConfigureMapForm configuremapform,
			int stateCode, HttpSession httpSession) throws Exception {

		
		Date currentDate = dateTimeUtil.getCurrentDate();
		//int stateVersion = 0;
		//stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		ConfigurationMapLandregion configmaplandregion = new ConfigurationMapLandregion();

			
		//int configureMapLandRegionCode = 0;
		
		configmaplandregion.setIsactive(true);
	
		/*	State state = new State();
		StatePK statePK = new StatePK();
		statePK.setStateCode(stateCode);
		statePK.setStateVersion(stateVersion);
		state.setStatePK(statePK);
		configmaplandregion.setState(state);*/
		int slc=commonService.getSlc(stateCode);
		configmaplandregion.setSlc(slc);
		//System.out.println("state code" + stateCode);
		configmaplandregion.setLastupdated(currentDate);
		configmaplandregion.setLastupdatedby(userId);
		configmaplandregion.setCreatedon(currentDate);
		configmaplandregion.setCreatedby( userId);

		
		ConfigurationMapLandregionLevel configurationMapLandregionLevel1 = new ConfigurationMapLandregionLevel();
		
		
		configurationMapLandregionLevel1.setIsactive(true);
		configurationMapLandregionLevel1.setConfigurationMapLandregion(configmaplandregion);
		configurationMapLandregionLevel1.setLandregionType('B');
		configurationMapLandregionLevel1.setIsmapupload(true);

		if(configuremapform.getBaseUrlState() !=null && configuremapform.getBaseUrlState().length() > 0
				&& configuremapform.isIsmapuploadState() == false)
		{
			configurationMapLandregionLevel1.setBaseUrl(configuremapform.getBaseUrlState());
			configurationMapLandregionLevel1.setIsmapupload(false);
			
		}
		
		Set<ConfigurationMapLandregionLevel> set = new HashSet<ConfigurationMapLandregionLevel>();
		set.add(configurationMapLandregionLevel1);
		
		configmaplandregion.setConfigurationMapLandregionLevels(set);
		//configureMapLandRegionCode = 
		configMapLandRegionDAO.save(configmaplandregion);
		
		return true;

		
	}

	@Override
	public boolean blockUpdate(ConfigureMapForm configureMapForm,
			int stateCode, HttpSession session) throws Exception {
		
		//Date currentDate = dateTimeUtil.getCurrentDate();
		//int stateVersion = 0;
		//stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		List<ConfigurationMapLandregion> configurationMapLandregionList=null;
		
		configurationMapLandregionList=configMapLandRegionLevelDAO.getMapConfigofState(stateCode);
		ConfigurationMapLandregion configmaplandregion = null;
		
		Integer configurationMapLandregionCode=null;
		if(session.getAttribute("configurationMapLandregionCode")!=null)
			configurationMapLandregionCode=Integer.parseInt(session.getAttribute("configurationMapLandregionCode").toString());	
		else
			configurationMapLandregionCode=0;
		
		if (configurationMapLandregionList!=null) {
			int i=0;
			
			for(ConfigurationMapLandregion configLand : configurationMapLandregionList )
			{
				if(configLand.getConfigurationMapLandregionCode()==configurationMapLandregionCode)
				{
					configmaplandregion = configurationMapLandregionList.get(i);
				}
				i++;
			}
			
	
			Set<ConfigurationMapLandregionLevel> configurationMapLandregionLevelsSet = null;
			configurationMapLandregionLevelsSet = configmaplandregion.getConfigurationMapLandregionLevels();
			
			for(ConfigurationMapLandregionLevel configurationMapLandregionLevel : configurationMapLandregionLevelsSet ){
				configurationMapLandregionLevel.setIsactive(false);
			}
			
			//int configureMapLandRegionCode = 0;
			List<ViewConfigMapLandRegion> viewConfigMapLandRegionList=null;
			viewConfigMapLandRegionList=configureMapForm.getViewConfigMapLandRegion();
			ConfigurationMapLandregionLevel configurationMapLandregionLevel1 = new ConfigurationMapLandregionLevel();
			
			
			if (viewConfigMapLandRegionList!=null) {
				configurationMapLandregionLevel1.setIsactive(true);
				configurationMapLandregionLevel1.setConfigurationMapLandregion(configmaplandregion);
				configurationMapLandregionLevel1.setLandregionType('B');
				configurationMapLandregionLevel1.setIsmapupload(true);
				for (ViewConfigMapLandRegion viewConfigMapLandRegion : viewConfigMapLandRegionList) {
					if (viewConfigMapLandRegion.getLandregiontype() == 'B' && viewConfigMapLandRegion.getIsmapupload()==false) {
						configurationMapLandregionLevel1.setBaseUrl(viewConfigMapLandRegion.getIsbaseUrl());
						configurationMapLandregionLevel1.setIsmapupload(false);
					}
				}
				
					
				
			configurationMapLandregionLevelsSet.add(configurationMapLandregionLevel1);
			
			configmaplandregion.setConfigurationMapLandregionLevels(configurationMapLandregionLevelsSet);
			//configureMapLandRegionCode = 
			configMapLandRegionDAO.saveOrUpdate(configmaplandregion);
		}
		
		}
		return true;
	}
	
	@Override
	public boolean saveLGDMMapConfiguration(ConfigureMapForm configMapLGDMForm,
			HttpServletRequest request, HttpSession httpSession) throws Exception {
		ConfigurationMapLocalbody configurationMapLocalbody = null;
		if (configMapLGDMForm != null) {
			String configMapLGDM[] = null;

			 
			boolean uploadMap = false;
			String urlMap = "";
			StringBuilder sb = null;
			sb = new StringBuilder();
			String tierCode = configMapLGDMForm.getTierSetupCode();
			if (configMapLGDMForm.getBaseUrl().contains(",")) {
				configMapLGDM = configMapLGDMForm.getBaseUrl().split(",");
			}
			for (int i = 1; i <= configMapLGDMForm.getTierSetupSize(); i++) {
				configurationMapLocalbody = new ConfigurationMapLocalbody();
				String uploadConfig = (String) request.getParameter("upload"
						+ i);

				if (uploadConfig.equals("yes")) {
					configurationMapLocalbody.setIsmapupload(true);
					uploadMap = true;
					urlMap = "";
				} else {
					configurationMapLocalbody.setIsmapupload(false);
					configurationMapLocalbody.setBaseUrl(configMapLGDM[i - 1]);
					uploadMap = false;
					if (configMapLGDM.length >= i){
						urlMap = configMapLGDM[i - 1];
					}	
				}
				sb.append(urlMap + "~" + uploadMap + ";");
			}
			String mapURL = sb.toString().substring(0, sb.length() - 1);
			configMapLocalBodyDAO.ConfigMapLgdm(tierCode, mapURL, 2);

		}
		return true;
	}

	@Override
	public boolean updateLGDMMapConfiguration(
			ConfigureMapForm configMapLGDMForm, int stateCode,
			HttpServletRequest request, HttpSession httpSession) throws Exception {
		if (configMapLGDMForm != null) {
			boolean uploadMap = false;
			String urlMap = "";

			StringBuilder sb = null;
			sb = new StringBuilder();
			String tierCode = configMapLGDMForm.getTierSetupCode();
			for (int i = 0; i < configMapLGDMForm.getLstdetail().size(); i++) {
				if (configMapLGDMForm.getLstdetail().get(i).isIsmapupload() == true) {
					uploadMap = true;
					urlMap = "";
				} else {
					uploadMap = false;
					urlMap = configMapLGDMForm.getLstdetail().get(i)
							.getBase_url();
				}
				sb.append(urlMap + "~" + uploadMap + ";");
			}
			String mapURL = sb.toString().substring(0, sb.length() - 1);
			configMapLocalBodyDAO.ConfigMapLgdm(tierCode, mapURL, 2);
		}
		return true;
	}

	@Override
	public boolean save(ConfigurationMapLocalbody configurationMapLocalbody) throws Exception {
		// TODO Auto-generated method stub
		return configMapLocalBodyDAO.save(configurationMapLocalbody);
	}
}
