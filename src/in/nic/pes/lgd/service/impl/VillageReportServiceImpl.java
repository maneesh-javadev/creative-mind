package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.ConsolidateReportLBRPT;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.dao.VillageReportDao;
import in.nic.pes.lgd.service.VillageReportService;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VillageReportServiceImpl implements VillageReportService {

	@Autowired
	VillageReportDao villageReportDao;

	@Autowired
	SessionFactory sessionFactory;

	/* Retreiving the state list from the database */
	@Override
	public List<State> getConsolidatedRptForVillage() throws Exception {
		Session session = null;
		List<State> getConsolidatedRptForLBList = new ArrayList<State>();

		try {
			session = sessionFactory.openSession();
			getConsolidatedRptForLBList = villageReportDao
					.getConsolidatedRptForVillage(session);
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return getConsolidatedRptForLBList;
	}

	/* Retreiving the district list from the database */
	@Override
	public List<District> getDistrictListbyStateCodeGlobal(int stateCode)
			throws Exception {
		Session session = null;
		List<District> getDistrictlist = new ArrayList<District>();
		try {
			session = sessionFactory.openSession();
			getDistrictlist = villageReportDao
					.getDistrictListbyStateCodeGlobal(stateCode, session);
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return getDistrictlist;
	}

	/* Retreiving the District wise villages and their mapped Gram Panchayats */

	@Override
	public List<ConsolidateReportLBRPT> getConsolidatedReport(int sCode,
			int vCode) throws Exception {
		Session session = null;
		List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();
		try {
			session = sessionFactory.openSession();
			reportList = villageReportDao.getConsolidatedReport(sCode, vCode,
					session);
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return reportList;
	}

	/**
	 * @param districtId
	 * @return
	 * @author vinay
	 */
	@Override
	public List<Object[]> getConsolidatedBlockPanchyatReport(Integer districtId) {
		// TODO Auto-generated method stub
		return villageReportDao.getConsolidatedBlockPanchyatReport(districtId);
	}

	/**
	 * @param stateCode
	 * @return
	 * @author vinay
	 */
	@Override
	public String getVillagePanchayatName(Integer stateCode) {
		// TODO Auto-generated method stub
		return villageReportDao.getVillagePanchayatName(stateCode);
	}

	@Override
	public List<State> getNofnStateList() {
		// TODO Auto-generated method stub
		return villageReportDao.getNofnStateList();
	}
}
