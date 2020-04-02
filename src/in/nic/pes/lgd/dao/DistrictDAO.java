package in.nic.pes.lgd.dao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.org.ep.exception.BaseAppException;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictHistory;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.DistrictWiseLBReportBean;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GazettePublication;
import in.nic.pes.lgd.bean.GazettePublicationDateSave;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.ParentHeirarchyBean;
import in.nic.pes.lgd.bean.ParliamentConstituencymodify;
import in.nic.pes.lgd.bean.StandardCodes;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictPK;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.StandardCodeDataForm;
import in.nic.pes.lgd.forms.StandardCodeForm;
import pes.attachment.util.AttachedFilesForm;

public interface DistrictDAO {
	
	
	public List<StandardCodeDataForm> updateStandardCode(StandardCodeForm standardCodeForm) throws BaseAppException;
	public List<District> getDistrictListbyStateCode(int slc)throws BaseAppException;
	public List<District> getDistrictListbyStateCodeGlobal(int stateCode)throws BaseAppException;
	
// TODO Remove unused code found by UCDetector
// 	public List<Subdistrict> getSubdistrictListbyDistrictCode(int districtCode)throws BaseAppException;	
	public List<Subdistrict> getSubdistrictListbyDistrict(int districtCode)throws BaseAppException;
	
	public int getMaxDistrictCode(String query)throws BaseAppException;

	public int getMaxDistrictVersionbyCode(int districtCode)throws BaseAppException;
	
// TODO Remove unused code found by UCDetector
// 	public List<District> getDistrictDetails(int districtCode,int districtVersion)throws BaseAppException;
	
// TODO Remove unused code found by UCDetector
// 	public List<State> getSubdistrictDetails(int subdistrictCode)throws BaseAppException;
	
// TODO Remove unused code found by UCDetector
// 	public boolean saveOrderDetails(GovernmentOrder governmentOrder, Session session)throws BaseAppException;
	
// TODO Remove unused code found by UCDetector
// 	public boolean save(MapLandRegion mapLandRegion)throws BaseAppException;
	public int getMaxRecords(String query)throws BaseAppException;
	
	 public List<District> getListVillageDetails(String query)throws BaseAppException;
	 
	
	 
	
	 
// TODO Remove unused code found by UCDetector
// 	 public boolean updateLReplacedBy(int lrReplacedby, DistrictPK districtPK)throws BaseAppException;
	 
	 public List<District> getDistrictListbyDistCode(int i)throws BaseAppException;
// TODO Remove unused code found by UCDetector
// 	 public List<District> getDistrictListbyDistCode(String i)throws BaseAppException;
	 
	 public List<District> getDistrictViewList(String query)throws BaseAppException;
	 
	 public List<District> getTargetDistrictList(int districtCode,int statecode)throws BaseAppException;
	 
	 public List<District> getTargetDistrictListFinal(int districtCode, int statecode) throws BaseAppException;
	 
// TODO Remove unused code found by UCDetector
// 		public boolean updateIsActive(DistrictPK districtPK)throws BaseAppException;
		
		//For Modify Sub District
		//public List<DistrictDataForm> getDistrictDetailsModify();
		public List<District> getDistrictDetailsModify(int districtCode)throws BaseAppException;
		public List<GovernmentOrder> getGovermentOrderDetail(int orderCode)throws BaseAppException;
		public void	ChangeDistrict(int districtCode,String districtNameEnglish,int userId,String districtNameLocal,String aliasEnglish,String aliasLocal, Session session)throws BaseAppException;
		public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode)throws BaseAppException;
		public boolean updateMapLanRegion(int mapCode, String coordinates, int subDistrictCode,Session httpsession)throws BaseAppException;
		public boolean modifyDistrictInfo(DistrictForm DistrictForm, DistrictPK districtPk, int map_landRegionCode,Session session1)throws BaseAppException;
// TODO Remove unused code found by UCDetector
// 		public boolean modifyDistrictCrInfo(DistrictForm DistrictForm, int  distcode, int map_landRegionCode,String cord1,Session session1)throws BaseAppException;
		/*public String ChangeCrDistrict(int districtCode,String districtNameEnglish,int userId,String orderno,Date orderDate,Date efectivDate,Date gazDate,String orderPath,String districtNameLocal,String aliasEnglish,String aliasLocal, Session session)throws BaseAppException;*/
		
		
	
// TODO Remove unused code found by UCDetector
// 		public boolean save(MapLandRegion mapLandRegion,Session session)throws BaseAppException;
		
		public boolean updateIsActive(boolean isActive, DistrictPK districtPK, Session session)throws BaseAppException;
		
		
// TODO Remove unused code found by UCDetector
// 		public List<District>getdistrictListbyDistrict(int districtCode)throws BaseAppException;

		public boolean updateLrReplaces(int lrReplaces, DistrictPK sdpk,
				Session session)throws BaseAppException;

		public boolean saveDistrict(District newDistrict, Session session)throws BaseAppException;

		public District getSubDistrictDetail(DistrictPK spdk, Session session)throws BaseAppException;

		public boolean publishDistrict(District sdbean, Session session)throws BaseAppException;

		public	boolean update(String query, Session session)throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 		public	boolean save(District district, Session session)throws BaseAppException;

		public List<District> getDistrictModify(int distCode)throws BaseAppException;
		 public List<District> getDistrictListbyDistrict(int districtCode)throws BaseAppException;
		 
		 public List<District> getDistrict(int districtCode)throws BaseAppException;
		 
		 
		 
		
		public List<DistrictHistory> getDistrictHistoryDetail(char districtNameEnglish ,int districtCode)throws BaseAppException;
		
		
		public List<GazettePublication> getGazettePublication()throws BaseAppException;

		public List<GazettePublicationDateSave> getGazettePublicationsave(String orderCode,Date gazPubDate)throws BaseAppException;


// TODO Remove unused code found by UCDetector
// 		public boolean saveMapLandRegion(MapLandRegion mapLandRegion, Session session)throws BaseAppException;
// TODO Remove unused code found by UCDetector
// 		public List<Subdistrict> getSubdistrictDetails(String query)throws BaseAppException;
		//public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType,int entityCode,int entityVersion);
		public List<Headquarters> getHeadquarterModify(char entityType,int entityCode,int entityversion)throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 		public void SetGovermentOrderEntity(String entityCode,char entityType)throws BaseAppException;
		public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType,int entityCode,int entityVersion,int minorVersion)throws BaseAppException;

		boolean invalidateFunctionCall(String districtCodes, int districtCode,
				long roleCode, String subDistrictCodes)throws BaseAppException;
		public List<District> getDistrictListbyStateCode(int stateCode, String districtList)throws BaseAppException;

		public abstract List<District> getDistrictListbyStateCodeForListBox(int stateCode)throws BaseAppException;

		District getSingleDistrictDetailsMaxVersion(String sQuery)throws BaseAppException;

		boolean updateActive(boolean isActive, SubdistrictPK DistrictPK,
				Session session)throws BaseAppException;

		boolean  updatevillage(boolean b, VillagePK sdpk, Session session)throws BaseAppException;


		public List<ParliamentConstituencymodify> getpcCode(Integer stateCode,
				char constituencType, Integer pcCode,Integer limit,Integer offset)throws BaseAppException;

		//public List<StandardCodes> getStandardCode(Integer pcCode,Integer limit,Integer offset)throws BaseAppException;

		
		

		int getMaxDistrictVersion(int districtCode, Session session)throws BaseAppException;

		/*Retrieve files from the attachment table*/
		public List<Attachment> getAttacmentdetail(int orderCode) throws BaseAppException;
		/*Retrieving the Map details attachment from the database*/
		public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType,int entityCode, Session session) throws BaseAppException;
		public District getById(Integer subDistrictCode, Session session);
		public int saveDataInMap(DistrictForm districtForm,
				List<AttachedFilesForm> validFileMap, HttpSession httpSession,
				Session session);
		public List<District> getTargetDistrictShiftSDistrictFinal(int sourceDistrictCode,int statecode) throws BaseAppException;
		public String insertDistrict(HttpServletRequest request,
				HttpSession httpSession, DistrictForm districtForm,
				Session session);
		public void saveOrUpdate(int districtId, int mapAttachmentId,
				Session session);
		public int findDuplicate(String districtName, Integer stateCode);
		//Maneesh
		public  String saveInvalidateDistrictDAO(DistrictForm districtForm,Session hsession,Long userId);
		public List<District> getTargetDistrictShiftSDistrict(int sourceDistrictCode,int stateCode) throws BaseAppException;
		
		/*public Integer ChangeCrDistrict(int districtCode, String districtNameEnglish,
				int userId, String orderno,Date orderDate,Date efectivDate,Date gazpubDate,String orderPath,String districtNameLocal, String aliasEnglish,
				String aliasLocal, Session session,Integer slc) throws BaseAppException;*/
		public List<StandardCodes> getStandardCode(StandardCodeForm standardCodeForm) throws BaseAppException;
		public String ChangeCrDistrict(int districtCode, String districtNameEnglish,String districtNameEnglishch,
				int userId, String orderno,Date orderDate,Date efectivDate,Date gazpubDate,String orderPath,String districtNameLocal, String aliasEnglish,
				String aliasLocal, Session session,Integer slc) throws BaseAppException;
		/*public List<District> getDistrictListExtend(Integer stateCode,Integer orgCode) throws BaseAppException; *///Extend details
	/*	public List<District> getDistrictListbyStateCodeExtend(Integer stateCode,
				String districtList,Integer orgCode) throws BaseAppException;*/
		public List<District> getDistrictListExtend(Integer stateCode,Integer orgCode) throws BaseAppException ;
		public List<District> getDistrictListExtendforBlock(Integer stateCode,Integer orgCode) throws BaseAppException;
		public List<District> getDistrictListbyStateCodeExtend(Integer stateCode,
				String districtList,Integer orgCode,char type) throws BaseAppException;
		//Maneesh
		public int updateDistrictMap(Session session, int... params);
		public List<DistrictWiseLBReportBean> DistrictWiseLBReportDetail(
				Integer districtCode) throws BaseAppException;
		public String getDistrictNameEnglishbyDistrictCode(Integer districtCode);
		
		/**
		 * Created On 01-10-2014
		 * To get District List for particular @orgCode and @locatedAtLevelCode with their @operationState
		 * 
		 * @author Ripunj 
		 * @param stateCode
		 * @param orgCode
		 * @param locatedatlevelCode
		 * @return
		 * @throws BaseAppException
		 */
		public List<District> getDistrictListbyStateCodeForListBoxExtended(
				int stateCode, int orgCode, int locatedatlevelCode)
				throws BaseAppException;
		/**
		 * Created On 15-10-2014
		 * To get District List for particular @orgCode and @locatedAtLevelCode with their @operationState without selected @districtList
		 * 
		 * @author Ripunj
		 * @param stateCode
		 * @param districtList
		 * @param orgCode
		 * @param locatedatlevelCode
		 * @return
		 * @throws BaseAppException
		 */
		public List<District> getDistrictListbyStateCodeExtended(int stateCode,
				String districtList, int orgCode, int locatedatlevelCode)
				throws BaseAppException;
		
		/**
		 * Created on 03-11-2014 
		 * To Update PESA status of District
		 * @author Ripunj 
		 * @param DistrictForm
		 * @return
		 * @throws BaseAppException
		 * Changed by kirandeep on 01/05/2015 parameter added
		 * Changed by Sunita on 07/04/2016 parameter added input param
		 */
		public boolean updatePesaStatus(String inputParam,Integer stateCode) throws BaseAppException;
		
		
		
		/**
		 * New methods for the localbody impact issue
		 * Addded on 11-12-2014
		 * @author Kirandeep 
		 * @param districtCode
		 * @return
		 * @throws BaseAppException
		 */
		public List<District> getDistrictListbyStateCodeForLocalBody(int districtCode) throws BaseAppException;
		
		/**
		 * added on 06/01/2015 by kirandeep for localbody impact issue.
		 */
		
		public List<District> getTargetDistrictShiftSDistrictForlocalbody(int sourceDistrictCode, int statecode) throws BaseAppException;
		public List<District> getDistrictListbyStateCodeForLocalbody(int stateCode,	String districtList) throws BaseAppException;
		public List<DistrictHistory> getDistrictHistoryReport(char districtNameEnglish,
				int districtCode) throws Exception;
		public List<District> getDistrictListbyDistrictCodeForLocalBody(int dlc) throws BaseAppException;

	
		/** 
		 * @author Kirandeep for landregion is pesa 
		 * date: 30/04/2015
		 * @param entityCodes
		 * @param type
		 * @return
		 * @throws BaseAppException
		 */
		public List<ParentHeirarchyBean> getHeirarchyByParentCodes(String entityCodes,Character type,Character afterPost)  throws BaseAppException;

		public List<District> getDistrictListbyCreteria(Integer stateCode,List<Integer> districtCodeList) throws Exception; 
		
		/**
		 * Code used for getting districtList for multiple districts
		 * @author Pooja
		 * @since 21-10-2015
		 * @param states
		 * @return
		 * @throws Exception
		 */
		public List<District> getDistrictListbyStates(String states) throws Exception;
		
		public List<District> getDistrictListbyCriteria(Integer entityCode,String entityType) throws Exception;
		
		public boolean districtMapFinalised(Integer districtCode);
		
		public boolean distInvalFnAfterCreateMulDist(Integer districtCode,Integer userId,Date effectiveDate)throws Exception;
		
		public Response saveEffectiveDateEntityDistrict(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId)throws Exception;
		
		int getMaxDistrictMinorVersion(int districtCode,int districtVersion) throws BaseAppException;
}
