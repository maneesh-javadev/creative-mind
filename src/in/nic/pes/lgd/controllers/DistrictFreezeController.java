package in.nic.pes.lgd.controllers;


import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.FreezeDistrictBean;
import in.nic.pes.lgd.bean.LocalbodyPRIDistrictFreeze;
import in.nic.pes.lgd.bean.MappedLbWardDisttrictWise;
import in.nic.pes.lgd.bean.NodalOfficer;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.service.AddAttachmentService;
import in.nic.pes.lgd.service.DistrictFreezeService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.utils.ApplicationConstant;
import in.nic.pes.lgd.utils.DocumentConverter;
import in.nic.pes.lgd.validator.NodalOfficerValidator;
import pes.attachment.util.AttachedFilesForm;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;


/**
 * @Author Vinay Yadav 
 * @since 12 Feb 2015
 * 
 * 
 */
@Controller
public class DistrictFreezeController { // NO_UCD (unused code)

	@Autowired
	DocumentConverter docconverter;
	@Autowired
	private DistrictFreezeService districtFreezeService;
	
	@Autowired
	private GovernmentOrderService govtOrderService;
	
	@Autowired
	private AddAttachmentService addAttachmentService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private NodalOfficerValidator nodalOfficerValidator;
	
	private static final Logger log = Logger.getLogger(DistrictFreezeController.class);

	private Integer districtCode;
	private Integer stateCode;
	
	private Integer userId;
	private String districtName;
	/**
	 * The {@code errorHandler} returns error path and saved required stack trace. 
	 * @param request
	 * @param e
	 * @return
	 */
	private String errorHandler(HttpServletRequest request, Exception e) {
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		return expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
	}
	
	/**
	 * The {@code setGlobalAttributes} used to assign logged in district into district code variable and 
	 * logged in user into used id. 
	 * @param session
	 * @return
	 */
	public void setGlobalAttributes(HttpSession session) {
			SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
			stateCode = sessionObject.getStateId();
			districtCode = sessionObject.getDistrictCode();
			districtName =sessionObject.getDistrict();
			userId = sessionObject.getUserId().intValue();
	}
	
	/*
	 * Codes Started for District Freeze Land Region
	 * 
	 */
	
	@RequestMapping(value = "/freezeDistrict", method = RequestMethod.GET)
	public ModelAndView freezeDistrict(HttpSession session, HttpServletRequest request) {
		log.info("DistrictFreezeController.freezeDistrict execution started.");
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalAttributes(session);
			if( districtCode == null || districtCode == 0 ){
				mav.addObject("msgid", "Invalid District User");
				mav.setViewName("success");
				return mav;
			}
			FreezeDistrictBean districtDetails = districtFreezeService.getDestrictDetails(districtCode);
			
			
			mav.setViewName("view_freezeDistrict");
			mav.addObject("freezeDistrict", districtDetails);
			mav.addObject("userId", userId);
			log.info("DistrictFreezeController.freezeDistrict execution completed.");
		} catch (Exception e) {
			log.error("Exception in DistrictFreezeController.freezeDistrict : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	@RequestMapping(value = "/freezeDistrict", method = RequestMethod.POST)
	public ModelAndView freezeDistrictPostRecords(@ModelAttribute("freezeDistrict") FreezeDistrictBean freezeDistrictBean, HttpSession session, HttpServletRequest request) {
		log.info("DistrictFreezeController.freezeDistrictPostRecords execution started.");
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalAttributes(session);
			if( districtCode == null || districtCode == 0 ){
				mav.addObject("msgid", "Invalid District User");
				mav.setViewName("success");
				return mav;
			}
			freezeDistrictBean.setDistrictCode(districtCode);
			freezeDistrictBean.setUserId(userId);
			freezeDistrictBean.setIpAddress(request.getRemoteAddr());
			if("Freeze".equals(freezeDistrictBean.getProcessAction())){
				System.out.println("INSIDE FREEZE CONDITION");
				Boolean isFreezed = districtFreezeService.freezeUnFreezeDistrictEntity(freezeDistrictBean);
				System.out.println("RETURNED VALUE FROM DB");
				if(isFreezed){
					mav.addObject("msgid", "Freezed Successfully");
					mav.setViewName("success");
				}else{
					mav = freezeDistrict(session, request);
					mav.addObject("draftedVillages", districtFreezeService.draftedVillagesFromDistrict(districtCode));
				}
			}else if("UnFreeze".equals(freezeDistrictBean.getProcessAction())){
				if(StringUtils.isBlank(freezeDistrictBean.getReason())){
					mav = freezeDistrict(session, request);
					mav.addObject("errorReason", "Please enter a valid reason.");
					return mav;
				}
				Boolean isUnFreezed = districtFreezeService.freezeUnFreezeDistrictEntity(freezeDistrictBean);
				if (isUnFreezed) {
					mav.addObject("msgid", "Unfreezed Successfully");
				} else {
					mav.addObject("msgid", "It is Already in Unfreezed State");
				}
				mav.setViewName("success");
			}else {
				throw new Exception("Invalid Process Execution.");
			}
			log.info("DistrictFreezeController.freezeDistrictPostRecords execution ends here.");
		} catch (Exception e) {
			log.error("Exception in DistrictFreezeController.freezeDistrictPostRecords : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	/*
	 * Codes Started for LocalBody PRI 
	 * 
	 */
	
	@RequestMapping(value = "/freezePRILB", method = RequestMethod.GET)
	public ModelAndView freezeDistrictPRI(@ModelAttribute("freezePRI") FreezeDistrictBean freezeDistrictPRIBean,HttpSession session, HttpServletRequest request) {
		log.info("DistrictPRIFreezeController.freezeDistrict execution started.");
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalAttributes(session);
			if( districtCode == null || districtCode == 0 ) {
				mav.addObject("msgid", "Invalid District User");
				mav.setViewName("success");
				return mav;
			}
			
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place
			// of 10
			char lbType = 'P';
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 20, lbType);

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			//String message = hMap.get("message");
			if (govtOrderConfig == null && mapConfig == null) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "PRI doesn't exists in your state");	
				return mav;
			} 

			FreezeDistrictBean districtDetails = districtFreezeService.getDestrictDetailsForPRI(districtCode);
			mav.setViewName("view_freezeDistrictPRI");
			mav.addObject("freezeDistrict", districtDetails);
			freezeDistrictPRIBean.setDistrictCode(districtDetails.getDistrictCode());
			freezeDistrictPRIBean.setDistrictNameEnglish(districtDetails.getDistrictNameEnglish());
			freezeDistrictPRIBean.setDistrictStatus(districtDetails.getDistrictStatus());
			freezeDistrictPRIBean.setStateStatus(districtDetails.getStateStatus());
			mav.addObject("userId", userId);
			
			log.info("DistrictPRIFreezeController.freezeDistrict execution completed.");
		} catch (Exception e) {
			log.error("Exception in DistrictPRIFreezeController.freezeDistrict : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	@RequestMapping(value = "/freezeDistrictPRI", method = RequestMethod.POST)
	public ModelAndView freezeDistrictPostRecordsPRI(HttpSession session, HttpServletRequest request,@ModelAttribute("freezePRI") FreezeDistrictBean freezeDistrictPRIBean,  BindingResult binding) {
		log.info("DistrictPRIFreezeController.freezeDistrictPostRecords execution started.");
		ModelAndView mav = new ModelAndView();
		try {
			System.out.println("into pri district freeze metod");
			nodalOfficerValidator.validateTokenError(binding,freezeDistrictPRIBean.getUserOTP(),userId.longValue());
			if(binding.hasErrors()){
				FreezeDistrictBean districtDetails = districtFreezeService.getDestrictDetailsForPRI(districtCode);
				mav.setViewName("view_freezeDistrictPRI");
				mav.addObject("freezeDistrict", districtDetails);
				freezeDistrictPRIBean.setDistrictCode(districtDetails.getDistrictCode());
				freezeDistrictPRIBean.setDistrictNameEnglish(districtDetails.getDistrictNameEnglish());
				freezeDistrictPRIBean.setDistrictStatus(districtDetails.getDistrictStatus());
				freezeDistrictPRIBean.setStateStatus(districtDetails.getStateStatus());
				freezeDistrictPRIBean.setUserOTP(null);
				mav.addObject("userId", userId);
				return mav;
			}
			
			setGlobalAttributes(session);			
			if( districtCode == null || districtCode == 0 ) {
				mav.addObject("msgid", "Invalid District User");
				mav.setViewName("success");
				return mav;
			}
						
			freezeDistrictPRIBean.setUserOTP(null);
			freezeDistrictPRIBean.setDistrictCode(districtCode);
			freezeDistrictPRIBean.setUserId(userId);
			freezeDistrictPRIBean.setIpAddress(request.getRemoteAddr());
			if("Freeze".equals(freezeDistrictPRIBean.getProcessAction())) {
				// condition to check does localbody PRI is in draft villages if yes return list of villages otherwise return none
				List<LocalbodyPRIDistrictFreeze> finalList= new ArrayList<LocalbodyPRIDistrictFreeze>();
				List<LocalbodyPRIDistrictFreeze> isDraftList = districtFreezeService.draftedVillagesPRIFromDistrict(districtCode,ApplicationConstant.LOCAL_BODY_PRI_LEVEL_CODE);
				if(!isDraftList.isEmpty()){
							for (LocalbodyPRIDistrictFreeze localbodyPRIDistrictFreeze : isDraftList) {
								   localbodyPRIDistrictFreeze.setLbStatus("In Draft Mode");
								   finalList.add(localbodyPRIDistrictFreeze);
							}
				}
				
				List<LocalbodyPRIDistrictFreeze> isFreezedList = districtFreezeService.freezeUnFreezeDistrictEntityCheckByDistrictId(districtCode,ApplicationConstant.LOCAL_BODY_PRI_LEVEL_CODE);
				if(!isFreezedList.isEmpty()){
					for (LocalbodyPRIDistrictFreeze localbodyPRIDistrictFreeze : isFreezedList) {
						   if(localbodyPRIDistrictFreeze.getLbStatus().equalsIgnoreCase("Status not set")){
							   localbodyPRIDistrictFreeze.setLbStatus("Unfreeze");
						   }
						   finalList.add(localbodyPRIDistrictFreeze);
					}
		        }
				/*isDraftList.addAll(isFreezedList);*/
				/*if( !isDraftList.isEmpty() ) {
					mav = freezeDistrictPRI(freezeDistrictPRIBean,session, request);
					mav.addObject("headingMessage", "draft mode");
					mav.addObject("draftedOrFreezedVillages", isDraftList);
					//mav.addObject("draftedVillages", isDraftList);
				} else {
					// condition to check does localbody PRI is in freeze state if yes return list of villages otherwise return none 
					*/
					if( !finalList.isEmpty()) {
						mav = freezeDistrictPRI(freezeDistrictPRIBean,session, request);
						mav.addObject("headingMessage", "Unfreeze/Draft");
						mav.addObject("draftedOrFreezedVillages", finalList);
					} else {
					 	Boolean isFreezed = districtFreezeService.freezeUnFreezeDistrictPRIEntity(freezeDistrictPRIBean);
					 	if( isFreezed ) {
					 		mav.addObject("msgid", "Freezed Successfully");
					 		mav.setViewName("success");
					 		
					 	}
					}
				
			} else if("UnFreeze".equals(freezeDistrictPRIBean.getProcessAction())) {
				if(StringUtils.isBlank(freezeDistrictPRIBean.getReason())) {
					mav = freezeDistrictPRI(freezeDistrictPRIBean,session, request);
					mav.addObject("errorReason", "Please enter a valid reason.");
					return mav;
				}
				
				Boolean isUnFreezed = districtFreezeService.freezeUnFreezeDistrictPRIEntity(freezeDistrictPRIBean);
				if (isUnFreezed) {
					mav.addObject("msgid", "Unfreezed Successfully");
				} else {
					mav.addObject("msgid", "It is Already in Unfreezed State");
				}
				mav.setViewName("success");
			}else {
				throw new Exception("Invalid Process Execution.");
			}
			log.info("DistrictPRIFreezeController.freezeDistrictPostRecords execution ends here.");
		} catch (Exception e) {
			log.error("Exception in DistrictPRIFreezeController.freezeDistrictPostRecords : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	
	/*
	 * Codes Started for LocalBody TRD 
	 * 
	 */
	
	@RequestMapping(value = "/freezeTRDLB", method = RequestMethod.GET)
	public ModelAndView freezeDistrictTRD(@ModelAttribute("freezeTRD") FreezeDistrictBean freezeDistrictTRDBean,HttpSession session, HttpServletRequest request) {
		log.info("DistrictTRDFreezeController.freezeDistrict execution started.");
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalAttributes(session);
			System.out.println("DISTRICT CODE ::::: " + districtCode);
			if( districtCode == null || districtCode == 0 ) {
				mav.addObject("msgid", "Invalid District User");
				mav.setViewName("success");
				return mav;
			}
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place
			// of 10
			char lbType = 'T';
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 27, lbType);

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			//String message = hMap.get("message");
			if (govtOrderConfig == null && mapConfig == null) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Traditional doesn't exists in your state");	
				return mav;
			} 
			FreezeDistrictBean districtDetails = districtFreezeService.getDestrictDetailsForTRD(districtCode);
			mav.setViewName("view_freezeDistrictTRD");
			mav.addObject("freezeDistrict", districtDetails);
			freezeDistrictTRDBean.setDistrictCode(districtDetails.getDistrictCode());
			freezeDistrictTRDBean.setDistrictNameEnglish(districtDetails.getDistrictNameEnglish());
			freezeDistrictTRDBean.setDistrictStatus(districtDetails.getDistrictStatus());
			freezeDistrictTRDBean.setStateStatus(districtDetails.getStateStatus());
			mav.addObject("userId", userId);
			log.info("DistrictTRDFreezeController.freezeDistrict execution completed.");
		} catch (Exception e) {
			log.error("Exception in DistrictTRDFreezeController.freezeDistrict : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	@RequestMapping(value = "/freezeDistrictTRD", method = RequestMethod.POST)
	public ModelAndView freezeDistrictTRD(HttpSession session, HttpServletRequest request,@ModelAttribute("freezeURB") FreezeDistrictBean freezeDistrictTRDBean,  BindingResult binding){
		log.info("DistrictTRDFreezeController.freezeDistrictPostRecords execution started.");
		ModelAndView mav = new ModelAndView();
		try {
			nodalOfficerValidator.validateTokenError(binding,freezeDistrictTRDBean.getUserOTP(),userId.longValue());
			if(binding.hasErrors()){
				FreezeDistrictBean districtDetails = districtFreezeService.getDestrictDetailsForURB(districtCode);
				mav.setViewName("view_freezeDistrictURB");
				mav.addObject("freezeDistrict", districtDetails);
				freezeDistrictTRDBean.setDistrictCode(districtDetails.getDistrictCode());
				freezeDistrictTRDBean.setDistrictNameEnglish(districtDetails.getDistrictNameEnglish());
				freezeDistrictTRDBean.setDistrictStatus(districtDetails.getDistrictStatus());
				freezeDistrictTRDBean.setStateStatus(districtDetails.getStateStatus());
				freezeDistrictTRDBean.setUserOTP(null);
				mav.addObject("userId", userId);
				return mav;
			}
			
			setGlobalAttributes(session);
			freezeDistrictTRDBean.setUserOTP(null);
			if( districtCode == null || districtCode == 0 ){
				mav.addObject("msgid", "Invalid District User");
				mav.setViewName("success");
				return mav;
			}
			freezeDistrictTRDBean.setDistrictCode(districtCode);
			freezeDistrictTRDBean.setUserId(userId);
			freezeDistrictTRDBean.setIpAddress(request.getRemoteAddr());
			if("Freeze".equals(freezeDistrictTRDBean.getProcessAction())) {
				// condition to check does localbody TRD is in draft villages if yes return list of villages otherwise return none 
				List<LocalbodyPRIDistrictFreeze> finalList= new ArrayList<LocalbodyPRIDistrictFreeze>();
				List<LocalbodyPRIDistrictFreeze> isDraftList = districtFreezeService.draftedVillagesPRIFromDistrict(districtCode,ApplicationConstant.LOCAL_BODY_TRD_LEVEL_CODE);
				if(!isDraftList.isEmpty()){
					for (LocalbodyPRIDistrictFreeze localbodyPRIDistrictFreeze : isDraftList) {
						   localbodyPRIDistrictFreeze.setLbStatus("In Draft Mode");
						   finalList.add(localbodyPRIDistrictFreeze);
					}
				}
				 List<LocalbodyPRIDistrictFreeze> isFreezedList  = districtFreezeService.freezeUnFreezeDistrictEntityCheckByDistrictId(districtCode,ApplicationConstant.LOCAL_BODY_TRD_LEVEL_CODE);
				 if(!isFreezedList.isEmpty()){
						for (LocalbodyPRIDistrictFreeze localbodyPRIDistrictFreeze : isFreezedList) {
							   if(localbodyPRIDistrictFreeze.getLbStatus().equalsIgnoreCase("Status not set")){
								   localbodyPRIDistrictFreeze.setLbStatus("Unfreeze");
							   }
							   finalList.add(localbodyPRIDistrictFreeze);
						}
			        }
				/* if(!isDraftList.isEmpty()) {
					 mav = freezeDistrictTRD(freezeDistrictTRDBean,session, request);
					 mav.addObject("headingMessage", "draft mode");
					 mav.addObject("draftedOrFreezedVillages", isDraftList);
				 } else {*/
					// condition to check does localbody TRD is in freeze state if yes return list of villages otherwise return none 
				
					 if(!finalList.isEmpty()) {
						 mav = freezeDistrictTRD(freezeDistrictTRDBean,session, request);
						 mav.addObject("headingMessage", "Unfreeze/Draft");
						 mav.addObject("draftedOrFreezedVillages", finalList);
					 } else {
						 Boolean isFreezed = districtFreezeService.freezeUnFreezeDistrictPRIEntity(freezeDistrictTRDBean);
						 if( isFreezed ) {
							 mav.addObject("msgid", "Freezed Successfully");
							 mav.setViewName("success");
						 }
					 }
				 /*}*/
			} else if("UnFreeze".equals(freezeDistrictTRDBean.getProcessAction())) {
				if(StringUtils.isBlank(freezeDistrictTRDBean.getReason())) {
					mav = freezeDistrictTRD(freezeDistrictTRDBean,session, request);
					mav.addObject("errorReason", "Please enter a valid reason.");
					return mav;
				}
				Boolean isUnFreezed = districtFreezeService.freezeUnFreezeDistrictPRIEntity(freezeDistrictTRDBean);
				if (isUnFreezed) {
					mav.addObject("msgid", "Unfreezed Successfully");
				} else {
					mav.addObject("msgid", "It is Already in Unfreezed State");
				}
				mav.setViewName("success");
			}else {
				throw new Exception("Invalid Process Execution.");
			}
			log.info("DistrictTRDFreezeController.freezeDistrictPostRecords execution ends here.");
		} catch (Exception e) {
			log.error("Exception in DistrictTRDFreezeController.freezeDistrictPostRecords : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	

	/*
	 * Codes Started for LocalBody TRD 
	 * 
	 */
	
	@RequestMapping(value = "/freezeURBLB", method = RequestMethod.GET)
	public ModelAndView freezeDistrictURB(@ModelAttribute("freezeURB") FreezeDistrictBean freezeDistrictPRIBean,HttpSession session, HttpServletRequest request) {
		log.info("DistrictURBFreezeController.freezeDistrict execution started.");
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalAttributes(session);
			if( districtCode == null || districtCode == 0 ) {
				mav.addObject("msgid", "Invalid District User");
				mav.setViewName("success");
				return mav;
			}
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place
			// of 10
			char lbType = 'U';
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 21, lbType);

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			//String message = hMap.get("message");
			if (govtOrderConfig == null && mapConfig == null) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Urban doesn't exists in your state");
				return mav;
			} 
			FreezeDistrictBean districtDetails = districtFreezeService.getDestrictDetailsForURB(districtCode);
			mav.setViewName("view_freezeDistrictURB");
			mav.addObject("freezeDistrict", districtDetails);
			freezeDistrictPRIBean.setDistrictCode(districtDetails.getDistrictCode());
			freezeDistrictPRIBean.setDistrictNameEnglish(districtDetails.getDistrictNameEnglish());
			freezeDistrictPRIBean.setDistrictStatus(districtDetails.getDistrictStatus());
			freezeDistrictPRIBean.setStateStatus(districtDetails.getStateStatus());
			mav.addObject("userId", userId);
			log.info("DistrictURBFreezeController.freezeDistrict execution completed.");
		} catch (Exception e) {
			log.error("Exception in DistrictURBFreezeController.freezeDistrict : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	@RequestMapping(value = "/freezeDistrictURB", method = RequestMethod.POST)
	public ModelAndView freezeDistrictPostRecordsURB(HttpSession session, HttpServletRequest request,@ModelAttribute("freezeURB") FreezeDistrictBean freezeDistrictPRIBean,  BindingResult binding) {
		log.info("DistrictURBFreezeController.freezeDistrictPostRecords execution started.");
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalAttributes(session);
			if( districtCode == null || districtCode == 0 ) {
				mav.addObject("msgid", "Invalid District User");
				mav.setViewName("success");
				return mav;
			}
			nodalOfficerValidator.validateTokenError(binding,freezeDistrictPRIBean.getUserOTP(),userId.longValue());
			if(binding.hasErrors()){
				FreezeDistrictBean districtDetails = districtFreezeService.getDestrictDetailsForURB(districtCode);
				mav.setViewName("view_freezeDistrictURB");
				mav.addObject("freezeDistrict", districtDetails);
				freezeDistrictPRIBean.setDistrictCode(districtDetails.getDistrictCode());
				freezeDistrictPRIBean.setDistrictNameEnglish(districtDetails.getDistrictNameEnglish());
				freezeDistrictPRIBean.setDistrictStatus(districtDetails.getDistrictStatus());
				freezeDistrictPRIBean.setStateStatus(districtDetails.getStateStatus());
				freezeDistrictPRIBean.setUserOTP(null);
				mav.addObject("userId", userId);
				return mav;
			}
			freezeDistrictPRIBean.setUserOTP(null);
			freezeDistrictPRIBean.setDistrictCode(districtCode);
			freezeDistrictPRIBean.setUserId(userId);
			freezeDistrictPRIBean.setIpAddress(request.getRemoteAddr());
			if("Freeze".equals(freezeDistrictPRIBean.getProcessAction())) {
				// condition to check does localbody PRI is in draft villages if yes return list of villages otherwise return none 
				List<LocalbodyPRIDistrictFreeze> finalList= new ArrayList<LocalbodyPRIDistrictFreeze>();
				List<LocalbodyPRIDistrictFreeze> isDraftList = districtFreezeService.draftedVillagesPRIFromDistrict(districtCode,ApplicationConstant.LOCAL_BODY_URB_LEVEL_CODE);
				if(!isDraftList.isEmpty()){
					for (LocalbodyPRIDistrictFreeze localbodyPRIDistrictFreeze : isDraftList) {
						   localbodyPRIDistrictFreeze.setLbStatus("In Draft Mode");
						   finalList.add(localbodyPRIDistrictFreeze);
					}
				}
				// condition to check does localbody PRI is in freeze state if yes return list of villages otherwise return none
				List<LocalbodyPRIDistrictFreeze> isFreezedList = districtFreezeService.freezeUnFreezeDistrictEntityCheckByDistrictId(districtCode,ApplicationConstant.LOCAL_BODY_URB_LEVEL_CODE);
				 if(!isFreezedList.isEmpty()){
						for (LocalbodyPRIDistrictFreeze localbodyPRIDistrictFreeze : isFreezedList) {
							   if(localbodyPRIDistrictFreeze.getLbStatus().equalsIgnoreCase("Status not set")){
								   localbodyPRIDistrictFreeze.setLbStatus("Unfreeze");
							   }
							   finalList.add(localbodyPRIDistrictFreeze);
						}
			     }
				/* if(!isDraftList.isEmpty()) {
					 mav = freezeDistrictURB(freezeDistrictPRIBean,session, request);
					 mav.addObject("headingMessage", "draft mode");
					 mav.addObject("draftedOrFreezedVillages", isDraftList);
				 } else {*/
					// condition to check does localbody PRI is in freeze state if yes return list of villages otherwise return none 
					 
					 if(!finalList.isEmpty()) {
						 mav = freezeDistrictURB(freezeDistrictPRIBean,session, request);
						 mav.addObject("headingMessage", "Unfreeze/Draft");
						 mav.addObject("draftedOrFreezedVillages", finalList);
					 } else {   
						 Boolean isFreezed = districtFreezeService.freezeUnFreezeDistrictPRIEntity(freezeDistrictPRIBean);
						 if(isFreezed) {
							 mav.addObject("msgid", "Freezed Successfully");
							 mav.setViewName("success");
						 }
					 }
				 /*}*/
			}else if("UnFreeze".equals(freezeDistrictPRIBean.getProcessAction())) {
				if(StringUtils.isBlank(freezeDistrictPRIBean.getReason())) {
					mav = freezeDistrictURB(freezeDistrictPRIBean,session, request);
					mav.addObject("errorReason", "Please enter a valid reason.");
					return mav;
				}
				Boolean isUnFreezed = districtFreezeService.freezeUnFreezeDistrictPRIEntity(freezeDistrictPRIBean);
				if (isUnFreezed) {
					mav.addObject("msgid", "Unfreezed Successfully");
				} else {
					mav.addObject("msgid", "It is Already in Unfreezed State");
				}
				mav.setViewName("success");
			}else {
				throw new Exception("Invalid Process Execution.");
			}
			log.info("DistrictURBFreezeController.freezeDistrictPostRecords execution ends here.");
		} catch (Exception e) {
			log.error("Exception in DistrictURBFreezeController.freezeDistrictPostRecords : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	
	@RequestMapping(value = "/freezeDistrictPcAcMapping", method = RequestMethod.GET)
	public ModelAndView freezeDistrictPcAcMapping(HttpSession session, HttpServletRequest request,@ModelAttribute("mappedLbWardDisttrict") NodalOfficer nodal) {
		ModelAndView mav = new ModelAndView();
		Boolean isFreeze=false;
		setGlobalAttributes(session);
		if( districtCode == null || districtCode == 0 ) {
			mav.addObject("msgid", "Invalid District User");
			mav.setViewName("success");
			return mav;
		}
		try {
			 String path=districtFreezeService.isDistrictByNodalofficer(districtCode);
			 if(path!=null&& !path.isEmpty()){
				 isFreeze=true;
				 String pathView = path.substring(path.lastIndexOf(File.separatorChar)+1);
				 mav.addObject("pathView",pathView);
				 session.setAttribute("path", path);
				
			 }
			 intlizeModelAndView(mav,isFreeze);
		}  catch (Exception e) {
			log.error("Exception in DistrictFreezeController.freezeDistrictPcAcMapping : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
          return mav;
	}
	@RequestMapping(value = "/freezeDistrictPcAcMapping", method = RequestMethod.POST)
	public ModelAndView freezeDistrictPcAcMappingPost(HttpSession session, HttpServletRequest request ,
			@ModelAttribute("mappedLbWardDisttrict") NodalOfficer nodal,@RequestParam(value = "attachFile", required = false) CommonsMultipartFile file) {
		ModelAndView mav = new ModelAndView();
	
	   AttachedFilesForm attachedFilesForm=new AttachedFilesForm();
	   AttachmentMaster attachmentMaster=null;
	
		setGlobalAttributes(session);
		if( districtCode == null || districtCode == 0 ) {
			mav.addObject("msgid", "Invalid District User");
			mav.setViewName("success");
			return mav;
		}
		
		try {
			 File  serverFile=null;
			if(file!=null  && file.getSize()>0){
				if(file.getContentType().equalsIgnoreCase("application/pdf")){
					   attachmentMaster=addAttachmentService.getAttachmentMaster();
                        if(attachmentMaster!=null && !"".equals(attachmentMaster.getFileLocation())){
                        	File dir = new File(attachmentMaster.getFileLocation() + File.separator + "PcAcMappingFile");
                            byte[] bytes = file.getBytes();
                        if (!dir.exists())
                            dir.mkdirs();
                        
                        String districtName=districtService.getDistrictNameEnglishbyDistrictCode(districtCode);
                       System.out.println(districtName);
                       
                          serverFile = new File(dir.getAbsolutePath()
                                + File.separator +districtName+"("+ districtCode+")"+"PcAcMapping.pdf");
                        BufferedOutputStream stream = new BufferedOutputStream(
                                 new FileOutputStream(serverFile));
                         stream.write(bytes);
                         stream.close();
                         attachedFilesForm.setFileLocation(serverFile.toString());
                         attachedFilesForm.setFileName(serverFile.getName());
                         attachedFilesForm.setFileSize(file.getSize());
                         attachedFilesForm.setFileTimestampName(serverFile.getName());
                         attachedFilesForm.setFileType(file.getContentType());
                         Boolean isSave= districtFreezeService.saveFreezePcAcMappingOfDistrict(nodal,attachedFilesForm);
                   	    if(isSave){
                   			mav.setViewName("success");
                   	    mav.addObject("msgid", "Freezed Sucessfully");
                   	    } 
                   	    else{
                   	    intlizeModelAndView(mav,false);
                   		 mav.addObject("msgid", "Unable to Freeze PC/AC Mapping of District");
                   	}     
                    } else {
                    	 intlizeModelAndView(mav,false);
                    	mav.addObject("msg", "File location is not defined.");
                    }
                
                 
            }
				else{
					 intlizeModelAndView(mav,false);
                	 mav.addObject("msgid", "Invalid file,Please Upload only pdf file");	
				}
				
		} else{
			 intlizeModelAndView(mav,false);
        	 mav.addObject("msgid", "File not found");	
		}
		} catch (Exception e) {
			log.error("Exception in DistrictFreezeController.freezeDistrictPcAcMapping : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
	  return mav;
	}
	
	private void intlizeModelAndView(ModelAndView mav,Boolean isFeeze) throws Exception{
		    List<MappedLbWardDisttrictWise> mappedLbWardDisttrictWise=null;
			NodalOfficer nodalOfficer=null; 
	
			 nodalOfficer=districtFreezeService.freezeUnFreezeDistrictNodaloffice(districtCode);
		     mappedLbWardDisttrictWise=districtFreezeService.getMappedLbWardDisttrictWise(districtCode);
		     mav.addObject("nodalOfficer",nodalOfficer);
      	    mav.addObject("mappedLbWardDisttrictWise",mappedLbWardDisttrictWise);
        	 mav.setViewName("freeze_DistrictPcAc_mapping");
        	mav.addObject("isFreeze",isFeeze);
		
	 }
	@RequestMapping(value = "/unfreezeDistrictPcAcMapping", method = RequestMethod.POST)
	public ModelAndView unfreezeDistrictPcAcMapping(HttpSession httpSession, HttpServletRequest request ,
			@ModelAttribute("mappedLbWardDisttrict") NodalOfficer nodalOfficerbean,@RequestParam(value = "attachFile", required = false) CommonsMultipartFile file) {
		ModelAndView mav=new ModelAndView();
		Boolean unfeezed=false;
		try {
			 unfeezed=districtFreezeService.unfreezeDistrictPcAcMapping(nodalOfficerbean);
			if(unfeezed){
				 	mav = new ModelAndView("success");
					mav.addObject("msgid", "UnFreezed Sucessfully");
		}
			else{
				mav.addObject("msgid", "Unable to UnFreeze PC/AC Mapping of District");
				intlizeModelAndView(mav,true);
			}
		}catch (Exception e) {
			log.error("Exception in DistrictFreezeController.unfreezeDistrictPcAcMapping : ", e);
			mav = new ModelAndView(errorHandler(request, e));
		}
		return mav;
	}
	@RequestMapping(value = "/showPcAcMappingPDF", method = RequestMethod.GET)
	public HttpServletResponse pdfDistrictPcAcMapping(HttpSession httpSession, HttpServletRequest request,HttpServletResponse response,@ModelAttribute("mappedLbWardDisttrict") NodalOfficer nodal){
	String path=(String)httpSession.getAttribute("path");
		File file=new File(path);
		  response.setContentLength((int)file.length());
          response.setContentType(new MimetypesFileTypeMap().getContentType(file));
          response.addHeader("Content-Disposition","attachment; filename="+file.getName());
          ServletOutputStream sos=null;
          FileInputStream fis=null;
		try {
			sos = response.getOutputStream();
			 fis=new FileInputStream(file);
			 int data=0;
	          do {
	              data=fis.read();
	              if(data!=-1)
	                  sos.write(data);
	          } while(data!=-1);
	          fis.close();
	          sos.flush();
	          sos.close();
		} catch (Exception e) {
			log.error("Exception in DistrictPRIFreezeController.showPcAcMappingPDF : ", e);
		
		}
	    return response;
	}
}
