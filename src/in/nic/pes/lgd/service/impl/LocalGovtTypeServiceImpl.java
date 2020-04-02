package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.CheckLocalBodyType;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTypeHistory;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.LocalGovtTypeDAO;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;
import in.nic.pes.lgd.forms.LocalGovtTypeForm;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.LocalGovtTypeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import pes.attachment.util.AttachedFilesForm;

public class LocalGovtTypeServiceImpl implements LocalGovtTypeService {
	private static final Logger log = Logger.getLogger(LocalGovtTypeServiceImpl.class);

	@Autowired
	LocalGovtTypeDAO localGovtTypeDAO;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	SessionFactory sessionFactory;

	int userId = 4000;
	long createdBy = 4000;

	Session session = null;
	Transaction tx = null;

	@Override
	public int saveLocalGovtType(LocalGovtTypeForm localgovtTypeform,
			GovernmentOrderForm govtOrderForm,
			List<AttachedFilesForm> attachedList, HttpServletRequest request)
			throws Exception {
		int code = 0;
		try {
			try {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				LocalBodyType localbodyType = new LocalBodyType();
				localbodyType.setLocalBodyTypeName(localgovtTypeform
						.getLocalBodyTypeName());
				localbodyType.setCategory(localgovtTypeform.getCategoryRadio()
						.charAt(0));

				if (localgovtTypeform.getCategoryRadio().charAt(0) == 'U') {
					localbodyType.setLevel('I');
				} else {
					localbodyType.setLevel(localgovtTypeform.getLevel().charAt(
							0));
				}

				if (localgovtTypeform.getCategoryRadio().charAt(0) == 'U'
						|| localgovtTypeform.getRuralCategory().charAt(0) == 'T') {
					localbodyType.setIspartixgovt(false);
				} else {
					localbodyType.setIspartixgovt(true);
				}
				localbodyType.setCreatedby(createdBy);
				localbodyType.setCreatedon(new Date());
				localbodyType.setLastupdated(new Date());
				localbodyType.setLastupdatedby(createdBy);
				localbodyType.setIsactive(true);
				code = localGovtTypeDAO.saveLocalGovtType(localbodyType,
						session);
				GovernmentOrder govtOrder = convertLocalbodyService
						.saveDataInGovtOrder(govtOrderForm, session);
				convertLocalbodyService.saveDataInAttachment(govtOrder,
						govtOrderForm, attachedList, session);
				tx.commit();
				// return code;
			} catch (Exception e) {
				log.debug("Exception" + e);
				tx.rollback();
				return 0;
			} finally {
				if (session != null && session.isOpen()){
					session.close();
				}	
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			return 0;
		}
		return code;
	}

	@Override
	public List<LocalBodyType> getLocalBodyTypeDetails(
			int localBodyTypeCode) throws Exception {
		List<LocalBodyType> localgovt = null;
		try {
			localgovt = localGovtTypeDAO
					.getLocalBodyTypeDetails(localBodyTypeCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return localgovt;
	}

	@Override
	public List<LocalGovtTypeDataForm> getLocalBodyTypeDetailsall(
			int localBodyTypeCode) throws Exception {
		List<LocalGovtTypeDataForm> localgovt = null;
		try {
			localgovt = localGovtTypeDAO
					.getLocalBodyTypeDetailall(localBodyTypeCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		// TODO Auto-generated method stub
		return localgovt;
	}

	@Override
	public boolean modifyLocalGovtTypeInfo(LocalGovtTypeForm localgovtTypeForm,GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request)throws Exception
	{
		Date currentDate = new Date();
		LocalBodyType localBodyTypebean =  new LocalBodyType();
		LocalBodyTypeHistory localBodyTypeHistory = new LocalBodyTypeHistory();
		List<LocalBodyType> listGovtTypeDetail = new ArrayList<LocalBodyType>();	
		List<LocalGovtTypeDataForm> listdetail = new ArrayList<LocalGovtTypeDataForm>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
		

			if (localgovtTypeForm.getListGovtTypeDetail().size() > 0) {
				localgovtTypeForm.setCorrection(true);
			} else
				localgovtTypeForm.setCorrection(false);

			/*if (localgovtTypeForm.isCorrection() == true) {
				List<LocalGovtTypeDataForm> listGovtTypeDetails = new ArrayList<LocalGovtTypeDataForm>();
				listGovtTypeDetails = localgovtTypeForm
						.getListGovtTypeDetails();

				for (LocalGovtTypeDataForm element : listGovtTypeDetails) {
					localBodyTypebean.setLocalBodyTypeName(element
							.getLocalBodyTypeName());
					localBodyTypebean.setLocalBodyTypeCode(element
							.getLocalBodyTypeCode());
					localBodyTypebean.setIspartixgovt(element.isIspartixgovt());
					localBodyTypebean.setCategory(element.getCategory());
					localBodyTypebean.setLevel(element.getLevel());

				}
				localBodyTypebean.setCreatedby(createdBy);
				localBodyTypebean.setCreatedon(currentDate);
				localBodyTypebean.setLastupdated(currentDate);
				localBodyTypebean.setLastupdatedby(111);
				localBodyTypebean.setIsactive(true);
				localGovtTypeDAO.updateLocalGovtType(localBodyTypebean,session);
			} else if (localgovtTypeForm.isCorrection() == false) {
				for (int i = 1; i < listGovtTypeDetail.size(); i++) {
					if (listGovtTypeDetail.get(i).getCategory() == 'R'
							&& listGovtTypeDetail.get(i).getIspartixgovt() == true) {

					}
				}		*/		

				listGovtTypeDetail = localgovtTypeForm.getListGovtTypeDetail();

				for (LocalBodyType element : listGovtTypeDetail) {

					LocalBodyType localbodyType = new LocalBodyType(
							element.getLocalBodyTypeCode());
					localBodyTypeHistory.setLocalBodyType(localbodyType);
					localBodyTypeHistory.setLocalBodyTypeName(element
							.getLocalBodyTypeName());
					localBodyTypeHistory.setCreatedby(createdBy);
					localBodyTypeHistory.setCategory(element.getCategory());
					localBodyTypeHistory.setIspartixgovt(element
							.getIspartixgovt());
					localBodyTypeHistory.setLevel(element.getLevel());
					localBodyTypeHistory.setCreatedon(currentDate);
					localBodyTypeHistory.setLastupdated(currentDate);
					localBodyTypeHistory.setLastupdatedby(createdBy);
					localBodyTypeHistory.setIsactive(false);
					localGovtTypeDAO.saveLocalGovtTypeHistory(localBodyTypeHistory,session);
				}
				// LocalBodyTypeHistory
				
				GovernmentOrder govtOrder = convertLocalbodyService
						.saveDataInGovtOrder(govtOrderForm, session);
			 
				listdetail = localgovtTypeForm.getListdetail();
				for(LocalGovtTypeDataForm element : listdetail){
					
				 
					localBodyTypebean.setLocalBodyTypeName(element
							.getLocalBodyTypeName());
					localBodyTypebean.setLocalBodyTypeCode(element
							.getLocalBodyTypeCode());
					localBodyTypebean.setCategory(element.getCategory());
					if (element.getCategory() == 'U'
							|| element.getCategory() == 'T') {
						localBodyTypebean.setIspartixgovt(false);
					} else {
						localBodyTypebean.setIspartixgovt(true);
					}
					if (element.getCategory() == 'U') {
						localBodyTypebean.setLevel('I');
						localBodyTypebean.setIspartixgovt(false);
					} else {
						localBodyTypebean.setLevel(localgovtTypeForm.getLevel()
								.charAt(0));
					}
					localBodyTypebean.setCreatedby(createdBy);
					localBodyTypebean.setCreatedon(currentDate);
					localBodyTypebean.setLastupdated(currentDate);
					localBodyTypebean.setLastupdatedby(createdBy);
					localBodyTypebean.setIsactive(true);
				 
					localGovtTypeDAO.updateLocalGovtType(localBodyTypebean,session);				 
				}
				if (attachedList != null && !attachedList.isEmpty()) {
					convertLocalbodyService.saveDataInAttachment(govtOrder,
							govtOrderForm, attachedList, session);
				
				}
					
			 
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;

	}

	@Override
	public List<LocalBodyTiersSetup> getLocalBodyTypeDetails() throws Exception {
		LocalBodyType localBodyTypebean = null;
		localBodyTypebean = new LocalBodyType();
		List<LocalBodyTiersSetup> localBodyTiersSetupList = null;
		try {

			localBodyTiersSetupList = new ArrayList<LocalBodyTiersSetup>();
			localBodyTiersSetupList = localGovtTypeDAO.lgsetup();
			int localBodyTypeCode = localBodyTypebean.getLocalBodyTypeCode();
			int j = 0;
			for (j = 0; j < localBodyTiersSetupList.size(); j++) {
				if (localBodyTypeCode == localBodyTiersSetupList.get(j)
						.getLocalBodySetupCode()) {
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return localBodyTiersSetupList;
	}

	@Override
	public boolean checkgovtTypeDependency(char category, char level)
			throws Exception {
		boolean success = true;
		try {
			// category
			if (category == 'P' || category == 'p') {
				String Hql = "from LocalBodyType where level='" + level
						+ "' and ispartixgovt=true and category='R'";
				try {
					success = localGovtTypeDAO.checkgovtTypeDependency(Hql);
				} catch (Exception e) {
					log.debug("Exception" + e);
					success = false;
				}
			} else {
				success = false;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return success;
	}
	
	@Override
	public List<CheckLocalBodyType> checkLocalBodyType(Integer localBodyTypeCode) throws Exception
	{
		return localGovtTypeDAO.checkLocalBodyTypeDAO(localBodyTypeCode);
	}

}
