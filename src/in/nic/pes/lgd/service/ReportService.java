package in.nic.pes.lgd.service;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;

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
import in.nic.pes.lgd.restructure.reporting.entities.LocalBodyMappedToDistrictsEntity;

 public interface ReportService {

	 List<ConsolidateReportLB> getConsolidatedRptForLBs() throws Exception;

	 List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyState(int stateCode, char type, char level) throws Exception;

	 List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyState(int stateCode, char type, char level, int parentLBCode) throws Exception;
	
	 List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyFinYear(int stateCode, char type, char level, int parentLBCode , String financialYear) throws Exception;

	 List<ConsolidateReportLandregions> getConsolidatedRptForLandRegions() throws Exception;

	 List<ConsolidateReportLandregionsforDistrict> getLocalBodyLandRegionbyState(int stateCode) throws Exception;

	 List<ConsolidateReportLandregionsforSubDistrict> getLocalBodyLandRegionbySubDistic(int stateCode, int district_code) throws Exception;

	 List<ConsolidateReportLandregionsforVillage> getLocalBodyLandRegionbyVillage(int subdistrict_code) throws Exception;

	 List<ConsolidateReportForRuralLB> getConsolidatedRptForRuralLBs(String financialYear) throws Exception;
	
	 List<MappedVillageByLBCodeBean> getMappedVillageByLBCode(int lbCode) throws Exception;

	 List<StatewiseUnmappedVillages> getStateWiseUnmappedVillages(Integer stateCode, Integer limit, Integer offset) throws Exception;

	 List<StatewiseGPtoVillageMapped> getStateWiseGPtoVillageMapping() throws Exception;

	 List<StateWiseDistrictVillagePanchaytMapping> getStateWiseGPtoVillageMapping(Integer entity_code) throws Exception;

	 List<UnmappedLbPcAcWard> getunmappedLbPcAcWard(Integer entity_code) throws Exception;

	 List<Object[]> getWardsPerLBType(Integer statecode);

	 List<Object[]> getWardsPerLocalBody(Integer statecode, Integer localbodyType);

	 List<Object[]> getWardsDetails(Integer lblcCode);

	 List<PCACMappingforLBWard> getPCACMappingforLBWardDetails(Integer entityCode, char type) throws Exception;

	 String getLocalBodyNameByCode(Integer lbCode);

	 String getDistrictPanchayatNomenclatureNameWithStateName(Integer stateCode);

	 List<ViewUnMappedLocalBodies> getViewUnMappedLBDetails(Integer stateCode, Integer lbTypeCode) throws Exception;

	 List<DistrictWiseInvalidatedVillage> getDistrictWiseInvalidatedVillage(Integer district_code) throws Exception;

	 String getLBNameByTypeCode(Integer localBodyCode);

	 List<LocalBodyWiseWards> getWardsPerLBObjects(Integer statecode, Integer localbodyType, int limit, int offset, String search);

	 BigInteger countWardsPerLBObjects(Integer statecode, Integer localbodyType, String search);

	 Object[] lbWardIsParentAndCount(Integer statecode, Integer localbodyType);

	 List<DPwardConstituencyWiseVP> getDPwardConstituencyWiseVP(Integer stateCode) throws Exception;

	 Object[] getDistrictWiseDetails(Integer stateCode, Integer districtCode) throws Exception;

	 String getLocalBodyName(Integer lbCode, Integer PlbCode);

	 Character getNextLevellbCode(Integer lbCode, Integer stateCode, String ctype, String clevel);

	 List<ConsolidateReportLB> getConsolidatedRptForLBsCombined() throws Exception;

	 List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyStateCombined(int stateCode, char type, char level) throws Exception;

	 List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyStateCombined(int stateCode, char type, char level, int parentLBCode) throws Exception;

	 List<LocalBodyHistory> getLocalBodyHistoryReport(char localBodyNameEnglish, int localBodyCode) throws BaseAppException;

	 String getLbHierchicalDetail(Integer lbCodeOrlbTypeCode, Integer stateCode,boolean parenCodeflag) throws Exception;
	
	 List<LandRegionDetail> landRegionDetail(Character entity_type, Integer entity_code) throws Exception;
	
	 List<ReportingStatewiseGISMappedLBEntity> getStatewiseGISMappedLocalBody() throws Exception;
	
	 List<LocalBodyMappedToDistrictsEntity> getLocalBodyMappedWithDistricts(Integer stateCode)throws Exception;
	
	 List<Object> getFreezedStatusList();
	
	 List<ReportDistwiseVillagandMappedGP> getDistwiseVillagandMappedGP(Integer districtCode)throws Exception;
	
	 List<ConsolidateReportForRuralLB> getConsolidatedRptForRuralLBsPES(String financialYear) throws Exception;
	
	 String getDistrictPanchayatNomenclatureNameWithStateNamePES(Integer stateCode);
	
	 String getLocalBodyNameByCodePES(Integer lbCode);
	
	 String getLocalBodyNamePES(Integer lbCode, Integer PlbCode);
	
	 List<MappedVillageByLBCodeBean> getMappedVillageByLBCodePES(int lbCode) throws Exception;
	
	 List<GetLocalBodyListbyLBtypebyState> getLocalBodyListbyLBtypebyFinYearPES(int stateCode, char type, char level, int parentLBCode , String financialYear) throws Exception;
	
	 AttachmentMaster getUploadFileConfigurationDetails(Long fileMasterId) throws HibernateException;
	
	 List<StatewiseGPtoVillageMapped> getStateWiseGPtoVillageMappingB() throws Exception;
	
	 List<ParentwiseChildDetials> getParentwiseChildDetils(Character parentType,Integer parentCode, Character category,Character level) throws Exception;
	
	 String getEntitywiseParentDetails(String entityType,Integer entityCode) throws Exception;
	
	 String getSubdistictCensusCodebyVillageCode(Integer villageCode) throws Exception;
	
	 List<LBTypeDetailsWithCategory> buildLocalBodyHierarchyWithCategorywithCantonmentRPT(Integer setupCode, Integer setupVersion) throws HibernateException;
	
	 List<LGDUpdationEntity> getLGDUpdation(Integer stateCode) ;
}
