package in.nic.pes.lgd.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.stereotype.Service;

import in.nic.pes.lgd.bean.DesignationLevelSortorder;
import in.nic.pes.lgd.bean.Designationpojo;
import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.constant.DesignationConstant;
import in.nic.pes.lgd.dao.LgdDesignationDAO;
import in.nic.pes.lgd.dao.OrganizationDAO;

@Service
public class LgdDesignationDAOImpl implements LgdDesignationDAO{
	private static final Logger log = Logger.getLogger(LgdDesignationDAOImpl.class); 
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdDesignation> getExistingDesignations(Integer tierSetupCode, String designationType) {
		// TODO Auto-generated method stub
		Session session = null;
		List<LgdDesignation> listDesignations = new ArrayList<LgdDesignation>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = " from LgdDesignation des where des.tierSetupCode = :tierSetupCode and des.designationType=:designationType and des.isActive is true order by des.sortOrder asc"; 
			Query query = session.createQuery(queryBuilder);
			query.setParameter("tierSetupCode", tierSetupCode);
			query.setParameter("designationType", designationType);
			listDesignations = query.list();
		} 
		catch(Exception e){
			log.debug("Exception" + e);
		}
		finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return listDesignations;
	}
	
	
	/*modified by pooja on 14-05-2015 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdDesignation> getExistingDesignationsForDepartment(Integer olc) {
		// TODO Auto-generated method stub
		Session session = null;
		List<LgdDesignation> listDesignations = new ArrayList<LgdDesignation>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = " from LgdDesignation des where des.olc = :olc  and des.designationType=:designationType and des.isActive is true order by des.lgdDesignationPK.designationCode";
			Query query = session.createQuery(queryBuilder);
			query.setParameter("olc", olc);
			query.setParameter("designationType", "D");
			listDesignations = query.list();	
		} 
		catch(Exception e){
			log.error("Exception-->"+e);
			throw new HibernateException(e);
		}
		finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return listDesignations;
	}

	
	@Override
	public void saveOrUpdate(LgdDesignation designation) {
		// TODO Auto-generated method stub
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			Integer tId=null;
			if(designation.isNextVersion()){
				
				this.deactivePreviousVersion(designation.getLgdDesignationPK().getDesignationCode());
				
			}
			
			if(designation.getOpeationCode()!=null &&  designation.getIsActive() && (designation.isNextVersion()||designation.getLgdDesignationPK().getDesignationVersion()==1))
			{
				tId=this.getNextTransactionsId();
				designation.setTransactionId(tId);
				designation.setDesignationAddedBy("I");
					
			}
			
			session.saveOrUpdate(designation);
			session.flush();
			if(designation.getOpeationCode()!=null &&  designation.getIsActive() && (designation.isNextVersion()||designation.getLgdDesignationPK().getDesignationVersion()==1)){
				
				EntityTransactionsNew entityTransactionsNew=new EntityTransactionsNew();
				String description=designation.getDescription();
				if(designation.getDesignationName()!=null){
					description=description.replace("{DESIGNATION_NAME}", designation.getDesignationName());
				}
				if(designation.getLgdDesignationPK()!=null && designation.getLgdDesignationPK().getDesignationCode()!=null){
					description=description.replace("{DESIGNATION_CODE}", designation.getLgdDesignationPK().getDesignationCode().toString());
				}
				String parentName="";
				if(designation.getOlc()!=null){
					List<Organization> OrganizationList =organizationDAO.getOrganizationDetails(designation.getOlc());
					if(OrganizationList!=null && !OrganizationList.isEmpty()){
						parentName=OrganizationList.get(0).getOrgName();
					}
				}else{
					Query query=session.getNamedQuery("getLocalGovSetupbyTierSetupCode");
					query.setParameter("stateCode", designation.getStateCode());
					query.setParameter("tierSetupCode", designation.getTierSetupCode());
					List<GetLocalGovtSetup> localGovtSetupList=	query.list();
					if(localGovtSetupList!=null && !localGovtSetupList.isEmpty()){
						parentName=localGovtSetupList.get(0).getNomenclatureEnglish();
					}
					
				}
				description=description.replace("{ORGANIZATION_OR_LOCAL_BODY_TYPE}", parentName);
				entityTransactionsNew.setDescription(description);
				entityTransactionsNew.settId(tId);
				entityTransactionsNew.setOperationCode(designation.getOpeationCode());
				entityTransactionsNew.setLevel(DesignationConstant.DESIGNATION_LEVEL.toString());
				entityTransactionsNew.setStateCode(designation.getStateCode());
				java.util.Date date = new Date();
				java.sql.Timestamp lastUpdated = new java.sql.Timestamp(date.getTime());
				entityTransactionsNew.setEffectiveDate(lastUpdated);
				entityTransactionsNew.setHasError(Boolean.FALSE);
				entityTransactionsNew.setIsactive(Boolean.TRUE);
				entityTransactionsNew.setStatusFlg("P");
				entityTransactionsNew.setEntityType(DesignationConstant.DESIGNATION_ENTITY_TYPE.toString());
				entityTransactionsNew.setEntityCode(designation.getLgdDesignationPK()!=null && designation.getLgdDesignationPK().getDesignationCode()!=null?designation.getLgdDesignationPK().getDesignationCode().toString():null);
				session.save(entityTransactionsNew);
				
			}
			
			
			tx.commit();
		} catch (Exception e) {
			log.error("Exception-->"+e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return;
	}
	
	
	
	

	
	@Override
	public void remove(LgdDesignation designation) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LgdDesignation.class);
			criteria.add( Restrictions.eq("isActive", Boolean.TRUE));
			criteria.add( Restrictions.eq("lgdDesignationPK.designationCode", designation.getDesignationCode()));
			
			List<LgdDesignation> lgdDesignationList = criteria.list();
			
			if(lgdDesignationList!=null && !lgdDesignationList.isEmpty()) {
				LgdDesignation delDesig=lgdDesignationList.get(0);
				Transaction tx = session.beginTransaction();
				delDesig.setIsActive(Boolean.FALSE);
				session.saveOrUpdate(delDesig);
				tx.commit();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return;
	}

	@Override
	public LgdDesignation getDesignationById(Integer designationId) {
		// TODO Auto-generated method stub
		Session session = null;
		LgdDesignation designation = null;
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "from LgdDesignation des where des.lgdDesignationPK.designationCode = :designationId and des.isActive is true"; 
			Query query = session.createQuery(queryBuilder);
			query.setParameter("designationId", designationId);
			designation = (LgdDesignation) query.uniqueResult();
		} 
		catch(Exception e){
			log.debug("Exception" + e);
			designation = null;
		}
		finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return designation != null ? designation : new LgdDesignation() ;
	}

	@Override
	@SuppressWarnings("unchecked")
	public LgdDesignation getLBReportingDetail(int tierSetupCode) throws Exception {
		LgdDesignation desig = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			List<Designationpojo> lbReportingList = session.getNamedQuery("GetLBDesignation").setParameter("tierSetupCode", tierSetupCode).list();
			if(!lbReportingList.isEmpty()) {
				String temp = null;
				for (int i = 0; i < lbReportingList.size(); i++) {
					if (i == 0) {
						temp = lbReportingList.get(i).getReportToCode() + "~";
					} else {
						temp += lbReportingList.get(i).getReportToCode() + "~";
					}
				}
				desig = new LgdDesignation();
				desig.setDesignationName(temp);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		
		return desig;
	}
	
	@SuppressWarnings("unchecked")
	public List<LgdDesignation> getOfficialDesignation(int lgTypeCode) throws Exception {
		Session session = null;
		List<LgdDesignation> list = null;
		
		Query query;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from LgdDesignation des where des.tierSetupCode = :tierSetupCode and des.designationType=:designationType and des.isActive is true  order by des.isTopDesignation desc, des.designationCode");
			query.setParameter("tierSetupCode", lgTypeCode);
			query.setParameter("designationType", "O");
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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdDesignation> getLocalBodyTierSetupParentList(int tierSetupCode)	throws Exception {
		List<LgdDesignation> desigList = null;
		Session session = null;
		try  {
			session = sessionFactory.openSession();
			List<LocalBodyTiersSetup> tierSetupList = session.getNamedQuery("getLocalBodyTierSetupParentList") .setParameter("tierSetupCode", tierSetupCode).list();
			
			if (!tierSetupList.isEmpty())  {
				desigList = new ArrayList<LgdDesignation>();
				for (LocalBodyTiersSetup lbTSetup : tierSetupList) {
					/*if(lbTSetup.getParentTierSetupCode() != null) {	
						LgdDesignation desig = new LgdDesignation();
						desig.setDesignationCode(lbTSetup.getTierSetupCode());
						desig.setDesignationName(lbTSetup.getNomenclatureEnglish());
						desigList.add(desig);
							
					} else {
						LgdDesignation desig = new LgdDesignation();
						desig.setDesignationCode(lbTSetup.getTierSetupCode());
						desig.setDesignationName(lbTSetup.getNomenclatureEnglish());
						desigList.add(desig);
					}*/
					
					LgdDesignation desig = new LgdDesignation();
					desig.setDesignationCode(lbTSetup.getTierSetupCode());
					desig.setDesignationName(lbTSetup.getNomenclatureEnglish());
					desigList.add(desig);
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return desigList;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LgdDesignation> getDesignationReporting(int lgTypeCode) throws Exception {
		Session session = null;
		List list = new ArrayList();
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from LgdDesignation des where des.tierSetupCode = :tierSetupCode and des.designationType=:designationType and des.isActive is true");
			query.setParameter("tierSetupCode", lgTypeCode);
			query.setParameter("designationType", "O");
			list = query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Designationpojo> recentAddedDesignation(Integer tierSetupCode) {
		// TODO Auto-generated method stub
		Session session = null;
		List<Designationpojo> list = new ArrayList<Designationpojo>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("GetLBDesignation");
			query.setParameter("tierSetupCode", tierSetupCode);
			list = query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return list;
	}

	@Override
	public boolean isDesignationBeingUsed(Integer designationId) {
		// TODO Auto-generated method stub
		boolean isexist = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "select case when count(1) > 0 then true else false end as is_exist  from reporting_setup where designation_code = :designationId or report_to = :designationId"; 
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("designationId", designationId);
			isexist = (Boolean) query.uniqueResult();
		} catch(Exception e){
			log.debug("Exception" + e);
			isexist = false;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return isexist;
	}

	@Override
	public void saveReOrder(Integer designationCode,Integer order) {
		Session session = null;
		try{
			if(designationCode!=null){
				session = sessionFactory.openSession();
				Query query=session.createQuery("from LgdDesignation where designationCode=:designationCode and isactive=true");
				query.setParameter("designationCode", designationCode);
				List<LgdDesignation> lgdDesignationList=query.list();
				
				if(lgdDesignationList!=null && !lgdDesignationList.isEmpty()){
					LgdDesignation designation=lgdDesignationList.get(0);
					designation.setSortOrder(order);
					Transaction tx = session.beginTransaction();
					session.saveOrUpdate(designation);
					tx.commit();
				}
				
				
				
				
			}
		} catch(Exception e){
			log.debug("Exception" + e);
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
 }
	
	/*Added by Pooja on 14-05-2015*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrgLocatedAtLevels> getOrgLocatedLevelList(Integer olc) throws Exception {
		Session session = null;
		List<OrgLocatedAtLevels> listOrgLevels = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("SELECT  org_located_at_levels.org_located_level_code as orgLocatedLevelCode," + " org_located_at_levels.located_at_level AS locatedAtLevel,"
					+ " (case(length(trim(coalesce(org_level_specific_name,'')))) when 0 then org_name  else org_level_specific_name end) as orgLevelSpecificName" + " FROM org_located_at_levels,organization"
					+ " WHERE organization.olc = org_located_at_levels.olc AND organization.isactive = TRUE AND organization.olc =:olc AND org_located_at_levels.isactive");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("olc", olc);
			query.addScalar("orgLocatedLevelCode");
			query.addScalar("orgLevelSpecificName");
			query.addScalar("locatedAtLevel");
			query.setResultTransformer(Transformers.aliasToBean(OrgLocatedAtLevels.class));
			listOrgLevels = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listOrgLevels;
	}
	
	
	/*Added by Pooja on 19-05-2015*/
	@Override
	public Integer getLocatedAtLevel(Integer orgLocatedLevelCode) {
		Session session = null;
		Integer locatedAtLevel = 0;
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "select located_at_level from org_located_at_levels where org_located_level_code = :orgLocatedLevelCode and isactive";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("orgLocatedLevelCode", orgLocatedLevelCode);
			locatedAtLevel = (Integer) query.uniqueResult();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return locatedAtLevel;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DesignationLevelSortorder> getExistingDesignationLevel(LgdDesignation designation) {
		// TODO Auto-generated method stub
		Session session = null;
		List<DesignationLevelSortorder> listDesignationLevels = new ArrayList<DesignationLevelSortorder>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = " from DesignationLevelSortorder dls where dls.lgdDesignationCode = :designation and dls.isActive is true";
			Query query = session.createQuery(queryBuilder);
			query.setParameter("designation",designation);
			listDesignationLevels = query.list();
		} 
		catch(Exception e){
			log.error("Exception-->"+e);
			throw new HibernateException(e);
		}
		finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return listDesignationLevels;
	}

	
	/*added by pooja on 21-05-2015*/
	@Override
	public void updateIsActive(Integer designationCode)
	{
		Session session = null;
		try{
			if(designationCode!=null){
				session = sessionFactory.openSession();
				LgdDesignation designation=(LgdDesignation)session.load(LgdDesignation.class, designationCode);
				designation.setIsActive(false);
				Transaction tx = session.beginTransaction();
				session.saveOrUpdate(designation);
				tx.commit();
			}
		} catch(Exception e){
			log.debug("Exception" + e);
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}	
	}
	/*added by pooja on 01-06-2015*/
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdDesignation> getDepartmentDesignationsForReorder(Integer olc,Integer orgLocatedLevelCode)
	{
		Session session = null;
		List<LgdDesignation> listDesignations = new ArrayList<LgdDesignation>();
		try {
			session = sessionFactory.openSession();
			
			String queryBuilder = "select lgd_designation.designation_code as designationCode,lgd_designation.designation_name as designationName,lgd_designation.designation_name_local as designationNameLocal" +
					" ,lgd_designation.ismultiple as isMultiple,lgd_designation.iscontractpermanent as isContractPermanent" +
					" from lgd_designation join designation_level_sortorder" +
					" on lgd_designation.designation_code = designation_level_sortorder.designation_code" +
					" where lgd_designation.olc= :olc and designation_level_sortorder.org_located_level_code = :orgLocatedLevelCode" +
					" and lgd_designation.isactive is true and designation_level_sortorder.isactive is true" +
					" order by designation_level_sortorder.sort_order";
			SQLQuery query = session.createSQLQuery(queryBuilder);
			query.setParameter("olc", olc);
			query.setParameter("orgLocatedLevelCode",orgLocatedLevelCode);
			query.addScalar("designationCode");
			query.addScalar("designationName");
			query.addScalar("designationNameLocal");
			query.addScalar("isMultiple");
			query.addScalar("isContractPermanent");
			query.setResultTransformer(Transformers.aliasToBean(LgdDesignation.class));
			listDesignations = query.list();
			
		} 
		catch(Exception e){
			log.error("Exception-->"+e);
			throw new HibernateException(e);
		}
		finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return listDesignations;
	}
	@Override
	public String getOrgSpecificName(Integer olc,Integer orgLocatedLevelCode)
	{
		Session session = null;
		String orgSpecificName = "";
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "select distinct (case(length(trim(coalesce(org_level_specific_name,'')))) when 0 then org_name  else org_level_specific_name end) as orgLevelSpecificName " +
					" FROM org_located_at_levels,organization WHERE organization.olc = org_located_at_levels.olc AND organization.isactive = TRUE " +
					" AND organization.olc = :olc AND org_located_at_levels.org_located_level_code = :orgLocatedLevelCode AND org_located_at_levels.isactive";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("olc",olc);
			query.setParameter("orgLocatedLevelCode", orgLocatedLevelCode);
			orgSpecificName = (String) query.uniqueResult();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return orgSpecificName;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DesignationLevelSortorder> getdesignationLevelsForReorder(Integer orgLocatedLevelCode) {
		// TODO Auto-generated method stub
		Session session = null;
		List<DesignationLevelSortorder> listDesignationLevels = new ArrayList<DesignationLevelSortorder>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = " from DesignationLevelSortorder dls where dls.orgLocatedLevelCode = :orgLocatedLevelCode and dls.isActive is true";
			Query query = session.createQuery(queryBuilder);
			query.setParameter("orgLocatedLevelCode",orgLocatedLevelCode);
			listDesignationLevels = query.list();
		} 
		catch(Exception e){
			log.error("Exception-->"+e);
			throw new HibernateException(e);
		}
		finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return listDesignationLevels;
	}
	@Override
	public void saveOrUpdateReorderLevel(DesignationLevelSortorder desigLevelSortOrder) throws HibernateException
	{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(desigLevelSortOrder);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return;
	}
	
	@Override
	public Integer getNextDesignationCodeFromLgdDesignation() {
		Session session = null;
		Long key=null;
		try {
			session = sessionFactory.openSession();
			//designationCode=(Integer) session.createQuery("SELECT nextval('public.seq_lgd_designation') as num").uniqueResult();
			Query query = session.createSQLQuery( "select nextval('public.seq_lgd_designation')" );
			key = ((BigInteger) query.uniqueResult()).longValue();
			
		} catch (Exception e) {
			log.error("Exception-->"+e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return key.intValue();
	}
	
	public void deactivePreviousVersion(Integer designationCode) {
		Session session1 = null;
		try {
			session1 = sessionFactory.openSession();
			Transaction tx = session1.beginTransaction();
			Query query = session1.createQuery("from LgdDesignation des where des.lgdDesignationPK.designationCode = :designationId and des.isActive is true");
			query.setParameter("designationId", designationCode);
			List<LgdDesignation> lgdDesignationList = query.list();
			if (lgdDesignationList != null && !lgdDesignationList.isEmpty()) {
				LgdDesignation lgdDesignationPre = lgdDesignationList.get(0);
				List<DesignationLevelSortorder> previousList=lgdDesignationPre.getDesignationLevelList();
				for(DesignationLevelSortorder designationLevelSortorder:previousList){
					designationLevelSortorder.setIsActive(Boolean.FALSE);
					session1.update(designationLevelSortorder);
					session1.flush();
				}
				lgdDesignationPre.setIsActive(Boolean.FALSE);
				session1.update(lgdDesignationPre);
				session1.flush();

			}

			tx.commit();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
		}
		return;

	}
	
	
	@Override
	public Integer getNextTransactionsId() {
		Session session = null;
		Long key=null;
		try {
			session = sessionFactory.openSession();
			//designationCode=(Integer) session.createQuery("SELECT nextval('public.seq_lgd_designation') as num").uniqueResult();
			Query query = session.createSQLQuery( "select nextval('entitytransactions_seq')" );
			key = ((BigInteger) query.uniqueResult()).longValue();
			
		} catch (Exception e) {
			log.error("Exception-->"+e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return key.intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DesignationLevelSortorder> getdesignationLevelsbyDesigCode(Integer desigCode) {
		// TODO Auto-generated method stub
		Session session = null;
		List<DesignationLevelSortorder> listDesignationLevels = new ArrayList<DesignationLevelSortorder>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = " from DesignationLevelSortorder dls where dls.lgdDesignationCode.lgdDesignationPK.designationCode = :desigCode and dls.isActive is true";
			Query query = session.createQuery(queryBuilder);
			query.setParameter("desigCode",desigCode);
			listDesignationLevels = query.list();
		} 
		catch(Exception e){
			log.error("Exception-->"+e);
			throw new HibernateException(e);
		}
		finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return listDesignationLevels;
	}
	
	@Override
	public Integer saveOrUpdateWebserviceDesignation(LgdDesignation designation) {
		// TODO Auto-generated method stub
		Session session = null;
		Integer desigCode=null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			Integer tId=null;
			if(designation.isNextVersion()){
				
				this.deactivePreviousVersion(designation.getLgdDesignationPK().getDesignationCode());
				
			}
			
			if(designation.getOpeationCode()!=null &&  designation.getIsActive() && (designation.isNextVersion()||designation.getLgdDesignationPK().getDesignationVersion()==1))
			{
				tId=this.getNextTransactionsId();
				designation.setTransactionId(tId);
					
			}
			
			session.saveOrUpdate(designation);
			session.flush();
			session.clear();
			
			desigCode=designation.getLgdDesignationPK().getDesignationCode();
			if(designation.getOpeationCode()!=null &&  designation.getIsActive() && (designation.isNextVersion()||designation.getLgdDesignationPK().getDesignationVersion()==1)){
				
				EntityTransactionsNew entityTransactionsNew=new EntityTransactionsNew();
				String description=designation.getDescription();
				if(designation.getDesignationName()!=null){
					description=description.replace("{DESIGNATION_NAME}", designation.getDesignationName());
				}
				if(designation.getLgdDesignationPK()!=null && designation.getLgdDesignationPK().getDesignationCode()!=null){
					description=description.replace("{DESIGNATION_CODE}", designation.getLgdDesignationPK().getDesignationCode().toString());
				}
				String parentName="";
				if(designation.getOlc()!=null){
					List<Organization> OrganizationList =organizationDAO.getOrganizationDetails(designation.getOlc());
					if(OrganizationList!=null && !OrganizationList.isEmpty()){
						parentName=OrganizationList.get(0).getOrgName();
					}
				}else{
					Query query=session.getNamedQuery("getLocalGovSetupbyTierSetupCode");
					query.setParameter("stateCode", designation.getStateCode());
					query.setParameter("tierSetupCode", designation.getTierSetupCode());
					List<GetLocalGovtSetup> localGovtSetupList=	query.list();
					if(localGovtSetupList!=null && !localGovtSetupList.isEmpty()){
						parentName=localGovtSetupList.get(0).getNomenclatureEnglish();
					}
					
				}
				description=description.replace("{ORGANIZATION_OR_LOCAL_BODY_TYPE}", parentName);
				entityTransactionsNew.setDescription(description);
				entityTransactionsNew.settId(tId);
				entityTransactionsNew.setOperationCode(designation.getOpeationCode());
				entityTransactionsNew.setLevel(DesignationConstant.DESIGNATION_LEVEL.toString());
				entityTransactionsNew.setStateCode(designation.getStateCode());
				java.util.Date date = new Date();
				java.sql.Timestamp lastUpdated = new java.sql.Timestamp(date.getTime());
				entityTransactionsNew.setEffectiveDate(lastUpdated);
				entityTransactionsNew.setHasError(Boolean.FALSE);
				entityTransactionsNew.setIsactive(Boolean.TRUE);
				entityTransactionsNew.setStatusFlg("P");
				entityTransactionsNew.setEntityType(DesignationConstant.DESIGNATION_ENTITY_TYPE.toString());
				entityTransactionsNew.setEntityCode(designation.getLgdDesignationPK()!=null && designation.getLgdDesignationPK().getDesignationCode()!=null?designation.getLgdDesignationPK().getDesignationCode().toString():null);
				session.save(entityTransactionsNew);
				
			}
			
			
			tx.commit();
		} catch (Exception e) {
			log.error("Exception-->"+e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return desigCode;
	}
	
	@Override
	public Integer isDesinationListExist(List<Integer> desiginationCodeList,Integer olc) throws Exception {
		Session session = null;
		Integer existDesigCount=0;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select cast(count(1) as integer)  from lgd_designation where designation_code  in(:desiginationCodeList) and  olc=:olc and isactive");
			query.setParameter("olc", olc, Hibernate.INTEGER);
			query.setParameterList("desiginationCodeList", desiginationCodeList);
			existDesigCount = (Integer) query.uniqueResult();
		} catch (Exception e) {
			log.debug("OrganizationDAOImpl-->organizationExist" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return existDesigCount;
	}
}
