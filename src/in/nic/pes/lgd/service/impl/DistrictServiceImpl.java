package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictHistory;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.DistrictWiseLBReportBean;
import in.nic.pes.lgd.bean.GazettePublication;
import in.nic.pes.lgd.bean.GazettePublicationDateSave;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.LandregionReplacedby;
import in.nic.pes.lgd.bean.LandregionReplaces;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.ParentHeirarchyBean;
import in.nic.pes.lgd.bean.ParliamentConstituencymodify;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.StandardCodes;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictPK;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.common.EncryptionUtil;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.GovernmentOrderDAO;
import in.nic.pes.lgd.dao.HeadquartersDAO;
import in.nic.pes.lgd.dao.LandRegionReplacedByDAO;
import in.nic.pes.lgd.dao.LandRegionReplacesDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.MapLandRegionDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.forms.DistrictDataForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.StandardCodeDataForm;
import in.nic.pes.lgd.forms.StandardCodeForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.ShiftService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.CurrentDateTime;
import in.nic.pes.lgd.utils.DatePicker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.itextpdf.tool.xml.css.parser.state.Properties;
import com.org.ep.exception.BaseAppException;

public class DistrictServiceImpl implements DistrictService {
	private static final Logger log = Logger.getLogger(DistrictServiceImpl.class);
	@Autowired
	private CommonService commonService;

	@Autowired
	private GovernmentOrderDAO govtOrderDAO;

	@Autowired
	private StateService stateService;

	@Autowired
	private DistrictDAO districtDAO;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ShiftService shiftService;

	@Autowired
	private GovernmentOrderService govtOrderService;

	@Autowired
	private SubDistrictDAO subdistrictDAO;

	@Autowired
	private MapLandRegionDAO mapLandRegionDAO;

	@Autowired
	private ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	private VillageDAO villageDAO;

	@Autowired
	private LandRegionReplacedByDAO landRegionReplacedByDAO;

	@Autowired
	private LandRegionReplacesDAO landRegionReplacesDAO;

	@Autowired
	private HeadquartersDAO headquartersDAO;

	@Autowired
	private VillageService villageService;

	@Autowired
	private SubDistrictService subistrictService;

	@Autowired
	private LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CurrentDateTime dateTimeUtil;

	private int mapCd;
	private Date timestamp = DatePicker.getDate("2011-09-26");
	private int sdCode;
	private int sdVersionCode;
	private int distritVersion;
	private int minorVersion;
	private Transaction tx = null;
	private int districtCode = 0;
	private int landCoveredRegionCode = 0;
	private String effectiveDate = "2011-12-12";
	private District newDistrict = null;
	private int villageCode = 0;
	private int userId = 1000;
	private long createdBy = 1000;
	private Map<String, String> sdVillMap = new HashMap<String, String>();

	@Override
	//@Transactional
	public boolean publishSubdistrit(DistrictForm sdForm, Session session) throws BaseAppException {
		boolean isCommited = false;

		newDistrict = new District();
		try {
			sdCode = subdistrictDAO.getMaxSubDistrictCode() + 1;
			sdVersionCode = 1;

			districtCode = districtDAO.getMaxDistrictCode("select COALESCE(max(district_code),1) from district");
			if (districtCode == 0) {
				districtCode = 1;
			} else {
				districtCode = districtCode + 1;
			}
			distritVersion = 1;
			
			minorVersion=1;

			villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
			if (villageCode == 0) {
				villageCode = 1;
			} else {
				villageCode = villageCode + 1;
			}

			/*
			 * districtCode = Integer.parseInt(sdForm .getSubDistrictList());
			 * log.debug("Sub district code is"+districtCode); try { sList =
			 * districtDAO.getSubdistrictDetails(
			 * "from Subdistrict where subdistrictCode='" + districtCode +
			 * "' and isactive=true"); } catch (Exception e2) {
			 * e2.printStackTrace(); } subdistricVersion =
			 * sList.get(0).getSubdistrictVersion();
			 * log.debug("Sub district version  is"+districtCode);
			 */
			List<Subdistrict> fullContributedList = null;
			List<Subdistrict> partContributedList = null;

			List<District> fullContributedListDistrit = new ArrayList<District>();

			List<District> partContributedListDistrit = new ArrayList<District>();

			String coveredAreaCode = sdForm.getDistrictNameEnglish();
			

			List<Village> vFullList = null;
			List<Village> vPartList = null;

			fullContributedList = new ArrayList<Subdistrict>();
			partContributedList = new ArrayList<Subdistrict>();
			vFullList = new ArrayList<Village>();
			vPartList = new ArrayList<Village>();
			
			//int government_order_code = districtDAO.getMaxRecords("select COALESCE(max(order_code),1) from government_order");
			//int map_landRegionCode = districtDAO.getMaxRecords("select COALESCE(max(map_attachment_code),1) from map_attachment");

			int lrReplacedBy = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
			int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
			int rByid = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
			int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
			int headquarterCode = headquartersDAO.getMaxHeadquartersCode();

			String[] temp = coveredAreaCode.split(",");
			for (int i = 0; i < temp.length; i++) {
				
				if (temp[i].contains("FULL")) {
					landCoveredRegionCode = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					List<District> lstTemp = null;
					lstTemp = new ArrayList<District>();
					try {
						lstTemp = districtDAO.getListVillageDetails("from District where district_code='" + landCoveredRegionCode + "' and isactive=true");
					} catch (Exception e) {

						log.error("Exception-->" + e);
					}
					fullContributedListDistrit.add(lstTemp.get(0));
				}
				if (temp[i].contains("PART")) {
					landCoveredRegionCode = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					List<District> lstTemp = null;
					lstTemp = new ArrayList<District>();
					try {
						lstTemp = districtDAO.getListVillageDetails("from District where district_code='" + landCoveredRegionCode + "' and isactive=true");
					} catch (Exception e) {

						log.error("Exception-->" + e);
					}
					partContributedListDistrit.add(lstTemp.get(0));
				}
			}

			/*
			 * subdistrictCode = Integer.parseInt(sdForm.getStateNameEnglish());
			 * try { sdList =
			 * districtDAO.getSubdistrictDetails(subdistrictCode); } catch
			 * (Exception e2) { e2.printStackTrace(); }
			 */

			if (sdForm.getContributedSubDistricts() != null) {
				/* subdistricVersion = sdList.get(0).getStateVersion(); */
				String[] selectedSubDistricts = sdForm.getContributedSubDistricts().split(",");
				for (int i = 0; i < selectedSubDistricts.length; i++) {
					int sCodeFull = Integer.parseInt(selectedSubDistricts[i].substring(0, selectedSubDistricts[i].length() - 4));

					List<Subdistrict> lstTemp = null;
					lstTemp = new ArrayList<Subdistrict>();
					try {
						lstTemp = subdistrictDAO.viewSubDistrictDetails("from Subdistrict s where subdistrictCode=" + sCodeFull + " and isactive=true");
					} catch (Exception e) {

						log.error("Exception-->" + e);
					}
					if (selectedSubDistricts[i].contains("FULL")) {
						fullContributedList.add(lstTemp.get(0));
					} else if (selectedSubDistricts[i].contains("PART")) {
						partContributedList.add(lstTemp.get(0));
					}
				}
			}
			if (sdForm.getContributedVillages() != null) {
				if (sdForm.getContributedVillages() != null && sdForm.getContributedVillages().length() > 0) {
					int vCode = 0;
					String[] selectedVillages = sdForm.getContributedVillages().split(",");
					for (int v = 0; v < selectedVillages.length; v++) {
						if (selectedVillages[v].contains("FULL") || selectedVillages[v].contains("PART")) {
							vCode = Integer.parseInt(selectedVillages[v].substring(0, selectedVillages[v].length() - 4));
						} else {
							vCode = Integer.parseInt(selectedVillages[v]);
						}
						List<Village> lstTemp = null;
						lstTemp = new ArrayList<Village>();

						lstTemp = villageDAO.getListVillageDetails("from Village where village_code=" + vCode + " and isactive=true");
						if (selectedVillages[v].contains("FULL")) {
							vFullList.add(lstTemp.get(0));
						} else if (selectedVillages[v].contains("PART")) {
							vPartList.add(lstTemp.get(0));
						}
					}
				}
			}

			/*
			 * try { governmentOrder.setOrderCode(government_order_code);
			 * governmentOrder.setOrderDate(sdForm.getOrderDate());
			 * governmentOrder.setEffectiveDate(sdForm.getEffectiveDate());
			 * governmentOrder.setGazPubDate(sdForm.getGazPubDate());
			 * governmentOrder.setCreatedon(timestamp);
			 * governmentOrder.setDescription("LGD DETAILS-V");
			 * governmentOrder.setIssuedBy("GOVT");
			 * governmentOrder.setCreatedby(createdby);
			 * governmentOrder.setLastupdated(timestamp);
			 * governmentOrder.setLastupdatedby("2");
			 * governmentOrder.setLevel("OK");
			 * governmentOrder.setOrderNo(sdForm.getOrderNo());
			 * governmentOrder.setStatus('A');
			 * governmentOrder.setUserId(createdby); MultipartFile
			 * filename=null; filename=sdForm.getFilePath();
			 * log.debug("filename is "+filename); writeMap(filename,request);
			 * String filePath=request.getRealPath("/")+
			 * filename.getOriginalFilename() ;
			 * governmentOrder.setOrderPath(filePath);
			 * log.debug("Filepath will display here---"+filePath);
			 * governmentOrder.setXmlDbPath("mmmmmm");
			 * governmentOrder.setXmlOrderPath("sdsd");
			 * 
			 * districtDAO.saveOrderDetails(governmentOrder, session); } catch
			 * (Exception e) { log.error("Exception-->"+e); }
			 */

			// End here government details
			// Start Map Details

			try {

				if (fullContributedListDistrit.size() > 0 && partContributedListDistrit.size() == 0) {
					mapCd = this.publishMapLandRegion(sdForm, districtCode, distritVersion, session);
					this.publishDistrict(sdForm, districtCode, distritVersion, minorVersion, session);
					this.saveHeadquarters(sdForm, headquarterCode, sdCode, sdVersionCode, session);

					// Land Region Replaced By

					this.saveReplacedBy(rByid, lrReplacedBy, districtCode, distritVersion, session);
					for (int k = 0; k < fullContributedListDistrit.size(); k++) {
						// Land Region Replaces
						this.saveReplaces(id, lrReplaces, fullContributedListDistrit.get(k).getDistrictCode(), fullContributedListDistrit.get(k).getDistrictVersion(), session);

						DistrictPK sdpk = new DistrictPK(fullContributedListDistrit.get(k).getDistrictCode(), fullContributedListDistrit.get(k).getDistrictVersion(),fullContributedListDistrit.get(k).getMinorVersion());
						districtDAO.updateIsActive(false, sdpk, session);
						// Subdistrict impacting code

						this.saveNewSubdistritVersion(districtCode, fullContributedListDistrit.get(k).getDistrictCode(), fullContributedListDistrit.get(k).getDistrictVersion(), session);

						// Village impacting code

						/*
						 * this.saveNewVillageVersion(fullContributedList.get(k).
						 * getSubdistrictCode(), sdCode, sdVersionCode,
						 * session);
						 */
						id++;
					}
					DistrictPK sdpk = new DistrictPK(districtCode, distritVersion,minorVersion);
					districtDAO.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																			// detail
																			// in
					// sub
					// district
					// table
					isCommited = true;
				}

				else if (partContributedListDistrit.size() > 0 && fullContributedList.size() > 0) {
					mapCd = this.publishMapLandRegion(sdForm, districtCode, distritVersion, session);
					this.publishDistrict(sdForm, districtCode, distritVersion, mapCd, session);
					this.saveHeadquarters(sdForm, headquarterCode, sdCode, sdVersionCode, session);
					this.saveReplacedBy(rByid, lrReplacedBy, districtCode, distritVersion, session);
					for (int k = 0; k < partContributedListDistrit.size(); k++) {
						this.saveReplaces(id, lrReplaces, partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(), session);

						DistrictPK sdpk = new DistrictPK(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion());
						// code to get new sd version
						this.saveNewDistrictVersion(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion(), lrReplacedBy, session);
						// -------------------

						districtDAO.updateIsActive(false, sdpk, session);
						saveNewSubdistrictVersionPartContri(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictCode(), distritVersion,minorVersion, session);
						id++;
					}
					for (int k = 0; k < fullContributedList.size(); k++) {

						this.saveNewSubdistritfordist(districtCode, fullContributedList.get(k).getTlc(), fullContributedList.get(k).getTlc(), session);
						SubdistrictPK sdpk = new SubdistrictPK(fullContributedList.get(k).getTlc(), fullContributedList.get(k).getTlc(), fullContributedList.get(k).getMinorVersion());
						districtDAO.updateActive(false, sdpk, session);
					}

					DistrictPK sdpk = new DistrictPK(districtCode, distritVersion,minorVersion);
					districtDAO.updateLrReplaces(lrReplaces, sdpk, session);
					isCommited = true;
				}

				else if (partContributedListDistrit.size() > 0 && partContributedList.size() > 0 && vFullList.size() > 0) {
					mapCd = this.publishMapLandRegion(sdForm, districtCode, distritVersion, session);
					this.publishDistrict(sdForm, districtCode, distritVersion, mapCd, session);
					this.saveHeadquarters(sdForm, headquarterCode, sdCode, sdVersionCode, session);

					// Land Region Replaced By

					this.saveReplacedBy(rByid, lrReplacedBy, districtCode, distritVersion, session);
					for (int k = 0; k < partContributedListDistrit.size(); k++) {

						this.saveReplaces(id, lrReplaces, partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(), session);

						DistrictPK sdpk = new DistrictPK(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion());
						// code to get new sd version
						this.saveNewDistrictVersion(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion(), lrReplacedBy, session);
						// -------------------

						districtDAO.updateIsActive(false, sdpk, session);
						saveNewSubdistrictVersionPartContri(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictCode(), distritVersion,minorVersion, session);
						id++;

					}

					for (int k = 0; k < partContributedList.size(); k++) {

						this.saveNewSubdist(districtCode, partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), session);

					}
					for (int l = 0; l < vFullList.size(); l++) {
						for (int k = 0; k < partContributedList.size(); k++) {
							this.saveNewvillage(partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), vFullList.get(l).getTlc(), vFullList.get(l).getTlc(), vFullList.get(l).getVlc(), vFullList.get(l).getVlc(), session);

							VillagePK villagepkk = new VillagePK(vFullList.get(k).getVlc(), vFullList.get(k).getVlc(),vFullList.get(k).getMinorVersion());
							districtDAO.updatevillage(false, villagepkk, session);
						}
					}
					// this.saveNewvillver(vFullList, session);

					// this.saveNewvillage(vFullList.get(l).getSubDistrictCode(),vFullList.get(l).getSubDistrictVersion(),
					// vFullList.get(l).getVillageCode(),
					// vFullList.get(l).getVillageVersion(), session);

					DistrictPK sdpk = new DistrictPK(districtCode, distritVersion,minorVersion);
					districtDAO.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																			// detail
																			// in
					// sub
					// district
					// table
					isCommited = true;
				}

				else if (partContributedListDistrit.size() > 0 && partContributedList.size() > 0 && vPartList.size() > 0) {
					mapCd = this.publishMapLandRegion(sdForm, districtCode, distritVersion, session);
					this.publishDistrict(sdForm, districtCode, distritVersion, mapCd, session);
					this.saveHeadquarters(sdForm, headquarterCode, sdCode, sdVersionCode, session);

					// Land Region Replaced By

					this.saveReplacedBy(rByid, lrReplacedBy, districtCode, distritVersion, session);
					for (int k = 0; k < partContributedListDistrit.size(); k++) {

						this.saveReplaces(id, lrReplaces, partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(), session);

						DistrictPK sdpk = new DistrictPK(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion());
						// code to get new sd version
						this.saveNewDistrictVersion(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion(), lrReplacedBy, session);
						// -------------------

						districtDAO.updateIsActive(false, sdpk, session);
						saveNewSubdistrictVersionPartContri(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictCode(), distritVersion,minorVersion, session);
						id++;

					}
					for (int k = 0; k < partContributedList.size(); k++) {

						this.saveNewSubdist(districtCode, partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), session);
					}

					for (int l = 0; l < vPartList.size(); l++) {
						for (int k = 0; k < partContributedList.size(); k++) {
							this.saveNewvillage(partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), vPartList.get(l).getTlc(), vPartList.get(l).getTlc(), vPartList.get(l).getVlc(), vFullList.get(l).getVlc(), session);

							/*
							 * VillagePK sdpk = new
							 * VillagePK(fullContributedList
							 * .get(k).getSubdistrictCode(),
							 * fullContributedList.get(k)
							 * .getSubdistrictVersion());
							 * districtDAO.updatevillage(false, sdpk, session);
							 */
						}
					}
					DistrictPK sdpk = new DistrictPK(districtCode, distritVersion,minorVersion);
					districtDAO.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																			// detail
																			// in
					// sub
					// district
					// table
					isCommited = true;
				}

				else if (fullContributedListDistrit.size() == 0 && partContributedListDistrit.size() > 0) {
					mapCd = 0;
					mapCd = this.publishMapLandRegion(sdForm, districtCode, distritVersion, session);
					this.publishDistrict(sdForm, districtCode, distritVersion, mapCd, session);
					this.saveHeadquarters(sdForm, headquarterCode, sdCode, sdVersionCode, session);
					// Land Region Replaced By

					this.saveReplacedBy(rByid, lrReplacedBy, districtCode, distritVersion, session);
					for (int k = 0; k < partContributedListDistrit.size(); k++) {
						// Land Region Replaces

						this.saveReplaces(id, lrReplaces, partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(), session);

						DistrictPK sdpk = new DistrictPK(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion());
						// code to get new sd version
						this.saveNewDistrictVersion(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion(), lrReplacedBy, session);
						// -------------------

						// Subdistrict impacting code
						this.saveNewSubdistritVersion(districtCode, partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(), session);

						// Village impacting code

						/*
						 * this.saveNewVillageVersion(partContributedList.get(k).
						 * getSubdistrictCode(),
						 * partContributedListDistrit.get(k).getDistrictCode(),
						 * partContributedListDistrit
						 * .get(k).getDistrictVersion()+1, session);
						 */// --------------------

						districtDAO.updateIsActive(false, sdpk, session);
						saveNewSubdistrictVersionPartContri(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictCode(), distritVersion,minorVersion, session);

						/*
						 * saveNewSubdistrictVersionPartContri(
						 * partContributedList.get(k).getSubdistrictCode(),
						 * districtCode, distritVersion, session);
						 */
						// saveNewVillageVersionPartContri(vFullList.get(k).getVillageCode(),
						// districtCode, distritVersion, session);
						// }
						id++;
					}
					/*
					 * for (int i=0; i<partContributedList.size();i++){
					 * saveNewSubdistrictVersionPartContri
					 * (partContributedList.get(i).getSubdistrictCode(),
					 * sdCode,sdVersionCode, session);
					 * saveNewSubdistrictVersionPartContri
					 * (partContributedList.get(i).getSubdistrictCode(),
					 * session);
					 * 
					 * }
					 */
					DistrictPK sdpk = new DistrictPK(districtCode, distritVersion,minorVersion);
					districtDAO.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																			// detail
																			// in
																			// sub
																			// district
																			// table
					isCommited = true;
				}

				else if (fullContributedListDistrit.size() > 0 && partContributedListDistrit.size() > 0) {
					// Full Contributed
					mapCd = this.publishMapLandRegion(sdForm, distritVersion, distritVersion, session);
					this.publishDistrict(sdForm, districtCode, distritVersion, mapCd, session);
					this.saveHeadquarters(sdForm, headquarterCode, sdCode, sdVersionCode, session);
					// Land Region Replaced By

					this.saveReplacedBy(rByid, lrReplacedBy, districtCode, distritVersion, session);
					for (int k = 0; k < fullContributedListDistrit.size(); k++) {
						// Land Region Replaces
						this.saveReplaces(id, lrReplaces, fullContributedListDistrit.get(k).getDistrictCode(), fullContributedListDistrit.get(k).getDistrictVersion(), session);

						DistrictPK sdpk = new DistrictPK(fullContributedListDistrit.get(k).getDistrictCode(), fullContributedListDistrit.get(k).getDistrictVersion(),fullContributedListDistrit.get(k).getMinorVersion());

						districtDAO.updateIsActive(false, sdpk, session);

						// Subdistricit impacting code
						this.saveNewSubdistritVersion(districtCode, fullContributedListDistrit.get(k).getDistrictCode(), fullContributedListDistrit.get(k).getDistrictVersion(), session);
						// Village impacting code

						/*
						 * this.saveNewVillageVersion(fullContributedList.get(k).
						 * getSubdistrictCode(), sdCode, sdVersionCode,
						 * session);
						 */
						id++;
					}
					// -------------------------------------------------------
					// Part Contributed
					for (int k = 0; k < partContributedListDistrit.size(); k++) {
						// Land Region Replaces
						this.saveReplaces(id, lrReplaces, partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(), session);

						DistrictPK sdpk = new DistrictPK(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion());
						// code to get new sd version
						this.saveNewDistrictVersion(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(),partContributedListDistrit.get(k).getMinorVersion(), lrReplacedBy, session);
						// -------------------

						// Subdistrict impacting code
						this.saveNewSubdistritVersion(districtCode, partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictVersion(), session);

						// Village impacting code

						/*
						 * this.saveNewVillageVersion(partContributedList.get(k).
						 * getSubdistrictCode(),
						 * partContributedListDistrit.get(k).getDistrictCode(),
						 * partContributedListDistrit
						 * .get(k).getDistrictVersion()+1, session);
						 */// --------------------

						districtDAO.updateIsActive(false, sdpk, session);
						saveNewSubdistrictVersionPartContri(partContributedListDistrit.get(k).getDistrictCode(), partContributedListDistrit.get(k).getDistrictCode(), distritVersion,minorVersion, session);

						/*
						 * saveNewSubdistrictVersionPartContri(
						 * partContributedList.get(k).getSubdistrictCode(),
						 * districtCode, distritVersion, session);
						 */
						// saveNewVillageVersionPartContri(vFullList.get(k).getVillageCode(),
						// districtCode, distritVersion, session);
						// }
						id++;
					}
					/*
					 * for (int i=0; i<partContributedList.size();i++){
					 * saveNewSubdistrictVersionPartContri
					 * (partContributedList.get(i).getSubdistrictCode(),
					 * sdCode,sdVersionCode, session);
					 * saveNewSubdistrictVersionPartContri
					 * (partContributedList.get(i).getSubdistrictCode(),
					 * session);
					 * 
					 * }
					 */

					DistrictPK sdpk = new DistrictPK(districtCode, distritVersion,minorVersion);
					districtDAO.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																			// detail
																			// in
																			// sub
																			// district
																			// table
					isCommited = true;
				}
				/*
				 * if (fullContributedList.size() > 0 &&
				 * partContributedList.size() == 0) { // Land Region Replaced By
				 * for (int k = 0; k < fullContributedList.size(); k++) { //
				 * Village impacting code
				 * this.saveNewVillageVersion(fullContributedList.get(k)
				 * .getSubdistrictCode(), fullContributedList.get(k)
				 * .getSubdistrictCode(), fullContributedList.get(k)
				 * .getSubdistrictVersion(), session); id++; } isCommited =
				 * true;
				 * 
				 * } else if (fullContributedList.size() == 0 &&
				 * partContributedList.size() > 0) {
				 * 
				 * 
				 * for (int k = 0; k < partContributedList.size(); k++) {
				 * 
				 * // Village impacting code
				 * this.saveNewVillageVersion(partContributedList
				 * .get(k).getSubdistrictCode(),
				 * partContributedList.get(k).getSubdistrictCode(),
				 * partContributedList.get(k).getSubdistrictVersion()+1,
				 * session); // --------------------
				 * 
				 * id++; } for (int i=0; i<vFullList.size();i++){
				 * saveNewVillageVersionFullContri
				 * (vFullList.get(i).getVillageCode(),
				 * fullContributedList.get(i) .getSubdistrictCode(),
				 * sdVersionCode, session); } for (int i=0;
				 * i<vPartList.size();i++){
				 * saveNewVillageVersionFullContri(vPartList
				 * .get(i).getVillageCode(),
				 * partContributedList.get(i).getSubdistrictCode(),
				 * sdVersionCode, session);
				 * saveNewVillageVersionPartContri(vPartList
				 * .get(i).getVillageCode(), session);
				 * 
				 * } } isCommited = true;
				 * 
				 * } else if (fullContributedList.size() > 0 &&
				 * partContributedList.size() > 0) {
				 * 
				 * for (int k = 0; k < fullContributedList.size(); k++) { //
				 * Village impacting code
				 * this.saveNewVillageVersion(fullContributedList.get(k)
				 * .getSubdistrictCode(), fullContributedList.get(k)
				 * .getSubdistrictCode(), fullContributedList.get(k)
				 * .getSubdistrictVersion(), session); id++; } //
				 * ------------------------------------------------------- //
				 * Part Contributed for (int k = 0; k <
				 * partContributedList.size(); k++) { // Village impacting code
				 * this.saveNewVillageVersion(partContributedList.get(k).
				 * getSubdistrictCode(),
				 * partContributedList.get(k).getSubdistrictCode(),
				 * partContributedList.get(k).getSubdistrictVersion()+1,
				 * session); //--------------------
				 * 
				 * id++; }
				 * 
				 * isCommited = true; }
				 */
			} catch (Exception e) {
				log.error("Exception-->" + e);
				log.debug("::::::::::RolledBack");
				isCommited = false;
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}

		return isCommited;
	}

	public boolean publishDistrict(DistrictForm sdForm, int sdCode, int sdVersion, int minorVersion, Session session) throws BaseAppException {
		State state = null;
		StatePK statepk = null;
		int distVersion = 1;
		state = new State();
		// stateVersion=this.getStateVersion(sdForm.getStateCode());
		try {
			statepk = new StatePK(Integer.parseInt(sdForm.getStateNameEnglish()), distVersion);
			state.setStatePK(statepk);
			// newDistrict.setState(state);
			// newDistrict.setStateCode(Integer.parseInt(sdForm.getStateNameEnglish()));
			// newDistrict.setStateVersion(distVersion);
			newDistrict.setAliasEnglish(sdForm.getDistrictAliasInEn());
			newDistrict.setAliasLocal(sdForm.getDistrictAliasInLcl());
			newDistrict.setCensus2001Code(sdForm.getCensus2011Code());
			newDistrict.setCensus2011Code("001");
			newDistrict.setCreatedby(1);
			newDistrict.setCreatedon(timestamp);
			newDistrict.setDistrictNameEnglish(sdForm.getDistrictNameInEn());
			newDistrict.setDistrictNameLocal(sdForm.getDistrictNameInLcl());
			newDistrict.setEffectiveDate(CurrentDateTime.getDate(effectiveDate));
			newDistrict.setIsactive(true);
			newDistrict.setLastupdated(timestamp);
			newDistrict.setLastupdatedby(12);
			//newDistrict.setMapCode(mapCd);
			newDistrict.setSscode(sdForm.getStateSpecificCode());
			SubdistrictPK spk = new SubdistrictPK(11, 1,1);
			Set<Subdistrict> sd = new HashSet<Subdistrict>();
			Subdistrict subdist = new Subdistrict();
			subdist.setTlc(10);
			subdist.setSubdistrictNameEnglish("Subdistrict");
			subdist.setSubdistrictNameLocal("Subdistrict_local");
			subdist.setTlc(1);
			subdist.setSubdistrictPK(spk);
			sd.add(subdist);
			newDistrict.setSubdistricts(sd);
			newDistrict.setLrReplacedby(null);
			newDistrict.setLrReplaces(null);
			DistrictPK DistrictPK = new DistrictPK(sdCode, sdVersion,minorVersion);
			newDistrict.setDistrictPK(DistrictPK);
			districtDAO.saveDistrict(newDistrict, session);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	/*@Override
	public boolean publishSubDistrict(DistrictForm sdForm, int sdCode, int sdVersion, int mapCd, Session session) throws BaseAppException {

		int distVersion = 1;
		// distVersion=this.getSubDistrictVersion(sdCode);
		try {
			DistrictPK dPK = new DistrictPK(districtCode, distVersion);
			District dist = new District();
			SubdistrictPK sdpk = new SubdistrictPK(sdCode, sdVersion);
			Subdistrict sdbean = new Subdistrict();
			dist.setDistrictPK(dPK);
			sdbean.setSubdistrictPK(sdpk);
			sdbean.setSubdistrictNameEnglish(sdForm.getDistrictNameInEn());
			sdbean.setSubdistrictNameLocal(sdForm.getDistrictNameInLcl());
			sdbean.setAliasEnglish(sdForm.getDistrictAliasInEn());
			sdbean.setAliasLocal(sdForm.getCensus2011Code());
			sdbean.setCensus2001Code(sdForm.getCensus2011Code());
			// sdbean.setMapLandregionCode(sdForm.getMapCode());
			sdbean.setSscode(sdForm.getStateSpecificCode());
			sdbean.setEffectiveDate(timestamp);
			sdbean.setLastupdated(timestamp);
			sdbean.setCreatedon(timestamp);
			sdbean.setCreatedby(1010101);
			sdbean.setLastupdatedby(1010101);
			sdbean.setIsactive(true);
			sdbean.setMapLandregionCode(mapCd);
			sdbean.setDistrict(dist);
			subdistrictDAO.publishSubDistrict(sdbean, session);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}*/


	public int publishMapLandRegion(DistrictForm sdForm, int sdCode, int sdVersion, Session session) throws BaseAppException {
		
		String cord;
		try {
			cord = sdForm.getLatitude() + ":" + sdForm.getLongitude();
			// MultipartFile filename=null;
			// filename=sdForm.getFilePathMapUpLoad();
			// writeMap(filename,request);
			// String
			// filePath=request.getRealPath("/")+filename.getOriginalFilename()
			// ;

			// log.debug("Filepath will display here-MAPlandregion--"+filePath);

			MapLandRegion mapbean = new MapLandRegion();
			mapbean.setLandregionCode(sdCode);
			mapbean.setLandregionVersion(sdVersion);
			mapbean.setLandregionType('T');
			mapbean.setCoordinates(cord);
			// mapbean.setImagePath(sdForm.getMapUrl());
			mapbean.setLastupdated(timestamp);
			mapbean.setEffectiveDate(timestamp);
			mapbean.setLastupdatedby(1010101);
			mapbean.setCreatedby(1010101);
			mapbean.setCreatedon(timestamp);
			mapbean.setWarningflag(false);
			mapCd = mapLandRegionDAO.configMap(mapbean, session);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return mapCd;
	}

	@Override
	public boolean saveReplacedBy(int id, int lrReplacedBy, int sdCode, int sdVersionCode, Session session) throws BaseAppException {
		try {
			LandregionReplacedby landregionReplacedbyBean = null;
			landregionReplacedbyBean = new LandregionReplacedby();
			landregionReplacedbyBean.setId(id);
			landregionReplacedbyBean.setLrReplacedby(lrReplacedBy);
			landregionReplacedbyBean.setEntityCode(sdCode);
			landregionReplacedbyBean.setEntityVersion(sdVersionCode);
			landregionReplacedbyBean.setEntityType('T');
			landRegionReplacedByDAO.save(landregionReplacedbyBean, session);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	@Override
	public boolean saveReplaces(int id, int lrReplaces, int sdCode, int sdVersionCode, Session session) throws BaseAppException {
		LandregionReplaces landregionReplacesBean = null;
		try {
			landregionReplacesBean = new LandregionReplaces();
			landregionReplacesBean.setId(id);
			landregionReplacesBean.setLrReplaces(lrReplaces);
			landregionReplacesBean.setEntityCode(sdCode);
			landregionReplacesBean.setEntityVersion(sdVersionCode);
			landregionReplacesBean.setEntityType('T');
			landRegionReplacesDAO.save(landregionReplacesBean, session);
			session.flush();
			session.refresh(landregionReplacesBean);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	@Override
	public boolean saveNewDistrictVersion(int subDistrictCode, int subDistrictCodeVersion,int minorVersion, int lrReplacedBy, Session session) throws BaseAppException {
		DistrictPK spdk = null;

		District sdbeanLocal = null;
		try {
			spdk = new DistrictPK(subDistrictCode, subDistrictCodeVersion,minorVersion);
			sdbeanLocal = new District();
			District sdbean = null;
			sdbeanLocal = districtDAO.getSubDistrictDetail(spdk, session);
			sdbean = new District();
			spdk = null;
			spdk = new DistrictPK(subDistrictCode, subDistrictCodeVersion + 1,minorVersion);
			// State dist = sdbeanLocal.getState();
			sdbean.setDistrictPK(spdk);
			sdbean.setDistrictNameEnglish(sdbeanLocal.getDistrictNameEnglish());
			sdbean.setDistrictNameLocal(sdbeanLocal.getDistrictNameLocal());
			sdbean.setAliasEnglish(sdbeanLocal.getAliasEnglish());
			sdbean.setAliasLocal(sdbeanLocal.getAliasLocal());
			sdbean.setCensus2001Code(sdbeanLocal.getCensus2001Code());
			sdbean.setCensus2011Code(sdbeanLocal.getCensus2011Code());
			sdbean.setCreatedby(sdbeanLocal.getCreatedby());
			sdbean.setCreatedon(sdbeanLocal.getCreatedon());
			// sdbean.setState(dist);
			sdbean.setEffectiveDate(sdbeanLocal.getEffectiveDate());
			sdbean.setFlagCode(2);
			sdbean.setIsactive(true);
			sdbean.setLastupdated(sdbeanLocal.getLastupdated());
			sdbean.setLrReplacedby(lrReplacedBy);
			sdbean.setLrReplaces(sdbeanLocal.getLrReplaces());
			//sdbean.setMapCode(sdbeanLocal.getMapCode());
			sdbean.setSscode(sdbeanLocal.getSscode());
			try {
				districtDAO.publishDistrict(sdbean, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	public boolean saveNewSubDistrictVersion(int subDistrictCode, int subDistrictCodeVersion, int lrReplacedBy, Session session) throws BaseAppException {
		SubdistrictPK sdpk = null;
		Subdistrict sdbeanLocal = null;
		try {
			sdpk = new SubdistrictPK(subDistrictCode, subDistrictCodeVersion,1);
			sdbeanLocal = new Subdistrict();
			sdbeanLocal = subdistrictDAO.getSubDistrictDetail(sdpk, session);
			Subdistrict sdbean = new Subdistrict();
			sdpk = null;
			sdpk = new SubdistrictPK(subDistrictCode, subDistrictCodeVersion + 1,1);
			District dist = sdbeanLocal.getDistrict();
			sdbean.setSubdistrictPK(sdpk);
			sdbean.setSubdistrictNameEnglish(sdbeanLocal.getSubdistrictNameEnglish());
			sdbean.setSubdistrictNameLocal(sdbeanLocal.getSubdistrictNameLocal());
			sdbean.setAliasEnglish(sdbeanLocal.getAliasEnglish());
			sdbean.setAliasLocal(sdbeanLocal.getAliasLocal());
			sdbean.setCensus2001Code(sdbeanLocal.getCensus2001Code());
			sdbean.setCensus2011Code(sdbeanLocal.getCensus2011Code());
			sdbean.setCreatedby(sdbeanLocal.getCreatedby());
			sdbean.setCreatedon(sdbeanLocal.getCreatedon());
			sdbean.setDistrict(dist);
			sdbean.setEffectiveDate(sdbeanLocal.getEffectiveDate());
			sdbean.setFlagCode(2);
			sdbean.setIsactive(true);
			sdbean.setLastupdated(sdbeanLocal.getLastupdated());
			sdbean.setLrReplacedby(lrReplacedBy);
			sdbean.setLrReplaces(sdbeanLocal.getLrReplaces());
			//sdbean.setMapLandregionCode(sdbeanLocal.getMapLandregionCode());
			sdbean.setSscode(sdbeanLocal.getSscode());
			try {
				subdistrictDAO.publishSubDistrict(sdbean, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	// Subdistrict impacting code
	public boolean saveNewSubdistrit(int stateCodeNew, int districtCode, int distritVersion, Session session) throws BaseAppException {
		// deactivating the old Subdistrit
		// new Version code
		try {
			List<Subdistrict> listSubdistrict = new ArrayList<Subdistrict>();
			String villageInactivesql = "update subdistrict set isactive = '" + false + "' where district_code = " + districtCode + " and isactive='true'";
			try {
				subdistrictDAO.update(villageInactivesql, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
			listSubdistrict = subdistrictDAO.getSubDistrict(districtCode);
			for (int itrSD = 0; itrSD < listSubdistrict.size(); itrSD++) {
				District district = null;

				district = new District();
				DistrictPK districtPKObj = null;
				districtPKObj = new DistrictPK(stateCodeNew, 1,1);
				district.setDistrictPK(districtPKObj);
				SubdistrictPK sdpk = null;
				Subdistrict subDistrictbean = new Subdistrict();
				subDistrictbean.setDistrict(district);
				sdpk = new SubdistrictPK(listSubdistrict.get(itrSD).getTlc(), listSubdistrict.get(itrSD).getTlc() + 1,1);

				subDistrictbean.setSubdistrictPK(sdpk);
				subDistrictbean.setAliasEnglish(listSubdistrict.get(itrSD).getAliasEnglish());
				subDistrictbean.setAliasLocal(listSubdistrict.get(itrSD).getAliasLocal());
				subDistrictbean.setCensus2001Code(listSubdistrict.get(itrSD).getCensus2001Code());
				subDistrictbean.setCensus2011Code(listSubdistrict.get(itrSD).getCensus2011Code());
				subDistrictbean.setCreatedby(listSubdistrict.get(itrSD).getCreatedby());
				subDistrictbean.setCreatedon(listSubdistrict.get(itrSD).getCreatedon());

				subDistrictbean.setEffectiveDate(listSubdistrict.get(itrSD).getEffectiveDate());
				subDistrictbean.setFlagCode(2);
				subDistrictbean.setIsactive(true);
				subDistrictbean.setLastupdated(listSubdistrict.get(itrSD).getLastupdated());
				subDistrictbean.setLastupdatedby(listSubdistrict.get(itrSD).getLastupdatedby());
				subDistrictbean.setLrReplacedby(listSubdistrict.get(itrSD).getLrReplacedby());
				subDistrictbean.setLrReplaces(listSubdistrict.get(itrSD).getLrReplaces());
			//	subDistrictbean.setMapLandregionCode(listSubdistrict.get(itrSD).getMapLandregionCode());
				subDistrictbean.setSscode(listSubdistrict.get(itrSD).getSscode());
				subDistrictbean.setSubdistrictNameEnglish(listSubdistrict.get(itrSD).getSubdistrictNameEnglish());
				subDistrictbean.setSubdistrictNameLocal(listSubdistrict.get(itrSD).getSubdistrictNameLocal());
				try {
					subdistrictDAO.save(subDistrictbean, session);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
			listSubdistrict = subdistrictDAO.getSubDistrictListbyDistrict(districtCode);
			for (int itrSD1 = 0; itrSD1 < listSubdistrict.size(); itrSD1++) {

				this.saveNewVillageVersion(listSubdistrict.get(itrSD1).getTlc(), listSubdistrict.get(itrSD1).getTlc(), session);
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}

		return true;
	}

	// Subdistrict impacting code
	public boolean saveNewSubdist(int stateCodeNew, int districtCode, int distritVersion, Session session) throws BaseAppException {
		// deactivating the old Subdistrit
		// new Version code
		try {
			List<Subdistrict> listSubdistrict = new ArrayList<Subdistrict>();
			String villageInactivesql = "update subdistrict set isactive = '" + false + "' where district_code = " + districtCode + " and isactive='true'";
			try {
				subdistrictDAO.update(villageInactivesql, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
			listSubdistrict = subdistrictDAO.getSubDistrict(districtCode);
			for (int itrSD = 0; itrSD < listSubdistrict.size(); itrSD++) {
				District district = null;

				district = new District();
				DistrictPK districtPKObj = null;
				districtPKObj = new DistrictPK(stateCodeNew, 1,1);
				district.setDistrictPK(districtPKObj);
				SubdistrictPK sdpk = null;
				Subdistrict subDistrictbean = new Subdistrict();
				subDistrictbean.setDistrict(district);
				sdpk = new SubdistrictPK(listSubdistrict.get(itrSD).getTlc(), listSubdistrict.get(itrSD).getTlc() + 1,1);

				subDistrictbean.setSubdistrictPK(sdpk);
				subDistrictbean.setAliasEnglish(listSubdistrict.get(itrSD).getAliasEnglish());
				subDistrictbean.setAliasLocal(listSubdistrict.get(itrSD).getAliasLocal());
				subDistrictbean.setCensus2001Code(listSubdistrict.get(itrSD).getCensus2001Code());
				subDistrictbean.setCensus2011Code(listSubdistrict.get(itrSD).getCensus2011Code());
				subDistrictbean.setCreatedby(listSubdistrict.get(itrSD).getCreatedby());
				subDistrictbean.setCreatedon(listSubdistrict.get(itrSD).getCreatedon());

				subDistrictbean.setEffectiveDate(listSubdistrict.get(itrSD).getEffectiveDate());
				subDistrictbean.setFlagCode(2);
				subDistrictbean.setIsactive(true);
				subDistrictbean.setLastupdated(listSubdistrict.get(itrSD).getLastupdated());
				subDistrictbean.setLastupdatedby(listSubdistrict.get(itrSD).getLastupdatedby());
				subDistrictbean.setLrReplacedby(listSubdistrict.get(itrSD).getLrReplacedby());
				subDistrictbean.setLrReplaces(listSubdistrict.get(itrSD).getLrReplaces());
				//subDistrictbean.setMapLandregionCode(listSubdistrict.get(itrSD).getMapLandregionCode());
				subDistrictbean.setSscode(listSubdistrict.get(itrSD).getSscode());
				subDistrictbean.setSubdistrictNameEnglish(listSubdistrict.get(itrSD).getSubdistrictNameEnglish());
				subDistrictbean.setSubdistrictNameLocal(listSubdistrict.get(itrSD).getSubdistrictNameLocal());
				try {
					subdistrictDAO.save(subDistrictbean, session);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}

		/*
		 * listSubdistrict=subdistrictDAO.getSubDistrictListbyDistrict(districtCode
		 * ); for (int itrSD1=0; itrSD1<listSubdistrict.size(); itrSD1++){
		 * 
		 * this.saveNewVillageVersion(listSubdistrict.get(itrSD1).getSubdistrictCode
		 * (), listSubdistrict.get(itrSD1).getSubdistrictVersion(), session); }
		 */

		return true;
	}

	// Subdistrict impacting code
	public boolean saveNewSubdistritfordist(int stateCodeNew, int districtCode, int distritVersion, Session session) throws BaseAppException {
		// deactivating the old Subdistrit
		// new Version code
		try {
			List<Subdistrict> listSubdistrict = new ArrayList<Subdistrict>();
			String villageInactivesql = "update subdistrict set isactive = '" + false + "' where district_code = " + districtCode + " and isactive='true'";
			try {
				subdistrictDAO.update(villageInactivesql, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
			listSubdistrict = subdistrictDAO.getSubDistrict(districtCode);
			for (int itrSD = 0; itrSD < listSubdistrict.size(); itrSD++) {
				District district = null;

				district = new District();
				DistrictPK districtPKObj = null;
				districtPKObj = new DistrictPK(stateCodeNew, 1,1);
				district.setDistrictPK(districtPKObj);
				SubdistrictPK sdpk = null;
				Subdistrict subDistrictbean = new Subdistrict();
				subDistrictbean.setDistrict(district);
				sdpk = new SubdistrictPK(listSubdistrict.get(itrSD).getTlc(), listSubdistrict.get(itrSD).getTlc() + 1,1);

				subDistrictbean.setSubdistrictPK(sdpk);
				subDistrictbean.setAliasEnglish(listSubdistrict.get(itrSD).getAliasEnglish());
				subDistrictbean.setAliasLocal(listSubdistrict.get(itrSD).getAliasLocal());
				subDistrictbean.setCensus2001Code(listSubdistrict.get(itrSD).getCensus2001Code());
				subDistrictbean.setCensus2011Code(listSubdistrict.get(itrSD).getCensus2011Code());
				subDistrictbean.setCreatedby(listSubdistrict.get(itrSD).getCreatedby());
				subDistrictbean.setCreatedon(listSubdistrict.get(itrSD).getCreatedon());

				subDistrictbean.setEffectiveDate(listSubdistrict.get(itrSD).getEffectiveDate());
				subDistrictbean.setFlagCode(2);
				subDistrictbean.setIsactive(true);
				subDistrictbean.setLastupdated(listSubdistrict.get(itrSD).getLastupdated());
				subDistrictbean.setLastupdatedby(listSubdistrict.get(itrSD).getLastupdatedby());
				subDistrictbean.setLrReplacedby(listSubdistrict.get(itrSD).getLrReplacedby());
				subDistrictbean.setLrReplaces(listSubdistrict.get(itrSD).getLrReplaces());
				//subDistrictbean.setMapLandregionCode(listSubdistrict.get(itrSD).getMapLandregionCode());
				subDistrictbean.setSscode(listSubdistrict.get(itrSD).getSscode());
				subDistrictbean.setSubdistrictNameEnglish(listSubdistrict.get(itrSD).getSubdistrictNameEnglish());
				subDistrictbean.setSubdistrictNameLocal(listSubdistrict.get(itrSD).getSubdistrictNameLocal());
				try {
					subdistrictDAO.save(subDistrictbean, session);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}

		return true;
	}

	// Subdistrict impacting code
	public boolean saveNewSubdistritVersion(int stateCodeNew, int districtCode, int distritVersion, Session session) throws BaseAppException {
		// deactivating the old Subdistrit
		// new Version code
		try {

			List<Subdistrict> listSubdistrict = new ArrayList<Subdistrict>();
			String villageInactivesql = "update subdistrict set isactive = '" + false + "' where district_code = " + districtCode + " and isactive='true'";
			try {
				subdistrictDAO.update(villageInactivesql, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
			listSubdistrict = subdistrictDAO.getSubDistrictListbyDistrict(districtCode);
			for (int itrSD = 0; itrSD < listSubdistrict.size(); itrSD++) {
				District district = null;

				district = new District();
				DistrictPK districtPKObj = null;
				districtPKObj = new DistrictPK(stateCodeNew, 1,1);
				district.setDistrictPK(districtPKObj);
				SubdistrictPK sdpk = null;
				Subdistrict subDistrictbean = new Subdistrict();
				subDistrictbean.setDistrict(district);
				sdpk = new SubdistrictPK(listSubdistrict.get(itrSD).getTlc(), listSubdistrict.get(itrSD).getTlc() + 1,1);

				subDistrictbean.setSubdistrictPK(sdpk);
				subDistrictbean.setAliasEnglish(listSubdistrict.get(itrSD).getAliasEnglish());
				subDistrictbean.setAliasLocal(listSubdistrict.get(itrSD).getAliasLocal());
				subDistrictbean.setCensus2001Code(listSubdistrict.get(itrSD).getCensus2001Code());
				subDistrictbean.setCensus2011Code(listSubdistrict.get(itrSD).getCensus2011Code());
				subDistrictbean.setCreatedby(listSubdistrict.get(itrSD).getCreatedby());
				subDistrictbean.setCreatedon(listSubdistrict.get(itrSD).getCreatedon());

				subDistrictbean.setEffectiveDate(listSubdistrict.get(itrSD).getEffectiveDate());
				subDistrictbean.setFlagCode(2);
				subDistrictbean.setIsactive(true);
				subDistrictbean.setLastupdated(listSubdistrict.get(itrSD).getLastupdated());
				subDistrictbean.setLastupdatedby(listSubdistrict.get(itrSD).getLastupdatedby());
				subDistrictbean.setLrReplacedby(listSubdistrict.get(itrSD).getLrReplacedby());
				subDistrictbean.setLrReplaces(listSubdistrict.get(itrSD).getLrReplaces());
				//subDistrictbean.setMapLandregionCode(listSubdistrict.get(itrSD).getMapLandregionCode());
				subDistrictbean.setSscode(listSubdistrict.get(itrSD).getSscode());
				subDistrictbean.setSubdistrictNameEnglish(listSubdistrict.get(itrSD).getSubdistrictNameEnglish());
				subDistrictbean.setSubdistrictNameLocal(listSubdistrict.get(itrSD).getSubdistrictNameLocal());
				try {
					subdistrictDAO.save(subDistrictbean, session);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
			listSubdistrict = subdistrictDAO.getSubDistrictListbyDistrict(districtCode);
			for (int itrSD1 = 0; itrSD1 < listSubdistrict.size(); itrSD1++) {

				this.saveNewVillageVersion(listSubdistrict.get(itrSD1).getTlc(), listSubdistrict.get(itrSD1).getTlc(), session);
			}
		}

		catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	public boolean saveNewvillver(List<Village> villageList, Session session) throws BaseAppException {
		// deactivating the old village

		// new Version code
		String strVillageCode = "";

		String queryVillageCondition = "";
		try {
			for (int l = 0; l < villageList.size(); l++) {
				if (l == 0) {
					strVillageCode = String.valueOf(villageList.get(l).getVlc());
					queryVillageCondition = " (village_code = " + villageList.get(l).getVlc() + " and village_version=" + villageList.get(l).getVlc() + ")";
				} else {
					strVillageCode += "," + villageList.get(l).getVlc();
					queryVillageCondition += " or (village_code = " + villageList.get(l).getVlc() + " and village_version=" + villageList.get(l).getVlc() + ")";
				}
			}
			String villageInactivesql = "update village set isactive = '" + false + "' where village_code in ( " + strVillageCode + ") and isactive='true'";
			try {
				villageDAO.update(villageInactivesql, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
			String insertBulkVillage = "insert into village(village_code,village_version,village_name_english,village_name_local,subdistrict_code,subdistrict_version,alias_english,alias_local,"
					+ "census_2001_code,census_2011_code,sscode,lr_replaces,map_attachment_code,effective_date,lastupdated,lastupdatedby,createdon," + "createdby,village_status,isactive,lr_replacedby,flag_code)"
					+ "select  village_code,village_version+1,village_name_english,village_name_local,subdistrict_code,subdistrict_version,alias_english,alias_local,"
					+ "census_2001_code,census_2011_code,sscode,lr_replaces,map_attachment_code,effective_date,lastupdated,lastupdatedby,createdon," + "createdby,village_status,TRUE,lr_replacedby,2 from village where " + queryVillageCondition
					+ " and isactive=false ";

			try {
				villageDAO.insertBulkVillage(insertBulkVillage, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}

			/*
			 * listVillage=villageDAO.getVillageListbySubDistrictCode(
			 * subDistrictCode); for (int villItr=0; villItr<listVillage.size();
			 * villItr++){ VillagePK villagePK=null; SubdistrictPK sdpk=null;
			 * Subdistrict sdbean=null;
			 * 
			 * 
			 * sdpk = new SubdistrictPK(subDistrictCode, subDistrictVersion);
			 * sdbean = new Subdistrict(); villagePK=new
			 * VillagePK(listVillage.get(villItr).getVillageCode(),
			 * listVillage.get(villItr).getVillageVersion()+1); Village
			 * villBean=new Village();
			 * 
			 * sdbean.setSubdistrictPK(sdpk);
			 * villBean.setVillageNameEnglish(listVillage
			 * .get(villItr).getVillageNameEnglish());
			 * villBean.setVillageNameLocal
			 * (listVillage.get(villItr).getVillageNameLocal());
			 * villBean.setAliasEnglish
			 * (listVillage.get(villItr).getAliasEnglish());
			 * villBean.setAliasLocal(listVillage.get(villItr).getAliasLocal());
			 * villBean
			 * .setCensus2011Code(listVillage.get(villItr).getCensus2011Code());
			 * villBean
			 * .setCensus2001Code(listVillage.get(villItr).getCensus2001Code());
			 * villBean.setCreatedby(listVillage.get(villItr).getCreatedby());
			 * villBean.setCreatedon(listVillage.get(villItr).getCreatedon());
			 * villBean
			 * .setEffectiveDate(listVillage.get(villItr).getEffectiveDate());
			 * villBean.setIsactive(true);
			 * villBean.setLastupdated(listVillage.get
			 * (villItr).getLastupdated());
			 * villBean.setLastupdatedby(listVillage
			 * .get(villItr).getLastupdatedby());
			 * villBean.setLrReplacedby(listVillage
			 * .get(villItr).getLrReplacedby());
			 * villBean.setLrReplaces(listVillage.get(villItr).getLrReplaces());
			 * villBean.setMapCode(listVillage.get(villItr).getMapCode());
			 * villBean.setSscode(listVillage.get(villItr).getSscode());
			 * villBean.setSubdistrict(sdbean);
			 * villBean.setVillagePK(villagePK);
			 * villBean.setVillageStatus(listVillage
			 * .get(villItr).getVillageStatus());
			 * //villBean.setVillagePartsBySurveyno
			 * (listVillage.get(villItr).getVillagePartsBySurveyno());
			 * villBean.setFlagCode(2); try { villageDAO.save(villBean,
			 * session); } catch (Exception e) { log.error("Exception-->"+e); }
			 * }
			 */
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}

		return true;
	}

	public boolean saveNewvill(int subDistrictCode, int subDistrictVersion, Session session) throws BaseAppException {
		// deactivating the old village

		// new Version code
		try {
			String villageInactivesql = "update village set isactive = '" + false + "' where subdistrict_code = " + subDistrictCode + " and isactive='true'";
			try {
				villageDAO.update(villageInactivesql, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
			String insertBulkVillage = "insert into village(village_code,village_version,village_name_english,village_name_local,subdistrict_code,subdistrict_version,alias_english,alias_local,"
					+ "census_2001_code,census_2011_code,sscode,lr_replaces,map_attachment_code,effective_date,lastupdated,lastupdatedby,createdon," + "createdby,village_status,isactive,lr_replacedby,flag_code)"
					+ "select  village_code,village_version+1,village_name_english,village_name_local,subdistrict_code,subdistrict_version,alias_english,alias_local,"
					+ "census_2001_code,census_2011_code,sscode,lr_replaces,map_attachment_code,effective_date,lastupdated,lastupdatedby,createdon," + "createdby,village_status,TRUE,lr_replacedby,2 from village where subdistrict_code="
					+ subDistrictCode + "  and subdistrict_version=" + subDistrictVersion + " and isactive=false ";

			try {
				villageDAO.insertBulkVillage(insertBulkVillage, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}

			/*
			 * listVillage=villageDAO.getVillageListbySubDistrictCode(
			 * subDistrictCode); for (int villItr=0; villItr<listVillage.size();
			 * villItr++){ VillagePK villagePK=null; SubdistrictPK sdpk=null;
			 * Subdistrict sdbean=null;
			 * 
			 * 
			 * sdpk = new SubdistrictPK(subDistrictCode, subDistrictVersion);
			 * sdbean = new Subdistrict(); villagePK=new
			 * VillagePK(listVillage.get(villItr).getVillageCode(),
			 * listVillage.get(villItr).getVillageVersion()+1); Village
			 * villBean=new Village();
			 * 
			 * sdbean.setSubdistrictPK(sdpk);
			 * villBean.setVillageNameEnglish(listVillage
			 * .get(villItr).getVillageNameEnglish());
			 * villBean.setVillageNameLocal
			 * (listVillage.get(villItr).getVillageNameLocal());
			 * villBean.setAliasEnglish
			 * (listVillage.get(villItr).getAliasEnglish());
			 * villBean.setAliasLocal(listVillage.get(villItr).getAliasLocal());
			 * villBean
			 * .setCensus2011Code(listVillage.get(villItr).getCensus2011Code());
			 * villBean
			 * .setCensus2001Code(listVillage.get(villItr).getCensus2001Code());
			 * villBean.setCreatedby(listVillage.get(villItr).getCreatedby());
			 * villBean.setCreatedon(listVillage.get(villItr).getCreatedon());
			 * villBean
			 * .setEffectiveDate(listVillage.get(villItr).getEffectiveDate());
			 * villBean.setIsactive(true);
			 * villBean.setLastupdated(listVillage.get
			 * (villItr).getLastupdated());
			 * villBean.setLastupdatedby(listVillage
			 * .get(villItr).getLastupdatedby());
			 * villBean.setLrReplacedby(listVillage
			 * .get(villItr).getLrReplacedby());
			 * villBean.setLrReplaces(listVillage.get(villItr).getLrReplaces());
			 * villBean.setMapCode(listVillage.get(villItr).getMapCode());
			 * villBean.setSscode(listVillage.get(villItr).getSscode());
			 * villBean.setSubdistrict(sdbean);
			 * villBean.setVillagePK(villagePK);
			 * villBean.setVillageStatus(listVillage
			 * .get(villItr).getVillageStatus());
			 * //villBean.setVillagePartsBySurveyno
			 * (listVillage.get(villItr).getVillagePartsBySurveyno());
			 * villBean.setFlagCode(2); try { villageDAO.save(villBean,
			 * session); } catch (Exception e) { log.error("Exception-->"+e); }
			 * }
			 */
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	public boolean saveNewVillageVersion(int subDistrictCode, int subDistrictVersion, Session session) throws BaseAppException {
		// deactivating the old village

		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
		try {
			String villageInactivesql = "update village set isactive = '" + false + "' where subdistrict_code = " + subDistrictCode + " and isactive='true'";
			try {
				villageDAO.update(villageInactivesql, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
			/*
			 * String insertBulkVillage=
			 * "insert into village(village_code,village_version,village_name_english,village_name_local,subdistrict_code,subdistrict_version,alias_english,alias_local,"
			 * +
			 * "census_2001_code,census_2011_code,sscode,lr_replaces,map_landregion_code,effective_date,lastupdated,lastupdatedby,createdon,"
			 * +"createdby,village_status,isactive,lr_replacedby,flag_code)" +
			 * "select  village_code,village_version+1,village_name_english,village_name_local,subdistrict_code,subdistrict_version,alias_english,alias_local,"
			 * +
			 * "census_2001_code,census_2011_code,sscode,lr_replaces,map_landregion_code,effective_date,lastupdated,lastupdatedby,createdon,"
			 * +
			 * "createdby,village_status,TRUE,lr_replacedby,2 from village where (subdistrict_code="
			 * +subDistrictCode+"  and subdistrict_version="+subDistrictVersion+
			 * ") and isactive=false ";
			 * 
			 * try { villageDAO.insertBulkVillage(insertBulkVillage,session); }
			 * catch (Exception e) { log.error("Exception-->"+e); }
			 */

			listVillage = villageDAO.getVillageListbySubDistrictCode(subDistrictCode);
			for (int villItr = 0; villItr < listVillage.size(); villItr++) {
				VillagePK villagePK = null;
				SubdistrictPK sdpk = null;
				Subdistrict sdbean = null;

				sdpk = new SubdistrictPK(subDistrictCode, subDistrictVersion,1);
				sdbean = new Subdistrict();
				villagePK = new VillagePK(listVillage.get(villItr).getVlc(), listVillage.get(villItr).getVlc() + 1,1);
				Village villBean = new Village();

				sdbean.setSubdistrictPK(sdpk);
				villBean.setVillageNameEnglish(listVillage.get(villItr).getVillageNameEnglish());
				villBean.setVillageNameLocal(listVillage.get(villItr).getVillageNameLocal());
				villBean.setAliasEnglish(listVillage.get(villItr).getAliasEnglish());
				villBean.setAliasLocal(listVillage.get(villItr).getAliasLocal());
				villBean.setCensus2011Code(listVillage.get(villItr).getCensus2011Code());
				villBean.setCensus2001Code(listVillage.get(villItr).getCensus2001Code());
				villBean.setCreatedby(listVillage.get(villItr).getCreatedby());
				villBean.setCreatedon(listVillage.get(villItr).getCreatedon());
				villBean.setEffectiveDate(listVillage.get(villItr).getEffectiveDate());
				villBean.setIsactive(true);
				villBean.setLastupdated(listVillage.get(villItr).getLastupdated());
				villBean.setLastupdatedby(listVillage.get(villItr).getLastupdatedby());
				villBean.setLrReplacedby(listVillage.get(villItr).getLrReplacedby());
				villBean.setLrReplaces(listVillage.get(villItr).getLrReplaces());
				villBean.setMapCode(listVillage.get(villItr).getMapCode());
				villBean.setSscode(listVillage.get(villItr).getSscode());
				// villBean.setSubdistrict(sdbean);
				villBean.setVillagePK(villagePK);
				villBean.setVillageStatus(listVillage.get(villItr).getVillageStatus());
				// villBean.setVillagePartsBySurveyno(listVillage.get(villItr).getVillagePartsBySurveyno());
				villBean.setFlagCode(2);
				try {
					villageDAO.save(villBean, session);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	public boolean saveNewvillage(int subdistrictcodenew, int subdistrictversionnew, int subdistrictcodeold, int subdistrictversionold, int villagecode, int subDistrictVersion, Session session) throws BaseAppException {
		// deactivating the old village

		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
		try {
			String villageInactivesql = "update village set isactive = '" + false + "' where village_code = " + villagecode + " and isactive='true'";
			try {
				villageDAO.update(villageInactivesql, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
			listVillage = villageDAO.getVillageList(villagecode);
			for (int villItr = 0; villItr < listVillage.size(); villItr++) {
				VillagePK villagePK = null;
				SubdistrictPK sdpk = null;
				Subdistrict sdbean = null;

				sdpk = new SubdistrictPK(subdistrictcodenew, subdistrictversionnew,1);
				sdbean = new Subdistrict();
				villagePK = new VillagePK(listVillage.get(villItr).getVlc(), listVillage.get(villItr).getVlc() + 1,1);
				Village villBean = new Village();

				sdbean.setSubdistrictPK(sdpk);
				villBean.setVillageNameEnglish(listVillage.get(villItr).getVillageNameEnglish());
				villBean.setVillageNameLocal(listVillage.get(villItr).getVillageNameLocal());
				villBean.setAliasEnglish(listVillage.get(villItr).getAliasEnglish());
				villBean.setAliasLocal(listVillage.get(villItr).getAliasLocal());
				villBean.setCensus2011Code(listVillage.get(villItr).getCensus2011Code());
				villBean.setCensus2001Code(listVillage.get(villItr).getCensus2001Code());
				villBean.setCreatedby(listVillage.get(villItr).getCreatedby());
				villBean.setCreatedon(listVillage.get(villItr).getCreatedon());
				villBean.setEffectiveDate(listVillage.get(villItr).getEffectiveDate());
				villBean.setIsactive(true);
				villBean.setLastupdated(listVillage.get(villItr).getLastupdated());
				villBean.setLastupdatedby(listVillage.get(villItr).getLastupdatedby());
				villBean.setLrReplacedby(listVillage.get(villItr).getLrReplacedby());
				villBean.setLrReplaces(listVillage.get(villItr).getLrReplaces());
				villBean.setMapCode(listVillage.get(villItr).getMapCode());
				villBean.setSscode(listVillage.get(villItr).getSscode());
				// villBean.setSubdistrict(sdbean);
				villBean.setVillagePK(villagePK);
				villBean.setVillageStatus(listVillage.get(villItr).getVillageStatus());
				// villBean.setVillagePartsBySurveyno(listVillage.get(villItr).getVillagePartsBySurveyno());
				villBean.setFlagCode(2);
				try {
					villageDAO.save(villBean, session);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}

		return true;
	}

	@Override
	public boolean saveNewSubdistrictVersionPartContri(int villCode, int newSDCode, int newSDVersion,int minorVersion, Session session) throws BaseAppException {
		// new Version code
		List<Subdistrict> listVillage = null;
		listVillage = new ArrayList<Subdistrict>();
		try {
			listVillage = subdistrictDAO.getVillageDetailsModify(villCode);
			for (int villItr = 0; villItr < listVillage.size(); villItr++) {
				SubdistrictPK subdistrictPK = null;
				DistrictPK sdpk = null;
				District sdbean = null;
				sdpk = new DistrictPK(newSDCode, newSDVersion,minorVersion);
				sdbean = new District();
				subdistrictPK = new SubdistrictPK(listVillage.get(villItr).getTlc(), listVillage.get(villItr).getTlc(),1);
				sdbean.setDistrictPK(sdpk);
				try {
					Subdistrict subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
					subdistrict.setDistrict(sdbean);
					subdistrict.setFlagCode(2);
					subdistrict.setIsactive(true);
					session.update(subdistrict);
					if (session.contains(subdistrict)){
						session.evict(subdistrict);
					}	
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}

	/*@Override
	public boolean saveNewSubdistrictVersionPartContri(int villCode, Session session) throws BaseAppException {
		// new Version code
		try {
			List<Subdistrict> listVillage = null;
			listVillage = new ArrayList<Subdistrict>();
			listVillage = subdistrictDAO.getVillageDetailsModify(villCode);
			for (int villItr = 0; villItr < listVillage.size(); villItr++) {
				SubdistrictPK subdistrictPK = null;
				subdistrictPK = new SubdistrictPK(listVillage.get(villItr).getTlc(), listVillage.get(villItr).getTlc());
				try {
					Subdistrict subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
					subdistrict.setFlagCode(2);
					subdistrict.setIsactive(true);
					session.update(subdistrict);
					if (session.contains(subdistrict)){
						session.evict(subdistrict);
					}	
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}*/

	/*@Override
	public boolean saveNewVillageVersion(int subDistrictCode, int newSDCode, int newSDVersion, Session session) throws BaseAppException {
		// deactivating the old village

		// new Version code
		try {
			List<Village> listVillage = null;
			listVillage = new ArrayList<Village>();
			String villageInactivesql = "update village set isactive = '" + false + "' where subdistrict_code = " + subDistrictCode + " and isactive='true'";
			try {
				villageDAO.update(villageInactivesql, session);
			} catch (Exception e) {
				log.error("Exception-->" + e);
			}
			listVillage = villageDAO.getVillageListbySubDistrictCode(subDistrictCode);
			for (int villItr = 0; villItr < listVillage.size(); villItr++) {
				VillagePK villagePK = null;
				SubdistrictPK sdpk = null;
				Subdistrict sdbean = null;

				sdpk = new SubdistrictPK(newSDCode, newSDVersion);
				sdbean = new Subdistrict();
				villagePK = new VillagePK(listVillage.get(villItr).getVlc(), listVillage.get(villItr).getVlc() + 1);
				Village villBean = new Village();

				sdbean.setSubdistrictPK(sdpk);
				villBean.setVillageNameEnglish(listVillage.get(villItr).getVillageNameEnglish());
				villBean.setVillageNameLocal(listVillage.get(villItr).getVillageNameLocal());
				villBean.setAliasEnglish(listVillage.get(villItr).getAliasEnglish());
				villBean.setAliasLocal(listVillage.get(villItr).getAliasLocal());
				villBean.setCensus2011Code(listVillage.get(villItr).getCensus2011Code());
				villBean.setCensus2001Code(listVillage.get(villItr).getCensus2001Code());
				villBean.setCreatedby(listVillage.get(villItr).getCreatedby());
				villBean.setCreatedon(listVillage.get(villItr).getCreatedon());
				villBean.setEffectiveDate(listVillage.get(villItr).getEffectiveDate());
				villBean.setIsactive(true);
				villBean.setLastupdated(listVillage.get(villItr).getLastupdated());
				villBean.setLastupdatedby(listVillage.get(villItr).getLastupdatedby());
				villBean.setLrReplacedby(listVillage.get(villItr).getLrReplacedby());
				villBean.setLrReplaces(listVillage.get(villItr).getLrReplaces());
				villBean.setMapCode(listVillage.get(villItr).getMapCode());
				villBean.setSscode(listVillage.get(villItr).getSscode());
				// villBean.setSubdistrict(sdbean);
				villBean.setVillagePK(villagePK);
				villBean.setVillageStatus(listVillage.get(villItr).getVillageStatus());
				// villBean.setVillagePartsBySurveyno(listVillage.get(villItr).getVillagePartsBySurveyno());
				villBean.setFlagCode(2);
				try {
					villageDAO.save(villBean, session);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}

		return true;
	}
*/
	/*@Override
	public boolean saveNewVillageVersionPartContri(int villCode, int newSDCode, int newSDVersion, Session session) throws BaseAppException {
		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
		try {
			listVillage = villageDAO.getVillageDetailsModify(villCode, session);
			for (int villItr = 0; villItr < listVillage.size(); villItr++) {
				VillagePK villagePK = null;
				SubdistrictPK sdpk = null;
				Subdistrict sdbean = null;

				sdpk = new SubdistrictPK(newSDCode, newSDVersion);
				sdbean = new Subdistrict();
				villagePK = new VillagePK(listVillage.get(villItr).getVlc(), listVillage.get(villItr).getVlc());
				sdbean.setSubdistrictPK(sdpk);

				try {
					Village village = (Village) session.load(Village.class, villagePK);
					// village.setSubdistrict(sdbean);
					village.setFlagCode(2);
					village.setIsactive(true);
					session.update(village);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return true;
	}*/

	public void saveHeadquarters(SubDistrictForm sdForm, int maxHCode, int regionCode, int regionVersion, Session session) throws BaseAppException {

		Headquarters headquarters = new Headquarters();
		try {
			headquarters.setHeadquarterCode(maxHCode);
			headquarters.setHeadquarterVersion(1);
			headquarters.setHeadquarterNameEnglish(sdForm.getHeadquarterName());
			headquarters.setHeadquarterLocalName(sdForm.getHeadquarterNameLocal());
			headquarters.setIsactive(true);
			// ;headquarters.setRegionCode(regionCode);
			// headquarters.setRegionVersion(regionVersion);
			headquarters.setRegionType('T');

			headquartersDAO.save(headquarters, session);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
	}

	public boolean save(SubDistrictForm sdForm) throws BaseAppException {
		/*
		 * Subdistricts subdistricts = new Subdistricts();
		 * in.nic.pes.lgd.ws.Subdistrict subdistrictDraft = new
		 * in.nic.pes.lgd.ws.Subdistrict(); LandDetails landDetails = new
		 * LandDetails(); landDetails.setMyCode(sdForm.getSubdistrictCode());
		 * landDetails.setMyVersionNo(sdForm.getSubDistrictVersion());
		 * landDetails.setNameEnglish(sdForm.getSubdistrictNameEnglish());
		 * landDetails.setNameLocal(sdForm.getSubdistrictNameLocal());
		 * landDetails.setAliasEnglish(sdForm.getAliasEnglish());
		 * landDetails.setAliasLocal(sdForm.getAliasLocal());
		 * landDetails.setParentCode(sdForm.getDistrictCode());
		 * landDetails.setParentVersionNo(sdForm.getDistrictVersion());
		 * landDetails.setCensus2011Code(sdForm.getCensus2011Code());
		 * subdistrictDraft.setSubdistrictDetails(landDetails);
		 * subdistricts.getSubdistrict().add(subdistrictDraft); //Save to XML
		 * XmlStorage xmlStorage = new XmlStorage();
		 * xmlStorage.objectToXml(subdistricts, "c:/Ram.xml"); log.debug
		 * ("..........################...........Data Saved into Subdistrict XML"
		 * );
		 */
		return true;
	}

	public int getStateVersion(int districtCode) throws BaseAppException {
		int distVers = 0;
		Session session=null;
		try {
			session=sessionFactory.openSession();
			distVers = Integer.parseInt(session.createQuery("select stateVersion from State where isactive=true and stateCode=" + districtCode).list().get(0).toString());
		} catch (Exception e) {

			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distVers;

	}

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCode(int stateCode) throws BaseAppException {
		List<District> district = null;
		Session session=null;
		try {
			session=sessionFactory.openSession();
			district = session.createQuery("from District d where d.isactive='true' and d.state.statePK.stateCode=" + stateCode + "order by d.districtNameEnglish").list();
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return district;
	}

	@SuppressWarnings("unchecked")
	public int getSubDistrictVersion(int districtCode) throws BaseAppException {
		int distVers = 0;
		Session session=null;
		try {
			session=sessionFactory.openSession();
			List<District> dist = session.createQuery("from District d where isactive=true and districtCode=" + districtCode).list();
			distVers = dist.get(0).getDistrictPK().getDistrictVersion();
		} catch (Exception e) {

			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distVers;
	}

	/*@Override
	public boolean saveNewVillageVersionPartContri(int villCode, Session session) throws BaseAppException {
		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
		try {
			listVillage = villageDAO.getVillageDetailsModify(villCode, session);
			for (int villItr = 0; villItr < listVillage.size(); villItr++) {
				VillagePK villagePK = null;
				villagePK = new VillagePK(listVillage.get(villItr).getVlc(), listVillage.get(villItr).getVlc());

				try {
					Village village = (Village) session.load(Village.class, villagePK);
					village.setFlagCode(2);
					village.setIsactive(true);
					session.update(village);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {

			log.error("Exception-->" + e);
		}
		return true;
	}
*/
	/*@Override
	public boolean saveNewVillageVersionFullContri(int villCode, int newSDCode, int newSDVersion, Session session) throws BaseAppException {
		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
		try {
			listVillage = villageDAO.getVillageDetailsModify(villCode, session);
			for (int villItr = 0; villItr < listVillage.size(); villItr++) {
				VillagePK villagePK = null;
				SubdistrictPK sdpk = null;
				Subdistrict sdbean = null;

				sdpk = new SubdistrictPK(newSDCode, newSDVersion);
				sdbean = new Subdistrict();
				villagePK = new VillagePK(listVillage.get(villItr).getVlc(), listVillage.get(villItr).getVlc() + 1);
				sdbean.setSubdistrictPK(sdpk);

				try {
					Village village = (Village) session.load(Village.class, villagePK);
					// village.setSubdistrict(sdbean);
					village.setFlagCode(2);
					village.setIsactive(true);
					session.update(village);
				} catch (Exception e) {
					log.error("Exception-->" + e);
				}
			}
		} catch (Exception e) {

			log.error("Exception-->" + e);
		}
		return true;
	}*/

	public List<SubdistrictDataForm> getSubdistrictDetailsModify(int subdistrictCode) throws BaseAppException {
		SubdistrictDataForm subdistrictDataForm = new SubdistrictDataForm();
		List<SubdistrictDataForm> listSubdistrictDetails = new ArrayList<SubdistrictDataForm>();
		Session session = null;
		session = sessionFactory.openSession();
		try {
			try {
				List<Subdistrict> listSubdistrictDetail = subdistrictDAO.getSubdistrictDetailsModify(subdistrictCode, session);
				int orderCode = 192;
				List<GovernmentOrder> govtOrderValue = subdistrictDAO.getGovermentOrderDetail(orderCode);
				Iterator<GovernmentOrder> itrGovt = govtOrderValue.iterator();
				while (itrGovt.hasNext()) {
					GovernmentOrder governmentOrder = (GovernmentOrder) itrGovt.next();

					governmentOrder.getOrderNo();
					governmentOrder.getOrderDate();
					governmentOrder.getEffectiveDate();
					governmentOrder.getGazPubDate();
					governmentOrder.getOrderPath();

					subdistrictDataForm.setOrderNo(governmentOrder.getOrderNo());
					subdistrictDataForm.setOrderDate(governmentOrder.getOrderDate());
					subdistrictDataForm.setEffectiveDate(governmentOrder.getEffectiveDate());
					subdistrictDataForm.setGazPubDate(governmentOrder.getGazPubDate());
					subdistrictDataForm.setOrderPath(governmentOrder.getOrderPath());
					

				}

				int mapCode = 0;

				Iterator<Subdistrict> itr = listSubdistrictDetail.iterator();
				while (itr.hasNext()) {
					Subdistrict element = (Subdistrict) itr.next();
					String cordinate = "";
					/*try {

						if (element.getMapLandregionCode() == null) {
							mapCode = 0;
						} else {
							mapCode = element.getMapLandregionCode();
						}
						if (mapCode == 0) {
							log.debug("mapCode is ::::::" + mapCode);
						} else {
							List<MapLandRegion> listCordinate = subdistrictDAO.getMapDetailsModify(mapCode, session);

							Iterator<MapLandRegion> itr1 = listCordinate.iterator();
							while (itr1.hasNext()) {
								MapLandRegion mapLandRegion = (MapLandRegion) itr1.next();
								cordinate = mapLandRegion.getCoordinates();
								int mapCode1 = mapLandRegion.getLandregionCode();
								int version = mapLandRegion.getLandregionVersion();
								StringTokenizer st = new StringTokenizer(cordinate, ":");
								while (st.hasMoreTokens()) {
									String longitude = st.nextToken();
									String latitude = st.nextToken();
									subdistrictDataForm.setLongi(longitude);
									subdistrictDataForm.setLati(latitude);

								}
							}

						}

					} catch (Exception e) {
						log.error("Exception-->" + e);
					}*/

					subdistrictDataForm.setAliasEnglish(element.getAliasEnglish());
					subdistrictDataForm.setAliasEnglishch(element.getAliasEnglish());
					subdistrictDataForm.setAliasLocal(element.getAliasLocal());
					subdistrictDataForm.setAliasLocalch(element.getAliasLocal());
					subdistrictDataForm.setCensus2001Code(element.getCensus2001Code());
					subdistrictDataForm.setSscode(element.getSscode());
					subdistrictDataForm.setSubdistrictCode(element.getTlc());
					subdistrictDataForm.setSubdistrictVersion(element.getTlc());
					subdistrictDataForm.setSubdistrictNameEnglish(element.getSubdistrictNameEnglish());
					subdistrictDataForm.setSubdistrictNameLocal(element.getSubdistrictNameLocal());
					subdistrictDataForm.setSubdistrictNameEnglishch(element.getSubdistrictNameEnglish());
					subdistrictDataForm.setSubdistrictNameLocalch(element.getSubdistrictNameLocal());
					subdistrictDataForm.setCensus2011Code(element.getCensus2011Code());
					subdistrictDataForm.setCordinate(cordinate);
					subdistrictDataForm.setLrReplacedby(element.getLrReplacedby());// Check
																					// Vanisha
																					// 7-10-2011
																					// .getLrPartCode());
					subdistrictDataForm.setLrReplaces(element.getLrReplaces()); // Check
																				// details
																				// of
																				// LRPartcode
					//subdistrictDataForm.setMapLandregionCode(element.getMapLandregionCode());
					subdistrictDataForm.setDistrict_code(element.getDlc());
					subdistrictDataForm.setDistrict_version(element.getDlc());
					listSubdistrictDetails.add(subdistrictDataForm);
					SubDistrictForm vform = new SubDistrictForm();
					vform.setListSubdistrictDetails(listSubdistrictDetails);
				}
			} catch (Exception e) {
				log.error("Exception-->" + e);

			} finally {
				if (session != null && session.isOpen()) {
					session.clear();
					session.close();
				}
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return listSubdistrictDetails;
	}

	/*public District viewDistrictDetail() throws BaseAppException {

		
		 * StateDAO stateDAO= new StateDAO(); Map<String, Object>
		 * stateList=stateDAO.getStateList(); State state=stateList.get(key);
		 * DistrictDAO districtDAO= new DistrictDAO();
		 * districtDAO.getDistrictList(state);
		 

		return new District(0, 0);

	}*/

	public List<District> getDistrictList(int slc) throws BaseAppException {
		List<District> district = null;
		district = districtDAO.getDistrictListbyStateCode(slc);
		return district;
	}

	public List<District> getDistrictListGlobal(int stateCode) throws BaseAppException {

		List<District> district = null;
		try {
			district = districtDAO.getDistrictListbyStateCodeGlobal(stateCode);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return district;

	}

	/*public List<CheckAuthorization> getDistrictListAuthentication(int stateCode) throws BaseAppException {
			return commonService.checkAuthorization('D', "S", stateCode, null);
	}
*/
	public List<District> getDistrictViewList(String query) throws BaseAppException {
		List<District> district = null;
		try {
			district = districtDAO.getDistrictViewList(query);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return district;

	}

	/*@Override
	public void createDistrict() throws BaseAppException {

	}*/

	public List<District> getDistrictListByDistCode(int districtCode) throws BaseAppException {
		List<District> district = null;
		try {
			district = districtDAO.getDistrictListbyDistCode(districtCode);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return district;
	}

	/*
	 * public List<District> getTargetDistrictList(int districtCode) { return
	 * districtDAO.getTargetDistrictList(districtCode);
	 * 
	 * }
	 */
	public List<District> getTargetDistrictList(int districtCode, int statecode) throws BaseAppException {
		List<District> district = null;
		try {
			district = districtDAO.getTargetDistrictList(districtCode, statecode);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return district;

	}

	/*public List<District> getTargetDistrictListFinal(int districtCode, int statecode) throws BaseAppException {
		List<District> district = null;
		try {
			district = districtDAO.getTargetDistrictListFinal(districtCode, statecode);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return district;

	}*/

	/*@Override
	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws BaseAppException {

		List<GovernmentOrder> govOrderDetail = districtDAO.getGovermentOrderDetail(orderCode);
		log.debug("mapDetail---" + govOrderDetail.size());
		return govOrderDetail;
	}*/

	/*@Override
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode) throws BaseAppException {

		List<MapLandRegion> mapDetail = districtDAO.getMapDetailsModify(mapLandregionCode);
		log.debug("mapDetail---" + mapDetail.size());
		return mapDetail;
	}*/

	@Override
	public List<DistrictForm> getMergeDistrictWithSubDistrictWithSubdistrict(String list) {
		String listformat = list;
		String listformat1[] = listformat.split(",");
		String listformat2[];
		String temp, temp1;
		String data;

		List<District> dlist = new ArrayList<District>();
		List<Subdistrict> listSubdistrict = new ArrayList<Subdistrict>();
		List<DistrictForm> slist = new ArrayList<DistrictForm>();
		DistrictForm obj;
		int l;
		try {
			for (int i = 0; i < listformat1.length; i++) {
				l = listformat1[i].indexOf("#");
				temp = listformat1[i].substring(0, l);
				dlist = this.getDistrictListByDistCode(Integer.parseInt(temp));
				temp1 = listformat1[i].substring(l + 1, listformat1[i].length());
				listformat2 = temp1.split(":");
				for (int j = 0; j < listformat2.length; j++) {
					data = listformat2[j];
					obj = new DistrictForm();
					listSubdistrict = subdistrictDAO.getSubDistrictbySubdistCode(Integer.parseInt(data));
					obj.setDistrictCode(dlist.get(0).getDistrictCode());
					obj.setDistrictVersion(dlist.get(0).getDistrictVersion());
					obj.setDistrictNameEnglish(dlist.get(0).getDistrictNameEnglish());
					obj.setDistrictNameInLcl(dlist.get(0).getDistrictNameLocal());
					obj.setSubdistrictCode(listSubdistrict.get(0).getSubdistrictCode());
					obj.setSubDistrictVersion(listSubdistrict.get(0).getSubdistrictVersion());
					obj.setSubdistrictNameEnglish(listSubdistrict.get(0).getSubdistrictNameEnglish());
					obj.setSubdistrictNameLocal(listSubdistrict.get(0).getSubdistrictNameLocal());
					// slist.set(j, obj);
					slist.add(obj);

				}
			}
			return slist;
			/*
			 * sourcedistrictinfo=this.getDistrictDetailsModify(); listdetail=
			 */
		} catch (Exception e) {

			log.error("Exception-->" + e);
		}
		return null;

	}

	@Override
	public List<DistrictDataForm> getDistrictDetailsModify(int districtCode) throws BaseAppException {

		List<DistrictDataForm> listDistrictDetails = new ArrayList<DistrictDataForm>();
		DistrictDataForm districtDataForm = new DistrictDataForm();
		List<District> listDistrictDetail = districtDAO.getDistrictDetailsModify(districtCode);
		int districtversion = districtDAO.getMaxDistrictVersionbyCode(districtCode);
		int minorVersion = districtDAO.getMaxDistrictMinorVersion(districtCode,districtversion);
		List<GetGovernmentOrderDetail> govtOrderValue = districtDAO.GovOrderDetail('D', districtCode, districtversion,minorVersion);
		List<Headquarters> listHeadquarter = districtDAO.getHeadquarterModify('D', districtCode, districtversion);

		if (!listHeadquarter.isEmpty()) {
			Headquarters element = listHeadquarter.get(0);
			districtDataForm.setHeadquarterCode(element.getHeadquarterCode());
			districtDataForm.setHeadquarterVersion(element.getHeadquarterVersion());
			districtDataForm.setHeadquarterName(element.getHeadquarterNameEnglish());
			districtDataForm.setHeadquarterNameLocal(element.getHeadquarterLocalName());
			districtDataForm.setCordinate(listDistrictDetail.get(0).getCoordinates());
			districtDataForm.setWarningflag(listDistrictDetail.get(0).isWarningflag());

		}

		if (!govtOrderValue.isEmpty()) {
			GetGovernmentOrderDetail governmentOrder = govtOrderValue.get(0);
			districtDataForm.setOrderCodecr(governmentOrder.getOrderCode());
			if (governmentOrder.getOrderNo() != null) {
				districtDataForm.setOrderNocr(governmentOrder.getOrderNo().trim());
			}
			districtDataForm.setOrderDatecr(governmentOrder.getOrderDate());
			districtDataForm.setGazPubDatecr(governmentOrder.getGazPubDate());
			districtDataForm.setOrderPathcr(governmentOrder.getOrderPath());
		}

		District element = listDistrictDetail.get(0);

		if (element.getAliasEnglish() != null){
			districtDataForm.setAliasEnglish(element.getAliasEnglish().trim());
		}	
		if (element.getAliasLocal() != null){
			districtDataForm.setAliasLocal(element.getAliasLocal().trim());
		}	
		if (element.getAliasLocal() != null){
			districtDataForm.setAliasLocalch(element.getAliasLocal().trim());
		}	
		if (element.getSscode() != null){
			districtDataForm.setSscode(element.getSscode().trim());
		}	
		if (element.getDistrictNameEnglish() != null){
			districtDataForm.setDistrictNameEnglish(element.getDistrictNameEnglish().trim());
		}	
		if (element.getDistrictNameLocal() != null){
			districtDataForm.setDistrictNameLocal(element.getDistrictNameLocal().trim());
		}	
		if (element.getDistrictNameEnglish() != null){
			districtDataForm.setDistrictNameEnglishch(element.getDistrictNameEnglish().trim());
		}	
		if (element.getDistrictNameLocal() != null){
			districtDataForm.setDistrictNameLocalch(element.getDistrictNameLocal().trim());
		}	
		districtDataForm.setWarningflag(element.isWarningflag());
		districtDataForm.setCensus2001Code(element.getCensus2001Code());
		districtDataForm.setDistrictCode(element.getDistrictCode());
		districtDataForm.setDistrictVersion(element.getDistrictVersion());
		districtDataForm.setMinorVersion(element.getMinorVersion());
		districtDataForm.setCensus2011Code(element.getCensus2011Code());
		String Cordinate = null;
		if (element.getCoordinates() != null) {
			if (!element.getCoordinates().trim().equals("")){
				Cordinate = element.getCoordinates();
			}	
		}
		districtDataForm.setCordinate(Cordinate);
		/* districtDataForm.setCordinate(cordinate); */
		districtDataForm.setLrReplacedby(element.getLrReplacedby());
		districtDataForm.setLrReplaces(element.getLrReplaces());
		//districtDataForm.setMapCode(element.getMapCode());
		districtDataForm.setStateCode(element.getSlc());
		districtDataForm.setStateVersion(element.getSlc());
		districtDataForm.setOrdereffectiveDatecr(element.getEffectiveDate());
		districtDataForm.setOrdereffectiveDate(element.getEffectiveDate());
		listDistrictDetails.add(districtDataForm);
		DistrictForm vform = new DistrictForm();
		vform.setListDistrictDetails(listDistrictDetails);

		return listDistrictDetails;
	}

	@Override
	public boolean modifyDistrictInfo(DistrictForm districtForm, HttpServletRequest request, HttpSession httpsession) throws BaseAppException {
		Session session = null;
		Transaction tx1 = null;
		session = sessionFactory.openSession();
		tx1 = session.beginTransaction();
		try {
			if (districtForm.isCorrection() == true) {
				District districtBean = new District();
				List<DistrictDataForm> listDistrict = new ArrayList<DistrictDataForm>();
				listDistrict = districtForm.getListDistrictDetails();
				Iterator<DistrictDataForm> itr = listDistrict.iterator();
				while (itr.hasNext()) {
					DistrictDataForm element = (DistrictDataForm) itr.next();
					try {
						DistrictPK districtPK = new DistrictPK();
						districtPK.setDistrictCode(element.getDistrictCode());
						districtPK.setDistrictVersion(element.getDistrictVersion());
						//String longitude = "";
						//String latitude = "";
						int mapCode = 0;
						//districtBean.setMapCode(element.getMapCode());
						districtBean.setIsactive(true);
						if (element.getMapCode() == null) {
							mapCode = 0;
						} else {
							mapCode = element.getMapCode();
						}

						/*if (element.getLatitude() == null) {
							latitude = "";
						} else {
							latitude = element.getLatitude();
						}
						if (element.getLongitude() == null) {
							latitude = "";
						} else {
							longitude = element.getLongitude();
						}*/
						
						String cord1 = null;
						if (districtForm.getLati().split(",").length >= 1) {
							String[] tempLati = districtForm.getLati().split(",");
							String[] tempLongi = districtForm.getLongi().split(",");
							cord1 = tempLati[0] + "," + tempLongi[0] + ":";
							if (tempLati.length > 1) {
								for (int i = 1; i < tempLati.length; i++) {
									cord1 += tempLati[i] + "," + tempLongi[i] + ":";
								}
							}
							cord1 = cord1.substring(0, cord1.length() - 1);
						}
						int map_landRegionCode = 0;
						if (mapCode == 0) {
							map_landRegionCode = districtDAO.getMaxDistrictCode("select COALESCE(max(map_attachment_code),1) from map_attachment");
							if (map_landRegionCode == 0) {
								map_landRegionCode = 1;
							} else {
								map_landRegionCode = map_landRegionCode + 1;
							}
							String cord = null;
							if (districtForm.getLati().split(",").length > 1) {
								String[] tempLati = districtForm.getLati().split(",");
								String[] tempLongi = districtForm.getLongi().split(",");
								cord = tempLati[0] + "," + tempLongi[0] + ":";
								if (tempLati.length > 1) {
									for (int i = 1; i < tempLati.length; i++) {
										cord += tempLati[i] + "," + tempLongi[i] + ":";
									}
								}
								cord = cord.substring(0, cord.length() - 1);
							}
							try {

								Timestamp time = CurrentDateTime.getCurrentTimestamp();
								MapLandRegion mapLandRegion = null;
								mapLandRegion = new MapLandRegion();
								mapLandRegion.setEffectiveDate(CurrentDateTime.getDate("2011-12-12"));
								mapLandRegion.setCreatedon(time);
								mapLandRegion.setCreatedby(100);
								mapLandRegion.setLandregionType('D');
								mapLandRegion.setLandregionVersion(element.getDistrictVersion());
								mapLandRegion.setCoordinates(cord);
								mapLandRegion.setMapLandregionCode(map_landRegionCode);
								mapLandRegion.setLandregionCode(element.getDistrictCode());
								mapLandRegion.setWarningflag(true);
								mapLandRegionDAO.saveMap(mapLandRegion, session);
								//districtBean.setMapCode(map_landRegionCode);
								districtDAO.modifyDistrictInfo(districtForm, districtPK, map_landRegionCode, session);
							} catch (Exception e) {
								log.error("Exception-->" + e);
							}
						} else {

							districtDAO.updateMapLanRegion(mapCode, cord1, element.getDistrictCode(), session);

							districtDAO.modifyDistrictInfo(districtForm, districtPK, mapCode, session);
						}
						govtOrderService.updateGovernmentOrder(element.getOrderNocr(), element.getOrderCodecr(), element.getOrderDatecr(), element.getOrdereffectiveDatecr(), element.getGazPubDatecr(), "update", element.getOrderPath(),
								districtForm.getFilePathcr(), request);

					} catch (Exception e) {
						log.error("Exception-->" + e);
						// tx.rollback();
						return false;
					}

				}

			} else if (districtForm.isCorrection() == false) {
				List<DistrictDataForm> listDistrict = new ArrayList<DistrictDataForm>();
				listDistrict = districtForm.getListDistrictDetails();
				Iterator<DistrictDataForm> itr = listDistrict.iterator();
				while (itr.hasNext()) {
					DistrictDataForm element = (DistrictDataForm) itr.next();
					int districtVersion = 1;
					districtVersion = districtDAO.getMaxDistrictVersionbyCode(element.getDistrictCode());
					if (districtVersion == 1) {
						districtVersion = districtVersion + 1;
					} else {
						districtVersion = districtVersion + 1;
					}
					try {
						String districtCode = Integer.toString(element.getDistrictCode());

						// Save district detail
						districtDAO.ChangeDistrict(element.getDistrictCode(), element.getDistrictNameEnglishch(), 444, element.getDistrictNameLocalch(), element.getAliasEnglishch(), element.getAliasLocalch(), session);
						// Government Order
						List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
						/*
						 * ================Getting the values from Application
						 * and Setting IN
						 * AddAttachmentBeanClass==================
						 */

						AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
						addAttachmentBean.setCurrentlyUploadFileList(districtForm.getAttachedFile());
						addAttachmentBean.setUploadLocation(districtForm.getUploadLocation());
						addAttachmentBean.setAllowedTotalNoOfAttachment(districtForm.getAllowedNoOfAttachment());
						addAttachmentBean.setAllowedTotalFileSize(districtForm.getAllowedTotalFileSize());
						addAttachmentBean.setAllowedIndividualFileSize(districtForm.getAllowedIndividualFileSize());
						addAttachmentBean.setFileTitle(request.getParameterValues("fileTitle"));
						addAttachmentBean.setDeletedFileList(request.getParameterValues("fileSize"));
						addAttachmentBean.setNoOFAlreadyAttachedFiles(0);// 7.Number
																			// Of
																			// Already
																			// Attached
																			// File.
						addAttachmentBean.setTotalSizeOFAlreadyAttachedFiles(0);// 8.Already
																				// attached
																				// file
																				// total
																				// size.
						addAttachmentBean.setMimeTypeList(allowedMimeTypeList);// 9.File
																				// Mime
																				// Type
																				// List.
						addAttachmentBean.setDeletedFileID(request.getParameterValues("deletedFileID2"));// 10.File
																											// Id
																											// array
																											// to
																											// be
																											// deleted
						addAttachmentBean.setDeletedFileName(request.getParameterValues("deletedFileName2"));// 11.File
																												// Name
																												// Array
																												// To
																												// Be
																												// Deleted.
						addAttachmentBean.setDeletedFileList(request.getParameterValues("deletedFileSizeList2"));// 12.Deleted
																													// File
																													// Array.
						addAttachmentBean.setNoOFMandatoryFile(1);// 13.Number
																	// of
																	// Mandatory
																	// file.

						addAttachmentBean.setMimeTypeList(allowedMimeTypeList);
						AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
						attachmentHandler.validateAttachment(addAttachmentBean);
						List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
						attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);

						GovernmentOrder govtOrder = saveDataInGovtOrder(districtForm, session);
						saveDataInAttachment(govtOrder, districtForm, metaInfoOfToBeAttachedFileList, session);
						shiftService.saveDataInGovtOrderEntityWise(govtOrder, districtCode, 'D', session);

					} catch (Exception e) {

						log.error("Exception-->" + e);
					}
				}
			}
			tx1.commit();
		} catch (Exception e) {

			log.error("Exception-->" + e);
			tx1.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session.clear();
			}
		}
		return true;

	}

	public List<Village> getVillageList(String districtCode) throws BaseAppException {
		String selectedCoveredLandRegionByULB = districtCode;
		List<Village> fullSubdistrictContributingList = null;
		fullSubdistrictContributingList = new ArrayList<Village>();
		List<Village> partSubdistrictContributingList = null;
		partSubdistrictContributingList = new ArrayList<Village>();

		List<Village> coveredAreaListFull = null;
		coveredAreaListFull = new ArrayList<Village>();

		Village village = null;
		int districtCodeNew = 0;
		String[] temp = selectedCoveredLandRegionByULB.split(",");
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));

				/*
				 * List<Subdistrict> lstTemp = null; lstTemp = new
				 * ArrayList<Subdistrict>();
				 * fullSubdistrictContributingList.add(lstTemp.get(0));
				 */

				fullSubdistrictContributingList = subdistrictDAO.getSubdistrictListbyVillageCode(districtCodeNew);

				for (int j = 0; j < fullSubdistrictContributingList.size(); j++) {
					village = new Village();

					village.setVlc(fullSubdistrictContributingList.get(j).getVlc());
					village.setVillageNameEnglish(fullSubdistrictContributingList.get(j).getVillageNameEnglish());
					village.setVillageNameLocal(fullSubdistrictContributingList.get(j).getVillageNameLocal());
					coveredAreaListFull.add(j, village);
				}

			}
			if (temp[i].contains("PART")) {
				districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
				/*
				 * List<Subdistrict> lstTemp = null; lstTemp = new
				 * ArrayList<Subdistrict>();
				 * partSubdistrictContributingList.add(lstTemp.get(0));
				 */
				partSubdistrictContributingList = subdistrictDAO.getSubdistrictListbyVillageCode(districtCodeNew);
				for (int j = 0; j < partSubdistrictContributingList.size(); j++) {
					village = new Village();

					village.setVlc(partSubdistrictContributingList.get(j).getVlc());
					village.setVillageCode(partSubdistrictContributingList.get(j).getVlc());
					village.setVillageNameEnglish(partSubdistrictContributingList.get(j).getVillageNameEnglish());
					village.setVillageNameLocal(partSubdistrictContributingList.get(j).getVillageNameLocal());
					coveredAreaListFull.add(j, village);
				}
			}

		}
		return coveredAreaListFull;

	}

	/*
	 * public List<District>getDistricts(String districtCode) {
	 * log.debug("State code is"+districtCode); String
	 * selectedCoveredLandRegionByULB = districtCode; List<District>
	 * fullSubdistrictContributingList = null; fullSubdistrictContributingList =
	 * new ArrayList<District>(); List<District> partSubdistrictContributingList
	 * = null; partSubdistrictContributingList = new ArrayList<District>();
	 * 
	 * List<District> coveredAreaListFull = null; coveredAreaListFull = new
	 * ArrayList<District>();
	 * 
	 * log.debug("sdsdsdsdd"); District subdistrict=null; int districtCodeNew=0;
	 * log.debug("selecteddistrict>>>>>>>>" + selectedCoveredLandRegionByULB);
	 * String[] temp = selectedCoveredLandRegionByULB.split(","); for (int i =
	 * 0; i < temp.length; i++) { log.debug(temp[i]); if
	 * (temp[i].contains("FULL")) { districtCodeNew =
	 * Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
	 * 
	 * List<Subdistrict> lstTemp = null; lstTemp = new ArrayList<Subdistrict>();
	 * fullSubdistrictContributingList.add(lstTemp.get(0));
	 * fullSubdistrictContributingList
	 * =stateDAO.getdistrictListbyDistrict(districtCodeNew);
	 * 
	 * for (int j = 0; j < fullSubdistrictContributingList.size(); j++) {
	 * subdistrict= new District(); List<District>
	 * fullubdistrictContributingList = null;
	 * subdistrict.setDistrictCode(fullubdistrictContributingList
	 * .get(j).getDistrictCode());
	 * subdistrict.setDistrictNameEnglish(fullSubdistrictContributingList
	 * .get(j).getDistrictNameEnglish());
	 * subdistrict.setDistrictNameLocal(fullSubdistrictContributingList
	 * .get(j).getDistrictNameLocal()); coveredAreaListFull.add(j,subdistrict);
	 * }
	 * 
	 * } if (temp[i].contains("PART")) { districtCodeNew=
	 * Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
	 * List<Subdistrict> lstTemp = null; lstTemp = new ArrayList<Subdistrict>();
	 * partSubdistrictContributingList.add(lstTemp.get(0));
	 * partSubdistrictContributingList
	 * =stateDAO.getdistrictListbyDistrict(districtCodeNew); for (int j = 0; j <
	 * partSubdistrictContributingList.size(); j++) {
	 * 
	 * subdistrict.setDistrictCode(partSubdistrictContributingList.get(j).
	 * getDistrictCode());
	 * subdistrict.setDistrictNameEnglish(partSubdistrictContributingList
	 * .get(j).getDistrictNameEnglish());
	 * subdistrict.setDistrictNameLocal(partSubdistrictContributingList
	 * .get(j).getDistrictNameLocal()); coveredAreaListFull.add(j,subdistrict);
	 * 
	 * 
	 * } }
	 * 
	 * } return coveredAreaListFull;
	 * 
	 * }
	 */

	// ///////////////////////
	
	/**
	 * Changes for Mark Pesa Region by Ripunj on 18-11-2014
	 */
	public List<Subdistrict> getSubdistrictListbyDistrict(String districtCode) throws BaseAppException {
		String selectedCoveredLandRegionByULB = districtCode;
		List<Subdistrict> fullSubdistrictContributingList = null;
		fullSubdistrictContributingList = new ArrayList<Subdistrict>();
		List<Subdistrict> partSubdistrictContributingList = null;
		partSubdistrictContributingList = new ArrayList<Subdistrict>();

		List<Subdistrict> coveredAreaListFull = null;
		coveredAreaListFull = new ArrayList<Subdistrict>();

		Subdistrict subdistrict = null;
		int districtCodeNew = 0;
		String[] temp = selectedCoveredLandRegionByULB.split(",");
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));

				/*
				 * List<Subdistrict> lstTemp = null; lstTemp = new
				 * ArrayList<Subdistrict>();
				 * fullSubdistrictContributingList.add(lstTemp.get(0));
				 */
				fullSubdistrictContributingList = subdistrictDAO.getSubDistrictListbyDistrict(districtCodeNew);

				for (int j = 0; j < fullSubdistrictContributingList.size(); j++) {
					subdistrict = new Subdistrict();

					subdistrict.setTlc(fullSubdistrictContributingList.get(j).getTlc());
					subdistrict.setSubdistrictCode(fullSubdistrictContributingList.get(j).getTlc());
					subdistrict.setCensus2001Code(fullSubdistrictContributingList.get(j).getTlc() + "FULL");
					subdistrict.setSubdistrictNameEnglish(fullSubdistrictContributingList.get(j).getSubdistrictNameEnglish() + " (" + "FULL" + ")");
					subdistrict.setSubdistrictNameLocal(fullSubdistrictContributingList.get(j).getSubdistrictNameLocal());
					//subdistrict.setIs_pesa(fullSubdistrictContributingList.get(j).getIs_pesa());
					coveredAreaListFull.add(subdistrict);
				}

			}
			if (temp[i].contains("PART")) {
				districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
				/*
				 * List<Subdistrict> lstTemp = null; lstTemp = new
				 * ArrayList<Subdistrict>();
				 * partSubdistrictContributingList.add(lstTemp.get(0));
				 */
				partSubdistrictContributingList = subdistrictDAO.getSubDistrictListbyDistrict(districtCodeNew);
				for (int j = 0; j < partSubdistrictContributingList.size(); j++) {
					subdistrict = new Subdistrict();
					subdistrict.setTlc(partSubdistrictContributingList.get(j).getTlc());
					subdistrict.setSubdistrictCode(partSubdistrictContributingList.get(j).getTlc());
					subdistrict.setSubdistrictNameEnglish(partSubdistrictContributingList.get(j).getSubdistrictNameEnglish());
					subdistrict.setSubdistrictNameLocal(partSubdistrictContributingList.get(j).getSubdistrictNameLocal());
					subdistrict.setDistrictNameEnglish(partSubdistrictContributingList.get(j).getDistrict().getDistrictNameEnglish()); // added
					//subdistrict.setIs_pesa(partSubdistrictContributingList.get(j).getIs_pesa());																										// by
																																		// sushil
					coveredAreaListFull.add(subdistrict);
				}
			}
		}
		return coveredAreaListFull;
	}

	@Override
	public List<Subdistrict> getPartSubdistrictListbyDistrict(String districtCode) throws BaseAppException {
		String selectedCoveredLandRegionByULB = districtCode;
		
		List<Subdistrict> partSubdistrictContributingList = null;
		partSubdistrictContributingList = new ArrayList<Subdistrict>();

		List<Subdistrict> coveredAreaListFull = null;
		coveredAreaListFull = new ArrayList<Subdistrict>();

		Subdistrict subdistrict = null;
		int districtCodeNew = 0;
		String[] temp = selectedCoveredLandRegionByULB.split(",");
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
				/*
				 * List<Subdistrict> lstTemp = null; lstTemp = new
				 * ArrayList<Subdistrict>();
				 * partSubdistrictContributingList.add(lstTemp.get(0));
				 */
				partSubdistrictContributingList = subdistrictDAO.getSubDistrictListbyDistrict(districtCodeNew);
				for (int j = 0; j < partSubdistrictContributingList.size(); j++) {
					subdistrict = new Subdistrict();

					subdistrict.setTlc(partSubdistrictContributingList.get(j).getTlc());
					subdistrict.setSubdistrictCode(partSubdistrictContributingList.get(j).getTlc());
					subdistrict.setSubdistrictNameEnglish(partSubdistrictContributingList.get(j).getSubdistrictNameEnglish());
					subdistrict.setSubdistrictNameLocal(partSubdistrictContributingList.get(j).getSubdistrictNameLocal());
					coveredAreaListFull.add(j, subdistrict);
				}
			}

		}
		return coveredAreaListFull;

	}

	public List<GazettePublication> getGazettePublicationDate() throws BaseAppException {
		List<GazettePublication> gazettePublication = new ArrayList<GazettePublication>();
		gazettePublication = districtDAO.getGazettePublication();
		return gazettePublication;

	}

	public List<GazettePublicationDateSave> getGazettePublicationDateSave(String orderCode, Date Gazettedate) throws BaseAppException {
		List<GazettePublicationDateSave> gazettePublicationsave = new ArrayList<GazettePublicationDateSave>();
		gazettePublicationsave = districtDAO.getGazettePublicationsave(orderCode, Gazettedate);
		return gazettePublicationsave;

	}

	public List<ParliamentConstituencymodify> getPcCode(Integer stateCode, char constituencType, Integer pcCode, Integer limit, Integer offset) throws BaseAppException {
		List<ParliamentConstituencymodify> parliamentconstituency = new ArrayList<ParliamentConstituencymodify>();
		parliamentconstituency = districtDAO.getpcCode(stateCode, constituencType, pcCode, limit, offset);
		return parliamentconstituency;

	}

	public List<StandardCodeDataForm> getStandardCode(StandardCodeForm standardCodeForm) throws BaseAppException {
		List<StandardCodes> standardCodesList = new ArrayList<StandardCodes>();
		standardCodesList = districtDAO.getStandardCode(standardCodeForm);
		List<StandardCodeDataForm> standardCodeDataList = new ArrayList<StandardCodeDataForm>();
		StandardCodeDataForm standardCodeDataForm = null;
		int i = 0;
		for (StandardCodes standardCodes : standardCodesList) {
			standardCodeDataForm = new StandardCodeDataForm();
			if (standardCodes.getCensus2011Code() != null) {
				standardCodeDataForm.setCensus2011Code(String.valueOf(standardCodes.getCensus2011Code()));
				standardCodeDataForm.setCensus2011Codech(String.valueOf(standardCodes.getCensus2011Code()));
			}
			standardCodeDataForm.setEntityCode(standardCodes.getEntityCode());
			standardCodeDataForm.setEntityNameEnglish(standardCodes.getEntityNameEnglish());
			standardCodeDataForm.setEntityNameLocal(standardCodes.getEntityNameLocal());
			standardCodeDataForm.setEntityNameLocalch(standardCodes.getEntityNameLocal());
			standardCodeDataForm.setSsCode(standardCodes.getSsCode());
			standardCodeDataForm.setSsCodech(standardCodes.getSsCode());
			standardCodeDataList.add(i, standardCodeDataForm);
			i++;

		}
		return standardCodeDataList;

	}

	public List<DistrictHistory> getDistrictHistoryDetail(char districtNameEnglish, int districtCode) throws BaseAppException {
		List<DistrictHistory> DistrictHistory = new ArrayList<DistrictHistory>();
		DistrictHistory = districtDAO.getDistrictHistoryDetail(districtNameEnglish, districtCode);
		return DistrictHistory;

	}

	// For localbody upload file
	public boolean writeMap(MultipartFile filePath, HttpServletRequest request) throws BaseAppException {

		FileOutputStream fo = null;
		try {
			MultipartFile map = filePath;

			if (map.getBytes().length > 0) {

				@SuppressWarnings("deprecation")
				String savePath = request.getRealPath("/") + map.getOriginalFilename();

				// String savePath =request.getRealPath("/") + "uploadedimages";
				File ff = new File(savePath);
				ff.createNewFile();
				fo = new FileOutputStream(ff);
				fo.write(map.getBytes());
				if (!ff.exists()) {
					ff.mkdirs();
				}
			}
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (fo != null){
					fo.close();
				}	
			} catch (IOException e) {
				// ProfilerLogger.getLogger(WriteImage.class).error("Unable to close  FileOutputStream  . . . . . . . . . .  .  . .  . . . . ."+e.toString());
			}
		}
		return true;
	}

	// End here

	public void saveHeadquarters(DistrictForm sdForm, int maxHCode, int regionCode, int regionVersion, Session session) throws BaseAppException {

		try {
			Headquarters headquarters = new Headquarters();

			headquarters.setHeadquarterCode(maxHCode);
			headquarters.setHeadquarterVersion(1);
			headquarters.setHeadquarterNameEnglish(sdForm.getHeadquarterName());
			headquarters.setHeadquarterLocalName(sdForm.getHeadquarterNameLocal());
			headquarters.setIsactive(true);
			// headquarters.setRegionCode(regionCode);
			// headquarters.setRegionVersion(regionVersion);
			headquarters.setRegionType('T');

			headquartersDAO.save(headquarters, session);
		} catch (Exception e) {
			Log.error(e);
		}
	}

	public List<District> getDistrict(String query) throws BaseAppException {
		return districtDAO.getDistrictViewList(query);
	}
	
	@Override
	public List<District> getDistrict(int districtCode)throws BaseAppException{
		return districtDAO.getDistrict(districtCode);
	}

	/*@Override
	@SuppressWarnings("unchecked")
	public List<Subdistrict> getVillageDetailforReorganize(String listVillCode) throws BaseAppException {

		Session session=null;
		
		List<Subdistrict> SubdistritList = new ArrayList<Subdistrict>();
		String sql = "SELECT  subdistrict.subdistrict_code, subdistrict.subdistrict_name_english, district.district_code, district.district_name_english FROM public.subdistrict, public.district "
				+ "WHERE district.district_code = subdistrict.subdistrict_code AND subdistrict.isactive = TRUE AND " + "district.isactive = TRUE AND subdistrict.subdistrict_code IN (" + listVillCode + ")";

		session=sessionFactory.openSession();
		List<Object[]> temp = session.createSQLQuery(sql).list();

		for (int k = 0; k < temp.size(); k++) {
			Subdistrict vill = new Subdistrict();
			Object vill1[] = temp.get(k);
			vill.setTlc((Integer) vill1[0]);
			vill.setSubdistrictNameEnglish(((String) vill1[1]) + " (" + ((String) vill1[3]) + ")");
			vill.setDlc((Integer) vill1[2]);
			vill.setSubdistrictNameLocal((String) vill1[3]); // sub district
																// name
																// temporarily
																// holding in
																// village name
																// local
			SubdistritList.add(vill);
		}

		if (session != null && session.isOpen()) {
			session.close();
		}
		return SubdistritList;
	}*/

	/*@Override
	public boolean saveDistrict(DistrictForm sdForm) {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {
			isMethod1Commited = this.publishSubdistrit(sdForm, session);
			tx.commit();
			isCommited = true;
		} catch (Exception e) {
			tx.rollback();
			isCommited = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isCommited;
	}*/

	/*@Override
	public boolean saveDistrictShift(DistrictForm sdForm, List<ShiftVillageForm> listvillageShift, HttpServletRequest request) throws BaseAppException {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;

		Session session = null;
		Transaction tx = null;

		session = null;
		tx = session.beginTransaction();

		isMethod1Commited = this.publishSubdistrit(sdForm, session);
		tx.commit();
		try {
			// shiftService.shiftDistrict(shiftDistrictForm,request);
			for (int i = 0; i < listvillageShift.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				isMethod2Commited = shiftService.shiftVillagereorganize(listvillageShift.get(i), request);
			}
			tx.commit();
			isCommited = true;
		} catch (Exception e) {
			tx.rollback();

			isCommited = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isCommited;

	}*/

	/*@Override
	public int publishMapLandRegion(DistrictForm sdForm, int sdCode, int sdVersion, Session session, HttpServletRequest request) throws BaseAppException {

		return 0;
	}*/

	@Override
	public boolean saveDistrict(DistrictForm sdForm, HttpServletRequest request) throws BaseAppException {

		return false;
	}

/*	@Override
	public boolean reOrganize(DistrictForm SDForm, List<VillageForm> listNewVillForm, List<VillageForm> listModifyVillForm, HttpServletRequest request, HttpSession httpSession) throws BaseAppException {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {
			for (int i = 0; i < listModifyVillForm.size(); i++) {
				isMethod2Commited = villageService.modifyVillageInfo((listModifyVillForm.get(i)), request, httpSession);
				tx.commit();
				session.close();

			}
			session = null;
			tx = null;

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			isMethod1Commited = this.publishSubdistrit(SDForm, session);
			tx.commit();
			session.close();

			if (isMethod1Commited == true) {
				for (int i = 0; i < listNewVillForm.size(); i++) {
					session = null;
					tx = null;

					session = sessionFactory.openSession();
					tx = session.beginTransaction();

					listNewVillForm.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
					listNewVillForm.get(i).setCreateFromExistingVillages(true);
					// isMethod3Commited=villageService.addVillage(listNewVillForm.get(i));
				}
			}
			tx.commit();
			session.close();

			
			 * if(listSDForm.size()>0){ session = null; tx = null;
			 * session=sessionFactory.openSession(); tx=
			 * session.beginTransaction(); for(int i=0;
			 * i<listSDForm.size();i++){
			 * isMethod3Commited=this.renameContributedSD(listSDForm.get(i),
			 * session); } tx.commit(); session.close(); }
			 
			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true) {
				// tx.commit();
				isCommited = true;
			}
		} catch (Exception e) {
			// tx.rollback();
			isCommited = false;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return isCommited;
	}*/

/*	@Override
	public boolean reOrganize(DistrictForm SDForm, List<VillageForm> listNewSubdist, HttpServletRequest request, HttpSession httpSession) throws BaseAppException {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		isMethod1Commited = this.publishSubdistrit(SDForm, session);
		tx.commit();
		session.close();
		try {
			for (int i = 0; i < listNewSubdist.size(); i++) {
				isMethod2Commited = villageService.modifyVillageInfo((listNewSubdist.get(i)), request, httpSession);
			}
			if (isMethod1Commited == true && isMethod2Commited == true) {
				// tx.commit();
				isCommited = true;
			}
		} catch (Exception e) {
			// tx.rollback();
			isCommited = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isCommited;
	}
*/
	/*@Override
	public boolean reOrganize(DistrictForm SDForm, List<VillageForm> listNewSubdistrictForm, List<VillageForm> listNewSubdist, List<SubDistrictForm> listNewVillForm, HttpServletRequest request, HttpSession httpSession) throws BaseAppException {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		Session session = null;
		Transaction tx = null;

		try {
			for (int i = 0; i < listNewSubdistrictForm.size(); i++) {
				isMethod1Commited = villageService.modifyVillageInfo((listNewSubdistrictForm.get(i)), request, httpSession);
			}
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			isMethod2Commited = this.publishSubdistrit(SDForm, session);
			tx.commit();
			session.close();
			// while(isMethod1Commited==true){
			for (int i = 0; i < listNewVillForm.size(); i++) {
				session = null;
				tx = null;
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewVillForm.get(i).setDistrictCode(String.valueOf(districtCode));
				// listNewVillForm.get(i).setSubdistrictNameEnglish(String.valueOf(districtCode));
				// listNewVillForm.get(i).setCreateFromExistingVillages(true);
				isMethod3Commited = subistrictService.publish(listNewVillForm.get(i), session);
				// }
			}
			tx.commit();
			session.close();
			for (int i = 0; i < listNewSubdist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				listNewSubdist.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
				// listNewVillForm.get(i).setCreateFromExistingVillages(true);
				isMethod4Commited = villageService.addVillage(listNewSubdist.get(i));
			}
			tx.commit();
			session.close();

			
			 * if(listSDForm.size()>0){ session = null; tx = null;
			 * session=sessionFactory.openSession(); tx=
			 * session.beginTransaction(); for(int i=0;
			 * i<listSDForm.size();i++){
			 * isMethod3Commited=this.renameContributedSD(listSDForm.get(i),
			 * session); } tx.commit(); session.close(); }
			 
			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true && isMethod4Commited == true) {
				// tx.commit();
				isCommited = true;
			}
		} catch (Exception e) {
			// tx.rollback();
			isCommited = false;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return isCommited;
	}
*/

	/*
	 * @Override public boolean reOrganizetotal(DistrictForm SDForm,
	 * List<SubDistrictForm> listNewSubdistrictForm, List<VillageForm>
	 * listNewVillForm, HttpServletRequest request,HttpSession httpSession) {
	 * boolean isCommited = false; boolean isMethod1Commited = false; boolean
	 * isMethod2Commited = false; boolean isMethod3Commited = false; Session
	 * session = null; Transaction tx = null;
	 * 
	 * session = sessionFactory.openSession(); tx = session.beginTransaction();
	 * 
	 * try { try { isMethod1Commited = this.publishSubdistrit(SDForm, session);
	 * tx.commit(); session.close(); }catch (Exception e) { exception
	 * log.error("Exception-->"+e);
	 * 
	 * } try { session = sessionFactory.openSession(); tx =
	 * session.beginTransaction();
	 * listNewSubdistrictForm.get(0).setDistrictCode(
	 * String.valueOf(districtCode)); isMethod2Commited =
	 * SubistrictService.publish( listNewSubdistrictForm.get(0), session);
	 * }catch (Exception e) { log.error("Exception-->"+e); } tx.commit();
	 * session.close(); try { session = sessionFactory.openSession(); tx =
	 * session.beginTransaction();
	 * listNewVillForm.get(0).setSubdistrictNameEnglish(String.valueOf(sdCode));
	 * listNewVillForm.get(0).setCreateFromExistingVillages(true);
	 * isMethod3Commited = villageService.addVillage( listNewVillForm.get(0));
	 * }catch (Exception e) { log.error("Exception-->"+e); } tx.commit();
	 * session.close();
	 * 
	 * 
	 * 
	 * 
	 * } catch (Exception e) { // tx.rollback(); isCommited = false; } finally {
	 * // session.close(); } return isCommited; }
	 */

	public List<District> getSubDistrictViewList(String query) throws BaseAppException {
		return districtDAO.getDistrictViewList(query);
	}

	@Override
	public boolean invalidateLoop(String districtCode, String subDistrictCodes, HttpSession httpSession) throws BaseAppException {
		try {
			sdVillMap.put(districtCode, subDistrictCodes);
			if (!sdVillMap.isEmpty()) {
				httpSession.setAttribute("sdVillMap", sdVillMap);
			}
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return false;
		}
		return true;
	}

	/*@Override
	public boolean invalidateDistrict(String districtCodes, int districtCode, long roleCode, String subDistrictCodes) throws BaseAppException {
		boolean success = false;

		try {
			success = districtDAO.invalidateFunctionCall(districtCodes, districtCode, roleCode, subDistrictCodes);
		} catch (Exception e) {
			log.error("Exception-->" + e);
			success = false;
		}

		return success;
	}
*/
	@Override
	public List<District> getDistrictListbyStateCode(int stateCode, String districtList) throws BaseAppException {
		return districtDAO.getDistrictListbyStateCode(stateCode, districtList);
	}

	@Override
	public List<District> getDistrictListbyStateCodeForListBox(int stateCode) throws BaseAppException {
		return districtDAO.getDistrictListbyStateCodeForListBox(stateCode);
	}

	/*@Override
	public District getDistrictBean(int districtCode) throws BaseAppException {
		District DistrictBean = null;
		DistrictBean = new District();

		return DistrictBean;
	}*/

	/*@Override
	public District getSingleDistrictDetailsMaxVersion(int dcode) throws BaseAppException {
		District d = new District();
		String sQuery = "SELECT * FROM  district where district_code=" + dcode + " and district_version=(select max(district_version) from district where district_code=" + dcode + ")";
		d = districtDAO.getSingleDistrictDetailsMaxVersion(sQuery);
		return d;
	}
*/
	/*
	 * @Override public boolean saveNewSubdistritVersion(int DistrictCode, int
	 * newSDCode, int newSDVersion, Session session) { method stub return false;
	 * }
	 */
	public GovernmentOrder saveDataInGovtOrder(DistrictForm govtForm, Session session) throws BaseAppException {

		GovernmentOrder governmentOrder = new GovernmentOrder();
		try {
			governmentOrder.setOrderDate(govtForm.getOrderDate());
			governmentOrder.setEffectiveDate(govtForm.getOrdereffectiveDate());
			governmentOrder.setGazPubDate(govtForm.getGazPubDate());
			governmentOrder.setCreatedon(new Date());
			governmentOrder.setDescription("LGD DETAILS");
			governmentOrder.setIssuedBy("GOVERNOR");
			governmentOrder.setCreatedby(createdBy);
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby(createdBy);
			governmentOrder.setLevel("U");
			governmentOrder.setOrderNo(govtForm.getOrderNo());
			governmentOrder.setStatus('A');
			governmentOrder.setUserId(userId);
			localGovtBodyDAO.saveOrderDetails(governmentOrder, session);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return governmentOrder;
	}

	public void saveDataInAttachment(GovernmentOrder govtOrder, DistrictForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws BaseAppException {

		Attachment attachment = null;
		try {
			Iterator<AttachedFilesForm> it = attachedList.iterator();
			while (it.hasNext()) {
				AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
				attachment = new Attachment();
				attachment.setGovernmentOrder(govtOrder);
				attachment.setFileName(filesForm.getFileName());
				attachment.setFileSize(filesForm.getFileSize());
				attachment.setFileContentType(filesForm.getFileType());
				attachment.setFileLocation(filesForm.getFileLocation());
				attachment.setFileTimestamp(filesForm.getFileTimestampName());
				session.save(attachment);
				session.flush();
				if (session.contains(attachment)){
					session.evict(attachment);
				}	
			}
		} catch (Exception e) {

			log.error("Exception-->" + e);
		}
	}

	@Override
	public synchronized boolean changeDistrict(DistrictForm districtForm, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request) throws BaseAppException {
         Session session=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Integer  tid = null, orderCode = null;
			int districtVersion = 0;
			List<DistrictDataForm> listDistrict = new ArrayList<DistrictDataForm>();
			listDistrict = districtForm.getListDistrictDetails();
			Iterator<DistrictDataForm> itr = listDistrict.iterator();
			
			boolean result = false;
			while (itr.hasNext()) {

				DistrictDataForm element = (DistrictDataForm) itr.next();
				districtVersion = districtDAO.getMaxDistrictVersionbyCode(element.getDistrictCode());
				if (districtVersion == 1) {
					districtVersion = districtVersion + 1;
				} else {
					districtVersion = districtVersion + 1;
				}

					
				
				String orderpath = "govtOrderUpload";
				String data = districtDAO.ChangeCrDistrict(element.getDistrictCode(), element.getDistrictNameEnglish(), element.getDistrictNameEnglishch(), districtForm.getUserId(), govtOrderForm.getOrderNo(), govtOrderForm.getOrderDate(),
						govtOrderForm.getEffectiveDate(), govtOrderForm.getGazPubDate(), orderpath, element.getDistrictNameLocalch(), element.getAliasEnglish(), element.getAliasLocalch(), session, districtForm.getStateCode());

				if (data != null) {
					// int ocode = Integer.parseInt(ordercode);
					String temp[] = data.split(",");
					tid = Integer.parseInt(temp[0]);
					orderCode = Integer.parseInt(temp[1]);
					GovernmentOrder govtOrder = new GovernmentOrder();
					govtOrder.setOrderDate(govtOrderForm.getOrderDate());
					govtOrder.setEffectiveDate(govtOrderForm.getEffectiveDate());
					govtOrder.setGazPubDate(govtOrderForm.getGazPubDate());
					govtOrder.setCreatedon(new Date());
					govtOrder.setDescription("LGD DETAILS");
					govtOrder.setIssuedBy("GOVERNOR");
					govtOrder.setCreatedby(createdBy);
					govtOrder.setLastupdated(new Date());
					govtOrder.setLastupdatedby(createdBy);
					govtOrder.setLevel("U");
					govtOrder.setOrderNo(govtOrderForm.getOrderNo());
					govtOrder.setStatus('A');
					govtOrder.setUserId(userId);
					govtOrder.setOrderCode(orderCode);
					/*
					 * GovernmentOrder govtOrder = convertLocalbodyService
					 * .saveDataInGovtOrder(govtOrderForm, session);
					 */
					convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm, attachedList, session);
					/*
					 * shiftService.saveDataInGovtOrderEntityWise(govtOrder,
					 * villageCode, 'V', session);
					 */

					//

					/*
					 * districtDAO.ChangeDistrict(element.getDistrictCode(),
					 * element.getDistrictNameEnglishch(), 555,
					 * element.getDistrictNameLocalch(),
					 * element.getAliasEnglish(), element.getAliasLocalch(),
					 * session);
					 */
					/*
					 * GovernmentOrder govtOrder = convertLocalbodyService
					 * .saveDataInGovtOrder(govtOrderForm, session);
					 * convertLocalbodyService.saveDataInAttachment(govtOrder,
					 * govtOrderForm, attachedList, session);
					 * shiftService.saveDataInGovtOrderEntityWise(govtOrder,
					 * districtCode, 'D', session);
					 */

					tx.commit();
					char t_type = 'D';
					EmailSmsThread est = new EmailSmsThread(tid, t_type, emailService);
					est.start();
					result = true;
				}
			}
			return result;

		} catch (Exception e) {
			log.error("Exception-->" + e);
			tx.rollback();
			return false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}
	}

	@Override
	public boolean changeDistrictforTemplate(DistrictForm districtForm, GovernmentOrderForm govtOrderForm, HttpServletRequest request, HttpSession httpSession) throws BaseAppException {
	 Session session=null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int districtVersion = 0;
			Integer tid = null, orderCode = null;
			boolean flag = false;
			List<DistrictDataForm> listDistrict = new ArrayList<DistrictDataForm>();
			listDistrict = districtForm.getListDistrictDetails();
			Iterator<DistrictDataForm> itr = listDistrict.iterator();
			Integer stateCode = null, slc = null;

			
			
			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
			

			while (itr.hasNext()) {

				DistrictDataForm element = (DistrictDataForm) itr.next();
				districtVersion = districtDAO.getMaxDistrictVersionbyCode(element.getDistrictCode());
				if (districtVersion == 1) {
					districtVersion = districtVersion + 1;
				} else {
					districtVersion = districtVersion + 1;
				}

				stateCode = element.getStateCode();
				slc = commonService.getSlc(stateCode);

				//String districtCode = Integer.toString(element.getDistrictCode());
				String orderpath = "govtOrderUpload";
				String data = districtDAO.ChangeCrDistrict(element.getDistrictCode(), element.getDistrictNameEnglish(), element.getDistrictNameEnglishch(), userId, govtOrderForm.getOrderNo(), govtOrderForm.getOrderDate(),
						govtOrderForm.getEffectiveDate(), govtOrderForm.getGazPubDate(), orderpath, element.getDistrictNameLocalch(), element.getAliasEnglish(), element.getAliasLocalch(), session, slc);

				if (data != null) {
					String temp[] = data.split(",");
					tid = Integer.parseInt(temp[0]);
					orderCode = Integer.parseInt(temp[1]);

					GenerateDetails generatedetails = new GenerateDetails();

					generatedetails = (GenerateDetails) httpSession.getAttribute("validGovermentGenerateUpload");

					/*
					 * GovernmentOrder govtOrder = convertLocalbodyService
					 * .saveDataInGovtOrder(govtOrderForm, session);
					 * shiftService.saveDataInGovtOrderEntityWise(govtOrder,
					 * subdistrictCode, 'T', session);
					 */
					if (orderCode != null) {
						flag = convertLocalbodyService.saveDataInAttachmentWithOrderCodeGenrate(orderCode, generatedetails, session);
					}

				}
				if (flag) {
					tx.commit();
					char t_type = 'D';
					EmailSmsThread est = new EmailSmsThread(tid, t_type, emailService);
					est.start();
				} else
					tx.rollback();
				return flag;
				/*
				 * GovernmentOrder govtOrder = convertLocalbodyService
				 * .saveDataInGovtOrder(govtOrderForm, session);
				 * shiftService.saveDataInGovtOrderEntityWise(govtOrder,
				 * districtCode, 'D', session);
				 */

			}

		} catch (Exception e) {

			log.error("Exception-->" + e);
			tx.rollback();
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				//session.clear();
			}
		}
		return true;
	}
	
	
	
	@Override
	public boolean modifyDistrictCrInfo(DistrictForm districtForm, HttpServletRequest request, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, boolean delflag, String deleteAttachmentId[], HttpSession httpSession)
			throws BaseAppException {

		Session session = null;
		Transaction tx1 = null;
		session = sessionFactory.openSession();
		tx1 = session.beginTransaction();
		/*try {
			// correction in district detail

			if (!districtForm.getListDistrictDetails().isEmpty()) {

				DistrictDataForm element = districtForm.getListDistrictDetails().get(0);
				Integer districtCode = element.getDistrictCode();
				Integer districtVersion = element.getDistrictVersion();
				DistrictPK districtPK = new DistrictPK();
				districtPK.setDistrictCode(districtCode);
				districtPK.setDistrictVersion(districtVersion);

				Date currentDate = dateTimeUtil.getCurrentDate();
				SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
				Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;

				//districtForm.setCensus2011Code(element.getCensus2011Code().toString());
				districtForm.setSscode(element.getSscode());

				String cord1 = "";
				if (districtForm.getLati() != null && !districtForm.getLati().isEmpty()) {
					if (districtForm.getLati().split(",").length >= 1) {
						String[] tempLati = districtForm.getLati().split(",");
						String[] tempLongi = districtForm.getLongi().split(",");
						cord1 = tempLati[0] + ":" + tempLongi[0] + ",";
						if (tempLati.length > 1) {
							for (int i = 1; i < tempLati.length; i++) {
								cord1 += tempLati[i] + ":" + tempLongi[i] + ",";
							}
						}
						cord1 = cord1.substring(0, cord1.length() - 1);
					}
				}

				String previousCords = element.getCordinate();
				previousCords = previousCords == null ? "" : previousCords;
				if (!cord1.equalsIgnoreCase(previousCords)) {
					districtForm.setWarningflag(false);
				}

				Integer mapCode = element.getMapCode();

				if (delflag == true) {
					govtOrderService.deleteMapAttachementforLandRegion(element.getDistrictCode(), 'D', session);
					mapCode = null;
				}

				if (attachedMapList != null) {
					mapCode = govtOrderService.saveDatainMapAttachmentforLandregion(attachedMapList, element.getDistrictCode(), 'D', session);

				}

				District district = (District) session.get(District.class, districtPK);
				district.setAliasEnglish(element.getAliasEnglish());
				district.setAliasLocal(element.getAliasLocal());
				//district.setCensus2011Code(element.getCensus2011Code());
				district.setSscode(element.getSscode());
				//district.setMapCode(mapCode);
				district.setWarningflag(districtForm.isWarningflag());
				district.setCoordinates(cord1);
				session.update(district);
				session.flush();

				if (element.getHeadquarterName() != "" || element.getHeadquarterNameLocal() != "") {

					List<Headquarters> headquarterList = stateService.getHeadquterDetailALL(element.getDistrictCode(), "D");
					for (Headquarters headquarter : headquarterList) {

						Headquarters headquarterupdate = (Headquarters) session.get(Headquarters.class, headquarter.getHeadquarterCode());
						headquarterupdate.setIsactive(false);

						session.update(headquarterupdate);
						session.flush();
						session.clear();

					}

					Headquarters headquarters = new Headquarters();
					Integer headquarterCode = element.getHeadquarterCode();
					if (headquarterCode != null) {
						headquarters.setHeadquarterCode(headquarterCode);

					}

					headquarters.setHeadquarterVersion(1);
					headquarters.setIsactive(true);
					headquarters.setRegionType('D');
					headquarters.setLrlc(districtCode);
					headquarters.setHeadquarterNameEnglish(element.getHeadquarterName());
					headquarters.setHeadquarterLocalName(element.getHeadquarterNameLocal());
					headquartersDAO.saveOrUpdate(headquarters, session);
				}

				GovernmentOrder govtOrder = new GovernmentOrder();
				if (element.getOrderCodecr() != null) {

					govtOrder = govtOrderService.updateGovernmentOrder(element.getOrderNocr(), element.getOrderCodecr(), element.getOrderDatecr(), element.getOrdereffectiveDatecr(), element.getGazPubDatecr(), "update", request, session);
				} else {
					
					 * insert the record into goverment order and goverment
					 * order entity wise table if record is blank for goverment
					 * order
					 if (element.getOrderNocr() != "" && element.getOrderDatecr() != null && element.getOrdereffectiveDatecr() != null) {
						govtOrder = new GovernmentOrder();
						govtOrder.setOrderDate(element.getOrderDatecr());
						govtOrder.setEffectiveDate(element.getOrdereffectiveDatecr());
						govtOrder.setOrderNo(element.getOrderNocr());
						govtOrder.setGazPubDate(element.getGazPubDatecr());
						govtOrder.setDescription("insert");
						govtOrder.setCreatedby(userId);
						govtOrder.setCreatedon(currentDate);
						govtOrder.setLastupdated(currentDate);
						govtOrder.setLastupdatedby(userId.longValue());
						govtOrder.setIssuedBy("insert");
						govtOrder.setLevel("1");
						govtOrder.setUserId(userId.longValue());
						govtOrder.setXmlDbPath("sdf");
						govtOrder.setXmlOrderPath("");
						session.save(govtOrder);
						session.flush();
						session.refresh(govtOrder);
						//Integer orderCode = null;
						//orderCode = govtOrder.getOrderCode();

						GovernmentOrderEntityWise governmentOrderEntityWise = new GovernmentOrderEntityWise();
						governmentOrderEntityWise.setEntityCode(districtCode);
						governmentOrderEntityWise.setEntityType('D');
						governmentOrderEntityWise.setEntityVersion(districtVersion);
						governmentOrderEntityWise.setGovernmentOrder(govtOrder);
						session.saveOrUpdate(governmentOrderEntityWise);
						session.flush();
					}

				}*/
		
		
		   try
		   {
			   List<DistrictDataForm> listDistrict = districtForm.getListDistrictDetails();
		   
			   
			   if (!listDistrict.isEmpty()) {
					
				   DistrictDataForm element = listDistrict.get(0);

					Query query = session.createSQLQuery("select * from basic_correction_in_district(:districtCode,:userId,:aliasEnglish,:aliasLocal,:headquartersEng,:headquartersLocal,:sscode,:order_no,:order_date,:gaz_pubdate,:govtOrderConfig)");
					 query.setParameter("districtCode"	  ,element.getDistrictCode()	       , Hibernate.INTEGER)
						.setParameter("userId"	    	  ,userId				  			   , Hibernate.INTEGER)
						.setParameter("aliasEnglish"  	  ,element.getAliasEnglish()		   , Hibernate.STRING)
						.setParameter("aliasLocal"		  ,element.getAliasLocal()			   , Hibernate.STRING)
						.setParameter("headquartersEng"   ,element.getHeadquarterName()		   , Hibernate.STRING)
						.setParameter("headquartersLocal" ,element.getHeadquarterNameLocal()   , Hibernate.STRING)
						.setParameter("sscode"			  ,element.getSscode()				   , Hibernate.STRING)
						.setParameter("order_no"		  ,element.getOrderNocr()			   , Hibernate.STRING)
						.setParameter("order_date"		  ,element.getOrderDatecr()			   , Hibernate.TIMESTAMP)
						.setParameter("gaz_pubdate"		  ,element.getGazPubDatecr()		   , Hibernate.TIMESTAMP)
						.setParameter("govtOrderConfig"   ,"upload"			                   , Hibernate.STRING);
						
					String returnedValue = (String) query.uniqueResult();
					tx1.commit();
					if (returnedValue != null) {
						String[] ldata = returnedValue.split(",");
						String orderCode = ldata[0];
						String tid = ldata[1];
						
						
						int Transactionid = Integer.parseInt(tid);
						int ocode = Integer.parseInt(orderCode);
						
						GovernmentOrder govtOrder=(GovernmentOrder)session.get(GovernmentOrder.class, ocode);
						
			   
			   
			   
			   

				if (deleteAttachmentId != null) {
					govtOrderDAO.deleteAttachmentForLandRegion(deleteAttachmentId, session);

				}

				if (attachedList != null) {
					villageService.saveDataInAttachment(govtOrder, attachedList, session);
				}

			   }
		}
			   
		} catch (Exception e) {
			tx1.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return true;
	}

	/* Retrieve files from the attachment table */

	public List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws BaseAppException {
		return districtDAO.getAttacmentdetail(orderCode);
	}

	/* Retrieving the Map details attachment from the database */

	/*public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode) throws BaseAppException {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			return districtDAO.getEntityWiseMapDetails(entityType, entityCode, session);
		} catch (Exception e) {
			log.error("Exception-->" + e);
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session.clear();
			}
		}
		return null;

	}
*/
	@Override
	public List<StandardCodeDataForm> updateStandardCode(StandardCodeForm standardCodeForm) throws BaseAppException {
		return(districtDAO.updateStandardCode(standardCodeForm));
		
	}

	/*@Override
	public District getById(Integer subDistrictCode, Session session) throws BaseAppException {

		return districtDAO.getById(subDistrictCode, session);
	}
*/
	@Override
	public int saveDataInMap(DistrictForm districtForm, List<AttachedFilesForm> validFileMap, HttpSession httpSession, Session session) {
		return districtDAO.saveDataInMap(districtForm, validFileMap, httpSession, session);
	}

	@Override
	public String insertDistrict(HttpServletRequest request, HttpSession httpSession, DistrictForm districtForm, Session session) {
		return districtDAO.insertDistrict(request, httpSession, districtForm, session);

	}

	/*@Override
	public void saveOrUpdate(int subDistrictId, int mapAttachmentId, Session session) {
		districtDAO.saveOrUpdate(subDistrictId, mapAttachmentId, session);
	}*/
    /**
     * Changes for add isPesa Flag for Mark Pesa Land Regions by Ripunj on 18-11-2014 
     * 
     */
	/*public List<Village> getVillageListRemovingOldVillage(String districtCode, String oldVillage, String[] subdistrictCode) throws BaseAppException {
		try {
			log.debug("village  code is" + districtCode);
			String selectedCoveredLandRegionByULB = districtCode;
			List<Village> partSubdistrictContributingList = null;
			partSubdistrictContributingList = new ArrayList<Village>();

			List<Village> coveredAreaListFull = null;
			coveredAreaListFull = new ArrayList<Village>();
			List<Village> finalcoveredAreaListFull = null;
			finalcoveredAreaListFull = new ArrayList<Village>();

			List<Village> tempcoveredAreaListFull = null;
			tempcoveredAreaListFull = new ArrayList<Village>();
			Village village = null;
			int districtCodeNew = 0;
			String[] temp = selectedCoveredLandRegionByULB.split(",");
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].contains("PART")) {
					districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					partSubdistrictContributingList = subdistrictDAO.getSubdistrictListbyVillageCode(districtCodeNew);
					for (int j = 0; j < partSubdistrictContributingList.size(); j++) {
						village = new Village();
						village.setVlc(partSubdistrictContributingList.get(j).getVlc());
						village.setVillageCode(partSubdistrictContributingList.get(j).getVlc());
						village.setVillageNameEnglish(partSubdistrictContributingList.get(j).getVillageNameEnglish());
						village.setVillageNameLocal(partSubdistrictContributingList.get(j).getVillageNameLocal());
						village.setIs_pesa(partSubdistrictContributingList.get(j).getIs_pesa());
						if (subdistrictCode[i] != null) {
							village.setSubdistrict(subdistrictCode[i]); // added
																		// by
																		// sushil
						}
						coveredAreaListFull.add(village);
					}
				}

				if (oldVillage != null && !oldVillage.equals("")) {
					String[] arrayTotalVillage = oldVillage.split(",");
					for (Village villageObject : coveredAreaListFull) {
						for (int j = 0; j < arrayTotalVillage.length; j++) {
							int tempval = 0;
							if (arrayTotalVillage[j] != null && !arrayTotalVillage[j].equals("")) {
								tempval = Integer.parseInt(arrayTotalVillage[j]);
							}
							if (tempval == villageObject.getVlc()) {
								tempcoveredAreaListFull.add(villageObject);
							}
						}
					}
					coveredAreaListFull.removeAll(tempcoveredAreaListFull);
				}
				finalcoveredAreaListFull.addAll(coveredAreaListFull);
			}
			return finalcoveredAreaListFull;
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		}
	}*/

	@Override
	public String saveInvalidateDistrict(DistrictForm districtForm, HttpSession httpSession) {
		
		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		String orderCode = null;
		try {

			orderCode = districtDAO.saveInvalidateDistrictDAO(districtForm, hsession, districtForm.getUserId().longValue());

			tx.commit();

		} catch (Exception e) {
			log.error("Exception-->" + e);

			tx.rollback();
		} finally {
			if (hsession != null && hsession.isOpen()){
				hsession.close();
			}	

		}
		return orderCode;
	}

	@Override
	public int findDuplicate(String districtName, Integer stateCode) throws BaseAppException {
		return districtDAO.findDuplicate(districtName, stateCode);
	}

	public List<Subdistrict> getSubdistrictListbyDistrictCode(int districtCode) throws BaseAppException {
		return subdistrictDAO.getSubDistrictLists(districtCode);
	}

	public List<District> getTargetDistrictShiftSDistrict(int sourcedistrictCode, int stateCode) throws BaseAppException {
		Session session = null;
		List<District> distListbySubDistCodeShift = new ArrayList<District>();
		try {
			distListbySubDistCodeShift = districtDAO.getTargetDistrictShiftSDistrict(sourcedistrictCode, stateCode);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distListbySubDistCodeShift;

	}

	/*public List<District> getTargetDistrictShiftSDistrictFinal(int sourcedistrictCode, int stateCode) throws BaseAppException {
		Session session = null;
		List<District> distListbySubDistCodeShift = new ArrayList<District>();
		try {
			distListbySubDistCodeShift = districtDAO.getTargetDistrictShiftSDistrictFinal(sourcedistrictCode, stateCode);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distListbySubDistCodeShift;

	}*/

	public List<District> getDistrictListExtend(Integer stateCode, Integer orgCode) throws BaseAppException {

		List<District> district = null;
		try {
			district = districtDAO.getDistrictListExtend(stateCode, orgCode);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return district;

	}

	public List<District> getDistrictListExtendforBlock(Integer stateCode, Integer orgCode) throws BaseAppException {

		List<District> district = null;
		try {
			district = districtDAO.getDistrictListExtendforBlock(stateCode, orgCode);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return district;

	}

	@Override
	public List<District> getDistrictListbyStateCodeExtend(Integer stateCode, String districtList, Integer orgCode, char type) throws BaseAppException {
		return districtDAO.getDistrictListbyStateCodeExtend(stateCode, districtList, orgCode, type);
	}

	@Override
	public int updateDistrictMap(Session session, int... params) {
		return districtDAO.updateDistrictMap(session, params);
	}
	
	
	@Override
	public List<DistrictWiseLBReportBean> DistrictWiseLBReportDetail(Integer districtCode) throws BaseAppException {
		return districtDAO.DistrictWiseLBReportDetail(districtCode);
	}

	@Override
	public List<District> getDistrictListbyStateCodeForListBoxExtended(int stateCode,int orgCode,int locatedatlevelCode) throws BaseAppException {
		return districtDAO.getDistrictListbyStateCodeForListBoxExtended(stateCode,orgCode,locatedatlevelCode);
	}
	
	@Override
	public List<District> getDistrictListbyStateCodeExtended(int stateCode,
			String districtList, int orgCode, int locatedatlevelCode)
			throws BaseAppException {
		return districtDAO.getDistrictListbyStateCodeExtended(stateCode,districtList,orgCode,locatedatlevelCode);
	}
	/**
	 * For Mark PESA Land Region
	 * @author Ripunj on 13-11-2014
	 * @param districtForm
	 * @param subDistrictForm
	 * @param villageForm
	 * @return
	 * @throws BaseAppException
	 */
	/*parameter added by kirandeep for is pesa langregion modified use case*/
	/*Modify by Sunita on 07/04/2016 for new function Mark PESA Land Region 
	 * added parameter input param*/
	@Override
	public boolean updatePesaStatus(String inputParam,Integer stateCode) throws BaseAppException {
		return districtDAO.updatePesaStatus(inputParam, stateCode);
	}
	
	
	
	public List<Village> getVillageList(String districtCode, String oldVillage, String[] subdistrictCode) throws BaseAppException {
		try {
			String selectedCoveredLandRegionByULB = districtCode;
			List<Village> partSubdistrictContributingList = null;
			partSubdistrictContributingList = new ArrayList<Village>();

			List<Village> coveredAreaListFull = null;
			coveredAreaListFull = new ArrayList<Village>();
			
			Village village = null;
			int districtCodeNew = 0;
			String[] temp = selectedCoveredLandRegionByULB.split(",");
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].contains("PART")) {
					districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					partSubdistrictContributingList = subdistrictDAO.getSubdistrictListbyVillageCode(districtCodeNew);
					for (int j = 0; j < partSubdistrictContributingList.size(); j++) {
						village = new Village();
						village.setVlc(partSubdistrictContributingList.get(j).getVlc());
						village.setVillageCode(partSubdistrictContributingList.get(j).getVlc());
						village.setVillageNameEnglish(partSubdistrictContributingList.get(j).getVillageNameEnglish());
						village.setVillageNameLocal(partSubdistrictContributingList.get(j).getVillageNameLocal());
						//village.setIs_pesa(partSubdistrictContributingList.get(j).getIs_pesa());
						if (subdistrictCode[i] != null) {
							village.setSubdistrict(subdistrictCode[i]); // added
																		// by
																		// sushil
						}
						coveredAreaListFull.add(village);
					}
				}
			}
			return coveredAreaListFull;
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		}
	}
	
	
	/*
	 * added on 11/12/2014 for localbody impact
	 * 
	 */
	
	
	public List<District> getDistrictListbyStateCodeForLocalBody(int slc) throws BaseAppException{
		
		return districtDAO.getDistrictListbyStateCodeForLocalBody(slc);
		
	}

	/* Modified by Pooja on 10-08-2015 */
	@Override
	public List<Subdistrict> getSubDistrictListbyDistrictForLocalBody(String districtCode) throws BaseAppException {

		List<Subdistrict> partSubdistrictContributingList = new ArrayList<Subdistrict>();
		String districtCodeNew = "";
		String[] temp = districtCode.split(",");
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				districtCodeNew = districtCodeNew + (temp[i].substring(0, temp[i].length() - 4)) + ",";
			}
		}
		if (!("").equals(districtCodeNew))
			partSubdistrictContributingList = subdistrictDAO.getSubDistrictListbyDistrictForLocalBody(districtCodeNew.substring(0, districtCodeNew.length() - 1));
		return partSubdistrictContributingList;

	}
	
	/**
	 * Added for the lgd Impact by kirandeep on 02/01/2015
	 * @throws Exception 
	 */
	public List<Subdistrict> getSubdistrictListbyDistrictInDistrictForLocalBody(String districtCode) throws Exception {
		String selectedCoveredLandRegionByULB = districtCode;
		List<Subdistrict> fullSubdistrictContributingList = null;
		fullSubdistrictContributingList = new ArrayList<Subdistrict>();
		List<Subdistrict> partSubdistrictContributingList = null;
		partSubdistrictContributingList = new ArrayList<Subdistrict>();

		List<Subdistrict> coveredAreaListFull = null;
		coveredAreaListFull = new ArrayList<Subdistrict>();

		Subdistrict subdistrict = null;
		int districtCodeNew = 0;
		String[] temp = selectedCoveredLandRegionByULB.split(",");
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));

				/*
				 * List<Subdistrict> lstTemp = null; lstTemp = new
				 * ArrayList<Subdistrict>();
				 * fullSubdistrictContributingList.add(lstTemp.get(0));
				 */
				fullSubdistrictContributingList = subdistrictDAO.getSubDistrictListbyDistrictInDistrictForLocalBody(districtCodeNew);

				for (int j = 0; j < fullSubdistrictContributingList.size(); j++) {
					subdistrict = new Subdistrict();

					subdistrict.setTlc(fullSubdistrictContributingList.get(j).getTlc());
					subdistrict.setSubdistrictCode(fullSubdistrictContributingList.get(j).getTlc());
					subdistrict.setCensus2001Code(fullSubdistrictContributingList.get(j).getTlc() + "FULL");
					subdistrict.setSubdistrictNameEnglish(fullSubdistrictContributingList.get(j).getSubdistrictNameEnglish() + " (" + "FULL" + ")");
					subdistrict.setSubdistrictNameLocal(fullSubdistrictContributingList.get(j).getSubdistrictNameLocal());
					//subdistrict.setIs_pesa(fullSubdistrictContributingList.get(j).getIs_pesa());
					subdistrict.setOperation_state(fullSubdistrictContributingList.get(j).getOperation_state());
					
					coveredAreaListFull.add(subdistrict);
				}

			}
			if (temp[i].contains("PART")) {
				districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
				/*
				 * List<Subdistrict> lstTemp = null; lstTemp = new
				 * ArrayList<Subdistrict>();
				 * partSubdistrictContributingList.add(lstTemp.get(0));
				 */
				partSubdistrictContributingList = subdistrictDAO.getSubDistrictListbyDistrictInDistrictForLocalBody(districtCodeNew);
				for (int j = 0; j < partSubdistrictContributingList.size(); j++) {
					subdistrict = new Subdistrict();
					subdistrict.setTlc(partSubdistrictContributingList.get(j).getTlc());
					subdistrict.setSubdistrictCode(partSubdistrictContributingList.get(j).getTlc());
					subdistrict.setSubdistrictNameEnglish(partSubdistrictContributingList.get(j).getSubdistrictNameEnglish());
					subdistrict.setSubdistrictNameLocal(partSubdistrictContributingList.get(j).getSubdistrictNameLocal());
					subdistrict.setDistrictNameEnglish(partSubdistrictContributingList.get(j).getDistrictNameEnglish()); // added
					//subdistrict.setIs_pesa(partSubdistrictContributingList.get(j).getIs_pesa());
					subdistrict.setOperation_state(partSubdistrictContributingList.get(j).getOperation_state());// by
																																		// sushil
					coveredAreaListFull.add(subdistrict);
				}
			}
		}
		return coveredAreaListFull;
	}
	
	
	/*added on 05/01/2015 for local body impact by kirandeep*/
	@Override
	public List<Village> getVillageListRemovingOldVillageForDistrictForm(String districtCode, String oldVillage, String[] subdistrictCode) throws BaseAppException {
		try {
			String selectedCoveredLandRegionByULB = districtCode;
			List<Village> partSubdistrictContributingList = null;
			partSubdistrictContributingList = new ArrayList<Village>();

			List<Village> coveredAreaListFull = null;
			
			List<Village> finalcoveredAreaListFull = null;
			finalcoveredAreaListFull = new ArrayList<Village>();

			List<Village> tempcoveredAreaListFull = null;
			tempcoveredAreaListFull = new ArrayList<Village>();
			Village village = null;
			int districtCodeNew = 0;
			String[] temp = selectedCoveredLandRegionByULB.split(",");
			for (int i = 0; i < temp.length; i++) {
				coveredAreaListFull = new ArrayList<Village>();  // duplicate village come when two and more subdistrict  select bug resolved by Maneesh Kumar since 25Jan2016
				if (temp[i].contains("PART")) {
					districtCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					partSubdistrictContributingList = subdistrictDAO.getSubdistrictListbyVillageCodeForDistrictForm(districtCodeNew);
					for (int j = 0; j < partSubdistrictContributingList.size(); j++) {
						village = new Village();
						village.setVlc(partSubdistrictContributingList.get(j).getVlc());
						village.setVillageCode(partSubdistrictContributingList.get(j).getVlc());
						village.setVillageNameEnglish(partSubdistrictContributingList.get(j).getVillageNameEnglish());
						village.setVillageNameLocal(partSubdistrictContributingList.get(j).getVillageNameLocal());
						//village.setIs_pesa(partSubdistrictContributingList.get(j).getIs_pesa());
						village.setOperation_state(partSubdistrictContributingList.get(j).getOperation_state());
						if (subdistrictCode[i] != null) {
							village.setSubdistrict(subdistrictCode[i]); // added
																		// by
																		// sushil
						}
						coveredAreaListFull.add(village);
					}
				}

				if (oldVillage != null && !oldVillage.equals("")) {
					String[] arrayTotalVillage = oldVillage.split(",");
					for (Village villageObject : coveredAreaListFull) {
						for (int j = 0; j < arrayTotalVillage.length; j++) {
							int tempval = 0;
							if (arrayTotalVillage[j] != null && !arrayTotalVillage[j].equals("")) {
								tempval = Integer.parseInt(arrayTotalVillage[j]);
							}
							if (tempval == villageObject.getVlc()) {
								tempcoveredAreaListFull.add(villageObject);
							}
						}
					}
					coveredAreaListFull.removeAll(tempcoveredAreaListFull);
				}
				finalcoveredAreaListFull.addAll(coveredAreaListFull);
			}
			return finalcoveredAreaListFull;
		} catch (Exception e) {
			log.error("Exception-->" + e);
			return null;
		}
	}
	
	
	

	/**
	 * added on 06/01/2015 by kirandeep for localbody impact issue.
	 */	
	@Override
	public List<District> getTargetDistrictShiftSDistrictForlocalbody(int sourceDistrictCode, int statecode) throws BaseAppException {
		
		return districtDAO.getTargetDistrictShiftSDistrictForlocalbody(sourceDistrictCode, statecode);
		
	}
	
	/**
	 * added on 07/01/2015 by kirandeep for localbody impact issue.
	 */	
	public List<District> getDistrictListbyStateCodeForLocalbody(int stateCode,	String districtList) throws BaseAppException{
			return districtDAO.getDistrictListbyStateCodeForLocalbody(stateCode, districtList);
		
	}

	@Override
	public List<DistrictHistory> getDistrictHistoryReport(char districtNameEnglish,
			int districtCode) throws Exception {
		// TODO Auto-generated method stub
		return districtDAO.getDistrictHistoryReport(districtNameEnglish,districtCode);
	}

	@Override
	public List<District> getDistrictListbyDistrictCodeForLocalBody(int dlc) throws BaseAppException {
	return districtDAO.getDistrictListbyDistrictCodeForLocalBody(dlc);
	}
	
	/** 
	 * @author Kirandeep for landregion is pesa 
	 * date: 30/04/2015
	 * @param entityCodes
	 * @param type
	 * @return
	 * @throws BaseAppException
	 */
	@Override
	public List<ParentHeirarchyBean> getHeirarchyByParentCodes(String entityCodes,Character type,Character afterPost)  throws BaseAppException {
		return districtDAO.getHeirarchyByParentCodes(entityCodes,type,afterPost);
	}
	
	/**
	 * Code used for getting districtList for multiple districts
	 * @author Pooja
	 * @since 21-10-2015
	 * @param states
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<District> getDistrictListbyStates(String states) throws Exception{
		List<District> partDistrictContributingList = new ArrayList<District>();
		String statesNew = "";
		String[] temp = states.split(",");
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				statesNew = statesNew + (temp[i].substring(0, temp[i].length() - 4)) + ",";
			}
		}
		if (!("").equals(statesNew))
			partDistrictContributingList = districtDAO.getDistrictListbyStates(statesNew.substring(0, statesNew.length() - 1));
		return partDistrictContributingList;
		
	}

	@Override
	public List<District> getDistrictListbyCriteria(Integer entityCode, String entityType) throws Exception {
		return districtDAO.getDistrictListbyCriteria(entityCode, entityType);
	}

	@Override
	public String getDistrictNameEnglishbyDistrictCode(Integer districtCode) {
		return districtDAO.getDistrictNameEnglishbyDistrictCode(districtCode);
	}
	
	/*@Author Pranav Tiwari
	* To get district Url for GIS
	* On 21-03-2017*/
	
	@Override
	public String getDistrictForGIS(Integer districtCode,String districtNameEnglish,String isShowOnlyBoundary)throws IOException{
		try {
			
			String gisDistrictURL = null;
			boolean isMapFin = districtDAO.districtMapFinalised(districtCode);
			if(isMapFin)
				return "mapNtFin";
			else{
			java.util.Properties properties = new java.util.Properties();
			InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");
			properties.load(inputStreamPro);
			String gisServerLoc=properties.getProperty("gisMap.server.location");
			String enDistCode=EncryptionUtil.encode(districtCode.toString());
			
			String enDistrictName=EncryptionUtil.encode(districtNameEnglish);
			String enisShowOnlyBoundary=EncryptionUtil.encode(isShowOnlyBoundary);
		
			 gisDistrictURL = gisServerLoc+"?distCode=" + enDistCode +"&distName="+enDistrictName+"&isShowOnlyBoundary="+enisShowOnlyBoundary;
			return gisDistrictURL;
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return "FAILED";
		}
		
	}

	@Override
	public boolean distInvalFnAfterCreateMulDist(Integer districtCode, Integer userId, Date effectiveDate) throws Exception {
		return districtDAO.distInvalFnAfterCreateMulDist(districtCode, userId, effectiveDate);
	}

	@Override
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion,int minorVersion)throws BaseAppException {
		// TODO Auto-generated method stub
		return districtDAO.GovOrderDetail(entityType, entityCode, entityVersion,entityVersion);
	}

	@Override
	public Response saveEffectiveDateEntityDistrict(List<GetEntityEffectiveDate> getEntityEffectiveDateList,
			Integer userId) throws Exception {
		
		return districtDAO.saveEffectiveDateEntityDistrict(getEntityEffectiveDateList, userId);
	}

	
	
	

}