package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.GetEntitiesWithDisturbed;
import in.nic.pes.lgd.bean.GetEntitiesWithWanrning;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.service.BlockService;
import in.nic.pes.lgd.service.DisturbedEntitiesService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class DisturbedStateController  // NO_UCD (unused code)
{
	private static final Logger log = Logger.getLogger(DisturbedStateController.class);
	@Autowired
	DisturbedEntitiesService disturbedEntitiesService;
	
	@Autowired
	BlockService blockService;
	
	private Integer stateCode;

	private Integer districtCode;

	private Long userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
	}
	
	
	
	/**
	 * The {@code viewResolveEntitiesinDisturbedStateGetPRI} used to show <list> of entities 
	 * belongs to PRI Local Body. 
	 * @param getEntitiesWithWanrning
	 * @param model
	 * @param httpSession
	 * @param request
	 * @return ModelAndView (Redirected to PRI Resolve Disturbed State or 
	 * With Warning Flag)
	 */
	@RequestMapping(value="/viewResolveEntitiesinDisturbedStatePRI",method = RequestMethod.GET)
	public ModelAndView viewResolveEntitiesinDisturbedStateGetPRI(@ModelAttribute("GetEntitiesWithWanrning") GetEntitiesWithWanrning getEntitiesWithWanrning, 
																  Model model,
																  HttpSession httpSession,
																  HttpServletRequest request) {
		ModelAndView mav;
		try {
			String type = "P";
			
			String parentEntityType = "";
			int parententitycode = 0;
			if(districtCode != 0) {
				parentEntityType = "D";
				parententitycode = districtCode;
			} else {
				parentEntityType = "S";	
				parententitycode = stateCode;
			}
			
			Integer uidi = userId != null ? Integer.parseInt(userId.toString()) : 0;
			
			List<GetEntitiesWithDisturbed> disturbedList = new ArrayList<GetEntitiesWithDisturbed>();
			List<GetEntitiesWithWanrning> warningList = new ArrayList<GetEntitiesWithWanrning>();
			List<GetEntitiesWithWanrning> mapLandRegionList = disturbedEntitiesService.getEntitiesWithWarningFlag(uidi,type,parentEntityType,parententitycode);
			List<GetEntitiesWithDisturbed> mapLandRegionListDisturb = disturbedEntitiesService.getEntitiesWithDisturbedFlag(uidi,type,parentEntityType,parententitycode);
			for (GetEntitiesWithWanrning getEntitiesWithWanrningObj : mapLandRegionList) {
				boolean isdisturbed = getEntitiesWithWanrningObj.isIsdisturbed();
				if(isdisturbed == false)
				/*if(isdisturbed == true){
					disturbedList.add(getEntitiesWithWanrningObj);
				} else*/
				{
					warningList.add(getEntitiesWithWanrningObj);
				}
			}
			for (GetEntitiesWithDisturbed getEntitiesWithDisturbObj : mapLandRegionListDisturb) {
				disturbedList.add(getEntitiesWithDisturbObj);
			}
			
			model.addAttribute("warningEnties",warningList);
			model.addAttribute("disturbedEnties",disturbedList);
			mav = new ModelAndView("viewResolveEntitiesinDisturbedState");
			String  warningEntiesFlag = request.getParameter("warningEntiesFlag");	
			if(warningEntiesFlag!=null && !warningEntiesFlag.equals("")){
				mav.addObject("warningEntiesFlag", warningEntiesFlag);
			}
			mav.addObject("type",type);
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	/**
	 * The {@code viewResolveEntitiesinDisturbedStateTra} used to show <list> of entities 
	 * belongs to Traditional Local Body. 
	 * @param getEntitiesWithWanrning
	 * @param model
	 * @param httpSession
	 * @param request
	 * @return ModelAndView (Redirected to Traditional LB Resolve Disturbed State or 
	 * With Warning Flag)
	 */
	@RequestMapping(value="/viewResolveEntitiesinDisturbedStateTra",method = RequestMethod.GET)
	public ModelAndView viewResolveEntitiesinDisturbedStateGetTra(@ModelAttribute("GetEntitiesWithWanrning") GetEntitiesWithWanrning getEntitiesWithWanrning, 
																  Model model,
																  HttpSession httpSession,
																  HttpServletRequest request) {
		ModelAndView mav;
		try {
			String type="T";
			String parentEntityType = "";
			int parententitycode = 0;
			if(districtCode != 0) {
				parentEntityType = "D";
				parententitycode=districtCode;
			} else {
				parentEntityType = "S";	
				parententitycode=stateCode;
			}
			
			Integer uidi = userId != null ? Integer.parseInt(userId.toString()) : 0; 
			
			List<GetEntitiesWithDisturbed> disturbedList = new ArrayList<GetEntitiesWithDisturbed>();
			List<GetEntitiesWithWanrning> warningList = new ArrayList<GetEntitiesWithWanrning>();
			List<GetEntitiesWithWanrning> mapLandRegionList = disturbedEntitiesService.getEntitiesWithWarningFlag(uidi,type,parentEntityType,parententitycode);
			List<GetEntitiesWithDisturbed> mapLandRegionListDisturb = disturbedEntitiesService.getEntitiesWithDisturbedFlag(uidi,type,parentEntityType,parententitycode);
			for (GetEntitiesWithWanrning getEntitiesWithWanrningObj : mapLandRegionList) {
				boolean isdisturbed = getEntitiesWithWanrningObj.isIsdisturbed();
				/*if(isdisturbed == true){
					disturbedList.add(getEntitiesWithWanrningObj);
				} else{*/
				if(isdisturbed == false){
					warningList.add(getEntitiesWithWanrningObj);
				}
			}
			for (GetEntitiesWithDisturbed getEntitiesWithDisturbObj : mapLandRegionListDisturb) {
					disturbedList.add(getEntitiesWithDisturbObj);
			}
			model.addAttribute("warningEnties",warningList);
			model.addAttribute("disturbedEnties",disturbedList);
			mav = new ModelAndView("viewResolveEntitiesinDisturbedState");
			String  warningEntiesFlag = request.getParameter("warningEntiesFlag");	
			if(warningEntiesFlag != null && !warningEntiesFlag.equals("")){
				mav.addObject("warningEntiesFlag", warningEntiesFlag);
			}
			mav.addObject("type",type);
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	/**
	 * The {@code viewResolveEntitiesinDisturbedStateTra} used to show <list> of entities 
	 * belongs to Urban Local Body.  
	 * @param getEntitiesWithWanrning
	 * @param model
	 * @param httpSession
	 * @param request
	 * @return ModelAndView (Redirected to Urban LB Resolve Disturbed State or 
	 * With Warning Flag)
	 */
	@RequestMapping(value="/viewResolveEntitiesinDisturbedStateULB",method = RequestMethod.GET)
	public ModelAndView viewResolveEntitiesinDisturbedStateGetULB(@ModelAttribute("GetEntitiesWithWanrning") GetEntitiesWithWanrning getEntitiesWithWanrning, 
																  Model model,
																  HttpSession httpSession,
																  HttpServletRequest request) {
		ModelAndView mav;
		try {
			String type = "U";
			String parentEntityType = "";
			int parententitycode = 0;
			if(districtCode != 0) {
				parentEntityType = "D";
				parententitycode=districtCode;
			} else {
				parentEntityType = "S";	
				parententitycode=stateCode;
			}
			Integer uidi = userId != null ? Integer.parseInt(userId.toString()) : 0; 
			List<GetEntitiesWithDisturbed> disturbedList = new ArrayList<GetEntitiesWithDisturbed>();
			List<GetEntitiesWithWanrning> warningList = new ArrayList<GetEntitiesWithWanrning>();
			List<GetEntitiesWithWanrning> mapLandRegionList = disturbedEntitiesService.getEntitiesWithWarningFlag(uidi,type,parentEntityType,parententitycode);
			List<GetEntitiesWithDisturbed> mapLandRegionListDisturb = disturbedEntitiesService.getEntitiesWithDisturbedFlag(uidi,type,parentEntityType,parententitycode);
			for (GetEntitiesWithWanrning getEntitiesWithWanrningObj : mapLandRegionList) {
				boolean isdisturbed = getEntitiesWithWanrningObj.isIsdisturbed();
				/*if(isdisturbed == true){
					disturbedList.add(getEntitiesWithWanrningObj);
				} else{*/
				if(isdisturbed == false){
					warningList.add(getEntitiesWithWanrningObj);
				}
			}
			for (GetEntitiesWithDisturbed getEntitiesWithDisturbObj : mapLandRegionListDisturb) {
				disturbedList.add(getEntitiesWithDisturbObj);
			}
			model.addAttribute("warningEnties",warningList);
			model.addAttribute("disturbedEnties",disturbedList);
			mav = new ModelAndView("viewResolveEntitiesinDisturbedState");
			String  warningEntiesFlag = request.getParameter("warningEntiesFlag");
			if(warningEntiesFlag!=null && !warningEntiesFlag.equals("")){
				mav.addObject("warningEntiesFlag", warningEntiesFlag);
			}
			mav.addObject("type",type);
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	/**
	 * The {@code viewResolveEntitiesinDisturbedStateGetLR} used to show <list> of entities 
	 * belongs to Land Region. 
	 * @param getEntitiesWithWanrning
	 * @param model
	 * @param httpSession
	 * @param ModelAndView (Redirected to Land Region Resolve Disturbed State or 
	 * With Warning Flag)
	 * @return
	 * 
	 */
	@RequestMapping(value="/viewResolveEntitiesinDisturbedStateLR",method = RequestMethod.GET)
	public ModelAndView viewResolveEntitiesinDisturbedStateGetLR(@ModelAttribute("GetEntitiesWithWanrning") GetEntitiesWithWanrning getEntitiesWithWanrning, 
																 Model model,
																 HttpSession httpSession,
																 HttpServletRequest request) {
		ModelAndView mav;
		try {
			String type="L";
			String parentEntityType = "";
			int parententitycode=0;
			if(districtCode != 0) {
				parentEntityType = "D";
				parententitycode = districtCode;
			} else {
				parentEntityType = "S";	
				parententitycode=stateCode;
			}
			
			Integer uidi = userId != null ? Integer.parseInt(userId.toString()) : 0;
				
			List<GetEntitiesWithWanrning> disturbedList = new ArrayList<GetEntitiesWithWanrning>();
			List<GetEntitiesWithWanrning> warningList = new ArrayList<GetEntitiesWithWanrning>();
			List<GetEntitiesWithWanrning> mapLandRegionList = disturbedEntitiesService.getEntitiesWithWarningFlag(uidi,type,parentEntityType,parententitycode);
			for (GetEntitiesWithWanrning getEntitiesWithWanrningObj : mapLandRegionList) {
				boolean isdisturbed = getEntitiesWithWanrningObj.isIsdisturbed();
				/*if(isdisturbed){
					disturbedList.add(getEntitiesWithWanrningObj);
				}else{*/
				if(isdisturbed == false){
					warningList.add(getEntitiesWithWanrningObj);
				}
			}
			model.addAttribute("warningEnties",warningList);
			model.addAttribute("disturbedEnties",disturbedList);
			mav = new ModelAndView("viewResolveEntitiesinDisturbedState");
			String  warningEntiesFlag = request.getParameter("warningEntiesFlag");	
			if(warningEntiesFlag != null && !warningEntiesFlag.equals("")){
				mav.addObject("warningEntiesFlag", warningEntiesFlag);
			}
			mav.addObject("type",type);
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/viewResolveEntitiesinDisturbedState")
	public ModelAndView viewResolveEntitiesinDisturbedState(@ModelAttribute("GetEntitiesWithWanrning") GetEntitiesWithWanrning getEntitiesWithWanrning,
															Model model,
															HttpSession httpSession, 
															HttpServletRequest request) {
		ModelAndView mav;
		try {
				disturbedEntitiesService.unSetMapLandRegionFlag(getEntitiesWithWanrning.getEntityCode(), getEntitiesWithWanrning.getEntityVersion());		
				
				String parentEntityType = "";
				int parententitycode=0;
				String type = request.getParameter("type");
				
				
				if(districtCode != 0) {
					parentEntityType = "D";
					parententitycode=districtCode;
				} else {
					parentEntityType = "S";
					parententitycode=stateCode;
				}
				
				Integer uidi = userId != null ? Integer.parseInt(userId.toString()) : 0;
				
				List<GetEntitiesWithWanrning> disturbedList = new ArrayList<GetEntitiesWithWanrning>();
				List<GetEntitiesWithWanrning> warningList = new ArrayList<GetEntitiesWithWanrning>();
				List<GetEntitiesWithWanrning> mapLandRegionList = disturbedEntitiesService.getEntitiesWithWarningFlag(uidi,type,parentEntityType,parententitycode);
				for (GetEntitiesWithWanrning getEntitiesWithWanrningObj : mapLandRegionList) {
					boolean isdisturbed = getEntitiesWithWanrningObj.isIsdisturbed();
					/*if(isdisturbed == true){
						disturbedList.add(getEntitiesWithWanrningObj);
					} else{*/
					if(isdisturbed == false){
						warningList.add(getEntitiesWithWanrningObj);
					}
				}
				model.addAttribute("warningEnties",warningList);
				model.addAttribute("disturbedEnties",disturbedList);
				mav = new ModelAndView("viewResolveEntitiesinDisturbedState");
				String  warningEntiesFlag = request.getParameter("warningEntiesFlag");	
				if(warningEntiesFlag!=null && !warningEntiesFlag.equals("")){
					mav.addObject("warningEntiesFlag", warningEntiesFlag);
				}
				mav.addObject("type",type);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	/*@RequestMapping(value="/assignBlockVillageDisturbedState", method=RequestMethod.GET)
	public ModelAndView assignVillagestoBlockGet(HttpSession session, 
												 @ModelAttribute("shiftvillageForm") ShiftVillageForm shiftvillageForm,
												 Model model, 
												 HttpServletRequest request,
												 HttpSession httpSession) {
		ModelAndView mav=null;
		try {
			if (stateCode == null) {
					mav = new ModelAndView("redirect:login.htm");
			} else{
				villList=disturbedEntitiesService.getUnMappedVillageList(stateCode);
				model.addAttribute("villList", villList);
				mav=new ModelAndView("assignBlockVillageDisturbedState");
				model.addAttribute("disturbedEntitiesService",blockService.getBlockStateWise(stateCode));
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/assignBlockVillageDisturbedState", method=RequestMethod.POST)
	public ModelAndView assignVillagestoBlock(@ModelAttribute("shiftvillageForm") ShiftVillageForm shiftvillageForm,
											  Model model, 
											  HttpServletRequest request, 
											  HttpSession httpSession) {
		ModelAndView mav = null;
		boolean isCommited = false;
		try {
			isCommited=disturbedEntitiesService.assignDisturbedVillagestoBlock(shiftvillageForm);
			
		} catch (Exception e) {}
		if(isCommited==true) {
			mav = new ModelAndView("success");
			mav.addObject("msgid","Villages have been moved Successfully");
		} else {
			mav = new ModelAndView("success");
			mav.addObject("msgid","Village Shifting failure, please check logs");
		}	
		return mav;
	}*/
	
	@RequestMapping(value="/viewAssignBlockVillage", method=RequestMethod.GET)
	public ModelAndView viewAssignVillagestoBlockGet(@RequestParam(value="villageCodes", required=false) String villageCodes, 
													 Model model, 
													 HttpServletRequest request) {
		ModelAndView mav;
		try {
			List<Village> villList=disturbedEntitiesService.getBlockVillageDetailByBlockCode(villageCodes);
			model.addAttribute("villList", villList);
			mav = new ModelAndView("viewAssignBlock");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request,"","label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
}
