package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.CitizenFeedback;
import in.nic.pes.lgd.dao.CititzenSectionDAO;
import in.nic.pes.lgd.service.CitizenSectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitizenSectionServiceImpl implements CitizenSectionService {

	//private static final Logger log=Logger.getLogger(CitizenSectionServiceImpl.class);
	
	@Autowired
	CititzenSectionDAO cititzenSectionDAO;
	
	@Override
	public Boolean saveFeedback(CitizenFeedback citizenFeedback) {
		return cititzenSectionDAO.saveFeedback(citizenFeedback);
	}

}
