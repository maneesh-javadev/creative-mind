package in.nic.pes.lgd.service.impl;

import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.ChangeCoverageNameParentofDisturbLocalbody;
import in.nic.pes.lgd.bean.ChangeLocalBodyCoveredArea;
import in.nic.pes.lgd.bean.ChangeLocalBodyName;
import in.nic.pes.lgd.bean.ChangeLocalBodyUrbanType;
import in.nic.pes.lgd.bean.ChangeLocalBodypriType;
import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.ConfigurationMapLocalbody;
import in.nic.pes.lgd.bean.ConstituencyLocalbody;
import in.nic.pes.lgd.bean.ConstituencyMapDetailsbyacCode;
import in.nic.pes.lgd.bean.ConstituencyVillage;
import in.nic.pes.lgd.bean.ConstituencyWard;
import in.nic.pes.lgd.bean.CoveredWardLandregion;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GetLandRegionNameforWard;
import in.nic.pes.lgd.bean.GetLandRegionWise;
import in.nic.pes.lgd.bean.GetLocalBodyTypeCode;
import in.nic.pes.lgd.bean.GetLocalGovtBodyTypeList;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.GetSubTypeList;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.Habitation;
import in.nic.pes.lgd.bean.InsertLocalGovtBody;
import in.nic.pes.lgd.bean.LBCoverageWard;
import in.nic.pes.lgd.bean.LgdPfmsMapping;
import in.nic.pes.lgd.bean.LocalBodyCoveredArea;
import in.nic.pes.lgd.bean.LocalBodyCoveredAreaLB;
import in.nic.pes.lgd.bean.LocalBodyDetails;
import in.nic.pes.lgd.bean.LocalBodyParent;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTypeHistory;
import in.nic.pes.lgd.bean.LocalBodyTypeWiseDepartment;
import in.nic.pes.lgd.bean.LocalBodyViewChild;
import in.nic.pes.lgd.bean.LocalGovtBody;
import in.nic.pes.lgd.bean.LocalGovtBodyForSelectedBody;
import in.nic.pes.lgd.bean.LocalGovtBodyNameList;
import in.nic.pes.lgd.bean.LocalGovtBodyWard;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyPK;
import in.nic.pes.lgd.bean.LocalbodyReplaces;
import in.nic.pes.lgd.bean.LocalbodyUnMappedBody;
import in.nic.pes.lgd.bean.LocalbodyUnMappedBodyLevelWise;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.LocalbodyWardId;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.LocalbodyforStateWiseFinal;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MapLocalbody;
import in.nic.pes.lgd.bean.NodalOfficer;
import in.nic.pes.lgd.bean.ParentWiseLBList;
import in.nic.pes.lgd.bean.ParentWiseLocalBodies;
import in.nic.pes.lgd.bean.ParentWiseLocalBodiesWithoutChildCount;
import in.nic.pes.lgd.bean.PartillyMappedLRList;
import in.nic.pes.lgd.bean.PartillyMappedLRListLevelWise;
import in.nic.pes.lgd.bean.PushLBtoPES;
import in.nic.pes.lgd.bean.Pushcoveragetopes;
import in.nic.pes.lgd.bean.SearchLocalGovtBody;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatewisePesaPanchyat;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.UlbBean;
import in.nic.pes.lgd.bean.UnLRDistrictWiseList;
import in.nic.pes.lgd.bean.UnLRSWiseList;
import in.nic.pes.lgd.bean.UnmappedLBList;
import in.nic.pes.lgd.bean.ViewLandRegionDisturbedlist;
import in.nic.pes.lgd.bean.ViewLocalBodyLandRegion;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.localbodywardtemp;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LGBodyCoveredAreaDTO;
import in.nic.pes.lgd.forms.LocalGovtBodyDataForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;
import in.nic.pes.lgd.forms.NodalOfficerForm;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.LocalGovtBodyService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.cmc.lgd.localbody.services.LocalBodyService;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

@Service
@Transactional
@Repository
public class LocalGovtBodyServiceImpl implements LocalGovtBodyService {
	private static final Logger log = Logger.getLogger(LocalGovtBodyServiceImpl.class);
	@Autowired
	private LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DistrictDAO districtDAO;

	@Autowired
	private SubDistrictDAO subdistrictDAO;

	@Autowired
	private VillageDAO villageDAO;

	@Autowired
	private GovernmentOrderService govtOrderService;

	@Autowired
	private ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LocalBodyService localBodyService;

	private int createdBy = 4000;
	Map<Integer, LocalBodyCoveredArea> lbCoveredAreaList = null;

	// get StateWise List
	public List<LocalbodyforStateWise> getLocalBodyListStateWise(char localBodyType, int stateCode) throws Exception {
		return localGovtBodyDAO.getLocalBodyListStateWise(localBodyType, stateCode);
	}

	public List<LocalbodyforStateWise> getLocalBodyListStateWiseChangeType(char localBodyType, int stateCode, int lbtypecode) throws Exception {
		return localGovtBodyDAO.getLocalBodyListStateWiseChangeType(localBodyType, stateCode, lbtypecode);
	}

	// get StateWise List
	public List<LocalbodyforStateWise> getLocalBodyListStateWiseTierSet(char localBodyType, int stateCode, int parentyresetupCd) throws Exception {
		return localGovtBodyDAO.getLocalBodyListStateWiseTierSet(localBodyType, stateCode, parentyresetupCd);
	}

	// get StateWise List
	public List<LocalbodyforStateWise> getLocalBodyListStateWiseTierSetF(char localBodyType, int stateCode, int parentyresetupCd) throws Exception {
		return localGovtBodyDAO.getLocalBodyListStateWiseTierSet(localBodyType, stateCode, parentyresetupCd);
	}

	public List<State> getStateName(int stateCode) throws Exception {
		return localGovtBodyDAO.getStateName(stateCode);
	}

	public List<Localbody> getLocalbodyname(String parentLBodyCode) throws Exception {
		return localGovtBodyDAO.getLocalbodyname(parentLBodyCode);
	}

	public List<LocalbodyforStateWise> getLocalBodystatelist(char localBodyType, int stateCode) throws Exception {
		return localGovtBodyDAO.getLocalBodystatelist(localBodyType, stateCode);
	}

	public int getDistrctCode(HttpSession session) {
		int districtCode = 0;
		try {
			SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
			districtCode = sessionObject.getDistrictCode();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return districtCode;
	}

	/*
	 * @Override public int saveLocalGovtBody(LocalGovtBodyForm
	 * localGovtBodyForm,int stateCode, GovernmentOrderForm
	 * govtOrderForm,List<AttachedFilesForm>
	 * attachedList,List<AttachedFilesForm> attachedMapList, HttpServletRequest
	 * request) { // START VARIBALE DECLARATION
	 * 
	 * int localbodyCodeFULL = 0; int max_lbCoveredRegionCode = 0;
	 * GovernmentOrder govtOrder = null; int maxlb_replaces = 0; int
	 * max_lbReplacedBy = 0; Session session = null; Transaction tx = null;
	 * Localbody localbody = null;
	 * 
	 * // END VARIBALE DECLARATION
	 * 
	 * try { session = sessionFactory.openSession(); tx =
	 * session.beginTransaction(); max_lbCoveredRegionCode =
	 * localGovtBodyDAO.getMaxRecords
	 * ("select COALESCE(max(lb_covered_region_code),1) from lb_covered_landregion"
	 * ); if (max_lbCoveredRegionCode == 0) { max_lbCoveredRegionCode = 1; }
	 * else { max_lbCoveredRegionCode++; } maxlb_replaces =
	 * localGovtBodyDAO.getMaxRecords
	 * ("select COALESCE(max(lb_replaces),1) from localbody_replaces"); if
	 * (maxlb_replaces == 0) { maxlb_replaces = 1; } else { maxlb_replaces++; }
	 * max_lbReplacedBy = localGovtBodyDAO.getMaxRecords(
	 * "select COALESCE(max(lb_replacedby),1) from localbody_replacedby"); if
	 * (max_lbReplacedBy == 0) { max_lbReplacedBy = 1; } else {
	 * max_lbReplacedBy++; }
	 * 
	 * String lgTypeNameId = localGovtBodyForm.getLgd_LBTypeName(); // Saving
	 * New LocalBody Details String[] lgTypeNameArr = lgTypeNameId.split(":");
	 * String lgTypeCode = lgTypeNameArr[0]; String lgTypeName =
	 * lgTypeNameArr[1];
	 * 
	 * if (localGovtBodyForm.isLgd_LBExistCheck()) {
	 * saveDataforExistLocalBodies(localGovtBodyForm,localbodyCodeFULL,
	 * max_lbCoveredRegionCode,maxlb_replaces, max_lbReplacedBy, session,
	 * lgTypeName); }
	 * 
	 * if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
	 * saveDataforUnmappedLocalBodies(localGovtBodyForm,localbodyCodeFULL,
	 * max_lbCoveredRegionCode,maxlb_replaces, max_lbReplacedBy, session,
	 * lgTypeName); }
	 * 
	 * localbody = saveNewLocalBodyDetails(localGovtBodyForm,govtOrderForm,
	 * stateCode, max_lbCoveredRegionCode,maxlb_replaces, lgTypeName,
	 * lgTypeCode, session);
	 * 
	 * Localbody lbUpdate = localbody;
	 * 
	 * session.save(localbody); session.flush();
	 * 
	 * 
	 * if (session.contains(localbody)) session.evict(localbody);
	 * 
	 * if (!localGovtBodyForm.getlatitude().isEmpty() &&
	 * localGovtBodyForm.getlatitude() != null &&
	 * !localGovtBodyForm.getlongitude().isEmpty() &&
	 * localGovtBodyForm.getlongitude() != null) { MapLocalbody mLB =
	 * saveDataInMapLB(localGovtBodyForm, lbUpdate,session);
	 * lbUpdate.setMapLocalbodyCode(mLB.getMapLocalbodyCode());
	 * session.update(lbUpdate); }
	 * 
	 * saveDataInReplacedBy(localbody, max_lbReplacedBy, session);
	 * saveDataInHeadquarter(localbody, localGovtBodyForm, lgTypeName,session);
	 * 
	 * // for saving Upload Map attachment if (attachedMapList != null &&
	 * !attachedMapList.isEmpty())
	 * govtOrderService.saveDatainMapAttachment(attachedMapList
	 * ,localbody.getId().getLocalBodyCode(), 'G', session); // end
	 * 
	 * if (govtOrderForm != null) { govtOrder =
	 * convertLocalbodyService.saveDataInGovtOrder(govtOrderForm, session); //
	 * for saving Upload Govt Order Attachment if (attachedList != null &&
	 * !attachedList.isEmpty()) {
	 * convertLocalbodyService.saveDataInAttachment(govtOrder,govtOrderForm,
	 * attachedList, session); }
	 * 
	 * // / End saveDataInGovtOrderEntityWise(govtOrder,
	 * localbody.getId().getLocalBodyCode(), session); } tx.commit();
	 * 
	 * } catch (Exception e) { log.debug("Exception" + e); if(tx != null)
	 * tx.rollback(); return 1; } finally { if(session != null &&
	 * session.isOpen()) session.close(); } return
	 * localbody.getId().getLocalBodyCode(); }
	 */

	@Override
	public String saveLocalGovtBody(LocalGovtBodyForm localGovtBodyForm, int stateCode, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, HttpServletRequest request, Integer userId) {
		// START VARIBALE DECLARATION
		int localbodyCodeFULL = 0;
		//int max_lbCoveredRegionCode = 0;
		//GovernmentOrder govtOrder = null;
		//int maxlb_replaces = 0;
		//int max_lbReplacedBy = 0;
		Session session = null;
		Transaction tx = null;
		//Localbody localbody = null;
		List<InsertLocalGovtBody> localgovtbody = null;
		// int localbodyid = 0;
		String retValue = null;
		// END VARIBALE DECLARATION

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			/*
			 * max_lbCoveredRegionCode = localGovtBodyDAO.getMaxRecords(
			 * "select COALESCE(max(lb_covered_region_code),1) from lb_covered_landregion"
			 * ); if (max_lbCoveredRegionCode == 0) { max_lbCoveredRegionCode =
			 * 1; } else { max_lbCoveredRegionCode++; } maxlb_replaces =
			 * localGovtBodyDAO.getMaxRecords(
			 * "select COALESCE(max(lb_replaces),1) from localbody_replaces");
			 * if (maxlb_replaces == 0) { maxlb_replaces = 1; } else {
			 * maxlb_replaces++; } max_lbReplacedBy =
			 * localGovtBodyDAO.getMaxRecords
			 * ("select COALESCE(max(lb_replacedby),1) from localbody_replacedby"
			 * ); if (max_lbReplacedBy == 0) { max_lbReplacedBy = 1; } else {
			 * max_lbReplacedBy++; }
			 */

			String lgTypeNameId = localGovtBodyForm.getLgd_LBTypeName();

			String[] lgTypeNameArr = lgTypeNameId.split(":");
			String lgTypeCode = lgTypeNameArr[0];
			String lgTypeName = lgTypeNameArr[1];

			if (localGovtBodyForm.isLgd_LBExistCheck()) {
				localgovtbody = saveDataforExistLocalBodies(localGovtBodyForm, localbodyCodeFULL, session, lgTypeName, lgTypeCode, stateCode, userId);
			} else {
				localgovtbody = saveDataforExistLocalBodies(localGovtBodyForm, localbodyCodeFULL, session, lgTypeName, lgTypeCode, stateCode, userId);
			}
			Iterator<InsertLocalGovtBody> localgovtbodyItr = localgovtbody.iterator();
			InsertLocalGovtBody localdata = (InsertLocalGovtBody) localgovtbodyItr.next();
			retValue = localdata.getCreate_localbody_fn();
			log.debug("THE RETURNED LOCAL BODY::::: " + retValue);

			/*
			 * if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
			 * saveDataforUnmappedLocalBodies
			 * (localGovtBodyForm,localbodyCodeFULL,
			 * max_lbCoveredRegionCode,maxlb_replaces, max_lbReplacedBy,
			 * session, lgTypeName); }
			 * 
			 * localbody =
			 * saveNewLocalBodyDetails(localGovtBodyForm,govtOrderForm,
			 * stateCode, max_lbCoveredRegionCode,maxlb_replaces, lgTypeName,
			 * lgTypeCode, session);
			 * 
			 * Localbody lbUpdate = localbody;
			 * 
			 * if (!localGovtBodyForm.getlatitude().isEmpty() &&
			 * localGovtBodyForm.getlatitude() != null &&
			 * !localGovtBodyForm.getlongitude().isEmpty() &&
			 * localGovtBodyForm.getlongitude() != null) { MapLocalbody mLB =
			 * saveDataInMapLB(localGovtBodyForm, lbUpdate,session);
			 * lbUpdate.setMapLocalbodyCode(mLB.getMapLocalbodyCode());
			 * session.update(lbUpdate); }
			 * 
			 * saveDataInReplacedBy(localbody, max_lbReplacedBy, session);
			 * saveDataInHeadquarter(localbody, localGovtBodyForm,
			 * lgTypeName,session);
			 * 
			 * 
			 * if (attachedMapList != null && !attachedMapList.isEmpty())
			 * govtOrderService
			 * .saveDatainMapAttachment(attachedMapList,localbody
			 * .getId().getLocalBodyCode(), 'G', session);
			 * 
			 * if (govtOrderForm != null) { govtOrder =
			 * convertLocalbodyService.saveDataInGovtOrder(govtOrderForm,
			 * session); if (attachedList != null && !attachedList.isEmpty()) {
			 * convertLocalbodyService
			 * .saveDataInAttachment(govtOrder,govtOrderForm, attachedList,
			 * session); }
			 * 
			 * saveDataInGovtOrderEntityWise(govtOrder,
			 * localbody.getId().getLocalBodyCode(), session); }
			 */

			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
			// return 1;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return retValue;
	}

	@Override
	public String saveLocalGovtBodyULB(LocalGovtBodyForm localGovtBodyForm, int stateCode, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, HttpServletRequest request, Integer userId) {
		// START VARIBALE DECLARATION
		int localbodyCodeFULL = 0;
		//int max_lbCoveredRegionCode = 0;
		//GovernmentOrder govtOrder = null;
		//int maxlb_replaces = 0;
		//int max_lbReplacedBy = 0;
		Session session = null;
		Transaction tx = null;
		//Localbody localbody = null;
		List<InsertLocalGovtBody> localgovtbody = null;
		// int localbodyid = 0;
		String returnValue = null;
		// END VARIBALE DECLARATION

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String lgTypeNameId = localGovtBodyForm.getLgd_LBTypeName();

			/*
			 * String[] lgTypeNameArr = lgTypeNameId.split(":"); String
			 * lgTypeCode = lgTypeNameArr[0]; String lgTypeName =
			 * lgTypeNameArr[1];
			 */

			if (localGovtBodyForm.isLgd_LBExistCheck()) {
				localgovtbody = saveDataforExistLocalBodiesULB(localGovtBodyForm, localbodyCodeFULL, session, lgTypeNameId, stateCode, userId);
			} else {
				localgovtbody = saveDataforExistLocalBodiesULB(localGovtBodyForm, localbodyCodeFULL, session, lgTypeNameId, stateCode, userId);
			}
			Iterator<InsertLocalGovtBody> localgovtbodyItr = localgovtbody.iterator();
			InsertLocalGovtBody localdata = (InsertLocalGovtBody) localgovtbodyItr.next();
			returnValue = localdata.getCreate_localbody_fn();
			log.debug("THE RETURNED LOCAL BODY::::: " + returnValue);

			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
			// return 1;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return returnValue;
	}

	/*private void saveDataInHeadquarter(Localbody localbody, LocalGovtBodyForm localGovtBodyForm, String lgTypeName, Session session) {
		Headquarters headQuter = new Headquarters();
		try {
			if (localGovtBodyForm.getHeadQuarterCode() != null && localGovtBodyForm.getHeadQuarterCode() != "") {
				String headquarterCode = null;
				if (localGovtBodyForm.getHeadQuarterCode().contains("FULL")) {
					headquarterCode = localGovtBodyForm.getHeadQuarterCode().replaceAll("FULL", "");
				} else if (localGovtBodyForm.getHeadQuarterCode().contains("PART")) {
					headquarterCode = localGovtBodyForm.getHeadQuarterCode().replaceAll("PART", "");
				}
				if (lgTypeName.equalsIgnoreCase("D")) {

					District dist = getDistrictByCode(Integer.parseInt(headquarterCode), session);
					if (dist != null) {
						headQuter.setHeadquarterNameEnglish(dist.getDistrictNameEnglish());
						headQuter.setHeadquarterLocalName(dist.getDistrictNameLocal());
						headQuter.setLrlc(Integer.parseInt(headquarterCode));
						headQuter.setRegionType('D');
						// headQuter.setRegionVersion(dist.getDistrictVersion());
						headQuter.setRemarks("District Panchayt HeadQuarter");
					}
				}

				else if (lgTypeName.equalsIgnoreCase("I")) {
					Subdistrict subDist = getSubdistrictByCode(Integer.parseInt(headquarterCode), session);
					if (subDist != null) {
						headQuter.setHeadquarterNameEnglish(subDist.getSubdistrictNameEnglish());
						headQuter.setHeadquarterLocalName(subDist.getSubdistrictNameLocal());
						
						 * headQuter.setRegionCode(Integer
						 * .parseInt(headquarterCode));
						 
						headQuter.setRegionType('T');
						// headQuter.setRegionVersion(subDist.getTlc());
						headQuter.setRemarks("SubDistrict Panchayt HeadQuarter");
					}
				}

				else if (lgTypeName.equalsIgnoreCase("V")) {

					Village village = getVillageByCode(Integer.parseInt(headquarterCode), session);
					if (village != null) {
						headQuter.setHeadquarterNameEnglish(village.getVillageNameEnglish());
						headQuter.setHeadquarterLocalName(village.getVillageNameLocal());
						
						 * headQuter.setRegionCode(Integer
						 * .parseInt(headquarterCode));
						 
						headQuter.setRegionType('V');
						// headQuter.setRegionVersion(village.getVlc());
						headQuter.setRemarks("Village Panchayt HeadQuarter");
					}
				}

				headQuter.setHeadquarterVersion(1);
				headQuter.setIsactive(true);
				headQuter.setLocalbody(localbody);

				session.save(headQuter);
				session.flush();
			}
		} catch (Exception e) {

			log.debug("Exception" + e);
		}
	}*/

	/*@Override
	public int saveUrbanLocalGovtBody(LocalGovtBodyForm localGovtBodyForm, int stateCode, GovernmentOrderForm govtOrderForm, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, HttpServletRequest request) {

		int localbodyCodeFULL = 0;
		int max_lbCoveredRegionCode = 0;
		int maxlb_replaces = 0;
		int max_lbReplacedBy = 0;
		Session session = null;
		Transaction tx = null;
		Localbody localbody = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			max_lbCoveredRegionCode = localGovtBodyDAO.getMaxRecords("select COALESCE(max(lb_covered_region_code),1) from lb_covered_landregion");
			maxlb_replaces = localGovtBodyDAO.getMaxRecords("select COALESCE(max(lb_replaces),1) from localbody_replaces");
			max_lbReplacedBy = localGovtBodyDAO.getMaxRecords("select COALESCE(max(lb_replacedby),1) from localbody_replacedby");
			maxlb_replaces = maxlb_replaces + 1;
			max_lbCoveredRegionCode = max_lbCoveredRegionCode + 1;
			max_lbReplacedBy++;

			String lgTypeName = localGovtBodyForm.getLgd_LBTypeName();

			if (localGovtBodyForm.isLgd_LBExistCheck()) {

				saveDataforExistUrbanLocalBodies(localGovtBodyForm, max_lbCoveredRegionCode, maxlb_replaces, max_lbReplacedBy, session, lgTypeName);
			}

			if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				saveDataforUnmappedLocalBodies(localGovtBodyForm, localbodyCodeFULL, max_lbCoveredRegionCode, maxlb_replaces, max_lbReplacedBy, session, lgTypeName);
			}

			localbody = saveNewLocalBodyDetails(localGovtBodyForm, govtOrderForm, stateCode, max_lbCoveredRegionCode, maxlb_replaces, "D", lgTypeName, session);
			Localbody lbUpdate = localbody;

			if (!localGovtBodyForm.getlatitude().isEmpty() && localGovtBodyForm.getlatitude() != null && !localGovtBodyForm.getlongitude().isEmpty() && localGovtBodyForm.getlongitude() != null) {
				MapLocalbody mLB = saveDataInMapLB(localGovtBodyForm, lbUpdate, session);
				lbUpdate.setMap_attachment_code(mLB.getMapLocalbodyCode());

				session.update(lbUpdate);

			}

			saveDataInReplacedBy(localbody, max_lbReplacedBy, session);

			// for saving Upload Map attachment
			if (attachedMapList != null && !attachedMapList.isEmpty()) {
				govtOrderService.saveDatainMapAttachment(attachedMapList, localbody.getLocalBodyCode(), 'G', session);
			}
			// end
			GovernmentOrder govtOrder = null;
			if (govtOrderForm != null) {
				govtOrder = convertLocalbodyService.saveDataInGovtOrder(govtOrderForm, session);
				if (attachedList != null && !attachedList.isEmpty()) {
					convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm, attachedList, session);
				}

				saveDataInGovtOrderEntityWise(govtOrder, localbody.getLocalBodyTypeCode(), session);
			}

			tx.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
			return 1;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return localbody.getLocalBodyCode();
	}*/

	public List<PushLBtoPES> saveLocalBodyinPES(int localbodycode) throws Exception {
		Session session = null;
		Transaction tx = null;
		List<PushLBtoPES> localbodyPes = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			localbodyPes = localGovtBodyDAO.saveLocalBodyinPES(localbodycode);
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyPes;
	}

	public List<Pushcoveragetopes> saveCoverageinPES(int localbodycode) throws Exception {
		Session session = null;
		Transaction tx = null;
		List<Pushcoveragetopes> coveragePes = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			coveragePes = localGovtBodyDAO.saveCoverageinPES(localbodycode);
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return coveragePes;
	}

	/*private void saveDataforExistUrbanLocalBodies(LocalGovtBodyForm localGovtBodyForm, int max_lbCoveredRegionCode, int maxlb_replaces, int max_lbReplacedBy, Session session, String lgTypeName) throws Exception {

		int localbodyCode = 0;
		int localBodyVersion = 0;
		String districtPanchayatListforUrban = localGovtBodyForm.getLgd_UrbanLBDistExistDest();

		if (districtPanchayatListforUrban != null) {
			int i = 0;
			String[] temp = districtPanchayatListforUrban.split(",");
			for (i = 0; i < temp.length; i++) {
				if (temp[i].contains("FULL")) {

					localbodyCode = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					localBodyVersion = localGovtBodyDAO.getMaxRecords("select COALESCE(max(local_body_version),1) from localbody where isactive =true and local_body_code = " + localbodyCode);
					saveDataInLBReplaces(localbodyCode, localBodyVersion, maxlb_replaces, session);

					updateExistingLocalBodies(localbodyCode, localBodyVersion, max_lbReplacedBy, 'F', session);

				}
				if (temp[i].contains("PART")) {

					localbodyCode = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					localBodyVersion = localGovtBodyDAO.getMaxRecords("select COALESCE(max(local_body_version),1) from localbody where isactive =true and local_body_code = " + localbodyCode);

					saveDataInLBReplaces(localbodyCode, localBodyVersion, maxlb_replaces, session);
					updateExistingLocalBodies(localbodyCode, localBodyVersion, max_lbReplacedBy, 'P', session);

				}
			}
			saveCoveredAreaforUrban(localGovtBodyForm, max_lbCoveredRegionCode, session);
		}

	}*/

	/**
	 * @param localGovtBodyForm
	 * @param max_lbCoveredRegionCode
	 * @param session
	 * @throws NumberFormatException
	 */
	/*private void saveCoveredAreaforUrban(LocalGovtBodyForm localGovtBodyForm, int max_lbCoveredRegionCode, Session session) throws Exception {
		String subdistrictList = localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest();
		if (subdistrictList != null) {
			int subdistrictCode = 0;
			int version = 0;
			String[] temp1 = subdistrictList.split(",");
			for (int i = 0; i < temp1.length; i++) {
				if (temp1[i].contains("FULL")) {

					subdistrictCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					version = subdistrictDAO.getMaxSubdistrictVersion(subdistrictCode);
					saveDistrictInLBCoveredLandRegion(subdistrictCode, version, max_lbCoveredRegionCode, session, 'F', 'T');
				}
				if (temp1[i].contains("PART")) {
					subdistrictCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					version = subdistrictDAO.getMaxSubdistrictVersion(subdistrictCode);
					saveDistrictInLBCoveredLandRegion(subdistrictCode, version, max_lbCoveredRegionCode, session, 'P', 'T');
				}
			}
		}
	}*/

	/*private void saveDataforUnmappedLocalBodies(LocalGovtBodyForm localGovtBodyForm, int localbodyCodeFULL, int max_lbCoveredRegionCode, int maxlb_replaces, int max_lbReplacedBy, Session session, String lgTypeName) throws Exception {

		if (lgTypeName.equalsIgnoreCase("D")) {
			saveUnmappedCoveredAreaforDistrictPanchayat(localGovtBodyForm, max_lbCoveredRegionCode, session);

		} else if (lgTypeName.equalsIgnoreCase("I")) {
			saveUnmappedCoveredAreaforInterPanchayat(localGovtBodyForm, max_lbCoveredRegionCode, session);
		} else if (lgTypeName.equalsIgnoreCase("V")) {

			saveUnmappedCoveredAreaforVillagePanchayat(localGovtBodyForm, max_lbCoveredRegionCode, session);
		}
	}*/

	/*private void saveUnmappedCoveredAreaforVillagePanchayat(LocalGovtBodyForm localGovtBodyForm, int max_lbCoveredRegionCode, Session session) throws Exception {

		String villageListforVP = localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped();

		if (villageListforVP != null) {
			int villageCode = 0;
			int version = 0;
			String[] temp1 = villageListforVP.split(",");
			for (int i = 0; i < temp1.length; i++) {
				if (temp1[i].contains("FULL")) {

					villageCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);
					saveDistrictInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'F', 'V');
				}
				if (temp1[i].contains("PART")) {
					villageCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);
					saveDistrictInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'P', 'V');
				}
			}
		}
	}*/

	/*private void saveUnmappedCoveredAreaforInterPanchayat(LocalGovtBodyForm localGovtBodyForm, int max_lbCoveredRegionCode, Session session) throws Exception {

		String subDistrictListforIP = localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped();

		String villageListforIP = localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped();

		if (subDistrictListforIP != null) {
			int subdistrictCode = 0;
			int subdistrictVersion = 0;
			LbCoveredLandregion lbCoveredLandregion = null;
			String[] temp1 = subDistrictListforIP.split(",");
			for (int i = 0; i < temp1.length; i++) {
				if (temp1[i].contains("FULL")) {

					subdistrictCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(subdistrictCode);
					saveDistrictInLBCoveredLandRegion(subdistrictCode, subdistrictVersion, max_lbCoveredRegionCode, session, 'F', 'I');
				}
				if (temp1[i].contains("PART")) {

					subdistrictCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(subdistrictCode);
					lbCoveredLandregion = saveDistrictInLBCoveredLandRegion(subdistrictCode, subdistrictVersion, max_lbCoveredRegionCode, session, 'P', 'I');
				}
			}
			if (villageListforIP != null) {

				String[] temp3 = villageListforIP.split(",");
				int villageCode = 0;
				int version = 0;
				for (int i = 0; i < temp3.length; i++) {
					if (temp3[i].contains("FULL")) {

						villageCode = Integer.parseInt(temp3[i].substring(0, temp3[i].length() - 4));
						version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);
						saveLandRegionInLBCoveredLandRegionDetails(villageCode, version, lbCoveredLandregion, session, 'F', 'V');
					}
					if (temp3[i].contains("PART")) {

						villageCode = Integer.parseInt(temp3[i].substring(0, temp3[i].length() - 4));
						version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);
						saveLandRegionInLBCoveredLandRegionDetails(villageCode, version, lbCoveredLandregion, session, 'P', 'V');
					}
				}
			}

		}
	}*/

	/*private void saveUnmappedCoveredAreaforDistrictPanchayat(LocalGovtBodyForm localGovtBodyForm, int max_lbCoveredRegionCode, Session session) throws Exception {
		String districtListforDP = localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped();
		String subDistrictListforDP = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped();
		// LbCoveredLandregion lbCoveredLandregion = null;
		String villageListforDP = localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped();

		if (districtListforDP != null) {
			String[] temp1 = districtListforDP.split(",");
			int districtVersion = 0;
			for (int i = 0; i < temp1.length; i++) {
				if (temp1[i].contains("FULL")) {

					int districtCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					districtVersion = districtDAO.getMaxDistrictVersionbyCode(districtCode);
					saveDistrictInLBCoveredLandRegion(districtCode, districtVersion, max_lbCoveredRegionCode, session, 'F', 'D');
				}
				if (temp1[i].contains("PART")) {

					int districtCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					districtVersion = districtDAO.getMaxDistrictVersionbyCode(districtCode);
					saveDistrictInLBCoveredLandRegion(districtCode, districtVersion, max_lbCoveredRegionCode, session, 'P', 'D');
				}
			}
			if (subDistrictListforDP != null) {
				int subdistrictCode = 0;
				int subdistrictVersion = 0;
				String[] temp2 = subDistrictListforDP.split(",");
				for (int i = 0; i < temp2.length; i++) {
					if (temp2[i].contains("FULL")) {

						subdistrictCode = Integer.parseInt(temp2[i].substring(0, temp2[i].length() - 4));
						subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(subdistrictCode);

						saveDistrictInLBCoveredLandRegion(subdistrictCode, subdistrictVersion, max_lbCoveredRegionCode, session, 'F', 'T');

						
						 * saveLandRegionInLBCoveredLandRegionDetails(
						 * subdistrictCode, subdistrictVersion,
						 * lbCoveredLandregion, session, 'F', 'T');
						 
					}
					if (temp2[i].contains("PART")) {

						subdistrictCode = Integer.parseInt(temp2[i].substring(0, temp2[i].length() - 4));
						subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(subdistrictCode);
						saveDistrictInLBCoveredLandRegion(subdistrictCode, subdistrictVersion, max_lbCoveredRegionCode, session, 'P', 'T');

						
						 * saveLandRegionInLBCoveredLandRegionDetails(
						 * subdistrictCode, subdistrictVersion,
						 * lbCoveredLandregion, session, 'P', 'T');
						 
					}
				}
			}
			if (villageListforDP != null) {
				String[] temp3 = villageListforDP.split(",");
				int villageCode = 0;
				int version = 0;
				for (int i = 0; i < temp3.length; i++) {
					if (temp3[i].contains("FULL")) {

						villageCode = Integer.parseInt(temp3[i].substring(0, temp3[i].length() - 4));
						version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);
						saveDistrictInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'F', 'V');

						
						 * saveLandRegionInLBCoveredLandRegionDetails(villageCode
						 * , version, lbCoveredLandregion, session, 'F', 'V');
						 

					}
					if (temp3[i].contains("PART")) {

						villageCode = Integer.parseInt(temp3[i].substring(0, temp3[i].length() - 4));
						version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);

						saveDistrictInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'P', 'V');

						
						 * saveLandRegionInLBCoveredLandRegionDetails(villageCode
						 * , version, lbCoveredLandregion, session, 'P', 'V');
						 
					}
				}
			}
		}
	}*/

	/*
	 * private void saveDataforExistLocalBodies(LocalGovtBodyForm
	 * localGovtBodyForm, int localbodyCodeFULL,int max_lbCoveredRegionCode, int
	 * maxlb_replaces,int max_lbReplacedBy, Session session, String lgTypeName)
	 * throws Exception { LbCoveredLandregion lbCoveredLandregion = null; String
	 * districtPanchayatListforDP = localGovtBodyForm.getLgd_LBDistPDestList();
	 * 
	 * String intermediatePanchayatLIst =
	 * localGovtBodyForm.getLgd_LBInterPDestList();
	 * 
	 * String villagePanchayatList =
	 * localGovtBodyForm.getLgd_LBVillageDestAtVillageCA();
	 * 
	 * if (lgTypeName.equalsIgnoreCase("D")) { int i = 0;
	 * 
	 * if (districtPanchayatListforDP != null) { String[] temp =
	 * districtPanchayatListforDP.split(","); for (i = 0; i < temp.length; i++)
	 * { if (temp[i].contains("FULL")) { localbodyCodeFULL =
	 * Integer.parseInt(temp[i].substring(0, temp[i].length() - 4)); int
	 * localBodyVersion = localGovtBodyDAO.getMaxRecords(
	 * "select COALESCE(max(local_body_version),1) from localbody where isactive =true and local_body_code = "
	 * + localbodyCodeFULL);
	 * saveDataInLBReplaces(localbodyCodeFULL,localBodyVersion, maxlb_replaces,
	 * session); updateExistingLocalBodies(localbodyCodeFULL,localBodyVersion,
	 * max_lbReplacedBy, 'F',session);
	 * 
	 * 
	 * 
	 * savePanchayatsInLBCoveredLandRegion(localbodyCodeFULL, localBodyVersion,
	 * max_lbCoveredRegionCode, session, 'F', 'D'); // Commented By Me
	 * 
	 * } else if (temp[i].contains("PART")) { localbodyCodeFULL =
	 * Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
	 * 
	 * int localBodyVersion = localGovtBodyDAO.getMaxRecords(
	 * "select COALESCE(max(local_body_version),1) from localbody where isactive =true and local_body_code = "
	 * + localbodyCodeFULL);
	 * saveDataInLBReplaces(localbodyCodeFULL,localBodyVersion, maxlb_replaces,
	 * session); updateExistingLocalBodies(localbodyCodeFULL,localBodyVersion,
	 * max_lbReplacedBy, 'P',session);
	 * 
	 * 
	 * savePanchayatsInLBCoveredLandRegion(localbodyCodeFULL, localBodyVersion,
	 * max_lbCoveredRegionCode, session, 'P', 'D'); // Commented By Me } }
	 * saveCoveredAreaforDistrictPanchayat
	 * (localGovtBodyForm,max_lbCoveredRegionCode, session); } } else if
	 * (lgTypeName.equalsIgnoreCase("I")) { if (intermediatePanchayatLIst !=
	 * null) { String[] temp = intermediatePanchayatLIst.split(","); for (int j
	 * = 0; j < temp.length; j++) { if (temp[j].contains("FULL")) { int
	 * lbCodeAtInter = Integer.parseInt(temp[j].substring(0, temp[j].length() -
	 * 4)); int localBodyVersion = localGovtBodyDAO.getMaxRecords(
	 * "select COALESCE(max(local_body_version),1) from localbody where isactive =true and local_body_code = "
	 * + lbCodeAtInter); saveDataInLBReplaces(lbCodeAtInter,
	 * localBodyVersion,maxlb_replaces, session);
	 * updateExistingLocalBodies(lbCodeAtInter,localBodyVersion,
	 * max_lbReplacedBy, 'F',session);
	 * 
	 * } else if (temp[j].contains("PART")) { int lbCodeAtInter =
	 * Integer.parseInt(temp[j].substring(0, temp[j].length() - 4)); int
	 * localBodyVersion = localGovtBodyDAO.getMaxRecords(
	 * "select COALESCE(max(local_body_version),1) from localbody where isactive =true and local_body_code = "
	 * + lbCodeAtInter); saveDataInLBReplaces(lbCodeAtInter,
	 * localBodyVersion,maxlb_replaces, session);
	 * updateExistingLocalBodies(lbCodeAtInter,localBodyVersion,
	 * max_lbReplacedBy, 'P',session); } }
	 * saveCoveredAreaforInterPanchayat(localGovtBodyForm,
	 * max_lbCoveredRegionCode, session); } } else if
	 * (lgTypeName.equalsIgnoreCase("V")) { if (villagePanchayatList != null) {
	 * String[] temp = villagePanchayatList.split(","); for (int j = 0; j <
	 * temp.length; j++) { if (temp[j].contains("FULL")) { int lbCodeAtVillage =
	 * Integer.parseInt(temp[j].substring(0, temp[j].length() - 4)); int
	 * localBodyVersion = localGovtBodyDAO.getMaxRecords(
	 * "select COALESCE(max(local_body_version),1) from localbody where isactive =true and local_body_code = "
	 * + lbCodeAtVillage); saveDataInLBReplaces(lbCodeAtVillage,
	 * localBodyVersion,maxlb_replaces, session);
	 * updateExistingLocalBodies(lbCodeAtVillage,localBodyVersion,
	 * max_lbReplacedBy, 'F',session);
	 * 
	 * } else if (temp[j].contains("PART")) { int lbCodeAtVillage =
	 * Integer.parseInt(temp[j].substring(0, temp[j].length() - 4)); int
	 * localBodyVersion = localGovtBodyDAO.getMaxRecords(
	 * "select COALESCE(max(local_body_version),1) from localbody where isactive =true and local_body_code = "
	 * + lbCodeAtVillage); saveDataInLBReplaces(lbCodeAtVillage,
	 * localBodyVersion,maxlb_replaces, session);
	 * updateExistingLocalBodies(lbCodeAtVillage,localBodyVersion,
	 * max_lbReplacedBy, 'P',session); } }
	 * saveCoveredAreaforVillagePanchayat(localGovtBodyForm
	 * ,max_lbCoveredRegionCode, session); } } }
	 */

	private List<InsertLocalGovtBody> saveDataforExistLocalBodies(LocalGovtBodyForm localGovtBodyForm, int localbodyCodeFULL, Session session, String lgTypeName, String lgtypecode, int stateCode, Integer userId) throws Exception {
		String districtPanchayatListforDP = localGovtBodyForm.getLgd_LBDistPDestList();
		String intermediatePanchayatLIst = localGovtBodyForm.getLgd_LBInterPDestList();
		String villagePanchayatList = localGovtBodyForm.getLgd_LBVillageDestAtVillageCA();
		String panchayatDP = null;
		List<InsertLocalGovtBody> localgovtbody = null;

		if (lgTypeName.equalsIgnoreCase("D")) {
			if (districtPanchayatListforDP != null) {
				panchayatDP = localGovtBodyForm.getLgd_LBDistPDestList();
			}
		} else if (lgTypeName.equalsIgnoreCase("I")) {
			if (intermediatePanchayatLIst != null) {
				panchayatDP = localGovtBodyForm.getLgd_LBInterPDestList();
			}
		} else if (lgTypeName.equalsIgnoreCase("V")) {
			if (villagePanchayatList != null) {
				panchayatDP = localGovtBodyForm.getLgd_LBVillageDestAtVillageCA();
			}
		}
		localgovtbody = localGovtBodyDAO.saveLocalBody(localGovtBodyForm, lgTypeName, lgtypecode, stateCode, userId, panchayatDP);

		return localgovtbody;
	}

	private List<InsertLocalGovtBody> saveDataforExistLocalBodiesULB(LocalGovtBodyForm localGovtBodyForm, int localbodyCodeFULL, Session session, String lgtypecode, int stateCode, Integer userId) throws Exception {
		//String districtPanchayatListforDP = localGovtBodyForm.getLgd_LBDistPDestList();
		//String intermediatePanchayatLIst = localGovtBodyForm.getLgd_LBInterPDestList();
		//String villagePanchayatList = localGovtBodyForm.getLgd_LBVillageDestAtVillageCA();
		String panchayatDP = null;
		List<InsertLocalGovtBody> localgovtbody = null;
		if (localGovtBodyForm.getLgd_UrbanLBDistExistDest() != null) {
			panchayatDP = localGovtBodyForm.getLgd_UrbanLBDistExistDest();
		}

		/*
		 * if (lgTypeName.equalsIgnoreCase("D")) { if
		 * (districtPanchayatListforDP != null) {
		 * panchayatDP=localGovtBodyForm.getLgd_LBDistPDestList(); } } else if
		 * (lgTypeName.equalsIgnoreCase("I")) { if (intermediatePanchayatLIst !=
		 * null) { panchayatDP=localGovtBodyForm.getLgd_LBInterPDestList(); } }
		 * else if (lgTypeName.equalsIgnoreCase("V")) { if (villagePanchayatList
		 * != null) {
		 * panchayatDP=localGovtBodyForm.getLgd_LBVillageDestAtVillageCA(); } }
		 */
		localgovtbody = localGovtBodyDAO.saveLocalBodyULB(localGovtBodyForm, lgtypecode, stateCode, userId, panchayatDP);

		return localgovtbody;
	}

	/*private LbCoveredLandregion saveDistInLBCoveredLandRegion(int code, int version, int max_lbCoveredRegionCode, Session session, char coverageType, char lg_Type) {

		LbCoveredLandregion lbCoveredLandregion = null;
		try {

			lbCoveredLandregion = new LbCoveredLandregion();

			lbCoveredLandregion.setLbCoveredRegionCode(max_lbCoveredRegionCode);
			lbCoveredLandregion.setCoverageType(coverageType);
			lbCoveredLandregion.setIsmainregion(true);
			lbCoveredLandregion.setLandRegionType(lg_Type);
			lbCoveredLandregion.setLandRegionVersion(version);
			lbCoveredLandregion.setLandRegionCode(code);
			lbCoveredLandregion.setIsactive(true);
			localGovtBodyDAO.saveLbLandRegionCoveredDetails(lbCoveredLandregion, session);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
		return lbCoveredLandregion;
	}*/

	/*private void saveLandRegionInLBCoveredLandRegionDetails(int landRegionCode, int version, LbCoveredLandregion lbCoveredLandregion, Session session, char coverageType, char lgdType) {
		LbCoveredLandregionPartDetails lbCLRPart = new LbCoveredLandregionPartDetails();
		lbCLRPart.setCoverageType(coverageType);
		lbCLRPart.setIsactive(true);
		lbCLRPart.setLandRegionCode(landRegionCode);
		lbCLRPart.setLandRegionType(lgdType);
		lbCLRPart.setLandRegionVersion(version);
		// lbCLRPart.setLbCoveredRegionCode(max_lbCoveredLanregionCode);
		lbCLRPart.setLbCoveredLandregion(lbCoveredLandregion);

		session.save(lbCLRPart);
		session.flush();
		if (session.contains(lbCLRPart)) {
			session.evict(lbCLRPart);
		}

	}*/

	/*private void saveDataInReplacedBy(Localbody localbody, int max_lbReplacedBy, Session session) {

		LocalbodyReplacedby lBReplacedby = new LocalbodyReplacedby();
		lBReplacedby.setLbReplacedby(max_lbReplacedBy);
		lBReplacedby.setReplacedby(localbody.getLocalBodyCode());
		lBReplacedby.setReplacedbyVersion(1);
		session.save(lBReplacedby);

	}*/

	
	public MapLocalbody saveDataInMapLB(LocalGovtBodyForm localGovtBodyForm, Localbody lb, Session session) {

		MapLocalbody mLb = null;
		try {
			mLb = new MapLocalbody();

			mLb.setCoordinates("lat=" + localGovtBodyForm.getlatitude() + "," + "long=" + localGovtBodyForm.getlongitude());
			mLb.setCreatedby(createdBy);
			mLb.setCreatedon(new Date());
			mLb.setEffectiveDate(new Date());
			mLb.setImagePath(localGovtBodyForm.getfileMapUpLoad());
			mLb.setLastupdated(new Date());
			mLb.setLastupdatedby((long) createdBy);
			mLb.setLocalbody(lb);
			mLb.setWarningflag(false);
			session.save(mLb);
			session.flush();

		} catch (Exception e) {

			log.debug("Exception" + e);
		}
		return mLb;

	}

	/*private Localbody saveNewLocalBodyDetails(LocalGovtBodyForm localGovtBodyForm, GovernmentOrderForm govtform, int stateCode, int max_lbCoveredRegionCode, int maxlb_replaces, String lgTypeName, String lgTypeCode, Session session) {
		Localbody newLocalBody = new Localbody();

		try {
			int max_localBodyCode = localGovtBodyDAO.getMaxRecords("select COALESCE(max(local_body_code),1) from localbody where isactive=true");
			State getstateObj = getDataFromState(stateCode);
			if (max_localBodyCode == 0) {
				max_localBodyCode = 1;
			} else {
				max_localBodyCode++;
			}

			newLocalBody.setLocalBodyNameEnglish(localGovtBodyForm.getLgd_LBNameInEn().trim());
			if (localGovtBodyForm.getLgd_LBAliasInEn() == null) {
				newLocalBody.setAliasEnglish("");
			} else {
				newLocalBody.setAliasEnglish(localGovtBodyForm.getLgd_LBAliasInEn().trim());
			}

			if (localGovtBodyForm.getLgd_LBNameInLocal() == null) {
				newLocalBody.setLocalBodyNameLocal("");
			} else {
				newLocalBody.setLocalBodyNameLocal(localGovtBodyForm.getLgd_LBNameInLocal().trim());
			}

			if (localGovtBodyForm.getLgd_LBAliasInLocal() == null) {
				newLocalBody.setAliasLocal("");
			} else {
				newLocalBody.setAliasLocal(localGovtBodyForm.getLgd_LBAliasInLocal().trim());
			}

			LocalbodyPK newlbpk = new LocalbodyPK(max_localBodyCode, 1);
			// newLocalBody.setId(newlbpk);

			newLocalBody.setCreatedby(createdBy);
			newLocalBody.setCreatedon(new Date());
			newLocalBody.setEffectiveDate(govtform.getEffectiveDate());
			newLocalBody.setLastupdated(new Date());
			newLocalBody.setIsactive(true);
			newLocalBody.setIsdisturbed(false);
			newLocalBody.setFlagCode(63);
			newLocalBody.setLastupdatedby(null);
			newLocalBody.setLocalBodySubtype(null);// Dnt Know What will Sub
													// Type Code
			if (lgTypeName.equalsIgnoreCase("D")) {
				newLocalBody.setParentlblc(null);
				// newLocalBody.setParentLocalBodyVersion(null);
			} else if (lgTypeName.equalsIgnoreCase("I")) {
				int pLbCode = Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtInter());

				int version = localGovtBodyDAO.getCurrentLocalbodyVersion(pLbCode);
				newLocalBody.setParentlblc(pLbCode);
				// newLocalBody.setParentLocalBodyVersion(version);
			} else if (lgTypeName.equalsIgnoreCase("V")) {

				int pLbCode = Integer.parseInt(localGovtBodyForm.getLgd_LBIntermediateAtVillage());

				int version = localGovtBodyDAO.getCurrentLocalbodyVersion(pLbCode);
				newLocalBody.setParentlblc(pLbCode);
				// newLocalBody.setParentLocalBodyVersion(version);

			}
			newLocalBody.setState(getstateObj);
			newLocalBody.setMap_attachment_code(null);// Get Data From
														// Map_LocalBody
			newLocalBody.setLbReplaces(maxlb_replaces);
			newLocalBody.setLbReplacedby(null); // coveredAreaLocalBodyList.get(j).getLbReplacedby());
			newLocalBody.setLbCoveredRegionCode(max_lbCoveredRegionCode); // No
																			// Increment
																			// Needed
																			// for
																			// max_lbCoveredRegionCode

			LocalBodyType localBodyType = (LocalBodyType) session.load(LocalBodyType.class, Integer.parseInt(lgTypeCode));
			newLocalBody.setLocalBodyType(localBodyType);

			session.save(newLocalBody);
			session.flush();
			if (session.contains(newLocalBody)) {
				session.evict(newLocalBody);
			}
		} catch (Exception e) {

			log.debug("Exception" + e);
		}

		return newLocalBody;

	}*/

	public State getDataFromState(int stateCode) {
		Session sessionState = null;
		Query queryState = null;
		State stateObj = null;

		try {
			sessionState = sessionFactory.openSession();
			queryState = sessionState.createQuery("from State Where isactive=true and stateCode=" + stateCode);
			@SuppressWarnings("unchecked")
			List<State> stateList = queryState.list();
			stateObj = (State) stateList.get(0);
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (sessionState != null && sessionState.isOpen()) {
				sessionState.close();
			}
		}
		return stateObj;
	}

	public boolean saveDataInGovtOrderEntityWise(GovernmentOrder govtOrder, int localbodyCode, Session session) {

		GovernmentOrderEntityWise govtOrderEWise = new GovernmentOrderEntityWise();
		try {

			govtOrderEWise.setGovernmentOrder(govtOrder);
			govtOrderEWise.setEntityCode(localbodyCode);// Change Here in New
														// Code Bcz We changed
														// The name
			govtOrderEWise.setEntityVersion(1);
			govtOrderEWise.setEntityType('G');

			localGovtBodyDAO.saveOrderDetailsEntityWise(govtOrderEWise, session);
		} catch (Exception e) {

		}
		return true;
	}

	/*private void updateExistingLocalBodies(int localbodyCodeFULL, int localBodyVersion, int max_lbReplacedBy, char type, Session session) {
		if (localbodyCodeFULL > 0) {
			Localbody lbC = getDatafromLB(localbodyCodeFULL, localBodyVersion);
			lbC.setIsactive(false);
			session.update(lbC);
			session.flush();

			Localbody insertLb = lbC;

			if (session.contains(lbC)) {
				session.evict(lbC);
			}

			LocalbodyPK lBPK = new LocalbodyPK();
			lBPK.setLocalBodyCode(localbodyCodeFULL);
			lBPK.setLocalBodyVersion(++localBodyVersion);
			// insertLb.setId(lBPK);

			if (type == 'P') {
				insertLb.setIsdisturbed(true);
				insertLb.setIsactive(true);
			} else {
				insertLb.setIsdisturbed(false);
				insertLb.setIsactive(false);
			}
			insertLb.setFlagCode(67);

			insertLb.setLbReplacedby(max_lbReplacedBy);
			session.save(insertLb);
			session.flush();
			if (session.contains(insertLb)) {
				session.evict(insertLb);
			}
		}

	}*/

	public Localbody getDatafromLB(int localbodyCodeFULL, int localBodyVersion) {
		Session sessionLB = null;
		Query query = null;
		Localbody lbC = null;
		try {
			sessionLB = sessionFactory.openSession();
			query = sessionLB.createQuery("from Localbody lb Where isactive=true and lb.id.localBodyCode=" + localbodyCodeFULL + " and lb.id.localBodyVersion=" + localBodyVersion);
			@SuppressWarnings("unchecked")
			List<Localbody> localbodyList = query.list();
			if (!localbodyList.isEmpty() && localbodyList.get(0) != null) {
				lbC = (Localbody) localbodyList.get(0);
			}
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (sessionLB != null && sessionLB.isOpen()) {
				sessionLB.close();
			}
		}
		return lbC;
	}

	public GovernmentOrder saveDataInGovtOrder(LocalGovtBodyForm localGovtBodyForm, Session session, HttpServletRequest request) {
		Long updatedBy = (long) 4000;

		GovernmentOrder governmentOrder = new GovernmentOrder();
		try {
			governmentOrder.setOrderDate(localGovtBodyForm.getLgd_LBorderDate());
			governmentOrder.setEffectiveDate(localGovtBodyForm.getLgd_LBeffectiveDate());
			governmentOrder.setGazPubDate(localGovtBodyForm.getLgd_LBgazPubDate());
			governmentOrder.setCreatedon(new Date());
			governmentOrder.setDescription("LGD DETAILS-V");
			governmentOrder.setIssuedBy("GOVERNOR");
			governmentOrder.setCreatedby(4000);
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby(updatedBy);
			governmentOrder.setLevel("OK");
			governmentOrder.setOrderNo(localGovtBodyForm.getLgd_LBorderNo());
			governmentOrder.setStatus('A');
			governmentOrder.setUserId(4000);
			/*
			 * MultipartFile filename = null; filename =
			 * localGovtBodyForm.getLgd_LBfilePath(); writeMap(filename,
			 * request); governmentOrder.setOrderPath(request.getRealPath("/") +
			 * filename.getOriginalFilename());
			 */
			governmentOrder.setXmlDbPath("Test");
			governmentOrder.setXmlOrderPath("");

			localGovtBodyDAO.saveOrderDetails(governmentOrder, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return governmentOrder;
	}

	public GovernmentOrder updateDataInGovtOrder(LocalGovtBodyForm localGovtBodyForm, Session session, HttpServletRequest request) {
		Long updatedBy = (long) 4000;

		GovernmentOrder governmentOrder = new GovernmentOrder();
		try {
			governmentOrder = (GovernmentOrder) session.get(GovernmentOrder.class, localGovtBodyForm.getOrderCode());
			governmentOrder.setOrderDate(localGovtBodyForm.getLgd_LBorderDate());
			governmentOrder.setEffectiveDate(localGovtBodyForm.getLgd_LBeffectiveDate());
			governmentOrder.setGazPubDate(localGovtBodyForm.getLgd_LBgazPubDate());
			governmentOrder.setCreatedon(governmentOrder.getCreatedon());
			governmentOrder.setDescription(governmentOrder.getDescription());
			governmentOrder.setIssuedBy(governmentOrder.getIssuedBy());
			governmentOrder.setCreatedby(governmentOrder.getCreatedby());
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby(updatedBy);
			governmentOrder.setLevel(governmentOrder.getLevel());
			governmentOrder.setOrderNo(localGovtBodyForm.getLgd_LBorderNo());
			governmentOrder.setStatus(governmentOrder.getStatus());
			governmentOrder.setUserId(4000);
			/*
			 * MultipartFile filename = null; filename =
			 * localGovtBodyForm.getLgd_LBfilePath(); writeMap(filename,
			 * request); governmentOrder.setOrderPath(request.getRealPath("/") +
			 * filename.getOriginalFilename());
			 */
			governmentOrder.setXmlDbPath("Test");
			governmentOrder.setXmlOrderPath("");

			localGovtBodyDAO.updateOrderDetails(governmentOrder, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return governmentOrder;
	}

	/*private void saveCoveredAreaforVillagePanchayat(LocalGovtBodyForm localGovtBodyForm, int max_lbCoveredRegionCode, Session session) throws Exception {

		String villageListforVP = localGovtBodyForm.getLgd_LBVillageCAreaDestL();

		if (villageListforVP != null) {
			int villageCode = 0;
			int version = 0;
			String[] temp1 = villageListforVP.split(",");
			for (int i = 0; i < temp1.length; i++) {
				if (temp1[i].contains("FULL")) {

					villageCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);
					saveDistrictInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'F', 'V');
				}
				if (temp1[i].contains("PART")) {
					villageCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);
					saveDistrictInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'P', 'V');
				}
			}
		}
	}*/

	/*private void saveCoveredAreaforInterPanchayat(LocalGovtBodyForm localGovtBodyForm, int max_lbCoveredRegionCode, Session session) throws Exception {

		String subDistrictListforIP = localGovtBodyForm.getLgd_LBInterCAreaDestList();

		String villageListforIP = localGovtBodyForm.getLgd_LBVillageDestLatICA();

		if (subDistrictListforIP != null) {
			int subdistrictCode = 0;
			int subdistrictVersion = 0;
			// LbCoveredLandregion lbCoveredLandregion = null;
			String[] temp1 = subDistrictListforIP.split(",");
			for (int i = 0; i < temp1.length; i++) {
				if (temp1[i].contains("FULL")) {

					subdistrictCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(subdistrictCode);
					saveDistrictInLBCoveredLandRegion(subdistrictCode, subdistrictVersion, max_lbCoveredRegionCode, session, 'F', 'T');
				}
				if (temp1[i].contains("PART")) {

					subdistrictCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(subdistrictCode);
					saveDistrictInLBCoveredLandRegion(subdistrictCode, subdistrictVersion, max_lbCoveredRegionCode, session, 'P', 'T');
				}
			}
			if (villageListforIP != null) {

				String[] temp3 = villageListforIP.split(",");
				int villageCode = 0;
				int version = 0;
				for (int i = 0; i < temp3.length; i++) {
					if (temp3[i].contains("FULL")) {

						villageCode = Integer.parseInt(temp3[i].substring(0, temp3[i].length() - 4));
						version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);

						saveDistrictInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'F', 'V');

						
						 * saveLandRegionInLBCoveredLandRegionDetails(villageCode
						 * , version, lbCoveredLandregion, session, 'F', 'V');
						 
					}
					if (temp3[i].contains("PART")) {

						villageCode = Integer.parseInt(temp3[i].substring(0, temp3[i].length() - 4));
						version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);

						saveDistrictInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'P', 'V');

						
						 * saveLandRegionInLBCoveredLandRegionDetails(villageCode
						 * , version, lbCoveredLandregion, session, 'P', 'V');
						 
					}
				}
			}

		}
	}*/

	/*private void saveCoveredAreaforDistrictPanchayat(LocalGovtBodyForm localGovtBodyForm, int max_lbCoveredRegionCode, Session session) throws Exception {
		String districtListforDP = localGovtBodyForm.getLgd_LBDistCAreaDestList();
		String subDistrictListforDP = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA();
		LbCoveredLandregion lbCoveredLandregion = null;
		String villageListforDP = localGovtBodyForm.getLgd_LBVillageDestLatDCA();

		if (districtListforDP != null) {
			String[] temp1 = districtListforDP.split(",");
			int districtVersion = 0;
			for (int i = 0; i < temp1.length; i++) {
				if (temp1[i].contains("FULL")) {
					int districtCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));

					districtVersion = districtDAO.getMaxDistrictVersionbyCode(districtCode);

					saveDistInLBCoveredLandRegion(districtCode, districtVersion, max_lbCoveredRegionCode, session, 'F', 'D');
					
					 * saveLandRegionInLBCoveredLandRegionDetails(districtCode,
					 * districtVersion, max_lbCoveredRegionCode, session, 'F',
					 * 'D');
					 // D.P. Dist Will save In LBCOveredLandRegion
				}
				if (temp1[i].contains("PART")) {
					int districtCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					districtVersion = districtDAO.getMaxDistrictVersionbyCode(districtCode);

					lbCoveredLandregion = saveDistInLBCoveredLandRegion(districtCode, districtVersion, max_lbCoveredRegionCode, session, 'P', 'D');
				}
			}
			if (subDistrictListforDP != null) {
				int subdistrictCode = 0;
				int subdistrictVersion = 0;
				String[] temp2 = subDistrictListforDP.split(",");
				for (int i = 0; i < temp2.length; i++) {
					if (temp2[i].contains("FULL")) {
						subdistrictCode = Integer.parseInt(temp2[i].substring(0, temp2[i].length() - 4));
						subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(subdistrictCode);
						saveDistInLBCoveredLandRegion(subdistrictCode, subdistrictVersion, max_lbCoveredRegionCode, session, 'F', 'T');
					}
					if (temp2[i].contains("PART")) {
						subdistrictCode = Integer.parseInt(temp2[i].substring(0, temp2[i].length() - 4));
						subdistrictVersion = subdistrictDAO.getMaxSubDistrictVersion(subdistrictCode);
						saveDistInLBCoveredLandRegion(subdistrictCode, subdistrictVersion, max_lbCoveredRegionCode, session, 'P', 'T');
					}
				}
			}
			if (villageListforDP != null) {
				String[] temp3 = villageListforDP.split(",");
				int villageCode = 0;
				int version = 0;
				for (int i = 0; i < temp3.length; i++) {
					if (temp3[i].contains("FULL")) {
						villageCode = Integer.parseInt(temp3[i].substring(0, temp3[i].length() - 4));
						version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);
						saveDistInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'F', 'V');
					}
					if (temp3[i].contains("PART")) {
						villageCode = Integer.parseInt(temp3[i].substring(0, temp3[i].length() - 4));
						version = villageDAO.getMaxVillageVersionbyVillageCode(villageCode);
						saveDistInLBCoveredLandRegion(villageCode, version, max_lbCoveredRegionCode, session, 'P', 'V');
					}
				}
			}
		}
	}*/

	/*private LbCoveredLandregion saveDistrictInLBCoveredLandRegion(int code, int version, int max_lbCoveredRegionCode, Session session, char coverageType, char lg_Type) {

		LbCoveredLandregion lbCoveredLandregion = null;
		try {

			lbCoveredLandregion = new LbCoveredLandregion();

			lbCoveredLandregion.setLbCoveredRegionCode(max_lbCoveredRegionCode);
			lbCoveredLandregion.setCoverageType(coverageType);
			lbCoveredLandregion.setIsmainregion(true);
			lbCoveredLandregion.setLandRegionType(lg_Type);
			lbCoveredLandregion.setLandRegionVersion(version);
			lbCoveredLandregion.setLandRegionCode(code);
			lbCoveredLandregion.setIsactive(true);
			localGovtBodyDAO.saveLbLandRegionCoveredDetails(lbCoveredLandregion, session);

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return lbCoveredLandregion;
	}*/

	public boolean saveDataInLBReplaces(int localbodyCode, int localBodyVersion, int maxlb_replaces, Session session) {

		try {

			LocalbodyReplaces localbodyReplaces = new LocalbodyReplaces();
			localbodyReplaces.setReplaces(localbodyCode);
			localbodyReplaces.setLbReplaces(maxlb_replaces);
			localbodyReplaces.setReplacesVersion(localBodyVersion);

			localGovtBodyDAO.saveLandRegionReplacesDetails(localbodyReplaces, session);
			return true;

		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return false;
	}

	public List<District> getDistrictList(int stateCode) throws Exception {
		return localGovtBodyDAO.getDistrictListbyStateCode(stateCode);

	}

	public List<LocalBodyType> getLGTypeForGovtBody() throws Exception {
		return localGovtBodyDAO.getLGTypeForGovtBody();

	}

	// create by chandan for everybody's need
	@Override
	public List<Localbody> getULBList(int districtCode) throws Exception {
		return localGovtBodyDAO.getULBListINDistrict(districtCode);
	}

	/*@Override
	public List<Localbody> getULBListByDistrict(int districtCode) throws Exception {
		return localGovtBodyDAO.getULBListByDistrict(districtCode);
	}*/

	// Get Localbody code and name getLocalBodyCodeAndName

	/*@Override
	public List<Localbody> getLocalBodyViewList(String query) throws Exception {
		return localGovtBodyDAO.getLocalbodyViewList(query);
	}
*/
	// /End here

	/*@Override
	public List<Subdistrict> getUnmappedSubDistLocalbodyViewList(String query) throws Exception {
		return localGovtBodyDAO.getUnmappedSubDistLocalbodyViewList(query);
	}*/

	public List<Subdistrict> getDeleteSubDistMapped(String distlist, String subdistlist) throws Exception {

		List<Subdistrict> mappedList = new ArrayList<Subdistrict>();
		List<Subdistrict> mappedListFull = new ArrayList<Subdistrict>();
		//String listOfIds = null;
		String listOfIddistLists = null;
		String listofIdsubDistLists = null;

		try {
			listOfIddistLists = distlist.replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
			listofIdsubDistLists = subdistlist.replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
			String selectddistId[] = listOfIddistLists.split(",");
			String selectdSubDistListId[] = listofIdsubDistLists.split(",");
			for (String stringObjDist : selectddistId) {
				for (String stringObjSubDist : selectdSubDistListId) {
					mappedListFull = localGovtBodyDAO.getDeleteSubDistMapped(Integer.parseInt(stringObjDist), Integer.parseInt(stringObjSubDist));
					mappedList.addAll(mappedListFull);
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return mappedList;

	}

	public List<Village> getDeleteVillageMappedDP(String subdistlist, String villageList) throws Exception {

		List<Village> mappedList = new ArrayList<Village>();
		List<Village> mappedListFull = new ArrayList<Village>();
		//String listOfIds = null;
		String listOfSubDistLists = null;
		String listOfVillageLists = null;
		try {
			listOfSubDistLists = subdistlist.replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
			listOfVillageLists = villageList.replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");

			String selectSubDistListId[] = listOfSubDistLists.split(",");
			String selectdVillageistId[] = listOfVillageLists.split(",");
			for (String stringObjSubDist : selectSubDistListId) {
				for (String stringObjVillage : selectdVillageistId) {
					mappedListFull = localGovtBodyDAO.getDeleteVillageMappedDP(Integer.parseInt(stringObjSubDist), Integer.parseInt(stringObjVillage));
					mappedList.addAll(mappedListFull);
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return mappedList;

	}

	/*@Override
	public List<Village> getDeleteVillageMapped(String subdistlist) throws Exception {

		List<Village> mappedList = new ArrayList<Village>();
		List<Village> mappedListFull = new ArrayList<Village>();
		String listOfIds = null;
		String listOfVillageLists = null;
		try {
			listOfVillageLists = subdistlist.replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");

			String selectVillageId[] = listOfVillageLists.split(",");
			for (String stringObjVillage : selectVillageId) {
				mappedListFull = localGovtBodyDAO.getDeleteVillageMapped(Integer.parseInt(stringObjVillage));
				mappedList.addAll(mappedListFull);
			}

		} catch (Exception e) {
			throw e;
		}
		return mappedList;

	}
*/
	public List<Village> getDeleteVillageMappedforSubDist(String subdistlist, String villagelist) throws Exception {

		List<Village> mappedList = new ArrayList<Village>();
		List<Village> mappedListFull = new ArrayList<Village>();
		//String listOfIds = null;
		String listOfVillageLists = null;
		String listOfSubDists = null;
		try {
			listOfVillageLists = villagelist.replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
			listOfSubDists = subdistlist.replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
			String selectVillageId[] = listOfVillageLists.split(",");
			String selectdSubDistListId[] = listOfSubDists.split(",");

			for (String stringObjSubDist : selectdSubDistListId) {
				for (String stringObjVillage : selectVillageId) {
					mappedListFull = localGovtBodyDAO.getDeleteVillageMappedforSubDist(Integer.parseInt(stringObjSubDist), Integer.parseInt(stringObjVillage));
					mappedList.addAll(mappedListFull);
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return mappedList;

	}

	/*@Override
	public List<Village> getUnmappedVillageLocalbodyViewList(String query) throws Exception {
		return localGovtBodyDAO.getUnmappedVillageLocalbodyViewList(query);
	}*/

	public List<ParentWiseLocalBodies> getLocalGovtBodySubDistList(int localBodyCode) throws Exception {
		// int localBodyCode=1;
		int stateCode = 6;
		return localGovtBodyDAO.getLocalGovtBodySubDistList(localBodyCode, stateCode);
	}

	public static String stringRemovePart(String value) {
		return value.replaceAll("PART", "");
	}

	public static String stringRemoveFull(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("PART", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}

	public static String stringRemovePartSemFinal(String value) {
		return value.replaceAll("_PART", "");
	}

	public static String stringRemoveFullSemFinal(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("_PART", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}

	public static String stringRemovePartWard(String value) {
		return value.replaceAll("_PART_T", "");
	}

	public static String stringRemoveFullWard(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("_PART_T")) {
				valueBuffer.append(temp[i].replace("_PART_T", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}

	public static String stringRemovePartSforname(String value) {
		return value.replaceAll("_PART", "");
	}

	public static String stringRemovePartS(String value) {
		return value.replaceAll("_PART", "").replaceAll("PART", "");// modified
																	// @author
																	// Vinay
																	// Yadav
																	// 23-12-2013
	}

	public static String stringRemoveFullSforname(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				valueBuffer.append(temp[i].replace("_FULL", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			} else if (temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("_PART", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}
		String txt = null;
		if (valueBuffer != null && valueBuffer.length() > 0) {
			txt = valueBuffer.substring(0, valueBuffer.length() - 1);
		}
		return txt;
	}

	private String concateLocalBody(String value) {
		StringBuffer valueBuffer = new StringBuffer(value);
		valueBuffer.append(",");
		return valueBuffer.toString();
	}

	public static String stringRemoveFullS(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("_PART", "").replace("PART", ""));// modified
																						// @author
																						// vinay
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}

	public static String stringRemovePartFinal(String value) {
		return value.replaceAll("_PART", "");
	}

	public static String stringRemoveFullFinal(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("_PART", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}

	public static String stringRemovePartCovChngFinal(String value) {
		return value.replaceAll("PART", "");
	}

	public static String stringRemoveFullCovChngFinal(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("PART", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}

	public static String stringRemoveFullFinalTo(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				valueBuffer.append(temp[i].replace("_FULL", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}

	public static String RemoveFullPart(String value) {
		if (value != null) {
			if (value.contains(",")) {
				String[] temp = value.split(",");
				StringBuffer valueBuffer = new StringBuffer();
				for (int i = 0; i < temp.length; i++) {
					if (temp[i].contains("PART")) {
						valueBuffer.append(temp[i].replace("PART", ""));
						if (i < temp.length) {
							valueBuffer.append(",");
						}
					} else if (temp[i].contains("FULL")) {
						valueBuffer.append(temp[i].replace("FULL", ""));
						if (i < temp.length) {
							valueBuffer.append(",");
						}
					}
				}

				return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
			} else if (value.contains("FULL")) {
				return value.replace("FULL", "");
			} else if (value.contains("PART")) {
				return value.replace("PART", "");
			}
		}
		return null;

	}

	public static String removePart(String value) {
		if (value.contains(",") && value.contains("FULL")) {
			String[] temp = value.split(",");
			StringBuffer valueBuffer = new StringBuffer();
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].contains("FULL")) {
					valueBuffer.append(temp[i].replace("FULL", ""));
					if (i < temp.length) {
						valueBuffer.append(",");
					}
				}
			}
			return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
		} else if (value.contains("FULL")) {
			return value.replace("FULL", "");
		}
		return null;

	}

	public static String stringRemovePartLocalBody(String value) {
		return value.replaceAll("_PART_D", "");
	}

	public static String stringRemoveFullLocalBody(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				valueBuffer.append(temp[i].replace("_FULL_D", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}

	public static String stringRemovePartLocalBodyCoverage(String value) {
		return value.replaceAll("_PART", "");
	}

	public static String stringRemoveFullLocalBodyCoverage(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				valueBuffer.append(temp[i].replace("_FULL", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();

	}

	public static String stringRemovePartLocalBodySubDist(String value) {
		value = value.replaceAll("_PART_T", "");
		/*
		 * Added replace for PART_D for district instead sub-district wise urban
		 * local body creation. @author Vinay Yadav 23-12-2013
		 */
		value = value.replaceAll("_PART_D", "");
		return value;
	}

	public static String stringRemoveFullLocalBodySubDist(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				/*
				 * Added replace for FULL_D for district instead sub-district
				 * wise urban local body creation. @author Vinay Yadav
				 * 23-12-2013
				 */
				valueBuffer.append(temp[i].replace("_FULL_T", "").replace("_FULL_D", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}
		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
	}

	public static String stringRemovePartLocalBodyDistrict(String value) {
		return value.replaceAll("_PART_T", "");
	}

	public static String stringRemoveFullLocalBodyDistrict(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				valueBuffer.append(temp[i].replace("_FULL_T", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}
		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
	}

	public static String stringRemovePartLocalBodyVillageFin(String value) {
		String[] temp = value.split(":");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("_PART_V", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
	}

	public static String stringRemoveFullLocalBodyVillageFin(String value) {
		String[] temp = value.split(":");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				valueBuffer.append(temp[i].replace("_FULL_V", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
	}

	public static String stringRemoveFullLocalBodyVillageFinBoth(String value) {
		String[] temp = value.split(":");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL") || temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("_FULL_V", "").replace("_PART_V", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
	}

	public static String stringRemovePartLocalBodyVillage(String value) {
		return value.replaceAll("_PART_V", "");
	}

	public static String stringRemoveFullLocalBodyVillage(String value) {
		String[] temp = value.split(",");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				valueBuffer.append(temp[i].replace("_FULL_V", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}
		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap stringCreationFullPart(String value) {
		String[] temp = value.split(",");
		List listPart = new ArrayList<Integer>();// Not Synchronized
		List listFull = new ArrayList<Integer>();
		HashMap mp = new HashMap();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				listPart.add(temp[i].replace("PART", ""));

			}
			if (temp[i].contains("FULL")) {
				listFull.add(temp[i].replace("FULL", ""));

			}

		}

		mp.put("PART", listPart);

		mp.put("FULL", listFull);

		return mp;

	}

	public List<District> getDistrictNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<District> districtNameList = new ArrayList<District>();
		List<District> districtNameListFull = new ArrayList<District>();

		String listOfIds = null;

		try {
			if (localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("PART") && !localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("FULL")) {
				listOfIds = stringRemovePartLocalBody(localGovtBodyForm.getLgd_LBDistCAreaDestList());
			}
			if (!localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("PART") && localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBody(localGovtBodyForm.getLgd_LBDistCAreaDestList());
			}
			if (localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("PART") && localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBDistCAreaDestList().replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {

				districtNameListFull = localGovtBodyDAO.getDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				districtNameList.addAll(districtNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return districtNameList;
	}

	public List<District> getDistrictNamebyDisIDCovChange(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<District> districtNameList = new ArrayList<District>();
		List<District> districtNameListFull = new ArrayList<District>();

		String listOfIds = null;

		try {
			if (localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("PART") && !localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("FULL")) {
				listOfIds = stringRemovePartLocalBody(localGovtBodyForm.getLgd_LBDistCAreaDestList());
			}
			if (!localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("PART") && localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBody(localGovtBodyForm.getLgd_LBDistCAreaDestList());
			}
			if (localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("PART") && localGovtBodyForm.getLgd_LBDistCAreaDestList().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBDistCAreaDestList().replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {
				String selectdDistPancIdtemp[] = dpIdObj.split(":");
				for (String dpIdObj3 : selectdDistPancIdtemp) {
					districtNameListFull = localGovtBodyDAO.getDistrictNamebyDisID(Integer.parseInt(dpIdObj3));
					districtNameList.addAll(districtNameListFull);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return districtNameList;
	}

	public List<District> getUnmappedDistrictNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<District> districtNameList = new ArrayList<District>();
		List<District> districtNameListFull = new ArrayList<District>();

		String listOfIds = null;

		try {
			if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped().contains("PART") && !localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped().contains("FULL")) {
				listOfIds = stringRemovePartLocalBody(localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped());
			}
			if (!localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped().contains("PART") && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBody(localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped());
			}
			if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped().contains("PART") && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped().replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {
				districtNameListFull = localGovtBodyDAO.getDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				districtNameList.addAll(districtNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return districtNameList;
	}

	public List<Subdistrict> getUnmappedSubDistrictNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> unmappedSubdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> unmappedSubdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;

		try {
			if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped().contains("PART") && !localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDist(localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped());
			}
			if (!localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped().contains("PART") && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDist(localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped());
			}
			if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped().contains("PART") && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {
				unmappedSubdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				unmappedSubdistrictNameList.addAll(unmappedSubdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return unmappedSubdistrictNameList;
	}

	public List<Subdistrict> getUnmappedSubDistrictNamebyDisIDforIP(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> unmappedSubdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> unmappedSubdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;

		try {
			if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("PART") && !localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDist(localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped());
			}
			if (!localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("PART") && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDist(localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped());
			}
			if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("PART") && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {
				unmappedSubdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				unmappedSubdistrictNameList.addAll(unmappedSubdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return unmappedSubdistrictNameList;
	}

	public List<Subdistrict> getUnmappedSubDistrictbyInterNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> unmappedSubdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> unmappedSubdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;

		try {
			if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("PART") && !localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDist(localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped());
			}
			if (!localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("PART") && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDist(localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped());
			}
			if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("PART") && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {
				unmappedSubdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				unmappedSubdistrictNameList.addAll(unmappedSubdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return unmappedSubdistrictNameList;
	}

	public List<Village> getconunmappedVillageNamebyVillageID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> unmappedvillagetNameList = new ArrayList<Village>();
		List<Village> unmappedvillageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped().contains("PART") && !localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped());
			}
			if (!localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped());
			}
			if (localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				unmappedvillageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				unmappedvillagetNameList.addAll(unmappedvillageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return unmappedvillagetNameList;
	}

	public List<Village> getconunmappedVillageNamebyVillageIDforIP(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> unmappedvillagetNameList = new ArrayList<Village>();
		List<Village> unmappedvillageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("PART") && !localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped());
			}
			if (!localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped());
			}
			if (localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				unmappedvillageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				unmappedvillagetNameList.addAll(unmappedvillageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return unmappedvillagetNameList;
	}

	public List<Village> getconunmappedVillageNamebyVillageIDforVP(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> unmappedvillagetNameList = new ArrayList<Village>();
		List<Village> unmappedvillageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("PART") && !localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped());
			}
			if (!localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped());
			}
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				unmappedvillageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				unmappedvillagetNameList.addAll(unmappedvillageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return unmappedvillagetNameList;
	}

	public List<Village> getconunmappedVillageNamebyInterVillageID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> unmappedvillagetNameList = new ArrayList<Village>();
		List<Village> unmappedvillageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("PART") && !localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped());
			}
			if (!localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped());
			}
			if (localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				unmappedvillageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				unmappedvillagetNameList.addAll(unmappedvillageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return unmappedvillagetNameList;
	}

	public List<Village> getconunmappedVillageNamebyVPancVillageID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> unmappedvillagetNameList = new ArrayList<Village>();
		List<Village> unmappedvillageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("PART") && !localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped());
			}
			if (!localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped());
			}
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				unmappedvillageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				unmappedvillagetNameList.addAll(unmappedvillageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return unmappedvillagetNameList;
	}

	public List<Subdistrict> getSubDistrictNamebyDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("PART") && !localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDist(localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA());
			}
			if (!localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("PART") && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDist(localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA());
			}
			if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("PART") && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				subdistrictNameList.addAll(subdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	public List<Subdistrict> getSubDistrictNamebyDisIDCovChange(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("PART") && !localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDist(localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA());
			}
			if (!localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("PART") && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDist(localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA());
			}
			if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("PART") && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				String selectSubDistPancIdtemp[] = dpIdObj.split(":");
				for (String dpIdObj3 : selectSubDistPancIdtemp) {
					subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj3));
					subdistrictNameList.addAll(subdistrictNameListFull);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	public List<Subdistrict> getSubDistrictNamebyDisIDInter(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("PART") && !localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDist(localGovtBodyForm.getLgd_LBInterCAreaDestList());
			}
			if (!localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("PART") && localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDist(localGovtBodyForm.getLgd_LBInterCAreaDestList());
			}
			if (localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("PART") && localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBInterCAreaDestList().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				String selectdSubdistIdtemp[] = dpIdObj.split(":");
				for (String dpIdObj3 : selectdSubdistIdtemp) {
					subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj3));
					subdistrictNameList.addAll(subdistrictNameListFull);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	public List<Subdistrict> getSubDistrictNamebyDisIDULB(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("PART") && !localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDist(localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest());
			}
			if (!localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("PART") && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDist(localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest());
			}
			if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("PART") && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				subdistrictNameList.addAll(subdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	public List<Subdistrict> getSubDistrictNamebyDisIDULBFinal(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("PART") && !localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDistFinal(localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest());
			}
			if (!localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("PART") && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDistFinal(localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest());
			}
			if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("PART") && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyUrbanFinBoth(localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest());
				// listOfIds=localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().replaceAll("_PART_T",
				// "").replaceAll("_FULL_T", "");
			}
			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				subdistrictNameList.addAll(subdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	public static String stringRemovePartLocalBodySubDistFinal(String value) {
		String[] temp = value.split(":");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("_PART_T", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
	}

	public static String stringRemoveFullLocalBodySubDistFinal(String value) {
		String[] temp = value.split(":");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL")) {
				valueBuffer.append(temp[i].replace("_FULL_T", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
	}

	public static String stringRemoveFullLocalBodyUrbanFinBoth(String value) {
		String[] temp = value.split(":");
		StringBuffer valueBuffer = new StringBuffer();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].contains("FULL") || temp[i].contains("PART")) {
				valueBuffer.append(temp[i].replace("_FULL_T", "").replace("_PART_T", ""));
				if (i < temp.length) {
					valueBuffer.append(",");
				}
			}
		}

		return (valueBuffer.substring(0, valueBuffer.length() - 1)).toString();
	}

	public List<Subdistrict> getSubDistrictNamebyCovChangeULB(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null) {
				listOfIds = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban();
			}
			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				subdistrictNameList.addAll(subdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	/**
	 * The {@code getDistrictNamebyCovChangeULB} return all District Names based
	 * on
	 * 
	 * @param availlgdLBInterCAreaSourceListUmappedUrban
	 *            .
	 * @param availlgdLBInterCAreaSourceListUmappedUrban
	 * @return
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	public String getDistrictNamebyCovChangeULB(String availlgdLBInterCAreaSourceListUmappedUrban) throws Exception {
		return localGovtBodyDAO.getDistrictNamebyCovChangeULB(availlgdLBInterCAreaSourceListUmappedUrban);
	}

	public List<District> getDistrictNamebyCovChangePRITrad(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<District> districtNameList = new ArrayList<District>();
		List<District> districtNameListFull = new ArrayList<District>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null) {
				if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped().contains("_P_true") || localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped().contains("_P_false")
						|| localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped().contains("_F_true") || localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped().contains("_F_false")) {
					listOfIds = localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
				} else {
					listOfIds = localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped();
				}
				String selectdDistId[] = listOfIds.split(",");
				for (String dpIdObj : selectdDistId) {
					districtNameListFull = localGovtBodyDAO.getDistrictNamebyDisID(Integer.parseInt(dpIdObj));
					districtNameList.addAll(districtNameListFull);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return districtNameList;
	}

	public List<Subdistrict> getSubDistrictNamebyCovChangePRITrad(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null) {
				if (localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped().contains("_P_true") || localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped().contains("_P_false")
						|| localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped().contains("_F_true") || localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped().contains("_F_false")) {
					listOfIds = localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
				} else {
					listOfIds = localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped();
				}

				String selectdSubDistId[] = listOfIds.split(",");
				for (String dpIdObj : selectdSubDistId) {
					subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
					subdistrictNameList.addAll(subdistrictNameListFull);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	public List<Subdistrict> getSubDistrictNamebyCovChangePRITradInter(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped().contains("_P_true") || localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped().contains("_P_false")
					|| localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped().contains("_F_true") || localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped().contains("_F_false")) {
				listOfIds = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
			} else {
				listOfIds = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped();
			}

			String selectdSubDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubDistId) {
				subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				subdistrictNameList.addAll(subdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	public List<Village> getVillageNamebyCovChangePRITrad(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null) {

				if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped().contains("_P_true") || localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped().contains("_P_false")
						|| localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped().contains("_F_true") || localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped().contains("_F_false")) {
					listOfIds = localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
				} else {
					listOfIds = localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped();
				}

				String selectdVillageId[] = listOfIds.split(",");
				for (String dpIdObj : selectdVillageId) {
					villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
					villagetNameList.addAll(villageNameListFull);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	public List<Village> getVillageNamebyCovChangePRITradInter(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null) {
				if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped().contains("_P_true") || localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped().contains("_P_false")
						|| localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped().contains("_F_true") || localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped().contains("_F_false")) {
					listOfIds = localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
				} else {
					listOfIds = localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped();
				}
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				villagetNameList.addAll(villageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	/*@Override
	public List<Village> getVillageNamebyCovChangePRITradVill(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null) {
				listOfIds = localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped();
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				villagetNameList.addAll(villageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}*/

	public List<Village> getVillageNamebyCovChangePRITradVillFinal(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null) {
				listOfIds = localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped().replaceAll("_P_false", "").replaceAll("_P_true", "").replaceAll("_F_false", "").replaceAll("_F_true", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				villagetNameList.addAll(villageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	public List<Subdistrict> getunMappedSubDistrictNamebyDisIDULB(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().contains("PART") && !localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDist(localGovtBodyForm.getLgd_UrbanLBDistUmappedDest());
			}
			if (!localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().contains("PART") && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDist(localGovtBodyForm.getLgd_UrbanLBDistUmappedDest());
			}
			if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().contains("PART") && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
				/*
				 * Added replace for PART_D and FULL_D for district instead
				 * sub-district wise urban local body creation. @author Vinay
				 * Yadav 23-12-2013
				 */
				listOfIds = listOfIds.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}
			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				subdistrictNameList.addAll(subdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	public static void main(String[] args) {
		String ss = "766_PART_T,910_PART_T,845_PART_T,972_FULL_T".replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
		ss = ss.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
		System.out.println(ss);
	}

	public List<Subdistrict> getSubDistrictNamebyInterSubDisID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("PART") && !localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodySubDist(localGovtBodyForm.getLgd_LBInterCAreaDestList());
			}
			if (!localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("PART") && localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodySubDist(localGovtBodyForm.getLgd_LBInterCAreaDestList());
			}
			if (localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("PART") && localGovtBodyForm.getLgd_LBInterCAreaDestList().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBInterCAreaDestList().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictNameListFull = localGovtBodyDAO.getSubDistrictNamebyDisID(Integer.parseInt(dpIdObj));
				subdistrictNameList.addAll(subdistrictNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictNameList;
	}

	public List<Village> getconVillageNamebyVillID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("PART") && !localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatDCA());
			}
			if (!localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatDCA());
			}
			if (localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageDestLatDCA().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				villagetNameList.addAll(villageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	public List<Village> getconVillageNamebyVillIDforChCov(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("PART") && !localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatDCA());
			}
			if (!localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatDCA());
			}
			if (localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatDCA().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageDestLatDCA().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				String selectdVillagePancIdtemp[] = dpIdObj.split(":");
				for (String dpIdObj3 : selectdVillagePancIdtemp) {
					villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj3));
					villagetNameList.addAll(villageNameListFull);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	public List<Village> getconVillageNamebyVillIDVill(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("PART") && !localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICA());
			}
			if (!localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICA());
			}
			if (localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageDestLatICA().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				String selectdVillageIdtemp[] = dpIdObj.split(":");
				for (String dpIdObj3 : selectdVillageIdtemp) {
					villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj3));
					villagetNameList.addAll(villageNameListFull);
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	public List<Village> getconVillageNamebyVillIDVillChCov(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("PART") && !localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICA());
			}
			if (!localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICA());
			}
			if (localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageDestLatICA().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				String selectdVillPancIdtemp[] = dpIdObj.split(":");
				for (String dpIdObj3 : selectdVillPancIdtemp) {
					villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj3));
					villagetNameList.addAll(villageNameListFull);
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	/*@Override
	public List<Village> getconVillageNamebyVillIDVP(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("PART") && !localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageCAreaDestL());
			}
			if (!localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageCAreaDestL());
			}
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageCAreaDestL().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				villagetNameList.addAll(villageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}*/

	public List<Village> getconVillageNamebyVillIDVPFin(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("PART") && !localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillageFin(localGovtBodyForm.getLgd_LBVillageCAreaDestL());
			}
			if (!localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillageFin(localGovtBodyForm.getLgd_LBVillageCAreaDestL());
			}
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillageFinBoth(localGovtBodyForm.getLgd_LBVillageCAreaDestL());
				// listOfIds =
				// localGovtBodyForm.getLgd_LBVillageCAreaDestL().replaceAll("_PART_V",
				// "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				villagetNameList.addAll(villageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	public List<Village> getconVillageNamebyInterVillID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("PART") && !localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICA());
			}
			if (!localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageDestLatICA());
			}
			if (localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("PART") && localGovtBodyForm.getLgd_LBVillageDestLatICA().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageDestLatICA().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				villagetNameList.addAll(villageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	public List<Village> getconVillageNamebyVillageID(LocalGovtBodyForm localGovtBodyForm) throws Exception {
		List<Village> villagetNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try {
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("PART") && !localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("FULL")) {
				listOfIds = stringRemovePartLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageCAreaDestL());
			}
			if (!localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("FULL")) {
				listOfIds = stringRemoveFullLocalBodyVillage(localGovtBodyForm.getLgd_LBVillageCAreaDestL());
			}
			if (localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("PART") && localGovtBodyForm.getLgd_LBVillageCAreaDestL().contains("FULL")) {
				listOfIds = localGovtBodyForm.getLgd_LBVillageCAreaDestL().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
			}
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId) {
				villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				villagetNameList.addAll(villageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villagetNameList;
	}

	@Override
	public List<Localbody> getLocalGovtBodyList(int localBodyCode, int stateCode) throws Exception {
		return localGovtBodyDAO.getLocalBodyList(localBodyCode, stateCode);
	}

	// Added by Pooja
	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcode(int localBodyCode) throws Exception {
		return localGovtBodyDAO.getchildlocalbodiesByParentcode(localBodyCode);
	}

	// Added by Pooja
	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcodeChangeCoverage(int localBodyCode, int lbCode) throws Exception {
		return localGovtBodyDAO.getchildlocalbodiesByParentcodeChangeCoverage(localBodyCode, lbCode);
	}

	// Added by Arnab
	public List<ParentWiseLocalBodies> getchildlocalbodiesWithoutCountByParentcode(int localBodyCode) throws Exception {
		return localGovtBodyDAO.getchildlocalbodiesWithoutCountByParentcode(localBodyCode);
	}

	public List<Localbody> getLocalBodyNamebyLBID(String formvalue) throws Exception {
		List<Localbody> localbodyNameList = new ArrayList<Localbody>();
		List<Localbody> localbodyNameListFull = new ArrayList<Localbody>();

		String listOfIds = null;

		try {
			if (formvalue.contains("FULL") || formvalue.contains("PART")) {
				listOfIds = formvalue.replaceAll("_FULL", "").replaceAll("_PART", "");
			}
			if (!formvalue.contains("PART") && !formvalue.contains("FULL")) {
				listOfIds = formvalue;
			}
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {
				if (dpIdObj != null && !"".equals(dpIdObj)) { // added condition
																// to avoid
																// NumberFormatException
					localbodyNameListFull = localGovtBodyDAO.getLocalBodyNamebyLBID(Integer.parseInt(dpIdObj));
					localbodyNameList.addAll(localbodyNameListFull);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return localbodyNameList;
	}

	public List<Localbody> getLocalBodyNamebyLBIDforHeiRChange(String formvalue) throws Exception {
		List<Localbody> localbodyNameList = new ArrayList<Localbody>();
		List<Localbody> localbodyNameListFull = new ArrayList<Localbody>();
		String listOfIds = null;
		try {

			listOfIds = formvalue;
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {
				localbodyNameListFull = localGovtBodyDAO.getLocalBodyNamebyLBID(Integer.parseInt(dpIdObj));
				localbodyNameList.addAll(localbodyNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return localbodyNameList;
	}

	public List<LocalBodyType> getLocalBodyTypeNamebyLBTID(String formvalue) throws Exception {
		List<LocalBodyType> localbodyTypeNameList = new ArrayList<LocalBodyType>();
		List<LocalBodyType> localbodyTypeNameListFull = new ArrayList<LocalBodyType>();

		String listOfIds = null;

		try {
			if (!formvalue.contains("PART") && !formvalue.contains("FULL")) {
				listOfIds = formvalue;
			}
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {
				localbodyTypeNameListFull = localGovtBodyDAO.getLocalBodyTypeNamebyLBTID(Integer.parseInt(dpIdObj));
				localbodyTypeNameList.addAll(localbodyTypeNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return localbodyTypeNameList;
	}

	public List<LocalBodyType> getLocalBodyTypeNamebyChangeLBN(int formvalue) throws Exception {
		List<LocalBodyType> localbodyTypeNameList = new ArrayList<LocalBodyType>();

		try {
			localbodyTypeNameList = localGovtBodyDAO.getLocalBodyTypeNamebyLBTID(formvalue);
		} catch (Exception e) {
			throw e;
		}
		return localbodyTypeNameList;
	}

	public List<Village> getVillageNamebyVillID(String formvalue) throws Exception {
		List<Village> villageNameList = new ArrayList<Village>();
		List<Village> villageNameListFull = new ArrayList<Village>();

		String listOfIds = null;

		try {
			if (formvalue.contains("FULL") || formvalue.contains("PART")) {
				listOfIds = formvalue.replaceAll("_FULL", "").replaceAll("_PART", "");
			}
			if (!formvalue.contains("PART") && !formvalue.contains("FULL")) {
				listOfIds = formvalue;
			}
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId) {
				villageNameListFull = localGovtBodyDAO.getconVillageNamebyVillID(Integer.parseInt(dpIdObj));
				villageNameList.addAll(villageNameListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villageNameList;
	}

	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcodeInter(String localBodyCode) throws Exception {

		List<ParentWiseLocalBodies> interLocalBodyList = new ArrayList<ParentWiseLocalBodies>();
		List<ParentWiseLocalBodies> interLocalBodyListFull = new ArrayList<ParentWiseLocalBodies>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullSemFinal(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartSemFinal(localBodyCode);
		}
		String selectdLocalbody[] = listOfIds.split(",");
		for (String localbodyobj : selectdLocalbody) {
			interLocalBodyList = localGovtBodyDAO.getchildlocalbodiesByParentcode(Integer.parseInt(localbodyobj));
			interLocalBodyListFull.addAll(interLocalBodyList);
		}

		return interLocalBodyListFull;
	}

	public List<Localbody> getLocalGovtBodyVillageList(int localBodyCode) throws Exception {

		return localGovtBodyDAO.getLocalGovtBodyVillageList(localBodyCode);
	}

	public List<Localbody> getLocalGovtBodyMappedVillageList(int landRegionCode, int lbtypecode) throws Exception {

		return localGovtBodyDAO.getLocalGovtBodyMappedVillageList(landRegionCode, lbtypecode);
	}

	public List<Localbody> getLocalGovtBodyParentList(int localBodyCode) throws Exception {

		return localGovtBodyDAO.getLocalGovtBodyParentList(localBodyCode);
	}

	public List<Subdistrict> getSubDistrictListforLocalBody(String districtCode) throws NumberFormatException, Exception {
		List<Subdistrict> subDistrictList = new ArrayList<Subdistrict>();
		List<Subdistrict> subDistrictListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		if (districtCode.contains(",")) {
			listOfIds = stringRemoveFull(districtCode);
		} else if (districtCode.contains("PART")) {
			listOfIds = stringRemovePart(districtCode);
		}
		String selectdDistrct[] = listOfIds.split(",");
		for (String distobj : selectdDistrct) {

			subDistrictList = subdistrictDAO.getSubDistrictListbyDistrictCode(Integer.parseInt(distobj));
			subDistrictListFull.addAll(subDistrictList);
		}

		return subDistrictListFull;

	}

	public List<Village> getVillageListforLocalBody(String subdistrictCode) throws NumberFormatException, Exception {
		List<Village> villageList = new ArrayList<Village>();
		List<Village> villageListFull = new ArrayList<Village>();

		String listOfIds = null;
		if (subdistrictCode.contains(",")) {
			listOfIds = stringRemoveFull(subdistrictCode);
		} else if (subdistrictCode.contains("PART")) {
			listOfIds = stringRemovePart(subdistrictCode);
		}
		String selectdSubDistrct[] = listOfIds.split(",");
		for (String subdistobj : selectdSubDistrct) {

			villageList = villageDAO.getVillageListbySubDistrictCode(Integer.parseInt(subdistobj));
			villageListFull.addAll(villageList);
		}

		return villageListFull;
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforname(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			if (localBodyCode.contains("FULL")) {
				listOfIds = stringRemoveFullSforname(localBodyCode);
			}

			else if (localBodyCode.contains("PART")) {
				listOfIds = stringRemovePartSforname(localBodyCode);
			}
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		// for(String vpIdObj : selectdVillagePancId)
		// {

		coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredVillageList(Integer.parseInt(selectdVillagePancId[0]));
		coveredAreaList.addAll(coveredAreaListFull);
		// }

		return coveredAreaList;

	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforrlbtoulb(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		try {
			String listOfIds = "";
			if (localBodyCode != null && localBodyCode.contains(",")) {
				String lbodyArray[] = localBodyCode.split(",");
				for (int i = 0; i < lbodyArray.length; i++) {
					if (lbodyArray[i].contains("PART")) {
						String str = lbodyArray[i].substring(0, lbodyArray[i].indexOf("_"));
						listOfIds += concateLocalBody(str);
					}
				}
			}
			String selectdVillagePancId[] = listOfIds.split(",");
			for (String vpIdObj : selectdVillagePancId) {
				coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredVillageList(Integer.parseInt(vpIdObj));
				if (!coveredAreaListFull.isEmpty()) {
					for (LocalBodyCoveredArea coveredArea : coveredAreaListFull) {
						coveredArea.setLandRegionCodeStr(coveredArea.getLandRegionCode() + "_" + vpIdObj + "_P");
					}
				}
				coveredAreaList.addAll(coveredAreaListFull);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return coveredAreaList;
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageList(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFull(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePart(localBodyCode);
		}

		String selectdVillagePancId[] = listOfIds.split(",");
		for (String vpIdObj : selectdVillagePancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredVillageListFinal(Integer.parseInt(vpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredVillageListforLB(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> coveredAreaList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> coveredAreaListFull = new ArrayList<LocalBodyCoveredAreaLB>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullS(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartS(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String vpIdObj : selectdVillagePancId) {
			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyCoveredVillageListforLB(Integer.parseInt(vpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredVillageListforLBforMCov(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> coveredAreaList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> coveredAreaListFull = new ArrayList<LocalBodyCoveredAreaLB>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullS(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartS(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String vpIdObj : selectdVillagePancId) {
			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyCoveredVillageListforLBforMCov(Integer.parseInt(vpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredDistrictListFinalforLBforMCov(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> coveredAreaList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> coveredAreaListFull = new ArrayList<LocalBodyCoveredAreaLB>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullS(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartS(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String vpIdObj : selectdVillagePancId) {
			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyCoveredDistrictListforLBforMCov(Integer.parseInt(vpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforModify(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullLocalBodyVillage(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartLocalBodyVillage(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String vpIdObj : selectdVillagePancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredVillageList(Integer.parseInt(vpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforModifyCoverage(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullSemFinal(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartSemFinal(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String vpIdObj : selectdVillagePancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredVillageList(Integer.parseInt(vpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListforWard(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		/*
		 * List<LocalBodyCoveredArea> coveredAreaListFull = new
		 * ArrayList<LocalBodyCoveredArea>();
		 * 
		 * String listOfIds = null; if (localBodyCode.contains(",")) listOfIds =
		 * stringRemoveFull(localBodyCode); else if
		 * (localBodyCode.contains("PART")) listOfIds =
		 * stringRemovePart(localBodyCode);
		 * 
		 * String selectdVillagePancId[] = listOfIds.split(","); for(String
		 * vpIdObj : selectdVillagePancId) {
		 * 
		 * coveredAreaListFull = localGovtBodyDAO
		 * .getLocalGovtBodyforCoveredVillageList(Integer .parseInt( vpIdObj));
		 * coveredAreaList.addAll(coveredAreaListFull); }
		 */
		coveredAreaList = localGovtBodyDAO.getNewWardVillageCoverage(localBodyCode);

		return coveredAreaList;

	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictList(String localBodyCode) throws NumberFormatException, Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFull(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePart(localBodyCode);
		}
		String selectdDistPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredDistrictList(Integer.parseInt(dpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListforHeadQuarter(String localBodyCode) throws NumberFormatException, Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinalTo(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdDistPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredDistrictList(Integer.parseInt(dpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListFinal(String localBodyCode) throws NumberFormatException, Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinal(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdDistPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredDistrictList(Integer.parseInt(dpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredDistrictListFinalforLB(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> coveredAreaList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> coveredAreaListFull = new ArrayList<LocalBodyCoveredAreaLB>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinal(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdDistPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredDistrictListFinalforLB(Integer.parseInt(dpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListFinalTo(String localBodyCode) throws NumberFormatException, Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinalTo(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdDistPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredDistrictList(Integer.parseInt(dpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListFinalToDistrict(String localBodyCode) throws NumberFormatException, Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinalTo(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdDistPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredDistrictListDistrict(Integer.parseInt(dpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistrictListFinalTo(String localBodyCode) throws NumberFormatException, Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinalTo(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdDistPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredSubDistrictList(Integer.parseInt(dpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistrictListInterTo(String localBodyCode) throws NumberFormatException, Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinalTo(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdDistPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredSubDistrictListInter(Integer.parseInt(dpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredVillageListFinalTo(String localBodyCode) throws NumberFormatException, Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinalTo(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdDistPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredVillageFinalList(Integer.parseInt(dpIdObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	// for View Local body Success
	public List<LocalBodyCoveredArea> getCoveredDistrictListforLB(int localBodyCode) throws Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();

		coveredAreaList = localGovtBodyDAO.getLocalGovtBodyforCoveredDistrictList(localBodyCode);

		return coveredAreaList;
	}

	// for View Local body Success
	public List<LocalBodyCoveredArea> getCoveredSubDistrictListforLB(int localBodyCode) throws Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();

		coveredAreaList = localGovtBodyDAO.getLocalGovtBodyforCoveredSubDistList(localBodyCode);

		return coveredAreaList;

	}

	// for View Local body Success
	public List<LocalBodyCoveredArea> getCoveredVillageListforLB(int localBodyCode) throws Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();

		coveredAreaList = localGovtBodyDAO.getLocalGovtBodyforCoveredVillageListFinal(localBodyCode);

		return coveredAreaList;

	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistList(String localBodyCode) throws NumberFormatException, Exception {

		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullS(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartS(localBodyCode);
		}
		String selectdSubDistPancId[] = listOfIds.split(",");
		for (String sdpIDObj : selectdSubDistPancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredSubDistList(Integer.parseInt(sdpIDObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistListFinal(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> coveredAreaList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> coveredAreaListFull = new ArrayList<LocalBodyCoveredAreaLB>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullS(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartS(localBodyCode);
		}
		String selectdSubDistPancId[] = listOfIds.split(",");
		for (String sdpIDObj : selectdSubDistPancId) {
			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredSubDistListFinal(Integer.parseInt(sdpIDObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistListFinalforCov(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> coveredAreaList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> coveredAreaListFull = new ArrayList<LocalBodyCoveredAreaLB>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullSemFinal(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartSemFinal(localBodyCode);
		}
		String selectdSubDistPancId[] = listOfIds.split(",");
		for (String sdpIDObj : selectdSubDistPancId) {
			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredSubDistListFinalChangeCov(Integer.parseInt(sdpIDObj));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public List<LBCoverageWard> getLocalGovtBodyforCoveredSubDistListforWard(String localBodyCode) throws NumberFormatException, Exception {

		List<LBCoverageWard> coveredAreaList = new ArrayList<LBCoverageWard>();
		/*
		 * List<LBCoverageWard> coveredAreaListFull = new
		 * ArrayList<LBCoverageWard>();
		 * 
		 * String listOfIds = null; if (localBodyCode.contains(",")) listOfIds =
		 * stringRemoveFull(localBodyCode); else if
		 * (localBodyCode.contains("PART")) listOfIds =
		 * stringRemovePart(localBodyCode);
		 * 
		 * String selectdSubDistPancId[] = listOfIds.split(","); for(String
		 * sdpIDObj : selectdSubDistPancId) {
		 * 
		 * coveredAreaListFull = localGovtBodyDAO
		 * .getLocalGovtBodyforCoveredSubDistListforWard(Integer
		 * .parseInt(sdpIDObj)); coveredAreaList.addAll(coveredAreaListFull); }
		 */
		coveredAreaList = localGovtBodyDAO.getLocalGovtBodyforCoveredSubDistListforWard(localBodyCode);

		return coveredAreaList;

	}

	/*@Override
	public List<ParentWiseLocalBodies> getIntermediatePanchayatbyParentCodes(String localBodyCode) throws NumberFormatException, Exception {

		List<ParentWiseLocalBodies> coveredAreaList = new ArrayList<ParentWiseLocalBodies>();
		List<ParentWiseLocalBodies> coveredAreaListFull = new ArrayList<ParentWiseLocalBodies>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFull(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePart(localBodyCode);
		}
		String selectdDistrictPancId[] = listOfIds.split(",");
		for (String dpIdObj : selectdDistrictPancId) {

			coveredAreaListFull = localGovtBodyDAO.getchildlocalbodiesByParentcode(Integer.parseInt(dpIdObj.toString()));

			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}*/

	public List<ParentWiseLocalBodiesWithoutChildCount> getIntermediatePanchayatbyParentCodeWithoutChild(String localBodyCode) throws NumberFormatException, Exception {

		List<ParentWiseLocalBodiesWithoutChildCount> coveredAreaList = new ArrayList<ParentWiseLocalBodiesWithoutChildCount>();
		List<ParentWiseLocalBodiesWithoutChildCount> coveredAreaListFull = new ArrayList<ParentWiseLocalBodiesWithoutChildCount>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFull(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePart(localBodyCode);
		}
		if (localBodyCode.contains(",")) {
			String selectdDistrictPancId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistrictPancId) {

				coveredAreaListFull = localGovtBodyDAO.getchildlocalbodiesByParentcodeWithoutChild(Integer.parseInt(dpIdObj.toString()));

				coveredAreaList.addAll(coveredAreaListFull);
			}
		} else {
			coveredAreaListFull = localGovtBodyDAO.getchildlocalbodiesByParentcodeWithoutChild(Integer.parseInt(localBodyCode.toString()));

			coveredAreaList.addAll(coveredAreaListFull);
		}
		return coveredAreaList;

	}

	/*@Override
	public List<LocalBodyUrbanType> getLocalBodyUrbanName(int localBodyCode, String urbanTypeBodyCode, int stateCode, int localBodyTypeCode) throws Exception {

		List<LocalBodyUrbanType> LocalbodyforStateWise1 = new ArrayList<LocalBodyUrbanType>();
		LocalbodyforStateWise1 = localGovtBodyDAO.getLocalBodyUrbanName(localBodyCode, urbanTypeBodyCode, stateCode, localBodyTypeCode);
		return LocalbodyforStateWise1;

	}*/

	/*@Override
	public List<LocalBodyTypeForStateWiseView> getLocalBodyListStateWiseView(int stateCode) throws Exception {
		List<LocalBodyTypeForStateWiseView> LocalbodyforStateWise1 = new ArrayList<LocalBodyTypeForStateWiseView>();
		LocalbodyforStateWise1 = localGovtBodyDAO.getLocalBodyListStateWiseView(stateCode);
		return LocalbodyforStateWise1;
	}*/

	public List<LocalGovtBodyForSelectedBody> getLocalBodyListForSelectedBody(int localBodyTypeCode, int stateCode) throws Exception {
		List<LocalGovtBodyForSelectedBody> LocalGovtBodyForSelectedBody = new ArrayList<LocalGovtBodyForSelectedBody>();
		LocalGovtBodyForSelectedBody = localGovtBodyDAO.getLocalBodyListForSelectedBody(localBodyTypeCode, stateCode);
		return LocalGovtBodyForSelectedBody;
	}

	public List<LocalBodyViewChild> getLocalBodyListForSubDistBody(int localBodyTypeCode, int stateCode, int localBodyCode) throws Exception {
		List<LocalBodyViewChild> LocalGovtBodyForSelectedBody = new ArrayList<LocalBodyViewChild>();
		LocalGovtBodyForSelectedBody = localGovtBodyDAO.getLocalBodyListForSubDistBody(localBodyTypeCode, stateCode, localBodyCode);
		return LocalGovtBodyForSelectedBody;
	}

	public List<LocalBodyType> getGovtTypeViewList(String query) throws Exception {
		return localGovtBodyDAO.getGovtTypeViewList(query);
	}

	public List<LocalGovtTypeDataForm> getLocalBodyTypeDetails(int localBodyTypeCode) throws Exception {
		return localGovtBodyDAO.getLocalBodyTypeDetails(localBodyTypeCode);
	}

	/*@Override
	public List<ChildLocalGovtBody> getLocalGovtBodyDetail(int localBodyTypeCode) throws Exception {
		return localGovtBodyDAO.getChildLocalBodyListForSelectedBody(localBodyTypeCode);
	}
*/
	// For localbody upload file
	public boolean writeMap(MultipartFile filePath, HttpServletRequest request) {

		FileOutputStream fo = null;
		try {
			MultipartFile map = filePath;

			if (map.getBytes().length > 0) {

				@SuppressWarnings("deprecation")
				String savePath = request.getRealPath("/") + map.getOriginalFilename();

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
				if (fo != null) {
					fo.close();
				}
			} catch (IOException e) {
				// ProfilerLogger.getLogger(WriteImage.class).error("Unable to close  FileOutputStream  . . . . . . . . . .  .  . .  . . . . ."+e.toString());
			}
		}
		return true;
	}

	// End here

	// Get Unmapped local bodies from Localbody
	public List<LocalbodyUnMappedBody> getLocalGovtBodyforUnmappedLocalBodyList(char localBodyType, int localBodyCode) throws Exception {

		List<LocalbodyUnMappedBody> localbodyUnMappedBody = new ArrayList<LocalbodyUnMappedBody>();
		localbodyUnMappedBody = localGovtBodyDAO.getLocalGovtBodyforUnmappedLocalBodyList(localBodyType, localBodyCode);

		return localbodyUnMappedBody;
	}

	// Get Unmapped DIstList By StateCode
	/*@Override
	public List<LocalbodyUnMappedBody> getUnmappedDistList(char localBodyType, int stateCode) throws Exception {

		List<LocalbodyUnMappedBody> localbodyUnMappedDistList = new ArrayList<LocalbodyUnMappedBody>();
		localbodyUnMappedDistList = localGovtBodyDAO.getLocalGovtBodyforUnmappedLocalBodyList(localBodyType, stateCode);

		return localbodyUnMappedDistList;
	}
*/
	/**
	 * @param stateCode
	 * @param category
	 * @return
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	public List<LocalbodyListbyState> getPanchayatListbyStateandCategory(int stateCode, char category, char level) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		String localbodyCodes = localGovtBodyDAO.getLocalBodyTypeCode(category, level);
		String[] localbodyCodeList = localbodyCodes.split(",");

		for (int i = 0; i < localbodyCodeList.length; i++) {
			if (localbodyCodeList[i] != null) {
				int lbTypeCode = Integer.parseInt(localbodyCodeList[i].toString());
				List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbTypeCode, stateCode);
				localbodywholeList.addAll(localbodyList);
			}
		}
		return localbodywholeList;
	}

	/*@Override
	public List<LocalbodyListbyState> getPanchayatListbyParentCategory(int stateCode, char category, char level) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		int lbTypeCode = localGovtBodyDAO.getLocalBodyParentTypeCode(category, level);

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbTypeCode, stateCode);
		localbodywholeList.addAll(localbodyList);

		return localbodywholeList;
	}*/

	// Code Added to resolve the issue related to Change LB Type for 2 tier.
	/*@Override
	public List<LocalbodyListbyState> getPanchayatListbyParentCategoryF(int stateCode, char category, char level, int parentLbCode) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		int lbTypeCode = localGovtBodyDAO.getLocalBodybyLBID(parentLbCode);

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbTypeCode, stateCode);
		localbodywholeList.addAll(localbodyList);

		return localbodywholeList;
	}
*/
	public List<LocalbodyListbyState> getPanchayatListbyParentCategoryFChangeTier(int stateCode, char category, char level, int parentLbCode) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		int lbTypeCode = localGovtBodyDAO.getLocalBodybyLBID(parentLbCode);

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatListChangeTier(lbTypeCode, stateCode, parentLbCode);
		localbodywholeList.addAll(localbodyList);

		return localbodywholeList;
	}

	public List<LocalbodyListbyState> getPanchayatListbylblcCode(int stateCode, int lbTypeCode) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbTypeCode, stateCode);
		localbodywholeList.addAll(localbodyList);

		return localbodywholeList;
	}

	/*@Override
	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryNewWard(int stateCode, char category, char lblevel) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		String localbodyCodes = localGovtBodyDAO.getLocalBodyTypeCodeNewWard(category, lblevel);
		String[] localbodyCodeList = localbodyCodes.split(",");

		for (int i = 0; i < localbodyCodeList.length; i++) {
			if (localbodyCodeList[i] != null) {
				int lbTypeCode = Integer.parseInt(localbodyCodeList[i].toString());
				List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbTypeCode, stateCode);
				localbodywholeList.addAll(localbodyList);
			}
		}
		return localbodywholeList;
	}*/

	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryNewWardF(int lbtypeCd, int stateCode) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		try {
			List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbtypeCd, stateCode);
			localbodywholeList.addAll(localbodyList);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return localbodywholeList;
	}

	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryParentInter(int stateCode, char category, char lblevel, int parentyresetupCd) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		List<LocalbodyforStateWise> localbodyListTemp = new ArrayList<LocalbodyforStateWise>();
		List<LocalbodyforStateWise> localbodyListFinal = new ArrayList<LocalbodyforStateWise>();
		List<LocalbodyforStateWise> localbodyListFinalInner = new ArrayList<LocalbodyforStateWise>();

		int temptiersetupcode = 0;
		int finaltiersetupcode = 0;
		int finaltiersetupcodeinner = 0;
		//String localbodyCodes = null;
		//String level = null;
		int lbtypeCode = 0;
		if (parentyresetupCd != 0) {
			localbodyListTemp = localGovtBodyDAO.getLocalBodyListStateWiseTierSet(category, stateCode, parentyresetupCd);
			Iterator<LocalbodyforStateWise> localbodyListTemplistItr = localbodyListTemp.iterator();
			while (localbodyListTemplistItr.hasNext()) {
				temptiersetupcode = localbodyListTemplistItr.next().getParentLocalBodyTypeCode();
			}
			if (temptiersetupcode == 0) {
				Iterator<LocalbodyforStateWise> localbodyListTemplistItrF = localbodyListTemp.iterator();
				while (localbodyListTemplistItrF.hasNext()) {
					lbtypeCode = localbodyListTemplistItrF.next().getLocalBodyTypeCode();
				}
			}

			//Iterator<LocalbodyforStateWise> localbodyListFinalTmp = localbodyListTemp.iterator();
			/*while (localbodyListFinalTmp.hasNext()) {
				level = localbodyListFinalTmp.next().getLevel();
			}*/

			if (temptiersetupcode != 0) {
				localbodyListFinal = localGovtBodyDAO.getLocalBodyListStateWiseTierSet(category, stateCode, temptiersetupcode);
				Iterator<LocalbodyforStateWise> localbodyListFinallistItr = localbodyListFinal.iterator();
				while (localbodyListFinallistItr.hasNext()) {
					finaltiersetupcode = localbodyListFinallistItr.next().getParentLocalBodyTypeCode();
				}

				if (finaltiersetupcode == 0) {
					Iterator<LocalbodyforStateWise> localbodyListTemplistItrF = localbodyListFinal.iterator();
					while (localbodyListTemplistItrF.hasNext()) {
						lbtypeCode = localbodyListTemplistItrF.next().getLocalBodyTypeCode();
					}
				}

				//Iterator<LocalbodyforStateWise> localbodyListFinallistItrF = localbodyListFinal.iterator();
				/*while (localbodyListFinallistItrF.hasNext()) {
					level = localbodyListFinallistItrF.next().getLevel();
				}*/

				if (finaltiersetupcode != 0) {
					localbodyListFinalInner = localGovtBodyDAO.getLocalBodyListStateWiseTierSet(category, stateCode, finaltiersetupcode);
					Iterator<LocalbodyforStateWise> localbodyListFinallistInnerItr = localbodyListFinalInner.iterator();
					while (localbodyListFinallistInnerItr.hasNext()) {
						finaltiersetupcodeinner = localbodyListFinallistInnerItr.next().getParentLocalBodyTypeCode();
					}
				}

				if (finaltiersetupcodeinner == 0) {
					Iterator<LocalbodyforStateWise> localbodyListTemplistItrInnnerF = localbodyListFinalInner.iterator();
					while (localbodyListTemplistItrInnnerF.hasNext()) {
						lbtypeCode = localbodyListTemplistItrInnnerF.next().getLocalBodyTypeCode();
					}
				}

			}
		}
		if (parentyresetupCd == 0) {
			//localbodyCodes = 
			localGovtBodyDAO.getLocalBodyTypeCodeNewWard(category, lblevel);
		}

		/*
		 * if(parentyresetupCd !=0 && (temptiersetupcode==0 ||
		 * finaltiersetupcode==0)) { localbodyCodes =
		 * localGovtBodyDAO.getLocalBodyTypeCodeNewWard
		 * (category,level.charAt(0)); }
		 * 
		 * String[] localbodyCodeList = localbodyCodes.split(",");
		 * 
		 * for (int i = 0; i < localbodyCodeList.length; i++) { if
		 * (localbodyCodeList[i] != null) { int lbTypeCode =
		 * Integer.parseInt(localbodyCodeList[i].toString());
		 * List<LocalbodyListbyState> localbodyList =
		 * localGovtBodyDAO.getdistrictPanchayatList(lbTypeCode, stateCode);
		 * localbodywholeList.addAll(localbodyList); } }
		 */
		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbtypeCode, stateCode);
		localbodywholeList.addAll(localbodyList);
		return localbodywholeList;
	}

	/*@Override
	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryNewWardParentInter(int stateCode, char category, char lblevel) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		String localbodyCodes = localGovtBodyDAO.getLocalBodyTypeCodeNewWardParentInterDistrict(category, lblevel);
		String[] localbodyCodeList = localbodyCodes.split(",");

		for (int i = 0; i < localbodyCodeList.length; i++) {
			if (localbodyCodeList[i] != null) {
				int lbTypeCode = Integer.parseInt(localbodyCodeList[i].toString());
				List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbTypeCode, stateCode);
				localbodywholeList.addAll(localbodyList);
			}
		}
		return localbodywholeList;
	}*/

	public List<LocalbodyListbyState> getPanchayatListbyStateandCategoryforselected(int stateCode, char category, int localBodyCode) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		String localbodyCodes = localGovtBodyDAO.getLocalBodyTypeCode1(category);
		String[] localbodyCodeList = localbodyCodes.split(",");

		for (int i = 0; i < localbodyCodeList.length; i++) {
			if (localbodyCodeList[i] != null) {
				int lbTypeCode = Integer.parseInt(localbodyCodeList[i].toString());
				List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatListforselected(lbTypeCode, stateCode, localBodyCode);
				localbodywholeList.addAll(localbodyList);
			}
		}

		return localbodywholeList;
	}

	public List<LocalbodyListbyState> getPanchayatListbyStateandlbTypeCode(int stateCode, int lbtypeCode) throws Exception {

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbtypeCode, stateCode);

		return localbodyList;
	}

	@Override
	public List<Localbody> getPanchayatListbylocalbody(int stateCode, int lbtypeCode, int offset, int limit) {
		return localGovtBodyDAO.getPanchayatListbylocalbody(lbtypeCode, stateCode, offset, limit);
	}

	@Override
	public List<UlbBean> getPanchayatListbylocalbodyUrban(int districtCode, int lbtypeCode, int offset, int limit) throws Exception {
		return localGovtBodyDAO.getPanchayatListbylocalbodyUrban(districtCode, lbtypeCode, offset, limit);
	}

	@Override
	public List<Localbody> getPanchayatListbylocalbodyVillagepanchayat(int stateCode, int lbtypeCode, String intermediatePanchayatCodes, int offset, int limit) {
		return localGovtBodyDAO.getPanchayatListbylocalbodyVillagepanchayat(lbtypeCode, stateCode, intermediatePanchayatCodes, offset, limit);
	}

	public List<Localbody> getLocalbodylist(int stateCode) {
		return localGovtBodyDAO.getLocalbodylist(stateCode);
	}

	public List<LocalbodyListbyState> getLandRegionWisedistrict(String type, Integer districtcode, Integer lbtype) throws Exception {

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatListbydistrictcode(type, districtcode, lbtype);
		return localbodyList;

	}

	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeD(int stateCode, char lbtypeCode) throws Exception {
		List<LocalbodyforStateWise> traditionalTypeList = localGovtBodyDAO.getTraditioanlTypebyPRITypeD(lbtypeCode, stateCode);
		return traditionalTypeList;
	}

	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeI(int stateCode, char lbtypeCode) throws Exception {
		List<LocalbodyforStateWise> traditionalTypeList = localGovtBodyDAO.getTraditioanlTypebyPRITypeI(lbtypeCode, stateCode);
		return traditionalTypeList;
	}

	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeV(int stateCode, char lbtypeCode) throws Exception {
		List<LocalbodyforStateWise> traditionalTypeList = localGovtBodyDAO.getTraditioanlTypebyPRITypeV(lbtypeCode, stateCode);
		return traditionalTypeList;
	}

	public List<LocalbodyListbyState> getDPListbyStateAndlbTypeCode(int stateCode, int lbtypeCode) throws Exception {
		log.debug("hariom I m Here");
		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(lbtypeCode, stateCode);

		return localbodyList;
	}

	public List<LocalbodyListbyState> getUrbanLocalBodyList(int stateCode, int localbodyTypeCode) throws Exception {

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatList(localbodyTypeCode, stateCode);

		return localbodyList;
	}

	public List<LocalbodyListbyState> getUrbanLocalBodyListChangeCoverage(int stateCode, int localbodyTypeCode, int lbCode) throws Exception {

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getdistrictPanchayatListChangeCoverage(localbodyTypeCode, stateCode, lbCode);

		return localbodyList;
	}

	public List<SearchLocalGovtBody> getLocalGovtBodySearchDetail(String entityName, String entityCode) throws Exception {
		List<SearchLocalGovtBody> SearchLGB = new ArrayList<SearchLocalGovtBody>();
		SearchLGB = localGovtBodyDAO.getLocalGovtBodySearchDetail(entityName, entityCode);
		return SearchLGB;

	}

	public List<SearchLocalGovtBody> getLocalGovtBodySearchDetailByCode(int entityCodeForSearch, String entityCode) throws Exception {
		List<SearchLocalGovtBody> SearchLGB = new ArrayList<SearchLocalGovtBody>();
		SearchLGB = localGovtBodyDAO.getLocalGovtBodySearchDetailByCode(entityCodeForSearch, entityCode);
		return SearchLGB;

	}

	public List<LocalBodyTypeHistory> getLocalBodyTypeHistory(String query) throws Exception {
		return localGovtBodyDAO.getLocalBodyTypeHistory(query);
	}

	// Added by Madan
	/*@Override
	public List<LocalGovtBody> getLocalBodySubList(int localBodyCode, int stateCode) throws Exception {
		return localGovtBodyDAO.getLocalBodySubList(localBodyCode, stateCode);
	}*/

	// Added by Madan
	public List<Localbody> getGovtLocalBodyTypeDetails(int localBodyCode) throws Exception {
		return localGovtBodyDAO.getGovtLocalBodyTypeDetails(localBodyCode);
	}

	// Added by Madan
	public List<LocalBodyDetails> getGovtLocalBodyDetails(int localBodyCode) throws Exception {
		return localGovtBodyDAO.getGovtLocalBodyDetails(localBodyCode);
	}

	/*@Override
	public List<District> getUnmappedLocalbodyDistList(String query) throws Exception {
		localGovtBodyDAO.getUnmappedLocalbodyDistList(query);
		return null;
	}*/

	@Override
	public List<GetLocalBodyTypeCode> getUrbanLocalBodyTypes1() throws Exception {
		// TODO Auto-generated method stub
		char category = 'U';
		return localGovtBodyDAO.getUrbanLocalBodyTypes1(category);
	}

	public List<LocalBodyCoveredArea> getCoveredAreaforULB(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFull(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePart(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String idObject : selectdVillagePancId) {

			coveredAreaListFull = localGovtBodyDAO.getCoveredAreaforULB(Integer.parseInt(idObject));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	/*@Override
	public List<LocalBodyCoveredArea> getCoveredAreaforULBFinal(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinal(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String idObject : selectdVillagePancId) {

			coveredAreaListFull = localGovtBodyDAO.getCoveredAreaforULB(Integer.parseInt(idObject));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}*/

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBFinalforLB(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> coveredAreaList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> coveredAreaListFull = new ArrayList<LocalBodyCoveredAreaLB>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullFinal(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartFinal(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String idObject : selectdVillagePancId) {
			coveredAreaListFull = localGovtBodyDAO.getCoveredAreaforULBforLB(Integer.parseInt(idObject));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBFinalforLBforM(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> coveredAreaList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> coveredAreaListFull = new ArrayList<LocalBodyCoveredAreaLB>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFull(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePart(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String idObject : selectdVillagePancId) {
			coveredAreaListFull = localGovtBodyDAO.getCoveredAreaforULBforLB(Integer.parseInt(idObject));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBFinalforLBforMF(String localBodyCode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> coveredAreaList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> coveredAreaListFull = new ArrayList<LocalBodyCoveredAreaLB>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFull(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePart(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");
		for (String idObject : selectdVillagePancId) {
			coveredAreaListFull = localGovtBodyDAO.getCoveredAreaforULBforLBforMF(Integer.parseInt(idObject));
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;
	}

	/*@Override
	public String getlocalbodyNamebyCode(int localbodyCode) throws Exception {
		return localGovtBodyDAO.getlocalbodyNamebyCode(localbodyCode);
	}*/

	/*@Override
	public List<Localbody> getLocalGovtBodyDistList(int stateCode) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public String modifyLocalBodyForName(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession) {
		String retValue = null;
		List<ChangeLocalBodyName> changeLBName = null;
		try {
			changeLBName = localGovtBodyDAO.modifylocalbodyforname(localGovtBodyForm, request, httpsession);
			Iterator<ChangeLocalBodyName> changeLBNameItr = changeLBName.iterator();
			ChangeLocalBodyName localdata = (ChangeLocalBodyName) changeLBNameItr.next();
			retValue = localdata.getChange_local_body_name_fn();

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return retValue;

	}

	@Override
	public String modifyLocalBodyForcoverageArea(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession, boolean isULBDisttLevel) {
		String retValue = null;
		List<ChangeLocalBodyCoveredArea> changeLBCoverage = null;
		try {
			changeLBCoverage = localGovtBodyDAO.modifylocalbodyforCoveredArea(localGovtBodyForm, request, httpsession, isULBDisttLevel);
			Iterator<ChangeLocalBodyCoveredArea> changeLBCoverageItr = changeLBCoverage.iterator();
			ChangeLocalBodyCoveredArea localdata = (ChangeLocalBodyCoveredArea) changeLBCoverageItr.next();
			retValue = localdata.getChange_coverage_of_local_body_fn();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return retValue;
	}

	@Override
	public String modifyDisturbedLocalBodyForcoverageAreaNameParent(LocalGovtBodyForm localGovtBodyForm, LocalGovtBodyForm localGovtBodyFormChngeName, GovernmentOrderForm govtOrderForm, HttpServletRequest request, HttpSession httpsession,
			Integer userId) {
		String retValue = null;
		List<ChangeCoverageNameParentofDisturbLocalbody> changeLBCoverageNameParentDisLB = null;
		try {
			changeLBCoverageNameParentDisLB = localGovtBodyDAO.modifyDisturbedLocalBodyForcoverageAreaNameParent(localGovtBodyForm, localGovtBodyFormChngeName, govtOrderForm, request, httpsession);
			Iterator<ChangeCoverageNameParentofDisturbLocalbody> changeLBCoverageItr = changeLBCoverageNameParentDisLB.iterator();
			ChangeCoverageNameParentofDisturbLocalbody localdata = (ChangeCoverageNameParentofDisturbLocalbody) changeLBCoverageItr.next();
			retValue = localdata.getChange_coverage_name_parent_of_disturb_localbody();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return retValue;
	}

	@Override
	public boolean saveDataInAttachCoverageLBody(List<AttachedFilesForm> attachedList, HttpSession session, int getordercode) throws Exception {
		return localGovtBodyDAO.saveDataInAttachCoverageLBody(attachedList, session, getordercode);
	}

	/**
	 * @param localGovtBodyForm
	 * @param maxlbcode
	 * @param request
	 * @param httpsession
	 * @param catagery
	 * @param isULBDisttLevel
	 *            (New Added Parameter)
	 * @return
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	@Override
	public boolean correctLocalBodyForcoverageArea(LocalGovtBodyForm localGovtBodyForm, Integer maxlbcode, HttpServletRequest request, HttpSession httpsession, String catagery, boolean isULBDisttLevel) throws Exception {
		boolean returnSucess = localGovtBodyDAO.correctlocalbodyforCoveredArea(localGovtBodyForm, maxlbcode, request, httpsession, catagery, isULBDisttLevel);
		return returnSucess;
	}

	@Override
	public String modifyLocalBodyForUrbanType(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, Integer stateCode,Integer userId) {
		String retValue = null;
		List<ChangeLocalBodyUrbanType> changeLBType = null;
		try {
			List<LocalBodyDetails> localBodyDetails = localGovtBodyForm.getLocalBodyDetails();
			Localbody localBodyDetails1 = new Localbody();

			GovernmentOrder governmentOrder = new GovernmentOrder();
			localBodyDetails1.setLocalBodyCode(localGovtBodyForm.getLocalBodyCode());
			localBodyDetails1.setLocalBodyTypeCode(Integer.parseInt(localGovtBodyForm.getLocalBodyTypeCode()));
			localBodyDetails1.setLocalBodyNameEnglish(localBodyDetails.get(0).getLocalBodyTypeName());
			localGovtBodyForm.setLocalBodyNameEnglish(localBodyDetails.get(0).getLocalBodyNameEnglish());
			governmentOrder.setOrderDate(localGovtBodyForm.getLgd_LBorderDate());
			governmentOrder.setOrderNo(localGovtBodyForm.getLgd_LBorderNo());
			governmentOrder.setEffectiveDate(localGovtBodyForm.getLgd_LBeffectiveDate());
			governmentOrder.setGazPubDate(localGovtBodyForm.getLgd_LBgazPubDate());

			changeLBType = localGovtBodyDAO.modifylocalbodyurbantype(localGovtBodyForm, request, stateCode,userId);

			Iterator<ChangeLocalBodyUrbanType> changeLBTypeItr = changeLBType.iterator();
			ChangeLocalBodyUrbanType localdata = (ChangeLocalBodyUrbanType) changeLBTypeItr.next();
			retValue = localdata.getChange_type_for_local_body_fn();

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return retValue;
	}

	@Override
	public String modifyLocalBodyForPriType(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, Integer userId) {
		String retValue = null;
		List<ChangeLocalBodypriType> changeLBHer = null;
		try {
			Localbody localBodyDetails1 = new Localbody();

			localBodyDetails1.setLocalBodyCode(localGovtBodyForm.getLocalBodyCode());
			// localBodyDetails1.setLocalBodyTypeCode(Integer.parseInt(localGovtBodyForm.getLocalBodyTypeCode()));
			localBodyDetails1.setLocalBodyTypeCode(Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()));
			localBodyDetails1.setParentlblc(localGovtBodyForm.getParentLocalBodyCode());

			changeLBHer = localGovtBodyDAO.modifylocalbodypritype(localGovtBodyForm, request, userId);

			Iterator<ChangeLocalBodypriType> changeLBHerItr = changeLBHer.iterator();
			ChangeLocalBodypriType localdata = (ChangeLocalBodypriType) changeLBHerItr.next();
			retValue = localdata.getChange_local_body_parent_fn();
			return retValue;
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		}
	}

	public boolean modifyLocalBodyForCorrection(LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, HttpServletRequest request, String coodinates, String mapfilename, boolean delFlag) {

		Transaction tx = null;
		Session session = null;
		
		try {
			// Localbody localBodyDetails1 = new Localbody();

			//List<Localbody> localbodyD = null;
			//String retVal = null;
			int retValue = 0;
			int retOrderCode = 0;
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			GovernmentOrder governmentOrder = new GovernmentOrder();
			GovernmentOrderEntityWise governmentOrderEntityWise = null;
			localGovtBodyForm.setLocalBodyCode(localGovtBodyForm.getLocalBodyCode());
			int localBodyCode = localGovtBodyForm.getLocalBodyCode();
			List<LocalBodyDetails> lbdetails = localGovtBodyDAO.getGovtLocalBodyDetails(localBodyCode);
			String latitude = localGovtBodyForm.getLatitude();
			String longitude = localGovtBodyForm.getLongitude();
			String[] arrayLati = latitude.split(",");
			String[] arrayLong = longitude.split(",");
			/*
			 * if (localGovtBodyForm.getOrderCode() != null) {
			 * governmentOrder.setOrderCode(localGovtBodyForm.getOrderCode()); }
			 * else { Integer orderCode =
			 * localGovtBodyDAO.getmaxgovtordercode();
			 * governmentOrder.setOrderCode(orderCode + 1);
			 * governmentOrderEntityWise = new GovernmentOrderEntityWise();
			 * governmentOrderEntityWise
			 * .setEntityCode(localGovtBodyForm.getLocalBodyCode());
			 * governmentOrderEntityWise.setEntityType('G');
			 * governmentOrderEntityWise
			 * .setEntityVersion(lbdetails.get(0).getLocalBodyVersion());
			 * governmentOrderEntityWise.setGovernmentOrder(governmentOrder);
			 * localGovtBodyDAO.saveDataIngovtorderentity(localGovtBodyForm,
			 * governmentOrderEntityWise, session); }
			 */
			governmentOrder.setOrderDate(localGovtBodyForm.getLgd_LBorderDate());
			governmentOrder.setOrderNo(localGovtBodyForm.getLgd_LBorderNo());
			governmentOrder.setGazPubDate(localGovtBodyForm.getLgd_LBgazPubDate());

			governmentOrder.setIssuedBy("GOVERNER");
			governmentOrder.setCreatedby(createdBy);
			governmentOrder.setCreatedon(new Date());
			governmentOrder.setEffectiveDate(new Date());
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby((long) createdBy);

			localGovtBodyForm.setMap_attachment_code(localGovtBodyForm.getMap_attachment_code());

			if (delFlag == true) {
				localGovtBodyDAO.deleteMapAttachementforLB(localGovtBodyForm.getLocalBodyCode(), 'G', session);
			}

			String cord1 = null;
			if (localGovtBodyForm.getLati() != null && localGovtBodyForm.getLati().split(",").length > 0) {
				String[] tempLati = localGovtBodyForm.getLati().split(",");
				String[] tempLongi = localGovtBodyForm.getLongi().split(",");
				cord1 = tempLati[0] + ":" + tempLongi[0] + ",";
				if (tempLati.length > 1) {
					for (int i = 1; i < tempLati.length; i++) {
						cord1 += tempLati[i] + ":" + tempLongi[i] + ",";
					}
				}
				cord1 = cord1.substring(0, cord1.length() - 1);
			}
			// localGovtBodyForm.setContributedBlocks(cord1);
			StringBuffer sb = null;

			if (!localGovtBodyForm.getlatitude().equals("") && !localGovtBodyForm.getLongitude().equals("")) {
				sb = new StringBuffer();
				int count = 1;
				for (int i = 0; i < arrayLati.length; i++) {
					for (int j = 0; j < arrayLong.length; j++) {
						if (i == j) {
							if (count == 1) {
								String temp = arrayLati[i] + ":" + arrayLong[i];
								sb.append(temp);
								count++;
							} else {
								sb.append(",");
								String temp = arrayLati[i] + ":" + arrayLong[i];
								sb.append(temp);
							}
						}
					}
				}
			}
			if (sb != null && cord1 != null) {
				cord1 = cord1 + "," + sb;
			}
			if (sb != null && cord1 == null) {
				cord1 = sb.toString();
			}

			String mapfilaNameCurr = null;
			if (attachedMapList != null) {
				Iterator<AttachedFilesForm> it = attachedMapList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					mapfilaNameCurr = filesForm.getFileName();
				}
			}
			if (mapfilaNameCurr == null) {
				mapfilaNameCurr = mapfilename;
			}

			// localbodyD=localGovtBodyDAO.getLocalBodyNamebyLBID(localGovtBodyForm.getLocalBodyCode());
			// localBodyDetails1 = (Localbody)
			// session.load(Localbody.class,localGovtBodyForm.getLocalBodyCode());

			// localBodyDetails1.setCoordinates(cord1);
			localGovtBodyForm.setCoordinates(cord1);
			if (coodinates != null) {
				if (!coodinates.equals(cord1)) {
					localGovtBodyForm.setWarningflag(false);
				}
			}
			if (mapfilename != null) {
				if (!mapfilename.equals(mapfilaNameCurr)) {
					localGovtBodyForm.setWarningflag(false);
				}
			}

			/*
			 * if(coodinates !=null || mapfilename !=null) {
			 * if(!coodinates.equals(cord1) ||
			 * !mapfilename.equals(mapfilaNameCurr)) {
			 * localGovtBodyForm.setWarningflag(false); } } if(coodinates ==null
			 * || mapfilename ==null) { if(cord1 !=null || mapfilaNameCurr
			 * !=null) { localGovtBodyForm.setWarningflag(false); } }
			 */
			if (mapfilename != null) {
				if (mapfilaNameCurr != mapfilename) {
					if (localGovtBodyForm.getOrderCode() != null) {
						MapAttachment mapattachment = localGovtBodyDAO.saveDataInMapLBGovtOrderCorrect(localGovtBodyForm, governmentOrder, session, attachedList, attachedMapList);
						localGovtBodyForm.setMap_attachment_code((int) mapattachment.getAttachmentId());
					} else if (localGovtBodyForm.getLgd_LBorderNo().trim().isEmpty() || localGovtBodyForm.getLgd_LBorderDate() == null || localGovtBodyForm.getLgd_LBeffectiveDate() == null || localGovtBodyForm.getLgd_LBgazPubDate() == null) {
						retValue = localGovtBodyDAO.saveDataInMapLBGovtOrderCorrectWithMap(localGovtBodyForm, governmentOrder, session, attachedList, attachedMapList);
						localGovtBodyForm.setMap_attachment_code(retValue);
					}
				} else {
					if (localGovtBodyForm.getOrderCode() != null) {
						// boolean
						// val=localGovtBodyDAO.saveDataInMapLBGovtOrderCorrectWithoutMap(localGovtBodyForm,
						// governmentOrder,session, attachedList);
						governmentOrder.setOrderCode(localGovtBodyForm.getOrderCode());
						retOrderCode = localGovtBodyDAO.saveDataGovtOrder(localGovtBodyForm, governmentOrder, session, attachedList);
					} else if (!localGovtBodyForm.getLgd_LBorderNo().trim().isEmpty() || localGovtBodyForm.getLgd_LBorderDate() != null || localGovtBodyForm.getLgd_LBeffectiveDate() != null || localGovtBodyForm.getLgd_LBgazPubDate() != null) {
						retOrderCode = localGovtBodyDAO.saveDataInMapLBGovtOrderCorrectWithoutOrderCodeM(localGovtBodyForm, governmentOrder, session, attachedList);
						governmentOrder.setOrderCode(retOrderCode);
						governmentOrderEntityWise = new GovernmentOrderEntityWise();
						governmentOrderEntityWise.setEntityCode(localGovtBodyForm.getLocalBodyCode());
						governmentOrderEntityWise.setEntityType('G');
						governmentOrderEntityWise.setEntityVersion(lbdetails.get(0).getLocalBodyVersion());
						governmentOrderEntityWise.setGovernmentOrder(governmentOrder);
						localGovtBodyDAO.saveDataIngovtorderentityD(localGovtBodyForm, governmentOrderEntityWise, session);
					}
				}
			} else {
				/*
				 * if(localGovtBodyForm.getOrderCode() !=null) { boolean
				 * val=localGovtBodyDAO
				 * .saveDataInMapLBGovtOrderCorrectWithoutMap(localGovtBodyForm,
				 * governmentOrder,session, attachedList); }
				 */
				// if((!localGovtBodyForm.getLgd_LBorderNo().trim().isEmpty() ||
				// localGovtBodyForm.getLgd_LBorderDate() !=null ||
				// localGovtBodyForm.getLgd_LBeffectiveDate() !=null ||
				// localGovtBodyForm.getLgd_LBgazPubDate() !=null) &&
				// attachedMapList !=null)
				if (localGovtBodyForm.getOrderCode() == null) {
					retOrderCode = localGovtBodyDAO.saveDataGovtOrderwithoutMap(localGovtBodyForm, governmentOrder, session, attachedList);
					governmentOrder.setOrderCode(retOrderCode);
					governmentOrderEntityWise = new GovernmentOrderEntityWise();
					governmentOrderEntityWise.setEntityCode(localGovtBodyForm.getLocalBodyCode());
					governmentOrderEntityWise.setEntityType('G');
					governmentOrderEntityWise.setEntityVersion(lbdetails.get(0).getLocalBodyVersion());
					governmentOrderEntityWise.setGovernmentOrder(governmentOrder);
					localGovtBodyDAO.saveDataIngovtorderentityD(localGovtBodyForm, governmentOrderEntityWise, session);
					if (mapfilaNameCurr != null) {
						retValue = localGovtBodyDAO.saveDataInMapLBGovtOrderCorrectWithMap(localGovtBodyForm, governmentOrder, session, attachedList, attachedMapList);
						localGovtBodyForm.setMap_attachment_code(retValue);
					}
				} else if (localGovtBodyForm.getOrderCode() != null) {
					/*
					 * retOrderCode=localGovtBodyDAO.
					 * saveDataInMapLBGovtOrderCorrectWithoutOrderCodeAndMap
					 * (localGovtBodyForm, governmentOrder,session,
					 * attachedList);
					 * governmentOrder.setOrderCode(retOrderCode);
					 * governmentOrderEntityWise = new
					 * GovernmentOrderEntityWise();
					 * governmentOrderEntityWise.setEntityCode
					 * (localGovtBodyForm.getLocalBodyCode());
					 * governmentOrderEntityWise.setEntityType('G');
					 * governmentOrderEntityWise
					 * .setEntityVersion(lbdetails.get(0
					 * ).getLocalBodyVersion());
					 * governmentOrderEntityWise.setGovernmentOrder
					 * (governmentOrder);
					 * localGovtBodyDAO.saveDataIngovtorderentityD
					 * (localGovtBodyForm, governmentOrderEntityWise, session);
					 */

					governmentOrder.setOrderCode(localGovtBodyForm.getOrderCode());
					retOrderCode = localGovtBodyDAO.saveDataGovtOrder(localGovtBodyForm, governmentOrder, session, attachedList);
					if (mapfilaNameCurr != null) {
						retValue = localGovtBodyDAO.saveDataInMapLBGovtOrderCorrectWithMap(localGovtBodyForm, governmentOrder, session, attachedList, attachedMapList);
						localGovtBodyForm.setMap_attachment_code(retValue);
					}
				} else if (localGovtBodyForm.getLgd_LBorderNo().trim().isEmpty() || localGovtBodyForm.getLgd_LBorderDate() == null || localGovtBodyForm.getLgd_LBeffectiveDate() == null || localGovtBodyForm.getLgd_LBgazPubDate() == null) {
					if (mapfilaNameCurr != null) {
						retValue = localGovtBodyDAO.saveDataInMapLBGovtOrderCorrectWithMap(localGovtBodyForm, governmentOrder, session, attachedList, attachedMapList);
						localGovtBodyForm.setMap_attachment_code(retValue);
					}
				}

			}

			/*
			 * if (localGovtBodyForm.getOrderCode() != null) {
			 * governmentOrder.setOrderCode(localGovtBodyForm.getOrderCode()); }
			 * else { //Integer orderCode =
			 * localGovtBodyDAO.getmaxgovtordercode();
			 * //governmentOrder.setOrderCode(orderCode + 1);
			 * governmentOrder.setOrderCode(retOrderCode); }
			 */
			/*
			 * if(!localGovtBodyForm.getLgd_LBorderNo().trim().isEmpty() ||
			 * localGovtBodyForm.getLgd_LBorderDate() !=null ||
			 * localGovtBodyForm.getLgd_LBeffectiveDate() !=null ||
			 * localGovtBodyForm.getLgd_LBgazPubDate() !=null) {
			 * governmentOrderEntityWise = new GovernmentOrderEntityWise();
			 * governmentOrderEntityWise
			 * .setEntityCode(localGovtBodyForm.getLocalBodyCode());
			 * governmentOrderEntityWise.setEntityType('G');
			 * governmentOrderEntityWise
			 * .setEntityVersion(lbdetails.get(0).getLocalBodyVersion());
			 * governmentOrderEntityWise.setGovernmentOrder(governmentOrder);
			 * localGovtBodyDAO.saveDataIngovtorderentityD(localGovtBodyForm,
			 * governmentOrderEntityWise, session); }
			 */
			if (mapfilename != null) {
				if (mapfilaNameCurr != null) {
					if (mapfilaNameCurr != mapfilename) {
						localGovtBodyDAO.saveLatLongDet(localGovtBodyForm, session);
					} else {
						localGovtBodyDAO.saveLatLongDetMapSame(localGovtBodyForm, session);
					}
				} else {
					localGovtBodyDAO.saveLatLongDetMapSame(localGovtBodyForm, session);
				}

			} else {
				localGovtBodyDAO.saveLatLongDet(localGovtBodyForm, session);
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return false;
	}

	public boolean modifyLocalBodyForCorrectionGenerate(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession) {

		try {
			Localbody localBodyDetails1 = new Localbody();
			GovernmentOrder governmentOrder = new GovernmentOrder();
			GovernmentOrderEntityWise governmentOrderEntityWise = null;
			localBodyDetails1.setLocalBodyCode(localGovtBodyForm.getLocalBodyCode());
			int localBodyCode = localGovtBodyForm.getLocalBodyCode();
			List<LocalBodyDetails> lbdetails = localGovtBodyDAO.getGovtLocalBodyDetails(localBodyCode);

			if (localGovtBodyForm.getOrderCode() != null) {
				governmentOrder.setOrderCode(localGovtBodyForm.getOrderCode());
			} else {
				Integer OrderCode = localGovtBodyDAO.getmaxgovtordercode();
				governmentOrder.setOrderCode(OrderCode + 1);
				governmentOrderEntityWise = new GovernmentOrderEntityWise();
				governmentOrderEntityWise.setEntityCode(localGovtBodyForm.getLocalBodyCode());
				governmentOrderEntityWise.setEntityType('G');
				governmentOrderEntityWise.setEntityVersion(lbdetails.get(0).getLocalBodyVersion());
				governmentOrderEntityWise.setGovernmentOrder(governmentOrder);
				localGovtBodyDAO.saveDataIngovtorderentityGenerate(localGovtBodyForm, governmentOrderEntityWise, httpsession);
			}
			governmentOrder.setOrderDate(localGovtBodyForm.getLgd_LBorderDate());
			governmentOrder.setOrderNo(localGovtBodyForm.getLgd_LBorderNo());
			governmentOrder.setGazPubDate(localGovtBodyForm.getLgd_LBgazPubDate());

			governmentOrder.setIssuedBy("GOVERNER");
			governmentOrder.setCreatedby(createdBy);
			governmentOrder.setCreatedon(new Date());
			governmentOrder.setEffectiveDate(new Date());
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby((long) createdBy);

			localBodyDetails1.setMap_attachment_code(localGovtBodyForm.getMap_attachment_code());
			localBodyDetails1.setCoordinates("lat=" + localGovtBodyForm.getlatitude() + ":" + "long=" + localGovtBodyForm.getlongitude());
			localBodyDetails1.setLocalBodyCode(localGovtBodyForm.getLocalBodyCode());

			if (httpsession.getAttribute("validGovermentGenerateUpload") != null) {
				//boolean insertGovtTableFlag = 
				localGovtBodyDAO.saveDataInAttachGenerateLocalBodyCoverageChange(localGovtBodyForm, request.getSession(), governmentOrder);
			}
			// localGovtBodyDAO.saveDataInMapLB(localGovtBodyForm,
			// governmentOrder, httpsession, attachedList, attachedMapList);
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean modifyLocalBody(LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, HttpServletRequest request) {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		int maxVersionLb = 0;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<LocalBodyDetails> localBodyDetails = localGovtBodyForm.getLocalBodyDetails();

			if (localGovtBodyForm.isLgd_LBchkModifyName() || localGovtBodyForm.isLgd_LBchkModifyParent() || localGovtBodyForm.isLgd_LBchkModifyType() && !localBodyDetails.isEmpty()) {
				if (localGovtBodyForm.isLgd_LBchkModifyName()) {
					query = session.createSQLQuery("Select change_local_body_fn("

					+ localBodyDetails.get(0).getLocalBodyCode() + "," + "'" + localBodyDetails.get(0).getLocalBodyNameEnglish() + "'" + "," + 1000 + "," + "'" + localBodyDetails.get(0).getLocalBodyNameLocal() + "'" + "," + "'"
							+ localBodyDetails.get(0).getAliasNameEnglish() + "'" + "," + "'" + localBodyDetails.get(0).getAlisNameLocal() + "'" + ")");
					List list = query.list();

					if (!list.isEmpty()) {
						maxVersionLb = (Integer) list.get(0);
						session.flush(); // dirty Loading
					}
				}
				if (localGovtBodyForm.isLgd_LBchkModifyParent()) {
					String spitString[] = localGovtBodyForm.getLocalBodyNameEnglishList().split(",");

					if (maxVersionLb > 0)

						query = session.createSQLQuery("Select change_local_body_parent_fn(" + localBodyDetails.get(0).getLocalBodyCode() + "," + spitString[0] + "," + 1000 + "," + maxVersionLb + ")");

					else
						query = session.createSQLQuery("Select change_local_body_parent_fn(" + localBodyDetails.get(0).getLocalBodyCode() + "," + spitString[0] + "," + 1000 + ")");
					List list = query.list();

					if (!list.isEmpty()) {
						maxVersionLb = (Integer) list.get(0);
						session.flush(); // dirty Loading
					}
				}
				if (localGovtBodyForm.isLgd_LBchkModifyType()) {

					if (maxVersionLb > 0)
						query = session.createSQLQuery("Select change_type_of_local_body_fn(" + localBodyDetails.get(0).getLocalBodyCode() + "," + localGovtBodyForm.getLgd_lbTypeCode() + "," + 1000 + "," + maxVersionLb + ")");

					else
						query = session.createSQLQuery("Select change_type_of_local_body_fn(" + localBodyDetails.get(0).getLocalBodyCode() + "," + localGovtBodyForm.getLgd_lbTypeCode() + "," + 1000 + ")");
					List list = query.list();
					if (!list.isEmpty()) {
						maxVersionLb = (Integer) list.get(0);
						session.flush(); // dirty Loading

					}
				}
				if (localGovtBodyForm.isLgd_LBchkModifyCoverage() && !localBodyDetails.isEmpty()) {
					log.debug("Will Be Added later");
				}

				if (maxVersionLb > 0 && !localBodyDetails.isEmpty()) {

					GovernmentOrder govtOrder = null;
					if (localGovtBodyForm.getOrderCode() != null) {
						govtOrder = updateDataInGovtOrder(localGovtBodyForm, session, request);

					} else {
						govtOrder = saveDataInGovtOrder(localGovtBodyForm, session, request);

					}
					if (attachedList != null) {
						saveDataInAttachment(govtOrder, attachedList, session);
					}

					if (attachedMapList != null) {
						govtOrderService.saveDatainMapAttachment(attachedMapList, localBodyDetails.get(0).getLocalBodyCode(), 'G', session);
					}
					//boolean valueReturn = 
					saveDataInGovtOrderEntityWiseforUpdate(govtOrder, localBodyDetails.get(0).getLocalBodyCode(), maxVersionLb, session);

					if (!localGovtBodyForm.getlatitude().isEmpty() && localGovtBodyForm.getlatitude() != null && !localGovtBodyForm.getlongitude().isEmpty() && localGovtBodyForm.getlongitude() != null) {
						LocalbodyPK lbPK = new LocalbodyPK();
						lbPK.setLocalBodyCode(localBodyDetails.get(0).getLocalBodyCode());
						lbPK.setLocalBodyVersion(maxVersionLb);

						Localbody lbNext = (Localbody) session.get(Localbody.class, lbPK);

						MapLocalbody mLB = saveDataInMapLB(localGovtBodyForm, lbNext, session);
						lbNext.setMap_attachment_code(mLB.getMapLocalbodyCode());

						session.update(lbNext);

					}
				}

			}
			tx.commit();
			return true;

		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	private boolean saveDataInGovtOrderEntityWiseforUpdate(GovernmentOrder govtOrder, Integer localBodyCode, Integer localBodyVersion, Session session) {
		GovernmentOrderEntityWise govtOrderEWise = new GovernmentOrderEntityWise();
		try {

			govtOrderEWise.setGovernmentOrder(govtOrder);
			govtOrderEWise.setEntityCode(localBodyCode);// Change Here in New
														// Code Bcz We changed
														// The name
			govtOrderEWise.setEntityVersion(localBodyVersion);
			govtOrderEWise.setEntityType('G');

			localGovtBodyDAO.saveOrderDetailsEntityWise(govtOrderEWise, session);
		} catch (Exception e) {

		}
		return false;
	}

	public void saveDataInAttachment(GovernmentOrder govtOrder, List<AttachedFilesForm> attachedList, Session session) {

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

	public List<LocalGovtBodyWard> getlocalGovtBodyWardList(int localBodyCode) throws Exception {

		List<LocalGovtBodyWard> wardList = new ArrayList<LocalGovtBodyWard>();

		wardList = localGovtBodyDAO.getlocalGovtBodyWardList(localBodyCode);

		return wardList;

	}

	public List<LocalGovtBodyWard> getlocalGovtBodyWardListforpagination(int localBodyCode, int offset, int limit) {

		List<LocalGovtBodyWard> wardList = new ArrayList<LocalGovtBodyWard>();

		wardList = localGovtBodyDAO.getlocalGovtBodyWardListforpagination(localBodyCode, offset, limit);

		return wardList;

	}

	@Override
	public List<LocalbodyWard> getWardByWardCode(int wardCode) throws Exception {

		List<LocalbodyWard> wardObj = new ArrayList<LocalbodyWard>();

		wardObj = localGovtBodyDAO.getwardByWardCode(wardCode);

		return wardObj;

	}

	@Override
	public boolean invalidateWard(int wardCode, int userid) throws Exception {
		return localGovtBodyDAO.invalidateWard(wardCode, userid);

	}

	@Override
	public boolean deleteWard(int wardCode, int userid) throws Exception {
		return localGovtBodyDAO.deleteWard(wardCode, userid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnmappedLBList> getUnmappedLBDistList(char category, int distCode) throws Exception {
		return localGovtBodyDAO.getUnmappedLBDistList(category, distCode);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getLocalBodyList(int localBodyCode, int stateCode) {
		List<Localbody> lbList = new ArrayList<Localbody>();
		Session session = null;

		session = sessionFactory.openSession();
		lbList = session.createQuery("from Localbody l where l.isactive=true and l.localBodyType.localBodyTypeCode=" + localBodyCode + " and l.state.stateCode=" + stateCode).list();
		if (session != null && session.isOpen()) {
			session.close();
		}
		return lbList;
	}

	@Override
	public List<PartillyMappedLRList> getPartillymappedLBDistList(char landRegionType, int stateCode, char category) throws Exception {

		return localGovtBodyDAO.getPartillymappedLBDistList(landRegionType, stateCode, category);
	}

	public List<LocalBodyCoveredArea> getCoveredSubDistrictLocalBody(int localBodyCode) throws Exception {
		return localGovtBodyDAO.getCoveredSubDistrictLocalBody(localBodyCode);
	}

	@Override
	public List<GetLocalBodyTypeCode> getTypeListbylevel(char typeCode, char category) throws Exception {

		return localGovtBodyDAO.getTypeListbylevel(typeCode, category);
	}

	@Override
	public List<LocalbodyWard> getWardDetail(int wardCode) {
		List<LocalbodyWard> localbodyward = null;
		try {
			localbodyward = localGovtBodyDAO.getwardDetail(wardCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return localbodyward;
	}

	@Override
	public List<GetLandRegionNameforWard> getDistrictNameWard(int wardCode) {
		List<GetLandRegionNameforWard> landregionDistrictward = null;
		try {
			landregionDistrictward = localGovtBodyDAO.getDistrictNameWard(wardCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return landregionDistrictward;
	}

	@Override
	public List<GetLandRegionNameforWard> getSubDistrictNameWard(int wardCode) {
		List<GetLandRegionNameforWard> landregionSubDistrictward = null;
		try {
			landregionSubDistrictward = localGovtBodyDAO.getSubDistrictNameWard(wardCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return landregionSubDistrictward;
	}

	@Override
	public List<GetLandRegionNameforWard> getVillageNameWard(int wardCode) {
		List<GetLandRegionNameforWard> landregionVillageward = null;
		try {
			landregionVillageward = localGovtBodyDAO.getVillageNameWard(wardCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return landregionVillageward;
	}

	/*@Override
	public List<CoveredWardLandregion> getCoverageLangRegion(int wardCode) {
		List<CoveredWardLandregion> coveragewardDet = null;
		try {
			coveragewardDet = localGovtBodyDAO.getCoverageLangRegion(wardCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return coveragewardDet;
	}*/

	public boolean createNewWard(LocalGovtBodyForm localGovtBodyForm, int stateCode, HttpServletRequest request, int userid) {
		Session session = null;
		// Transaction tx = null;
		boolean returnType = false;
		// END VARIBALE DECLARATION
		try {
			session = sessionFactory.openSession();
			/* tx = session.beginTransaction(); */
			returnType = saveWardInfo(localGovtBodyForm, session, request, userid);
			/*
			 * if (returnType) { GovernmentOrder govtOrder =
			 * saveDataInGovtOrder( localGovtBodyForm, session, request);
			 * 
			 * saveDataInGovtOrderEntityWise(govtOrder,
			 * Integer.parseInt(localGovtBodyForm .getLb_lgdPanchayatName()),
			 * session);
			 */
			/*
			 * if (returnType) { tx.commit(); }
			 */

		} catch (Exception e) {
			log.debug("Exception" + e);
			/*
			 * if (tx != null) { tx.rollback(); }
			 */
			returnType = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return returnType;
	}

	private boolean saveWardInfo(LocalGovtBodyForm localGovtBodyForm, Session session, HttpServletRequest request, int userid) {
		boolean returnFlag = false;
		try {
			String pCode = null;
			//int version = 0;
			List<LocalBodyDetails> lbDetails;
			String typeCode[] = localGovtBodyForm.getLgd_LBTypeName().split(":");
			String coverageList = null;
			if (typeCode[1].equalsIgnoreCase("D")) {
				pCode = localGovtBodyForm.getLocalBodyNameEnglishList();
				localGovtBodyForm.setWard_landRegiontype('D');
				lbDetails = localGovtBodyDAO.getGovtLocalBodyDetails(Integer.parseInt(pCode));
				if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null) {
					coverageList = localGovtBodyForm.getLgd_LBDistCAreaDestList() + "," + localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() + "," + localGovtBodyForm.getLgd_LBVillageDestLatDCA();
				}
				if (localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null) {
					coverageList = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() + "," + localGovtBodyForm.getLgd_LBVillageDestLatDCA();
				}
				if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null) {
					coverageList = localGovtBodyForm.getLgd_LBDistCAreaDestList() + "," + localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA();
				}
				if (localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null) {
					coverageList = localGovtBodyForm.getLgd_LBVillageDestLatDCA();
				}
				if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null) {
					coverageList = localGovtBodyForm.getLgd_LBDistCAreaDestList();
				}
				if (!lbDetails.isEmpty()) {
					Integer local_body_code = null;
					if (localGovtBodyForm.getLocalBodyNameEnglishList() != null && !localGovtBodyForm.getLocalBodyNameEnglishList().equals("")) {
						local_body_code = Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList());
					}
					returnFlag = saveWardData(lbDetails, localGovtBodyForm, session, request, local_body_code, coverageList, userid);
				}
			} else if (typeCode[1].equalsIgnoreCase("I") && !localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("U")) {
				int slc = localGovtBodyForm.getSlc();
				if (slc == 34)
					pCode = localGovtBodyForm.getLgd_LBInterPSourceList();
				else
					pCode = localGovtBodyForm.getLgd_LBDistListAtInterCA();
				localGovtBodyForm.setWard_landRegiontype('I');
				lbDetails = localGovtBodyDAO.getGovtLocalBodyDetails(Integer.parseInt(pCode));

				if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBVillageDestLatICA() != null) {
					coverageList = localGovtBodyForm.getLgd_LBInterCAreaDestList() + "," + localGovtBodyForm.getLgd_LBVillageDestLatICA();
				}
				if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBVillageDestLatICA() == null) {
					coverageList = localGovtBodyForm.getLgd_LBInterCAreaDestList();
				}
				if (localGovtBodyForm.getLgd_LBInterCAreaDestList() == null && localGovtBodyForm.getLgd_LBVillageDestLatICA() != null) {
					coverageList = localGovtBodyForm.getLgd_LBVillageDestLatICA();
				}
				if (!lbDetails.isEmpty()) {
					Integer local_body_code = null;
					if (localGovtBodyForm.getLgd_LBInterPSourceList() != null && !localGovtBodyForm.getLgd_LBInterPSourceList().equals("")) {
						local_body_code = Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList());
					}

					returnFlag = saveWardData(lbDetails, localGovtBodyForm, session, request, local_body_code, coverageList, userid);
				}
			} else if (typeCode[1].equalsIgnoreCase("V")) {
				/* Modified changed by kirandeep on 05/08/2014 */
				pCode = localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg();

				if (localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg() == null || localGovtBodyForm.getLgd_LBDistListAtVillageCA() == "0") {
					pCode = localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg();
				}

				localGovtBodyForm.setWard_landRegiontype('V');
				lbDetails = localGovtBodyDAO.getGovtLocalBodyDetails(Integer.parseInt(pCode));

				coverageList = localGovtBodyForm.getLgd_LBVillageCAreaDestL();
				if (!lbDetails.isEmpty()) {
					Integer local_body_code = null;
					if (localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg() != null && !localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg().equals("")) {
						local_body_code = Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg());
					}
					returnFlag = saveWardData(lbDetails, localGovtBodyForm, session, request, local_body_code, coverageList, userid);
				}
			}

			if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("U")) {
				pCode = localGovtBodyForm.getLb_lgdPanchayatName();
				localGovtBodyForm.setWard_landRegiontype('U');
				lbDetails = localGovtBodyDAO.getGovtLocalBodyDetails(Integer.parseInt(pCode));

				coverageList = localGovtBodyForm.getLgd_LBSubDistrictList();
				if (!lbDetails.isEmpty()) {
					Integer local_body_code = null;
					if (localGovtBodyForm.getLb_lgdPanchayatName() != null && !localGovtBodyForm.getLb_lgdPanchayatName().equals("")) {
						local_body_code = Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName());
					}
					returnFlag = saveWardData(lbDetails, localGovtBodyForm, session, request, local_body_code, coverageList, userid);
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			returnFlag = false;
		}
		return returnFlag;

	}

	public void fetchSaveCovrgeListUrban(LocalbodyWard lbWard, @SuppressWarnings("rawtypes") List list, LocalGovtBodyForm localGovtBodyForm, char covrgType, char lbType, Session session) {
		try {
			for (Object code : list) {

				String landRegionCode = (String) code;
				Integer landRegionCodeP = Integer.parseInt(landRegionCode);
				int version = subdistrictDAO.getMaxSubDistrictVersion(landRegionCodeP);
				saveCoverdWardDetailsInfo(lbWard, localGovtBodyForm, landRegionCodeP, covrgType, version, lbType, session);

			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

	}

	public void fetchSaveCovrgeListVillage(LocalbodyWard lbWard, @SuppressWarnings("rawtypes") List list, LocalGovtBodyForm localGovtBodyForm, char covrgType, char lbType, Session session) {
		try {
			for (Object code : list) {

				String landRegionCode = (String) code;
				Integer landRegionCodeP = Integer.parseInt(landRegionCode);
				int version = villageDAO.getMaxVillageVersionbyVillageCode(landRegionCodeP);
				saveCoverdWardDetailsInfo(lbWard, localGovtBodyForm, landRegionCodeP, covrgType, version, lbType, session);

			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

	}

	public synchronized boolean saveWardData(List<LocalBodyDetails> lbDetails, LocalGovtBodyForm localGovtBodyForm, Session session, HttpServletRequest request, Integer local_body_code, String coverageList, int userid) {
		//Transaction tx = null;
		boolean returnFlag = false;
		try {
			/*
			 * session = sessionFactory.openSession(); tx =
			 * session.beginTransaction();
			 */

			returnFlag = localGovtBodyDAO.saveLocalBodyWard(lbDetails, localGovtBodyForm, session, request, local_body_code, coverageList, userid);
			// returnFlag = true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			/*
			 * if (tx != null) { tx.rollback(); }
			 */
			returnFlag = false;
		} finally {
			/* tx.commit(); */
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return returnFlag;
	}

	// Change By manish(NIC)
	private void saveCoverdWardDetailsInfo(LocalbodyWard lbWard, LocalGovtBodyForm localGovtBodyForm, Integer landRegionCode, char coverageType, int version, char type, Session session) {

		try {

			CoveredWardLandregion cWardLandregion = new CoveredWardLandregion();
			cWardLandregion.setLocalbodyWard(lbWard);
			cWardLandregion.setIsactive(true);
			cWardLandregion.setLandRegionCode(landRegionCode);
			cWardLandregion.setLandRegionType(type);
			// cWardLandregion.setLandRegionVersion(version);
			cWardLandregion.setCoverageType(coverageType);

			session.save(cWardLandregion);
			if (session.isDirty()) {
				session.flush();
			}

			if (session.contains(cWardLandregion)) {
				session.evict(cWardLandregion);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		}

	}

	/*
	 * @Override public boolean modifyWard(LocalGovtBodyForm localGovtBodyForm,
	 * int wardCode, Session session, HttpServletRequest request) throws
	 * Exception { // TODO ministryUpdate
	 * 
	 * int code = 0;
	 * 
	 * LocalbodyWard localbodywardbean = null; LocalbodyWard
	 * organizationbeanisActive = null; localbodywardbean = new LocalbodyWard();
	 * int wardVersion = 1; wardVersion =
	 * localGovtBodyDAO.getMaxWardVersion(localGovtBodyForm .getWard_code());
	 * Session session1 = null; Transaction tx1 = null; session1 =
	 * sessionFactory.openSession(); tx1 = session1.beginTransaction(); try { if
	 * (localGovtBodyForm.isCorrection() == true) { LocalbodyWardId wardpk = new
	 * LocalbodyWardId( localGovtBodyForm.getWard_code(), wardVersion); //
	 * OrganizationPK orgpk = new //
	 * OrganizationPK(localGovtBodyForm.getWard_code(),orgVersion);
	 * 
	 * localGovtBodyDAO.update(localGovtBodyForm, wardpk, session1);
	 * 
	 * } else if (localGovtBodyForm.isCorrection() == false) { LocalbodyWardId
	 * wardpk2 = new LocalbodyWardId( localGovtBodyForm.getWard_code(),
	 * wardVersion); localGovtBodyDAO.updateisActive(organizationbeanisActive,
	 * wardpk2, session1); if (wardVersion == 0) { wardVersion = wardVersion +
	 * 1; } else { wardVersion = wardVersion + 1; } LocalbodyWardId wardpk = new
	 * LocalbodyWardId( localGovtBodyForm.getWard_code(), wardVersion);
	 * localbodywardbean.setWardNameEnglish(localGovtBodyForm .getWard_Name());
	 * localbodywardbean.setId(wardpk); localbodywardbean.setIsactive(true); //
	 * localbodywardbean.saveWithSession(localbodywardbean, // session1);
	 * 
	 * govtOrderService.saveGovernmentOrder( localbodywardbean.getOrderNo(),
	 * localbodywardbean.getOrderDate(),
	 * localbodywardbean.getOrdereffectiveDate(),
	 * localbodywardbean.getGazPubDate(), "LGT", localbodywardbean.getOrderPath
	 * (),ministryForm.getFilePath(),request);
	 * 
	 * // localGovtTypeDAO.SetGovermentOrderEntity(localBodyTypecode,'G');
	 * 
	 * } tx1.commit(); } catch (Exception e) { // TODO Auto-generated catch
	 * block log.debug("Exception" + e); tx1.rollback(); } finally {
	 * session1.clear(); session1.close(); }
	 * 
	 * return true; }
	 */

	/*@Override
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion) throws Exception {
		// TODO Auto-generated method stub
		return localGovtBodyDAO.GovOrderDetail(entityType, entityCode, entityVersion);

	}*/

	/*@Override
	public int getWardCurrentVersion(int wardCode) throws Exception {
		// TODO Auto-generated method stub
		int version = localGovtBodyDAO.getWardCurrentVersion(wardCode);
		return version;
	}
*/
	@Override
	public List<LBCoverageWard> getLGBforCoveredDistListExWard(int localBodyCode) throws Exception {
		List<LBCoverageWard> coveredAreaList = new ArrayList<LBCoverageWard>();

		coveredAreaList = localGovtBodyDAO.getLGBforCoveredDistListExWard(localBodyCode);

		return coveredAreaList;
	}

	@Override
	public List<LBCoverageWard> getLGBforCoveredSubDistListExWard(int localBodyCode) throws Exception {
		List<LBCoverageWard> coveredAreaList = new ArrayList<LBCoverageWard>();

		coveredAreaList = localGovtBodyDAO.getLGBforCoveredSubDistListExWard(localBodyCode);

		return coveredAreaList;
	}

	@Override
	public List<LBCoverageWard> getLGBforCoveredVillageListExWard(int localBodyCode) throws Exception {
		List<LBCoverageWard> coveredAreaList = new ArrayList<LBCoverageWard>();

		coveredAreaList = localGovtBodyDAO.getLGBforCoveredVillageListExWard(localBodyCode);

		return coveredAreaList;
	}

	@Override
	public List<LBCoverageWard> getLGBforCoveredVillListExWard(int localBodyCode) throws Exception {
		List<LBCoverageWard> coveredAreaList = new ArrayList<LBCoverageWard>();

		coveredAreaList = localGovtBodyDAO.getLGBforCoveredVillListExWard(localBodyCode);

		return coveredAreaList;
	}

	@Override
	public List<LBCoverageWard> getLGBforCoveredVillListforWard(String localBodyCode) throws Exception {
		List<LBCoverageWard> coveredAreaList = new ArrayList<LBCoverageWard>();
		List<LBCoverageWard> coveredAreaListFull = new ArrayList<LBCoverageWard>();

		String listOfIds = null;
		String landRegionName = null;
		LBCoverageWard localbodyBean = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFullWard(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePartWard(localBodyCode);
		}
		String selectdVillagePancId[] = listOfIds.split(",");

		for (String vpIdObj : selectdVillagePancId) {
			coveredAreaListFull = localGovtBodyDAO.getLGBforCoveredVillListExWard(Integer.parseInt(vpIdObj));
			for (LBCoverageWard element : coveredAreaListFull) {
				localbodyBean = new LBCoverageWard();
				//List<LBCoverageWard> tmpList = new ArrayList<LBCoverageWard>();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				coveredAreaList.add(localbodyBean);
			}

		}

		return coveredAreaList;
	}

	@Override
	public List<LBCoverageWard> getLGBforCoveredSubDistUrbanListExWard(int localBodyCode) throws Exception {
		List<LBCoverageWard> coveredAreaList = new ArrayList<LBCoverageWard>();

		coveredAreaList = localGovtBodyDAO.getLGBforCoveredSubDistUrbanListExWard(localBodyCode);

		return coveredAreaList;
	}

	@Override
	public List<LocalbodyforStateWise> getLocalBodyListStat(char localBodyType, int stateCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalBodyTypeWiseDepartment> getLocalBodyTypeWiseDeptList(int stateCode, int lBTypeCode, char levelCode) throws Exception {
		List<LocalBodyTypeWiseDepartment> LBTList = new ArrayList<LocalBodyTypeWiseDepartment>();

		LBTList = localGovtBodyDAO.getLocalBodyTypeWiseDeptList(stateCode, lBTypeCode, levelCode);

		return LBTList;
	}

	@Override
	public List<LocalBodyTypeWiseDepartment> getLocalBodyWiseDeptList(int stateCode, int lBCode, char levelCode) throws Exception {
		List<LocalBodyTypeWiseDepartment> LBList = new ArrayList<LocalBodyTypeWiseDepartment>();

		LBList = localGovtBodyDAO.getLocalBodyWiseDeptList(stateCode, lBCode, levelCode);

		return LBList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetSubTypeList> getLBSubTypeList(int stateCode, int lBTypeCode) {

		Session session = null;
		Query query = null;
		List<GetSubTypeList> getSubList = null;
		// END VARIBALE DECLARATION
		try {

			session = sessionFactory.openSession();
			query = session.getNamedQuery("getsubtypelist").setParameter("stateCode", stateCode).setParameter("localBodyTypeCode", lBTypeCode);
			getSubList = query.list();
			return getSubList;

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getSubList;
	}

	public Village getVillageByCode(int villageCode, Session session) {
		Query query = null;
		Village village = null;
		try {
			query = session.createQuery("from Village Where isactive = true And  villagePK.villageCode=" + villageCode);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				village = (Village) list.get(0);
			}
			return village;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session.contains(village)) {
				session.evict(village);
			}
		}

		return null;

	}

	public Subdistrict getSubdistrictByCode(int subdistrictCode, Session session) {
		Query query = null;
		Subdistrict subdistrict = null;
		try {
			query = session.createQuery("from Subdistrict Where isactive = true And  subdistrictPK.subdistrictCode=" + subdistrictCode);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				subdistrict = (Subdistrict) list.get(0);
			}
			return subdistrict;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session.contains(subdistrict)) {
				session.evict(subdistrict);
			}
		}

		return null;

	}

	public District getDistrictByCode(int districtCode, Session session) {
		Query query = null;
		District district = null;
		try {
			query = session.createQuery("from District Where isactive = true And  districtPK.districtCode=" + districtCode);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				district = (District) list.get(0);
			}
			return district;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session.contains(district)) {
				session.evict(district);
			}
		}

		return null;

	}

	@Override
	public Collection<LocalBodyCoveredArea> getLBCoveredAreaList(int localBodyCode) throws Exception {
		List<LocalBodyCoveredArea> lbCovArea = localGovtBodyDAO.getLocalGovtBodyforCoveredDistrictList(localBodyCode);
		lbCoveredAreaList = new HashMap<Integer, LocalBodyCoveredArea>();
		for (LocalBodyCoveredArea lbCArea : lbCovArea)
			lbCoveredAreaList.put(lbCArea.getLandRegionCode(), lbCArea);
		return lbCoveredAreaList.values();
	}

	@Override
	public List<LocalbodyListbyState> getExistingLBListbyStateandCategory(int stateCode, char category) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		String localbodyCodes = localGovtBodyDAO.getLocalBodyTypeCode1(category);
		String[] localbodyCodeList = localbodyCodes.split(",");

		for (int i = 0; i < localbodyCodeList.length; i++) {
			if (localbodyCodeList[i] != null) {
				int lbTypeCode = Integer.parseInt(localbodyCodeList[i].toString());
				List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getExistingPanchayatList(lbTypeCode, stateCode);
				localbodywholeList.addAll(localbodyList);
			}
		}

		return localbodywholeList;
	}

	@Override
	public List<LocalbodyListbyState> getExistingLBListbyStateandCategoryInter(int stateCode, char category) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		String localbodyCodes = localGovtBodyDAO.getLocalBodyTypeCode1Inter(category);
		String[] localbodyCodeList = localbodyCodes.split(",");

		for (int i = 0; i < localbodyCodeList.length; i++) {
			if (localbodyCodeList[i] != null) {
				int lbTypeCode = Integer.parseInt(localbodyCodeList[i].toString());
				List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getExistingPanchayatList(lbTypeCode, stateCode);
				localbodywholeList.addAll(localbodyList);
			}
		}

		return localbodywholeList;
	}

	/*@Override
	public List<LocalbodyListbyState> getExistingLBListbyStateandCategoryVillage(int stateCode, char category) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		String localbodyCodes = localGovtBodyDAO.getLocalBodyTypeCode1Village(category);
		String[] localbodyCodeList = localbodyCodes.split(",");

		for (int i = 0; i < localbodyCodeList.length; i++) {
			if (localbodyCodeList[i] != null) {
				int lbTypeCode = Integer.parseInt(localbodyCodeList[i].toString());
				List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getExistingPanchayatList(lbTypeCode, stateCode);
				localbodywholeList.addAll(localbodyList);
			}
		}

		return localbodywholeList;
	}*/

	@Override
	public List<LocalbodyUnMappedBody> getUnmappedSubDistOrVillList(char type, String distCode) throws Exception {
		List<LocalbodyUnMappedBody> unMappedDistListWhole = new ArrayList<LocalbodyUnMappedBody>();
		String refineDistcode = null;

		if (type == 'T') {
			// refineDistcode = stringRemoveFullLocalBodyDistrict(distCode);
			if (distCode.contains("PART") && !distCode.contains("FULL")) {
				refineDistcode = stringRemovePartLocalBodyDistrict(distCode);
			}
			if (!distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = stringRemoveFullLocalBodyDistrict(distCode);
			}
			if (distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = distCode.replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
		}

		if (type == 'V') {
			// refineDistcode = stringRemoveFullLocalBodyDistrict(distCode);

			if (distCode.contains("PART") && !distCode.contains("FULL")) {
				refineDistcode = stringRemovePartLocalBodyDistrict(distCode);
			}
			if (!distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = stringRemoveFullLocalBodyDistrict(distCode);
			}
			if (distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = distCode.replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}

		}
		String[] distCodeList = refineDistcode.split(",");

		for (String distCodeValue : distCodeList) {
			int code = Integer.parseInt(distCodeValue);
			List<LocalbodyUnMappedBody> unMappedDistList = new ArrayList<LocalbodyUnMappedBody>();
			unMappedDistList = localGovtBodyDAO.getLocalGovtBodyforUnmappedLocalBodyList(type, code);
			unMappedDistListWhole.addAll(unMappedDistList);
		}

		return unMappedDistListWhole;

	}

	@Override
	public List<LocalbodyUnMappedBodyLevelWise> getUnmappedSubDistOrVillListFinal(char type, String distCode, char level) throws Exception {
		List<LocalbodyUnMappedBodyLevelWise> unMappedDistListWhole = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
		String refineDistcode = null;

		if (type == 'T') {
			// refineDistcode = stringRemoveFullLocalBodyDistrict(distCode);
			if (distCode.contains("PART") && !distCode.contains("FULL")) {
				refineDistcode = stringRemovePartLocalBodyDistrict(distCode);
			}
			if (!distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = stringRemoveFullLocalBodyDistrict(distCode);
			}
			if (distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = distCode.replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}
		}

		if (type == 'V') {
			// refineDistcode = stringRemoveFullLocalBodyDistrict(distCode);

			if (distCode.contains("PART") && !distCode.contains("FULL")) {
				refineDistcode = stringRemovePartLocalBodyDistrict(distCode);
			}
			if (!distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = stringRemoveFullLocalBodyDistrict(distCode);
			}
			if (distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = distCode.replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}

		}
		String[] distCodeList = refineDistcode.split(",");

		for (String distCodeValue : distCodeList) {
			int code = Integer.parseInt(distCodeValue);
			List<LocalbodyUnMappedBodyLevelWise> unMappedDistList = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
			unMappedDistList = localGovtBodyDAO.getLocalGovtBodyforUnmappedLocalBodyListLevelWise(type, code, level);
			unMappedDistListWhole.addAll(unMappedDistList);
		}

		return unMappedDistListWhole;

	}

	@Override
	public List<LocalbodyUnMappedBodyLevelWise> getUnmappedSubDistOrVillIPListFinal(char type, List<String> distCode, char level) throws Exception {
		List<LocalbodyUnMappedBodyLevelWise> unMappedDistListWhole = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
		//String refineDistcode = null;
		/*
		 * if (type == 'T') { if(distCode.contains("PART") &&
		 * !distCode.contains("FULL")) { refineDistcode =
		 * stringRemovePartLocalBody(distCode); } if(!distCode.contains("PART")
		 * && distCode.contains("FULL")) { refineDistcode =
		 * stringRemoveFullLocalBody(distCode); } if(distCode.contains("PART")
		 * && distCode.contains("FULL")) {
		 * refineDistcode=distCode.replaceAll("_PART_D"
		 * ,"").replaceAll("_FULL_D",""); }
		 * 
		 * }
		 */
		/*
		 * String[] distCodeList = refineDistcode.split(",");
		 * 
		 * for (String distCodeValue : distCodeList) { int code =
		 * Integer.parseInt(distCodeValue);
		 */
		List<LocalbodyUnMappedBodyLevelWise> unMappedDistList = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
		unMappedDistList = localGovtBodyDAO.getLocalGovtBodyforUnmappedLocalBodyListLevelWiseFinal(type, distCode, level);
		unMappedDistListWhole.addAll(unMappedDistList);
		// }

		return unMappedDistListWhole;

	}

	@Override
	public List<LocalbodyUnMappedBody> getUnmappedSubDistList(char type, String distCode) throws Exception {
		List<LocalbodyUnMappedBody> unMappedDistListWhole = new ArrayList<LocalbodyUnMappedBody>();
		String refineDistcode = null;
		if (type == 'T') {
			if (distCode.contains("PART") && !distCode.contains("FULL")) {
				refineDistcode = stringRemovePartLocalBody(distCode);
			}
			if (!distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = stringRemoveFullLocalBody(distCode);
			}
			if (distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = distCode.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}

		}

		String[] distCodeList = refineDistcode.split(",");

		for (String distCodeValue : distCodeList) {
			int code = Integer.parseInt(distCodeValue);
			List<LocalbodyUnMappedBody> unMappedDistList = new ArrayList<LocalbodyUnMappedBody>();
			unMappedDistList = localGovtBodyDAO.getLocalGovtBodyforUnmappedLocalBodyList(type, code);
			unMappedDistListWhole.addAll(unMappedDistList);
		}

		return unMappedDistListWhole;

	}

	@Override
	public List<LocalbodyUnMappedBodyLevelWise> getUnmappedSubDistListLevelWise(char type, String distCode, char level) throws Exception {
		List<LocalbodyUnMappedBodyLevelWise> unMappedDistListWhole = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
		String refineDistcode = null;
		if (type == 'T') {
			if (distCode.contains("PART") && !distCode.contains("FULL")) {
				refineDistcode = stringRemovePartLocalBody(distCode);
			}
			if (!distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = stringRemoveFullLocalBody(distCode);
			}
			if (distCode.contains("PART") && distCode.contains("FULL")) {
				refineDistcode = distCode.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}

		}

		String[] distCodeList = refineDistcode.split(",");

		for (String distCodeValue : distCodeList) {
			int code = Integer.parseInt(distCodeValue);
			List<LocalbodyUnMappedBodyLevelWise> unMappedDistList = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
			unMappedDistList = localGovtBodyDAO.getLocalGovtBodyforUnmappedLocalBodyListLevelWise(type, code, level);
			unMappedDistListWhole.addAll(unMappedDistList);
		}

		return unMappedDistListWhole;

	}

	@Override
	public List<LocalbodyUnMappedBodyLevelWise> getUnmappedSubDistListLevelWiseFinal(char type, List<String> distCode, char level) throws Exception {
		List<LocalbodyUnMappedBodyLevelWise> unMappedDistListWhole = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
		//String refineDistcode = null;
		/*
		 * if (type == 'T') { if(distCode.contains("PART") &&
		 * !distCode.contains("FULL")) { refineDistcode =
		 * stringRemovePartLocalBody(distCode); } if(!distCode.contains("PART")
		 * && distCode.contains("FULL")) { refineDistcode =
		 * stringRemoveFullLocalBody(distCode); } if(distCode.contains("PART")
		 * && distCode.contains("FULL")) {
		 * refineDistcode=distCode.replaceAll("_PART_D"
		 * ,"").replaceAll("_FULL_D",""); }
		 * 
		 * }
		 */
		/*
		 * String[] distCodeList = refineDistcode.split(",");
		 * 
		 * for (String distCodeValue : distCodeList) { int code =
		 * Integer.parseInt(distCodeValue);
		 */
		List<LocalbodyUnMappedBodyLevelWise> unMappedDistList = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
		unMappedDistList = localGovtBodyDAO.getLocalGovtBodyforUnmappedLocalBodyListLevelWiseFinal(type, distCode, level);
		unMappedDistListWhole.addAll(unMappedDistList);
		// }

		return unMappedDistListWhole;

	}

	@Override
	public List<PartillyMappedLRList> getPartlyMappedSubDistOrVillList(char type, List<String> districtCode, char category) throws Exception {
		List<PartillyMappedLRList> partMappedDistListWhole = new ArrayList<PartillyMappedLRList>();
		//String refineDistcode = null;

		/*
		 * if(type=='T') { //refineDistcode
		 * =stringRemoveFullLocalBodyDistrict(landregionCode);
		 * if(landregionCode.contains("PART") &&
		 * !landregionCode.contains("FULL")) { refineDistcode =
		 * stringRemovePartLocalBody(landregionCode); }
		 * if(!landregionCode.contains("PART") &&
		 * landregionCode.contains("FULL")) { refineDistcode =
		 * stringRemoveFullLocalBody(landregionCode); }
		 * if(landregionCode.contains("PART") &&
		 * landregionCode.contains("FULL")) {
		 * refineDistcode=landregionCode.replaceAll
		 * ("_PART_D","").replaceAll("_FULL_D",""); } }
		 * 
		 * if (type == 'V') { //refineDistcode =
		 * stringRemoveFullLocalBodyDistrict(landregionCode);
		 * 
		 * if(landregionCode.contains("PART") &&
		 * !landregionCode.contains("FULL")) { refineDistcode =
		 * stringRemovePartLocalBodyDistrict(landregionCode); }
		 * if(!landregionCode.contains("PART") &&
		 * landregionCode.contains("FULL")) { refineDistcode =
		 * stringRemoveFullLocalBodyDistrict(landregionCode); }
		 * if(landregionCode.contains("PART") &&
		 * landregionCode.contains("FULL")) {
		 * refineDistcode=landregionCode.replaceAll
		 * ("_PART_T","").replaceAll("_FULL_T",""); } }
		 */

		// String[] distCodeList = refineDistcode.split(",");

		// for (Integer distCodeValue : districtCode) {
		List<PartillyMappedLRList> partMappedDistList = new ArrayList<PartillyMappedLRList>();
		partMappedDistList = localGovtBodyDAO.getPartillymappedLBDistListFinal(type, districtCode, category);
		partMappedDistListWhole.addAll(partMappedDistList);
		// }
		return partMappedDistListWhole;

	}

	@Override
	public List<PartillyMappedLRListLevelWise> getPartlyMappedSubDistOrVillListFinal(char type, List<String> districtCode, char category, char level) throws Exception {
		List<PartillyMappedLRListLevelWise> partMappedDistListWhole = new ArrayList<PartillyMappedLRListLevelWise>();
		//String refineDistcode = null;

		/*
		 * if(type=='T') { //refineDistcode
		 * =stringRemoveFullLocalBodyDistrict(landregionCode);
		 * if(landregionCode.contains("PART") &&
		 * !landregionCode.contains("FULL")) { refineDistcode =
		 * stringRemovePartLocalBody(landregionCode); }
		 * if(!landregionCode.contains("PART") &&
		 * landregionCode.contains("FULL")) { refineDistcode =
		 * stringRemoveFullLocalBody(landregionCode); }
		 * if(landregionCode.contains("PART") &&
		 * landregionCode.contains("FULL")) {
		 * refineDistcode=landregionCode.replaceAll
		 * ("_PART_D","").replaceAll("_FULL_D",""); } }
		 * 
		 * if (type == 'V') { //refineDistcode =
		 * stringRemoveFullLocalBodyDistrict(landregionCode);
		 * 
		 * if(landregionCode.contains("PART") &&
		 * !landregionCode.contains("FULL")) { refineDistcode =
		 * stringRemovePartLocalBodyDistrict(landregionCode); }
		 * if(!landregionCode.contains("PART") &&
		 * landregionCode.contains("FULL")) { refineDistcode =
		 * stringRemoveFullLocalBodyDistrict(landregionCode); }
		 * if(landregionCode.contains("PART") &&
		 * landregionCode.contains("FULL")) {
		 * refineDistcode=landregionCode.replaceAll
		 * ("_PART_T","").replaceAll("_FULL_T",""); } }
		 */

		// String[] distCodeList = refineDistcode.split(",");

		// for (Integer distCodeValue : districtCode) {
		List<PartillyMappedLRListLevelWise> partMappedDistList = new ArrayList<PartillyMappedLRListLevelWise>();
		partMappedDistList = localGovtBodyDAO.getPartillymappedLBDistListFinalLevelWise(type, districtCode, category, level);
		partMappedDistListWhole.addAll(partMappedDistList);
		// }
		return partMappedDistListWhole;

	}

	/*@Override
	public List<PartillyMappedLRList> getPartUnmappedDistListByStateCode(char localBodyType, int stateCode, char category) throws Exception {

		List<PartillyMappedLRList> lbPartUnMappedDistList = new ArrayList<PartillyMappedLRList>();

		lbPartUnMappedDistList = localGovtBodyDAO.getPartillymappedLBDistList(localBodyType, stateCode, category);

		return lbPartUnMappedDistList;
	}*/

	// Get Unmapped LandRegionList By StateCode
	@Override
	public List<UnLRSWiseList> getUnMapLRStaWiseList(char type, int stateCode) throws Exception {

		List<UnLRSWiseList> uMappedLRList = new ArrayList<UnLRSWiseList>();
		uMappedLRList = localGovtBodyDAO.getUnMapLRStaWiseList(type, stateCode);

		return uMappedLRList;
	}

	@Override
	public List<UnLRDistrictWiseList> getUnMapLRDistWiseList(char type, int distictCode) throws Exception {
		List<UnLRDistrictWiseList> uMappedLRList = new ArrayList<UnLRDistrictWiseList>();
		uMappedLRList = localGovtBodyDAO.getUnMapLRDistWiseList(type, distictCode);

		return uMappedLRList;
	}

	// Get PartlyMapped LandRegionList By StateCode
	@Override
	public List<PartillyMappedLRList> getPartlyMapLRStaWiseList(char type, int stateCode) throws Exception {

		List<PartillyMappedLRList> uMappedLRList = new ArrayList<PartillyMappedLRList>();
		uMappedLRList = localGovtBodyDAO.getPartlyMapLRStaWiseList(type, stateCode);

		return uMappedLRList;
	}

	@Override
	public List<LocalBodyCoveredArea> getLocalGovtBodySubDistrictListFinal(String districtcode, String coveragecode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> subdistrictList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> subdistrictListFull = new ArrayList<LocalBodyCoveredArea>();
		String listOfIds1 = null;
		String listOfIds = null;
		try {

			if (coveragecode.contains(",")) {
				listOfIds = stringRemoveFullFinal(coveragecode);
			} else if (coveragecode.contains("PART")) {
				listOfIds = stringRemovePartFinal(coveragecode);
			}
			if (districtcode.contains("PART") && !districtcode.contains("FULL")) {
				listOfIds1 = stringRemovePartLocalBody(districtcode);
			}
			if (!districtcode.contains("PART") && districtcode.contains("FULL")) {
				listOfIds1 = stringRemoveFullLocalBody(districtcode);
			}
			if (districtcode.contains("PART") && districtcode.contains("FULL")) {
				listOfIds1 = districtcode.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}
			if (listOfIds1.endsWith(",")) {
				listOfIds1 = listOfIds1.substring(0, listOfIds1.length() - 1);
				log.debug(listOfIds1);
			}

			String selectdcoverage[] = listOfIds.split(",");
			String selectdDistPancId[] = listOfIds1.split(",");
			for (String dpIdObj : selectdcoverage) {
				for (String dpIdObj1 : selectdDistPancId) {
					subdistrictListFull = localGovtBodyDAO.getSubDistrictListFinal(Integer.parseInt(dpIdObj), Integer.parseInt(dpIdObj1));
					subdistrictList.addAll(subdistrictListFull);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictList;
	}

	@Override
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodySubDistrictListFinalChangeCov(String districtcode, String coveragecode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> subdistrictList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> subdistrictListFull = new ArrayList<LocalBodyCoveredAreaLB>();
		// List<String> listValTemp= new ArrayList<String>();
		String listOfIds1 = null;
		String listOfIds = null;
		try {
			if (coveragecode.contains(",")) {
				listOfIds = stringRemoveFullFinal(coveragecode);
			} else if (coveragecode.contains("PART")) {
				listOfIds = stringRemovePartFinal(coveragecode);
			}
			if (districtcode.contains("PART") && !districtcode.contains("FULL")) {
				listOfIds1 = stringRemovePartLocalBody(districtcode);
			}
			if (!districtcode.contains("PART") && districtcode.contains("FULL")) {
				listOfIds1 = stringRemoveFullLocalBody(districtcode);
			}
			if (districtcode.contains("PART") && districtcode.contains("FULL")) {
				listOfIds1 = districtcode.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}
			/*
			 * if(listOfIds1.contains(":")) { String
			 * listOfIdstemp[]=listOfIds1.split(":"); for(String dpIdObjtemp :
			 * listOfIdstemp) { listValTemp.addAll(dpIdObjtemp.get); } }
			 */
			if (listOfIds1.endsWith(",")) {
				listOfIds1 = listOfIds1.substring(0, listOfIds1.length() - 1);
				log.debug(listOfIds1);
			}

			String selectdcoverage[] = listOfIds.split(",");
			String selectdDistPancId[] = listOfIds1.split(",");
			for (String dpIdObj : selectdcoverage) {
				for (String dpIdObj1 : selectdDistPancId) {
					String selectdDistPancIdtemp[] = dpIdObj1.split(":");
					for (String dpIdObj2 : selectdDistPancIdtemp) {
						subdistrictListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredSubDistrictListFinalforLBbyDIstCode(Integer.parseInt(dpIdObj), Integer.parseInt(dpIdObj2));
						subdistrictList.addAll(subdistrictListFull);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictList;
	}

	@Override
	public List<CheckAuthorization> getLocalGovtBodySubDistrictListChangeCoverage(String districtcode) throws Exception {
		List<CheckAuthorization> subdistrictList = new ArrayList<CheckAuthorization>();
		List<CheckAuthorization> subdistrictListFull = new ArrayList<CheckAuthorization>();
		String listOfIds, listOfIdsF = null;

		StringBuffer valueBufferDistMapped = new StringBuffer();
		try {

			if (districtcode.contains(",")) {
				String[] temp = districtcode.split(",");
				for (int i = 0; i < temp.length; i++) {
					String[] temp2 = temp[i].split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_D") || temp2[j].contains("_FULL_D")) {
							valueBufferDistMapped.append(temp2[j]);
							valueBufferDistMapped.append(",");
						}
					}
				}
			}

			listOfIdsF = valueBufferDistMapped.toString();

			listOfIds = listOfIdsF.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");

			/*
			 * if(districtcode.contains("PART") &&
			 * !districtcode.contains("FULL")) { listOfIds =
			 * stringRemovePartLocalBodyCoverage(districtcode); }
			 * if(!districtcode.contains("PART") &&
			 * districtcode.contains("FULL")) { listOfIds =
			 * stringRemoveFullLocalBodyCoverage(districtcode); }
			 * if(districtcode.contains("PART") &&
			 * districtcode.contains("FULL")) {
			 * listOfIds=districtcode.replaceAll
			 * ("_PART_D","").replaceAll("_FULL_D",""); }
			 */

			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictListFull = localGovtBodyDAO.getSubDistrictList(Integer.parseInt(dpIdObj));
				subdistrictList.addAll(subdistrictListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictList;
	}

	@Override
	public List<CheckAuthorization> getLocalGovtBodySubDistrictList(String districtcode) throws Exception {
		List<CheckAuthorization> subdistrictList = new ArrayList<CheckAuthorization>();
		List<CheckAuthorization> subdistrictListFull = new ArrayList<CheckAuthorization>();
		String listOfIds = null;
		try {
			if (districtcode.contains("PART") && !districtcode.contains("FULL")) {
				listOfIds = stringRemovePartLocalBody(districtcode);
			}
			if (!districtcode.contains("PART") && districtcode.contains("FULL")) {
				listOfIds = stringRemoveFullLocalBody(districtcode);
			}
			if (districtcode.contains("PART") && districtcode.contains("FULL")) {
				listOfIds = districtcode.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}

			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictListFull = localGovtBodyDAO.getSubDistrictList(Integer.parseInt(dpIdObj));
				subdistrictList.addAll(subdistrictListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictList;
	}

	@Override
	public List<CheckAuthorization> getVillageListforDCA(String villagecode) throws Exception {
		List<CheckAuthorization> villageList = new ArrayList<CheckAuthorization>();
		List<CheckAuthorization> villageListFull = new ArrayList<CheckAuthorization>();
		String listOfIds, listOfIdsF = null;
		StringBuffer valueBufferVillageMapped = new StringBuffer();
		try {
			if (villagecode.contains(",")) {
				String[] temp = villagecode.split(",");
				for (int i = 0; i < temp.length; i++) {
					String[] temp2 = temp[i].split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
							valueBufferVillageMapped.append(temp2[j]);
							valueBufferVillageMapped.append(",");
						}
					}
				}
			}

			listOfIdsF = valueBufferVillageMapped.toString();

			listOfIds = listOfIdsF.replaceAll("_PART_T", "").replaceAll("_FULL_T", "");

			/*
			 * if(villagecode.contains("PART") && !villagecode.contains("FULL"))
			 * { listOfIds = stringRemovePartLocalBodySubDist(villagecode); }
			 * if(!villagecode.contains("PART") && villagecode.contains("FULL"))
			 * { listOfIds = stringRemoveFullLocalBodySubDist(villagecode); }
			 * if(villagecode.contains("PART") && villagecode.contains("FULL"))
			 * {
			 * listOfIds=villagecode.replaceAll("_PART_T","").replaceAll("_FULL_T"
			 * ,""); }
			 */

			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				villageListFull = localGovtBodyDAO.getvillageList(Integer.parseInt(dpIdObj));
				villageList.addAll(villageListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return villageList;
	}

	@Override
	public List<LocalBodyCoveredAreaLB> getVillageListforDCAFinal(String subdistrictcode, String coveragecode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredAreaLB> villageList = new ArrayList<LocalBodyCoveredAreaLB>();
		List<LocalBodyCoveredAreaLB> villageListFull = new ArrayList<LocalBodyCoveredAreaLB>();
		String listOfIds1 = null;
		String listOfIds = null;
		try {

			if (coveragecode.contains(",")) {
				listOfIds = stringRemoveFullFinal(coveragecode);
			} else if (coveragecode.contains("PART")) {
				listOfIds = stringRemovePartFinal(coveragecode);
			}
			if (subdistrictcode.contains("PART") && !subdistrictcode.contains("FULL")) {
				listOfIds1 = stringRemovePartLocalBodySubDist(subdistrictcode);
			}
			if (!subdistrictcode.contains("PART") && subdistrictcode.contains("FULL")) {
				listOfIds1 = stringRemoveFullLocalBodySubDist(subdistrictcode);
			}
			if (subdistrictcode.contains("PART") && subdistrictcode.contains("FULL")) {
				listOfIds1 = subdistrictcode.replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}

			if (listOfIds1.endsWith(",")) {
				listOfIds1 = listOfIds1.substring(0, listOfIds1.length() - 1);
				log.debug(listOfIds1);
			}

			String selectdcoverage[] = listOfIds.split(",");
			String selectdSubDistPancId[] = listOfIds1.split(",");
			for (String dpIdObj : selectdcoverage) {
				for (String dpIdObj1 : selectdSubDistPancId) {
					String selectdSubDistPancIdtemp[] = dpIdObj1.split(":");
					for (String dpIdObj3 : selectdSubDistPancIdtemp) {
						villageListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredVillageListFinalforLBbySubDIstCode(Integer.parseInt(dpIdObj), Integer.parseInt(dpIdObj3));
						villageList.addAll(villageListFull);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return villageList;
	}

	/*@Override
	public List<LocalBodyCoveredArea> getVillageListforDCACovChngFinal(String subdistrictcode, String coveragecode) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> villageList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> villageListFull = new ArrayList<LocalBodyCoveredArea>();
		String listOfIds1 = null;
		String listOfIds = null;
		try {

			if (coveragecode.contains(",")) {
				listOfIds = stringRemoveFullFinal(coveragecode);
			} else if (coveragecode.contains("PART")) {
				listOfIds = stringRemovePartFinal(coveragecode);
			}
			if (subdistrictcode.contains("PART") && !subdistrictcode.contains("FULL")) {
				listOfIds1 = stringRemovePartLocalBodySubDist(subdistrictcode);
			}
			if (!subdistrictcode.contains("PART") && subdistrictcode.contains("FULL")) {
				listOfIds1 = stringRemoveFullLocalBodySubDist(subdistrictcode);
			}
			if (subdistrictcode.contains("PART") && subdistrictcode.contains("FULL")) {
				listOfIds1 = subdistrictcode.replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
			}

			if (listOfIds1.endsWith(",")) {
				listOfIds1 = listOfIds1.substring(0, listOfIds1.length() - 1);
				log.debug(listOfIds1);
			}

			String selectdcoverage[] = listOfIds.split(",");
			String selectdSubDistPancId[] = listOfIds1.split(",");
			for (String dpIdObj : selectdcoverage) {
				for (String dpIdObj1 : selectdSubDistPancId) {
					String selectdSubDistPancIdtemp[] = dpIdObj1.split(":");
					for (String dpIdObj2 : selectdSubDistPancIdtemp) {
						villageListFull = localGovtBodyDAO.getvillageListFinal(Integer.parseInt(dpIdObj), Integer.parseInt(dpIdObj2));
						villageList.addAll(villageListFull);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return villageList;
	}
*/
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationMapLocalbody> getMapD(int stateCode, char level) {
		Session session = null;
		Query query = null;
		Query queryFinal = null;
		List<ConfigurationMapLocalbody> configMap = null;
		List<GetLocalGovtSetup> getSubList = null;

		try {
			session = sessionFactory.openSession();
			if (level == 'D') {
				query = session.getNamedQuery("getLocalGovSetupDPanchayat");
				query.setParameter("stateCode", stateCode);
				query.setParameter("category", 'P');
			}
			if (level == 'I') {
				query = session.getNamedQuery("getLocalGovSetupIPanchayat");
				query.setParameter("stateCode", stateCode);
				query.setParameter("category", 'P');
			}
			if (level == 'V') {
				query = session.getNamedQuery("getLocalGovSetupVPanchayat");
				query.setParameter("stateCode", stateCode);
				query.setParameter("category", 'P');
			}
			getSubList = query.list();
			int tiersetupcode = 0;
			Iterator<GetLocalGovtSetup> sublistItr = getSubList.iterator();
			while (sublistItr.hasNext()) {
				// tiersetcode.add(sublistItr.next().getTierSetupCode());
				tiersetupcode = sublistItr.next().getTierSetupCode();
			}
			queryFinal = session.createQuery("from ConfigurationMapLocalbody where tierSetupCode = ? and isactive='true'");
			queryFinal.setInteger(0, tiersetupcode);
			configMap = queryFinal.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return configMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationMapLocalbody> getMapDLBody(int stateCode, char level, char lbType) {
		Session session = null;
		Query query = null;
		Query queryFinal = null;
		List<ConfigurationMapLocalbody> configMap = null;
		List<GetLocalGovtSetup> getSubList = null;

		try {
			session = sessionFactory.openSession();
			if (level == 'D') {
				query = session.getNamedQuery("getLocalGovSetupDPanchayat");
				query.setParameter("stateCode", stateCode);
				query.setParameter("category", lbType);
			}
			if (level == 'I') {
				query = session.getNamedQuery("getLocalGovSetupIPanchayat");
				query.setParameter("stateCode", stateCode);
				query.setParameter("category", lbType);
			}
			if (level == 'V') {
				query = session.getNamedQuery("getLocalGovSetupVPanchayat");
				query.setParameter("stateCode", stateCode);
				query.setParameter("category", lbType);
			}
			getSubList = query.list();
			int tiersetupcode = 0;
			Iterator<GetLocalGovtSetup> sublistItr = getSubList.iterator();
			while (sublistItr.hasNext()) {
				tiersetupcode = sublistItr.next().getTierSetupCode();
			}
			queryFinal = session.createQuery("from ConfigurationMapLocalbody where tierSetupCode = ? and isactive='true'");
			queryFinal.setInteger(0, tiersetupcode);
			configMap = queryFinal.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return configMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationMapLocalbody> getUrbanMapD(int stateCode, int lbtypecd) {
		Session session = null;
		Query query = null;
		Query queryFinal = null;
		List<ConfigurationMapLocalbody> configMap = null;
		List<GetLocalGovtSetup> getSubList = null;

		try {
			session = sessionFactory.openSession();
			if (lbtypecd != 0) {
				query = session.getNamedQuery("getULBLocalGovtSetup");
				query.setParameter("stateCode", stateCode);
				query.setParameter("category", 'U');
				query.setParameter("lbtcode", lbtypecd);
			}
			getSubList = query.list();
			int tiersetupcode = 0;
			Iterator<GetLocalGovtSetup> sublistItr = getSubList.iterator();
			while (sublistItr.hasNext()) {
				// tiersetcode.add(sublistItr.next().getTierSetupCode());
				tiersetupcode = sublistItr.next().getTierSetupCode();
			}
			queryFinal = session.createQuery("from ConfigurationMapLocalbody where tierSetupCode = ? and isactive='true'");
			queryFinal.setInteger(0, tiersetupcode);
			configMap = queryFinal.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return configMap;
	}

	@Override
	public boolean saveDataInMapLocalBody(LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception {
		return localGovtBodyDAO.saveDataInMapLocalBody(localGovtBodyForm, attachedList, session);
	}

	@Override
	public List<LocalGovtBody> viewlocalbodyDetails(Integer localBodyCode) {
		return localGovtBodyDAO.viewLocalbodyDetails(localBodyCode);
	}

	/*@Override
	public GovernmentOrder viewgovernmetorderDetails(Integer orderCode) {
		return localGovtBodyDAO.viewgovernmetorderDetails(orderCode);
	}*/

	@Override
	public boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception {
		return localGovtBodyDAO.saveDataInAttach(governmentOrderForm, localGovtBodyForm, attachedList, session);
	}

	@Override
	public boolean saveDataInAttachGenerateLocalBody(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm localGovtBodyForm, HttpSession session) throws Exception {
		return localGovtBodyDAO.saveDataInAttachGenerateLocalBody(governmentOrderForm, localGovtBodyForm, session);
	}

	@Override
	public boolean saveDataInAttachGenerateLocalBodyUrbanTypeModify(LocalGovtBodyForm localGovtBodyForm, HttpSession session, int getordercode) throws Exception {
		return localGovtBodyDAO.saveDataInAttachGenerateLocalBodyUrbanTypeModify(localGovtBodyForm, session, getordercode);
	}

	@Override
	public List<GetLandRegionWise> getLandRegionWise(Integer localbodytypecode, char code, Integer districtcode, String lbtype) throws Exception {
		log.debug("DISTRICT ID::: " + districtcode + "LB TYPE::" + lbtype);
		return localGovtBodyDAO.getLandRegionWise(localbodytypecode, code, districtcode, lbtype);
	}

	@Override
	public List<UlbBean> getLandRegionWiseUrban(Integer localbodytypecode, Integer districtcode) throws Exception {
		log.debug("DISTRICT ID::: " + districtcode);
		return localGovtBodyDAO.getLandRegionWiseUrban(localbodytypecode, districtcode);
	}

	@Override
	public List<GetLandRegionWise> getLandRegionWise(Character localbodytypecode, int districtcode, String lbtype) throws Exception {
		log.debug("DISTRICT ID::: " + districtcode + "LB TYPE::" + lbtype);
		return localGovtBodyDAO.getLandRegionWise(localbodytypecode, districtcode, lbtype);
	}

	/*
	 * @Override public List<GetLandRegionWise> getLandRegionWiseInter(Integer
	 * localbodytypecode,Integer districtcode) throws Exception {
	 * System.out.println("DISTRICT ID::: "+districtcode); return
	 * localGovtBodyDAO.getLandRegionWiseInter(localbodytypecode,districtcode);
	 * }
	 * 
	 * @Override public List<GetLandRegionWise> getLandRegionWiseVill(Integer
	 * localbodytypecode,Integer districtcode) throws Exception {
	 * System.out.println("DISTRICT ID::: "+districtcode); return
	 * localGovtBodyDAO.getLandRegionWiseVill(localbodytypecode,districtcode); }
	 */

	@Override
	public List<ViewLocalBodyLandRegion> viewLandRegionDistrictName(int localBodyCode) {

		return localGovtBodyDAO.viewLandRegionDistrictName(localBodyCode);
	}

	@Override
	public List<ViewLocalBodyLandRegion> viewCoveragearea(int localBodyCode) {

		return localGovtBodyDAO.viewCoveragearea(localBodyCode);
	}

	@Override
	public List<ViewLocalBodyLandRegion> viewLandRegionsubDistrictName(int localBodyCode) {
		return localGovtBodyDAO.viewLandRegionsubDistrictName(localBodyCode);
	}

	@Override
	public List<ViewLocalBodyLandRegion> viewLandRegionvillageName(int localBodyCode) {
		return localGovtBodyDAO.viewLandRegionvillageName(localBodyCode);
	}

	@Override
	public List<ViewLocalBodyLandRegion> viewLandRegionvillageNameF(int localBodyCode) {
		return localGovtBodyDAO.viewLandRegionvillageNameF(localBodyCode);
	}

	@Override
	public List<ViewLandRegionDisturbedlist> viewGpdisturbedlist(int localBodyCode) {
		return localGovtBodyDAO.viewGpdisturbedlist(localBodyCode);
	}

	@Override
	public List<LocalbodyWard> getWardListFromContributingMunicipality(String localBodyCode) throws Exception {
		// return
		// localGovtBodyDAO.getWardListFromContributingMunicipality(localBodyCode);
		List<LocalbodyWard> wardList = new ArrayList<LocalbodyWard>();
		List<LocalbodyWard> wardListFull = new ArrayList<LocalbodyWard>();

		String listOfIds = null;
		if (localBodyCode.contains(",")) {
			listOfIds = stringRemoveFull(localBodyCode);
		} else if (localBodyCode.contains("PART")) {
			listOfIds = stringRemovePart(localBodyCode);
		}
		String selectedContMunicipalityId[] = listOfIds.split(",");
		for (String dpIdObj : selectedContMunicipalityId) {
			// localGovtBodyDAO.(localBodyCode);
			wardListFull = localGovtBodyDAO.getWardListFromContributingMunicipality(Integer.parseInt(dpIdObj.toString()));

			wardList.addAll(wardListFull);
		}

		return wardList;

	}

	/*
	 * } public List<ParentWiseLocalBodiesWithoutChildCount>
	 * getIntermediatePanchayatbyParentCodeWithoutChild( String localBodyCode)
	 * throws NumberFormatException, Exception {
	 * 
	 * List<ParentWiseLocalBodiesWithoutChildCount> coveredAreaList = new
	 * ArrayList<ParentWiseLocalBodiesWithoutChildCount>();
	 * List<ParentWiseLocalBodiesWithoutChildCount> coveredAreaListFull = new
	 * ArrayList<ParentWiseLocalBodiesWithoutChildCount>();
	 * 
	 * String listOfIds = null; if (localBodyCode.contains(",")) listOfIds =
	 * stringRemoveFull(localBodyCode); else if (localBodyCode.contains("PART"))
	 * listOfIds = stringRemovePart(localBodyCode);
	 * 
	 * String selectdDistrictPancId[] = listOfIds.split(","); for(String dpIdObj
	 * : selectdDistrictPancId) {
	 * 
	 * coveredAreaListFull = localGovtBodyDAO
	 * .getchildlocalbodiesByParentcodeWithoutChild(Integer
	 * .parseInt(dpIdObj.toString()));
	 * 
	 * coveredAreaList.addAll(coveredAreaListFull); }
	 * 
	 * return coveredAreaList;
	 * 
	 * }
	 */

	// Tanuj

	@Override
	public String getCategoryFromLocalBodyTypeCode(int localBodyType) throws Exception {
		log.debug("Inside the serviceIMPL class");
		Session session = null;
		String type = "";
		try {
			session = sessionFactory.openSession();
			type = localGovtBodyDAO.getCategoryFromLocalBodyTypeCode(localBodyType, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return type;
	}

	public List<LocalbodyforStateWiseFinal> getLocalBodyListStateCategoryWise(Integer localBodyType, Integer stateCode, Integer plblc, Integer lbCode, String lbName) throws Exception {
		return localGovtBodyDAO.getLocalBodyListStateCategoryWise(localBodyType, stateCode, plblc, lbCode, lbName);
	}

	public List<ParentWiseLBList> getParentWiseLBList(int localBodyCode) throws Exception {
		return localGovtBodyDAO.getParentWiseLBList(localBodyCode);
	}

	public int getLBTypeCodeFromLBCode(int localBodyCode) throws Exception {
		return localGovtBodyDAO.getLBTypeCodeFromLBCode(localBodyCode);
	}

	public List<Localbody> getLocalGovSetupWiseLocalbodyList(int localBodyCode) throws Exception {
		return localGovtBodyDAO.getLocalGovSetupWiseLocalbodyList(localBodyCode);
	}

	public List<Localbody> getLocalGovSetupWiseLocalbodyListByName(String localBodyName) throws Exception {
		return localGovtBodyDAO.getLocalGovSetupWiseLocalbodyListByName(localBodyName);
	}

	@Override
	public List<LocalGovtBodyDataForm> getLocalBodyDetailsModify(int localBodyCode) throws Exception {
		LocalGovtBodyDataForm localBodyDataForm = new LocalGovtBodyDataForm();
		//List<Localbody> listLocalBodyDetail = localGovtBodyDAO.getLocalBodyDetailsModify(localBodyCode);
		LocalBodyForm lbForm = this.localBodyService.getLocalBodyFormObject(localBodyCode);
		/* Added by sushil on 14-03-2013 */
		List<LGBodyCoveredAreaDTO> coveredAreaList = localGovtBodyDAO.getLGBodyCoveredAreaDetail(localBodyCode);
		localBodyDataForm.setCoveredAreaDetailList(coveredAreaList);
		/* end */
		int localBodyversion = localGovtBodyDAO.getMaxLocalBodyVersionbyCode(localBodyCode);
		// entity type 'G' for local body
		List<GetGovernmentOrderDetail> govtOrderValue = districtDAO.GovOrderDetail('G', localBodyCode, localBodyversion, lbForm.getMinorVersion());
		// Commented for later use by Tanuj

		/*
		 * List<Headquarters>
		 * listHeadquarter=localGovtBodyDAO.getHeadquarterModify
		 * ('L',localBodyCode, localBodyversion); Iterator<Headquarters> itrat =
		 * listHeadquarter.iterator(); while (itrat.hasNext()) { Headquarters
		 * element = (Headquarters) itrat.next();
		 * localBodyDataForm.setHeadquarterCode(element.getHeadquarterCode());
		 * localBodyDataForm
		 * .setHeadquarterVersion(element.getHeadquarterVersion());
		 * localBodyDataForm
		 * .setHeadquarterName(element.getHeadquarterNameEnglish());
		 * 
		 * }
		 */
		Iterator<GetGovernmentOrderDetail> itrGovt = govtOrderValue.iterator();
		while (itrGovt.hasNext()) {
			GetGovernmentOrderDetail governmentOrder = (GetGovernmentOrderDetail) itrGovt.next();

			governmentOrder.getOrderNo();
			governmentOrder.getOrderDate();
			governmentOrder.getEffectiveDate();
			governmentOrder.getGazPubDate();
			governmentOrder.getOrderPath();

			localBodyDataForm.setOrderCodecr(governmentOrder.getOrderCode());
			if (governmentOrder.getOrderNo() == null) {
				localBodyDataForm.setOrderNocr(governmentOrder.getOrderNo());
			} else {
				localBodyDataForm.setOrderNocr(governmentOrder.getOrderNo().trim());
			}

			localBodyDataForm.setOrderDatecr(governmentOrder.getOrderDate());
			localBodyDataForm.setOrdereffectiveDatecr(governmentOrder.getEffectiveDate());
			localBodyDataForm.setGazPubDatecr(governmentOrder.getGazPubDate());
			localBodyDataForm.setOrderPathcr(governmentOrder.getOrderPath());
		}

		
		List<LocalGovtBodyDataForm> listLocalBodyDetails = new ArrayList<LocalGovtBodyDataForm>();
		
	
				String cordinate = "";
				
				if (lbForm.getLocalBodyAliasEnglish() == null) {
					localBodyDataForm.setAliasEnglish(lbForm.getLocalBodyAliasEnglish());
				} else {
					localBodyDataForm.setAliasEnglish(lbForm.getLocalBodyAliasEnglish().trim());
				}
				if (lbForm.getLocalBodyAliasLocal() == null) {
					localBodyDataForm.setAliasLocal(lbForm.getLocalBodyAliasLocal());
				} else {
					localBodyDataForm.setAliasLocal(lbForm.getLocalBodyAliasLocal().trim());
				}
				
				if (lbForm.getLocalBodyNameEnglish() == null) {
					localBodyDataForm.setLocalBodyNameEnglish(lbForm.getLocalBodyNameEnglish());
				} else {
					localBodyDataForm.setLocalBodyNameEnglish(lbForm.getLocalBodyNameEnglish().trim());
				}
				if (lbForm.getLocalBodyNameLocal() == null) {
					localBodyDataForm.setLocalBodyNameLocal(lbForm.getLocalBodyNameLocal());
				} else {
					localBodyDataForm.setLocalBodyNameLocal(lbForm.getLocalBodyNameLocal().trim());
				}
				localBodyDataForm.setLocalBodyCode(lbForm.getLocalBodyCode());
				localBodyDataForm.setLocalBodyVersion(lbForm.getLocalBodyVersion());
				localBodyDataForm.setMinorVersion(lbForm.getMinorVersion());
				localBodyDataForm.setCordinate(cordinate);
				// localBodyDataForm.setLrReplacedby(element.getLrReplacedby());
				// localBodyDataForm.setLrReplaces(element.getLrReplaces());
				//localBodyDataForm.setMapCode(lbForm.getm);
				localBodyDataForm.setStateCode(lbForm.getStateCode());
				listLocalBodyDetails.add(localBodyDataForm);
				
				LocalGovtBodyForm vform = new LocalGovtBodyForm();
				vform.setListLocalBodyDetails(listLocalBodyDetails);
			
		return listLocalBodyDetails;
	}

	/* Retrieve files from the attachment table */

	public List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws Exception {
		return localGovtBodyDAO.getAttacmentdetail(orderCode);
	}

	/* Retrieving the Map details attachment from the database */
	/*@Override
	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode) {
		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			return localGovtBodyDAO.getEntityWiseMapDetails(entityType, entityCode, session);
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

	}*/

	@Override
	public List<GetLocalGovtBodyTypeList> getLBGTypeList(int stateCode) {
		log.debug("Inside the serviceIMPL class");
		Session session = null;
		List<GetLocalGovtBodyTypeList> getLGBTypelist = new ArrayList<GetLocalGovtBodyTypeList>();
		try {
			session = sessionFactory.openSession();
			getLGBTypelist = localGovtBodyDAO.getLBGTypeList(stateCode, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLGBTypelist;
	}

	@Override
	public List<LocalbodyWard> getWardDetails(int lblc/* , int offset ,int limit */) {
		return localGovtBodyDAO.getWardDetailsDAO(lblc/* ,offset,limit */);
	}

	/*@Override
	public int countWardDetails(int lblc) {
		return localGovtBodyDAO.countWardDetailsDAO(lblc);
	}
*/
	@Override
	public int countLocalBodyDetails(Integer lbtype, int stateCode) {
		return localGovtBodyDAO.countLocalBodyDetails(lbtype, stateCode);
	}

	/*@Override
	public Integer getmaxlandregioncode() {
		return localGovtBodyDAO.getmaxlandregioncode();
	}
*/
	@Override
	public Integer getlbcoverdregioncode(int lbcode) {
		int lbcoverdRegion = 0;
		try {
			lbcoverdRegion = localGovtBodyDAO.getlbcoverdregioncode(lbcode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return lbcoverdRegion;
	}

	@Override
	public Integer getParentLblccode(int lbcode) {
		int lbcoverdRegion = 0;
		try {
			lbcoverdRegion = localGovtBodyDAO.getParentLblccode(lbcode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return lbcoverdRegion;
	}

	@Override
	public int countLocalBodyDetailsforVillagePanchayat(Integer lbtype, int stateCode, String intermediatePanchayatCodes) {
		return localGovtBodyDAO.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, intermediatePanchayatCodes);
	}

	@Override
	public String invalidatePRILB(LocalGovtBodyForm LBForm, HttpSession httpSession) {
		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		//String lbdata = null;
		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		Integer ordercode = null;
		String localbdata = null;
		GovernmentOrderForm govtOrderForm = null;
		int Transactionid = 0;
		char t_type = 'R';
		try {

			// orderCode=localGovtBodyDAO.invalidatePRIDAO(LBForm,hsession,userId,httpSession);

			localbdata = localGovtBodyDAO.invalidatePRIDAO(LBForm, hsession, userId, httpSession);
			if (localbdata != null) {
				String[] ldata = localbdata.split(",");
				String vc = ldata[0];
				String tid = ldata[1];
				ordercode = Integer.parseInt(vc);
				Transactionid = Integer.parseInt(tid);
			}

			if (ordercode != null) {
				govtOrderForm = null;
				List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
				if (LBForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					govtOrderForm = (GovernmentOrderForm) httpSession.getAttribute("govtOrderForm");
					AddAttachmentBean addAttachmentBean = (AddAttachmentBean) httpSession.getAttribute("addAttachmentBean");
					AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
					attachmentHandler.validateAttachment(addAttachmentBean);
					metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
					attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);

				}
				if (LBForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					govtOrderService.saveDataInAttachmentWithOrderCode(ordercode, metaInfoOfToBeAttachedFileList, hsession);
				} else if (LBForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					//boolean insertGovtTableFlag = 
					govtOrderService.saveDataInAttachment(govtOrderForm, LBForm, httpSession, ordercode, hsession);
				}
			}

			tx.commit();
			EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
			est.start();

		} catch (Exception e) {
			log.debug("Exception" + e);

			tx.rollback();
		} finally {

			if (hsession != null && hsession.isOpen()) {
				hsession.close();
			}
			
			// sessionFactory.close();
		}
		return localbdata;
	}

	@Override
	public String invalidateTRILB(LocalGovtBodyForm LBForm, HttpSession httpSession) {
		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		Integer ordercode = null;
		String localbdata = null;
		int Transactionid = 0;
		char t_type = 'T';
		try {
			localbdata = localGovtBodyDAO.invalidateTRIDAO(LBForm, hsession, userId, httpSession);
			if (localbdata != null) {
				String[] ldata = localbdata.split(",");
				String vc = ldata[0];
				String tid = ldata[1];
				ordercode = Integer.parseInt(vc);
				Transactionid = Integer.parseInt(tid);
			}
			if (ordercode != null) {
				GovernmentOrderForm govtOrderForm = null;
				List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
				if (LBForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					govtOrderForm = (GovernmentOrderForm) httpSession.getAttribute("govtOrderForm");
					AddAttachmentBean addAttachmentBean = (AddAttachmentBean) httpSession.getAttribute("addAttachmentBean");
					AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
					attachmentHandler.validateAttachment(addAttachmentBean);
					metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
					attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);

					EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
					est.start();
				}
				if (LBForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					govtOrderService.saveDataInAttachmentWithOrderCode(ordercode, metaInfoOfToBeAttachedFileList, hsession);
				} else if (LBForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					//boolean insertGovtTableFlag = 
					govtOrderService.saveDataInAttachment(govtOrderForm, LBForm, httpSession, ordercode, hsession);
				}
			}

			tx.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);

			tx.rollback();
		} finally {

			if (hsession != null && hsession.isOpen()) {
				hsession.close();
			}
			// sessionFactory.close();
		}
		return localbdata;
	}

	@Override
	public String invalidateURBLB(LocalGovtBodyForm LBForm, HttpSession httpSession) {
		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		//Integer orderCode = 0;
		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		Integer ordercode = null;
		String localbdata = null;
		int Transactionid = 0;
		char t_type = 'T';
		try {
			localbdata = localGovtBodyDAO.invalidateTRIDAO(LBForm, hsession, userId, httpSession);
			if (localbdata != null) {
				String[] ldata = localbdata.split(",");
				String vc = ldata[0];
				String tid = ldata[1];
				ordercode = Integer.parseInt(vc);
				Transactionid = Integer.parseInt(tid);
			}
			{
				GovernmentOrderForm govtOrderForm = null;
				List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
				if (LBForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					govtOrderForm = (GovernmentOrderForm) httpSession.getAttribute("govtOrderForm");
					AddAttachmentBean addAttachmentBean = (AddAttachmentBean) httpSession.getAttribute("addAttachmentBean");
					AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
					attachmentHandler.validateAttachment(addAttachmentBean);
					metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
					attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);

					EmailSmsThread est = new EmailSmsThread(Transactionid, t_type, emailService);
					est.start();
				}
				if (LBForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					govtOrderService.saveDataInAttachmentWithOrderCode(ordercode, metaInfoOfToBeAttachedFileList, hsession);
				} else if (LBForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					//boolean insertGovtTableFlag = 
					govtOrderService.saveDataInAttachment(govtOrderForm, LBForm, httpSession, ordercode, hsession);
				}
			}

			tx.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);

			tx.rollback();
		} finally {

			if (hsession != null && hsession.isOpen()) {
				hsession.close();
			}
			// sessionFactory.close();
		}
		return localbdata;
	}

	@Override
	public int countwardDetails(int lblc) {
		return localGovtBodyDAO.countwardDetails(lblc);
	}

	/* modified by kirandeep on 01/09/2014 */
	@Override
	public boolean modifyWardData(LocalGovtBodyForm modifyWardCmd, HttpServletRequest request) throws Exception {
		Session session = null;
		//Transaction tx = null;
		boolean retValue = false;
		LocalbodyWard lbward = new LocalbodyWard();
		LocalbodyWardId lbwardid = new LocalbodyWardId();
		try {
			session = sessionFactory.openSession();

			lbwardid.setWardCode(modifyWardCmd.getWard_code());
			lbwardid.setWardVersion(modifyWardCmd.getLocalBodyVersion());
			lbward.setWardNameEnglish(modifyWardCmd.getWard_Name());
			lbward.setWardNameLocal(modifyWardCmd.getWard_NameLocal());
			lbward.setWardNumber(modifyWardCmd.getWard_number());
			lbward.setLblc(modifyWardCmd.getLblc());
			
			lbward.setEffectiveDate(modifyWardCmd.getEffectiveDate());
			lbward.setLastupdated(modifyWardCmd.getLastupdatedon());
			lbward.setLastupdatedby(modifyWardCmd.getLastupdatedby());
			lbward.setCreatedby(modifyWardCmd.getCreatedby());
			lbward.setCreatedon(modifyWardCmd.getCreatedon());
			lbward.setIsactive(modifyWardCmd.isIsactive());

			retValue = localGovtBodyDAO.modifyWardData(lbward, request, session);

		} catch (Exception e) {
			log.debug("Exception" + e);
			retValue = false;
		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
			// sessionFactory.close();
		}
		return retValue;

	}

	public String getlocalbodyname(int lblc) throws Exception {
		return (localGovtBodyDAO.getLocalBodyName(lblc));
	}

	@Override
	public String isVillageExist(int[] localbodyCode, char lbType) {
		return localGovtBodyDAO.isVillageExist(localbodyCode, lbType);
	}

	@Override
	public String getLocalBodyTypeMessagebyLocalbodyCode(Integer localbodyCode) {
		return localGovtBodyDAO.getLocalBodyTypeMessagebyLocalbodyCode(localbodyCode);
	}

	public List<GetLocalGovtSetup> getLocalbodyDetailbyCode(int stateCode, int lblc) throws Exception {

		// int stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		List<GetLocalGovtSetup> getLocalGovtSetupList1 = null;
		getLocalGovtSetupList1 = new ArrayList<GetLocalGovtSetup>();
		getLocalGovtSetupList = localGovtBodyDAO.getLocalbodyDetailDAO(stateCode);
		GetLocalGovtSetup getLocalGovtSetup = new GetLocalGovtSetup();
		if (getLocalGovtSetupList.size() > 0) {
			Iterator<GetLocalGovtSetup> itr = getLocalGovtSetupList.iterator();

			//String localBodyTypeCode = null;
			while (itr.hasNext()) {

				GetLocalGovtSetup element = (GetLocalGovtSetup) itr.next();
				if (element.getTierSetupCode() == lblc) {
					getLocalGovtSetup.setCategory(element.getCategory());
					getLocalGovtSetup.setLevel(element.getLevel());
					getLocalGovtSetup.setLocalBodyTypeCode(element.getLocalBodyTypeCode());
					getLocalGovtSetup.setLocalBodyTypeName(element.getLocalBodyTypeName());
					getLocalGovtSetup.setNomenclatureEnglish(element.getNomenclatureEnglish());
					getLocalGovtSetup.setNomenclatureLocal(element.getNomenclatureLocal());
					getLocalGovtSetup.setParentLocalBodyTypeName(element.getParentLocalBodyTypeName());
					getLocalGovtSetup.setParentTierSetupCode(element.getParentTierSetupCode());
					getLocalGovtSetup.setTierSetupCode(element.getTierSetupCode());

				}

			}
			getLocalGovtSetupList1.add(0, getLocalGovtSetup);

			/*
			 * String
			 * LocalbodyCode=localBodySetupDAO.getLocalBodyTypeCode1(localbodyTypes
			 * );
			 */
			return getLocalGovtSetupList1;
		}
		return getLocalGovtSetupList1;
	}

	@Override
	public List<LocalbodyListbyState> getExistingLBListTwoTier(int stateCode) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getExistingPanchayatList(2, stateCode);
		localbodywholeList.addAll(localbodyList);

		return localbodywholeList;
	}

	@Override
	public List<LocalbodyListbyState> getLandRegionByDistricCode(Integer localBodyTypeCode, Integer districtCode, String lbType) throws Exception {
		log.debug("DISTRICT ID::: " + districtCode + "LB TYPE::" + lbType);
		return localGovtBodyDAO.getLandRegionByDistricCode(localBodyTypeCode, districtCode, lbType);
	}

	@Override
	public String saveLocalBodyDraft(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, UserSelection userSelection) {
		String retValue = null;
		List<ChangeLocalBodyName> changeLBName = null;
		try {
			changeLBName = localGovtBodyDAO.saveLocalBodyDraft(localGovtBodyForm, request, userSelection);
			Iterator<ChangeLocalBodyName> changeLBNameItr = changeLBName.iterator();
			ChangeLocalBodyName localdata = (ChangeLocalBodyName) changeLBNameItr.next();
			retValue = localdata.getChange_local_body_name_fn();
			log.info("THE CAHNGED LOCAL BODY NAME::::: " + retValue);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return retValue;
	}

	public List<LocalbodyListbyState> getmappedlbforPConsituency(int stateCode, int lbtypeCode, int pcCode, char type) throws Exception {

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getmappedlbforPConsituency(lbtypeCode, stateCode, pcCode, type);

		return localbodyList;
	}

	public List<LocalbodyListbyState> getunmappedlbforPConsituency(int stateCode, int lbtypeCode, int pcCode, char type) throws Exception {

		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getunmappedlbforPConsituency(lbtypeCode, stateCode, pcCode, type);

		return localbodyList;
	}

	@Override
	public boolean ULBExistforConvert(int slc, int type, String ulbName) {

		return localGovtBodyDAO.ULBExistforConvert(slc, type, ulbName);
	}

	@Override
	public int getlblc(int localbodyCd) throws Exception {
		int lblc = 0;
		try {
			lblc = localGovtBodyDAO.getlblc(localbodyCd);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return lblc;
	}

	@Override
	public String getdisturblbReason(int lbCode, char type) throws Exception {
		String details = null;
		if (type == 'V') {
			details = localGovtBodyDAO.getdisturblbReasonVP(lbCode, type);
		} else if (type == 'I') {
			details = localGovtBodyDAO.getdisturblbReasonIP(lbCode, type);
		} else if (type == 'D') {
			details = localGovtBodyDAO.getdisturblbReasonDP(lbCode, type);
		}
		return details;
	}

	@Override
	public List<LocalbodyListbyState> getMappedVillageWardofConsituency(int PcCode, char consituencyType, char entityType, int slc) throws Exception {
		return localGovtBodyDAO.getMappedVillageWardofConsituency(PcCode, consituencyType, entityType, slc);
	}

	@Override
	public String mergeULB(LocalGovtBodyForm lbForm, HttpSession httpSession) {
		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		String localbdata = localGovtBodyDAO.mergeULB(lbForm, userId, hsession);
		Integer ordercode = null;
		//Integer Transactionid = null;
		GovernmentOrderForm govtOrderForm = null;
		if (localbdata != null) {
			String[] ldata = localbdata.split(",");
			String vc = ldata[1];
			//String tid = ldata[0];
			ordercode = Integer.parseInt(vc);
			//Transactionid = Integer.parseInt(tid);
		}
		try {
			if (ordercode != null) {
				govtOrderForm = null;
				List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
				if (lbForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					govtOrderForm = (GovernmentOrderForm) httpSession.getAttribute("govtOrderForm");
					AddAttachmentBean addAttachmentBean = (AddAttachmentBean) httpSession.getAttribute("addAttachmentBean");
					AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
					attachmentHandler.validateAttachment(addAttachmentBean);
					metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
					attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);

				}
				if (lbForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					govtOrderService.saveDataInAttachmentWithOrderCode(ordercode, metaInfoOfToBeAttachedFileList, hsession);
				} else if (lbForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					//boolean insertGovtTableFlag = 
					govtOrderService.saveDataInAttachment(govtOrderForm, lbForm, httpSession, ordercode, hsession);
				}
			}
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			localbdata = null;
			tx.rollback();
		} finally {
			if (hsession != null && hsession.isOpen()) {
				hsession.close();
			}
		}
		return localbdata;
	}

	@Override
	public boolean mapZpConsituencyDetail(String wardNo, String lbList, String vilList, String deltedLb, String deletedVil) throws Exception {
		boolean status = localGovtBodyDAO.mapZpConsituencyDetail(wardNo, lbList, vilList, deltedLb, deletedVil);
		return status;
	}

	@Override
	public List<ParentWiseLocalBodies> mappedZpWardConsituencyDetail(String wardNo, char type, boolean wholeData) throws Exception {
		List<ParentWiseLocalBodies> mappedenttity = localGovtBodyDAO.mappedZpWardConsituencyDetail(wardNo, type, wholeData);
		return mappedenttity;
	}

	/* modified by kirandeep on 01/09/2014 */
	@Override
	public Integer WardExist(int lblc, String wardName, int type) {
		return localGovtBodyDAO.WardExist(lblc, wardName, type);
	}

	@Override
	public List<Localbody> getChildLbByParentLb(int parentlblc, int slc) throws Exception {
		return localGovtBodyDAO.getChildLbByParentLb(parentlblc, slc);
	}

	@Override
	public boolean updateLbPesaStatus(String lbcode, int slc, String deletedlbcode) throws Exception {
		return localGovtBodyDAO.updateLbPesaStatus(lbcode, slc, deletedlbcode);
	}

	/**
	 * Change For LGD CODE by Maneesh Kumar 19Sep2014
	 */
	@Override
	public List<StatewisePesaPanchyat> getactiveLbPesa(Integer slc) {
		return localGovtBodyDAO.getactiveLbPesa(slc);
	}

	/*@Override
	public List<Localbody> getactiveLbPesa1(int slc) {
		// TODO Auto-generated method stub
		return localGovtBodyDAO.getactiveLbPesa1(slc);
	}*/

	@Override
	public List<State> getStateWisePesa() {
		// TODO Auto-generated method stub
		return localGovtBodyDAO.getStateWisePesa();
	}

	@Override
	public Integer getLblc(Integer localBodyCode) {
		return localGovtBodyDAO.getLblc(localBodyCode);
	}

	@Override
	public List<LocalBodyParent> getStateTopHierarchy(int slc) {
		// TODO Auto-generated method stub
		return localGovtBodyDAO.getStateTopHierarchy(slc);

	}

	@Override
	public List<Localbody> getStateTopHierarchyforGta(int slc, int localBodyTypeCode) {
		// TODO Auto-generated method stub
		return localGovtBodyDAO.getStateTopHierarchyforGta(slc, localBodyTypeCode);
	}

	/*
	 * method created for the GTA(union added for gta in existing function and
	 * lblc to local body code) (manage localbody PRI) author Ashish Dhupia ,
	 * Date : 23/07/2014
	 */
	public List<LocalbodyListbyState> getPanchayatListbyParentCategoryFChangeTierforGta(int stateCode, char category, char level, int parentLbCode, int parentLBCode1) throws Exception {
		List<LocalbodyListbyState> localbodywholeList = new ArrayList<LocalbodyListbyState>();
		int lbTypeCode = localGovtBodyDAO.getLocalBodybyLBID(parentLBCode1);

		/**
		 * Check for Null or empty value by Maneesh Kumar 23July2015
		 */
		List<LocalbodyListbyState> localbodyList = localGovtBodyDAO.getPanchayatListbyParentCategoryFChangeTierforGta(lbTypeCode, stateCode, parentLbCode);
		if (localbodyList != null && !localbodyList.isEmpty()) {
			localbodywholeList.addAll(localbodyList);
		}

		return localbodywholeList;
	}

	@Override
	public Integer getlocalbodycodeByLblc(int parentLBCode) {
		// TODO Auto-generated method stub
		return localGovtBodyDAO.getlocalbodycodeByLblc(parentLBCode);
	}

	public boolean checkLocalbodyExist(Integer lbCode) {
		return this.localGovtBodyDAO.checkLocalbodyExist(lbCode);
	}

	/* modified by kirandeep on 01/09/2014 */
	@Override
	public boolean createwarddetailsdelete(List<LocalbodyWard> ward) {
		return localGovtBodyDAO.createwarddetailsdelete(ward);
	}

	public List<localbodywardtemp> getlocalGovtBodyWardListforpaginationtemp(int localBodyCode, int offset, int limit) {

		List<localbodywardtemp> wardList = new ArrayList<localbodywardtemp>();

		wardList = localGovtBodyDAO.getlocalGovtBodyWardListforpaginationtemp(localBodyCode, offset, limit);

		return wardList;

	}

	public boolean managewarddeletetemp(Integer tempWardid) {
		return localGovtBodyDAO.managewarddeletetemp(tempWardid);

	}

	public boolean createwardformanage(localbodywardtemp tempward) {
		return localGovtBodyDAO.createwardformanage(tempward);
	}

	public boolean createwardformanageUpdate(localbodywardtemp tempward, String isUpdate) {
		return localGovtBodyDAO.createwardformanageUpdate(tempward, isUpdate);
	}

	public List<LocalbodyWard> getlocalGovtBodyWardListforpaginationtempdelete(int localBodyCode, int offset, int limit) {

		List<LocalbodyWard> wardList = new ArrayList<LocalbodyWard>();

		wardList = localGovtBodyDAO.getlocalGovtBodyWardListforpaginationtempdelete(localBodyCode, offset, limit);

		return wardList;

	}

	public boolean getexistingwardinTempTable(Integer lblc) {
		return localGovtBodyDAO.getexistingwardinTempTable(lblc);
	}

	public Integer restoreWard(Integer wardCd) {
		return localGovtBodyDAO.restoreWard(wardCd);
	}

	public List<LocalBodyCoveredArea> getLocalGovtBodyCoveredVillageListValid(String localBodyCode, String mappedList) throws NumberFormatException, Exception {
		List<LocalBodyCoveredArea> coveredAreaList = new ArrayList<LocalBodyCoveredArea>();
		List<LocalBodyCoveredArea> coveredAreaListFull = new ArrayList<LocalBodyCoveredArea>();

		//String listOfIds = null;
		String listofMappedId = null;
		/*
		 * if (localBodyCode.contains(",")){ listOfIds =
		 * stringRemoveFull(localBodyCode); } else if
		 * (localBodyCode.contains("PART")){ listOfIds =
		 * stringRemovePart(localBodyCode); }
		 */

		if (mappedList != null) {
			listofMappedId = stringRemoveMAPD(mappedList);
		}
		String MappedList[] = listofMappedId.split(",");
		String selectdVillagePancId[] = localBodyCode.split(",");
		for (String vpIdObj : selectdVillagePancId) {

			coveredAreaListFull = localGovtBodyDAO.getLocalGovtBodyforCoveredVillageListFinal(Integer.parseInt(vpIdObj));
			for (LocalBodyCoveredArea i : coveredAreaListFull) {
				for (String mappedid : MappedList) {
					if (mappedid != null && !mappedid.isEmpty()) {
						if (i.getLandRegionCode() == Integer.parseInt(mappedid)) {
							return null;
						}

					}

				}
			}
			coveredAreaList.addAll(coveredAreaListFull);
		}

		return coveredAreaList;

	}

	public static String stringRemoveMAPD(String value) {
		return value.replaceAll("MAPD", "");
	}

	/* added by Kirandeep on 1/11/2014 for ward */
	@Override
	public List<Localbody> checkForExistingGp(String str) {

		return localGovtBodyDAO.checkForExistingGp(str);
	}

	/**
	 * Add for LocalBody Draft Mode to fetch villageList for selected
	 * subdistcode
	 * 
	 * @param subdistrictCode
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public List<Village> getVillageListforLocalBodyBySubdistCode(String subdistrictCode) throws NumberFormatException, Exception {
		List<Village> villageList = new ArrayList<Village>();
		List<Village> villageListFull = new ArrayList<Village>();

		String listOfIds, listOfIdsF = null;
		StringBuffer valueBufferVillageMapped = new StringBuffer();
		if (subdistrictCode.contains(",")) {
			String[] temp = subdistrictCode.split(",");
			for (int i = 0; i < temp.length; i++) {
				String[] temp2 = temp[i].split(":");
				for (int j = 0; j < temp2.length; j++) {
					if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
						valueBufferVillageMapped.append(temp2[j]);
						valueBufferVillageMapped.append(",");
					}
				}
			}
		}
		listOfIdsF = valueBufferVillageMapped.toString();
		listOfIds = listOfIdsF.replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
		String selectdSubdistId[] = listOfIds.split(",");

		for (String subdistobj : selectdSubdistId) {
			villageList = villageDAO.getVillageListbySubDistrictCodeCov(Integer.parseInt(subdistobj));
			villageListFull.addAll(villageList);
		}
		return villageListFull;
	}

	/**
	 * For Local Boby Impact To get subdist list for selected district Code
	 * Added on 10-12-2014
	 * 
	 * @author Ripunj
	 */
	@Override
	public List<Subdistrict> getLocalGovtBodySubDistrictListChangeCover(String districtcode) throws Exception {
		List<Subdistrict> subdistrictList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictListFull = new ArrayList<Subdistrict>();
		String listOfIds, listOfIdsF = null;

		StringBuffer valueBufferDistMapped = new StringBuffer();
		try {

			if (districtcode.contains(",")) {
				String[] temp = districtcode.split(",");
				for (int i = 0; i < temp.length; i++) {
					String[] temp2 = temp[i].split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_D") || temp2[j].contains("_FULL_D")) {
							valueBufferDistMapped.append(temp2[j]);
							valueBufferDistMapped.append(",");
						}
					}
				}
			}

			listOfIdsF = valueBufferDistMapped.toString();
			listOfIds = listOfIdsF.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");

			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictListFull = subdistrictDAO.getSubDistListbyDistrictCodeCov(Integer.parseInt(dpIdObj));
				subdistrictList.addAll(subdistrictListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictList;
	}

	@Override
	public List<Subdistrict> getLocalGovtBodySubDistrictListForLocalBody(String districtcode) throws Exception {
		List<Subdistrict> subdistrictList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictListFull = new ArrayList<Subdistrict>();
		String listOfIds = null;
		try {
			if (districtcode.contains("PART") && !districtcode.contains("FULL")) {
				listOfIds = stringRemovePartLocalBody(districtcode);
			}
			if (!districtcode.contains("PART") && districtcode.contains("FULL")) {
				listOfIds = stringRemoveFullLocalBody(districtcode);
			}
			if (districtcode.contains("PART") && districtcode.contains("FULL")) {
				listOfIds = districtcode.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
			}

			String selectdSubdistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubdistId) {
				subdistrictListFull = subdistrictDAO.getSubDistListbyDistrictCodeCov(Integer.parseInt(dpIdObj));
				subdistrictList.addAll(subdistrictListFull);
			}
		} catch (Exception e) {
			throw e;
		}
		return subdistrictList;
	}

	/* added by Ashish Dhupia on 20/1/2015 for Habitation use case */
	@Override
	public boolean saveHabitation(Habitation habitationDet) {
		// TODO Auto-generated method stub
		return localGovtBodyDAO.saveHabitation(habitationDet);
	}

	@Override
	public List<LocalbodyforStateWise> gethierarchyforGP(char lbTypeCode, int stateCode) throws Exception {
		// TODO Auto-generated method stub
		List<LocalbodyforStateWise> traditionalTypeList = localGovtBodyDAO.gethierarchyforGP(lbTypeCode, stateCode);
		return traditionalTypeList;
	}

	
	/*@Override
	public List<LocalBodyHistory> getLocalBodyHistoryReport(char localBodyNameEnglish, int localBodyCode) throws BaseAppException {
		// TODO Auto-generated method stub
		return localGovtBodyDAO.getLocalBodyHistoryReport(localBodyNameEnglish, localBodyCode);
	}*/

	@Override
	public List<LocalbodyListbyState> getTopLocalbodyListforDistrictUser(Integer stateCode, Integer lbTypeCode, Integer districtCode) throws Exception {
		return localGovtBodyDAO.getTopLocalbodyListforDistrictUser(stateCode, lbTypeCode, districtCode);
	}

	@Override
	public List<LocalbodyListbyState> getchildlocalbodiesWithoutCountByParentcodeDistrict(Integer lbCode, Integer districtCode) throws Exception {
		return localGovtBodyDAO.getchildlocalbodiesWithoutCountByParentcodeDistrict(lbCode, districtCode);
	}
	/**
	 * For getting Ward List by LocalBodyCode
	 * @author Pooja 29-07-2015
	 * @param localBodyCode
	 */
	@Override
	public List<LocalbodyWard> getWardListByLbCode(Integer localBodyCode) throws Exception {
		return localGovtBodyDAO.getWardListByLbCode(localBodyCode);
	}
	/**
	 * For getting CoveredArea List of LocalBodyCodes
	 * @author Pooja Sharma (02-09-2015)
	 * @param localBodyCodes
	 */
	@Override
	public List<LocalGovtBodyNameList> getCoveredAreaListByLbCodes(String localBodyCodes) throws Exception{
		return localGovtBodyDAO.getCoveredAreaListByLbCodes(localBodyCodes);
	}

	@Override
	public List<LocalbodyListbyState> getUrbanListbyLocalbodyTypeList(String lbTypeCode, int stateCode)throws Exception {
		return localGovtBodyDAO.getUrbanListbyLocalbodyTypeList(lbTypeCode, stateCode);
	}

	@Override
	public Object[] getUrbanListbyAdminEntity(Integer adminEntityCode, Integer stateCode, String LbTypeCategory)throws Exception {
		return localGovtBodyDAO.getUrbanListbyAdminEntity(adminEntityCode, stateCode, LbTypeCategory);
	}

	@Override
	public List<ConstituencyLocalbody> getlbListforConstituencyMap(String lbType,Integer lbTypeCode, Integer stateCode, Integer parentlbCode,Integer districtCode, String lbFullCodes,String lbPartCodes,final Boolean forDropdown) throws Exception {
		List<Integer> lbPart =new ArrayList<Integer>();
		lbPart.add(-1);
		
		List<String> lbFull =new ArrayList<String>();
		lbFull.add("");
		
		if (lbFullCodes!=null && lbFullCodes!="") {
			Scanner scanner = new Scanner(lbFullCodes);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				lbFull.add(scanner.next());
			}
			scanner.close();
		}
		
		if (lbPartCodes!=null && lbPartCodes!="") {
			Scanner scanner = new Scanner(lbPartCodes);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				lbPart.add(Integer.parseInt(scanner.next()));
			}
			scanner.close();
		}
		return localGovtBodyDAO.getlbListforConstituencyMap(lbType,lbTypeCode, stateCode, parentlbCode, districtCode,lbFull, lbPart,forDropdown);
	}
	
	@Override
	public List<ConstituencyVillage> getVillageListforConstituencyMap(String lbPartCodes) throws Exception {
		List<Integer> lbPart =new ArrayList<Integer>();
		lbPart.add(-1);
		if (lbPartCodes!=null && lbPartCodes!="") {
			Scanner scanner = new Scanner(lbPartCodes);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				lbPart.add(Integer.parseInt(scanner.next().replace("PART", "")));
			}
			scanner.close();
		}
		return localGovtBodyDAO.getVillageListforConstituencyMap(lbPart);
	}	
	/*@Override
	public List<LBTypeHierarchy> getLBHierarchyList(Integer stateCode,String PANCHAYAT_TYPE) throws Exception {
		return localGovtBodyDAO.getLBHierarchyList(stateCode, PANCHAYAT_TYPE);
	}*/

	/*@Override
	public boolean saveTempWards(List<localbodywardtemp> insertListWard,List<localbodywardtemp> updateListWard,List<localbodywardtemp> deleteListWard) throws Exception {
		return localGovtBodyDAO.saveTempWards(insertListWard, updateListWard, deleteListWard);
	}*/

	/*@Override
	public boolean publishWards(WardForm wardForm) throws Exception {
		return localGovtBodyDAO.publishWards(wardForm);
	}*/

	/*@Override
	public List<localbodywardtemp> getLocalbodyWardTempList(Integer lblc) throws Exception {
		return localGovtBodyDAO.getLocalbodyWardTempList(lblc);
	}*/

	/*@Override
	public List<LocalbodyWard> getLocalbodyWardList(Integer lblc) throws Exception {
		return localGovtBodyDAO.getLocalbodyWardList(lblc);
	}*/

	/*@Override
	public List<LBTypeDetails> getLBTypeDetailsList(Integer stateCode, String PANCHAYAT_TYPE) throws Exception {
		return localGovtBodyDAO.getLBTypeDetailsList(stateCode, PANCHAYAT_TYPE);
	}*/
	
	@Override
	public List<ConstituencyMapDetailsbyacCode> getConstituencyMapDetails(Integer acCode) throws Exception {
		return localGovtBodyDAO.getConstituencyMapDetails(acCode);
	}
	
	@Override
	public List<ConstituencyWard> getWardListforConstituencyMap(String lbPartCodes,String wardCodes) throws Exception {
		List<Integer> lblcCodeList =new ArrayList<Integer>();
		lblcCodeList.add(-1);
		if (lbPartCodes!=null && lbPartCodes!="") {
			Scanner scanner = new Scanner(lbPartCodes);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				lblcCodeList.add(Integer.parseInt(scanner.next().replace("PART", "")));
			}
			scanner.close();
		}
		
		List<Integer> wardCodeList =new ArrayList<Integer>();
		wardCodeList.add(-1);
		if (wardCodes!=null && wardCodes!="") {
			Scanner scanner = new Scanner(wardCodes);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				wardCodeList.add(Integer.parseInt(scanner.next()));
			}
			scanner.close();
		}
		return localGovtBodyDAO.getWardforConstituencyMap(lblcCodeList,wardCodeList);
	}	
	
	/**
	 * @author Sunita Dagar
	 * @Date 18-11-2016
	 */
	public boolean saveNodalOfficerDetails(NodalOfficerForm nodalOfficerForm) throws Exception {
		return localGovtBodyDAO.saveNodalOfficerDetails(nodalOfficerForm);
	}

	@Override
	public List<NodalOfficer> getNodalOfficerDetails(Integer userId, Integer districtCode) {
		return localGovtBodyDAO.getNodalOfficerDetails(userId,districtCode);
	}

	@Override
	public boolean UpdateIsactiveStatue(int createdBy, Integer districtCode) {
		return localGovtBodyDAO.UpdateIsactiveStatue(createdBy,districtCode);
	}

	@Override
	public List<LocalBodyCoveredArea> getCoveredVillagesofLocalbodies(String localBodyCodes) throws Exception {
		return localGovtBodyDAO.getCoveredVillagesofLocalbodies(localBodyCodes);
	}
	
	@Override
	public List<LocalbodyforStateWiseFinal> getLocalBodyListforGisStatus(Integer localBodyType, Integer stateCode,Integer plblc, Integer lbCode, String lbName) throws Exception {
		return localGovtBodyDAO.getLocalBodyListforGisStatus(localBodyType, stateCode, plblc, lbCode, lbName);
	}

	@Override
	public Date getExistingLBEffectiveDate(Integer localBodyCode) throws HibernateException {
		return localGovtBodyDAO.getExistingLBEffectiveDate(localBodyCode);
	}

	@Override
	public String InvalidateUrbanLocalbodyProcess(LocalGovtBodyForm lbForm) throws Exception {
		return localGovtBodyDAO.InvalidateUrbanLocalbodyProcess(lbForm);
	}

	@Override
	public LocalBodyTable getMaxVersionLocabody(Integer localbodyCode) throws Exception {
		return localGovtBodyDAO.getMaxVersionLocabody(localbodyCode);
	}

	@Override
	public List<LgdPfmsMapping> fetchLgdPfmsMapping(Integer districtCode) throws Exception{
		
		return localGovtBodyDAO.getLdgPfmsMapping(districtCode);
	}

	@Override
	public boolean updateLgdPfmsMappingStatus(Integer lgdPfmsId,Integer userId) throws Exception {
		return localGovtBodyDAO.updateVerifiedStatus(lgdPfmsId,userId);
	}
	
}