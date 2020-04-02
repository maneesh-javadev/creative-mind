package in.nic.pes.lgd.draft.service;

import java.util.List;

import in.nic.pes.lgd.bean.Village;

public interface DraftVillageService {
	
	public List<Village> getDraftVillageList(String subdistrictCodes,String villagesFull)throws Exception;
	public List<Village> getEditDraftVillageList(String subdistrictCodes, Integer paramCode,boolean isContribute) throws Exception;
	
}
