
package in.nic.pes.lgd.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.entities.LocalBodyEntityDetails;
import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.entities.ManageLBDetails;
import com.cmc.lgd.localbody.services.LocalBodyService;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GetEntityEffectiveDate;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.Habitation;
import in.nic.pes.lgd.bean.HabitationConfiguration;
import in.nic.pes.lgd.bean.LocalbodyListbyStateold;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.VPDeNotifiedCoveraged;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillageDraft;
import in.nic.pes.lgd.bean.VillageHistory;
import in.nic.pes.lgd.bean.VillagePanchyatDeNotified;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.constant.CommanConstant;
import in.nic.pes.lgd.constant.VillageConstant;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.HabitationForm;
import in.nic.pes.lgd.forms.LBFreezeForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.ConfigMapService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.GovtOrderTemplateService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.StateDistrictFreezeService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.CommonUtil;
import in.nic.pes.lgd.validator.AbstractValidator;
import in.nic.pes.lgd.validator.CommonValidatorImpl;
import in.nic.pes.lgd.validator.HabitationValidator;
import in.nic.pes.lgd.validator.VillageValidator;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

@Controller
public class VillageController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(VillageController.class);
	@Autowired
	FileUploadUtility fileUploadUtility;
	

	@Autowired
	CommonValidatorImpl commonValidator;

	@Autowired
	VillageService villageService;

	@Autowired
	VillageValidator villageValidator;

	@Autowired
	DistrictService districtService;

	@Autowired
	SubDistrictService subDistrictService;

	@Autowired
	GovtOrderTemplateService govtOrderTemplateService;

	@Autowired
	ConfigGovtOrderService configGovtOrderService;

	@Autowired
	ConfigMapService configMapService;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	CommonService commonservice;

	@Autowired
	EmailService emailService;

	@Autowired
	LocalGovtBodyService localGovtBodyService;

	@Autowired
	private StateDistrictFreezeService stateDistrictFService;

	@Autowired
	private StateDAO stateDao;

	@Autowired
	AbstractValidator abstractValidator;
	
	@Autowired
	private LocalBodyService localBodyService;
	
	@Autowired
	private HabitationValidator habitationValidator;

	/**
	 * List of Attachments for Government Order and Map.
	 */
	List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
	List<Attachment> attachmentList = new ArrayList<Attachment>();
	/*
	 * @Autowired LbCoveredLandregionService lbCoveredLandregionService;
	 */

	private Integer stateCode;

	private Integer districtCode;

	private Long userId;
	
	public static class EffectiveDateList extends ArrayList<GetEntityEffectiveDate> {}

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
	}

	// Added on 21-10-11 Chandan
	@RequestMapping(value = "/invalidatevillage", method = RequestMethod.GET)
	public ModelAndView preInvalidateVillage(@ModelAttribute("invalidatevillage") VillageForm invalidateVillageMAV, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		
		try {
			List<District> districtList = null;
			districtList = new ArrayList<District>();
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}

			/*
			 * if(districtCode>0) districtList =
			 * districtService.getDistrictListByDistCode(districtCode); else
			 * 
			 * districtList = districtService.getDistrictList(stateCode);
			 */

			// Copy to Check Configuration
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 14, 'V');// 10
																								// is
																								// operation
																								// code
																								// for
																								// create
																								// new
																								// village
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			String message = hMap.get("message");
			if (govtOrderConfig != null && mapConfig != null) {
				mav = new ModelAndView("invalidateVillageMAV");
				if (districtCode == 0) {

					mav.addObject("flag1", 1);
					districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);

				} else {
					districtList = districtService.getDistrictListByDistCode(districtCode);
					// model.addAttribute("distList", distList);
					mav.addObject("flag1", 0);
					mav.addObject("flag2", districtCode);
				}

				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("districtList", districtList);
				mav.addObject("stateCode", stateCode);

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		// ModelAndView mav=new ModelAndView("invalidateVillageMAV");
		/*
		 * mv = new ModelAndView("invalidateVillageMAV");
		 * mv.addObject("invalidateVillageMAV", invalidateVillageMAV);
		 * mv.addObject("districtList", districtList);
		 */
		return mav;
	}

	@RequestMapping(value = "/enterInvalidateVillageOrderDetails", method = RequestMethod.POST)
	public ModelAndView enterInvalidateVillageOrderDetails(@ModelAttribute("invalidatevillage") VillageForm villageForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mv = null;
		Session session = null;
		try {
			String govtOrderConfig = null;
			List<District> districtList = null;
			districtList = new ArrayList<District>();

			String aMessage = "";
			String ordercode = null;
			String orderCode = null;
			String tid = null;
			Integer ulbCode = null;
			// Copy to Check Configuration
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 14, 'V');// 10
																								// is
																								// operation
																								// code
																								// for
																								// create
																								// new
																								// village
			govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			villageValidator.villageInvalidationValidation(villageForm, result, request);
			if (result.hasErrors()) {
				districtList = districtService.getDistrictList(stateCode);
				mv = new ModelAndView("invalidateVillageMAV");
				mv.addObject("districtList", districtList);
				mv.addObject("govtOrderConfig", govtOrderConfig);
				mv.addObject(villageForm);

			} else {
				mv = new ModelAndView("invalidateView");
				String isExistGovtOrder = request.getParameter("checkExistingGovtOrder");
				villageForm.setIsExistingGovtOrder(isExistGovtOrder);
				Integer orderCodeForInvalidateVil = 0;
				if ("Y".equals(isExistGovtOrder)) {
					orderCodeForInvalidateVil = villageForm.getOrderCode();
				}
				govtOrderConfig = villageForm.getGovtOrderConfig();
				httpSession.setAttribute("formbean", villageForm);
				VillageForm villageFormDet = (VillageForm) httpSession.getAttribute("formbean");
				int templCode = 0;
				List<GovernmentOrderTemplate> templateList = new ArrayList<GovernmentOrderTemplate>();
				templateList = new ArrayList<GovernmentOrderTemplate>();
				// char operation = villageFormDet.getOperation();
				govtOrderConfig = villageFormDet.getGovtOrderConfig();
				if (govtOrderConfig.equals("govtOrderGenerate")) {
					templateList = govtOrderTemplateService.getTemplateListByOperationCode(14);
					mv.addObject("templateList", templateList);
				}
				mv.addObject("govtOrderConfig", govtOrderConfig);
				if (villageForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(villageForm.getTemplateList());
					villageForm.setTemplateList(villageForm.getTemplateList());
					httpSession.setAttribute("formbean", villageForm);
					String templateBodySrc = govtOrderService.previewTemplate(templCode, httpSession);
					villageForm.setTemplateBodySrc(templateBodySrc);
					mv = new ModelAndView("previewGovtOrder");
				} else if (villageForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					String villCode = villageForm.getInvalidateVillageList();
					
					String destinationVillage = null;
					Integer distinationsubdistrict = null;
					Character operationState = 'P';
					if (villageForm.getSelectedCoveredLandRegionByULB() != null) {
						ulbCode = Integer.parseInt(villageForm.getSelectedCoveredLandRegionByULB());
					}
					if (villageForm.getButtonClicked() != null) {
						operationState = villageForm.getButtonClicked();
					}
					ordercode = villageService.invalidateVillageNew(villCode, userId.intValue(), villageForm.getOrderNo(), villageForm.getOrderDate(), villageForm.getEffectiveDate(), villageForm.getGovtOrderConfig(), villageForm.getGazPubDate(),
							destinationVillage, distinationsubdistrict, ulbCode, orderCodeForInvalidateVil, isExistGovtOrder, operationState);
					villageForm = (VillageForm) httpSession.getAttribute("formbean");
					if (ordercode != null) {
						if (operationState == 'S') {
							aMessage = "Village(s) Invalidated Successfully in Draft Mode";
						} else {
							aMessage = "Village(s) Invalidated Successfully";
						}
						mv = new ModelAndView("invalidateView");
						List<List<Village>> villageList = null;
						villageList = new ArrayList<List<Village>>();
						if (operationState == 'P')
							villageList = villageService.getVillageViewList(villageForm);
						else {
							mv = new ModelAndView("success");
							mv.addObject("message", aMessage);
						}
						mv.addObject("msgid", aMessage);
						mv.addObject("success", villageForm.getInvalidateVillageList());
						mv.addObject("villageList", villageList);
						session = sessionFactory.openSession();
						Transaction tx = session.beginTransaction();
						if (ordercode != null) {
							String[] ldata = ordercode.split(",");
							orderCode = ldata[0];
							int Transactionid = 0;
							attachmentList = new ArrayList<Attachment>();
							List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillage(request, villageForm, result);
							if (villageForm.getOrderCode() != null)
								attachmentList = govtOrderService.getAttachmentsbyOrderCode(villageForm.getOrderCode());
							if (villageForm.getButtonClicked() == 'S') {
								if (("Y").equalsIgnoreCase(isExistGovtOrder)) {
									validFileGovtUpload = new ArrayList<AttachedFilesForm>();
									AttachedFilesForm filesForm = new AttachedFilesForm();
									filesForm.setFileLocation(attachmentList.get(0).getFileLocation());
									filesForm.setFileType(attachmentList.get(0).getFileContentType());
									filesForm.setFileName(attachmentList.get(0).getFileName());
									filesForm.setFileSize(attachmentList.get(0).getFileSize());
									filesForm.setFileTimestampName(attachmentList.get(0).getFileTimestamp());
									validFileGovtUpload.add(filesForm);
								}
								// here orderCode is max village draft Code
								// boolean insertGovtTableFlag =
								villageService.saveDataInAttachDraftVilCreate(villageForm, validFileGovtUpload, Integer.parseInt(orderCode), request.getSession());
							}
							if (operationState == 'P') {
								tid = ldata[1];
								Transactionid = Integer.parseInt(tid);
								int ocode = Integer.parseInt(orderCode);
								if ("N".equals(villageForm.getIsExistingGovtOrder())) {
									GovernmentOrder govtOrder = new GovernmentOrder();
									govtOrder.setOrderDate(villageForm.getOrderDate());
									govtOrder.setEffectiveDate(villageForm.getEffectiveDate());
									govtOrder.setGazPubDate(villageForm.getGazPubDate());
									govtOrder.setCreatedon(new Date());
									govtOrder.setDescription("LGD DETAILS");
									govtOrder.setIssuedBy("GOVERNOR");
									govtOrder.setLastupdated(new Date());
									govtOrder.setLevel("U");
									govtOrder.setOrderNo(villageForm.getOrderNo());
									govtOrder.setStatus('A');
									govtOrder.setOrderCode(ocode);
									convertLocalbodyService.saveDataInAttachment(govtOrder, null, validFileGovtUpload, session);
								}
							}
							tx.commit();
							if (operationState == 'P') {
								EmailSmsThread est = new EmailSmsThread(Transactionid, 'V', emailService);
								est.start();
							}
						}
					} else {
						aMessage = "Error occured while Invalidating Villages ";
						mv = new ModelAndView("error");
						mv.addObject("message", aMessage);

					}
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return mv;
	}

	//// code by siva///////////////////////////

	@RequestMapping(value = "/invalidatevill", method = RequestMethod.POST)
	public ModelAndView invalidateVillage(@ModelAttribute("invalidatevillage") VillageForm invalidateVillageMAV, BindingResult result, SessionStatus status, HttpSession httpsession, HttpSession httpSession, HttpServletRequest request)
			throws Exception {
		ModelAndView mv = null;
		Session session = null;
		String ordercode = null;
		String orderCode = null;
		String tid = null;
		Integer ulbCode = null;

		try {

			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) httpsession.getAttribute("govtOrderForm");
			// Session session = sessionFactory.openSession();
			// Transaction tx = session.beginTransaction();
			try {

				invalidateVillageMAV = (VillageForm) httpsession.getAttribute("formbean");
				String orderNo;
				// List<CommonsMultipartFile> tmp_govtOrder=new ArrayList<>();
				if (invalidateVillageMAV.getSelectedCoveredLandRegionByULB() != null) {
					ulbCode = Integer.parseInt(invalidateVillageMAV.getSelectedCoveredLandRegionByULB());
				}
				orderNo = govtOrderForm.getOrderNo();
				// tmp_govtOrder=govtOrderForm.getAttachFile1();
				String govtOrder = govtOrderForm.getOrderNo();
				// String govtOrder=tmp_govtOrder.get(0).getOriginalFilename();
				Date effectiveDate = new Date();
				effectiveDate = govtOrderForm.getEffectiveDate();
				Date orderDate = new Date();
				orderDate = govtOrderForm.getOrderDate();
				Date gzbDate = new Date();
				gzbDate = govtOrderForm.getGazettePublicationdate();

				String villCode = invalidateVillageMAV.getInvalidateVillageList();

				// Integer userId=4002;
				String destinationVillage = null;
				Integer distinationsubdistrict = null;

				ordercode = villageService.invalidateVillageNew(villCode, userId.intValue(), orderNo, orderDate, effectiveDate, govtOrder, gzbDate, destinationVillage, distinationsubdistrict, ulbCode, 0, "N", 'P');

			} catch (Exception e) {
				// tx.rollback();
				// session.close();
				log.debug("Exception" + e);
			}

			if (ordercode != null) {
				String aMessage = "Village(s) Invalidated Successfully";
				List<List<Village>> villageList = null;
				villageList = new ArrayList<List<Village>>();
				villageList = villageService.getVillageViewList(invalidateVillageMAV);
				mv = new ModelAndView("invalidateView");
				mv.addObject("msgid", aMessage);
				mv.addObject("success", invalidateVillageMAV);
				mv.addObject("villageList", villageList);

				AddAttachmentBean addAttachmentBean = null;
				AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
				addAttachmentBean = getAttachmentBean(govtOrderForm, request, result);
				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);

				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					attachmentHandler.saveMetaDataINFileSystem(validFileGovtUpload, addAttachmentBean);
				}
				session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				if (ordercode != null) {
					String[] ldata = ordercode.split(",");
					orderCode = ldata[0];
					tid = ldata[1];
				}

				int Transactionid = Integer.parseInt(tid);
				int ocode = Integer.parseInt(orderCode);
				GovernmentOrder govtOrder = new GovernmentOrder();
				govtOrder.setOrderDate(invalidateVillageMAV.getOrderDate());
				govtOrder.setEffectiveDate(invalidateVillageMAV.getEffectiveDate());
				govtOrder.setGazPubDate(invalidateVillageMAV.getGazPubDate());
				govtOrder.setCreatedon(new Date());
				govtOrder.setDescription("LGD DETAILS");
				govtOrder.setIssuedBy("GOVERNOR");
				// govtOrder.setCreatedby(userId);
				govtOrder.setLastupdated(new Date());
				// govtOrder.setLastupdatedby(createdBy);
				govtOrder.setLevel("U");
				govtOrder.setOrderNo(invalidateVillageMAV.getOrderNo());
				govtOrder.setStatus('A');
				// govtOrder.setUserId(userId);
				govtOrder.setOrderCode(ocode);
				convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm, validFileGovtUpload, session);
				tx.commit();
				EmailSmsThread est = new EmailSmsThread(Transactionid, 'V', emailService);
				est.start();

			} else {
				String aMessage = "Error occured while Invalidating Villages ";
				mv = new ModelAndView("error");
				mv.addObject("message", aMessage);

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} finally {

			httpsession.removeAttribute("formbean");
			httpsession.removeAttribute("govtOrderForm");
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return mv;
	}

	

	

	/**
	 * Modified on 16-08-2014 Add Government order form details based on
	 * GovtOrderUpload and GovtOrderTemplate.
	 */
	@RequestMapping(value = "/createVillage", method = RequestMethod.GET)
	public ModelAndView createVillage(@ModelAttribute("addVillageNew") VillageForm addVillageNew, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
	try {
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			int operationCode = Integer.parseInt(VillageConstant.CREATE_VILLAGE_OP_CODE.toString());
			
			Map<String, String> mapGovOrderConfig = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, 'V');
			Map<String, String> mapMapConfig = govtOrderService.getMapConfiguration(stateCode, operationCode, 'V');
			String message = mapGovOrderConfig.get("message");
			String message2 = mapMapConfig.get("message");
			String govtOrderConfig = mapGovOrderConfig.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = mapMapConfig.get("mapUpload");// value=true,false

			if (govtOrderConfig == null && mapConfig == null) {
				String aMessage = "Please Configure Map and Govt Order ";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				return mav;
			} else if (govtOrderConfig == null) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
				return mav;
			} else if (mapConfig == null) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message2);
				return mav;
			}
			if (govtOrderConfig != null && mapConfig != null) {
				mav = new ModelAndView("createnew_village");
				mav.addObject("hideMap", mapConfig);
				mav.addObject("mapConfig", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("districtList",  districtService.getDistrictListbyStateCodeForLocalBody(stateCode));
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("mapConfig", mapConfig);
				if (districtCode == 0) {
					mav.addObject("flag1", 0);
				} else {
					mav.addObject("flag1", 1);
					mav.addObject("flag2", districtCode);
				}

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/**
	 * Modified on 21-08-2014 Add server side form validations and to save the
	 * details of form based on save and publish state in villageDraft and
	 * village entity respectively.
	 */

	@RequestMapping(value = "/enterVillageOrderDetails", method = RequestMethod.POST)
	public ModelAndView addVillage(@ModelAttribute("addVillageNew") VillageForm addVillageNew, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		ModelAndView mv = null;
		Integer orderCode=null;
		try {
			int operationCode = Integer.parseInt(VillageConstant.CREATE_VILLAGE_OP_CODE.toString());
			String isExistGovtOrder = request.getParameter("checkExistingGovtOrder");
			addVillageNew.setIsExistingGovtOrder(isExistGovtOrder);
			String rename = request.getParameter("rename");
			if ("1".equals(rename)) {
				String count = request.getParameter("count");
				StringBuffer renameIdList = new StringBuffer();
				StringBuffer renameNameList = new StringBuffer();
				StringBuffer withOutRenameIdList = new StringBuffer();
				int rowCount = 0;
				if (count != null) {
					rowCount = Integer.parseInt(count);
				}
				int renameCount = 1;
				int withOutRenameCount = 1;
				for (int i = 0; i < rowCount; i++) {
					String reNameFlag = request.getParameter("reNameFlag" + i);
					String villId = request.getParameter("villId" + i);
					String reNamedVillNameNew = request.getParameter("reNamedVillNameNew" + i);
					villId = villId.replaceAll("PART", "");
					if (reNameFlag == null) {
						if (withOutRenameCount == 1) {
							withOutRenameIdList.append(villId);
							withOutRenameCount++;
						} else {
							withOutRenameIdList.append(",");
							withOutRenameIdList.append(villId);
						}
					} else if (reNameFlag.equals("on")) {
						if (renameCount == 1) {
							renameIdList.append(villId);
							renameNameList.append(reNamedVillNameNew);
							renameCount++;
						} else {
							renameIdList.append(",");
							renameNameList.append(",");
							renameIdList.append(villId);
							renameNameList.append(reNamedVillNameNew);
						}
					}

				}
				addVillageNew.setRenameIdVillageList(renameIdList.toString());
				addVillageNew.setRenameNameVillageList(renameNameList.toString());
				addVillageNew.setWithOutRenameIdVillageList(withOutRenameIdList.toString());
			}
			villageValidator.villageCreationValidation(addVillageNew, result, request);
			if (result.hasErrors()) {
				mv=setCommanProperties(mv,addVillageNew);
			} else {
				if (addVillageNew.getDistrictCode() != null && !("").equals(addVillageNew.getDistrictCode())) {
					String tempDistContributing = addVillageNew.getDistrictCode();
					CommonUtil.CheckForParentForListItems(httpSession, response, abstractValidator, tempDistContributing, "D");
				}
				if (addVillageNew.getSubdistrictNameEnglish() != null && !("").equals(addVillageNew.getSubdistrictNameEnglish())) {
					String tempSubDistContributing = addVillageNew.getSubdistrictNameEnglish();
					CommonUtil.CheckForParentForListItems(httpSession, response, abstractValidator, tempSubDistContributing, "T");
				}
				if (addVillageNew.getContributedVillages() != null && !("").equals(addVillageNew.getContributedVillages())) {
					String tempVillageContributing = addVillageNew.getContributedVillages().replace("PART", "").replace("FULL", "");
					CommonUtil.CheckForParentForListItems(httpSession, response, abstractValidator, tempVillageContributing, "V");
				}
				if (addVillageNew.getSelectedCoveredLandRegionByULB() != null && !("").equals(addVillageNew.getSelectedCoveredLandRegionByULB())) {
					String tempLBContributing = addVillageNew.getSelectedCoveredLandRegionByULB().replace("PART", "").replace("FULL", "");
					CommonUtil.CheckForParentForListItems(httpSession, response, abstractValidator, tempLBContributing, "G");
				}
				addVillageNew.setOperationCode(operationCode);

				httpSession.setAttribute("formbean", addVillageNew);
				VillageForm villageForm = (VillageForm) httpSession.getAttribute("formbean");
				// List<GovernmentOrderTemplate> templateList = new
				// ArrayList<GovernmentOrderTemplate>();
				// templateList = new ArrayList<GovernmentOrderTemplate>();
				villageForm.setUserId(userId.intValue());
				String govtOrderConfig = null;
				char operation = villageForm.getOperation();
				govtOrderConfig = villageForm.getGovtOrderConfig();
				if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (operation == 'C') {
						// templateList =
						// govtOrderTemplateService.getTemplateListByOperationCode(10);
						if (villageForm.getAttachFile1() != null) {
							List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMap(request, villageForm, result, mv);
							httpSession.setAttribute("validFileMap", validFileMap);
						}
					}
				} else {
					if (villageForm.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMap(request, villageForm, result, mv);
						httpSession.setAttribute("validFileMap", validFileMap);
					}
				}
				int templCode = 0;
				int Emailflag = 0;
				int Transactionid = 0;
				if (villageForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					if (operation == 'C' || operation == 'I' || operation == 'M') {
						String templateBodySrc = govtOrderService.previewTemplate(templCode, httpSession);
						villageForm.setTemplateBodySrc(templateBodySrc);
						mv = new ModelAndView("previewGovtOrder");
					}
				} else if (villageForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (operation == 'C') {
						Integer vcode = null;
						String tid = null;
						String vc = null;
						String villagedata = villageService.insertVillage(stateCode, villageForm, request, httpSession);
						if (villagedata != null) {
							String[] lbp = villagedata.split(",");
							vc = lbp[0];
							if (villageForm.getButtonClicked()=='P'){
								tid = lbp[1];
								orderCode=Integer.parseInt(lbp[2]);
							}
								
							vcode = Integer.parseInt(vc);
							
						}
						attachmentList = new ArrayList<Attachment>();
						if (villageForm.getOrderCode() != null)
							attachmentList = govtOrderService.getAttachmentsbyOrderCode(villageForm.getOrderCode());
						if (vcode != null) {
							// Data and upload file
							
							
							if (villageForm.getButtonClicked() == 'S') {
								if (httpSession.getAttribute("validFileMap") != null) {
									@SuppressWarnings("unchecked")
									List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
									villageForm.setVillageCode(vcode);
									
									villageService.saveDataInMapDraft(villageForm, validFileMap, httpSession);
								}
								List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillage(request, villageForm, result);
								if (("Y").equalsIgnoreCase(isExistGovtOrder)) {
									validFileGovtUpload = new ArrayList<AttachedFilesForm>();
									AttachedFilesForm filesForm = new AttachedFilesForm();
									filesForm.setFileLocation(attachmentList.get(0).getFileLocation());
									filesForm.setFileType(attachmentList.get(0).getFileContentType());
									filesForm.setFileName(attachmentList.get(0).getFileName());
									filesForm.setFileSize(attachmentList.get(0).getFileSize());
									filesForm.setFileTimestampName(attachmentList.get(0).getFileTimestamp());
									validFileGovtUpload.add(filesForm);
								}
								
								villageService.saveDataInAttachDraftVilCreate(villageForm, validFileGovtUpload, vcode, request.getSession());
								mv = new ModelAndView("success");
								mv.addObject("msgid", "Draft Village Created Successfully");
								
							} else {
								if (httpSession.getAttribute("validFileMap") != null) {
									@SuppressWarnings("unchecked")
									List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
									villageForm.setVillageCode(vcode);
									// boolean insertTableFlag =
									villageService.saveDataInMap(villageForm, validFileMap, httpSession);

								}
								if (("N").equalsIgnoreCase(isExistGovtOrder)) {
									List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillage(request, villageForm, result);
									// boolean insertGovtTableFlag =
									GovernmentOrderForm governmentOrderForm=new GovernmentOrderForm();
									governmentOrderForm.setOrderCode(orderCode);
									villageService.saveDataInAttach(governmentOrderForm, validFileGovtUpload, vcode, request.getSession());
								}
								
							
								Emailflag = 1;
								if (tid != null) {
									Transactionid = Integer.parseInt(tid);
									Emailflag = 1;
								}
								
								
								
								mv=setCommanProperties(mv,addVillageNew);
								VillageForm villageFormNew=new VillageForm();
								villageFormNew.setDistrictNameEnglish(villageForm.getDistrictCode());
								villageFormNew.setSubdistrictNameEnglish(villageForm.getSubdistrictNameEnglish());
								mv.addObject("addVillageNew", villageFormNew);
								model.addAttribute("addVillageNew",villageFormNew);
								mv.addObject("msgid", "Village Published Successfully");
								mv.addObject("isVillageSaved", Boolean.TRUE);
								
								 
								if (Emailflag == 1) {
									int t_code = Transactionid;
									char t_type = 'V';
									EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
									est.start();

								}
							}

						} else {
							mv = new ModelAndView("success");
							mv.addObject("msgid", "Village Creation failure please check logs");
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}
	
	public ModelAndView setCommanProperties(ModelAndView mv,VillageForm villageForm)throws Exception{
		mv = new ModelAndView("createnew_village");
		mv.addObject("govtOrderConfig", villageForm.getGovtOrderConfig());
		mv.addObject("districtList", districtService.getDistrictListbyStateCodeForLocalBody(stateCode));
		mv.addObject(villageForm);
		mv.addObject("subDistrictList", subDistrictService.getSubDistrictList(Integer.parseInt(villageForm.getDistrictNameEnglish())));
		mv.addObject("subDistrictCode", villageForm.getSubdistrictNameEnglish());
		mv.addObject("districtCode", villageForm.getDistrictNameEnglish());
		if (districtCode == 0) {
			mv.addObject("flag1", 0);
		} else {
			mv.addObject("flag1", 1);
			mv.addObject("flag2", districtCode);
		}
		return mv;
	}

	/*// Contribute Village Detail
	@RequestMapping(value = "/contributeVillage", method = RequestMethod.GET)
	public ModelAndView contributeVillageMethod(@ModelAttribute("addVillageNew") VillageForm addVillageNew, Model model, HttpServletRequest request) {

		ModelAndView mv = null;
		try {
			List<VillageDataForm> listVillageDetails = null;
			listVillageDetails = new ArrayList<VillageDataForm>();
			// listVillageDetails=villageService.getVillageDetails();

			mv = new ModelAndView("contributeVillageDetails");
			model.addAttribute("size", listVillageDetails.size());
			model.addAttribute("listVillageDetails", listVillageDetails);

			addVillageNew.setListVillageDetails(listVillageDetails);

			mv.addObject("addVillageNew", addVillageNew);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}*/

	/*@RequestMapping(value = "/contributeVillageAction", method = RequestMethod.POST)
	public ModelAndView contributeVillage(@ModelAttribute("addVillageNew") VillageForm addVillageNew, BindingResult result, SessionStatus status, HttpServletRequest request) {
		ModelAndView mv;
		try {
			villageService.addVillage(addVillageNew);
			String aMessage = "New Village Detail Saved Successfully";
			mv = new ModelAndView("configview");
			mv.addObject("msgid", aMessage);
			mv.addObject("configview", addVillageNew);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}*/

	// contributeVillageModAction
	/*@RequestMapping(value = "/contributeVillageModAction", method = RequestMethod.POST)
	public ModelAndView contributeVillageModify(@ModelAttribute("addVillageNew") VillageForm addVillageNew, BindingResult result, SessionStatus status, HttpServletRequest request) {
		// villageService.addModifyVillage(addVillageNew);
		ModelAndView mv;
		try {
			String aMessage = "New Village Detail Saved Successfully";
			mv = new ModelAndView("configview");
			mv.addObject("msgid", aMessage);
			mv.addObject("configview", addVillageNew);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}*/

	// ULB Detail oF Contributing Villlage
	/*@RequestMapping(value = "/ulbVillage", method = RequestMethod.GET)
	public ModelAndView ulbVillageMethod(HttpServletRequest request) {
		// villageService.addVillage(addVillageNew);
		ModelAndView mv;
		try {
			VillageForm addVillageNew = new VillageForm();
			mv = new ModelAndView("ulbVillageDetails");
			// mv.addObject("msgid", aMessage);
			mv.addObject("addVillageNew", addVillageNew);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}
*/
	// Modify Start
	// modify correction Get Method

	@RequestMapping(value = "/modifyVillageCrbyId", method = RequestMethod.GET)
	public ModelAndView modifyVillageCorrection(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = null;
		String strVillageCode = request.getParameter("id");
		String reqWarningFlag = request.getParameter("warningEntiesFlag");
		session.setAttribute("reqWarningFlag", reqWarningFlag);
		session.setAttribute("id", strVillageCode);
		Integer villageCode = modifyVillageCmd.getVillageId();
		if (villageCode == null) {
			villageCode = Integer.parseInt(strVillageCode);
		}
		try {
			/* added by kirandeep for security fix */
			session.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(session, response, abstractValidator);

			List<VillageDataForm> listVillageDetails = villageService.getVillageDetailsModify(villageCode);
			EsapiEncoder.encode(listVillageDetails);

			int operationCode = Integer.parseInt(VillageConstant.CHANGE_VILLAGE_OP_CODE.toString());
			request.setAttribute("stateCode", stateCode);

			if (!listVillageDetails.isEmpty()) {
				VillageDataForm dataform = listVillageDetails.get(0);
				Integer orderCode = dataform.getOrderCodecr();
				model.addAttribute("subDistrictCode",dataform.getSubdistrictCode());
				mv = new ModelAndView("modifyVillageCorrection");
				boolean mandatoryFlag = true;
				model.addAttribute("pageWarningEntiesFlag", dataform.isWarningflag());
				model.addAttribute("reqWarningFlag", reqWarningFlag);

				Map<String, String> mapGovOrderConfig = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, 'M');
				Map<String, String> mapMapConfig = govtOrderService.getMapConfiguration(stateCode, operationCode, 'V');
				String message = mapGovOrderConfig.get("message");
				String message2 = mapMapConfig.get("message");
				String govtOrderConfig = mapGovOrderConfig.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = mapMapConfig.get("mapUpload");
				if (govtOrderConfig == null && mapConfig == null) {
					String aMessage = "Please Configure Map and Govt Order ";
					mv = new ModelAndView("success");
					mv.addObject("msgid", aMessage);
					return mv;
				} else if (govtOrderConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
					return mv;
				} else if (mapConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message2);
					return mv;
				}

				modifyVillageCmd.setOrderCode(dataform.getOrderCode());
				modifyVillageCmd.setOrderDate(dataform.getOrderDate());
				modifyVillageCmd.setOrderNo(dataform.getOrderNo());
				modifyVillageCmd.setEffectiveDate(dataform.getEffectiveDate());
				modifyVillageCmd.setGazPubDate(dataform.getGazPubDate());
				setAttachmentDetails(modifyVillageCmd);

				if (orderCode != null)
					attachmentList = govtOrderService.getAttachmentsbyOrderCode(orderCode);
				else {
					//mandatoryFlag = false;
					attachmentList = new ArrayList<Attachment>();
				}
				mapAttachmentList = getMapAttachmentListbyVillage(modifyVillageCmd, villageCode, listVillageDetails);

				modifyVillageCmd.setVillageStatus(dataform.getVillageStatus());
				modifyVillageCmd.setListVillageDetails(listVillageDetails);
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				mv.addObject("mapConfig", mapConfig);
				mv.addObject("govtOrderConfig", govtOrderConfig);
				mv.addObject("modifyVillageCmd", modifyVillageCmd);
				mv.addObject("govtfilecount", attachmentList.size());
				mv.addObject("mapfilecount", mapAttachmentList.size());
				session.setAttribute("mandatoryFlag", mandatoryFlag);
				mv.addObject("mandatoryFlag", mandatoryFlag);

			} else {
				String aMessage = "Sorry Data Not Found For Your Selection ";
				mv = new ModelAndView("success");
				mv.addObject("msgid", aMessage);
				return mv;

			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	// Modify Correction Post Method
	@RequestMapping(value = "/modifyVillageCrAction", method = RequestMethod.POST)
	public ModelAndView modifyVillagecorrection(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, HttpServletRequest request, SessionStatus status, Model model, HttpSession session, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = null;

		try {

			List<VillageDataForm> listVillage = modifyVillageCmd.getListVillageDetails();
			if (!listVillage.isEmpty()) {
				VillageDataForm element = listVillage.get(0);
				/* added by kirandeep for security fix */
				modifyVillageCmd.setVillageId(element.getVillageCode());
				session.setAttribute("formbean", modifyVillageCmd);
				CommonUtil.CheckForParent(session, response, abstractValidator);
				int operationCode = 11;
				char operation = 'M';
				char entityType = 'V';
				Map<String, String> mapGovOrderConfig = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				Map<String, String> mapMapConfig = govtOrderService.getMapConfiguration(stateCode, operationCode, entityType);
				String govtOrderConfig = mapGovOrderConfig.get("govtOrder"); // govtOrderUpload,govtOrderGenerate
				String mapConfig = mapMapConfig.get("mapUpload");// true,false

				modifyVillageCmd.setOrderNocr(element.getOrderNocr());
				modifyVillageCmd.setOrderDatecr(element.getOrderDatecr());
				modifyVillageCmd.setOrdereffectiveDatecr(element.getOrdereffectiveDatecr());
				modifyVillageCmd.setGazPubDatecr(element.getGazPubDatecr());

				mav = new ModelAndView("modifyVillageCorrection");
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				villageValidator.validate(modifyVillageCmd, result);
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				Integer fileCount=modifyVillageCmd.getGovtfilecount()!=null && modifyVillageCmd.getGovtfilecount().length()>0 ? Integer.parseInt(modifyVillageCmd.getGovtfilecount()):0;
				if(fileCount==0) {
					commonValidator.isValidMimeforGovOrderLandRegion(result, request, modifyVillageCmd.getAttachFile1());
				}
				
				//commonValidator.isValidMimeforMapLandRegion(result, request, modifyVillageCmd.getAttachFile2());

				if (result.hasErrors()) {
					String Cordinate = null;
					if (element.getCordinate() != null) {
						if (!element.getCordinate().trim().equals("")) {
							Cordinate = element.getCordinate();
						}
					}
					element.setCordinate(Cordinate);
					listVillage.set(0, element);
					modifyVillageCmd.setListVillageDetails(listVillage);
					model.addAttribute("attachmentList", attachmentList);
					model.addAttribute("mapAttachmentList", mapAttachmentList);
					mav.addObject("modifyVillageCmd", modifyVillageCmd);
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);
					mav.addObject("mapConfig", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("govtfilecount", attachmentList.size());
					mav.addObject("mapfilecount", mapAttachmentList.size());
					model.addAttribute("pageWarningEntiesFlag", modifyVillageCmd.getWarningFlag());
					if (session.getAttribute("mandatoryFlag") != null) {
						mav.addObject("mandatoryFlag", session.getAttribute("mandatoryFlag"));
					}
					return mav;
				}
				if (session.getAttribute("mandatoryFlag") != null) {
					session.removeAttribute("mandatoryFlag");
				}
				List<AttachedFilesForm> validFileGovtUpload = new ArrayList<AttachedFilesForm>();
				List<AttachedFilesForm> validFileMap = new ArrayList<AttachedFilesForm>();
				boolean delflag = false;
				if (govtOrderConfig.equals("govtOrderUpload")) {

					AddAttachmentBean govAttachmentBean = fileUploadUtility.getAttachmentBean(modifyVillageCmd, request, result);
					String deleteAttachmentId[] = null;
					if (govAttachmentBean != null) {
						deleteAttachmentId = govAttachmentBean.getDeletedFileID();
						validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govAttachmentBean, modifyVillageCmd.getAttachFile1(), result, mav);
					}
					if (mapConfig.equals("true")) {

						AddAttachmentBean mapAttachmentBean = fileUploadUtility.getAttachmentBeanMap2(modifyVillageCmd, request, result);
						validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean, modifyVillageCmd.getAttachFile2(), result, mav);

						String deleteID[] = mapAttachmentBean.getDeletedFileID();
						if (deleteID != null && deleteID.length > 0) {
							delflag = true;
						}
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList, validFileMap);
							if (warningFlag) {
								modifyVillageCmd.setWarningFlag(!warningFlag);
							}
						}
						villageService.modifyVillageCrInfo(modifyVillageCmd, request, validFileGovtUpload, validFileMap, delflag, deleteAttachmentId, session);

					} else {

						villageService.modifyVillageCrInfo(modifyVillageCmd, request, validFileGovtUpload, null, delflag, deleteAttachmentId, session);
					}

				}

				else if (govtOrderConfig.equals("govtOrderGenerate")) {

					if (mapConfig.equals("true")) {

						AddAttachmentBean mapAttachmentBean = fileUploadUtility.getAttachmentBeanMap2(modifyVillageCmd, request, result);
						validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean, modifyVillageCmd.getAttachFile2(), result, mav);

						String deleteID[] = mapAttachmentBean.getDeletedFileID();
						if (deleteID != null && deleteID.length > 0) {
							delflag = true;
						}
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList, validFileMap);
							if (warningFlag) {
								modifyVillageCmd.setWarningFlag(!warningFlag);
							}
						}
						villageService.modifyVillageCrInfo(modifyVillageCmd, request, null, validFileMap, delflag, null, session);

					} else {
						villageService.modifyVillageCrInfo(modifyVillageCmd, request, null, null, delflag, null, session);
					}
				}

				Object warningEntiesFlag = session.getAttribute("reqWarningFlag");
				session.removeAttribute("warningEntiesFlag");
				if (warningEntiesFlag != null && "W".equalsIgnoreCase(warningEntiesFlag.toString())) {
					mav = new ModelAndView("redirect:viewResolveEntitiesinDisturbedStateLR.htm?warningEntiesFlag=" + warningEntiesFlag.toString());
				} else {
					mav = new ModelAndView("redirect:viewVillageDetailformodify.htm?id=" + element.getVillageCode() + "&type=S");
				}

				return mav;
			}

			else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "No Record(s) available for corrction.");
				return mav;
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}

	/**
	 * Modification for Rename Draft Village on 05-03-2015
	 * 
	 * @param modifyVillageCmd
	 * @param result
	 * @param status
	 * @param request
	 * @param session
	 * @return
	 */
	// Modify Village Change Government Order
	@RequestMapping(value = "/draftModifyVillage", method = RequestMethod.POST)
	public ModelAndView draftModifyVillage(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		// villageService.modifyVillageInfo(modifyVillageCmd,request,session);
		ModelAndView mv = null;
		try {
			/* Added by kirandeep for security fix */
			session.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(session, response, abstractValidator);

			String isExistGovtOrder = request.getParameter("checkExistingGovtOrder");
			modifyVillageCmd.setIsExistingGovtOrder(isExistGovtOrder);
			session.setAttribute("formbean", modifyVillageCmd);
			// boolean saveSuccess = false;
			// String viewDistrictHistory = null;
			List<VillageDataForm> listVillage = new ArrayList<VillageDataForm>();
			listVillage = modifyVillageCmd.getListVillageDetails();
			Iterator<VillageDataForm> itr2 = listVillage.iterator();
			while (itr2.hasNext()) {
				VillageDataForm element = (VillageDataForm) itr2.next();
				modifyVillageCmd.setVillageNameEnglishCh(element.getVillageNameEnglishCh());
			}
			mv = new ModelAndView("modifyVillagechange");
			modifyVillageCmd = villageValidator.validateChange(modifyVillageCmd, result, request);
			if (result.hasErrors()) {
				result.getErrorCount();
				result.getAllErrors();
				mv.addObject("govtOrderConfig", modifyVillageCmd.getGovtOrderConfig());
				mv.addObject("modifyVillageCmd", modifyVillageCmd);
				return mv;
			} else {
				VillageForm villageForm = (VillageForm) session.getAttribute("formbean");
				char operation = villageForm.getOperation();
				// String govtOrderConfig = null;
				// govtOrderConfig = villageForm.getGovtOrderConfig();
				int templCode = 0;
				if ("govtOrderGenerate".equals(villageForm.getGovtOrderConfig())) {
					templCode = Integer.parseInt(villageForm.getTemplateList());
					if (operation == 'C' || operation == 'I' || operation == 'M') {
						villageForm.setTemplateList(villageForm.getTemplateList());

						villageForm.setOrderNo(villageForm.getOrderNo());
						villageForm.setEffectiveDate(villageForm.getEffectiveDate());
						villageForm.setGazPubDate(villageForm.getGazPubDate());
						villageForm.setOrderDate(villageForm.getOrderDate());
						String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
						villageForm.setTemplateBodySrc(templateBodySrc);
						mv = new ModelAndView("previewGovtOrder");
					}
				} else if ("govtOrderUpload".equals(villageForm.getGovtOrderConfig())) {
					int userid = 0;
					// AddAttachmentBean addAttachmentBean = null;
					// AddAttachmentHandler attachmentHandler = new
					// AddAttachmentHandler();
					GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
					// addAttachmentBean=getAttachmentBean(govtOrderForm,request,result);
					List<AttachedFilesForm> validFileGovtUpload = null;
					if (villageForm.getButtonClicked() == 'S') {
						validFileGovtUpload = new ArrayList<AttachedFilesForm>();
						validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillage(request, villageForm, result);

					} else {
						if (("N").equalsIgnoreCase(isExistGovtOrder)) {
							validFileGovtUpload = new ArrayList<AttachedFilesForm>();
							validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillage(request, villageForm, result);
							// boolean insertGovtTableFlag =
							// villageService.saveDataInAttach(null,
							// validFileGovtUpload,
							// villageForm.getVillageCode(),
							// request.getSession());
						}
					}

					// saveSuccess =
					villageService.changeVillage(villageForm, govtOrderForm, validFileGovtUpload, request, userid);
					if (villageForm.getOrderCode() != null)
						attachmentList = govtOrderService.getAttachmentsbyOrderCode(villageForm.getOrderCode());
					if (villageForm.getButtonClicked() == 'S') {
						if (("Y").equalsIgnoreCase(isExistGovtOrder)) {
							validFileGovtUpload = new ArrayList<AttachedFilesForm>();
							AttachedFilesForm filesForm = new AttachedFilesForm();
							filesForm.setFileLocation(attachmentList.get(0).getFileLocation());
							filesForm.setFileType(attachmentList.get(0).getFileContentType());
							filesForm.setFileName(attachmentList.get(0).getFileName());
							filesForm.setFileSize(attachmentList.get(0).getFileSize());
							filesForm.setFileTimestampName(attachmentList.get(0).getFileTimestamp());
							validFileGovtUpload.add(filesForm);
						}

						// boolean insertGovtTableFlag =
						villageService.saveDataInAttachDraftVilCreate(villageForm, validFileGovtUpload, villageForm.getRenameVillageCode(), request.getSession());
					} else {
						if (villageForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
							villageService.changeVillagegenerate(villageForm, govtOrderForm, validFileGovtUpload, request, userid, session);
						}
					}
					listVillage = villageForm.getListVillageDetails();
					Iterator<VillageDataForm> itr = listVillage.iterator();
					int villageCode = 0;
					// int villageVersion = 0;
					while (itr.hasNext()) {
						VillageDataForm element = (VillageDataForm) itr.next();
						villageCode = element.getVillageCode();
						// villageVersion = element.getVillage_version();
					}
					if (villageForm.getButtonClicked() == 'S') {
						String aMessage = "Rename Village Sucessfully in Draft Mode";
						mv = new ModelAndView("success");
						mv.addObject("msgid", aMessage);
						return mv;
					} else {
						// viewDistrictHistory =
						// "redirect:viewVillageDetailformodify.htm?id=" +
						// villageCode + "&type=S";

						// return viewDistrictHistory;
						mv = new ModelAndView("redirect:viewVillageDetailformodify.htm?id=" + villageCode + "&type=S");
						return mv;

					}

				}
				// mv = new ModelAndView("redirect:govtOrderCommon.htm");
				// return mv;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

		/* mv = new ModelAndView("redirect:govtOrderCommon.htm"); */ return mv;

	}

	// modify Village change Get Method
	@RequestMapping(value = "/modifyVillagechangebyId", method = RequestMethod.GET)
	public ModelAndView modifyVillage(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, Model model, HttpSession session, @RequestParam(value = "disturb", required = false) String disturb, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = null;

		try {
			char operation = 'M';

			int operationCode = 11;

			Integer villageCode = modifyVillageCmd.getVillageId();
			String preVersionEffecDate = "";
			/* added by kirandeep for security fix */
			session.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(session, response, abstractValidator);

			request.setAttribute("stateCode", stateCode);
			List<VillageDataForm> listVillageDetails = villageService.getVillageDetailsModify(villageCode);
			if (!listVillageDetails.isEmpty()) {
				VillageDataForm element = listVillageDetails.get(0);
				EsapiEncoder.encode(listVillageDetails);
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				String message = hMap.get("message");
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate

				if (govtOrderConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
					return mv;

				}
				if (govtOrderConfig != null) {
					mv = new ModelAndView("modifyVillagechange");
					List<GovernmentOrderTemplate> templateList = new ArrayList<GovernmentOrderTemplate>();
					templateList = new ArrayList<GovernmentOrderTemplate>();

					if (govtOrderConfig.equals("govtOrderGenerate")) {
						templateList = govtOrderTemplateService.getTemplateListByOperationCode(11);
						mv.addObject("templateList", templateList);
					}
					mv.addObject("govtOrderConfig", govtOrderConfig);
					model.addAttribute("listVillageDetails", listVillageDetails);
					model.addAttribute("disturb", disturb);
					modifyVillageCmd.setListVillageDetails(listVillageDetails);
					mv.addObject("modifyVillageCmd", modifyVillageCmd);
					model.addAttribute("size", listVillageDetails.size());
					mv.addObject("subDistrictCode", element.getSubdistrictCode()); // for
																					// unique
																					// constrain
					/**
					 * Added by Pooja on 18-11-2015 to show label of Previous
					 * version Effective Date
					 */
					String preVersionEffDate = villageService.getMaxPreVersionEffDateOfVillages(villageCode.toString());
					String[] string_start = preVersionEffDate.split("-");
					preVersionEffecDate = string_start[2] + "-" + string_start[1] + "-" + string_start[0];
					mv.addObject("preVersionEffDate", preVersionEffecDate);
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// Modify Change Village Post Method
	@RequestMapping(value = "/modifyVillageChangeAction", method = RequestMethod.POST)
	public String modifyVillageChange(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb) {

		try {
			String viewDistrictHistory = null;
			int villageCode = 0;
			// int villageVersion = 0;
			int userid = 0;
			// boolean saveSuccess = false;
			AddAttachmentBean addAttachmentBean = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			VillageForm villageForm = (VillageForm) session.getAttribute("formbean");
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

			// file upload govt order
			addAttachmentBean = getAttachmentBean(govtOrderForm, request, result);
			List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request, govtOrderForm, result);

			// file upload govt order

			if (validFileGovtUpload != null && validFileGovtUpload.size() != 0) {

				// table insertion
				// saveSuccess =
				villageService.changeVillage(villageForm, govtOrderForm, validFileGovtUpload, request, userid);

				
			}
			if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				attachmentHandler.saveMetaDataINFileSystem(validFileGovtUpload, addAttachmentBean);
			} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
				villageService.changeVillagegenerate(villageForm, govtOrderForm, validFileGovtUpload, request, userid, session);
			}
			List<VillageDataForm> listVillage = new ArrayList<VillageDataForm>();
			listVillage = villageForm.getListVillageDetails();
			Iterator<VillageDataForm> itr = listVillage.iterator();
			while (itr.hasNext()) {
				VillageDataForm element = (VillageDataForm) itr.next();
				villageCode = element.getVillageCode();
				// villageVersion = element.getVillage_version();
			}
			viewDistrictHistory = "redirect:viewVillageDetailformodify.htm?id=" + villageCode + "&type=S";

			return viewDistrictHistory;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@RequestMapping(value = "/viewVillageDetailformodify", method = RequestMethod.GET)
	public ModelAndView viewVillageDetail(@ModelAttribute("villageView") VillageForm villageView, Model model, @RequestParam("id") Integer villageCode, @RequestParam("type") String type, HttpServletRequest request) {
		ModelAndView mv = null;
		try {
			List<VillageDataForm> listVillageDetails = villageService.getVillageDetailsModify(villageCode);
			if(listVillageDetails!=null && !listVillageDetails.isEmpty()) {
				Integer subdistrictCode=listVillageDetails.get(0).getSubdistrictCode();
				model.addAttribute("subdistrictCode",subdistrictCode);
			}
			mv = new ModelAndView("view_modify_villagedetail");
			villageView.setListVillageDetails(listVillageDetails);
			model.addAttribute("successMsg", "The village was modified successfully");
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	// Modify End

	// @Author - Sarvapriya Malhotra
	@RequestMapping(value = "/saveAsDraftVillage", method = RequestMethod.POST)
	public ModelAndView saveAsDraft(@ModelAttribute("addVillageNew") VillageForm addVillageNew, BindingResult result, SessionStatus status, HttpServletRequest request) {
		// ModelAndView mav=new ModelAndView("createnew_village");
		ModelAndView mv = null;
		try {
			villageService.saveAsDraft(addVillageNew);

			mv = new ModelAndView("createnew_village");
			mv.addObject("addVillageNew", addVillageNew);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/publishSaveAsDraftVillage", method = RequestMethod.GET)
	public ModelAndView publishSaveAsDraftVillageGet(@ModelAttribute("draftVillage") VillageForm draftVillage, BindingResult result, SessionStatus status, @RequestParam(value = "xmlPath", required = false) String xmlPath,
			HttpServletRequest request) {
		// ModelAndView mav=new ModelAndView("createnew_village");
		ModelAndView mv;
		try {
			mv = new ModelAndView("createnew_village");
			mv.addObject("addVillageNew", villageService.getDraftVillage(xmlPath));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	// @Author - Sarvapriya Malhotra
	@RequestMapping(value = "/publishSaveAsDraftVillage", method = RequestMethod.POST)
	public ModelAndView publishSaveAsDraftVillage(@ModelAttribute("draftVillage") VillageForm draftVillage, BindingResult result, SessionStatus status, HttpServletRequest request) {
		// ModelAndView mav=new ModelAndView("createnew_village");
		ModelAndView mv;
		try {
			mv = new ModelAndView("createnew_village");
			mv.addObject("addVillageNew", villageService.getDraftVillage(""));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	// @Author - Sarvapriya Malhotra
	@RequestMapping(value = "/saveAsDraftInvalidateVillage", method = RequestMethod.POST)
	public ModelAndView saveAsDraftInvalidateVillage(@ModelAttribute("invalidatevillage") VillageForm invalidateVillage, BindingResult result, SessionStatus status, HttpServletRequest request) {
		// ModelAndView mav=new ModelAndView("createnew_village");
		ModelAndView mv = null;
		try {
			villageService.saveAsDraftInvalidateVillage(invalidateVillage);

			mv = new ModelAndView("invalidateVillageMAV");
			mv.addObject("invalidatevillage", invalidateVillage);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// @Author - Sarvapriya Malhotra
	@RequestMapping(value = "/publishSaveAsDraftInvalidateVillage", method = RequestMethod.POST)
	public ModelAndView publishSaveAsDraftInvalidateVillage(@ModelAttribute("invalidatevillage") VillageForm invalidateVillage, BindingResult result, SessionStatus status, HttpServletRequest request) {
		// ModelAndView mav=new ModelAndView("createnew_village");
		ModelAndView mv = null;
		try {
			mv = new ModelAndView("invalidateVillageMAV");

			invalidateVillage.setDistrictNameEnglish(villageService.getInvalidateDraftVillage("../invalidatevillage.xml").getDistrictNameEnglish());
			mv.addObject("invalidatevillage", invalidateVillage);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// End here
	public void setAttachmentDetails(VillageForm villageform) throws Exception {
		try {
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			villageform.setAllowedFileType(attachmentList.getFileType());
			villageform.setAllowedIndividualFileSize(attachmentList.getIndividualFileSize());
			villageform.setAllowedNoOfAttachment(attachmentList.getFileLimit());
			villageform.setAllowedTotalFileSize(attachmentList.getTotalFileSize());
			villageform.setUploadLocation(attachmentList.getFileLocation());
			villageform.setRequiredTitle(attachmentList.getRequireTitle());
			villageform.setUniqueTitle(attachmentList.getUniqueTitle());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}

	private AddAttachmentBean getAttachmentBean(GovernmentOrderForm villageform, HttpServletRequest request, BindingResult result) throws Exception {

		List<Attachment> alreadyAttachList = null;
		List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
		AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
		String fileUploadLocation = attachmentList.getFileLocation();
		int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
		long allowedTotalFileSize = attachmentList.getTotalFileSize();
		long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
		int noOFAlreadyAttachedFiles1 = 0;
		long totalSizeOFAlreadyAttachedFiles1 = 0L;
		int noOFMandatoryFile2 = 1;

		if (villageform.getOrderCode() != null) {
			if (request.getParameterValues("deletedFileID1") != null) {
				String[] deletedFileID1 = (String[]) request.getParameterValues("deletedFileID1");
				govtOrderService.deleteAttachedFiles(deletedFileID1, request, 'O');
			}
			alreadyAttachList = govtOrderService.getAttachmentsbyOrderCode(villageform.getOrderCode());

			noOFAlreadyAttachedFiles1 = alreadyAttachList.size(); // Already
																	// Attach
																	// Number Of
																	// File.
			// Already attached file total size.
			Iterator<Attachment> attachmentsIterator1 = alreadyAttachList.iterator();
			while (attachmentsIterator1.hasNext()) {
				Attachment att = (Attachment) attachmentsIterator1.next();
				long fileSize = att.getFileSize();
				totalSizeOFAlreadyAttachedFiles1 = totalSizeOFAlreadyAttachedFiles1 + fileSize;
			}
		}

		AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
		addAttachmentBeanTwo.setCurrentlyUploadFileList(villageform.getAttachFile1());
		addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
		addAttachmentBeanTwo.setFileTitle(villageform.getFileTitle1());
		addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
		addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
		addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
		addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
		addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
		addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime
																	// Type
																	// List.
		addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
																							// Id
																							// array
																							// to
																							// be
																							// deleted
		addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
																								// Name
																								// Array
																								// To
																								// Be
																								// Deleted.
		addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
																									// File
																									// Array.
		addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
																		// of
																		// Mandatory
																		// file.

		return addAttachmentBeanTwo;
	}

	/*
	 * private AddAttachmentBean getAttachmentBean(VillageForm villageform,
	 * HttpServletRequest request) throws Exception {
	 * 
	 * List<Attachment> alreadyAttachList = null; List<String>
	 * allowedMimeTypeList = govtOrderService.getMimeTypeList();
	 * AttachmentMaster attachmentList = govtOrderService
	 * .getUploadFileConfigurationDetails(1); String fileUploadLocation =
	 * attachmentList.getFileLocation(); int allowedTotalNoOfAttachment =
	 * attachmentList.getFileLimit(); long allowedTotalFileSize =
	 * attachmentList.getTotalFileSize(); long allowedIndividualFileSize =
	 * attachmentList.getIndividualFileSize(); int noOFAlreadyAttachedFiles1 =
	 * 0; long totalSizeOFAlreadyAttachedFiles1 = 0L; int noOFMandatoryFile2 =
	 * 1;
	 * 
	 * if (villageform.getOrderCode() != null) { if
	 * (request.getParameterValues("deletedFileID1") != null) { String[]
	 * deletedFileID1 = (String[]) request
	 * .getParameterValues("deletedFileID1");
	 * govtOrderService.deleteAttachedFiles(deletedFileID1, request,'O'); }
	 * alreadyAttachList = govtOrderService
	 * .getAttachmentsbyOrderCode(villageform.getOrderCode());
	 * 
	 * noOFAlreadyAttachedFiles1 = alreadyAttachList.size(); // Already //
	 * Attach // Number Of // File. // Already attached file total size.
	 * Iterator<Attachment> attachmentsIterator1 = alreadyAttachList
	 * .iterator(); while (attachmentsIterator1.hasNext()) { Attachment att =
	 * (Attachment) attachmentsIterator1.next(); long fileSize =
	 * att.getFileSize(); totalSizeOFAlreadyAttachedFiles1 =
	 * totalSizeOFAlreadyAttachedFiles1 + fileSize; } }
	 * 
	 * AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
	 * addAttachmentBeanTwo.setCurrentlyUploadFileList(villageform
	 * .getAttachFile1());
	 * addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
	 * addAttachmentBeanTwo.setFileTitle(villageform.getFileTitle1());
	 * addAttachmentBeanTwo
	 * .setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
	 * addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
	 * addAttachmentBeanTwo
	 * .setAllowedIndividualFileSize(allowedIndividualFileSize);
	 * addAttachmentBeanTwo
	 * .setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
	 * addAttachmentBeanTwo
	 * .setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
	 * addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File Mime
	 * // Type // List. addAttachmentBeanTwo.setDeletedFileID(request
	 * .getParameterValues("deletedFileID1"));// 10.File Id array to be //
	 * deleted addAttachmentBeanTwo.setDeletedFileName(request
	 * .getParameterValues("deletedFileName1"));// 11.File Name Array // To Be
	 * Deleted. addAttachmentBeanTwo.setDeletedFileList(request
	 * .getParameterValues("deletedFileSizeList1"));// 12.Deleted File // Array.
	 * addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);//
	 * 13.Number // of // Mandatory // file.
	 * 
	 * return addAttachmentBeanTwo; }
	 */

	// Old village Modify start
	@RequestMapping(value = "/modifyVillagebyId", method = RequestMethod.GET)
	public ModelAndView modifyVillage(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, Model model, @RequestParam("id") Integer villageCode, @RequestParam(value = "disturb", required = false) String disturb,
			HttpServletRequest request) {

		ModelAndView mv;
		try {
			List<VillageDataForm> listVillageDetails = villageService.getVillageDetailsModify(villageCode);

			mv = new ModelAndView("modify_village");
			model.addAttribute("size", listVillageDetails.size());
			model.addAttribute("listVillageDetails", listVillageDetails);
			model.addAttribute("disturb", disturb);
			modifyVillageCmd.setListVillageDetails(listVillageDetails);
			mv.addObject("modifyVillageCmd", modifyVillageCmd);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	// modifyVillageAction
	/*
	 * @RequestMapping(value="/modifyVillageAction", method=RequestMethod.POST)
	 * public ModelAndView modifyVillage(@ModelAttribute
	 * ("modifyVillageCmd")VillageForm modifyVillageCmd , BindingResult result,
	 * SessionStatus status, Model model,HttpServletRequest request) { //
	 * List<Village> listVillageDetails =
	 * villageService.getVillageDetailsModify(id); ModelAndView mav=new
	 * ModelAndView("modify_village");
	 * villageValidator.validate(modifyVillageCmd, result);
	 * if(result.hasErrors()) {
	 * mav.addObject("modifyVillageCmd",modifyVillageCmd); return mav; }
	 * 
	 * villageService.modifyVillageInfo(modifyVillageCmd,request); String
	 * aMessage="Village Detail Modified Successfully"; ModelAndView mv=new
	 * ModelAndView("configview"); mv.addObject("msgid", aMessage);
	 * status.setComplete(); return mv;
	 * 
	 * }
	 */

	@RequestMapping(value = "/modifyVillageAction", method = RequestMethod.POST)
	public String modifyVillage(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, SessionStatus status, Model model, HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb) {
		String viewDistrictHistory = null;
		try {
			ModelAndView mav = new ModelAndView("modify_village");
			try {
				villageValidator.validate(modifyVillageCmd, result);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			if (result.hasErrors()) {
				mav.addObject("modifyVillageCmd", modifyVillageCmd);
				return "modify_village";
			}
			try {
				villageService.modifyVillageInfo(modifyVillageCmd, request, httpSession);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			int villageCode = 0;
			int villageVersion = 0;
			List<VillageDataForm> listVillage = new ArrayList<VillageDataForm>();
			listVillage = modifyVillageCmd.getListVillageDetails();
			Iterator<VillageDataForm> itr = listVillage.iterator();
			while (itr.hasNext()) {
				VillageDataForm element = (VillageDataForm) itr.next();
				villageCode = element.getVillageCode();
				villageVersion = element.getVillage_version();
			}
			if (disturb.equals("true")) {
				viewDistrictHistory = "redirect:viewResolveEntitiesinDisturbedState.htm?resolved=" + villageCode + "," + villageVersion;
			} else {
				viewDistrictHistory = "redirect:viewVillageDetailformodify.htm?id=" + villageCode + "&type=S";
			}
			return viewDistrictHistory;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	// Old village Modify End
	// getting values for map from MapLocalBody
	private List<MapAttachment> getMapAttachmentListbyVillage(VillageForm villageForm, Integer villageCode, List<VillageDataForm> lstVillageDetails) {
		List<MapAttachment> mapAttachmentList = null;
		try {
			char entityType = 'V';
			mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(villageCode, entityType);
		} catch (Exception ex) {
			log.debug("Exception" + ex);
		}
		return mapAttachmentList;
	}

	// Moved From ViewController
	@RequestMapping(value = "/viewvillage", method = RequestMethod.GET)
	public ModelAndView showVillagePage(Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_village");
			setGlobalParams(httpSession);
			VillageDataForm villageDataForm = new VillageDataForm();
			if (stateCode != null) {
				if (districtCode > 0) {
					villageDataForm.setDistrictList(districtService.getDistrictListbyDistrictCodeForLocalBody(districtCode));
					villageDataForm.setDistrictCode(districtCode);
				} else {
					villageDataForm.setDistrictList( districtService.getDistrictListbyStateCodeForLocalBody(stateCode));
					villageDataForm.setDistrictCode(0);
				}

				villageDataForm.setStateCode(stateCode);
				villageDataForm.setIsDataDiv(Boolean.FALSE);
				mav.addObject("villagebean", villageDataForm);
				}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/**
	 * Modified on 25-08-2014 Get the village list based on operation state
	 * (save or publish state). To check whether the published village used as a
	 * contributing village in draft mode village.
	 */
	@RequestMapping(value = "/viewvillage", method = RequestMethod.POST)
	public ModelAndView getVillagePage(@ModelAttribute("villagebean") VillageDataForm villagebean, BindingResult result, HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mav = null;
		try {

			
			mav = new ModelAndView("view_village");
			if (districtCode > 0) {
				villagebean.setDistrictList(districtService.getDistrictListbyDistrictCodeForLocalBody(districtCode));
				
			} else {
				villagebean.setDistrictList( districtService.getDistrictListbyStateCodeForLocalBody(stateCode));
			}
			if (result.hasErrors()) {
				result.getAllErrors();
				return mav;
			}
			villagebean.setIsDataDiv(Boolean.TRUE);
			Integer subDistrictCode = villagebean.getSubdistrictCode();
			if (subDistrictCode != null && subDistrictCode>0) {
					model.addAttribute("villageList", villageService.getVillageListWithOperationState(subDistrictCode));
				
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewVillagePagination", method = RequestMethod.POST)
	public ModelAndView getVillagePagination(@ModelAttribute("villagebean") VillageDataForm villagebean, HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mav = null;
		String filterValue = null;
		String Pageset = httpSession.getAttribute("offset").toString();
		int i = Integer.parseInt(Pageset);
		if (i == 1) {
			filterValue = request.getParameter("filterOption");
			httpSession.setAttribute("filval", filterValue);
		}
		try {
			char EntityType = ' ';

			if (villagebean.getPageType().equals("V")) {
				mav = new ModelAndView("view_village");
				EntityType = 'V';
			} else if (villagebean.getPageType().equals("CV")) {
				mav = new ModelAndView("view_cvillage");
				EntityType = 'V';
			}

			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset").toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession.getAttribute("offset").toString()) + villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset").toString()) == (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1)
					&& villagebean.getDirection() == -1 && Integer.parseInt(httpSession.getAttribute("offset").toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession.getAttribute("offset").toString()) + villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			}

			List<StateWiseEntityDetails> stateWiseEntityDetails = null;

			if (httpSession.getAttribute("subDistrictCode") != null && !httpSession.getAttribute("subDistrictCode").equals("")) {

				/*
				 * System.out.println("districtCode :: " +
				 * httpSession.getAttribute("subDistrictCode"));
				 */
				stateWiseEntityDetails = villageService.getParentWiseList('T', Integer.parseInt(httpSession.getAttribute("subDistrictCode").toString()), null, null);
				httpSession.setAttribute("counter", stateWiseEntityDetails.size());

				stateWiseEntityDetails = villageService.getParentWiseList('T', Integer.parseInt(httpSession.getAttribute("subDistrictCode").toString()),
						httpSession.getAttribute("limit") == null ? null : Integer.parseInt(httpSession.getAttribute("limit").toString()),
						Integer.parseInt(httpSession.getAttribute("limit").toString()) * (Integer.parseInt(httpSession.getAttribute("offset").toString()) - 1));

			} else {
				stateWiseEntityDetails = villageService.getStateWiseVillageList(stateCode, EntityType,
						httpSession.getAttribute("villageCode") == null ? null : Integer.parseInt(httpSession.getAttribute("villageCode").toString()),
						httpSession.getAttribute("villageName") == null ? null : httpSession.getAttribute("villageName").toString(), null, null);
				httpSession.setAttribute("counter", stateWiseEntityDetails.size());

				stateWiseEntityDetails = villageService.getStateWiseVillageList(stateCode, EntityType,
						httpSession.getAttribute("villageCode") == null ? null : Integer.parseInt(httpSession.getAttribute("villageCode").toString()),
						httpSession.getAttribute("villageName") == null ? null : httpSession.getAttribute("villageName").toString(), httpSession.getAttribute("limit") == null ? null : Integer.parseInt(httpSession.getAttribute("limit").toString()),
						Integer.parseInt(httpSession.getAttribute("limit").toString()) * (Integer.parseInt(httpSession.getAttribute("offset").toString()) - 1));
			}

			model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY", stateWiseEntityDetails);
			villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
			model.addAttribute("offsets", Integer.parseInt(httpSession.getAttribute("offset").toString()) - 1);
			model.addAttribute("limits", Integer.parseInt(httpSession.getAttribute("limit").toString()));
			filterValue = (String) httpSession.getAttribute("filval");
			if (filterValue.equals("0")) {
				model.addAttribute("style0", "visibility: visible; display: block;");
				model.addAttribute("style1", "visibility: hidden; display: none;");

			}

			if (filterValue.equals("1")) {
				model.addAttribute("style0", "visibility: hidden; display: none;");
				model.addAttribute("style1", "visibility: hidden; display: none;");
				// model.addAttribute("style1", "visibility: visible; display:
				// block;");
			}
			model.addAttribute("villageCount",
					"Page " + Integer.parseInt(httpSession.getAttribute("offset").toString()) + " of " + (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewvillagebysubdistrictCode", method = RequestMethod.GET)
	public ModelAndView getVillagesbySubdistrict(HttpSession session, @ModelAttribute("villagebean") VillageDataForm villagebean, HttpServletRequest request, Model model, @RequestParam("id") Integer subdistrictCode) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_village");
			// int recordsLimit=villagebean.getRecordsLimit();

			String query = "from Village d where d.subdistrict.subdistrictPK.subdistrictCode='" + subdistrictCode + "'and d.isactive=true order by villageNameEnglish";
			List<Village> listVillageDetail = null;

			listVillageDetail = new ArrayList<Village>();

			listVillageDetail = villageService.getVillageViewList(query, villagebean.getPageRows(), 0);

			model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY", listVillageDetail);
			villagebean.setListVillageDetail(listVillageDetail);
			mav.addObject("villagebean", villagebean);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewVillageDetail")
	public ModelAndView viewVillageDetail(@ModelAttribute("villageView") VillageForm villageView, HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("view_villagedetail");
		/* Added by kirndeep for security fix */

		String id = request.getParameter("id");
		int villageCode = (villageView.getVillageId() == null) ? Integer.parseInt(id) : villageView.getVillageId();
		try {
			villageView.setListVillageDetails(villageService.getVillageDetailsModify(villageCode));
			GetEntityEffectiveDate getEntityEffectiveDate=new GetEntityEffectiveDate();
			getEntityEffectiveDate.setEntityCode(villageCode);
			getEntityEffectiveDate.setEntityType('V');
			model.addAttribute("villageHistory", villageService.getEntityEffeactiveDateByVersion(getEntityEffectiveDate));
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewVillageHistory")
	public ModelAndView viewVillageHistoryDetail(@ModelAttribute("villageForm") VillageForm villageForm, HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model) {

		ModelAndView mav = null;
		int villageCode = villageForm.getVillageId();
		try {
			/* added by kirandeep for security fix */
			session.setAttribute("formbean", villageForm);
			CommonUtil.CheckForParent(session, response, abstractValidator);
			mav = new ModelAndView("view_villagehistory");
			char villageNameEnglish = 'V';
			List<VillageHistory> villageHistoryDetail = villageService.getVillageHistoryDetail(villageNameEnglish, villageCode);
			model.addAttribute("villageHistory", villageHistoryDetail);
			villageForm.setVillageHistoryDetail(villageHistoryDetail);
			mav.addObject("villageForm", villageForm);
			model.addAttribute("viewPage", "abc");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	

	@RequestMapping(value = "/mergeUnmapVillages", method = RequestMethod.GET)
	public ModelAndView mergeUnmapVil(@ModelAttribute("invalidatevillage") VillageForm invalidateVillageMAV, Model model, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		
		List<LocalbodyforStateWise> localBodyTypelistUrban = new ArrayList<LocalbodyforStateWise>();
		try {
			List<District> districtList = null;
			districtList = new ArrayList<District>();
			if (stateCode== null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			

			Map<String, String> hMap = new HashMap<String, String>();
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 14, 'V');
			localBodyTypelistUrban = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			String message = hMap.get("message");
			if (govtOrderConfig != null && mapConfig != null) {
				mav = new ModelAndView("mergeUnmapVil");
				if (districtCode == 0) {
					mav.addObject("flag1", 1);
					districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);

				} else {
					districtList = districtService.getDistrictListByDistCode(districtCode);
					mav.addObject("flag1", 0);
					mav.addObject("flag2", districtCode);
				}
				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("districtList", districtList);
				mav.addObject("stateCode", stateCode);
				model.addAttribute("localBodyTypelist", localBodyTypelistUrban);

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/mergeandInvalidateVillageDetails", method = RequestMethod.POST)
	public ModelAndView mergeandInvalidateVillageDetails(@ModelAttribute("invalidatevillage") VillageForm villageForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mv = null;
		try {
			String govtOrderConfig = null;
			List<District> districtList = null;
			districtList = new ArrayList<District>();
			// Copy to Check Configuration
			Map<String, String> hMap = new HashMap<String, String>();
			String preVersionMaxEffDate = "";
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 14, 'V');// 10
																								// is
																								// operation
																								// code
																								// for
																								// create
																								// new
																								// village
			govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			villageValidator.villageInvalidationValidation(villageForm, result, request);
			if (result.hasErrors()) {
				districtList = districtService.getDistrictList(stateCode);
				mv = new ModelAndView("invalidateVillageMAV");
				mv.addObject("hideMap", mapConfig);
				mv.addObject("districtList", districtList);
				mv.addObject("govtOrderConfig", govtOrderConfig);
				mv.addObject(villageForm);

			} else {
				if (request.getParameter("preVersionEffDate") != null)
					preVersionMaxEffDate = (String) request.getParameter("preVersionEffDate");
				villageForm.setPreVersionMaxEffDate(preVersionMaxEffDate);
				govtOrderConfig = villageForm.getGovtOrderConfig();
				httpSession.setAttribute("formbean", villageForm);
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
				mv.addObject("govtOrderConfig", govtOrderConfig);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

		return mv;
	}

	/**
	 * Add on 21-08-2014 Get the draft village list.
	 */
	@RequestMapping(value = "/manageDraftVillage", method = RequestMethod.GET)
	public ModelAndView getDraftVillagePage(@ModelAttribute("villagebean") VillageDataForm villagebean, BindingResult result, HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			if (stateCode== null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			
			
			mav = new ModelAndView("view_village_draft");
			List<VillageDraft> villageList = new ArrayList<VillageDraft>();
			if (districtCode > 0) {
				villageList = villageService.getVillageDraftListByDistrictCode(districtCode);
			} else {
				villageList = villageService.getVillageDraftList(stateCode);
			}
			model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY", villageList);
			model.addAttribute("viewPage", "abc");
			villagebean.setLimit(3000);
			httpSession.setAttribute("limit", villagebean.getLimit());
			villagebean.setOffset(1);
			httpSession.setAttribute("offset", villagebean.getOffset());

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/**
	 * Add on 22-08-2014 Retrieve the draft village list details with their
	 * attachment details.
	 */
	@RequestMapping(value = "/viewVillageDraftDetail")
	public ModelAndView viewVillageDraftDetail(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, HttpServletRequest request, Model model, HttpSession httpSession, HttpServletResponse response) {
		ModelAndView mav = null;

		String id = request.getParameter("id");
		int villageCode = (modifyVillageCmd.getVillageId() == null) ? Integer.parseInt(id) : modifyVillageCmd.getVillageId();
		try {
			// Added by Anchal Todariya on 28-03-2015 for Security audit fix
			modifyVillageCmd.setDraftVillageCode(villageCode);
			httpSession.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(httpSession, response, abstractValidator);
			
			boolean mandatoryFlag = true;
			List<District> districtList = new ArrayList<District>();
			List<Subdistrict> subDistrictList = new ArrayList<Subdistrict>();
			mav = new ModelAndView("manage_villagedraftdetail");
			List<VillageDataForm> listVillageDetails = villageService.getVillageDraftDetailsModify(villageCode);
			List<VillageDraft> vilDraftattachmentList = new ArrayList<VillageDraft>();

			districtList = districtService.getDistrictListByDistCode(Integer.parseInt(listVillageDetails.get(0).getDistrictNameEnglish()));
			mav.addObject("flag1", 0);
			mav.addObject("mandatoryFlag", mandatoryFlag);
			mav.addObject("flag2", 0);
			mav.addObject("districtList", districtList);
			subDistrictList = subDistrictService.getSubDistrictListBySubDistCode(Integer.parseInt(listVillageDetails.get(0).getSubdistrictNameEnglish()));
			mav.addObject("subDistrictList", subDistrictList);
			mav.addObject("subDistrictCode", listVillageDetails.get(0).getSubdistrictNameEnglish());
			mav.addObject("districtCode", listVillageDetails.get(0).getDistrictNameEnglish());
			mav.addObject("draftVilCode", listVillageDetails.get(0).getVillageCode());
			mav.addObject("govtOrderConfig", listVillageDetails.get(0).getGovtOrder());
			mav.addObject("existVilOrUlbFlag", listVillageDetails.get(0).getExistVilOrUlbFlag());
			mav.addObject("selectedUlbCode", listVillageDetails.get(0).getUlbCodeValid());
			modifyVillageCmd.setIsPesaStatus(listVillageDetails.get(0).getIsPesa());
			modifyVillageCmd.setListVillageDetails(listVillageDetails);
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10
																													// is
																													// operation
																													// code
																													// for
																													// create
																													// new
			String mapConfig = hMap.get("mapUpload");
			String message = hMap.get("message");
			if (listVillageDetails != null && listVillageDetails.get(0).getGovtOrder() != null && mapConfig != null) {
				mav.addObject("hideMap", mapConfig);
			} else {
				mav.addObject("msgid", message);
			}

			setAttachmentDetails(modifyVillageCmd);
			attachmentList = new ArrayList<Attachment>();
			mapAttachmentList = new ArrayList<MapAttachment>();
			if (villageCode > 0)
				vilDraftattachmentList = govtOrderService.getAttachmentsbyOrderCodeForDraftVil(villageCode);
			else {
				mandatoryFlag = false;
				attachmentList = new ArrayList<Attachment>();
				mapAttachmentList = new ArrayList<MapAttachment>();
			}
			Attachment attachmentDet = new Attachment();
			if (("N").equalsIgnoreCase(listVillageDetails.get(0).getIsExistGovtOrder())) {
				if (vilDraftattachmentList != null && vilDraftattachmentList.get(0).getGovt_order_file_name() != null) {
					attachmentDet.setFileLocation(vilDraftattachmentList.get(0).getGovt_order_file_location());
					attachmentDet.setFileContentType(vilDraftattachmentList.get(0).getGovt_order_file_content_type());
					attachmentDet.setFileName(vilDraftattachmentList.get(0).getGovt_order_file_name());
					attachmentDet.setFileSize(vilDraftattachmentList.get(0).getGovt_order_file_size());
					attachmentDet.setFileTimestamp(vilDraftattachmentList.get(0).getGovt_order_file_timestamp());
					attachmentList.add(attachmentDet);
				}
			}
			MapAttachment mapAttachmentDet = new MapAttachment();
			if (vilDraftattachmentList != null && vilDraftattachmentList.get(0).getMap_upload_file_name() != null) {

				mapAttachmentDet.setFileLocation(vilDraftattachmentList.get(0).getMap_upload_file_location());
				mapAttachmentDet.setFileContentType(vilDraftattachmentList.get(0).getMap_upload_file_content_type());
				mapAttachmentDet.setFileName(vilDraftattachmentList.get(0).getMap_upload_file_name());
				mapAttachmentDet.setFileSize(vilDraftattachmentList.get(0).getMap_upload_file_size());
				mapAttachmentDet.setFileTimestamp(vilDraftattachmentList.get(0).getMap_upload_file_timestamp());
				mapAttachmentList.add(mapAttachmentDet);
			}

			modifyVillageCmd.setVillageStatus(listVillageDetails.get(0).getVillageStatus());
			request.setAttribute("attachmentList", attachmentList);
			request.setAttribute("mapAttachmentList", mapAttachmentList);
			mav.addObject("modifyVillageCmd", modifyVillageCmd);
			mav.addObject("govtfilecount", attachmentList.size());
			mav.addObject("mapfilecount", mapAttachmentList.size());
			mav.addObject("mandatoryFlag", mandatoryFlag);
			mav.addObject("isExistGovt", listVillageDetails.get(0).getIsExistGovtOrder());
			mav.addObject("orderCode", listVillageDetails.get(0).getOrderCode());
			mav.addObject("mapConfig", true);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/**
	 * Add on 25-08-2014 Save or publish the draft village modified details with
	 * their attachment details in draft village and village entity
	 * respectively.
	 */
	@RequestMapping(value = "/saveModifyDraftVillageOrderDetails", method = RequestMethod.POST)
	public ModelAndView modifySaveDraftVillage(@ModelAttribute("modifyVillageCmd") VillageForm addVillageNew, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpSession,
			HttpServletResponse response) {
		ModelAndView mv = null;
		int operationCode = 10;
		// int districtCode = getDistrictId(httpSession);
		try {
			List<VillageDataForm> villDataForm = addVillageNew.getListVillageDetails();

			addVillageNew.setDraftVillageCode(villDataForm.get(0).getVillageCode());
			httpSession.setAttribute("formbean", addVillageNew);
			CommonUtil.CheckForParent(httpSession, response, abstractValidator);

			String isExistGovtOrder = request.getParameter("checkExistingGovtOrder");
			addVillageNew.setIsExistingGovtOrder(isExistGovtOrder);
			addVillageNew.setDistrictNameEnglish(request.getParameter("districtCode"));
			addVillageNew.setSubdistrictNameEnglish(request.getParameter("subDistrictCode"));
			addVillageNew.setGovtOrderConfig(request.getParameter("govtOrderConfig"));
			String existVilOrUlbFlag = request.getParameter("existVilOrUlbFlag");
			String isExistGovt = request.getParameter("isExistGovt");
			String rename = request.getParameter("rename");
			addVillageNew.getOrderCode();
			if (("0").equals(request.getParameter("govtfilecount")) || ("Y").equals(isExistGovtOrder))
				attachmentList = new ArrayList<Attachment>();

			if (addVillageNew.getOrderCode() != null && ("Y").equals(isExistGovtOrder))
				attachmentList = govtOrderService.getAttachmentsbyOrderCode(addVillageNew.getOrderCode());

			if (("0").equals(request.getParameter("mapfilecount")))
				mapAttachmentList = new ArrayList<MapAttachment>();
			request.setAttribute("attachmentList", attachmentList);
			request.setAttribute("mapAttachmentList", mapAttachmentList);
			
			if ("1".equals(rename)) {
				String count = request.getParameter("count");
				StringBuffer renameIdList = new StringBuffer();
				StringBuffer renameNameList = new StringBuffer();
				StringBuffer withOutRenameIdList = new StringBuffer();
				int rowCount = 0;
				if (count != null) {
					rowCount = Integer.parseInt(count);
				}
				int renameCount = 1;
				int withOutRenameCount = 1;
				for (int i = 0; i < rowCount; i++) {
					String reNameFlag = request.getParameter("reNameFlag" + i);
					String villId = request.getParameter("villId" + i);
					String reNamedVillNameNew = request.getParameter("reNamedVillNameNew" + i);
					villId = villId.replaceAll("PART", "");
					if (reNameFlag == null) {
						if (withOutRenameCount == 1) {
							withOutRenameIdList.append(villId);
							withOutRenameCount++;
						} else {
							withOutRenameIdList.append(",");
							withOutRenameIdList.append(villId);
						}
					} else if (reNameFlag.equals("on")) {
						if (renameCount == 1) {
							renameIdList.append(villId);
							renameNameList.append(reNamedVillNameNew);
							renameCount++;
						} else {
							renameIdList.append(",");
							renameNameList.append(",");
							renameIdList.append(villId);
							renameNameList.append(reNamedVillNameNew);
						}
					}

				}
				addVillageNew.setRenameIdVillageList(renameIdList.toString());
				addVillageNew.setRenameNameVillageList(renameNameList.toString());
				addVillageNew.setWithOutRenameIdVillageList(withOutRenameIdList.toString());
			}
			String villagePreviousName = (String) request.getParameter("villagenameInEngOld");
			villageValidator.villageDraftModifyValidation(addVillageNew, villDataForm.get(0), result, request, villagePreviousName);
			if (result.hasErrors()) {
				List<District> districtList = null;
				districtList = new ArrayList<District>();
				districtList = districtService.getDistrictList(stateCode);
				mv = new ModelAndView("manage_villagedraftdetail");
				mv.addObject("govtOrderConfig", addVillageNew.getGovtOrderConfig());
				mv.addObject("districtList", districtList);
				mv.addObject(addVillageNew);
				districtList = districtService.getDistrictList(stateCode);
				boolean mandatoryFlag = true;
				List<Subdistrict> subDistrictList = new ArrayList<Subdistrict>();
				List<VillageDataForm> listVillageDetails = villDataForm;
				// List<VillageDraft> vilDraftattachmentList = new
				// ArrayList<VillageDraft>();

				districtList = districtService.getDistrictListByDistCode(Integer.parseInt(addVillageNew.getDistrictNameEnglish()));
				mv.addObject("flag1", 0);
				mv.addObject("mandatoryFlag", mandatoryFlag);
				mv.addObject("flag2", 0);
				mv.addObject("districtList", districtList);
				subDistrictList = subDistrictService.getSubDistrictListBySubDistCode(Integer.parseInt(addVillageNew.getSubdistrictNameEnglish()));
				mv.addObject("subDistrictList", subDistrictList);
				mv.addObject("subDistrictCode", addVillageNew.getSubdistrictNameEnglish());
				mv.addObject("districtCode", addVillageNew.getDistrictNameEnglish());
				mv.addObject("draftVilCode", listVillageDetails.get(0).getVillageCode());
				mv.addObject("govtOrderConfig", addVillageNew.getGovtOrderConfig());
				mv.addObject("existVilOrUlbFlag", existVilOrUlbFlag);
				addVillageNew.setListVillageDetails(listVillageDetails);

				Map<String, String> hMap = new HashMap<String, String>();
				// Please first check your operation code then set it in place
				// of 10
				hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10
																														// is
																														// operation
																														// code
																														// for
																														// create
																														// new
				String mapConfig = hMap.get("mapUpload");
				String message = hMap.get("message");
				if (listVillageDetails != null && listVillageDetails.get(0).getGovtOrder() != null && mapConfig != null) {
					mv.addObject("hideMap", mapConfig);
				} else {
					mv.addObject("msgid", message);
				}

				setAttachmentDetails(addVillageNew);

				addVillageNew.setVillageStatus(listVillageDetails.get(0).getVillageStatus());
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				mv.addObject("modifyVillageCmd", addVillageNew);
				mv.addObject("govtfilecount", attachmentList.size());
				mv.addObject("mapfilecount", mapAttachmentList.size());
				mv.addObject("mandatoryFlag", mandatoryFlag);
				mv.addObject("mapConfig", true);

			} else {
				addVillageNew.setOperationCode(operationCode);
				httpSession.setAttribute("formbean", addVillageNew);
				VillageForm villageForm = (VillageForm) httpSession.getAttribute("formbean");
				villageForm.setUserId(userId.intValue());
				villageForm.setIsPesaStatus(addVillageNew.getIsPesaStatus());
				// List<GovernmentOrderTemplate> templateList = new
				// ArrayList<GovernmentOrderTemplate>();
				// templateList = new ArrayList<GovernmentOrderTemplate>();
				String govtOrderConfig = null;
				char operation = villageForm.getOperation();
				govtOrderConfig = villageForm.getGovtOrderConfig();
				villageForm.setIsExistingGovtOrder(isExistGovtOrder);
				if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (operation == 'C') {
						// templateList =
						// govtOrderTemplateService.getTemplateListByOperationCode(10);
						if (addVillageNew.getMapfilecount() != null && addVillageNew.getMapfilecount().equalsIgnoreCase("0") && villageForm.getAttachFile2() != null) {
							List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapVilModify(request, villageForm, result, mv);
							httpSession.setAttribute("validFileMap", validFileMap);
						}
					}
				} else {
					if (addVillageNew.getMapfilecount() != null && addVillageNew.getMapfilecount().equalsIgnoreCase("0") && villageForm.getAttachFile2() != null) {
						List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapVilModify(request, villageForm, result, mv);
						httpSession.setAttribute("validFileMap", validFileMap);
					}
				}
				int templCode = 0;
				int Emailflag = 0;
				int Transactionid = 0;
				if (villageForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					if (operation == 'C' || operation == 'I' || operation == 'M') {
						String templateBodySrc = govtOrderService.previewTemplate(templCode, httpSession);
						villageForm.setTemplateBodySrc(templateBodySrc);
						mv = new ModelAndView("previewGovtOrder");
					}
				} else if (villageForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					if (operation == 'C') {
						Integer vcode = null;
						String tid = null;
						String vc = null;
						String villagedata = villageService.insertVillageModify(stateCode, villageForm, request, httpSession);
						if (villagedata != null) {
							String[] lbp = villagedata.split(",");
							vc = lbp[0];
							if (villageForm.getButtonClicked().equals("P"))
								tid = lbp[1];
							vcode = Integer.parseInt(vc);
						}
						if (vcode != null) {
							// Data and upload file
							if (villageForm.getButtonClicked() == 'S') {
								if (httpSession.getAttribute("validFileMap") != null) {
									@SuppressWarnings("unchecked")
									List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
									villageForm.setVillageCode(vcode);
									// boolean insertTableFlag =
									villageService.saveDataInMapDraft(villageForm, validFileMap, httpSession);
								}
								if (addVillageNew.getGovtfilecount() != null && addVillageNew.getGovtfilecount().equalsIgnoreCase("0")) {
									List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillageModify(request, villageForm, result);
									if ((("Y").equalsIgnoreCase(isExistGovt)) && (attachmentList.size() > 0)) {
										validFileGovtUpload = new ArrayList<AttachedFilesForm>();
										AttachedFilesForm filesForm = new AttachedFilesForm();
										filesForm.setFileLocation(attachmentList.get(0).getFileLocation());
										filesForm.setFileType(attachmentList.get(0).getFileContentType());
										filesForm.setFileName(attachmentList.get(0).getFileName());
										filesForm.setFileSize(attachmentList.get(0).getFileSize());
										filesForm.setFileTimestampName(attachmentList.get(0).getFileTimestamp());
										validFileGovtUpload.add(filesForm);
									}
									// boolean insertGovtTableFlag =
									villageService.saveDataInAttachDraftVilCreate(villageForm, validFileGovtUpload, vcode, request.getSession());
								}
								mv = new ModelAndView("success");
								mv.addObject("msgid", "Draft Village Created Successfully");
							} else {
								if (httpSession.getAttribute("validFileMap") != null) {

									if (addVillageNew.getMapfilecount() != null && addVillageNew.getMapfilecount().equalsIgnoreCase("0")) {
										@SuppressWarnings("unchecked")
										List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
										villageForm.setVillageCode(vcode);
										// boolean insertTableFlag =
										villageService.saveDataInMap(villageForm, validFileMap, httpSession);
									} else {
										// List<AttachedFilesForm> validFileMap
										// = (List<AttachedFilesForm>)
										// httpSession.getAttribute("validFileMap");
										villageForm.setVillageCode(vcode);
										// boolean insertTableFlag =
										villageService.saveDataInMapDraftVillageModify(villageForm, mapAttachmentList, httpSession);
									}

								}
								if (("N").equals(isExistGovt)) {
									if (addVillageNew.getGovtfilecount() != null && addVillageNew.getGovtfilecount().equalsIgnoreCase("0")) {
										List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillageModify(request, villageForm, result);
										// boolean insertGovtTableFlag =
										villageService.saveDataInAttach(null, validFileGovtUpload, vcode, request.getSession());
									} else {
										// List<AttachedFilesForm>
										// validFileGovtUpload =
										// fileUploadUtility.ValidateAndUploadFileGovtOrderVillageModify(request,
										// villageForm, result);
										// boolean insertGovtTableFlag =
										villageService.saveDataInAttachDraftVillageModify(null, attachmentList, vcode, request.getSession());
									}
								}
								Emailflag = 1;
								if (tid != null) {
									Transactionid = Integer.parseInt(tid);
									Emailflag = 1;
								}
								villageService.deleteDraftVillageModify(villDataForm.get(0).getVillageCode(), request, httpSession);
								mv = new ModelAndView("success");
								mv.addObject("msgid", "Draft Village Published Successfully");
								/*
								 * mv = new ModelAndView("success");
								 * mv.addObject("msgid",
								 * "New Village Created Successfully");
								 */

								if (Emailflag == 1) {
									int t_code = Transactionid;
									char t_type = 'V';
									EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
									est.start();

								}
							}

						} else {
							mv = new ModelAndView("success");
							mv.addObject("msgid", "Village Creation failure please check logs");
						}
					}
				}

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	/**
	 * Add on 24-08-2014 To publish the draft mode village details into village
	 * entity.
	 */
	@RequestMapping(value = "/publichModifyVillage", method = RequestMethod.GET)
	public ModelAndView publichModifyDraftVillage(@ModelAttribute("modifyVillageCmd") VillageDataForm modifyVillageCmd, BindingResult result, HttpServletRequest request, Model model, HttpSession httpSession, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			String id = request.getParameter("villageId");
			int villageCode = (modifyVillageCmd.getVillageId() == null) ? Integer.parseInt(id) : modifyVillageCmd.getVillageId();

			modifyVillageCmd.setDraftVillageCode(villageCode);
			httpSession.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(httpSession, response, abstractValidator);

			mav = new ModelAndView("view_village_draft");
			List<VillageDraft> listVillageDet = villageService.getVillageDraftDetails(villageCode);
			List<VillageDataForm> listVillageDetails = villageService.getVillageDraftDetailsModify(villageCode);

			int Emailflag = 0;
			int Transactionid = 0;
			Integer vcode = null;
			String tid = null;
			String vc = null;
			if (listVillageDet != null) {
				// String govtOrderConfig = null;
				// char operation = 'C';
				// List<GovernmentOrderTemplate> templateList = new
				// ArrayList<GovernmentOrderTemplate>();
				// templateList = new ArrayList<GovernmentOrderTemplate>();
				// govtOrderConfig = listVillageDet.get(0).getGovtOrder();
				/*
				 * if (govtOrderConfig.equals("govtOrderGenerate")) { if
				 * (operation == 'C') { templateList =
				 * govtOrderTemplateService.getTemplateListByOperationCode(10);
				 * if (villageForm.getAttachFile1() != null) {
				 * List<AttachedFilesForm> validFileMap =
				 * fileUploadUtility.ValidateAndUploadFileMap(request,
				 * villageForm, result, mv);
				 * httpSession.setAttribute("validFileMap", validFileMap); } } }
				 * else { if (villageForm.getAttachFile1() != null) {
				 * List<AttachedFilesForm> validFileMap =
				 * fileUploadUtility.ValidateAndUploadFileMap(request,
				 * villageForm, result, mv);
				 * httpSession.setAttribute("validFileMap", validFileMap); } }
				 */
				String villagedata = villageService.publishDraftVillageModify(stateCode, listVillageDet.get(0), request, httpSession);
				if (villagedata != null) {
					String[] lbp = villagedata.split(",");
					vc = lbp[0];
					tid = lbp[1];
					vcode = Integer.parseInt(vc);
				}
				if (vcode != null) {
					// Data and upload file
					if (httpSession.getAttribute("validFileMap") != null) {
						// List<AttachedFilesForm> validFileMap =
						// (List<AttachedFilesForm>)
						// httpSession.getAttribute("validFileMap");
						// boolean insertTableFlag =
						villageService.saveDataInMapDraftVilModify(listVillageDetails.get(0), httpSession);
					}
					// List<AttachedFilesForm> validFileGovtUpload =
					// fileUploadUtility.ValidateAndUploadFileGovtOrderVillage(request,
					// villageForm, result);
					attachmentList = new ArrayList<Attachment>();
					Attachment attachmentDet = new Attachment();
					mapAttachmentList = new ArrayList<MapAttachment>();
					MapAttachment mapAttachmentDet = new MapAttachment();

					if (listVillageDet != null && listVillageDet.get(0).getGovt_order_file_name() != null) {
						attachmentDet.setFileLocation(listVillageDet.get(0).getGovt_order_file_location());
						attachmentDet.setFileContentType(listVillageDet.get(0).getGovt_order_file_content_type());
						attachmentDet.setFileName(listVillageDet.get(0).getGovt_order_file_name());
						attachmentDet.setFileSize(listVillageDet.get(0).getGovt_order_file_size());
						attachmentDet.setFileTimestamp(listVillageDet.get(0).getGovt_order_file_timestamp());
						attachmentList.add(attachmentDet);
						// boolean insertGovtTableFlag =
						villageService.saveDataInAttachDraftVillageModify(null, attachmentList, vcode, request.getSession());
					}
					if (listVillageDet != null && listVillageDet.get(0).getMap_upload_file_name() != null) {
						mapAttachmentDet.setFileLocation(listVillageDet.get(0).getMap_upload_file_location());
						mapAttachmentDet.setFileContentType(listVillageDet.get(0).getMap_upload_file_content_type());
						mapAttachmentDet.setFileName(listVillageDet.get(0).getMap_upload_file_name());
						mapAttachmentDet.setFileSize(listVillageDet.get(0).getMap_upload_file_size());
						mapAttachmentDet.setFileTimestamp(listVillageDet.get(0).getMap_upload_file_timestamp());
						mapAttachmentList.add(mapAttachmentDet);
						// boolean insertGovtTableFlag =
						villageService.saveDataInMapDraftVillageModify(null, mapAttachmentList, request.getSession());
					}

					Emailflag = 1;
					if (tid != null) {
						Transactionid = Integer.parseInt(tid);
						Emailflag = 1;
					}
					villageService.deleteDraftVillageModify(listVillageDet.get(0).getVillageCode(), request, httpSession);
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Draft Village Published Successfully");

					if (Emailflag == 1) {
						int t_code = Transactionid;
						char t_type = 'V';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();

					}

				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Village Modification failure please check logs");
				}

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/* added by Ashish Dhupia on 20/1/2015 for Habitation use case 
	//@RequestMapping(value = "/createHabitation", method = RequestMethod.GET)
	public ModelAndView habitation(@ModelAttribute("habitation1") HabitationForm habitation, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		List<District> districtList = null;
		districtList = new ArrayList<District>();
		try {
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			mav = new ModelAndView("habitationvillage");
			
			mav.addObject("slc", stateCode);
			districtList = districtService.getDistrictList(stateCode);
			mav.addObject("districtList", districtList);

			// model.addAttribute("habitation", new habitation());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/savehabitation", method = RequestMethod.POST)
	public ModelAndView savehabitation(@ModelAttribute("habitation1") HabitationForm habitation, BindingResult result, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("habitationvillage");
			boolean checksave = false;
			List<District> districtList = null;
			districtList = new ArrayList<District>();
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			
			mav.addObject("slc", stateCode);
			districtList = districtService.getDistrictList(stateCode);
			mav.addObject("districtList", districtList);
			char parent_type = habitation.getParentType();
			villageValidator.validateHabitationForm(habitation, result, parent_type);
			if (result.hasErrors()) {
				return mav;
			} else {
				Habitation habitationDet = new Habitation();
				
				habitationDet.setCreatedby(userId);
				habitationDet.setLastupdatedby(userId);
				habitationDet.setCreatedon(new Date());
				habitationDet.setLastupdated(new Date());
				habitationDet.setIsactive(true);
				habitationDet.setHabitationVersion(1);
				habitationDet.setFlagcode(null);
				habitationDet.setTransactionId(null);
				habitationDet.setHabitationNameEnglish(habitation.getHabitationNameEnglish());
				habitationDet.setHabitationNameLocal(habitation.getHabitationNameLocal());
				habitationDet.setEffectiveDate(habitation.getEffectiveDate());
				habitationDet.setSscode(habitation.getSscode());
				habitationDet.setParentType(habitation.getParentType());
				if (habitation.getParentType() == 'V') {
					habitationDet.setParentCode(habitation.getVparentCode());
				} else {
					habitationDet.setParentCode(habitation.getParentCode());
				}
				checksave = localGovtBodyService.saveHabitation(habitationDet);
				if (checksave == true) {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Habitation Creation Successfully!");
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Habitation is Not Creation Successfully!");
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}*/

	@RequestMapping(value = "/renameModifyVillage", method = RequestMethod.GET)
	public ModelAndView renameDraftVillage(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, Model model, HttpSession session, @RequestParam(value = "disturb", required = false) String disturb, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = null;

		try {
			char operation = 'M';
			
			int operationCode = 11;

			int villageCode = modifyVillageCmd.getVillageId();
			String preVersionEffecDate = "";
			request.setAttribute("stateCode", stateCode);
			/*
			 * List<VillageDataForm> listVillageDetails = villageService
			 * .getVillageDetailsModify(villageCode);
			 */

			// Added by Anchal Todariya on 28-03-2015 for Security audit fix
			modifyVillageCmd.setDraftVillageCode(villageCode);
			session.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(session, response, abstractValidator);
			List<VillageDataForm> listVillageDetails = villageService.getVillageDraftDetailsModify(villageCode);
			List<VillageDraft> vilDraftattachmentList = new ArrayList<VillageDraft>();
			if (!listVillageDetails.isEmpty()) {
				VillageDataForm element = listVillageDetails.get(0);
				EsapiEncoder.encode(listVillageDetails);
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				String message = hMap.get("message");
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				if (govtOrderConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
					return mv;

				}
				// mv.addObject("govtOrderConfig",
				// listVillageDetails.get(0).getGovtOrder());
				// mv.addObject("existVilOrUlbFlag",listVillageDetails.get(0).getExistVilOrUlbFlag());

				if (govtOrderConfig != null) {

					mv = new ModelAndView("modifyDraftVillagechange");
					mv.addObject("govtOrderConfig", govtOrderConfig);
					model.addAttribute("listVillageDetails", listVillageDetails);
					model.addAttribute("disturb", disturb);
					modifyVillageCmd.setListVillageDetails(listVillageDetails);
					mv.addObject("modifyVillageCmd", modifyVillageCmd);
					model.addAttribute("size", listVillageDetails.size());
					mv.addObject("subDistrictCode", element.getSubdistrictCode()); // for
																					// unique
																					// constrain

					setAttachmentDetails(modifyVillageCmd);
					attachmentList = new ArrayList<Attachment>();
					if (villageCode > 0)
						vilDraftattachmentList = govtOrderService.getAttachmentsbyOrderCodeForDraftVil(villageCode);
					Attachment attachmentDet = new Attachment();
					if (("N").equalsIgnoreCase(listVillageDetails.get(0).getIsExistGovtOrder())) {
						if (vilDraftattachmentList != null && vilDraftattachmentList.get(0).getGovt_order_file_name() != null) {
							attachmentDet.setFileLocation(vilDraftattachmentList.get(0).getGovt_order_file_location());
							attachmentDet.setFileContentType(vilDraftattachmentList.get(0).getGovt_order_file_content_type());
							attachmentDet.setFileName(vilDraftattachmentList.get(0).getGovt_order_file_name());
							attachmentDet.setFileSize(vilDraftattachmentList.get(0).getGovt_order_file_size());
							attachmentDet.setFileTimestamp(vilDraftattachmentList.get(0).getGovt_order_file_timestamp());
							attachmentList.add(attachmentDet);
						}
					} else {
						if (vilDraftattachmentList != null && vilDraftattachmentList.get(0).getGovt_order_file_name() != null) {
							mv.addObject("existUploadGovtOrderFileName", vilDraftattachmentList.get(0).getGovt_order_file_name());
							mv.addObject("existUploadGovtOrderFileTimeStamp", vilDraftattachmentList.get(0).getGovt_order_file_timestamp());
						}
					}
					request.setAttribute("attachmentList", attachmentList);
					mv.addObject("mandatoryFlag", true);
					mv.addObject("modifyVillageCmd", modifyVillageCmd);
					mv.addObject("govtfilecount", attachmentList.size());
					mv.addObject("isExistGovt", listVillageDetails.get(0).getIsExistGovtOrder());
					mv.addObject("orderCode", listVillageDetails.get(0).getOrderCode());
					mv.addObject("renameVillageCode", listVillageDetails.get(0).getRenameVillageCode());
					/**
					 * Added by Pooja on 18-11-2015 to show label of Previous
					 * version Effective Date
					 */
					String preVersionEffDate = villageService.getMaxPreVersionEffDateOfVillages(listVillageDetails.get(0).getRenameVillageCode().toString());
					String[] string_start = preVersionEffDate.split("-");
					preVersionEffecDate = string_start[2] + "-" + string_start[1] + "-" + string_start[0];
					mv.addObject("preVersionEffDate", preVersionEffecDate);
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	/**
	 * For Rename Draft Mode
	 * 
	 * @author Ripunj on 05-03-2015
	 * @param modifyVillageCmd
	 * @param result
	 * @param model
	 * @param session
	 * @param disturb
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/renameModifyVillage", method = RequestMethod.POST)
	public ModelAndView renameDraftVillageSaveOrPublish(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, Model model, HttpSession session, @RequestParam(value = "disturb", required = false) String disturb,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = null;

		try {
			char operation = 'M';
			
			int operationCode = 11;
			// boolean saveSuccess=false;
			int villageCode = modifyVillageCmd.getVillageId();
			request.setAttribute("stateCode", stateCode);
			/*
			 * List<VillageDataForm> listVillageDetails = villageService
			 * .getVillageDetailsModify(villageCode);
			 */
			List<VillageDataForm> listVillageDetails = villageService.getVillageDraftDetailsModify(villageCode);

			modifyVillageCmd.setDraftVillageCode(villageCode);
			session.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(session, response, abstractValidator);

			List<VillageDraft> vilDraftattachmentList = new ArrayList<VillageDraft>();
			int templCode = 0;
			if (!listVillageDetails.isEmpty()) {
				
				
				List<AttachedFilesForm> validFileGovtUpload = null;
				VillageDataForm element = listVillageDetails.get(0);
				String isExistGovtOrder = request.getParameter("checkExistingGovtOrder");
				modifyVillageCmd.setIsExistingGovtOrder(isExistGovtOrder);
				session.setAttribute("formbean", modifyVillageCmd);
				EsapiEncoder.encode(listVillageDetails);
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				String message = hMap.get("message");
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				if (govtOrderConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
					return mv;

				}
				mv = new ModelAndView("modifyDraftVillagechange");
				modifyVillageCmd = villageValidator.validateChangeModify(modifyVillageCmd, result, request);
				if (result.hasErrors()) {
					result.getErrorCount();
					result.getAllErrors();
					mv.addObject("govtOrderConfig", modifyVillageCmd.getGovtOrderConfig());
					mv.addObject("modifyVillageCmd", modifyVillageCmd);
					return mv;
				} else {
					VillageForm villageForm = (VillageForm) session.getAttribute("formbean");
					if (("0").equals(request.getParameter("govtfilecount")) || ("Y").equals(isExistGovtOrder))
						attachmentList = new ArrayList<Attachment>();

					if (modifyVillageCmd.getOrderCode() != null && ("Y").equals(isExistGovtOrder))
						attachmentList = govtOrderService.getAttachmentsbyOrderCode(modifyVillageCmd.getOrderCode());

					if (govtOrderConfig != null) {

						mv = new ModelAndView("modifyDraftVillagechange");
						mv.addObject("govtOrderConfig", govtOrderConfig);
						model.addAttribute("listVillageDetails", listVillageDetails);
						model.addAttribute("disturb", disturb);
						mv.addObject("modifyVillageCmd", modifyVillageCmd);
						model.addAttribute("size", listVillageDetails.size());
						mv.addObject("subDistrictCode", element.getSubdistrictCode()); // for
																						// unique
																						// constrain
						modifyVillageCmd.setRenameVillageCode(listVillageDetails.get(0).getRenameVillageCode());
						setAttachmentDetails(modifyVillageCmd);
						if (villageCode > 0)
							vilDraftattachmentList = govtOrderService.getAttachmentsbyOrderCodeForDraftVil(villageCode);
						Attachment attachmentDet = new Attachment();
						if (("N").equalsIgnoreCase(isExistGovtOrder)) {
							attachmentList = new ArrayList<Attachment>();
							if (vilDraftattachmentList != null && vilDraftattachmentList.get(0).getGovt_order_file_name() != null) {
								attachmentDet.setFileLocation(vilDraftattachmentList.get(0).getGovt_order_file_location());
								attachmentDet.setFileContentType(vilDraftattachmentList.get(0).getGovt_order_file_content_type());
								attachmentDet.setFileName(vilDraftattachmentList.get(0).getGovt_order_file_name());
								attachmentDet.setFileSize(vilDraftattachmentList.get(0).getGovt_order_file_size());
								attachmentDet.setFileTimestamp(vilDraftattachmentList.get(0).getGovt_order_file_timestamp());
								attachmentList.add(attachmentDet);
							}
						}
						request.setAttribute("attachmentList", attachmentList);
						mv.addObject("mandatoryFlag", true);
						mv.addObject("modifyVillageCmd", modifyVillageCmd);
						mv.addObject("govtfilecount", attachmentList.size());
						mv.addObject("isExistGovt", isExistGovtOrder);
						mv.addObject("orderCode", modifyVillageCmd.getOrderCode());
						villageForm.setIsExistingGovtOrder(isExistGovtOrder);
						villageForm.setOrderCode(modifyVillageCmd.getOrderCode());
						GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
						if (modifyVillageCmd.getGovtOrderConfig().equals("govtOrderGenerate")) {
							if (operation == 'C' || operation == 'I' || operation == 'M') {
								String templateBodySrc = govtOrderService.previewTemplate(templCode, session);
								modifyVillageCmd.setTemplateBodySrc(templateBodySrc);
								mv = new ModelAndView("previewGovtOrder");
							}
						} else if (modifyVillageCmd.getGovtOrderConfig().equals("govtOrderUpload")) {

							if (modifyVillageCmd.getButtonClicked() == 'S') {
								validFileGovtUpload = new ArrayList<AttachedFilesForm>();
								validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillageModify(request, modifyVillageCmd, result);
							} else {
								if (("N").equalsIgnoreCase(isExistGovtOrder)) {
									validFileGovtUpload = new ArrayList<AttachedFilesForm>();
									validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillageModify(request, modifyVillageCmd, result);
								} else {
									validFileGovtUpload = new ArrayList<AttachedFilesForm>();
									AttachedFilesForm filesForm = new AttachedFilesForm();
									filesForm.setFileLocation(attachmentList.get(0).getFileLocation());
									filesForm.setFileType(attachmentList.get(0).getFileContentType());
									filesForm.setFileName(attachmentList.get(0).getFileName());
									filesForm.setFileSize(attachmentList.get(0).getFileSize());
									filesForm.setFileTimestampName(attachmentList.get(0).getFileTimestamp());
									validFileGovtUpload.add(filesForm);
								}
							}
							// saveSuccess =
							villageService.changeVillageModify(villageForm, govtOrderForm, validFileGovtUpload, request, userId.intValue());
							/*
							 * if (villageForm.getOrderCode() != null)
							 * attachmentList =
							 * govtOrderService.getAttachmentsbyOrderCode(
							 * villageForm.getOrderCode() );
							 */
							if (villageForm.getButtonClicked() == 'S') {
								if (villageForm.getGovtfilecount() != null && villageForm.getGovtfilecount().equalsIgnoreCase("0")) {
									validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillageModify(request, villageForm, result);
									if ((("Y").equalsIgnoreCase(isExistGovtOrder)) && (attachmentList.size() > 0)) {
										validFileGovtUpload = new ArrayList<AttachedFilesForm>();
										AttachedFilesForm filesForm = new AttachedFilesForm();
										filesForm.setFileLocation(attachmentList.get(0).getFileLocation());
										filesForm.setFileType(attachmentList.get(0).getFileContentType());
										filesForm.setFileName(attachmentList.get(0).getFileName());
										filesForm.setFileSize(attachmentList.get(0).getFileSize());
										filesForm.setFileTimestampName(attachmentList.get(0).getFileTimestamp());
										validFileGovtUpload.add(filesForm);
									}
									// boolean insertGovtTableFlag =
									villageService.saveDataInAttachDraftVilCreate(villageForm, validFileGovtUpload, villageForm.getVillageId(), request.getSession());
								}
								mv = new ModelAndView("success");
								mv.addObject("msgid", "Draft Village Renamed Successfully");
							} else {
								villageService.deleteDraftVillageModify(villageForm.getVillageId(), request, session);
								mv = new ModelAndView("success");
								mv.addObject("msgid", "Renamed Draft Village Published Successfully");

							}
						}
						if (villageForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
							villageService.changeVillagegenerate(villageForm, govtOrderForm, validFileGovtUpload, request, userId.intValue(), session);
						}
					}
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	/**
	 * For Rename Draft Mode To Publish Renamed Villages
	 * 
	 * @author Ripunj on 05-03-2015
	 * @param modifyVillageCmd
	 * @param result
	 * @param model
	 * @param session
	 * @param disturb
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/renameModifyVillagePublish", method = RequestMethod.GET)
	public ModelAndView renameModifyVillagePublish(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, Model model, HttpSession session, @RequestParam(value = "disturb", required = false) String disturb,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = null;

		try {
			char operation = 'M';
			int operationCode = 11;
			// boolean saveSuccess=false;
			int villageCode = modifyVillageCmd.getVillageId();
			// Added by Anchal Todariya on 28-03-2015 for Security audit fix
			modifyVillageCmd.setDraftVillageCode(villageCode);
			session.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(session, response, abstractValidator);
			request.setAttribute("stateCode", stateCode);
			List<VillageDataForm> listVillageDetails = villageService.getVillageDraftDetailsModify(villageCode);

			List<VillageDraft> vilDraftattachmentList = new ArrayList<VillageDraft>();
			if (!listVillageDetails.isEmpty()) {
				
				List<AttachedFilesForm> validFileGovtUpload = null;
				String isExistGovtOrder = request.getParameter("checkExistingGovtOrder");
				modifyVillageCmd.setIsExistingGovtOrder(isExistGovtOrder);
				modifyVillageCmd.setListVillageDetails(listVillageDetails);
				session.setAttribute("formbean", modifyVillageCmd);
				EsapiEncoder.encode(listVillageDetails);
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				String message = hMap.get("message");
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				if (govtOrderConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
					return mv;

				}
				VillageForm villageForm = (VillageForm) session.getAttribute("formbean");
				if (govtOrderConfig != null) {

					mv = new ModelAndView("modifyDraftVillagechange");
					mv.addObject("govtOrderConfig", govtOrderConfig);

					modifyVillageCmd.setRenameVillageCode(listVillageDetails.get(0).getRenameVillageCode());
					setAttachmentDetails(modifyVillageCmd);
					attachmentList = new ArrayList<Attachment>();
					if (villageCode > 0)
						vilDraftattachmentList = govtOrderService.getAttachmentsbyOrderCodeForDraftVil(villageCode);
					if (("N").equalsIgnoreCase(listVillageDetails.get(0).getIsExistGovtOrder())) {
						if (vilDraftattachmentList != null && vilDraftattachmentList.get(0).getGovt_order_file_name() != null) {
							validFileGovtUpload = new ArrayList<AttachedFilesForm>();
							AttachedFilesForm filesForm = new AttachedFilesForm();
							filesForm.setFileLocation(vilDraftattachmentList.get(0).getGovt_order_file_location());
							filesForm.setFileType(vilDraftattachmentList.get(0).getGovt_order_file_content_type());
							filesForm.setFileName(vilDraftattachmentList.get(0).getGovt_order_file_name());
							filesForm.setFileSize(vilDraftattachmentList.get(0).getGovt_order_file_size());
							filesForm.setFileTimestampName(vilDraftattachmentList.get(0).getGovt_order_file_timestamp());
							validFileGovtUpload.add(filesForm);
						}
					}
					GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
					villageForm.setGovtOrderConfig(govtOrderConfig);
					villageForm.setIsExistingGovtOrder(listVillageDetails.get(0).getIsExistGovtOrder());
					// saveSuccess =
					villageService.changeVillageModifyPublish(villageForm, govtOrderForm, validFileGovtUpload, request, userId.intValue());
					villageService.deleteDraftVillageModify(villageForm.getVillageId(), request, session);
					mv = new ModelAndView("success");
					mv.addObject("msgid", "Renamed Draft Village Published Successfully");
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	/**
	 * Denotify grampanchyats merged/declared as OLD.
	 * 
	 * @author Anchal Todariya on 02-03-2015
	 * 
	 */

	@RequestMapping(value = "/deNotifyGramPancht", method = RequestMethod.GET)
	public ModelAndView deNofityGP(@ModelAttribute("denotifyGP") LBFreezeForm lbfreeze, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("deNotifyGP");
		try {
			
			lbfreeze.setStateCode(stateCode.toString());
			
			// List<GetLocalGovtSetup> getLocalGovtSetupList =
			// localGovtSetupService.getLocalbodyDetail(stateCode);
			List<District> districtList = districtService.getDistrictList(stateCode);
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			if (localBodyTypelist.size() == 2) {
				model.addAttribute("Tiertype", 2);
				mav.addObject("Tiertype", 2);
				httpSession.setAttribute("Tiertype", 2);
			} else if (localBodyTypelist.size() > 2) {
				model.addAttribute("Tiertype", 3);
				mav.addObject("Tiertype", 3);
				httpSession.setAttribute("Tiertype", 3);
			}
			if (stateCode != 34) {
				List<LocalbodyListbyStateold> districtPanchayatListold = stateDistrictFService.getExistingLBListbyStateandCategory(stateCode, 'P');
				model.addAttribute("districtPanchayatList", districtPanchayatListold);
				httpSession.setAttribute("districtPanchayatList", districtPanchayatListold);
			} else {
				List<LocalbodyListbyStateold> districtPanchayatList = stateDistrictFService.getExistingPanchayatListold(stateCode);
				model.addAttribute("districtPanchayatList", districtPanchayatList);
				httpSession.setAttribute("districtPanchayatList", districtPanchayatList);
			}
			model.addAttribute("statecode", stateCode);
			httpSession.setAttribute("statecode", stateCode);
			model.addAttribute("districtList", districtList);
			httpSession.setAttribute("districtList", districtList);
			model.addAttribute("tableflag", 1);
			// model.addAttribute("getLocalGovtSetupList",
			// getLocalGovtSetupList);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}

	@RequestMapping(value = "/deNotifyGramPanchtPost", method = RequestMethod.POST)
	public ModelAndView deNofityGPPost(@ModelAttribute("denotifyGP") LBFreezeForm lbfreeze, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("deNotifyGP");
		String statecode = null;
		String localbType = "V";
		try {
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
		
			mav.addObject("Tiertype", httpSession.getAttribute("Tiertype"));
			model.addAttribute("districtPanchayatList", httpSession.getAttribute("districtPanchayatList"));
			model.addAttribute("statecode", statecode);
			model.addAttribute("districtList", httpSession.getAttribute("districtList"));
			lbfreeze.setLocalbType(localbType);
			String localbodyCode = null;
			if (stateCode==19) {
				localbodyCode = lbfreeze.getGtaList();
			} else if (stateCode==14) {
				localbodyCode = lbfreeze.getDisPanchyat();
			} else {
				localbodyCode = lbfreeze.getIntermediatePanchyat();
			}
			httpSession.setAttribute("localbodyCode", localbodyCode);
			List<VillagePanchyatDeNotified> deNotifiedVPList = stateDistrictFService.getListforLBtoDenotify(Integer.parseInt(localbodyCode));
			String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
			lbfreeze.setStateName(stateName);
			mav.addObject("lbfreeze", lbfreeze);
			mav.addObject("deNotifiedVPList", deNotifiedVPList);
			model.addAttribute("tableflag", 0);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/deNotifyGPVillage", method = RequestMethod.POST)
	public ModelAndView deNofityGPVillages(@ModelAttribute("denotifyGP") LBFreezeForm lbfreeze, BindingResult result, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("deNotifyGPULBRLB");
		int operationCode = 10;
		String localbodyCode = null;
		
		try {
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			int count = stateDistrictFService.getDenotifyVPCount(lbfreeze.getTransactionid());
			if (count > 0) {
				mav.addObject("message", "De-Notification can not be Done as RLB is partly Selected !");
				localbodyCode = httpSession.getAttribute("localbodyCode").toString();
				List<VillagePanchyatDeNotified> deNotifiedVPList = stateDistrictFService.getListforLBtoDenotify(Integer.parseInt(localbodyCode));
				List<String> getRLBs = stateDistrictFService.getRLBS(lbfreeze.getTransactionid());
				String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
				lbfreeze.setStateName(stateName);
				mav.addObject("lbfreeze", lbfreeze);
				mav.addObject("tranId", lbfreeze.getTransactionid());
				model.addAttribute("getRLBs", getRLBs);
				model.addAttribute("deNotifiedVPList", deNotifiedVPList);
				model.addAttribute("tableflag", 0);
				model.addAttribute("message", "De-Notification can not be Done as RLB is partly Selected !");
				mav = new ModelAndView("deNotifyGP");
				return mav;
			} else {
				List<VillagePanchyatDeNotified> getVPULBList = stateDistrictFService.getListforULBGPtoDenotify(lbfreeze.getTransactionid());
				List<VillagePanchyatDeNotified> getVPRLBList = stateDistrictFService.getListforRLBGPtoDenotify(lbfreeze.getTransactionid());
				List<VPDeNotifiedCoveraged> getCoveragedData = null;
				for (VillagePanchyatDeNotified code : getVPULBList) {
					getCoveragedData = stateDistrictFService.getListforCoveraged(code.getLocalBodyCode());
				}
				mav.addObject("tranId", lbfreeze.getTransactionid());
				mav.addObject("getCoveragedData", getCoveragedData);
				mav.addObject("getVPULBList", getVPULBList);
				mav.addObject("getVpRLBList", getVPRLBList);
				// -------------------------code to fetch govt order
				// details-------------------------------------------------------start--*
				
				List<District> districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
				// Copy to Check Configuration
				Map<String, String> hMap = new HashMap<String, String>();
				// Please first check your operation code then set it in place
				// of 10
				hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, operationCode, 'V'); // 10
																												// is
																												// operation
																												// code
																												// for
																												// create
																												// new
																												// village
				String govtOrderConfig = hMap.get("govtOrder"); // values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload"); // values==true,false
				if (govtOrderConfig != null && mapConfig != null) {
					mav.addObject("hideMap", mapConfig);
					mav.addObject("districtList", districtList);
					char operation = 'C';
					if (govtOrderConfig.equals("govtOrderGenerate")) {
						if (operation == 'C') {
							List<GovernmentOrderTemplate> templateList = govtOrderTemplateService.getTemplateListByOperationCode(10);
							mav.addObject("templateList", templateList);
						}
					}
					mav.addObject("govtOrderConfig", govtOrderConfig);
				}
				// -------------------------code to fetch govt order
				// details----------------------------------------------------------end--*
			}
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/denotifyULBRLBVillage", method = RequestMethod.POST)
	public ModelAndView deNofityULBRLBVillages(@ModelAttribute("denotifyGP") LBFreezeForm lbfreeze, BindingResult result, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("deNotifyGPULBRLB");
		
		String localbodyCode = null;
		String isExistGovtOrder = request.getParameter("checkExistingGovtOrder");
		try {

			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			lbfreeze.setStateCode(stateCode.toString());
			villageValidator.validateGovOrderForm(lbfreeze, result);
			if (result.hasErrors()) {
				localbodyCode = httpSession.getAttribute("localbodyCode").toString();
				List<VillagePanchyatDeNotified> deNotifiedVPList = stateDistrictFService.getListforLBtoDenotify(Integer.parseInt(localbodyCode));
				String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
				lbfreeze.setStateName(stateName);
				mav.addObject("lbfreeze", lbfreeze);
				model.addAttribute("deNotifiedVPList", deNotifiedVPList);
				model.addAttribute("tableflag", 0);
				mav = new ModelAndView("deNotifyGPULBRLB");
				return mav;
			}
			String govtOrderConfig = null;
			char operation = lbfreeze.getOperation();
			govtOrderConfig = lbfreeze.getGovtOrderConfig();
			if (govtOrderConfig.equals("govtOrderGenerate")) {
				if (operation == 'C') {
					List<GovernmentOrderTemplate> templateList = govtOrderTemplateService.getTemplateListByOperationCode(10);
					mav.addObject("templateList", templateList);
					if (lbfreeze.getAttachFile2() != null) {
						List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapToDenotify(request, lbfreeze, result, mav);
						httpSession.setAttribute("validFileMap", validFileMap);
					}
				}
			} else {
				if (lbfreeze.getAttachFile2() != null) {
					List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapToDenotify(request, lbfreeze, result, mav);
					httpSession.setAttribute("validFileMap", validFileMap);
				}
			}
			int templCode = 0;
			if (lbfreeze.getGovtOrderConfig().equals("govtOrderGenerate")) {
				if (operation == 'C' || operation == 'I' || operation == 'M') {
					String templateBodySrc = govtOrderService.previewTemplate(templCode, httpSession);
					lbfreeze.setTemplateBodySrc(templateBodySrc);
					mav = new ModelAndView("previewGovtOrder");
				}
			} else if (lbfreeze.getGovtOrderConfig().equals("govtOrderUpload")) {
				List<Attachment> attachmentList = new ArrayList<Attachment>();
				String getCoveragedData = null;
				if (lbfreeze.getOrderCode() != null) {
					attachmentList = govtOrderService.getAttachmentsbyOrderCode(lbfreeze.getOrderCode());
					getCoveragedData = stateDistrictFService.setListforULBRLBDenotify(lbfreeze,userId.longValue(), attachmentList.get(0).getFileLocation());
					String message = stateDistrictFService.getTransactionDescription(String.valueOf(lbfreeze.getTransactionid()));
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
				} else {
					List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGOVVP(request, lbfreeze, result);
					if (("Y").equalsIgnoreCase(isExistGovtOrder)) {
						System.out.println();
						validFileGovtUpload = new ArrayList<AttachedFilesForm>();
						AttachedFilesForm filesForm = new AttachedFilesForm();
						filesForm.setFileLocation(attachmentList.get(0).getFileLocation());
						filesForm.setFileType(attachmentList.get(0).getFileContentType());
						filesForm.setFileName(attachmentList.get(0).getFileName());
						filesForm.setFileSize(attachmentList.get(0).getFileSize());
						filesForm.setFileTimestampName(attachmentList.get(0).getFileTimestamp());
						validFileGovtUpload.add(filesForm);
					}
					getCoveragedData = stateDistrictFService.setListforULBRLBDenotify(lbfreeze, userId, validFileGovtUpload.get(0).getFileLocation());
					String[] fetchData = getCoveragedData.split(",");
					if (fetchData.length > 0) {
						// boolean insertTableFlag =
						stateDistrictFService.saveDataInMap(fetchData[1], validFileGovtUpload);
						String message = stateDistrictFService.getTransactionDescription(fetchData[0]);
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
					}
				}

			}
			return mav;
		} catch (Exception e) {
			// IExceptionHandler expHandler =
			// ExceptionHandlerFactory.getInstance().create();
			e.printStackTrace();
			// getCause().getMessage() will fetch the raised exception from
			// database function..
			mav.addObject("msgid", e.getCause().getMessage());
			model.addAttribute("msgid", e.getCause().getMessage());
			mav = new ModelAndView("success");
			return mav;
		}
	}

	/**
	 * For Invalidate Draft Village Mode
	 * 
	 * @author Ripunj on 19-03-2015
	 * @param modifyVillageCmd
	 * @param model
	 * @param session
	 * @param disturb
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/invalidateModifyVillage", method = RequestMethod.GET)
	public ModelAndView invalidateDraftVillage(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, Model model, HttpSession session, @RequestParam(value = "disturb", required = false) String disturb, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = null;
		
		List<District> districtList = null;
		districtList = new ArrayList<District>();
		try {
			char operation = 'I';
			int operationCode = 14;
			if (stateCode == null) {
				return mv = new ModelAndView("redirect:login.htm");
			}

			int villageCode = modifyVillageCmd.getVillageId();
			// Added by Anchal Todariya on 28-03-2015 for Security audit fix
			modifyVillageCmd.setDraftVillageCode(villageCode);
			session.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(session, response, abstractValidator);
			request.setAttribute("stateCode", stateCode);
			List<VillageDataForm> listVillageDetails = villageService.getVillageDraftDetailsModify(villageCode);
			List<VillageDraft> vilDraftattachmentList = new ArrayList<VillageDraft>();
			if (!listVillageDetails.isEmpty()) {
				// VillageDataForm element=listVillageDetails.get(0);
				EsapiEncoder.encode(listVillageDetails);
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				String message = hMap.get("message");
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				if (govtOrderConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
					return mv;
				}
				if (govtOrderConfig != null) {
					mv = new ModelAndView("modifyInvalidateDraftVillagechange");
					if (districtCode == 0) {
						mv.addObject("flag1", 1);
						districtList = districtService.getDistrictListbyStateCodeForLocalBody(stateCode);
					} else {
						districtList = districtService.getDistrictListByDistCode(districtCode);
						mv.addObject("flag1", 0);
						mv.addObject("flag2", districtCode);
					}
					mv.addObject("stateCode", stateCode);
					mv.addObject("govtOrderConfig", govtOrderConfig);
					model.addAttribute("listVillageDetails", listVillageDetails);
					model.addAttribute("disturb", disturb);
					modifyVillageCmd.setListVillageDetails(listVillageDetails);
					mv.addObject("modifyVillageCmd", modifyVillageCmd);
					model.addAttribute("size", listVillageDetails.size());

					boolean mandatoryFlag = true;
					List<Subdistrict> subDistrictList = new ArrayList<Subdistrict>();

					districtList = districtService.getDistrictListByDistCode(Integer.parseInt(listVillageDetails.get(0).getDistrictNameEnglish()));
					mv.addObject("mandatoryFlag", mandatoryFlag);
					mv.addObject("districtList", districtList);
					subDistrictList = subDistrictService.getSubDistrictListBySubDistCode(Integer.parseInt(listVillageDetails.get(0).getSubdistrictNameEnglish()));
					mv.addObject("subDistrictList", subDistrictList);
					mv.addObject("subDistrictCode", listVillageDetails.get(0).getSubdistrictNameEnglish());
					mv.addObject("districtCode", listVillageDetails.get(0).getDistrictNameEnglish());
					mv.addObject("draftVilCode", listVillageDetails.get(0).getVillageCode());
					mv.addObject("govtOrderConfig", listVillageDetails.get(0).getGovtOrder());
					mv.addObject("invalidateVillageList", listVillageDetails.get(0).getInvalidateVillageList());

					setAttachmentDetails(modifyVillageCmd);
					attachmentList = new ArrayList<Attachment>();
					if (villageCode > 0)
						vilDraftattachmentList = govtOrderService.getAttachmentsbyOrderCodeForDraftVil(villageCode);
					Attachment attachmentDet = new Attachment();
					if (("N").equalsIgnoreCase(listVillageDetails.get(0).getIsExistGovtOrder())) {
						if (vilDraftattachmentList != null && vilDraftattachmentList.get(0).getGovt_order_file_name() != null) {
							attachmentDet.setFileLocation(vilDraftattachmentList.get(0).getGovt_order_file_location());
							attachmentDet.setFileContentType(vilDraftattachmentList.get(0).getGovt_order_file_content_type());
							attachmentDet.setFileName(vilDraftattachmentList.get(0).getGovt_order_file_name());
							attachmentDet.setFileSize(vilDraftattachmentList.get(0).getGovt_order_file_size());
							attachmentDet.setFileTimestamp(vilDraftattachmentList.get(0).getGovt_order_file_timestamp());
							attachmentList.add(attachmentDet);
						}
					}
					request.setAttribute("attachmentList", attachmentList);
					mv.addObject("mandatoryFlag", true);
					mv.addObject("modifyVillageCmd", modifyVillageCmd);
					mv.addObject("govtfilecount", attachmentList.size());
					mv.addObject("isExistGovt", listVillageDetails.get(0).getIsExistGovtOrder());
					mv.addObject("orderCode", listVillageDetails.get(0).getOrderCode());
					// mv.addObject("renameVillageCode",modifyVillageCmd.getRenameVillageCode());
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/invalidateModifyVillage", method = RequestMethod.POST)
	public ModelAndView invalidateDraftVillageSaveOrPublish(@ModelAttribute("modifyVillageCmd") VillageForm villageForm, BindingResult result, Model model, HttpSession httpSession, @RequestParam(value = "disturb", required = false) String disturb,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = null;
		Session session = null;
		try {
			String govtOrderConfig = null;
			List<District> districtList = null;
			districtList = new ArrayList<District>();

			String aMessage = "";
			String ordercode = null;
			String orderCode = null;
			String tid = null;
			Integer ulbCode = null;
			// Copy to Check Configuration
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 14, 'V');// 10
																								// is
																								// operation
																								// code
																								// for
																								// create
																								// new
			// village
			govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			villageValidator.villageInvalidationValidation(villageForm, result, request);
			if (result.hasErrors()) {
				districtList = districtService.getDistrictList(stateCode);
				mv = new ModelAndView("modifyInvalidateDraftVillagechange");
				mv.addObject("districtList", districtList);
				mv.addObject("govtOrderConfig", govtOrderConfig);

				boolean mandatoryFlag = true;
				List<Subdistrict> subDistrictList = new ArrayList<Subdistrict>();
				List<VillageDataForm> listVillageDetails = villageService.getVillageDraftDetailsModify(villageForm.getVillageCode());
				districtList = districtService.getDistrictListByDistCode(Integer.parseInt(listVillageDetails.get(0).getDistrictNameEnglish()));
				mv.addObject("mandatoryFlag", mandatoryFlag);
				mv.addObject("districtList", districtList);
				subDistrictList = subDistrictService.getSubDistrictListBySubDistCode(Integer.parseInt(listVillageDetails.get(0).getSubdistrictNameEnglish()));
				mv.addObject("subDistrictList", subDistrictList);
				mv.addObject("subDistrictCode", listVillageDetails.get(0).getSubdistrictNameEnglish());
				mv.addObject("districtCode", listVillageDetails.get(0).getDistrictNameEnglish());
				mv.addObject("draftVilCode", listVillageDetails.get(0).getVillageCode());
				mv.addObject("govtOrderConfig", listVillageDetails.get(0).getGovtOrder());
				villageForm.setListVillageDetails(listVillageDetails);
				model.addAttribute("size", listVillageDetails.size());
				mv.addObject(villageForm);
				return mv;

			} else {

				mv = new ModelAndView("modifyInvalidateDraftVillagechange");
				String isExistGovtOrder = request.getParameter("checkExistingGovtOrder");
				Integer draftVillageCode = 0;
				if (request.getParameter("draftVilCode") != null && !request.getParameter("draftVilCode").equals("")) {
					draftVillageCode = Integer.parseInt(request.getParameter("draftVilCode"));
				}
				villageForm.setDraftVillageCode(draftVillageCode);
				httpSession.setAttribute("formbean", villageForm);
				CommonUtil.CheckForParent(httpSession, response, abstractValidator);

				villageForm.setIsExistingGovtOrder(isExistGovtOrder);
				Integer orderCodeForInvalidateVil = 0;
				if ("Y".equals(isExistGovtOrder)) {
					orderCodeForInvalidateVil = villageForm.getOrderCode();
				}
				govtOrderConfig = villageForm.getGovtOrderConfig();
				httpSession.setAttribute("formbean", villageForm);
				// VillageForm villageFormDet = (VillageForm)
				// httpSession.getAttribute("formbean");
				int templCode = 0;
				List<GovernmentOrderTemplate> templateList = new ArrayList<GovernmentOrderTemplate>();
				templateList = new ArrayList<GovernmentOrderTemplate>();
				// govtOrderConfig = villageFormDet.getGovtOrderConfig();
				if (govtOrderConfig.equals("govtOrderGenerate")) {
					templateList = govtOrderTemplateService.getTemplateListByOperationCode(14);
					mv.addObject("templateList", templateList);
				}
				mv.addObject("govtOrderConfig", govtOrderConfig);
				if (villageForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					templCode = Integer.parseInt(villageForm.getTemplateList());
					villageForm.setTemplateList(villageForm.getTemplateList());
					httpSession.setAttribute("formbean", villageForm);
					String templateBodySrc = govtOrderService.previewTemplate(templCode, httpSession);
					villageForm.setTemplateBodySrc(templateBodySrc);
					mv = new ModelAndView("previewGovtOrder");
				} else if (villageForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					String villCode = villageForm.getInvalidateVillageList();
					
					String destinationVillage = null;
					Integer distinationsubdistrict = null;
					Character operationState = 'P';
					if (villageForm.getSelectedCoveredLandRegionByULB() != null) {
						ulbCode = Integer.parseInt(villageForm.getSelectedCoveredLandRegionByULB());
					}
					if (villageForm.getButtonClicked() != null) {
						operationState = villageForm.getButtonClicked();
					}
					List<VillageDraft> vilDraftattachmentList = new ArrayList<VillageDraft>();
					attachmentList = new ArrayList<Attachment>();
					if (draftVillageCode > 0)
						vilDraftattachmentList = govtOrderService.getAttachmentsbyOrderCodeForDraftVil(draftVillageCode);
					Attachment attachmentDet = new Attachment();
					if (("N").equalsIgnoreCase(isExistGovtOrder)) {
						if (vilDraftattachmentList != null && vilDraftattachmentList.get(0).getGovt_order_file_name() != null) {
							attachmentDet.setFileLocation(vilDraftattachmentList.get(0).getGovt_order_file_location());
							attachmentDet.setFileContentType(vilDraftattachmentList.get(0).getGovt_order_file_content_type());
							attachmentDet.setFileName(vilDraftattachmentList.get(0).getGovt_order_file_name());
							attachmentDet.setFileSize(vilDraftattachmentList.get(0).getGovt_order_file_size());
							attachmentDet.setFileTimestamp(vilDraftattachmentList.get(0).getGovt_order_file_timestamp());
							attachmentList.add(attachmentDet);
						}
					} else {
						if (villageForm.getOrderCode() != null && ("Y").equalsIgnoreCase(isExistGovtOrder))
							attachmentList = govtOrderService.getAttachmentsbyOrderCode(villageForm.getOrderCode());
					}
					List<VillageDataForm> listVillages = new ArrayList<VillageDataForm>();
					listVillages = villageForm.getListVillageDetails();
					Iterator<VillageDataForm> itr = listVillages.iterator();
					Date orderDate = null;
					Date effectiveDate = null;
					Date gazPubDate = null;
					// Timestamp
					// ordertimeStampDate,effectivetimeStampDate,gazPubtimeStampDate=null;
					String orderNo = null;
					while (itr.hasNext()) {

						VillageDataForm element = (VillageDataForm) itr.next();
						orderDate = element.getOrderDatecr();
						effectiveDate = element.getOrdereffectiveDatecr();
						gazPubDate = element.getGazPubDatecr();
						// java.sql.Timestamp gazPubtimeStampDateTemp = null;
						// ordertimeStampDate = new
						// Timestamp(orderDate.getTime());
						// effectivetimeStampDate = new
						// Timestamp(effectiveDate.getTime());
						orderNo = element.getOrderNocr();
						/*
						 * if (gazPubDate != null) { gazPubtimeStampDateTemp =
						 * new Timestamp(gazPubDate.getTime()); }
						 */
						// gazPubtimeStampDate = gazPubtimeStampDateTemp;

						ordercode = villageService.invalidateVillageDAOModify(villCode, userId.intValue(), element.getOrderNocr(), orderDate, effectiveDate, villageForm.getGovtOrderConfig(), gazPubDate, destinationVillage, distinationsubdistrict, session,
								ulbCode, orderCodeForInvalidateVil, isExistGovtOrder, operationState, draftVillageCode);
					}
					villageForm = (VillageForm) httpSession.getAttribute("formbean");
					if (ordercode != null) {
						if (operationState == 'S') {
							aMessage = "Village(s) Invalidated Successfully in Draft Mode";
						} else {
							aMessage = "Invalidated Village(s) Published Successfully";
						}
						List<List<Village>> villageList = null;
						villageList = new ArrayList<List<Village>>();
						if (operationState == 'P') {
							villageList = villageService.getVillageViewList(villageForm);
							mv = new ModelAndView("success");
							mv.addObject("message", aMessage);
						} else {
							mv = new ModelAndView("success");
							mv.addObject("message", aMessage);
						}
						mv.addObject("msgid", aMessage);
						mv.addObject("success", villageForm.getInvalidateVillageList());
						mv.addObject("villageList", villageList);
						session = sessionFactory.openSession();
						Transaction tx = session.beginTransaction();
						if (ordercode != null) {
							String[] ldata = ordercode.split(",");
							orderCode = ldata[0];
							int Transactionid = 0;
							List<AttachedFilesForm> validFileGovtUpload = new ArrayList<AttachedFilesForm>();
							AttachedFilesForm filesForm = new AttachedFilesForm();
							filesForm.setFileLocation(attachmentList.get(0).getFileLocation());
							filesForm.setFileType(attachmentList.get(0).getFileContentType());
							filesForm.setFileName(attachmentList.get(0).getFileName());
							filesForm.setFileSize(attachmentList.get(0).getFileSize());
							filesForm.setFileTimestampName(attachmentList.get(0).getFileTimestamp());
							validFileGovtUpload.add(filesForm);
							if (villageForm.getGovtfilecount() != null && villageForm.getGovtfilecount().equalsIgnoreCase("0")) {
								validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderVillageModify(request, villageForm, result);
							}
							if (villageForm.getButtonClicked() == 'S') {
								// here orderCode is max village draft Code
								// boolean insertGovtTableFlag =
								villageService.saveDataInAttachDraftVilCreate(villageForm, validFileGovtUpload, Integer.parseInt(orderCode), request.getSession());
							} else {
								villageService.deleteDraftVillageModify(draftVillageCode, request, httpSession);
							}
							if (operationState == 'P') {
								tid = ldata[1];
								Transactionid = Integer.parseInt(tid);
								int ocode = Integer.parseInt(orderCode);
								if ("N".equals(villageForm.getIsExistingGovtOrder())) {
									GovernmentOrder govtOrder = new GovernmentOrder();
									govtOrder.setOrderDate(orderDate);
									govtOrder.setEffectiveDate(effectiveDate);
									govtOrder.setGazPubDate(gazPubDate);
									govtOrder.setCreatedon(new Date());
									govtOrder.setDescription("LGD DETAILS");
									govtOrder.setIssuedBy("GOVERNOR");
									govtOrder.setLastupdated(new Date());
									govtOrder.setLevel("U");
									govtOrder.setOrderNo(orderNo);
									govtOrder.setStatus('A');
									govtOrder.setOrderCode(ocode);
									convertLocalbodyService.saveDataInAttachment(govtOrder, null, validFileGovtUpload, session);
								}
							}
							tx.commit();
							if (operationState == 'P') {
								EmailSmsThread est = new EmailSmsThread(Transactionid, 'V', emailService);
								est.start();
							}
						}
					} else {
						aMessage = "Error occured while Invalidating Villages ";
						mv = new ModelAndView("error");
						mv.addObject("message", aMessage);

					}
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return mv;
	}

	/**
	 * For Invalidate Draft Mode To Publish Renamed Villages
	 * 
	 * @author Ripunj on 19-03-2015
	 * @param modifyVillageCmd
	 * @param result
	 * @param model
	 * @param session
	 * @param disturb
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/invalidatedModifyVillagePublish", method = RequestMethod.GET)
	public ModelAndView invalidatedModifyVillagePublish(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, Model model, HttpSession httpsession, @RequestParam(value = "disturb", required = false) String disturb,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = null;
		Session session = null;
		try {
			char operation = 'I';
			int operationCode = 14;
			String destinationVillage = null;
			Integer distinationsubdistrict = null;
			String ordercode = null;
			String orderCode = null;
			String tid = null;
			Integer ulbCode = null;

			int villageCode = modifyVillageCmd.getVillageId();
			// Added by Anchal Todariya on 28-03-2015 for Security audit fix
			modifyVillageCmd.setDraftVillageCode(villageCode);
			httpsession.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(httpsession, response, abstractValidator);
			request.setAttribute("stateCode", stateCode);
			List<VillageDataForm> listVillageDetails = villageService.getVillageDraftDetailsModify(villageCode);
			List<VillageDraft> vilDraftattachmentList = new ArrayList<VillageDraft>();
			if (!listVillageDetails.isEmpty()) {

				
				List<AttachedFilesForm> validFileGovtUpload = null;
				modifyVillageCmd.setListVillageDetails(listVillageDetails);
				httpsession.setAttribute("formbean", modifyVillageCmd);
				EsapiEncoder.encode(listVillageDetails);
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getGovtOrderConfiguration(stateCode, operationCode, operation);
				String message = hMap.get("message");
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				if (govtOrderConfig == null) {
					mv = new ModelAndView("success");
					mv.addObject("msgid", message);
					return mv;
				}
				VillageForm villageForm = (VillageForm) httpsession.getAttribute("formbean");
				if (govtOrderConfig != null) {

					mv = new ModelAndView("modifyDraftVillagechange");
					mv.addObject("govtOrderConfig", govtOrderConfig);
					setAttachmentDetails(modifyVillageCmd);
					Integer draftVillageCode = listVillageDetails.get(0).getVillageCode();
					attachmentList = new ArrayList<Attachment>();
					if (villageCode > 0)
						vilDraftattachmentList = govtOrderService.getAttachmentsbyOrderCodeForDraftVil(villageCode);
					if (("N").equalsIgnoreCase(listVillageDetails.get(0).getIsExistGovtOrder())) {
						if (vilDraftattachmentList != null && vilDraftattachmentList.get(0).getGovt_order_file_name() != null) {
							validFileGovtUpload = new ArrayList<AttachedFilesForm>();
							AttachedFilesForm filesForm = new AttachedFilesForm();
							filesForm.setFileLocation(vilDraftattachmentList.get(0).getGovt_order_file_location());
							filesForm.setFileType(vilDraftattachmentList.get(0).getGovt_order_file_content_type());
							filesForm.setFileName(vilDraftattachmentList.get(0).getGovt_order_file_name());
							filesForm.setFileSize(vilDraftattachmentList.get(0).getGovt_order_file_size());
							filesForm.setFileTimestampName(vilDraftattachmentList.get(0).getGovt_order_file_timestamp());
							validFileGovtUpload.add(filesForm);
						}
					}
					// GovernmentOrderForm govtOrderForm = (GovernmentOrderForm)
					// httpsession.getAttribute("govtOrderForm");
					List<VillageDataForm> listVillages = new ArrayList<VillageDataForm>();
					listVillages = villageForm.getListVillageDetails();
					Iterator<VillageDataForm> itr = listVillages.iterator();
					while (itr.hasNext()) {

						VillageDataForm element = (VillageDataForm) itr.next();
						Date orderDate = element.getOrderDatecr();
						Date effectiveDate = element.getOrdereffectiveDatecr();
						Date gazPubDate = element.getGazPubDatecr();

						ordercode = villageService.invalidateVillageDAOModify(element.getInvalidateVillageList(), userId.intValue(), element.getOrderNocr(), orderDate, effectiveDate, element.getGovtOrder(), gazPubDate, destinationVillage,
								distinationsubdistrict, session, ulbCode, element.getOrderCode(), listVillageDetails.get(0).getIsExistGovtOrder(), 'P', element.getVillageCode());
					}
					villageService.deleteDraftVillageModify(draftVillageCode, request, httpsession);
					int Transactionid = 0;
					String[] ldata = ordercode.split(",");
					orderCode = ldata[0];
					tid = ldata[1];
					Transactionid = Integer.parseInt(tid);
					int ocode = Integer.parseInt(orderCode);
					session = sessionFactory.openSession();
					Transaction tx = session.beginTransaction();
					if ("N".equals(listVillageDetails.get(0).getIsExistGovtOrder())) {
						GovernmentOrder govtOrder = new GovernmentOrder();
						govtOrder.setOrderDate(villageForm.getOrderDate());
						govtOrder.setEffectiveDate(villageForm.getEffectiveDate());
						govtOrder.setGazPubDate(villageForm.getGazPubDate());
						govtOrder.setCreatedon(new Date());
						govtOrder.setDescription("LGD DETAILS");
						govtOrder.setIssuedBy("GOVERNOR");
						govtOrder.setLastupdated(new Date());
						govtOrder.setLevel("U");
						govtOrder.setOrderNo(villageForm.getOrderNo());
						govtOrder.setStatus('A');
						govtOrder.setOrderCode(ocode);
						convertLocalbodyService.saveDataInAttachment(govtOrder, null, validFileGovtUpload, session);
					}

					tx.commit();
					EmailSmsThread est = new EmailSmsThread(Transactionid, 'V', emailService);
					est.start();

					mv = new ModelAndView("success");
					mv.addObject("msgid", "Invalidate Draft Village Published Successfully");
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mv;
	}

	@RequestMapping(value = "/deleteDraftModifyVillage", method = RequestMethod.GET)
	public ModelAndView deleteDraftModifyVillage(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, BindingResult result, Model model, HttpSession httpsession, @RequestParam(value = "disturb", required = false) String disturb,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = null;

		try {
			int villageCode = modifyVillageCmd.getVillageId();
			request.setAttribute("stateCode", stateCode);
			// Added by Anchal Todariya on 28-03-2015 for Security audit fix
			modifyVillageCmd.setDraftVillageCode(villageCode);
			httpsession.setAttribute("formbean", modifyVillageCmd);
			CommonUtil.CheckForParent(httpsession, response, abstractValidator);
			// List<VillageDataForm> listVillageDetails =
			// villageService.getVillageDraftDetailsModify(villageCode);
			villageService.deleteDraftVillageModify(villageCode, request, httpsession);
			mv = new ModelAndView("success");
			mv.addObject("msgid", "Draft Village Deleted Successfully");

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}
	

	@RequestMapping(value = "/unMappedPolygon", method = RequestMethod.GET)
	public ModelAndView unMappedPolygon(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		setGlobalParams(session);
		ModelAndView mav = new ModelAndView("unMappedPolygon");
		ModelAndView mavHome = new ModelAndView("home");
		try {
			Boolean isUnMappedPolygonPresent=villageService.checkUnMappedPolygonByState(stateCode);
			if(isUnMappedPolygonPresent.booleanValue()==false){
				model.addAttribute("publishMessage", "There is no un-mapped polygon present.");
				return mavHome;
			} else {
				List<District> unMappedPolygonDistrict=villageService.getUnMappedPolygonDistrictByState(stateCode);
				model.addAttribute("unMappedPolygonDistrict",unMappedPolygonDistrict);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
		}
	
	/**
	 * The {@code errorHandler} returns error path and saved required stack trace. 
	 * @param request
	 * @param e
	 * @return
	 */
	private String errorHandler(HttpServletRequest request, Exception e){
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		return expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
	}
	
	
	
	@RequestMapping(value = "/createHabitation", method = RequestMethod.GET)
	public ModelAndView createHabitation(@ModelAttribute("habitationForm") HabitationForm habitationForm, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav =null;
		try {
			//String sessionToken =  request.getSession().getAttribute("OWASP_CSRFTOKEN").toString();
			mav=setHabitationProperites(mav,habitationForm,stateCode);
			
		   } catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	private ModelAndView setHabitationProperites(ModelAndView mav,HabitationForm habitationForm,Integer stateCode)throws Exception{
		if (stateCode == null) {
			return mav = new ModelAndView("redirect:login.htm");
		}
		
		HabitationConfiguration habitationConfiguration=villageService.getStateWiseHabitationConfiguration(stateCode);
	    if(habitationConfiguration==null){
	    	mav = new ModelAndView("success");
			mav.addObject("msgid", "kindly configure the level of habitation using 'Configure Habitations' from avilable in 'Configure' section");
			return mav;
	    }
	    String parentType=habitationConfiguration.getParentType();
		 mav = new ModelAndView("CREATE_HABITATION");
	    if((CommanConstant.HABITATION_PARENT_TYPE_GRAMPANCHAYAT.toString()).equals(parentType)){
	    	mav.addObject("localBodyTypeList", localBodyService.buildLBTypeList(stateCode));
	    	habitationForm.setParentTypeName("Garam Panchayat");
	    }else{
	    	mav.addObject("districtList", districtService.getDistrictList(stateCode));
	    	habitationForm.setParentTypeName("Village");
	    }
	    
	    
	    habitationForm.setParentType(parentType);
		mav.addObject("slc", stateCode);
		return mav;
	}
	
	
	@RequestMapping(value = "/buildHabitation", method = RequestMethod.POST)
	public ModelAndView buildHabitation(HttpServletRequest request,Model model,@ModelAttribute("habitationForm") HabitationForm habitationForm,  BindingResult binding) {
		ModelAndView mav =null;
		try {
			
			habitationValidator.validate(habitationForm, binding);
			if(binding.hasErrors()){
				mav=setHabitationProperites(mav,habitationForm,stateCode);
				return mav;
			}
			habitationForm.setCreatedby(userId);
			habitationForm.setCreatedon(new Date());
			habitationForm.setLastupdatedby(userId);
			habitationForm.setLastupdated(new Date());
			habitationForm.setSlc(stateCode);
			villageService.saveHabitation(habitationForm);
			mav = new ModelAndView("success");
			mav.addObject("msgid", "Habitation created Sucessfully");
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/habitationConfiguration", method = RequestMethod.GET)
	public ModelAndView habitationConfiguration(
			@ModelAttribute("habitationconfiguration") HabitationConfiguration habitationConfiguration,
			HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("HABITATION_CONFIGURATION");
		boolean Isinsert = true;
		try {
			HabitationConfiguration existshabitationconfiguration = villageService
					.getStateWiseHabitationConfiguration(stateCode);
			if (existshabitationconfiguration != null) {
				Isinsert = false;
				habitationConfiguration.setId(existshabitationconfiguration.getId());
				habitationConfiguration.setParentType(existshabitationconfiguration.getParentType());

			}
			mav.addObject("Isinsert", Isinsert);
			mav.addObject("IsDisabled", villageService.validateStateWiseHabitation(stateCode));
			mav.addObject("statNameEng", stateDao.getStateNameEnglish(stateCode));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}

	@RequestMapping(value = "/habitationConfiguration", method = RequestMethod.POST)
	public ModelAndView habitationConfiguration(
			@ModelAttribute("habitationconfiguration") HabitationConfiguration habitationConfiguration,
			BindingResult result, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("HABITATION_CONFIGURATION");

		try {
			Integer id = habitationConfiguration.getId();
			Date curDate = new Date();
			habitationConfiguration.setSlc(stateCode);
			habitationConfiguration.setIsactive(true);
			if (id == null) {
				habitationConfiguration.setCreatedon(curDate);
				habitationConfiguration.setCreatedby(userId);
			}
			habitationConfiguration.setLastupdated(curDate);
			habitationConfiguration.setLastupdatedby(userId);
			if (habitationConfiguration != null) {
				mav.addObject("msg", "data saved successfully");
			}
			villageService.saveHabitationConfiguration(habitationConfiguration);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}
	
	/* added by Monty Shiv on 20/04/2017 for Habitation use case */
	@RequestMapping(value = "/modifyHabitation", method = RequestMethod.GET)
	public ModelAndView modifyHabitation(@ModelAttribute("habitation") HabitationForm habitation, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		String parantType="";
		try {
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			HabitationConfiguration habitationConfiguration=villageService.getStateWiseHabitationConfiguration(stateCode);
			if(habitationConfiguration==null){
		    	mav = new ModelAndView("success");
				mav.addObject("msgid", "kindly configure the level of habitation using 'Configure Habitations' from avilable in 'Configure' section");
				return mav;
		    }
			mav = new ModelAndView("modifyHabitation");
			mav.addObject("slc", stateCode);
			parantType=habitationConfiguration.getParentType();
			mav.addObject("parantType",parantType );
			if((CommanConstant.HABITATION_PARENT_TYPE_VILLAGE.toString()).equals(parantType)){
				List<District> districtList = new ArrayList<District>();
				districtList = districtService.getDistrictList(stateCode);
				mav.addObject("districtList", districtList);
			}
			else{
				List<LocalBodyEntityDetails> zilaPanchyats=new ArrayList<LocalBodyEntityDetails>();
				mav.addObject("localBodyTypeList", localBodyService.buildLBTypeList(stateCode));
				if(districtCode!=null && districtCode>0){
					zilaPanchyats=localBodyService.getDistrictPanchayatListForDistrictUser(1, districtCode, null, null);
				}
				else{
					zilaPanchyats=localBodyService.getDistrictPanchayatList(1, stateCode, null, null);
				}
				mav.addObject("zilaPanchyats", zilaPanchyats);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/getHabitationDetails",method=RequestMethod.POST)
	public ModelAndView getHabitationDetails(@ModelAttribute("habitation") Habitation habitation,BindingResult bindingResult,Model model,HttpServletRequest request, HttpSession httpSession){
		ModelAndView mav = null;
		try{
			mav = new ModelAndView("updateHabitaion");
			Habitation habitationData= villageService.getHabitationDetails(habitation.getHabitationCode(),habitation.getHabitationVersion());
			model.addAttribute("habitationForm", habitationData);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value = "/updateHabitation", method = RequestMethod.POST)
	public ModelAndView updateHabitation(@ModelAttribute("habitationForm") HabitationForm habitation, BindingResult result, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			
			mav = new ModelAndView("modifyHabitation");
			boolean checksave = false;
			//villageValidator.validateHabitationForm(habitation, result, parent_type);
			/*if (result.hasErrors()) {
				return mav;
			} else {*/
			Habitation oldHabitation= villageService.getHabitationDetails(habitation.getHabitationCode(),habitation.getHabitationVersion());
			if(!oldHabitation.getHabitationNameEnglish().equalsIgnoreCase(habitation.getHabitationNameEnglish())){
				Habitation habitationDet = new Habitation();
				habitationDet.setCreatedby(userId);
				habitationDet.setLastupdatedby(userId);
				habitationDet.setCreatedon(new Date());
				habitationDet.setLastupdated(new Date());
				habitationDet.setEffectiveDate(new Date());
				habitationDet.setIsactive(true);
				habitationDet.setHabitationVersion(habitation.getHabitationVersion()+1);
				habitationDet.setFlagcode(90);
				habitationDet.setSlc(stateCode);
				habitationDet.setTransactionId(null);
				habitationDet.setHabitationNameEnglish(habitation.getHabitationNameEnglish());
				habitationDet.setHabitationNameLocal(habitation.getHabitationNameLocal());
				habitationDet.setParentType(habitation.getParentType().charAt(0));
				habitationDet.setParentCode(habitation.getParentCode());
				checksave = villageService.updateHabitation(habitationDet);
				oldHabitation.setIsactive(false);
			}
			else{
				oldHabitation.setHabitationNameLocal(habitation.getHabitationNameLocal());
			}
			checksave=villageService.updateHabitation(oldHabitation);
				
				if (checksave == true) {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Habitation Creation Successfully!");
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Habitation is Not Creation Successfully!");
				}
			/*}*/
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value = "/modifyVillageChangeEffectiveDate", method = RequestMethod.GET)
	public ModelAndView modifyVillageChangeEffectiveDate(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, Model model, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("CHANGE_EFFECTIVE_DATE_OF_VILLAGE");
		try {
			model.addAttribute("villageCode",modifyVillageCmd.getVillageId());
			model.addAttribute("curDate",new Date());
			
			//model.addAttribute("villageDetail",villageService.getVillageDetailsModify(modifyVillageCmd.getVillageId()));
				} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/getEffectiveDate", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getEffectiveDate(@RequestBody GetEntityEffectiveDate getEntityEffectiveDate) {
		return villageService.getEntityEffeactiveDate(getEntityEffectiveDate.getEntityCode(),getEntityEffectiveDate.getEntityType());
	}
	
	
	@RequestMapping(value = "/getEffectiveDatebyVersion", method = RequestMethod.POST)
	public @ResponseBody List<GetEntityEffectiveDate> getEffectiveDatebyVersion(@RequestBody GetEntityEffectiveDate getEntityEffectiveDate) {
		return villageService.getEntityEffeactiveDateByVersion(getEntityEffectiveDate);
	}
	
	@RequestMapping(value = "/saveEffectiveDate", headers="Accept=application/json", method = RequestMethod.POST)
	public @ResponseBody Response saveEffectiveDate(@RequestBody EffectiveDateList getEntityEffectiveDateList,HttpServletRequest request) {
		HttpSession httpsession= request.getSession();
		setGlobalParams(httpsession);
		return villageService.saveEffectiveDateEntity(getEntityEffectiveDateList,userId.intValue());
	}
	
	
		
	
	
	
	
	@RequestMapping(value = "/modifyVillageStatus", method = RequestMethod.GET)
	public ModelAndView modifyVillageStatus(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd, Model model, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView mav = null;
		String id = request.getParameter("id");
		int villageCode = (modifyVillageCmd.getVillageId() == null) ? Integer.parseInt(id) : modifyVillageCmd.getVillageId();
		List<String> villageStatusOBJ = new ArrayList<>();
		villageStatusOBJ.add("I");
		villageStatusOBJ.add("R");
		villageStatusOBJ.add("U");
      try {
    	  mav = new ModelAndView("modify_village_status");
    	  modifyVillageCmd.setListVillageDetails(villageService.getVillageDetailsModify(villageCode));
    	  if(villageService.getVillageDetailsModify(villageCode) != null && !villageService.getVillageDetailsModify(villageCode).isEmpty()){
    		  mav.addObject("village_details_seletced",villageService.getVillageDetailsModify(villageCode).get(0).getVillageStatus());
    	  }
    	  mav.addObject("village_details",villageStatusOBJ);
      } catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/saveModifyVillageStatus", method = RequestMethod.POST)
	public ModelAndView saveModifyVillageStatus(@ModelAttribute("modifyVillageCmd") VillageForm modifyVillageCmd,
			Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mav = null;
		boolean saveFlag = false;
		Integer villageCode=null;
		boolean formError = false;
		
		/*if((modifyVillageCmd.getVillageStatus() != null && !modifyVillageCmd.getVillageStatus().isEmpty()) && 
				modifyVillageCmd.getVillageStatus().equalsIgnoreCase("select")){
			formError = true;
		}*/
		try {
			mav = new ModelAndView("modify_village_status");
			if(!formError){
				if(request.getParameter("villageCodeVal") != null && !request.getParameter("villageCodeVal").isEmpty()){
					villageCode = Integer.valueOf(request.getParameter("villageCodeVal"));
				}
				saveFlag = villageService.saveVillageStatus(villageCode, modifyVillageCmd.getVillageStatus(), userId);
				if (saveFlag) {
					mav.setViewName("success");
					mav.addObject("msgid", "Village Status Updated Successfully....");
				}
			
		/*else{
				
				 mav.setViewName("success");
				 mav.addObject("msgid", "Faild to save the Information, Kindly  select  new village status and try again....");
				
			}
			*/
			}
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

}
		


