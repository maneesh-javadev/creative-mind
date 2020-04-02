package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.ConsolidateReportLBRPT;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.dao.VillageReportDao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class VillageReportDaoImpl implements VillageReportDao {
	
	private static final Logger log = Logger.getLogger(VillageReportDaoImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;

	/* Retreiving the state list from the database */
	@Override
	public List<State> getConsolidatedRptForVillage(Session session)
			throws Exception {
		Query query = null;
		List<State> getConsolidatedRptForVillage = null;
		try {
			query = session
					.createQuery("from State s where s.isactive = true order by stateNameEnglish");
			getConsolidatedRptForVillage = query.list();
		} catch (Exception e) {
			throw e;
		}
		return getConsolidatedRptForVillage;
	}

	/* Retreiving the district list from the database */
	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCodeGlobal(int stateCode,
			Session session) throws Exception {
		Query query = null;
		//System.out.println(" >> inside method - getDistrictListbyStateCodeGlobal");
		List<District> getDistrictList = null;
		try {
			getDistrictList = new ArrayList<District>();
			query = session
					.createQuery("from District d where d.state.statePK.stateCode=:stateCode and d.isactive=true order by districtCode");
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			getDistrictList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return getDistrictList;
	}

	/* Retreiving the District wise villages and their mapped Gram Panchayats */

	@SuppressWarnings("unchecked")
	public List<ConsolidateReportLBRPT> getConsolidatedReport(int sCode,
			int districtCode, Session session) throws Exception {

		Query query = null;
		List<ConsolidateReportLBRPT> reportList = null;
		try {
			/*System.out
					.println("Inside the VillageReportDaoImpl getConsolidatedReport method");*/
			reportList = new ArrayList<ConsolidateReportLBRPT>();

			String sql = "select c.subdistrict_name_english Subdistrict, d.village_code, d.village_name_english Village_Name,e.coverage_type as lbtype,	f.local_body_code,(select * "
					   + " from generate_lb_hierarchy(f.local_body_code)) Gram_Panchayat_Name from state a, district b, subdistrict c,village d left outer join (lb_covered_landregion e inner join "
					   + " localbody f on e.lb_covered_region_code=f.lb_covered_region_code and f.isactive='t' and f.local_body_type_code in (select lbt.local_body_type_code from local_body_type lbt "
					   + " where level = 'V' or category='U')) on d.VLC=e.LRLC and e.land_region_type='V' and e.isactive='t' where a.SLC = b.SLC and a.isactive = true and b.isactive='t' "
					   + " and b.DLC=c.DLC and c.isactive='t' and c.TLC=d.TLC and d.isactive='t' and b.district_code=:dCode "
					   + " order by a.state_name_english,b.district_name_english,c.subdistrict_name_english,d.village_name_english,d.village_code,f.local_body_name_english";
			query = session.createSQLQuery(sql);
		/*	query = session
					.createSQLQuery("select c.subdistrict_name_english Subdistrict, d.village_code, d.village_name_english Village_Name,e.coverage_type as lbtype," +
							"f.local_body_name_english Gram_Panchayat_Name from state a, district b, subdistrict c, village d left outer join lb_covered_landregion e on d.VLC=e.LRLC  " +
							"and e.land_region_type='V' and e.isactive='t' inner join localbody f on e.lb_covered_region_code=f.lb_covered_region_code and f.isactive='t' " +
							"and f.local_body_type_code in (select lbt.local_body_type_code from local_body_type lbt where level = 'V') where a.SLC = b.SLC and a.isactive = true and b.isactive='t' and b.DLC=c.DLC and c.isactive='t' and c.TLC=d.TLC and d.isactive='t' " +
							"and b.district_code=:dCode order by a.state_name_english,b.district_name_english,c.subdistrict_name_english,d.village_name_english,d.village_code,f.local_body_name_english");
		*/	
			query.setParameter("dCode", districtCode);

			reportList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return reportList;
	}

	/**
	 * @param districtId
	 * @return
	 * @author vinay
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getConsolidatedBlockPanchyatReport(Integer districtId) {
		// TODO Auto-generated method stub
		List<Object[]> reportList = new ArrayList<Object[]>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String queryBuilder = " select" +
								  " district_code," +
								  " district_name_english," +
								  " block_code," +
								  " block_name_english," +
								  " grampanchayat_code," +
								  " grampanchayat_name," +
								  " village_code," +
								  " village_name_english" +
								  " from get_districtwise_block_and_mapped_grampanchayats(:districtId)";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("districtId", districtId);
			reportList = query.list();
		}catch (Exception e) {
			// TODO: handle exception
			reportList = new ArrayList<Object[]>();
		} 
		finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return reportList;
	}

	/**
	 * @param stateCode
	 * @return
	 * @author vinay
	 */
	@Override
	public String getVillagePanchayatName(Integer stateCode) {
		// TODO Auto-generated method stub
		Session session = null;
		String villPanchyat = ""; 
		try {
			session = sessionFactory.openSession();
			Query criteria = session.getNamedQuery("getLocalGovSetupVPanchayat");
			criteria.setParameter("stateCode", stateCode);
			criteria.setParameter("category", 'P');
			GetLocalGovtSetup  localGovtSetup = (GetLocalGovtSetup) criteria.uniqueResult();
			if(localGovtSetup != null){
				villPanchyat = localGovtSetup.getNomenclatureEnglish();
			}	
		} catch (Exception e) {
			log.debug("Exception" + e);
			villPanchyat = "";
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return villPanchyat;

	}

	@Override
	public List<State> getNofnStateList() {
		Query query = null;
		Session session = null;
		List<State> stateList = null;
		session = sessionFactory.openSession();
		try {
			query = session
					.createSQLQuery("select s.state_code,s.state_name_english from  state s inner join nofn_states n on n.state_code=s.state_code where s.isactive = true order by s.state_name_english");
			stateList = query.list();
		} catch (Exception e) {
			throw e;
		}
		finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return stateList;
	}
}
