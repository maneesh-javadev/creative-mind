package in.nic.pes.lgd.draft.service;

import java.util.List;

import in.nic.pes.lgd.bean.District;


public interface DraftDistrictService {
	public List<District> getDraftDistrictList(Integer entityCode,String entityType) throws Exception;

}
