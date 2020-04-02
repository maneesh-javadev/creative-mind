package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GetLandRegionNameforWard;
import in.nic.pes.lgd.bean.GetLandRegionWise;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.GetLocalbodyDisturbRegion;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.LgdPfmsMapping;
import in.nic.pes.lgd.bean.LocalBodyCoveredArea;
import in.nic.pes.lgd.bean.LocalBodyDetails;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalGovtBody;
import in.nic.pes.lgd.bean.LocalGovtBodyWard;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyTypeInState;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.ParentWiseLocalBodies;
import in.nic.pes.lgd.bean.PartillyMappedLRList;
import in.nic.pes.lgd.bean.PushLBtoPES;
import in.nic.pes.lgd.bean.Pushcoveragetopes;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.UlbBean;
import in.nic.pes.lgd.bean.UnLRDistrictWiseList;
import in.nic.pes.lgd.bean.UnLRSWiseList;
import in.nic.pes.lgd.bean.UnmappedLBList;
import in.nic.pes.lgd.bean.ViewLandRegionDisturbedlist;
import in.nic.pes.lgd.bean.ViewLocalBodyLandRegion;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.localbodywardtemp;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LgdPfmsMappingForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.service.ComboFillingService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.DisturbedEntitiesService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.GovtOrderTemplateService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.service.impl.DistrictServiceImpl;
import in.nic.pes.lgd.utils.ApplicationConstant;
import in.nic.pes.lgd.utils.CommonUtil;
import in.nic.pes.lgd.validator.CustomValidatorLB;
import in.nic.pes.lgd.validator.CustomValidatorLBModify;
import in.nic.pes.lgd.validator.CustomValidatorWard;
import in.nic.pes.lgd.validator.GovernmentOrderValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.cmc.lgd.localbody.services.LocalBodyService;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class LocalGovtBodyController.
 */
@Controller
public class LocalGovtBodyController { // NO_UCD (unused code)

	private static final Logger log = Logger.getLogger(LocalGovtBodyController.class);

	private static final String VIEW_LOCAL_BODY_LIST = "viewLocalBodyList";

	private static final String VIEW_LOCAL_BODY_LIST_OTHER = "view_localgovtbody";
	private static final String VIEW_LOCAL_BODY_LIST_TRADITIONAL = "viewLocalBodyListTraditional";
	private static final String VIEW_LOCAL_BODY_LIST_URBAN = "viewLocalBodyListUrban";

	/** The local govt body service. */
	@Autowired
	private LocalGovtBodyService localGovtBodyService;

	@Autowired
	DisturbedEntitiesService disturbedEntitiesService;
	
	@Autowired
	DistrictService districtService;

	@Autowired
	private GovtOrderTemplateService govtOrderTemplateService;

	/** The local body validator. */
	@Autowired
	private CustomValidatorLB cValidator;

	@Autowired
	private CustomValidatorLBModify cValidatorModify;

	/** The Ward validator. */
	@Autowired
	private CustomValidatorWard validatorWard;

	/** The local govt setup service. */
	@Autowired
	private LocalGovtSetupService localGovtSetupService;

	/** The govt order service. */
	@Autowired
	private GovernmentOrderService govtOrderService;
	@Autowired
	private ConfigGovtOrderService configGovtOrderService;
	@Autowired
	private ComboFillingService comboFillingService;

	@Autowired
	private FileUploadUtility fileUploadUtility;

	@Autowired
	private EmailService emailService;

	@Autowired
	private LocalGovtBodyDraftController bodyDraftController;
	
	@Autowired
	GovernmentOrderValidator govtOrderValidator;
	
	private Integer stateCode;

	private Integer districtCode;

	private Integer userId;
	
	@Autowired
	private LocalBodyService localBodyService;
	
	

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		init(session);
	}

	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	}
	
	public void init(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId().intValue();
	}

	
	/**
	 * Invalidate local body urban.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @param httpSession
	 *            the http session
	 * @return the model and view
	 */
	@RequestMapping(value = "/invalidateLocalBodyUrban", method = RequestMethod.GET)
	public ModelAndView invalidateLocalBodyUrban(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpSession) {
		// List<District> districtList=localGovtBodyService.getDistrictList(10);
		// UC-042

		ModelAndView mav = null;
		try {
			mav = null;
			char category = 'U';
			if (stateCode == null ) {
				return new ModelAndView("redirect:login.htm");
			}


			List<LocalbodyTypeInState> listlbt = null;
			listlbt = new ArrayList<LocalbodyTypeInState>();
			listlbt = localGovtSetupService.getActiveLBTinState(stateCode, category);

			if (listlbt != null) {
				if (listlbt.size() > 0) {
					mav = new ModelAndView("invalidateLB");
					mav.addObject("category", 'U');
					mav.addObject("listlbt", listlbt);
				} else if (listlbt.size() == 0) {
					String message = "Setup is not Defined in this State";
					mav = new ModelAndView("error");
					mav.addObject("message", message);
				} else {
					String message = "Server Error";
					mav = new ModelAndView("error");
					mav.addObject("message", message);
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}

		return mav;
	}

	/**
	 * Invalidate local body panchayat.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @param httpSession
	 *            the http session
	 * @return the model and view
	 */
	@RequestMapping(value = "/invalidateLocalBodyPanchayat", method = RequestMethod.GET)
	public ModelAndView invalidateLocalBodyPanchayat(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpSession) {
		// List<District> districtList=localGovtBodyService.getDistrictList(10);
		// UC-042

		ModelAndView mav = null;
		try {
			mav = null;
			char category = 'U';
			
			if (stateCode == null) {
				return new ModelAndView("redirect:login.htm");
			}
			
			List<LocalbodyTypeInState> listlbt = null;
			listlbt = new ArrayList<LocalbodyTypeInState>();
			listlbt = localGovtSetupService.getActiveLBTinState(stateCode, category);
			if (listlbt.size() > 0) {
				mav = new ModelAndView("invalidateLB");
				mav.addObject("listlbt", listlbt);
				mav.addObject("category", 'P');
			} else if (listlbt.size() == 0)

			{
				String message = "Setup is not Defined in this State";
				mav = new ModelAndView("error");
				mav.addObject("message", message);
				mav.addObject("category", 'U');
			} else {
				String message = "Server Error";
				mav = new ModelAndView("error");
				mav.addObject("message", message);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}

		return mav;
	}

	/**
	 * Invalidate local body traditional.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @param httpSession
	 *            the http session
	 * @return the model and view
	 */
	@RequestMapping(value = "/invalidateLocalBodyTraditional", method = RequestMethod.GET)
	public ModelAndView invalidateLocalBodyTraditional(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpSession) {
		// UC-042
		ModelAndView mav = null;
		try {
			mav = null;
			char category = 'U';
			
			if (stateCode == null) {
				return new ModelAndView("redirect:login.htm");
			}
			
			List<LocalbodyTypeInState> listlbt = null;
			listlbt = new ArrayList<LocalbodyTypeInState>();
			listlbt = localGovtSetupService.getActiveLBTinState(stateCode, category);
			if (listlbt.size() > 0) {
				mav = new ModelAndView("invalidateLB");
				mav.addObject("category", 'T');
				mav.addObject("listlbt", listlbt);
			} else if (listlbt.size() == 0)

			{
				String message = "Setup is not Defined in this State";
				mav = new ModelAndView("error");
				mav.addObject("message", message);
				mav.addObject("category", 'U');
			} else {
				String message = "Server Error";
				mav = new ModelAndView("error");
				mav.addObject("message", message);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}

		return mav;
	}

	/**
	 * Invalidate lb post.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @return the model and view
	 */
	@RequestMapping(value = "/invalidateLBPost", method = RequestMethod.POST)
	public ModelAndView invalidateLBPost(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			
			mav = new ModelAndView("modify_gov_local_body_name");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	

	/**
	 * Draft localbody for pri.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param model
	 *            the model
	 * @param status
	 *            the status
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @return the model and view
	 */

	// private List<MapAttachment> mapAttachmentList = new
	// ArrayList<MapAttachment>();
	/*
	 * Declare districtPanchayatList,localBodyTypelist,localBodyTypelistTrad,
	 * districtPanchayatListTrad variable for create PRI localBody Details.
	 */
	@RequestMapping(value = "/draftLocalBodyforPRI", method = RequestMethod.POST)
	public ModelAndView draftLocalBodyforPRI(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, SessionStatus status, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;

		try {
			// /////////// DROP DOWN VALIDATIONS /////////////////////
			// ////////code for government order
			// checking///////////////////////////////////
			
			
			localGovtBodyForm.setSlc(stateCode);
			List<State> statenameList = null;
			List<Localbody> parentLocalbodyNameList = null;
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			List<LocalbodyforStateWise> localBodyTypelistTrad = localGovtBodyService.getLocalBodyListStateWise('T', stateCode);
			List<LocalbodyListbyState> districtPanchayatListTrad = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'T');
			if (stateCode != 0) {
				statenameList = localGovtBodyService.getStateName(stateCode);
				Iterator<State> statenameItr = statenameList.iterator();
				localGovtBodyForm.setStateName(statenameItr.next().getStateNameEnglish());
			}

			if ((!localGovtBodyForm.getLgd_LBDistrictAtInter().trim().equals("0") || Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtInter()) != 0) && localGovtBodyForm.getLgd_LBDistrictAtInter() != null
					&& localGovtBodyForm.getLgd_LBTypeName().charAt(2) == 'I') {
				parentLocalbodyNameList = localGovtBodyService.getLocalbodyname(localGovtBodyForm.getLgd_LBDistrictAtInter());
				Iterator<Localbody> localbodynameItr = parentLocalbodyNameList.iterator();
				localGovtBodyForm.setParentLocalbodyName(localbodynameItr.next().getLocalBodyNameEnglish());
			}
			// Code added by Arnab to resolve issue in Live site on 21/03/2013
			if ((!localGovtBodyForm.getLgd_LBDistrictAtInter().trim().equals("0") || Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtInter()) != 0) && localGovtBodyForm.getLgd_LBTypeName().charAt(2) == 'V') {
				parentLocalbodyNameList = localGovtBodyService.getLocalbodyname(localGovtBodyForm.getLgd_LBDistrictAtInter());
				Iterator<Localbody> localbodynameItr = parentLocalbodyNameList.iterator();
				localGovtBodyForm.setParentLocalbodyName(localbodynameItr.next().getLocalBodyNameEnglish());
			}
			

			String lgTypeNameId = localGovtBodyForm.getLgd_LBTypeName();

			String[] lgTypeNameArr = lgTypeNameId.split(":");
			String localGovtType = lgTypeNameArr[2];
			localGovtBodyForm.setLgd_LocalBodyTypeName(localGovtType);

			String contDist = null;
			String contSubDist = null;
			String contVillage = null;
			String contUnmappedDist = null;
			String contUnmappedSubDist = null;
			String contUnmappeVillage = null;
			String finalconDist = null;
			String finalconSubDist = null;
			String finalconVillage = null;

			if (localGovtBodyForm.getLbType() == 'P' || localGovtBodyForm.getLbType() == 'T') {
				if (localGovtBodyForm.isLgd_LBExistCheck()) {
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null) {
						List<District> contDistrict = localGovtBodyService.getDistrictNamebyDisID(localGovtBodyForm);
						if (contDistrict != null) {
							Iterator<District> distItr = contDistrict.iterator();
							StringBuffer finalcontDist = new StringBuffer();

							while (distItr.hasNext()) {
								finalcontDist.append(distItr.next().getDistrictNameEnglish().trim() + ",");
							}
							contDist = finalcontDist.toString();
							contDist = contDist.substring(0, finalcontDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);

							// localGovtBodyForm.setContDistrict(contDist);
						}
					}
					if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null) {
						List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyDisID(localGovtBodyForm);
						if (conSubDistrict != null) {
							Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
							StringBuffer finalcontSubDist = new StringBuffer();

							while (subdistItr.hasNext()) {
								finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDist = finalcontSubDist.toString();
							contSubDist = contSubDist.substring(0, finalcontSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContSubDistrict(contSubDist);
						}
					}
					if (localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null) {
						List<Village> conVillage = localGovtBodyService.getconVillageNamebyVillID(localGovtBodyForm);
						if (conVillage != null) {
							Iterator<Village> subVillageItr = conVillage.iterator();
							StringBuffer finalcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillage = finalcontVillage.toString();
							contVillage = contVillage.substring(0, finalcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContVillage(contVillage);
						}
					}
					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null) {
						List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyInterSubDisID(localGovtBodyForm);
						if (conSubDistrict != null) {
							Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
							StringBuffer finalcontSubDist = new StringBuffer();

							while (subdistItr.hasNext()) {
								finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDist = finalcontSubDist.toString();
							contSubDist = contSubDist.substring(0, finalcontSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContSubDistrict(contSubDist);
						}
					}
					if (localGovtBodyForm.getLgd_LBVillageDestLatICA() != null) {
						List<Village> conVillage = localGovtBodyService.getconVillageNamebyInterVillID(localGovtBodyForm);
						if (conVillage != null) {
							Iterator<Village> subVillageItr = conVillage.iterator();
							StringBuffer finalcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillage = finalcontVillage.toString();
							contVillage = contVillage.substring(0, finalcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContVillage(contVillage);
						}
					}
					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null) {
						List<Village> conVillage = localGovtBodyService.getconVillageNamebyVillageID(localGovtBodyForm);
						if (conVillage != null) {
							Iterator<Village> subVillageItr = conVillage.iterator();
							StringBuffer finalcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillage = finalcontVillage.toString();
							contVillage = contVillage.substring(0, finalcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContVillage(contVillage);
						}
					}
				}
				if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null) {
						List<District> contUnmappedDistrict = localGovtBodyService.getUnmappedDistrictNamebyDisID(localGovtBodyForm);
						if (contUnmappedDistrict != null) {
							Iterator<District> unMappeddistItr = contUnmappedDistrict.iterator();
							StringBuffer finalcontunMappedDist = new StringBuffer();

							while (unMappeddistItr.hasNext()) {
								finalcontunMappedDist.append(unMappeddistItr.next().getDistrictNameEnglish().trim() + ",");
							}
							contUnmappedDist = finalcontunMappedDist.toString();
							contUnmappedDist = contUnmappedDist.substring(0, finalcontunMappedDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);

						}
					}
					if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null) {
						List<Subdistrict> contUnmappedSubDistrict = localGovtBodyService.getUnmappedSubDistrictNamebyDisID(localGovtBodyForm);
						if (contUnmappedSubDistrict != null) {
							Iterator<Subdistrict> unMappedsubdistItr = contUnmappedSubDistrict.iterator();
							StringBuffer finalcontunMappedSubDist = new StringBuffer();

							while (unMappedsubdistItr.hasNext()) {
								finalcontunMappedSubDist.append(unMappedsubdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contUnmappedSubDist = finalcontunMappedSubDist.toString();
							contUnmappedSubDist = contUnmappedSubDist.substring(0, finalcontunMappedSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}
					if (localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						List<Village> conUnmappedVillage = localGovtBodyService.getconunmappedVillageNamebyVillageID(localGovtBodyForm);
						if (conUnmappedVillage != null) {
							Iterator<Village> subVillageItr = conUnmappedVillage.iterator();
							StringBuffer finalunmappedcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalunmappedcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contUnmappeVillage = finalunmappedcontVillage.toString();
							contUnmappeVillage = contUnmappeVillage.substring(0, finalunmappedcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}

					if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null) {
						List<Subdistrict> contUnmappedSubDistrictInter = localGovtBodyService.getUnmappedSubDistrictbyInterNamebyDisID(localGovtBodyForm);
						if (contUnmappedSubDistrictInter != null) {
							Iterator<Subdistrict> unMappedsubdistInterItr = contUnmappedSubDistrictInter.iterator();
							StringBuffer finalcontunMappedSubDist = new StringBuffer();

							while (unMappedsubdistInterItr.hasNext()) {
								finalcontunMappedSubDist.append(unMappedsubdistInterItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contUnmappedSubDist = finalcontunMappedSubDist.toString();
							contUnmappedSubDist = contUnmappedSubDist.substring(0, finalcontunMappedSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}
					if (localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						List<Village> conUnmappedVillage = localGovtBodyService.getconunmappedVillageNamebyInterVillageID(localGovtBodyForm);
						if (conUnmappedVillage != null) {
							Iterator<Village> subVillageItr = conUnmappedVillage.iterator();
							StringBuffer finalunmappedcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalunmappedcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contUnmappeVillage = finalunmappedcontVillage.toString();
							contUnmappeVillage = contUnmappeVillage.substring(0, finalunmappedcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}

					if (localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						List<Village> conUnmappedVillage = localGovtBodyService.getconunmappedVillageNamebyVPancVillageID(localGovtBodyForm);
						if (conUnmappedVillage != null) {
							Iterator<Village> subVillageItr = conUnmappedVillage.iterator();
							StringBuffer finalunmappedcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalunmappedcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contUnmappeVillage = finalunmappedcontVillage.toString();
							contUnmappeVillage = contUnmappeVillage.substring(0, finalunmappedcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}

				}
			}

			if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null) {
					finalconDist = contDist + "," + contUnmappedDist;
					localGovtBodyForm.setContDistrict(finalconDist);
				}
				if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null) {
					finalconDist = contDist;
					localGovtBodyForm.setContDistrict(finalconDist);
				}

				if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null) {
					finalconSubDist = contSubDist + "," + contUnmappedSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
				if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null) {
					finalconSubDist = contSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}

				if (localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
					finalconVillage = contVillage + "," + contUnmappeVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}
				if (localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
					finalconVillage = contVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}

				if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null) {
					finalconSubDist = contSubDist + "," + contUnmappedSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
				if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null) {
					finalconSubDist = contSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}

				if (localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
					finalconVillage = contVillage + "," + contUnmappeVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}
				if (localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
					finalconVillage = contVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}

				if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
					finalconVillage = contVillage + "," + contUnmappeVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}

				if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
					finalconVillage = contVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}
			}

			if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null) {
					finalconDist = contDist;
					localGovtBodyForm.setContDistrict(finalconDist);
				}
				if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null) {
					finalconSubDist = contSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
				if (localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null) {
					finalconVillage = contVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}
				if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null) {
					finalconSubDist = contSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
				if (localGovtBodyForm.getLgd_LBVillageDestLatICA() != null) {
					finalconVillage = contVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}
				if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null) {
					finalconVillage = contVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}

			}
			if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null) {
					finalconDist = contUnmappedDist;
					localGovtBodyForm.setContDistrict(finalconDist);
				}
				if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null) {
					finalconSubDist = contUnmappedSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
				if (localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
					finalconVillage = contUnmappeVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}
				if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null) {
					finalconSubDist = contUnmappedSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
				if (localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
					finalconVillage = contUnmappeVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}
				if (localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
					finalconVillage = contUnmappeVillage;
					localGovtBodyForm.setContVillage(finalconVillage);
				}
			}

			if (localGovtBodyForm.getLbType() == 'P') {
				localGovtBodyForm.setOperationCode(20);
				// localGovtBodyForm.setFlagCode(13);
				if (localGovtBodyForm.isLgd_LBExistCheck() || localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					localGovtBodyForm.setFlagCode(13);
				}
				if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					localGovtBodyForm.setFlagCode(29);
				}
			}
			if (localGovtBodyForm.getLbType() == 'T') {
				localGovtBodyForm.setOperationCode(27);
				// localGovtBodyForm.setFlagCode(14);
				if (localGovtBodyForm.isLgd_LBExistCheck() || localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					localGovtBodyForm.setFlagCode(14);
				}
				if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					localGovtBodyForm.setFlagCode(30);
				}
			}

			Map<String, String> hMap = new HashMap<String, String>();

			if (localGovtBodyForm.getLbType() == 'P') {
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, localGovtBodyForm.getOperationCode(), localGovtBodyForm.getLbType());
			}
			if (localGovtBodyForm.getLbType() == 'T') {
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, localGovtBodyForm.getOperationCode(), localGovtBodyForm.getLbType());
			}
			// hMap =
			// configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode,localGovtBodyForm.getOperationCode(),
			// 'V');// 10 is operation code for create new
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			// ////////code for government order
			// checking///////////////////////////////////

			// Errors errors = null;
			
			request.setAttribute("stateCode", stateCode);
			if (localGovtBodyForm.getLbType() == 'P') {
				localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);// Only
																			// Used
																			// For
																			// Combo
																			// Field
																			// Validation
				localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList); // Only
																					// Used
																					// For
																					// Combo
																					// Field
																					// Validation
			} else if (localGovtBodyForm.getLbType() == 'T') {
				localGovtBodyForm.setLocalBodyTypelist(localBodyTypelistTrad);// Only
																				// Used
																				// For
																				// Combo
																				// Field
																				// Validation
				localGovtBodyForm.setDistrictPanchayatList(districtPanchayatListTrad); // //
																						// Only
																						// Used
																						// For
																						// Combo
																						// Field
																						// Validation
			}

			if (localGovtBodyForm.getAttachFile2() != null) {
				List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapLocalBody(request, localGovtBodyForm, result, mav, session);
				session.setAttribute("validFileMap", validFileMap);
			}

			cValidator.validateLB(localGovtBodyForm, result, request, session);
			if (result.hasErrors()) {
				if (govtOrderConfig != null && mapConfig != null) {

					if (localBodyTypelist.size() > 0 && localGovtBodyForm.getLbType() == 'P') {
						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
						model.addAttribute("localBodyTypelist", localBodyTypelist);
						model.addAttribute("lbType", "P");
					} else if (localBodyTypelistTrad.size() > 0 && localGovtBodyForm.getLbType() == 'T') {
						localGovtBodyForm = setLBtype(localBodyTypelistTrad, localGovtBodyForm);
						model.addAttribute("localBodyTypelist", localBodyTypelistTrad);
						model.addAttribute("lbType", "T");
					}
					if (localGovtBodyForm.getLbType() == 'P') {
						model.addAttribute("districtPanchayatList", districtPanchayatList);
					} else if (localGovtBodyForm.getLbType() == 'T') {
						model.addAttribute("districtPanchayatList", districtPanchayatListTrad);
					}

					if (session.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					session.removeAttribute("validationErrorOne1");

					// mav.addObject("hideMap", mapConfig);
					// mav.addObject("govtOrderConfig", govtOrderConfig);
					mav = new ModelAndView("create_localgovtbody");
					mav.addObject("hideMap", mapConfig);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					return mav;
				}
			} else {
				/*
				 * List<CommonsMultipartFile> attachedFile =
				 * localGovtBodyForm.getAttachFile2(); if
				 * (attachedFile.get(0).getSize() > 0) { String[] titles =
				 * localGovtBodyForm.getFileTitle1(); addAttachmentBean2 =
				 * govtOrderService.getAttachmentBeanforMap(attachedFile,
				 * titles,request); if (addAttachmentBean2 != null) { String
				 * validateAttachment1 =
				 * attachmentHandler.validateAttachment(addAttachmentBean2);
				 * metaInfoOfToBeAttachedMapList =
				 * attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
				 * if (!validateAttachment1.equalsIgnoreCase(
				 * "validationSuccessFullyDone")) {
				 */
				if (localBodyTypelist.size() > 0 && localGovtBodyForm.getLbType() == 'P') {
					localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);// Only
																				// Used
																				// For
																				// Combo
																				// Field
																				// alidation
					localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
					model.addAttribute("localBodyTypelist", localBodyTypelist);
				} else if (localBodyTypelistTrad.size() > 0 && localGovtBodyForm.getLbType() == 'T') {
					localGovtBodyForm.setLocalBodyTypelist(localBodyTypelistTrad);// Only
																					// Used
																					// For
																					// Combo
																					// Field
																					// Validation

					localGovtBodyForm = setLBtype(localBodyTypelistTrad, localGovtBodyForm);
					model.addAttribute("localBodyTypelist", localBodyTypelistTrad);
				}
				if (localGovtBodyForm.getLbType() == 'P') {
					localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList); // Only
																						// Used
																						// For
																						// Combo
																						// Field
																						// Validation
					model.addAttribute("districtPanchayatList", districtPanchayatList);
				} else if (localGovtBodyForm.getLbType() == 'T') {
					localGovtBodyForm.setDistrictPanchayatList(districtPanchayatListTrad); // Only
																							// Used
																							// For
																							// Combo
																							// Field
																							// Validation
					model.addAttribute("districtPanchayatList", districtPanchayatListTrad);

				}
				mav = new ModelAndView("create_localgovtbody");
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				// request.setAttribute("validationErrorOne1",validateAttachment1);
				// return mav;
				// }
				// }
				// }
				/*
				 * if(localGovtBodyForm.getAttachFile2()!=null) {
				 * AddAttachmentHandler attachmentHandler = new
				 * AddAttachmentHandler(); AddAttachmentBean addAttachmentBean2
				 * = getAttachmentBeanMapLocalbody(localGovtBodyForm, request);
				 * AddAttachmentHandler attachmentHandlerMap = new
				 * AddAttachmentHandler(); String validateAttachment1 =
				 * attachmentHandlerMap.validateAttachment(addAttachmentBean2);
				 * 
				 * if (mapAttachmentList == null || mapAttachmentList.isEmpty())
				 * { if (!validateAttachment1.equalsIgnoreCase(
				 * "validationSuccessFullyDone")) { List<MapAttachment>
				 * alreadyAttachList =
				 * govtOrderService.getMapAttachmentListbyEntity(ordercode,
				 * 'G');
				 * request.setAttribute("validationErrorOne1",validateAttachment1
				 * );
				 * request.setAttribute("mapAttachmentList",alreadyAttachList);
				 * session
				 * .setAttribute("validationErrorOne1",validateAttachment1); mav
				 * = new ModelAndView("create_localgovtbody"); return mav; } }
				 * 
				 * String deleteSucOne1 =
				 * attachmentHandler.deleteMetaDataINFileSystem
				 * (addAttachmentBean2); List<AttachedFilesForm>
				 * metaInfoOfToBeAttachedMapList =
				 * attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
				 * String saveAttachmentMap =
				 * attachmentHandler.saveMetaDataINFileSystem
				 * (metaInfoOfToBeAttachedMapList,addAttachmentBean2); }
				 */

				// govtOrderConfig = localGovtBodyForm.getGovtOrderConfig();
				localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);
				localGovtBodyForm.setOperation("PC");
				/*
				 * if (localGovtBodyForm.getLbType() == 'P') {
				 * localGovtBodyForm.setOperationCode(20); } if
				 * (localGovtBodyForm.getLbType() == 'T') {
				 * localGovtBodyForm.setOperationCode(27); }
				 */
				session.setAttribute("formbean", localGovtBodyForm);
				mav.addObject("hideMap", mapConfig);
				mav = new ModelAndView("redirect:govtOrderCommon.htm");
				mav.addObject("govtOrderConfig", govtOrderConfig);
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
	 * Creates the local bodyfor traditional.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param model
	 *            the model
	 * @param session
	 *            the session
	 * @param request
	 *            the request
	 * @return the model and view
	 */
	@SuppressWarnings("deprecation")
	/**
	 * @author Vinay Yadav
	 * commented
	 * 
	 */
	// @RequestMapping(value = "/createLocalBodyforTraditional", method =
	// RequestMethod.GET)
	public ModelAndView createLocalBodyforTraditional(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpSession session, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			
			int operationCode = 27;
			int flagCode = 14;
			mav = null;
			/** The Intermediate panchayat list. */
			// List<LocalbodyListbyState> intermediatePanchayatList = new
			// ArrayList<LocalbodyListbyState>();
			List<UnLRSWiseList> unmappedstatewiseVList = new ArrayList<UnLRSWiseList>();
			List<UnLRDistrictWiseList> unmappeddistrictwiseVList = new ArrayList<UnLRDistrictWiseList>();
			if (stateCode != 0) {
				request.setAttribute("stateCode", stateCode);
				if (session.getAttribute("formbean") != null) {
					session.removeAttribute("formbean");
					session.removeValue("formbean");
				}

				List<LocalbodyforStateWise> localBodyTypelistTrad = localGovtBodyService.getLocalBodyListStateWise('T', stateCode);

				/*
				 * List<LocalbodyListbyState> districtPanchayatList =
				 * localGovtBodyService
				 * .getPanchayatListbyStateandCategory(stateCode, 'T');
				 */
				List<LocalbodyListbyState> districtPanchayatListTrad = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'T');

				// intermediatePanchayatList =
				// localGovtBodyService.getExistingLBListbyStateandCategoryInter(stateCode,
				// 'T');

				if (localBodyTypelistTrad.size() > 0) {

					localGovtBodyForm = setLBtype(localBodyTypelistTrad, localGovtBodyForm);
				}
				if (districtPanchayatListTrad.size() > 0) {
					model.addAttribute("districtPanchayatList", districtPanchayatListTrad);
				}

				if (districtCode == 0) {
					unmappedstatewiseVList = localGovtBodyService.getUnMapLRStaWiseList('V', stateCode);
				} else if (districtCode > 0) {
					unmappeddistrictwiseVList = localGovtBodyService.getUnMapLRDistWiseList('V', districtCode);
				}

				localGovtBodyForm.setOperation("PC");
				localGovtBodyForm.setOperationCode(operationCode);
				localGovtBodyForm.setFlagCode(flagCode);
				localGovtBodyForm.setLbType('T');

				Map<String, String> hMap = new HashMap<String, String>();
				// Please first check your operation code then set it in place
				// of 10
				char lbType = 'T';
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				String message = hMap.get("message");
				if (govtOrderConfig != null && mapConfig != null) {

					mav = new ModelAndView("create_localgovtbody");
					mav.addObject("hideMap", mapConfig);
					model.addAttribute("lbType", "T");
					mav.addObject("govtOrderConfig", govtOrderConfig);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("localBodyTypelist", localBodyTypelistTrad);
					if (districtCode == 0) {
						model.addAttribute("unmappedVillList", unmappedstatewiseVList);
					} else if (districtCode > 0) {
						model.addAttribute("unmappedVillList", unmappeddistrictwiseVList);
					}
					mav.addObject("localGovtBodyForm", localGovtBodyForm);

				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
				}

			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
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
	 * Creates the local body for urban. Declare localBodyTypelistUrban
	 * variable.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param model
	 *            the model
	 * @param session
	 *            the session
	 * @param request
	 *            the request
	 * @return the model and view
	 */

	@SuppressWarnings("deprecation")
	/**
	 * @author Vinay Yadav
	 */
	// @RequestMapping(value = "/createLocalBodyforUrban", method =
	// RequestMethod.GET)
	public ModelAndView createLocalBodyforUrban(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpSession session, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			int operationCode = 21;
			int flagCode = 15;
			mav = null;
			

				
				if (session.getAttribute("formbean") != null) {
					session.removeAttribute("formbean");
					session.removeValue("formbean");
				}
				request.setAttribute("stateCode", stateCode);
				List<LocalbodyforStateWise> localBodyTypelistUrban = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);

				localGovtBodyForm.setOperation("PC");
				localGovtBodyForm.setOperationCode(operationCode);
				localGovtBodyForm.setFlagCode(flagCode);

				localGovtBodyForm.setLbType('U');

				// Please first check your operation code then set it in place
				// of 10
				char lbType = 'U';
				Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				String message = hMap.get("message");
				if (govtOrderConfig != null && mapConfig != null) {

					mav = new ModelAndView("createUrbanlocalgovtbody");
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					model.addAttribute("localBodyTypelist", localBodyTypelistUrban);
					model.addAttribute("districtCode", districtCode);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					model.addAttribute("isDistrictLevel", ApplicationConstant.checkStateLBOnlyDisttWise(stateCode)); // Setting
																														// checked
																														// value
																														// for
																														// JSP
																														// Reference.
																														// @author
																														// Vinay
																														// Yadav
																														// 23-12-2013

				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
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
	 * Draft urban local govt body. Declare localBodyTypelistUrban variable
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param model
	 *            the model
	 * @param status
	 *            the status
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @return the model and view
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/draftUrbanLocalGovtBody", method = RequestMethod.POST)
	public ModelAndView draftUrbanLocalGovtBody(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, SessionStatus status, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		/*
		 * AddAttachmentBean addAttachmentBean2 = null; AddAttachmentHandler
		 * attachmentHandler = new AddAttachmentHandler();
		 * List<AttachedFilesForm> metaInfoOfToBeAttachedMapList = null;
		 */

		try {
			// ////////code for government order
			// checking///////////////////////////////////
			
			localGovtBodyForm.setSlc(stateCode);
			List<State> statenameList = null;
			List<LocalbodyforStateWise> localBodyTypelistUrban = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelistUrban);// Only
																			// Used
																			// For
																			// Combo
																			// Field
																			// Validation
			if (stateCode != 0) {
				statenameList = localGovtBodyService.getStateName(stateCode);
				Iterator<State> statenameItr = statenameList.iterator();
				localGovtBodyForm.setStateName(statenameItr.next().getStateNameEnglish());
			}

			/*
			 * if(localGovtBodyForm.isLgd_LBExistCheck()) {
			 */
			/*
			 * if (localGovtBodyForm.getLgd_LBTypeName().charAt(0)=='1') {
			 * comboFillingService.getComboValuesforApp('L', "S#1", stateCode,
			 * null);
			 * 
			 * String tmp10=localGovtBodyForm.getLgd_LBDistPDestList();
			 * tmp10=tmp10.replaceAll("PART","");
			 * tmp10=tmp10.replaceAll("FULL",""); String[] temp10 =
			 * tmp10.split(","); for (int i = 0; i < temp10.length; i++) {
			 * comboFillingService.getComboValuesforApp('L', "L",
			 * Integer.parseInt(temp10[i]), null); }
			 * if(localGovtBodyForm.getLgd_LBDistCAreaDestList() !="" &&
			 * localGovtBodyForm.getLgd_LBDistCAreaDestList() !=null) { String
			 * tmp11=localGovtBodyForm.getLgd_LBDistCAreaDestList();
			 * tmp11=tmp11.replaceAll("PART","");
			 * tmp11=tmp11.replaceAll("FULL",""); String[] temp11 =
			 * tmp11.split(","); for (int i = 0; i < temp11.length; i++) {
			 * comboFillingService.getComboValuesforApp('L', "L",
			 * Integer.parseInt(temp11[i]), null); } } }
			 */

			/*
			 * if (localGovtBodyForm.getLgd_LBTypeName().charAt(0)=='4') {
			 */
			/*
			 * if(localGovtBodyForm.getLgd_UrbanLBDistExistDest() !=null) {
			 * 
			 * //comboFillingService.getComboValuesforApp('L', "L",
			 * Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtInter()),
			 * null); String
			 * tmp=localGovtBodyForm.getLgd_UrbanLBDistExistDest().toString();
			 * tmp=tmp.replaceAll("PART",""); tmp=tmp.replaceAll("FULL","");
			 * String
			 * tmp9=localGovtBodyForm.getLgd_UrbanLBDistExistDest().replaceAll
			 * ("PART","").replaceAll("FULL",""); String
			 * tmp10=localGovtBodyForm.
			 * getLgd_UrbanLBSubdistrictListDest().replaceAll
			 * ("PART","").replaceAll("FULL","");
			 * comboFillingService.getComboValuesforList('N', "T", tmp9, tmp10);
			 * //} } else { String t=localGovtBodyForm.getLgd_LBTypeName(); int
			 * m=Integer.parseInt(t); String
			 * tmp=localGovtBodyForm.getLgd_UrbanLBDistExistDest().toString();
			 * tmp=tmp.replaceAll("PART",""); tmp=tmp.replaceAll("FULL","");
			 * comboFillingService.getComboValuesforList('L', "S#"+m,
			 * statecode1, tmp); } }
			 */
			/*
			 * if(localGovtBodyForm.isLgd_LBUnmappedCheck()) {
			 */
			/*
			 * if(localGovtBodyForm.getLgd_UrbanLBSubdistrictLUmappedDest() !=""
			 * && localGovtBodyForm.getLgd_UrbanLBSubdistrictLUmappedDest()
			 * !=null) { String
			 * tmp7=localGovtBodyForm.getLgd_UrbanLBDistUmappedDest
			 * ().replaceAll("PART","").replaceAll("FULL",""); String
			 * tmp8=localGovtBodyForm
			 * .getLgd_UrbanLBSubdistrictLUmappedDest().replaceAll
			 * ("PART","").replaceAll("FULL","");
			 * comboFillingService.getComboValuesforList('K', "S#T", statecode1,
			 * tmp8); comboFillingService.getComboValuesforList('K', "V", tmp7,
			 * tmp8); }
			 */
			// else if(localGovtBodyForm.getLgd_UrbanLBSubdistrictLUmappedDest()
			// =="" || localGovtBodyForm.getLgd_UrbanLBSubdistrictLUmappedDest()
			// ==null)
			/*
			 * if(localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() !=null) {
			 * //String
			 * tmp8=localGovtBodyForm.getLgd_UrbanLBSubdistrictLUmappedDest
			 * ().replaceAll("PART","").replaceAll("FULL",""); String
			 * tmp7=localGovtBodyForm
			 * .getLgd_UrbanLBDistUmappedDest().replaceAll(
			 * "_PART","").replaceAll("_FULL","");
			 * //comboFillingService.getComboValuesforList('K', "S#T",
			 * statecode1, tmp7); } }
			 */

			String lgTypeNameId = localGovtBodyForm.getLgd_LBTypeName();

			String[] lgTypeNameArr = lgTypeNameId.split(":");
			String localGovtType = lgTypeNameArr[1];
			localGovtBodyForm.setLgd_LocalBodyTypeName(localGovtType);
			localGovtBodyForm.setLbType('U');

			String contSubDist = null;
			String contUnmappedSubDist = null;
			String finalconSubDist = null;
			/*
			 * Get value after comparison of logged in state code with ULB
			 * District Wise. Referred in conditions.
			 * 
			 * @author vinay yadav 23-12-2013
			 */
			boolean isDisttLevel = ApplicationConstant.checkStateLBOnlyDisttWise(stateCode);
			if (localGovtBodyForm.getLbType() == 'U') {
				if (localGovtBodyForm.isLgd_LBExistCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null) {

						if (isDisttLevel) {
							String urbanLBSubdistrictListDest = localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
							contSubDist = localGovtBodyService.getDistrictNamebyCovChangeULB(urbanLBSubdistrictListDest);
						} else {
							List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyDisIDULB(localGovtBodyForm);
							if (conSubDistrict != null) {
								Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
								StringBuffer finalcontSubDist = new StringBuffer();

								while (subdistItr.hasNext()) {
									finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
								}
								contSubDist = finalcontSubDist.toString();
								contSubDist = contSubDist.substring(0, finalcontSubDist.length() - 1);
								// finalcontDist = finalcontDist.substring(0,
								// finalcontDist.length() - 1);
								// localGovtBodyForm.setContSubDistrict(contSubDist);
							}
						}
					}
				}
				if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
						if (isDisttLevel) {
							String urbanLBSubdistrictListDest = localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
							/*
							 * if(urbanLBSubdistrictListDest.contains(":")){
							 * StringBuilder builString = new StringBuilder();
							 * for(String st :
							 * urbanLBSubdistrictListDest.split("\\,")){
							 * builString
							 * .append(st.split("\\:")[1]).append(","); }
							 * urbanLBSubdistrictListDest =
							 * builString.toString(); }
							 */
							contUnmappedSubDist = localGovtBodyService.getDistrictNamebyCovChangeULB(urbanLBSubdistrictListDest.toString());
						} else {
							List<Subdistrict> conunmappedSubDistrict = localGovtBodyService.getunMappedSubDistrictNamebyDisIDULB(localGovtBodyForm);
							if (conunmappedSubDistrict != null) {
								Iterator<Subdistrict> subdistItr = conunmappedSubDistrict.iterator();
								StringBuffer finalcontSubDist = new StringBuffer();

								while (subdistItr.hasNext()) {
									finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
								}
								contUnmappedSubDist = finalcontSubDist.toString();
								contUnmappedSubDist = contUnmappedSubDist.substring(0, finalcontSubDist.length() - 1);
								// finalcontDist = finalcontDist.substring(0,
								// finalcontDist.length() - 1);
							}
						}
					}
				}
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					finalconSubDist = contSubDist + "," + contUnmappedSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
				if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
					finalconSubDist = contSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
					finalconSubDist = contSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
			}
			if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					finalconSubDist = contUnmappedSubDist;
					localGovtBodyForm.setContSubDistrict(finalconSubDist);
				}
			}

			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10

			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 21, 'U');

			// hMap =
			// configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode,
			// 21, 'U');// 10
			// create
			// new
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			// ////////code for government order
			// checking///////////////////////////////////

			// mav = null;

			// localGovtBodyForm.setLbType('U');
			// CustomValidatorLB cValidator = new CustomValidatorLB();
			if (localGovtBodyForm.getAttachFile2() != null) {
				List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapLocalBody(request, localGovtBodyForm, result, mav, session);
				session.setAttribute("validFileMap", validFileMap);
			}
			cValidator.validateLB(localGovtBodyForm, result, request, session);
			if (result.hasErrors()) {
				if (govtOrderConfig != null && mapConfig != null) {
					// mav.addObject("hideMap", mapConfig);
					// mav.addObject("govtOrderConfig", govtOrderConfig);
					
					if (stateCode != null) {
						if (session.getAttribute("formbean") != null) {
							session.removeAttribute("formbean");
							session.removeValue("formbean");
						}
						request.setAttribute("stateCode", stateCode);
					}

					if (localBodyTypelistUrban.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelistUrban, localGovtBodyForm);
						model.addAttribute("localBodyTypelist", localBodyTypelistUrban);
					}

					if (session.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					session.removeAttribute("validationErrorOne1");

					mav = new ModelAndView("createUrbanlocalgovtbody");
					mav.addObject("hideMap", mapConfig);
					// model.addAttribute("districtPanchayatList",
					// districtPanchayatList);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					return mav;
				}
			} else {

				/*
				 * List<CommonsMultipartFile> attachedFile =
				 * localGovtBodyForm.getAttachFile2(); if
				 * (attachedFile.get(0).getSize() > 0) { String[] titles =
				 * localGovtBodyForm.getFileTitle1(); addAttachmentBean2 =
				 * govtOrderService.getAttachmentBeanforMap(attachedFile,
				 * titles,request);
				 * 
				 * if (addAttachmentBean2 != null) { String validateAttachment1
				 * = attachmentHandler.validateAttachment(addAttachmentBean2);
				 * metaInfoOfToBeAttachedMapList =
				 * attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
				 * if (!validateAttachment1.equalsIgnoreCase(
				 * "validationSuccessFullyDone")) {
				 */
				
				if (stateCode != null) {
					if (session.getAttribute("formbean") != null) {
						session.removeAttribute("formbean");
						session.removeValue("formbean");
					}
					request.setAttribute("stateCode", stateCode);
				}

				if (localBodyTypelistUrban.size() > 0) {
					localGovtBodyForm = setLBtype(localBodyTypelistUrban, localGovtBodyForm);
					model.addAttribute("localBodyTypelist", localBodyTypelistUrban);
				}

				mav = new ModelAndView("createUrbanlocalgovtbody");
				// request.setAttribute("validationErrorOne1",validateAttachment1);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				// return mav;
				// }
				// }

			}

			govtOrderConfig = localGovtBodyForm.getGovtOrderConfig();
			localGovtBodyForm.setOperationCode(21);
			// localGovtBodyForm.setFlagCode(15);
			if (localGovtBodyForm.isLgd_LBExistCheck() || localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				localGovtBodyForm.setFlagCode(15);
			}
			if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				localGovtBodyForm.setFlagCode(31);
			}
			localGovtBodyForm.setOperation("PC");
			session.setAttribute("formbean", localGovtBodyForm);
			mav = new ModelAndView("redirect:govtOrderCommon.htm");
			mav.addObject("govtOrderConfig", govtOrderConfig);
			// }
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/**
	 * View local body success.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param model
	 *            the model
	 * @param lbCode
	 *            the lb code
	 * @return the model and view
	 */
	@RequestMapping(value = "/viewLocalBodySuccess", method = RequestMethod.GET)
	public ModelAndView viewLocalBodySuccess(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request, @RequestParam("id") Integer lbCode) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("viewlocalBodysuccess");
			List<Localbody> localbodylist = localGovtBodyService.getGovtLocalBodyTypeDetails(lbCode);

			List<LocalBodyCoveredArea> contridistrictList = localGovtBodyService.getCoveredDistrictListforLB(lbCode);

			List<LocalBodyCoveredArea> contrisubdistrictList = localGovtBodyService.getCoveredSubDistrictListforLB(lbCode);

			List<LocalBodyCoveredArea> contriVillageList = localGovtBodyService.getCoveredVillageListforLB(lbCode);

			model.addAttribute("localbodylist", localbodylist);
			model.addAttribute("contridistrictList", contridistrictList);
			model.addAttribute("contrisubdistrictList", contrisubdistrictList);
			model.addAttribute("contriVillageList", contriVillageList);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/**
	 * Sets the l btype.
	 * 
	 * @param localBodyTypelist
	 *            the local body typelist
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @return the local govt body form
	 */
	public LocalGovtBodyForm setLBtype(List<LocalbodyforStateWise> localBodyTypelist, LocalGovtBodyForm localGovtBodyForm) {

		try {
			for (int j = 0; j < localBodyTypelist.size(); j++) {

				String govtBodyType = localBodyTypelist.get(j).getLevel();
				// String category = localBodyTypelist.get(j).getCategory();

				if (govtBodyType != null) {

					if (govtBodyType.equalsIgnoreCase("D")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							localGovtBodyForm.setLgd_LBNomenclatureDist(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("I")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							localGovtBodyForm.setLgd_LBNomenclatureInter(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("V")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							localGovtBodyForm.setLgd_LBNomenclatureVillage(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
				}

			}
			return localGovtBodyForm;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return null;
		}
	}

	/**
	 * Creates the local body.
	 * 
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @return the model and view
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/addLocalGovtBody", method = RequestMethod.POST)
	public ModelAndView createLocalBody(SessionStatus status, HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = null;
		try {
			
			String[] val = null;

			String lbreturn = null;
			
			mav = null;
			@SuppressWarnings("unused")
			List<PushLBtoPES> lbodytoPes = null;
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
			List<AttachedFilesForm> metaInfoOfToBeAttachedMapList = null;
			// AddAttachmentBean addAttachmentBean = null;
			// AddAttachmentBean addAttachmentBean2 = null;
			// AddAttachmentHandler attachmentHandler = new
			// AddAttachmentHandler();
			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

			if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {

				lbreturn = localGovtBodyService.saveLocalGovtBody(localGovtBodyForm, stateCode, govtOrderForm, metaInfoOfToBeAttachedFileList, metaInfoOfToBeAttachedMapList, request, userId);
				val = lbreturn.split(",");

				localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));
				if (lbreturn != null) {
					// Data and upload file
					if (session.getAttribute("validFileMap") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) session.getAttribute("validFileMap");
						localGovtBodyService.saveDataInMapLocalBody(localGovtBodyForm, validFileMap, session);
					}
					if (session.getAttribute("validGovermentUpload") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) session.getAttribute("validGovermentUpload");
						localGovtBodyService.saveDataInAttach(govtOrderForm, localGovtBodyForm, validGovermentUpload, request.getSession());
					}
					if (localGovtBodyForm.isLgd_LBUnmappedCheck() && !localGovtBodyForm.isLgd_LBExistCheck()) {
						lbodytoPes = localGovtBodyService.saveLocalBodyinPES(Integer.parseInt(val[0]));
					}
				}
			} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
				lbreturn = localGovtBodyService.saveLocalGovtBody(localGovtBodyForm, stateCode, govtOrderForm, metaInfoOfToBeAttachedFileList, metaInfoOfToBeAttachedMapList, request, userId);
				val = lbreturn.split(",");

				localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (lbreturn != null) {
					if (session.getAttribute("validFileMap") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) session.getAttribute("validFileMap");
						@SuppressWarnings("unused")
						boolean insertTableFlag = localGovtBodyService.saveDataInMapLocalBody(localGovtBodyForm, validFileMap, session);
					}
					if (session.getAttribute("validGovermentGenerateUpload") != null) {
						@SuppressWarnings("unused")
						boolean insertGovtTableFlag = localGovtBodyService.saveDataInAttachGenerateLocalBody(govtOrderForm, localGovtBodyForm, request.getSession());
					}
					if (localGovtBodyForm.isLgd_LBUnmappedCheck() && !localGovtBodyForm.isLgd_LBExistCheck()) {
						lbodytoPes = localGovtBodyService.saveLocalBodyinPES(Integer.parseInt(val[0]));
					}
				}

			}

			if (lbreturn != null) {
				if (localGovtBodyForm.getLbType() == 'P') {
					int t_code = Integer.parseInt(val[1]);
					char t_type = 'R';
					EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
					est.start();
				}

				if (localGovtBodyForm.getLbType() == 'T') {
					int t_code = Integer.parseInt(val[1]);
					char t_type = 'T';
					EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
					est.start();
				}

			}
			session.removeAttribute("formbean");
			session.removeValue("formbean");
			session.removeAttribute("govtOrderForm");
			session.removeAttribute("validFileMap");

			session.removeAttribute("addAttachmentBean");

			if (lbreturn != null) {
				mav = new ModelAndView("redirect:viewLocalBodySuccess.htm?id=" + Integer.parseInt(val[0]));
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
	 * Creates the urban local body.
	 * 
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @return the model and view
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/addUrbanLocalGovtBody", method = RequestMethod.POST)
	public ModelAndView createUrbanLocalBody(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = null;
		try {
			mav = null;
			
			
			// int lbreturn = 0;
			String lbreturn = null;
			String[] val = null;
			//List<PushLBtoPES> lbodytoPes = null;
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
			List<AttachedFilesForm> metaInfoOfToBeAttachedMapList = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			AddAttachmentBean addAttachmentBean = null;
			AddAttachmentBean addAttachmentBean2 = null;
			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");
			if (session.getAttribute("addAttachmentBean") != null) {
				addAttachmentBean = (AddAttachmentBean) session.getAttribute("addAttachmentBean");
				@SuppressWarnings("unused")
				String validateAttachment = attachmentHandler.validateAttachment(addAttachmentBean);
				metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
			}

			// for Upload Map

			List<CommonsMultipartFile> attachedFile = localGovtBodyForm.getAttachFile2();
			String[] titles = localGovtBodyForm.getFileTitle1();
			addAttachmentBean2 = govtOrderService.getAttachmentBeanforMap(attachedFile, titles, request);
			if (addAttachmentBean2 != null) {
				@SuppressWarnings("unused")
				String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
				metaInfoOfToBeAttachedMapList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
			}
			if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
				lbreturn = localGovtBodyService.saveLocalGovtBodyULB(localGovtBodyForm, stateCode, govtOrderForm, metaInfoOfToBeAttachedFileList, metaInfoOfToBeAttachedMapList, request, userId);
				val = lbreturn.split(",");

				localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (lbreturn != null) {
					// Data and upload file
					if (session.getAttribute("validFileMap") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) session.getAttribute("validFileMap");
						localGovtBodyService.saveDataInMapLocalBody(localGovtBodyForm, validFileMap, session);
					}
					if (session.getAttribute("validGovermentUpload") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) session.getAttribute("validGovermentUpload");
						localGovtBodyService.saveDataInAttach(govtOrderForm, localGovtBodyForm, validGovermentUpload, request.getSession());
					}

					if (localGovtBodyForm.isLgd_LBUnmappedCheck() && !localGovtBodyForm.isLgd_LBExistCheck()) {
						//lbodytoPes = 
						localGovtBodyService.saveLocalBodyinPES(Integer.parseInt(val[0]));
					}

				}
			} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
				lbreturn = localGovtBodyService.saveLocalGovtBodyULB(localGovtBodyForm, stateCode, govtOrderForm, metaInfoOfToBeAttachedFileList, metaInfoOfToBeAttachedMapList, request, userId);
				val = lbreturn.split(",");
				localGovtBodyForm.setLocalBodyCode(Integer.parseInt(val[0]));

				if (lbreturn != null) {
					if (session.getAttribute("validFileMap") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validFileMap = (List<AttachedFilesForm>) session.getAttribute("validFileMap");
						@SuppressWarnings("unused")
						boolean insertTableFlag = localGovtBodyService.saveDataInMapLocalBody(localGovtBodyForm, validFileMap, session);
					}
					if (session.getAttribute("validGovermentGenerateUpload") != null) {
						@SuppressWarnings("unused")
						boolean insertGovtTableFlag = localGovtBodyService.saveDataInAttachGenerateLocalBody(govtOrderForm, localGovtBodyForm, request.getSession());
					}

					if (localGovtBodyForm.isLgd_LBUnmappedCheck() && !localGovtBodyForm.isLgd_LBExistCheck()) {
						//lbodytoPes = 
						localGovtBodyService.saveLocalBodyinPES(Integer.parseInt(val[0]));
					}

				}

			}

			if (lbreturn != null) {
				int t_code = Integer.parseInt(val[1]);
				char t_type = 'U';
				EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
				est.start();
			}
			session.removeAttribute("formbean");
			session.removeValue("formbean");
			session.removeAttribute("govtOrderForm");
			session.removeValue("govtOrderForm");
			session.removeAttribute("addAttachmentBean");
			session.removeValue("addAttachmentBean");

			if (lbreturn != null) {
				mav = new ModelAndView("redirect:viewLocalBodySuccess.htm?id=" + Integer.parseInt(val[0]));
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// Modify Local Urban Govt Bodies
	/**
	 * Modify urban local body.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @param request
	 * @return the model and view
	 */
	@RequestMapping(value = "/modifyDiffLocalGovtBodyMain", method = RequestMethod.GET)
	public ModelAndView modifyUrbanLocalBody(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			// model.addAttribute("districtList", districtList);
			// EsapiEncoder.encode(localGovtBodyForm);
			mav = new ModelAndView("modify_diff_govt_local_body_main");
			mav.addObject("localGovtBodyForm", localGovtBodyForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/** The lb details. */

	// List<LocalBodyDetails> lbDetails = new ArrayList<LocalBodyDetails>();

	/**
	 * Modify urban local body type.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param model
	 *            the model
	 * @param localBodyCode
	 *            the local body code
	 * @param category
	 *            the category
	 * @param session
	 *            the session
	 * @param request
	 *            the request
	 * @return the model and view
	 */

	/*
	 * Declare lbdetails,districtPanchayatList variable while manage PRI Local
	 * Form -Govt. Order Correction Link.
	 */
	@RequestMapping(value = "/modifyGovtLocalBodyMain")
	public ModelAndView modifyUrbanLocalBodyType(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "type", required = false) String typeVal) {
		ModelAndView mav = null;
		String warningEntiesFlag = request.getParameter("warningEntiesFlag");

		String id = request.getParameter("id");

		char type = ' ';
		int operationCode = 0;

		if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}
		int localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		char category = (type != ' ') ? type : localGovtBodyForm.getParentCategory();
		if (category == 'P') {
			operationCode = 45;
			localGovtBodyForm.setOperationCode(operationCode);
		} else if (category == 'T') {
			operationCode = 47;
			localGovtBodyForm.setOperationCode(operationCode);
		} else {
			operationCode = 46;
			localGovtBodyForm.setOperationCode(operationCode);
		}
		// int localBodyCode=localGovtBodyForm.getParentwiseId();
		// char category=localGovtBodyForm.getParentCategory();

		try {

			//int lbTypeCode = 0;
			String localbodyNameEng = null;
			String localbodyNameAliasEng = null;
			String coordinates = null;
			String mapattachmentfname = null;
			boolean disturbedFlag;
			boolean warningFlag;
			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();

			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			//List<Localbody> localbodyObj = null;
			mav = new ModelAndView("modify_govt_local_body");
			mav.addObject("warningEntiesFlag", warningEntiesFlag);
			session.setAttribute("warningEntiesFlag", warningEntiesFlag);
			mav.addObject("type", typeVal);
			

			// Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place
			// of 10
			//char lbType = 'U';
			// hMap =
			// govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 53,
			// lbType);

			// String govtOrderConfig = hMap.get("govtOrder");//
			// values==govtOrderUpload,govtOrderGenerate
			// String mapConfig = hMap.get("mapUpload");// values==true,false
			// String message = hMap.get("message");
			// mav.addObject("hideMap", mapConfig);

			List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
			// EsapiEncoder.encode(lbDetails);
			List<Attachment> attachmentList = null;
			List<MapAttachment> mapAttachmentList = null;
			localGovtBodyForm.setLocalBodyCode(localBodyCode);
			if (!lbDetails.isEmpty()) {
				model.addAttribute("localBodyForm", lbDetails.get(0));
				char level = lbDetails.get(0).getLevel().charAt(0);
				attachmentList = getAttachmentListbyLocalBody(localGovtBodyForm, localBodyCode, lbDetails, request);

				mapAttachmentList = getMapAttachmentListbyLocalBody(localGovtBodyForm, localBodyCode, lbDetails, request);

				// EsapiEncoder.encode(mapAttachmentList);
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);
				mav.addObject("orderCodeHidden", localGovtBodyForm.getOrderCode());

				//lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();
				localbodyNameEng = lbDetails.get(0).getLocalBodyNameEnglish();
				localbodyNameAliasEng = lbDetails.get(0).getAliasNameEnglish();
				localGovtBodyForm.setLocalbodyNameEnghidden(localbodyNameEng);
				localGovtBodyForm.setLocalbodyNameAliasEnghidden(localbodyNameAliasEng);
				coordinates = lbDetails.get(0).getCoordinates();

				session.setAttribute("Coordinates", coordinates);
				mav.addObject("Coordinates", coordinates);
				mapattachmentfname = lbDetails.get(0).getMapFileName();
				if (mapattachmentfname != null) {
					mav.addObject("govtfilecount", 1);
				}

				session.setAttribute("MapFileName", mapattachmentfname);

				disturbedFlag = lbDetails.get(0).isIsdisturbed();
				warningFlag = lbDetails.get(0).isWarningFlag();

				Map<String, String> hMap = new HashMap<String, String>();

				// Please first check your operation code then set it in place
				// of 10
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, category);

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				String message = hMap.get("message");
				mav.addObject("hideMap", mapConfig);

				localGovtBodyForm.setOperation("LBGOCHN");
				List<GovernmentOrderTemplate> templateListNew = new ArrayList<GovernmentOrderTemplate>();
				templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);

				if (category == 'U') {
					if (govtOrderConfig != null && mapConfig != null) {
						localGovtBodyForm.setLgd_LBlevelChk('U');
						localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category, stateCode);
						localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
						model.addAttribute("localBodyDetails", lbDetails);
						mav.addObject("govtOrderConfig", govtOrderConfig);
						mav.addObject("templateList", templateListNew);
						mav.addObject("localbodyNameEnghidden", localbodyNameEng);
						mav.addObject("localbodyNameAliasEnghidden", localbodyNameAliasEng);
						mav.addObject("warningFlag", warningFlag);
						mav.addObject("disturbedFlag", disturbedFlag);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					} else {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
					}

				} else {
					districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, category, level);

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
						if (govtOrderConfig != null && mapConfig != null) {

							localGovtBodyForm.setLgd_LBlevelChk('D');
							List<UnmappedLBList> unMappedPanchayatList = localGovtBodyService.getUnmappedLBDistList('D', stateCode);
							List<PartillyMappedLRList> partillyMappedPanchayatList = localGovtBodyService.getPartillymappedLBDistList('D', stateCode, category);
							model.addAttribute("localBodyUnmappedAreaList", unMappedPanchayatList);
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							model.addAttribute("landRegionPartiallyUnmapList", partillyMappedPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							mav.addObject("localbodyNameEnghidden", localbodyNameEng);
							mav.addObject("localbodyNameAliasEnghidden", localbodyNameAliasEng);
							mav.addObject("warningFlag", warningFlag);
							mav.addObject("disturbedFlag", disturbedFlag);
							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}

					}
					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
						if (govtOrderConfig != null && mapConfig != null) {

							localGovtBodyForm.setLgd_LBlevelChk('I');
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							mav.addObject("localbodyNameEnghidden", localbodyNameEng);
							mav.addObject("localbodyNameAliasEnghidden", localbodyNameAliasEng);
							mav.addObject("warningFlag", warningFlag);
							mav.addObject("disturbedFlag", disturbedFlag);
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}
					}

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('V');
							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							mav.addObject("localbodyNameEnghidden", localbodyNameEng);
							mav.addObject("localbodyNameAliasEnghidden", localbodyNameAliasEng);
							mav.addObject("warningFlag", warningFlag);
							mav.addObject("disturbedFlag", disturbedFlag);
							model.addAttribute("localBodyDetails", lbDetails);

							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}

					}
				}

				localGovtBodyForm.setLbType(category);
				mav.addObject("category", category);
				if (attachmentList != null) {
					mav.addObject("govtfilecount", attachmentList.size());
				}
				if (mapAttachmentList != null) {
					mav.addObject("mapfilecount", mapAttachmentList.size());
				}
				localGovtBodyForm.setLocalBodyDetails(lbDetails);
				if (session.getAttribute("validationErrorOne") != null) {
					String validateAttachment = (String) session.getAttribute("validationErrorOne");
					request.setAttribute("validationErrorOne", validateAttachment);
				}
				if (session.getAttribute("validationErrorOne1") != null) {
					String validateAttachment = (String) session.getAttribute("validationErrorOne1");
					request.setAttribute("validationErrorOne1", validateAttachment);
				}
				session.removeAttribute("validationErrorOne");
				session.removeAttribute("validationErrorOne1");
				model.addAttribute("attachmentList", attachmentList);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				String aMessage = "Sorry Data Not Found For Your Selection ";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				return mav;
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

	/**
	 * MOdified by sushil on 09-01-2013 for govt order upload or generate
	 * template Declare Lbdetails,districtPanchayatList variable in manage Name
	 * of Local Body Details.
	 * 
	 * @param localGovtBodyForm
	 * @param result
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyGovtLocalBodyMainforname", method = RequestMethod.GET)
	public ModelAndView modifyUrbanLocalBodyTypeForName(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		String id = request.getParameter("id");
		char type = ' ';
		int operationCode = 0;
		String localbodyNameEng = null;
		String localbodyNameAliasEng = null;
		String parentLBCode = null;
		// String lgdLBTypeName = request.getParameter("lgdLBTypeName");
		// //geting the parameter value from last jsp and field hi hiddend there
		// by kirandeep 14/1/2014

		if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}
		int localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		char category = (type != ' ') ? type : localGovtBodyForm.getParentCategory();
		if (category == 'P') {
			operationCode = 45;
			localGovtBodyForm.setOperationCode(operationCode);
		} else if (category == 'T') {
			operationCode = 47;
			localGovtBodyForm.setOperationCode(operationCode);
		} else {
			operationCode = 46;
			localGovtBodyForm.setOperationCode(operationCode);
		}

		// int localBodyCode=localGovtBodyForm.getParentwiseId();
		// char category=localGovtBodyForm.getParentCategory();

		try {
			@SuppressWarnings("unused")
			int lbTypeCode = 0;
			// @SuppressWarnings("unused")
			// int localBodyCode1 = localGovtBodyForm.getLocalBodyCode();
			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();

			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			@SuppressWarnings("unused")
			List<Localbody> localbodyObj = null;
			mav = new ModelAndView("modify_govt_local_bodyforname");
			
			// GetGovernmentOrderDetail government = new
			// GetGovernmentOrderDetail();
			List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
			// List<GetGovernmentOrderDetail> govtList =
			// govtOrderService.getGovtDetailsbyEntityCodeandVersion(localBodyCode,
			// lbDetails.get(0).getLocalBodyVersion(), 'G');

			// EsapiEncoder.encode(lbDetails);
			List<Attachment> attachmentList = null;
			List<MapAttachment> mapAttachmentList = null;
			localGovtBodyForm.setLocalBodyCode(localBodyCode);
			if (!lbDetails.isEmpty()) {
				char level = lbDetails.get(0).getLevel().charAt(0);
				// attachmentList =
				// getAttachmentListbyLocalBody(localGovtBodyForm,
				// localBodyCode, lbDetails, request);

				// mapAttachmentList =
				// getMapAttachmentListbyLocalBody(localGovtBodyForm,
				// localBodyCode, lbDetails, request);
				// EsapiEncoder.encode(mapAttachmentList);
				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);

				lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();

				localbodyNameEng = lbDetails.get(0).getLocalBodyNameEnglish();
				localbodyNameAliasEng = lbDetails.get(0).getAliasNameEnglish();
				if (category != 'U') {
					if (lbDetails.get(0).getLevel().equalsIgnoreCase("I") || lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
						parentLBCode = lbDetails.get(0).getParentLocalBodyCode().toString();
						localGovtBodyForm.setParentLBCode(parentLBCode);
					}
				}
				localGovtBodyForm.setLocalbodyNameEnghidden(localbodyNameEng);
				localGovtBodyForm.setLocalbodyNameAliasEnghidden(localbodyNameAliasEng);

				Map<String, String> hMap = new HashMap<String, String>();

				// Please first check your operation code then set it in place
				// of 10
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, category);

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				String message = hMap.get("message");
				/* added by sushil on 20-02-2013 */
				localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);

				localGovtBodyForm.setOperation("LBCCNM");
				List<GovernmentOrderTemplate> templateListNew = new ArrayList<GovernmentOrderTemplate>();
				templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);

				if (category == 'U') {
					if (govtOrderConfig != null && mapConfig != null) {
						localGovtBodyForm.setLgd_LBlevelChk('U');

						localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category, stateCode);
						localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
						model.addAttribute("localBodyDetails", lbDetails);
						// model.addAttribute("govtList", govtList);
						mav.addObject("govtOrderConfig", govtOrderConfig);
						mav.addObject("templateList", templateListNew);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					} else {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
					}

				} else {
					districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, category, level);

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('D');
							localGovtBodyForm.setHiddenLevel(lbDetails.get(0).getLevel());
							// List<UnmappedLBList> unMappedPanchayatList =
							// localGovtBodyService.getUnmappedLBDistList('D',
							// stateCode);
							// List<PartillyMappedLRList>
							// partillyMappedPanchayatList =
							// localGovtBodyService.getPartillymappedLBDistList('D',
							// stateCode, category);
							// model.addAttribute("localBodyUnmappedAreaList",
							// unMappedPanchayatList);
							// model.addAttribute("govtList", govtList);
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							// model.addAttribute("landRegionPartiallyUnmapList",
							// partillyMappedPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}
					}
					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('I');
							localGovtBodyForm.setHiddenLevel(lbDetails.get(0).getLevel());
							model.addAttribute("localBodyDetails", lbDetails);
							// model.addAttribute("govtList", govtList);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}

					}

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('V');
							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
							localGovtBodyForm.setHiddenLevel(lbDetails.get(0).getLevel());

							model.addAttribute("districtPanchayatList", districtPanchayatList);
							model.addAttribute("localBodyDetails", lbDetails);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							// model.addAttribute("govtList", govtList);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}
					}
				}

				/* Added by sushil on 21-11-2014 */
				// cValidatorModify.modifyValidationChangeName(localGovtBodyForm,
				// result);
				// if (result.hasErrors()) {
				// mav = new ModelAndView("modify_govt_local_bodyforname");
				// return mav;
				// }
				/* end by sushil on 21-11-2014 */
				localGovtBodyForm.setLbType(category);
				localGovtBodyForm.setHiddenLbType(Character.toString(category));

				localGovtBodyForm.setLocalBodyDetails(lbDetails);
				if (session.getAttribute("validationErrorOne") != null) {
					String validateAttachment = (String) session.getAttribute("validationErrorOne");
					request.setAttribute("validationErrorOne", validateAttachment);
				}
				if (session.getAttribute("validationErrorOne1") != null) {
					String validateAttachment = (String) session.getAttribute("validationErrorOne1");
					request.setAttribute("validationErrorOne1", validateAttachment);
				}
				session.removeAttribute("validationErrorOne");
				session.removeAttribute("validationErrorOne1");
				model.addAttribute("attachmentList", attachmentList);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				String aMessage = "Sorry Data Not Found For Your Selection ";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				return mav;
			}
		} catch (Exception e) {
			log.error(e);

			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		model.addAttribute("lgdLBTypeName", localGovtBodyForm.getLgd_LBTypeName());// adding
																					// the
																					// attribute
																					// to
																					// jsp
																					// by
																					// Kirandeep
																					// date
																					// :
																					// 14/01/2014
		return mav;
	}

	/**
	 * Added by sushil on 21-11-2014 for govt order effective date validation
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/modifyGovtLocalBodyMainforname", method = RequestMethod.POST)
	public ModelAndView changeLocalbodyName(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		String id = request.getParameter("id");
		char type = ' ';
		int operationCode = 0;
		String localbodyNameEng = null;
		String localbodyNameAliasEng = null;
		String parentLBCode = null;
		if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}
		int localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		char category = (type != ' ') ? type : localGovtBodyForm.getParentCategory();
		if (category == 'P') {
			operationCode = 45;
			localGovtBodyForm.setOperationCode(operationCode);
		} else if (category == 'T') {
			operationCode = 47;
			localGovtBodyForm.setOperationCode(operationCode);
		} else {
			operationCode = 46;
			localGovtBodyForm.setOperationCode(operationCode);
		}

		try {
			int lbTypeCode = 0;
			int localBodyCode1 = localGovtBodyForm.getLocalBodyCode();
			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();

			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			List<Localbody> localbodyObj = null;
			mav = new ModelAndView("modify_govt_local_bodyforname");
			
			List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
			List<Attachment> attachmentList = null;
			List<MapAttachment> mapAttachmentList = null;
			localGovtBodyForm.setLocalBodyCode(localBodyCode);
			if (!lbDetails.isEmpty()) {
				char level = lbDetails.get(0).getLevel().charAt(0);

				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);

				lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();

				localbodyNameEng = lbDetails.get(0).getLocalBodyNameEnglish();
				localbodyNameAliasEng = lbDetails.get(0).getAliasNameEnglish();
				if (category != 'U') {
					if (lbDetails.get(0).getLevel().equalsIgnoreCase("I") || lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
						parentLBCode = lbDetails.get(0).getParentLocalBodyCode().toString();
						localGovtBodyForm.setParentLBCode(parentLBCode);
					}
				}
				localGovtBodyForm.setLocalbodyNameEnghidden(localbodyNameEng);
				localGovtBodyForm.setLocalbodyNameAliasEnghidden(localbodyNameAliasEng);

				Map<String, String> hMap = new HashMap<String, String>();

				// Please first check your operation code then set it in place
				// of 10
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, category);

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				String message = hMap.get("message");
				/* added by sushil on 20-02-2013 */
				localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);

				localGovtBodyForm.setOperation("LBCCNM");
				List<GovernmentOrderTemplate> templateListNew = new ArrayList<GovernmentOrderTemplate>();
				templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);

				if (category == 'U') {
					if (govtOrderConfig != null && mapConfig != null) {
						localGovtBodyForm.setLgd_LBlevelChk('U');

						localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category, stateCode);
						localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
						model.addAttribute("localBodyDetails", lbDetails);
						// model.addAttribute("govtList", govtList);
						mav.addObject("govtOrderConfig", govtOrderConfig);
						mav.addObject("templateList", templateListNew);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					} else {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
					}
				} else {
					districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, category, level);

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('D');
							localGovtBodyForm.setHiddenLevel(lbDetails.get(0).getLevel());

							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}
					}
					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('I');
							localGovtBodyForm.setHiddenLevel(lbDetails.get(0).getLevel());
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}

					}

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('V');
							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
							localGovtBodyForm.setHiddenLevel(lbDetails.get(0).getLevel());

							model.addAttribute("districtPanchayatList", districtPanchayatList);
							model.addAttribute("localBodyDetails", lbDetails);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}
					}
				}

				/* Added by sushil on 21-11-2014 */
				cValidatorModify.modifyValidationChangeName(localGovtBodyForm, result);
				if (result.hasErrors()) {
					mav = new ModelAndView("modify_govt_local_bodyforname");
					return mav;
				}
				/* end by sushil on 21-11-2014 */
				localGovtBodyForm.setLbType(category);
				localGovtBodyForm.setHiddenLbType(Character.toString(category));

				localGovtBodyForm.setLocalBodyDetails(lbDetails);
				if (session.getAttribute("validationErrorOne") != null) {
					String validateAttachment = (String) session.getAttribute("validationErrorOne");
					request.setAttribute("validationErrorOne", validateAttachment);
				}
				if (session.getAttribute("validationErrorOne1") != null) {
					String validateAttachment = (String) session.getAttribute("validationErrorOne1");
					request.setAttribute("validationErrorOne1", validateAttachment);
				}
				session.removeAttribute("validationErrorOne");
				session.removeAttribute("validationErrorOne1");
				model.addAttribute("attachmentList", attachmentList);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				String aMessage = "Sorry Data Not Found For Your Selection ";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		model.addAttribute("lgdLBTypeName", localGovtBodyForm.getLgd_LBTypeName());
		return mav;
	}

	// Modified by Arnab on 7/02/2013
	/*
	 * Declare Lbdetails,districtPanchayatList,localBodyTypelist variable for
	 * manage PRI localBody Details:Update Local Body Coverage Area.
	 */
	@RequestMapping(value = "/modifyGovtLocalBodyMainforcoveragearea")
	public ModelAndView modifyUrbanLocalBodyTypeforcoveragearea(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {

		ModelAndView mav = null;
		List<UnLRSWiseList> statewiseunmappedList = null;
		List<UnLRDistrictWiseList> districtwiseunmappedList = null;
		List<ViewLocalBodyLandRegion> subdisticnamelist = null;
		List<ViewLocalBodyLandRegion> districtnamelist = null;
		List<ViewLocalBodyLandRegion> villagenamelist = null;
		String id = request.getParameter("id");
		/* Added by sushil on 24-11-2014 */
		if (id == null && localGovtBodyForm.getLocalBodyCode() != null) {
			id = localGovtBodyForm.getLocalBodyCode().toString();
		}
		String localBCode = (String) session.getAttribute("localBCode");
		char type = ' ';
		int operationCode = 0;
		//String test = null;
		/*
		 * values added in for the GTA in manage GTA author Ashish Dhupia , Date
		 * : 23/07/2014
		 */
		String getGtaInterPanch = (String) session.getAttribute("getGtaInterPanch");
		String getGtaList = (String) session.getAttribute("getGtaList");

		String Selectedvalue1 = (String) session.getAttribute("Selected");
		String Catageryforwad1 = (String) session.getAttribute("Catagery");
		
		if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}

		Integer localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		char category = (type != ' ') ? type : localGovtBodyForm.getParentCategory();
		/* Added by sushil on 24-11-2014 */
		if (category == ' ' || category == '\u0000') {
			category = localGovtBodyForm.getLbType();
		}

		if (category == 'P') {
			operationCode = 50;
			localGovtBodyForm.setOperationCode(operationCode);
		} else if (category == 'T') {
			operationCode = 52;
			localGovtBodyForm.setOperationCode(operationCode);
		} else {
			operationCode = 51;
			localGovtBodyForm.setOperationCode(operationCode);
		}

		// int localBodyCode=localGovtBodyForm.getParentwiseId();
		// char category=localGovtBodyForm.getParentCategory();

		try {
			int lbTypeCode = 0;
			@SuppressWarnings("unused")
			char level = ' ';
			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
			session.setAttribute("Selectedvalue", Selectedvalue1);
			session.setAttribute("Catageryforwad", Catageryforwad1);

			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);

			
			if (lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
				districtnamelist = localGovtBodyService.viewLandRegionDistrictName(localBodyCode);
				subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
				villagenamelist = localGovtBodyService.viewLandRegionvillageNameF(localBodyCode);
				if (districtnamelist.size() == 0) {
					districtnamelist = null;
				}
				if (subdisticnamelist.size() == 0) {
					subdisticnamelist = null;
				}
				if (villagenamelist.size() == 0) {
					villagenamelist = null;
				}
			} else if (lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
				/*
				 * values added in session for the GTA in manage GTA author
				 * Ashish Dhupia , Date : 23/07/2014
				 */
				session.setAttribute("localBCode", getGtaList);
				model.addAttribute("localBCode", getGtaList);
				subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
				villagenamelist = localGovtBodyService.viewLandRegionvillageNameF(localBodyCode);
				if (subdisticnamelist.size() == 0) {
					subdisticnamelist = null;
				}
				if (villagenamelist.size() == 0) {
					villagenamelist = null;
				}

			} else if (lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
				/*
				 * values added in session according to condition for the GTA
				 * (manage localbody PRI) author Ashish Dhupia , Date :
				 * 23/07/2014
				 */
				session.setAttribute("localBCode", getGtaInterPanch);
				model.addAttribute("localBCode", getGtaInterPanch);
				villagenamelist = localGovtBodyService.viewLandRegionvillageNameF(localBodyCode);
				if (villagenamelist.size() == 0) {
					villagenamelist = null;
				}
			}
			if (category == 'U') {
				// Conditional Check whether Logged in state is ULB oprate
				// District wise. @author Vinay Yadav 23-12-2013
				if (ApplicationConstant.checkStateLBOnlyDisttWise(stateCode)) {
					subdisticnamelist = localGovtBodyService.viewLandRegionDistrictName(localBodyCode);
				} else {
					subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
				}

			}

			List<ViewLandRegionDisturbedlist> gpdisturbedlist = localGovtBodyService.viewGpdisturbedlist(localBodyCode);

			// if (districtnamelist.size() != 0 || subdisticnamelist.size() != 0
			// || villagenamelist.size() != 0)
			if (districtnamelist != null || subdisticnamelist != null || villagenamelist != null) {
				// List<Localbody> localbodyObj = null;

				mav = new ModelAndView("modify_govt_local_bodyforcoveragearea");

				mav.addObject("selectBox", localGovtBodyForm.getLgd_LBTypeName());
				mav.addObject("stateCode", stateCode);

				// EsapiEncoder.encode(lbDetails);
				List<Attachment> attachmentList = null;
				List<MapAttachment> mapAttachmentList = null;
				localGovtBodyForm.setLocalBodyCode(localBodyCode);
				if (!lbDetails.isEmpty()) {

					// attachmentList =
					// getAttachmentListbyLocalBody(localGovtBodyForm,
					// localBodyCode, lbDetails, request);

					// mapAttachmentList =
					// getMapAttachmentListbyLocalBody(localGovtBodyForm,
					// localBodyCode, lbDetails, request);
					// EsapiEncoder.encode(mapAttachmentList);
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);

					lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();

					Map<String, String> hMap = new HashMap<String, String>();

					// Please first check your operation code then set it in
					// place
					// of 10
					hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, category);

					String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
					String mapConfig = hMap.get("mapUpload");// values==true,false
					String message = hMap.get("message");

					localGovtBodyForm.setOperation("LBCCH");
					List<GovernmentOrderTemplate> templateListNew = new ArrayList<GovernmentOrderTemplate>();
					templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);

					if (category == 'U') {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('U');

							localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category, stateCode);
							localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
							model.addAttribute("localBodyDetails", lbDetails);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							model.addAttribute("lgtLocalBodyType", localBodyTypelist);
							model.addAttribute("isDistrictLevel", ApplicationConstant.checkStateLBOnlyDisttWise(stateCode));// Conditional
																															// Check
																															// whether
																															// Logged
																															// in
																															// state
																															// is
																															// LB
																															// operate
																															// District
																															// wise.
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}

					} else {

						if (districtCode == 0) {
							districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategoryforselected(stateCode, category, localBodyCode);
						} else if (districtCode > 0) {
							districtPanchayatList = localGovtBodyService.getLandRegionByDistricCode(lbTypeCode, districtCode, Character.toString(category));
						}
						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
							if (govtOrderConfig != null && mapConfig != null) {
								localGovtBodyForm.setLgd_LBlevelChk('D');

								if (districtCode == 0) {
									statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('D', stateCode);
									model.addAttribute("UnmappedData", statewiseunmappedList);
								} else if (districtCode > 0) {
									districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('D', districtCode);
									model.addAttribute("UnmappedData", districtwiseunmappedList);
								}

								// List<UnmappedLBList> unMappedPanchayatList =
								// localGovtBodyService.getUnmappedLBDistList('D',
								// stateCode);
								// List<PartillyMappedLRList>
								// partillyMappedPanchayatList =
								// localGovtBodyService.getPartillymappedLBDistList('D',
								// stateCode, category);
								// model.addAttribute("localBodyUnmappedAreaList",
								// unMappedPanchayatList);
								model.addAttribute("localBodyDetails", lbDetails);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								mav.addObject("templateList", templateListNew);
								model.addAttribute("districtPanchayatList", districtPanchayatList);
								// model.addAttribute("landRegionPartiallyUnmapList",
								// partillyMappedPanchayatList);
								if (category == 'T') {
									localGovtBodyForm.setLgd_LBNomenclatureDist("District Council");
								} else {
									localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
								}
							} else {
								mav = new ModelAndView("success");
								mav.addObject("msgid", message);
							}
						}
						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
							if (govtOrderConfig != null && mapConfig != null) {
								localGovtBodyForm.setLgd_LBlevelChk('I');
								if (districtCode == 0) {
									statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('T', stateCode);
									model.addAttribute("UnmappedData", statewiseunmappedList);

								} else if (districtCode > 0) {
									districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('T', districtCode);
									model.addAttribute("UnmappedData", districtwiseunmappedList);
								}

								model.addAttribute("localBodyDetails", lbDetails);
								model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
								model.addAttribute("districtPanchayatList", districtPanchayatList);
								mav.addObject("templateList", templateListNew);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								if (category == 'T') {
									localGovtBodyForm.setLgd_LBNomenclatureInter("Block Advisory Council");
								} else {
									localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
								}
							} else {
								mav = new ModelAndView("success");
								mav.addObject("msgid", message);
							}

						}

						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
							if (govtOrderConfig != null && mapConfig != null) {
								localGovtBodyForm.setLgd_LBlevelChk('V');
								if (districtCode == 0) {
									statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('V', stateCode);
									model.addAttribute("UnmappedData", statewiseunmappedList);
								} else if (districtCode > 0) {
									districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('V', districtCode);
									model.addAttribute("UnmappedData", districtwiseunmappedList);
								}
								if (category == 'T') {
									localGovtBodyForm.setLgd_LBNomenclatureDist("District Council");
									localGovtBodyForm.setLgd_LBNomenclatureInter("Block Advisory Council");
									localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Council");
								} else {
									localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
									localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
									localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
								}
								model.addAttribute("districtPanchayatList", districtPanchayatList);
								model.addAttribute("localBodyDetails", lbDetails);
								mav.addObject("templateList", templateListNew);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							} else {
								mav = new ModelAndView("success");
								mav.addObject("msgid", message);
							}
						}
					}

					localGovtBodyForm.setLbType(category);
					localGovtBodyForm.setLocalBodyDetails(lbDetails);
					if (session.getAttribute("validationErrorOne") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					if (session.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne1", validateAttachment);
					}
					session.setAttribute("lbTypehidden", localGovtBodyForm.getLgd_LBTypeName());

					session.setAttribute("lbcodeHidden", localBodyCode.toString());
					session.setAttribute("lbCategoryHidden", Character.toString(category));
					session.removeAttribute("validationErrorOne");
					session.removeAttribute("validationErrorOne1");
					model.addAttribute("levelcheck", localGovtBodyForm.getLgd_LBlevelChk());

					model.addAttribute("attachmentList", attachmentList);
					model.addAttribute("districtnamelist", districtnamelist);
					model.addAttribute("districtnamelistHidden", districtnamelist);
					model.addAttribute("subdisticnamelist", subdisticnamelist);
					model.addAttribute("subdisticnamelistHidden", subdisticnamelist);
					model.addAttribute("villagenamelist", villagenamelist);
					model.addAttribute("villagenamelistHidden", villagenamelist);
					model.addAttribute("gpdisturbedlist", gpdisturbedlist);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("lgdLBTypeName", localGovtBodyForm.getLgd_LBTypeName());
					/*
					 * values added in the same variable according to condition
					 * for the GTA (manage localbody PRI) author Ashish Dhupia ,
					 * Date : 23/07/2014
					 */
					if (stateCode == 19) {

						boolean gta_value = true;
						if (getGtaInterPanch != null && !"".equalsIgnoreCase(getGtaInterPanch)) {
							gta_value = false;
							model.addAttribute("localBCode", getGtaInterPanch);
						}
						if (getGtaList != null && !"".equalsIgnoreCase(getGtaList)) {
							if (gta_value) {
								gta_value = false;
								model.addAttribute("localBCode", getGtaList);
							}
						}

						else {
							if (gta_value && stateCode == 19) {
								model.addAttribute("localBCode", localBCode);
							}

						}
					} else
						model.addAttribute("localBCode", localBCode);

					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					session.removeAttribute("getGtaInterPanch");
					session.removeAttribute("getGtaList");

				}
			} else {

				mav = new ModelAndView("redirect:/ForwadCorrectcoveredareaLocalBody.htm");
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				session.setAttribute("lbDistrictAtInter", localGovtBodyForm.getLgd_LBDistrictAtInter());
				session.setAttribute("lbDistrictAtVillage", localGovtBodyForm.getLgd_LBDistrictAtVillage());
				return mav;
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

	// Added by Arnab on 25/07/2013 for Disturbed Functionality
	/*
	 * Declare Lbdetails,districtPanchayatList,localBodyTypelist variable for
	 * manage PRI localBody Details:Update Local Body Coverage Area.
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/modifyGovtLocalBodyMainforcoverageareaDisturbed")
	public ModelAndView modifyUrbanLocalBodyTypeforcoverageareaDisturbed(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {

		ModelAndView mav = null;
		List<GetLocalbodyDisturbRegion> messageDisturbed = new ArrayList<GetLocalbodyDisturbRegion>();
		List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		List<UnLRSWiseList> statewiseunmappedList = null;
		List<UnLRDistrictWiseList> districtwiseunmappedList = null;
		List<ViewLocalBodyLandRegion> subdisticnamelist = null;
		List<ViewLocalBodyLandRegion> districtnamelist = null;
		List<ViewLocalBodyLandRegion> villagenamelist = null;
		String id = request.getParameter("id");
		// String localBCode = (String) session.getAttribute("localBCode");
		char type = ' ';
		int operationCode = 0;
		String Selectedvalue1 = (String) session.getAttribute("Selected");
		String Catageryforwad1 = (String) session.getAttribute("Catagery");
		
		if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}

		if (session.getAttribute("formbean") != null) {
			session.removeAttribute("formbean");
			session.removeValue("formbean");
		}

		Integer localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();

		char category = (type != ' ') ? type : localGovtBodyForm.getParentCategory();
		session.setAttribute("categoryHidden", Character.toString(category));
		operationCode = 67;
		localGovtBodyForm.setOperationCode(operationCode);

		try {
			int lbTypeCode = 0;
			@SuppressWarnings("unused")
			char level = ' ';

			Integer parentLblc = localGovtBodyService.getParentLblccode(localBodyCode);

			// String localBCode = (String) session.getAttribute("parentLblc");

			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
			session.setAttribute("Selectedvalue", Selectedvalue1);
			session.setAttribute("Catageryforwad", Catageryforwad1);

			session.setAttribute("LbCodeHiddenDisturbed", localBodyCode.toString());

			messageDisturbed = disturbedEntitiesService.getMessagebyLBCode(localBodyCode);

			String finalMessageDisturbed = messageDisturbed.get(0).getGetLocalbodyDisturbRegionfn();

			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);

			if (lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
				districtnamelist = localGovtBodyService.viewLandRegionDistrictName(localBodyCode);
				subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
				villagenamelist = localGovtBodyService.viewLandRegionvillageNameF(localBodyCode);
			} else if (lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
				subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
				villagenamelist = localGovtBodyService.viewLandRegionvillageNameF(localBodyCode);
			} else if (lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
				villagenamelist = localGovtBodyService.viewLandRegionvillageNameF(localBodyCode);
			}
			if (category == 'U') {
				subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
			}

			List<ViewLandRegionDisturbedlist> gpdisturbedlist = localGovtBodyService.viewGpdisturbedlist(localBodyCode);

			/*
			 * if (districtnamelist.size() != 0 || subdisticnamelist.size() != 0
			 * || villagenamelist.size() != 0)
			 */
			if (districtnamelist != null || subdisticnamelist != null || villagenamelist != null) {
				// List<Localbody> localbodyObj = null;
				mav = new ModelAndView("modify_govt_local_bodyforcoveragearea_disturbed");
				
				// mav.addObject("selectBox",
				// localGovtBodyForm.getLgd_LBTypeName());

				getLocalGovtSetupList = localGovtSetupService.isStateSetupDisturbed(stateCode, category, lbDetails.get(0).getLevel().charAt(0));

				String finalData = "";

				finalData = getLocalGovtSetupList.get(0).getLocalBodyTypeCode() + ":" + getLocalGovtSetupList.get(0).getLevel() + ":" + getLocalGovtSetupList.get(0).getCategory() + ":" + getLocalGovtSetupList.get(0).getTierSetupCode() + ":"
						+ getLocalGovtSetupList.get(0).getParentTierSetupCode();
				if (finalData != "") {
					mav.addObject("selectBox", finalData);
				}

				session.setAttribute("SelectedvalueMoved", finalData);

				mav.addObject("stateCode", stateCode);

				// EsapiEncoder.encode(lbDetails);
				List<Attachment> attachmentList = null;
				List<MapAttachment> mapAttachmentList = null;
				localGovtBodyForm.setLocalBodyCode(localBodyCode);
				if (!lbDetails.isEmpty()) {

					// attachmentList =
					// getAttachmentListbyLocalBody(localGovtBodyForm,
					// localBodyCode, lbDetails, request);

					// mapAttachmentList =
					// getMapAttachmentListbyLocalBody(localGovtBodyForm,
					// localBodyCode, lbDetails, request);
					// EsapiEncoder.encode(mapAttachmentList);
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);

					lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();

					Map<String, String> hMap = new HashMap<String, String>();

					// Please first check your operation code then set it in
					// place
					// of 10
					hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, category);

					String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
					String mapConfig = hMap.get("mapUpload");// values==true,false
					String message = hMap.get("message");

					localGovtBodyForm.setOperation("LBCCH");
					List<GovernmentOrderTemplate> templateListNew = new ArrayList<GovernmentOrderTemplate>();
					templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);

					if (category == 'U') {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('U');

							localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category, stateCode);
							localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
							model.addAttribute("localBodyDetails", lbDetails);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							model.addAttribute("lgtLocalBodyType", localBodyTypelist);
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}

					} else {

						if (districtCode == 0) {
							districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategoryforselected(stateCode, category, localBodyCode);
						} else if (districtCode > 0) {
							districtPanchayatList = localGovtBodyService.getLandRegionByDistricCode(lbTypeCode, districtCode, Character.toString(category));
						}
						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
							if (govtOrderConfig != null && mapConfig != null) {
								localGovtBodyForm.setLgd_LBlevelChk('D');

								if (districtCode == 0) {
									statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('D', stateCode);
									model.addAttribute("UnmappedData", statewiseunmappedList);
								} else if (districtCode > 0) {
									districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('D', districtCode);
									model.addAttribute("UnmappedData", districtwiseunmappedList);
								}

								// List<UnmappedLBList> unMappedPanchayatList =
								// localGovtBodyService.getUnmappedLBDistList('D',
								// stateCode);
								// List<PartillyMappedLRList>
								// partillyMappedPanchayatList =
								// localGovtBodyService.getPartillymappedLBDistList('D',
								// stateCode, category);
								// model.addAttribute("localBodyUnmappedAreaList",
								// unMappedPanchayatList);
								model.addAttribute("localBodyDetails", lbDetails);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								mav.addObject("templateList", templateListNew);
								model.addAttribute("districtPanchayatList", districtPanchayatList);
								// model.addAttribute("landRegionPartiallyUnmapList",
								// partillyMappedPanchayatList);

								localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
							} else {
								mav = new ModelAndView("success");
								mav.addObject("msgid", message);
							}
						}
						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
							if (govtOrderConfig != null && mapConfig != null) {
								localGovtBodyForm.setLgd_LBlevelChk('I');
								if (districtCode == 0) {
									statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('T', stateCode);
									model.addAttribute("UnmappedData", statewiseunmappedList);

								} else if (districtCode > 0) {
									districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('T', districtCode);
									model.addAttribute("UnmappedData", districtwiseunmappedList);
								}

								model.addAttribute("localBodyDetails", lbDetails);
								model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
								model.addAttribute("districtPanchayatList", districtPanchayatList);
								mav.addObject("templateList", templateListNew);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
							} else {
								mav = new ModelAndView("success");
								mav.addObject("msgid", message);
							}

						}

						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
							if (govtOrderConfig != null && mapConfig != null) {
								localGovtBodyForm.setLgd_LBlevelChk('V');
								if (districtCode == 0) {
									statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('V', stateCode);
									model.addAttribute("UnmappedData", statewiseunmappedList);
								} else if (districtCode > 0) {
									districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('V', districtCode);
									model.addAttribute("UnmappedData", districtwiseunmappedList);
								}
								localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
								localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
								localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
								model.addAttribute("districtPanchayatList", districtPanchayatList);
								model.addAttribute("localBodyDetails", lbDetails);
								mav.addObject("templateList", templateListNew);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							} else {
								mav = new ModelAndView("success");
								mav.addObject("msgid", message);
							}
						}
					}

					localGovtBodyForm.setLbType(category);
					localGovtBodyForm.setLocalBodyDetails(lbDetails);

					// session.setAttribute("formbean", localGovtBodyForm);

					if (session.getAttribute("validationErrorOne") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					if (session.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne1", validateAttachment);
					}
					session.setAttribute("lbTypehidden", localGovtBodyForm.getLgd_LBTypeName());

					session.setAttribute("lbcodeHidden", localBodyCode.toString());
					session.setAttribute("lbCategoryHidden", Character.toString(category));
					session.removeAttribute("validationErrorOne");
					session.removeAttribute("validationErrorOne1");
					model.addAttribute("levelcheck", localGovtBodyForm.getLgd_LBlevelChk());
					model.addAttribute("attachmentList", attachmentList);
					model.addAttribute("districtnamelist", districtnamelist);
					model.addAttribute("subdisticnamelist", subdisticnamelist);
					model.addAttribute("villagenamelist", villagenamelist);
					model.addAttribute("gpdisturbedlist", gpdisturbedlist);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("messageDisturbed", finalMessageDisturbed);
					model.addAttribute("lgdLBTypeName", localGovtBodyForm.getLgd_LBTypeName());

					model.addAttribute("localBCode", parentLblc);

					mav.addObject("localGovtBodyForm", localGovtBodyForm);
				}
			} else {
				return mav = new ModelAndView("redirect:ForwadCorrectcoveredareaLocalBody.htm");
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

	// Added by Arnab on 25/07/2013 for Disturbed Functionality
	@SuppressWarnings("deprecation")
	/*
	 * Declare Lbdetails,districtPanchayatList variable for manage PRI localBody
	 * Details:Update Local Body Coverage Area Form.
	 */
	@RequestMapping(value = "/redirectToDisturbedPage", method = RequestMethod.POST)
	public ModelAndView redirectToDisturbedPage(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request)

	{
		ModelAndView mav = null;
		String localbodyNameEng = null;
		String localbodyNameAliasEng = null;

		//String check = null;
		int operationCode = 0;
		char lbType;
		String contDist = null;
		String contSubDist = null;
		String contVillage = null;
		String finalconSubDist = null;
		String finalLandregion = null;
		String contSubDistMapped = null;
		String contSubDistUnMapped = null;
		String contDistMapped = null;
		String contDistUnMapped = null;
		String contVillMapped = null;
		String contVillUnMapped = null;
		@SuppressWarnings("unused")
		List<Attachment> alreadyAttachList = new ArrayList<Attachment>();
		//List<MapAttachment> alreadyAttachMapList = new ArrayList<MapAttachment>();

		//int lbTypeCode = 0;
		try {

			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}

			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			Map<String, String> hMap = new HashMap<String, String>();
			operationCode = localGovtBodyForm.getOperationCode();
			lbType = localGovtBodyForm.getLbType();

			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			// String message = hMap.get("message");
			localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);
			// ////////code for government order
			// checking///////////////////////////////////
			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);

			if (localGovtBodyForm.getLbType() == 'U') {
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null) {
					List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyCovChangeULB(localGovtBodyForm);
					if (conSubDistrict != null) {
						Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
						StringBuffer finalcontSubDist = new StringBuffer();

						while (subdistItr.hasNext()) {
							finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
						}
						contSubDist = finalcontSubDist.toString();
						contSubDist = contSubDist.substring(0, finalcontSubDist.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingCovLanRegion(contSubDist);
					}
				}

				if (localGovtBodyForm.isLgd_LBExistCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null) {
						List<Subdistrict> conSubDistrictMapped = localGovtBodyService.getSubDistrictNamebyDisIDULBFinal(localGovtBodyForm);
						if (conSubDistrictMapped != null) {
							Iterator<Subdistrict> subdistMappedItr = conSubDistrictMapped.iterator();
							StringBuffer finalcontSubDistMapped = new StringBuffer();

							while (subdistMappedItr.hasNext()) {
								finalcontSubDistMapped.append(subdistMappedItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistMapped = finalcontSubDistMapped.toString();
							contSubDistMapped = contSubDistMapped.substring(0, finalcontSubDistMapped.length() - 1);
							// localGovtBodyForm.setContSubDistrict(contSubDist);
						}

					}
				}
				if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
						List<Subdistrict> conunmappedSubDistrict = localGovtBodyService.getunMappedSubDistrictNamebyDisIDULB(localGovtBodyForm);
						if (conunmappedSubDistrict != null) {
							Iterator<Subdistrict> subdistUnMappedItr = conunmappedSubDistrict.iterator();
							StringBuffer finalcontSubDistUnMapped = new StringBuffer();

							while (subdistUnMappedItr.hasNext()) {
								finalcontSubDistUnMapped.append(subdistUnMappedItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistUnMapped = finalcontSubDistUnMapped.toString();
							contSubDistUnMapped = contSubDistUnMapped.substring(0, finalcontSubDistUnMapped.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}
				}

				if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
						finalconSubDist = contSubDistMapped + "," + contSubDistUnMapped;
						localGovtBodyForm.setContSubDistrict(finalconSubDist);
					}
					if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
						finalconSubDist = contSubDistMapped;
						localGovtBodyForm.setContSubDistrict(finalconSubDist);
					}
				}
				if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
						finalconSubDist = contSubDistMapped;
						localGovtBodyForm.setContSubDistrict(finalconSubDist);
					}
				}
				if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
						finalconSubDist = contSubDistUnMapped;
						localGovtBodyForm.setContSubDistrict(finalconSubDist);
					}
				}
			}

			if ((localGovtBodyForm.getLbType() == 'P') || (localGovtBodyForm.getLbType() == 'T')) {
				/*
				 * if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {
				 */
				// For District Panchayat Available Land Regions of PRI and
				// Traditional Local Body----START
				if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null) {
					List<District> condistrict = localGovtBodyService.getDistrictNamebyCovChangePRITrad(localGovtBodyForm);
					if (condistrict != null) {
						Iterator<District> distItr = condistrict.iterator();
						StringBuffer finalcontDist = new StringBuffer();

						while (distItr.hasNext()) {
							finalcontDist.append(distItr.next().getDistrictNameEnglish().trim() + ",");
						}
						contDist = finalcontDist.toString();
						contDist = contDist.substring(0, finalcontDist.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingDistrict(contDist);
					}
				}
				if (localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null) {
					List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyCovChangePRITrad(localGovtBodyForm);
					if (conSubDistrict != null) {
						Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
						StringBuffer finalcontSubDist = new StringBuffer();

						while (subdistItr.hasNext()) {
							finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
						}
						contSubDist = finalcontSubDist.toString();
						contSubDist = contSubDist.substring(0, finalcontSubDist.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingSubDistrict(contSubDist);
					}
				}
				if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null) {
					List<Village> conVillage = localGovtBodyService.getVillageNamebyCovChangePRITrad(localGovtBodyForm);
					if (conVillage != null) {
						Iterator<Village> villItr = conVillage.iterator();
						StringBuffer finalcontVillage = new StringBuffer();

						while (villItr.hasNext()) {
							finalcontVillage.append(villItr.next().getVillageNameEnglish().trim() + ",");
						}
						contVillage = finalcontVillage.toString();
						contVillage = contVillage.substring(0, finalcontVillage.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingVillage(contVillage);
					}
				}
				// For District Panchayat Available Land Regions of PRI and
				// Traditional Local Body----END

				// For Intermediate Panchayat Available Land Regions of PRI and
				// Traditional Local Body----START
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null) {
					List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyCovChangePRITradInter(localGovtBodyForm);
					if (conSubDistrict != null) {
						Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
						StringBuffer finalcontSubDist = new StringBuffer();

						while (subdistItr.hasNext()) {
							finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
						}
						contSubDist = finalcontSubDist.toString();
						contSubDist = contSubDist.substring(0, finalcontSubDist.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingSubDistrict(contSubDist);
					}
				}
				if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null) {
					List<Village> conVillage = localGovtBodyService.getVillageNamebyCovChangePRITradInter(localGovtBodyForm);
					if (conVillage != null) {
						Iterator<Village> villItr = conVillage.iterator();
						StringBuffer finalcontVillage = new StringBuffer();

						while (villItr.hasNext()) {
							finalcontVillage.append(villItr.next().getVillageNameEnglish().trim() + ",");
						}
						contVillage = finalcontVillage.toString();
						contVillage = contVillage.substring(0, finalcontVillage.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingVillage(contVillage);
					}
				}
				// For Intermediate Panchayat Available Land Regions of PRI and
				// Traditional Local Body----END

				// For Village Panchayat Available Land Regions of PRI and
				// Traditional Local Body----START

				if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null) {
					List<Village> conVillage = localGovtBodyService.getVillageNamebyCovChangePRITradVillFinal(localGovtBodyForm);
					if (conVillage != null) {
						Iterator<Village> villItr = conVillage.iterator();
						StringBuffer finalcontVillage = new StringBuffer();

						while (villItr.hasNext()) {
							finalcontVillage.append(villItr.next().getVillageNameEnglish().trim() + ",");
						}
						contVillage = finalcontVillage.toString();
						contVillage = contVillage.substring(0, finalcontVillage.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingVillage(contVillage);
					}
				}

				// For Village Panchayat Available Land Regions of PRI and
				// Traditional Local Body----END

				if (localGovtBodyForm.isLgd_LBExistCheck()) {
					// For Mapped Land Regions of DIstrict PAnchayat of PRI and
					// Traditional Local Body----START
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null) {
						List<District> contDistrict = localGovtBodyService.getDistrictNamebyDisIDCovChange(localGovtBodyForm);
						if (contDistrict != null) {
							Iterator<District> distItr = contDistrict.iterator();
							StringBuffer finalcontDist = new StringBuffer();

							while (distItr.hasNext()) {
								finalcontDist.append(distItr.next().getDistrictNameEnglish().trim() + ",");
							}
							contDistMapped = finalcontDist.toString();
							contDistMapped = contDistMapped.substring(0, finalcontDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}

					if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null) {
						List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyDisIDCovChange(localGovtBodyForm);
						if (conSubDistrict != null) {
							Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
							StringBuffer finalcontSubDist = new StringBuffer();

							while (subdistItr.hasNext()) {
								finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistMapped = finalcontSubDist.toString();
							contSubDistMapped = contSubDistMapped.substring(0, finalcontSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContSubDistrict(contSubDist);
						}
					}

					if (localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null) {
						List<Village> conVillage = localGovtBodyService.getconVillageNamebyVillIDforChCov(localGovtBodyForm);
						if (conVillage != null) {
							Iterator<Village> subVillageItr = conVillage.iterator();
							StringBuffer finalcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillMapped = finalcontVillage.toString();
							contVillMapped = contVillMapped.substring(0, finalcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContVillage(contVillage);
						}
					}
					// For Mapped Land Regions of DIstrict Panchayat of PRI and
					// Traditional Local Body----END

					// For Mapped Land Regions of Intermediate Panchayat of PRI
					// and Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null) {
						List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyDisIDInter(localGovtBodyForm);
						if (conSubDistrict != null) {
							Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
							StringBuffer finalcontSubDist = new StringBuffer();

							while (subdistItr.hasNext()) {
								finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistMapped = finalcontSubDist.toString();
							contSubDistMapped = contSubDistMapped.substring(0, finalcontSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContSubDistrict(contSubDist);
						}
					}

					if (localGovtBodyForm.getLgd_LBVillageDestLatICA() != null) {
						List<Village> conVillage = localGovtBodyService.getconVillageNamebyVillIDVill(localGovtBodyForm);
						if (conVillage != null) {
							Iterator<Village> subVillageItr = conVillage.iterator();
							StringBuffer finalcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillMapped = finalcontVillage.toString();
							contVillMapped = contVillMapped.substring(0, finalcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContVillage(contVillage);
						}
					}

					// For Mapped Land Regions of Intermediate Panchayat of PRI
					// and Traditional Local Body----END

					// For Mapped Land Regions of Village Panchayat of PRI and
					// Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null) {
						List<Village> conVillage = localGovtBodyService.getconVillageNamebyVillIDVPFin(localGovtBodyForm);
						if (conVillage != null) {
							Iterator<Village> subVillageItr = conVillage.iterator();
							StringBuffer finalcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillMapped = finalcontVillage.toString();
							contVillMapped = contVillMapped.substring(0, finalcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContVillage(contVillage);
						}
					}

					// For Mapped Land Regions of Village Panchayat of PRI and
					// Traditional Local Body----END
				}

				if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {

					// For Un Mapped Land Regions of District Panchayat of PRI
					// and Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null) {
						List<District> contUnmappedDistrict = localGovtBodyService.getUnmappedDistrictNamebyDisID(localGovtBodyForm);
						if (contUnmappedDistrict != null) {
							Iterator<District> unMappeddistItr = contUnmappedDistrict.iterator();
							StringBuffer finalcontunMappedDist = new StringBuffer();

							while (unMappeddistItr.hasNext()) {
								finalcontunMappedDist.append(unMappeddistItr.next().getDistrictNameEnglish().trim() + ",");
							}
							contDistUnMapped = finalcontunMappedDist.toString();
							contDistUnMapped = contDistUnMapped.substring(0, finalcontunMappedDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);

						}
					}

					if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null) {
						List<Subdistrict> contUnmappedSubDistrict = localGovtBodyService.getUnmappedSubDistrictNamebyDisID(localGovtBodyForm);
						if (contUnmappedSubDistrict != null) {
							Iterator<Subdistrict> unMappedsubdistItr = contUnmappedSubDistrict.iterator();
							StringBuffer finalcontunMappedSubDist = new StringBuffer();

							while (unMappedsubdistItr.hasNext()) {
								finalcontunMappedSubDist.append(unMappedsubdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistMapped = finalcontunMappedSubDist.toString();
							contSubDistMapped = contSubDistMapped.substring(0, finalcontunMappedSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}
					if (localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						List<Village> conUnmappedVillage = localGovtBodyService.getconunmappedVillageNamebyVillageID(localGovtBodyForm);
						if (conUnmappedVillage != null) {
							Iterator<Village> subVillageItr = conUnmappedVillage.iterator();
							StringBuffer finalunmappedcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalunmappedcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillUnMapped = finalunmappedcontVillage.toString();
							contVillUnMapped = contVillUnMapped.substring(0, finalunmappedcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}

					// For Un Mapped Land Regions of District Panchayat of PRI
					// and Traditional Local Body----END

					// For Un Mapped Land Regions of Intermediate Panchayat of
					// PRI and Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null) {
						List<Subdistrict> contUnmappedSubDistrict = localGovtBodyService.getUnmappedSubDistrictNamebyDisIDforIP(localGovtBodyForm);
						if (contUnmappedSubDistrict != null) {
							Iterator<Subdistrict> unMappedsubdistItr = contUnmappedSubDistrict.iterator();
							StringBuffer finalcontunMappedSubDist = new StringBuffer();

							while (unMappedsubdistItr.hasNext()) {
								finalcontunMappedSubDist.append(unMappedsubdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistMapped = finalcontunMappedSubDist.toString();
							contSubDistMapped = contSubDistMapped.substring(0, finalcontunMappedSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}
					if (localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						List<Village> conUnmappedVillage = localGovtBodyService.getconunmappedVillageNamebyVillageIDforIP(localGovtBodyForm);
						if (conUnmappedVillage != null) {
							Iterator<Village> subVillageItr = conUnmappedVillage.iterator();
							StringBuffer finalunmappedcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalunmappedcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillUnMapped = finalunmappedcontVillage.toString();
							contVillUnMapped = contVillUnMapped.substring(0, finalunmappedcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}

					// For Un Mapped Land Regions of Intermediate Panchayat of
					// PRI and Traditional Local Body----END

					// For Un Mapped Land Regions of Village Panchayat of PRI
					// and Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						List<Village> conUnmappedVillage = localGovtBodyService.getconunmappedVillageNamebyVillageIDforVP(localGovtBodyForm);
						if (conUnmappedVillage != null) {
							Iterator<Village> subVillageItr = conUnmappedVillage.iterator();
							StringBuffer finalunmappedcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalunmappedcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillUnMapped = finalunmappedcontVillage.toString();
							contVillUnMapped = contVillUnMapped.substring(0, finalunmappedcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}
				}

				// For Un Mapped Land Regions of Village Panchayat of PRI and
				// Traditional Local Body----END
				// Appending Mapped and UnMapped Data of District
				// Panchayat----START

				if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						finalLandregion = contDistMapped + "," + contSubDistMapped + "," + contVillMapped + "," + contDistUnMapped + "," + contSubDistMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						finalLandregion = contDistMapped + "," + contSubDistMapped + "," + contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}
				if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						finalLandregion = contDistMapped + "," + contSubDistMapped + "," + contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}
				if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						finalLandregion = contDistUnMapped + "," + contSubDistMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}

				// Appending Mapped and UnMapped Data of District
				// Panchayat----END

				// Appending Mapped and UnMapped Data of Intermediate
				// Panchayat----START

				if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						finalLandregion = contSubDistMapped + "," + contVillMapped + "," + contSubDistMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						finalLandregion = contSubDistMapped + "," + contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}

				if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {

					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						finalLandregion = contSubDistMapped + "," + contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}

				if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {

					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() == null && localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						finalLandregion = contSubDistMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}
				// Appending Mapped and UnMapped Data of Intermediate
				// Panchayat----END

				// Appending Mapped and UnMapped Data of Village
				// Panchayat----START

				if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {

					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						finalLandregion = contVillMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						finalLandregion = contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						finalLandregion = contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					// Appending Mapped and UnMapped Data of Village
					// Panchayat----END

				}
				if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						finalLandregion = contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}

				}

			}

			if (localGovtBodyForm.getAttachFile1() != null) {
				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderforChangeCoverage(request, localGovtBodyForm, result, mav, session);
				session.setAttribute("validGovermentUpload", validFileGovtUpload);
			}

			String selectedvalue1 = (String) session.getAttribute("SelectedvalueMoved");
			String localbodycode = (String) session.getAttribute("LbCodeHiddenDisturbed");
			String category = (String) session.getAttribute("categoryHidden");

			List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(Integer.parseInt(localbodycode));

			if (!lbDetails.isEmpty()) {
				Integer parentLBCode = null;
				char level = lbDetails.get(0).getLevel().charAt(0);

				//lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();

				localbodyNameEng = lbDetails.get(0).getLocalBodyNameEnglish();
				localbodyNameAliasEng = lbDetails.get(0).getAliasNameEnglish();
				if (category.charAt(0) != 'U') {
					if (lbDetails.get(0).getLevel().equalsIgnoreCase("I") || lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
						parentLBCode = lbDetails.get(0).getParentLocalBodyCode();
						localGovtBodyForm.setParentLBCode(parentLBCode.toString());
					}
				}
				localGovtBodyForm.setLocalbodyNameEnghidden(localbodyNameEng);
				localGovtBodyForm.setLocalbodyNameAliasEnghidden(localbodyNameAliasEng);

				if (category.charAt(0) == 'U') {

					localGovtBodyForm.setLgd_LBlevelChk('U');

					localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category.charAt(0), stateCode);
					localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
					model.addAttribute("localBodyDetails", lbDetails);
					// model.addAttribute("govtList", govtList);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);

				} else {
					if (parentLBCode != null) {
						districtPanchayatList = localGovtBodyService.getPanchayatListbyParentCategoryFChangeTier(stateCode, category.charAt(0), level, parentLBCode);
						// districtPanchayatList =
						// localGovtBodyService.getPanchayatListbyStateandCategory(stateCode,
						// category.charAt(0), level);
					}

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {

						localGovtBodyForm.setLgd_LBlevelChk('D');
						localGovtBodyForm.setHiddenLevel(lbDetails.get(0).getLevel());
						// List<UnmappedLBList> unMappedPanchayatList =
						// localGovtBodyService.getUnmappedLBDistList('D',
						// stateCode);
						// List<PartillyMappedLRList>
						// partillyMappedPanchayatList =
						// localGovtBodyService.getPartillymappedLBDistList('D',
						// stateCode, category);
						// model.addAttribute("localBodyUnmappedAreaList",
						// unMappedPanchayatList);
						// model.addAttribute("govtList", govtList);
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("districtPanchayatList", districtPanchayatList);
						// model.addAttribute("landRegionPartiallyUnmapList",
						// partillyMappedPanchayatList);

						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");

					}
					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {

						localGovtBodyForm.setLgd_LBlevelChk('I');
						localGovtBodyForm.setHiddenLevel(lbDetails.get(0).getLevel());
						model.addAttribute("localBodyDetails", lbDetails);
						// model.addAttribute("govtList", govtList);
						model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						model.addAttribute("districtPanchayatList", districtPanchayatList);
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
					}

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {

						localGovtBodyForm.setLgd_LBlevelChk('V');
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
						localGovtBodyForm.setHiddenLevel(lbDetails.get(0).getLevel());

						model.addAttribute("districtPanchayatList", districtPanchayatList);
						model.addAttribute("localBodyDetails", lbDetails);

						model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);

					}
				}

				// CustomValidatorLBModify cValidatorModify = new
				// CustomValidatorLBModify();
				cValidatorModify.modifyCoverageLBDisturbed(localGovtBodyForm, result, request, session);

				if (result.hasErrors()) {
					
					if (stateCode != null) {

						if (session.getAttribute("formbean") != null) {
							session.removeAttribute("formbean");
							session.removeValue("formbean");
						}
						request.setAttribute("stateCode", stateCode);
					}
					if (localGovtBodyForm.getLgd_LBlevelChk() == 'U') {
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {
						model.addAttribute("localBodyDetails", lbDetails);
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'I') {
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'V') {
						model.addAttribute("localBodyDetails", lbDetails);
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
					}

					if (session.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					session.removeAttribute("validationErrorOne1");
					mav = new ModelAndView("modify_govt_local_bodyforcoveragearea_disturbed");
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("selectBox", localGovtBodyForm.getLgd_LBTypeNamehidden());
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					return mav;
					// }
				}
				localGovtBodyForm.setLbType(category.charAt(0));
				localGovtBodyForm.setHiddenLbType(category);
				localGovtBodyForm.setLocalBodyDetails(lbDetails);
				localGovtBodyForm.setOperation("DLBR");
				session.setAttribute("formbean", localGovtBodyForm);

				model.addAttribute("levelcheck", lbDetails.get(0).getLevel());
				model.addAttribute("categorycheck", category);
				mav = new ModelAndView("modify_govt_local_bodyforDisturbed");
				model.addAttribute("selectBox", selectedvalue1);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				String aMessage = "Sorry Data Not Found For Your Selection ";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				return mav;
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

	// Added by Arnab on 25/07/2013 for Disturbed Functionality
	/*
	 * Declare Lbdetails,districtPanchayatList,localBodyTypelist variable for
	 * manage localBody Details:Govt Order Correction.
	 */
	@RequestMapping(value = "/redirectToGovtOrderPage", method = RequestMethod.POST)
	public ModelAndView redirectToGovtOrderPage(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;

		try {
			// LocalGovtBodyForm localGovtBodyFormSess = (LocalGovtBodyForm)
			// session.getAttribute("formbean");

			/*
			 * lbDetails =
			 * localGovtBodyService.getGovtLocalBodyDetails(localGovtBodyFormSess
			 * .getLocalBodyCode());
			 * localGovtBodyForm.setLocalBodyDetails(lbDetails);
			 */

			cValidatorModify.modifyValidationChangeNameAndParentforDisturb(localGovtBodyForm, result);
			List<LocalBodyDetails> lbDetails = localGovtBodyForm.getLocalBodyDetails();
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyForm.getDistrictPanchayatList();
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyForm.getLocalBodyTypelist();
			if (result.hasErrors()) {
				if (localGovtBodyForm.getLgd_LBlevelChk() == 'U') {
					model.addAttribute("localBodyDetails", lbDetails);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {
					model.addAttribute("localBodyDetails", lbDetails);
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'I') {
					model.addAttribute("localBodyDetails", lbDetails);
					model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
					localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'V') {
					model.addAttribute("localBodyDetails", lbDetails);
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
				}

				// session.setAttribute("formbean", localGovtBodyForm);

				session.setAttribute("formbeanChangeN", localGovtBodyForm);

				mav = new ModelAndView("redirectToDisturbedPage");
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				return mav;
			} else {
				// session.setAttribute("formbean", localGovtBodyForm);
				mav = new ModelAndView("redirect:govtOrderCommon.htm");
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				session.setAttribute("formbeanChangeN", localGovtBodyForm);

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

	// Added by Arnab on 25/07/2013 for Disturbed Functionality

	@RequestMapping(value = "/saveDisturbedLocalGovtBody", method = RequestMethod.POST)
	public ModelAndView saveDisturbedLocalGovtBody(HttpSession session, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			
			String[] val = null;
			int operationCode = 0;
			char lbType;
			String check = null;
			//String lbreturn = null;
			
			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			LocalGovtBodyForm localGovtBodyFormChngeName = (LocalGovtBodyForm) session.getAttribute("formbeanChangeN");
			GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

			Map<String, String> hMap = new HashMap<String, String>();
			operationCode = localGovtBodyForm.getOperationCode();
			lbType = localGovtBodyForm.getLbType();

			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			//String mapConfig = hMap.get("mapUpload");// values==true,false
			// String message = hMap.get("message");
			localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);

			if (govtOrderConfig.equals("govtOrderUpload")) {
				check = localGovtBodyService.modifyDisturbedLocalBodyForcoverageAreaNameParent(localGovtBodyForm, localGovtBodyFormChngeName, govtOrderForm, request, session, userId);

				val = check.split(",");


				if (session.getAttribute("validGovermentUpload") != null) {
					@SuppressWarnings("unchecked")
					List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) session.getAttribute("validGovermentUpload");
					//boolean insertGovtTableFlag = 
					localGovtBodyService.saveDataInAttachCoverageLBody(validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
				}
				if (check != null) {
					if (localGovtBodyForm.getLbType() == 'P') {
						int t_code = Integer.parseInt(val[0]);
						char t_type = 'R';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}
					if (localGovtBodyForm.getLbType() == 'T') {
						int t_code = Integer.parseInt(val[0]);
						char t_type = 'T';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}

					if (localGovtBodyForm.getLbType() == 'U') {
						int t_code = Integer.parseInt(val[0]);
						char t_type = 'U';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}
				}
			} else if (govtOrderConfig.equals("govtOrderGenerate")) {
				int templCode = 0;
				localGovtBodyForm.setOperation("DLBR");

				templCode = Integer.parseInt(localGovtBodyForm.getTemplateList());

				session.setAttribute("formbean", localGovtBodyForm);
				String templateBodylbSrc = govtOrderService.previewTemplate(templCode, session);
				govtOrderForm.setTemplateBodySrc(templateBodylbSrc);
				mav = new ModelAndView("previewGovtOrder");
				mav.addObject("governmentOrder", govtOrderForm);
			}
			if (govtOrderConfig.equals("govtOrderUpload")) {
				if (check != null) {

					session.removeAttribute("validationErrorOne");
					String aMessage = "The Disturbed Local Government Body was modified successfully";
					mav = new ModelAndView("success");
					mav.addObject("msgid", aMessage);
				}
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/*
	 * Declare districtPanchayatList,localBodyTypelist variable for create PRI
	 * localBody Details:Update Local Body Coverage Area.
	 */

	@RequestMapping(value = "/modifyGovtLocalBodyMainforcoverageareaClear", method = RequestMethod.GET)
	public ModelAndView modifyUrbanLocalBodyTypeforcoverageareaClear(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpSession session, HttpServletRequest request) {

		ModelAndView mav = null;
		List<UnLRSWiseList> statewiseunmappedList = null;
		List<UnLRDistrictWiseList> districtwiseunmappedList = null;
		//String id = request.getParameter("id");
		String localBCode = (String) session.getAttribute("localBCode");
		//char type = ' ';
		int operationCode = 0;
		String Selectedvalue1 = (String) session.getAttribute("Selected");
		String Catageryforwad1 = (String) session.getAttribute("Catagery");
		
		/*if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}*/

		String localBCodehidden = (String) session.getAttribute("lbcodeHidden");
		int localBodyCode = Integer.parseInt(localBCodehidden);

		String categoryhidden = (String) session.getAttribute("lbCategoryHidden");
		char category = categoryhidden.charAt(0);
		if (category == 'P') {
			operationCode = 50;
			localGovtBodyForm.setOperationCode(operationCode);
		} else if (category == 'T') {
			operationCode = 52;
			localGovtBodyForm.setOperationCode(operationCode);
		} else {
			operationCode = 51;
			localGovtBodyForm.setOperationCode(operationCode);
		}

		// int localBodyCode=localGovtBodyForm.getParentwiseId();
		// char category=localGovtBodyForm.getParentCategory();

		try {
			int lbTypeCode = 0;
			// char level = ' ';
			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
			session.setAttribute("Selectedvalue", Selectedvalue1);
			session.setAttribute("Catageryforwad", Catageryforwad1);

			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			List<ViewLocalBodyLandRegion> districtnamelist = localGovtBodyService.viewLandRegionDistrictName(localBodyCode);
			List<ViewLocalBodyLandRegion> subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
			List<ViewLocalBodyLandRegion> villagenamelist = localGovtBodyService.viewLandRegionvillageNameF(localBodyCode);
			List<ViewLandRegionDisturbedlist> gpdisturbedlist = localGovtBodyService.viewGpdisturbedlist(localBodyCode);

			if (districtnamelist.size() != 0 || subdisticnamelist.size() != 0 || villagenamelist.size() != 0) {
				//List<Localbody> localbodyObj = null;
				mav = new ModelAndView("modify_govt_local_bodyforcoveragearea");
				
				String localTypeCodeHidden = (String) session.getAttribute("lbTypehidden");

				mav.addObject("selectBox", localTypeCodeHidden);
				mav.addObject("stateCode", stateCode);
				List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
				// EsapiEncoder.encode(lbDetails);
				List<Attachment> attachmentList = null;
				List<MapAttachment> mapAttachmentList = null;
				localGovtBodyForm.setLocalBodyCode(localBodyCode);
				if (!lbDetails.isEmpty()) {

					// attachmentList =
					// getAttachmentListbyLocalBody(localGovtBodyForm,
					// localBodyCode, lbDetails, request);

					// mapAttachmentList =
					// getMapAttachmentListbyLocalBody(localGovtBodyForm,
					// localBodyCode, lbDetails, request);
					// EsapiEncoder.encode(mapAttachmentList);
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);

					lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();

					Map<String, String> hMap = new HashMap<String, String>();

					// Please first check your operation code then set it in
					// place
					// of 10
					hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, category);

					String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
					String mapConfig = hMap.get("mapUpload");// values==true,false
					String message = hMap.get("message");

					localGovtBodyForm.setOperation("LBCCH");
					List<GovernmentOrderTemplate> templateListNew = new ArrayList<GovernmentOrderTemplate>();
					templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);

					if (category == 'U') {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('U');

							localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category, stateCode);
							localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
							model.addAttribute("localBodyDetails", lbDetails);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							model.addAttribute("lgtLocalBodyType", localBodyTypelist);
						} else {
							mav = new ModelAndView("success");
							mav.addObject("msgid", message);
						}

					} else {

						if (districtCode == 0) {
							model.addAttribute("UnmappedData", localGovtBodyService.getPanchayatListbyStateandCategoryforselected(stateCode, category, localBodyCode));
						} else if (districtCode > 0) {
							model.addAttribute("UnmappedData", localGovtBodyService.getLandRegionByDistricCode(lbTypeCode, districtCode, Character.toString(category)));
						}
						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
							if (govtOrderConfig != null && mapConfig != null) {
								localGovtBodyForm.setLgd_LBlevelChk('D');

								if (districtCode == 0) {
									statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('D', stateCode);
									model.addAttribute("UnmappedData", statewiseunmappedList);
								} else if (districtCode > 0) {
									districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('D', districtCode);
									model.addAttribute("UnmappedData", districtwiseunmappedList);
								}

								// List<UnmappedLBList> unMappedPanchayatList =
								// localGovtBodyService.getUnmappedLBDistList('D',
								// stateCode);
								// List<PartillyMappedLRList>
								// partillyMappedPanchayatList =
								// localGovtBodyService.getPartillymappedLBDistList('D',
								// stateCode, category);
								// model.addAttribute("localBodyUnmappedAreaList",
								// unMappedPanchayatList);
								model.addAttribute("localBodyDetails", lbDetails);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								mav.addObject("templateList", templateListNew);
								model.addAttribute("districtPanchayatList", districtPanchayatList);
								// model.addAttribute("landRegionPartiallyUnmapList",
								// partillyMappedPanchayatList);

								localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
							} else {
								mav = new ModelAndView("success");
								mav.addObject("msgid", message);
							}
						}
						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
							if (govtOrderConfig != null && mapConfig != null) {
								localGovtBodyForm.setLgd_LBlevelChk('I');
								if (districtCode == 0) {
									statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('T', stateCode);
									model.addAttribute("UnmappedData", statewiseunmappedList);

								} else if (districtCode > 0) {
									districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('T', districtCode);
									model.addAttribute("UnmappedData", districtwiseunmappedList);
								}

								model.addAttribute("localBodyDetails", lbDetails);
								model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
								model.addAttribute("districtPanchayatList", districtPanchayatList);
								mav.addObject("templateList", templateListNew);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
							} else {
								mav = new ModelAndView("success");
								mav.addObject("msgid", message);
							}

						}

						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
							if (govtOrderConfig != null && mapConfig != null) {
								localGovtBodyForm.setLgd_LBlevelChk('V');
								if (districtCode == 0) {
									statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('V', stateCode);
									model.addAttribute("UnmappedData", statewiseunmappedList);
								} else if (districtCode > 0) {
									districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('V', districtCode);
									model.addAttribute("UnmappedData", districtwiseunmappedList);
								}
								localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
								localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
								localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
								model.addAttribute("districtPanchayatList", districtPanchayatList);
								model.addAttribute("localBodyDetails", lbDetails);
								mav.addObject("templateList", templateListNew);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							} else {
								mav = new ModelAndView("success");
								mav.addObject("msgid", message);
							}
						}
					}

					localGovtBodyForm.setLbType(category);
					localGovtBodyForm.setLocalBodyDetails(lbDetails);
					if (session.getAttribute("validationErrorOne") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					if (session.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne1", validateAttachment);
					}
					session.removeAttribute("validationErrorOne");
					session.removeAttribute("validationErrorOne1");
					model.addAttribute("attachmentList", attachmentList);
					model.addAttribute("districtnamelist", districtnamelist);
					model.addAttribute("subdisticnamelist", subdisticnamelist);
					model.addAttribute("villagenamelist", villagenamelist);
					model.addAttribute("gpdisturbedlist", gpdisturbedlist);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("lgdLBTypeName", localGovtBodyForm.getLgd_LBTypeName());

					model.addAttribute("localBCode", localBCode);

					mav.addObject("localGovtBodyForm", localGovtBodyForm);
				}
			} else {
				return mav = new ModelAndView("redirect:ForwadCorrectcoveredareaLocalBody.htm");
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

	/*
	 * Declare Lbdetails,districtPanchayatList,localBodyTypelist variable for
	 * manage PRI localBody Details:Map Coverage Area.
	 */

	@RequestMapping(value = "/correctGovtLocalBodycoveragearea")
	public ModelAndView CorrectLocalBodycoveragearea(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		List<UnLRSWiseList> statewiseunmappedList = null;
		List<UnLRDistrictWiseList> districtwiseunmappedList = null;
		List<ViewLocalBodyLandRegion> districtnamelist = null;
		List<ViewLocalBodyLandRegion> subdisticnamelist = null;
		List<ViewLocalBodyLandRegion> villagenamelist = null;
		String id = request.getParameter("id");
		String Selectedvalue1 = (String) session.getAttribute("Selected");
		String Catageryforwad1 = (String) session.getAttribute("Catagery");
		char type = ' ';
		
		if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}
		Integer localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		char category = (type != ' ') ? type : localGovtBodyForm.getParentCategory();
		try {
			@SuppressWarnings("unused")
			int lbTypeCode = 0;
			int lbversion = 0;
			
			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
			session.setAttribute("Selectedvalue", Selectedvalue1);
			session.setAttribute("Catageryforwad", Catageryforwad1);
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			// Code added according to new change----by Arnab on 13/03/2013
			List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
			if (lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
				districtnamelist = localGovtBodyService.viewLandRegionDistrictName(localBodyCode);
				subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
				villagenamelist = localGovtBodyService.viewLandRegionvillageName(localBodyCode);
			} else if (lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
				subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
				villagenamelist = localGovtBodyService.viewLandRegionvillageName(localBodyCode);
			} else if (lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
				villagenamelist = localGovtBodyService.viewLandRegionvillageName(localBodyCode);
			}
			if (category == 'U') {
				// Conditional Check whether Logged in state is ULB oprate
				// District wise. @author Vinay Yadav 23-12-2013
				if (ApplicationConstant.checkStateLBOnlyDisttWise(stateCode)) {
					subdisticnamelist = localGovtBodyService.viewLandRegionDistrictName(localBodyCode);
				} else {
					subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
				}

			}
			List<ViewLocalBodyLandRegion> coveragearealist = localGovtBodyService.viewCoveragearea(localBodyCode);

			// Code change done to allow User to go to Mapped Land Region page
			// irrespective of Mapped covered area.---- on 13/03/2013----Arnab
			if (coveragearealist.size() != 0 || coveragearealist.size() == 0) {

				// List<Localbody> localbodyObj = null;
				mav = new ModelAndView("Correct_govt_local_bodyforcoveragearea");
				mav.addObject("selectBox", localGovtBodyForm.getLgd_LBTypeName());

				String templbTypeName[] = localGovtBodyForm.getLgd_LBTypeName().split(":");
				String typelbtypecode = templbTypeName[0];

				mav.addObject("lbtypecode", typelbtypecode);

				mav.addObject("stateCode", stateCode);
				// lbDetails =
				// localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
				// EsapiEncoder.encode(lbDetails);
				List<Attachment> attachmentList = null;
				List<MapAttachment> mapAttachmentList = null;
				localGovtBodyForm.setLocalBodyCode(localBodyCode);
				if (!lbDetails.isEmpty()) {
					//char level = lbDetails.get(0).getLevel().charAt(0);
					attachmentList = getAttachmentListbyLocalBody(localGovtBodyForm, localBodyCode, lbDetails, request);
					mapAttachmentList = getMapAttachmentListbyLocalBody(localGovtBodyForm, localBodyCode, lbDetails, request);
					// EsapiEncoder.encode(mapAttachmentList);
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);

					lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();
					lbversion = lbDetails.get(0).getLocalBodyVersion();

					if (category == 'U') {
						localGovtBodyForm.setLgd_LBlevelChk('U');
						// Conditional Check whether Logged in state is ULB
						// oprate District wise. @author Vinay Yadav 23-12-2013
						if (ApplicationConstant.checkStateLBOnlyDisttWise(stateCode)) {
							statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('D', stateCode);
							model.addAttribute("isDistrictLevel", true);
						} else {
							if (districtCode == 0) {
								model.addAttribute("UnmappedData", localGovtBodyService.getUnMapLRStaWiseList('T', stateCode));
							} else if (districtCode > 0) {
								model.addAttribute("UnmappedData", localGovtBodyService.getUnMapLRDistWiseList('T', districtCode));
							}
						}

						localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category, stateCode);
						localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);

					} else {
						// districtPanchayatList =
						// localGovtBodyService.getPanchayatListbyStateandCategory(stateCode,
						// category, level);

						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {

							localGovtBodyForm.setLgd_LBlevelChk('D');
							List<UnmappedLBList> unMappedPanchayatList = localGovtBodyService.getUnmappedLBDistList('D', stateCode);
							List<PartillyMappedLRList> partillyMappedPanchayatList = localGovtBodyService.getPartillymappedLBDistList('D', stateCode, category);
							if (districtCode == 0) {
								statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('D', stateCode);
								model.addAttribute("UnmappedData", statewiseunmappedList);
							} else if (districtCode > 0) {
								districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('D', districtCode);
								model.addAttribute("UnmappedData", districtwiseunmappedList);
							}
							model.addAttribute("localBodyUnmappedAreaList", unMappedPanchayatList);
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							model.addAttribute("landRegionPartiallyUnmapList", partillyMappedPanchayatList);
							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");

						}
						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
							localGovtBodyForm.setLgd_LBlevelChk('I');

							if (districtCode == 0) {
								statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('T', stateCode);
								model.addAttribute("UnmappedData", statewiseunmappedList);
							} else if (districtCode > 0) {
								districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('T', districtCode);
								model.addAttribute("UnmappedData", districtwiseunmappedList);
							}

							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList);

							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");

						}

						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
							localGovtBodyForm.setLgd_LBlevelChk('V');

							if (districtCode == 0) {
								statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('V', stateCode);
								model.addAttribute("UnmappedData", statewiseunmappedList);
							} else if (districtCode > 0) {
								districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('V', districtCode);
								model.addAttribute("UnmappedData", districtwiseunmappedList);
							}

							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						}

					}

					localGovtBodyForm.setLbType(category);
					localGovtBodyForm.setLocalBodyVersion(lbversion);
					localGovtBodyForm.setLocalBodyDetails(lbDetails);
					session.setAttribute("lbcodeHidden", localBodyCode.toString());
					session.setAttribute("lbCategoryHidden", Character.toString(category));
					session.setAttribute("lbTypehidden", localGovtBodyForm.getLgd_LBTypeName());
					if (session.getAttribute("validationErrorOne") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					if (session.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne1", validateAttachment);
					}
					session.removeAttribute("validationErrorOne");
					session.removeAttribute("validationErrorOne1");
					model.addAttribute("levelcheck", localGovtBodyForm.getLgd_LBlevelChk());
					model.addAttribute("districtnamelist", districtnamelist);
					model.addAttribute("subdisticnamelist", subdisticnamelist);
					model.addAttribute("villagenamelist", villagenamelist);
					model.addAttribute("attachmentList", attachmentList);
					model.addAttribute("coveragearealist", coveragearealist);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
				}
			} else {
				return mav = new ModelAndView("redirect:ForwardCoveredLocalBodyforPRI.htm");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/*
	 * Declare Lbdetails,localBodyTypelist variable for LocalBody Form:Map
	 * Coverage Area.
	 */
	@RequestMapping(value = "/correctGovtLocalBodycoverageareaClear", method = RequestMethod.GET)
	public ModelAndView CorrectLocalBodycoverageareaClear(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		List<UnLRSWiseList> statewiseunmappedList = null;
		List<UnLRDistrictWiseList> districtwiseunmappedList = null;
		//String id = request.getParameter("id");
		String Selectedvalue1 = (String) session.getAttribute("Selected");
		String Catageryforwad1 = (String) session.getAttribute("Catagery");
		//char type = ' ';
		
		/*if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}*/
		// int localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ?
		// Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		String localBCodehidden = (String) session.getAttribute("lbcodeHidden");
		int localBodyCode = Integer.parseInt(localBCodehidden);

		// char category = (type != ' ') ? type :
		// localGovtBodyForm.getParentCategory();
		String categoryhidden = (String) session.getAttribute("lbCategoryHidden");
		char category = categoryhidden.charAt(0);
		try {
			//int lbTypeCode = 0;
			int lbversion = 0;
			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
			session.setAttribute("Selectedvalue", Selectedvalue1);
			session.setAttribute("Catageryforwad", Catageryforwad1);
			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			// Code added according to new change----by Arnab on 13/03/2013

			List<ViewLocalBodyLandRegion> districtnamelist = localGovtBodyService.viewLandRegionDistrictName(localBodyCode);
			List<ViewLocalBodyLandRegion> subdisticnamelist = localGovtBodyService.viewLandRegionsubDistrictName(localBodyCode);
			List<ViewLocalBodyLandRegion> villagenamelist = localGovtBodyService.viewLandRegionvillageName(localBodyCode);
			List<ViewLocalBodyLandRegion> coveragearealist = localGovtBodyService.viewCoveragearea(localBodyCode);

			// Code change done to allow User to go to Mapped Land Region page
			// irrespective of Mapped covered area.---- on 13/03/2013----Arnab
			if (coveragearealist.size() != 0 || coveragearealist.size() == 0) {

				// List<Localbody> localbodyObj = null;
				mav = new ModelAndView("Correct_govt_local_bodyforcoveragearea");
				
				mav.addObject("selectBox", localGovtBodyForm.getLgd_LBTypeName());
				mav.addObject("stateCode", stateCode);
				List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
				// EsapiEncoder.encode(lbDetails);
				List<Attachment> attachmentList = null;
				List<MapAttachment> mapAttachmentList = null;
				localGovtBodyForm.setLocalBodyCode(localBodyCode);
				if (!lbDetails.isEmpty()) {
					char level = lbDetails.get(0).getLevel().charAt(0);
					attachmentList = getAttachmentListbyLocalBody(localGovtBodyForm, localBodyCode, lbDetails, request);
					mapAttachmentList = getMapAttachmentListbyLocalBody(localGovtBodyForm, localBodyCode, lbDetails, request);
					// EsapiEncoder.encode(mapAttachmentList);
					request.setAttribute("attachmentList", attachmentList);
					request.setAttribute("mapAttachmentList", mapAttachmentList);

					//lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();
					lbversion = lbDetails.get(0).getLocalBodyVersion();

					if (category == 'U') {
						localGovtBodyForm.setLgd_LBlevelChk('U');
						if (districtCode == 0) {
							statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('T', stateCode);
							model.addAttribute("UnmappedData", statewiseunmappedList);
						} else if (districtCode > 0) {
							districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('T', districtCode);
							model.addAttribute("UnmappedData", districtwiseunmappedList);
						}
						localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category, stateCode);
						localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);

					} else {
						districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, category, level);

						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {

							localGovtBodyForm.setLgd_LBlevelChk('D');
							List<UnmappedLBList> unMappedPanchayatList = localGovtBodyService.getUnmappedLBDistList('D', stateCode);
							List<PartillyMappedLRList> partillyMappedPanchayatList = localGovtBodyService.getPartillymappedLBDistList('D', stateCode, category);
							if (districtCode == 0) {
								statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('D', stateCode);
								model.addAttribute("UnmappedData", statewiseunmappedList);
							} else if (districtCode > 0) {
								districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('D', districtCode);
								model.addAttribute("UnmappedData", districtwiseunmappedList);
							}
							model.addAttribute("localBodyUnmappedAreaList", unMappedPanchayatList);
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							model.addAttribute("landRegionPartiallyUnmapList", partillyMappedPanchayatList);
							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");

						}
						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
							localGovtBodyForm.setLgd_LBlevelChk('I');

							if (districtCode == 0) {
								statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('T', stateCode);
								model.addAttribute("UnmappedData", statewiseunmappedList);
							} else if (districtCode > 0) {
								districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('T', districtCode);
								model.addAttribute("UnmappedData", districtwiseunmappedList);
							}

							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList);

							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");

						}

						if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
							localGovtBodyForm.setLgd_LBlevelChk('V');

							if (districtCode == 0) {
								statewiseunmappedList = localGovtBodyService.getUnMapLRStaWiseList('V', stateCode);
								model.addAttribute("UnmappedData", statewiseunmappedList);
							} else if (districtCode > 0) {
								districtwiseunmappedList = localGovtBodyService.getUnMapLRDistWiseList('V', districtCode);
								model.addAttribute("UnmappedData", districtwiseunmappedList);
							}

							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						}

					}

					String localTypeCodeHidden = (String) session.getAttribute("lbTypehidden");
					mav.addObject("selectBox", localTypeCodeHidden);

					localGovtBodyForm.setLbType(category);
					localGovtBodyForm.setLocalBodyVersion(lbversion);
					localGovtBodyForm.setLocalBodyDetails(lbDetails);
					if (session.getAttribute("validationErrorOne") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					if (session.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) session.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne1", validateAttachment);
					}
					session.removeAttribute("validationErrorOne");
					session.removeAttribute("validationErrorOne1");

					model.addAttribute("districtnamelist", districtnamelist);
					model.addAttribute("subdisticnamelist", subdisticnamelist);
					model.addAttribute("villagenamelist", villagenamelist);
					model.addAttribute("attachmentList", attachmentList);
					model.addAttribute("coveragearealist", coveragearealist);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
				}
			} else {
				return mav = new ModelAndView("redirect:ForwardCoveredLocalBodyforPRI.htm");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// Methods have been shifted from LocalGovtBodyController.java ----on
	// 13/03/2013---by Arnab----Start
	/*
	 * Declare districtPanchayatList,localBodyTypelist variable.
	 */
	@RequestMapping(value = "/ForwardCoveredLocalBodyforPRI")
	public ModelAndView CorrectcoveredareaLocalBodyList(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {

		ModelAndView mav = null;
		try {
			@SuppressWarnings("unused")
			int localBodyTypeCode = 0;
			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			String localBodyTypeCodeDup = (String) session.getAttribute("Selectedvalue");
			String Catageryforwad = (String) session.getAttribute("Catageryforwad");
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);// Only
																		// Used
																		// For
																		// Combo
																		// Field
																		// Validation
			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);// Only
																				// Used
																				// For
																				// Combo
																				// Field
																				// Validation
			localGovtBodyForm.setLgd_lbCategory(Catageryforwad);
			localGovtBodyForm.setLgd_LBTypeName(localBodyTypeCodeDup);
			// List<GovernmentOrder> govtList =
			// govtOrderService.getGovtOrederDetails();
			// viewValidatorLB.validateView(localGovtBodyForm, result);
			if (result.hasErrors()) {
				if (localGovtBodyForm.getLgd_lbCategory().equals("P")) {
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
				} else if (localGovtBodyForm.getLgd_lbCategory().equals("T")) {
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
				} else if (localGovtBodyForm.getLgd_lbCategory().equals("U")) {
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
				}
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				// mav = new ModelAndView("view_localgovtbody");
				if (localGovtBodyForm.getLgd_lbCategory().equals("P")) {
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
				} else if (localGovtBodyForm.getLgd_lbCategory().equals("T")) {
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
				} else if (localGovtBodyForm.getLgd_lbCategory().equals("U")) {
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
				}
				@SuppressWarnings("unused")
				List<ParentWiseLocalBodies> parentWiseLocalBodies = null;
				// String localBodyTypeCodeDup =
				// localGovtBodyForm.getLgd_LBTypeName();
				String strArray[] = localBodyTypeCodeDup.split(":");
				String typeCode = strArray[0];
				String type = strArray[1];
				@SuppressWarnings("unused")
				String categoryDropDown = strArray[2];
				@SuppressWarnings("unused")
				String lbName = null;
				// List<LocalbodyListbyState> districtPanchayatList = null;
				if (Catageryforwad != null && Catageryforwad.length() > 0) {
					localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(Catageryforwad.charAt(0), stateCode);

					if (localBodyTypelist != null && localBodyTypelist.size() > 0) {

						String districtPanchayatCodes = (String) session.getAttribute("districtPanchayatCodes");
						String intermediatePanchayatCodes = (String) session.getAttribute("intermediatePanchayatCodes");
						Integer districtcode = districtCode;
						Integer lbtype = (Integer) session.getAttribute("lbtype");
						int offset = 0;
						int limit = 25;

						if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("U")) {
							// /////////////////////////////pagination///////////////////////////////

							localGovtBodyForm.setLimit(limit);
							localGovtBodyForm.setOffset(offset);
							session.setAttribute("limit", localGovtBodyForm.getLimit());
							boolean movenext = true;
							session.setAttribute("offset", localGovtBodyForm.getOffset());
							session.setAttribute("lbtype", lbtype);
							List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode, Integer.parseInt(typeCode), offset, limit);
							int counter = localGovtBodyService.countLocalBodyDetails(lbtype, stateCode);
							session.setAttribute("counter", counter);
							model.addAttribute("localGovtBodyForm", localGovtBodyForm);
							model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
							model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
							model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
							model.addAttribute("counter", counter);
							if (offset == (counter / limit))
								movenext = false;
							else
								movenext = true;
							model.addAttribute("movenext", movenext);
							model.addAttribute("LocalBodyCount",
									"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
											+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

							localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
							if (lgdLocalGovtBodyList.size() <= 0) {
								model.addAttribute("listnull", "LocalBody not present");
							}

							// /////////////////////////////pagination///////////////////////////////

						} else {
							if (type.equalsIgnoreCase("D")) {
								if (districtcode != 0) {
									List<LocalbodyListbyState> lgdLocalGovtBodyList = localGovtBodyService.getLandRegionWisedistrict(type, districtcode, lbtype);
									model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

									localGovtBodyForm.setLgdLocalGovtBodyList(lgdLocalGovtBodyList);
								}

								else {

									// /////////////////////////////pagination///////////////////////////////

									localGovtBodyForm.setLimit(limit);
									localGovtBodyForm.setOffset(offset);
									session.setAttribute("limit", localGovtBodyForm.getLimit());
									boolean movenext = true;
									session.setAttribute("offset", localGovtBodyForm.getOffset());
									session.setAttribute("lbtype", lbtype);
									List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode, Integer.parseInt(typeCode), offset, limit);
									int counter = localGovtBodyService.countLocalBodyDetails(lbtype, stateCode);
									session.setAttribute("counter", counter);
									model.addAttribute("localGovtBodyForm", localGovtBodyForm);
									model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
									model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
									model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
									model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
									model.addAttribute("counter", counter);
									if (offset == (counter / limit))
										movenext = false;
									else
										movenext = true;
									model.addAttribute("movenext", movenext);
									model.addAttribute(
											"LocalBodyCount",
											"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
													+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
									model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

									localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
									if (lgdLocalGovtBodyList.size() <= 0) {
										model.addAttribute("listnull", "LocalBody not present");
									}

									// /////////////////////////////pagination///////////////////////////////

								}
							}

							if (type.equalsIgnoreCase("I")) {
								if (districtPanchayatCodes != null) {

									// /////////////////////////////pagination///////////////////////////////
									localGovtBodyForm.setLimit(limit);
									localGovtBodyForm.setOffset(offset);
									session.setAttribute("limit", localGovtBodyForm.getLimit());
									boolean movenext = true;
									session.setAttribute("offset", localGovtBodyForm.getOffset());
									session.setAttribute("lbtype", lbtype);
									List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), districtPanchayatCodes, offset, limit);
									int counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, districtPanchayatCodes);
									session.setAttribute("counter", counter);
									model.addAttribute("localGovtBodyForm", localGovtBodyForm);
									model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
									model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
									model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
									model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
									model.addAttribute("counter", counter);
									if (offset == (counter / limit))
										movenext = false;
									else
										movenext = true;
									model.addAttribute("movenext", movenext);
									model.addAttribute(
											"LocalBodyCount",
											"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
													+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
									model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

									localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
									if (lgdLocalGovtBodyList.size() <= 0) {
										model.addAttribute("listnull", "LocalBody not present");
									}
									model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

									// /////////////////////////////pagination///////////////////////////////

								} else {
									int localbodyCode = Integer.parseInt(districtPanchayatCodes);
									List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService.getchildlocalbodiesByParentcode(localbodyCode);
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);

									localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
								}
							}
							int localBodyCode1 = 0;
							if (type.equalsIgnoreCase("V")) {
								if (districtPanchayatCodes != null) {
									if (intermediatePanchayatCodes != null) {
										localBodyCode1 = Integer.parseInt(intermediatePanchayatCodes);
									} else {
										localBodyCode1 = Integer.parseInt(districtPanchayatCodes);
									}

									// /////////////////////////////pagination///////////////////////////////

									localGovtBodyForm.setLimit(limit);
									localGovtBodyForm.setOffset(offset);
									session.setAttribute("limit", localGovtBodyForm.getLimit());
									boolean movenext = true;
									session.setAttribute("offset", localGovtBodyForm.getOffset());
									session.setAttribute("lbtype", lbtype);
									List<Localbody> localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), intermediatePanchayatCodes, offset, limit);
									int counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, intermediatePanchayatCodes);
									session.setAttribute("counter", counter);
									model.addAttribute("localGovtBodyForm", localGovtBodyForm);
									model.addAttribute("localbodysize", localBodyViewChild.size());
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);
									model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
									model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
									model.addAttribute("counter", counter);
									if (offset == (counter / limit))
										movenext = false;
									else
										movenext = true;
									model.addAttribute("movenext", movenext);
									model.addAttribute(
											"LocalBodyCount",
											"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
													+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);
									model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);
									model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
									localGovtBodyForm.setLocalbodyDetails(localBodyViewChild);
									if (localBodyViewChild.size() <= 0) {
										model.addAttribute("listnull", "LocalBody not present");
									}

									// /////////////////////////////pagination///////////////////////////////
								} else {
									if (localGovtBodyForm.getLocalBodyNameEnglishListSource() != null) {

										localBodyCode1 = Integer.parseInt(intermediatePanchayatCodes);
										List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService.getchildlocalbodiesByParentcode(localBodyCode1);
										model.addAttribute("LocalGovtBodyList", localBodyViewChild);
										localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
										model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

										model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
									}
									if (localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() != null) {
										localBodyCode1 = Integer.parseInt(districtPanchayatCodes);
										List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService.getchildlocalbodiesByParentcode(localBodyCode1);
										model.addAttribute("LocalGovtBodyList", localBodyViewChild);
										localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
										model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

										model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
									}
									if (localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() == null) {
										localBodyCode1 = Integer.parseInt(typeCode);
										/*
										 * List<ParentWiseLocalBodies>
										 * localBodyViewChild =
										 * localGovtBodyService
										 * .getPanchayatListbyStateandlbTypeCode
										 * (stateCode,
										 * Integer.parseInt(typeCode));
										 */
										List<LocalbodyListbyState> localBodyViewChild = localGovtBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, Integer.parseInt(typeCode));
										model.addAttribute("LocalGovtBodyList", localBodyViewChild);
										// localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
										model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

										model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
									}

								}
							}
						}
						session.setAttribute("typeCode", typeCode);
						if (localBodyTypelist.size() > 0) {
							localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
						}
						if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("T")) {
							if (districtcode != 0) {
								List<LocalbodyListbyState> districtPanchayatList1 = localGovtBodyService.getLandRegionWisedistrict(type, districtcode, lbtype);

								localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
								model.addAttribute("districtPanchayatList", districtPanchayatList1);
							} else {
								districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'T', 'D');
							}
						} else if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("P")) {
							if (districtcode != 0) {
								List<LocalbodyListbyState> districtPanchayatList1 = localGovtBodyService.getLandRegionWisedistrict(type, districtcode, lbtype);

								localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
								model.addAttribute("districtPanchayatList", districtPanchayatList1);
							} else {
								districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'P', 'D');
							}
						}

						session.setAttribute("lgtLocalBodyType", localBodyTypelist);

						model.addAttribute("districtPanchayatList", districtPanchayatList);
						session.setAttribute("districtPanchayatList", districtPanchayatList);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);

					}

					// localGovtBodyForm.setHdnType(localBodyTypeCodeDup);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					session.setAttribute("localGovtBodyForm", localGovtBodyForm);
					mav.addObject("msgid", "This LocalBody already Have the CoveredArea So Go to Change CoveredArea");
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

	// Methods have been shifted from LocalGovtBodyController.java ----on
	// 13/03/2013---by Arnab----End

	/**
	 * Modified by Sushil Declare
	 * lbdetails,districtPanchayatList,localBodyTypelist varibale for Manage PRI
	 * LocalBody Form.
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/modifyLocalBodyForCorrection", method = RequestMethod.POST)
	public ModelAndView modifyGovtLocalBodyForCorrection(@Valid @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpsession) {
		ModelAndView mav = new ModelAndView("modify_govt_local_body");
		boolean check = false;
		int operationCode = 0;
		char lbType;
		boolean delflag = false;
		List<Attachment> alreadyAttachList = new ArrayList<Attachment>();
		// List<MapAttachment> alreadyAttachMapList = new
		// ArrayList<MapAttachment>();
		List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
		List<AttachedFilesForm> metaInfoOfToBeAttachedMapList = null;

		try {
			// ////////code for government order
			// checking///////////////////////////////////
			List<Attachment> attachmentList = null;
			int localBodyCode = localGovtBodyForm.getLocalBodyCode();
			operationCode = localGovtBodyForm.getOperationCode();
			lbType = localGovtBodyForm.getLbType();
			
			String coodinates = (String) httpsession.getAttribute("Coordinates");
			String mapfilename = (String) httpsession.getAttribute("MapFileName");
			String warningFlag = (String) httpsession.getAttribute("warningEntiesFlag");

			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10

			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			// String message = hMap.get("message");

			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);

			if (govtOrderConfig.equals("govtOrderUpload") || govtOrderConfig.equals("govtOrderGenerate")) {
				AddAttachmentBean addAttachmentBean = getAttachmentBean(localGovtBodyForm, request);
				AddAttachmentBean addAttachmentBean2 = getAttachmentBeanforMap(localGovtBodyForm, request);

				@SuppressWarnings("unused")
				String deleteAttachmentID[] = addAttachmentBean.getDeletedFileID();

				String deleteID[] = addAttachmentBean2.getDeletedFileID();
				if (deleteID != null && deleteID.length > 0) {
					delflag = true;
				}

				/* ================Check the Validation================== */
				AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
				String validateAttachment = attachmentHandler.validateAttachment(addAttachmentBean);
				if (localGovtBodyForm.getOrderNo() != null) {
					if (!validateAttachment.equalsIgnoreCase("validationSuccessFullyDone")) {
						request.setAttribute("validationErrorOne", validateAttachment);
						request.setAttribute("attachmentList", alreadyAttachList);
						mav = new ModelAndView("modify_govt_local_body");
						return mav;
					}
				}

				metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
				metaInfoOfToBeAttachedMapList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
				@SuppressWarnings("unused")
				String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
				@SuppressWarnings("unused")
				String saveAttachment2 = attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedMapList, addAttachmentBean2);
			}

			if (localGovtBodyForm.getAttachFile1() != null) {
				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderforChangeCoverage(request, localGovtBodyForm, result, mav, httpsession);
				httpsession.setAttribute("validGovermentUpload", validFileGovtUpload);
			}

			cValidator.validateLBCovChange(localGovtBodyForm, result, request, httpsession);
			List<LocalBodyDetails> lbDetails = localGovtBodyForm.getLocalBodyDetails();
			if (result.hasErrors()) {
				if (govtOrderConfig != null && mapConfig != null) {
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					
					attachmentList = getAttachmentListbyLocalBody(localGovtBodyForm, localBodyCode, lbDetails, request);
					if (stateCode != null) {
						if (httpsession.getAttribute("formbean") != null) {
							httpsession.removeAttribute("formbean");
							httpsession.removeValue("formbean");
						}
						request.setAttribute("stateCode", stateCode);
					}
					if (localGovtBodyForm.getLgd_LBlevelChk() == 'U') {
						model.addAttribute("localBodyDetails", lbDetails);
						request.setAttribute("attachmentList", attachmentList);
						model.addAttribute("attachmentList", attachmentList);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {
						model.addAttribute("localBodyDetails", lbDetails);
						request.setAttribute("attachmentList", attachmentList);
						model.addAttribute("attachmentList", attachmentList);
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'I') {
						model.addAttribute("localBodyDetails", lbDetails);
						request.setAttribute("attachmentList", attachmentList);
						model.addAttribute("attachmentList", attachmentList);
						model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'V') {
						model.addAttribute("localBodyDetails", lbDetails);
						request.setAttribute("attachmentList", attachmentList);
						model.addAttribute("attachmentList", attachmentList);
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
					}
					mav = new ModelAndView("modify_govt_local_body");
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					return mav;
				}
			} else {

				/* New code */

				if (govtOrderConfig.equals("govtOrderUpload") || govtOrderConfig.equals("govtOrderGenerate")) {
					check = localGovtBodyService.modifyLocalBodyForCorrection(localGovtBodyForm, metaInfoOfToBeAttachedFileList, metaInfoOfToBeAttachedMapList, request, coodinates, mapfilename, delflag);

					if (check) {

						if (warningFlag != null) {
							if (localGovtBodyForm.getLbType() == 'P') {
								mav = new ModelAndView("redirect:viewResolveEntitiesinDisturbedStatePRI.htm?resolved=" + warningFlag);
								return mav;
							} else if (localGovtBodyForm.getLbType() == 'T') {
								mav = new ModelAndView("redirect:viewResolveEntitiesinDisturbedStateTra.htm?resolved=" + warningFlag);
								return mav;
							} else if (localGovtBodyForm.getLbType() == 'U') {
								mav = new ModelAndView("redirect:viewResolveEntitiesinDisturbedStateULB.htm?resolved=" + warningFlag);
								return mav;
							}
						} else {
							httpsession.removeAttribute("validationErrorOne");
							String aMessage = "Modify Local Gov. Body  successfully updated. ";
							mav = new ModelAndView("success");
							mav.addObject("msgid", aMessage);
							return mav;
						}
					} else {
						String aMessage = "Local Body Not successfully modified. ";
						mav = new ModelAndView("success");
						mav.addObject("msgid", aMessage);
					}

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

	@RequestMapping(value = "/saveLocalBodyCorrection", method = RequestMethod.POST)
	public ModelAndView saveLocalBodyCorrection(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		boolean check = false;

		try {
			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			check = localGovtBodyService.modifyLocalBodyForCorrectionGenerate(localGovtBodyForm, request, session);

			if (check) {
				String aMessage = "Modify Local Gov. Body Coverage Area is successfully updated. Local Body Code is " + localGovtBodyForm.getLocalBodyCode();
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/*
	 * Declare Lbdetails variable for manage PRI localBody Details:Modify Parent
	 * Name.
	 */
	@RequestMapping(value = "/modifyGovtLocalBodyMainfortype")
	public ModelAndView modifyUrbanLocalBodyTypefortype(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;

		String id = request.getParameter("id");
		int operationCode = 0;
		int parentLBCode = 0;
		char type = ' ';

		if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}
		int localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		char category = (type != ' ') ? type : localGovtBodyForm.getParentCategory();

		if (category == 'P') {
			operationCode = 48;
			localGovtBodyForm.setOperationCode(operationCode);
		} else if (category == 'T') {
			operationCode = 49;
			localGovtBodyForm.setOperationCode(operationCode);
		}

		// int localBodyCode=localGovtBodyForm.getParentwiseId();
		// char category=localGovtBodyForm.getParentCategory();

		try {

			//int lbTypeCode = 0;
			String oldlocalbodyParentName = null;
			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();

			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			//List<Localbody> localbodyObj = null;
			mav = new ModelAndView("modify_govt_local_bodyfortype");
			
			model.addAttribute("stateCode", stateCode);
			List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
			// EsapiEncoder.encode(lbDetails);
			List<Attachment> attachmentList = null;
			List<MapAttachment> mapAttachmentList = null;
			localGovtBodyForm.setLocalBodyCode(localBodyCode);
			if (!lbDetails.isEmpty()) {
				char level = lbDetails.get(0).getLevel().charAt(0);
				parentLBCode = lbDetails.get(0).getParentLocalBodyCode();

				request.setAttribute("attachmentList", attachmentList);
				request.setAttribute("mapAttachmentList", mapAttachmentList);

				//lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();
				oldlocalbodyParentName = lbDetails.get(0).getParentLocalBodyNameEnglish();
				localGovtBodyForm.setOldlocalbodyParentNamehidden(oldlocalbodyParentName);
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, category);

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				// String message = hMap.get("message");
				localGovtBodyForm.setOperation("LBHRC");
				List<GovernmentOrderTemplate> templateListNew = new ArrayList<GovernmentOrderTemplate>();
				templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);

				if (category == 'U') {
					if (govtOrderConfig != null && mapConfig != null) {
						localGovtBodyForm.setLgd_LBlevelChk('U');

						localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(category, stateCode);
						localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
						model.addAttribute("localBodyDetails", lbDetails);
						mav.addObject("govtOrderConfig", govtOrderConfig);
						mav.addObject("templateList", templateListNew);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					}

				} else {
					// Code Changed by Arnab on 25/03/2013 to resolve issue
					// related to Change Local Body Type
					// districtPanchayatList =
					// localGovtBodyService.getPanchayatListbyParentCategory(stateCode,
					// category, level);

					/*
					 * condition for the GTA to convert lblc in localbodycode
					 * (manage localbody PRI) author Ashish Dhupia , Date :
					 * 23/07/2014
					 */
					if (stateCode == 19 && ("I").equalsIgnoreCase(String.valueOf(level))) {
						Integer convertlblc = localGovtBodyService.getlocalbodycodeByLblc(parentLBCode);

						districtPanchayatList = localGovtBodyService.getPanchayatListbyParentCategoryFChangeTierforGta(stateCode, category, level, convertlblc, parentLBCode);
					} else
						districtPanchayatList = localGovtBodyService.getPanchayatListbyParentCategoryFChangeTier(stateCode, category, level, parentLBCode);

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('D');
							List<UnmappedLBList> unMappedPanchayatList = localGovtBodyService.getUnmappedLBDistList('D', stateCode);
							List<PartillyMappedLRList> partillyMappedPanchayatList = localGovtBodyService.getPartillymappedLBDistList('D', stateCode, category);
							model.addAttribute("localBodyUnmappedAreaList", unMappedPanchayatList);
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							model.addAttribute("landRegionPartiallyUnmapList", partillyMappedPanchayatList);

							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						}

					}
					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('I');
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						}

					}

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
						if (govtOrderConfig != null && mapConfig != null) {
							localGovtBodyForm.setLgd_LBlevelChk('V');
							localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
							localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
							model.addAttribute("districtPanchayatList", districtPanchayatList);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("templateList", templateListNew);
							model.addAttribute("localBodyDetails", lbDetails);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						}

					}
				}

				localGovtBodyForm.setLbType(category);
				localGovtBodyForm.setLocalBodyDetails(lbDetails);
				if (session.getAttribute("validationErrorOne") != null) {
					String validateAttachment = (String) session.getAttribute("validationErrorOne");
					request.setAttribute("validationErrorOne", validateAttachment);
				}
				if (session.getAttribute("validationErrorOne1") != null) {
					String validateAttachment = (String) session.getAttribute("validationErrorOne1");
					request.setAttribute("validationErrorOne1", validateAttachment);
				}
				session.removeAttribute("validationErrorOne");
				session.removeAttribute("validationErrorOne1");
				model.addAttribute("attachmentList", attachmentList);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				String aMessage = "Sorry Data Not Found For Your Selection ";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/*
	 * Declare Lbdetails,districtPanchayatList,localBodyTypelist variable for
	 * manage Urban localBody Details:Update Local Body Coverage Area.
	 */
	@RequestMapping(value = "/modifyGovtLocalBodytypeforurban")
	public ModelAndView modifyUrbanLocalBodyTypeFor(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;

		String id = request.getParameter("id");
		char lbType = 'P';
		char type = ' ';
		int operationCode = 22;
		if (request.getParameter("type") != null) {
			type = request.getParameter("type").charAt(0);
		}
		int localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		char category = (type != ' ') ? type : localGovtBodyForm.getParentCategory();

		// int localBodyCode=localGovtBodyForm.getParentwiseId();
		// char category=localGovtBodyForm.getParentCategory();

		try {

			int lbTypeCode = 0;
			String oldlocalbodyTypeName = null;
			List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();

			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			//List<Localbody> localbodyObj = null;
			mav = new ModelAndView("modify_govt_local_bodyurbantype");
			

			List<LocalBodyDetails> lbDetails = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
			// EsapiEncoder.encode(lbDetails);
			//List<Attachment> attachmentList = null;
			//List<MapAttachment> mapAttachmentList = null;
			localGovtBodyForm.setLocalBodyCode(localBodyCode);
			if (!lbDetails.isEmpty()) {
				char level = lbDetails.get(0).getLevel().charAt(0);

				lbTypeCode = lbDetails.get(0).getLocalBodyTypeCode();
				oldlocalbodyTypeName = lbDetails.get(0).getLocalBodyTypeName();
				localGovtBodyForm.setOldlocalbodyTypeNamehidden(oldlocalbodyTypeName);

				Map<String, String> hMap = new HashMap<String, String>();

				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String mapConfig = hMap.get("mapUpload");// values==true,false
				String message = hMap.get("message");
				localGovtBodyForm.setOperation("LBTC");
				List<GovernmentOrderTemplate> templateListNew = new ArrayList<GovernmentOrderTemplate>();
				templateListNew = govtOrderTemplateService.getTemplateListByOperationCode(operationCode);

				if (category == 'U') {
					if (govtOrderConfig != null && mapConfig != null) {
						localGovtBodyForm.setLgd_LBlevelChk('U');

						localBodyTypelist = localGovtBodyService.getLocalBodyListStateWiseChangeType(category, stateCode, lbTypeCode);
						localGovtBodyForm.setLgd_LBNomenclatureUrban("Urban Bodies");
						model.addAttribute("localBodyDetails", lbDetails);
						mav.addObject("govtOrderConfig", govtOrderConfig);
						mav.addObject("templateList", templateListNew);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					} else {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
					}
				} else {
					districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, category, level);

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("D")) {

						localGovtBodyForm.setLgd_LBlevelChk('D');
						List<UnmappedLBList> unMappedPanchayatList = localGovtBodyService.getUnmappedLBDistList('D', stateCode);
						List<PartillyMappedLRList> partillyMappedPanchayatList = localGovtBodyService.getPartillymappedLBDistList('D', stateCode, category);
						model.addAttribute("localBodyUnmappedAreaList", unMappedPanchayatList);
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("districtPanchayatList", districtPanchayatList);
						model.addAttribute("landRegionPartiallyUnmapList", partillyMappedPanchayatList);

						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");

					}
					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("I")) {
						localGovtBodyForm.setLgd_LBlevelChk('I');
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						model.addAttribute("districtPanchayatList", districtPanchayatList);
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");

					}

					if (lbDetails.get(0).getLevel() != null && lbDetails.get(0).getLevel().equalsIgnoreCase("V")) {
						localGovtBodyForm.setLgd_LBlevelChk('V');
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
						model.addAttribute("districtPanchayatList", districtPanchayatList);
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);

					}
				}

				localGovtBodyForm.setLbType(category);
				localGovtBodyForm.setLocalBodyDetails(lbDetails);
				/*
				 * if (session.getAttribute("validationErrorOne") != null) {
				 * String validateAttachment = (String)
				 * session.getAttribute("validationErrorOne");
				 * request.setAttribute("validationErrorOne",
				 * validateAttachment); } if
				 * (session.getAttribute("validationErrorOne1") != null) {
				 * String validateAttachment = (String)
				 * session.getAttribute("validationErrorOne1");
				 * request.setAttribute("validationErrorOne1",
				 * validateAttachment); }
				 * session.removeAttribute("validationErrorOne");
				 * session.removeAttribute("validationErrorOne1");
				 * model.addAttribute("attachmentList", attachmentList);
				 */
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				String aMessage = "Sorry Data Not Found For Your Selection ";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				return mav;
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
	 * Modify govt. local body name. Declare
	 * Lbdetails,districtPanchayatList,localBodyTypelist variable for manage PRI
	 * localBody Details:Update Local Body Name. Modified by sushil on
	 * 27-02-2013
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/modifyLocalBodyName", method = RequestMethod.POST)
	public ModelAndView modifyGovtLocalBodyNameForModify(@Valid @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpsession) {
		ModelAndView mav = null;
		String check = null;
		try {
			
			int operationCode = localGovtBodyForm.getOperationCode();

			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			char lbType = localGovtBodyForm.getLbType();
			// Code added to set slc of state.
			localGovtBodyForm.setSlc(stateCode);
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			List<AttachedFilesForm> attachedFilesList = null;
			List<LocalBodyDetails> lbDetails = localGovtBodyForm.getLocalBodyDetails();
			Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder"); // values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload"); // values==true,false
			localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);

			// localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
			// localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);
			String isFromDraft = request.getParameter("isFromDraft");

			if (localGovtBodyForm.getAttachFile1() != null) {
				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderforChangeCoverage(request, localGovtBodyForm, result, mav, httpsession);
				/* added by sushil */
				if (validFileGovtUpload == null && isFromDraft != null && !"".equals(isFromDraft)) {
					attachedFilesList = (List<AttachedFilesForm>) httpsession.getAttribute("attachedList");
					if (attachedFilesList != null && attachedFilesList.size() > 0) {
						AttachmentMaster attachmentMaster = govtOrderService.getUploadFileConfigurationDetails(1);
						for (AttachedFilesForm attachedFilesForm : attachedFilesList) {
							String saveAttachment = CommonUtil.readFileByPath(ApplicationConstant.getDefaultFilePath() + attachedFilesForm.getFileTimestampName(), attachedFilesForm.getFileTimestampName(), attachmentMaster.getFileLocation());
							attachedFilesForm.setFileLocation(attachmentMaster.getFileLocation() + "\\" + attachedFilesForm.getFileTimestampName());
							if (!"saveSuccessFully".equals(saveAttachment)) {
								request.setAttribute("validationErrorOne", saveAttachment);
							}
						}
					}
					validFileGovtUpload = attachedFilesList;
					httpsession.removeAttribute("attachedList");
				}
				httpsession.setAttribute("validGovermentUpload", validFileGovtUpload);
			}

			/*
			 * Modified Condition.
			 * 
			 * @author Vinay Yadav
			 */
			if (isFromDraft == null || "".equals(isFromDraft.trim())) {
				// CustomValidatorLBModify cValidatorModify = new
				// CustomValidatorLBModify();
				cValidatorModify.modifyValidationChangeName(localGovtBodyForm, result);
			}

			if (result.hasErrors()) {
				if (govtOrderConfig != null && mapConfig != null) {
					
					if (stateCode != null) {
						if (httpsession.getAttribute("formbean") != null) {
							httpsession.removeAttribute("formbean");
							httpsession.removeValue("formbean");
						}
						request.setAttribute("stateCode", stateCode);
					}
					if (localGovtBodyForm.getLgd_LBlevelChk() == 'U') {
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {
						model.addAttribute("localBodyDetails", lbDetails);
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'I') {
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'V') {
						model.addAttribute("localBodyDetails", lbDetails);
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
					}
					if (httpsession.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) httpsession.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					httpsession.removeAttribute("validationErrorOne1");

					mav = new ModelAndView("modify_govt_local_bodyforname");
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					return mav;
				}
			} else {
				if (govtOrderConfig.equals("govtOrderUpload")) {
					check = localGovtBodyService.modifyLocalBodyForName(localGovtBodyForm, request, httpsession);
					String[] val = check.split(",");

					if (httpsession.getAttribute("validGovermentUpload") != null) {
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) httpsession.getAttribute("validGovermentUpload");
						//boolean insertGovtTableFlag = 
						localGovtBodyService.saveDataInAttachCoverageLBody(validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
					}
					if (check != null) {
						httpsession.removeAttribute("validationErrorOne");
						// String aMessage =
						// "Modify Local Gov. Body Name is successfully updated. Local Body Code is "
						// +
						// localGovtBodyForm.getLocalBodyDetails().get(0).getLocalBodyCode();

						String aMessage = "The local government body " + lbDetails.get(0).getLocalBodyNameEnglish().trim() + " was updated successfully.";

						mav = new ModelAndView("success");
						mav.addObject("msgid", aMessage.trim());
						/* Code for deleting draft file should be here */
						if (isFromDraft != null && !"".equals(isFromDraft)) {
							bodyDraftController.deleteDraft(model, request);
						}
						/* End */
					}
					startSmsThread(localGovtBodyForm, check, val);
				} else if (govtOrderConfig.equals("govtOrderGenerate")) {
					int templCode = 0;
					GovernmentOrderForm govtOrderForm = new GovernmentOrderForm();
					localGovtBodyForm.setOperation("LBCCNM");

					templCode = Integer.parseInt(localGovtBodyForm.getTemplateList());
					httpsession.setAttribute("formbean", localGovtBodyForm);
					String templateBodylbSrc = govtOrderService.previewTemplate(templCode, httpsession);
					govtOrderForm.setTemplateBodySrc(templateBodylbSrc);
					mav = new ModelAndView("previewGovtOrder");
					mav.addObject("governmentOrder", govtOrderForm);
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

	/**
	 * @param localGovtBodyForm
	 * @param check
	 * @param val
	 */
	private void startSmsThread(LocalGovtBodyForm localGovtBodyForm, String check, String[] val) {
		if (check != null) {
			if (localGovtBodyForm.getLbType() == 'P') {
				int t_code = Integer.parseInt(val[0]);
				char t_type = 'R';
				EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
				est.start();
			}

			if (localGovtBodyForm.getLbType() == 'T') {
				int t_code = Integer.parseInt(val[0]);
				char t_type = 'T';
				EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
				est.start();
			}

			if (localGovtBodyForm.getLbType() == 'U') {
				int t_code = Integer.parseInt(val[0]);
				char t_type = 'U';
				EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
				est.start();

			}
		}
	}

	@RequestMapping(value = "/saveLocalBodyChangeName", method = RequestMethod.POST)
	public ModelAndView saveLocalBodyChangeName(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		String check = null;
		try {
			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			check = localGovtBodyService.modifyLocalBodyForName(localGovtBodyForm, request, session);
			String[] val = check.split(",");

			if (check != null) {
				if (session.getAttribute("validGovermentGenerateUpload") != null) {
					//boolean insertGovtTableFlag = 
					localGovtBodyService.saveDataInAttachGenerateLocalBodyUrbanTypeModify(localGovtBodyForm, request.getSession(), Integer.parseInt(val[1]));
				}

				if (check != null) {
					if (localGovtBodyForm.getLbType() == 'P') {
						int t_code = Integer.parseInt(val[0]);
						char t_type = 'R';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}
					if (localGovtBodyForm.getLbType() == 'T') {
						int t_code = Integer.parseInt(val[0]);
						char t_type = 'T';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}

					if (localGovtBodyForm.getLbType() == 'U') {
						int t_code = Integer.parseInt(val[0]);
						char t_type = 'U';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}
				}

				String aMessage = "Modify Local Gov. Body Coverage Area is successfully updated. Local Body Code is " + localGovtBodyForm.getLocalBodyCode();
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/*
	 * Declare Lbdetails,districtPanchayatList,localBodyTypelist variable for
	 * manage Urban localBody Details:Update Local Body Coverage Area.
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/modifyLocalBodyTypeForUrban", method = RequestMethod.POST)
	public ModelAndView modifyGovtLocalBodyTypeForUrban(@Valid @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpsession) {

		ModelAndView mav = null;
		//ModelAndView mv = null;
		// ModelAndView mv;
		// Integer check = null;
		String check = null;
		int operationCode = 22;
		char lbType = 'U';
		String conLocalBTypeList = null;
		//List<Attachment> alreadyAttachList = new ArrayList<Attachment>();
		//List<MapAttachment> alreadyAttachMapList = new ArrayList<MapAttachment>();
		List<LocalBodyDetails> lbDetails = localGovtBodyForm.getLocalBodyDetails();

		try {
			// ////////code for government order
			// checking///////////////////////////////////
			//AddAttachmentBean addAttachmentBean = null;
			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'U');
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
			Map<String, String> hMap = new HashMap<String, String>();

			// hMap =
			// configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode,
			// 10, 'V');
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			// String message = hMap.get("message");
			// String govtOrderConfig = hMap.get("govtOrder");//
			// values==govtOrderUpload,govtOrderGenerate
			// String mapConfig = hMap.get("mapUpload");// values==true,false
			localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);
			// ////////code for government order
			// checking///////////////////////////////////
			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);

			if (localGovtBodyForm.getLocalBodyTypeCode() != "0") {
				List<LocalBodyType> localbType = localGovtBodyService.getLocalBodyTypeNamebyChangeLBN(Integer.parseInt(localGovtBodyForm.getLocalBodyTypeCode()));
				if (localbType != null) {
					Iterator<LocalBodyType> localbodytypeItr = localbType.iterator();
					StringBuffer finalcontLocalbodytypeN = new StringBuffer();

					while (localbodytypeItr.hasNext()) {
						finalcontLocalbodytypeN.append(localbodytypeItr.next().getLocalBodyTypeName().trim());
					}
					conLocalBTypeList = finalcontLocalbodytypeN.toString();
					// onLocalBTypeList=conLocalBTypeList.substring(0,
					// finalcontLocalbodytypeN.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					localGovtBodyForm.setNewlocalbodyTypeNamehidden(conLocalBTypeList);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (localGovtBodyForm.getAttachFile1() != null) {
				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderforChangeCoverage(request, localGovtBodyForm, result, mav, httpsession);
				httpsession.setAttribute("validGovermentUpload", validFileGovtUpload);
			}

			// CustomValidatorLBModify cValidatorModify = new
			// CustomValidatorLBModify();
			cValidatorModify.modifyValidationLBTypeULB(localGovtBodyForm, result, request, httpsession);

			if (result.hasErrors()) {
				// if (govtOrderConfig != null && mapConfig != null) {

				// mav.addObject("hideMap", mapConfig);
				// mav.addObject("govtOrderConfig", govtOrderConfig);
				
				if (stateCode != null) {
					if (httpsession.getAttribute("formbean") != null) {
						httpsession.removeAttribute("formbean");
						httpsession.removeValue("formbean");
					}
					request.setAttribute("stateCode", stateCode);
				}
				if (localGovtBodyForm.getLgd_LBlevelChk() == 'U') {
					model.addAttribute("localBodyDetails", lbDetails);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {
					model.addAttribute("localBodyDetails", lbDetails);
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'I') {
					model.addAttribute("localBodyDetails", lbDetails);
					model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
					localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'V') {
					model.addAttribute("localBodyDetails", lbDetails);
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
				}

				if (httpsession.getAttribute("validationErrorOne1") != null) {
					String validateAttachment = (String) httpsession.getAttribute("validationErrorOne1");
					request.setAttribute("validationErrorOne", validateAttachment);
				}
				httpsession.removeAttribute("validationErrorOne1");
				mav = new ModelAndView("modify_govt_local_bodyurbantype");
				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				// return mav;
				// }
			} else {

				

				if (govtOrderConfig.equals("govtOrderUpload")) {

					

					check = localGovtBodyService.modifyLocalBodyForUrbanType(localGovtBodyForm, request, stateCode,userId);

					String[] val = check.split(",");


					/*
					 * if (localGovtBodyForm.getAttachFile1() != null) {
					 * List<AttachedFilesForm> validFileGovtUpload =
					 * fileUploadUtility
					 * .ValidateAndUploadFileGovtOrderforChangeCoverage
					 * (request,localGovtBodyForm,result);
					 * httpsession.setAttribute("validGovermentUpload",
					 * validFileGovtUpload); }
					 */

					if (httpsession.getAttribute("validGovermentUpload") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) httpsession.getAttribute("validGovermentUpload");
						//boolean insertGovtTableFlag = 
						localGovtBodyService.saveDataInAttachCoverageLBody(validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
					}
					if (check != null) {
						if (localGovtBodyForm.getLbType() == 'U') {
							int t_code = Integer.parseInt(val[0]);
							;
							char t_type = 'U';
							EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
							est.start();
						}
					}
				} else if (govtOrderConfig.equals("govtOrderGenerate")) {
					int templCode = 0;
					GovernmentOrderForm govtOrderForm = new GovernmentOrderForm();
					localGovtBodyForm.setOperation("LBTC");

					templCode = Integer.parseInt(localGovtBodyForm.getTemplateList());

					httpsession.setAttribute("formbean", localGovtBodyForm);
					String templateBodylbSrc = govtOrderService.previewTemplate(templCode, httpsession);
					govtOrderForm.setTemplateBodySrc(templateBodylbSrc);
					mav = new ModelAndView("previewGovtOrder");
					mav.addObject("governmentOrder", govtOrderForm);
				}
				if (govtOrderConfig.equals("govtOrderUpload")) {
					if (check != null) {
						httpsession.removeAttribute("validationErrorOne");
						String aMessage = "The Local Government Body Type was updated successfully";
						// String aMessage =
						// "Local Body successfully modified. Local Body Code is "
						// +
						// localGovtBodyForm.getLocalBodyDetails().get(0).getLocalBodyCode();
						mav = new ModelAndView("success");
						mav.addObject("msgid", aMessage);
					}
				}
			}
			/*
			 * else { String aMessage =
			 * "Local Body successfully modified. Local Body Code is " +
			 * localGovtBodyForm
			 * .getLocalBodyDetails().get(0).getLocalBodyCode(); mav = new
			 * ModelAndView("success"); mav.addObject("msgid", aMessage);
			 * 
			 * }
			 */
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/saveUrbanLocalGovtBodyChangeType", method = RequestMethod.POST)
	public ModelAndView saveUrbanLocalGovtBodyChangeType(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		String check = null;
		try {
			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			check = localGovtBodyService.modifyLocalBodyForUrbanType(localGovtBodyForm, request, stateCode,userId);
			String[] val = check.split(",");

			if (check != null) {
				if (session.getAttribute("validGovermentGenerateUpload") != null) {
					//boolean insertGovtTableFlag = 
					localGovtBodyService.saveDataInAttachGenerateLocalBodyUrbanTypeModify(localGovtBodyForm, request.getSession(), Integer.parseInt(val[1]));
				}
				if (localGovtBodyForm.getLbType() == 'U') {
					int t_code = Integer.parseInt(val[0]);
					;
					char t_type = 'U';
					EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
					est.start();
				}
				String aMessage = "Modify Local Gov. Body Type is successfully updated";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/*
	 * Declare Lbdetails,districtPanchayatList variable for manage PRI localBody
	 * Details:Get Local Body Details .
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/modifyLocalBodyTypeForPri", method = RequestMethod.POST)
	public ModelAndView modifyGovtLocalBodyTypeForPri(@Valid @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpsession) {

		ModelAndView mav = null;
		//ModelAndView mv;
		String check = null;
		int operationCode = 0;
		String conLocalBParentList = null;
		char lbType;
		//List<Attachment> alreadyAttachList = new ArrayList<Attachment>();
		//List<MapAttachment> alreadyAttachMapList = new ArrayList<MapAttachment>();
		List<LocalBodyDetails> lbDetails = localGovtBodyForm.getLocalBodyDetails();

		try {
			// ////////code for government order
			// checking///////////////////////////////////
			operationCode = localGovtBodyForm.getOperationCode();
			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			Map<String, String> hMap = new HashMap<String, String>();
			operationCode = localGovtBodyForm.getOperationCode();
			lbType = localGovtBodyForm.getLbType();

			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			// String message = hMap.get("message");
			localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);

			// ////////code for government order
			// checking///////////////////////////////////
			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);

			if (!localGovtBodyForm.getLocalBodyNameEnglishList().equals("0")) {
				List<Localbody> localbParent = localGovtBodyService.getLocalBodyNamebyLBIDforHeiRChange(localGovtBodyForm.getLocalBodyNameEnglishList());
				if (localbParent != null) {
					Iterator<Localbody> localbodyParentItr = localbParent.iterator();
					StringBuffer finalcontLocalbodyParent = new StringBuffer();

					while (localbodyParentItr.hasNext()) {
						finalcontLocalbodyParent.append(localbodyParentItr.next().getLocalBodyNameEnglish().trim());
					}
					conLocalBParentList = finalcontLocalbodyParent.toString();
					localGovtBodyForm.setNewlocalbodyParentNamehidden(conLocalBParentList);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (localGovtBodyForm.getAttachFile1() != null) {
				List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderforChangeCoverage(request, localGovtBodyForm, result, mav, httpsession);
				httpsession.setAttribute("validGovermentUpload", validFileGovtUpload);
			}

			// CustomValidatorLBModify cValidatorModify = new
			// CustomValidatorLBModify();
			cValidatorModify.modifyValidationLBTopTier(localGovtBodyForm, result, request, httpsession);

			if (result.hasErrors()) {
				if (govtOrderConfig != null && mapConfig != null) {

					
					if (stateCode != null) {

						if (httpsession.getAttribute("formbean") != null) {
							httpsession.removeAttribute("formbean");
							httpsession.removeValue("formbean");
						}
						request.setAttribute("stateCode", stateCode);
					}
					// districtPanchayatList =
					// localGovtBodyService.getPanchayatListbyParentCategory(stateCode,
					// category, level);

					if (localGovtBodyForm.getLgd_LBlevelChk() == 'U') {
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {
						model.addAttribute("localBodyDetails", lbDetails);
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'I') {
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'V') {
						model.addAttribute("localBodyDetails", lbDetails);
						model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
						localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
					}
					if (httpsession.getAttribute("validationErrorOne1") != null) {
						String validateAttachment = (String) httpsession.getAttribute("validationErrorOne1");
						request.setAttribute("validationErrorOne", validateAttachment);
					}
					httpsession.removeAttribute("validationErrorOne1");
					mav = new ModelAndView("modify_govt_local_bodyfortype");
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					/* Added by Sushil on 05-12-2014 */
					int parentLBCode = localGovtBodyService.getParentLblccode(localGovtBodyForm.getLocalBodyCode());
					districtPanchayatList = localGovtBodyService.getPanchayatListbyParentCategoryFChangeTier(stateCode, ' ', ' ', parentLBCode);
					model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
					return mav;
				}
			} else {

				if (govtOrderConfig.equals("govtOrderUpload")) {

					check = localGovtBodyService.modifyLocalBodyForPriType(localGovtBodyForm, request, userId);

					String[] val = check.split(",");


					/*
					 * if (localGovtBodyForm.getAttachFile1() != null) {
					 * List<AttachedFilesForm> validFileGovtUpload =
					 * fileUploadUtility
					 * .ValidateAndUploadFileGovtOrderforChangeCoverage
					 * (request,localGovtBodyForm,result);
					 * httpsession.setAttribute("validGovermentUpload",
					 * validFileGovtUpload); }
					 */
					/* Modified by Sushil on 24-11-2014 */
					if (httpsession.getAttribute("validGovermentUpload") != null && val.length > 1 && val[1] != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) httpsession.getAttribute("validGovermentUpload");
						//boolean insertGovtTableFlag = 
						localGovtBodyService.saveDataInAttachCoverageLBody(validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
					}
					if (check != null && !"".equals(val[0])) {
						String str = val[0];
						if (CommonUtil.isStringInt(str)) {
							if (localGovtBodyForm.getLbType() == 'P') {
								int t_code = Integer.parseInt(val[0]);
								char t_type = 'R';
								EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
								est.start();
							}
							if (localGovtBodyForm.getLbType() == 'T') {
								int t_code = Integer.parseInt(val[0]);
								char t_type = 'T';
								EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
								est.start();
							}
						} else {
							model.addAttribute("msg", str);
							mav = new ModelAndView("modify_govt_local_bodyfortype");
							mav.addObject("hideMap", mapConfig);
							mav.addObject("govtOrderConfig", govtOrderConfig);
							mav.addObject("localGovtBodyForm", localGovtBodyForm);
							/* Modified by Sushil on 05-12-2014 */
							int parentLBCode = localGovtBodyService.getParentLblccode(localGovtBodyForm.getLocalBodyCode());
							districtPanchayatList = localGovtBodyService.getPanchayatListbyParentCategoryFChangeTier(stateCode, ' ', ' ', parentLBCode);
							model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
							return mav;
						}
					}

				}

				else if (govtOrderConfig.equals("govtOrderGenerate")) {
					int templCode = 0;
					GovernmentOrderForm govtOrderForm = new GovernmentOrderForm();
					localGovtBodyForm.setOperation("LBHRC");

					templCode = Integer.parseInt(localGovtBodyForm.getTemplateList());

					httpsession.setAttribute("formbean", localGovtBodyForm);
					String templateBodylbSrc = govtOrderService.previewTemplate(templCode, httpsession);
					govtOrderForm.setTemplateBodySrc(templateBodylbSrc);
					mav = new ModelAndView("previewGovtOrder");
					mav.addObject("governmentOrder", govtOrderForm);
				}
				if (govtOrderConfig.equals("govtOrderUpload")) {
					if (check != null) {

						httpsession.removeAttribute("validationErrorOne");
						// String aMessage =
						// "Local Body successfully modified. Local Body Code is "
						// +
						// localGovtBodyForm.getLocalBodyDetails().get(0).getLocalBodyCode();
						String aMessage = "The local government body " + lbDetails.get(0).getLocalBodyNameEnglish().trim() + " was updated successfully.";
						mav = new ModelAndView("success");
						mav.addObject("msgid", aMessage.trim());
					}
				}

				/*
				 * List<AttachedFilesForm> metaInfoOfToBeAttachedFileList =
				 * attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
				 * List<AttachedFilesForm> metaInfoOfToBeAttachedMapList =
				 * attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
				 * 
				 * check =
				 * localGovtBodyService.modifyLocalBodyForPriType(localGovtBodyForm
				 * , metaInfoOfToBeAttachedFileList,
				 * metaInfoOfToBeAttachedMapList, request, httpsession); if
				 * (metaInfoOfToBeAttachedFileList != null &&
				 * metaInfoOfToBeAttachedFileList.size() > 0) { String
				 * saveAttachment = attachmentHandler.saveMetaDataINFileSystem(
				 * metaInfoOfToBeAttachedFileList, addAttachmentBean); } if
				 * (metaInfoOfToBeAttachedMapList != null &&
				 * metaInfoOfToBeAttachedMapList.size() > 0) { String
				 * saveAttachment = attachmentHandler.saveMetaDataINFileSystem(
				 * metaInfoOfToBeAttachedMapList, addAttachmentBean2); }
				 * 
				 * if (check != null) { if (localGovtBodyForm.getLbType() ==
				 * 'P') { int t_code = check; char t_type = 'R'; EmailSmsThread
				 * est = new EmailSmsThread(t_code, t_type, emailService);
				 * est.start(); } if (localGovtBodyForm.getLbType() == 'T') {
				 * int t_code = check; char t_type = 'T'; EmailSmsThread est =
				 * new EmailSmsThread(t_code, t_type, emailService);
				 * est.start(); }
				 * 
				 * httpsession.removeAttribute("validationErrorOne"); String
				 * aMessage =
				 * "Modify Local Gov. Body Parent is successfully updated"; mav
				 * = new ModelAndView("success"); mav.addObject("msgid",
				 * aMessage); return mav; } else {
				 * 
				 * String aMessage =
				 * "Local Body successfully modified. Local Body Code is " +
				 * localGovtBodyForm
				 * .getLocalBodyDetails().get(0).getLocalBodyCode(); mav = new
				 * ModelAndView("success"); mav.addObject("msgid", aMessage); }
				 */

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

	@RequestMapping(value = "/saveParentHeirarchyChange", method = RequestMethod.POST)
	public ModelAndView saveParentHeirarchyChange(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		String check = null;
		try {
			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			check = localGovtBodyService.modifyLocalBodyForPriType(localGovtBodyForm, request, userId);
			String[] val = check.split(",");

			if (check != null) {
				if (session.getAttribute("validGovermentGenerateUpload") != null) {
					@SuppressWarnings("unused")
					boolean insertGovtTableFlag = localGovtBodyService.saveDataInAttachGenerateLocalBodyUrbanTypeModify(localGovtBodyForm, request.getSession(), Integer.parseInt(val[1]));
				}
				if (localGovtBodyForm.getLbType() == 'P') {
					int t_code = Integer.parseInt(val[0]);
					char t_type = 'R';
					EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
					est.start();
				}
				if (localGovtBodyForm.getLbType() == 'T') {
					int t_code = Integer.parseInt(val[0]);
					char t_type = 'T';
					EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
					est.start();
				}
				String aMessage = "Local Body successfully modified. Local Body Code is " + localGovtBodyForm.getLocalBodyDetails().get(0).getLocalBodyCode();
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/*
	 * Declare Lbdetails,districtPanchayatList,localBodyTypelist variable for
	 * manage PRI localBody Details:Update Local Body Coverage Area.
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	@RequestMapping(value = "/modifyLocalBodyCoveredarea", method = RequestMethod.POST)
	public ModelAndView modifyGovtLocalBodyCoveredArea(@Valid @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpsession) {

		ModelAndView mav = null;
		ModelAndView mv;
		String check = null;
		int operationCode = 0;
		char lbType;
		String contDist = null;
		String contSubDist = null;
		String contVillage = null;
		String finalconSubDist = null;
		String finalLandregion = null;
		String contSubDistMapped = null;
		String contSubDistUnMapped = null;
		String contDistMapped = null;
		String contDistUnMapped = null;
		String contVillMapped = null;
		String contVillUnMapped = null;
		List<Attachment> alreadyAttachList = new ArrayList<Attachment>();
		List<MapAttachment> alreadyAttachMapList = new ArrayList<MapAttachment>();
		List<LocalBodyDetails> lbDetails = localGovtBodyForm.getLocalBodyDetails();
		try {
			// ////////code for government order
			// checking///////////////////////////////////
			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			Map<String, String> hMap = new HashMap<String, String>();
			operationCode = localGovtBodyForm.getOperationCode();
			lbType = localGovtBodyForm.getLbType();

			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			// String message = hMap.get("message");
			localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);
			// ////////code for government order
			// checking///////////////////////////////////
			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);

			/*
			 * Get value after comparison of logged in state code. Referred in
			 * conditions.
			 * 
			 * @author vinay yadav 23-12-2013
			 */
			boolean isDisttLevel = Boolean.FALSE;

			if (localGovtBodyForm.getLbType() == 'U') {
				isDisttLevel = ApplicationConstant.checkStateLBOnlyDisttWise(stateCode);
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null) {
					if (isDisttLevel) {
						contSubDist = localGovtBodyService.getDistrictNamebyCovChangeULB(localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban());
						localGovtBodyForm.setExistingCovLanRegion(contSubDist);
					} else {
						List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyCovChangeULB(localGovtBodyForm);
						if (conSubDistrict != null) {
							Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
							StringBuffer finalcontSubDist = new StringBuffer();
							while (subdistItr.hasNext()) {
								finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDist = finalcontSubDist.toString();
							contSubDist = contSubDist.substring(0, finalcontSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							localGovtBodyForm.setExistingCovLanRegion(contSubDist);
						}
					}
				}

				if (localGovtBodyForm.isLgd_LBExistCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null) {
						if (isDisttLevel) {
							String urbanLBSubdistrictListDest = localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest().replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
							if (urbanLBSubdistrictListDest.contains(":")) {
								StringBuilder builString = new StringBuilder();
								for (String st : urbanLBSubdistrictListDest.split("\\,")) {
									builString.append(st.split("\\:")[1]).append(",");
								}
								contSubDistMapped = localGovtBodyService.getDistrictNamebyCovChangeULB(builString.toString());
							}
						} else {
							List<Subdistrict> conSubDistrictMapped = localGovtBodyService.getSubDistrictNamebyDisIDULBFinal(localGovtBodyForm);
							if (conSubDistrictMapped != null) {
								Iterator<Subdistrict> subdistMappedItr = conSubDistrictMapped.iterator();
								StringBuffer finalcontSubDistMapped = new StringBuffer();
								while (subdistMappedItr.hasNext()) {
									finalcontSubDistMapped.append(subdistMappedItr.next().getSubdistrictNameEnglish().trim() + ",");
								}
								contSubDistMapped = finalcontSubDistMapped.toString();
								contSubDistMapped = contSubDistMapped.substring(0, finalcontSubDistMapped.length() - 1);
								// localGovtBodyForm.setContSubDistrict(contSubDist);
							}
						}
					}
				}
				if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
						if (isDisttLevel) {
							String urbanLBDistUmappedDest = localGovtBodyForm.getLgd_UrbanLBDistUmappedDest();
							urbanLBDistUmappedDest = urbanLBDistUmappedDest.replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
							contSubDistUnMapped = localGovtBodyService.getDistrictNamebyCovChangeULB(urbanLBDistUmappedDest);

						} else {
							List<Subdistrict> conunmappedSubDistrict = localGovtBodyService.getunMappedSubDistrictNamebyDisIDULB(localGovtBodyForm);
							if (conunmappedSubDistrict != null) {
								Iterator<Subdistrict> subdistUnMappedItr = conunmappedSubDistrict.iterator();
								StringBuffer finalcontSubDistUnMapped = new StringBuffer();

								while (subdistUnMappedItr.hasNext()) {
									finalcontSubDistUnMapped.append(subdistUnMappedItr.next().getSubdistrictNameEnglish().trim() + ",");
								}
								contSubDistUnMapped = finalcontSubDistUnMapped.toString();
								contSubDistUnMapped = contSubDistUnMapped.substring(0, finalcontSubDistUnMapped.length() - 1);
								// finalcontDist = finalcontDist.substring(0,
								// finalcontDist.length() - 1);
							}
						}
					}
				}

				if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
						finalconSubDist = contSubDistMapped + "," + contSubDistUnMapped;
						localGovtBodyForm.setContSubDistrict(finalconSubDist);
					}
					if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
						finalconSubDist = contSubDistMapped;
						localGovtBodyForm.setContSubDistrict(finalconSubDist);
					}
				}
				if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
						finalconSubDist = contSubDistMapped;
						localGovtBodyForm.setContSubDistrict(finalconSubDist);
					}
				}
				if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
						finalconSubDist = contSubDistUnMapped;
						localGovtBodyForm.setContSubDistrict(finalconSubDist);
					}
				}
			}

			if ((localGovtBodyForm.getLbType() == 'P') || (localGovtBodyForm.getLbType() == 'T')) {
				/*
				 * if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {
				 */
				// For District Panchayat Available Land Regions of PRI and
				// Traditional Local Body----START
				if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null) {
					List<District> condistrict = localGovtBodyService.getDistrictNamebyCovChangePRITrad(localGovtBodyForm);
					if (condistrict != null) {
						Iterator<District> distItr = condistrict.iterator();
						StringBuffer finalcontDist = new StringBuffer();

						while (distItr.hasNext()) {
							finalcontDist.append(distItr.next().getDistrictNameEnglish().trim() + ",");
						}
						contDist = finalcontDist.toString();
						contDist = contDist.substring(0, finalcontDist.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingDistrict(contDist);
					}
				}
				if (localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null) {
					List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyCovChangePRITrad(localGovtBodyForm);
					if (conSubDistrict != null) {
						Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
						StringBuffer finalcontSubDist = new StringBuffer();

						while (subdistItr.hasNext()) {
							finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
						}
						contSubDist = finalcontSubDist.toString();
						contSubDist = contSubDist.substring(0, finalcontSubDist.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingSubDistrict(contSubDist);
					}
				}
				if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null) {
					List<Village> conVillage = localGovtBodyService.getVillageNamebyCovChangePRITrad(localGovtBodyForm);
					if (conVillage != null) {
						Iterator<Village> villItr = conVillage.iterator();
						StringBuffer finalcontVillage = new StringBuffer();

						while (villItr.hasNext()) {
							finalcontVillage.append(villItr.next().getVillageNameEnglish().trim() + ",");
						}
						contVillage = finalcontVillage.toString();
						contVillage = contVillage.substring(0, finalcontVillage.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingVillage(contVillage);
					}
				}
				// For District Panchayat Available Land Regions of PRI and
				// Traditional Local Body----END

				// For Intermediate Panchayat Available Land Regions of PRI and
				// Traditional Local Body----START
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null) {
					List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyCovChangePRITradInter(localGovtBodyForm);
					if (conSubDistrict != null) {
						Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
						StringBuffer finalcontSubDist = new StringBuffer();

						while (subdistItr.hasNext()) {
							finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
						}
						contSubDist = finalcontSubDist.toString();
						contSubDist = contSubDist.substring(0, finalcontSubDist.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingSubDistrict(contSubDist);
					}
				}
				if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null) {
					List<Village> conVillage = localGovtBodyService.getVillageNamebyCovChangePRITradInter(localGovtBodyForm);
					if (conVillage != null) {
						Iterator<Village> villItr = conVillage.iterator();
						StringBuffer finalcontVillage = new StringBuffer();

						while (villItr.hasNext()) {
							finalcontVillage.append(villItr.next().getVillageNameEnglish().trim() + ",");
						}
						contVillage = finalcontVillage.toString();
						contVillage = contVillage.substring(0, finalcontVillage.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingVillage(contVillage);
					}
				}
				// For Intermediate Panchayat Available Land Regions of PRI and
				// Traditional Local Body----END

				// For Village Panchayat Available Land Regions of PRI and
				// Traditional Local Body----START

				if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null) {
					List<Village> conVillage = localGovtBodyService.getVillageNamebyCovChangePRITradVillFinal(localGovtBodyForm);
					if (conVillage != null) {
						Iterator<Village> villItr = conVillage.iterator();
						StringBuffer finalcontVillage = new StringBuffer();

						while (villItr.hasNext()) {
							finalcontVillage.append(villItr.next().getVillageNameEnglish().trim() + ",");
						}
						contVillage = finalcontVillage.toString();
						contVillage = contVillage.substring(0, finalcontVillage.length() - 1);
						// finalcontDist = finalcontDist.substring(0,
						// finalcontDist.length() - 1);
						localGovtBodyForm.setExistingVillage(contVillage);
					}
				}

				// For Village Panchayat Available Land Regions of PRI and
				// Traditional Local Body----END

				if (localGovtBodyForm.isLgd_LBExistCheck()) {
					// For Mapped Land Regions of DIstrict PAnchayat of PRI and
					// Traditional Local Body----START
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null) {
						List<District> contDistrict = localGovtBodyService.getDistrictNamebyDisIDCovChange(localGovtBodyForm);
						if (contDistrict != null) {
							Iterator<District> distItr = contDistrict.iterator();
							StringBuffer finalcontDist = new StringBuffer();

							while (distItr.hasNext()) {
								finalcontDist.append(distItr.next().getDistrictNameEnglish().trim() + ",");
							}
							contDistMapped = finalcontDist.toString();
							contDistMapped = contDistMapped.substring(0, finalcontDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}

					if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null) {
						List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyDisIDCovChange(localGovtBodyForm);
						if (conSubDistrict != null) {
							Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
							StringBuffer finalcontSubDist = new StringBuffer();

							while (subdistItr.hasNext()) {
								finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistMapped = finalcontSubDist.toString();
							contSubDistMapped = contSubDistMapped.substring(0, finalcontSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContSubDistrict(contSubDist);
						}
					}

					if (localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null) {
						List<Village> conVillage = localGovtBodyService.getconVillageNamebyVillIDforChCov(localGovtBodyForm);
						if (conVillage != null) {
							Iterator<Village> subVillageItr = conVillage.iterator();
							StringBuffer finalcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillMapped = finalcontVillage.toString();
							contVillMapped = contVillMapped.substring(0, finalcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContVillage(contVillage);
						}
					}
					// For Mapped Land Regions of DIstrict Panchayat of PRI and
					// Traditional Local Body----END

					// For Mapped Land Regions of Intermediate Panchayat of PRI
					// and Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null) {
						List<Subdistrict> conSubDistrict = localGovtBodyService.getSubDistrictNamebyDisIDInter(localGovtBodyForm);
						if (conSubDistrict != null) {
							Iterator<Subdistrict> subdistItr = conSubDistrict.iterator();
							StringBuffer finalcontSubDist = new StringBuffer();

							while (subdistItr.hasNext()) {
								finalcontSubDist.append(subdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistMapped = finalcontSubDist.toString();
							contSubDistMapped = contSubDistMapped.substring(0, finalcontSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContSubDistrict(contSubDist);
						}
					}

					if (localGovtBodyForm.getLgd_LBVillageDestLatICA() != null) {
						List<Village> conVillage = localGovtBodyService.getconVillageNamebyVillIDVill(localGovtBodyForm);
						if (conVillage != null) {
							Iterator<Village> subVillageItr = conVillage.iterator();
							StringBuffer finalcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillMapped = finalcontVillage.toString();
							contVillMapped = contVillMapped.substring(0, finalcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContVillage(contVillage);
						}
					}

					// For Mapped Land Regions of Intermediate Panchayat of PRI
					// and Traditional Local Body----END

					// For Mapped Land Regions of Village Panchayat of PRI and
					// Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null) {
						List<Village> conVillage = localGovtBodyService.getconVillageNamebyVillIDVPFin(localGovtBodyForm);
						if (conVillage != null) {
							Iterator<Village> subVillageItr = conVillage.iterator();
							StringBuffer finalcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillMapped = finalcontVillage.toString();
							contVillMapped = contVillMapped.substring(0, finalcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
							// localGovtBodyForm.setContVillage(contVillage);
						}
					}

					// For Mapped Land Regions of Village Panchayat of PRI and
					// Traditional Local Body----END
				}

				if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {

					// For Un Mapped Land Regions of District Panchayat of PRI
					// and Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null) {
						List<District> contUnmappedDistrict = localGovtBodyService.getUnmappedDistrictNamebyDisID(localGovtBodyForm);
						if (contUnmappedDistrict != null) {
							Iterator<District> unMappeddistItr = contUnmappedDistrict.iterator();
							StringBuffer finalcontunMappedDist = new StringBuffer();

							while (unMappeddistItr.hasNext()) {
								finalcontunMappedDist.append(unMappeddistItr.next().getDistrictNameEnglish().trim() + ",");
							}
							contDistUnMapped = finalcontunMappedDist.toString();
							contDistUnMapped = contDistUnMapped.substring(0, finalcontunMappedDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);

						}
					}

					if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null) {
						List<Subdistrict> contUnmappedSubDistrict = localGovtBodyService.getUnmappedSubDistrictNamebyDisID(localGovtBodyForm);
						if (contUnmappedSubDistrict != null) {
							Iterator<Subdistrict> unMappedsubdistItr = contUnmappedSubDistrict.iterator();
							StringBuffer finalcontunMappedSubDist = new StringBuffer();

							while (unMappedsubdistItr.hasNext()) {
								finalcontunMappedSubDist.append(unMappedsubdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistMapped = finalcontunMappedSubDist.toString();
							contSubDistMapped = contSubDistMapped.substring(0, finalcontunMappedSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}
					if (localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						List<Village> conUnmappedVillage = localGovtBodyService.getconunmappedVillageNamebyVillageID(localGovtBodyForm);
						if (conUnmappedVillage != null) {
							Iterator<Village> subVillageItr = conUnmappedVillage.iterator();
							StringBuffer finalunmappedcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalunmappedcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillUnMapped = finalunmappedcontVillage.toString();
							contVillUnMapped = contVillUnMapped.substring(0, finalunmappedcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}

					// For Un Mapped Land Regions of District Panchayat of PRI
					// and Traditional Local Body----END

					// For Un Mapped Land Regions of Intermediate Panchayat of
					// PRI and Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null) {
						List<Subdistrict> contUnmappedSubDistrict = localGovtBodyService.getUnmappedSubDistrictNamebyDisIDforIP(localGovtBodyForm);
						if (contUnmappedSubDistrict != null) {
							Iterator<Subdistrict> unMappedsubdistItr = contUnmappedSubDistrict.iterator();
							StringBuffer finalcontunMappedSubDist = new StringBuffer();

							while (unMappedsubdistItr.hasNext()) {
								finalcontunMappedSubDist.append(unMappedsubdistItr.next().getSubdistrictNameEnglish().trim() + ",");
							}
							contSubDistMapped = finalcontunMappedSubDist.toString();
							contSubDistMapped = contSubDistMapped.substring(0, finalcontunMappedSubDist.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}
					if (localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						List<Village> conUnmappedVillage = localGovtBodyService.getconunmappedVillageNamebyVillageIDforIP(localGovtBodyForm);
						if (conUnmappedVillage != null) {
							Iterator<Village> subVillageItr = conUnmappedVillage.iterator();
							StringBuffer finalunmappedcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalunmappedcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillUnMapped = finalunmappedcontVillage.toString();
							contVillUnMapped = contVillUnMapped.substring(0, finalunmappedcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}

					// For Un Mapped Land Regions of Intermediate Panchayat of
					// PRI and Traditional Local Body----END

					// For Un Mapped Land Regions of Village Panchayat of PRI
					// and Traditional Local Body----START

					if (localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						List<Village> conUnmappedVillage = localGovtBodyService.getconunmappedVillageNamebyVillageIDforVP(localGovtBodyForm);
						if (conUnmappedVillage != null) {
							Iterator<Village> subVillageItr = conUnmappedVillage.iterator();
							StringBuffer finalunmappedcontVillage = new StringBuffer();

							while (subVillageItr.hasNext()) {
								finalunmappedcontVillage.append(subVillageItr.next().getVillageNameEnglish().trim() + ",");
							}
							contVillUnMapped = finalunmappedcontVillage.toString();
							contVillUnMapped = contVillUnMapped.substring(0, finalunmappedcontVillage.length() - 1);
							// finalcontDist = finalcontDist.substring(0,
							// finalcontDist.length() - 1);
						}
					}
				}

				// For Un Mapped Land Regions of Village Panchayat of PRI and
				// Traditional Local Body----END
				// Appending Mapped and UnMapped Data of District
				// Panchayat----START

				if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						finalLandregion = contDistMapped + "," + contSubDistMapped + "," + contVillMapped + "," + contDistUnMapped + "," + contSubDistMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						finalLandregion = contDistMapped + "," + contSubDistMapped + "," + contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}
				if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						finalLandregion = contDistMapped + "," + contSubDistMapped + "," + contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}
				if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						finalLandregion = contDistUnMapped + "," + contSubDistMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}

				// Appending Mapped and UnMapped Data of District
				// Panchayat----END

				// Appending Mapped and UnMapped Data of Intermediate
				// Panchayat----START

				if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						finalLandregion = contSubDistMapped + "," + contVillMapped + "," + contSubDistMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						finalLandregion = contSubDistMapped + "," + contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}

				if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {

					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() != null && localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						finalLandregion = contSubDistMapped + "," + contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}

				if (!localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {

					if (localGovtBodyForm.getLgd_LBInterCAreaDestList() == null && localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						finalLandregion = contSubDistMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
				}
				// Appending Mapped and UnMapped Data of Intermediate
				// Panchayat----END

				// Appending Mapped and UnMapped Data of Village
				// Panchayat----START

				if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {

					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						finalLandregion = contVillMapped + "," + contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						finalLandregion = contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						finalLandregion = contVillUnMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}
					// Appending Mapped and UnMapped Data of Village
					// Panchayat----END

				}
				if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
					if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						finalLandregion = contVillMapped;
						localGovtBodyForm.setFinalLandRegion(finalLandregion);
					}

				}

			}

			if (localGovtBodyForm.getAttachFile1() != null) {
				try {
					List<AttachedFilesForm> validFileGovtUpload = fileUploadUtility.ValidateAndUploadFileGovtOrderforChangeCoverage(request, localGovtBodyForm, result, mav, httpsession);
					httpsession.setAttribute("validGovermentUpload", validFileGovtUpload);
				} catch (Exception e) {
					log.error(e);

				}
			}

			// CustomValidatorLBModify cValidatorModify = new
			// CustomValidatorLBModify();
			cValidatorModify.modifyCoverageLB(localGovtBodyForm, result, request, httpsession);

			if (result.hasErrors()) {
				// if (govtOrderConfig != null && mapConfig != null) {

				// mav.addObject("hideMap", mapConfig);
				// mav.addObject("govtOrderConfig", govtOrderConfig);
				
				if (stateCode != null) {

					if (httpsession.getAttribute("formbean") != null) {
						httpsession.removeAttribute("formbean");
						httpsession.removeValue("formbean");
					}
					request.setAttribute("stateCode", stateCode);
				}
				if (localGovtBodyForm.getLgd_LBlevelChk() == 'U') {
					model.addAttribute("localBodyDetails", lbDetails);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {
					model.addAttribute("localBodyDetails", lbDetails);
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'I') {
					model.addAttribute("localBodyDetails", lbDetails);
					model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
					localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'V') {
					model.addAttribute("localBodyDetails", lbDetails);
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
				}

				if (httpsession.getAttribute("validationErrorOne1") != null) {
					String validateAttachment = (String) httpsession.getAttribute("validationErrorOne1");
					request.setAttribute("validationErrorOne", validateAttachment);
				}
				httpsession.removeAttribute("validationErrorOne1");
				mav = new ModelAndView("modify_govt_local_bodyforcoveragearea");
				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("selectBox", localGovtBodyForm.getLgd_LBTypeNamehidden());
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				modifyUrbanLocalBodyTypeforcoveragearea(localGovtBodyForm, result, model, httpsession, request); /*
																												 * Added
																												 * by
																												 * sushil
																												 */
				// return mav;
				// }
			} else {

				if (govtOrderConfig.equals("govtOrderUpload")) {
					// Added boolean parameter is Local Body District Wise in
					// ULB. @author Vinay Yadav 23-12-2013
					check = localGovtBodyService.modifyLocalBodyForcoverageArea(localGovtBodyForm, request, httpsession, isDisttLevel);

					String[] val = check.split(",");


					/*
					 * if (localGovtBodyForm.getAttachFile1() != null) {
					 * List<AttachedFilesForm> validFileGovtUpload =
					 * fileUploadUtility
					 * .ValidateAndUploadFileGovtOrderforChangeCoverage
					 * (request,localGovtBodyForm,result);
					 * httpsession.setAttribute("validGovermentUpload",
					 * validFileGovtUpload); }
					 */

					if (httpsession.getAttribute("validGovermentUpload") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) httpsession.getAttribute("validGovermentUpload");
						boolean insertGovtTableFlag = localGovtBodyService.saveDataInAttachCoverageLBody(validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
					}
					if (check != null) {
						if (localGovtBodyForm.getLbType() == 'P') {
							int t_code = Integer.parseInt(val[0]);
							char t_type = 'R';
							EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
							est.start();
						}
						if (localGovtBodyForm.getLbType() == 'T') {
							int t_code = Integer.parseInt(val[0]);
							char t_type = 'T';
							EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
							est.start();
						}

						if (localGovtBodyForm.getLbType() == 'U') {
							int t_code = Integer.parseInt(val[0]);
							char t_type = 'U';
							EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
							est.start();
						}
					}
				} else if (govtOrderConfig.equals("govtOrderGenerate")) {
					int templCode = 0;
					GovernmentOrderForm govtOrderForm = new GovernmentOrderForm();
					localGovtBodyForm.setOperation("LBCCH");

					templCode = Integer.parseInt(localGovtBodyForm.getTemplateList());

					httpsession.setAttribute("formbean", localGovtBodyForm);
					String templateBodylbSrc = govtOrderService.previewTemplate(templCode, httpsession);
					govtOrderForm.setTemplateBodySrc(templateBodylbSrc);
					mav = new ModelAndView("previewGovtOrder");
					mav.addObject("governmentOrder", govtOrderForm);
				}
				if (govtOrderConfig.equals("govtOrderUpload")) {
					if (check != null) {

						httpsession.removeAttribute("validationErrorOne");
						String aMessage = "The coverage area of the selected Local Government Body was modified successfully";
						mav = new ModelAndView("success");
						mav.addObject("msgid", aMessage);
					}
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

	@RequestMapping(value = "/saveLocalBodyCoveredArea", method = RequestMethod.POST)
	public ModelAndView saveLocalBodyCoveredArea(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		String check = null;
		try {
			
			// Conditional Check whether Logged in state is ULB oprate District
			// wise. @author Vinay Yadav 23-12-2013

			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			boolean isULBDisttLevel = Boolean.FALSE;
			if (localGovtBodyForm.getLbType() == 'U')
				isULBDisttLevel = ApplicationConstant.checkStateLBOnlyDisttWise(stateCode);
			check = localGovtBodyService.modifyLocalBodyForcoverageArea(localGovtBodyForm, request, session, isULBDisttLevel);
			String[] val = check.split(",");

			if (check != null) {
				if (session.getAttribute("validGovermentGenerateUpload") != null) {
					@SuppressWarnings("unused")
					boolean insertGovtTableFlag = localGovtBodyService.saveDataInAttachGenerateLocalBodyUrbanTypeModify(localGovtBodyForm, request.getSession(), Integer.parseInt(val[1]));
				}

				if (check != null) {
					if (localGovtBodyForm.getLbType() == 'P') {
						int t_code = Integer.parseInt(val[0]);
						char t_type = 'R';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}
					if (localGovtBodyForm.getLbType() == 'T') {
						int t_code = Integer.parseInt(val[0]);
						char t_type = 'T';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}

					if (localGovtBodyForm.getLbType() == 'U') {
						int t_code = Integer.parseInt(val[0]);
						char t_type = 'U';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}
				}

				String aMessage = "Modify Local Gov. Body Coverage Area is successfully updated. Local Body Code is " + localGovtBodyForm.getLocalBodyCode();
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/*
	 * Declare Lbdetails,districtPanchayatList,localBodyTypelist variable for
	 * manage PRI localBody Details:Update Map Coverage Area.
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/CorrectLocalBodyCoveredarea", method = RequestMethod.POST)
	public ModelAndView CorrectGovtLocalBodyCoveredArea(@Valid @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpsession) {

		ModelAndView mav = null;
		// ModelAndView mv;
		// boolean check = false;
		//int localbodyCd = 0;
		@SuppressWarnings("unused")
		List<Pushcoveragetopes> coveragetoPes = null;
		@SuppressWarnings("unused")
		List<Attachment> alreadyAttachList = new ArrayList<Attachment>();
		@SuppressWarnings("unused")
		List<MapAttachment> alreadyAttachMapList = new ArrayList<MapAttachment>();
		List<LocalBodyDetails> lbDetails = localGovtBodyForm.getLocalBodyDetails();

		try {
			// ////////code for government order
			// checking///////////////////////////////////
			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			@SuppressWarnings("unused")
			Map<String, String> hMap = new HashMap<String, String>();
			String catagery = (String) httpsession.getAttribute("Catagery");
			// Please first check your operation code then set it in place of 10
			// hMap =
			// configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode,
			// 10, 'V');// 10
			// is
			// operation
			// code
			// for
			// create
			// new
			// String govtOrderConfig = hMap.get("govtOrder");//
			// values==govtOrderUpload,govtOrderGenerate
			// String mapConfig = hMap.get("mapUpload");// values==true,false
			// ////////code for government order
			// checking///////////////////////////////////
			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);

			// CustomValidatorLBModify cValidatorModify = new
			// CustomValidatorLBModify();
			// cValidatorModify.modifyValidation(localGovtBodyForm, result);
			if (result.hasErrors()) {
				// if (govtOrderConfig != null && mapConfig != null) {

				// mav.addObject("hideMap", mapConfig);
				// mav.addObject("govtOrderConfig", govtOrderConfig);
				
				if (stateCode != null) {

					if (httpsession.getAttribute("formbean") != null) {
						httpsession.removeAttribute("formbean");
						httpsession.removeValue("formbean");
					}
					request.setAttribute("stateCode", stateCode);
				}
				if (localGovtBodyForm.getLgd_LBlevelChk() == 'U') {
					model.addAttribute("localBodyDetails", lbDetails);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'D') {

					model.addAttribute("localBodyDetails", lbDetails);
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'I') {

					model.addAttribute("localBodyDetails", lbDetails);
					model.addAttribute("lgd_LGdistrictPanchayatList", districtPanchayatList);
					localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
				} else if (localGovtBodyForm.getLgd_LBlevelChk() == 'V') {
					model.addAttribute("localBodyDetails", lbDetails);
					localGovtBodyForm.setLgd_LBNomenclatureDist("District Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
					localGovtBodyForm.setLgd_LBNomenclatureVillage("Village Panchayat");
				}

				mav = new ModelAndView("Correct_govt_local_bodyforcoveragearea");
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				return mav;
				// }
			}
			// Integer maxlbcode = localGovtBodyService.getmaxlandregioncode();
			Integer maxlbcode = localGovtBodyService.getlbcoverdregioncode(localGovtBodyForm.getLocalBodyCode());

			/*
			 * Get value after comparison of logged in state code with ULB
			 * District Wise. Referred for conditions.
			 * 
			 * @author vinay yadav 23-12-2013
			 */
			boolean isULBDisttLevel = Boolean.FALSE;
			if (localGovtBodyForm.getLbType() == 'U')
				isULBDisttLevel = ApplicationConstant.checkStateLBOnlyDisttWise(stateCode);
			boolean returnSucess = localGovtBodyService.correctLocalBodyForcoverageArea(localGovtBodyForm, maxlbcode, request, httpsession, catagery, isULBDisttLevel);
			if (returnSucess) {
				coveragetoPes = localGovtBodyService.saveCoverageinPES(localGovtBodyForm.getLocalBodyCode());
			}
			if (returnSucess) {
				httpsession.removeAttribute("validationErrorOne");

				String aMessage = "The covered area of  Local Government  Body was mapped successfully. Local Body Code is " + localGovtBodyForm.getLocalBodyCode();
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage.trim());
				return mav;
			} else {

				String aMessage = "Fail to Correct Coverage Area of Local Body Code" + localGovtBodyForm.getLocalBodyCode() + " , kindly try again";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
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

	/**
	 * Modify govt local body name.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @return the model and view
	 */
	

	/**
	 * Gets the attachment beanfor map.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param request
	 *            the request
	 * @return the attachment beanfor map
	 */
	public AddAttachmentBean getAttachmentBeanforMap(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request) {

		@SuppressWarnings("unused")
		ModelAndView mav = null;
		try {
			List<MapAttachment> alreadyAttachList = null;
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(2);

			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0;
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;

			if (localGovtBodyForm.getOrderCode() != null) {
				alreadyAttachList = govtOrderService.getMapAttachmentListbyEntity(localGovtBodyForm.getLocalBodyCode(), 'G');

				if (request.getParameterValues("deletedFileID2") != null) {
					String[] deletedFileID1 = (String[]) request.getParameterValues("deletedFileID2");
					govtOrderService.deleteAttachedFiles(deletedFileID1, request, 'M');
				}

				noOFAlreadyAttachedFiles1 = alreadyAttachList.size();
				Iterator<MapAttachment> attachmentsIterator1 = alreadyAttachList.iterator();
				while (attachmentsIterator1.hasNext()) {
					MapAttachment att = (MapAttachment) attachmentsIterator1.next();
					long fileSize = att.getFileSize();
					totalSizeOFAlreadyAttachedFiles1 = totalSizeOFAlreadyAttachedFiles1 + fileSize;
				}
			}

			AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(localGovtBodyForm.getAttachFile2());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(localGovtBodyForm.getFileTitle2());
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File

			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID2"));// 10.File

			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName2"));// 11.File

			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList2"));// 12.Deleted

			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);
			return addAttachmentBeanTwo;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	/**
	 * Gets the attachment bean.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param request
	 *            the request
	 * @return the attachment bean
	 */
	public AddAttachmentBean getAttachmentBean(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request) {

		try {
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
			@SuppressWarnings("unused")
			String delSuccess = null;
			if (localGovtBodyForm.getOrderCode() != null) {
				alreadyAttachList = govtOrderService.getAttachmentsbyOrderCode(localGovtBodyForm.getOrderCode());

				if (request.getParameterValues("deletedFileID1") != null) {
					String[] deletedFileID1 = (String[]) request.getParameterValues("deletedFileID1");
					delSuccess = govtOrderService.deleteAttachedFiles(deletedFileID1, request, 'O');
				}

				if (alreadyAttachList != null && alreadyAttachList.size() > 0) {
					noOFAlreadyAttachedFiles1 = alreadyAttachList.size(); // Already
																			// attached
																			// file
																			// total
																			// size.
					Iterator<Attachment> attachmentsIterator1 = alreadyAttachList.iterator();
					while (attachmentsIterator1.hasNext()) {
						Attachment att = (Attachment) attachmentsIterator1.next();
						long fileSize = att.getFileSize();
						totalSizeOFAlreadyAttachedFiles1 = totalSizeOFAlreadyAttachedFiles1 + fileSize;
					}
				}
			}

			AddAttachmentBean addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(localGovtBodyForm.getAttachFile1());
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);
			addAttachmentBeanTwo.setFileTitle(localGovtBodyForm.getFileTitle1());
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);
			addAttachmentBeanTwo.setMimeTypeList(allowedMimeTypeList);// 9.File
			addAttachmentBeanTwo.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
			addAttachmentBeanTwo.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
			addAttachmentBeanTwo.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
			addAttachmentBeanTwo.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
																			// of
																			// Mandatory
																			// file.
			return addAttachmentBeanTwo;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			log.debug("Exception" + e);
			return null;
		}
	}

	List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();

	/**
	 * Creates the wardfor panchayat. Declare localBodyTypelist variable.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param model
	 *            the model
	 * @param session
	 *            the session
	 * @return the model and view
	 */
	@SuppressWarnings("deprecation")
	/* @RequestMapping(value = "/createWardforPRI", method = RequestMethod.GET) */
	public ModelAndView createWardforPanchayat(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
		/** The Intermediate panchayat list. */
		// List<LocalbodyListbyState> intermediatePanchayatList = new
		// ArrayList<LocalbodyListbyState>();
		// List<LocalbodyListbyState> villagePanchayatList = new
		// ArrayList<LocalbodyListbyState>();

		try {
			

			
			localGovtBodyForm.setLgd_lbCategory("P");
			localGovtBodyForm.setLbType('P');

			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}
			/* query changed by kirandeep on 01/09/2014 */
			/*
			 * List<LocalGovtBodyWard> listWardDetails=new
			 * ArrayList<LocalGovtBodyWard>();
			 * listWardDetails=localGovtBodyService
			 * .getlocalGovtBodyWardListforpaginationonCreate
			 * (localGovtBodyForm.getLocalBodyCode());
			 */

			getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'P');

			/*
			 * intermediatePanchayatList = localGovtBodyService
			 * .getExistingLBListbyStateandCategoryInter(stateCode, 'P');
			 * 
			 * villagePanchayatList = localGovtBodyService
			 * .getExistingLBListbyStateandCategoryVillage(stateCode, 'P');
			 */

			if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {

				model.addAttribute("stateCode", stateCode);
				request.setAttribute("localSetUpList", getLocalGovtSetupList);

				localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);

				/*
				 * districtPanchayatList = localGovtBodyService
				 * .getPanchayatListbyStateandCategory(stateCode, 'P');
				 */

				/*
				 * if (districtPanchayatList.size() > 0) {
				 * model.addAttribute("districtPanchayatList",
				 * districtPanchayatList); }
				 */
				/*
				 * if (intermediatePanchayatList.size() > 0) {
				 * model.addAttribute("intermediatePanchayatList",
				 * intermediatePanchayatList); } if (villagePanchayatList.size()
				 * > 0) { model.addAttribute("villagePanchayatList",
				 * villagePanchayatList); }
				 */

				mav = new ModelAndView("createPRIWard");
				model.addAttribute("lbType", "P");
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				model.addAttribute("stateCode", stateCode);
				model.addAttribute("districtCode", districtCode);
				/* changed by kirandeep on 01/09/2014 */
				/* model.addAttribute("listWardDetails",listWardDetails); */
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				model.addAttribute("showTable", false);

			} else {

				mav = new ModelAndView("success");
				mav.addObject("msgid", "Please first Set Up Local Government for this State");

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
	 * Creates the wardfor traditional. Declare
	 * districtPanchayatList,localBodyTypelist variable for creation of ward.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param model
	 *            the model
	 * @param session
	 *            the session
	 * @return the model and view
	 */
	// @RequestMapping(value = "/createWardforTraditional", method =
	// RequestMethod.GET)
	public ModelAndView createWardforTraditional(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
		/** The Intermediate panchayat list. */
		@SuppressWarnings("unused")
		List<LocalbodyListbyState> intermediatePanchayatList = new ArrayList<LocalbodyListbyState>();

		try {
			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			
			localGovtBodyForm.setLgd_lbCategory("T");
			getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'T');

			if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {

				model.addAttribute("stateCode", stateCode);
				request.setAttribute("localSetUpList", getLocalGovtSetupList);

				localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('T', stateCode);
				/*
				 * if (localBodyTypelist.size() > 0) {
				 * 
				 * localGovtBodyForm = setLBtype(localBodyTypelist,
				 * localGovtBodyForm); }
				 */
				/*
				 * districtPanchayatList = localGovtBodyService
				 * .getPanchayatListbyStateandCategory(stateCode, 'T');
				 * 
				 * intermediatePanchayatList = localGovtBodyService
				 * .getExistingLBListbyStateandCategoryInter(stateCode, 'T');
				 */

				if (districtPanchayatList.size() > 0) {
					model.addAttribute("districtPanchayatList", districtPanchayatList);
				}

				localGovtBodyForm.setLbType('T');
				mav = new ModelAndView("createPRIWard");
				model.addAttribute("stateCode", stateCode);
				model.addAttribute("lbType", "T");
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Please first Set Up Local Government for this State");
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
	 * Creates the wardfor urban. Declare localBodyTypelist variable for create
	 * Wards
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param model
	 *            the model
	 * @param session
	 *            the session
	 * @return the model and view
	 */
	//@RequestMapping(value = "/createWardforUrban", method = RequestMethod.GET)
	public ModelAndView createWardforUrban(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyForm.getLocalBodyTypelist();
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		List<LocalbodyforStateWise> localBodyTypelistUrban = new ArrayList<LocalbodyforStateWise>();
		try {
			
			//
			getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'U');

			if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {

				model.addAttribute("stateCode", stateCode);
				request.setAttribute("localSetUpList", getLocalGovtSetupList);

				localGovtBodyForm.setLgd_lbCategory("U");

				localBodyTypelistUrban = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);

				if (localBodyTypelist.size() > 0) {

					localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
				}
				localGovtBodyForm.setLbType('U');
				model.addAttribute("lbType", "U");
				mav = new ModelAndView("createWard");
				model.addAttribute("localBodyTypelist", localBodyTypelistUrban);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Please first Set Up Local Government for this State");
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
	 * Creates the urban ward. Declare localBodyTypelistUrban variable.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @return the model and view
	 */
	/* Modified by changed by kirandeep on 05/08/2014 */
	@RequestMapping(value = "/createWardforUrbanPost", method = RequestMethod.POST)
	public ModelAndView createUrbanWardPost(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		//AddAttachmentBean addAttachmentBean2 = null;
		//AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
		//List<AttachedFilesForm> metaInfoOfToBeAttachedMapList = null;
		

		try {
			
			List<LocalbodyforStateWise> localBodyTypelistUrban = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelistUrban);
			// /////////////code for drop down
			// validation/////////////////////////////////

			// /////////////end code for drop down
			// validation/////////////////////////////////

			// /////////////code for government order
			// checking///////////////////////////////////
			//Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			//hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10
																								// is
																								// operation
																								// code
																								// for
																								// create
																								// new
			//String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			//String mapConfig = hMap.get("mapUpload");// values==true,false

			// ////////code for government order
			// checking///////////////////////////////////
			mav = null;
			localGovtBodyForm.setLgd_lbCategory("U");
			/* changed by kirandeep on 01/09/2014 */
			/*
			 * validatorWard.validateWardforULB(localGovtBodyForm, result); if
			 * (result.hasErrors()) {
			 * 
			 * mav = new ModelAndView("createWard");
			 * model.addAttribute("localBodyTypelist", localBodyTypelistUrban);
			 * model.addAttribute("stateCode", stateCode);
			 * mav.addObject("localGovtBodyForm", localGovtBodyForm); // } }
			 * else {
			 */

			/* Modified changed by kirandeep on 05/08/2014 */

			@SuppressWarnings("unchecked")
			List<LocalGovtBodyWard> listWardDetails = (List<LocalGovtBodyWard>) session.getAttribute("wardlistforurban");
			ArrayList<String> wardnames = new ArrayList<String>();
			ArrayList<String> wardnumbers = new ArrayList<String>();

			String arrayupdate = request.getParameter("formNominations");
			String arrayinsert = request.getParameter("newWardList");
			String array_DeleteWardData = request.getParameter("deleteWardList");

			if (!arrayupdate.equalsIgnoreCase("")) {
				String[] wards = arrayupdate.split("@@");

				if (wards.length > 0) {
					for (String warddetail : wards) {

						String[] warddetailfull = warddetail.split("\\|");
						Long wardListindex = Long.parseLong(warddetailfull[0]);
						for (int i = 0; i < listWardDetails.size(); i++) {
							if (Long.parseLong(listWardDetails.get(i).getWardCode().toString()) == wardListindex) {
								listWardDetails.remove(i);
							}
						}

						String wardName = warddetailfull[1];
						String wardNumber = warddetailfull[2];
						wardnames.add(wardName);
						wardnumbers.add(wardNumber);
					}
				}
			}

			if (!arrayinsert.equalsIgnoreCase("")) {
				String[] wards = arrayinsert.split("@@");

				if (wards.length > 0) {

					for (String warddetail : wards) {

						String[] warddetailfull = warddetail.split("\\|");

						String wardName = warddetailfull[0];
						String wardNumber = warddetailfull[1];
						wardnames.add(wardName);
						wardnumbers.add(wardNumber);

					}
				}
			}
			/* changed by kirandeep on 01/09/2014 */
			if (listWardDetails != null) {
				for (LocalGovtBodyWard ward : listWardDetails) {
					wardnames.add(ward.getWardNameInEnglish());
					wardnumbers.add(ward.getWardNumber());
				}

			}

			boolean checkduplicatenames = true;
			boolean checkduplicatenumber = true;
			checkduplicatenames = checkDuplicate(wardnames);
			checkduplicatenumber = checkDuplicate(wardnumbers);

			if (checkduplicatenames == false || checkduplicatenumber == false) {
				String aMessage = null;
				aMessage = "Duplicate values found No ward details affected";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				session.removeAttribute("wardlistforurban");
				return mav;

			}

			boolean createWardreturn = false;

			if (!arrayupdate.equalsIgnoreCase("")) {
				String[] wards = arrayupdate.split("@@");

				if (wards.length > 0) {

					for (String warddetail : wards) {
						String wardNamelocal = "";
						String[] warddetailfull = warddetail.split("\\|");
						Integer wardListindex = Integer.parseInt(warddetailfull[0]);
						String wardName = warddetailfull[1];
						String wardNumber = warddetailfull[2];
						if (warddetailfull.length > 3)
							wardNamelocal = warddetailfull[3];

						List<LocalbodyWard> listWard = localGovtBodyService.getWardDetail(wardListindex);

						for (LocalbodyWard lbWard : listWard) {
							localGovtBodyForm.setLocalBodyVersion(lbWard.getWardVersion());
							localGovtBodyForm.setLblc(lbWard.getLblc());
							localGovtBodyForm.setMap_code(0);
							localGovtBodyForm.setEffectiveDate(lbWard.getEffectiveDate());
							localGovtBodyForm.setLastupdatedon(new Date());
							localGovtBodyForm.setLastupdatedby(userId);
							localGovtBodyForm.setCreatedby(lbWard.getCreatedby());
							localGovtBodyForm.setCreatedon(lbWard.getCreatedon());
							localGovtBodyForm.setIsactive(lbWard.isIsactive());
							localGovtBodyForm.setWard_code(lbWard.getWardCode());
							localGovtBodyForm.setLblc(lbWard.getLblc());
							localGovtBodyForm.setWard_version(lbWard.getWardVersion());
						}

						localGovtBodyForm.setWard_Name(wardName);
						localGovtBodyForm.setWard_number(wardNumber);
						localGovtBodyForm.setWard_NameLocal(wardNamelocal);

						createWardreturn = localGovtBodyService.modifyWardData(localGovtBodyForm, request);
						if (createWardreturn) {
							continue;
						} else {

						}

					}

				}

			}

			if (!arrayinsert.equalsIgnoreCase("")) {
				String[] wards = arrayinsert.split("@@");

				if (wards.length > 0) {

					for (String warddetail : wards) {
						String wardNamelocal = "";
						String[] warddetailfull = warddetail.split("\\|");
						// Integer wardListindex =
						// Integer.parseInt(warddetailfull[0]);
						String wardName = warddetailfull[0];
						String wardNumber = warddetailfull[1];
						if (warddetailfull.length > 2)
							wardNamelocal = warddetailfull[2];

						localGovtBodyForm.setWard_Name(wardName);
						localGovtBodyForm.setWard_number(wardNumber);
						localGovtBodyForm.setWard_NameLocal(wardNamelocal);
						createWardreturn = localGovtBodyService.createNewWard(localGovtBodyForm, stateCode, request, userId);

					}

				}

			}

			if (!array_DeleteWardData.equalsIgnoreCase("")) {
				String[] wards = array_DeleteWardData.split("@@");

				if (wards.length > 0) {
					for (String warddetail : wards) {
						//String wardNamelocal = "";
						String[] warddetailfull = warddetail.split("\\|");
						Integer wardListindex = Integer.parseInt(warddetailfull[0]);
						//String wardName = warddetailfull[1];
						//String wardNumber = warddetailfull[2];
						/*if (warddetailfull.length > 3)
							wardNamelocal = warddetailfull[3];*/

						List<LocalbodyWard> listWard = localGovtBodyService.getWardDetail(wardListindex);
						createWardreturn = localGovtBodyService.createwarddetailsdelete(listWard);
					}
				}
			}

			if (createWardreturn) {
				String aMessage = "Urban Type Ward successfully created.";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				session.removeAttribute("wardlistforurban");
			} else {
				String aMessage = "No ward details Modified";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				session.removeAttribute("wardlistforurban");
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

	/**
	 * Creates the panchayat ward.
	 * 
	 * Declare districtPanchayatList variable for creation of ward.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @return the model and view
	 */

	/* changed by kirandeep on 01/09/2014 */

	/**
	 * Redirect local body.
	 * 
	 * @param session
	 *            the session
	 * @param request
	 * @return the model and view
	 */

	/* changed by kirandeep on 01/09/2014 */
	@RequestMapping(value = "/saveLocalGovtBody", method = RequestMethod.POST)
	public ModelAndView redirectLocalBody(HttpSession session, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			if (localGovtBodyForm.getLbType() == 'U') {
				mav = new ModelAndView("forward:addUrbanLocalGovtBody.htm");
			} else {
				mav = new ModelAndView("forward:addLocalGovtBody.htm");
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/invalidatedLocalGovtBody", method = RequestMethod.POST)
	public ModelAndView invalidateLocalBody(HttpSession session, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			LocalGovtBodyForm localGovtBodyForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			if (localGovtBodyForm.getOperation().equals("IPRI")) {
				mav = new ModelAndView("forward:invalidatePRILBFinal.htm");
			} else if (localGovtBodyForm.getOperation().equals("ITRI")) {
				mav = new ModelAndView("forward:invalidateTRILBFinal.htm");
			} else if (localGovtBodyForm.getOperation().equals("IURB")) {
				mav = new ModelAndView("forward:invalidateURBFinal.htm");
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
	 * Modify ward detail.
	 * 
	 * @param modifyWardCmd
	 *            the modify ward cmd
	 * @param session
	 *            the session
	 * @param model
	 *            the model
	 * @param wardCode
	 *            the ward code
	 * @param request
	 * @return the model and view
	 */
	@RequestMapping(value = "/modifyWard")
	public ModelAndView modifyWardDetail(@ModelAttribute("modifyWardCmd") LocalGovtBodyForm modifyWardCmd, BindingResult result, HttpSession session, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		int wardCode = modifyWardCmd.getWardId();
		try {
		

			mav = new ModelAndView();
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			if (localBodyTypelist.size() > 0) {
				modifyWardCmd = setLBtype(localBodyTypelist, modifyWardCmd);
			}
			List<LocalbodyWard> listWard = localGovtBodyService.getWardDetail(wardCode);

			List<GetLandRegionNameforWard> landregionDistrict = localGovtBodyService.getDistrictNameWard(wardCode);
			List<GetLandRegionNameforWard> landregionSubDistrict = localGovtBodyService.getSubDistrictNameWard(wardCode);
			List<GetLandRegionNameforWard> landregionVillage = localGovtBodyService.getVillageNameWard(wardCode);
			if (landregionDistrict != null) {
				for (GetLandRegionNameforWard landregionWardD : landregionDistrict) {
					modifyWardCmd.setLgd_LBDistCAreaSourceListUmapped(landregionWardD.getLandRegionName());
				}
			}
			if (landregionSubDistrict != null) {
				for (GetLandRegionNameforWard landregionWardSD : landregionSubDistrict) {
					modifyWardCmd.setLgd_LBSubDistrictSourceLatDCAUmapped(landregionWardSD.getLandRegionName());
				}
			}
			if (landregionVillage != null) {
				for (GetLandRegionNameforWard landregionWardV : landregionVillage) {
					modifyWardCmd.setLgd_LBVillageDestLatDCAUmapped(landregionWardV.getLandRegionName());
				}
			}

			modifyWardCmd.setWard_code(wardCode);
			for (LocalbodyWard lbWard : listWard) {
				modifyWardCmd.setLblc(lbWard.getLblc());
				modifyWardCmd.setWard_Name(lbWard.getWardNameEnglish());
				modifyWardCmd.setWard_NameLocal(lbWard.getWardNameLocal());
				modifyWardCmd.setWard_number(lbWard.getWardNumber());
			}
			mav = new ModelAndView("modifyWard");

			model.addAttribute("landRegionDistrictList", landregionDistrict);
			model.addAttribute("landRegionSubDistrictList", landregionSubDistrict);
			model.addAttribute("landRegionVillageList", landregionVillage);

			model.addAttribute("localBodyTypelist", localBodyTypelist);
			mav.addObject("modifyWardCmd", modifyWardCmd);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/**
	 * Modify ward.
	 * 
	 * @param modifyWardCmd
	 *            the modify ward cmd
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @param session
	 *            the session
	 * @return the model and view
	 */
	@RequestMapping(value = "/modifyWardAction", method = RequestMethod.POST)
	public ModelAndView modifyWardAction(@ModelAttribute("modifyWardCmd") LocalGovtBodyForm modifyWardCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		boolean check = false;
		try {
			@SuppressWarnings("unused")
			String viewMinistrydetail = null;
			mav = new ModelAndView("modifyWard");
			mav.addObject("modifyWardCmd", modifyWardCmd);
			int wardCode = 0;
			wardCode = modifyWardCmd.getWard_code();
			List<LocalbodyWard> listWard = localGovtBodyService.getWardDetail(wardCode);
			validatorWard.validateModifyWard(modifyWardCmd, result);
			if (result.hasErrors()) {

				for (LocalbodyWard lbWard : listWard) {
					modifyWardCmd.setLocalBodyVersion(lbWard.getWardVersion());
					modifyWardCmd.setLblc(lbWard.getLblc());
					modifyWardCmd.setMap_code(0);
					modifyWardCmd.setEffectiveDate(lbWard.getEffectiveDate());
					modifyWardCmd.setLastupdatedon(new Date());
					modifyWardCmd.setLastupdatedby(userId);
					modifyWardCmd.setCreatedby(lbWard.getCreatedby());
					modifyWardCmd.setCreatedon(lbWard.getCreatedon());
					modifyWardCmd.setIsactive(lbWard.isIsactive());
				}

				List<GetLandRegionNameforWard> landregionDistrict = localGovtBodyService.getDistrictNameWard(wardCode);
				List<GetLandRegionNameforWard> landregionSubDistrict = localGovtBodyService.getSubDistrictNameWard(wardCode);
				List<GetLandRegionNameforWard> landregionVillage = localGovtBodyService.getVillageNameWard(wardCode);
				if (landregionDistrict != null) {
					for (GetLandRegionNameforWard landregionWardD : landregionDistrict) {
						modifyWardCmd.setLgd_LBDistCAreaSourceListUmapped(landregionWardD.getLandRegionName());
					}
				}
				if (landregionSubDistrict != null) {
					for (GetLandRegionNameforWard landregionWardSD : landregionSubDistrict) {
						modifyWardCmd.setLgd_LBSubDistrictSourceLatDCAUmapped(landregionWardSD.getLandRegionName());
					}
				}
				if (landregionVillage != null) {
					for (GetLandRegionNameforWard landregionWardV : landregionVillage) {
						modifyWardCmd.setLgd_LBVillageDestLatDCAUmapped(landregionWardV.getLandRegionName());
					}
				}

				mav = new ModelAndView("modifyWard");
				model.addAttribute("landRegionDistrictList", landregionDistrict);
				model.addAttribute("landRegionSubDistrictList", landregionSubDistrict);
				model.addAttribute("landRegionVillageList", landregionVillage);
				// model.addAttribute("localBodyTypelist", localBodyTypelist);
				// model.addAttribute("stateCode", stateCode);
				// model.addAttribute("districtPanchayatList",districtPanchayatList);
				mav.addObject("localGovtBodyForm", modifyWardCmd);
			} else {
				for (LocalbodyWard lbWard : listWard) {
					modifyWardCmd.setLocalBodyVersion(lbWard.getWardVersion());
					modifyWardCmd.setLblc(lbWard.getLblc());
					modifyWardCmd.setMap_code(0);
					modifyWardCmd.setEffectiveDate(lbWard.getEffectiveDate());
					modifyWardCmd.setLastupdatedon(new Date());
					modifyWardCmd.setLastupdatedby(userId);
					modifyWardCmd.setCreatedby(lbWard.getCreatedby());
					modifyWardCmd.setCreatedon(lbWard.getCreatedon());
					modifyWardCmd.setIsactive(lbWard.isIsactive());
				}

				check = localGovtBodyService.modifyWardData(modifyWardCmd, request);
				if (check) {
					String aMessage = "Ward Modified successfully!!";
					mav = new ModelAndView("success");
					mav.addObject("msgid", aMessage);
					return mav;
				} else {
					String aMessage = "Ward Not Modified successfully!!";
					mav = new ModelAndView("success");
					mav.addObject("msgid", aMessage);
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/modifyUrbanWard")
	public ModelAndView modifyUrbanWardDetail(@ModelAttribute("wardViewDetails") LocalGovtBodyForm modifyUrbanWardCmd, HttpSession session, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		int wardCode = modifyUrbanWardCmd.getUrbanwardId();
		try {
			

			mav = new ModelAndView();
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
			if (localBodyTypelist.size() > 0) {
				modifyUrbanWardCmd = setLBtype(localBodyTypelist, modifyUrbanWardCmd);
			}
			List<LocalbodyWard> listWard = localGovtBodyService.getWardDetail(wardCode);
			List<GetLandRegionNameforWard> landregionSubDistrict = localGovtBodyService.getSubDistrictNameWard(wardCode);
			if (landregionSubDistrict != null) {
				for (GetLandRegionNameforWard landregionWardSD : landregionSubDistrict) {
					modifyUrbanWardCmd.setLgd_LBSubDistrictSourceLatDCAUmapped(landregionWardSD.getLandRegionName());
				}
			}

			modifyUrbanWardCmd.setWard_code(wardCode);
			for (LocalbodyWard lbWard : listWard) {
				modifyUrbanWardCmd.setLblc(lbWard.getLblc());
				modifyUrbanWardCmd.setWard_Name(lbWard.getWardNameEnglish());
				modifyUrbanWardCmd.setWard_NameLocal(lbWard.getWardNameLocal());
				modifyUrbanWardCmd.setWard_number(lbWard.getWardNumber());
			}
			mav = new ModelAndView("modifyUrbanWard");
			model.addAttribute("landRegionSubDistrictList", landregionSubDistrict);
			model.addAttribute("localBodyTypelist", localBodyTypelist);
			mav.addObject("modifyUrbanWardCmd", modifyUrbanWardCmd);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/modifyUrbanWardAction", method = RequestMethod.POST)
	public ModelAndView modifyUrbanWardAction(@ModelAttribute("modifyUrbanWardCmd") LocalGovtBodyForm modifyWardCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		boolean check = false;
		
		try {
			@SuppressWarnings("unused")
			String viewMinistrydetail = null;
			mav = new ModelAndView("modifyUrbanWard");
			mav.addObject("modifyUrbanWardCmd", modifyWardCmd);
			int wardCode = 0;
			wardCode = modifyWardCmd.getWard_code();
			List<LocalbodyWard> listWard = localGovtBodyService.getWardDetail(wardCode);
			validatorWard.validateModifyWard(modifyWardCmd, result);
			if (result.hasErrors()) {
				for (LocalbodyWard lbWard : listWard) {
					/*
					 * modifyWardCmd.setWard_Name(lbWard.getWardNameEnglish());
					 * modifyWardCmd
					 * .setWard_NameLocal(lbWard.getWardNameLocal());
					 * modifyWardCmd.setWard_number(lbWard.getWardNumber());
					 */

					modifyWardCmd.setLocalBodyVersion(lbWard.getWardVersion());
					modifyWardCmd.setLblc(lbWard.getLblc());
					modifyWardCmd.setMap_code(0);
					modifyWardCmd.setEffectiveDate(lbWard.getEffectiveDate());
					// modifyWardCmd.setLastupdatedon(lbWard.getLastupdated());
					modifyWardCmd.setLastupdatedon(new Date());
					// modifyWardCmd.setLastupdatedby(lbWard.getLastupdatedby());
					modifyWardCmd.setLastupdatedby(userId);
					modifyWardCmd.setCreatedby(lbWard.getCreatedby());
					modifyWardCmd.setCreatedon(lbWard.getCreatedon());
					modifyWardCmd.setIsactive(lbWard.isIsactive());
				}

				List<GetLandRegionNameforWard> landregionSubDistrict = localGovtBodyService.getSubDistrictNameWard(wardCode);
				if (landregionSubDistrict != null) {
					for (GetLandRegionNameforWard landregionWardSD : landregionSubDistrict) {
						modifyWardCmd.setLgd_LBSubDistrictSourceLatDCAUmapped(landregionWardSD.getLandRegionName());
					}
				}

				mav = new ModelAndView("modifyUrbanWard");
				model.addAttribute("landRegionSubDistrictList", landregionSubDistrict);
				// model.addAttribute("localBodyTypelist", localBodyTypelist);
				// model.addAttribute("stateCode", stateCode);
				// model.addAttribute("districtPanchayatList",districtPanchayatList);
				mav.addObject("localGovtBodyForm", modifyWardCmd);
			} else {
				for (LocalbodyWard lbWard : listWard) {
					/*
					 * modifyWardCmd.setWard_Name(lbWard.getWardNameEnglish());
					 * modifyWardCmd
					 * .setWard_NameLocal(lbWard.getWardNameLocal());
					 * modifyWardCmd.setWard_number(lbWard.getWardNumber());
					 */

					modifyWardCmd.setLocalBodyVersion(lbWard.getWardVersion());
					modifyWardCmd.setLblc(lbWard.getLblc());
					modifyWardCmd.setMap_code(0);
					modifyWardCmd.setEffectiveDate(lbWard.getEffectiveDate());
					// modifyWardCmd.setLastupdatedon(lbWard.getLastupdated());
					modifyWardCmd.setLastupdatedon(new Date());
					// modifyWardCmd.setLastupdatedby(lbWard.getLastupdatedby());
					modifyWardCmd.setLastupdatedby(userId);
					modifyWardCmd.setCreatedby(lbWard.getCreatedby());
					modifyWardCmd.setCreatedon(lbWard.getCreatedon());
					modifyWardCmd.setIsactive(lbWard.isIsactive());
				}

				// LocalGovtBodyForm
				// wardDataObject=(LocalGovtBodyForm)session.getAttribute("wardDataObject");

				check = localGovtBodyService.modifyWardData(modifyWardCmd, request);

				if (check) {
					String aMessage = "Urban Ward Modified successfully!!";
					mav = new ModelAndView("success");
					mav.addObject("msgid", aMessage);
					return mav;
				} else {
					String aMessage = "Urban Ward Not Modified successfully!!";
					mav = new ModelAndView("success");
					mav.addObject("msgid", aMessage);
				}
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
	 * Sets the attachment details.
	 * 
	 * @param localgovtform
	 *            the new attachment details
	 * @param request
	 */
	public void setAttachmentDetails(LocalGovtBodyForm localgovtform, HttpServletRequest request) {

		try {
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);

			localgovtform.setAllowedFileType(attachmentList.getFileType());
			localgovtform.setAllowedIndividualFileSize(attachmentList.getIndividualFileSize());
			localgovtform.setAllowedNoOfAttachment(attachmentList.getFileLimit());
			localgovtform.setAllowedTotalFileSize(attachmentList.getTotalFileSize());
			localgovtform.setUploadLocation(attachmentList.getFileLocation());
			localgovtform.setRequiredTitle(attachmentList.getRequireTitle());
			localgovtform.setUniqueTitle(attachmentList.getUniqueTitle());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			@SuppressWarnings("unused")
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		}

	}

	// getting values for map from MapLocalBody
	/**
	 * Gets the map attachment listby local body.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param localBodyCode
	 *            the local body code
	 * @param lbDetails
	 *            the lb details
	 * @param request
	 * @return the map attachment listby local body
	 */
	private List<MapAttachment> getMapAttachmentListbyLocalBody(LocalGovtBodyForm localGovtBodyForm, Integer localBodyCode, List<LocalBodyDetails> lbDetails, HttpServletRequest request) {

		List<MapAttachment> mapAttachmentList = null;
		try {
			char entityType = 'G';
			mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(localBodyCode, entityType);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			@SuppressWarnings("unused")
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
		return mapAttachmentList;
	}

	/**
	 * Gets the attachment listby local body.
	 * 
	 * @param localGovtBodyForm
	 *            the local govt body form
	 * @param localBodyCode
	 *            the local body code
	 * @param lbDetails
	 *            the lb details
	 * @param request
	 * @return the attachment listby local body
	 */
	private List<Attachment> getAttachmentListbyLocalBody(LocalGovtBodyForm localGovtBodyForm, Integer localBodyCode, List<LocalBodyDetails> lbDetails, HttpServletRequest request) {
		List<Attachment> attachmentList = null;
		try {
			List<GetGovernmentOrderDetail> govtList = govtOrderService.getGovtDetailsbyEntityCodeandVersion(localBodyCode, lbDetails.get(0).getLocalBodyVersion(), 'G');

			@SuppressWarnings("rawtypes")
			Iterator itr = govtList.iterator();
			while (itr.hasNext()) {
				GetGovernmentOrderDetail element = (GetGovernmentOrderDetail) itr.next();

				attachmentList = govtOrderService.getAttachmentsbyOrderCode(element.getOrderCode());

				localGovtBodyForm.setOrderCode(element.getOrderCode());
				localGovtBodyForm.setLgd_LBorderDate(element.getOrderDate());
				localGovtBodyForm.setLgd_LBorderNo(element.getOrderNo().trim());
				localGovtBodyForm.setLgd_LBorderDate(element.getOrderDate());
				localGovtBodyForm.setLgd_LBeffectiveDate(element.getEffectiveDate());
				localGovtBodyForm.setLgd_LBgazPubDate(element.getGazPubDate());
				setAttachmentDetails(localGovtBodyForm, request);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			@SuppressWarnings("unused")
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
		return attachmentList;
	}

	

	// //////////////pagination////////////////////
	@RequestMapping(value = "/viewManagegeWardPagination", method = RequestMethod.POST)
	public ModelAndView getlocalbodyWardPagination(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, HttpServletRequest request, HttpSession httpSession, Model model) {
		ModelAndView mav = null;
		mav = new ModelAndView("view_ward");
		boolean movenext = true;
		if (!"".equalsIgnoreCase(localGovtBodyForm.getLgd_LBTypeName())) {
			httpSession.setAttribute("test", localGovtBodyForm.getLgd_LBTypeName());
		} else {
			localGovtBodyForm.setLgd_LBTypeName((String) httpSession.getAttribute("test"));
		}
		String localbdytype1 = null;
		@SuppressWarnings("unused")
		int localbdytype = 0;
		int offset = Integer.parseInt(httpSession.getAttribute("offset").toString());
		int limit = Integer.parseInt(httpSession.getAttribute("limit").toString());
		int counter = Integer.parseInt(httpSession.getAttribute("counter").toString());
		int direction = localGovtBodyForm.getDirection();
		
		localGovtBodyForm.setParentCategory('v');
		@SuppressWarnings("unused")
		List<Localbody> localbodyList = null;
		localbodyList = new ArrayList<Localbody>();
		localbdytype1 = localGovtBodyForm.getLgd_LBTypeName().substring(0, 1);
		localbdytype = Integer.parseInt(localbdytype1);
		String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
		if (localBodyTypeCodeDup.equals("0"))
			localBodyTypeCodeDup = request.getParameter("lbTypeDetail");
		String strArray[] = localBodyTypeCodeDup.split(":");
		String typeCode = strArray[0];
		@SuppressWarnings("unused")
		String type = strArray[1];
		String categoryDropDown = strArray[2];
		@SuppressWarnings("unused")
		Integer lbtype = Integer.parseInt(typeCode);
		// ");
		// ;
		// ;
		//
		Integer lbCode = null;
		try {
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, localGovtBodyForm.getLgd_lbCategory().charAt(0), 'D');
			model.addAttribute("districtPanchayatList", districtPanchayatList);

			if (httpSession.getAttribute("lbCode") != null && !("").equals(httpSession.getAttribute("lbCode").toString())) {
				lbCode = Integer.parseInt(httpSession.getAttribute("lbCode").toString());
				List<LocalGovtBodyWard> listWardDetails = null;

				if ((offset != (counter / limit + 1)) && (direction == 1) && ((offset + direction) > 0)) {
					localGovtBodyForm.setOffset(offset + direction);
					httpSession.setAttribute("offset", localGovtBodyForm.getOffset());

				} else if ((offset != (counter / limit + 1)) && (direction == -1) && ((offset + direction) >= 0)) {
					localGovtBodyForm.setOffset(offset + direction);
					httpSession.setAttribute("offset", localGovtBodyForm.getOffset());
				}

				offset = Integer.parseInt(httpSession.getAttribute("offset").toString());

				listWardDetails = localGovtBodyService.getlocalGovtBodyWardListforpagination(lbCode.intValue(), offset * limit, limit);
				if (listWardDetails != null && !listWardDetails.isEmpty()) {
					counter = localGovtBodyService.countwardDetails(lbCode.intValue());
					localGovtBodyForm.setLimit(limit);
					localGovtBodyForm.setOffset(offset);
					httpSession.setAttribute("limit", localGovtBodyForm.getLimit());
					httpSession.setAttribute("offset", localGovtBodyForm.getOffset());
					// session.setAttribute("lbtype", lbtype);
					httpSession.setAttribute("lbtype", categoryDropDown);
					httpSession.setAttribute("lbCode", lbCode);
					httpSession.setAttribute("counter", counter);

					model.addAttribute("wardsize", listWardDetails.size());
					model.addAttribute("wardList", listWardDetails);
					model.addAttribute("limits", Integer.parseInt(httpSession.getAttribute("limit").toString()));
					model.addAttribute("offsets", Integer.parseInt(httpSession.getAttribute("offset").toString()));
					model.addAttribute("counter", counter);
					Integer movePointer = counter / limit;
					movePointer = (counter % limit == 0) ? movePointer : movePointer + 1;
					if (offset == (movePointer - 1)) {
						movenext = false;
					} else {
						movenext = true;
					}

					model.addAttribute("movenext", movenext);
					model.addAttribute("wardCount", "Page " + (offset + 1) + " of " + (movePointer));
					model.addAttribute("wardList", listWardDetails);
					mav = new ModelAndView("view_ward");
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					model.addAttribute("lbtype", categoryDropDown.charAt(0));
					model.addAttribute("viewPage", "abc");

				}

			}

			

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return mav;

	}

	// ///////////////pagination//////////////////////

	@RequestMapping(value = "/viewUrbanWard", method = RequestMethod.POST)
	public ModelAndView viewUrbanWard(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {

		/*
		 * String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
		 * String strArray[] = localBodyTypeCodeDup.split(":"); String typeCode
		 * = strArray[0]; String type = strArray[1];
		 */
		ModelAndView mav = null;
		try {
			/* Modified changed by kirandeep on 05/08/2014 */
			localGovtBodyForm.setLgd_lbCategory("U");
			String localbdytype1 = localGovtBodyForm.getLgd_LBTypeName().substring(0, 1);
			int localbdytype = Integer.parseInt(localbdytype1);
			String checkmanage = request.getParameter("manage");
			// Integer.parseInt(request.getParameter("localbdytyp").toString());
			
			int offset = 0;
			//int limit = 25;

			// /////////////code for drop down
			// validation/////////////////////////////////

			if (localbdytype == 1) {
				comboFillingService.getComboValuesforApp('L', "S#1", stateCode, Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()));
				// comboFillingService.getComboValuesforApp('W',
				// null,Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()),null);
			}

			if (localbdytype == 2) {
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtInterCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList()));
			}
			if (localbdytype == 3) {
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()));
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBVillageSourceAtVillageCA()));
			}
			// /////////////end code for drop down
			// validation/////////////////////////////////
			List<LocalGovtBodyWard> listWardDetails = new ArrayList<LocalGovtBodyWard>();
			List<LocalbodyWard> listWardDetailsdetails = null;
			List<localbodywardtemp> listWardDetailstemp = null;

			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);
			localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
			model.addAttribute("localBodyTypelist", localBodyTypelist);

			if (localGovtBodyForm.getLb_lgdPanchayatName() != null) {
				// ALERT State Code Hard Code for testing
				listWardDetails = new ArrayList<LocalGovtBodyWard>();
				if (checkmanage != null && "manage".equalsIgnoreCase(checkmanage)) {
					listWardDetailstemp = localGovtBodyService.getlocalGovtBodyWardListforpaginationtemp(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()), offset, 100);
				}
				listWardDetailsdetails = localGovtBodyService.getlocalGovtBodyWardListforpaginationtempdelete(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()), offset, 100);
				// listWardDetails=localGovtBodyService.getlocalGovtBodyWardListforpagination(lbCode.intValue(),
				// offset, 100);

				listWardDetails = localGovtBodyService.getlocalGovtBodyWardList(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()));
			}

			model.addAttribute("wardList", listWardDetails);
			model.addAttribute("listWardDetailstemp", listWardDetailstemp);
			model.addAttribute("listWardDetailsdetails", listWardDetailsdetails);

			if (session.getAttribute("wardlist") != null) {
				session.setAttribute("wardlist", null);
			}
			if (session.getAttribute("wardlistforurban") == null) {
				session.setAttribute("wardlistforurban", listWardDetails);
			}
			localGovtBodyForm.setLocalGovtBodyWardList(listWardDetails);
			if (listWardDetails.size() > 0) {
				if (checkmanage != null && "manage".equalsIgnoreCase(checkmanage)) {
					mav = new ModelAndView("manageWardUrban");
				} else
					mav = new ModelAndView("createWard");
				model.addAttribute("viewPage", "abc");
				model.addAttribute("showTable", true);
			} else {
				if (checkmanage != null && "manage".equalsIgnoreCase(checkmanage)) {
					mav = new ModelAndView("manageWardUrban");
				} else
					mav = new ModelAndView("createWard");
				model.addAttribute("showTable", true);
				String errorMsg = "Ward List is not available";
				model.addAttribute("message", errorMsg);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/viewWardDetails")
	public ModelAndView viewWardDetail(@ModelAttribute("wardViewDetails") LocalGovtBodyForm wardView, BindingResult result, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		int wardCode = wardView.getWardId();
		try {
			List<LocalbodyWard> listWardDetails = localGovtBodyService.getWardByWardCode(wardCode);
			mav = new ModelAndView("view_warddetail");
			wardView.setListLBWard(listWardDetails);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/viewUrbanWardDetails")
	public ModelAndView viewUrbanWardDetail(@ModelAttribute("wardViewDetails") LocalGovtBodyForm wardView, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		int wardCode = wardView.getUrbanwardId();
		try {
			List<LocalbodyWard> listWardDetails = localGovtBodyService.getWardByWardCode(wardCode);
			mav = new ModelAndView("view_warddetail");
			wardView.setListLBWard(listWardDetails);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/invalidateWard")
	public ModelAndView abolishWard(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, HttpSession session, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		int wardCode = localGovtBodyForm.getWardId();
		

		try {
			boolean isCommitted = false;
			
			// isCommitted = localGovtBodyService.invalidateWard(wardCode,
			// userId);
			isCommitted = localGovtBodyService.deleteWard(wardCode, userId);
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);
			if (isCommitted == true) {
				model.addAttribute("msgid", "The selected Ward has been deleted. !");
				mav = new ModelAndView("success");
			} else {
				List<LocalGovtBodyWard> listWardDetails = null;
				listWardDetails = new ArrayList<LocalGovtBodyWard>();
				listWardDetails = localGovtBodyService.getlocalGovtBodyWardList(1);
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				model.addAttribute("wardList", listWardDetails);
				model.addAttribute("viewPage", "abc");
				mav = new ModelAndView("view_ward");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/invalidateUrbanWard")
	public ModelAndView abolishUrbanWard(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, HttpSession session, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		int wardCode = localGovtBodyForm.getUrbanwardId();
		

		try {
			boolean isCommitted = false;
			
			// isCommitted = localGovtBodyService.invalidateWard(wardCode,
			// userId);
			isCommitted = localGovtBodyService.deleteWard(wardCode, userId);
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);
			if (isCommitted == true) {
				model.addAttribute("msgid", "The selected Ward has been deleted. !");
				mav = new ModelAndView("success");
			} else {
				List<LocalGovtBodyWard> listWardDetails = null;
				listWardDetails = new ArrayList<LocalGovtBodyWard>();
				listWardDetails = localGovtBodyService.getlocalGovtBodyWardList(1);
				model.addAttribute("wardList", listWardDetails);
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				model.addAttribute("viewPage", "abc");
				mav = new ModelAndView("view_urbanward");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// This functions of View Ward has been shifted from ViewController
	// End-------ARNAB
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/invalidateprilocalbody")
	public ModelAndView preInvalidatePRI(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, HttpSession session, Model model, HttpServletRequest request) {

		@SuppressWarnings("unused")
		List<GetLandRegionWise> lbdist = null;
		@SuppressWarnings("unused")
		GetLandRegionWise ele = null;
		@SuppressWarnings("unused")
		LocalbodyListbyState lbs = null;
		List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
		ModelAndView mav = null;
		try {
			char lbType = 'P';
			int operationCode = 57;
			mav = null;
			
			request.setAttribute("stateCode", stateCode);
			
			@SuppressWarnings("unused")
			int code = 0;
			@SuppressWarnings("unused")
			Integer lbco = null;

			if (stateCode != 0) {

				if (session.getAttribute("formbean") != null) {
					session.removeAttribute("formbean");
					session.removeValue("formbean");
				}
				List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
				if (districtCode != 0) {
					districtPanchayatList = localGovtBodyService.getLandRegionByDistricCode(1, districtCode, "P");
				} else {
					if (stateCode != 34) {
						districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
					} else {
						districtPanchayatList = localGovtBodyService.getExistingLBListTwoTier(stateCode);
					}
				}
				if (localBodyTypelist.size() > 0) {

					localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
				}
				if (districtPanchayatList.size() > 0) {
					model.addAttribute("districtPanchayatList", districtPanchayatList);
				}
				localGovtBodyForm.setLbType('P');
				localGovtBodyForm.setOperation("IPRI");
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
				String govtOrderConfig = hMap.get("govtOrder");
				String mapConfig = hMap.get("mapUpload");
				String message = hMap.get("message");
				if (govtOrderConfig != null && mapConfig != null) {

					mav = new ModelAndView("invalidate_localgovtbody");
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					// model.addAttribute("localBodyTypelist",
					// localBodyTypelist);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					mav.addObject("flag1", 0);
					if (localBodyTypelist.size() == 2) {
						mav.addObject("Tier", 2);
						mav.addObject("Tiertype", 2);
					} else if (localBodyTypelist.size() > 2) {
						mav.addObject("Tier", 3);
						mav.addObject("Tiertype", 3);
					}else{
						mav.addObject("Tier", 1);
						mav.addObject("Tiertype", 1);
						model.addAttribute("singleTierGpList", localBodyService.getLocalBodiesForManage("P", 3, stateCode, null, districtCode));
					}
					if (districtCode != 0) {
						localBodyTypelist.remove(0);
					}
					model.addAttribute("localBodyTypelist", localBodyTypelist);

				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
				}

			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			log.debug("Exception" + e);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/invalidateLocalBodyforPRI", method = RequestMethod.POST)
	public ModelAndView InvalidatePRIpost(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		
		char lbType = 'P';
		int operationCode = 57;
		int flagCode = 36;
		ModelAndView mv = null;
		String lblevel = null;
		int type = 0;
		String lbcode = null;
		List<Localbody> lblists = null;

		try {

			Map<String, String> hMap = new HashMap<String, String>();

			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");
			@SuppressWarnings("unused")
			String mapConfig = hMap.get("mapUpload");
			String listformat = localGovtBodyForm.getListformat();
			localGovtBodyForm.setOperationCode(operationCode);
			localGovtBodyForm.setFlagCode(flagCode);
			if (!localGovtBodyForm.isLboption()) {
				listformat = listformat.substring(1, listformat.length() - 1);
				localGovtBodyForm.setListformat(listformat);
			}
			lblevel = request.getParameter("flag1");
			type = Integer.parseInt(lblevel);

			if (type == 1) {
				lbcode = localGovtBodyForm.getDistrictpanlbid();
			} else if (type == 2) {
				lbcode = localGovtBodyForm.getIntermediatepanlbid();
			} else if (type == 3) {
				lbcode = localGovtBodyForm.getGrampanlbid();
			}
			if (lbcode == null) {
				lbcode = localGovtBodyForm.getInvalidatedlbcode();
			}
			lblists = localGovtBodyService.getLocalbodyname(lbcode);
			if (lblists.size() > 0) {

				localGovtBodyForm.setLocalBodyNameEnglish(lblists.get(0).getLocalBodyNameEnglish());
				localGovtBodyForm.setLocalBodyNameLocal(lblists.get(0).getLocalBodyNameLocal());
				localGovtBodyForm.setLocalBodyVersion(lblists.get(0).getLocalBodyVersion());
			}

			httpSession.setAttribute("lblevel", lblevel);
			localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);
			localGovtBodyForm.setOperation("IPRI");
			httpSession.setAttribute("formbean", localGovtBodyForm);

			mv = new ModelAndView("redirect:govtOrderCommon.htm");
			mv.addObject("govtOrderConfig", govtOrderConfig);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	@RequestMapping(value = "/invalidatePRILBFinal", method = RequestMethod.POST)
	public ModelAndView InvalidatePRILB(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm lbForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "fileName", required = false) String fileName) throws Exception {

		ModelAndView mv = null;
		@SuppressWarnings("unused")
		boolean success = false;
		List<Localbody> lblists = null;
		Localbody lbdelete = null;
		String lbcode = null;
		@SuppressWarnings("unused")
		String[] lbid = null;
		@SuppressWarnings("unused")
		int lb = 0;
		String lblevel = null;
		int type = 0;
		try {

			@SuppressWarnings("unused")
			Map<String, String> hMap = new HashMap<String, String>();
			LocalGovtBodyForm lgbForm = (LocalGovtBodyForm) httpSession.getAttribute("formbean");
			lgbForm.setOrderNo(lbForm.getOrderNo());
			lgbForm.setEffectiveDate(lbForm.getEffectiveDate());
			lgbForm.setGazPubDate(lbForm.getGazPubDate());
			lgbForm.setOrderDate(lbForm.getOrderDate());
			lgbForm.getAliasNameEnglish();
			lbForm.getLgd_LBDistrictAtInter();
			lgbForm.getAliasNameEnglish();

			lblevel = (String) httpSession.getAttribute("lblevel");
			type = Integer.parseInt(lblevel);
			if (type == 1) {
				lbcode = lgbForm.getDistrictpanlbid();
			} else if (type == 2) {
				lbcode = lgbForm.getIntermediatepanlbid();
			} else if (type == 3) {
				lbcode = lgbForm.getGrampanlbid();
			}
			if (lbcode == null) {
				if (lgbForm.getInvalidatedlbcode() != null) {
					lbcode = lgbForm.getInvalidatedlbcode();
				}
			}
			
			LocalBodyForm localBodyForm = localBodyService.getLocalBodyFormObject(Integer.parseInt(lbcode));
			if(localBodyForm!=null) {
				lgbForm.setLocalBodyNameEnglish(localBodyForm.getLocalBodyNameEnglish());
				lgbForm.setLocalBodyNameLocal(localBodyForm.getLocalBodyNameLocal());
				lgbForm.setLocalBodyVersion(localBodyForm.getLocalBodyVersion());
			}
			
			
			String ordercode = localGovtBodyService.invalidatePRILB(lgbForm, httpSession);
			if (ordercode == null) {
				@SuppressWarnings("unused")
				String message = "Error";
				mv = new ModelAndView("error");
				mv.addObject("message", "Error, Please Check Logs");
				return mv;
			} else {
				if (lgbForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					mv = new ModelAndView("invalidatePRILBSuccess");
					mv.addObject("lbdelete", localGovtBodyService.getMaxVersionLocabody(Integer.parseInt(lbcode)));

				} else if (lgbForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					mv = new ModelAndView("invalidatePRILBSuccess");
					mv.addObject("lbdelete", localGovtBodyService.getMaxVersionLocabody(Integer.parseInt(lbcode)));

					

				}

				else {
					String message = "Error";
					mv = new ModelAndView("error");
					mv.addObject("message", message);
				}
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} finally {
			httpSession.removeAttribute("formbean");
			httpSession.removeAttribute("lblevel");
		}
		return mv;

	}

	/*
	 * Declare districtPanchayatList,localBodyTypelist variable.
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/invalidatetriocalbody")
	public ModelAndView InvalidateTRI(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, HttpSession session, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			char lbType = 'T';
			int operationCode = 58;
			mav = null;
			
			request.setAttribute("stateCode", stateCode);
			
			int locabodyTypecode;
			List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
			if (stateCode != 0) {

				
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
				String govtOrderConfig = hMap.get("govtOrder");
				String mapConfig = hMap.get("mapUpload");
				String message = hMap.get("message");
				if (govtOrderConfig != null && mapConfig != null) {
				
					if (session.getAttribute("formbean") != null) {
						session.removeAttribute("formbean");
						session.removeValue("formbean");
					}
					List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('T', stateCode);
					locabodyTypecode = localBodyTypelist.get(0).getLocalBodyTypeCode();
					if (districtCode != 0) {
						districtPanchayatList = localGovtBodyService.getLandRegionByDistricCode(locabodyTypecode, districtCode, "T");
					} else {
						if (stateCode != 34) {
							districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'T');
						} else {
							districtPanchayatList = localGovtBodyService.getExistingLBListTwoTier(stateCode);
						}
					}
					if (localBodyTypelist.size() > 0) {

						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
					}
					if (districtPanchayatList.size() > 0) {
						model.addAttribute("districtPanchayatList", districtPanchayatList);
					}
					localGovtBodyForm.setLbType('T');
					localGovtBodyForm.setOperation("ITRI");
					

						mav = new ModelAndView("invalidate_trilocalbody");
						mav.addObject("hideMap", mapConfig);
						mav.addObject("govtOrderConfig", govtOrderConfig);
						// model.addAttribute("localBodyTypelist",
						// localBodyTypelist);
						mav.addObject("localGovtBodyForm", localGovtBodyForm);
						mav.addObject("flag1", 0);
						if (localBodyTypelist.size() == 2) {
							mav.addObject("Tier", 2);
						} else if (localBodyTypelist.size() > 2) {
							mav.addObject("Tier", 3);
						}else if (localBodyTypelist.size() == 1) {
							mav.addObject("Tier", 1);
							model.addAttribute("singleTierGpList", localBodyService.getLocalBodiesForManage("T", locabodyTypecode, stateCode, null, districtCode));
						}
						if (districtCode != 0) {
							localBodyTypelist.remove(0);
						}
						model.addAttribute("localBodyTypelist", localBodyTypelist);
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
				}

				
				

				
			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/invalidateLocalBodyforTRI", method = RequestMethod.POST)
	public ModelAndView InvalidateTRIpost(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		
		char lbType = 'T';
		int operationCode = 58;
		int flagCode = 37;
		ModelAndView mv = null;
		String lblevel = null;
		int type = 0;
		String lbcode = null;
		List<Localbody> lblists = null;
		try {

			Map<String, String> hMap = new HashMap<String, String>();
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");
			//String mapConfig = hMap.get("mapUpload");
			String listformat = localGovtBodyForm.getListformat();
			if (!localGovtBodyForm.isLboption()) {
				listformat = listformat.substring(1, listformat.length() - 1);
				localGovtBodyForm.setListformat(listformat);
			}
			localGovtBodyForm.setOperationCode(operationCode);
			localGovtBodyForm.setFlagCode(flagCode);
			localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);
			localGovtBodyForm.setOperation("ITRI");
			httpSession.setAttribute("formbean", localGovtBodyForm);
			lblevel = request.getParameter("flag1");

			type = Integer.parseInt(lblevel);
			if (type == 1) {
				lbcode = localGovtBodyForm.getDistrictpanlbid();
			} else if (type == 2) {
				lbcode = localGovtBodyForm.getIntermediatepanlbid();
			} else if (type == 3) {
				lbcode = localGovtBodyForm.getGrampanlbid();
			}
			if (lbcode == null) {
				lbcode = localGovtBodyForm.getInvalidatedlbcode();
			}
			lblists = localGovtBodyService.getLocalbodyname(lbcode);
			if (lblists.size() > 0) {

				localGovtBodyForm.setLocalBodyNameEnglish(lblists.get(0).getLocalBodyNameEnglish());
				localGovtBodyForm.setLocalBodyNameLocal(lblists.get(0).getLocalBodyNameLocal());
				localGovtBodyForm.setLocalBodyVersion(lblists.get(0).getLocalBodyVersion());
			}

			httpSession.setAttribute("lblevel", lblevel);
			mv = new ModelAndView("redirect:govtOrderCommon.htm");
			mv.addObject("govtOrderConfig", govtOrderConfig);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	@RequestMapping(value = "/invalidateTRILBFinal", method = RequestMethod.POST)
	public ModelAndView InvalidateTRILB(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm lbForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "fileName", required = false) String fileName) throws Exception {

		ModelAndView mv = null;
		// boolean success = false;
		List<Localbody> lblists = null;
		Localbody lbdelete = null;
		String lbcode = null;
		// String[] lbid = null;
		//int lb = 0;
		String lblevel = null;
		int type = 0;
		try {

			//Map<String, String> hMap = new HashMap<String, String>();
			LocalGovtBodyForm lgbForm = (LocalGovtBodyForm) httpSession.getAttribute("formbean");

			lgbForm.setOrderNo(lbForm.getOrderNo());
			lgbForm.setEffectiveDate(lbForm.getEffectiveDate());
			lgbForm.setGazPubDate(lbForm.getGazPubDate());
			lgbForm.setOrderDate(lbForm.getOrderDate());
			lgbForm.getAliasNameEnglish();
			lbForm.getLgd_LBDistrictAtInter();
			lgbForm.getAliasNameEnglish();
			lblevel = (String) httpSession.getAttribute("lblevel");
			type = Integer.parseInt(lblevel);
			if (type == 1) {
				lbcode = lgbForm.getDistrictpanlbid();
			} else if (type == 2) {
				lbcode = lgbForm.getIntermediatepanlbid();
			} else if (type == 3) {
				lbcode = lgbForm.getGrampanlbid();
			}
			if (lbcode == null) {
				if (lgbForm.getInvalidatedlbcode() != null) {
					lbcode = lgbForm.getInvalidatedlbcode();
				}
			}
			
			LocalBodyForm localBodyForm = localBodyService.getLocalBodyFormObject(Integer.parseInt(lbcode));
			if(localBodyForm!=null) {
				lgbForm.setLocalBodyNameEnglish(localBodyForm.getLocalBodyNameEnglish());
				lgbForm.setLocalBodyNameLocal(localBodyForm.getLocalBodyNameLocal());
				lgbForm.setLocalBodyVersion(localBodyForm.getLocalBodyVersion());
			}
				
			String ordercode = localGovtBodyService.invalidateTRILB(lgbForm, httpSession);
			if (ordercode == null) {
				// String message = "Error";
				mv = new ModelAndView("error");
				mv.addObject("message", "Error, Please Check Logs");
				return mv;
			} else {
				if (lgbForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					mv = new ModelAndView("invalidatePRILBSuccess");
					mv.addObject("lbdelete", localGovtBodyService.getMaxVersionLocabody(Integer.parseInt(lbcode)));

				} else if (lgbForm.getGovtOrderConfig().equals("govtOrderGenerate")) {

					mv = new ModelAndView("invalidatePRILBSuccess");
					mv.addObject("lbdelete", localGovtBodyService.getMaxVersionLocabody(Integer.parseInt(lbcode)));

				}

				else {
					String message = "Error";
					mv = new ModelAndView("error");
					mv.addObject("message", message);
				}
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} finally {
			httpSession.removeAttribute("formbean");
			httpSession.removeAttribute("lblevel");
		}
		return mv;

	}

	/*
	 * Declare districtPanchayatList,localBodyTypelist variable
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/invalidateturbanlocalbody")
	public ModelAndView InvalidateUrban(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, HttpSession session, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			mav=seCommantinvalidateturbanlocalbodyProperties(localGovtBodyForm,request,session,model,mav);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	public ModelAndView seCommantinvalidateturbanlocalbodyProperties(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession session, Model model,ModelAndView mav)throws Exception{
		List<LocalbodyListbyState> districtPanchayatList2 = new ArrayList<LocalbodyListbyState>();
		char lbType = 'U';
		int operationCode = 59;
		mav = null;
		
		request.setAttribute("stateCode", stateCode);
		List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
		if (stateCode != 0) {

			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);

			districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, 4);
			districtPanchayatList2 = localGovtBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, 5);

			if (localBodyTypelist.size() > 0) {

				localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
			}
			if (districtPanchayatList.size() > 0) {
				model.addAttribute("districtPanchayatList", districtPanchayatList);
			}
			if (districtPanchayatList2.size() > 0) {
				model.addAttribute("districtPanchayatList2", districtPanchayatList2);
			}
			localGovtBodyForm.setLbType('U');
			localGovtBodyForm.setOperation("IURB");
			Map<String, String> hMap = new HashMap<String, String>();
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");
			String mapConfig = hMap.get("mapUpload");
			String message = hMap.get("message");
			if (govtOrderConfig != null && mapConfig != null) {

				mav = new ModelAndView("invalidate_urblocalbody");
				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				mav.addObject("flag1", 0);
				model.addAttribute("scode", stateCode);

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", message);
			}

		} else {
			mav = new ModelAndView("configview");
			String message = "Please First Select State";
			mav.addObject("msgid", message);
		}
		return mav;
		
	}
	
	
	
	@RequestMapping(value = "/invalidateLocalBodyforURB", method = RequestMethod.POST)
	public ModelAndView InvalidateURBpost(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {
		
		char lbType = 'U';
		int operationCode = 59;
		int flagCode = 38;
		ModelAndView mv = null;
		String lblevel = null;
		int type = 0;
		String lbcode = null;
		List<Localbody> lblists = null;
		try {

			Map<String, String> hMap = new HashMap<String, String>();
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");
			@SuppressWarnings("unused")
			String mapConfig = hMap.get("mapUpload");
			// String listformat = localGovtBodyForm.getListformat();
			localGovtBodyForm.setOperationCode(operationCode);
			localGovtBodyForm.setFlagCode(flagCode);
			localGovtBodyForm.setUserId(userId);
			localGovtBodyForm.setGovtOrderConfig(govtOrderConfig);
			localGovtBodyForm.setOperation("IURB");
			
			lblevel = request.getParameter("flag1");

			type = Integer.parseInt(lblevel);
			if (type == 1) {
				lbcode = localGovtBodyForm.getDistrictpanlbid();
			} else if (type == 2) {
				lbcode = localGovtBodyForm.getIntermediatepanlbid();
			}

			if (lbcode == null) {
				lbcode = localGovtBodyForm.getInvalidatedlbcode();
			}
			LocalBodyForm localBodyForm = localBodyService.getLocalBodyFormObject(Integer.parseInt(lbcode));
			if (localBodyForm!=null) {

				localGovtBodyForm.setLocalBodyNameEnglish(localBodyForm.getLocalBodyNameEnglish());
				localGovtBodyForm.setLocalBodyNameLocal(localBodyForm.getLocalBodyNameLocal());
				localGovtBodyForm.setLocalBodyVersion(localBodyForm.getLocalBodyVersion());
				localGovtBodyForm.setLocalBodyCode(localBodyForm.getLocalBodyCode());
				localGovtBodyForm.setMinorVersion(localBodyForm.getMinorVersion());
				localGovtBodyForm.setLocalBodyTypeCode(localBodyForm.getLocalBodyTypeCode().toString());
				
			}
			
			if(localBodyForm.getIsdisturbed()!=null && ("true").equalsIgnoreCase(localBodyForm.getIsdisturbed())) {
				httpSession.setAttribute("lblevel", lblevel);
				httpSession.setAttribute("formbean", localGovtBodyForm);
				mv = new ModelAndView("redirect:govtOrderCommon.htm");
				mv.addObject("govtOrderConfig", govtOrderConfig);
			}else {
				
				String ordercode = localGovtBodyService.InvalidateUrbanLocalbodyProcess(localGovtBodyForm);
				if (ordercode==null || ordercode!=null && ordercode.length()<=0) {
					mv = new ModelAndView("error");
					mv.addObject("message", "Error, Please Check Logs");
					return mv;
				} else {
					mv = new ModelAndView("invalidateUrbanLBSuccess");
					mv.addObject("lbdelete",localGovtBodyService.getMaxVersionLocabody(localGovtBodyForm.getLocalBodyCode()));
					
				}
			}
			
			
			//mv.addObject("govtOrderConfig", govtOrderConfig);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	@RequestMapping(value = "/invalidateURBFinal", method = RequestMethod.POST)
	public ModelAndView InvalidateURBLB(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm lgbForm, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request,Model model) throws Exception {
		ModelAndView mv = null;
		try {
			init(httpSession);
			
			if(lgbForm.getLocalBodyCode()==null) {
				LocalGovtBodyForm distLgbForm=(LocalGovtBodyForm)httpSession.getAttribute("formbean");
				if(distLgbForm!=null) {
				lgbForm.setLocalBodyCode(distLgbForm.getLocalBodyCode());
				lgbForm.setLocalBodyNameEnglish(distLgbForm.getLocalBodyNameEnglish());
				lgbForm.setOrderNo(distLgbForm.getLgd_LBorderNo());
				lgbForm.setOrderDate(distLgbForm.getLgd_LBorderDate());
				lgbForm.setEffectiveDate(distLgbForm.getLgd_LBeffectiveDate());
				lgbForm.setGazPubDate(distLgbForm.getLgd_LBgazPubDate());
				lgbForm.setLocalBodyTypeCode(distLgbForm.getLbTypeCode().toString());
				lgbForm.setOrderPath("UPLOAD");
				lgbForm.setUnResolvedFlag(distLgbForm.isUnResolvedFlag());
				}
			}
			
			
			
			govtOrderValidator.validateGovermentnotMandatory(lgbForm, result);
			if(result.hasErrors()){
				mv=seCommantinvalidateturbanlocalbodyProperties(lgbForm,request,httpSession,model,mv);
				return mv;
			}
			
			lgbForm=setAttributeUrabnLocalbodyInvalidate(lgbForm);
			String ordercode = localGovtBodyService.InvalidateUrbanLocalbodyProcess(lgbForm);
			if (ordercode==null || ordercode!=null && ordercode.length()<=0) {
				mv = new ModelAndView("error");
				mv.addObject("message", "Error, Please Check Logs");
				return mv;
			} else {
				model.addAttribute("lbdelete",localGovtBodyService.getMaxVersionLocabody(lgbForm.getLocalBodyCode()));
				mv = new ModelAndView("invalidateUrbanLBSuccess");
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		} finally {
			httpSession.removeAttribute("formbean");
			httpSession.removeAttribute("lblevel");
		}
		return mv;

	}
	
	public LocalGovtBodyForm setAttributeUrabnLocalbodyInvalidate(LocalGovtBodyForm lgbForm)throws Exception{
		lgbForm.setOperationCode(59);
		lgbForm.setFlagCode(38);
		lgbForm.setUserId(userId);
		
		
		if(lgbForm.getDistrictpanlbid()!=null &&  !("").equals(lgbForm.getDistrictpanlbid().trim())){
			lgbForm.setLocalBodyCode(Integer.parseInt(lgbForm.getDistrictpanlbid()));
		}
		if(lgbForm.getLgd_LBTypeName()!=null &&  !("").equals(lgbForm.getLgd_LBTypeName().trim())){
			lgbForm.setLocalBodyTypeCode(lgbForm.getLgd_LBTypeName().split(":")[0]);
		}
		List<CommonsMultipartFile> gazettePublicationUpload= lgbForm.getGazettePublicationUpload();
		if(gazettePublicationUpload!=null && !gazettePublicationUpload.isEmpty()){
			lgbForm.setOrderPath(gazettePublicationUpload.get(0).getFileItem().getName());
		}
		if(!(isparamValidate(lgbForm.getOrderNo(),'S') && isparamValidate(lgbForm.getOrderDate(),'D') && 
			isparamValidate(lgbForm.getEffectiveDate(),'D') && isparamValidate(lgbForm.getOrderPath(),'S')))
		{
			lgbForm.setOrderNo(null);
			lgbForm.setOrderDate(null);
			lgbForm.setEffectiveDate(null);
			lgbForm.setOrderPath(null);
			lgbForm.setMandatory(Boolean.FALSE);
		}else{
			lgbForm.setMandatory(Boolean.TRUE);
		}
		return lgbForm;
		
		
	}
	
	public static boolean isparamValidate(Object param,Character type)throws Exception{
		if(param==null ){
			return false;
		}
		if(type=='S' && ("").equals(param.toString().trim())){
			return false;
		}
		
		return true;
	}

	// View Local Govt Body Details modified by sushil on 30-01-2013

	// Methods have been shifted from ViewController.java ----on 07/03/2013---by
	// Arnab----Start
	// @RequestMapping(value = "/viewLocalBodyforPRI", method =
	// RequestMethod.GET)
	public ModelAndView viewLocalBodyforPanchayat(HttpSession session, @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, Model model) {
		String skip = request.getParameter("skip");
		ModelAndView mav = null;
		if (skip == null) {
			LocalGovtBodyDraftController draftController = new LocalGovtBodyDraftController();
			mav = draftController.getDraftedLGObjectList(model, request);
			if (mav != null && mav.getModel() != null) {
				Map<String, Object> map = mav.getModel();
				if (map.containsKey("fileList")) {
					mav.addObject("fileList", map.get("fileList"));
					mav.setViewName("viewLocalBodyDraftList");
					return mav;
				}
			}
		}

		//List<LocalbodyListbyState> intermediatePanchayatList = new ArrayList<LocalbodyListbyState>();
		try {
		

			if (stateCode != 0) {
				
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'P');
				if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {
					
					char lbType = 'P';
					int operationCode = 45;
					Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);

					String govtOrderConfig = hMap.get("govtOrder");
					String mapConfig = hMap.get("mapUpload");
					String message = hMap.get("message");
					if (message != null) {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
						return mav;
					} else {
						// mav = new ModelAndView("view_localgovtbody");
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
						mav.addObject("mapConfig", mapConfig);
						mav.addObject("govtOrderConfig", govtOrderConfig);
					}

					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList", getLocalGovtSetupList);

					localGovtBodyForm.setLgd_lbCategory("P");
					request.setAttribute("stateCode", stateCode);
					List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
					String type = localBodyTypelist.get(0).getLevel();

					int lbtype = localBodyTypelist.get(0).getLocalBodyTypeCode();
					localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);

					if (districtCode != 0) {
						List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);
						localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
						model.addAttribute("districtPanchayatList", districtPanchayatList);
					} else {
						List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'P', 'D');

						localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
						if (districtPanchayatList.size() > 0) {
							model.addAttribute("districtPanchayatList", districtPanchayatList);
						}
					}
					/*
					 * if (districtCode != 0) { intermediatePanchayatList =
					 * localGovtBodyService.getLandRegionWisedistrict(type,
					 * districtCode, lbtype);
					 * model.addAttribute("localBodyforSubDistList",
					 * intermediatePanchayatList); } else {
					 * intermediatePanchayatList =
					 * localGovtBodyService.getExistingLBListbyStateandCategoryInter
					 * (stateCode, 'P'); if (intermediatePanchayatList.size() >
					 * 0) { model.addAttribute("localBodyforSubDistList",
					 * intermediatePanchayatList); } }
					 */
					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
					}
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("lbType", lbType);
					localGovtBodyForm.setLbtypeLevel('P');
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					return mav;
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Please first Set Up Local Government for this State");
				}
			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// Methods have been shifted from ViewController.java ----on 07/03/2013---by
	// Arnab----End

	// View TraditionalLocal Govt Body Details modified by Arnab on 07-02-2013
	// Method have been shifted from ViewController.java ----on 07/03/2013---by
	// Arnab----Start
	/*
	 * Declare localBodyTypelist variable
	 */
	// @RequestMapping(value = "/viewLocalBodyforTraditional", method =
	// RequestMethod.GET)
	public ModelAndView viewLocalBodyforTraditional(HttpSession session, @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		List<LocalbodyListbyState> intermediatePanchayatList = new ArrayList<LocalbodyListbyState>();
		try {

		

			if (stateCode != 0) {
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'T');

				if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {

					char lbType = 'T';
					int operationCode = 47;
					Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
					String govtOrderConfig = hMap.get("govtOrder");
					String mapConfig = hMap.get("mapUpload");
					String message = hMap.get("message");
					if (message != null) {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
						return mav;
					} else {
						// mav = new ModelAndView("view_localgovtbody");
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
						mav.addObject("mapConfig", mapConfig);
						mav.addObject("govtOrderConfig", govtOrderConfig);
					}

					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList", getLocalGovtSetupList);
					localGovtBodyForm.setLgd_lbCategory("T");
					List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
					localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('T', stateCode);
					String type = localBodyTypelist.get(0).getLevel();

					int lbtype = localBodyTypelist.get(0).getLocalBodyTypeCode();
					localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);

					if (districtCode != 0) {
						List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);
						localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
						model.addAttribute("districtPanchayatList", districtPanchayatList);
					} else {
						List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'T', 'D');

						localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
						if (districtPanchayatList.size() > 0) {
							model.addAttribute("districtPanchayatList", districtPanchayatList);
						}
					}

					if (districtCode != 0) {
						intermediatePanchayatList = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);
						model.addAttribute("localBodyforSubDistList", intermediatePanchayatList);
					} else {
						intermediatePanchayatList = localGovtBodyService.getExistingLBListbyStateandCategoryInter(stateCode, 'T');
						if (intermediatePanchayatList.size() > 0) {
							model.addAttribute("localBodyforSubDistList", intermediatePanchayatList);
						}
					}

					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
					}
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("lbType", lbType);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);

					return mav;
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Please first Set Up Local Government for this State");
				}
			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	// Methods have been shifted from ViewController.java ----on 07/03/2013---by
	// Arnab----End

	// View Urban Local Govt Body Details modified by Arnab on 07-02-2013
	// Method have been shifted from ViewController.java ----on 07/03/2013---by
	// Arnab----Start
	/*
	 * Declare localBodyTypelist variable
	 */
	// @RequestMapping(value = "/viewLocalBodyforUrban", method =
	// RequestMethod.GET)
	public ModelAndView viewLocalBodyforUrban(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		try {
			

			if (stateCode != 0) {
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'U');

				if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {

					char lbType = 'U';
					int operationCode = 46;
					Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
					String govtOrderConfig = hMap.get("govtOrder");
					String mapConfig = hMap.get("mapUpload");
					String message = hMap.get("message");
					if (message != null) {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
						return mav;
					} else {
						// mav = new ModelAndView("view_localgovtbody");
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
						mav.addObject("mapConfig", mapConfig);
						mav.addObject("govtOrderConfig", govtOrderConfig);
					}

					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList", getLocalGovtSetupList);

					localGovtBodyForm.setLgd_lbCategory("U");
					List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
					localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);

					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
					}
					// mav = new ModelAndView("view_localgovtbody");
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Please first Set Up Local Government for this State");

				}
			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	// Method have been shifted from ViewController.java ----on 07/03/2013---by
	// Arnab----End

	/**
	 * Declare districtPanchayatList variable for modify PRI localBody Details.
	 * On click of get button
	 * 
	 * @modified by sushil on 12-02-2013
	 */
	// Method have been shifted from ViewController.java ----on 07/03/2013---by
	// Arnab----Start
	@RequestMapping(value = "/modifyLocalBodyforPRI", method = RequestMethod.POST)
	public ModelAndView viewLocalBodyList(HttpSession session, @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, HttpServletRequest request, Model model) {

		ModelAndView mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_OTHER);
		try {
			
			String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
			String Catageryforwad = localGovtBodyForm.getLgd_lbCategory();
			/*
			 * variables added for the GTA (manage localbody PRI) author Ashish
			 * Dhupia , Date : 23/07/2014
			 */
			String gtadp = localGovtBodyForm.getParentList();
			String getGtaList = localGovtBodyForm.getGtaList();
			String getGtaInterPanch = localGovtBodyForm.getGtaInterPanch();
			String strArray[] = localBodyTypeCodeDup.split(":");
			String typeCode = strArray[0];
			String type = strArray[1];
			String parentLB = strArray[4];
			char lbType = localGovtBodyForm.getLbtypeLevel();
			String lbCode = null;
			Integer lbCodetemp = 0;
			Integer lbtype = Integer.parseInt(typeCode);
			List<LocalbodyListbyState> districtPanchayatList = null;
			List<LocalbodyforStateWise> localBodyTypelist1 = localGovtBodyForm.getLocalBodyTypelist();
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist1);
			String intermediatePanchayatCodes1 = null;
			if (localGovtBodyForm.getLgd_lbCategory() != null && localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("P")) {
				districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
				localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
				intermediatePanchayatCodes1 = localGovtBodyForm.getLocalBodyNameEnglishListSource();
			}
			// List<GovernmentOrder> govtList =
			// govtOrderService.getGovtOrederDetails();

			// List<ParentWiseLocalBodies> parentWiseLocalBodies = null;

			// intermediatePanchayatCodes1 =
			// localGovtBodyForm.getLocalBodyNameEnglishListSource();
			districtPanchayatList = null;
			model.addAttribute("lgdLBTypeName", localGovtBodyForm.getLgd_LBTypeName()); // setting
																						// the
																						// attribute
																						// to
																						// next
																						// jsp
																						// for
																						// getting
																						// the
																						// localbodytype
																						// and
																						// other
																						// values
																						// by
																						// kirnadeep
																						// 14/1/2014
			if (localGovtBodyForm.getLgd_lbCategory() != null && localGovtBodyForm.getLgd_lbCategory().length() > 0) {
				List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);

				if (localBodyTypelist != null && localBodyTypelist.size() > 0) {

					String districtPanchayatCodes = localGovtBodyForm.getLocalBodyNameEnglishList();
					String intermediatePanchayatCodes = localGovtBodyForm.getLocalBodyNameEnglishListSource();
					// String villagepanchayatCodes =
					// localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg();
					String districtPanchayatCode = districtPanchayatCodes;
					String intermediatePanchayatCode = intermediatePanchayatCodes;
					
					int offset = 0;
					int limit = 3000;

					if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("U")) {
						// /////////////////////////////pagination///////////////////////////////
						List<Localbody> lgdLocalGovtBodyList = null;
						List<UlbBean> lgdLocalGovtBodyListUrban = null;
						localGovtBodyForm.setLimit(limit);
						localGovtBodyForm.setOffset(offset);
						session.setAttribute("limit", localGovtBodyForm.getLimit());
						boolean movenext = true;
						session.setAttribute("offset", localGovtBodyForm.getOffset());
						session.setAttribute("lbtype", lbtype);
						if (districtCode == 0) {
							lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode, Integer.parseInt(typeCode), offset, limit);
						} else {
							lgdLocalGovtBodyListUrban = localGovtBodyService.getPanchayatListbylocalbodyUrban(districtCode, Integer.parseInt(typeCode), offset, limit);
						}

						int counter = localGovtBodyService.countLocalBodyDetails(lbtype, stateCode);
						session.setAttribute("counter", counter);
						model.addAttribute("localGovtBodyForm", localGovtBodyForm);
						if (districtCode == 0) {
							model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
						} else {
							model.addAttribute("localbodysize", lgdLocalGovtBodyListUrban.size());
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyListUrban);
						}

						model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
						model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
						model.addAttribute("counter", counter);
						if (offset == (counter / limit)) {
							movenext = false;
						} else {
							movenext = true;
						}
						model.addAttribute("movenext", movenext);
						model.addAttribute("LocalBodyCount",
								"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of " + (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));

						if (districtCode == 0) {
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

							localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
							if (lgdLocalGovtBodyList.size() <= 0) {
								model.addAttribute("listnull", "LocalBody not present");
							}
						} else {
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyListUrban);

							// localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyListUrban);
							if (lgdLocalGovtBodyListUrban.size() <= 0) {
								model.addAttribute("listnull", "LocalBody not present");
							}
						}
						// /////////////////////////////pagination///////////////////////////////

					} else if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("P")) {
						if (type.equalsIgnoreCase("D")) {
							if (districtCode != 0) {
								List<LocalbodyListbyState> lgdLocalGovtBodyList = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

								localGovtBodyForm.setLgdLocalGovtBodyList(lgdLocalGovtBodyList);
							}

							else {

								// /////////////////////////////pagination///////////////////////////////

								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext = true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode, Integer.parseInt(typeCode), offset, limit);
								int counter = localGovtBodyService.countLocalBodyDetails(lbtype, stateCode);
								session.setAttribute("counter", counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
								model.addAttribute("counter", counter);
								if (offset == (counter / limit)) {
									movenext = false;
								} else {
									movenext = true;
								}
								model.addAttribute("movenext", movenext);
								model.addAttribute("LocalBodyCount",
										"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
												+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

								localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
								if (lgdLocalGovtBodyList.size() <= 0) {
									model.addAttribute("listnull", "LocalBody not present");
								}

								// /////////////////////////////pagination///////////////////////////////

							}
						}

						if (type.equalsIgnoreCase("I")) {
							/*
							 * "I" condition for the GTA (manage localbody PRI)
							 * author Ashish Dhupia , Date : 23/07/2014
							 */
							boolean isGTA = false;
							if (!"".equals(gtadp) && (Integer.parseInt(gtadp) > 1)) {
								session.setAttribute("gtadp", gtadp);
								if (getGtaList != null) {

									isGTA = true;
									/*
									 * lbCodetemp=localGovtBodyService.getlblc(
									 * Integer.parseInt(getGtaList));
									 * lbCode=lbCodetemp.toString();
									 */
								}
								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext = true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								session.setAttribute("getGtaList", getGtaList);
								session.setAttribute("localBCode", localGovtBodyForm.getLocalBodyNameEnglishList());

								// List<Localbody> lgdLocalGovtBodyList =
								// localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode,
								// Integer.parseInt(typeCode),
								// districtPanchayatCodes, offset, limit);
								List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), getGtaList, offset, limit);
								int counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, getGtaList);
								session.setAttribute("counter", counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
								model.addAttribute("counter", counter);
								model.addAttribute("gtadpPost", gtadp);
								model.addAttribute("getGtaListPost", getGtaList);
								if (offset == (counter / limit)) {
									movenext = false;
								} else {
									movenext = true;
								}
								model.addAttribute("movenext", movenext);
								model.addAttribute("LocalBodyCount",
										"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
												+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

								localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
								if (lgdLocalGovtBodyList.size() <= 0) {
									model.addAttribute("listnull", "LocalBody not present");
								}
								model.addAttribute("hdnDistrictPanchayat", getGtaList);
							}

							if (districtPanchayatCodes != null && !isGTA) {

								// /////////////////////////////pagination///////////////////////////////
								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext = true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);

								session.setAttribute("localBCode", localGovtBodyForm.getLocalBodyNameEnglishList());

								// List<Localbody> lgdLocalGovtBodyList =
								// localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode,
								// Integer.parseInt(typeCode),
								// districtPanchayatCodes, offset, limit);
								List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), districtPanchayatCodes, offset, limit);
								int counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, districtPanchayatCodes);
								session.setAttribute("counter", counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
								model.addAttribute("counter", counter);
								model.addAttribute("gtadpPost", gtadp);
								if (offset == (counter / limit)) {
									movenext = false;
								} else {
									movenext = true;
								}
								model.addAttribute("movenext", movenext);
								model.addAttribute("LocalBodyCount",
										"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
												+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

								localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
								if (lgdLocalGovtBodyList.size() <= 0) {
									model.addAttribute("listnull", "LocalBody not present");
								}
								model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

								// /////////////////////////////pagination///////////////////////////////

							} else {
								/*
								 * int localbodyCode =
								 * Integer.parseInt(districtPanchayatCodes);
								 * List<ParentWiseLocalBodies>
								 * localBodyViewChild = localGovtBodyService.
								 * getchildlocalbodiesByParentcode
								 * (localbodyCode);
								 * model.addAttribute("LocalGovtBodyList",
								 * localBodyViewChild);
								 * 
								 * localGovtBodyForm.setLocalBodyViewChild(
								 * localBodyViewChild);
								 */
								if (!isGTA) {
									List<LocalbodyListbyState> localBodyViewChild = localGovtBodyService.getPanchayatListbylblcCode(stateCode, Integer.parseInt(typeCode));
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);
									localGovtBodyForm.setLgdLocalGovtBodyList(localBodyViewChild);
									// localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);

								}
							}
						}
						int localBodyCode1 = 0;
						boolean isGTA = false;
						if (type.equalsIgnoreCase("V")) {

							/*
							 * "V" condition for the GTA (manage localbody PRI)
							 * author Ashish Dhupia , Date : 23/07/2014
							 */
							if (!"".equals(gtadp) && (Integer.parseInt(gtadp) > 1)) { // !gtadp.equalsIgnoreCase("1")){
								isGTA = true;
								if (getGtaList != null && !getGtaList.equalsIgnoreCase("0")) {
									localBodyCode1 = Integer.parseInt(getGtaInterPanch);
									if (!getGtaInterPanch.equals("")) {
										lbCodetemp = localGovtBodyService.getlblc(Integer.parseInt(getGtaInterPanch));
										// lbCode=districtPanchayatCodes;
										lbCode = lbCodetemp.toString();
										// lbCode=intermediatePanchayatCodes;
									}
									session.setAttribute("getGtaInterPanch", getGtaInterPanch);
									session.setAttribute("localBCode", localGovtBodyForm.getLocalBodyNameEnglishListSource());
								}

								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext = true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								List<Localbody> localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), lbCode, offset, limit);
								int counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, lbCode);

								session.setAttribute("counter", counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", localBodyViewChild.size());
								model.addAttribute("LocalGovtBodyList", localBodyViewChild);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
								model.addAttribute("counter", counter);
								if (offset == (counter / limit)) {
									movenext = false;
								} else {
									movenext = true;
								}
								model.addAttribute("movenext", movenext);
								model.addAttribute("LocalBodyCount",
										"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
												+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList", localBodyViewChild);
								model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);
								model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
								/* model. added for post */
								model.addAttribute("gtadpPost", gtadp);
								model.addAttribute("getGtaListPost", getGtaList);
								model.addAttribute("getGtaInterPanchPost", getGtaInterPanch);
								localGovtBodyForm.setLocalbodyDetails(localBodyViewChild);
								if (localBodyViewChild.size() <= 0) {
									model.addAttribute("listnull", "LocalBody not present");
								}
							}

							if (districtPanchayatCodes != null && !isGTA) {
								if (intermediatePanchayatCodes != null) {
									localBodyCode1 = Integer.parseInt(intermediatePanchayatCodes);
									// if(!intermediatePanchayatCodes.equals(null)
									// ||
									// !intermediatePanchayatCodes.equals(""))
									if (!intermediatePanchayatCodes.equals("")) {
										lbCodetemp = localGovtBodyService.getlblc(Integer.parseInt(intermediatePanchayatCodes));
										// lbCode=districtPanchayatCodes;
										lbCode = lbCodetemp.toString();
										// lbCode=intermediatePanchayatCodes;
									}
									session.setAttribute("localBCode", localGovtBodyForm.getLocalBodyNameEnglishListSource());
								} else {
									localBodyCode1 = Integer.parseInt(districtPanchayatCodes);
									// if(!districtPanchayatCodes.equals(null)
									// || !districtPanchayatCodes.equals(""))
									if (!districtPanchayatCodes.equals("")) {
										lbCodetemp = localGovtBodyService.getlblc(Integer.parseInt(districtPanchayatCodes));
										lbCode = lbCodetemp.toString();
										// lbCode=districtPanchayatCodes;
									}
									session.setAttribute("localBCode", localGovtBodyForm.getLocalBodyNameEnglishList());
								}
								// /////////////////////////////pagination///////////////////////////////

								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext = true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								// session.setAttribute("localBCode",
								// localGovtBodyForm.getLocalBodyNameEnglishListSource());

								/*
								 * List<Localbody> localBodyViewChild =
								 * localGovtBodyService
								 * .getPanchayatListbylocalbodyVillagepanchayat
								 * (stateCode, Integer.parseInt(typeCode),
								 * intermediatePanchayatCodes, offset, limit);
								 * int counter = localGovtBodyService.
								 * countLocalBodyDetailsforVillagePanchayat
								 * (lbtype, stateCode,
								 * intermediatePanchayatCodes);
								 */
								List<Localbody> localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), lbCode, offset, limit);
								int counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, lbCode);

								session.setAttribute("counter", counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", localBodyViewChild.size());
								model.addAttribute("LocalGovtBodyList", localBodyViewChild);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
								model.addAttribute("counter", counter);
								model.addAttribute("gtadpPost", gtadp);
								if (offset == (counter / limit)) {
									movenext = false;
								} else {
									movenext = true;
								}
								model.addAttribute("movenext", movenext);
								model.addAttribute("LocalBodyCount",
										"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
												+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList", localBodyViewChild);
								model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);
								model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
								localGovtBodyForm.setLocalbodyDetails(localBodyViewChild);
								if (localBodyViewChild.size() <= 0) {
									model.addAttribute("listnull", "LocalBody not present");
								}

								// /////////////////////////////pagination///////////////////////////////
							} else {
								if (localGovtBodyForm.getLocalBodyNameEnglishListSource() != null) {
									if (isGTA) {
										localBodyCode1 = Integer.parseInt(getGtaInterPanch);
									} else {

										localBodyCode1 = Integer.parseInt(intermediatePanchayatCodes);
									}
									List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService.getchildlocalbodiesByParentcode(localBodyCode1);
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);
									localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

									model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
								}
								if (localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() != null) {
									localBodyCode1 = Integer.parseInt(districtPanchayatCodes);
									List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService.getchildlocalbodiesByParentcode(localBodyCode1);
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);
									localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

									model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
								}
								if (localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() == null) {
									localBodyCode1 = Integer.parseInt(typeCode);
									List<LocalbodyListbyState> localBodyViewChild = localGovtBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, Integer.parseInt(typeCode));
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);
									// localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

									model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
								}

							}
						}
					} else if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("T")) {
						int lbdistrictAtInter = 0;
						int lbdistrictAtVillage = 0;
						if (localGovtBodyForm.getLgd_LBDistrictAtInter() != null && !("".equalsIgnoreCase(localGovtBodyForm.getLgd_LBDistrictAtInter()))) {
							lbdistrictAtInter = Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtInter());
						}
						if (localGovtBodyForm.getLgd_LBDistrictAtVillage() != null && !("".equalsIgnoreCase(localGovtBodyForm.getLgd_LBDistrictAtVillage()))) {
							lbdistrictAtVillage = Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtVillage());
						}
						List<Localbody> localBodyViewChild = null;
						int counter = 0;
						//boolean movenext = true;
						if (lbdistrictAtVillage > 0) {
							localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), localGovtBodyForm.getLgd_LBDistrictAtVillage(), offset, limit);
							counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, localGovtBodyForm.getLgd_LBDistrictAtVillage());
							session.setAttribute("localBCode", localGovtBodyForm.getLgd_LBDistrictAtVillage());
						}
						if (lbdistrictAtVillage == 0 && lbdistrictAtInter > 0) {
							localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), localGovtBodyForm.getLgd_LBDistrictAtInter(), offset, limit);
							counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, localGovtBodyForm.getLgd_LBDistrictAtInter());
							session.setAttribute("localBCode", localGovtBodyForm.getLgd_LBDistrictAtInter());
						}
						if (lbdistrictAtVillage == 0 && lbdistrictAtInter == 0) {
							localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbody(stateCode, Integer.parseInt(typeCode), offset, limit);
							counter = localGovtBodyService.countLocalBodyDetails(lbtype, stateCode);
						}
						List<LocalbodyListbyState> intermediatePanchayatList = new ArrayList<LocalbodyListbyState>();
						intermediatePanchayatList = localGovtBodyService.getExistingLBListbyStateandCategoryInter(stateCode, 'T');
						if (intermediatePanchayatList.size() > 0) {
							model.addAttribute("localBodyforSubDistList", intermediatePanchayatList);
						}
						session.setAttribute("counter", counter);
						model.addAttribute("localGovtBodyForm", localGovtBodyForm);
						model.addAttribute("localbodysize", localBodyViewChild.size());
						model.addAttribute("LocalGovtBodyList", localBodyViewChild);
						model.addAttribute("hdnDistrictPanchayat", lbdistrictAtInter);
						model.addAttribute("hdnIntermediatePanchayat", lbdistrictAtVillage);
						model.addAttribute("lgd_LBDistrictAtInter", lbdistrictAtInter);
						model.addAttribute("lgd_LBDistrictAtVillage", lbdistrictAtVillage);
						/*
						 * model.addAttribute("limits",
						 * Integer.parseInt(session.
						 * getAttribute("limit").toString()));
						 * model.addAttribute("offsets",
						 * Integer.parseInt(session
						 * .getAttribute("offset").toString()))
						 */;
						model.addAttribute("counter", counter);
						/*
						 * if (offset == (counter / limit)){ movenext = false;
						 * }else{ movenext = true; }
						 * model.addAttribute("movenext", movenext);
						 * model.addAttribute("LocalBodyCount", "Page " +
						 * (Integer
						 * .parseInt(session.getAttribute("offset").toString())
						 * + 1) + " of " +
						 * (Integer.parseInt(session.getAttribute
						 * ("counter").toString()) /
						 * Integer.parseInt(session.getAttribute
						 * ("limit").toString()) + 1));
						 */

						/*
						 * model.addAttribute("hdnDistrictPanchayat",
						 * districtPanchayatCodes);
						 * model.addAttribute("hdnIntermediatePanchayat",
						 * intermediatePanchayatCodes);
						 */
						localGovtBodyForm.setLocalbodyDetails(localBodyViewChild);
						if (localBodyViewChild.size() <= 0) {
							model.addAttribute("listnull", "LocalBody not present");
						}
					}
					session.setAttribute("typeCode", typeCode);
					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
					}
					if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("T")) {
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
						if (districtCode != 0) {
							List<LocalbodyListbyState> districtPanchayatList1 = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);

							localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList1);
						} else {
							districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'T', 'D');
						}
					} else if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("P")) {
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
						if (districtCode != 0) {
							List<LocalbodyListbyState> districtPanchayatList1 = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);

							localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList1);
						} else {
							districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'P', 'D');
						}
					} else if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("U")) {
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
						/*
						 * if (districtCode != 0) { List<LocalbodyListbyState>
						 * districtPanchayatList1 =
						 * localGovtBodyService.getLandRegionWisedistrict(type,
						 * districtCode, lbtype);
						 * 
						 * localGovtBodyForm.setDistrictPanchayatList(
						 * districtPanchayatList);
						 * model.addAttribute("districtPanchayatList",
						 * districtPanchayatList1); } else {
						 * districtPanchayatList =
						 * localGovtBodyService.getPanchayatListbyStateandCategory
						 * (stateCode, 'P', 'D'); }
						 */
					}

					session.setAttribute("lgtLocalBodyType", localBodyTypelist);

					model.addAttribute("districtPanchayatList", districtPanchayatList);
					session.setAttribute("districtPanchayatList", districtPanchayatList);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					session.setAttribute("districtPanchayatCodes", districtPanchayatCode);
					session.setAttribute("intermediatePanchayatCodes", intermediatePanchayatCode);
				}

				localGovtBodyForm.setLbtypeLevel(lbType);
				localGovtBodyForm.setParentLB(parentLB);
				localGovtBodyForm.setHiddenLevel(type);
				localGovtBodyForm.setDistrictCode(Integer.toString(districtCode));
				mav.addObject("localGovtBodyForm", localGovtBodyForm);

				session.setAttribute("localGovtBodyForm", localGovtBodyForm);
				request.setAttribute("stateCode", stateCode);
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("lbType", lbType);
				model.addAttribute("lbtypeLevel", type);
				model.addAttribute("hiddenLevel", type);
				model.addAttribute("stateCode", stateCode);
				session.setAttribute("Selected", localBodyTypeCodeDup);
				session.setAttribute("Catagery", Catageryforwad);
				session.setAttribute("intermediatePanchayatCodes", intermediatePanchayatCodes1);
				session.setAttribute("lbtype", lbtype);

			}
			// }

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	// Method have been shifted from ViewController.java ----on 07/03/2013---by
	// Arnab----End

	// Method have been shifted from ViewController.java ----on 07/03/2013---by
	// Arnab----Start
	@RequestMapping(value = "/ViewLocalBodyforPRIPost")
	public ModelAndView viewLocalBodyListforView(HttpSession session, @ModelAttribute("viewlocalbody") LocalGovtBodyForm viewlocalbody, BindingResult result, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		try {
			Integer localBodyCode = viewlocalbody.getParentwiseId();
			mav = new ModelAndView("view_localgovtbodyDeatils");
			List<LocalGovtBody> viewlocalbody1 = localGovtBodyService.viewlocalbodyDetails(localBodyCode);
			model.addAttribute("viewlocalbody", viewlocalbody1.get(0));
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/*
	 * Declare districtPanchayatList,localBodyTypelist variable to ivalidate PRI
	 * localBody Form
	 */
	@RequestMapping(value = "/invalidatePRIDisturbed")
	public ModelAndView invalidatePRIDisturbed(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, HttpSession session, Model model, HttpServletRequest request) {

		//List<GetLandRegionWise> lbdist = null;

		//GetLandRegionWise ele = null;
		//LocalbodyListbyState lbs = null;
		ModelAndView mav = null;
		//String warningEntiesFlag = request.getParameter("warningEntiesFlag");
		String id = request.getParameter("id");
		int localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		//List<ParentWiseLocalBodies> lb = null;
		List<LocalBodyDetails> lbdetail = null;
		List<LocalBodyDetails> lbdetailnew = null;
		String lbnameInvalidated = null;
		int lbtypeinvalidate = 0;
		List<LocalbodyforStateWise> localBodyTypelistnew = new ArrayList<LocalbodyforStateWise>();
		String parentLocalbody = null;
		String grandparentLB = null;
		int parentlbcode = 0;
		int grandparentLBcode = 0;
		String lbdisturbreason = null;

		try {

			char lbType = 'P';
			int operationCode = 57;
			mav = null;
			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			request.setAttribute("stateCode", stateCode);
			
			List<LocalbodyforStateWise> localBodyTypelist = null;
			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");

			}
			localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);

			if (localBodyTypelist.size() > 0) {

				localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
			}

			if (districtCode != 0) {
				districtPanchayatList = localGovtBodyService.getLandRegionByDistricCode(1, districtCode, "P");
			} else {
				if (stateCode != 34) {
					districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
				} else {
					districtPanchayatList = localGovtBodyService.getExistingLBListTwoTier(stateCode);
				}
			}
			if (localBodyTypelist.size() > 0) {

				localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
			}
			if (districtPanchayatList.size() > 0) {
				model.addAttribute("districtPanchayatList", districtPanchayatList);
			}

			localGovtBodyForm.setLbType('P');
			localGovtBodyForm.setOperation("IPRI");
			localGovtBodyForm.setUnResolvedFlag(Boolean.TRUE);
			Map<String, String> hMap = new HashMap<String, String>();
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");
			String mapConfig = hMap.get("mapUpload");
			//String message = hMap.get("message");
			if (govtOrderConfig != null && mapConfig != null) {

				mav = new ModelAndView("invalidate_disturblocalgovtbody");
				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				mav.addObject("flag1", 0);
				if (localBodyTypelist.size() == 2) {
					mav.addObject("Tier", 2);
					mav.addObject("Tiertype", 2);
				} else if (localBodyTypelist.size() > 2) {
					mav.addObject("Tier", 3);
					mav.addObject("Tiertype", 3);
				}

				lbdetail = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
				if (lbdetail.size() > 0) {

					lbtypeinvalidate = lbdetail.get(0).getLocalBodyTypeCode();
					for (LocalbodyforStateWise obj : localBodyTypelist) {
						if (obj.getLocalBodyTypeCode() == lbtypeinvalidate) {
							localBodyTypelistnew.add(obj);
							break;
						}
					}
					model.addAttribute("localBodyTypelist", localBodyTypelistnew);
					if (lbtypeinvalidate == 1) {
						lbnameInvalidated = lbdetail.get(0).getLocalBodyNameEnglish();
						model.addAttribute("invalidatedlb", lbnameInvalidated);
						model.addAttribute("invalidatedlbcode", localBodyCode);
						model.addAttribute("lbselectedtype", 1);
						lbdisturbreason = localGovtBodyService.getdisturblbReason(localBodyCode, 'D');
						model.addAttribute("lbdisturbreason", lbdisturbreason);
					} else if (lbtypeinvalidate == 2) {
						lbnameInvalidated = lbdetail.get(0).getLocalBodyNameEnglish();
						parentLocalbody = lbdetail.get(0).getParentLocalBodyNameEnglish();
						parentlbcode = lbdetail.get(0).getParentLocalBodyCode();
						model.addAttribute("invalidatedlb", lbnameInvalidated);
						model.addAttribute("parentlb", parentLocalbody);
						model.addAttribute("invalidatedlbcode", localBodyCode);
						model.addAttribute("parentlbcode", parentlbcode);
						model.addAttribute("lbselectedtype", 2);
						lbdisturbreason = localGovtBodyService.getdisturblbReason(localBodyCode, 'I');
						model.addAttribute("lbdisturbreason", lbdisturbreason);

					} else if (lbtypeinvalidate == 3) {
						lbnameInvalidated = lbdetail.get(0).getLocalBodyNameEnglish();
						parentLocalbody = lbdetail.get(0).getParentLocalBodyNameEnglish();
						parentlbcode = lbdetail.get(0).getParentLocalBodyCode();
						model.addAttribute("invalidatedlb", lbnameInvalidated);
						model.addAttribute("parentlb", parentLocalbody);
						model.addAttribute("invalidatedlbcode", localBodyCode);
						model.addAttribute("parentlbcode", parentlbcode);
						lbdetailnew = localGovtBodyService.getGovtLocalBodyDetails(parentlbcode);
						if (lbdetailnew.size() > 0) {
							grandparentLB = lbdetailnew.get(0).getParentLocalBodyNameEnglish();
							grandparentLBcode = lbdetailnew.get(0).getParentLocalBodyCode();
							model.addAttribute("grandparentLB", grandparentLB);
							model.addAttribute("grandparentLBcode", grandparentLBcode);
						} else {
							model.addAttribute("grandparentLB", "Not Exist");
							model.addAttribute("grandparentLBcode", "1");

						}
						model.addAttribute("lbselectedtype", 3);
						lbdisturbreason = localGovtBodyService.getdisturblbReason(localBodyCode, 'V');
						model.addAttribute("lbdisturbreason", lbdisturbreason);

					}

				} else {
					model.addAttribute("msgid", "Parent Information is not Available Please Check get_lb_details() function for lbcode " + localBodyCode);
					mav = new ModelAndView("success");

				}
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Configuration government order is not defined in the state please defined the setup first !");
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			log.debug("Exception" + e);
			return mav;
		}

		return mav;
	}

	/*
	 * Declare districtPanchayatList,localBodyTypelist variable
	 */
	@RequestMapping(value = "/invalidateTRIDisturbed")
	public ModelAndView invalidateTRIDisturbed(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, HttpSession session, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		//String warningEntiesFlag = request.getParameter("warningEntiesFlag");
		String id = request.getParameter("id");
		int localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
		//List<ParentWiseLocalBodies> lb = null;
		List<LocalBodyDetails> lbdetail = null;
		List<LocalBodyDetails> lbdetailnew = null;
		String lbnameInvalidated = null;
		int lbtypeinvalidate = 0;
		List<LocalbodyforStateWise> localBodyTypelistnew = new ArrayList<LocalbodyforStateWise>();
		List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
		String parentLocalbody = null;
		String grandparentLB = null;
		int parentlbcode = 0;
		String lbdisturbreason = null;
		int grandparentLBcode = 0;
		try {
			char lbType = 'T';
			int operationCode = 58;
			mav = null;
			
			request.setAttribute("stateCode", stateCode);
			
			//int locabodyTypecode;
			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");

			}
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('T', stateCode);
			if (districtCode != 0) {
				districtPanchayatList = localGovtBodyService.getLandRegionByDistricCode(localBodyCode, districtCode, "T");
			} else {
				if (stateCode != 34)
					districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'T');
				else
					districtPanchayatList = localGovtBodyService.getExistingLBListTwoTier(stateCode);
			}
			if (localBodyTypelist.size() > 0) {

				localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
			}
			if (districtPanchayatList.size() > 0) {
				model.addAttribute("districtPanchayatList", districtPanchayatList);
			}

			if (localBodyTypelist.size() > 0) {

				localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
			}
			localGovtBodyForm.setLbType('T');
			localGovtBodyForm.setOperation("ITRI");
			Map<String, String> hMap = new HashMap<String, String>();
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");
			String mapConfig = hMap.get("mapUpload");
			//String message = hMap.get("message");
			if (govtOrderConfig != null && mapConfig != null) {

				mav = new ModelAndView("invalidate_DisturbedTRI");
				mav.addObject("hideMap", mapConfig);
				mav.addObject("govtOrderConfig", govtOrderConfig);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				mav.addObject("flag1", 0);
				if (localBodyTypelist.size() == 2) {
					mav.addObject("Tier", 2);
				} else if (localBodyTypelist.size() > 2) {
					mav.addObject("Tier", 3);
				}

				lbdetail = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
				if (lbdetail.size() > 0) {

					lbtypeinvalidate = lbdetail.get(0).getLocalBodyTypeCode();
					for (LocalbodyforStateWise obj : localBodyTypelist) {
						if (obj.getLocalBodyTypeCode() == lbtypeinvalidate) {
							localBodyTypelistnew.add(obj);
							break;
						}
					}
					model.addAttribute("localBodyTypelist", localBodyTypelistnew);
					if (lbtypeinvalidate == 9 || lbtypeinvalidate == 10 || lbtypeinvalidate == 3) {
						lbnameInvalidated = lbdetail.get(0).getLocalBodyNameEnglish();
						model.addAttribute("invalidatedlb", lbnameInvalidated);
						model.addAttribute("invalidatedlbcode", localBodyCode);
						model.addAttribute("lbselectedtype", 1);
						lbdisturbreason = localGovtBodyService.getdisturblbReason(localBodyCode, 'D');
						model.addAttribute("lbdisturbreason", lbdisturbreason);
					} else if (lbtypeinvalidate == 14 || lbtypeinvalidate == 2 || lbtypeinvalidate == 3) {
						lbnameInvalidated = lbdetail.get(0).getLocalBodyNameEnglish();
						parentLocalbody = lbdetail.get(0).getParentLocalBodyNameEnglish();
						parentlbcode = lbdetail.get(0).getParentLocalBodyCode();
						model.addAttribute("invalidatedlb", lbnameInvalidated);
						model.addAttribute("parentlb", parentLocalbody);
						model.addAttribute("invalidatedlbcode", localBodyCode);
						model.addAttribute("parentlbcode", parentlbcode);
						model.addAttribute("lbselectedtype", 2);
						lbdisturbreason = localGovtBodyService.getdisturblbReason(localBodyCode, 'I');
						model.addAttribute("lbdisturbreason", lbdisturbreason);

					} else if (lbtypeinvalidate == 11 || lbtypeinvalidate == 12 || lbtypeinvalidate == 15 || lbtypeinvalidate == 16) {
						lbnameInvalidated = lbdetail.get(0).getLocalBodyNameEnglish();
						parentLocalbody = lbdetail.get(0).getParentLocalBodyNameEnglish();
						parentlbcode = lbdetail.get(0).getParentLocalBodyCode();
						model.addAttribute("invalidatedlb", lbnameInvalidated);
						model.addAttribute("parentlb", parentLocalbody);
						model.addAttribute("invalidatedlbcode", localBodyCode);
						model.addAttribute("parentlbcode", parentlbcode);
						lbdetailnew = localGovtBodyService.getGovtLocalBodyDetails(parentlbcode);
						if (lbdetailnew.size() > 0) {
							grandparentLB = lbdetailnew.get(0).getParentLocalBodyNameEnglish();
							grandparentLBcode = lbdetailnew.get(0).getParentLocalBodyCode();
							model.addAttribute("grandparentLB", grandparentLB);
							model.addAttribute("grandparentLBcode", grandparentLBcode);
						} else {
							model.addAttribute("grandparentLB", "Not Exist");
							model.addAttribute("grandparentLBcode", "1");

						}
						model.addAttribute("lbselectedtype", 3);
						lbdisturbreason = localGovtBodyService.getdisturblbReason(localBodyCode, 'V');
						model.addAttribute("lbdisturbreason", lbdisturbreason);

					}

				} else {
					model.addAttribute("msgid", "Parent Information is not Available Please Check get_lb_details() function for lbcode " + localBodyCode);
					mav = new ModelAndView("success");

				}

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Configuration government order is not defined in the state please defined the setup first !");
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/*
	 * Declare localBodyTypelist variable
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/invalidateULBDisturbed")
	public ModelAndView invalidateULBDisturbed(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, HttpSession session, Model model, HttpServletRequest request) {
		// List<LocalbodyListbyState> districtPanchayatList2 = new
		// ArrayList<LocalbodyListbyState>();
		ModelAndView mav = null;
		// List<ParentWiseLocalBodies> lb= null;
		List<LocalBodyDetails> lbdetail = null;
		// List<LocalBodyDetails> lbdetailnew = null;
		String lbnameInvalidated = null;
		int lbtypeinvalidate = 0;
		String lbdisturbreason = null;
		List<LocalbodyforStateWise> localBodyTypelistnew = new ArrayList<LocalbodyforStateWise>();
		try {
			char lbType = 'U';
			int operationCode = 59;
			mav = null;
			
			request.setAttribute("stateCode", stateCode);
			// String warningEntiesFlag =
			// request.getParameter("warningEntiesFlag");
			String id = request.getParameter("id");
			int localBodyCode = (localGovtBodyForm.getParentwiseId() == null) ? Integer.parseInt(id) : localGovtBodyForm.getParentwiseId();
			if (stateCode != 0) {

				if (session.getAttribute("formbean") != null) {
					session.removeAttribute("formbean");
					session.removeValue("formbean");
				}
				List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
				if (localBodyTypelist.size() > 0) {

					localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
				}
				localGovtBodyForm.setLbType('U');
				localGovtBodyForm.setOperation("IURB");
				localGovtBodyForm.setUnResolvedFlag(Boolean.TRUE);
				Map<String, String> hMap = new HashMap<String, String>();
				hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
				String govtOrderConfig = hMap.get("govtOrder");
				String mapConfig = hMap.get("mapUpload");
				// String message = hMap.get("message");
				if (govtOrderConfig != null && mapConfig != null) {

					mav = new ModelAndView("invalidate_DisturbedULB");
					mav.addObject("hideMap", mapConfig);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					mav.addObject("flag1", 0);
					model.addAttribute("scode", stateCode);

					lbdetail = localGovtBodyService.getGovtLocalBodyDetails(localBodyCode);
					if (lbdetail.size() > 0) {

						lbtypeinvalidate = lbdetail.get(0).getLocalBodyTypeCode();
						for (LocalbodyforStateWise obj : localBodyTypelist) {
							if (obj.getLocalBodyTypeCode() == lbtypeinvalidate) {
								localBodyTypelistnew.add(obj);
								break;
							}
						}
						model.addAttribute("localBodyTypelist", localBodyTypelistnew);
						lbnameInvalidated = lbdetail.get(0).getLocalBodyNameEnglish();
						model.addAttribute("invalidatedlb", lbnameInvalidated);
						model.addAttribute("invalidatedlbcode", localBodyCode);
						lbdisturbreason = localGovtBodyService.getdisturblbReason(localBodyCode, 'I');
						model.addAttribute("lbdisturbreason", lbdisturbreason);
					}
				} else {
					model.addAttribute("msgid", "Parent Information is not Available Please Check get_lb_details() function for lbcode " + localBodyCode);
					mav = new ModelAndView("success");

				}

			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Configuration government order is not defined in the state please defined the setup first !");
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/updatePesaStatus")
	public ModelAndView preInvalidatePRI(HttpSession session, Model model, HttpServletRequest request) {
		List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
		LocalGovtBodyForm localGovtBodyForm = new LocalGovtBodyForm();
		ModelAndView mav = null;
		try {
			mav = null;
			
			request.setAttribute("stateCode", stateCode);
			
			if (districtCode != 0) {
				districtPanchayatList = localGovtBodyService.getLandRegionByDistricCode(1, districtCode, "P");
			} else {
				if (stateCode != 34) {
					districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
				} else {
					districtPanchayatList = localGovtBodyService.getExistingLBListTwoTier(stateCode);
				}
			}
			model.addAttribute("districtPanchayatList", districtPanchayatList);
			mav = new ModelAndView("update_LBPesa_Status");
			mav.addObject("localGovtBodyForm", localGovtBodyForm);
			model.addAttribute("flag", 0);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/getLBPesaRecords", method = RequestMethod.POST)
	public ModelAndView getLBPesaRecords(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, HttpSession session, Model model, HttpServletRequest request) {
		int parentLbCode = 0;
		List<Localbody> lbList = new ArrayList<Localbody>();
		ModelAndView mav = null;
		List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();
		
		
		try {
			mav = new ModelAndView("update_LBPesa_Status");
			parentLbCode = Integer.parseInt(localGovtBodyForm.getLgd_LBIntermediateAtVillage());
			
			lbList = localGovtBodyService.getChildLbByParentLb(parentLbCode, stateCode);
			if (lbList.size() > 0) {
				model.addAttribute("lbList", lbList);
				model.addAttribute("flag", 1);
			} else {
				if (districtCode != 0) {
					districtPanchayatList = localGovtBodyService.getLandRegionByDistricCode(1, districtCode, "P");
				} else {
					if (stateCode != 34) {
						districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
					} else {
						districtPanchayatList = localGovtBodyService.getExistingLBListTwoTier(stateCode);
					}
				}
				model.addAttribute("districtPanchayatList", districtPanchayatList);
				model.addAttribute("flag", 3);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/saveLbPesaRecords", method = RequestMethod.POST)
	public ModelAndView saveLbPesaRecords(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, HttpSession session, Model model, HttpServletRequest request) {
		String pesaLbId = null;
		ModelAndView mav = null;
		boolean success = true;
		String Message = null;
		
		try {
			pesaLbId = localGovtBodyForm.getChoosenlb();
			success = localGovtBodyService.updateLbPesaStatus(pesaLbId, stateCode, localGovtBodyForm.getListformat());
			if (success)
				Message = "Record Updated Successfully";
			else
				Message = "Records Insertion Failure";
			mav = new ModelAndView("success");
			mav.addObject("msgid", Message);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/* changed by kirandeep on 01/09/2014 */
	/* @RequestMapping(value = "/viewwardforPRI", method = RequestMethod.GET) */
	@SuppressWarnings("deprecation")
	public ModelAndView testingmethod(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, HttpSession session, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
		/** The Intermediate panchayat list. */
		// List<LocalbodyListbyState> intermediatePanchayatList = new
		// ArrayList<LocalbodyListbyState>();
		// List<LocalbodyListbyState> villagePanchayatList = new
		// ArrayList<LocalbodyListbyState>();

		try {
			

			
			localGovtBodyForm.setLgd_lbCategory("P");
			localGovtBodyForm.setLbType('P');

			if (session.getAttribute("formbean") != null) {
				session.removeAttribute("formbean");
				session.removeValue("formbean");
			}

			/*
			 * List<LocalGovtBodyWard> listWardDetails=new
			 * ArrayList<LocalGovtBodyWard>();
			 * listWardDetails=localGovtBodyService
			 * .getlocalGovtBodyWardListforpaginationonCreate
			 * (localGovtBodyForm.getLocalBodyCode());
			 */

			getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'P');

			/*
			 * intermediatePanchayatList = localGovtBodyService
			 * .getExistingLBListbyStateandCategoryInter(stateCode, 'P');
			 * 
			 * villagePanchayatList = localGovtBodyService
			 * .getExistingLBListbyStateandCategoryVillage(stateCode, 'P');
			 */

			if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {

				model.addAttribute("stateCode", stateCode);
				request.setAttribute("localSetUpList", getLocalGovtSetupList);

				localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);

				/*
				 * districtPanchayatList = localGovtBodyService
				 * .getPanchayatListbyStateandCategory(stateCode, 'P');
				 */

				/*
				 * if (districtPanchayatList.size() > 0) {
				 * model.addAttribute("districtPanchayatList",
				 * districtPanchayatList); }
				 */
				/*
				 * if (intermediatePanchayatList.size() > 0) {
				 * model.addAttribute("intermediatePanchayatList",
				 * intermediatePanchayatList); } if (villagePanchayatList.size()
				 * > 0) { model.addAttribute("villagePanchayatList",
				 * villagePanchayatList); }
				 */

				mav = new ModelAndView("manageWard");
				model.addAttribute("lbType", "P");
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				model.addAttribute("stateCode", stateCode);
				model.addAttribute("districtCode", districtCode);
				/* model.addAttribute("listWardDetails",listWardDetails); */
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				model.addAttribute("showTable", false);

			} else {

				mav = new ModelAndView("success");
				mav.addObject("msgid", "Please first Set Up Local Government for this State");

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/* changed by kirandeep on 01/09/2014 */
	@RequestMapping(value = "/managewardnew", method = RequestMethod.POST)
	public ModelAndView managewardnew(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

		return mav;

	}

	@RequestMapping(value = "/publishWard", method = RequestMethod.POST)
	public ModelAndView publishWard(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String localbdytype1 = null;
		int localbdytype = 0;
		int counter = 0;
		session.removeAttribute("test");
		String checkmanage = request.getParameter("manage");
		String tempWardCode = request.getParameter("tempWardCode");
		String isNew = request.getParameter("isNew");
		String isUpdate = request.getParameter("isUpdate");
		String IsDelete = request.getParameter("IsDelete");
		String IsRestore = request.getParameter("IsRestore");
		String istempDelete = request.getParameter("istempDelete");
		List<LocalbodyforStateWise> localBodyTypelist = null;
		
		try {

			// Integer.parseInt(request.getParameter("localbdytyp").toString());
			
			request.setAttribute("stateCode", stateCode);
			

			// /////////////code for drop down
			// validation/////////////////////////////////

			if (localbdytype == 1 && localGovtBodyForm.getLocalBodyNameEnglishList() != null) {
				comboFillingService.getComboValuesforApp('L', "S#1", stateCode, Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()));

				// comboFillingService.getComboValuesforApp('W',
				// null,Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()),null);
			}

			if (localbdytype == 2 && localGovtBodyForm.getLgd_LBDistListAtInterCA() != null && localGovtBodyForm.getLgd_LBInterPSourceList() != null) {

				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtInterCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList()));
			}
			if (localbdytype == 3 && localGovtBodyForm.getLgd_LBDistListAtVillageCA() != null && localGovtBodyForm.getLgd_LBInterListAtVillageCA() != null) {
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()));
				// comboFillingService.getComboValuesforApp('L', "L",
				// Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg()),
				// Integer.parseInt(localGovtBodyForm.getLgd_LBVillageSourceAtVillageCA()));
			}
			// /////////////end code for drop down
			// validation/////////////////////////////////
			localGovtBodyForm.setSlc(stateCode);
			validatorWard.validateviewWard(localGovtBodyForm, result);
			if (result.hasErrors()) {
				mav = new ModelAndView("view_ward");
				localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);
				localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
				model.addAttribute("lbType", localGovtBodyForm.getLgd_lbCategory().charAt(0));
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				localbdytype1 = localGovtBodyForm.getLgd_LBTypeName().substring(0, 1);
				localbdytype = Integer.parseInt(localbdytype1);
				String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
				String strArray[] = localBodyTypeCodeDup.split(":");
				//String typeCode = strArray[0];
				String type = strArray[1];
				String categoryDropDown = strArray[2];
				// Integer lbtype=Integer.parseInt(typeCode);

				int offset = 0;
				int limit = 25;
				//String districtPanchayatCodes = localGovtBodyForm.getLocalBodyNameEnglishList();
				//String intermediatePanchayatCodes = localGovtBodyForm.getLocalBodyNameEnglishListSource();

				localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);
				localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				model.addAttribute("lbType", localGovtBodyForm.getLgd_lbCategory().charAt(0));

				// ////////code for government order
				// checking///////////////////////////////////
				//Map<String, String> hMap = new HashMap<String, String>();
				// Please first check your operation code then set it in place
				// of 10
				//hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10
																									// is
																									// operation
																									// code
				// ///////code for government order
				// checking/////////////////////////////////// // for
				// create
				// new

				Integer lbCode = null;
				if (type.equalsIgnoreCase("D") && localGovtBodyForm.getLocalBodyNameEnglishList() != null && !("").equals(localGovtBodyForm.getLocalBodyNameEnglishList().trim())) {
					lbCode = Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList());
				} else if (type.equalsIgnoreCase("I") && localGovtBodyForm.getLgd_LBInterPSourceList() != null && !("").equals(localGovtBodyForm.getLgd_LBInterPSourceList().trim())) {
					lbCode = Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList().trim());
				} else if (type.equalsIgnoreCase("V") && localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg() != null && !("").equals(localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg().trim())) {
					lbCode = Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg().trim());
				} else if (categoryDropDown.equalsIgnoreCase("U") && localGovtBodyForm.getLocalBodyNameEnglishList() != null && !("").equals(localGovtBodyForm.getLocalBodyNameEnglishList().trim())) {
					lbCode = Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList().trim());
				}

				List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, localGovtBodyForm.getLgd_lbCategory().charAt(0), 'D');
				model.addAttribute("districtPanchayatList", districtPanchayatList);
				List<LocalGovtBodyWard> listWardDetails = null;
				List<LocalbodyWard> listWardDetailsdetails = null;
				List<localbodywardtemp> listWardDetailstemp = null;
				boolean checksave = false;
				if (lbCode != null) {
					if (checkmanage != null && "manage".equalsIgnoreCase(checkmanage)) {
						localbodywardtemp wardforSave = new localbodywardtemp();
						if ("Yes".equalsIgnoreCase(istempDelete) || "Yes".equalsIgnoreCase(istempDelete)) {
							localGovtBodyService.managewarddeletetemp(Integer.parseInt(tempWardCode));
						}

						listWardDetailstemp = localGovtBodyService.getlocalGovtBodyWardListforpaginationtemp(lbCode.intValue(), offset, 100);
						if ("Yes".equalsIgnoreCase(isNew)) {
							for (localbodywardtemp wardtemp : listWardDetailstemp) {

								if (Integer.parseInt(tempWardCode) == wardtemp.getTemp_ward_code()) {
									wardforSave = wardtemp;
									checksave = localGovtBodyService.createwardformanage(wardtemp);
									if (checksave) {
										localGovtBodyService.managewarddeletetemp(Integer.parseInt(tempWardCode));
									}

								}

							}

							if (checksave) {
								listWardDetailstemp.remove(wardforSave);
								model.addAttribute("msgid", "Ward added Successfully");
							} else {
								model.addAttribute("msgid", "Ward  alerady exist Kindly update or delte the Ward First");
							}
						}

						if ("Yes".equalsIgnoreCase(isUpdate) || "Yes".equalsIgnoreCase(IsDelete)) {
							for (localbodywardtemp wardtemp : listWardDetailstemp) {

								if (Integer.parseInt(tempWardCode) == wardtemp.getTemp_ward_code()) {
									wardforSave = wardtemp;
									boolean checksaveup = localGovtBodyService.createwardformanageUpdate(wardtemp, isUpdate);
									if (checksaveup) {
										localGovtBodyService.managewarddeletetemp(Integer.parseInt(tempWardCode));
									}

								}
							}
							listWardDetailstemp.remove(wardforSave);
							if ("Yes".equalsIgnoreCase(isUpdate)) {
								model.addAttribute("msgid", "Ward Updated Successfully");
							} else
								model.addAttribute("msgid", "Ward Deleted Successfully");
						}

						if ("Yes".equalsIgnoreCase(IsRestore)) {
							//Integer checkRestore = 
							localGovtBodyService.restoreWard(Integer.parseInt(tempWardCode));
						}

					}
					listWardDetailsdetails = localGovtBodyService.getlocalGovtBodyWardListforpaginationtempdelete(lbCode.intValue(), offset, 100);
					listWardDetails = localGovtBodyService.getlocalGovtBodyWardListforpagination(lbCode.intValue(), offset, 100);
					/*
					 * if(listWardDetails!=null && !listWardDetails.isEmpty()) {
					 */
					counter = localGovtBodyService.countwardDetails(lbCode.intValue());
					localGovtBodyForm.setLimit(limit);
					localGovtBodyForm.setOffset(offset);
					session.setAttribute("limit", localGovtBodyForm.getLimit());
					boolean movenext = true;
					session.setAttribute("offset", localGovtBodyForm.getOffset());
					// session.setAttribute("lbtype", lbtype);
					session.setAttribute("lbtype", categoryDropDown);
					session.setAttribute("lbCode", lbCode);
					session.setAttribute("counter", counter);

					if (listWardDetails != null && !listWardDetails.isEmpty()) {
						model.addAttribute("wardsize", listWardDetails.size());
					}

					model.addAttribute("wardList", listWardDetails);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
					model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
					model.addAttribute("counter", counter);
					Integer movePointer = counter / limit;
					movePointer = (counter % limit == 0) ? movePointer : movePointer + 1;
					if (offset == (movePointer - 1)) {
						movenext = false;
					} else {
						movenext = true;
					}

					model.addAttribute("movenext", movenext);
					model.addAttribute("wardCount", "Page " + (offset + 1) + " of " + (movePointer));
					model.addAttribute("wardList", listWardDetails);
					if (session.getAttribute("wardlist") != null) {
						session.setAttribute("wardlist", null);
					}
					if (session.getAttribute("wardlist") == null) {
						session.setAttribute("wardlist", listWardDetails);
					}
					if (checkmanage != null && "manage".equalsIgnoreCase(checkmanage)) {
						mav = new ModelAndView("manageWard");
					} else
						mav = new ModelAndView("createPRIWard");
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					model.addAttribute("lbtype", categoryDropDown.charAt(0));
					model.addAttribute("viewPage", "abc");
					model.addAttribute("listWardDetailstemp", listWardDetailstemp);
					model.addAttribute("listWardDetailsdetails", listWardDetailsdetails);
					model.addAttribute("showTable", true);

					/* } *//*
							 * else{ model.addAttribute("listnull",
							 * "Ward not present"); mav = new
							 * ModelAndView("createPRIWard"); String errorMsg =
							 * "Ward List is not available";
							 * mav.addObject("message", errorMsg);
							 * model.addAttribute("showTable",true); }
							 */
				}

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;
	}

	@RequestMapping(value = "/publishWardUrban", method = RequestMethod.POST)
	public ModelAndView viewUrbanWardforManage(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {

		/*
		 * String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
		 * String strArray[] = localBodyTypeCodeDup.split(":"); String typeCode
		 * = strArray[0]; String type = strArray[1];
		 */
		ModelAndView mav = null;
		try {
			/* Modified changed by kirandeep on 05/08/2014 */
			localGovtBodyForm.setLgd_lbCategory("U");
			String localbdytype1 = localGovtBodyForm.getLgd_LBTypeName().substring(0, 1);
			int localbdytype = Integer.parseInt(localbdytype1);
			// Integer.parseInt(request.getParameter("localbdytyp").toString());
			

			String checkmanage = request.getParameter("manage");
			String tempWardCode = request.getParameter("tempWardCode");
			String istempDelete = request.getParameter("istempDelete");
			String isNew = request.getParameter("isNew");
			String isUpdate = request.getParameter("isUpdate");
			String IsDelete = request.getParameter("IsDelete");
			String IsRestore = request.getParameter("IsRestore");

			int offset = 0;
			@SuppressWarnings("unused")
			int limit = 25;

			// /////////////code for drop down
			// validation/////////////////////////////////

			if (localbdytype == 1) {
				comboFillingService.getComboValuesforApp('L', "S#1", stateCode, Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()));
				// comboFillingService.getComboValuesforApp('W',
				// null,Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()),null);
			}

			if (localbdytype == 2) {
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtInterCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList()));
			}
			if (localbdytype == 3) {
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()));
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBVillageSourceAtVillageCA()));
			}
			// /////////////end code for drop down
			// validation/////////////////////////////////
			List<LocalGovtBodyWard> listWardDetails = new ArrayList<LocalGovtBodyWard>();
			List<LocalbodyWard> listWardDetailsdetails = null;
			List<localbodywardtemp> listWardDetailstemp = null;
			boolean checksave = false;

			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);
			localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
			model.addAttribute("localBodyTypelist", localBodyTypelist);

			if (localGovtBodyForm.getLb_lgdPanchayatName() != null) {
				// ALERT State Code Hard Code for testing
				if (checkmanage != null && "manage".equalsIgnoreCase(checkmanage)) {
					localbodywardtemp wardforSave = new localbodywardtemp();
					if ("Yes".equalsIgnoreCase(istempDelete) || "Yes".equalsIgnoreCase(istempDelete)) {
						localGovtBodyService.managewarddeletetemp(Integer.parseInt(tempWardCode));
					}

					listWardDetailstemp = localGovtBodyService.getlocalGovtBodyWardListforpaginationtemp(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()), offset, 100);
					if ("Yes".equalsIgnoreCase(isNew)) {
						for (localbodywardtemp wardtemp : listWardDetailstemp) {

							if (Integer.parseInt(tempWardCode) == wardtemp.getTemp_ward_code()) {
								wardforSave = wardtemp;
								checksave = localGovtBodyService.createwardformanage(wardtemp);
								if (checksave) {
									localGovtBodyService.managewarddeletetemp(Integer.parseInt(tempWardCode));
								}

							}

						}

						if (checksave) {
							listWardDetailstemp.remove(wardforSave);
							model.addAttribute("msgid", "Ward added Successfully");
						} else {
							model.addAttribute("msgid", "Ward  alerady exist Kindly update or delte the Ward First");
						}
					}

					if ("Yes".equalsIgnoreCase(isUpdate) || "Yes".equalsIgnoreCase(IsDelete)) {
						for (localbodywardtemp wardtemp : listWardDetailstemp) {

							if (Integer.parseInt(tempWardCode) == wardtemp.getTemp_ward_code()) {
								wardforSave = wardtemp;
								boolean checksaveup = localGovtBodyService.createwardformanageUpdate(wardtemp, isUpdate);
								if (checksaveup) {
									localGovtBodyService.managewarddeletetemp(Integer.parseInt(tempWardCode));
								}

							}
						}
						listWardDetailstemp.remove(wardforSave);
						if ("Yes".equalsIgnoreCase(isUpdate)) {
							model.addAttribute("msgid", "Ward Updated Successfully");
						} else
							model.addAttribute("msgid", "Ward Deleted Successfully");
					}

					if ("Yes".equalsIgnoreCase(IsRestore)) {
						//Integer checkRestore = 
						localGovtBodyService.restoreWard(Integer.parseInt(tempWardCode));
					}

					listWardDetails = new ArrayList<LocalGovtBodyWard>();
					listWardDetails = localGovtBodyService.getlocalGovtBodyWardList(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()));
					listWardDetailsdetails = localGovtBodyService.getlocalGovtBodyWardListforpaginationtempdelete(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()), offset, 100);

				}
			}
			model.addAttribute("wardList", listWardDetails);
			if (session.getAttribute("wardlist") != null) {
				session.setAttribute("wardlist", null);
			}
			if (session.getAttribute("wardlistforurban") == null) {
				session.setAttribute("wardlistforurban", listWardDetails);
			}
			localGovtBodyForm.setLocalGovtBodyWardList(listWardDetails);
			if (listWardDetails.size() > 0) {
				mav = new ModelAndView("manageWardUrban");
				model.addAttribute("viewPage", "abc");
				model.addAttribute("listWardDetailsdetails", listWardDetailsdetails);
				model.addAttribute("listWardDetailstemp", listWardDetailstemp);
				model.addAttribute("showTable", true);
			} else {
				mav = new ModelAndView("manageWardUrban");
				model.addAttribute("showTable", false);
				String errorMsg = "Ward List is not available";
				model.addAttribute("message", errorMsg);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	//@RequestMapping(value = "/viewwardforUrban", method = RequestMethod.GET)
	public ModelAndView viewWardForUrbanGot(HttpSession session, @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request) {
		ModelAndView mv = null;
		try {
			
			// String localbodyCategory = "U";
			localGovtBodyForm.setLgd_lbCategory("U");
			if (stateCode != null) {
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'U');

				if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {
					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList", getLocalGovtSetupList);

					List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
						model.addAttribute("localBodyTypelist", localBodyTypelist);
					}
					mv = new ModelAndView("manageWardUrban");
					// model.addAttribute("localBodyTypelist",
					// localBodyTypelist);
					mv.addObject("localGovtBodyForm", localGovtBodyForm);
					return mv;
				} else {
					mv = new ModelAndView("success");
					mv.addObject("msgid", "Please first Set Up Local Government for this State");
				}
			} else {
				mv = new ModelAndView("success");
				mv.addObject("msgid", "Please first Set Up Local Government for this State");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// @RequestMapping(value = "/viewwardforTraditional", method =
	// RequestMethod.GET)
	public ModelAndView viewWardForTraditionalmanage(HttpSession session, @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
		List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
		/** The Intermediate panchayat list. */
		@SuppressWarnings("unused")
		List<LocalbodyListbyState> intermediatePanchayatList = new ArrayList<LocalbodyListbyState>();

		try {
			
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
			
			localGovtBodyForm.setLgd_lbCategory("T");
			getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'T');

			if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {

				model.addAttribute("stateCode", stateCode);
				request.setAttribute("localSetUpList", getLocalGovtSetupList);

				localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('T', stateCode);
				/*
				 * if (localBodyTypelist.size() > 0) {
				 * 
				 * localGovtBodyForm = setLBtype(localBodyTypelist,
				 * localGovtBodyForm); }
				 */
				/*
				 * districtPanchayatList = localGovtBodyService
				 * .getPanchayatListbyStateandCategory(stateCode, 'T');
				 * 
				 * intermediatePanchayatList = localGovtBodyService
				 * .getExistingLBListbyStateandCategoryInter(stateCode, 'T');
				 */

				if (districtPanchayatList.size() > 0) {
					model.addAttribute("districtPanchayatList", districtPanchayatList);
				}

				localGovtBodyForm.setLbType('T');
				mav = new ModelAndView("manageWard");
				model.addAttribute("stateCode", stateCode);
				model.addAttribute("lbType", "T");
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Please first Set Up Local Government for this State");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;

	}

	/* Added by kirandeep on 05/08/2014 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean checkDuplicate(ArrayList list) {
		HashSet set = new HashSet();
		for (int i = 0; i < list.size(); i++) {
			boolean val = set.add(list.get(i));
			if (val == false) {
				return val;
			}
		}
		return true;
	}



	// @RequestMapping(value = "/publishWardAll", method = RequestMethod.POST)
	public ModelAndView publishWardAll(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String localbdytype1 = null;
		int localbdytype = 0;
		int counter = 0;
		session.removeAttribute("test");
		String checkmanage = request.getParameter("manage");
		/* String tempWardCode=request.getParameter("tempWardCode"); */
		/* String isNew=request.getParameter("isNew"); */
		/* String isUpdate=request.getParameter("isUpdate"); */
		String publishWardsList = request.getParameter("publishWardsList");
		/* String publishAll=request.getParameter("publishAllWard"); */
		String deleteAllWardTemp = request.getParameter("deleteAllWardTemp");
		/* String IsDelete=request.getParameter("IsDelete"); */
		/*
		 * String IsRestore=request.getParameter("IsRestore"); String
		 * istempDelete=request.getParameter("istempDelete");
		 */
		List<LocalbodyforStateWise> localBodyTypelist = null;
		
		try {

			// Integer.parseInt(request.getParameter("localbdytyp").toString());
			
			request.setAttribute("stateCode", stateCode);
			
			String[] wardPublishedList;
			ArrayList<Integer> WardList = null;

			if (publishWardsList != null && !"".equalsIgnoreCase(publishWardsList)) {
				wardPublishedList = publishWardsList.split(",");
				WardList = new ArrayList<Integer>();
				for (String Ward : wardPublishedList) {
					WardList.add(Integer.parseInt(Ward));

				}
			}

			// /////////////code for drop down
			// validation/////////////////////////////////

			if (localbdytype == 1 && localGovtBodyForm.getLocalBodyNameEnglishList() != null) {
				comboFillingService.getComboValuesforApp('L', "S#1", stateCode, Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()));

				// comboFillingService.getComboValuesforApp('W',
				// null,Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()),null);
			}

			if (localbdytype == 2 && localGovtBodyForm.getLgd_LBDistListAtInterCA() != null && localGovtBodyForm.getLgd_LBInterPSourceList() != null) {

				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtInterCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList()));
			}
			if (localbdytype == 3 && localGovtBodyForm.getLgd_LBDistListAtVillageCA() != null && localGovtBodyForm.getLgd_LBInterListAtVillageCA() != null) {
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()));
				// comboFillingService.getComboValuesforApp('L', "L",
				// Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg()),
				// Integer.parseInt(localGovtBodyForm.getLgd_LBVillageSourceAtVillageCA()));
			}
			// /////////////end code for drop down
			// validation/////////////////////////////////
			localGovtBodyForm.setSlc(stateCode);
			validatorWard.validateviewWard(localGovtBodyForm, result);
			if (result.hasErrors()) {
				mav = new ModelAndView("view_ward");
				localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);
				localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
				model.addAttribute("lbType", localGovtBodyForm.getLgd_lbCategory().charAt(0));
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				localbdytype1 = localGovtBodyForm.getLgd_LBTypeName().substring(0, 1);
				localbdytype = Integer.parseInt(localbdytype1);
				String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
				String strArray[] = localBodyTypeCodeDup.split(":");
				//String typeCode = strArray[0];
				String type = strArray[1];
				String categoryDropDown = strArray[2];
				// Integer lbtype=Integer.parseInt(typeCode);

				int offset = 0;
				int limit = 25;
				//String districtPanchayatCodes = localGovtBodyForm.getLocalBodyNameEnglishList();
				//String intermediatePanchayatCodes = localGovtBodyForm.getLocalBodyNameEnglishListSource();

				localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);
				localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				model.addAttribute("lbType", localGovtBodyForm.getLgd_lbCategory().charAt(0));

				// ////////code for government order
				// checking///////////////////////////////////
				//Map<String, String> hMap = new HashMap<String, String>();
				// Please first check your operation code then set it in place
				// of 10
				//hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10
																									// is
																									// operation
																									// code
				// ///////code for government order
				// checking/////////////////////////////////// // for
				// create
				// new

				Integer lbCode = null;
				if (type.equalsIgnoreCase("D") && localGovtBodyForm.getLocalBodyNameEnglishList() != null && !("").equals(localGovtBodyForm.getLocalBodyNameEnglishList().trim())) {
					lbCode = Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList());
				} else if (type.equalsIgnoreCase("I") && localGovtBodyForm.getLgd_LBInterPSourceList() != null && !("").equals(localGovtBodyForm.getLgd_LBInterPSourceList().trim())) {
					lbCode = Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList().trim());
				} else if (type.equalsIgnoreCase("V") && localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg() != null && !("").equals(localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg().trim())) {
					lbCode = Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg().trim());
				} else if (categoryDropDown.equalsIgnoreCase("U") && localGovtBodyForm.getLocalBodyNameEnglishList() != null && !("").equals(localGovtBodyForm.getLocalBodyNameEnglishList().trim())) {
					lbCode = Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList().trim());
				}

				List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, localGovtBodyForm.getLgd_lbCategory().charAt(0), 'D');
				model.addAttribute("districtPanchayatList", districtPanchayatList);
				List<LocalGovtBodyWard> listWardDetails = null;
				List<LocalbodyWard> listWardDetailsdetails = null;
				List<localbodywardtemp> listWardDetailstemp = null;
				boolean checksave = false;
				if (lbCode != null) {
					if (checkmanage != null && "manage".equalsIgnoreCase(checkmanage)) {

						listWardDetailstemp = localGovtBodyService.getlocalGovtBodyWardListforpaginationtemp(lbCode.intValue(), offset, 100);
						if (WardList != null && "Yes".equalsIgnoreCase(deleteAllWardTemp)) {
							for (Integer tempWardCodeForDelete : WardList) {
								localGovtBodyService.managewarddeletetemp(tempWardCodeForDelete);
							}
						}

						if (WardList != null && !"Yes".equalsIgnoreCase(deleteAllWardTemp)) {
							for (localbodywardtemp localbodywardtemp : listWardDetailstemp) {
								if (WardList.contains(localbodywardtemp.getTemp_ward_code())) {
									if (localbodywardtemp.isIsnew()) {
										checksave = localGovtBodyService.createwardformanage(localbodywardtemp);
										if (checksave) {
											localGovtBodyService.managewarddeletetemp(localbodywardtemp.getTemp_ward_code());
										}
									}
									if (localbodywardtemp.isIsupdate()) {
										boolean checksaveup = localGovtBodyService.createwardformanageUpdate(localbodywardtemp, "Yes");
										if (checksaveup) {
											localGovtBodyService.managewarddeletetemp(localbodywardtemp.getTemp_ward_code());
										}
									}
									if (localbodywardtemp.isIsdelete()) {
										boolean checksaveup = localGovtBodyService.createwardformanageUpdate(localbodywardtemp, "NO");
										if (checksaveup) {
											localGovtBodyService.managewarddeletetemp(localbodywardtemp.getTemp_ward_code());
										}
									}
								}
							}
						}

						listWardDetailstemp = localGovtBodyService.getlocalGovtBodyWardListforpaginationtemp(lbCode.intValue(), offset, 100);

					}
					listWardDetailsdetails = localGovtBodyService.getlocalGovtBodyWardListforpaginationtempdelete(lbCode.intValue(), offset, 100);
					listWardDetails = localGovtBodyService.getlocalGovtBodyWardListforpagination(lbCode.intValue(), offset, 100);
					/*
					 * if(listWardDetails!=null && !listWardDetails.isEmpty()) {
					 */
					counter = localGovtBodyService.countwardDetails(lbCode.intValue());
					localGovtBodyForm.setLimit(limit);
					localGovtBodyForm.setOffset(offset);
					session.setAttribute("limit", localGovtBodyForm.getLimit());
					boolean movenext = true;
					session.setAttribute("offset", localGovtBodyForm.getOffset());
					// session.setAttribute("lbtype", lbtype);
					session.setAttribute("lbtype", categoryDropDown);
					session.setAttribute("lbCode", lbCode);
					session.setAttribute("counter", counter);

					if (listWardDetails != null && !listWardDetails.isEmpty()) {
						model.addAttribute("wardsize", listWardDetails.size());
					}

					model.addAttribute("wardList", listWardDetails);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
					model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
					model.addAttribute("counter", counter);
					Integer movePointer = counter / limit;
					movePointer = (counter % limit == 0) ? movePointer : movePointer + 1;
					if (offset == (movePointer - 1)) {
						movenext = false;
					} else {
						movenext = true;
					}

					model.addAttribute("movenext", movenext);
					model.addAttribute("wardCount", "Page " + (offset + 1) + " of " + (movePointer));
					model.addAttribute("wardList", listWardDetails);
					if (session.getAttribute("wardlist") != null) {
						session.setAttribute("wardlist", null);
					}
					if (session.getAttribute("wardlist") == null) {
						session.setAttribute("wardlist", listWardDetails);
					}
					if (checkmanage != null && "manage".equalsIgnoreCase(checkmanage)) {
						mav = new ModelAndView("manageWard");
					} else
						mav = new ModelAndView("createPRIWard");
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					model.addAttribute("lbtype", categoryDropDown.charAt(0));
					model.addAttribute("viewPage", "abc");
					model.addAttribute("listWardDetailstemp", listWardDetailstemp);
					model.addAttribute("listWardDetailsdetails", listWardDetailsdetails);
					model.addAttribute("showTable", true);

					/* } *//*
							 * else{ model.addAttribute("listnull",
							 * "Ward not present"); mav = new
							 * ModelAndView("createPRIWard"); String errorMsg =
							 * "Ward List is not available";
							 * mav.addObject("message", errorMsg);
							 * model.addAttribute("showTable",true); }
							 */
				}

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;

	}

	@RequestMapping(value = "/publishWardUrbanAll", method = RequestMethod.POST)
	public ModelAndView viewUrbanWardforManageUrban(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {

		/*
		 * String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
		 * String strArray[] = localBodyTypeCodeDup.split(":"); String typeCode
		 * = strArray[0]; String type = strArray[1];
		 */
		ModelAndView mav = null;
		try {
			/* Modified changed by kirandeep on 05/08/2014 */
			localGovtBodyForm.setLgd_lbCategory("U");
			String localbdytype1 = localGovtBodyForm.getLgd_LBTypeName().substring(0, 1);
			int localbdytype = Integer.parseInt(localbdytype1);
			// Integer.parseInt(request.getParameter("localbdytyp").toString());
			

			String checkmanage = request.getParameter("manage");
			/* String tempWardCode=request.getParameter("tempWardCode"); */
			/* String istempDelete=request.getParameter("istempDelete"); */
			/* String isNew=request.getParameter("isNew"); */
			/* String isUpdate=request.getParameter("isUpdate"); */
			/* String IsDelete=request.getParameter("IsDelete"); */
			/* String IsRestore=request.getParameter("IsRestore"); */
			String publishWardsList = request.getParameter("publishWardsList");
			/* String publishAll=request.getParameter("publishAllWard"); */
			String deleteAllWardTemp = request.getParameter("deleteAllWardTemp");

			String[] wardPublishedList;
			ArrayList<Integer> WardList = null;

			if (publishWardsList != null && !"".equalsIgnoreCase(publishWardsList)) {
				wardPublishedList = publishWardsList.split(",");
				WardList = new ArrayList<Integer>();
				for (String Ward : wardPublishedList) {
					WardList.add(Integer.parseInt(Ward));

				}
			}

			int offset = 0;
			//@SuppressWarnings("unused")
			//int limit = 25;

			// /////////////code for drop down
			// validation/////////////////////////////////

			if (localbdytype == 1) {
				comboFillingService.getComboValuesforApp('L', "S#1", stateCode, Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()));
				// comboFillingService.getComboValuesforApp('W',
				// null,Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()),null);
			}

			if (localbdytype == 2) {
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtInterCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList()));
			}
			if (localbdytype == 3) {
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()));
				comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBVillageSourceAtVillageCA()));
			}
			// /////////////end code for drop down
			// validation/////////////////////////////////
			List<LocalGovtBodyWard> listWardDetails = new ArrayList<LocalGovtBodyWard>();
			List<LocalbodyWard> listWardDetailsdetails = null;
			List<localbodywardtemp> listWardDetailstemp = null;
			boolean checksave = false;

			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);
			localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
			model.addAttribute("localBodyTypelist", localBodyTypelist);

			if (localGovtBodyForm.getLb_lgdPanchayatName() != null) {
				// ALERT State Code Hard Code for testing
				if (checkmanage != null && "manage".equalsIgnoreCase(checkmanage)) {
					listWardDetailstemp = localGovtBodyService.getlocalGovtBodyWardListforpaginationtemp(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()), offset, 100);

					if (WardList != null && "Yes".equalsIgnoreCase(deleteAllWardTemp)) {
						for (Integer tempWardCodeForDelete : WardList) {
							localGovtBodyService.managewarddeletetemp(tempWardCodeForDelete);
						}
					}

					if (WardList != null && !"Yes".equalsIgnoreCase(deleteAllWardTemp)) {
						for (localbodywardtemp localbodywardtemp : listWardDetailstemp) {
							if (WardList.contains(localbodywardtemp.getTemp_ward_code())) {
								if (localbodywardtemp.isIsnew()) {
									checksave = localGovtBodyService.createwardformanage(localbodywardtemp);
									if (checksave) {
										localGovtBodyService.managewarddeletetemp(localbodywardtemp.getTemp_ward_code());
									}
								}
								if (localbodywardtemp.isIsupdate()) {
									boolean checksaveup = localGovtBodyService.createwardformanageUpdate(localbodywardtemp, "Yes");
									if (checksaveup) {
										localGovtBodyService.managewarddeletetemp(localbodywardtemp.getTemp_ward_code());
									}
								}
								if (localbodywardtemp.isIsdelete()) {
									boolean checksaveup = localGovtBodyService.createwardformanageUpdate(localbodywardtemp, "NO");
									if (checksaveup) {
										localGovtBodyService.managewarddeletetemp(localbodywardtemp.getTemp_ward_code());
									}
								}
							}
						}
					}

					listWardDetails = new ArrayList<LocalGovtBodyWard>();
					listWardDetails = localGovtBodyService.getlocalGovtBodyWardList(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()));
					listWardDetailsdetails = localGovtBodyService.getlocalGovtBodyWardListforpaginationtempdelete(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()), offset, 100);
					listWardDetailstemp = localGovtBodyService.getlocalGovtBodyWardListforpaginationtemp(Integer.parseInt(localGovtBodyForm.getLb_lgdPanchayatName()), offset, 100);

				}
			}
			model.addAttribute("wardList", listWardDetails);
			if (session.getAttribute("wardlist") != null) {
				session.setAttribute("wardlist", null);
			}
			if (session.getAttribute("wardlistforurban") == null) {
				session.setAttribute("wardlistforurban", listWardDetails);
			}
			localGovtBodyForm.setLocalGovtBodyWardList(listWardDetails);
			if (listWardDetails.size() > 0) {
				mav = new ModelAndView("manageWardUrban");
				model.addAttribute("viewPage", "abc");
				model.addAttribute("listWardDetailsdetails", listWardDetailsdetails);
				model.addAttribute("listWardDetailstemp", listWardDetailstemp);
				model.addAttribute("showTable", true);
				model.addAttribute("localGovtBodyForm", localGovtBodyForm);
			} else {
				mav = new ModelAndView("manageWardUrban");
				model.addAttribute("showTable", false);
				String errorMsg = "Ward List is not available";
				model.addAttribute("message", errorMsg);
				model.addAttribute("localGovtBodyForm", localGovtBodyForm);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/*@RequestMapping(value="/lgdPfmsMapping",method=RequestMethod.GET)
	public  ModelAndView lgdPfmsMappingGet(@ModelAttribute("LGD_PFMS_MAPPING_FORM") LgdPfmsMappingForm form,Model model, HttpServletRequest request, HttpSession httpSession)
	{
		ModelAndView mav = null;
		try{
			init(httpSession);
			model.addAttribute("userid", userId);
			List<LgdPfmsMapping> lgdPfmsMappinglList=new ArrayList<>();
			lgdPfmsMappinglList=localGovtBodyService.fetchLgdPfmsMapping();
			model.addAttribute("LIST_OF_LGD_PFMS_MAPPING", lgdPfmsMappinglList);
			mav = new ModelAndView("lgdPfmsMapping");
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		
		return mav;
	}*/
	
	@RequestMapping(value="/lgdPfmsMapping",method=RequestMethod.GET)
	public ModelAndView lgdPfmsMappingGet(@ModelAttribute("LGD_PFMS_MAPPING_FORM") LgdPfmsMappingForm form,Model model, HttpServletRequest request, HttpSession httpSession)
	{
		ModelAndView mav = null;
		try{
			init(httpSession);
			Integer levelCode=0;
			model.addAttribute("userid", userId);
			List<LgdPfmsMapping> lgdPfmsMappinglList=null;
			if(districtCode>0)
			{
				lgdPfmsMappinglList=localGovtBodyService.fetchLgdPfmsMapping(districtCode);
				levelCode=2;
			}
			else {
				model.addAttribute("LIST_OF_DISTRICT", districtService.getDistrictList(stateCode));
				model.addAttribute("regionName", "state");
				levelCode=1;
			}
			model.addAttribute("LIST_OF_LGD_PFMS_MAPPING", lgdPfmsMappinglList);
			model.addAttribute("levelCode", levelCode);
			mav = new ModelAndView("lgdPfmsMapping");
		}
		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		
		return mav;
	}
	
}
