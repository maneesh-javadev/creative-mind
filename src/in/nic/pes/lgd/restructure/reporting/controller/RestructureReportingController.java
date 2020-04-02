package in.nic.pes.lgd.restructure.reporting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.rules.LocalBodyUtil;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.common.CaptchaValidator;
import in.nic.pes.lgd.forms.StateDataForm;
import in.nic.pes.lgd.restructure.reporting.entities.GenericReportingEntity;
import in.nic.pes.lgd.restructure.reporting.service.ViewEntityDetailsService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.StateService;

@Controller
public class RestructureReportingController {

	private static final Logger log = Logger.getLogger(RestructureReportingController.class);
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private LocalBodyUtil localBodyUtil;
	
	@Autowired
	private LocalGovtBodyService localGovtBodyService;
	
	@Autowired
	private ViewEntityDetailsService viewEntityService;
	
	private static final String ENITTY_TYPE_DISTRICT = "D"; //  for subdistrict 
	
	private static final String ENITTY_TYPE_DISTRICT_FOR_BLOCK = "d"; //  for Block 
	
	private static final String ENITTY_TYPE_SUBDISTRICT = "T";
	
	private static final String ENITTY_TYPE_VILLAGE = "V";
	
	private static final String ENITTY_TYPE_BLOCK = "B";
	
	
	/*
	 * State Level Report
	 */
	
	@RequestMapping(value = "/globalviewstateforcitizen", method = RequestMethod.GET)
	public ModelAndView showStatePageglobal(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("globalview_state_report");
		try {
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("showSearchHierarchy", Boolean.TRUE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value = "/globalViewStateRepport", method = RequestMethod.POST)
	public ModelAndView showGlobalStatePage(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, 
											Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView("globalview_state_report");
		try {
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);                                        
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showSearchHierarchy", Boolean.TRUE);
				return mav;
			} 

			List<GenericReportingEntity> stateEntityDetails = viewEntityService.getViewStateDetails();
			
			model.addAttribute("SEARCH_STATE_RESULTS_KEY", stateEntityDetails);
			model.addAttribute("showSearchHierarchy", Boolean.FALSE);
		} catch (Exception e) {
			log.error("view details controller: ", e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav.setViewName(redirectPath);
			return mav;
		}
		return mav;
	}

	
	/*
	 * District Level Reports
	 */
	
	@RequestMapping(value = "/globalviewdistrictforcitizen", method = RequestMethod.GET)
	public ModelAndView showDistrictPageglobal(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("globalview_district_report");
		try {
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			model.addAttribute("showSearchHierarchy", Boolean.TRUE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value = "/globalviewdistrict", method = RequestMethod.POST)
	public ModelAndView showglobalDistrictPage(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, 
											   Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView("globalview_district_report");
		try {
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			model.addAttribute("radioButton", genericReportingEntity.getSearchCriteriaType());

			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showSearchHierarchy", Boolean.TRUE);
				return mav;
			} 

			List<GenericReportingEntity> stateWiseEntityDetails = viewEntityService.getViewEntityWiseDetails(
														  		  genericReportingEntity.getParamStateCode(),
														  		  ENITTY_TYPE_DISTRICT,
														  		  genericReportingEntity.getParamEntityCode(),
														  		  genericReportingEntity.getParamEntityName());
			
			model.addAttribute("SEARCH_DISTRICT_RESULTS_KEY", stateWiseEntityDetails);
			model.addAttribute("showSearchHierarchy", Boolean.FALSE);
			model.addAttribute("selectedState",genericReportingEntity.getParamStateCode());
		} catch (Exception e) {
			log.error("view details controller: ", e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav.setViewName(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	/*
	 * Sub-district Level Reports.
	 */
	
	@RequestMapping(value = "/globalviewsubdistrictforcitizen", method = RequestMethod.GET)
	public ModelAndView showSubdistrictPageglobal(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("globalview_subdistrict_report");
		try {
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			model.addAttribute("showSearchHierarchy", Boolean.TRUE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value = "/globalviewsubdistrict", method = RequestMethod.POST)
	public ModelAndView showglobalSubDistrictPage(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, 
											   	  Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView("globalview_subdistrict_report");
		try {
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			model.addAttribute("radioButton", genericReportingEntity.getSearchCriteriaType());

			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showSearchHierarchy", Boolean.TRUE);
				return mav;
			} 

			List<GenericReportingEntity> stateWiseEntityDetails = null;
			if("N".equalsIgnoreCase(genericReportingEntity.getSearchCriteriaType())){
				stateWiseEntityDetails = viewEntityService.getViewEntityWiseDetails(
				  		  genericReportingEntity.getParamStateCode(),
				  		  ENITTY_TYPE_SUBDISTRICT,
				  		  genericReportingEntity.getParamEntityCode(),
				  		  genericReportingEntity.getParamEntityName());

			} else if("P".equalsIgnoreCase(genericReportingEntity.getSearchCriteriaType())){
				stateWiseEntityDetails = viewEntityService.getViewParentWiseDetails(ENITTY_TYPE_DISTRICT, genericReportingEntity.getParamStateCode(),genericReportingEntity.getParamDistrictCode(),genericReportingEntity.getParamSubDistrictCode());
			}
			
			model.addAttribute("SEARCH_SUB_DISTRICT_RESULTS_KEY", stateWiseEntityDetails);
			model.addAttribute("showSearchHierarchy", Boolean.FALSE);
		} catch (Exception e) {
			log.error("view details controller: ", e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav.setViewName(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	
	
	/*
	 * Village Level Reports.
	 */
	
	@RequestMapping(value = "/globalviewvillageforcitizen", method = RequestMethod.GET)
	public ModelAndView showVillagePageglobal(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("globalview_village_report");
		try {
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			model.addAttribute("showSearchHierarchy", Boolean.TRUE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value = "/globalviewvillage", method = RequestMethod.POST)
	public ModelAndView showglobalVillagePage(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, 
											  Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView("globalview_village_report");
		List<Object[]> villagetypestatus=null;
		try {
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
 			model.addAttribute("radioButton", genericReportingEntity.getSearchCriteriaType());

			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showSearchHierarchy", Boolean.TRUE);
				return mav;
			} 

			List<GenericReportingEntity> stateWiseEntityDetails = null;
			if("N".equalsIgnoreCase(genericReportingEntity.getSearchCriteriaType())){
				villagetypestatus =viewEntityService.getVillageStatusWiseDetails(ENITTY_TYPE_VILLAGE, genericReportingEntity.getParamStateCode(),genericReportingEntity.getParamDistrictCode(),genericReportingEntity.getParamSubDistrictCode(),genericReportingEntity.getParamEntityCode(),genericReportingEntity.getParamEntityName(),genericReportingEntity.getSearchCriteriaType());

			} else if("P".equalsIgnoreCase(genericReportingEntity.getSearchCriteriaType())){
				//stateWiseEntityDetails = viewEntityService.getViewParentWiseDetails(ENITTY_TYPE_SUBDISTRICT, genericReportingEntity.getParamStateCode(),genericReportingEntity.getParamDistrictCode(),genericReportingEntity.getParamSubDistrictCode());
				villagetypestatus =viewEntityService.getVillageStatusWiseDetails(ENITTY_TYPE_SUBDISTRICT, genericReportingEntity.getParamStateCode(),genericReportingEntity.getParamDistrictCode(),genericReportingEntity.getParamSubDistrictCode(),genericReportingEntity.getParamEntityCode(),genericReportingEntity.getParamEntityName(),genericReportingEntity.getSearchCriteriaType());
			
			}
			
			model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY", stateWiseEntityDetails);
			mav.addObject("village_status_details",villagetypestatus);
			model.addAttribute("showSearchHierarchy", Boolean.FALSE);
		} catch (Exception e) {
			log.error("view details controller: ", e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav.setViewName(redirectPath);
			return mav;
		}
		return mav;
	}

	
	/*
	 * Sub-district Level Reports.
	 */
	
	@RequestMapping(value = "/globalviewBlockforcitizen", method = RequestMethod.GET)
	public ModelAndView showBlockPageGlobal(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("globalview_block_report");
		try {
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			model.addAttribute("showSearchHierarchy", Boolean.TRUE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value = "/globalviewBlock", method = RequestMethod.POST)
	public ModelAndView showGlobalBlockPage(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, 
											   	  Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView("globalview_block_report");
		try {
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			model.addAttribute("radioButton", genericReportingEntity.getSearchCriteriaType());

			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showSearchHierarchy", Boolean.TRUE);
				return mav;
			} 

			List<GenericReportingEntity> stateWiseEntityDetails = null;
			if("N".equalsIgnoreCase(genericReportingEntity.getSearchCriteriaType())){
				stateWiseEntityDetails = viewEntityService.getViewEntityWiseDetails(
				  		  genericReportingEntity.getParamStateCode(),
				  		  ENITTY_TYPE_BLOCK,
				  		  genericReportingEntity.getParamEntityCode(),
				  		  genericReportingEntity.getParamEntityName());

			} else if("P".equalsIgnoreCase(genericReportingEntity.getSearchCriteriaType())){
				/* 
				 * ENITTY_TYPE_DISTRICT in lower case used  for Block Search
				 */
				stateWiseEntityDetails = viewEntityService.getViewParentWiseDetails(ENITTY_TYPE_DISTRICT_FOR_BLOCK, genericReportingEntity.getParamStateCode(),genericReportingEntity.getParamDistrictCode(),genericReportingEntity.getParamSubDistrictCode());
			}
			
			model.addAttribute("SEARCH_BLOCK_RESULTS_KEY", stateWiseEntityDetails);
			model.addAttribute("showSearchHierarchy", Boolean.FALSE);
		} catch (Exception e) {
			log.error("view details controller: ", e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav.setViewName(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/downloadReportGO")
	public void fileDownloadGO(HttpServletRequest request, HttpServletResponse response, @RequestParam("filename") String filename) throws Exception {
		String isSuccess = localBodyUtil.downloadFileFromServer(filename, Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString()), request, response);
		if(!"SUCCESS".equals(isSuccess)){
			throw new Exception(isSuccess);
		}
		return;
	}
	
	/**
	 * The {@code getglobalStatePage} {@linkplain globalviewstateforcitizen} moved from CitizenController.java
	 * to RestructureReporting.java 
	 * @param stateView
	 * @param request
	 * @param session
	 * @param model
	 * @param captchaAlreadyEntered
	 * @return
	 */
	@RequestMapping(value = "/globalviewstateforcitizen", method = RequestMethod.POST)
	public ModelAndView getglobalStatePage(@ModelAttribute("stateView") StateDataForm stateView, HttpServletRequest request, HttpSession session, Model model, @RequestParam("captchaAlreadyEntered") String captchaAlreadyEntered) {		
		ModelAndView mav = new ModelAndView("globalview_cstate");	
		session.setAttribute("redirectStateBean", stateView);
		try {
			if( captchaAlreadyEntered == null || captchaAlreadyEntered.equals("")) {
				String captchaAnswer = stateView.getCaptchaAnswer();
				CaptchaValidator captchaValidator = new CaptchaValidator();
				boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
				if(!messageFlag) {
	      			mav.addObject("flag1",1);
	      			mav.addObject("captchaSuccess", messageFlag);
	      			stateView.setCaptchaAnswer(null);
	      			return mav;
				} else {
					List<State> listStateDetails = stateService.getStateViewList(stateView);
					model.addAttribute("SEARCH_STATE_RESULTS_KEY", listStateDetails);
					stateView.setListStateDetail(listStateDetails);
					mav.addObject("stateView", stateView);
					model.addAttribute("viewPage", "abc");
					stateView.setCaptchaAnswer(null);
					return mav;
	      	}
		} else {
			List<State> listStateDetails = stateService.getStateViewList(stateView);
			model.addAttribute("SEARCH_STATE_RESULTS_KEY", listStateDetails);
			stateView.setListStateDetail(listStateDetails);
			mav.addObject("stateView", stateView);
			model.addAttribute("viewPage", "abc");
			stateView.setCaptchaAnswer(null);
			return mav;
		}
	} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			stateView.setCaptchaAnswer(null);
			return mav;
		}
	}
	
	
	/*
	 *  Block Wise Villages and ULBs Reports.
	 */
	
	@RequestMapping(value = "/globalviewblockwiseVillageandUlbforcitizen", method = RequestMethod.GET)
	public ModelAndView showBlockWiseVillsAndULBs(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("global_blockwise_vill_ulb_report");
		try {
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			model.addAttribute("showSearchHierarchy", Boolean.TRUE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	/**
	 * @author Maneesh Kumar
	 * @since 17Mar2016
	 * @param genericReportingEntity
	 * @param model
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/globalviewblockwiseVillageandUlbforcitizen", method = RequestMethod.POST)
	public ModelAndView showBlockWiseVillsAndULBsPOST(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("global_blockwise_vill_ulb_report");
		try{
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			model.addAttribute("radioButton", genericReportingEntity.getSearchCriteriaType());

			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			if ( !messageFlag) { 
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showSearchHierarchy", Boolean.TRUE);
				return mav;
			} 
			
			model.addAttribute("blockWiseEntityList", viewEntityService.getBlockwiseandULBDetails(genericReportingEntity.getParamBlockCode()));
			
		} catch (Exception e) {
			log.error("view details controller: ", e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav.setViewName(redirectPath);
			return mav;
		}
		return mav;
		

	}
	
	
	@RequestMapping(value = "/globalViewLocalBodyForCitizen", method = RequestMethod.GET)
	public ModelAndView globalviewLocalbody(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("globalview_localbody_report");
		try {
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			model.addAttribute("showSearchHierarchy", Boolean.TRUE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value = "/globalViewLocalBodyForCitizen", method = RequestMethod.POST)
	public ModelAndView showglobalviewLocalbody(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, 
											  Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView("globalview_localbody_report");
		try {
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			model.addAttribute("radioButton", genericReportingEntity.getSearchCriteriaType());

			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				genericReportingEntity.setParamStateCode(null);
				model.addAttribute("showSearchHierarchy", Boolean.TRUE);
				return mav;
			} 

			
			Integer parentLblc=null;
			Integer lbTypeCode=null;
			/*if("N".equalsIgnoreCase(genericReportingEntity.getSearchCriteriaType())){
				

			} else if("P".equalsIgnoreCase(genericReportingEntity.getSearchCriteriaType())){
				lbTypeCode = Integer.parseInt(genericReportingEntity.getLocalBodyTypeParam().split("\\_")[0]);
			}*/
			//if("P".equalsIgnoreCase(genericReportingEntity.getSearchCriteriaType())){
				lbTypeCode = Integer.parseInt(genericReportingEntity.getLocalBodyTypeParam().split("\\_")[0]);
				if(genericReportingEntity.getLocalBodyLevelCodes()!=null){
					String[] lbCodeArray = genericReportingEntity.getLocalBodyLevelCodes().split(",");
					parentLblc = Integer.parseInt(lbCodeArray[lbCodeArray.length - 1]);
				}
			//}
			
			model.addAttribute("SEARCH_Localbody_RESULTS_KEY", localGovtBodyService.getLocalBodyListforGisStatus(lbTypeCode,genericReportingEntity.getParamStateCode(), parentLblc, genericReportingEntity.getParamEntityCode(),genericReportingEntity.getParamEntityName()));
			model.addAttribute("showSearchHierarchy", Boolean.FALSE);
		} catch (Exception e) {
			log.error("view details controller: ", e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav.setViewName(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/downloadAnnouncementFile")
	public void downloadAnnouncementFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("filename") String filename) throws Exception {
		String isSuccess = localBodyUtil.downloadFileFromServer(filename, Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO_ANNOUNCEMENT.toString()), request, response);
		if(!"SUCCESS".equals(isSuccess)){
			throw new Exception(isSuccess);
		}
		return;
	}
	
	
	
	
}

