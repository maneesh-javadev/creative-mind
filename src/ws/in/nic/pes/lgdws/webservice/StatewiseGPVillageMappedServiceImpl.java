package  ws.in.nic.pes.lgdws.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StatewiseGPVillageMappedServiceImpl implements StatewiseGPVillageMappedService{

	@Autowired
	private  StatewiseGPVillageMappedDAO statewiseGPVillageMappedDAOImpl;
	
	@Override
	public StateWiseGPVillageMappedEntity getStateWiseGPtoVillageMapping(Integer stateCode,String LBGroup){
		return statewiseGPVillageMappedDAOImpl.getStateWiseGPtoVillageMapping(stateCode,LBGroup);
	}

}
