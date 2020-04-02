package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.LandRegion;
import in.nic.pes.lgd.bean.LbCoveredLandregion;
import in.nic.pes.lgd.bean.Localbody;

import java.util.List;

public interface LbCoveredLandregionDAO {
	
	public boolean save(LbCoveredLandregion lbCoveredLandregion)throws Exception; // NO_UCD (unused code)
	public List<LandRegion> getcoveredLandregionByLocalbodyCode(int localBody)throws Exception;
	public String getLandregionName(String query)throws Exception; // NO_UCD (unused code)
	public List<Localbody> getULBList(int stateCode);
}
