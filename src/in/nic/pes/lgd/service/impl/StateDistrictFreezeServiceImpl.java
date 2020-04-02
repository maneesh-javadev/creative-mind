package in.nic.pes.lgd.service.impl;

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
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.StateDistrictFreezeDAO;
import in.nic.pes.lgd.forms.LBFreezeForm;
import in.nic.pes.lgd.service.StateDistrictFreezeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exception.BaseAppException;

/**
 * @author Kirandeep added on 1 Feb 2015 for stateFreeeze
 * 
 */

public class StateDistrictFreezeServiceImpl implements StateDistrictFreezeService {

	@Autowired
	StateDistrictFreezeDAO stateDistrictFDAO;

	@Autowired
	DistrictDAO districtDAO;

	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;

	/*
	 * @Override public boolean freezeUnFreezeFromState(String list, Integer
	 * user_id, String ip, Character entityType) throws Exception { return
	 * stateDistrictFDAO.freezeUnFreezeFromState(list, user_id, ip, entityType);
	 * }
	 */
	/* added by sunita on 16-07-2015 */
	@Override
	public boolean freezeUnFreezeFromState(String list, Integer user_id, String ip, Character entityType, Character lbLrType) throws Exception {
		return stateDistrictFDAO.freezeUnFreezeFromState(list, user_id, ip, entityType, lbLrType);
	}

	@Override
	public List<Village> does_entity_child_in_draft_exist_fn(Integer dlc) throws Exception {
		return stateDistrictFDAO.does_entity_child_in_draft_exist_fn(dlc);
	}

	@Override
	public Map<String, Object> getAllAttributeForStateFreeze(Integer slc, Character lb_lr_type) throws Exception {
		return stateDistrictFDAO.getAllAttributeForStateFreeze(slc, lb_lr_type);
	}

	/**
	 * Save Local Body Freeze/Un-freeze.
	 * 
	 * @author Ashish Dhupia on 15-02-2015
	 * 
	 */

	@Override
	public List<StandardCodes> getListforLBFreezeUnfreeze(LBFreezeForm lbfreeze, char status) throws BaseAppException {
		List<StandardCodes> standardCodesList = new ArrayList<StandardCodes>();
		standardCodesList = stateDistrictFDAO.getListforLBFreezeUnfreeze(lbfreeze, status);
		/*
		 * List<StandardCodeDataForm> standardCodeDataList = new
		 * ArrayList<StandardCodeDataForm>(); StandardCodeDataForm
		 * standardCodeDataForm = null; int i = 0; for (StandardCodes
		 * standardCodes : standardCodesList) { standardCodeDataForm = new
		 * StandardCodeDataForm(); if (standardCodes.getCensus2011Code() !=
		 * null) {
		 * standardCodeDataForm.setCensus2011Code(String.valueOf(standardCodes
		 * .getCensus2011Code()));
		 * standardCodeDataForm.setCensus2011Codech(String
		 * .valueOf(standardCodes.getCensus2011Code())); }
		 * standardCodeDataForm.setEntityCode(standardCodes.getEntityCode());
		 * standardCodeDataForm
		 * .setEntityNameEnglish(standardCodes.getEntityNameEnglish());
		 * standardCodeDataForm
		 * .setEntityNameLocal(standardCodes.getEntityNameLocal());
		 * standardCodeDataForm
		 * .setEntityNameLocalch(standardCodes.getEntityNameLocal());
		 * standardCodeDataForm.setSsCode(standardCodes.getSsCode());
		 * standardCodeDataForm.setSsCodech(standardCodes.getSsCode());
		 * standardCodeDataList.add(i, standardCodeDataForm); i++;
		 * 
		 * }
		 */
		return standardCodesList;

	}

	@Override
	public List<StandardCodes> getlBcoverage(Integer entitycode) throws BaseAppException {
		return stateDistrictFDAO.getlBcoverage(entitycode);
	}

	@Override
	public List<StandardCodes> getlblcCode(Integer lblc) throws BaseAppException {
		return stateDistrictFDAO.getlblcCode(lblc);
	}

	@Override
	public List<ParentWiseLocalBodiesold> getchildlocalbodiesByParentcodeold(int localBodyCode) throws Exception {
		return stateDistrictFDAO.getchildlocalbodiesByParentcodeold(localBodyCode);
	}

	@Override
	public boolean freezeLocalBody(String allList) throws Exception {
		return stateDistrictFDAO.freezeLocalBody(allList);
	}

	@Override
	public boolean chechUncheckLocalBodyFreeze(int lblc) throws Exception {
		return stateDistrictFDAO.chechUncheckLocalBodyFreeze(lblc);
	}

	/*
	 * public List<LocalbodyListbyStateold> getExistingPanchayatListold(int
	 * lbTypeCode,int stateCode) throws Exception { return
	 * stateDistrictFDAO.getExistingPanchayatListold(lbTypeCode,stateCode); }
	 */

	@Override
	public List<LocalbodyListbyStateold> getExistingPanchayatListold(int stateCode) throws Exception {
		List<LocalbodyListbyStateold> localbodywholeList = new ArrayList<LocalbodyListbyStateold>();

		List<LocalbodyListbyStateold> localbodyList = stateDistrictFDAO.getExistingPanchayatListold(2, stateCode);
		localbodywholeList.addAll(localbodyList);

		return localbodywholeList;
	}

	@Override
	public List<LocalbodyListbyStateold> getExistingLBListbyStateandCategory(int stateCode, char category) throws Exception {
		List<LocalbodyListbyStateold> localbodywholeList = new ArrayList<LocalbodyListbyStateold>();
		String localbodyCodes = localGovtBodyDAO.getLocalBodyTypeCode1(category);
		String[] localbodyCodeList = localbodyCodes.split(",");

		for (int i = 0; i < localbodyCodeList.length; i++) {
			if (localbodyCodeList[i] != null) {
				int lbTypeCode = Integer.parseInt(localbodyCodeList[i].toString());
				List<LocalbodyListbyStateold> localbodyList = stateDistrictFDAO.getExistingPanchayatList(lbTypeCode, stateCode);
				localbodywholeList.addAll(localbodyList);
			}
		}

		return localbodywholeList;
	}

	/* addded by kirandeep for freeze/unfreeze pri */
	@Override
	public boolean freezeUnFreezeFromStateForPri(String list, Integer user_id, String ip, Character entityType, Character lbLrType) throws Exception {
		return stateDistrictFDAO.freezeUnFreezeFromStateForPri(list, user_id, ip, entityType, lbLrType);
	}

	/* addded by kirandeep for freeze/unfreeze pri */
	@Override
	public List<CheckLocalBodyDraftState> doesLbChildInDraftExist(Integer dlc, Character lbLrType) throws BaseAppException {
		return stateDistrictFDAO.doesLbChildInDraftExist(dlc, lbLrType);
	}

	@Override
	public List<CheckLocalBodyFreezeStatus> doesLbChildInFreezExist(Integer dlc, Character lbLrType) throws BaseAppException {
		return stateDistrictFDAO.doesLbChildInFreezExist(dlc, lbLrType);
	}

	@Override
	public boolean checkSaveStateStatus(Integer stateId, Character lb_lr_type) throws BaseAppException {
		return stateDistrictFDAO.checkSaveStateStatus(stateId, lb_lr_type);
	}

	@Override
	public List<Object[]> getNomenclature(Integer localbodyCode) throws Exception {
		return stateDistrictFDAO.getNomenclature(localbodyCode);
	}

	public List<VillagePanchyatDeNotified> getListforLBtoDenotify(Integer localbodyCode) throws Exception {
		return stateDistrictFDAO.getListforLBtoDenotify(localbodyCode);
	}

	public List<VillagePanchyatDeNotified> getListforULBGPtoDenotify(Integer localbodyCode) throws Exception {
		return stateDistrictFDAO.getListforULBGPtoDenotify(localbodyCode);
	}

	public List<VillagePanchyatDeNotified> getListforRLBGPtoDenotify(Integer localbodyCode) throws Exception {
		return stateDistrictFDAO.getListforRLBGPtoDenotify(localbodyCode);
	}

	public Integer getDenotifyVPCount(Integer tranId) throws Exception {
		return stateDistrictFDAO.getDenotifyVPCount(tranId);
	}

	@Override
	public List<VPDeNotifiedCoveraged> getListforCoveraged(int localBodyCode) throws Exception {
		return stateDistrictFDAO.getListforCoveraged(localBodyCode);
	}

	@Override
	public String setListforULBRLBDenotify(LBFreezeForm lbfreeze, Long userId, String fileLoc) throws Exception {
		return stateDistrictFDAO.setListforULBRLBDenotify(lbfreeze, userId, fileLoc);
	}

	public String getTransactionDescription(String tranId) throws Exception {
		return stateDistrictFDAO.getTransactionDescription(tranId);
	}

	public boolean saveDataInMap(String fetchData, List<AttachedFilesForm> validFileGovtUpload) throws Exception {
		return stateDistrictFDAO.saveDataInMap(fetchData, validFileGovtUpload);
	}

	@Override
	public List<String> getRLBS(Integer tranId) throws Exception {
		return stateDistrictFDAO.getRLBS(tranId);
	}

	/*
	 * Added by Anchal Todariya on 25-03-2015
	 */
	@Override
	public List<LocalbodyListbyState> getExistingLBListbyStateandCategoryForDistrict(int districtCode, char category) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		String localbodyCodes = localGovtBodyDAO.getLocalBodyTypeCode1(category);
		String[] localbodyCodeList = localbodyCodes.split(",");

		for (int i = 0; i < localbodyCodeList.length; i++) {
			if (localbodyCodeList[i] != null) {
				int lbTypeCode = Integer.parseInt(localbodyCodeList[i].toString());
				List<LocalbodyListbyState> localbodyList = stateDistrictFDAO.getExistingPanchayatListForDistrict(lbTypeCode, districtCode);
				localbodywholeList.addAll(localbodyList);
			}
		}

		return localbodywholeList;
	}

	@Override
	public List<LocalbodyListbyState> getExistingPanchayatListoldForDistrict(int districtCode) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();

		List<LocalbodyListbyState> localbodyList = stateDistrictFDAO.getExistingPanchayatListoldForDistrict(2, districtCode);
		localbodywholeList.addAll(localbodyList);

		return localbodywholeList;
	}

	/**
	 * Added by kirandeep for locabody validation
	 * 
	 * @param stateCode
	 * @param lbType
	 * @return
	 * @throws BaseAppException
	 */
	@Override
	public boolean getStatusOfLocabodyTypeFreezeUnfreze(Integer stateCode, String lbType) throws BaseAppException {

		return stateDistrictFDAO.getStatusOfLocabodyTypeFreezeUnfreze(stateCode, lbType);
	}

	/**
	 * Added by kirandeep for localbody freeze validations if parent freeze or
	 * not
	 * 
	 * @param lblc
	 * @param lbType
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkParentStatus(int lblc, Character lbType) throws Exception {
		return stateDistrictFDAO.checkParentStatus(lblc, lbType);
	}

	/**
	 * @author Maneesh Kumar 01-July-2015
	 */
	@Override
	public List<GetFreezeDataForLocalbody> getFreezeLocalbodybyState(Integer parentLocalbodyCode, Integer lbTypeCode, Integer stateCode,Integer districtCode) throws Exception {
		return stateDistrictFDAO.getFreezeLocalbodybyState(parentLocalbodyCode, lbTypeCode, stateCode,districtCode);
	}

	

	@Override
	public boolean saveLBFreezePopulation(List<LBFreezeUnfreeze> freezeUnfreeze) {
		// TODO Auto-generated method stub
		return stateDistrictFDAO.saveLBFreezePopulation(freezeUnfreeze);
	}

	@Override
	public List<GetFreezeDataForLocalbody> getFreezeLocalbodybyStatePopulation(Integer parentLocalbodyCode,
			Integer lbTypeCode, Integer stateCode, Integer districtCode) throws Exception {
		// TODO Auto-generated method stub
		return stateDistrictFDAO.getFreezeLocalbodybyStatePopulation(parentLocalbodyCode, lbTypeCode, stateCode, districtCode);
	}
}
