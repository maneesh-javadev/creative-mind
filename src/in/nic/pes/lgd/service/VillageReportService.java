package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.ConsolidateReportLBRPT;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.State;

import java.util.List;

public interface VillageReportService {

	public List<State> getConsolidatedRptForVillage() throws Exception;

	public List<District> getDistrictListbyStateCodeGlobal(int stateCode)
			throws Exception;

	public List<ConsolidateReportLBRPT> getConsolidatedReport(int sCode,
			int vCode) throws Exception;
	
	/**
	 * @param districtId
	 * @return
	 * @author vinay yadav
	 */
	public List<Object[]> getConsolidatedBlockPanchyatReport(Integer districtId);
	
	/**
	 * @param stateCode
	 * @return
	 * @author vinay yadav
	 */
	public String getVillagePanchayatName(Integer stateCode);
	
	public List<State> getNofnStateList();
}
