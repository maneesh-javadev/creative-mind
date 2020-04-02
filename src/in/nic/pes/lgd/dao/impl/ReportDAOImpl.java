package in.nic.pes.lgd.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cmc.lgd.localbody.entities.LBTypeDetailsWithCategory;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.org.ep.exception.BaseAppException;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.ConsolidateReportForRuralLB;
import in.nic.pes.lgd.bean.ConsolidateReportLB;
import in.nic.pes.lgd.bean.ConsolidateReportLandregions;
import in.nic.pes.lgd.bean.ConsolidateReportLandregionsforDistrict;
import in.nic.pes.lgd.bean.ConsolidateReportLandregionsforSubDistrict;
import in.nic.pes.lgd.bean.ConsolidateReportLandregionsforVillage;
import in.nic.pes.lgd.bean.DPwardConstituencyWiseVP;
import in.nic.pes.lgd.bean.DistrictWiseDetail;
import in.nic.pes.lgd.bean.DistrictWiseInvalidatedVillage;
import in.nic.pes.lgd.bean.GetLBHierarchy;
import in.nic.pes.lgd.bean.GetLocalBodyListbyLBtypebyState;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LGDUpdationEntity;
import in.nic.pes.lgd.bean.LandRegionDetail;
import in.nic.pes.lgd.bean.LocalBodyHistory;
import in.nic.pes.lgd.bean.LocalBodyWiseWards;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.MappedVillageByLBCodeBean;
import in.nic.pes.lgd.bean.PCACMappingforLBWard;
import in.nic.pes.lgd.bean.ParentwiseChildDetials;
import in.nic.pes.lgd.bean.ReportDistwiseVillagandMappedGP;
import in.nic.pes.lgd.bean.ReportingStatewiseGISMappedLBEntity;
import in.nic.pes.lgd.bean.StateWiseDistrictVillagePanchaytMapping;
import in.nic.pes.lgd.bean.StatewiseGPtoVillageMapped;
import in.nic.pes.lgd.bean.StatewiseUnmappedVillages;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.UnmappedLbPcAcWard;
import in.nic.pes.lgd.bean.ViewUnMappedLocalBodies;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.ReportDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.restructure.reporting.entities.LocalBodyMappedToDistrictsEntity;

@SuppressWarnings("unchecked")
@Transactional
public class ReportDAOImpl implements ReportDAO {

	private static final Logger log = Logger.getLogger(ReportDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	StateDAO stateDao;

	@Autowired
	private DistrictDAO districtDAO;

	@Autowired
	private LocalGovtBodyDAO localGovtBodyDAO;

	
	@Override
	public List<ConsolidateReportLB> getConsolidatedRptForLBs() throws Exception {
		Session session = null;
		Query query = null;
		List<ConsolidateReportLB> getConsolidatedRptForLBs = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getConsolidateReportLB");
			getConsolidatedRptForLBs = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getConsolidatedRptForLBs;
	}

	@Override
	public List<ConsolidateReportLandregions> getConsolidatedRptForLandregion() throws Exception {
		Session session = null;
		Query query = null;
		List<ConsolidateReportLandregions> getConsolidatedRptForLandregion = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getConsolidateReportLandregion");
			getConsolidatedRptForLandregion = query.list();

		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getConsolidatedRptForLandregion;
	}

	@Override
	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyState(int stateCode, char type, char level) throws Exception {
		Session session = null;
		Query query = null;
		List<GetLocalBodyListbyLBtypebyState> getLBList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodyListbyLBtypebyState").setParameter("type", type, Hibernate.CHARACTER).setParameter("level", level, Hibernate.CHARACTER).setParameter("stateCode", stateCode, Hibernate.INTEGER);
			getLBList = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLBList;
	}

	@Override
	public List<ConsolidateReportLandregionsforDistrict> getLocalBodyListbyLBtypebyState(int slc) throws Exception {
		Session session = null;
		Query query = null;
		List<ConsolidateReportLandregionsforDistrict> getLBList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getConsolidateReportLandregionforDistrict").setParameter("slc", slc, Hibernate.INTEGER);
			getLBList = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLBList;
	}

	@Override
	public List<ConsolidateReportLandregionsforSubDistrict> getLocalBodyListbysubdistic(int slc, int dlc) throws Exception {
		Session session = null;
		Query query = null;
		List<ConsolidateReportLandregionsforSubDistrict> getLBList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getConsolidateReportLandregionforSubDistrict").setParameter("slc", slc, Hibernate.INTEGER).setParameter("dlc", dlc, Hibernate.INTEGER);

			getLBList = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLBList;
	}

	@Override
	public List<ConsolidateReportLandregionsforVillage> getLocalBodyListbyvillage(int tlc) throws Exception {
		Session session = null;
		Query query = null;
		List<ConsolidateReportLandregionsforVillage> getLBList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getConsolidateReportLandregionforVillage")

			.setParameter("tlc", tlc, Hibernate.INTEGER);
			getLBList = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLBList;
	}

	@Override
	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyState(int stateCode, char type, char level, int parentLBCode) throws Exception {
		Session session = null;
		Query query = null;
		Integer parentCode = null;
		if (parentLBCode != 0 && parentLBCode != -1) {
			parentCode = parentLBCode;
		}
		List<GetLocalBodyListbyLBtypebyState> getLBList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLBListbyLBtypebyParentCbyState").setParameter("type", type, Hibernate.CHARACTER).setParameter("level", level, Hibernate.CHARACTER).setParameter("stateCode", stateCode, Hibernate.INTEGER)
					.setParameter("parentLBCode", parentCode, Hibernate.INTEGER);
			getLBList = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLBList;
	}
	
	// Added By Pranav
	@Override
	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyFinYear(int stateCode, char type, char level,
			int parentLBCode, String financialYear) throws Exception {
		Session session = null;
		Query query = null;
		Integer parentCode = null;
		if(parentLBCode !=0 && parentLBCode != -1){
			parentCode = parentLBCode;
			
		}
		List<GetLocalBodyListbyLBtypebyState> getLBlist = null;
		try {
			
			session = sessionFactory.openSession();
			String NameQuery="getLBListbyLBtypebyParentCbyWithoutFinYear";
			if(financialYear!=null && !("").equals(financialYear.trim())){
				NameQuery="getLBListbyLBtypebyParentCbyFinYear";	
			}
			query = session.getNamedQuery(NameQuery);
			query.setParameter("type", type, Hibernate.CHARACTER);
			query.setParameter("level", level, Hibernate.CHARACTER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("parentLBCode", parentCode, Hibernate.INTEGER);
			if(financialYear!=null && !("").equals(financialYear.trim())){
				query.setParameter("financialYear",financialYear,Hibernate.CHARACTER);
			}
			
			getLBlist = query.list();
		} catch (Exception e) {
			throw e;
		}finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		
		return getLBlist;
	}
	

	@Override
	public List<ConsolidateReportForRuralLB> getConsolidatedRptForRuralLBs(String financialYear) throws Exception {
		Session session = null;
		Query query = null;
		List<ConsolidateReportForRuralLB> getConsolidatedRptForLBs = null;
		try {
			session = sessionFactory.openSession();
			String NameQuery="getConsolidateReportForRuralLBWithoutFinYear";
			if(financialYear!=null && !("").equals(financialYear.trim())){
				NameQuery="getConsolidateReportForRuralLB";	
			}
			query = session.getNamedQuery(NameQuery);
			if(financialYear!=null && !("").equals(financialYear.trim())){
				query.setParameter("financialYear",financialYear);	
			}
			
			getConsolidatedRptForLBs = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getConsolidatedRptForLBs;
	}

	@Override
	public List<MappedVillageByLBCodeBean> getMappedVillageByLBCode(int lbCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<MappedVillageByLBCodeBean> mappedVillageByLBCode = new ArrayList<MappedVillageByLBCodeBean>();
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("Select distinct village_code,v.census_2011_code,village_name_english,r.coverage_type,ismainregion from localbody l, lb_covered_landregion r,village v"
					+ " where l.local_body_code=:lbCode and l.lb_covered_region_code=r.lb_covered_region_code and r.lrlc=v.village_code and r.land_region_type='V' and r.isactive=true and l.isactive=true and v.isactive=true");
			criteria.setParameter("lbCode", lbCode, Hibernate.INTEGER);
			mappedVillageByLBCode = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mappedVillageByLBCode;
	}

	public List<StatewiseUnmappedVillages> getStateWiseUnmappedVillages(Integer stateCode, Integer limit, Integer offset) throws Exception {
		List<StatewiseUnmappedVillages> stateWiseUnmappedVillagesList = new ArrayList<StatewiseUnmappedVillages>();
		stateWiseUnmappedVillagesList = null;
		Query query = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getStatewiseUnmappedVillages").setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("limit", limit, Hibernate.INTEGER).setParameter("offset", offset, Hibernate.INTEGER);

			stateWiseUnmappedVillagesList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			session.close();
		}
		return stateWiseUnmappedVillagesList;
	}

	//@SuppressWarnings("unchecked")
	public List<StatewiseGPtoVillageMapped> getStateWiseGPtoVillageMapping() throws Exception {
		List<StatewiseGPtoVillageMapped> stateWiseGPtoVillageMappingList = new ArrayList<StatewiseGPtoVillageMapped>();
		Query query = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getStatewiseGPtoVillageMapping");
			stateWiseGPtoVillageMappingList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			session.close();
		}
		return stateWiseGPtoVillageMappingList;
	}
	
	public List<StatewiseGPtoVillageMapped> getStateWiseGPtoVillageMappingB() throws Exception {
		List<StatewiseGPtoVillageMapped> stateWiseGPtoVillageMappingList = new ArrayList<StatewiseGPtoVillageMapped>();
		Query query = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getStatewiseGPtoVillageMappingB");
			stateWiseGPtoVillageMappingList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			session.close();
		}
		return stateWiseGPtoVillageMappingList;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public List<UnmappedLbPcAcWard> getunmappedLbPcAcWard(Integer entity_code) throws Exception {
		List<UnmappedLbPcAcWard> stateWiseGPtoVillageMappingList = new ArrayList<UnmappedLbPcAcWard>();
		stateWiseGPtoVillageMappingList = null;

		Query query = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("unmappedLbPcAcWardBean").setParameter("entity_code", entity_code, Hibernate.INTEGER);

			stateWiseGPtoVillageMappingList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			session.close();
		}
		return stateWiseGPtoVillageMappingList;
	}

	@Override
	public List<StateWiseDistrictVillagePanchaytMapping> getStateWiseGPtoVillageMapping(Integer entity_code) throws Exception {
		List<StateWiseDistrictVillagePanchaytMapping> stateWiseGPtoVillageMappingList = new ArrayList<StateWiseDistrictVillagePanchaytMapping>();
		stateWiseGPtoVillageMappingList = null;
		Query query = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("DistrictVillagePanchaytMapping").setParameter("entity_code", entity_code, Hibernate.INTEGER);

			stateWiseGPtoVillageMappingList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			session.close();
		}
		return stateWiseGPtoVillageMappingList;
	}

	@Override
	public List<Object[]> getWardsPerLBType(Integer statecode) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder builder = new StringBuilder(" select f.local_body_type_code, " + " f.local_body_type_name," + " (SELECT count(1) FROM localbody_ward w WHERE EXISTS ( " + "  SELECT l.local_body_type_code FROM localbody l WHERE "
					+ "  w.lblc=l.lblc and w.isactive and l.isactive and l.slc=:stateCode and " + "  l.local_body_type_code = f.local_body_type_code)" + " ) as total_wards," + " f.nomenclature_english, f.nomenclature_local"
					+ " from get_local_gov_setup_fn(:stateCode) f " + " group by f.local_body_type_code, f.local_body_type_name," + " f.nomenclature_english, f.nomenclature_local");

			Query query = session.createSQLQuery(builder.toString());
			query.setCacheable(true);
			query.setCacheMode(CacheMode.GET);
			query.setParameter("stateCode", statecode);
			//@SuppressWarnings("unchecked")
			List<Object[]> wardsPerLBTypes = query.list();
			return wardsPerLBTypes;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	@Override
	public List<Object[]> getWardsPerLocalBody(Integer statecode, Integer localbodyType) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder builder = new StringBuilder("select local_body_code, local_body_name_english, local_body_name_local, lblc, ward_counts, parent_name, grandparent_name from get_ward_count_fn(:stateCode, :lbTypeCode)  limit 10");
			Query query = session.createSQLQuery(builder.toString());
			query.setCacheable(true);
			query.setCacheMode(CacheMode.GET);
			query.setParameter("stateCode", statecode);
			query.setParameter("lbTypeCode", localbodyType);

			//@SuppressWarnings("unchecked")
			List<Object[]> wardsPerLocalBodies = query.list();
			return wardsPerLocalBodies;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	@Override
	public List<Object[]> getWardsDetails(Integer lblcCode) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder builder = new StringBuilder("select ward_name_english, ward_name_local, ward_number from localbody_ward where isactive and lblc= :lblcCode order by ward_number asc");
			Query query = session.createSQLQuery(builder.toString());
			query.setCacheable(true);
			query.setCacheMode(CacheMode.GET);
			query.setParameter("lblcCode", lblcCode);

			//@SuppressWarnings("unchecked")
			List<Object[]> wardsList = query.list();
			return wardsList;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	//@SuppressWarnings("unchecked")
	@Override
	public String getLocalBodyNameByCode(Integer lbCode) {
		// TODO Auto-generated method stub
		String Message = "";
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder builder = new StringBuilder("SELECT nomenclature_english, local_body_name_english,parent_name,state_name from get_panchayats_detailesby_lb(:lblcCode)");
			Query query = session.createSQLQuery(builder.toString());
			query.setParameter("lblcCode", lbCode);
			List<Object[]> localbodyList = query.list();
			if (!localbodyList.isEmpty()) {
				Object[] lbDetailsArray = localbodyList.get(0);
				Message = (String) lbDetailsArray[0] + "s";

				Object ipName = lbDetailsArray[1];
				if (ipName != null && !"".equals(ipName.toString())) {
					Message += " of " + ipName;
				}

				Object vpName = lbDetailsArray[2];
				if (vpName != null && !"".equals(vpName.toString())) {
					Message += ", " + vpName;
				}

				Object stateName = lbDetailsArray[3];
				if (stateName != null && !"".equals(stateName.toString())) {
					Message += ", " + stateName;
				}
			}
			/*
			 * Object obj = query.uniqueResult(); if(obj != null){ lbName =
			 * (String) obj; }
			 */
			return Message;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	@Override
	public String getDistrictPanchayatNomenclatureNameWithStateName(Integer stateCode) {
		// TODO Auto-generated method stub
		String Message = "";
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder builder = new StringBuilder("select distinct local_body_tiers_setup.nomenclature_english,State_name_english from local_body_setup,local_body_tiers_setup,state where"
					+ " state.slc=local_body_setup.slc and state.isactive and local_body_setup.local_body_setup_code=local_body_tiers_setup.local_body_setup_code"
					+ " and local_body_setup.isactive and local_body_tiers_setup.isactive and local_body_setup.slc=:slc and local_body_type_code in(1,2,3)" + " and parent_tier_setup_code is null");
			Query query = session.createSQLQuery(builder.toString());
			query.setParameter("slc", stateCode);
			Object[] dpDetailsArray = (Object[]) query.uniqueResult();
			if (dpDetailsArray != null) {

				Object dpName = dpDetailsArray[0] + "s";
				if (dpName != null && !"".equals(dpName.toString())) {
					Message += dpName + " of ";
				}
				Object stateName = dpDetailsArray[1];
				if (dpName != null && !"".equals(dpName.toString())) {
					Message += stateName;
				}
			}
			/*
			 * Object obj = query.uniqueResult(); if(obj != null){ lbName =
			 * (String) obj; }
			 */

			return Message;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	@Override
	public List<PCACMappingforLBWard> getPCACMappingforLBWardDetails(Integer entityCode, char type) throws Exception {

		List<PCACMappingforLBWard> pcACMappingforLBWardList = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getPCACMappingforLBWardDetails");
			query.setParameter("entityCode", entityCode, Hibernate.INTEGER);
			query.setParameter("entityType", type, Hibernate.CHARACTER);
			pcACMappingforLBWardList = query.list();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return pcACMappingforLBWardList;

	}

	@Override
	public List<ViewUnMappedLocalBodies> getViewUnMappedLBDetails(Integer stateCode, Integer lbTypeCode) throws Exception {

		List<ViewUnMappedLocalBodies> viewUnMappedLBlist = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getViewUnMappedLBdetails");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER);
			viewUnMappedLBlist = query.list();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return viewUnMappedLBlist;
	}

	@Override
	public List<DistrictWiseInvalidatedVillage> getDistrictWiseInvalidatedVillage(Integer district_code) throws Exception {
		List<DistrictWiseInvalidatedVillage> districtWiseInvalidatedVillagelist = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getDistrictWiseInvalidatedVillage");
			query.setParameter("entity_code", district_code, Hibernate.INTEGER);
			districtWiseInvalidatedVillagelist = query.list();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return districtWiseInvalidatedVillagelist;
	}

	@Override
	public String getLBNameByTypeCode(Integer localBodyCode) {
		// TODO Auto-generated method stub
		String lbName = "";
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getLBHierarchy").setParameter("parentLBCode", localBodyCode, Hibernate.INTEGER);
			GetLBHierarchy obj = (GetLBHierarchy) query.uniqueResult();
			lbName = obj.getGet_lb_hierarchy_as_text_rpt();
		} catch (Exception e) {
			lbName = "";
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return lbName;
	}

	@Override
	public List<LocalBodyWiseWards> getWardsPerLBObjects(Integer statecode, Integer localbodyType, int limit, int offset, String search) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getLocalBodyWiseWards");
			query.setCacheable(true);
			query.setCacheMode(CacheMode.GET);
			query.setParameter("stateCode", statecode, Hibernate.INTEGER);
			query.setParameter("lbTypeCode", localbodyType, Hibernate.INTEGER);
			query.setParameter("limit", limit, Hibernate.INTEGER);
			query.setParameter("offset", offset, Hibernate.INTEGER);
			query.setParameter("search", search, Hibernate.STRING);

			//@SuppressWarnings("unchecked")
			List<LocalBodyWiseWards> wardsPerLocalBodies = query.list();
			return wardsPerLocalBodies;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	@Override
	public Object[] lbWardIsParentAndCount(Integer statecode, Integer localbodyType) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder builder = new StringBuilder("select parent, grandparent from lb_ward_is_parents_and_count(:state, :lbTypeCode)");
			Query query = session.createSQLQuery(builder.toString());
			query.setCacheable(true);
			query.setCacheMode(CacheMode.GET);
			query.setParameter("state", statecode);
			query.setParameter("lbTypeCode", localbodyType);

			Object[] wardsList = (Object[]) query.uniqueResult();

			return wardsList;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	@Override
	public BigInteger countWardsPerLBObjects(Integer statecode, Integer localbodyType, String search) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder builder = new StringBuilder("select count(1) from get_ward_count_fn(:stateCode, :lbTypeCode, :search, :limit, :offset)");
			Query query = session.createSQLQuery(builder.toString());
			query.setCacheable(true);
			query.setCacheMode(CacheMode.GET);
			query.setParameter("stateCode", statecode, Hibernate.INTEGER);
			query.setParameter("lbTypeCode", localbodyType, Hibernate.INTEGER);
			query.setParameter("limit", null, Hibernate.INTEGER);
			query.setParameter("offset", null, Hibernate.INTEGER);
			query.setParameter("search", search, Hibernate.STRING);
			BigInteger totalRecords = (BigInteger) query.uniqueResult();
			return totalRecords;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	@Override
	public List<DPwardConstituencyWiseVP> getDPwardConstituencyWiseVP(Integer state_code) throws Exception {
		List<DPwardConstituencyWiseVP> dpwardConstituencyWiseVPList = new ArrayList<DPwardConstituencyWiseVP>();
		dpwardConstituencyWiseVPList = null;

		Query query = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("dpWardConstituencyWiseVPBean").setParameter("state_code", state_code, Hibernate.INTEGER);
			dpwardConstituencyWiseVPList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return dpwardConstituencyWiseVPList;
	}

	@Override
	public Object[] getDistrictWiseDetails(Integer stateCode, Integer districtCode) throws Exception {
		Query query = null;
		Session session = null;
		Object[] objectArray = null;
		List<DistrictWiseDetail> districtWiseDetailList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("rptDistrictWiseDetailFn");
			query.setParameter("district_code", districtCode, Hibernate.INTEGER);
			districtWiseDetailList = query.list();
			if (districtWiseDetailList != null && !districtWiseDetailList.isEmpty()) {
				objectArray = new Object[3];
				objectArray[0] = districtWiseDetailList;
				objectArray[1] = stateDao.getStateNameEnglishbyStateCode(stateCode);
				objectArray[2] = districtDAO.getDistrictNameEnglishbyDistrictCode(districtCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return objectArray;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public String getLocalBodyName(Integer lbCode, Integer PlbCode) {
		Session session = null;
		String Message = "List of Mapped Villages of ";
		try {
			LocalBodyTable lbEntiity = new LocalBodyTable();
			
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(LocalBodyTable.class);
			criteria.add(Restrictions.eq("localBodyCode", lbCode));
			criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
			lbEntiity = (LocalBodyTable) criteria.uniqueResult();
			
			
			
			if (lbEntiity!=null) {
				Message += lbEntiity.getLocalBodyNameEnglish();
				if (PlbCode == 0) {
					PlbCode = lbEntiity.getParentLocalBodyCode();
				}
			}

			StringBuilder builder = new StringBuilder("SELECT nomenclature_english, local_body_name_english,parent_name,state_name from get_panchayats_detailesby_lb(:lblcCode)");
			Query query = session.createSQLQuery(builder.toString());
			query.setParameter("lblcCode", PlbCode);
			List<Object[]> localbodyList = query.list();
			if (!localbodyList.isEmpty()) {
				Object[] lbDetailsArray = localbodyList.get(0);
				Message += "(" + (String) lbDetailsArray[0] + ")";

				Object ipName = lbDetailsArray[1];
				if (ipName != null && !"".equals(ipName.toString())) {
					Message += " , " + ipName;
				}

				Object vpName = lbDetailsArray[2];
				if (vpName != null && !"".equals(vpName.toString())) {
					Message += ", " + vpName;
				}

				Object stateName = lbDetailsArray[3];
				if (stateName != null && !"".equals(stateName.toString())) {
					Message += ", " + stateName;
				}
			}

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return Message;
	}

	public Character getNextLevellbCode(Integer lbCode, Integer stateCode, String ctype, String clevel) {
		Session session = null;
		Query query = null;
		Character nextLevel = null;
		try {
			//List<LocalBodyDetails> localBodyDetailsList = localGovtBodyDAO.getGovtLocalBodyDetails(lbCode);

			session = sessionFactory.openSession();
			query = session.getNamedQuery("findChildDetail").setParameter("stateCode", stateCode).setParameter("type", ctype).setParameter("level", clevel);
			List<GetLocalGovtSetup> getLocalGovtSetupList = query.list();
			if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {
				nextLevel = getLocalGovtSetupList.get(0).getLevel();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return nextLevel;
	}

	@Override
	public List<ConsolidateReportLB> getConsolidatedRptForLBsCombined() throws Exception {
		Session session = null;
		Query query = null;
		List<ConsolidateReportLB> getConsolidatedRptForLBs = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getConsolidateReportLBnew");
			getConsolidatedRptForLBs = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getConsolidatedRptForLBs;
	}

	@Override
	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyStateCombined(int stateCode, char type, char level) throws Exception {
		Session session = null;
		Query query = null;
		List<GetLocalBodyListbyLBtypebyState> getLBList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLBListbyLBtypebyParentCbyStateCombined").setParameter("type", type, Hibernate.CHARACTER).setParameter("level", level, Hibernate.CHARACTER).setParameter("stateCode", stateCode, Hibernate.INTEGER);
			getLBList = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLBList;
	}

	@Override
	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyStateCombined(int stateCode, char type, char level, int parentLBCode) throws Exception {
		Session session = null;
		Query query = null;
		Integer parentCode = null;
		if (parentLBCode != 0 && parentLBCode != -1) {
			parentCode = parentLBCode;
		}
		List<GetLocalBodyListbyLBtypebyState> getLBList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLBListbyLBtypebyParentCbyStateCombinedCb").setParameter("type", type, Hibernate.CHARACTER).setParameter("level", level, Hibernate.CHARACTER).setParameter("stateCode", stateCode, Hibernate.INTEGER)
					.setParameter("parentLBCode", parentCode, Hibernate.INTEGER);
			getLBList = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLBList;
	}

	@Override
	public List<LocalBodyHistory> getLocalBodyHistoryReport(char localBodyNameEnglish, int localBodyCode) throws BaseAppException {
		// TODO Auto-generated method stub
		Session session = null;

		SQLQuery query = null;
		List<LocalBodyHistory> localBodyHistList = new ArrayList<LocalBodyHistory>();

		try {
			session = sessionFactory.openSession();

			query = session.createSQLQuery("select * from get_land_region_history_fn_for_citizen(?,?)");

			query.setParameter(0, localBodyNameEnglish);
			query.setParameter(1, localBodyCode);
			query.addEntity(LocalBodyHistory.class);
			localBodyHistList = query.list();

			return localBodyHistList;

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localBodyHistList;

	}

	public String getLbHierchicalDetail(Integer lbCodeOrlbTypeCode, Integer stateCode,boolean parenCodeflag) throws Exception {
		String Message = "";
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("select * from getLbHierchicalDetail(:lbCodeOrlbTypeCode");
			if (stateCode != null) {
				 queryBuild.append(",:stateCode ");
			}
			if(parenCodeflag){
				 queryBuild.append(" ,:parenCodeflag ");
			}
			queryBuild.append(")");
			Query query = session.createSQLQuery(queryBuild.toString());
			query.setParameter("lbCodeOrlbTypeCode", lbCodeOrlbTypeCode);
			if (stateCode != null) {
				query.setParameter("stateCode", stateCode);
			}
			if(parenCodeflag){
				query.setParameter("parenCodeflag", parenCodeflag);
			}
			Message = (String) query.uniqueResult();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return Message;
	}
	
	//@SuppressWarnings("unchecked")
	public List<LandRegionDetail> landRegionDetail(Character entity_type, Integer entity_code) throws Exception {

		Query query = null;
		Session session = sessionFactory.openSession();
		List<LandRegionDetail> result = null;
		try {
			query = session.getNamedQuery("getLandRegionDetail").setParameter("entity_type", entity_type, Hibernate.CHARACTER).setParameter("entity_code", entity_code, Hibernate.INTEGER);
			//long start = System.currentTimeMillis();
			result = query.list();
			
		}finally {
			  if (session != null) {
					session.flush();
					session.close();
				}
		}
		return result;
	}

	/*Added BY:
	  Pranav Tiwari
	  StateWise GIS Mapped Local Body
	   */
	
	
	@Override
	public List<ReportingStatewiseGISMappedLBEntity> getStatewiseGISMappedLocalBody() throws Exception {
		Session session = null;
		List<ReportingStatewiseGISMappedLBEntity> localBodyList = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("globalViewStateGISMappedLocalBody");
			localBodyList = query.list();
			
		} catch (HibernateException e) {
			
			LGDLogger.getLogger(ReportDAOImpl.class).error(e.toString());
			throw e;
			
		}finally {
			
			if(session!=null && session.isOpen()){
				session.close();
			}
			
		}
		
		return localBodyList;
	}
	
	/*Added BY:
	  Pranav Tiwari
	  Local Body Mapped with more then one districts
	  13-oct-2016 */
	
	
	@Override
	public List<LocalBodyMappedToDistrictsEntity> getLocalBodyMappedWithDistricts(Integer stateCode) throws Exception {
		Session session = null;
		List<LocalBodyMappedToDistrictsEntity> mappedLBlist = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("localBodyMappedToDistricts");
			query.setParameter("stateCode",stateCode);
			mappedLBlist = query.list();
		} catch (Exception e) {
			LGDLogger.getLogger(ReportDAOImpl.class).error(e.toString());
			throw e;
		}finally {
			if(session!=null && session.isOpen())
			session.close();
		}
		return mappedLBlist;
	}
	
	@Override
	public List<Object> getFreezedStatusList() {
		Session session = null;
		SQLQuery query = null;
		List<Object> list = new ArrayList<Object>();
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select state_code, state_name_english,count(dlc) as totalDistricts, "
					+ "count(case district_status when 'F' then 1 END) as frozenDistricts from state s, "
					+ "district d left outer join freeze_status_constituency f on d.district_code=f.district_code"
					+ " where s.slc=d.slc and s.isactive and d.isactive group by state_code,state_name_english order by"
					+ " state_name_english");
			list = query.list();
			return list;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}
	
	@Override
	public List<ReportDistwiseVillagandMappedGP> getDistwiseVillagandMappedGP(Integer districtCode)throws Exception{
		List<ReportDistwiseVillagandMappedGP> distwiseVillagandMappedGPList=null;
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.getNamedQuery("REPORT_DISTRICTWISE_LB_AND_MAPPED_VILLAGE");
			query.setParameter("districtCode", districtCode);
			distwiseVillagandMappedGPList=query.list();
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		finally {
			if (session != null && session.isOpen()) {
			session.close();
			}
		}
		return distwiseVillagandMappedGPList;
	}
	
	@Override
	public AttachmentMaster getUploadFileConfigurationDetails(Long fileMasterId) throws HibernateException{
		// TODO Auto-generated method stub
		log.info("reportDaoimpl.getUploadFileConfigurationDetails execution started.");
		Session session = null;
		AttachmentMaster attachmentMaster = new AttachmentMaster();
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(AttachmentMaster.class).add(Restrictions.eq("fileMasterId", fileMasterId));
			attachmentMaster = (AttachmentMaster) criteria.uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in reportDaoimpl.getUploadFileConfigurationDetails : ", e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				}
		}
		return attachmentMaster;	
	}

	@Override
	public List<ConsolidateReportForRuralLB> getConsolidatedRptForRuralLBsPES(String financialYear) throws Exception {
		Session session = null;
		Query query = null;
		List<ConsolidateReportForRuralLB> getConsolidatedRptForLBs = null;
		try {
			session = sessionFactory.openSession();
			String NameQuery="getConsolidateReportForRuralLBWithoutFinYearPES";
			if(financialYear!=null && !("").equals(financialYear.trim())){
				NameQuery="getConsolidateReportForRuralLBPES";	
			}
			query = session.getNamedQuery(NameQuery);
			if(financialYear!=null && !("").equals(financialYear.trim())){
				query.setParameter("financialYear",financialYear);	
			}
			
			getConsolidatedRptForLBs = query.list();
		} catch (Exception e) {
			session.disconnect();
			SessionFactory sessionFactory = session.getSessionFactory();
			sessionFactory.close();
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getConsolidatedRptForLBs;
	}

	@Override
	public String getDistrictPanchayatNomenclatureNameWithStateNamePES(Integer stateCode) {
		String Message = "";
		Session session = null;
		try {
			session = sessionFactory.openSession();
			/*StringBuilder builder = new StringBuilder("select local_body_tiers_setup.nomenclature_english,State_name_english from local_body_setup,local_body_tiers_setup,state where"
					+ " state.slc=local_body_setup.slc and state.isactive and local_body_setup.local_body_setup_code=local_body_tiers_setup.local_body_setup_code"
					+ " and local_body_setup.isactive and local_body_tiers_setup.isactive and local_body_setup.slc=:slc and local_body_type_code in(1,2,3)" + " and parent_tier_setup_code is null");*/
			Query query = session.createSQLQuery("select * from statewise_localbody_tier_setup_nomenclature_pes(:slc)");
			query.setParameter("slc", stateCode);
			Object[] dpDetailsArray = (Object[]) query.uniqueResult();
			if (dpDetailsArray != null) {

				Object dpName = dpDetailsArray[0] + "s";
				if (dpName != null && !"".equals(dpName.toString())) {
					Message += dpName + " of ";
				}
				Object stateName = dpDetailsArray[1];
				if (dpName != null && !"".equals(dpName.toString())) {
					Message += stateName;
				}
			}
			/*
			 * Object obj = query.uniqueResult(); if(obj != null){ lbName =
			 * (String) obj; }
			 */

			return Message;
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
	}

	@Override
	public String getLocalBodyNameByCodePES(Integer lbCode) {
		// TODO Auto-generated method stub
				String Message = "";
				Session session = null;
				try {
					session = sessionFactory.openSession();
					StringBuilder builder = new StringBuilder("SELECT nomenclature_english, local_body_name_english,parent_name,state_name from get_panchayats_detailesby_lb_pes(:lblcCode)");
					Query query = session.createSQLQuery(builder.toString());
					query.setParameter("lblcCode", lbCode);
					List<Object[]> localbodyList = query.list();
					if (!localbodyList.isEmpty()) {
						Object[] lbDetailsArray = localbodyList.get(0);
						Message = (String) lbDetailsArray[0] + "s";

						Object ipName = lbDetailsArray[1];
						if (ipName != null && !"".equals(ipName.toString())) {
							Message += " of " + ipName;
						}

						Object vpName = lbDetailsArray[2];
						if (vpName != null && !"".equals(vpName.toString())) {
							Message += ", " + vpName;
						}

						Object stateName = lbDetailsArray[3];
						if (stateName != null && !"".equals(stateName.toString())) {
							Message += ", " + stateName;
						}
					}
					/*
					 * Object obj = query.uniqueResult(); if(obj != null){ lbName =
					 * (String) obj; }
					 */
					return Message;
				} finally {
					if (session != null && session.isOpen()) {
						session.flush();
						session.close();
					}
				}
	}

	@Override
	public String getLocalBodyNamePES(Integer lbCode, Integer PlbCode) {
		Session session = null;
		String Message = "List of Mapped Villages of ";
		try {
			session = sessionFactory.openSession();
			StringBuilder builder = new StringBuilder("from Localbody where localBodyCode=:lblcCode and isactive=true");
			Query query = session.createQuery(builder.toString());
			query.setParameter("lblcCode", lbCode);
			List<Localbody> localbodyListVP = query.list();
			if (!localbodyListVP.isEmpty()) {
				Message += localbodyListVP.get(0).getLocalBodyNameEnglish();
				if (PlbCode == 0) {
					PlbCode = localbodyListVP.get(0).getParentlblc();
				}
			}

			builder = new StringBuilder("SELECT nomenclature_english, local_body_name_english,parent_name,state_name from get_panchayats_detailesby_lb_pes(:lblcCode)");
			query = session.createSQLQuery(builder.toString());
			query.setParameter("lblcCode", PlbCode);
			List<Object[]> localbodyList = query.list();
			if (!localbodyList.isEmpty()) {
				Object[] lbDetailsArray = localbodyList.get(0);
				Message += "(" + (String) lbDetailsArray[0] + ")";

				Object ipName = lbDetailsArray[1];
				if (ipName != null && !"".equals(ipName.toString())) {
					Message += " , " + ipName;
				}

				Object vpName = lbDetailsArray[2];
				if (vpName != null && !"".equals(vpName.toString())) {
					Message += ", " + vpName;
				}

				Object stateName = lbDetailsArray[3];
				if (stateName != null && !"".equals(stateName.toString())) {
					Message += ", " + stateName;
				}
			}

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}

		return Message;
	}

	@Override
	public List<MappedVillageByLBCodeBean> getMappedVillageByLBCodePES(int lbCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<MappedVillageByLBCodeBean> mappedVillageByLBCode = new ArrayList<MappedVillageByLBCodeBean>();
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select * from getMappedVillageByLBCode_pes(:lbCode)");
			criteria.setParameter("lbCode", lbCode, Hibernate.INTEGER);
			mappedVillageByLBCode = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mappedVillageByLBCode;
	}

	@Override
	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyFinYearPES(int stateCode, char type,
			char level, int parentLBCode, String financialYear) throws Exception {
		Session session = null;
		Query query = null;
		Integer parentCode = null;
		if(parentLBCode !=0 && parentLBCode != -1){
			parentCode = parentLBCode;
			
		}
		List<GetLocalBodyListbyLBtypebyState> getLBlist = null;
		try {
			
			session = sessionFactory.openSession();
			String NameQuery="getLBListbyLBtypebyParentCbyWithoutFinYearPES";
			if(financialYear!=null && !("").equals(financialYear.trim())){
				NameQuery="getLBListbyLBtypebyParentCbyFinYearPES";	
			}
			query = session.getNamedQuery(NameQuery);
			query.setParameter("type", type, Hibernate.CHARACTER);
			query.setParameter("level", level, Hibernate.CHARACTER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("parentLBCode", parentCode, Hibernate.INTEGER);
			if(financialYear!=null && !("").equals(financialYear.trim())){
				query.setParameter("financialYear",financialYear,Hibernate.CHARACTER);
			}
			
			getLBlist = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		
		return getLBlist;
	}
	
	@Override
	public List<ParentwiseChildDetials> getParentwiseChildDetils(Character parentType,Integer parentCode, Character category,Character level) throws Exception {
		Session session = null;
		Query query = null;
		List<ParentwiseChildDetials> parentwiseChildDetialsLsit = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("parentwiseChildDetailsFn");
			query.setParameter("parent_type", parentType, Hibernate.CHARACTER);
			query.setParameter("parent_code", parentCode, Hibernate.INTEGER);
			query.setParameter("category", category, Hibernate.CHARACTER);
			query.setParameter("level", level, Hibernate.CHARACTER);
			parentwiseChildDetialsLsit = query.list();
		} catch (Exception e) {
			log.error("Eror in Consolidate Report of LGD-->",e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return parentwiseChildDetialsLsit;
	}
	
	
	@Override
	public List<LandRegionDetail> getEntitywiseParentDetails(String entityType,Integer entityCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LandRegionDetail> landRegionDetailList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLandRegionDetail");
			query.setParameter("entity_type", entityType);
			query.setParameter("entity_code", entityCode);
			landRegionDetailList = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return landRegionDetailList;
	}
	
	
	@Override
	public String getSubdistictCensusCodebyVillageCode(Integer villageCode) throws Exception{
		Session session = null;
		String subdistrictCensus2011Code = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Village where vlc=:villageCode and isactive=true ");
			query.setParameter("villageCode", villageCode);
			List<Village> villageList=query.list();
			if(villageList!=null && !villageList.isEmpty()){
				Integer subdistrictCode=villageList.get(0).getTlc();
				query = session.createQuery("from Subdistrict where tlc=:subdistrictCode and isactive=true ");
				query.setParameter("subdistrictCode", subdistrictCode);
				List<Subdistrict> subdistrictList=query.list();
				if(subdistrictList!=null && !subdistrictList.isEmpty()){
					subdistrictCensus2011Code =subdistrictList.get(0).getCensus2011Code();
				}
			}
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrictCensus2011Code;
	}
	
	@Override
	public List<LBTypeDetailsWithCategory> buildLocalBodyHierarchyWithCategorywithCantonmentRPT(Integer setupCode, Integer setupVersion) throws HibernateException {
		Session session = null;
		List<LBTypeDetailsWithCategory> lbTypesDetails = new ArrayList<LBTypeDetailsWithCategory>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Dynamic_LBTYPE_Details_With_Cantonment_RPT");
			query.setParameter("setupCode", setupCode, Hibernate.INTEGER).setParameter("setupVersion", setupVersion, Hibernate.INTEGER);
			lbTypesDetails = query.list();
		} catch (HibernateException e) {
			// TODO: handle exception
			log.error("Error in LocalBodyDaoImpl.buildLocalBodyHierarchy : ", e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbTypesDetails;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LGDUpdationEntity> getLGDUpdation(Integer stateCode) {
		Session session = null;
		List<LGDUpdationEntity> lgdUpdationEntityList=null;
		try {
			session = sessionFactory.openSession();
			String namedQuery="GET_LGD_UPDATION_STATE";
			if(stateCode>0) {
				namedQuery="GET_LGD_UPDATION_DISTRICT";
			}
			
			Query query = session.getNamedQuery(namedQuery);
			if(stateCode>0) {
				query.setParameter("stateCode", stateCode);
			}
			
			lgdUpdationEntityList= query.list();
			
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdUpdationEntityList;
	}


}
