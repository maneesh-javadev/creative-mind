
package in.nic.pes.lgd.controllers;

import java.util.regex.*; 
import java.util.Scanner;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.rules.LocalBodyUtil;
import com.cmc.lgd.localbody.services.LocalBodyService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.LgdOrganisationListByLocation;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.NodalOfficerState;
import in.nic.pes.lgd.bean.NotificationLog;
import in.nic.pes.lgd.bean.ReportingForm;
import in.nic.pes.lgd.bean.ReportingIssue;
import in.nic.pes.lgd.bean.ReportingIssue.Level;
import in.nic.pes.lgd.bean.RequestedWebServicesForm;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.UserRegistration;
import in.nic.pes.lgd.bean.WsRegisteredIpEntity;
import in.nic.pes.lgd.bean.WsSubscribedApplicationEntity;
import in.nic.pes.lgd.bean.WsUserRegistrationForm;
import in.nic.pes.lgd.common.CaptchaValidator;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.common.MailService;
import in.nic.pes.lgd.common.SmsManager;
import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.constant.DraftUtils;
import in.nic.pes.lgd.forms.Response;
import in.nic.pes.lgd.forms.StateEntity;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.validator.CommonValidatorImpl;
import in.nic.pes.lgd.validator.WsUserRegistrationValidator;
import pes.attachment.util.AddAttachmentBean;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import ws.in.nic.pes.lgdws.services.WSService;

@Controller
public class WsUserController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(WsUserController.class);
	@Autowired
	FileUploadUtility fileUploadUtility;

	@Autowired
	StateService stateService;
	
	@Autowired
	WSService wsService;
	
	@Autowired
	MailService mailService;
	@Autowired
	SmsManager smsManager;
	
	@Autowired
	DraftUtils draftUtils;

	@Autowired
	WsUserRegistrationValidator wValidator;
	
	@Autowired
	private LocalBodyUtil localBodyUtil;
	
	@Autowired
	private CommonValidatorImpl commonValidator;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private LocalBodyService localBodyService;

	  public static final String DEFAULT_ENCODING = "UTF-8"; 
	    static BASE64Encoder enc = new BASE64Encoder();
	    static BASE64Decoder dec = new BASE64Decoder();
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
	
	private String userType;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		/*SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));*/
	}
	
	private void setGlobalParams(HttpSession session){
		if(session.getAttribute("sessionObject")!=null){
			SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
			stateCode = sessionObject.getStateId();
			districtCode = sessionObject.getDistrictCode();
			userId = sessionObject.getUserId();
			userType=sessionObject.getIsCenterorstate();
		}
	}

	
	@RequestMapping(value = "/wsUserRegistration", method = RequestMethod.GET)
	public ModelAndView createVillage(@ModelAttribute("createWsUser") WsUserRegistrationForm createWsUser,
			BindingResult result,  HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			List<State> stateList = null;
			List<LgdOrganisationListByLocation> centerlist = null;
			/*if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}*/
			stateList = stateService.getStateSourceList();
			centerlist = wsService.getOrganizationListByLocation(0, 0);
			mav = new ModelAndView("wsUser_RegistraionForm");
			mav.addObject("stateList", stateList);
			mav.addObject("centerlist", centerlist);
			mav.addObject("intialPage", true);
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
	
	@RequestMapping(value = "/wsUserRegistrationSubmit", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("createWsUser") WsUserRegistrationForm createWsUser,BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		ModelAndView mavOBJ;
		ModelAndView mav = null;
		List<State> stateList = null;
		//MailService mailService = new MailService();
		//SmsManager sms = new SmsManager();
		
		List<LgdOrganisationListByLocation> centerlist = null;
		try {
			
			stateList = stateService.getStateSourceList();
			centerlist = wsService.getOrganizationListByLocation(0, 0);
			mav = new ModelAndView("wsUser_RegistraionForm");
			String captchaAnswer = createWsUser.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			if ( !messageFlag ) {
				createWsUser.setCaptchaAnswer(null);
				mav.addObject("captchaFaliedMessage", messageFlag);
				mav.addObject("createWsUser", createWsUser);
				mav.addObject("stateList", stateList);
				mav.addObject("centerlist", centerlist);
				mav.addObject("errorPage", true);
				return mav;
			}
			wValidator.validate(createWsUser, result);
			//commonValidator.isValidMime(result, request, createWsUser.getFileUpload());
			if(result.getFieldError("attachFile1")!=null)
			result.rejectValue("fileUpload", "error.ms", "Attach doc is not of type gif,jpg,pdf,png,jpeg,pjpeg");
			if (result.hasErrors()) {
				mavOBJ = new ModelAndView("wsUser_RegistraionForm");
				mavOBJ.addObject("createWsUser", createWsUser);
				mavOBJ.addObject("stateList", stateList);
				mavOBJ.addObject("centerlist", centerlist);
				mavOBJ.addObject("countIP", createWsUser.getWsRegisteredIpEntity().size());
				mavOBJ.addObject("count", createWsUser.getWsSubscribedApplicationEntity().size());
				mavOBJ.addObject("errorPage", true);
				return mavOBJ;
			}
			if(createWsUser.isStateRadio()){
				createWsUser.setStateCode(createWsUser.getStateCode() == 0?null:createWsUser.getStateCode());
				createWsUser.setOrgUnitCode(createWsUser.getStateOrgUnitCode());
			}
			if(createWsUser.isCenterRadio()){
				createWsUser.setStateCode(null);
			}
			if(createWsUser.isIpCheck()){
				SecureRandom random = new SecureRandom();
				int num = random.nextInt(100000);
				String formatted = String.format("%05d", num); 
				createWsUser.setMobileWebToken(formatted);
				
			}
				
			createWsUser=draftUtils.uploadFileToServerForWsUserDetails(createWsUser,Long.parseLong( DraftConstant.ATTACHMENT_WEBSERVICES_DOC.toString()),request,httpSession);
			createWsUser.setRegistrationStatus('N');
			WsUserRegistrationForm wsUser = wsService.insertWsUserRegistrationDetails(createWsUser);
			String aMessage = "Web Service user details sucessfully saved.!";
			mavOBJ = new ModelAndView("successON");
			mavOBJ.addObject("msgid", aMessage);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mavOBJ = new ModelAndView(redirectPath);
			return mavOBJ;
		}
		return mavOBJ;
	}
	
	public static String base64encode(String text) {
        try {
            return enc.encode(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
	
	public static String base64decode(String text) {
        try {
            return new String(dec.decodeBuffer(text), DEFAULT_ENCODING);
        } catch (IOException e) {
            return null;
        }
    }
	
	@RequestMapping(value = "/wsUserRegistrationList", method = RequestMethod.GET)
	public ModelAndView userRegistrationList(@ModelAttribute("createWsUser") WsUserRegistrationForm createWsUser,HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
/*			int pageStart=createWsUser.getPageStart();
*/			List<UserRegistration>  registrationForms=wsService.getWsUserRegistrations();
			
			if(registrationForms!=null && registrationForms.size()>0){
				System.out.println("checkingSize"+registrationForms.size());
				for (UserRegistration wsUserRegistrationForm : registrationForms) {
					String uploadedFileName=wsUserRegistrationForm.getUploadFileName();
					if(uploadedFileName!=null){
						String lastIndex=uploadedFileName.substring(0,uploadedFileName.lastIndexOf("_")).concat(uploadedFileName.substring(uploadedFileName.lastIndexOf("."),uploadedFileName.length()));
						wsUserRegistrationForm.setFileName(lastIndex);
					}
				}
			}
			
			
			mav = new ModelAndView("wsUserRegistrationList");
			mav.addObject("registrationForms", registrationForms);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	/*@RequestMapping(value = "/wsUserRegistrationList", method = RequestMethod.POST)
	public ModelAndView getPaginationData(@ModelAttribute("createWsUser") WsUserRegistrationForm createWsUser,HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			int pageStart=createWsUser.getPageStart();
			List<UserRegistration>  registrationForms=wsService.getWsUserRegistrations(pageStart);
				for (UserRegistration wsUserRegistrationForm : registrationForms) {
					String uploadedFileName=wsUserRegistrationForm.getUploadFileName();
					if(uploadedFileName!=null){
						String lastIndex=uploadedFileName.substring(0,uploadedFileName.lastIndexOf("_")).concat(uploadedFileName.substring(uploadedFileName.lastIndexOf("."),uploadedFileName.length()));
						wsUserRegistrationForm.setFileName(lastIndex);
					}
			}
			mav = new ModelAndView("wsUserRegistrationList");
			mav.addObject("registrationForms", registrationForms);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}*/

	
	@RequestMapping(value = "/uploadUserFile", method = RequestMethod.GET)
	public void uploadFile(HttpServletResponse response,@RequestParam String fileName, HttpServletRequest request) {
		String isSuccess;
		try {
			isSuccess = localBodyUtil.downloadFileFromServer(fileName, Long.parseLong(LocalBodyConstant.ATTACHMENT_WEBSERVICES_DOC.toString()), request,response);
			if(!"SUCCESS".equals(isSuccess)){
				throw new Exception(isSuccess);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value = "/approveUserRegitration", method = RequestMethod.GET)
	public ModelAndView approvedUserRegistration(@ModelAttribute("createWsUser") WsUserRegistrationForm createWsUser,HttpServletResponse response,@RequestParam int userId,@RequestParam String type, HttpServletRequest request) {
		ModelAndView mav = null;
		String encriptedToken= "";
		String desc;
		try {
			
			UserRegistration wsUser = wsService.approveUserRegistration(userId, type);
			if( "A".equalsIgnoreCase(type) ){
				if(wsUser.getMobileWebToken() != null && !wsUser.getMobileWebToken().isEmpty() ){
					encriptedToken = base64encode(wsUser.getMobileWebToken());
					desc="Your request to access the Web Services from LGD has been approved.Token to consume web service for mobile app: "+encriptedToken;
					
				}else{
					desc="Your request to access the Web Services from LGD has been approved.";
				}
			}else{
				
				desc="Your request to access the Web Services from LGD has been rejected.";
			}
			
				
				if(wsUser.getEmail() != null && !wsUser.getEmail().isEmpty()){
					NotificationLog mailobj = new NotificationLog();
					mailobj.setMsgDesc(desc);
					mailobj.setSubject("Consuming LGD web services Approval");
					mailobj.setEmailId(wsUser.getEmail());
					mailobj.setRegistrationSrno(wsUser.getUserRegistrationId());
					mailService.sendMailNotification(mailobj);
				}
				if(wsUser.getMobile() != null && !wsUser.getMobile().isEmpty()){
					NotificationLog smsobj = new NotificationLog();
					smsobj.setMsgDesc(desc);
					smsobj.setMobileNo(wsUser.getMobile());
					smsobj.setRegistrationSrno(wsUser.getUserRegistrationId());
					smsManager.smsNotification(smsobj);
				}
			
			
			
/*			int pageStart=createWsUser.getPageStart();
*/			List<UserRegistration>  registrationForms=wsService.getWsUserRegistrations();
			for (UserRegistration wsUserRegistrationForm : registrationForms) {
				String uploadedFileName=wsUserRegistrationForm.getUploadFileName();
				if(uploadedFileName!=null){
					String lastIndex=uploadedFileName.substring(0,uploadedFileName.lastIndexOf("_")).concat(uploadedFileName.substring(uploadedFileName.lastIndexOf("."),uploadedFileName.length()));
					wsUserRegistrationForm.setFileName(lastIndex);
				}
			}
			mav = new ModelAndView("wsUserRegistrationList");
			mav.addObject("registrationForms", registrationForms);
			mav.addObject("sucess", true);
			mav.addObject("type", type);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/userRegistratinService", method = RequestMethod.GET)
	public ModelAndView registrationUserService(HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			List<State> stateList = null;
			List<LgdOrganisationListByLocation> centerlist = null;
			stateList = stateService.getStateSourceList();
			centerlist = wsService.getOrganizationListByLocation(0, 0);
			mav = new ModelAndView("userRegistrationService");
			mav.addObject("stateList", stateList);
			mav.addObject("centerlist", centerlist);
			mav.addObject("intialPage", true);
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
	
	
	@RequestMapping(value = "/userLoginForm", method = RequestMethod.GET)
	public ModelAndView userLoginForm(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView modelAndView = null;
		try {
			Object objId=request.getSession().getAttribute("userId");
			Object objName=request.getSession().getAttribute("userName");
			if(objId!=null) {
				request.getSession().removeAttribute("userId");
			}
			if(objName!=null) {
				request.getSession().removeAttribute("userName");
			}
			 modelAndView=new ModelAndView("otherServicesLogin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/generateOtpRegistration", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveUnitLevelList(@RequestBody UserRegistration userRegistration,HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> dataMap=new HashMap<String,Object>();
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			UserRegistration userRegistrations = objectMapper.convertValue(userRegistration, new TypeReference<UserRegistration>() { });
			UserRegistration userDetails=null;
			userDetails=wsService.getRegistrationDetails(userRegistrations.getMobile());
			if(userDetails!=null){
				dataMap.put("msg", "Mobile Number is already registered");
				dataMap.put("responseRedirect", false);
				return dataMap;
			}
			userDetails=wsService.getRegistrationDetails(userRegistrations.getEmail());
			if(userDetails!=null){
				dataMap.put("msg", "Email-id is already registered");
				dataMap.put("responseRedirect", false);
				return dataMap;
			}
			dataMap.put("responseRedirect", true);
			SecureRandom random = new SecureRandom();
			int num = random.nextInt(100000);
			String formatted = String.format("%05d", num);
			sendOTP(formatted, userRegistrations.getMobile());
			dataMap.put("otp", formatted);
			request.getSession().setAttribute("registrdOTP", base64encode(formatted));
			if(userRegistrations.getEmail() != null && !userRegistrations.getEmail().isEmpty()){
				NotificationLog mailobj = new NotificationLog();
				mailobj.setMsgDesc("Your OTp is "+formatted+" for registration in LGD site this is valid for the next 24 hrs.");
				mailobj.setSubject("Your registration has been sucessfully done");
				mailobj.setEmailId(userRegistrations.getEmail());
				System.out.println("sendMailNotification started");
				//mailobj.setRegistrationSrno(wsUser.getWsUserId());
				mailService.sendMail(mailobj.getEmailId(), mailobj.getSubject(), mailobj.getMsgDesc());
				
			}
		} catch (Exception e) {
			dataMap.put("error", "Error While Inserting Data");
			e.printStackTrace();
		}
		return dataMap;
	}
	
	@RequestMapping(value = "/regenerateOTP", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> reGenerateOTP(@RequestBody UserRegistration userRegistration,HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> dataMap=new HashMap<String,Object>();
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			UserRegistration userRegistrations = objectMapper.convertValue(userRegistration, new TypeReference<UserRegistration>() { });
			UserRegistration userDetails=null;
			userDetails=wsService.getRegistrationDetails(userRegistrations.getMobile());
			if(userDetails==null){
				dataMap.put("msg", "You are not a registered user");
				dataMap.put("responseRedirect", false);
				return dataMap;
			}
			dataMap.put("responseRedirect", true);
			SecureRandom random = new SecureRandom();
			int num = random.nextInt(100000);
			String formatted = String.format("%05d", num);
			if(userDetails.getEmail() != null && !userDetails.getEmail().isEmpty()){
				NotificationLog mailobj = new NotificationLog();
				mailobj.setMsgDesc("Your OTp is "+formatted+" for registration in LGD site this is valid for the next 24 hrs.");
				mailobj.setSubject("Your registration has been sucessfully done");
				mailobj.setEmailId(userDetails.getEmail());
				//mailobj.setRegistrationSrno(userDetails.getWsUserId());
				mailService.sendMail(mailobj.getEmailId(), mailobj.getSubject(), mailobj.getMsgDesc());
			}
			sendOTP(formatted, userDetails.getMobile());
			userDetails.setGeneratedOTP(formatted);
			wsService.saveUserRegistrationDetails(userDetails);
			dataMap.put("otp", formatted);
			request.getSession().setAttribute("registrdOTP", base64encode(formatted));
		} catch (Exception e) {
			dataMap.put("error", "Error While Inserting Data");
			e.printStackTrace();
		}
		return dataMap;
	}
	
	
	
	
	@RequestMapping(value = "/getCenterList", method = RequestMethod.GET)
	public @ResponseBody List<LgdOrganisationListByLocation> getCenterLsit(HttpServletResponse response, HttpServletRequest request) {
		List<LgdOrganisationListByLocation> centerlist = null;
		try {
			centerlist = wsService.getOrganizationListByLocation(0, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return centerlist;
	}
	
	@RequestMapping(value = "/getStateList", method = RequestMethod.GET)
	public @ResponseBody List<StateEntity> getStateList(HttpServletResponse response, HttpServletRequest request) {
		List<State> stateList = null;
		List<StateEntity> stateForms = new ArrayList<StateEntity>();
		try {
			stateList = stateService.getStateSourceList();
			for (State state : stateList) {
				StateEntity form=new StateEntity();
				form.setStateCode(state.getStateCode());
				form.setStateNameEnglish(state.getStateNameEnglish());
				stateForms.add(form);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stateForms;
	}
	
	private synchronized void sendOTP(String otp,String pnos)throws Exception{
		String message = "Your OTp is <otpno> for registration in LGD site this is valid for the next 24 hrs.";
		/*String otp =base64decode(encodeotp);*/
		message = message.replace("<otpno>",otp);
		SmsManager sms = new SmsManager();
		try{
		sms.makeHTTPConnection(pnos, message);
		}catch(Exception e){
			log.error(e);
		}
	}
	
	@RequestMapping(value = "/saveUserRegisterData", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveUserRegistrationDetails(@RequestBody UserRegistration userRegistrations,HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> dataMap=new HashMap<String,Object>();
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			dataMap.put("newRegistr", false);
//			UserRegistration userRegistrations = objectMapper.convertValue(userRegistration, new TypeReference<UserRegistration>() { });
			String encodedOTP=(String) request.getSession().getAttribute("registrdOTP");
			if(!base64decode(encodedOTP).equals(userRegistrations.getGeneratedOTP())){
				dataMap.put("msg", "You have entered incorrect OTP");
				return dataMap;
			}
			userRegistrations.setRegisteredOn(new Date(System.currentTimeMillis()));
			wsService.saveUserRegistrationDetails(userRegistrations);
			dataMap.put("newRegistr", true);
			dataMap.put("msg", "user registration has been done successfully");
			UserRegistration userDetails=wsService.getRegistrationDetails(userRegistrations.getMobile());
			request.getSession().setAttribute("userId",userDetails.getUserRegistrationId());
			request.getSession().setAttribute("userName",userDetails.getName());
		} catch (Exception e) {
			dataMap.put("error", "Error While Inserting Data");
			e.printStackTrace();
		}
		return dataMap;
	}
	
	@RequestMapping(value = "/orgListBasedOnState", method = RequestMethod.POST)
	public @ResponseBody List<LgdOrganisationListByLocation> getOrganisationBasedOnState(@RequestBody int stateCode) {
		List<LgdOrganisationListByLocation> orgStateList=null;
		try {
			orgStateList = wsService.getOrganizationListByLocation(stateCode, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgStateList;
	}
	
	
	@RequestMapping(value = "/validateCredintial", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> loginCredintialValidate(@RequestBody UserRegistration userRegistration,HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> dataMap=new HashMap<String,Object>();
		dataMap.put("responseRedirect", true);
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			UserRegistration userRegistrations = objectMapper.convertValue(userRegistration, new TypeReference<UserRegistration>() { });
			UserRegistration userDetails=wsService.getRegistrationDetails(userRegistrations.getMobile());
			if(userDetails==null){
				dataMap.put("msg", "You are not a registered user");
				dataMap.put("responseRedirect", false);
				return dataMap;
			}
			
			if(userRegistrations.getGeneratedOTP()!=null) {
				if(userDetails!=null&& !(userRegistrations.getGeneratedOTP().equals(userDetails.getGeneratedOTP()))){
					dataMap.put("msg", "You have entered incorrect OTP");
					dataMap.put("responseRedirect", false);
					return dataMap;
				}
			}else {
				dataMap.put("msg", "You can't left OTP blank");
				dataMap.put("responseRedirect", false);
				return dataMap;
			}
			dataMap.put("msg", "Login Sucessfully");
			request.getSession().setAttribute("userId",userDetails.getUserRegistrationId());
			request.getSession().setAttribute("userName",userDetails.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	@RequestMapping(value = "/userOtherServices", method = RequestMethod.GET)
	public ModelAndView lgdOtherServices(HttpServletResponse response,Model model, HttpServletRequest request) {
		ModelAndView modelAndView = null;
		try {
			if(request.getSession().getAttribute("userId")!=null){
				int userId = (Integer) request.getSession().getAttribute("userId");
				String userName = (String) request.getSession().getAttribute("userName");
				model.addAttribute("userId",userId);
				model.addAttribute("userName",userName);
			}
			 modelAndView=new ModelAndView("lgdOtherServices");
			 /*response.sendRedirect("/LGD/userLoginForm.do");*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/requestWebServices", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> requertConsumeWebService( @RequestParam(value ="file",required = false) List<CommonsMultipartFile> file,
			@RequestParam("applicationNameList") String applicationNameList,@RequestParam("ipAddressList") String ipAddressList,
			@RequestParam("mobileAppCheck") Boolean mobileAppCheck,
			HttpSession session, HttpServletRequest request) {
		List<WsRegisteredIpEntity> ipEntities=new ArrayList<WsRegisteredIpEntity>();
		List<WsSubscribedApplicationEntity> applicationEntities=new ArrayList<WsSubscribedApplicationEntity>();
		Map<String, Object> dataMap=new HashMap<String,Object>();
		try {
			
			
			String validateAttachmentMessage =this.invalidAttchment(file, Long.parseLong(LocalBodyConstant.ATTACHMENT_WEBSERVICES_DOC.toString()));
			if(! LocalBodyConstant.SUCCESS_MESSAGE.toString().equals(validateAttachmentMessage)){
				dataMap.put("status", false);
				dataMap.put("gazettePublicationUpload", validateAttachmentMessage);
				 return dataMap;
			}
			
			int userId = (Integer) request.getSession().getAttribute("userId");
			UserRegistration registration=wsService.getRegistration(userId);
			WsUserRegistrationForm createWsUser=new WsUserRegistrationForm();
			
			
			
			
			if(file!=null) {
				createWsUser.setFileUpload(file);
				createWsUser=draftUtils.uploadFileToServerForWsUserDetails(createWsUser,Long.parseLong( DraftConstant.ATTACHMENT_WEBSERVICES_DOC.toString()),request,session);
				
			}
			if(mobileAppCheck){
				SecureRandom random = new SecureRandom();
				int num = random.nextInt(100000);
				String formatted = String.format("%05d", num); 
				registration.setMobileWebToken(formatted);
			}
			JSONArray jsonarrayIp = new JSONArray(ipAddressList);
			JSONArray jsonarrayApplication = new JSONArray(applicationNameList);
			for (int i = 0; i < jsonarrayIp.length(); i++) {
				WsRegisteredIpEntity registeredIpEntity=new WsRegisteredIpEntity();
			    JSONObject jsonobject = jsonarrayIp.getJSONObject(i);
			    registeredIpEntity.setIpAddress(jsonobject.getString("ipAddress"));
			    registeredIpEntity.setUserRegistration(registration);
			    ipEntities.add(registeredIpEntity);
			}
			for (int i = 0; i < jsonarrayApplication.length(); i++) {
				WsSubscribedApplicationEntity applicationEntity=new WsSubscribedApplicationEntity();
			    JSONObject jsonobject = jsonarrayApplication.getJSONObject(i);
			    applicationEntity.setApplicationName(jsonobject.getString("applicationName"));
			    applicationEntity.setUserRegistration(registration);
			    applicationEntities.add(applicationEntity);
			}
			registration.setWsRegisteredIpEntity(ipEntities);
			registration.setWsSubscribedApplicationEntity(applicationEntities);
			if(file!=null) {
				registration.setUploadFileName(createWsUser.getUploadFileName());
			}
			wsService.saveRequestWebService(registration);
			dataMap.put("status", true);
		}
		catch(Exception exception){
			exception.printStackTrace();
			 dataMap.put("status", false);
			 dataMap.put("errorMessage",exception.getMessage());
			 return dataMap;
		}
		return dataMap;
		
	}
	
	
	@RequestMapping(value = "/userReportIssues", method = RequestMethod.GET)
	public ModelAndView reportIssue(HttpServletResponse response,Model model, HttpServletRequest request) {
		ModelAndView modelAndView = null;
		try {
			 modelAndView=new ModelAndView("reportingIssuesForm");
			 /*response.sendRedirect("/LGD/userLoginForm.do");*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/createNewReportingIssue", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> generateIssue(@RequestBody ReportingIssue reportingIssue,HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> dataMap=new HashMap<String,Object>();
		List<ReportingIssue> reportingIssuesList = null;
		List<ReportingForm> reportingForms = new ArrayList<ReportingForm>();
		try {
			int userId = (Integer) request.getSession().getAttribute("userId");
			ObjectMapper objectMapper=new ObjectMapper();
			ReportingIssue issue = objectMapper.convertValue(reportingIssue, new TypeReference<ReportingIssue>() { });
			issue.setUserId(userId);
			issue.setSubmittedFromIp(request.getRemoteAddr());
			wsService.saveReportingIssue(issue);
			reportingIssuesList=wsService.reportingIssueList(userId);
			for (ReportingIssue reporting : reportingIssuesList) {
				ReportingForm form=new ReportingForm();
				form.setIssueId(reporting.getSerialNumber());
				form.setRepliedOn(reporting.getRepliedOn());
				form.setReportedIssueText(reporting.getReportedIssueText());
				form.setReplyText(reporting.getReplyText());
				if(reportingIssue.getNodalOfficer()!=null){
					form.setStateName(reporting.getNodalOfficer().getState().getStateNameEnglish());
					form.setUserType(reporting.getNodalOfficer().getUserType());
				}
				else{
					form.setStateName(stateService.getStateNameEnglish(reporting.getSuubmittedToState()));
					form.setUserType(reporting.getSubmittedToType());
				}
				reportingForms.add(form);
			}
			dataMap.put("reportingIssues", reportingForms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	@RequestMapping(value = "/listOfReportingIssue", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getListOfReportingIssue(HttpServletResponse response,Model model, HttpServletRequest request) {
		List<ReportingIssue> reportingIssues = null;
		List<ReportingForm> reportingForms = new ArrayList<ReportingForm>();
		Map<String, Object> dataObject=new HashMap<String,Object>();
		List<LgdOrganisationListByLocation> centerlist = null;
		try {
			int userId = (Integer) request.getSession().getAttribute("userId");
			reportingIssues=wsService.reportingIssueList(userId);
			List<Level> issueType = Arrays.asList(ReportingIssue.Level.values());
			dataObject.put("issueType", issueType);
			centerlist = wsService.getOrganizationListByLocation(0, 0);
			for (ReportingIssue reportingIssue : reportingIssues) {
				ReportingForm form=new ReportingForm();
				form.setIssueId(reportingIssue.getSerialNumber());
				form.setRepliedOn(reportingIssue.getRepliedOn());
				form.setReportedIssueText(reportingIssue.getReportedIssueText());
				form.setReplyText(reportingIssue.getReplyText());
				if(reportingIssue.getNodalOfficer()!=null){
					form.setStateName(reportingIssue.getNodalOfficer().getState().getStateNameEnglish());
					form.setUserType(reportingIssue.getNodalOfficer().getUserType());
				}
				else{
					
					if(reportingIssue.getSuubmittedToState()==0){
						for (LgdOrganisationListByLocation lgdOrganisationListByLocation : centerlist) {
							if(lgdOrganisationListByLocation.getOrgUnitCode().equals(reportingIssue.getSubmittedToCenter())){
								form.setStateName(lgdOrganisationListByLocation.getOrgUnitName());
							}
						}
					}
					else{
						form.setStateName(stateService.getStateNameEnglish(reportingIssue.getSuubmittedToState()));
					}
					form.setUserType(reportingIssue.getSubmittedToType());
				}
				reportingForms.add(form);
			}
			dataObject.put("reportingIssues", reportingForms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataObject;
	}
	
	
	@RequestMapping(value = "/getRequestedWebServices", method = RequestMethod.GET)
	public @ResponseBody RequestedWebServicesForm getListOfConsumeWebServices(HttpServletResponse response,Model model, HttpServletRequest request) {
		UserRegistration registration=null;
		RequestedWebServicesForm requestedWebServicesForm=new RequestedWebServicesForm();
		List<String> applicationNames=new ArrayList<String>();
		List<String> ipAddress=new ArrayList<String>();
		try {
			int userId = (Integer) request.getSession().getAttribute("userId");
			 registration=wsService.getRegistration(userId);
			 List<WsRegisteredIpEntity> ipEntities=registration.getIpAddressList();
			 List<WsSubscribedApplicationEntity> applicationEntities=registration.getApplicationName();
			for (WsSubscribedApplicationEntity wsSubscribedApplicationEntity : applicationEntities) {
				applicationNames.add(wsSubscribedApplicationEntity.getApplicationName());
			}
			requestedWebServicesForm.setApplicationName(applicationNames);
			for (WsRegisteredIpEntity ipEntity : ipEntities) {
				ipAddress.add(ipEntity.getIpAddress());
			}
			requestedWebServicesForm.setIpAddressList(ipAddress);
			if(registration.getMobileWebToken()!=null && registration.getMobileWebToken()!=""){
				requestedWebServicesForm.setMobileApp(true);
			}
			if(registration!=null && registration.getUploadFileName()!=null) {
				requestedWebServicesForm.setFileName(registration.getUploadFileName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestedWebServicesForm;
	}
	
	
	@RequestMapping(value = "/getReportingIsses", method = RequestMethod.GET)
	public ModelAndView reportingIssueList(@ModelAttribute("reportingIssue") ReportingIssue reportingIssue,HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		List<ReportingIssue> reportingIssues = null;
		try {
			
			int pageStart=reportingIssue.getPageStart();
			if("S".equalsIgnoreCase(userType)){
				NodalOfficerState nodalOfficerState=commonService.getNodalOfficerDetails(userId);
				reportingIssues=wsService.stateReportedIssue(nodalOfficerState.getNodalOfficerId());
				if(reportingIssues == null || reportingIssues.size()==0){
					reportingIssues=wsService.stateReportedIssue(nodalOfficerState.getState().getStateCode(),nodalOfficerState.getUserType());
				}
			}
			else{
				reportingIssues=wsService.stateReportedIssue(0);
			}
			mav = new ModelAndView("reportingIssuesList");
			mav.addObject("reportingIssue", reportingIssues);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value = "/replyToReportedIssue", method = RequestMethod.POST)
	public ModelAndView replyToReportedIssue(@ModelAttribute("reportingIssue") ReportingIssue reportingIssue,HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		List<ReportingIssue> reportingIssues = null;
		try {
			wsService.replyReportedIssue(reportingIssue);
			mav = new ModelAndView("reportingIssuesList");
			NodalOfficerState nodalOfficerState=commonService.getNodalOfficerDetails(userId);
			reportingIssues=wsService.stateReportedIssue(nodalOfficerState.getNodalOfficerId());
			mav.addObject("reportingIssue", reportingIssues);
			mav.addObject("sucess", true);
			}
		 catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	

	@RequestMapping(value = "/saveSubscriptionDetails", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> sendNotification(@RequestBody EmailNotification emailNotification,HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> dataMap=new HashMap<String,Object>();
		try {
			int userId = (Integer) request.getSession().getAttribute("userId");
			emailNotification.setUserid(userId);
			wsService.saveEmailSmsNotification(emailNotification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	@RequestMapping(value = "/getSubscriberUser", method = RequestMethod.GET)
	public @ResponseBody EmailNotification getSubscriberUsers(HttpServletResponse response, HttpServletRequest request) {
		EmailNotification emailNotifications=null;
		try {
			int userId = (Integer) request.getSession().getAttribute("userId");
			emailNotifications=wsService.getSubscriberUserDetails(userId);
			if(emailNotifications!=null){
				return emailNotifications;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailNotifications;
	}
	
	
	
	@RequestMapping(value = "/getTestEmailNSMSService", method = RequestMethod.GET)
	public ModelAndView getTestEmailNSMSService(HttpServletResponse response,Model model, HttpServletRequest request) {
		ModelAndView modelAndView = null;
		try {
			 modelAndView=new ModelAndView("wsTestSMSNWebService");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/testSMSandMailService", method = RequestMethod.GET)
	private @ResponseBody Response fetchCapacityBuildingActivityGPs(String mailIds,String pnos) {
		return commonService.sendMailSMS(mailIds, pnos);
		
	}
	
	private String invalidAttchment(List<CommonsMultipartFile> attachmentList, Long fileMasterId) throws Exception{
		AttachmentMaster master = localBodyService.getUploadFileConfigurationDetails(fileMasterId);
		AddAttachmentBean attachmentBean = new AddAttachmentBean();
		attachmentBean.setUploadLocation(master.getFileLocation());
		attachmentBean.setCurrentlyUploadFileList(attachmentList);
		attachmentBean.setAllowedTotalNoOfAttachment(master.getFileLimit());// 4.Allowed Total Number Of Attachment.
		attachmentBean.setAllowedTotalFileSize(master.getTotalFileSize());// 5.Allowed
		attachmentBean.setAllowedIndividualFileSize(master.getIndividualFileSize());// 6.Allowed Individual File Size.
		attachmentBean.setNoOFAlreadyAttachedFiles(0);// 7.Number Of Already Attached File.
		attachmentBean.setTotalSizeOFAlreadyAttachedFiles(0l);// 8.Already attached file total size.
		attachmentBean.setMimeTypeList(localBodyService.fetchMimeType());// 9.File Mime Type List.
		attachmentBean.setNoOFMandatoryFile(0);// 13.Number of Mandatory file.
		
		SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
		String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
		if (!"validationSuccessFullyDone".equals(validateAttachment)) {
			return validateAttachment;
		}
		return LocalBodyConstant.SUCCESS_MESSAGE.toString();
	}

	
}
		
	

