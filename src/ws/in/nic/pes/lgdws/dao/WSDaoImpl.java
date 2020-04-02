package ws.in.nic.pes.lgdws.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.LgdOrganisationListByLocation;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.bean.ReportingIssue;
import in.nic.pes.lgd.bean.UserRegistration;
import in.nic.pes.lgd.bean.WsAllowedIpEntity;
import in.nic.pes.lgd.bean.WsRegisteredIpEntity;
import in.nic.pes.lgd.bean.WsSubscribedApplicationEntity;
import in.nic.pes.lgd.bean.WsUserRegistrationForm;
import ws.in.nic.pes.lgdws.entity.CoveredVillagesOfBlock;
import ws.in.nic.pes.lgdws.entity.GetHierarchyOfEntity;
import ws.in.nic.pes.lgdws.entity.LevelwiseofRuralLbCount;
import ws.in.nic.pes.lgdws.entity.TotalNoofPriLbnVillageCount;
import ws.in.nic.pes.lgdws.entity.VillageListWithHierarchy;
import ws.in.nic.pes.lgdws.entity.WSDistrict;
import ws.in.nic.pes.lgdws.entity.WSLocalbody;
import ws.in.nic.pes.lgdws.entity.WSOrganization;
import ws.in.nic.pes.lgdws.entity.WSState;
import ws.in.nic.pes.lgdws.entity.WSSubdistrict;
import ws.in.nic.pes.lgdws.entity.WSVillage;
import ws.in.nic.pes.lgdws.entity.WardDetailList;

import ws.in.nic.pes.lgdws.webservice.StateWiseGPVillageMappedEntity;

public class WSDaoImpl implements WSDao {

	//private static final Logger log = Logger.getLogger(WSDaoImpl.class);
	private static final Logger log = Logger.getLogger(WSDaoImpl.class); 
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<WSState> getStateList() throws Exception {
		Session session = null;
		Query query = null;
		List<WSState> wsStateList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getStateList");
			wsStateList = query.list();
		} finally {
			session.close();
		}
		return wsStateList;
	}
	

	public static Integer convertValidateData(String strObj) {
		Integer intObj = null;
		try {
			intObj = Integer.parseInt(strObj.toString());
		} catch (Exception e) {
			intObj = 0;
		}
		return intObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WSDistrict> getDistrictList(String stateCode)throws Exception {
		Session session = null;
		Query query = null;
		List<WSDistrict> wsDistrictList = null;
		try {
			Integer slc = convertValidateData(stateCode);
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getDistrictList");
			query.setParameter("slc", slc);
			wsDistrictList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return wsDistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WSDistrict> getDistrict(String districtCode)throws Exception {
		Session session = null;
		Query query = null;
		List<WSDistrict> wsDistrictList = null;
		try {
			Integer dlc = convertValidateData(districtCode);
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getDistrict");
			query.setParameter("dlc", dlc);
			wsDistrictList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return wsDistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WSSubdistrict> getSubdistrictList(String districtCode)throws Exception {
		Session session = null;
		Query query = null;
		List<WSSubdistrict> wsSubdistrictList = null;
		try {
			Integer dlc = convertValidateData(districtCode);
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getSubdistrictList");
			query.setParameter("dlc", dlc);
			wsSubdistrictList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return wsSubdistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WSSubdistrict> getSubdistrict(String subDistrictCode)throws Exception {
		Session session = null;
		Query query = null;
		List<WSSubdistrict> wsSubdistrictList = null;
		try {
			Integer tlc = convertValidateData(subDistrictCode);
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getSubDistrict");
			query.setParameter("tlc", tlc);
			wsSubdistrictList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return wsSubdistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WSVillage> getVillageList(String subdistrictCode)throws Exception {
		Session session = null;
		Query query = null;
		List<WSVillage> wsVillageList = null;
		try {
			Integer tlc = convertValidateData(subdistrictCode);
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getVillageList");
			query.setParameter("tlc", tlc);
			wsVillageList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return wsVillageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WSVillage> getVillage(String villageCode)throws Exception {
		Session session = null;
		Query query = null;
		List<WSVillage> wsVillageList = null;
		try {
			Integer vlc = convertValidateData(villageCode);
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getVillage");
			query.setParameter("vlc", vlc);
			wsVillageList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return wsVillageList;
	}


	@Override
	public boolean checkAuthorizedKey(String authKey) throws Exception {
		boolean keyFlag=false;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createSQLQuery("select case when count(1)>0 then true else false end from subscribing_application where passkey=:authKey");
			query.setParameter("authKey", authKey);
			keyFlag=(boolean)query.uniqueResult();
			
		} finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		return keyFlag;
	}


	@Override
	public boolean checkValidEntityCode(Integer entityCode,String entity) throws Exception {
		boolean existStateFlag=false;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select case when count(1)>0 then true else false end from ");
			sb.append(entity);
			sb.append(" where isactive and ");
			
			if(("LOCALBODY").equals(entity)){
				sb.append("local_body");
			}else{
				sb.append(entity.toLowerCase());
			}
			sb.append("_code=:entityCode");
			Query query=session.createSQLQuery(sb.toString());
			query.setParameter("entityCode", entityCode);
			existStateFlag=(boolean)query.uniqueResult();
			
		} finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		return existStateFlag;
	}


	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WSLocalbody> getLBListbySubdistrict(String landRegionType, Integer entityCode, Integer lbTypeCode) throws Exception {
		List<WSLocalbody> wsLocalbodyList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("getLbListbuSubdistrict");
			query.setParameter("landRegionType",landRegionType);
			query.setParameter("entityCode", entityCode);
			query.setParameter("lbTypeCode", lbTypeCode);
			wsLocalbodyList=query.list();
		} finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		return wsLocalbodyList;
	}
	
	@Override
	public boolean validateLbTypeCode(Integer lbTypeCode) throws Exception{
		boolean existStateFlag=false;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select case when count(1)>0 then true else false end from local_body_type ");
			sb.append("  where local_body_type_code=:lbTypeCode  ");
			Query query=session.createSQLQuery(sb.toString());
			query.setParameter("lbTypeCode", lbTypeCode);
			existStateFlag=(boolean)query.uniqueResult();
			
		} finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		return existStateFlag;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WSLocalbody> getLocalbodyListbyDistrictnLbTypeCode(Integer districtCode,Integer lbTypeCode) throws Exception {
		List<WSLocalbody> wsLocalbodyList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("getLocalbodyListbyDistrictnLbTypeCode");
			query.setParameter("districtCode",districtCode);
			query.setParameter("lbTypeCode", lbTypeCode);
			wsLocalbodyList=query.list();
		} finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		return wsLocalbodyList;
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public Map<String, Collection<?>> getImpactDetails(Integer stateCode) throws Exception {
		// TODO Auto-generated method stub
		log.info("LocalBodyDaoImpl.fetchPreviousNames execution started.");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String queryBuilder = " select trans from EntityTransactionsNew trans where trans.stateCode = :statecode and trans.statusFlg in ('P','S')" +
								  " and trans.description is not null order by trans.statusFlg desc"; 
			Query query = session.createQuery(queryBuilder).setParameter("statecode", stateCode);
			//query.setMaxResults(10);
			List<EntityTransactionsNew> entityTransactions = query.list();
			//session.flush();
			
			
			Collection<Integer> transIDs = CollectionUtils.collect( entityTransactions, new Transformer() {
				@Override
				public Integer transform(Object input) {
					return ((EntityTransactionsNew) input).gettId();
				}
			});
			
			query = session.createQuery("select metadata from EntityTransactionsMetadata metadata where metadata.tid in (:tranIds) ");
			query.setParameterList("tranIds", transIDs);
			List<EntityTransactionsMetadata> metadataDetails = query.list();
			
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("fetch_entity_transactions");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			List<EntityTransactionsNew> entityTransactions  = query.list();
			
			query = session.getNamedQuery("fetch_entity_transaction_metadata");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			List<EntityTransactionsMetadata> metadataDetails  = query.list();
			
			
			Map<String, Collection<?>> mapImpactDetails = new ConcurrentHashMap<>();
			mapImpactDetails.put("entity_transactions", entityTransactions);
			mapImpactDetails.put("entity_transactions_metadata", metadataDetails);
			
			return mapImpactDetails;
		} catch(Exception e){
			throw e;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}*/
	@Override
	public boolean validateOrgTypeCode(Integer orgTypeCode) throws Exception{
		boolean existStateFlag=false;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			StringBuilder sb=new StringBuilder("select case when count(1)>0 then true else false end from organization_type ");
			sb.append("  where org_type_code=:orgTypeCode  ");
			Query query=session.createSQLQuery(sb.toString());
			query.setParameter("orgTypeCode", orgTypeCode);
			existStateFlag=(boolean)query.uniqueResult();
			
		} finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		return existStateFlag;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WSOrganization> getOrganizationList(Integer lbTypeCode, boolean isCenter, Integer stateCode) throws Exception {
		List<WSOrganization> wsOrganization=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("getOrganizationList");
			query.setParameter("orgTypeCode",lbTypeCode,Hibernate.INTEGER);
			query.setParameter("isCenter", (isCenter==true?0:1),Hibernate.INTEGER);
			query.setParameter("stateCode", stateCode,Hibernate.INTEGER);
			wsOrganization=query.list();
		} finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		return wsOrganization;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LevelwiseofRuralLbCount> getLevelwiseRuralLBCount(Integer stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LevelwiseofRuralLbCount> levelwiseofRuralLbCountList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("GET_LEVEL_WISE_RURAL_LB_COUNT");
			query.setParameter("stateCode", stateCode);
			levelwiseofRuralLbCountList = query.list();
		} finally {
			session.close();
		}
		 return levelwiseofRuralLbCountList;
	}


	@Override
	public TotalNoofPriLbnVillageCount getTotalNoofPriLbnVillageCount(String QueryName) throws Exception {
		Session session = null;
		Query query = null;
		TotalNoofPriLbnVillageCount totalNoofPriLbnVillageCount= null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery(QueryName);
			List<TotalNoofPriLbnVillageCount> totalNoofPriLbnVillageCountList  = query.list();
			if(totalNoofPriLbnVillageCountList!=null && !totalNoofPriLbnVillageCountList.isEmpty()){
				totalNoofPriLbnVillageCount= totalNoofPriLbnVillageCountList.get(0);
			}
		} finally {
			session.close();
		}
		 return totalNoofPriLbnVillageCount;
	}
	
	@Override
	public Object[] villageWiseLGDDetails(Integer villageCode)throws Exception{
		Session session = null;
		Query query = null;
		Object[] ObjectArr=new Object[5];
		try {
			session = sessionFactory.openSession();
			
			query = session.getNamedQuery("State_LGD_Detail_RD_DEPT");
			query.setParameter("villageCode", villageCode);
			ObjectArr[0]=query.list();
			
			query = session.getNamedQuery("District_LGD_Detail_RD_DEPT");
			query.setParameter("villageCode", villageCode);
			ObjectArr[1]=query.list();
			
			query = session.getNamedQuery("Sub-District_LGD_Detail_RD_DEPT");
			query.setParameter("villageCode", villageCode);
			ObjectArr[2]=query.list();
			
			query = session.getNamedQuery("Block_LGD_Detail_RD_DEPT");
			query.setParameter("villageCode", villageCode);
			ObjectArr[3]=query.list();
			
			query = session.getNamedQuery("GP_LGD_Detail_RD_DEPT");
			query.setParameter("villageCode", villageCode);
			ObjectArr[4]=query.list();
			
			
		} finally {
			session.close();
		}
		 return ObjectArr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WardDetailList> getWardDetailsbyLocalbodyCode(Integer localbodyCode) throws Exception {
		Session session = null;
		Query query = null;
		List<WardDetailList> wardDetailList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("WARD_DETAIL_LIST_BY_LOCALBODY");
			query.setParameter("localbodyCode", localbodyCode);
			wardDetailList = query.list();
		} finally {
			session.close();
		}
		 return wardDetailList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getBlockListbyDistrictCode(Integer districtCode,boolean isactive) throws Exception {
		Session session = null;
		Query query = null;
		List<Block> blockList = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Block where dlc=:districtCode and isactive=:isactive ");
			query.setParameter("districtCode", districtCode);
			query.setParameter("isactive", isactive);
			blockList = query.list();
		} finally {
			session.close();
		}
		 return blockList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WSLocalbody> getDistrictwiseBlockandGramPanchyat(Integer blockCode) throws Exception {
		Session session = null;
		Query query = null;
		List<WSLocalbody> list = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getBlockwiswGP");
			query.setParameter("blockCode", blockCode);
			list = query.list();
		} finally {
			session.close();
		}
		 return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdOrganisationListByLocation> getOrganizationListByLocation(Integer entity_lc, Integer entity_type) {
	
		Session session = null;
		List<LgdOrganisationListByLocation> data = new ArrayList<LgdOrganisationListByLocation>();
		try {
			session = sessionFactory.openSession();

			StringBuilder strQuery = new StringBuilder("SELECT org_unit_code as orgUnitCode ,org_code as orgCode,org_unit_name as orgUnitName, org_unit_name_local as orgUnitNameLocal FROM get_organization_list_by_location(:entity_lc,:entity_type)");
			SQLQuery query = (SQLQuery) session.createSQLQuery(strQuery.toString()).setParameter("entity_lc", entity_lc).setParameter("entity_type", entity_type);
			query.addScalar("orgUnitCode").addScalar("orgCode").addScalar("orgUnitName").addScalar("orgUnitNameLocal");
			
			data = query.setResultTransformer(Transformers.aliasToBean(LgdOrganisationListByLocation.class)).list();
			// data=(List<LgdOrganisationListByOrgType>)query.list();
			return data;
		} catch (Exception e) {
			System.out.println("Exception in getOrganizationListByLocation method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		
	}
	
	@Override
	@Transactional
	public WsUserRegistrationForm insertWsUserRegistrationDetails(WsUserRegistrationForm wsUser) {
		Session session = null; 
		Timestamp timestamp= new Timestamp(System.currentTimeMillis());
		try {
			session = sessionFactory.openSession();
			wsUser.setRegisteredOn(timestamp);
			session.save(wsUser);
			
			List<WsSubscribedApplicationEntity> applicationEntities = wsUser.getWsSubscribedApplicationEntity();
			
			for (WsSubscribedApplicationEntity wsSubscribedApplicationEntity : applicationEntities) {
				//wsSubscribedApplicationEntity.setWsUserRegistrationForm(wsUser);
				session.save(wsSubscribedApplicationEntity);
			}
			
			List<WsRegisteredIpEntity> entities = wsUser.getWsRegisteredIpEntity();
			
			for (WsRegisteredIpEntity ipEntity : entities) {
				//ipEntity.setWsUserRegistrationForm(wsUser);
				session.save(ipEntity);
			}
			
			/*appEntity.setApplicationName(wsUser.getApplicationName());
			appEntity.setWsUserRegistrationForm(wsUser);*/
			//session.save(regIpEntity);
			
			session.flush();
		} catch (Exception e) {
			return wsUser;
		}finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
	
	}
		return wsUser;
		}


	/* (non-Javadoc)
	 * @see ws.in.nic.pes.lgdws.dao.WSDao#getWsUserRegistrations()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRegistration> getWsUserRegistrations() {
		Session session = sessionFactory.openSession();
		List<UserRegistration> registrationForms=null;
		try{
			List<Character> statusType=new ArrayList<Character>();
			statusType.add('N');
			statusType.add('A');
			statusType.add('R');
			
			Query query = session.createQuery("From UserRegistration where registrationStatus in(:registrationStatus) order by registeredOn DESC");
			query.setParameterList("registrationStatus", statusType);
			int rowCount=query.list().size();
			/*query.setFirstResult(pageStart);
			query.setMaxResults(10);
			*/registrationForms = query.list();
			/*if(registrationForms!=null && registrationForms.size()>0){
				
				if(rowCount%10!=0){
					rowCount=(rowCount/10)+1;
				}else{
					rowCount=rowCount/10;
				}
				registrationForms.get(0).setRowCount(rowCount);	
				registrationForms.get(0).setPageStart(pageStart);	
			}*/
			
		}
		catch(Exception exception){
			
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
	}
		return registrationForms;

	}


	/* (non-Javadoc)
	 * @see ws.in.nic.pes.lgdws.dao.WSDao#approveUserRegistration(int)
	 */
	@Override
	@Transactional
	public UserRegistration approveUserRegistration(int userId,String type) {
		Session session = sessionFactory.openSession();
		UserRegistration registrationForm= (UserRegistration) session.get(UserRegistration.class, userId);
		try{
			if("A".equalsIgnoreCase(type)){
				List<WsRegisteredIpEntity> allowedIpEntity= registrationForm.getIpAddressList();
				for (WsRegisteredIpEntity wsRegisteredIpEntity : allowedIpEntity) {
					WsAllowedIpEntity ipEntity=new WsAllowedIpEntity();
					ipEntity.setIpAddress(wsRegisteredIpEntity.getIpAddress());
					ipEntity.setUserRegistration(registrationForm);
					session.save(ipEntity);
				}
			}
			registrationForm.setRegistrationStatus(type.charAt(0));
			session.saveOrUpdate(registrationForm);
			session.flush();
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return registrationForm;
	}


	@Override
	public void saveUserRegistrationDetails(UserRegistration registration) {
		// TODO Auto-generated method stub
		Session session = null;
		try{
			session = sessionFactory.openSession();
			session.saveOrUpdate(registration);
			session.flush();
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}


	@Override
	public UserRegistration getRegistrationDetails(String emailOrnumber) {
		Session session = null;
		UserRegistration registration=null;
		try{
			session = sessionFactory.openSession();
			Criteria criteria=session.createCriteria(UserRegistration.class);
			Criterion email = Restrictions.eq("email",emailOrnumber);
			Criterion mobile = Restrictions.eq("mobile",emailOrnumber);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(email);
			disjunction.add(mobile);
			criteria.add(disjunction);
			List results = criteria.list();
			if(results!=null && results.size()>0){
				registration=(UserRegistration) results.get(0);
			}
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return registration;
	}


	@Override
	public UserRegistration getRegistration(int userId) {
		Session session = null;
		UserRegistration registration=null;
		try{
			session = sessionFactory.openSession();
			
			registration=(UserRegistration) session.get(UserRegistration.class, new Integer(userId));
			session.flush();
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return registration;
	}
	
	
	@Override
	@Transactional
	public void saveRequestWebService(UserRegistration wsUser) {
		Session session = null; 
		Timestamp timestamp= new Timestamp(System.currentTimeMillis());
		try {
			session = sessionFactory.openSession();
			wsUser.setRegistrationStatus('N');
			wsUser.setRegisteredOn(timestamp);
			session.saveOrUpdate(wsUser);
			List<WsSubscribedApplicationEntity> applicationEntities = wsUser.getWsSubscribedApplicationEntity();
			
			for (WsSubscribedApplicationEntity wsSubscribedApplicationEntity : applicationEntities) {
				wsSubscribedApplicationEntity.setUserRegistration(wsUser);
				session.save(wsSubscribedApplicationEntity);
			}
			List<WsRegisteredIpEntity> entities = wsUser.getWsRegisteredIpEntity();
			for (WsRegisteredIpEntity ipEntity : entities) {
				ipEntity.setUserRegistration(wsUser);
				session.save(ipEntity);
			}
			session.flush();
		} 
		catch (Exception e) {
			
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}


	@Override
	public void saveReportingIssue(ReportingIssue reportingIssue) {
		Session session = null; 
		Timestamp timestamp= new Timestamp(System.currentTimeMillis());
		try {
			session = sessionFactory.openSession();
			reportingIssue.setSubmittedOn(timestamp);
			UserRegistration registration=(UserRegistration) session.get(UserRegistration.class, reportingIssue.getUserId());
			reportingIssue.setRegistration(registration);
			reportingIssue.setSuubmittedToState(reportingIssue.getStateCode());
			
			if(reportingIssue.getStateType()==0)
				reportingIssue.setSubmittedToType('R');
			if(reportingIssue.getStateType()==1)
				reportingIssue.setSubmittedToType('U');
			if(reportingIssue.getStateType()==2)
				reportingIssue.setSubmittedToType('B');
			if(reportingIssue.getStateType()==3)
				reportingIssue.setSubmittedToType('P');
			NodalOfficerState nodalOfficerState=getNodalOfficerState(reportingIssue.getStateCode(), reportingIssue.getStateType());
			reportingIssue.setNodalOfficer(nodalOfficerState);
			session.saveOrUpdate(reportingIssue);
			session.flush();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		
	}

	
	public NodalOfficerState getNodalOfficerState(int stateCode,int type){
		NodalOfficerState nodalOfficerState=null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query=session.createQuery("from NodalOfficerState where stateCode=:stateCode and userType=:userType");
			query.setParameter("stateCode",stateCode);
			
			
			if(type==0)
			query.setParameter("userType",'R');
			if(type==1)
			query.setParameter("userType",'U');
			if(type==2)
			query.setParameter("userType",'B');
			if(type==3)
			query.setParameter("userType",'P');
			
			if(!query.list().isEmpty()){
				nodalOfficerState=(NodalOfficerState) query.list().get(0);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return nodalOfficerState;
		
	}
	
	

	@Override
	public List<ReportingIssue> listOfRptIssues(int userId) {
		Session session = null;
		List<ReportingIssue> list=null;
		try {
			session = sessionFactory.openSession();
			Query query=session.createQuery("from ReportingIssue rp left join fetch rp.nodalOfficer n left join fetch rp.registration r where r.userRegistrationId=:userRegistrationId");
			query.setParameter("userRegistrationId", userId);
			//int rowCount=query.list().size();
			/*query.setFirstResult(0);
			query.setMaxResults(10);*/
			list = query.list();
			/*if(list!=null && list.size()>0){
				
				if(rowCount%10!=0){
					rowCount=(rowCount/10)+1;
				}else{
					rowCount=rowCount/10;
				}
				list.get(0).setRowCount(rowCount);	
				list.get(0).setPageStart(0);	
			}*/
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}


	@Override
	public List<ReportingIssue> stateReportedIssue(int nodelId) {
		Session session = null;
		List<ReportingIssue> list=null;
		Query query=null;
		try {
			session = sessionFactory.openSession();
			if(nodelId!=0){
				query=session.createQuery("from ReportingIssue where nodalOfficer.nodalOfficerId=:nodalOfficerId");
				query.setParameter("nodalOfficerId", nodelId);
			}else{
				 query=session.createQuery("from ReportingIssue where SuubmittedToState=:SuubmittedToState");
				query.setParameter("SuubmittedToState", nodelId);
			}
			
			/*int rowCount=query.list().size();
			query.setFirstResult(0);
			query.setMaxResults(10);*/
			list = query.list();
			/*if(list!=null && list.size()>0){
				
				if(rowCount%10!=0){
					rowCount=(rowCount/10)+1;
				}else{
					rowCount=rowCount/10;
				}
				list.get(0).setRowCount(rowCount);	
				list.get(0).setPageStart(0);	
			}*/
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}


	@Override
	public void replyReportedIssue(ReportingIssue reportingIssue) {
		Session session = null; 
		Timestamp timestamp= new Timestamp(System.currentTimeMillis());
		try {
			session = sessionFactory.openSession();
			ReportingIssue issue=(ReportingIssue) session.get(ReportingIssue.class, reportingIssue.getSerialNumber());
			issue.setReplyText(reportingIssue.getReplyText());
			issue.setRepliedOn(timestamp);
			session.flush();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		
	}


	@Override
	public List<ReportingIssue> stateReportedIssue(int stateCode, Character userType) {
		Session session = null;
		List<ReportingIssue> list=null;
		try {
			session = sessionFactory.openSession();
			Query query=session.createQuery("from ReportingIssue rp left join fetch rp.nodalOfficer n left join fetch rp.registration r where rp.SuubmittedToState=:stateCode and rp.submittedToType=:submittedToType");
			query.setParameter("stateCode", stateCode);
			query.setParameter("submittedToType", userType);
			/*int rowCount=query.list().size();
			query.setFirstResult(0);
			query.setMaxResults(10);*/
			list = query.list();
			/*if(list!=null && list.size()>0){
				
				if(rowCount%10!=0){
					rowCount=(rowCount/10)+1;
				}else{
					rowCount=rowCount/10;
				}
				list.get(0).setRowCount(rowCount);	
				list.get(0).setPageStart(0);	
			}*/
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}


	@Override
	public void saveEmailSmsNotification(EmailNotification emailNotification) {
		Session session = null; 
		EmailNotification emailNotification2=null;
		try {
			session = sessionFactory.openSession();
			
			emailNotification2=getSubscriberUserDetails(emailNotification.getUserid());
			if(emailNotification2!=null){
				emailNotification.setId(emailNotification2.getId());
			}
			
			session.saveOrUpdate(emailNotification);
			session.flush();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		
	}


	@Override
	public EmailNotification getSubscriberUserDetails(int userId) {
			Session session = null;
			Query query=null;
			List<EmailNotification> emailNotification=null;
			try {
				session = sessionFactory.openSession();
				query=session.createQuery("from EmailNotification where userid=:userid");
				query.setParameter("userid", userId);
				emailNotification= query.list();
				if(emailNotification!=null && emailNotification.size()>0){
					return emailNotification.get(0);
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (session != null && session.isOpen()) {
					session.flush();
					session.close();
				}
			}
			return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WSLocalbody> getUrbanLocalbodyBasedOnLandRegiontypeAndCode(String landRegionType,Integer landRegionTypeCode) throws Exception {
		List<WSLocalbody> wsLocalbodyList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("getUrbanLocalbodyBasedOnLandRegiontypeAndCode");
			query.setParameter("landRegionType",landRegionType);
			query.setParameter("landRegionCode", landRegionTypeCode);
			wsLocalbodyList=query.list();
		} finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		return wsLocalbodyList;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageListWithHierarchy> fetchVillageListWithHierarchy(Integer subDistrictCode) throws Exception {
		List<VillageListWithHierarchy> villageListWithHierarchy=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("FETCH_VillageListWithHierarchy");
			query.setParameter("subDistrictCode",subDistrictCode);
			
			villageListWithHierarchy=query.list();
		}
		
		finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		
		return villageListWithHierarchy;
	}

	@Override
	public StateWiseGPVillageMappedEntity getStateBlocWiseMapped(Integer stateCode, String finYear,Character queryFlag) {
	  
		List<StateWiseGPVillageMappedEntity> stateBlocWiseMappedList =new ArrayList<StateWiseGPVillageMappedEntity> ();
		StateWiseGPVillageMappedEntity  stateBlocWiseMapped =new StateWiseGPVillageMappedEntity();
		
		Query query =null;
		Session session = null;
		
		try
		{
			
			/**
			 * Flag 
			 *  G-GIS 
			 *  C-Census Village
			 *  M-Mapped Village
			 */
			
			session =sessionFactory.openSession();
			
			if(stateCode!= null && stateCode>0){
				String nameQuery="getstateBlocWiseMapped";
				if(queryFlag=='C') {
					nameQuery="percentCensusVillage";
				}else if(queryFlag=='G') {
					nameQuery="percentGPIntegratedwithGIS";
				}
				query =session.getNamedQuery(nameQuery);
				query.setParameter("stateCode", stateCode);
				if(queryFlag!='G') {
					query.setParameter("finYear", finYear);
				}
				stateBlocWiseMappedList =query.list();
				if(stateBlocWiseMappedList != null && stateBlocWiseMappedList.size()>0)
					stateBlocWiseMapped = stateBlocWiseMappedList.get(0);
			}
			
		}
		catch(Exception e)
		{
			log.error("Exception" + e);
			
		} 
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return stateBlocWiseMapped;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<CoveredVillagesOfBlock> fetchCoveredVillagesOfBlock(Integer blockCode) throws Exception {
		List<CoveredVillagesOfBlock> coveredvillagesofblock=null;
		Session session=null;
		Query query =null;
		try{
			session=sessionFactory.openSession();
			
			 query = session.getNamedQuery("List_CoveredVillagesOfBlock");
			query.setParameter("blockCode",blockCode);
			
			coveredvillagesofblock=query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		
		return coveredvillagesofblock;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GetHierarchyOfEntity> getHierarchyOfEntity(String entityType,Integer entityCode) throws Exception {
		List<GetHierarchyOfEntity> getHierarchyOfEntityList=null;
		Session session=null;
		
		try{
	
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("FETCH_HierarchyOfEntity");
			query.setParameter("entityType",entityType);
			query.setParameter("entityCode",entityCode);
			getHierarchyOfEntityList=query.list();
		}
		
		finally {
			if (session!=null && session.isOpen()) {
				session.close();
			}
		}
		
		return getHierarchyOfEntityList;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<WSVillage> getVillageListALL(Integer page)throws Exception {
		Session session = null;
		Query query = null;
		List<WSVillage> wsVillageList = null;
		try {
			Integer poffset=(page-1)*100000;
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getVillageALl");
			query.setParameter("poffset", poffset);
			wsVillageList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return wsVillageList;
	}
	
}
