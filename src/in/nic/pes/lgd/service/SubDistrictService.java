package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictHistory;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.forms.AssemblyDataForm;
import in.nic.pes.lgd.forms.AssemblyForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.ParliamentDataForm;
import in.nic.pes.lgd.forms.ParliamentForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.ws.InvalidateSubdistrictForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exception.BaseAppException;

public interface SubDistrictService {



	public int publishMapLandRegion(SubDistrictForm sdForm, int sdCode, int sdVersion, Session session) throws Exception;

	public boolean save(SubDistrictForm sdForm) throws Exception;

	public abstract List<Object> getSubDistrictFormList(SubDistrictForm sdForm) throws Exception;

	public List<Subdistrict> getSubDistrictList(int districtCode) throws Exception;

	public List<SubDistrictForm> viewSubDistrictDetails() throws Exception;

	public abstract List<District> getDistrictListbyStateCode(int stateCode) throws Exception;

	public List<Subdistrict> getTargetSubDistrictListbySubDistrict(int subdistrictCode, int districtCode) throws Exception;

	public Subdistrict getById(Integer subDistrictCode) throws Exception;

	public List<Subdistrict> getSubDistrictListBySubDistCode(int subdistrictCode) throws Exception;

	public List<Subdistrict> getSubDistrictViewList(String query) throws Exception;

	

	public abstract boolean saveReplaces(int id, int lrReplaces, int sdCode, int sdVersionCode, Session session) throws Exception;

	public abstract boolean saveReplacedBy(int id, int lrReplacedBy, int sdCode, int sdVersionCode, Session session) throws Exception;

	// modify
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode, Session session) throws Exception;



	

	public boolean modifyParliamentConstituencyInfo(ParliamentForm parliamentform, HttpServletRequest request, HttpSession httpSession) throws Exception;

	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws Exception;

	public abstract List<Village> getVillageDetailforReorganize(String listVillCode) throws Exception;

	
	
	public List<SubdistrictHistory> getSubDistrictHistoryDetail(char subdistrictNameEnglish, int subdistrictCode) throws Exception;

	public abstract boolean renameContributedSD(SubDistrictForm SDForm, Session session) throws Exception;

	boolean invalidateLoop(String subdistrictCode, String VillagesCodes, HttpSession httpSession) throws Exception;

	boolean invalidateSubDistrict(String subdistrictCodes, int subdistrictCode, long roleCode, String villageCodes) throws Exception;

	// public boolean invalidateSubDistrictCall(String sourceSubdistCode, int
	// userId, String keyAppend,Date effectiveDate, int orderNo,Date
	// orderDate,Date gazPubDate)throws Exception;
	String getInvalidateVillageSubdistricts() throws Exception;

	String getInvalidateVillages() throws Exception;

	InvalidateSubdistrictForm getInvalidateDraftSubdistrict(String filePath) throws Exception;

	public abstract List<Subdistrict> getSubDistrictListbyDistrictCode(int districtCode, String subdistrictList) throws Exception;

	public Subdistrict getSingleSubdistrictDetailsMaxVersion(int subdistrictCode) throws Exception;

	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictName(int districtCode) throws Exception;

	List<Subdistrict> getSubDistrictListView(String sQuery) throws Exception;

	public void clearAll() throws Exception;

	public void saveAsDraftInvalidateSubdistrict(SubDistrictForm invalidateSubdistrict, HttpSession httpSession) throws Exception;

	String getInvalidateSubdistricts() throws Exception;

	public GovernmentOrder saveDataInGovtOrder(SubDistrictForm subDistrictForm, Session session) throws Exception;

	public void saveDataInAttachment(GovernmentOrder govtOrder, SubDistrictForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws Exception;

	public List<Subdistrict> getSubDistListbyDistCodeShift(int districtCode) throws Exception;

	public List<Subdistrict> getTargetSubDistListforShift(int subdistrictCode, int districtCode) throws Exception;

	public List<ParliamentDataForm> getParliamentConstituencyDetail(int ParliamentCode) throws Exception;

	public List<AssemblyDataForm> getAssemblyConstituencyDetail(int ParliamentCode) throws Exception;

	public List<VillageDataForm> getVillageDetail(int subdistrictCode) throws Exception;

	public boolean modifyAssemblyConstituencyInfo(AssemblyForm assemblyForm, HttpServletRequest request, HttpSession httpSession) throws Exception;

	// new Modify
	/*
	 * public boolean changeSubDistrict(SubDistrictForm subdistrictForm,
	 * GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList,
	 * HttpServletRequest request)throws Exception;
	 */

	/*
	 * public boolean changeSubDistrictforTemplate(SubDistrictForm
	 * subdistrictForm, GovernmentOrderForm govtOrderForm, HttpServletRequest
	 * request)throws Exception;
	 */

	/*
	 * public boolean modifySubDistrictCrInfo(SubDistrictForm subdistrictForm,
	 * HttpServletRequest request, List<AttachedFilesForm>
	 * attachedList,List<AttachedFilesForm> attachedMapList, HttpSession
	 * httpSession)throws Exception;
	 */

	/*List<CheckAuthorization> getSubDistrictListAuthentication(int districtCode, HttpSession httpSession) throws Exception;
*/
	/* Retrieve files from the attachment table */

	public List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws Exception;

	/* Retrieving the Map details attachment from the database */

	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode) throws Exception;

	public List<Headquarters> getHeadquterDetails(Integer regionCode, String region_type) throws Exception;

	public boolean changeSubDistrict(SubDistrictForm subdistrictForm, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request) throws Exception;

	public int saveDataInMap(SubDistrictForm subDistrictForm, List<AttachedFilesForm> validFileMap, HttpSession httpSession, Session session);

	public String insertSubDistrict(HttpServletRequest request, HttpSession httpSession, SubDistrictForm subDistrictForm, Session session);

	public void saveOrUpdate(int subDistrictId, int mapAttachmentId, Session session);

	public Subdistrict getById(Integer subDistrictCode, Session session) throws Exception;

	/*
	 * public int invalidateSubDistrictCall(String sourceSubdistCode, int
	 * userId,String keyAppend, Date effectiveDate, String orderNo,Date
	 * orderDate, Date gazPubDate)throws Exception;
	 */
	String invalidateSubDistrictCall(String sourceSubdistCode, int userId, String keyAppend, java.util.Date effectiveDate, String orderNo, java.util.Date orderDate, java.util.Date gazPubDate) throws Exception;

	public boolean changeSubDistrictforTemplate(SubDistrictForm subdistrictForm, GovernmentOrderForm govtOrderForm, HttpServletRequest request, HttpSession httpSession) throws Exception;

	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameExtend(Integer districtCode, Integer orgCode) throws Exception;

	public boolean subDistrictExist(int disCode, String subDistrictName) throws Exception;

	public boolean modifySubDistrictCrInfo(SubDistrictForm subdistrictForm, HttpServletRequest request, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, boolean delflag, String deleteAttachmentId[],
			HttpSession httpSession) throws Exception;


	/**
	 * Get SubDistrict List For Extended Department
	 * 
	 * @author Ripunj on 10-10-2014
	 * @param districtCode
	 * @param orgCode
	 * @param locatedAtLevelCode
	 * @return
	 * @throws BaseAppException
	 */
	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameExtended(int districtCode, int orgCode, int locatedAtLevelCode) throws BaseAppException;

	/**
	 * Get SubDistrict List For Extended Department without
	 * selected @subdistrictList
	 * 
	 * @author Ripunj on 16-10-2014
	 * @param districtCode
	 * @param subdistrictList
	 * @param orgCode
	 * @param locatedAtLevelCode
	 * @return
	 * @throws BaseAppException
	 */
	public List<Subdistrict> getSubDistrictListbyDistrictCodeExtended(int districtCode, String subdistrictList, Integer orgCode, Integer locatedAtLevelCode) throws BaseAppException;

	/* added on 31/12/2014 for the localbody impact by kirandeep */
	public List<District> getDistrictListbyStateCodeForLocalBody(int stateCode);

	/* added on 31/12/2014 for the localbody impact by kirandeep */
	public List<Subdistrict> getSubDistrictListForLocalbody(int districtCode) throws Exception;

	/***
	 * 
	 * added by kirandeep on 07/01/2015 for the localbody impact
	 * 
	 * 
	 */
	public List<Subdistrict> getTargetSubDistListforShiftForLocalbody(int subdistrictCode, int districtCode) throws Exception;

	/**
	 * 
	 * added by kirandeep on 08/01/2015 for localbody impact
	 * 
	 * @param districtCode
	 * @return
	 * @throws BaseAppException
	 */

	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameForLocalBody(int districtCode) throws BaseAppException;

	/**
	 * 
	 * added by kirandeep on 08/01/2015
	 * 
	 * @return
	 * @throws BaseAppException
	 */

	public List<Subdistrict> getSubDistrictListbyDistrictCodeForLocalBody(int districtCode, String subdistrictList) throws BaseAppException;

	public List<Subdistrict> getSubdistrictList(Integer districtCode) throws Exception;

	public List<SubdistrictHistory> getSubDistHistoryReport(char subdistrictNameEnglish, int subdistrictCode) throws BaseAppException;

	List<Village> getvillagesInDraftMode(Integer subdistrictCode) throws BaseAppException;

	/**
	 * 
	 * added by Anju Gupta on 023/03/2015
	 * 
	 * @return ListSubDistrict for mark pesa @throws
	 */
	public List<Subdistrict> getSubdistrictListBySLCfprMarkPesa(int slc) throws Exception;

	public List<Subdistrict> getSubDistrictListbyCreteria(String districtCodes, String subDistrictCodes, Integer districtCode) throws Exception;

	public List<SubdistrictDataForm> getSubdistrictDetailsModify(int subdistrictCode) throws Exception;
	
	public Response saveEffectiveDateEntitySubdistrict(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId)throws Exception;
}
