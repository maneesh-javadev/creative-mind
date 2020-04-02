package in.nic.pes.lgd.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import in.nic.pes.lgd.bean.Designation;
import in.nic.pes.lgd.bean.DesignationDelete;
import in.nic.pes.lgd.bean.DesignationPesDelete;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.LocalBodySetup;
import in.nic.pes.lgd.bean.LocalBodySetupPK;
import in.nic.pes.lgd.bean.LocalBodySubtype;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalbodyTypeInState;
import in.nic.pes.lgd.bean.OrganizationDesignation;
import in.nic.pes.lgd.bean.ReportingLbSetup;
import in.nic.pes.lgd.bean.ReportingOrgSetup;
import in.nic.pes.lgd.bean.ReportingSetup;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.common.ReleaseResources;
import in.nic.pes.lgd.dao.LocalBodySetupDAO;
import in.nic.pes.lgd.dao.LocalGovTypeDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.DesignationForm;
import in.nic.pes.lgd.forms.DesignationReportingForm;
import in.nic.pes.lgd.forms.LGSetupForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.utils.CurrentDateTime;
import in.nic.pes.lgd.utils.DatePicker;

public class LocalGovtSetupServiceImpl implements LocalGovtSetupService {
	
	private static final Logger log = Logger.getLogger(LocalGovtSetupServiceImpl.class);

	@Autowired
	LocalGovTypeDAO LgTypeDao;
	@Autowired
	LocalBodySetupDAO localBodySetupDAO;
	@Autowired
	StateDAO stateDao;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	CommonService commonService;
	
		long rolecode = 1000;

	public List<GetLocalGovtSetup> isStateSetup(int stateCode, char category)
			throws Exception {
		// int stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		getLocalGovtSetupList = localBodySetupDAO.isStateSetup(stateCode,
				category);
		if (getLocalGovtSetupList.size() > 0) {
			return getLocalGovtSetupList;
		}
		return getLocalGovtSetupList;
	}
	
	public List<GetLocalGovtSetup> isStateSetupDisturbed(int stateCode, char category, char level) throws Exception {
		// int stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		getLocalGovtSetupList = localBodySetupDAO.isStateSetupDisturbed(stateCode,category,level);
		if (getLocalGovtSetupList.size() > 0) {
			return getLocalGovtSetupList;
		}
		return getLocalGovtSetupList;
	}
	
	public boolean saveLocalGovSetup(
			List<List<LocalBodyType>> localBodyTypeListToSave, int stateCode)
			throws Exception {
		// TODO saveLocalGovSetup

		int localBodySetupCode = 0;
		int localBodySetupVersion = 1;
		int tierSetupCode = 0;
		//int localBodySubtypeCode = 0;
		int localBodyTypeCode = 0;
		String localBodyNomenLocal = null;
		String localBodyNomenEng = null;
		boolean success = false;
		boolean temp2 = false;
		boolean temp1 = false;

		List<LocalBodyType> lbtList = null;

		try {
			localBodySetupCode = localBodySetupDAO
					.getMaxRecords("select COALESCE(max(local_body_setup_code),1) from local_body_setup");
			tierSetupCode = localBodySetupDAO
					.getMaxRecords("select COALESCE(max(tier_setup_code),1) from local_body_tiers_setup");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e1);
		}
		int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		int slc=commonService.getSlc(stateCode);
		
		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		temp2 = this.setupTableObject(localBodySetupCode,
				localBodySetupVersion, session, stateCode, stateVersion);

		try {

			for (int i = 0; i < localBodyTypeListToSave.size(); i++) {
				int parentTierSetupCode = 0;
				lbtList = new ArrayList<LocalBodyType>();
				lbtList = localBodyTypeListToSave.get(i);

				for (int j = 0; j < lbtList.size(); j++) {

					String[] temp = lbtList.get(j).getTemp().split(":");
					localBodyTypeCode = Integer.parseInt(temp[0]);
					// localBodylevel=temp[1].charAt(0);
					localBodyNomenEng = temp[2].trim();
					if (temp.length == 4) {
						localBodyNomenLocal = temp[3].trim();
					}
					if (localBodySetupCode != 0) {
						temp1 = this.tierSetupTableObject(localBodyNomenLocal,
								localBodyNomenEng, localBodyTypeCode,
								parentTierSetupCode, localBodySetupCode,
								localBodySetupVersion, tierSetupCode, session,slc);
						parentTierSetupCode = tierSetupCode;
						tierSetupCode += 1;
					}

				}

				/*
				 * String lbtFormData = lgSetupForm.getlBTList();
				 * System.out.println("lGSetupForm.getlBTList();" +
				 * lbtFormData); int localBodyTypeCode = 0; String
				 * localBodyNomenLocal = null; String localBodyNomenEng = null;
				 * int parentTierSetupCode = 0;
				 * this.setupTableObject(localBodySetupCode,
				 * localBodySetupVersion, session);
				 */
				// String[] templbtFormData = lbtFormData.split(",");
				/*
				 * for (int i = 0; i < templbtFormData.length; i++) { String[]
				 * temp = templbtFormData[i].split(":"); // for (int j = 0; j <
				 * temp.length; j++) { localBodyTypeCode =
				 * Integer.parseInt(temp[0]); //
				 * localBodylevel=temp[1].charAt(0); localBodyNomenEng =
				 * temp[2]; if (temp.length == 4) { localBodyNomenLocal =
				 * temp[3]; } // } if (localBodySetupCode != 0) {
				 * this.tierSetupTableObject(localBodyNomenLocal,
				 * localBodyNomenEng, localBodyTypeCode, parentTierSetupCode,
				 * localBodySetupCode, localBodySetupVersion, tierSetupCode,
				 * session); parentTierSetupCode = tierSetupCode; tierSetupCode
				 * += 1; } }
				 */
			}
			if (temp1 && temp2) {
				tx.commit();
				success = true;
			} else
				success = false;
			return true;
		} catch (Exception e) {
			//System.out.println("::::::::::RolledBack");
			// tx.rollback();
			log.debug("Exception" + e);
			success = false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return success;
	}

	public boolean saveLocalGovSetupUrban(
			List<List<LocalBodyType>> localBodyTypeListToSave, int stateCode)
			throws Exception {
		// TODO saveLocalGovSetup

		int localBodySetupCode = 0;
		int localBodySetupVersion = 1;
		int tierSetupCode = 0;
		//int localBodySubtypeCode = 0;
		int localBodyTypeCode = 0;
		String localBodyNomenLocal = null;
		String localBodyNomenEng = null;
		boolean success = false;
		boolean temp2 = false;
		boolean temp1 = false;

		List<LocalBodyType> lbtList = null;

		try {
			localBodySetupCode = localBodySetupDAO
					.getMaxRecords("select COALESCE(max(local_body_setup_code),1) from local_body_setup");
			tierSetupCode = localBodySetupDAO
					.getMaxRecords("select COALESCE(max(tier_setup_code),1) from local_body_tiers_setup");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e1);
		}
		int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		int slc=commonService.getSlc(stateCode);
		
		
		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		temp2 = this.setupTableObject(localBodySetupCode,
				localBodySetupVersion, session, stateCode, stateVersion);

		try {

			for (int i = 0; i < localBodyTypeListToSave.size(); i++) {
				int parentTierSetupCode = 0;
				lbtList = new ArrayList<LocalBodyType>();
				lbtList = localBodyTypeListToSave.get(i);

				for (int j = 0; j < lbtList.size(); j++) {

					String[] temp = lbtList.get(j).getTemp().split(":");
					localBodyTypeCode = Integer.parseInt(temp[0]);
					// localBodylevel=temp[1].charAt(0);
					localBodyNomenEng = temp[2].trim();
					if (temp.length == 4) {
						localBodyNomenLocal = temp[3].trim();
					}
					if (localBodySetupCode != 0) {
						temp1 = this.tierSetupTableObject(localBodyNomenLocal,
								localBodyNomenEng, localBodyTypeCode,
								parentTierSetupCode, localBodySetupCode,
								localBodySetupVersion, tierSetupCode, session,slc);
						parentTierSetupCode = 0;
						tierSetupCode += 1;
					}

				}

				/*
				 * String lbtFormData = lgSetupForm.getlBTList();
				 * System.out.println("lGSetupForm.getlBTList();" +
				 * lbtFormData); int localBodyTypeCode = 0; String
				 * localBodyNomenLocal = null; String localBodyNomenEng = null;
				 * int parentTierSetupCode = 0;
				 * this.setupTableObject(localBodySetupCode,
				 * localBodySetupVersion, session);
				 */
				// String[] templbtFormData = lbtFormData.split(",");
				/*
				 * for (int i = 0; i < templbtFormData.length; i++) { String[]
				 * temp = templbtFormData[i].split(":"); // for (int j = 0; j <
				 * temp.length; j++) { localBodyTypeCode =
				 * Integer.parseInt(temp[0]); //
				 * localBodylevel=temp[1].charAt(0); localBodyNomenEng =
				 * temp[2]; if (temp.length == 4) { localBodyNomenLocal =
				 * temp[3]; } // } if (localBodySetupCode != 0) {
				 * this.tierSetupTableObject(localBodyNomenLocal,
				 * localBodyNomenEng, localBodyTypeCode, parentTierSetupCode,
				 * localBodySetupCode, localBodySetupVersion, tierSetupCode,
				 * session); parentTierSetupCode = tierSetupCode; tierSetupCode
				 * += 1; } }
				 */
			}
			if (temp1 && temp2) {
				tx.commit();
				success = true;
			} else
				success = false;
			return true;
		} catch (Exception e) {

			// tx.rollback();
			log.debug("Exception" + e);
			success = false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return success;
	}

	/*
	 * public boolean saveLocalGovSetup(LGSetupForm lgSetupForm, boolean isNew)
	 * { int localBodySetupCode=0; int localBodySetupVersion=0; int
	 * tier_setup_code=0; int localBodySubtypeCode=0;
	 * 
	 * Session session = null; Transaction tx = null;
	 * 
	 * session=sessionFactory.openSession(); tx= session.beginTransaction();
	 * 
	 * 
	 * if (isNew == true) { localBodySetupVersion = 1; try { localBodySetupCode
	 * = localGovtSetupDetailsDAO
	 * .getMaxRecords("select max(local_body_setup_code) from local_body_setup"
	 * ); tier_setup_code = localGovtSetupDetailsDAO
	 * .getMaxRecords("select max(tier_setup_code) from local_body_tiers_setup"
	 * ); localBodySubtypeCode = localGovtSetupDetailsDAO
	 * .getMaxRecords("select max( local_body_subtype_code) from local_body_subtype"
	 * ); } catch (Exception e) { } }
	 * 
	 * else {
	 * 
	 * try { List<LocalBodySetup> setupDetail = localGovtSetupDetailsDAO
	 * .getLocalBodySetupDetails(stateCode, stateVersion); localBodySetupCode =
	 * setupDetail.get(0).getLocalBodySetupCode(); localBodySetupVersion =
	 * setupDetail.get(0).getLocalBodySetupVersion(); tier_setup_code =
	 * localGovtSetupDetailsDAO
	 * .getMaxRecords("select max(tier_setup_code) from local_body_tiers_setup"
	 * ); localBodySubtypeCode = localGovtSetupDetailsDAO
	 * .getMaxRecords("select max( local_body_subtype_code) from local_body_subtype"
	 * );
	 * 
	 * localGovtSetupDetailsDAO.updateIsActiveLocalBodySetup(false,
	 * localBodySetupCode, localBodySetupVersion, session);
	 * localGovtSetupDetailsDAO.updateIsActiveTierSetUp(false,
	 * localBodySetupCode, localBodySetupVersion, session);
	 * localGovtSetupDetailsDAO.updateIsActiveSubType(false, stateCode,
	 * stateVersion, session); localBodySetupVersion++; }
	 * 
	 * catch (Exception e) { } }
	 * 
	 * try { this.setupTableObject(lgSetupForm, localBodySetupCode,
	 * localBodySetupVersion, session); if (localBodySetupCode != 0) {
	 * this.tierSetupTableObject(lgSetupForm, localBodySetupCode,
	 * localBodySetupVersion, tier_setup_code, session); } if
	 * (localBodySetupCode != 0) { this.subTypeTableObject(lgSetupForm,
	 * localBodySubtypeCode, session); } tx.commit(); return true; } catch
	 * (Exception e) { System.out.println("::::::::::RolledBack");
	 * tx.rollback(); log.debug("Exception" + e); return false; } finally{
	 * session.close(); } }
	 */

	@Override
	public List<LGSetupForm> getLocalGovtTypeDB(char category,Session session) throws Exception {
		
		
		
		List<LocalBodyType> localGovtTypeDAO = LgTypeDao
				.getLocalGoveTypeDetails(category,session);
		List<LGSetupForm> LgTypeDetails = null;
		LgTypeDetails = new ArrayList<LGSetupForm>();
		LGSetupForm lgSetupForm = null;
		for (int k = 0; k < localGovtTypeDAO.size(); k++) {
			lgSetupForm = new LGSetupForm();
			lgSetupForm.setLocalBodyTypeCode(localGovtTypeDAO.get(k)
					.getLocalBodyTypeCode());
			lgSetupForm.setLocalBodyTypeName(localGovtTypeDAO.get(k)
					.getLocalBodyTypeName());
			lgSetupForm.setCategory(localGovtTypeDAO.get(k).getCategory());
			lgSetupForm.setLevel(localGovtTypeDAO.get(k).getLevel());
			LgTypeDetails.add(k, lgSetupForm);
		}
		return LgTypeDetails;
	}

	@Override
	public List<LGSetupForm> getLocalGovtTypeDBForModify() throws Exception {
		//int setupCode = 0;
		//int setupVersion = 0;
		//int stateCode = 0;
		//char category = 'T';// check
		//int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		//Session session = sessionFactory.openSession();
		//List<LocalBodyType> LgTypeDetailsDAO = LgTypeDao.getLocalGoveTypeDetails(category,session);
		List<LGSetupForm> LgTypeDetails = new ArrayList<LGSetupForm>();
		//List<LocalBodySetup> setupDetail = localBodySetupDAO.getLocalBodySetupDetails(stateCode, stateVersion, category);

		//setupCode = setupDetail.get(0).getLocalBodySetupCode();
		//setupVersion = setupDetail.get(0).getLocalBodySetupVersion();

		//List<LocalBodyTiersSetup> tierSetupDetail = localBodySetupDAO.getTierSetupDetails(setupCode, setupVersion);
		//LGSetupForm lgSetupForm = null;
		/*
		 * for (int k = 0; k < LgTypeDetailsDAO.size(); k++) { lgSetupForm = new
		 * LGSetupForm(); int typeCode =
		 * LgTypeDetailsDAO.get(k).getLocalBodyTypeCode(); String typeName =
		 * LgTypeDetailsDAO.get(k) .getLocalBodyTypeName();
		 * lgSetupForm.setLocalBodyTypeCode(typeCode);
		 * lgSetupForm.setLocalBodyTypeName(typeName);
		 * lgSetupForm.setCategory(LgTypeDetailsDAO.get(k).getCategory());
		 * 
		 * for (int i = 0; i < tierSetupDetail.size(); i++) { // int
		 * tierTypeCode = tierSetupDetail.get(i) // .getLocalBodyTypeCode(); //
		 * if (typeCode == tierTypeCode) {
		 * 
		 * lgSetupForm.setNomenEnglish(tierSetupDetail.get(i)
		 * .getNomenclatureEnglish());
		 * lgSetupForm.setNomenLocal(tierSetupDetail.get(i)
		 * .getNomenclatureLocal());
		 * 
		 * } } LgTypeDetails.add(lgSetupForm); }
		 */
		/*if (session != null && session.isOpen()) {
			session.close();
		}*/
		return LgTypeDetails;
	}

	// 1-FOR LOCAL BODY SETUP TABLE OBJECT

	
	
	
	
	
	public boolean setupTableObject(int localBodySetupCode,
			int localBodySetupVersion, Session session, int stateCode,
			int stateVersion) throws Exception {

		LocalBodySetup setup = new LocalBodySetup();
		Integer slc=commonService.getSlc(stateCode);
		try {
			LocalBodySetupPK localBodySetupPK = null;
			localBodySetupPK = new LocalBodySetupPK(localBodySetupCode,
					localBodySetupVersion);
			setup.setLocalBodySetupPK(localBodySetupPK);

			State state = new State();
			StatePK statePK = new StatePK(stateCode, stateVersion);
			state.setStatePK(statePK);
			setup.setSlc(slc);

			//setup.setState(state);
			
			Date timestamp = DatePicker.getDate("2011-09-20");
			new Timestamp(Calendar.getInstance().getTime().getTime());
			setup.setEffectiveDate(timestamp);
			setup.setIsactive(true);
			/*
			 * setup.setLocalBodySetupCode(localBodySetupCode);
			 * setup.setLocalBodySetupVersion(localBodySetupVersion);
			 * setup.setStateCode(stateCode);
			 * setup.setStateVersion(stateVersion);
			 */
			setup.setCreatedOn(timestamp);
			setup.setCreatedBy(rolecode);
			setup.setLastUpdated(timestamp);
			setup.setLastUpdatedBy(rolecode);
			// setup.setRoleCode(1);

			localBodySetupDAO.saveLocalBodySetup(setup, session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			return false;
		}
		return true;

	}

	// 2-FOR LOCAL BODY TIER SETUP TABLE OBJECT
	public boolean tierSetupTableObject(String localBodyNomenLocal,
			String localBodyNomenEng, int localBodyTypeCode,
			int parentTierSetupCode, int localBodySetupCode,
			int localBodySetupVersion, int tierSetupCode, Session session,int slc)
			throws Exception {
		LocalBodyTiersSetup localBodyTiersSetup = new LocalBodyTiersSetup();
		//int slc=commonservice.getSlc(stateId);
		
		LocalBodySetup obj=new LocalBodySetup();
		obj.setSlc(slc);
		LocalBodyType localBodyType = new LocalBodyType();
		localBodyType.setLocalBodyTypeCode(localBodyTypeCode);
	
		localBodyTiersSetup.setLocalbodytype(localBodyType);
		localBodyTiersSetup.setTierSetupCode(tierSetupCode);
		localBodyTiersSetup.setNomenclatureEnglish(localBodyNomenEng);
		localBodyTiersSetup.setNomenclatureLocal(localBodyNomenLocal);
		localBodyTiersSetup.setLocalBodySetup(obj);
		LocalBodySetupPK localBodySetupPK = null;
		localBodySetupPK = new LocalBodySetupPK(localBodySetupCode,localBodySetupVersion);
		LocalBodySetup localBodySetupBean = null;
		localBodySetupBean = new LocalBodySetup();
		localBodySetupBean = (LocalBodySetup) session.load(
		LocalBodySetup.class, localBodySetupPK);
		localBodyTiersSetup.setLocalBodySetupCode(localBodySetupCode);
		localBodyTiersSetup.setLocalBodyTypeCode(localBodyTypeCode);
		localBodyTiersSetup.setLocalBodySetupVersion(localBodySetupVersion);
		localBodyTiersSetup.setLocalBodySetup(localBodySetupBean);
		localBodyTiersSetup.setParentTierSetupCode(parentTierSetupCode);
		localBodyTiersSetup.setIsactive(true);
		try {
			localBodySetupDAO.saveLocalBodyTierSetup(localBodyTiersSetup,
					session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			return false;
		}
		/*
		 * session.flush(); session.clear();
		 */
		return true;
	}

	/*
	 * //2-FOR LOCAL BODY TIER SETUP TABLE OBJECT
	 * 
	 * @SuppressWarnings("unchecked") public boolean
	 * tierSetupTableObject(LGSetupForm lgSetupForm,int localBodySetupCode, int
	 * localBodySetupVersion, int tier_setup_code, Session session ) { int
	 * parentTierCode = 0; int i=0; List<Integer> pcode=new
	 * ArrayList<Integer>(); if (this.getParentCode(lgSetupForm).size()>0){
	 * pcode.addAll(this.getParentCode(lgSetupForm)); } //
	 * 
	 * List<LocalBodyTiersSetup> localBodyTiersSetupList = new
	 * ArrayList<LocalBodyTiersSetup>(); Map<Object, String> hMap = new
	 * HashMap<Object, String>(); hMap = lgSetupForm.getLGNomenMap(); Iterator
	 * itr = hMap.entrySet().iterator();
	 * 
	 * while (itr.hasNext()) { LocalBodyTiersSetup localBodyTiersSetup = new
	 * LocalBodyTiersSetup();
	 * 
	 * @SuppressWarnings("unchecked") Map.Entry<Object, String> pairs =
	 * (Map.Entry<Object, String>) itr.next();
	 * 
	 * int code = (Integer) pairs.getKey();
	 * 
	 * localBodyTiersSetup.setTierSetupCode(tier_setup_code); String name =
	 * pairs.getValue(); String[] nameLocal = name.split(",");
	 * localBodyTiersSetup.setLocalBodyTypeCode(code);
	 * localBodyTiersSetup.setNomenclatureEnglish(nameLocal[0]);
	 * localBodyTiersSetup.setNomenclatureLocal(nameLocal[1]);
	 * localBodyTiersSetup.setLocalBodySetupCode(localBodySetupCode);
	 * localBodyTiersSetup.setLocalBodySetupVersion(localBodySetupVersion);
	 * localBodyTiersSetup.setIsactive(true);
	 * localGovtSetupDetailsDAO.saveLocalBodyTierSetup(localBodyTiersSetup,
	 * session); //check if (pcode.size()>0){
	 * if(i==0)localBodyTiersSetup.setParentLocalBodyTypeCode(0); else
	 * localBodyTiersSetup.setParentLocalBodyTypeCode((Integer) pcode.get(i-1));
	 * i+=1; }
	 * 
	 * else localBodyTiersSetup.setParentLocalBodyTypeCode(parentTierCode);
	 * 
	 * session.flush(); session.clear(); tier_setup_code+=1; }
	 * 
	 * return true; }
	 */

	// 4- LOCAL BODY SUB-TYPE TABLE

	@SuppressWarnings("rawtypes")
	public boolean subTypeTableObject(LGSetupForm lgSetupForm,
			int localBodySubtypeCode, Session session) throws Exception {

		Date timestamp = DatePicker.getDate("2011-09-20");
 
		Map<Object, String> hMap = lgSetupForm.getSubTypeMap();
		Iterator itr = hMap.entrySet().iterator();

		while (itr.hasNext()) {
			// LocalBodySubtype Object created

			LocalBodySubtype localBodySubtype = new LocalBodySubtype();
			@SuppressWarnings("unchecked")
			Map.Entry<Object, String> pairs = (Map.Entry<Object, String>) itr
					.next();
			//int localBodyTypeCode = Integer.parseInt(pairs.getKey().toString());
			String grade = pairs.getValue();
			if (grade.length() > 0) {

				String[] subTypeGroup = grade.split(":");

				for (int i = 0; subTypeGroup.length > i; i++) {

					if (subTypeGroup[i].indexOf(',') != -1) {
						String[] subTypeName = subTypeGroup[i].split(",");
						localBodySubtype
								.setLocalBodySubtypeCode(localBodySubtypeCode);
						localBodySubtype.setSubtypeNameEng(subTypeName[0]);
						localBodySubtype.setSubtypeNameLocal(subTypeName[1]);
						// localBodySubtype.setLocalBodyTypeCode(localBodyTypeCode);//check
						/*
						 * localBodySubtype.setStateCode(stateCode);
						 * localBodySubtype.setStateVersion(stateVersion);
						 */
						localBodySubtype.setLastupdatedby(1);
						localBodySubtype.setCreatedon(timestamp);
						localBodySubtype.setCreatedby(1124);
						localBodySubtype.setIsactive(true);
						localBodySubtype.setLastupdated(timestamp);
					}
					localBodySetupDAO.saveLocalBodySubType(localBodySubtype,
							session);
					session.flush();
					session.clear();
					localBodySubtypeCode += 1;
				}

			}
		}
		return true;
	}

	public List<Integer> getParentCode(LGSetupForm lgSetupForm)
			throws Exception {
		List<Integer> pcode = new ArrayList<Integer>();
		Map<Object, String> hMap = lgSetupForm.getChildOrderMap();
		@SuppressWarnings("rawtypes")
		Iterator itr = hMap.entrySet().iterator();
		while (itr.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<Object, String> pairs = (Map.Entry<Object, String>) itr
					.next();
			String grade = pairs.getValue();
			if (grade.length() > 0) {
				String[] subTypeGroup = grade.split(",");
				for (int i = 0; subTypeGroup.length > i; i++) {
					pcode.add((Integer.parseInt(subTypeGroup[i])));
				}
			}
		}
		return pcode;
	}
	
	public List<OrganizationDesignation> getOrganizationDesignationDetails(int olcCode, int olcLevel) throws Exception {

		List<OrganizationDesignation> designationDAO = localBodySetupDAO.getOrganizationDesignationDetails(olcCode, olcLevel);
		if(!designationDAO.isEmpty())
		{
			List<OrganizationDesignation> lstDesignation = new ArrayList<OrganizationDesignation>();
			OrganizationDesignation designationForm = new OrganizationDesignation();
          
			String desigE = null;
			String desigL = null;

			desigE = designationDAO.get(0).isIsmultiple() + "," + designationDAO.get(0).isIscontractpermanent() + "," + designationDAO.get(0).getDesignationName() + "~";
			if(designationDAO.get(0).getDesignationNameLocal()==null)
			{
				desigL = "" + "," + designationDAO.get(0).getDesignationCode() + "~";
			}
			else
			{	
				desigL = designationDAO.get(0).getDesignationNameLocal() + "," + designationDAO.get(0).getDesignationCode() + "~";
			}	
			if (designationDAO.size() > 1) {
				for (int k = 1; k < designationDAO.size(); k++) {
					desigE += designationDAO.get(k).isIsmultiple() + "," + designationDAO.get(k).isIscontractpermanent() + "," + designationDAO.get(k).getDesignationName() + "~";
					if(designationDAO.get(k).getDesignationNameLocal()==null)
					{
						desigL += "" + "," + designationDAO.get(k).getDesignationCode() + "~";
					}
					else
					{	
						desigL += designationDAO.get(k).getDesignationNameLocal() + "," + designationDAO.get(k).getDesignationCode() + "~";
					}	
				}
			}
			designationForm.setDesignationName(desigE);
			designationForm.setDesignationNameLocal(desigL);

			lstDesignation.add(designationForm);
			return lstDesignation;
		}
		else
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int saveDesignation(DesignationForm designationForm)
			throws Exception {
		Session session = null;
		Transaction tx = null;
		int designationCode = 0;
		//int localBodyTypeCode = designationForm.getLgTypeCode();
		boolean isLocalBody = false;
		int tierSetupCode = 0;
		boolean modifyFlag=false;
		if (designationForm.getIsLocalBody() == 'E'){
			isLocalBody = true;
		}	

		session = sessionFactory.openSession();
		/*tx = session.beginTransaction();*/

		/*try {
			if (designationForm.getDeletedDesignation().length() > 0) {
				String temp[] = designationForm.getDeletedDesignation().split(
						",");
				for (int i = 0; i < temp.length; i++) {
					session.createQuery(
							"delete from Designation where designationCode="
									+ temp[i]).executeUpdate();
					
				}
				tx.commit();
			}
		} catch (HibernateException e1) {
			tx.rollback();
			log.debug("Exception" + e1);
		}*/
		tx = session.beginTransaction();
		tierSetupCode = designationForm.getLgTypeCode();
		List<Designation> isExist = sessionFactory
				.openSession()
				.createQuery(
						"from Designation where tierSetupCode=" + tierSetupCode
								+ " and islocalbody='" + isLocalBody
								+ "' order by designationCode").list();
		try {
			designationCode = localBodySetupDAO
					.getMaxRecords("select COALESCE(max(designation_code),1) from designation");
		} catch (Exception e) {
		} 

	if (isExist.size() > 0) {
			try {
				// code for update
				String temp[] = designationForm.getIsMultiple().split(",");
				String tempDesgName[] = designationForm.getDesgName()
						.split(",");
				String tempDesgNameLocal[] = designationForm.getDesgNameLocal()
						.split(",");
				String tempModDesig[] = designationForm
						.getModifiedDesignation().split("~");
				String tempDesg[]=null;
				for(int i=0;i<tempDesgName.length;i++)
				{
					modifyFlag=false;
					Designation designation = new Designation();
					
					if(i<isExist.size())
					{
						for (int k = 0; k < isExist.size(); k++) {
							 if(isExist.get(i).getDesignationName().trim().equals(tempDesgName[i].trim()))
							 {
								 designation=isExist.get(i);
								 modifyFlag=true;
								 break;
							 }
						}
						
						if(modifyFlag==true)
						{
						designation.setDesignationName(tempDesgName[i].trim());
						designation.setDesignationNameLocal(tempDesgNameLocal[i].trim());
						if (i == 0) 
						{
							designation.setIstopdesignation(true);
						}
						 else{ 
							 designation.setIstopdesignation(false);
							 designation.setIsmultiple(
									Boolean.parseBoolean(temp[i - 1]));
						 }
						
					}
				}
				
						else
					{
					
						for (int k = 0; k < tempModDesig.length;) {
							 tempDesg = tempModDesig[k].split(",");
							
								designation.setDesignationCode(designationCode);
								designation.setDesignationName(tempDesgName[i].trim());
								designation.setDesignationNameLocal(tempDesgNameLocal[i].trim());
								designation.setIsmultiple(Boolean.parseBoolean(tempDesg[2]));
								designation.setIslocalbody(isLocalBody);
								if (i == 0) 
									designation.setIstopdesignation(true);
								 else
									 designation.setIstopdesignation(false);
								
								
								designation.setTierSetupCode(tierSetupCode);
								designationCode += 1;
							    break;
									
							 
						  }
				}
					
					
					localBodySetupDAO.saveDesignation(designation, session);
					session.flush();
					session.clear();
			}		
		
				
				/*for (int i = 0; i < isExist.size(); i++) {
					if (i == 0) {
						isExist.get(i).setIstopdesignation(true);
					} else {
						isExist.get(i).setIstopdesignation(false);
						isExist.get(i).setIsmultiple(
								Boolean.parseBoolean(temp[i - 1]));
					}
					if (tempDesgName != null && tempDesgName.length >= 1
							&& i < tempDesgName.length)
						isExist.get(i).setDesignationName(tempDesgName[i]);
					if (tempDesgNameLocal.length > 0)
					{	try
						{
							if(tempDesgNameLocal[i].length() > 0)
							{	
								isExist.get(i).setDesignationNameLocal(tempDesgNameLocal[i]);
							}
						}
						catch(Exception e)
						{
							 isExist.get(i).setDesignationNameLocal("");
						}
					}
					session.update(isExist.get(i));
					session.flush();
					session.clear();
				}
				// -------------------
				// code for new designation
				if (!designationForm.getModifiedDesignation().equals("null") && !designationForm.getModifiedDesignation().equals("")) {
					
					for (int k = 0; k < tempModDesig.length; k++) {
						String tempDesg[] = tempModDesig[k].split(",");

						Designation designation = new Designation();
						designation.setDesignationCode(designationCode);
						designation.setDesignationName(tempDesg[0]);
						if (tempDesg[1].length() > 0)
							designation.setDesignationNameLocal(tempDesg[1]);
						else
							designation.setDesignationNameLocal(null);
						designation.setIsmultiple(Boolean
								.parseBoolean(tempDesg[2]));
						designation.setIslocalbody(isLocalBody);
						designation.setIstopdesignation(false);
						designation.setTierSetupCode(tierSetupCode);
						localBodySetupDAO.saveDesignation(designation, session);
						session.flush();
						session.clear();
						designationCode += 1;
					}
				}*/
				tx.commit();
				
			} catch (Exception e) {
				tierSetupCode = 0;
				tx.rollback();
				log.debug("Exception" + e);
			} finally {
				ReleaseResources.doReleaseResources(session);
			}
	} else {
			String temp[] = null;
			String tempDesgName[] = null;
			String tempDesgNameLocal[] = null;
			if (designationForm.getIsMultiple() != null){
				temp = designationForm.getIsMultiple().split(",");
			}
			if (designationForm.getDesgName() != null) {
				tempDesgName = designationForm.getDesgName().split(",");
			}
			if (designationForm.getDesgNameLocal() != null) {
				tempDesgNameLocal = designationForm.getDesgNameLocal().split(",");
			}
			try {
				if (tempDesgName != null && tempDesgName.length >= 1) {

					for (int i = 0; i < tempDesgName.length; i++) {
						Designation designation = new Designation();
						if (i == 0) {
							designation.setDesignationCode(designationCode);
							designation.setDesignationName(tempDesgName[i]);
							if (tempDesgNameLocal != null
									&& tempDesgNameLocal.length >= 1){
								
								designation
										.setDesignationNameLocal(tempDesgNameLocal[i]);
							}	
							designation.setIslocalbody(isLocalBody);
							designation.setIstopdesignation(true);
						} else {
							designation.setDesignationCode(designationCode);
							designation.setDesignationName(tempDesgName[i]);
							if (tempDesgNameLocal != null
									&& tempDesgNameLocal.length >= 1){
								designation
										.setDesignationNameLocal(tempDesgNameLocal[i]);
							}	
							designation.setIsmultiple(Boolean
									.parseBoolean(temp[i - 1]));
							designation.setIslocalbody(isLocalBody);
							designation.setIstopdesignation(false);
						}
						designation.setTierSetupCode(tierSetupCode);
						localBodySetupDAO.saveDesignation(designation, session);
						session.flush();
						session.clear();
						designationCode += 1;
					}
					tx.commit();
				}
			} catch (Exception e) {
				tierSetupCode = 0;
				tx.rollback();
				log.debug("Exception" + e);
			} finally {
				ReleaseResources.doReleaseResources(session);
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		}
		return tierSetupCode;
	}

	public List<LocalBodyType> getLGTypeForDesignation(int statecode,
			char category) throws Exception {
		int stateVersion = stateDao.getCurrentVersionbyStateCode(statecode);
		Integer slc=commonService.getSlc(statecode);
		String sql = null;
		
		if (category == 'T') {
			sql = "SELECT b.tier_setup_code as localBodyTypeCode, b.nomenclature_english FROM local_body_setup a, local_body_tiers_setup b, local_body_type c WHERE a.local_body_setup_code = b.local_body_setup_code AND a.local_body_setup_version = b.local_body_setup_version AND b.local_body_type_code = c.local_body_type_code"
					+ " AND a.slc="
					+ slc
					+ " AND c.category='R' AND a.isactive='true' AND c.ispartixgovt='false'";
		} else {
			sql = "SELECT b.tier_setup_code as localBodyTypeCode, b.nomenclature_english FROM local_body_setup a, local_body_tiers_setup b, local_body_type c WHERE a.local_body_setup_code = b.local_body_setup_code AND a.local_body_setup_version = b.local_body_setup_version AND b.local_body_type_code = c.local_body_type_code"
					+ " AND a.slc="
					+ slc
					+ " AND c.category='"
					+ category
					+ "' AND a.isactive='true' AND c.ispartixgovt='true'";
		}
		List<LocalBodyType> lGType = new ArrayList<LocalBodyType>();
		@SuppressWarnings("unchecked")
		List<Object[]> temp = localBodySetupDAO.getLGType(sql, statecode,
				stateVersion, category);

		for (int k = 0; k < temp.size(); k++) {
			LocalBodyType lb = new LocalBodyType();
			Object lb1[] = temp.get(k);
			lb.setLocalBodyTypeName((String) lb1[1]);
			lb.setLocalBodyTypeCode((Integer) lb1[0]);
			lGType.add(lb);
		}
		return lGType;

	}

	public boolean saveReportingLBSetup(DesignationReportingForm designationReportingForm) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createSQLQuery("select * from remove_local_body_designations_fn(:tierSetupCode)");
			query.setParameter("tierSetupCode", Integer.parseInt(designationReportingForm.getLgTypeName().split("%")[0]), Hibernate.INTEGER);
			query.list();
			
			ReportingSetup reportingLbSetup = new ReportingSetup();
			String[] tempDesig = designationReportingForm.getDesignation().split("\\,");
			String[] tempReport = designationReportingForm.getReportTo().split(",");
			
			for (int i = 0; i < tempDesig.length; i++) {
				reportingLbSetup.setDesignationCode(Integer.parseInt(tempDesig[i]));
				if (tempReport[i].length() > 0){
					reportingLbSetup.setReportTo(Integer.parseInt(tempReport[i]));
				}
				localBodySetupDAO.saveReportingSetup(reportingLbSetup, session);
				session.flush();
				session.clear();
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}	
		}
		return false;
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Designation> getDesignation(int lgTypeCode) throws Exception {
		Session session = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		Query query;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Designation where tierSetupCode="
					+ lgTypeCode
					+ " and istopdesignation=false and islocalbody=false");
			list = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return list;
	}
	
	@Override
	public List<LocalBodyType> getLgDetails(int statecode, char category)
			throws Exception {
		
		int stateVersion = stateDao.getCurrentVersionbyStateCode(statecode);
		Integer slc=commonService.getSlc(statecode);
		String sql = null;
		if (category == 'T') {
			sql = " SELECT distinct a.level, b.nomenclature_english, b.tier_setup_code " +
				  " FROM local_body_type a, local_body_tiers_setup b, local_body_setup c, lgd_designation d " +
				  " WHERE a.local_body_type_code = b.local_body_type_code AND c.local_body_setup_code = b.local_body_setup_code AND c.local_body_setup_version = b.local_body_setup_version AND d.tier_setup_code = b.tier_setup_code" + 
				  " AND c.slc=" + slc + 
				  " AND a.category='R' AND b.isactive=true AND d.designation_type='O' AND a.ispartixgovt='false'";
		} else {
			sql = " SELECT distinct a.level, b.nomenclature_english, b.tier_setup_code " +
				  " FROM local_body_type a, local_body_tiers_setup b, local_body_setup c, lgd_designation d " +
				  " WHERE a.local_body_type_code = b.local_body_type_code AND c.local_body_setup_code = b.local_body_setup_code AND c.local_body_setup_version = b.local_body_setup_version AND d.tier_setup_code = b.tier_setup_code" +
				  " AND c.slc="+ slc +
				  " AND a.category='"+ category + "' " +
				  " AND b.isactive=true AND  d.designation_type='O' AND a.ispartixgovt='true'";
		}

		List<LocalBodyType> lGType = new ArrayList<LocalBodyType>();

		@SuppressWarnings("unchecked")
		List<Object[]> temp = localBodySetupDAO.getLGType(sql, statecode, stateVersion, category);

		for (int k = 0; k < temp.size(); k++) {
			LocalBodyType lb = new LocalBodyType();
			Object lb1[] = temp.get(k);
			lb.setLevel((Character) lb1[0]);
			lb.setLocalBodyTypeName((String) lb1[1]);
			lb.setLocalBodyTypeCode((Integer) lb1[2]);
			String txt = lb1[2] + "%" + lb1[0];
			lb.setTemp(txt);
			lGType.add(lb);
		}
		return lGType;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getSubType() throws Exception {
		int stateCode = 0;
		//int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		List listVillageDetails = localBodySetupDAO.getSubType(stateCode);
		return listVillageDetails;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getDesignation() throws Exception {
		List listVillageDetails = localBodySetupDAO.getDesignation(1);
		return listVillageDetails;
	}

	@SuppressWarnings("rawtypes")
	
	@Override
	public List getSubTier() throws Exception {
		int stateCode = 0;
		//int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		List listVillageDetails = localBodySetupDAO.getSubTier(stateCode);
		return listVillageDetails;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getDesignationElected() throws Exception {
		int stateCode = 0;
		//int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		List listVillageDetails = localBodySetupDAO.getDesignationElected(
				stateCode, true);
		return listVillageDetails;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getDesignationOfficail() throws Exception {
		int stateCode = 0;
		//int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		List listVillageDetails = localBodySetupDAO.getDesignationOfficail(
				stateCode, false);
		return listVillageDetails;
	}

	@Override
	public List<LocalBodySubtype> getSubTypeDetails(int tierSetupCode)
			throws Exception {
		List<LocalBodySubtype> localBodySubtypeDAO = null;
		localBodySubtypeDAO = new ArrayList<LocalBodySubtype>();
		localBodySubtypeDAO = localBodySetupDAO
				.getSubTypeDetails(tierSetupCode);
		return localBodySubtypeDAO;
	}

	@Override
	public List<Designation> getDesignationDetails(boolean isElected,
			int tierSetupCode) throws Exception {

		List<Designation> designationDAO = localBodySetupDAO
				.getDesignationDetails(isElected, tierSetupCode);
		if(!designationDAO.isEmpty())
		{
		List<Designation> lstDesignation = new ArrayList<Designation>();
		Designation designationForm = new Designation();
          
		String desigE = null;
		String desigL = null;

		desigE = designationDAO.get(0).isIsmultiple() + ","
				+ designationDAO.get(0).getDesignationName() + "~";
		desigL = designationDAO.get(0).getDesignationNameLocal() + ","
				+ designationDAO.get(0).getDesignationCode() + "~";

		if (designationDAO.size() > 1) {
			for (int k = 1; k < designationDAO.size(); k++) {
				desigE += designationDAO.get(k).isIsmultiple() + ","
						+ designationDAO.get(k).getDesignationName() + "~";
				desigL += designationDAO.get(k).getDesignationNameLocal() + ","
						+ designationDAO.get(k).getDesignationCode() + "~";
			}
		}
		designationForm.setDesignationName(desigE);
		designationForm.setDesignationNameLocal(desigL);

		lstDesignation.add(designationForm);
		return lstDesignation;}
		else
		{
			return null;
		}
	}

	@Override
	public List<LocalBodyType> getLGBList(int localBodyTypeCode)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalbodyTypeInState> getActiveLBTinState(int stateCode,
			char category) throws Exception {
		// TODO getActiveLBTinState
		List<LocalbodyTypeInState> listLBT = null;
		listLBT = new ArrayList<LocalbodyTypeInState>();
		try {
			listLBT = localBodySetupDAO.getLBList(stateCode, category);
		} catch (Exception e) {
			log.debug("Exception" + e);
			listLBT = null;
		}
		return listLBT;
	}

	/*
	 * @Override public List<LocalBodyType> getActiveLBTinState(int stateCode) {
	 * // TODO getActiveLBTinState List <LocalBodyType> listLBT=null;
	 * listLBT=new ArrayList<LocalBodyType>(); int
	 * stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode); String
	 * sql="SELECT local_body_type.local_body_type_code," +
	 * "local_body_type.local_body_type_name FROM  public.local_body_type, " +
	 * "public.local_body_tiers_setup,  public.local_body_setup WHERE" +
	 * " local_body_tiers_setup.local_body_type_code = local_body_type.local_body_type_code "
	 * +
	 * "AND local_body_setup.local_body_setup_code = local_body_tiers_setup.local_body_setup_code AND "
	 * +
	 * "local_body_setup.local_body_setup_version = local_body_tiers_setup.local_body_setup_version AND "
	 * + " local_body_setup.state_code = '"+stateCode+
	 * "' AND local_body_setup.state_version = '"+stateVersion+"'";
	 * 
	 * try { listLBT=localBodySetupDAO.getLBList(sql); } catch (Exception e) {
	 * // TODO Auto-generated catch block log.debug("Exception" + e); listLBT=null; }
	 * return listLBT; }
	 */

	@Override
	public boolean saveLocalBodySubType(LGSetupForm lGSetupForm, int stateCode,
			HttpSession httpSession) throws Exception {
		// TODO saveLocalBodySubType

		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();

		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();

		String data = lGSetupForm.getSubTyp();

		String[] tempData = data.split("~");
		String[] tempTempData = null;
		String[] NomenSTData = null;
		String subtypeNameEng = null;
		String subtypeNameLocal = null;
		int tierSetupCode;
		LocalBodySubtype localBodySubtypeBean = null;

		try {
			tx = session.beginTransaction();
			for (int i = 0; i < tempData.length; i++) {

				if (i % 2 != 0) {
					if (!tempData[i].equals("")) {
						tempTempData = tempData[i].split(":");

						for (int j = 0; j < tempTempData.length; j++) {
							NomenSTData = tempTempData[j].split("%");
							tierSetupCode = Integer.parseInt(tempData[i - 1]);

							if (NomenSTData.length > 0) {
								subtypeNameEng = NomenSTData[0];
							}
							if (NomenSTData.length == 2) {
								subtypeNameLocal = NomenSTData[1];
							}

							if (NomenSTData.length > 0) {
								localBodySubtypeBean = new LocalBodySubtype();
								localBodySubtypeBean.setSubtypeNameEng(subtypeNameEng);
								localBodySubtypeBean.setSubtypeNameLocal(subtypeNameLocal);
								LocalBodyTiersSetup localBodyTiersSetup = new LocalBodyTiersSetup(tierSetupCode);
								localBodySubtypeBean.setLocalBodyTiersSetup(localBodyTiersSetup);
								// localBodySubtypeBean.setTierSetupCode(tierSetupCode);
								localBodySubtypeBean.setCreatedby(rolecode);
								localBodySubtypeBean.setCreatedon(time);
								localBodySubtypeBean.setIsactive(true);
								subtypeNameEng = null;
								subtypeNameLocal = null;
								try {
									localBodySetupDAO.saveLBSubType(localBodySubtypeBean, session);
									// tx.commit();
								} catch (Exception e) {
									log.debug("Exception" + e);
									tx.rollback();
									return false;
								}
							}
						}
					}

				}
			}
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if(tx != null){
				tx.rollback();
			}	
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	@Override
	public List<LocalBodyType> loopThroughTiers(LGSetupForm lGSetupForm)
			throws Exception {
		// TODO loopThroughTiers
		List<LocalBodyType> localBodyTypeList = null;
		localBodyTypeList = new ArrayList<LocalBodyType>();

		String check = lGSetupForm.getCheck();
		String LocalBodyTypeCode = lGSetupForm.getTemp1();
		String NomenEnglishList = lGSetupForm.getNomenEnglish();
		String NomenLocalList = lGSetupForm.getNomenLocal();
		String LevelLGBList = lGSetupForm.getLevelLGB();
		String LocalBodyTypeNameList = lGSetupForm.getLocalBodyTypeName();
		String[] tempcheckList = null;
		String[] tempLocalBodyTypeCodeList = null;
		String[] tempNomenEnglishList = null;
		String[] tempNomenLocalList = null;
		String[] tempLevelLGBList = null;
		String[] tempLocalBodyTypeNameList = null;
		if (check != null) {
			tempcheckList = check.split(",");
		}

		if (LocalBodyTypeCode != null) {
			tempLocalBodyTypeCodeList = LocalBodyTypeCode.split(",");
		}

		if (NomenEnglishList != null) {
			tempNomenEnglishList = NomenEnglishList.split(",");
		}

		if (NomenLocalList != null) {
			tempNomenLocalList = NomenLocalList.replace(",", ", ").split(",");
		}

		if (LevelLGBList != null) {
			tempLevelLGBList = LevelLGBList.split(",");
		}

		if (LocalBodyTypeNameList != null) {
			tempLocalBodyTypeNameList = LocalBodyTypeNameList.split(",");
		}

		LocalBodyType localBodyTypeBean = null;
		int j = 0;
		String tempConcat = null;
		if (tempcheckList != null) {
			for (int i = 0; i < tempLocalBodyTypeCodeList.length; i++) {
				localBodyTypeBean = new LocalBodyType();
				if (tempcheckList[j].equals(Integer.toString(i))) {
					localBodyTypeBean.setLocalBodyTypeCode(Integer
							.parseInt(tempLocalBodyTypeCodeList[i]));
					tempConcat = tempLocalBodyTypeCodeList[i] + ":"
							+ tempLevelLGBList[i] + ":"
							+ tempNomenEnglishList[j];
					tempConcat += ":" + tempNomenLocalList[j];
					localBodyTypeBean.setTemp(tempConcat);
					localBodyTypeBean
							.setLocalBodyTypeName(tempLocalBodyTypeNameList[i]);
					localBodyTypeList.add(localBodyTypeBean);
					if (j < tempcheckList.length - 1) {
						j++;
					}
				}
			}
		}
		return localBodyTypeList;
	}

	@Override
	public boolean modify(int stateCode,
			List<List<LocalBodyType>> localBodyTypeListToSave, char category)
			throws Exception {
		
		// TODO modify
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		boolean success = false;
		boolean temp = false;
		boolean temp1 = false;
		boolean temp3 = false;
		boolean temp4 = false;
		//boolean temp5 = false;
		int localBodySetupCode = 0;
		int localBodySetupVersion = 0;
		List<LocalBodyType> lbtList = null;
		int localBodyTypeCode = 0;
		String localBodyNomenLocal = null;
		String localBodyNomenEng = null;
		List<LocalBodySetup> localBodySetupList = null;
		int tierSetupCode = 0;
		Session session = null;
		Transaction tx = null;
		Integer slc=commonService.getSlc(stateCode);
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			localBodySetupList = new ArrayList<LocalBodySetup>();

			int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
			tierSetupCode = localBodySetupDAO
					.getMaxRecords("select COALESCE(max(tier_setup_code),1) from local_body_tiers_setup");
			localBodySetupList = localBodySetupDAO.getLocalBodySetupDetails(
					stateCode, stateVersion, category);
			localBodySetupCode = localBodySetupList.get(0)
					.getLocalBodySetupCode();
			localBodySetupVersion = localBodySetupList.get(0)
					.getLocalBodySetupVersion();
			List<LocalBodyTiersSetup> localBodyTiersSetupList = localBodySetupDAO
					.getTierSetupDetails(localBodySetupCode,
							localBodySetupVersion);

			LocalBodySetup localBodySetupBean = null;
			localBodySetupBean = new LocalBodySetup();
			LocalBodySetupPK localBodySetupPK = null;
			localBodySetupPK = new LocalBodySetupPK(localBodySetupCode,
					localBodySetupVersion);
			localBodySetupBean.setLocalBodySetupPK(localBodySetupPK);
			//State state = new State();
			//StatePK statePK = new StatePK(stateCode, stateVersion);
			//state.setStatePK(statePK);
			localBodySetupBean.setSlc(slc);
			localBodySetupBean.setCreatedBy(rolecode);
			localBodySetupBean.setCreatedOn(time);
			localBodySetupBean.setEffectiveDate(time);
			localBodySetupBean.setIsactive(false);
			localBodySetupBean.setLastUpdated(time);
			localBodySetupBean.setLastUpdatedBy(rolecode);
			// inactivate current setup
			temp = localBodySetupDAO.updateIsActiveSetup(localBodySetupBean,
					session);
			// inactivate current tier setup

			temp4 = localBodySetupDAO.updateIsActiveTierSetUp(false,
					localBodySetupCode, localBodySetupVersion, session);

			for (int i = 0; i < localBodyTiersSetupList.size(); i++) {
				//temp5 = 
				localBodySetupDAO.updateIsActiveLbSubtype(false,
						localBodyTiersSetupList.get(i).getTierSetupCode(),
						session);
			}
			// insert new setup
			temp1 = this
					.setupTableObject(localBodySetupCode,
							localBodySetupVersion + 1, session, stateCode,
							stateVersion);
			for (int i = 0; i < localBodyTypeListToSave.size(); i++) {
				int parentTierSetupCode = 0;
				lbtList = new ArrayList<LocalBodyType>();
				lbtList = localBodyTypeListToSave.get(i);

				for (int j = 0; j < lbtList.size(); j++) {
					String[] temp2 = lbtList.get(j).getTemp().split(":");
					localBodyTypeCode = Integer.parseInt(temp2[0]);
					localBodyNomenEng = temp2[2].trim();
					if (temp2.length == 4) {
						localBodyNomenLocal = temp2[3].trim();
					}
					if (localBodySetupCode != 0) {
						// insert new tiersetup
						temp3 = this.tierSetupTableObject(localBodyNomenLocal,
								localBodyNomenEng, localBodyTypeCode,
								parentTierSetupCode, localBodySetupCode,
								localBodySetupVersion + 1, tierSetupCode,
								session,slc);
						parentTierSetupCode = tierSetupCode;
						tierSetupCode += 1;
					}
					localBodyNomenLocal = null;

				}

			}

			if (temp && temp1 && temp3 && temp4) {
				tx.commit();
				success = true;
			}
		} catch (HibernateException e) {
			if(tx != null){
				tx.rollback();
			}	
			log.debug("Exception" + e);
			success = false;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return success;
	}

	@Override
	public boolean modifyUrban(int stateCode,
			List<List<LocalBodyType>> localBodyTypeListToSave, char category)
			throws Exception {
		
				
		// TODO modify
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		boolean success = false;
		boolean temp = false;
		boolean temp1 = false;
		boolean temp3 = false;
		boolean temp4 = false;
		//boolean temp5 = false;
		int localBodySetupCode = 0;
		int localBodySetupVersion = 0;
		List<LocalBodyType> lbtList = null;
		int localBodyTypeCode = 0;
		String localBodyNomenLocal = null;
		String localBodyNomenEng = null;
		List<LocalBodySetup> localBodySetupList = null;
		int tierSetupCode = 0;
		Session session = null;
		Transaction tx = null;
		Integer slc=commonService.getSlc(stateCode);
		session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();

			localBodySetupList = new ArrayList<LocalBodySetup>();

			int stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
			tierSetupCode = localBodySetupDAO
					.getMaxRecords("select COALESCE(max(tier_setup_code),1) from local_body_tiers_setup");
			localBodySetupList = localBodySetupDAO.getLocalBodySetupDetails(
					stateCode, stateVersion, category);
			localBodySetupCode = localBodySetupList.get(0).getLocalBodySetupCode();
			localBodySetupVersion = localBodySetupList.get(0)
					.getLocalBodySetupVersion();
			List<LocalBodyTiersSetup> localBodyTiersSetupList = localBodySetupDAO
					.getTierSetupDetails(localBodySetupCode,
							localBodySetupVersion);

			LocalBodySetup localBodySetupBean = null;
			localBodySetupBean = new LocalBodySetup();
			LocalBodySetupPK localBodySetupPK = null;
			localBodySetupPK = new LocalBodySetupPK(localBodySetupCode,
					localBodySetupVersion);
			localBodySetupBean.setLocalBodySetupPK(localBodySetupPK);
			State state = new State();
			StatePK statePK = new StatePK(stateCode, stateVersion);
			state.setStatePK(statePK);
			//Integer slc=this.getSlcfromState(stateCode, stateVersion);
			//System.out.print(">>>>>>>>>>>>>>>>>>>>>>"+states.get(0).getSlc());
			state.setSlc(slc);
			
			localBodySetupBean.setSlc(slc);
			localBodySetupBean.setCreatedBy(rolecode);
			localBodySetupBean.setCreatedOn(time);
			localBodySetupBean.setEffectiveDate(time);
			localBodySetupBean.setIsactive(false);
			localBodySetupBean.setLastUpdated(time);
			localBodySetupBean.setLastUpdatedBy(rolecode);
			// inactivate current setup
			temp = localBodySetupDAO.updateIsActiveSetup(localBodySetupBean,
					session);
			// inactivate current tier setup

			temp4 = localBodySetupDAO.updateIsActiveTierSetUp(false,localBodySetupCode, localBodySetupVersion, session);

			for (int i = 0; i < localBodyTiersSetupList.size(); i++) {
				//temp5 = 
				localBodySetupDAO.updateIsActiveLbSubtype(false,
						localBodyTiersSetupList.get(i).getTierSetupCode(),
						session);
			}
			// insert new setup
			temp1 = this
					.setupTableObject(localBodySetupCode,
							localBodySetupVersion + 1, session, stateCode,
							stateVersion);
			for (int i = 0; i < localBodyTypeListToSave.size(); i++) {
				int parentTierSetupCode = 0;
				lbtList = new ArrayList<LocalBodyType>();
				lbtList = localBodyTypeListToSave.get(i);

				for (int j = 0; j < lbtList.size(); j++) {
					String[] temp2 = lbtList.get(j).getTemp().split(":");
					localBodyTypeCode = Integer.parseInt(temp2[0]);
					localBodyNomenEng = temp2[2].trim();
					if (temp2.length == 4) {
						localBodyNomenLocal = temp2[3].trim();
					}
					if (localBodySetupCode != 0) {
						// insert new tiersetup
						temp3 = this.tierSetupTableObject(localBodyNomenLocal,
								localBodyNomenEng, localBodyTypeCode,
								parentTierSetupCode, localBodySetupCode,
								localBodySetupVersion + 1, tierSetupCode,
								session,slc);
						parentTierSetupCode = 0;
						tierSetupCode += 1;
					}
					localBodyNomenLocal = null;

				}

			}

			if (temp && temp1 && temp3 && temp4) {
				tx.commit();
				success = true;
			}
		} catch (HibernateException e) {
			if(tx != null){
				tx.rollback();
			}	
			log.debug("Exception" + e);
			success = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}
		return success;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationDesignation> isLBReportingOrganExist(Integer designationCode) throws Exception {
		Session session = null;
		
		session = sessionFactory.openSession();
		List<OrganizationDesignation> desigList = null;
		OrganizationDesignation desig = new OrganizationDesignation();
		List<ReportingOrgSetup> reportingList = null;
		try {
			if(designationCode!=0)
			{
			desigList=new ArrayList<OrganizationDesignation>();
			reportingList = session.createQuery(
					"from ReportingLbSetup where reportTo="
							+ designationCode).list();
			
			desig.setDesignationCode(reportingList.get(0).getId());
			desigList.add(desig);
			}
		
			
			return desigList;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return desigList;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean getParentTierSetupList(int tierSetupCode) throws Exception {
		Session session = null;
		boolean valReturn=true;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from LocalBodyTiersSetup where tierSetupCode= :tierSetupCode and isactive = true");
			query.setParameter("tierSetupCode", tierSetupCode);
			List<LocalBodyTiersSetup>tierSetupList = query.list();
				
			if (!tierSetupList.isEmpty()) {
				LocalBodyTiersSetup element = tierSetupList.get(0);
				if(element.getParentTierSetupCode() != null){
					valReturn=false;
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return valReturn;
	}
	
	
	/*@Override
	public List<Designation> getTierPanchayat(List<GetLocalGovtSetup> tierSetupList) throws Exception 
	{
		List<Designation> getDesignationPanchayatList = null;
		Session session = null;
		getDesignationPanchayatList = new ArrayList<Designation>();
		getDesignationPanchayatList = localBodySetupDAO.getTierPanchayat(tierSetupList,session);
		if (getDesignationPanchayatList.size() > 0)
		{
			return getDesignationPanchayatList;
		}
		return getDesignationPanchayatList;
	}*/
	/*@Override
	public List<Designation> getTierTraditional(List<GetLocalGovtSetup> tierSetupList) throws Exception 
	{
		List<Designation> getDesignationPanchayatList = null;
		Session session = null;
		getDesignationPanchayatList = new ArrayList<Designation>();
		getDesignationPanchayatList = localBodySetupDAO.getTierTraditional(tierSetupList,session);
		if (getDesignationPanchayatList.size() > 0)
		{
			return getDesignationPanchayatList;
		}
		return getDesignationPanchayatList;
	}*/
	
	/*@Override
	public List<Designation> getTierUrban(List<GetLocalGovtSetup> tierSetupList) throws Exception 
	{
		List<Designation> getDesignationPanchayatList = null;
		Session session = null;
		getDesignationPanchayatList = new ArrayList<Designation>();
		getDesignationPanchayatList = localBodySetupDAO.getTierUrban(tierSetupList,session);
		if (getDesignationPanchayatList.size() > 0)
		{
			return getDesignationPanchayatList;
		}
		return getDesignationPanchayatList;
	}*/
	
	
	
	
	

	
	
	
	
	public List<GetLocalGovtSetup> getLocalbodyDetail(int stateCode)
			throws Exception {
		
		// int stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		getLocalGovtSetupList = localBodySetupDAO.getLocalbodyDetailDAO(stateCode);
		
			if (getLocalGovtSetupList.size() > 0) 
			{
				Iterator<GetLocalGovtSetup> itr = getLocalGovtSetupList.iterator();
				
				String localBodyTypeCode=null;
					while (itr.hasNext()) 
					{
						
						GetLocalGovtSetup element = (GetLocalGovtSetup) itr.next();
						localBodyTypeCode=element.getLocalBodyTypeCode()+":"+element.getLevel()+":"+stateCode+":"+element.getCategory()+":"+element.getParentTierSetupCode()+":"+element.getLocalBodyTypeName();
						element.setNomenclatureEnglish(localBodyTypeCode);
						
					}
					
				/*String LocalbodyCode=localBodySetupDAO.getLocalBodyTypeCode1(localbodyTypes);*/
				return getLocalGovtSetupList;
		}
		return getLocalGovtSetupList;
	}

	public List<GetLocalGovtSetup> getLocalbodyDetail(int stateCode, String localGovtListFlag)
			throws Exception {
		
		// int stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		getLocalGovtSetupList = localBodySetupDAO.getLocalbodyDetailDAO(stateCode,localGovtListFlag);
		
			if (getLocalGovtSetupList.size() > 0) 
			{
				Iterator<GetLocalGovtSetup> itr = getLocalGovtSetupList.iterator();
				
				String localBodyTypeCode=null;
					while (itr.hasNext()) 
					{
						
						GetLocalGovtSetup element = (GetLocalGovtSetup) itr.next();
						localBodyTypeCode=element.getLocalBodyTypeCode()+":"+element.getLevel()+":"+stateCode+":"+element.getCategory()+":"+element.getParentTierSetupCode()+":"+element.getLocalBodyTypeName();
						element.setNomenclatureEnglish(localBodyTypeCode);
						
					}
					
				/*String LocalbodyCode=localBodySetupDAO.getLocalBodyTypeCode1(localbodyTypes);*/
				return getLocalGovtSetupList;
		}
		return getLocalGovtSetupList;
	}
	

	/**
	 * 
	 * @param removedCodes , comma separated
	 * @param stateCode
	 * @return is updated local bodies for urban
	 * @author vinay yadav 15-11-2012 17:25:00
	 */
	@Override
	public boolean updateUrbanLocalBodies(String removedCodes, Integer stateCode, Integer userId) {
		// TODO Auto-generated method stub
		return LgTypeDao.updateUrbanLocalBodies(removedCodes, stateCode, userId);
	}

	/**
	 * 
	 * @param opration
	 * @param localBodyTypeCodes , comma separated
	 * @param stateCode
	 * @return is updated local bodies for panchyat as per operation (added / removed)
	 * @author vinay yadav 21-11-2012 15:25:00
	 */
	@Override
	public boolean updateRuralLocalBodies(String opration, String localBodyTypeCodes, Integer stateCode, Integer userId) {
		// TODO Auto-generated method stub
		return LgTypeDao.updateRuralLocalBodies(opration, localBodyTypeCodes, stateCode, userId);
	}
	
	
	public List<GetLocalGovtSetup> getLocalbodyDetailbyCode(int stateCode,int lblc)
			throws Exception {
		
		// int stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		List<GetLocalGovtSetup> getLocalGovtSetupList1 = null;
		getLocalGovtSetupList1 = new ArrayList<GetLocalGovtSetup>();
		getLocalGovtSetupList = localBodySetupDAO.getLocalbodyDetailDAO(stateCode);
		GetLocalGovtSetup getLocalGovtSetup =new GetLocalGovtSetup();
			if (getLocalGovtSetupList.size() > 0) 
			{
				Iterator<GetLocalGovtSetup> itr = getLocalGovtSetupList.iterator();
				
				//String localBodyTypeCode=null;
					while (itr.hasNext()) 
					{
						
						GetLocalGovtSetup element = (GetLocalGovtSetup) itr.next();
					    if(element.getTierSetupCode()==lblc)
					    {
					    	getLocalGovtSetup.setCategory(element.getCategory());
					    	getLocalGovtSetup.setLevel(element.getLevel());
					    	getLocalGovtSetup.setLocalBodyTypeCode(element.getLocalBodyTypeCode());
					    	getLocalGovtSetup.setLocalBodyTypeName(element.getLocalBodyTypeName());
					    	getLocalGovtSetup.setNomenclatureEnglish(element.getNomenclatureEnglish());
					    	getLocalGovtSetup.setNomenclatureLocal(element.getNomenclatureLocal());
					    	getLocalGovtSetup.setParentLocalBodyTypeName(element.getParentLocalBodyTypeName());
					    	getLocalGovtSetup.setParentTierSetupCode(element.getParentTierSetupCode());
					    	getLocalGovtSetup.setTierSetupCode(element.getTierSetupCode());
					    	
					    }
						
					}
					getLocalGovtSetupList1.add(0, getLocalGovtSetup);
					
					/*String LocalbodyCode=localBodySetupDAO.getLocalBodyTypeCode1(localbodyTypes);*/
				return getLocalGovtSetupList1;
		}
		return getLocalGovtSetupList1;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Designation> isLBReportingExist(Integer designationCode)
			throws Exception {
		Session session = null;
		
		session = sessionFactory.openSession();
		List<Designation> desigList = null;
		Designation desig = new Designation();
		List<ReportingLbSetup> reportingList = null;
		try {
			if(designationCode!=0)
			{
			
			reportingList = session.createQuery(
					"from ReportingLbSetup where reportTo=:designationCode  or (designationCode=:designationCode and reportTo!=0)")
					.setParameter("designationCode", designationCode).list();
			
		
				if(!reportingList.isEmpty())
				{
				desigList=new ArrayList<Designation>();
				desig.setDesignationCode(reportingList.get(0).getId());
				desigList.add(desig);
				}
			}
		
			
			return desigList;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return desigList;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdDesignation> isOrganizationReportingExist(Integer designationCode) throws Exception {
		Session session = null;
		List<LgdDesignation> organdesigList = null;
		try {
			session = sessionFactory.openSession();
			if(designationCode!=0)
			{
			
				List<ReportingSetup> reportingOrgList = session.createQuery(
					"from ReportingSetup where reportTo=:designationCode  or (designationCode=:designationCode and reportTo!=0)")
					.setParameter("designationCode", designationCode).list();
			
		
				if(!reportingOrgList.isEmpty())
				{
					organdesigList=new ArrayList<LgdDesignation>();
					LgdDesignation organdesig = new LgdDesignation();
					//organdesig.setDesignationCode(reportingOrgList.get(0).getId());
					organdesigList.add(organdesig);
				}
			}
			return organdesigList;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return organdesigList;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkDesignationPesDelete(Integer designationCode) throws Exception 
	{
		Session session = null;
		boolean valReturn=false;
		try 
		{
			session = sessionFactory.openSession();
			
			List<DesignationPesDelete> designationPesDeleteList = session
					.getNamedQuery("checkDesignationPesDelete")
					.setParameter("designationCode", designationCode).list();
			
			if (designationPesDeleteList.size() > 0) 
			{
				valReturn=designationPesDeleteList.get(0).isPesflag();
				
				if(valReturn==true)
				{
					List<DesignationDelete> designationDeleteList = session
							.getNamedQuery("deleteDesignation")
							.setParameter("designationCode", designationCode).list();
					
					if(!designationDeleteList.isEmpty())
					{
						valReturn=designationDeleteList.get(0).isPesflag();
					}
					
					
				}
			}
			
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return valReturn;
	}

	
	@Override
	public boolean checkOrganDesignationDelete(Integer designationCode) throws Exception 
	{
		Session session = null;
		boolean valReturn=true;
		try 
		{
			session = sessionFactory.openSession();
			
			int i=session.createQuery("delete from OrganizationDesignation where designationCode="+ designationCode).executeUpdate();
			if(i<=0)
			{
				valReturn=false;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return valReturn;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkDesignationPesDeleteM(Integer designationCode) throws Exception 
	{
		Session session = null;//,session1=null;
		boolean valReturn=false;
		try 
		{
			session = sessionFactory.openSession();
			//session1 = sessionFactory.openSession();
			List<DesignationPesDelete> designationPesDeleteList = session
					.getNamedQuery("checkDesignationPesDelete")
					.setParameter("designationCode", designationCode).list();
			
			if (designationPesDeleteList.size() > 0) 
			{
				valReturn=designationPesDeleteList.get(0).isPesflag();
				
				
			}
			
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
			/*if (session1 != null && session1.isOpen()){
				session1.close();
			}*/
		}
		return valReturn;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Designation> getTopDesignation(int lgTypeCode) throws Exception {
		Session session = null;
		List list = null;
		Query query;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Designation where tierSetupCode="
					+ lgTypeCode
					+ " and istopdesignation=true and islocalbody=false");
			list = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return list;
	}
	
	
	public boolean checkTierPanchayatforDesignation(List<GetLocalGovtSetup> tierSetupList,Integer stateCode) throws Exception
	{
		return (localBodySetupDAO.checkTierPanchayatforDesignation(tierSetupList, stateCode));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Designation getParentTierSetupCodeforDes(int desCode) throws Exception {
		Designation desig = new Designation();
		String temp = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			List<Designation> desList  = session.createQuery("from Designation where designationCode="+ desCode).list();
			
			if(desList.size()>0)
			{
				temp=String.valueOf(desList.get(0).getTierSetupCode());
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		desig.setDesignationName(temp);
		return desig;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Designation> isLbReportRefExist(Integer designationCode)
			throws Exception {
		Session session = null;
		
		session = sessionFactory.openSession();
		List<Designation> desigList = null;
		Designation desig = new Designation();
		List<ReportingLbSetup> reportingList = null;
		try {
			if(designationCode!=0)
			{
			desigList=new ArrayList<Designation>();
			reportingList = session.createQuery(
					"from ReportingLbSetup where reportTo="
							+ designationCode).list();
				if(reportingList.size()>0)
				{
				desig.setDesignationCode(reportingList.get(0).getId());
				desigList.add(desig);
				}
				else
				{
					int i=session.createQuery(
							"delete from ReportingLbSetup where designationCode="
									+ designationCode).executeUpdate();
					if(i<=0)
					{
						desig.setDesignationCode(0);
						desigList.add(desig);
					}
					
				}
			}
			
			return desigList;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return desigList;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
	}

	@Override
	public LocalBodyTiersSetup loadTierSetUp(Integer localBodyTypeCode, Integer tiresetupCode) {
		// TODO Auto-generated method stub
		return LgTypeDao.loadTierSetUp(localBodyTypeCode, tiresetupCode);
	}

	@Override
	public Integer maxTierSetupCode() {
		// TODO Auto-generated method stub
		return LgTypeDao.maxTierSetupCode();
	}

	@Override
	public Object[] localbodySetupCodeandVersion(Integer statecode, Character category) {
		// TODO Auto-generated method stub
		return LgTypeDao.localbodySetupCodeandVersion(statecode, category);
	}

	@Override
	public boolean saveOrUpdateTierSetup(LocalBodyTiersSetup setup) {
		// TODO Auto-generated method stub
		return LgTypeDao.saveOrUpdateTierSetup(setup);
	}

	@Override
	public LocalBodySetup getLocalbodySetup(Integer statecode, Integer localBodySetupCode) {
		// TODO Auto-generated method stub
		return LgTypeDao.getLocalbodySetup(statecode, localBodySetupCode);
	}

	@Override
	public List<Object[]> getLocalBodyTypesRural() {
		// TODO Auto-generated method stub
		return LgTypeDao.getLocalBodyTypesRural();
	}

	//-------------------------------------------------------------------------------------------------
	
	@Override
	public List<Object[]> getDefinedTiersSetup(Integer statecode) {
		// TODO Auto-generated method stub
		return LgTypeDao.getDefinedTiersSetup(statecode);
	}

	@Override
	public LocalBodySetup addNewSetup(Integer stateCode, Integer userId) {
		// TODO Auto-generated method stub
		return LgTypeDao.addNewSetup(stateCode, userId);
	}

	@Override
	public LocalBodySetup invalidateExistingSetup(Integer setupCode, Integer stateCode, Integer userId) {
		// TODO Auto-generated method stub
		return LgTypeDao.invalidateExistingSetup(setupCode, stateCode, userId);
	}

	@Override
	public Integer getParentCodeTierSetup(Integer localbodyType, Integer setupcode, Integer setupversion) {
		// TODO Auto-generated method stub
		return LgTypeDao.getParentCodeTierSetup(localbodyType, setupcode, setupversion);
	}
	
	/*@Override
	public boolean saveOrUpdateLBSetup(LocalBodySetup lbsetup) {
		// TODO Auto-generated method stub
		return LgTypeDao.saveOrUpdateLBSetup(lbsetup);
	}*/
	/**
	 * Added by ripunj on 06-02-2014
	 */
	@Override
	public List<GetLocalGovtSetup> getLocalbodyDetailDAOOrderInLocalBodyType(
			int stateCode) throws Exception {
		// TODO Auto-generated method stub
		return localBodySetupDAO.getLocalbodyDetailDAOOrderInLocalBodyType(stateCode);
	}
	
	public List<GetLocalGovtSetup> getLocalbodyDetailForVillage(int stateCode)throws Exception{
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		getLocalGovtSetupList = localBodySetupDAO.getLocalbodyDetailForVillage(stateCode);
		
			if (getLocalGovtSetupList.size() > 0) 
			{
				Iterator<GetLocalGovtSetup> itr = getLocalGovtSetupList.iterator();
				
				String localBodyTypeCode=null;
					while (itr.hasNext()) 
					{
						
						GetLocalGovtSetup element = (GetLocalGovtSetup) itr.next();
						localBodyTypeCode=element.getLocalBodyTypeCode()+":"+element.getLevel()+":"+stateCode+":"+element.getCategory()+":"+element.getParentTierSetupCode()+":"+element.getLocalBodyTypeName();
						element.setNomenclatureEnglish(localBodyTypeCode);
						
					}
					
				/*String LocalbodyCode=localBodySetupDAO.getLocalBodyTypeCode1(localbodyTypes);*/
				return getLocalGovtSetupList;
		}
			return getLocalGovtSetupList;
		
	}

	@Override
	public List<GetLocalGovtSetup> getLocalbodyDetailForTraditional(int stateCode, char category) throws Exception {
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		getLocalGovtSetupList = localBodySetupDAO.getLocalbodyDetailForTraditional(stateCode, category);
		
			if (getLocalGovtSetupList.size() > 0) 
			{
				Iterator<GetLocalGovtSetup> itr = getLocalGovtSetupList.iterator();
				
				String localBodyTypeCode=null;
					while (itr.hasNext()) 
					{
						
						GetLocalGovtSetup element = (GetLocalGovtSetup) itr.next();
						localBodyTypeCode=element.getLocalBodyTypeCode()+":"+element.getLevel()+":"+stateCode+":"+element.getCategory()+":"+element.getParentTierSetupCode()+":"+element.getLocalBodyTypeName();
						element.setNomenclatureEnglish(localBodyTypeCode);
						
					}
					
				/*String LocalbodyCode=localBodySetupDAO.getLocalBodyTypeCode1(localbodyTypes);*/
				return getLocalGovtSetupList;
		}
			return getLocalGovtSetupList;
	};
	
	
	public List<GetLocalGovtSetup> getLocalbodyDetailCategory(int stateCode,Character category){
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		try{
			getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
			getLocalGovtSetupList = localBodySetupDAO.getLocalbodyDetailCategoryDAO(stateCode,category);
				if (getLocalGovtSetupList.size() > 0) 
				{
					Iterator<GetLocalGovtSetup> itr = getLocalGovtSetupList.iterator();
					String localBodyTypeCode=null;
						while (itr.hasNext()) 
						{
							GetLocalGovtSetup element = (GetLocalGovtSetup) itr.next();
							localBodyTypeCode=element.getLocalBodyTypeCode()+":"+element.getLevel()+":"+stateCode+":"+element.getCategory()+":"+element.getParentTierSetupCode()+":"+element.getLocalBodyTypeName();
							element.setNomenclatureEnglish(localBodyTypeCode);
						}
					return getLocalGovtSetupList;
			}
		} catch (Exception e){
			throw new HibernateException(e);
		}
		return getLocalGovtSetupList;
	}
	
	public List<GetLocalGovtSetup> getLocalbodyDetailCantonment(int stateCode)
			throws Exception {
		
		// int stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		getLocalGovtSetupList = localBodySetupDAO.getLocalbodyDetailDAOCantonment(stateCode);
		
			if (getLocalGovtSetupList.size() > 0) 
			{
				Iterator<GetLocalGovtSetup> itr = getLocalGovtSetupList.iterator();
				
				String localBodyTypeCode=null;
					while (itr.hasNext()) 
					{
						
						GetLocalGovtSetup element = (GetLocalGovtSetup) itr.next();
						localBodyTypeCode=element.getLocalBodyTypeCode()+":"+element.getLevel()+":"+stateCode+":"+element.getCategory()+":"+element.getParentTierSetupCode()+":"+element.getLocalBodyTypeName();
						element.setNomenclatureEnglish(localBodyTypeCode);
						
					}
					
				/*String LocalbodyCode=localBodySetupDAO.getLocalBodyTypeCode1(localbodyTypes);*/
				return getLocalGovtSetupList;
		}
		return getLocalGovtSetupList;
	}
	
}
