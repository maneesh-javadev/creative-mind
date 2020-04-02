package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.AssemblyConstituencyId;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWiseNew;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.LandregionReplacedby;
import in.nic.pes.lgd.bean.LandregionReplaces;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.ParliamentConstituencyId;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictHistory;
import in.nic.pes.lgd.bean.SubdistrictPK;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.common.ReleaseResources;
import in.nic.pes.lgd.dao.GovernmentOrderDAO;
import in.nic.pes.lgd.dao.HeadquartersDAO;
import in.nic.pes.lgd.dao.LandRegionReplacedByDAO;
import in.nic.pes.lgd.dao.LandRegionReplacesDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.MapLandRegionDAO;
import in.nic.pes.lgd.dao.ParliamentDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.forms.AssemblyDataForm;
import in.nic.pes.lgd.forms.AssemblyForm;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.ParliamentDataForm;
import in.nic.pes.lgd.forms.ParliamentForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.forms.VillageDataForm;
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
import in.nic.pes.lgd.ws.InvalidateSubdistrictForm;
import in.nic.pes.lgd.ws.InvalidateVillageForm;
import in.nic.pes.lgd.ws.LandDetails;
import in.nic.pes.lgd.ws.Subdistricts;
import in.nic.pes.lgd.ws.Villages;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exception.BaseAppException;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

/**
 * 
 * @Author: Ram
 */
public class SubDistrictServiceImpl implements SubDistrictService {
	private static final Logger log = Logger.getLogger(SubDistrictServiceImpl.class);

	@Autowired
	EmailService emailService;

	@Autowired
	StateService stateService;

	@Autowired
	CommonService commonService;

	@Autowired
	GovernmentOrderDAO govtOrderDAO;

	@Autowired
	private SubDistrictDAO subdistrictDAO;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	private MapLandRegionDAO mapLandRegionDAO;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private LandRegionReplacedByDAO landRegionReplacedByDAO;

	@Autowired
	private LandRegionReplacesDAO landRegionReplacesDAO;

	@Autowired
	private VillageDAO villageDAO;

	@Autowired
	HeadquartersDAO headquartersDAO;

	@Autowired
	VillageService villageService;

	@Autowired
	DistrictService districtService;

	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	ShiftService shiftService;

	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	ParliamentDAO parliamentDAO;

	@Autowired
	CurrentDateTime dateTimeUtil;
	
	@Autowired
	DraftUtils draftUtils;
	

	// get the district code, district version, map code from session
	// int districtCode = 34;
	// int districtVersion = 1;
	int mapCd;
	int distictCode;
	int districVersion;
	int userId = 1000;
	long createdBy = 1000;
	Session session = null;
	Transaction tx = null;
	Map<String, String> sdVillMap = new HashMap<String, String>();
	// District dist=new District(districtCode,districtVersion);
	Date timestamp = DatePicker.getDate("2011-09-26");

	int sdCode;
	int sdVersionCode;

	/*@Override
	@Transactional
	public boolean publish(SubDistrictForm sdForm, Session session) throws Exception {
		boolean isCommited = false;
		sdCode = subdistrictDAO.getMaxSubDistrictCode() + 1;
		sdVersionCode = 1;

		List<Subdistrict> fullContributedList = null;
		List<Subdistrict> partContributedList = null;
		List<Village> vFullList = null;
		List<Village> vPartList = null;

		fullContributedList = new ArrayList<Subdistrict>();
		partContributedList = new ArrayList<Subdistrict>();
		vFullList = new ArrayList<Village>();
		vPartList = new ArrayList<Village>();

		int lrReplacedBy = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
		int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
		int rByid = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
		int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
		int headquarterCode = headquartersDAO.getMaxHeadquartersCode();

		String[] selectedSubDistricts = sdForm.getContributedSubDistricts().split(",");
		for (int i = 0; i < selectedSubDistricts.length; i++) {
			int sCodeFull = Integer.parseInt(selectedSubDistricts[i].substring(0, selectedSubDistricts[i].length() - 4));

			List<Subdistrict> lstTemp = null;
			lstTemp = new ArrayList<Subdistrict>();

			lstTemp = subdistrictDAO.viewSubDistrictDetails("from Subdistrict s where subdistrictCode=" + sCodeFull + " and isactive=true");

			if (selectedSubDistricts[i].contains("FULL")) {
				fullContributedList.add(lstTemp.get(0));
			} else if (selectedSubDistricts[i].contains("PART")) {
				partContributedList.add(lstTemp.get(0));
			}
		}
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
		try {
			if (fullContributedList.size() > 0 && partContributedList.size() == 0) {
				mapCd = this.publishMapLandRegion(sdForm, sdCode, sdVersionCode, session);
				this.saveSubDistrict(sdForm, sdCode, sdVersionCode, mapCd, session);
				this.saveHeadquarters(sdForm, headquarterCode, sdCode, sdVersionCode, session);

				// Land Region Replaced By

				this.saveReplacedBy(rByid, lrReplacedBy, sdCode, sdVersionCode, session);
				for (int k = 0; k < fullContributedList.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, fullContributedList.get(k).getTlc(), fullContributedList.get(k).getTlc(), session);
					SubdistrictPK sdpk = new SubdistrictPK(fullContributedList.get(k).getTlc(), fullContributedList.get(k).getTlc());
					subdistrictDAO.updateIsActive(false, sdpk, session);
					// Village impacting code
					this.saveNewVillageVersion(fullContributedList.get(k).getTlc(), sdCode, sdVersionCode, session);
					id++;
				}
				SubdistrictPK sdpk = new SubdistrictPK(sdCode, sdVersionCode);
				subdistrictDAO.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																			// detail
																			// in
																			// sub
																			// district
																			// table
				isCommited = true;
			}

			else if (fullContributedList.size() == 0 && partContributedList.size() > 0) {
				mapCd = 0;
				mapCd = this.publishMapLandRegion(sdForm, sdCode, sdVersionCode, session);
				this.saveSubDistrict(sdForm, sdCode, sdVersionCode, mapCd, session);
				this.saveHeadquarters(sdForm, headquarterCode, sdCode, sdVersionCode, session);
				// Land Region Replaced By

				this.saveReplacedBy(rByid, lrReplacedBy, sdCode, sdVersionCode, session);
				for (int k = 0; k < partContributedList.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), session);

					SubdistrictPK sdpk = new SubdistrictPK(partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc());

					// code to get new sd version
					this.saveNewSubDistrictVersion(partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), lrReplacedBy, session);
					// --------------------

					// Village impacting code
					this.saveNewVillageVersion(partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc() + 1, session);
					// --------------------

					subdistrictDAO.updateIsActive(false, sdpk, session);
					id++;
				}
				for (int i = 0; i < vFullList.size(); i++) {
					saveNewVillageVersionFullContri(vFullList.get(i).getVlc(), sdCode, sdVersionCode, session);
				}
				for (int i = 0; i < vPartList.size(); i++) {
					saveNewVillageVersionFullContri(vPartList.get(i).getVlc(), sdCode, sdVersionCode, session);
					saveNewVillageVersionPartContri(vPartList.get(i).getVlc(), session);

				}
				SubdistrictPK sdpk = new SubdistrictPK(sdCode, sdVersionCode);
				subdistrictDAO.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																			// detail
																			// in
																			// sub
																			// district
																			// table

				isCommited = true;

			} else if (fullContributedList.size() > 0 && partContributedList.size() > 0) {
				// Full Contributed
				// mapCd = this.publishMapLandRegion(sdForm, sdCode,
				// sdVersionCode, session);
				this.saveSubDistrict(sdForm, sdCode, sdVersionCode, mapCd, session);
				// this.saveHeadquarters(sdForm, headquarterCode, sdCode,
				// sdVersionCode, session);
				// Land Region Replaced By

				this.saveReplacedBy(rByid, lrReplacedBy, sdCode, sdVersionCode, session);
				for (int k = 0; k < fullContributedList.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, fullContributedList.get(k).getTlc(), fullContributedList.get(k).getTlc(), session);
					SubdistrictPK sdpk = new SubdistrictPK(fullContributedList.get(k).getTlc(), fullContributedList.get(k).getTlc());
					subdistrictDAO.updateIsActive(false, sdpk, session);
					// Village impacting code
					this.saveNewVillageVersion(fullContributedList.get(k).getTlc(), sdCode, sdVersionCode, session);
					id++;
				}
				// -------------------------------------------------------
				// Part Contributed
				for (int k = 0; k < partContributedList.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), session);

					SubdistrictPK sdpk2 = new SubdistrictPK(partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc());

					// code to get new sd version
					this.saveNewSubDistrictVersion(partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), lrReplacedBy, session);
					// --------------------

					// Village impacting code
					this.saveNewVillageVersion(partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc(), partContributedList.get(k).getTlc() + 1, session);
					// --------------------
					subdistrictDAO.updateIsActive(false, sdpk2, session);
					id++;
				}
				for (int i = 0; i < vFullList.size(); i++) {
					saveNewVillageVersionFullContri(vFullList.get(i).getVlc(), sdCode, sdVersionCode, session);
				}
				for (int i = 0; i < vPartList.size(); i++) {
					saveNewVillageVersionFullContri(vPartList.get(i).getVlc(), sdCode, sdVersionCode, session);
					saveNewVillageVersionPartContri(vPartList.get(i).getVlc(), session);

				}

				SubdistrictPK sdpk2 = new SubdistrictPK(sdCode, sdVersionCode);
				subdistrictDAO.updateLrReplaces(lrReplaces, sdpk2, session);// corresponding
																			// detail
																			// in
																			// sub
																			// district
																			// table

				isCommited = true;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			// System.out.println("::::::::::RolledBack");
			isCommited = false;
		}

		return isCommited;
	}*/

	@Override
	public List<Object> getSubDistrictFormList(SubDistrictForm sdForm) {
		List<Object> newDistrictDetail = new ArrayList<Object>();
		newDistrictDetail.add(sdForm.getSubdistrictNameEnglish());
		newDistrictDetail.add(sdForm.getSubdistrictNameLocal());
		newDistrictDetail.add(sdForm.getAliasEnglish());
		newDistrictDetail.add(sdForm.getAliasLocal());
		newDistrictDetail.add(sdForm.getCensus2011Code());
		return newDistrictDetail;

	}

	/*public boolean saveSubDistrict(SubDistrictForm sdForm, int sdCode, int sdVersion, int mapCd, Session session) throws Exception {
		int distVersion = 0;
		distVersion = this.getSubDistrictVersion(Integer.parseInt(sdForm.getDistrictCode()));
		DistrictPK dPK = new DistrictPK(Integer.parseInt(sdForm.getDistrictCode()), distVersion);
		District dist = new District();
		SubdistrictPK sdpk = new SubdistrictPK(sdCode, sdVersion);
		Subdistrict sdbean = new Subdistrict();
		dist.setDistrictPK(dPK);
		// sdbean.setSubdistrictPK(sdpk);

		sdbean.setSubdistrictNameEnglish(sdForm.getSubdistrictNameEnglish());
		sdbean.setSubdistrictNameLocal(sdForm.getSubdistrictNameLocal());
		sdbean.setAliasEnglish(sdForm.getAliasEnglish());
		sdbean.setAliasLocal(sdForm.getAliasLocal());
		if (sdForm.getCensus2011Code().length() > 0) {
			sdbean.setCensus2011Code(sdForm.getCensus2011Code());
		}
		sdbean.setMapLandregionCode(sdForm.getMapCode());
		sdbean.setSscode(sdForm.getSscode());
		sdbean.setEffectiveDate(timestamp);
		sdbean.setLastupdated(timestamp);
		sdbean.setCreatedon(timestamp);
		sdbean.setCreatedby(1010101);
		sdbean.setLastupdatedby(1010101);
		sdbean.setIsactive(true);
		sdbean.setMapLandregionCode(mapCd);
		sdbean.setDistrict(dist);
		subdistrictDAO.publishSubDistrict(sdbean, session);
		return true;
	}*/

	public int publishMapLandRegion(SubDistrictForm sdForm, int sdCode, int sdVersion, Session session) throws Exception {
		String cord = null;
		if (sdForm.getLatitude().split(",").length > 1) {
			String[] tempLati = sdForm.getLatitude().split(",");
			String[] tempLongi = sdForm.getLongitude().split(",");
			cord = tempLati[0] + "," + tempLongi[0] + ":";
			if (tempLati.length > 1) {
				for (int i = 1; i < tempLati.length; i++) {
					cord += tempLati[i] + "," + tempLongi[i] + ":";
				}
			}
			cord = cord.substring(0, cord.length() - 1);
		}
		// cord = sdForm.getLati() + ":" + sdForm.getLongi();

		MapLandRegion mapbean = new MapLandRegion();
		mapbean.setLandregionCode(sdCode);
		mapbean.setLandregionVersion(sdVersion);
		mapbean.setLandregionType('T');
		mapbean.setCoordinates(cord);
		mapbean.setImagePath(sdForm.getFileMapUpLoad());
		mapbean.setLastupdated(timestamp);
		mapbean.setEffectiveDate(timestamp);
		mapbean.setLastupdatedby(1010101);
		mapbean.setCreatedby(1010101);
		mapbean.setCreatedon(timestamp);
		mapbean.setWarningflag(false);
		mapCd = mapLandRegionDAO.configMap(mapbean, session);
		return mapCd;
	}

	@Override
	public boolean saveReplacedBy(int id, int lrReplacedBy, int sdCode, int sdVersionCode, Session session) throws Exception {
		LandregionReplacedby landregionReplacedbyBean = null;
		landregionReplacedbyBean = new LandregionReplacedby();
		landregionReplacedbyBean.setId(id);
		landregionReplacedbyBean.setLrReplacedby(lrReplacedBy);
		landregionReplacedbyBean.setEntityCode(sdCode);
		landregionReplacedbyBean.setEntityVersion(sdVersionCode);
		landregionReplacedbyBean.setEntityType('T');
		landRegionReplacedByDAO.save(landregionReplacedbyBean, session);
		return true;
	}

	@Override
	public boolean saveReplaces(int id, int lrReplaces, int sdCode, int sdVersionCode, Session session) throws Exception {
		LandregionReplaces landregionReplacesBean = null;
		landregionReplacesBean = new LandregionReplaces();
		landregionReplacesBean.setId(id);
		landregionReplacesBean.setLrReplaces(lrReplaces);
		landregionReplacesBean.setEntityCode(sdCode);
		landregionReplacesBean.setEntityVersion(sdVersionCode);
		landregionReplacesBean.setEntityType('T');
		landRegionReplacesDAO.save(landregionReplacesBean, session);
		session.flush();
		session.refresh(landregionReplacesBean);
		return true;
	}

	/*public boolean saveNewSubDistrictVersion(int subDistrictCode, int subDistrictCodeVersion, int lrReplacedBy, Session session) throws Exception {
		SubdistrictPK sdpk = null;
		Subdistrict sdbeanLocal = null;
		sdpk = new SubdistrictPK(subDistrictCode, subDistrictCodeVersion);
		sdbeanLocal = new Subdistrict();
		sdbeanLocal = subdistrictDAO.getSubDistrictDetail(sdpk, session);
		Subdistrict sdbean = new Subdistrict();
		sdpk = null;
		sdpk = new SubdistrictPK(subDistrictCode, subDistrictCodeVersion + 1);
		District dist = sdbeanLocal.getDistrict();
		// sdbean.setSubdistrictPK(sdpk);
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
		sdbean.setMapLandregionCode(sdbeanLocal.getMapLandregionCode());
		sdbean.setSscode(sdbeanLocal.getSscode());
		try {
			subdistrictDAO.publishSubDistrict(sdbean, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}
*/
	/*@Override
	public boolean saveNewVillageVersion(int subDistrictCode, int newSDCode, int newSDVersion, Session session) throws Exception {
		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
		listVillage = villageDAO.getVillageListbySubDistrictCode(subDistrictCode);
		for (int villItr = 0; villItr < listVillage.size(); villItr++) {
			VillagePK villagePK = null;
			SubdistrictPK sdpk = null;
			Subdistrict sdbean = null;

			sdpk = new SubdistrictPK(newSDCode, newSDVersion);
			sdbean = new Subdistrict();
			villagePK = new VillagePK(listVillage.get(villItr).getVlc(), listVillage.get(villItr).getVlc() + 1);
			Village villBean = new Village();

			// sdbean.setSubdistrictPK(sdpk);
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
				log.debug("Exception" + e);
			}
		}
		// deactivating the old village
		String villageInactivesql = "update village set isactive = '" + false + "' where subdistrict_code = " + subDistrictCode + " and isactive='true'";
		try {
			villageDAO.update(villageInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public boolean saveNewVillageVersionFullContri(int villCode, int newSDCode, int newSDVersion, Session session) throws Exception {
		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
		listVillage = villageDAO.getVillageDetailsModify(villCode, session);
		for (int villItr = 0; villItr < listVillage.size(); villItr++) {
			VillagePK villagePK = null;
			SubdistrictPK sdpk = null;
			Subdistrict sdbean = null;

			sdpk = new SubdistrictPK(newSDCode, newSDVersion);
			sdbean = new Subdistrict();
			villagePK = new VillagePK(listVillage.get(villItr).getVlc(), listVillage.get(villItr).getVlc() + 1);
			// sdbean.setSubdistrictPK(sdpk);

			try {
				Village village = (Village) session.load(Village.class, villagePK);
				// village.setSubdistrict(sdbean);
				village.setFlagCode(2);
				village.setIsactive(true);
				session.update(village);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		}
		return true;
	}

	@Override
	public boolean saveNewVillageVersionPartContri(int villCode, Session session) throws Exception {
		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
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
				log.debug("Exception" + e);
			}
		}
		return true;
	}
*/
	public void saveHeadquarters(SubDistrictForm sdForm, int maxHCode, int regionCode, int regionVersion, Session session) throws Exception {

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
	}

	public boolean save(SubDistrictForm sdForm) {
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
		 * xmlStorage.objectToXml(subdistricts, "c:/Ram.xml");
		 * System.out.println (
		 * "..........################...........Data Saved into Subdistrict XML"
		 * );
		 */
		return true;
	}

	// changed
	public List<Subdistrict> getSubDistrictList(int districtCode) throws Exception {
		return subdistrictDAO.getSubDistrictListbyDistrictCode(districtCode);

	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDistListbyDistCodeShift(int districtCode) {
		Session session = null;
		Query query = null;
		List<Subdistrict> subDistListbyDistCodeShift = new ArrayList<Subdistrict>();
		try {
			session = sessionFactory.openSession();
			/*
			 * query = session .createQuery(
			 * "from Subdistrict sd where sd.isactive=true and sd.district.districtPK.districtCode="
			 * + districtCode + " order by sd.subdistrictNameEnglish");
			 */
			query = session.createQuery("from Subdistrict sd where sd.isactive=true and sd.dlc=" + districtCode + " order by sd.subdistrictNameEnglish");
			subDistListbyDistCodeShift = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subDistListbyDistCodeShift;

	}

	@Override
	public List<SubDistrictForm> viewSubDistrictDetails() throws Exception {
		List<SubDistrictForm> listVillageDetails = subdistrictDAO.viewSubDistrictDetails();
		// System.out.println("List Size---" + listVillageDetails.size());
		return listVillageDetails;
	}

	/* changed for new method */
	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCode(int stateCode) {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("from District d where d.isactive='true' and d.slc=" + stateCode + "order by d.districtNameEnglish").list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public int getSubDistrictVersion(int districtCode) throws Exception {
		Session session = sessionFactory.openSession();
		int distVers = 0;
		try {
			List<District> dist = session.createQuery("from District d where isactive=true and districtCode=" + districtCode).list();
			distVers = dist.get(0).getDistrictPK().getDistrictVersion();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distVers;
	}

	@Override
	public boolean modifyAssemblyConstituencyInfo(AssemblyForm assemblyForm, HttpServletRequest request, HttpSession httpSession) throws Exception {
		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		try {
			AssemblyConstituency sdbean = null;
			sdbean = new AssemblyConstituency();

			List<AssemblyDataForm> assemblyDataForm = new ArrayList<AssemblyDataForm>();
			assemblyDataForm = assemblyForm.getListAssemblyFormDetail();

			Iterator<AssemblyDataForm> itr = assemblyDataForm.iterator();
			while (itr.hasNext()) {
				AssemblyDataForm element = (AssemblyDataForm) itr.next();
				int pcVersion = 1;
				pcVersion = subdistrictDAO.getMaxAssemblyVersion(element.getAcCode());
				if (pcVersion == 1) {
					pcVersion = pcVersion + 1;
				} else {
					pcVersion = pcVersion + 1;
				}
				AssemblyConstituencyId sdpk = new AssemblyConstituencyId(element.getAcCode(), pcVersion);

				sdbean.setId(sdpk);
				sdbean.setAcVersion(pcVersion);
				sdbean.setAcNameEnglish(element.getAcNameEnglish().trim());
				sdbean.setAcNameLocal(element.getAcNameLocal().trim());
				sdbean.setCreatedby(element.getCreatedby());
				sdbean.setCreatedon(element.getCreatedon());
				sdbean.setEciCode(element.getEciCode());
				sdbean.setEffectiveDate(element.getEffectiveDate());
				sdbean.setIsactive(true);
				sdbean.setIsdisturbed(true);
				sdbean.setLastupdated(element.getLastupdated());
				sdbean.setAcCode(element.getAcCode());
				sdbean.setId(sdpk);
				sdpk.setAcCode(element.getAcCode());
				sdpk.setAcVersion(pcVersion);
				char ParliamentConstituency = 'A';
				subdistrictDAO.ChangeParliamentConstituency(ParliamentConstituency, element.getAcCode(), element.getAcNameEnglish(), userId, element.getAcNameLocal(), element.getEciCode());
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public boolean modifyParliamentConstituencyInfo(ParliamentForm parliamentform, HttpServletRequest request, HttpSession httpSession) throws Exception {

		List<ParliamentDataForm> listSubdistrict = new ArrayList<ParliamentDataForm>();
		ParliamentConstituency sdbean = new ParliamentConstituency();
		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		try {
			listSubdistrict = parliamentform.getListParliamentFormDetail();
			for (ParliamentDataForm element : listSubdistrict) {
				int pcVersion = 1;
				pcVersion = subdistrictDAO.getMaxSubDistrictVersion(element.getPcCode());
				if (pcVersion == 1) {
					pcVersion = pcVersion + 1;
				} else {
					pcVersion = pcVersion + 1;
				}
				ParliamentConstituencyId sdpk = new ParliamentConstituencyId(element.getPcCode(), pcVersion);

				sdbean.setPcNameEnglish(element.getPcNameEnglish().trim());
				sdbean.setPcNameLocal(element.getPcNameLocal().trim());

				sdbean.setCreatedby(element.getCreatedby());
				sdbean.setCreatedon(element.getCreatedon());
				sdbean.setEciCode(element.getEciCode());
				sdbean.setEffectiveDate(element.getEffectiveDate());
				sdbean.setIsactive(true);
				sdbean.setIsdisturbed(true);
				sdbean.setLastupdated(element.getLastupdated());
				sdbean.setPcCode(element.getPcCode());
				sdbean.setPcCode(element.getPcCode());
				sdbean.setPcVersion(element.getPcVersion());
				sdbean.setId(sdpk);
				sdpk.setPcCode(element.getPcCode());
				sdpk.setPcVersion(pcVersion);
				char ParliamentConstituency = 'P';
				subdistrictDAO.ChangeParliamentConstituency(ParliamentConstituency, element.getPcCode(), element.getPcNameEnglish(), userId, element.getPcNameLocal(), element.getEciCode());
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return true;

	}

	/*@Override
	public boolean modifySubDistrictInfo(SubDistrictForm subDistrictForm, HttpServletRequest request, HttpSession httpSession) throws Exception {

		Session session1 = null;
		Transaction tx1 = null;
		

		try {
			
			Integer stateCode = null, slc = null;
			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
			if (request.getAttribute("stateCode") != null) {
				stateCode = Integer.parseInt(request.getAttribute("stateCode").toString());
			}

			slc = commonService.getSlc(stateCode);
			session1 = sessionFactory.openSession();
			tx1 = session1.beginTransaction();
			if (subDistrictForm.isCorrection() == true) {

				Subdistrict subdistrictBean = new Subdistrict();

				// VillageForm villageFm =new VillageForm();
				int subdistrictCode = subDistrictForm.getSubdistrictCode();
				List<SubdistrictDataForm> listSubdistrict = new ArrayList<SubdistrictDataForm>();
				listSubdistrict = subDistrictForm.getListSubdistrictDetails();
				for (SubdistrictDataForm element : listSubdistrict) {

					SubdistrictPK subdistrictPK = new SubdistrictPK();
					subdistrictPK.setSubdistrictCode(element.getSubdistrictCode());
					subdistrictPK.setSubdistrictVersion(element.getSubdistrictVersion());
					String longitude = "";
					String latitude = "";
					int mapCode = 0;
					subdistrictBean.setMapLandregionCode(element.getMapLandregionCode());
					String cordinate = "";
					subdistrictBean.setIsactive(true);
					if (element.getMapLandregionCode() == null) {
						mapCode = 0;
					} else {
						mapCode = element.getMapLandregionCode();
					}
					if (subDistrictForm.getLati() == null) {
						latitude = "";

					} else {
						latitude = subDistrictForm.getLati();
					}
					if (subDistrictForm.getLongi() == null) {
						latitude = "";
					} else {
						longitude = subDistrictForm.getLongi();
					}
					cordinate = longitude + "," + latitude;
					String cord1 = null;
					if (subDistrictForm.getLati().split(",").length > 0) {
						String[] tempLati = subDistrictForm.getLati().split(",");
						String[] tempLongi = subDistrictForm.getLongi().split(",");
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
						map_landRegionCode = subdistrictDAO.getMaxSubDistrictCode("select COALESCE(max(subdistrict_code),1) from subdistrict");

						if (map_landRegionCode == 0) {
							map_landRegionCode = 1;
						} else {
							map_landRegionCode = map_landRegionCode + 1;
						}
						String cord = null;
						if (subDistrictForm.getLati().split(",").length > 0) {
							String[] tempLati = subDistrictForm.getLati().split(",");
							String[] tempLongi = subDistrictForm.getLongi().split(",");
							cord = tempLati[0] + "," + tempLongi[0] + ":";
							if (tempLati.length > 1) {
								for (int i = 1; i < tempLati.length; i++) {
									cord += tempLati[i] + "," + tempLongi[i] + ":";
								}
							}
							cord = cord.substring(0, cord.length() - 1);
						}
						Timestamp time = CurrentDateTime.getCurrentTimestamp();
						MapLandRegion mapLandRegion = null;
						mapLandRegion = new MapLandRegion();
						mapLandRegion.setEffectiveDate(CurrentDateTime.getDate("2011-12-12"));
						mapLandRegion.setCreatedon(time);
						mapLandRegion.setCreatedby(100);
						mapLandRegion.setLandregionType('T');
						mapLandRegion.setLandregionVersion(element.getSubdistrictVersion());
						mapLandRegion.setCoordinates(cord);
						mapLandRegion.setImagePath(element.getMapUrl()); // condition
						mapLandRegion.setLandregionCode(element.getSubdistrictCode());
						mapLandRegion.setWarningflag(true);
						mapLandRegionDAO.saveMap(mapLandRegion, session1);
						try {
							subdistrictBean.setMapLandregionCode(map_landRegionCode);
							subdistrictDAO.modifySubDistrictInfo(subDistrictForm, cord1, subdistrictCode, map_landRegionCode, session1);
						} catch (Exception e) {
							log.debug("Exception" + e);
						}
					} else {
						subdistrictDAO.updateMapLanRegion(mapCode, cord1, element.getSubdistrictCode(), session1);
						subdistrictDAO.modifySubDistrictInfo(subDistrictForm, cord1, subdistrictCode, mapCode, session1);
					}
					govtOrderService.updateGovernmentOrder(element.getOrderNocr(), element.getOrderCodecr(), element.getOrderDatecr(), element.getOrdereffectiveDatecr(), element.getGazPubDatecr(), "update", request, session1);
					subDistrictForm.setGazPubDate(element.getGazPubDatecr());
					subDistrictForm.setOrderNo(element.getOrderNocr());
					subDistrictForm.setOrderCode(element.getOrderCodecr());
					subDistrictForm.setOrderDate(element.getOrderDatecr());
					subDistrictForm.setOrdereffectiveDate(element.getOrdereffectiveDatecr());
					List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
					
					 * ================Getting the values from Application and
					 * Setting IN AddAttachmentBeanClass==================
					 
					AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
					addAttachmentBean.setCurrentlyUploadFileList(subDistrictForm.getAttachedFilecr());// 1.List
																										// Of
																										// File
																										// To
																										// Be
																										// Upload.
					addAttachmentBean.setUploadLocation(subDistrictForm.getUploadLocationcr());// 2.Location
																								// For
																								// File
																								// Upload
																								// In
																								// File
																								// System.
					addAttachmentBean.setAllowedTotalNoOfAttachment(subDistrictForm.getAllowedNoOfAttachmentcr());// 3.Allowed
																													// Total
																													// Number
																													// Of
																													// Attachment.
					addAttachmentBean.setAllowedTotalFileSize(subDistrictForm.getAllowedTotalFileSizecr());// 4.Allowed
																											// Total
																											// File
																											// Size.
					addAttachmentBean.setAllowedIndividualFileSize(subDistrictForm.getAllowedIndividualFileSizecr());// 5.Allowed
																														// Individual
																														// File
																														// Size.
					addAttachmentBean.setFileTitle(request.getParameterValues("fileTitle"));// 6.File
																							// Title
																							// Array.
					addAttachmentBean.setDeletedFileList(request.getParameterValues("fileSize"));// 7.Deleted
																									// File
																									// Array.
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
					addAttachmentBean.setNoOFMandatoryFile(1);// 13.Number of
																// Mandatory
																// file.
					addAttachmentBean.setMimeTypeList(allowedMimeTypeList);// 12.File
																			// Mime
																			// Type
																			// List.
					AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
					attachmentHandler.validateAttachment(addAttachmentBean);
					List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
					attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
					GovernmentOrder govtOrder = saveDataInGovtOrder(subDistrictForm, session1);
					saveDataInAttachment(govtOrder, subDistrictForm, metaInfoOfToBeAttachedFileList, session1);
					// shiftService.saveDataInGovtOrderEntityWise(govtOrder,
					// subdistrictCode , 'T', session1);
					//
				}

			} else if (subDistrictForm.isCorrection() == false) {
				Subdistrict sdbean = null;
				sdbean = new Subdistrict();
				List<SubdistrictDataForm> listSubdistrict = new ArrayList<SubdistrictDataForm>();
				listSubdistrict = subDistrictForm.getListSubdistrictDetails();
				Iterator<SubdistrictDataForm> itr = listSubdistrict.iterator();
				while (itr.hasNext()) {
					SubdistrictDataForm element = (SubdistrictDataForm) itr.next();
					int subdistrictVersion = 1;
					subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(element.getSubdistrictCode());
					if (subdistrictVersion == 1) {
						subdistrictVersion = subdistrictVersion + 1;
					} else {
						subdistrictVersion = subdistrictVersion + 1;
					}
					SubdistrictPK sdpk = new SubdistrictPK(element.getSubdistrictCode(), subdistrictVersion);
					// sdbean.setSubdistrictPK(sdpk);
					sdbean.getTlc();

					District dist = new District();
					sdbean.getTlc();
					if (element.getSubdistrictNameEnglishch() == null) {
						sdbean.setSubdistrictNameEnglish(element.getSubdistrictNameEnglishch());
					} else {
						sdbean.setSubdistrictNameEnglish(element.getSubdistrictNameEnglishch().trim());
					}
					if (element.getSubdistrictNameLocalch() == null) {
						sdbean.setSubdistrictNameLocal(element.getSubdistrictNameLocalch());
					} else {
						sdbean.setSubdistrictNameLocal(element.getSubdistrictNameLocalch().trim());
					}
					if (element.getAliasEnglishch() == null) {
						sdbean.setAliasEnglish(element.getAliasEnglishch());
					} else {
						sdbean.setAliasEnglish(element.getAliasEnglishch().trim());
					}
					if (element.getAliasLocalch() == null) {
						sdbean.setAliasLocal(element.getAliasLocalch());
					} else {
						sdbean.setAliasLocal(element.getAliasLocalch().trim());
					}

					sdbean.setCensus2011Code(element.getCensus2011Code());
					sdbean.setMapLandregionCode(element.getMapLandregionCode());
					sdbean.setSscode(element.getSscode());
					sdbean.setEffectiveDate(timestamp);
					sdbean.setLastupdated(timestamp);
					sdbean.setCreatedon(timestamp);
					sdbean.setCreatedby(1010101);
					sdbean.setLastupdatedby(1010101);
					sdbean.setIsactive(true);
					sdbean.setMapLandregionCode(mapCd);
					sdbean.setDlc(element.getDistrict_code());
					sdbean.setDlc(element.getDistrict_version());
					sdbean.setDistrict(dist);
					DistrictPK dsPK = new DistrictPK(element.getDistrict_code(), element.getDistrict_version());
					dist.setDistrictPK(dsPK);
					District sd = null;
					sd = new District();
					sd.setDistrictPK(dsPK);
					sdbean.setDistrict(sd);
					try {

						sdpk.setSubdistrictCode(element.getSubdistrictCode());
						sdpk.setSubdistrictVersion(subdistrictVersion);
						String subdistrictCode = Integer.toString(element.getSubdistrictCode());

						subdistrictDAO.ChangeSubDistrict(element.getSubdistrictCode(), element.getSubdistrictNameEnglish(), element.getSubdistrictNameEnglishch(), userId, element.getSubdistrictNameLocalch(), element.getAliasEnglishch(),
								element.getAliasLocalch(), session1, element.getOrderNo(), element.getOrderDate(), element.getEffectiveDate(), element.getOrderPath(), element.getGazPubDate(), slc);
						List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
						
						 * ================Getting the values from Application
						 * and Setting IN
						 * AddAttachmentBeanClass==================
						 

						AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
						addAttachmentBean.setCurrentlyUploadFileList(subDistrictForm.getAttachedFile());// 1.List
																										// Of
																										// File
																										// To
																										// Be
																										// Upload.
						addAttachmentBean.setUploadLocation(subDistrictForm.getUploadLocation());// 2.Location
																									// For
																									// File
																									// Upload
																									// In
																									// File
																									// System.
						addAttachmentBean.setAllowedTotalNoOfAttachment(subDistrictForm.getAllowedNoOfAttachment());// 3.Allowed
																													// Total
																													// Number
																													// Of
																													// Attachment.
						addAttachmentBean.setAllowedTotalFileSize(subDistrictForm.getAllowedTotalFileSize());// 4.Allowed
																												// Total
																												// File
																												// Size.
						addAttachmentBean.setAllowedIndividualFileSize(subDistrictForm.getAllowedIndividualFileSize());// 5.Allowed
																														// Individual
																														// File
																														// Size.
						addAttachmentBean.setFileTitle(request.getParameterValues("fileTitle"));// 6.File
																								// Title
																								// Array.
						addAttachmentBean.setDeletedFileList(request.getParameterValues("fileSize"));// 7.Deleted
																										// File
																										// Array.
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
						addAttachmentBean.setMimeTypeList(allowedMimeTypeList);// 12.File
																				// Mime
																				// Type
																				// List.
						AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
						attachmentHandler.validateAttachment(addAttachmentBean);
						List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
						attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
						GovernmentOrder govtOrder = saveDataInGovtOrder(subDistrictForm, session1);
						saveDataInAttachment(govtOrder, subDistrictForm, metaInfoOfToBeAttachedFileList, session1);
						shiftService.saveDataInGovtOrderEntityWise(govtOrder, subdistrictCode, 'T', session1);
					} catch (Exception e) {

						log.debug("Exception" + e);
					}
				}
			}
			tx1.commit();
		} catch (Exception e) {

			log.debug("Exception" + e);
			if (tx != null) {
				tx1.rollback();
			}
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
		}
		return true;

	}*/

	public List<SubdistrictDataForm> getSubdistrictDetailsModify(int subdistrictCode) throws Exception {
		SubdistrictDataForm subdistrictDataForm = new SubdistrictDataForm();
		List<SubdistrictDataForm> listSubdistrictDetails = new ArrayList<SubdistrictDataForm>();
		Session session1 = sessionFactory.openSession();
		try {

			List<Subdistrict> listSubdistrictDetail = subdistrictDAO.getSubdistrictDetailsModify(subdistrictCode, session1);
			Integer subdistrictversion = subdistrictDAO.getMaxSubDistrictVersion(subdistrictCode);

			
			if (!listSubdistrictDetail.isEmpty()) {
				Subdistrict element = listSubdistrictDetail.get(0);

				
				List<GetGovernmentOrderDetail> govtOrderValue = villageDAO.GovOrderDetail('T', subdistrictCode, subdistrictversion, element.getMinorVersion(), session1);
				subdistrictDAO.GovOrderDetail('T', subdistrictCode, subdistrictversion);

				if (!govtOrderValue.isEmpty()) {
					GetGovernmentOrderDetail governmentOrder = govtOrderValue.get(0);
		
					subdistrictDataForm.setOrderCodecr(governmentOrder.getOrderCode());
					if (governmentOrder.getOrderNo() == null) {
						subdistrictDataForm.setOrderNocr(governmentOrder.getOrderNo());
					} else {
						subdistrictDataForm.setOrderNocr(governmentOrder.getOrderNo().trim());
						subdistrictDataForm.setOrderNo(governmentOrder.getOrderNo().trim());
					}
					subdistrictDataForm.setOrderDatecr(governmentOrder.getOrderDate());
					subdistrictDataForm.setGazPubDatecr(governmentOrder.getGazPubDate());
				}

				
				
				Integer regionCode = element.getTlc();
				List<Headquarters> headquartersDetails = this.getHeadquterDetails(regionCode, "T");

				if (!headquartersDetails.isEmpty()) {
					Headquarters headquarters = headquartersDetails.get(0);
					subdistrictDataForm.setHeadquarterName(headquarters.getHeadquarterNameEnglish());
					subdistrictDataForm.setHeadquarterNameLocal(headquarters.getHeadquarterLocalName());
					subdistrictDataForm.setHeadquarterCode(headquarters.getHeadquarterCode());
				}

				subdistrictDataForm.setTlc(element.getTlc());
				subdistrictDataForm.setAliasEnglish(element.getAliasEnglish());
				subdistrictDataForm.setAliasEnglishch(element.getAliasEnglish());
				subdistrictDataForm.setAliasLocal(element.getAliasLocal());
				subdistrictDataForm.setAliasLocalch(element.getAliasLocal());
				subdistrictDataForm.setCensus2001Code(element.getCensus2001Code());
				if (element.getSscode() != null)
					subdistrictDataForm.setSscode(element.getSscode().trim());
				else
					subdistrictDataForm.setSscode(element.getSscode());
				subdistrictDataForm.setSubdistrictCode(element.getTlc());
				subdistrictDataForm.setSubdistrictVersion(element.getSubdistrictVersion());
				subdistrictDataForm.setMinorVersion(element.getMinorVersion());
				if (element.getSubdistrictNameEnglish() == null)
					subdistrictDataForm.setSubdistrictNameEnglish(element.getSubdistrictNameEnglish());
				else
					subdistrictDataForm.setSubdistrictNameEnglish(element.getSubdistrictNameEnglish().trim());
				subdistrictDataForm.setSubdistrictNameLocal(element.getSubdistrictNameLocal());
				if (element.getSubdistrictNameEnglish() == null)
					subdistrictDataForm.setSubdistrictNameEnglishch(element.getSubdistrictNameEnglish());
				else
					subdistrictDataForm.setSubdistrictNameEnglishch(element.getSubdistrictNameEnglish().trim());
				subdistrictDataForm.setSubdistrictNameLocalch(element.getSubdistrictNameLocal());
				subdistrictDataForm.setCensus2011Code(element.getCensus2011Code());
				subdistrictDataForm.setLrReplacedby(element.getLrReplacedby());
				subdistrictDataForm.setLrReplaces(element.getLrReplaces()); // Check
				//subdistrictDataForm.setMapLandregionCode(element.getMapLandregionCode());
				subdistrictDataForm.setDistrict_code(element.getDlc());
				subdistrictDataForm.setDistrict_version(element.getDlc());

				String Cordinate = null;
				if (element.getCoordinates() != null) {
					if (!element.getCoordinates().trim().equals("")) {
						Cordinate = element.getCoordinates();
					}
				}
				subdistrictDataForm.setCordinate(Cordinate);
				subdistrictDataForm.setWarningFlag(element.getWarningFlag());
				subdistrictDataForm.setOrdereffectiveDatecr(element.getEffectiveDate());
				subdistrictDataForm.setOrdereffectiveDate(element.getEffectiveDate());
			}

			listSubdistrictDetails.add(subdistrictDataForm);
			SubDistrictForm vform = new SubDistrictForm();
			vform.setListSubdistrictDetails(listSubdistrictDetails);

		} catch (Exception e) {
			throw new Exception(e);

		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.clear();
				session1.close();
			}
		}
		return listSubdistrictDetails;
	}

	@Override
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode, Session session) throws Exception {
		List<MapLandRegion> mapDetail = subdistrictDAO.getMapDetailsModify(mapLandregionCode, session);

		return mapDetail;
	}

	public List<Subdistrict> getSubDistrictListBySubDistCode(int subdistrictCode) throws Exception {
		return subdistrictDAO.getSubDistrictListby(subdistrictCode);
	}

	public List<Subdistrict> getSubDistrictViewList(String query) throws Exception {
		return subdistrictDAO.getSubDistrictViewList(query);
	}

	@Override
	public Subdistrict getById(Integer subDistrictCode) throws Exception {

		return null;
	}

	@Override
	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws Exception {

		List<GovernmentOrder> govOrderDetail = subdistrictDAO.getGovermentOrderDetail(orderCode);

		return govOrderDetail;
	}

	@Override
	public List<Subdistrict> getTargetSubDistrictListbySubDistrict(int subdistrictCode, int districtCode) throws Exception {
		return subdistrictDAO.getTargetSubDistrictList(subdistrictCode, districtCode);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Village> getVillageDetailforReorganize(String listVillCode) throws Exception {
		List<Village> villList = new ArrayList<Village>();
		String sql = "SELECT  village.village_code, village.village_name_english, subdistrict.subdistrict_code, subdistrict.subdistrict_name_english FROM public.village, public.subdistrict "
				+ "WHERE subdistrict.subdistrict_code = village.subdistrict_code AND village.isactive = TRUE AND " + "subdistrict.isactive = TRUE AND village.village_code IN (" + listVillCode + ")";

		Session session = sessionFactory.openSession();
		List<Object[]> temp = session.createSQLQuery(sql).list();

		for (int k = 0; k < temp.size(); k++) {
			Village vill = new Village();
			Object vill1[] = temp.get(k);
			vill.getVlc();
			vill.setVillageNameEnglish(((String) vill1[1]) + " (" + ((String) vill1[3]) + ")");
			vill.getVlc();
			vill.setVillageNameLocal((String) vill1[3]); // sub district name
															// temporarily
															// holding in
															// village name
															// local
			villList.add(vill);
		}

		if (session != null && session.isOpen()) {
			session.close();
		}
		return villList;
	}

	/*@Override
	public boolean reOrganize(SubDistrictForm SDForm, List<VillageForm> listNewVillForm, List<SubDistrictForm> listSDForm) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			isMethod1Commited = this.publish(SDForm, session);
			tx.commit();
			ReleaseResources.doReleaseResources(session);

			// while(isMethod1Commited==true){
			for (int i = 0; i < listNewVillForm.size(); i++) {
				listNewVillForm.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
				listNewVillForm.get(i).setCreateFromExistingVillages(true);
				isMethod2Commited = villageService.addVillage(listNewVillForm.get(i));
			}
			// }
			if (listSDForm.size() > 0) {
				session = null;
				tx = null;
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				for (int i = 0; i < listSDForm.size(); i++) {
					isMethod3Commited = this.renameContributedSD(listSDForm.get(i), session);
				}
				tx.commit();
				ReleaseResources.doReleaseResources(session);
			}
			if (isMethod1Commited == true && isMethod2Commited == true) {
				// tx.commit();
				isCommited = true;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			// tx.rollback();
			isCommited = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isCommited;
	}*/

	/*@Override
	public boolean reOrganize(SubDistrictForm SDForm, List<VillageForm> listModifyVillForm, HttpServletRequest request, List<SubDistrictForm> listSDForm, HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		Session session = null;
		Transaction tx = null;

		
		 * session=sessionFactory.openSession(); tx= session.beginTransaction();
		 

		try {
			for (int i = 0; i < listModifyVillForm.size(); i++) {
				isMethod2Commited = villageService.modifyVillageInfo((listModifyVillForm.get(i)), request, httpSession);
			}
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			isMethod1Commited = this.publish(SDForm, session);
			tx.commit();
			ReleaseResources.doReleaseResources(session);

			if (listSDForm.size() > 0) {
				session = null;
				tx = null;
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				for (int i = 0; i < listSDForm.size(); i++) {
					isMethod3Commited = this.renameContributedSD(listSDForm.get(i), session);
				}
				tx.commit();
				ReleaseResources.doReleaseResources(session);
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
	}*/

	/*@Override
	public boolean reOrganize(SubDistrictForm SDForm, List<VillageForm> listNewVillForm, List<VillageForm> listModifyVillForm, HttpServletRequest request, List<SubDistrictForm> listSDForm, HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		Session session = null;
		Transaction tx = null;

		
		 * session=sessionFactory.openSession(); tx= session.beginTransaction();
		 

		try {
			for (int i = 0; i < listModifyVillForm.size(); i++) {
				isMethod2Commited = villageService.modifyVillageInfo((listModifyVillForm.get(i)), request, httpSession);
			}
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			isMethod1Commited = this.publish(SDForm, session);
			tx.commit();
			ReleaseResources.doReleaseResources(session);

			// while(isMethod1Commited==true){
			for (int i = 0; i < listNewVillForm.size(); i++) {
				listNewVillForm.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
				listNewVillForm.get(i).setCreateFromExistingVillages(true);
				isMethod3Commited = villageService.addVillage(listNewVillForm.get(i));
			}
			// }
			if (listSDForm.size() > 0) {
				session = null;
				tx = null;
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				for (int i = 0; i < listSDForm.size(); i++) {
					isMethod3Commited = this.renameContributedSD(listSDForm.get(i), session);
				}
				tx.commit();
				ReleaseResources.doReleaseResources(session);
			}
			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true) {
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
	public List<SubdistrictHistory> getSubDistrictHistoryDetail(char subdistrictNameEnglish, int subdistrictCode) throws Exception {
		List<SubdistrictHistory> SubdistrictHistory = new ArrayList<SubdistrictHistory>();
		SubdistrictHistory = subdistrictDAO.getSubDistHistoryDetailShift(subdistrictNameEnglish, subdistrictCode);
		return SubdistrictHistory;

	}

	@Override
	public boolean renameContributedSD(SubDistrictForm SDForm, Session session) throws Exception {
		int sdCode = 0;
		String sdName = null;
		String sdNameLocal = null;

		sdCode = SDForm.getSubdistrictCode();
		sdName = SDForm.getSubdistrictNameEnglish();
		sdNameLocal = SDForm.getSubdistrictNameLocal();

		try {
			session.createSQLQuery("update subdistrict set subdistrict_name_english='" + sdName + "', subdistrict_name_local='" + sdNameLocal + "' where subdistrict_code=" + sdCode + " and isactive=true").executeUpdate();
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return false;
		}
	}

	/*@Override
	public boolean publishSDandRenamedContriSD(SubDistrictForm SDForm, List<SubDistrictForm> listContriSDForm) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {
			isMethod1Commited = this.publish(SDForm, session);
			for (int i = 0; i < listContriSDForm.size(); i++) {
				isMethod2Commited = this.renameContributedSD(listContriSDForm.get(i), session);
			}
			if (isMethod1Commited == true && isMethod2Commited == true) {
				tx.commit();
				isCommited = true;
			}
		} catch (Exception e) {
			tx.rollback();
			isCommited = false;
		} finally {
			ReleaseResources.doReleaseResources(session);
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isCommited;
	}*/

	/*@Override
	public boolean publishSubDistrict(SubDistrictForm sdForm) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {
			isMethod1Commited = this.publish(sdForm, session);
			tx.commit();
			isCommited = true;
		} catch (Exception e) {
			tx.rollback();
			isCommited = false;
		} finally {
			ReleaseResources.doReleaseResources(session);
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isCommited;
	}*/

	@Override
	public boolean invalidateSubDistrict(String subdistrictCodes, int subdistrictCode, long roleCode, String villageCodes) throws Exception {
		// TODO invalidateSubDistrict
		try {
			subdistrictDAO.invalidateFunctionCall(subdistrictCodes, subdistrictCode, roleCode, villageCodes);
			sdVillMap.clear();

		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}

		return true;
	}

	@Override
	public String invalidateSubDistrictCall(String sourceSubdistCode, int userId, String keyAppend, Date effectiveDate, String orderNo, Date orderDate, Date gazPubDate) throws Exception {
		// TODO invalidateSubDistrict
		String MaxCode = null;
		try {
			MaxCode = subdistrictDAO.invalidateFunctionCallSubDistrict(sourceSubdistCode, userId, keyAppend, effectiveDate, orderNo, orderDate, orderDate);
			sdVillMap.clear();
			/* Modified by kirandeep for Invalidate Subdistrict on 16/03/2015 */

		} catch (BaseAppException e) {
			log.debug("Exception" + e);
			throw new BaseAppException(e.getMessage());
		}

		return MaxCode;
	}

	@Override
	public boolean invalidateLoop(String subdistrictCode, String VillagesCodes, HttpSession httpSession) throws Exception {

		try {
			sdVillMap.put(subdistrictCode, VillagesCodes);
			if (!sdVillMap.isEmpty()) {
				httpSession.setAttribute("sdVillMap", sdVillMap);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	@Override
	public String getInvalidateVillageSubdistricts() throws Exception {
		String subdistrictList;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			InvalidateVillageForm invalidateVillageForm = (InvalidateVillageForm) unmarshaller.unmarshal(new File("../invalidatevillage.xml"));

			subdistrictList = invalidateVillageForm.getSourceSubdistrict().getSubdistricts().get(0).getSubdistrict().get(0).getSubdistrictDetails().getNameEnglish();

			if (invalidateVillageForm.getDestinationSubdistrict() != null) {
				subdistrictList += "," + invalidateVillageForm.getDestinationSubdistrict().getSubdistrictDetails().getNameEnglish();
			}
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return null;
		}
		return subdistrictList;
	}

	@Override
	public String getInvalidateVillages() throws Exception {
		String villageList = "";

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			InvalidateVillageForm invalidateVillageForm = (InvalidateVillageForm) unmarshaller.unmarshal(new File("../invalidatevillage.xml"));

			for (int i = 0; i < invalidateVillageForm.getSourceSubdistrict().getSubdistricts().get(0).getSubdistrict().get(0).getVillages().getVillage().size(); i++)
				villageList += invalidateVillageForm.getSourceSubdistrict().getSubdistricts().get(0).getSubdistrict().get(0).getVillages().getVillage().get(i).getNameEnglish() + ",";

			if (invalidateVillageForm.getDestinationSubdistrict() != null) {
				villageList = villageList.substring(0, villageList.length() - 1);
				villageList += ":" + invalidateVillageForm.getDestinationSubdistrict().getVillages().getVillage().get(0).getNameEnglish();
			}
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return null;
		}

		return villageList;
	}

	@Override
	public void saveAsDraftInvalidateSubdistrict(SubDistrictForm form, HttpSession httpSession) throws Exception {
		try {
			InvalidateSubdistrictForm invalidateSubdistrictForm = new InvalidateSubdistrictForm();
			in.nic.pes.lgd.ws.District district = new in.nic.pes.lgd.ws.District();
			in.nic.pes.lgd.ws.Subdistrict subdistrict = new in.nic.pes.lgd.ws.Subdistrict();
			Subdistricts subdistricts = new Subdistricts();
			LandDetails landDetails = new LandDetails();
			landDetails.setNameEnglish(form.getDistrictNameEnglish());
			district.setDistrictDetails(landDetails);
			landDetails = new LandDetails();
			landDetails.setNameEnglish(form.getSubdistrictNameEnglish());
			subdistrict.setSubdistrictDetails(landDetails);
			subdistricts.getSubdistrict().add(subdistrict);
			district.getSubdistricts().add(subdistricts);
			invalidateSubdistrictForm.setSourceSubdistrict(district);

			subdistricts = new Subdistricts();

			if (httpSession.getAttribute("sdVillMap") != null) {
				Map<String, String> sdVillMap = (Map<String, String>) httpSession.getAttribute("sdVillMap");
				Set keys = sdVillMap.keySet();
				String key = "";

				for (Iterator i = keys.iterator(); i.hasNext();) {
					key = (String) i.next();
					String value = (String) sdVillMap.get(key);

					Villages villages = new Villages();
					subdistrict = new in.nic.pes.lgd.ws.Subdistrict();
					landDetails = new LandDetails();
					landDetails.setNameEnglish(key);
					subdistrict.setSubdistrictDetails(landDetails);

					String[] tempVillages = value.split(",");
					for (int intloops = 0; intloops < tempVillages.length; intloops++) {
						landDetails = new LandDetails();
						landDetails.setNameEnglish(tempVillages[intloops]);
						villages.getVillage().add(landDetails);
					}
					subdistrict.setVillages(villages);
					subdistricts.getSubdistrict().add(subdistrict);
				}
			} else {
				subdistrict = new in.nic.pes.lgd.ws.Subdistrict();
				landDetails = new LandDetails();
				landDetails.setNameEnglish(form.getTargetSubdistrictYes());
				subdistrict.setSubdistrictDetails(landDetails);
				subdistricts.getSubdistrict().add(subdistrict);
			}

			invalidateSubdistrictForm.setDestinationSubdistricts(subdistricts);

			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(invalidateSubdistrictForm, new File("../invalidateSubdistrict.xml"));

			sdVillMap.clear();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}

	@Override
	public InvalidateSubdistrictForm getInvalidateDraftSubdistrict(String filePath) throws Exception {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			InvalidateSubdistrictForm invalidateSubdistrictForm = (InvalidateSubdistrictForm) unmarshaller.unmarshal(new File(filePath));

			return invalidateSubdistrictForm;
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String getInvalidateSubdistricts() throws Exception {
		try {
			// System.out.println("INSIDE DWR");

			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			InvalidateSubdistrictForm invalidateSubdistrictForm = (InvalidateSubdistrictForm) unmarshaller.unmarshal(new File("../invalidateSubdistrict.xml"));

			String invalidatedSubdistrict = invalidateSubdistrictForm.getSourceSubdistrict().getSubdistricts().get(0).getSubdistrict().get(0).getSubdistrictDetails().getNameEnglish();
			String invalidatingSubdistrict;
			if (invalidateSubdistrictForm.getDestinationSubdistricts().getSubdistrict().get(0).getVillages() == null) {
				invalidatingSubdistrict = invalidateSubdistrictForm.getDestinationSubdistricts().getSubdistrict().get(0).getSubdistrictDetails().getNameEnglish();
				return invalidatedSubdistrict + "::" + invalidatingSubdistrict;
			} else {
				String invalidatingSubdistricts = "";
				for (int i = 0; i < invalidateSubdistrictForm.getDestinationSubdistricts().getSubdistrict().size(); i++) {
					String strTempSubdistrict = invalidateSubdistrictForm.getDestinationSubdistricts().getSubdistrict().get(i).getSubdistrictDetails().getNameEnglish();
					String strTempVillageList = "";
					for (int j = 0; j < invalidateSubdistrictForm.getDestinationSubdistricts().getSubdistrict().get(i).getVillages().getVillage().size(); j++) {
						strTempVillageList += invalidateSubdistrictForm.getDestinationSubdistricts().getSubdistrict().get(i).getVillages().getVillage().get(j).getNameEnglish() + "#";
					}
					strTempVillageList = strTempVillageList.substring(0, strTempVillageList.length() - 1);
					invalidatingSubdistricts += strTempSubdistrict + "%" + strTempVillageList + "%";
				}
				return invalidatedSubdistrict + "::" + invalidatingSubdistricts;
			}
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void clearAll() {
		sdVillMap.clear();
	}

	@Override
	public List<Subdistrict> getSubDistrictListbyDistrictCode(int districtCode, String subdistrictList) throws Exception {
		return subdistrictDAO.getSubDistrictListbyDistrictCode(districtCode, subdistrictList);
	}

	@Override
	public Subdistrict getSingleSubdistrictDetailsMaxVersion(int subdistrictCode) throws Exception {
		Subdistrict subdistrictBean = null;
		subdistrictBean = new Subdistrict();
		int subdistrictVersion = 0;
		subdistrictVersion = subdistrictDAO.getMaxSubdistrictVersion(subdistrictCode);
		subdistrictBean = subdistrictDAO.getSubdistrictDetailsMaxVersionByCode(subdistrictCode, subdistrictVersion);
		return subdistrictBean;

	}

	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictName(int districtCode) throws Exception {
		return subdistrictDAO.getSubDistrictListbyDistrictCodewithDistrictName(districtCode);
	}

	@Override
	public List<Subdistrict> getSubDistrictListView(String sQuery) throws Exception {
		return subdistrictDAO.getSubDistrictList(sQuery);
	}

	public GovernmentOrder saveGovernmentOrder(ParliamentForm Parliamentorm, Session session) throws Exception {

		GovernmentOrder governmentOrder = new GovernmentOrder();
		try {
			governmentOrder.setOrderDate(new Date());
			governmentOrder.setEffectiveDate(new Date());
			governmentOrder.setGazPubDate(new Date());
			governmentOrder.setCreatedon(new Date());
			governmentOrder.setDescription("LGD DETAILS");
			governmentOrder.setIssuedBy("GOVERNOR");
			governmentOrder.setCreatedby(createdBy);
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby(createdBy);
			governmentOrder.setLevel("S");
			governmentOrder.setOrderNo(Parliamentorm.getOrderNo());
			governmentOrder.setStatus('A');
			governmentOrder.setUserId(userId);
			localGovtBodyDAO.saveOrderDetails(governmentOrder, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return governmentOrder;
	}

	public GovernmentOrder saveDataInGovtOrder(SubDistrictForm govtForm, Session session) throws Exception {

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
			governmentOrder.setLevel("S");
			governmentOrder.setOrderNo(govtForm.getOrderNo());
			governmentOrder.setStatus('A');
			governmentOrder.setUserId(userId);
			localGovtBodyDAO.saveOrderDetails(governmentOrder, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return governmentOrder;
	}

	public void saveDataInAttachment(GovernmentOrder govtOrder, SubDistrictForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws Exception {

		Attachment attachment = null;
		try {
			if (attachedList != null) {
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
					if (session.contains(attachment)) {
						session.evict(attachment);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getSubDisListbyDistCodeShift(int districtCode) throws Exception {
		Session session = null;
		Query query = null;
		List<Subdistrict> subdistrictlist = new ArrayList<Subdistrict>();
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Subdistrict sd where sd.isactive=true and sd.district.districtPK.districtCode=" + districtCode + " order by sd.subdistrictNameEnglish");
			subdistrictlist = query.list();
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrictlist;
	}

	public List<VillageDataForm> getVillageDetail(int subdistrictCode) throws Exception {
		Session session1 = null;
		Transaction tx1 = null;
		session1 = sessionFactory.openSession();
		tx1 = session1.beginTransaction();
		List<Village> listVillageDetail = villageDAO.getVillageDetailsModify(subdistrictCode, session1);
		VillageDataForm villageDataForm = new VillageDataForm();
		List<VillageDataForm> listVillageDetails = new ArrayList<VillageDataForm>();
		int mapCode = 0;
		try {
			// government Order
			
			int villageVersion,minorVersion;
			if(listVillageDetail!=null && !listVillageDetail.isEmpty()) {
				Village obj=listVillageDetail.get(0);
				villageVersion=obj.getVillageVersion();
				minorVersion=obj.getMinorVersion();
			}else {
				villageVersion = villageDAO.getVillageVersion(subdistrictCode, session1);
				minorVersion=1;
			}
			
			List<GetGovernmentOrderDetail> govtOrderValue = villageDAO.GovOrderDetail('V', subdistrictCode, villageVersion,minorVersion, session1);
			Iterator<GetGovernmentOrderDetail> itrGovt = govtOrderValue.iterator();
			while (itrGovt.hasNext()) {
				GetGovernmentOrderDetail governmentOrder = (GetGovernmentOrderDetail) itrGovt.next();
				governmentOrder.getOrderNo();
				governmentOrder.getOrderDate();
				governmentOrder.getEffectiveDate();
				governmentOrder.getGazPubDate();

				villageDataForm.setOrderCodecr(governmentOrder.getOrderCode());
				if (governmentOrder.getOrderNo() == null) {
					villageDataForm.setOrderNocr(governmentOrder.getOrderNo());
				} else {
					villageDataForm.setOrderNocr(governmentOrder.getOrderNo().trim());
				}

				villageDataForm.setOrderDatecr(governmentOrder.getOrderDate());
				villageDataForm.setOrdereffectiveDatecr(governmentOrder.getEffectiveDate());
				villageDataForm.setGazPubDatecr(governmentOrder.getGazPubDate());
				villageDataForm.setOrderNo(governmentOrder.getOrderNo());
				villageDataForm.setOrderDate(governmentOrder.getOrderDate());
				villageDataForm.setOrdereffectiveDate(governmentOrder.getEffectiveDate());
				villageDataForm.setGazPubDate(governmentOrder.getGazPubDate());

			}
			Iterator<Village> itr = listVillageDetail.iterator();
			int tempCode = 0;
			while (itr.hasNext()) {
				Village element = (Village) itr.next();
				if (tempCode != element.getVlc()) {
					String cordinate = "";
					try {

						if (element.getMapCode() == null) {
							mapCode = 0;
						} else {
							mapCode = element.getMapCode();
						}
						if (mapCode == 0) {
							log.debug("Exception" + mapCode);
						} else {
							List<MapLandRegion> listCordinate = villageDAO.getMapDetailsModify(mapCode, session1);

							Iterator<MapLandRegion> itr1 = listCordinate.iterator();
							while (itr1.hasNext()) {
								MapLandRegion mapLandRegion = (MapLandRegion) itr1.next();
								cordinate = mapLandRegion.getCoordinates();
								int mapCode1 = mapLandRegion.getLandregionCode();
								int version = mapLandRegion.getLandregionVersion();
								villageDataForm.setLati(cordinate);
							}

						}

					} catch (Exception e) {
						log.debug("Exception" + e);
					}
					villageDataForm.setAliasEnglish(element.getAliasEnglish());
					villageDataForm.setAliasEnglishCh(element.getAliasEnglish());
					villageDataForm.setAliasLocal(element.getAliasLocal());
					villageDataForm.setAliasLocalCh(element.getAliasLocal());
					villageDataForm.setCensus2001Code(element.getCensus2001Code());
					if (element.getSscode() == null) {
						villageDataForm.setSscode(element.getSscode());
					} else {
						villageDataForm.setSscode(element.getSscode().trim());
					}

					villageDataForm.setVillageCode(element.getVlc());
					villageDataForm.setVillage_version(element.getVlc());
					villageDataForm.setVillageNameEnglish(element.getVillageNameEnglish());
					villageDataForm.setVillageNameLocal(element.getVillageNameLocal());
					villageDataForm.setVillageNameEnglishCh(element.getVillageNameEnglish());
					villageDataForm.setVillageNameLocalCh(element.getVillageNameLocal());
					villageDataForm.setCensus2011Code(element.getCensus2011Code());
					villageDataForm.setCordinate(cordinate);
					villageDataForm.setLrReplacedby(element.getLrReplacedby());
					villageDataForm.setLrReplaces(element.getLrReplaces());
					villageDataForm.setMapCode(element.getMapCode());
					villageDataForm.setSubdistrictCode(element.getTlc());
					villageDataForm.setSubdistrictVersion(element.getTlc());
					listVillageDetails.add(villageDataForm);
					tempCode = element.getVlc();
					VillageForm vform = new VillageForm();
					vform.setListVillageDetails(listVillageDetails);
				}
			}
			tx1.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			tx1.rollback();
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
				
			}
		}

		return listVillageDetails;
	}

	public List<AssemblyDataForm> getAssemblyConstituencyDetail(int ParliamentCode) throws Exception {
		AssemblyDataForm assemblyDataForm = new AssemblyDataForm();
		List<AssemblyConstituency> listAssemblyDetail = subdistrictDAO.getAssemblyDetailsModify(ParliamentCode);
		// int
		// parliamentConstituencyversion=subdistrictDAO.getMaxParliamentConstituencyVersion(ParliamentCode);

		List<GetGovernmentOrderDetail> govtOrderValue = subdistrictDAO.GovOrderDetail('A', ParliamentCode, 1);
		Iterator<GetGovernmentOrderDetail> itrGovt = govtOrderValue.iterator();
		while (itrGovt.hasNext()) {
			GetGovernmentOrderDetail governmentOrder = (GetGovernmentOrderDetail) itrGovt.next();
			governmentOrder.getOrderNo();
			governmentOrder.getOrderDate();
			governmentOrder.getEffectiveDate();
			governmentOrder.getGazPubDate();
			assemblyDataForm.setOrderCode(governmentOrder.getOrderCode());
			assemblyDataForm.setOrderNo(governmentOrder.getOrderNo().trim());
			// assemblyDataForm.setOrderDate(governmentOrder.getOrderDate());
			// assemblyDataForm.setEffectiveDate(governmentOrder.getEffectiveDate());
			// assemblyDataForm.setGazPubDate(governmentOrder.getGazPubDate());
		}
		int mapCode = 0;
		int tempCode = 0;

		List<AssemblyDataForm> listAssemblyConstituencyDetails = new ArrayList<AssemblyDataForm>();
		Iterator<AssemblyConstituency> itr = listAssemblyDetail.iterator();
		while (itr.hasNext()) {
			AssemblyConstituency element = (AssemblyConstituency) itr.next();
			if (tempCode != element.getId().getAcCode()) {
				String cordinate = "";
				try {
					if (element.getMapConstituencyCode() == null) {
						mapCode = 0;
					} else {
						mapCode = element.getMapConstituencyCode();
					}
					if (mapCode == 0) {
						log.debug("Exception" + mapCode);
					} else {
						List<MapLandRegion> listCordinate = subdistrictDAO.getMapDetailsModify(mapCode);
						Iterator<MapLandRegion> itr1 = listCordinate.iterator();
						while (itr1.hasNext()) {
							MapLandRegion mapLandRegion = (MapLandRegion) itr1.next();
							cordinate = mapLandRegion.getCoordinates();
							int mapCode1 = mapLandRegion.getLandregionCode();
							int version = mapLandRegion.getLandregionVersion();
							// System.out.println(cordinate + "<>");
							assemblyDataForm.setLati(cordinate);
						}
					}

				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				assemblyDataForm.setAcNameEnglish(element.getAcNameEnglish());
				assemblyDataForm.setAcNameLocal(element.getAcNameLocal());
				assemblyDataForm.setEciCode(element.getEciCode());
				// assemblyDataForm.set(cordinate);
				assemblyDataForm.setAcCode(element.getAcCode());
				assemblyDataForm.setPcCode(element.getPlc());
				// ParliamentForm.setMapLandregionCode(element.getMapLandregionCode());
				assemblyDataForm.setCreatedby(element.getCreatedby());
				assemblyDataForm.setCreatedon(element.getCreatedon());
				tempCode = element.getAcCode();
				assemblyDataForm.setEffectiveDate(element.getEffectiveDate());
				listAssemblyConstituencyDetails.add(assemblyDataForm);

				AssemblyForm assemblyForm = new AssemblyForm();
				assemblyForm.setListAssemblyFormDetail(listAssemblyConstituencyDetails);
			}
		}
		return listAssemblyConstituencyDetails;
	}

	public List<ParliamentDataForm> getParliamentConstituencyDetail(int ParliamentCode) throws Exception {
		ParliamentDataForm ParliamentForm = new ParliamentDataForm();
		List<ParliamentConstituency> listParliamentDetail = subdistrictDAO.getParliamentDetailsModify(ParliamentCode);
		int parliamentConstituencyversion = subdistrictDAO.getMaxParliamentConstituencyVersion(ParliamentCode);

		List<GetGovernmentOrderDetail> govtOrderValue = subdistrictDAO.GovOrderDetail('P', ParliamentCode, parliamentConstituencyversion);
		Iterator<GetGovernmentOrderDetail> itrGovt = govtOrderValue.iterator();
		while (itrGovt.hasNext()) {
			GetGovernmentOrderDetail governmentOrder = (GetGovernmentOrderDetail) itrGovt.next();
			governmentOrder.getOrderNo();
			governmentOrder.getOrderDate();
			governmentOrder.getEffectiveDate();
			governmentOrder.getGazPubDate();
			ParliamentForm.setOrderCode(governmentOrder.getOrderCode());
			ParliamentForm.setOrderNo(governmentOrder.getOrderNo().trim());
			ParliamentForm.setOrderDate(governmentOrder.getOrderDate());
			ParliamentForm.setEffectiveDate(governmentOrder.getEffectiveDate());
			ParliamentForm.setGazPubDate(governmentOrder.getGazPubDate());
		}
		int mapCode = 0;
		int tempCode = 0;

		List<ParliamentDataForm> listParliamentConstituencyDetails = new ArrayList<ParliamentDataForm>();
		Iterator<ParliamentConstituency> itr = listParliamentDetail.iterator();
		while (itr.hasNext()) {
			ParliamentConstituency element = (ParliamentConstituency) itr.next();
			if (tempCode != element.getId().getPcCode()) {
				String cordinate = "";
				try {
					if (element.getMapConstituencyCode() == null) {
						mapCode = 0;
					} else {
						mapCode = element.getMapConstituencyCode();
					}
					if (mapCode == 0) {
						log.debug("Exception" + mapCode);
					} else {
						List<MapLandRegion> listCordinate = subdistrictDAO.getMapDetailsModify(mapCode);
						Iterator<MapLandRegion> itr1 = listCordinate.iterator();
						while (itr1.hasNext()) {
							MapLandRegion mapLandRegion = (MapLandRegion) itr1.next();
							cordinate = mapLandRegion.getCoordinates();
							int mapCode1 = mapLandRegion.getLandregionCode();
							int version = mapLandRegion.getLandregionVersion();
							// System.out.println(cordinate + "<>");
							ParliamentForm.setLati(cordinate);
						}
					}

				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				ParliamentForm.setPcNameEnglish(element.getPcNameEnglish());
				ParliamentForm.setPcNameLocal(element.getPcNameLocal());
				ParliamentForm.setEciCode(element.getEciCode());
				ParliamentForm.setCordinate(cordinate);
				ParliamentForm.setPcCode(element.getPcCode());

				// ParliamentForm.setMapLandregionCode(element.getMapLandregionCode());
				ParliamentForm.setCreatedby(element.getCreatedby());
				ParliamentForm.setCreatedon(element.getCreatedon());
				tempCode = element.getPcCode();
				ParliamentForm.setEffectiveDate(element.getEffectiveDate());
				listParliamentConstituencyDetails.add(ParliamentForm);

				ParliamentForm parliamentform = new ParliamentForm();
				parliamentform.setListParliamentFormDetail(listParliamentConstituencyDetails);
			}
		}
		return listParliamentConstituencyDetails;
	}

	// changed
	public List<Subdistrict> getTargetSubDistListforShift(int subdistrictCode, int districtCode) throws Exception {
		Session session = null;
		Query query = null;
		List<Subdistrict> subdistrictlist = new ArrayList<Subdistrict>();
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Subdistrict sd where sd.isactive=true and sd.district.districtPK.districtCode=" + districtCode + " and sd.subdistrictPK.subdistrictCode !=" + subdistrictCode + " order by sd.subdistrictNameEnglish");
			subdistrictlist = query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrictlist;

	}

	@Override
	public boolean changeSubDistrict(SubDistrictForm subdistrictForm, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int subdistrictVersion = 0;
			Integer tid = null, orderCode = null;
			List<SubdistrictDataForm> listSubdistrict = new ArrayList<SubdistrictDataForm>();
			

			listSubdistrict = subdistrictForm.getListSubdistrictDetails();
			Iterator<SubdistrictDataForm> itr = listSubdistrict.iterator();
			while (itr.hasNext()) {

				SubdistrictDataForm element = (SubdistrictDataForm) itr.next();
				subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(element.getSubdistrictCode());
				if (subdistrictVersion == 1) {
					subdistrictVersion = subdistrictVersion + 1;
				} else {
					subdistrictVersion = subdistrictVersion + 1;
				}

				String subdistrictCode = Integer.toString(element.getSubdistrictCode());
				// dsfsd
				String data = subdistrictDAO.ChangeSubDistrict(element.getSubdistrictCode(), element.getSubdistrictNameEnglish(), element.getSubdistrictNameEnglishch(), subdistrictForm.getUserId(), element.getSubdistrictNameLocalch(), element.getAliasEnglishch(),
						element.getAliasLocalch(), session, subdistrictForm.getOrderNo(), subdistrictForm.getOrderDate(), subdistrictForm.getEffectiveDate(), subdistrictForm.getGovtOrderConfig(), subdistrictForm.getGazPubDate(), subdistrictForm.getStateCode());

				if (data != null) {
					String temp[] = data.split(",");
					tid = Integer.parseInt(temp[0]);
					orderCode = Integer.parseInt(temp[1]);
				}

				/*
				 * GovernmentOrder govtOrder = convertLocalbodyService
				 * .saveDataInGovtOrder(govtOrderForm, session);
				 */
				if (orderCode != null) {
					boolean flag = convertLocalbodyService.saveDataInAttachmentWithOrderCode(orderCode, attachedList, session);
					/*
					 * shiftService.saveDataInGovtOrderEntityWise(govtOrder, i
					 * subdistrictCode, 'T', session);
					 */
					if (flag) {
						tx.commit();
						char t_type = 'T';
						EmailSmsThread est = new EmailSmsThread(tid, t_type, emailService);
						est.start();

					} else {
						tx.rollback();
						return false;
					}
				} else {
					tx.rollback();
					return false;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	@Override
	public boolean changeSubDistrictforTemplate(SubDistrictForm subdistrictForm, GovernmentOrderForm govtOrderForm, HttpServletRequest request, HttpSession httpSession) throws Exception {
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int subdistrictVersion = 0;
			Integer tid = null, orderCode = null;
			boolean flag = false;
			
			Integer stateCode = null, slc = null;
			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
			if (request.getAttribute("stateCode") != null) {
				stateCode = Integer.parseInt(request.getAttribute("stateCode").toString());
			}

			slc = commonService.getSlc(stateCode);

			GenerateDetails generatedetails = new GenerateDetails();
			List<SubdistrictDataForm> listsubdistrict = new ArrayList<SubdistrictDataForm>();
			listsubdistrict = subdistrictForm.getListSubdistrictDetails();
			Iterator<SubdistrictDataForm> itr = listsubdistrict.iterator();
			while (itr.hasNext()) {

				SubdistrictDataForm element = (SubdistrictDataForm) itr.next();
				subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(element.getSubdistrictCode());
				if (subdistrictVersion == 1) {
					subdistrictVersion = subdistrictVersion + 1;
				} else {
					subdistrictVersion = subdistrictVersion + 1;
				}

				String subdistrictCode = Integer.toString(element.getSubdistrictCode());
				String orderno = govtOrderForm.getOrderNo();
				Date orderdate = govtOrderForm.getOrderDate();
				Date effctdate = govtOrderForm.getEffectiveDate();
				// session("userid");

				String data = subdistrictDAO.ChangeSubDistrict(element.getSubdistrictCode(), element.getSubdistrictNameEnglish(), element.getSubdistrictNameEnglishch(), userId, element.getSubdistrictNameLocalch(), element.getAliasEnglishch(),
						element.getAliasLocalch(), session, orderno, orderdate, effctdate, element.getOrderPath(), element.getGazPubDate(), slc);
				generatedetails = (GenerateDetails) httpSession.getAttribute("validGovermentGenerateUpload");

				if (data != null) {
					String temp[] = data.split(",");
					tid = Integer.parseInt(temp[0]);
					orderCode = Integer.parseInt(temp[1]);
				}

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
				char t_type = 'T';
				EmailSmsThread est = new EmailSmsThread(tid, t_type, emailService);
				est.start();
			} else
				tx.rollback();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			ModelAndView mav = new ModelAndView(redirectPath);
			return false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<Headquarters> getHeadquterDetails(Integer regionCode, String region_type) throws Exception {
		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		String HQL;
		List<Headquarters> headquartersDetails = new ArrayList<Headquarters>();
		try {
			HQL = "from Headquarters where lrlc=" + regionCode + " and regionType='" + region_type + "' and isactive=true";
			headquartersDetails = headquartersDAO.getHeadquartersDetailsDAO(HQL, hsession);

			tx.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			// System.out.println("Download Directory All
			// StateRpt"+e.toString());
			headquartersDetails = null;
			tx.rollback();
		} finally {
			if (hsession != null && hsession.isOpen()) {
				hsession.close();
			}

			sessionFactory.close();
		}
		return headquartersDetails;
	}

	@Override
	public boolean modifySubDistrictCrInfo(SubDistrictForm subdistrictForm, HttpServletRequest request, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, boolean delflag, String deleteAttachmentId[],
			HttpSession httpSession) throws Exception {

		Session session1 = sessionFactory.openSession();
		Transaction tx1 = session1.beginTransaction();
		

		try {

			List<SubdistrictDataForm> listsubDistrict = subdistrictForm.getListSubdistrictDetails();

			if (!listsubDistrict.isEmpty()) {
				
				SubdistrictDataForm element = listsubDistrict.get(0);

				Query query = session1.createSQLQuery("select * from change_subdistrict_basic_details_fn(:villageCode,:userId,:aliasEnglish,:aliasLocal,:headquartersEng,:headquartersLocal,:sscode,:order_no,:order_date,:effectiveDate,:gaz_pubdate,:govtOrderConfig,:slc)");
				 query.setParameter("villageCode"	  ,element.getSubdistrictCode()	       , Hibernate.INTEGER)
					.setParameter("userId"	    	  ,userId				  			   , Hibernate.INTEGER)
					.setParameter("aliasEnglish"  	  ,element.getAliasEnglish()		   , Hibernate.STRING)
					.setParameter("aliasLocal"		  ,element.getAliasLocal()			   , Hibernate.STRING)
					.setParameter("headquartersEng"   ,element.getHeadquarterName()		   , Hibernate.STRING)
					.setParameter("headquartersLocal" ,element.getHeadquarterNameLocal()   , Hibernate.STRING)
					.setParameter("sscode"			  ,element.getSscode()				   , Hibernate.STRING)
					.setParameter("order_no"		  ,element.getOrderNocr()			   , Hibernate.STRING)
					.setParameter("order_date"		  ,element.getOrderDatecr()			   , Hibernate.TIMESTAMP)
					.setParameter("effectiveDate"	  ,element.getOrdereffectiveDatecr()   , Hibernate.TIMESTAMP)
					.setParameter("gaz_pubdate"		  ,element.getGazPubDatecr()		   , Hibernate.TIMESTAMP)
					.setParameter("govtOrderConfig"   ,"upload"			                   , Hibernate.STRING)
					.setParameter("slc"	    	      ,subdistrictForm.getStateCode()	   , Hibernate.INTEGER);
				String returnedValue = (String) query.uniqueResult();
				tx1.commit();
				if (returnedValue != null) {
					String[] ldata = returnedValue.split(",");
					String orderCode = ldata[0];
					String tid = ldata[1];
				
			
				
					

				int Transactionid = Integer.parseInt(tid);
				int ocode = Integer.parseInt(orderCode);
				
				GovernmentOrder govtOrder=(GovernmentOrder)session1.get(GovernmentOrder.class, ocode);
				
				
				
				
				/*Integer subdistrictCode = element.getSubdistrictCode();
			
				List<Subdistrict> existSubdistrictList =this.getSubDistrictListBySubDistCode(subdistrictCode);
				Subdistrict existSubdistrict=null;
				if(existSubdistrictList!=null && !existSubdistrictList.isEmpty()) {
					existSubdistrict=existSubdistrictList.get(0);
					SubdistrictPK subdistrictPK = new SubdistrictPK();
					subdistrictPK.setSubdistrictCode(subdistrictCode);
					subdistrictPK.setSubdistrictVersion(existSubdistrict.getSubdistrictVersion());
					subdistrictPK.setMinorVersion(existSubdistrict.getMinorVersion());
					Date currentDate = dateTimeUtil.getCurrentDate();
					

					SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
					Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;

					String cord1 = "";
					if (subdistrictForm.getLati() != null && !subdistrictForm.getLati().isEmpty() && subdistrictForm.getLongi() != null && !subdistrictForm.getLongi().isEmpty()) {
						if (subdistrictForm.getLati().split(",").length >= 1) {
							String[] tempLati = subdistrictForm.getLati().split(",");
							String[] tempLongi = subdistrictForm.getLongi().split(",");
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
					if (!cord1.equalsIgnoreCase(previousCords) && cord1 != "") {
						subdistrictForm.setWarningFlag(false);
					}

					if (cord1 == "") {
						cord1 = null;
					}

					Subdistrict subdistrict = new Subdistrict();
					
					draftUtils.copyBeanAttributes(subdistrict, existSubdistrict); 
					
					subdistrict.setAliasEnglish(element.getAliasEnglish());
					subdistrict.setAliasLocal(element.getAliasLocal());
					//subdistrict.setCensus2011Code(element.getCensus2011Code());
					if (element.getSscode() != "")
						subdistrict.setSscode(element.getSscode());
					else
						subdistrict.setSscode(null);
					subdistrict.setCoordinates(cord1);

					subdistrict.setWarningFlag(subdistrictForm.getWarningFlag());
					subdistrict.setLastupdatedby(userId);
					subdistrict.setLastupdated(currentDate);
					//subdistrict.setMapLandregionCode(mapCode);
					int minorVersion=subdistrict.getMinorVersion();
					subdistrict.setMinorVersion(minorVersion);
					subdistrict.getSubdistrictPK().setMinorVersion(minorVersion+1);
					session1.save(subdistrict);
					session1.flush();
					
					existSubdistrict.setIsactive(false);
					existSubdistrict.getSubdistrictPK().setMinorVersion(minorVersion);
					session1.update(existSubdistrict);
					session1.flush();

					if (element.getHeadquarterName() != "" || element.getHeadquarterNameLocal() != "") {

						List<Headquarters> headquarterList = stateService.getHeadquterDetailALL(element.getSubdistrictCode(), "T");
						for (Headquarters headquarter : headquarterList) {

							Headquarters headquarterupdate = (Headquarters) session1.get(Headquarters.class, headquarter.getHeadquarterCode());
							headquarterupdate.setIsactive(false);

							session1.update(headquarterupdate);
							session1.flush();
							session1.clear();

						}

						Headquarters headquarters = new Headquarters();
						Integer headquarterCode = element.getHeadquarterCode();
						if (headquarterCode != null) {
							headquarters.setHeadquarterCode(headquarterCode);

						}

						headquarters.setHeadquarterVersion(1);
						headquarters.setIsactive(true);
						headquarters.setRegionType('T');
						headquarters.setLrlc(subdistrictCode);
						headquarters.setHeadquarterNameEnglish(element.getHeadquarterName());
						headquarters.setHeadquarterLocalName(element.getHeadquarterNameLocal());
						headquartersDAO.saveOrUpdate(headquarters, session1);
					}

					GovernmentOrder govtOrder = new GovernmentOrder();
					if (element.getOrderCodecr() != null) {

						govtOrder = govtOrderService.updateGovernmentOrder(element.getOrderNocr(), element.getOrderCodecr(), element.getOrderDatecr(), element.getOrdereffectiveDatecr(), element.getGazPubDatecr(), "update", request, session1);
					} else {
						
						 * insert the record into goverment order and goverment
						 * order entity wise table if record is blank for goverment
						 * order
						 * 
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
							govtOrder.setUserId(userId);
							govtOrder.setXmlDbPath("sdf");
							govtOrder.setXmlOrderPath("");
							session1.save(govtOrder);
							session1.flush();
							session1.refresh(govtOrder);
							

							GovernmentOrderEntityWiseNew governmentOrderEntityWise = new GovernmentOrderEntityWiseNew();
							governmentOrderEntityWise.setEntityCode(subdistrictCode);
							governmentOrderEntityWise.setEntityType('T');
							governmentOrderEntityWise.setEntityVersion(existSubdistrict.getSubdistrictVersion());
							governmentOrderEntityWise.setGovernmentOrder(govtOrder);
							governmentOrderEntityWise.setMinorVersion(existSubdistrict.getMinorVersion()+1);
							session1.saveOrUpdate(governmentOrderEntityWise);
							session1.flush();
						}

					}
*/
					if (deleteAttachmentId != null) {
						govtOrderDAO.deleteAttachmentForLandRegion(deleteAttachmentId, session1);

					}

					if (attachedList != null) {
						villageService.saveDataInAttachment(govtOrder, attachedList, session1);
					}

					
				}
				
				
			}

		} catch (Exception e) {
			tx1.rollback();
			throw new Exception(e);

		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
		}
		return true;

	}

	/*@Override
	public List<CheckAuthorization> getSubDistrictListAuthentication(int districtCode, HttpSession httpSession) throws Exception {
		try {

			// Check if District is Authorized Distrcit of logged in State or
			// Not
			commonService.checkAuthorization('D', "S", Integer.parseInt(httpSession.getAttribute("stateCode").toString()), districtCode);

			return commonService.checkAuthorization('T', "D", districtCode, null);
		} catch (Exception e) {
			throw e;
		}
	}*/

	/* Retrieve files from the attachment table */

	public List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws Exception {
		return subdistrictDAO.getAttacmentdetail(orderCode);
	}
	/* Retrieving the Map details attachment from the database */

	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			return subdistrictDAO.getEntityWiseMapDetails(entityType, entityCode, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session.clear();
			}
		}
		return null;

	}

	@Override
	public Subdistrict getById(Integer subDistrictCode, Session session) throws Exception {

		return subdistrictDAO.getById(subDistrictCode, session);
	}

	@Override
	public int saveDataInMap(SubDistrictForm subDistrictForm, List<AttachedFilesForm> validFileMap, HttpSession httpSession, Session session) {
		return subdistrictDAO.saveDataInMap(subDistrictForm, validFileMap, httpSession, session);
	}

	@Override
	public String insertSubDistrict(HttpServletRequest request, HttpSession httpSession, SubDistrictForm subDistrictForm, Session session) {
		return subdistrictDAO.insertSubDistrict(request, httpSession, subDistrictForm, session);

	}

	@Override
	public void saveOrUpdate(int subDistrictId, int mapAttachmentId, Session session) {
		subdistrictDAO.saveOrUpdate(subDistrictId, mapAttachmentId, session);
	}

	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameExtend(Integer districtCode, Integer orgCode) throws Exception {
		return subdistrictDAO.getSubDistrictListbyDistrictCodewithDistrictNameExtend(districtCode, orgCode);
	}

	@Override
	public boolean subDistrictExist(int disCode, String subDistrictName) throws Exception {
		// TODO Auto-generated method stub
		return subdistrictDAO.subDistrictExist(disCode, subDistrictName);
	}

	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameExtended(int districtCode, int orgCode, int locatedAtLevelCode) throws BaseAppException {
		return subdistrictDAO.getSubDistrictListbyDistrictCodewithDistrictNameExtended(districtCode, orgCode, locatedAtLevelCode);

	}

	@Override
	public List<Subdistrict> getSubDistrictListbyDistrictCodeExtended(int districtCode, String subdistrictList, Integer orgCode, Integer locatedAtLevelCode) throws BaseAppException {
		return subdistrictDAO.getSubDistrictListbyDistrictCodeExtended(districtCode, subdistrictList, orgCode, locatedAtLevelCode);
	}

	/* added on 31/12/2014 for the localbody impact by kirandeep */

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCodeForLocalBody(int stateCode) {
		Session session = sessionFactory.openSession();
		List<District> districts = new ArrayList<District>();
		try {

			String queryBuilder = "select case when d.district_code  in (select * from get_draft_used_lb_lr_temp(d.district_code,'D')) then  cast ('F' as character) else cast('A' as character) end as operation_state, district_code as districtCode, district_name_english as districtNameEnglish from district d  where d.isactive='true' and d.slc="
					+ stateCode + " order by d.district_name_english  ";

			SQLQuery query = session.createSQLQuery(queryBuilder);
			query.addScalar("districtCode").addScalar("districtNameEnglish").addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(District.class)).list();
			districts = query.list();
			return districts;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/* added on 01/01/2015 for the localbody impact by kirandeep */
	@Override
	public List<Subdistrict> getSubDistrictListForLocalbody(int districtCode) throws Exception {
		return subdistrictDAO.getSubDistrictListForLocalbody(districtCode);
	}

	/***
	 * 
	 * added by kirandeep on 07/01/2015 for the localbody impact
	 * 
	 * 
	 */

	public List<Subdistrict> getTargetSubDistListforShiftForLocalbody(int subdistrictCode, int districtCode) throws Exception {

		Session session = sessionFactory.openSession();
		List<Subdistrict> subdistrictlist = new ArrayList<Subdistrict>();
		try {

			String queryBuilder = "select case when sd.subdistrict_code  in (select * from get_draft_used_lb_lr_temp(sd.subdistrict_code,'T')) then  cast ('F' as character) else cast('A' as character) end as operation_state,  sd.subdistrict_code as subdistrictCode , sd.subdistrict_name_english as subdistrictNameEnglish from subdistrict sd inner join district d on  d.dlc=sd.dlc and d.district_code="
					+ districtCode + " where sd.isactive and d.isactive and sd.subdistrict_code!= " + subdistrictCode;

			SQLQuery query = session.createSQLQuery(queryBuilder);
			query.addScalar("subdistrictCode").addScalar("subdistrictNameEnglish").addScalar("operation_state");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class)).list();
			subdistrictlist = query.list();
			return subdistrictlist;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	public List<Subdistrict> getSubDistrictListbyDistrictCodewithDistrictNameForLocalBody(int districtCode) throws BaseAppException {

		return subdistrictDAO.getSubDistrictListbyDistrictCodewithDistrictNameForLocalBody(districtCode);
	}

	/**
	 * 
	 * added by kirandeep on 08/01/2015
	 * 
	 * @return
	 * @throws BaseAppException
	 */

	public List<Subdistrict> getSubDistrictListbyDistrictCodeForLocalBody(int districtCode, String subdistrictList) throws BaseAppException {

		return subdistrictDAO.getSubDistrictListbyDistrictCodeForLocalBody(districtCode, subdistrictList);
	}

	@Override
	public List<Subdistrict> getSubdistrictList(Integer districtCode) throws Exception {
		return subdistrictDAO.getSubdistrictList(districtCode);
	}

	@Override
	public List<SubdistrictHistory> getSubDistHistoryReport(char subdistrictNameEnglish, int subdistrictCode) throws BaseAppException {

		return subdistrictDAO.getSubDistHistoryReport(subdistrictNameEnglish, subdistrictCode);
	}

	@Override
	public List<Village> getvillagesInDraftMode(Integer subdistrictCode) throws BaseAppException {
		return subdistrictDAO.getvillagesInDraftMode(subdistrictCode);

	}

	/**
	 * 
	 * added by Anju Gupta on 23/03/2015
	 * 
	 * @return SubdistrictList match with district
	 *
	 **/
	public List<Subdistrict> getSubdistrictListBySLCfprMarkPesa(int slc) throws Exception {
		return subdistrictDAO.getSubdistrictListBySLCfprMarkPesa(slc);
	}

	/**
	 * Using DWR Subdistrict List populate base on Creteria for extend
	 * organigation Units
	 * 
	 * @author Maneesh Kumar 21-July-2015
	 */
	@Override
	public List<Subdistrict> getSubDistrictListbyCreteria(String districtCodes, String subDistrictCodes, Integer districtCode) throws Exception {
		List<Integer> districtList = districtCodes != null ? commonService.createListFormString(districtCodes) : null;
		List<Integer> subDistrictCodeList = subDistrictCodes != null ? commonService.createListFormString(subDistrictCodes) : null;
		return subdistrictDAO.getSubDistrictListbyCreteria(districtList, subDistrictCodeList, districtCode);

	}

	@Override
	public Response saveEffectiveDateEntitySubdistrict(List<GetEntityEffectiveDate> getEntityEffectiveDateList,
			Integer userId) throws Exception {
		// TODO Auto-generated method stub
		return subdistrictDAO.saveEffectiveDateEntitySubdistrict(getEntityEffectiveDateList, userId);
	}

	
	

	

	



}