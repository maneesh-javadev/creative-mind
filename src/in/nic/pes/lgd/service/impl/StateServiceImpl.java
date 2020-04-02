package in.nic.pes.lgd.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.bean.AssemblyConstituency;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AuditTrailLGD;
import in.nic.pes.lgd.bean.ConfigurationBlockVillageMapping;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictFreezeEntity;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.EntityFreezeStatus;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.FreezeLocalBodyEntity;
import in.nic.pes.lgd.bean.FreezeUnfreezeStateConfigEntity;
import in.nic.pes.lgd.bean.GeneratedFileDetails;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.LandregionReplacedby;
import in.nic.pes.lgd.bean.LandregionReplaces;
import in.nic.pes.lgd.bean.LgdDataConfirmation;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationByCentreLevel;
import in.nic.pes.lgd.bean.ParentwiseChildDetials;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.Search;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateHistory;
import in.nic.pes.lgd.bean.StateNamed;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictPK;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillagePK;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.GovernmentOrderDAO;
import in.nic.pes.lgd.dao.HeadquartersDAO;
import in.nic.pes.lgd.dao.LandRegionReplacedByDAO;
import in.nic.pes.lgd.dao.LandRegionReplacesDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.MapLandRegionDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.forms.StateDataForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.BlockService;
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
import in.nic.pes.lgd.utils.CommonUtil;
import in.nic.pes.lgd.utils.CurrentDateTime;
import in.nic.pes.lgd.utils.DatePicker;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

@Repository
public class StateServiceImpl implements StateService {
	

	private static final Logger log = Logger.getLogger(StateServiceImpl.class);

	@Autowired
	CommonService commonService;

	@Autowired
	EmailService emailService;

	/**
	 * 
	 */
	/**
	 * 
	 */
	@Autowired
	BlockService blockService;
	@Autowired
	StateDAO stateDao;

	@Autowired
	CurrentDateTime dateTimeUtil;

	@Autowired
	FileUploadUtility fileUploadUtility;

	@Autowired
	ShiftService shiftService;
	@Autowired
	DistrictDAO districtDAO;

	@Autowired
	DistrictService districtService;

	@Autowired
	SubDistrictService SubistrictService;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	private SubDistrictDAO subdistrictDAO;

	@Autowired
	private MapLandRegionDAO mapLandRegionDAO;

	@Autowired
	private VillageDAO villageDAO;

	@Autowired
	GovernmentOrderDAO govtOrderDAO;

	@Autowired
	VillageService villageService;

	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	private LandRegionReplacedByDAO landRegionReplacedByDAO;

	@Autowired
	private LandRegionReplacesDAO landRegionReplacesDAO;

	@Autowired
	private HeadquartersDAO headquartersDAO;

	@Autowired
	SessionFactory sessionFactory;

	// get the district code, district version, map code from session
	// int districtCode = 34;
	// int districtVersion = 1;
	int mapCd;
	int stateCode = 0;
	int distictCode;
	int districVersion;
	int userId = 1000;
	long createdBy = 1000;
	// District dist=new District(districtCode,districtVersion);
	Date timestamp = DatePicker.getDate("2011-09-26");

	int sdCode;
	int sdVersionCode;
	int subdistricVersion;
	int districtCode = 0;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean changeStateforTemplate(StateForm stateForm, GovernmentOrderForm govtOrderForm, HttpServletRequest request, HttpSession httpSession) throws Exception {
		// TODO Auto-generated method stub
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int stateVersion = 0;
			boolean flag = false;
			List<StateDataForm> listState = new ArrayList<StateDataForm>();
			listState = stateForm.getListStateDetails();
			Iterator<StateDataForm> itr = listState.iterator();
			Integer stateCode = null, slc = null;
			Integer tid = null, orderCode = null;
			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;

			while (itr.hasNext()) {

				StateDataForm element = itr.next();
				stateVersion = stateDao.getMaxVersionbyStateCode(element.getStateCode());
				if (stateVersion == 1) {
					stateVersion = stateVersion + 1;
				} else {
					stateVersion = stateVersion + 1;
				}

				stateCode = element.getStateCode();
				slc = commonService.getSlc(stateCode);
				String stateOrUt = String.valueOf(element.getStateOrUt());

				String orderpath = "govtOrderUpload";
				String data = stateDao.ChangeCrState(element.getStateCode(), element.getStateNameEnglish(), element.getStateNameEnglishch(), userId, govtOrderForm.getOrderNo(), govtOrderForm.getOrderDate(), govtOrderForm.getEffectiveDate(),
						govtOrderForm.getGazPubDate(), orderpath, stateOrUt, element.getStateNameLocal(), element.getShortName(), session, slc);

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
					 * slc.toString(), 'S', session);
					 */
					if (orderCode != null) {
						flag = convertLocalbodyService.saveDataInAttachmentWithOrderCodeGenrate(orderCode, generatedetails, session);
					}

					if (flag) {
						tx.commit();
						char t_type = 'S';
						EmailSmsThread est = new EmailSmsThread(tid, t_type, emailService);
						est.start();
					} else
						tx.rollback();
					return true;

				}
			}

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
		return false;
	}

	// added by kamlesh for create State
	@Override
	public ModelAndView createState(StateForm stateForm, GovernmentOrderForm govtOrderForm, int user_id, List<AttachedFilesForm> validFileMap, HttpServletRequest request, BindingResult bindingResult, HttpSession httpSession) throws Exception {
		List<StateNamed> result = null;
		Session hsession = null;
		ModelAndView mav = null;
		boolean insertGovtTableFlag;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		int Transactionid = 0;
		char stateOrUt = '\u0000';
		int tmpflag = 0;
		try {
			String stateNameEnglish = (stateForm.getDistrictNameInEn() != null) ? stateForm.getDistrictNameInEn().trim() : null;
			String shortName = (stateForm.getShortName() != null) ? stateForm.getShortName().trim() : null;
			stateOrUt = stateForm.getStateOrUt();
			String census20011 = (stateForm.getCensus2011Code() != null && !stateForm.getCensus2011Code().isEmpty()) ? stateForm.getCensus2011Code().trim() : null;
			String headquterNameEnglish = (stateForm.getHeadquarterName() != null) ? stateForm.getHeadquarterName().trim() : null;
			String headquterNameLocal = (stateForm.getHeadquarterNameLocal().trim() != null) ? stateForm.getHeadquarterNameLocal().trim() : null;
			String contributeState = (stateForm.getSTATENAMEENGLISH() != null) ? stateForm.getSTATENAMEENGLISH().trim() : null;
			String contributeDistrict = (stateForm.getDistrictNameEnglish() != null) ? stateForm.getDistrictNameEnglish().trim() : null;
			String govtOrderConfig = stateForm.getGovtOrderConfig() != null ? stateForm.getGovtOrderConfig().toString().trim() : null;
			String order_no = govtOrderForm.getOrderNo() != null ? govtOrderForm.getOrderNo().toString().trim() : null;
			Date order_date = govtOrderForm.getOrderDate();
			Date effective_date = govtOrderForm.getEffectiveDate();
			Date gaz_pub_date = govtOrderForm.getGazPubDate();
			String[] temp = null;
			String stateListFull = null;
			String stateListPart = null;
			if (contributeState != null) {
				temp = contributeState.split("\\,");
				for (int i = 0; i < temp.length; i++) {
					if (temp[i].contains("FULL")) {
						if (stateListFull == null) {
							stateListFull = temp[i].replaceAll("FULL", "");
						} else {
							stateListFull = stateListFull + ",";
							stateListFull = stateListFull + temp[i].replaceAll("FULL", "");
						}

					} else if (temp[i].contains("PART")) {
						if (stateListPart == null) {
							stateListPart = temp[i].replaceAll("PART", "");
						} else {
							stateListPart = stateListPart + ",";
							stateListPart = stateListPart + temp[i];
							stateListPart = stateListPart.replaceAll("PART", "");
						}
					}

				}
			}

			String list_of_district_full = null;
			if (contributeDistrict != null) {
				list_of_district_full = contributeDistrict.replaceAll("FULL", "");
				;
			}

			Integer map_attachment_code = null;
			if (!(validFileMap == null)) {
				map_attachment_code = stateDao.saveDataInMap(stateForm, validFileMap, hsession);
			} else {
				map_attachment_code = null;
			}
			String tmpResult = null;
			result = stateDao.createStateDAO(user_id, stateNameEnglish, order_no, order_date, effective_date, stateOrUt, census20011, gaz_pub_date, stateListFull, stateListPart, list_of_district_full, shortName,headquterNameEnglish, hsession);

			Integer annouId = null;
			Integer stateId = null;
			if (result == null) {
				tmpflag = 1;
			} else {
				tx.commit();
				annouId = null;
				stateId = null;
				Iterator<StateNamed> itr = result.iterator();
				while (itr.hasNext()) {
					StateNamed element = itr.next();
					tmpResult = element.getCreate_state_fn();
				}

			}

			if (tmpResult != null) {
				String[] subStringsArray = tmpResult.split("\\,");
				annouId = Integer.parseInt(subStringsArray[1]);
				stateId = Integer.parseInt(subStringsArray[0]);
				Transactionid = Integer.parseInt(subStringsArray[2]);
				stateForm.setStateCode(stateId);

			}
			govtOrderForm.setOrderCode(annouId);
			if (govtOrderConfig.equalsIgnoreCase("govtOrderGenerate")) {
				GenerateDetails genDetails = (GenerateDetails) httpSession.getAttribute("validGovermentGenerateUpload");
				insertGovtTableFlag = stateDao.saveDataInAttachGenerateState(govtOrderForm, genDetails, annouId, session);
			} else {
				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, bindingResult);
				insertGovtTableFlag = stateDao.saveDataInAttachWithOrderCode(govtOrderForm, validFileGovtUpload, annouId, session);
			}

			if (!insertGovtTableFlag) {
				mav = new ModelAndView("error");
				mav.addObject("msgid", "New State Creation Failed, Kindly Re-enter Again");
			}

			mav = new ModelAndView("success");
			mav.addObject("msgid", "New State Created Successfully");

		} catch (Exception e) {
			log.debug("Exception" + e);
			tx.rollback();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;

		} finally {
			if (hsession != null && hsession.isOpen()) {
				hsession.close();
			}

		}
		if (Transactionid > 0) {
			EmailSmsThread est = new EmailSmsThread(Transactionid, stateOrUt, emailService);
			est.start();
		}
		return mav;
	}

	@Override
	public boolean DistrcitmodifyReorg(StateForm SDForm, List<DistrictForm> listdistritNew, List<VillageForm> listVillageModify, HttpServletRequest request, HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(SDForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		try {
			// while(isMethod1Commited==true){
			for (int i = 0; i < listdistritNew.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listdistritNew.get(i).setStateNameEnglish(String.valueOf(stateCode));
				// listNewVillForm.get(i).setSubdistrictNameEnglish(String.valueOf(districtCode));
				// listNewVillForm.get(i).setCreateFromExistingVillages(true);
				isMethod2Commited = districtService.publishSubdistrit(listdistritNew.get(i), session);
			}
			tx.commit();
			session.close();

			for (int i = 0; i < listVillageModify.size(); i++) {
				isMethod3Commited = villageService.modifyVillageInfo((listVillageModify.get(i)), request, httpSession);
			}

			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true) {
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
	}

	@Override
	public int findDuplicate(String stateName) throws Exception {
		return stateDao.findDuplicate(stateName);
	}

	@Override
	public List<State> getAllNotInStates(String stateList) throws Exception {
		List<State> state = null;
		try {
			state = stateDao.getAllNotInStates(stateList);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return state;
	}

	@Override
	public List<AssemblyConstituency> getAssemblySourceList() throws Exception {
		List<AssemblyConstituency> assenbconst = null;
		try {
			assenbconst = stateDao.getAllassembly();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return assenbconst;

	}

	/* Retrieve files from the attachment table */
	@Override
	public List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws Exception {
		return stateDao.getAttacmentdetail(orderCode);
	}

	/* HELP */
	@Override
	public List<GeneratedFileDetails> getCBTHtml(String fName, String documentType, int documentId) throws Exception {
		return stateDao.getCBTHtml(fName, documentType, documentId);
	}

	@Override
	public List<State> getDistrict(String query) throws Exception {
		List<State> state = null;
		try {
			state = stateDao.getDistrictViewList(query);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return state;
	}

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCode(int stateCode) throws Exception {
		List<District> district = null;
		try {
			district = sessionFactory.openSession().createQuery("from District d where d.isactive='true' and d.state.statePK.stateCode=" + stateCode + "order by d.districtNameEnglish").list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return district;
	}

	@Override
	public List<District> getDistricts(String stateCode) throws Exception {
		// System.out.println("State code is" + stateCode);
		String selectedCoveredLandRegionByULB = stateCode;
		List<District> fullSubdistrictContributingList = null;
		fullSubdistrictContributingList = new ArrayList<District>();
		List<District> partSubdistrictContributingList = null;
		partSubdistrictContributingList = new ArrayList<District>();

		List<District> partStateContributingList = null;

		List<District> coveredAreaListFull = null;
		coveredAreaListFull = new ArrayList<District>();
		try {

			District district = null;
			int stateCodeNew = 0;

			String[] temp = selectedCoveredLandRegionByULB.split(",");
			for (int i = 0; i < temp.length; i++) {
				// System.out.println(temp[i]);
				if (temp[i].contains("FULL")) {
					stateCodeNew = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
					// System.out.println("wewewewe");
					fullSubdistrictContributingList = stateDao.getdistrictListbyDistrict(stateCodeNew);
					for (int j = 0; j < fullSubdistrictContributingList.size(); j++) {
						district = new District();

						district.setDistrictCode(fullSubdistrictContributingList.get(j).getDistrictCode());
						district.setDistrictNameEnglish(fullSubdistrictContributingList.get(j).getDistrictNameEnglish());
						district.setDistrictNameLocal(fullSubdistrictContributingList.get(j).getDistrictNameLocal());
						coveredAreaListFull.add(j, district);
					}
				}

				if (temp[i].contains("PART")) {
					String tmp = temp[i].substring(0, temp[i].length() - 4);
					stateCodeNew = Integer.parseInt(tmp);

					partSubdistrictContributingList = stateDao.getdistrictListbyDistrict(stateCodeNew);
					for (int j = 0; j < partSubdistrictContributingList.size(); j++) {
						district = new District();
						district.setDistrictCode(partSubdistrictContributingList.get(j).getDistrictCode());
						district.setDistrictNameEnglish(partSubdistrictContributingList.get(j).getDistrictNameEnglish());
						district.setDistrictNameLocal(partSubdistrictContributingList.get(j).getDistrictNameLocal());
						coveredAreaListFull.add(j, district);
					}
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return coveredAreaListFull;
	}

	@Override
	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			return stateDao.getEntityWiseMapDetails(entityType, entityCode, session);
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
	public List<GovernmentOrder> getGovermentOrderDetail(int orderCode) throws Exception {
		List<GovernmentOrder> govOrderDetail = null;
		try {
			govOrderDetail = stateDao.getGovermentOrderDetail(orderCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return govOrderDetail;
	}

	/* Added by sushil on 19-11-2012 */
	@Override
	public List<StateDataForm> getGovtOrderByEntityCode(int entityCode, char type) {
		List<StateDataForm> fileList = null;
		List<Object[]> list = stateDao.getGovtOrderByEntityCode(entityCode, type);
		if (!list.isEmpty()) {
			fileList = new ArrayList<StateDataForm>();
			for (Object[] object : list) {
				StateDataForm dataForm = new StateDataForm();
				dataForm.setFilelocation(object[1].toString());
				dataForm.setFileName(object[0].toString());
				fileList.add(dataForm);
			}
		}
		return fileList;
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
			/*
			 * System.out .println("Download Directory All StateRpt" +
			 * e.toString());
			 */
			headquartersDetails = null;
			tx.rollback();
		} finally {
			if (hsession != null && hsession.isOpen()) {
				hsession.close();
			}

		}
		return headquartersDetails;
	}

	@Override
	public List<Headquarters> getHeadquterDetailALL(Integer regionCode, String region_type) throws Exception {
		Session hsession = null;
		hsession = sessionFactory.openSession();
		Transaction tx = hsession.beginTransaction();
		String HQL;
		List<Headquarters> headquartersDetails = new ArrayList<Headquarters>();
		try {
			HQL = "from Headquarters where isactive=true and lrlc=" + regionCode + " and regionType='" + region_type + "'";
			headquartersDetails = headquartersDAO.getHeadquartersDetailsDAO(HQL, hsession);

			tx.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			/*
			 * System.out .println("Download Directory All StateRpt" +
			 * e.toString());
			 */
			headquartersDetails = null;
			tx.rollback();
		} finally {
			if (hsession != null && hsession.isOpen()) {
				hsession.close();
			}

		}
		return headquartersDetails;
	}

	@Override
	public List<MapLandRegion> getMapDetailsModify(int mapLandregionCode) throws Exception {
		List<MapLandRegion> mapDetail = null;
		try {
			mapDetail = stateDao.getMapDetailsModify(mapLandregionCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return mapDetail;
	}

	@Override
	public List<AssemblyConstituency> getParliamentSource(int stateCode) throws Exception {
		List<AssemblyConstituency> assenbconst = null;
		try {
			assenbconst = stateDao.getAllparliamentassembly(stateCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return assenbconst;
	}

	@Override
	public List<ParliamentConstituency> getParliamentSourceList(int stateCode) throws Exception {
		List<ParliamentConstituency> parliamentconst = null;
		try {
			parliamentconst = stateDao.getAllparliament(stateCode);
		} catch (Exception e) {
			throw e;
		}
		return parliamentconst;
	}

	@Override
	public List<District> getPartDistricts(String stateCode) throws Exception {
		// System.out.println("State code is" + stateCode);
		String selectedCoveredLandRegionByULB = stateCode;
		List<District> fullSubdistrictContributingList = null;
		fullSubdistrictContributingList = new ArrayList<District>();
		List<District> partSubdistrictContributingList = null;
		partSubdistrictContributingList = new ArrayList<District>();

		List<District> partStateContributingList = null;

		List<District> coveredAreaListFull = null;
		coveredAreaListFull = new ArrayList<District>();
		try {

			District district = null;
			int stateCodeNew = 0;

			String[] temp = selectedCoveredLandRegionByULB.split(",");
			for (int i = 0; i < temp.length; i++) {
				// System.out.println(temp[i]);
				if (!temp[i].contains("FULL")) {
					String tmp = temp[i].substring(0, temp[i].length() - 4);
					stateCodeNew = Integer.parseInt(tmp);

					partSubdistrictContributingList = stateDao.getdistrictListbyDistrict(stateCodeNew);
					for (int j = 0; j < partSubdistrictContributingList.size(); j++) {
						district = new District();
						district.setDistrictCode(partSubdistrictContributingList.get(j).getDistrictCode());
						district.setDistrictNameEnglish(partSubdistrictContributingList.get(j).getDistrictNameEnglish());
						district.setDistrictNameLocal(partSubdistrictContributingList.get(j).getDistrictNameLocal());
						coveredAreaListFull.add(j, district);
					}
				}

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return coveredAreaListFull;
	}

	@Override
	public List<StateDataForm> getStateDetailsModify(int stateCode) throws Exception {

		List<StateDataForm> listStateDetails = null;
		try {
			List<State> stateList = stateDao.getStateDetailsModify(stateCode);
			if (stateList != null && !stateList.isEmpty()) {
				State stateDetails = stateList.get(0);
				Integer stateversion = stateDetails.getStateVersion();
				Integer slc = stateDetails.getSlc();
				Integer minorVersion=stateDetails.getMinorVersion();
				StateDataForm stateDataForm = new StateDataForm();
				stateDataForm.setSlc(slc);
				stateDataForm.setStateOrUt(stateDetails.getStateOrUt());
				String stateNameEnglish = stateDetails.getStateNameEnglish() != null && !stateDetails.getStateNameEnglish().isEmpty() ? stateDetails.getStateNameEnglish().trim() : null;
				stateDataForm.setStateNameEnglish(stateNameEnglish);
				stateDataForm.setStateNameEnglishch(stateNameEnglish);
				stateDataForm.setStateNameLocal(stateDetails.getStateNameLocal() != null && !stateDetails.getStateNameLocal().isEmpty() ? stateDetails.getStateNameLocal().trim() : null);
				stateDataForm.setStateNameLocalch(stateDataForm.getStateNameLocal());
				stateDataForm.setShortName(stateDetails.getShortName() != null && !stateDetails.getShortName().isEmpty() ? stateDetails.getShortName().trim() : null);
				Character stateOrUt = stateDetails.getStateOrUt();
				stateDataForm.setStateOrUt(stateOrUt);
				stateDataForm.setStateOrUtch(stateOrUt);
				stateDataForm.setStateNameLocal(stateDetails.getStateNameLocal() != null && !stateDetails.getStateNameLocal().isEmpty() ? stateDetails.getStateNameLocal().trim() : null);
				stateDataForm.setCensus2001Code(stateDetails.getCensus2001Code());
				stateDataForm.setStateCode(stateDetails.getStateCode());
				stateDataForm.setStateVersion(stateversion);
				stateDataForm.setMinorVersion(minorVersion);
				stateDataForm.setCensus2011Code(stateDetails.getCensus2011Code());
				String Cordinate = null;
				if (stateDetails.getCoordinates() != null) {
					if (!stateDetails.getCoordinates().trim().equals("")) {
						Cordinate = stateDetails.getCoordinates();
					}
				}

				stateDataForm.setCordinate(Cordinate);
				stateDataForm.setLrReplacedby(stateDetails.getLrReplacedby());// Check
				stateDataForm.setLrReplaces(stateDetails.getLrReplaces()); // Check
				//stateDataForm.setMapCode(stateDetails.getMapCode());
				stateDataForm.setWarningflag(stateDetails.getWarningflag());
				stateDataForm.setOrdereffectiveDate(stateDetails.getEffectiveDate());
				stateDataForm.setOrdereffectiveDatecr(stateDetails.getEffectiveDate());
				List<Headquarters> headquartersDetails = this.getHeadquterDetails(slc, "S");
				if (headquartersDetails != null && !headquartersDetails.isEmpty()) {
					Headquarters headquarters = headquartersDetails.get(0);
					stateDataForm.setHeadquarterName(headquarters.getHeadquarterNameEnglish() != null && !headquarters.getHeadquarterNameEnglish().isEmpty() ? headquarters.getHeadquarterNameEnglish().trim() : null);
					stateDataForm.setHeadquarterNameLocal(headquarters.getHeadquarterLocalName() != null && !headquarters.getHeadquarterLocalName().isEmpty() ? headquarters.getHeadquarterLocalName().trim() : null);
				}

				List<GetGovernmentOrderDetail> govtOrderValue = districtDAO.GovOrderDetail('S', stateCode, stateversion,minorVersion);
				if (govtOrderValue != null && !govtOrderValue.isEmpty()) {
					GetGovernmentOrderDetail governmentOrder = govtOrderValue.get(0);
					stateDataForm.setOrderCodecr(governmentOrder.getOrderCode());
					stateDataForm.setOrderNocr(governmentOrder.getOrderNo().trim());
					stateDataForm.setOrderDatecr(governmentOrder.getOrderDate());
					stateDataForm.setGazPubDatecr(governmentOrder.getGazPubDate());

				}

				listStateDetails = new ArrayList<StateDataForm>();
				listStateDetails.add(stateDataForm);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return listStateDetails;
	}

	@Override
	public List<StateHistory> getStateHistoryDetail(char stateNameEnglish, int stateCode) throws Exception {

		List<StateHistory> StateHistory = new ArrayList<StateHistory>();
		try {
			StateHistory = stateDao.getStateHistoryDetail(stateNameEnglish, stateCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return StateHistory;

	}

	@Override
	public List<Search> getStateSearchDetail(String entityName, String entityCode) throws Exception {
		List<Search> SearchState = new ArrayList<Search>();
		try {
			SearchState = stateDao.getStateSearchDetail(entityName, entityCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return SearchState;
	}

	@Override
	public List<State> getStateSourceList() throws Exception {
		return stateDao.getAllStates();
	}

	@Override
	public List<State> getStateSourceList(int stateCode) throws Exception {
		List<State> state = null;
		try {
			state = stateDao.getAllStates(stateCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return state;
	}

	@Override
	public List<State> getStateTargetList(int stateCode) throws Exception {
		List<State> state = null;
		try {
			state = stateDao.getStateTargetList(stateCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return state;

	}

	@SuppressWarnings("unchecked")
	public int getStateVersion(int districtCode) {
		int distVers = 0;
		try {
			distVers = Integer.parseInt(sessionFactory.openSession().createQuery("select stateVersion from State where isactive=true and stateCode=" + districtCode).list().get(0).toString());
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		return distVers;

	}

	@Override
	public List<State> getStateViewList(StateDataForm stateView) throws Exception {
		List<State> state = null;
		try {

			String stateName = stateView.getStateNameEnglish();
			String stateCode = null;
			if (stateName == null) {
				stateCode = stateView.getCode();
			}
			String query = null;

			/*
			 * if (stateName == null) { query =
			 * "from State d where d.isactive=true and d.statePK.stateCode=" +
			 * stateCode; } else { if (!stateName.equals("") &&
			 * stateView.getCode().equals("")) { query =
			 * "from State d where d.isactive=true and upper(d.stateNameEnglish) like '"
			 * + stateName.trim().toUpperCase() + "%'"; } else if
			 * (!stateName.equals("") && !stateView.getCode().equals("")) {
			 * query =
			 * "from State d where d.isactive=true and d.statePK.stateCode=" +
			 * stateView.getCode() + " and upper(d.stateNameEnglish) like '" +
			 * stateName.trim().toUpperCase() + "%'"; } else if
			 * (stateName.equals("") && !stateView.getCode().equals("")) { query
			 * = "from State d where d.isactive=true And d.statePK.stateCode=" +
			 * stateView.getCode() + " order by stateNameEnglish"; } else if
			 * (stateName != null) { query =
			 * "from State d where d.isactive=true order by stateNameEnglish"; }
			 * 
			 * }
			 */
			/*
			 * query =
			 * "select new State(d.stateCode,d.stateNameEnglish,d.stateNameLocal) from State d where d.isactive=true order by d.stateNameEnglish"
			 * ; state = stateDao.getStateViewList(query);
			 */

			/* Changed by Anju Gupta on 13/03/2015 */

			query = " select d.state_code as stateCode, d.state_name_english as stateNameEnglish, d.state_name_local as stateNameLocal," + " (select l.ismapupload from configuration_map_landregion m, configuration_map_landregion_level l"
					+ " where m.configuration_map_landregion_code = l.configuration_map_landregion_code and m.isactive and l.isactive" + " and m.slc=d.state_code and l.landregion_type='S') as mapupload"
					+ ", CAST(d.census_2001_code AS varchar(2)) as census2001Code,d.Census_2011_code as census2011Code," + "COALESCE(A.file_location,'')  as fileLocation ,COALESCE(A.file_timestamp,'')  as fileName " + " from State d "
					+ " LEFT OUTER JOIN  government_order_entitywise Ge ON (d.state_code =Ge.entity_code  " + "AND d.state_version=Ge.entity_version and Ge.entity_type='S' )	" + "LEFT OUTER JOIN  attachment A ON ge.order_code=A.announcement_id "
					+ "where d.isactive=true order by d.state_Name_English";
			state = stateDao.getStateViewListSQL(query);

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return state;
	}

	@SuppressWarnings("unchecked")
	public int getSubDistrictVersion(int districtCode) throws Exception {
		int distVers = 0;
		List<District> dist = null;
		try {
			dist = sessionFactory.openSession().createQuery("from District d where isactive=true and districtCode=" + districtCode).list();
			distVers = dist.get(0).getDistrictPK().getDistrictVersion();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return distVers;
	}

	@Override
	public boolean modifyStateCrInfo(StateForm stateForm, HttpServletRequest request, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList, boolean delflag, String deleteAttachmentId[], HttpSession httpSession)
			throws Exception {

		Session session1 = sessionFactory.openSession();
		Transaction tx1 = session1.beginTransaction();

		try {

			List<StateDataForm> liststate = stateForm.getListStateDetails();
			
			if (!liststate.isEmpty()) {
				StateDataForm element = liststate.get(0);
				Integer stateCode = element.getStateCode();
				Integer stateVersion = element.getStateVersion();
				Integer minorVersion=element.getMinorVersion();
				StatePK statePK = new StatePK();
				statePK.setStateCode(stateCode);
				statePK.setStateVersion(stateVersion);
				statePK.setMinorVersion(minorVersion);

				Date currentDate = dateTimeUtil.getCurrentDate();
				
				Integer orderCode = null;
				SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
				Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;

				String cord1 = "";
				if (stateForm.getLati() != null && !stateForm.getLati().isEmpty() && stateForm.getLongi() != null && !stateForm.getLongi().isEmpty()) {
					if (stateForm.getLati().split(",").length >= 1) {
						String[] tempLati = stateForm.getLati().split(",");
						String[] tempLongi = stateForm.getLongi().split(",");
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
					stateForm.setWarningFlag(false);
				}

				if (cord1 == "") {
					cord1 = null;
				}

				Integer mapCode = element.getMapCode();
				if (delflag == true) {
					govtOrderService.deleteMapAttachementforLandRegion(stateCode, 'S', session1);
					mapCode = null;
				}

				if (attachedMapList != null) {
					mapCode = govtOrderService.saveDatainMapAttachmentforLandregion(attachedMapList, stateCode, 'S', session1);

				}

				State state = (State) session1.get(State.class, statePK);
				state.setShortName(element.getShortName());
				//state.setCensus2011Code(element.getCensus2011Code());
				state.setCoordinates(cord1);
				state.setWarningflag(element.isWarningflag());
				state.setLastupdatedby(userId);
				state.setLastupdated(currentDate);
				//state.setMapCode(mapCode);
				session1.update(state);
				session1.flush();

				if (element.getHeadquarterName() != "" || element.getHeadquarterNameLocal() != "") {
					List<Headquarters> headquarterList = this.getHeadquterDetailALL(element.getSlc(), "S");
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
					headquarters.setRegionType('S');
					headquarters.setLrlc(stateCode);
					headquarters.setHeadquarterNameEnglish(element.getHeadquarterName());
					headquarters.setHeadquarterLocalName(element.getHeadquarterNameLocal());
					headquartersDAO.saveOrUpdate(headquarters, session1);
				}

				GovernmentOrder govtOrder = new GovernmentOrder();
				if (element.getOrderCodecr() != null) {
					govtOrder = govtOrderService.updateGovernmentOrder(element.getOrderNocr(), element.getOrderCodecr(), element.getOrderDatecr(), element.getOrdereffectiveDatecr(), element.getGazPubDatecr(), "update", request, session1);
				} else {

					/*
					 * insert the record into goverment order and goverment
					 * order entity wise table if record is blank for goverment
					 * order
					 * 
					 */if (element.getOrderNocr() != "" && element.getOrderDatecr() != null && element.getOrdereffectiveDatecr() != null) {
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
						orderCode = govtOrder.getOrderCode();

						GovernmentOrderEntityWise governmentOrderEntityWise = new GovernmentOrderEntityWise();
						governmentOrderEntityWise.setEntityCode(stateCode);
						governmentOrderEntityWise.setEntityType('S');
						governmentOrderEntityWise.setEntityVersion(stateVersion);
						governmentOrderEntityWise.setGovernmentOrder(govtOrder);
						session1.saveOrUpdate(governmentOrderEntityWise);
						session1.flush();
					}

				}

				if (deleteAttachmentId != null) {
					govtOrderDAO.deleteAttachmentForLandRegion(deleteAttachmentId, session1);

				}

				if (attachedList != null) {
					villageService.saveDataInAttachment(govtOrder, attachedList, session1);
				}

			}

			tx1.commit();
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

	@Override
	public boolean modifyStateInfo(StateForm stateForm, HttpServletRequest request) throws Exception {
		Session session1 = null;
		Transaction tx1 = null;
		session1 = sessionFactory.openSession();
		tx1 = session1.beginTransaction();
		Integer tid = null, orderCode = null;
		try {
			
			if (stateForm.isCorrection() == true) {
				/*
				 * System.out .println(
				 * "if----------------------------------StateModify------");
				 */
				State stateBean = new State();
				GovernmentOrder governmentOrderbean = new GovernmentOrder();

				List<StateDataForm> listState = new ArrayList<StateDataForm>();
				listState = stateForm.getListStateDetails();
				Iterator<StateDataForm> itr = listState.iterator();
				while (itr.hasNext()) {
					StateDataForm element = itr.next();
					try {
						StatePK statePK = new StatePK();
						statePK.setStateCode(element.getStateCode());
						statePK.setStateVersion(element.getStateVersion());
						stateBean.setStateNameEnglish(element.getStateNameEnglish().trim());
						stateBean.setStateNameLocal(element.getStateNameLocal());
						stateBean.setAliasEnglish(element.getAliasEnglish());
						stateBean.setAliasLocal(element.getAliasLocal());
						stateBean.setCensus2011Code(element.getCensus2011Code());
						String stateOrUt = String.valueOf(element.getStateOrUt());

						//stateBean.setMapCode(element.getMapCode());
						governmentOrderbean.setOrderCode(element.getOrderCodecr());
						governmentOrderbean.setOrderDate(element.getOrderDatecr());
						governmentOrderbean.setEffectiveDate(element.getOrdereffectiveDatecr());
						governmentOrderbean.setGazPubDate(element.getGazPubDatecr());
						governmentOrderbean.setOrderNo(element.getOrderNocr().trim());
						governmentOrderbean.setOrderPath(element.getOrderPathcr());
						// stateBean.setSscode(element.getSscode());
						// System.out.println("StateCode----------"
						// + element.getStateCode());
						stateBean.setStateVersion(element.getStateVersion());
						String longitude = "";
						String latitude = "";
						int mapCode = 0;
						//stateBean.setMapCode(element.getMapCode());
						String cordinate = "";
						stateBean.setIsactive(true);
						if (element.getMapCode() == null) {
							mapCode = 0;
						} else {
							mapCode = element.getMapCode();
						}

						if (element.getLatitude() == null) {
							latitude = "";
						} else {
							latitude = element.getLatitude();
						}
						if (element.getLongitude() == null) {
							latitude = "";
						} else {
							longitude = element.getLongitude();
						}
						cordinate = longitude + ":" + latitude;
						String cord1 = null;
						if (stateForm.getLati().split(",").length > 1) {
							String[] tempLati = stateForm.getLati().split(",");
							String[] tempLongi = stateForm.getLongi().split(",");
							cord1 = tempLati[0] + "," + tempLongi[0] + ":";
							if (tempLati.length > 1) {
								for (int i = 1; i < tempLati.length; i++) {
									cord1 += tempLati[i] + "," + tempLongi[i] + ":";
								}
							}
							cord1 = cord1.substring(0, cord1.length() - 1);
						}
						// System.out.println("Longitude --" + longitude
						// + ":::latitude---" + latitude + "::mapCode--"
						// + mapCode);
						try {
							int map_landRegionCode = 0;
							if (mapCode == 0) {
								map_landRegionCode = stateDao.getMaxStateCode("select COALESCE(max(map_landregion_code),1) from map_landregion");
								// System.out.println("CODE--LandRegion-else
								// if-"
								// + map_landRegionCode);
								if (map_landRegionCode == 0) {
									map_landRegionCode = 1;
								} else {
									map_landRegionCode = map_landRegionCode + 1;
								}
								String cord = null;
								if (stateForm.getLatitude().split(",").length > 1) {
									String[] tempLati = stateForm.getLatitude().split(",");
									String[] tempLongi = stateForm.getLongitude().split(",");
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
									mapLandRegion.setLandregionType('S');
									mapLandRegion.setLandregionVersion(element.getStateVersion());
									mapLandRegion.setCoordinates(cord);
									mapLandRegion.setLandregionCode(element.getStateCode());
									mapLandRegion.setWarningflag(true);
									mapLandRegionDAO.saveMap(mapLandRegion, session1);
									try {
										//stateBean.setMapCode(map_landRegionCode);
										// System.out.println("getMapCode====="
										// + element.getMapCode());
										stateDao.modifyStateInfo(stateForm, statePK, map_landRegionCode, session1);
									} catch (Exception e) {
										log.debug("Exception" + e);
									}
									//
								} catch (Exception e) {
									// TODO Auto-generated catch block
									log.debug("Exception" + e);
								}
							} else {

								stateDao.updateMapLanRegion(mapCode, cord1, element.getStateCode(), session1);

								stateDao.modifyStateInfo(stateForm, statePK, mapCode, session1);
							}
							/* if (element.getOrdereffectiveDate()<) */

							govtOrderService.updateGovernmentOrder(element.getOrderNocr(), element.getOrderCodecr(), element.getOrderDatecr(), element.getOrdereffectiveDatecr(), element.getGazPubDatecr(), "update", element.getOrderPath(),
									stateForm.getFilePathcr(), request);
						}

						catch (Exception e) {
							log.debug("Exception" + e);
						}

					} catch (Exception e) {
						log.debug("Exception" + e);
						// tx.rollback();
						return false;
					}

				}
				return true;
			}

			else if (stateForm.isCorrection() == false) {
				/*
				 * System.out
				 * .println("if----------------------------------OrderNo------"
				 * + stateForm.getOrderNo()); System.out
				 * .println("Elseif----------------------------------------" +
				 * stateForm.getGazPubDate());
				 */

				State sbean = null;
				sbean = new State();

				List<StateDataForm> listState = new ArrayList<StateDataForm>();
				listState = stateForm.getListStateDetails();

				Iterator<StateDataForm> itr = listState.iterator();

				while (itr.hasNext()) {
					StateDataForm element = itr.next();
					int stateVersion = 1;
					stateVersion = stateDao.getMaxVersionbyStateCode(element.getStateCode());
					if (stateVersion == 1) {
						stateVersion = stateVersion + 1;
					} else {
						stateVersion = stateVersion + 1;
					}
					StatePK spk = new StatePK(element.getStateCode(), stateVersion);
					// System.out.println("CODE--StateCode if-"
					// + element.getStateCode());
					// System.out.println("CODE--StateVersion if-" +
					// stateVersion);
					sbean.setStatePK(spk);
					sbean.setStateVersion(stateVersion);

					// /////////////////////
					String stateOrUt = String.valueOf(element.getStateOrUt());
					sbean.setStateNameEnglish(element.getStateNameEnglishch().trim());
					sbean.setStateNameLocal(element.getStateNameLocal());
					sbean.setAliasEnglish(element.getAliasEnglish());
					sbean.setAliasLocal(element.getAliasLocal());
					sbean.setCensus2011Code(element.getCensus2011Code());
					//sbean.setMapCode(element.getMapCode());
					// sbean.setSscode(element.getSscode());
					sbean.setEffectiveDate(timestamp);
					sbean.setLastupdated(timestamp);
					sbean.setCreatedon(timestamp);
					sbean.setCreatedby(1010101);
					sbean.setLastupdatedby(1010101);
					sbean.setIsactive(true);
					try {

						// System.out.println("in save try block");

						spk.setStateVersion(stateVersion);
						String data = stateDao.ChangeCrState(element.getStateCode(), element.getStateNameEnglish(), element.getStateNameEnglishch(), stateForm.getUserId(), element.getOrderNocr(), element.getOrderDatecr(), element.getGazPubDatecr(),
								element.getOrdereffectiveDatecr(), element.getOrderPath(), stateOrUt, element.getStateNameLocal(), element.getShortName(), session1, stateForm.getStateCode());

						if (data != null) {
							String temp[] = data.split(",");
							tid = Integer.parseInt(temp[0]);
							orderCode = Integer.parseInt(temp[1]);

							String statecode = Integer.toString(element.getStateCode());
							/*
							 * System.out .println("State Code for gov" +
							 * statecode);
							 */
							// System.out.println("Order Code for
							// gov---"+element.getOrderCode());
							/*
							 * govtOrderService.saveGovernmentOrder(
							 * stateForm.getOrderNo(), stateForm.getOrderDate(),
							 * stateForm.getOrdereffectiveDate(),
							 * stateForm.getGazPubDate(), "Modify",
							 * stateForm.getOrderPath
							 * (),stateForm.getFilePath(),request);
							 * System.out.println ("Order Code********"
							 * +element.getOrderCodecr());
							 */
							// int ordCode =
							// stateDao.getMaxStateCode("select max(order_code)
							// from GovernmentOrder");
							// System.out.println("Max Order Code--"+ordCode);

							// new start
							List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
							/*
							 * ================Getting the values from
							 * Application and Setting IN
							 * AddAttachmentBeanClass==================
							 */

							AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
							addAttachmentBean.setCurrentlyUploadFileList(stateForm.getAttachedFile());// 1.List
																										// Of
																										// File
																										// To
																										// Be
																										// Upload.
							addAttachmentBean.setUploadLocation(stateForm.getUploadLocation());// 2.Location
																								// For
																								// File
																								// Upload
																								// In
																								// File
																								// System.
							addAttachmentBean.setAllowedTotalNoOfAttachment(stateForm.getAllowedNoOfAttachment());// 3.Allowed
																													// Total
																													// Number
																													// Of
																													// Attachment.
							addAttachmentBean.setAllowedTotalFileSize(stateForm.getAllowedTotalFileSize());// 4.Allowed
																											// Total
																											// File
																											// Size.
							addAttachmentBean.setAllowedIndividualFileSize(stateForm.getAllowedIndividualFileSize());// 5.Allowed
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

							/*
							 * GovernmentOrder govtOrder = saveDataInGovtOrder(
							 * stateForm, session1);
							 * saveDataInAttachment(govtOrder, stateForm,
							 * metaInfoOfToBeAttachedFileList, session1);
							 * shiftService
							 * .saveDataInGovtOrderEntityWise(govtOrder,
							 * statecode, 'S', session1);
							 */
							// new end

							// stateDao.SetGovermentOrderEntity(statecode,'S');
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.debug("Exception" + e);
						return false;
					}
				}
			}
			tx1.commit();
			char t_type = 'S';
			EmailSmsThread est = new EmailSmsThread(tid, t_type, emailService);
			est.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			return false;
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
				
			}
		}
		return true;
	}

	@Override
	public boolean modifyStateInfoNew(StateForm stateForm, List<AttachedFilesForm> attachedList, HttpServletRequest request) throws Exception {
		Session session1 = null;
		Transaction tx1 = null;
		session1 = sessionFactory.openSession();
		tx1 = session1.beginTransaction();
		Integer tid = null, orderCode = null;
		try {
			
			if (stateForm.isCorrection() == true) {
				State stateBean = new State();
				GovernmentOrder governmentOrderbean = new GovernmentOrder();

				List<StateDataForm> listState = stateForm.getListStateDetails();
				if (listState != null && !listState.isEmpty()) {
					StateDataForm stateDetails = listState.get(0);
					StatePK statePK = new StatePK();
					statePK.setStateCode(stateDetails.getStateCode());
					statePK.setStateVersion(stateDetails.getStateVersion());
					stateBean.setStateNameEnglish(stateDetails.getStateNameEnglishch() != null && !stateDetails.getStateNameEnglishch().isEmpty() ? stateDetails.getStateNameEnglishch().trim() : null);
					stateBean.setStateNameLocal(stateDetails.getStateNameLocal());
					stateBean.setAliasEnglish(stateDetails.getAliasEnglish());
					stateBean.setAliasLocal(stateDetails.getAliasLocal());
					stateBean.setCensus2011Code(stateDetails.getCensus2011Code());
					//stateBean.setMapCode(stateDetails.getMapCode());
					stateBean.setStateVersion(stateDetails.getStateVersion());
				//	stateBean.setMapCode(stateDetails.getMapCode());
					stateBean.setIsactive(true);

					governmentOrderbean.setOrderCode(stateDetails.getOrderCodecr());
					governmentOrderbean.setOrderDate(stateDetails.getOrderDatecr());
					governmentOrderbean.setEffectiveDate(stateDetails.getOrdereffectiveDatecr());
					governmentOrderbean.setGazPubDate(stateDetails.getGazPubDatecr());
					governmentOrderbean.setOrderNo(stateDetails.getOrderNocr().trim());
					governmentOrderbean.setOrderPath(stateDetails.getOrderPathcr());

					String longitude = stateDetails.getLongitude() != null && !stateDetails.getLongitude().isEmpty() ? stateDetails.getLongitude() : "";
					String latitude = stateDetails.getLatitude() != null && !stateDetails.getLatitude().isEmpty() ? stateDetails.getLatitude() : "";
					int mapCode = stateDetails.getMapCode() != null ? stateDetails.getMapCode() : 0;

					String cordinate = longitude + ":" + latitude;
					String cord1 = null;
					if (stateForm.getLati().split(",").length > 1) {
						String[] tempLati = stateForm.getLati().split(",");
						String[] tempLongi = stateForm.getLongi().split(",");
						cord1 = tempLati[0] + "," + tempLongi[0] + ":";
						if (tempLati.length > 1) {
							for (int i = 1; i < tempLati.length; i++) {
								cord1 += tempLati[i] + "," + tempLongi[i] + ":";
							}
						}
						cord1 = cord1.substring(0, cord1.length() - 1);
					}

					String cord = null;
					if (stateForm.getLatitude().split(",").length > 1) {
						String[] tempLati = stateForm.getLatitude().split(",");
						String[] tempLongi = stateForm.getLongitude().split(",");
						cord = tempLati[0] + "," + tempLongi[0] + ":";
						if (tempLati.length > 1) {
							for (int i = 1; i < tempLati.length; i++) {
								cord += tempLati[i] + "," + tempLongi[i] + ":";
							}
						}
						cord = cord.substring(0, cord.length() - 1);
					}

					if (mapCode != 0) {
						stateDao.updateMapLanRegion(mapCode, cord1, stateDetails.getStateCode(), session1);
						stateDao.modifyStateInfo(stateForm, statePK, mapCode, session1);
					} else {
						int map_landRegionCode = stateDao.getMaxStateCode("select COALESCE(max(map_landregion_code),1) from map_landregion");
						map_landRegionCode = map_landRegionCode == 0 ? 1 : ++map_landRegionCode;
						Timestamp time = CurrentDateTime.getCurrentTimestamp();
						MapLandRegion mapLandRegion = new MapLandRegion();
						mapLandRegion.setEffectiveDate(CurrentDateTime.getDate("2011-12-12"));
						mapLandRegion.setCreatedon(time);
						mapLandRegion.setCreatedby(100);
						mapLandRegion.setLandregionType('S');
						mapLandRegion.setLandregionVersion(stateDetails.getStateVersion());
						mapLandRegion.setCoordinates(cord);
						mapLandRegion.setLandregionCode(stateDetails.getStateCode());
						mapLandRegion.setWarningflag(true);
						mapLandRegionDAO.saveMap(mapLandRegion, session1);
						//stateBean.setMapCode(map_landRegionCode);
						stateDao.modifyStateInfo(stateForm, statePK, map_landRegionCode, session1);

					}

					govtOrderService.updateGovernmentOrder(stateDetails.getOrderNocr(), stateDetails.getOrderCodecr(), stateDetails.getOrderDatecr(), stateDetails.getOrdereffectiveDatecr(), stateDetails.getGazPubDatecr(), "update",
							stateDetails.getOrderPath(), stateForm.getFilePathcr(), request);
				}

				tx1.commit();
				return true;
			} else if (stateForm.isCorrection() == false) {
				List<StateDataForm> listState = stateForm.getListStateDetails();
				if (listState != null && !listState.isEmpty()) {
					StateDataForm stateDetails = listState.get(0);
					String stateOrUt = String.valueOf(stateDetails.getStateOrUtch()); // change
																						// from
																						// stateOrUt
																						// to
																						// stateOrUtch
																						// to
																						// get
																						// change
																						// value
					String data = stateDao.ChangeCrState(stateDetails.getStateCode(), stateDetails.getStateNameEnglish(), stateDetails.getStateNameEnglishch(), stateForm.getUserId(), stateDetails.getOrderNocr(), stateDetails.getOrderDatecr(),
							stateDetails.getOrdereffectiveDatecr(), stateDetails.getGazPubDatecr(), stateDetails.getOrderPath(), stateOrUt, stateDetails.getStateNameLocal(), stateDetails.getShortName(), session1, stateForm.getStateCode());

					boolean flag = false;
					if (data != null) {
						String temp[] = data.split(",");
						tid = Integer.parseInt(temp[0]);
						orderCode = Integer.parseInt(temp[1]);
						if (orderCode != null) {
							flag = convertLocalbodyService.saveDataInAttachmentWithOrderCode(orderCode, attachedList, session1);
						}
						if (flag) {
							tx1.commit();
							char t_type = 'S';
							EmailSmsThread est = new EmailSmsThread(tid, t_type, emailService);
							est.start();
						} else
							tx1.rollback();
					}

				}
			}

		} catch (Exception e) {

			log.debug("Exception" + e);
			tx1.rollback();
			throw new SQLException("error in calling function"); // changed by
																	// kirandeep
																	// and
																	// Sushish
																	// shakya
																	// 15/10/2014
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
				
			}
		}
		return true;
	}

	@Override
	public String openFile(String filePath) throws IOException {
		return CommonUtil.isfileExist(filePath);
	}

	/*
	 * public boolean publishSubDistrict(SubDistrictForm sdForm, int sdCode, int
	 * sdVersion, int mapCd, Session session) { int distVersion=0;
	 * distVersion=this.getSubDistrictVersion(sdForm.getDistrictCode());
	 * DistrictPK dPK=new DistrictPK(sdForm.getDistrictCode(),distVersion);
	 * District dist=new District(); SubdistrictPK sdpk = new
	 * SubdistrictPK(sdCode, sdVersion); Subdistrict sdbean = new Subdistrict();
	 * dist.setDistrictPK(dPK); sdbean.setSubdistrictPK(sdpk);
	 * 
	 * sdbean.setSubdistrictNameEnglish(sdForm.getSubdistrictNameEnglish());
	 * sdbean.setSubdistrictNameLocal(sdForm.getSubdistrictNameLocal());
	 * sdbean.setAliasEnglish(sdForm.getAliasEnglish());
	 * sdbean.setAliasLocal(sdForm.getAliasLocal());
	 * sdbean.setCensus2011Code(Integer.parseInt(sdForm.getCensus2011Code()));
	 * sdbean.setMapLandregionCode(sdForm.getMapCode());
	 * sdbean.setSscode(sdForm.getSscode()); sdbean.setEffectiveDate(timestamp);
	 * sdbean.setLastupdated(timestamp); sdbean.setCreatedon(timestamp);
	 * sdbean.setCreatedby(1010101); sdbean.setLastupdatedby(1010101);
	 * sdbean.setIsactive(true); sdbean.setMapLandregionCode(mapCd);
	 * sdbean.setDistrict(dist); subdistrictDAO.publishSubDistrict(sdbean,
	 * session); return true; }
	 */
	/*public boolean publishDistrict(StateForm sdForm, int sdCode, int sdVersion, int mapCd, Session session) throws Exception {
		int stateCode = 20;
		// stateCode = Integer.parseInt(sdForm.getStateNameEnglishMain());

		// System.out.println("code is" + stateCode);
		District newDistrict = new District();
		newDistrict.setAliasEnglish(sdForm.getDistrictAliasInEn());
		newDistrict.setAliasLocal(sdForm.getDistrictAliasInLcl());
		newDistrict.setCensus2001Code("x");
		newDistrict.setCensus2011Code("001");
		newDistrict.setCreatedby(1);
		newDistrict.setCreatedon(timestamp);
		newDistrict.setDistrictNameEnglish(sdForm.getDistrictNameInEn());
		newDistrict.setDistrictNameLocal(sdForm.getDistrictNameInLcl());
		newDistrict.setEffectiveDate(timestamp);
		newDistrict.setIsactive(true);
		newDistrict.setLastupdated(timestamp);
		newDistrict.setLastupdatedby(12);
		newDistrict.setMapCode(mapCd);
		newDistrict.setSscode(sdForm.getStateSpecificCode());
		State state = null;
		StatePK statepk = null;
		state = new State();
		statepk = new StatePK(stateCode, 1);
		state.setStatePK(statepk);
		// newDistrict.setState(state);
		// newDistrict.setFlagCode(flagCode);
		SubdistrictPK spk = new SubdistrictPK(11, 1);
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
		DistrictPK DistrictPK = new DistrictPK(sdCode, sdVersion);
		newDistrict.setDistrictPK(DistrictPK);
		districtDAO.saveDistrict(newDistrict, session);
		return true;
	}*/

	public int publishMapLandRegion(StateForm sdForm, int sdCode, int sdVersion, Session session) throws Exception {
		// System.out.println("::::::::::::::Persisting Map Details");
		String cord;
		cord = sdForm.getLatitude() + ":" + sdForm.getLongitude();
		// MultipartFile filename=null;
		// filename=sdForm.getFilePathMapUpLoad();
		// writeMap(filename,request);
		// String
		// filePath=request.getRealPath("/")+filename.getOriginalFilename() ;

		// System.out.println("Filepath will display
		// here-MAPlandregion--"+filePath);
		try {
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
			log.debug("Exception" + e);
		}
		return mapCd;
	}

	@Override
	public boolean publishState(StateForm stateForm, int sdCode, int sdVersion, int mapCd, Session session) throws Exception {
		State newState = new State();
		StatePK statePK = null;
		newState.setAliasEnglish(stateForm.getDistrictAliasInEn());
		newState.setAliasLocal(stateForm.getDistrictAliasInLcl());
		newState.setCensus2001Code("a");
		newState.setCensus2011Code("02");
		newState.setCreatedby(1);
		newState.setCreatedon(timestamp);
		Set<District> sd = new HashSet<District>();
		District district = new District();
		sd.add(district);
		// newState.setDistricts(sd);
		newState.setEffectiveDate(timestamp);
		newState.setIsactive(true);
		newState.setLastupdated(timestamp);
		newState.setLastupdated(timestamp);
		newState.setLrReplaces(null);
		newState.setLrReplacedby(null);
		//newState.setMapCode(mapCd);
		newState.setShortName("s");
		newState.setStateCode(sdCode);
		newState.setStateNameEnglish(stateForm.getDistrictNameInEn());
		newState.setStateNameLocal(stateForm.getDistrictNameInLcl());
		newState.setStateOrUt('S');
		statePK = new StatePK(sdCode, sdVersion);
		newState.setStatePK(statePK);
		stateDao.saveState(newState, session);
		// TODO Auto-generated method stub

		return true;
	}

	/*public boolean publishSubDistrict(DistrictForm sdForm, int sdCode, int sdVersion, int mapCd, Session session) throws Exception {

		int distVersion = 0;
		distVersion = this.getSubDistrictVersion(sdForm.getDistrictCode());
		DistrictPK dPK = new DistrictPK(sdForm.getDistrictCode(), distVersion);
		District dist = new District();
		SubdistrictPK sdpk = new SubdistrictPK(sdCode, sdVersion);
		Subdistrict sdbean = new Subdistrict();
		dist.setDistrictPK(dPK);
		sdbean.setSubdistrictPK(sdpk);

		sdbean.setSubdistrictNameEnglish("asas");
		sdbean.setSubdistrictNameLocal("sdsdsd");
		sdbean.setAliasEnglish("sdsdsd");
		sdbean.setAliasLocal("sdsds");
		sdbean.setCensus2011Code("00002");
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
	}
*/
	/*public boolean publishSubDistrict(StateForm sdForm, int sdCode, int sdVersion, int mapCd, Session session) throws Exception {
		int distVersion = 0;
		distVersion = this.getSubDistrictVersion(sdForm.getDistrictCode());
		DistrictPK dPK = new DistrictPK(sdForm.getDistrictCode(), distVersion);
		District dist = new District();
		SubdistrictPK sdpk = new SubdistrictPK(sdCode, sdVersion);
		Subdistrict sdbean = new Subdistrict();
		try {
			dist.setDistrictPK(dPK);
			sdbean.setSubdistrictPK(sdpk);

			sdbean.setSubdistrictNameEnglish("asas");
			sdbean.setSubdistrictNameLocal("sdsdsd");
			sdbean.setAliasEnglish("sdsdsd");
			sdbean.setAliasLocal("sdsds");
			sdbean.setCensus2011Code("00002");
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
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}*/

	@Override
	public boolean reOrganize(StateForm SDForm, List<VillageForm> listNewSubdist, HttpServletRequest request, HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		Session session = null;
		Transaction tx = null;
		try {
			for (int i = 0; i < listNewSubdist.size(); i++) {
				isMethod1Commited = villageService.modifyVillageInfo((listNewSubdist.get(i)), request, httpSession);
			}
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			isMethod2Commited = this.saveState(SDForm, session);
			if (isMethod1Commited == true && isMethod2Commited == true) {
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

	@Override
	public boolean reOrganizedistrict(StateForm SDForm, List<DistrictForm> listdistritNew) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {
			// while(isMethod1Commited==true){
			for (int i = 0; i < listdistritNew.size(); i++) {
				listdistritNew.get(i).setDistrictCode((districtCode));
				// listNewVillForm.get(i).setSubdistrictNameEnglish(String.valueOf(districtCode));
				// listNewVillForm.get(i).setCreateFromExistingVillages(true);
				isMethod2Commited = districtService.publishSubdistrit(listdistritNew.get(i), session);
				// }
			}

			/*
			 * if(listSDForm.size()>0){ session = null; tx = null;
			 * session=sessionFactory.openSession(); tx=
			 * session.beginTransaction(); for(int i=0;
			 * i<listSDForm.size();i++){
			 * isMethod3Commited=this.renameContributedSD(listSDForm.get(i),
			 * session); }
			 */
			tx.commit();
			session.close();

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
	}

	@Override
	public boolean reOrganizeDistrictModify(StateForm SDForm, List<DistrictForm> listNewSubdist, HttpServletRequest request, HttpSession httpSession) throws Exception {

		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		for (int i = 0; i < listNewSubdist.size(); i++) {
			isMethod4Commited = districtService.modifyDistrictInfo((listNewSubdist.get(i)), request, httpSession);

		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// isMethod1Commited = this.saveState(SDForm, session);
			tx.commit();

			session.close();

			if (isMethod4Commited == true) {
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

	/*@Override
	public boolean reOrganizeModify(StateForm SDForm, List<SubDistrictForm> listNewSubdist, HttpServletRequest request, HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		for (int i = 0; i < listNewSubdist.size(); i++) {
			isMethod4Commited = SubistrictService.modifySubDistrictInfo((listNewSubdist.get(i)), request, httpSession);// /modify
																														// by
																														// vanisha
		}
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			isMethod1Commited = this.saveState(SDForm, session);
			tx.commit();

			tx.commit();
			session.close();

			if (isMethod4Commited == true) {
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
	public boolean reOrganizesubdistrict(StateForm SDForm, List<SubDistrictForm> listNewVillForm) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {

			for (int i = 0; i < listNewVillForm.size(); i++) {
				listNewVillForm.get(i).setDistrictCode(String.valueOf(districtCode));
				isMethod2Commited = SubistrictService.publish(listNewVillForm.get(i), session);
			}
			tx.commit();
			session.close();
			if (isMethod2Commited == true) {
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

	@Override
	public boolean reOrganizeVillage(StateForm SDForm, List<VillageForm> listNewVillForm, HttpServletRequest request) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			isMethod1Commited = this.saveState(SDForm, session);
			tx.commit();
			isCommited = true;

			try {

				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				for (int i = 0; i < listNewVillForm.size(); i++) {

					listNewVillForm.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
					listNewVillForm.get(i).setCreateFromExistingVillages(true);
					isMethod3Commited = villageService.addVillage(listNewVillForm.get(i));
				}

				tx.commit();
				session.close();

				/*
				 * if(listSDForm.size()>0){ session = null; tx = null;
				 * session=sessionFactory.openSession(); tx=
				 * session.beginTransaction(); for(int i=0;
				 * i<listSDForm.size();i++){
				 * isMethod3Commited=this.renameContributedSD(listSDForm.get(i),
				 * session); } tx.commit(); session.close(); }
				 */
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
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return isCommited;
	}

	@Override
	public boolean save(StateForm sdForm) {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {
			isMethod1Commited = this.saveState(sdForm, session);
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
	}

	public boolean save(SubDistrictForm sdForm) throws Exception {
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

	@Override
	public boolean saveDataInAttach(StateForm stateForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception {
		return stateDao.saveDataInAttach(stateForm, attachedList, session);
	}

	@Override
	public boolean saveDataInAttachMap(StateForm stateForm, List<AttachedFilesForm> attachedList, HttpSession session) throws Exception {
		return stateDao.saveDataInAttachMap(stateForm, attachedList, session);
	}

	@Override
	public void saveDataInAttachment(GovernmentOrder govtOrder, StateForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws Exception {

		Attachment attachment = null;
		try {
			Iterator<AttachedFilesForm> it = attachedList.iterator();
			while (it.hasNext()) {
				AttachedFilesForm filesForm = it.next();
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
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}

	@Override
	public GovernmentOrder saveDataInGovtOrder(StateForm govtForm, Session session) throws Exception {
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

	public void saveHeadquarters(StateForm sdForm, int maxHCode, int regionCode, int regionVersion, Session session) throws Exception {

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
			log.debug("Exception" + e);
		}
	}

	/*@Override
	public boolean saveNewDistrictVersion(int stateCodeNew, int stateCode, int sVersion, Session session) throws Exception {
		DistrictPK spdk = null;
		spdk = null;
		try {
			List<District> listdistrict = new ArrayList<District>();
			String DistrictInactive = "update district set isactive = '" + false + "' where state_code = " + stateCode + " and isactive='true'";
			try {
				districtDAO.update(DistrictInactive, session);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			listdistrict = districtDAO.getDistrictListbyDistrict(stateCode);
			for (int itr = 0; itr < listdistrict.size(); itr++) {
				State state = null;
				state = new State();
				StatePK statepk = null;
				statepk = new StatePK(stateCodeNew, 1);
				state.setStatePK(statepk);
				SubdistrictPK sdpk = null;
				District sdbean = new District();

				spdk = new DistrictPK(listdistrict.get(itr).getDistrictCode(), listdistrict.get(itr).getDistrictVersion() + 1);
				// sdbean.setState(state);
				sdbean.setDistrictNameEnglish(listdistrict.get(itr).getDistrictNameEnglish());
				sdbean.setDistrictNameLocal(listdistrict.get(itr).getDistrictNameLocal());
				sdbean.setAliasEnglish(listdistrict.get(itr).getAliasEnglish());
				sdbean.setAliasLocal(listdistrict.get(itr).getAliasLocal());
				sdbean.setCensus2001Code(listdistrict.get(itr).getCensus2001Code());
				sdbean.setCensus2011Code(listdistrict.get(itr).getCensus2011Code());
				sdbean.setCreatedby(listdistrict.get(itr).getCreatedby());
				sdbean.setCreatedon(listdistrict.get(itr).getCreatedon());
				sdbean.setEffectiveDate(listdistrict.get(itr).getEffectiveDate());
				sdbean.setFlagCode(2);
				sdbean.setIsactive(true);
				sdbean.setLastupdated(listdistrict.get(itr).getLastupdated());
				sdbean.setLrReplacedby(listdistrict.get(itr).getLrReplacedby());
				sdbean.setLrReplaces(listdistrict.get(itr).getLrReplaces());
				sdbean.setMapCode(listdistrict.get(itr).getMapCode());
				sdbean.setSscode(listdistrict.get(itr).getSscode());
				sdbean.setDistrictPK(spdk);

				try {
					districtDAO.publishDistrict(sdbean, session);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}

			}
			listdistrict = districtDAO.getDistrictListbyDistrict(stateCode);
			for (int itr1 = 0; itr1 < listdistrict.size(); itr1++) {
				this.saveNewSubdistritVersion(listdistrict.get(itr1).getDistrictCode(), listdistrict.get(itr1).getDistrictVersion(), session);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}*/

	public boolean saveNewDistrictVersionfordistrict(int stateCodeNew, int stateCode, int sVersion, Session session) throws Exception {
		DistrictPK spdk = null;
		spdk = null;
		List<District> listdistrict = new ArrayList<District>();
		String DistrictInactive = "update district set isactive = '" + false + "' where state_code = " + stateCode + " and isactive='true'";
		try {
			districtDAO.update(DistrictInactive, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		listdistrict = districtDAO.getDistrict(stateCode);
		for (int itr = 0; itr < listdistrict.size(); itr++) {
			State state = null;
			state = new State();
			StatePK statepk = null;
			statepk = new StatePK(stateCodeNew, 1);
			state.setStatePK(statepk);
			SubdistrictPK sdpk = null;
			District sdbean = new District();

			spdk = new DistrictPK(listdistrict.get(itr).getDistrictCode(), listdistrict.get(itr).getDistrictVersion() + 1,1);
			// sdbean.setState(state);
			sdbean.setDistrictNameEnglish(listdistrict.get(itr).getDistrictNameEnglish());
			sdbean.setDistrictNameLocal(listdistrict.get(itr).getDistrictNameLocal());
			sdbean.setAliasEnglish(listdistrict.get(itr).getAliasEnglish());
			sdbean.setAliasLocal(listdistrict.get(itr).getAliasLocal());
			sdbean.setCensus2001Code(listdistrict.get(itr).getCensus2001Code());
			sdbean.setCensus2011Code(listdistrict.get(itr).getCensus2011Code());
			sdbean.setCreatedby(listdistrict.get(itr).getCreatedby());
			sdbean.setCreatedon(listdistrict.get(itr).getCreatedon());
			sdbean.setEffectiveDate(listdistrict.get(itr).getEffectiveDate());
			sdbean.setFlagCode(2);
			sdbean.setIsactive(true);
			sdbean.setLastupdated(listdistrict.get(itr).getLastupdated());
			sdbean.setLrReplacedby(listdistrict.get(itr).getLrReplacedby());
			sdbean.setLrReplaces(listdistrict.get(itr).getLrReplaces());
			//sdbean.setMapCode(listdistrict.get(itr).getMapCode());
			sdbean.setSscode(listdistrict.get(itr).getSscode());
			sdbean.setDistrictPK(spdk);

			try {
				districtDAO.publishDistrict(sdbean, session);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

		}

		return true;
	}

	@Override
	public boolean saveNewDistrictVersionPartContri(int distCode, int newSDCode, int newSDVersion, Session session) throws Exception {
		// new Version code

		List<District> listVillage = null;
		listVillage = new ArrayList<District>();
		listVillage = districtDAO.getDistrictModify(distCode);
		for (int villItr = 0; villItr < listVillage.size(); villItr++) {
			DistrictPK districtPK = null;
			StatePK sdpk = null;
			State sdbean = null;

			sdpk = new StatePK(newSDCode, newSDVersion);
			sdbean = new State();
			districtPK = new DistrictPK(listVillage.get(villItr).getDistrictCode(), listVillage.get(villItr).getDistrictVersion(),listVillage.get(villItr).getMinorVersion());
			sdbean.setStatePK(sdpk);

			try {
				District district = (District) session.load(District.class, districtPK);

				// district.setState(sdbean);
				district.setFlagCode(2);
				district.setIsactive(true);
				session.update(district);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		}
		return true;
	}

	@Override
	public boolean saveNewDistrictVersionPartContri(int distCode, Session session) throws Exception {
		// new Version code
		List<District> listVillage = null;
		listVillage = new ArrayList<District>();
		listVillage = districtDAO.getDistrictModify(distCode);
		for (int villItr = 0; villItr < listVillage.size(); villItr++) {
			DistrictPK districtPK = null;
			districtPK = new DistrictPK(listVillage.get(villItr).getDistrictCode(), listVillage.get(villItr).getDistrictVersion(),listVillage.get(villItr).getMinorVersion());

			try {
				District district = (District) session.load(District.class, districtPK);
				district.setFlagCode(2);
				district.setIsactive(true);
				session.update(district);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		}
		return true;
	}

	public boolean saveNewStateVersion(int subDistrictCode, int subDistrictCodeVersion, int lrReplacedBy, Session session) throws Exception {
		StatePK sdpk = null;
		State sdbeanLocal;
		// SubdistrictPK sdpk =null;
		// Subdistrict sdbeanLocal = null;
		sdpk = new StatePK(subDistrictCode, subDistrictCodeVersion);
		// sdpk=new SubdistrictPK(subDistrictCode, subDistrictCodeVersion);
		sdbeanLocal = new State();
		sdbeanLocal = stateDao.getSubDistrictDetail(sdpk, session);

		// sdbeanLocal=subdistrictDAO.getSubDistrictDetail(sdpk, session);
		State sdbean = new State();
		sdpk = null;
		sdpk = new StatePK(subDistrictCode, subDistrictCodeVersion + 1);
		// State dist=sdbeanLocal.gets;

		Set<District> sd = new HashSet<District>();
		District district = new District();
		sd.add(district);

		// sdbean.setDistricts(sd);
		sdbean.setStatePK(sdpk);
		sdbean.setStateNameEnglish(sdbeanLocal.getStateNameEnglish());
		sdbean.setStateNameLocal(sdbeanLocal.getStateNameLocal());
		sdbean.setAliasEnglish(sdbeanLocal.getAliasEnglish());
		sdbean.setAliasLocal(sdbeanLocal.getAliasLocal());
		sdbean.setCensus2001Code(sdbeanLocal.getCensus2001Code());
		sdbean.setCensus2011Code(sdbeanLocal.getCensus2011Code());
		sdbean.setCreatedby(sdbeanLocal.getCreatedby());
		sdbean.setCreatedon(sdbeanLocal.getCreatedon());
		sdbean.setEffectiveDate(sdbeanLocal.getEffectiveDate());
		sdbean.setFlagCode(2);
		sdbean.setIsactive(true);
		sdbean.setLastupdated(sdbeanLocal.getLastupdated());
		sdbean.setLrReplacedby(lrReplacedBy);
		sdbean.setLrReplaces(sdbeanLocal.getLrReplaces());
		//sdbean.setMapCode(sdbeanLocal.getMapCode());
		sdbean.setStateCode(sdbeanLocal.getStateCode());
		sdbean.setShortName(sdbeanLocal.getShortName());
		sdbean.setStateOrUt(sdbeanLocal.getStateOrUt());
		try {
			stateDao.saveState(sdbean, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	/*// Subdistrict impacting code
	public boolean saveNewSubdistrict(int discode, int disversion, int districtCode, int distritVersion, Session session) throws Exception {
		// deactivating the old Subdistrit
		// new Version code
		List<Subdistrict> listSubdistrict = new ArrayList<Subdistrict>();
		String villageInactivesql = "update subdistrict set isactive = '" + false + "' where district_code = " + discode + " and isactive='true'";
		try {
			subdistrictDAO.update(villageInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		listSubdistrict = subdistrictDAO.getSubDistrict(districtCode);
		for (int itrSD = 0; itrSD < listSubdistrict.size(); itrSD++) {
			District district = null;

			district = new District();
			DistrictPK districtPKObj = null;
			districtPKObj = new DistrictPK(discode, disversion);
			district.setDistrictPK(districtPKObj);
			SubdistrictPK sdpk = null;
			DistrictPK distritpk = null;
			Subdistrict subDistrictbean = new Subdistrict();
			subDistrictbean.setDistrict(district);
			sdpk = new SubdistrictPK(listSubdistrict.get(itrSD).getTlc(), listSubdistrict.get(itrSD).getTlc() + 1);

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
			subDistrictbean.setMapLandregionCode(listSubdistrict.get(itrSD).getMapLandregionCode());
			subDistrictbean.setSscode(listSubdistrict.get(itrSD).getSscode());
			subDistrictbean.setSubdistrictNameEnglish(listSubdistrict.get(itrSD).getSubdistrictNameEnglish());
			subDistrictbean.setSubdistrictNameLocal(listSubdistrict.get(itrSD).getSubdistrictNameLocal());
			try {
				subdistrictDAO.save(subDistrictbean, session);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		}
		listSubdistrict = subdistrictDAO.getSubDistrictListbyDistrict(districtCode);
		for (int itrSD1 = 0; itrSD1 < listSubdistrict.size(); itrSD1++) {

			this.saveNewVillageVers(listSubdistrict.get(itrSD1).getTlc(), listSubdistrict.get(itrSD1).getTlc(), session);
		}

		return true;
	}

	public boolean saveNewSubDistrictVersion(int subDistrictCode, int subDistrictCodeVersion, int lrReplacedBy, Session session) throws Exception {
		SubdistrictPK sdpk = null;
		Subdistrict sdbeanLocal = null;
		sdpk = new SubdistrictPK(subDistrictCode, subDistrictCodeVersion);
		sdbeanLocal = new Subdistrict();
		sdbeanLocal = subdistrictDAO.getSubDistrictDetail(sdpk, session);
		Subdistrict sdbean = new Subdistrict();
		sdpk = null;
		sdpk = new SubdistrictPK(subDistrictCode, subDistrictCodeVersion + 1);
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
		sdbean.setMapLandregionCode(sdbeanLocal.getMapLandregionCode());
		sdbean.setSscode(sdbeanLocal.getSscode());
		try {
			subdistrictDAO.publishSubDistrict(sdbean, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public boolean saveNewSubdistrictVersionPartContri(int villCode, int newSDCode, int newSDVersion, Session session) throws Exception {
		// new Version code
		List<Subdistrict> listVillage = null;
		listVillage = new ArrayList<Subdistrict>();
		listVillage = subdistrictDAO.getVillageDetailsModify(villCode);
		for (int villItr = 0; villItr < listVillage.size(); villItr++) {
			SubdistrictPK subdistrictPK = null;
			DistrictPK sdpk = null;
			District sdbean = null;
			sdpk = new DistrictPK(newSDCode, newSDVersion);
			sdbean = new District();
			subdistrictPK = new SubdistrictPK(listVillage.get(villItr).getTlc(), listVillage.get(villItr).getTlc() + 1);
			sdbean.setDistrictPK(sdpk);
			try {
				Subdistrict subdistrict = (Subdistrict) session.load(Subdistrict.class, subdistrictPK);
				subdistrict.setDistrict(sdbean);
				subdistrict.setFlagCode(2);
				subdistrict.setIsactive(true);
				session.update(subdistrict);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		}
		return true;
	}

	@Override
	public boolean saveNewSubdistrictVersionPartContri(int villCode, Session session) throws Exception {
		// new Version code
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
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		}
		return true;
	}*/

	// Subdistrict impacting code
	/*public boolean saveNewSubdistrit(int districtCode, int distritVersion, Session session) throws Exception {
		// deactivating the old Subdistrit
		// new Version code
		List<Subdistrict> listSubdistrict = new ArrayList<Subdistrict>();
		String villageInactivesql = "update subdistrict set isactive = '" + false + "' where district_code = " + districtCode + " and isactive='true'";
		try {
			subdistrictDAO.update(villageInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		listSubdistrict = subdistrictDAO.getSubDistrictListbyDistrict(districtCode);
		for (int itrSD = 0; itrSD < listSubdistrict.size(); itrSD++) {
			District district = null;

			district = new District();
			DistrictPK districtPKObj = null;
			districtPKObj = new DistrictPK(districtCode, distritVersion);
			district.setDistrictPK(districtPKObj);
			SubdistrictPK sdpk = null;
			DistrictPK distritpk = null;
			Subdistrict subDistrictbean = new Subdistrict();
			subDistrictbean.setDistrict(district);
			sdpk = new SubdistrictPK(listSubdistrict.get(itrSD).getTlc(), listSubdistrict.get(itrSD).getTlc() + 1);

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
			subDistrictbean.setMapLandregionCode(listSubdistrict.get(itrSD).getMapLandregionCode());
			subDistrictbean.setSscode(listSubdistrict.get(itrSD).getSscode());
			subDistrictbean.setSubdistrictNameEnglish(listSubdistrict.get(itrSD).getSubdistrictNameEnglish());
			subDistrictbean.setSubdistrictNameLocal(listSubdistrict.get(itrSD).getSubdistrictNameLocal());
			try {
				subdistrictDAO.save(subDistrictbean, session);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

		}

		return true;
	}*/

	@Override
	public boolean saveNewSubdistritVersion(int DistrictCode, int districtCode2, int distritVersion, Session session) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	// Subdistrict impacting code
	/*public boolean saveNewSubdistritVersion(int districtCode, int distritVersion, Session session) throws Exception {
		// deactivating the old Subdistrit
		// new Version code
		List<Subdistrict> listSubdistrict = new ArrayList<Subdistrict>();
		String villageInactivesql = "update subdistrict set isactive = '" + false + "' where district_code = " + districtCode + " and isactive='true'";
		try {
			subdistrictDAO.update(villageInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		listSubdistrict = subdistrictDAO.getSubDistrictListbyDistrict(districtCode);
		for (int itrSD = 0; itrSD < listSubdistrict.size(); itrSD++) {
			District district = null;

			district = new District();
			DistrictPK districtPKObj = null;
			districtPKObj = new DistrictPK(districtCode, distritVersion);
			district.setDistrictPK(districtPKObj);
			SubdistrictPK sdpk = null;
			DistrictPK distritpk = null;
			Subdistrict subDistrictbean = new Subdistrict();
			subDistrictbean.setDistrict(district);
			sdpk = new SubdistrictPK(listSubdistrict.get(itrSD).getTlc(), listSubdistrict.get(itrSD).getTlc() + 1);

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
			subDistrictbean.setMapLandregionCode(listSubdistrict.get(itrSD).getMapLandregionCode());
			subDistrictbean.setSscode(listSubdistrict.get(itrSD).getSscode());
			subDistrictbean.setSubdistrictNameEnglish(listSubdistrict.get(itrSD).getSubdistrictNameEnglish());
			subDistrictbean.setSubdistrictNameLocal(listSubdistrict.get(itrSD).getSubdistrictNameLocal());
			try {
				subdistrictDAO.save(subDistrictbean, session);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		}
		listSubdistrict = subdistrictDAO.getSubDistrictListbyDistrict(districtCode);
		for (int itrSD1 = 0; itrSD1 < listSubdistrict.size(); itrSD1++) {

			this.saveNewVillageVersion(listSubdistrict.get(itrSD1).getTlc(), listSubdistrict.get(itrSD1).getTlc(), session);
		}

		return true;
	}

	// Subdistrict impacting code
	public boolean saveNewvillageforstate(int discode, int disversion, int districtCode, int distritVersion, Session session) throws Exception {
		// deactivating the old Subdistrit
		// new Version code
		List<Subdistrict> listSubdistrict = new ArrayList<Subdistrict>();
		String villageInactivesql = "update subdistrict set isactive = '" + false + "' where district_code = " + discode + " and isactive='true'";
		try {
			subdistrictDAO.update(villageInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		listSubdistrict = subdistrictDAO.getSubDistrict(districtCode);
		for (int itrSD = 0; itrSD < listSubdistrict.size(); itrSD++) {
			District district = null;

			district = new District();
			DistrictPK districtPKObj = null;
			districtPKObj = new DistrictPK(discode, disversion);
			district.setDistrictPK(districtPKObj);
			SubdistrictPK sdpk = null;
			DistrictPK distritpk = null;
			Subdistrict subDistrictbean = new Subdistrict();
			subDistrictbean.setDistrict(district);
			sdpk = new SubdistrictPK(listSubdistrict.get(itrSD).getTlc(), listSubdistrict.get(itrSD).getTlc() + 1);

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
			subDistrictbean.setMapLandregionCode(listSubdistrict.get(itrSD).getMapLandregionCode());
			subDistrictbean.setSscode(listSubdistrict.get(itrSD).getSscode());
			subDistrictbean.setSubdistrictNameEnglish(listSubdistrict.get(itrSD).getSubdistrictNameEnglish());
			subDistrictbean.setSubdistrictNameLocal(listSubdistrict.get(itrSD).getSubdistrictNameLocal());
			try {
				subdistrictDAO.save(subDistrictbean, session);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
		}

		return true;
	}

	public boolean saveNewVillageVers(int subDistrictCode, int subDistrictVersion, Session session) throws Exception {
		// deactivating the old village

		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
		String villageInactivesql = "update village set isactive = '" + false + "' where subdistrict_code = " + subDistrictCode + " and isactive='true'";
		try {
			villageDAO.update(villageInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		listVillage = villageDAO.getVillageListbySubDistrictCode(subDistrictCode);
		for (int villItr = 0; villItr < listVillage.size(); villItr++) {
			VillagePK villagePK = null;
			SubdistrictPK sdpk = null;
			Subdistrict sdbean = null;

			sdpk = new SubdistrictPK(subDistrictCode, subDistrictVersion);
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
				log.debug("Exception" + e);
			}
		}

		return true;
	}

	public boolean saveNewVillageVersion(int villcode, int villversion, int subDistrictCode, int subDistrictVersion, Session session) throws Exception {
		// deactivating the old village

		// new Version code
		List<Village> listVillage = null;
		listVillage = new ArrayList<Village>();
		String villageInactivesql = "update village set isactive = '" + false + "' where subdistrict_code = " + villcode + " and isactive='true'";
		try {
			villageDAO.update(villageInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		listVillage = villageDAO.getVillageList(subDistrictCode);
		for (int villItr = 0; villItr < listVillage.size(); villItr++) {
			VillagePK villagePK = null;
			SubdistrictPK sdpk = null;
			Subdistrict sdbean = null;

			sdpk = new SubdistrictPK(villcode, villversion);
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
				log.debug("Exception" + e);
			}
		}

		return true;
	}*/

	@Override
	public boolean saveNewVillageVersion(int subDistrictCode, int newSDCode, int newSDVersion, Session session) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveNewVillageVersion(int subDistrictCode, int subDistrictVersion, Session session) throws Exception {
		// deactivating the old village

		// new Version code
		List<Village> listVillage = null;

		String villageInactivesql = "update village set isactive = '" + false + "' where subdistrict_code = " + subDistrictCode + " and isactive='true'";
		try {
			villageDAO.update(villageInactivesql, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		String insertBulkVillage = "insert into village(village_code,village_version,village_name_english,village_name_local,subdistrict_code,subdistrict_version,alias_english,alias_local,"
				+ "census_2001_code,census_2011_code,sscode,lr_replaces,map_landregion_code,effective_date,lastupdated,lastupdatedby,createdon," + "createdby,village_status,isactive,lr_replacedby,flag_code)"
				+ "select  village_code,village_version+1,village_name_english,village_name_local,subdistrict_code,subdistrict_version,alias_english,alias_local,"
				+ "census_2001_code,census_2011_code,sscode,lr_replaces,map_landregion_code,effective_date,lastupdated,lastupdatedby,createdon," + "createdby,village_status,TRUE,lr_replacedby,2 from village where (subdistrict_code=" + subDistrictCode
				+ "  and subdistrict_version=" + subDistrictVersion + ") and isactive=false ";

		try {
			villageDAO.insertBulkVillage(insertBulkVillage, session);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		/*
		 * listVillage=villageDAO.getVillageListbySubDistrictCode(
		 * subDistrictCode ); for (int villItr=0; villItr<listVillage.size();
		 * villItr++){ VillagePK villagePK=null; SubdistrictPK sdpk=null;
		 * Subdistrict sdbean=null;
		 * 
		 * 
		 * sdpk = new SubdistrictPK(subDistrictCode, subDistrictVersion); sdbean
		 * = new Subdistrict(); villagePK=new
		 * VillagePK(listVillage.get(villItr).getVillageCode(),
		 * listVillage.get(villItr).getVillageVersion()+1); Village villBean=new
		 * Village();
		 * 
		 * sdbean.setSubdistrictPK(sdpk);
		 * villBean.setVillageNameEnglish(listVillage
		 * .get(villItr).getVillageNameEnglish());
		 * villBean.setVillageNameLocal(listVillage
		 * .get(villItr).getVillageNameLocal());
		 * villBean.setAliasEnglish(listVillage.get(villItr).getAliasEnglish());
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
		 * villBean.setLastupdated(listVillage.get(villItr).getLastupdated());
		 * villBean
		 * .setLastupdatedby(listVillage.get(villItr).getLastupdatedby());
		 * villBean.setLrReplacedby(listVillage.get(villItr).getLrReplacedby());
		 * villBean.setLrReplaces(listVillage.get(villItr).getLrReplaces());
		 * villBean.setMapCode(listVillage.get(villItr).getMapCode());
		 * villBean.setSscode(listVillage.get(villItr).getSscode());
		 * villBean.setSubdistrict(sdbean); villBean.setVillagePK(villagePK);
		 * villBean
		 * .setVillageStatus(listVillage.get(villItr).getVillageStatus());
		 * //villBean.setVillagePartsBySurveyno(listVillage.get(villItr).
		 * getVillagePartsBySurveyno()); villBean.setFlagCode(2); try {
		 * villageDAO.save(villBean, session); } catch (Exception e) {
		 * log.debug("Exception" + e); } }
		 */

		return true;
	}

	/*@Override
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
			sdbean.setSubdistrictPK(sdpk);

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
	}*/

	/*@Override
	public boolean saveNewVillageVersionPartContri(int villCode, int newSDCode, int newSDVersion, Session session) throws Exception {
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
					log.debug("Exception" + e);
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}
*/
	/*@Override
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
	}*/

	@Override
	public boolean saveReplacedBy(int id, int lrReplacedBy, int sdCode, int sdVersionCode, Session session) throws Exception {
		LandregionReplacedby landregionReplacedbyBean = null;
		try {

			landregionReplacedbyBean = new LandregionReplacedby();
			landregionReplacedbyBean.setId(id);
			landregionReplacedbyBean.setLrReplacedby(lrReplacedBy);
			landregionReplacedbyBean.setEntityCode(sdCode);
			landregionReplacedbyBean.setEntityVersion(sdVersionCode);
			landregionReplacedbyBean.setEntityType('T');
			landRegionReplacedByDAO.save(landregionReplacedbyBean, session);
			session.flush();
			session.refresh(landregionReplacedbyBean);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public boolean saveReplaces(int id, int lrReplaces, int sdCode, int sdVersionCode, Session session) throws Exception {
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
			log.debug("Exception" + e);
		}
		return true;
	}

	@Override
	public boolean saveShiftdistrict(StateForm sdForm, List<ShiftDistrictForm> listdistritShift, HttpServletRequest request) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		isMethod1Commited = this.saveState(sdForm, session);
		tx.commit();
		try {
			// shiftService.shiftDistrict(shiftDistrictForm,request);
			for (int i = 0; i < listdistritShift.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				listdistritShift.get(i).setDistrictNameEnglish(String.valueOf(districtCode));
				isMethod2Commited = shiftService.shiftDistrictReorganize(listdistritShift.get(i), request);
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
	}

	@Override
	public boolean saveShiftSubdistrict(StateForm sdForm, List<ShiftSubDistrictForm> listsubdistritShift, HttpServletRequest request) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		isMethod1Commited = this.saveState(sdForm, session);
		tx.commit();
		try {
			// shiftService.shiftDistrict(shiftDistrictForm,request);
			for (int i = 0; i < listsubdistritShift.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				isMethod2Commited = shiftService.shiftSubDistrictReorganize(listsubdistritShift.get(i), request);
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
	}

	@Override
	public boolean saveShiftvillage(StateForm sdForm, List<ShiftVillageForm> listvillageShift, HttpServletRequest request) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		isMethod1Commited = this.saveState(sdForm, session);
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
	}

	@Override
	@Transactional
	public boolean saveState(StateForm stateForm, Session session) throws Exception {
		// System.out.println("in save state");
		boolean isCommited = false;
		int distictCode = 0;
		// District dist=new District(districtCode,districtVersion);
		Date timestamp = DatePicker.getDate("2011-09-26");
		int stVersionCode;
		int sdCode = 0;
		int sdVersionCode = 0;
		int distritVersion = 0;
		int idLR = 0;
		int lrReplacedby = 0;

		int localGovtBodyCode = 0;
		int tier_setup_code = 0;
		int government_order_code = 0;
		int localGovtBodySubtypeCode = 0;
		int localBodyVersion = 1;
		int createdby = 1000;
		int map_landRegionCode = 0;
		int landCoveredRegionCode = 0;
		int landCoveredRegionCodeOfSubDistrict = 0;
		int headquarterCode = 0;
		int subdistricVersion = 0;
		String longitude = "";
		int localBodyTypeCode = 0;
		boolean result = false;
		int flagCode = 1;
		String EffectiveDate = "2011-12-12";
		int SubDistrictCode = 0;

		District oldDistrict = null;
		District newDistrict = null;
		Subdistrict oldSubDistrict = null;

		String cordinate = "";
		String latitude = "";
		String coveredAreaCode;
		State oldState = null;
		State newState = null;

		int districVersion = 0;
		VillagePK villagePK1 = null;
		// DistrictCode = Integer.parseInt(districtForm.getStateNameEnglish());
		// System.out.println("the District code is"+DistrictCode);
		Timestamp time = CurrentDateTime.getCurrentTimestamp();
		LandregionReplacedby LandregionReplacedbyBean = null;
		newDistrict = new District();

		List<State> sdList = null;
		sdList = new ArrayList<State>();
		// int subdistrictCode =
		// Integer.parseInt(stateForm.getSTATENAMEENGLISH());
		// System.out.println("code is"+subdistrictCode);

		districtCode = districtDAO.getMaxDistrictCode("select COALESCE(max(district_code),1) from district");
		// System.out.println("District code is-" + districtCode);
		if (districtCode == 0) {
			districtCode = 1;
		} else {
			districtCode = districtCode + 1;
		}
		distritVersion = 1;
		stateCode = stateDao.getMaxStateCode("select COALESCE(max(state_code),1) from State");
		// System.out.println("State code is-" + stateCode);
		if (stateCode == 0) {
			stateCode = 1;
		} else {
			stateCode = stateCode + 1;
		}
		stVersionCode = 1;

		List<State> fullContributedListState = null;
		fullContributedListState = new ArrayList<State>();
		List<State> partContributedListState = null;
		partContributedListState = new ArrayList<State>();

		List<Subdistrict> fullContributedList = null;
		List<Subdistrict> partContributedList = null;

		fullContributedList = new ArrayList<Subdistrict>();
		partContributedList = new ArrayList<Subdistrict>();

		List<District> fullContributedListDistrit = null;
		fullContributedListDistrit = new ArrayList<District>();
		List<District> partContributedListDistrit = null;
		partContributedListDistrit = new ArrayList<District>();

		List<Village> vFullList = null;
		List<Village> vPartList = null;
		vFullList = new ArrayList<Village>();
		vPartList = new ArrayList<Village>();

		/*
		 * coveredAreaCode=stateForm.getSTATENAMEENGLISH();
		 * System.out.println("coveredAreaCode>>>>>>>>" + coveredAreaCode);
		 * 
		 * 
		 * String[] selectedState=stateForm.getSTATENAMEENGLISH().split(",");
		 * for (int i = 0; i < selectedState.length; i++) { int sCodeFull =
		 * Integer.parseInt(selectedState[i].substring(0,
		 * selectedState[i].length() - 4));
		 * 
		 * List<State> lstTemp = null; lstTemp = new ArrayList<State>(); lstTemp
		 * = stateDao.getStateList("from State where stateCode='"+ sCodeFull +
		 * "' and isactive=true"); if (selectedState[i].contains("FULL")){
		 * fullContributedListState.add(lstTemp.get(0)); } else if
		 * (selectedState[i].contains("PART")){
		 * partContributedListState.add(lstTemp.get(0)); } }
		 */

		String selectedVillage = stateForm.getSTATENAMEENGLISH();
		// System.out.println("selectedVillage>>>>>>>>" + selectedVillage);
		String[] temp = selectedVillage.split(",");
		for (int i = 0; i < temp.length; i++) {
			// System.out.println(temp[i]);
			if (temp[i].contains("FULL")) {
				int vCodeFull = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
				/*
				 * Village fullVillageBean = null; fullVillageBean=new
				 * Village();
				 */

				List<State> lstTemp = null;
				lstTemp = new ArrayList<State>();

				try {

					lstTemp = stateDao.getStateList("from State where stateCode='" + vCodeFull + "' and isactive=true");
					/*
					 * lstTemp = villageDAO .getListVillageDetails(
					 * "from Village where village_code='" + vCodeFull +
					 * "' and isactive=true");
					 */
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				fullContributedListState.add(lstTemp.get(0));

			}
			if (temp[i].contains("PART")) {
				int vCodePart = Integer.parseInt(temp[i].substring(0, temp[i].length() - 4));
				List<State> lstTemp = null;
				lstTemp = new ArrayList<State>();
				try {
					lstTemp = stateDao.getStateList("from State where stateCode='" + vCodePart + "' and isactive=true");
					/*
					 * lstTemp = villageDAO .getListVillageDetails(
					 * "from Village where village_code='" + vCodePart +
					 * "' and isactive=true");
					 */
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				partContributedListState.add(lstTemp.get(0));
			}

		}

		districtCode = districtDAO.getMaxDistrictCode("select COALESCE(max(district_code),1) from district");

		if (districtCode == 0) {
			districtCode = 1;
		} else {
			districtCode = districtCode + 1;
		}
		distritVersion = 1;

		sdCode = subdistrictDAO.getMaxSubDistrictCode() + 1;
		sdVersionCode = 1;

		if (stateForm.getDistrictNameEnglish() != null) {

			coveredAreaCode = stateForm.getDistrictNameEnglish();

			String[] temp1 = coveredAreaCode.split(",");
			for (int i = 0; i < temp1.length; i++) {

				if (temp1[i].contains("FULL")) {
					landCoveredRegionCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					List<District> lstTemp = null;
					lstTemp = new ArrayList<District>();
					try {
						lstTemp = districtDAO.getListVillageDetails("from District where district_code='" + landCoveredRegionCode + "' and isactive=true");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.debug("Exception" + e);
					}
					fullContributedListDistrit.add(lstTemp.get(0));
				}
				if (temp1[i].contains("PART")) {
					landCoveredRegionCode = Integer.parseInt(temp1[i].substring(0, temp1[i].length() - 4));
					List<District> lstTemp = null;
					lstTemp = new ArrayList<District>();
					try {
						lstTemp = districtDAO.getListVillageDetails("from District where district_code='" + landCoveredRegionCode + "' and isactive=true");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.debug("Exception" + e);
					}
					partContributedListDistrit.add(lstTemp.get(0));
				}
			}
		}

		/*
		 * int subdistrictCode = 10; try { sdList =
		 * districtDAO.getSubdistrictDetails(subdistrictCode); } catch
		 * (Exception e2) { // TODO Auto-generated catch block
		 * e2.printStackTrace(); } subdistricVersion =
		 * sdList.get(0).getStateVersion();
		 */

		/*
		 * int subdistrictCode =
		 * Integer.parseInt(stateForm.getStateNameEnglish()); try { sdList =
		 * districtDAO.getSubdistrictDetails(subdistrictCode); } catch
		 * (Exception e2) { // TODO Auto-generated catch block
		 * e2.printStackTrace(); }
		 */
		if (stateForm.getContributedSubDistricts() != null) {
			// subdistricVersion = sdList.get(0).getStateVersion();
			String[] selectedSubDistricts = stateForm.getContributedSubDistricts().split(",");
			for (int i = 0; i < selectedSubDistricts.length; i++) {
				int sCodeFull = Integer.parseInt(selectedSubDistricts[i].substring(0, selectedSubDistricts[i].length() - 4));

				List<Subdistrict> lstTemp = null;
				lstTemp = new ArrayList<Subdistrict>();
				try {
					lstTemp = subdistrictDAO.viewSubDistrictDetails("from Subdistrict s where subdistrictCode=" + sCodeFull + " and isactive=true");
				} catch (Exception e) {
					// TODO: handle exception
					log.debug("Exception" + e);
				}
				if (selectedSubDistricts[i].contains("FULL")) {
					fullContributedList.add(lstTemp.get(0));
				} else if (selectedSubDistricts[i].contains("PART")) {
					partContributedList.add(lstTemp.get(0));
				}
			}
		}
		if (stateForm.getContributedVillages() != null) {
			{
				int vCode = 0;
				String[] selectedVillages = stateForm.getContributedVillages().split(",");
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

		int lrReplacedBy = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
		int lrReplaces = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(lr_replaces),1) from landregion_replaces");
		int rByid = landRegionReplacedByDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
		int id = landRegionReplacesDAO.getMaxRecords("select COALESCE(max(id),1) from landregion_replaces");
		headquarterCode = headquartersDAO.getMaxHeadquartersCode();

		try {
			if (fullContributedListState.size() > 0 && partContributedListState.size() == 0) {
				mapCd = this.publishMapLandRegion(stateForm, stateCode, stVersionCode, session);
				this.publishState(stateForm, stateCode, stVersionCode, mapCd, session);
				this.saveHeadquarters(stateForm, headquarterCode, stateCode, sdVersionCode, session);

				// Land Region Replaced By

				try {
					this.saveReplacedBy(rByid, lrReplacedBy, stVersionCode, stVersionCode, session);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				for (int k = 0; k < fullContributedListState.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, fullContributedListState.get(k).getStateCode(), fullContributedListState.get(k).getStateVersion(), session);
					StatePK sdpk = new StatePK(fullContributedListState.get(k).getStateCode(), fullContributedListState.get(k).getStateVersion());

					stateDao.updateIsActive(false, sdpk, session);
					// Distrcit impacting code
					//this.saveNewDistrictVersion(stateCode, fullContributedListState.get(k).getStateCode(), fullContributedListState.get(k).getStateVersion(), session);
					id++;
				}
				StatePK sdpk = new StatePK(stateCode, stVersionCode);
				stateDao.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																		// detail
																		// in
																		// sub
																		// district
																		// table
				isCommited = true;
			}

			else if (partContributedListState.size() > 0 && fullContributedListDistrit.size() > 0) {
				mapCd = this.publishMapLandRegion(stateForm, stateCode, stVersionCode, session);
				this.publishState(stateForm, stateCode, stVersionCode, mapCd, session);
				this.saveHeadquarters(stateForm, headquarterCode, stateCode, sdVersionCode, session);

				// Land Region Replaced By

				try {
					this.saveReplacedBy(rByid, lrReplacedBy, stVersionCode, stVersionCode, session);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				for (int k = 0; k < partContributedListState.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), session);

					StatePK sdpk = new StatePK(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion());

					// code to get new sd version
					this.saveNewStateVersion(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), lrReplacedBy, session);
					// --------------------

					stateDao.updateIsActive(false, sdpk, session);
					saveNewDistrictVersionPartContri(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateCode(), stVersionCode, session);

					// Distrcit impacting code
					id++;
				}
				for (int i = 0; i < fullContributedListDistrit.size(); i++) {
					this.saveNewDistrictVersionfordistrict(stateCode, fullContributedListDistrit.get(i).getDistrictCode(), fullContributedListDistrit.get(i).getDistrictVersion(), session);

					DistrictPK districtpk = new DistrictPK(fullContributedListDistrit.get(i).getDistrictCode(), fullContributedListDistrit.get(i).getDistrictVersion(),fullContributedListDistrit.get(i).getMinorVersion());

					stateDao.updatedistrict(false, districtpk, session);

					//this.saveNewSubdistrit(fullContributedListDistrit.get(i).getDistrictCode(), fullContributedListDistrit.get(i).getDistrictVersion(), session);

				}

				StatePK sdpk1 = new StatePK(stateCode, stVersionCode);
				stateDao.updateLrReplaces(lrReplaces, sdpk1, session);// corresponding
																		// detail
																		// in
																		// sub
																		// district
																		// table
				isCommited = true;
			}

			else if (partContributedListState.size() > 0 && fullContributedListDistrit.size() > 0 && fullContributedList.size() > 0) {
				mapCd = this.publishMapLandRegion(stateForm, stateCode, stVersionCode, session);
				this.publishState(stateForm, stateCode, stVersionCode, mapCd, session);
				this.saveHeadquarters(stateForm, headquarterCode, stateCode, sdVersionCode, session);

				// Land Region Replaced By

				try {
					this.saveReplacedBy(rByid, lrReplacedBy, stVersionCode, stVersionCode, session);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				for (int k = 0; k < partContributedListState.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), session);
					StatePK sdpk = new StatePK(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion());

					stateDao.updateIsActive(false, sdpk, session);

					// Distrcit impacting code
					id++;

					for (int i = 0; i < fullContributedListDistrit.size(); i++) {
						this.saveNewDistrictVersionfordistrict(stateCode, fullContributedListDistrit.get(i).getDistrictCode(), fullContributedListDistrit.get(i).getDistrictVersion(), session);
					}
					for (int i = 0; i < fullContributedList.size(); i++) {

						//this.saveNewSubdistritVersion(fullContributedList.get(i).getTlc(), fullContributedList.get(i).getTlc(), session);

					}
					saveNewDistrictVersionPartContri(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateCode(), stVersionCode, session);

					StatePK sdpk1 = new StatePK(stateCode, stVersionCode);
					stateDao.updateLrReplaces(lrReplaces, sdpk1, session);// corresponding
																			// detail
																			// in
																			// sub
																			// district
																			// table
					isCommited = true;
				}
			}

			else if (partContributedListState.size() > 0 && partContributedListDistrit.size() > 0 && fullContributedList.size() > 0) {
				mapCd = this.publishMapLandRegion(stateForm, stateCode, stVersionCode, session);
				this.publishState(stateForm, stateCode, stVersionCode, mapCd, session);
				this.saveHeadquarters(stateForm, headquarterCode, stateCode, sdVersionCode, session);

				// Land Region Replaced By

				try {
					this.saveReplacedBy(rByid, lrReplacedBy, stVersionCode, stVersionCode, session);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				for (int k = 0; k < partContributedListState.size(); k++) {
					this.saveReplaces(id, lrReplaces, partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), session);

					StatePK sdpk = new StatePK(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion());

					// code to get new sd version
					this.saveNewStateVersion(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), lrReplacedBy, session);
					// --------------------

					stateDao.updateIsActive(false, sdpk, session);
					saveNewDistrictVersionPartContri(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateCode(), stVersionCode, session);
					// Distrcit impacting code
					id++;
				}
				for (int i = 0; i < partContributedListDistrit.size(); i++) {
					this.saveNewDistrictVersionfordistrict(stateCode, partContributedListDistrit.get(i).getDistrictCode(), partContributedListDistrit.get(i).getDistrictVersion(), session);
				}
				for (int j = 0; j < fullContributedList.size(); j++) {

					//this.saveNewSubdistrict(fullContributedList.get(j).getDlc(), fullContributedList.get(j).getDlc(), fullContributedList.get(j).getTlc(), fullContributedList.get(j).getTlc(), session);

					SubdistrictPK subdistrictpk = new SubdistrictPK(fullContributedList.get(j).getTlc(), fullContributedList.get(j).getTlc(),1);

					stateDao.updatesubdistrict(false, subdistrictpk, session);

					StatePK sdpk1 = new StatePK(stateCode, stVersionCode);
					stateDao.updateLrReplaces(lrReplaces, sdpk1, session);// corresponding
																			// detail
																			// in
																			// sub
																			// district
																			// table
					isCommited = true;
				}

			}

			/*
			 * else if(partContributedListState.size()>0 &&
			 * fullContributedListDistrit.size()>0 &&
			 * fullContributedList.size()>0 && vFullList.size()>0) { mapCd =
			 * this.publishMapLandRegion(stateForm, stateCode, stVersionCode,
			 * session); this.publishState(stateForm, stateCode, stVersionCode,
			 * mapCd, session); this.saveHeadquarters(stateForm,
			 * headquarterCode, stateCode, sdVersionCode, session);
			 * 
			 * //Land Region Replaced By
			 * 
			 * try { this.saveReplacedBy(rByid, lrReplacedBy, stVersionCode,
			 * stVersionCode, session); } catch (Exception e) {
			 * log.debug("Exception" + e); } for (int k = 0; k
			 * <partContributedListState.size(); k++) { //Land Region Replaces
			 * this.saveReplaces(id, lrReplaces,
			 * partContributedListState.get(k).getStateCode(),
			 * partContributedListState.get(k).getStateVersion(), session);
			 * StatePK sdpk = new
			 * StatePK(partContributedListState.get(k).getStateCode(),
			 * partContributedListState.get(k).getStateVersion());
			 * 
			 * stateDao.updateIsActive(false, sdpk, session);
			 * 
			 * //Distrcit impacting code id++;
			 * 
			 * for (int i = 0; i< fullContributedListDistrit.size(); i++) {
			 * this.saveNewDistrictVersionfordistrict(stateCode,
			 * fullContributedListDistrit.get(i).getDistrictCode(),
			 * fullContributedListDistrit.get(i).getDistrictVersion(), session);
			 * } for (int i = 0; i< fullContributedList.size(); i++) {
			 * this.saveNewSubdistrict
			 * (fullContributedList.get(i).getSubdistrictCode(),
			 * fullContributedList.get(i).getSubdistrictVersion(), session); }
			 * for (int i = 0; i< vFullList.size(); i++) {
			 * this.saveNewVillageVersion(vFullList.get(i).getVillageCode(),
			 * vFullList.get(i).getVillageVersion(), session); }
			 * saveNewDistrictVersionPartContri
			 * (partContributedListState.get(k).getStateCode(),
			 * partContributedListState.get(k).getStateCode(), stVersionCode,
			 * session); StatePK sdpk1 = new StatePK(stateCode, stVersionCode);
			 * stateDao.updateLrReplaces(lrReplaces, sdpk1,
			 * session);//corresponding detail in sub district table isCommited
			 * = true; } }
			 */
			else if (partContributedListState.size() > 0 && partContributedListDistrit.size() > 0 && partContributedList.size() > 0 && vFullList.size() > 0) {
				mapCd = this.publishMapLandRegion(stateForm, stateCode, stVersionCode, session);
				this.publishState(stateForm, stateCode, stVersionCode, mapCd, session);
				this.saveHeadquarters(stateForm, headquarterCode, stateCode, sdVersionCode, session);

				// Land Region Replaced By

				try {
					this.saveReplacedBy(rByid, lrReplacedBy, stVersionCode, stVersionCode, session);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				for (int k = 0; k < partContributedListState.size(); k++) {
					this.saveReplaces(id, lrReplaces, partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), session);

					StatePK sdpk = new StatePK(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion());

					// code to get new sd version
					this.saveNewStateVersion(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), lrReplacedBy, session);
					// --------------------

					stateDao.updateIsActive(false, sdpk, session);
					saveNewDistrictVersionPartContri(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateCode(), stVersionCode, session);
					id++;
				}
				for (int i = 0; i < partContributedListDistrit.size(); i++) {
					this.saveNewDistrictVersionfordistrict(stateCode, partContributedListDistrit.get(i).getDistrictCode(), partContributedListDistrit.get(i).getDistrictVersion(), session);

					for (int j = 0; j < partContributedList.size(); j++) {
						//this.saveNewvillageforstate(partContributedList.get(j).getDlc(), partContributedList.get(j).getDlc(), partContributedList.get(j).getTlc(), partContributedList.get(j).getTlc(), session);

						for (int k = 0; k < vFullList.size(); k++) {
							//this.saveNewVillageVersion(vFullList.get(k).getTlc(), vFullList.get(k).getTlc(), vFullList.get(k).getVlc(), vFullList.get(k).getVlc(), session);
							VillagePK villagepk = new VillagePK(vFullList.get(k).getVlc(), vFullList.get(k).getVlc(),1);

							stateDao.updatevillage(false, villagepk, session);

						}
						StatePK sdpk1 = new StatePK(stateCode, stVersionCode);
						stateDao.updateLrReplaces(lrReplaces, sdpk1, session);// corresponding
																				// detail
																				// in
																				// sub
																				// district
																				// table
						isCommited = true;
					}
				}
			}

			else if (partContributedListState.size() > 0 && partContributedListDistrit.size() > 0 && partContributedList.size() > 0 && vPartList.size() > 0) {
				mapCd = this.publishMapLandRegion(stateForm, stateCode, stVersionCode, session);
				this.publishState(stateForm, stateCode, stVersionCode, mapCd, session);
				this.saveHeadquarters(stateForm, headquarterCode, stateCode, sdVersionCode, session);

				// Land Region Replaced By

				try {
					this.saveReplacedBy(rByid, lrReplacedBy, stVersionCode, stVersionCode, session);
				} catch (Exception e) {
					log.debug("Exception" + e);
				}
				for (int k = 0; k < partContributedListState.size(); k++) {
					this.saveReplaces(id, lrReplaces, partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), session);

					StatePK sdpk = new StatePK(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion());

					// code to get new sd version
					this.saveNewStateVersion(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), lrReplacedBy, session);
					// --------------------

					stateDao.updateIsActive(false, sdpk, session);
					saveNewDistrictVersionPartContri(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateCode(), stVersionCode, session);
					id++;
				}
				for (int i = 0; i < partContributedListDistrit.size(); i++) {
					this.saveNewDistrictVersionfordistrict(stateCode, partContributedListDistrit.get(i).getDistrictCode(), partContributedListDistrit.get(i).getDistrictVersion(), session);

					for (int j = 0; j < partContributedList.size(); j++) {
						//this.saveNewSubdistrict(partContributedList.get(j).getDlc(), partContributedList.get(j).getDlc(), partContributedList.get(j).getTlc(), partContributedList.get(j).getTlc(), session);

						for (int k = 0; k < vPartList.size(); k++) {
							//this.saveNewVillageVersion(vPartList.get(k).getTlc(), vPartList.get(k).getTlc(), vPartList.get(k).getVlc(), vPartList.get(k).getVlc(), session);
						}
						StatePK sdpk1 = new StatePK(stateCode, stVersionCode);
						stateDao.updateLrReplaces(lrReplaces, sdpk1, session);// corresponding
																				// detail
																				// in
																				// sub
																				// district
																				// table
						isCommited = true;
					}
				}
			}

			else if (fullContributedListState.size() == 0 && partContributedListState.size() > 0) {
				mapCd = 0;
				mapCd = this.publishMapLandRegion(stateForm, stateCode, stVersionCode, session);
				this.saveHeadquarters(stateForm, headquarterCode, stateCode, sdVersionCode, session);
				this.publishState(stateForm, stateCode, stVersionCode, mapCd, session);// this.saveHeadquarters(sdForm,
																						// headquarterCode,
																						// sdCode,
																						// sdVersionCode,
																						// session);
				// Land Region Replaced By

				this.saveReplacedBy(rByid, lrReplacedBy, stVersionCode, stVersionCode, session);
				for (int k = 0; k < partContributedListState.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), session);

					StatePK sdpk = new StatePK(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion());

					// code to get new sd version
					this.saveNewStateVersion(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), lrReplacedBy, session);
					// --------------------

					// District impacting code
					//this.saveNewDistrictVersion(stateCode, partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), session);

					stateDao.updateIsActive(false, sdpk, session);
					saveNewDistrictVersionPartContri(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateCode(), stVersionCode, session);
					id++;
				}
				/*
				 * for (int i=0; i<fullContributedListDistrit.size();i++){
				 * saveNewDistrictVersionPartContri
				 * (fullContributedListDistrit.get(i).getDistrictCode(),
				 * stateCode, stVersionCode, session); } for (int i=0;
				 * i<partContributedListDistrit.size();i++){
				 * saveNewDistrictVersionPartContri
				 * (partContributedListDistrit.get
				 * (i).getDistrictCode(),stateCode, stVersionCode, session);
				 * saveNewDistrictVersionPartContri
				 * (partContributedListDistrit.get(i).getDistrictCode(),
				 * session); }
				 */

				StatePK sdpk = new StatePK(stateCode, stVersionCode);
				stateDao.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																		// detail
																		// in
																		// sub
																		// district
																		// table
				isCommited = true;
			} else if (fullContributedListState.size() > 0 && partContributedListState.size() > 0) {
				mapCd = this.publishMapLandRegion(stateForm, stateCode, stVersionCode, session);
				this.saveHeadquarters(stateForm, headquarterCode, stateCode, sdVersionCode, session);
				this.publishState(stateForm, stateCode, stVersionCode, mapCd, session);

				// Land Region Replaced By

				this.saveReplacedBy(rByid, lrReplacedBy, stVersionCode, stVersionCode, session);
				for (int k = 0; k < fullContributedListState.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, fullContributedListState.get(k).getStateCode(), fullContributedListState.get(k).getStateVersion(), session);
					StatePK sdpk = new StatePK(fullContributedListState.get(k).getStateCode(), fullContributedListState.get(k).getStateVersion());
					stateDao.updateIsActive(false, sdpk, session);
					// District impacting code
					//this.saveNewDistrictVersion(stateCode, fullContributedListState.get(k).getStateCode(), fullContributedListState.get(k).getStateVersion(), session);
					id++;
				}
				// -------------------------------------------------------
				// Part Contributed
				for (int k = 0; k < partContributedListState.size(); k++) {
					// Land Region Replaces
					this.saveReplaces(id, lrReplaces, partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), session);

					StatePK sdpk = new StatePK(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion());

					// code to get new sd version
					this.saveNewStateVersion(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), lrReplacedBy, session);
					// --------------------

					// District impacting code
					//this.saveNewDistrictVersion(stateCode, partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateVersion(), session);

					stateDao.updateIsActive(false, sdpk, session);
					saveNewDistrictVersionPartContri(partContributedListState.get(k).getStateCode(), partContributedListState.get(k).getStateCode(), stVersionCode, session);
					id++;
				}
				/*
				 * for (int i=0; i<fullContributedListDistrit.size();i++){
				 * saveNewDistrictVersionPartContri
				 * (fullContributedListDistrit.get(i).getDistrictCode(),
				 * stateCode, stVersionCode, session); } for (int i=0;
				 * i<partContributedListDistrit.size();i++){
				 * saveNewDistrictVersionPartContri
				 * (partContributedListDistrit.get
				 * (i).getDistrictCode(),stateCode, stVersionCode, session);
				 * saveNewDistrictVersionPartContri
				 * (partContributedListDistrit.get(i).getDistrictCode(),
				 * session); }
				 */

				StatePK sdpk = new StatePK(stateCode, stVersionCode);
				stateDao.updateLrReplaces(lrReplaces, sdpk, session);// corresponding
																		// detail
																		// in
																		// sub
																		// district
																		// table
				isCommited = true;

			}

			/*
			 * if (fullContributedListDistrit.size() > 0 &&
			 * partContributedListDistrit.size() == 0) {
			 * 
			 * // Land Region Replaced By for (int k = 0; k <
			 * fullContributedListDistrit.size(); k++) { // Land Region Replaces
			 * 
			 * DistrictPK sdpk = new DistrictPK(fullContributedListDistrit
			 * .get(k).getDistrictCode(), fullContributedListDistrit.get(k)
			 * .getDistrictVersion()); districtDAO.updateIsActive(false, sdpk,
			 * session); // Subdistrict impacting code
			 * this.saveNewSubdistritVersion
			 * (fullContributedListDistrit.get(k).getDistrictCode(),
			 * fullContributedListDistrit.get(k).getDistrictVersion(), session);
			 * // Village impacting code
			 * 
			 * } DistrictPK sdpk = new DistrictPK(districtCode, distritVersion);
			 * isCommited = true; }
			 * 
			 * else if (fullContributedListDistrit.size() == 0 &&
			 * partContributedListDistrit.size() > 0) {
			 * 
			 * for (int k = 0; k < partContributedListDistrit.size(); k++) { //
			 * Subdistrict impacting code
			 * this.saveNewSubdistritVersion(partContributedListDistrit
			 * .get(k).getDistrictCode(),
			 * partContributedListDistrit.get(k).getDistrictVersion(), session);
			 * 
			 * } DistrictPK sdpk = new DistrictPK(districtCode, distritVersion);
			 * 
			 * isCommited = true; }
			 * 
			 * else if (fullContributedListDistrit.size() > 0 &&
			 * partContributedListDistrit.size() > 0) { // Full Contributed for
			 * (int k = 0; k < fullContributedListDistrit.size(); k++) {
			 * 
			 * DistrictPK sdpk = new DistrictPK(fullContributedListDistrit
			 * .get(k).getDistrictCode(), fullContributedListDistrit.get(k)
			 * .getDistrictVersion());
			 * 
			 * // Subdistricit impacting code this.saveNewSubdistritVersion(
			 * fullContributedListDistrit.get(k).getDistrictCode(),
			 * fullContributedListDistrit.get(k).getDistrictVersion(), session);
			 * } // ------------------------------------------------------- //
			 * Part Contributed for (int k = 0; k <
			 * partContributedListDistrit.size(); k++) {
			 * 
			 * DistrictPK sdpk = new DistrictPK(partContributedListDistrit
			 * .get(k).getDistrictCode(), partContributedListDistrit.get(k)
			 * .getDistrictVersion()); // ------------------- // Subdistrict
			 * impacting code
			 * this.saveNewSubdistritVersion(partContributedListDistrit
			 * .get(k).getDistrictCode(),
			 * partContributedListDistrit.get(k).getDistrictVersion(), session);
			 * 
			 * }
			 * 
			 * DistrictPK sdpk = new DistrictPK(districtCode, distritVersion);
			 * isCommited = true; } if (fullContributedList.size() > 0 &&
			 * partContributedList.size() == 0) {
			 * 
			 * for (int k = 0; k < fullContributedList.size(); k++) { // Land
			 * Region Replaces SubdistrictPK sdpk = new
			 * SubdistrictPK(fullContributedList .get(k).getSubdistrictCode(),
			 * fullContributedList .get(k).getSubdistrictVersion());
			 * 
			 * // Village impacting code
			 * this.saveNewVillageVersion(fullContributedList.get(k)
			 * .getSubdistrictCode(), fullContributedList.get(k)
			 * .getSubdistrictVersion(), session); id++; } isCommited = true; }
			 * else if (fullContributedList.size() == 0 &&
			 * partContributedList.size() > 0) {
			 * 
			 * for (int k = 0; k < partContributedList.size(); k++) {
			 * 
			 * // Village impacting code
			 * this.saveNewVillageVersion(partContributedList.get(k)
			 * .getSubdistrictCode(), partContributedList.get(k)
			 * .getSubdistrictVersion(), session); // --------------------
			 * 
			 * id++; } isCommited = true;
			 * 
			 * } else if (fullContributedList.size() > 0 &&
			 * partContributedList.size() > 0) {
			 * 
			 * for (int k = 0; k < fullContributedList.size(); k++) {
			 * 
			 * SubdistrictPK sdpk = new SubdistrictPK(fullContributedList
			 * .get(k).getSubdistrictCode(), fullContributedList
			 * .get(k).getSubdistrictVersion()); // Village impacting code
			 * this.saveNewVillageVersion(fullContributedList.get(k)
			 * .getSubdistrictCode(), fullContributedList.get(k)
			 * .getSubdistrictVersion(), session); id++; } //
			 * ------------------------------------------------------- // Part
			 * Contributed for (int k = 0; k < partContributedList.size(); k++)
			 * { // Land Region Replaces
			 * 
			 * SubdistrictPK sdpk2 = new
			 * SubdistrictPK(partContributedList.get(k).getSubdistrictCode(),
			 * partContributedList.get(k).getSubdistrictVersion());
			 * 
			 * // Village impacting code
			 * this.saveNewVillageVersion(partContributedList
			 * .get(k).getSubdistrictCode(),
			 * partContributedList.get(k).getSubdistrictVersion(), session);
			 * //-------------------- subdistrictDAO.updateIsActive(false,
			 * sdpk2, session); id++; } for (int i=0; i<vFullList.size();i++){
			 * saveNewVillageVersionFullContri
			 * (vFullList.get(i).getVillageCode(), fullContributedList.get(i)
			 * .getSubdistrictCode(), sdVersionCode, session); } for (int i=0;
			 * i<vPartList.size();i++){
			 * saveNewVillageVersionFullContri(vPartList
			 * .get(i).getVillageCode(),
			 * partContributedList.get(i).getSubdistrictCode(), sdVersionCode,
			 * session);
			 * saveNewVillageVersionPartContri(vPartList.get(i).getVillageCode
			 * (), session);
			 * 
			 * } isCommited = true; }
			 */

		} catch (Exception e) {
			log.debug("Exception" + e);
			// System.out.println("::::::::::RolledBack");
			isCommited = false;
		}

		return isCommited;
	}

	@Override
	public boolean stateDistrictmodifyreorg(StateForm sDForm, List<DistrictForm> listdistritNew, List<SubDistrictForm> listSubdistritNew, List<VillageForm> listNewVill, List<DistrictForm> listDistrictModify,
			List<SubDistrictForm> listSubdistrictModify, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean stateDistrictmodifyreorg(StateForm sdForm, List<DistrictForm> listNewDist, List<SubDistrictForm> listNewSubDist, List<VillageForm> listNewVillage, List<DistrictForm> listModifyDist, List<SubDistrictForm> listModifySubDist,
			HttpServletRequest request, HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		boolean isMethod5Commited = false;
		boolean isMethod6Commited = false;
		boolean isMethod7Commited = false;

		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(sdForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}

		try {

			for (int i = 0; i < listNewDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewDist.get(i).setStateNameEnglish(String.valueOf(stateCode));
				isMethod2Commited = districtService.publishSubdistrit(listNewDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		try {
			for (int i = 0; i < listNewSubDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewSubDist.get(i).setDistrictCode(String.valueOf(districtCode));
				//isMethod3Commited = SubistrictService.publish(listNewSubDist.get(i), session,);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		try {
			for (int i = 0; i < listNewVillage.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewVillage.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
				listNewVillage.get(i).setCreateFromExistingVillages(true);
				// isMethod4Commited=villageService.addVillage(listNewVillage.get(i));

				tx.commit();
				session.close();

			}
			for (int i = 0; i < listModifyDist.size(); i++) {
				isMethod5Commited = districtService.modifyDistrictInfo((listModifyDist.get(i)), request, httpSession);

			}
			for (int i = 0; i < listModifySubDist.size(); i++) {
				//isMethod6Commited = SubistrictService.modifySubDistrictInfo((listModifySubDist.get(i)), request, httpSession);

			}

			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true && isMethod4Commited == true && isMethod5Commited == true && isMethod6Commited == true) {

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
	}

	/* Retrieving the Map details attachment from the database */

	@Override
	public boolean stateDistrictonlymodifyreorg(StateForm sdForm, List<DistrictForm> listNewDist, List<SubDistrictForm> listNewSubDist, List<VillageForm> listNewVillage, List<DistrictForm> listModifyDist, HttpServletRequest request,
			HttpSession httpsession) throws Exception {

		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		boolean isMethod5Commited = false;
		boolean isMethod6Commited = false;
		boolean isMethod7Commited = false;

		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(sdForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}

		try {

			for (int i = 0; i < listNewDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewDist.get(i).setStateNameEnglish(String.valueOf(stateCode));
				isMethod2Commited = districtService.publishSubdistrit(listNewDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		try {
			for (int i = 0; i < listNewSubDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewSubDist.get(i).setDistrictCode(String.valueOf(districtCode));
				//isMethod3Commited = SubistrictService.publish(listNewSubDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		try {
			for (int i = 0; i < listNewVillage.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewVillage.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
				listNewVillage.get(i).setCreateFromExistingVillages(true);
				// isMethod4Commited=villageService.addVillage(listNewVillage.get(i));

				tx.commit();
				session.close();

			}
			for (int i = 0; i < listModifyDist.size(); i++) {
				isMethod5Commited = districtService.modifyDistrictInfo((listModifyDist.get(i)), request, httpsession);

			}

			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true && isMethod4Commited == true && isMethod5Commited == true) {

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
	}

	@Override
	public boolean stateDistrictReorg(StateForm sdForm, List<DistrictForm> listNewDist) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(sdForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			for (int i = 0; i < listNewDist.size(); i++) {

				listNewDist.get(i).setStateCode((stateCode));
				listNewDist.get(i).setStateNameEnglish(String.valueOf(stateCode));
				isMethod2Commited = districtService.publishSubdistrit(listNewDist.get(i), session);
			}
			tx.commit();
			session.close();

			if (isMethod1Commited == true && isMethod2Commited == true) {

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
	}

	@Override
	public boolean stateDistrictSubdistrictReorg(StateForm sdForm, List<DistrictForm> listNewDist, List<SubDistrictForm> listNewSubDist) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;

		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(sdForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}

		try {

			for (int i = 0; i < listNewDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewDist.get(i).setStateNameEnglish(String.valueOf(stateCode));
				isMethod2Commited = districtService.publishSubdistrit(listNewDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();
		try {

			for (int i = 0; i < listNewSubDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				listNewSubDist.get(i).setDistrictCode(String.valueOf(districtCode));
				//isMethod3Commited = SubistrictService.publish(listNewSubDist.get(i), session);
			}
			tx.commit();
			session.close();
			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true) {

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
	}

	@Override
	public boolean stateDistrictSubdistrictvillagemodifyReorg(StateForm sdForm, List<DistrictForm> listNewDist, List<SubDistrictForm> listNewSubDist, List<VillageForm> listNewVillage, List<DistrictForm> listModifyDist,
			List<SubDistrictForm> listModifySubDist, List<VillageForm> listModifyVillage, HttpServletRequest request, HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		boolean isMethod5Commited = false;
		boolean isMethod6Commited = false;
		boolean isMethod7Commited = false;

		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(sdForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}

		try {

			for (int i = 0; i < listNewDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewDist.get(i).setStateNameEnglish(String.valueOf(stateCode));
				isMethod2Commited = districtService.publishSubdistrit(listNewDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		try {
			for (int i = 0; i < listNewSubDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewSubDist.get(i).setDistrictCode(String.valueOf(districtCode));
				//isMethod3Commited = SubistrictService.publish(listNewSubDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		for (int i = 0; i < listNewVillage.size(); i++) {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			listNewVillage.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
			listNewVillage.get(i).setCreateFromExistingVillages(true);
			// isMethod4Commited=villageService.addVillage(listNewVillage.get(i));

			tx.commit();
			session.close();

		}
		for (int i = 0; i < listModifyDist.size(); i++) {
			isMethod5Commited = districtService.modifyDistrictInfo((listModifyDist.get(i)), request, httpSession);

		}
		for (int i = 0; i < listModifySubDist.size(); i++) {
			//isMethod6Commited = SubistrictService.modifySubDistrictInfo((listModifySubDist.get(i)), request, httpSession);

		}
		try {
			for (int i = 0; i < listModifyVillage.size(); i++) {
				isMethod7Commited = villageService.modifyVillageInfo((listModifyVillage.get(i)), request, httpSession);

			}
			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true && isMethod4Commited == true && isMethod5Commited == true && isMethod6Commited == true && isMethod7Commited == true) {

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
	}

	@Override
	public boolean stateDistrictSubdistrictvillageReorg(StateForm sdForm, List<DistrictForm> listNewDist, List<SubDistrictForm> listNewSubDist, List<VillageForm> listNewVillage) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(sdForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}

		try {
			for (int i = 0; i < listNewDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewDist.get(i).setStateNameEnglish(String.valueOf(stateCode));
				isMethod2Commited = districtService.publishSubdistrit(listNewDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		try {
			for (int i = 0; i < listNewSubDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewSubDist.get(i).setDistrictCode(String.valueOf(districtCode));
				//isMethod3Commited = SubistrictService.publish(listNewSubDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		try {

			for (int i = 0; i < listNewVillage.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewVillage.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
				listNewVillage.get(i).setCreateFromExistingVillages(true);
				// isMethod4Commited=villageService.addVillage(listNewVillage.get(i));
				tx.commit();
				session.close();

			}
			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true && isMethod4Commited == true) {

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
	}

	@Override
	public boolean stateDistrictSubdistrictvillageReorgShift(StateForm sdForm, List<DistrictForm> listNewDist, List<SubDistrictForm> listNewSubDist, List<VillageForm> listNewVillage, List<ShiftDistrictForm> listdistritShift,
			List<ShiftSubDistrictForm> listsubdistritShiftList, List<ShiftVillageForm> listvillageShift, HttpServletRequest request) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		boolean isMethod5Commited = false;
		boolean isMethod6Commited = false;

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			try {
				isMethod1Commited = this.saveState(sdForm, session);
				tx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				log.debug("Exception" + e);
			}
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			for (int i = 0; i < listdistritShift.size(); i++) {
				isMethod4Commited = shiftService.shiftDistrictReorganize(listdistritShift.get(i), request);

			}
			// shiftService.shiftDistrict(shiftDistrictForm,request);
			for (int i = 0; i < listsubdistritShiftList.size(); i++) {
				isMethod5Commited = shiftService.shiftSubDistrictReorganize(listsubdistritShiftList.get(i), request);

			}
			// shiftService.shiftDistrict(shiftDistrictForm,request);
			for (int i = 0; i < listvillageShift.size(); i++) {
				isMethod6Commited = shiftService.shiftVillagereorganize(listvillageShift.get(i), request);
			}
			try {

				for (int i = 0; i < listNewDist.size(); i++) {
					listNewDist.get(i).setStateNameEnglish(String.valueOf(stateCode));
					isMethod2Commited = districtService.publishSubdistrit(listNewDist.get(i), session);
				}
				tx.commit();
				session.close();

				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				for (int i = 0; i < listNewSubDist.size(); i++) {
					listNewSubDist.get(i).setDistrictCode(String.valueOf(districtCode));
				//	isMethod3Commited = SubistrictService.publish(listNewSubDist.get(i), session);
					tx.commit();
					session.close();
				}

				for (int i = 0; i < listNewVillage.size(); i++) {
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					listNewVillage.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
					listNewVillage.get(i).setCreateFromExistingVillages(true);
					// isMethod4Commited=villageService.addVillage(listNewVillage.get(i));
					tx.commit();
					session.close();

				}
				// shiftService.shiftDistrict(shiftDistrictForm,request);

				if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true && isMethod4Commited == true && isMethod5Commited == true && isMethod6Commited == true) {

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
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return isCommited;
	}

	@Override
	public boolean stateDistrictSubdistrictvillageReorgShiftmodify(StateForm sdForm, List<DistrictForm> listNewDist, List<SubDistrictForm> listNewSubDist, List<VillageForm> listNewVillage, List<ShiftDistrictForm> listdistritShift,
			List<ShiftSubDistrictForm> listsubdistritShiftList, List<ShiftVillageForm> listvillageShift, List<DistrictForm> listDistmodify, List<SubDistrictForm> listSubmodify, List<VillageForm> listVillmodify, HttpServletRequest request,
			HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		boolean isMethod5Commited = false;
		boolean isMethod6Commited = false;
		boolean isMethod7Commited = false;
		boolean isMethod8Commited = false;
		boolean isMethod9Commited = false;
		boolean isMethod10Commited = false;

		Session session = null;
		Transaction tx = null;
		for (int i = 0; i < listdistritShift.size(); i++) {
			isMethod1Commited = shiftService.shiftDistrictReorganize(listdistritShift.get(i), request);

		}
		// shiftService.shiftDistrict(shiftDistrictForm,request);
		for (int i = 0; i < listsubdistritShiftList.size(); i++) {
			isMethod2Commited = shiftService.shiftSubDistrictReorganize(listsubdistritShiftList.get(i), request);
		}
		// shiftService.shiftDistrict(shiftDistrictForm,request);
		for (int i = 0; i < listvillageShift.size(); i++) {
			isMethod3Commited = shiftService.shiftVillagereorganize(listvillageShift.get(i), request);
		}
		for (int i = 0; i < listDistmodify.size(); i++) {
			isMethod4Commited = districtService.modifyDistrictInfo((listDistmodify.get(i)), request, httpSession);

		}
		for (int i = 0; i < listSubmodify.size(); i++) {
			//isMethod5Commited = SubistrictService.modifySubDistrictInfo((listSubmodify.get(i)), request, httpSession);
		}
		for (int i = 0; i < listVillmodify.size(); i++) {
			isMethod6Commited = villageService.modifyVillageInfo((listVillmodify.get(i)), request, httpSession);
		}
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		try {
			// isMethod7Commited = this.saveState(sdForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}

		try {

			for (int i = 0; i < listNewDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				listNewDist.get(i).setStateNameEnglish(String.valueOf(stateCode));
				// isMethod8Commited = districtService.publishSubdistrit(
				// listNewDist.get(i), session);
			}
			tx.commit();
			session.close();

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			for (int i = 0; i < listNewSubDist.size(); i++) {
				listNewSubDist.get(i).setDistrictCode(String.valueOf(districtCode));
				// isMethod9Commited = SubistrictService.publish(
				// listNewSubDist.get(i), session);
				tx.commit();
				session.close();
			}

			for (int i = 0; i < listNewVillage.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewVillage.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
				listNewVillage.get(i).setCreateFromExistingVillages(true);
				// isMethod10Commited=villageService.addVillage(listNewVillage.get(i));
				tx.commit();
				session.close();

			}
			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true && isMethod4Commited == true && isMethod5Commited == true && isMethod6Commited == true && isMethod7Commited == true && isMethod8Commited == true
					&& isMethod9Commited == true && isMethod10Commited == true) {

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
	}

	@Override
	public boolean statemodifyReorg(StateForm sdForm, List<DistrictForm> listNewDist, List<DistrictForm> listNewmodify, HttpServletRequest request, HttpSession httpsession) throws Exception {

		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(sdForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}

		try {

			for (int i = 0; i < listNewDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewDist.get(i).setStateNameEnglish(String.valueOf(stateCode));
				isMethod2Commited = districtService.publishSubdistrit(listNewDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		try {
			for (int i = 0; i < listNewmodify.size(); i++) {
				isMethod3Commited = districtService.modifyDistrictInfo((listNewmodify.get(i)), request, httpsession);
			}

			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true) {

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
	}

	@Override
	public List<State> statesearch(String s) throws Exception {
		s = s.substring(0, s.length() - 1);
		s = s.toUpperCase();

		return stateDao.getStatebyname(s);

	}

	@Override
	public boolean StateSubdistrictmodifyReorg(StateForm SDForm, List<SubDistrictForm> listNewVillForm, List<DistrictForm> listNewModify, HttpServletRequest request, HttpSession httpsession) throws Exception {

		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		Session session = null;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(SDForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		try {

			for (int i = 0; i < listNewVillForm.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				listNewVillForm.get(i).setDistrictCode(String.valueOf(districtCode));
				//isMethod2Commited = SubistrictService.publish(listNewVillForm.get(i), session);
			}
			tx.commit();
			session.close();

			for (int i = 0; i < listNewModify.size(); i++) {
				isMethod3Commited = districtService.modifyDistrictInfo((listNewModify.get(i)), request, httpsession);

			}
			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true) {
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
	}

	@Override
	public boolean SubdistrictmodifyReorg(StateForm sdForm, List<SubDistrictForm> listNewSubDist, List<SubDistrictForm> listNewSubdistrictmodify, HttpServletRequest request, HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		Session session = null;
		Transaction tx = null;
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		try {
			isMethod1Commited = this.saveState(sdForm, session);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}

		try {

			for (int i = 0; i < listNewSubDist.size(); i++) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				listNewSubDist.get(i).setDistrictCode(String.valueOf(districtCode));
				//isMethod2Commited = SubistrictService.publish(listNewSubDist.get(i), session);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
		tx.commit();
		session.close();

		try {
			for (int i = 0; i < listNewSubdistrictmodify.size(); i++) {
				//isMethod3Commited = SubistrictService.modifySubDistrictInfo((listNewSubdistrictmodify.get(i)), request, httpSession);

			}

			if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true) {

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
	}

	@Override
	public boolean villagemodifyReorg(StateForm sdForm, List<VillageForm> listNewVillage, List<VillageForm> listNewVillagemodify, HttpServletRequest request, HttpSession httpSession) throws Exception {
		boolean isCommited = false;
		boolean isMethod1Commited = false;
		boolean isMethod2Commited = false;
		boolean isMethod3Commited = false;
		boolean isMethod4Commited = false;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			try {
				isMethod1Commited = this.saveState(sdForm, session);
				tx.commit();
			} catch (Exception e) {
				// TODO: handle exception
				log.debug("Exception" + e);
			}
			try {
				for (int i = 0; i < listNewVillage.size(); i++) {
					session = sessionFactory.openSession();
					tx = session.beginTransaction();
					listNewVillage.get(i).setSubdistrictNameEnglish(String.valueOf(sdCode));
					listNewVillage.get(i).setCreateFromExistingVillages(true);
					isMethod2Commited = villageService.addVillage(listNewVillage.get(i));
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.debug("Exception" + e);
			}
			tx.commit();
			session.close();
			try {
				for (int i = 0; i < listNewVillagemodify.size(); i++) {
					isMethod3Commited = villageService.modifyVillageInfo((listNewVillagemodify.get(i)), request, httpSession);

				}
				if (isMethod1Commited == true && isMethod2Commited == true && isMethod3Commited == true) {
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
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return isCommited;
	}

	@Override
	public String getStateNameEnglish(Integer stateCode) {
		return (stateDao.getStateNameEnglish(stateCode));
	}

	@Override
	public boolean checkStateExist(Integer stateCode) {
		return stateDao.checkStateExist(stateCode);
	}

	/**
	 * Get All States For Extended Department Functionality.
	 * 
	 * @author Ripunj on 10-10-2014
	 */
	@Override
	public List<State> getStateSourceListExtended(Integer orgCode, Integer orgLocatedLevelCode) throws Exception {
		return stateDao.getAllStatesExtended(orgCode, orgLocatedLevelCode);
	}

	/**
	 * Get All States For Extended Department Functionality without
	 * selected @stateList.
	 * 
	 * @author Ripunj on 10-10-2014
	 */
	@Override
	public List<State> getAllNotInStatesExtended(String stateList, Integer orgCode, Integer orgLocatedLevelCode) {
		return stateDao.getAllNotInStatesExtended(stateList, orgCode, orgLocatedLevelCode);
	}

	/* Added by Kirandeep for the gigw work on 23/12/2014 */

	/*
	 * @Override public List<String> getDetailsofDocument(String type){
	 * List<String> details=new ArrayList<String>();
	 * 
	 * String filetype=null; double filelength=0; String lastmodified=null;
	 * String directoryLocation =null; String fileName=null; String
	 * filePath=null;
	 * 
	 * 
	 * if(type.equalsIgnoreCase("cbt")){ directoryLocation =
	 * LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("userManual.directory.location").toString();
	 * fileName = LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("userManual.brochure.fileName.CBT").toString();
	 * filePath = directoryLocation+File.separator+fileName; }
	 * 
	 * if(type.equalsIgnoreCase("presentation")){ directoryLocation =
	 * LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("userManual.directory.location").toString();
	 * fileName = LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("userManual.cbt.presentation").toString();
	 * filePath = directoryLocation+File.separator+fileName; }
	 * 
	 * 
	 * if(type.equalsIgnoreCase("usermanual")){ directoryLocation =
	 * LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("userManual.directory.location").toString();
	 * fileName = LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("userManual.manual.fileName").toString();
	 * filePath = directoryLocation+File.separator+fileName; }
	 * 
	 * if(type.equalsIgnoreCase("brochure")){ directoryLocation =
	 * LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("userManual.directory.location").toString();
	 * fileName = LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("userManual.brochure.fileName").toString();
	 * filePath = directoryLocation+File.separator+fileName;
	 * 
	 * }
	 * 
	 * if(type.equalsIgnoreCase("register")){ directoryLocation =
	 * LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("userManual.directory.location").toString();
	 * fileName = LGDResourceBundle.getBundle("upload_info",
	 * Locale.ENGLISH).getObject("dataRegister.fileName").toString(); filePath =
	 * directoryLocation+File.separator+fileName; }
	 * 
	 * File f = new File(filePath);
	 * filetype=FilenameUtils.getExtension(filePath);
	 * 
	 * SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
	 * lastmodified= sdf.format(f.lastModified()); filelength=f.length();
	 * 
	 * 
	 * filelength=filelength/1024;
	 * 
	 * details.add(filetype); details.add(String.valueOf(filelength));
	 * details.add(lastmodified);
	 * 
	 * return details;
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	/**
	 * Anju 1-1-2015 to get all state list
	 */
	public List<State> getAllStates() throws Exception {
		return stateDao.getAllStates();
	}

	/* added by ashish dhupia for menu disable functionality on 4/2/2015 */
	@Override
	public List<MenuProfile> getDisableMenueList(Integer slc, Integer districtCode) throws Exception {
		// TODO Auto-generated method stub
		return stateDao.getDisableMenueList(slc, districtCode);
	}

	@Override
	public List<Search> getStateSearchDetailByCode(int entityCodeForSearch, String entityCode) throws Exception {
		List<Search> SearchState = new ArrayList<Search>();
		try {
			SearchState = stateDao.getStateSearchDetailByCode(entityCodeForSearch, entityCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return SearchState;
	}
	/**
	 * Get all State List with operation state code used in coverage
	 * Administrative Entity at center level.
	 * 
	 * @author Pooja
	 * @since 21-10-2015
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<State> getStateListWithOperationState() throws Exception {
		return stateDao.getStateListWithOperationState();
	}
	
	/**
	 * Code used to get Is Pesa Act implemented for the state or not.
	 * 
	 * @author Pooja
	 * @since 19-05-2016
	 * @param stateCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public Boolean isPesaState(Integer stateCode) throws Exception {
		return stateDao.isPesaState(stateCode);
	}
	
	@Override
	public Integer getStateCode(Integer districtCode)throws Exception{
		return stateDao.getStateCode(districtCode);
	}
	/*
	@Override
	public List<AllSearch> getAllSearchDetailByCode(String entityCodeForSearch, String entityCode, boolean isByCode) throws Exception{
		return stateDao.getAllSearchDetailByCode(entityCodeForSearch,entityCode,isByCode);
	}*/

	@Override
	public boolean saveNodalOfficerDetail(NodalOfficerState nodalOfficerState) throws Exception {
		return stateDao.saveNodalOfficerDetail(nodalOfficerState);
	}

	@Override
	public boolean sendOTPForLGDDataConfirmation(Long userId) throws Exception {
		return stateDao.sendOTPForLGDDataConfirmation(userId);
	}

	@Override
	public Object[] getNodalOfficerDetails(Integer entityCode,Character entityType,Long userId,Boolean isNodalOfficeDet,Integer stateCode)throws Exception {
		return stateDao.getNodalOfficerDetails(entityCode, entityType,userId,isNodalOfficeDet,stateCode);
	}

	@Override
	public Character validateOTP(String userOTP, Long userId) throws Exception {
		return stateDao.validateOTP(userOTP, userId);
	}

	@Override
	public boolean saveLGDDataConfirmation(LgdDataConfirmation lgdDataConfirmation) throws Exception {
		return stateDao.saveLGDDataConfirmation(lgdDataConfirmation);
	}

	@Override
	public boolean updateNodalOfficerDetail(NodalOfficerState nodalOfficerState,NodalOfficerState existNodalOfficerState) throws Exception {
		return stateDao.updateNodalOfficerDetail(nodalOfficerState, existNodalOfficerState);
	}

	@Override
	public List<DistrictFreezeEntity> getDistrictwiseFreezeStatus(Integer stateCode,Character userType) throws Exception {
		return stateDao.getDistrictwiseFreezeStatus(stateCode,userType);
	}

	@Override
	public boolean saveDistrictUnfreezebyState(LgdDataConfirmation lgdDataConfirmation)throws Exception {
		return stateDao.saveDistrictUnfreezebyState(lgdDataConfirmation);
	}

	@Override
	public boolean getFreezeStatusbyState(Integer stateCode, Character userType, Character checkLevel)throws Exception {
		return stateDao.getFreezeStatusbyState(stateCode, userType, checkLevel);
	}

	@Override
	public boolean saveStateFreezeUnfreezebyState(LgdDataConfirmation lgdDataConfirmation) throws Exception {
		return stateDao.saveStateFreezeUnfreezebyState(lgdDataConfirmation);
	}

	@Override
	public Character getUserTypeofNodalOfficer(Long lUserId)throws Exception{
		return stateDao.getUserTypeofNodalOfficer(lUserId);
	}

	@Override
	public boolean getFreezeStatusbyUserId(Long userId, Character userType, Character level,Integer stateCode) throws Exception {
		return stateDao.getFreezeStatusbyUserId(userId, userType, level,stateCode);
	}

	@Override
	public List<EntityFreezeStatus> getEntityFreezeStatus(Integer stateCode) throws Exception {
		return stateDao.getEntityFreezeStatus(stateCode);
	}

	@Override
	public String getGisTokenValue() throws Exception {
		return stateDao.getGisTokenValue();
	}

	/*@Override
	public List<FreezeLocalBodyEntity> getListOfUlbs(Integer districtCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<AuditTrailLGD> getAuditTrailLGD() throws Exception {
		return stateDao.getAuditTrailLGD();
	}

	/*@Override
	public List<ParentwiseChildDetials> getUlbDeatilsList(Integer districtCode) throws Exception {
		// TODO Auto-generated method stub
		return stateDao.getUlbDeatilsList(districtCode);
	}*/

	@Override
	public String getStateSetupType(Integer stateCode) throws Exception {
		// TODO Auto-generated method stub
		return stateDao.getStateSetupType(stateCode);
	}

	@Override
	public List<ParentwiseChildDetials> getparentwisecountofBPandGP(Integer districtCode) throws Exception {
		// TODO Auto-generated method stub
		return stateDao.getparentwisecountofBPandGP(districtCode);
	}

	@Override
	public List<DistrictFreezeEntity> getDistrictwiseFreezeStatusULB(Integer stateCode, Character userType)
			throws Exception {
		// TODO Auto-generated method stub
		return stateDao.getDistrictwiseFreezeStatusULB(stateCode,userType);
	}

	@Override
	public List<FreezeLocalBodyEntity> freezeUnfreezeLocalBodyEntity(Integer districtCode, Character parentType,
			Character userType, Long userId) throws Exception {
		// TODO Auto-generated method stub
		return stateDao.freezeUnfreezeLocalBodyEntity(districtCode,parentType,userType, userId);
	}

	@Override
	public boolean saveConfigurationLGDUpdation(FreezeUnfreezeStateConfigEntity  freezeUnfreezeStateConfigEntity,LgdDataConfirmation lgdDataConfirmation)throws Exception
	{
		return stateDao.saveConfigurationLGDUpdation(freezeUnfreezeStateConfigEntity,lgdDataConfirmation);
	}

	@Override
	public FreezeUnfreezeStateConfigEntity getConfigurationOfLGDDataConfirmation(Integer stateCode, Character userType)
			throws Exception {
		return stateDao.getConfigurationOfLGDDataConfirmation(stateCode, userType);
	}

	@Override
	public boolean saveStateFreezeUnfreezebyStateOnly(Integer stateCode, Character userType,Character status,Long userId,String fileName) throws Exception {
		// TODO Auto-generated method stub
		return stateDao.saveStateFreezeUnfreezebyStateOnly(stateCode, userType,status, userId,fileName);
	}


	


	@Override
	public  List<OrganizationByCentreLevel> getOrganisationList(Integer stateCode) throws Exception {
		// TODO Auto-generated method stub
		return stateDao.getOrganisationList(stateCode);
	}

	@Override
	public  boolean saveConfigurationLGDUpdation(ConfigurationBlockVillageMapping saveconfigBlockVillageMapping) throws Exception {
		// TODO Auto-generated method stub
		return stateDao.saveConfigurationLGDUpdation(saveconfigBlockVillageMapping);
	}

	@Override
	public boolean saveconfigBlockVillageMapping(ConfigurationBlockVillageMapping configBlockVillageMapping)
			throws Exception {
		// TODO Auto-generated method stub
		return stateDao.saveConfigurationLGDUpdation(configBlockVillageMapping);
	}

	@Override
	public ConfigurationBlockVillageMapping getconfigureBlockVillage(Long userId, Integer stateCode)
			throws Exception {
		// TODO Auto-generated method stub
		return stateDao.getconfigureBlockVillage(userId,stateCode);
	}

	
	@Override
	public Response saveEffectiveDateEntityState(List<GetEntityEffectiveDate> getEntityEffectiveDateList,
			Long userId) throws Exception {
		
		return stateDao.saveEffectiveDateEntityState(getEntityEffectiveDateList, userId);
	}
	
	
	
	}
	

	
