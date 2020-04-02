package in.nic.pes.lgd.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cmc.lgd.localbody.entities.LocalBodyForm;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GetLBDetailsBySubDistricts;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWiseNew;
import in.nic.pes.lgd.bean.Habitation;
import in.nic.pes.lgd.bean.HabitationConfiguration;
import in.nic.pes.lgd.bean.LandregionReplacedby;
import in.nic.pes.lgd.bean.LandregionReplaces;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.PesApplicationMaster;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.UnpublishedItems;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillageDraft;
import in.nic.pes.lgd.bean.VillageHistory;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.bean.VillagePartsBySurveyno;
import in.nic.pes.lgd.common.EncryptionUtil;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.constant.CommanConstant;
import in.nic.pes.lgd.dao.AssemblyDAO;
import in.nic.pes.lgd.dao.BlockDAO;
import in.nic.pes.lgd.dao.GovernmentOrderDAO;
import in.nic.pes.lgd.dao.LandRegionReplacedByDAO;
import in.nic.pes.lgd.dao.LandRegionReplacesDAO;
import in.nic.pes.lgd.dao.LgdDesignationDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.MapLandRegionDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.draft.entities.DraftMaster;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.HabitationForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.ShiftService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.CurrentDateTime;
import in.nic.pes.lgd.utils.DatePicker;
import in.nic.pes.lgd.ws.District;
import in.nic.pes.lgd.ws.GISInformation;
import in.nic.pes.lgd.ws.GISNode;
import in.nic.pes.lgd.ws.GISNodes;
import in.nic.pes.lgd.ws.InvalidateVillageForm;
import in.nic.pes.lgd.ws.LandDetails;
import in.nic.pes.lgd.ws.Parent;
import in.nic.pes.lgd.ws.Parents;
import in.nic.pes.lgd.ws.Subdistricts;
import in.nic.pes.lgd.ws.SurveyNumbers;
import in.nic.pes.lgd.ws.Villages;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

@Service
@Transactional
@Repository
public class VillageServiceImpl implements VillageService {
	private static final Logger log = Logger.getLogger(VillageServiceImpl.class);

	@Autowired
	private VillageDAO villageDAO;

	@Autowired
	private MapLandRegionDAO mapLandRegionDAO;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	private LandRegionReplacedByDAO landRegionReplacedByDAO;

	@Autowired
	private LandRegionReplacesDAO landRegionReplacesDAO;

	@Autowired
	SubDistrictDAO subdistrictDAO;

	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	ShiftService shiftService;

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	BlockDAO blockDAO;

	@Autowired
	AssemblyDAO AssemblyDAO;

	@Autowired
	CurrentDateTime dateTimeUtil;

	@Autowired
	GovernmentOrderDAO govtOrderDAO;

	@Autowired
	FileUploadUtility fileUploadUtility;

	@Autowired
	EmailService emailService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	DraftUtils draftUtils;
	
	@Autowired
	LgdDesignationDAO lgdDesignationDAO;

	int subdistrictCode = 0;
	int subdistricVersion = 0;
	int village_version = 0;
	int village_minor_version = 0;
	int userId = 1000;
	long createdBy = 1000;
	Session session = null;
	Transaction tx = null;

	int fullContri;
	int partcontri;
	private boolean createFromExistingVillages;
	private boolean createFromNewLand;
	private boolean createFromCoverageOfUrbanLocalBody;
	int roleCode = 1000;

	public Village setVillage(VillageForm villageForm) throws Exception {
		// create bean object
		Village villageBean = new Village();
		// For current date
		Date timestamp = DatePicker.getDate("2011-09-20");
		int villageCode = 0;
		villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
		if (villageCode == 0) {
			villageCode = 1;
		} else {
			villageCode = villageCode + 1;
		}

		VillagePK villagePk = new VillagePK(villageCode, village_version,village_minor_version);
		villageBean.setVillagePK(villagePk);
		villageBean.setAliasEnglish(villageForm.getNewVillageNameEnglish());
		villageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
		if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
			villageBean.setCensus2011Code(villageForm.getCensus2011Code());
		}
		villageBean.setCreatedby(roleCode);
		villageBean.setIsactive(true);
		villageBean.setLastupdatedby(roleCode);
		villageBean.setLrReplacedby(null);// .setLrPartCode(null);
		villageBean.setLrReplaces(null);
		villageBean.setMapCode(null);
		// villageBean.setRemarks("village created ");
		villageBean.setSscode(villageForm.getStateSpecificCode());
		villageBean.setTlc(subdistrictCode);
		villageBean.setTlc(subdistricVersion);
		villageBean.setVlc(village_version);
		villageBean.setLastupdated(timestamp);
		villageBean.setCreatedon(timestamp);
		villageBean.setEffectiveDate(timestamp);
		villageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
		villageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
		villageBean.setVillageStatus(villageForm.getVillageType());
		return villageBean;
	}

	public MapLandRegion setVillageCoordinates(VillageForm villageForm) throws Exception {
		// for MAP Coordinates
		Date timestamp = DatePicker.getDate("2011-09-20");
		MapLandRegion mapLandRegion = new MapLandRegion();
		String coordinate = villageForm.getLatitude() + ":" + villageForm.getLongitude();
		mapLandRegion.setCoordinates(coordinate);

		int villageCode = 0;
		villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
		mapLandRegion.setLandregionCode(villageCode);
		mapLandRegion.setLandregionVersion(village_version);
		mapLandRegion.setLandregionType('V');
		mapLandRegion.setCoordinates(coordinate);
		mapLandRegion.setLastupdated(timestamp);
		mapLandRegion.setEffectiveDate(timestamp);
		mapLandRegion.setLastupdatedby(roleCode);
		mapLandRegion.setCreatedby(roleCode);
		mapLandRegion.setCreatedon(timestamp);
		mapLandRegion.setWarningflag(false);
		return mapLandRegion;
	}

	@SuppressWarnings("null")
	@Override
	public boolean addVillage(VillageForm villageForm, HttpServletRequest request, HttpSession httpSession) throws Exception {

		int idLR = 0;
		int flagCode = 1;
		int lrReplacedby = 0;
		Village oldVillage = null;
		Village newVillageBean = null;
		LandregionReplacedby LandregionReplacedbyBean = null;
		Timestamp time = null;
		int villageCode = 0;
		time = CurrentDateTime.getCurrentTimestamp();
		String EffectiveDate = "2011-12-12";
		createFromNewLand = villageForm.isCreateFromNewLand();
		createFromCoverageOfUrbanLocalBody = villageForm.isCreateFromCoverageOfUrbanLocalBody();
		createFromExistingVillages = villageForm.isCreateFromExistingVillages();

		List<Village> fullContributedList = null;
		fullContributedList = new ArrayList<Village>();

		List<Village> partContributedList = null;
		partContributedList = new ArrayList<Village>();

		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();

		if (villageForm.getSubdistrictNameEnglish() != null && villageForm.getSubdistrictNameEnglish() != "") {
			subdistrictCode = Integer.parseInt(villageForm.getSubdistrictNameEnglish());

			try {
				sdList = villageDAO.getSubdistrictDetails("from Subdistrict where subdistrictCode='" + subdistrictCode + "' and isactive=true");
			} catch (Exception e2) {
				log.debug("Exception" + e2);
			}
			subdistricVersion = sdList.get(0).getTlc();
		}
		if (createFromExistingVillages) {

			String selectedVillage = villageForm.getContributedVillages();
			if (selectedVillage != null) {
				String[] temp = selectedVillage.split(",");
				for (int i = 0; i < temp.length; i++) {
					if (temp[i].contains("FULL")) {
						int vCodeFull = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
						List<Village> lstTemp = null;
						lstTemp = new ArrayList<Village>();
						try {
							lstTemp = villageDAO.getListVillageDetails("from Village where village_code='" + vCodeFull + "' and isactive=true");
						} catch (Exception e) {
							log.debug("Exception" + e);
						}
						fullContributedList.add(lstTemp.get(0));
					}
					if (temp[i].contains("PART")) {
						int vCodePart = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
						List<Village> lstTemp = null;
						lstTemp = new ArrayList<Village>();
						try {
							lstTemp = villageDAO.getListVillageDetails("from Village where village_code='" + vCodePart + "' and isactive=true");
						} catch (Exception e) {
							log.debug("Exception" + e);
						}
						partContributedList.add(lstTemp.get(0));
					}

				}
			}
			@SuppressWarnings("unused")
			List<VillagePartsBySurveyno> villagePartsBySurveynolist = new ArrayList<VillagePartsBySurveyno>();
		} else {
			fullContributedList.clear();
			partContributedList.clear();
		}

		// *******************************************************************/

		List<Subdistrict> fullSubdistrictContributingList = null;
		fullSubdistrictContributingList = new ArrayList<Subdistrict>();
		List<Subdistrict> partSubdistrictContributingList = null;
		partSubdistrictContributingList = new ArrayList<Subdistrict>();

		if (createFromCoverageOfUrbanLocalBody) {

			String selectedCoveredLandRegionByULB = villageForm.getSelectedCoveredLandRegionByULB();
			if (selectedCoveredLandRegionByULB != null) {
				String[] temp = selectedCoveredLandRegionByULB.split(",");
				for (int i = 0; i < temp.length; i++) {
					if (temp[i].contains("FULL")) {
						int sdCodeFull = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
						List<Subdistrict> lstTemp = null;
						lstTemp = new ArrayList<Subdistrict>();

						try {
							lstTemp = villageDAO.getSubdistrictDetails("from Subdistrict where subdistrictCode='" + sdCodeFull + "' and isactive=true");
						} catch (Exception e) {
							log.debug("Exception" + e);
						}
						fullSubdistrictContributingList.add(lstTemp.get(0));

					}
					if (temp[i].contains("PART")) {
						int sdCodePart = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
						List<Subdistrict> lstTemp = null;
						lstTemp = new ArrayList<Subdistrict>();
						try {
							lstTemp = villageDAO.getSubdistrictDetails("from Subdistrict where subdistrictCode='" + sdCodePart + "' and isactive=true");
						} catch (Exception e) {
							log.debug("Exception" + e);
						}
						partSubdistrictContributingList.add(lstTemp.get(0));
					}

				}
			}
		} else {
			fullSubdistrictContributingList.clear();
			partSubdistrictContributingList.clear();
		}
		httpSession.setAttribute("partContributedList", partContributedList);
		httpSession.setAttribute("fullContributedList", fullContributedList);
		if (fullContributedList.size() == 0 && partContributedList.size() == 1 && createFromNewLand == false && createFromCoverageOfUrbanLocalBody == false) {
			newVillageBean = new Village();
			villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
			if (villageCode == 0) {
				villageCode = 1;
			} else {
				villageCode = villageCode + 1;
			}

			newVillageBean.setAliasEnglish(villageForm.getNewVillageAliasEnglish());
			newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
			if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
				newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
			}
			newVillageBean.setCreatedby(roleCode);
			newVillageBean.setIsactive(true);
			newVillageBean.setLastupdatedby(roleCode);
			newVillageBean.setLrReplaces(null);
			newVillageBean.setLrReplacedby(null);
			newVillageBean.setMapCode(null);
			newVillageBean.setSscode(villageForm.getStateSpecificCode());
			newVillageBean.setLastupdated(time);
			newVillageBean.setCreatedon(time);
			newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
			newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
			newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
			newVillageBean.setVillageStatus(villageForm.getVillageType());
			VillagePK villagePK = new VillagePK(villageCode, 1,1);
			newVillageBean.setVillagePK(villagePK);
			newVillageBean.setFlagCode(flagCode);
			if (request != null) {
				this.uploadMapOrLat(villageForm, villageCode, request);
			}
			try {
				villageDAO.save(newVillageBean);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			this.insertSurveyNumber(villageForm, villageCode, 1);
			int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
			int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");

			LandregionReplaces landregionReplacesBean = null;
			landregionReplacesBean = new LandregionReplaces();
			landregionReplacesBean.setId(id);
			landregionReplacesBean.setLrReplaces(lrReplaces);
			landregionReplacesBean.setEntityCode(partContributedList.get(0).getVlc());
			landregionReplacesBean.setEntityVersion(partContributedList.get(0).getVlc());
			landregionReplacesBean.setEntityType('V');
			try {
				landRegionReplacesDAO.save(landregionReplacesBean);
			} catch (Exception e1) {
				log.debug("Exception" + e1);
			}

			try {
				VillagePK villagePk = new VillagePK();
				villagePk.setVillageCode(villageCode);
				villagePk.setVillageVersion(1);
				villageDAO.updateLReplaces(lrReplaces, villagePk);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			for (int i = 0; i < partContributedList.size(); i++) {

				lrReplacedby = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replacedby),1) from landregion_replacedby");
				idLR = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replacedby");
				LandregionReplacedbyBean = new LandregionReplacedby();
				LandregionReplacedbyBean.setId(idLR);
				LandregionReplacedbyBean.setLrReplacedby(lrReplacedby);
				LandregionReplacedbyBean.setEntityCode(villageCode);
				LandregionReplacedbyBean.setEntityVersion(1);
				LandregionReplacedbyBean.setEntityType('V');
				try {
					landRegionReplacedByDAO.save(LandregionReplacedbyBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}
				// deactivating the old village
				oldVillage = new Village();
				try {
					VillagePK villagePk = new VillagePK();
					villagePk.setVillageCode(partContributedList.get(i).getVlc());
					villagePk.setVillageVersion(partContributedList.get(i).getVlc());
					oldVillage.setIsactive(false);
					villageDAO.updateIsActive(villagePk);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}

				// inserting old village with new version and with replacedby
				// field
				oldVillage.setAliasEnglish(partContributedList.get(i).getAliasEnglish());
				oldVillage.setAliasLocal(partContributedList.get(i).getAliasLocal());
				oldVillage.setCensus2001Code(partContributedList.get(i).getCensus2001Code());
				oldVillage.setCensus2011Code(partContributedList.get(i).getCensus2011Code());
				oldVillage.setCreatedby(partContributedList.get(i).getCreatedby());
				oldVillage.setCreatedon(partContributedList.get(i).getCreatedon());
				oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
				oldVillage.setIsactive(true);
				oldVillage.setLastupdated(time);
				oldVillage.setLastupdatedby(roleCode);
				oldVillage.setLrReplaces(partContributedList.get(i).getLrReplaces());
				oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
				oldVillage.setMapCode(partContributedList.get(i).getMapCode());
				oldVillage.setSscode(partContributedList.get(i).getSscode());
				oldVillage.setVillageNameEnglish(partContributedList.get(i).getVillageNameEnglish());
				oldVillage.setVillageNameLocal(partContributedList.get(i).getVillageNameLocal());
				oldVillage.setVillageStatus(partContributedList.get(i).getVillageStatus());
				// oldVillage.setSubdistrict(sd);
				VillagePK oldvillagePK = new VillagePK(partContributedList.get(i).getVlc(), partContributedList.get(i).getVlc() + 1,partContributedList.get(i).getMinorVersion());
				oldVillage.setVillagePK(oldvillagePK);
				oldVillage.setFlagCode(flagCode);

				try {
					villageDAO.save(oldVillage);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				idLR += 1;
			}

		} else if (fullContributedList.size() >= 0 && partContributedList.size() >= 0 && createFromNewLand == false && createFromCoverageOfUrbanLocalBody == false) {
			if (fullContributedList.size() > 0 && partContributedList.size() == 0) {
				newVillageBean = new Village();
				villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
				if (villageCode == 0) {
					villageCode = 1;
				} else {
					villageCode = villageCode + 1;
				}
				newVillageBean.setAliasEnglish(villageForm.getNewVillageAliasEnglish());
				newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
				if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
					newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
				}
				newVillageBean.setCreatedby(roleCode);
				newVillageBean.setIsactive(true);
				newVillageBean.setLastupdatedby(roleCode);
				newVillageBean.setLrReplaces(null);
				newVillageBean.setLrReplacedby(null);
				newVillageBean.setMapCode(null);
				newVillageBean.setSscode(villageForm.getStateSpecificCode());
				newVillageBean.setLastupdated(time);
				newVillageBean.setCreatedon(time);
				newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
				newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
				newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
				newVillageBean.setVillageStatus(villageForm.getVillageType());
				VillagePK villagePK = new VillagePK(villageCode, 1,1);
				newVillageBean.setVillagePK(villagePK);
				newVillageBean.setFlagCode(flagCode);
				if (request != null) {
					this.uploadMapOrLat(villageForm, villageCode, request);
				}
				try {
					villageDAO.save(newVillageBean);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				this.insertSurveyNumber(villageForm, villageCode, 1);
				int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
				int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
				LandregionReplaces landregionReplacesBean = null;
				// putting the value for new village in the landregion replaces
				for (int i = 0; i < fullContributedList.size(); i++) {
					landregionReplacesBean = new LandregionReplaces();
					landregionReplacesBean.setId(id);
					landregionReplacesBean.setLrReplaces(lrReplaces);
					landregionReplacesBean.setEntityCode(fullContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityVersion(fullContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityType('V');
					id += 1;
					try {
						landRegionReplacesDAO.save(landregionReplacesBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
				}
				try {

					VillagePK villagePk = new VillagePK();
					villagePk.setVillageCode(villageCode);
					villagePk.setVillageVersion(1);
					villageDAO.updateLReplaces(lrReplaces, villagePk);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				oldVillage = null;

				lrReplacedby = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replacedby),1) from landregion_replacedby");
				idLR = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replacedby");
				LandregionReplacedbyBean = new LandregionReplacedby();
				LandregionReplacedbyBean.setId(idLR);
				LandregionReplacedbyBean.setLrReplacedby(lrReplacedby);
				LandregionReplacedbyBean.setEntityCode(villageCode);
				LandregionReplacedbyBean.setEntityVersion(1);
				LandregionReplacedbyBean.setEntityType('V');
				try {
					landRegionReplacedByDAO.save(LandregionReplacedbyBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}

				for (int i = 0; i < fullContributedList.size(); i++) {

					// deactivating the old village
					oldVillage = new Village();
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(fullContributedList.get(i).getVlc());
						villagePk.setVillageVersion(fullContributedList.get(i).getVlc());
						oldVillage.setIsactive(false);
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

				for (int i = 0; i < fullContributedList.size(); i++) {
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(fullContributedList.get(i).getVlc());
						villagePk.setVillageVersion(fullContributedList.get(i).getVlc());
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}

					oldVillage = new Village();
					// inserting old village with new version and with
					// replacedby field
					oldVillage.setAliasEnglish(fullContributedList.get(i).getAliasEnglish());
					oldVillage.setAliasLocal(fullContributedList.get(i).getAliasLocal());
					oldVillage.setCensus2001Code(fullContributedList.get(i).getCensus2001Code());
					oldVillage.setCensus2011Code(fullContributedList.get(i).getCensus2011Code());
					oldVillage.setCreatedby(fullContributedList.get(i).getCreatedby());
					oldVillage.setCreatedon(fullContributedList.get(i).getCreatedon());
					oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
					oldVillage.setIsactive(false);
					oldVillage.setLastupdated(time);
					oldVillage.setLastupdatedby(roleCode);
					oldVillage.setLrReplaces(fullContributedList.get(i).getLrReplaces());
					oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
					oldVillage.setMapCode(fullContributedList.get(i).getMapCode());
					oldVillage.setSscode(fullContributedList.get(i).getSscode());
					oldVillage.setVillageNameEnglish(fullContributedList.get(i).getVillageNameEnglish());
					oldVillage.setVillageNameLocal(fullContributedList.get(i).getVillageNameLocal());
					oldVillage.setVillageStatus(fullContributedList.get(i).getVillageStatus());
					VillagePK oldvillagePK = new VillagePK(fullContributedList.get(i).getVlc(), fullContributedList.get(i).getVlc() + 1,fullContributedList.get(i).getMinorVersion());
					oldVillage.setVillagePK(oldvillagePK);
					oldVillage.setFlagCode(flagCode);

					try {
						villageDAO.save(oldVillage);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

			} else if (fullContributedList.size() > 0 && partContributedList.size() > 0) {
				newVillageBean = new Village();
				villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
				if (villageCode == 0) {
					villageCode = 1;
				} else {
					villageCode = villageCode + 1;
				}
				newVillageBean.setAliasEnglish(villageForm.getNewVillageAliasEnglish());
				newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
				if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
					newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
				}
				newVillageBean.setCreatedby(roleCode);
				newVillageBean.setIsactive(true);
				newVillageBean.setLastupdatedby(roleCode);
				newVillageBean.setLrReplaces(null);
				newVillageBean.setLrReplacedby(null);
				newVillageBean.setMapCode(null);
				newVillageBean.setSscode(villageForm.getStateSpecificCode());
				newVillageBean.setLastupdated(time);
				newVillageBean.setCreatedon(time);
				newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
				newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
				newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
				newVillageBean.setVillageStatus(villageForm.getVillageType());
				VillagePK villagePK = new VillagePK(villageCode, 1,1);
				newVillageBean.setVillagePK(villagePK);
				newVillageBean.setFlagCode(flagCode);
				if (request != null) {
					this.uploadMapOrLat(villageForm, villageCode, request);
				}
				try {
					villageDAO.save(newVillageBean);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				this.insertSurveyNumber(villageForm, villageCode, 1);
				int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
				int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
				LandregionReplaces landregionReplacesBean = null;

				for (int i = 0; i < fullContributedList.size(); i++) {
					landregionReplacesBean = new LandregionReplaces();
					landregionReplacesBean.setId(id);
					landregionReplacesBean.setLrReplaces(lrReplaces);
					landregionReplacesBean.setEntityCode(fullContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityVersion(fullContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityType('V');
					id += 1;
					try {
						landRegionReplacesDAO.save(landregionReplacesBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
				}
				for (int i = 0; i < partContributedList.size(); i++) {
					landregionReplacesBean = new LandregionReplaces();
					landregionReplacesBean.setId(id);
					landregionReplacesBean.setLrReplaces(lrReplaces);
					landregionReplacesBean.setEntityCode(partContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityVersion(partContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityType('V');
					id += 1;
					try {
						landRegionReplacesDAO.save(landregionReplacesBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
				}
				try {

					VillagePK villagePk = new VillagePK();
					villagePk.setVillageCode(villageCode);
					villagePk.setVillageVersion(1);
					villageDAO.updateLReplaces(lrReplaces, villagePk);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				lrReplacedby = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replacedby),1) from landregion_replacedby");
				idLR = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replacedby");
				LandregionReplacedbyBean = new LandregionReplacedby();
				LandregionReplacedbyBean.setId(idLR);
				LandregionReplacedbyBean.setLrReplacedby(lrReplacedby);
				LandregionReplacedbyBean.setEntityCode(villageCode);
				LandregionReplacedbyBean.setEntityVersion(1);
				LandregionReplacedbyBean.setEntityType('V');
				try {
					landRegionReplacedByDAO.save(LandregionReplacedbyBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}

				for (int i = 0; i < fullContributedList.size(); i++) {

					// deactivating the old village
					oldVillage = new Village();
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(fullContributedList.get(i).getVlc());
						villagePk.setVillageVersion(fullContributedList.get(i).getVlc());
						oldVillage.setIsactive(false);
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

				for (int i = 0; i < fullContributedList.size(); i++) {
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(fullContributedList.get(i).getVlc());
						villagePk.setVillageVersion(fullContributedList.get(i).getVlc());
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}

					oldVillage = new Village();
					// inserting old village with new version and with
					// replacedby field
					oldVillage.setAliasEnglish(fullContributedList.get(i).getAliasEnglish());
					oldVillage.setAliasLocal(fullContributedList.get(i).getAliasLocal());
					oldVillage.setCensus2001Code(fullContributedList.get(i).getCensus2001Code());
					oldVillage.setCensus2011Code(fullContributedList.get(i).getCensus2011Code());
					oldVillage.setCreatedby(fullContributedList.get(i).getCreatedby());
					oldVillage.setCreatedon(fullContributedList.get(i).getCreatedon());
					oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
					oldVillage.setIsactive(false);
					oldVillage.setLastupdated(time);
					oldVillage.setLastupdatedby(roleCode);
					oldVillage.setLrReplaces(fullContributedList.get(i).getLrReplaces());
					oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
					oldVillage.setMapCode(fullContributedList.get(i).getMapCode());
					// oldVillage.setRemarks(fullContributedList.get(i).getRemarks());
					oldVillage.setSscode(fullContributedList.get(i).getSscode());
					oldVillage.setVillageNameEnglish(fullContributedList.get(i).getVillageNameEnglish());
					oldVillage.setVillageNameLocal(fullContributedList.get(i).getVillageNameLocal());
					oldVillage.setVillageStatus(fullContributedList.get(i).getVillageStatus());
					// oldVillage.setSubdistrict(sd);
					VillagePK oldvillagePK = new VillagePK(fullContributedList.get(i).getVlc(), fullContributedList.get(i).getVlc() + 1,fullContributedList.get(i).getMinorVersion());
					oldVillage.setVillagePK(oldvillagePK);
					oldVillage.setFlagCode(flagCode);

					try {
						villageDAO.save(oldVillage);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

				for (int i = 0; i < partContributedList.size(); i++) {
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(partContributedList.get(i).getVlc());
						villagePk.setVillageVersion(partContributedList.get(i).getVlc());
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}

					oldVillage = new Village();
					// inserting old village with new version and with
					// replacedby field
					oldVillage.setAliasEnglish(partContributedList.get(i).getAliasEnglish());
					oldVillage.setAliasLocal(partContributedList.get(i).getAliasLocal());
					oldVillage.setCensus2001Code(partContributedList.get(i).getCensus2001Code());
					oldVillage.setCensus2011Code(partContributedList.get(i).getCensus2011Code());
					oldVillage.setCreatedby(partContributedList.get(i).getCreatedby());
					oldVillage.setCreatedon(partContributedList.get(i).getCreatedon());
					oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
					oldVillage.setIsactive(true);
					oldVillage.setLastupdated(time);
					oldVillage.setLastupdatedby(roleCode);
					oldVillage.setLrReplaces(partContributedList.get(i).getLrReplaces());
					oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
					oldVillage.setMapCode(partContributedList.get(i).getMapCode());
					// oldVillage.setRemarks(partContributedList.get(i).getRemarks());
					oldVillage.setSscode(partContributedList.get(i).getSscode());
					oldVillage.setVillageNameEnglish(partContributedList.get(i).getVillageNameEnglish());
					oldVillage.setVillageNameLocal(partContributedList.get(i).getVillageNameLocal());
					oldVillage.setVillageStatus(partContributedList.get(i).getVillageStatus());
					VillagePK oldvillagePK = new VillagePK(partContributedList.get(i).getVlc(), partContributedList.get(i).getVlc() + 1,partContributedList.get(i).getMinorVersion());
					oldVillage.setVillagePK(oldvillagePK);
					oldVillage.setFlagCode(flagCode);

					try {
						villageDAO.save(oldVillage);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

			} else if (fullContributedList.size() == 0 && partContributedList.size() > 1) {
				newVillageBean = new Village();
				villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
				if (villageCode == 0) {
					villageCode = 1;
				} else {
					villageCode = villageCode + 1;
				}
				newVillageBean.setAliasEnglish(villageForm.getNewVillageAliasEnglish());
				newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
				if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
					newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
				}
				newVillageBean.setCreatedby(roleCode);
				newVillageBean.setIsactive(true);
				newVillageBean.setLastupdatedby(roleCode);
				newVillageBean.setLrReplaces(null);
				newVillageBean.setLrReplacedby(null);
				newVillageBean.setMapCode(null);
				newVillageBean.setSscode(villageForm.getStateSpecificCode());
				newVillageBean.setLastupdated(time);
				newVillageBean.setCreatedon(time);
				newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
				newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
				newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
				newVillageBean.setVillageStatus(villageForm.getVillageType());
				VillagePK villagePK = new VillagePK(villageCode, 1,1);
				newVillageBean.setVillagePK(villagePK);
				newVillageBean.setFlagCode(flagCode);
				if (request != null) {
					this.uploadMapOrLat(villageForm, villageCode, request);
				}
				try {
					villageDAO.save(newVillageBean);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				this.insertSurveyNumber(villageForm, villageCode, 1);
				int id = 0;
				id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
				int lrReplaces = 0;
				lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
				LandregionReplaces landregionReplacesBean = null;
				for (int i = 0; i < partContributedList.size(); i++) {
					landregionReplacesBean = new LandregionReplaces();
					landregionReplacesBean.setId(id);
					landregionReplacesBean.setLrReplaces(lrReplaces);
					landregionReplacesBean.setEntityCode(partContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityVersion(partContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityType('V');
					id += 1;
					try {
						landRegionReplacesDAO.save(landregionReplacesBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
				}
				try {
					VillagePK villagePk = new VillagePK();
					villagePk.setVillageCode(villageCode);
					villagePk.setVillageVersion(1);
					villageDAO.updateLReplaces(lrReplaces, villagePk);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				lrReplacedby = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replacedby),1) from landregion_replacedby");
				for (int i = 0; i < partContributedList.size(); i++) {

					idLR = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replacedby");
					LandregionReplacedbyBean = new LandregionReplacedby();
					LandregionReplacedbyBean.setId(idLR);
					LandregionReplacedbyBean.setLrReplacedby(lrReplacedby);
					LandregionReplacedbyBean.setEntityCode(villageCode);
					LandregionReplacedbyBean.setEntityVersion(1);
					LandregionReplacedbyBean.setEntityType('V');
					try {
						landRegionReplacedByDAO.save(LandregionReplacedbyBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(partContributedList.get(i).getVlc());
						villagePk.setVillageVersion(partContributedList.get(i).getVlc());
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
					oldVillage = new Village();
					oldVillage.setAliasEnglish(partContributedList.get(i).getAliasEnglish());
					oldVillage.setAliasLocal(partContributedList.get(i).getAliasLocal());
					oldVillage.setCensus2001Code(partContributedList.get(i).getCensus2001Code());
					oldVillage.setCensus2011Code(partContributedList.get(i).getCensus2011Code());
					oldVillage.setCreatedby(partContributedList.get(i).getCreatedby());
					oldVillage.setCreatedon(partContributedList.get(i).getCreatedon());
					oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
					oldVillage.setIsactive(true);
					oldVillage.setLastupdated(time);
					oldVillage.setLastupdatedby(roleCode);
					oldVillage.setLrReplaces(partContributedList.get(i).getLrReplaces());
					oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
					oldVillage.setMapCode(partContributedList.get(i).getMapCode());
					oldVillage.setSscode(partContributedList.get(i).getSscode());
					oldVillage.setVillageNameEnglish(partContributedList.get(i).getVillageNameEnglish());
					oldVillage.setVillageNameLocal(partContributedList.get(i).getVillageNameLocal());
					oldVillage.setVillageStatus(partContributedList.get(i).getVillageStatus());
					VillagePK oldvillagePK = new VillagePK(partContributedList.get(i).getVlc(), partContributedList.get(i).getVlc() + 1,partContributedList.get(i).getMinorVersion());
					oldVillage.setVillagePK(oldvillagePK);
					oldVillage.setFlagCode(flagCode);

					try {
						villageDAO.save(oldVillage);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
					idLR += 1;
				}

			}
		} else if (fullContributedList.size() == 0 && partContributedList.size() == 0 && createFromNewLand == true && createFromCoverageOfUrbanLocalBody == false) {
			newVillageBean = new Village();
			villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
			if (villageCode == 0) {
				villageCode = 1;
			} else {
				villageCode = villageCode + 1;
			}
			newVillageBean.setAliasEnglish(villageForm.getNewVillageAliasEnglish());
			newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
			if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
				newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
			}
			newVillageBean.setCreatedby(roleCode);
			newVillageBean.setIsactive(true);
			newVillageBean.setLastupdatedby(roleCode);
			newVillageBean.setLrReplaces(null);
			newVillageBean.setLrReplacedby(null);
			newVillageBean.setMapCode(null);
			newVillageBean.setSscode(villageForm.getStateSpecificCode());
			newVillageBean.setLastupdated(time);
			newVillageBean.setCreatedon(time);
			newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
			newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
			newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
			newVillageBean.setVillageStatus(villageForm.getVillageType());
			VillagePK villagePK = new VillagePK(villageCode, 1,1);
			newVillageBean.setVillagePK(villagePK);
			newVillageBean.setFlagCode(flagCode);
			if (request != null) {
				this.uploadMapOrLat(villageForm, villageCode, request);
			}
			try {
				villageDAO.save(newVillageBean);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			this.insertSurveyNumber(villageForm, villageCode, 1);
		} else if (partContributedList.size() == 0 && fullContributedList.size() == 0 && createFromNewLand == false && createFromCoverageOfUrbanLocalBody == true) {
			newVillageBean = new Village();
			villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
			if (villageCode == 0) {
				villageCode = 1;
			} else {
				villageCode = villageCode + 1;
			}
			newVillageBean.setAliasEnglish(villageForm.getNewVillageAliasEnglish());
			newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
			if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
				newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
			}
			newVillageBean.setCreatedby(roleCode);
			newVillageBean.setIsactive(true);
			newVillageBean.setLastupdatedby(roleCode);
			newVillageBean.setLrReplaces(null);
			newVillageBean.setLrReplacedby(null);
			newVillageBean.setMapCode(null);
			newVillageBean.setSscode(villageForm.getStateSpecificCode());
			newVillageBean.setLastupdated(time);
			newVillageBean.setCreatedon(time);
			newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
			newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
			newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
			newVillageBean.setVillageStatus(villageForm.getVillageType());
			VillagePK villagePK = new VillagePK(villageCode, 1,1);
			newVillageBean.setVillagePK(villagePK);
			newVillageBean.setFlagCode(flagCode);
			if (request != null) {
				this.uploadMapOrLat(villageForm, villageCode, request);
			}
			try {
				villageDAO.save(newVillageBean);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			this.insertSurveyNumber(villageForm, villageCode, 1);
			int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
			int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
			LandregionReplaces landregionReplacesBean = null;

			for (int i = 0; i < fullSubdistrictContributingList.size(); i++) {
				landregionReplacesBean = new LandregionReplaces();
				landregionReplacesBean.setId(id);
				landregionReplacesBean.setLrReplaces(lrReplaces);
				landregionReplacesBean.setEntityCode(fullSubdistrictContributingList.get(i).getTlc());
				landregionReplacesBean.setEntityVersion(fullSubdistrictContributingList.get(i).getTlc());
				landregionReplacesBean.setEntityType('T');
				id += 1;
				try {
					landRegionReplacesDAO.save(landregionReplacesBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}
			}
			for (int i = 0; i < partContributedList.size(); i++) {
				landregionReplacesBean = new LandregionReplaces();
				landregionReplacesBean.setId(id);
				landregionReplacesBean.setLrReplaces(lrReplaces);
				landregionReplacesBean.setEntityCode(partSubdistrictContributingList.get(i).getTlc());
				landregionReplacesBean.setEntityVersion(partSubdistrictContributingList.get(i).getTlc());
				landregionReplacesBean.setEntityType('T');
				id += 1;
				try {
					landRegionReplacesDAO.save(landregionReplacesBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}
			}

			try {

				VillagePK villagePk = new VillagePK();
				villagePk.setVillageCode(villageCode);
				villagePk.setVillageVersion(1);
				villageDAO.updateLReplaces(lrReplaces, villagePk);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

		}
		govtOrderService.saveGovtOrder(villageForm.getOrderNo(), villageForm.getOrderDate(), villageForm.getEffectiveDate(), villageForm.getGazPubDate(), "save", villageForm.getOrderPath(), "", request);

		villageDAO.SetGovermentOrderEntity(Integer.toString(villageCode), 'V');
		return true;

	}

	@SuppressWarnings("null")
	@Override
	public boolean modifyVillageCrInfo(VillageForm villageForm, HttpServletRequest request, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, boolean delflag, String deleteAttachmentId[], HttpSession httpSession)
			throws Exception {

		Session session1 = sessionFactory.openSession();
		Transaction tx1 = session1.beginTransaction();
		boolean result = false;
		try {
			List<VillageDataForm> listVillage = villageForm.getListVillageDetails();
			if (!listVillage.isEmpty()) {
				VillageDataForm element = listVillage.get(0);
				
				
				
				
				
				Query query = session1.createSQLQuery("select * from change_village_basic_details_fn(:villageCode,:userId,:aliasEnglish,:aliasLocal,:sscode,:order_no,:order_date,:effectiveDate,:gaz_pubdate,:govtOrderConfig)");
				 query.setParameter("villageCode"	    ,element.getVillageCode()		  , Hibernate.INTEGER)
					.setParameter("userId"	    	,userId				  				  , Hibernate.INTEGER)
					.setParameter("aliasEnglish"  	,element.getAliasEnglish()			  , Hibernate.STRING)
					.setParameter("aliasLocal"		,element.getAliasLocal()			  , Hibernate.STRING)
					.setParameter("sscode"			,element.getSscode()				  , Hibernate.STRING)
					.setParameter("order_no"		,element.getOrderNocr()				  , Hibernate.STRING)
					.setParameter("order_date"		,element.getOrderDatecr()			  , Hibernate.TIMESTAMP)
					.setParameter("effectiveDate"	,element.getOrdereffectiveDatecr()	  , Hibernate.TIMESTAMP)
					.setParameter("gaz_pubdate"		,element.getGazPubDatecr()			  , Hibernate.TIMESTAMP)
					.setParameter("govtOrderConfig" ,"upload"			                  , Hibernate.STRING);
				String returnedValue = (String) query.uniqueResult();
				tx1.commit();
				if (returnedValue != null) {
					String[] ldata = returnedValue.split(",");
					String orderCode = ldata[0];
					String tid = ldata[1];
					
				
					

				int Transactionid = Integer.parseInt(tid);
				int ocode = Integer.parseInt(orderCode);
				
				GovernmentOrder govtOrder=(GovernmentOrder)session1.get(GovernmentOrder.class, ocode);
				
				
				Integer fileCount=villageForm.getGovtfilecount()!=null && villageForm.getGovtfilecount().length()>0 ? Integer.parseInt(villageForm.getGovtfilecount()):0;
				if(fileCount==1) {
					
					
					Criteria criteria = session1.createCriteria(GovernmentOrderEntityWiseNew.class);
				
					criteria.add( Restrictions.eq("entityCode", element.getVillageCode()));
					criteria.add( Restrictions.eq("entityType","V" ));
					criteria.add( Restrictions.eq("entityVersion",element.getVillage_version() ));
					criteria.add( Restrictions.eq("minorVersion", element.getMinorVersion()));
					List<Attachment> attachmentList= null;
					List<GovernmentOrderEntityWiseNew> governmentOrderEntityWiseNewList = criteria.list();
					if(governmentOrderEntityWiseNewList!=null && !governmentOrderEntityWiseNewList.isEmpty()) {
						int oldorderCode=governmentOrderEntityWiseNewList.get(0).getOrderCode();
						attachmentList=villageDAO.getAttacmentdetail(oldorderCode);
						if(attachmentList!=null && !attachmentList.isEmpty()) {
							Attachment attachment=attachmentList.get(0);
							attachment.setGovernmentOrder(govtOrder);
							session1.save(attachment);
							session1.flush();
							session1.clear();
							
						}
					}
					
				}else {
					if (deleteAttachmentId != null) {
						govtOrderDAO.deleteAttachmentForLandRegion(deleteAttachmentId, session1);

					}
					
					

					if (attachedList != null) {
						this.saveDataInAttachment(govtOrder, attachedList, session1);
					}
				}
			
				result = true;
				

			
				

			}
			
		}
		} catch (Exception e) {
			tx1.rollback();
			throw new Exception(e);
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
				
			}
			httpSession.removeAttribute("govtdetailsexit");
		}
		return result;

	}

	@SuppressWarnings("null")
	@Override
	public boolean modifyVillageCrInfo(VillageForm villageForm, HttpServletRequest request, HttpSession httpSession) throws Exception {
		Session session1 = null;
		Transaction tx1 = null;
		session1 = sessionFactory.openSession();
		tx1 = session1.beginTransaction();
		try {
			// correction in village detail
			Village villageBean = new Village();
			List<VillageDataForm> listVillage = new ArrayList<VillageDataForm>();
			listVillage = villageForm.getListVillageDetails();
			Iterator<VillageDataForm> itr = listVillage.iterator();
			while (itr.hasNext()) {
				VillageDataForm element = (VillageDataForm) itr.next();
				VillagePK villagePk = new VillagePK();
				villagePk.setVillageCode(element.getVillageCode());
				villagePk.setVillageVersion(element.getVillage_version());
				villageBean.setMapCode(element.getMapCode());
				villageBean.setIsactive(true);
				villageDAO.modifyVillageInfo(villageForm, villagePk, 1, session1, httpSession);
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
		return true;

	}

	// modify Village Change Upload
	/* Add some changes for Rename Draft Village on 10-03-2015 by Ripunj*/
	@SuppressWarnings("null")
	@Override
	public synchronized boolean changeVillage(VillageForm villageForm, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request, int uid) throws Exception {
		char t_type = 'V';
		int Transactionid = 0;
		String govtOrderConfig = "";
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int villageVersion = 0;
			List<VillageDataForm> listVillages = new ArrayList<VillageDataForm>();
			listVillages = villageForm.getListVillageDetails();
			Iterator<VillageDataForm> itr = listVillages.iterator();
			boolean result = false;
			int orderCodeForVilGovt=0;
			if(villageForm.getOrderCode()!=null && !villageForm.getOrderCode().equals("")){
				orderCodeForVilGovt=villageForm.getOrderCode();
			}
			if((villageForm.getGovtOrderConfig() != null) && !(("").equals(villageForm.getGovtOrderConfig())))
				govtOrderConfig = villageForm.getGovtOrderConfig();
			while (itr.hasNext()) {

				VillageDataForm element = (VillageDataForm) itr.next();
				villageVersion = villageDAO.getMaxVillageVersionbyCode(element.getVillageCode());
				if (villageVersion == 1) {
					villageVersion = villageVersion + 1;
				} else {
					villageVersion = villageVersion + 1;
				}
				String ordercode = villageDAO.ChangeVillage(element.getVillageCode(), uid, element.getVillageNameEnglishCh(), villageForm.getEffectiveDate(), element.getVillageNameLocalCh(), element.getAliasEnglishCh(), element.getAliasLocalCh(),
						session, villageForm.getOrderNo(), villageForm.getOrderDate(), villageForm.getGazPubDate(),villageForm.getButtonClicked(),villageForm.getIsExistingGovtOrder(),orderCodeForVilGovt,govtOrderConfig);
				
				if(villageForm.getButtonClicked().equals('P')){
					if (ordercode != null) {
						String[] ldata = ordercode.split(",");
						String orderCode = ldata[0];
						String tid = ldata[1];
	
						Transactionid = Integer.parseInt(tid);
						int ocode = Integer.parseInt(orderCode);
						if("N".equals(villageForm.getIsExistingGovtOrder())){
							GovernmentOrder govtOrder = new GovernmentOrder();
							govtOrder.setOrderDate(villageForm.getOrderDate());
							govtOrder.setEffectiveDate(villageForm.getEffectiveDate());
							govtOrder.setGazPubDate(villageForm.getGazPubDate());
							govtOrder.setCreatedon(new Date());
							govtOrder.setDescription("LGD DETAILS");
							govtOrder.setIssuedBy("GOVERNOR");
							govtOrder.setCreatedby(createdBy);
							govtOrder.setLastupdated(new Date());
							govtOrder.setLastupdatedby(createdBy);
							govtOrder.setLevel("U");
							govtOrder.setOrderNo(villageForm.getOrderNo());
							govtOrder.setStatus('A');
							govtOrder.setUserId(userId);
							govtOrder.setOrderCode(ocode);
							govtOrder.setOrderPath(govtOrderConfig);
							convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm, attachedList, session);
						}
						tx.commit();
						result = true;
						EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
						est.start();
					}
				}else{
					villageForm.setRenameVillageCode(Integer.parseInt(ordercode));
					tx.commit();
					result = true;
				}
			}
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			return false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				
			}	
		}

	}
	/* Add some changes for Rename Draft Village on 10-03-2015 by Ripunj*/
	@SuppressWarnings("null")
	@Override
	public synchronized boolean changeVillagegenerate(VillageForm villageForm, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request, int uid, HttpSession httpSession) throws Exception {
		int Transactionid = 0;
		char t_type = 'V';
		String govtOrderConfig = "";
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int villageVersion = 0;
			List<VillageDataForm> listVillages = new ArrayList<VillageDataForm>();
			listVillages = villageForm.getListVillageDetails();
			Iterator<VillageDataForm> itr = listVillages.iterator();
			boolean result = false;
			int orderCodeForVilGovt=0;
			if(villageForm.getOrderCode()!=null && !villageForm.getOrderCode().equals("")){
				orderCodeForVilGovt=villageForm.getOrderCode();
			}
			if((villageForm.getGovtOrderConfig() != null) && !(("").equals(villageForm.getGovtOrderConfig())))
				govtOrderConfig = villageForm.getGovtOrderConfig();
			while (itr.hasNext()) {

				VillageDataForm element = (VillageDataForm) itr.next();
				villageVersion = villageDAO.getMaxVillageVersionbyCode(element.getVillageCode());
				if (villageVersion == 1) {
					villageVersion = villageVersion + 1;
				} else {
					villageVersion = villageVersion + 1;
				}
				String ordercode = villageDAO.ChangeVillage(element.getVillageCode(), uid, element.getVillageNameEnglishCh(), villageForm.getEffectiveDate(), element.getVillageNameLocalCh(), element.getAliasEnglishCh(), element.getAliasLocalCh(),
						session, villageForm.getOrderNo(), villageForm.getOrderDate(), villageForm.getGazPubDate(),'P',"N",orderCodeForVilGovt,govtOrderConfig);
				if (ordercode != null) {
					String[] ldata = ordercode.split(",");
					String orderCode = ldata[0];
					String tid = ldata[1];
					Transactionid = Integer.parseInt(tid);
					int ocode = Integer.parseInt(orderCode);
					govtOrderService.saveDataInAttachment(govtOrderForm, null, httpSession, ocode, session);
					tx.commit();
					result = true;
					EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
					est.start();

				}
			}
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			return false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}

	}
	/* Add some changes for Rename Draft Village on 10-03-2015 by Ripunj*/
	public boolean changeVillageforTemplate(VillageForm villageForm, GovernmentOrderForm govtOrderForm, HttpServletRequest request) throws Exception {
		String govtOrderConfig ="";
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int villageVersion = 0;
			List<VillageDataForm> listVillage = new ArrayList<VillageDataForm>();
			listVillage = villageForm.getListVillageDetails();
			Iterator<VillageDataForm> itr = listVillage.iterator();
			int orderCodeForVilGovt=0;
			if(villageForm.getOrderCode()!=null && !villageForm.getOrderCode().equals("")){
				orderCodeForVilGovt=villageForm.getOrderCode();
			}
			if((villageForm.getGovtOrderConfig() != null) && !("").equals(villageForm.getGovtOrderConfig()))
				govtOrderConfig = villageForm.getGovtOrderConfig();
			while (itr.hasNext()) {

				VillageDataForm element = (VillageDataForm) itr.next();
				villageVersion = villageDAO.getMaxVillageVersionbyCode(element.getVillageCode());
				if (villageVersion == 1) {
					villageVersion = villageVersion + 1;
				} else {
					villageVersion = villageVersion + 1;
				}

				String villageCode = Integer.toString(element.getVillageCode());
				villageDAO.ChangeVillage(element.getVillageCode(), 555, element.getVillageNameEnglishCh(), villageForm.getEffectiveDate(), element.getVillageNameLocalCh(), element.getAliasEnglishCh(), element.getAliasLocalCh(), session,
						villageForm.getOrderNo(), villageForm.getOrderDate(), villageForm.getGazPubDate(),'P',"N",orderCodeForVilGovt,govtOrderConfig);
				GovernmentOrder govtOrder = convertLocalbodyService.saveDataInGovtOrder(govtOrderForm, session);
				shiftService.saveDataInGovtOrderEntityWise(govtOrder, villageCode, 'V', session);

			}
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}
	}
	/* Add some changes for Rename Draft Village on 10-03-2015 by Ripunj*/
	// Modify Village by @vanisha
	@SuppressWarnings("null")
	@Override
	public boolean modifyVillageInfo(VillageForm villageForm, HttpServletRequest request, HttpSession httpSession) throws Exception {
		Session session1 = null;
		Transaction tx1 = null;
		session1 = sessionFactory.openSession();
		tx1 = session1.beginTransaction();
		String govtOrderConfig ="";
		try {
			if (villageForm.isCorrection() == true) {
				Village villageBean = new Village();
				List<VillageDataForm> listVillage = new ArrayList<VillageDataForm>();
				listVillage = villageForm.getListVillageDetails();
				Iterator<VillageDataForm> itr = listVillage.iterator();
				while (itr.hasNext()) {
					VillageDataForm element = (VillageDataForm) itr.next();

					try {
						Timestamp time = CurrentDateTime.getCurrentTimestamp();
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(element.getVillageCode());
						villagePk.setVillageVersion(element.getVillage_version());
						int mapCode = 0;
						villageBean.setMapCode(element.getMapCode());
						villageBean.setIsactive(true);
						if (element.getMapCode() == null) {
							mapCode = 0;
						} else {
							mapCode = element.getMapCode();
						}
						String cord1 = null;
						if (villageForm.getLati().split(",").length > 1) {
							String[] tempLati = villageForm.getLati().split(",");
							String[] tempLongi = villageForm.getLongi().split(",");
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
							map_landRegionCode = villageDAO.getMaxVillageCode("select COALESCE(max(map_landregion_code),1) from map_landregion");
							if (map_landRegionCode == 0) {
								map_landRegionCode = 1;
							} else {
								map_landRegionCode = map_landRegionCode + 1;
							}
							String cord = null;
							if (villageForm.getLati().split(",").length > 1) {
								String[] tempLati = villageForm.getLati().split(",");
								String[] tempLongi = villageForm.getLongi().split(",");
								cord = tempLati[0] + "," + tempLongi[0] + ":";
								if (tempLati.length > 1) {
									for (int i = 1; i < tempLati.length; i++) {
										cord += tempLati[i] + "," + tempLongi[i] + ":";
									}
								}
								cord = cord.substring(0, cord.length() - 1);
							}

							MapLandRegion mapLandRegion = null;
							mapLandRegion = new MapLandRegion();
							mapLandRegion.setEffectiveDate(CurrentDateTime.getDate("2011-12-12"));
							mapLandRegion.setCreatedon(time);
							mapLandRegion.setCreatedby(roleCode);
							mapLandRegion.setLandregionType('V');
							mapLandRegion.setImagePath(element.getImagePath());
							mapLandRegion.setLandregionVersion(element.getVillage_version());
							mapLandRegion.setCoordinates(cord);
							mapLandRegion.setMapLandregionCode(map_landRegionCode);
							mapLandRegion.setLandregionCode(element.getVillageCode());
							mapLandRegion.setWarningflag(true);
							// Save
							mapLandRegionDAO.saveMap(mapLandRegion, session1);
							villageBean.setMapCode(map_landRegionCode);
							villageDAO.modifyVillageInfo(villageForm, villagePk, map_landRegionCode, session1, httpSession);

						} else {
							// update
							villageDAO.updateMapLanRegion(mapCode, cord1, element.getVillageCode(), session1);

							villageDAO.modifyVillageInfo(villageForm, villagePk, mapCode, session1, httpSession);
						}
						govtOrderService.updateGovernmentOrder(element.getOrderNocr(), element.getOrderCodecr(), element.getOrderDatecr(), element.getOrdereffectiveDatecr(), element.getGazPubDatecr(), "update", element.getOrderPath(),
								villageForm.getFilePathcr(), request);

					} catch (Exception e) {
						log.debug("Exception" + e);
						return false;

					}
				}
			} else if (villageForm.isCorrection() == false) {
				try {
					int villageVersion = 0;
					List<VillageDataForm> listVillage = new ArrayList<VillageDataForm>();
					listVillage = villageForm.getListVillageDetails();
					Iterator<VillageDataForm> itr = listVillage.iterator();
					int orderCodeForVilGovt=0;
					if(villageForm.getOrderCode()!=null && !villageForm.getOrderCode().equals("")){
						orderCodeForVilGovt=villageForm.getOrderCode();
					}
					if((villageForm.getGovtOrderConfig() != null) && !(("").equals(villageForm.getGovtOrderConfig())))
						govtOrderConfig = villageForm.getGovtOrderConfig();
					while (itr.hasNext()) {

						VillageDataForm element = (VillageDataForm) itr.next();
						villageVersion = villageDAO.getMaxVillageVersionbyCode(element.getVillageCode());
						if (villageVersion == 1) {
							villageVersion = villageVersion + 1;
						} else {
							villageVersion = villageVersion + 1;
						}

						String villageCode = Integer.toString(element.getVillageCode());
						// Save Village Detail
						villageDAO.ChangeVillage(element.getVillageCode(), 555, element.getVillageNameEnglishCh(), villageForm.getEffectiveDate(), element.getVillageNameLocalCh(), element.getAliasEnglishCh(), element.getAliasLocalCh(), session1,
								villageForm.getOrderNo(), villageForm.getOrderDate(), villageForm.getGazPubDate(),'P',"N",orderCodeForVilGovt,govtOrderConfig);
						// Save Government Order
						List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
						AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
						addAttachmentBean.setCurrentlyUploadFileList(villageForm.getAttachedFile());
						addAttachmentBean.setUploadLocation(villageForm.getUploadLocation());
						addAttachmentBean.setAllowedTotalNoOfAttachment(villageForm.getAllowedNoOfAttachment());
						addAttachmentBean.setAllowedTotalFileSize(villageForm.getAllowedTotalFileSize());
						addAttachmentBean.setAllowedIndividualFileSize(villageForm.getAllowedIndividualFileSize());
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
						GovernmentOrder govtOrder = saveDataInGovtOrder(villageForm, session1);
						saveDataInAttachment(govtOrder, villageForm, metaInfoOfToBeAttachedFileList, session1);
						shiftService.saveDataInGovtOrderEntityWise(govtOrder, villageCode, 'V', session1);

					}
				} catch (Exception e) {
					log.debug("Exception" + e);
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
		return true;

	}

	@Override
	public boolean modify(VillageForm villageForm, HttpServletRequest request, HttpSession httpSession) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		session = sessionFactory.openSession();
		try {
			Village sdbean = null;
			sdbean = new Village();

			List<VillageDataForm> assemblyDataForm = new ArrayList<VillageDataForm>();
			assemblyDataForm = villageForm.getListVillageDetails();

			Iterator<VillageDataForm> itr = assemblyDataForm.iterator();
			while (itr.hasNext()) {
				VillageDataForm element = (VillageDataForm) itr.next();
				int pcVersion = 1;
				pcVersion = subdistrictDAO.getMaxStandardcodesVersion(element.getVillageCode());
				if (pcVersion == 1) {
					pcVersion = pcVersion + 1;
				} else {
					pcVersion = pcVersion + 1;
				}
				VillagePK VillagePK = new VillagePK(element.getVillageCode(), pcVersion,1);

				sdbean.setVillagePK(VillagePK);
				sdbean.setVlc(pcVersion);
				sdbean.setVillageNameEnglish(element.getVillageNameEnglish().trim());
				sdbean.setCensus2001Code(element.getCensus2001Code());
				sdbean.setVillageNameLocal(element.getVillageNameLocal());
				sdbean.setVillageCode(element.getVillageCode());
				villageDAO.update(sdbean, session);
				char StandardCode = 'V';
				subdistrictDAO.ChangeStandardCode(StandardCode, element.getVillageCode(), element.getCensus2001Code(), null, element.getSscode());
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return true;

	}

	/*@Override
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode, Session session) throws Exception {
		List<MapLandRegion> mapDetail = villageDAO.getMapDetailsModify(mapLandregionCode, session);
		return mapDetail;

	}*/

	public List<Village> getVillageList(int villageCode) throws Exception {
		return villageDAO.getVillageList(villageCode);
	}

	public List<Village> getVillageListConctSDName(int subDistrictCode, String name) throws Exception {
		List<Village> villList = new ArrayList<Village>();
		villList = villageDAO.getVillageListbySubDistrictCode(subDistrictCode);
		for (int i = 0; i < villList.size(); i++) {
			villList.get(i).setVillageNameEnglish(villList.get(i).getVillageNameEnglish() + " (" + name + ")");
		}
		return villList;
	}

	public List<VillagePartsBySurveyno> getSurveyList(int villageCode) throws Exception {
		return villageDAO.getSurveyListbyVillageCode(villageCode);
	}

	@Override
	public List<VillageDataForm> getVillageDetailsModify(int villageCode) throws Exception {

		List<VillageDataForm> listVillageDetails = new ArrayList<VillageDataForm>();
		Session session1 = sessionFactory.openSession();
		try {

			List<Village> listVillageDetail = villageDAO.getVillageDetailsModify(villageCode, session1);
			VillageDataForm villageDataForm = new VillageDataForm();

			Integer villageversion = null;
			Integer minorVersion = null;

			if (!listVillageDetail.isEmpty()) {
				Village element = listVillageDetail.get(0);

				villageversion = element.getVillageVersion();
				minorVersion=element.getMinorVersion();
				villageDataForm.setMinorVersion(minorVersion);
				villageDataForm.setAliasEnglish(element.getAliasEnglish());
				villageDataForm.setAliasEnglishCh(element.getAliasEnglish());
				villageDataForm.setAliasLocal(element.getAliasLocal());
				villageDataForm.setAliasLocalCh(element.getAliasLocal());
				villageDataForm.setCensus2001Code(element.getCensus2001Code());
				if (element.getSscode() != null){
					villageDataForm.setSscode(element.getSscode().trim());
				}	
				villageDataForm.setVillageCode(element.getVlc());
				villageDataForm.setVillage_version(element.getVlc());
				villageDataForm.setVillageNameEnglish(element.getVillageNameEnglish());
				villageDataForm.setVillageNameEnglishCh(element.getVillageNameEnglish());
				villageDataForm.setVillageNameLocal(element.getVillageNameLocal());
				villageDataForm.setVillageNameLocalCh(element.getVillageNameLocal());
				villageDataForm.setCensus2011Code(element.getCensus2011Code());
				String Cordinate = null;
				if (element.getCoordinate() != null) {
					if (!element.getCoordinate().trim().equals("")){
						Cordinate = element.getCoordinate();
					}	
				}
				villageDataForm.setCordinate(Cordinate);
				villageDataForm.setLrReplacedby(element.getLrReplacedby());
				villageDataForm.setLrReplaces(element.getLrReplaces());
				villageDataForm.setMapCode(element.getMapCode());
				villageDataForm.setSubdistrictCode(element.getTlc());
				villageDataForm.setVillage_version(element.getVillageVersion());
				villageDataForm.setVillStat(element.getVillageStatus());
				villageDataForm.setVillageStatus(element.getVillageStatus());
				villageDataForm.setWarningflag(element.getWarningFlag());
				villageDataForm.setEffectiveDate(element.getEffectiveDate());
				villageDataForm.setOrdereffectiveDatecr(element.getEffectiveDate());
				villageDataForm.setOrdereffectiveDate(element.getEffectiveDate());
			}

			List<GetGovernmentOrderDetail> govtOrderValue = villageDAO.GovOrderDetail('V', villageCode, villageversion,minorVersion, session1);
			if (govtOrderValue != null && !govtOrderValue.isEmpty()) {
				GetGovernmentOrderDetail governmentOrder = govtOrderValue.get(0);

				villageDataForm.setOrderCodecr(governmentOrder.getOrderCode());
				villageDataForm.setOrderNocr(governmentOrder.getOrderNo().trim());
				villageDataForm.setOrderDatecr(governmentOrder.getOrderDate());
				villageDataForm.setGazPubDatecr(governmentOrder.getGazPubDate());
				villageDataForm.setOrderNo(governmentOrder.getOrderNo());
				villageDataForm.setOrderDate(governmentOrder.getOrderDate());
				villageDataForm.setGazPubDate(governmentOrder.getGazPubDate());

			}

			listVillageDetails.add(villageDataForm);
		} catch (Exception e) {
			throw new Exception(e);

		} finally {
			if (session1.isOpen() && session1 != null){
				session1.close();
			}	
		}

		return listVillageDetails;
	}

	/**
	 *  To retrieve the Draft Village Details in manage Form.
	 */
	/* Add some changes for Rename Draft Village on 10-03-2015 by Ripunj*/
	@Override
	public List<VillageDataForm> getVillageDraftDetailsModify(int villageCode) throws Exception {

		List<VillageDataForm> listVillageDetails = new ArrayList<VillageDataForm>();
		
		try {

			List<VillageDraft> listVillageDetail = villageDAO.getVillageDraftDetails(villageCode);
			VillageDataForm villageDataForm = new VillageDataForm();

			Integer villageversion = null;

			if (!listVillageDetail.isEmpty()) {
				VillageDraft element = listVillageDetail.get(0);
				
				//villageversion = element.getVillageVersion();
				villageDataForm.setDistrictNameEnglish(element.getDistrictCode().toString());
				villageDataForm.setSubdistrictNameEnglish(element.getSubDistrict().toString());
				villageDataForm.setAliasEnglish(element.getVillageNameAliasEn());
				villageDataForm.setAliasEnglishCh(element.getVillageNameAliasEn());
				villageDataForm.setAliasLocal(element.getVillageNameAliasLocal());
				villageDataForm.setAliasLocalCh(element.getVillageNameAliasLocal());
				villageDataForm.setCensus2011Code(element.getCensus());
				if (element.getSsCode() != null){
					villageDataForm.setSscode(element.getSsCode().trim());
				}	
				villageDataForm.setVillageCode(element.getVillageCode());
				villageDataForm.setVillageNameEnglish(element.getVillageNameEnglish());
				villageDataForm.setVillageNameEnglishCh(element.getVillageNameEnglish());
				villageDataForm.setVillageNameLocal(element.getVillageNameLocale());
				villageDataForm.setVillageNameLocalCh(element.getVillageNameLocale());
				villageDataForm.setVillageStatus(element.getVillageType());
				villageDataForm.setUpLoadMap(element.getUpLoadMap());
				
				villageDataForm.setOrderNocr(element.getOrderNo().toString());
				villageDataForm.setSubdistrictCode(element.getSubDistrict());
				villageDataForm.setOrderDatecr(element.getOrdertimeStampDate());
				villageDataForm.setOrdereffectiveDatecr(element.getEffectivetimeStampDate());
				villageDataForm.setGazPubDatecr(element.getGazPubtimeStampDate());
				villageDataForm.setOrderNo(element.getOrderNo().toString());
				villageDataForm.setGovtOrder(element.getGovtOrder());
				villageDataForm.setOrderDate(element.getOrdertimeStampDate());
				villageDataForm.setOrdereffectiveDate(element.getEffectivetimeStampDate());
				villageDataForm.setGazPubDate(element.getGazPubtimeStampDate());
				villageDataForm.setIsExistGovtOrder(element.getIsExistGovtOrder());
				villageDataForm.setRenameVillageCode(element.getVillage_rename_code());
				villageDataForm.setOrderCode(element.getOrdercode());
				if(element.getUlbCodeValid()!=null  && !element.getUlbCodeValid().equalsIgnoreCase("")){
					villageDataForm.setExistVilOrUlbFlag("U");
				}else if(element.getWithoutRenameNameVillageList()!=null || element.getFullContributedVillage() !=null || element.getRenameIdVillageList()!=null){
					villageDataForm.setExistVilOrUlbFlag("V");
				}else {
					villageDataForm.setExistVilOrUlbFlag("");
				}
				villageDataForm.setCordinate(element.getGisNodes());
				villageDataForm.setInvalidateVillageList(element.getInvalidateVillageList());
				/*Modified by pooja on 04-08-2015*/
				String ulbCodeValid = element.getUlbCodeValid();
				if (ulbCodeValid != null && ulbCodeValid.length() > 0) {
					villageDataForm.setUlbCodeValid(ulbCodeValid);
				} else {
					villageDataForm.setUlbCodeValid("");
				}
				
				villageDataForm.setIsPesa(element.getIsPesa());
			}
				listVillageDetails.add(villageDataForm);
		} catch (Exception e) {
			throw new Exception(e);

		} finally {
			/*if (session1.isOpen() && session1 != null){
				session1.close();
			}	*/
		}

		return listVillageDetails;
	}
	
	@Override
	public List getSubType() throws Exception {
		List listVillageDetails = villageDAO.getSubType();
		return listVillageDetails;
	}

	@Override
	public List getDesignation() throws Exception {
		List listVillageDetails = villageDAO.getDesignation();
		return listVillageDetails;
	}

	@Override
	public List getSubTier() throws Exception {
		List listVillageDetails = villageDAO.getSubTier();
		return listVillageDetails;
	}

	// @Author - Sarvapriya Malhtora
	@Override
	public String saveAsDraft(VillageForm addVillageNew) throws Exception {
		String xmlPath = null;
		String description = null;
		Date timestamp = DatePicker.getDate(new Date().toString());
		try {
			in.nic.pes.lgd.ws.VillageForm villageForm = new in.nic.pes.lgd.ws.VillageForm();
			LandDetails landDetails = new LandDetails();
			Parents parents = new Parents();
			Parent parent = new Parent();
			parent.setParentCode(Integer.parseInt(addVillageNew.getDistrictNameEnglish()));
			parents.getParent().add(parent);
			parent = new Parent();
			parent.setParentCode(Integer.parseInt(addVillageNew.getSubdistrictNameEnglish()));
			parents.getParent().add(parent);
			landDetails.setParents(parents);
			landDetails.setNameEnglish(addVillageNew.getNewVillageNameEnglish());
			landDetails.setNameLocal(addVillageNew.getNewVillageNameLocal());
			landDetails.setAliasEnglish(addVillageNew.getNewVillageNameEnglish());
			landDetails.setAliasLocal(addVillageNew.getNewVillageAliasLocal());
			if (addVillageNew.getCensus2011Code() != null && addVillageNew.getCensus2011Code().trim().length() > 0) {
				landDetails.setCensus2011Code(addVillageNew.getCensus2011Code());
			}
			landDetails.setSSCode(addVillageNew.getStateSpecificCode());

			SurveyNumbers surveyNumbers = new SurveyNumbers();
			if (addVillageNew.getSurveyNumber() != null){
				for (int i = 0; i < addVillageNew.getSurveyNumber().length; i++)
					surveyNumbers.getSurveyNumber().add(addVillageNew.getSurveyNumber()[i]);
			landDetails.setSurveyNumbers(surveyNumbers);
			}
			GISInformation gisInformation = new GISInformation();
			GISNodes gisNodes = new GISNodes();
			if (!addVillageNew.getLongitude().equals("") & !addVillageNew.getLatitude().equals("")) {
				String[] strLongitudes = addVillageNew.getLongitude().split(",");
				String[] strLatitudes = addVillageNew.getLatitude().split(",");
				for (int i = 0; i < strLongitudes.length; i++) {
					GISNode gisNode = new GISNode();
					gisNode.setLongitude(Float.parseFloat(strLongitudes[i]));
					gisNode.setLatitude(Float.parseFloat(strLatitudes[i]));
					gisNodes.getGISNode().add(gisNode);
				}
			}

			gisInformation.setGISNodes(gisNodes);
			gisInformation.setMapURL(addVillageNew.getUrlpath().getOriginalFilename());

			landDetails.setGISInformation(gisInformation);
			villageForm.setLandDetails(landDetails);

			if (addVillageNew.getContributedVillages() != null) {
				Villages villages = new Villages();

				String villageArray[] = addVillageNew.getContributedVillages().split(",");
				for (int i = 0; i < villageArray.length; i++) {
					landDetails = new LandDetails();
					landDetails.setNameEnglish(villageArray[i]);
					villages.getVillage().add(landDetails);
				}
				villageForm.setContributingVillages(villages);
			}

			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Marshaller marshaller = jaxbContext.createMarshaller();
			xmlPath = "../village" + timestamp + ".xml";
			marshaller.marshal(villageForm, new File(xmlPath));
			description = "New village '" + addVillageNew.getNewVillageNameEnglish() + "' in draft.";
			this.saveUnpublishedItems(description, xmlPath, 'V');
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}

	// @Author - Sarvapriya Malhtora
	@Override
	public VillageForm getDraftVillage(String fileName) throws Exception {
		VillageForm villageForm = new VillageForm();
		try {
			// Get Values Back from Saved XML
			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			in.nic.pes.lgd.ws.VillageForm draftVillageForm = (in.nic.pes.lgd.ws.VillageForm) unmarshaller.unmarshal(new File(fileName));

			villageForm.setNewVillageNameEnglish(draftVillageForm.getLandDetails().getNameEnglish() == "" ? null : draftVillageForm.getLandDetails().getNameEnglish());
			villageForm.setNewVillageNameLocal(draftVillageForm.getLandDetails().getNameLocal() == null ? "" : draftVillageForm.getLandDetails().getNameLocal());
			villageForm.setNewVillageAliasEnglish(draftVillageForm.getLandDetails().getAliasEnglish() == "" ? null : draftVillageForm.getLandDetails().getAliasEnglish());
			villageForm.setNewVillageAliasLocal(draftVillageForm.getLandDetails().getAliasLocal() == null ? "" : draftVillageForm.getLandDetails().getAliasLocal());
			villageForm.setCensus2011Code(draftVillageForm.getLandDetails().getCensus2011Code() == null ? null : draftVillageForm.getLandDetails().getCensus2011Code());
			villageForm.setStateSpecificCode(draftVillageForm.getLandDetails().getSSCode() == null ? "" : draftVillageForm.getLandDetails().getSSCode());

			if (draftVillageForm.getLandDetails().getParents() != null){
				villageForm.setDistrictNameEnglish(Integer.toString(draftVillageForm.getLandDetails().getParents().getParent().get(0).getParentCode()));
			}	
			if (draftVillageForm.getLandDetails().getGISInformation() != null) {
				String TEST_FILE = draftVillageForm.getLandDetails().getGISInformation().getMapURL() == null ? "" : draftVillageForm.getLandDetails().getGISInformation().getMapURL();
				MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
				MockMultipartHttpServletRequest mockMultipartHttpServletRequest = (MockMultipartHttpServletRequest) request;
				CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) mockMultipartHttpServletRequest.getFile(TEST_FILE);

				villageForm.setUrlpath(commonsMultipartFile);

				if (draftVillageForm.getLandDetails().getGISInformation().getGISNodes() != null) {
					String latitude = "";
					String longitude = "";

					for (int i = 0; i < draftVillageForm.getLandDetails().getGISInformation().getGISNodes().getGISNode().size() - 1; i++) {
						latitude += draftVillageForm.getLandDetails().getGISInformation().getGISNodes().getGISNode().get(i).getLatitude() + ",";
						longitude += draftVillageForm.getLandDetails().getGISInformation().getGISNodes().getGISNode().get(i).getLongitude() + ",";
					}
					latitude += draftVillageForm.getLandDetails().getGISInformation().getGISNodes().getGISNode().get(draftVillageForm.getLandDetails().getGISInformation().getGISNodes().getGISNode().size() - 1).getLatitude();
					longitude += draftVillageForm.getLandDetails().getGISInformation().getGISNodes().getGISNode().get(draftVillageForm.getLandDetails().getGISInformation().getGISNodes().getGISNode().size() - 1).getLongitude();

					villageForm.setLatitude(latitude);
					villageForm.setLongitude(longitude);
				}
			}

			String[] surveyNumbers = new String[draftVillageForm.getLandDetails().getSurveyNumbers().getSurveyNumber().size()];
			if (draftVillageForm.getLandDetails().getSurveyNumbers() != null){
				for (int i = 0; i < draftVillageForm.getLandDetails().getSurveyNumbers().getSurveyNumber().size(); i++)
					surveyNumbers[i] = draftVillageForm.getLandDetails().getSurveyNumbers().getSurveyNumber().get(i);
			}	
			villageForm.setSurveyNumber(surveyNumbers);

		} catch (Exception e) {
		}

		return villageForm;
	}

	public int getSubdistrict() throws Exception {
		in.nic.pes.lgd.ws.VillageForm draftVillageForm;
		try {
			// Get Values Back from Saved XML
			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			draftVillageForm = (in.nic.pes.lgd.ws.VillageForm) unmarshaller.unmarshal(new File("../village" + DatePicker.getDate(new Date().toString()) + ".xml"));

		} catch (Exception e) {
			return 0;
		}
		if (draftVillageForm.getLandDetails().getParents() != null) {
			return draftVillageForm.getLandDetails().getParents().getParent().get(1).getParentCode();
		} else
			return 0;

	}

	public String[] getSelectedVillages() throws Exception {
		in.nic.pes.lgd.ws.VillageForm draftVillageForm;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			draftVillageForm = (in.nic.pes.lgd.ws.VillageForm) unmarshaller.unmarshal(new File("../village" + DatePicker.getDate(new Date().toString()) + ".xml"));

		} catch (Exception e) {
			return null;
		}
		if (draftVillageForm.getContributingVillages() != null & draftVillageForm.getContributingVillages().getVillage() != null) {
			String[] villageList = new String[draftVillageForm.getContributingVillages().getVillage().size()];
			for (int i = 0; i < draftVillageForm.getContributingVillages().getVillage().size(); i++)
				villageList[i] = draftVillageForm.getContributingVillages().getVillage().get(i).getNameEnglish();
			return villageList;
		} else
			return null;

	}

	public List<Village> getVillageListByVillCode(int villageCode) throws Exception {
		return villageDAO.getVillageListByVillCode(villageCode);
	}

	public List<Village> getVillageViewList(String query, int limit, int offset) throws Exception {
		return villageDAO.getVillageViewList(query, limit, offset);

	}

	public List<Village> getVillageViewList(String query) throws Exception {
		return villageDAO.getVillageViewList(query);

	}

	@Override
	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws Exception {
		List<GovernmentOrder> govOrderDetail = subdistrictDAO.getGovermentOrderDetail(orderCode);
		// VillageForm villageForm =new VillageForm();
		return govOrderDetail;
	}

	// Created by Chandan 21-11-2011
	// Created by Chandan 21-11-2011
	@Override
	public boolean invalidateVillage(VillageForm villageform, HttpSession httpSession) throws Exception {
		int flagCode = 0;
		flagCode = 9;
		boolean success = false;
		villageform = (VillageForm) httpSession.getAttribute("formbean");
		Village village = null;
		if (villageform.isRdoVillageDelete() == true) {
			String villageListFromForm = villageform.getInvalidateVillageList();

			int villageToBeMerge = Integer.parseInt(villageform.getVillageListMerge());
			success = villageDAO.invalidateVillage(villageToBeMerge, villageListFromForm, roleCode);
			return success;
		} else if (villageform.isRdoVillageDelete() == false) {
			String villageListFromForm = villageform.getInvalidateVillageList();
			String[] temp = villageListFromForm.split(",");
			for (int i = 0; i < temp.length; i++) {
				int villageCode = Integer.parseInt(temp[i]);
				int villageVersion = 0;
				villageVersion = villageDAO.getVillageVersion(villageCode);
				village = new Village();
				try {
					VillagePK villagePk = new VillagePK();
					villagePk.setVillageCode(villageCode);
					villagePk.setVillageVersion(villageVersion);
					village.setIsactive(false);
					villageDAO.updateIsActive(villagePk);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				flagCode = 9;
				success = insertNewVillage(villageCode, villageVersion, flagCode);
			}
			return success;
		} else {
			return false;
		}

	}

	// Created By kamlesh
	@Override
	public String invalidateVillageNew(String villCode, Integer userId, String orderNumber, Date orderDate, Date effectiveDate, String govtOrder, Date gzbDate, String destinationVillage, Integer distinationSubdistrict,Integer ulbCode,Integer oderCode,String isExistGovtOrder,Character invalidateVillageNew) throws Exception {
		// boolean tmp=false;
		String ordercode = null;
		try {

			ordercode = villageDAO.invalidateVillageDAO(villCode, userId, orderNumber, orderDate, effectiveDate, govtOrder, gzbDate, destinationVillage, distinationSubdistrict, session,ulbCode,oderCode,isExistGovtOrder,invalidateVillageNew);
			return ordercode;

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return ordercode;
	}

	public boolean insertNewVillage(int villageCode, int villageVersion, int flagCode) throws Exception {
		Village newVillageBean = null;
		newVillageBean = new Village();
		List<Village> villageList = null;
		villageList = new ArrayList<Village>();

		// inserting a new village
		// create bean object
		// For current date
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		String EffectiveDate = "2011-12-12";
		villageList = villageDAO.getListVillageDetails("from Village v where v.villagePK.villageCode=" + villageCode + " and v.villagePK.villageVersion=" + villageVersion);
		newVillageBean.setAliasEnglish(villageList.get(0).getAliasEnglish());
		newVillageBean.setAliasLocal(villageList.get(0).getAliasLocal());
		newVillageBean.setCensus2011Code(villageList.get(0).getCensus2011Code());
		newVillageBean.setCensus2001Code(villageList.get(0).getCensus2001Code());
		newVillageBean.setCreatedby(villageList.get(0).getCreatedby());
		newVillageBean.setIsactive(false);
		newVillageBean.setLastupdatedby(1);
		newVillageBean.setLrReplaces(villageList.get(0).getLrReplaces());
		newVillageBean.setLrReplacedby(villageList.get(0).getLrReplacedby());
		newVillageBean.setMapCode(villageList.get(0).getMapCode());
		// newVillageBean.setRemarks("village created ");
		newVillageBean.setSscode(villageList.get(0).getSscode());
		newVillageBean.setLastupdated(time);
		newVillageBean.setCreatedon(villageList.get(0).getCreatedon());
		newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
		newVillageBean.setVillageNameEnglish(villageList.get(0).getVillageNameEnglish());
		newVillageBean.setVillageNameLocal(villageList.get(0).getVillageNameLocal());
		newVillageBean.setVillageStatus(villageList.get(0).getVillageStatus());
		VillagePK villagePK = new VillagePK(villageCode, villageVersion + 1,1);
		newVillageBean.setVillagePK(villagePK);
		newVillageBean.setFlagCode(flagCode);

		try {
			villageDAO.save(newVillageBean);
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	public List<VillageHistory> getVillageHistoryDetail(char villageNameEnglish, int villageCode) throws Exception {
		List<VillageHistory> VillageHistoryDetail = new ArrayList<VillageHistory>();
		VillageHistoryDetail = villageDAO.getVillageHistoryDetail(villageNameEnglish, villageCode);
		return VillageHistoryDetail;
	}

	// End here 21-10-11
	@Override
	public List<Village> getBlockVillagebyBlockCode(int blockCode) throws Exception {
		return blockDAO.getBlockVillagebyBlockCode(blockCode);
	}

	@Override
	public List<List<Village>> getVillageViewList(VillageForm villageForm) throws NumberFormatException, Exception {
		String[] str = null;
		List<Village> villageList = null;
		villageList = new ArrayList<Village>();
		List<List<Village>> villageListList = null;
		villageListList = new ArrayList<List<Village>>();
		str = villageForm.getInvalidateVillageList().split(",");
		for (int i = 0; i < str.length; i++) {
			// TODO getVillageViewList stub
			villageList = villageDAO.getVillageListByMaxVersion(Integer.parseInt(str[i]));
			villageListList.add(villageList);
		}
		return villageListList;
	}

	public boolean insertSurveyNumber(VillageForm villageForm, int villageCode, int villageVersion) throws Exception {
		String[] str = null;
		str = villageForm.getSurveyNumber();
		for (int i = 0; i < str.length; i++) {
			// TODO insertSurveyNumber
			villageDAO.saveSurveynos(str[i], villageCode, villageVersion);
		}
		return true;
	}

	// For localbody upload file
	@Override
	public boolean writeMap(MultipartFile filePath, HttpServletRequest request) throws Exception {

		FileOutputStream fo = null;
		try {
			MultipartFile map = filePath;

			if (map.getBytes().length > 0) {

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

	public boolean uploadMapOrLat(VillageForm villageForm, int villageCode, HttpServletRequest request) throws Exception {
		MapLandRegion mapLandRegion = null;
		mapLandRegion = new MapLandRegion();
		try {

			// ///

			Timestamp time = CurrentDateTime.getCurrentTimestamp();
			Timestamp timestamp = CurrentDateTime.getCurrentTimestamp();

			String cordinate = "";
			String latitude = "";
			String longitude = "";

			if (villageForm.getLatitude() == null) {
				latitude = "";
			} else {
				latitude = villageForm.getLatitude();
			}
			if (villageForm.getLongitude() == null) {
				latitude = "";
			} else {
				longitude = villageForm.getLongitude();
			}
			if (villageForm.getLatitude() == null && villageForm.getLongitude() == null) {
				cordinate = null;
			}
			cordinate = longitude + ":" + latitude;

			// mapLandRegion.setMapLandregionCode(map_landRegionCode);
			mapLandRegion.setEffectiveDate(timestamp);
			mapLandRegion.setCreatedon(time);
			mapLandRegion.setCreatedby(2000);

			mapLandRegion.setLandregionVersion(1);
			if (cordinate != null) {
				mapLandRegion.setCoordinates(cordinate);
			}
			mapLandRegion.setLandregionCode(villageCode);
			mapLandRegion.setWarningflag(true);

			// mapLandRegion.setImagePath("C:/M:");
			MultipartFile filename = null;
			filename = villageForm.getUrlpath();
			writeMap(filename, request);
			String filePath = request.getRealPath("/") + filename.getOriginalFilename();
			mapLandRegion.setImagePath(filePath);
			mapLandRegion.setLastupdated(timestamp);
			mapLandRegion.setLastupdatedby(1010101);

			// End here map details
		} catch (Exception e) {
			// log.debug("Exception" + e);
			return true;
		}
		return true;
	}

	@SuppressWarnings("null")
	@Override
	public boolean addVillage(VillageForm villageForm) throws Exception {
		int idLR = 0;
		int flagCode = 1;
		int lrReplacedby = 0;
		Village oldVillage = null;
		Village newVillageBean = null;
		LandregionReplacedby LandregionReplacedbyBean = null;
		Timestamp time = null;
		time = CurrentDateTime.getCurrentTimestamp();
		String EffectiveDate = "2011-12-12";
		createFromNewLand = villageForm.isCreateFromNewLand();
		createFromCoverageOfUrbanLocalBody = villageForm.isCreateFromCoverageOfUrbanLocalBody();
		createFromExistingVillages = villageForm.isCreateFromExistingVillages();

		List<Village> fullContributedList = null;
		fullContributedList = new ArrayList<Village>();

		List<Village> partContributedList = null;
		partContributedList = new ArrayList<Village>();

		List<Subdistrict> sdList = null;
		sdList = new ArrayList<Subdistrict>();

		subdistrictCode = Integer.parseInt(villageForm.getSubdistrictNameEnglish());
		try {
			sdList = villageDAO.getSubdistrictDetails("from Subdistrict where subdistrictCode='" + subdistrictCode + "' and isactive=true");
		} catch (Exception e2) {
			log.debug("Exception" + e2);
		}
		subdistricVersion = sdList.get(0).getTlc();

		if (createFromExistingVillages) {

			String selectedVillage = villageForm.getContributedVillages();
			String[] temp = selectedVillage.split(",");
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].contains("FULL")) {
					int vCodeFull = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					List<Village> lstTemp = null;
					lstTemp = new ArrayList<Village>();

					try {
						lstTemp = villageDAO.getListVillageDetails("from Village where village_code='" + vCodeFull + "' and isactive=true");
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
					fullContributedList.add(lstTemp.get(0));

				}
				if (temp[i].contains("PART")) {
					int vCodePart = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					List<Village> lstTemp = null;
					lstTemp = new ArrayList<Village>();
					try {
						lstTemp = villageDAO.getListVillageDetails("from Village where village_code='" + vCodePart + "' and isactive=true");
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
					partContributedList.add(lstTemp.get(0));
				}

			}
			@SuppressWarnings("unused")
			List<VillagePartsBySurveyno> villagePartsBySurveynolist = new ArrayList<VillagePartsBySurveyno>();
		} else {
			fullContributedList.clear();
			partContributedList.clear();
		}

		// *******************************************************************/

		List<Subdistrict> fullSubdistrictContributingList = null;
		fullSubdistrictContributingList = new ArrayList<Subdistrict>();
		List<Subdistrict> partSubdistrictContributingList = null;
		partSubdistrictContributingList = new ArrayList<Subdistrict>();

		if (createFromCoverageOfUrbanLocalBody) {
			String selectedCoveredLandRegionByULB = villageForm.getSelectedCoveredLandRegionByULB();
			String[] temp = selectedCoveredLandRegionByULB.split(",");
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].contains("FULL")) {
					int sdCodeFull = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					List<Subdistrict> lstTemp = null;
					lstTemp = new ArrayList<Subdistrict>();

					try {
						lstTemp = villageDAO.getSubdistrictDetails("from Subdistrict where subdistrictCode='" + sdCodeFull + "' and isactive=true");
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
					fullSubdistrictContributingList.add(lstTemp.get(0));

				}
				if (temp[i].contains("PART")) {
					int sdCodePart = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					List<Subdistrict> lstTemp = null;
					lstTemp = new ArrayList<Subdistrict>();
					try {
						lstTemp = villageDAO.getSubdistrictDetails("from Subdistrict where subdistrictCode='" + sdCodePart + "' and isactive=true");
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
					partSubdistrictContributingList.add(lstTemp.get(0));
				}

			}

		} else {
			fullSubdistrictContributingList.clear();
			partSubdistrictContributingList.clear();
		}

		// cases for creating villages
		if (fullContributedList.size() == 0 && partContributedList.size() == 1 && createFromNewLand == false && createFromCoverageOfUrbanLocalBody == false) {
			newVillageBean = new Village();
			int villageCode = 0;
			villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
			if (villageCode == 0) {
				villageCode = 1;
			} else {
				villageCode = villageCode + 1;
			}
			newVillageBean.setAliasEnglish(villageForm.getNewVillageNameEnglish());
			newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
			if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
				newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
			}
			newVillageBean.setCreatedby(roleCode);
			newVillageBean.setIsactive(true);
			newVillageBean.setLastupdatedby(roleCode);
			newVillageBean.setLrReplaces(null);
			newVillageBean.setLrReplacedby(null);
			newVillageBean.setMapCode(null);
			newVillageBean.setSscode(villageForm.getStateSpecificCode());
			newVillageBean.setLastupdated(time);
			newVillageBean.setCreatedon(time);
			newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
			newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
			newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
			newVillageBean.setVillageStatus(villageForm.getVillageType());
			VillagePK villagePK = new VillagePK(villageCode, 1,1);
			newVillageBean.setVillagePK(villagePK);
			newVillageBean.setFlagCode(flagCode);

			try {
				villageDAO.save(newVillageBean);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

			int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
			int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
			LandregionReplaces landregionReplacesBean = null;
			landregionReplacesBean = new LandregionReplaces();
			// putting the value for new village in the landregion replaces
			landregionReplacesBean.setId(id);
			landregionReplacesBean.setLrReplaces(lrReplaces);
			landregionReplacesBean.setEntityCode(partContributedList.get(0).getVlc());
			landregionReplacesBean.setEntityVersion(partContributedList.get(0).getVlc());
			landregionReplacesBean.setEntityType('V');
			try {
				landRegionReplacesDAO.save(landregionReplacesBean);
			} catch (Exception e1) {
				log.debug("Exception" + e1);
			}

			try {
				VillagePK villagePk = new VillagePK();
				villagePk.setVillageCode(villageCode);
				villagePk.setVillageVersion(1);
				villageDAO.updateLReplaces(lrReplaces, villagePk);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			for (int i = 0; i < partContributedList.size(); i++) {
				lrReplacedby = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replacedby),1) from landregion_replacedby");
				idLR = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replacedby");
				LandregionReplacedbyBean = new LandregionReplacedby();
				LandregionReplacedbyBean.setId(idLR);
				LandregionReplacedbyBean.setLrReplacedby(lrReplacedby);
				LandregionReplacedbyBean.setEntityCode(villageCode);
				LandregionReplacedbyBean.setEntityVersion(1);
				LandregionReplacedbyBean.setEntityType('V');
				try {
					landRegionReplacedByDAO.save(LandregionReplacedbyBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}

				oldVillage = new Village();
				try {
					VillagePK villagePk = new VillagePK();
					villagePk.setVillageCode(partContributedList.get(i).getVlc());
					villagePk.setVillageVersion(partContributedList.get(i).getVlc());
					// oldVillage.setLrReplacedby(lrReplacedby);
					oldVillage.setIsactive(false);
					villageDAO.updateIsActive(villagePk);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				oldVillage.setAliasEnglish(partContributedList.get(i).getAliasEnglish());
				oldVillage.setAliasLocal(partContributedList.get(i).getAliasLocal());
				oldVillage.setCensus2001Code(partContributedList.get(i).getCensus2001Code());
				oldVillage.setCensus2011Code(partContributedList.get(i).getCensus2011Code());
				oldVillage.setCreatedby(partContributedList.get(i).getCreatedby());
				oldVillage.setCreatedon(partContributedList.get(i).getCreatedon());
				oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
				oldVillage.setIsactive(true);
				oldVillage.setLastupdated(time);
				oldVillage.setLastupdatedby(roleCode);
				oldVillage.setLrReplaces(partContributedList.get(i).getLrReplaces());
				oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
				oldVillage.setMapCode(partContributedList.get(i).getMapCode());
				oldVillage.setSscode(partContributedList.get(i).getSscode());
				oldVillage.setVillageNameEnglish(partContributedList.get(i).getVillageNameEnglish());
				oldVillage.setVillageNameLocal(partContributedList.get(i).getVillageNameLocal());
				oldVillage.setVillageStatus(partContributedList.get(i).getVillageStatus());
				VillagePK oldvillagePK = new VillagePK(partContributedList.get(i).getVlc(), partContributedList.get(i).getVlc() + 1,partContributedList.get(i).getMinorVersion());
				oldVillage.setVillagePK(oldvillagePK);
				oldVillage.setFlagCode(flagCode);

				try {
					villageDAO.save(oldVillage);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				idLR += 1;
			}

		} else if (fullContributedList.size() >= 0 && partContributedList.size() >= 0 && createFromNewLand == false && createFromCoverageOfUrbanLocalBody == false) {

			if (fullContributedList.size() > 0 && partContributedList.size() == 0) {
				newVillageBean = new Village();
				int villageCode = 0;
				villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
				if (villageCode == 0) {
					villageCode = 1;
				} else {
					villageCode = villageCode + 1;
				}
				newVillageBean.setAliasEnglish(villageForm.getNewVillageNameEnglish());
				newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
				if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
					newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
				}
				newVillageBean.setCreatedby(roleCode);
				newVillageBean.setIsactive(true);
				newVillageBean.setLastupdatedby(roleCode);
				newVillageBean.setLrReplaces(null);
				newVillageBean.setLrReplacedby(null);
				newVillageBean.setMapCode(null);
				// newVillageBean.setRemarks("village created ");
				newVillageBean.setSscode(villageForm.getStateSpecificCode());
				newVillageBean.setLastupdated(time);
				newVillageBean.setCreatedon(time);
				newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
				newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
				newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
				newVillageBean.setVillageStatus(villageForm.getVillageType());
				VillagePK villagePK = new VillagePK(villageCode, 1,1);
				newVillageBean.setVillagePK(villagePK);
				newVillageBean.setFlagCode(flagCode);

				try {
					villageDAO.save(newVillageBean);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}

				int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
				int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
				LandregionReplaces landregionReplacesBean = null;
				// putting the value for new village in the landregion replaces
				for (int i = 0; i < fullContributedList.size(); i++) {
					landregionReplacesBean = new LandregionReplaces();
					landregionReplacesBean.setId(id);
					landregionReplacesBean.setLrReplaces(lrReplaces);
					landregionReplacesBean.setEntityCode(fullContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityVersion(fullContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityType('V');
					id += 1;
					try {
						landRegionReplacesDAO.save(landregionReplacesBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
				}
				try {

					VillagePK villagePk = new VillagePK();
					villagePk.setVillageCode(villageCode);
					villagePk.setVillageVersion(1);
					villageDAO.updateLReplaces(lrReplaces, villagePk);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				oldVillage = null;
				lrReplacedby = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replacedby),1) from landregion_replacedby");
				idLR = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replacedby");
				LandregionReplacedbyBean = new LandregionReplacedby();
				LandregionReplacedbyBean.setId(idLR);
				LandregionReplacedbyBean.setLrReplacedby(lrReplacedby);
				LandregionReplacedbyBean.setEntityCode(villageCode);
				LandregionReplacedbyBean.setEntityVersion(1);
				LandregionReplacedbyBean.setEntityType('V');
				try {
					landRegionReplacedByDAO.save(LandregionReplacedbyBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}

				for (int i = 0; i < fullContributedList.size(); i++) {

					// deactivating the old village
					oldVillage = new Village();
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(fullContributedList.get(i).getVlc());
						villagePk.setVillageVersion(fullContributedList.get(i).getVlc());
						oldVillage.setIsactive(false);
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

				for (int i = 0; i < fullContributedList.size(); i++) {
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(fullContributedList.get(i).getVlc());
						villagePk.setVillageVersion(fullContributedList.get(i).getVlc());
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}

					oldVillage = new Village();
					// inserting old village with new version and with
					// replacedby field
					oldVillage.setAliasEnglish(fullContributedList.get(i).getAliasEnglish());
					oldVillage.setAliasLocal(fullContributedList.get(i).getAliasLocal());
					oldVillage.setCensus2001Code(fullContributedList.get(i).getCensus2001Code());
					oldVillage.setCensus2011Code(fullContributedList.get(i).getCensus2011Code());
					oldVillage.setCreatedby(fullContributedList.get(i).getCreatedby());
					oldVillage.setCreatedon(fullContributedList.get(i).getCreatedon());
					oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
					oldVillage.setIsactive(false);
					oldVillage.setLastupdated(time);
					oldVillage.setLastupdatedby(roleCode);
					oldVillage.setLrReplaces(fullContributedList.get(i).getLrReplaces());
					oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
					oldVillage.setMapCode(fullContributedList.get(i).getMapCode());
					oldVillage.setSscode(fullContributedList.get(i).getSscode());
					oldVillage.setVillageNameEnglish(fullContributedList.get(i).getVillageNameEnglish());
					oldVillage.setVillageNameLocal(fullContributedList.get(i).getVillageNameLocal());
					oldVillage.setVillageStatus(fullContributedList.get(i).getVillageStatus());

					VillagePK oldvillagePK = new VillagePK(fullContributedList.get(i).getVlc(), fullContributedList.get(i).getVlc() + 1,fullContributedList.get(i).getMinorVersion());
					oldVillage.setVillagePK(oldvillagePK);
					oldVillage.setFlagCode(flagCode);

					try {
						villageDAO.save(oldVillage);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

			} else if (fullContributedList.size() > 0 && partContributedList.size() > 0) {
				newVillageBean = new Village();
				int villageCode = 0;
				villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
				if (villageCode == 0) {
					villageCode = 1;
				} else {
					villageCode = villageCode + 1;
				}
				newVillageBean.setAliasEnglish(villageForm.getNewVillageNameEnglish());
				newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
				if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
					newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
				}
				newVillageBean.setCreatedby(roleCode);
				newVillageBean.setIsactive(true);
				newVillageBean.setLastupdatedby(roleCode);
				newVillageBean.setLrReplaces(null);
				newVillageBean.setLrReplacedby(null);
				newVillageBean.setMapCode(null);
				newVillageBean.setSscode(villageForm.getStateSpecificCode());
				newVillageBean.setLastupdated(time);
				newVillageBean.setCreatedon(time);
				newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
				newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
				newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
				newVillageBean.setVillageStatus(villageForm.getVillageType());
				VillagePK villagePK = new VillagePK(villageCode, 1,1);
				newVillageBean.setVillagePK(villagePK);
				newVillageBean.setFlagCode(flagCode);

				try {
					villageDAO.save(newVillageBean);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}

				int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
				int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
				LandregionReplaces landregionReplacesBean = null;
				for (int i = 0; i < fullContributedList.size(); i++) {
					landregionReplacesBean = new LandregionReplaces();
					landregionReplacesBean.setId(id);
					landregionReplacesBean.setLrReplaces(lrReplaces);
					landregionReplacesBean.setEntityCode(fullContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityVersion(fullContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityType('V');
					id += 1;
					try {
						landRegionReplacesDAO.save(landregionReplacesBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
				}
				for (int i = 0; i < partContributedList.size(); i++) {
					landregionReplacesBean = new LandregionReplaces();
					landregionReplacesBean.setId(id);
					landregionReplacesBean.setLrReplaces(lrReplaces);
					landregionReplacesBean.setEntityCode(partContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityVersion(partContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityType('V');
					id += 1;
					try {
						landRegionReplacesDAO.save(landregionReplacesBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
				}
				try {

					VillagePK villagePk = new VillagePK();
					villagePk.setVillageCode(villageCode);
					villagePk.setVillageVersion(1);
					villageDAO.updateLReplaces(lrReplaces, villagePk);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}

				lrReplacedby = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replacedby),1) from landregion_replacedby");
				idLR = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replacedby");
				LandregionReplacedbyBean = new LandregionReplacedby();
				LandregionReplacedbyBean.setId(idLR);
				LandregionReplacedbyBean.setLrReplacedby(lrReplacedby);
				LandregionReplacedbyBean.setEntityCode(villageCode);
				LandregionReplacedbyBean.setEntityVersion(1);
				LandregionReplacedbyBean.setEntityType('V');
				try {
					landRegionReplacedByDAO.save(LandregionReplacedbyBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}

				for (int i = 0; i < fullContributedList.size(); i++) {

					oldVillage = new Village();
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(fullContributedList.get(i).getVlc());
						villagePk.setVillageVersion(fullContributedList.get(i).getVlc());
						oldVillage.setIsactive(false);
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

				for (int i = 0; i < fullContributedList.size(); i++) {

					// deactivating the old village
					// String villageInactivehql =
					// "update Village set isactive = '"+ false
					// +"' where villageCode = '"+partContributedList.get(i).getVillageCode()+"' and isactive=true";
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(fullContributedList.get(i).getVlc());
						villagePk.setVillageVersion(fullContributedList.get(i).getVlc());
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}

					oldVillage = new Village();
					// inserting old village with new version and with
					// replacedby field
					oldVillage.setAliasEnglish(fullContributedList.get(i).getAliasEnglish());
					oldVillage.setAliasLocal(fullContributedList.get(i).getAliasLocal());
					oldVillage.setCensus2001Code(fullContributedList.get(i).getCensus2001Code());
					oldVillage.setCensus2011Code(fullContributedList.get(i).getCensus2011Code());
					oldVillage.setCreatedby(fullContributedList.get(i).getCreatedby());
					oldVillage.setCreatedon(fullContributedList.get(i).getCreatedon());
					oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
					oldVillage.setIsactive(false);
					oldVillage.setLastupdated(time);
					oldVillage.setLastupdatedby(roleCode);
					oldVillage.setLrReplaces(fullContributedList.get(i).getLrReplaces());
					oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
					oldVillage.setMapCode(fullContributedList.get(i).getMapCode());
					// oldVillage.setRemarks(fullContributedList.get(i).getRemarks());
					oldVillage.setSscode(fullContributedList.get(i).getSscode());
					oldVillage.setVillageNameEnglish(fullContributedList.get(i).getVillageNameEnglish());
					oldVillage.setVillageNameLocal(fullContributedList.get(i).getVillageNameLocal());
					oldVillage.setVillageStatus(fullContributedList.get(i).getVillageStatus());
					// oldVillage.setSubdistrict(sd);
					VillagePK oldvillagePK = new VillagePK(fullContributedList.get(i).getVlc(), fullContributedList.get(i).getVlc() + 1, fullContributedList.get(i).getMinorVersion());

					oldVillage.setVillagePK(oldvillagePK);
					oldVillage.setFlagCode(flagCode);

					try {
						villageDAO.save(oldVillage);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

				for (int i = 0; i < partContributedList.size(); i++) {
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(partContributedList.get(i).getVlc());
						villagePk.setVillageVersion(partContributedList.get(i).getVlc());
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}

					oldVillage = new Village();
					// inserting old village with new version and with
					// replacedby field
					oldVillage.setAliasEnglish(partContributedList.get(i).getAliasEnglish());
					oldVillage.setAliasLocal(partContributedList.get(i).getAliasLocal());
					oldVillage.setCensus2001Code(partContributedList.get(i).getCensus2001Code());
					oldVillage.setCensus2011Code(partContributedList.get(i).getCensus2011Code());
					oldVillage.setCreatedby(partContributedList.get(i).getCreatedby());
					oldVillage.setCreatedon(partContributedList.get(i).getCreatedon());
					oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
					oldVillage.setIsactive(true);
					oldVillage.setLastupdated(time);
					oldVillage.setLastupdatedby(roleCode);
					oldVillage.setLrReplaces(partContributedList.get(i).getLrReplaces());
					oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
					oldVillage.setMapCode(partContributedList.get(i).getMapCode());
					// oldVillage.setRemarks(partContributedList.get(i).getRemarks());
					oldVillage.setSscode(partContributedList.get(i).getSscode());
					oldVillage.setVillageNameEnglish(partContributedList.get(i).getVillageNameEnglish());
					oldVillage.setVillageNameLocal(partContributedList.get(i).getVillageNameLocal());
					oldVillage.setVillageStatus(partContributedList.get(i).getVillageStatus());
					// oldVillage.setSubdistrict(sd);
					VillagePK oldvillagePK = new VillagePK(partContributedList.get(i).getVlc(), partContributedList.get(i).getVlc() + 1,partContributedList.get(i).getMinorVersion());
					// VillagePK oldvillagePK =new VillagePK();
					// oldvillagePK.setVillageCode(partContributedList.get(i).getVillageCode());
					// oldvillagePK.setVillageVersion(partContributedList.get(i).getVillageVersion()+1);//version
					// updation
					oldVillage.setVillagePK(oldvillagePK);
					oldVillage.setFlagCode(flagCode);

					try {
						villageDAO.save(oldVillage);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
				}

			} else if (fullContributedList.size() == 0 && partContributedList.size() > 1) {

				newVillageBean = new Village();
				int villageCode = 0;
				villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
				if (villageCode == 0) {
					villageCode = 1;
				} else {
					villageCode = villageCode + 1;
				}

				// DateFormat dateFormat = new
				// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				newVillageBean.setAliasEnglish(villageForm.getNewVillageNameEnglish());
				newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
				if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
					newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
				}
				newVillageBean.setCreatedby(roleCode);
				newVillageBean.setIsactive(true);
				newVillageBean.setLastupdatedby(roleCode);
				newVillageBean.setLrReplaces(null);
				newVillageBean.setLrReplacedby(null);
				newVillageBean.setMapCode(null);
				// newVillageBean.setRemarks("village created ");
				newVillageBean.setSscode(villageForm.getStateSpecificCode());
				newVillageBean.setLastupdated(time);
				newVillageBean.setCreatedon(time);
				newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
				newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
				newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
				newVillageBean.setVillageStatus(villageForm.getVillageType());
				VillagePK villagePK = new VillagePK(villageCode, 1,1);
				newVillageBean.setVillagePK(villagePK);
				newVillageBean.setFlagCode(flagCode);
				try {
					villageDAO.save(newVillageBean);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}

				int id = 0;
				id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
				int lrReplaces = 0;
				lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
				LandregionReplaces landregionReplacesBean = null;
				for (int i = 0; i < partContributedList.size(); i++) {
					landregionReplacesBean = new LandregionReplaces();
					landregionReplacesBean.setId(id);
					landregionReplacesBean.setLrReplaces(lrReplaces);
					landregionReplacesBean.setEntityCode(partContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityVersion(partContributedList.get(i).getVlc());
					landregionReplacesBean.setEntityType('V');
					id += 1;
					try {
						landRegionReplacesDAO.save(landregionReplacesBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
				}
				try {
					VillagePK villagePk = new VillagePK();
					villagePk.setVillageCode(villageCode);
					villagePk.setVillageVersion(1);
					villageDAO.updateLReplaces(lrReplaces, villagePk);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}

				lrReplacedby = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replacedby),1) from landregion_replacedby");
				for (int i = 0; i < partContributedList.size(); i++) {

					idLR = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replacedby");
					LandregionReplacedbyBean = new LandregionReplacedby();
					LandregionReplacedbyBean.setId(idLR);
					LandregionReplacedbyBean.setLrReplacedby(lrReplacedby);
					LandregionReplacedbyBean.setEntityCode(villageCode);
					LandregionReplacedbyBean.setEntityVersion(1);
					LandregionReplacedbyBean.setEntityType('V');
					try {
						landRegionReplacedByDAO.save(LandregionReplacedbyBean);
					} catch (Exception e1) {
						log.debug("Exception" + e1);
					}
					try {
						VillagePK villagePk = new VillagePK();
						villagePk.setVillageCode(partContributedList.get(i).getVlc());
						villagePk.setVillageVersion(partContributedList.get(i).getVlc());
						villageDAO.updateIsActive(villagePk);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
					oldVillage = new Village();
					oldVillage.setAliasEnglish(partContributedList.get(i).getAliasEnglish());
					oldVillage.setAliasLocal(partContributedList.get(i).getAliasLocal());
					oldVillage.setCensus2001Code(partContributedList.get(i).getCensus2001Code());
					oldVillage.setCensus2011Code(partContributedList.get(i).getCensus2011Code());
					oldVillage.setCreatedby(partContributedList.get(i).getCreatedby());
					oldVillage.setCreatedon(partContributedList.get(i).getCreatedon());
					oldVillage.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
					oldVillage.setIsactive(true);
					oldVillage.setLastupdated(time);
					oldVillage.setLastupdatedby(roleCode);
					oldVillage.setLrReplaces(partContributedList.get(i).getLrReplaces());
					oldVillage.setLrReplacedby(lrReplacedby);// replacedby field
					oldVillage.setMapCode(partContributedList.get(i).getMapCode());
					oldVillage.setSscode(partContributedList.get(i).getSscode());
					oldVillage.setVillageNameEnglish(partContributedList.get(i).getVillageNameEnglish());
					oldVillage.setVillageNameLocal(partContributedList.get(i).getVillageNameLocal());
					oldVillage.setVillageStatus(partContributedList.get(i).getVillageStatus());
					VillagePK oldvillagePK = new VillagePK(partContributedList.get(i).getVlc(), partContributedList.get(i).getVlc() + 1,partContributedList.get(i).getMinorVersion());
					oldVillage.setVillagePK(oldvillagePK);
					oldVillage.setFlagCode(flagCode);

					try {
						villageDAO.save(oldVillage);
					} catch (Exception e) {
						log.debug("Exception" + e);
					}
					idLR += 1;
				}

			}
			// get the no of partially contributed villages

		} else if (fullContributedList.size() == 0 && partContributedList.size() == 0 && createFromNewLand == true && createFromCoverageOfUrbanLocalBody == false) {
			newVillageBean = new Village();
			int villageCode = 0;
			villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
			if (villageCode == 0) {
				villageCode = 1;
			} else {
				villageCode = villageCode + 1;
			}
			newVillageBean.setAliasEnglish(villageForm.getNewVillageNameEnglish());
			newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
			if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
				newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
			}
			newVillageBean.setCreatedby(roleCode);
			newVillageBean.setIsactive(true);
			newVillageBean.setLastupdatedby(roleCode);
			newVillageBean.setLrReplaces(null);
			newVillageBean.setLrReplacedby(null);
			newVillageBean.setMapCode(null);
			// newVillageBean.setRemarks("village created ");
			newVillageBean.setSscode(villageForm.getStateSpecificCode());
			newVillageBean.setLastupdated(time);
			newVillageBean.setCreatedon(time);
			newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
			newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
			newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
			newVillageBean.setVillageStatus(villageForm.getVillageType());
			VillagePK villagePK = new VillagePK(villageCode, 1,1);
			newVillageBean.setVillagePK(villagePK);
			newVillageBean.setFlagCode(flagCode);

			try {
				villageDAO.save(newVillageBean);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

			// insert record survey no into table

		} else if (partContributedList.size() == 0 && fullContributedList.size() == 0 && createFromNewLand == false && createFromCoverageOfUrbanLocalBody == true) {
			// only from ULB
			newVillageBean = new Village();
			// inserting a new village
			int villageCode = 0;
			villageCode = villageDAO.getMaxVillageCode("select COALESCE(max(village_code),1) from village");
			if (villageCode == 0) {
				villageCode = 1;
			} else {
				villageCode = villageCode + 1;
			}

			// DateFormat dateFormat = new
			// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			newVillageBean.setAliasEnglish(villageForm.getNewVillageNameEnglish());
			newVillageBean.setAliasLocal(villageForm.getNewVillageAliasLocal());
			if (villageForm.getCensus2011Code() != null && villageForm.getCensus2011Code().trim().length() > 0) {
				newVillageBean.setCensus2011Code(villageForm.getCensus2011Code());
			}
			newVillageBean.setCreatedby(roleCode);
			newVillageBean.setIsactive(true);
			newVillageBean.setLastupdatedby(roleCode);
			newVillageBean.setLrReplaces(null);
			newVillageBean.setLrReplacedby(null);
			newVillageBean.setMapCode(null);
			// newVillageBean.setRemarks("village created ");
			newVillageBean.setSscode(villageForm.getStateSpecificCode());
			newVillageBean.setLastupdated(time);
			newVillageBean.setCreatedon(time);
			newVillageBean.setEffectiveDate(CurrentDateTime.getDate(EffectiveDate));
			newVillageBean.setVillageNameEnglish(villageForm.getNewVillageNameEnglish());
			newVillageBean.setVillageNameLocal(villageForm.getNewVillageNameLocal());
			newVillageBean.setVillageStatus(villageForm.getVillageType());
			VillagePK villagePK = new VillagePK(villageCode, 1,1);
			newVillageBean.setVillagePK(villagePK);
			newVillageBean.setFlagCode(flagCode);

			try {
				villageDAO.save(newVillageBean);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

			int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
			int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
			LandregionReplaces landregionReplacesBean = null;

			for (int i = 0; i < fullSubdistrictContributingList.size(); i++) {
				landregionReplacesBean = new LandregionReplaces();
				landregionReplacesBean.setId(id);
				landregionReplacesBean.setLrReplaces(lrReplaces);
				landregionReplacesBean.setEntityCode(fullSubdistrictContributingList.get(i).getTlc());
				landregionReplacesBean.setEntityVersion(fullSubdistrictContributingList.get(i).getTlc());
				landregionReplacesBean.setEntityType('T');
				id += 1;
				try {
					landRegionReplacesDAO.save(landregionReplacesBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}
			}
			for (int i = 0; i < partContributedList.size(); i++) {
				landregionReplacesBean = new LandregionReplaces();
				landregionReplacesBean.setId(id);
				landregionReplacesBean.setLrReplaces(lrReplaces);
				landregionReplacesBean.setEntityCode(partSubdistrictContributingList.get(i).getTlc());
				landregionReplacesBean.setEntityVersion(partSubdistrictContributingList.get(i).getTlc());
				landregionReplacesBean.setEntityType('T');
				id += 1;
				try {
					landRegionReplacesDAO.save(landregionReplacesBean);
				} catch (Exception e1) {
					log.debug("Exception" + e1);
				}
			}

			try {

				VillagePK villagePk = new VillagePK();
				villagePk.setVillageCode(villageCode);
				villagePk.setVillageVersion(1);
				villageDAO.updateLReplaces(lrReplaces, villagePk);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

		}

		return true;

	}

	@Override
	public void saveAsDraftInvalidateVillage(VillageForm villageForm) throws Exception {
		try {
			// Now we make temporary XML to save invalidateVillage Form
			InvalidateVillageForm invalidateVillageForm = new InvalidateVillageForm();
			District district = new District();
			Subdistricts subdistricts = new Subdistricts();
			Villages villages = new Villages();
			in.nic.pes.lgd.ws.Subdistrict subdistrict = new in.nic.pes.lgd.ws.Subdistrict();
			LandDetails landDetails = new LandDetails();
			landDetails.setNameEnglish(villageForm.getDistrictNameEnglish());
			district.setDistrictDetails(landDetails);
			landDetails = new LandDetails();
			landDetails.setNameEnglish(villageForm.getSubdistrictNameEnglish().split(",")[0]);
			subdistrict.setSubdistrictDetails(landDetails);

			String[] villageList = villageForm.getInvalidateVillageList().split(",");
			for (int i = 0; i < villageList.length; i++) {
				landDetails = new LandDetails();
				landDetails.setNameEnglish(villageList[i]);
				villages.getVillage().add(landDetails);
			}

			subdistrict.setVillages(villages);
			subdistricts.getSubdistrict().add(subdistrict);
			district.getSubdistricts().add(subdistricts);
			invalidateVillageForm.setSourceSubdistrict(district);

			if (villageForm.getVillageListMerge() != null) {
				villages = new Villages();
				subdistrict = new in.nic.pes.lgd.ws.Subdistrict();
				landDetails = new LandDetails();
				landDetails.setNameEnglish(villageForm.getSubdistrictNameEnglish().split(",")[1]);
				subdistrict.setSubdistrictDetails(landDetails);
				landDetails = new LandDetails();
				landDetails.setNameEnglish(villageForm.getVillageListMerge());
				villages.getVillage().add(landDetails);
				subdistrict.setVillages(villages);
				invalidateVillageForm.setDestinationSubdistrict(subdistrict);
			}

			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(invalidateVillageForm, new File("../invalidatevillage.xml"));
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
	}

	@Override
	public VillageForm getInvalidateDraftVillage(String xmlPath) throws Exception {
		try {
			VillageForm villageForm = new VillageForm();

			JAXBContext jaxbContext = JAXBContext.newInstance("in.nic.pes.lgd.ws");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			InvalidateVillageForm invalidateVillageForm = (InvalidateVillageForm) unmarshaller.unmarshal(new File(xmlPath));
			villageForm.setDistrictNameEnglish(invalidateVillageForm.getSourceSubdistrict().getDistrictDetails().getNameEnglish());
			return villageForm;
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean saveUnpublishedItems(String description, String xmlPath, char type) throws Exception {
		Session session = null;
		Transaction tx = null;
		Date timestamp = DatePicker.getDate(new Date().toString());
		UnpublishedItems beanUI = new UnpublishedItems();
		beanUI.setCreatedby(5000);
		beanUI.setCreatedon(timestamp);
		beanUI.setIsactive(true);
		beanUI.setItemDescription(description);
		beanUI.setItemPageLinkCode(1);
		beanUI.setItemType(type);
		beanUI.setItemXmlPath(xmlPath);
		beanUI.setLastupdated(timestamp);
		beanUI.setLastupdatedby(5000);
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(beanUI);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			if (tx != null){
				tx.rollback();
			}	
			return false;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
	}

	@Override
	public List<Village> getVillageListByVillageCodes(String entityCode, int type) throws Exception {

		return villageDAO.getVillageList(entityCode, type);

	}

	@Override
	public List<Village> getVillageListbySubDistrictWithSDName(int subDistrictCode) throws Exception {
		return villageDAO.getVillageListbySubDistrictWithSDName(subDistrictCode);
	}

	public GovernmentOrder saveDataInGovtOrder(VillageForm govtForm, Session session) {

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
			log.debug("Exception" + e);
		}
		return governmentOrder;
	}

	public void saveDataInAttachment(GovernmentOrder govtOrder, VillageForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws Exception {

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
			log.debug("Exception" + e);
		}
	}

	

	@Override
	public List<StateWiseEntityDetails> getStateWiseVillageList(Integer statecode, char entitytype, Integer villageCode, String villageName, Integer limit, Integer offset) throws Exception {
		List<StateWiseEntityDetails> StateWiseVillageList = new ArrayList<StateWiseEntityDetails>();
		StateWiseVillageList = villageDAO.getStateWiseVillageList(statecode, entitytype, villageCode, villageName, limit, offset);
		return StateWiseVillageList;
	}

	@Override
	public List<StateWiseEntityDetails> getParentWiseList(char entitytype, Integer parentCode, Integer limit, Integer offset) throws Exception {
		List<StateWiseEntityDetails> StateWiseVillageList = new ArrayList<StateWiseEntityDetails>();
		StateWiseVillageList = villageDAO.getParentWiseList(entitytype, parentCode, limit, offset);
		return StateWiseVillageList;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getVillListbySubDistCodeShift(int subDistrictCode) throws Exception {
		List<Village> vlgLst = new ArrayList<Village>();
		Query query = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Village v where v.tlc=" + subDistrictCode + " and isactive=true order by v.villageNameEnglish");
			vlgLst = query.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return vlgLst;

	}

	@Override
	public void saveDataInAttachment(GovernmentOrder govtOrder, List<AttachedFilesForm> attachedList, Session session) throws Exception {

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
					session.merge(attachment);
					session.flush();
					if (session.contains(attachment)){
						session.evict(attachment);
					}	
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
	}

	@Override
	public boolean saveDataInMap(VillageForm villageForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception {
		return villageDAO.saveDataInMap(villageForm, attachedList, session);

	}

	@Override
	public boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm, List<AttachedFilesForm> attachedList, int vilcode, HttpSession session) throws Exception {
		return villageDAO.saveDataInAttach(governmentOrderForm, attachedList, vilcode, session);

	}

	@Override
	public String insertVillage(Integer stateCode,VillageForm villageForm, HttpServletRequest request, HttpSession session) {

		return villageDAO.insertVillage(stateCode,villageForm, request, session);

	}
	/**
	 * For save or publish the draft Village details in manage draft village Form.
	 */
	@Override
	public String insertVillageModify(Integer stateCode,VillageForm villageForm, HttpServletRequest request, HttpSession session) {

		return villageDAO.insertVillageModify(stateCode,villageForm, request, session);

	}

	

	public List<VillagePartsBySurveyno> getSurveyNumber(String surveyList) throws Exception {
		return villageDAO.getSurveyNumber(surveyList);
	}

	/*modified by pooja on 22-07-2015*/
	@Override
	public Character VilageExist(int b, String c) {
		// TODO Auto-generated method stub
		return villageDAO.VilageExist(b, c);
	}

	/* Retrieve files from the attachment table */

	public List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws Exception {
		return villageDAO.getAttacmentdetail(orderCode);
	}

	/* Retrieving the Map details attachment from the database */

	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			return villageDAO.getEntityWiseMapDetails(entityType, entityCode, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return null;

	}

	@Override
	public int saveDataInAttach(GovernmentOrderForm governmentOrderForm, List<AttachedFilesForm> validFileGovtUpload, HttpSession session, Session session2) {

		return villageDAO.saveDataInAttachment(governmentOrderForm, validFileGovtUpload, session, session2);
	}

	public PesApplicationMaster getApplicationURL(int applicationId) {

		return villageDAO.getApplicationURL(applicationId);
	}

	@SuppressWarnings("unchecked")
	public List<Village> getVillageByBlockList(String blockcode) throws Exception {

		return villageDAO.getVillageByBlockList(blockcode);

	}

	@Override
	public boolean addvillagegenerate(VillageForm villageForm, HttpServletRequest request, HttpSession httpSession, GovernmentOrderForm govtOrderForm) throws Exception {

		Integer vcode = null;
		String tid = null;
		String vc = null;
		int Emailflag = 0;
		int Transactionid = 0;
		int orderCode = 0;
		villageForm.setOrderNo(govtOrderForm.getOrderNo());
		villageForm.setEffectiveDate(govtOrderForm.getEffectiveDate());
		villageForm.setGazPubDate(govtOrderForm.getGazPubDate());
		villageForm.setOrderDate(govtOrderForm.getOrderDate());
		String villagedata = villageDAO.insertVillage(0,villageForm, request, httpSession);
		boolean flag = false;
		if (villagedata != null) {
			String[] lbp = villagedata.split(",");
			vc = lbp[0];
			tid = lbp[1];
			vcode = Integer.parseInt(vc);
			orderCode = Integer.parseInt(lbp[2]);
		}
		if (vcode != null) {
			flag = true;
			// Data and upload file
			if (httpSession.getAttribute("validFileMap") != null) {
				List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
				villageForm.setVillageCode(vcode);
				villageDAO.saveDataInMap(villageForm, validFileMap, httpSession);
			}
			Transaction tx1 = null;
			Session sessionObj2 = sessionFactory.openSession();
			tx1 = sessionObj2.beginTransaction();
			try {
				govtOrderForm.setOrderCode(orderCode);
				govtOrderService.saveDataInAttachment(govtOrderForm, null, httpSession, orderCode, sessionObj2);
				tx1.commit();
			} catch (Exception e) {
				tx1.rollback();

			} finally {

				if (sessionObj2 != null && sessionObj2.isOpen()){
					sessionObj2.clear();
					sessionObj2.close();
				}	
				
			}
			Emailflag = 1;
			if (tid != null) {
				Transactionid = Integer.parseInt(tid);
				Emailflag = 1;
			}
			if (Emailflag == 1) {
				int t_code = Transactionid;
				char t_type = 'V';
				EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
				est.start();
			}

		}
		httpSession.removeAttribute("govtform");

		return flag;
	}

	@Override
	public List<Village> getmapUnmapVillageList(int slc, int dlc, int tlc, int type) throws Exception {
		return villageDAO.getmapUnmapVillageList(slc, dlc, tlc, type);
	}

	@Override
	public List<Village> getVillageSuerveryNo(String vCode) throws Exception {
		return villageDAO.getVillageSuerveryNo(vCode);
	}
	/**
	 * To get the contributing villages with its opearion state (modify,save and publish state).
	 */
	@Override
	public List<Village> getVillagebysubdisrictCodes(String vCode) throws Exception {
		return villageDAO.getVillagebysubdisrictCodes(vCode);
	}
	/**
	 * To get the contributing villages with its opearion state (modify,save and publish state).
	 */
	@Override
	public List<Village> getVillageListWithOperationState(int subDistrictCode) throws Exception {
		return villageDAO.getVillageListbySubDistrictCodeWithOperationState(subDistrictCode);
	}
	/**
	 * To get the draft village details statewise.
	 */
	@Override
	public List<VillageDraft> getVillageDraftList(int stateCode) throws Exception {
		return villageDAO.getVillageDraftList(stateCode);
	}
	/**
	 * To get the draft village details.
	 */
	@Override
	public List<VillageDraft> getVillageDraftDetails(int villageCode) throws Exception {
		return villageDAO.getVillageDraftDetails(villageCode);
	}
	/**
	 * To save the draft village upload map details in Create Village Form.
	 */
	@Override
	public boolean saveDataInMapDraft(VillageForm villageForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception {
		return villageDAO.saveDataInMapDraft(villageForm, attachedList, session);

	}
	@Override
	public List<Village> getDraftVillageListWithOperationState(int subDistrictCode,int draftVilCode) throws Exception {
		return villageDAO.getDraftVillageListbySubDistrictCodeWithOperationState(subDistrictCode,draftVilCode);
	}
	
	@Override
	public boolean saveDataInMapDraftVilModify(VillageDataForm villageDataForm, HttpSession session) throws Exception {
		return villageDAO.saveDataInMapDraftVilModify(villageDataForm, session);

	}
	/**
	 * To save the draft village govt order details in Create Village Form.
	 */
	@Override
	public boolean saveDataInAttachDraftVilCreate(VillageForm villageForm, List<AttachedFilesForm> attachedList, int vilcode, HttpSession session) throws Exception {
		return villageDAO.saveDataInAttachDraftVilCreate(villageForm, attachedList, vilcode, session);

	}
	/**
	 * To publish the Draft Village Details.
	 */
	@Override
	public String publishDraftVillageModify(Integer stateCode, VillageDraft villageDraft,
			HttpServletRequest request, HttpSession session) throws Exception {
		return villageDAO.publishDraftVillageModify(stateCode,villageDraft, request, session);
	}
	/**
	 * To delete the Draft Village Details for a particular village.
	 */
	@Override
	public String deleteDraftVillageModify(int villageCode,HttpServletRequest request, HttpSession session) throws Exception {
		return villageDAO.deleteDraftVillageModify(villageCode, request, session);
	}
	/**
	 * To save the draft village govt order details in Modify Village Form.
	 */
	@Override
	public boolean saveDataInAttachDraftVillageModify(
			GovernmentOrderForm governmentOrderForm,
			List<Attachment> attachedList, int vilcode, HttpSession session)
			throws Exception {
		return villageDAO.saveDataInAttachDraftVillageModify(governmentOrderForm, attachedList, vilcode, session);
		
	}
	

	/**
	 * To save the draft village upload map details in Modify Village Form
	 */
	@Override
	public boolean saveDataInMapDraftVillageModify(VillageForm villageForm,
			List<MapAttachment> attachedList, HttpSession session)
			throws Exception {
		// TODO Auto-generated method stub
		return villageDAO.saveDataInMapDraftVillageModify(villageForm, attachedList, session);
	}
	/**
	 * 
	 */
	@Override
	public List<Village> getVillageListbySubDistrictWithSDNameExtended(int subDistrictCode,int orgCode,int locatedLevelCode) throws Exception {
		return villageDAO.getVillageListbySubDistrictWithSDNameExtended(subDistrictCode,orgCode,locatedLevelCode);
	}

	
	/*
	 * added on 11/12/2014 for localbody impact
	 * 
	 */
	@Override
	public	List<Village> getVillagebysubdisrictCodesForLocalBody(String vCode) throws Exception{
		 return villageDAO.getVillagebysubdisrictCodesForLocalBody(vCode);
	}
	/**
	 * Add to get Villages from selected block for localbody Draft Mode.
	 * @author Ripunj on 07-01-2015 
	 * @param blockcode
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Village> getVillageByBlockListForLocalBody(String blockcode)
			throws Exception {
		List<Integer> blockCodeList=new ArrayList<Integer>();
		blockCodeList.add(-1);
		Scanner scanner = new Scanner(blockcode);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			int value=Integer.parseInt(scanner.next());
			blockCodeList.add(value);
		}
		scanner.close();
		return villageDAO.getVillageByBlockListForLocalBody(blockCodeList);
	}
	
	
	
	/**
	 * added on 07/01/2015 by kirandeep for localbody impact issue.
	 */
	public List<Village> getVillageListbySubDistrictCodeCov(int subDistrictCode) throws Exception{
		
		return villageDAO.getVillageListbySubDistrictCodeCov(subDistrictCode);
		
	}
	
	
	/**
	 * 
	 * added by kirandeep on 08/01/2015 for local body impact
	 * @param subDistrictCode
	 * @return
	 * @throws Exception
	 */
	
	public List<Village> getVillageListbySubDistrictWithSDNameForLocalbody(int subDistrictCode) throws Exception{
		
		return villageDAO.getVillageListbySubDistrictWithSDNameForLocalbody(subDistrictCode);
		
	}
	
	/**
	 * To save the draft rename village govt order details in Draft Village Form.
	 */
	@Override
	public boolean saveDataInAttachDraftRenameVilCreate(VillageForm villageForm, List<AttachedFilesForm> attachedList, int vilcode, HttpSession session) throws Exception {
		return villageDAO.saveDataInAttachDraftRenameVilCreate(villageForm, attachedList, vilcode, session);

	}
	
	/**
	 * Added by Ripunj for rename draft village modify on 09-03-2015
	 * @param villageForm
	 * @param govtOrderForm
	 * @param attachedList
	 * @param request
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	
	// modify Village Change Upload
		@SuppressWarnings("null")
		@Override
		public synchronized boolean changeVillageModify(VillageForm villageForm, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request, int uid) throws Exception {
			char t_type = 'V';
			int Transactionid = 0;
			String govtOrderConfig ="";
			try {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				
				int villageVersion = 0;
				List<VillageDataForm> listVillages = new ArrayList<VillageDataForm>();
				listVillages = villageForm.getListVillageDetails();
				Iterator<VillageDataForm> itr = listVillages.iterator();
				int orderCodeForVilGovt=0;
				if(villageForm.getOrderCode()!=null && !villageForm.getOrderCode().equals("")){
					orderCodeForVilGovt=villageForm.getOrderCode();
				}
				boolean result = false;
				if(villageForm.getGovtOrderConfig()!=null && !"".equals(villageForm.getGovtOrderConfig()))
					govtOrderConfig = villageForm.getGovtOrderConfig();
				while (itr.hasNext()) {

					VillageDataForm element = (VillageDataForm) itr.next();
					Date orderDate = element.getOrderDatecr();
					Date effectiveDate = element.getOrdereffectiveDatecr();
					Date gazPubDate = element.getGazPubDatecr();
					java.sql.Timestamp gazPubtimeStampDateTemp = null;
					final java.sql.Timestamp ordertimeStampDate = new Timestamp(orderDate.getTime());
					final java.sql.Timestamp effectivetimeStampDate = new Timestamp(effectiveDate.getTime());
					if (gazPubDate != null) {
						gazPubtimeStampDateTemp = new Timestamp(gazPubDate.getTime());
					}
					final java.sql.Timestamp gazPubtimeStampDate = gazPubtimeStampDateTemp;
					
					villageVersion = villageDAO.getMaxVillageVersionbyCode(element.getVillageCode());
					if (villageVersion == 1) {
						villageVersion = villageVersion + 1;
					} else {
						villageVersion = villageVersion + 1;
					}
					if(('P')==(villageForm.getButtonClicked())){
						String ordercode = villageDAO.ChangeVillage(villageForm.getRenameVillageCode(), uid, element.getVillageNameEnglishCh(), effectivetimeStampDate, element.getVillageNameLocalCh(), element.getAliasEnglishCh(), element.getAliasLocalCh(),
								session, element.getOrderNocr(), ordertimeStampDate, gazPubtimeStampDate,villageForm.getButtonClicked(),villageForm.getIsExistingGovtOrder(),orderCodeForVilGovt,govtOrderConfig);

						if (ordercode != null) {
							String[] ldata = ordercode.split(",");
							String orderCode = ldata[0];
							String tid = ldata[1];

							Transactionid = Integer.parseInt(tid);
							int ocode = Integer.parseInt(orderCode);
							if("N".equals(villageForm.getIsExistingGovtOrder())){
								GovernmentOrder govtOrder = new GovernmentOrder();
								govtOrder.setOrderDate(ordertimeStampDate);
								govtOrder.setEffectiveDate(effectivetimeStampDate);
								govtOrder.setGazPubDate(gazPubtimeStampDate);
								govtOrder.setCreatedon(new Date());
								govtOrder.setDescription("LGD DETAILS");
								govtOrder.setIssuedBy("GOVERNOR");
								govtOrder.setCreatedby(createdBy);
								govtOrder.setLastupdated(new Date());
								govtOrder.setLastupdatedby(createdBy);
								govtOrder.setLevel("U");
								govtOrder.setOrderNo(element.getOrderNocr());
								govtOrder.setStatus('A');
								govtOrder.setUserId(userId);
								govtOrder.setOrderCode(ocode);
								govtOrder.setOrderPath(govtOrderConfig);
								convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm, attachedList, session);
							}
							tx.commit();
							result = true;
							EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
							est.start();
						}
					}else{
						VillageDraft vd =new VillageDraft();
						List<VillageDraft> listVillageDetail = villageDAO.getVillageDraftDetails(element.getVillageCode());
						vd=listVillageDetail.get(0);
						vd.setVillageNameEnglish(element.getVillageNameEnglishCh());
						vd.setVillageCode(element.getVillageCode());
						vd.setSubDistrict(element.getSubdistrictCode());
						vd.setUserId(uid);
						vd.setOrderNo(element.getOrderNocr().trim());
						vd.setOrdertimeStampDate(ordertimeStampDate);
						vd.setEffectivetimeStampDate(effectivetimeStampDate);
						vd.setGovtOrder(villageForm.getGovtOrderConfig());
						vd.setOperationCode(11);
						//vd.setFlagCode(flagCode);
						vd.setGazPubtimeStampDate(gazPubtimeStampDate);
						vd.setVillageNameLocale(element.getVillageNameLocalCh());
						vd.setVillageNameAliasEn(element.getAliasEnglishCh());
						vd.setVillageNameAliasLocal(element.getAliasLocalCh());
						vd.setOrdercode(villageForm.getOrderCode());
						//vd.setDistrictCode(Integer.parseInt(element.getDistrictNameEnglish()));
						vd.setIsExistGovtOrder(villageForm.getIsExistingGovtOrder());
						Attachment atDetails = new Attachment();
						if (request.getAttribute("attachmentList") != null && villageForm.getGovtfilecount().equalsIgnoreCase("1")) {
							List<Attachment> atList = (ArrayList<Attachment>) request.getAttribute("attachmentList");
							atDetails = atList.get(0);
							vd.setGovt_order_file_content_type(atDetails.getFileContentType());
							vd.setGovt_order_file_location(atDetails.getFileLocation());
							vd.setGovt_order_file_name(atDetails.getFileName());
							vd.setGovt_order_file_size(atDetails.getFileSize());
							vd.setGovt_order_file_timestamp(atDetails.getFileTimestamp());
						}
						session.saveOrUpdate(vd);
						//vd.setOrdercode(vilForm.getOrderCode());
						//villageForm.setRenameVillageCode(Integer.parseInt(ordercode));
						tx.commit();
						result = true;
					}
				}
				return result;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.debug("Exception" + e);
				tx.rollback();
				return false;

			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}	
			}

		}
		@SuppressWarnings("null")
		@Override
		public synchronized boolean changeVillageModifyPublish(VillageForm villageForm, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, HttpServletRequest request, int uid) throws Exception {
			char t_type = 'V';
			int Transactionid = 0;
			String govtOrderConfig = "";
			try {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				
				int villageVersion = 0;
				List<VillageDataForm> listVillages = new ArrayList<VillageDataForm>();
				listVillages = villageForm.getListVillageDetails();
				Iterator<VillageDataForm> itr = listVillages.iterator();
				int orderCodeForVilGovt=0;
				if(villageForm.getOrderCode()!=null && !villageForm.getOrderCode().equals("")){
					orderCodeForVilGovt=villageForm.getOrderCode();
				}
				boolean result = false;
				if((villageForm.getGovtOrderConfig() != null) && !(("").equals(villageForm.getGovtOrderConfig())))
					govtOrderConfig = villageForm.getGovtOrderConfig();
				while (itr.hasNext()) {

					VillageDataForm element = (VillageDataForm) itr.next();
					Date orderDate = element.getOrderDatecr();
					Date effectiveDate = element.getOrdereffectiveDatecr();
					Date gazPubDate = element.getGazPubDatecr();
					if(element.getOrderCode()!=null && !element.getOrderCode().equals("")){
						orderCodeForVilGovt=element.getOrderCode();
					}
					java.sql.Timestamp gazPubtimeStampDateTemp = null;
					final java.sql.Timestamp ordertimeStampDate = new Timestamp(orderDate.getTime());
					final java.sql.Timestamp effectivetimeStampDate = new Timestamp(effectiveDate.getTime());
					if (gazPubDate != null) {
						gazPubtimeStampDateTemp = new Timestamp(gazPubDate.getTime());
					}
					final java.sql.Timestamp gazPubtimeStampDate = gazPubtimeStampDateTemp;
					
					villageVersion = villageDAO.getMaxVillageVersionbyCode(element.getVillageCode());
					if (villageVersion == 1) {
						villageVersion = villageVersion + 1;
					} else {
						villageVersion = villageVersion + 1;
					}
						String ordercode = villageDAO.ChangeVillage(villageForm.getRenameVillageCode(), uid, element.getVillageNameEnglishCh(), effectivetimeStampDate, element.getVillageNameLocalCh(), element.getAliasEnglishCh(), element.getAliasLocalCh(),
								session, element.getOrderNocr(), ordertimeStampDate, gazPubtimeStampDate,'P',element.getIsExistGovtOrder(),orderCodeForVilGovt,govtOrderConfig);

						if (ordercode != null) {
							String[] ldata = ordercode.split(",");
							String orderCode = ldata[0];
							String tid = ldata[1];

							Transactionid = Integer.parseInt(tid);
							int ocode = Integer.parseInt(orderCode);
							if("N".equals(villageForm.getIsExistingGovtOrder())){
								GovernmentOrder govtOrder = new GovernmentOrder();
								govtOrder.setOrderDate(ordertimeStampDate);
								govtOrder.setEffectiveDate(effectivetimeStampDate);
								govtOrder.setGazPubDate(gazPubtimeStampDate);
								govtOrder.setCreatedon(new Date());
								govtOrder.setDescription("LGD DETAILS");
								govtOrder.setIssuedBy("GOVERNOR");
								govtOrder.setCreatedby(createdBy);
								govtOrder.setLastupdated(new Date());
								govtOrder.setLastupdatedby(createdBy);
								govtOrder.setLevel("U");
								govtOrder.setOrderNo(element.getOrderNocr());
								govtOrder.setStatus('A');
								govtOrder.setUserId(userId);
								govtOrder.setOrderCode(ocode);
								govtOrder.setOrderPath(govtOrderConfig);
								convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm, attachedList, session);
							}
							tx.commit();
							result = true;
							EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
							est.start();
						}
					
				}
				return result;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.debug("Exception" + e);
				tx.rollback();
				return false;

			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}	
			}

		}

		@Override
		public List<Village> getmapUnmapVillageListInvalidateDraftMode(int slc,
				int dlc, int tlc, int type,int draftVillageCode) throws Exception {
			// TODO Auto-generated method stub
			return villageDAO.getmapUnmapVillageListInvalidateDraftMode(slc,dlc,tlc,type,draftVillageCode);
		}

		@Override
		public String invalidateVillageDAOModify(String villCode,
				Integer userId, String orderNumber, Date orderDateCR,
				Date effectiveDateCR, String govtOrder, Date gzbDateCR,
				String destinationVillage, Integer distinationSubdistrict,
				Session session, Integer ulb, Integer orderCode,
				String isExistGovtOrder, Character operationState,
				Integer draftVillageCode) throws Exception {
			// TODO Auto-generated method stub
			return villageDAO.invalidateVillageDAOModify(villCode, userId, orderNumber, orderDateCR, effectiveDateCR, govtOrder, gzbDateCR, destinationVillage, distinationSubdistrict, session, ulb, orderCode, isExistGovtOrder, operationState, draftVillageCode);
		}
		/**
		 * Get Draft Village Entities by DistrictCode
		 * @author Ripunj on 09-04-2015
		 * @param districtCode
		 * @return
		 * @throws Exception
		 */
		@Override
		public List<VillageDraft> getVillageDraftListByDistrictCode(
				int districtCode) throws Exception {
			// TODO Auto-generated method stub
			return villageDAO.getVillageDraftListByDistrictCode(districtCode);
		}
		
		
		/**
		 * author Anju Gupta on 30/03/2015
		 * for villageList for Pesa Land Regions
		 */
		public List<Village> villageListbySlctoIspesa(int slc) throws Exception {
			return villageDAO.villageListbySlctoIspesa(slc);
		}
		
		
		/**
		 *  Using DWR Village List populate base on Creteria for extend organigation Units
		 *  @author Maneesh Kumar 21-July-2015
		 */
		@Override
		public List<Village> getVillageListbyCreteria(String districtCodes, String subDistrictCodes,String villageCodes,Integer subDistrictCode) throws Exception {
			List<Integer>  districtList=districtCodes!=null?commonService.createListFormString(districtCodes):null;
			List<Integer> subDistrictCodeList=subDistrictCodes!=null?commonService.createListFormString(subDistrictCodes):null;
			List<Integer> villageCodeList=villageCodes!=null?commonService.createListFormString(villageCodes):null;
				return villageDAO.getVillageListbyCreteria(districtList, subDistrictCodeList,villageCodeList,subDistrictCode);
			
		}
		
		/**
		 * Get Invalidate Village is mapped or unmapped
		 * @param invalidateVillageCodeList
		 * @author Pooja Sharma 29-07-2015
		 */
		@Override
		public Boolean[] isMappedOrUnmapped(Integer[] invalidateVilllageCodeList) throws Exception {
			return villageDAO.isMappedOrUnmapped(invalidateVilllageCodeList);
		}
		/**
		 * Get LocalBodyDetails by SubdistrictCodes
		 * @param subDistricts
		 * @author Pooja Sharma on 02-09-2015
		 */
		@Override
		public List<GetLBDetailsBySubDistricts> getLBDetailsBySubDistricts(String subDistricts) throws Exception{
			return villageDAO.getLBDetailsBySubDistricts(subDistricts);
		}
		
		@Override
		public String getMaxPreVersionEffDateOfVillages(String villages) throws Exception {
			return villageDAO.getMaxPreVersionEffDateOfVillages(villages);
		}
		
		/**
		 *  @author Maneesh Kumar 09MAy2016
		 */
		@SuppressWarnings("resource")
		@Override
		public String getISPesaFlag(String villageCodes)throws Exception{
		
			List<Integer> villageCodeList=null;
			if(villageCodes!=null && !("").equals(villageCodes.trim())){
				villageCodeList = new ArrayList<Integer>();
				Scanner scanner = new Scanner(villageCodes);
				scanner.useDelimiter("@");
				while (scanner.hasNext()) {
					villageCodeList.add(Integer.parseInt(scanner.next()));
				}
			}
			
			return villageDAO.getISPesaFlag(villageCodeList);
		}

		@Override
		public String getMappedVillageForGIS(Integer subdistrictCode, Integer villageCode, String villageName,String coverage, String isShowOnlyBoundary) throws IOException {
			String gisVillageURL=null;
			try {
		
					Properties properties = new Properties();
					InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");
					properties.load(inputStreamPro);
					String gisServerLoc=properties.getProperty("gisMap.server.location");
					String ensubdistCode=EncryptionUtil.encode(subdistrictCode.toString());
					if(villageCode!=null) {
						String status=villageDAO.checkVillageNewforMap(villageCode);
						if(status!=null && ("FV").equals(status )){
							String enisShowOnlyBoundary=EncryptionUtil.encode(status);
							String envillageName=EncryptionUtil.encode(villageName);
							String envillageCode=EncryptionUtil.encode(villageCode.toString());
							coverage=villageDAO.getContributingLandregion(villageCode, 'V'); 
							String encoverage=EncryptionUtil.encode(coverage);
							gisVillageURL = gisServerLoc+"?subdistCode=" + ensubdistCode +"&villageCode="+envillageCode+"&villageName="+envillageName+"&coverage="+encoverage+"&isShowOnlyBoundary="+enisShowOnlyBoundary;
						}else{
							String envillageName=EncryptionUtil.encode(villageName);
							String enisShowOnlyBoundary=EncryptionUtil.encode(isShowOnlyBoundary);
							
							String envillageCode=EncryptionUtil.encode(villageCode.toString());
							gisVillageURL = gisServerLoc+"?subdistCode=" + ensubdistCode +"&villageCode="+envillageCode+"&villageName="+envillageName+"&isShowOnlyBoundary="+enisShowOnlyBoundary;
							
						
						}
					}else {
						String envillageName=EncryptionUtil.encode(villageName);
						String enisShowOnlyBoundary=EncryptionUtil.encode(isShowOnlyBoundary);
						String encoverage=EncryptionUtil.encode(coverage);
						gisVillageURL = gisServerLoc+"?subdistCode=" + ensubdistCode +"&villageName="+envillageName+"&coverage="+encoverage+"&isShowOnlyBoundary="+enisShowOnlyBoundary;
						}
					
				return gisVillageURL;
			} catch (Exception e) {
				e.printStackTrace();
				return "FAILED";
			}
		}

		@Override
		public String finalizeVillageGIS(Integer villageCode) throws Exception {
			return villageDAO.finalizeVillageGIS(villageCode);
		}
		
		
		@Override
		public String getUnMappedVillageForGIS(Integer subdistrictCode, String isShowOnlyBoundary) throws IOException {
			String gisVillageURL=null;
			try {
				
				Properties properties = new Properties();
				InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");
				properties.load(inputStreamPro);
				String gisServerLoc=properties.getProperty("gisMap.server.location");
				if(subdistrictCode!=null){
					String ensubdistCode=EncryptionUtil.encode(subdistrictCode.toString());
					String enisShowOnlyBoundary=EncryptionUtil.encode(isShowOnlyBoundary);
					gisVillageURL = gisServerLoc+"?subdistCode=" + ensubdistCode +"&isShowOnlyBoundary="+enisShowOnlyBoundary;
				}
				return gisVillageURL;
			} catch (HibernateException e) {
				e.printStackTrace();
				return "FAILED";
			}
		}
		
		@Override
		public Boolean checkUnMappedPolygonByState(Integer stateCode) {
			return villageDAO.checkUnMappedPolygonByState(stateCode);
		}
		
		@Override
		public List<in.nic.pes.lgd.bean.District> getUnMappedPolygonDistrictByState(Integer stateCode) {
			return villageDAO.getUnMappedPolygonDistrictByState(stateCode);
		}
		
		@Override
		public List<Subdistrict> getUnMappedPolygonSubDistrictByDistrict(Integer districtCode) {
			return villageDAO.getUnMappedPolygonSubDistrictByDistrict(districtCode);
		}

		@Override
		public String insertVillageGisMapStatus(Integer villageCode) throws HibernateException {
			return villageDAO.insertVillageGisMapStatus(villageCode);
		}

		@Override
		public HabitationConfiguration getStateWiseHabitationConfiguration(Integer slc) throws Exception {
			return villageDAO.getStateWiseHabitationConfiguration(slc);
		}

		@Override
		public boolean habitationNameUniquewithParent(String habitationNameEng, Integer parentCode, String parentType)throws Exception {
			return villageDAO.habitationNameUniquewithParent(habitationNameEng, parentCode, parentType);
		}

		@Override
		public List<Habitation> getHabitationList(String villageCodes) throws Exception {
			List<Integer> villageCodeList = new ArrayList<Integer>();
			villageCodeList.add(-1);
			if(villageCodes!=null)
			if (villageCodes.contains(",")) {
				Scanner scanner = new Scanner(villageCodes);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					villageCodeList.add(Integer.parseInt(scanner.next()));
				}
			scanner.close();
			}else{
				villageCodeList.add(Integer.parseInt(villageCodes));
			}
			
			
			return villageDAO.getHabitationList(villageCodeList);
		}

		@Override
		public void saveHabitationLocalbody(LocalBodyForm localBodyForm) throws Exception {
			villageDAO.saveHabitationLocalbody(localBodyForm);
		}

		@Override
		public List<Habitation> getMappedHabitationList(Integer localBodyCode) throws Exception {
			return villageDAO.getMappedHabitationList(localBodyCode);
		}

		@Override
		public void saveHabitation(HabitationForm habitationForm) throws Exception {
			villageDAO.saveHabitation(habitationForm);
			
		}

		@Override
		public boolean validateParentCode(Integer parentCode, String parentType, Integer slc) throws Exception {
			return villageDAO.validateParentCode(parentCode, parentType, slc);
		}

		@Override
		public void saveHabitationConfiguration(HabitationConfiguration habitationConfiguration)throws Exception {
			 villageDAO.saveHabitationConfiguration(habitationConfiguration);

			
		}

		@Override
		public boolean validateStateWiseHabitation(Integer stateCode)throws Exception {
			return villageDAO.validateStateWiseHabitation(stateCode);
		}


		@Override
		public List<Habitation> getHabitationList(int parentCode, int stateCode) {
			return villageDAO.getHabitationList(parentCode, stateCode);
		}

		@Override
		public Habitation getHabitationDetails(int habitationId,int habitationVersion) {
			return villageDAO.getHabitationDetails(habitationId,habitationVersion);
		}

		@Override
		public boolean updateHabitation(Habitation habitation) {
			return villageDAO.updateHabitation(habitation);
		}
		
		
		@Override
		public String getHabitationConfigrationLocalbody(Integer slc)throws Exception{
			HabitationConfiguration habitationConfiguration =villageDAO.getStateWiseHabitationConfiguration(slc);
			if(habitationConfiguration!=null){
			return habitationConfiguration.getParentType();
			}else{
				return CommanConstant.HABITHABITATION_PARENT_TYPE_NOT_CONFIGURE.toString();
			}
		}

		@Override
		public Map<String, Object> getEntityEffeactiveDate(Integer entityCode, Character entityType)throws HibernateException {
			return villageDAO.getEntityEffeactiveDate(entityCode,entityType);
		}

		@Override
		public Response saveEffectiveDateEntity(List<GetEntityEffectiveDate> getEntityEffectiveDateList,Integer userId) {
			return villageDAO.saveEffectiveDateEntity(getEntityEffectiveDateList,userId);
		}

		@Override
		public List<GetEntityEffectiveDate> getEntityEffeactiveDateByVersion(GetEntityEffectiveDate getEntityEffectiveDate)
				throws HibernateException {
			return villageDAO.getEntityEffeactiveDateByVersion(getEntityEffectiveDate);
		}
		
		
		@Override
		public boolean saveVillageStatus(Integer villageCode, String villageStatus, Long userId) throws Exception {
			// TODO Auto-generated method stub
			return villageDAO.saveVillageStatus(villageCode, villageStatus, userId);
		}

		@Override
		public boolean validateNewEffectiveDate(Integer villageCode,List<GetEntityEffectiveDate> getEntityEffectiveDateList,
				List<GetEntityEffectiveDate> effectiveDateListOld) {
			
			return villageDAO.validateNewEffectiveDate(villageCode, getEntityEffectiveDateList, effectiveDateListOld);
		}

		
		
}
