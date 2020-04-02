package in.nic.pes.lgd.controllers;

import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.FreezeDistrictBean;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LBFreezeUnfreeze;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyListbyStateold;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.StandardCodes;
import in.nic.pes.lgd.bean.StateFreeze;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.LBFreezeForm;
import in.nic.pes.lgd.service.DistrictFreezeService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.service.ReportService;
import in.nic.pes.lgd.service.StateDistrictFreezeService;
import in.nic.pes.lgd.validator.NodalOfficerValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

/**
 * @Author Kirandeep added on 1 Feb 2015 for stateFreeeze
 * 
 */

@Controller
public class StateDistrictFreezeController { // NO_UCD (unused code)

	private static final Logger log = Logger.getLogger(StateDistrictFreezeController.class);

	@Autowired
	private StateDistrictFreezeService stateDistrictFService;

	@Autowired
	LocalGovtBodyService localGovtBodyService;

	@Autowired
	LocalGovtSetupService localGovtSetupService;

	@Autowired
	DistrictService districtService;

	@Autowired
	private StateDAO stateDao;

	@Autowired
	ReportService reportService;

	@Autowired
	private GovernmentOrderService govtOrderService;

	private Integer stateCode;

	private Integer districtCode;

	private Integer userId;
	
	@Autowired
	private DistrictFreezeService districtFreezeService;
	
	@Autowired
	private NodalOfficerValidator nodalOfficerValidator;

	
	public void globalParam(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId().intValue();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		globalParam(session);
	}

	@RequestMapping(value = "/stateUserFreeze", method = RequestMethod.GET)
	public ModelAndView getStateUserFreeze(HttpSession session, @ModelAttribute("stateFreeze") StateFreeze stateFreeze, BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("stateUserFreeze");
		try {
			globalParam(session);
			Map<String, Object> mapStateFreeze = stateDistrictFService.getAllAttributeForStateFreeze(stateCode, 'L');
			setMapAttributes(mapStateFreeze, mav);
			stateFreeze.setLbLrType('L');
			mav.addObject("stateFreeze", stateFreeze);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}

	@RequestMapping(value = "/allStateFreeze", method = RequestMethod.POST)
	public ModelAndView modifyState(@ModelAttribute("stateFreeze") StateFreeze stateFreeze, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("success");

			if (!"".equalsIgnoreCase(stateFreeze.getDcodes())) {
				/* added by sunita on 16-07-2015 */
				Character lbLrType = stateFreeze.getLbLrType();
				boolean check = stateDistrictFService.freezeUnFreezeFromState(stateFreeze.getDcodes(), userId, request.getRemoteAddr().toString(), 'D', lbLrType);
				if (check) {
					model.addAttribute("msgid", "Records Updated Succesfully");
				} else {
					model.addAttribute("msgid", "Records NOT Updated Succesfully");
				}
			} else {
				model.addAttribute("msgid", "Records NOT Updated Succesfully");
			}

			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/freezeState", method = RequestMethod.POST)
	public ModelAndView stateFreeze(@ModelAttribute("stateFreeze") StateFreeze stateFreeze, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("success");
			

			boolean chkStatus = stateDistrictFService.checkSaveStateStatus(stateCode, 'L');
			if (chkStatus) {
				String list = null;
				String msg = null;
				if (stateFreeze.getStatusByStateUser() == 1) {
					list = stateCode + "_F_NA:";
					msg = "State Freeze Successfully";
				}
				if (stateFreeze.getStatusByStateUser() == 0) {
					list = stateCode + "_U_NA:";
					msg = "State unfreeze Successfully";
				}
				/* added by sunita 15-07-2015 */
				Character lbLrType = stateFreeze.getLbLrType();
				boolean checkStateStatus = stateDistrictFService.freezeUnFreezeFromState(list, userId, request.getRemoteAddr().toString(), 'S', lbLrType);
				if (checkStateStatus) {
					model.addAttribute("msgid", msg);
				} else {
					model.addAttribute("msgid", "State Can Not be Freezed ");
				}
			} else if (chkStatus && stateFreeze.getStatusByStateUser() == 0) {
				model.addAttribute("msgid", "Can not Freeze State Districts Are not Freezed Yet");
			}

			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	public void setMapAttributes(java.util.Map<String, Object> map, ModelAndView mav) {
		for (String key : map.keySet()) {
			mav.addObject(key, map.get(key));
		}
	}

	/**
	 * @author Maneesh Kumar 01-July-2015
	 * @param model
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/localBodyFreeze", method = RequestMethod.GET)
	public ModelAndView lbFreeze(Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("lbFreeze");
		try {
			globalParam(httpSession);
			model.addAttribute("getLocalGovtSetupList", localGovtSetupService.getLocalbodyDetail(stateCode));
			if (districtCode != null && districtCode > 0) {
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("districtUser", true);
			} else {
				model.addAttribute("districtUser", false);
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
	 * @author Maneesh Kumar 01-July-2015
	 * @param model
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/localBodyFreezeBack", method = RequestMethod.POST)
	public ModelAndView lbFreezeBack(Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("lbFreeze");
		try {
			model.addAttribute("getLocalGovtSetupList", localGovtSetupService.getLocalbodyDetail(stateCode));
			if (districtCode != null && districtCode > 0) {
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("districtUser", true);
			} else {
				model.addAttribute("districtUser", false);
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
	 * @author Maneesh Kumar 01-July-2015
	 * @param parentLocalbodyCode
	 * @param lbTypeCode
	 * @param category
	 * @param model
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/localBodyFreezePost", method = RequestMethod.POST)
	public ModelAndView localBodyFreezePost(@RequestParam("parentLocalbodyCode") Integer parentLocalbodyCode, @RequestParam("lbTypeCode") Integer lbTypeCode, @RequestParam("category") String category, @RequestParam("formCategory") String formCategory, Model model, HttpServletRequest request,
			HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			model.addAttribute("freezeDataForLocalbodyList", stateDistrictFService.getFreezeLocalbodybyState(parentLocalbodyCode, lbTypeCode, stateCode, districtCode));
			model.addAttribute("titleMeaage", parentLocalbodyCode == 0 ? reportService.getLbHierchicalDetail(lbTypeCode, stateCode, false) : reportService.getLbHierchicalDetail(parentLocalbodyCode, stateCode, true));
			model.addAttribute("category", category);
			model.addAttribute("formCategory", formCategory);
			mav = new ModelAndView("localBodyFreezePost");
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/alllbfreeze", method = RequestMethod.POST)
	public ModelAndView localBodyFreezePost2(@ModelAttribute("localBodyFreeze") LBFreezeForm lbfreeze, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = null;

		try {
			mav = new ModelAndView("success");
			String allList = lbfreeze.getAllData();
			boolean checkStateStatus = stateDistrictFService.freezeLocalBody(allList);
			if (checkStateStatus) {
				model.addAttribute("msgid", "Selected Local Bodies Successfully Freezed/Unfreezed. !!! ");
			} else {
				model.addAttribute("msgid", "Local Body Not Freezed Successfully!! ");
			}

			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	/**
	 * @author kirandeep for pri freeze
	 * 
	 */

	@RequestMapping(value = "/freezUnfreezPRI", method = RequestMethod.GET)
	public ModelAndView getfreezeUnfreezPRI(HttpSession session, @ModelAttribute("stateFreeze") StateFreeze stateFreeze, BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("freezUnfreezPRI");
		try {
			globalParam(session);
			/* added by kirandeep for freeze/unfreeze issues */
			Map<String, String> hMap = new HashMap<String, String>();
			char lbType = 'P';
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 20, lbType);

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			// String message = hMap.get("message");
			if (govtOrderConfig == null && mapConfig == null) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "PRI doesn't exists in your state");
				return mav;
			}

			Map<String, Object> mapStateFreeze = stateDistrictFService.getAllAttributeForStateFreeze(stateCode, 'P');
			setMapAttributes(mapStateFreeze, mav);
			stateFreeze.setLbLrType('P');
			mav.addObject("stateFreeze", stateFreeze);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}

	/**
	 * @author kirandeep for pri,tri,urb freeze post district
	 * 
	 */

	@RequestMapping(value = "/alldistrictFreezePri", method = RequestMethod.POST)
	public ModelAndView freezeUnfreezePri(Model model, HttpServletRequest request, HttpSession session,@ModelAttribute("stateFreeze") StateFreeze stateFreeze,  BindingResult binding) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("success");
			Character userType=stateFreeze.getLbLrType();
			 
			nodalOfficerValidator.validateTokenError(binding,stateFreeze.getUserOTP(),userId.longValue());
			if(binding.hasErrors()){
				switch(userType){
				case 'P':
					mav=new ModelAndView("freezUnfreezPRI");
					break;
				case 'T':
					mav=new ModelAndView("freezUnfreezTri");
					break;
				case 'U':
					mav=new ModelAndView("freezUnfreezUrb");
					break;
				
				}
				Map<String, Object> mapStateFreeze = stateDistrictFService.getAllAttributeForStateFreeze(stateCode, userType);
				setMapAttributes(mapStateFreeze, mav);
				stateFreeze.setLbLrType(userType);
				mav.addObject("stateFreeze", stateFreeze);
				mav.addObject("userId", userId);
				return mav;
			}
			
			if (!"".equalsIgnoreCase(stateFreeze.getDcodes())) {
				Character lbLrType = stateFreeze.getLbLrType();
				boolean check = stateDistrictFService.freezeUnFreezeFromStateForPri(stateFreeze.getDcodes(), userId, request.getRemoteAddr().toString(), 'D', lbLrType);
				if (check) {
					model.addAttribute("msgid", "Records Updated Succesfully");
				} else {
					model.addAttribute("msgid", "Records NOT Updated Succesfully");
				}
			} else {
				model.addAttribute("msgid", "Records NOT Updated Succesfully");
			}

			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	/**
	 * @author kirandeep for pri,tri,urb freeze post State
	 * 
	 */
	@RequestMapping(value = "/freezeStateForPri", method = RequestMethod.POST)
	public ModelAndView stateFreezeForPri(Model model, HttpServletRequest request, HttpSession session,@ModelAttribute("stateFreeze") StateFreeze stateFreeze,   BindingResult binding) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("success");
			Character userType=stateFreeze.getLbLrType();
			
			nodalOfficerValidator.validateTokenError(binding,stateFreeze.getUserOTP(),userId.longValue());
			if(binding.hasErrors()){
				switch(userType){
					case 'P':
						mav=new ModelAndView("freezUnfreezPRI");
						break;
					case 'T':
						mav=new ModelAndView("freezUnfreezTri");
						break;
					case 'U':
						mav=new ModelAndView("freezUnfreezUrb");
						break;
					
				}
				Map<String, Object> mapStateFreeze = stateDistrictFService.getAllAttributeForStateFreeze(stateCode, userType);
				setMapAttributes(mapStateFreeze, mav);
				stateFreeze.setLbLrType(userType);
				mav.addObject("stateFreeze", stateFreeze);
				return mav;
			}

			boolean chkStatus = stateDistrictFService.checkSaveStateStatus(stateCode, stateFreeze.getLbLrType());
			if (chkStatus) {
				String list = null;
				String msg = null;
				if (stateFreeze.getStatusByStateUser() == 1) {
					list = stateCode + "_F_NA:";
					msg = "State Freeze Successfully";
				}
				if (stateFreeze.getStatusByStateUser() == 0) {
					list = stateCode + "_U_NA:";
					msg = "State unfreeze Successfully";
				}
				Character lbLrType = stateFreeze.getLbLrType();
				boolean checkStateStatus = stateDistrictFService.freezeUnFreezeFromStateForPri(list, userId, request.getRemoteAddr().toString(), 'S', lbLrType);
				if (checkStateStatus) {
					model.addAttribute("msgid", msg);
				} else {
					model.addAttribute("msgid", "State Can Not be Freezed ");
				}
			} else if (chkStatus && stateFreeze.getStatusByStateUser() == 0) {
				model.addAttribute("msgid", "Can not Freeze State Districts Are not Freezed Yet");
			}

			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	/**
	 * @author kirandeep for Tri freeze
	 * 
	 */

	@RequestMapping(value = "/freezUnfreezTri", method = RequestMethod.GET)
	public ModelAndView getfreezeUnfreezTri(HttpSession session, @ModelAttribute("stateFreeze") StateFreeze stateFreeze, BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("freezUnfreezTri");
		try {
			globalParam(session);
			/* added by kirandeep for freeze/unfreeze issues */
			Map<String, String> hMap = new HashMap<String, String>();
			char lbType = 'T';
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 27, lbType);

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false

			if (govtOrderConfig == null && mapConfig == null) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Traditional doesn't exists in your state");
				return mav;
			}
			Map<String, Object> mapStateFreeze = stateDistrictFService.getAllAttributeForStateFreeze(stateCode, 'T');
			setMapAttributes(mapStateFreeze, mav);
			stateFreeze.setLbLrType('T');
			mav.addObject("stateFreeze", stateFreeze);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}

	/**
	 * @author kirandeep for URB freeze
	 * 
	 */

	@RequestMapping(value = "/freezUnfreezUrb", method = RequestMethod.GET)
	public ModelAndView getfreezeUnfreezeUrb(HttpSession session, @ModelAttribute("stateFreeze") StateFreeze stateFreeze, BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("freezUnfreezUrb");
		try {
			globalParam(session);
			/* added by kirandeep for freeze/unfreeze issues */
			Map<String, String> hMap = new HashMap<String, String>();
			char lbType = 'U';
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 21, lbType);

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			if (govtOrderConfig == null && mapConfig == null) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Urban doesn't exists in your state");
				return mav;
			}
			Map<String, Object> mapStateFreeze = stateDistrictFService.getAllAttributeForStateFreeze(stateCode, 'U');
			setMapAttributes(mapStateFreeze, mav);
			stateFreeze.setLbLrType('U');
			mav.addObject("stateFreeze", stateFreeze);
			mav.addObject("userId", userId);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}

	/**
	 * Save Local Body Freeze/Un-freeze for Districts.
	 * 
	 * @author Anchal Todariya on 20-03-2015
	 * 
	 */

	@RequestMapping(value = "/localBodyFreezeDistrict", method = RequestMethod.GET)
	public ModelAndView districtLBFreeze(@ModelAttribute("localBodyFreeze") LBFreezeForm lbfreeze, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("districtLBFreeze");
		try {
			globalParam(httpSession);
			lbfreeze.setStateCode(stateCode.toString());

			if (districtCode == null || districtCode == 0) {
				mav.addObject("msgid", "Invalid District User !");
				mav.setViewName("success");
				return mav;
			}
			lbfreeze.setDistrictCode(String.valueOf(districtCode));
			List<GetLocalGovtSetup> getLocalGovtSetupList = localGovtSetupService.getLocalbodyDetail(stateCode);
			List<District> districtList = districtService.getDistrictList(stateCode);
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
			List<LocalbodyforStateWise> localBodyTypelistTrad = localGovtBodyService.getLocalBodyListStateWise('T', stateCode);
			List<LocalbodyListbyState> districtPanchayatListTrad = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'T');
			if (localBodyTypelist.size() == 2) {
				model.addAttribute("Tiertype", 2);
				mav.addObject("Tiertype", 2);
			} else if (localBodyTypelist.size() > 2) {
				model.addAttribute("Tiertype", 3);
				mav.addObject("Tiertype", 3);
			}

			if (localBodyTypelistTrad.size() == 2) {
				model.addAttribute("TradTiertype", 2);
				mav.addObject("TradTiertype", 2);
			} else if (localBodyTypelistTrad.size() > 2) {
				model.addAttribute("TradTiertype", 3);
				mav.addObject("TradTiertype", 3);
			}

			if (stateCode != 34) {
				List<LocalbodyListbyState> districtPanchayatListold = stateDistrictFService.getExistingLBListbyStateandCategoryForDistrict(districtCode, 'P');
				model.addAttribute("districtPanchayatList", districtPanchayatListold);
			} else {
				List<LocalbodyListbyState> districtPanchayatList = stateDistrictFService.getExistingPanchayatListoldForDistrict(districtCode);
				model.addAttribute("districtPanchayatList", districtPanchayatList);
			}
			if (districtPanchayatListTrad.size() > 0) {
				model.addAttribute("districtPanchayatListTrad", districtPanchayatListTrad);
			}
			model.addAttribute("localBodyTypelist", localBodyTypelistTrad);
			model.addAttribute("districtList", districtList);
			model.addAttribute("getLocalGovtSetupList", getLocalGovtSetupList);
			model.addAttribute("stateCode", stateCode);
			model.addAttribute("districtName", httpSession.getAttribute("login_key_id"));
			model.addAttribute("districtCode", lbfreeze.getDistrictCode());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}

	@RequestMapping(value = "/districtLocalBodyFreezePost", method = RequestMethod.POST)
	public ModelAndView districtLocalBodyFreezePost(@ModelAttribute("localBodyFreeze") LBFreezeForm lbfreeze, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = null;
		mav = new ModelAndView("localBodyFreezePost");
		String statecode = null;
		String localbType = "";
		int lbType = 0;
		char status = 'Y';

		if (districtCode == null || districtCode == 0) {
			mav.addObject("msgid", "Invalid District User !");
			mav.setViewName("success");
			return mav;
		}
		String entityName = lbfreeze.getEntityName();
		List<Object[]> nomenclature = new ArrayList<Object[]>();
		try {
			if (districtCode> 0) {
					lbfreeze.setDistrictCode(districtCode.toString());
				}
			String districtPanchyat = "";
			String intermediatePanchyat = "";
			String[] vc = entityName.split(":");
			if (entityName.contains(":")) {
				lbType = Integer.parseInt(vc[0]);
				localbType = vc[1];
				lbfreeze.setStateCode(statecode);
				lbfreeze.setLocalbType(localbType);
			}
			if ("P".equals(vc[3]) || "U".equals(vc[3])) {
				districtPanchyat = lbfreeze.getDistrictPanchyat();
				intermediatePanchyat = lbfreeze.getIntermediatePanchyat();
			} else {
				districtPanchyat = lbfreeze.getDistrictPanchyattrad();
				intermediatePanchyat = lbfreeze.getIntermediatePanchyattrad();
			}
			if (districtPanchyat != null && !"".equals(districtPanchyat) || intermediatePanchyat != null && !"".equals(intermediatePanchyat)) {
				if ("I".equalsIgnoreCase(localbType)) {
					nomenclature = stateDistrictFService.getNomenclature(Integer.parseInt(districtPanchyat));
					mav.addObject("nomenclature", nomenclature);
					mav.addObject("havingParentNomenclature", Boolean.TRUE);
				}
				if ("V".equalsIgnoreCase(localbType)) {
					if (intermediatePanchyat != null && !("").equals(intermediatePanchyat)) {
						nomenclature = stateDistrictFService.getNomenclature(Integer.parseInt(intermediatePanchyat));
					} else if (districtPanchyat != null && !("").equals(districtPanchyat)) {
						nomenclature = stateDistrictFService.getNomenclature(Integer.parseInt(districtPanchyat));
					}
					mav.addObject("nomenclature", nomenclature);
					mav.addObject("havingParentNomenclature", Boolean.TRUE);
				}
			} else {
				mav.addObject("nomenclature", entityName.split("\\:")[5]);
				mav.addObject("havingParentNomenclature", Boolean.FALSE);
			}
			// String tier = lbfreeze.getTiertype();
			List<StandardCodes> standardCodeDataList = stateDistrictFService.getListforLBFreezeUnfreeze(lbfreeze, status);
			// mav.addObject("standardCodeDataList", standardCodeDataList);
			List<StandardCodes> coverageLblc = new ArrayList<StandardCodes>();
			List<StandardCodes> getalldata = new ArrayList<StandardCodes>();
			String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
			lbfreeze.setStateName(stateName);
			lbfreeze.setEntity_name_english(httpSession.getAttribute("login_key_id").toString());
			mav.addObject("lbfreeze", lbfreeze);
			for (StandardCodes standardCodes : standardCodeDataList) {
				coverageLblc = stateDistrictFService.getlBcoverage(standardCodes.getEntityCode());
				if (coverageLblc != null && coverageLblc.size() > 0) {
					for (int i = 0; i < coverageLblc.size(); i++) {
						StandardCodes coverage = coverageLblc.get(i);
						standardCodes.setCoverage(coverage.getCoverage());
						standardCodes.setLblc(coverage.getLblc());
						if (coverage != null)
							getalldata = stateDistrictFService.getlblcCode(coverage.getLblc());
					}

				}

				if (getalldata != null && getalldata.size() > 0) {
					for (int i = 0; i < coverageLblc.size(); i++) {
						StandardCodes ac = coverageLblc.get(i);
						/*
						 * standardCodes.setCoverage(ac.getCoverage());
						 * standardCodes.setLblc(ac.getLblc());
						 */

						StandardCodes object = getalldata.get(i);
						standardCodes.setStatus(object.getStatus());
						// standardCodes.setLblc(object.getStatus());
					}
				}
			}
			mav.addObject("coverageLblc", standardCodeDataList);
			model.addAttribute("districtName", httpSession.getAttribute("login_key_id"));
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	@RequestMapping(value = "/localBodyPopulation", method = RequestMethod.GET)
	public ModelAndView localBodyPopulation(@ModelAttribute("freezeForm") LBFreezeForm freezeForm,  Model model, HttpServletRequest request,
			HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			globalParam(httpSession);
			mav = new ModelAndView("localBodyPopulation");
			model.addAttribute("getLocalGovtSetupList", localGovtSetupService.getLocalbodyDetailForVillage(stateCode));
			if (districtCode != null && districtCode > 0) {
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("districtUser", true);
			} else {
				model.addAttribute("districtUser", false);
			}
			mav.addObject("freezeForm", freezeForm);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	
	@RequestMapping(value = "/localBodyPopulation", method = RequestMethod.POST)
	public ModelAndView localBodyPopulationPost(@ModelAttribute("freezeForm") LBFreezeForm freezeForm,BindingResult result,Model model, HttpServletRequest request,
			HttpSession httpSession) {
		ModelAndView mav = null;
		
		try {
			mav = new ModelAndView("localBodyPopulation");
			boolean  save=stateDistrictFService.saveLBFreezePopulation(freezeForm.getLbFreezeUnfreezes());
			if (save == true) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "population saved Successfully!");
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "population is Not save Successfully!");
			}
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	
	@RequestMapping(value = "/localBodyPopulationForUrban", method = RequestMethod.GET)
	public ModelAndView localBodyPopulationForUrban(@ModelAttribute("freezeForm") LBFreezeForm freezeForm,  Model model, HttpServletRequest request,
			HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			globalParam(httpSession);
			mav = new ModelAndView("localBodyPopulationForUrban");
			model.addAttribute("getLocalGovtSetupList", localGovtSetupService.isStateSetup(stateCode,'U'));
			if (districtCode != null && districtCode > 0) {
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("districtUser", true);
			} else {
				model.addAttribute("districtUser", false);
			}
			mav.addObject("stateCode", stateCode);
			mav.addObject("freezeForm", freezeForm);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	
	@RequestMapping(value = "/localBodyPopulationForUrban", method = RequestMethod.POST)
	public ModelAndView localBodyPopulationUrbanPost(@ModelAttribute("freezeForm") LBFreezeForm freezeForm,BindingResult result,Model model, HttpServletRequest request,
			HttpSession httpSession) {
		ModelAndView mav = null;
		
		try {
			mav = new ModelAndView("localBodyPopulationForUrban");
			boolean  save=stateDistrictFService.saveLBFreezePopulation(freezeForm.getLbFreezeUnfreezes());
			if (save == true) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "population saved Successfully!");
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "population is Not save Successfully!");
			}
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	@RequestMapping(value = "/localBodyPopulationForTraditional", method = RequestMethod.GET)
	public ModelAndView localBodyPopulationForTraditional(@ModelAttribute("freezeForm") LBFreezeForm freezeForm,  Model model, HttpServletRequest request,
			HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			globalParam(httpSession);
			mav = new ModelAndView("localBodyPopulationForTraditional");
			model.addAttribute("getLocalGovtSetupList", localGovtSetupService.getLocalbodyDetailForTraditional(stateCode,'T'));
			if (districtCode != null && districtCode > 0) {
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("districtUser", true);
			} else {
				model.addAttribute("districtUser", false);
			}
			mav.addObject("freezeForm", freezeForm);
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	
	@RequestMapping(value = "/localBodyPopulationForTraditional", method = RequestMethod.POST)
	public ModelAndView localBodyPopulationTraditionalPost(@ModelAttribute("freezeForm") LBFreezeForm freezeForm,BindingResult result,Model model, HttpServletRequest request,
			HttpSession httpSession) {
		ModelAndView mav = null;
		
		try {
			mav = new ModelAndView("localBodyPopulationForTraditional");
			boolean  save=stateDistrictFService.saveLBFreezePopulation(freezeForm.getLbFreezeUnfreezes());
			if (save == true) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "population saved Successfully!");
			} else {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "population is Not save Successfully!");
			}
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	
/*	@RequestMapping(value = "/lBodyFreezeRural", method = RequestMethod.GET)
	public ModelAndView lbFreezeRural(Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("lbFreezeRural");
		try {
			globalParam(httpSession);
			Character isUserType = httpSession.getAttribute("isUserType") != null? httpSession.getAttribute("isUserType").toString().charAt(0) : null;
			if(!stateDao.getFreezeStatusbyUserId(userId.longValue(),isUserType,'D'))
			{
			model.addAttribute("getLocalGovtSetupList", localGovtSetupService.getLocalbodyDetailCategory(stateCode,'R'));
			if (districtCode != null && districtCode > 0) {
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("districtUser", true);
				
				
			} else {
				model.addAttribute("districtUser", false);
			}
		}else{
			mav.setViewName("success");
			mav.addObject("msgid", "district is freeze you can't change the coverage ");
		}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}
	
	
	@RequestMapping(value = "/lBodyFreezeUrban", method = RequestMethod.GET)
	public ModelAndView lbFreezeUrban(Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("lbFreezeUrban");
		try {
			globalParam(httpSession);
			Character isUserType = httpSession.getAttribute("isUserType") != null? httpSession.getAttribute("isUserType").toString().charAt(0) : null;
			if(!stateDao.getFreezeStatusbyUserId(userId.longValue(),isUserType,'D'))
			{
				model.addAttribute("getLocalGovtSetupList", localGovtSetupService.getLocalbodyDetailCategory(stateCode,'U'));
				if (districtCode != null && districtCode > 0) {
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("districtUser", true);
					
					
				} else {
					model.addAttribute("districtUser", false);
				}
			}else{
				mav.setViewName("success");
				mav.addObject("msgid", "district is freeze you can't change the coverage ");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}
	
	
	@RequestMapping(value = "/lBodyFreezeTribe", method = RequestMethod.GET)
	public ModelAndView lbFreezeTribe(Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("lbFreezeTribe");
		try {
			globalParam(httpSession);
			Character isUserType = httpSession.getAttribute("isUserType") != null? httpSession.getAttribute("isUserType").toString().charAt(0) : null;
			if(!stateDao.getFreezeStatusbyUserId(userId.longValue(),isUserType,'D'))
			{
			model.addAttribute("getLocalGovtSetupList", localGovtSetupService.getLocalbodyDetailCategory(stateCode,'U'));
			if (districtCode != null && districtCode > 0) {
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("districtUser", true);
				
				
			} else {
				model.addAttribute("districtUser", false);
			}
			}else{
				mav.setViewName("success");
				mav.addObject("msgid", "district is freeze you can't change the coverage ");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;

	}*/
}

