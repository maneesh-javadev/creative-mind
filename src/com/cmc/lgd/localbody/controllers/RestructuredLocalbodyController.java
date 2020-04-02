package com.cmc.lgd.localbody.controllers;

import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.ApplicationConstant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.entities.CriteriaDraftedEntities;
import com.cmc.lgd.localbody.entities.LBAttributes;
import com.cmc.lgd.localbody.entities.LocalBodyForm;
import com.cmc.lgd.localbody.rules.Errors;
import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.rules.LocalBodyUtil;
import com.cmc.lgd.localbody.services.LocalBodyService;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class RestructuredLocalbodyController { // NO_UCD (unused code)
	
	private Integer stateCode;
	
	private Integer userId;
	
	private Integer districtCode;
	
	@Autowired
	private LocalBodyService localBodyService;
	
	@Autowired
	private LocalBodyUtil localBodyUtil;
	
	@Autowired
	private Errors lbFormValidator;
	
	@Autowired
	private VillageService villageService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		SimpleDateFormat dateFormat = new SimpleDateFormat(LocalBodyConstant.CURRENT_DATE_PATTERN.toString());
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.getBindingResult();
        binder.validate();
     	String obj = binder.getObjectName();
		if("localBodyForm".equalsIgnoreCase(obj)) {
			LocalBodyForm localBodyForm = (LocalBodyForm) binder.getTarget();
			localBodyForm.setStateCode(stateCode);
			localBodyForm.setCreatedBy(userId);
			localBodyForm.setDistrictCode(districtCode);
		}
	}
	
	/**
	 * 
	 * @param session
	 */
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId().intValue();
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
		
	/**
	 * 
	 * @param request
	 * @param mav
	 * @param localBodyForm
	 * @return
	 */
	private ModelAndView setAttributes(HttpServletRequest request, ModelAndView mav, LocalBodyForm localBodyForm){
		try {
			setGlobalParams(request.getSession());
			String localBodyCreationType = localBodyForm.getLocalBodyCreationType();
			LBAttributes attributes = localBodyService.onLoadLocalBody(stateCode, localBodyCreationType, null); //null for create new local body process
			if(attributes.getIsGovernmentOrderUpload() == null || attributes.getIsMapUpload() == null){
				return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
			}
			if(localBodyCreationType.equals(LocalBodyConstant.LB_PANCHAYAT.toString())){
				mav.addObject("habitationConfigration",villageService.getHabitationConfigrationLocalbody(stateCode));
			}
			if(localBodyCreationType.equals(LocalBodyConstant.LB_URBAN.toString())){
				mav.addObject("localBodyTypeList", attributes.getLocalBodyTypes());
				mav.addObject("isDistrictLevel", ApplicationConstant.checkStateLBOnlyDisttWise(stateCode));
			} else  {
				mav.addObject("lbTypeHierarchy", attributes.getLbTypeHierarchy());
			}
			if(!attributes.getIsGovernmentOrderUpload()){
				mav.addObject("templateList", attributes.getGovernmentOrderTemplates());
				
			}
			mav.addObject("LOCAL_BODY_CREATION_TYPE", localBodyCreationType);
			request.getSession().setAttribute("isGovernmentOrderUpload", attributes.getIsGovernmentOrderUpload());
			request.getSession().setAttribute("isMapUpload", attributes.getIsMapUpload());
			
			mav.addObject("attachmentMasterGO", attributes.getAttachmentMasterGO());
			mav.addObject("attachmentMasterMap", attributes.getAttachmentMasterMap());
			
			mav.addObject("localBodyForm", localBodyForm);
		}catch(Exception ex){
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param localBodyForm
	 * @return
	 */
	public ModelAndView createLocalBodyAsDraft(HttpServletRequest request, Model model, LocalBodyForm localBodyForm){
		ModelAndView mav = new ModelAndView();
		try{
			Boolean isSaved = localBodyService.saveLocalBodyAsDraft(localBodyForm);
			if( isSaved ) {
				model.addAttribute("publishMessage", "Local Body is successfully saved in Draft Mode.");
			}
			return localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(localBodyForm.getLocalBodyCreationType()), stateCode, districtCode); //null for create new local body process
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	} 
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param localBodyForm
	 * @return
	 */
	public ModelAndView publishLocalBody(HttpServletRequest request,  Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm){
		ModelAndView mav = new ModelAndView("restructued_create_localbody");
		try{
			String publishStatus = localBodyService.publishLocalBody(localBodyForm, request);
			if(LocalBodyConstant.LB_PUBLISH_FAILD.toString().equals(publishStatus)){
				throw new Exception("Unable to publish local body.");
			}
			
			Integer savedLBCode = Integer.parseInt(publishStatus.split("\\,")[0]);
			localBodyForm.setLocalBodyCode(savedLBCode);
			if((localBodyForm.getLocalBodyTypeCode()==3) && ((localBodyForm.getContributingHabiationCodes()!=null && !("").equals(localBodyForm.getContributingHabiationCodes()))||(localBodyForm.getAvilableHabitation()!=null && !("").equals(localBodyForm.getAvilableHabitation())))){
				villageService.saveHabitationLocalbody(localBodyForm);
			}
			LBAttributes attributes = localBodyService.getLocalBodyDetailsForView(savedLBCode);
			mav.addObject("LocalBodyDetails", attributes.getLocalBodyDetails());
			mav.addObject("LandRegionCoverges", attributes.getLandRegionCovergeDetails());
			mav.setViewName("restructued_view_success_localbody");
		}catch(Exception ex){
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	//@RequestMapping(value = "/restuctureCreatePRILocalBody", method = RequestMethod.GET)
	@RequestMapping(value = "/createLocalBodyforPRI", method = RequestMethod.GET)
	public ModelAndView createPRILocalBody(HttpServletRequest request,  Model model){
		LocalBodyForm localBodyForm = new LocalBodyForm();
		localBodyForm.setLocalBodyCreationType(LocalBodyConstant.LB_PANCHAYAT.toString());
		return setAttributes(request, new ModelAndView("restructued_create_localbody"), localBodyForm);
	}
	
	//@RequestMapping(value = "/restuctureCreateTraditinalLocalBody", method = RequestMethod.GET)
	@RequestMapping(value = "/createLocalBodyforTraditional", method = RequestMethod.GET)
	public ModelAndView createTraditionalLocalBody(HttpServletRequest request, Model model){
		LocalBodyForm localBodyForm = new LocalBodyForm();
		localBodyForm.setLocalBodyCreationType(LocalBodyConstant.LB_TRADITIONAL.toString());
		return setAttributes(request, new ModelAndView("restructued_create_localbody"), localBodyForm);
	} 
	
	//@RequestMapping(value = "/restuctureCreateUrbanLocalBody", method = RequestMethod.GET)
	@RequestMapping(value = "/createLocalBodyforUrban", method = RequestMethod.GET)
	public ModelAndView createUrbanLocalBody(HttpServletRequest request, HttpSession session, Model model){
		LocalBodyForm localBodyForm = new LocalBodyForm();
		localBodyForm.setLocalBodyCreationType(LocalBodyConstant.LB_URBAN.toString());
		return setAttributes(request, new ModelAndView("restructued_create_urban_localbody"), localBodyForm);
	}
	
	@RequestMapping(value = "/buildLocalBody", method = RequestMethod.POST)
	public ModelAndView buildLocalBody(HttpServletRequest request, Model model, @ModelAttribute("localBodyForm") LocalBodyForm localBodyForm, BindingResult binding) throws Exception{
		ModelAndView mav = new ModelAndView();
		try{
			lbFormValidator.validate(localBodyForm, binding, (Boolean) request.getSession().getAttribute("isGovernmentOrderUpload"));
			if(binding.hasErrors()){
				String annotation = null;
				if(localBodyForm.getId() != null){
					if( LocalBodyConstant.LB_URBAN.toString().equals(localBodyForm.getLocalBodyCreationType()) ){
						annotation = "restructued_modify_temp_urban_localbody";
					} else {
						annotation = "restructued_modify_temp_localbody";
					}
					java.util.Map<String, Object> draftedLBDetails = localBodyService.getLBFormforModification(localBodyForm.getId());
					draftedLBDetails.remove("lbForm");
					localBodyUtil.setMapAttributes(draftedLBDetails, mav);
					mav.addObject("modifyProcess", Boolean.TRUE);
				}else{
					if( LocalBodyConstant.LB_URBAN.toString().equals(localBodyForm.getLocalBodyCreationType()) ){
						annotation = "restructued_create_urban_localbody";
					} else {
						annotation = "restructued_create_localbody";
					}
				}
				mav.setViewName(annotation);
				mav.addObject("checkedServerValidation", Boolean.TRUE);
				return setAttributes(request, mav, localBodyForm);
			}
			
			if("DRAFT".equals(localBodyForm.getProcessAction())){
				return createLocalBodyAsDraft(request, model, localBodyForm);
			}else if("PUBLISH".equals(localBodyForm.getProcessAction())){
				return publishLocalBody(request, model, localBodyForm);
			}
		} catch(Exception ex) {
			mav = new ModelAndView(errorHandler(request, ex));
		}	
		return null;
	}
	
	@RequestMapping(value = "/callViewDraftedLB", method = RequestMethod.POST)
	public ModelAndView callViewDraftedLocalBody(HttpServletRequest request,  HttpServletResponse response, Model model, CriteriaDraftedEntities criteria){
		ModelAndView mav = new ModelAndView("restructued_view_drafted_localbody");
		try{
			LBAttributes attributes = localBodyService.onLoadLocalBody(stateCode, criteria.getLocalBodyCreationType(), null); //null for create new local body process
			if(attributes.getIsGovernmentOrderUpload() == null || attributes.getIsMapUpload() == null){
				return new ModelAndView("success").addObject("msgid", attributes.getSystemConfigMessage());
			}
			request.getSession().setAttribute("isGovernmentOrderUpload", attributes.getIsGovernmentOrderUpload());
			request.getSession().setAttribute("isMapUpload", attributes.getIsMapUpload());
						
			java.util.Map<String, Object> draftedLBDetails = localBodyService.getDraftedTempLBDetails(criteria.getEntityTempId());
			localBodyUtil.setMapAttributes(draftedLBDetails, mav);
			mav.addObject("draftBCreationType", criteria.getLocalBodyCreationType());
		} catch(Exception ex) {
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	} 
	
	@RequestMapping(value = "/modifiedDraftedLocalBody", method = RequestMethod.POST)
	public ModelAndView modiifiedDraftedLB(HttpServletRequest request, HttpServletResponse response, Model model, CriteriaDraftedEntities criteria){
		ModelAndView mav = null;
		try{
			if( LocalBodyConstant.LB_URBAN.toString().equals(criteria.getLocalBodyCreationType())){
				mav = new ModelAndView("restructued_modify_temp_urban_localbody");
			} else {
				mav = new ModelAndView("restructued_modify_temp_localbody");
			}
			java.util.Map<String, Object> draftedLBDetails = localBodyService.getLBFormforModification(criteria.getEntityTempId());
			LocalBodyForm lbForm = (LocalBodyForm) draftedLBDetails.get("lbForm");
			lbForm.setLocalBodyCreationType(criteria.getLocalBodyCreationType());
			lbForm.setHidContributingLBCodes(lbForm.getContributingLBCodes());
			lbForm.setHidContributingLandRegionCodes(lbForm.getContributingLandRegionCodes());
			lbForm.setIsResetedCoverage(Boolean.FALSE);
			mav.addObject("checkedServerValidation", Boolean.TRUE);
			mav.addObject("modifyProcess", Boolean.TRUE);
			draftedLBDetails.remove("lbForm");
			localBodyUtil.setMapAttributes(draftedLBDetails, mav);
			return setAttributes(request, mav, lbForm);
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView(errorHandler(request, ex));
		}
	}
	
	@RequestMapping(value = "/publishSingleDraftedLocalBody", method = RequestMethod.POST)
	public ModelAndView publishSinleDraftedLB(HttpServletRequest request,  HttpServletResponse response, Model model, 
											  @RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode,
											  @RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType){
		try{
			Boolean isPublishedStatus = localBodyService.publishOrDeleteDraftToTransaction(tempLocalBodyCode, Boolean.FALSE, request);
			if( isPublishedStatus ) {
				model.addAttribute("publishMessage", "Record Published Successfully.");
			}
			return localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), stateCode, districtCode); //null for create new local body process
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView(errorHandler(request, ex));
		}
	} 
	
	@RequestMapping(value = "/deleteSingleDraftedLocalBody", method = RequestMethod.POST)
	public ModelAndView deleteSingleDraftedLB(HttpServletRequest request,  HttpServletResponse response, Model model, 
												@RequestParam("tempLocalBodyCode") Integer tempLocalBodyCode,
												@RequestParam("tempLocalBodyCreationType") String tempLocalBodyCreationType){
		try{
			Boolean isPublishedStatus = localBodyService.publishOrDeleteDraftToTransaction(tempLocalBodyCode, Boolean.TRUE, request);
			if( isPublishedStatus ) {
				model.addAttribute("publishMessage", "Drafted Local Body Deleted Successfully.");
			}
			return localBodyUtil.setAttributesForDraftedEntities(request, new CriteriaDraftedEntities(tempLocalBodyCreationType), stateCode, districtCode); //null for create new local body process
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView(errorHandler(request, ex));
		}
	}
	
	@RequestMapping(value = "/downloadLBGovernementOrder", method = RequestMethod.GET)
	public void fileDownloadGO(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model, @RequestParam("filename") String filename) throws Exception {
		String isSuccess = localBodyUtil.downloadFileFromServer(filename, Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString()), request, response);
		if(!"SUCCESS".equals(isSuccess)){
			throw new Exception(isSuccess);
		}
		return;
	}
	
	@RequestMapping(value = "/downloadLBMap", method = RequestMethod.GET)
	public void fileDownloadMap(HttpServletRequest request, HttpServletResponse response,  Map<String, Object> model, @RequestParam("filename") String filename) throws Exception {
		String isSuccess = localBodyUtil.downloadFileFromServer(filename, Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_MAP.toString()), request, response);
		if(!"SUCCESS".equals(isSuccess)){
			throw new Exception(isSuccess);
		}
		return;
	}
	
	@RequestMapping(value = "/searchDraftedEntitiesPRI", method = RequestMethod.GET)
	public ModelAndView searchDraftedEntitiesPRI(HttpServletRequest request,  Model model){
		setGlobalParams(request.getSession());
		CriteriaDraftedEntities criteriaDraftedEntities = new CriteriaDraftedEntities(LocalBodyConstant.LB_PANCHAYAT.toString());
		return localBodyUtil.setAttributesForDraftedEntities(request, criteriaDraftedEntities, stateCode, districtCode);
	}
	
	@RequestMapping(value = "/searchDraftedEntitiesTraditional", method = RequestMethod.GET)
	public ModelAndView searchDraftedEntitiesTraditional(HttpServletRequest request,  Model model){
		setGlobalParams(request.getSession());
		CriteriaDraftedEntities criteriaDraftedEntities = new CriteriaDraftedEntities(LocalBodyConstant.LB_TRADITIONAL.toString());
		return localBodyUtil.setAttributesForDraftedEntities(request, criteriaDraftedEntities, stateCode, districtCode);
	}
	
	@RequestMapping(value = "/searchDraftedEntitiesUrban", method = RequestMethod.GET)
	public ModelAndView searchDraftedEntitiesUrban(HttpServletRequest request,  Model model){
		setGlobalParams(request.getSession());
		CriteriaDraftedEntities criteriaDraftedEntities = new CriteriaDraftedEntities(LocalBodyConstant.LB_URBAN.toString());
		return localBodyUtil.setAttributesForDraftedEntities(request, criteriaDraftedEntities, stateCode, districtCode);
	}
	
	@RequestMapping(value = "/getDraftedEntityDetails", method = RequestMethod.POST)
	public ModelAndView getDraftedEntityDetails(HttpServletRequest request,  HttpServletResponse response, Model model, CriteriaDraftedEntities criteriaDraftedEntities){
		try{
			setGlobalParams(request.getSession());
			return localBodyUtil.setAttributesForDraftedEntities(request, criteriaDraftedEntities, stateCode, districtCode);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ModelAndView(errorHandler(request, ex));
		}
	} 
}
