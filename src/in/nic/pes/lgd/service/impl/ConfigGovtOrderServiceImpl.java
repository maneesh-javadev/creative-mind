package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.ConfigurationGovernmentOrder;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.ViewConfigMapLandRegion;
import in.nic.pes.lgd.dao.ConfigGovtOrderDAO;
import in.nic.pes.lgd.dao.LocalBodySetupDAO;
import in.nic.pes.lgd.dao.NomenclatureSubDistDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.AdministratorUnit;
import in.nic.pes.lgd.forms.ConfigGovtOrderForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.ConfigMapService;
import in.nic.pes.lgd.utils.CurrentDateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfigGovtOrderServiceImpl implements ConfigGovtOrderService {
	private static final Logger LOG = Logger.getLogger(ConfigGovtOrderServiceImpl.class);
	
	@Autowired
	private ConfigGovtOrderDAO configGovtOrderDAO;

	@Autowired
	private StateDAO stateDao;

	@Autowired
	private NomenclatureSubDistDAO nomenclatureSubDistDAO;

	@Autowired
	private ConfigMapService configMapService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private LocalBodySetupDAO localBodySetupDAO;

	@Autowired
	private CommonService commonService;
	// @Autowired
	// SessionObject sessionObject;

	/*
	 * int stateCode=35; int stateVersion=1;
	 */
	// int roleCode=25;

	
	private List<Operations> OperationList = new ArrayList<Operations>();
	private List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();
	
	private Long userId=0L;
	
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		userId = sessionObject.getUserId();
		
	}

	@Transactional
	public boolean configGovtOrder(ConfigGovtOrderForm configGovtOrderForm, int slc) throws Exception {
		// TODO to insert The Central Admin
		
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;

		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		int stateVersion = 0;
		stateVersion = stateDao.getCurrentVersionbyStateCode(slc);
		configurationGovernmentOrderBean.setIsactive(true);

		State state = null;
		StatePK statePK = null;
		boolean success = false;

		while (itr.hasNext()) {

			AdministratorUnit element = (AdministratorUnit) itr.next();
			//System.out.println("Value--configOrder--" + element.getOperationBlockValue());
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());
			state = new State();
			statePK = new StatePK();
			statePK.setStateCode(slc);
			statePK.setStateVersion(stateVersion);
			state.setStatePK(statePK);
			state.setSlc(slc);
			configurationGovernmentOrderBean.setSlc(slc);
			// configurationGovernmentOrderBean.setState(state);
			configurationGovernmentOrderBean.setIsactive(true);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			idGovtOrder = idGovtOrder + 1;
			configurationGovernmentOrderBean.setId(idGovtOrder);

			try {
				success = configGovtOrderDAO.save(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}
		}
		if (success)
			return true;
		else
			return false;
	}

	/*@Override
	public boolean lrmMethod(ConfigGovtOrderForm configGovtOrderForm) throws Exception {
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");
		//System.out.println("CODE--LandRegion-else if-" + idGovtOrder);

		//System.out.println("Size--" + configGovtOrderForm.getListAdminUnits().size());

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		int stateCode = 0;
		int stateVersion = 0;
		while (itr.hasNext()) {

			AdministratorUnit element = (AdministratorUnit) itr.next();
			//System.out.println("Value--lrmMethod--" + element.getOperationBlockValue());
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());

			configurationGovernmentOrderBean.setIsactive(true);
			// configurationGovernmentOrderBean.setRoleCode(roleCode);
			// configurationGovernmentOrderBean.setStateCode(stateCode);
			// configurationGovernmentOrderBean.setStateVersion(stateVersion);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			idGovtOrder = idGovtOrder + 1;
			configurationGovernmentOrderBean.setId(idGovtOrder);

			try {
				configGovtOrderDAO.saveAdminLandRegion(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}

		}

		return false;
	}*/

	/*@Override
	public boolean adminOrder(ConfigGovtOrderForm configGovtOrderForm) throws Exception {
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");
		//System.out.println("CODE--Admin-else if-" + idGovtOrder);

		//System.out.println("Size--" + configGovtOrderForm.getListAdminUnits().size());

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		int stateCode = 0;
		int stateVersion = 0;
		while (itr.hasNext()) {

			AdministratorUnit element = (AdministratorUnit) itr.next();
			//System.out.println("Value-Admin---" + element.getOperationBlockValue());
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());

			configurationGovernmentOrderBean.setIsactive(true);
			// configurationGovernmentOrderBean.setRoleCode(roleCode);
			// configurationGovernmentOrderBean.setStateCode(stateCode);
			// configurationGovernmentOrderBean.setStateVersion(stateVersion);
			idGovtOrder = idGovtOrder + 1;
			configurationGovernmentOrderBean.setId(idGovtOrder);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);

			try {
				configGovtOrderDAO.saveAdminBlock(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}

		}

		return true;
	}*/

	/*@Override
	public boolean constituencyOrder(ConfigGovtOrderForm configGovtOrderForm) throws Exception {
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();

		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");
		//System.out.println("CODE--Constituency-else if-" + idGovtOrder);

		//System.out.println("Size--" + configGovtOrderForm.getListAdminUnits().size());

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		int stateCode = 0;
		int stateVersion = 0;
		while (itr.hasNext()) {

			AdministratorUnit element = (AdministratorUnit) itr.next();
			//System.out.println("Value-constituency---" + element.getOperationBlockValue());
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());

			configurationGovernmentOrderBean.setIsactive(true);
			// configurationGovernmentOrderBean.setRoleCode(roleCode);
			// configurationGovernmentOrderBean.setStateCode(stateCode);
			// configurationGovernmentOrderBean.setStateVersion(stateVersion);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			idGovtOrder = idGovtOrder + 1;
			configurationGovernmentOrderBean.setId(idGovtOrder);

			try {
				configGovtOrderDAO.saveAdminConstituency(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}
		}
		return false;
	}*/

	public Integer getSlcfromState(int stateCode, int stateVersion) throws Exception {
		int slc;
		String hql = null;
		hql = "select slc from state where  state_code=" + stateCode + " and state_version=" + stateVersion;
		slc = localBodySetupDAO.getSlcfromStateDAO(hql);

		return slc;
	}

	// ################ Insert configuration government order for PRI open
	// ###########
	@Override
	public boolean configInsertPRI(ConfigGovtOrderForm configGovtOrderForm, int slc, HttpSession httpSession) throws Exception {
		setGlobalParams(httpSession);
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();
	
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		//boolean flag = false;
		while (itr.hasNext()) {

			idGovtOrder = idGovtOrder + 1;

			AdministratorUnit element = (AdministratorUnit) itr.next();

			configurationGovernmentOrderBean.setIsactive(true);
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());
			configurationGovernmentOrderBean.setSlc(slc);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			configurationGovernmentOrderBean.setId(idGovtOrder);

			try {

				//flag = 
				this.setIsActive(slc, element.getOperationBlockValue());

				configGovtOrderDAO.saveAdminPRI(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}

		}
		return true;
	}

	// ################ Insert configuration government order for PRI Close
	// ###########

	// ################ Insert configuration government order for Trade open
	// ###########
	@Override
	public boolean configInsertTrade(ConfigGovtOrderForm configGovtOrderForm, int slc, HttpSession httpSession) throws Exception {
		setGlobalParams(httpSession);
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

		
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		//boolean flag = false;
		while (itr.hasNext()) {

			idGovtOrder = idGovtOrder + 1;

			AdministratorUnit element = (AdministratorUnit) itr.next();

			configurationGovernmentOrderBean.setIsactive(true);
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());
			configurationGovernmentOrderBean.setSlc(slc);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			configurationGovernmentOrderBean.setId(idGovtOrder);

			try {
				//flag = 
				this.setIsActive(slc, element.getOperationBlockValue());
				configGovtOrderDAO.saveAdminTrade(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}

		}
		return true;
	}

	// ################ Insert configuration government order for Trade Close
	// ###########

	// ################ Insert configuration government order for Urban open
	// ###########
	@Override
	public boolean configInsertUrban(ConfigGovtOrderForm configGovtOrderForm, int slc, HttpSession httpSession) throws Exception {
		setGlobalParams(httpSession);
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

			
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		//boolean flag = false;
		while (itr.hasNext()) {

			idGovtOrder = idGovtOrder + 1;

			AdministratorUnit element = (AdministratorUnit) itr.next();

			configurationGovernmentOrderBean.setIsactive(true);
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());
			configurationGovernmentOrderBean.setSlc(slc);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			configurationGovernmentOrderBean.setId(idGovtOrder);

			try {
				//flag = 
				this.setIsActive(slc, element.getOperationBlockValue());
				configGovtOrderDAO.saveAdminUrban(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}

		}
		return true;
	}

	// ################ Insert configuration government order for Urban Close
	// ###########

	// ################ Insert configuration government order for LandRegion
	// open ###########
	@Override
	public boolean configInsertLandRegion(ConfigGovtOrderForm configGovtOrderForm, int slc, HttpSession httpSession) throws Exception {
		setGlobalParams(httpSession);
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

			
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		//boolean flag = false;
		while (itr.hasNext()) {

			idGovtOrder = idGovtOrder + 1;

			AdministratorUnit element = (AdministratorUnit) itr.next();

			configurationGovernmentOrderBean.setIsactive(true);
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());
			configurationGovernmentOrderBean.setSlc(slc);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			configurationGovernmentOrderBean.setId(idGovtOrder);
			try {
				//flag = 
				this.setIsActive(slc, element.getOperationBlockValue());
				configGovtOrderDAO.saveAdminLandRegion(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}

		}
		return true;
	}

	// ################ Insert configuration government order for LandRegion
	// Close ###########

	// ################ Insert configuration government order for Center open
	// ###########
	@Override
	public boolean configInsertCenter(ConfigGovtOrderForm configGovtOrderForm, int slc, HttpSession httpSession) throws Exception {
		setGlobalParams(httpSession);
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

		
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		//boolean flag = false;
		while (itr.hasNext()) {

			idGovtOrder = idGovtOrder + 1;

			AdministratorUnit element = (AdministratorUnit) itr.next();

			configurationGovernmentOrderBean.setIsactive(true);
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());
			configurationGovernmentOrderBean.setSlc(slc);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			configurationGovernmentOrderBean.setId(idGovtOrder);
			try {
				//flag = 
				this.setIsActive(slc, element.getOperationBlockValue());
				configGovtOrderDAO.saveAdminCenter(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}

		}
		return true;
	}

	// ################ Insert configuration government order for Center Close
	// ###########

	// ################ Insert configuration government order for constituency
	// open ###########
	@Override
	public boolean configInsertConstituence(ConfigGovtOrderForm configGovtOrderForm, int slc, HttpSession httpSession) throws Exception {
		setGlobalParams(httpSession);
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

			
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		//boolean flag = false;
		while (itr.hasNext()) {

			idGovtOrder = idGovtOrder + 1;

			AdministratorUnit element = (AdministratorUnit) itr.next();

			configurationGovernmentOrderBean.setIsactive(true);
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());
			configurationGovernmentOrderBean.setSlc(slc);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			configurationGovernmentOrderBean.setId(idGovtOrder);
			try {
				//flag = 
				this.setIsActive(slc, element.getOperationBlockValue());
				configGovtOrderDAO.saveAdminConstituency(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}

		}
		return true;
	}

	// ################ Insert configuration government order for Constituency
	// Close ###########

	// ################ Insert configuration government order for Block open
	// ###########
	@Override
	public boolean configInsertBlock(ConfigGovtOrderForm configGovtOrderForm, int slc, HttpSession httpSession) throws Exception {
		setGlobalParams(httpSession);
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

		
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		idGovtOrder = configGovtOrderDAO.getMaxRecords("select COALESCE(max(id),1) from configuration_government_order");

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();
		//boolean flag = false;
		while (itr.hasNext()) {

			idGovtOrder = idGovtOrder + 1;

			AdministratorUnit element = (AdministratorUnit) itr.next();

			configurationGovernmentOrderBean.setIsactive(true);
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());
			configurationGovernmentOrderBean.setSlc(slc);
			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setLastupdatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			configurationGovernmentOrderBean.setId(idGovtOrder);

			try {
				//flag = 
				this.setIsActive(slc, element.getOperationBlockValue());
				configGovtOrderDAO.saveAdminBlock(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			}

		}
		return true;
	}

	// ################ Insert configuration government order for Block Close
	// ###########

	@Override
	public boolean configUpdate(ConfigGovtOrderForm configGovtOrderForm, int slc, HttpSession session) throws Exception {
		setGlobalParams(session);
		ConfigurationGovernmentOrder configurationGovernmentOrderBean = new ConfigurationGovernmentOrder();

		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		int idGovtOrder = 0;
		
		Date createdon = null;
		boolean success = false;
			
		@SuppressWarnings("unchecked")
		List<ConfigurationGovernmentOrder> ConfigurationGovernmentOrderlist = (List<ConfigurationGovernmentOrder>) session.getAttribute("configurationGovernmentOrderList");

		List<AdministratorUnit> listAdmin = new ArrayList<AdministratorUnit>();
		listAdmin = configGovtOrderForm.getListAdminUnits();
		Iterator<AdministratorUnit> itr = listAdmin.iterator();

		configurationGovernmentOrderBean.setSlc(slc);
		while (itr.hasNext()) {

			AdministratorUnit element = (AdministratorUnit) itr.next();
			configurationGovernmentOrderBean.setOperationCode(element.getOperationBlockValue());
			configurationGovernmentOrderBean.setIsgovtorderupload(element.isIsgovtorderuploadBlock());

			for (int j = 0; j < ConfigurationGovernmentOrderlist.size(); j++) {
				if (element.getOperationBlockValue() == ConfigurationGovernmentOrderlist.get(j).getOperationCode()) {
					createdon = ConfigurationGovernmentOrderlist.get(j).getCreatedon();
					userId = ConfigurationGovernmentOrderlist.get(j).getCreatedby();
					idGovtOrder = ConfigurationGovernmentOrderlist.get(j).getId();
				}

			}

			configurationGovernmentOrderBean.setLastupdated(time);
			configurationGovernmentOrderBean.setCreatedby(userId);
			configurationGovernmentOrderBean.setCreatedon(createdon);
			configurationGovernmentOrderBean.setLastupdatedby(userId);

			configurationGovernmentOrderBean.setId(idGovtOrder);
			configurationGovernmentOrderBean.setIsactive(true);

			try {
				success = configGovtOrderDAO.UpdateLRM(configurationGovernmentOrderBean);
			} catch (Exception e) {
				LOG.debug("Exception" + e);
				return false;
			} finally {
				session.setAttribute("configurationGovernmentOrderList", null);
			}
		}
		if (success) {
			return true;
		} else
			return false;
	}

	// ################ Insert and update method configuration government order
	// open ###########

	public boolean setIsActive(int slc, int opcode) throws Exception {
		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		String SQL = null;
		//int i = 0;
		try {

			SQL = "update configuration_government_order set isactive=false where slc=" + slc + " and operation_code=" + opcode + " and isactive=true";

			configGovtOrderDAO.setIsActiveDAO(SQL, hsession);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return false;
		} finally {
			if (hsession != null && hsession.isOpen()) {
				hsession.close();
				sessionFactory.close();
			}
		}
		return true;
	}

	@Override
	public List<Operations> getOperationDetail(String category) throws Exception {
		// TODO Check LRM Config
		List<Operations> OperationDetailList = null;
		OperationDetailList = new ArrayList<Operations>();

		// stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		String Hql = "from Operations where category like '" + category + "' and isactive=true";
		OperationDetailList = configGovtOrderDAO.getOperationDetailDAO(Hql);
		if (OperationDetailList.size() > 0) {
			return OperationDetailList;
		} else
			return null;
	}

	@Override
	public List<ConfigurationGovernmentOrder> checkLRMConfig(int slc, List<Operations> OperationListLand) throws Exception {
		// TODO Check LRM Config

		String tempcode = "";

		List<Operations> OperationList = null;
		OperationList = new ArrayList<Operations>();
		OperationList = (List<Operations>) OperationListLand;
		// ConfigurationGovernmentOrder obj=new ConfigurationGovernmentOrder();
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;
		configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();

		if (OperationListLand == null) {
			configurationGovernmentOrderList = null;
		} else {
			for (Operations operation : OperationList) {
				tempcode = tempcode + operation.getOperationCode() + ",";
			}
			if (tempcode != null) {
				StringBuffer code = new StringBuffer(tempcode);
				code.setCharAt(tempcode.lastIndexOf(','), ' ');
				// obj.setIsactive(true);
				String Hql = "from ConfigurationGovernmentOrder where operationCode in(" + code + ") and slc=" + slc + " and isactive=true order by operationCode";
				configurationGovernmentOrderList = configGovtOrderDAO.getConfigurationGovernmentOrderDetails(Hql);
				if (configurationGovernmentOrderList.size() > 0) {
					return configurationGovernmentOrderList;
				} else {
					configurationGovernmentOrderList = null;
				}

			}

		}

		return configurationGovernmentOrderList;

		/*
		 * List <ConfigurationGovernmentOrder>
		 * configurationGovernmentOrderList=null;
		 * configurationGovernmentOrderList=new
		 * ArrayList<ConfigurationGovernmentOrder>(); int stateVersion=0;
		 * //stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		 * String Hql=
		 * "from ConfigurationGovernmentOrder where operationCode between 3 and 17 and slc='"
		 * +slc+"' and isactive=true order by operationCode";
		 * configurationGovernmentOrderList
		 * =configGovtOrderDAO.getConfigurationGovernmentOrderDetails(Hql);
		 * if(configurationGovernmentOrderList.size()>0) { return
		 * configurationGovernmentOrderList; }else return null;
		 */
	}

	@Override
	public List<ConfigurationGovernmentOrder> checkLRMConfigbyOperationCode(int stateCode, int operationCode) throws Exception {
		// TODO Check LRM Config
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;
		configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();

		// stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		String Hql = "from ConfigurationGovernmentOrder where operationCode='" + operationCode + "' and slc='" + stateCode + "' and isactive=true order by operationCode";
		configurationGovernmentOrderList = configGovtOrderDAO.getConfigurationGovernmentOrderDetails(Hql);
		if (configurationGovernmentOrderList.size() > 0) {
			return configurationGovernmentOrderList;
		} else
			return null;
	}

	public List<ConfigurationGovernmentOrder> checkCentralAdminConfig() throws Exception {
		// TODO Check Configuration of central admin
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();

		int stateCode = 0;
		String Hql = "from ConfigurationGovernmentOrder where operationCode= 1 and isactive=true and slc='" + stateCode + "' order by operationCode";
		configurationGovernmentOrderList = configGovtOrderDAO.getConfigurationGovernmentOrderDetails(Hql);
		if (configurationGovernmentOrderList.size() > 0) {
			return configurationGovernmentOrderList;
		} else {
			configurationGovernmentOrderList = null;
		}

		return configurationGovernmentOrderList;
	}

	/*@Override
	public List<ConfigurationGovernmentOrder> checkCentralAdminConfig(List<Operations> OperationListCenter, int slc) throws Exception {

		String tempcode = "";

		List<Operations> OperationList = null;
		OperationList = new ArrayList<Operations>();
		OperationList = (List<Operations>) OperationListCenter;
		// ConfigurationGovernmentOrder obj=new ConfigurationGovernmentOrder();
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;
		configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();

		if (OperationListCenter == null) {
			configurationGovernmentOrderList = null;
		} else {
			for (Operations operation : OperationList) {
				tempcode = tempcode + operation.getOperationCode() + ",";
			}
			if (tempcode != null) {
				StringBuffer code = new StringBuffer(tempcode);
				code.setCharAt(tempcode.lastIndexOf(','), ' ');
				// obj.setIsactive(true);
				String Hql = "from ConfigurationGovernmentOrder where operationCode in(" + code + ") and slc=" + slc + " and isactive=true order by operationCode";
				configurationGovernmentOrderList = configGovtOrderDAO.getConfigurationGovernmentOrderDetails(Hql);
				if (configurationGovernmentOrderList.size() > 0) {
					return configurationGovernmentOrderList;
				} else {
					configurationGovernmentOrderList = null;
				}

			}

		}

		return configurationGovernmentOrderList;

		
		 * // TODO Check Configuration of central admin List
		 * <ConfigurationGovernmentOrder> configurationGovernmentOrderList=null;
		 * configurationGovernmentOrderList=new
		 * ArrayList<ConfigurationGovernmentOrder>(); int stateVersion=0;
		 * stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		 * String Hql=
		 * "from ConfigurationGovernmentOrder where operationCode between 1 and 2 and state.statePK.stateCode='"
		 * +stateCode+"' and state.statePK.stateVersion='"+stateVersion+
		 * "' order by operationCode";
		 * configurationGovernmentOrderList=configGovtOrderDAO
		 * .getConfigurationGovernmentOrderDetails(Hql);
		 * if(configurationGovernmentOrderList.size()>0) { return
		 * configurationGovernmentOrderList; }else {
		 * configurationGovernmentOrderList=null; }
		 * 
		 * return configurationGovernmentOrderList;
		 
	}
*/
	@Override
	public List<ConfigurationGovernmentOrder> checkLGDMConfig(int slc, List<Operations> OperationListUrban) throws Exception {
		// TODO Check Configuration of ldg admin
		String tempcode = "";

		List<Operations> OperationList = null;
		OperationList = new ArrayList<Operations>();
		OperationList = (List<Operations>) OperationListUrban;
		// ConfigurationGovernmentOrder obj=new ConfigurationGovernmentOrder();
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;
		configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();

		if (OperationListUrban == null) {
			configurationGovernmentOrderList = null;
		} else {
			for (Operations operation : OperationList) {
				tempcode = tempcode + operation.getOperationCode() + ",";
			}
			if (tempcode != null) {
				StringBuffer code = new StringBuffer(tempcode);
				code.setCharAt(tempcode.lastIndexOf(','), ' ');
				// obj.setIsactive(true);
				String Hql = "from ConfigurationGovernmentOrder where operationCode in(" + code + ") and slc=" + slc + " and isactive=true order by operationCode";
				configurationGovernmentOrderList = configGovtOrderDAO.getConfigurationGovernmentOrderDetails(Hql);
				if (configurationGovernmentOrderList.size() > 0) {
					return configurationGovernmentOrderList;
				} else {
					configurationGovernmentOrderList = null;
				}

			}

		}

		return configurationGovernmentOrderList;

	}

	// Maneesh

	@Override
	public List<ConfigurationGovernmentOrder> checkLGDMConfigPRI(int slc, List<Operations> OperationListPRI) throws Exception {
		// TODO Check Configuration of ldg admin
		String tempcode = "";

		List<Operations> OperationList = null;
		OperationList = new ArrayList<Operations>();
		OperationList = (List<Operations>) OperationList;
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;
		configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();

		if (OperationListPRI == null) {
			configurationGovernmentOrderList = null;
		} else {
			for (Operations operation : OperationListPRI) {
				tempcode = tempcode + operation.getOperationCode() + ",";
			}
			if (tempcode != null) {
				StringBuffer code = new StringBuffer(tempcode);
				code.setCharAt(tempcode.lastIndexOf(','), ' ');
				String Hql = "from ConfigurationGovernmentOrder where operationCode in(" + code + ") and slc=" + slc + " and isactive=true order by operationCode";
				configurationGovernmentOrderList = configGovtOrderDAO.getConfigurationGovernmentOrderDetailsPRI(Hql);
				if (configurationGovernmentOrderList.size() > 0) {
					return configurationGovernmentOrderList;
				} else {
					configurationGovernmentOrderList = null;
				}

			}

		}

		return configurationGovernmentOrderList;

	}

	// Maneesh

	@Override
	public List<ConfigurationGovernmentOrder> checkLGDMConfigTrade(int slc, List<Operations> OperationListTrade) throws Exception {

		String tempcode = "";

		OperationList = (List<Operations>) OperationList;

		if (OperationListTrade == null)
			configurationGovernmentOrderList = null;
		else {
			for (Operations operation : OperationListTrade) {
				tempcode = tempcode + operation.getOperationCode() + ",";
			}
			if (tempcode != null) {
				StringBuffer code = new StringBuffer(tempcode);
				code.setCharAt(tempcode.lastIndexOf(','), ' ');
				String Hql = "from ConfigurationGovernmentOrder where operationCode in(" + code + ") and slc=" + slc + " and isactive=true order by operationCode";
				configurationGovernmentOrderList = configGovtOrderDAO.getConfigurationGovernmentOrderDetailsPRI(Hql);
				if (configurationGovernmentOrderList.size() > 0) {
					return configurationGovernmentOrderList;
				} else {
					configurationGovernmentOrderList = null;
				}

			}

		}

		return configurationGovernmentOrderList;

	}

	// Maneesh

	@Override
	public List<ConfigurationGovernmentOrder> checkconstituencyMgrConfig(int stateCode) throws Exception {
		// TODO checkconstituencyMgrConfig
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;
		configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();
		int stateVersion = 0;
		stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		String Hql = "from ConfigurationGovernmentOrder where operationCode in(30,31,32,33,34,35,37,38) and state.statePK.stateCode='" + stateCode + "' and state.statePK.stateVersion='" + stateVersion + "' order by operationCode";
		configurationGovernmentOrderList = configGovtOrderDAO.getConfigurationGovernmentOrderDetails(Hql);
		if (configurationGovernmentOrderList.size() > 0) {
			return configurationGovernmentOrderList;
		} else {
			configurationGovernmentOrderList = null;
		}
		return configurationGovernmentOrderList;
	}

	@Override
	public List<ConfigurationGovernmentOrder> checkadministrativeUnitMgrConfig(int slc, List<Operations> OperationList) throws Exception {
		String tempcode = "";

		OperationList = (List<Operations>) OperationList;

		if (OperationList == null)
			configurationGovernmentOrderList = null;
		else {
			for (Operations operation : OperationList) {
				tempcode = tempcode + operation.getOperationCode() + ",";
			}
			if (tempcode != null) {
				StringBuffer code = new StringBuffer(tempcode);
				code.setCharAt(tempcode.lastIndexOf(','), ' ');
				String Hql = "from ConfigurationGovernmentOrder where operationCode in(" + code + ") and slc=" + slc + " and isactive=true order by operationCode";
				configurationGovernmentOrderList = configGovtOrderDAO.getConfigurationGovernmentOrderDetailsPRI(Hql);
				if (configurationGovernmentOrderList.size() > 0) {
					return configurationGovernmentOrderList;
				} else {
					configurationGovernmentOrderList = null;
				}

			}

		}

		return configurationGovernmentOrderList;

	}

	@Override
	public Map<String, String> checkMapAndGovtOrderConfiguration(int stateCode, int operationCode, char landRegionType) throws Exception {
		// TODO checkMapandGovtOrderConfiguration
		Map<String, String> hMap = new HashMap<String, String>();
		// check map configuration
		List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
		viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
		viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);

		// check government configuration
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;

		configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();
		if (landRegionType == 'D' || landRegionType == 'T' || landRegionType == 'V' || landRegionType == 'B') {
			configurationGovernmentOrderList = checkLRMConfigbyOperationCode(stateCode, operationCode);
		}
		if (landRegionType == 'S') {
			LOG.info("nothing to do.");
			// configurationGovernmentOrderList =
			// checkCentralAdminConfig(stateCode);
		}
		if (configurationGovernmentOrderList != null && viewConfigMapLandRegion.size() > 0) {
			for (int i = 0; i < configurationGovernmentOrderList.size(); i++) {
				if (configurationGovernmentOrderList.get(i).getOperationCode() == operationCode) {
					if (configurationGovernmentOrderList.get(i).isIsgovtorderupload()) {
						hMap.put("govtOrder", "govtOrderUpload");
					} else if (!configurationGovernmentOrderList.get(i).isIsgovtorderupload()) {
						hMap.put("govtOrder", "govtOrderGenerate");
					} else {
						hMap.put("govtOrder", null);
					}
					// mav.addObject("isgovtorderupload", "govtordergenerate");
				}
			}

			for (int j = 0; j < viewConfigMapLandRegion.size(); j++) {

				if (viewConfigMapLandRegion.get(j).getLandregiontype() == landRegionType) {
					if (viewConfigMapLandRegion.get(j).getIsmapupload()) {
						// mav.addObject("ismapupload", "mapupload");
						hMap.put("mapUpload", "false");

					} else if (!viewConfigMapLandRegion.get(j).getIsmapupload()) {
						hMap.put("mapUpload", "true");
						// mav.addObject("baseurl",
						// viewConfigMapLandRegion.get(j).getIsbaseUrl());
					} else {
						hMap.put("mapUpload", null);
					}
					// break;
				}
			}

		} else if (configurationGovernmentOrderList == null && viewConfigMapLandRegion.size() > 0) {
			String msgid = "Configuration government order is not defined in the state please defined the setup first !";
			hMap.put("message", msgid);
		} else if (viewConfigMapLandRegion.size() == 0 && configurationGovernmentOrderList != null) {
			String msgid = "Map configuration is not defined in the state !";
			hMap.put("message", msgid);
		} else if (configurationGovernmentOrderList == null && viewConfigMapLandRegion.size() == 0) {
			String msgid = "Neither configuration government order is defined nor map configuration. Please configure the system";
			hMap.put("message", msgid);
		} else {
			String msgid = "Neither configuration government order is defined nor map configuration. Please configure the system";
			hMap.put("message", msgid);

		}
		return hMap;
	}

	/*@Override
	public Map<String, String> checkMapAndGovtOrderConfigurationforBlock(int stateCode, int operationCode, char landRegionType) throws Exception {
		// TODO checkMapandGovtOrderConfiguration
		Map<String, String> hMap = new HashMap<String, String>();
		// check map configuration
		List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
		viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
		viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);

		// check Nomenclature
		List<NomenclatureSubdistrict> nomenclatureSubdistrictList = new ArrayList<NomenclatureSubdistrict>();
		int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		int slc = commonService.getSlc(stateCode);
		nomenclatureSubdistrictList = nomenclatureSubDistDAO.getNomenclatureDetails(slc);

		// check government configuration
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;

		configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();
		// configurationGovernmentOrderList =
		// checkadministrativeUnitMgrConfig(stateCode);
		if (nomenclatureSubdistrictList.size() > 0 && nomenclatureSubdistrictList.get(0).isIssubdistrictblocksame()) {
			String msgid = "You cannot create the block because definition of block and sub-district is same for the state !";
			hMap.put("message", msgid);
		} else if (configurationGovernmentOrderList != null && viewConfigMapLandRegion.size() > 0 && nomenclatureSubdistrictList.size() > 0 && nomenclatureSubdistrictList.size() > 0) {
			for (int i = 0; i < configurationGovernmentOrderList.size(); i++) {
				if (configurationGovernmentOrderList.get(i).getOperationCode() == operationCode) {
					if (configurationGovernmentOrderList.get(i).isIsgovtorderupload()) {
						hMap.put("govtOrder", "govtOrderUpload");
					} else if (!configurationGovernmentOrderList.get(i).isIsgovtorderupload()) {
						hMap.put("govtOrder", "govtOrderGenerate");
					} else {
						hMap.put("govtOrder", null);
					}
					// mav.addObject("isgovtorderupload", "govtordergenerate");
				}
			}

			for (int j = 0; j < viewConfigMapLandRegion.size(); j++) {

				if (viewConfigMapLandRegion.get(j).getLandregiontype() == landRegionType) {
					if (viewConfigMapLandRegion.get(j).getIsmapupload()) {
						// mav.addObject("ismapupload", "mapupload");
						hMap.put("mapUpload", "false");

					} else if (!viewConfigMapLandRegion.get(j).getIsmapupload()) {
						hMap.put("mapUpload", "true");
						// mav.addObject("baseurl",
						// viewConfigMapLandRegion.get(j).getIsbaseUrl());
					} else {
						hMap.put("mapUpload", null);
					}
					// break;
				} else {
					String msgid = "Map configuration is not defined in the state !";
					hMap.put("message", msgid);
				}
			}

		} else if (nomenclatureSubdistrictList.size() > 0 && !nomenclatureSubdistrictList.get(0).isIssubdistrictblocksame()) {
			String msgid = "You cannot create the block because definition of block and sub-district is same for the state !";
			hMap.put("message", msgid);
		} else if (configurationGovernmentOrderList == null && viewConfigMapLandRegion.size() > 0 && nomenclatureSubdistrictList.size() > 0) {
			String msgid = "Configuration government order is not defined in the state please define the setup first !";
			hMap.put("message", msgid);
		} else if (viewConfigMapLandRegion.size() == 0 && configurationGovernmentOrderList != null && nomenclatureSubdistrictList.size() > 0) {
			String msgid = "Map configuration is not defined in the state !";
			hMap.put("message", msgid);
		} else if (nomenclatureSubdistrictList.size() == 0 && viewConfigMapLandRegion.size() > 0 && configurationGovernmentOrderList != null) {
			String msgid = "Nomenclature of sub-district is not defined in the state, plase define the setup first !";
			hMap.put("message", msgid);
		} else if (configurationGovernmentOrderList == null && viewConfigMapLandRegion.size() > 0 && nomenclatureSubdistrictList.size() == 0) {
			String msgid = "Neither nomenclature of sub-district is defined nor configuration government order. Please configure the system";
			hMap.put("message", msgid);
		} else if (configurationGovernmentOrderList == null && viewConfigMapLandRegion.size() == 0 && nomenclatureSubdistrictList.size() > 0) {
			String msgid = "Neither map configuration is defined nor configuration government order. Please configure the system";
			hMap.put("message", msgid);
		} else if (configurationGovernmentOrderList == null && viewConfigMapLandRegion.size() == 0 && nomenclatureSubdistrictList.size() == 0) {
			String msgid = "Neither nomenclature of sub-district is defined nor configuration government order nor map configuration. Please configure the system";
			hMap.put("message", msgid);
		}

		else {
			String msgid = "Unknown Server error !";
			hMap.put("message", msgid);

		}
		return hMap;
	}*/

	@Override
	public Map<String, String> checkMapAndGovtOrderConfigurationforCentral(int operationCode) throws Exception {
		// TODO checkMapandGovtOrderConfiguration
		Map<String, String> hMap = new HashMap<String, String>();
		// check map configuration
		//List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
		//viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
		// viewConfigMapLandRegion=configMapService.checkMapConfigurationforCentral();

		// check government configuration
		List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;

		configurationGovernmentOrderList = checkCentralAdminConfig();

		if (configurationGovernmentOrderList == null) {
			String msgid = "Configuration government order for Central Level is not defined, please defined the setup first !";
			hMap.put("message", msgid);
		} else {
			for (int i = 0; i < configurationGovernmentOrderList.size(); i++) {
				if (configurationGovernmentOrderList.get(i).getOperationCode() == operationCode) {
					if (configurationGovernmentOrderList.get(i).isIsgovtorderupload()) {
						hMap.put("govtOrder", "govtOrderUpload");
					} else if (!configurationGovernmentOrderList.get(i).isIsgovtorderupload()) {
						hMap.put("govtOrder", "govtOrderGenerate");
					} else {
						hMap.put("govtOrder", null);
					}
					// mav.addObject("isgovtorderupload", "govtordergenerate");
				}
			}

		}
		/*
		 * else if(viewConfigMapLandRegion.size()==0 &&
		 * configurationGovernmentOrderList!=null) { String msgid=
		 * "Map configuration for Central Level is not defined, please defined the setup first !"
		 * ; hMap.put("message", msgid); } else
		 * if(configurationGovernmentOrderList ==null &&
		 * viewConfigMapLandRegion.size()==0) { String msgid=
		 * "Neither configuration government order is defined nor map configuration. Please configure the system"
		 * ; hMap.put("message", msgid); } else { String msgid=
		 * "Neither configuration government order is defined nor map configuration. Please configure the system"
		 * ; hMap.put("message", msgid);
		 * 
		 * }
		 */
		return hMap;
	}
}
