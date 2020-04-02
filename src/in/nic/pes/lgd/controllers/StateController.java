package in.nic.pes.lgd.controllers;
import java.util.Date;
//import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.controllers.ManageRestructuredLBController.EffectiveDateList;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.ConfigurationBlockVillageMapping;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.FreezeUnfreezeStateConfigEntity;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.Headquarters;
import in.nic.pes.lgd.bean.LgdDataConfirmation;
import in.nic.pes.lgd.bean.LocalbodyPRIDistrictFreeze;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.bean.ParentwiseChildDetials;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.constant.StateConstant;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.draft.service.DraftUtilService;
import in.nic.pes.lgd.forms.DistrictDataForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.forms.StateDataForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.DistrictFreezeService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.GovtOrderTemplateService;
import in.nic.pes.lgd.service.ReportService;
import in.nic.pes.lgd.service.ShiftService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.validator.CommonValidatorImpl;
import in.nic.pes.lgd.validator.NodalOfficerValidator;
import in.nic.pes.lgd.validator.StateValidator;
import in.nic.pes.lgd.validator.VillageValidator;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

@Controller
public class StateController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(StateController.class);
	int Itr = 0;

	@Autowired
	StateService stateService;

	@Autowired
	FileUploadUtility fileUploadUtility;

	@Autowired
	GovtOrderTemplateService govtOrderTemplateService;

	@Autowired
	DistrictService districtService;

	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	ShiftService shiftService;

	@Autowired
	private SubDistrictService subdistrictService;

	@Autowired
	VillageService villageService;

	@Autowired
	GovernmentOrderService governmentOrderService;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	StateValidator stateValidator;

	@Autowired
	VillageValidator villageValidator;

	@Autowired
	ConfigGovtOrderService configGovtOrderService;

	@Autowired
	private CommonValidatorImpl commonValidator;

	@Autowired
	private NodalOfficerValidator nodalOfficerValidator;

	@Autowired
	private DraftUtilService draftUtilService;

	@Autowired
	private DraftUtils draftUtils;

	@Autowired
	private CommonService commonService;

	@Autowired
	private DistrictFreezeService districtFreezeService;
	
	@Autowired
	private ReportService reportService;

	List<StateDataForm> liststateDetails = new ArrayList<StateDataForm>();

	List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();

	List<Attachment> attachmentList = new ArrayList<Attachment>();

	List<Headquarters> headquartersDetails = new ArrayList<Headquarters>();

	private Integer stateCode;

	private Integer districtCode;

	private Long userId;
	
	

	private static final Long NODAL_OFFICER_FILE_ID = 4L;
	
	
	private static final Long FREEZE_UPLOAD_FILE_ID = 9L;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.getBindingResult();
        binder.validate();
	}

	private void setGlobalParams(HttpSession session) {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
	}

	@RequestMapping(value = "/add_Anothersatate", method = RequestMethod.GET)
	public String addAnotherSD(HttpSession httpSession, @ModelAttribute("state") StateForm sdForm, Model model,
			HttpServletRequest request) {
		try {
			Itr = 0;

			StateForm subDistrictForm = (StateForm) httpSession.getAttribute("lstStateForm");
			String temp = subDistrictForm.getSTATENAMEENGLISH().replace("PART", "").replace("FULL", "");
			String[] temp1 = subDistrictForm.getSTATENAMEENGLISH().split(",");

			List<State> listState = null;
			listState = new ArrayList<State>();
			listState = stateService.getDistrict("from State where stateCode IN (" + temp + ") and isactive=true");
			for (int i = 0; i < listState.size(); i++) {
				listState.get(i).setStateNameEnglish(listState.get(i).getStateNameEnglish() + " ("
						+ (temp1[i].contains("FULL") ? "FULL)" : "PART)"));
				listState.get(i).setAliasEnglish(
						listState.get(i).getStateCode() + (temp1[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																											// holding
																											// sub
																											// district
																											// code
																											// in
																											// alias
			}
			model.addAttribute("listState", listState);
			model.addAttribute("state", subDistrictForm);
			return "addstate";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
	}

	@RequestMapping(value = "/addNewState", method = RequestMethod.POST)
	public ModelAndView addNewDistrict(HttpSession httpSession, @ModelAttribute("state") StateForm stateForm,
			BindingResult bindingResult, SessionStatus status, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		// ModelAndView mav1=null;
		try {
			stateValidator.validateCreateState(stateForm, bindingResult);
			if (bindingResult.hasErrors()) {
				mav = new ModelAndView("addstate");
				return mav;
			}

			httpSession.setAttribute("formbean", stateForm);
			mav = new ModelAndView("redirect:govtOrderCommon.htm");

			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}

	@RequestMapping(value = "/cancelST", method = RequestMethod.GET)
	public String cancel(HttpSession httpSession, HttpServletRequest request,
			@ModelAttribute("newsubdistrictform") SubDistrictForm sdForm, Model model) {
		try {
			httpSession.removeAttribute("lstStateForm");
			httpSession.removeAttribute("lstDistritForm");
			httpSession.removeAttribute("lstSubdistritForm");
			httpSession.removeAttribute("lstVillForm");
			httpSession.removeAttribute("modifydistrictForm");
			httpSession.removeAttribute("modifySubdistrictForm");
			httpSession.removeAttribute("modifyVillForm");
			httpSession.removeAttribute("lstShiftdistritForm");
			httpSession.removeAttribute("lstShiftSubdistritForm");
			httpSession.removeAttribute("lstShiftvillageForm");
			/*
			 * httpSession.removeAttribute("modifyVillForm");
			 */httpSession.removeAttribute("contributedSDs");

			Itr = 0;
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return "redirect:home.htm";
	}

	// Modify State Change Government Order
	@RequestMapping(value = "/draftChangeState", method = RequestMethod.POST)
	public ModelAndView draftModifyState(@ModelAttribute("modifyStateCmd") StateForm modifyStateCmd,
			BindingResult result, SessionStatus status, HttpServletRequest request, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		try {

			// ////////code for government order
			// checking///////////////////////////////////

			int operationCode = 43;
			char operation = 'M';
			Map<String, String> mapGovOrderConfig = governmentOrderService.getGovtOrderConfiguration(stateCode,
					operationCode, operation);
			Map<String, String> mapMapConfig = governmentOrderService.getMapConfiguration(stateCode, operationCode,
					'S');

			String govtOrderConfig = mapGovOrderConfig.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = mapMapConfig.get("mapUpload");// value=true,false

			// ///////code for government order
			// checking///////////////////////////////////
			session.setAttribute("formbean", modifyStateCmd);
			modifyStateCmd = stateValidator.validateChange(modifyStateCmd, result);
			if (result.hasErrors()) {

				if (govtOrderConfig != null && mapConfig != null) {
					result.getErrorCount();
					result.getAllErrors();

					mv = new ModelAndView("modifyStateChange");
					mv.addObject("modifyDistrictCmd", modifyStateCmd);

				}

				List<StateDataForm> listStateDetails = stateService.getStateDetailsModify(stateCode);
				mv.addObject("govtOrderConfig", govtOrderConfig);
				model.addAttribute("listdistrictDetails", listStateDetails);
				// model.addAttribute("disturb", disturb);
				modifyStateCmd.setListStateDetails(listStateDetails);
				mv.addObject("modifyStateCmd", modifyStateCmd);

				return mv;
			} else {
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
				return mv;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@RequestMapping(value = "/modifyDistrcit", method = RequestMethod.GET)
	public ModelAndView modiftDistrict(HttpSession httpSession,
			@ModelAttribute("modifyDistrictCmd") DistrictForm districtForm, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("reorganize_districtModifyForState");
		try {
			Itr = 1;
			// int temp = 0;

			String districtCode = null;

			districtCode = (String) httpSession.getAttribute("DistrictCodeModify");
			int subdistritCode = Integer.parseInt(districtCode.replace("PART", "").replace("FULL", ""));
			List<DistrictDataForm> listSubdistrictDetails = districtService.getDistrictDetailsModify(subdistritCode);

			model.addAttribute("size", listSubdistrictDetails.size());
			model.addAttribute("listVillageDetails", listSubdistrictDetails);
			districtForm.setListDistrictDetails(listSubdistrictDetails);
			mv.addObject("modifySubDistrictCmd", districtForm);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@RequestMapping(value = "/modifyStateChangeAction", method = RequestMethod.POST)
	public ModelAndView modifyDistrictChange(@ModelAttribute("stateForm") StateForm stateForm, BindingResult result,
			SessionStatus status, Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb) throws Exception {
		ModelAndView mv = null;
		// String viewDistrictHistory = null;

		// int stateVersion = 0;
		boolean saveSuccess = false;
		AddAttachmentBean addAttachmentBean = null;
		try {
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();

			StateForm stForm = (StateForm) session.getAttribute("formbean");
			stForm.setStateCode(stateCode);
			stForm.setUserId(userId.intValue());
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

			if (session.getAttribute("addAttachmentBean") != null) {
				addAttachmentBean = (AddAttachmentBean) session.getAttribute("addAttachmentBean");
				// String validateAttachment =
				attachmentHandler.validateAttachment(addAttachmentBean);
			}
			/*
			 * ======================================Attached File Saving Part
			 * ==========================================
			 */

			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler
					.getMetaDataListOfFiles(addAttachmentBean);
			List<StateDataForm> listStateDetails = new ArrayList<StateDataForm>();
			listStateDetails = stForm.getListStateDetails();
			//
			mv = new ModelAndView("view_statedetail");
			if (listStateDetails.size() > 0) {
				listStateDetails.get(0).setOrderNocr(govtOrderForm.getOrderNo());
				// listStateDetails.get(0).setOrderCodecr(govtOrderForm.getOrderCode());
				listStateDetails.get(0).setOrderDatecr(govtOrderForm.getOrderDate());
				listStateDetails.get(0).setOrdereffectiveDatecr(govtOrderForm.getEffectiveDate());
				listStateDetails.get(0).setGazPubDatecr(govtOrderForm.getGazPubDate());
				stateCode = listStateDetails.get(0).getStateCode();

				stForm.setListStateDetails(listStateDetails);
				if (metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {
					saveSuccess = stateService.modifyStateInfoNew(stForm, metaInfoOfToBeAttachedFileList, request);
					if (saveSuccess == true) {
						// String saveAttachment =
						attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
						mv = new ModelAndView("redirect:viewStateDetailforModify.htm?id=" + stateCode + "&type=S");
					}
					if (!saveSuccess) {
						mv = new ModelAndView("error");
						mv.addObject("message", "Faild to save the Information, Kindly try again");

					}

					// saveSuccess = stateService.save(stateForm);

				} else {

					mv = new ModelAndView("error");
					mv.addObject("message", "Faild to save the Information, Kindly try again");

				}

			} else {
				mv = new ModelAndView("error");
				mv.addObject("message", "Faild to save the Information, Kindly try again");
			}
			/*
			 * else if (govtOrderForm.getGovtOrderConfig().equals(
			 * "govtOrderGenerate")) { saveSuccess =
			 * districtService.changeDistrictforTemplate( districtForm,
			 * govtOrderForm, request);
			 * 
			 * }
			 */

			// listStateDetails.get(0).setOrdereffectiveDatecr(govtOrderForm.getOrderDate1());

			/*
			 * stateForm.setListStateDetails(listStateDetails);
			 * 
			 * mv.addObject("modifyStateCmd", stateForm); return mv;
			 */
			//
			/*
			 * Iterator<StateDataForm> itr = listdistrict.iterator(); while
			 * (itr.hasNext()) { StateDataForm element = (StateDataForm)
			 * itr.next(); stateCode = element.getStateCode(); stateVersion =
			 * element.getStateVersion(); }
			 */

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv; // changed by kirandeep and Sushish shakya 15/10/2014
		}

		return mv;

	}

	// modify State Controller
	@RequestMapping(value = "/modifyStateAction", method = RequestMethod.POST)
	public String modifyState(@ModelAttribute("modifyStateCmd") StateForm modifyStateCmd, BindingResult result,
			SessionStatus status, Model model, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb, HttpSession httpSession) {
		try {
			// ModelAndView mv = new ModelAndView();
			// System.out.println("update----------------------------");
			String viewStatedetail = null;
			ModelAndView mav = new ModelAndView("modify_state");
			stateValidator.validate(modifyStateCmd, result);
			if (result.hasErrors()) {
				mav.addObject("modifyStateCmd", modifyStateCmd);
				return "modify_state";
			}

			// String aMessage="State Detail Modified Successfully";

			// stateService.modifyStateInfo(modifyStateCmd, request,);
			modifyStateCmd.setStateCode(stateCode);
			modifyStateCmd.setUserId(userId.intValue());
			stateService.modifyStateInfo(modifyStateCmd, request);

			int stateCode = 0;
			int stateVersion = 0;
			List<StateDataForm> listState = new ArrayList<StateDataForm>();
			listState = modifyStateCmd.getListStateDetails();
			Iterator<StateDataForm> itr = listState.iterator();
			while (itr.hasNext()) {
				StateDataForm element = itr.next();
				stateCode = element.getStateCode();
				stateVersion = element.getStateVersion();
			}
			if (disturb.equals("true")) {
				viewStatedetail = "redirect:viewResolveEntitiesinDisturbedState.htm?resolved=" + stateCode + ","
						+ stateVersion;
			} else {
				viewStatedetail = "redirect:viewStateDetailforModify.htm?id=" + stateCode + "&type=S";
			}
			return viewStatedetail;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
	}

	@RequestMapping(value = "/modifyStatechangebyId")
	public ModelAndView modifyState(@ModelAttribute("modifyStateCmd") StateForm modifyStateCmd, Model model,
			HttpSession session, HttpServletRequest request) {

		int operationCode = 43;
		ModelAndView mv = null;
		Integer stateCode = modifyStateCmd.getStateId();

		try {
			List<StateDataForm> listStateDetails = stateService.getStateDetailsModify(stateCode);
			Map<String, String> govConfigMap = governmentOrderService.getGovtOrderConfiguration(stateCode,
					operationCode, 'M');
			String message = govConfigMap.get("message");
			String govtOrderConfig = govConfigMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			model.addAttribute("size", listStateDetails.size());
			if (govtOrderConfig != null) {

				mv = new ModelAndView("modifyStateChange");
				mv.addObject("govtOrderConfig", govtOrderConfig);
				model.addAttribute("listdistrictDetails", listStateDetails);
				modifyStateCmd.setListStateDetails(listStateDetails);
				mv.addObject("modifyDistrictCmd", modifyStateCmd);

			} else {
				mv = new ModelAndView("success");
				mv.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	// modify State
	@RequestMapping(value = "/modifyState", method = RequestMethod.GET)
	public ModelAndView modifyState(@ModelAttribute("modifyStateCmd") StateForm modifyStateCmd, Model model,
			@RequestParam("id") Integer stateCode, @RequestParam(value = "disturb", required = false) String disturb,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("modify_state");
		try {
			List<StateDataForm> liststateDetails = stateService.getStateDetailsModify(stateCode);

			model.addAttribute("size", liststateDetails.size());
			model.addAttribute("liststateDetails", liststateDetails);
			model.addAttribute("disturb", disturb);
			modifyStateCmd.setListStateDetails(liststateDetails);
			mv.addObject("modify_state", modifyStateCmd);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_subdistrict_Modify_forState", method = RequestMethod.POST)
	public String modifyVillage(HttpSession httpSession, @ModelAttribute("addVillage") VillageForm modifyVillageCmd,
			BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {
		Itr = 1;
		// ModelAndView mav = null;
		try {
			List<VillageForm> listVillModify = new ArrayList<VillageForm>();
			villageValidator.validate(modifyVillageCmd, result);
			/*
			 * if(result.hasErrors()) { //mav=new
			 * ModelAndView("reorganize_subdistrict_Modify_Village"); }
			 */
			// else{
			if (httpSession.getAttribute("modifyVillForm") != null) {
				List<VillageForm> villForm = new ArrayList<VillageForm>();
				villForm = (List<VillageForm>) httpSession.getAttribute("modifyVillForm");
				for (int i = 0; i < villForm.size(); i++) {
					listVillModify.add(villForm.get(i));
				}
			}
			listVillModify.add(modifyVillageCmd);
			httpSession.setAttribute("modifyVillForm", listVillModify);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			// return mv;
		}
		return "add_state.htm?villagemodify";

	}

	// modified District

	@RequestMapping(value = "/reorganize_subdistrict_Modify_forState", method = RequestMethod.GET)
	public ModelAndView modifyVillageGet(HttpSession httpSession,
			@ModelAttribute("addVillage") VillageForm modifyVillageCmd, Model model, HttpServletRequest request) {
		Itr = 1;
		String villCode = null;
		// int temp = 0;
		ModelAndView mv = new ModelAndView("reorganize_village");

		try {
			villCode = (String) httpSession.getAttribute("villCodeModify");
			int villageCode = Integer.parseInt(villCode.replace("PART", "").replace("FULL", ""));
			List<VillageDataForm> listVillageDetails = villageService.getVillageDetailsModify(villageCode);

			model.addAttribute("size", listVillageDetails.size());
			model.addAttribute("listVillageDetails", listVillageDetails);
			modifyVillageCmd.setListVillageDetails(listVillageDetails);
			mv.addObject("modifyVillageCmd", modifyVillageCmd);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@ModelAttribute("stateSourceList")
	public List<State> populateSourceStateList(HttpServletRequest request) {

		try {
			return stateService.getStateSourceList();
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}

	}

	@RequestMapping(value = "/preview_State", method = RequestMethod.POST)
	public ModelAndView previewSubdistrict(HttpSession httpSession, @ModelAttribute("state") StateForm sForm,
			HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		try {
			sForm.setOperation('C');
			String govtOrderConfig = null;
			govtOrderConfig = sForm.getGovtOrderConfig();
			httpSession.setAttribute("formbean", sForm);
			mav = new ModelAndView("redirect:govtOrderCommon.htm");
			mav.addObject("govtOrderConfig", govtOrderConfig);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/preview_State", method = RequestMethod.GET)
	public String previewSubdistrictGet(HttpSession httpSession, @ModelAttribute("state") StateForm sdForm,
			BindingResult result, HttpServletRequest request, Model model) {
		try

		{
			Itr = 1;
			String sdFullList = null;
			List<Village> villListforFullSD = null;
			List<Subdistrict> SubListforFullSD = null;
			List<District> DisListforFullSD = null;
			// ShiftDistrictForm shiftsistrictForm = new ShiftDistrictForm();
			ShiftSubDistrictForm shiftSubDistrictForm = new ShiftSubDistrictForm();
			StateForm stateForm = (StateForm) httpSession.getAttribute("lstStateForm");
			model.addAttribute("state", stateForm);
			String temp = stateForm.getSTATENAMEENGLISH().replace("PART", "").replace("FULL", "");
			String[] temp1 = stateForm.getSTATENAMEENGLISH().split(",");

			// String
			// temp2=subDistrictForm.getContributedSubDistricts().replace("PART",
			// "").replace("FULL", "");
			// code to get State list----------

			List<State> listState = null;
			listState = new ArrayList<State>();
			listState = stateService.getDistrict("from State where stateCode IN (" + temp + ") and isactive=true");
			for (int i = 0; i < listState.size(); i++) {

				if (temp1[i].contains("FULL")) {
					if (sdFullList != null) {
						sdFullList += listState.get(i).getStateCode() + ",";
					} else {
						sdFullList = listState.get(i).getStateCode() + ",";
					}
				}
				listState.get(i).setStateNameEnglish(listState.get(i).getStateNameEnglish() + " ("
						+ (temp1[i].contains("FULL") ? "FULL)" : "PART)"));
				listState.get(i).setAliasEnglish(
						listState.get(i).getStateCode() + (temp1[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																											// holding
																											// sub
																											// district
																											// code
																											// in
																											// alias
			}

			// code to get District list----------

			temp = "";
			List<District> listdistrit = new ArrayList<District>();
			List<DistrictForm> dL = new ArrayList<DistrictForm>();

			if (httpSession.getAttribute("lstDistritForm") != null) {
				dL = (List<DistrictForm>) httpSession.getAttribute("lstDistritForm");

				if (dL.get(dL.size() - 1).getDistrictNameEnglish() != null) {
					String contriSD[] = dL.get(dL.size() - 1).getDistrictNameEnglish().split(",");
					for (int i = 0; i < contriSD.length; i++) {
						if (i == 0 && contriSD[i].contains("PART")) {
							temp = contriSD[i].replace("PART", "") + ",";
						}
						if (contriSD[i].contains("PART")) {
							temp += contriSD[i].replace("PART", "") + ",";
						}
					}
				}

				String subDistrict = dL.get(dL.size() - 1).getDistrictNameEnglish();
				if (subDistrict != null) {
					if (temp != null) {
						temp = subDistrict.replace("PART", "").replace("FULL", "");
					} /*
						 * else { temp = subDistrict.replace("PART",
						 * "").replace("FULL", ""); }
						 */
				}
			} else {
				StateForm districtF = new StateForm();
				districtF = (StateForm) httpSession.getAttribute("lstStateForm");
				if (districtF.getDistrictNameEnglish() != null) {
					if (districtF.getDistrictNameEnglish().replace("PART", "").replace("FULL", "") != null) {
						temp = districtF.getDistrictNameEnglish().replace("PART", "").replace("FULL", "");
					}
				}
			}
			try {
				if (stateForm.getDistrictNameEnglish() != null) {
					String[] temp3 = stateForm.getDistrictNameEnglish().split(",");
					// String[]
					// temp2=sdList.get(sdList.size()-1).getContributedSubDistricts().split(",");
					listdistrit = districtService.getSubDistrictViewList(
							"from District where districtCode IN (" + temp + ") and isactive=true");
					for (int i = 0; i < listdistrit.size(); i++) {
						try {
							listdistrit.get(i).setDistrictNameEnglish(listdistrit.get(i).getDistrictNameEnglish() + " ("
									+ (temp3[i].contains("FULL") ? "FULL)" : "PART)"));
						} catch (Exception e) {
							// TODO: handle exception
							log.debug("Exception" + e);
						}
						try {
							listdistrit.get(i).setAliasEnglish(listdistrit.get(i).getDistrictCode()
									+ (temp3[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																						// holding
																						// sub
																						// district
																						// code
																						// in
																						// alias
						} catch (Exception e) {
							// TODO: handle exception
							log.debug("Exception" + e);
						}

					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.debug("Exception" + e);
			}
			if (httpSession.getAttribute("lstDistritForm") != null) {
				for (int k = 0; k < dL.size(); k++) {
					District district = null;
					district = new District();
					district.setDistrictNameEnglish(dL.get(k).getDistrictNameEnglish() + " (New)");
					listdistrit.add(district);
				}
			}

			// code to get district modify list----------
			if (httpSession.getAttribute("modifydistrictForm") != null) {
				List<DistrictForm> sd = new ArrayList<DistrictForm>();

				sd = (List<DistrictForm>) httpSession.getAttribute("modifydistrictForm");
				// List<DistrictDataForm> listVillageData = new
				// ArrayList<DistrictDataForm>();
				// DistrictForm districtForm = new DistrictForm();
				// List<DistrictDataForm> districtDataForm = new
				// ArrayList<DistrictDataForm>();
				// districtDataForm = districtForm.getListDistrictDetails();
				for (int k = 0; k < sd.size(); k++) {
					District vill = null;
					vill = new District();
					vill.setDistrictNameEnglish(
							sd.get(k).getListDistrictDetails().get(0).getDistrictNameEnglish() + " (Modified)");
					listdistrit.add(vill);
				}
			}

			// code to get Subdistrict list----------
			temp = null;
			List<Subdistrict> listSubdistrit = new ArrayList<Subdistrict>();
			List<SubDistrictForm> sdList = new ArrayList<SubDistrictForm>();
			sdList = (List<SubDistrictForm>) httpSession.getAttribute("lstSubdistritForm");

			if (httpSession.getAttribute("lstSubdistritForm") != null) {
				if (sdList.get(sdList.size() - 1).getContributedSubDistricts() != null) {
					String contriSD[] = sdList.get(sdList.size() - 1).getContributedSubDistricts().split(",");
					for (int i = 0; i < contriSD.length; i++) {
						if (i == 0 && contriSD[i].contains("PART")) {
							temp = contriSD[i].replace("PART", "") + ",";
						}
						if (contriSD[i].contains("PART")) {
							temp += contriSD[i].replace("PART", "") + ",";
						}
					}
				}
				String subDistritNames = sdList.get(sdList.size() - 1).getContributedSubDistricts();
				if (subDistritNames != null) {
					if (temp != null) {
						temp = subDistritNames.replace("PART", "").replace("FULL", "");
					} else
						temp = subDistritNames.replace("PART", "").replace("FULL", "");
				}
			} else {
				StateForm districtF = new StateForm();
				districtF = (StateForm) httpSession.getAttribute("lstStateForm");
				if (districtF.getContributedSubDistricts() != null) {

					temp = districtF.getContributedSubDistricts().replace("PART", "").replace("FULL", "");
				}
			}
			if (stateForm.getContributedSubDistricts() != null) {
				String[] temp3 = stateForm.getContributedSubDistricts().split(",");
				listSubdistrit = subdistrictService.getSubDistrictViewList(
						"from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");
				for (int i = 0; i < listSubdistrit.size(); i++) {
					listSubdistrit.get(i).setSubdistrictNameEnglish(listSubdistrit.get(i).getSubdistrictNameEnglish()
							+ " (" + (temp3[i].contains("FULL") ? "FULL)" : "PART)"));
					listSubdistrit.get(i).setAliasEnglish(
							listSubdistrit.get(i).getTlc() + (temp3[i].contains("FULL") ? "FULL" : "PART")); // temporarily
																												// holding
																												// sub
																												// district
																												// code
																												// in
																												// alias
				}
			}
			if (httpSession.getAttribute("lstSubdistritForm") != null) {
				for (int k = 0; k < sdList.size(); k++) {
					Subdistrict subdistrict = null;
					subdistrict = new Subdistrict();
					subdistrict.setSubdistrictNameEnglish(sdList.get(k).getSubdistrictNameEnglish() + " (New)");
					listSubdistrit.add(subdistrict);
				}
			}

			// code to get shift subdistrict
			temp = "";
			List<ShiftSubDistrictForm> sdListSub = new ArrayList<ShiftSubDistrictForm>();

			if (httpSession.getAttribute("lstShiftSubdistritForm") != null) {
				sdListSub = (List<ShiftSubDistrictForm>) httpSession.getAttribute("lstShiftSubdistritForm");

				if (sdListSub.get(sdListSub.size() - 1).getSubdistrictNameEnglish() != null) {
					String contriSD[] = sdListSub.get(sdListSub.size() - 1).getSubdistrictNameEnglish().split(",");
					for (int i = 0; i < contriSD.length; i++) {
						if (i == 0 && contriSD[i].contains("PART")) {
							temp = contriSD[i].replace("PART", "") + ",";
						}
						if (contriSD[i].contains("PART")) {
							temp += contriSD[i].replace("PART", "") + ",";
						}
					}
				}
				String subDistritNames = sdListSub.get(sdListSub.size() - 1).getSubdistrictNameEnglish();
				if (subDistritNames != null) {
					if (temp != null) {
						temp = subDistritNames.replace("PART", "").replace("FULL", "");
					} /*
						 * else temp = subDistritNames.replace("PART",
						 * "").replace("FULL", "");
						 */
				}
			} else {
				if (shiftSubDistrictForm.getSubdistrictNameEnglish() != null) {
					temp = shiftSubDistrictForm.getSubdistrictNameEnglish().replace("PART", "").replace("FULL", "");
					listSubdistrit = subdistrictService.getSubDistrictViewList(
							"from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");
				}
			}
			if (httpSession.getAttribute("lstShiftSubdistritForm") != null) {
				for (int k = 0; k < sdListSub.size(); k++) {
					Subdistrict subdistrict = null;
					subdistrict = new Subdistrict();
					subdistrict.setSubdistrictNameEnglish(sdListSub.get(k).getSubdistrictNameEnglish() + " (Shift)");
					listSubdistrit.add(subdistrict);
				}
			}

			List<Village> listVillage = new ArrayList<Village>();

			// code to get Subdidtrict modify list----------
			if (httpSession.getAttribute("modifySubdistrictForm") != null) {
				List<SubDistrictForm> sd = new ArrayList<SubDistrictForm>();
				sd = null;
				sd = (List<SubDistrictForm>) httpSession.getAttribute("modifySubdistrictForm");
				// List<SubdistrictDataForm> listVillageData = new
				// ArrayList<SubdistrictDataForm>();
				// SubDistrictForm villageForm = new SubDistrictForm();
				// List<SubdistrictDataForm> villDataForm = new
				// ArrayList<SubdistrictDataForm>();
				// villDataForm = villageForm.getListSubdistrictDetails();
				for (int k = 0; k < sd.size(); k++) {
					Subdistrict vill = null;
					vill = new Subdistrict();
					vill.setSubdistrictNameEnglish(
							sd.get(k).getListSubdistrictDetails().get(0).getSubdistrictNameEnglish() + " (Modified)");
					listSubdistrit.add(vill);
				}
			}

			temp = null;

			List<VillageForm> villForm = new ArrayList<VillageForm>();
			villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");

			if (httpSession.getAttribute("lstVillForm") != null) {
				if (villForm.get(villForm.size() - 1).getContributedVillages() != null) {
					String contriVill[] = villForm.get(villForm.size() - 1).getContributedVillages().split(",");
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")) {
							temp = contriVill[i].replace("PART", "") + ",";
						}
						if (contriVill[i].contains("PART")) {
							temp += contriVill[i].replace("PART", "") + ",";
						}
					}
				}
				if (temp != null) {
					temp += villForm.get(villForm.size() - 1).getContributedVillages().replace("PART", "")
							.replace("FULL", "");
				} else
					temp = villForm.get(villForm.size() - 1).getContributedVillages().replace("PART", "")
							.replace("FULL", "");

			} else {
				StateForm districtF = new StateForm();
				districtF = (StateForm) httpSession.getAttribute("lstStateForm");
				if (districtF.getContributedVillages() != null) {
					temp = districtF.getContributedVillages().replace("PART", "").replace("FULL", "");
				}
			}

			listVillage = villageService
					.getVillageViewList("from Village where villageCode IN (" + temp + ") and isactive=true");
			if (httpSession.getAttribute("lstVillForm") != null) {
				for (int k = 0; k < villForm.size(); k++) {
					Village vill = null;
					vill = new Village();
					vill.setVillageNameEnglish(villForm.get(k).getNewVillageNameEnglish() + " (New)");
					listVillage.add(vill);
				}
			}

			// -- code to get modified village list----
			// -- code to get modified village list----
			if (httpSession.getAttribute("modifyVillForm") != null) {
				villForm = null;
				villForm = (List<VillageForm>) httpSession.getAttribute("modifyVillForm");

				for (int k = 0; k < villForm.size(); k++) {
					Village vill = null;
					vill = new Village();
					vill.setVillageNameEnglish(
							villForm.get(k).getListVillageDetails().get(0).getVillageNameEnglish() + " (Modified)");
					listVillage.add(vill);
				}
			}

			temp = null;
			List<ShiftVillageForm> listVill = new ArrayList<ShiftVillageForm>();
			ShiftVillageForm shiftVillageForm = new ShiftVillageForm();

			listVill = (List<ShiftVillageForm>) httpSession.getAttribute("lstShiftvillageForm");
			if (httpSession.getAttribute("lstShiftvillageForm") != null) {
				if (listVill.get(listVill.size() - 1).getVillageNameEnglish() != null) {
					String contriVill[] = listVill.get(listVill.size() - 1).getVillageNameEnglish().split(",");
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")) {
							temp = contriVill[i].replace("PART", "") + ",";
						}
						if (contriVill[i].contains("PART")) {
							temp += contriVill[i].replace("PART", "") + ",";
						}
					}
				}
				if (temp != null) {
					temp += listVill.get(listVill.size() - 1).getVillageNameEnglish().replace("PART", "")
							.replace("FULL", "");
				} else
					temp = listVill.get(listVill.size() - 1).getVillageNameEnglish().replace("PART", "").replace("FULL",
							"");

			} else {
				if (shiftVillageForm.getVillageNameEnglish() != null) {
					temp = shiftVillageForm.getVillageNameEnglish().replace("PART", "").replace("FULL", "");

					listVillage = villageService
							.getVillageViewList("from Village where villageCode IN (" + temp + ") and isactive=true");
				}
			}
			if (httpSession.getAttribute("lstShiftvillageForm") != null) {
				for (int k = 0; k < listVill.size(); k++) {
					Village vill = null;
					vill = new Village();
					vill.setVillageNameEnglish(listVill.get(k).getVillageNameEnglish() + " (Shift)");
					listVillage.add(vill);
				}
			}

			model.addAttribute("listdist", listdistrit);
			model.addAttribute("listSD", listSubdistrit);
			model.addAttribute("listVill", listVillage);
			model.addAttribute("listState", listState);

			if (sdFullList != null) {
				sdFullList = sdFullList.substring(0, sdFullList.length() - 1);
				DisListforFullSD = new ArrayList<District>();
				DisListforFullSD = districtService.getDistrictViewList(
						"from District v where v.state.statePK.stateCode IN (" + sdFullList + ") and isactive=true");
				for (int i = 0; i < DisListforFullSD.size(); i++) {
					listdistrit.add(DisListforFullSD.get(i));
				}
			}
			if (sdFullList != null) {
				sdFullList = sdFullList.substring(0, sdFullList.length() - 1);
				SubListforFullSD = new ArrayList<Subdistrict>();
				SubListforFullSD = subdistrictService
						.getSubDistrictViewList("from Subdistrict v where v.district.districtPK.districtCode IN ("
								+ sdFullList + ") and isactive=true");
				for (int i = 0; i < SubListforFullSD.size(); i++) {
					listSubdistrit.add(SubListforFullSD.get(i));
				}
			}

			if (sdFullList != null) {
				sdFullList = sdFullList.substring(0, sdFullList.length() - 1);
				villListforFullSD = new ArrayList<Village>();
				villListforFullSD = villageService
						.getVillageViewList("from Village v where v.subdistrict.subdistrictPK.subdistrictCode IN ("
								+ sdFullList + ") and isactive=true");
				for (int i = 0; i < villListforFullSD.size(); i++) {
					listVillage.add(villListforFullSD.get(i));
				}
			}

			httpSession.setAttribute("contributeSts", listState);
			httpSession.setAttribute("contributedDs", listdistrit);
			httpSession.setAttribute("contributedSDs", listSubdistrit);
			httpSession.setAttribute("contributedVills", listVillage);

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return mav;
		}
		// TODO: handle exception

		return "preview_State";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganizeDistrictForState", method = RequestMethod.GET)
	public ModelAndView reorganizeDistrict(HttpSession httpSession,
			@ModelAttribute("districtReorganize") DistrictForm newsubdistrictform, Model model,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("reorganizedistrict");
		try {
			Itr = 1;
			String temp = null;
			if (httpSession.getAttribute("lstDistritForm") != null) {
				List<DistrictForm> subdistritForm = new ArrayList<DistrictForm>();
				subdistritForm = (List<DistrictForm>) httpSession.getAttribute("lstDistritForm");
				String contriVill[] = subdistritForm.get(subdistritForm.size() - 1).getDistrictNameEnglish().split(",");
				if (contriVill.length > 0) {
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")) {
							temp = contriVill[i].replace("PART", "") + ",";
						}
						if (contriVill[i].contains("PART")) {
							temp += contriVill[i].replace("PART", "") + ",";
						}
					}
				}
				if (temp != null) {
					temp += subdistritForm.get(subdistritForm.size() - 1).getDistrictNameEnglish().replace("PART", "")
							.replace("FULL", "");
				} else
					temp = subdistritForm.get(subdistritForm.size() - 1).getDistrictNameEnglish().replace("PART", "")
							.replace("FULL", "");
			} else {
				try {
					StateForm districtForm = (StateForm) httpSession.getAttribute("lstStateForm");
					temp = districtForm.getDistrictNameEnglish().replace("PART", "").replace("FULL", "");
				} catch (Exception e) {
					// TODO: handle exception
					log.debug("Exception" + e);
				}
			}

			List<District> listsub = new ArrayList<District>();
			listsub = districtService
					.getSubDistrictViewList("from District where districtCode IN (" + temp + ") and isactive=true");
			model.addAttribute("listDist", listsub);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganizeDistrictForState", method = RequestMethod.POST)
	public String reorganizeDistrictForState(HttpSession httpSession, HttpServletRequest request,
			@ModelAttribute("districtReorganize") DistrictForm districtForm, Model model) {
		List<DistrictForm> listDist = new ArrayList<DistrictForm>();
		try {
			if (httpSession.getAttribute("lstDistritForm") != null) {
				List<DistrictForm> distritForm = new ArrayList<DistrictForm>();
				distritForm = (List<DistrictForm>) httpSession.getAttribute("lstDistritForm");
				for (int i = 0; i < distritForm.size(); i++) {
					listDist.add(distritForm.get(i));
				}
			}
			listDist.add(districtForm);
			httpSession.setAttribute("lstDistritForm", listDist);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return mav;
		}
		return "redirect:add_state.htm?modify";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganizeSubDistrictForState", method = RequestMethod.GET)
	public ModelAndView reorganizeDistrictGet(HttpSession httpSession,
			@ModelAttribute("subdistrictform") SubDistrictForm newsubdistrictform, Model model,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("reorganize_Subdistrict_State");
		try {
			Itr = 1;
			String temp = null;
			if (httpSession.getAttribute("lstSubdistritForm") != null) {
				List<SubDistrictForm> subdistritForm = new ArrayList<SubDistrictForm>();
				subdistritForm = (List<SubDistrictForm>) httpSession.getAttribute("lstSubdistritForm");
				String contriVill[] = subdistritForm.get(subdistritForm.size() - 1).getContributedSubDistricts()
						.split(",");
				if (contriVill.length > 0) {
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")) {
							temp = contriVill[i].replace("PART", "") + ",";
						}
						if (contriVill[i].contains("PART")) {
							temp += contriVill[i].replace("PART", "") + ",";
						}
					}
				}
				if (temp != null) {
					temp += subdistritForm.get(subdistritForm.size() - 1).getSubDistrictList().replace("PART", "")
							.replace("FULL", "");
				} else
					temp = subdistritForm.get(subdistritForm.size() - 1).getSubDistrictList().replace("PART", "")
							.replace("FULL", "");
			} else {
				try {
					StateForm districtForm = (StateForm) httpSession.getAttribute("lstStateForm");
					temp = districtForm.getContributedSubDistricts().replace("PART", "").replace("FULL", "");
				} catch (Exception e) {
					// TODO: handle exception
					log.debug("Exception" + e);
				}
			}

			List<Subdistrict> listsub = new ArrayList<Subdistrict>();
			listsub = subdistrictService.getSubDistrictViewList(
					"from Subdistrict where subdistrictCode IN (" + temp + ") and isactive=true");
			model.addAttribute("listSD", listsub);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganizeSubDistrictForState", method = RequestMethod.POST)
	public String reorganizeSubDistrictNewVill(HttpSession httpSession, HttpServletRequest request,
			@ModelAttribute("newsubdistrictform") SubDistrictForm newsubdistrictform, Model model) {
		List<SubDistrictForm> listVill = new ArrayList<SubDistrictForm>();
		try {
			if (httpSession.getAttribute("lstSubdistritForm") != null) {
				List<SubDistrictForm> subdistritForm = new ArrayList<SubDistrictForm>();
				subdistritForm = (List<SubDistrictForm>) httpSession.getAttribute("lstSubdistritForm");
				for (int i = 0; i < subdistritForm.size(); i++) {
					listVill.add(subdistritForm.get(i));
				}
			}

			listVill.add(newsubdistrictform);

			httpSession.setAttribute("lstSubdistritForm", listVill);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return mav;
		}

		return "redirect:add_state.htm?createsubdistrict";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_state_village", method = RequestMethod.POST)
	public String reorganizeSubDistrictNewVill(HttpSession httpSession, HttpServletRequest request,
			@ModelAttribute("addVillage") VillageForm addVillageNew, Model model) {
		Itr = 1;
		List<VillageForm> listVill = new ArrayList<VillageForm>();
		try {

			if (httpSession.getAttribute("lstVillForm") != null) {
				List<VillageForm> villForm = new ArrayList<VillageForm>();
				villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");
				for (int i = 0; i < villForm.size(); i++) {
					listVill.add(villForm.get(i));
				}
			}
			listVill.add(addVillageNew);
			httpSession.setAttribute("lstVillForm", listVill);

			if (addVillageNew.getCoveredLandRegionByULB().equals("Final")) {
				return "redirect:add_state.htm?village";
			} else
				return "redirect:add_state.htm?village";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_state_village", method = RequestMethod.GET)
	public String reorganizeSubDistrictNewVillGet(HttpSession httpSession,
			@ModelAttribute("addVillage") VillageForm addVillageNew, Model model, HttpServletRequest request) {
		try {
			Itr = 1;
			String temp = null;
			if (httpSession.getAttribute("lstVillForm") != null) {
				List<VillageForm> villForm = new ArrayList<VillageForm>();
				villForm = (List<VillageForm>) httpSession.getAttribute("lstVillForm");
				String contriVill[] = villForm.get(villForm.size() - 1).getContributedVillages().split(",");
				if (contriVill.length > 0) {
					for (int i = 0; i < contriVill.length; i++) {
						if (i == 0 && contriVill[i].contains("PART")) {
							temp = contriVill[i].replace("PART", "") + ",";
						}
						if (contriVill[i].contains("PART")) {
							temp += contriVill[i].replace("PART", "") + ",";
						}
					}
				}
				if (temp != null) {
					temp += villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
				} else
					temp = villForm.get(villForm.size() - 1).getVillageList().replace("PART", "").replace("FULL", "");
			} else {
				StateForm districtForm = (StateForm) httpSession.getAttribute("lstStateForm");
				temp = districtForm.getContributedVillages().replace("PART", "").replace("FULL", "");
			}
			List<Village> listVill = new ArrayList<Village>();
			listVill = subdistrictService.getVillageDetailforReorganize(temp);
			model.addAttribute("listVill", listVill);
			return "reorganize_state_village";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return redirectPath;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/savestate", method = RequestMethod.POST)
	public ModelAndView saveState(HttpSession httpSession, @ModelAttribute("state") StateForm sdForm,
			BindingResult bindingResult, HttpServletRequest request, Model model,
			@RequestParam(value = "fileName", required = false) String fileName) throws Exception {
		ModelAndView mav = null;
		// boolean persistanceTest = false;
		// int statecode = 0;
		// String result = null;

		try {

			StateForm stateForm = new StateForm();
			stateForm = (StateForm) httpSession.getAttribute("formbean");

			GovernmentOrderForm govtOrderForm = new GovernmentOrderForm();
			govtOrderForm = (GovernmentOrderForm) httpSession.getAttribute("govtOrderForm");

			List<AttachedFilesForm> validFileMap = null;
			if (httpSession.getAttribute("validFileMap") != null) {
				validFileMap = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
			}

			mav = stateService.createState(stateForm, govtOrderForm, userId.intValue(), validFileMap, request,
					bindingResult, httpSession);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	public void setAttachmentDetails(StateForm stateform, HttpServletRequest request) {
		try {
			AttachmentMaster attachmentList = governmentOrderService.getUploadFileConfigurationDetails(1);

			stateform.setAllowedFileType(attachmentList.getFileType());
			stateform.setAllowedIndividualFileSize(attachmentList.getIndividualFileSize());
			stateform.setAllowedNoOfAttachment(attachmentList.getFileLimit());
			stateform.setAllowedTotalFileSize(attachmentList.getTotalFileSize());
			stateform.setUploadLocation(attachmentList.getFileLocation());
			stateform.setRequiredTitle(attachmentList.getRequireTitle());
			stateform.setUniqueTitle(attachmentList.getUniqueTitle());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return mv;
		}

	}

	@RequestMapping(value = "/add_state", method = RequestMethod.GET)
	public ModelAndView showform(@ModelAttribute("state") StateForm stateForm, Model model, HttpSession httpSession,
			HttpServletRequest request) {
		ModelAndView mav = null;
		try {

			if (stateCode == null || stateCode == 0) {
				Map<String, String> hMap = configGovtOrderService.checkMapAndGovtOrderConfigurationforCentral(1);
				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				String message = hMap.get("message");
				if (govtOrderConfig != null && mapConfig == null) {
					mav = new ModelAndView("addstate");
					model.addAttribute("govtOrderConfig", govtOrderConfig);
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
				}
				return mav;
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "You are not authorised for this action, kindly login as center user");
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shift_SubDistrict", method = RequestMethod.POST)
	public String showShiftDistrict(HttpSession httpSession, HttpServletRequest request,
			@ModelAttribute("shift_SubDistrict") ShiftSubDistrictForm districtform, Model model) {
		List<ShiftSubDistrictForm> listSD = new ArrayList<ShiftSubDistrictForm>();
		try {
			if (httpSession.getAttribute("lstShiftSubdistritForm") != null) {
				List<ShiftSubDistrictForm> distritForm = new ArrayList<ShiftSubDistrictForm>();
				distritForm = (List<ShiftSubDistrictForm>) httpSession.getAttribute("lstShiftSubdistritForm");
				for (int i = 0; i < distritForm.size(); i++) {
					listSD.add(distritForm.get(i));
				}
			}
			listSD.add(districtform);
			httpSession.setAttribute("lstShiftSubdistritForm", listSD);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return mav;
		}
		return "redirect:add_state.htm";
	}

	@RequestMapping(value = "/shift_district", method = RequestMethod.GET)
	public ModelAndView showShiftDistrictform(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("shiftdistrictState");
		try {
			mav.addObject("shiftdistrict", new ShiftDistrictForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ShiftDistrict", method = RequestMethod.POST)
	public String showShiftDistrictform(HttpSession httpSession, HttpServletRequest request,
			@ModelAttribute("shiftdistrict") ShiftDistrictForm districtform, Model model) {
		List<ShiftDistrictForm> listDist = new ArrayList<ShiftDistrictForm>();
		try {
			if (httpSession.getAttribute("lstShiftdistritForm") != null) {
				List<ShiftDistrictForm> distritForm = new ArrayList<ShiftDistrictForm>();
				distritForm = (List<ShiftDistrictForm>) httpSession.getAttribute("lstShiftdistritForm");
				for (int i = 0; i < distritForm.size(); i++) {
					listDist.add(distritForm.get(i));
				}
			}
			listDist.add(districtform);
			httpSession.setAttribute("lstShiftdistritForm", listDist);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return "redirect:add_state.htm";
	}

	@RequestMapping(value = "/shift_SubDistrict", method = RequestMethod.GET)
	public ModelAndView showShiftSubDistrictform(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("shiftSubdistrictdistrictState");
		try {
			mav.addObject("shift_SubDistrict", new ShiftSubDistrictForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shift_village", method = RequestMethod.POST)
	public String showShiftVillageform(HttpSession httpSession, HttpServletRequest request,
			@ModelAttribute("shiftvillageSubDistrict") ShiftVillageForm shiftVillageForm, Model model) {
		List<ShiftVillageForm> listDist = new ArrayList<ShiftVillageForm>();
		try {
			if (httpSession.getAttribute("lstShiftvillageForm") != null) {
				List<ShiftVillageForm> distritForm = new ArrayList<ShiftVillageForm>();
				distritForm = (List<ShiftVillageForm>) httpSession.getAttribute("lstShiftvillageForm");
				for (int i = 0; i < distritForm.size(); i++) {
					listDist.add(distritForm.get(i));
				}
			}
			listDist.add(shiftVillageForm);
			httpSession.setAttribute("lstShiftvillageForm", listDist);
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}
		return "redirect:add_state.htm";
	}

	@RequestMapping(value = "/shift_village", method = RequestMethod.GET)
	public ModelAndView showShiftVillageSubDistrict(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("shiftvillageState");
		try {
			mav.addObject("shift_village", new ShiftVillageForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/uploadGovOrder", method = RequestMethod.GET)
	public ModelAndView uploadGovOrder(@ModelAttribute("uploadGovCmd") StateDataForm uploadGovCmd, Model model,
			@RequestParam("id") Integer orderCode, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("upload_govOrder");
		try {
			uploadGovCmd.setOrderCode(orderCode);
			List<GovernmentOrder> lst = stateService.getGovermentOrderDetail(orderCode);
			uploadGovCmd.setLst(lst);
			model.addAttribute(uploadGovCmd);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@RequestMapping(value = "/uploadGovOrder", method = RequestMethod.POST)
	public ModelAndView uploadGovOrder(@ModelAttribute("uploadGovCmd") StateForm uploadGovCmd, BindingResult result,
			SessionStatus status, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("modify_state");
		try {
			// int orderCode = uploadGovCmd.getOrderCode();
			// String orderPath = uploadGovCmd.getOrderPath();
			// MultipartFile filename = uploadGovCmd.getFilePath();
			// governmentOrderService.updatePath(orderCode, orderPath, filename,
			// request);
			// ModelAndView mv=new ModelAndView("modifyState");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
		// stateService.ministryUpdate(modifyMinistryCmd, orgCode,
		// session,request);

	}

	/*@RequestMapping(value = "/reorganize_SubDistrict_Modify", method = RequestMethod.GET)
	public ModelAndView viewDistrictDetail(HttpSession httpSession,
			@ModelAttribute("modifySubDistrictCmd") SubDistrictForm subDistrictForm, Model model,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("reorganize_SubdistrictModifyForState");
		try {
			Itr = 1;
			// int temp = 0;

			String SubdistrictCode = null;
			SubdistrictCode = (String) httpSession.getAttribute("SubCodeModify");
			int subdistritCode = Integer.parseInt(SubdistrictCode.replace("PART", "").replace("FULL", ""));
			List<SubdistrictDataForm> listSubdistrictDetails = subdistrictService
					.getSubdistrictDetailsModify(subdistritCode);

			model.addAttribute("size", listSubdistrictDetails.size());
			model.addAttribute("listVillageDetails", listSubdistrictDetails);
			subDistrictForm.setListSubdistrictDetails(listSubdistrictDetails);
			mv.addObject("modifySubDistrictCmd", subDistrictForm);
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}*/

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modifyDistrcit", method = RequestMethod.POST)
	public ModelAndView viewDistrictDetailList(HttpSession httpSession,
			@ModelAttribute("modifyDistrictCmd") DistrictForm districtForm, BindingResult result, SessionStatus status,
			Model model, HttpServletRequest request) {

		Itr = 1;
		ModelAndView mav = null;
		try {
			List<DistrictForm> listdistrictModify = new ArrayList<DistrictForm>();
			// villageValidator.validate(modifySubDistrictCmd, result);
			/*
			 * if(result.hasErrors()) { //mav=new
			 * ModelAndView("reorganize_subdistrict_Modify_Village"); }
			 */
			// else{
			if (httpSession.getAttribute("modifydistrictForm") != null) {
				List<DistrictForm> SubdistrictForm = new ArrayList<DistrictForm>();
				SubdistrictForm = (List<DistrictForm>) httpSession.getAttribute("modifydistrictForm");
				for (int i = 0; i < SubdistrictForm.size(); i++) {
					listdistrictModify.add(SubdistrictForm.get(i));
				}
			}
			listdistrictModify.add(districtForm);
			httpSession.setAttribute("modifydistrictForm", listdistrictModify);

			mav = new ModelAndView("redirect:add_state.htm?districtmodify");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reorganize_SubDistrict_Modify", method = RequestMethod.POST)
	public String viewDistrictDetailList(HttpSession httpSession,
			@ModelAttribute("modifySubDistrictCmd") SubDistrictForm newsubdistrictform, BindingResult result,
			SessionStatus status, Model model, HttpServletRequest request) {

		Itr = 1;
		// ModelAndView mav = null;
		try {
			List<SubDistrictForm> listSubdistrictModify = new ArrayList<SubDistrictForm>();
			// villageValidator.validate(modifySubDistrictCmd, result);
			/*
			 * if(result.hasErrors()) { //mav=new
			 * ModelAndView("reorganize_subdistrict_Modify_Village"); }
			 */
			// else{
			if (httpSession.getAttribute("modifySubdistrictForm") != null) {
				List<SubDistrictForm> SubdistrictForm = new ArrayList<SubDistrictForm>();
				SubdistrictForm = (List<SubDistrictForm>) httpSession.getAttribute("modifySubdistrictForm");
				for (int i = 0; i < SubdistrictForm.size(); i++) {
					listSubdistrictModify.add(SubdistrictForm.get(i));
				}
			}
			listSubdistrictModify.add(newsubdistrictform);
			httpSession.setAttribute("modifySubdistrictForm", listSubdistrictModify);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			// return mv;
		}
		/*
		 * Itr=1; status.setComplete();
		 */
		return "redirect:add_state.htm?subdistrictmodify";
		// }

	}

	// ###################################################### State Correction
	// ##################################################################################################################################

	// --------------------------------- view state GET
	// Method---------------------------------------------------
	@RequestMapping(value = "/viewstate", method = RequestMethod.GET)
	public ModelAndView getStatePage(@ModelAttribute("statebean") StateForm statebean, BindingResult result,
			Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("view_state");
		List<State> stateList = null;
		// List<StateWiseEntityDetails> stateWiseEntityDetails = null;

		try {

			if (stateCode != null) {
				stateList = stateService.getStateSourceList(stateCode);
				model.addAttribute("SEARCH_STATE_RESULTS_KEY", stateList);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			return mav;
		}

		return mav;

	}

	@RequestMapping(value = "/modifyStateCrbyId")
	public ModelAndView modifyStateCorrection(@ModelAttribute("modifyStateCmd") StateForm modifyStateCmd,
			HttpSession httpSession, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("modifyStateCorrection");
		
		try {
			;
			Integer operationCode = Integer.parseInt(StateConstant.STATE_OPEARATION_CODE.toString());
			char operation = StateConstant.STATE_MODIFY_OPERATION.toString().charAt(0);
			liststateDetails = stateService.getStateDetailsModify(stateCode);
			if (liststateDetails != null && !liststateDetails.isEmpty()) {
				StateDataForm stateDataForm = liststateDetails.get(0);
				Integer orderCode = stateDataForm.getOrderCodecr();
				boolean mandatoryFlag = true;
				model.addAttribute("pageWarningEntiesFlag", stateDataForm.isWarningflag());
				Map<String, String> mapGovOrderConfig = governmentOrderService.getGovtOrderConfiguration(stateCode,
						operationCode, operation);
				Map<String, String> mapMapConfig = governmentOrderService.getMapConfiguration(stateCode, operationCode,
						StateConstant.STATE_ENTITY_TYPE.toString().charAt(0));

				String message = mapGovOrderConfig.get("message");
				String message2 = mapMapConfig.get("message");
				String govtOrderConfig = mapGovOrderConfig.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = mapMapConfig.get("mapUpload");// value=true,false

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

				modifyStateCmd.setOrderCode(stateDataForm.getOrderCode());
				modifyStateCmd.setOrderDate(stateDataForm.getOrderDate());
				modifyStateCmd.setOrderNo(stateDataForm.getOrderNo());
				modifyStateCmd.setEffectiveDate(stateDataForm.getEffectiveDate());
				modifyStateCmd.setGazPubDate(stateDataForm.getGazPubDate());
				setAttachmentDetails(modifyStateCmd, request);

				if (orderCode != null)
					attachmentList = governmentOrderService.getAttachmentsbyOrderCode(orderCode);
				else {
					// mandatoryFlag = false;
					attachmentList = new ArrayList<Attachment>();
				}
				mapAttachmentList = governmentOrderService.getMapAttachmentListbyEntity(stateCode, 'S');
				modifyStateCmd.setListStateDetails(liststateDetails);
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				mv.addObject("mapConfig", mapConfig);
				mv.addObject("govtOrderConfig", govtOrderConfig);
				mv.addObject("modifyStateCmd", modifyStateCmd);
				mv.addObject("govtfilecount", attachmentList.size());
				mv.addObject("mapfilecount", mapAttachmentList.size());
				httpSession.setAttribute("mandatoryFlag", mandatoryFlag);
				mv.addObject("mandatoryFlag", mandatoryFlag);

			} else {

				String aMessage = "Sorry Data Not Found For Your Selection ";
				mv = new ModelAndView("success");
				mv.addObject("msgid", aMessage);
				return mv;
			}
			return mv;

		} catch (Exception e) {
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

	}

	@RequestMapping(value = "/modifyStateCrAction", method = RequestMethod.POST)
	public ModelAndView modifyStatecorrection(@ModelAttribute("modifyStateCmd") StateForm modifyStateCmd,
			BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request)
			throws Exception {

		ModelAndView mav = null;

		try {

			List<StateDataForm> listState = modifyStateCmd.getListStateDetails();
			if (!listState.isEmpty()) {
				StateDataForm stateDataForm = listState.get(0);
				Integer statecode = stateDataForm.getStateCode();
				int operationCode = 43;
				char operation = 'M';

				Map<String, String> mapGovOrderConfig = governmentOrderService.getGovtOrderConfiguration(statecode,
						operationCode, operation);
				Map<String, String> mapMapConfig = governmentOrderService.getMapConfiguration(statecode, operationCode,
						'S');
				String govtOrderConfig = mapGovOrderConfig.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = mapMapConfig.get("mapUpload");// value=true,false

				modifyStateCmd.setOrderCode(stateDataForm.getOrderCodecr());
				modifyStateCmd.setOrderDate(stateDataForm.getOrderDatecr());
				modifyStateCmd.setGazPubDate(stateDataForm.getGazPubDatecr());
				modifyStateCmd.setOrdereffectiveDate(stateDataForm.getOrdereffectiveDatecr());
				modifyStateCmd.setOrderNo(stateDataForm.getOrderNocr());

				mav = new ModelAndView("modifyStateCorrection");
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				stateValidator.validateCorrection(modifyStateCmd, result);
				commonValidator.isValidMimeforGovOrderLandRegion(result, request, modifyStateCmd.getAttachFile1());
				commonValidator.isValidMimeforMapLandRegion(result, request, modifyStateCmd.getAttachFile2());
				if (result.hasErrors()) {
					String Cordinate = null;
					if (stateDataForm.getCordinate() != null) {
						if (!stateDataForm.getCordinate().trim().equals("")) {
							Cordinate = stateDataForm.getCordinate();
						}
					}
					stateDataForm.setCordinate(Cordinate);
					listState.set(0, stateDataForm);
					modifyStateCmd.setListStateDetails(listState);
					model.addAttribute("attachmentList", attachmentList);
					model.addAttribute("mapAttachmentList", mapAttachmentList);
					mav.addObject("modifyStateCmd", modifyStateCmd);
					mav.addObject("govtfilecount", attachmentList.size());
					mav.addObject("mapfilecount", mapAttachmentList.size());
					model.addAttribute("listsubdistrictDetails", liststateDetails);
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);
					model.addAttribute("govtOrderConfig", govtOrderConfig);
					model.addAttribute("mapConfig", mapConfig);
					model.addAttribute("pageWarningEntiesFlag", stateDataForm.isWarningflag());

					if (session.getAttribute("mandatoryFlag") != null) {
						mav.addObject("mandatoryFlag", session.getAttribute("mandatoryFlag"));
					}
					return mav;
				}
				if (session.getAttribute("mandatoryFlag") != null) {
					session.removeAttribute("mandatoryFlag");
				}
				List<AttachedFilesForm> validFileGovtUpload = null;
				List<AttachedFilesForm> validFileMap = null;
				String deleteAttachmentId[] = null;
				boolean delflag = false;
				if (govtOrderConfig.equals("govtOrderUpload")) {
					AddAttachmentBean govAttachmentBean = getAttachmentBean(modifyStateCmd, request);
					if (govAttachmentBean != null) {
						validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrder(request,
								govAttachmentBean, modifyStateCmd.getAttachFile1(), result, mav);
						deleteAttachmentId = govAttachmentBean.getDeletedFileID();
					}
					if (mapConfig.equals("true")) {
						AddAttachmentBean mapAttachmentBean = getAttachmentBeanforMap(modifyStateCmd, request);
						if (mapAttachmentBean != null) {
							validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean,
									modifyStateCmd.getAttachFile2(), result, mav);

							String deleteID[] = mapAttachmentBean.getDeletedFileID();
							if (deleteID != null && deleteID.length > 0) {
								delflag = true;
							}
						}
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList,
									validFileMap);
							if (warningFlag) {
								modifyStateCmd.setWarningFlag(!warningFlag);
							}
						}

						stateService.modifyStateCrInfo(modifyStateCmd, request, validFileGovtUpload, validFileMap,
								delflag, deleteAttachmentId, session);
					}

					else {
						stateService.modifyStateCrInfo(modifyStateCmd, request, validFileGovtUpload, null, delflag,
								deleteAttachmentId, session);
					}

				} else if (govtOrderConfig.equals("govtOrderGenerate")) {
					if (mapConfig.equals("true")) {
						AddAttachmentBean mapAttachmentBean = getAttachmentBeanforMap(modifyStateCmd, request);
						if (mapAttachmentBean != null) {
							validFileMap = fileUploadUtility.ValidateAndUploadFileMap2(request, mapAttachmentBean,
									modifyStateCmd.getAttachFile2(), result, mav);

							String deleteID[] = mapAttachmentBean.getDeletedFileID();
							if (deleteID != null && deleteID.length > 0) {
								delflag = true;
							}
						}
						if (validFileMap != null) {
							boolean warningFlag = fileUploadUtility.validWarninngFlagMapUpload(mapAttachmentList,
									validFileMap);
							if (warningFlag) {
								modifyStateCmd.setWarningFlag(!warningFlag);
							}
						}

						stateService.modifyStateCrInfo(modifyStateCmd, request, null, validFileMap, delflag, null,
								session);
					} else {
						stateService.modifyStateCrInfo(modifyStateCmd, request, null, null, delflag, null, session);
					}
				}

				Object warningEntiesFlag = session.getAttribute("reqWarningFlag");
				session.removeAttribute("warningEntiesFlag");
				if (warningEntiesFlag != null && "W".equalsIgnoreCase(warningEntiesFlag.toString())) {
					mav = new ModelAndView("redirect:viewResolveEntitiesinDisturbedStateLR.htm?warningEntiesFlag="
							+ warningEntiesFlag.toString());
				} else {
					mav = new ModelAndView("redirect:viewStateDetailforModify.htm?id=" + statecode + "&type=S");

				}

				return mav;
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "No Record(s) available for corrction.");
				return mav;
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}

	// -----------------------------------------------------------------------------------

	// ---------------- function for get attachement bean for goverment
	// order-------------
	private AddAttachmentBean getAttachmentBean(StateForm stateform, HttpServletRequest request) {

		try {
			List<Attachment> alreadyAttachList = null;
			List<String> allowedMimeTypeList = governmentOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = governmentOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0;
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			Integer orderCode = null;
			List<StateDataForm> liststate = new ArrayList<StateDataForm>();
			liststate = stateform.getListStateDetails();

			if (!liststate.isEmpty()) {
				StateDataForm element = liststate.get(0);
				orderCode = element.getOrderCodecr();

				if (orderCode != null) {

					/*
					 * if (orderCode1 != null) { if
					 * (request.getParameterValues("deletedFileID1") != null) {
					 * String[] deletedFileID1 = request
					 * .getParameterValues("deletedFileID1");
					 * governmentOrderService
					 * .deleteAttachedFiles(deletedFileID1, request, 'O'); }
					 */
					alreadyAttachList = governmentOrderService.getAttachmentsbyOrderCode(orderCode);

					noOFAlreadyAttachedFiles1 = alreadyAttachList.size(); // Already
																			// Attach
																			// Number
																			// Of
																			// File.
					// Already attached file total size.
					Iterator<Attachment> attachmentsIterator1 = alreadyAttachList.iterator();
					while (attachmentsIterator1.hasNext()) {
						Attachment att = attachmentsIterator1.next();
						long fileSize = (att).getFileSize();
						totalSizeOFAlreadyAttachedFiles1 = totalSizeOFAlreadyAttachedFiles1 + fileSize;
					}
				}
			}
			AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(stateform.getAttachFile1());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			// addAttachmentBeanTwo.setFileTitle(stateform.g);
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
																		// Mime
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
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	// ---------------------------------------------------------------------------------

	// ---------------------- function for get attacement bean for
	// map-------------------------
	private AddAttachmentBean getAttachmentBeanforMap(StateForm stateForm, HttpServletRequest request) {
		try {
			List<MapAttachment> alreadyAttachList = null;
			List<String> allowedMimeTypeList = governmentOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = governmentOrderService.getUploadFileConfigurationDetails(2);

			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0;
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;

			if (stateForm.getOrderCode() != null) {
				/*
				 * if (request.getParameterValues("deletedFileID2") != null) {
				 * String[] deletedFileID1 = request
				 * .getParameterValues("deletedFileID2");
				 * governmentOrderService.deleteAttachedFiles(deletedFileID1,
				 * request, 'M'); }
				 */
				alreadyAttachList = governmentOrderService.getMapAttachmentListbyEntity(stateForm.getStateCode(), 'T');

				noOFAlreadyAttachedFiles1 = alreadyAttachList.size(); // Already
																		// Attach
																		// Number
																		// Of
																		// File.
				// Already attached file total size.
				Iterator<MapAttachment> attachmentsIterator1 = alreadyAttachList.iterator();
				while (attachmentsIterator1.hasNext()) {
					MapAttachment att = attachmentsIterator1.next();
					long fileSize = att.getFileSize();
					totalSizeOFAlreadyAttachedFiles1 = totalSizeOFAlreadyAttachedFiles1 + fileSize;
				}
			}

			AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(stateForm.getAttachFile2());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			// addAttachmentBeanTwo.setFileTitle(stateForm.getf);
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
																		// Mime
																		// Type
																		// List.
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID2"));// 10.File
																								// Id
																								// array
																								// to
																								// be
																								// deleted
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName2"));// 11.File
																									// Name
																									// Array
																									// To
																									// Be
																									// Deleted.
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList2"));// 12.Deleted
																										// File
																										// Array.
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
																			// of
																			// Mandatory
																			// file.

			return addAttachmentBeanTwo;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	// ---------------------------------------------------------------------------------------

	// -----------------------------View Details for
	// State-----------------------------------
	@RequestMapping(value = "/viewStateDetailforModify", method = RequestMethod.GET)
	public ModelAndView viewStateDetail(@ModelAttribute("stateForm") StateForm stateForm, Model model,
			@RequestParam("id") Integer stateCode, @RequestParam("type") String type, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view_statedetail");
		try {
			List<StateDataForm> listStateDetails = stateService.getStateDetailsModify(stateCode);
			stateForm.setListStateDetails(listStateDetails);
			model.addAttribute("successMsg", "The state/UT was modified successfully");
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}
	// -----------------------------------------------------------------------------------------

	// ###########################################################################################
	// end of State Correction
	// ###################################################################################

	@RequestMapping(value = "/getAssignNodelOfficer", method = RequestMethod.GET)
	public ModelAndView getAssignNodelOfficer(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView("NODAL_OFFICER");
		try {
			model.addAttribute("nodalOfficerState", new NodalOfficerState());

		} catch (Exception e) {
			log.error("stateController(getAssignNodelOfficer(GET))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}

	@RequestMapping(value = "/saveAssignNodelOfficer", method = RequestMethod.POST)
	public ModelAndView saveAssignNodelOfficer(HttpSession session, HttpServletRequest request,
			@ModelAttribute("nodalOfficerState") NodalOfficerState nodalOfficerState, BindingResult binding) {
		ModelAndView mav = new ModelAndView("NODAL_OFFICER");
		try {
			setGlobalParams(session);
			System.out.println("into save method");
			nodalOfficerState.setUserType(
					session.getAttribute("isUserType") != null ? (Character) session.getAttribute("isUserType") : null);
			System.out.println("isUserType-->" + nodalOfficerState.getUserType());
			nodalOfficerValidator.validate(nodalOfficerState, binding);
			nodalOfficerValidator.isValidMime(binding, request, nodalOfficerState.getFileUpload());
			if (binding.hasErrors()) {
				return mav;
			}
			Character level = districtCode > 0 ? 'D' : 'S';

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println("districtCode-->" + districtCode);
			nodalOfficerState.setCreatedBy(userId.intValue());
			nodalOfficerState.setCreatedOn(timestamp);
			nodalOfficerState.setModifiedBy(userId.intValue());
			nodalOfficerState.setModifiedOn(timestamp);
			nodalOfficerState.setIsactive(Boolean.TRUE);
			nodalOfficerState.setStateCode(stateCode);
			nodalOfficerState.setUserId(userId);
			nodalOfficerState.setLevel(level);
			nodalOfficerState.setDistrictCode(districtCode);
			nodalOfficerState.setFilePath(this.uploadFileToServer(nodalOfficerState.getFileUpload(), NODAL_OFFICER_FILE_ID,'N', request, session));
			stateService.saveNodalOfficerDetail(nodalOfficerState);
			mav = new ModelAndView("redirect:home.htm");
		} catch (Exception e) {
			log.error("stateController(saveAssignNodelOfficer(POST))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}

	@RequestMapping(value = "/editAssignNodelOfficer", method = RequestMethod.GET)
	public ModelAndView editAssignNodelOfficer(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView("EDIT_NODAL_OFFICER");
		try {
			setGlobalParams(session);
			Object[] objectArray = getNodalOfficerDetailsFn(Boolean.TRUE);
			model.addAttribute("nodalOfficerState", (NodalOfficerState) objectArray[2]);

		} catch (Exception e) {
			log.error("stateController(getAssignNodelOfficer(GET))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}

	@RequestMapping(value = "/updateAssignNodelOfficer", method = RequestMethod.POST)
	public ModelAndView updateAssignNodelOfficer(HttpSession session, HttpServletRequest request, Model model,
			@ModelAttribute("nodalOfficerState") NodalOfficerState nodalOfficerState, BindingResult binding) {
		ModelAndView mav = new ModelAndView("EDIT_NODAL_OFFICER");
		try {

			nodalOfficerValidator.validate(nodalOfficerState, binding);
			nodalOfficerValidator.isValidMime(binding, request, nodalOfficerState.getFileUpload());
			Object[] objectArray = getNodalOfficerDetailsFn(Boolean.TRUE);
			NodalOfficerState existNodalOfficerState = (NodalOfficerState) objectArray[2];
			if (binding.hasErrors()) {
				// model.addAttribute("nodalOfficerState",existNodalOfficerState);
				return mav;
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			nodalOfficerState.setCreatedBy(userId.intValue());
			nodalOfficerState.setCreatedOn(timestamp);
			nodalOfficerState.setModifiedBy(userId.intValue());
			nodalOfficerState.setModifiedOn(timestamp);
			nodalOfficerState.setIsactive(Boolean.TRUE);
			nodalOfficerState.setStateCode(stateCode);
			nodalOfficerState.setUserId(userId);
			Character level = districtCode > 0 ? 'D' : 'S';
			nodalOfficerState.setLevel(level);
			nodalOfficerState.setDistrictCode(districtCode);
			nodalOfficerState.setFilePath(this.uploadFileToServer(nodalOfficerState.getFileUpload(), NODAL_OFFICER_FILE_ID,'N', request, session));
			stateService.updateNodalOfficerDetail(nodalOfficerState, existNodalOfficerState);
			mav.setViewName("success");
			mav.addObject("msgid", "Nodal Officer details updated successfully");
		} catch (Exception e) {
			log.error("stateController(saveAssignNodelOfficer(POST))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}

	private String uploadFileToServer(List<CommonsMultipartFile> UploadFiles, Long fileMasterId,Character type,
			HttpServletRequest request, HttpSession httpSession) throws Exception {
		List<CommonsMultipartFile> gazettePublicationUpload = UploadFiles;
		String fileDetails=null;
		if (gazettePublicationUpload != null && !gazettePublicationUpload.isEmpty()) {
			AttachmentMaster master = draftUtilService.getUploadFileConfigurationDetails(fileMasterId);
			AddAttachmentBean attachmentBean = new AddAttachmentBean();
			attachmentBean.setUploadLocation(master.getFileLocation());
			attachmentBean.setCurrentlyUploadFileList(gazettePublicationUpload);
			SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
			String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
			List<AttachedFilesForm> metaInfoList = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
			if (metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment)) {
				String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoList, attachmentBean);
				if ("saveSuccessFully".equals(saveAttachment)) {
					AttachedFilesForm attachedFilesForm = metaInfoList.get(0);
					if(type=='N'){
						fileDetails=attachmentBean.getUploadLocation().replaceAll("\\\\", "/") + "/"+ attachedFilesForm.getFileTimestampName();
					}else if(type=='F'){
						fileDetails=attachedFilesForm.getFileTimestampName();
					}
					

				}
			}
		}
		return fileDetails;
	}

	@RequestMapping(value = "/getLGDDataConfirmation", method = RequestMethod.GET)
	public ModelAndView getLGDDataConfirmation(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView();
		
	   //Test
		//Integer stateCode=35;
		//Integer districtCode=603;
		
		
	 try {
			setGlobalParams(session);
			Character userType = session.getAttribute("isUserType") != null
					? session.getAttribute("isUserType").toString().charAt(0) : null;
				    session.setAttribute("userType", userType);
			        model.addAttribute("userTypeStr", userType);
			        Boolean LDCConfiguration=null;
			       
			        
			        FreezeUnfreezeStateConfigEntity freezeUnfreezeStateConfigEntity=stateService.getConfigurationOfLGDDataConfirmation(stateCode, userType);
					if(freezeUnfreezeStateConfigEntity!=null && freezeUnfreezeStateConfigEntity.getFinalizeAtStateLevel()!=null) {
						LDCConfiguration=freezeUnfreezeStateConfigEntity.getFinalizeAtStateLevel();
					}
			      
			       
			        if(LDCConfiguration==null)
					{
			        mav.setViewName("success");
					mav.addObject("msgid", " State has not been configured by any level ");
					
					}else
					{
						Character status = null;
						String stateSetupType=null;
						List<ParentwiseChildDetials> parentwiseChildDetials =null; 
			    	 if (districtCode > 0)
				     {
				    	 
				    	 if(LDCConfiguration)
					       {
				    		mav.setViewName("success");
					        mav.addObject("msgid", "LGD Data configuration facility at state Level.");	
					        }
					      else 
					      {
					    	  mav = new ModelAndView("LGD_DATA_CONFIRMATION_DISTRICT_USER");
								Object[] objectArray = getNodalOfficerDetailsFn(Boolean.FALSE);
						      
			                    if (objectArray != null) {
								if (objectArray[0] != null && (boolean) objectArray[0] == false) {
									mav.setViewName("success");
									mav.addObject("msgid", "First insert Nodal Officer Details");
									return mav;
								}
								if (objectArray[1] != null && (boolean) objectArray[0] == true && objectArray[3] != null) {
									model.addAttribute("lgdDataConfirmation", (LgdDataConfirmation) objectArray[1]);
									model.addAttribute("isConfirmDataSame", (boolean) objectArray[3]);
								}
								
								
								
								if(userType=='R')
								{
								parentwiseChildDetials =reportService.getParentwiseChildDetils('D', districtCode, 'F',null);
								model.addAttribute("dlc", districtCode);
								model.addAttribute("districtNameEnglish", (String) objectArray[5]);
								model.addAttribute("subdstrictList",parentwiseChildDetials);
								}
								else if(userType=='U')
								{
									
								model.addAttribute("districtListStatus",stateService.freezeUnfreezeLocalBodyEntity(districtCode,'D',userType,userId));
								model.addAttribute("ulbList",stateService.freezeUnfreezeLocalBodyEntity(districtCode,'D',userType,null));
								}
								 
								else if(userType=='P')
								{
					 				stateSetupType =stateService.getStateSetupType(stateCode);
								    model.addAttribute("dlc", districtCode);
									model.addAttribute("districtNameEnglish", (String) objectArray[5]);
									model.addAttribute("stateSetupType",stateSetupType);
									
									
									
									if("DIV".equals(stateSetupType))
									{
									parentwiseChildDetials = stateService.getparentwisecountofBPandGP(districtCode);
									model.addAttribute("priList",parentwiseChildDetials);
									}
									
									else if("DV".equals(stateSetupType))
									{
										
										parentwiseChildDetials = stateService.getparentwisecountofBPandGP(districtCode);
										model.addAttribute("priList",parentwiseChildDetials);
									}
									else if("IV".equals(stateSetupType))
									{
										
										parentwiseChildDetials = stateService.getparentwisecountofBPandGP(districtCode);
										model.addAttribute("priList",parentwiseChildDetials);
									}
									
								}
								else if(userType=='T')
								{
								model.addAttribute("districtListStatusTlb",stateService.freezeUnfreezeLocalBodyEntity(districtCode,'D',userType,userId));
								model.addAttribute("tlbList",stateService.freezeUnfreezeLocalBodyEntity(districtCode,'D',userType,null));
							    }
								
								
								if (objectArray[4] != null) {
									status = (Character) objectArray[4] == 'F' ? 'F' : 'U';
								}
								//Added by sushma singh 28-03-03 show unfreeze status for Revenue
								if(userType=='R' && objectArray[4] == null)
								{
									status = 'U';
								}
								
								model.addAttribute("status", status);
								model.addAttribute("stateStatus", (Character) objectArray[6]);
								mav.addObject("entityName", getEntityNamebyUserType(userType));
								mav = setLocalbodyFreezeAttribute(districtCode, userType, mav);
							} else {
								mav.setViewName("success");
								mav.addObject("msgid", "First insert Nodal Officer Details");
							    return mav;
							} 
					      }
				    
					      
			    }
					     
			else {
				if(LDCConfiguration)
				{
					return new ModelAndView("redirect:getfreezeUnfreezeByState.htm");
				}else {
					
				mav = new ModelAndView("LGD_DATA_CONFIRMATION_DISTRICT_STATE_USER");
				boolean stateFrezzeStatus = false;
				NodalOfficerState nodalOfficerState = commonService.getNodalOfficerDetails(userId);
				if (nodalOfficerState != null && nodalOfficerState.getLgdDataConfirmation() != null
						&& nodalOfficerState.getLgdDataConfirmation().getStatus() !=null 
					&&	nodalOfficerState.getLgdDataConfirmation().getStatus() == 'F') {
					stateFrezzeStatus = true;
				}
                 
			    LgdDataConfirmation lgdDataConfirmation = new LgdDataConfirmation();
				lgdDataConfirmation.setUserId(userId);
				lgdDataConfirmation.setUserOTP(null);
				
				if(userType =='R')
				{
				model.addAttribute("districtFreezeEntityList",stateService.getDistrictwiseFreezeStatus(stateCode, userType));
				}
				
				else if(userType =='U')
				{
				
				lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
				}
				
				else if(userType =='P')
				{
					stateSetupType =stateService.getStateSetupType(stateCode);
					 model.addAttribute("stateSetupType",stateSetupType);
					 
					 if("DIV".equals(stateSetupType))
						{
						 lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
						}
					
					 else if("DV".equals(stateSetupType))
						{
						 lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
						}
					 
					 else if("IV".equals(stateSetupType))
						{
						 lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
						}
					  
					 
				 }
				
				else if(userType =='T')
				{
				
				lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
				}
				
				
				    model.addAttribute("stateFrezzeStatus", stateFrezzeStatus);
					model.addAttribute("lgdDataConfirmation", lgdDataConfirmation);
					mav.addObject("entityName", getEntityNamebyUserType(userType));
					mav.addObject("stateFreezeBtn",stateService.getFreezeStatusbyState(stateCode, userType, 'D'));
				
				
			}
		}
	
  }
} 
		catch (Exception e)
		{
			log.error("stateController(getLGDDataConfirmation(GET))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	private String getEntityNamebyUserType(Character userType)throws Exception{
	String entityName="";	
		if(userType!=null){
			switch (userType){
				case 'P' :
					entityName="PRI Local Bodies";
					break;
				case 'U' :
					entityName="Urban Local Bodies";
					break;
				case 'T' :
					entityName="TLB Local Bodies";
					break;
				case 'R' :
					entityName="Revenue";
					break;	
				case 'B' :
					entityName="Block";
					break;
				case 'E' :
					entityName="Constituency";
					break;		
			
			}
			
		}
		return entityName;
	}

	private ModelAndView setLocalbodyFreezeAttribute(Integer districtCode, Character userType, ModelAndView mav)
			throws Exception {
		if (userType != null && (userType == 'P' || userType == 'T' || userType == 'U')) {
			List<LocalbodyPRIDistrictFreeze> finalList = new ArrayList<LocalbodyPRIDistrictFreeze>();
			List<LocalbodyPRIDistrictFreeze> isDraftList = districtFreezeService
					.draftedVillagesPRIFromDistrict(districtCode, userType.toString());
			if (isDraftList != null && !isDraftList.isEmpty()) {
				for (LocalbodyPRIDistrictFreeze localbodyPRIDistrictFreeze : isDraftList) {
					localbodyPRIDistrictFreeze.setLbStatus("In Draft Mode");
					finalList.add(localbodyPRIDistrictFreeze);
				}
			}
			List<LocalbodyPRIDistrictFreeze> isFreezedList = districtFreezeService
					.freezeUnFreezeDistrictEntityCheckByDistrictId(districtCode, userType.toString());
			if (!isFreezedList.isEmpty()) {
				for (LocalbodyPRIDistrictFreeze localbodyPRIDistrictFreeze : isFreezedList) {
					if (localbodyPRIDistrictFreeze.getLbStatus().equalsIgnoreCase("Status not set")) {
						localbodyPRIDistrictFreeze.setLbStatus("Unfreeze");
					}
					finalList.add(localbodyPRIDistrictFreeze);
				}
			}
			mav.addObject("coverageFreeze", finalList.size()>0 ?false:true);
			mav.addObject("draftedOrFreezedVillages", finalList);
		}else{
			mav.addObject("coverageFreeze", true);
		}
		return mav;
	}

	@RequestMapping(value = "/saveLGDDataConfirmation", method = RequestMethod.POST)
	public ModelAndView saveLGDDataConfirmation(HttpSession session, HttpServletRequest request, Model model,
			@ModelAttribute("lgdDataConfirmation") LgdDataConfirmation lgdDataConfirmation, BindingResult binding) {
		ModelAndView mav = new ModelAndView("LGD_DATA_CONFIRMATION_DISTRICT_USER");
		boolean saveFlag = false;
		try {
			List<CommonsMultipartFile> UploadFiles=lgdDataConfirmation.getUploadFiles();
			nodalOfficerValidator.validateTokenError(binding, lgdDataConfirmation.getUserOTP(), userId);
			Object[] objectArray = getNodalOfficerDetailsFn(Boolean.FALSE);

			if (objectArray[1] != null && (boolean) objectArray[0] == true) {
				LgdDataConfirmation lgdDataConfirmationexist = (LgdDataConfirmation) objectArray[1];
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				lgdDataConfirmation.setUpdatedOn(timestamp);
				lgdDataConfirmationexist.setUpdatedOn(timestamp);
				draftUtils.copyBeanAttributes(lgdDataConfirmation, lgdDataConfirmationexist);
				if (binding.hasErrors()) {
					lgdDataConfirmation.setUserOTP(null);
					if (objectArray != null) {
						if (objectArray[0] != null && (boolean) objectArray[0] == false) {
							mav.setViewName("success");
							mav.addObject("msgid", "First insert Nodal Officer Details");
							return mav;
						}
						if (objectArray[1] != null && (boolean) objectArray[0] == true && objectArray[3] != null) {

							model.addAttribute("isConfirmDataSame", (boolean) objectArray[3]);
						}
						Character status = 'U';
						model.addAttribute("dlc", districtCode);
						model.addAttribute("districtNameEnglish", (String) objectArray[5]);

						if (objectArray[4] != null) {
							status = (Character) objectArray[4] == 'F' ? 'F' : 'U';
						}
						model.addAttribute("status", status);
						return mav;
					}
				}
				
				NodalOfficerState nodalOfficerState = commonService.getNodalOfficerDetails(userId);
				if (nodalOfficerState != null)
				{
				lgdDataConfirmation.setNodalOfficerId(nodalOfficerState.getNodalOfficerId());	
				}
			
			    lgdDataConfirmation.setFileName(this.uploadFileToServer(UploadFiles, FREEZE_UPLOAD_FILE_ID,'F', request, session));
				lgdDataConfirmation.setLgdConfirmationId(null);
				lgdDataConfirmation.setUpdatedOn(timestamp);
				lgdDataConfirmation.setIsactive(Boolean.TRUE);
				lgdDataConfirmation.setStatus('F');
				saveFlag = stateService.saveLGDDataConfirmation(lgdDataConfirmation);

			}

			if (saveFlag) {
				mav.setViewName("success");
				mav.addObject("msgid", "Data has been freezed by District user");
			} else {
				mav.setViewName("success");
				mav.addObject("msgid", "Some problem to save");
			}

		} catch (Exception e) {
			log.error("stateController(saveLGDDataConfirmation(POST))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}

	private Object[] getNodalOfficerDetailsFn(boolean isNodalOfficeDet) throws Exception {
		Character entityType = 'S';
		Integer entityCode = stateCode;
		if (districtCode > 0) {
			entityType = 'D';
			entityCode = districtCode;
		}

		return (stateService.getNodalOfficerDetails(entityCode, entityType, userId, isNodalOfficeDet,stateCode));

	}

	@RequestMapping(value = "/saveDistrictUnfreezebyState", method = RequestMethod.POST)
	public ModelAndView saveDistrictUnfreezebyState(HttpSession session, HttpServletRequest request, Model model,
			@ModelAttribute("lgdDataConfirmation") LgdDataConfirmation lgdDataConfirmation, BindingResult binding) {
		ModelAndView mav = new ModelAndView("LGD_DATA_CONFIRMATION");
		boolean saveFlag = false;
		try {
			nodalOfficerValidator.validateTokenError(binding, lgdDataConfirmation.getUserOTP(), userId);
			Character userType = session.getAttribute("isUserType") != null
					? session.getAttribute("isUserType").toString().charAt(0) : null;
			if (binding.hasErrors()) {
				boolean stateFrezzeStatus = false;
				NodalOfficerState nodalOfficerState = commonService.getNodalOfficerDetails(userId);
				if (nodalOfficerState != null && nodalOfficerState.getLgdDataConfirmation() != null
						&& nodalOfficerState.getLgdDataConfirmation().getStatus() == 'F') {
					stateFrezzeStatus = true;
				}
				model.addAttribute("stateFrezzeStatus", stateFrezzeStatus);
				model.addAttribute("districtFreezeEntityList",
						stateService.getDistrictwiseFreezeStatus(stateCode, userType));
				lgdDataConfirmation.setUserId(userId);
				lgdDataConfirmation.setUserOTP(null);
				mav = new ModelAndView("LGD_DATA_CONFIRMATION_DISTRICT_STATE_USER");
				return mav;
			}
			lgdDataConfirmation.setUserId(userId);
			lgdDataConfirmation.setUserType(userType);
			saveFlag = stateService.saveDistrictUnfreezebyState(lgdDataConfirmation);
			if (saveFlag) {
				mav.setViewName("success");
				mav.addObject("msgid", "District's Level Data Successfully unfreeze by State.");
			} else {
				mav.setViewName("success");
				mav.addObject("msgid", "Some problem to save");
			}
		} catch (Exception e) {
			log.error("stateController(saveLGDDataConfirmation(POST))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}

	@RequestMapping(value = "/saveStateFreezebyState", method = RequestMethod.POST)
	public ModelAndView saveStateFreezebyState(HttpSession session, HttpServletRequest request, Model model,
			@ModelAttribute("lgdDataConfirmation") LgdDataConfirmation lgdDataConfirmation, BindingResult binding) {
		ModelAndView mav = new ModelAndView("LGD_DATA_CONFIRMATION");
		boolean saveFlag = false;
		try {
			//Add By Sushma Singh
			
			List<CommonsMultipartFile> UploadFiles=lgdDataConfirmation.getUploadFiles();
	        Character isUserType = session.getAttribute("isUserType") != null
					? session.getAttribute("isUserType").toString().charAt(0) : null;
			nodalOfficerValidator.validateTokenError(binding, lgdDataConfirmation.getUserOTP(), userId);
			//Add by Sushma Singh
			
			
			Object[] objectArray = getNodalOfficerDetailsFn(Boolean.FALSE);

			if (objectArray[1] != null && (boolean) objectArray[0] == true) {
				LgdDataConfirmation lgdDataConfirmationexist = (LgdDataConfirmation) objectArray[1];
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				lgdDataConfirmation.setUpdatedOn(timestamp);
				lgdDataConfirmationexist.setUpdatedOn(timestamp);
				draftUtils.copyBeanAttributes(lgdDataConfirmation, lgdDataConfirmationexist);
			
			if(binding.hasErrors()){
				
				mav = new ModelAndView("LGD_DATA_CONFIRMATION_DISTRICT_STATE_USER");
				boolean stateFrezzeStatus = false;
				NodalOfficerState nodalOfficerState = commonService.getNodalOfficerDetails(userId);
				if (nodalOfficerState != null && nodalOfficerState.getLgdDataConfirmation() != null
						&& nodalOfficerState.getLgdDataConfirmation().getStatus() == 'F') {
					stateFrezzeStatus = true;
				}

				model.addAttribute("stateFrezzeStatus", stateFrezzeStatus);
				model.addAttribute("districtFreezeEntityList",
						stateService.getDistrictwiseFreezeStatus(stateCode, isUserType));
				
				lgdDataConfirmation.setUserId(userId);
				lgdDataConfirmation.setUserOTP(null);
				model.addAttribute("lgdDataConfirmation", lgdDataConfirmation);
				
				mav.addObject("entityName", getEntityNamebyUserType(isUserType));
			}

			if (stateService.getFreezeStatusbyState(stateCode, isUserType, 'D')) {
				lgdDataConfirmation.setUserId(userId);
				lgdDataConfirmation.setUserType(isUserType);
				lgdDataConfirmation.setStatus('F');
				saveFlag = stateService.saveStateFreezeUnfreezebyState(lgdDataConfirmation);
				
				if (saveFlag) {
					mav.setViewName("success");
					mav.addObject("msgid", "Data Successfully Freeze by State.");
				} else {
					mav.setViewName("success");
					mav.addObject("msgid", "Some problem to save");
				}
			} else {
				mav.setViewName("success");
				mav.addObject("msgid", "District data is not proper to freeze by state ");
			}
			
			NodalOfficerState nodalOfficerState = commonService.getNodalOfficerDetails(userId);
			if (nodalOfficerState != null)
			{
			lgdDataConfirmation.setNodalOfficerId(nodalOfficerState.getNodalOfficerId());	
			}
			
			lgdDataConfirmation.setFileName(this.uploadFileToServer(UploadFiles, FREEZE_UPLOAD_FILE_ID,'F', request, session));
			lgdDataConfirmation.setLgdConfirmationId(null);
			lgdDataConfirmation.setUpdatedOn(timestamp);
			lgdDataConfirmation.setIsactive(Boolean.TRUE);
			lgdDataConfirmation.setStatus('F');
			saveFlag = stateService.saveLGDDataConfirmation(lgdDataConfirmation);

		}
		}
			catch (Exception e) {
			log.error("stateController(saveLGDDataConfirmation(POST))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}

	@RequestMapping(value = "/saveStateUnFreezebyStateNew", method = RequestMethod.POST)
	public ModelAndView saveStateUnFreezebyState(HttpSession session, HttpServletRequest request, Model model,
			@ModelAttribute("lgdDataConfirmation") LgdDataConfirmation lgdDataConfirmation, BindingResult binding) {
		ModelAndView mav = new ModelAndView("LGD_DATA_CONFIRMATION");
		boolean saveFlag = false;
		try {
			Character isUserType = session.getAttribute("isUserType") != null
					? session.getAttribute("isUserType").toString().charAt(0) : null;
			nodalOfficerValidator.validateTokenError(binding, lgdDataConfirmation.getUserOTP(), userId);
			if(binding.hasErrors()){
				mav = new ModelAndView("LGD_DATA_CONFIRMATION_DISTRICT_STATE_USER");
				boolean stateFrezzeStatus = false;
				NodalOfficerState nodalOfficerState = commonService.getNodalOfficerDetails(userId);
				if (nodalOfficerState != null && nodalOfficerState.getLgdDataConfirmation() != null
						&& nodalOfficerState.getLgdDataConfirmation().getStatus() == 'F') {
					stateFrezzeStatus = true;
				}

				model.addAttribute("stateFrezzeStatus", stateFrezzeStatus);
				model.addAttribute("districtFreezeEntityList",
						stateService.getDistrictwiseFreezeStatus(stateCode, isUserType));
				
				lgdDataConfirmation.setUserId(userId);
				lgdDataConfirmation.setUserOTP(null);
				model.addAttribute("lgdDataConfirmation", lgdDataConfirmation);
				
				mav.addObject("entityName", getEntityNamebyUserType(isUserType));
				return mav;
			}
		

			if (stateService.getFreezeStatusbyState(stateCode, isUserType, 'M')) {
				lgdDataConfirmation.setUserId(userId);
				lgdDataConfirmation.setUserType(isUserType);
				lgdDataConfirmation.setStatus('U');
				saveFlag = stateService.saveStateFreezeUnfreezebyState(lgdDataConfirmation);
				if (saveFlag) {
					mav.setViewName("success");
					mav.addObject("msgid", "Data Successfully Freeze by State.");
				} else {
					mav.setViewName("success");
					mav.addObject("msgid", "Some problem to save");
				}
			} else {
				mav.setViewName("success");
				mav.addObject("msgid", "District data has been not unfrozen properly by State ");
			}

		} catch (Exception e) {
			log.error("stateController(saveLGDDataConfirmation(POST))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value = "/callWebService", method = RequestMethod.GET)
	public ModelAndView callWebService(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return new ModelAndView("DESIG_WEB_SERVICE");
	}
	
	@RequestMapping(value = "/callWebService", method = RequestMethod.POST)
	public ModelAndView callWebServicePOST(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView("DESIG_WEB_SERVICE");
		try {
			String url=request.getParameter("weburl");
			String paramString=request.getParameter("paramString");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			
			Scanner scanner = new Scanner(paramString);
			scanner.useDelimiter("&&");
			
			while(scanner.hasNext()){
				String 	 rowvalues = scanner.next();
				String[] designationColumns	= rowvalues.split("\\%%");
				map.add(designationColumns[0],designationColumns[1]);
			}
			
		
			HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, headers);
			
		
			
			ResponseEntity<String> res = restTemplate.postForEntity( url, req , String.class );
			System.out.println(res.getBody());
			model.addAttribute("outputservice",res.getBody());

		} catch (Exception e) {
			e.printStackTrace();
			log.error("stateController(callWebService(POST))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value = "/gisFreezeUnfreezeStatusReport", method = RequestMethod.GET)
	public ModelAndView gisFreezeUnfreezeStatusReport(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView mav = new ModelAndView("GIS_FREEZE_UNFREEZE_STATUS");
		try {
			model.addAttribute("token",stateService.getGisTokenValue());

		} catch (Exception e) {
			log.error("stateController(getAssignNodelOfficer(GET))--->>" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
		@RequestMapping(value = "/getAuditTrailLGDReport", method = RequestMethod.GET)
	public ModelAndView getAuditTrailLGDReport(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model){
		ModelAndView mav = new ModelAndView("AUDIT_TRAIL_LGD_REPORT");
		try {
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
		@RequestMapping(value = "/freezeUnfreezeDistrictwiseULBList")
		public ModelAndView freezeUnfreezeDistrictwiseULBList( HttpServletRequest request, Model model) {
			ModelAndView mav = null;
			try {
			    mav = new ModelAndView("freezeUnfreezeDistrictwiseUlbList");
				String districtCode = request.getParameter("districtCode");
				model.addAttribute("ulbList",stateService.freezeUnfreezeLocalBodyEntity(Integer.parseInt(districtCode),'D','U',null));
				System.out.print(districtCode);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
			}
			
			return mav;
		}

		@RequestMapping(value = "/getStateConfiguration", method = RequestMethod.GET)
		public ModelAndView getStateConfiguration (	@ModelAttribute("freezeUnfreezeStateConfigEntity") FreezeUnfreezeStateConfigEntity freezeUnfreezeStateConfigEntity,HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model){
			ModelAndView mav = null;
		    String saveflag="N";
		    //Integer stateCode=35;
			try {
				  mav= new ModelAndView("Get_State_Configuration");
				 Character isUserType = session.getAttribute("isUserType") != null
							? session.getAttribute("isUserType").toString().charAt(0) : null;
						 
				 mav.addObject("entityName", getEntityNamebyUserType(isUserType));
				
		  freezeUnfreezeStateConfigEntity=stateService.getConfigurationOfLGDDataConfirmation(stateCode, isUserType);
		  if(freezeUnfreezeStateConfigEntity!=null && freezeUnfreezeStateConfigEntity.getFinalizeAtStateLevel()!=null){
			  if(freezeUnfreezeStateConfigEntity.getFinalizeAtStateLevel()){
				   saveflag="S";
			   }else if(!freezeUnfreezeStateConfigEntity.getFinalizeAtStateLevel()){
				   saveflag="B";
			   } 
		  }
		   
			model.addAttribute("freezeUnfreezeStateConfigEntity",freezeUnfreezeStateConfigEntity);
			model.addAttribute("saveflag",saveflag);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
			}
			return mav;
		}
		
		
		@RequestMapping(value = "/saveStateConfiguration", method = RequestMethod.POST)
		public ModelAndView saveStateConfiguration(HttpSession session, HttpServletRequest request, Model model,
				@ModelAttribute("freezeUnfreezeStateConfigEntity") FreezeUnfreezeStateConfigEntity freezeUnfreezeStateConfigEntity , BindingResult binding) {
			ModelAndView mav = new ModelAndView("Get_State_Configuration");
			boolean saveFlag = false;
			boolean dcFlag = false;
			
			Integer id;
			
			try 
			{
				setGlobalParams(session);
				Character userType = session.getAttribute("isUserType") != null
						? session.getAttribute("isUserType").toString().charAt(0) : null;
						freezeUnfreezeStateConfigEntity.setStateCode(stateCode);
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());   
						freezeUnfreezeStateConfigEntity.setUserType(userType);
						id=freezeUnfreezeStateConfigEntity.getId();
						
						LgdDataConfirmation lgdDataConfirmation = null;
						   NodalOfficerState nodalOfficerState = commonService.getNodalOfficerDetails(userId);
							if (nodalOfficerState != null && nodalOfficerState.getLgdDataConfirmation() != null)
							{
								lgdDataConfirmation=nodalOfficerState.getLgdDataConfirmation();
							}
			                
						
						
						if(id==null)
						{   
							freezeUnfreezeStateConfigEntity.setCreatedBy(userId);
							freezeUnfreezeStateConfigEntity.setCreatedOn(timestamp);
							saveFlag =stateService.saveConfigurationLGDUpdation(freezeUnfreezeStateConfigEntity,lgdDataConfirmation);
							mav.setViewName("success");
							if(saveFlag)
							{
								
								mav.addObject("msgid", "Configuration of LGD Data confirmation save successfully. ");
								
							}else{
								mav.addObject("msgid", "Some problem to save data. ");
							}
							
						} 
						else
						{
						   
							freezeUnfreezeStateConfigEntity.setLastUpdatedBy(userId);
							freezeUnfreezeStateConfigEntity.setUpdated(timestamp);
							saveFlag =stateService.saveConfigurationLGDUpdation(freezeUnfreezeStateConfigEntity,lgdDataConfirmation);
							mav.setViewName("success");
							if(saveFlag)
							{
							 mav.addObject("msgid", "Configuration of LGD Data confirmation has been updated successfully.  ");
								
							}else{
								mav.addObject("msgid", "Some problem to update data. ");
							}
							
						}
						model.addAttribute("saveFlag",saveFlag);
						
			}catch (Exception e) {
				log.error("stateController(saveLGDDataConfirmation(POST))--->>" + e);
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
			}
			
			return mav;
	}
		
		
		@RequestMapping(value = "/getfreezeUnfreezeByState", method = RequestMethod.GET)
		public ModelAndView getfreezeUnfreezeByState (HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model){
			 ModelAndView mav = null;
			try {
				
				setGlobalParams(session);
				LgdDataConfirmation lgdDataConfirmation = new LgdDataConfirmation();
				mav=setCommanProperties(mav, session, model,lgdDataConfirmation);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
			}
			return mav;
		}
		
		@RequestMapping(value = "/saveStateFreezebyOnlyAtState", method = RequestMethod.POST)
		public ModelAndView saveStateFreezebyOnlyAtState(HttpSession session, HttpServletRequest request, Model model,
				@ModelAttribute("lgdDataConfirmation") LgdDataConfirmation lgdDataConfirmation, BindingResult binding) {
			ModelAndView mav = new ModelAndView("LGD_DATA_CONFIRMATION");
			boolean saveFlag = false;
			
			try {
				//Added By Sushma Singh 19 september 2019
				
				List<CommonsMultipartFile> UploadFiles=lgdDataConfirmation.getUploadFiles();
		        Character isUserType = session.getAttribute("isUserType") != null
						? session.getAttribute("isUserType").toString().charAt(0) : null;
				
				if(lgdDataConfirmation.getStatus()!=null && lgdDataConfirmation.getStatus()=='F') {
					nodalOfficerValidator.isValidMime(binding, request, lgdDataConfirmation.getUploadFiles());
					nodalOfficerValidator.validateTokenError(binding, lgdDataConfirmation.getUserOTP(), userId);
				}
				
				if(binding.hasErrors()){
					
					mav=setCommanProperties(mav, session, model,lgdDataConfirmation);
					return mav;
				}
				  
				lgdDataConfirmation.setFileName(this.uploadFileToServer(UploadFiles, FREEZE_UPLOAD_FILE_ID,lgdDataConfirmation.getStatus(), request, session));
				 saveFlag=stateService.saveStateFreezeUnfreezebyStateOnly(stateCode, isUserType,lgdDataConfirmation.getStatus(),userId,lgdDataConfirmation.getFileName());
				  if (saveFlag) {
						mav.setViewName("success");
						StringBuilder sb=new StringBuilder("Data Successfully ");
						if(lgdDataConfirmation.getStatus()!=null && lgdDataConfirmation.getStatus()=='F') {
							sb.append(" Freezed");
						}else {
							sb.append(" Unfreezed");
						}
						sb.append(" by State.");
						mav.addObject("msgid", sb.toString());
					} else {
						mav.setViewName("success");
						mav.addObject("msgid", "Some problem to save");
					}

			}
				catch (Exception e) {
				log.error("stateController(saveLGDDataConfirmation(POST))--->>" + e);
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
			}
			return mav;
		}
	
		
		private ModelAndView setCommanProperties(ModelAndView mav,HttpSession session,Model model,LgdDataConfirmation lgdDataConfirmation) throws Exception {
			Character userType = session.getAttribute("isUserType") != null
					? session.getAttribute("isUserType").toString().charAt(0) : null;
				    session.setAttribute("userType", userType);
			        model.addAttribute("userTypeStr", userType);
			
			mav= new ModelAndView("Freeze_Unfreeze_By_State");
			boolean stateFrezzeStatus = false;
			String stateSetupType=null;
			NodalOfficerState nodalOfficerState = commonService.getNodalOfficerDetails(userId);
			if (nodalOfficerState != null && nodalOfficerState.getLgdDataConfirmation() != null
					&& nodalOfficerState.getLgdDataConfirmation().getStatus() !=null 
				&&	nodalOfficerState.getLgdDataConfirmation().getStatus() == 'F') {
				stateFrezzeStatus = true;
			}
             
			lgdDataConfirmation.setUserId(userId);
			lgdDataConfirmation.setUserOTP(null);
			
			if(userType =='R')
			{
			model.addAttribute("districtFreezeEntityList",stateService.getDistrictwiseFreezeStatus(stateCode, userType));
			}else if(userType =='U')
			{
				lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
		    }
			
			else if(userType =='P')
			{
				stateSetupType =stateService.getStateSetupType(stateCode);
				 model.addAttribute("stateSetupType",stateSetupType);
				 
				 if("DIV".equals(stateSetupType))
					{
					 lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
					}
				
				 else if("DV".equals(stateSetupType))
					{
					 lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
					}
				 
				 else if("IV".equals(stateSetupType))
					{
					 lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
					}
				  
				 
			 }
			else if(userType =='T')
			{
			lgdDataConfirmation.setDistrictFreezeEntityListULB(stateService.freezeUnfreezeLocalBodyEntity(stateCode,'S',userType,userId));
			}
			
			model.addAttribute("stateFrezzeStatus", stateFrezzeStatus);
			mav.addObject("stateFreezeBtn",stateService.getFreezeStatusbyState(stateCode, userType, 'S'));
			mav.addObject("entityName", getEntityNamebyUserType(userType));
			model.addAttribute("lgdDataConfirmation", lgdDataConfirmation);
			return mav;
		}
		
		@RequestMapping(value = "/configureBlockVillagePartiallyMap", method = RequestMethod.GET)
		public ModelAndView configureBlockVillagePartiallyMap (	@ModelAttribute("configBlockVillageMapping") ConfigurationBlockVillageMapping configBlockVillageMapping,HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model){
			ModelAndView mav = null;
		    String saveflag="N";
			try {
				  mav= new ModelAndView("CONFIGURE_BLOCKVILLAGE_PARTIALLYMAP");
				  ConfigurationBlockVillageMapping configBlockVillageMapping1 =  stateService.getconfigureBlockVillage(userId ,stateCode);
				  if(configBlockVillageMapping1 != null) {
					  mav.addObject("configBlockVillageMapping", configBlockVillageMapping1);
				  }
				 //mav.addObject("entityName", getEntityNamebyUserType(isUserType));
				
		 
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
			}
			return mav;
		}
	
		
		
		@RequestMapping(value = "/saveConfigureBlockVillagePartiallyMap", method = RequestMethod.POST)
		public ModelAndView saveConfigureBlockVillagePartiallyMap(HttpSession session, HttpServletRequest request, Model model,
				@ModelAttribute("configBlockVillageMapping") ConfigurationBlockVillageMapping configBlockVillageMapping , BindingResult binding) {
			ModelAndView mav = new ModelAndView("Get_State_Configuration");
			boolean flag = false;
			boolean saveFlag = false;
			String radioSelect= request.getParameter("coverageType");
			if("true".equals(radioSelect)) {
				flag =true;
			}
			
			 //ConfigurationBlockVillageMapping configBlockVillageMapping = new ConfigurationBlockVillageMapping();
			
			try 
			{
				 Character isUserType = session.getAttribute("isUserType") != null
							? session.getAttribute("isUserType").toString().charAt(0) : null;
					
						   Timestamp timestamp = new Timestamp(System.currentTimeMillis());   
						    configBlockVillageMapping.setCreatedby(userId);
							configBlockVillageMapping.setCreatedon(timestamp);
							configBlockVillageMapping.setCoverageType(flag);
							configBlockVillageMapping.setUserType(isUserType);
							configBlockVillageMapping.setStateCode(stateCode);
							saveFlag =stateService.saveconfigBlockVillageMapping(configBlockVillageMapping);
							//mav.setViewName("success");
							if(saveFlag)
							{
								
								mav.addObject("msgid", "Configuration  of Block Village(Partially/Fully)  save successfully. ");
								
							}else{
								mav.addObject("msgid", "Some problem to save data. ");
							}
							
						
						
			}catch (Exception e) {
				log.error("stateController(saveLGDDataConfirmation(POST))--->>" + e);
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
			}
			
			return mav;
	}
		
		
		
		
	//Added by Sushma Singh march 05,2020
		
		@RequestMapping(value = "/modifyStateChangeEffectiveDate", method = RequestMethod.POST)
		public ModelAndView modifyVillageChangeEffectiveDate(@ModelAttribute("statebean")StateForm stateForm, Model model, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
			ModelAndView mav = new ModelAndView("CHANGE_EFFECTIVE_DATE_OF_STATE");
				Integer StateCode=null;
			try {
				//model.addAttribute("StateCode",stateForm.getStateCode());
				
				
				StateCode=Integer.valueOf(request.getParameter("stateId"));
				model.addAttribute("StateCode",StateCode);
				model.addAttribute("curDate",new Date());
				 
				
				
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
		
		@RequestMapping(value = "/saveEffectiveDateState", headers="Accept=application/json", method = RequestMethod.POST)
		public @ResponseBody Response saveEffectiveDate(@RequestBody EffectiveDateList getEntityEffectiveDateList,HttpServletRequest request) throws Exception {
			HttpSession httpsession= request.getSession();
			setGlobalParams(httpsession);
			return stateService.saveEffectiveDateEntityState(getEntityEffectiveDateList,userId.longValue());
		}
		
		

		
}
