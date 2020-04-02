package in.nic.pes.lgd.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.org.ep.exception.BaseAppException;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictHistory;
import in.nic.pes.lgd.bean.DistrictWiseLBReportBean;
import in.nic.pes.lgd.bean.GazettePublication;
import in.nic.pes.lgd.bean.GazettePublicationDateSave;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.ParentHeirarchyBean;
import in.nic.pes.lgd.bean.ParliamentConstituencymodify;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.forms.DistrictDataForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.StandardCodeDataForm;
import in.nic.pes.lgd.forms.StandardCodeForm;
import pes.attachment.util.AttachedFilesForm;

public interface DistrictService {

// TODO Remove unused code found by UCDetector
// 	public void createDistrict() throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	public District viewDistrictDetail() throws BaseAppException;

	public List<District> getDistrictList(int slc) throws BaseAppException;

	public List<District> getDistrictListGlobal(int stateCode) throws BaseAppException; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	boolean saveDistrict(DistrictForm districtForm) throws BaseAppException;

	public List<Subdistrict> getSubdistrictListbyDistrict(String districtCode) throws BaseAppException; // NO_UCD (unused code)

	public List<Village> getVillageList(String districtCode) throws BaseAppException; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<Village> getVillageListRemovingOldVillage(String districtCode, String totalVillage, String[] subdistrictCode) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	public boolean publishSubDistrict(DistrictForm sdForm, int sdCode, int sdVersion, int mapCd, Session session) throws BaseAppException;

	public boolean publishDistrict(DistrictForm sdForm, int sdCode, int sdVersion, int mapCd, Session session) throws BaseAppException;

	public abstract boolean saveNewSubdistritVersion(int DistrictCode, int newSDCode, int newSDVersion, Session session) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	public int publishMapLandRegion(DistrictForm sdForm, int sdCode, int sdVersion, Session session, HttpServletRequest request) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	public abstract boolean saveNewVillageVersionPartContri(int villCode, int newSDCode, int newSDVersion, Session session) throws BaseAppException;

	public List<District> getDistrictListByDistCode(int districtCode) throws BaseAppException;

	public List<District> getDistrictViewList(String query) throws BaseAppException;

	public List<District> getTargetDistrictList(int districtCode, int statecode) throws BaseAppException; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<District> getTargetDistrictListFinal(int districtCode, int statecode) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	public abstract boolean saveNewVillageVersion(int subDistrictCode, int newSDCode, int newSDVersion, Session session) throws BaseAppException;

	public abstract boolean saveReplaces(int id, int lrReplaces, int sdCode, int sdVersionCode, Session session) throws BaseAppException;

	public abstract boolean saveReplacedBy(int id, int lrReplacedBy, int sdCode, int sdVersionCode, Session session) throws BaseAppException;

	//ublic List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode) throws BaseAppException;

	public List<DistrictDataForm> getDistrictDetailsModify(int districtCode) throws BaseAppException;

	public boolean modifyDistrictInfo(DistrictForm districtForm, HttpServletRequest request, HttpSession httpSession) throws BaseAppException;

	/* public List<District> getDistricts(String districtCode); */
	boolean saveNewSubdistrictVersionPartContri(int villCode, int newSDCode, int newSDVersion,int minorVersion, Session session) throws BaseAppException;

	boolean  saveNewDistrictVersion(int subDistrictCode, int subDistrictCodeVersion,int minorVersion, int lrReplacedBy, Session session) throws BaseAppException;

	public List<DistrictHistory> getDistrictHistoryDetail(char districtNameEnglish, int districtCode) throws BaseAppException;

	public List<GazettePublication> getGazettePublicationDate() throws BaseAppException;

	public List<Subdistrict> getSubdistrictListbyDistrictCode(int districtCode) throws BaseAppException;

	public List<GazettePublicationDateSave> getGazettePublicationDateSave(String orderCode, Date Gazettedate) throws BaseAppException;

	boolean saveDistrict(DistrictForm sdForm, HttpServletRequest request) throws BaseAppException;

	public List<District> getDistrict(String query) throws BaseAppException;
	
	public List<District> getDistrict(int districtCode)throws BaseAppException;
// TODO Remove unused code found by UCDetector
// 	public abstract List<Subdistrict> getVillageDetailforReorganize(String listVillCode) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	public abstract boolean reOrganize(DistrictForm SDForm, List<VillageForm> listNewVillForm, HttpServletRequest request, HttpSession httpSession) throws BaseAppException;

	public boolean publishSubdistrit(DistrictForm sdForm, Session session) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	boolean reOrganize(DistrictForm SDForm, List<VillageForm> listNewVillForm, List<VillageForm> listModifyVill, List<SubDistrictForm> listSubdistritNew, HttpServletRequest request, HttpSession httpSession) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	boolean reOrganize(DistrictForm SDForm, List<VillageForm> listNewVillForm, List<VillageForm> listModifyVillForm, HttpServletRequest request, HttpSession httpSession) throws BaseAppException;

	public List<District> getSubDistrictViewList(String query) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	boolean saveNewVillageVersionFullContri(int villCode, int newSDCode, int newSDVersion, Session session) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	boolean saveNewVillageVersionPartContri(int villCode, Session session) throws BaseAppException;

	boolean invalidateLoop(String districtCode, String subDistrictCodes, HttpSession httpSession) throws BaseAppException; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	boolean invalidateDistrict(String districtCodes, int districtCode, long roleCode, String subDistrictCodes) throws BaseAppException;

	public abstract List<District> getDistrictListbyStateCode(int stateCode, String districtList) throws BaseAppException; // NO_UCD (unused code)

	public abstract List<District> getDistrictListbyStateCodeForListBox(int stateCode) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	boolean saveNewSubdistrictVersionPartContri(int villCode, Session session) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	District getDistrictBean(int districtCode) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	District getSingleDistrictDetailsMaxVersion(int dcode) throws BaseAppException;

// TODO Remove unused code found by UCDetector
// 	public boolean saveDistrictShift(DistrictForm sDForm, List<ShiftVillageForm> listvillageShift, HttpServletRequest request) throws BaseAppException;

	public GovernmentOrder saveDataInGovtOrder(DistrictForm districtForm, Session session) throws BaseAppException;

	public void saveDataInAttachment(GovernmentOrder govtOrder, DistrictForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws BaseAppException;

	public List<ParliamentConstituencymodify> getPcCode(Integer stateCode, char constituencType, Integer pcCode, Integer limit, Integer offset) throws BaseAppException;

	// new Modify
	// public List<StandardCodes> getStandardCode(Integer pcCode,Integer
	// limit,Integer offset)throws BaseAppException;

	public boolean changeDistrict(DistrictForm districtForm, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request) throws BaseAppException;

	/*
	 * public boolean changeDistrictforTemplate(DistrictForm districtForm,
	 * GovernmentOrderForm govtOrderForm, HttpServletRequest request)throws
	 * BaseAppException;
	 */

	/*
	 * public boolean modifyDistrictCrInfo(DistrictForm districtForm,
	 * HttpServletRequest request, List<AttachedFilesForm>
	 * attachedList,List<AttachedFilesForm> attachedMapList, HttpSession
	 * httpSession)throws BaseAppException;
	 */

// TODO Remove unused code found by UCDetector
// 	public List<CheckAuthorization> getDistrictListAuthentication(int stateCode) throws BaseAppException;

	/* Retrieve files from the attachment table */

	public List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws BaseAppException;

	/* Retrieving the Map details attachment from the database */

// TODO Remove unused code found by UCDetector
// 	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode) throws BaseAppException;

	List<Subdistrict> getPartSubdistrictListbyDistrict(String districtCode) throws BaseAppException; // NO_UCD (unused code)

	public int saveDataInMap(DistrictForm districtForm, List<AttachedFilesForm> validFileMap, HttpSession httpSession, Session session);

	public String insertDistrict(HttpServletRequest request, HttpSession httpSession, DistrictForm districtForm, Session session);

// TODO Remove unused code found by UCDetector
// 	public void saveOrUpdate(int districtId, int mapAttachmentId, Session session);

// TODO Remove unused code found by UCDetector
// 	public District getById(Integer subDistrictCode, Session session) throws BaseAppException;

	public int findDuplicate(String districtName, Integer stateCode) throws BaseAppException; // NO_UCD (unused code)

	// Maneesh
	public String saveInvalidateDistrict(DistrictForm districtForm, HttpSession httpSession);

	public List<DistrictForm> getMergeDistrictWithSubDistrictWithSubdistrict(String listformat);

	// Maneesh
	public boolean changeDistrictforTemplate(DistrictForm districtForm, GovernmentOrderForm govtOrderForm, HttpServletRequest request, HttpSession httpSession) throws BaseAppException;

	// public List<StandardCodes> getStandardCode(StandardCodeForm
	// standardCodeForm) throws BaseAppException;
	// public List<StandardCodes> updateStandardCode(StandardCodeForm
	// standardCodeForm) throws BaseAppException;
	// public boolean updateStandardCode(StandardCodeForm standardCodeForm)
	// throws BaseAppException;
	public List<District> getTargetDistrictShiftSDistrict(int sourcedistrictCode, int stateCode) throws BaseAppException; // NO_UCD (unused code)

// TODO Remove unused code found by UCDetector
// 	public List<District> getTargetDistrictShiftSDistrictFinal(int sourcedistrictCode, int stateCode) throws BaseAppException;

	/*
	 * public List<District> getDistrictListExtend(Integer stateCode,Integer
	 * orgCode) throws BaseAppException;
	 */
	/*
	 * public List<District> getDistrictListbyStateCodeExtend(Integer stateCode,
	 * String districtList,Integer orgCode) throws BaseAppException;
	 */
	public List<District> getDistrictListExtend(Integer stateCode, Integer orgCode) throws BaseAppException; // NO_UCD (unused code)

	public List<District> getDistrictListExtendforBlock(Integer stateCode, Integer orgCode) throws BaseAppException; // NO_UCD (unused code)

	public List<District> getDistrictListbyStateCodeExtend(Integer stateCode, String districtList, Integer orgCode, char type) throws BaseAppException; // NO_UCD (unused code)

	// Maneesh
	public boolean modifyDistrictCrInfo(DistrictForm districtForm, HttpServletRequest request, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, boolean delflag, String deleteAttachmentId[], HttpSession httpSession)
			throws BaseAppException;

	public List<StandardCodeDataForm> getStandardCode(StandardCodeForm standardCodeForm) throws BaseAppException;

	public List<StandardCodeDataForm> updateStandardCode(StandardCodeForm standardCodeForm) throws BaseAppException;

	// Maneesh
	public int updateDistrictMap(Session session, int... params);
	
	
	public List<DistrictWiseLBReportBean> DistrictWiseLBReportDetail(Integer districtCode) throws BaseAppException;
	
	
	public List<District> getDistrictListbyStateCodeForListBoxExtended(int stateCode,int orgCode,int locatedatlevelCode) throws BaseAppException;
	
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
	public List<District> getDistrictListbyStateCodeExtended(int stateCode, // NO_UCD (unused code)
			String districtList, int orgCode, int locatedatlevelCode)
			throws BaseAppException;
	

	/**
	 * For Mark PESA Land Region
	 * @author Ripunj on 13-11-2014
	 * @param districtForm
	 * @param subDistrictForm
	 * @param villageForm
	 * @return
	 * @throws BaseAppException
	 */
	public boolean updatePesaStatus(String inputParam,Integer stateCode) throws BaseAppException ;
	
	/**
	 * For Mark Pesa Land Regions
	 * @author Ripunj on 18-11-2014
	 * @param districtCode
	 * @param totalVillage
	 * @param subdistrictCode
	 * @return
	 * @throws BaseAppException
	 */
	public List<Village> getVillageList(String districtCode, String totalVillage, String[] subdistrictCode) throws BaseAppException; // NO_UCD (unused code)
	

	/**
	 * New methods for the lgd impact issue
	 * 
	 * 
	 */
	public List<District> getDistrictListbyStateCodeForLocalBody(int slc) throws BaseAppException;
	/**
	 * 
	 * @param districtCode
	 * @return
	 * @throws BaseAppException
	 */
	public List<Subdistrict> getSubDistrictListbyDistrictForLocalBody(String districtCode) throws BaseAppException; // NO_UCD (unused code)
	

	/**
	 * New methods for the lgd impact issue
	 * Kirandeep 02/01/2015
	 * 
	 */
	public List<Subdistrict> getSubdistrictListbyDistrictInDistrictForLocalBody(String districtCode) throws Exception ; // NO_UCD (unused code)
	
	/*added on 05/01/2015 for local body impact by kirandeep*/
	public List<Village> getVillageListRemovingOldVillageForDistrictForm(String districtCode, String oldVillage, String[] subdistrictCode) throws BaseAppException; // NO_UCD (unused code)
	
	/**
	 * added on 06/01/2015 by kirandeep for localbody impact issue.
	 */	
	public List<District> getTargetDistrictShiftSDistrictForlocalbody(int sourceDistrictCode, int statecode) throws BaseAppException ; // NO_UCD (unused code)
	
	/**
	 * added on 07/01/2015 by kirandeep for localbody impact issue.
	 */
	public List<District> getDistrictListbyStateCodeForLocalbody(int stateCode,	String districtList) throws BaseAppException; // NO_UCD (unused code)
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
	public List<ParentHeirarchyBean> getHeirarchyByParentCodes(String entityCodes,Character type,Character afterPost)  throws BaseAppException; // NO_UCD (unused code)
	
	/**
	 * Code used for getting districtList for multiple districts
	 * @author Pooja
	 * @since 21-10-2015
	 * @param states
	 * @return
	 * @throws Exception
	 */
	public List<District> getDistrictListbyStates(String states) throws Exception; // NO_UCD (unused code)
	
	public List<District> getDistrictListbyCriteria(Integer entityCode,String entityType) throws Exception;
	public String getDistrictNameEnglishbyDistrictCode(Integer districtCode);
	
	public String getDistrictForGIS(Integer districtCode,String districtNameEnglish,String isShowOnlyBoundary)throws IOException;
	
	public boolean distInvalFnAfterCreateMulDist(Integer districtCode,Integer userId,Date effectiveDate)throws Exception;
	
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType,int entityCode,int entityVersion,int minorVersion)throws BaseAppException;
	
	public Response saveEffectiveDateEntityDistrict(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId)throws Exception;
}
