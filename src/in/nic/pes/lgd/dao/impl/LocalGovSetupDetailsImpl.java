package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Designation;
import in.nic.pes.lgd.bean.Designationpojo;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LocalBodySetup;
import in.nic.pes.lgd.bean.LocalBodySubtype;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalbodyTypeInState;
import in.nic.pes.lgd.bean.OrganizationDesignation;
import in.nic.pes.lgd.bean.ReportingSetup;
import in.nic.pes.lgd.dao.LocalBodySetupDAO;
import in.nic.pes.lgd.service.CommonService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Service
public class LocalGovSetupDetailsImpl implements LocalBodySetupDAO {
	
	private static final Logger log = Logger.getLogger(LocalGovSetupDetailsImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	CommonService commonService;

	// Local Body Setup data to save
	@Override
	public boolean saveLocalBodySetup(LocalBodySetup localBodySetup,
			Session session) throws Exception {

		try {
			session.persist(localBodySetup);
			session.flush();
			session.refresh(localBodySetup);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}
	
	
	//Maneesh
	public Integer getSlcfromStateDAO(String hql) throws Exception
	{
		Session session=null;
		//Query query = null;
		//List <State> state=null;
		
		int slc=0;
		try {
			session = sessionFactory.openSession();
			//String hql="select slc from state where  state_code="+stateCode+" and state_version="+stateVersion;
			slc = Integer.parseInt(session.createSQLQuery(hql).list().get(0).toString());
			/*state=query.list();
			if(state.size()>0)
			 slc = state.get(0).getSlc();*/
			
			
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return slc;
				
	}
	
	//Maneesh
	
	

	public synchronized boolean saveLocalBodyTierSetup(
			LocalBodyTiersSetup localBodyTiersSetup, Session session)
			throws Exception {

		try {
			session.persist(localBodyTiersSetup);
			session.flush();
			session.refresh(localBodyTiersSetup);
		} catch (HibernateException e) {
			throw e;
		}
		return true;
	}

	// Local Body Sub Type data to save
	@Override
	public boolean saveLocalBodySubType(LocalBodySubtype localBodySubtype,
			Session session) throws Exception {
		try {
			session.persist(localBodySubtype);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrganizationDesignation> getOrganizationDesignationDetails(int olcCode, int olcLevel) throws Exception {

		Query criteria = null;
		Session session = null;
		List<OrganizationDesignation> designationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from OrganizationDesignation where olc=:olcCode and orgLocatedAtLevels.orgLocatedLevelCode=:olcLevel order by designationCode");
			criteria.setParameter("olcCode", olcCode, Hibernate.INTEGER);
			criteria.setParameter("olcLevel", olcLevel,	Hibernate.INTEGER);
			designationList = criteria.list();
		} catch (HibernateException e) {
			throw e;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return designationList;

	}
	

	// Designation table data to save
	public boolean saveDesignation(Designation designation, Session session)
			throws Exception {
		try {
			session.saveOrUpdate(designation);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public boolean saveReportingSetup(ReportingSetup reportingLbSetup, Session session) throws Exception {
		try {
			session.save(reportingLbSetup);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	/*@Override
	public boolean saveLGSetupDetails(LGSetupForm lGSetupForm, Session session)
			throws Exception {

		return true;
	}*/

	public int getMaxRecords(String query) throws Exception {
		int MaxCode = 0;
		Session session = sessionFactory.openSession();
		try {

			Transaction tx = session.beginTransaction();
			MaxCode = Integer.parseInt(session.createSQLQuery(query).list()
					.get(0).toString()) + 1;
			tx.commit();

		} catch (Exception e) {
			throw e;
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}

		}
		return MaxCode;
	}

	@Override
	public boolean getStateCode(int stateCode) throws Exception {
		boolean isExist = false;
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createSQLQuery("select state_code from local_body_setup where state_code=:stateCode");
			criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			if (criteria.list().size() != 0) {
				isExist = true;
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}

		return isExist;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetLocalGovtSetup> isStateSetup(int stateCode, char category) throws Exception {
		Session session = null;
		List<GetLocalGovtSetup> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Query criteria = session.getNamedQuery("getLocalGovSetupFn");
			criteria.setParameter("stateCode", stateCode);
			criteria.setParameter("category", category);
			list = criteria.list();
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
	public List<GetLocalGovtSetup> isStateSetupDisturbed(int stateCode, char category, char level) throws Exception {
		Session session = null;
		List<GetLocalGovtSetup> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Query criteria = session.getNamedQuery("getLocalGovSetupFnDisturbed");
			criteria.setParameter("stateCode", stateCode);
			criteria.setParameter("category", category);
			criteria.setParameter("level", level);
			list = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getLGType(String sql, int stateCode, int stateVersion, char category) throws Exception {
		List lGType = new ArrayList();
		Session session = null;
		Query query;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery(sql);
			lGType = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return lGType;
	}

	@SuppressWarnings("rawtypes")
	public List getSubType(int statecode) throws Exception {
		Session session = null;
		Query criteria = null;
		List list = new ArrayList();

		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("TypeSub");
			criteria.setParameter("state", statecode, Hibernate.INTEGER);
			list = criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	

		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Designationpojo> getDesignation(int tierSetupCode)
			throws Exception {
		Session session = null;
		Query criteria = null;
		List<Designationpojo> desigList = new ArrayList<Designationpojo>();
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("GetDesignation");
			criteria.setParameter("tierSetupCode", tierSetupCode);
			desigList = criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	

		}
		return desigList;
	}

	@SuppressWarnings("rawtypes")
	public List getSubTier(int statecode) throws Exception {
		Session session = null;
		Query criteria = null;
		List list = new ArrayList();

		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("GetTier");
			criteria.setParameter("state", statecode, Hibernate.INTEGER);
			list = criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	

		}
		return list;
	}

	@Override
	public List<LocalBodySetup> getLocalBodySetupDetails(int stateCode, int stateVersion, char category) throws Exception {
		List<LocalBodySetup> setupDetail = new ArrayList<LocalBodySetup>();
		LocalBodySetup localBodySetup = new LocalBodySetup();
		String sqlCode = null;
		String sqlVersion = null;
		Session session = null;
		
		Integer slc=commonService.getSlc(stateCode);
		
		try {
			if (category == 'U' || category == 'u') {
				category = 'U';

				sqlCode = "SELECT distinct(local_body_setup.local_body_setup_code) FROM  local_body_setup,   local_body_tiers_setup,   local_body_type WHERE  local_body_setup.local_body_setup_code = local_body_tiers_setup.local_body_setup_code AND  local_body_setup.local_body_setup_version = local_body_tiers_setup.local_body_setup_version AND  local_body_type.local_body_type_code = local_body_tiers_setup.local_body_type_code AND  local_body_setup.slc = "
						+ slc
						+ " AND  local_body_type.category = '"
						+ category
						+ "' AND  local_body_setup.isactive = true AND  local_body_tiers_setup.isactive = true";
				category = 'U';
			} else if (category == 'P' || category == 'p') {
				category = 'R';

				sqlCode = "SELECT distinct(local_body_setup.local_body_setup_code) FROM  local_body_setup,   local_body_tiers_setup,   local_body_type WHERE  local_body_setup.local_body_setup_code = local_body_tiers_setup.local_body_setup_code AND  local_body_setup.local_body_setup_version = local_body_tiers_setup.local_body_setup_version AND  local_body_type.local_body_type_code = local_body_tiers_setup.local_body_type_code AND  local_body_setup.slc = "
						+ slc
						+ " AND  local_body_type.category = '"
						+ category
						+ "' AND  local_body_setup.isactive = true AND  local_body_tiers_setup.isactive = true AND  local_body_type.ispartixgovt = true";
				category = 'P';
			} else if (category == 'T' || category == 't') {
				category = 'R';

				sqlCode = "SELECT distinct(local_body_setup.local_body_setup_code) FROM  local_body_setup,   local_body_tiers_setup,   local_body_type WHERE  local_body_setup.local_body_setup_code = local_body_tiers_setup.local_body_setup_code AND  local_body_setup.local_body_setup_version = local_body_tiers_setup.local_body_setup_version AND  local_body_type.local_body_type_code = local_body_tiers_setup.local_body_type_code AND  local_body_setup.slc = "
						+ slc
						+ " AND  local_body_type.category = '"
						+ category
						+ "' AND  local_body_setup.isactive = true AND  local_body_tiers_setup.isactive = true AND  local_body_type.ispartixgovt = false";
				category = 'T';
			}
			if (sqlCode != null){
				session = sessionFactory.openSession();
			@SuppressWarnings("unchecked")
			List<Object> temp = session.createSQLQuery(sqlCode).list();
			localBodySetup.setLocalBodySetupCode((Integer) temp.get(0));
			}
			if (category == 'U' || category == 'u') {
				category = 'U';

				sqlVersion = "SELECT distinct(local_body_setup.local_body_setup_version) FROM  local_body_setup,   local_body_tiers_setup,   local_body_type WHERE  local_body_setup.local_body_setup_code = local_body_tiers_setup.local_body_setup_code AND  local_body_setup.local_body_setup_version = local_body_tiers_setup.local_body_setup_version AND  local_body_type.local_body_type_code = local_body_tiers_setup.local_body_type_code AND  local_body_setup.slc = "
						+ slc
						+ " AND  local_body_type.category = '"
						+ category
						+ "' AND  local_body_setup.isactive = true AND  local_body_tiers_setup.isactive = true";
				category = 'U';
			} else if (category == 'P' || category == 'p') {
				category = 'R';

				sqlVersion = "SELECT distinct(local_body_setup.local_body_setup_version) FROM  local_body_setup,   local_body_tiers_setup,   local_body_type WHERE  local_body_setup.local_body_setup_code = local_body_tiers_setup.local_body_setup_code AND  local_body_setup.local_body_setup_version = local_body_tiers_setup.local_body_setup_version AND  local_body_type.local_body_type_code = local_body_tiers_setup.local_body_type_code AND  local_body_setup.slc = "
						+ slc
						+ " AND  local_body_type.category = '"
						+ category
						+ "' AND  local_body_setup.isactive = true AND  local_body_tiers_setup.isactive = true AND  local_body_type.ispartixgovt = true";
				category = 'P';
			} else if (category == 'T' || category == 't') {
				category = 'R';

				sqlVersion = "SELECT distinct(local_body_setup.local_body_setup_version) FROM  local_body_setup,   local_body_tiers_setup,   local_body_type WHERE  local_body_setup.local_body_setup_code = local_body_tiers_setup.local_body_setup_code AND  local_body_setup.local_body_setup_version = local_body_tiers_setup.local_body_setup_version AND  local_body_type.local_body_type_code = local_body_tiers_setup.local_body_type_code AND  local_body_setup.slc = "
						+ slc
						+ " AND  local_body_type.category = '"
						+ category
						+ "' AND  local_body_setup.isactive = true AND  local_body_tiers_setup.isactive = true AND  local_body_type.ispartixgovt = false";
				category = 'T';
			} else {
				sqlVersion = null;
			}
	 
			if(sqlVersion !=null){
			@SuppressWarnings("unchecked")
			List<Object> temp1 = session.createSQLQuery(sqlVersion).list();
			localBodySetup.setLocalBodySetupVersion((Integer) temp1.get(0));
			}
		
			
			setupDetail.add(localBodySetup);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}finally{
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return setupDetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyTiersSetup> getTierSetupDetails(int setupCode,
			int setupVersion) throws Exception {

		Query criteria = null;
		Session session = null;
		List<LocalBodyTiersSetup> localBodyTiersSetupList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from LocalBodyTiersSetup where localBodySetupCode=:setupCode And localBodySetupVersion=:setupVersion");
			criteria.setParameter("setupCode", setupCode, Hibernate.INTEGER);
			criteria.setParameter("setupVersion", setupVersion,
					Hibernate.INTEGER);
			localBodyTiersSetupList = criteria.list();
		} catch (HibernateException e) {
			throw e;
		} finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
		return localBodyTiersSetupList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodySubtype> getSubTypeDetails(int tierSetupCode)
			throws Exception {

		Query criteria = null;
		Session session = null;
		List<LocalBodySubtype> localBodySubtypeList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from LocalBodySubtype where localBodyTiersSetup.tierSetupCode=:tierSetupCode  And isactive='true'");
			criteria.setParameter("tierSetupCode", tierSetupCode,
					Hibernate.INTEGER);
			localBodySubtypeList = criteria.list();
		} catch (HibernateException e) {
			throw e;
		} finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
		return localBodySubtypeList;

	}

	/*@Override
	public boolean updateIsActiveSubType(boolean isActive, int stateCode,
			int stateVersion, Session session) throws Exception {
		boolean retval = false;
		try {

			session.createSQLQuery(
					"update local_body_subtype set isactive=:isActive where state_code=:stateCode and state_version=:stateVersion")
					.setParameter("isActive", isActive, Hibernate.BOOLEAN)
					.setParameter("stateCode", stateCode, Hibernate.INTEGER)
					.setParameter("stateVersion", stateVersion,
							Hibernate.INTEGER).executeUpdate();
			retval = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			retval = false;
		}
		return retval;
	}*/

	/*public boolean updateIsActiveLocalBodySetup(boolean isActive,
			int setupCode, int setupVersion, Session session) throws Exception {
		boolean retval = false;
		try {
			session.createSQLQuery(
					"update local_body_setup set isactive=:isActive where local_body_setup_code=:setupCode and local_body_setup_version=:setupVersion")
					.setParameter("isActive", isActive, Hibernate.BOOLEAN)
					.setParameter("setupCode", setupCode, Hibernate.INTEGER)
					.setParameter("setupVersion", setupVersion,
							Hibernate.INTEGER).executeUpdate();

			retval = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			retval = false;
		}
		return retval;

	}*/

	public boolean updateIsActiveTierSetUp(boolean isActive, int setupCode,
			int setupVersion, Session session) throws Exception {
		boolean retval = false;
		try {
			session.createSQLQuery(
					"update local_body_tiers_setup set isactive=:isActive where local_body_setup_code=:setupCode and local_body_setup_version=:setupVersion")
					.setParameter("isActive", isActive, Hibernate.BOOLEAN)
					.setParameter("setupCode", setupCode, Hibernate.INTEGER)
					.setParameter("setupVersion", setupVersion,Hibernate.INTEGER).executeUpdate();

			retval = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			retval = false;
		}
		return retval;
	}

	public boolean updateIsActiveLbSubtype(boolean isActive, int tierSetupCode,
			Session session) throws Exception {
		boolean retval = false;
		try {
			session.createSQLQuery(
					"update local_body_subtype set isactive=:isActive where tier_setup_code=:tierSetupCode")
					.setParameter("isActive", isActive, Hibernate.BOOLEAN)
					.setParameter("tierSetupCode", tierSetupCode,Hibernate.INTEGER).executeUpdate();
			retval = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			retval = false;
		}
		return retval;
	}

	@SuppressWarnings("rawtypes")
	public List getDesignationElected(int statecode, boolean islocalbody)
			throws Exception {
		Query criteria = null;
		Session session = null;
		List list = new ArrayList();
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("DesignationElected");
			criteria.setParameter("state", statecode, Hibernate.INTEGER);
			criteria.setParameter("islocalbody", islocalbody, Hibernate.BOOLEAN);
			list = criteria.list();

		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null &&  session.isOpen()){
				session.close();
			}	
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getDesignationOfficail(int statecode, boolean islocalbody)
			throws Exception {
		Query criteria = null;
		Session session = null;
		List list = new ArrayList();
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("DesignationOfficial");
			criteria.setParameter("state", statecode, Hibernate.INTEGER);
			criteria.setParameter("islocalbody", islocalbody, Hibernate.BOOLEAN);
			list = criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null &&  session.isOpen()){
				session.close();
			}	
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Designation> getDesignationDetails(boolean isElected,
			int tierSetupCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Designation> designationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createQuery("from Designation where islocalbody=:isElected and tierSetupCode=:tierSetupCode order by designationCode");
			criteria.setParameter("isElected", isElected, Hibernate.BOOLEAN);
			criteria.setParameter("tierSetupCode", tierSetupCode,
					Hibernate.INTEGER);
			designationList = criteria.list();
		} catch (HibernateException e) {
			throw e;
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}	
		}
		return designationList;

	}
	
	/*@Override
	public boolean deleteDesignation(int tierSetupCode) throws Exception {
		boolean retval = false;
		Session session = null;
		Query query;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(
					"delete from Designation where "
							+ "tierSetupCode=:tierSetupCode").setParameter(
					"tierSetupCode", tierSetupCode, Hibernate.INTEGER);
			query.executeUpdate();
			retval = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			retval = false;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return retval;
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyTypeInState> getLBList(int stateCode, char category)
			throws Exception {
	 

		List<LocalbodyTypeInState> localbodyTypeInStateList = null;
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.getNamedQuery("getLocalBodyTypeDetailsByStateFn");
			criteria.setParameter("stateCode", stateCode);
			criteria.setParameter("category", category);
			localbodyTypeInStateList = criteria.list();

		} catch (Exception e) {
			throw e;

		} finally {
			if (session != null &&  session.isOpen()){
				session.close();
			}	

		}
		return localbodyTypeInStateList;

	}

	@Override
	public boolean saveLBSubType(LocalBodySubtype localBodySubtypeBean, Session session) throws Exception {
		try {
			session.save(localBodySubtypeBean);
		} catch (HibernateException e) {
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateIsActiveSetup(LocalBodySetup localBodySetupBean,
			Session session) throws Exception {
		 
		try {
			session.update(localBodySetupBean);
		 
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			throw e;
		}
		return true;
	}

	

	/*@Override
	public int getMaxRecords(String query, Session session) throws Exception {
		int MaxCode = 0;

		try {
			session = sessionFactory.openSession();
			MaxCode = Integer.parseInt(session.createSQLQuery(query).list()
					.get(0).toString()) + 1;
		} catch (Exception e) {
			throw e;
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return MaxCode;
	}*/
	
	/*@Override
	public List<Designation> getTierPanchayat(List<GetLocalGovtSetup> tierSetupList, Session session) throws Exception
	{
		List<Designation> queryList=null;
		try 
		{
			session = sessionFactory.openSession();
			//List<GetLocalGovtSetup> userid=tierSetupList.list();
			List<Integer> localgovtlist=new ArrayList<Integer>(); 
			
			Iterator<GetLocalGovtSetup> localgovsetupitr=tierSetupList.iterator();
			while(localgovsetupitr.hasNext())
			{
				localgovtlist.add(localgovsetupitr.next().getTierSetupCode());
			}
			//System.out.println("Userid list"+localgovtlist.size());
			
			
			Query query2=session.createQuery("from Designation where tierSetupCode in(:tierSetupCode) order by istopdesignation DESC");
			query2.setParameterList("tierSetupCode",localgovtlist);
			
			//System.out.println("user id list.............."+query2.list().size());
			queryList=query2.list();
		}
		catch (Exception e) 
		{
			throw e;
		}
		finally 
		{
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return queryList;
	}*/
	
	/*@Override
	public List<Designation> getTierTraditional(List<GetLocalGovtSetup> tierSetupList, Session session) throws Exception
	{
		List<Designation> queryList=null;
		try 
		{
			session = sessionFactory.openSession();
			//List<GetLocalGovtSetup> userid=tierSetupList.list();
			List<Integer> localgovtlist=new ArrayList<Integer>(); 
			
			Iterator<GetLocalGovtSetup> localgovsetupitr=tierSetupList.iterator();
			while(localgovsetupitr.hasNext())
			{
				localgovtlist.add(localgovsetupitr.next().getTierSetupCode());
			}
			//System.out.println("Userid list"+localgovtlist.size());
			
			
			Query query2=session.createQuery("from Designation where tierSetupCode in(:tierSetupCode)");
			query2.setParameterList("tierSetupCode",localgovtlist);
			
			//System.out.println("user id list.............."+query2.list().size());
			queryList=query2.list();
		}
		catch (Exception e) 
		{
			throw e;
		}
		finally 
		{
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return queryList;
	}*/
	
	/*@Override
	public List<Designation> getTierUrban(List<GetLocalGovtSetup> tierSetupList, Session session) throws Exception
	{
		List<Designation> queryList=null;
		try 
		{
			session = sessionFactory.openSession();
			List<Integer> localgovtlist=new ArrayList<Integer>(); 
			Iterator<GetLocalGovtSetup> localgovsetupitr=tierSetupList.iterator();
			while(localgovsetupitr.hasNext())
			{
				localgovtlist.add(localgovsetupitr.next().getTierSetupCode());
			}
			
			Query query2=session.createQuery("from Designation where tierSetupCode in(:tierSetupCode)");
			query2.setParameterList("tierSetupCode",localgovtlist);
			
			queryList=query2.list();
		}
		catch (Exception e) 
		{
			throw e;
		}
		finally 
		{
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return queryList;
	}*/
	
	
	/*@Override
	public List<State> getStateDetailDAO(String HQL,Session session) throws Exception
	{
		List<State> result=null;
		Query query=null;
		result=new ArrayList<State>(); 
		try 
		{
			session = sessionFactory.openSession();
			
			query=session.createQuery(HQL);
			result=query.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		finally 
		{
			if(session !=null && session.isOpen()){
				session.close();
			}
			
		}
		return result;
	}*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GetLocalGovtSetup> getLocalbodyDetailDAO(int stateCode) throws Exception {
		Session session = null;
		List<GetLocalGovtSetup> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Query criteria = session.getNamedQuery("getLocalGovSetupFnF");
			criteria.setParameter("stateCode", stateCode);
			list = criteria.list();
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
	public List<GetLocalGovtSetup> getLocalbodyDetailDAO(int stateCode, String localGovtListFlag) throws Exception {
		Session session = null;
		Query criteria = null;
		List<GetLocalGovtSetup> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("getLocalGovSetupFnFP");
			criteria.setParameter("stateCode", stateCode);
			criteria.setParameter("localGovtListFlag", localGovtListFlag);
			list = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return list;
	}

	//Tanuj
	
	/*@Override
	public List<LocalBodySetup> getLocalBodySetupDetails(int stateCode,
			int stateVersion, Session session) throws Exception {
	 
		Query criteria = null;
		List<LocalBodySetup> setupDetail = new ArrayList<LocalBodySetup>();
	 
		try {
			@SuppressWarnings("unchecked")
			LocalBodySetup localBodySetup = new LocalBodySetup();
			@SuppressWarnings("unchecked")
			List<Object> temp = session
					.createSQLQuery(
							"select local_body_setup_code from local_body_setup where state_code=:stateCode And state_version=:stateVersion")
					.setParameter("stateCode", stateCode, Hibernate.INTEGER)
					.setParameter("stateVersion", stateVersion,
							Hibernate.INTEGER).list();
			@SuppressWarnings("unchecked")
			int localBodySetupCode = (Integer) temp.get(0);
			@SuppressWarnings("unchecked")
			List<Object> temp1 = session
					.createSQLQuery(
							"select COALESCE(max(local_body_setup_version),1) from local_body_setup where state_code=:stateCode And state_version=: stateVersion  And local_body_setup_code=:localBodySetupCode")
					.setParameter("stateCode", stateCode, Hibernate.INTEGER)
					.setParameter("stateVersion", stateVersion,
							Hibernate.INTEGER)
					.setParameter("localBodySetupCode", localBodySetupCode,
							Hibernate.INTEGER).list();
			localBodySetup.setLocalBodySetupCode(localBodySetupCode);
			localBodySetup.setLocalBodySetupVersion((Integer) temp1.get(0));
			setupDetail.add(localBodySetup);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return setupDetail;
	}*/
	
	
	@SuppressWarnings("unchecked")
	public boolean checkTierPanchayatforDesignation(List<GetLocalGovtSetup> tierSetupList, Integer stateCode) throws Exception {
		 	Session session = null;
			boolean find = false, flag = true;
			try {
				
		    	Integer slc = commonService.getSlc(stateCode);
				String queryString=" select distinct local_body_tiers_setup.tier_setup_code from "+ 
								   " lgd_designation designation "+
								   " inner join local_body_tiers_setup on designation.tier_setup_code = local_body_tiers_setup.tier_setup_code"+ 
								   " left join local_body_setup on local_body_tiers_setup.local_body_setup_code = local_body_setup.local_body_setup_code"+ 
								   " and local_body_tiers_setup.local_body_setup_version = local_body_setup.local_body_setup_version "+
								   " where designation.isactive and designation.designation_type = 'O' "+
								   " and local_body_setup.slc = :paramSlc";

				session = sessionFactory.openSession();
				Query query = session.createSQLQuery(queryString).setParameter("paramSlc", slc);
				List<Integer> temp = query.list();
				
				if(temp.size() > 0) {
					for(GetLocalGovtSetup element:tierSetupList) {
						int tierSetupCode = element.getTierSetupCode();
						find = false;
						for(int i = 0; i < temp.size(); i++) {
							if(tierSetupCode == temp.get(i)) {
								find = true;
								break;
							}
						}
						
						if(!find) {
							flag = false;
							break;
						}
					}
				}
			} catch (Exception e) {
				log.debug("Exception" + e);
				flag = false;
			} finally {
				if (session != null && session.isOpen()){
					session.close();
				}	
			}
			return flag;
		}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GetLocalGovtSetup> getLocalbodyDetailDAOOrderInLocalBodyType(int stateCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<GetLocalGovtSetup> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("getLocalGovSetupForDeptMapping");
			criteria.setParameter("stateCode", stateCode);
			list = criteria.list();
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
	public List<GetLocalGovtSetup> getLocalbodyDetailForVillage(int stateCode) throws Exception {
		Session session = null;
		List<GetLocalGovtSetup> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Query criteria = session.getNamedQuery("getLocalGovSetupFnFPopulation");
			criteria.setParameter("stateCode", stateCode);
			list = criteria.list();
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
	public List<GetLocalGovtSetup> getLocalbodyDetailForTraditional(int stateCode,char category) throws Exception {
		Session session = null;
		List<GetLocalGovtSetup> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Query criteria = session.getNamedQuery("getLocalGovSetupVPanchayat");
			criteria.setParameter("stateCode", stateCode);
			criteria.setParameter("category", category);
			list = criteria.list();
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
	public List<GetLocalGovtSetup> getLocalbodyDetailCategoryDAO(int stateCode,Character category) {
		Session session = null;
		List<GetLocalGovtSetup> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Query criteria = session.getNamedQuery("getLocalGovSetupFnFCategory");
			criteria.setParameter("stateCode", stateCode);
			criteria.setParameter("category", category);
			list = criteria.list();
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
	public List<GetLocalGovtSetup> getLocalbodyDetailDAOCantonment(int stateCode) throws Exception {
		Session session = null;
		List<GetLocalGovtSetup> list = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			Query criteria = session.getNamedQuery("getLocalGovSetupFnCantonment");
			criteria.setParameter("stateCode", stateCode);
			list = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return list;
	}
	
}
