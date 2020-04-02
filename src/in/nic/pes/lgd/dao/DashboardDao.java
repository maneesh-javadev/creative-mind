package in.nic.pes.lgd.dao;

import java.util.List;

import in.nic.pes.lgd.bean.GETDashboardEntityCount;
import in.nic.pes.lgd.bean.GETDashboardLBDetails;
import in.nic.pes.lgd.bean.GETDashboardVillageDetails;
import in.nic.pes.lgd.bean.GetDashboardChangeEntityCount;
import in.nic.pes.lgd.bean.GetDashboardChangeEntityDetail;
import in.nic.pes.lgd.forms.DashboardForm;
import ws.in.nic.pes.lgdws.entity.WSState;

public interface DashboardDao {
	
	DashboardForm getDashboardDetails(Integer stateCode,Character userType);
	List<GetDashboardChangeEntityCount> getEntityDetailsList(Integer stateCode, Character userType,String finYear);
	List<GETDashboardVillageDetails> getVillageDetails(Integer stateCode, String flag);
	List<GETDashboardEntityCount> getAllEntityCountList();
	List<GETDashboardLBDetails> getLocalbodyDetails(Integer stateCode, String flag);
	List<WSState> getStateList();
	List<GetDashboardChangeEntityDetail> getEntityChangeDetail(Integer stateCode,String flag,Character userType,String finYear);

}
