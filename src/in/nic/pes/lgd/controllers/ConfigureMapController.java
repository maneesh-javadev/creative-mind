package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.ConfigurationMapConstituency;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.GetMapConfigLocalBody;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.ViewConfigMapLandRegion;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.dao.ConfigMapLocalBodyDAO;
import in.nic.pes.lgd.forms.ConfigureMapForm;
import in.nic.pes.lgd.service.ConfigMapService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.service.LocalGovtTypeService;
import in.nic.pes.lgd.validator.ConfigMapConstituencyValidator;
import in.nic.pes.lgd.validator.ConfigMapValidator;
import in.nic.pes.lgd.validator.ConfigurationMapLGDMValidator;
import in.nic.pes.lgd.validator.ConfigurationMapLandregionLevelValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class ConfigureMapController { // NO_UCD (unused code)

	@Autowired
	ConfigMapService configMapService;

	@Autowired
	ConfigMapLocalBodyDAO configMapLocalBodyDAO;

	@Autowired
	LocalGovtSetupService localGovtSetupService;

	@Autowired
	LocalGovtTypeService localGovtTypeService;

	ConfigMapValidator configueMapValidator = new ConfigMapValidator();
	ConfigMapConstituencyValidator configMapConstituencyValidator = new ConfigMapConstituencyValidator();
	ConfigurationMapLandregionLevelValidator configurationMapLandregionLevelValidator = new ConfigurationMapLandregionLevelValidator();
	ConfigurationMapLGDMValidator configureMapLGDMValidator = new ConfigurationMapLGDMValidator();

	private Integer stateCode;

	private Integer districtCode;

	private Long userId;

	char category;

	private void setGlobalParams(HttpSession session) {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		setGlobalParams(session);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();

	// Configure Map for Local Body LGDM Urban
	@RequestMapping(value = "/configMapLGDM", method = RequestMethod.GET)
	public ModelAndView configureMapLocalBody(@ModelAttribute("configMapLGDMForm") ConfigureMapForm configMapLGDMForm, HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mav;
		try {
			mav = null;
			category = 'U';
			getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, category);
			if (getLocalGovtSetupList.size() > 0) {
				StringBuilder sb = null;
				sb = new StringBuilder();
				int tierCode = 0;
				for (int i = 0; i < getLocalGovtSetupList.size(); i++) {
					tierCode = getLocalGovtSetupList.get(i).getTierSetupCode();
					sb.append(tierCode + ",");
				}
				String mapConfig = sb.toString().substring(0, sb.length() - 1);

				configMapLGDMForm.setCategory(category);
				if (configMapLocalBodyDAO.getConfigureMapLocalBody(mapConfig).size() > 0) {
					// view & modify
					ConfigureMapForm configureMapForm = null;
					List<GetMapConfigLocalBody> lstdetail = configMapLocalBodyDAO.configMapLocalBodyDetail(stateCode, category);
					List<GetMapConfigLocalBody> viewmaplstdetail = new ArrayList<GetMapConfigLocalBody>();
					GetMapConfigLocalBody getMapConfigLocalBody = null;

					for (int i = 0; i < lstdetail.size(); i++) {
						getMapConfigLocalBody = (GetMapConfigLocalBody) lstdetail.get(i);
						if (lstdetail.get(i).getTier_setup_code() != null) {
							viewmaplstdetail.add(getMapConfigLocalBody);
						}

					}

					configureMapForm = new ConfigureMapForm();
					configureMapForm.setViewmaplstdetail(viewmaplstdetail);
					mav = new ModelAndView("configmapLGDMview");
					mav.addObject("viewmaplstdetail", viewmaplstdetail);
					mav.addObject("configureMapForm", configureMapForm);

				} else {
					// save //new page will be open for processing
					mav = new ModelAndView("configMapLGDM");
					mav.addObject("getLocalGovtSetupList", getLocalGovtSetupList);
					mav.addObject("getLocalGovtSetupListSize", getLocalGovtSetupList.size());
					// mav.addObject("configMapLGDMForm", configMapLGDMForm);
				}

			} else {
				// display message setup not configured
				mav = new ModelAndView();
				mav.setViewName("errorConfigshow");
				request.setAttribute("message", "Local Govt Setup is Not Configured.Please Configure Local Govt Setup First");
				mav.addObject("message", "Local Govt Setup is Not Configured.Please Configure Local Govt Setup First");

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// Configure Map for Local Body LGDM PRI
	@RequestMapping(value = "/configMapPRI", method = RequestMethod.GET)
	public ModelAndView configureMapLocalBodyPRI(@ModelAttribute("configMapLGDMForm") ConfigureMapForm configMapLGDMForm, HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			category = 'P';
			

			getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, category);
			if (getLocalGovtSetupList.size() > 0) {
				StringBuilder sb = null;
				sb = new StringBuilder();
				int tierCode = 0;
				for (int i = 0; i < getLocalGovtSetupList.size(); i++) {
					tierCode = getLocalGovtSetupList.get(i).getTierSetupCode();
					sb.append(tierCode + ",");
				}
				String mapConfig = sb.toString().substring(0, sb.length() - 1);

				configMapLGDMForm.setCategory(category);

				if (configMapLocalBodyDAO.getConfigureMapLocalBody(mapConfig).size() > 0) {
					// view & modify
					ConfigureMapForm configureMapForm = null;
					List<GetMapConfigLocalBody> lstdetail = configMapLocalBodyDAO.configMapLocalBodyDetail(stateCode, category);
					List<GetMapConfigLocalBody> viewmaplstdetail = new ArrayList<GetMapConfigLocalBody>();
					GetMapConfigLocalBody getMapConfigLocalBody = null;

					for (int i = 0; i < lstdetail.size(); i++) {
						getMapConfigLocalBody = (GetMapConfigLocalBody) lstdetail.get(i);
						if (lstdetail.get(i).getTier_setup_code() != null) {
							viewmaplstdetail.add(getMapConfigLocalBody);
						}

					}
					configureMapForm = new ConfigureMapForm();
					configureMapForm.setViewmaplstdetail(viewmaplstdetail);
					mav = new ModelAndView("configmapLGDMview");
					mav.addObject("viewmaplstdetail", viewmaplstdetail);
					mav.addObject("configureMapForm", configureMapForm);
				} else {
					// save //new page will be open for processing

					mav = new ModelAndView("configMapLGDM");
					mav.addObject("getLocalGovtSetupList", getLocalGovtSetupList);
					mav.addObject("getLocalGovtSetupListSize", getLocalGovtSetupList.size());
					// mav.addObject("configMapLGDMForm", configMapLGDMForm);
				}

			} else {
				// display message setup not configured

				mav = new ModelAndView();
				mav.setViewName("errorConfigshow");
				request.setAttribute("message", "Local Govt Setup is Not Configured.Please Configure Local Govt Setup First");
				mav.addObject("message", "Local Govt Setup is Not Configured.Please Configure Local Govt Setup First");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// ------------------------------------LGDM-----------------------------------------

	@RequestMapping(value = "/config_landregion", method = RequestMethod.GET)
	public ModelAndView showFormLandRegion(HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		boolean flag = false;
		try {
			setGlobalParams(httpSession);
			ConfigureMapForm configureMapForm = null;
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
			viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);
			if (viewConfigMapLandRegion.size() > 0) {
				for (int i = 0; i < viewConfigMapLandRegion.size(); i++) {
					if (viewConfigMapLandRegion.get(i).getLandregiontype() == 'S') {
						flag = true;
					}

				}

			}

			if (flag) {
				configureMapForm = new ConfigureMapForm();
				configureMapForm.setViewConfigMapLandRegion(viewConfigMapLandRegion);
				mav = new ModelAndView("configLandRegionView");
				mav.addObject("configureMapForm", configureMapForm);
			} else if (!flag) {
				configureMapForm = new ConfigureMapForm();
				mav = new ModelAndView("configLandRegion");
				mav.addObject("config", configureMapForm);
			} else {
				mav = new ModelAndView("error");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/configland", method = RequestMethod.POST)
	public ModelAndView configLandregion(@ModelAttribute("config") ConfigureMapForm configMapBean, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {

		
		ModelAndView mv1 = new ModelAndView("configLandRegion", result.getModel());

		ModelAndView mav;
		try {
			configueMapValidator.validate(configMapBean, result);

			configMapService.configureLand(configMapBean, stateCode, httpSession);
			mav = null;
			ConfigureMapForm configureMapForm = null;
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
			viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
			viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);
			configureMapForm = new ConfigureMapForm();
			configureMapForm.setViewConfigMapLandRegion(viewConfigMapLandRegion);
			// mav=new ModelAndView("configLandRegionView");
			mav = new ModelAndView("configLandRegionView");
			mav.addObject("viewConfigMapLandRegion", viewConfigMapLandRegion);
			mav.addObject("configureMapForm", configureMapForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv1 = new ModelAndView(redirectPath);
			return mv1;
		}
		return mav;

	}

	@RequestMapping(value = "/config_block", method = RequestMethod.GET)
	public ModelAndView showFormBlock(HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			setGlobalParams(httpSession);
			ConfigureMapForm configureMapForm = null;
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
			viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
			viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);
			boolean flag = false;
			Integer configurationMapLandregionCode = null;
			if (viewConfigMapLandRegion.size() > 0) {
				for (int i = 0; i < viewConfigMapLandRegion.size(); i++) {
					if (viewConfigMapLandRegion.get(i).getLandregiontype() == 'B') {
						configurationMapLandregionCode = viewConfigMapLandRegion.get(i).getConfigurationMapLandregionCode();

						flag = true;
					}

				}

				httpSession.setAttribute("configurationMapLandregionCode", configurationMapLandregionCode);

				if (flag) {
					configureMapForm = new ConfigureMapForm();
					configureMapForm.setViewConfigMapLandRegion(viewConfigMapLandRegion);
					mav = new ModelAndView("viewConfigMapBlock");
					mav.addObject("viewConfigMapLandRegion", viewConfigMapLandRegion);
					mav.addObject("configureMapForm", configureMapForm);
				} else if (!flag) {
					configureMapForm = new ConfigureMapForm();
					mav = new ModelAndView("configBlock");
					mav.addObject("config", configureMapForm);
				} else {
					mav = new ModelAndView("errorConfigshow");
					request.setAttribute("message", "Local Govt Setup is Not Configured.Please Configure Local Govt Setup First");
				}

			} else if (viewConfigMapLandRegion.size() == 0) {

				configureMapForm = new ConfigureMapForm();
				mav = new ModelAndView("configBlock");
				mav.addObject("config", configureMapForm);
				/*
				 * mav = new ModelAndView("error"); String message =
				 * "State Map  is not Configured !!"; mav.addObject("message",
				 * message);
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

	@RequestMapping(value = "/blockType", method = RequestMethod.POST)
	public ModelAndView blockType(@ModelAttribute("config") ConfigureMapForm configureMapFormBean, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {

		
		ModelAndView mv;
		try {
			
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
			viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
			viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);
			httpSession.setAttribute("viewConfigMapLandRegion", viewConfigMapLandRegion);
			/*
			 * ModelAndView mv1 = new ModelAndView("modifyConfigMapBlock",
			 * result.getModel());
			 */

			/*
			 * configurationMapLandregionLevelValidator.validateModifyBlock(
			 * configureMapFormBean, result); if (result.hasErrors()) {
			 * result.getErrorCount(); return mv1; }
			 */
			boolean suc = false;
			mv = null;
			suc = configMapService.blockInsert(configureMapFormBean, stateCode, httpSession);
			if (suc) {
				mv = new ModelAndView("redirect:config_block.htm");
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
		}
		return mv;

		
	}

	@RequestMapping(value = "/modifyConstituncy", method = RequestMethod.POST)
	public ModelAndView modifyConfigMapConstituency(@ModelAttribute("configureMapForm") ConfigureMapForm configureMapForm, HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mv;
		try {
			// System.out.println("sdfhj====================>>>>>>>>>>>>>>>>"+configurationMapLandregionCode);
			// ConfigureMapForm configureMapForm=null;
			
			List<ConfigurationMapConstituency> listConfigurationMapConstituency = null;
			listConfigurationMapConstituency = new ArrayList<ConfigurationMapConstituency>();
			listConfigurationMapConstituency = configMapService.getConfigureMapConstituencyDetail(stateCode);
			httpSession.setAttribute("listConfigurationMapConstituency", listConfigurationMapConstituency);
			configureMapForm.setListConfigurationMapConstituency(listConfigurationMapConstituency);
			mv = new ModelAndView("viewConfigMapConstituncy");
			model.addAttribute("size", listConfigurationMapConstituency.size());
			model.addAttribute("listConfigurationMapConstituency", listConfigurationMapConstituency);
			mv.addObject("configureMapForm", configureMapForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	@RequestMapping(value = "/modifyLandBlock", method = RequestMethod.POST)
	public ModelAndView modifyConfigMapBlock(@ModelAttribute("modifyconfigMapCmd") ConfigureMapForm modifyconfigMapCmd, HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mv;
		try {
			// System.out.println("sdfhj====================>>>>>>>>>>>>>>>>"+configurationMapLandregionCode);
			// ConfigureMapForm configureMapForm=null;
			
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
			viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
			viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);
			// session.setAttribute("viewConfigMapLandRegion",
			// viewConfigMapLandRegion);
			modifyconfigMapCmd.setViewConfigMapLandRegion(viewConfigMapLandRegion);
			mv = new ModelAndView("modifyConfigMapBlock");
			model.addAttribute("size", viewConfigMapLandRegion.size());
			model.addAttribute("viewConfigMapLandRegion", viewConfigMapLandRegion);
			mv.addObject("modifyconfigMapCmd", modifyconfigMapCmd);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	@RequestMapping(value = "/modifyLandRegion", method = RequestMethod.POST)
	public ModelAndView modifyConfigMap(@ModelAttribute("modifyconfigMapCmd") ConfigureMapForm modifyconfigMapCmd, HttpServletRequest request, Model model, HttpSession httpSession) {

		ModelAndView mv;
		try {
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
			viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
			viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);
			Integer configurationMapLandregionCode = null;
			if (viewConfigMapLandRegion != null) {
				if (viewConfigMapLandRegion.size() > 0) {
					for (ViewConfigMapLandRegion viewConfigMap : viewConfigMapLandRegion) {
						if (viewConfigMap.getLandregiontype() == 'S') {
							configurationMapLandregionCode = viewConfigMap.getConfigurationMapLandregionCode();
						}
					}
				}
			}

			httpSession.setAttribute("configurationMapLandregionCode", configurationMapLandregionCode);
			// session.setAttribute("viewConfigMapLandRegion",
			// viewConfigMapLandRegion);
			modifyconfigMapCmd.setViewConfigMapLandRegion(viewConfigMapLandRegion);
			mv = new ModelAndView("modifyLandRegion");
			model.addAttribute("size", viewConfigMapLandRegion.size());
			/*
			 * model.addAttribute("viewConfigMapLandRegion",
			 * viewConfigMapLandRegion);
			 */
			mv.addObject("modifyconfigMapCmd", modifyconfigMapCmd);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	// Update
	@RequestMapping(value = "/modifyLandMapUpdate", method = RequestMethod.POST)
	public ModelAndView lrmLandUpdate(@ModelAttribute("modifyconfigMapCmd") ConfigureMapForm configureMapFormBean, BindingResult result, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mv;
		try {

			@SuppressWarnings("unused")
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
			viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
			viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);
			// httpSession.setAttribute("viewConfigMapLandRegion",viewConfigMapLandRegion);
			boolean suc = false;
			mv = new ModelAndView();
			ModelAndView mv1 = new ModelAndView("modifyLandRegion", result.getModel());
			configueMapValidator.validateModifyLand(configureMapFormBean, result);
			if (result.hasErrors()) {
				// configureMapFormBean.setIsmapuploadState(false);
				return mv1;
			}
			suc = configMapService.lgdmUpdate(configureMapFormBean, stateCode, httpSession);
			if (suc) {
				mv = new ModelAndView("redirect:config_landregion.htm");

			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	// Update Config Map Block
	@RequestMapping(value = "/modifyBlockUpdate", method = RequestMethod.POST)
	public ModelAndView configMapBlockUpdate(@ModelAttribute("modifyconfigMapCmd") ConfigureMapForm configureMapFormBean, BindingResult result, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mv;
		try {
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
			viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
			viewConfigMapLandRegion = configMapService.checkMapConfiguration(stateCode);
			httpSession.setAttribute("viewConfigMapLandRegion", viewConfigMapLandRegion);
			ModelAndView mv1 = new ModelAndView("modifyConfigMapBlock", result.getModel());

			configurationMapLandregionLevelValidator.validateModifyBlock(configureMapFormBean, result);
			if (result.hasErrors()) {
				result.getErrorCount();
				return mv1;
			}
			boolean suc = false;
			mv = null;
			suc = configMapService.blockUpdate(configureMapFormBean, stateCode, httpSession);
			if (suc) {
				mv = new ModelAndView("redirect:config_block.htm");
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/view_constituency", method = RequestMethod.GET)
	public ModelAndView viewConstituency(
			/*
			 * @ModelAttribute ("config")ConfigureMapForm config , BindingResult
			 * result, Model model,@RequestParam("id")Integer code
			 */HttpServletRequest request) {
		ConfigureMapForm configureMapForm = new ConfigureMapForm();
		ModelAndView mav = new ModelAndView("viewConstituncy");
		try {
			mav.addObject("config", configureMapForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/modifyLandRegion", method = RequestMethod.GET)
	public ModelAndView modifyLandRegion(HttpServletRequest request) {
		ConfigureMapForm configureMapForm = new ConfigureMapForm();
		ModelAndView mav;
		try {
			EsapiEncoder.encode(configureMapForm);
			mav = new ModelAndView("modifyLandRegion");
			mav.addObject("config", configureMapForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// config map local body
	@RequestMapping(value = "/configmapLGDMmodify", method = RequestMethod.GET)
	public ModelAndView modifymapLocalBody(HttpServletRequest request) {
		ConfigureMapForm configureMapForm = new ConfigureMapForm();
		List<GetMapConfigLocalBody> lstdetail = null;
		lstdetail = new ArrayList<GetMapConfigLocalBody>();
		ModelAndView mav;
		try {
			EsapiEncoder.encode(lstdetail);
			lstdetail = configMapLocalBodyDAO.configMapLocalBodyDetail(stateCode, category);
			mav = new ModelAndView("configmapLGDMmodify");
			mav.addObject("configureMapForm", configureMapForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// List<GetMapConfigLocalBody> lstdetail = new
	// ArrayList<GetMapConfigLocalBody>();
	// List<GetMapConfigLocalBody> viewmaplstdetail = new
	// ArrayList<GetMapConfigLocalBody>();

	@RequestMapping(value = "/configmapLGDMview", method = RequestMethod.POST)
	public ModelAndView modifyConfigMapLGDM(@ModelAttribute("configMapLGDMForm") ConfigureMapForm configMapLGDMForm, HttpServletRequest request, Model model, HttpSession httpSession) {
		List<GetMapConfigLocalBody> lstdetail = null;
		ModelAndView mv;
		try {
			lstdetail = new ArrayList<GetMapConfigLocalBody>();
			char lbType = configMapLGDMForm.getCategory();
			lstdetail = configMapLocalBodyDAO.configMapLocalBodyDetail(stateCode, lbType);
			configMapLGDMForm.setLstdetail(lstdetail);
			mv = new ModelAndView("configmapLGDMmodify");
			model.addAttribute("tierSetupSize", lstdetail.size());
			model.addAttribute("lstdetail", lstdetail);
			mv.addObject("configMapLGDMForm", configMapLGDMForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	@RequestMapping(value = "/configMapLGDMmodify", method = RequestMethod.POST)
	public ModelAndView updateConfigMapLGDM(@ModelAttribute("configMapLGDMForm") ConfigureMapForm configMapLGDMForm, BindingResult result, Model model, HttpSession httpSession, HttpServletRequest request) {
		boolean suc = false;
		ModelAndView mv = new ModelAndView();
		try {
			List<GetMapConfigLocalBody> lstdetail = null;
			lstdetail = new ArrayList<GetMapConfigLocalBody>();
			List<GetMapConfigLocalBody> viewmaplstdetail = new ArrayList<GetMapConfigLocalBody>();
			configureMapLGDMValidator.validate(configMapLGDMForm, result);
			if (result.hasErrors()) {
				char lbType = configMapLGDMForm.getCategory();
				GetMapConfigLocalBody getMapConfigLocalBody = null;
				lstdetail = configMapLocalBodyDAO.configMapLocalBodyDetail(stateCode, lbType);
				for (int i = 0; i < lstdetail.size(); i++) {
					getMapConfigLocalBody = (GetMapConfigLocalBody) lstdetail.get(i);
					if (lstdetail.get(i).getTier_setup_code() != null) {
						viewmaplstdetail.add(getMapConfigLocalBody);
					}
				}
				model.addAttribute("lstdetail", lstdetail);
				result.getErrorCount();
				configMapLGDMForm.setLstdetail(lstdetail);
				result.getAllErrors();
				httpSession.setAttribute("viewmaplstdetail", viewmaplstdetail);
				mv.addObject("configMapLGDMForm", configMapLGDMForm);
				mv = new ModelAndView("configmapLGDMmodify");
				return mv;
			} else {

				suc = configMapService.updateLGDMMapConfiguration(configMapLGDMForm, stateCode, request, httpSession);

				// httpSession.setAttribute("viewmaplstdetail",
				// viewmaplstdetail);

				if (suc) {
					if (category == 'U') {
						mv = new ModelAndView("redirect:configMapLGDM.htm");
					} else if (category == 'P') {
						mv = new ModelAndView("redirect:configMapPRI.htm");
					} else if (category == 'T') {
						mv = new ModelAndView("redirect:configMapTrd.htm");
					}

				} else {
					String aMessage = "Error Occured";
					mv = new ModelAndView("error");
					mv.addObject("msgid", aMessage);
				}
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

	/// ############# Config Map for Traditional OPEN
	/// ###########################

	// $$$$$$$$$$$$$$$ Get the detail of map for traditional $$$$$$$$$$$$$$$$$$
	@RequestMapping(value = "/configMapTrd", method = RequestMethod.GET)
	public ModelAndView configureMapLocalBodyTRD(@ModelAttribute("configMapLGDMForm") ConfigureMapForm configMapLGDMForm, HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			category = 'T';
			getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, category);
			if (getLocalGovtSetupList.size() > 0) {
				StringBuilder sb = null;
				sb = new StringBuilder();
				int tierCode = 0;
				for (int i = 0; i < getLocalGovtSetupList.size(); i++) {
					tierCode = getLocalGovtSetupList.get(i).getTierSetupCode();
					sb.append(tierCode + ",");
				}
				String mapConfig = sb.toString().substring(0, sb.length() - 1);

				configMapLGDMForm.setCategory(category);

				if (configMapLocalBodyDAO.getConfigureMapLocalBody(mapConfig).size() > 0) {
					// view & modify
					ConfigureMapForm configureMapForm = null;
					List<GetMapConfigLocalBody> lstdetail = configMapLocalBodyDAO.configMapLocalBodyDetail(stateCode, category);
					List<GetMapConfigLocalBody> viewmaplstdetail = new ArrayList<GetMapConfigLocalBody>();
					GetMapConfigLocalBody getMapConfigLocalBody = null;

					for (int i = 0; i < lstdetail.size(); i++) {
						getMapConfigLocalBody = (GetMapConfigLocalBody) lstdetail.get(i);
						if (lstdetail.get(i).getTier_setup_code() != null) {
							viewmaplstdetail.add(getMapConfigLocalBody);
						}

					}
					configureMapForm = new ConfigureMapForm();
					configureMapForm.setViewmaplstdetail(viewmaplstdetail);
					mav = new ModelAndView("configmapLGDMview");
					mav.addObject("viewmaplstdetail", viewmaplstdetail);
					mav.addObject("configureMapForm", configureMapForm);
				} else {
					// save //new page will be open for processing

					mav = new ModelAndView("configMapLGDM");
					mav.addObject("getLocalGovtSetupList", getLocalGovtSetupList);
					mav.addObject("getLocalGovtSetupListSize", getLocalGovtSetupList.size());
					// mav.addObject("configMapLGDMForm", configMapLGDMForm);
				}

			} else {
				// display message setup not configured
				mav = new ModelAndView();
				mav.setViewName("errorConfigshow");
				request.setAttribute("message", "Local Govt Setup is Not Configured.Please Configure Local Govt Setup First");
				mav.addObject("message", "Local Govt Setup is Not Configured.Please Configure Local Govt Setup First");

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// Configure Map for Local Body LGDM Traditional

	@RequestMapping(value = "/configMapLGDM", method = RequestMethod.POST)
	public ModelAndView configureMapLocalBodyPost(@ModelAttribute("configMapLGDMForm") ConfigureMapForm configMapLGDMForm, BindingResult result, Model model, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			List<GetMapConfigLocalBody> lstdetail = configMapLocalBodyDAO.configMapLocalBodyDetail(stateCode, category);
			boolean success = false;
			configureMapLGDMValidator.validateSave(configMapLGDMForm, result, request);
			if (result.hasErrors()) {
				model.addAttribute("lstdetail", lstdetail);
				result.getErrorCount();
				configMapLGDMForm.setLstdetail(lstdetail);
				result.getAllErrors();
				mv.addObject("getLocalGovtSetupList", getLocalGovtSetupList);
				mv.addObject("getLocalGovtSetupListSize", getLocalGovtSetupList.size());
				mv.addObject("configMapLGDMForm", configMapLGDMForm);
				mv.setViewName("configMapLGDM");
				return mv;
			} else {
				success = configMapService.saveLGDMMapConfiguration(configMapLGDMForm, request, httpSession);
				if (category == 'U' && success) {
					mv = new ModelAndView("redirect:configMapLGDM.htm");
				} else if (category == 'P' && success) {
					mv = new ModelAndView("redirect:configMapPRI.htm");
				} else if (category == 'T' && success) {
					mv = new ModelAndView("redirect:configMapTrd.htm");

				} else {
					String message = "Server error due to invalid localbody Category or Error while saving record";
					mv = new ModelAndView("error");
					mv.addObject("message", message);
				}
				/*
				 * lstdetail = configMapLocalBodyDAO
				 * .configMapLocalBodyDetail(stateCode, category);
				 * ConfigureMapForm configureMapForm = null;
				 * 
				 * List<GetMapConfigLocalBody> viewmaplstdetail = new
				 * ArrayList<GetMapConfigLocalBody>(); GetMapConfigLocalBody
				 * getMapConfigLocalBody = null;
				 * 
				 * for (int i = 0; i < lstdetail.size(); i++) {
				 * getMapConfigLocalBody = (GetMapConfigLocalBody)
				 * lstdetail.get(i); if (lstdetail.get(i).getTier_setup_code()
				 * != null) { viewmaplstdetail.add(getMapConfigLocalBody); }
				 * 
				 * } configureMapForm = new ConfigureMapForm();
				 * configureMapForm.setViewmaplstdetail(viewmaplstdetail); mv =
				 * new ModelAndView("configmapLGDMview");
				 * mv.addObject("viewmaplstdetail", viewmaplstdetail);
				 * mv.addObject("configureMapForm", configureMapForm);
				 */
				/*
				 * String aMessage="Configure Map  is saved successfully!!";
				 * ModelAndView mav=new ModelAndView("configview");
				 * mav.addObject("msgid", aMessage);
				 */

				return mv;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	// ############## get method of map constituency
	// ##############################
	@SuppressWarnings("unused")
	@RequestMapping(value = "/config_constituency", method = RequestMethod.GET)
	public ModelAndView showFormConstituency(HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			setGlobalParams(httpSession);
			ConfigureMapForm configureMapForm = null;
			List<ConfigurationMapConstituency> listConfigurationMapConstituency = null;
			listConfigurationMapConstituency = new ArrayList<ConfigurationMapConstituency>();
			listConfigurationMapConstituency = configMapService.getConfigureMapConstituencyDetail(stateCode);
			if (listConfigurationMapConstituency != null) {
				// System.out.println("in if block");
				configureMapForm = new ConfigureMapForm();
				configureMapForm.setListConfigurationMapConstituency(listConfigurationMapConstituency);
				mav = new ModelAndView("modifyConfigMapConstituncy");
				mav.addObject("listConfigurationMapConstituency", listConfigurationMapConstituency);
				mav.addObject("configureMapForm", configureMapForm);
				// return "configConstituency.htm";
				return mav;
			} else if (listConfigurationMapConstituency == null) {
				configureMapForm = new ConfigureMapForm();
				mav = new ModelAndView("configConstituency");
				configureMapForm.setIsmapuploadParliament(true);
				configureMapForm.setIsmapuploadAssembly(true);
				mav.addObject("config", configureMapForm);
				return mav;
			} else {
				mav = new ModelAndView("error");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// ############## get method of map constituency
	// ##############################

	// ############## post method of map constituency
	// ##############################
	@RequestMapping(value = "/typeOfConstituncy", method = RequestMethod.POST)
	public ModelAndView typeOfConstituncy(@ModelAttribute("config") ConfigureMapForm config, BindingResult result, SessionStatus status, HttpSession httpSession, HttpServletRequest request) {

		
		ModelAndView mv1 = new ModelAndView("configConstituency", result.getModel());
		ModelAndView mav;
		try {
			configMapConstituencyValidator.validate(config, result);
			if (result.hasErrors()) {
				return mv1;
			}
			// String aMessage="Constituency Configuration Saved Successfully";
			configMapService.addvaluesbody(config, stateCode);
			mav = null;
			ConfigureMapForm configureMapForm = null;
			List<ConfigurationMapConstituency> listConfigurationMapConstituency = null;
			listConfigurationMapConstituency = new ArrayList<ConfigurationMapConstituency>();
			listConfigurationMapConstituency = configMapService.getConfigureMapConstituencyDetail(stateCode);
			configureMapForm = new ConfigureMapForm();
			configureMapForm.setListConfigurationMapConstituency(listConfigurationMapConstituency);
			// ModelAndView mv=new ModelAndView("modifyConstituncy");
			mav = new ModelAndView("modifyConfigMapConstituncy");
			mav.addObject("listConfigurationMapConstituency", listConfigurationMapConstituency);
			mav.addObject("configureMapForm", configureMapForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	// ############## post method of map constituency
	// ##############################

	// ############## get method of map constituency modify
	// ##############################

	@RequestMapping(value = "/modifyConstituncy", method = RequestMethod.GET)
	public ModelAndView modifyConstituency(HttpServletRequest request) {
		ConfigureMapForm configureMapForm = new ConfigureMapForm();
		ModelAndView mav;

		try {
			EsapiEncoder.encode(configureMapForm);
			mav = new ModelAndView("modifyConfigMapConstituncy");
			mav.addObject("config", configureMapForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/viewConfigMapConstituncy", method = RequestMethod.POST)
	public ModelAndView updateConstituency(@ModelAttribute("configureMapForm") ConfigureMapForm configureMapForm, BindingResult result, HttpServletRequest request, HttpSession httpSession) {
	
		ModelAndView mv;
		try {
			
			for (int i = 0; i < configureMapForm.getListConfigurationMapConstituency().size(); i++) {
				@SuppressWarnings("unused")
				char type = configureMapForm.getListConfigurationMapConstituency().get(i).getConstituencyType();
				/*
				 * if (type == 'P') {
				 * 
				 * } else if (type == 'A') {
				 * 
				 * } else { System.out.println("type not found..."); }
				 */
			}
			List<ConfigurationMapConstituency> listConfigurationMapConstituency = null;
			listConfigurationMapConstituency = new ArrayList<ConfigurationMapConstituency>();
			listConfigurationMapConstituency = configMapService.getConfigureMapConstituencyDetail(stateCode);
			httpSession.setAttribute("listConfigurationMapConstituency", listConfigurationMapConstituency);
			ModelAndView mv1 = new ModelAndView("viewConfigMapConstituncy", result.getModel());
			configMapConstituencyValidator.validateModify(configureMapForm, result);
			if (result.hasErrors()) {
				return mv1;
			}
			// configureMapForm.setListConfigurationMapConstituency(listConfigurationMapConstituency);

			boolean suc = false;
			mv = null;
			suc = configMapService.lgdmconstituencyMapUpdate(configureMapForm, stateCode, httpSession);
			if (suc) {
				// String
				// aMessage="Land Region Manager's data Updated Successfully.";
				mv = new ModelAndView("redirect:config_constituency.htm");
				// mv.addObject("configGovtOrderForm",configGovtOrderForm);
				// mv.addObject("msgid", aMessage);
			} else {
				String aMessage = "Error Occured";
				mv = new ModelAndView("error");
				mv.addObject("msgid", aMessage);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

}
