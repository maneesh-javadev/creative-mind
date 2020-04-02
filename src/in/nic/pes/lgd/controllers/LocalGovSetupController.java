package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.ConfigurationMapLocalbody;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LocalBodySetup;
import in.nic.pes.lgd.bean.LocalBodySubtype;
import in.nic.pes.lgd.bean.LocalBodyTiersSetup;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalbodyTypeInState;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.common.ReleaseResources;
import in.nic.pes.lgd.dao.LocalBodySetupDAO;
import in.nic.pes.lgd.forms.DesignationForm;
import in.nic.pes.lgd.forms.LGSetupForm;
import in.nic.pes.lgd.service.ComboFillingService;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConfigMapService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.validator.GovtsetupValidator;
import in.nic.pes.lgd.validator.LocalGovSetupValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
@SessionAttributes({ "LgTypeDetails" })
public class LocalGovSetupController { // NO_UCD (unused code)
	
	private static final Logger log = Logger.getLogger(LocalGovSetupController.class);

	@Autowired
	LocalGovtSetupService localGovtSetupService;

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	GovtsetupValidator govtsetupValidator;
	
	@Autowired
	ComboFillingService comboFillingService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	ConfigMapService configMapService;
	
	@Autowired
	LocalBodySetupDAO localBodySetupDAO;

	// @Autowired
	LocalGovSetupValidator localGovSetupValidator = new LocalGovSetupValidator();

	List<List<LocalBodyType>> localBodyTypeListToSave = new ArrayList<List<LocalBodyType>>();
	int j 				= 0;
	
	int slc 			= 0;
	boolean isActive 	= true;
	char category;
	

	
	private Integer stateCode;

	private Integer districtCode;

	private Integer userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId().intValue();
	}
	
	
	
	@RequestMapping(value = "/local_gov_setupUrban", method = RequestMethod.GET)
	public ModelAndView defineLocalGovSetupGetUrban(@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm, Model model, HttpSession httpSession, HttpServletRequest request) {
		category = 'U';
		Session session = sessionFactory.openSession();
		ModelAndView mv = null;
		try {
			
			if (stateCode == null || userId == null) {
				return mv = new ModelAndView("redirect:login.htm");
			}
			
			List<GetLocalGovtSetup> getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, category);

			httpSession.setAttribute("category", category);
			if (getLocalGovtSetupList.size() > 0) {
				EsapiEncoder.encode(getLocalGovtSetupList);
				model.addAttribute("getLocalGovtSetupList", getLocalGovtSetupList);
				mv = new ModelAndView();
				mv.setViewName("viewTierSetup");
				mv.addObject("category", category);
				return mv;
			}
			List<LGSetupForm> LgTypeDetails = localGovtSetupService.getLocalGovtTypeDB(category,session);
			String Default = "Default";
			model.addAttribute("size", LgTypeDetails.size());
			EsapiEncoder.encode(LgTypeDetails);
			model.addAttribute("LgTypeDetails", LgTypeDetails);
			mv = new ModelAndView();
			mv.setViewName("localgovtsetup");
			mv.addObject("value", Default);
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
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mv;
	}

	@RequestMapping(value = "/local_gov_setupPanchayat", method = RequestMethod.GET)
	public ModelAndView local_gov_setupPanchayat(@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm, Model model, HttpSession httpSession, HttpServletRequest request) {
		category = 'P';
		Session session = sessionFactory.openSession();
		ModelAndView mv = null;
		try {
			
			if (stateCode == null || userId == null) {
				return mv = new ModelAndView("redirect:login.htm");
			}
			
			List<GetLocalGovtSetup> getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, category);

			httpSession.setAttribute("category", category);
			if (getLocalGovtSetupList.size() > 0) {
				EsapiEncoder.encode(getLocalGovtSetupList);
				model.addAttribute("getLocalGovtSetupList",
						getLocalGovtSetupList);
				mv = new ModelAndView();
				mv.setViewName("viewTierSetup");
				mv.addObject("category", category);
				return mv;
			}
			List<LGSetupForm> LgTypeDetails = localGovtSetupService.getLocalGovtTypeDB(category,session);
			String Default = "Default";
			model.addAttribute("size", LgTypeDetails.size());
			EsapiEncoder.encode(LgTypeDetails);
			model.addAttribute("LgTypeDetails", LgTypeDetails);
			mv = new ModelAndView();
			mv.setViewName("localgovtsetup");
			mv.addObject("value", Default);
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
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mv;
	}

	@RequestMapping(value = "/local_gov_setupTraditional", method = RequestMethod.GET)
	public ModelAndView defineLocalGovSetupGetTraditional(@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm, Model model, HttpSession httpSession, HttpServletRequest request) {
		category = 'T';
		ModelAndView mv = null;
		Session session =null;
		try {
			
			if (stateCode == null || userId == null) {
				return mv = new ModelAndView("redirect:login.htm");
			}
			
			List<GetLocalGovtSetup> getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, category);
			session = sessionFactory.openSession();
			httpSession.setAttribute("category", category);
			if (getLocalGovtSetupList.size() > 0) {
				EsapiEncoder.encode(getLocalGovtSetupList);
				model.addAttribute("getLocalGovtSetupList",getLocalGovtSetupList);
				mv = new ModelAndView();
				mv.setViewName("viewTierSetup");
				mv.addObject("category", category);
				return mv;
			}
			List<LGSetupForm> LgTypeDetails = localGovtSetupService.getLocalGovtTypeDB(category,session);
			String Default = "Default";
			model.addAttribute("size", LgTypeDetails.size());
			EsapiEncoder.encode(LgTypeDetails);
			model.addAttribute("LgTypeDetails", LgTypeDetails);
			mv = new ModelAndView();
			mv.setViewName("localgovtsetup");
			mv.addObject("value", Default);
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
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mv;
	}

	@RequestMapping(value = "/viewSubtype")
	public ModelAndView viewLbSubtype(@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm, Model model, HttpSession httpSession,HttpServletRequest request)
	{
		ModelAndView mv = null;
		int tierSetupCode = 0;
		tierSetupCode=lGSetupForm.getTierSetupID();
		try {
			
			if (stateCode == null || userId == null) {
				return mv = new ModelAndView("redirect:login.htm");
			}
			
			List<LocalBodySubtype> localBodySubtypeList = localGovtSetupService.getSubTypeDetails(tierSetupCode);

			if (localBodySubtypeList.size() > 0) {
				EsapiEncoder.encode(localBodySubtypeList);
				model.addAttribute("localBodySubtypeList", localBodySubtypeList);
				mv = new ModelAndView();
				mv.setViewName("viewLbSubtype");
				mv.addObject("category", category);
			} else {
				mv = new ModelAndView("errorConfigView");
				mv.addObject("message","Local Body Grade is Not defined for this Government Type");
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

	@RequestMapping(value = "/modifyLbSetup", method = RequestMethod.POST)
	public ModelAndView modifyLocalbodySetup(@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm, 
											 Model model, 
											 HttpSession httpSession,
											 HttpServletRequest request, 
											 @RequestParam("category") char category, 
											 @RequestParam("isCorrection") Boolean isCorrection) {
		Session session = sessionFactory.openSession();
		ModelAndView mv = null;
		try {
			List<LGSetupForm> LgTypeDetails = localGovtSetupService.getLocalGovtTypeDB(category,session);
			List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
			

			getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, category);

			LGSetupForm lGSetupFormBean = null;
			for (int i = 0; i < LgTypeDetails.size(); i++) {
				for (int j = 0; j < getLocalGovtSetupList.size(); j++) {
					if (LgTypeDetails.get(i).getLocalBodyTypeCode().equals(getLocalGovtSetupList.get(j).getLocalBodyTypeCode())) {
						LgTypeDetails.remove(i);
						lGSetupFormBean = new LGSetupForm();
						lGSetupFormBean.setNomenEnglish(getLocalGovtSetupList.get(j).getNomenclatureEnglish());
						lGSetupFormBean.setNomenLocal(getLocalGovtSetupList.get(j).getNomenclatureLocal());
						lGSetupFormBean.setLocalBodyTypeCode(getLocalGovtSetupList.get(j).getLocalBodyTypeCode());
						lGSetupFormBean.setLocalBodyTypeName(getLocalGovtSetupList.get(j).getLocalBodyTypeName());
						lGSetupFormBean.setLevel(getLocalGovtSetupList.get(j).getLevel());
						lGSetupFormBean.setTierSetupID(getLocalGovtSetupList.get(j).getTierSetupCode());
						LgTypeDetails.add(i, lGSetupFormBean);
					}
				}
			}
			String modify = "modify";
			mv = new ModelAndView();
			mv.setViewName("modifyLgSetup");
			EsapiEncoder.encode(LgTypeDetails);
			mv.addObject("LgTypeDetails", LgTypeDetails);
			mv.addObject("size", LgTypeDetails.size());
			mv.addObject("value", modify);
			if(isCorrection != null && isCorrection){
				mv.addObject("isCorrection", isCorrection);
				mv.addObject("category", category);
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
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mv;
	}

	@RequestMapping(value = "/local_gov_setup_step2", method = RequestMethod.POST)
	public ModelAndView defineLocalGovSetupPost(@ModelAttribute("lGSetupForm") @Valid LGSetupForm lGSetupForm, BindingResult result, Model model, HttpSession httpSession, @RequestParam(value = "modify") String value, HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		ModelAndView mv = null;
		try {
			List<LocalBodyType> localBodyTypeList = null;
			localGovSetupValidator.localGovtSetupValidation(lGSetupForm, result);
			if (result.hasErrors()) {
				if (value.equals("modify")) {
					// if (category == 'U') {
					List<LGSetupForm> LgTypeDetails = localGovtSetupService.getLocalGovtTypeDB(category,session);
					model.addAttribute("size", LgTypeDetails.size());
					EsapiEncoder.encode(LgTypeDetails);
					model.addAttribute("LgTypeDetails", LgTypeDetails);
					mv = new ModelAndView();
					mv.setViewName("modifyLgSetup");
					EsapiEncoder.encode(LgTypeDetails);
					mv.addObject("LgTypeDetails", LgTypeDetails);
					mv.addObject("size", LgTypeDetails.size());
					mv.addObject("value", "modify");
					mv.addObject(lGSetupForm);

					// }
				} else if (value.equals("Default")) {
					// if (category == 'U') {
					List<LGSetupForm> LgTypeDetails = localGovtSetupService.getLocalGovtTypeDB(category,session);
					String Default = "Default";
					model.addAttribute("size", LgTypeDetails.size());
					EsapiEncoder.encode(LgTypeDetails);
					model.addAttribute("LgTypeDetails", LgTypeDetails);
					mv = new ModelAndView();
					mv.setViewName("localgovtsetup");
					mv.addObject("value", Default);
					mv.addObject(lGSetupForm);
					// }
				}

			} else {
				if (category == 'T') {
					mv = new ModelAndView("local_gov_setup_step2");
					localBodyTypeList = new ArrayList<LocalBodyType>();
					localBodyTypeList = localGovtSetupService.loopThroughTiers(lGSetupForm);
					if (localBodyTypeList != null) {
						if (value.equals("modify")) {
							updateLocalBodies(request, category);
							model.addAttribute("size", localBodyTypeList.size());
							model.addAttribute("localBodyTypeList", localBodyTypeList);
							String modify = "modify";
							model.addAttribute("value", modify);
						} else if (value.equals("Default")) {
							String Default = "Default";
							model.addAttribute("value", Default);
							localBodyTypeList = new ArrayList<LocalBodyType>();
							String str = this.defineLocalGovSetupPanchayat(lGSetupForm, model, value, httpSession, request);
							mv = new ModelAndView(str);
						}
						httpSession.setAttribute("localBodyList", localBodyTypeList);
					}
				} else if (category == 'P' || category == 'p') {
					if (value.equals("modify")) {
						updateLocalBodies(request, category);
						localBodyTypeList = new ArrayList<LocalBodyType>();
						String str = this.defineLocalGovSetupPanchayat(lGSetupForm, model, value, httpSession, request);
						mv = new ModelAndView(str);
						String modify = "modify";
						model.addAttribute("value", modify);
						
					} else if (value.equals("Default")) {
						String Default = "Default";
						model.addAttribute("value", Default);
						localBodyTypeList = new ArrayList<LocalBodyType>();
						String str = this.defineLocalGovSetupPanchayat(lGSetupForm, model, value, httpSession, request);
						mv = new ModelAndView(str);
					}
				}

				else if (category == 'U' || category == 'u') {
					
					if (value.equals("modify")) {
						updateLocalBodies(request, category);
						localBodyTypeList = new ArrayList<LocalBodyType>();
						String str = this.defineLocalGovSetupUrban(lGSetupForm, model, value, httpSession, request);
						mv = new ModelAndView(str);
						String modify = "modify";
						model.addAttribute("value", modify);
					} else if (value.equals("Default")) {
						String Default = "Default";
						model.addAttribute("value", Default);
						localBodyTypeList = new ArrayList<LocalBodyType>();
						String str = this.defineLocalGovSetupUrban(lGSetupForm, model, value, httpSession, request);
						mv = new ModelAndView(str);
					}

				} else {
					String message = "Invalid Category";
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
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/local_gov_setup_step3", method = RequestMethod.POST)
	public String defineLocalGovSetupPostStep2(
			@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm,
			Model model, HttpSession httpSession,
			@RequestParam("modify") String value, HttpServletRequest request) {

		try {
			List<LocalBodyType> localBodyTypeList = null;
			localBodyTypeList = new ArrayList<LocalBodyType>();
			List<LocalBodyType> localBodyTypeListSession = null;
			localBodyTypeListSession = new ArrayList<LocalBodyType>();
			localBodyTypeListSession = (List<LocalBodyType>) httpSession.getAttribute("localBodyList");
			char localBodylevel;
			String localBodyNomenEng = null;
			int localBodyTypeCode = 0;
			String localBodyNomenLocal = null;
			String tempConcat = null;
			LocalBodyType localBodyTypeBean = null;

			String[] tempLBTList = null;
			if (lGSetupForm.getlBTList() != "") {
				tempLBTList = lGSetupForm.getlBTList().split(",");
			}
			if (tempLBTList != null) {
				for (int i = 0; i < tempLBTList.length; i++) {
					localBodyTypeBean = new LocalBodyType();
					String[] temp = tempLBTList[i].split(":");
					localBodyTypeCode = Integer.parseInt(temp[0]);
					localBodylevel = temp[1].charAt(0);
					localBodyNomenEng = temp[2];
					if (temp.length == 4) {
						localBodyNomenLocal = temp[3];
					}
					localBodyTypeBean.setLocalBodyTypeCode(localBodyTypeCode);
					localBodyTypeBean.setLevel(localBodylevel);

					tempConcat = localBodyTypeCode + ":" + localBodylevel + ":"
							+ localBodyNomenEng;
					if (localBodyNomenLocal != null) {
						tempConcat += ":" + localBodyNomenLocal;
					}
					localBodyNomenEng = null;
					localBodyNomenLocal = null;
					localBodyTypeBean.setTemp(tempConcat);
					localBodyTypeList.add(localBodyTypeBean);

				}
			}
			if (localBodyTypeList != null) {
				for (int i = 0; i < localBodyTypeList.size(); i++)
					localBodyTypeListSession.remove(localBodyTypeList.get(i));
				localBodyTypeListToSave.add(j, localBodyTypeList);
				j += 1;
			}
			if (value.equals("modify")) {
				String modify = "modify";
				model.addAttribute("value", modify);
				model.addAttribute("hasTiers", "true");
			} else if (value.equals("Default")) {
				String Default = "Default";
				model.addAttribute("value", Default);
				model.addAttribute("hasTiers", "true");
			}
			EsapiEncoder.encode(localBodyTypeListSession);
			model.addAttribute("localBodyTypeList", localBodyTypeListSession);
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}
		return "local_gov_setup_step2";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/local_gov_setup_step4", method = RequestMethod.POST)
	public String defineLocalGovSetupPostStep3(
			@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm,
			Model model, HttpSession httpSession,
			@RequestParam("modify") String value, HttpServletRequest request) {
		try {
			List<LocalBodyType> localBodyTypeList = null;
			localBodyTypeList = new ArrayList<LocalBodyType>();

			List<LocalBodyType> localBodyTypeListSession = null;
			localBodyTypeListSession = new ArrayList<LocalBodyType>();

			
			isActive=true;
			//slc=6;
			
			localBodyTypeListSession = (List<LocalBodyType>) httpSession.getAttribute("localBodyList");
			char localBodylevel;
			String localBodyNomenEng = null;
			int localBodyTypeCode;
			String localBodyNomenLocal = null;
			String tempConcat = null;
			/*System.out.println("lGSetupForm.getlBTList();"
					+ lGSetupForm.getlBTList());*/
			LocalBodyType localBodyTypeBean = null;
			if (lGSetupForm.getlBTList() != "") {
				String[] tempLBTList = lGSetupForm.getlBTList().split(",");
				if (tempLBTList.length > 1) {
					for (int i = 0; i < tempLBTList.length; i++) {
						localBodyTypeBean = new LocalBodyType();
						String[] temp = tempLBTList[i].split(":");
						localBodyTypeCode = Integer.parseInt(temp[0]);
						localBodylevel = temp[1].charAt(0);
						if (temp.length > 2) {
							localBodyNomenEng = temp[2];
						}
						if (temp.length == 4) {
							localBodyNomenLocal = temp[3];
						}
						localBodyTypeBean
								.setLocalBodyTypeCode(localBodyTypeCode);
						localBodyTypeBean.setLevel(localBodylevel);

						tempConcat = localBodyTypeCode + ":" + localBodylevel
								+ ":" + localBodyNomenEng;
						if (localBodyNomenLocal != null) {
							tempConcat += ":" + localBodyNomenLocal;
						}
						localBodyNomenEng = null;
						localBodyNomenLocal = null;
						localBodyTypeBean.setTemp(tempConcat);
						localBodyTypeList.add(localBodyTypeBean);

					}
					for (int i = 0; i < localBodyTypeList.size(); i++) {
						if (localBodyTypeListSession.size() > 0) {
							localBodyTypeListSession.remove(localBodyTypeList
									.get(i));
						}
					}
					localBodyTypeListToSave.add(j, localBodyTypeList);
					j++;
				}
			}
			boolean success = false;
			if (value.equals("modify")) {
				String modify = "modify";
				model.addAttribute("value", modify);
				if (localBodyTypeListToSave.size() > 0) {
					success = localGovtSetupService.modify(stateCode,
							localBodyTypeListToSave, category);
				}
			} else if (value.equals("Default")) {
				String Default = "Default";
				model.addAttribute("value", Default);
				if (localBodyTypeListToSave.size() > 0) {
					success = localGovtSetupService.saveLocalGovSetup(
							localBodyTypeListToSave, stateCode);
				}

			}
			localBodyTypeListToSave.clear();
			j = 0;
			httpSession.setAttribute("localBodyList", null);
			List<LocalbodyTypeInState> listLBT = null;
			listLBT = new ArrayList<LocalbodyTypeInState>();
			if (success) {
				listLBT = localGovtSetupService.getActiveLBTinState(stateCode,
						category);
			}
			if (listLBT.size() > 0) {

				model.addAttribute("size", listLBT.size());
				model.addAttribute("listLBT", listLBT);
			} else {
				return "error";
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}

		return "local_gov_setup_step3";

	}

	@RequestMapping(value = "/local_gov_setup_step5", method = RequestMethod.POST)
	public ModelAndView defineLocalGovSetupPostStep4(
			@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm,
			Model model, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mv;
		try {
			@SuppressWarnings("unused")
			List<LocalBodyType> localBodyTypeList = new ArrayList<LocalBodyType>();
			boolean success = false;
			try {
				success = localGovtSetupService.saveLocalBodySubType(
						lGSetupForm, stateCode, httpSession);
			} catch (Exception e) {
				// TODO defineLocalGovSetupPostStep4
				log.debug("Exception" + e);
			}
			mv = null;
			char category = ' ';
			if (httpSession.getAttribute("category") != null) {
				category = httpSession.getAttribute("category").toString()
						.charAt(0);
			}
			httpSession.removeAttribute("category");
			if (success) {
				if (category == 'U')
					mv = new ModelAndView("redirect:local_gov_setupUrban.htm");
				else if (category == 'P')
					mv = new ModelAndView(
							"redirect:local_gov_setupPanchayat.htm");
				else if (category == 'T')
					mv = new ModelAndView(
							"redirect:local_gov_setupTraditional.htm");
				else {
					String message = "Invalid Category";
					mv = new ModelAndView("error");
					mv.addObject("message", message);
				}

			} else {
				String message = "Subtype Tier Setup Failed";
				mv = new ModelAndView("error");
				mv.addObject("message", message);
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

		return mv;
	}

	/*
	 * @RequestMapping(value="/local_gov_setup",method=RequestMethod.POST)
	 * public String defineLocalGovSetupPost(@ModelAttribute("lGSetupForm")
	 * LGSetupForm lGSetupForm, Model model, @RequestParam("mode")String mode){
	 * boolean test=false; if (mode.equals("UPDATE")) test =
	 * localGovtSetupService.saveLocalGovSetup(lGSetupForm, false); else test =
	 * localGovtSetupService.saveLocalGovSetup(lGSetupForm, true);
	 * 
	 * if (test == true) { model.addAttribute("msgid",
	 * "Local Government Details Has Been Successfully Saved. !"); return
	 * "redirect:viewTierSetup.htm"; } else model.addAttribute("msgid",
	 * "Local Government Details Hasn't Been Saved. Kindly Re-enter the Details. !"
	 * ); return "success"; }
	 */

	@RequestMapping(value = "/modify_lgsetup", method = RequestMethod.GET)
	public String modifyLocalGovSetupGet(
			@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm,
			Model model, HttpSession httpSession, HttpServletRequest request) {
		// check
		try {
			if (stateCode == null) {
				return "redirect:login.htm";
			}
			
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
		return "localgovtsetup";
	}
	
	@RequestMapping(value = "/designation_hierarchy_elected", method = RequestMethod.POST)
	public String designationHierarchy(
			@ModelAttribute("designationForm") DesignationForm designationForm,BindingResult br,
			Model model, HttpSession httpSession,HttpServletRequest request) {
		Session session = null;
		try {
			
			govtsetupValidator.validate(designationForm, br);
			if (br.hasErrors()) 
			{
				char passvalue='\0';
				if(designationForm.getDesigtype()=='P'){
					passvalue='R';
				}	
				else if(designationForm.getDesigtype()=='T'|| designationForm.getDesigtype()=='U'){
					passvalue=designationForm.getDesigtype();
				}	
				
					List<LocalBodyType> lgt = localGovtSetupService
							.getLGTypeForDesignation(stateCode, passvalue);
					designationForm.setDesigtype(designationForm.getDesigtype());
					if (lgt.size() > 0) {
						model.addAttribute("lgT", lgt);
						model.addAttribute("tierSetupCode",designationForm.getLgTypeCode());
						return "designationhierarchyElected";
					}
			}
			int tierSetupCode = 0;
			boolean isLocalBody = false;
			if (designationForm.getIsLocalBody() == 'E'){
				isLocalBody = true;
			}	
			
			tierSetupCode = localGovtSetupService.saveDesignation(designationForm);
			
			if (tierSetupCode > 0) {
				session = sessionFactory.openSession();
				model.addAttribute(
						"NewDesig",
						session.createQuery(
								"from Designation where tierSetupCode="
										+ tierSetupCode + " and islocalbody="
										+ isLocalBody
										+ " order by designationCode").list());
				if(designationForm.getOpeation()=='I')
					model.addAttribute("successMsg","The ER Designations were saved successfully");
				else
					model.addAttribute("successMsg","The ER designations were modified successfully");
				ReleaseResources.doReleaseResources(session);
				return "viewDesignationSuccessElectedOfficial";
			} else {
				model.addAttribute("msgid",
						"Designation Hierarchy Failed To Save. Kindly Re-enter the Details. !");
				return "success";
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		} catch (HibernateException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@RequestMapping(value = "/designation_hierarchy_panchayat", method = RequestMethod.POST)
	public String designationHierarchyP(
			@ModelAttribute("designationForm") DesignationForm designationForm,BindingResult br,
			Model model, HttpSession httpSession, HttpServletRequest request) {
		Session session =null;
		try {
			govtsetupValidator.validate(designationForm, br);
			if (br.hasErrors()) 
			{
				char passvalue='\0';
				if(designationForm.getDesigtype()=='P'){
					passvalue='R';
				}	
				else if(designationForm.getDesigtype()=='T'|| designationForm.getDesigtype()=='U'){
					passvalue=designationForm.getDesigtype();
				}	
				
					List<LocalBodyType> lgt = localGovtSetupService
							.getLGTypeForDesignation(stateCode, passvalue);
					designationForm.setDesigtype(designationForm.getDesigtype());
					if (lgt.size() > 0) {
						model.addAttribute("lgT", lgt);
						model.addAttribute("desgName",designationForm.getDesgName());
						model.addAttribute("desgNameLocal",designationForm.getDesgNameLocal());
						return "designationhierarchyPanchayat";
					}
			}
			int tierSetupCode = 0;
			
			tierSetupCode = localGovtSetupService.saveDesignation(designationForm);
			if (tierSetupCode > 0) {
				boolean isLocalBody = false;
				if (designationForm.getIsLocalBody() == 'E'){
					isLocalBody = true;
				}	
				session = sessionFactory.openSession();
				model.addAttribute(
						"NewDesig",
						session.createQuery(
								"from Designation where tierSetupCode="
										+ tierSetupCode + " and isLocalBody="
										+ isLocalBody
										+ " order by designationCode").list());
				ReleaseResources.doReleaseResources(session);
				if(designationForm.getOpeation()=='I')
					model.addAttribute("successMsg","The Official Designations  were saved successfully");
				else
					model.addAttribute("successMsg","The Official Designations  were modified successfully");
				
				return "viewDesignationSuccessElectedOfficial";
			} else {
				model.addAttribute("msgid",
						"Designation Hierarchy Failed To Save. Kindly Re-enter the Details. !");
				return "success";
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		} catch (HibernateException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@ModelAttribute
	public LGSetupForm get() {
		return new LGSetupForm();
	}

	

	public String defineLocalGovSetupPanchayat(LGSetupForm lGSetupForm, Model model, String value, HttpSession httpSession, HttpServletRequest request) {
		try {
			List<LocalBodyType> localBodyTypeList = null;
			localBodyTypeList = new ArrayList<LocalBodyType>();
			String check = lGSetupForm.getCheck();
			String LocalBodyTypeCode = lGSetupForm.getTemp1();
			String NomenEnglishList = lGSetupForm.getNomenEnglish();
			String NomenLocalList = lGSetupForm.getNomenLocal();
			String LevelLGBList = lGSetupForm.getLevelLGB();
			String LocalBodyTypeNameList = lGSetupForm.getLocalBodyTypeName();
			String[] tempcheckList = null;
			String[] tempLocalBodyTypeCodeList = null;
			String[] tempNomenEnglishList = null;
			String[] tempNomenLocalList = null;
			String[] tempLevelLGBList = null;
			String[] tempLocalBodyTypeNameList = null;
			if (check != null) {
				tempcheckList = check.split(",");
			}
			if (LocalBodyTypeCode != null) {
				tempLocalBodyTypeCodeList = LocalBodyTypeCode.split(",");
			}
			if (NomenEnglishList != null) {
				tempNomenEnglishList = NomenEnglishList.replace(",", ", ").split(",");
			}
			if (NomenLocalList != null) {
				tempNomenLocalList = NomenLocalList.replace(",", ", ").split(",");
			}
			if (LevelLGBList != null) {
				tempLevelLGBList = LevelLGBList.split(",");
			}
			if (LevelLGBList != null) {
				tempLocalBodyTypeNameList = LocalBodyTypeNameList.split(",");
			}
			LocalBodyType localBodyTypeBean = null;
			int j = 0;
			String tempConcat = null;
			if (tempcheckList != null) {
				for (int i = 0; i < tempLocalBodyTypeCodeList.length; i++) {
					localBodyTypeBean = new LocalBodyType();
					if (tempcheckList[j].equals(Integer.toString(i))) {
						localBodyTypeBean.setLocalBodyTypeCode(Integer.parseInt(tempLocalBodyTypeCodeList[i]));
						tempConcat = tempLocalBodyTypeCodeList[i] + ":"+ tempLevelLGBList[i] + ":"+ tempNomenEnglishList[j];
						tempConcat += ":" + tempNomenLocalList[j];
						localBodyTypeBean.setTemp(tempConcat);
						localBodyTypeBean.setLocalBodyTypeName(tempLocalBodyTypeNameList[i]);
						localBodyTypeList.add(localBodyTypeBean);
						if (j < tempcheckList.length - 1) {
							j++;
						}
					}
				}
			}
			localBodyTypeListToSave.add(localBodyTypeList);
			boolean success = false;
			
			if (value.equals("modify")) {
				String modify = "modify";
				model.addAttribute("value", modify);
				if (localBodyTypeListToSave.size() > 0) {
					success = localGovtSetupService.modify(stateCode,localBodyTypeListToSave, category);
				}
			} else if (value.equals("Default")) {
				String Default = "Default";
				model.addAttribute("value", Default);
				if (localBodyTypeListToSave.size() > 0) {
					success = localGovtSetupService.saveLocalGovSetup(localBodyTypeListToSave, stateCode);
				}
			}
			localBodyTypeListToSave.clear();
			j = 0;
			List<LocalbodyTypeInState> listLBT = null;
			listLBT = new ArrayList<LocalbodyTypeInState>();
			if (success) {
				listLBT = localGovtSetupService.getActiveLBTinState(stateCode, category);
			}
			if (listLBT.size() > 0) {
				model.addAttribute("size", listLBT.size());
				model.addAttribute("listLBT", listLBT);
			} else {
				return "error";
			}
			return "local_gov_setup_step3";
		} catch (NumberFormatException e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}

	public String defineLocalGovSetupUrban(LGSetupForm lGSetupForm,
			Model model, String value, HttpSession httpSession,
			HttpServletRequest request) {
		try {
			List<LocalBodyType> localBodyTypeList = null;
			localBodyTypeList = new ArrayList<LocalBodyType>();
			String check = lGSetupForm.getCheck();
			String LocalBodyTypeCode = lGSetupForm.getTemp1();
			String NomenEnglishList = lGSetupForm.getNomenEnglish();
			String NomenLocalList = lGSetupForm.getNomenLocal();
			String LevelLGBList = lGSetupForm.getLevelLGB();
			String LocalBodyTypeNameList = lGSetupForm.getLocalBodyTypeName();
			String[] tempcheckList = null;
			String[] tempLocalBodyTypeCodeList = null;
			String[] tempNomenEnglishList = null;
			String[] tempNomenLocalList = null;
			String[] tempLevelLGBList = null;
			String[] tempLocalBodyTypeNameList = null;
			try {
				if (check != null) {
					tempcheckList = check.split(",");
				}
				if (LocalBodyTypeCode != null) {
					tempLocalBodyTypeCodeList = LocalBodyTypeCode.split(",");
				}
				if (NomenEnglishList != null) {
					tempNomenEnglishList = NomenEnglishList.replace(",", " , ")
							.split(",");
				}
				if (NomenLocalList != null) {
					tempNomenLocalList = NomenLocalList.replace(",", " , ")
							.split(",");
				}
				if (LevelLGBList != null) {
					tempLevelLGBList = LevelLGBList.split(",");
				}
				if (LevelLGBList != null) {
					tempLocalBodyTypeNameList = LocalBodyTypeNameList
							.split(",");
				}
			} catch (Exception e1) {
				//e1.printStackTrace();
				throw e1;
			}
			LocalBodyType localBodyTypeBean = null;
			int j = 0;
			String tempConcat = null;
			try {
				if (tempcheckList != null) {
					for (int i = 0; i < tempLocalBodyTypeCodeList.length; i++) {
						localBodyTypeBean = new LocalBodyType();
						if (tempcheckList[j].equals(Integer.toString(i))) {
							localBodyTypeBean.setLocalBodyTypeCode(Integer
									.parseInt(tempLocalBodyTypeCodeList[i]));
							tempConcat = tempLocalBodyTypeCodeList[i] + ":"
									+ tempLevelLGBList[i] + ":"
									+ tempNomenEnglishList[j];
							tempConcat += ":" + tempNomenLocalList[j];
							localBodyTypeBean.setTemp(tempConcat);
							localBodyTypeBean
									.setLocalBodyTypeName(tempLocalBodyTypeNameList[i]);
							localBodyTypeList.add(localBodyTypeBean);
							if (j < tempcheckList.length - 1) {
								j++;
							}
						}
					}
				}
			} catch (NumberFormatException e) {
				log.debug("Exception" + e);
			}
			localBodyTypeListToSave.add(localBodyTypeList);
			boolean success = false;
			if (value.equals("modify")) {
				String modify = "modify";
				model.addAttribute("value", modify);
				if (localBodyTypeListToSave.size() > 0) {
					success = localGovtSetupService.modifyUrban(stateCode,
							localBodyTypeListToSave, category);
				}
			} else if (value.equals("Default")) {
				String Default = "Default";
				model.addAttribute("value", Default);
				if (localBodyTypeListToSave.size() > 0) {
					success = localGovtSetupService.saveLocalGovSetupUrban(
							localBodyTypeListToSave, stateCode);
				}

			}
			localBodyTypeListToSave.clear();
			j = 0;
			// httpSession.setAttribute("localBodyList", null);
			List<LocalbodyTypeInState> listLBT = null;
			listLBT = new ArrayList<LocalbodyTypeInState>();
			if (success) {
				listLBT = localGovtSetupService.getActiveLBTinState(stateCode,
						category);
			}
			if (listLBT.size() > 0) {
				model.addAttribute("size", listLBT.size());
				model.addAttribute("listLBT", listLBT);
			} else {
				String Default = "Error While fetching Subdistrict";
				model.addAttribute("message", Default);
				return "error";
			}
			return "local_gov_setup_step3";
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
	}

	
	/**
	 * The private method {@code updateLocalBodies} calls {@code performUpdationLocalBodies} based on	{@param process}
	 * to update corresponding local bodies. 
	 * @param request
	 * @param process
	 */
	private void updateLocalBodies(HttpServletRequest request, Character process) {
		String initialSelectedSetup = request.getParameter("initialChkedValues");
		String changedSelectedSetup = request.getParameter("updateChkedValues");
		performUpdationLocalBodies(initialSelectedSetup, changedSelectedSetup, process);
		return; 
	}
	
	/**
	 * Private method {@code performUpdationLocalBodies} used to perform database function 
	 * to update parent code or put LB in disturbed state as per {@value process} (Add/Remove) 
	 * parameter.
	 * @param initialSelectedSetup
	 * @param changedSelectedSetup
	 * @param process
	 */
	private void performUpdationLocalBodies(String initialSelectedSetup, String changedSelectedSetup, Character process){
		if(initialSelectedSetup != null && changedSelectedSetup != null){
			String removedCodes = "";
			String addedCodes = "";
			String[] arrInitial = initialSelectedSetup.split("\\,");
			String[] arrChanged = changedSelectedSetup.split("\\,");
			for(int i=0; i < arrInitial.length; i++){
				boolean flag = true;
				for(int j=0; j < arrChanged.length; j++){
					if(arrInitial[i].equals(arrChanged[j])){
						arrChanged[j] = null;
						flag = false;
						break;
					}
				}
				if(flag){
					removedCodes += arrInitial[i] + ",";
				}
			}
			for(String changedId : arrChanged){
				if(changedId != null ){
					addedCodes += changedId + ",";
				}
			}
			if(new Character('U').toString().equalsIgnoreCase(process.toString())){
				if(!removedCodes.trim().equals("")){
					localGovtSetupService.updateUrbanLocalBodies(removedCodes.substring(0, removedCodes.length()-1), stateCode, userId);
				}
			}else{
				if(!addedCodes.trim().equals("")){
					localGovtSetupService.updateRuralLocalBodies("ADD", addedCodes.substring(0, addedCodes.length()-1), stateCode, userId);
				}
				if(!removedCodes.trim().equals("")){
					localGovtSetupService.updateRuralLocalBodies("REMOVE", removedCodes.substring(0, removedCodes.length()-1), stateCode, userId);
				}
			} 
		}
		return;
	}
	
	/**
	 * The {@code correctLocalbodySetup} used for correction of the names in
	 * English / Local language  of defined local body setup.
	 * @param lGSetupForm
	 * @param model
	 * @param httpSession
	 * @param request
	 * @param correctionChkedValues
	 * @return
	 */
	@RequestMapping(value = "/correctionLbSetup", method = RequestMethod.POST)
	public ModelAndView correctLocalbodySetup(@ModelAttribute("lGSetupForm") LGSetupForm lGSetupForm,
											  Model model, 
											  HttpSession httpSession,
											  HttpServletRequest request, 
											  @RequestParam("correctionChkedValues") String correctionChkedValues) {
		
		ModelAndView mv = null;
		if (stateCode == null || userId == null) {
			return mv = new ModelAndView("redirect:login.htm");
		}
		try {
			CustomRegexValidator validator = CustomRegexValidator.getInstance();
			Scanner scanner = new Scanner(correctionChkedValues);
			scanner.useDelimiter("@@");
			boolean isCorrectionDone  = false;
			boolean isServersideValid = true;
			boolean mapConfigRequired;
			Date currentDate = new Date();
			while(scanner.hasNext()){
				mapConfigRequired = false;
				String 	 rowvalues 			= scanner.next();
				String[] columnvalues  		= rowvalues.split("\\|");
				Integer  localBodyTypeCode 	= Integer.parseInt(columnvalues[0]);
				String   nameEnglish 		= columnvalues[1];
				isServersideValid = validator.checkforAlphabetsandSpace(nameEnglish);
				if(!isServersideValid){
					break;
				}	
				String   nameLocal 			= columnvalues[2];
				isServersideValid = validator.validateSpecialCharacters(nameLocal);
				if(!isServersideValid){
					break;
				}	
				Integer  tiresetupCode 		= Integer.parseInt(columnvalues[3]);
				LocalBodyTiersSetup tiresetup = localGovtSetupService.loadTierSetUp(localBodyTypeCode, tiresetupCode);
				if(tiresetup == null){
					tiresetup = new LocalBodyTiersSetup();
					Integer maxid = localGovtSetupService.maxTierSetupCode();
					Object[] dtls = localGovtSetupService.localbodySetupCodeandVersion(stateCode, category);
					tiresetup.setTierSetupCode(maxid);
					LocalBodySetup setup = localGovtSetupService.getLocalbodySetup(stateCode, (Integer)dtls[0]);
					tiresetup.setLocalBodySetup(setup);
					tiresetup.setLocalBodySetupVersion((Integer)dtls[1]);
					tiresetup.setIsactive(true);
					tiresetup.setLocalbodytype(new LocalBodyType(localBodyTypeCode));
					mapConfigRequired = true;
				}
				tiresetup.setNomenclatureEnglish(nameEnglish);
				tiresetup.setNomenclatureLocal(nameLocal);
				if(localGovtSetupService.saveOrUpdateTierSetup(tiresetup)){
					if(mapConfigRequired){
						ConfigurationMapLocalbody map = new ConfigurationMapLocalbody();
						map.setTierSetupCode(tiresetup.getTierSetupCode());
						map.setIsactive(true);
						map.setIsmapupload(true);
						map.setLastupdated(currentDate);
						map.setLastupdatedby(userId.longValue());
						map.setCreatedon(currentDate);
						map.setCreatedby(userId);
						configMapService.save(map);
					}
					isCorrectionDone = true;
				}else{
					isCorrectionDone = false;
					break;
				}
			}
			scanner.close();
			if(!isServersideValid){
				return modifyLocalbodySetup(lGSetupForm, 
											model, 
											httpSession, 
											request, 
											category, 
											true);
			}
			List<GetLocalGovtSetup> getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, category);
			EsapiEncoder.encode(getLocalGovtSetupList);
			model.addAttribute("getLocalGovtSetupList", getLocalGovtSetupList);
			mv = new ModelAndView();
			mv.addObject("lGSetupForm" , lGSetupForm);
			mv.setViewName("viewTierSetup");
			mv.addObject("category", category);
			mv.addObject("isCorrectionDone",isCorrectionDone);
			mv.addObject("successmsg", "Goverenment Tier setup has been corrcted successfully.");
			return mv;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Process <a>/localGovSetupRural</a> to show default configuration for 
	 * Rural Government Setup for Panchyat.
	 * @param model
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/localGovSetupRural", method = RequestMethod.GET)
	public ModelAndView displayLocalGovSetupRural(Model model, HttpSession httpSession, HttpServletRequest request) {
		try {
			
			if (stateCode == null || userId == null) {
				return new ModelAndView("redirect:login.htm");
			}
			List<Object[]> localBodyTypesRural = localGovtSetupService.getLocalBodyTypesRural();
			List<Object[]> preSelectedLocalBodyTypes = localGovtSetupService.getDefinedTiersSetup(stateCode);
			model.addAttribute("localBodyTypesRural", localBodyTypesRural);
			model.addAttribute("preSelectedLocalBodyTypes", preSelectedLocalBodyTypes);
			httpSession.setAttribute("preSelectedLBs", preSelectedLocalBodyTypes);
			return new ModelAndView("local_gov_setup_rural");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
	}
	
	/**
	 * Process <a>/localGovSetupRuralAdd</a> for building new Hierarchy  of local body tier setup
	 * for a specific state. 
	 * @param model
	 * @param httpSession
	 * @param request
	 * @param lbRuralChkedValues
	 * @return
	 */
	@RequestMapping(value = "/localGovSetupRuralAdd", method = RequestMethod.POST)
	public ModelAndView defineLocalGovSetupRural(Model model, 
												 HttpSession httpSession, 
												 HttpServletRequest request,
												 @RequestParam("lbRuralChkedValues") String lbRuralChkedValues) {
		try {
			return forwardRuralSetup(httpSession, model, lbRuralChkedValues, "add");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
	}
	
	/**
	 * Process <a>/localGovSetupRuralModify</a> for modifying existing tier setup Hierarchy 
	 * of local body tier setup within a specific state. 
	 * @param model
	 * @param httpSession
	 * @param request
	 * @param lbRuralChkedValues
	 * @return
	 */
	@RequestMapping(value = "/localGovSetupRuralModify", method = RequestMethod.POST)
	public ModelAndView modifyLocalGovSetupRural(Model model, 
												 HttpSession httpSession, 
												 HttpServletRequest request,
												 @RequestParam("lbRuralChkedValues") String lbRuralChkedValues) {
		try {
			return forwardRuralSetup(httpSession, model, lbRuralChkedValues, "modify");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
	}
	
	/**
	 * The {@code forwardRuralSetup} use to process the action 
	 * for rural setup addition/modification.
	 * @param httpSession
	 * @param model
	 * @param lbRuralChkedValues
	 * @param process
	 * @return ModelAndView
	 */
	private ModelAndView forwardRuralSetup(HttpSession httpSession, Model model, String lbRuralChkedValues, String process){
	
		if (stateCode == null) {
			return new ModelAndView("redirect:login.htm");
		}
		String url = "defined_local_gov_setup_rural";
		if(lbRuralChkedValues != null && !"".equals(lbRuralChkedValues)){
			Scanner scanner = new Scanner(lbRuralChkedValues);
			scanner.useDelimiter("@@");
			List<Object[]> definedLocalBodyTypesRural = new ArrayList<Object[]>();
			Map<String, String[]> definedSetup = new HashMap<String, String[]>();
			while(scanner.hasNext()){
				String 	 rowvalues 			= scanner.next();
				String[] columnvalues  		= rowvalues.split("\\|");
				definedLocalBodyTypesRural.add(columnvalues);
				definedSetup.put(columnvalues[0], columnvalues);
			}
			scanner.close();
			model.addAttribute("definedLocalBodyTypesRural", definedLocalBodyTypesRural);
			httpSession.setAttribute("definedSetup", definedSetup);
			if("modify".equalsIgnoreCase(process)){
				@SuppressWarnings("unchecked")
				List<Object[]> predefHierarchy = (List<Object[]>) httpSession.getAttribute("preSelectedLBs");
				Set<Integer> set = new TreeSet<Integer>();
				List<Integer> tiersetupcodes = new LinkedList<Integer>();
				for(Object[] obj : predefHierarchy){
					set.add((Integer)obj[5]);
					tiersetupcodes.add((Integer)obj[4]);
				}
				model.addAttribute("hierarchySet", set);
				model.addAttribute("predefHierarchy", predefHierarchy);
				url = "modify_local_gov_setup_rural";
			}
		}
		httpSession.setAttribute("process", process);
		return new ModelAndView(url);
	}
		
	/**
	 * The {@code localGovSetupRuralHierarchy} is post action to add / modify 
	 * tier setup hierarchy, process add records to database with latest defined 
	 * hierarchy.
	 * <code>performUpdationLocalBodies(initialLBCodes, newLBCodes, 'P');</code>
	 * is updating all existed local bodies's parent and put all new local
	 * bodies in disturb state.
	 * <code>addNewSetup(stateCode, userId)</code> : for new 
	 * local body tier setup.
	 * <code>invalidateExistingSetup(Integer.parseInt(setupCode), stateCode, userId)</code> : for
	 * replacing existing hierarchy with new one.
	 * @param model
	 * @param httpSession
	 * @param request
	 * @param lbHirarchyValues
	 * @param ruralHirarchyLevels
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/lgManageRuralSetup", method = RequestMethod.POST)
	public ModelAndView localGovSetupRuralHierarchy(Model model, 
									 		     	HttpSession httpSession, 
									 		     	HttpServletRequest request,
									 		     	@RequestParam("lbRuralHirarchyValues") String lbHirarchyValues,
									 		     	@RequestParam("ruralHirarchyLevels") String ruralHirarchyLevels) {
		ModelAndView mav = new ModelAndView();
		if (stateCode == null) {
			mav.setViewName("redirect:login.htm");
			return mav;
		}
		try {
			Set<String> set = new TreeSet<String>(Arrays.asList(ruralHirarchyLevels.split("\\,")));
			int setupEntry = set.size();
			Object sessionObj = httpSession.getAttribute("definedSetup");
			String process = (String) httpSession.getAttribute("process");
			List<Object[]> presel = null;
			boolean isModify = false;
			if("modify".equals(process)){
				isModify=true;
				presel = (List<Object[]>) httpSession.getAttribute("preSelectedLBs");
			}
			httpSession.removeAttribute("definedSetup");
			httpSession.removeAttribute("process");
			httpSession.removeAttribute("preSelectedLBs");
			String message;
			if(setupEntry > 0 && sessionObj != null){
				String newLBCodes = "";
				Map<String, String[]> definedSetup = (HashMap<String, String[]>) sessionObj;
				String[] hierarchyList = lbHirarchyValues.split("\\@@");
				MultiMap mp = new MultiValueMap();
				for(String hRows : hierarchyList){
					String[] p = hRows.split("\\|"); 
					mp.put(p[1], hRows);
					if(isModify){
						newLBCodes += p[0] + ",";
					}
				}
				List<String> keys = new LinkedList<String>(mp.keySet());
		        Collections.sort(keys);
				for(int i = 0; i < setupEntry; i++){
					String setupCode = set.iterator().next();
					LocalBodySetup setup = null;
					if("add".equals(process)){
						setup = localGovtSetupService.addNewSetup(stateCode, userId);
					}else if(isModify){
						setup = localGovtSetupService.invalidateExistingSetup(Integer.parseInt(setupCode), stateCode, userId);
					}
					if(setup != null){
						Integer localBodySetupCode = setup.getLocalBodySetupPK().getLocalBodySetupCode();
						Integer localBodySetupVersion = setup.getLocalBodySetupPK().getLocalBodySetupVersion();
						List<String> list = null;
				        for(String key : keys){
				        	list= (List<String>) mp.get(key);
				        	for(String values : list){
				        		String[] arrayValues = values.split("\\|");
				        		String lbType = arrayValues[0];
				        		String parentLB = arrayValues[1];
				        		String heirarchyLB = arrayValues[2];		
				        		if(setupCode.equals(heirarchyLB)){
				        			String[] setupvalues  = definedSetup.get(lbType);
				        			int tiersetCode = localBodySetupDAO.getMaxRecords("select COALESCE(max(tier_setup_code), 0) from local_body_tiers_setup");
				        			LocalBodyTiersSetup tiresetup = new LocalBodyTiersSetup();
				        			tiresetup.setTierSetupCode(tiersetCode);
				        			tiresetup.setLocalBodySetup(setup);
				        			tiresetup.setLocalbodytype(new LocalBodyType(Integer.parseInt(setupvalues[0])));
				        			tiresetup.setNomenclatureEnglish(setupvalues[1]);
				        			tiresetup.setNomenclatureLocal(setupvalues[2]);
				        			tiresetup.setIsactive(true);
				        			tiresetup.setLocalBodySetupCode(localBodySetupCode);
				        			tiresetup.setLocalBodySetupVersion(localBodySetupVersion);
				        			if(!"-1".equals(parentLB)){
				        				Integer parentCode = localGovtSetupService.getParentCodeTierSetup(Integer.parseInt(parentLB), 
				        																				  localBodySetupCode, 
				        																				  localBodySetupVersion);
				        				tiresetup.setParentTierSetupCode(parentCode);		
				        			}
				        			if(!localGovtSetupService.saveOrUpdateTierSetup(tiresetup)){
				        				message = "Exception occured while database transaction in local_body_tiers_setup.";
				        				mav.addObject("msgid",message);
				        				mav.setViewName("error");
				        				return mav;
				        			}
				        		}
				        	}
				        }
					}else{
						message = "Exception occured while database transaction in loca_body_setup.";
						mav.addObject("msgid",message);
        				mav.setViewName("error");
        				return mav;
					}
					set.remove(setupCode);
				}
				if(isModify){
					String initialLBCodes = "";
					for(Object[] o : presel){
						initialLBCodes += o[0].toString() + ",";
					}
					performUpdationLocalBodies(initialLBCodes, newLBCodes, 'P'); //P- Panchyat(Rural)
				}
				message = "Local Government Setup Hierarchy for Rural defined successfully.";
				mav.addObject("msgid",message);
				mav.setViewName("success");
				return mav;
			}
			message = "Either Hierarchy is already built or invalid defined Hierarchy.";
			mav.addObject("msgid",message);
			mav.setViewName("error");
			return mav;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
	}
}