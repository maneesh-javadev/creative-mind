package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.GetEntitiesWithDisturbed;
import in.nic.pes.lgd.bean.GetEntitiesWithWanrning;
import in.nic.pes.lgd.bean.GetLocalbodyDisturbRegion;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.forms.ShiftVillageForm;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface DisturbedEntitiesService {

	public List<GetEntitiesWithWanrning> getEntitiesWithWarningFlag(Integer Userid, String type,String parentEntityType,Integer parententitycode) throws Exception;
	
	public List<GetEntitiesWithDisturbed> getEntitiesWithDisturbedFlag(Integer Userid, String type,String parentEntityType,Integer parententitycode) throws Exception;

	public abstract boolean unSetMapLandRegionFlag(int landRegionCode, int landRegionVersion) throws Exception;

// TODO Remove unused code found by UCDetector
// 	public List<Village> getUnMappedVillageList(int stateCode) throws Exception;

	public  List<Block> getBlockList(String villCode) throws Exception; // NO_UCD (unused code)
	
	public List<GetLocalbodyDisturbRegion> getMessagebyLBCode(int localbodycode) throws Exception;

	public abstract boolean assignDisturbedVillagestoBlock(ShiftVillageForm shiftVillageForm) throws Exception;

	public abstract List<Village> getBlockVillageDetailByBlockCode(String villageCodes) throws Exception;

	public List<CheckAuthorization> getBlockList(HttpSession httpSession) throws Exception; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<GetEntitiesWithWanrning> getEntitiesWithDisturbFlag(UserSelection selection ,String type,String parentEntityType);

}
