package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.ParliamentConstituencyId;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictHistory;
import in.nic.pes.lgd.bean.SubdistrictPK;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.forms.ParliamentForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exception.BaseAppException;

public interface SubDistrictDAO {

	public abstract Subdistrict getSubDistrictXml()throws BaseAppException;
	
	public boolean publishSubDistrict(Subdistrict subdistric, Session ses)throws BaseAppException;

	public abstract int getMaxSubDistrictCode()throws BaseAppException;

	public abstract int getMaxSubDistrictVersion(int subDistrictCode)throws BaseAppException;
	
	
	public abstract int getPcVersion(int pcCode)throws BaseAppException;

	
	public abstract int getMaxParliamentConstituencyVersion(int ParliamentCode)throws BaseAppException;


	public List<Subdistrict> getSubDistrictListby(int subDistrictCode)throws BaseAppException;

	public List<SubDistrictForm> viewSubDistrictDetails()throws BaseAppException;

	public List<Subdistrict> viewSubDistrictDetails(String sql)throws BaseAppException;

	public List<Subdistrict> getSubDistrictListbyDistrictCode(int districtCode)throws BaseAppException;

	public List<Subdistrict> getTargetSubDistrictList(int subdistrictCode,
			int districtCode)throws BaseAppException;

	public boolean updateLrReplaces(int lrReplaces,
			SubdistrictPK subdistrictPK, Session session)throws BaseAppException;

	public boolean updateLrReplacedBy(int lrReplacedBy,
			SubdistrictPK subdistrictPK, Session session)throws BaseAppException;

	public boolean updateIsActive(boolean isActive,
			SubdistrictPK subdistrictPK, Session session)throws BaseAppException;

	public boolean addNewVersion(SubdistrictPK subdistrictPK, Session session)throws BaseAppException;

	// For Modify Sub District
	public List<SubdistrictDataForm> getSubdistrictDetailsModify()throws BaseAppException;

	public List<Subdistrict> getSubdistrictDetailsModify(int subdistrictCode, Session session)throws BaseAppException;

	
	public List<ParliamentConstituency> getParliamentDetailsModify(int ParliamentCode)throws BaseAppException;

	
	public int getMaxSubDistrictCode(String query)throws BaseAppException;

	/*public void ChangeSubDistrict(int subdistrictCode,
			String subdistrictNameEnglish, int userId,
			String subdistrictNameLocal, String aliasEnglish,
			String aliasLocal, Session session,String orderNo,Date orderDate,Date effectiveDate,String govOrder,Date govPubDate) throws BaseAppException; 
*/
	
	/*public Integer ChangeSubDistrict(int subdistrictCode,
			String subdistrictNameEnglish, int userId,
			String subdistrictNameLocal, String aliasEnglish,
			String aliasLocal, Session session,String orderNo,Date orderDate,Date effectiveDate,String govOrder,Date govPubDate) throws BaseAppException; */
		
	
	
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode) throws BaseAppException ; 

	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode,Session session) throws BaseAppException;


	public boolean updateMapLanRegion(int mapCode, String coordinates,
			int subDistrictCode, Session session1) throws BaseAppException;

	public boolean modifySubDistrictInfo(SubDistrictForm subDistrictForm,String cord1,
			int subdistrictCode, int map_landRegionCode,
			Session session) throws BaseAppException;

	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws BaseAppException;

	// get modify
	List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion) throws BaseAppException;
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType,int entityCode,int entityVersion,Session session) throws BaseAppException;

	public List<Headquarters> getHeadquarterModify(int subdistrictCode,
			int subdistrictversion,Session session) throws BaseAppException;

	public List<Subdistrict> getSubDistrictViewList(String query) throws BaseAppException;

	public abstract Subdistrict getSubDistrictDetail(
			SubdistrictPK subdistrictPK, Session session) throws BaseAppException;

	public List<Subdistrict> getTargetSubDistrictListbySubDistrict(
			int subdistrictCode, int districtCode) throws BaseAppException;

	public abstract int getMaxDistrictCode() throws BaseAppException;

	public List<Subdistrict> getListVillageDetails(String query) throws BaseAppException;

	public boolean saveDistrict(Subdistrict district) throws BaseAppException;

	public boolean updateLReplaces(int lrReplacedby, SubdistrictPK subdistrictPK) throws BaseAppException;

	public boolean updateIsActive(SubdistrictPK subdistrictPK) throws BaseAppException;

	public List<District> getSubdistrictDetails(int subdistrictCode) throws BaseAppException;

	public int getMaxDistrictCode(String query)throws BaseAppException;

	public List<Subdistrict> getSubDistrictListbyDistrict(int districtCode) throws BaseAppException;

	public List<Subdistrict> getSubDistrict(int districtCode) throws BaseAppException;

	public List<Subdistrict> getVillageDetailsModify(int villageCode) throws BaseAppException;

	public List<Village> getSubdistrictListbyVillageCode(int districtCode) throws BaseAppException;

	public abstract boolean update(String query, Session session) throws BaseAppException;

	public boolean save(Subdistrict subdistrict, Session session) throws BaseAppException;

	public List<SubdistrictHistory> getSubDistrictHistoryDetail(
			char subdistrictNameEnglish, int subdistrictCode) throws BaseAppException;

	public int getMaxSubdistrictVersion(int subdistrictCode, Session session) throws BaseAppException;

	public abstract void SetGovermentOrderEntity(String subdistrictCode, char c) throws BaseAppException;

	public abstract boolean invalidateFunctionCall(String subdistrictCodes,
			int subdistrictCode, long roleCode, String villageCodes) throws BaseAppException;
	public String invalidateFunctionCallSubDistrict(String sourceSubdistCode, int userId, String keyAppend,Date effectiveDate, String orderNo,Date orderDate,Date gazPubDate)
			throws BaseAppException;

	public List<Subdistrict> getSubDistrictListbyDistrictCode(int districtCode,
			String subdistrictList) throws BaseAppException;

	int getMaxSubdistrictVersion(int subdistrictCode) throws BaseAppException;

	public abstract Subdistrict getSubdistrictDetailsMaxVersionByCode(
			int subdistrictCode, int subdistrictVersion) throws BaseAppException;

	public abstract List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictName(
			int districtCode) throws BaseAppException;

	List<Subdistrict> getSubDistrictList(String sQuery)throws BaseAppException;

	//public List<Attachment> getAttacmentdetail(int orderCode)throws BaseAppException;

	public boolean insertBulkSubdistrict(String query, Session session)throws BaseAppException;

	public List<SubdistrictHistory> getSubDistHistoryDetailShift(char subdistrictNameEnglish, int subdistrictCode) throws BaseAppException;
public abstract boolean updateAssembly(String query, Session session) throws BaseAppException;
	
	
	public List<AssemblyConstituency> getAssemblyList(int districtCode) throws BaseAppException;

	
	public boolean modifyParliamentInfo(ParliamentForm ParliamentForm,
			ParliamentConstituencyId ParliamentconstituencyId, int map_landRegionCode, Session session) throws BaseAppException;

	

	public abstract void ChangeParliamentConstituency(char constituencyType,int pcCode,
			String pcNameEnglish,int userid, String pcNameLocal, Integer eciCode) throws BaseAppException;

	
	
	public List<AssemblyConstituency> getAssemblyDetailsModify(int ParliamentCode) throws BaseAppException;

	public List<Village> getVillageModify(int subdistrictCode) throws BaseAppException;

	public abstract int getMaxAssemblyVersion(int subDistrictCode) throws BaseAppException;
	
	public abstract int getMaxStandardcodesVersion(int subDistrictCode) throws BaseAppException;

	public void ChangeStandardCode(char landregionCodes, int pcCode, String cencouscode1,Integer cencouscode, String sscode) throws BaseAppException;
	public int getRecordsforSubdistrictName(String subdistrictName) throws BaseAppException;

	/*Retrieve files from the attachment table*/
	public List<Attachment> getAttacmentdetail(int orderCode) throws BaseAppException;
	/*Retrieving the Map details attachment from the database*/
	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType,int entityCode, Session session) throws BaseAppException;
	
	
	public int saveDataInMap(SubDistrictForm subDistrictForm,
			List<AttachedFilesForm> validFileMap, HttpSession httpSession, Session session);

	public String insertSubDistrict(HttpServletRequest request,
			HttpSession httpSession, SubDistrictForm subDistrictForm, Session session);

	public  Subdistrict getById(Integer subDistrictCode, Session session);

	public void saveOrUpdate(int subDistrictId,int mapAttachmentId, Session session);
	public List<Subdistrict> getSubDistrictbySubdistCode(int subdistrictCode) throws BaseAppException;
	/*public  Integer ChangeSubDistrict(int subdistrictCode,
			String subdistrictNameEnglish, int userId,
			String subdistrictNameLocal, String aliasEnglish,
			String aliasLocal, Session session,String orderNo,Date orderDate,Date effectiveDate,String govOrder,Date govPubDate,Integer slc) throws BaseAppException;*/
	public List<Subdistrict> getSubDistrictLists(int districtCode) throws BaseAppException;
	
	
	public String ChangeSubDistrict(int subdistrictCode,
			String subdistrictNameEnglish,String subdistrictNameEnglishch, int userId,
			String subdistrictNameLocal, String aliasEnglish,
			String aliasLocal, Session session,String orderNo,Date orderDate,Date effectiveDate,String govOrder,Date govPubDate,Integer slc) throws BaseAppException;
	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameExtend(
			Integer districtCode,Integer orgCode) throws BaseAppException;
	
	public boolean subDistrictExist(int disCode,String subDistrictName) throws BaseAppException;
	
	public abstract List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameExtended(
			int districtCode,int orgCode,int locatedAtLevelCode) throws BaseAppException;

	/**
	 * Get SubDistrict List For Extended Department without selected @subdistrictList
	 * @author Ripunj on 16-10-2014
	 * @param districtCode
	 * @param subdistrictList
	 * @param orgCode
	 * @param locatedAtLevelCode
	 * @return
	 * @throws BaseAppException
	 */
	public List<Subdistrict> getSubDistrictListbyDistrictCodeExtended(int districtCode,
			String subdistrictList, Integer orgCode, Integer locatedAtLevelCode)
			throws BaseAppException;
	
	/**
	 * Added by Kirandeep on 11/12/2014 
	 * for localbody impact
	 */
	/*param modified by pooja on 13-08-2015*/
	public List<Subdistrict> getSubDistrictListbyDistrictForLocalBody(String districtCode);
	

	/**
	 * Added by Ripunj on 09-12-2014
	 * Get SubDistrict LIst For Local Body Impact 
	 */
	public List<Subdistrict> getSubDistListbyDistrictCodeCov(int districtCode) throws Exception;
	
	/* added on 31/12/2014 for the localbody impact by kirandeep*/
	public List<Subdistrict> getSubDistrictListForLocalbody(int districtCode)throws Exception;
	
	/* added on 02/01/2015 for the localbody impact by kirandeep*/
	public List<Subdistrict> getSubDistrictListbyDistrictInDistrictForLocalBody(int districtCode);
	
	
	/*added on 05/01/2015 for local body impact by kirandeep*/
	public List<Village> getSubdistrictListbyVillageCodeForDistrictForm(int districtCode) throws BaseAppException;

	/**
	 * 
	 * added by kirandeep on 08/01/2015
	 * @param districtCode
	 * @return
	 * @throws BaseAppException
	 */
	
	List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameForLocalBody(
			int districtCode) throws BaseAppException;
	
	/**
	 * 
	 * added by kirandeep on 08/01/2015
	 * 
	 * @return
	 * @throws BaseAppException
	 */	

	public List<Subdistrict> getSubDistrictListbyDistrictCodeForLocalBody(
			int districtCode, String subdistrictList) throws BaseAppException;
	
	public List<Subdistrict> getSubdistrictList(Integer districtCode) throws Exception;
	public List<SubdistrictHistory> getSubDistHistoryReport(
			char subdistrictNameEnglish, int subdistrictCode) throws BaseAppException;
	
	public List<Village> getvillagesInDraftMode(Integer subdistrictCode) throws BaseAppException ;
	
	
	/**
	 * 
	 * added by Anju Gupta on 23/03/2015
	 * 
	 * @return SubdistrictList for MarkPesa
	 * @throws 
	 */	
	public List<Subdistrict> getSubdistrictListBySLCfprMarkPesa(int slc) throws Exception;
	
	public List<Subdistrict> getSubDistrictListbyCreteria(List<Integer>  districtList,List<Integer> subDistrictCodeList,Integer districtCode) throws Exception;
	
	public Response saveEffectiveDateEntitySubdistrict(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId)throws Exception;
	
}
