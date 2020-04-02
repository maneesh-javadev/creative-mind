package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.CheckLocalBodyDraftState;
import in.nic.pes.lgd.bean.CheckLocalBodyFreezeStatus;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GetFreezeDataForLocalbody;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.LBFreezeUnfreeze;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyListbyStateold;
import in.nic.pes.lgd.bean.ParentWiseLocalBodiesold;
import in.nic.pes.lgd.bean.StandardCodes;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateFreeze;
import in.nic.pes.lgd.bean.VPDeNotifiedCoveraged;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillagePanchyatDeNotified;
import in.nic.pes.lgd.constant.DepartmentConstent;
import in.nic.pes.lgd.dao.StateDistrictFreezeDAO;
import in.nic.pes.lgd.forms.LBFreezeForm;
import in.nic.pes.lgd.service.LocalGovtBodyService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exception.BaseAppException;

/**
 * @author Kirandeep added on 1 Feb 2015 for stateFreeeze
 * 
 */

public class StateDistrictFreezeDAOImpl implements StateDistrictFreezeDAO {
	private static final Logger log = Logger.getLogger(StateDistrictFreezeDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	LocalGovtBodyService localGovtBodyService;

	@Override
	public boolean freezeUnFreezeFromState(String list, Integer user_id, String ip, Character entityType, Character lbLrType) {
		Session session = sessionFactory.openSession();
		Boolean status = false;
		try {
			/*
			 * SQLQuery query = session.createSQLQuery(
			 * "select * from freeze_unfreeze_entity_fn_for_State(:list,:user_id,:ip,:entityType)"
			 * );
			 */
			/*
			 * added by sunita 16-07-2015 for using StatePri function for Land
			 * Region
			 */
			SQLQuery query = session.createSQLQuery("select * from freeze_unfreeze_entity_fn_for_statePRI(:list,:user_id,:ip,:entityType,:lbLrType)");
			query.setParameter("list", list);
			query.setParameter("user_id", user_id);
			query.setParameter("ip", ip);
			query.setParameter("entityType", entityType);
			query.setParameter("lbLrType", lbLrType);
			status = Boolean.parseBoolean(query.uniqueResult().toString());
			
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	public List<Village> does_entity_child_in_draft_exist_fn(Integer dlc) throws Exception {
		Session session = null;
		List<Village> getVillageList = null;
		SQLQuery query = null;
		try {
			getVillageList = new ArrayList<Village>();
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select village_code as villageCode,village_name_english as villageNameEnglish from does_entity_child_in_draft_exist_fn(:entityType,:dlc,:villageType)");
			query.setParameter("entityType", 'D');
			query.setParameter("dlc", dlc, Hibernate.INTEGER);
			query.setParameter("villageType", 'V');
			query.addScalar("villageCode");
			query.addScalar("villageNameEnglish");

			query.setResultTransformer(Transformers.aliasToBean(Village.class));
			getVillageList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getVillageList;
	}

	@Override
	public Map<String, Object> getAllAttributeForStateFreeze(Integer slc, Character lbLrType) throws Exception {
		Session session = null;
		Map<String, Object> mapStateFreeze = new HashedMap();
		List<District> districtList = null;
		List<Object> objlist = new ArrayList<Object>();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(State.class).add(Restrictions.eq("statePK.stateCode", slc)).add(Restrictions.eq("isactive", true));
			State state = (State) criteria.uniqueResult();
			mapStateFreeze.put("stateName", state.getStateNameEnglish());
			session.flush();

			Query query = null;
			query = session.getNamedQuery("getStateFreezeDistList").setParameter("state_code", slc).setParameter("lb_lr_type", lbLrType);
			mapStateFreeze.put("districtList", query.list());
			session.flush();

			Query queryDlist = session.createQuery("from District d where d.slc=:slc and d.isactive=true order by districtNameEnglish");
			queryDlist.setParameter("slc", slc);
			districtList = queryDlist.list();
			SQLQuery sqlquery = session.createSQLQuery("select * from state_freeze where slc=:slc and status_by_state_user =1 and lb_lr_type=:lb_lr_type and entity_type ='D'");
			sqlquery.setParameter("slc", slc);
			sqlquery.setParameter("lb_lr_type", lbLrType);
			objlist = sqlquery.list();
			if (districtList.size() == objlist.size()) {
				mapStateFreeze.put("enableFreeze", false);
			} else {
				mapStateFreeze.put("enableFreeze", true);
			}
			session.flush();

			Criteria stateFreezeCriteria = session.createCriteria(StateFreeze.class).add(Restrictions.eq("slc", slc)).add(Restrictions.eq("entityTtype", 'S')).add(Restrictions.eq("entityCode", slc)).add(Restrictions.eq("lbLrType", lbLrType));
			StateFreeze stateFreeze = (StateFreeze) stateFreezeCriteria.uniqueResult();
			if (stateFreeze != null) {
				Integer statusCode = stateFreeze.getStatusByStateUser();
				if (statusCode == 1) {
					mapStateFreeze.put("showHideButton", true);
				}
				if (statusCode == 0) {
					mapStateFreeze.put("showHideButton", false);
				}
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return mapStateFreeze;

	}

	/**
	 * Save Local Body Freeze/Un-freeze.
	 * 
	 * @author Ashish Dhupia on 15-02-2015
	 * 
	 */

	public List<StandardCodes> getListforLBFreezeUnfreeze(LBFreezeForm lbfreeze, char Status) throws BaseAppException {
		Session session = null;
		Query query = null;
		String squery = null;
		Integer entityCode = null;
		int lbType = 0;
		int parentCode = 0;
		int disCode = 0;
		int parentType = 0;
		// List<GetLandRegionWise> districtPanchayatListDisUser = new
		// ArrayList<GetLandRegionWise>();
		List<StandardCodes> standardCodes = new ArrayList<StandardCodes>();
		// List<StandardCodes> standardCodesfordistrict = new
		// ArrayList<StandardCodes>();
		try {

			String entity = lbfreeze.getEntityName();
			{
				// Changes for traditional localbody hierarchy
				entityCode = Integer.parseInt(lbfreeze.getStateCode());
				String[] vc = entity.split(":");

				lbType = Integer.parseInt(vc[0]);
				parentType = Integer.parseInt(vc[4]);
				if (parentType == 0) {
					if (Status == 'N') {
						squery = "getStandardCodesforLB";
					} else {
						squery = "getStandardCodesforLBDistricts";
					}

				} else {
					if (lbType == 2 || lbType == 14) {
						squery = "getStandardCodesforLBWithParent";
						if (lbType == 2)
							parentCode = Integer.parseInt(lbfreeze.getDistrictPanchyat());
						if (lbType == 14)
							parentCode = Integer.parseInt(lbfreeze.getDistrictPanchyattrad());
					} else if (lbType == 3 || lbType == 11) {
						squery = "getStandardCodesforLBWithParent";
						if (lbfreeze.getTiertype().equals("3")) {
							if (lbType == 3)
								parentCode = Integer.parseInt(lbfreeze.getIntermediatePanchyat());
							if (lbType == 11)
								parentCode = Integer.parseInt(lbfreeze.getIntermediatePanchyattrad());
						} else {
							if (lbType == 3) {
								if (lbfreeze.getDistrictPanchyat() != null && !"".equals(lbfreeze.getDistrictPanchyat()))
									parentCode = Integer.parseInt(lbfreeze.getDistrictPanchyat());
								else if (lbfreeze.getIntermediatePanchyat() != null && !"".equals(lbfreeze.getIntermediatePanchyat()))
									parentCode = Integer.parseInt(lbfreeze.getIntermediatePanchyat());
							}
							if (lbType == 11) {
								if (lbfreeze.getDisPanchyattrad() != null && !"".equals(lbfreeze.getDisPanchyattrad()))
									parentCode = Integer.parseInt(lbfreeze.getDisPanchyattrad());
								else if (lbfreeze.getIntermediatePanchyattrad() != null && !"".equals(lbfreeze.getIntermediatePanchyattrad()))
									parentCode = Integer.parseInt(lbfreeze.getIntermediatePanchyattrad());
							}
						}
					} else
						squery = "getStandardCodesforLB";
				}
			}
			session = sessionFactory.openSession();
			if (lbType == 0)
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER);
			else if ((lbType == 3 || lbType == 2 || lbType == 11 || lbType == 14) && parentType != 0)
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER).setParameter("type", lbType, Hibernate.INTEGER).setParameter("parent", parentCode, Hibernate.INTEGER);
			else if (Status == 'N') {
				query = session.getNamedQuery(squery).setParameter("entityCode", entityCode, Hibernate.INTEGER).setParameter("type", lbType, Hibernate.INTEGER);
			} else {
				query = session.getNamedQuery(squery).setParameter("districtCode", Integer.parseInt(lbfreeze.getDistrictCode()), Hibernate.INTEGER).setParameter("type", lbType, Hibernate.INTEGER);
			}
			standardCodes = query.list();
			/*
			 * if (lbType==1 && !lbfreeze.getDistrictCode().contains(",") ) {
			 * disCode = Integer.parseInt(lbfreeze.getDistrictCode());
			 * districtPanchayatListDisUser
			 * =localGovtBodyService.getLandRegionWise(1,'D',disCode,"P");
			 * if(districtPanchayatListDisUser.size()==0){
			 * districtPanchayatListDisUser
			 * =localGovtBodyService.getLandRegionWise(1,'D',disCode,"T"); }
			 * for(StandardCodes obj:standardCodes) {
			 * lbType=obj.getEntityCode(); for(GetLandRegionWise
			 * val:districtPanchayatListDisUser) {
			 * if(val.getLocalBodyCode()==lbType) {
			 * standardCodesfordistrict.add(obj); break; }
			 * 
			 * } } return standardCodesfordistrict;
			 * 
			 * }
			 */

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return standardCodes;
	}

	public List<StandardCodes> getlBcoverage(Integer entitycode) throws BaseAppException {
		Session session = sessionFactory.openSession();
		List<StandardCodes> getVillageList = null;
		SQLQuery query = null;
		try {
			// query =
			// session.createSQLQuery("select array_to_string(array(select (CASE WHEN land_region_type='D' THEN 'District' WHEN land_region_type='T' THEN 'Sub District' WHEN land_region_type='V' then 'Village' end)||'-'||land_region_name_english||'('||land_region_code||')' from get_coverage_lb_list_fn (:entitycode)) ,',') as coverage,(select lblc from localbody where local_body_code=:entitycode and isactive ) as lblc");

			query = session
					.createSQLQuery("select array_to_string(array(select (CASE WHEN a.land_region_type='V' THEN (SELECT * FROM get_lr_coverage('V',a.land_region_code )) WHEN a.land_region_type='T' THEN (SELECT * FROM get_lr_coverage('T',a.land_region_code )) WHEN a.land_region_type='D' THEN (SELECT * FROM get_lr_coverage('D',a.land_region_code ))END) FROM  (SELECT * FROM   get_coverage_lb_list_fn(:entitycode)) a) ,',') as coverage ,(select lblc from localbody where local_body_code=:entitycode and isactive ) as lblc");
			// query.setParameter("entitycode", entitycode);
			query.setParameter("entitycode", entitycode, Hibernate.INTEGER);
			query.addScalar("coverage").addScalar("lblc");
			query.setResultTransformer(Transformers.aliasToBean(StandardCodes.class));
			getVillageList = query.list();
			return getVillageList;

		} catch (Exception e) {
			log.error("Exception-->" + e);
			// TODO: handle exception
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getVillageList;
	}

	@Override
	public List<StandardCodes> getlblcCode(Integer lblc) throws BaseAppException {
		Session session = sessionFactory.openSession();
		List getVillageList = null;
		List<StandardCodes> getVillageList1 = null;
		SQLQuery query = null;
		try {
			query = session.createSQLQuery("select status as status from localbody_freeze  where lblc=" + lblc);
			getVillageList = query.list();

			if (getVillageList != null && getVillageList.size() > 0) {
				StandardCodes standardCodes = new StandardCodes();
				standardCodes.setStatus(Integer.valueOf(getVillageList.get(0).toString()));
				getVillageList1 = new ArrayList<StandardCodes>();
				getVillageList1.add(standardCodes);
			}
			return getVillageList1;

		} catch (Exception e) {
			log.error("Exception-->" + e);
			// TODO: handle exception
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getVillageList1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentWiseLocalBodiesold> getchildlocalbodiesByParentcodeold(int localBodyCode) throws Exception {
		List<ParentWiseLocalBodiesold> childlocalbodiesByParentcodeList = null;
		Session session = null;
		Query query = null;
		session = sessionFactory.openSession();
		try {
			query = session.getNamedQuery("getLocalGovtBodyforParentold").setParameter("localBodyCode", localBodyCode);
			childlocalbodiesByParentcodeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return childlocalbodiesByParentcodeList;
	}

	@Override
	public boolean freezeLocalBody(String allList) throws Exception {
		Session session = sessionFactory.openSession();
		Boolean status = false;
		try {
			SQLQuery query = session.createSQLQuery("select * from freeze_unfreeze_entity_fn_for_localbody(:allList)");
			query.setParameter("allList", allList);
			status = Boolean.parseBoolean(query.uniqueResult().toString());
			
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return status;
	}

	public boolean chechUncheckLocalBodyFreeze(int lblc) throws Exception {

		Session session = sessionFactory.openSession();
		Boolean status = false;
		try {
			SQLQuery query = session.createSQLQuery("select * from does_localbody_in_draft_exist(:lblc)");
			query.setParameter("lblc", lblc);
			status = Boolean.parseBoolean(query.uniqueResult().toString());
			
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return status;
	}

	public List<LocalbodyListbyStateold> getExistingPanchayatListold(int lbTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyStateold> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getExistingPanchayatListbyState").setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER).setParameter("stateCode", stateCode, Hibernate.INTEGER);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	public List<LocalbodyListbyStateold> getExistingPanchayatList(int lbTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyStateold> districtPanchayatListold = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getExistingPanchayatListbyStateold").setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER).setParameter("stateCode", stateCode, Hibernate.INTEGER);
			districtPanchayatListold = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatListold;
	}

	/* added by kirandeep for state freeze/unfreeze pri */
	@Override
	public boolean freezeUnFreezeFromStateForPri(String list, Integer user_id, String ip, Character entityType, Character lbLrType) throws Exception {
		Session session = sessionFactory.openSession();
		Boolean status = false;
		try {
			SQLQuery query = session.createSQLQuery("select * from freeze_unfreeze_entity_fn_for_statePRI(:list,:user_id,:ip,:entityType,:lbLrType)");
			query.setParameter("list", list);
			query.setParameter("user_id", user_id);
			query.setParameter("ip", ip);
			query.setParameter("entityType", entityType);
			query.setParameter("lbLrType", lbLrType);
			status = Boolean.parseBoolean(query.uniqueResult().toString());
			
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return status;
	}

	/* addded by kirandeep for freeze/unfreeze pri */

	public List<CheckLocalBodyDraftState> doesLbChildInDraftExist(Integer dlc, Character lbLrType) throws BaseAppException {
		Session session = sessionFactory.openSession();
		Boolean status = false;
		Query query = null;
		List<CheckLocalBodyDraftState> checkLocalBodyDraftState = null;

		try {
			if (lbLrType == 'P') {
				query = session.getNamedQuery("doeslbPriDraftExist").setParameter("districtCode", dlc);
				checkLocalBodyDraftState = query.list();
			}

			if (lbLrType == 'T') {
				query = session.getNamedQuery("doeslbPriDraftExistTRI").setParameter("districtCode", dlc);
				checkLocalBodyDraftState = query.list();
			}

			if (lbLrType == 'U') {
				query = session.getNamedQuery("doeslbPriDraftExistUrb").setParameter("districtCode", dlc);
				checkLocalBodyDraftState = query.list();
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return checkLocalBodyDraftState;

	}

	/* addded by kirandeep for freeze/unfreeze pri */
	@Override
	public List<CheckLocalBodyFreezeStatus> doesLbChildInFreezExist(Integer dlc, Character lbLrType) throws BaseAppException {
		Session session = sessionFactory.openSession();
		Boolean status = false;
		Query query = null;
		List<CheckLocalBodyFreezeStatus> checkLocalBodyFreezState = null;
		/* added by kirandeep for freeze/unfreeze issues */
		List<CheckLocalBodyFreezeStatus> CheckLocalBodyFreezeStatusCopy = new ArrayList<CheckLocalBodyFreezeStatus>();

		try {

			if (lbLrType == 'P') {
				query = session.getNamedQuery("doeslbPriFreezExist").setParameter("districtCode", dlc);
				checkLocalBodyFreezState = query.list();
			}

			if (lbLrType == 'T') {
				query = session.getNamedQuery("doeslbTriFreezExist").setParameter("districtCode", dlc);
				checkLocalBodyFreezState = query.list();
			}

			if (lbLrType == 'U') {
				query = session.getNamedQuery("doeslbUrbFreezExist").setParameter("districtCode", dlc);
				checkLocalBodyFreezState = query.list();
			}
			/* added by kirandeep for freeze/unfreeze issues */
			for (CheckLocalBodyFreezeStatus CheckLocalBodyFreezeStatus2 : checkLocalBodyFreezState) {
				if (CheckLocalBodyFreezeStatus2.getStatus().equalsIgnoreCase("Status not set")) {
					CheckLocalBodyFreezeStatus2.setStatus("Unfreeze");
				}
				CheckLocalBodyFreezeStatusCopy.add(CheckLocalBodyFreezeStatus2);

			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return CheckLocalBodyFreezeStatusCopy;

	}

	@Override
	public boolean checkSaveStateStatus(Integer stateId, Character lb_lr_type) throws BaseAppException {

		Session session = sessionFactory.openSession();
		List<District> districtList = new ArrayList<District>();
		List<Object> objlist = new ArrayList<Object>();
		try {

			Query queryDlist = session.createQuery("from District d where d.slc=:slc and d.isactive=true order by districtNameEnglish");
			queryDlist.setParameter("slc", stateId);
			districtList = queryDlist.list();
			SQLQuery sqlquery = session.createSQLQuery("select * from state_freeze where slc=:slc and status_by_state_user =1 and lb_lr_type=:lb_lr_type and entity_type ='D'");
			sqlquery.setParameter("slc", stateId);
			sqlquery.setParameter("lb_lr_type", lb_lr_type);
			objlist = sqlquery.list();
			

		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		if (districtList.size() == objlist.size()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<Object[]> getNomenclature(Integer localbodyCode) throws Exception {
		Session session = sessionFactory.openSession();
		List<Object[]> nomenclature =null;
		try {
			SQLQuery query = session.createSQLQuery("select nomenclature_english,local_body_name_english,parent_name,state_name from get_panchayats_detailesby_lb(:localbodyCode)");
			query.setParameter("localbodyCode", localbodyCode);
			nomenclature = query.list();
			
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return nomenclature;
	}

	/**
	 * Added by Anchal for de-notify GPs
	 */
	public List<VillagePanchyatDeNotified> getListforLBtoDenotify(Integer localbodyCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<VillagePanchyatDeNotified> standardCodes = null;
		try {
			session = sessionFactory.openSession();
			query = (SQLQuery) session.getNamedQuery("getDeNotifiedVillagePanchayats").setParameter("localbodycode", localbodyCode, Hibernate.INTEGER);
			standardCodes = query.list();
		} catch (Exception e) {
			log.error("Exception in StateDistrictFreezeDaoImpl.getListforLBtoDenotify : ", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return standardCodes;
	}

	/**
	 * Added by Anchal for de-notify GPs
	 */
	public List<VillagePanchyatDeNotified> getListforULBGPtoDenotify(Integer tranId) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<VillagePanchyatDeNotified> getULBs = null;
		try {
			session = sessionFactory.openSession();
			query = (SQLQuery) session.getNamedQuery("getDeNotifiedVPULB").setParameter("tranId", tranId, Hibernate.INTEGER);
			getULBs = query.list();
		} catch (Exception e) {
			log.error("Exception in StateDistrictFreezeDaoImpl.getListforULBGPtoDenotify : ", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getULBs;
	}

	/**
	 * Added by Anchal for de-notify GPs
	 */
	public List<VillagePanchyatDeNotified> getListforRLBGPtoDenotify(Integer tranId) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<VillagePanchyatDeNotified> getRLBs = null;
		try {
			session = sessionFactory.openSession();
			query = (SQLQuery) session.getNamedQuery("getDeNotifiedVPRLB").setParameter("tranId", tranId, Hibernate.INTEGER);
			getRLBs = query.list();
		} catch (Exception e) {
			log.error("Exception in StateDistrictFreezeDaoImpl.getListforRLBGPtoDenotify : ", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getRLBs;
	}

	/**
	 * Added by Anchal for de-notify GPs
	 */
	public Integer getDenotifyVPCount(Integer tranId) throws Exception {
		Session session = null;
		SQLQuery query = null;
		int count = 0;
		try {
			session = sessionFactory.openSession();
			query = (SQLQuery) session.createSQLQuery("select count(1) from localbody where transaction_id=:tranId and isactive and local_body_type_code=3 ");
			query.setParameter("tranId", tranId, Hibernate.INTEGER);
			log.error("query-->" + query);
			count = Integer.parseInt(query.uniqueResult().toString());
		} catch (Exception e) {
			log.error("Exception in StateDistrictFreezeDaoImpl.getDenotifyVPCount : ", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return count;

	}

	/**
	 * Added by Anchal for de-notify GPs
	 */
	@Override
	public List<VPDeNotifiedCoveraged> getListforCoveraged(int localBodyCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<VPDeNotifiedCoveraged> getCLBs = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("select lrlc as datavalue,(case when land_region_type='T' then 'Subdistrict' when land_region_type='D' then 'District' end) land_region_type_display,(case when coverage_type='P' then 'Part' when coverage_type='F' then 'Full' end) Coverage_type_display,"
							+ "(CASE WHEN land_region_type='V' THEN (SELECT * FROM get_lr_coverage_for_inactive('V',lrlc )) WHEN land_region_type='T' THEN (SELECT * FROM get_lr_coverage_for_inactive('T',lrlc ))"
							+ "WHEN land_region_type='D' THEN (SELECT * FROM get_lr_coverage_for_inactive('D',lrlc ))END) Lrlc_display from lb_covered_landregion where lb_covered_region_code=(select lb_covered_region_code from localbody where local_body_code=:localBodyCode and isactive) ");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			getCLBs = query.list();
		} catch (Exception e) {
			log.error("Exception in StateDistrictFreezeDaoImpl.getListforCoveraged : ", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return getCLBs;
	}

	/**
	 * Added by Anchal for de-notify GPs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String setListforULBRLBDenotify(LBFreezeForm lbfreeze, Long userId, String fileLoc) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Transaction transaction = null;
		String getCLBs = null;
		Date effectiveDate = lbfreeze.getEffectiveDate();
		Date gazPubDate = lbfreeze.getGazPubDate();
		Date orderDate = lbfreeze.getOrderDate();
		java.sql.Timestamp ordertimeStampDateTemp = null;
		if (orderDate != null) {
			ordertimeStampDateTemp = new Timestamp(orderDate.getTime());
		}
		final java.sql.Timestamp ordertimeStampDate = ordertimeStampDateTemp;
		java.sql.Timestamp effectivetimeStampDateTemp = null;
		if (effectiveDate != null) {
			effectivetimeStampDateTemp = new Timestamp(effectiveDate.getTime());
		}
		final java.sql.Timestamp effectivetimeStampDate = effectivetimeStampDateTemp;
		java.sql.Timestamp gazPubtimeStampDateTemp = null;
		if (gazPubDate != null) {
			gazPubtimeStampDateTemp = new Timestamp(gazPubDate.getTime());
		}
		final java.sql.Timestamp gazPubtimeStampDate = gazPubtimeStampDateTemp;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session.createSQLQuery("select denotify_rlb_ulb_fn(:transactionid,:list_of_rlbs,:v_order_no,:v_order_date,:v_effective_date,:v_gaz_pub_date,:v_order_path,:goordercode,:v_uesrid,:v_statecode,:ulb_coverage);");
			query.setParameter("transactionid", lbfreeze.getTransactionid(), Hibernate.INTEGER);
			query.setParameter("list_of_rlbs", lbfreeze.getRlbsListData());
			query.setParameter("v_order_no", lbfreeze.getOrderNo());
			query.setParameter("v_order_date", ordertimeStampDate, Hibernate.TIMESTAMP);
			query.setParameter("v_effective_date", effectivetimeStampDate, Hibernate.TIMESTAMP);
			query.setParameter("v_gaz_pub_date", gazPubtimeStampDate, Hibernate.TIMESTAMP);
			query.setParameter("v_order_path", fileLoc, Hibernate.STRING);
			query.setParameter("goordercode", lbfreeze.getOrderCode(), Hibernate.INTEGER);
			query.setParameter("v_uesrid", userId);
			query.setParameter("v_statecode", Integer.parseInt(lbfreeze.getStateCode()), Hibernate.INTEGER);
			query.setParameter("ulb_coverage", lbfreeze.getUlbsListData());
			getCLBs = query.uniqueResult().toString();
			transaction.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			e.getCause().getMessage();
			e.getCause().getLocalizedMessage();
			try {
				log.error("Exception in StateDistrictFreezeDaoImpl.setListforULBRLBDenotify : ", e);
				transaction.rollback();
			} catch (RuntimeException rbe) {
				log.error("Couldn’t roll back transaction", rbe);
			}
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getCLBs;
	}

	@Override
	public String getTransactionDescription(String tranId) throws Exception {
		Session session = null;
		SQLQuery query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select description from entity_transactions where tid=:tranId");
			query.setParameter("tranId", Integer.parseInt(tranId), Hibernate.INTEGER);
		} catch (HibernateException e) {
			log.error("Exception in StateDistrictFreezeDaoImpl.getTransactionDescription : ", e);
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return query.uniqueResult().toString();
	}

	@Override
	public boolean saveDataInMap(String fetchData, List<AttachedFilesForm> attachedList) throws Exception {

		Transaction tx1 = null;
		Session sessionObj = null;
		Attachment attachment = null;
		GovernmentOrder governmentOrder = new GovernmentOrder();
		governmentOrder.setOrderCode(Integer.parseInt(fetchData));
		try {
			sessionObj = sessionFactory.openSession();
			tx1 = sessionObj.beginTransaction();
			if (attachedList != null && !attachedList.isEmpty()) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					attachment.setGovernmentOrder(governmentOrder);
					sessionObj.save(attachment);
					tx1.commit();
				}
			}
			return true;
		} catch (Exception e) {
			log.error("Exception in StateDistrictFreezeDaoImpl.saveDataInMap : ", e);
			tx1.rollback();
			return false;
		} finally {
			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.close();
			}
		}

	}

	@Override
	public List<String> getRLBS(Integer tranId) throws Exception {
		Session session = null;
		SQLQuery query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select array_to_string(array(select local_body_name_english||' ('||local_body_code||')' from localbody where transaction_id=:tranId and local_body_type_code=3 and isactive),',') as rlbdata;");
			query.setParameter("tranId", tranId, Hibernate.INTEGER);
		} catch (HibernateException e) {
			log.error("Exception in StateDistrictFreezeDaoImpl.getTransactionDescription : ", e);
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return query.list();
	}

	/**
	 * Added by Anchal Todariya on 25-03-2015
	 * 
	 * */
	public List<LocalbodyListbyState> getExistingPanchayatListForDistrict(int lbTypeCode, int districtCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getIntermediatePanchayatListbyDistrict").setParameter("districtCode", districtCode, Hibernate.INTEGER).setParameter("type", lbTypeCode, Hibernate.INTEGER);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	@Override
	public List<LocalbodyListbyState> getExistingPanchayatListoldForDistrict(int lbTypeCode, int districtCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getIntermediatePanchayatListbyDistrict").setParameter("type", lbTypeCode, Hibernate.INTEGER).setParameter("districtCode", districtCode, Hibernate.INTEGER);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	/**
	 * Added by kirandeep for locabody validation
	 * 
	 * @param stateCode
	 * @param lbType
	 * @return
	 * @throws BaseAppException
	 */
	@Override
	public boolean getStatusOfLocabodyTypeFreezeUnfreze(Integer stateCode, String lbType) throws BaseAppException {
		Session session = sessionFactory.openSession();
		Boolean status = false;
		List<StateFreeze> list = new ArrayList<StateFreeze>();
		try {

			Query query = session.createQuery("from StateFreeze where entityCode =:stateCode and entityTtype='S' and lbLrType=:lbType and statusByStateUser = 1 ");
			query.setParameter("stateCode", stateCode);
			query.setParameter("lbType", lbType);
			list = query.list();
			

		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		if (list.size() == 1) {
			status = true;
			return status;
		} else {
			return status;
		}

	}

	/**
	 * Added by kirandeep for localbody freeze validations if parent freeze or
	 * Change by Maneesh Kumar Since 27-July2015
	 * 
	 * @param lblc
	 * @param lbType
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkParentStatus(int lblc, Character lbType) throws Exception {
		Session session = sessionFactory.openSession();
		Boolean status = false;
		try {
			StringBuilder stringBuilder=new StringBuilder("select case when count(1)>0 then true else false end  from state_freeze sf,localbody_districts ld,localbody l where l.local_body_code=:lblc"); 
			stringBuilder.append(" and sf.entity_code=ld.district_code and l.local_body_code=ld.local_body_code and l.local_body_version=ld.local_body_version and l.isactive ");
			stringBuilder.append(" and sf.lb_lr_type=:lbType and sf.entity_type='D' and status_by_state_user=1 ");
			
			SQLQuery query = session.createSQLQuery(stringBuilder.toString());
			query.setParameter("lblc", lblc);
			query.setParameter("lbType", lbType);
			status = (boolean)query.uniqueResult();
					
			
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	/**
	 * @author Maneesh Kumar 01-July-2015
	 * @param localbodyCode
	 * @param lbTypeCode
	 * @param stateCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<GetFreezeDataForLocalbody> getFreezeLocalbodybyState(Integer parentLocalbodyCode, Integer lbTypeCode, Integer stateCode,Integer districtCode) throws Exception {
		Session session = null;
		List<GetFreezeDataForLocalbody> freezeDataForLocalbodyList = null;
		try {
			session = sessionFactory.openSession();
			Query query=null;
			if(districtCode>0){
				query = session.getNamedQuery("GetFreezeDataForLocalbodyFnDistrictUser");
			}else
			{
				query = session.getNamedQuery("GetFreezeDataForLocalbodyFn");	
			}
			query.setParameter("parentLocalbodyCode", parentLocalbodyCode);
			query.setParameter("lbTypeCode", lbTypeCode);
			query.setParameter("stateCode", stateCode);
			if(districtCode>0){
			query.setParameter("districtCode", districtCode);
			}
			freezeDataForLocalbodyList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return freezeDataForLocalbodyList;
	}

	

	@Override
	public boolean saveLBFreezePopulation(List<LBFreezeUnfreeze> freezeUnfreeze) {
		// TODO Auto-generated method stub
		Session session=null;
		boolean flag =true; 
		try{
			session = sessionFactory.openSession();
			Transaction transaction=session.beginTransaction();
			for (LBFreezeUnfreeze lbFreezeUnfreeze : freezeUnfreeze) {
				if(lbFreezeUnfreeze.getLblc() == null)continue;
				session.saveOrUpdate(lbFreezeUnfreeze);
			}
			transaction.commit();
		}
		catch(Exception exception){
			flag =false;
			exception.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return flag;
	}

	@Override
	public List<GetFreezeDataForLocalbody> getFreezeLocalbodybyStatePopulation(Integer parentLocalbodyCode,
			Integer lbTypeCode, Integer stateCode, Integer districtCode) throws Exception {
		Session session = null;
		List<GetFreezeDataForLocalbody> freezeDataForLocalbodyList = null;
		try {
			session = sessionFactory.openSession();
			Query query=null;
			if(districtCode>0){
				query = session.getNamedQuery("GetFreezeDataForLocalbodyFnDistrictUser");
			}else
			{
				query = session.getNamedQuery("GetFreezeDataForLocalbodyFnPopulation");	
			}
			query.setParameter("parentLocalbodyCode", parentLocalbodyCode);
			query.setParameter("lbTypeCode", lbTypeCode);
			query.setParameter("stateCode", stateCode);
			query.setParameter("districtCode", districtCode);
			freezeDataForLocalbodyList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return freezeDataForLocalbodyList;
	}

}
