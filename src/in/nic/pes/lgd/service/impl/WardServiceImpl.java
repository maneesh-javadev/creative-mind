package in.nic.pes.lgd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;

import in.nic.pes.lgd.bean.DRAFTWARDCOVERAGE;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.WardCoverageDetail;
import in.nic.pes.lgd.bean.localbodywardtemp;
import in.nic.pes.lgd.dao.WardDao;
import in.nic.pes.lgd.forms.WardForm;
import in.nic.pes.lgd.service.WardService;

@Service
public class WardServiceImpl implements WardService {

	@Autowired
	private WardDao wardDao;
	
	@Override
	public List<LBTypeHierarchy> getLBHierarchyList(Integer stateCode,String PANCHAYAT_TYPE) throws Exception {
		return wardDao.getLBHierarchyList(stateCode, PANCHAYAT_TYPE);
	}

	@Override
	public boolean saveTempWards(List<localbodywardtemp> insertListWard,List<localbodywardtemp> updateListWard,List<localbodywardtemp> deleteListWard) throws Exception {
		return wardDao.saveTempWards(insertListWard, updateListWard, deleteListWard);
	}

	@Override
	public boolean publishWards(WardForm wardForm) throws Exception {
		return wardDao.publishWards(wardForm);
	}

	@Override
	public List<localbodywardtemp> getLocalbodyWardTempList(Integer lblc) throws Exception {
		return wardDao.getLocalbodyWardTempList(lblc);
	}

	@Override
	public List<LocalbodyWard> getLocalbodyWardList(Integer lblc) throws Exception {
		return wardDao.getLocalbodyWardList(lblc);
	}

	@Override
	public List<LBTypeDetails> getLBTypeDetailsList(Integer stateCode, String PANCHAYAT_TYPE) throws Exception {
		return wardDao.getLBTypeDetailsList(stateCode, PANCHAYAT_TYPE);
	}

	@Override
	public Integer getlblcCodeofLocalbody(Integer localBodyCode) throws Exception {
		return wardDao.getlblcCodeofLocalbody(localBodyCode);
	}

	@Override
	public List<LocalbodyWard> getwardDetail(Integer wardCode) throws Exception {
		return wardDao.getwardDetail(wardCode);
	}

	@Override
	public Object[] getLocalbodyWardListbyLoclbodyCode(Integer localBodyCode) throws Exception {
		return wardDao.getLocalbodyWardListbyLoclbodyCode(localBodyCode);
	}

	@Override
	public boolean checkLocalbodtSetupbyState(Integer stateCode,String PANCHAYAT_TYPE)throws Exception{
		return wardDao.checkLocalbodtSetupbyState(stateCode, PANCHAYAT_TYPE);
	}

	@Override
	public List<LocalbodyWard> getWardDetails(Integer lblc) throws Exception {
		return wardDao.getWardDetails(lblc);
	}
	
	/**
	 * These Methods add for 'Add n Manage Ward Covergae '
	 * 
	 * @author Maneesh Kumar
	 * @since 01DEc2014
	 */

	public Object[] getWardCoverageDetail(Integer lblc, Integer wardCode, String selLevel,boolean isUrban) throws Exception{
		return wardDao.getWardCoverageDetail(lblc, wardCode, selLevel,isUrban);
	}

	@Override
	public List<WardCoverageDetail> getWardCoverageDetailbyLevel(Integer lblc, Integer wardCode, Character level, String entityCodeList, boolean wardCovergaeFlag)throws Exception {
		return wardDao.getWardCoverageDetailbyLevel(lblc, wardCode, level, entityCodeList, wardCovergaeFlag);
	}

	@Override
	public boolean saveWardCoverageDetail(Integer wardCode, String selLevel, Object[] wardCoverageDistrictList, Object[] wardCoverageSubdistrictList, Object[] wardCoverageVillageList,Integer userId,String category)throws Exception {
		return wardDao.saveWardCoverageDetail(wardCode, selLevel, wardCoverageDistrictList, wardCoverageSubdistrictList, wardCoverageVillageList,userId,category);
	}

	@Override
	public Object[] getBasicDetailofLocalbody(Integer localBodyCode,String PANCHAYAT_TYPE) throws Exception {
		return wardDao.getBasicDetailofLocalbody(localBodyCode,PANCHAYAT_TYPE);
	}

	@Override
	public List<LocalbodyWard> getDeleteWardList(Integer lblc) throws Exception {
		return wardDao.getDeleteWardList(lblc);
	}

	@Override
	public String restoreDeleteWard(Integer wardCode, Integer wardVersion,Integer userId) throws Exception {
		return wardDao.restoreDeleteWard(wardCode, wardVersion,userId);
	}

	@Override
	public boolean checkWardDuplicateInDraft(Integer lblc, String wardNameEnglish) throws Exception {
		
		
		return wardDao.checkWardDuplicateInDraft(lblc, wardNameEnglish);
	}

	@Override
	public boolean checkWardCoverageExistinLocalbody(Integer lblc) throws Exception {
		return wardDao.checkWardCoverageExistinLocalbody(lblc);
	}

	@Override
	public List<LocalbodyWard> fetchDraftWardCoverage(Integer lblc) throws Exception {
		return wardDao.fetchDraftWardCoverage(lblc);
	}

	@Override
	public boolean publishWardCoverage(WardForm wardForm) throws Exception {
		return wardDao.publishWardCoverage(wardForm);
	}

	
	
	@Override
	public boolean checkWardDuplicateInDeletedWard(Integer lblc, String wardNameEnglish) throws Exception {
		return wardDao.checkWardDuplicateInDeletedWard(lblc, wardNameEnglish);
	}
	
	@Override
	public boolean saveLocalNameOfWard(String arrayupdate ,Integer userCode) throws Exception {
		return wardDao.saveLocalNameOfWard(arrayupdate ,userCode);
	}

	@Override
	public List<DRAFTWARDCOVERAGE> getDraftWardCoverageDetails(Integer wardCode, Integer wardVersion,boolean isDraft) {
		return wardDao.getDraftWardCoverageDetails(wardCode, wardVersion,isDraft);
	}

	/**
	 * End of 'Add n Manage Ward Covergae ' methods.
	 */


}
