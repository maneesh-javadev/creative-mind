package in.nic.pes.lgd.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.nic.pes.lgd.bean.AdministrativeEntityCoverage;
import in.nic.pes.lgd.bean.ChangeLvlSpecificNameOrg;
import in.nic.pes.lgd.bean.DepartmentByCentreLevel;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.DesignationMaster;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictLineDepartment;
import in.nic.pes.lgd.bean.ExtendDepartment;
import in.nic.pes.lgd.bean.GetOrganizationAtLevels;
import in.nic.pes.lgd.bean.GetOrganizationListFn;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationByCentreLevel;
import in.nic.pes.lgd.bean.OrganizationCoverage;
import in.nic.pes.lgd.bean.OrganizationCoverageDetail;
import in.nic.pes.lgd.bean.OrganizationDesignation;
import in.nic.pes.lgd.bean.OrganizationPK;
import in.nic.pes.lgd.bean.OrganizationType;
import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.bean.PushOrgDepToPes;
import in.nic.pes.lgd.bean.ReportingSetup;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateLineDepartment;
import in.nic.pes.lgd.constant.DepartmentConstent;
import in.nic.pes.lgd.dao.OrganizationDAO;
import in.nic.pes.lgd.forms.DepartmentAdminForm;
import in.nic.pes.lgd.forms.DepartmentForm;
import in.nic.pes.lgd.forms.MinistryForm;
import in.nic.pes.lgd.forms.OrgLocatedAtLevelsForm;
import in.nic.pes.lgd.forms.OrganizationTypeForm;
import in.nic.pes.lgd.forms.OrganizationUnitForm;
import in.nic.pes.lgd.interceptor.LanguageListener;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.impl.OrganizationServiceImpl;
import in.nic.pes.lgd.utils.CommonUtil;
import in.nic.pes.lgd.utils.CurrentDateTime;

@Repository
@Transactional
public class OrganizationDAOImpl implements OrganizationDAO {
	private static final Logger log = Logger.getLogger(OrganizationServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CommonService commonService;

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getMinistryList() throws Exception {
		List<Organization> organization = null;
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization org where org.organizationType.orgTypeCode=1 and org.isactive=true order by orgName");
			organization = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organization;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getDepartmentList() throws Exception {
		List<Organization> organization = null;
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization org where org.organizationType.orgTypeCode=2 and org.isactive=true");
			organization = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organization;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationType> getOrgTypeList() throws Exception {
		List<OrganizationType> organizationtype = null;
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrganizationType");
			organizationtype = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationtype;
	}

	@SuppressWarnings("unchecked")
	public List<Organization> getMinistryListbyState(int stateCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<Organization> organizationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization org where org.organizationType.orgTypeCode=1 and org.isactive=true and org.stateCode=:stateCode");
			criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			organizationList = criteria.list();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;
		// return
		// sessionFactory.getCurrentSession().createQuery("from Organization org where org.organizationType.orgTypeCode=1 and org.isactive=true and org.stateCode="+stateCode).list();
	}

	@SuppressWarnings("unchecked")
	public List<Organization> getDepartmentListByMinistry(int orgCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<Organization> organizationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization org where isactive=true and org.parentorgcode=:orgCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			organizationList = criteria.list();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;
		// return
		// sessionFactory.getCurrentSession().createQuery("from Organization org where isactive=true and org.parentorgcode="+
		// orgCode).list();
	}

	@SuppressWarnings("unchecked")
	public List<StateLineDepartment> getStateLineDepartmentList(int stateCode, int deptCode, char entityType) throws Exception {

		Session session = null;
		Query query = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getDepartmentDetails").setParameter("stateCode", stateCode).setParameter("deptCode", deptCode).setParameter("entityType", entityType);
			list = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DistrictLineDepartment> getDistrictLineDepartmentList(int districtCode, char level) throws Exception {

		Session session = null;
		Query query = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLineDepartmentDetails").setParameter("districtCode", districtCode).setParameter("level", level);
			list = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DistrictLineDepartment> getSubDistrictLineDepartmentList(int subDistrictCode, char level) throws Exception {

		Session session = null;

		Query query = null;
		@SuppressWarnings("rawtypes")
		List list = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLineDepartmentDetail").setParameter("subDistrictCode", subDistrictCode).setParameter("level", level);
			list = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DistrictLineDepartment> getBlockLineDepartmentList(int blockCode, char level) throws Exception {

		Session session = null;
		Query query = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getBlockLineDepartmentDetail").setParameter("blockCode", blockCode).setParameter("level", level);
			list = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DistrictLineDepartment> getVillageLineDepartmentList(int villageCode, char level) throws Exception {

		Session session = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLineDepartmentDetailsByVillage").setParameter("villageCode", villageCode).setParameter("level", level);
			list = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentByCentreLevel> getDepartmentListByCentreLevel(char entityCode) throws Exception {

		Session session = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getCentreLevelDepartmentDetails").setParameter("entityCode", entityCode);
			list = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<OrganizationByCentreLevel> getOrganizationListByCentreLevel(char entityCode) throws Exception {

		Session session = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getCentreLevelOrganizationDetails").setParameter("entityCode", entityCode);
			list = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	public int getRecordsforMinistry(String ministryName) throws Exception {

		Query criteria = null;
		Session session = null;
		int recordCount = 0;
		try {

			session = sessionFactory.openSession();
			/*
			 * Query modified by Pooja on 07-07-2015 for consider many
			 * whitespaces as one whitespace b/w name
			 */
			criteria = session
					.createSQLQuery("select count(*) from organization where org_type_code=1 and isactive=true and upper(btrim(replace(replace(replace(org_name,' ','<>'),'><',''),'<>',' '))) LIKE upper(btrim(replace(replace(replace(:ministryName,' ','<>'),'><',''),'<>',' '))) ");
			criteria.setParameter("ministryName", ministryName, Hibernate.STRING);
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			if (!list.isEmpty() && list.get(0) != null) {
				recordCount = Integer.parseInt(list.get(0).toString());
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return recordCount;
	}

	public void saveMinistryDetails(Organization org) throws Exception {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.save(org);
			session.flush();

		} catch (Exception e) {
			
			log.error("Exception-->" + e);
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	public int getMaxCode() throws Exception {
		int MaxCode = 0;
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select COALESCE(max(org_code),1) from organization");

			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxCode = (Integer) list.get(0);
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return MaxCode;
	}

	public int getRecordsforOrganizationType(String orgTypeName) throws Exception {

		Query criteria = null;
		Session session = null;

		int recordCount = 0;
		try {
			session = sessionFactory.openSession();
			orgTypeName = orgTypeName.trim().toUpperCase();
			criteria = session.createSQLQuery("select count(*) from organization_type where UPPER(TRIM(org_type)) LIKE :orgTypeName");
			criteria.setParameter("orgTypeName", orgTypeName, Hibernate.STRING);
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			if (!list.isEmpty() && list.get(0) != null) {
				recordCount = Integer.parseInt(list.get(0).toString());
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return recordCount;

	}

	@Override
	public int getRecordsforDeptValidation(String orgName, int... orgTypeCode) throws Exception {
		Query criteria = null;
		Session session = null;
		orgName = orgName.trim().toUpperCase();
		int recordCount = 0;
		try {
			session = sessionFactory.openSession();
			if (orgTypeCode[0] != 0) {
				criteria = session.createSQLQuery("select count(*) from organization where org_type_code=:orgTypeCode and isactive=true and org_level='C' and UPPER(TRIM(org_name)) LIKE UPPER(:orgName)");
				criteria.setParameter("orgTypeCode", orgTypeCode[0], Hibernate.INTEGER);
				criteria.setParameter("orgName", orgName, Hibernate.STRING);
			} else if (orgTypeCode[1] != 0) {
				criteria = session.createSQLQuery("select count(*) from organization where isactive=true and org_level='C' and UPPER(TRIM(org_name)) LIKE UPPER(:orgName)");
				criteria.setParameter("orgName", orgName, Hibernate.STRING);
			}
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			if (!list.isEmpty()) {
				recordCount = Integer.valueOf(list.get(0).toString());
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return recordCount;
	}

	@Override
	public int getRecordsforDeptValidation(String orgName, int orgTypeCode, int stateCode) throws Exception {

		Query criteria = null;
		Session session = null;
		orgName = orgName.trim().toUpperCase();
		int recordCount = 0;
		try {
			session = sessionFactory.openSession();
			criteria = session
					.createSQLQuery("select count(*) from organization,state where state.slc = organization.slc AND state.isactive = TRUE AND org_type_code=:orgTypeCode and organization.isactive=true and org_level='S' and state_code=:stateCode and UPPER(TRIM(org_name)) LIKE :orgName");
			criteria.setParameter("orgTypeCode", orgTypeCode, Hibernate.INTEGER);
			criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			criteria.setParameter("orgName", orgName, Hibernate.STRING);
			recordCount = Integer.parseInt(criteria.list().get(0).toString());
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return recordCount;

	}

	public void saveOrganisationType(OrganizationType orgType) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.save(orgType);
			session.flush();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DesignationMaster> getDesignationName(String designationName) throws Exception {
		String desname = designationName;
		String desnameInitial = null;
		Query criteria = null;
		Session session = null;
		List<DesignationMaster> designationMasterList = null;
		desname = desname.toLowerCase();
		desnameInitial = desname.substring(0, 1).toUpperCase();
		desname = desnameInitial + desname.substring(1, desname.length());
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from DesignationMaster where designationName like :desname%");
			criteria.setParameter("desname", desname, Hibernate.INTEGER);
			designationMasterList = criteria.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return designationMasterList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganization() throws Exception {
		List<Organization> organization = null;
		Query query = null;

		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Organization");
			organization = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organization;
	}

	@Override
	public boolean saveDesignationMaster(DesignationMaster designationMaster, Session session) throws Exception {
		try {
			session.save(designationMaster);
			return true;
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
	}

	@Override
	public boolean saveOrganizationDesignation(OrganizationDesignation organizationDesignation, Session session) throws Exception {
		try {
			session.save(organizationDesignation);
			session.flush();
			session.clear();
			return true;
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
	}

	@Override
	public boolean saveReportingOrg(ReportingSetup reportingOrg, Session session) throws Exception {
		try {
			session.save(reportingOrg);
			session.flush();
			session.clear();
			return true;
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
	}

	public DesignationMaster getDesignationCode(String designationName) throws Exception {

		Query criteria = null;
		Session session = null;
		designationName = designationName.trim();
		DesignationMaster designationMaster = null;
		designationMaster = new DesignationMaster();
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from DesignationMaster where designationName =:designationName.trim()");
			criteria.setParameter("designationName", designationName, Hibernate.STRING);
			designationMaster = (DesignationMaster) criteria.list().get(0);
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return designationMaster;

	}

	@Override
	public int setDesignationMaster(String designationName) throws Exception {
		int designationCode = 0;
		Session session = null;
		Transaction txn = null;
		DesignationMaster designationMaster = new DesignationMaster();
		designationMaster.setDesignationName(designationName);
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			session.save(designationMaster);
			session.flush();
			session.refresh(designationMaster);
			designationCode = designationMaster.getDesignationCode();
			txn.commit();
		} catch (HibernateException e) {
			if (txn != null) {
				txn.rollback();
			}

			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return designationCode;
	}

	@Override
	public Organization getOrganizationDetailByCode(int orgCode) throws Exception {
		Query criteria = null;
		Session session = null;
		Organization organization = null;
		organization = new Organization();
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization where organization.orgCode =:orgCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			organization = (Organization) criteria.list().get(0);
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organization;
	}

	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationDetailByOrgCode(int orgCode) throws Exception {
		List<Organization> listOrganization = null;
		listOrganization = new ArrayList<Organization>();
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization where organization.orgCode =:orgCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			listOrganization = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listOrganization;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Organization> getOrgbyOrgType(int orgTypeCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Organization> organizationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization org where org.isactive=true and org.organizationType.orgTypeCode =:orgTypeCode order by orgName");
			criteria.setParameter("orgTypeCode", orgTypeCode, Hibernate.INTEGER);
			organizationList = criteria.list();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Organization> getOrgbyOrgTypeForReporting(int orgTypeCode, int orgCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Organization> organizationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization org where org.isactive=true and org.orgCode!=:orgCode and org.organizationType.orgTypeCode =:orgTypeCode order by orgName");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			criteria.setParameter("orgTypeCode", orgTypeCode, Hibernate.INTEGER);
			organizationList = criteria.list();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationDesignation> getDesignationByOrgCode(int orgCode, int locatedAtLevelCode) throws Exception {
		Query criteria = null;
		Query criteria1 = null;
		Session session = null;
		List<OrganizationDesignation> organizationDesignationList = null;

		char locatedAtLevel = 0;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("Select locatedAtLevel from OrgLocatedAtLevels where orgLocatedLevelCode=:locatedAtLevelCode");
			criteria.setParameter("locatedAtLevelCode", locatedAtLevelCode, Hibernate.INTEGER);
			locatedAtLevel = criteria.list().get(0).toString().charAt(0);
			criteria1 = session.createQuery("from OrganizationDesignation o where o.isactive=true and o.organization.orgCode=:orgCode  and (o.locatedAtLevel='A' or o.locatedAtLevel=:locatedAtLevel)");
			criteria1.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			criteria1.setParameter("locatedAtLevel", locatedAtLevel, Hibernate.CHARACTER);
			organizationDesignationList = criteria1.list();

		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationDesignationList;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrganizationDesignation> getDesignationByOrgCodeNonTop(int orgCode, int locatedAtLevelCode) throws Exception {

		Query criteria = null;
		Query criteria1 = null;
		Session session = null;

		List<OrganizationDesignation> organizationDesignationList = null;
		char locatedAtLevel = 0;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("Select locatedAtLevel from OrgLocatedAtLevels where orgLocatedLevelCode=:locatedAtLevelCode");
			criteria.setParameter("locatedAtLevelCode", locatedAtLevelCode, Hibernate.INTEGER);
			locatedAtLevel = criteria.list().get(0).toString().charAt(0);
			criteria1 = session.createQuery("from OrganizationDesignation o where o.isactive=true and o.organization.orgCode=:orgCode  and o.topDesignation=false and (o.locatedAtLevel='A' or o.locatedAtLevel=:locatedAtLevel)");
			criteria1.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			criteria1.setParameter("locatedAtLevel", locatedAtLevel, Hibernate.CHARACTER);
			organizationDesignationList = criteria1.list();
			// criteria1.list();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationDesignationList;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrganizationDesignation> getReportingDesignationForTopDesignation(int orgCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<OrganizationDesignation> organizationDesignationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrganizationDesignation o where o.isactive=true and o.organization.orgCode=:orgCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			organizationDesignationList = criteria.list();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationDesignationList;

	}

	@SuppressWarnings("unchecked")
	public List<Organization> getMinistryDetailList(String query) throws Exception {
		List<Organization> listOrganization = null;
		listOrganization = new ArrayList<Organization>();
		Query criteria = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery(query);
			listOrganization = criteria.list();
		} catch (Exception e) {

			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listOrganization;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getMinistryDetail(int orgCode) throws Exception {
		// TODO getMinistryDetail

		Query criteria = null;
		Session session = null;
		List<Organization> organizationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization where isactive=true and orgCode=:orgCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			organizationList = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationDetails(int orgCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<Organization> organizationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization where isactive=true and orgCode=:orgCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			organizationList = criteria.list();
		} catch (Exception e) {
			log.error("exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrgLocatedAtLevels> getOrganizationDetailsLowLevel(int orgCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<OrgLocatedAtLevels> organizationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrgLocatedAtLevels where isactive=true and orgLocatedLevelCode=:orgCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			organizationList = criteria.list();
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MinistryForm> getMinistryDetails(int orgCode) throws Exception {
		// TODO getMinistryDetail
		Query criteria = null;
		Session session = null;
		List<MinistryForm> ministryFormList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization where isactive=true and orgCode=:orgCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			ministryFormList = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ministryFormList;
	}

	@Override
	public boolean updateisActive(Organization organizationbeanisActive, OrganizationPK orgpk, Session session) throws Exception {
		// TODO updateisActive
		try {
			Organization organization = (Organization) session.get(Organization.class, orgpk);
			organization.setOrganizationPK(orgpk);
			organization.setIsactive(false);
			// session.update(organization);
			// Query query =
			// session.createQuery("update o Organization o set o.isactive=false where  o.organizationPK.orgCode=:orgCode and o.organizationPK.orgVersion=:orgVersion");
			Query query = session.createSQLQuery("update organization set isactive=false where  org_code=:orgCode and org_version=:orgVersion");
			query.setParameter("orgCode", orgpk.getOrgCode(), Hibernate.INTEGER);
			query.setParameter("orgVersion", orgpk.getOrgVersion(), Hibernate.INTEGER);
			query.executeUpdate();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
		return true;
	}

	@Override
	public void saveWithSession(Organization organization, OrganizationPK orgpk, int tmporgVersion, Session session) throws Exception {
		try {
			updateisActive(organization, orgpk, session);
			session.save(organization);
			// session.flush();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
	}

	@Override
	public int getMaxOrganizationVersion(int orgCode) throws Exception {
		Query criteria = null;
		Session session = null;
		int MaxVersionCode = 0;
		try {

			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select max(org_version) from organization where org_code=:orgCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			MaxVersionCode = Integer.parseInt(criteria.list().get(0).toString());

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return MaxVersionCode;
	}

	@Override
	public boolean update(MinistryForm ministryForm, OrganizationPK orgpk, Session session1) throws Exception {
		// TODO Auto-generated method stub
		try {
			Organization organizationbean = (Organization) session1.get(Organization.class, orgpk);
			organizationbean.setOrganizationPK(orgpk);
			organizationbean.setOrgName(ministryForm.getMinistryNamecr());
			organizationbean.setShortName(ministryForm.getShortministryName());
			session1.update(organizationbean);
			session1.flush();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrganization(Organization organization, Session session) throws Exception {
		try {
			session.save(organization);
			return true;
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
	}

	@Override
	public int saveOrgLocatedAtLevel(OrgLocatedAtLevels orgLocatedAtLevels, Session session) throws Exception {
		int orgLocatedAtLevelCode = 0;
		try {

			session.save(orgLocatedAtLevels);
			session.flush();
			session.refresh(orgLocatedAtLevels);

			orgLocatedAtLevelCode = orgLocatedAtLevels.getOrgLocatedLevelCode();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return orgLocatedAtLevelCode;
	}

	@Override
	public int saveOrgCoverage(OrganizationCoverage organizationCoverage, Session session) throws Exception {
		int coverageId = 0;
		try {
			session.save(organizationCoverage);
			session.flush();
			session.refresh(organizationCoverage);
			coverageId = organizationCoverage.getOrgCoverageId();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return coverageId;
	}

	@Override
	public int saveOrgCoverageDetail(OrganizationCoverageDetail organizationCoverageDetail, Session session) throws Exception {
		int coverageDetailId = 0;
		try {

			session.save(organizationCoverageDetail);
			session.flush();
			session.refresh(organizationCoverageDetail);
			coverageDetailId = organizationCoverageDetail.getOrgCoverageDetailId();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return coverageDetailId;
	}

	@Override
	public int getMaxOrgCode() throws Exception {
		int MaxCode = 0;
		Session session = null;
		Query query = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select COALESCE(max(org_code),1) from organization");
			list = query.list();

			MaxCode = Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxCode + 1;
	}

	@Override
	public int getMaxCoverageCode() throws Exception {
		int MaxCode = 0;
		Session session = null;
		Query query = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select COALESCE(max(coverage_code),1) from org_coverage_detail");
			list = query.list();
			MaxCode = Integer.parseInt(list.get(0).toString());

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxCode + 1;
	}

	@SuppressWarnings("unchecked")
	public List<Organization> getDepartmentList(String query) throws Exception {
		List<Organization> listOrganization = null;
		listOrganization = new ArrayList<Organization>();
		Session session = null;
		Query sqlQuery = null;
		//List list = null;
		try {
			session = sessionFactory.openSession();
			sqlQuery = session.createQuery(query);
			listOrganization = sqlQuery.list();
		} catch (HibernateException e) {

			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listOrganization;
	}

	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationList(String query) throws Exception {
		Session session = null;
		List<Organization> listOrganization = null;
		listOrganization = new ArrayList<Organization>();
		Query sqlQuery = null;
		try {
			session = sessionFactory.openSession();
			sqlQuery = session.createQuery(query);
			listOrganization = sqlQuery.list();
		} catch (HibernateException e) {

			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listOrganization;
	}

	@Override
	public boolean updateDepartment(DepartmentForm departmentForm, OrganizationPK orgpk, Session session1) throws Exception {
		// TODO Auto-generated method stub
		try {
			Organization organizationbean = (Organization) session1.get(Organization.class, orgpk);
			organizationbean.setOrganizationPK(orgpk);
			organizationbean.setOrgName(departmentForm.getDeptNamecr());
			organizationbean.setOrgNameLocal(departmentForm.getDeptNameLocal());
			organizationbean.setShortName(departmentForm.getShortDeptName());
			session1.update(organizationbean);
			session1.flush();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrgLocatedAtLevels> getOrgLocatedbyOrgCode(int orgCode) throws Exception {

		List<OrgLocatedAtLevels> orgLocatedList = new ArrayList<OrgLocatedAtLevels>();

		Query criteria = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrgLocatedAtLevels where isactive=true and olc=:orgCode order by orgLocatedLevelCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			orgLocatedList = criteria.list();
			for (OrgLocatedAtLevels orgLocate : orgLocatedList) {
				if (orgLocate.getLocatedAtLevel() == 0) {
					orgLocate.setOrgLevelSpecificName("Central");
				}
				if (orgLocate.getLocatedAtLevel() == 1) {
					orgLocate.setOrgLevelSpecificName("State");
				}
				if (orgLocate.getLocatedAtLevel() == 2) {
					orgLocate.setOrgLevelSpecificName("District");
				}
				if (orgLocate.getLocatedAtLevel() == 3) {
					orgLocate.setOrgLevelSpecificName("Sub District");
				}
				if (orgLocate.getLocatedAtLevel() == 4) {
					orgLocate.setOrgLevelSpecificName("Village");
				}
			}
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return orgLocatedList;
	}

	@Override
	public Integer getOrgLocatedbyOrgCodeforTopReporting(int orgCode, int locatedLevel) throws Exception {
		Session session = null;
		Integer parentlavelCode = 0;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select parent_org_located_level_code from org_located_at_levels where olc = :olc and org_located_level_code = :locatedLevel");
			query.setParameter("olc", orgCode, Hibernate.INTEGER);
			query.setParameter("locatedLevel", locatedLevel, Hibernate.INTEGER);
			Object obj = query.uniqueResult();
			if (obj != null) {
				parentlavelCode = (Integer) obj;
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return parentlavelCode;
	}

	@Override
	public boolean updateOrgType(OrganizationTypeForm organizationTypeForm, int orgTypeCode, Session session1) throws Exception {
		// TODO Auto-generated method stub
		try {
			OrganizationType organizationbean = (OrganizationType) session1.get(OrganizationType.class, orgTypeCode);
			organizationbean.setOrgType(organizationTypeForm.getOrgTypeName());
			session1.update(organizationbean);
			session1.flush();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateOrg(Organization organization, Session session) throws Exception {
		try {
			session.update(organization);
			// session.flush();
			/*
			 * if (session.contains(organization)) session.evict(organization);
			 */
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return false;
		}
		return true;
	}

	@Override
	public Organization getOrgnizationObject(int orgCode, Session session) throws Exception {
		Query query = null;
		Organization orgObj = null;
		try {
			query = session.createQuery("From Organization Where isactive=true AND organizationPK.orgCode = " + orgCode);
			@SuppressWarnings("unchecked")
			List<Organization> orgList = query.list();
			if (!orgList.isEmpty() && orgList.get(0) != null) {
				orgObj = (Organization) orgList.get(0);
			}
			return orgObj;

		} catch (HibernateException e) {
			log.error("Exception-->" + e);

		}
		return orgObj;

	}

	@Override
	public int getMaxRecords(String userQuery) throws Exception {
		int MaxCode = 0;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(userQuery);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxCode = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxCode;
	}

	public boolean saveDesignation(OrganizationDesignation designation, Session session) throws Exception {
		try {
			session.saveOrUpdate(designation);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	public boolean saveDesignationData(OrganizationDesignation designation, Session session) throws Exception {
		try {
			session.save(designation);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	@Override
	public int getRecordsforOrganization(int orgTypeCode) throws Exception {

		Query criteria = null;
		Session session = null;
		int recordCount = 0;
		try {

			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select count(*) from organization where isactive=true and org_type_code=:orgTypeCode");
			criteria.setParameter("orgTypeCode", orgTypeCode, Hibernate.INTEGER);
			recordCount = Integer.parseInt(criteria.list().get(0).toString());

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return recordCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationTypeForm> getOrgTypeDetails(int orgtypeCode) throws Exception {
		Query criteria = null;
		Session session = null;

		List<OrganizationTypeForm> organizationTypeForm = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Organization where isactive=true " + "and org.organizationType.orgTypeCode =:orgtypeCode").setParameter("orgtypeCode", orgtypeCode, Hibernate.INTEGER);
			organizationTypeForm = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationTypeForm;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OrgLocatedAtLevels> getOrgLocatedbyOrgCodemodify(int orgCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<OrgLocatedAtLevels> orgLocatedList = new ArrayList<OrgLocatedAtLevels>();
		try {
			/**
			 * 0005205: Bug in Set parent org unit changed by kirandep on
			 * 19/05/2015
			 */
			session = sessionFactory.openSession();
			/*
			 * Query modified for multiple types of offices at a given level by
			 * pooja on 22-06-2015
			 * Query Again modified for  Local body types 
			 * Pranav Tiwari on 11-11-2016
			 */
			query = session.createSQLQuery("select distinct located_at_level as locatedAtLevel,unit_level_name_english as unitLevelNameEnglish "
					+ "from org_located_at_levels ol,administration_unit_level al where al.admin_unit_level_code = ol.located_at_level and olc=:olc and ol.isactive "
					+ " union "
					+" select distinct located_at_level as locatedAtLevel,local_body_type_name as unitLevelNameEnglish "
					+" from org_located_at_levels ol,local_body_type lb where lb.local_body_type_code =abs(located_at_level)   and olc=:olc and ol.isactive and located_at_level <0 order by unitLevelNameEnglish");
			query.setParameter("olc", orgCode, Hibernate.INTEGER);
			    //query.addScalar("locatedAtLevel").addScalar("orgLevelSpecificName").addScalar("unit_level_name_english");
			query.addScalar("locatedAtLevel").addScalar("unitLevelNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(OrgLocatedAtLevels.class));
			orgLocatedList = query.list();
		
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return orgLocatedList;

	}
	
	
	/*
	 *Added By Pranav Tiwari on 15-12-2016
	 *  To get Org located at level with 
	 *  local body list 
	 *  for extend department
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<OrgLocatedAtLevels> getOrgLocatedbyOrgForExtendDepartment(boolean isCenterUser,int orgCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<OrgLocatedAtLevels> orgLocatedList = new ArrayList<OrgLocatedAtLevels>();
		try {
			
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder(" select ");
			queryBuild.append(" distinct located_at_level as locatedAtLevel,unit_level_name_english as unitLevelNameEnglish "
					+ "from org_located_at_levels ol,administration_unit_level al where al.admin_unit_level_code = ol.located_at_level and olc=:olc and ol.isactive ");
			if(!isCenterUser){
				queryBuild.append(" and admin_unit_level_code <> 1 ");
			}
			queryBuild.append(" union ");
			queryBuild.append(" select distinct located_at_level as locatedAtLevel,local_body_type_name as unitLevelNameEnglish "
					+" from org_located_at_levels ol,local_body_type lb where lb.local_body_type_code =abs(located_at_level)   and olc=:olc and ol.isactive and located_at_level <0 order by unitLevelNameEnglish ");
			 query = session.createSQLQuery(queryBuild.toString());
			/*query = session.createSQLQuery("select distinct located_at_level as locatedAtLevel,unit_level_name_english as unitLevelNameEnglish "
					+ "from org_located_at_levels ol,administration_unit_level al where al.admin_unit_level_code = ol.located_at_level and olc=:olc and ol.isactive and admin_unit_level_code <> 1 "
					+ " union "
					+" select distinct located_at_level as locatedAtLevel,local_body_type_name as unitLevelNameEnglish "
					+" from org_located_at_levels ol,local_body_type lb where lb.local_body_type_code =abs(located_at_level)   and olc=:olc and ol.isactive and located_at_level <0 order by unitLevelNameEnglish");*/
			query.setParameter("olc", orgCode, Hibernate.INTEGER);
			    //query.addScalar("locatedAtLevel").addScalar("orgLevelSpecificName").addScalar("unit_level_name_english");
			query.addScalar("locatedAtLevel").addScalar("unitLevelNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(OrgLocatedAtLevels.class));
			orgLocatedList = query.list();
			
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) 	 
				session.close();
		}
		return orgLocatedList;

	}

	@Override
	public int updateOrgLocatedAtLevel(OrgLocatedAtLevels orgLocatedAtLevels, Session session) throws Exception {
		// TODO Auto-generated method stub
		int orgLocatedAtLevelCode = 0;
		try {
			session.update(orgLocatedAtLevels);
			session.flush();
			session.refresh(orgLocatedAtLevels);
			orgLocatedAtLevelCode = orgLocatedAtLevels.getOrgLocatedLevelCode();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return orgLocatedAtLevelCode;
	}

	@Override
	public int updateOrgCoverage(OrganizationCoverage organizationCoverage, Session session) throws Exception {
		int coverageId = 0;
		try {
			session.update(organizationCoverage);
			session.flush();
			session.refresh(organizationCoverage);
			coverageId = organizationCoverage.getOrgCoverageId();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return coverageId;
	}

	@Override
	public int updateOrgCoverageDetail(OrganizationCoverageDetail organizationCoverageDetail, Session session) throws Exception {
		int coverageDetailId = 0;
		try {
			session.update(organizationCoverageDetail);
			session.flush();
			session.refresh(organizationCoverageDetail);
			coverageDetailId = organizationCoverageDetail.getOrgCoverageDetailId();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return coverageDetailId;
	}

	@Override
	public boolean updateOrganization(Organization organization, Session session) throws Exception {
		try {
			session.update(organization);
			return true;
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationCoverage> getOrgCoveragemodify(int orgLevelCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<OrganizationCoverage> orgLocatedList = new ArrayList<OrganizationCoverage>();
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrganizationCoverage where isactive=true " + "and org_located_level_code=" + orgLevelCode);
			orgLocatedList = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return orgLocatedList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationCoverageDetail> getOrgCoverageDetailmodify(int coverageDetailCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<OrganizationCoverageDetail> orgLocatedList = new ArrayList<OrganizationCoverageDetail>();
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrganizationCoverageDetail where isactive=true " + "and coverage_code=" + coverageDetailCode);

			orgLocatedList = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return orgLocatedList;
	}

	@Override
	public boolean deleteOrgType(OrganizationTypeForm organizationTypeForm, int orgTypeCode, Session session1) throws Exception {
		// TODO Auto-generated method stub
		try {
			OrganizationType organizationbean = (OrganizationType) session1.get(OrganizationType.class, orgTypeCode);
			organizationbean.setOrgType(organizationTypeForm.getOrgTypeName());
			session1.delete(organizationbean);
			session1.flush();
		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return false;
		}
		return true;
	}

	@Override
	public int orgVersionByCode(int orgCode, Session session) {
		Query query = null;
		int orgVersion = 0;
		try {
			query = session.createSQLQuery("Select org_version from organization where org_code=" + orgCode);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				orgVersion = (Integer) list.get(0);
			}
			return orgVersion;
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetOrganizationListFn> getOrgbyTypeCodeAtlevel(int orgTypeCode, Integer orgLevel, int stateCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<GetOrganizationListFn> organizationList = null;
		try {
			session = sessionFactory.openSession();
			if (stateCode > 0) {
				criteria = session.getNamedQuery("getOrgbyTypeCodelevelS");
				criteria.setParameter("orgTypeCode", orgTypeCode, Hibernate.INTEGER);
				criteria.setParameter("orgLevel", orgLevel, Hibernate.INTEGER);
				criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			} else if (stateCode == 0) {
				criteria = session.getNamedQuery("getOrgbyTypeCodelevelC");
				criteria.setParameter("orgTypeCode", orgTypeCode, Hibernate.INTEGER);
				criteria.setParameter("orgLevel", orgLevel, Hibernate.INTEGER);

			}
			organizationList = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetOrganizationAtLevels> getOrgAtLevel(int orgCode, Integer orgLevel) throws Exception {
		Query criteria = null;
		Session session = null;
		List<GetOrganizationAtLevels> orgLevlAtList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("getOrgAtLevels");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			criteria.setParameter("orgLevel", orgLevel, Hibernate.INTEGER);
			orgLevlAtList = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return orgLevlAtList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getSlc(int stateCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<State> slclist = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from State where statePK.stateCode= " + stateCode);

			slclist = criteria.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return slclist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationDesignation> getDesignationReporting(int orglevel, int olc) throws Exception {
		List<OrganizationDesignation> organization = null;
		Query query = null;

		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from OrganizationDesignation where olc=:selectedOlc and orgLocatedAtLevels.orgLocatedLevelCode=:selectedOrglavel and istopdesignation=false");
			query.setParameter("selectedOlc", olc, Hibernate.INTEGER);
			query.setParameter("selectedOrglavel", orglevel, Hibernate.INTEGER);
			organization = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organization;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LgdDesignation getDesignationReportingOrganizDetail(int orglevel, int olc) throws Exception {
		LgdDesignation lgdDesignation = new LgdDesignation();

		Query query, query1 = null;
		Session session = null;

		StringBuffer strbuff = new StringBuffer();

		try {
			session = sessionFactory.openSession();
			// query =
			// session.createQuery("from LgdDesignation where designationType = :desigType and olc = :selectedOlc and orgLocatedLevelCode = :selectedOrglavel and isActive is true order by isTopDesignation desc, designationCode");
			query = session.createQuery("from LgdDesignation where designationType = :desigType and olc = :selectedOlc and orgLocatedLevelCode = :selectedOrglavel and isActive is true order by isTopDesignation desc, sortOrder");
			query.setParameter("selectedOlc", olc, Hibernate.INTEGER);
			query.setParameter("selectedOrglavel", orglevel, Hibernate.INTEGER);
			query.setParameter("desigType", "D", Hibernate.STRING);
			List<LgdDesignation> organizationDesList = query.list();

			for (LgdDesignation designation : organizationDesList) {
				query1 = session.createQuery("from ReportingSetup where designationCode = :designationCode");
				//query1.setParameter("designationCode", designation.getDesignationCode(), Hibernate.INTEGER);
				List<ReportingSetup> rptorgList = query1.list();
				for (ReportingSetup repSetup : rptorgList) {
					strbuff.append(repSetup.getReportTo());
					strbuff.append("~");
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		lgdDesignation.setDesignationName(strbuff.toString());
		return lgdDesignation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LgdDesignation> getDesignationReportingOrganiz(int orglevel, int olc) throws Exception {
		List<LgdDesignation> organization = null;
		Query query = null;

		Session session = null;
		try {
			session = sessionFactory.openSession();
			// query =
			// session.createQuery("from LgdDesignation where designationType = :desigType and olc = :selectedOlc and orgLocatedLevelCode = :selectedOrglavel and isActive is true  order by isTopDesignation desc, designationCode, sortOrder");
			query = session.createQuery("from LgdDesignation where designationType = :desigType and olc = :selectedOlc and orgLocatedLevelCode = :selectedOrglavel and isActive is true  order by isTopDesignation desc,sortOrder");
			query.setParameter("selectedOlc", olc, Hibernate.INTEGER);
			query.setParameter("selectedOrglavel", orglevel, Hibernate.INTEGER);
			query.setParameter("desigType", "D", Hibernate.STRING);
			organization = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organization;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LgdDesignation> getTopDesignationReporting(int orglevel, int olc) throws Exception {
		List<LgdDesignation> organization = null;
		Query query = null;

		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from LgdDesignation where olc = :selectedOlc and orgLocatedLevelCode = :selectedOrglavel and isActive is true  order by isTopDesignation desc, designationCode");
			query.setParameter("selectedOlc", olc, Hibernate.INTEGER);
			query.setParameter("selectedOrglavel", orglevel, Hibernate.INTEGER);
			organization = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organization;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationDesignation> getReportTo(int orglevel, int olc) throws Exception {
		List<OrganizationDesignation> organization = null;
		Query query = null;

		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from OrganizationDesignation where olc=:selectedOlc and orgLocatedAtLevels.orgLocatedLevelCode=:selectedOrglavel");
			query.setParameter("selectedOlc", olc, Hibernate.INTEGER);
			query.setParameter("selectedOrglavel", orglevel, Hibernate.INTEGER);
			organization = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organization;
	}

	@SuppressWarnings("unchecked")
	public boolean getParentOrgLevel(int orglevel, int olc) throws Exception {
		List<OrgLocatedAtLevels> orglocatedAtLevels = null;
		Query query = null;
		Session session = null;
		boolean valReturn = false;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from OrgLocatedAtLevels where olc=:selectedOlc and orgLocatedLevelCode=:selectedOrglavel");
			query.setParameter("selectedOlc", olc, Hibernate.INTEGER);
			query.setParameter("selectedOrglavel", orglevel, Hibernate.INTEGER);
			orglocatedAtLevels = query.list();

			if (orglocatedAtLevels.size() > 0) {
				for (OrgLocatedAtLevels orgSetup : orglocatedAtLevels) {
					if (orglocatedAtLevels.size() == 1) {
						if (orgSetup.getParentOrgLocatedLevelCode() == null) {
							valReturn = true;
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return valReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationDesignation> getTopLevelReportTo(int orglevel, int olc) throws Exception {
		List<OrgLocatedAtLevels> orglocatedAtLevels = null;
		List<OrganizationDesignation> organization = null;
		Query query = null;
		Query query1 = null;
		int orgLevelReturn = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from OrgLocatedAtLevels where olc=:selectedOlc and orgLocatedLevelCode=:selectedOrglavel");
			query.setParameter("selectedOlc", olc, Hibernate.INTEGER);
			query.setParameter("selectedOrglavel", orglevel, Hibernate.INTEGER);
			orglocatedAtLevels = query.list();

			if (orglocatedAtLevels.size() > 0) {
				for (OrgLocatedAtLevels orgSetup : orglocatedAtLevels) {
					if (orglocatedAtLevels.size() == 1) {
						if (orgSetup.getParentOrgLocatedLevelCode() != null) {
							orgLevelReturn = orgSetup.getParentOrgLocatedLevelCode();
						}
					}
				}
			}
			query1 = session.createQuery("from OrganizationDesignation where olc=:selectedOlc and orgLocatedAtLevels.orgLocatedLevelCode=:selectedOrglavel");
			query1.setParameter("selectedOlc", olc, Hibernate.INTEGER);
			query1.setParameter("selectedOrglavel", orgLevelReturn, Hibernate.INTEGER);
			organization = query1.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organization;
	}

	public synchronized boolean updateOrganizationTypeDAO(OrganizationType organizationType, Session session) throws Exception {
		try {
			session.update(organizationType);
			return true;

		} catch (HibernateException e) {
			log.error("Exception-->" + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationDetailbystateCode(Integer stateCode) throws Exception {
		Query criteria = null;
		Session session = null;
		Integer slc = null;
		List<Organization> organizationList = null;
		try {

			slc = commonService.getSlc(stateCode);
			if (slc != null) {
				session = sessionFactory.openSession();
				criteria = session.createQuery("from Organization where isactive=true and slc=:slc order by orgName ");
				criteria.setParameter("slc", slc, Hibernate.INTEGER);
				organizationList = criteria.list();
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ExtendDepartment> checkExtendDetail(Integer orgCode) throws Exception {
		Query criteria = null;
		Session session = null;
		ExtendDepartment extendDepartment = null;
		String queryString = null;
		List<ExtendDepartment> extendDepartmentList = null;
		List<Object[]> temp = null;
		try {
			session = sessionFactory.openSession();
			queryString = "select org_coverage.org_coverage_entity_type,org_coverage.coverage_detail_code,org_located_at_levels.located_at_level,org_coverage.org_coverage_id from org_located_at_levels "
					+ "LEFT JOIN org_coverage ON org_located_at_levels.org_located_level_code = org_coverage.org_located_level_code and org_coverage.isactive = TRUE "
					+ "where org_located_at_levels.isactive=true and org_located_at_levels.located_at_level!='S' and org_located_at_levels.olc=" + orgCode;
			criteria = session.createSQLQuery(queryString);

			temp = criteria.list();

			if (temp.size() > 0) {
				extendDepartmentList = new ArrayList<ExtendDepartment>();

				for (int k = 0; k < temp.size(); k++) {
					Object obj[] = temp.get(k);
					extendDepartment = new ExtendDepartment();
					extendDepartment.setCoverage(obj[0].toString().charAt(0));
					extendDepartment.setCoverageDetailCode(Integer.parseInt(obj[1].toString()));
					extendDepartment.setLocatedAtLevel(obj[2].toString().charAt(0));
					extendDepartment.setOrgCoverageId(Integer.parseInt(obj[3].toString()));
					extendDepartmentList.add(k, extendDepartment);
				}
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return extendDepartmentList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ExtendDepartment getExtendBasicDetails(int olc, char level) throws Exception {
		Query criteria = null;
		Session session = null;
		ExtendDepartment extendDepartment = null;

		List<OrganizationCoverage> orgCoverageList = new ArrayList<OrganizationCoverage>();
		List<OrgLocatedAtLevels> orgLocatedList = new ArrayList<OrgLocatedAtLevels>();
		List<OrganizationCoverageDetail> orgCoverageDetailList = new ArrayList<OrganizationCoverageDetail>();
		List<Integer> entityDetail = new ArrayList<Integer>();
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrgLocatedAtLevels where isactive=true and olc=:olc and locatedAtLevel=:locatedAtLevel").setParameter("olc", olc).setParameter("locatedAtLevel", level);
			orgLocatedList = criteria.list();
			session.clear();
			if (orgLocatedList.size() > 0) {
				extendDepartment = new ExtendDepartment();
				extendDepartment.setOrgLevelSpecificName(orgLocatedList.get(0).getOrgLevelSpecificName());
				extendDepartment.setOrgLevelSpecificNameLocal(orgLocatedList.get(0).getOrgLevelSpecificNameLocal());
				extendDepartment.setOrgLevelSpecificShortName(orgLocatedList.get(0).getOrgLevelSpecificShortName());
				extendDepartment.setOrgLocatedLevelCode(orgLocatedList.get(0).getOrgLocatedLevelCode());
				criteria = session.createQuery("from OrganizationCoverage where isactive=true and orgLocatedLevelCode=:orgLocatedLevelCode and orgCoverageEntityType=:orgCoverageEntityType")
						.setParameter("orgLocatedLevelCode", orgLocatedList.get(0).getOrgLocatedLevelCode()).setParameter("orgCoverageEntityType", orgLocatedList.get(0).getLocatedAtLevel());
				orgCoverageList = criteria.list();
				session.clear();
				if (orgCoverageList.size() > 0) {
					char coverage = orgCoverageList.get(0).getCoverage();
					extendDepartment.setCoverage(coverage);
					extendDepartment.setOrgCoverageId(orgCoverageList.get(0).getOrgCoverageId());
					extendDepartment.setCoverageDetailCode(orgCoverageList.get(0).getCoverageDetailCode());
					// extendDepartment.setOrgCoverageEntityType(orgCoverageList.get(0).getOrgCoverageEntityType());
					if (coverage != 'F') {
						criteria = session.createQuery("from OrganizationCoverageDetail where isactive=true and coverageCode=:coverageCode").setParameter("coverageCode", orgCoverageList.get(0).getCoverageDetailCode());
						orgCoverageDetailList = criteria.list();
						int i = 0;
						if (orgCoverageDetailList.size() > 0) {
							for (OrganizationCoverageDetail element : orgCoverageDetailList) {
								entityDetail.add(i, element.getEntityCode());
								i++;
							}

							extendDepartment.setEntityCode(entityDetail);
						}
					}
				}

			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return extendDepartment;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<District> getDistrictListforExtendDep(int stateCode, String dlc) throws Exception {
		List<District> distList = null;
		List<State> stateList = null;
		Integer slc = null;
		Query query = null;
		Session session = null;
		try {
			distList = new ArrayList<District>();
			stateList = new ArrayList<State>();
			session = sessionFactory.openSession();
			stateList = session.createQuery("from State where isactive=true and stateCode=" + stateCode).list();
			slc = stateList.get(0).getSlc();
			query = session.createQuery("from District d where d.slc=" + slc + " and d.isactive=true and dlc not in(" + dlc + ") order by districtCode");

			distList = query.list();
			for (int i = 0; i < distList.size(); i++) {
				distList.get(i).getDistrictNameEnglish();
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String checkExtendDetailforDelete(Integer orgCode, char level) throws Exception {
		Query criteria = null;
		Session session = null;
		//ExtendDepartment extendDepartment = null;
		String queryString = null;
		//List<ExtendDepartment> extendDepartmentList = null;
		List<Object[]> temp = null;
		String dcoverageCode = "";
		try {
			session = sessionFactory.openSession();
			queryString = "select org_coverage_detail.coverage_code from org_located_at_levels INNER JOIN org_coverage on org_located_at_levels.org_located_level_code=org_coverage.org_located_level_code"
					+ " LEFT JOIN org_coverage_detail on org_coverage.coverage_detail_code=org_coverage_detail.coverage_code  where org_located_at_levels.isactive=true and org_coverage.isactive=true and"
					+ " org_coverage_detail.isactive=true and org_located_at_levels.olc=" + orgCode + " and org_located_at_levels.located_at_level='" + level + "'";
			criteria = session.createSQLQuery(queryString);

			temp = criteria.list();

			if (temp.size() > 0) {

				for (int k = 0; k < temp.size(); k++) {
					dcoverageCode += temp.get(k) + ",";

				}

				if (dcoverageCode.length() > 0) {
					dcoverageCode = dcoverageCode.substring(0, dcoverageCode.lastIndexOf(","));
				}
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return dcoverageCode;
	}

	@Override
	public boolean checkExistNameDeparment(Integer stateCode, char level, String deptName) throws Exception {
		Query criteria = null;
		Session session = null;
		Integer slc = null;
		String queryString = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			slc = commonService.getSlc(stateCode);
			session = sessionFactory.openSession();
			if (level == 'S') {
				queryString = "select olc from organization where slc=" + slc + "  and org_name like '" + deptName + "'";
			} else {
				queryString = "select * from org_located_at_levels where olc in(select olc from organization where slc=" + slc + ") and located_at_level='" + CommonUtil.setCategoryLevel(level) + "' and org_level_specific_name like '" + deptName
						+ "'";
			}
			criteria = session.createSQLQuery(queryString);
			list = criteria.list();
			if (list.size() > 1) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean checkExistNameDeparmentModify(Integer stateCode, char level, String deptName, String deptNamech) throws Exception {
		Query criteria = null;
		Session session = null;
		Integer slc = null;
		String queryString = null;
		@SuppressWarnings("rawtypes")
		List list = null;

		try {
			if (!deptName.equalsIgnoreCase(deptNamech)) {
				slc = commonService.getSlc(stateCode);
				session = sessionFactory.openSession();

				if (level == 'S') {
					queryString = "select olc from organization where slc=" + slc + "  and Upper(org_name) like Upper('" + deptNamech + "')";
				} else if (level == 'C') {
					queryString = "select olc from organization where (slc=" + slc + " or slc ISNULL)  and Upper(org_name) like Upper('" + deptNamech + "')";
				} else if (level == 'A') {
					queryString = "select * from org_located_at_levels where olc in(select olc from organization where slc=" + slc + ") and located_at_level > 5 " + "and Upper(org_level_specific_name) like upper( '" + deptNamech
							+ "') and Upper(org_level_specific_name) not like upper( '" + deptName + "') ";

				} else {
					queryString = "select * from org_located_at_levels where olc in(select olc from organization where slc=" + slc + ") and located_at_level='" + CommonUtil.setCategoryLevel(level)
							+ "' and Upper(org_level_specific_name) like upper( '" + deptNamech + "') and Upper(org_level_specific_name) not like upper( '" + deptName + "') ";

				}
				criteria = session.createSQLQuery(queryString);
				list = criteria.list();
				if (list.size() > 0) {
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean setParentCodeforExtendDepartment(int olc) throws Exception {
		Query criteria = null;
		Session session = null;
		Transaction tx = null;

		List<OrgLocatedAtLevels> orgLocatedListout = new ArrayList<OrgLocatedAtLevels>();

		List<OrgLocatedAtLevels> orgLocatedListupdate = new ArrayList<OrgLocatedAtLevels>();
		Integer pdistrict = null, psubdistric = null, pblock = null, pvillage = null;
		String levels = "";

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			criteria = session.createQuery("from OrgLocatedAtLevels where isactive=true and olc=:olc").setParameter("olc", olc);

			orgLocatedListout = criteria.list();

			orgLocatedListupdate = orgLocatedListout;
			session.clear();
			Map<String, Integer> parentMp = new HashMap<String, Integer>();

			if (orgLocatedListout.size() > 0) {
				for (OrgLocatedAtLevels element : orgLocatedListout) {
					parentMp.put(String.valueOf(element.getLocatedAtLevel()), element.getOrgLocatedLevelCode());
					levels += element.getLocatedAtLevel() + ",";

				}

				if (levels.indexOf('V') > 0) {

					if (levels.indexOf('T') > 0) {
						pvillage = parentMp.get("T");
					} else if (levels.indexOf('D') > 0) {
						pvillage = parentMp.get("D");
					} else {
						pvillage = parentMp.get("S");
					}

				}

				if (levels.indexOf('T') > 0) {

					if (levels.indexOf('D') > 0) {
						psubdistric = parentMp.get("D");

					} else {

						psubdistric = parentMp.get("S");

					}
				}
				if (levels.indexOf('B') > 0) {
					if (levels.indexOf('D') > 0) {
						pblock = parentMp.get("D");

					} else {

						pblock = parentMp.get("S");

					}

				}
				if (levels.indexOf('D') > 0) {

					pdistrict = parentMp.get("S");

				}

				for (OrgLocatedAtLevels element : orgLocatedListupdate) {
					if (element.getLocatedAtLevel() == 'D' && pdistrict != null) {
						OrgLocatedAtLevels orgLocated = (OrgLocatedAtLevels) session.load(OrgLocatedAtLevels.class, element.getOrgLocatedLevelCode());

						orgLocated.setParentOrgLocatedLevelCode(pdistrict);
						session.update(orgLocated);

					} else if (element.getLocatedAtLevel() == 'T' && psubdistric != null) {
						OrgLocatedAtLevels orgLocated = (OrgLocatedAtLevels) session.load(OrgLocatedAtLevels.class, element.getOrgLocatedLevelCode());

						orgLocated.setParentOrgLocatedLevelCode(psubdistric);
						session.update(orgLocated);

					} else if (element.getLocatedAtLevel() == 'V' && pvillage != null) {
						OrgLocatedAtLevels orgLocated = (OrgLocatedAtLevels) session.load(OrgLocatedAtLevels.class, element.getOrgLocatedLevelCode());

						orgLocated.setParentOrgLocatedLevelCode(pvillage);
						session.update(orgLocated);

					} else if (element.getLocatedAtLevel() == 'B' && pblock != null) {
						OrgLocatedAtLevels orgLocated = (OrgLocatedAtLevels) session.load(OrgLocatedAtLevels.class, element.getOrgLocatedLevelCode());

						orgLocated.setParentOrgLocatedLevelCode(pblock);
						session.update(orgLocated);

					}

				}

			}
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			log.error("Exception-->" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<OrgLocatedAtLevels> getDepartMentLowLevelDetails(Integer orgUnitCode) throws Exception {
		List<OrgLocatedAtLevels> orgLocatedAtLevelsList = null;
		Session session=null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select org_located_level_code from org_units where org_unit_code=:orgUnitCode").setParameter("orgUnitCode", orgUnitCode);
			Integer orgLocatedLevelCode = Integer.parseInt(query.uniqueResult().toString());

			Query hqlquery = session.createQuery("from OrgLocatedAtLevels where orgLocatedLevelCode=:orgLocatedLevelCode").setParameter("orgLocatedLevelCode", orgLocatedLevelCode);
			orgLocatedAtLevelsList = hqlquery.list();

		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return orgLocatedAtLevelsList;
	}

	@SuppressWarnings("unchecked")
	public boolean checktOrganizationTypeNameExist(String OrgTypeName) throws Exception {
		List<OrganizationType> orgTypeList = null;
		Session session = null;

		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from OrganizationType where orgType=:OrgTypeName").setParameter("OrgTypeName", OrgTypeName);
			orgTypeList = query.list();
			if (orgTypeList.size() > 1)
				return true;
			else
				return false;

		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<DistrictLineDepartment> getLandRegionLineDepartmentListforOrganization(int entityCode, char level, List<Organization> orgList) throws Exception {
		Session session = null;
		Query query = null;
		@SuppressWarnings("rawtypes")
		List list = null;
		String olc = "";

		try {
			for (Organization element : orgList) {
				olc = olc + element.getOlc().toString() + ",";
			}
			olc = olc.substring(0, olc.lastIndexOf(","));

			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLandRegionLineDepartmentDetails").setParameter("entityCode", entityCode).setParameter("level", CommonUtil.setCategoryLevel(level)).setParameter("olc", olc);
			list = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DistrictLineDepartment> getLandRegionLineDepartmentDetailforOrganization(Integer orgUnitCode) throws Exception {
		Session session = null;
		Query query = null;
		@SuppressWarnings("rawtypes")
		List list = null;

		try {

			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLandRegionLineDepartmentDetailsView").setParameter("orgUnitCode", orgUnitCode);

			list = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkExistingDepartment(String deptName, char orgLevel) {
		List<OrganizationType> orgTypeList = null;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Organization where orgName=:orgName and orgLevel=:orgLevel").setParameter("orgName", deptName).setParameter("orgLevel", orgLevel);
			orgTypeList = query.list();
			if (!orgTypeList.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean checkExistingOrganizationByLevel(String orgName, char orgLevel) {
		Query criteria = null;
		Session session = null;
		String queryString = null;
		try {
			session = sessionFactory.openSession();
			if (orgLevel == 'S') {
				queryString = "select a.orgLocatedLevelCode from OrgLocatedAtLevels a where a.orgLevelSpecificName=:orgLevelSpecificName and a.locatedAtLevel=:locatedAtLevel and a.isactive=true";
			}
			criteria = session.createQuery(queryString);
			criteria.setParameter("orgLevelSpecificName", orgName);
			criteria.setParameter("locatedAtLevel", orgLevel);
			if (!criteria.list().isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/*
	 * @modified by Maneesh on 24-05-2013
	 */
	@SuppressWarnings("unchecked")
	public List<DistrictLineDepartment> getLandRegionLineDepartmentList(Integer entityCode, Integer deptCode, char level) throws Exception {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			Query query = null;
			if (deptCode == null) {
				query = session.getNamedQuery("getNewLineDepartmentDetails").setParameter("entityCode", entityCode).setParameter("level", CommonUtil.setCategoryLevel(level));
			} else {
				query = session.getNamedQuery("getNewLineDepartmentDetail").setParameter("entityCode", entityCode).setParameter("level", CommonUtil.setCategoryLevel(level)).setParameter("orgLocatedLevelCode", deptCode);
			}

			list = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	public String getOrgTypeName(Integer orgTypeCode) throws Exception {
		Query criteria = null;
		Session session = null;
		String orgTypeName = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrganizationType where orgTypeCode:orgTypeCode");
			criteria.setParameter("orgTypeCode", orgTypeCode, Hibernate.INTEGER);
			orgTypeName = criteria.uniqueResult().toString();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return orgTypeName;
		// return
		// sessionFactory.getCurrentSession().createQuery("from Organization org where isactive=true and org.parentorgcode="+
		// orgCode).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PushOrgDepToPes> saveOrgDeptIntoPES(int entityCode, Session session) throws Exception {
		Query query = null;
		List<PushOrgDepToPes> valueReturn = null;
		try {
			query = session.getNamedQuery("pushOrgDepttoPES").setParameter("EntityCode", entityCode, Hibernate.INTEGER);

			valueReturn = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return valueReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getDeptAdminUnits(int slc, int type) throws Exception {
		Session session = null;
		List<DeptAdminUnit> deptadminUnits = null;
		/* query modified for center and state level by sunita on 10-07-2015 */

		try {
			
			session = sessionFactory.openSession();
			
			StringBuilder strQuery = new StringBuilder(" select admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,unit_level_name_local as adminLevelNameLocal,");
			strQuery.append("case when (admin_unit_level_code>");
			if(slc==0){
				strQuery.append("=");
			}
			strQuery.append("0 and  admin_unit_level_code<=5 )then 'L' when  admin_unit_level_code>5 then 'A' else 'C' ");
			strQuery.append("end as unitLevelType from administration_unit_level where ");
			
			if (type == Integer.parseInt(DepartmentConstent.CREATE_ADMINISTRATIVE_UNIT.toString())) {
				if (slc == 0) {
					strQuery.append("  slc=:slc");
				}else{
					strQuery.append(" admin_unit_level_code>0 and  admin_unit_level_code<=5 or slc=:slc");
				}
			}else if (type == Integer.parseInt(DepartmentConstent.MANAGE_ADMINISTRATIVE_UNIT.toString())) {
				if (slc == 0) {
					strQuery.append("  slc=:slc and mdds_Entity=false");
				} else {
					strQuery.append("  slc=:slc  ");
				}
			}
			if(type==2 && slc==0) {
				strQuery.append(" and admin_unit_level_code not in(-100) ");
			}
			strQuery.append(" and isactive=true order by unit_level_name_english");
			
			deptadminUnits = (List<DeptAdminUnit>) session.createSQLQuery(strQuery.toString()).addScalar("adminUnitCode")
			.addScalar("adminLevelNameEng").addScalar("adminLevelNameLocal").addScalar("unitLevelType")
			.setParameter("slc", slc)
			.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class)).list();
			
			
			
			
			
				/*if (type == Integer.parseInt(DepartmentConstent.MANAGE_ADMINISTRATIVE_UNIT.toString())) {
					if (slc == 0) {
						stringBuilder.append(" where (du.slc=0 and revenueEntity=false  ) ");
					} else {
						stringBuilder.append(" where (du.slc=:sCode)");
					}
				}

				stringBuilder.append(" and isactive=true order by adminLevelNameEng");*/
				
				
				
				
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return deptadminUnits;
	}

	@Override
	public boolean saveDeptAdmitUnit(DeptAdminUnit deptAdminUnit) throws Exception {
		Session session = null;
		Transaction tx = null;
		boolean success = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Timestamp timestamp = CurrentDateTime.getCurrentTimestamp();
			deptAdminUnit.setCreatedon(timestamp);
			if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'D') {
				deptAdminUnit.setIsactive(false);
				deptAdminUnit.setIspublish('D');
				deptAdminUnit.setRevenueEntity(false);
			} else if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'P') {
				deptAdminUnit.setIsactive(true);
				deptAdminUnit.setIspublish('P');
				deptAdminUnit.setRevenueEntity(false);
			}
			else if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'D') {
				deptAdminUnit.setIsactive(true);
				deptAdminUnit.setIspublish('P');
				deptAdminUnit.setRevenueEntity(false);
			}
			else if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'R') {
				deptAdminUnit.setIsactive(false);
				deptAdminUnit.setRevenueEntity(true);
				//deptAdminUnit.setRevenuelevel(true);
				deptAdminUnit.setIspublish('D');
			} else if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'L') {
				deptAdminUnit.setIsactive(true);
				deptAdminUnit.setRevenueEntity(true);
				//deptAdminUnit.setRevenuelevel(true);
				deptAdminUnit.setIspublish('P');
			}
			else if (deptAdminUnit.getButtonClicked() != null && deptAdminUnit.getButtonClicked() == 'R') {
				deptAdminUnit.setIsactive(true);
				deptAdminUnit.setRevenueEntity(true);
				//deptAdminUnit.setRevenuelevel(true);
				deptAdminUnit.setIspublish('P');
			}
			session.save(deptAdminUnit);
			tx.commit();
			success = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return success;
	}

	@Override
	public boolean adminUnitOradminChildExist(int slc, String deptAdminUnitName, int adminUnitCode, char choiceType, int parentCode) {
		Session session = null;
		String sql = null;
		boolean success = true;
		if (choiceType == 'A') {
			/**
			 * and isactive=true Removed from query for draft mode. By kirandeep
			 * on 12/05/2015
			 */

			sql = "select case when count(Admin_unit_level_code) > 0 then false else true end from administration_unit_level du where (du.slc=:slc or du.slc=0) and  UPPER(TRIM(du.unit_level_name_english)) =:dtUnitName and isactive" ;
		} else if (choiceType == 'E') {
			/**
			 * and isactive=true Removed from query for draft mode. By kirandeep
			 * on 12/05/2015
			 */
			sql = "select case when count(admin_unit_entity_code) > 0 then false else true end from administration_unit_entity de where de.slc=:slc and de.admin_unit_level_code=:adminUnitCode and de.parent_unit_entity_code=:parentCode  and  UPPER(TRIM(de.admin_entity_name_english)) =:dtUnitName  and isactive";
		} else if (choiceType == 'C') {
			sql = "select case when count(1)>0 then false else (select case when count(1)>0 then false else true end from org_located_at_levels ol where ol.located_at_level= :adminUnitCode)   end as childExist from administration_unit_entity ae where ae.admin_unit_level_code =:adminUnitCode  and isactive";
		} else if (choiceType == 'D') {
			sql = "select case when count(admin_unit_entity_code) > 0 then false else true end from administration_unit_entity de where de.slc=:slc and isactive=true and de.parent_unit_entity_code=:adminUnitCode  and isactive";
		}
		try {
			session = sessionFactory.openSession();
			Query criteria = session.createSQLQuery(sql);
			
			if (choiceType == 'A') {
				criteria.setParameter("dtUnitName", deptAdminUnitName.trim().toUpperCase());
				criteria.setParameter("slc", slc);
			} else if (choiceType == 'E') {
				criteria.setParameter("dtUnitName", deptAdminUnitName.trim().toUpperCase());
				criteria.setParameter("adminUnitCode", adminUnitCode);
				criteria.setParameter("parentCode", parentCode);
				criteria.setParameter("slc", slc);
			} else  if(choiceType=='C'){
				criteria.setParameter("adminUnitCode", adminUnitCode);
			}else{
				criteria.setParameter("adminUnitCode", adminUnitCode);
				criteria.setParameter("slc", slc);
			}
			success = (Boolean) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return success;
	}

	/*modify by deepti add overlapUnit on 09-05-2016*/
	@Override
	public DepartmentAdminForm getDeptAdminUnitDetail(int adminUnitCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		String sql = null;
		sql = "select au.parent_category as parentCategory,au.overlapping_unit as overlapUnit ,au.Admin_unit_level_code as adminUnitCode,au.unit_level_name_english as adminLevelNameEnglish," + 
				"au.unit_level_name_local as adminLevelNameLocal,au.createdby as createdby," + 
				"case when au.parent_category='G' then (select nomenclature_english from local_body_setup lbs inner join local_body_tiers_setup lts " + 
				"on lbs.local_body_setup_code=lts.local_body_setup_code and lbs.local_body_setup_version=lts.local_body_setup_version " + 
				"where lbs.slc=au.slc and lbs.isactive and lts.isactive and lts.local_body_type_code=au.Parent_admin_unit_level_code)else" + 
				" aup.unit_level_name_english end as parentAdminLevelName," + 
				"au.Parent_admin_unit_level_code as parentAdminCode,au.Admin_unit_level_code as adminUnitLevelCode from " + 
				"administration_unit_level aup,administration_unit_level au where au.parent_admin_unit_level_code=aup.admin_unit_level_code   " + 
				"and au.admin_unit_level_code=:adminUnitCode ";
		DepartmentAdminForm deptAdminUnit = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery(sql);
			query.setParameter("adminUnitCode", adminUnitCode, Hibernate.INTEGER);
			query.addScalar("overlapUnit").addScalar("adminUnitCode").addScalar("adminLevelNameEnglish").addScalar("adminLevelNameLocal").addScalar("createdby")
			.addScalar("parentAdminLevelName").addScalar("parentAdminCode").addScalar("parentCategory").addScalar("adminUnitLevelCode");
			query.setResultTransformer(Transformers.aliasToBean(DepartmentAdminForm.class));
			deptAdminUnit = (DepartmentAdminForm) query.uniqueResult();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnit;
	}

	@Override
	public DepartmentAdminForm getDeptAdminEntityDetail(int adminEntityCode) throws Exception {
		Session session = null;
		DepartmentAdminForm departmentAdminForm = null;
		Integer unitLevelCode = null;
		Character parentCategory=null;
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "select ul.parent_admin_unit_level_code as parentAdminCode,ul.parent_category as parentCategory from administration_unit_entity ue,administration_unit_level ul where " + "ue.admin_unit_level_code=ul.admin_unit_level_code and ue.admin_unit_entity_code=:adminEntityCode";
			SQLQuery query = session.createSQLQuery(queryBuilder);
			query.setParameter("adminEntityCode", adminEntityCode);
			query.addScalar("parentAdminCode").addScalar("parentCategory");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			List<DeptAdminUnit> deptAdminUnitList = (List<DeptAdminUnit>) query.list();
			if(deptAdminUnitList!=null && !deptAdminUnitList.isEmpty()){
				DeptAdminUnit deptAdminUnit=deptAdminUnitList.get(0);
				parentCategory=deptAdminUnit.getParentCategory();
				unitLevelCode=deptAdminUnit.getParentAdminCode();
			}
			
			
			StringBuffer builder = new StringBuffer("select distinct ae.admin_unit_entity_code as adminUnitCode,ae.admin_entity_name_english as " + "adminLevelNameEnglish,ae.admin_unit_level_code as adminUnitLevelCode,ae.admin_entity_name_local as"
					+ " adminLevelNameLocal,ae.parent_unit_entity_code as parentAdminCode,ae.parent_category as parentCategory,CAST(COALESCE(aucount.childcount,0,aucount.childcount) AS integer) as  childRecords,");
			/*Modified by pooja on 19-10-2015 */
			if(parentCategory!='G'){
				if(unitLevelCode == 0)
					builder.append("CAST('Center' as character varying) as parentAdminLevelName from administration_unit_entity aep,administration_unit_entity ae left outer join (select parent_unit_entity_code,count(1) as childcount " +
							"from administration_unit_entity where parent_unit_entity_code=0 group by  parent_unit_entity_code) aucount on aucount.parent_unit_entity_code=0  where aep.parent_unit_entity_code=0 and aep.isactive and ae.admin_unit_entity_code=:adminUnitCode");
				else if (unitLevelCode == 1)
					builder.append("st.state_name_english as parentAdminLevelName from state st," + "administration_unit_entity ae left outer join (select parent_unit_entity_code,count(1) as childcount from administration_unit_entity group "
							+ "by  parent_unit_entity_code) aucount on ae.admin_unit_entity_code=aucount.parent_unit_entity_code  where ae.parent_unit_entity_code=st.state_code" + " and st.isactive and ae.admin_unit_entity_code=:adminUnitCode");
				else if (unitLevelCode == 2)
					builder.append("dis.district_name_english as parentAdminLevelName from district dis," + "administration_unit_entity ae left outer join (select parent_unit_entity_code,count(1) as childcount from administration_unit_entity group "
							+ "by  parent_unit_entity_code) aucount on ae.admin_unit_entity_code=aucount.parent_unit_entity_code  where ae.parent_unit_entity_code=dis.district_code" + " and dis.isactive and ae.admin_unit_entity_code=:adminUnitCode");
				else if (unitLevelCode == 3)
					builder.append("sub.subdistrict_name_english as parentAdminLevelName from subdistrict sub,"
							+ "administration_unit_entity ae left outer join (select parent_unit_entity_code,count(1) as childcount from administration_unit_entity group "
							+ "by  parent_unit_entity_code) aucount on ae.admin_unit_entity_code=aucount.parent_unit_entity_code  where ae.parent_unit_entity_code=sub.subdistrict_code"
							+ " and sub.isactive and  ae.admin_unit_entity_code=:adminUnitCode  ");
				else if (unitLevelCode == 4)
					builder.append("vil.village_name_english as parentAdminLevelName from village vil," + "administration_unit_entity ae left outer join (select parent_unit_entity_code,count(1) as childcount from administration_unit_entity group "
							+ "by  parent_unit_entity_code) aucount on ae.admin_unit_entity_code=aucount.parent_unit_entity_code  where ae.parent_unit_entity_code=vil.village_code" + " and vil.isactive and ae.admin_unit_entity_code=:adminUnitCode");
				else if (unitLevelCode == 5)
					builder.append("bl.block_name_english as parentAdminLevelName from block bl," + "administration_unit_entity ae left outer join (select parent_unit_entity_code,count(1) as childcount from administration_unit_entity group "
							+ "by  parent_unit_entity_code) aucount on ae.admin_unit_entity_code=aucount.parent_unit_entity_code  where ae.parent_unit_entity_code=bl.block_code" + " and bl.isactive and ae.admin_unit_entity_code=:adminUnitCode");
			
				else
					builder.append("aep.admin_entity_name_english as parentAdminLevelName from administration_unit_entity aep,"
							+ "administration_unit_entity ae left outer join (select parent_unit_entity_code,count(1) as childcount from administration_unit_entity group "
							+ "by  parent_unit_entity_code) aucount on ae.admin_unit_entity_code=aucount.parent_unit_entity_code  where ae.parent_unit_entity_code=aep.admin_unit_entity_code"
							+ " and aep.isactive and ae.admin_unit_entity_code=:adminUnitCode");
			}else{
				
						builder.append("l.local_body_name_english as parentAdminLevelName from localbody l," + "administration_unit_entity ae left outer join (select parent_unit_entity_code,count(1) as childcount from administration_unit_entity group "
								+ "by  parent_unit_entity_code) aucount on ae.admin_unit_entity_code=aucount.parent_unit_entity_code  where ae.parent_unit_entity_code=l.local_body_code" + " and l.isactive and ae.admin_unit_entity_code=:adminUnitCode");
					
			}
			
			query = session.createSQLQuery(builder.toString());
			query.setParameter("adminUnitCode", adminEntityCode, Hibernate.INTEGER);
			query.addScalar("adminUnitCode").addScalar("adminLevelNameEnglish").addScalar("adminLevelNameLocal").addScalar("parentAdminLevelName")
			.addScalar("childRecords").addScalar("parentAdminCode").addScalar("parentCategory").addScalar("adminUnitLevelCode");
			query.setResultTransformer(Transformers.aliasToBean(DepartmentAdminForm.class));
			departmentAdminForm = (DepartmentAdminForm) query.uniqueResult();
		} catch (Exception e) {
			log.debug("Exception " + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return departmentAdminForm;
	}

	@Override
	public boolean deleteAdminUnitEntity(Integer slc, Integer adminUnitCode, int entityType) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		String coverageCode = Integer.toString(adminUnitCode);
		String stateCode = Integer.toString(slc);
		String sql = null;
		boolean status = false;
		Integer adminCoverageCode = Integer.valueOf(0);
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			if (entityType == 0)
				sql = "delete from Administration_Unit_Level au where  au.Admin_unit_level_code= " + coverageCode + " and au.Slc='" + stateCode + "' and isactive=true ";
			else {
				sql = "delete from administration_unit_entity ae where  ae.admin_unit_entity_code= " + coverageCode + " and ae.Slc='" + stateCode + "' and isactive=true ";
				query = session.createSQLQuery("select admin_coverage_code from administration_unit_entity where admin_unit_entity_code=:code");
				query.setParameter("code", adminUnitCode);
				adminCoverageCode = (Integer) query.uniqueResult();

			}
			query = session.createSQLQuery(sql);
			query.executeUpdate();
			if (adminCoverageCode > 0) {
				query = session.createSQLQuery("delete from administrative_entity_coverage ac where ac.admin_coverage_code =:adminCoverageCode");
				query.setParameter("adminCoverageCode", adminCoverageCode);
				query.executeUpdate();
			}
			txn.commit();
			status = true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	@Override
	public boolean saveDeptAdmitEntity(DeptAdminUnitEntity deptAdminEntity, Session session) throws Exception {

		boolean status = false;
		try {
			Timestamp timestamp = CurrentDateTime.getCurrentTimestamp();
			deptAdminEntity.setCreatedon(timestamp);

			if (deptAdminEntity.getPublishOrSave().equals('D')) {
				deptAdminEntity.setIsPublish('D');
				deptAdminEntity.setIsactive(false);
			}
			if (deptAdminEntity.getPublishOrSave().equals('P')) {
				deptAdminEntity.setIsPublish('P');
				deptAdminEntity.setIsactive(true);
			}
			session.saveOrUpdate(deptAdminEntity);
			status = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnitEntity> getDeptAdminEntity(int slc) throws Exception {
		Query query = null;
		Session session = null;
		List<DeptAdminUnitEntity> deptAdminEntity = new ArrayList<DeptAdminUnitEntity>();
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from DeptAdminUnitEntity de where de.slc=:sCode and isactive=true order by adminUnitEntityCode");
			query.setParameter("sCode", slc);
			deptAdminEntity = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return deptAdminEntity;
	}

	/* modify by deepti add overlapUnit  on 09-05-2016*/
	@Override
	public boolean modifyDepartmentAdminDetail(DepartmentAdminForm dAdminForm, char parentModify) throws Exception {

		Session session = null;
		boolean success = false;
		String sqlQuery = null;
		String englishName = dAdminForm.getAdminLevelNameEnglish();
		String localName = dAdminForm.getAdminLevelNameLocal();
		Integer entityCode = dAdminForm.getAdministrationUnit();
		Integer slc = dAdminForm.getSlc();
		Character overlapUnit= dAdminForm.getOverlapUnit();
		Character parentCategory= dAdminForm.getParentCategory();
		Integer parentCode = null;
		if (parentModify == 'Y')
			parentCode = dAdminForm.getParentAdminCode();
			
		if (parentModify == 'Y') {
			sqlQuery = "update administration_unit_level set unit_level_name_english=:englishName, unit_level_name_local=:localName,parent_admin_unit_level_code "
			+ "=:parentCode ,parent_category =:parentCategory,overlapping_unit =:overlapUnit where admin_unit_level_code=:entityCode and slc=:slc and isactive=true";
		}else {
			sqlQuery = "update administration_unit_level set unit_level_name_english=:englishName, unit_level_name_local=:localName,overlapping_unit ="
					+ ":overlapUnit where admin_unit_level_code=:entityCode and slc=:slc and isactive=true";
		}
			try {
			session = sessionFactory.openSession();
			Query query=session.createSQLQuery(sqlQuery);
			query.setParameter("englishName", englishName);
			query.setParameter("localName",localName );
			query.setParameter("overlapUnit",overlapUnit );
			if (parentModify == 'Y') {
				query.setParameter("parentCode",parentCode);
				query.setParameter("parentCategory", parentCategory);
			}
			
			query.setParameter("entityCode", entityCode);
			query.setParameter("slc",slc );
			query.executeUpdate();
			success = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return success;
	}

	/*Modified by Pooja on 14-08-2015*/
	@Override
	public boolean saveDeptAdminCoverage(String adminCoverage, String wardCoverage, Integer coverageCode, Integer entityCode, Session session) throws Exception {
		Query query = null;
		Boolean status = true;
		String sqlQuery = null;
		if (adminCoverage != null && adminCoverage.isEmpty())
			adminCoverage = null;
		if (wardCoverage != null && wardCoverage.isEmpty())
			wardCoverage = null;
		try {
			if (adminCoverage != null || wardCoverage != null) {
				if (coverageCode != null && coverageCode == 0) {
					query = session.createSQLQuery("select CAST(nextval('adminCoverageCode_seq') AS integer) ");
					coverageCode = (Integer) query.uniqueResult();
					sqlQuery = "update administration_unit_entity set admin_coverage_code='" + coverageCode + "' where admin_unit_entity_code=" + entityCode + " and isactive=true";
					session.createSQLQuery(sqlQuery).executeUpdate();

				}
				query = session.createSQLQuery("select * from administrative_coverage_fn(:adminCoverage,:coverageCode,:wardCoverage)").setParameter("coverageCode", coverageCode, Hibernate.INTEGER)
						.setParameter("adminCoverage", adminCoverage, Hibernate.STRING).setParameter("wardCoverage", wardCoverage, Hibernate.STRING);
				status = (Boolean) query.uniqueResult();
			}

		} catch (Exception e) {
			status = false;
			log.debug("Exception" + e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getUnitLevelNames(Integer adminUnitCode, Integer slc) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Integer parentAdminUnitCode = Integer.valueOf(0);
		String queryBuilder = null;
		List<DeptAdminUnit> parentEntityUnits = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select parent_admin_unit_level_code from administration_unit_level where admin_unit_level_code=:adminUnitCode ");
			query.setParameter("adminUnitCode", adminUnitCode);
			parentAdminUnitCode = (Integer) query.uniqueResult();
			if (parentAdminUnitCode == 1) {
				queryBuilder = "select state_code as parentAdminCode,state_name_english as adminLevelNameEng from state where slc=:statCode and isactive=true order by adminLevelNameEng";
			} else if (parentAdminUnitCode == 2) {
				queryBuilder = "select district_code as parentAdminCode,district_name_english as adminLevelNameEng from district where slc=:statCode and isactive=true order by adminLevelNameEng";
			} else if (parentAdminUnitCode == 3) {
				queryBuilder = "select subdistrict_code as parentAdminCode,subdistrict_name_english as adminLevelNameEng from subdistrict where dlc in(select dlc from district where slc=:statCode and isactive=true) and isactive order by adminLevelNameEng";
			} else if (parentAdminUnitCode == 4) {
				queryBuilder = "select village_code as parentAdminCode,village_name_english as adminLevelNameEng from village where  slc=:statCode and isactive=true order by adminLevelNameEng";
			} else if (parentAdminUnitCode == 5) {
				queryBuilder = "select block_code as parentAdminCode,block_name_english as adminLevelNameEng from block where dlc in(select dlc from district where slc=:statCode and isactive=true) and isactive order by adminLevelNameEng";
			} else {
				queryBuilder = "select admin_unit_entity_code as parentAdminCode,admin_entity_name_english as adminLevelNameEng from administration_unit_entity where  admin_unit_level_code=:adminUnitLevelCode and isactive=true order by adminLevelNameEng";
			}
			query = session.createSQLQuery(queryBuilder);
			if (parentAdminUnitCode == 1 || parentAdminUnitCode == 2 || parentAdminUnitCode == 3 || parentAdminUnitCode == 4 || parentAdminUnitCode == 5)
				query.setParameter("statCode", slc, Hibernate.INTEGER);
			else
				query.setParameter("adminUnitLevelCode", parentAdminUnitCode, Hibernate.INTEGER);
			query.addScalar("parentAdminCode").addScalar("adminLevelNameEng");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			parentEntityUnits = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return parentEntityUnits;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdministrativeEntityCoverage> getEntityCoverageDetail(Integer entityCode) throws Exception {
		List<AdministrativeEntityCoverage> administrativeEntityCoverages = new ArrayList<AdministrativeEntityCoverage>();
		Session session = null;
		try {
			Query query = null;
			session = sessionFactory.openSession();
			query = session.getNamedQuery("adminCoverageDetails");
			query.setParameter("adminEntityCode", entityCode);
			administrativeEntityCoverages = query.list();
		} catch (Exception e) {
			log.debug("Exception " + e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return administrativeEntityCoverages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnitEntity> getUnityEntityByParentLevel(Integer slc, int levelCode, int entityCode) throws Exception {
		Session session = null;
		List<DeptAdminUnitEntity> deptAdminUnitEntities = new ArrayList<DeptAdminUnitEntity>();
		StringBuilder queryBuilder = new StringBuilder("from DeptAdminUnitEntity de where de.slc=:sCode and isactive=true and ");
		try {

			if (entityCode > 0)
				queryBuilder.append("de.adminUnitLevelCode=:levelCode and de.parentAdminUnitEntityCode=:entiycode order by adminUnitEntityCode");
			else
				queryBuilder.append("de.adminUnitLevelCode=:levelCode order by adminUnitEntityCode");
			session = sessionFactory.openSession();
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("sCode", slc);
			query.setParameter("levelCode", levelCode);
			if (entityCode > 0)
				query.setParameter("entiycode", entityCode);
			deptAdminUnitEntities = query.list();

		} catch (Exception e) {
			log.debug("Exceptin " + e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return deptAdminUnitEntities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdministrativeEntityCoverage> adminEntityCoveredArea(int entityCode, int slc, char coverageType) throws Exception {
		Session session = null;
		String sql = null;
		List<AdministrativeEntityCoverage> administrativeEntityCoverages = null;
		try {
			SQLQuery query = null;
			session = sessionFactory.openSession();
			sql = "select entity_link_code as entityCode,entity_type as entiyType,lb_code_for_ward as lbCodeForWard from administrative_entity_coverage where" + " admin_coverage_code in (select admin_coverage_code from administration_unit_entity  where admin_unit_level_code=:Code "
					+ "and slc=:slc and admin_coverage_code > 0)  and coverage_type=:cType and isactive=true";
			query = session.createSQLQuery(sql);
			query.setParameter("slc", slc);
			query.setParameter("Code", entityCode);
			query.setParameter("cType", coverageType);
			query.addScalar("entityCode").addScalar("entiyType").addScalar("lbCodeForWard");
			query.setResultTransformer(Transformers.aliasToBean(AdministrativeEntityCoverage.class));
			administrativeEntityCoverages = query.list();
		} catch (Exception e) {
			log.debug("Exception " + e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return administrativeEntityCoverages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationDetailbystateandType(Integer stateCode, int type) throws Exception {
		Query criteria = null;
		Session session = null;
		Integer slc = null;
		String topLevelType = null;
		if (type == 3)
			topLevelType = "3,4,5,6";
		else if (type == 2)
			topLevelType = "2";
		List<Organization> organizationList = null;
		try {

			slc = commonService.getSlc(stateCode);
			if (slc != null) {
				session = sessionFactory.openSession();
				/* query modified by pooja on 22-06-2015 for orderby name */
				criteria = session.createQuery("from Organization where isactive=true and slc=:slc and orgTypeCode in (" + topLevelType + ") order by orgName");
				criteria.setParameter("slc", slc, Hibernate.INTEGER);
				organizationList = criteria.list();
			}

		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationParent(Integer orgCode, int orgLevel) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<Organization> organizationsList = null;
		try {
			session = sessionFactory.openSession();
			/*
			 * Query modified for multiple types of offices at a given level by
			 * pooja on 22-06-2015
			 */
			query = session.createSQLQuery("select org_unit_code as orgCode,org_unit_name as orgName from org_units where org_located_level_code "
					+ " in (select org_located_level_code from org_located_at_levels where olc =:code and located_at_level =:level and isactive) and headoffice='H' and isactive order by orgName");
			query.setParameter("code", orgCode, Hibernate.INTEGER);
			query.setParameter("level", orgLevel, Hibernate.INTEGER);
			query.addScalar("orgCode").addScalar("orgName");
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			organizationsList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return organizationsList;

	}

	/**
	 * 
	 * get Organization Name by orgCode added by Anju Gupta on 6/01/2015 query
	 * modified by pooja on 18-06-2015
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationParentName(int orgCode, int locatedAtLevel) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<Organization> organizationsList = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select org_unit_code as orgCode,org_unit_name as orgName " + " from org_units where org_located_level_code in "
					+ " (select org_located_level_code from org_located_at_levels where olc=:orgCode and located_at_level=:locatedAtLevel and isactive is true) and isactive order by orgName");
			query.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			query.setParameter("locatedAtLevel", locatedAtLevel, Hibernate.INTEGER);
			query.addScalar("orgCode").addScalar("orgName");
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			organizationsList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return organizationsList;

	}

	/**
	 * ANJU
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationChilds(Integer parentOrgCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<Organization> organizationsList = null;
		try {
			session = sessionFactory.openSession();
			/*
			 * Query modified for multiple types of offices at a given level by
			 * pooja on 22-06-2015
			 */
			query = session
					.createSQLQuery("select org_unit_code as orgCode,org_unit_name as orgName from org_units where org_located_level_code in (select org_located_level_code "
							+ " from org_located_at_levels where parent_org_located_level_code=(select org_located_level_code from org_units where org_unit_code =:parentOrgCode and isactive) and isactive) and isactive and (parent_org_unit_code is NULL or parent_org_unit_code=0)");
			query.setParameter("parentOrgCode", parentOrgCode, Hibernate.INTEGER);
			query.addScalar("orgCode").addScalar("orgName");
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			organizationsList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return organizationsList;
	}

	@Override
	public boolean updateOrgParentChildUnits(int parentCode, String childOrgUnits) throws Exception {
		Session session = null;
		SQLQuery query = null;
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			query = session.createSQLQuery("update org_units set parent_org_unit_code=NULL where parent_org_unit_code =:parentCode and isactive");
			query.setParameter("parentCode", parentCode, Hibernate.INTEGER);
			query.executeUpdate();
			session.getTransaction().commit();
			query = session.createSQLQuery("update org_units set parent_org_unit_code=:parentCode where " + "org_unit_code in(" + childOrgUnits + ") and isactive");
			query.setParameter("parentCode", parentCode, Hibernate.INTEGER);
			query.executeUpdate();
			status = true;
		} catch (Exception e) {
			log.error("Exception-->" + e);

		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return status;
	}

	/**
	 * original code merged from bug fixed by Sushil on 18-05-2015
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationSpecificChilds(Integer parentOrgCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<Organization> organizationsList = new ArrayList<Organization>();
		List<Organization> organizationsListEntity = new ArrayList<Organization>();
		List<Organization> entityOrgList = new ArrayList<Organization>();
		Integer enttiyType = null;
		Integer entityCode = null;
		String result;
		String sql = null;
		int lrOrgCode;
		int count;
		int entityOrgCode;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select concat(entity_lc, ',', entity_type) from org_units where org_unit_code=:code and isactive");
			query.setParameter("code", parentOrgCode);
			result = (String) query.uniqueResult();
			String data[] = result.split(",");
			entityCode = Integer.parseInt(data[0]);
			enttiyType = Integer.parseInt(data[1]);
			/*
			 * queries modified for taking multiple levels by pooja on
			 * 25-06-2015
			 */
			
			if(enttiyType<0){
				sql="select  org_unit_code as orgCode,org_unit_name as orgName from org_units o ,localbody l where org_located_level_code in"
			+ " (select org_located_level_code from org_located_at_levels  where parent_org_located_level_code=(select org_located_level_code from "
			+ "org_units where org_unit_code =:parentOrgCode and isactive) and isactive)and o.isactive and  o.entity_lc=l.local_body_code and l.isactive and "
			+ "l.parent_lblc in( select lblc from localbody  where   local_body_code  =:entityCode and isactive) and  (o.parent_org_unit_code is NULL or o.parent_org_unit_code=0)";
			}else if (enttiyType == 1) {

				sql = "select  org_unit_code as orgCode,org_unit_name as orgName from org_units o ,district d where org_located_level_code in (select org_located_level_code"
						+ " from org_located_at_levels where parent_org_located_level_code=(select org_located_level_code from org_units where"
						+ " org_unit_code =:parentOrgCode and isactive) and isactive)and o.isactive and o.entity_lc=d.dlc and d.isactive and d.slc=:entityCode and "
						+ "(o.parent_org_unit_code is NULL or o.parent_org_unit_code=0)"
						+ " union "
						+" select  org_unit_code as orgCode,org_unit_name as orgName from org_units o ,localbody l where org_located_level_code in (select org_located_level_code from org_located_at_levels "
						+ " where parent_org_located_level_code=(select org_located_level_code from org_units where org_unit_code =:parentOrgCode and isactive) and isactive)and o.isactive and "
						+ "o.entity_lc=l.local_body_code and l.isactive and l.slc=:entityCode and (o.parent_org_unit_code is NULL or o.parent_org_unit_code=0)";
			} else if (enttiyType == 2) {

				sql = "select  org_unit_code as orgCode,org_unit_name as orgName from org_units o ,subdistrict s where org_located_level_code in (select org_located_level_code"
						+ " from org_located_at_levels where parent_org_located_level_code=(select org_located_level_code from org_units where"
						+ " org_unit_code =:parentOrgCode and isactive) and isactive)and o.isactive and o.entity_lc=s.tlc and s.isactive and s.dlc=:entityCode and (o.parent_org_unit_code is NULL or o.parent_org_unit_code=0)"
						+" union "
						+"select  org_unit_code as orgCode,org_unit_name as orgName from org_units o ,block b where org_located_level_code in (select org_located_level_code from org_located_at_levels"
						+ " where parent_org_located_level_code=(select org_located_level_code from org_units where org_unit_code =:parentOrgCode and isactive) and isactive) and "
						+ "o.isactive and o.entity_lc=b.blc and b.isactive and b.dlc=:entityCode and (o.parent_org_unit_code is NULL or o.parent_org_unit_code=0)";
			} else if (enttiyType == 3) {

				sql = "select  org_unit_code as orgCode,org_unit_name as orgName from org_units o ,village v where org_located_level_code in (select org_located_level_code"
						+ " from org_located_at_levels where parent_org_located_level_code=(select org_located_level_code from org_units where"
						+ " org_unit_code =:parentOrgCode and isactive) and isactive)and o.isactive and o.entity_lc=v.vlc and v.isactive and v.tlc=:entityCode and (o.parent_org_unit_code is NULL or o.parent_org_unit_code=0)";
			} else if (enttiyType == 5) {

				sql = "select  org_unit_code as orgCode,org_unit_name as orgName from org_units o ,block b where org_located_level_code in (select org_located_level_code"
						+ " from org_located_at_levels where parent_org_located_level_code=(select org_located_level_code from org_units where"
						+ " org_unit_code =:parentOrgCode and isactive) and isactive)and o.isactive and o.entity_lc=b.blc and b.isactive and b.dlc=:entityCode and (o.parent_org_unit_code is NULL or o.parent_org_unit_code=0)";
			}
			if (null != sql && !sql.isEmpty()) {
				query = session.createSQLQuery(sql);
				query.setParameter("parentOrgCode", parentOrgCode, Hibernate.INTEGER);
				query.setParameter("entityCode", entityCode, Hibernate.INTEGER);
				query.addScalar("orgCode").addScalar("orgName");
				query.setResultTransformer(Transformers.aliasToBean(Organization.class));
				organizationsList = query.list();

			}

			sql = "select  org_unit_code as orgCode,org_unit_name as orgName from org_units o ,administration_unit_entity a where org_located_level_code in (select org_located_level_code"
					+ " from org_located_at_levels where parent_org_located_level_code=(select org_located_level_code from org_units where"
					+ " org_unit_code =:parentOrgCode and isactive) and isactive)and o.isactive and o.entity_lc=a.admin_unit_entity_code and a.isactive and a.parent_unit_entity_code=:entityCode and (o.parent_org_unit_code is NULL or o.parent_org_unit_code=0)";
			query = session.createSQLQuery(sql);
			query.setParameter("parentOrgCode", parentOrgCode, Hibernate.INTEGER);
			query.setParameter("entityCode", entityCode, Hibernate.INTEGER);
			query.addScalar("orgCode").addScalar("orgName");
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			organizationsListEntity = query.list();
			for (Organization obj : organizationsListEntity) {
				count = 1;
				entityOrgCode = obj.getOrgCode();
				for (Organization lrObj : organizationsList) {
					lrOrgCode = lrObj.getOrgCode();
					if (entityOrgCode == lrOrgCode) {
						count = 0;
						break;
					}
				}
				if (count == 1)
					entityOrgList.add(obj);

			}
			if (entityOrgList.size() > 0)
				organizationsList.addAll(entityOrgList);

		}

		catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return organizationsList;
	}

	/* added by chandra on 11-09-14 to get previous mapped organization Units */
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationMappedChilds(Integer parentOrgCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<Organization> organizationsList = null;
		try {
			session = sessionFactory.openSession();
			/*
			 * Query modified for multiple types of offices at a given level by
			 * pooja on 22-06-2015
			 */
			query = session.createSQLQuery("select org_unit_code as orgCode,org_unit_name as orgName from org_units where org_located_level_code in (select org_located_level_code "
					+ " from org_located_at_levels where parent_org_located_level_code=(select org_located_level_code from org_units where org_unit_code =:parentOrgCode and isactive) and isactive) and isactive and parent_org_unit_code =:parentOrgCode");
			query.setParameter("parentOrgCode", parentOrgCode, Hibernate.INTEGER);
			query.addScalar("orgCode").addScalar("orgName");
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			organizationsList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return organizationsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAdminEntityOrgUnits(Integer adminEntityCode) throws Exception {
		Session session = null;
		String queryString = "select org_unit_name from org_units where entity_lc=:code and entity_type=:type and isactive";
		Integer type = null;
		SQLQuery query = null;
		List<Object> objects = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			type = (Integer) session.createSQLQuery("select admin_unit_level_code from administration_unit_entity  where   admin_unit_entity_code=:code   and isactive").setParameter("code", adminEntityCode).uniqueResult();
			query = (SQLQuery) session.createSQLQuery(queryString).setParameter("code", adminEntityCode).setParameter("type", type);
			objects = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objects;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String updateOrgUnitNames(Integer adminEntityCode, String name) throws Exception {
		Session session = null;
		String queryString = "select org_unit_code,org_unit_name from org_units where entity_lc=:code and entity_type=:type and isactive";
		Integer type = null;
		SQLQuery query = null;
		List<Object[]> objects = null;
		StringBuilder updateScript = new StringBuilder();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			type = (Integer) session.createQuery("select adminUnitLevelCode from DeptAdminUnitEntity where adminUnitEntityCode=:code and isactive=true").setParameter("code", adminEntityCode).uniqueResult();
			query = (SQLQuery) session.createSQLQuery(queryString).setParameter("code", adminEntityCode).setParameter("type", type);
			objects = query.list();
			StringBuilder builder = new StringBuilder();
			String orgOldname = new String();
			Integer OrgCode = null;
			for (Object[] obj : objects) {
				OrgCode = (Integer) obj[0];
				orgOldname = (String) obj[1];
				builder.append(orgOldname.substring(0, orgOldname.indexOf("-"))).append("- ").append(name).append(" )");
				query = session.createSQLQuery("update org_units set org_unit_name=:newName where " + "org_unit_code=:entityCode ");
				query.setParameter("entityCode", OrgCode, Hibernate.INTEGER);
				query.setParameter("newName", builder.toString(), Hibernate.STRING);
				query.executeUpdate();
				updateScript.append("update org_units set org_unit_name='").append(builder).append("' where org_unit_code=").append(OrgCode).append(";\n");
				builder.delete(0, builder.length());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		System.out.println(updateScript);
		return updateScript.toString();
	}

	@Override
	public String modifyDepartmentAdminEntity(DepartmentAdminForm dAdminForm, char parentModify) throws Exception {
		Session session = null;
		String sqlQuery = null;
		String englishName = dAdminForm.getAdminLevelNameEnglish();
		String localName = dAdminForm.getAdminLevelNameLocal();
		Integer entityCode = dAdminForm.getAdministrationUnit();
		Integer slc = dAdminForm.getSlc();
		Integer distCode = dAdminForm.getDistrictCode();
		Integer unitLevelCode = null;
		Integer parentCode = null;
		
		if (parentModify == 'Y') {
		unitLevelCode = dAdminForm.getAdminUnitLevelCode();
			parentCode = dAdminForm.getParentAdminCode();
			sqlQuery = "update administration_unit_entity set admin_entity_name_english=:englishName, "
			+ "admin_entity_name_local=:localName, parent_unit_entity_code =:parentCode,admin_unit_level_code ="
			+ ":unitLevelCode,districtCode=:distCode,parent_category=(select parent_category from "
			+ "administration_unit_level where admin_unit_level_code =:unitLevelCode and isactive)"
			+ " where admin_unit_entity_code=:entityCode and slc=:slc and isactive=true";
		} else {
			sqlQuery = "update administration_unit_entity set admin_entity_name_english=:englishName, "
			+ "admin_entity_name_local=:localName where admin_unit_entity_code=:entityCode and slc=:slc"
			+ " and districtCode=:distCode and isactive=true";
		}

		try {
			session = sessionFactory.openSession();
			SQLQuery query =session.createSQLQuery(sqlQuery);
			query.setParameter("englishName", englishName);
			query.setParameter("localName", localName);
			query.setParameter("entityCode",entityCode );
			query.setParameter("distCode", distCode);
			query.setParameter("slc",slc );
			if (parentModify == 'Y') {
			query.setParameter("parentCode", parentCode);
			query.setParameter("unitLevelCode",unitLevelCode );
			}
			query.executeUpdate();

		} catch (Exception e) {
			sqlQuery = null;
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return sqlQuery;
	}

	/**
	 * Added by Ripunj On 29-09-2014 To get Admin Unit Levels
	 */
	@SuppressWarnings("unchecked")
	public List<ExtendDepartment> getAdministrativeUnitLevelByOrgCode(Integer orgCode, String isCenterFlag) throws Exception {

		Session session = null;
		SQLQuery query = null;
		List<ExtendDepartment> listAdminUnitLevel = new ArrayList<ExtendDepartment>();
		try {
			session = sessionFactory.openSession();
			if (("F").equalsIgnoreCase(isCenterFlag)) {
				query = session.createSQLQuery("select org_located_level_code as orgLocatedLevelCode,admin_unit_level_code as adminUnitLevelCode," + " unit_level_name_english as unitLevelNameEnglish  "
						+ " from org_located_at_levels org,Administration_Unit_Level aul where admin_unit_level_code!=1 and  located_at_level=admin_unit_level_code  and olc=:orgCode and org.isactive and aul.isactive "
						+ " order by parent_org_located_level_code asc");
			} else {
				query = session.createSQLQuery("select org_located_level_code as orgLocatedLevelCode,admin_unit_level_code as adminUnitLevelCode," + " unit_level_name_english as unitLevelNameEnglish  "
						+ " from org_located_at_levels org,Administration_Unit_Level aul where  located_at_level=admin_unit_level_code  and olc=:orgCode and org.isactive and aul.isactive " + " order by parent_org_located_level_code asc");
			}
			query.setParameter("orgCode", orgCode);
			query.setResultTransformer(Transformers.aliasToBean(ExtendDepartment.class));
			listAdminUnitLevel = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listAdminUnitLevel;
	}

	/**
	 * Added by Ripunj On 29-09-2014 To get Admin Unit Levels
	 */
	@SuppressWarnings("unchecked")
	public List<OrgLocatedAtLevels> getAdministrativeUnitLevelByOrgLevel(Integer orgCode, Integer locatedLevelCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<OrgLocatedAtLevels> organizationList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrgLocatedAtLevels where isactive=true and olc=:orgCode and  locatedAtLevel=:locatedLevelCode");
			criteria.setParameter("orgCode", orgCode, Hibernate.INTEGER);
			criteria.setParameter("locatedLevelCode", locatedLevelCode, Hibernate.INTEGER);
			organizationList = criteria.list();
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getOrgCoverageForFullCoveage(int orgLevelCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<OrganizationCoverage> organizationCoverageList = new ArrayList<OrganizationCoverage>();
		String isCoverageFull = "S";
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from OrganizationCoverage where coverage='F' and isactive=true " + "and org_located_level_code=" + orgLevelCode);
			organizationCoverageList = criteria.list();
			if (organizationCoverageList.size() > 0) {
				isCoverageFull = "F";
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isCoverageFull;
	}

	/**
	 * added on 11/12/2014 for localbody draft
	 */

	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getUnitLevelNamesForLocalBody(Integer adminUnitCode, Integer slc) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Integer parentAdminUnitCode = Integer.valueOf(0);
		String queryBuilder = null;
		List<DeptAdminUnit> parentEntityUnits = null;
		try {
			session = sessionFactory.openSession();
			
			Criteria criteria = session.createCriteria(DeptAdminUnit.class);
			criteria.add( Restrictions.eq("isactive",Boolean.TRUE));
			criteria.add( Restrictions.eq("adminUnitCode", adminUnitCode));
			
			List<DeptAdminUnit> existDeptAdminUnit = criteria.list();
			if(existDeptAdminUnit!=null && !existDeptAdminUnit.isEmpty()){
				DeptAdminUnit existDeptForm=existDeptAdminUnit.get(0);
				Character parentCategory=existDeptForm.getParentCategory();
				parentAdminUnitCode=existDeptForm.getParentAdminCode();
				if(parentCategory=='G'){
					queryBuilder = "select cast('A' as character) as  operation_state, local_body_code as parentAdminCode,local_body_name_english as adminLevelNameEng from localbody where  local_body_type_code=:adminUnitLevelCode and isactive=true and slc=:statCode order by adminLevelNameEng";
				}
				else{
					/*Modified by pooja on 15-10-2015 for administrative unit entity at center level*/
					if(parentAdminUnitCode == 0) {
						queryBuilder = "select cast('A' as character) as  operation_state,cast(0 as integer) as parentAdminCode,cast('Center' as character varying) as adminLevelNameEng ";
					}	
					else if(parentAdminUnitCode == 1 && slc == 0){
						queryBuilder = "select case when st.state_code  in (select local_body_code from get_draft_used_lb_lr_temp(st.state_code,'S')) "
								+ "then cast('F' as character)  else cast('A' as character) end as operation_state ,state_code as parentAdminCode,state_name_english as adminLevelNameEng from state st where isactive order by adminLevelNameEng";
					}
					else if(parentAdminUnitCode == -100 && slc == 0){
						queryBuilder = "select cast('A' as character) as  operation_state, country_code as parentAdminCode,country_name as adminLevelNameEng from country order by country_name";
					}
					else if (parentAdminUnitCode == 1 && slc != 0) {
						queryBuilder = "select case when st.state_code  in (select local_body_code from get_draft_used_lb_lr_temp(st.state_code,'S')) "
								+ "then cast('F' as character)  else cast('A' as character) end as operation_state ,state_code as parentAdminCode,state_name_english as adminLevelNameEng from state st where slc=:statCode and isactive=true order by adminLevelNameEng";
					} else if (parentAdminUnitCode == 2) {
						queryBuilder = "select case when dt.district_code  in (select local_body_code from get_draft_used_lb_lr_temp(dt.district_code,'D')) "
								+ "then cast('F' as character)  else cast('A' as character) end as operation_state ,district_code as parentAdminCode,district_name_english as adminLevelNameEng from district dt where slc=:statCode and isactive=true order by adminLevelNameEng";
					} else if (parentAdminUnitCode == 3) {
						queryBuilder = "select case when sd.subdistrict_code  in (select local_body_code from get_draft_used_lb_lr_temp(sd.subdistrict_code,'T')) "
								+ "then cast('F' as character)  else cast('A' as character) end as operation_state , subdistrict_code as parentAdminCode,subdistrict_name_english as adminLevelNameEng from subdistrict  sd where dlc in(select dlc from district where slc=:statCode and isactive=true) and isactive order by adminLevelNameEng";
					} else if (parentAdminUnitCode == 4) {
						queryBuilder = "select  case when vl.village_code  in (select local_body_code from get_draft_used_lb_lr_temp(vl.village_code,'V')) "
								+ "then cast('F' as character)  else cast('A' as character) end as operation_state ,village_code as parentAdminCode,village_name_english as adminLevelNameEng from village vl where  slc=:statCode and isactive=true order by adminLevelNameEng";
					} else if (parentAdminUnitCode == 5) {
						queryBuilder = "select  case when bl.block_code  in (select local_body_code from get_draft_used_lb_lr_temp(bl.block_code,'B')) "
								+ "then cast('F' as character)  else cast('A' as character) end as operation_state , block_code as parentAdminCode,block_name_english as adminLevelNameEng from block bl where dlc in(select dlc from district where slc=:statCode and isactive=true) and isactive order by adminLevelNameEng";
					} else {
						queryBuilder = "select cast('A' as character) as  operation_state, admin_unit_entity_code as parentAdminCode,admin_entity_name_english as adminLevelNameEng from administration_unit_entity where  admin_unit_level_code=:adminUnitLevelCode and isactive=true order by adminLevelNameEng";
					}
					
				}
				query = session.createSQLQuery(queryBuilder);
				if(parentCategory=='G'){
					query.setParameter("adminUnitLevelCode", parentAdminUnitCode, Hibernate.INTEGER);
					query.setParameter("statCode", slc, Hibernate.INTEGER);
				}else{
					if ((parentAdminUnitCode == 1 && slc!=0) || parentAdminUnitCode == 2 || parentAdminUnitCode == 3 || parentAdminUnitCode == 4 || parentAdminUnitCode == 5)
						query.setParameter("statCode", slc, Hibernate.INTEGER);
					else if(parentAdminUnitCode != 0 && parentAdminUnitCode != 1 && parentAdminUnitCode!=-100)
						query.setParameter("adminUnitLevelCode", parentAdminUnitCode, Hibernate.INTEGER);
				}
				query.addScalar("operation_state");
				query.addScalar("parentAdminCode").addScalar("adminLevelNameEng");
				query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
				parentEntityUnits = query.list();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return parentEntityUnits;
	}

	/**
	 * 
	 * @author Anju @ on 12-01-2015 @ to Update OrgUnit details According to
	 *         orgunitcode modified by pooja on 25-06-2015 for update
	 *         orgunitname
	 */
	
	public boolean getUpdateMethod(OrganizationUnitForm orggunit) {
		boolean retValue=false;
		Session session = null;
		SQLQuery query = null;
		String orgSpecCode = orggunit.getOrgSpecCode();
		String orgUnitName = orggunit.getOrgUnitName();
		String orgUnitNameInLocal = orggunit.getOrgUnitNameInLocal();

		String phoneno = orggunit.getPhoneNo();
		Integer pincode = null;
		if (!("").equals(orggunit.getPinCode()))
			pincode = Integer.parseInt(orggunit.getPinCode());
		String email = orggunit.getEmail();
		String address1 = orggunit.getAddress1();
		String address2 = orggunit.getAddress2();
		String address3 = orggunit.getAddress3();
		String level = orggunit.getLevel();
		Integer code = orggunit.getCode();
		String villageCode = orggunit.getVillageListRLB();
		String ulbCode = orggunit.getUlbList();
		if(level!= null && level!= "")
		{
	
		if (level.equalsIgnoreCase("1")) { 
			level = "U";
			code =ulbCode!=null?Integer.parseInt(ulbCode):null;
		} else {
			level = "R";
			code = villageCode!=null?Integer.parseInt(villageCode):null;
		}
		
		}
		else
		{
			code=0;
			level="";
			
		}
		
		try {
			session = sessionFactory.openSession();
			
									
			
			query = session.createSQLQuery(" select * from capture_org_units_details(:orgSpecCode,:orgUnitName,"
					+ ":phoneno,:email,:address1,:address2,:address3,:pincode,:level,:code,:orgUnitCode,:userId,:orgUnitNameInLocal)");
			
			query.setParameter("orgSpecCode", orgSpecCode);
			query.setParameter("orgUnitName", orgUnitName);
			query.setParameter("phoneno", phoneno);
			query.setParameter("email", email);
			query.setParameter("address1", address1);
			query.setParameter("address2", address2);
			query.setParameter("address3", address3);
			query.setParameter("pincode", pincode, Hibernate.INTEGER);
			query.setParameter("level", level);
			query.setParameter("code", code);
			query.setParameter("orgUnitCode", orggunit.getOrgUnitCode());
			query.setParameter("userId", orggunit.getUserId());
			query.setParameter("orgUnitNameInLocal", orgUnitNameInLocal);
			retValue = (boolean) query.uniqueResult();
			

		} catch (Exception e) {

			log.debug("Exception" + e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return retValue;
	}

	/**
	 * 
	 * @author Anju @ on 12-01-2015 @ to getOrganizationDetailby Parent Org Code
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getDepartmentDetails(Integer parentorgcode) {
		Session session = null;
		SQLQuery query = null;
		List<Organization> organizationsList1 = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select org_name as orgName,org_code as orgCode from Organization where parent_org_code=:parentorgcode and isactive=true order by org_name");

			query.setParameter("parentorgcode", parentorgcode, Hibernate.INTEGER);
			query.addScalar("orgCode").addScalar("orgName");
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			organizationsList1 = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return organizationsList1;

	}

	/**
	 * 
	 * @author Anju @ on 08-01-2015 @ to getOrganizationDetailby Slc Code
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationDetailbySlcCode(Integer slcCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<Organization> organizationsList1 = null;
		try {

			session = sessionFactory.openSession();
			query = session.createSQLQuery("select org_name as orgName,org_code as orgCode from Organization where isactive=true and slc=:slcCode order by org_name");
			query.setParameter("slcCode", slcCode, Hibernate.INTEGER);
			query.addScalar("orgCode").addScalar("orgName");
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			organizationsList1 = query.list();
		}

		catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return organizationsList1;

	}

	/**
	 * Added by Anju On 06-01-2015 To get Org Located Levels
	 */
	@SuppressWarnings("unchecked")
	public List<OrgLocatedAtLevels> getOrganizationnUnitLevelByOrgLevel(Integer orgCode) throws Exception {

		Session session = null;
		SQLQuery query = null;
		List<OrgLocatedAtLevels> listAdminUnitLevelq = new ArrayList<OrgLocatedAtLevels>();

		try {
			session = sessionFactory.openSession();
			{
				query = session.createSQLQuery("select org_located_level_code as orgLocatedLevelCode,located_at_level as locatedAtLevel" +

				" from org_located_at_levels org where  olc=:orgCode");
			}
			query.setParameter("orgCode", orgCode);
			query.addScalar("orgLocatedLevelCode").addScalar("locatedAtLevel");
			query.setResultTransformer(Transformers.aliasToBean(OrgLocatedAtLevels.class));
			listAdminUnitLevelq = query.list();

		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listAdminUnitLevelq;
	}

	/**
	 * Added by Anju Gupta On 06-01-2015 To get Org Unit level
	 */
	@SuppressWarnings("unchecked")
	public List<OrganizationUnit> getOrganizationeUnitLevelByorglocatedlevelcode(Integer orglocatedlevelcode) throws Exception {

		Session session = null;
		SQLQuery query = null;
		List<OrganizationUnit> listOrgUnitLevel = new ArrayList<OrganizationUnit>();
		try {
			session = sessionFactory.openSession();

			query = session.createSQLQuery("select org_unit_code  as orgunitcode,org_unit_name as orgunitname," +

			" from org_unit org in(orglocatedlevelcode) ");

			query.setParameter("orglocatedlevelcode", orglocatedlevelcode);
			query.setResultTransformer(Transformers.aliasToBean(OrganizationUnit.class));
			listOrgUnitLevel = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listOrgUnitLevel;
	}

	/**
	 * Added by Anju Gupta On 18-03-2015 To get Org located at levelS
	 */

	@SuppressWarnings("unchecked")
	public List<ExtendDepartment> getOrg_located_at_levelsByOrgCode(Integer orgCode, String isCenterFlag) throws Exception {

		Session session = null;
		SQLQuery query = null;
		List<ExtendDepartment> listAdminUnitLevel = new ArrayList<ExtendDepartment>();
		try {
			session = sessionFactory.openSession();
			/* modified by pooja on 18-06-2015 */
			if (("F").equalsIgnoreCase(isCenterFlag)) {
				query = session.createSQLQuery("select distinct admin_unit_level_code as adminUnitLevelCode," + " unit_level_name_english as unitLevelNameEnglish  from org_located_at_levels org,Administration_Unit_Level aul "
						+ " where located_at_level=admin_unit_level_code  and olc=:orgCode and org.isactive and aul.isactive " + " order by adminUnitLevelCode asc");
			} else {
				query = session.createSQLQuery("select distinct located_at_level as adminUnitLevelCode," + "CASE  WHEN located_at_level = 0 THEN 'Center'WHEN  located_at_level>0 "
						+ "THEN (SELECT unit_level_name_english FROM Administration_Unit_Level " + "WHERE admin_unit_level_code=located_at_level) END  as unitLevelNameEnglish "
						+ "from org_located_at_levels where olc=:orgCode order by adminUnitLevelCode");
			}
			query.setParameter("orgCode", orgCode);
			query.setResultTransformer(Transformers.aliasToBean(ExtendDepartment.class));
			listAdminUnitLevel = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listAdminUnitLevel;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExtendDepartment> getAdministrativeUnitLevelDeptByOrgCode(Integer orgCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<ExtendDepartment> listAdminUnitLevel = new ArrayList<ExtendDepartment>();
		try {
			/* modified by kirandeep for getting the is active levels */
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select org_located_level_code as orgLocatedLevelCode,located_at_level as adminUnitLevelCode ,"
			+ "CASE WHEN located_at_level = 0 THEN 'Center' "
			+ "WHEN  located_at_level < 0 THEN  (select local_body_type_name from local_body_type  where local_body_type_code= abs(org_located_at_levels.located_at_level) )"
			+ "	ELSE (SELECT unit_level_name_english FROM Administration_Unit_Level WHERE admin_unit_level_code= (located_at_level) ) END as unitLevelNameEnglish "
			+ "from org_located_at_levels where olc=:orgCode and isactive=true");
			query.setParameter("orgCode", orgCode);
			query.setResultTransformer(Transformers.aliasToBean(ExtendDepartment.class));
			listAdminUnitLevel = query.list();
		} catch (Exception e) {
			log.error("Exception in OrganizationDaoImpl.getAdministrativeUnitLevelByOrgCode" + e);
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listAdminUnitLevel;
	}

	/**
	 * To get Draft Dept Admin Units
	 * 
	 * @author Ripunj on 21-04-2015
	 * @param slc
	 * @param type
	 * @param isPublish
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getDraftDeptAdminUnits(Integer slc, Integer type, Character isPublish) throws Exception {
		Query query = null;
		Session session = null;
		List<DeptAdminUnit> deptadminUnits = new ArrayList<DeptAdminUnit>();
		String sql = null;
		if (type == 1)
			sql = "from DeptAdminUnit du where (du.slc=:sCode or du.slc=0) and isactive=false and ispublish=:publish_state order by adminUnitCode";
		else
			sql = "from DeptAdminUnit du where du.slc=:sCode and isactive=false and ispublish=:publish_state order by adminUnitCode";
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(sql);
			query.setParameter("sCode", slc);
			query.setParameter("publish_state", isPublish);
			deptadminUnits = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return deptadminUnits;

	}

	/**
	 * To update Draft Dept Admin Units
	 * 
	 * @author Ripunj on 21-04-2015
	 * @param deptAdminUnit
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean saveorUpdateDeptAdmitUnitDraft(DeptAdminUnit deptAdminUnit) throws Exception {
		Session session = null;
		Transaction tx = null;
		boolean success = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Timestamp timestamp = CurrentDateTime.getCurrentTimestamp();
			deptAdminUnit.setCreatedon(timestamp);
			deptAdminUnit.setRevenueEntity(false);
			session.update(deptAdminUnit);
			tx.commit();
			success = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return success;
	}

	/**
	 * To delete Draft Dept Admin Units
	 * 
	 * @author Ripunj on 22-04-2015
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteAdminUnitLevel(Integer slc, Integer adminUnitCode, int entityType) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		String coverageCode = Integer.toString(adminUnitCode);
		String stateCode = Integer.toString(slc);
		String sql = null;
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			sql = "delete from Administration_Unit_Level au where  au.Admin_unit_level_code= " + coverageCode + " and au.Slc='" + stateCode + "' and isactive=false and ispublish = 'D' ";
			query = session.createSQLQuery(sql);
			query.executeUpdate();
			txn.commit();
			status = true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	/**
	 * To update active flag false in case of delete administrative unit level
	 * ,entities and their coverages
	 * 
	 * @author Ripunj on 22-04-2015
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteAdminUnitEntitysetFlagFalse(Integer slc, Integer adminUnitCode, int entityType) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		String coverageCode = Integer.toString(adminUnitCode);
		String stateCode = Integer.toString(slc);
		String sql = null;
		boolean status = false;
		Integer adminCoverageCode = Integer.valueOf(0);
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			if (entityType == 0)
				sql = "update Administration_Unit_Level au set isactive=false where au.Admin_unit_level_code= " + coverageCode + " and au.Slc='" + stateCode + "' and isactive=true and ispublish='P' ";
			else {
				sql = "update administration_unit_entity ae set isactive=false where ae.admin_unit_entity_code= " + coverageCode + " and ae.Slc='" + stateCode + "' and isactive=true and ispublish='P' ";
				query = session.createSQLQuery("select admin_coverage_code from administration_unit_entity where admin_unit_entity_code=:code");
				query.setParameter("code", adminUnitCode);
				adminCoverageCode = (Integer) query.uniqueResult();

			}
			query = session.createSQLQuery(sql);
			query.executeUpdate();
			if (adminCoverageCode > 0) {
				query = session.createSQLQuery("update administrative_entity_coverage ac set isactive=false where ac.admin_coverage_code =:adminCoverageCode and isactive=true");
				query.setParameter("adminCoverageCode", adminCoverageCode);
				query.executeUpdate();
			}
			txn.commit();
			status = true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	/**
	 * Added by kirandeep for the geting the parent level for draft in admin
	 * unit entity
	 * 
	 * @param slc
	 * @param levelCode
	 * @param entityCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnitEntity> getUnityEntityByParentLevelForDraft(Integer slc, int levelCode, int entityCode) throws Exception {
		Session session = null;
		List<DeptAdminUnitEntity> deptAdminUnitEntities = new ArrayList<DeptAdminUnitEntity>();
		List<DeptAdminUnitEntity> deptAdminUnitEntities2 = new ArrayList<DeptAdminUnitEntity>();
		List<DeptAdminUnit> deptAdminUnit = new ArrayList<DeptAdminUnit>();
		StringBuilder queryBuilder = new StringBuilder("from DeptAdminUnitEntity de where de.slc=:sCode and isactive=false and isPublish='D' and ");
		
		try {
			if (entityCode > 0)
				queryBuilder.append("de.adminUnitLevelCode=:levelCode and de.parentAdminUnitEntityCode=:entiycode order by adminUnitEntityCode");
			else
				queryBuilder.append("de.adminUnitLevelCode=:levelCode order by adminUnitEntityCode");
			session = sessionFactory.openSession();
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("sCode", slc);
			query.setParameter("levelCode", levelCode);
			if (entityCode > 0)
				query.setParameter("entiycode", entityCode);
			deptAdminUnitEntities = query.list();

			StringBuilder queryBuilder1 = new StringBuilder("from DeptAdminUnit where adminUnitCode = " + levelCode);
			Query query2 = session.createQuery(queryBuilder1.toString());
			deptAdminUnit = query2.list();
			for (DeptAdminUnitEntity deptAdminUnitEntity : deptAdminUnitEntities) {
				deptAdminUnitEntity.setParentAdminUnitEntityName(deptAdminUnit.get(0).getAdminLevelNameEng());
				deptAdminUnitEntities2.add(deptAdminUnitEntity);
			}

		} catch (Exception e) {
			log.debug("Exceptin " + e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return deptAdminUnitEntities2;
	}

	/**
	 * Added by kirandeep for deleting a admin unit enitity from draft
	 * 
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteAdminUnitEntityForDraft(Integer slc, Integer adminUnitCode, int entityType) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		String coverageCode = Integer.toString(adminUnitCode);
		String stateCode = Integer.toString(slc);
		String sql = null;
		boolean status = false;
		Integer adminCoverageCode = Integer.valueOf(0);
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();

			sql = "delete from administration_unit_entity ae where  ae.admin_unit_entity_code= " + coverageCode + " and ae.Slc='" + stateCode + "' and isactive=false and isPublish ='D' ";
			query = session.createSQLQuery("select admin_coverage_code from administration_unit_entity where admin_unit_entity_code=:code");
			query.setParameter("code", adminUnitCode);
			adminCoverageCode = (Integer) query.uniqueResult();

			query = session.createSQLQuery(sql);
			query.executeUpdate();
			if (adminCoverageCode > 0) {
				query = session.createSQLQuery("delete from administrative_entity_coverage ac where ac.admin_coverage_code =:adminCoverageCode");
				query.setParameter("adminCoverageCode", adminCoverageCode);
				query.executeUpdate();
			}
			txn.commit();
			status = true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	/**
	 * Added by kirandeep for for publishing admin entity
	 * 
	 * @param adminEntityCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean publishAdminEntity(Integer adminEntityCode) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			query = session.createSQLQuery("update  administration_unit_entity set (isPublish,isactive)=('P',true) where admin_unit_entity_code= :adminEntityCode ");
			query.setParameter("adminEntityCode", adminEntityCode);
			query.executeUpdate();
			txn.commit();
			status = true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	/**
	 * Added by kirandeep for modifying admin unit entity in draft
	 * 
	 * @param dAdminForm
	 * @param parentModify
	 * @return
	 * @throws Exception
	 */
	@Override
	public String modifyDepartmentAdminEntityForDraft(DepartmentAdminForm dAdminForm, char parentModify) throws Exception {
		Session session = null;
		String sqlQuery = null;
		String englishName = dAdminForm.getAdminLevelNameEnglish();
		String localName = dAdminForm.getAdminLevelNameLocal();
		String entityCode = dAdminForm.getAdministrationUnit().toString();
		String slc = dAdminForm.getSlc().toString();
		/* Changes for district User by ripunj on 28-04-2015 */
		String distCode = dAdminForm.getDistrictCode().toString();
		String unitLevelCode = null;
		String parentCode = null;
		if (parentModify == 'Y')
			parentCode = dAdminForm.getParentAdminCode().toString();
		if (parentModify == 'Y') {
			unitLevelCode = dAdminForm.getAdminUnitLevelCode().toString();
			sqlQuery = "update administration_unit_entity set admin_entity_name_english='" + englishName + "', admin_entity_name_local='" + localName + "', parent_unit_entity_code ='" + parentCode + "',admin_unit_level_code ='" + unitLevelCode + "',districtCode='" + distCode 
					+ "' where admin_unit_entity_code=" + entityCode + " and slc='" + slc + "' and isactive=false";
		} else {
			sqlQuery = "update administration_unit_entity set admin_entity_name_english='" + englishName + "', admin_entity_name_local='" + localName + "' where admin_unit_entity_code=" + entityCode + " and slc='" + slc + "' and districtCode='"
					+ distCode + "' and isactive=false";
		}

		try {
			session = sessionFactory.openSession();
			session.createSQLQuery(sqlQuery).executeUpdate();

		} catch (Exception e) {
			sqlQuery = null;
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return sqlQuery;
	}

	/**
	 * added by kirandeep for the getting the details of coveranges of draft
	 * admin entity
	 * 
	 * @param entityCode
	 * @param slc
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	/*Modified by pooja on 28-10-2015*/
	@Override
	public List<AdministrativeEntityCoverage> getEntityCoverageDetailForDraft(Integer entityCode) throws Exception {
		List<AdministrativeEntityCoverage> administrativeEntityCoverages = new ArrayList<AdministrativeEntityCoverage>();
		Session session = null;
		try {
			Query query = null;
			session = sessionFactory.openSession();
			query = session.getNamedQuery("adminCoverageDetailsForDraft");
			query.setParameter("adminEntityCode", entityCode);
			administrativeEntityCoverages = query.list();
		} catch (Exception e) {
			log.debug("Exception " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return administrativeEntityCoverages;
	}

	/**
	 * added by kirandeep for saving the coverage of draft admin unit entity
	 * 
	 * @param adminCoverage
	 * @param wardCoverage
	 * @param deletedCoverage deleted by pooja on 14-08-2015
	 * @param coverageCode
	 * @param entityCode
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean saveDeptAdminCoverageForDraft(String adminCoverage, String wardCoverage, Integer coverageCode, Integer entityCode, Session session) throws Exception {
		Query query = null;
		Boolean status = true;
		String sqlQuery = null;
		if (adminCoverage != null && adminCoverage.isEmpty())
			adminCoverage = null;
		if (wardCoverage != null && wardCoverage.isEmpty())
			wardCoverage = null;
		try {
			if (adminCoverage != null || wardCoverage != null) {
				if (coverageCode != null && coverageCode == 0) {
					query = session.createSQLQuery("select CAST(nextval('adminCoverageCode_seq') AS integer) ");
					coverageCode = (Integer) query.uniqueResult();
					sqlQuery = "update administration_unit_entity set admin_coverage_code='" + coverageCode + "' where admin_unit_entity_code=" + entityCode + " and isactive=false and isPublish ='D' ";
					session.createSQLQuery(sqlQuery).executeUpdate();

				}
				query = session.createSQLQuery("select * from administrative_coverage_fn(:adminCoverage,:coverageCode,:wardCoverage)").setParameter("coverageCode", coverageCode, Hibernate.INTEGER)
						.setParameter("adminCoverage", adminCoverage, Hibernate.STRING).setParameter("wardCoverage", wardCoverage, Hibernate.STRING);
				status = (Boolean) query.uniqueResult();
			}

		} catch (Exception e) {
			status = false;
			log.debug("Exception" + e);
		}
		return status;
	}

	/**
	 * To Get AdminLevel For District User
	 * 
	 * @author Ripunj on 24-04-2015
	 * @param slc
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getAdminLevelForDistrictUser(Integer slc) throws Exception {
		Session session = null;
		List<DeptAdminUnit> departmentAdminForm = new ArrayList<DeptAdminUnit>();
		try {
			StringBuilder queryBuilder = new StringBuilder("select admin_unit_level_code  as adminUnitCode, " + "unit_level_name_english      as adminLevelNameEng, " + "parent_admin_unit_level_code as parentAdminCode "
					+ "from get_admin_level_fn(:sCode)");
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(queryBuilder.toString());
			query.setParameter("sCode", slc);
			query.addScalar("adminUnitCode").addScalar("adminLevelNameEng").addScalar("parentAdminCode");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			departmentAdminForm = query.list();
		} catch (Exception e) {
			log.debug("Exceptin " + e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return departmentAdminForm;
	}

	/**
	 * To get Admin Entity for District User
	 * 
	 * @author Ripunj on 27-04-2015
	 * @param entity_code
	 * @param admin_level
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnitEntity> getAdminEntityForDistrictUser(Integer entity_code, Integer admin_level) throws Exception {
		Session session = null;
		List<DeptAdminUnitEntity> departmentAdminForm = new ArrayList<DeptAdminUnitEntity>();
		try {
			StringBuilder queryBuilder = new StringBuilder("select admin_unit_entity_code  as adminUnitEntityCode, " + "admin_unit_level_code      as adminUnitLevelCode, " + "admin_entity_name_english as adminEntityNameEnglish, "
					+ "admin_entity_name_local as adminEntityNameLocal, " + "parent_unit_entity_Code as parentAdminUnitEntityCode, " + "from get_admin_entity_fn(:entity_code,:admin_level)");
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(queryBuilder.toString());
			query.setParameter("entity_code", entity_code);
			query.setParameter("admin_level", admin_level);
			query.addScalar("adminUnitEntityCode").addScalar("adminUnitLevelCode").addScalar("adminEntityNameEnglish").addScalar("adminEntityNameLocal").addScalar("parentAdminUnitEntityCode");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnitEntity.class));
			departmentAdminForm = query.list();
		} catch (Exception e) {
			log.debug("Exceptin " + e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return departmentAdminForm;
	}

	/**
	 * To get Unit level names for District User
	 * 
	 * @author Ripunj on 27-04-2015
	 * @param adminUnitCode
	 * @param slc
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getUnitLevelNamesForLocalBodyDistrictUser(Integer adminUnitCode, Integer districtCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Integer parentAdminUnitCode = Integer.valueOf(0);
		String queryBuilder = null;
		List<DeptAdminUnit> parentEntityUnits = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select parent_admin_unit_level_code from administration_unit_level where admin_unit_level_code=:adminUnitCode ");
			query.setParameter("adminUnitCode", adminUnitCode);
			parentAdminUnitCode = (Integer) query.uniqueResult();
			if (parentAdminUnitCode == 1) {
				queryBuilder = "select case when st.state_code  in (select local_body_code from get_draft_used_lb_lr_temp(st.state_code,'S')) "
						+ "then cast('F' as character)  else cast('A' as character) end as operation_state ,state_code as parentAdminCode,state_name_english as adminLevelNameEng from state st where slc in (select slc from district where district_code=:districtCode and isactive=true)  and isactive=true order by adminLevelNameEng";
			} else if (parentAdminUnitCode == 2) {
				queryBuilder = "select case when dt.district_code  in (select local_body_code from get_draft_used_lb_lr_temp(dt.district_code,'D')) "
						+ "then cast('F' as character)  else cast('A' as character) end as operation_state ,district_code as parentAdminCode,district_name_english as adminLevelNameEng from district dt where  district_code=:districtCode and isactive=true order by adminLevelNameEng";
			} else if (parentAdminUnitCode == 3) {
				queryBuilder = "select case when sd.subdistrict_code  in (select local_body_code from get_draft_used_lb_lr_temp(sd.subdistrict_code,'T')) "
						+ "then cast('F' as character)  else cast('A' as character) end as operation_state , subdistrict_code as parentAdminCode,subdistrict_name_english as adminLevelNameEng from subdistrict  sd where dlc in(select dlc from district where  district_code=:districtCode and isactive=true) and isactive order by adminLevelNameEng";
			} else if (parentAdminUnitCode == 4) {
				queryBuilder = "select  case when vl.village_code  in (select local_body_code from get_draft_used_lb_lr_temp(vl.village_code,'V')) "
						+ "then cast('F' as character)  else cast('A' as character) end as operation_state ,village_code as parentAdminCode,village_name_english as adminLevelNameEng from village vl where  dlc in(select dlc from district where  district_code=:districtCode and isactive=true) and isactive=true order by adminLevelNameEng";
			} else if (parentAdminUnitCode == 5) {
				queryBuilder = "select  case when bl.block_code  in (select local_body_code from get_draft_used_lb_lr_temp(bl.block_code,'B')) "
						+ "then cast('F' as character)  else cast('A' as character) end as operation_state , block_code as parentAdminCode,block_name_english as adminLevelNameEng from block bl where dlc in(select dlc from district where  district_code=:districtCode and isactive=true) and isactive order by adminLevelNameEng";
			} else {
				// queryBuilder =
				// "select cast('A' as character) as  operation_state, admin_unit_entity_code as parentAdminCode,admin_entity_name_english as adminLevelNameEng from administration_unit_entity where  admin_unit_level_code=:adminUnitLevelCode and isactive=true order by adminLevelNameEng";
				queryBuilder = "select cast('A' as character) as  operation_state, admin_unit_entity_code as parentAdminCode,admin_entity_name_english as adminLevelNameEng ,admin_entity_name_local as adminLevelNameLocal  from get_admin_entity_fn(:districtCode,:adminUnitCode)  order by adminLevelNameEng";
			}
			query = session.createSQLQuery(queryBuilder);
			if (parentAdminUnitCode == 1 || parentAdminUnitCode == 2 || parentAdminUnitCode == 3 || parentAdminUnitCode == 4 || parentAdminUnitCode == 5)
				query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
			else {
				query.setParameter("districtCode", districtCode, Hibernate.INTEGER);
				query.setParameter("adminUnitCode", adminUnitCode, Hibernate.INTEGER);
			}
			/*
			 * query.setParameter("adminUnitLevelCode", parentAdminUnitCode,
			 * Hibernate.INTEGER);
			 */
			query.addScalar("operation_state");
			query.addScalar("parentAdminCode").addScalar("adminLevelNameEng");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			parentEntityUnits = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return parentEntityUnits;
	}

	/**
	 * To get Unit Admin Entities for District User
	 * 
	 * @author Ripunj on 28-04-2015
	 * @param districtCode
	 * @param levelCode
	 * @param entityCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnitEntity> getUnityEntityByParentLevelForDistrictUser(Integer districtCode, int levelCode, int entityCode) throws Exception {
		Session session = null;
		List<DeptAdminUnitEntity> deptAdminUnitEntities = new ArrayList<DeptAdminUnitEntity>();
		StringBuilder queryBuilder = new StringBuilder("from DeptAdminUnitEntity de where de.districtCode=:district_Code and isactive=true and ");
		try {

			if (entityCode > 0)
				queryBuilder.append("de.adminUnitLevelCode=:levelCode and de.parentAdminUnitEntityCode=:entiycode order by adminUnitEntityCode");
			else
				queryBuilder.append("de.adminUnitLevelCode=:levelCode order by adminUnitEntityCode");
			session = sessionFactory.openSession();
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("district_Code", districtCode);
			query.setParameter("levelCode", levelCode);
			if (entityCode > 0)
				query.setParameter("entiycode", entityCode);
			deptAdminUnitEntities = query.list();

		} catch (Exception e) {
			log.debug("Exceptin " + e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return deptAdminUnitEntities;
	}

	/**
	 * To get Dept Admin Entities By District
	 * 
	 * @author Ripunj on 28-04-2015
	 * @param slc
	 * @param districtCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnitEntity> getDeptAdminEntityForDistrict(Integer slc, Integer districtCode) throws Exception {
		Query query = null;
		Session session = null;
		List<DeptAdminUnitEntity> deptAdminEntity = new ArrayList<DeptAdminUnitEntity>();
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from DeptAdminUnitEntity de where de.slc=:sCode and de.districtCode=:district_Code and isactive=true order by adminUnitEntityCode");
			query.setParameter("sCode", slc, Hibernate.INTEGER);
			query.setParameter("district_Code", districtCode, Hibernate.INTEGER);
			deptAdminEntity = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return deptAdminEntity;
	}

	/* added by pooja on 23-06-2015 for capture Org Unit Details */
	@SuppressWarnings("unchecked")
	public List<OrganizationUnit> getOrganizationUnitDetails(Integer orgUnitCode) throws Exception {

		Session session = null;
		SQLQuery query = null;
		List<OrganizationUnit> listOrgUnit = new ArrayList<OrganizationUnit>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("select org_unit_code as orgUnitCode,org_unit_name as orgUnitName,org_spec_code as orgSpecCode,email as email,phoneno as phoneNo,address1 as address1,address2 as address2,address3 as address3,pin_code as pinCode,code as code, CAST(level AS character varying) as level"
							+ " from org_units where org_unit_code =:orgUnitCode and isactive is true");
			query.setParameter("orgUnitCode", orgUnitCode, Hibernate.INTEGER);
			query.addScalar("orgUnitCode");
			query.addScalar("orgUnitName");
			query.addScalar("orgSpecCode");
			query.addScalar("address1");
			query.addScalar("address2");
			query.addScalar("address3");
			query.addScalar("email");
			query.addScalar("phoneNo");
			query.addScalar("pinCode");
			query.addScalar("code");
			query.addScalar("level");
			query.setResultTransformer(Transformers.aliasToBean(OrganizationUnit.class));
			listOrgUnit = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listOrgUnit;
	}

	/* modified by pooja on 26-06-2015 */
	public Object[] getVlcTlcDlc(Integer villageCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Object[] vlcTlcDlc = null;

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select vlc,tlc,dlc,slc from village  where village_code =:villageCode and isactive is true");
			query.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List listVlcTlcDlc = query.list();
			if (null != listVlcTlcDlc && !listVlcTlcDlc.isEmpty()) {
				vlcTlcDlc = (Object[]) listVlcTlcDlc.get(0);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vlcTlcDlc;
	}

	public Integer getSlcByLbOrVillageCode(Integer code) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Integer slc = 0;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select slc from localbody where local_body_code =:code and isactive");
			query.setParameter("code", code, Hibernate.INTEGER);
			slc = (Integer) query.uniqueResult();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return slc;
	}

	/* added by pooja on 02-07-2015 */
	@Override
	public boolean checkExistingOrgUnitName(Integer stateCode, String orgUnitName) throws Exception {
		Session session = null;
		boolean isDuplicate = true;
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "select orgunitname_exist_fn(:stateCode, :orgUnitName )";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("orgUnitName", orgUnitName, Hibernate.STRING);
			isDuplicate = (Boolean) query.uniqueResult();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isDuplicate;
	}
	
	/**
	 * added by pooja on 07-08-2015
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Integer[] getLocalBobyTypeListByAdminUnitCode(Integer adminUnitCode, Integer slc, String status) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Integer[] lbTypes = new Integer[10];
		try {
			session = sessionFactory.openSession();
			StringBuilder str = new StringBuilder("select distinct local_body_type_code from localbody where local_body_code in (select entity_code from");
			if ("D".equals(status))
				str.append(" get_admin_coverage_detail_for_draft");
			if ("P".equals(status))
				str.append(" get_admin_coverage_Detail");
			str.append("(:adminUnitCode) where entity_type='G' and lb_code_for_ward is null) and slc=:slc and isactive");
			query = session.createSQLQuery(str.toString());
			query.setParameter("slc", slc);
			query.setParameter("adminUnitCode", adminUnitCode);
			List lbTypeList = query.list();
			if (!lbTypeList.isEmpty() && lbTypeList != null) {
				for (int i = 0; i < lbTypeList.size(); i++)
					lbTypes[i] = (Integer) lbTypeList.get(i);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbTypes;
	}
	/**
	 *  added by pooja on 14-08-2015 
	 */
	@Override
	public Integer getdistrictByadminUnitCodeAndParentEntityCode(Integer adminUnitCode, Integer parentEntityCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Integer parentAdminUnitCode = Integer.valueOf(0);
		String queryBuilder = null;
		Integer districtCode = 0;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DeptAdminUnit.class);
			criteria.add( Restrictions.eq("adminUnitCode", adminUnitCode));
			criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
			
			List<DeptAdminUnit> DeptAdminUnitList = criteria.list();
			if(DeptAdminUnitList!=null && !DeptAdminUnitList.isEmpty()){
				DeptAdminUnit deptAdminUnit=DeptAdminUnitList.get(0);
				if(deptAdminUnit.getParentCategory()!='G'){
					query = session.createSQLQuery("select parent_admin_unit_level_code from administration_unit_level where admin_unit_level_code=:adminUnitCode ");
					query.setParameter("adminUnitCode", adminUnitCode);
					parentAdminUnitCode = (Integer) query.uniqueResult();
					if (parentAdminUnitCode == 2) {
						districtCode = parentEntityCode;
					} else if (parentAdminUnitCode == 3) {
						queryBuilder = "select dlc from subdistrict where tlc=:parentEntityCode and isactive";
					} else if (parentAdminUnitCode == 4) {
						queryBuilder = "select dlc from village where vlc=:parentEntityCode and isactive";
					} else if (parentAdminUnitCode == 5) {
						queryBuilder = "select dlc from block where blc=:parentEntityCode and isactive";
					}
					if (parentAdminUnitCode == 3 || parentAdminUnitCode == 4 || parentAdminUnitCode == 5) {
						query = session.createSQLQuery(queryBuilder);
						query.setParameter("parentEntityCode", parentEntityCode);
						districtCode = (Integer) query.uniqueResult();
					}
				}
				
			}
			
			
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtCode;
	}
	
	/**
	 * to check org_level_specific_name exist within state
	 * @author Pooja
	 * @since 16-09-2015
	 * @param stateCode
	 * @param orgLevelSpecificName
	 * 
	 */
	@Override
	public boolean checkExistOrgLevelSpecificName(Integer stateCode, String orgLevelSpecificName) throws Exception {
		Session session = null;
		boolean isDuplicate = false;
		Integer countResult = 0;
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "with temp as(select distinct oll.org_level_specific_name " +
					"from organization org,org_located_at_levels oll where oll.isactive " +
					"and upper(btrim(replace(replace(replace(oll.org_level_specific_name,' ','<>'),'><',''),'<>',' ')))= " +
					"upper(btrim(replace(replace(replace(:orgLevelSpecificName,' ','<>'),'><',''),'<>',' '))) " +
					"and org.slc=:stateCode and org.isactive  and org.olc=oll.olc) " +
					"select cast(count(*) as integer) from temp";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("orgLevelSpecificName", orgLevelSpecificName, Hibernate.STRING);
			countResult = (Integer) query.uniqueResult();
			if(countResult > 0) {
				isDuplicate = true;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isDuplicate;
	}
	/**
	 * code use to rename org_level_specific_name
	 * @author Pooja
	 * @since 18-09-2015
	 * @param orgLocatedAtLevelsForm
	 * @param session
	 * @return
	 */
	public String changeLvlSpecificNameOrg(OrgLocatedAtLevelsForm orgLocatedAtLevelsForm,HttpSession session) throws Exception{
		Session hibsession = null;
		java.sql.Timestamp effectiveTimeStampDate = null;
		java.sql.Timestamp orderTimeStampDate = null;
		java.sql.Timestamp gazPubTimeStampDate = null;
		String orderNo = null;
		Query query = null;
		ChangeLvlSpecificNameOrg changeLvlSpecificNameOrg = new ChangeLvlSpecificNameOrg();
		String resultFlag = null;
		try{
		hibsession = sessionFactory.openSession();
		if(!("").equals(orgLocatedAtLevelsForm.getOrderNo()))
			orderNo = orgLocatedAtLevelsForm.getOrderNo();
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		Date effectiveDate = orgLocatedAtLevelsForm.getOrdereffectiveDate();
		Date orderDate = orgLocatedAtLevelsForm.getOrderDate();
		Date gazPubDate = orgLocatedAtLevelsForm.getGazPubDate();
		if(effectiveDate != null)
		effectiveTimeStampDate = new Timestamp(effectiveDate.getTime());
		if(orderDate!=null)
		orderTimeStampDate = new Timestamp(orderDate.getTime());
		if(gazPubDate!=null)
		gazPubTimeStampDate = new Timestamp(gazPubDate.getTime());
		if(orderNo != null && !("").equals(orderNo))
			orgLocatedAtLevelsForm.setOrderPath("govtOrderUpload");
		query = hibsession.getNamedQuery("ChangeOrgLvlSpecificNameQuery").setParameter("orgLocatedLevelCode",orgLocatedAtLevelsForm.getOrgLocatedLevelCode(),Hibernate.INTEGER).setParameter("userId",userId,Hibernate.INTEGER)
				.setParameter("orgLevelSpecificName",orgLocatedAtLevelsForm.getOrgLevelSpecificName(),Hibernate.STRING).setParameter("effectiveTimeStampDate",effectiveTimeStampDate,Hibernate.TIMESTAMP).setParameter("orderNo",orderNo,Hibernate.STRING)
				.setParameter("orderTimeStampDate", orderTimeStampDate,Hibernate.TIMESTAMP).setParameter("orderCode",orgLocatedAtLevelsForm.getOrderCode(),Hibernate.INTEGER).setParameter("gazPubTimeStampDate",gazPubTimeStampDate,Hibernate.TIMESTAMP)
				.setParameter("orderPath",orgLocatedAtLevelsForm.getOrderPath(),Hibernate.STRING);
		changeLvlSpecificNameOrg = (ChangeLvlSpecificNameOrg)query.list().get(0);
		hibsession.flush();
		if (hibsession.contains(query)) {
			hibsession.evict(query);
		}

		return changeLvlSpecificNameOrg.getChange_level_specific_name_org_fn();
	} catch (Exception e) {
		LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		resultFlag = null;
	} finally {
		try {
			hibsession.close();
		} catch (Exception e) {
			resultFlag = null;
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		}
	}
	return resultFlag;
	}
	/**
	 * Code use for getting parent admin unit level code by admin unit level code.
	 * @author Pooja
	 * @since 20-10-2015
	 * @param adminUnitLevelCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getParentAdmnUnitLvlCode(Integer adminUnitLevelCode) throws Exception{
		Session session = null;
		SQLQuery query = null;
		Integer parentAdminUnitCode = 0;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select parent_admin_unit_level_code from administration_unit_level where admin_unit_level_code=:adminUnitLevelCode and isactive");
			query.setParameter("adminUnitLevelCode", adminUnitLevelCode);
			parentAdminUnitCode = (Integer) query.uniqueResult();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return parentAdminUnitCode;
	}
	
	/**
	 * @author Pooja
	 * @since 20-10-2015
	 * @param adminUnitLvlCode
	 * @param parentUnitEntityCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer getStateByParentUnitEntityCode(Integer adminUnitLvlCode,Integer parentUnitEntityCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		Integer parentAdminUnitCode = 0;
		Integer stateCode = 0;
		String queryBuilder = "";
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select parent_admin_unit_level_code from administration_unit_level where admin_unit_level_code=:adminUnitLvlCode and isactive");
			query.setParameter("adminUnitLvlCode", adminUnitLvlCode);
			parentAdminUnitCode = (Integer) query.uniqueResult();
			if(parentAdminUnitCode == 2){
				queryBuilder = "select slc from district where dlc=:parentUnitEntityCode and isactive";
			}else if(parentAdminUnitCode == 3){
				queryBuilder = "select slc from district where dlc=(select dlc from subdistrict where tlc=:parentUnitEntityCode and isactive) and isactive";
			}else if(parentAdminUnitCode == 4){
				queryBuilder = "select slc from village where vlc=:parentUnitEntityCode and isactive";
			}else if(parentAdminUnitCode == 5){
				queryBuilder = "select slc from district where dlc=(select dlc from block where blc=:parentUnitEntityCode and isactive) and isactive";
			}
			if (parentAdminUnitCode == 2 || parentAdminUnitCode == 3 || parentAdminUnitCode == 4 || parentAdminUnitCode == 5) {
			query = session.createSQLQuery(queryBuilder);
			query.setParameter("parentUnitEntityCode",parentUnitEntityCode);
			stateCode = (Integer)query.uniqueResult();
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateCode;
	}
	
	@Override
	public List<OrganizationUnit> getOrganizationeUnitsByorglocatedlevelcode(Integer orglocatedlevelcode) throws Exception {

		Session session = null;
		List<OrganizationUnit> listOrgUnitLevel = null;
		try {
			session = sessionFactory.openSession();
			Query query=session.createQuery("from OrganizationUnit where orgLocatedLevelCode=:orglocatedlevelcode and isActive=true");
			query.setParameter("orglocatedlevelcode", orglocatedlevelcode);
			listOrgUnitLevel = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listOrgUnitLevel;
	}
	
	/*added by deepti on 11-05-2016 for unit overlapping exist or not*/
	@Override
	public Character getOverlappingExist(Integer adminUnitlabelcode) throws Exception{
		Character overlapCode ='N';
		Session session=null;
		try {
			session = sessionFactory.openSession();
			Query query=session.createSQLQuery("select overlapping_unit from administration_unit_level where admin_unit_level_code =:adminUnitlabelcode");
			query.setParameter("adminUnitlabelcode", adminUnitlabelcode);
			overlapCode = (Character) query.list().get(0);
		} catch (Exception e) {
			log.error("Exception " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return overlapCode;
	}
	
	/*added by deepti on 16-05-2016  for getting entity list */
	@Override
	public List<DeptAdminUnitEntity> getEntityList(Integer adminUnitLabelCode) throws Exception {

		Session session = null;
		List<DeptAdminUnitEntity> entityList = null;
		try {
			session = sessionFactory.openSession();
			Query query=session.createQuery("from DeptAdminUnitEntity where adminUnitLevelCode=:adminUnitLabelCode and isActive=true");
			query.setParameter("adminUnitLabelCode", adminUnitLabelCode);
			entityList = query.list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return entityList;
	}
	
	/**
	 * added by Sunita on 24-11-2016
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getDeptAdminUnitsRevenue(int slc, int type) throws Exception {
		Session session = null;
		List<DeptAdminUnit> deptadminUnits = null;

		try {
			StringBuilder stringBuilder = new StringBuilder("from DeptAdminUnit du");
			if (type == Integer.parseInt(DepartmentConstent.CREATE_ADMINISTRATIVE_UNIT.toString())) {
				if (slc == 0) {
					stringBuilder.append(" where (du.slc=0) ");
				} else {
					stringBuilder.append(" where ((du.adminUnitCode>=1 and du.adminUnitCode<=5) or du.slc=:sCode )");
				}
			} else {
				if (type == Integer.parseInt(DepartmentConstent.MANAGE_ADMINISTRATIVE_UNIT.toString())) {
					if (slc == 0) {
						stringBuilder.append(" where (du.slc=0 and revenueEntity=true  ) ");
					} else {
						stringBuilder.append(" where (du.slc=:sCode)");
					}
				}

			}
			stringBuilder.append(" and isactive=true order by adminLevelNameEng");
			session = sessionFactory.openSession();
			Query query = session.createQuery(stringBuilder.toString());
			if ((type == Integer.parseInt(DepartmentConstent.MANAGE_ADMINISTRATIVE_UNIT.toString()) && slc != 0) || (type == Integer.parseInt(DepartmentConstent.CREATE_ADMINISTRATIVE_UNIT.toString()) && slc != 0)) {
				query.setParameter("sCode", slc);
			}
			deptadminUnits = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return deptadminUnits;
	}
	
	@Override
	public  Map<String, Object> getOrgUnitswiseDeptDetails(Integer orgUnitCode,boolean isParentOrgUnit) throws Exception {
		Session session = null;
		Map<String,Object> orgUnitwiseDeptDetails=null;
		try {
			orgUnitwiseDeptDetails=new HashMap<String,Object>();
			session = sessionFactory.openSession();
			
			
			SQLQuery query=session.createSQLQuery("select olc from org_located_at_levels where org_located_level_code in"
			+ " (select org_located_level_code from org_units where org_unit_code =:orgUnitCode and isactive) and isactive");
			
			query.setParameter("orgUnitCode", orgUnitCode);
			Integer olc=(Integer)query.uniqueResult();
			if(olc!=null){
				orgUnitwiseDeptDetails.put("olc", olc);
			
				StringBuilder queryBuild = new StringBuilder("select admin_unit_level_code as adminUnitCode,");
				queryBuild.append("unit_level_name_english as adminLevelNameEng ,parent_admin_unit_level_code as parentAdminCode,");
				queryBuild.append("slc from administration_unit_level where admin_unit_level_code in(select located_at_level "); 
				queryBuild.append(" from org_located_at_levels where olc in(:olc) and isactive and located_at_level>");
				if(isParentOrgUnit){
					queryBuild.append("1");
				}else{
					queryBuild.append("5");
				}
				queryBuild.append(")");
				query = session.createSQLQuery(queryBuild.toString());
				query.setParameter("olc", olc);
				query.addScalar("adminUnitCode");
				query.addScalar("adminLevelNameEng");
				query.addScalar("parentAdminCode");
				query.addScalar("slc");
				query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
				orgUnitwiseDeptDetails.put("deptwiseAdminUnitLevelList", query.list());
				
				if(isParentOrgUnit){
					orgUnitwiseDeptDetails.put("UserEntityDefinerOrg", this.getMinistryDetail(olc));
				}
			}
			
			
		}catch(Exception e){
		log.error("Department wise Entity", e);
		e.printStackTrace();
		}finally {
		
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return orgUnitwiseDeptDetails;
	}
	
	@Override
	public Character getParentCategory(Integer adminUnitLevelCode,Session session )throws Exception{
		Query query = session.createSQLQuery("select parent_category from administration_unit_level where admin_unit_level_code=:adminUnitLevelCode  and isactive");
		query.setParameter("adminUnitLevelCode", adminUnitLevelCode);
		return ((Character)query.uniqueResult());
	}
	
	@Override
	public boolean isOrganizationExist(Integer olc) throws Exception {
		Session session = null;
		Boolean isExistOrg=false;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select case when count(1)>0 then true else false end from organization where olc=:olc and isactive");
			query.setParameter("olc", olc, Hibernate.INTEGER);

			isExistOrg = (Boolean) query.uniqueResult();
		} catch (Exception e) {
			log.debug("OrganizationDAOImpl-->organizationExist" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isExistOrg;
	}
	
	@Override
	public boolean isOrgLocatedLevelCodeExist(Integer olc,Integer orgLocatedLevelCode) throws Exception {
		Session session = null;
		Boolean isExistOrg=false;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select case when count(1)>0 then true else false end  from org_located_at_levels where org_located_level_code"
			+ " =:orgLocatedLevelCode and  olc=:olc and isactive");
			query.setParameter("olc", olc, Hibernate.INTEGER);
			query.setParameter("orgLocatedLevelCode", orgLocatedLevelCode, Hibernate.INTEGER);
			isExistOrg = (Boolean) query.uniqueResult();
		} catch (Exception e) {
			log.debug("OrganizationDAOImpl-->organizationExist" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isExistOrg;
	}
	
	
	@Override
	public List<Organization> getOrganizationListbyOrgType(Integer orgTypeCode,Integer stateCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<Organization> organizationList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryStr =new StringBuilder("from Organization org where org.organizationType.orgTypeCode=:orgTypeCode and org.isactive=true ");
			if(stateCode!=null && stateCode>0){
				queryStr.append("  and org.slc=:stateCode");
			}
			criteria = session.createQuery(queryStr.toString());
			if(stateCode!=null && stateCode>0){
				criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			}
			criteria.setParameter("orgTypeCode", orgTypeCode, Hibernate.INTEGER);
			organizationList = criteria.list();
		} catch (HibernateException e) {
			log.error(" OrganizationDAOImpl Exception-->getOrganizationListbyOrgType method" + e);
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;
		// return
		// sessionFactory.getCurrentSession().createQuery("from Organization org where org.organizationType.orgTypeCode=1 and org.isactive=true and org.stateCode="+stateCode).list();
	}
	
	
	@Override
	public List<Object> getOrganizationAtLevelbyOrgCode(int orgCode) throws Exception{
		Session session = null;
		List<Object> objectList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder strQuery = new StringBuilder("SELECT * FROM get_organization_at_levels_by_org_code(?) order by org_located_level_code");
			SQLQuery query = (SQLQuery) session.createSQLQuery(strQuery.toString()).setParameter(0, orgCode);
//			query.addScalar("key").addScalar("value").addScalar("levelWiseOfficeName");
			objectList = query.list();
			

		} catch (Exception e) {
			log.error(" OrganizationDAOImpl Exception-->getOrganizationAtLevelbyOrgCode method" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return objectList;
		
	}
	
	
	/**
	 * Added by kirandeep for for publishing admin entity
	 * 
	 * @param adminEntityCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean publishAdminEntityAll(List<Integer> adminEntityCodeList) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			query = session.createSQLQuery("update  administration_unit_entity set (isPublish,isactive)=('P',true) where admin_unit_entity_code in(:adminEntityCodeList) ");
			query.setParameterList("adminEntityCodeList", adminEntityCodeList);
			query.executeUpdate();
			txn.commit();
			status = true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}
	
	@Override
	public List<Organization> getOrganizationDetails() throws Exception {
		Session session = null;
		Query query = null;
		List<Organization> organizationList = null;
		try {
			System.out.println("report Dao impl +++++++++++++++++++++++");

			session = sessionFactory.openSession();
			 query = session.createQuery("from Organization where slc= 0 and orgTypeCode NOT IN (1,2) and isactive = true ");
			 organizationList=query.list();
		
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;
	}


	public boolean updateParentAdminEntityDetailsCall(DepartmentAdminForm adminUnitView ,Long userId) throws Exception {
		Session session = null;
		Query query = null;
		Boolean status=false;
		try {
			Integer  adminUnitEntityCode   =adminUnitView.getAdminUnitEntityCode();
			Integer  parentAdminUnitEntityCode   = adminUnitView.getParentAdminUnitEntityCode();
			Integer  adminUnitLevelCode  =adminUnitView.getAdminUnitLevelCode();
			Integer  userId1 = userId.intValue();
			
			session = sessionFactory.openSession();
			 query = session.createSQLQuery("select * from change_parent_of_admin_unit_entity(:ulc , :auec , :plc , :userId1) ");
			
			 query.setParameter("auec", adminUnitEntityCode, Hibernate.INTEGER);
			 query.setParameter("ulc", adminUnitLevelCode, Hibernate.INTEGER);
			 query.setParameter("plc", parentAdminUnitEntityCode, Hibernate.INTEGER);
			 query.setParameter("userId1", userId1, Hibernate.INTEGER);
				 
			 
			 status = (Boolean) query.uniqueResult();
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}
}
