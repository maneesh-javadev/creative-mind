package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.ConfigurationGovernmentOrder;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.dao.LocalBodySetupDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.AdministratorUnit;
import in.nic.pes.lgd.forms.ConfigGovtOrderForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.validator.UploadGovernmentOrderValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class ConfigGovtOrderController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(ConfigGovtOrderController.class);

	@Autowired
	ConfigGovtOrderService configGovtOrderService;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	UploadGovernmentOrderValidator uploadgovtOrderValidator;

	@Autowired
	StateDAO stateDao;

	@Autowired
	LocalBodySetupDAO localBodySetupDAO;

	@Autowired
	CommonService commonService;


	List<Operations> OperationList = null;
	List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;
	int configSize = 0;

	private Integer stateCode;

	private Integer districtCode;

	private Long userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession httpSession) {
		try {
			setGlobalParams(httpSession);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		} catch (Exception e) {
			// IExceptionHandler expHandler =
			// ExceptionHandlerFactory.getInstance().create();

		}
	}

	private void setGlobalParams(HttpSession session) {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
	}

	

	public Integer getSlcfromState(int stateCode, int stateVersion) throws Exception {
		int slc;
		String hql = null;
		hql = "select slc from state where  state_code=" + stateCode + " and state_version=" + stateVersion;
		slc = localBodySetupDAO.getSlcfromStateDAO(hql);

		return slc;
	}

	@RequestMapping(value = "/constituencyMgr", method = RequestMethod.GET)
	public ModelAndView constituencyMgrPage(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		// TODO constituency Manager

		ModelAndView mav = null;
		try {
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			
			List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;
			configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();
			configurationGovernmentOrderList = configGovtOrderService.checkconstituencyMgrConfig(stateCode);
			httpSession.setAttribute("configurationGovernmentOrderList", configurationGovernmentOrderList);
			List<AdministratorUnit> listAdminUnits = null;
			AdministratorUnit administratorUnit = null;
			if (configurationGovernmentOrderList != null) {
				mav = new ModelAndView("constituencyMgrView");
				int i = 0;
				listAdminUnits = new ArrayList<AdministratorUnit>();
				for (int j = 30; j <= 38; j++) {
					String oprName = null;
					if (j == 30) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Create Assembly Constituency";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("isgovtorderuploadLocalSetup33");
						listAdminUnits.add(administratorUnit);
					} else if (j == 31) {
						oprName = "Create New Ward(Traditional)";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("isgovtorderuploadRegionbody34");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 32) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Create New Ward(PRI)";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody35");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 33) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Map Parliament/Assembly Constituency to local Govt";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody36");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 34) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Create Parliament Constituency";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody37");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 35) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Modify Ward name";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody38");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 37) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Modify covered area of ward";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody39");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 38) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Create New Ward(Urban)";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody40");
						listAdminUnits.add(administratorUnit);
					} else {
						oprName = null;
					}
					if (i < configurationGovernmentOrderList.size() - 1) {
						i++;
					}
				}
				configGovtOrderForm.setListAdminUnits(listAdminUnits);
				mav.addObject("configGovtOrderForm", configGovtOrderForm);
			} else if (configurationGovernmentOrderList == null) {

				mav = new ModelAndView("constituencyMgr");
				listAdminUnits = new ArrayList<AdministratorUnit>();
				administratorUnit = new AdministratorUnit();

				administratorUnit.setOperationBlockName("Create Assembly Constituency");
				administratorUnit.setOperationBlockValue(30);
				administratorUnit.setIsgovtorderuploadBlock(true);
				administratorUnit.setIsgovtoorderuploadBlockTag("isgovtorderuploadCreateWard33");
				listAdminUnits.add(administratorUnit);

				AdministratorUnit administratorUnit1 = new AdministratorUnit();
				administratorUnit1.setOperationBlockName("Create New Ward(Traditional)");
				administratorUnit1.setOperationBlockValue(31);
				administratorUnit1.setIsgovtorderuploadBlock(true);
				administratorUnit1.setIsgovtoorderuploadBlockTag("isgovtorderuploadModifyWard34");
				listAdminUnits.add(administratorUnit1);

				AdministratorUnit administratorUnit2 = new AdministratorUnit();
				administratorUnit2.setOperationBlockName("Map Parliament/Assembly Constituency to local Govt");
				administratorUnit2.setOperationBlockValue(33);
				administratorUnit2.setIsgovtorderuploadBlock(true);
				administratorUnit2.setIsgovtoorderuploadBlockTag("isgovtorderuploadModCovArWard35");
				listAdminUnits.add(administratorUnit2);

				AdministratorUnit administratorUnit3 = new AdministratorUnit();
				administratorUnit3.setOperationBlockName("Create Parliament Constituency");
				administratorUnit3.setOperationBlockValue(34);
				administratorUnit3.setIsgovtorderuploadBlock(true);
				administratorUnit3.setIsgovtoorderuploadBlockTag("isgovtorderuploadModCovArWard36");
				listAdminUnits.add(administratorUnit3);

				AdministratorUnit administratorUnit4 = new AdministratorUnit();
				administratorUnit4.setOperationBlockName("Modify Ward name");
				administratorUnit4.setOperationBlockValue(35);
				administratorUnit4.setIsgovtorderuploadBlock(true);
				administratorUnit4.setIsgovtoorderuploadBlockTag("isgovtorderuploadModCovArWard37");
				listAdminUnits.add(administratorUnit4);

				AdministratorUnit administratorUnit5 = new AdministratorUnit();
				administratorUnit5.setOperationBlockName("Modify covered area of ward");
				administratorUnit5.setOperationBlockValue(37);
				administratorUnit5.setIsgovtorderuploadBlock(true);
				administratorUnit5.setIsgovtoorderuploadBlockTag("isgovtorderuploadModCovArWard38");
				listAdminUnits.add(administratorUnit5);

				AdministratorUnit administratorUnit6 = new AdministratorUnit();
				administratorUnit6.setOperationBlockName("Create New Ward(Urban)");
				administratorUnit6.setOperationBlockValue(38);
				administratorUnit6.setIsgovtorderuploadBlock(true);
				administratorUnit6.setIsgovtoorderuploadBlockTag("isgovtorderuploadModCovArWard39");
				listAdminUnits.add(administratorUnit6);

				configGovtOrderForm.setListAdminUnits(listAdminUnits);

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

	@RequestMapping(value = "/constituencyMgrModify", method = RequestMethod.GET)
	public ModelAndView constituencyMgrModify(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		// TODO constituency Manager

		ModelAndView mav = null;
		try {
			if (stateCode == null) {
				return mav = new ModelAndView("redirect:login.htm");
			}
			
			List<ConfigurationGovernmentOrder> configurationGovernmentOrderList = null;
			configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();
			configurationGovernmentOrderList = configGovtOrderService.checkconstituencyMgrConfig(stateCode);
			httpSession.setAttribute("configurationGovernmentOrderList", configurationGovernmentOrderList);
			List<AdministratorUnit> listAdminUnits = null;
			AdministratorUnit administratorUnit = null;
			if (configurationGovernmentOrderList != null) {
				mav = new ModelAndView("constituencyMgrModify");
				int i = 0;
				listAdminUnits = new ArrayList<AdministratorUnit>();
				for (int j = 30; j <= 38; j++) {
					String oprName = null;
					if (j == 30) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Create Assembly Constituency";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("isgovtorderuploadLocalSetup41");
						listAdminUnits.add(administratorUnit);
					} else if (j == 31) {
						oprName = "Create New Ward(Traditional)";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("isgovtorderuploadRegionbody42");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 32) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Create New Ward(PRI)";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody43");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 33) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Map Parliament/Assembly Constituency to local Govt";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody44");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 34) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Create Parliament Constituency";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody45");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 35) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Modify Ward name";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody46");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 37) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Modify covered area of ward";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody47");
						listAdminUnits.add(administratorUnit);
					}
					if (j == 38) {
						// listAdminUnits = new ArrayList<AdministratorUnit>();
						oprName = "Create New Ward(Urban)";
						administratorUnit = new AdministratorUnit();
						administratorUnit.setOperationBlockName(oprName);
						administratorUnit.setOperationBlockValue(configurationGovernmentOrderList.get(i).getOperationCode());
						administratorUnit.setIsgovtorderuploadBlock(configurationGovernmentOrderList.get(i).isIsgovtorderupload());
						administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody48");
						listAdminUnits.add(administratorUnit);
					} else {
						oprName = null;
					}
					if (i < configurationGovernmentOrderList.size() - 1) {
						i++;
					}
				}
				configGovtOrderForm.setListAdminUnits(listAdminUnits);
				mav.addObject("configGovtOrderForm", configGovtOrderForm);
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

	@RequestMapping(value = "/configureOrder", method = RequestMethod.POST)
	public ModelAndView configOrder(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {
		// TODO insert Central admin
		ModelAndView mv = null;
		try {
			boolean success = false;
			try {
				success = configGovtOrderService.configGovtOrder(configGovtOrderForm, stateCode);
			} catch (Exception e) {
				log.debug("Exception" + e);

			}
			if (success) {
				String aMessage = "Central Administration's data Saved Successfully.";
				mv = new ModelAndView("redirect:centralAdmin.htm");
				mv.addObject("configGovtOrderForm", configGovtOrderForm);
				mv.addObject("msgid", aMessage);
				// System.out.println("hello3333333333333333333333333");
			} else {
				mv = new ModelAndView("error");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/modifyCentralAdmin", method = RequestMethod.POST)
	public ModelAndView modifyCentralAdmin(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {

		// TODO Central Admin Update
		// ModelAndView mav=null;
		boolean suc = false;
		ModelAndView mv = null;
		try {
			
			suc = configGovtOrderService.configUpdate(configGovtOrderForm, stateCode, httpSession);
			if (suc) {
				String aMessage = "Central Admin Configuration Updated Successfully.";
				mv = new ModelAndView("redirect:centralAdmin.htm");
				mv.addObject("configGovtOrderForm", configGovtOrderForm);
				mv.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
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

	/*
	 * @RequestMapping(value="/administrativeUnitMgrUpdate",
	 * method=RequestMethod.POST) public ModelAndView
	 * AUMUpdate(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm
	 * configGovtOrderForm ,BindingResult result,HttpSession httpSession) {
	 * 
	 * //TODO lrmupdate // ModelAndView mav=null; boolean suc=false;
	 * ModelAndView mv=null;
	 * suc=configGovtOrderService.configUpdate(configGovtOrderForm,stateCode,
	 * httpSession); if(suc) { //String aMessage=
	 * "Land Region Manager's data Updated Successfully."; mv=new
	 * ModelAndView("redirect:lrmForm.htm");
	 * //mv.addObject("configGovtOrderForm",configGovtOrderForm);
	 * //mv.addObject("msgid", aMessage); } else { String aMessage=
	 * "Error Occured"; mv=new ModelAndView("error"); mv.addObject("msgid",
	 * aMessage); } return mv; }
	 */
	// update LRM

	@RequestMapping(value = "/constituencyMgrAction", method = RequestMethod.POST)
	public ModelAndView constituencyMgrMethod(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {
		// System.out.println("hello constituencyMgrAction--//////////////");
		// ConfigGovtOrderForm configGovtOrderForm = new ConfigGovtOrderForm();
		// ModelAndView mav=new ModelAndView("constituencyMgr");
		boolean success = false;
		ModelAndView mv = null;

		try {

			try {
				success = configGovtOrderService.configInsertConstituence(configGovtOrderForm, stateCode, httpSession);
			} catch (Exception e) {
				// TODO constituencyMgrAction
				log.debug("Exception" + e);
			}

			if (success) {
				mv = new ModelAndView("redirect:constituencyMgr.htm");
				String aMessage = "Constituency Manager's data Saved Successfully.";
				mv.addObject("configGovtOrderForm", configGovtOrderForm);
				mv.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error.";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
				return mv;
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/constituencyMgrUpdate", method = RequestMethod.POST)
	public ModelAndView constituencyUpdate(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {

		// TODO lrmupdate
		// ModelAndView mav=null;
		boolean suc = false;
		ModelAndView mv = null;
		try {
			
			suc = configGovtOrderService.configUpdate(configGovtOrderForm, stateCode, httpSession);
			if (suc) {
				// String
				// aMessage="Land Region Manager's data Updated Successfully.";
				mv = new ModelAndView("redirect:constituencyMgr.htm");
				// mv.addObject("configGovtOrderForm",configGovtOrderForm);
				// mv.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
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

	@RequestMapping(value = "/uploadGovtOrder", method = RequestMethod.GET)
	public ModelAndView showUploadGovtOrder(HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("govtOrder");
			mav.addObject("governmentOrder", new GovernmentOrderForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/saveGovtOrder", method = RequestMethod.POST)
	public ModelAndView uploadGovernmentOrder(@ModelAttribute("governmentOrder") GovernmentOrderForm govtOrderForm, BindingResult result, SessionStatus status, HttpServletRequest request) throws Exception {
		// boolean saveSuccess = false;
		ModelAndView mav = null;
		AddAttachmentBean addAttachmentBean = null;
		try {

			mav = new ModelAndView("govtOrder");
			uploadgovtOrderValidator.validate(govtOrderForm, result);
			addAttachmentBean = setAttachmentBean(request, govtOrderForm);
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			String validateAttachmentOne = attachmentHandler.validateAttachment(addAttachmentBean);
			if (result.hasErrors()) {
				mav = new ModelAndView("govtOrder");
				mav.addObject("governmentOrder", govtOrderForm);
				return mav;
			} else if (!validateAttachmentOne.equalsIgnoreCase("validationSuccessFullyDone")) {

				mav = new ModelAndView("govtOrder");
				mav.addObject("governmentOrder", govtOrderForm);
				request.setAttribute("validationErrorOne", validateAttachmentOne);
				return mav;
			} else {
				List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
				if (metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {

					// saveSuccess =
					govtOrderService.saveMetaData(metaInfoOfToBeAttachedFileList, govtOrderForm);
					// String saveAttachment =
					attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);
				}
			}

			String aMessage = "Government Order Details is saved successfully";
			mav = new ModelAndView("success");
			mav.addObject("msgid", aMessage);

			return mav;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	public AddAttachmentBean setAttachmentBean(HttpServletRequest request, GovernmentOrderForm govtOrderForm) {

		AddAttachmentBean addAttachmentBeanTwo;
		try {
			List<String> allowedMimeTypeList = govtOrderService.getMimeTypeList();
			AttachmentMaster attachmentList = govtOrderService.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			int noOFMandatoryFile2 = 1;
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */

			addAttachmentBeanTwo = new AddAttachmentBean();
			addAttachmentBeanTwo.setCurrentlyUploadFileList(govtOrderForm.getAttachFile1());// 1.List
																							// Of
																							// File
																							// To
																							// Be
																							// Upload.
			addAttachmentBeanTwo.setUploadLocation(fileUploadLocation);// 2.Location
																		// For
																		// File
																		// Upload
																		// In
																		// File
																		// System.
			addAttachmentBeanTwo.setFileTitle(govtOrderForm.getFileTitle1());// 3.File
																				// Title
																				// Array.
			addAttachmentBeanTwo.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
																							// Total
																							// Number
																							// Of
																							// Attachment.
			addAttachmentBeanTwo.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed
																				// Total
																				// File
																				// Size.
			addAttachmentBeanTwo.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed
																							// Individual
																							// File
																							// Size.
			addAttachmentBeanTwo.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number
																						// Of
																						// Already
																						// Attached
																						// File.
			addAttachmentBeanTwo.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already
																										// attached
																										// file
																										// total
																										// size.
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
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}

		return addAttachmentBeanTwo;
	}

	// #################### Configuration Government Order for Traditonal OPEN
	// ################################################

	// $$$$$$$$$$$$$$$$$$$ view save method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/lgdmFormTrade", method = RequestMethod.GET)
	public ModelAndView lgdmFormPageTrade(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {

			int configSize = 0;
			

			OperationList = new ArrayList<Operations>();
			OperationList = configGovtOrderService.getOperationDetail("T");
			if (OperationList != null) {

				configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();
				configurationGovernmentOrderList = configGovtOrderService.checkLGDMConfigTrade(stateCode, OperationList);
				List<AdministratorUnit> listAdminUnits = null;
				AdministratorUnit administratorUnit = null;

				if (configurationGovernmentOrderList != null)
					configSize = configurationGovernmentOrderList.size();
				else
					configSize = 0;

				if (configSize < OperationList.size()) {

					httpSession.setAttribute("flag", "true");
					listAdminUnits = new ArrayList<AdministratorUnit>();
					mav = new ModelAndView("localGovtDirectoryMgrforTrade");

					for (Operations operation : OperationList) {

						AdministratorUnit administratorUnit1 = new AdministratorUnit();
						administratorUnit1.setOperationBlockName(operation.getOperationName());
						administratorUnit1.setOperationBlockValue(operation.getOperationCode());
						administratorUnit1.setIsgovtorderuploadBlock(operation.getIsactive());
						administratorUnit1.setIsgovtoorderuploadBlockTag("isgovtorderuploadNewLocBody5");
						listAdminUnits.add(administratorUnit1);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				} else if (configurationGovernmentOrderList != null) {
					mav = new ModelAndView("localGovtDirectoryMgrViewTrade");
					listAdminUnits = new ArrayList<AdministratorUnit>();

					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();

						for (Operations Operation : OperationList) {

							if (configGovtOrder.getOperationCode() == Operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(Operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}

						listAdminUnits.add(administratorUnit);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);
				}

			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Traditional Type operation not define in opeartion table");
			}

		} catch (NumberFormatException e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// $$$$$$$$$$$$$$$$$$$ save method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/lgdmFormActionTrade", method = RequestMethod.POST)
	public ModelAndView lgdmFormMethodTrade(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {
		String flag = null;
		boolean success = false;
		ModelAndView mv = null;
		if (httpSession.getAttribute("flag") != null) {
			flag = httpSession.getAttribute("flag").toString();
		}
		try {

						try {
				if (flag.equals("true"))
					success = configGovtOrderService.configInsertTrade(configGovtOrderForm, stateCode, httpSession);
				else {
					mv = new ModelAndView("error");
					mv.addObject("error", "Configration is alrady configure for this state");
				}

			} catch (Exception e) {
				log.debug("Exception" + e);
			}

			if (success)
				mv = new ModelAndView("redirect:lgdmFormTrade.htm");
			else
				mv = new ModelAndView("error");

		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

		return mv;
	}

	// $$$$$$$$$$$$$$$$$$$ view update method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/localGovtDirectoryMgrModifyTrade", method = RequestMethod.GET)
	public ModelAndView modifyLGDMTrade(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {

		ModelAndView mav = null;

		try {

			
			OperationList = configGovtOrderService.getOperationDetail("T");
			if (OperationList != null) {
				configurationGovernmentOrderList = configGovtOrderService.checkLGDMConfigTrade(stateCode, OperationList);
				httpSession.setAttribute("configurationGovernmentOrderList", configurationGovernmentOrderList);
				List<AdministratorUnit> listAdminUnits = null;
				AdministratorUnit administratorUnit = null;

				if (configurationGovernmentOrderList != null) {
					// TODO View of LGDM
					mav = new ModelAndView("localGovtDirectoryMgrModifyTrade");
					// int i = 0;
					listAdminUnits = new ArrayList<AdministratorUnit>();
					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						// String oprName = null;

						administratorUnit = new AdministratorUnit();
						for (Operations Operation : OperationList) {
							if (configGovtOrder.getOperationCode() == Operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(Operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}
					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);
				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Traditional Type operation not define in opeartion table");
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

	// $$$$$$$$$$$$$$$$$$ update method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/modifyLGDMTrade", method = RequestMethod.POST)
	public ModelAndView modifyLGDMTrade(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {

		boolean suc = false;
		ModelAndView mv = null;
		try {
			suc = configGovtOrderService.configUpdate(configGovtOrderForm, stateCode, httpSession);
			if (suc) {
				String aMessage = "LGDM Updated Successfully.";
				mv = new ModelAndView("redirect:lgdmFormTrade.htm");
				mv.addObject("configGovtOrderForm", configGovtOrderForm);
				mv.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
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

	// #################### Configuration Government Order for Traditonal
	// CLOSE################################################

	// #################### Configuration Government Order for Panchyat OPEN
	// ################################################

	// $$$$$$$$$$$$$$$$$$$ view save method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/lgdmFormPRI", method = RequestMethod.GET)
	public ModelAndView lgdmFormPagePRI(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		// TODO View LGDM
		ModelAndView mav = null;
		try {

			OperationList = configGovtOrderService.getOperationDetail("P");
			if (OperationList != null) {

				configurationGovernmentOrderList = configGovtOrderService.checkLGDMConfigPRI(stateCode, OperationList);

				List<AdministratorUnit> listAdminUnits = null;
				AdministratorUnit administratorUnit = null;
				if (configurationGovernmentOrderList != null)
					configSize = configurationGovernmentOrderList.size();
				else
					configSize = 0;
				if (configSize < OperationList.size()) {

					listAdminUnits = new ArrayList<AdministratorUnit>();
					mav = new ModelAndView("localGovtDirectoryMgrforPRI");

					// int i=0;
					for (Operations operation : OperationList) {

						AdministratorUnit administratorUnit1 = new AdministratorUnit();

						administratorUnit1.setOperationBlockName(operation.getOperationName());
						administratorUnit1.setOperationBlockValue(operation.getOperationCode());
						administratorUnit1.setIsgovtorderuploadBlock(operation.getIsactive());
						administratorUnit1.setIsgovtoorderuploadBlockTag("isgovtorderuploadNewLocBody5");
						listAdminUnits.add(administratorUnit1);
						// i++;
					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				} else if (configurationGovernmentOrderList != null) {
					mav = new ModelAndView("localGovtDirectoryMgrViewPRI");
					listAdminUnits = new ArrayList<AdministratorUnit>();

					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();
						for (Operations Operation : OperationList) {
							if (configGovtOrder.getOperationCode() == Operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(Operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Panchayat Type operation not define in opeartion table");
			}
		} catch (NumberFormatException e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// $$$$$$$$$$$$$$$$$$$ save method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/lgdmFormActionPRI", method = RequestMethod.POST)
	public ModelAndView lgdmFormMethodPRI(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {
		boolean success = false;
		ModelAndView mv = null;
		try {
			try {
				success = configGovtOrderService.configInsertPRI(configGovtOrderForm, stateCode, httpSession);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			if (success) {
				mv = new ModelAndView("redirect:lgdmFormPRI.htm");
			} else {
				mv = new ModelAndView("error");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		// System.out.println("hello*******-------lgdmFormAction--------------");
		return mv;
	}

	// $$$$$$$$$$$$$$$$$$$ view update method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/localGovtDirectoryMgrModifyPRI", method = RequestMethod.GET)
	public ModelAndView modifyLGDMPRI(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		// TODO View LGDM
		ModelAndView mav = null;
		try {
			OperationList = configGovtOrderService.getOperationDetail("P");
			if (OperationList != null) {
				configurationGovernmentOrderList = configGovtOrderService.checkLGDMConfigPRI(stateCode, OperationList);
				httpSession.setAttribute("configurationGovernmentOrderList", configurationGovernmentOrderList);
				List<AdministratorUnit> listAdminUnits = null;
				AdministratorUnit administratorUnit = null;

				if (configurationGovernmentOrderList != null) {

					mav = new ModelAndView("localGovtDirectoryMgrModifyPRI");

					listAdminUnits = new ArrayList<AdministratorUnit>();
					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();
						for (Operations Operation : OperationList) {
							if (configGovtOrder.getOperationCode() == Operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(Operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}
					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);
				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Panchayat Type operation not define in opeartion table");
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

	// $$$$$$$$$$$$$$$$$$$ update method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/modifyLGDMPRI", method = RequestMethod.POST)
	public ModelAndView modifyLGDMPRI(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {

		boolean suc = false;
		ModelAndView mv = null;
		try {
			suc = configGovtOrderService.configUpdate(configGovtOrderForm, stateCode, httpSession);
			if (suc) {
				String aMessage = "LGDM Updated Successfully.";
				mv = new ModelAndView("redirect:lgdmFormPRI.htm");
				mv.addObject("configGovtOrderForm", configGovtOrderForm);
				mv.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
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
	// #################### Configuration Government Order for Panchyat CLOSE
	// ################################################

	// #################### Configuration Government Order for Urban OPEN
	// ################################################

	// $$$$$$$$$$$$$$$$$$$ view save method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/lgdmForm", method = RequestMethod.GET)
	public ModelAndView lgdmFormPage(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {

			OperationList = configGovtOrderService.getOperationDetail("U");
			if (OperationList != null) {

				configurationGovernmentOrderList = configGovtOrderService.checkLGDMConfig(stateCode, OperationList);

				List<AdministratorUnit> listAdminUnits = null;
				AdministratorUnit administratorUnit = null;
				if (configurationGovernmentOrderList != null)
					configSize = configurationGovernmentOrderList.size();
				else
					configSize = 0;

				if (configSize < OperationList.size()) {

					listAdminUnits = new ArrayList<AdministratorUnit>();
					mav = new ModelAndView("localGovtDirectoryMgr");

					for (Operations operation : OperationList) {

						AdministratorUnit administratorUnit1 = new AdministratorUnit();

						administratorUnit1.setOperationBlockName(operation.getOperationName());
						administratorUnit1.setOperationBlockValue(operation.getOperationCode());
						administratorUnit1.setIsgovtorderuploadBlock(operation.getIsactive());
						administratorUnit1.setIsgovtoorderuploadBlockTag("isgovtorderuploadNewLocBody5");
						listAdminUnits.add(administratorUnit1);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				} else if (configurationGovernmentOrderList != null) {
					mav = new ModelAndView("localGovtDirectoryMgrView");
					listAdminUnits = new ArrayList<AdministratorUnit>();

					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();
						for (Operations Operation : OperationList) {
							if (configGovtOrder.getOperationCode() == Operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(Operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Urban Type operation not define in opeartion table");
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

	// $$$$$$$$$$$$$$$$$$$ save method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/lgdmFormAction", method = RequestMethod.POST)
	public ModelAndView lgdmFormMethod(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {
		boolean success = false;
		ModelAndView mv = null;
		try {
			try {
				success = configGovtOrderService.configInsertUrban(configGovtOrderForm, stateCode, httpSession);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			if (success) {
				mv = new ModelAndView("redirect:lgdmForm.htm");
			} else {
				mv = new ModelAndView("error");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// $$$$$$$$$$$$$$$$$$$ view update method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/localGovtDirectoryMgrModify", method = RequestMethod.GET)
	public ModelAndView modifyLGDM(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		// TODO View LGDM
		ModelAndView mav = null;
		try {

			OperationList = configGovtOrderService.getOperationDetail("U");
			if (OperationList != null) {
				configurationGovernmentOrderList = configGovtOrderService.checkLGDMConfig(stateCode, OperationList);
				httpSession.setAttribute("configurationGovernmentOrderList", configurationGovernmentOrderList);
				List<AdministratorUnit> listAdminUnits = null;
				AdministratorUnit administratorUnit = null;

				if (configurationGovernmentOrderList != null) {
					mav = new ModelAndView("localGovtDirectoryMgrModify");

					listAdminUnits = new ArrayList<AdministratorUnit>();
					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();
						for (Operations Operation : OperationList) {
							if (configGovtOrder.getOperationCode() == Operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(Operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}
					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);
				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Urban Type operation not define in opeartion table");
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

	// $$$$$$$$$$$$$$$$$$ update method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/modifyLGDM", method = RequestMethod.POST)
	public ModelAndView modifyLGDM(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {

		boolean suc = false;
		ModelAndView mv = null;
		try {
			suc = configGovtOrderService.configUpdate(configGovtOrderForm, stateCode, httpSession);
			if (suc) {
				String aMessage = "LGDM Updated Successfully.";
				mv = new ModelAndView("redirect:lgdmForm.htm");
				mv.addObject("configGovtOrderForm", configGovtOrderForm);
				mv.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
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

	// #################### Configuration Government Order for Urban CLOSE
	// ################################################

	// #################### Configuration Government Order for Land Region Open
	// ################################################

	// $$$$$$$$$$$$$$$$$$$ view save and update method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/lrmForm", method = RequestMethod.GET)
	public ModelAndView lrmFormView(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		// TODO For view of lrm
		ModelAndView mav = null;
		try {

			OperationList = configGovtOrderService.getOperationDetail("L");
			if (OperationList != null) {
				configurationGovernmentOrderList = configGovtOrderService.checkLRMConfig(stateCode, OperationList);

				List<AdministratorUnit> listAdminUnits = new ArrayList<AdministratorUnit>();

				if (configurationGovernmentOrderList != null)
					configSize = configurationGovernmentOrderList.size();
				else
					configSize = 0;

				if (configSize == OperationList.size()) {
					mav = new ModelAndView("landRegionMgrView");
					AdministratorUnit administratorUnit = new AdministratorUnit();
					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();
						for (Operations operation : OperationList) {
							if (configGovtOrder.getOperationCode() == operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				} else {
					mav = new ModelAndView("redirect:lrmFormModify.htm");

				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Land Region Type operation not define in opeartion table");
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

	// $$$$$$$$$$$$$$$$$$$ save method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/lrmFormAction", method = RequestMethod.POST)
	public ModelAndView lrmFormMethod(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {

		boolean succ = false;
		ModelAndView mv = null;
		try {

			succ = configGovtOrderService.configInsertLandRegion(configGovtOrderForm, stateCode, httpSession);
			if (succ) {
				mv = new ModelAndView("redirect:lrmForm.htm");
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
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

	// $$$$$$$$$$$$$$$$$$$ view update method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/lrmFormModify", method = RequestMethod.GET)
	public ModelAndView lrmForm(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			OperationList = configGovtOrderService.getOperationDetail("L");
			if (OperationList != null) {
				configurationGovernmentOrderList = configGovtOrderService.checkLRMConfig(stateCode, OperationList);
				httpSession.setAttribute("configurationGovernmentOrderList", configurationGovernmentOrderList);
				List<AdministratorUnit> listAdminUnits = new ArrayList<AdministratorUnit>();
				if (configurationGovernmentOrderList != null) {
					configSize = configurationGovernmentOrderList.size();
				}
				if (configSize < OperationList.size()) {
					// TODO Default View of LRM
					mav = new ModelAndView("landRegionMgr");

					for (Operations operation : OperationList) {
						AdministratorUnit administratorUnit1 = new AdministratorUnit();
						administratorUnit1.setOperationBlockName(operation.getOperationName());
						administratorUnit1.setOperationBlockValue(operation.getOperationCode());
						administratorUnit1.setIsgovtorderuploadBlock(operation.getIsactive());
						administratorUnit1.setIsgovtoorderuploadBlockTag("isgovtorderuploadLocalSetup21");
						listAdminUnits.add(administratorUnit1);
					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				} else {

					mav = new ModelAndView("modifyLandRegionMgr");

					AdministratorUnit administratorUnit = new AdministratorUnit();
					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();
						for (Operations operation : OperationList) {
							if (configGovtOrder.getOperationCode() == operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);
				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Land Region Type operation not define in opeartion table");
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

	// $$$$$$$$$$$$$$$$$$$ update method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/lrmFormUPDATE", method = RequestMethod.POST)
	public ModelAndView lrmFormUpdate(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {
		// TODO lrmupdate
		boolean suc = false;
		ModelAndView mv = null;
		try {
			suc = configGovtOrderService.configUpdate(configGovtOrderForm, stateCode, httpSession);
			if (suc) {
				mv = new ModelAndView("redirect:lrmForm.htm");
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
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

	// #################### Configuration Government Order for Land Region CLOSE
	// ################################################

	// #################### Configuration Government Order for BLOCK OPEN
	// ################################################

	// $$$$$$$$$$$$$$$$$$$ view save and update method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/administrativeUnitMgr", method = RequestMethod.GET)
	public ModelAndView administrativeUnitMgrPage(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {

			OperationList = configGovtOrderService.getOperationDetail("B");
			if (OperationList != null) {
				configurationGovernmentOrderList = configGovtOrderService.checkadministrativeUnitMgrConfig(stateCode, OperationList);
				httpSession.setAttribute("configurationGovernmentOrderList", configurationGovernmentOrderList);

				List<AdministratorUnit> listAdminUnits = null;
				AdministratorUnit administratorUnit = null;

				if (configurationGovernmentOrderList != null)
					configSize = configurationGovernmentOrderList.size();
				else
					configSize = 0;

				if (configSize < OperationList.size()) {

					listAdminUnits = new ArrayList<AdministratorUnit>();
					mav = new ModelAndView("administrativeMgr");

					for (Operations operation : OperationList) {

						AdministratorUnit administratorUnit1 = new AdministratorUnit();

						administratorUnit1.setOperationBlockName(operation.getOperationName());
						administratorUnit1.setOperationBlockValue(operation.getOperationCode());
						administratorUnit1.setIsgovtorderuploadBlock(operation.getIsactive());
						administratorUnit1.setIsgovtoorderuploadBlockTag("isgovtorderuploadNewLocBody5");
						listAdminUnits.add(administratorUnit1);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				} else if (configurationGovernmentOrderList != null) {
					mav = new ModelAndView("administrativeMgrView");
					listAdminUnits = new ArrayList<AdministratorUnit>();

					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();
						for (Operations Operation : OperationList) {
							if (configGovtOrder.getOperationCode() == Operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(Operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				}

			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Block Type operation not define in opeartion table");
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
		}
		return mav;

	}

	// $$$$$$$$$$$$$$$$$$$ save method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/administrativeUnitMgrAction", method = RequestMethod.POST)
	public ModelAndView administrativeUnitMgrMethod(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {
		// AdministratorUnit administratorUnit = new AdministratorUnit();

		boolean success = false;
		ModelAndView mv = null;
		try {
			try {
				success = configGovtOrderService.configInsertBlock(configGovtOrderForm, stateCode, httpSession);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			if (success) {
				mv = new ModelAndView("redirect:administrativeUnitMgr.htm");
				// mv.addObject("configGovtOrderForm",configGovtOrderForm);
			} else {
				mv = new ModelAndView("error");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// $$$$$$$$$$$$$$$$$$$ view update method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/administrativeUnitMgrModify", method = RequestMethod.GET)
	public ModelAndView administrativeUnitMgrModify(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			OperationList = configGovtOrderService.getOperationDetail("B");
			if (OperationList != null) {
				configurationGovernmentOrderList = configGovtOrderService.checkadministrativeUnitMgrConfig(stateCode, OperationList);
				httpSession.setAttribute("configurationGovernmentOrderList", configurationGovernmentOrderList);
				List<AdministratorUnit> listAdminUnits = null;

				if (configurationGovernmentOrderList != null) {
					configSize = configurationGovernmentOrderList.size();
				}
				/*
				 * if (configSize<OperationList.size()) {
				 * 
				 * mav = new ModelAndView("administrativeUnitMgrModify");
				 * 
				 * for (Operations operation : OperationList) {
				 * AdministratorUnit administratorUnit1 = new
				 * AdministratorUnit();
				 * administratorUnit1.setOperationBlockName(operation.
				 * getOperationName());
				 * administratorUnit1.setOperationBlockValue(operation.
				 * getOperationCode());
				 * administratorUnit1.setIsgovtorderuploadBlock(operation.
				 * getIsactive());
				 * administratorUnit1.setIsgovtoorderuploadBlockTag(
				 * "isgovtorderuploadLocalSetup21"); listAdminUnits.add(
				 * administratorUnit1); }
				 * 
				 * configGovtOrderForm.setListAdminUnits(listAdminUnits);
				 * mav.addObject("configGovtOrderForm", configGovtOrderForm);
				 * 
				 * }
				 */
				listAdminUnits = new ArrayList<AdministratorUnit>();
				if (configSize > OperationList.size()) {

					mav = new ModelAndView("administrativeUnitMgrModify");

					AdministratorUnit administratorUnit = new AdministratorUnit();

					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();
						for (Operations operation : OperationList) {
							if (configGovtOrder.getOperationCode() == operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				} else {

					mav = new ModelAndView("administrativeUnitMgrModify");

					AdministratorUnit administratorUnit = new AdministratorUnit();
					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();
						for (Operations operation : OperationList) {
							if (configGovtOrder.getOperationCode() == operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);
				}

			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Block Type operation not define in opeartion table");
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

	// $$$$$$$$$$$$$$$$$$$ update method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/administrativeUnitMgrUpdate", method = RequestMethod.POST)
	public ModelAndView administrativeUnitMgrUpdate(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {
		boolean suc = false;
		ModelAndView mv = null;
		try {
			suc = configGovtOrderService.configUpdate(configGovtOrderForm, stateCode, httpSession);
			if (suc) {
				// String
				// aMessage="Land Region Manager's data Updated Successfully.";
				mv = new ModelAndView("redirect:administrativeUnitMgr.htm");
				// mv.addObject("configGovtOrderForm",configGovtOrderForm);
				// mv.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
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

	// #################### Configuration Government Order for BLOCK CLOSE
	// ################################################

	// #################### Configuration Government Order for Center OPEN
	// ################################################

	// $$$$$$$$$$$$$$$$$$$ view save method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/lgdmFormCenter", method = RequestMethod.GET)
	public ModelAndView lgdmFormPageCenter(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {

			int configSize = 0;
			
			OperationList = new ArrayList<Operations>();
			OperationList = configGovtOrderService.getOperationDetail("C");
			if (OperationList != null) {
				configurationGovernmentOrderList = new ArrayList<ConfigurationGovernmentOrder>();
				configurationGovernmentOrderList = configGovtOrderService.checkLGDMConfigTrade(stateCode, OperationList);
				List<AdministratorUnit> listAdminUnits = null;
				AdministratorUnit administratorUnit = null;

				if (configurationGovernmentOrderList != null)
					configSize = configurationGovernmentOrderList.size();
				else
					configSize = 0;

				if (configSize < OperationList.size()) {

					listAdminUnits = new ArrayList<AdministratorUnit>();
					mav = new ModelAndView("localGovtDirectoryMgrforCenter");

					for (Operations operation : OperationList) {

						AdministratorUnit administratorUnit1 = new AdministratorUnit();
						administratorUnit1.setOperationBlockName(operation.getOperationName());
						administratorUnit1.setOperationBlockValue(operation.getOperationCode());
						administratorUnit1.setIsgovtorderuploadBlock(operation.getIsactive());
						administratorUnit1.setIsgovtoorderuploadBlockTag("isgovtorderuploadNewLocBody5");
						listAdminUnits.add(administratorUnit1);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);

				} else if (configurationGovernmentOrderList != null) {
					mav = new ModelAndView("localGovtDirectoryMgrViewCenter");
					listAdminUnits = new ArrayList<AdministratorUnit>();

					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						administratorUnit = new AdministratorUnit();

						for (Operations Operation : OperationList) {

							if (configGovtOrder.getOperationCode() == Operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(Operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}

						listAdminUnits.add(administratorUnit);

					}

					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);
				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Center Type operation not define in opeartion table");
			}

		} catch (NumberFormatException e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// $$$$$$$$$$$$$$$$$$$ save method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/lgdmFormActionCenter", method = RequestMethod.POST)
	public ModelAndView lgdmFormMethodCenter(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {

		boolean success = false;
		ModelAndView mv = null;

		try {

			try {
				success = configGovtOrderService.configInsertCenter(configGovtOrderForm, stateCode, httpSession);
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

			if (success)
				mv = new ModelAndView("redirect:lgdmFormCenter.htm");
			else
				mv = new ModelAndView("error");

		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

		return mv;
	}

	// $$$$$$$$$$$$$$$$$$$ view update method
	// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/localGovtDirectoryMgrModifyCenter", method = RequestMethod.GET)
	public ModelAndView modifyLGDMCenter(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, HttpSession httpSession, HttpServletRequest request) {

		ModelAndView mav = null;

		try {

			

			OperationList = configGovtOrderService.getOperationDetail("C");
			if (OperationList != null) {
				configurationGovernmentOrderList = configGovtOrderService.checkLGDMConfigTrade(stateCode, OperationList);
				httpSession.setAttribute("configurationGovernmentOrderList", configurationGovernmentOrderList);
				List<AdministratorUnit> listAdminUnits = null;
				AdministratorUnit administratorUnit = null;

				if (configurationGovernmentOrderList != null) {
					// TODO View of LGDM
					mav = new ModelAndView("localGovtDirectoryMgrModifyCenter");
					// int i = 0;
					listAdminUnits = new ArrayList<AdministratorUnit>();
					for (ConfigurationGovernmentOrder configGovtOrder : configurationGovernmentOrderList) {

						// String oprName = null;

						administratorUnit = new AdministratorUnit();
						for (Operations Operation : OperationList) {
							if (configGovtOrder.getOperationCode() == Operation.getOperationCode()) {

								administratorUnit.setOperationBlockName(Operation.getOperationName());
								administratorUnit.setOperationBlockValue(configGovtOrder.getOperationCode());
								administratorUnit.setIsgovtorderuploadBlock(configGovtOrder.isIsgovtorderupload());
								administratorUnit.setIsgovtoorderuploadBlockTag("ismodifynameoflocalbody1");

							}
						}
						listAdminUnits.add(administratorUnit);

					}
					configGovtOrderForm.setListAdminUnits(listAdminUnits);
					mav.addObject("configGovtOrderForm", configGovtOrderForm);
				}
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message", "Center Type operation not define in opeartion table");
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

	// $$$$$$$$$$$$$$$$$$ update method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	@RequestMapping(value = "/modifyLGDMCenter", method = RequestMethod.POST)
	public ModelAndView modifyLGDMCenter(@ModelAttribute("configGovtOrderForm") ConfigGovtOrderForm configGovtOrderForm, BindingResult result, HttpSession httpSession, HttpServletRequest request) {

		boolean suc = false;
		ModelAndView mv = null;
		try {

			

			suc = configGovtOrderService.configUpdate(configGovtOrderForm, stateCode, httpSession);
			if (suc) {
				String aMessage = "LGDM Updated Successfully.";
				mv = new ModelAndView("redirect:lgdmFormCenter.htm");
				mv.addObject("configGovtOrderForm", configGovtOrderForm);
				mv.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
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

	// #################### Configuration Government Order for Center
	// CLOSE################################################

}
