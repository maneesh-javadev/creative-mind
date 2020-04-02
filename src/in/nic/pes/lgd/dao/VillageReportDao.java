package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.ConsolidateReportLBRPT;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.State;

import java.util.List;

import org.hibernate.Session;

public interface VillageReportDao {

	public List<State> getConsolidatedRptForVillage(Session session)
			throws Exception;

	public List<District> getDistrictListbyStateCodeGlobal(int stateCode,
			Session session) throws Exception;

	public List<ConsolidateReportLBRPT> getConsolidatedReport(int sCode,
			int vCode, Session session) throws Exception;
	
	/**
	 * @param districtId
	 * @return
	 * @author vinay
	 */
	public List<Object[]> getConsolidatedBlockPanchyatReport(Integer districtId);
	
	/**
	 * @param stateCode
	 * @return
	 * @author vinay
	 */
	public String getVillagePanchayatName(Integer stateCode);
	
	
	public List<State> getNofnStateList();
}
