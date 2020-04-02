package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.LandRegion;
import in.nic.pes.lgd.bean.Localbody;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Repository
public interface LbCoveredLandregionService {
	

	public List<LandRegion> getCoveredLandRegionByULBList(int localBodyCode)throws Exception; // NO_UCD (unused code)
	/**
	 * 
	 */
	public List<Localbody> getULBList(int stateCode) throws Exception; // NO_UCD (unused code)

}
