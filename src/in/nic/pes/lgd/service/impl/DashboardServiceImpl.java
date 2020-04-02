package in.nic.pes.lgd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nic.pes.lgd.bean.GETDashboardEntityCount;
import in.nic.pes.lgd.bean.GETDashboardLBDetails;
import in.nic.pes.lgd.bean.GETDashboardVillageDetails;
import in.nic.pes.lgd.bean.GetDashboardChangeEntityCount;
import in.nic.pes.lgd.bean.GetDashboardChangeEntityDetail;
import in.nic.pes.lgd.dao.DashboardDao;
import in.nic.pes.lgd.forms.DashboardForm;
import in.nic.pes.lgd.service.DashboardService;
import ws.in.nic.pes.lgdws.entity.WSState;

@Service
public class DashboardServiceImpl implements DashboardService {

	
	@Autowired
	DashboardDao dashboardDao;
	
	@Override
	public DashboardForm getDashboardDetails(Integer stateCode, Character userType) {
		return dashboardDao.getDashboardDetails(stateCode, userType);
	}

	@Override
	public List<GetDashboardChangeEntityCount> getEntityDetailsList(Integer stateCode, Character userType, String finYear) {
		return dashboardDao.getEntityDetailsList(stateCode, userType, finYear);
	}

	@Override
	public List<GETDashboardVillageDetails> getVillageDetails(Integer stateCode, String flag) {
		return dashboardDao.getVillageDetails(stateCode, flag);
	}

	@Override
	public List<GETDashboardEntityCount> getAllEntityCountList() {
		return dashboardDao.getAllEntityCountList();
	}

	@Override
	public List<GETDashboardLBDetails> getLocalbodyDetails(Integer stateCode, String flag) {
		return dashboardDao.getLocalbodyDetails(stateCode, flag);
	}

	@Override
	public List<WSState> getStateList() {
		return dashboardDao.getStateList();
	}

	@Override
	public List<GetDashboardChangeEntityDetail> getEntityChangeDetail(Integer stateCode, String flag,Character userType, String finYear) {
		return dashboardDao.getEntityChangeDetail(stateCode, flag, userType, finYear);
	}

	

}
