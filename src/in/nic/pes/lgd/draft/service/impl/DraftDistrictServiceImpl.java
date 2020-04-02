package in.nic.pes.lgd.draft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.draft.dao.DraftDistrictDao;
import in.nic.pes.lgd.draft.service.DraftDistrictService;

@Service 
public class DraftDistrictServiceImpl implements DraftDistrictService {

	@Autowired
	DraftDistrictDao draftDistrictDao;

	@Override
	public List<District> getDraftDistrictList(Integer entityCode, String entityType) throws Exception {
		return draftDistrictDao.getDraftDistrictList(entityCode, entityType);
	}
	
	

}
