package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.LocalBodySetup;
import in.nic.pes.lgd.bean.LocalBodySetupPK;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.dao.LocalBodySetupDAO;
import in.nic.pes.lgd.dao.LocalGovTypeDAO;
import in.nic.pes.lgd.utils.CurrentDateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LocalGovTypeDAOImpl implements LocalGovTypeDAO{
	private static final Logger log = Logger.getLogger(LocalGovTypeDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private LocalBodySetupDAO localBodySetupDAO;
	
			
	@SuppressWarnings("unchecked")
	public List<LocalBodyType> getLocalGoveTypeDetails(char category,Session session) throws Exception
	{		
		String query="";
		 Query criteria = null;
		List<LocalBodyType> localbodytype=null;
		try{
		if(category=='P' || category=='p'){
			query = "from LocalBodyType l where l.category='R' and ispartixgovt=TRUE and isactive=TRUE order by l.level,l.localBodyTypeCode ";
		}
		else if(category=='T' || category=='t'){
			query = "from LocalBodyType l where l.category='R' and ispartixgovt=FALSE and isactive=TRUE order by l.level,l.localBodyTypeCode ";
		}
		else if(category=='U' || category=='u'){
			query = "from LocalBodyType l where l.category='U' and isactive=TRUE order by l.level,l.localBodyTypeCode ";
		}
		else{
			return null;
		}
		
		 session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			localbodytype = criteria.list();
		
		//localbodytype=sessionFactory.getCurrentSession().createQuery(query).list();
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodytype;
									
	}
	
	public int save(LocalBodyType contact) throws Exception
	{
		int valsave=0;
		try{
			valsave=(Integer) sessionFactory.getCurrentSession().save(contact);
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		return valsave;
	}

	
	

	@Override
	public boolean updateUrbanLocalBodies(String removedCodes, Integer stateCode, Integer userId) {
		// TODO Auto-generated method stub
		Session session = null;
		boolean isUpdated = false;
		try{
			session = sessionFactory.openSession();
			String str = "select * from update_local_bodies_urban(:localbodycodes, :statecode, :userid)";
			
			Query query = session.createSQLQuery(str);
			query.setParameter("localbodycodes", removedCodes);
			query.setParameter("statecode", stateCode);
			query.setParameter("userid", userId);
			isUpdated = (Boolean) query.uniqueResult();
		}
		catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
			//System.out.println(e.getMessage());
		}finally{
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return isUpdated;
	}

	@Override
	public boolean updateRuralLocalBodies(String operation, String localBodyTypeCodes, Integer stateCode, Integer userId) {
		// TODO Auto-generated method stub
		Session session = null;
		boolean isUpdated = false;
		try{
			session = sessionFactory.openSession();
			String str = "select * from update_local_bodies_rurals(:operation, :localbodycodes, :statecode, :userid)";
			Query query = session.createSQLQuery(str);
			query.setParameter("operation", operation);
			query.setParameter("localbodycodes", localBodyTypeCodes);
			query.setParameter("statecode", stateCode);
			query.setParameter("userid", userId);
			isUpdated = (Boolean) query.uniqueResult();
		}catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
			//System.out.println(e.getMessage());
		}finally{
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return isUpdated;
	}

	@Override
	public LocalBodyTiersSetup loadTierSetUp(Integer localBodyTypeCode,Integer tiresetupCode) {
		// TODO Auto-generated method stub
		Session session = null;
		LocalBodyTiersSetup tiresetup = null;
		try{
			session = sessionFactory.openSession();
			String str = "select model from LocalBodyTiersSetup model where model.isactive is true and model.localBodyTypeCode = :localBodyTypeCode and model.tierSetupCode=:tiresetupCode";
			
			Query query = session.createQuery(str);
			query.setParameter("localBodyTypeCode", localBodyTypeCode);
			query.setParameter("tiresetupCode", tiresetupCode);
			
			tiresetup = (LocalBodyTiersSetup) query.uniqueResult();
		}
		catch (Exception e) {
			// TODO: handle exception
			tiresetup = null;
			log.debug("Exception" + e);
			//System.out.println(e.getMessage());
		}finally{
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return tiresetup;
	}

	@Override
	public Integer maxTierSetupCode() {
		// TODO Auto-generated method stub
		Session session = null;
		Integer maxid = 1;
		try{
			session = sessionFactory.openSession();
			String str = "select max(tier_setup_code) from local_body_tiers_setup";
			Query query = session.createSQLQuery(str);
			maxid += (Integer) query.uniqueResult();
		}
		catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
			//System.out.println(e.getMessage());
		}finally{
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return maxid;
	}

	@Override
	public Object[] localbodySetupCodeandVersion(Integer statecode, Character category) {
		// TODO Auto-generated method stub
		Session session = null;
		Object[] details  = null;
		try{
			session = sessionFactory.openSession();
			String str = " select distinct local_body_setup_code, local_body_setup_version " +
						 " from local_body_tiers_setup where isactive and tier_setup_code in" +
						 " (SELECT tier_setup_code FROM get_local_gov_setup_fn(:statecode, :category))";
			Query query = session.createSQLQuery(str);
			query.setParameter("statecode", statecode);
			query.setParameter("category", category);
			details = (Object[]) query.uniqueResult();
		}
		catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
			//System.out.println(e.getMessage());
		}finally{
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return details;
	}

	@Override
	public boolean saveOrUpdateTierSetup(LocalBodyTiersSetup setup) {
		// TODO Auto-generated method stub
		boolean status = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(setup);
			tx.commit();
			status = true;
		}catch(Exception e){
			status = false;
		}finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}

	@Override
	public LocalBodySetup getLocalbodySetup(Integer statecode, Integer localBodySetupCode) {
		// TODO Auto-generated method stub
		Session session = null;
		LocalBodySetup setup = null;
		try{
			session = sessionFactory.openSession();
			String str = "select model from LocalBodySetup model where model.isactive is true and model.slc = :state and model.localBodySetupCode=:setupCode";
			
			Query query = session.createQuery(str);
			query.setParameter("state", statecode);
			query.setParameter("setupCode", localBodySetupCode);
			
			setup = (LocalBodySetup) query.uniqueResult();
		}finally{
			if(session != null && session.isOpen()){
				session.close();
				}
		}
		return setup;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getLocalBodyTypesRural() {
		// TODO Auto-generated method stub
		Session session = null;
		List<Object[]> ruralLocalBodies = new ArrayList<Object[]>();
		try{
			session = sessionFactory.openSession();
			String str = "select local_body_type_code, local_body_type_name, level from local_body_type where category='R' and isactive order by level='D' desc, level='I' desc,level='V' desc";
			
			Query query = session.createSQLQuery(str);
			ruralLocalBodies = query.list();
		}finally{
			if(session != null && session.isOpen()){
				session.close();
				}
		}
		return ruralLocalBodies;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDefinedTiersSetup(Integer statecode) {
		// TODO Auto-generated method stub
		Session session = null;
		List<Object[]> ruralLocalBodies = new ArrayList<Object[]>();
		try{
			session = sessionFactory.openSession();
			String queryBuilder = "	select distinct t.local_body_type_code,"+
								  " t.nomenclature_english,"+
								  " t.nomenclature_local,"+
								  //" case when t.parent_tier_setup_code is null or t.parent_tier_setup_code = 0  then -1 else t.parent_tier_setup_code end, "+
								  " t.parent_tier_setup_code,"+
								  " t.tier_setup_code,"+
								  " t.local_body_setup_code,"+
								  " t.local_body_setup_version,"+
								  " t.isactive,"+
								  " l.local_body_type_name"+
								  " from local_body_tiers_setup t, local_body_setup s, local_body_type l "+ 
								  "	where "+
								  " t.local_body_type_code=l.local_body_type_code "+
								  " and l.category='R' "+
								  " and t.local_body_setup_code = s.local_body_setup_code "+ 
								  " and t.local_body_setup_version = s.local_body_setup_version "+
								  " and s.isactive and t.isactive and l.isactive"+
								  " and s.slc = :stateCode"+
								  " order by t.tier_setup_code asc";
			
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("stateCode", statecode);
			ruralLocalBodies = query.list();
		}finally{
			if(session != null && session.isOpen()){session.close();}
		}
		return ruralLocalBodies;
		
	}

	@Override
	public LocalBodySetup addNewSetup(Integer stateCode, Integer userId) {
		// TODO Auto-generated method stub
		LocalBodySetup setup = null;
		try {
			int localBodySetupCode = localBodySetupDAO.getMaxRecords("select COALESCE(max(local_body_setup_code), 0) from local_body_setup");
		    int localBodySetupVersion = localBodySetupDAO.getMaxRecords("select COALESCE(max(local_body_setup_version), 0) from local_body_setup where local_body_setup_code = "+localBodySetupCode);
			LocalBodySetupPK setupPk = new LocalBodySetupPK(localBodySetupCode, localBodySetupVersion);
			Timestamp time = CurrentDateTime.getCurrentTimestamp();
			
			setup = new LocalBodySetup();
			setup.setLocalBodySetupPK(setupPk);
			setup.setSlc(stateCode);
			setup.setEffectiveDate(new Date());
			setup.setIsactive(true);
			setup.setCreatedOn(time);
			setup.setCreatedBy(new Long(userId));
			setup.setLastUpdated(time);
			setup.setLastUpdatedBy(new Long(userId));
			if(saveOrUpdateLBSetup(setup))
				return setup;
		} catch (Exception e) {
			setup = null;
		}
		return setup;
	}

	@Override
	public LocalBodySetup invalidateExistingSetup(Integer setupCode, Integer stateCode, Integer userId) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String setupQueryBuilder = " select model from LocalBodySetup model where " +
									   " model.isactive = :isActive and " +
									   " model.localBodySetupCode = :setupCode and " +
									   " model.slc= :stateCode";
			Query querySetup = session.createQuery(setupQueryBuilder);
			querySetup.setParameter("isActive", true);
			querySetup.setParameter("setupCode", setupCode);
			querySetup.setParameter("stateCode", stateCode);	
			LocalBodySetup setup = (LocalBodySetup) querySetup.uniqueResult();
			Integer setupVersion = setup.getLocalBodySetupVersion();
			Transaction tx = session.beginTransaction();
			String updateTiersQueryBuilder = " update local_body_tiers_setup set isactive=false " +
											 " where isactive " +
											 " and local_body_setup_code=:setupCode " +
											 " and local_body_setup_version=:setupVersion";
			Query queryTierSetup = session.createSQLQuery(updateTiersQueryBuilder);
			queryTierSetup.setParameter("setupCode", setupCode);
			queryTierSetup.setParameter("setupVersion", setupVersion);
			queryTierSetup.executeUpdate();
			setup.setIsactive(false);
			session.update(setup);
			tx.commit();
			session.clear();
			
			tx = session.beginTransaction();
			LocalBodySetupPK setupPk = new LocalBodySetupPK(setupCode, setupVersion + 1);
			Timestamp time = CurrentDateTime.getCurrentTimestamp();
			LocalBodySetup newsetup = new LocalBodySetup();
			newsetup.setLocalBodySetupPK(setupPk);
			newsetup.setSlc(stateCode);
			newsetup.setEffectiveDate(new Date());
			newsetup.setIsactive(true);
			newsetup.setCreatedOn(time);
			newsetup.setCreatedBy(new Long(userId));
			newsetup.setLastUpdated(time);
			newsetup.setLastUpdatedBy(new Long(userId));
			session.persist(newsetup);
			tx.commit();
			return newsetup;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return null;
	}

	@Override
	public Integer getParentCodeTierSetup(Integer localbodyType, Integer setupcode, Integer setupversion) {
		// TODO Auto-generated method stub
		Integer tiersetcode = null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			String str = " select tier_setup_code " +
						 " from local_body_tiers_setup where " +
						 " local_body_type_code=:lbType " +
						 " and local_body_setup_code =:setupCode " +
						 " and local_body_setup_version =:setupVersion " +
						 " and isactive";
			
			Query query = session.createSQLQuery(str);
			query.setParameter("lbType", localbodyType);
			query.setParameter("setupCode", setupcode);
			query.setParameter("setupVersion", setupversion);
			tiersetcode = (Integer) query.uniqueResult();
		}finally{
			if(session != null && session.isOpen()){session.close();}
		}
		return tiersetcode;
	}
	
	@Override
	public boolean saveOrUpdateLBSetup(LocalBodySetup setup) {
		// TODO Auto-generated method stub
		boolean status = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(setup);
			tx.commit();
			status = true;
		}catch(Exception e){
			status = false;
		}finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}

}
