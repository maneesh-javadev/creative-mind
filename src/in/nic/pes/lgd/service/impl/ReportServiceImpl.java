package in.nic.pes.lgd.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmc.lgd.localbody.entities.LBTypeDetailsWithCategory;
import com.org.ep.exception.BaseAppException;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.ConsolidateReportForRuralLB;
import in.nic.pes.lgd.bean.ConsolidateReportLB;
import in.nic.pes.lgd.bean.ConsolidateReportLandregions;
import in.nic.pes.lgd.bean.ConsolidateReportLandregionsforDistrict;
import in.nic.pes.lgd.bean.ConsolidateReportLandregionsforSubDistrict;
import in.nic.pes.lgd.bean.ConsolidateReportLandregionsforVillage;
import in.nic.pes.lgd.bean.DPwardConstituencyWiseVP;
import in.nic.pes.lgd.bean.DistrictWiseInvalidatedVillage;
import in.nic.pes.lgd.bean.GetLocalBodyListbyLBtypebyState;
import in.nic.pes.lgd.bean.LGDUpdationEntity;
import in.nic.pes.lgd.bean.LandRegionDetail;
import in.nic.pes.lgd.bean.LocalBodyHistory;
import in.nic.pes.lgd.bean.LocalBodyWiseWards;
import in.nic.pes.lgd.bean.MappedVillageByLBCodeBean;
import in.nic.pes.lgd.bean.PCACMappingforLBWard;
import in.nic.pes.lgd.bean.ParentwiseChildDetials;
import in.nic.pes.lgd.bean.ReportDistwiseVillagandMappedGP;
import in.nic.pes.lgd.bean.ReportingStatewiseGISMappedLBEntity;
import in.nic.pes.lgd.bean.StateWiseDistrictVillagePanchaytMapping;
import in.nic.pes.lgd.bean.StatewiseGPtoVillageMapped;
import in.nic.pes.lgd.bean.StatewiseUnmappedVillages;
import in.nic.pes.lgd.bean.UnmappedLbPcAcWard;
import in.nic.pes.lgd.bean.ViewUnMappedLocalBodies;
import in.nic.pes.lgd.dao.ReportDAO;
import in.nic.pes.lgd.restructure.reporting.entities.LocalBodyMappedToDistrictsEntity;
import in.nic.pes.lgd.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	
	private static final Logger log = Logger.getLogger(ReportServiceImpl.class);
	
	@Autowired
	ReportDAO reportDAO;

	@Override
	public List<ConsolidateReportLB> getConsolidatedRptForLBs()
			throws Exception {
		List<ConsolidateReportLB> getConsolidatedRptForLBList = new ArrayList<ConsolidateReportLB>();

		try {
			getConsolidatedRptForLBList = reportDAO.getConsolidatedRptForLBs();
		} catch (Exception e) {
			throw e;
		}
		return getConsolidatedRptForLBList;

	}
	@Override
	public List<ConsolidateReportLandregions> getConsolidatedRptForLandRegions()throws Exception{
		List<ConsolidateReportLandregions> getConsolidatedRptForLandregion = new ArrayList<ConsolidateReportLandregions>();

		try {
			getConsolidatedRptForLandregion = reportDAO.getConsolidatedRptForLandregion();
		} catch (Exception e) {
			throw e;
		}
		return getConsolidatedRptForLandregion;

	}

	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyState(
			int stateCode, char type, char level) throws Exception {
		List<GetLocalBodyListbyLBtypebyState> getLBList = new ArrayList<GetLocalBodyListbyLBtypebyState>();

		try {
			getLBList = reportDAO.getLocalBodyListbyLBtypebyState(stateCode,
					type, level);
		} catch (Exception e) {
			throw e;
		}
		return getLBList;
	}
	
	
	public List<ConsolidateReportLandregionsforDistrict> getLocalBodyLandRegionbyState(int slc) throws Exception {
		List<ConsolidateReportLandregionsforDistrict> getLBList = new ArrayList<ConsolidateReportLandregionsforDistrict>();

		try {
			getLBList = reportDAO.getLocalBodyListbyLBtypebyState(slc);
		} catch (Exception e) {
			throw e;
		}
		return getLBList;
	}

	public List<ConsolidateReportLandregionsforSubDistrict> getLocalBodyLandRegionbySubDistic(int stateCode, int district_code)throws Exception {
		List<ConsolidateReportLandregionsforSubDistrict> getLBList = new ArrayList<ConsolidateReportLandregionsforSubDistrict>();

		try {
			getLBList = reportDAO.getLocalBodyListbysubdistic(stateCode,district_code);
		} catch (Exception e) {
			throw e;
		}
		return getLBList;
	}
	public List<ConsolidateReportLandregionsforVillage> getLocalBodyLandRegionbyVillage(int subdistrict_code) throws Exception{
		List<ConsolidateReportLandregionsforVillage> getLBList = new ArrayList<ConsolidateReportLandregionsforVillage>();

		try {
			getLBList = reportDAO.getLocalBodyListbyvillage(subdistrict_code);
		} catch (Exception e) {
			throw e;
		}
		return getLBList;
	}

	

	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyState(
			int stateCode, char type, char level, int parentLBCode) throws Exception {
		List<GetLocalBodyListbyLBtypebyState> getLBList = new ArrayList<GetLocalBodyListbyLBtypebyState>();

		try {
			getLBList = reportDAO.getLocalBodyListbyLBtypebyState(stateCode,
					type, level,parentLBCode);
		} catch (Exception e) {
			throw e;
		}
		return getLBList;
	}
	
	//Added By Pranav
	
		@Override
		public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyFinYear(int stateCode, char type, char level,
				int parentLBCode, String financialYear) throws Exception {
			List<GetLocalBodyListbyLBtypebyState> getLBlist = new ArrayList<GetLocalBodyListbyLBtypebyState>();
			
			try {
				
					getLBlist = reportDAO.getLocalBodyListbyLBtypebyFinYear(stateCode, type, level, parentLBCode, financialYear);
			} catch (Exception e) {
				throw e;
			}
			return getLBlist;
		}

	@Override
	public List<ConsolidateReportForRuralLB> getConsolidatedRptForRuralLBs(String financialYear)
			throws Exception {
		List<ConsolidateReportForRuralLB> getConsolidatedRptForLBList = new ArrayList<ConsolidateReportForRuralLB>();

		try {
			getConsolidatedRptForLBList = reportDAO.getConsolidatedRptForRuralLBs(financialYear);
		} catch (Exception e) {
			throw e;
		}
		return getConsolidatedRptForLBList;

	}
	
	
	
	public List<MappedVillageByLBCodeBean> getMappedVillageByLBCode(int lbCode)throws Exception
	{
		List<MappedVillageByLBCodeBean> mappedVillageByLBCode	=	new ArrayList<MappedVillageByLBCodeBean>();
		try{
		mappedVillageByLBCode	=	reportDAO.getMappedVillageByLBCode(lbCode);
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		return mappedVillageByLBCode;
	}
	
	@Override
	public List<StatewiseUnmappedVillages> getStateWiseUnmappedVillages(
			Integer stateCode,Integer limit,Integer offset) throws Exception {
		List<StatewiseUnmappedVillages> stateWiseUnmappedVillagesList = new ArrayList<StatewiseUnmappedVillages>();
		stateWiseUnmappedVillagesList=null;
		try {
			stateWiseUnmappedVillagesList = reportDAO.getStateWiseUnmappedVillages(stateCode,limit,offset);
		} catch (Exception e) {
			throw e;
		}
		return stateWiseUnmappedVillagesList;
	}
	
	
	@Override
	public List<StatewiseGPtoVillageMapped> getStateWiseGPtoVillageMapping() throws Exception {
		List<StatewiseGPtoVillageMapped> stateWiseGPtoVillageMappingList = new ArrayList<StatewiseGPtoVillageMapped>();
		stateWiseGPtoVillageMappingList=null;
		try {
			stateWiseGPtoVillageMappingList = reportDAO.getStateWiseGPtoVillageMapping();
		} catch (Exception e) {
			throw e;
		}
		return stateWiseGPtoVillageMappingList;
	}
	
	@Override
	public List<StateWiseDistrictVillagePanchaytMapping> getStateWiseGPtoVillageMapping(Integer entity_code) throws Exception {
		List<StateWiseDistrictVillagePanchaytMapping> stateWiseGPtoVillageMappingList = new ArrayList<StateWiseDistrictVillagePanchaytMapping>();
		stateWiseGPtoVillageMappingList=null;
		try {
			stateWiseGPtoVillageMappingList = reportDAO.getStateWiseGPtoVillageMapping(entity_code);
		} catch (Exception e) {
			throw e;
		}
		return stateWiseGPtoVillageMappingList;
	}
	
	@Override
	public  List<UnmappedLbPcAcWard> getunmappedLbPcAcWard(Integer entity_code) throws Exception {
		List<UnmappedLbPcAcWard> stateWiseGPtoVillageMappingList = new ArrayList<UnmappedLbPcAcWard>();
		stateWiseGPtoVillageMappingList=null;
		try {
			stateWiseGPtoVillageMappingList = reportDAO.getunmappedLbPcAcWard(entity_code);
		} catch (Exception e) {
			throw e;
		}
		return stateWiseGPtoVillageMappingList;
	}
	

	@Override
	public List<Object[]> getWardsPerLBType(Integer statecode) {
		// TODO Auto-generated method stub
		return reportDAO.getWardsPerLBType(statecode);
	}
	@Override
	public List<Object[]> getWardsPerLocalBody(Integer statecode, Integer localbodyType) {
		// TODO Auto-generated method stub
		return reportDAO.getWardsPerLocalBody(statecode, localbodyType);
	}
	@Override
	public List<Object[]> getWardsDetails(Integer lblcCode) {
		// TODO Auto-generated method stub
		return reportDAO.getWardsDetails(lblcCode);
	}
	
	public List<PCACMappingforLBWard> getPCACMappingforLBWardDetails(Integer entityCode,char type) throws Exception {
		// TODO Auto-generated method stub
		return reportDAO.getPCACMappingforLBWardDetails(entityCode,type);
	}
	
	@Override
	public String getLocalBodyNameByCode(Integer lbCode) {
		// TODO Auto-generated method stub
		return reportDAO.getLocalBodyNameByCode(lbCode);
	}
	@Override
	public String getDistrictPanchayatNomenclatureNameWithStateName(
			Integer stateCode) {
		// TODO Auto-generated method stub
		return reportDAO.getDistrictPanchayatNomenclatureNameWithStateName(stateCode);
	}
	@Override
	public List<ViewUnMappedLocalBodies> getViewUnMappedLBDetails(
			Integer stateCode, Integer lbTypeCode) throws Exception {
		return reportDAO.getViewUnMappedLBDetails(stateCode,lbTypeCode);
		
	}
	@Override
	public List<DistrictWiseInvalidatedVillage> getDistrictWiseInvalidatedVillage(
			Integer district_code) throws Exception {
		return reportDAO.getDistrictWiseInvalidatedVillage(district_code);
	}
	@Override
	public String getLBNameByTypeCode(Integer localBodyCode) {
		// TODO Auto-generated method stub
		return reportDAO.getLBNameByTypeCode(localBodyCode);
	}
	@Override
	public List<LocalBodyWiseWards> getWardsPerLBObjects(Integer statecode,
														 Integer localbodyType,
														 int limit,
														 int offset,
														 String search) {
		// TODO Auto-generated method stub
		return reportDAO.getWardsPerLBObjects(statecode, localbodyType, limit, offset, search);
	}
	@Override
	public Object[] lbWardIsParentAndCount(Integer statecode,Integer localbodyType) {
		// TODO Auto-generated method stub
		return reportDAO.lbWardIsParentAndCount(statecode, localbodyType);
	}
	@Override
	public BigInteger countWardsPerLBObjects(Integer statecode, Integer localbodyType, String search) {
		// TODO Auto-generated method stub
		return reportDAO.countWardsPerLBObjects(statecode, localbodyType, search);
	}
	@Override
	public  List<DPwardConstituencyWiseVP> getDPwardConstituencyWiseVP(Integer statecode) throws Exception {
		List<DPwardConstituencyWiseVP> dpwardConstituencyWiseVPList = new ArrayList<DPwardConstituencyWiseVP>();
		dpwardConstituencyWiseVPList=null;
		try {
			dpwardConstituencyWiseVPList = reportDAO.getDPwardConstituencyWiseVP(statecode);
		} catch (Exception e) {
			throw e;
		}
		return dpwardConstituencyWiseVPList;
	}
	
	@Override
	public Object[] getDistrictWiseDetails(Integer stateCode,Integer districtCode) throws Exception {
		return reportDAO.getDistrictWiseDetails(stateCode,districtCode);
	}
	@Override
	public String getLocalBodyName(Integer lbCode,Integer PlbCode) {
		return reportDAO.getLocalBodyName(lbCode,PlbCode);
	}
	
	public Character getNextLevellbCode(Integer lbCode,Integer stateCode,String ctype,String clevel){
		return reportDAO.getNextLevellbCode(lbCode, stateCode, ctype, clevel);
	}
	
	@Override
	public List<ConsolidateReportLB> getConsolidatedRptForLBsCombined() throws Exception{
		List<ConsolidateReportLB> getConsolidatedRptForLBList = new ArrayList<ConsolidateReportLB>();

		try {
			getConsolidatedRptForLBList = reportDAO.getConsolidatedRptForLBsCombined();
		} catch (Exception e) {
			throw e;
		}
		return getConsolidatedRptForLBList;

	}
	
	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyStateCombined(int stateCode, char type, char level) throws Exception {
		List<GetLocalBodyListbyLBtypebyState> getLBList = new ArrayList<GetLocalBodyListbyLBtypebyState>();

		try {
			getLBList = reportDAO.getLocalBodyListbyLBtypebyStateCombined(stateCode, type, level);
		} catch (Exception e) {
			throw e;
		}
		return getLBList;
	}
	
	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyStateCombined(int stateCode, char type, char level, int parentLBCode) throws Exception {
		List<GetLocalBodyListbyLBtypebyState> getLBList = new ArrayList<GetLocalBodyListbyLBtypebyState>();

		try {
			getLBList = reportDAO.getLocalBodyListbyLBtypebyStateCombined(stateCode, type, level, parentLBCode);
		} catch (Exception e) {
			throw e;
		}
		return getLBList;
	}
	@Override
	public List<LocalBodyHistory> getLocalBodyHistoryReport(
			char localBodyNameEnglish, int localBodyCode)
			throws BaseAppException {
		// TODO Auto-generated method stub
		return reportDAO.getLocalBodyHistoryReport(localBodyNameEnglish, localBodyCode);
	}
	@Override
	public String getLbHierchicalDetail(Integer lbCodeOrlbTypeCode, Integer stateCode,boolean parenCodeflag) throws Exception{
		return reportDAO.getLbHierchicalDetail(lbCodeOrlbTypeCode,stateCode,parenCodeflag);
	}
	@Override
	public List<LandRegionDetail> landRegionDetail(Character entity_type, Integer entity_code) throws Exception {
		
		return reportDAO.landRegionDetail(entity_type, entity_code);
	}
	
	@Override
	public List<ReportingStatewiseGISMappedLBEntity> getStatewiseGISMappedLocalBody() throws Exception {
		return reportDAO.getStatewiseGISMappedLocalBody();
	}
	@Override
	public List<LocalBodyMappedToDistrictsEntity> getLocalBodyMappedWithDistricts(Integer stateCode) throws Exception {
		return reportDAO.getLocalBodyMappedWithDistricts(stateCode);
	}
	@Override
	public List<Object> getFreezedStatusList() {
		return reportDAO.getFreezedStatusList();
	}
	@Override
	public List<ReportDistwiseVillagandMappedGP> getDistwiseVillagandMappedGP(Integer districtCode) throws Exception {
		return reportDAO.getDistwiseVillagandMappedGP(districtCode);
	}
	@Override
	public List<ConsolidateReportForRuralLB> getConsolidatedRptForRuralLBsPES(String financialYear) throws Exception {
		List<ConsolidateReportForRuralLB> getConsolidatedRptForLBList = new ArrayList<ConsolidateReportForRuralLB>();

		try {
			getConsolidatedRptForLBList = reportDAO.getConsolidatedRptForRuralLBsPES(financialYear);
		} catch (Exception e) {
			throw e;
		}
		return getConsolidatedRptForLBList;
	}
	@Override
	public String getDistrictPanchayatNomenclatureNameWithStateNamePES(Integer stateCode) {
		// TODO Auto-generated method stub
		return reportDAO.getDistrictPanchayatNomenclatureNameWithStateNamePES(stateCode);
	}
	@Override
	public String getLocalBodyNameByCodePES(Integer lbCode) {
		// TODO Auto-generated method stub
		return reportDAO.getLocalBodyNameByCodePES(lbCode);
	}
	@Override
	public String getLocalBodyNamePES(Integer lbCode, Integer PlbCode) {
		// TODO Auto-generated method stub
		return reportDAO.getLocalBodyNamePES(lbCode,PlbCode);
	}
	@Override
	public List<MappedVillageByLBCodeBean> getMappedVillageByLBCodePES(int lbCode) throws Exception {
		List<MappedVillageByLBCodeBean> mappedVillageByLBCode	=	new ArrayList<MappedVillageByLBCodeBean>();
		try{
		mappedVillageByLBCode	=	reportDAO.getMappedVillageByLBCodePES(lbCode);
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		return mappedVillageByLBCode;
	}
	@Override
	public List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyFinYearPES(int stateCode, char type,
			char level, int parentLBCode, String financialYear) throws Exception {
		List<GetLocalBodyListbyLBtypebyState> getLBlist = new ArrayList<GetLocalBodyListbyLBtypebyState>();
		try {
				getLBlist = reportDAO.getLocalBodyListbyLBtypebyFinYearPES(stateCode, type, level, parentLBCode, financialYear);
		} catch (Exception e) {
			throw e;
		}
		return getLBlist;
	}
	@Override
	public AttachmentMaster getUploadFileConfigurationDetails(Long fileMasterId) throws HibernateException {
		return reportDAO.getUploadFileConfigurationDetails(fileMasterId);
	}
	@Override
	public List<StatewiseGPtoVillageMapped> getStateWiseGPtoVillageMappingB() throws Exception {
		List<StatewiseGPtoVillageMapped> stateWiseGPtoVillageMappingList = new ArrayList<StatewiseGPtoVillageMapped>();
		stateWiseGPtoVillageMappingList=null;
		try {
			stateWiseGPtoVillageMappingList = reportDAO.getStateWiseGPtoVillageMappingB();
		} catch (Exception e) {
			throw e;
		}
		return stateWiseGPtoVillageMappingList;
	}
	
	@Override
	public List<ParentwiseChildDetials> getParentwiseChildDetils(Character parentType, Integer parentCode,Character category,Character level) throws Exception {
		return reportDAO.getParentwiseChildDetils(parentType, parentCode, category,level);
	}
	@Override
	public String  getEntitywiseParentDetails(String entityType,Integer entityCode) throws Exception {
		String message=null;
		if(entityType!=null){
			List<LandRegionDetail> landRegionDetailList=reportDAO.getEntitywiseParentDetails(entityType,entityCode);
			if(landRegionDetailList!=null && !landRegionDetailList.isEmpty()){
				LandRegionDetail landRegionDetail=landRegionDetailList.get(0);
				switch(entityType.charAt(0)){
				case 'S':message="GIS view of "+landRegionDetail.getStateNameEnglish()+" State";
				break;
				case 'D':message="GIS view of "+landRegionDetail.getDistrictNameEnglish()+" District of "+landRegionDetail.getStateNameEnglish()+ " State";
				break;
				case 'T':message="GIS view of "+landRegionDetail.getSubdistrictNameEnglish()+" Sub-District of "+landRegionDetail.getDistrictNameEnglish()
				+" District of "+ landRegionDetail.getStateNameEnglish()   + " State";
				break;
				case 'V':message="GIS view of "+landRegionDetail.getVillageNameEnglish()+" Village of "+landRegionDetail.getSubdistrictNameEnglish()+" Sub-District of "+landRegionDetail.getDistrictNameEnglish()
				+" District of "+ landRegionDetail.getStateNameEnglish()   + " State";
				break;
				}
			}
			
			
		}
		return message;
	}
	@Override
	public String getSubdistictCensusCodebyVillageCode(Integer villageCode) throws Exception {
		return reportDAO.getSubdistictCensusCodebyVillageCode(villageCode);
	}
	
	@Override
	public List<LBTypeDetailsWithCategory> buildLocalBodyHierarchyWithCategorywithCantonmentRPT(Integer setupCode,Integer setupVersion) throws HibernateException {
		return reportDAO.buildLocalBodyHierarchyWithCategorywithCantonmentRPT(setupCode, setupVersion);
	}
	
	@Override
	public List<LGDUpdationEntity> getLGDUpdation(Integer stateCode) {
		return reportDAO.getLGDUpdation(stateCode);
	}
	
	
	
}
