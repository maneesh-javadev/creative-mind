package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.ChildExtendDept;
import in.nic.pes.lgd.bean.Country;
import in.nic.pes.lgd.bean.DepartmentMapping;
import in.nic.pes.lgd.bean.DepartmentMappingFrom;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.bean.ExistDeptHierarchy;
import in.nic.pes.lgd.bean.ExistingDeptMapping;
import in.nic.pes.lgd.bean.FetchOrgLocatedLevel;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.LgdOrganisationListByOrgType;
import in.nic.pes.lgd.bean.LgdOrganizationAtLevels;
import in.nic.pes.lgd.bean.LgdSubordinateOrgUnits;
import in.nic.pes.lgd.bean.LgdUpperHierarchyLevel;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationCoverage;
import in.nic.pes.lgd.bean.OrganizationCoverageDetail;
import in.nic.pes.lgd.bean.OrganizationPK;
import in.nic.pes.lgd.bean.OrganizationType;
import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.bean.VillageGISMapStatus;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.constant.DepartmentConstent;
import in.nic.pes.lgd.dao.AdministrativeDepratmentDAO;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.OrganizationDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.entities.DraftMaster;
import in.nic.pes.lgd.draft.entities.GovermentDetailDraft;
import in.nic.pes.lgd.draft.entities.SubdistrictDraft;
import in.nic.pes.lgd.draft.form.DepartmentOrgListDto;
import in.nic.pes.lgd.draft.form.LocalBodyDetailDto;
import in.nic.pes.lgd.forms.AdminOrgDeptForm;
import in.nic.pes.lgd.forms.DepartmentDataForm;
import in.nic.pes.lgd.interceptor.LanguageListener;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.restructure.reporting.dao.impl.ViewEntityDetailsDaoImpl;
import in.nic.pes.lgd.restructure.reporting.entities.GenericReportingEntity;
import in.nic.pes.lgd.service.CommonService;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.batik.svggen.font.table.ScriptList;
import org.apache.log4j.Logger;
import org.apache.velocity.runtime.directive.Parse;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.exolab.castor.xml.handlers.ValueOfFieldHandler;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Service;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;
import com.cmc.lgd.localbody.entities.LocalBodyEntityDetails;
import com.cmc.lgd.localbody.entities.LocalBodyTable;


import pes.attachment.util.AttachedFilesForm;

@Service
public class AdministrativeDepratmentDAOImpl implements AdministrativeDepratmentDAO { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(AdministrativeDepratmentDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private OrganizationDAO organizationDAO;

	@Autowired
	private DistrictDAO districtDAO;

	@Autowired
	private SubDistrictDAO subDistrictDAO;

	@Autowired
	private VillageDAO villageDAO;

	@Autowired
	CommonService commonService;

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getLandRegionUnitLevel(boolean isCenterUser) {
		Session session = null;
		List<DeptAdminUnit> landRegionDepts = new ArrayList<DeptAdminUnit>();
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuilder = new StringBuilder("from DeptAdminUnit where revenueEntity is true and isactive is true and adminUnitCode>0");
			if (!isCenterUser) {
				queryBuilder.append(" and parentAdminCode <> 0");
			}
			queryBuilder.append(" order by adminUnitCode asc");
			Query query = session.createQuery(queryBuilder.toString());
			landRegionDepts = query.list();
			
			if(isCenterUser) {
				 queryBuilder = new StringBuilder("from DeptAdminUnit where  isactive is true and adminUnitCode=-100");
				 query = session.createQuery(queryBuilder.toString());
				List<DeptAdminUnit>  landRegionDeptsCountry = query.list();
				landRegionDepts.addAll(0,landRegionDeptsCountry);
			}
			
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return landRegionDepts;
	}

	@SuppressWarnings("unchecked")
	public List<DeptAdminUnit> getAdministrativeUnitLevel(Integer parentAdminCode, Integer stateCode) {

		Session session = null;
		Query query=null;
		Character parentCateogry=null;
		List<DeptAdminUnit> listAdminUnitLevel = new ArrayList<DeptAdminUnit>();
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuilder=new StringBuilder();
			
			queryBuilder.append("from DeptAdminUnit where isactive is true and revenueEntity is not true and parentAdminCode=:parentAdminCode");
			if(stateCode>0)
				queryBuilder.append(" and slc=:stateCode ");
			if(parentAdminCode<0 && parentAdminCode>-50){
				parentAdminCode=-(parentAdminCode);
				parentCateogry='G';
			}else if(parentAdminCode>=0 && parentAdminCode<=5){
				parentCateogry='L';
			}else if( parentAdminCode>5) {
				parentCateogry='A';
			}else if(stateCode==0) {
				parentCateogry='C';
			}
			queryBuilder.append(" and parentCategory=:parentCateogry ");
			queryBuilder.append(" order by revenueEntity desc, adminUnitCode asc");
			query = session.createQuery(queryBuilder.toString());
			if(stateCode>0)
				query.setParameter("stateCode", stateCode);
			query.setParameter("parentAdminCode", parentAdminCode);
			query.setParameter("parentCateogry", parentCateogry);
			listAdminUnitLevel = query.list();
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listAdminUnitLevel;
	}
	public List<LBTypeDetails> getLocalBodyTypesDetails(Integer stateCode) {
		// TODO Auto-generated method stub
		Session session = null;
		Query query=null;
		List<LBTypeDetails> lBTypeDetails=new ArrayList<LBTypeDetails>();
		
		List<LBTypeHierarchy> localBodyTypes=null;
		try{
			session=sessionFactory.openSession();
			query = session.getNamedQuery("Dynamic_Local_body_Hierarchy_ALL_TYPE");
			query.setParameter("stateCode", stateCode,Hibernate.INTEGER);
			
			localBodyTypes = query.list();
			if (session != null && session.isOpen()) {
				session.close();
			}
			if(localBodyTypes.size()>0){
				session=sessionFactory.openSession();
			for(LBTypeHierarchy lbh:localBodyTypes){
				query = session.getNamedQuery("Dynamic_Local_body_Type_Details");
				query.setParameter("setupCode",lbh.getLocalBodySetupCode(),Hibernate.INTEGER).setParameter("setupVersion",lbh.getLocalBodySetupVersion(), Hibernate.INTEGER);
				List<LBTypeDetails> lBTypeDetail2=query.list();
				  if(lBTypeDetail2!=null &&lBTypeDetail2.size()>0)
				  lBTypeDetails.addAll(lBTypeDetail2);
				}
			 }
			
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return lBTypeDetails;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getUnitLevelNames(Set<Integer> unitCodes) {
		Session session = null;
		Set<Integer> unitCode=new HashSet<Integer>();
		Set<Integer> localBody=new HashSet<Integer>();
		List<Object[]> listUnitNames = new ArrayList<Object[]>();
		List<Object[]> localBodyList1 = new ArrayList<Object[]>();
		List<Object[]> localBodyList2 = new ArrayList<Object[]>();
		 try {
			
			   Iterator itr=unitCodes.iterator();
			   while(itr.hasNext()){
				 Integer id=  (Integer)itr.next();
			    	 if(id<0){
			    		 localBody.add(-id);
			    	 }
			    	 else{
			    		 unitCode.add(id);
			    	 }
			     }
			session = sessionFactory.openSession();
			session.beginTransaction();
			if(unitCode!=null && unitCode.size()>0){
			String unitCodeQuery = " select admin_unit_level_code, unit_level_name_english from Administration_Unit_Level where isactive " + " and admin_unit_level_code in(:unitCode) order by unit_level_name_english";
			Query query1 = session.createSQLQuery(unitCodeQuery);
			query1.setParameterList("unitCode", unitCode, Hibernate.INTEGER);
			listUnitNames = query1.list();
			session.getTransaction().commit();
			}
			session.beginTransaction();
			String localBodyQuery = " select local_body_type_code, local_body_type_name from local_body_type where  local_body_type_code in(:localBody) and isactive order by local_body_type_name";
			Query query2 = session.createSQLQuery(localBodyQuery);
			query2.setParameterList("localBody", localBody, Hibernate.INTEGER);
			localBodyList1=query2.list();
			for(int i=0;i<localBodyList1.size();i++){
				
				Object[] obj=new Object[2];
				obj[0]=-Integer.parseInt(localBodyList1.get(i)[0].toString());
				obj[1]=localBodyList1.get(i)[1];
				localBodyList2.add(obj);
			}
			listUnitNames.addAll(localBodyList2);
		
			
			
			session.getTransaction().commit();
		}catch(Exception ex) {
			
		}
		 finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listUnitNames;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnitEntity> getAdminEntities(Integer unitLevelCode) {
		Session session = null;
		List<DeptAdminUnitEntity> listAdminEntities = new ArrayList<DeptAdminUnitEntity>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "from DeptAdminUnitEntity where isactive is true and adminUnitLevelCode=:unitLevelCode order by adminEntityNameEnglish";
			Query query = session.createQuery(queryBuilder);
			query.setParameter("unitLevelCode", unitLevelCode);
			listAdminEntities = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listAdminEntities;
	}

	@Override
	public boolean saveAdminDepartmentProcess(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, boolean isCenterUser,Long userId,Boolean isOrganization) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			
			// True only in case of first or single level department
			boolean isTopDept = true;
			Integer sortOrder = Integer.parseInt(DepartmentConstent.SHORT_ORDER_START_SEQ.toString());
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Organization organization = null;
			int maxCoverageCode = 0;
			// Map<Integer, Integer> locatedParent = new HashMap<Integer,
			// Integer>();
			LinkedList<Integer> locatedParentCodes = new LinkedList<Integer>();
			int maxOrgCode1 = organizationDAO.getMaxOrgCode();
			for (AdminOrgDeptForm adminDeptForm : storedFormList) {
				if (isTopDept) {

					/* Organization Details Save Process */
					organization = new Organization();
					organization.setOrgCode(maxOrgCode1);
					organization.setOlc(maxOrgCode1);
					organization.setOrganizationPK(new OrganizationPK(maxOrgCode1, 1));
					organization.setOrgName(adminDeptForm.getDepartmentNameEnglish());
					organization.setOrgNameLocal(adminDeptForm.getDepartmentNameLocal());
					organization.setShortName(adminDeptForm.getDepartmentShortName());
					organization.setIsactive(true);

					OrganizationType organizationType = new OrganizationType();
					Integer orgLevel=1; //for state
					Integer slc=stateCode;  //for state
					Integer orgTypeCode=2 ;// for dept
					Integer parentorgcode=null; //for dept
				
					if(adminDeptForm.getIsMinistry()){//for Ministary
						orgTypeCode=1;
						parentorgcode=0;
					}else if(isOrganization){//for organization
						orgTypeCode=adminDeptForm.getOrgType();
						parentorgcode=adminDeptForm.getDeptOrgCode();
					}
					
					if(isCenterUser){ //for center
						orgLevel=0;  
						slc=0;
						if(!isOrganization){
							parentorgcode=adminDeptForm.getMinistryId();
						}
						
					}
					
					
					organization.setOrgLevel(orgLevel);;
					organization.setSlc(slc);
					organizationType.setOrgTypeCode(orgTypeCode);
					organization.setParentorgcode(parentorgcode);
					organization.setOrganizationType(organizationType);
					session.save(organization);
					/* Organization Details Save Process Ends */

				}
				/* Located At Level Details Save Process */
				OrgLocatedAtLevels orgLocated = new OrgLocatedAtLevels();
				orgLocated.setOlc(maxOrgCode1);
				orgLocated.setSortOrder(++sortOrder);
				if (isTopDept) {
					orgLocated.setOrganization(organization);

					if (isCenterUser) {
						orgLocated.setLocatedAtLevel(0);
						/*if(adminDeptForm.getIsMinistry()){
						orgLocated.setParentOrgLocatedLevelCode(adminDeptForm.getMinistryId()); // as discuss wit madam ministry and dept for center level  parent on org_located  must be zero by Maneesh Kumar 25/10/2017
						}else{*/
							orgLocated.setParentOrgLocatedLevelCode(0);
						//}
					} else {
						orgLocated.setLocatedAtLevel(1);
					}
				} else {
					orgLocated.setOrganization(organization);
					Integer unitLevelCode = adminDeptForm.getUnitLevelCode();
					orgLocated.setLocatedAtLevel(unitLevelCode);
					orgLocated.setOrgLevelSpecificName(adminDeptForm.getDepartmentNameEnglish());
					orgLocated.setOrgLevelSpecificNameLocal(adminDeptForm.getDepartmentNameLocal());
					orgLocated.setOrgLevelSpecificShortName(adminDeptForm.getDepartmentShortName());
					// Integer parentUnitCode = findOrgLocatedParent(session,
					// unitLevelCode);
					if (!locatedParentCodes.isEmpty()) {
						orgLocated.setParentOrgLocatedLevelCode(locatedParentCodes.getLast());
					}
					// orgLocated.setParentOrgLocatedLevelCode(locatedParent.get(parentUnitCode));
				}
				orgLocated.setIsactive(true);
				orgLocated.setOrguntDone(false);
				/*Modified by pooja on 24-09-2015*/
				orgLocated.setOrgLocatedLevelVersion(1);
				Calendar date = new GregorianCalendar(2005, Calendar.JANUARY,1);
				orgLocated.setEffectiveDate(date.getTime());
				orgLocated.setCreatedBy(userId);
				orgLocated.setLastUpdatedBy(userId);
				orgLocated.setLastupdated(new Date());
				session.save(orgLocated);

				locatedParentCodes.add(orgLocated.getOrgLocatedLevelCode());
				// locatedParent.put(orgLocated.getLocatedAtLevel(),
				// orgLocated.getOrgLocatedLevelCode());
				/* Located At Level Details Save Process Ends */

				/* Coverage Details Save Process Started */
				if (!isTopDept) {
					String coverage = adminDeptForm.getChooseLevelAllOrSpecific();
					if ("F".equalsIgnoreCase(coverage)) {
						OrganizationCoverage orgCoverage = new OrganizationCoverage();
						orgCoverage.setOrgLocatedLevelCode(orgLocated.getOrgLocatedLevelCode());
						orgCoverage.setOrgCoverageEntityType(0);
						orgCoverage.setCoverage(coverage.charAt(0));
						orgCoverage.setCoverageDetailCode(0);
						orgCoverage.setIsactive(true);
						orgCoverage.setOrguntDone(false);
						session.save(orgCoverage);

					} else {
						String accessLevel = adminDeptForm.getFormAccessLevel();
						if (maxCoverageCode == 0) {
							maxCoverageCode = organizationDAO.getMaxCoverageCode() + 1;
						}
						Integer unitLevelCode = adminDeptForm.getUnitLevelCode();

						if ("S".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);
						} else if ("D".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getDistrictIds(), 2, // District
																									// Level
																									// Unit,
									coverage, orgLocated, maxCoverageCode, session);

						} else if ("T".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getDistrictIds(), 2, // District
																									// Level
																									// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getSubdistrictIds(), 3, // Sub-district
																										// Level
																										// Unit
									coverage, orgLocated, maxCoverageCode, session);

						} else if ("B".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getDistrictIds(), 2, // District
																									// Level
																									// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getBlockIds(), 5, // Block
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

						} else if ("V".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getDistrictIds(), 2, // District
																									// Level
																									// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getSubdistrictIds(), 3, // Sub-district
																										// Level
																										// Unit,
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getVillageIds(), 4, // Village
																									// Level
																									// Unit,
									coverage, orgLocated, maxCoverageCode, session);

						} else if ("A".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getAdminUnitEntityIds(), unitLevelCode, coverage, orgLocated, maxCoverageCode, session);
						}else if ("U".equalsIgnoreCase(accessLevel)) {
						
							maxCoverageCode = setCoverageState(adminDeptForm.getUrbanIds(),  adminDeptForm.getUnitLevelCode(), coverage, orgLocated, maxCoverageCode, session);
						
						}
						else if ("X".equalsIgnoreCase(accessLevel)) {
						
						maxCoverageCode = setCoverageState(adminDeptForm.getDpIds(),  Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
						}else if ("Y".equalsIgnoreCase(accessLevel)) {
						
						maxCoverageCode = setCoverageState(adminDeptForm.getDpIds(),  Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
						maxCoverageCode = setCoverageState(adminDeptForm.getIpIds(),  Integer.parseInt(DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
						}else if ("Z".equalsIgnoreCase(accessLevel)) {
						
						maxCoverageCode = setCoverageState(adminDeptForm.getDpIds(),  Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
						maxCoverageCode = setCoverageState(adminDeptForm.getIpIds(),  Integer.parseInt(DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
						maxCoverageCode = setCoverageState(adminDeptForm.getVpIds(),  Integer.parseInt(DepartmentConstent.VILLAGE_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						}else if ("C".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getCountryIds(),-100, //country
									// Level
									// Unit
coverage, orgLocated, maxCoverageCode, session);
						}
					}
				}
				isTopDept = false;
			}
			transaction.commit();
			session.flush();
			session.clear();
			isSuccess = executeOrgUnitFuncation(session, maxOrgCode1, stateCode, null, null);
			// isSuccess = true;

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			isSuccess = false;
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return isSuccess;
	}

	private Integer setCoverageState(String selectedIds, Integer unitLevelCode, String coverage, OrgLocatedAtLevels orgLocated, Integer maxCoverageCode, Session session) {
		if (selectedIds != null && !selectedIds.trim().isEmpty()) {
			this.setOrgCodes(orgLocated.getOrgLocatedLevelCode(), unitLevelCode, coverage, maxCoverageCode, session);
			if (selectedIds != null && !"".equals(selectedIds)) {
				String[] arrId = selectedIds.split("\\,");
				for (String entityId : arrId) {
					setOrgCoverageDetail(maxCoverageCode, Integer.parseInt(entityId), session);
				}
				maxCoverageCode++;
			}
		}

		return maxCoverageCode;
	}

	private void setOrgCodes(Integer locatedLevel, Integer unitLevelCode, String coverage, Integer maxCoverageCode, Session session) {
		OrganizationCoverage orgCoverage = new OrganizationCoverage();
		orgCoverage.setOrgLocatedLevelCode(locatedLevel);
		orgCoverage.setOrgCoverageEntityType(unitLevelCode);
		orgCoverage.setCoverage(coverage.charAt(0));
		orgCoverage.setCoverageDetailCode(maxCoverageCode);
		orgCoverage.setIsactive(true);
		orgCoverage.setOrguntDone(false);
		session.save(orgCoverage);
		return;
	}

	/**
	 * To delete Specfic Coverage Details used in Extended Department
	 * Functionality.
	 * 
	 * @author Ripunj on 06-10-2014
	 */

	private void deleteSpecificOrgCoverage(Integer locatedLevelCode, Session session) {
		
		Query query = null;
		Query query1 = null;
		
		try {
			String sql = " DELETE FROM OrganizationCoverageDetail  WHERE coverageCode IN (select coverageDetailCode from OrganizationCoverage where " + " orgLocatedLevelCode=:locatedLevelCode) ";
			query = session.createQuery(sql);
			query.setParameter("locatedLevelCode", locatedLevelCode);
			query.executeUpdate();

			sql = "DELETE FROM OrganizationCoverage WHERE orgLocatedLevelCode=:locatedLevelCode ";
			query1 = session.createQuery(sql);
			query1.setParameter("locatedLevelCode", locatedLevelCode);
			query1.executeUpdate();
			

		} catch (HibernateException e) {
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		} finally {

		}
		return;
	}

	private void setOrgCoverageDetail(Integer maxCoverageCode, Integer entityCode, Session session) {
		OrganizationCoverageDetail orgCoverageDetail = new OrganizationCoverageDetail();
		orgCoverageDetail.setCoverageCode(maxCoverageCode);
		orgCoverageDetail.setEntityCode(entityCode);
		orgCoverageDetail.setIsactive(true);
		session.save(orgCoverageDetail);
		return;
	}

	/*
	 * private Integer findOrgLocatedParent(Session session, Integer
	 * unitLevelCode){ Integer parentOrgLocated = null; try { //String
	 * queryBuilder =
	 * "select org_located_level_code from org_located_at_levels where isactive and located_at_level = (select parent_admin_unit_level_code from administration_unit_level where isactive and admin_unit_level_code = :unitLevel) and olc = :olc"
	 * ; String queryBuilder =
	 * "select parent_admin_unit_level_code from administration_unit_level where isactive and admin_unit_level_code = :unitLevel"
	 * ; Query query = session.createSQLQuery(queryBuilder);
	 * query.setParameter("unitLevel", unitLevelCode); parentOrgLocated =
	 * (Integer) query.uniqueResult(); } catch (Exception e) { // TODO: handle
	 * exception parentOrgLocated = null; } return parentOrgLocated; }
	 */

	/**
	 * Change By Maneesh Kumar Since 28-July-2015 This Method Change for extend
	 * orgnaization Units
	 * 
	 * @param session
	 * @param olc
	 * @param stateCode
	 * @param OrgLocatedLevelCode
	 * @param extendOrganizationUnitFlag
	 * @return
	 */
	private Boolean executeOrgUnitFuncation(Session session, Integer olc, Integer stateCode, Integer OrgLocatedLevelCode, String extendOrganizationUnitFlag) {
		Boolean isExecuted = false;
		try {
			StringBuilder stringBuilder = new StringBuilder("select * from set_org_units_fn(:olc, :stateCode");
			if (OrgLocatedLevelCode != null && extendOrganizationUnitFlag != null && ("Y").equalsIgnoreCase(extendOrganizationUnitFlag)) {
				stringBuilder.append(",:OrgLocatedLevelCode,:extendOrganizationUnitFlag");
			}
			stringBuilder.append(")");
			Query query = session.createSQLQuery(stringBuilder.toString());
			query.setParameter("olc", olc);
			query.setParameter("stateCode", stateCode);
			if (OrgLocatedLevelCode != null && extendOrganizationUnitFlag != null && ("Y").equalsIgnoreCase(extendOrganizationUnitFlag)) {
				query.setParameter("OrgLocatedLevelCode", OrgLocatedLevelCode);
				query.setParameter("extendOrganizationUnitFlag", extendOrganizationUnitFlag);
			}
			isExecuted = (Boolean) query.uniqueResult();
		} catch (Exception e) {
			isExecuted = false;
		}
		return isExecuted;
	}

	/**
	 * Set Org Units for a particular @orgLocatedLevelCode and passing parameter
	 * 'Y' for extended department functionality.
	 * 
	 * @param session
	 * @param olc
	 * @param stateCode
	 * @param orgLocatedLevelCode
	 * @return
	 */
	private Boolean executeOrgUnitFunctionForExtended(Session session, Integer olc, Integer stateCode, Integer orgLocatedLevelCode) {
		Boolean isExecuted = false;
		try {
			String queryBuilder = "select * from set_org_units_fn(:olc, :stateCode,:orgLocatedLevelCode,'Y')";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("olc", olc);
			query.setParameter("stateCode", stateCode);
			query.setParameter("orgLocatedLevelCode", orgLocatedLevelCode);
			isExecuted = (Boolean) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			isExecuted = false;
		}
		return isExecuted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getDepartmentList(Integer stateCode, Integer orgTypeCode) {
		// TODO Auto-generated method stub
		Session session = null;
		List<Organization> listDepartments = new ArrayList<Organization>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "from Organization o where o.organizationType.orgTypeCode = :orgTypeCode and o.isactive is true and o.slc = :stateCode order by orgName";
			Query query = session.createQuery(queryBuilder);
			query.setParameter("orgTypeCode", orgTypeCode);
			query.setParameter("stateCode", stateCode);
			listDepartments = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listDepartments;
	}

	@SuppressWarnings("unchecked")
	public List<DeptAdminUnitEntity> getAdminUnitListbyStateCode(int stateCode) {

		Session session = null;
		List<DeptAdminUnitEntity> getDeptAdminUnitList = new ArrayList<DeptAdminUnitEntity>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "from DeptAdminUnitEntity where isactive=true and slc=:stateCode order by adminEntityNameEnglish asc";
			Query query = session.createQuery(queryBuilder);
			query.setParameter("stateCode", stateCode);
			getDeptAdminUnitList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return getDeptAdminUnitList;
	}

	@Override
	public boolean checkExistingDepartmentName(Integer stateCode, String departmentName, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Session session = null;
		boolean isDuplicate = true;
		try {
			if (checkInSession(departmentName, httpSession)) {
				return isDuplicate;
			}
			session = sessionFactory.openSession();
			String queryBuilder = "select dept_exist_fn(:stateCode, :departmentName )";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("departmentName", departmentName, Hibernate.STRING);
			isDuplicate = (Boolean) query.uniqueResult();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isDuplicate;
	}

	@SuppressWarnings("unchecked")
	private boolean checkInSession(String depatmentName, HttpSession httpSession) {
		if (httpSession == null) {
			WebContext ctx = WebContextFactory.get();
			if (ctx != null) {
				httpSession = ctx.getSession(false);
			}
		}
		LinkedList<AdminOrgDeptForm> departmentForms = (LinkedList<AdminOrgDeptForm>) httpSession.getAttribute("storedDeptForms");
		if (departmentForms != null) {
			for (AdminOrgDeptForm form : departmentForms) {
				if (depatmentName.equalsIgnoreCase(form.getDepartmentNameEnglish())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Get Details of Admin Unit Entities with operation state for extended
	 * functionality of department.
	 * 
	 * @author Ripunj on 06-10-2014
	 * @param orgCode
	 * @param unitLevelCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnitEntity> getAdminEntitiesExtended(Integer orgCode, Integer unitLevelCode) {
		// TODO Auto-generated method stub

		Session session = null;
		List<DeptAdminUnitEntity> listAdminEntities = new ArrayList<DeptAdminUnitEntity>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "select case when au.admin_unit_entity_code =a.entity_code then cast('F' as character)" + " else cast('A' as character) end as operation_state,"
					+ " admin_unit_entity_code as adminUnitEntityCode,admin_entity_name_english as adminEntityNameEnglish " + " from administration_unit_entity au left outer join "
					+ " (select entity_code from org_coverage_detail where isactive and coverage_code in" + " (select coverage_detail_code from org_coverage where org_coverage_entity_type=:unitLevelCode and isactive and org_located_level_code in"
					+ " (select org_located_level_code from org_located_at_levels  where olc=:orgCode and located_at_level=:unitLevelCode and isactive)))as a"
					+ " on au.admin_unit_entity_code =a.entity_code where admin_unit_level_code=:unitLevelCode and isactive order by admin_entity_name_english ";

			SQLQuery query = session.createSQLQuery(queryBuilder);
			query.addScalar("adminUnitEntityCode").addScalar("adminEntityNameEnglish").addScalar("operation_state");
			query.setParameter("unitLevelCode", unitLevelCode);
			query.setParameter("orgCode", orgCode);

			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnitEntity.class)).list();
			listAdminEntities = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listAdminEntities;
	}

	@Override
	public boolean saveExtendedAdminDepartmentProcess(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, Integer orgLocatedAtLevelCode, boolean isCenterUser) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			// True only in case of first or single level department
			boolean isTopDept = false;
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			//Organization organization = null;
			int maxCoverageCode = 0;
			// Map<Integer, Integer> locatedParent = new HashMap<Integer,
			// Integer>();
			LinkedList<Integer> locatedParentCodes = new LinkedList<Integer>();
			/* int maxOrgCode1 = organizationDAO.getMaxOrgCode(); */
			int maxOrgCode1 = 0;
			for (AdminOrgDeptForm adminDeptForm : storedFormList) {
				maxOrgCode1 = Integer.parseInt(adminDeptForm.getOrgName());
				/* Located At Level Details Save Process */
				OrgLocatedAtLevels orgLocated = new OrgLocatedAtLevels();
				orgLocated.setOrgLocatedLevelCode(orgLocatedAtLevelCode);
				locatedParentCodes.add(orgLocated.getOrgLocatedLevelCode());
				/* Located At Level Details Save Process Ends */

				/* Coverage Details Save Process Started */
				if (!isTopDept) {
					String coverage = adminDeptForm.getChooseLevelAllOrSpecific();
					if ("F".equalsIgnoreCase(coverage)) {

						deleteSpecificOrgCoverage(orgLocatedAtLevelCode, session);

						OrganizationCoverage orgCoverage = new OrganizationCoverage();
						orgCoverage.setOrgLocatedLevelCode(orgLocated.getOrgLocatedLevelCode());
						orgCoverage.setOrgCoverageEntityType(0);
						orgCoverage.setCoverage(coverage.charAt(0));
						orgCoverage.setCoverageDetailCode(0);
						orgCoverage.setIsactive(true);
						orgCoverage.setOrguntDone(false);
						session.save(orgCoverage);

					} else {
						String accessLevel = adminDeptForm.getFormAccessLevel();
						if (maxCoverageCode == 0) {
							maxCoverageCode = organizationDAO.getMaxCoverageCode() + 1;
						}
						Integer unitLevelCode = adminDeptForm.getUnitLevelCode();

						if ("S".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);
						} else if ("D".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getDistrictIds(), 2, // District
																									// Level
																									// Unit,
									coverage, orgLocated, maxCoverageCode, session);

						} else if ("T".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getDistrictIds(), 2, // District
																									// Level
																									// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getSubdistrictIds(), 3, // Sub-district
																										// Level
																										// Unit
									coverage, orgLocated, maxCoverageCode, session);

						} else if ("B".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getDistrictIds(), 2, // District
																									// Level
																									// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getBlockIds(), 5, // Block
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

						} else if ("V".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getStateIds(), 1, // State
																								// Level
																								// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getDistrictIds(), 2, // District
																									// Level
																									// Unit
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getSubdistrictIds(), 3, // Sub-district
																										// Level
																										// Unit,
									coverage, orgLocated, maxCoverageCode, session);

							maxCoverageCode = setCoverageState(adminDeptForm.getVillageIds(), 4, // Village
																									// Level
																									// Unit,
									coverage, orgLocated, maxCoverageCode, session);

						} else if ("A".equalsIgnoreCase(accessLevel)) {
							maxCoverageCode = setCoverageState(adminDeptForm.getAdminUnitEntityIds(), unitLevelCode, coverage, orgLocated, maxCoverageCode, session);
						}
						/*else if ("L".equalsIgnoreCase(accessLevel) || "P".equalsIgnoreCase(accessLevel) ||"G".equalsIgnoreCase(accessLevel)) {
						maxCoverageCode = setCoverageState(adminDeptForm.getAdminUnitEntityIds(), unitLevelCode, coverage, orgLocated, maxCoverageCode, session);
					}*/
					}
				}
				isTopDept = false;
			}
			transaction.commit();
			session.flush();
			session.clear();
			isSuccess = executeOrgUnitFunctionForExtended(session, maxOrgCode1, stateCode, orgLocatedAtLevelCode);
			// isSuccess = true;

		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return isSuccess;
	}

	/**
	 * To check Existing Department Name For Extended Department Functionality.
	 * 
	 * @author Ripunj on 12-10-2014
	 */

	@Override
	public boolean checkExistingDepartmentNameForExtendedDept(Integer stateCode, String departmentName, Integer orgLocatedLevelCode, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Session session = null;
		boolean isDuplicate = true;
		try {
			/*
			 * if(checkInSession(departmentName, httpSession)){ return
			 * isDuplicate; }
			 */
			session = sessionFactory.openSession();
			String queryBuilder = "select dept_exist_fn(:stateCode, :departmentName,:orgLocatedLevelCode,'Y')";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("departmentName", departmentName, Hibernate.STRING);
			query.setParameter("orgLocatedLevelCode", orgLocatedLevelCode, Hibernate.INTEGER);
			isDuplicate = (Boolean) query.uniqueResult();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isDuplicate;
	}

	@SuppressWarnings("unchecked")
	public List<DeptAdminUnit> getAdminUnitLevelList(int stateCode) {

		Session session = null;
		List<DeptAdminUnit> getAdminUnitLevelList = new ArrayList<DeptAdminUnit>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "from DeptAdminUnit where adminUnitCode in(select adminUnitLevelCode from DeptAdminUnitEntity where slc=:stateCode)";
			Query query = session.createQuery(queryBuilder);
			query.setParameter("stateCode", stateCode);
			getAdminUnitLevelList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return getAdminUnitLevelList;
	}

	@SuppressWarnings("unchecked")
	public List<DeptAdminUnitEntity> getAdminUnitListbyStateCodeandLevel(int stateCode, int level) {

		Session session = null;
		List<DeptAdminUnitEntity> getDeptAdminUnitList = new ArrayList<DeptAdminUnitEntity>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "from DeptAdminUnitEntity where isactive=true and slc=:stateCode and  adminUnitLevelCode =:level order by adminEntityNameEnglish asc";
			Query query = session.createQuery(queryBuilder);
			query.setParameter("stateCode", stateCode);
			query.setParameter("level", level);
			getDeptAdminUnitList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return getDeptAdminUnitList;
	}

	/**
	 * To get Department Details for LocalBody Department Mapping
	 * 
	 * @author Ripunj on 23-01-2015
	 * @param orgUnitCode
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public LgdDepartmentDetail getDepartmentDetails(Long orgUnitCode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String strQuery = "SELECT * FROM get_org_unit_details_fn(?)";
			LgdDepartmentDetail deptDetail = (LgdDepartmentDetail) session.createSQLQuery(strQuery).setParameter(1, orgUnitCode.intValue()).list();
			return deptDetail;
		} catch (Exception e) {
			System.out.println("Exception in getDepartmentDetails method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}

	}*/

	/**
	 * To get Organization Level Details for LocalBody Department Mapping
	 * 
	 * @author Ripunj on 23-01-2015
	 * @param orgCode
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdOrganizationAtLevels> getOrganizationAtLevels(int orgCode) {
		Session session = null;
		List<LgdOrganizationAtLevels> data = new ArrayList<LgdOrganizationAtLevels>();
		try {
			session = sessionFactory.openSession();
			StringBuilder strQuery = new StringBuilder("SELECT org_located_level_code as key, org_located_level_name as value,level_wise_office_name as levelWiseOfficeName FROM get_organization_at_levels(?)");
			SQLQuery query = (SQLQuery) session.createSQLQuery(strQuery.toString()).setParameter(0, orgCode);
			query.addScalar("key").addScalar("value").addScalar("levelWiseOfficeName");
			data = query.setResultTransformer(Transformers.aliasToBean(LgdOrganizationAtLevels.class)).list();
			return data;

		} catch (Exception e) {
			System.out.println("Exception in getOrganizationAtLevels method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	/**
	 * To get Organization list for selected org type for LocalBody Department
	 * Mapping
	 * 
	 * @author Ripunj on 27-01-2015
	 * @param deparmentLvl
	 * @param stateLvl
	 * @param stateCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdOrganisationListByOrgType> getOrganizationListByOrgTypeFn(int deparmentLvl, int stateLvl, int stateCode) {

		Session session = null;
		List<LgdOrganisationListByOrgType> data = new ArrayList<LgdOrganisationListByOrgType>();
		try {
			session = sessionFactory.openSession();

			// Commented inorder to get the list of Departments along with
			// Attached Offices
			StringBuilder strQuery = new StringBuilder("SELECT org_unit_code as orgUnitCode ,org_code as orgCode,org_unit_name as orgUnitName, org_unit_name_local as orgUnitNameLocal FROM get_organization_list_by_location_headoffice(:stateCode,:stateLvl)");
			SQLQuery query = (SQLQuery) session.createSQLQuery(strQuery.toString()).setParameter("stateCode", stateCode).setParameter("stateLvl", stateLvl);
			query.addScalar("orgUnitCode").addScalar("orgCode").addScalar("orgUnitName").addScalar("orgUnitNameLocal");
			
			data = query.setResultTransformer(Transformers.aliasToBean(LgdOrganisationListByOrgType.class)).list();
			// data=(List<LgdOrganisationListByOrgType>)query.list();
			return data;
		} catch (Exception e) {
			System.out.println("Exception in getOrganizationListByOrgTypeFn method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	/**
	 * To get Unmapped Subordinate OrgUnits Details for LocalBody Department
	 * Mapping
	 * 
	 * @author Ripunj on 29-01-2015
	 * @param orgCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdSubordinateOrgUnits> getUnmappedSubordinateOrgUnits(int orgCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder strQuery = new StringBuilder("SELECT org.org_unit_code as orgUnitCode, org.org_unit_name as orgUnitName FROM get_subordinate_org_units(?) org "
					+ " where not exists (select from_level_code from dept_mapping map,dept_mapping_from mapfrom where " + " state_id = ? and mapping_type = ? and to_level_type_code = ? "
					+ " and map.mapping_id = mapfrom.mapping_id and from_level_category = ? and org.org_unit_code = from_level_code)");
			List<LgdSubordinateOrgUnits> data = (List<LgdSubordinateOrgUnits>) session.createSQLQuery(strQuery.toString()).addScalar("orgUnitCode").addScalar("orgUnitName").setParameter(0, orgCode).setParameter(1, stateCode)
					.setParameter(2, mappingType).setParameter(3, toLevelTypeCode).setParameter(4, fromLevelCategory).setResultTransformer(Transformers.aliasToBean(LgdSubordinateOrgUnits.class)).list();
			return data;
		} catch (Exception e) {
			System.out.println("Exception in getUnmappedSubordinateOrgUnits method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	/**
	 * To check Department Mapping Exists or not for LocalBody Department
	 * Mapping
	 * 
	 * @author Ripunj on 29-01-2015
	 * @param stateID
	 * @param mappingType
	 * @param toLevelTypeCode
	 * @param fromLevelCategory
	 * @param fromLevelTypeCode
	 * @param fromLevelCode
	 * @return
	 * @throws ApplicationException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean checkIfDeptMappingExists(Integer stateID, String mappingType, Integer toLevelTypeCode, String fromLevelCategory, Integer fromLevelTypeCode, Integer fromLevelCode) {
		Session session = null;
		{
			try {
				session = sessionFactory.openSession();
				String strQuery = "(select * from dept_mapping map, dept_mapping_from mapfrom where state_id = ? and mapping_type = ? and "
						+ " to_level_type_code = ? and map.mapping_id = mapfrom.mapping_id and from_level_category = ? and from_level_type_code = ? and " + "  from_level_code = ?) ";

				List<Integer> deptMappings = (List<Integer>) session.createSQLQuery(strQuery).setParameter(0, stateID).setParameter(1, mappingType).setParameter(2, toLevelTypeCode).setParameter(3, fromLevelCategory)
						.setParameter(4, fromLevelTypeCode).setParameter(5, fromLevelCode).list();
				if (deptMappings.size() > 0)
					return true;
				else
					return false;
			} catch (Exception e) {
				System.out.println("Exception in checkIfDeptMappingExists method of AdministrativeDepratmentDAOImpl is :  " + e);
				throw e;
			} finally {
				if (session != null && session.isOpen()) {
					session.flush();
					session.close();
				}
			}
		}

	}

	/**
	 * To get Unmapped ULB List Details for LocalBody Department Mapping
	 * 
	 * @author Ripunj on 02-02-2015
	 * @param localBodyTypeCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyListbyState> getUnmappedULBList(Integer localBodyTypeCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder strQuery = new StringBuilder("SELECT local_body_code as localBodyCode,local_body_type_code as localBodyTypeCode,local_body_type_name as localBodyTypeName ,"
					+ " local_body_name_english as localBodyNameEnglish,local_body_name_local as localBodyNameLocal FROM get_lb_list_fn(?,?) lb where not exists "
					+ "(select from_level_code from dept_mapping map,dept_mapping_from mapfrom where state_id = ? and mapping_type = ? and to_level_type_code = ? "
					+ "and map.mapping_id = mapfrom.mapping_id and from_level_category = ? and lb.local_body_code = from_level_code)");
			List<LocalbodyListbyState> data = (List<LocalbodyListbyState>) session.createSQLQuery(strQuery.toString()).addScalar("localBodyCode").addScalar("localBodyTypeCode").addScalar("localBodyTypeName").addScalar("localBodyNameEnglish")
					.addScalar("localBodyNameLocal").setParameter(0, localBodyTypeCode).setParameter(1, stateCode).setParameter(2, stateCode).setParameter(3, mappingType).setParameter(4, toLevelTypeCode).setParameter(5, fromLevelCategory)
					.setResultTransformer(Transformers.aliasToBean(LocalbodyListbyState.class)).list();
			return data;
		} catch (Exception e) {
			System.out.println("Exception in getUnmappedULBList method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	/**
	 * To get Unmapped Parent wise LB List Details for LocalBody Department
	 * Mapping
	 * 
	 * @author Ripunj on 02-02-2015
	 * @param localBodyCode
	 * @param stateCode
	 * @param mappingType
	 * @param fromLevelCategory
	 * @param toLevelTypeCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyListbyState> getUnmappedParentwiseLBList(int localBodyCode, Integer stateCode, String mappingType, String fromLevelCategory, Integer toLevelTypeCode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder strQuery = new StringBuilder("SELECT local_body_code as localBodyCode,local_body_name_english as localBodyNameEnglish FROM get_parentwise_lb_list(?) lb where not exists "
					+ "(select from_level_code from dept_mapping map,dept_mapping_from mapfrom where state_id = ? and mapping_type = ? and to_level_type_code = ? "
					+ "and map.mapping_id = mapfrom.mapping_id and from_level_category = ? and lb.local_body_code = from_level_code)");
			List<LocalbodyListbyState> data = (List<LocalbodyListbyState>) session.createSQLQuery(strQuery.toString()).addScalar("localBodyCode").addScalar("localBodyNameEnglish").setParameter(0, localBodyCode).setParameter(1, stateCode)
					.setParameter(2, mappingType).setParameter(3, toLevelTypeCode).setParameter(4, fromLevelCategory).setResultTransformer(Transformers.aliasToBean(LocalbodyListbyState.class)).list();
			return data;
		} catch (Exception e) {
			System.out.println("Exception in getUnmappedParentwiseLBList method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	/**
	 * To get Subordinate OrgUnits List Details for LocalBody Department Mapping
	 * 
	 * @author Ripunj on 02-02-2015
	 * @param orgCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LgdSubordinateOrgUnits> getSubordinateOrgUnits(int orgCode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder strQuery = new StringBuilder("SELECT org.org_unit_code as orgUnitCode, org.org_unit_name as orgUnitName FROM get_subordinate_org_units(?) as org");
			List<LgdSubordinateOrgUnits> data = (List<LgdSubordinateOrgUnits>) session.createSQLQuery(strQuery.toString()).addScalar("orgUnitCode").addScalar("orgUnitName").setParameter(0, orgCode)
					.setResultTransformer(Transformers.aliasToBean(LgdSubordinateOrgUnits.class)).list();
			return data;
		} catch (Exception e) {
			System.out.println("Exception in getSubordinateOrgUnits method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	/**
	 * @author Ripunj on 03-02-2015
	 * @param deptMapping
	 * @return
	 */
	
	//Modified by Sushma Singh jan 2020
	@Override
	public DepartmentMapping merge(DepartmentMapping deptMapping,String fromCategory,Integer fromLevelTypeCode, String frmLevelCode,String wardcode) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			//session.saveOrUpdate(deptMapping);
			 StringBuilder queryString =new StringBuilder("select * from insert_update_dept_mapping(:state_id,:mapping_type,:to_level_category,:to_level_type_code,:to_level_code,:mapping_doneby_userid,:from_level_category,:from_level_type_code,:from_level_code,:ward_list");
				if(deptMapping.getMappingId()!=null) {
				queryString.append(",:mapping_id");
			   }
			  queryString.append(");");
			  Query query = session.createSQLQuery(queryString.toString());
			  query.setParameter("state_id",deptMapping.getStateId());
			  query.setParameter("mapping_type",deptMapping.getMappingType());
			  query.setParameter("to_level_category",deptMapping.getToLevelCategory());
			  query.setParameter("to_level_type_code",deptMapping.getToLevelTypeCode());
			  query.setParameter("to_level_code",deptMapping.getToLevelCode());
			  query.setParameter("mapping_doneby_userid",deptMapping.getMappingDonebyUserid());
			  query.setParameter("from_level_category",fromCategory);
			  query.setParameter("from_level_type_code",fromLevelTypeCode);
			  query.setParameter("from_level_code",frmLevelCode);
			  query.setParameter("ward_list",wardcode);
			  if(deptMapping.getMappingId()!=null)
			  {
				  query.setParameter("mapping_id",deptMapping.getMappingId(),Hibernate.LONG);
			  }
			  query.uniqueResult();
			  
			   
		} catch (Exception e) {
			System.out.println("Exception in merge method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return null;
	}

	/**
	 * @author Ripunj on 03-02-2015
	 * @param stateID
	 * @param mappingType
	 * @param toLevelCategory
	 * @param toLevelCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DepartmentMapping getDeptMappingByMappingType(Integer stateID, String mappingType, String toLevelCategory, Long toLevelCode) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
            String queryString = " from DepartmentMapping model " + "where model.stateId=:stateId and model.mappingType=:mappingType and model.toLevelCategory=:toLevelCategory and model.toLevelCode=:toLevelCode and model.isactive=true ";
			Query query = session.createQuery(queryString);
			query.setParameter("stateId", stateID);
			query.setParameter("mappingType", mappingType);
			query.setParameter("toLevelCategory", toLevelCategory);
			query.setParameter("toLevelCode", toLevelCode);
			List<DepartmentMapping> resultList = query.list();
			if (resultList == null || resultList.size() == 0) {
				return null;
			}
			return resultList.get(0);
		} catch (Exception e) {
			System.out.println("Exception in getDeptMappingByMappingType method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	/**
	 * To Get Organization List filter by stateCode and organization type
	 * 
	 * @author Maneesh Kumar 07-May-2015
	 * @param stateCode
	 * @param orgTypeCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationbyCriteria(Integer slc, Integer orgTypeCode) throws Exception {
		Session session = null;
		List<Organization> organizationList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select org_code as olc,org_name as orgName from organization where isactive=true ");
			if (slc >0) {
				queryBuild.append(" and slc=:slc");
			}else{
				queryBuild.append(" and coalesce(slc,0)=0 ");
			}
			if (orgTypeCode != null && orgTypeCode!=2 && orgTypeCode!=1) {
				queryBuild.append(" and org_type_code not in(:ministCode,:deptCode)");
			}else{	
				queryBuild.append(" and org_type_code=:orgTypeCode");
			}
			queryBuild.append(" order by org_name");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			if (slc > 0) {
				query.setParameter("slc", slc);
			}
			if (orgTypeCode != null && orgTypeCode!=2 && orgTypeCode!=1) {
				query.setParameter("deptCode", Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString()));
				query.setParameter("ministCode",Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_MINIST.toString()));
			}else{
				query.setParameter("orgTypeCode", orgTypeCode);
			}
			query.addScalar("olc");
			query.addScalar("orgName");
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			organizationList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getExistLandRegionUnitLevelbyOrganization(boolean isCenterUser, Integer olc, Integer slc,String entityType) throws Exception {
		Session session = null;
		List<DeptAdminUnit> deptAdminUnitList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = null;
			if ((DepartmentConstent.ENTITY_TYPE_LANDREGION.toString()).equals(entityType)) {
				queryBuild = new StringBuilder("select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode ,"
				+ "oll.parent_org_located_level_code as parentOrgLocatedLevelCode from org_located_at_levels oll left join Administration_Unit_Level aul on  oll.located_at_level=aul.admin_unit_level_code "
				+ " where oll.olc=:olc and aul.admin_unit_level_code<=:landregionMaxCapacityand and aul.isactive and oll.isactive");
			}else if ((DepartmentConstent.ENTITY_TYPE_ADMINUNIT.toString()).equals(entityType)){
				queryBuild = new StringBuilder("select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode ,"
				+ "oll.parent_org_located_level_code as parentOrgLocatedLevelCode from org_located_at_levels oll left join Administration_Unit_Level aul on  oll.located_at_level=aul.admin_unit_level_code "
				+ " where oll.olc=:olc and aul.admin_unit_level_code>:landregionMaxCapacityand and aul.isactive and oll.isactive and slc=:slc");
			}else if ((DepartmentConstent.ENTITY_TYPE_BOTH.toString()).equals(entityType) ){
				
				queryBuild = new StringBuilder("select DISTINCT admin_unit_level_code as adminUnitCode,parent_admin_unit_level_code as seqLevel,unit_level_name_english as adminLevelNameEng,slc,"
						+ "(select located_at_level from org_located_at_levels where org_located_level_code=oll.parent_org_located_level_code and isactive) as parentAdminCode " + " from org_located_at_levels oll left join Administration_Unit_Level aul"
						+ " on  oll.located_at_level=aul.admin_unit_level_code where oll.olc=:olc  and aul.isactive and oll.isactive");
			}
                else if ((DepartmentConstent.LOCAL_BODY_CODE.toString()).equals(entityType) ){
				
				queryBuild = new StringBuilder("select DISTINCT -local_body_type_code as adminUnitCode,0 as seqLevel,local_body_type_name as adminLevelNameEng,0 as slc,"
									             +"(select located_at_level from org_located_at_levels where org_located_level_code=oll.parent_org_located_level_code and isactive) as parentAdminCode "
									            +"from org_located_at_levels oll left join local_body_type aul on  -oll.located_at_level=aul.local_body_type_code where oll.olc=:olc " 
									            +"and aul.isactive and oll.isactive  ");
			}
		/*	if(!isCenterUser){
				queryBuild.append(" and admin_unit_level_code<>1");
			}
			*/
			//For Local Body
			 if ((DepartmentConstent.ENTITY_TYPE_BOTH.toString()).equals(entityType)){
				 queryBuild.append(" union all select DISTINCT -local_body_type_code as adminUnitCode,0 as seqLevel,local_body_type_name as adminLevelNameEng,0 as slc,"
									             +"(select located_at_level from org_located_at_levels where org_located_level_code=oll.parent_org_located_level_code and isactive) as parentAdminCode "
									            +"from org_located_at_levels oll left join local_body_type aul on  -oll.located_at_level=aul.local_body_type_code where oll.olc=:olc " 
									            +"and aul.isactive and oll.isactive  ");
				 
				 
			 }else{
				 queryBuild.append(" order by adminUnitCode ");
			 }
			
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("olc", olc);
			query.addScalar("adminUnitCode");
			query.addScalar("adminLevelNameEng");
			query.addScalar("parentAdminCode");
			if (!(DepartmentConstent.ENTITY_TYPE_BOTH.toString()).equals(entityType) && !(DepartmentConstent.LOCAL_BODY_CODE.toString()).equals(entityType)) {
				query.addScalar("parentOrgLocatedLevelCode");
				query.setParameter("landregionMaxCapacityand", Integer.parseInt(DepartmentConstent.LANDREGION_MAX_CAPACITY.toString()));
				if ((DepartmentConstent.ENTITY_TYPE_ADMINUNIT.toString()).equals(entityType)){
					query.setParameter("slc", slc);
				}
			} else {
				query.addScalar("seqLevel");
			}
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();

		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getLandRegionUnitLevelbyOrganization(boolean isCenterUser, Integer olc, Integer landRegionSlc, Integer adminUnitCode) throws Exception {
		Session session = null;
		List<DeptAdminUnit> deptAdminUnitList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder(" select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode"
					+ " from Administration_Unit_Level where isactive and slc=:landRegionSlc and admin_unit_level_code<>1 and parent_admin_unit_level_code<(select parent_admin_unit_level_code from Administration_Unit_Level "
					+ "where admin_unit_level_code=(select min(located_at_level) from org_located_at_levels where olc=:olc and located_at_level<>1 and isactive) ) and isactive");
			if (adminUnitCode != null) {
				if (adminUnitCode == Integer.parseInt(DepartmentConstent.BLOCK_CODE.toString())) {
					adminUnitCode = Integer.parseInt(DepartmentConstent.BLOCK_EQUL_CODE.toString());
				}
				queryBuild.append(" and admin_unit_level_code<:adminUnitCode");
			}

			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("olc", olc);
			query.setParameter("landRegionSlc", landRegionSlc);
			if (adminUnitCode != null) {
				query.setParameter("adminUnitCode", adminUnitCode);
			}
			query.addScalar("adminUnitCode");
			query.addScalar("adminLevelNameEng");
			query.addScalar("slc");
			query.addScalar("parentAdminCode");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getChildofExistingParentLevelbyOrganization(Integer parentAdminLvlCode, Integer olc, Integer landRegionSlc, Integer slc, List<Integer> adminLvlCodeList, String existAdminCode, Integer childAdminCode) throws Exception {
		Session session = null;
		List<DeptAdminUnit> deptAdminUnitList = null;
		try {
			session = sessionFactory.openSession();
			boolean childAdminCodeFlag = false;
			if (childAdminCode != null
					&& (childAdminCode == Integer.parseInt(DepartmentConstent.STATE_CODE.toString()) || childAdminCode == Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString())
							|| childAdminCode == Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()) || childAdminCode == Integer.parseInt(DepartmentConstent.VILLAGE_CODE.toString()) || childAdminCode == Integer
							.parseInt(DepartmentConstent.BLOCK_CODE.toString()))) {
				childAdminCodeFlag = true;
			}
			StringBuilder queryBuild = new StringBuilder("select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode"
					+ "  from Administration_Unit_Level where isactive and slc =:slc  and admin_unit_level_code not in(select located_at_level from org_located_at_levels where olc=:olc) and " + "parent_admin_unit_level_code");
			if (!existAdminCode.contains(parentAdminLvlCode.toString())) {
				queryBuild.append(">");
			}
			queryBuild.append("=:parentAdminLvlCode");
			if (parentAdminLvlCode == Integer.parseInt(DepartmentConstent.BLOCK_CODE.toString())) {
				queryBuild.append(" or admin_unit_level_code=" + DepartmentConstent.BLOCK_CHILD_CODE.toString());
			}
			queryBuild.append(" and (slc =:slc or (admin_unit_level_code<=5 and admin_unit_level_code>0))");
			if (adminLvlCodeList != null) {
				queryBuild.append(" and admin_unit_level_code not in(:adminLvlCodeList) ");
			}
			if (childAdminCodeFlag) {
				queryBuild.append(" and parent_admin_unit_level_code>=:childAdminCode");
			}

			queryBuild.append(" union all ");

			queryBuild
					.append("select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode from Administration_Unit_Level "
							+ " where isactive and (slc =:slc or (admin_unit_level_code<=5 and admin_unit_level_code>0)) and admin_unit_level_code not in(select located_at_level from org_located_at_levels where olc=:olc and isactive) "
						//	+ "and parent_admin_unit_level_code"
							);
			if (!existAdminCode.contains(parentAdminLvlCode.toString())) {
				queryBuild.append(">");
			}
			//queryBuild.append("=((select case when (admin_unit_level_code<=5 and admin_unit_level_code>0) then (parent_admin_unit_level_code+1)  else  parent_admin_unit_level_code end from administration_unit_level  where isactive and admin_unit_level_code=:parentAdminLvlCode))");
			queryBuild.append(" and (admin_unit_level_code<=5 and admin_unit_level_code>0)");
			//add by sanjai
			queryBuild.append("  and admin_unit_level_code!=:parentAdminLvlCode ");
			if (adminLvlCodeList != null) {
				queryBuild.append(" and admin_unit_level_code not in(:adminLvlCodeList) ");
			}
			if (childAdminCodeFlag) {
				queryBuild.append(" and parent_admin_unit_level_code>=:childAdminCode");
			}
			// }
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			//query.setParameter("landRegionSlc", landRegionSlc);
			query.setParameter("slc", slc);
			query.setParameter("parentAdminLvlCode", parentAdminLvlCode);
			query.setParameter("olc", olc);
			if (adminLvlCodeList != null) {
				query.setParameterList("adminLvlCodeList", adminLvlCodeList);
			}
			if (childAdminCodeFlag) {
				query.setParameter("childAdminCode", childAdminCode);
			}
			query.addScalar("adminUnitCode");
			query.addScalar("adminLevelNameEng");
			query.addScalar("slc");
			query.addScalar("parentAdminCode");

			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();

		} catch(Exception ex){
			
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
	}
*/
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getChildofExistingParentLevelbyOrganization(Integer parentAdminLvlCode, Integer olc, Integer landRegionSlc, Integer slc, List<Integer> adminLvlCodeList,List<Integer> localBodyCode, String localBodyLevel, Integer childAdminCode) throws Exception {
		Session session = null;
	     Integer adminLevelCode=parentAdminLvlCode;
		if(parentAdminLvlCode>5){
			adminLevelCode=	getLandReginCode(parentAdminLvlCode);
		}
		
		int	parentAdminLvlCodeLB=parentAdminLvlCode;
		if("D".equals(localBodyLevel)){
			parentAdminLvlCode=2;
		}
		else if("I".equals(localBodyLevel))
			parentAdminLvlCode=3;
		else if("V".equals(localBodyLevel))
			parentAdminLvlCode=4;
		
		List<DeptAdminUnit> deptAdminUnitList = null;
		try {
			session = sessionFactory.openSession();
			boolean childAdminCodeFlag = false;
			if (childAdminCode != null
					&& (childAdminCode == Integer.parseInt(DepartmentConstent.STATE_CODE.toString()) || childAdminCode == Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString())
							|| childAdminCode == Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()) || childAdminCode == Integer.parseInt(DepartmentConstent.VILLAGE_CODE.toString()) || childAdminCode == Integer
							.parseInt(DepartmentConstent.BLOCK_CODE.toString()))) {
				childAdminCodeFlag = true;
			}
			StringBuilder queryBuild = new StringBuilder("select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode "
					+ " from Administration_Unit_Level where isactive and slc =:slc  and admin_unit_level_code not in(:adminLvlCodeList)  and admin_unit_level_code>5 and parent_admin_unit_level_code=:parentAdminLvlCode");

			queryBuild.append(" union all ");

			queryBuild
					.append("select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode from Administration_Unit_Level  "
							+ "where isactive and admin_unit_level_code not in(:adminLvlCodeList)  and (admin_unit_level_code<=5 and admin_unit_level_code>0) and admin_unit_level_code>:adminLevelCode");
			
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("slc", slc);
			query.setParameter("parentAdminLvlCode", parentAdminLvlCode);
			
			query.setParameter("adminLevelCode", adminLevelCode);
			query.setParameterList("adminLvlCodeList", adminLvlCodeList);
			/*if (childAdminCodeFlag) {
			query.setParameter("childAdminCode", childAdminCode);
			}*/
			query.addScalar("adminUnitCode");
			query.addScalar("adminLevelNameEng");
			query.addScalar("slc");
			query.addScalar("parentAdminCode");

			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();
			
			List<DeptAdminUnit> getLocalBody=null;
			if(parentAdminLvlCode<=5)
				getLocalBody=getLocalBody(slc,parentAdminLvlCodeLB,localBodyLevel,localBodyCode);
			else
				getLocalBody=getLocalBody(slc,adminLevelCode,localBodyLevel,localBodyCode);
			
			deptAdminUnitList.addAll(getLocalBody);
		} catch(Exception ex){
			throw new HibernateException(ex);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
	}

	List<DeptAdminUnit> getLocalBody(Integer slc,Integer parentAdminLvlCodeLB,String localBodyLevel,List<Integer> localBodyCode){
		
		Character level =localBodyLevel.trim().charAt(0);
		Session session = null;
		if(localBodyCode.size()==0)
			localBodyCode.add(0);
		List<DeptAdminUnit> deptAdminUnitList = null;
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("select -admin_unit_code as adminUnitCode,:adminUnitCode as parentAdminCode, admin_level_name_eng as adminLevelNameEng,cast(local_body_level as character varying) as localBodyLevel from "
					+ "get_lb_extend_dept_hierarchy(:slc,:adminUnitCode,:localBody) where admin_unit_code not in (:localBodyCode)");
			query.setParameter("slc", slc,Hibernate.INTEGER);
			query.setParameter("adminUnitCode", parentAdminLvlCodeLB,Hibernate.INTEGER);
			query.setParameter("localBody", level,Hibernate.CHARACTER);
			query.setParameterList("localBodyCode", localBodyCode);
			query.addScalar("adminUnitCode");
			query.addScalar("adminLevelNameEng");
			query.addScalar("localBodyLevel");
			query.addScalar("parentAdminCode");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();
			
		}
		catch(Exception ex){
			throw new HibernateException(ex);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
		
	}
	private Integer getLandReginCode(int adminLevelCode){
		String landRegionCode="";
		Session session = null;
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery(" select * from get_land_region_parent_of_admin_level(:adminLevelCode)");
			query.setParameter("adminLevelCode", adminLevelCode);
			landRegionCode=query.list().get(0).toString();
			
		} catch(Exception e){
	     	   e.printStackTrace();
	        } finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
	return 	Integer.parseInt(landRegionCode);
	}
	public List<OrgLocatedAtLevels> getParentDeptListbyOrganization(Integer olc, Integer parentLevelCode) throws Exception {
		Session session = null;
		List<OrgLocatedAtLevels> parentDeptList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select ");
			queryBuild.append(" case when located_at_level=1 then (select org_name  from organization o where o.olc=parent.olc and o.isactive) else org_level_specific_name end"
					+ "  as orgLevelSpecificName  from org_located_at_levels parent  where isactive and located_at_level=:parentLevelCode  and olc=:olc  ");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			if (parentLevelCode != null) {
				query.setParameter("parentLevelCode", parentLevelCode);
			}
			query.setParameter("olc", olc);
			query.addScalar("orgLevelSpecificName");

			query.setResultTransformer(Transformers.aliasToBean(OrgLocatedAtLevels.class));
			parentDeptList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return parentDeptList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> createtHierarchybyadminUnitCode(Integer olc, List<Integer> adminUnitCode,List<Integer> localBodyCode, Integer slc) throws Exception {
		Session session = null;
		List<DeptAdminUnit> deptAdminUnitList = null;
		if(adminUnitCode.size()==0)
			adminUnitCode.add(-1);
		if(localBodyCode.size()==0)
			localBodyCode.add(0);
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select DISTINCT cast(null as Integer)orgLocatedLevelCode,admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng," + "case when admin_unit_level_code=2 then 'D' "
					+ "when admin_unit_level_code=3 then 'T'  " + "when admin_unit_level_code=4 then 'V' " + " when admin_unit_level_code=5 then 'B' "
					+ "else 'A' end  as adminLevelNameLocal,slc,parent_admin_unit_level_code as parentAdminCode,Cast(false as boolean)revenueEntity,oll.parent_org_located_level_code as parentOrgLocatedLevelCode,oll.sort_order as sortOrder "
					+ " from Administration_Unit_Level aul left join org_located_at_levels oll on oll.located_at_level=aul.admin_unit_level_code " + "where aul.isactive and oll.isactive and oll.olc=:olc and oll.located_at_level in(:adminUnitCode) "
					+ " union all " + "select cast(null as Integer)orgLocatedLevelCode, admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng," + "case when admin_unit_level_code=2 then 'D'"
					+ " when admin_unit_level_code=3 then 'T'" + " when admin_unit_level_code=4 then 'V'" + " when admin_unit_level_code=5 then 'B'"
					+ " else 'A' end   as adminLevelNameLocal,slc,parent_admin_unit_level_code as parentAdminCode,Cast(true as boolean)revenueEntity,cast(null as Integer)parentOrgLocatedLevelCode,cast(0 as Integer) sortOrder "
					+ " from Administration_Unit_Level where isactive and admin_unit_level_code in(:adminUnitCode) and admin_unit_level_code not in "
					+ "(select admin_unit_level_code from Administration_Unit_Level aul left join org_located_at_levels oll on oll.located_at_level=aul.admin_unit_level_code "
					+ "where aul.isactive and oll.isactive and oll.olc=:olc and oll.located_at_level in(:adminUnitCode))"
                    +" union all"
                    +" select cast(null as Integer)orgLocatedLevelCode, -admin_unit_code as adminUnitCode, admin_level_name_eng as adminLevelNameEng,case when local_body_level='D' then 'X' "
                    +" when local_body_level='I' then 'Y' else  'Z' end as adminLevelNameLocal,0 as slc, 0 as parentAdminCode,Cast(true as boolean)revenueEntity,cast(null as Integer)parentOrgLocatedLevelCode,cast(0 as Integer) sortOrder "
                    +" from get_lb_extend_dept_hierarchy(:slc,1,'') where admin_unit_code in (:localBodyCode)  order by parentAdminCode,slc desc");

			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameterList("adminUnitCode", adminUnitCode);
			query.setParameterList("localBodyCode", localBodyCode);
			query.setParameter("olc", olc);
			query.setParameter("slc", slc);
			query.addScalar("orgLocatedLevelCode");
			query.addScalar("adminUnitCode");
			query.addScalar("adminLevelNameEng");
			query.addScalar("adminLevelNameLocal");
			query.addScalar("revenueEntity");
			query.addScalar("slc");
			query.addScalar("parentAdminCode");
			query.addScalar("parentOrgLocatedLevelCode");
			query.addScalar("sortOrder");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();
          
		} catch(Exception e){
     	   e.printStackTrace();
        } finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
	}

	@SuppressWarnings("unchecked")
	public List<OrgLocatedAtLevels> getExistDeptbyOrganization(Integer olc, Integer orgLocatedLevelCode) throws Exception {
		Session session = null;
		List<OrgLocatedAtLevels> DeptList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select DISTINCT org_level_specific_name as orgLevelSpecificName,org_level_specific_name_local as orgLevelSpecificNameLocal,"
					+ " org_level_specific_short_name as orgLevelSpecificShortName,org_located_level_code as orgLocatedLevelCode,(select case when located_at_level<>1 then  "
					+ " org_level_specific_name else (select org_name from organization where olc=parent.olc and isactive) end  from org_located_at_levels child "
					+ " where isactive and olc=parent.olc and org_located_level_code=parent.parent_org_located_level_code)as parentOrgLevelSpecificName,(select parent_org_located_level_code from org_located_at_levels "
					+ " where isactive and olc=parent.olc and org_located_level_code=parent.org_located_level_code)as parentOrgLocatedLevelCode from org_located_at_levels parent "
					+ " where isactive and olc=:olc and org_located_level_code=:orgLocatedLevelCode");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("orgLocatedLevelCode", orgLocatedLevelCode);
			query.setParameter("olc", olc);
			query.addScalar("orgLevelSpecificName");
			query.addScalar("orgLevelSpecificNameLocal");
			query.addScalar("orgLevelSpecificShortName");
			query.addScalar("orgLocatedLevelCode");
			query.addScalar("parentOrgLevelSpecificName");
			query.addScalar("parentOrgLocatedLevelCode");
			query.setResultTransformer(Transformers.aliasToBean(OrgLocatedAtLevels.class));
			DeptList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return DeptList;
	}

	@Override
	public boolean saveRestructureDepartmentLevel(LinkedList<AdminOrgDeptForm> storedFormList, Integer stateCode, boolean isCenterUser, Integer olc,Long userId) throws Exception {

		Session session = null;
		Transaction transaction = null;
		@SuppressWarnings("unused")
		boolean isSuccess = false;
		int maxCoverageCode = 0;
		try {
			session = sessionFactory.openSession();
			CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
			transaction = session.beginTransaction();
			maxCoverageCode = organizationDAO.getMaxCoverageCode() + 1;
			Integer orgVersion = (Integer) session.createSQLQuery("select org_version from organization where olc=:olc and isactive=true").setParameter("olc", olc).uniqueResult();
			Integer sortOrder = (Integer) session.createSQLQuery("select max(sort_order) from org_located_at_levels where isactive and olc=:olc").setParameter("olc", olc).uniqueResult();
			OrganizationPK organizationPK = new OrganizationPK();
			organizationPK.setOrgCode(olc);
			organizationPK.setOrgVersion(orgVersion);
			Organization organization = (Organization) session.get(Organization.class, organizationPK);
			Map<String, Integer> parentCodeList = new LinkedHashMap<String, Integer>();
			Integer parentCode = null;
			for (AdminOrgDeptForm adminOrgDeptForm : storedFormList) {
				OrgLocatedAtLevels orgLocated = new OrgLocatedAtLevels();
				String hierArr[]= adminOrgDeptForm.getHierarchyIds().split(",");
				Integer parentUnitLevelCode=-99;
				if(hierArr.length>=2){
					parentUnitLevelCode=Integer.parseInt(hierArr[hierArr.length-2]);
				}
				orgLocated.setOrganization(organization);
				if (parentUnitLevelCode==0 && isCenterUser==true) {
					parentCode = (Integer) session.createSQLQuery("select org_located_level_code from org_located_at_levels where olc=:olc and located_at_level=:locatedAtLevel and isactive").setParameter("olc", olc)
							.setParameter("locatedAtLevel", 0).uniqueResult();
				}else if (parentUnitLevelCode==1 && isCenterUser==false) {
					parentCode = (Integer) session.createSQLQuery("select org_located_level_code from org_located_at_levels where olc=:olc and located_at_level=:locatedAtLevel and isactive").setParameter("olc", olc)
							.setParameter("locatedAtLevel", Integer.parseInt(DepartmentConstent.STATE_CODE.toString())).uniqueResult();
				} else {
					parentCode = parentCodeList.get(adminOrgDeptForm.getParentDepartmentName());
				}
				if (parentCode == null) {
					String parentDepartmentName=adminOrgDeptForm.getParentDepartmentName();
					if (customValidator.checkforOnlyNumeric(parentDepartmentName)) {
						parentCode=Integer.parseInt(parentDepartmentName);
					}else{
						parentCode = (Integer) session.createSQLQuery("select org_located_level_code from org_located_at_levels where org_level_specific_name ilike :orgLevelSpecificName and olc=:olc and isactive").setParameter("olc", olc)
								.setParameter("orgLevelSpecificName", parentDepartmentName).uniqueResult();
					}
					
				}
				/*if (adminOrgDeptForm.getSortOrder().equals(Integer.parseInt(DepartmentConstent.SHORT_ORDER_START_SEQ.toString()))) {
					orgLocated.setSortOrder(++sortOrder);
				} else {
					orgLocated.setSortOrder(adminOrgDeptForm.getSortOrder());
				}*/
				orgLocated.setLocatedAtLevel(adminOrgDeptForm.getUnitLevelCode());
				orgLocated.setOrgLevelSpecificName(adminOrgDeptForm.getDepartmentNameEnglish());
				orgLocated.setOrgLevelSpecificNameLocal(adminOrgDeptForm.getDepartmentNameLocal());
				orgLocated.setOrgLevelSpecificShortName(adminOrgDeptForm.getDepartmentShortName());
				orgLocated.setIsactive(true);
				orgLocated.setOrguntDone(false);
				orgLocated.setParentOrgLocatedLevelCode(parentCode);
				/*Modified by pooja on 28-09-2015*/
				orgLocated.setOrgLocatedLevelVersion(1);
				Calendar date = new GregorianCalendar(2005, Calendar.JANUARY,1);
				orgLocated.setEffectiveDate(date.getTime());
				orgLocated.setCreatedBy(userId);
				orgLocated.setLastUpdatedBy(userId);
				orgLocated.setLastupdated(new Date());
				session.save(orgLocated);
				parentCodeList.put(adminOrgDeptForm.getDepartmentNameEnglish(), orgLocated.getOrgLocatedLevelCode());
				String accessLevel = adminOrgDeptForm.getFormAccessLevel();
				String coverage = adminOrgDeptForm.getChooseLevelAllOrSpecific();
				if ("F".equalsIgnoreCase(coverage)) {
					OrganizationCoverage orgCoverage = new OrganizationCoverage();
					orgCoverage.setOrgLocatedLevelCode(orgLocated.getOrgLocatedLevelCode());
					orgCoverage.setOrgCoverageEntityType(0);
					orgCoverage.setCoverage(coverage.charAt(0));
					orgCoverage.setCoverageDetailCode(0);
					orgCoverage.setIsactive(true);
					orgCoverage.setOrguntDone(false);
					session.save(orgCoverage);
				} else {
					if ("S".equalsIgnoreCase(accessLevel)) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), // State
																																						// Level
																																						// Unit
								coverage, orgLocated, maxCoverageCode, session);
					} else if ("D".equalsIgnoreCase(accessLevel)) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), // State
																																						// Level
																																						// Unit
								coverage, orgLocated, maxCoverageCode, session);

						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDistrictIds(), Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), // District
																																								// Level
																																								// Unit,
								coverage, orgLocated, maxCoverageCode, session);

					} else if ("T".equalsIgnoreCase(accessLevel)) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), // State
																																						// Level
																																						// Unit
								coverage, orgLocated, maxCoverageCode, session);

						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDistrictIds(), Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), // District
																																								// Level
																																								// Unit
								coverage, orgLocated, maxCoverageCode, session);

						maxCoverageCode = setCoverageState(adminOrgDeptForm.getSubdistrictIds(), Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()), // Sub-district
																																									// Level
																																									// Unit
								coverage, orgLocated, maxCoverageCode, session);

					} else if ("B".equalsIgnoreCase(accessLevel)) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), // State
																																						// Level
																																						// Unit
								coverage, orgLocated, maxCoverageCode, session);

						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDistrictIds(), Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), // District
																																								// Level
																																								// Unit
								coverage, orgLocated, maxCoverageCode, session);

						maxCoverageCode = setCoverageState(adminOrgDeptForm.getBlockIds(), Integer.parseInt(DepartmentConstent.BLOCK_CODE.toString()), // Block
																																						// Level
																																						// Unit
								coverage, orgLocated, maxCoverageCode, session);

					} else if ("V".equalsIgnoreCase(accessLevel)) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), // State
																																						// Level
																																						// Unit
								coverage, orgLocated, maxCoverageCode, session);

						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDistrictIds(), Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), // District
																																								// Level
																																								// Unit
								coverage, orgLocated, maxCoverageCode, session);

						maxCoverageCode = setCoverageState(adminOrgDeptForm.getSubdistrictIds(), Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()), // Sub-district
																																									// Level
																																									// Unit,
								coverage, orgLocated, maxCoverageCode, session);

						maxCoverageCode = setCoverageState(adminOrgDeptForm.getVillageIds(), Integer.parseInt(DepartmentConstent.VILLAGE_CODE.toString()), // Village
																																							// Level
																																							// Unit,
								coverage, orgLocated, maxCoverageCode, session);

					} else if ("A".equalsIgnoreCase(accessLevel)) {
						
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getAdminUnitEntityIds(), adminOrgDeptForm.getUnitLevelCode(), coverage, orgLocated, maxCoverageCode, session);
						
					}else if ("U".equalsIgnoreCase(accessLevel)) {
						
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getUrbanIds(),  adminOrgDeptForm.getUnitLevelCode(), coverage, orgLocated, maxCoverageCode, session);
						
					}
					else if ("X".equalsIgnoreCase(accessLevel)) {
						
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDpIds(),  Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
					}else if ("Y".equalsIgnoreCase(accessLevel)) {
						
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDpIds(),  Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getIpIds(),  Integer.parseInt(DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
					}else if ("Z".equalsIgnoreCase(accessLevel)) {
						
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDpIds(),  Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getIpIds(),  Integer.parseInt(DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
						
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getVpIds(),  Integer.parseInt(DepartmentConstent.VILLAGE_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					}
				}

			}

			transaction.commit();
			session.flush();
			session.clear();
			executeSetSortOrderDept(session, olc,isCenterUser);
			isSuccess = executeOrgUnitFuncation(session, olc, stateCode, null, null);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return true;

	}
	
	private Boolean executeSetSortOrderDept(Session session, Integer olc,boolean isCenter) {
		Boolean isExecuted = false;
		try {
			Query query = session.createSQLQuery("select * from set_sort_order_dept(:olc,:isCenter)");
			query.setParameter("olc", olc);
			query.setParameter("isCenter", isCenter);
			
			isExecuted = (Boolean) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			isExecuted = false;
		}
		return isExecuted;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getStateWiseAdminUnit(boolean isCenterUser, Integer slc) throws Exception {
		Session session = null;
		List<DeptAdminUnit> deptAdminUnitList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select admin_unit_level_code as adminUnitCode,parent_admin_unit_level_code as parentAdminCode,slc from Administration_Unit_Level where isactive and slc in(:landRegionSlc,:slc)");

			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("slc", slc);
			query.setParameter("landRegionSlc", Integer.parseInt(DepartmentConstent.LAND_REGION_SLC.toString()));
			query.addScalar("adminUnitCode");
			query.addScalar("parentAdminCode");
			query.addScalar("slc");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
	}

	/*@Override
	public boolean checkParentOrgLocatedLevelCode(Integer parentOrgLocatedLevelCode, Integer level) {
		Session session = null;
		boolean orgLocatedLevelCodeFlag = false;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder(
					"select case when located_at_level=:level then true else false end as orgLocatedLevelCodeFlag  from  org_located_at_levels   where isactive and org_located_level_code=:parentOrgLocatedLevelCode");

			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("parentOrgLocatedLevelCode", parentOrgLocatedLevelCode);
			query.setParameter("level", level);
			orgLocatedLevelCodeFlag = Boolean.parseBoolean(query.uniqueResult().toString());

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return orgLocatedLevelCodeFlag;
	}
*/
	/*@Override
	public Integer getEqualUnitLevelCode(Integer level) {
		Session session = null;
		Integer equalUnitLevelCode = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder(" select (parent_admin_unit_level_code+1) from administration_unit_level  where isactive and admin_unit_level_code=:level");

			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("level", level);
			equalUnitLevelCode = Integer.parseInt((query.uniqueResult().toString()));

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return equalUnitLevelCode;
	}*/

	@SuppressWarnings("unchecked")
	public List<DeptAdminUnit> getOfficeNamebyLevel(Integer olc, Integer level) {
		Session session = null;
		List<DeptAdminUnit> existOfficeList = new ArrayList<DeptAdminUnit>();
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select a.*,hirar.hierarchy from (select case when (oll.located_at_level=:stateLevel or oll.located_at_level=:centerLevel) then (select org_name from organization  where isactive and olc=oll.olc) else org_level_specific_name end "
					+ " as  adminLevelNameEng,oll.located_at_level as adminUnitCode,oll.org_located_level_code as orgLocatedLevelCode from  org_located_at_levels oll where isactive and olc=:olc)a"
					+ " inner join org_level_hierarchy_parent_to_child() hirar on a.orgLocatedLevelCode=hirar.org_located_level_code");
			if (level != null) {
				queryBuild.append(" and located_at_level=:level");
			}
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			if (level != null) {
				query.setParameter("level", level);
			}
			query.setParameter("olc", olc);
			query.setParameter("stateLevel", Integer.parseInt(DepartmentConstent.STATE_LEVEL.toString()));
			query.setParameter("centerLevel", Integer.parseInt(DepartmentConstent.CENTER_LEVEL.toString()));
			query.addScalar("adminLevelNameEng");
			query.addScalar("adminUnitCode");
			query.addScalar("orgLocatedLevelCode");
			query.addScalar("hierarchy");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			existOfficeList = query.list();
		

		} catch (Exception e) {
			throw e;
		}  
		
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return existOfficeList;

	}

	/* for Organization Unit Start */
/*Query Modified by pooja on 24-08-2015*/
	public String getStateName(int stateCode) throws Exception {
		Session session = null;
		String stateName = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select state_name_english from state where state_code=:stateCode and isactive");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			stateName = (String) query.uniqueResult();

		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return stateName;

	}

	@Override
	public Object[] getOrgUnitEntityData(Integer orgDeptCode, Integer stateCode) throws Exception {
		Session session = null;
		Object[] orgUnitEnityDetail = null;
		try {
			session = sessionFactory.openSession();

			String strquery = "select a.org_located_level_code,a.entity_lc,a.entity_type,a.org_unit_code from organization org,org_located_at_levels oll,org_units a where org.olc=oll.olc and oll.org_located_level_code=a.org_located_level_code and a.headoffice='H' and org.olc=:orgDeptCode and oll.located_at_level=:entityLevel";
			SQLQuery query = session.createSQLQuery(strquery);
			query.setParameter("orgDeptCode", orgDeptCode);
			if (stateCode == 0) {
				query.setParameter("entityLevel", 0);
			} else {
				query.setParameter("entityLevel", 1);
			}
			orgUnitEnityDetail = (Object[]) query.list().get(0);
			session.flush();
			session.clear();

		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return orgUnitEnityDetail;
	}

	@Override
	public Boolean saveOrganizationUnit(List<OrganizationUnit> orgUnitList) throws Exception {
		Session session = null;
		Boolean status = Boolean.FALSE;
		try {
			session = sessionFactory.openSession();
			SQLQuery query = session.createSQLQuery("select max(org_unit_code) from org_units");
			Integer maxOrgUnitPk = (Integer) query.uniqueResult();
			Transaction tx = session.beginTransaction();
			int counter = 1;
			for (OrganizationUnit orgUnitObj : orgUnitList) {
				if (null != orgUnitObj.getOrgUnitCode()) {
					query = session.createSQLQuery("update org_units set org_unit_name=:orgUnitName where org_unit_code=:orgUnitCode");
					query.setParameter("orgUnitName", orgUnitObj.getOrgUnitName());
					query.setParameter("orgUnitCode", orgUnitObj.getOrgUnitCode());
					query.executeUpdate();

				} else {
					orgUnitObj.setOrgUnitCode(maxOrgUnitPk + counter);
					session.save(orgUnitObj);

				}
				if (counter % 30 == 0) {
					session.flush();
					session.clear();
				}
				counter++;
			}
			tx.commit();
			status = Boolean.TRUE;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public List<OrganizationUnit> getOrganizationUnitList(Integer orgDeptCode, Integer stateCode) throws Exception {
		Session session = null;
		List<OrganizationUnit> orgUnitList = new ArrayList<OrganizationUnit>();
		try {

			session = sessionFactory.openSession();
			String qryStr = "select a.org_unit_code as orgUnitCode,a.org_unit_name as orgUnitName from organization  o, org_located_at_levels b, org_units a where o.olc=b.olc and b.org_located_level_code=a.org_located_level_code and a.headoffice='S' and o.olc=:orgDeptCode";
			SQLQuery orgUnitQuery = session.createSQLQuery(qryStr);
			orgUnitQuery.setParameter("orgDeptCode", orgDeptCode);
			orgUnitQuery.addScalar("orgUnitCode").addScalar("orgUnitName");
			orgUnitQuery.setResultTransformer(Transformers.aliasToBean(OrganizationUnit.class));
			orgUnitList = orgUnitQuery.list();

		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return orgUnitList;
	}

	/* OrganizationUnit Ends here */

	/**
	 * Get details of Office by OrgLocatedLevelCode and StateCode for extend
	 * organigation Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 */
	@SuppressWarnings("unchecked")
	public Object[] getExtendOrganizationBasicDetail(Integer OrgLocatedLevelCode, Integer stateCode) throws Exception {
		Session session = null;
		Object[] objectArray = null;
		try {
			objectArray = new Object[10];
			session = sessionFactory.openSession();
			
		     StringBuilder queryBuild = new StringBuilder("select case when childoll.located_at_level=1 then 'S'  when childoll.located_at_level=-1 then 'X' when childoll.located_at_level=-2 then 'Y'  when childoll.located_at_level=-3 then 'Z' "
		    	    + " when childoll.located_at_level=2 then 'D' when childoll.located_at_level=3 then 'T' "
					+ "when childoll.located_at_level=4 then 'V' when childoll.located_at_level=5 then 'B' when childoll.located_at_level>5 then 'A' else 'U' end  as pageAccessLevel,childoll.located_at_level as locatedAtLevel,"
					+ "childoll.org_level_specific_name as orgLevelSpecificName,childoll.org_level_specific_name_local as orgLevelSpecificNameLocal,childoll.org_level_specific_short_name as orgLevelSpecificShortName,"
					+ "aul.unit_level_name_english as unitLevelName,case when parentoll.located_at_level=1 then (select org_name from organization where olc=parentoll.olc ) else parentoll.org_level_specific_name end"
					+ " as parentOrgLevelSpecificName from org_located_at_levels childoll left join administration_unit_level aul on aul.admin_unit_level_code=childoll.located_at_level left join org_located_at_levels parentoll "
					+ "on childoll.parent_org_located_level_code=parentoll.org_located_level_code  where childoll.org_located_level_code =:OrgLocatedLevelCode and childoll.isactive ");

			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("OrgLocatedLevelCode", OrgLocatedLevelCode);
			query.addScalar("orgLevelSpecificName");
			query.addScalar("locatedAtLevel");
			query.addScalar("orgLevelSpecificNameLocal");
			query.addScalar("orgLevelSpecificShortName");
			query.addScalar("pageAccessLevel");
			query.addScalar("unitLevelName");
			query.addScalar("parentOrgLevelSpecificName");
			query.setResultTransformer(Transformers.aliasToBean(OrgLocatedAtLevels.class));
			List<OrgLocatedAtLevels> orgLocatedAtLevelsList = query.list();
			objectArray[0] = orgLocatedAtLevelsList;

			queryBuild = new StringBuilder("select org_coverage_entity_type as orgCoverageEntityType ,coverage from org_coverage  where isactive and org_located_level_code = :OrgLocatedLevelCode ");
			query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("OrgLocatedLevelCode", OrgLocatedLevelCode);
			query.addScalar("orgCoverageEntityType");
			query.addScalar("coverage");
			query.setResultTransformer(Transformers.aliasToBean(OrganizationCoverage.class));
			objectArray[1] = query.list();

			queryBuild = new StringBuilder("select ocd.entity_code as entityCode,oc.org_coverage_entity_type as orgCoverageEntityType  from org_coverage_detail ocd left join  org_coverage oc on ocd.coverage_code=oc.coverage_detail_code"
					+ "  where oc.org_located_level_code =:OrgLocatedLevelCode and oc.isactive and ocd.isactive");
			query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("OrgLocatedLevelCode", OrgLocatedLevelCode);
			query.addScalar("orgCoverageEntityType");
			query.addScalar("entityCode");
			query.setResultTransformer(Transformers.aliasToBean(OrganizationCoverageDetail.class));
			List<OrganizationCoverageDetail> organizationCoverageDetailList = query.list();
			objectArray[2] = organizationCoverageDetailList;

			String PageAccessLevel = orgLocatedAtLevelsList.get(Integer.parseInt(DepartmentConstent.ZERO_VALUE.toString())).getPageAccessLevel();
			if ("A".equalsIgnoreCase(PageAccessLevel)) {
				List<Integer> adminEntitieList = null;
				if (organizationCoverageDetailList != null && !organizationCoverageDetailList.isEmpty()) {
					adminEntitieList = new ArrayList<Integer>();
					for (OrganizationCoverageDetail organizationCoverageDetail : organizationCoverageDetailList) {
						adminEntitieList.add(organizationCoverageDetail.getEntityCode());
					}
				}

				Integer adminUnitLevelCode = orgLocatedAtLevelsList != null && !orgLocatedAtLevelsList.isEmpty() ? orgLocatedAtLevelsList.get(0).getLocatedAtLevel() : null;

				if (adminUnitLevelCode != null && adminEntitieList != null && !adminEntitieList.isEmpty()) {
					objectArray[3] = this.getAdminUnitLevelListbyCreteria(adminEntitieList, adminUnitLevelCode);
				} else {
					objectArray[3] = null;
				}
				if (adminEntitieList != null && !adminEntitieList.isEmpty()) {
					objectArray[4] = this.getAdminUnitLevelListbyCreteria(adminEntitieList, null);
				} else {
					objectArray[4] = null;
				}
			} 
			else if("X".equalsIgnoreCase(PageAccessLevel) ){
				List<Integer> existlbList = null;
				if (organizationCoverageDetailList != null && !organizationCoverageDetailList.isEmpty()) {
					existlbList = new ArrayList<Integer>();
					for (OrganizationCoverageDetail organizationCoverageDetail : organizationCoverageDetailList) {
							if(organizationCoverageDetail.getOrgCoverageEntityType()==-1){
								existlbList.add(organizationCoverageDetail.getEntityCode());
							}
					}
					existlbList.add(-99);
					objectArray[3]= this.getDistrictPanchayatList(1,stateCode,existlbList,Boolean.FALSE);
					objectArray[4]= this.getDistrictPanchayatList(1,stateCode,existlbList,Boolean.TRUE);
				}
				else{
					objectArray[3]= null;
					objectArray[4]= null;
				}
				
				
			}else if("Y".equalsIgnoreCase(PageAccessLevel) ){
				List<Integer> existdpList = null;
				List<Integer> existipList = null;
				if (organizationCoverageDetailList != null && !organizationCoverageDetailList.isEmpty()) {
					existdpList = new ArrayList<Integer>();
					existipList = new ArrayList<Integer>();
					existdpList.add(-99);
					existipList.add(-99);
					for (OrganizationCoverageDetail organizationCoverageDetail : organizationCoverageDetailList) {
							if(organizationCoverageDetail.getOrgCoverageEntityType()==-1){
								existdpList.add(organizationCoverageDetail.getEntityCode());
							}else if(organizationCoverageDetail.getOrgCoverageEntityType()==-2){
								existipList.add(organizationCoverageDetail.getEntityCode());
							}
					}
					
					objectArray[3]= this.getDistrictPanchayatList(1,stateCode,existdpList,Boolean.FALSE);
					objectArray[4]= this.getDistrictPanchayatList(1,stateCode,existdpList,Boolean.TRUE);
					
					objectArray[5]= this.getlbListbylbCode(existipList);
					
					
					
				}
				else{
					objectArray[3]= null;
					objectArray[4]= null;
					
					objectArray[5]= null;
					
				}
				
				
			}else if("Z".equalsIgnoreCase(PageAccessLevel) ){
				List<Integer> existdpList = null;
				List<Integer> existipList = null;
				List<Integer> existvpList = null;
				if (organizationCoverageDetailList != null && !organizationCoverageDetailList.isEmpty()) {
					existdpList = new ArrayList<Integer>();
					existipList = new ArrayList<Integer>();
					existvpList = new ArrayList<Integer>();
					existdpList.add(-99);
					existipList.add(-99);
					existvpList.add(-99);
					for (OrganizationCoverageDetail organizationCoverageDetail : organizationCoverageDetailList) {
							if(organizationCoverageDetail.getOrgCoverageEntityType()==-1){
								existdpList.add(organizationCoverageDetail.getEntityCode());
							}else if(organizationCoverageDetail.getOrgCoverageEntityType()==-2){
								existipList.add(organizationCoverageDetail.getEntityCode());
							}else if(organizationCoverageDetail.getOrgCoverageEntityType()==-3){
								existvpList.add(organizationCoverageDetail.getEntityCode());
							}
					}
					
					objectArray[3]= this.getDistrictPanchayatList(1,stateCode,existdpList,Boolean.FALSE);
					objectArray[4]= this.getDistrictPanchayatList(1,stateCode,existdpList,Boolean.TRUE);
					
					objectArray[5]= this.getlbListbylbCode(existipList);
					objectArray[6]= this.getlbListbylbCode(existvpList);
					
					
					
					
					
				}
				else{
					objectArray[3]= null;
					objectArray[4]= null;
					
					objectArray[5]= null;
					objectArray[6]= null;
					
					
				}
				
				
			}
			
			else if("U".equalsIgnoreCase(PageAccessLevel) ){
				List<Integer> existlbList = null;
				if (organizationCoverageDetailList != null && !organizationCoverageDetailList.isEmpty()) {
					existlbList = new ArrayList<Integer>();
					int lbTypeCode=0;
					for (OrganizationCoverageDetail organizationCoverageDetail : organizationCoverageDetailList) {
					int entityType=organizationCoverageDetail.getOrgCoverageEntityType();
							if(entityType<0 && entityType !=-1 && entityType !=-2 && entityType !=-3){
								existlbList.add(organizationCoverageDetail.getEntityCode());
								lbTypeCode=entityType;
							}
					}
					existlbList.add(-99);
					objectArray[3]= this.getDistrictPanchayatList(-lbTypeCode,stateCode,existlbList,Boolean.FALSE);
					objectArray[4]= this.getDistrictPanchayatList(-lbTypeCode,stateCode,existlbList,Boolean.TRUE);
				}
				else{
					objectArray[3]= null;
					objectArray[4]= null;
				}
				
				
			}
			
			/*else if("L".equalsIgnoreCase(PageAccessLevel) || "P".equalsIgnoreCase(PageAccessLevel) || "G".equalsIgnoreCase(PageAccessLevel)){
				List<Integer> localbodyEntitylist = null;
				if (organizationCoverageDetailList != null && !organizationCoverageDetailList.isEmpty()) {
					localbodyEntitylist = new ArrayList<Integer>();
					for (OrganizationCoverageDetail organizationCoverageDetail : organizationCoverageDetailList) {
							localbodyEntitylist.add(organizationCoverageDetail.getEntityCode());
					}
			    }
				
				Integer localbodyTypeCode =  orgLocatedAtLevelsList != null && !orgLocatedAtLevelsList.isEmpty() ? orgLocatedAtLevelsList.get(0).getLocatedAtLevel() : null;

				if(localbodyTypeCode != null){
					Boolean flag = true;
					objectArray[3]= this.getLocalbodyUnitLevelListbyCriteria(localbodyEntitylist, localbodyTypeCode,stateCode,flag);
				}else{
					objectArray[3]= null;
				}
				if(localbodyEntitylist != null && !localbodyEntitylist.isEmpty()){
					
					Boolean flag = false;
					objectArray[4] = this.getLocalbodyUnitLevelListbyCriteria(localbodyEntitylist, localbodyTypeCode,stateCode,flag);
				}else{
					objectArray[4] = null;
				}
			}
			*/
			else {
				List<Integer> districtCodeList = new ArrayList<Integer>();
				List<Integer> subDistrictList = null;
				List<Integer> villageList = null;
				districtCodeList.add(Integer.parseInt(DepartmentConstent.ZERO_VALUE.toString()));
				if ("T".equalsIgnoreCase(PageAccessLevel) || "V".equalsIgnoreCase(PageAccessLevel)) {
					subDistrictList = new ArrayList<Integer>();
					subDistrictList.add(Integer.parseInt(DepartmentConstent.ZERO_VALUE.toString()));
				}
				if ("V".equalsIgnoreCase(PageAccessLevel)) {
					villageList = new ArrayList<Integer>();
					villageList.add(Integer.parseInt(DepartmentConstent.ZERO_VALUE.toString()));
				}
				for (OrganizationCoverageDetail organizationCoverageDetail : organizationCoverageDetailList) {
					if (organizationCoverageDetail.getOrgCoverageEntityType().equals(Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()))) {
						districtCodeList.add(organizationCoverageDetail.getEntityCode());
					}
					if (organizationCoverageDetail.getOrgCoverageEntityType().equals(Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()))) {
						subDistrictList.add(organizationCoverageDetail.getEntityCode());
					}

					if (organizationCoverageDetail.getOrgCoverageEntityType().equals(Integer.parseInt(DepartmentConstent.VILLAGE_CODE.toString()))) {
						villageList.add(organizationCoverageDetail.getEntityCode());
					}
				}
				objectArray[3] = districtDAO.getDistrictListbyCreteria(stateCode, districtCodeList);

				objectArray[4] = districtDAO.getDistrictListbyCreteria(Integer.parseInt(DepartmentConstent.ZERO_VALUE.toString()), districtCodeList);

				if (subDistrictList != null && !subDistrictList.isEmpty()) {
					objectArray[5] = subDistrictDAO.getSubDistrictListbyCreteria(null, subDistrictList, null);

				} else {
					objectArray[5] = null;

				}

				if (villageList != null && !villageList.isEmpty()) {
					objectArray[6] = villageDAO.getVillageListbyCreteria(null, null, villageList, null);
				} else {
					objectArray[6] = null;
				}

			}

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objectArray;
	}

	/**
	 * Get List<DeptAdminUnitEntity> base on Creteria for extend organigation
	 * Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 */
	@SuppressWarnings("unchecked")
	public List<DeptAdminUnitEntity> getAdminUnitLevelListbyCreteria(List<Integer> adminEntitieList, Integer adminUnitLevelCode) {

		Session session = null;
		List<DeptAdminUnitEntity> listAdminEntities = new ArrayList<DeptAdminUnitEntity>();
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("from DeptAdminUnitEntity where isactive is true and adminUnitEntityCode");
			if (adminUnitLevelCode != null) {
				queryBuild.append(" not in(:adminEntitieList) and  adminUnitLevelCode=:adminUnitLevelCode");
			} else {
				queryBuild.append(" in(:adminEntitieList) ");
			}
			queryBuild.append(" order by adminEntityNameEnglish");
			Query query = session.createQuery(queryBuild.toString());
			query.setParameterList("adminEntitieList", adminEntitieList);
			if (adminUnitLevelCode != null) {
				query.setParameter("adminUnitLevelCode", adminUnitLevelCode);
			}
			listAdminEntities = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listAdminEntities;
	}

	/**
	 * Check Coverage are Change or not of Office for extend organigation Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 *//*
	public boolean checkDeptCoverageAreaChangeotNot(Integer orgCverageEntityType, List<Integer> newItems, Integer orgLocatedLevelCode) throws Exception {
		Session session = null;
		boolean checkDeptCoverageAreaFlag = false;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select entity_code from org_coverage_detail where isactive and coverage_code=(select coverage_detail_code from org_coverage where isactive and "
					+ "org_located_level_code= :orgLocatedLevelCode");
			if (orgCverageEntityType != null) {
				queryBuild.append(" and org_coverage_entity_type =:orgCverageEntityType ");
			}
			queryBuild.append(" ) and entity_code in(:newItems) ");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameterList("newItems", newItems);
			query.setParameter("orgLocatedLevelCode", orgLocatedLevelCode);
			if (orgCverageEntityType != null) {
				query.setParameter("orgCverageEntityType", orgCverageEntityType);
			}
			List<ArrayList> list = query.list();
			checkDeptCoverageAreaFlag = list.size() != newItems.size();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return checkDeptCoverageAreaFlag;
	}
*/
	/**
	 * Save New Coverage information of Office for extend organigation Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 */
	@SuppressWarnings("unchecked")
	public boolean saveOrganizationUnits(AdminOrgDeptForm adminOrgDeptForm) throws Exception {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		int maxCoverageCode = 0;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			maxCoverageCode = organizationDAO.getMaxCoverageCode() + 1;
			Integer orgLocatedLevelCode = adminOrgDeptForm.getOrgLocatedLevelCode();
			Integer olc = null;
			String accessLevel = adminOrgDeptForm.getFormAccessLevel();
			/*Modified by pooja on 29-09-2015*/
			Criteria criteria = session.createCriteria(OrgLocatedAtLevels.class);
			criteria.add(Restrictions.eq("orgLocatedLevelCode",orgLocatedLevelCode));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			OrgLocatedAtLevels orgLocated = (OrgLocatedAtLevels)criteria.uniqueResult();
			olc = orgLocated.getOlc();
  
			

			/*
			 * OrgLocatedAtLevels neworgLocated = new OrgLocatedAtLevels(); ;
			 * neworgLocated.setOrganization(organization);
			 * neworgLocated.setLocatedAtLevel(orgLocated.getLocatedAtLevel());
			 * neworgLocated.setIsactive(true);
			 * neworgLocated.setOrgLevelSpecificName
			 * (orgLocated.getOrgLevelSpecificName());
			 * neworgLocated.setOrgLevelSpecificNameLocal
			 * (orgLocated.getOrgLevelSpecificNameLocal());
			 * neworgLocated.setOrgLevelSpecificShortName
			 * (orgLocated.getOrgLevelSpecificShortName());
			 * neworgLocated.setParentOrgLocatedLevelCode
			 * (orgLocated.getParentOrgLocatedLevelCode());
			 * neworgLocated.setOrguntDone(false);
			 * neworgLocated.setSortOrder(orgLocated.getSortOrder());
			 * 
			 * session.save(neworgLocated);
			 */
			List<OrganizationCoverage> organizationCoverageList = session.createQuery("from OrganizationCoverage where isactive=true and orgLocatedLevelCode=:orgLocatedLevelCode").setParameter("orgLocatedLevelCode", orgLocatedLevelCode).list();
			String coverage = adminOrgDeptForm.getChooseLevelAllOrSpecific();
			if ("F".equalsIgnoreCase(coverage)) {
				List<Integer> coverageDetailCodeList=new ArrayList<Integer>();
				for (OrganizationCoverage organizationCoverage : organizationCoverageList) {
					organizationCoverage.setIsactive(false);
					coverageDetailCodeList.add(organizationCoverage.getCoverageDetailCode());
					session.update(organizationCoverage);
				}
				
				
				  if (coverageDetailCodeList != null) {
					  Query query =  session.createQuery( "from OrganizationCoverageDetail where isactive=true and coverageCode IN(:coverageDetailCodeList)" ); 
					  query.setParameterList("coverageDetailCodeList",coverageDetailCodeList); List<OrganizationCoverageDetail>
					  organizationCoverageDetailList = query.list();
				  
					  for (OrganizationCoverageDetail organizationCoverageDetail :
						  organizationCoverageDetailList) {
						  organizationCoverageDetail.setIsactive(false);
						  session.update(organizationCoverageDetail); 
					  } 
				  }
				 
				
				
				OrganizationCoverage orgCoverage = new OrganizationCoverage();
				orgCoverage.setOrgLocatedLevelCode(orgLocatedLevelCode);
				orgCoverage.setOrgCoverageEntityType(0);
				orgCoverage.setCoverage(coverage.charAt(0));
				orgCoverage.setCoverageDetailCode(0);
				orgCoverage.setIsactive(true);
				orgCoverage.setOrguntDone(false);
				session.save(orgCoverage);
			} else {
				Integer coverageCodeDistrict = null, coverageCodeSubdistrict = null, coverageCodeVillage = null, coverageCodeBlock = null, coverageCodeAdmini = null;
				Integer coverageCodeDP=null, coverageCodeIP=null, coverageCodeVP=null, coverageCodeU=null;
				
				if (organizationCoverageList != null && !organizationCoverageList.isEmpty()) {
					for (OrganizationCoverage organizationCoverage : organizationCoverageList) {
						if (organizationCoverage.getOrgCoverageEntityType() == Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString())) {
							coverageCodeDistrict = organizationCoverage.getCoverageDetailCode();
						} else if (organizationCoverage.getOrgCoverageEntityType() == Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString())) {
							coverageCodeSubdistrict = organizationCoverage.getCoverageDetailCode();
						} else if (organizationCoverage.getOrgCoverageEntityType() == Integer.parseInt(DepartmentConstent.VILLAGE_CODE.toString())) {
							coverageCodeVillage = organizationCoverage.getCoverageDetailCode();
						} else if (organizationCoverage.getOrgCoverageEntityType() == Integer.parseInt(DepartmentConstent.BLOCK_CODE.toString())) {
							coverageCodeBlock = organizationCoverage.getCoverageDetailCode();
						}else if (organizationCoverage.getOrgCoverageEntityType() == Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString())) {
							coverageCodeDP = organizationCoverage.getCoverageDetailCode();
						}else if (organizationCoverage.getOrgCoverageEntityType() == Integer.parseInt(DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString())) {
							coverageCodeIP = organizationCoverage.getCoverageDetailCode();
						}else if (organizationCoverage.getOrgCoverageEntityType() == Integer.parseInt(DepartmentConstent.VILLAGE_PANCHAYAT_CODE.toString())) {
							coverageCodeVP = organizationCoverage.getCoverageDetailCode();
						}else if (organizationCoverage.getOrgCoverageEntityType()<0 && organizationCoverage.getOrgCoverageEntityType()!=-1 && organizationCoverage.getOrgCoverageEntityType()!=-2 && organizationCoverage.getOrgCoverageEntityType()!=-3) {
							coverageCodeU = organizationCoverage.getCoverageDetailCode();
						}
						else {
							coverageCodeAdmini = organizationCoverage.getCoverageDetailCode();
						}
						organizationCoverage.setOrguntDone(false);
						session.update(organizationCoverage);

					}
				}
				
				List<OrganizationCoverageDetail> disableOrganizationCoverageDetailList = new ArrayList<OrganizationCoverageDetail>();
				if ("U".equalsIgnoreCase(accessLevel)) {
					if (coverageCodeU != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getUrbanIds(), coverageCodeU, session);
					}
				} 
				else if ("X".equalsIgnoreCase(accessLevel)) {
					if (coverageCodeDP != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getDpIds(), coverageCodeDP, session);
					}
				} else if ("Y".equalsIgnoreCase(accessLevel)) {
					if (coverageCodeDP != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getDpIds(), coverageCodeDP, session);
					}
					if (coverageCodeIP != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getIpIds(), coverageCodeIP, session);
					}
					List<Integer> dpList = adminOrgDeptForm.getDpIds() != null ? commonService.createListFormString(adminOrgDeptForm.getDpIds()) : null;
					if (dpList != null) {
						disableOrganizationCoverageDetailList.addAll(this.getCoverageListParttoFull(dpList, Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), orgLocatedLevelCode));
					}
				} else if ("Z".equalsIgnoreCase(accessLevel)) {
					if (coverageCodeDP != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getDpIds(), coverageCodeDP, session);
					}
					if (coverageCodeIP != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getIpIds(), coverageCodeIP, session);
					}
					if (coverageCodeVP != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getVpIds(), coverageCodeVP, session);
					}
					
					List<Integer> dpList = adminOrgDeptForm.getDpIds() != null ? commonService.createListFormString(adminOrgDeptForm.getDpIds()) : null;
					if (dpList != null) {
						disableOrganizationCoverageDetailList.addAll(this.getCoverageListParttoFull(dpList, Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), orgLocatedLevelCode));
					}

					List<Integer> ipList = adminOrgDeptForm.getIpIds() != null ? commonService.createListFormString(adminOrgDeptForm.getIpIds()) : null;
					if (ipList != null) {
						disableOrganizationCoverageDetailList.addAll(this.getCoverageListParttoFull(ipList, Integer.parseInt(DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString()), orgLocatedLevelCode));
					}

					
				} else
				if ("D".equalsIgnoreCase(accessLevel)) {
					if (coverageCodeDistrict != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getDistrictIds(), coverageCodeDistrict, session);
					}
				} else if ("T".equalsIgnoreCase(accessLevel)) {
					if (coverageCodeDistrict != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getDistrictIds(), coverageCodeDistrict, session);
					}
					if (coverageCodeSubdistrict != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getSubdistrictIds(), coverageCodeSubdistrict, session);
					}
					List<Integer> districtList = adminOrgDeptForm.getDistrictIds() != null ? commonService.createListFormString(adminOrgDeptForm.getDistrictIds()) : null;
					if (districtList != null) {
						disableOrganizationCoverageDetailList.addAll(this.getCoverageListParttoFull(districtList, Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), orgLocatedLevelCode));
					}
				} else if ("V".equalsIgnoreCase(accessLevel)) {
					if (coverageCodeDistrict != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getDistrictIds(), coverageCodeDistrict, session);
					}
					if (coverageCodeSubdistrict != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getSubdistrictIds(), coverageCodeSubdistrict, session);
					}
					if (coverageCodeVillage != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getVillageIds(), coverageCodeVillage, session);
					}

					List<Integer> districtList = adminOrgDeptForm.getDistrictIds() != null ? commonService.createListFormString(adminOrgDeptForm.getDistrictIds()) : null;
					List<Integer> subDistrictList = adminOrgDeptForm.getSubdistrictIds() != null ? commonService.createListFormString(adminOrgDeptForm.getSubdistrictIds()) : null;
					if (districtList != null) {
						disableOrganizationCoverageDetailList.addAll(this.getCoverageListParttoFull(districtList, Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), orgLocatedLevelCode));
					}
					if (subDistrictList != null) {
						disableOrganizationCoverageDetailList.addAll(this.getCoverageListParttoFull(subDistrictList, Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()), orgLocatedLevelCode));
					}
				} else if ("B".equalsIgnoreCase(accessLevel)) {
					if (coverageCodeDistrict != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getDistrictIds(), coverageCodeDistrict, session);
					}
					if (coverageCodeBlock != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getBlockIds(), coverageCodeBlock, session);
					}
					List<Integer> districtList = adminOrgDeptForm.getDistrictIds() != null ? commonService.createListFormString(adminOrgDeptForm.getDistrictIds()) : null;
					if (districtList != null) {
						disableOrganizationCoverageDetailList.addAll(this.getCoverageListParttoFull(districtList, Integer.parseInt(DepartmentConstent.BLOCK_CODE.toString()), orgLocatedLevelCode));
					}
				} else {
					if (coverageCodeAdmini != null) {
						this.setCoverageDetailforExistingCoverage(adminOrgDeptForm.getAdminUnitEntityIds(), coverageCodeAdmini, session);
					}
				}

				for (OrganizationCoverageDetail organizationCoverageDetailid : disableOrganizationCoverageDetailList) {
					OrganizationCoverageDetail organizationCoverageDetail = (OrganizationCoverageDetail) session.get(OrganizationCoverageDetail.class, organizationCoverageDetailid.getOrgCoverageDetailId());
					organizationCoverageDetail.setIsactive(false);
					session.update(organizationCoverageDetail);
				}
				
				if ("S".equalsIgnoreCase(accessLevel)) {
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
				} else if ("D".equalsIgnoreCase(accessLevel)) {
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);

					if (coverageCodeDistrict == null) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDistrictIds(), Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					}

				} else if ("T".equalsIgnoreCase(accessLevel) && coverageCodeSubdistrict == null) {
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);

					if (coverageCodeDistrict == null) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDistrictIds(), Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					}

					if (coverageCodeSubdistrict == null) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getSubdistrictIds(), Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					}

				} else if ("B".equalsIgnoreCase(accessLevel)) {
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);

					if (coverageCodeDistrict == null) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDistrictIds(), Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					}

					if (coverageCodeBlock == null) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getBlockIds(), Integer.parseInt(DepartmentConstent.BLOCK_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					}

				} else if ("V".equalsIgnoreCase(accessLevel)) {
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getStateIds(), Integer.parseInt(DepartmentConstent.STATE_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);

					if (coverageCodeDistrict == null) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getDistrictIds(), Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					}

					if (coverageCodeSubdistrict == null) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getSubdistrictIds(), Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					}
					if (coverageCodeVillage == null) {
						maxCoverageCode = setCoverageState(adminOrgDeptForm.getVillageIds(), Integer.parseInt(DepartmentConstent.VILLAGE_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					}

				} else if ("A".equalsIgnoreCase(accessLevel) && coverageCodeAdmini == null) {
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getAdminUnitEntityIds(), orgLocated.getLocatedAtLevel(), coverage, orgLocated, maxCoverageCode, session);

				}
				/*else if ("L".equalsIgnoreCase(accessLevel) || "P".equalsIgnoreCase(accessLevel) || "G".equalsIgnoreCase(accessLevel)) {
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getAdminUnitEntityIds(), orgLocated.getLocatedAtLevel(), coverage, orgLocated, maxCoverageCode, session);

				}*/
				else if ("U".equalsIgnoreCase(accessLevel)) {
					
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getUrbanIds(),  orgLocated.getLocatedAtLevel(), coverage, orgLocated, maxCoverageCode, session);
					
				}
				else if ("X".equalsIgnoreCase(accessLevel)) {
					
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getDpIds(),  Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					
				}else if ("Y".equalsIgnoreCase(accessLevel)) {
					
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getDpIds(),  Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getIpIds(),  Integer.parseInt(DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					
				}else if ("Z".equalsIgnoreCase(accessLevel)) {
					
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getDpIds(),  Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getIpIds(),  Integer.parseInt(DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
					
					maxCoverageCode = setCoverageState(adminOrgDeptForm.getVpIds(),  Integer.parseInt(DepartmentConstent.VILLAGE_PANCHAYAT_CODE.toString()), coverage, orgLocated, maxCoverageCode, session);
				}


			}
			transaction.commit();
			session.flush();
			session.clear();
			
		
			
			session.flush();
			session.clear();
			
			isSuccess = executeOrgUnitFuncation(session, olc, adminOrgDeptForm.getStateCode(), orgLocatedLevelCode, DepartmentConstent.EXTEND_ORGANIZATION_UNITS_FLAG.toString());
			
			Query query =session.createSQLQuery("select * from reset_coverage_by_orglocated(:orgLocatedLevelCode)");
			query.setParameter("orgLocatedLevelCode", orgLocatedLevelCode);
			Boolean resetCoverag= (Boolean)query.uniqueResult();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

	/**
	 * @author Maneesh Kumar since 28-July-2015
	 * @param entityCodeList
	 * @param entityType
	 * @param orgLocatedLevelCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrganizationCoverageDetail> getCoverageListParttoFull(List<Integer> entityCodeList, Integer entityType, Integer orgLocatedLevelCode) {
		List<OrganizationCoverageDetail> organizationCoverageDetailList = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("");
			if (entityType.equals(Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()))) {
				queryBuild.append(" select ocd.org_coverage_detail_id as orgCoverageDetailId from org_coverage_detail ocd left join  org_coverage oc on ocd.coverage_code=oc.coverage_detail_code"
						+ " where oc.org_located_level_code =:orgLocatedLevelCode and oc.isactive and ocd.isactive  and oc.org_coverage_entity_type=3  and ocd.entity_code in (select tlc from subdistrict where isactive and dlc in(:entityCodeList)) "
						+ " union all " + " select ocd.org_coverage_detail_id as orgCoverageDetailId from org_coverage_detail ocd left join  org_coverage oc on ocd.coverage_code=oc.coverage_detail_code "
						+ " where oc.org_located_level_code =:orgLocatedLevelCode and oc.isactive and ocd.isactive  and oc.org_coverage_entity_type=4  and ocd.entity_code in (select vlc from village where isactive and dlc in(:entityCodeList))");
			} else if (entityType.equals(Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()))) {
				queryBuild.append(" select ocd.org_coverage_detail_id as orgCoverageDetailId from org_coverage_detail ocd left join  org_coverage oc on ocd.coverage_code=oc.coverage_detail_code"
						+ " where oc.org_located_level_code =:orgLocatedLevelCode and oc.isactive and ocd.isactive  and oc.org_coverage_entity_type=4  and ocd.entity_code in (select vlc from village where isactive and tlc in(:entityCodeList)) ");
			} else if (entityType.equals(Integer.parseInt(DepartmentConstent.BLOCK_CODE.toString()))) {
				queryBuild.append(" select ocd.org_coverage_detail_id as orgCoverageDetailId from org_coverage_detail ocd left join  org_coverage oc on ocd.coverage_code=oc.coverage_detail_code"
						+ " where oc.org_located_level_code =:orgLocatedLevelCode and oc.isactive and ocd.isactive  and oc.org_coverage_entity_type=5  and ocd.entity_code in (select blc from block where isactive and dlc in(:entityCodeList)) ");
			}else if (entityType.equals(Integer.parseInt(DepartmentConstent.DISTRICT_PANCHAYAT_CODE.toString()))) {
				queryBuild.append("select ocd.org_coverage_detail_id as orgCoverageDetailId from org_coverage_detail ocd left join org_coverage oc on ocd.coverage_code=oc.coverage_detail_code "
				+ "where oc.org_located_level_code=:orgLocatedLevelCode and oc.isactive and ocd.isactive  and oc.org_coverage_entity_type=-2  and "
				+ "ocd.entity_code in(select local_body_code from localbody where isactive and parent_lblc in(:entityCodeList)) "
				+ " union all  "
				+ "select ocd.org_coverage_detail_id as orgCoverageDetailId from org_coverage_detail ocd left join  org_coverage oc on ocd.coverage_code=oc.coverage_detail_code where "
				+ "oc.org_located_level_code=:orgLocatedLevelCode and oc.isactive and ocd.isactive  and oc.org_coverage_entity_type=-3  and ocd.entity_code in(select local_body_code from localbody where "
				+ "isactive and parent_lblc in(select local_body_code from localbody where isactive and parent_lblc in(:entityCodeList)))");
			}else if (entityType.equals(Integer.parseInt(DepartmentConstent.INTERMEDIATE_PANCHAYAT_CODE.toString()))) {
				queryBuild.append("select ocd.org_coverage_detail_id as orgCoverageDetailId from org_coverage_detail ocd left join  org_coverage oc on ocd.coverage_code=oc.coverage_detail_code where "
				+ "oc.org_located_level_code=:orgLocatedLevelCode and oc.isactive and ocd.isactive  and oc.org_coverage_entity_type=-3  and ocd.entity_code in(select local_body_code from localbody where "
				+ "isactive and parent_lblc in(:entityCodeList))");
			}
			
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameterList("entityCodeList", entityCodeList);
			query.setParameter("orgLocatedLevelCode", orgLocatedLevelCode);
			query.addScalar("orgCoverageDetailId");
			query.setResultTransformer(Transformers.aliasToBean(OrganizationCoverageDetail.class));
			organizationCoverageDetailList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationCoverageDetailList;
	}

	/**
	 * @author Maneesh Kumar since 28-July-2015
	 * @param selectedIds
	 * @param coverageCode
	 * @param session
	 */

	private void setCoverageDetailforExistingCoverage(String selectedIds, Integer coverageCode, Session session) {
		if (selectedIds != null && !"".equals(selectedIds)) {
			String[] arrId = selectedIds.split("\\,");
			for (String entityId : arrId) {
				setOrgCoverageDetail(coverageCode, Integer.parseInt(entityId), session);
			}

		}
	}
	
	public boolean validateEntityListforFullCoverage(List<Integer> entityList,Integer entityType,Integer parentEntityCode) throws Exception {
		boolean validateFullCoverageFlag=false;
		Session session=null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select  case when count(1)=(select count(1) from");
			if(entityType.equals(Integer.parseInt(DepartmentConstent.DISTRICT_CODE.toString()))){
				queryBuild.append(" district   where isactive and slc=:parentEntityCode) then true else false end  from district d where d.dlc in(:entityList) and d.slc=:parentEntityCode and isactive");	
			}else if(entityType.equals(Integer.parseInt(DepartmentConstent.SUBDISTRICT_CODE.toString()))){
				queryBuild.append(" subdistrict   where isactive and dlc=:parentEntityCode) then true else false end  from subdistrict t where t.tlc in(:entityList) and t.dlc=:parentEntityCode and isactive");	
			}
			else if(entityType.equals(Integer.parseInt(DepartmentConstent.VILLAGE_CODE.toString()))){
				queryBuild.append(" village  where isactive and tlc=:parentEntityCode) then true else false end  from village v where v.vlc in(:entityList) and v.tlc=:parentEntityCode and isactive");	
			}else if(entityType.equals(Integer.parseInt(DepartmentConstent.BLOCK_CODE.toString()))){
				queryBuild.append(" block   where isactive and dlc=:parentEntityCode) then true else false end  from block b where b.blc in(:entityList) and b.dlc=:parentEntityCode and isactive");	
			}else{
				queryBuild.append(" from administration_unit_entity  where isactive and  admin_unit_level_code=:parentEntityCode) then true else false end from administration_unit_entity au " +
									" where au.admin_unit_entity_code in(:entityList) and admin_unit_level_code=:parentEntityCode");	
			}
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("parentEntityCode", parentEntityCode);
			query.setParameterList("entityList", entityList);
			
			validateFullCoverageFlag = (boolean)query.uniqueResult();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return validateFullCoverageFlag;
	}
 
	/**
	 * Code used to get Organization Located Level List except top level 
	 * for Rename Specific Name of organization Levels
	 * @author Pooja @since 17-09-2015
	 * @param olc
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrgLocatedAtLevels> getOrgLocatedLevelListExceptTop(Integer olc) throws Exception {
		Session session = null;
		List<OrgLocatedAtLevels> listOrgLevels = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("SELECT org_located_at_levels.org_located_level_code as orgLocatedLevelCode, " +
					"org_located_at_levels.located_at_level AS locatedAtLevel,org_level_specific_name as orgLevelSpecificName , " +
					"(CASE org_located_at_levels.located_at_level WHEN 1 THEN 'STATE' WHEN 2 THEN 'DISTRICT' WHEN 3 THEN 'SUBDISTRICT' " +
					"WHEN 5 THEN 'BLOCK' WHEN 4 THEN 'VILLAGE' ELSE (select UPPER(unit_level_name_english) " +
					"from administration_unit_level where admin_unit_level_code = org_located_at_levels.located_at_level)END) AS locatedLevel " +
					"FROM org_located_at_levels,organization WHERE organization.olc = org_located_at_levels.olc AND " +
					"organization.isactive = TRUE AND length(trim(coalesce(org_located_at_levels.org_level_specific_name,''))) > 0 " +
					"AND organization.olc =:olc AND org_located_at_levels.isactive order by orgLevelSpecificName");
			
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("olc", olc);
			query.addScalar("orgLocatedLevelCode");
			query.addScalar("orgLevelSpecificName");
			query.addScalar("locatedAtLevel");
			query.addScalar("locatedLevel");
			query.setResultTransformer(Transformers.aliasToBean(OrgLocatedAtLevels.class));
			listOrgLevels = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listOrgLevels;
	}
	
	/**
	 * code for saving attachment details in Update Organization Levels
	 * @author Pooja @since 21-09-2015
	 * @param attachedList
	 * @param orgLocatedLevelCode
	 */
	@Override
	public Boolean saveDataInAttachment(List<AttachedFilesForm> attachedList, Integer orgLocatedLevelCode) throws Exception {

		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Attachment attachment = null;
		Query query = null;
		Boolean flag = false;
		GovernmentOrderEntityWise goe = null;
		Integer ver = 1;
		try {
			if (attachedList != null) {
				query = sessionObj.createSQLQuery("select max(entity_version) from government_order_entitywise where entity_code=:orgLocatedLevelCode and entity_type=:type")
						.setParameter("orgLocatedLevelCode",orgLocatedLevelCode, Hibernate.INTEGER).setParameter("type", 'O', Hibernate.CHARACTER);
				ver = (Integer)query.uniqueResult();
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					query = sessionObj.createQuery("from GovernmentOrderEntityWise where entityCode=:orgLocatedLevelCode and entityVersion=:ver and entityType=:type").setParameter("orgLocatedLevelCode", orgLocatedLevelCode, Hibernate.INTEGER)
							.setParameter("ver",ver, Hibernate.INTEGER).setParameter("type", 'O', Hibernate.CHARACTER);
					goe = (GovernmentOrderEntityWise) query.list().get(0);
					if (goe != null) {
						GovernmentOrder govorder = new GovernmentOrder();
						govorder.setOrderCode(goe.getOrderCode());
						attachment.setGovernmentOrder(govorder);
					}
					sessionObj.save(attachment);
					tx1.commit();
				}
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
			tx1.rollback();
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
			
		}
		return flag;
	}

/*	@Override
	public List<DeptAdminUnit> getChildofExistingParentLevelbyOrganization(Integer parentAdminLvlCode, Integer slc, List<Integer> adminLvlCodeList, boolean ExistLvlFlag,Integer ExistLvlCode) throws Exception {
		List<DeptAdminUnit> deptAdminUnitList = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder sb = new StringBuilder("select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode "
					+ "from Administration_Unit_Level a where a.isactive and a.ispublish='P' and a.admin_unit_level_code >=1 and a.admin_unit_level_code<=5 ");
			if (adminLvlCodeList != null) {
				sb.append(" and admin_unit_level_code not in(:adminLvlCodeList)");
			}
			sb.append(" and a.parent_admin_unit_level_code");

			if (!ExistLvlFlag) {
				sb.append(">");
			}
			if (ExistLvlFlag &&  ExistLvlCode >= 1 && ExistLvlCode <= 5) {
				sb.append("=-11");
			} else {
				if (parentAdminLvlCode >= 1 && parentAdminLvlCode <= 5) {
					sb.append("=:parentAdminLvlCode");
				} else {

					sb.append("=(select b.parent_admin_unit_level_code+1 from administration_unit_level b where b.admin_unit_level_code=:parentAdminLvlCode)");
				}

			}

			sb.append(" union all ");
			sb.append("select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode "
					+ "from administration_unit_level a where a.isactive and a.ispublish='P' and a.slc=:slc");
			if (adminLvlCodeList != null) {
				sb.append(" and admin_unit_level_code not in(:adminLvlCodeList)");
			}
			sb.append(" and a.parent_admin_unit_level_code=:parentAdminLvlCode");

			SQLQuery query = session.createSQLQuery(sb.toString());
			// query.setParameter("landRegionSlc", landRegionSlc);
			query.setParameter("slc", slc);
			query.setParameter("parentAdminLvlCode", parentAdminLvlCode);

			if (adminLvlCodeList != null) {
				query.setParameterList("adminLvlCodeList", adminLvlCodeList);
			}

			query.addScalar("adminUnitCode");
			query.addScalar("adminLevelNameEng");
			query.addScalar("slc");
			query.addScalar("parentAdminCode");

			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getUnitLevelbyOrganization(Integer olc) throws Exception {
		Session session = null;
		List<DeptAdminUnit> deptAdminUnitList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = null;
			
				queryBuild = new StringBuilder("select DISTINCT admin_unit_level_code as adminUnitCode,unit_level_name_english as adminLevelNameEng,slc,parent_admin_unit_level_code as parentAdminCode ,"
				+ "oll.parent_org_located_level_code as parentOrgLocatedLevelCode from org_located_at_levels oll left join Administration_Unit_Level aul on  oll.located_at_level=aul.admin_unit_level_code "
				+ " where oll.olc=:olc and aul.admin_unit_level_code<=:landregionMaxCapacityand and aul.isactive and oll.isactive order by parentAdminCode");
			
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("olc", olc);
			query.setParameter("landregionMaxCapacityand", Integer.parseInt(DepartmentConstent.LANDREGION_MAX_CAPACITY.toString()));
			query.addScalar("adminUnitCode");
			query.addScalar("adminLevelNameEng");
			query.addScalar("parentAdminCode");
			
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
	}
	
	
	@Override
	public List<Organization> getCenterOrganizationbyCriteria(Integer orgTypeCode,Integer parentOrgCode) throws Exception {
		Session session = null;
		List<Organization> organizationList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select ");
			if(orgTypeCode==1){
				queryBuild.append(" org_code as olc ");
			}else{
				queryBuild.append(" olc ");
			}
			queryBuild.append(" ,org_name as orgName from organization  where  isactive and (slc=0 or COALESCE( slc,0) =0)  ");
			if(orgTypeCode!=Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_MINIST.toString()) && orgTypeCode!=Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString())){
				queryBuild.append(" and org_type_code!=:deptType and org_type_code!=:ministType ");
			}else{
				queryBuild.append(" and org_type_code=:orgTypeCode ");
			}
			if(orgTypeCode!=1){
				queryBuild.append(" and parent_org_code=:parentOrgCode ");
			}
			queryBuild.append(" order by orgName ");
			SQLQuery query = session.createSQLQuery(queryBuild.toString());
			
			if(orgTypeCode!=Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_MINIST.toString()) && orgTypeCode!=Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString())){
				query.setParameter("deptType", Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_DEPT.toString()));
				query.setParameter("ministType", Integer.parseInt(DepartmentConstent.ORGANIZTION_TYPE_CODE_MINIST.toString()));
			}else{
				query.setParameter("orgTypeCode", orgTypeCode);
			}
			if(orgTypeCode!=1){
				query.setParameter("parentOrgCode", parentOrgCode);
			}
			query.addScalar("olc");
			query.addScalar("orgName");
			query.setResultTransformer(Transformers.aliasToBean(Organization.class));
			organizationList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return organizationList;
	}
	
	/*
	Added By Pranav Tiwari
	On 21 Oct 2016
	To get Org at Level
	for the Department-LocalBody Mapping form*/
	@Override
	public List<LgdOrganizationAtLevels> getOrganizationAtLevelsForDeptLBMapping(int orgCode) {
		Session session = null;
		List<LgdOrganizationAtLevels> data = new ArrayList<LgdOrganizationAtLevels>();
		try {
			session = sessionFactory.openSession();
			StringBuilder strQuery = new StringBuilder("SELECT org_located_level_code as key,org_located_level_name as value,level_wise_office_name as levelWiseOfficeName  FROM get_organization_at_levels(?) order by org_located_level_code");
			SQLQuery query = (SQLQuery) session.createSQLQuery(strQuery.toString()).setParameter(0, orgCode);
			query.addScalar("key").addScalar("value").addScalar("levelWiseOfficeName");
			query.setResultTransformer(Transformers.aliasToBean(LgdOrganizationAtLevels.class));
			data = query.list();
			return data;

		} catch (Exception e) {
			System.out.println("Exception in getOrganizationAtLevels method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		
	}
	
	/*Added By Pranav Tiwari
	On 26 Oct 2016
	To get Org hierarchy particular level at located level 
	for the Department-LocalBody Mapping form*/

	@Override
	public List<LgdUpperHierarchyLevel> getUpperHierarchyLevel(int org_located_level_code) {
		Session session = null;
		List<LgdUpperHierarchyLevel> hierarchyList = new ArrayList<LgdUpperHierarchyLevel>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getUpperHierarchyLevel");
			query.setParameter("org_located_level_code", org_located_level_code,Hibernate.INTEGER);
			hierarchyList = query.list();
			if(hierarchyList!=null && !hierarchyList.isEmpty()){
				List<Integer> comboList = new ArrayList<Integer>();
				for (LgdUpperHierarchyLevel lgdUpperHierarchyLevel : hierarchyList) {
					comboList.add(lgdUpperHierarchyLevel.getLevelCode());
				}
				
				Query query1 = session.createSQLQuery("select org_unit_code,org_unit_name,org_located_level_code from org_units where org_located_level_code in (:comboList) ");
				query1.setParameterList("comboList", comboList);
				List<OrganizationUnit> organizationUnits = query1.list();
				hierarchyList.get(0).setList(organizationUnits);
			}
			
		} catch (Exception e) {
			System.out.println("Exception in getUpperHierarchyLevel method of AdministrativeDepratmentDAOImpl is :  " + e);
			throw e;
		}
		finally {
			if(session != null && session.isOpen()){
				session.flush();
				session.close();
			}
		}
		return hierarchyList;
	}
	
	@SuppressWarnings("unchecked")
	public List<LocalBodyTable> getLocalBodyEntities(Integer unitLevelCode, Integer stateCode){
		
		
		//List<Object[]>	getLocalBodyEntitie=null;
		 List<LocalBodyTable> getLocalBodyEntities=null;
		Session session = null;
		 Transaction transaction=null;
		
		try {
			session = sessionFactory.openSession();
			//StringBuilder queryBuild = null;
			//queryBuild = new StringBuilder("select local_body_code ,local_body_name_english from localbody l where local_body_type_code=:unitLevelCode and l.slc=:stateCode and isactive order by local_body_name_english");
			//SQLQuery query = session.createSQLQuery(queryBuild.toString());
			//query.setParameter("unitLevelCode", unitLevelCode);
			//query.setParameter("stateCode", stateCode);
			//query.setResultTransformer(Transformers.aliasToBean(LocalBodyTable.class));
		   // getLocalBodyEntitie = query.list();
		    
		    
			transaction=session.beginTransaction();
			transaction.begin();
		    
		    getLocalBodyEntities=session.createCriteria(LocalBodyTable.class).add(Restrictions.eq("localBodyTypeCode", unitLevelCode))
		    		.addOrder(Order.asc("localBodyNameEnglish")).add(Restrictions.eq("slc", stateCode)).add(Restrictions.eq("isactive", true)).list();
		    
		  
		    transaction.commit();
		    
		}catch(Exception e){
			transaction.rollback();
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLocalBodyEntities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptAdminUnit> getExistingDepartmentSetUp(boolean isCenterUser, Integer olc, Integer slc,String entityType) {
		Session session = null;
		List<DeptAdminUnit> deptAdminUnitList = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder();
			
		 queryBuild.append(" select a.*,hirar.hierarchy from ( select distinct oll.org_located_level_code as orgLocatedLevelCode, parent_org_located_level_code as parentOrgLocatedLevelCode, admin_unit_level_code as adminUnitCode,parent_admin_unit_level_code as seqLevel,unit_level_name_english as adminLevelNameEng,slc,"
		 		+ "(select located_at_level "
				 +" from org_located_at_levels where org_located_level_code=oll.parent_org_located_level_code and isactive) as parentAdminCode,'A' as localBodyLevel  from org_located_at_levels oll left join Administration_Unit_Level "
				  +" aul on  oll.located_at_level=aul.admin_unit_level_code where olc=:olc and oll.isactive and aul.isactive"
				 +"   union all"
				 +"   select DISTINCT oll.org_located_level_code as orgLocatedLevelCode, parent_org_located_level_code as parentOrgLocatedLevelCode, -local_body_type_code as adminUnitCode,0 as seqLevel,local_body_type_name as adminLevelNameEng,0 as"
				 +"   slc,(select located_at_level from org_located_at_levels where org_located_level_code=oll.parent_org_located_level_code and isactive) as parentAdminCode,level as localBodyLevel from org_located_at_levels oll left join"
				 +"   local_body_type aul on  -oll.located_at_level=aul.local_body_type_code where olc=:olc and aul.isactive and oll.isactive )a "
				 +"   inner join org_level_hierarchy_parent_to_child() hirar   on a.orgLocatedLevelCode=hirar.org_located_level_code");
		 
		
		 SQLQuery query = session.createSQLQuery(queryBuild.toString());
		 /*   set hierarchy on DeptAdminUnit.adminLevelNameLocal   */
			query.setParameter("olc", olc);
			query.addScalar("adminUnitCode");
			query.addScalar("adminLevelNameEng");
			query.addScalar("parentAdminCode");
			
			query.addScalar("hierarchy");
			query.addScalar("orgLocatedLevelCode");
			query.addScalar("parentOrgLocatedLevelCode");
			//query.setParameter("landregionMaxCapacityand", Integer.parseInt(DepartmentConstent.LANDREGION_MAX_CAPACITY.toString()));
			
			query.addScalar("seqLevel");
			query.addScalar("localBodyLevel");
			query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
			deptAdminUnitList = query.list();

		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return deptAdminUnitList;
	}
	//BY abhishek
		public List<Organization> getAllOrganizationDetailsBySC(Integer slc){
			Session session = null;
			List<Organization> organizationList = null;
			try {
				session = sessionFactory.openSession();
				StringBuilder queryBuild = new StringBuilder("select org_code as olc,org_name as orgName from organization where isactive=true ");
				if (slc >0) {
					queryBuild.append(" and slc=:slc");
				}else{
					queryBuild.append(" and coalesce(slc,0)=0 ");
				}
				queryBuild.append(" order by org_name");
				SQLQuery query = session.createSQLQuery(queryBuild.toString());
				if (slc > 0) {
					query.setParameter("slc", slc);
				}
				query.addScalar("olc");
				query.addScalar("orgName");
				query.setResultTransformer(Transformers.aliasToBean(Organization.class));
				organizationList = query.list();

			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return organizationList;
		}
	
		/*Change by Maneesh Kumar 07/04/2017
		 * (non-Javadoc)
		 * @see in.nic.pes.lgd.dao.AdministrativeDepratmentDAO#checkOrgUnit(java.lang.Integer)
		 */
		 public String[] checkOrgUnit(Integer orgVal)
		  {
			String[] msg=new String[2];
			Session session = null;
			try {
				session = sessionFactory.openSession();
				SQLQuery query = session.createSQLQuery("select case when count(1)>1 then true else false end as org_unit_flag  from org_units where org_located_level_code in "
				+ "(select org_located_level_code  from org_located_at_levels where olc=:orgVal) and (parent_org_unit_code=0 or parent_org_unit_code is null)");
				query.setParameter("orgVal", orgVal);
				boolean orgUnitFlag= ((boolean)query.uniqueResult());
				if(orgUnitFlag){
					msg[0]="Parent Org unit is not set for all org units.";
				}
			
			
				query = session.createSQLQuery("select case when count(1)=0 then true else false end as desig_flag from lgd_designation where designation_code in "
				+ "(select designation_code from designation_level_sortorder where org_located_level_code in (select org_located_level_code  from org_located_at_levels where olc=:orgVal))");
				query.setParameter("orgVal", orgVal);
				boolean desigFlag= ((boolean)query.uniqueResult());
				if(desigFlag){
					if (msg[0] != null && !msg[0].equals("")) {
                		msg[1] = "Designations are not entered.";
                    }else{
                    	msg[0] = "Designations are not entered.";
                    }
				}

			} catch (Exception e) {
				LGDLogger.getLogger(LanguageListener.class).error(e.toString());
				e.printStackTrace();
			}finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return msg;
		}
		
		/* Change by Maneesh Kumar 07/04/2017
		 *  (non-Javadoc)
		 * @see in.nic.pes.lgd.dao.AdministrativeDepratmentDAO#invokeScriptCall(java.lang.String, java.lang.String, java.lang.Integer)
		 */
		public boolean invokeScriptCall(String level,String queryType,Integer selectedOrgVal){
			boolean result=false;
			Session session = null;
			try {
				session = sessionFactory.openSession();
				StringBuilder sb=new StringBuilder("select * from generate_");
				sb.append(level);
				sb.append("_");
				sb.append(queryType);
				sb.append("_script(:selectedOrgVal)");
				SQLQuery query = session.createSQLQuery(sb.toString());
				query.setParameter("selectedOrgVal", selectedOrgVal);
				result= ((boolean)query.uniqueResult());
			} catch (Exception e) {
				LGDLogger.getLogger(LanguageListener.class).error(e.toString());
				e.printStackTrace();
			}finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return result;
		}

		
		
		/*  * @Author  Pranav Tiwari 
		    * To fetch Local body list
		    * on 13 Dec 2015
		    * Replaced On 15 Feb 17
		*/
		@SuppressWarnings("unchecked")
		public List<LocalBodyTable> getLocalbodyUnitLevelListbyCriteria(List<Integer> localbodyEntitylist,Integer localbodyTypeCode,Integer stateCode,Boolean flag){
			Session session = null;
			List<LocalBodyTable> listLocalbodyEnities = new ArrayList<LocalBodyTable>();
			try {
				session = sessionFactory.openSession();  
				StringBuilder queryBuild = new StringBuilder(" from LocalBodyTable where isactive is true and localBodyCode ");
				if(localbodyTypeCode != null){ 
				  localbodyTypeCode = -(localbodyTypeCode);
				}
				  if(flag == true){
					  queryBuild.append(" not in (:localbodyEntitylist) and localBodyTypeCode = :localbodyTypeCode");
				  }else{
					  queryBuild.append(" in (:localbodyEntitylist) and localBodyTypeCode = :localbodyTypeCode");
				  }
				  
				  queryBuild.append(" and slc=:stateCode ");
				  queryBuild.append(" order by localBodyNameEnglish");
				  Query query = session.createQuery(queryBuild.toString());
				  query.setParameterList("localbodyEntitylist", localbodyEntitylist);
				  if(localbodyTypeCode != null){
					  query.setParameter("localbodyTypeCode", localbodyTypeCode);
				  }
				  query.setParameter("stateCode", stateCode);
				  listLocalbodyEnities = query.list();
			} finally {
				if(session != null && session.isOpen()){
					session.close();
				}
			}
			return listLocalbodyEnities;
		}
		
		
		/*
		 * By Pranav on 17 Feb 2017
		 *To get the Office Level name
		 *In extend department Form along with Local body
		*/
		@SuppressWarnings("unchecked")
		public List<DeptAdminUnit> getOfficeNamebyLevelWithLocalbody(Integer olc, Integer level) {
			
			Session session = null;
			List<DeptAdminUnit> existOfficeList = null;
			try {
				session = sessionFactory.openSession();
				StringBuilder queryBuild = new StringBuilder("select case when (oll.located_at_level=:stateLevel or oll.located_at_level=:centerLevel) then (select org_name from organization  where isactive and olc=oll.olc) else org_level_specific_name end "
						+ "as  adminLevelNameEng,oll.located_at_level as adminUnitCode,oll.org_located_level_code as orgLocatedLevelCode from  org_located_at_levels oll where isactive and olc=:olc");
				if (level != null) {
					queryBuild.append(" and located_at_level=:level");
				}
				SQLQuery query = session.createSQLQuery(queryBuild.toString());
				if (level != null) {
					query.setParameter("level", level);
				}
				query.setParameter("olc", olc);
				query.setParameter("stateLevel", Integer.parseInt(DepartmentConstent.STATE_LEVEL.toString()));
				query.setParameter("centerLevel", Integer.parseInt(DepartmentConstent.CENTER_LEVEL.toString()));
				query.addScalar("adminLevelNameEng");
				query.addScalar("adminUnitCode");
				query.addScalar("orgLocatedLevelCode");
				query.setResultTransformer(Transformers.aliasToBean(DeptAdminUnit.class));
				existOfficeList = query.list();

			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return existOfficeList;

		}
		
		@Override
		public List<ChildExtendDept> getChildofParentAdminCode(Integer slc,Integer parentAdminLevelCode,List<Integer> existAdminCodeList)throws Exception{
			Session session = null;
			List<ChildExtendDept> childExtendDeptList = null;
			try {
				if(!existAdminCodeList.contains(-100)) {
					session = sessionFactory.openSession();
					
					Query query = session.getNamedQuery("FETCH_CHILD_OF_PARENT_ADMIN_UNIT_LEVELS");
					query.setParameterList("existAdminCodeList", existAdminCodeList);
					query.setParameter("slc", slc);
					query.setParameter("parentAdminLevelCode", parentAdminLevelCode);
					childExtendDeptList = query.list();
				}
				
			}catch(Exception e){
				log.error("Extend Dept function 'get_lb_extend_dept_hierarchy_new' error->>",e);
			}finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return childExtendDeptList;
		}
		
		@Override
		public List<ExistDeptHierarchy> getExistDeptHierarchy(Integer olc,Integer slc,Boolean isHierarchy)throws Exception{
			Session session = null;
			List<ExistDeptHierarchy> existDeptHierarchyList = null;
			try {
				
				session = sessionFactory.openSession();
				String nameQuery="GET_EXIST_HIERARCHY_DEPT";
				if(isHierarchy==null){
					nameQuery="GET_EXIST_ORG_LEVEL";
				}else if(!isHierarchy){
					nameQuery="GET_EXIST_HIERARCHY_FORM";
				}
				Query query = session.getNamedQuery(nameQuery);
				query.setParameter("olc", olc);
				if(isHierarchy==null || (isHierarchy!=null && isHierarchy)){
					query.setParameter("slc", slc);
				}
				
				existDeptHierarchyList = query.list();
				
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return existDeptHierarchyList;
		}
		
		@Override
		public String[]  getLevelWiseTreeDetails(Integer stateCode,String parentHierarchy)throws Exception{
			Session session = null;
			String[] LevlDetArr =new String[5];
			try {
				session = sessionFactory.openSession();
				Query query = session.getNamedQuery("GET_LEVELWISE_TREE_DETAILS");
				query.setParameter("stateCode", stateCode);
				query.setParameter("parentHierarchy", parentHierarchy);
				List<VillageGISMapStatus> villageGISMapStatus = query.list();
				
				if(villageGISMapStatus!=null && !villageGISMapStatus.isEmpty()){
					int index=0;
					String 	treeDetails=villageGISMapStatus.get(0).getMessage();
					Scanner scanner = new Scanner(treeDetails);
					scanner.useDelimiter("@");
					while (scanner.hasNext()) {
						LevlDetArr[index]=scanner.next();
						index++;
					}
				}
				
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			
			return LevlDetArr;
		}
		
		@Override
		public List<LocalBodyEntityDetails> getDistrictPanchayatList( Integer lbTypeCode,Integer stateCode,List<Integer> existlbList,boolean existFlag) throws HibernateException {
			// TODO Auto-generated method stub
			log.info("AdministrativeDepratmentDAOImpl.getDistrictPanchayatList execution started.");
			Session session = null;
			List<LocalBodyEntityDetails> localBodyDetails = new ArrayList<LocalBodyEntityDetails>();
			try {
				session = sessionFactory.openSession();
				String nameQuery="DISTRICT_PANCHYAT_LIST_FOR_DEPT";
				if(existFlag){
					 nameQuery="EXIST_DISTRICT_PANCHYAT_LIST_FOR_DEPT";
				}
				Query query = session.getNamedQuery(nameQuery);
				query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
				query.setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER);
				query.setParameterList("existlbList", existlbList);
				
				localBodyDetails = query.list();
			} catch (HibernateException e) {
				// TODO: handle exception
				log.error("Error in AdministrativeDepratmentDAOImpl.getDistrictPanchayatList : ", e);
				throw e;
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return localBodyDetails;
		}
		
		@Override
		public List<LocalBodyEntityDetails> getParentwiseLocalBody(Integer localBodyCode,List<Integer> existlbList,boolean existFlag) throws HibernateException {
			// TODO Auto-generated method stub
			log.info("AdministrativeDepratmentDAOImpl.getParentwiseLocalBody execution started.");
			Session session = null;
			List<LocalBodyEntityDetails> parentwiseLocalBodyDetails = new ArrayList<LocalBodyEntityDetails>();
			try {
				session = sessionFactory.openSession();
				String nameQuery="LBLIST_By_Parent_FOR_DEPT";
				if(existFlag){
					 nameQuery="EXIST_LBLIST_By_Parent_FOR_DEPT";
				}
				Query query = session.getNamedQuery(nameQuery);
				query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
				query.setParameterList("existlbList", existlbList);
				parentwiseLocalBodyDetails = query.list();
			} catch (HibernateException e) {
				// TODO: handle exception
				log.error("Error in AdministrativeDepratmentDAOImpl.getParentwiseLocalBody : ", e);
				throw e;
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return parentwiseLocalBodyDetails;
		}
		
		@Override
		public List<OrgLocatedAtLevels> getOfficeDetailbyParent(Integer olc,Integer parentOrgLocaltedCode,Integer locatedAtLevel)throws Exception{
			Session session = null;
			List<OrgLocatedAtLevels> orgLocatedAtLevelsList =null;
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(OrgLocatedAtLevels.class);
				criteria.add( Restrictions.eq("organization.olc", olc));
				criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
				if(parentOrgLocaltedCode!=null){
				criteria.add( Restrictions.eq("parentOrgLocatedLevelCode", parentOrgLocaltedCode)); 
				}
				criteria.add( Restrictions.eq("locatedAtLevel", locatedAtLevel)); 
				orgLocatedAtLevelsList = criteria.list();
			} catch (HibernateException e) {
				log.error("Error in AdministrativeDepratmentDAOImpl.getOfficeDetailbyParent : ", e);
				throw e;
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return orgLocatedAtLevelsList;
		}
		
		
		@Override
		public List<LocalBodyEntityDetails> getlbListbylbCode(List<Integer> existlbList) throws HibernateException {
			// TODO Auto-generated method stub
			log.info("AdministrativeDepratmentDAOImpl.getlbListbylbCode execution started.");
			Session session = null;
			List<LocalBodyEntityDetails> localBodyDetails = new ArrayList<LocalBodyEntityDetails>();
			try {
				session = sessionFactory.openSession();
			
				SQLQuery query = session.createSQLQuery("select child.local_body_code as localBodyCode, case when coalesce (child.parent_lblc,0)=0 then child.local_body_name_english else "
				+ "child.local_body_name_english||'('||parent.local_body_name_english||')' end  as localBodyNameEnglish from localbody child left join localbody parent on "
				+ "child.parent_lblc=parent.local_body_code and parent.isactive   where child.local_body_code in(:existlbList)");
				query.setParameterList("existlbList", existlbList);
				query.addScalar("localBodyCode");
				query.addScalar("localBodyNameEnglish");
				query.setResultTransformer(Transformers.aliasToBean(LocalBodyEntityDetails.class));
				localBodyDetails = query.list();
			} catch (HibernateException e) {
				// TODO: handle exception
				log.error("Error in AdministrativeDepratmentDAOImpl.getlbListbylbCode : ", e);
				throw e;
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return localBodyDetails;
		}
		
		@Override
		public List<LocalBodyEntityDetails> getlbListbyCreteria(List<Integer> existDPlbList,List<Integer> existIPlbList,List<Integer> existVPlbList) throws HibernateException {
			// TODO Auto-generated method stub
			log.info("AdministrativeDepratmentDAOImpl.getlbListbylbCode execution started.");
			Session session = null;
			List<LocalBodyEntityDetails> localBodyDetails = new ArrayList<LocalBodyEntityDetails>();
			try {
				session = sessionFactory.openSession();
				SQLQuery query=null;
				if(existVPlbList==null){
					query = session.createSQLQuery("select child.local_body_code as localBodyCode, case when coalesce (child.parent_lblc,0)=0 then child.local_body_name_english else "
							+ "child.local_body_name_english||'('||parent.local_body_name_english||')' end  as localBodyNameEnglish from localbody child left join localbody parent "
							+ "on child.parent_lblc=parent.local_body_code and parent.isactive where child.local_body_code in(:existIPlbList) and child.parent_lblc not in(:existDPlbList) and child.isactive");
					query.setParameterList("existDPlbList", existDPlbList);
					query.setParameterList("existIPlbList", existIPlbList);
				}else{
					
					query = session.createSQLQuery("select child.local_body_code as localBodyCode, case when coalesce (child.parent_lblc,0)=0 then child.local_body_name_english else child.local_body_name_english||'('||"
							+ "parent.local_body_name_english||')' end  as localBodyNameEnglish from localbody child left join localbody parent on child.parent_lblc=parent.local_body_code and "
							+ "parent.isactive where child.local_body_code in(:existVPlbList) and child.parent_lblc not in(select local_body_code from localbody  where parent_lblc in(:existDPlbList) "
							+ "and isactive) and child.parent_lblc not in(:existIPlbList)");
					query.setParameterList("existDPlbList", existDPlbList);
					query.setParameterList("existIPlbList", existIPlbList);
					query.setParameterList("existVPlbList", existVPlbList);
					
				}
				
				query.addScalar("localBodyCode");
				query.addScalar("localBodyNameEnglish");
				query.setResultTransformer(Transformers.aliasToBean(LocalBodyEntityDetails.class));
				localBodyDetails = query.list();
			} catch (HibernateException e) {
				// TODO: handle exception
				log.error("Error in AdministrativeDepratmentDAOImpl.getlbListbylbCode : ", e);
				throw e;
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return localBodyDetails;
		}
		
		@Override
		public List<OrgLocatedAtLevels> getOrgLocatedbyOrgCodeNew(int orgCode) throws Exception {
			Session session = null;
			SQLQuery query = null;
			List<OrgLocatedAtLevels> orgLocatedList = new ArrayList<OrgLocatedAtLevels>();
			try {
				/**
				 * 0005205: Bug in Set parent org unit changed by kirandep on
				 * 19/05/2015
				 */
				System.out.println("into function");
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
				System.out.println("orgCode"+orgCode);
				System.out.println("list size"+orgLocatedList.size());
			} catch (Exception e) {
				System.out.println("orgCode"+orgCode);
				e.printStackTrace();
				log.error("Exception-->" + e);
				return null;
			} finally {
				if (session != null && session.isOpen())
					session.close();
			}
			return orgLocatedList;

		}
		
		@Override
		public List<FetchOrgLocatedLevel> fetchOfficeDetailbyParent(Integer olc,Integer parentOrgLocaltedCode,Integer locatedAtLevel)throws Exception{
			Session session = null;
			List<FetchOrgLocatedLevel> orgLocatedAtLevelsList =null;
			try {
				session = sessionFactory.openSession();
				session = sessionFactory.openSession();
				Query query = session.getNamedQuery("FETCH_ORG_LOCATED_LEVEL");
				
				
				query.setParameter("olc", olc);
				query.setParameter("isactive", Boolean.TRUE);
				query.setParameter("parentOrgLocatedLevelCode",parentOrgLocaltedCode );
				
				query.setParameter("locatedAtLevel",locatedAtLevel );
			
				orgLocatedAtLevelsList = query.list();
			} catch (HibernateException e) {
				log.error("Error in AdministrativeDepratmentDAOImpl.getOfficeDetailbyParent : ", e);
				throw e;
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return orgLocatedAtLevelsList;
		}
		
		/*@Override
		public List<Object[]> getDeptOrgList(Integer adminLevel ,Integer parentEntity ) throws HibernateException {
			// TODO Auto-generated method stub
			log.info("LocalBodyDaoImpl.getLBListOfSelectedLR execution started.");
			Session session = null;
			List<Object[]> getDeptOrgList=new ArrayList<Object[]>();
			try {
				session = sessionFactory.openSession();
				Query query = session.createSQLQuery("select o.org_code , o.org_name ,o.org_name_local from organization o inner join org_located_at_levels oll on oll.olc = o.olc  inner join org_units ou on  ou.org_located_level_code = oll.org_located_level_code where  entity_lc=:parentEntity and entity_type=:adminLevel");
				query.setParameter("adminLevel", adminLevel, Hibernate.INTEGER);
				query.setParameter("parentEntity", parentEntity, Hibernate.INTEGER);

				getDeptOrgList = query.list();
			} catch (Exception e) {
				// TODO Auto-generated method stub
				log.error("Exception" , e);
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return getDeptOrgList;
		}*/
	
		
		@Override
		public  List<DepartmentOrgListDto> getDeptOrgList(Integer adminLevel ,Integer parentEntity ) throws HibernateException{
			 Session session = null;	
	        
			
			 List<DepartmentOrgListDto> departmentOrgListDto = null;
			 String sqlQuery=null;
			try {
				sqlQuery="select o.org_code,o.org_name,ou.org_unit_code , ou.org_unit_name  from organization o inner join org_located_at_levels oll on oll.olc = o.olc  inner join org_units ou on  ou.org_located_level_code = oll.org_located_level_code where  entity_lc=:parentEntity and entity_type=:adminLevel and ou.isactive  and oll.isactive and o.isactive";
				session = sessionFactory.openSession();
				Query query =	session.createSQLQuery(sqlQuery);
				query.setParameter("adminLevel", adminLevel, Hibernate.INTEGER);
				query.setParameter("parentEntity", parentEntity, Hibernate.INTEGER);
				
				List<Object[]> departmentOrgListObj =query.list();
				if(departmentOrgListObj != null && departmentOrgListObj.size() > 0)
				{
					
					departmentOrgListDto = new ArrayList<DepartmentOrgListDto>();
					for(Object[] obj:departmentOrgListObj) {
						DepartmentOrgListDto beanOBJ=new DepartmentOrgListDto();
						if(obj[0] != null){
							beanOBJ.setOrgCode((Integer)obj[0]);
						}
						if(obj[1] != null && !((String)obj[1]).toString().isEmpty()){
							beanOBJ.setOrgName((String)obj[1]);
						}else{
							beanOBJ.setOrgName("");
						}
						if(obj[2] != null){
							beanOBJ.setOrgUnitCode((Integer)obj[2]);
						}
						if(obj[3] != null && !((String)obj[3]).toString().isEmpty()){
							beanOBJ.setOrgUnitName((String)obj[3]);
						}else{
							beanOBJ.setOrgUnitName("");
						}
						
				
						departmentOrgListDto.add(beanOBJ);
					}
				
					
			}
				
			}
			catch (Exception e) {
				// TODO: handle exception
				log.error("Error in AdministrativeDepratmentDAOImpl.getDeptOrgList : ", e);
				throw e;
			}
			
			finally{
				if(session != null && session.isOpen())
					session.close();
			}
			return departmentOrgListDto;
		}

	

		@Override
		public List<EntityTransactionsNew> getTranctionDtlsChangeEntity(Integer stateCode) throws HibernateException {
			// TODO Auto-generated method stub
			
			Session session = null;
			List<EntityTransactionsNew> entityTransactionsNews = null;
			try {
				session = sessionFactory.openSession();
				Query query = session.getNamedQuery("fetch_entity_transactions_for_admin_unit_entity");
				query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
				entityTransactionsNews = query.list();
			} catch (HibernateException e) {
				// TODO: handle exception
				log.error("Error in AdministrativeDepratmentDAOImpl.getTranctionDtlsChangeEntity : ", e);
				throw e;
			} 
			return entityTransactionsNews;
			
			
			
		}

		@Override
		public List<Object> getScriptThroughTxId(Integer transactionId) throws Exception {
				Session session = null;
				
				List<Object> scriptList =null;
				 String sqlQuery=null;
				try {
					sqlQuery="select script from entity_transactions_scripts where tid =:transactionId";
					session = sessionFactory.openSession();
					Query query =	session.createSQLQuery(sqlQuery);
					query.setParameter("transactionId", transactionId, Hibernate.INTEGER);
					
					scriptList = query.list();
				}
				catch(HibernateException e) {
					log.error("Error in AdministrativeDepratmentDAOImpl.getScriptThroughTxId : ", e);
					throw e;
				}
			
			return scriptList;
		}
		
		
		@Override
		public List<Country> getCountryList() throws Exception{
			Session session=null;
			List<Country> countryList=null;
			try{
				session=sessionFactory.openSession();
				Query query=session.createQuery("from Country order by countryName");
				countryList=query.list();
				
			}catch(Exception e){
				log.error(e);
				throw e;
				
			}finally{
				if
				(session!=null && session.isOpen()){
					session.close();
				}
			}
			return countryList;
		}
		
		
		@Override
		public List<ExistingDeptMapping> getExistingDeptMapping(Integer orgUnitCode,Integer orgLocatedCode,Integer slc, String mappingType) throws Exception {
			 Session session=null;
		     List<ExistingDeptMapping> existingDeptMappingList=null;
			try {
				session=sessionFactory.openSession();
				Query query=session.getNamedQuery("ExistingDepartmentMapping");
				query.setParameter("slc", slc);
				query.setParameter("mappingType", mappingType);
				query.setParameter("orgLocatedCode", orgLocatedCode);
				query.setParameter("orgUnitCode",orgUnitCode);
				existingDeptMappingList=query.list();
			 }catch(Exception e){
				log.error(e);
				throw e;
				
			}finally{
				if
				(session!=null && session.isOpen()){
					session.close();
				}
			}
			return existingDeptMappingList;
		}
		
		
		@SuppressWarnings("unlikely-arg-type")
		@Override
		public boolean deleteExistingDeptMapping(Integer mappingFromId, Integer mappingId,Integer userid,Integer stateID) {
			Session session=null;
			boolean delFlag=false;
			Transaction txn = null;
			Query query = null;
			try {
			    session = sessionFactory.openSession();
				txn = session.beginTransaction();
				query=session.createSQLQuery("Select * from remove_existingMapping_fn(:mappingFromId,:mappingId,:userid,:stateID)");
                query.setParameter("mappingFromId", mappingFromId,Hibernate.INTEGER);
				query.setParameter("mappingId", mappingId,Hibernate.INTEGER);
				query.setParameter("userid", userid,Hibernate.INTEGER);
				query.setParameter("stateID", stateID,Hibernate.INTEGER);
				query.uniqueResult(); 
				txn.commit();
				delFlag=true;  
				
			}catch(Exception e){
				log.debug("Exception" + e);
				if (txn != null) {
					txn.rollback();
				}
				return false;
				
			}finally{
				if
				(session!=null && session.isOpen()){
					session.close();
				}
			}
			return delFlag;
		}
		
		
		
}
