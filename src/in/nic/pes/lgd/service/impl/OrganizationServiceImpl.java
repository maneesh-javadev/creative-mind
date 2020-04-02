package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.AdministrativeEntityCoverage;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.DepartmentByCentreLevel;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.DeptAdminUnitEntity;
import in.nic.pes.lgd.bean.DesignationMaster;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictLineDepartment;
import in.nic.pes.lgd.bean.ExtendDepartment;
import in.nic.pes.lgd.bean.GetOrganizationAtLevels;
import in.nic.pes.lgd.bean.GetOrganizationListFn;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationByCentreLevel;
import in.nic.pes.lgd.bean.OrganizationCoverage;
import in.nic.pes.lgd.bean.OrganizationCoverageDetail;
import in.nic.pes.lgd.bean.OrganizationDesignation;
import in.nic.pes.lgd.bean.OrganizationPK;
import in.nic.pes.lgd.bean.OrganizationType;
import in.nic.pes.lgd.bean.OrganizationUnit;
import in.nic.pes.lgd.bean.ReportingSetup;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateLineDepartment;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.common.LgdSession;
import in.nic.pes.lgd.common.ReleaseResources;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.OrganizationDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.DepartmentAdminForm;
import in.nic.pes.lgd.forms.DepartmentDataForm;
import in.nic.pes.lgd.forms.DepartmentForm;
import in.nic.pes.lgd.forms.DesignationForm;
import in.nic.pes.lgd.forms.DesignationReportingForm;
import in.nic.pes.lgd.forms.MinistryForm;
import in.nic.pes.lgd.forms.OrgLocatedAtLevelsForm;
import in.nic.pes.lgd.forms.OrganizationTypeForm;
import in.nic.pes.lgd.forms.OrganizationUnitForm;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pes.attachment.util.AttachedFilesForm;

@Repository
public class OrganizationServiceImpl implements OrganizationService {

	private static final Logger LOG = Logger.getLogger(OrganizationServiceImpl.class);
	@Autowired
	private OrganizationDAO organizationDAO;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	private StateDAO stateDao;

	private int stateCode = 27;
	private int stateVersion = 1;
	private int userId = 1000;
	private long createdBy = 1000;

	public List<Organization> getMinistryList() throws Exception {
		return organizationDAO.getMinistryList();
	}

	public List<DepartmentByCentreLevel> getDepartmentListByCentreLevel(char entityCode) throws Exception {
		return organizationDAO.getDepartmentListByCentreLevel(entityCode);
	}

	public List<OrganizationByCentreLevel> getOrganizationListByCentreLevel(char entityCode) throws Exception {
		return organizationDAO.getOrganizationListByCentreLevel(entityCode);
	}

	public List<OrganizationType> getOrgTypeList() throws Exception {
		return organizationDAO.getOrgTypeList();
	}

	public List<Organization> getMinistryListbyState(int stateCode) throws Exception {
		return organizationDAO.getMinistryListbyState(stateCode);
	}

	public List<Organization> getDepartmentList() throws Exception {
		return organizationDAO.getDepartmentList();
	}

	public List<Organization> getDepartmentListByMinistry(int orgCode) throws Exception {
		return organizationDAO.getDepartmentListByMinistry(orgCode);
	}

	public int saveMinistryDetails(MinistryForm ministryForm) throws Exception {
		int orgVersion = 1;
		int orgCode = organizationDAO.getMaxCode();
		if (orgCode > 0) {
			orgCode = orgCode + 1;
			Organization org = new Organization();
			OrganizationPK orgPk = new OrganizationPK(orgCode, orgVersion);
			OrganizationType organizationType = new OrganizationType(1); // 1 is
																			// Organisation
																			// Type
																			// Code
																			// for
																			// Ministry
			org.setOrganizationPK(orgPk);
			org.setOrganizationType(organizationType);
			org.setOrgLevel(0);  //zero is orgLevel for  Ministry  Maneesh Kumar Since 09-June-2015
			org.setIsactive(true);
			// org.setOrgTypeCode(1);
			org.setOrgName(ministryForm.getMinistryName());
			org.setShortName(ministryForm.getShortministryName());
			org.setOrgNameLocal("");
			organizationDAO.saveMinistryDetails(org);
		}
		return orgCode;
	}

	public void saveOrganisationType(OrganizationTypeForm orgTypeForm) throws Exception {
		OrganizationType orgType = new OrganizationType();
		orgType.setOrgType(orgTypeForm.getOrgTypeName());
		organizationDAO.saveOrganisationType(orgType);
	}

	@Override
	public List<DesignationMaster> getDesignationName(String designationName) throws Exception {
		return organizationDAO.getDesignationName(designationName);
	}

	@Override
	public List<Organization> getOrganization() throws Exception {
		return organizationDAO.getOrganization();
	}

	@Override
	public boolean saveOrganizationDesignation(DesignationForm designationForm) throws Exception {
		Session session = null;
		Transaction tx = null;
		Organization organization = null;
		int orgCode = 0;
		int orgVersion = 0;
		@SuppressWarnings("unused")
		char locatedAtLevel = 0;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String tempIsMultiple[] = designationForm.getIsMultiple().split(",");
		String tempIsLevelSpecific[] = designationForm.getIsLevelSpecific().split(",");
		String tempLevelSpecificCode[] = designationForm.getLevelSpecificCode().split(",");
		String tempDesgName[] = designationForm.getDesgName().split(",");
		orgCode = designationForm.getOrgCode();
		orgVersion = Integer.parseInt(session.createQuery("select orgVersion from Organization where isactive=true and orgCode=" + orgCode).list().get(0).toString());

		@SuppressWarnings("rawtypes")
		List isExist = session.createQuery("from OrganizationDesignation d where d.organization.organizationPK.orgCode=" + orgCode).list();
		if (isExist.size() > 0) {
			session.createSQLQuery("update oraganization_designation set isactive=false where org_code=" + orgCode).executeUpdate();
		}
		try {
			for (int i = 0; i < tempDesgName.length; i++) {
				OrganizationDesignation orgDesigBean = new OrganizationDesignation();
				organization = new Organization();
				OrganizationPK orgPK = new OrganizationPK(orgCode, orgVersion);
				organization.setOrganizationPK(orgPK);
				orgDesigBean.setDesignationName(tempDesgName[i]);
				orgDesigBean.setIsactive(true);
				// orgDesigBean.setOrganization(organization);
				orgDesigBean.setIsmultiple(Boolean.parseBoolean(tempIsMultiple[i]));
				if (i == 0) {
					orgDesigBean.setIsmultiple(false);
					// orgDesigBean.setTopDesignation(true);
				} else {
					// orgDesigBean.setTopDesignation(false);
					LOG.info("in else not req. yet");
				}
				if (tempIsLevelSpecific[i].equals("true")) {
					locatedAtLevel = session.createQuery("select locatedAtLevel from OrgLocatedAtLevels " + "where orgLocatedLevelCode=" + tempLevelSpecificCode[i]).list().get(0).toString().charAt(0);
					// orgDesigBean.setLocatedAtLevel(locatedAtLevel);
				} else {
					// orgDesigBean.setLocatedAtLevel('A');
					LOG.info("in else not req. yet");
				}
				organizationDAO.saveOrganizationDesignation(orgDesigBean, session);
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			LOG.error("Exception-->"+e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean saveOrganizationReporting(DesignationReportingForm designationReportingForm) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			ReportingSetup reportingOrg = new ReportingSetup();
			
			String[] tempDesig = designationReportingForm.getDesignation().split(",");
			String[] tempReport = designationReportingForm.getReportTo().split(",");
			Query  query = null;
			for (int i = 0; i < tempDesig.length; i++) {
				query = session.createSQLQuery("delete from reporting_setup where designation_code = :desigCode");
				query.setParameter("desigCode", Integer.parseInt(tempDesig[i]));
				query.executeUpdate();
				if (tempReport[i].length() > 0) {
					reportingOrg.setReportTo(Integer.parseInt(tempReport[i]));
				}
				reportingOrg.setDesignationCode(Integer.parseInt(tempDesig[i]));
				organizationDAO.saveReportingOrg(reportingOrg, session);
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			LOG.error("Exception-->"+e);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public List<Organization> getOrgbyOrgType(int orgTypeCode) throws Exception {
		return organizationDAO.getOrgbyOrgType(orgTypeCode);
	}

	@Override
	public List<OrganizationDesignation> getDesignationByOrgCode(int orgCode, int locatedAtLevelCode) throws Exception {
		return organizationDAO.getDesignationByOrgCode(orgCode, locatedAtLevelCode);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationReporting(String orgType) {
		Session session=null;
		List list=null;
		if (orgType.equals("Line Departments")) {
			list= session.createQuery("from Organization where orgTypeCode=2").list();
		} else if (orgType.equals("Organization")) {
			list= session.createQuery("from Organization where orgTypeCode<>2 and orgTypeCode<>1").list();
		} else {
			list= session.createQuery("from Organization").list();
		}
		if (session != null && session.isOpen()) {
			session.close();
		}
		return list;
		
	}

	@Override
	public List<OrganizationDesignation> getDesignationByOrgCodeNonTop(int orgCode, int locatedAtLevelCode) throws Exception {
		return organizationDAO.getDesignationByOrgCodeNonTop(orgCode, locatedAtLevelCode);
	}

	public List<Organization> getMinistryDetailList(String query) throws Exception {
		return organizationDAO.getMinistryDetailList(query);
	}

	@Override
	public List<Organization> getMinistryDetail(int orgCode) throws Exception {
		return organizationDAO.getMinistryDetail(orgCode);
	}

	@Override
	public List<MinistryForm> getMinistryDetails(int orgCode) throws Exception {
		return organizationDAO.getMinistryDetails(orgCode);
	}

	@Override
	public List<Organization> getOrganizationDetails(int orgCode) throws Exception {
		return organizationDAO.getOrganizationDetails(orgCode);
	}

	@Override
	public List<OrgLocatedAtLevels> getOrganizationDetailsLowLevel(int orgCode) throws Exception {
		return organizationDAO.getOrganizationDetailsLowLevel(orgCode);
	}

	@Override
	public boolean ministryUpdate(MinistryForm ministryForm, int orgCode, HttpSession session, HttpServletRequest request) throws Exception {
		stateVersion = stateDao.getCurrentVersionbyStateCode(stateCode);
		Organization organizationbean = null;
		/* Organization organizationbeanisActive = null; */
		organizationbean = new Organization();
		int orgVersion = 1;
		orgVersion = organizationDAO.getMaxOrganizationVersion(ministryForm.getOrgCode());
		int tmporgVersion = orgVersion;
		Session session1 = null;
		Transaction tx1 = null;
		session1 = new LgdSession().getSession(sessionFactory);
		tx1 = session1.beginTransaction();
		try {
			if (ministryForm.isCorrection() == true) {
				OrganizationPK orgpk = new OrganizationPK(ministryForm.getOrgCode(), orgVersion);
				organizationDAO.update(ministryForm, orgpk, session1);
			} else if (ministryForm.isCorrection() == false) {
				OrganizationPK orgpk2 = new OrganizationPK(ministryForm.getOrgCode(), orgVersion);
			
				if (orgVersion == 0) {
					orgVersion = orgVersion + 1;
				} else {
					orgVersion = orgVersion + 1;
				}
				OrganizationPK orgpk = new OrganizationPK(ministryForm.getOrgCode(), orgVersion);
				OrganizationType organizationType1 = new OrganizationType(1);
				organizationbean.setOrganizationType(organizationType1);
				organizationbean.setOrgNameLocal("");
				organizationbean.setOrganizationPK(orgpk);
				organizationbean.setOrgName(ministryForm.getMinistryNamecr());
				organizationbean.setShortName(ministryForm.getShortministryName());
				// organizationbean.setIslocalbodyspecific(ministryForm.isLocalBodySpecific());
				// organizationbean.setOrgTypeCode(ministryForm.getOrgTypeCode());
				// organizationbean.setLocalBodyType(localBodyType);
				organizationbean.setOrgLevel('C');// need to be correct by vanisha
				organizationbean.setIsactive(true);
				organizationDAO.saveWithSession(organizationbean, orgpk2, tmporgVersion, session1);
			}
			tx1.commit();
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			tx1.rollback();
		} finally {
			/*
			 * session1.clear(); session1.close();
			 */
			ReleaseResources.doReleaseResources(session1);
		}

		return true;
	}

	@Override
	public int getMaxOrganizationVersion(int orgCode) throws Exception {
		return organizationDAO.getMaxOrganizationVersion(orgCode);
	}

	@Override
	public boolean saveDepartment(DepartmentForm departmentFormCentralL, List<DepartmentForm> listDepartmentFormStateL, List<DepartmentForm> listDepartmentFormDistrictL, DepartmentForm departmentFormSubDistrictL, DepartmentForm departmentFormBlockL,
			DepartmentForm departmentFormVillageL, int stateCode) throws Exception {
		Session session = null;
		Transaction tx = null;
		Organization orgBean = null;
		OrganizationType organizationType = null;
		OrganizationPK organizationPK = null;
		LocalBodyType localBodyType = null;
		State state = null;
		StatePK statepk = null;
		String[] tempEntityCode = null;
		int maxCoverageCode = 0;
		char coverageLevel = 0;
		int orgLocatedCode = 0;
		int orgLocatedCodeCent = 0;
		int parentOrgLocatedCode = 0;
		int maxOrgCode1 = 0;
		int itr = 0;
		Map<Integer, String> parentMp = new HashMap<Integer, String>();
		//List<PushOrgDepToPes> lbodytoPes=null;

		try {
			maxOrgCode1 = organizationDAO.getMaxOrgCode();
			maxCoverageCode = organizationDAO.getMaxCoverageCode();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//Integer slc;
			state = new State(stateCode);
			statepk = new StatePK(stateCode, stateVersion);
			state.setStatePK(statepk);
			state.setSlc(stateCode);
			organizationType = new OrganizationType();
			orgBean = new Organization(maxOrgCode1);
			// maxOrgCode1=departmentFormCentralL.getOrgCode();
			organizationPK = new OrganizationPK(maxOrgCode1, 1);
			if (departmentFormCentralL.isOrganization()) {
				orgBean.setParentorgcode(Integer.parseInt(departmentFormCentralL.getDeptOrgCode()));
				organizationType.setOrgTypeCode(Integer.parseInt(departmentFormCentralL.getOrgType()));
			} else {
				organizationType.setOrgTypeCode(2);
				if (departmentFormCentralL.getMinistryName() != null){
					orgBean.setParentorgcode(Integer.parseInt(departmentFormCentralL.getMinistryName()));
				}	
			}
			if (departmentFormCentralL.getIsLocalBodyTypeSpecifc() == 'Y') {
				localBodyType = new LocalBodyType();
				localBodyType.setLocalBodyTypeCode(departmentFormCentralL.getLocalBodyTypeCode());
				orgBean.setLocalBodyType(localBodyType);
				if (departmentFormCentralL.getIsLocalBodySpecifc() == 'Y') {
					orgBean.setLocalBodyCode(departmentFormCentralL.getLocalBodyCode());
				}
			}
			orgBean.setOrganizationPK(organizationPK);
			orgBean.setOrgName(departmentFormCentralL.getDeptName());
			orgBean.setOrgNameLocal(departmentFormCentralL.getDeptNameLocal());
			orgBean.setShortName(departmentFormCentralL.getShortDeptName());
			// orgBean.setSlc(stateCode);
			orgBean.setIsactive(true);

			if (departmentFormCentralL.isAUM()) {
				orgBean.setOrgLevel(CommonUtil.setCategoryLevel('S'));
				orgBean.setSlc(stateCode);
			} else{
				orgBean.setOrgLevel(CommonUtil.setCategoryLevel('C'));
			}	
			orgBean.setOrganizationType(organizationType);
			orgBean.setIsactive(true);
			orgBean.setOlc(maxOrgCode1);
			orgBean.setOrgCode(maxOrgCode1);
			// orgBean.setSlc(0);
			organizationDAO.saveOrganization(orgBean, session);

			// Organisation Located At Code
			OrgLocatedAtLevels orgLocated = null;
			orgLocated = new OrgLocatedAtLevels();
			orgBean.setOrganizationPK(organizationPK);
			// orgBean.setSlc(stateCode);
			if (departmentFormCentralL.isAUM())
				orgLocated.setLocatedAtLevel(CommonUtil.setCategoryLevel('S'));
			else
				orgLocated.setLocatedAtLevel(CommonUtil.setCategoryLevel('C'));
			if (departmentFormCentralL.isOrganization()) {
				if (departmentFormCentralL.isAUM()) {
					LOG.info("in if not req. yet");
				} else
					orgLocated.setParentOrgLocatedLevelCode(Integer.parseInt(departmentFormCentralL.getDeptOrgCode()));
			} else {
				if (departmentFormCentralL.getMinistryName() != null){
					orgLocated.setParentOrgLocatedLevelCode(Integer.parseInt(departmentFormCentralL.getMinistryName()));
				}	
			}
			orgLocated.setOlc(maxOrgCode1);
			orgLocated.setOrganization(orgBean);
			/* orgLocated.setOrguntDone(true); */
			orgLocated.setIsactive(true);
			orgLocatedCodeCent = organizationDAO.saveOrgLocatedAtLevel(orgLocated, session);
			if (departmentFormCentralL.isAUM())
				parentMp.put(orgLocatedCodeCent, departmentFormCentralL.getDeptName());
			else
				parentMp.put(orgLocatedCodeCent, departmentFormCentralL.getMinistryName());

			if (departmentFormCentralL.getSpecificLevel() != null) {
				String tempLevel[] = departmentFormCentralL.getSpecificLevel().split(",");
				orgLocated = null;
				for (int i = 0; i < tempLevel.length; i++) {
					orgLocated = new OrgLocatedAtLevels();
					orgLocated.setLocatedAtLevel(CommonUtil.setCategoryLevel(tempLevel[i].charAt(0)));
					orgLocated.setOrganization(orgBean);
					orgLocated.setIsactive(true);
					orgLocated.setOlc(maxOrgCode1);
					orgLocatedCode = organizationDAO.saveOrgLocatedAtLevel(orgLocated, session);

					if (tempLevel[i].charAt(0) == 'S') { // for State
						coverageLevel = listDepartmentFormStateL.get(itr).getLevelradio().charAt(0); // Full
																										// or
																										// Specific
						if (coverageLevel == 'S') { // If Specific
							maxCoverageCode = organizationDAO.getMaxCoverageCode();
							tempEntityCode = null;
							tempEntityCode = listDepartmentFormStateL.get(itr).getStateName().split(",");
							for (int k = 0; k < tempEntityCode.length; k++) {
								this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
							}
							this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
							maxCoverageCode++;
						}

						else { // for Full
							this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session);
						}
						orgLocated.setParentOrgLocatedLevelCode(orgLocatedCodeCent);
						orgLocated.setOrgLevelSpecificName(listDepartmentFormStateL.get(itr).getDeptName().trim());
						orgLocated.setOrgLevelSpecificNameLocal(listDepartmentFormStateL.get(itr).getDeptNameLocal());
						orgLocated.setOrgLevelSpecificShortName(listDepartmentFormStateL.get(itr).getShortDeptName());
						session.update(orgLocated);
						parentMp.put(orgLocatedCode, listDepartmentFormStateL.get(itr).getDeptName().trim());

						if (listDepartmentFormStateL.size() > 1){
							itr++;
						}	
						if (itr == listDepartmentFormStateL.size()){
							itr = 0;
						}	
					}

					if (tempLevel[i].charAt(0) == 'D') { // for District
						coverageLevel = listDepartmentFormDistrictL.get(itr).getLevelradio().charAt(0); // Full
																										// or
																										// Specific
						if (coverageLevel == 'S' && !departmentFormCentralL.isAUM()) {// If
																						// Specific
							if (listDepartmentFormDistrictL.get(itr).getSpecificLevel().equals("Full")) {
								tempEntityCode = null;
								tempEntityCode = listDepartmentFormDistrictL.get(itr).getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (listDepartmentFormDistrictL.get(itr).getSpecificLevel().equals("Part")) {
								tempEntityCode = null;
								tempEntityCode = listDepartmentFormDistrictL.get(itr).getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (listDepartmentFormDistrictL.get(itr).getSpecificLevel().equals("Full,Part")) {
								tempEntityCode = null;
								tempEntityCode = listDepartmentFormDistrictL.get(itr).getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = listDepartmentFormDistrictL.get(itr).getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
						} else if (coverageLevel == 'S' && departmentFormCentralL.isAUM()) {
							tempEntityCode = null;
							tempEntityCode = listDepartmentFormDistrictL.get(itr).getDistrictName().split(",");
							for (int k = 0; k < tempEntityCode.length; k++) {
								this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
							}
							this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
							maxCoverageCode++;
						} else { // for Full
							this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session);
						}
						parentOrgLocatedCode = this.getMapKey(parentMp, listDepartmentFormDistrictL.get(itr).getParent().trim());
						orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
						orgLocated.setOrgLevelSpecificName(listDepartmentFormDistrictL.get(itr).getDeptName().trim());
						orgLocated.setOrgLevelSpecificNameLocal(listDepartmentFormDistrictL.get(itr).getDeptNameLocal());
						orgLocated.setOrgLevelSpecificShortName(listDepartmentFormDistrictL.get(itr).getShortDeptName());
						session.update(orgLocated);
						parentMp.put(orgLocatedCode, listDepartmentFormDistrictL.get(itr).getDeptName().trim());

						if (listDepartmentFormDistrictL.size() > 1){
							itr++;
						}	
						if (itr == listDepartmentFormDistrictL.size()){
							itr = 0;
						}	
					}
					if (tempLevel[i].charAt(0) == 'T') { // for Sub District
						coverageLevel = departmentFormSubDistrictL.getLevelradio().charAt(0); // Full
																								// or
																								// Specific
						if (coverageLevel == 'S') {// If Specific
							tempEntityCode = null;
							if (departmentFormSubDistrictL.getSpecificLevel().equals("SFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormSubDistrictL.getSpecificLevel().equals("DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormSubDistrictL.getSpecificLevel().equals("DPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormSubDistrictL.getSpecificLevel().equals("SFull,DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormSubDistrictL.getSpecificLevel().equals("DFull,DPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormSubDistrictL.getSpecificLevel().equals("SFull,DFull,DPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;
							}
						} else { // for Full
							this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session);
						}
						parentOrgLocatedCode = this.getMapKey(parentMp, departmentFormSubDistrictL.getParent().trim());
						orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
						orgLocated.setOrgLevelSpecificName(departmentFormSubDistrictL.getDeptName().trim());
						orgLocated.setOrgLevelSpecificNameLocal(departmentFormSubDistrictL.getDeptNameLocal());
						orgLocated.setOrgLevelSpecificShortName(departmentFormSubDistrictL.getShortDeptName());
						session.update(orgLocated);
						parentMp.put(orgLocatedCode, departmentFormSubDistrictL.getDeptName().trim());
					}

					if (tempLevel[i].charAt(0) == 'B') { // for Block
						coverageLevel = departmentFormBlockL.getLevelradio().charAt(0); // Full
																						// or
																						// Specific
						if (coverageLevel == 'S') {// If Specific
							tempEntityCode = null;
							if (departmentFormBlockL.getSpecificLevel().equals("SFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormBlockL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormBlockL.getSpecificLevel().equals("DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormBlockL.getSpecificLevel().equals("DPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormBlockL.getBlockName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'B', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormBlockL.getSpecificLevel().equals("SFull,DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormBlockL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}

							if (departmentFormBlockL != null) {
								if (departmentFormBlockL.getSpecificLevel().equals("DFull,DPart")) {
									tempEntityCode = null;
									tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
									for (int k = 0; k < tempEntityCode.length; k++) {
										this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
									}
									this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
									maxCoverageCode++;

									tempEntityCode = null;
									tempEntityCode = departmentFormBlockL.getBlockName().split(",");
									for (int k = 0; k < tempEntityCode.length; k++) {
										this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
									}
									this.saveOrgCoverage('S', maxCoverageCode, 'B', orgLocatedCode, session);
									maxCoverageCode++;
								}
							}
							if (departmentFormBlockL.getSpecificLevel().equals("SFull,DFull,DPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormBlockL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormBlockL.getBlockName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'B', orgLocatedCode, session);
								maxCoverageCode++;
							}
						}

						else { // for Full
							this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session);
						}
						parentOrgLocatedCode = this.getMapKey(parentMp, departmentFormBlockL.getParent().trim());
						orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
						orgLocated.setOrgLevelSpecificName(departmentFormBlockL.getDeptName().trim());
						orgLocated.setOrgLevelSpecificNameLocal(departmentFormBlockL.getDeptNameLocal());
						orgLocated.setOrgLevelSpecificShortName(departmentFormBlockL.getShortDeptName());
						session.update(orgLocated);
						parentMp.put(orgLocatedCode, departmentFormBlockL.getDeptName().trim());
					}

					if (tempLevel[i].charAt(0) == 'V') { // for Village
						coverageLevel = departmentFormVillageL.getLevelradio().charAt(0); // Full
																							// or
																							// Specific
						if (coverageLevel == 'S') {// If Specific
							tempEntityCode = null;
							if (departmentFormVillageL.getSpecificLevel().equals("SFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SDFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SDPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getVillageName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SFull,DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SFull,DFull,SDFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("DFull,SDFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;

							}
							if (departmentFormVillageL.getSpecificLevel().equals("DFull,SDFull,SDPart")) {

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getVillageName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SFull,DFull,SDFull,SDPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getVillageName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.saveOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode, session);
								maxCoverageCode++;
							}
						} else { // for Full
							this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session);
						}
						parentOrgLocatedCode = this.getMapKey(parentMp, departmentFormVillageL.getParent().trim());
						orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
						orgLocated.setOrgLevelSpecificName(departmentFormVillageL.getDeptName().trim());
						orgLocated.setOrgLevelSpecificNameLocal(departmentFormVillageL.getDeptNameLocal());
						orgLocated.setOrgLevelSpecificShortName(departmentFormVillageL.getShortDeptName());
						session.update(orgLocated);
						parentMp.put(orgLocatedCode, departmentFormVillageL.getDeptName().trim());
					}
					// ---------------
					if (itr > 0) {
						i--;
					}
				}
			}
			tx.commit();
			// code for org_unit table
			tx = session.beginTransaction();
			session.createSQLQuery("select * from set_org_units_fn_new(" + maxOrgCode1 + "," + stateCode + ")").list();
			//lbodytoPes=organizationDAO.saveOrgDeptIntoPES(maxOrgCode1, session);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			//boolean flag = 
			this.deleteDeparmentTransaction(session, maxOrgCode1);

			LOG.error("Exception-->"+e);

			return false;
		} finally {
			ReleaseResources.doReleaseResources(session);
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean saveOrgCoverage(char coverageLevel, int coverageDetailCode, char coverageEntityType, int orgLocatedCode, Session session) throws Exception {
		OrganizationCoverage orgCoverage = new OrganizationCoverage();
		orgCoverage.setCoverage(coverageLevel);
		orgCoverage.setCoverageDetailCode(coverageDetailCode);
		orgCoverage.setIsactive(true);
		orgCoverage.setOrgCoverageEntityType(CommonUtil.setCategoryLevel(coverageEntityType));
		orgCoverage.setOrgLocatedLevelCode(orgLocatedCode);
		try {
			organizationDAO.saveOrgCoverage(orgCoverage, session);
			return true;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			return false;
		}
	}

	@Override
	public boolean saveOrgCoverageDetail(int maxCoverageCode, int entityCode, Session session) throws Exception {
		OrganizationCoverageDetail orgCoverageDetail = new OrganizationCoverageDetail();
		orgCoverageDetail.setCoverageCode(maxCoverageCode);
		orgCoverageDetail.setEntityCode(entityCode);
		orgCoverageDetail.setIsactive(true);
		try {
			organizationDAO.saveOrgCoverageDetail(orgCoverageDetail, session);
			return true;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			return false;
		}
	}

	@Override
	public List<Organization> getDepartmentList(String query) throws Exception {

		return organizationDAO.getDepartmentList(query);
	}

	@Override
	public List<Organization> getOrganizationList(String query) throws Exception {

		return organizationDAO.getOrganizationList(query);
	}

	private int getMapKey(Map<Integer, String> parentMp, String value) throws Exception {
		int key = 0;
		@SuppressWarnings("rawtypes")
		Set set = parentMp.entrySet();
		@SuppressWarnings("rawtypes")
		Iterator i = set.iterator();
		while (i.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry mp = (Map.Entry) i.next();
			if (mp.getValue().equals(value)) {
				key = (Integer) mp.getKey();
				break;
			}
		}
		return key;
	}

	@Override
	public boolean departmentUpdate(DepartmentForm departmentForm, int orgCode, HttpSession session, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String category = departmentForm.getSpecificLevel();

		/*
		 * List<State> stateList = null; stateList = new ArrayList<State>();
		 * State state = new State(); StatePK statePK = null; statePK = new
		 * StatePK(stateCode, stateVersion); stateList =
		 * stateDao.getStateList("from State s where s.statePK.stateCode=" +
		 * stateCode + " and s.statePK.stateVersion=" + stateVersion +
		 * " and isactive=true");
		 */

		/*
		 * Organization organizationbean = null; Organization
		 * organizationbeanisActive = null;
		 */

		Session session1 = null;
		Transaction tx1 = null;
		session1 = sessionFactory.openSession();
		tx1 = session1.beginTransaction();
		try {
			if (category.equalsIgnoreCase("S")) {
				// stateVersion =
				// stateDao.getCurrentVersionbyStateCode(stateCode);
				Integer orgVersion = organizationDAO.getMaxOrganizationVersion(departmentForm.getOrgCode());
				OrganizationPK orgpk = new OrganizationPK(departmentForm.getOrgCode(), orgVersion);
				organizationDAO.updateDepartment(departmentForm, orgpk, session1);
			} else {

				// List<OrgLocatedAtLevels>
				// orgLocatedAtLevelsList=session1.createSQLQuery("select orgLocatedLevelCode from OrgLocatedAtLevels where olc="+orgCode+" and locatedAtLevel='"+category.charAt(0)+"' and isactive=true").list();
				// Integer
				// orgLocatedCode=Integer.parseInt(session1.createQuery("select orgLocatedLevelCode from OrgLocatedAtLevels where olc="+orgCode+" and locatedAtLevel='"+category.charAt(0)+"' and isactive=true").uniqueResult().toString());
				OrgLocatedAtLevels orgLocatedAtLevelsupdate = (OrgLocatedAtLevels) session1.get(OrgLocatedAtLevels.class, orgCode);
				orgLocatedAtLevelsupdate.setOrgLevelSpecificName(departmentForm.getDeptNamecr());
				orgLocatedAtLevelsupdate.setOrgLevelSpecificNameLocal(departmentForm.getDeptNameLocal());
				orgLocatedAtLevelsupdate.setOrgLevelSpecificShortName(departmentForm.getShortDeptName());
				session1.update(orgLocatedAtLevelsupdate);
			}

			/*
			 * if (departmentForm.isCorrection()== true) { OrganizationPK orgpk
			 * = new OrganizationPK(departmentForm.getOrgCode(),orgVersion);
			 * 
			 * organizationDAO.updateDepartment(departmentForm,orgpk, session1);
			 * 
			 * }
			 */
			/*
			 * else if (departmentForm.isCorrection()== false) { OrganizationPK
			 * orgpk2 = new
			 * OrganizationPK(departmentForm.getOrgCode(),orgVersion);
			 * organizationDAO
			 * .updateisActive(organizationbeanisActive,orgpk2,session1); if
			 * (orgVersion == 0) { orgVersion = orgVersion+1; } else {
			 * orgVersion = orgVersion + 1; } OrganizationPK orgpk = new
			 * OrganizationPK(departmentForm.getOrgCode(),orgVersion);
			 * OrganizationType organizationType1=new OrganizationType(1); //1
			 * is Organisation Type Code for Ministry
			 * organizationbean.setOrganizationType(organizationType1);
			 * organizationbean.setOrgNameLocal("");
			 * organizationbean.setOrganizationPK(orgpk);
			 * organizationbean.setOrgName(departmentForm.getDeptName());
			 * organizationbean.setShortName(departmentForm.getShortDeptName());
			 * // organizationbean.setIslocalbodyspecific(departmentForm.
			 * isLocalBodySpecific()); //
			 * organizationbean.setOrgTypeCode(ministryForm.getOrgTypeCode());
			 * // organizationbean.setLocalBodyType(localBodyType);
			 * organizationbean.setOrgLevel('C'); ///pending.....
			 * organizationbean.setIsactive(true);
			 * organizationDAO.saveWithSession(organizationbean, session1);
			 * govtOrderService.saveGovernmentOrder(
			 * departmentForm.getOrderNo(), departmentForm.getOrderDate(),
			 * departmentForm.getOrdereffectiveDate(),
			 * departmentForm.getGazPubDate(), "LGT",
			 * departmentForm.getOrderPath
			 * (),departmentForm.getFilePath(),request);
			 * 
			 * //
			 * localGovtTypeDAO.SetGovermentOrderEntity(localBodyTypecode,'G');
			 * 
			 * }
			 */
			tx1.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Exception-->"+e);
			tx1.rollback();
			return false;
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.clear();
				session1.close();
			}
			
		}
		return true;
	}

	/**
	 * @Modified by sushil on 16-05-2013 for delete center level department
	 * @return boolean
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean abolishDepartment(int olc) throws Exception {
		List<OrgLocatedAtLevels> orgLocatedList = null;
		List<OrganizationCoverage> orgCoverageList = null;
		List<OrganizationCoverageDetail> orgCoverageDetailList = null;
		Session session = null;
		Transaction tx = null;
		int maxVersionCode = 0;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		maxVersionCode = this.getMaxOrganizationVersion(olc);
		OrganizationPK organizationPK = new OrganizationPK(olc, maxVersionCode);
		try {
			orgLocatedList = session.createQuery("from OrgLocatedAtLevels where olc=" + olc).list();
			for (int i = 0; i < orgLocatedList.size(); i++) {
				orgCoverageList = session.createQuery("from OrganizationCoverage where orgLocatedLevelCode=" + orgLocatedList.get(i).getOrgLocatedLevelCode()).list();
				for (int k = 0; k < orgCoverageList.size(); k++) {
					orgCoverageDetailList = session.createQuery("from OrganizationCoverageDetail where coverageCode=" + orgCoverageList.get(k).getCoverageDetailCode()).list();
					for (int l = 0; l < orgCoverageDetailList.size(); l++) {
						orgCoverageDetailList.get(l).setIsactive(false);
						session.update(orgCoverageDetailList.get(l));
					}
					orgCoverageList.get(k).setIsactive(false);
					session.update(orgCoverageList.get(k));
				}
				orgLocatedList.get(i).setIsactive(false);
				session.update(orgLocatedList.get(i));
			}
			Organization org = (Organization) session.get(Organization.class, organizationPK);
			org.setIsactive(false);
			session.update(org);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			LOG.error("Exception-->"+e);
			tx.rollback();
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationType> getOrganizationType() throws Exception {

		Session session=null;
		List<OrganizationType> list=null;
		session=sessionFactory.openSession();
		list=session.createQuery("from OrganizationType where orgTypeCode not in (1,2)").list();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationType> getOrganizationTypeforAddDesig() throws Exception {
		Session session=null;
		List<OrganizationType> list=null;
		session=sessionFactory.openSession();
		list=session.createQuery("from OrganizationType where orgTypeCode not in (1)").list();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getDepartmentListforOrg(String orgCode) throws Exception {
		Session session=null;
		List<Organization> list=null;
		session=sessionFactory.openSession();
		list=session.createQuery("from Organization where isactive=true and parentorgcode=" + Integer.parseInt(orgCode)).list();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return list;
	}

	@Override
	public List<StateLineDepartment> getStateLineDepartmentList(int stateCode, int deptCode, char entityType) throws Exception {

		List<StateLineDepartment> StateLineDepartment = new ArrayList<StateLineDepartment>();
		StateLineDepartment = organizationDAO.getStateLineDepartmentList(stateCode, deptCode, entityType);
		return StateLineDepartment;
	}

	@Override
	public List<DistrictLineDepartment> getDistrictLineDepartmentList(int districtCode, char level) throws Exception {
		List<DistrictLineDepartment> DistLineDepartment = null;
		DistLineDepartment = new ArrayList<DistrictLineDepartment>();
		DistLineDepartment = organizationDAO.getDistrictLineDepartmentList(districtCode, level);
		return DistLineDepartment;

	}

	/*
	 * public List<DistrictLineDepartment> getSubDistrictLineDepartmentList(int
	 * orgCode,char level) { List<DistrictLineDepartment>
	 * DistLineDepartment=null; String orgLocatedLevelCode=null; Session session
	 * =null;
	 * 
	 * try { session=sessionFactory.openSession();
	 * orgLocatedLevelCode=session.createSQLQuery(
	 * "select org_located_level_code from org_located_at_levels where isactive=true and located_at_level='"
	 * +level+"' and org_code="+orgCode).list().get(0).toString();
	 * System.out.println(">>>>>>jsdkajk======="+orgLocatedLevelCode);
	 * if(orgLocatedLevelCode !=null) { DistLineDepartment= new
	 * ArrayList<DistrictLineDepartment>(); DistLineDepartment
	 * =organizationDAO.getSubDistrictLineDepartmentList
	 * (Integer.parseInt(orgLocatedLevelCode)); } } catch (Exception e) {
	 * 
	 * LOG.error("Exception-->"+e); } finally { if(session.isOpen()) session.close(); }
	 * return DistLineDepartment; }
	 */

	@Override
	public List<DistrictLineDepartment> getSubDistrictLineDepartmentList(int subDistrictCode, char level) throws Exception {
		List<DistrictLineDepartment> SubDistLineDepartment = null;
		SubDistLineDepartment = new ArrayList<DistrictLineDepartment>();
		SubDistLineDepartment = organizationDAO.getSubDistrictLineDepartmentList(subDistrictCode, level);
		return SubDistLineDepartment;
	}

	@Override
	public List<DistrictLineDepartment> getBlockLineDepartmentList(int blockCode, char level) throws Exception {
		List<DistrictLineDepartment> blockLineDepartment = null;
		blockLineDepartment = new ArrayList<DistrictLineDepartment>();
		blockLineDepartment = organizationDAO.getBlockLineDepartmentList(blockCode, level);
		return blockLineDepartment;
	}

	@Override
	public List<DistrictLineDepartment> getVillageLineDepartmentList(int villageCode, char level) throws Exception {
		List<DistrictLineDepartment> villageLineDepartment = null;
		villageLineDepartment = new ArrayList<DistrictLineDepartment>();
		villageLineDepartment = organizationDAO.getVillageLineDepartmentList(villageCode, level);
		return villageLineDepartment;
	}

	/*
	 * public List<DistrictLineDepartment> getVillageLineDepartmentList(int
	 * orgCode,char level) { List<DistrictLineDepartment>
	 * DistLineDepartment=null; String orgLocatedLevelCode=null; Session session
	 * =null;
	 * 
	 * try { session=sessionFactory.openSession();
	 * orgLocatedLevelCode=session.createSQLQuery(
	 * "select org_located_level_code from org_located_at_levels where isactive=true and located_at_level='"
	 * +level+"' and org_code="+orgCode).list().get(0).toString();
	 * System.out.println(">>>>>>jsdkajk======="+orgLocatedLevelCode);
	 * if(orgLocatedLevelCode !=null) { DistLineDepartment= new
	 * ArrayList<DistrictLineDepartment>(); DistLineDepartment
	 * =organizationDAO.getVillageLineDepartmentList
	 * (Integer.parseInt(orgLocatedLevelCode)); } } catch (Exception e) {
	 * 
	 * LOG.error("Exception-->"+e); } finally { if(session.isOpen()) session.close(); }
	 * return DistLineDepartment; }
	 */

	private List<OrgLocatedAtLevels> getOrgLocatedbyOrgCode(int orgCode) throws Exception {
		return organizationDAO.getOrgLocatedbyOrgCode(orgCode);
	}

	public Integer getOrgLocatedbyOrgCodeforTopReporting(int orgCode, int olc) throws Exception {
		return organizationDAO.getOrgLocatedbyOrgCodeforTopReporting(orgCode, olc);
	}

	@Override
	public List<Organization> getOrgbyOrgTypeForReporting(int orgTypeCode, int orgCode) throws Exception {
		return organizationDAO.getOrgbyOrgTypeForReporting(orgTypeCode, orgCode);
	}

	@Override
	public List<OrganizationDesignation> getReportingDesignationForTopDesignation(int orgCode) throws Exception {
		return organizationDAO.getReportingDesignationForTopDesignation(orgCode);
	}

	@Override
	public boolean orgTypeUpdate(OrganizationTypeForm organizationTypeForm, int orgTypeCode, HttpSession session, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		Session session1 = null;
		Transaction tx1 = null;
		session1 = sessionFactory.openSession();
		tx1 = session1.beginTransaction();
		try {
			organizationDAO.updateOrgType(organizationTypeForm, orgTypeCode, session1);
			tx1.commit();
			return true;
		} catch (HibernateException e) {
			LOG.error("Exception-->"+e);
			tx1.rollback();
			return false;
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
		}
	}

	/**
	 * @Modified by sushil on 16-05-2013 for modify center level organization
	 * @return boolean
	 */
	@Override
	public boolean orgUpdate(OrganizationTypeForm organizationTypeForm, int orgCode, HttpSession session, HttpServletRequest request) throws Exception {
		Session session1 = null;
		Query query=null;
		Transaction tx1 = null;
		try {
			session1 = sessionFactory.openSession();
			tx1 = session1.beginTransaction();

			/*String category = null;
			if (session.getAttribute("category") != null) {
				category = session.getAttribute("category").toString();
			} else {
				category = "S";
			}
			 */
			
			query = session1.createSQLQuery(" select * from org_rename_fn(?,?,?) ");
			query.setParameter(0, orgCode,Hibernate.INTEGER);
			query.setParameter(1, organizationTypeForm.getOrgName(),Hibernate.STRING);
			query.setParameter(2, organizationTypeForm.getUserId(),Hibernate.INTEGER);
			Boolean flag=(boolean)query.uniqueResult();
			/*if (category.equalsIgnoreCase("S")) {
				Organization orgnizationObj = (Organization) organizationDAO.getOrgnizationObject(orgCode, session1);
				// Organization orgnizationObjInsert = orgnizationObj;
				orgnizationObj.setOrgName(organizationTypeForm.getOrgName());
				orgnizationObj.setOrgNameLocal(organizationTypeForm.getOrgNameLocal());
				orgnizationObj.setShortName(organizationTypeForm.getShortName());
				session1.update(orgnizationObj);
			} else {
				OrgLocatedAtLevels orgLocatedAtLevels = (OrgLocatedAtLevels) session1.get(OrgLocatedAtLevels.class, orgCode);
				orgLocatedAtLevels.setOrgLevelSpecificName(organizationTypeForm.getOrgName());
				orgLocatedAtLevels.setOrgLevelSpecificNameLocal(organizationTypeForm.getOrgNameLocal());
				orgLocatedAtLevels.setOrgLevelSpecificShortName(organizationTypeForm.getShortName());
				session1.update(orgLocatedAtLevels);

			}*/
			tx1.commit();
			return flag;
		} catch (HibernateException e) {
			e.printStackTrace();
			LOG.error("Exception-->"+e);
			tx1.rollback();
			return false;
		} finally {
			if (session1 != null && session1.isOpen()){
				session1.close();
			}	
		}
	}

	@Override
	public int getRecordsforOrganization(int orgTypeCode) throws Exception {
		return organizationDAO.getRecordsforOrganization(orgTypeCode);
	}

	@Override
	public List<OrganizationTypeForm> getOrgTypeDetails(int orgtypeCode) throws Exception {

		return organizationDAO.getOrgTypeDetails(orgtypeCode);
	}

	@Override
	public OrganizationDesignation getOrgDesignationDetails(int orgCode) throws Exception {
		List<OrganizationDesignation> orgDesignationDAO = organizationDAO.getReportingDesignationForTopDesignation(orgCode);
		OrganizationDesignation designation = new OrganizationDesignation();
		List<OrgLocatedAtLevels> locatedAtLevel = new ArrayList<OrgLocatedAtLevels>();
		int orgLocatedCode = 0;
		String desigE = null;
		locatedAtLevel = organizationDAO.getOrgLocatedbyOrgCode(orgCode);
		for (int i = 0; i < locatedAtLevel.size(); i++) {
			/*
			 * if (locatedAtLevel.get(i).getLocatedAtLevel() ==
			 * orgDesignationDAO .get(0).getLocatedAtLevel()) { orgLocatedCode =
			 * locatedAtLevel.get(i).getOrgLocatedLevelCode(); }
			 */}
		desigE = orgDesignationDAO.get(0).isIsmultiple() + "," + orgDesignationDAO.get(0).getDesignationName() + "," + orgLocatedCode + "~";

		if (orgDesignationDAO.size() > 1) {
			for (int k = 1; k < orgDesignationDAO.size(); k++) {
				orgLocatedCode = 0;
				for (int i = 0; i < locatedAtLevel.size(); i++) {
					/*
					 * if (locatedAtLevel.get(i).getLocatedAtLevel() ==
					 * orgDesignationDAO .get(k).getLocatedAtLevel()) {
					 * orgLocatedCode = locatedAtLevel.get(i)
					 * .getOrgLocatedLevelCode(); }
					 */
				}
				desigE += orgDesignationDAO.get(k).isIsmultiple() + "," + orgDesignationDAO.get(k).getDesignationName() + "," + orgLocatedCode + "~";
			}
		}
		designation.setDesignationName(desigE);

		return designation;
	}

	@Override
	public List<OrgLocatedAtLevels> getOrgLocatedbyOrgCodemodify(int orgCode) throws Exception {
		return organizationDAO.getOrgLocatedbyOrgCodemodify(orgCode);
	}

	@Override
	public List<OrganizationCoverage> getOrgCoveragemodify(int orgLevelCode) throws Exception {
		return organizationDAO.getOrgCoveragemodify(orgLevelCode);
	}

	@Override
	public List<OrganizationCoverageDetail> getOrgCoverageDetailmodify(int coverageDetailCode) throws Exception {
		return organizationDAO.getOrgCoverageDetailmodify(coverageDetailCode);
	}

	@Override
	public boolean updateDepartment(DepartmentForm departmentFormCentralL, List<DepartmentForm> listDepartmentFormStateL, List<DepartmentForm> listDepartmentFormDistrictL, DepartmentForm departmentFormSubDistrictL,
			DepartmentForm departmentFormVillageL) throws Exception {
		Session session = null;
		Transaction tx = null;
		Organization orgBean = null;
		OrganizationType organizationType = null;
		OrganizationPK organizationPK = null;
		State state = null;
		StatePK statepk = null;
		String[] tempEntityCode = null;

		int maxOrgCode = 0;
		int maxCoverageCode = 0;
		char coverageLevel = 0;
		int orgLocatedCode = 0;
		int orgLocatedCodeCent = 0;
		int parentOrgLocatedCode = 0;
		int itr = 0;
		Map<Integer, String> parentMp = new HashMap<Integer, String>();

		maxOrgCode = organizationDAO.getMaxOrgCode();
		maxCoverageCode = organizationDAO.getMaxCoverageCode();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			// Organisation Code
			state = new State();
			statepk = new StatePK(stateCode, stateVersion);
			state.setStatePK(statepk);
			organizationType = new OrganizationType();
			orgBean = new Organization();
			organizationPK = new OrganizationPK(maxOrgCode, 1);
			if (departmentFormCentralL.isOrganization()) {
				organizationType.setOrgTypeCode(Integer.parseInt(departmentFormCentralL.getOrgType()));
				orgBean.setParentorgcode(Integer.parseInt(departmentFormCentralL.getDeptOrgCode()));
			} else {
				organizationType.setOrgTypeCode(2);
				orgBean.setParentorgcode(Integer.parseInt(departmentFormCentralL.getMinistryName()));
			}
			orgBean.setOrganizationPK(organizationPK);
			orgBean.setOrgName(departmentFormCentralL.getDeptName());
			orgBean.setOrgNameLocal(departmentFormCentralL.getDeptNameLocal());
			orgBean.setShortName(departmentFormCentralL.getShortDeptName());
			orgBean.setOrgLevel('C');
			orgBean.setOrganizationType(organizationType);
			orgBean.setIsactive(true);
			// orgBean.setState(state);
			organizationDAO.updateOrganization(orgBean, session);// //////////correct

			// Organisation Located At Code
			OrgLocatedAtLevels orgLocated = null;
			orgLocated = new OrgLocatedAtLevels();
			orgBean.setOrganizationPK(organizationPK);
			orgLocated.setLocatedAtLevel('C');
			if (departmentFormCentralL.isOrganization()) {
				orgLocated.setParentOrgLocatedLevelCode(Integer.parseInt(departmentFormCentralL.getOrgType()));
			} else {
				orgLocated.setParentOrgLocatedLevelCode(Integer.parseInt(departmentFormCentralL.getMinistryName()));
			}
			orgLocated.setOrganization(orgBean);
			orgLocated.setIsactive(true);
			orgLocatedCodeCent = organizationDAO.updateOrgLocatedAtLevel(orgLocated, session);// //////////
			parentMp.put(orgLocatedCode, departmentFormCentralL.getMinistryName());

			if (departmentFormCentralL.getSpecificLevel() != null) {
				String tempLevel[] = departmentFormCentralL.getSpecificLevel().split(",");
				orgLocated = null;
				for (int i = 0; i < tempLevel.length; i++) {
					orgLocated = new OrgLocatedAtLevels();
					orgLocated.setLocatedAtLevel(tempLevel[i].charAt(0));
					orgLocated.setOrganization(orgBean);
					orgLocated.setIsactive(true);
					orgLocatedCode = organizationDAO.updateOrgLocatedAtLevel(orgLocated, session);// //////////

					if (tempLevel[i].charAt(0) == 'S') { // for State
						coverageLevel = listDepartmentFormStateL.get(itr).getLevelradio().charAt(0); // Full
																										// or
																										// Specific
						if (coverageLevel == 'S') { // If Specific
							maxCoverageCode = organizationDAO.getMaxCoverageCode();
							tempEntityCode = null;
							tempEntityCode = listDepartmentFormStateL.get(itr).getStateName().split(",");
							for (int k = 0; k < tempEntityCode.length; k++) {
								this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
							}
							this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
							maxCoverageCode++;
						}

						else { // for Full
							this.updateOrgCoverage('F', 0, 'C', orgLocatedCode, session);
						}
						orgLocated.setParentOrgLocatedLevelCode(orgLocatedCodeCent);
						orgLocated.setOrgLevelSpecificName(listDepartmentFormStateL.get(itr).getDeptName().trim());
						orgLocated.setOrgLevelSpecificNameLocal(listDepartmentFormStateL.get(itr).getDeptNameLocal());
						orgLocated.setOrgLevelSpecificShortName(listDepartmentFormStateL.get(itr).getShortDeptName());
						session.update(orgLocated);
						parentMp.put(orgLocatedCode, listDepartmentFormStateL.get(itr).getDeptName().trim());

						if (listDepartmentFormStateL.size() > 1){
							itr++;
						}	
						if (itr == listDepartmentFormStateL.size()){
							itr = 0;
						}	
					}

					if (tempLevel[i].charAt(0) == 'D') { // for District
						coverageLevel = listDepartmentFormDistrictL.get(itr).getLevelradio().charAt(0); // Full
																										// or
																										// Specific
						if (coverageLevel == 'S') {// If Specific
							if (listDepartmentFormDistrictL.get(itr).getSpecificLevel().equals("Full")) {
								tempEntityCode = null;
								tempEntityCode = listDepartmentFormDistrictL.get(itr).getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);// ////
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (listDepartmentFormDistrictL.get(itr).getSpecificLevel().equals("Part")) {
								tempEntityCode = null;
								tempEntityCode = listDepartmentFormDistrictL.get(itr).getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (listDepartmentFormDistrictL.get(itr).getSpecificLevel().equals("Full,Part")) {
								tempEntityCode = null;
								tempEntityCode = listDepartmentFormDistrictL.get(itr).getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = listDepartmentFormDistrictL.get(itr).getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
						} else { // for Full
							this.updateOrgCoverage('F', 0, 'C', orgLocatedCode, session);
						}
						parentOrgLocatedCode = this.getMapKey(parentMp, listDepartmentFormDistrictL.get(itr).getParent().trim());
						orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
						orgLocated.setOrgLevelSpecificName(listDepartmentFormDistrictL.get(itr).getDeptName().trim());
						orgLocated.setOrgLevelSpecificNameLocal(listDepartmentFormDistrictL.get(itr).getDeptNameLocal());
						orgLocated.setOrgLevelSpecificShortName(listDepartmentFormDistrictL.get(itr).getShortDeptName());
						session.update(orgLocated);
						parentMp.put(orgLocatedCode, listDepartmentFormDistrictL.get(itr).getDeptName().trim());

						if (listDepartmentFormDistrictL.size() > 1){
							itr++;
						}	
						if (itr == listDepartmentFormDistrictL.size()){
							itr = 0;
						}	
					}
					if (tempLevel[i].charAt(0) == 'T') { // for Sub District
						coverageLevel = departmentFormSubDistrictL.getLevelradio().charAt(0); // Full
																								// or
																								// Specific
						if (coverageLevel == 'S') {// If Specific
							tempEntityCode = null;
							if (departmentFormSubDistrictL.getSpecificLevel().equals("SFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormSubDistrictL.getSpecificLevel().equals("DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormSubDistrictL.getSpecificLevel().equals("DPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormSubDistrictL.getSpecificLevel().equals("SFull,DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormSubDistrictL.getSpecificLevel().equals("SFull,DFull,DPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;
							}
						}

						else { // for Full
							this.updateOrgCoverage('F', 0, 'C', orgLocatedCode, session);
						}
						parentOrgLocatedCode = this.getMapKey(parentMp, departmentFormSubDistrictL.getParent().trim());
						orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
						orgLocated.setOrgLevelSpecificName(departmentFormSubDistrictL.getDeptName().trim());
						orgLocated.setOrgLevelSpecificNameLocal(departmentFormSubDistrictL.getDeptNameLocal());
						orgLocated.setOrgLevelSpecificShortName(departmentFormSubDistrictL.getShortDeptName());
						session.update(orgLocated);
						parentMp.put(orgLocatedCode, departmentFormSubDistrictL.getDeptName().trim());
					}
					if (tempLevel[i].charAt(0) == 'V') { // for Village
						coverageLevel = departmentFormVillageL.getLevelradio().charAt(0); // Full
																							// or
																							// Specific
						if (coverageLevel == 'S') {// If Specific
							tempEntityCode = null;
							if (departmentFormVillageL.getSpecificLevel().equals("SFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SDFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SDPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getVillageName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SFull,DFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SFull,DFull,SDFull")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;
							}
							if (departmentFormVillageL.getSpecificLevel().equals("SFull,DFull,SDFull,SDPart")) {
								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getStateName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
								maxCoverageCode++;

								tempEntityCode = null;
								tempEntityCode = departmentFormVillageL.getVillageName().split(",");
								for (int k = 0; k < tempEntityCode.length; k++) {
									this.updateOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
								}
								this.updateOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode, session);
								maxCoverageCode++;
							}
						} else { // for Full
							this.updateOrgCoverage('F', 0, 'C', orgLocatedCode, session);
						}
						parentOrgLocatedCode = this.getMapKey(parentMp, departmentFormVillageL.getParent().trim());
						orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
						orgLocated.setOrgLevelSpecificName(departmentFormVillageL.getDeptName().trim());
						orgLocated.setOrgLevelSpecificNameLocal(departmentFormVillageL.getDeptNameLocal());
						orgLocated.setOrgLevelSpecificShortName(departmentFormVillageL.getShortDeptName());
						session.update(orgLocated);
						parentMp.put(orgLocatedCode, departmentFormVillageL.getDeptName().trim());
					}
					// ---------------
					if (itr > 0) {
						i--;
					}
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			tx.rollback();
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean updateOrgCoverageDetail(int maxCoverageCode, int entityCode, Session session) throws Exception {
		OrganizationCoverageDetail orgCoverageDetail = new OrganizationCoverageDetail();
		orgCoverageDetail.setCoverageCode(maxCoverageCode);
		orgCoverageDetail.setEntityCode(entityCode);
		orgCoverageDetail.setIsactive(true);
		try {
			organizationDAO.updateOrgCoverageDetail(orgCoverageDetail, session);
			return true;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			return false;
		}
	}

	@Override
	public boolean updateOrgCoverage(char coverageLevel, int coverageDetailCode, char coverageEntityType, int orgLocatedCode, Session session) throws Exception {
		OrganizationCoverage orgCoverage = new OrganizationCoverage();
		orgCoverage.setCoverage(coverageLevel);
		orgCoverage.setCoverageDetailCode(coverageDetailCode);
		orgCoverage.setIsactive(true);
		orgCoverage.setOrgCoverageEntityType(coverageEntityType);
		orgCoverage.setOrgLocatedLevelCode(orgLocatedCode);
		try {
			organizationDAO.updateOrgCoverage(orgCoverage, session);
			return true;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			return false;
		}
	}

	@Override
	public boolean orgTypeDelete(OrganizationTypeForm organizationTypeForm, int orgTypeCode, HttpSession session, HttpServletRequest request) throws Exception {
		Session session1 = null;

		try {
			session1 = sessionFactory.openSession();

			organizationDAO.deleteOrgType(organizationTypeForm, orgTypeCode, session1);

			return true;
		} catch (HibernateException e) {
			LOG.error("Exception-->"+e);

			return false;
		} finally {
			if (session1 != null && session1.isOpen()){
				session1.close();
			}	
		}
	}

	public GovernmentOrder saveDataInGovtOrder(MinistryForm govtForm, Session session) throws Exception {

		GovernmentOrder governmentOrder = new GovernmentOrder();
		try {
			governmentOrder.setOrderDate(govtForm.getOrderDate());
			governmentOrder.setEffectiveDate(govtForm.getOrdereffectiveDate());
			governmentOrder.setGazPubDate(govtForm.getGazPubDate());
			governmentOrder.setCreatedon(new Date());
			governmentOrder.setDescription("LGD DETAILS");
			governmentOrder.setIssuedBy("GOVERNOR");
			governmentOrder.setCreatedby(createdBy);
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby(createdBy);
			governmentOrder.setLevel("S");
			governmentOrder.setOrderNo(govtForm.getOrderNo());
			governmentOrder.setStatus('A');
			governmentOrder.setUserId(userId);
			localGovtBodyDAO.saveOrderDetails(governmentOrder, session);
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
		}
		return governmentOrder;
	}

	public boolean saveDataInGovtOrderfordeletedepartment(MinistryForm viewDept) {

		GovernmentOrder governmentOrder = new GovernmentOrder();
		GovernmentOrderEntityWise governmentOrderEntityWise = new GovernmentOrderEntityWise();
		try {
			governmentOrder.setOrderDate(new Date());
			governmentOrder.setEffectiveDate(new Date());
			governmentOrder.setGazPubDate(new Date());
			governmentOrder.setCreatedon(new Date());
			governmentOrder.setDescription("LGD DETAILS");
			governmentOrder.setIssuedBy("GOVERNOR");
			governmentOrder.setCreatedby(createdBy);
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby(createdBy);
			governmentOrder.setLevel("S");
			governmentOrder.setOrderNo(viewDept.getOrderNo());
			governmentOrder.setStatus('A');
			governmentOrder.setUserId(userId);
			governmentOrderEntityWise.setEntityCode(viewDept.getDeptCode());
			governmentOrderEntityWise.setEntityType('G');
			Integer OrderCode = localGovtBodyDAO.getmaxgovtordercode();
			governmentOrderEntityWise.setOrderCode(OrderCode + 1);
			governmentOrderEntityWise.setEntityVersion(2);
			governmentOrderEntityWise.setGovernmentOrder(governmentOrder);
			localGovtBodyDAO.saveOrderDetailsfordeletedepartment(governmentOrder);
			// localGovtBodyDAO.saveDataIngovtorderentityfordeletedepartment(governmentOrderEntityWise);
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			return false;
		}
		return true;
	}

	public void saveDataInAttachment(GovernmentOrder govtOrder, MinistryForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws Exception {

		Attachment attachment = null;
		try {
			Iterator<AttachedFilesForm> it = attachedList.iterator();
			while (it.hasNext()) {
				AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
				attachment = new Attachment();
				attachment.setGovernmentOrder(govtOrder);
				attachment.setFileName(filesForm.getFileName());
				attachment.setFileSize(filesForm.getFileSize());
				attachment.setFileContentType(filesForm.getFileType());
				attachment.setFileLocation(filesForm.getFileLocation());
				attachment.setFileTimestamp(filesForm.getFileTimestampName());
				session.save(attachment);
				session.flush();
				if (session.contains(attachment)){
					session.evict(attachment);
				}	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Exception-->"+e);
		}
	}

	@Override
	public MinistryForm getgovernmentOrder(Integer orderCode) {
		GovernmentOrder governmentOrder = localGovtBodyDAO.viewgovernmentOrder(orderCode);
		MinistryForm govtForm1 = new MinistryForm();
		try {
			govtForm1.setOrderDate(governmentOrder.getOrderDate());
			govtForm1.setEffectiveDate(governmentOrder.getEffectiveDate());
			govtForm1.setGazPubDate(governmentOrder.getGazPubDate());
			govtForm1.setOrderNo(governmentOrder.getOrderNo());

		} catch (Exception e) {
			LOG.error("Exception-->"+e);
		}
		return govtForm1;
	}

	public List<DepartmentDataForm> getOrgDetailsModify() throws Exception {
		return null;
	}

	@Override
	public List<GetOrganizationListFn> getOrgbyTypeCodeAtlevel(int orgTypeCode, Integer orgLevel, int stateCode) throws Exception {
		return organizationDAO.getOrgbyTypeCodeAtlevel(orgTypeCode, orgLevel, stateCode);
	}

	@Override
	public List<State> getSlc(int stateCode) throws Exception {
		return organizationDAO.getSlc(stateCode);
	}

	@Override
	public List<GetOrganizationAtLevels> getOrgAtLevels(int orgTypeCode, Integer orgLevel) throws Exception {
		return organizationDAO.getOrgAtLevel(orgTypeCode, orgLevel);
	}

	@Override
	public boolean saveOrganizationReporting(DesignationForm designationForm) throws Exception {
		Session session = null;
		Transaction txn = null;
		boolean modifyFlag = false;
		int designationCode = 0;
		OrganizationDesignation orgDesignation = new OrganizationDesignation();
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			Organization orgObject = fetchOrgnisationByOrgCode(designationForm.getOrgCode(), session);

			OrgLocatedAtLevels orgAtLevObj = fetchOrgAtLevelsByLevlCode(designationForm.getLocatedAtLevel(), session);
			// orgDesignation.setOrganization(orgObject);
			orgDesignation.setOlc(orgObject.getOlc());
			orgDesignation.setOrgLocatedAtLevels(orgAtLevObj);

			String desigName = designationForm.getDesgName();
			//String desigNamLocal = designationForm.getDesgNameLocal();
			//String isMultiple = designationForm.getIsMultiple();
			//String desigNameArry[] = null;
			//String desigNamLocalAry[] = null;
			//String isMultipleAry[] = null;

			@SuppressWarnings("unchecked")
			List<OrganizationDesignation> isExist = sessionFactory.openSession()
					.createQuery("from OrganizationDesignation where olc=" + designationForm.getOrgCode() + " and orgLocatedAtLevels.orgLocatedLevelCode=" + designationForm.getLocatedAtLevel() + " order by designationCode").list();

			/*if (desigNamLocal != null && desigNamLocal.contains(",")){
				desigNamLocalAry = desigNamLocal.split(",");
			}	
			if (isMultiple != null && isMultiple.contains(",")){
				isMultipleAry = isMultiple.split(",");
			}	*/

			designationCode = organizationDAO.getMaxRecords("select COALESCE(max(designation_code),1) from oraganization_designation");
			designationCode = designationCode + 1;
			if (isExist.size() > 0) {
				if (desigName != null && desigName.contains(",")) {
					String temp[] = designationForm.getIsMultiple().split(",");
					String tempCPermanent[] =  designationForm.getIsContractPer().split(",");
					String tempDesgName[] = designationForm.getDesgName().split(",");
					String tempDesgNameLocal[] = designationForm.getDesgNameLocal().split(",");
					String tempModDesig[] = designationForm.getModifiedDesignation().split("~");
					String tempDesg[] = null;

					for (int i = 0; i < tempDesgName.length; i++) {
						modifyFlag = false;
						OrganizationDesignation designation = new OrganizationDesignation();
						designation.setOlc(orgObject.getOlc());
						designation.setOrgLocatedAtLevels(orgAtLevObj);
						if (i < isExist.size()) {
							for (int k = 0; k < isExist.size(); k++) {
								if (isExist.get(i).getDesignationName().trim().equals(tempDesgName[i].trim())) {
									designation = isExist.get(i);
									modifyFlag = true;
									break;
								}
							}

							if (modifyFlag == true) {
								designation.setDesignationName(tempDesgName[i].trim());

								if (tempDesgNameLocal[i].trim() != "") {
									designation.setDesignationNameLocal(tempDesgNameLocal[i].trim());
								} else {
									designation.setDesignationNameLocal("");
								}
								if (i == 0) {
									designation.setIstopdesignation(true);
								} else {
									designation.setIstopdesignation(false);
									designation.setIsmultiple(Boolean.parseBoolean(temp[i - 1]));
									designation.setIscontractpermanent(Boolean.parseBoolean(tempCPermanent[i - 1]));
								}
							}
						} else {
							for (int k = 0; k < tempModDesig.length;) {
								tempDesg = tempModDesig[k].split(",");

								// designation.setDesignationCode(designationCode);
								designation.setDesignationName(tempDesgName[i].trim());
								if (tempDesgNameLocal[i] != null) {
									designation.setDesignationNameLocal(tempDesgNameLocal[i].trim());
								} else {
									designation.setDesignationNameLocal("");
								}
								designation.setIsmultiple(Boolean.parseBoolean(tempDesg[2]));
								designation.setIscontractpermanent(Boolean.parseBoolean(tempDesg[3]));
								if (i == 0){
									designation.setIstopdesignation(true);
								}	
								else{
									designation.setIstopdesignation(false);
								}
								designationCode += 1;
								break;
							}
						}
						organizationDAO.saveDesignation(designation, session);
						session.flush();
						session.clear();
					}
				}
			} else {
				String temp[] = null;
				String tempCPermanent[] = null;
				String tempDesgName[] = null;
				String tempDesgNameLocal[] = null;
				if (designationForm.getIsMultiple() != null && !designationForm.getIsMultiple().isEmpty()) {
					temp = designationForm.getIsMultiple().split(",");
				}
				if(designationForm.getIsContractPer() !=null &&   !designationForm.getIsContractPer().isEmpty())
				{
					tempCPermanent =  designationForm.getIsContractPer().split(",");
				}
				if (designationForm.getDesgName() != null && !designationForm.getDesgName().isEmpty()) {
					tempDesgName = designationForm.getDesgName().split(",");
				}
				if (designationForm.getDesgNameLocal() != null && !designationForm.getDesgNameLocal().isEmpty()) {
					tempDesgNameLocal = designationForm.getDesgNameLocal().split(",");
				}
				try {
					if (tempDesgName != null && tempDesgName.length >= 1) {
						for (int i = 0; i < tempDesgName.length; i++) {
							OrganizationDesignation designation = new OrganizationDesignation();
							designation.setOlc(orgObject.getOlc());
							designation.setOrgLocatedAtLevels(orgAtLevObj);
							if (i == 0) {
								designation.setDesignationCode(designationCode);
								designation.setDesignationName(tempDesgName[i]);
								if (tempDesgNameLocal != null && tempDesgNameLocal.length >= 1){
									designation.setDesignationNameLocal(tempDesgNameLocal[i]);
								}	
								designation.setIstopdesignation(true);
								designation.setIscontractpermanent(true);
							} else {
								designation.setDesignationCode(designationCode);
								designation.setDesignationName(tempDesgName[i]);
								if (tempDesgNameLocal != null && tempDesgNameLocal.length >= 1){
									designation.setDesignationNameLocal(tempDesgNameLocal[i]);
								}	
								designation.setIsmultiple(Boolean.parseBoolean(temp[i - 1]));
								designation.setIstopdesignation(false);
								designation.setIscontractpermanent(Boolean.parseBoolean(tempCPermanent[i - 1]));
							}
							organizationDAO.saveDesignationData(designation, session);
							// session.flush();
							// session.clear();
							designationCode += 1;
						}
					}
				} catch (Exception e) {
					LOG.error("Exception-->"+e);
				}
			}
			txn.commit();
			return true;
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
				LOG.error("Exception-->"+e);
			}
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public static void saveOtherOrgDesignation(OrganizationDesignation orgDesignation, DesignationForm designationForm, Session session) {
		try {
			// Save Other Designations

			orgDesignation.setIsactive(true);
			orgDesignation.setIstopdesignation(false);

			session.saveOrUpdate(orgDesignation);
			if (session.contains(orgDesignation)) {
				session.flush();
				session.evict(orgDesignation);
			}
		} catch (Exception e) {
		}
	}

	public static void saveTopOrgDesignation(OrganizationDesignation orgDesignation, DesignationForm designationForm, Session session) {
		try {
			// Save Top Designation
			orgDesignation.setDesignationName(designationForm.getTopDesigName());
			orgDesignation.setDesignationNameLocal(designationForm.getTopDesigNameeLocal());
			orgDesignation.setIsactive(true);
			orgDesignation.setIsmultiple(false);
			orgDesignation.setIstopdesignation(true);

			session.save(orgDesignation);
			if (session.contains(orgDesignation)) {
				session.flush();
				session.evict(orgDesignation);
			}
		} catch (Exception e) {

		}
	}

	private static Organization fetchOrgnisationByOrgCode(int orgCode, Session session) {
		Query query;
		Organization orgObj = null;
		try {
			query = session.createQuery("From Organization Where isactive=true And organizationPK.orgCode=" + orgCode);
			@SuppressWarnings("unchecked")
			List<Organization> fetchOrgnisationByOrgCode = query.list();
			if (fetchOrgnisationByOrgCode != null && fetchOrgnisationByOrgCode.get(0) != null) {
				orgObj = fetchOrgnisationByOrgCode.get(0);
			}
			return orgObj;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
		}
		return null;
	}

	private static OrgLocatedAtLevels fetchOrgAtLevelsByLevlCode(int orgLevelCode, Session session) {
		Query query = null;
		OrgLocatedAtLevels orgLAtLevelObj = null;
		try {
			query = session.createQuery("From OrgLocatedAtLevels Where isactive=true And orgLocatedLevelCode=" + orgLevelCode);
			@SuppressWarnings("unchecked")
			List<OrgLocatedAtLevels> fetchOrgAtLevelsByLevlCode = query.list();
			if (fetchOrgAtLevelsByLevlCode != null && fetchOrgAtLevelsByLevlCode.get(0) != null){
				orgLAtLevelObj = fetchOrgAtLevelsByLevlCode.get(0);
			}	
			return orgLAtLevelObj;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
		}
		return null;
	}

	public List<OrganizationDesignation> getDesignationReporting(int orglevel, int olc) throws Exception {
		return organizationDAO.getDesignationReporting(orglevel, olc);
	}

	public LgdDesignation getDesignationReportingOrganizDetail(int orglevel, int olc) throws Exception {
		return organizationDAO.getDesignationReportingOrganizDetail(orglevel, olc);
	}
	
	public List<LgdDesignation> getDesignationReportingOrganiz(int orglevel, int olc) throws Exception {
		return organizationDAO.getDesignationReportingOrganiz(orglevel, olc);
	}

	public List<LgdDesignation> getTopDesignationReporting(int orglevel, int olc) throws Exception {
		return organizationDAO.getTopDesignationReporting(orglevel, olc);
	}
	
	public List<OrganizationDesignation> getReportTo(int orglevel, int olc) throws Exception {
		return organizationDAO.getReportTo(orglevel, olc);
	}

	public boolean getParentOrgLevel(int orglevel, int olc) throws Exception {
		return organizationDAO.getParentOrgLevel(orglevel, olc);
	}

	public List<OrganizationDesignation> getTopLevelReportTo(int orglevel, int olc) throws Exception {
		return organizationDAO.getTopLevelReportTo(orglevel, olc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationType> getOrganizationTypeListbyOrgTypeCode(Integer OrgTypeCode) throws Exception {

		Session session=null;
		List<OrganizationType> list=null;
		session=sessionFactory.openSession();
		list=session.createQuery("from OrganizationType where orgTypeCode=:orgTypeCode").setParameter("orgTypeCode", OrgTypeCode).list();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationType> getOrganizationTypeListbyOrgType(String OrgType) throws Exception {

		Session session=null;
		List<OrganizationType> list=null;
		session=sessionFactory.openSession();
		list=session.createQuery("from OrganizationType where orgType like upper(:orgType) or  orgType like :orgType").setParameter("orgType", OrgType + "%").list();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return list;
	}

	@Override
	public boolean checktOrganizationTypeNameExist(String OrgTypeName) throws Exception {

		return checktOrganizationTypeNameExist(OrgTypeName);

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean ChecktOrganizationTypeExist(Integer OrgTypeCode) throws Exception {

		List<Organization> orgList = null;
		Session session = null;

		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Organization where orgTypeCode=:orgTypeCode").setParameter("orgTypeCode", OrgTypeCode);
			orgList = query.list();
			if (orgList.size() > 0)
				return false;
			else
				return true;

		} catch (Exception e) {

			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public boolean deleteOrganizationType(Integer OrgTypeCode) throws Exception {

		Session session = null;

		Query query = null;

		try {
			session = sessionFactory.openSession();
			String hql = "delete from OrganizationType where orgTypeCode = :orgTypeCode";
			query = session.createQuery(hql).setParameter("orgTypeCode", OrgTypeCode);
			int rowCount = query.executeUpdate();

			if (rowCount > 0)
				return true;
			else
				return false;

		} catch (Exception e) {

			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganizationType> getOrganizationType(Integer OrgTypeCode) throws Exception {

		List<OrganizationType> orgList = null;
		Session session = null;

		Query query = null;

		try {
			session = sessionFactory.openSession();
			String hql = "from OrganizationType where orgTypeCode = :orgTypeCode";
			query = session.createQuery(hql).setParameter("orgTypeCode", OrgTypeCode);
			orgList = query.list();
			return orgList;

		} catch (Exception e) {

			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public boolean updateOrganizationType(OrganizationType organizationType) throws Exception {

		Session session = null;
		session = sessionFactory.openSession();
		Transaction tx = null;
		boolean flag = false;

		try {
			tx = session.beginTransaction();
			flag = organizationDAO.updateOrganizationTypeDAO(organizationType, session);
			if (flag){
				tx.commit();
			}	
			return flag;

		} catch (Exception e) {
			tx.rollback();
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	public boolean deleteDeparmentTransaction(Session session, int maxOrgCode1) {
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			String delOrgCoverageDetail = "delete from OrganizationCoverageDetail where coverageCode in(select coverageDetailCode from  OrganizationCoverage where orgLocatedLevelCode in (select orgLocatedLevelCode from OrgLocatedAtLevels where olc=:olc))";
			session.createQuery(delOrgCoverageDetail).setParameter("olc", maxOrgCode1).executeUpdate();
			String delOrgCoverage = "delete from  OrganizationCoverage where orgLocatedLevelCode in (select orgLocatedLevelCode from OrgLocatedAtLevels where olc=:olc)";
			session.createQuery(delOrgCoverage).setParameter("olc", maxOrgCode1).executeUpdate();
			String delOrgLocatedAtLevels = "delete from OrgLocatedAtLevels where olc=:olc)";
			session.createQuery(delOrgLocatedAtLevels).setParameter("olc", maxOrgCode1).executeUpdate();
			String delOrganization = "delete from Organization where olc=:olc)";
			session.createQuery(delOrganization).setParameter("olc", maxOrgCode1).executeUpdate();
			tx.commit();
			return true;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			tx = null;
			return false;

		}
	}

	@Override
	public List<Organization> getOrganizationDetailbystateCode(Integer stateCode) throws Exception {
		return organizationDAO.getOrganizationDetailbystateCode(stateCode);
	}

	/**
	 * Code optimized by sushil on 24-06-2013
	 */
	@Override
	public String getExtendDepatmentInformation(Integer orgCode) throws Exception {
		StringBuilder tempDetails = new StringBuilder();
		List<Organization> orgList = null;
		Organization organization = null;
		List<OrgLocatedAtLevels> orgLocatedAtLevelsList = null;
		try {
			orgList = this.getOrganizationDetails(orgCode);

			if (orgList.size() > 0) {
				organization = (Organization) orgList.get(0);
				if (organization != null) {
					tempDetails = tempDetails.append(organization.getOrgName()).append("~").append(organization.getOrgNameLocal()).append("~").append(organization.getShortName()).append("~").append(organization.getLocalBodyTypeCode()).append("~");
				}
				orgLocatedAtLevelsList = this.getOrgLocatedbyOrgCode(orgCode);
				if (orgLocatedAtLevelsList != null) {
					for (OrgLocatedAtLevels element : orgLocatedAtLevelsList) {
						tempDetails = tempDetails.append(element.getLocatedAtLevel()).append("~");
					}
				}
			}
		} catch (Exception e) {
			tempDetails = null;
		} finally {
			orgList = null;
			organization = null;
			orgLocatedAtLevelsList = null;
		}
		return tempDetails.toString();
	}

	@Override
	public ExtendDepartment getExtendBasicDetails(Integer orgCode, char level) throws Exception {
		return organizationDAO.getExtendBasicDetails(orgCode, level);
	}

	@Override
	public List<ExtendDepartment> checkExtendDetail(Integer orgCode) throws Exception {
		return organizationDAO.checkExtendDetail(orgCode);
	}

	public List<District> getDistrictListforExtendDep(int stateCode, String dlc) throws Exception {
		return organizationDAO.getDistrictListforExtendDep(stateCode, dlc);
	}

	@Override
	public boolean extendDepartment(DepartmentForm departmentFormCentralL, List<DepartmentForm> listDepartmentFormStateL, List<DepartmentForm> listDepartmentFormDistrictL, DepartmentForm departmentFormSubDistrictL,
			DepartmentForm departmentFormBlockL, DepartmentForm departmentFormVillageL, int stateCode, Integer orgCode) throws Exception {
		Session session = null;
		Transaction tx = null;
		//Organization orgBean = null;
		OrganizationType organizationType = null;
		OrganizationPK organizationPK = null;
		//LocalBodyType localBodyType = null;
		//State state = null;
		//StatePK statepk = null;
		String[] tempEntityCode = null;
		ExtendDepartment extendDepartment = null;
		//int maxOrgCode = 0;
		int maxCoverageCode = 0;
		char coverageLevel = 0;
		int orgLocatedCode = 0;
		//int orgLocatedCodeCent = 0;
		int parentOrgLocatedCode = 0;
		//int maxOrgCode1 = 0;
		//Query criteria;
		int itr = 0;
		Map<Integer, String> parentMp = new HashMap<Integer, String>();
		OrgLocatedAtLevels orgLocated = null;
		//List<OrgLocatedAtLevels> orgLocatedList = null;
		try {
			organizationType = new OrganizationType();
			if (departmentFormCentralL.isOrganization()) {
				// orgBean.setParentorgcode(Integer.parseInt(departmentFormCentralL.getDeptOrgCode()));
				organizationType.setOrgTypeCode(Integer.parseInt(departmentFormCentralL.getOrgType()));
			} else {
				organizationType.setOrgTypeCode(2);
				/*
				 * if (departmentFormCentralL.getMinistryName() != null)
				 * orgBean.
				 * setParentorgcode(Integer.parseInt(departmentFormCentralL
				 * .getMinistryName()));
				 */
			}
			if (orgCode != null) {

				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				maxCoverageCode = organizationDAO.getMaxCoverageCode();
				if (departmentFormCentralL.getSpecificLevel() != null) {
					String tempLevel[] = departmentFormCentralL.getSpecificLevel().split(",");

					for (int i = 0; i < tempLevel.length; i++) {

						// ------------------------------ Extend Department for
						// District
						// ------------------------------------------------------------Extend
						// Department for
						// District--------------------------------------
						if (tempLevel[i].charAt(0) == 'D') { // for District
							boolean isNew = listDepartmentFormDistrictL.get(itr).isLevelNew();
							String coveragetype = listDepartmentFormDistrictL.get(itr).getLevelradio();
							if (coveragetype == null){
								coveragetype = "S";
							}	
							if (!isNew) {
								extendDepartment = this.getExtendBasicDetails(orgCode, 'D');

								/*
								 * orgLocated.setIsactive(false);
								 * orgLocated.setOrgLocatedLevelCode
								 * (extendDepartment.getOrgLocatedLevelCode());
								 */
								orgLocated = (OrgLocatedAtLevels) session.load(OrgLocatedAtLevels.class, extendDepartment.getOrgLocatedLevelCode());
								orgLocated.setOrguntDone(false);

								session.update(orgLocated);
								Integer coverageDetailCode = extendDepartment.getCoverageDetailCode();
								if (coveragetype.equalsIgnoreCase("F")) {
									String temp = "";
									temp = this.checkExtendDetailforDelete(orgCode, 'T');
									//List<Integer> entityCode = extendDepartment.getEntityCode();

									OrganizationCoverage orgCoverage = new OrganizationCoverage();
									orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, extendDepartment.getOrgCoverageId());
									orgCoverage.setOrguntDone(false);
									orgCoverage.setOrgCoverageEntityType('C');
									orgCoverage.setCoverageDetailCode(0);
									orgCoverage.setCoverage('F');
									// orgCoverage.setOrgCoverageId(extendDepartment.getOrgCoverageId());
									session.update(orgCoverage);

									if (temp.length() > 0 && coverageDetailCode != null) {

										this.deleteEntityinExtendDepatment(temp, session);

									}

								} else {
									OrganizationCoverage orgCoverage = new OrganizationCoverage();
									orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, extendDepartment.getOrgCoverageId());
									orgCoverage.setOrguntDone(false);
									/*
									 * orgCoverage.setOrgCoverageId(extendDepartment
									 * .getOrgCoverageId());
									 */
									session.update(orgCoverage);

									tempEntityCode = null;
									tempEntityCode = listDepartmentFormDistrictL.get(itr).getDistrictName().split(",");

									for (int k = 0; k < tempEntityCode.length; k++) {
										this.extendOrgCoverageDetail(coverageDetailCode, Integer.parseInt(tempEntityCode[k]), session);

									}
								}

							} else {
								orgLocated = new OrgLocatedAtLevels();
								extendDepartment = this.getExtendBasicDetails(orgCode, 'S');
								Integer orgVersion = Integer.parseInt(session.createQuery("select orgVersion from Organization where isactive=true and orgCode=" + orgCode).list().get(0).toString());
								organizationPK = new OrganizationPK();
								organizationPK.setOrgCode(orgCode);
								organizationPK.setOrgVersion(orgVersion);
								Organization OrgLoad = new Organization();
								OrgLoad = (Organization) session.load(Organization.class, organizationPK);
								OrgLoad.setOrganizationType(organizationType);
								// session.clear();
								if (departmentFormCentralL.isAUM())
									parentMp.put(extendDepartment.getOrgLocatedLevelCode(), departmentFormCentralL.getDeptName());
								else
									parentMp.put(extendDepartment.getOrgLocatedLevelCode(), departmentFormCentralL.getMinistryName());

								parentOrgLocatedCode = this.getMapKey(parentMp, listDepartmentFormDistrictL.get(itr).getParent().trim());
								orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
								orgLocated.setOrgLevelSpecificName(listDepartmentFormDistrictL.get(itr).getDeptName().trim());
								orgLocated.setOrgLevelSpecificNameLocal(listDepartmentFormDistrictL.get(itr).getDeptNameLocal());
								orgLocated.setOlc(orgCode);
								orgLocated.setOrganization(OrgLoad);
								orgLocated.setLocatedAtLevel(tempLevel[i].charAt(0));
								orgLocated.setOrgLevelSpecificShortName(listDepartmentFormDistrictL.get(itr).getShortDeptName());
								orgLocatedCode = organizationDAO.saveOrgLocatedAtLevel(orgLocated, session);

								parentMp.put(orgLocatedCode, listDepartmentFormDistrictL.get(itr).getDeptName().trim());

								coverageLevel = listDepartmentFormDistrictL.get(itr).getLevelradio().charAt(0); // Full
																												// or
																												// Specific
								if (coverageLevel == 'S' && !departmentFormCentralL.isAUM()) {// If
																								// Specific
									if (listDepartmentFormDistrictL.get(itr).getSpecificLevel().equals("Full")) {
										tempEntityCode = null;
										tempEntityCode = listDepartmentFormDistrictL.get(itr).getStateName().split(",");

										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
										maxCoverageCode++;
									}

									if (listDepartmentFormDistrictL.get(itr).getSpecificLevel().equals("Part")) {
										tempEntityCode = null;
										tempEntityCode = listDepartmentFormDistrictL.get(itr).getDistrictName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
										maxCoverageCode++;
									}

									if (listDepartmentFormDistrictL.get(itr).getSpecificLevel().equals("Full,Part")) {
										tempEntityCode = null;
										tempEntityCode = listDepartmentFormDistrictL.get(itr).getStateName().split(",");

										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode, session);
										maxCoverageCode++;

										tempEntityCode = null;
										tempEntityCode = listDepartmentFormDistrictL.get(itr).getDistrictName().split(",");

										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
										maxCoverageCode++;
									}
								} else if (coverageLevel == 'S' && departmentFormCentralL.isAUM()) {
									tempEntityCode = null;
									tempEntityCode = listDepartmentFormDistrictL.get(itr).getDistrictName().split(",");
									for (int k = 0; k < tempEntityCode.length; k++) {
										this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
									}
									this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
									maxCoverageCode++;
								} else { // for Full
									this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session);
								}

								if (listDepartmentFormDistrictL.size() > 1){
									itr++;
								}	
								if (itr == listDepartmentFormDistrictL.size()){
									itr = 0;
								}	
							}
						}

						// ------------------------------ END Extend Department
						// for District
						// ------------------------------------------------------------END
						// Extend Department for
						// District--------------------------------------

						// ------------------------------ Extend Department for
						// SubDistrict
						// ------------------------------------------------------------Extend
						// Department for
						// SubDistrict--------------------------------------
						else if (tempLevel[i].charAt(0) == 'T') { // for
																	// District
							boolean isNew = departmentFormSubDistrictL.isLevelNew();
							String coveragetype = departmentFormSubDistrictL.getLevelradio();
							if (coveragetype == null){
								coveragetype = "S";
							}	
							if (!isNew) {
								extendDepartment = this.getExtendBasicDetails(orgCode, 'T');

								/*
								 * orgLocated.setIsactive(false);
								 * orgLocated.setOrgLocatedLevelCode
								 * (extendDepartment.getOrgLocatedLevelCode());
								 */
								orgLocated = (OrgLocatedAtLevels) session.load(OrgLocatedAtLevels.class, extendDepartment.getOrgLocatedLevelCode());
								orgLocated.setOrguntDone(false);

								session.update(orgLocated);
								Integer coverageDetailCode = extendDepartment.getCoverageDetailCode();
								if (coveragetype.equalsIgnoreCase("F")) {
									String temp = "";
									temp = this.checkExtendDetailforDelete(orgCode, 'T');

									/*
									 * orgCoverage = (OrganizationCoverage)
									 * session.load( OrganizationCoverage.class,
									 * extendDepartment.getOrgCoverageId());
									 */

									if (temp.length() > 0 && coverageDetailCode != null) {
										// temp=temp.substring(0,temp.lastIndexOf(","));
										this.deleteEntityinExtendDepatment(temp, session);
										this.deleteCoverageExtendDepatment(extendDepartment.getOrgLocatedLevelCode(), session);

									}

									OrganizationCoverage orgCoverage = new OrganizationCoverage();
									orgCoverage.setIsactive(true);
									orgCoverage.setOrgLocatedLevelCode(extendDepartment.getOrgLocatedLevelCode());
									orgCoverage.setOrguntDone(false);
									orgCoverage.setOrgCoverageEntityType('C');
									orgCoverage.setCoverageDetailCode(0);
									orgCoverage.setCoverage('F');
									// orgCoverage.setOrgCoverageId(extendDepartment.getOrgCoverageId());
									session.save(orgCoverage);

								} else {
									Integer dcoverageid = null, tcoverageid = null;
									Integer dcoverageCode = null, tcoverageCode = null;
									List<ExtendDepartment> extendDepartmentList = null;
									extendDepartmentList = this.checkExtendDetail(orgCode);
									for (ExtendDepartment element : extendDepartmentList) {
										if (element.getCoverage() == 'D' && element.getLocatedAtLevel() == 'T') {
											dcoverageid = element.getOrgCoverageId();
											dcoverageCode = element.getCoverageDetailCode();
										} else if (element.getCoverage() == 'T' && element.getLocatedAtLevel() == 'T') {
											tcoverageid = element.getOrgCoverageId();
											tcoverageCode = element.getCoverageDetailCode();
										}
									}

									if (departmentFormSubDistrictL.getDistrictName() != null && departmentFormSubDistrictL.getSubdistrictName() == null) {

										if (dcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, dcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(dcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'D', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;

										}

									}

									if (departmentFormSubDistrictL.getDistrictName() == null && departmentFormSubDistrictL.getSubdistrictName() != null) {
										if (tcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, tcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(tcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'T', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;
										}

									}

									if (departmentFormSubDistrictL.getDistrictName() != null && departmentFormSubDistrictL.getSubdistrictName() != null) {

										if (dcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, dcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(dcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'D', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;

										}

										if (tcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, tcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(tcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'T', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;
										}

									}
								}

							} else {
								orgLocated = new OrgLocatedAtLevels();
								extendDepartment = this.getExtendBasicDetails(orgCode, 'S');

								Integer orgVersion = Integer.parseInt(session.createQuery("select orgVersion from Organization where isactive=true and orgCode=" + orgCode).list().get(0).toString());
								organizationPK = new OrganizationPK();
								organizationPK.setOrgCode(orgCode);
								organizationPK.setOrgVersion(orgVersion);
								Organization OrgLoad = new Organization();
								OrgLoad = (Organization) session.load(Organization.class, organizationPK);
								OrgLoad.setOrganizationType(organizationType);
								// session.clear();
								if (departmentFormCentralL.isAUM())
									parentMp.put(extendDepartment.getOrgLocatedLevelCode(), departmentFormCentralL.getDeptName());
								else
									parentMp.put(extendDepartment.getOrgLocatedLevelCode(), departmentFormCentralL.getMinistryName());

								parentOrgLocatedCode = this.getMapKey(parentMp, departmentFormSubDistrictL.getParent());
								orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
								orgLocated.setOrgLevelSpecificName(departmentFormSubDistrictL.getDeptName().trim());
								orgLocated.setOrgLevelSpecificNameLocal(departmentFormSubDistrictL.getDeptNameLocal());
								orgLocated.setOlc(orgCode);
								orgLocated.setOrganization(OrgLoad);
								orgLocated.setLocatedAtLevel(tempLevel[i].charAt(0));
								orgLocated.setOrgLevelSpecificShortName(departmentFormSubDistrictL.getShortDeptName());
								orgLocatedCode = organizationDAO.saveOrgLocatedAtLevel(orgLocated, session);

								parentMp.put(orgLocatedCode, departmentFormSubDistrictL.getDeptName().trim());

								coverageLevel = departmentFormSubDistrictL.getLevelradio().charAt(0); // Full
																										// or
																										// Specific
								if (coverageLevel == 'S') {// If
															// Specific

									if (departmentFormSubDistrictL.getDistrictName() != null && departmentFormSubDistrictL.getSubdistrictName() == null) {
										tempEntityCode = null;
										tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
										maxCoverageCode++;

									}

									if (departmentFormSubDistrictL.getDistrictName() == null && departmentFormSubDistrictL.getSubdistrictName() != null) {

										tempEntityCode = null;
										tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
										maxCoverageCode++;

									}

									if (departmentFormSubDistrictL.getDistrictName() != null && departmentFormSubDistrictL.getSubdistrictName() != null) {

										tempEntityCode = null;
										tempEntityCode = departmentFormSubDistrictL.getDistrictName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
										maxCoverageCode++;

										tempEntityCode = null;
										tempEntityCode = departmentFormSubDistrictL.getSubdistrictName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
										maxCoverageCode++;

									}

								} else { // for Full
									this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session);
								}
							}

						}
						// ------------------------------ END Extend Department
						// for SubDistrict
						// ------------------------------------------------------------END
						// Extend Department for
						// SubDistrict--------------------------------------

						// ------------------------------ Extend Department for
						// Block
						// ------------------------------------------------------------END
						// Extend Department for
						// Block--------------------------------------

						else

						if (tempLevel[i].charAt(0) == 'B') { // for District
							boolean isNew = departmentFormBlockL.isLevelNew();
							String coveragetype = departmentFormBlockL.getLevelradio();
							if (coveragetype == null){
								coveragetype = "S";
							}	
							if (!isNew) {
								extendDepartment = this.getExtendBasicDetails(orgCode, 'B');

								/*
								 * orgLocated.setIsactive(false);
								 * orgLocated.setOrgLocatedLevelCode
								 * (extendDepartment.getOrgLocatedLevelCode());
								 */
								orgLocated = (OrgLocatedAtLevels) session.load(OrgLocatedAtLevels.class, extendDepartment.getOrgLocatedLevelCode());
								orgLocated.setOrguntDone(false);

								session.update(orgLocated);
								Integer coverageDetailCode = extendDepartment.getCoverageDetailCode();
								if (coveragetype.equalsIgnoreCase("F")) {
									String temp = "";
									temp = this.checkExtendDetailforDelete(orgCode, 'B');

									/*
									 * orgCoverage = (OrganizationCoverage)
									 * session.load( OrganizationCoverage.class,
									 * extendDepartment.getOrgCoverageId());
									 */

									if (temp.length() > 0 && coverageDetailCode != null) {
										// temp=temp.substring(0,temp.lastIndexOf(","));
										this.deleteEntityinExtendDepatment(temp, session);
										this.deleteCoverageExtendDepatment(extendDepartment.getOrgLocatedLevelCode(), session);

									}

									OrganizationCoverage orgCoverage = new OrganizationCoverage();
									orgCoverage.setIsactive(true);
									orgCoverage.setOrgLocatedLevelCode(extendDepartment.getOrgLocatedLevelCode());
									orgCoverage.setOrguntDone(false);
									orgCoverage.setOrgCoverageEntityType('C');
									orgCoverage.setCoverageDetailCode(0);
									orgCoverage.setCoverage('F');
									// orgCoverage.setOrgCoverageId(extendDepartment.getOrgCoverageId());
									session.save(orgCoverage);

								} else {
									Integer dcoverageid = null, bcoverageid = null;
									Integer dcoverageCode = null, bcoverageCode = null;
									List<ExtendDepartment> extendDepartmentList = null;
									extendDepartmentList = this.checkExtendDetail(orgCode);
									for (ExtendDepartment element : extendDepartmentList) {
										if (element.getCoverage() == 'D' && element.getLocatedAtLevel() == 'B') {
											dcoverageid = element.getOrgCoverageId();
											dcoverageCode = element.getCoverageDetailCode();
										} else if (element.getCoverage() == 'B' && element.getLocatedAtLevel() == 'B') {
											bcoverageid = element.getOrgCoverageId();
											bcoverageCode = element.getCoverageDetailCode();
										}
									}

									if (departmentFormBlockL.getDistrictName() != null && departmentFormBlockL.getBlockName() == null) {

										if (dcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, dcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(dcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'D', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;

										}

									}

									if (departmentFormBlockL.getDistrictName() == null && departmentFormBlockL.getBlockName() != null) {
										if (bcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, bcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormBlockL.getBlockName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(bcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormBlockL.getBlockName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'B', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;
										}

									}

									if (departmentFormBlockL.getDistrictName() != null && departmentFormBlockL.getBlockName() != null) {

										if (dcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, dcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(dcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'D', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;

										}

										if (bcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, bcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormBlockL.getBlockName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(bcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormBlockL.getBlockName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'B', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;
										}

									}
								}

							} else {
								orgLocated = new OrgLocatedAtLevels();
								extendDepartment = this.getExtendBasicDetails(orgCode, 'S');

								Integer orgVersion = Integer.parseInt(session.createQuery("select orgVersion from Organization where isactive=true and orgCode=" + orgCode).list().get(0).toString());
								organizationPK = new OrganizationPK();
								organizationPK.setOrgCode(orgCode);
								organizationPK.setOrgVersion(orgVersion);
								Organization OrgLoad = new Organization();
								OrgLoad = (Organization) session.load(Organization.class, organizationPK);
								OrgLoad.setOrganizationType(organizationType);
								// session.clear();
								if (departmentFormCentralL.isAUM())
									parentMp.put(extendDepartment.getOrgLocatedLevelCode(), departmentFormCentralL.getDeptName());
								else
									parentMp.put(extendDepartment.getOrgLocatedLevelCode(), departmentFormCentralL.getMinistryName());

								parentOrgLocatedCode = this.getMapKey(parentMp, departmentFormBlockL.getParent());
								orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
								orgLocated.setOrgLevelSpecificName(departmentFormBlockL.getDeptName().trim());
								orgLocated.setOrgLevelSpecificNameLocal(departmentFormBlockL.getDeptNameLocal());
								orgLocated.setOlc(orgCode);
								orgLocated.setOrganization(OrgLoad);
								orgLocated.setLocatedAtLevel(tempLevel[i].charAt(0));
								orgLocated.setOrgLevelSpecificShortName(departmentFormBlockL.getShortDeptName());
								orgLocatedCode = organizationDAO.saveOrgLocatedAtLevel(orgLocated, session);

								parentMp.put(orgLocatedCode, departmentFormBlockL.getDeptName().trim());

								coverageLevel = departmentFormBlockL.getLevelradio().charAt(0); // Full
																								// or
																								// Specific
								if (coverageLevel == 'S') {// If
															// Specific

									if (departmentFormBlockL.getDistrictName() != null && departmentFormBlockL.getBlockName() == null) {
										tempEntityCode = null;
										tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
										maxCoverageCode++;

									}

									if (departmentFormBlockL.getDistrictName() == null && departmentFormBlockL.getBlockName() != null) {

										tempEntityCode = null;
										tempEntityCode = departmentFormBlockL.getBlockName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'B', orgLocatedCode, session);
										maxCoverageCode++;

									}

									if (departmentFormBlockL.getDistrictName() != null && departmentFormBlockL.getBlockName() != null) {

										tempEntityCode = null;
										tempEntityCode = departmentFormBlockL.getDistrictName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
										maxCoverageCode++;

										tempEntityCode = null;
										tempEntityCode = departmentFormBlockL.getBlockName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'B', orgLocatedCode, session);
										maxCoverageCode++;

									}

								} else { // for Full
									this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session);
								}
							}

						}

						// ------------------------------End Extend Department
						// for Block
						// ------------------------------------------------------------END
						// Extend Department for
						// Block--------------------------------------

						// ------------------------------Extend Department for
						// Village
						// ------------------------------------------------------------
						// Extend Department for
						// Village--------------------------------------

						if (tempLevel[i].charAt(0) == 'V') { // for District
							boolean isNew = departmentFormVillageL.isLevelNew();
							String coveragetype = departmentFormVillageL.getLevelradio();
							if (coveragetype == null){
								coveragetype = "S";
							}	
							if (!isNew) {
								extendDepartment = this.getExtendBasicDetails(orgCode, 'V');

								/*
								 * orgLocated.setIsactive(false);
								 * orgLocated.setOrgLocatedLevelCode
								 * (extendDepartment.getOrgLocatedLevelCode());
								 */
								orgLocated = (OrgLocatedAtLevels) session.load(OrgLocatedAtLevels.class, extendDepartment.getOrgLocatedLevelCode());
								orgLocated.setOrguntDone(false);

								session.update(orgLocated);
								Integer coverageDetailCode = extendDepartment.getCoverageDetailCode();
								if (coveragetype.equalsIgnoreCase("F")) {
									String temp = "";
									temp = this.checkExtendDetailforDelete(orgCode, 'V');

									/*
									 * orgCoverage = (OrganizationCoverage)
									 * session.load( OrganizationCoverage.class,
									 * extendDepartment.getOrgCoverageId());
									 */

									if (temp.length() > 0 && coverageDetailCode != null) {
										// temp=temp.substring(0,temp.lastIndexOf(","));
										this.deleteEntityinExtendDepatment(temp, session);
										this.deleteCoverageExtendDepatment(extendDepartment.getOrgLocatedLevelCode(), session);

									}

									OrganizationCoverage orgCoverage = new OrganizationCoverage();
									orgCoverage.setIsactive(true);
									orgCoverage.setOrgLocatedLevelCode(extendDepartment.getOrgLocatedLevelCode());
									orgCoverage.setOrguntDone(false);
									orgCoverage.setOrgCoverageEntityType('C');
									orgCoverage.setCoverageDetailCode(0);
									orgCoverage.setCoverage('F');
									// orgCoverage.setOrgCoverageId(extendDepartment.getOrgCoverageId());
									session.save(orgCoverage);

								} else {
									Integer dcoverageid = null, tcoverageid = null;//, vcoverageid = null;
									Integer dcoverageCode = null, tcoverageCode = null;//, vcoverageCode = null;
									List<ExtendDepartment> extendDepartmentList = null;
									extendDepartmentList = this.checkExtendDetail(orgCode);
									for (ExtendDepartment element : extendDepartmentList) {
										if (element.getCoverage() == 'D' && element.getLocatedAtLevel() == 'V') {
											dcoverageid = element.getOrgCoverageId();
											dcoverageCode = element.getCoverageDetailCode();
										} else if (element.getCoverage() == 'T' && element.getLocatedAtLevel() == 'V') {
											tcoverageid = element.getOrgCoverageId();
											tcoverageCode = element.getCoverageDetailCode();
										} /*else if (element.getCoverage() == 'V' && element.getLocatedAtLevel() == 'V') {
											//vcoverageid = element.getOrgCoverageId();
											//vcoverageCode = element.getCoverageDetailCode();
										}*/
									}

									if (departmentFormVillageL.getDistrictName() != null) {

										if (dcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, dcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(dcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'D', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;

										}

									}

									if (departmentFormVillageL.getSubdistrictName() != null) {
										if (tcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, tcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(tcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'T', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;
										}

									}

									if (departmentFormVillageL.getVillageName() != null) {

										if (dcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, dcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormVillageL.getVillageName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(dcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormVillageL.getVillageName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'V', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;

										}

										if (tcoverageid != null) {
											OrganizationCoverage orgCoverage = new OrganizationCoverage();
											orgCoverage = (OrganizationCoverage) session.load(OrganizationCoverage.class, tcoverageid);
											orgCoverage.setOrguntDone(false);
											session.update(orgCoverage);

											tempEntityCode = null;
											tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(tcoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}

										} else {
											tempEntityCode = null;
											tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
											for (int k = 0; k < tempEntityCode.length; k++) {
												this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
											}
											this.saveOrgCoverage('S', maxCoverageCode, 'T', extendDepartment.getOrgLocatedLevelCode(), session);
											maxCoverageCode++;
										}

									}
								}

							} else {
								orgLocated = new OrgLocatedAtLevels();
								extendDepartment = this.getExtendBasicDetails(orgCode, 'S');

								Integer orgVersion = Integer.parseInt(session.createQuery("select orgVersion from Organization where isactive=true and orgCode=" + orgCode).list().get(0).toString());
								organizationPK = new OrganizationPK();
								organizationPK.setOrgCode(orgCode);
								organizationPK.setOrgVersion(orgVersion);
								Organization OrgLoad = new Organization();
								OrgLoad = (Organization) session.load(Organization.class, organizationPK);
								OrgLoad.setOrganizationType(organizationType);
								// session.clear();
								if (departmentFormCentralL.isAUM())
									parentMp.put(extendDepartment.getOrgLocatedLevelCode(), departmentFormCentralL.getDeptName());
								else
									parentMp.put(extendDepartment.getOrgLocatedLevelCode(), departmentFormCentralL.getMinistryName());

								parentOrgLocatedCode = this.getMapKey(parentMp, departmentFormVillageL.getParent());
								orgLocated.setParentOrgLocatedLevelCode(parentOrgLocatedCode);
								orgLocated.setOrgLevelSpecificName(departmentFormVillageL.getDeptName().trim());
								orgLocated.setOrgLevelSpecificNameLocal(departmentFormVillageL.getDeptNameLocal());
								orgLocated.setOlc(orgCode);
								orgLocated.setOrganization(OrgLoad);
								orgLocated.setLocatedAtLevel(tempLevel[i].charAt(0));
								orgLocated.setOrgLevelSpecificShortName(departmentFormVillageL.getShortDeptName());
								orgLocatedCode = organizationDAO.saveOrgLocatedAtLevel(orgLocated, session);

								parentMp.put(orgLocatedCode, departmentFormVillageL.getDeptName().trim());

								coverageLevel = departmentFormVillageL.getLevelradio().charAt(0); // Full
																									// or
																									// Specific
								if (coverageLevel == 'S') {// If
															// Specific

									if (departmentFormVillageL.getDistrictName() != null) {
										tempEntityCode = null;
										tempEntityCode = departmentFormVillageL.getDistrictName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode, session);
										maxCoverageCode++;

									}

									if (departmentFormVillageL.getSubdistrictName() != null) {

										tempEntityCode = null;
										tempEntityCode = departmentFormVillageL.getSubdistrictName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode, session);
										maxCoverageCode++;

									}

									if (departmentFormVillageL.getVillageName() != null) {

										tempEntityCode = null;
										tempEntityCode = departmentFormVillageL.getVillageName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode, session);
										maxCoverageCode++;

										tempEntityCode = null;
										tempEntityCode = departmentFormVillageL.getVillageName().split(",");
										for (int k = 0; k < tempEntityCode.length; k++) {
											this.saveOrgCoverageDetail(maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session);
										}
										this.saveOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode, session);
										maxCoverageCode++;

									}

								} else { // for Full
									this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session);
								}
							}

						}
						// ------------------------------End Extend Department
						// for Village
						// ------------------------------------------------------------END
						// Extend Department for
						// Village--------------------------------------

					}

				}

			}

			/*
			 * maxOrgCode1 = organizationDAO.getMaxOrgCode(); maxCoverageCode =
			 * organizationDAO.getMaxCoverageCode(); session =
			 * sessionFactory.openSession(); tx = session.beginTransaction();
			 * Integer slc; state = new State(stateCode); statepk = new
			 * StatePK(stateCode, stateVersion); state.setStatePK(statepk);
			 * state.setSlc(stateCode); organizationType = new
			 * OrganizationType(); orgBean = new Organization(maxOrgCode1);
			 * //maxOrgCode1=departmentFormCentralL.getOrgCode(); organizationPK
			 * = new OrganizationPK(maxOrgCode1, 1); if
			 * (departmentFormCentralL.isOrganization()) {
			 * orgBean.setParentorgcode(Integer
			 * .parseInt(departmentFormCentralL.getDeptOrgCode()));
			 * organizationType.setOrgTypeCode(Integer
			 * .parseInt(departmentFormCentralL.getOrgType())); } else {
			 * organizationType.setOrgTypeCode(2); if
			 * (departmentFormCentralL.getMinistryName() != null)
			 * orgBean.setParentorgcode(Integer
			 * .parseInt(departmentFormCentralL.getMinistryName())); } if
			 * (departmentFormCentralL.getIsLocalBodyTypeSpecifc() == 'Y') {
			 * localBodyType = new LocalBodyType();
			 * localBodyType.setLocalBodyTypeCode(departmentFormCentralL
			 * .getLocalBodyTypeCode());
			 * orgBean.setLocalBodyType(localBodyType); if
			 * (departmentFormCentralL.getIsLocalBodySpecifc() == 'Y') {
			 * orgBean.setLocalBodyCode(departmentFormCentralL
			 * .getLocalBodyCode()); } }
			 * orgBean.setOrganizationPK(organizationPK);
			 * orgBean.setOrgName(departmentFormCentralL.getDeptName());
			 * orgBean.
			 * setOrgNameLocal(departmentFormCentralL.getDeptNameLocal());
			 * orgBean.setShortName(departmentFormCentralL.getShortDeptName());
			 * //orgBean.setSlc(stateCode); orgBean.setIsactive(true);
			 * 
			 * if (departmentFormCentralL.isAUM()) { orgBean.setOrgLevel('S');
			 * orgBean.setSlc(stateCode); } else orgBean.setOrgLevel('C');
			 * orgBean.setOrganizationType(organizationType);
			 * orgBean.setIsactive(true); orgBean.setOlc(maxOrgCode1);
			 * orgBean.setOrgCode(maxOrgCode1); //orgBean.setSlc(0);
			 * organizationDAO.saveOrganization(orgBean, session);
			 * 
			 * // Organisation Located At Code OrgLocatedAtLevels orgLocated =
			 * null; orgLocated = new OrgLocatedAtLevels();
			 * orgBean.setOrganizationPK(organizationPK);
			 * //orgBean.setSlc(stateCode); if (departmentFormCentralL.isAUM())
			 * orgLocated.setLocatedAtLevel('S'); else
			 * orgLocated.setLocatedAtLevel('C'); if
			 * (departmentFormCentralL.isOrganization()) { if
			 * (departmentFormCentralL.isAUM()) { } else
			 * orgLocated.setParentOrgLocatedLevelCode(Integer
			 * .parseInt(departmentFormCentralL.getDeptOrgCode())); } else { if
			 * (departmentFormCentralL.getMinistryName() != null) orgLocated
			 * .setParentOrgLocatedLevelCode(Integer
			 * .parseInt(departmentFormCentralL .getMinistryName())); }
			 * orgLocated.setOlc(maxOrgCode1);
			 * orgLocated.setOrganization(orgBean);
			 * orgLocated.setIsactive(true); orgLocatedCodeCent =
			 * organizationDAO.saveOrgLocatedAtLevel( orgLocated, session); if
			 * (departmentFormCentralL.isAUM()) parentMp.put(orgLocatedCodeCent,
			 * departmentFormCentralL.getDeptName()); else
			 * parentMp.put(orgLocatedCodeCent,
			 * departmentFormCentralL.getMinistryName());
			 * 
			 * if (departmentFormCentralL.getSpecificLevel() != null) { String
			 * tempLevel[] = departmentFormCentralL.getSpecificLevel()
			 * .split(","); orgLocated = null; for (int i = 0; i <
			 * tempLevel.length; i++) { orgLocated = new OrgLocatedAtLevels();
			 * orgLocated.setLocatedAtLevel(tempLevel[i].charAt(0));
			 * orgLocated.setOrganization(orgBean);
			 * orgLocated.setIsactive(true); orgLocated.setOlc(maxOrgCode1);
			 * orgLocatedCode = organizationDAO.saveOrgLocatedAtLevel(
			 * orgLocated, session);
			 * 
			 * if (tempLevel[i].charAt(0) == 'S') { // for State coverageLevel =
			 * listDepartmentFormStateL.get(itr) .getLevelradio().charAt(0); //
			 * Full or Specific if (coverageLevel == 'S') { // If Specific
			 * maxCoverageCode = organizationDAO .getMaxCoverageCode();
			 * tempEntityCode = null; tempEntityCode =
			 * listDepartmentFormStateL.get(itr) .getStateName().split(","); for
			 * (int k = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail(maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++; }
			 * 
			 * else { // for Full this.saveOrgCoverage('F', 0, 'C',
			 * orgLocatedCode, session); } orgLocated
			 * .setParentOrgLocatedLevelCode(orgLocatedCodeCent); orgLocated
			 * .setOrgLevelSpecificName(listDepartmentFormStateL
			 * .get(itr).getDeptName().trim()); orgLocated
			 * .setOrgLevelSpecificNameLocal(listDepartmentFormStateL
			 * .get(itr).getDeptNameLocal()); orgLocated
			 * .setOrgLevelSpecificShortName(listDepartmentFormStateL
			 * .get(itr).getShortDeptName()); session.update(orgLocated);
			 * parentMp.put(orgLocatedCode, listDepartmentFormStateL
			 * .get(itr).getDeptName().trim());
			 * 
			 * if (listDepartmentFormStateL.size() > 1) itr++; if (itr ==
			 * listDepartmentFormStateL.size()) itr = 0; }
			 * 
			 * 
			 * if (tempLevel[i].charAt(0) == 'T') { // for Sub District
			 * coverageLevel = departmentFormSubDistrictL
			 * .getLevelradio().charAt(0); // Full or Specific if (coverageLevel
			 * == 'S') {// If Specific tempEntityCode = null; if
			 * (departmentFormSubDistrictL.getSpecificLevel() .equals("SFull"))
			 * { tempEntityCode = null; tempEntityCode =
			 * departmentFormSubDistrictL .getStateName().split(","); for (int k
			 * = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormSubDistrictL.getSpecificLevel() .equals("DFull"))
			 * { tempEntityCode = null; tempEntityCode =
			 * departmentFormSubDistrictL .getDistrictName().split(","); for
			 * (int k = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormSubDistrictL.getSpecificLevel() .equals("DPart"))
			 * { tempEntityCode = null; tempEntityCode =
			 * departmentFormSubDistrictL .getSubdistrictName().split(","); for
			 * (int k = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormSubDistrictL.getSpecificLevel()
			 * .equals("SFull,DFull")) { tempEntityCode = null; tempEntityCode =
			 * departmentFormSubDistrictL .getStateName().split(","); for (int k
			 * = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode =
			 * departmentFormSubDistrictL .getDistrictName().split(","); for
			 * (int k = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormSubDistrictL.getSpecificLevel()
			 * .equals("DFull,DPart")) { tempEntityCode = null; tempEntityCode =
			 * departmentFormSubDistrictL .getDistrictName().split(","); for
			 * (int k = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode =
			 * departmentFormSubDistrictL .getSubdistrictName().split(","); for
			 * (int k = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormSubDistrictL.getSpecificLevel()
			 * .equals("SFull,DFull,DPart")) { tempEntityCode = null;
			 * tempEntityCode = departmentFormSubDistrictL
			 * .getStateName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode =
			 * departmentFormSubDistrictL .getDistrictName().split(","); for
			 * (int k = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode =
			 * departmentFormSubDistrictL .getSubdistrictName().split(","); for
			 * (int k = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode,
			 * session); maxCoverageCode++; } } else { // for Full
			 * this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session); }
			 * parentOrgLocatedCode = this.getMapKey(parentMp,
			 * departmentFormSubDistrictL.getParent().trim()); orgLocated
			 * .setParentOrgLocatedLevelCode(parentOrgLocatedCode); orgLocated
			 * .setOrgLevelSpecificName(departmentFormSubDistrictL
			 * .getDeptName().trim()); orgLocated
			 * .setOrgLevelSpecificNameLocal(departmentFormSubDistrictL
			 * .getDeptNameLocal()); orgLocated
			 * .setOrgLevelSpecificShortName(departmentFormSubDistrictL
			 * .getShortDeptName()); session.update(orgLocated);
			 * parentMp.put(orgLocatedCode, departmentFormSubDistrictL
			 * .getDeptName().trim()); }
			 * 
			 * if (tempLevel[i].charAt(0) == 'B') { // for Block coverageLevel =
			 * departmentFormBlockL.getLevelradio() .charAt(0); // Full or
			 * Specific if (coverageLevel == 'S') {// If Specific tempEntityCode
			 * = null; if (departmentFormBlockL.getSpecificLevel().equals(
			 * "SFull")) { tempEntityCode = null; tempEntityCode =
			 * departmentFormBlockL .getStateName().split(","); for (int k = 0;
			 * k < tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormBlockL.getSpecificLevel().equals( "DFull")) {
			 * tempEntityCode = null; tempEntityCode = departmentFormBlockL
			 * .getDistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormBlockL.getSpecificLevel().equals( "DPart")) {
			 * tempEntityCode = null; tempEntityCode = departmentFormBlockL
			 * .getBlockName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'B', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormBlockL.getSpecificLevel().equals( "SFull,DFull"))
			 * { tempEntityCode = null; tempEntityCode = departmentFormBlockL
			 * .getStateName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormBlockL
			 * .getDistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++; }
			 * 
			 * if(departmentFormBlockL!=null) { if
			 * (departmentFormBlockL.getSpecificLevel() .equals("DFull,DPart"))
			 * { tempEntityCode = null; tempEntityCode = departmentFormBlockL
			 * .getDistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormBlockL
			 * .getBlockName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'B', orgLocatedCode,
			 * session); maxCoverageCode++; } } if
			 * (departmentFormBlockL.getSpecificLevel().equals(
			 * "SFull,DFull,DPart")) { tempEntityCode = null; tempEntityCode =
			 * departmentFormBlockL .getStateName().split(","); for (int k = 0;
			 * k < tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormBlockL
			 * .getDistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormBlockL
			 * .getBlockName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'B', orgLocatedCode,
			 * session); maxCoverageCode++; } }
			 * 
			 * else { // for Full this.saveOrgCoverage('F', 0, 'C',
			 * orgLocatedCode, session); } parentOrgLocatedCode =
			 * this.getMapKey(parentMp,
			 * departmentFormBlockL.getParent().trim()); orgLocated
			 * .setParentOrgLocatedLevelCode(parentOrgLocatedCode);
			 * orgLocated.setOrgLevelSpecificName(departmentFormBlockL
			 * .getDeptName().trim()); orgLocated
			 * .setOrgLevelSpecificNameLocal(departmentFormBlockL
			 * .getDeptNameLocal()); orgLocated
			 * .setOrgLevelSpecificShortName(departmentFormBlockL
			 * .getShortDeptName()); session.update(orgLocated);
			 * parentMp.put(orgLocatedCode, departmentFormBlockL
			 * .getDeptName().trim()); }
			 * 
			 * if (tempLevel[i].charAt(0) == 'V') { // for Village coverageLevel
			 * = departmentFormVillageL.getLevelradio() .charAt(0); // Full or
			 * Specific if (coverageLevel == 'S') {// If Specific tempEntityCode
			 * = null; if (departmentFormVillageL.getSpecificLevel()
			 * .equals("SFull")) { tempEntityCode = null; tempEntityCode =
			 * departmentFormVillageL .getStateName().split(","); for (int k =
			 * 0; k < tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormVillageL.getSpecificLevel() .equals("DFull")) {
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getDistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormVillageL.getSpecificLevel() .equals("SDFull")) {
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getSubdistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormVillageL.getSpecificLevel() .equals("SDPart")) {
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getVillageName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormVillageL.getSpecificLevel()
			 * .equals("SFull,DFull")) { tempEntityCode = null; tempEntityCode =
			 * departmentFormVillageL .getStateName().split(","); for (int k =
			 * 0; k < tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getDistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormVillageL.getSpecificLevel()
			 * .equals("SFull,DFull,SDFull")) { tempEntityCode = null;
			 * tempEntityCode = departmentFormVillageL
			 * .getStateName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getDistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getSubdistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormVillageL.getSpecificLevel()
			 * .equals("DFull,SDFull")) { tempEntityCode = null; tempEntityCode
			 * = departmentFormVillageL .getDistrictName().split(","); for (int
			 * k = 0; k < tempEntityCode.length; k++) {
			 * this.saveOrgCoverageDetail( maxCoverageCode,
			 * Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getSubdistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * } if (departmentFormVillageL.getSpecificLevel()
			 * .equals("DFull,SDFull,SDPart")) {
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getDistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getSubdistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getVillageName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode,
			 * session); maxCoverageCode++; } if
			 * (departmentFormVillageL.getSpecificLevel()
			 * .equals("SFull,DFull,SDFull,SDPart")) { tempEntityCode = null;
			 * tempEntityCode = departmentFormVillageL
			 * .getStateName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'S', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getDistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'D', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getSubdistrictName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'T', orgLocatedCode,
			 * session); maxCoverageCode++;
			 * 
			 * tempEntityCode = null; tempEntityCode = departmentFormVillageL
			 * .getVillageName().split(","); for (int k = 0; k <
			 * tempEntityCode.length; k++) { this.saveOrgCoverageDetail(
			 * maxCoverageCode, Integer.parseInt(tempEntityCode[k]), session); }
			 * this.saveOrgCoverage('S', maxCoverageCode, 'V', orgLocatedCode,
			 * session); maxCoverageCode++; } } else { // for Full
			 * this.saveOrgCoverage('F', 0, 'C', orgLocatedCode, session); }
			 * parentOrgLocatedCode = this.getMapKey(parentMp,
			 * departmentFormVillageL.getParent().trim()); orgLocated
			 * .setParentOrgLocatedLevelCode(parentOrgLocatedCode); orgLocated
			 * .setOrgLevelSpecificName(departmentFormVillageL
			 * .getDeptName().trim()); orgLocated
			 * .setOrgLevelSpecificNameLocal(departmentFormVillageL
			 * .getDeptNameLocal()); orgLocated
			 * .setOrgLevelSpecificShortName(departmentFormVillageL
			 * .getShortDeptName()); session.update(orgLocated);
			 * parentMp.put(orgLocatedCode, departmentFormVillageL
			 * .getDeptName().trim()); } // --------------- if (itr > 0) { i--;
			 * } } }
			 */
			tx.commit();

			organizationDAO.setParentCodeforExtendDepartment(orgCode);

			// code for org_unit table
			tx = session.beginTransaction();
			session.createSQLQuery("select * from set_org_units_fn(" + orgCode + ","

			+ stateCode + ")").list();
			tx.commit();
			return true;

		} catch (Exception e) {
			tx.rollback();
			/*
			 * boolean
			 * flag=this.deleteDeparmentTransaction(session,maxOrgCode1);
			 */

			LOG.error("Exception-->"+e);

			return false;
		} finally {
			tx.setTimeout(10);
			tx = null;
			ReleaseResources.doReleaseResources(session);
		}
	}

	@Override
	public boolean deleteEntityinExtendDepatment(String coverageDetailCode, Session session) throws Exception {
		Query query = null;
		int i = 0;
		try {
			query = session.createQuery("delete from OrganizationCoverageDetail o where o.isactive=true and o.coverageCode in(" + coverageDetailCode + ")");
			i = query.executeUpdate();
			if (i > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			return false;
		}
	}

	@Override
	public boolean deleteCoverageExtendDepatment(Integer orgLocatedLevelCode, Session session) throws Exception {
		Query query = null;
		int i = 0;
		try {
			query = session.createQuery("delete from OrganizationCoverage o where o.isactive=true and o.orgLocatedLevelCode=" + orgLocatedLevelCode);
			i = query.executeUpdate();
			if (i > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			return false;
		}
	}

	@Override
	public boolean extendOrgCoverageDetail(int maxCoverageCode, int entityCode, Session session) throws Exception {
		OrganizationCoverageDetail orgCoverageDetail = new OrganizationCoverageDetail();
		orgCoverageDetail.setCoverageCode(maxCoverageCode);
		orgCoverageDetail.setEntityCode(entityCode);
		orgCoverageDetail.setIsactive(true);
		orgCoverageDetail.setOrguntDone(false);
		try {
			organizationDAO.saveOrgCoverageDetail(orgCoverageDetail, session);
			return true;
		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public String getExtendDetaildofEntity(Integer olc, char located_at_level, char org_coverage_entity_type) throws Exception {
		Session session = null;
		Query query = null;
		String dlc = "";
		String queryString = null;
		List<Object[]> temp = null;

		try {

			queryString = "select  org_coverage_detail.entity_code from org_located_at_levels INNER JOIN org_coverage on org_located_at_levels.org_located_level_code=org_coverage.org_located_level_code"
					+ "  LEFT JOIN  org_coverage_detail on org_coverage.coverage_detail_code=org_coverage_detail.coverage_code"
					+ "  where org_located_at_levels.isactive=true and org_coverage.isactive=true and org_coverage_detail.isactive=true and org_located_at_levels.olc=" + olc + "  and org_located_at_levels.located_at_level='" + located_at_level
					+ "' and org_coverage_entity_type='" + org_coverage_entity_type + "'";

			session = sessionFactory.openSession();
			query = session.createSQLQuery(queryString);

			temp = query.list();

			if (temp.size() > 0) {

				for (int k = 0; k < temp.size(); k++) {
					// Object obj[] = temp.get(k);
					dlc += temp.get(k) + ",";
				}

				if (dlc.length() > 0){
					dlc = dlc.substring(0, dlc.lastIndexOf(","));
				}	

			}

		} catch (Exception e) {
			LOG.error("Exception-->"+e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return dlc;
	}

	@Override
	public String checkExtendDetailforDelete(Integer orgCode, char level) throws Exception {
		return organizationDAO.checkExtendDetailforDelete(orgCode, level);
	}

	@Override
	public boolean checkExistNameDeparment(Integer stateCode, char level, String deptName) throws Exception {
		return false; // organizationDAO.checkExistNameDeparment(stateCode,level,deptName);
	}

	@Override
	public List<OrgLocatedAtLevels> getDepartMentLowLevelDetails(Integer orgUnitCode) throws Exception {
		// TODO getMinistryDetail
		return organizationDAO.getDepartMentLowLevelDetails(orgUnitCode);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean abolishDepartmentforState(int olc) throws Exception {
		List<OrgLocatedAtLevels> orgLocatedList = null;
		List<OrganizationCoverage> orgCoverageList = null;
		List<OrganizationCoverageDetail> orgCoverageDetailList = null;
		List<Organization> organizationList = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			organizationList = session.createQuery("from Organization where isactive=true and olc=" + olc).list();
			if (!organizationList.isEmpty()) {
				Organization Organization = organizationList.get(0);

				orgLocatedList = session.createQuery("from OrgLocatedAtLevels where isactive=true and olc=" + olc).list();
				for (OrgLocatedAtLevels orgLocatedAtLevels : orgLocatedList) {
					orgCoverageList = session.createQuery("from OrganizationCoverage where isactive=true and orgLocatedLevelCode=" + orgLocatedAtLevels.getOrgLocatedLevelCode()).list();

					for (OrganizationCoverage organizationCoverage : orgCoverageList) {
						orgCoverageDetailList = session.createQuery("from OrganizationCoverageDetail where isactive=true and coverageCode=" + organizationCoverage.getCoverageDetailCode()).list();

						for (OrganizationCoverageDetail organizationCoverageDetail : orgCoverageDetailList) {
							OrganizationCoverageDetail organizationCoverageDetailupdate = (OrganizationCoverageDetail) session.get(OrganizationCoverageDetail.class, organizationCoverageDetail.getOrgCoverageDetailId());
							organizationCoverageDetailupdate.setIsactive(false);
							session.update(organizationCoverageDetailupdate);
							session.flush();
							session.clear();
						}

						OrganizationCoverage organizationCoverageupdate = (OrganizationCoverage) session.get(OrganizationCoverage.class, organizationCoverage.getOrgCoverageId());
						organizationCoverageupdate.setIsactive(false);
						session.update(organizationCoverageupdate);
						session.flush();
						session.clear();

					}

					OrgLocatedAtLevels orgLocatedAtLevelsupdate = (OrgLocatedAtLevels) session.get(OrgLocatedAtLevels.class, orgLocatedAtLevels.getOrgLocatedLevelCode());
					orgLocatedAtLevelsupdate.setIsactive(false);
					session.update(orgLocatedAtLevelsupdate);
					session.flush();
					session.clear();

				}

				OrganizationPK organizationPK = new OrganizationPK(olc, Organization.getOrgVersion());
				Organization organizationupdate = (Organization) session.get(Organization.class, organizationPK);
				organizationupdate.setIsactive(false);
				session.update(organizationupdate);
				session.flush();
				session.clear();

			}
			tx.commit();
			return true;

		} catch (Exception e) {
			LOG.error("Exception-->"+e);
			tx.rollback();
			return false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<DistrictLineDepartment> getLandRegionLineDepartmentListforOrganization(int entityCode, char level, List<Organization> orgList) throws Exception {
		List<DistrictLineDepartment> DistLineDepartment = null;
		DistLineDepartment = new ArrayList<DistrictLineDepartment>();
		DistLineDepartment = organizationDAO.getLandRegionLineDepartmentListforOrganization(entityCode, level, orgList);
		return DistLineDepartment;

	}

	@Override
	public List<DistrictLineDepartment> getLandRegionLineDepartmentDetailforOrganization(Integer orgUnitCode) throws Exception {
		List<DistrictLineDepartment> DistLineDepartment = null;
		DistLineDepartment = new ArrayList<DistrictLineDepartment>();
		DistLineDepartment = organizationDAO.getLandRegionLineDepartmentDetailforOrganization(orgUnitCode);
		return DistLineDepartment;

	}

	/*
	 * @modified by Maneesh on 24-05-2013
	 */
	@Override
	public List<DistrictLineDepartment> getLandRegionLineDepartmentList(Integer entityCode, Integer deptCode, char level) throws Exception {
		return (organizationDAO.getLandRegionLineDepartmentList(entityCode, deptCode, level));

	}

	/**
	 * Added by sushil on 14-06-2013
	 */
	@Override
	public GovernmentOrder getGovtOrderObject(MinistryForm ministryForm) throws Exception {
		GovernmentOrder governmentOrder = new GovernmentOrder();
		GovernmentOrderEntityWise governmentOrderEntityWise = new GovernmentOrderEntityWise();
		governmentOrder.setOrderDate(ministryForm.getOrderDate());
		governmentOrder.setEffectiveDate(ministryForm.getEffectiveDate());
		governmentOrder.setGazPubDate(ministryForm.getGazPubDate());
		governmentOrder.setCreatedon(new Date());
		governmentOrder.setDescription("LGD DETAILS");
		governmentOrder.setIssuedBy("GOVERNOR");
		governmentOrder.setCreatedby(createdBy);
		governmentOrder.setLastupdated(new Date());
		governmentOrder.setLastupdatedby(createdBy);
		governmentOrder.setLevel("C");
		governmentOrder.setOrderNo(ministryForm.getOrderNo());
		governmentOrder.setStatus('A');
		governmentOrder.setUserId(userId);
		governmentOrder.setAttachFile1(ministryForm.getAttachFile1());
		governmentOrderEntityWise.setEntityCode(ministryForm.getDeptCode());
		governmentOrderEntityWise.setEntityType('O');
		Integer OrderCode = localGovtBodyDAO.getmaxgovtordercode();
		governmentOrderEntityWise.setOrderCode(OrderCode + 1);
		governmentOrderEntityWise.setEntityVersion(2);
		governmentOrderEntityWise.setGovernmentOrder(governmentOrder);
		return governmentOrder;
	}

	public String getOrgTypeName(Integer typeCode) throws Exception {
		return organizationDAO.getOrgTypeName(typeCode);
	}

	@Override
	public List<DeptAdminUnit> getDeptAdminUnits(int slc,int type) throws Exception {
		return organizationDAO.getDeptAdminUnits(slc,type);
	}

	@Override
	public boolean saveDeptAdmitUnit(DeptAdminUnit deptAdminUnit)
			throws Exception {
		return organizationDAO.saveDeptAdmitUnit(deptAdminUnit);
	}

	@Override
	public boolean adminUnitOradminChildExist(int slc,String deptAdminUnitName,int adminUnitCode,char choiceType,int parentCode) throws Exception {
		return organizationDAO.adminUnitOradminChildExist(slc,deptAdminUnitName,adminUnitCode,choiceType,parentCode);
	}

	@Override
	public DepartmentAdminForm getDeptAdminUnitDetail(int adminUnitCode,char adminType)
			throws Exception {
		DepartmentAdminForm departmentAdminForm = null;
		if (adminType == 'U')
			departmentAdminForm = organizationDAO
					.getDeptAdminUnitDetail(adminUnitCode);
		else if (adminType == 'E')
			departmentAdminForm = organizationDAO
					.getDeptAdminEntityDetail(adminUnitCode);
		return departmentAdminForm;
	}

	@Override
	public boolean deleteAdminUnitEntity(Integer slc, Integer adminUnitCode,int entityType)
			throws Exception {
		return organizationDAO.deleteAdminUnitEntity(slc,adminUnitCode,entityType);
	}

	@Override
	public boolean saveDeptAdmitEntity(DepartmentAdminForm departmentAdminForm)
			throws Exception {
		DeptAdminUnitEntity deptAdminEntity = new DeptAdminUnitEntity();
		deptAdminEntity.setAdminEntityNameEnglish(departmentAdminForm.getAdminEntityNameEnglish());
		deptAdminEntity.setAdminEntityNameLocal(departmentAdminForm.getAdminEntityNameLocal());
		deptAdminEntity.setAdminUnitLevelCode(departmentAdminForm.getAdminUnitLevelCode());
		deptAdminEntity.setParentAdminUnitEntityCode(departmentAdminForm.getParentAdminUnitEntityCode());
		deptAdminEntity.setSlc(departmentAdminForm.getSlc());
		deptAdminEntity.setCreatedby(departmentAdminForm.getCreatedby());
		deptAdminEntity.setPublishOrSave(departmentAdminForm.getPublishOrSave());
		deptAdminEntity.setDistrictCode(departmentAdminForm.getDistrictCode());
		
		boolean status= false;
		Session session = null;
		Transaction tx= null;
		String adminCoverage=departmentAdminForm.getAdminCoverage();
		String wardCoverage=departmentAdminForm.getWardCoverage();
		Integer adminCoverageCode=Integer.valueOf(0);
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			deptAdminEntity.setParentCategory(organizationDAO.getParentCategory(departmentAdminForm.getAdminUnitLevelCode(), session));
			if(departmentAdminForm.isCoverageExist())
			{
			Query query = session
						.createSQLQuery("select CAST(nextval('adminCoverageCode_seq') AS integer) ");		
			adminCoverageCode=(Integer)query.uniqueResult();
			deptAdminEntity.setCoverageExist(true);
			}
			deptAdminEntity.setAdminCoverageCode(adminCoverageCode);
			status=organizationDAO.saveDeptAdmitEntity(deptAdminEntity,session);
			if(status && departmentAdminForm.isCoverageExist())
			status=organizationDAO.saveDeptAdminCoverage(adminCoverage,wardCoverage,adminCoverageCode,null,session);
			if(status)
			tx.commit();
		}  catch (Exception e) {
			LOG.debug("Exception" + e);
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	@Override
	public List<DeptAdminUnitEntity> getDeptAdminEntity(int slc)
			throws Exception {
		return organizationDAO.getDeptAdminEntity(slc);
	}

	@Override
	public boolean modifyDepartmentAdminDetail(DepartmentAdminForm dAdminForm,
			char parentModify) throws Exception {
		return organizationDAO.modifyDepartmentAdminDetail(dAdminForm,parentModify);
	}

	@Override
	public List<DeptAdminUnit> getUnitLevelNames(Integer adminUnitCode,Integer slc) throws Exception {
		return organizationDAO.getUnitLevelNames(adminUnitCode,slc);
	}

	@Override
	public List<AdministrativeEntityCoverage> getEntityCoverageDetail(
			Integer entityCode) throws Exception {
		return organizationDAO.getEntityCoverageDetail(entityCode);
	}

	@Override
	public List<DeptAdminUnitEntity> getUnityEntityByParentLevel(Integer slc,
			int levelCode, int entityCode) throws Exception {
		return organizationDAO.getUnityEntityByParentLevel(slc,levelCode,entityCode);
	}

	

	@Override
	public List<AdministrativeEntityCoverage> adminEntityCoveredArea(int entityCode,int slc,char coverageType) throws Exception{
		return organizationDAO.adminEntityCoveredArea(entityCode,slc,coverageType);
	}
	
	/*Modified by Pooja on 13-08-2015*/
	@Override
	public boolean modifyEntityCoverage(
			DepartmentAdminForm departmentAdminForm) throws Exception {
		boolean status= false;
		Session session = null;
		Transaction tx= null;
		String adminCoverage=departmentAdminForm.getAdminCoverage();
		String wardCoverage=departmentAdminForm.getWardCoverage();
		Integer entityCode=departmentAdminForm.getAdminUnitCode();
		Integer adminCoverageCode = Integer.valueOf(0);
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session
					.createSQLQuery("select admin_coverage_code from administration_unit_entity where admin_unit_entity_code=:code");
			query.setParameter("code", entityCode);
		    adminCoverageCode=(Integer)query.uniqueResult();
			status=organizationDAO.saveDeptAdminCoverage(adminCoverage,wardCoverage,adminCoverageCode,entityCode,session);
			tx.commit();
		}  catch (Exception e) {
			LOG.debug("Exception" + e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	@Override
	public List<Organization> getOrganizationDetailbystateandType(
			Integer stateCode,int type) throws Exception {
		return organizationDAO.getOrganizationDetailbystateandType(stateCode,type);
	}

	@Override
	public List<Organization> getOrganizationParent(Integer orgCode, int orgLevel)
			throws Exception {
		return organizationDAO.getOrganizationParent(orgCode,orgLevel);
	}

	@Override
	public List<Organization> getOrganizationChilds(Integer parentOrgCode)
			throws Exception {
		return organizationDAO.getOrganizationChilds(parentOrgCode);
	}

	@Override
	public boolean updateOrgParentChildUnits(int parentCode,
			String childOrgUnits) throws Exception {
		return organizationDAO.updateOrgParentChildUnits(parentCode,childOrgUnits);
	}

	@Override
	public List<Organization> getOrganizationSpecificChilds(
			Integer parentOrgCode) throws Exception {
		return organizationDAO.getOrganizationSpecificChilds(parentOrgCode);
	}
    
	/*added by chandra on 11-09-14 to get previous mapped organization Units */
	@Override
	public List<Organization> getOrganizationMappedChilds(Integer parentOrgCode)
			throws Exception {
		return organizationDAO.getOrganizationMappedChilds(parentOrgCode);
	}

	@Override
	public List<Object> getAdminEntityOrgUnits(Integer adminEntityCode)
			throws Exception {
		return organizationDAO.getAdminEntityOrgUnits(adminEntityCode);
	}

	@Override
	public String updateOrgUnitNames(Integer adminEntityCode, String name)
			throws Exception {
		return organizationDAO.updateOrgUnitNames(adminEntityCode,name);
	}
	@Override
	public List<ExtendDepartment> getAdministrativeUnitLevelByOrgCode(
			Integer orgCode,String isCenterFlag) throws Exception {
		// TODO Auto-generated method stub
		return organizationDAO.getAdministrativeUnitLevelByOrgCode(orgCode,isCenterFlag);
	}

	@Override
	public List<OrgLocatedAtLevels> getAdministrativeUnitLevelByOrgLevel(
			Integer orgCode, Integer locatedAtLevel) throws Exception {
		return organizationDAO.getAdministrativeUnitLevelByOrgLevel(orgCode,locatedAtLevel);
	}
	/**
	 * Get OrgCoverage For Full Coverage Status
	 * @param orgLevelCode
	 * @return
	 * @throws Exception
	 */
	public String getOrgCoverageForFullCoveage(int orgLevelCode)
			throws Exception{
		return organizationDAO.getOrgCoverageForFullCoveage(orgLevelCode);
	}

	@Override
	public String modifyDepartmentAdminEntity(DepartmentAdminForm dAdminForm,
			char parentModify) throws Exception {
		return organizationDAO.modifyDepartmentAdminEntity(dAdminForm,parentModify);
	}

	
	/*
	 * added on 11/12/2014 for localbody impact
	 * 
	 */
	@Override
	public List<DeptAdminUnit> getUnitLevelNamesForLocalBody(
			Integer adminUnitCode, Integer slc) throws Exception {
		// TODO Auto-generated method stub
		return organizationDAO.getUnitLevelNamesForLocalBody(adminUnitCode,slc);
	}
	/**
	 *
	 * added on 11/01/2015 for getDepartmentDetails by parentorgcode
	 * added locatedAtLevel parameter on 18-06-2015 by pooja
	 */
	 
	@Override
	public List<Organization> getOrganizationParentName(int orgLevel,int locatedAtLevel)
			throws Exception {
		return organizationDAO.getOrganizationParentName(orgLevel,locatedAtLevel);
		
	}
	/*
	 *get Update Org Unit 
	 *on 11/01/2015
	 *added  by Anju Gupta
	 */
	
	@Override
	public boolean getUpdateMethod(OrganizationUnitForm orggunit) throws Exception {
	
		
	return  organizationDAO.getUpdateMethod(orggunit);
		
		
	}
	/*
	 *getDepartmentDetails by parentorgcode
	 *on 11/01/2015
	 *added  by Anju Gupta
	 */
	
	@Override
	public  List<Organization> getDepartmentDetails(Integer parentorgcode)throws Exception {
	
		
		return  organizationDAO.getDepartmentDetails(parentorgcode);
		
		
	}
	
	
	/*
	 *getOrganizationDetailbySLCCode
	 *on 11/01/2015
	 *added  by Anju Gupta
	 */
	@Override 
	public List<Organization>  getOrganizationDetailbySlcCode(Integer slcCode)
			throws Exception {
		return  organizationDAO.getOrganizationDetailbySlcCode(slcCode);
		// TODO Auto-generated method stub
		
	}
	
	//anju
		@Override
		public List<OrganizationUnit> getOrganizationeUnitLevelByorglocatedlevelcode(
				Integer orglocatedlevelcode) throws Exception {
			// TODO Auto-generated method stub
			return organizationDAO.getOrganizationeUnitLevelByorglocatedlevelcode(orglocatedlevelcode);
		}
	
		
		//anju  getOrganizationnUnitLevelByOrgLevel
			@Override
			public List<OrgLocatedAtLevels> getOrganizationnUnitLevelByOrgLevel(
					Integer orglocatedlevelcode) throws Exception {
				// TODO Auto-generated method stub
				return organizationDAO.getOrganizationnUnitLevelByOrgLevel(orglocatedlevelcode);
			}
			
			
			
	//added by Anju getOrg_located_at_levelsByOrgCode on	18/03/2015
			
	@Override
	public List<ExtendDepartment> getOrg_located_at_levelsByOrgCode(
			Integer orgCode, String isCenterFlag) throws Exception {

		return organizationDAO.getOrg_located_at_levelsByOrgCode(orgCode,
				isCenterFlag);
	}

	@Override
	public List<ExtendDepartment> getAdministrativeUnitLevelDeptByOrgCode(Integer orgCode) throws Exception {
		return organizationDAO.getAdministrativeUnitLevelDeptByOrgCode(orgCode);
	}
	
	/**
	 * To get Draft Dept Admin Units
	 * @author Ripunj on 21-04-2015
	 * @param slc
	 * @param type
	 * @param isPublish
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DeptAdminUnit> getDraftDeptAdminUnits(Integer slc,
			Integer type, Character isPublish) throws Exception {
		// TODO Auto-generated method stub
		return organizationDAO.getDraftDeptAdminUnits(slc, type, isPublish);
	}

	@Override
	public boolean saveorUpdateDeptAdmitUnitDraft(DeptAdminUnit deptAdminUnit)
			throws Exception {
		// TODO Auto-generated method stub
		return organizationDAO.saveorUpdateDeptAdmitUnitDraft(deptAdminUnit);
	}
	/**
	 * To delete Draft Dept Admin Units
	 * @author Ripunj on 22-04-2015
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteAdminUnitLevel(Integer slc, Integer adminUnitCode,
			int entityType) throws Exception {
		// TODO Auto-generated method stub
		return organizationDAO.deleteAdminUnitLevel(slc, adminUnitCode, entityType);
	}
	
	/**
	 * To update active flag false in case of delete administrative unit level ,entities and their coverages
	 * @author Ripunj on 22-04-2015
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return 
	 * @throws Exception
	 */
	@Override
	public boolean deleteAdminUnitEntitysetFlagFalse(Integer slc,
			Integer adminUnitCode, int entityType) throws Exception {
		// TODO Auto-generated method stub
		return organizationDAO.deleteAdminUnitEntitysetFlagFalse(slc, adminUnitCode, entityType);
	}

	/**
	 * Added by kirandeep for the geting the parent level for draft in admin unit entity
	 * @param slc
	 * @param levelCode
	 * @param entityCode
	 * @return
	 * @throws Exception
	 */			

	public List<DeptAdminUnitEntity> getUnityEntityByParentLevelForDraft(Integer slc,int levelCode, int entityCode) throws Exception {
		return organizationDAO.getUnityEntityByParentLevelForDraft(slc, levelCode, entityCode);
	}
	
	/**
	 * Added by kirandeep for deleting a admin unit enitity from draft
	 * @param slc
	 * @param adminUnitCode
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean deleteAdminUnitEntityForDraft(Integer slc,Integer adminUnitCode, int entityType) throws Exception {
		return organizationDAO.deleteAdminUnitEntityForDraft(slc, adminUnitCode, entityType);
	}
	
	/**
	 * Added by kirandeep for for publishing admin entity
	 * @param adminEntityCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean publishAdminEntity(Integer adminEntityCode) throws Exception {
		return organizationDAO.publishAdminEntity(adminEntityCode);
	}
		
	/**
	 * Added by kirandeep for modifying admin unit entity in draft
	 * @param dAdminForm
	 * @param parentModify
	 * @return
	 * @throws Exception
	 */
	@Override
	public String modifyDepartmentAdminEntityForDraft(DepartmentAdminForm dAdminForm,
		char parentModify) throws Exception {
		return organizationDAO.modifyDepartmentAdminEntityForDraft(dAdminForm, parentModify);
	}
	
	/**
	 * added by kirandeep for the getting the details of coveranges of draft admin entity
	 * @param entityCode
	 * @param slc
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<AdministrativeEntityCoverage> getEntityCoverageDetailForDraft(Integer entityCode) throws Exception {
	
		return organizationDAO.getEntityCoverageDetailForDraft(entityCode);
	}
	
	/**
	 * added by kirandeep for saving the coverage of draft admin unit entity
	 * @param DepartmentAdminForm
	 * 
	 */
	
	@Override
	public boolean modifyEntityCoverageForDraft(
			DepartmentAdminForm departmentAdminForm) throws Exception {
		boolean status= false;
		Session session = null;
		Transaction tx= null;
		String adminCoverage=departmentAdminForm.getAdminCoverage();
		String wardCoverage=departmentAdminForm.getWardCoverage();
		Integer entityCode=departmentAdminForm.getAdminUnitCode();
		Integer adminCoverageCode = Integer.valueOf(0);
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session
					.createSQLQuery("select admin_coverage_code from administration_unit_entity where admin_unit_entity_code=:code");
			query.setParameter("code", entityCode);
		    adminCoverageCode=(Integer)query.uniqueResult();
			status=organizationDAO.saveDeptAdminCoverageForDraft(adminCoverage,wardCoverage,adminCoverageCode,entityCode,session);
			tx.commit();
		}  catch (Exception e) {
			LOG.debug("Exception" + e);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}
	
	/**
	 * @param slc
	 */
	@Override
	public List<DeptAdminUnit> getAdminLevelForDistrictUser(Integer slc)
			throws Exception {
		// TODO Auto-generated method stub
		return organizationDAO.getAdminLevelForDistrictUser(slc);
	}

	/**
	 * 
	 */
	@Override
	public List<DeptAdminUnitEntity> getAdminEntityForDistrictUser(
			Integer entity_code, Integer admin_level) throws Exception {
		// TODO Auto-generated method stub
		return organizationDAO.getAdminEntityForDistrictUser(entity_code, admin_level);
	}

/**
	 * 
	 * @param adminUnitCode
	 * @param slc
	 * @return
	 * @throws Exception
	 */
	public List<DeptAdminUnit> getUnitLevelNamesForLocalBodyDistrictUser(
			Integer adminUnitCode, Integer districtCode) throws Exception{
		return organizationDAO.getUnitLevelNamesForLocalBodyDistrictUser(adminUnitCode, districtCode);
	}

	/**
	 * To get Unit Admin Entities for District User
	 * @author Ripunj on 28-04-2015
	 * @param districtCode
	 * @param levelCode
	 * @param entityCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DeptAdminUnitEntity> getUnityEntityByParentLevelForDistrictUser(
			Integer districtCode, int levelCode, int entityCode) throws Exception {
		// TODO Auto-generated method stub
		return organizationDAO.getUnityEntityByParentLevelForDistrictUser(districtCode,levelCode,entityCode);
	}
	
	@Override
	public List<DeptAdminUnitEntity> getDeptAdminEntityForDistrict(Integer slc,Integer districtCode) throws Exception{
		return organizationDAO.getDeptAdminEntityForDistrict(slc, districtCode);
	}
	
	/**
	 * added by pooja on 23-06-2015 for capture Org Unit Details
	 * */
	@Override
	public List<OrganizationUnit> getOrganizationUnitDetails(Integer orgUnitCode) throws Exception
	{
		return organizationDAO.getOrganizationUnitDetails(orgUnitCode);
	}
	@Override
	public Object[] getVlcTlcDlc(Integer villageCode) throws Exception
	{
		return organizationDAO.getVlcTlcDlc(villageCode);
	}
	@Override
	public Integer getSlcByLbOrVillageCode(Integer code) throws Exception
	{
		return organizationDAO.getSlcByLbOrVillageCode(code);
	}
	@Override	
	public boolean checkExistingOrgUnitName(Integer stateCode, String orgUnitName) throws Exception{
		return organizationDAO.checkExistingOrgUnitName(stateCode, orgUnitName);
	}
	
	/**
	 *  added by pooja on 07-08-2015 for ManageAdminUnitEntity Coverage
	 */
	@Override
	public Integer[] getLocalBobyTypeListByAdminUnitCode(Integer adminUnitCode,Integer slc,String status) throws Exception{
		return organizationDAO.getLocalBobyTypeListByAdminUnitCode(adminUnitCode, slc, status);
	}
	/**
	 *  added by pooja on 14-08-2015 
	 */
	@Override
	public Integer getdistrictByadminUnitCodeAndParentEntityCode(Integer adminUnitCode, Integer parentEntityCode) throws Exception{
		return organizationDAO.getdistrictByadminUnitCodeAndParentEntityCode(adminUnitCode, parentEntityCode);
	}
	
	/**
	 * added by pooja on 16-09-2015
	 */
	@Override
	public boolean checkExistOrgLevelSpecificName(Integer stateCode, String orgLevelSpecificName) throws Exception {
		return organizationDAO.checkExistOrgLevelSpecificName(stateCode, orgLevelSpecificName);
	}
	
	@Override	
	public String changeLvlSpecificNameOrg(OrgLocatedAtLevelsForm orgLocatedAtLevelsForm,HttpSession session) throws Exception {
		return organizationDAO.changeLvlSpecificNameOrg(orgLocatedAtLevelsForm, session);
	}
	
	/**
	 * added by pooja on 20-10-2015
	 */
	@Override
	public Integer getParentAdmnUnitLvlCode(Integer adminUnitLevelCode) throws Exception{
		return organizationDAO.getParentAdmnUnitLvlCode(adminUnitLevelCode);
	}
	@Override
	public Integer getStateByParentUnitEntityCode(Integer adminUnitLvlCode,Integer parentUnitEntityCode) throws Exception{
		return organizationDAO.getStateByParentUnitEntityCode(adminUnitLvlCode, parentUnitEntityCode);
	}

	@Override
	public List<OrganizationUnit> getOrganizationeUnitsByorglocatedlevelcode(Integer orglocatedlevelcode) throws Exception {
		return organizationDAO.getOrganizationeUnitsByorglocatedlevelcode(orglocatedlevelcode);
	}
	
	/*added by deepti on 11-05-2016 for unit overlapping exist or not*/
	public Character getOverlappingExist(Integer adminUnitlabelcode) throws Exception{
		return organizationDAO.getOverlappingExist(adminUnitlabelcode);
	}
	
	/*added by deepti on 16-05-2016  for getting entity list */
	public List<DeptAdminUnitEntity> getEntityList(Integer adminUnitLabelCode) throws Exception{
		return organizationDAO.getEntityList(adminUnitLabelCode);
	}
	
	/**
	 * added by Sunita on 24-11-2016
	 */
	@Override
	public List<DeptAdminUnit> getDeptAdminUnitsRevenue(int slc,int type) throws Exception {
		return organizationDAO.getDeptAdminUnitsRevenue(slc,type);
	}

	@Override
	public List<OrgLocatedAtLevels> getOrgLocatedbyOrgForExtendDepartment(boolean isCenterUser, int orgCode) throws Exception {
		return organizationDAO.getOrgLocatedbyOrgForExtendDepartment(isCenterUser, orgCode);
	}

	@Override
	public  Map<String, Object> getOrgUnitswiseDeptDetails(Integer orgUnitCode,boolean isParentOrgUnit) throws Exception {
		return organizationDAO.getOrgUnitswiseDeptDetails(orgUnitCode,isParentOrgUnit);
	}

	@Override
	public List<Organization> getOrganizationListbyOrgType(Integer orgTypeCode, Integer stateCode) throws Exception {
		return organizationDAO.getOrganizationListbyOrgType(orgTypeCode, stateCode);
	}

	@Override
	public List<Object> getOrganizationAtLevelbyOrgCode(int orgCode) throws Exception {
		return organizationDAO.getOrganizationAtLevelbyOrgCode(orgCode);
	}

	@Override
	public boolean publishAdminEntityAll(String adminEntityCodes) throws Exception {
		List<Integer> adminEntityCodeList = new ArrayList<Integer>();
		adminEntityCodeList.add(-99);
		if (adminEntityCodes!=null && adminEntityCodes.contains(",")) {
			Scanner scanner = new Scanner(adminEntityCodes);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				adminEntityCodeList.add(Integer.parseInt(scanner.next()));
			}
		}else if(adminEntityCodes!=null && adminEntityCodes.length()>0){
			adminEntityCodeList.add(Integer.parseInt(adminEntityCodes));
		}
		return organizationDAO.publishAdminEntityAll(adminEntityCodeList);
	}
	@Override
	public List<Organization> getOrganizationDetails() throws Exception {
		System.out.println("report service impl +++++++++++++++++++");
		return organizationDAO.getOrganizationDetails();
	}
	
	
	@Override
	public boolean updateParentAdminEntityDetailsCall(DepartmentAdminForm adminUnitView , Long userId) throws Exception {
		return organizationDAO.updateParentAdminEntityDetailsCall(adminUnitView ,userId);
	}

	
}