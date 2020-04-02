package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.ConvertRLBtoTLB;
import in.nic.pes.lgd.bean.GetLandRegionWise;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.ParentWiseLocalBodies;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.forms.ConvertRLBtoTLBForm;
import in.nic.pes.lgd.forms.ConvertRLBtoULBForm;
import in.nic.pes.lgd.forms.ConvertTLBtoRLBForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.validator.ConvertLocalBodyValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class ConvertLocalBodyController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(ConvertLocalBodyController.class);

	@Autowired
	private LocalGovtBodyService localGovtBodyService;

	@Autowired
	private ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	private LocalGovtSetupService localGovtSetupService;

	@Autowired
	private GovernmentOrderService govtOrderService;

	@Autowired
	private ConvertLocalBodyValidator convertLocalBodyValidator;
	
	@Autowired
	private ConfigGovtOrderService configGovtOrderService;

	@Autowired
	private EmailService emailService;

	private List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
	private List<LocalbodyListbyState> panchayatList = new ArrayList<LocalbodyListbyState>();
	private Integer stateCode;
	
	private Integer districtCode;

	private Integer userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request,HttpSession session) {
		SimpleDateFormat dateFormat = null;
		try {
			setGlobalParams(session);
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "label.lgd", "userId", 1, e);
		}
	}

	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
	}
	

	/**
	 * Changed functionality according to SRS by Sushil on 12-03-2013
	 */
	@RequestMapping(value = "/convertRLBtoULB", method = RequestMethod.GET)
	public ModelAndView showRLBtoULBform(@ModelAttribute("convertRLBtoULB") ConvertRLBtoULBForm convertform, Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		char lbType = 'P';
		int operationCode = 19;
		char level = 0;
		String ddLevel = "";
		String dynaAttribute = null;
		boolean isSingleTier=false;
		try {
			
			if (stateCode != 0) {
				convertform.setOperationCode(operationCode);
				convertform.setLbType(lbType);
				Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, ' ');

				Character localbodytypecode = 'D';
				String lbtype = null;
				String govtOrderConfig = hMap.get("govtOrder");
				String message = hMap.get("message");
				if (govtOrderConfig == null && message != null) {
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
					return mav;
				} else {
					getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, lbType);
					if (getLocalGovtSetupList == null || getLocalGovtSetupList.isEmpty()) {
						mav = new ModelAndView("success");
						mav.addObject("msgid", "Please first Set Up Local Government for this State");
						return mav;
					} else {
						if (!getLocalGovtSetupList.isEmpty()) {
							for (GetLocalGovtSetup localGovtSetup : getLocalGovtSetupList) {
								if (localGovtSetup != null && localGovtSetup.getParentTierSetupCode() == 0) {
									level = localGovtSetup.getLevel();
								}
								if (localGovtSetup != null) {
									ddLevel += localGovtSetup.getLevel();
								}
							}
							if (level == 'D') {
								dynaAttribute = "districtPanchayatList";
							} else if (level == 'I') {
								dynaAttribute = "interPanchayatList";
							}else if (level == 'V') {
								dynaAttribute = "villageLBList";
								isSingleTier=Boolean.TRUE;
							}
							model.addAttribute("ddLevel", ddLevel);
						}
						model.addAttribute("isSingleTier", isSingleTier);
						model.addAttribute("urbanlbType", localGovtBodyService.getLocalBodyListStateWise('U', stateCode));
						if (districtCode > 0) {
							if (!getLocalGovtSetupList.isEmpty()) {
								List<GetLandRegionWise> panchayatList = localGovtBodyService.getLandRegionWise(localbodytypecode, districtCode, lbtype);
								mav = new ModelAndView("convertRLBtoULB");
								model.addAttribute(dynaAttribute, panchayatList);
								model.addAttribute("stateCode", stateCode);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								mav.addObject("convertRLBtoULB", convertform);
							}
						} else {
							if (!getLocalGovtSetupList.isEmpty()) {
								if (level != 0) {
									panchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, lbType, level);
									mav = new ModelAndView("convertRLBtoULB");
									model.addAttribute(dynaAttribute, panchayatList);
									model.addAttribute("stateCode", stateCode);
									mav.addObject("govtOrderConfig", govtOrderConfig);
									mav.addObject("convertRLBtoULB", convertform);
								} else {
									ModelAndView mv = new ModelAndView("configview");
									mv.addObject("msgid", "Invalid data set found");
									return mv;
								}
							}
							return mav;
						}
					}
				}
			} else {
				ModelAndView mv = new ModelAndView("configview");
				String message = "Please First Select State";
				mv.addObject("msgid", message);
				return mv;
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
	 * Added by Sushil on 25-03-2013
	 */
	@RequestMapping(value = "/convertTLBtoULB", method = RequestMethod.GET)
	public ModelAndView showTLBtoULBform(Model model, ConvertRLBtoULBForm convertform, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		char lbType = 'T';
		int operationCode = 28;
		char level = 0;
		String ddLevel = "";
		String dynaAttribute = null;
		boolean isSingleTier=false;
		try {
			
			if (stateCode != 0) {
				convertform.setOperationCode(operationCode);
				convertform.setLbType(lbType);
				Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, ' ');

				Character localbodytypecode = 'D';
				String lbtype = null;
				String govtOrderConfig = hMap.get("govtOrder");
				String message = hMap.get("message");
				if (govtOrderConfig == null && message != null) {
					mav = new ModelAndView("success");
					mav.addObject("msgid", message);
					return mav;
				} else {
					getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, lbType);
					if (getLocalGovtSetupList == null || getLocalGovtSetupList.isEmpty()) {
						mav = new ModelAndView("success");
						mav.addObject("msgid", "Please first Set Up Local Government for this State");
						return mav;
					} else {
						if (!getLocalGovtSetupList.isEmpty()) {
							for (GetLocalGovtSetup localGovtSetup : getLocalGovtSetupList) {
								if (localGovtSetup != null && localGovtSetup.getParentTierSetupCode() == 0) {
									level = localGovtSetup.getLevel();
								}
								if (localGovtSetup != null) {
									ddLevel += localGovtSetup.getLevel();
								}
							}
							if (level == 'D') {
								dynaAttribute = "districtPanchayatList";
							} else if (level == 'I') {
								dynaAttribute = "interPanchayatList";
							}else if (level == 'V') {
								dynaAttribute = "villageLBList";
								isSingleTier=Boolean.TRUE;
							}
							model.addAttribute("ddLevel", ddLevel);
						}
						model.addAttribute("isSingleTier", isSingleTier);
						if (districtCode != 0) {
							if (!getLocalGovtSetupList.isEmpty()) {
								List<GetLandRegionWise> panchayatList = localGovtBodyService.getLandRegionWise(localbodytypecode, districtCode, lbtype);
								mav = new ModelAndView("convertTLBtoULB");
								model.addAttribute(dynaAttribute, panchayatList);
								model.addAttribute("stateCode", stateCode);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								mav.addObject("convertTLBtoULB", convertform);
							}
						} else {
							if (!getLocalGovtSetupList.isEmpty()) {
								panchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, lbType, level);
								mav = new ModelAndView("convertTLBtoULB");
								model.addAttribute(dynaAttribute, panchayatList);
								model.addAttribute("stateCode", stateCode);
								mav.addObject("govtOrderConfig", govtOrderConfig);
								mav.addObject("convertTLBtoULB", convertform);
							}
							return mav;
						}
					}
				}
			} else {
				ModelAndView mv = new ModelAndView("configview");
				String message = "Please First Select State";
				mv.addObject("msgid", message);
				return mv;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	

	/** The district panchayat list. */
	List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();

	/** The district panchayat list for traditional. */
	List<LocalbodyListbyState> districtTraditionalList = new ArrayList<LocalbodyListbyState>();

	@RequestMapping(value = "/convertRLBtoTLB", method = RequestMethod.GET)
	public ModelAndView showRLBtoTLBform(@ModelAttribute("localGovtBodyForm") ConvertRLBtoTLBForm convertRLBtoTLBForm, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		
		try {

			char lbTypePri = 'P';
			int operationCodePri = 20;
			char lbTypeTrad = 'T';
			int operationCodeTrad = 27;

			char lbType = 'P';
			int operationCode = 29;
			String messageTrad = null;

			mav = new ModelAndView("convertRLBtoTLB");
			if (stateCode != 0) {
				model.addAttribute("stateCode", stateCode);

				convertRLBtoTLBForm.setOperationCode(operationCode);
				convertRLBtoTLBForm.setLbType(lbType);

				// List<LocalbodyforStateWise> localBodyTypelist =
				// localGovtBodyService.getLocalBodyListStateWise('P',
				// stateCode);
				List<LocalbodyforStateWise> localBodyTypelist = convertLocalbodyService.getLocalBodyListStateWise('P', stateCode);

				List<LocalbodyforStateWise> traditionalocalBodyTypelist = convertLocalbodyService.getLocalBodyListStateWise('T', stateCode);

				districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');

				districtTraditionalList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'T');

				Map<String, String> hMapPri = new HashMap<String, String>();

				// Please first check your operation code then set it in place
				// of 10
				hMapPri = govtOrderService.getGovtOrderConfigurationConvert(stateCode, operationCodePri, lbTypePri);

				String govtOrderConfigPri = hMapPri.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate

				Map<String, String> hMapTrad = new HashMap<String, String>();

				hMapTrad = govtOrderService.getGovtOrderConfigurationConvert(stateCode, operationCodeTrad, lbTypeTrad);

				String govtOrderConfigTrad = hMapTrad.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				messageTrad = hMapTrad.get("message");

				Map<String, String> hMap = govtOrderService.getGovtOrderConfigurationConvert(stateCode, operationCode, ' ');

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate

				if (govtOrderConfigPri != null && govtOrderConfigTrad != null && govtOrderConfig != null) {

					if (localBodyTypelist.size() > 0) {
						convertRLBtoTLBForm = setLBtype(localBodyTypelist, convertRLBtoTLBForm, request);
					}

					if (traditionalocalBodyTypelist.size() > 0) {
						convertRLBtoTLBForm = setLBtypeTrade(traditionalocalBodyTypelist, convertRLBtoTLBForm, request);
					}

					if (districtPanchayatList.size() > 0) {
						model.addAttribute("districtPanchayatList", districtPanchayatList);
					}

					if (districtTraditionalList.size() > 0) {
						model.addAttribute("districtTraditionalList", districtTraditionalList);
					}

					if (traditionalocalBodyTypelist.size() > 0) {
						model.addAttribute("traditionalocalBodyTypelist", traditionalocalBodyTypelist);
					}
					model.addAttribute("localbodyTypelist", localBodyTypelist);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("convertRLBtoTLBForm", convertRLBtoTLBForm);
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", messageTrad);
				}
			} else {
				ModelAndView mv = new ModelAndView("configview");
				String message = "Please First Select State";
				mv.addObject("msgid", message);
				return mv;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/convertTLBtoRLB", method = RequestMethod.GET)
	public ModelAndView showTLBtoRLBform(@ModelAttribute("localGovtBodyForm") ConvertTLBtoRLBForm convertTLBtoRLBForm, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		
		try {

			char lbTypePri = 'P';
			int operationCodePri = 20;
			char lbTypeTrad = 'T';
			int operationCodeTrad = 27;

			char lbType = 'T';
			int operationCode = 64;

			mav = new ModelAndView("convertTLBtoRLB");
			if (stateCode != 0) {
				model.addAttribute("stateCode", stateCode);

				convertTLBtoRLBForm.setOperationCode(operationCode);
				convertTLBtoRLBForm.setLbType(lbType);

				List<LocalbodyforStateWise> localBodyTypelist = convertLocalbodyService.getLocalBodyListStateWise('P', stateCode);

				List<LocalbodyforStateWise> traditionalocalBodyTypelist = convertLocalbodyService.getLocalBodyListStateWise('T', stateCode);

				districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');

				districtTraditionalList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'T');

				Map<String, String> hMapPri = new HashMap<String, String>();

				// Please first check your operation code then set it in place
				// of 10
				hMapPri = govtOrderService.getGovtOrderConfigurationConvert(stateCode, operationCodePri, lbTypePri);

				String govtOrderConfigPri = hMapPri.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
				String messagePri = hMapPri.get("message");

				Map<String, String> hMapTrad = new HashMap<String, String>();

				hMapTrad = govtOrderService.getGovtOrderConfigurationConvert(stateCode, operationCodeTrad, lbTypeTrad);

				String govtOrderConfigTrad = hMapTrad.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate

				Map<String, String> hMap = govtOrderService.getGovtOrderConfigurationConvert(stateCode, operationCode, ' ');

				String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate

				if (govtOrderConfigPri != null && govtOrderConfigTrad != null && govtOrderConfig != null) {
					if (localBodyTypelist.size() > 0) {
						convertTLBtoRLBForm = setLBtypeTradeforPRI(localBodyTypelist, convertTLBtoRLBForm, request);
					}

					if (traditionalocalBodyTypelist.size() > 0) {
						convertTLBtoRLBForm = setLBtypePRIforTrade(traditionalocalBodyTypelist, convertTLBtoRLBForm, request);
					}

					if (districtPanchayatList.size() > 0) {
						model.addAttribute("districtPanchayatList", districtPanchayatList);
					}

					if (districtTraditionalList.size() > 0) {
						model.addAttribute("districtTraditionalList", districtTraditionalList);
					}

					if (traditionalocalBodyTypelist.size() > 0) {
						model.addAttribute("traditionalocalBodyTypelist", traditionalocalBodyTypelist);
					}
					model.addAttribute("localbodyTypelist", localBodyTypelist);
					mav.addObject("govtOrderConfig", govtOrderConfig);
					mav.addObject("convertTLBtoRLBForm", convertTLBtoRLBForm);
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", messagePri);
				}
			} else {
				ModelAndView mv = new ModelAndView("configview");
				String message = "Please First Select State";
				mv.addObject("msgid", message);
				return mv;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	public ConvertRLBtoTLBForm setLBtype(List<LocalbodyforStateWise> localBodyTypelist, ConvertRLBtoTLBForm convertRLBtoTLBForm, HttpServletRequest request) {
		try {
			for (int j = 0; j < localBodyTypelist.size(); j++) {
				String govtBodyType = localBodyTypelist.get(j).getLevel();
				String category = localBodyTypelist.get(j).getCategory();

				if (govtBodyType != null) {
					if (govtBodyType.equalsIgnoreCase("D") && category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertRLBtoTLBForm.setLgd_LBNomenclatureDist(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("I") && category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertRLBtoTLBForm.setLgd_LBNomenclatureInter(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("V") && category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertRLBtoTLBForm.setLgd_LBNomenclatureVillage(localBodyTypelist.get(j).getNomenclatureEnglish());
							convertRLBtoTLBForm.setLgd_LBNomenclatureDist("District Panchayat");
							convertRLBtoTLBForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						}
					}
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
		return convertRLBtoTLBForm;
	}

	public ConvertTLBtoRLBForm setLBtypeTradeforPRI(List<LocalbodyforStateWise> localBodyTypelist, ConvertTLBtoRLBForm convertTLBtoRLBForm, HttpServletRequest request) {
		try {
			for (int j = 0; j < localBodyTypelist.size(); j++) {
				String govtBodyType = localBodyTypelist.get(j).getLevel();
				String category = localBodyTypelist.get(j).getCategory();

				if (govtBodyType != null) {
					if (govtBodyType.equalsIgnoreCase("D") && category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertTLBtoRLBForm.setLgd_LBNomenclatureDist(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("I") && category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertTLBtoRLBForm.setLgd_LBNomenclatureInter(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("V") && category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertTLBtoRLBForm.setLgd_LBNomenclatureVillage(localBodyTypelist.get(j).getNomenclatureEnglish());
							convertTLBtoRLBForm.setLgd_LBNomenclatureDist("District Panchayat");
							convertTLBtoRLBForm.setLgd_LBNomenclatureInter("Intermediate Panchayat");
						}
					}
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
		return convertTLBtoRLBForm;
	}

	public ConvertTLBtoRLBForm setLBtypePRIforTrade(List<LocalbodyforStateWise> localBodyTypelist, ConvertTLBtoRLBForm convertTLBtoRLBForm, HttpServletRequest request) {
		try {
			for (int j = 0; j < localBodyTypelist.size(); j++) {
				String govtBodyType = localBodyTypelist.get(j).getLevel();
				String category = localBodyTypelist.get(j).getCategory();

				if (govtBodyType != null) {
					if (govtBodyType.equalsIgnoreCase("D") && category.equalsIgnoreCase("T")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertTLBtoRLBForm.setLgd_LBNomenclatureDistTrade(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("I") && category.equalsIgnoreCase("T")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertTLBtoRLBForm.setLgd_LBNomenclatureInterTrade(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("V") && category.equalsIgnoreCase("T")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertTLBtoRLBForm.setLgd_LBNomenclatureVillageTrade(localBodyTypelist.get(j).getNomenclatureEnglish());
							convertTLBtoRLBForm.setLgd_LBNomenclatureDistTrade("Hill District Council");
							convertTLBtoRLBForm.setLgd_LBNomenclatureInterTrade("Block Advisory Council");
						}
					}
				}

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
		return convertTLBtoRLBForm;
	}

	public ConvertRLBtoTLBForm setLBtypeTrade(List<LocalbodyforStateWise> localBodyTypelist, ConvertRLBtoTLBForm convertRLBtoTLBForm, HttpServletRequest request) {
		try {
			for (int j = 0; j < localBodyTypelist.size(); j++) {
				String govtBodyType = localBodyTypelist.get(j).getLevel();
				String category = localBodyTypelist.get(j).getCategory();

				if (govtBodyType != null) {
					if (govtBodyType.equalsIgnoreCase("D") && category.equalsIgnoreCase("T")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertRLBtoTLBForm.setLgd_LBNomenclatureDistTrade(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("I") && category.equalsIgnoreCase("T")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
							convertRLBtoTLBForm.setLgd_LBNomenclatureInterTrade(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("V") && category.equalsIgnoreCase("T")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {
						convertRLBtoTLBForm.setLgd_LBNomenclatureVillageTrade(localBodyTypelist.get(j).getNomenclatureEnglish());
						convertRLBtoTLBForm.setLgd_LBNomenclatureDistTrade("Hill District Council");
						convertRLBtoTLBForm.setLgd_LBNomenclatureInterTrade("Block Advisory Council");
						}
					}
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
		return convertRLBtoTLBForm;
	}

	@ModelAttribute("urbanlocalbodyType")
	public List<LocalbodyforStateWise> getUrbanLocalBodyTypes(HttpSession session, HttpServletRequest request) {

		
		List<LocalbodyforStateWise> localBodyTypelist;
		try {
			setGlobalParams(session);
			localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('U', stateCode);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}

		return localBodyTypelist;
	}

	@RequestMapping(value = "/draftConversionRLBtoULB", method = RequestMethod.POST)
	public ModelAndView saveConversionRLBtoULB(@ModelAttribute("convertRLBtoULB") ConvertRLBtoULBForm convertform, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String govtSetUp = null;
		//char level = 0;
		String ddLevel = "";
		//String dynaAttribute = null;
		try {
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 19, 'U');

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			govtSetUp = getStateSetUp(getLocalGovtSetupList, request);

			convertLocalBodyValidator.validate(convertform, result, govtSetUp, stateCode);
			if (result.hasErrors()) {
				mav = new ModelAndView("convertRLBtoULB");
				/* added by sushil on 13-03-2013 */
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'P');
				if (!getLocalGovtSetupList.isEmpty()) {
					for (GetLocalGovtSetup localGovtSetup : getLocalGovtSetupList) {
						/*if (localGovtSetup != null && localGovtSetup.getParentTierSetupCode() == 0) {
							level = localGovtSetup.getLevel();
						}*/
						if (localGovtSetup != null) {
							ddLevel += localGovtSetup.getLevel();
						}
					}
					/*if (level == 'D') {
						dynaAttribute = "districtPanchayatList";
					} else if (level == 'I') {
						dynaAttribute = "interPanchayatList";
					}*/
					model.addAttribute("ddLevel", ddLevel);
				}
				if (govtOrderConfig != null && mapConfig != null) {
					setConvertRLBFields(convertform, model, govtSetUp);
					mav = new ModelAndView("convertRLBtoULB");
					model.addAttribute("PanchayatListforRural", panchayatList);
					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList", getLocalGovtSetupList);
				}
				if (result.hasFieldErrors()) {
					model.addAttribute("isError", true);
				}
				model.addAttribute("PanchayatListforRural", panchayatList);
				model.addAttribute("stateCode", stateCode);
				request.setAttribute("localSetUpList", getLocalGovtSetupList);
				mav.addObject("convertRLBtoULB", convertform);
				return mav;
			} else {
				session.setAttribute("formbean", convertform);
				mav = new ModelAndView("redirect:govtOrderCommon.htm");
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/draftConversionRLBtoTLB", method = RequestMethod.POST)
	public ModelAndView saveConversionRLBtoTLB(@ModelAttribute("convertRLBtoTLB") ConvertRLBtoTLBForm convertform, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String govtSetUp = null;
		String contLBDistrict = null;
		String contLBInter = null;
		String contLBVillage = null;
		String contVillList = null;
		String conLocalBTypeList = null;
		String contradlBTypeList = null;
		try {
			// ////////code for government order
			// checking///////////////////////////////////
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			
			// hMap =
			// configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode,
			// 29, 'V');// 10 is operation code for create new

			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 29, 'T');

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			convertform.setOperationCode(29);
			convertform.setGovtOrderConfig(govtOrderConfig);
			// ////////code for government order
			// checking///////////////////////////////////

			if (convertform.getRurallbTypeName() != null) {
				String lgTypeNameId = convertform.getRurallbTypeName();

				String[] lgTypeNameArr = lgTypeNameId.split(":");
				String localGovtType = lgTypeNameArr[0];
				List<LocalBodyType> localbType = localGovtBodyService.getLocalBodyTypeNamebyLBTID(localGovtType);
				if (localbType != null) {
					Iterator<LocalBodyType> localbodytypeItr = localbType.iterator();
					StringBuffer finalcontLocalbodytypeN = new StringBuffer();

					while (localbodytypeItr.hasNext()) {
						finalcontLocalbodytypeN.append(localbodytypeItr.next().getLocalBodyTypeName().trim() + ",");
					}
					conLocalBTypeList = finalcontLocalbodytypeN.toString();
					conLocalBTypeList = conLocalBTypeList.substring(0, finalcontLocalbodytypeN.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContPrilbtype(conLocalBTypeList);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getTraditionalLgTypeNameNew().equals("0")) {
				String tdlgTypeNameId = convertform.getTraditionalLgTypeNameNew();

				String[] tradlgTypeNameArr = tdlgTypeNameId.split(":");
				String tradlocalGovtType = tradlgTypeNameArr[0];
				List<LocalBodyType> tradlocalbType = localGovtBodyService.getLocalBodyTypeNamebyLBTID(tradlocalGovtType);
				if (tradlocalbType != null) {
					Iterator<LocalBodyType> localbodytypeItr = tradlocalbType.iterator();
					StringBuffer finalcontLocalbodytypeN = new StringBuffer();

					while (localbodytypeItr.hasNext()) {
						finalcontLocalbodytypeN.append(localbodytypeItr.next().getLocalBodyTypeName().trim() + ",");
					}
					contradlBTypeList = finalcontLocalbodytypeN.toString();
					contradlBTypeList = contradlBTypeList.substring(0, finalcontLocalbodytypeN.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContTradlbtypeNew(contradlBTypeList);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getTraditionalLbTypeName().equals("0")) {
				String tdlgTypeNameId = convertform.getTraditionalLbTypeName();

				String[] tradlgTypeNameArr = tdlgTypeNameId.split(":");
				String tradlocalGovtType = tradlgTypeNameArr[0];
				List<LocalBodyType> tradlocalbType = localGovtBodyService.getLocalBodyTypeNamebyLBTID(tradlocalGovtType);
				if (tradlocalbType != null) {
					Iterator<LocalBodyType> localbodytypeItr = tradlocalbType.iterator();
					StringBuffer finalcontLocalbodytypeN = new StringBuffer();

					while (localbodytypeItr.hasNext()) {
						finalcontLocalbodytypeN.append(localbodytypeItr.next().getLocalBodyTypeName().trim() + ",");
					}
					contradlBTypeList = finalcontLocalbodytypeN.toString();
					contradlBTypeList = contradlBTypeList.substring(0, finalcontLocalbodytypeN.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContTradlbtypeMerged(contradlBTypeList);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (convertform.getLgd_LBDistListAtVillageCA() != null) {
				List<Localbody> contDP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBDistListAtVillageCA());
				if (contDP != null) {
					Iterator<Localbody> localbodyItr = contDP.iterator();
					StringBuffer finalcontLocalbody = new StringBuffer();

					while (localbodyItr.hasNext()) {
						finalcontLocalbody.append(localbodyItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBDistrict = finalcontLocalbody.toString();
					contLBDistrict = contLBDistrict.substring(0, finalcontLocalbody.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContDPForPRI(contLBDistrict);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (convertform.getLgd_LBInterListAtVillageCA() != null) {
				List<Localbody> contIP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBInterListAtVillageCA());
				if (contIP != null) {
					Iterator<Localbody> localbodyInterItr = contIP.iterator();
					StringBuffer finalIntercontLocalbody = new StringBuffer();

					while (localbodyInterItr.hasNext()) {
						finalIntercontLocalbody.append(localbodyInterItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBInter = finalIntercontLocalbody.toString();
					contLBInter = contLBInter.substring(0, finalIntercontLocalbody.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContIPforPRI(contLBInter);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (convertform.getLgd_LBVillageDestAtVillageCA() != null) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBVillageDestAtVillageCA());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContVPforPRI(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBDistrictAtVillageforNew().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBDistrictAtVillageforNew());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContDPForTrad(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBIntermediateAtVillageforNew().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBIntermediateAtVillageforNew());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContIPforTrad(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBDistrictAtVillageforExist().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBDistrictAtVillageforExist());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContDPForTradMerged(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBIntermediateAtVillageforExist().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBIntermediateAtVillageforExist());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContIPforTradMerged(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBVillagePanchaytforExist().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBVillagePanchaytforExist());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContVPforTradMerged(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (convertform.getLgd_LBVillageCAreaDestL() != null) {
				List<Village> contVillage = localGovtBodyService.getVillageNamebyVillID(convertform.getLgd_LBVillageCAreaDestL());
				if (contVillage != null) {
					Iterator<Village> VillItr = contVillage.iterator();
					StringBuffer finalVillcontVillage = new StringBuffer();

					while (VillItr.hasNext()) {
						finalVillcontVillage.append(VillItr.next().getVillageNameEnglish().trim() + ",");
					}
					contVillList = finalVillcontVillage.toString();
					contVillList = contVillList.substring(0, finalVillcontVillage.length() - 1);
					convertform.setContVillageList(contVillList);
				}
			}

			govtSetUp = getStateSetUp(getLocalGovtSetupList, request);

			convertLocalBodyValidator.validateRLBtoTLB(convertform, result, govtSetUp, stateCode);
			if (result.hasErrors()) {
				if (govtOrderConfig != null && mapConfig != null) {
					// setConvertRLBFields(convertform,model,govtSetUp);
					mav = new ModelAndView("convertRLBtoTLB");
					model.addAttribute("PanchayatListforRural", panchayatList);
					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList", getLocalGovtSetupList);
					mav.addObject("convertRLBtoTLB", convertform);
				}
				return mav;
			} else {
				session.setAttribute("formbean", convertform);
				mav = new ModelAndView("redirect:govtOrderCommon.htm");
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/draftConversionTLBtoRLB", method = RequestMethod.POST)
	public ModelAndView saveConversionTLBtoRLB(@ModelAttribute("convertTLBtoRLB") ConvertTLBtoRLBForm convertform, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String govtSetUp = null;
		String contLBDistrict = null;
		String contLBInter = null;
		String contLBVillage = null;
		String contVillList = null;
		String conLocalBTypeList = null;
		String contradlBTypeList = null;
		try {
			// ////////code for government order
			// checking///////////////////////////////////
			Map<String, String> hMap = new HashMap<String, String>();
			// Please first check your operation code then set it in place of 10
			
			// hMap =
			// configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode,
			// 29, 'V');// 10 is operation code for create new

			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, 64, 'P');

			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			convertform.setOperationCode(64);
			convertform.setGovtOrderConfig(govtOrderConfig);
			// ////////code for government order
			// checking///////////////////////////////////

			if (convertform.getRurallbTypeName() != null) {
				String lgTypeNameId = convertform.getRurallbTypeName();

				String[] lgTypeNameArr = lgTypeNameId.split(":");
				String localGovtType = lgTypeNameArr[0];
				List<LocalBodyType> localbType = localGovtBodyService.getLocalBodyTypeNamebyLBTID(localGovtType);
				if (localbType != null) {
					Iterator<LocalBodyType> localbodytypeItr = localbType.iterator();
					StringBuffer finalcontLocalbodytypeN = new StringBuffer();

					while (localbodytypeItr.hasNext()) {
						finalcontLocalbodytypeN.append(localbodytypeItr.next().getLocalBodyTypeName().trim() + ",");
					}
					conLocalBTypeList = finalcontLocalbodytypeN.toString();
					conLocalBTypeList = conLocalBTypeList.substring(0, finalcontLocalbodytypeN.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContPrilbtype(conLocalBTypeList);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getTraditionalLgTypeNameNew().equals("0")) {
				String tdlgTypeNameId = convertform.getTraditionalLgTypeNameNew();

				String[] tradlgTypeNameArr = tdlgTypeNameId.split(":");
				String tradlocalGovtType = tradlgTypeNameArr[0];
				List<LocalBodyType> tradlocalbType = localGovtBodyService.getLocalBodyTypeNamebyLBTID(tradlocalGovtType);
				if (tradlocalbType != null) {
					Iterator<LocalBodyType> localbodytypeItr = tradlocalbType.iterator();
					StringBuffer finalcontLocalbodytypeN = new StringBuffer();

					while (localbodytypeItr.hasNext()) {
						finalcontLocalbodytypeN.append(localbodytypeItr.next().getLocalBodyTypeName().trim() + ",");
					}
					contradlBTypeList = finalcontLocalbodytypeN.toString();
					contradlBTypeList = contradlBTypeList.substring(0, finalcontLocalbodytypeN.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContTradlbtypeNew(contradlBTypeList);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getTraditionalLbTypeName().equals("0")) {
				String tdlgTypeNameId = convertform.getTraditionalLbTypeName();

				String[] tradlgTypeNameArr = tdlgTypeNameId.split(":");
				String tradlocalGovtType = tradlgTypeNameArr[0];
				List<LocalBodyType> tradlocalbType = localGovtBodyService.getLocalBodyTypeNamebyLBTID(tradlocalGovtType);
				if (tradlocalbType != null) {
					Iterator<LocalBodyType> localbodytypeItr = tradlocalbType.iterator();
					StringBuffer finalcontLocalbodytypeN = new StringBuffer();

					while (localbodytypeItr.hasNext()) {
						finalcontLocalbodytypeN.append(localbodytypeItr.next().getLocalBodyTypeName().trim() + ",");
					}
					contradlBTypeList = finalcontLocalbodytypeN.toString();
					contradlBTypeList = contradlBTypeList.substring(0, finalcontLocalbodytypeN.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContTradlbtypeMerged(contradlBTypeList);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (convertform.getLgd_LBDistListAtVillageCA() != null) {
				List<Localbody> contDP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBDistListAtVillageCA());
				if (contDP != null) {
					Iterator<Localbody> localbodyItr = contDP.iterator();
					StringBuffer finalcontLocalbody = new StringBuffer();

					while (localbodyItr.hasNext()) {
						finalcontLocalbody.append(localbodyItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBDistrict = finalcontLocalbody.toString();
					contLBDistrict = contLBDistrict.substring(0, finalcontLocalbody.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContDPForPRI(contLBDistrict);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (convertform.getLgd_LBInterListAtVillageCA() != null) {
				List<Localbody> contIP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBInterListAtVillageCA());
				if (contIP != null) {
					Iterator<Localbody> localbodyInterItr = contIP.iterator();
					StringBuffer finalIntercontLocalbody = new StringBuffer();

					while (localbodyInterItr.hasNext()) {
						finalIntercontLocalbody.append(localbodyInterItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBInter = finalIntercontLocalbody.toString();
					contLBInter = contLBInter.substring(0, finalIntercontLocalbody.length() - 1);
					// finalcontDist = finalcontDist.substring(0,
					// finalcontDist.length() - 1);
					convertform.setContIPforPRI(contLBInter);
					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (convertform.getLgd_LBVillageDestAtVillageCA() != null) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBVillageDestAtVillageCA());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContVPforPRI(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBDistrictAtVillageforNew().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBDistrictAtVillageforNew());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContDPForTrad(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBIntermediateAtVillageforNew().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBIntermediateAtVillageforNew());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContIPforTrad(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBDistrictAtVillageforExist().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBDistrictAtVillageforExist());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContDPForTradMerged(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBIntermediateAtVillageforExist().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBIntermediateAtVillageforExist());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContIPforTradMerged(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (!convertform.getLgd_LBVillagePanchaytforExist().equals("0")) {
				List<Localbody> contVP = localGovtBodyService.getLocalBodyNamebyLBID(convertform.getLgd_LBVillagePanchaytforExist());
				if (contVP != null) {
					Iterator<Localbody> localbodyVillageItr = contVP.iterator();
					StringBuffer finalVillagecontLocalbody = new StringBuffer();

					while (localbodyVillageItr.hasNext()) {
						finalVillagecontLocalbody.append(localbodyVillageItr.next().getLocalBodyNameEnglish().trim() + ",");
					}
					contLBVillage = finalVillagecontLocalbody.toString();
					contLBVillage = contLBVillage.substring(0, finalVillagecontLocalbody.length() - 1);
					// contLBVillage = contLBVillage.substring(0,
					// contLBVillage.length() - 1);
					convertform.setContVPforTradMerged(contLBVillage);

					// localGovtBodyForm.setContDistrict(contDist);
				}
			}

			if (convertform.getLgd_LBVillageCAreaDestL() != null) {
				List<Village> contVillage = localGovtBodyService.getVillageNamebyVillID(convertform.getLgd_LBVillageCAreaDestL());
				if (contVillage != null) {
					Iterator<Village> VillItr = contVillage.iterator();
					StringBuffer finalVillcontVillage = new StringBuffer();

					while (VillItr.hasNext()) {
						finalVillcontVillage.append(VillItr.next().getVillageNameEnglish().trim() + ",");
					}
					contVillList = finalVillcontVillage.toString();
					contVillList = contVillList.substring(0, finalVillcontVillage.length() - 1);
					convertform.setContVillageList(contVillList);
				}
			}

			govtSetUp = getStateSetUp(getLocalGovtSetupList, request);

			convertLocalBodyValidator.validateTLBtoRLB(convertform, result, govtSetUp, stateCode);
			if (result.hasErrors()) {
				if (govtOrderConfig != null && mapConfig != null) {
					// setConvertRLBFields(convertform,model,govtSetUp);
					mav = new ModelAndView("convertTLBtoRLB");
					model.addAttribute("PanchayatListforRural", panchayatList);
					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList", getLocalGovtSetupList);
					mav.addObject("convertTLBtoRLB", convertform);
				}
				return mav;
			} else {
				session.setAttribute("formbean", convertform);
				mav = new ModelAndView("redirect:govtOrderCommon.htm");
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	/**
	 * @Modified by Sushil
	 * @Date 03-05-2013
	 * @return mav
	 */
	@RequestMapping(value = "/draftConversionTLBtoULB", method = RequestMethod.POST)
	public ModelAndView saveConversionTLBtoULB(@ModelAttribute("convertTLBtoULB") ConvertRLBtoULBForm convertform, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String govtSetUp = null;
		//char level = 0;
		String ddLevel = "";
		//String dynaAttribute = null;
		try {
			// code for government order checking
			Map<String, String> hMap = new HashMap<String, String>();
			
			hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 28, 'U');
			String govtOrderConfig = hMap.get("govtOrder");// values==govtOrderUpload,govtOrderGenerate
			String mapConfig = hMap.get("mapUpload");// values==true,false
			// end code for government order checking

			govtSetUp = getStateSetUp(getLocalGovtSetupList, request);

			convertLocalBodyValidator.validate(convertform, result, govtSetUp, stateCode);
			if (result.hasErrors()) {
				mav = new ModelAndView("convertTLBtoULB");
				/* added by sushil on 13-03-2013 */
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'P');
				if (!getLocalGovtSetupList.isEmpty()) {
					for (GetLocalGovtSetup localGovtSetup : getLocalGovtSetupList) {
						/*if (localGovtSetup != null && localGovtSetup.getParentTierSetupCode() == 0) {
							level = localGovtSetup.getLevel();
						}*/
						if (localGovtSetup != null) {
							ddLevel += localGovtSetup.getLevel();
						}
					}
					/*if (level == 'D') {
						dynaAttribute = "districtPanchayatList";
					} else if (level == 'I') {
						dynaAttribute = "interPanchayatList";
					}*/
					model.addAttribute("ddLevel", ddLevel);
				}
				if (govtOrderConfig != null && mapConfig != null) {
					// setConvertRLBFields(convertform, model, govtSetUp);
					mav = new ModelAndView("convertTLBtoULB");
					model.addAttribute("PanchayatListforRural", panchayatList);
					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList", getLocalGovtSetupList);
				}
				if (result.hasFieldErrors()) {
					model.addAttribute("isError", true);
				}
				model.addAttribute("PanchayatListforRural", panchayatList);
				model.addAttribute("stateCode", stateCode);
				request.setAttribute("localSetUpList", getLocalGovtSetupList);
				mav.addObject("convertTLBtoULB", convertform);
				return mav;
			} else {
				session.setAttribute("formbean", convertform);
				mav = new ModelAndView("redirect:govtOrderCommon.htm");
				return mav;
			}
			/*
			 * if (result.hasErrors()) { if (govtOrderConfig != null &&
			 * mapConfig != null) { setConvertRLBFields(convertform, model,
			 * govtSetUp); mav = new ModelAndView("convertTLBtoULB");
			 * model.addAttribute("PanchayatListforRural", panchayatList);
			 * model.addAttribute("stateCode", stateCode);
			 * request.setAttribute("localSetUpList", getLocalGovtSetupList);
			 * mav.addObject("convertRLBtoULB", convertform); } return mav; }
			 * else { session.setAttribute("formbean", convertform); mav = new
			 * ModelAndView("redirect:govtOrderCommon.htm"); return mav; }
			 */
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	private String getStateSetUp(List<GetLocalGovtSetup> getLocalGovtSetupList, HttpServletRequest request) {
		String govtSetUp = null;
		try {
			if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {
				for (GetLocalGovtSetup loclGovtSetUp : getLocalGovtSetupList) {

					if (govtSetUp == null)
						govtSetUp = Character.toString(loclGovtSetUp.getLevel());
					else
						govtSetUp = govtSetUp + Character.toString(loclGovtSetUp.getLevel());
				}

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return redirectPath;
		}
		return govtSetUp;
	}

	private void setConvertRLBFields(ConvertRLBtoULBForm convertform, Model model, String govtSetUp) {
		List<ParentWiseLocalBodies> intermedaiteList = new ArrayList<ParentWiseLocalBodies>();
		List<ParentWiseLocalBodies> villageList = new ArrayList<ParentWiseLocalBodies>();

		try {
			if (convertform.getDistrictLocalBodies().contains(",")) {
				String[] districtP = convertform.getDistrictLocalBodies().split(",");
				if (govtSetUp.equalsIgnoreCase("DIV")) {
					convertform.setDistrictLocalBodyCode(districtP[0].toString());
					int districtP1 = Integer.parseInt(districtP[0].toString());
					if (districtP1 > 0) {
						intermedaiteList = localGovtBodyService.getchildlocalbodiesByParentcode(districtP1);
						model.addAttribute("intermedaiteList", intermedaiteList);
					}
				} else if (govtSetUp.equalsIgnoreCase("DV")) {
					convertform.setDistrictLocalBodyCode(districtP[1].toString());
					int districtP2 = Integer.parseInt(districtP[1].toString());
					if (districtP2 > 0) {
						intermedaiteList = localGovtBodyService.getchildlocalbodiesByParentcode(districtP2);
						model.addAttribute("intermedaiteList", intermedaiteList);
					}
				}
			}

			if (convertform.getIntermediateLocalBodies().contains(",")) {
				String[] interP = convertform.getIntermediateLocalBodies().split(",");
				if (govtSetUp.equalsIgnoreCase("DIV")) {
					convertform.setInterLocalBodyCode(interP[0].toString());
					int interP1 = Integer.parseInt(interP[0].toString());
					if (interP1 > 0) {
						villageList = localGovtBodyService.getchildlocalbodiesByParentcode(interP1);
						model.addAttribute("villageLBList", villageList);
					}
				} else if (govtSetUp.equalsIgnoreCase("IV")) {
					convertform.setInterLocalBodyCode(interP[1].toString());
					int districtP2 = Integer.parseInt(interP[1].toString());
					if (districtP2 > 0) {
						villageList = localGovtBodyService.getchildlocalbodiesByParentcode(districtP2);
						model.addAttribute("villageLBList", villageList);
					}
				}
			}
			if (convertform.getDeclarenewULB() != null) {
				String lbTypeName = convertform.getUrbanLgTypeNameNew();
				if (lbTypeName != null && !lbTypeName.isEmpty()) {
					convertform.setHdnUrbanLgTypeName(lbTypeName);
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
	}

	@RequestMapping(value = "/publishConversionRLBtoULB", method = RequestMethod.POST)
	public ModelAndView conversionRLBtoULB(HttpServletRequest request, HttpSession session) throws Exception {
		Integer saveSuccess = null;
		ModelAndView mv = null;
		try {
			
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			AddAttachmentBean addAttachmentBean = null;
			if (stateCode != 0) {

				ConvertRLBtoULBForm convertform = (ConvertRLBtoULBForm) session.getAttribute("formbean");
				GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

				if (session.getAttribute("addAttachmentBean") != null) {
					addAttachmentBean = (AddAttachmentBean) session.getAttribute("addAttachmentBean");
					@SuppressWarnings("unused")
					String validateAttachment = attachmentHandler.validateAttachment(addAttachmentBean);
					metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
				}

				if (metaInfoOfToBeAttachedFileList != null && metaInfoOfToBeAttachedFileList.size() != 0) {
					saveSuccess = convertLocalbodyService.saveConversionRLBtoULB(convertform, govtOrderForm, metaInfoOfToBeAttachedFileList, stateCode, session);
					@SuppressWarnings("unused")
					String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedFileList, addAttachmentBean);

				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					saveSuccess = convertLocalbodyService.saveConversionRLBtoULBforTemplate(convertform, govtOrderForm, stateCode, session);
				}

				session.removeAttribute("formbean");
				session.removeAttribute("govtOrderForm");
				session.removeAttribute("addAttachmentBean");

				mv = new ModelAndView("configview");
				if (saveSuccess != null) {
					// if (convertform.getLbType() == 'U') {
					int t_code = saveSuccess;
					EmailSmsThread est = new EmailSmsThread(t_code, convertform.getLbType(), emailService);
					est.start();
					// }
					String message = "Conversion has been done successfully";
					mv.addObject("msgid", message);
				} else {
					String message = "Conversion Failed !!";
					mv.addObject("msgid", message);
				}
				return mv;
			} else {
				mv = new ModelAndView("configview");
				String message = "Please First Select State";
				mv.addObject("msgid", message);
				return mv;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@RequestMapping(value = "/publishConversionRLBtoTLB", method = RequestMethod.POST)
	public ModelAndView conversionRLBtoTLB(HttpServletRequest request, HttpSession session) throws Exception {
		// boolean saveSuccess = false;
		List<ConvertRLBtoTLB> lbreturn = null;
		ModelAndView mv = null;
		String retValue = null;
		String[] val = null;

	

		try {
			
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			AddAttachmentBean addAttachmentBean = null;
			if (stateCode != 0) {
				ConvertRLBtoTLBForm convertform = (ConvertRLBtoTLBForm) session.getAttribute("formbean");
				GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

				if (session.getAttribute("addAttachmentBean") != null) {
					addAttachmentBean = (AddAttachmentBean) session.getAttribute("addAttachmentBean");
					@SuppressWarnings("unused")
					String validateAttachment = attachmentHandler.validateAttachment(addAttachmentBean);
					metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
				}

				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					lbreturn = convertLocalbodyService.saveConversionRLBtoTLB(convertform, govtOrderForm, metaInfoOfToBeAttachedFileList, stateCode, userId);

					Iterator<ConvertRLBtoTLB> localgovtbodyItr = lbreturn.iterator();
					ConvertRLBtoTLB localdata = (ConvertRLBtoTLB) localgovtbodyItr.next();
					retValue = localdata.getConvert_rlb_tlb_fn();

					val = retValue.split(",");


					if (lbreturn != null) {
						/*
						 * if (metaInfoOfToBeAttachedFileList != null &&
						 * metaInfoOfToBeAttachedFileList.size() != 0) { String
						 * saveAttachment =
						 * attachmentHandler.saveMetaDataINFileSystem
						 * (metaInfoOfToBeAttachedFileList,addAttachmentBean); }
						 */

						if (session.getAttribute("validGovermentUpload") != null) {
							@SuppressWarnings("unchecked")
							List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) session.getAttribute("validGovermentUpload");
							convertLocalbodyService.saveDataInAttachRLBtoTLB(govtOrderForm, convertform, validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
						}
					}
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					lbreturn = convertLocalbodyService.saveConversionRLBtoTLB(convertform, govtOrderForm, metaInfoOfToBeAttachedFileList, stateCode, userId);
					Iterator<ConvertRLBtoTLB> localgovtbodyItr = lbreturn.iterator();
					ConvertRLBtoTLB localdata = (ConvertRLBtoTLB) localgovtbodyItr.next();
					retValue = localdata.getConvert_rlb_tlb_fn();
					val = retValue.split(",");
					if (lbreturn != null) {
						if (session.getAttribute("validGovermentGenerateUpload") != null) {
							// boolean insertGovtTableFlag =
							// localGovtBodyService.saveConversionRLBtoTLBforTemplate(govtOrderForm,convertform,request.getSession());
							convertLocalbodyService.saveConversionRLBtoTLBforTemplate(govtOrderForm, convertform, request.getSession(), Integer.parseInt(val[1]));
						}
					}
				}

				session.removeAttribute("formbean");
				session.removeAttribute("govtOrderForm");
				session.removeAttribute("addAttachmentBean");

				mv = new ModelAndView("configview");
				if (lbreturn != null) {
					String message = "Conversion has been done Successfully";

					if (convertform.getLbType() == 'T') {
						int t_code = Integer.parseInt(val[1]);
						char t_type = 'T';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}

					mv.addObject("msgid", message);
				} else {
					String message = "Conversion has not been done Successfully";
					mv.addObject("msgid", message);
				}
				return mv;

			} else {
				mv = new ModelAndView("configview");
				String message = "Please First Select State";
				mv.addObject("msgid", message);
				return mv;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}

	@RequestMapping(value = "/publishConversionTLBtoRLB", method = RequestMethod.POST)
	public ModelAndView conversionTLBtoRLB(HttpServletRequest request, HttpSession session) throws Exception {
		List<ConvertRLBtoTLB> lbreturn = null;
		ModelAndView mv = null;
		String retValue = null;
		String[] val = null;

		

		try {
			
			List<AttachedFilesForm> metaInfoOfToBeAttachedFileList = null;
			AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
			AddAttachmentBean addAttachmentBean = null;
			if (stateCode != 0) {
				ConvertTLBtoRLBForm convertform = (ConvertTLBtoRLBForm) session.getAttribute("formbean");
				GovernmentOrderForm govtOrderForm = (GovernmentOrderForm) session.getAttribute("govtOrderForm");

				if (session.getAttribute("addAttachmentBean") != null) {
					addAttachmentBean = (AddAttachmentBean) session.getAttribute("addAttachmentBean");
					@SuppressWarnings("unused")
					String validateAttachment = attachmentHandler.validateAttachment(addAttachmentBean);
					metaInfoOfToBeAttachedFileList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean);
				}

				if (govtOrderForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					lbreturn = convertLocalbodyService.saveConversionTLBtoRLB(convertform, govtOrderForm, metaInfoOfToBeAttachedFileList, stateCode, userId);

					Iterator<ConvertRLBtoTLB> localgovtbodyItr = lbreturn.iterator();
					ConvertRLBtoTLB localdata = (ConvertRLBtoTLB) localgovtbodyItr.next();
					retValue = localdata.getConvert_rlb_tlb_fn();

					val = retValue.split(",");


					if (lbreturn != null) {
						/*
						 * if (metaInfoOfToBeAttachedFileList != null &&
						 * metaInfoOfToBeAttachedFileList.size() != 0) { String
						 * saveAttachment =
						 * attachmentHandler.saveMetaDataINFileSystem
						 * (metaInfoOfToBeAttachedFileList,addAttachmentBean); }
						 */

						if (session.getAttribute("validGovermentUpload") != null) {
							@SuppressWarnings("unchecked")
							List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) session.getAttribute("validGovermentUpload");
							convertLocalbodyService.saveDataInAttachTLBtoRLB(govtOrderForm, convertform, validGovermentUpload, request.getSession(), Integer.parseInt(val[1]));
						}
					}
				} else if (govtOrderForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					lbreturn = convertLocalbodyService.saveConversionTLBtoRLB(convertform, govtOrderForm, metaInfoOfToBeAttachedFileList, stateCode, userId);
					Iterator<ConvertRLBtoTLB> localgovtbodyItr = lbreturn.iterator();
					ConvertRLBtoTLB localdata = (ConvertRLBtoTLB) localgovtbodyItr.next();
					retValue = localdata.getConvert_rlb_tlb_fn();
					val = retValue.split(",");
					if (lbreturn != null) {
						if (session.getAttribute("validGovermentGenerateUpload") != null) {
							// boolean insertGovtTableFlag =
							// localGovtBodyService.saveConversionRLBtoTLBforTemplate(govtOrderForm,convertform,request.getSession());
							convertLocalbodyService.saveConversionTLBtoRLBforTemplate(govtOrderForm, convertform, request.getSession(), Integer.parseInt(val[1]));
						}
					}
				}

				session.removeAttribute("formbean");
				session.removeAttribute("govtOrderForm");
				session.removeAttribute("addAttachmentBean");

				mv = new ModelAndView("configview");
				if (lbreturn != null) {
					String message = "Conversion has been done Successfully";

					if (convertform.getLbType() == 'P') {
						int t_code = Integer.parseInt(val[1]);
						char t_type = 'T';
						EmailSmsThread est = new EmailSmsThread(t_code, t_type, emailService);
						est.start();
					}

					mv.addObject("msgid", message);
				} else {
					String message = "Conversion has not been done Successfully";
					mv.addObject("msgid", message);
				}
				return mv;

			} else {
				mv = new ModelAndView("configview");
				String message = "Please First Select State";
				mv.addObject("msgid", message);
				return mv;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
	}
	
	
	@RequestMapping(value = "/mergeULBs", method = RequestMethod.GET)
	public ModelAndView mergeOldUB(
			@ModelAttribute("localGovtFormData") LocalGovtBodyForm localGovtForm,
			HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		char lbType = 'U';
		int operationCode = 66;
		Map<String, String> hMap = new HashMap<String, String>();
		List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
		try {
			
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode,
					operationCode, lbType);
			String message = null;
			String govtOrderConfig = hMap.get("govtOrder");
			if (govtOrderConfig == null) {
				mav = new ModelAndView("success");
				message = "Please Configure Goverment Order";
				mav.addObject("msgid", message);
				mav.addObject("stateCode", stateCode);

			} else if (govtOrderConfig.equals("govtOrderGenerate")) {
				mav = new ModelAndView("success");
				mav.addObject("msgid",
						"Prsently Ulb Merge Service is available  for GovermentOrderUpload Only");
			} else {
				localBodyTypelist = localGovtBodyService
						.getLocalBodyListStateWise('U', stateCode);
				mav = new ModelAndView("mergeexistingulb");
				mav.addObject("localBodyType", localBodyTypelist);

			}

		} catch (Exception e) {

		}
		return mav;
	}

	@RequestMapping(value = "/mergeUrbanLb", method = RequestMethod.POST)
	public ModelAndView mergeUrbanLocalBody(
			@ModelAttribute("localGovtFormData") LocalGovtBodyForm localGovtForm,
			HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		char lbType = 'U';
		int operationCode = 66;
		Map<String, String> hMap = new HashMap<String, String>();
		session.removeAttribute("formbean");
		try {
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode,
					operationCode, lbType);
			String govtOrderConfig = hMap.get("govtOrder");
			if (govtOrderConfig.equals("govtOrderGenerate")) {
				mav = new ModelAndView("success");
				mav.addObject("msgid",
						"Presently Ulb Merge Service is available for govermentOrderUpload Only");
			} else {
				localGovtForm.setGovtOrderConfig(govtOrderConfig);
				localGovtForm.setOperation("MULB");
				session.setAttribute("formbean", localGovtForm);
				mav = new ModelAndView("redirect:govtOrderCommon.htm");
			}
		} catch (Exception e) {
			session.removeAttribute("formbean");
		}
		return mav;
	}

	@RequestMapping(value = "/mergeUrbanLbFinal", method = RequestMethod.POST)
	public ModelAndView mergeUrbanLocalBodyFinal(
			@ModelAttribute("localGovtFormData") LocalGovtBodyForm localGovtForm,
			BindingResult result, HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = null;
		char lbType = 'U';
		int operationCode = 66;
		@SuppressWarnings("unused")
		Map<String, String> hMap = new HashMap<String, String>();
		LocalGovtBodyForm lbForm = new LocalGovtBodyForm();
		String success = null;
		try {
			hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode,
					operationCode, lbType);
			lbForm = (LocalGovtBodyForm) session.getAttribute("formbean");
			lbForm.setOrderNo(localGovtForm.getOrderNo());
			lbForm.setOrderDate(localGovtForm.getOrderDate());
			lbForm.setEffectiveDate(localGovtForm.getEffectiveDate());
			lbForm.setGazPubDate(localGovtForm.getGazPubDate());
			success = localGovtBodyService.mergeULB(lbForm, session);
			mav = new ModelAndView("success");
			if (success != null)
				mav.addObject("msgid", "Ulb have been Merged Successfuly");
			else

				mav.addObject("msgid", " Ulb Merger Failded");

		} catch (Exception e) {

		} finally {
			session.removeAttribute("formbean");
		}
		return mav;
	}



}
