package in.nic.pes.lgd.draft.dao;

import java.util.List;

import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;

public interface DraftVillageDao {
	
	public List<Village> getDraftVillageList(List<Integer> subdistrictCodeList, List<Integer> villageListFull) throws Exception;
	public List<Village> getEditDraftVillageList(List<Integer> subdistrictCodeList, Integer paramCode,boolean isContribute) throws Exception;
	public List<Village> getCoverageVillageDetails(DraftSubdistrictForm draftSubdistrictForm)throws Exception;
	

}
