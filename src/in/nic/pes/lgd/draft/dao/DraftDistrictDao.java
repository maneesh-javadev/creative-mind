package in.nic.pes.lgd.draft.dao;

import java.util.List;

import in.nic.pes.lgd.bean.District;

public interface DraftDistrictDao {
	public List<District> getDraftDistrictList(Integer entityCode,String entityType) throws Exception;
	public String getDistrictNameEng(Integer districtCode)throws Exception;
}
