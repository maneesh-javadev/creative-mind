package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.CheckLocalBodyDraftState;
import in.nic.pes.lgd.bean.CheckLocalBodyFreezeStatus;
import in.nic.pes.lgd.bean.GetFreezeDataForLocalbody;
import in.nic.pes.lgd.bean.LBFreezeUnfreeze;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyListbyStateold;
import in.nic.pes.lgd.bean.ParentWiseLocalBodiesold;
import in.nic.pes.lgd.bean.StandardCodes;
import in.nic.pes.lgd.bean.VPDeNotifiedCoveraged;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillagePanchyatDeNotified;
import in.nic.pes.lgd.forms.LBFreezeForm;

import java.util.List;
import java.util.Map;

import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exception.BaseAppException;

/**
 * @author Kirandeep added on 1 Feb 2015 for stateFreeeze
 * 
 */

public interface StateDistrictFreezeDAO {

	/*
	 * public boolean freezeUnFreezeFromState(String list, Integer user_id,
	 * String ip, Character entityType) throws Exception;
	 */
	/* added by sunita on 16-07-2015 */

	public boolean freezeUnFreezeFromState(String list, Integer user_id, String ip, Character entityType, Character lbLrType);

	public List<Village> does_entity_child_in_draft_exist_fn(Integer dlc) throws Exception;

	public Map<String, Object> getAllAttributeForStateFreeze(Integer slc, Character lb_lr_type) throws Exception;

	/**
	 * Save Local Body Freeze/Un-freeze.
	 * 
	 * @author Ashish Dhupia on 15-02-2015
	 * 
	 */

	public List<StandardCodes> getListforLBFreezeUnfreeze(LBFreezeForm lbfreeze, char Status) throws BaseAppException;

	public List<StandardCodes> getlBcoverage(Integer entitycode) throws BaseAppException;

	public List<StandardCodes> getlblcCode(Integer entitycode) throws BaseAppException;

	public List<ParentWiseLocalBodiesold> getchildlocalbodiesByParentcodeold(int localbodyCode) throws Exception;

	public boolean freezeLocalBody(String allList) throws Exception;

	public boolean chechUncheckLocalBodyFreeze(int lblc) throws Exception;

	public List<LocalbodyListbyStateold> getExistingPanchayatListold(int lbTypeCode, int stateCode) throws Exception;

	public List<LocalbodyListbyStateold> getExistingPanchayatList(int lbTypeCode, int stateCode) throws Exception;

	/* addded by kirandeep for freeze/unfreeze pri */
	public boolean freezeUnFreezeFromStateForPri(String list, Integer user_id, String ip, Character entityType, Character lbLrType) throws Exception;

	/* addded by kirandeep for freeze/unfreeze pri */
	public List<CheckLocalBodyDraftState> doesLbChildInDraftExist(Integer dlc, Character lbLrType) throws BaseAppException;

	public List<CheckLocalBodyFreezeStatus> doesLbChildInFreezExist(Integer dlc, Character lbLrType) throws BaseAppException;

	public boolean checkSaveStateStatus(Integer stateId, Character lb_lr_type) throws BaseAppException;

	public List<Object[]> getNomenclature(Integer localbodyCode) throws Exception;

	/*
	 * @author - Anchal Todariya
	 * 
	 * @reason - to denotify gram panchyat usecase
	 */

	public List<VillagePanchyatDeNotified> getListforLBtoDenotify(Integer localbodyCode) throws Exception;

	public Integer getDenotifyVPCount(Integer tranId) throws Exception;

	public List<VillagePanchyatDeNotified> getListforULBGPtoDenotify(Integer localbodyCode) throws Exception;

	public List<VillagePanchyatDeNotified> getListforRLBGPtoDenotify(Integer localbodyCode) throws Exception;

	public List<VPDeNotifiedCoveraged> getListforCoveraged(int localBodyCode) throws Exception;

	public String setListforULBRLBDenotify(LBFreezeForm lbfreeze, Long userId, String fileLoc) throws Exception;

	public String getTransactionDescription(String tranId) throws Exception;

	boolean saveDataInMap(String fetchData, List<AttachedFilesForm> attachedList) throws Exception;

	public List<String> getRLBS(Integer tranId) throws Exception;

	/*
	 * Added by Anchal Todariya on 25-03-2015
	 */
	public List<LocalbodyListbyState> getExistingPanchayatListoldForDistrict(int lbTypeCode, int stateCode) throws Exception;

	public List<LocalbodyListbyState> getExistingPanchayatListForDistrict(int lbTypeCode, int stateCode) throws Exception;

	/**
	 * Added by kirandeep for locabody validation
	 * 
	 * @param stateCode
	 * @param lbType
	 * @return
	 * @throws BaseAppException
	 */

	public boolean getStatusOfLocabodyTypeFreezeUnfreze(Integer stateCode, String lbType) throws BaseAppException;

	/**
	 * Added by kirandeep for localbody freeze validations if parent freeze or
	 * not
	 * 
	 * @param lblc
	 * @param lbType
	 * @return
	 * @throws Exception
	 */
	public boolean checkParentStatus(int lblc, Character lbType) throws Exception;

	/**
	 * @author Maneesh Kumar 01-July-2015
	 * @param localbodyCode
	 * @param lbTypeCode
	 * @param stateCode
	 * @return
	 * @throws Exception
	 */
	public List<GetFreezeDataForLocalbody> getFreezeLocalbodybyState(Integer parentLocalbodyCode, Integer lbTypeCode, Integer stateCode,Integer districtCode) throws Exception;

	
	public boolean saveLBFreezePopulation(List<LBFreezeUnfreeze> freezeUnfreeze);
	
	public List<GetFreezeDataForLocalbody> getFreezeLocalbodybyStatePopulation(Integer parentLocalbodyCode, Integer lbTypeCode, Integer stateCode,Integer districtCode) throws Exception;
}
