package in.nic.pes.lgd.controllers;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.rules.LocalBodyUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.common.beans.ConsolidatePanchaytReportVariable;
import in.nic.pes.common.beans.MyStackList;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.ConsolidateReportForRuralLB;
import in.nic.pes.lgd.bean.ConsolidateReportLB;
import in.nic.pes.lgd.bean.ConsolidateReportLBLandregion;
import in.nic.pes.lgd.bean.ConsolidateReportLBRPT;
import in.nic.pes.lgd.bean.ConsolidateReportLandregions;
import in.nic.pes.lgd.bean.ConsolidateReportLandregionsforDistrict;
import in.nic.pes.lgd.bean.ConsolidateReportLandregionsforSubDistrict;
import in.nic.pes.lgd.bean.ConsolidateReportLandregionsforVillage;
import in.nic.pes.lgd.bean.DPwardConstituencyWiseVP;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictWiseDetail;
import in.nic.pes.lgd.bean.DistrictWiseInvalidatedVillage;
import in.nic.pes.lgd.bean.DistrictWiseLBReportBean;
import in.nic.pes.lgd.bean.GetLocalBodyListbyLBtypebyState;
import in.nic.pes.lgd.bean.LGDUpdationEntity;
import in.nic.pes.lgd.bean.LandRegionDetail;
import in.nic.pes.lgd.bean.LocalBodyDetails;
import in.nic.pes.lgd.bean.LocalBodyWiseWards;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.MappedVillageByLBCodeBean;
import in.nic.pes.lgd.bean.PCACMappingforLBWard;
import in.nic.pes.lgd.bean.ReportDistwiseVillagandMappedGP;
import in.nic.pes.lgd.bean.ReportStatePanchayatPojo;
import in.nic.pes.lgd.bean.ReportingStatewiseGISMappedLBEntity;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateWiseDistrictVillagePanchaytMapping;
import in.nic.pes.lgd.bean.StatewiseEntitiesCount;
import in.nic.pes.lgd.bean.StatewiseGPtoVillageMapped;
import in.nic.pes.lgd.bean.StatewiseUnmappedVillages;
import in.nic.pes.lgd.bean.UnmappedLbPcAcWard;
import in.nic.pes.lgd.bean.ViewUnMappedLocalBodies;
import in.nic.pes.lgd.common.CaptchaValidator;
import in.nic.pes.lgd.common.CompareStatewiseEntitiesCount;
//import in.nic.pes.lgd.common.CompareStatewiseEntitiesCount;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.ExceptionalReportsForm;
import in.nic.pes.lgd.forms.HabitationForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.MappedLBWARDTOPCAC;
import in.nic.pes.lgd.forms.MinistryForm;
import in.nic.pes.lgd.forms.OrganizationUnitForm;
import in.nic.pes.lgd.forms.SearchForm;
import in.nic.pes.lgd.forms.StatePanchayatSetupReportForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.menu.LoginForm;
import in.nic.pes.lgd.restructure.reporting.entities.GenericReportingEntity;
import in.nic.pes.lgd.restructure.reporting.entities.LocalBodyMappedToDistrictsEntity;
import in.nic.pes.lgd.service.ComboFillingService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.InitialService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.MapService;
import in.nic.pes.lgd.service.ReportService;
import in.nic.pes.lgd.service.StatePanchayatReportService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.VillageReportService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.DataTablesParamUtility;
import in.nic.pes.lgd.utils.JQueryDataTableParamModel;

@Controller
@SessionAttributes("stack")  
public class ReportController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(ReportController.class);

	@Autowired
	private LocalBodyUtil localBodyUtil;

	@Autowired
	ReportService reportService;

	@Autowired
	StateDAO stateDao;

	@Autowired
	VillageReportService villageReportService;

	@Autowired
	ComboFillingService comboFillingService;

	@Autowired
	StatePanchayatReportService statePanchayatService;

	@Autowired
	StateService stateService;
	
	@Autowired
	DistrictService districtService;
	
	@Autowired
	private LocalGovtBodyService localGovtBodyService;
	
	@Autowired
	private InitialService initialService;
	
	
	@Autowired
	VillageService villageService;
	
	@Autowired
	@Qualifier("MapService")
	MapService mapService;
	
	private String serverLoc=null;
	
	private static Long DOWNLOAD_FILE_ID=3L;
	
	private static String CURRENTLY_ACTIVE_LOCALBODY="A";
	// MyStackList<ConsolidatePanchaytReportVariable> stack;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setAutoGrowCollectionLimit(80000);
	}

	@RequestMapping("/rptStatePanchayatSetup")
	public ModelAndView showStatePanchayatSetUp(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("statePanchaytSetUpRpt");
		try {
			mav.addObject("statePanchayatSetup",
					new StatePanchayatSetupReportForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping("/rptStateTraditionalSetup")
	public ModelAndView showStateTradionalSetUp(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("statePanchaytSetUpRpt");
		try {
			mav.addObject("statePanchayatSetup",
					new StatePanchayatSetupReportForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping("/rptStateUrbanSetup")
	public ModelAndView showStateUrbanSetUp(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("statePanchaytSetUpRpt");
		try {
			mav.addObject("statePanchayatSetup",
					new StatePanchayatSetupReportForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/* Report on District wise villages and Mapped Gram Panchayats */

	@RequestMapping(value = "/rptConsolidateVillageGramPanchayat", method = RequestMethod.GET)
	public ModelAndView showrptConsolidateVillageGramPanchayat(HttpSession httpSession,HttpServletRequest request,Model model,@ModelAttribute("reportDistwiseVillagandMappedGP") ReportDistwiseVillagandMappedGP reportDistwiseVillagandMappedGP) {
		ModelAndView mav = new ModelAndView("rptConsolidateVillageGramPanchayat");

		try {
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/rptConsolidateVillageGramPanchayat", method = RequestMethod.POST)
	public ModelAndView showrptConsolidateVillageGramPanchayatPOST(HttpSession httpSession,HttpServletRequest request,Model model,@ModelAttribute("reportDistwiseVillagandMappedGP") ReportDistwiseVillagandMappedGP reportDistwiseVillagandMappedGP) {
		ModelAndView mav = new ModelAndView("rptConsolidateVillageGramPanchayat");
		try {
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			String captchaAnswer = reportDistwiseVillagandMappedGP.getCaptchaAnswer();
			
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				reportDistwiseVillagandMappedGP.setCaptchaAnswer(null);
				return mav;
			} 

			model.addAttribute("reportList", reportService.getDistwiseVillagandMappedGP(reportDistwiseVillagandMappedGP.getParamDistrictCode()));
			
		} catch (Exception e) {
			log.error("view details controller: ", e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav.setViewName(redirectPath);
			return mav;
		}
		return mav;
	}

/*	@RequestMapping(value = "/rptConsolidateVillageGramPanchayat", method = RequestMethod.POST)
	public ModelAndView showrptConsolidateForVillageGramPanchayat(
			@ModelAttribute("consolidateReport") ConsolidateReportLBRPT consolidateReport,
			BindingResult result, Model model, HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("rptConsolidateVillageGramPanchayat");
			 New Captcha code 
			String captchaAnswer = consolidateReport.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session,
					captchaAnswer);
			if (messageFlag) {
				 End of New captcha code 

				comboFillingService.getComboValuesforApp('D', "S", Integer
						.parseInt(consolidateReport.getState_name_english()),
						Integer.parseInt(consolidateReport.getDistrictPName()));

				System.out
						.println("-------------->> In WebServicesController POST Method");

				Integer sCode = Integer.parseInt(request
						.getParameter("stateCode"));
				//System.out.println("Value of the hidden statecode" + sCode);

				Integer vCode = Integer.parseInt(request
						.getParameter("villageCode"));
				//System.out.println("Value of the hidden statecode" + vCode);

				
				 * Retreiving list of villages from the selected state and the
				 * district
				 
				List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();
				reportList = villageReportService.getConsolidatedReport(sCode,
						vCode);

				 Empty list check 
				if (reportList.isEmpty()) {
					String saveMessage = "No Village Record Found";
					model.addAttribute("saveMsg", saveMessage);
				}
				model.addAttribute("reportList", reportList);
				//System.out.println("----->>>>>>>>" + reportList);

			} else {
				Integer sCode = Integer.parseInt(request
						.getParameter("stateCode"));
				result.rejectValue("captchaAnswer", "captcha.errorMessage");
				model.addAttribute("statelist",
						villageReportService.getConsolidatedRptForVillage());
				model.addAttribute("districtList", villageReportService
						.getDistrictListbyStateCodeGlobal(sCode));
				mav.addObject("consolidateReport", consolidateReport);
				consolidateReport.setState_name_english(null);

				return mav;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		try {
			model.addAttribute("statelist",
					villageReportService.getConsolidatedRptForVillage());
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		Integer dlc = null;
		if (consolidateReport.getDistrictPName() != null) {
			dlc = Integer.parseInt(consolidateReport.getDistrictPName().trim());
		}
		try {
		if (dlc != null) {
			List<LandRegionDetail> landregionDetail = null;
			landregionDetail = new ArrayList<LandRegionDetail>();
			landregionDetail = reportService.landRegionDetail('D',
					dlc);
			if (landregionDetail.size() > 0){
				model.addAttribute("message", " of "
						+ landregionDetail.get(0).getDistrictNameEnglish()
						+ " District("
						+ landregionDetail.get(0).getStateNameEnglish() + ")");
			}	
		}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		model.addAttribute("consolidateReport", new ConsolidateReportLBRPT());
		return mav;

	}*/

	/* End of Report on District wise villages and Mapped Gram Panchayats */
	/* Report on State Panchayat Setup */

	@RequestMapping(value = "/rptStatePanchayats.do", method = RequestMethod.GET)
	public ModelAndView showrptStatePanchayat(HttpServletRequest request,
			HttpSession session, Model model) {
		ModelAndView mav = null;

		try {

			mav = new ModelAndView("rptStatePanchayat");

			/*System.out
					.println("-------------->> In WebServicesController GET Method");*/
			model.addAttribute("reportStatePanchayat",
					new ConsolidateReportLBRPT());
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/rptStatePanchayats", method = RequestMethod.POST)
	public ModelAndView showrptForStatePanchayat(
			@ModelAttribute("reportStatePanchayat") ConsolidateReportLBRPT reportStatePanchayat,
			BindingResult result, Model model, HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("rptStatePanchayat");
			/* New Captcha code */
			String captchaAnswer = reportStatePanchayat.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session,
					captchaAnswer);
			if (messageFlag) {
				/* End of New captcha code */
				/*System.out
						.println("-------------->> In WebServicesController POST Method");*/

				// Retreiving list of villages from the selected state and the
				// district
				List<ReportStatePanchayatPojo> reportList = new ArrayList<ReportStatePanchayatPojo>();
				char panchayat = 'P';
				reportList = statePanchayatService
						.getStatePanchayatReport(panchayat);
				//System.out.println("ReportList generated");
				// Empty list check
				if (reportList.isEmpty()) {
					String saveMessage = "No State Record Found";
					model.addAttribute("saveMsg", saveMessage);
				}
				model.addAttribute("reportList", reportList);
				/*System.out.println("----->>>>>>>>" + reportList);
				System.out.println("---->>>"
						+ reportList.get(0).getStateNameEnglish());
				System.out.println("---->>>"
						+ reportList.get(1).getStateNameEnglish());
				System.out.println("---->>>"
						+ reportList.get(2).getStateNameEnglish());
				System.out.println("---->>>"
						+ reportList.get(3).getStateNameEnglish());*/

			} else {
				result.rejectValue("captchaAnswer", "captcha.errorMessage");
				mav.addObject("reportStatePanchayat", reportStatePanchayat);
				return mav;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			return mav;
		}
		model.addAttribute("reportStatePanchayat", new ConsolidateReportLBRPT());
		return mav;
	}

	/* Report on Local body Land Regions */
	@RequestMapping(value = "/rptConsolidateforLandregions", method = RequestMethod.GET)
	public ModelAndView showConsolidateReportforLandregions(
			HttpServletRequest request) {
		ModelAndView mav = null;

		try {
			mav = new ModelAndView("rptConsolidateforLandregions");
			mav.addObject("consolidateReportlandregion",
					new ConsolidateReportLBRPT());
			List<ConsolidateReportLBRPT> consolidateLBList = new ArrayList<ConsolidateReportLBRPT>();
			mav.addObject("consolidateLBList", consolidateLBList);
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/rptConsolidateforLandregions", method = RequestMethod.POST)
	public ModelAndView showConsolidateforLocalBodyLandregions(
			@ModelAttribute("consolidateReportlandregion") ConsolidateReportLBLandregion consolidateReportlb,
			BindingResult result, Model model, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = null;
		//int sdCountP = 0;
		//int vCountP = 0;
		//int dbCountT = 0;
		//int ipCountT = 0;
		//int vpCountT = 0;
		int sd_count = 0;
		int v_count = 0;
		int d_count = 0;

		try {
			mav = new ModelAndView("rptConsolidateforLandregions");
			/* New Captcha code */
			String captchaAnswer = consolidateReportlb.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session,
					captchaAnswer);
			if (messageFlag) {
				/* End of New captcha code */
				List<ConsolidateReportLandregions> consolidateLBList = reportService
						.getConsolidatedRptForLandRegions();

				List<ConsolidateReportLBLandregion> reportList = new ArrayList<ConsolidateReportLBLandregion>();

				int state_code = -1;
				ConsolidateReportLBLandregion consolidateReportLBRPT = null;
				for (ConsolidateReportLandregions consReporLBObj : consolidateLBList)

				{

					if (state_code != consReporLBObj.getStateCode()) {

						if (consolidateReportLBRPT != null){
							reportList.add(consolidateReportLBRPT);
						}	
						if (consReporLBObj.getNoofdistricts() != null){
							d_count = consReporLBObj.getNoofdistricts()
									+ d_count;
						}	
						if (consReporLBObj.getNoofsubdistricts() != null){
							sd_count = consReporLBObj.getNoofsubdistricts()
									+ sd_count;
						}	
						if (consReporLBObj.getNoofvillages() != null){
							v_count = consReporLBObj.getNoofvillages()
									+ v_count;
						}	

						state_code = consReporLBObj.getStateCode();
						consolidateReportLBRPT = new ConsolidateReportLBLandregion();
						consolidateReportLBRPT.setState_code(consReporLBObj
								.getStateCode());
						consolidateReportLBRPT
								.setState_name_english(consReporLBObj
										.getStateNameEnglish());
						consolidateReportLBRPT.setd_count(consReporLBObj
								.getNoofdistricts());
						consolidateReportLBRPT.setsd_count(consReporLBObj
								.getNoofsubdistricts());
						consolidateReportLBRPT.setV_count(consReporLBObj
								.getNoofvillages());
						consolidateReportLBRPT
								.setCensus_2001_code(consReporLBObj
										.getCensus_2001_code());
						consolidateReportLBRPT
								.setCensus_2011_code(consReporLBObj
										.getCensus_2011_code());
						consolidateReportLBRPT.setTotalDCount(d_count);
						consolidateReportLBRPT.setTotalsdCount(sd_count);
						consolidateReportLBRPT.setTotalVCount(v_count);

					}

				}

				if (consolidateReportLBRPT != null){
					reportList.add(consolidateReportLBRPT);
				}	
				consolidateReportlb.setTotalDCount(d_count);
				consolidateReportlb.setTotalsdCount(sd_count);
				consolidateReportlb.setTotalVCount(v_count);

				model.addAttribute("consolidateLBList", reportList);
				mav.addObject("consolidateReportlandregion",
						consolidateReportlb);
			} else {

				result.rejectValue("captchaAnswer", "captcha.errorMessage");
				mav.addObject("consolidateReportlandregion",
						consolidateReportlb);
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/* End Report on Local body Land Regions */
	@RequestMapping(value = "/rptConsolidateforPanchayat")
	public ModelAndView showConsolidateReport(HttpServletRequest request) {
		ModelAndView mav = null;
		ConsolidateReportLBRPT consolidateReportLBRPT = new ConsolidateReportLBRPT();
		try {
			mav = new ModelAndView("rptConsolidateforPanchayat");
			mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
			List<ConsolidateReportLBRPT> consolidateLBList = new ArrayList<ConsolidateReportLBRPT>();
			mav.addObject("consolidateLBList", consolidateLBList);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	

	@RequestMapping(value = "/rptConsolidateforPanchayat", method = RequestMethod.POST)
	public ModelAndView showConsolidateforPanchayat(@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBRPT consolidateReportlb, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		List<ConsolidateReportLB> consolidateLBList = new ArrayList<ConsolidateReportLB>();
		int dbCountP = 0;
		int ipCountP = 0;
		int vpCountP = 0;
		int dbCountT = 0;
		int ipCountT = 0;
		int vpCountT = 0;
		int countUrban = 0;
		boolean messageFlag = false;

		try {
			mav = new ModelAndView("rptConsolidateforPanchayat");
			/* New Captcha code */
			String captchaAnswer = consolidateReportlb.getCaptchaAnswer();
			String captchaAnswers = consolidateReportlb.getCaptchaAnswers();
			if (captchaAnswer.equals("0") && captchaAnswers.equals("0")) {
				messageFlag = true;
			} else {
				CaptchaValidator captchaValidator = new CaptchaValidator();
				messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			}
			if (messageFlag) {
				/* End of New captcha code */
				consolidateLBList = reportService.getConsolidatedRptForLBsCombined();

				List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();

				int state_code = -1;
				ConsolidateReportLBRPT consolidateReportLBRPT = null;
				int dp = 0, ip = 0, vp = 0, dt = 0, it = 0, vt = 0, u = 0;
				for (ConsolidateReportLB consReporLBObj : consolidateLBList)

				{

					if (state_code != consReporLBObj.getStateCode()) {

						if (consolidateReportLBRPT != null) {
							reportList.add(consolidateReportLBRPT);
						}
						dp = 0;
						ip = 0;
						vp = 0;
						dt = 0;
						it = 0;
						vt = 0;
						u = 0;
						state_code = consReporLBObj.getStateCode();
						consolidateReportLBRPT = new ConsolidateReportLBRPT();
						consolidateReportLBRPT.setState_code(consReporLBObj.getStateCode());
						consolidateReportLBRPT.setState_name_english(consReporLBObj.getStateNameEnglish());

						if (consReporLBObj.getCategory() == 'R' && consReporLBObj.getLevel() == 'D') {
							dp = dp + consReporLBObj.getCount();
							dbCountP = consReporLBObj.getCount() + dbCountP;
						} else if (consReporLBObj.getCategory() == 'R' && consReporLBObj.getLevel() == 'I') {
							ip = ip + consReporLBObj.getCount();
							ipCountP = consReporLBObj.getCount() + ipCountP;
						} else if (consReporLBObj.getCategory() == 'R' && consReporLBObj.getLevel() == 'V') {
							vp = vp + consReporLBObj.getCount();
							vpCountP = consReporLBObj.getCount() + vpCountP;
						} else if (consReporLBObj.getCategory() == 'U') {
							u = u + consReporLBObj.getCount();
							countUrban = consReporLBObj.getCount() + countUrban;
						}

					} else {

						if (consReporLBObj.getCategory() == 'R' && consReporLBObj.getLevel() == 'D') {
							dp = dp + consReporLBObj.getCount();
							dbCountP = consReporLBObj.getCount() + dbCountP;
						} else if (consReporLBObj.getCategory() == 'R' && consReporLBObj.getLevel() == 'I') {
							ip = ip + consReporLBObj.getCount();

							ipCountP = consReporLBObj.getCount() + ipCountP;
						} else if (consReporLBObj.getCategory() == 'R' && consReporLBObj.getLevel() == 'V') {

							vp = vp + consReporLBObj.getCount();
							vpCountP = consReporLBObj.getCount() + vpCountP;
						}  else if (consReporLBObj.getCategory() == 'U') {
							u = u + consReporLBObj.getCount();
							countUrban = consReporLBObj.getCount() + countUrban;
						}
					}
					consolidateReportLBRPT.setDp_count(dp);
					consolidateReportLBRPT.setIp_count(ip);
					consolidateReportLBRPT.setVp_count(vp);
					consolidateReportLBRPT.setTrad_dp_count(dt);
					consolidateReportLBRPT.setTrad_ip_count(it);
					consolidateReportLBRPT.setTrad_vp_count(vt);
					consolidateReportLBRPT.setUrban_count(u);

				}

				if (consolidateReportLBRPT != null) {
					reportList.add(consolidateReportLBRPT);
				}
				consolidateReportlb.setTotalDPCount(dbCountP);
				consolidateReportlb.setTotalIPCount(ipCountP);
				consolidateReportlb.setTotalVPCount(vpCountP);
				consolidateReportlb.setTotalTradDPCount(dbCountT);
				consolidateReportlb.setTotalTradIPCount(ipCountT);
				consolidateReportlb.setTotalTradVPCount(vpCountT);
				consolidateReportlb.setTotalUrbanCount(countUrban);

				model.addAttribute("consolidateLBList", reportList);
				mav.addObject("consolidateReportLBRPT", consolidateReportlb);
			} else {

				result.rejectValue("captchaAnswer", "captcha.errorMessage");
				mav.addObject("consolidateReportLBRPT", consolidateReportlb);
				return mav;
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	// ////////////////by siva //////////////////////
	@RequestMapping("/rptConsolidateforDistrictLevel")
	public ModelAndView showConsolidateforLandregionDistrict(
			@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBLandregion consolidateReportLBRPT,
			Model model, HttpServletRequest request) {
		ModelAndView mav = null;

		int stateCode = consolidateReportLBRPT.getStateId();
		// char type=consolidateReportLBRPT.getStatetype();
		// char level=consolidateReportLBRPT.getStatelevel();

		int sd_count = 0;
		int v_count = 0;
		List<ConsolidateReportLandregionsforDistrict> getList = new ArrayList<ConsolidateReportLandregionsforDistrict>();
		try {

			mav = new ModelAndView("rptConsolidateforDistrict");

			getList = reportService.getLocalBodyLandRegionbyState(stateCode);

			for (ConsolidateReportLandregionsforDistrict list : getList) {

				if (list.getNoofsubdistricts() != null){
					sd_count = list.getNoofsubdistricts() + sd_count;
				}	
				if (list.getNoofvillages() != null){
					v_count = list.getNoofvillages() + v_count;
				consolidateReportLBRPT.setCensus_2001_code(list
						.getCensus_2001_code());
				consolidateReportLBRPT.setCensus_2011_code(list
						.getCensus_2011_code());
				}
			}

			String stateName = stateDao
					.getStateNameEnglishbyStateCode(stateCode);
			consolidateReportLBRPT.setState_name_english(stateName);
			consolidateReportLBRPT.setState_code(stateCode);

			consolidateReportLBRPT.setsd_count(sd_count);
			consolidateReportLBRPT.setV_count(v_count);

			mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
			model.addAttribute("consolidateLBList", getList);

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// //////////end by siva////////////////////////
	// ////////////////by siva //////////////////////
	@RequestMapping("/rptConsolidateforSubDistrictLevel")
	public ModelAndView showConsolidateforLandregionSubDistrict(
			@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBLandregion consolidateReportlb,
			Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;

		int stateCode = consolidateReportlb.getStateId();
		int district_code = consolidateReportlb.getDistrict_code();
		session.setAttribute("DIST_CODE", district_code);

		int v_count = 0;
		//List<ConsolidateReportLandregionsforDistrict> getList1 = new ArrayList<ConsolidateReportLandregionsforDistrict>();
		List<ConsolidateReportLandregionsforSubDistrict> getList = new ArrayList<ConsolidateReportLandregionsforSubDistrict>();
		try {
			mav = new ModelAndView("rptConsolidateforSubDistrict");

			getList = reportService.getLocalBodyLandRegionbySubDistic(
					stateCode, district_code);
			for (ConsolidateReportLandregionsforSubDistrict list : getList) {
				if (list.getNoofvillages() != null){
					v_count = list.getNoofvillages() + v_count;
				consolidateReportlb.setCensus_2001_code(list
						.getCensus_2001_code());
				consolidateReportlb.setCensus_2011_code(list
						.getCensus_2011_code());
				}
			}

			String stateName = stateDao
					.getStateNameEnglishbyStateCode(stateCode);
			String district_name_english = stateDao
					.getDistrictNameEnglishbyStateCode(district_code);
			consolidateReportlb.setState_name_english(stateName);
			consolidateReportlb.setState_code(stateCode);
			consolidateReportlb.setdistrict_name_english(district_name_english);
			consolidateReportlb.setDistrict_code(district_code);
			consolidateReportlb.setEntityNames(stateName.trim()+","+district_name_english.trim());

			consolidateReportlb.setV_count(v_count);

			mav.addObject("consolidateReportLBRPT", consolidateReportlb);
			model.addAttribute("consolidateLBList", getList);

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// //////////end by siva////////////////////////
	// ////////////////by siva //////////////////////
	@RequestMapping("/rptConsolidateforvillageLevel")
	public ModelAndView showConsolidateforLandregionVillage(
			@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBLandregion consolidateReportLBRPT,
			Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;

		int stateCode = consolidateReportLBRPT.getStateId();
		int district_code = (Integer) session.getAttribute("DIST_CODE");
		// int district_code=consolidateReportLBRPT.getDistrict_code();
		int subdistrict_code = consolidateReportLBRPT.getSubdistrict_code();

		int v_count = 0;
		List<ConsolidateReportLandregionsforVillage> getList = new ArrayList<ConsolidateReportLandregionsforVillage>();
		try {

			mav = new ModelAndView("rptConsolidateforVillage");

			getList = reportService
					.getLocalBodyLandRegionbyVillage(subdistrict_code);

			String stateName = stateDao
					.getStateNameEnglishbyStateCode(stateCode);
			String district_name_english = stateDao
					.getDistrictNameEnglishbyStateCode(district_code);
			String subdistrict_name_english = stateDao
					.getSubDistricNameEnglishbySubdistriccode(subdistrict_code);
			consolidateReportLBRPT.setState_name_english(stateName);
			consolidateReportLBRPT.setState_code(stateCode);
			consolidateReportLBRPT
					.setdistrict_name_english(district_name_english);
			consolidateReportLBRPT.setCensus_2001_code(getList.get(0)
					.getCensus_2001_code());
			consolidateReportLBRPT.setCensus_2011_code(getList.get(0)
					.getCensus_2011_code());
			consolidateReportLBRPT.setSubdistrict_code(subdistrict_code);

			consolidateReportLBRPT.setV_count(v_count);
			consolidateReportLBRPT
					.setSubdistrict_name_english(subdistrict_name_english);
			consolidateReportLBRPT.setEntityNames(stateName.trim()+","+district_name_english.trim()+","+subdistrict_name_english.trim());
			mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
			model.addAttribute("consolidateLBList1", getList);

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	// //////////end by siva////////////////////////

	/*
	 * @RequestMapping("/rptConsolidateforPanbyLevel") public ModelAndView
	 * showConsolidateforPanbyLevel(
	 * 
	 * @ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBRPT
	 * consolidateReportLBRPT, Model model, HttpServletRequest request,
	 * 
	 * @RequestParam(value = "selstate") int stateCode,
	 * 
	 * @RequestParam(value = "type") char type,
	 * 
	 * @RequestParam(value = "level") char level)
	 */
// start add method for back functionality  (by Sangita)
	
	@RequestMapping(value = "/rptBacktoParentConsolidateLandRegion", method = RequestMethod.POST)
	public ModelAndView rptBacktoParentConsolidateLandRegion(
			@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBLandregion consolidateReportLBRPT,			
			BindingResult result, Model model, HttpSession httpSession,
			HttpServletRequest request
			) {

		ModelAndView mav = null;
		//int ipCount = 0;
		//int vpCount = 0;
		try {
			char parentlevel=consolidateReportLBRPT.getParentlevel();
			//int sdCountP = 0;
			//int vCountP = 0;
			//int dbCountT = 0;
			//int ipCountT = 0;
			//int vpCountT = 0;
			int sd_count = 0;
			int v_count = 0;
			int d_count = 0;
			switch(parentlevel)
			{
			case 'S':	
				
				List<ConsolidateReportLandregions> consolidateLBList = reportService.getConsolidatedRptForLandRegions();				
				List<ConsolidateReportLBLandregion> reportList = new ArrayList<ConsolidateReportLBLandregion>();
				ConsolidateReportLBLandregion consolidateReportlb=new ConsolidateReportLBLandregion();
				int state_code = -1;			
				for (ConsolidateReportLandregions consReporLBObj : consolidateLBList)
					{
						if (state_code != consReporLBObj.getStateCode()) {
							if (consolidateReportLBRPT != null ){
								if(consolidateReportLBRPT.getState_code()!=0)
									reportList.add(consolidateReportLBRPT);
							}	
							if (consReporLBObj.getNoofdistricts() != null){
							d_count = consReporLBObj.getNoofdistricts()
								+ d_count;
							}	
							if (consReporLBObj.getNoofsubdistricts() != null){
								sd_count = consReporLBObj.getNoofsubdistricts()
								+ sd_count;
							}	
							if (consReporLBObj.getNoofvillages() != null){
								v_count = consReporLBObj.getNoofvillages()
								+ v_count;
							}	

							state_code = consReporLBObj.getStateCode();
							consolidateReportLBRPT = new ConsolidateReportLBLandregion();
							consolidateReportLBRPT.setState_code(consReporLBObj
									.getStateCode());
							consolidateReportLBRPT
									.setState_name_english(consReporLBObj
											.getStateNameEnglish());
							consolidateReportLBRPT.setd_count(consReporLBObj
									.getNoofdistricts());
							consolidateReportLBRPT.setsd_count(consReporLBObj
									.getNoofsubdistricts());
							consolidateReportLBRPT.setV_count(consReporLBObj
									.getNoofvillages());
							consolidateReportLBRPT
									.setCensus_2001_code(consReporLBObj
											.getCensus_2001_code());
							consolidateReportLBRPT
									.setCensus_2011_code(consReporLBObj
											.getCensus_2011_code());
							consolidateReportLBRPT.setTotalDCount(d_count);
							consolidateReportLBRPT.setTotalsdCount(sd_count);
							consolidateReportLBRPT.setTotalVCount(v_count);

						}
					}

					if (consolidateReportLBRPT != null){
						reportList.add(consolidateReportLBRPT);
					}	
					consolidateReportlb.setTotalDCount(d_count);
					consolidateReportlb.setTotalsdCount(sd_count);
					consolidateReportlb.setTotalVCount(v_count);
	
					model.addAttribute("consolidateLBList", reportList);
					model.addAttribute("consolidateReportlandregion",
							consolidateReportlb);
					mav = new ModelAndView("rptConsolidateforLandregions");
					break;
				case 'D':
					int stateCode = consolidateReportLBRPT.getStateId();
					// char type=consolidateReportLBRPT.getStatetype();
					// char level=consolidateReportLBRPT.getStatelevel();
		
					
					List<ConsolidateReportLandregionsforDistrict> getList = new ArrayList<ConsolidateReportLandregionsforDistrict>();				
					mav = new ModelAndView("rptConsolidateforDistrict");
	
					getList = reportService.getLocalBodyLandRegionbyState(stateCode);
	
					for (ConsolidateReportLandregionsforDistrict list : getList) {
	
						if (list.getNoofsubdistricts() != null){
							sd_count = list.getNoofsubdistricts() + sd_count;
						}	
						if (list.getNoofvillages() != null){
							v_count = list.getNoofvillages() + v_count;
						consolidateReportLBRPT.setCensus_2001_code(list
								.getCensus_2001_code());
						consolidateReportLBRPT.setCensus_2011_code(list
								.getCensus_2011_code());
						}
					}
	
					String stateName = stateDao
							.getStateNameEnglishbyStateCode(stateCode);
					consolidateReportLBRPT.setState_name_english(stateName);
					consolidateReportLBRPT.setState_code(stateCode);	
					consolidateReportLBRPT.setsd_count(sd_count);
					consolidateReportLBRPT.setV_count(v_count);
	
					mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
					model.addAttribute("consolidateLBList", getList);
					mav = new ModelAndView("rptConsolidateforDistrict");
					break;
					
				case 'T' :
					 stateCode = consolidateReportLBRPT.getStateId();
					int district_code = consolidateReportLBRPT.getDistrict_code();
					httpSession.setAttribute("DIST_CODE", district_code);					
					//List<ConsolidateReportLandregionsforDistrict> getList1 = new ArrayList<ConsolidateReportLandregionsforDistrict>();
					List<ConsolidateReportLandregionsforSubDistrict> getList2 = new ArrayList<ConsolidateReportLandregionsforSubDistrict>();
					
						mav = new ModelAndView("rptConsolidateforSubDistrict");

						getList2 = reportService.getLocalBodyLandRegionbySubDistic(
								stateCode, district_code);
						for (ConsolidateReportLandregionsforSubDistrict list : getList2) {
							if (list.getNoofvillages() != null){
								v_count = list.getNoofvillages() + v_count;
								consolidateReportLBRPT.setCensus_2001_code(list
									.getCensus_2001_code());
								consolidateReportLBRPT.setCensus_2011_code(list
									.getCensus_2011_code());
							}
						}

						String stateNames = stateDao
								.getStateNameEnglishbyStateCode(stateCode);
						String district_name_english = stateDao
								.getDistrictNameEnglishbyStateCode(district_code);
						consolidateReportLBRPT.setState_name_english(stateNames);
						consolidateReportLBRPT.setState_code(stateCode);
						consolidateReportLBRPT.setdistrict_name_english(district_name_english);
						consolidateReportLBRPT.setDistrict_code(district_code);

						consolidateReportLBRPT.setV_count(v_count);
						consolidateReportLBRPT.setEntityNames(stateNames.trim()+","+district_name_english.trim());
						mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
						model.addAttribute("consolidateLBList", getList2);

			}
			return mav;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}
	
	
// ends method for back functionality	
	
	@RequestMapping("/rptConsolidateforPanbyLevel")
	public ModelAndView showConsolidateforPanbyLevel(
			@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBRPT consolidateReportLBRPT,BindingResult bindingResult,
			Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		/*added by pooja on 04-06-2015*/
		if(bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError("parentLevel");
			if(fieldError != null) {
				consolidateReportLBRPT.setParentLevel('d');
			}
		}
		int stateCode = consolidateReportLBRPT.getStateId();
		char type = consolidateReportLBRPT.getStatetype();
		char level = consolidateReportLBRPT.getStatelevel();

		int ipCount = 0;
		int vpCount = 0;
		int vcCount = 0;
		List<GetLocalBodyListbyLBtypebyState> getList = new ArrayList<GetLocalBodyListbyLBtypebyState>();
		try {
			
			getList = reportService.getLocalBodyListbyLBtypebyStateCombined(stateCode,
					type, level);
			if(getList.size()==0 && "2".equals(consolidateReportLBRPT.getLbLevel()))
			{
				level='D';
				getList = reportService.getLocalBodyListbyLBtypebyStateCombined(stateCode,
						type, level);
				
			}
			
			if (type == 'R' || type == 'T') {
				if (level == 'D'){
					mav = new ModelAndView("rptConsolidateforPanbyDLevel");
				}	
				if (level == 'I'){
					mav = new ModelAndView("rptConsolidateforPanbyILevel");
				}	
				if (level == 'V'){
					mav = new ModelAndView("rptConsolidateforPanbyVLevel");
				}	
			    } else if (type == 'U') {
				mav = new ModelAndView("rptConsolidatefoUrban");
			}
			
			if (type == 'R' || type == 'T') {
				for (GetLocalBodyListbyLBtypebyState list : getList) {
					if (level == 'D') {
						if (list.getChildCount() != null){
							ipCount = list.getChildCount() + ipCount;
						}	
						if (list.getGrandChildCount() != null){
							vpCount = list.getGrandChildCount() + vpCount;
						}	
						if(list.getVillageCouncelCount()!=null){
							vcCount=list.getVillageCouncelCount()+vcCount;
						}
						
					} else if (level == 'I') {
						if (list.getChildCount() != null){
							vpCount = list.getChildCount() + vpCount;
						}	
					}
				}
			}
			String stateName = stateDao
					.getStateNameEnglishbyStateCode(stateCode);
			consolidateReportLBRPT.setState_name_english(stateName);
			consolidateReportLBRPT.setState_code(stateCode);
			consolidateReportLBRPT.setLbtype(type);

			consolidateReportLBRPT.setIp_count(ipCount);
			consolidateReportLBRPT.setVp_count(vpCount);

			mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
			model.addAttribute("consolidateLBList", getList);
			model.addAttribute("hierarchylevel",1);
			model.addAttribute("vcCount",vcCount);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/*
	 * @RequestMapping("/rptConsolidateforPanbyIstLevel") public ModelAndView
	 * showConsolidateforPanbyIstLevel(
	 * 
	 * @ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBRPT
	 * consolidateReportLBRPT, Model model, HttpServletRequest request,
	 * 
	 * @RequestParam(value = "selstate") int stateCode,
	 * 
	 * @RequestParam(value = "type") char type,
	 * 
	 * @RequestParam(value = "level") char level,
	 * 
	 * @RequestParam(value = "parentLBCode") int parentLBCode)
	 */
	@RequestMapping("/rptConsolidateforPanbyIstLevel")
	public ModelAndView showConsolidateforPanbyIstLevel(@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBRPT consolidateReportLBRPT,BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		/*added by pooja on 04-06-2015*/
		if(bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError("parentLevel");
			if(fieldError != null) {
				consolidateReportLBRPT.setParentLevel('d');
			}
		}
		Boolean isVillageCouncil=consolidateReportLBRPT.getIsVillageCouncil();
		int stateCode = consolidateReportLBRPT.getStateId();
		char type = consolidateReportLBRPT.getLbtype();
		char level = consolidateReportLBRPT.getStatelevel();
		int parentLBCode = 0;
		if (consolidateReportLBRPT.getLbLevel().equals("2"))
			parentLBCode = consolidateReportLBRPT.getiPLbCode();
		else if (consolidateReportLBRPT.getLbLevel().equals("3"))
			parentLBCode = consolidateReportLBRPT.getvPLbCode();
		else
			parentLBCode = consolidateReportLBRPT.getLocalbodycode();

		int ipCount = 0;
		int vpCount = 0;
		int vcCount = 0;
		List<GetLocalBodyListbyLBtypebyState> getParentLBwiseList = new ArrayList<GetLocalBodyListbyLBtypebyState>();
		try {
			if (level == 'D'){
				mav = new ModelAndView("rptConsolidateforPanbyDLevel");
			}	
			else if (level == 'I'){
				mav = new ModelAndView("rptConsolidateforPanbyILevel");
			}	
			else if(level == 'V'){
				mav = new ModelAndView("rptConsolidateforPanbyVLevel");
			}
			if(stateCode==19 && level=='V'){
				getParentLBwiseList = reportService
						.getLocalBodyListbyLBtypebyStateCombined(stateCode, 'R', level,
								parentLBCode);
			}else{
				getParentLBwiseList = reportService
						.getLocalBodyListbyLBtypebyStateCombined(stateCode, type, level,
								parentLBCode);
			}
			
			for (GetLocalBodyListbyLBtypebyState list : getParentLBwiseList) {
				if (level == 'D') {
					if (list.getChildCount() != null){
						ipCount = list.getChildCount() + ipCount;
					}	
					if (list.getGrandChildCount() != null){
						vpCount = list.getGrandChildCount() + vpCount;
					}	
					if(list.getVillageCouncelCount()!=null){
						vcCount=list.getVillageCouncelCount()+vcCount;
					}
				} else if (level == 'I') {
					if (list.getChildCount() != null){
						vpCount = list.getChildCount() + vpCount;
					}	
					if(list.getVillageCouncelCount()!=null){
						vcCount=list.getVillageCouncelCount()+vcCount;
					}
				}
			}
			
			List<GetLocalBodyListbyLBtypebyState> duplicateList =new ArrayList<GetLocalBodyListbyLBtypebyState>();
			if(isVillageCouncil && isVillageCouncil !=null){
				for (GetLocalBodyListbyLBtypebyState getLocalBodyListbyLBtypebyState : getParentLBwiseList) {		
					if(getLocalBodyListbyLBtypebyState.getLocalbodyTypeName().equalsIgnoreCase("Village Council")){
						duplicateList.add(getLocalBodyListbyLBtypebyState);
					}
				}
				getParentLBwiseList.clear();
				getParentLBwiseList.addAll(duplicateList);
			}

			//String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
			String parentLbcode = stateDao.getLBHierarchy(parentLBCode);

			// consolidateReportLBRPT.setState_name_english(stateName);
			consolidateReportLBRPT.setState_name_english(parentLbcode);
			consolidateReportLBRPT.setState_code(stateCode);
			consolidateReportLBRPT.setLbtype(type);

			consolidateReportLBRPT.setIp_count(ipCount);
			consolidateReportLBRPT.setVp_count(vpCount);
			consolidateReportLBRPT.setStatetype(type);
            
			if(level=='I')
			{
			model.addAttribute("hierarchylevel",2);
			consolidateReportLBRPT.setiPLbCode(parentLBCode);
			}
			else if(level=='V')
			{
			model.addAttribute("hierarchylevel",3);
			consolidateReportLBRPT.setvPLbCode(parentLBCode);
			}
			if(level=='V' && consolidateReportLBRPT.getiPLbCode()==0)
			model.addAttribute("hierarchylevel",4);
				
			mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
			model.addAttribute("consolidateLBList", getParentLBwiseList);
			model.addAttribute("vcCount",vcCount);
			model.addAttribute("isVillageCouncil", isVillageCouncil);
			model.addAttribute("isVillageCouncilBack", false);
			

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/beckConsolidateReportToParent", method = RequestMethod.POST)
	public ModelAndView beckConsolidateReportToParent(
			@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB,
			BindingResult result, Model model, HttpSession session,
			HttpServletRequest request) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showConsolidateReportForRuralLBBack", method = RequestMethod.POST)
	public ModelAndView showConsolidateReportForRuralLBBack(
			@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB,
			BindingResult result, Model model, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = null;
		if (session.getAttribute("parentLBCodeM") != null
				&& session.getAttribute("flag").toString().charAt(0) == 'M') {
			int stateCode = Integer.parseInt(session.getAttribute("stateCode")
					.toString());
			char type = session.getAttribute("type").toString().charAt(0);
			char level = 'V';
			int parentLBCode = Integer.parseInt(session.getAttribute(
					"parentLBCodeV").toString());

			int ipCount = 0;
			int vpCount = 0;
			char flag = 'E';
			List<GetLocalBodyListbyLBtypebyState> getParentLBwiseList = new ArrayList<GetLocalBodyListbyLBtypebyState>();
			try {
				if (level == 'D'){
					mav = new ModelAndView("rptConsolidateforPanbyDLevelRural");
				}	
				if (level == 'I'){
					mav = new ModelAndView("rptConsolidateforPanbyILevelRural");
				}	
				if (level == 'V'){
					mav = new ModelAndView("rptConsolidateforPanbyVLevelRural");
				}	
				getParentLBwiseList = reportService
						.getLocalBodyListbyLBtypebyState(stateCode, type,
								level, parentLBCode);

				for (GetLocalBodyListbyLBtypebyState list : getParentLBwiseList) {
					if (level == 'D') {
						if (list.getChildCount() != null){
							ipCount = list.getChildCount() + ipCount;
						}	
						if (list.getGrandChildCount() != null){
							vpCount = list.getGrandChildCount() + vpCount;
						}	
					} else if (level == 'I') {
						if (list.getChildCount() != null){
							vpCount = list.getChildCount() + vpCount;
						}	
					}
				}

				//String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
				String parentLbcode = stateDao.getLBHierarchy(parentLBCode);

				// consolidateReportLBRPT.setState_name_english(stateName);
				consolidateReportForRuralLB.setState_name_english(parentLbcode);
				consolidateReportForRuralLB.setState_code(stateCode);
				consolidateReportForRuralLB.setLbtype(type);

				consolidateReportForRuralLB.setIp_count(ipCount);
				consolidateReportForRuralLB.setVp_count(vpCount);

				mav.addObject("consolidateReportLBRPT",
						consolidateReportForRuralLB);
				model.addAttribute("consolidateLBList", getParentLBwiseList);
				session.setAttribute("flag", flag);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			} finally {
				session.removeAttribute("parentLBCodeM");
			}
			return mav;
		} else if (session.getAttribute("getParentLBwiseList") != null
				&& session.getAttribute("parentLBCodeI") != null
				&& session.getAttribute("flag").toString().charAt(0) == 'E') {
			int stateCode = Integer.parseInt(session.getAttribute("stateCode")
					.toString());
			char type = session.getAttribute("type").toString().charAt(0);
			char level = 'I';
			int parentLBCode = Integer.parseInt(session.getAttribute(
					"parentLBCodeI").toString());

			int ipCount = 0;
			int vpCount = 0;
			char flag = 'C';
			List<GetLocalBodyListbyLBtypebyState> getParentLBwiseList = new ArrayList<GetLocalBodyListbyLBtypebyState>();
			try {
				if (level == 'D'){
					mav = new ModelAndView("rptConsolidateforPanbyDLevelRural");
				}	
				if (level == 'I'){
					mav = new ModelAndView("rptConsolidateforPanbyILevelRural");
				}	
				if (level == 'V'){
					mav = new ModelAndView("rptConsolidateforPanbyVLevelRural");
				}	
				getParentLBwiseList = reportService
						.getLocalBodyListbyLBtypebyState(stateCode, type,
								level, parentLBCode);

				for (GetLocalBodyListbyLBtypebyState list : getParentLBwiseList) {
					if (level == 'D') {
						if (list.getChildCount() != null){
							ipCount = list.getChildCount() + ipCount;
						}	
						if (list.getGrandChildCount() != null){
							vpCount = list.getGrandChildCount() + vpCount;
						}	
					} else if (level == 'I') {
						if (list.getChildCount() != null){
							vpCount = list.getChildCount() + vpCount;
						}	
					}
				}

				//String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
				String parentLbcode = stateDao.getLBHierarchy(parentLBCode);

				// consolidateReportLBRPT.setState_name_english(stateName);
				consolidateReportForRuralLB.setState_name_english(parentLbcode);
				consolidateReportForRuralLB.setState_code(stateCode);
				consolidateReportForRuralLB.setLbtype(type);

				consolidateReportForRuralLB.setIp_count(ipCount);
				consolidateReportForRuralLB.setVp_count(vpCount);

				mav.addObject("consolidateReportLBRPT",
						consolidateReportForRuralLB);
				model.addAttribute("consolidateLBList", getParentLBwiseList);
				session.setAttribute("flag", flag);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			} finally {
				session.removeAttribute("parentLBCodeI");
			}
			return mav;
		} else if (session.getAttribute("getList") != null
				&& session.getAttribute("levelD").toString().charAt(0) == 'D'
				&& session.getAttribute("flag").toString().charAt(0) == 'C') {
			int stateCode = Integer.parseInt(session.getAttribute("stateCode")
					.toString());
			char type = session.getAttribute("type").toString().charAt(0);
			char level = session.getAttribute("levelD").toString().charAt(0);
			int ipCount = 0;
			int vpCount = 0;
			char flag = 'D';
			List<GetLocalBodyListbyLBtypebyState> getList = new ArrayList<GetLocalBodyListbyLBtypebyState>();
			try {
				if (type == 'P' || type == 'T') {
					if (level == 'D'){
						mav = new ModelAndView(
								"rptConsolidateforPanbyDLevelRural");
					}	
					if (level == 'I'){
						mav = new ModelAndView(
								"rptConsolidateforPanbyILevelRural");
					}	
					if (level == 'V'){
						mav = new ModelAndView(
								"rptConsolidateforPanbyVLevelRural");
					}	
				} else if (type == 'U') {
					mav = new ModelAndView("rptConsolidatefoUrbanRural");
				}
				getList = reportService.getLocalBodyListbyLBtypebyState(
						stateCode, type, level);
				if (type == 'P' || type == 'T') {
					for (GetLocalBodyListbyLBtypebyState list : getList) {
						if (level == 'D') {
							if (list.getChildCount() != null){
								ipCount = list.getChildCount() + ipCount;
							}	
							if (list.getGrandChildCount() != null){
								vpCount = list.getGrandChildCount() + vpCount;
							}	
						} else if (level == 'I') {
							if (list.getChildCount() != null){
								vpCount = list.getChildCount() + vpCount;
							}	
						}
					}
				}
				String stateName = stateDao
						.getStateNameEnglishbyStateCode(stateCode);
				consolidateReportForRuralLB.setState_name_english(stateName);
				consolidateReportForRuralLB.setState_code(stateCode);
				consolidateReportForRuralLB.setLbtype(type);

				consolidateReportForRuralLB.setIp_count(ipCount);
				consolidateReportForRuralLB.setVp_count(vpCount);

				mav.addObject("consolidateReportLBRPT",
						consolidateReportForRuralLB);
				model.addAttribute("consolidateLBList", getList);
				session.setAttribute("flag", flag);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			} finally {
				session.removeAttribute("getList");
			}
			return mav;

		} else if (session.getAttribute("getList") != null
				&& session.getAttribute("levelD").toString().charAt(0) == 'D'
				&& session.getAttribute("flag").toString().charAt(0) == 'E') {

			int stateCode = Integer.parseInt(session.getAttribute("stateCode")
					.toString());
			char type = session.getAttribute("type").toString().charAt(0);
			char level = session.getAttribute("levelD").toString().charAt(0);

			int ipCount = 0;
			int vpCount = 0;
			List<GetLocalBodyListbyLBtypebyState> getList = new ArrayList<GetLocalBodyListbyLBtypebyState>();
			try {
				if (type == 'P' || type == 'T') {
					if (level == 'D'){
						mav = new ModelAndView(
								"rptConsolidateforPanbyDLevelRural");
					}	
					if (level == 'I'){
						mav = new ModelAndView(
								"rptConsolidateforPanbyILevelRural");
					}	
					if (level == 'V'){
						mav = new ModelAndView(
								"rptConsolidateforPanbyVLevelRural");
					}	
				} else if (type == 'U') {
					mav = new ModelAndView("rptConsolidatefoUrban");
				}
				getList = reportService.getLocalBodyListbyLBtypebyState(
						stateCode, type, level);
				if (type == 'P' || type == 'T') {
					for (GetLocalBodyListbyLBtypebyState list : getList) {
						if (level == 'D') {
							if (list.getChildCount() != null){
								ipCount = list.getChildCount() + ipCount;
							}	
							if (list.getGrandChildCount() != null){
								vpCount = list.getGrandChildCount() + vpCount;
							}	
						} else if (level == 'I') {
							if (list.getChildCount() != null){
								vpCount = list.getChildCount() + vpCount;
							}	
						}
					}
				}
				String stateName = stateDao
						.getStateNameEnglishbyStateCode(stateCode);
				consolidateReportForRuralLB.setState_name_english(stateName);
				consolidateReportForRuralLB.setState_code(stateCode);
				consolidateReportForRuralLB.setLbtype(type);

				consolidateReportForRuralLB.setIp_count(ipCount);
				consolidateReportForRuralLB.setVp_count(vpCount);

				mav.addObject("consolidateReportLBRPT",
						consolidateReportForRuralLB);
				model.addAttribute("consolidateLBList", getList);

			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			} finally {
				session.removeAttribute("getList");
			}
			return mav;
		} else if (session.getAttribute("consolidateLBList") != null) {
			List<ConsolidateReportForRuralLB> consolidateLBList = new ArrayList<ConsolidateReportForRuralLB>();
			int dbCountP = 0;
			int ipCountP = 0;
			int vpCountP = 0;
			int dbCountT = 0;
			int ipCountT = 0;
			int vpCountT = 0;
			int countUrban = 0;
			try {
				mav = new ModelAndView("rptConsolidateforRuralLB");
				consolidateLBList = (List<ConsolidateReportForRuralLB>) session
						.getAttribute("consolidateLBList");
				List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();
				int state_code = -1;
				ConsolidateReportLBRPT consolidateReportLBRPT = null;
				for (ConsolidateReportForRuralLB consReporLBObj : consolidateLBList)

				{
					if (state_code != consReporLBObj.getStateCode()) {

						if (consolidateReportLBRPT != null){
							reportList.add(consolidateReportLBRPT);
						}	
						state_code = consReporLBObj.getStateCode();
						consolidateReportLBRPT = new ConsolidateReportLBRPT();
						consolidateReportLBRPT.setState_code(consReporLBObj
								.getStateCode());
						consolidateReportLBRPT
								.setState_name_english(consReporLBObj
										.getStateNameEnglish());

						if (consReporLBObj.getCategory() == 'P'
								&& consReporLBObj.getLevel() == 'D') {
							consolidateReportLBRPT.setDp_count(consReporLBObj
									.getCount());
							dbCountP = consReporLBObj.getCount() + dbCountP;
						} else if (consReporLBObj.getCategory() == 'P'
								&& consReporLBObj.getLevel() == 'I') {
							consolidateReportLBRPT.setIp_count(consReporLBObj
									.getCount());
							ipCountP = consReporLBObj.getCount() + ipCountP;
						} else if (consReporLBObj.getCategory() == 'P'
								&& consReporLBObj.getLevel() == 'V') {
							consolidateReportLBRPT.setVp_count(consReporLBObj
									.getCount());
							vpCountP = consReporLBObj.getCount() + vpCountP;
						}

					} else {
						if (consReporLBObj.getCategory() == 'P'
								&& consReporLBObj.getLevel() == 'D') {
							consolidateReportLBRPT.setDp_count(consReporLBObj
									.getCount());
							dbCountP = consReporLBObj.getCount() + dbCountP;
						} else if (consReporLBObj.getCategory() == 'P'
								&& consReporLBObj.getLevel() == 'I') {
							consolidateReportLBRPT.setIp_count(consReporLBObj
									.getCount());
							ipCountP = consReporLBObj.getCount() + ipCountP;
						} else if (consReporLBObj.getCategory() == 'P'
								&& consReporLBObj.getLevel() == 'V') {
							consolidateReportLBRPT.setVp_count(consReporLBObj
									.getCount());
							vpCountP = consReporLBObj.getCount() + vpCountP;
						}
					}

				}

				if (consolidateReportLBRPT != null){
					reportList.add(consolidateReportLBRPT);
				}	
				consolidateReportForRuralLB.setTotalDPCount(dbCountP);
				consolidateReportForRuralLB.setTotalIPCount(ipCountP);
				consolidateReportForRuralLB.setTotalVPCount(vpCountP);
				consolidateReportForRuralLB.setTotalTradDPCount(dbCountT);
				consolidateReportForRuralLB.setTotalTradIPCount(ipCountT);
				consolidateReportForRuralLB.setTotalTradVPCount(vpCountT);
				consolidateReportForRuralLB.setTotalUrbanCount(countUrban);

				model.addAttribute("consolidateLBList", reportList);
				mav.addObject("consolidateReportLBRPT",
						consolidateReportForRuralLB);
			} catch (Exception e) {
				log.debug("Exception" + e);
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			} finally {
				session.removeAttribute("consolidateLBList");
			}
			return mav;
		}
		return mav;
	}

	// Testing for rural

	@RequestMapping("/rptConsolidateforPanbyIstLevelRural")
	public ModelAndView showConsolidateforPanbyIstLevelRural(
			@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBRPT consolidateReportLBRPT,
			Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		int stateCode = consolidateReportLBRPT.getStateId();
		char type = consolidateReportLBRPT.getLbtype();
		char level = consolidateReportLBRPT.getStatelevel();
		int parentLBCode = consolidateReportLBRPT.getLocalbodycode();

		/*
		 * session.setAttribute("stateCode", stateCode);
		 * session.setAttribute("type", type);
		 */
		if (level == 'V') {
			char flag = 'E';
			session.setAttribute("parentLBCodeV", parentLBCode);
			session.setAttribute("flag", flag);
		}
		if (level == 'I') {
			char flag = 'C';
			session.setAttribute("parentLBCodeI", parentLBCode);
			session.setAttribute("flag", flag);
		}
		if (level == 'M') {
			mav = new ModelAndView("rptConsolidateforPanbyMLevel");
			char flag = 'M';
			session.setAttribute("parentLBCodeM", parentLBCode);
			session.setAttribute("flag", flag);
			List<MappedVillageByLBCodeBean> mappedVillageByLBCode = new ArrayList<MappedVillageByLBCodeBean>();
			try {
				mappedVillageByLBCode = reportService
						.getMappedVillageByLBCode(parentLBCode);
				//String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);

				/* temporarily commented */

				/*
				 * String parentLbcode = stateDao.getLBHierarchy(parentLBCode);
				 * consolidateReportLBRPT.setState_name_english(parentLbcode);
				 */
				consolidateReportLBRPT.setState_code(stateCode);
				consolidateReportLBRPT.setLbtype(type);

				mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
				model.addAttribute("consolidateLBList", mappedVillageByLBCode);
				return mav;
			} catch (Exception e) {
				log.debug("Exception" + e);
			}

		}
		int ipCount = 0;
		int vpCount = 0;
		List<GetLocalBodyListbyLBtypebyState> getParentLBwiseList = new ArrayList<GetLocalBodyListbyLBtypebyState>();
		try {
			if (level == 'D'){
				mav = new ModelAndView("rptConsolidateforPanbyDLevelRural");
			}	
			if (level == 'I'){
				mav = new ModelAndView("rptConsolidateforPanbyILevelRural");
			}	
			if (level == 'V'){
				mav = new ModelAndView("rptConsolidateforPanbyVLevelRural");
			}	
			getParentLBwiseList = reportService
					.getLocalBodyListbyLBtypebyState(stateCode, type, level,
							parentLBCode);
			session.setAttribute("getParentLBwiseList", getParentLBwiseList);

			for (GetLocalBodyListbyLBtypebyState list : getParentLBwiseList) {
				if (level == 'D') {
					if (list.getChildCount() != null){
						ipCount = list.getChildCount() + ipCount;
					}	
					if (list.getGrandChildCount() != null){
						vpCount = list.getGrandChildCount() + vpCount;
					}	
				} else if (level == 'I') {
					if (list.getChildCount() != null){
						vpCount = list.getChildCount() + vpCount;
					}	
				}
			}

			//String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
			String parentLbcode = stateDao.getLBHierarchy(parentLBCode);

			// consolidateReportLBRPT.setState_name_english(stateName);
			consolidateReportLBRPT.setState_name_english(parentLbcode);
			consolidateReportLBRPT.setState_code(stateCode);
			consolidateReportLBRPT.setLbtype(type);

			consolidateReportLBRPT.setIp_count(ipCount);
			consolidateReportLBRPT.setVp_count(vpCount);

			mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
			model.addAttribute("consolidateLBList", getParentLBwiseList);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/welcomeLocal", method = RequestMethod.GET)
	public String welcome(@ModelAttribute("loginForm") LoginForm loginForm,
			Model model, HttpSession session, HttpServletRequest request) {
		try {
			//String abc = "";
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			return redirectPath;
		} finally {
			session.removeAttribute("consolidateLBList");
			session.removeAttribute("getList");
			session.removeAttribute("getParentLBwiseList");
			session.removeAttribute("flag");
			session.invalidate();
		}
		return "welcome";
	}

	@RequestMapping(value = "/rptConsolidateBlockGramPanchayat", method = RequestMethod.GET)
	public ModelAndView showConsolidateBlockGramPanchayat(
			HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("rptConsolidateBlockGramPanchayat");
		try {

			List<State> statelist = villageReportService
					.getConsolidatedRptForVillage();
			model.addAttribute("statelist", statelist);
			model.addAttribute("consolidateReport",
					new ConsolidateReportLBRPT());

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/rptConsolidateBlockGramPanchayat", method = RequestMethod.POST)
	public ModelAndView showRptConsolidateForBlockGramPanchayat(
			@ModelAttribute("consolidateReport") ConsolidateReportLBRPT consolidateReport,
			BindingResult result, Model model, HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("rptConsolidateBlockGramPanchayat");
		try {
			/* New Captcha code */
			String captchaAnswer = consolidateReport.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session,
					captchaAnswer);
			Integer stateCode = Integer.parseInt(consolidateReport
					.getState_name_english());
			Integer districtCode = Integer.parseInt(consolidateReport
					.getDistrictPName());

			if (!messageFlag) {
				result.rejectValue("captchaAnswer", "captcha.errorMessage");
				model.addAttribute("statelist",
						villageReportService.getConsolidatedRptForVillage());
				model.addAttribute("stateId", stateCode);
				model.addAttribute("districtId", districtCode);
				model.addAttribute("captchaError", true);
				mav.addObject("consolidateReport", consolidateReport);
				return mav;
			}

			List<Object[]> blockReportData = villageReportService
					.getConsolidatedBlockPanchyatReport(districtCode);
			model.addAttribute("reportList", blockReportData);
			if (blockReportData.isEmpty()) {
				model.addAttribute("saveMsg",
						"No Record(s) for District Wise Blocks and Mapped Gram panchayats found!");
				model.addAttribute("statelist",
						villageReportService.getConsolidatedRptForVillage());
				model.addAttribute("stateId", stateCode);
				model.addAttribute("districtId", districtCode);
				model.addAttribute("captchaError", true);
				mav.addObject("consolidateReport", consolidateReport);
				return mav;
			}

			model.addAttribute("consolidateReport",
					new ConsolidateReportLBRPT());

			String villPanchyatHeading = villageReportService
					.getVillagePanchayatName(stateCode);

			model.addAttribute("villPanchyatHeading", villPanchyatHeading);

			String statename = request.getParameter("statename");
			String disttname = request.getParameter("disttname");
			String statusMessage = "District Wise Blocks and Mapped Gram panchayats/TLB of State '"
					+ statename + "' and District '" + disttname + "' ";
			model.addAttribute("statusMessage", statusMessage);
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/stateWiseUnmappedVillagesReport", method = RequestMethod.GET)
	public ModelAndView getStateWiseUnmappedVillages(
			@ModelAttribute("statewiseUnmappedVillagesForm") VillageDataForm statewiseUnmappedVillagesForm,
			Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		List<State> stateList = null;
		stateList = new ArrayList<State>();

		try {

			stateList = stateService.getStateSourceList();
			if (stateList.size() > 0) {

				// stateList.get(0).getStateCode();
				mav = new ModelAndView("stateWiseUnmappedVillagesReport");
				// model.addAttribute("localGovtBodyForm", new
				// LocalGovtBodyForm());
				model.addAttribute("stateSourceList", stateList);
				// localGovtBodyForm.setStateDetail(stateList);
				model.addAttribute("statewiseUnmappedVillagesForm",
						statewiseUnmappedVillagesForm);

			} else {

				mav = new ModelAndView("errorConfigshow");
				request.setAttribute("message", "state not found in list");

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/stateWiseUnmappedVillagesReport", method = RequestMethod.POST)
	public ModelAndView getStateWiseUnmappedVillagesPost(
			@ModelAttribute("statewiseUnmappedVillagesForm") VillageDataForm statewiseUnmappedVillagesForm,
			Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		List<State> stateList = null;
		List<StatewiseUnmappedVillages> statewiseUnmappedVillagesList = null;
		Integer stateCode = null;
		stateList = new ArrayList<State>();
		boolean messageFlag = false;
		try {

			stateList = stateService.getStateSourceList();

			mav = new ModelAndView("stateWiseUnmappedVillagesReport");
			model.addAttribute("stateSourceList", stateList);
			model.addAttribute("statewiseUnmappedVillagesForm",
					statewiseUnmappedVillagesForm);
			String captchaAnswer = statewiseUnmappedVillagesForm
					.getCaptchaAnswers();
			CaptchaValidator captchaValidator = new CaptchaValidator();

			if (captchaAnswer != null){
				messageFlag = captchaValidator.validateCaptcha(httpSession,
						captchaAnswer);
			}	
			statewiseUnmappedVillagesForm.setCaptchaAnswers(null);

			if (messageFlag == true) {
				if (statewiseUnmappedVillagesForm.getStateNameEnglish() != null){
					stateCode = Integer.parseInt(statewiseUnmappedVillagesForm
							.getStateNameEnglish());
				}	
				if (stateCode != null) {

					List<LandRegionDetail> landregionDetail = null;
					landregionDetail = new ArrayList<LandRegionDetail>();
					landregionDetail = reportService
							.landRegionDetail('S', stateCode);
					if (landregionDetail.size() > 0){

						model.addAttribute("message",
								"State wise Unmapped Villages of "
										+ landregionDetail.get(0)
												.getStateNameEnglish().trim());
					}	
					httpSession.setAttribute("message",
							"State wise Unmapped Villages of "
									+ landregionDetail.get(0)
											.getStateNameEnglish().trim());
					httpSession.setAttribute("limit",
							statewiseUnmappedVillagesForm.getPageRows());
					statewiseUnmappedVillagesForm.setOffset(1);
					httpSession.setAttribute("offset",
							statewiseUnmappedVillagesForm.getOffset());
					httpSession.setAttribute("stateCode", stateCode);

					statewiseUnmappedVillagesList = reportService
							.getStateWiseUnmappedVillages( // Total Record of
															// List

									httpSession.getAttribute("stateCode") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("stateCode")
													.toString()), null, null);
					httpSession.setAttribute("counter",
							statewiseUnmappedVillagesList.size()); // Set
																	// Counter
																	// Value to
																	// total
																	// Record

					statewiseUnmappedVillagesList = reportService
							.getStateWiseUnmappedVillages(
									httpSession.getAttribute("stateCode") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("stateCode")
													.toString()),

									httpSession.getAttribute("limit") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("limit")
													.toString()), 0);

					model.addAttribute("offsets", Integer.parseInt(httpSession
							.getAttribute("offset").toString()) - 1);
					model.addAttribute("limits", Integer.parseInt(httpSession
							.getAttribute("limit").toString()));
					model.addAttribute("counter", Integer.parseInt(httpSession
							.getAttribute("counter").toString()));
					model.addAttribute(
							"serise",
							((Integer.parseInt(httpSession.getAttribute(
									"counter").toString())))
									/ ((Integer.parseInt(httpSession
											.getAttribute("limit").toString())) * 5));
					model.addAttribute(
							"villageCount",
							"Page "
									+ Integer.parseInt(httpSession
											.getAttribute("offset").toString())
									+ " of "
									+ (Integer
											.parseInt(httpSession.getAttribute(
													"counter").toString())
											/ Integer.parseInt(httpSession
													.getAttribute("limit")
													.toString()) + 1));
					statewiseUnmappedVillagesForm.setPageRows(Integer
							.parseInt(httpSession.getAttribute("limit")
									.toString()));
					/*modified by pooja on 06-07-2015 to display error message when no record*/
				if(statewiseUnmappedVillagesList!=null && statewiseUnmappedVillagesList.size()>0){
					model.addAttribute("SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY",
							statewiseUnmappedVillagesList);
					model.addAttribute("size",
							statewiseUnmappedVillagesList.size());
				}
				else{
					 model.addAttribute("flag2",1);
				}

				} else {
					mav = new ModelAndView("errorConfigshow");
					request.setAttribute("message", "Problem to reading data");
				}
			}

			else {
				mav.addObject("flag1", 1);
				model.addAttribute("captchaSuccess2", messageFlag);
			}

			statewiseUnmappedVillagesForm.setStateNameEnglish(null);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/stateWiseUnmappedVillagesReportPagination", method = RequestMethod.POST)
	public ModelAndView getStateWiseUnmappedVillagesPaginationPost(
			@ModelAttribute("statewiseUnmappedVillagesForm") VillageDataForm statewiseUnmappedVillagesForm,
			Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;

		List<StatewiseUnmappedVillages> statewiseUnmappedVillagesList = null;
		//Integer stateCode = null;

		try {
			mav = new ModelAndView("stateWiseUnmappedVillagesReport");

			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer
					.parseInt(httpSession.getAttribute("counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString())
							+ statewiseUnmappedVillagesForm.getDirection() > 0) {
				statewiseUnmappedVillagesForm
						.setOffset(Integer.parseInt(httpSession.getAttribute(
								"offset").toString())
								+ statewiseUnmappedVillagesForm.getDirection());
				httpSession.setAttribute("offset",
						statewiseUnmappedVillagesForm.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset")
					.toString()) == (Integer.parseInt(httpSession.getAttribute(
					"counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& statewiseUnmappedVillagesForm.getDirection() == -1
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString())
							+ statewiseUnmappedVillagesForm.getDirection() > 0) {
				statewiseUnmappedVillagesForm
						.setOffset(Integer.parseInt(httpSession.getAttribute(
								"offset").toString())
								+ statewiseUnmappedVillagesForm.getDirection());
				httpSession.setAttribute("offset",
						statewiseUnmappedVillagesForm.getOffset());
				httpSession.setAttribute("counter", Integer
						.parseInt(httpSession.getAttribute("counter")
								.toString()));
			}

			model.addAttribute("message",
					httpSession.getAttribute("message") == null ? null
							: httpSession.getAttribute("message").toString());
			statewiseUnmappedVillagesList = reportService
					.getStateWiseUnmappedVillages(
							httpSession.getAttribute("stateCode") == null ? null
									: Integer.parseInt(httpSession
											.getAttribute("stateCode")
											.toString()),

							httpSession.getAttribute("limit") == null ? null
									: Integer.parseInt(httpSession
											.getAttribute("limit").toString()),
							Integer.parseInt(httpSession.getAttribute("limit")
									.toString())
									* (Integer.parseInt(httpSession
											.getAttribute("offset").toString()) - 1));

			model.addAttribute("offsets", Integer.parseInt(httpSession
					.getAttribute("offset").toString()) - 1);
			model.addAttribute("limits", Integer.parseInt(httpSession
					.getAttribute("limit").toString()));
			model.addAttribute(
					"villageCount",
					"Page "
							+ Integer.parseInt(httpSession.getAttribute(
									"offset").toString())
							+ " of "
							+ (Integer.parseInt(httpSession.getAttribute(
									"counter").toString())
									/ Integer.parseInt(httpSession
											.getAttribute("limit").toString()) + 1));
			statewiseUnmappedVillagesForm.setPageRows(Integer
					.parseInt(httpSession.getAttribute("limit").toString()));

			model.addAttribute("SEARCH_UNMAPPED_VILLAGES_RESULTS_KEY",
					statewiseUnmappedVillagesList);
			model.addAttribute("statewiseUnmappedVillagesList",
					statewiseUnmappedVillagesList);
			model.addAttribute("size", statewiseUnmappedVillagesList.size());

			statewiseUnmappedVillagesForm.setStateNameEnglish(null);
			return mav;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}

	@RequestMapping(value = "/stateWiseGPtoVillageMappingReport", method = RequestMethod.GET)
	public ModelAndView getStateWiseGPtoVillageMapping(
			@ModelAttribute("stateWiseGPtoVillageMappingForm") VillageDataForm stateWiseGPtoVillageMappingForm,
			Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;

		try {

			// stateList.get(0).getStateCode();
			mav = new ModelAndView("stateWiseGPtoVillageMappingReport");
			// model.addAttribute("localGovtBodyForm", new LocalGovtBodyForm());

			// localGovtBodyForm.setStateDetail(stateList);
			model.addAttribute("stateWiseGPtoVillageMappingForm",
					stateWiseGPtoVillageMappingForm);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/stateWiseGPtoVillageMappingReport", method = RequestMethod.POST)
	public ModelAndView getStateWiseGPtoVillageMappingPost(
			@ModelAttribute("stateWiseGPtoVillageMappingForm") VillageDataForm stateWiseGPtoVillageMappingForm,
			Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		List<StatewiseGPtoVillageMapped> stateWiseGPtoVillageMappingList = null;
		stateWiseGPtoVillageMappingList = new ArrayList<StatewiseGPtoVillageMapped>();

		boolean messageFlag = false;
		try {

			mav = new ModelAndView("stateWiseGPtoVillageMappingReport");

			model.addAttribute("stateWiseGPtoVillageMappingForm",
					stateWiseGPtoVillageMappingForm);
			String captchaAnswer = stateWiseGPtoVillageMappingForm
					.getCaptchaAnswers();
			CaptchaValidator captchaValidator = new CaptchaValidator();

			if (captchaAnswer != null){
				messageFlag = captchaValidator.validateCaptcha(session,
						captchaAnswer);
			}	
			stateWiseGPtoVillageMappingForm.setCaptchaAnswers(null);

			if (messageFlag == true) {

				stateWiseGPtoVillageMappingList = reportService
						.getStateWiseGPtoVillageMapping();
				model.addAttribute("StatewiseGPtoVillageMappingList",
						stateWiseGPtoVillageMappingList);
				return mav;
			}

			else {
				mav.addObject("flag1", 1);
				model.addAttribute("captchaSuccess2", messageFlag);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/stateWiseGramPanchayats", method = RequestMethod.GET)
	public ModelAndView getStateWiseGramPanchayats(
			@ModelAttribute("statewiseUnmappedVillagesForm") VillageDataForm statewiseUnmappedVillagesForm,
			Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		List<State> stateList = null;
		stateList = new ArrayList<State>();

		try {

			stateList = stateService.getStateSourceList();
			if (stateList.size() > 0) {

				// stateList.get(0).getStateCode();
				mav = new ModelAndView("stateWiseGramPanchayats");
				// model.addAttribute("localGovtBodyForm", new
				// LocalGovtBodyForm());
				model.addAttribute("stateSourceList", stateList);
				// localGovtBodyForm.setStateDetail(stateList);
				model.addAttribute("statewiseUnmappedVillagesForm",
						statewiseUnmappedVillagesForm);

			} else {

				mav = new ModelAndView("errorConfigshow");
				request.setAttribute("message", "state not found in list");

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/stateWiseMappedVillagesReport", method = RequestMethod.POST)
	public ModelAndView getStateWiseGramPanchayatsPost(
			@ModelAttribute("statewiseUnmappedVillagesForm") VillageDataForm statewiseUnmappedVillagesForm,
			Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		List<State> stateList = null;
		List<StateWiseDistrictVillagePanchaytMapping> stateWiseGPtoVillageMappingList = null;
		stateWiseGPtoVillageMappingList = new ArrayList<StateWiseDistrictVillagePanchaytMapping>();
		Integer stateCode = null;
		stateList = new ArrayList<State>();
		boolean messageFlag = false;
		boolean districtpan = true;
		int totalVillage = 0;
		int totalGp = 0;
		int totalMappedVil = 0;
		float mappedPercent = 0;
		float mappedVillage = 0;
		try {

			stateList = stateService.getStateSourceList();

			mav = new ModelAndView("stateWiseGramPanchayats");
			model.addAttribute("stateSourceList", stateList);
			model.addAttribute("statewiseUnmappedVillagesForm",
					statewiseUnmappedVillagesForm);
			String captchaAnswer = statewiseUnmappedVillagesForm
					.getCaptchaAnswers();
			CaptchaValidator captchaValidator = new CaptchaValidator();

			if (captchaAnswer != null){
				messageFlag = captchaValidator.validateCaptcha(httpSession,
						captchaAnswer);
			}	
			statewiseUnmappedVillagesForm.setCaptchaAnswers(null);

			if (messageFlag == true) {
				if (statewiseUnmappedVillagesForm.getStateNameEnglish() != null){
					stateCode = Integer.parseInt(statewiseUnmappedVillagesForm
							.getStateNameEnglish());
				}	
				if (stateCode != null) {

					stateWiseGPtoVillageMappingList = reportService
							.getStateWiseGPtoVillageMapping(stateCode);
					if (stateWiseGPtoVillageMappingList!=null  && !stateWiseGPtoVillageMappingList.isEmpty() ) {
						for (int i = 0; i < stateWiseGPtoVillageMappingList
								.size(); i++) {
							totalVillage += stateWiseGPtoVillageMappingList
									.get(i).getToalVillage();
							totalGp += stateWiseGPtoVillageMappingList.get(i)
									.getTotalgrampan();
							totalMappedVil += stateWiseGPtoVillageMappingList
									.get(i).getMappedVillage();

						}
						mappedVillage = totalMappedVil;
						if (totalVillage == 0 || mappedVillage == 0)
							mappedPercent = 0;
						else
							mappedPercent = (float) ((mappedVillage / totalVillage) * 100.0);
						model.addAttribute("totalVillage", totalVillage);
						model.addAttribute("totalGp", totalGp);
						model.addAttribute("totalMappedVil", totalMappedVil);
						DecimalFormat df = new DecimalFormat("0.00");
						mappedPercent = Float.parseFloat(df.format(mappedPercent));
						model.addAttribute("mappedPercent", mappedPercent);
						// model.addAttribute("count",stateWiseGPtoVillageMappingList.size());
						model.addAttribute("StatewiseGPtoVillageMappingList",
								stateWiseGPtoVillageMappingList);
					}

					else {
						districtpan = false;
						model.addAttribute("districtpan", districtpan);

					}
					return mav;
				} else {
					mav = new ModelAndView("errorConfigshow");
					request.setAttribute("message", "Problem to reading data");
				}
			}

			else {
				mav.addObject("flag1", 1);
				model.addAttribute("captchaSuccess2", messageFlag);
			}

			statewiseUnmappedVillagesForm.setStateNameEnglish(null);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/rptConsolidateforRuralLB", method = RequestMethod.GET)
	public ModelAndView showConsolidateReportForRuralLBGET(@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB,BindingResult result, Model model, HttpSession session,HttpServletRequest request) {
		//List<ConsolidateReportForRuralLB> consolidateLBList = new ArrayList<ConsolidateReportForRuralLB>();
		ModelAndView mav = new ModelAndView("rptConsolidateforRuralLB");
		// mav.addObject("consolidateLBList", consolidateLBList);
		model.addAttribute("get", true);
		return mav;
	}
	
	
	@RequestMapping(value = "/rptConsolidateforRuralLBPES", method = RequestMethod.GET)
	public ModelAndView showConsolidateReportForRuralLBGETPES(@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB,BindingResult result, Model model, HttpSession session,HttpServletRequest request) {
		//List<ConsolidateReportForRuralLB> consolidateLBList = new ArrayList<ConsolidateReportForRuralLB>();
		ModelAndView mav = new ModelAndView("rptConsolidateforRuralLBPES");
		// mav.addObject("consolidateLBList", consolidateLBList);
		model.addAttribute("get", true);
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/rptConsolidateforRuralLB", method = RequestMethod.POST)
	public ModelAndView showConsolidateReportForRuralLB(
		@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB,BindingResult result, Model model, HttpSession session,HttpServletRequest request) {
		ModelAndView mav = null;
		String financialYear = consolidateReportForRuralLB.getFinancialYear();
		char flag = 'D';
		try {
			mav = new ModelAndView("rptConsolidateforRuralLB");
			boolean messageFlag = false;
			
			String captchaAnswer = consolidateReportForRuralLB
					.getCaptchaAnswers();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			if (captchaAnswer != null) {
				messageFlag = captchaValidator.validateCaptcha(session,
						captchaAnswer);
			}
			consolidateReportForRuralLB.setCaptchaAnswers(null);

			if (messageFlag) {
				if((CURRENTLY_ACTIVE_LOCALBODY).equals(consolidateReportForRuralLB.getSearchCriteriaType())){
					financialYear=null;
				}
				MyStackList<ConsolidatePanchaytReportVariable> stack =new  MyStackList<ConsolidatePanchaytReportVariable>();
				List<ConsolidateReportForRuralLB> consolidateLBList = reportService
						.getConsolidatedRptForRuralLBs(financialYear);
				
				consolidateReportForRuralLB.setParentLevel('S');
				consolidateReportForRuralLB.setParentCode(0);
				consolidateReportForRuralLB.setFinancialYear(financialYear);
				session.setAttribute("flag", flag);
				List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();
				int state_code = -1;
				ConsolidateReportLBRPT consolidateReportLBRPT = null;
				for (ConsolidateReportForRuralLB consReporLBObj : consolidateLBList) {
					int count = consReporLBObj.getCount();
					if (state_code != consReporLBObj.getStateCode()) {
						if (consolidateReportLBRPT != null) {
							reportList.add(consolidateReportLBRPT);
						}
						state_code = consReporLBObj.getStateCode();
						consolidateReportLBRPT = new ConsolidateReportLBRPT();
						consolidateReportLBRPT.setState_code(consReporLBObj
								.getStateCode());
						consolidateReportLBRPT
								.setState_name_english(consReporLBObj
										.getStateNameEnglish());
						if (consReporLBObj.getLevel() == 'D') {
							consolidateReportLBRPT.setDp_count(count);
						} else if (consReporLBObj.getLevel() == 'I') {
							consolidateReportLBRPT.setIp_count(count);
						} else if (consReporLBObj.getLevel() == 'V') {
							consolidateReportLBRPT.setVp_count(count);
						}
					} else {
						if (consReporLBObj.getLevel() == 'D') {
							consolidateReportLBRPT.setDp_count(count);
						} else if (consReporLBObj.getLevel() == 'I') {
							consolidateReportLBRPT.setIp_count(count);
						} else if (consReporLBObj.getLevel() == 'V') {
							consolidateReportLBRPT.setVp_count(count);
						}
					}
				}
				if (consolidateReportLBRPT != null){
					reportList.add(consolidateReportLBRPT);
				}	
				model.addAttribute("get", false);
				model.addAttribute("consolidateLBList", reportList);
				mav.addObject("stack", stack); 
			
				mav.addObject("consolidateReportLBRPT",
						consolidateReportForRuralLB);
			} else {
				mav.addObject("flag1", 1);
				model.addAttribute("captchaSuccess1", messageFlag);
				model.addAttribute("get", true);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/rptConsolidateforRuralLBPES", method = RequestMethod.POST)
	public ModelAndView showConsolidateReportForRuralLBPES(
		@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB,BindingResult result, Model model, HttpSession session,HttpServletRequest request) {
		ModelAndView mav = null;
		String financialYear = consolidateReportForRuralLB.getFinancialYear();
		char flag = 'D';
		try {
			mav = new ModelAndView("rptConsolidateforRuralLBPES");
			boolean messageFlag = false;
			
			String captchaAnswer = consolidateReportForRuralLB
					.getCaptchaAnswers();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			if (captchaAnswer != null) {
				messageFlag = captchaValidator.validateCaptcha(session,
						captchaAnswer);
			}
			consolidateReportForRuralLB.setCaptchaAnswers(null);

			if (messageFlag) {
				if((CURRENTLY_ACTIVE_LOCALBODY).equals(consolidateReportForRuralLB.getSearchCriteriaType())){
					financialYear=null;
				}
				MyStackList<ConsolidatePanchaytReportVariable> stack =new  MyStackList<ConsolidatePanchaytReportVariable>();
				List<ConsolidateReportForRuralLB> consolidateLBList = reportService
						.getConsolidatedRptForRuralLBsPES(financialYear);
				
				consolidateReportForRuralLB.setParentLevel('S');
				consolidateReportForRuralLB.setParentCode(0);
				consolidateReportForRuralLB.setFinancialYear(financialYear);
				session.setAttribute("flag", flag);
				List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();
				int state_code = -1;
				ConsolidateReportLBRPT consolidateReportLBRPT = null;
				for (ConsolidateReportForRuralLB consReporLBObj : consolidateLBList) {
					int count = consReporLBObj.getCount();
					if (state_code != consReporLBObj.getStateCode()) {
						if (consolidateReportLBRPT != null) {
							reportList.add(consolidateReportLBRPT);
						}
						state_code = consReporLBObj.getStateCode();
						consolidateReportLBRPT = new ConsolidateReportLBRPT();
						consolidateReportLBRPT.setState_code(consReporLBObj
								.getStateCode());
						consolidateReportLBRPT
								.setState_name_english(consReporLBObj
										.getStateNameEnglish());
						if (consReporLBObj.getLevel() == 'D') {
							consolidateReportLBRPT.setDp_count(count);
						} else if (consReporLBObj.getLevel() == 'I') {
							consolidateReportLBRPT.setIp_count(count);
						} else if (consReporLBObj.getLevel() == 'V') {
							consolidateReportLBRPT.setVp_count(count);
						}
					} else {
						if (consReporLBObj.getLevel() == 'D') {
							consolidateReportLBRPT.setDp_count(count);
						} else if (consReporLBObj.getLevel() == 'I') {
							consolidateReportLBRPT.setIp_count(count);
						} else if (consReporLBObj.getLevel() == 'V') {
							consolidateReportLBRPT.setVp_count(count);
						}
					}
				}
				if (consolidateReportLBRPT != null){
					reportList.add(consolidateReportLBRPT);
				}	
				model.addAttribute("get", false);
				model.addAttribute("consolidateLBList", reportList);
				mav.addObject("stack", stack); 
			
				mav.addObject("consolidateReportLBRPT",
						consolidateReportForRuralLB);
			} else {
				mav.addObject("flag1", 1);
				model.addAttribute("captchaSuccess1", messageFlag);
				model.addAttribute("get", true);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
		return mav;
	}

	@RequestMapping("/rptConsolidateforPanbyLevelRural")
	public ModelAndView showConsolidateforPanbyLevelRural(@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBRPT consolidateReportLBRPT, BindingResult bindingResult,
    @ModelAttribute("stack") MyStackList<ConsolidatePanchaytReportVariable> stack,Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = null;
		/* Added by Sushil on 22-05-2015 */
		if(bindingResult.hasErrors()) {
			mav = new ModelAndView("error");
			return mav;
		}
		int stateCode = consolidateReportLBRPT.getStateId();
		String financialYear = consolidateReportLBRPT.getFinancialYear();
		consolidateReportLBRPT.setFinancialYear(financialYear);
		char type ='P';
		char level = consolidateReportLBRPT.getStatelevel();
		Integer localbodycode = consolidateReportLBRPT.getLocalbodycode();
		//System.out.println("Runing Function-stateCode:"+stateCode+"level:"+level+"LocalbodyCode:"+localbodycode);
		request.setAttribute("lbCode",localbodycode);
		if(stateCode!=0) {
		httpSession.setAttribute("stateCode", stateCode);
		}else {
		if(httpSession.getAttribute("stateCode")!=null) {
				stateCode=Integer.parseInt(httpSession.getAttribute("stateCode").toString());
			}
		}
		httpSession.setAttribute("type", type);
		httpSession.setAttribute("levelD", level);
		int ipCount = 0;
		int vpCount = 0;
		String Message=null;
		boolean flag=false;
		
		List<GetLocalBodyListbyLBtypebyState> getList = new ArrayList<GetLocalBodyListbyLBtypebyState>();
		ConsolidatePanchaytReportVariable consolidatePanchaytReportVariable=new ConsolidatePanchaytReportVariable();
		consolidatePanchaytReportVariable.setCode(consolidateReportLBRPT.getParentCode());
		consolidatePanchaytReportVariable.setLevel(consolidateReportLBRPT.getParentLevel());
		try {
			if (type == 'P' || type == 'T') {
			if (level == 'D') {
					for(ConsolidatePanchaytReportVariable element:stack)
					{
						if(consolidateReportLBRPT.getParentLevel()==element.getLevel())
						{
							flag=true;
							break;
						}
					}
					if(!flag)
					{
						stack.push(consolidatePanchaytReportVariable);
					}
					mav = new ModelAndView("rptConsolidateforPanbyDLevelRural");
					
					Message=reportService.getDistrictPanchayatNomenclatureNameWithStateName(stateCode);
					consolidateReportLBRPT.setState_name_english(Message);
					 consolidateReportLBRPT.setParentLevel(level);
					 consolidateReportLBRPT.setParentCode(stateCode);
				}
				if (level == 'I') {
					if(consolidateReportLBRPT.getParentLevel()=='D'){
						consolidateReportLBRPT.setOtherInformationFlag(true);
						consolidatePanchaytReportVariable.setOtherInformationFlag(true);
					}
					mav = new ModelAndView("rptConsolidateforPanbyILevelRural");
					for(ConsolidatePanchaytReportVariable element:stack)
					{
					   if(consolidateReportLBRPT.getParentLevel()==element.getLevel())
						{
							flag=true;
							break;
						}
					}
					if(!flag)
					{
						stack.push(consolidatePanchaytReportVariable);
					}
					 consolidateReportLBRPT.setParentLevel(level);
					 consolidateReportLBRPT.setParentCode(localbodycode);
					
					if(localbodycode==0)
					{
						Message=reportService.getDistrictPanchayatNomenclatureNameWithStateName(stateCode);
						consolidateReportLBRPT.setState_name_english(Message);
					}
					else
					{	
					Message = reportService.getLocalBodyNameByCode(localbodycode);
					consolidateReportLBRPT.setState_name_english(Message);
					}
				}
				if (level == 'V') {
					if(consolidateReportLBRPT.getParentLevel()=='I'){
						consolidateReportLBRPT.setOtherInformationFlag(true);
						consolidatePanchaytReportVariable.setOtherInformationFlag(true);
					}
					mav = new ModelAndView("rptConsolidateforPanbyVLevelRural");
					
					for(ConsolidatePanchaytReportVariable element:stack)
					{
						if(consolidateReportLBRPT.getParentLevel()==element.getLevel())
						{
							flag=true;
							break;
						}
					}
					if(!flag)
					{
						stack.push(consolidatePanchaytReportVariable);
					}
					 consolidateReportLBRPT.setParentLevel(level);
					 consolidateReportLBRPT.setParentCode(localbodycode);
					
					if(localbodycode==0)
					{
						Message=reportService.getDistrictPanchayatNomenclatureNameWithStateName(stateCode);
						consolidateReportLBRPT.setState_name_english(Message);
					}
					else
					{	
					Message = reportService.getLocalBodyNameByCode(localbodycode);
					consolidateReportLBRPT.setState_name_english(Message);
					}
				}
				if(level=='M') {
					if(consolidateReportLBRPT.getParentLevel()=='V'){
						consolidateReportLBRPT.setOtherInformationFlag(true);
						consolidatePanchaytReportVariable.setOtherInformationFlag(true);
					}
					mav = new ModelAndView("rptConsolidateforPanbyMLevel");

					for(ConsolidatePanchaytReportVariable element:stack)
					{
						if(consolidateReportLBRPT.getParentLevel()==element.getLevel())
						{
							flag=true;
							break;
						}
					}
					if(!flag)
					{
						stack.push(consolidatePanchaytReportVariable);
					}
					
					Integer Plblc=consolidatePanchaytReportVariable.getCode();
					if(Plblc==0)
					{
						Message=reportService.getDistrictPanchayatNomenclatureNameWithStateName(stateCode);
						consolidateReportLBRPT.setState_name_english(Message);
					}
					else
					{	
						Message = reportService.getLocalBodyName(localbodycode,Plblc);
						consolidateReportLBRPT.setState_name_english(Message);
					}
					String otherInfotitle="";
					if(Message!=null && Message.indexOf("of")!=-1 && Message.indexOf(")")!=-1)
					{
						otherInfotitle=Message.substring(Message.indexOf("of"),(Message.indexOf(")")+1));
					}
					request.setAttribute("otherInfotitle", otherInfotitle);
					request.setAttribute("DefaultLanguageCode", 1);
					request.setAttribute("stateCode", stateCode);
					//char flag = 'M';
					List<MappedVillageByLBCodeBean> mappedVillageByLBCode = new ArrayList<MappedVillageByLBCodeBean>();
					mappedVillageByLBCode = reportService.getMappedVillageByLBCode(localbodycode);
					mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
					model.addAttribute("consolidateLBList", mappedVillageByLBCode);
				    return mav;
				}
			} else if (type == 'U') {
				mav = new ModelAndView("rptConsolidatefoUrban");
			}
			//if(localbodycode==0)
			
			//getList = reportService.getLocalBodyListbyLBtypebyState(stateCode, type, level, localbodycode);
			getList = reportService.getLocalBodyListbyLBtypebyFinYear(stateCode, type, level, localbodycode, financialYear);
			
			
			httpSession.setAttribute("getList", getList);
			if (type == 'P' || type == 'T') {
				for (GetLocalBodyListbyLBtypebyState list : getList) {
					if (level == 'D') {
						Integer childCount = list.getChildCount();
						if (childCount != null){
							ipCount += childCount;
						Integer grandChildCount = list.getGrandChildCount();
						
						if (grandChildCount != null){
							vpCount += grandChildCount;
						}	
						}
					} else if (level == 'I') {
						Integer iChildCount = list.getChildCount();
						if (iChildCount != null){
							vpCount += iChildCount;
						}	
					}
				}
			}
			
			//mav.addObject("stack", stack); 
			/**
			 * @author Maneesh Kumar
			 * @Since 19/05/2014
			 * @Param include Other information of panchayat by including reports/otherPesAppReport.jsp and set following Parameter. 
			 */
			String typeName="";
			String otherInfotitle="";
			if(Message!=null && Message!="" && Message.indexOf(" ")!=-1){
				typeName=Message.substring(0, Message.indexOf(" ",Message.indexOf(" ")+1));
				if(Message.indexOf("of")!=-1 && Message.indexOf(")")!=-1){
				otherInfotitle=Message.substring(Message.indexOf("of"),(Message.indexOf(")")+1));
				}
			}
			if((level=='I' || level=='V' || level=='D')){
				request.setAttribute("typeName", typeName);
				request.setAttribute("otherInfotitle", otherInfotitle);
				request.setAttribute("DefaultLanguageCode", 1);
				request.setAttribute("stateCode", stateCode);
			}
			
			/***
			 * End 
			 */
			consolidateReportLBRPT.setState_code(stateCode);
			consolidateReportLBRPT.setLbtype(type);
			consolidateReportLBRPT.setIp_count(ipCount);
			consolidateReportLBRPT.setVp_count(vpCount);
			mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
			model.addAttribute("consolidateLBList", getList);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	@RequestMapping("/rptConsolidateforPanbyLevelRuralPES")
	public ModelAndView showConsolidateforPanbyLevelRuralPES(@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBRPT consolidateReportLBRPT, BindingResult bindingResult,
	@ModelAttribute("stack") MyStackList<ConsolidatePanchaytReportVariable> stack, Model model,  HttpServletRequest request,HttpSession httpSession) {
		ModelAndView mav = null;
		/* Added by Sushil on 22-05-2015 */
		if(bindingResult.hasErrors()) {
			mav = new ModelAndView("error");
			return mav;
		}
		int stateCode = consolidateReportLBRPT.getStateId();
		String financialYear = consolidateReportLBRPT.getFinancialYear();
		consolidateReportLBRPT.setFinancialYear(financialYear);
		char type ='P';
		char level = consolidateReportLBRPT.getStatelevel();
		Integer localbodycode = consolidateReportLBRPT.getLocalbodycode();
		//System.out.println("Runing Function-stateCode:"+stateCode+"level:"+level+"LocalbodyCode:"+localbodycode);
		request.setAttribute("lbCode",localbodycode);
		if(stateCode!=0) {
			
			httpSession.setAttribute("stateCode", stateCode);
		}else {
			if(httpSession.getAttribute("stateCode")!=null) {
				stateCode=Integer.parseInt(httpSession.getAttribute("stateCode").toString());
			}
		}
		httpSession.setAttribute("type", type);
		httpSession.setAttribute("levelD", level);
		int ipCount = 0;
		int vpCount = 0;
		String Message=null;
		boolean flag=false;
		
		List<GetLocalBodyListbyLBtypebyState> getList = new ArrayList<GetLocalBodyListbyLBtypebyState>();
		ConsolidatePanchaytReportVariable consolidatePanchaytReportVariable=new ConsolidatePanchaytReportVariable();
		consolidatePanchaytReportVariable.setCode(consolidateReportLBRPT.getParentCode());
		consolidatePanchaytReportVariable.setLevel(consolidateReportLBRPT.getParentLevel());
		try {
			if (type == 'P' || type == 'T') {
				if (level == 'D') {
					for(ConsolidatePanchaytReportVariable element:stack)
					{
						if(consolidateReportLBRPT.getParentLevel()==element.getLevel())
						{
							flag=true;
							break;
						}
					}
					if(!flag)
					{
						stack.push(consolidatePanchaytReportVariable);
					}
					mav = new ModelAndView("rptConsolidateforPanbyDLevelRuralPES");
					
					Message=reportService.getDistrictPanchayatNomenclatureNameWithStateNamePES(stateCode);
					consolidateReportLBRPT.setState_name_english(Message);
					 consolidateReportLBRPT.setParentLevel(level);
					 consolidateReportLBRPT.setParentCode(stateCode);
				}
				if (level == 'I') {
					if(consolidateReportLBRPT.getParentLevel()=='D'){
						consolidateReportLBRPT.setOtherInformationFlag(true);
						consolidatePanchaytReportVariable.setOtherInformationFlag(true);
					}
					mav = new ModelAndView("rptConsolidateforPanbyILevelRuralPES");
					for(ConsolidatePanchaytReportVariable element:stack)
					{
						if(consolidateReportLBRPT.getParentLevel()==element.getLevel())
						{
							flag=true;
							break;
						}
					}
					if(!flag)
					{
						stack.push(consolidatePanchaytReportVariable);
					}
					 consolidateReportLBRPT.setParentLevel(level);
					 consolidateReportLBRPT.setParentCode(localbodycode);
					
					if(localbodycode==0)
					{
						Message=reportService.getDistrictPanchayatNomenclatureNameWithStateNamePES(stateCode);
						consolidateReportLBRPT.setState_name_english(Message);
					}
					else
					{	
					Message = reportService.getLocalBodyNameByCodePES(localbodycode);
					consolidateReportLBRPT.setState_name_english(Message);
					}
				}
				if (level == 'V') {
					if(consolidateReportLBRPT.getParentLevel()=='I'){
						consolidateReportLBRPT.setOtherInformationFlag(true);
						consolidatePanchaytReportVariable.setOtherInformationFlag(true);
					}
					mav = new ModelAndView("rptConsolidateforPanbyVLevelRuralPES");
					
					for(ConsolidatePanchaytReportVariable element:stack)
					{
						if(consolidateReportLBRPT.getParentLevel()==element.getLevel())
						{
							flag=true;
							break;
						}
					}
					if(!flag)
					{
						stack.push(consolidatePanchaytReportVariable);
					}
				    consolidateReportLBRPT.setParentLevel(level);
					consolidateReportLBRPT.setParentCode(localbodycode);
					if(localbodycode==0)
					{
					Message=reportService.getDistrictPanchayatNomenclatureNameWithStateNamePES(stateCode);
					consolidateReportLBRPT.setState_name_english(Message);
					}
					else
					{	
					Message = reportService.getLocalBodyNameByCodePES(localbodycode);
					consolidateReportLBRPT.setState_name_english(Message);
					}
				}
				if(level=='M') {
					if(consolidateReportLBRPT.getParentLevel()=='V'){
						consolidateReportLBRPT.setOtherInformationFlag(true);
						consolidatePanchaytReportVariable.setOtherInformationFlag(true);
					}
					mav = new ModelAndView("rptConsolidateforPanbyMLevelPES");

					for(ConsolidatePanchaytReportVariable element:stack)
					{
						if(consolidateReportLBRPT.getParentLevel()==element.getLevel())
						{
							flag=true;
							break;
						}
					}
					if(!flag)
					{
						stack.push(consolidatePanchaytReportVariable);
					}
					
					Integer Plblc=consolidatePanchaytReportVariable.getCode();
					if(Plblc==0)
					{
						Message=reportService.getDistrictPanchayatNomenclatureNameWithStateNamePES(stateCode);
						consolidateReportLBRPT.setState_name_english(Message);
					}
					else
					{	
						Message = reportService.getLocalBodyNamePES(localbodycode,Plblc);
						consolidateReportLBRPT.setState_name_english(Message);
					}
					String otherInfotitle="";
					if(Message!=null && Message.indexOf("of")!=-1 && Message.indexOf(")")!=-1)
					{
						otherInfotitle=Message.substring(Message.lastIndexOf("of"),(Message.indexOf(")")+1));
					}
					request.setAttribute("otherInfotitle", otherInfotitle);
					request.setAttribute("DefaultLanguageCode", 1);
					request.setAttribute("stateCode", stateCode);
					//char flag = 'M';
					List<MappedVillageByLBCodeBean> mappedVillageByLBCode = new ArrayList<MappedVillageByLBCodeBean>();
					mappedVillageByLBCode = reportService.getMappedVillageByLBCodePES(localbodycode);
					mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
					model.addAttribute("consolidateLBList", mappedVillageByLBCode);
					return mav;
				}
			} else if (type == 'U') {
				mav = new ModelAndView("rptConsolidatefoUrban");
			}
			//if(localbodycode==0)
			
			//getList = reportService.getLocalBodyListbyLBtypebyState(stateCode, type, level, localbodycode);
			getList = reportService.getLocalBodyListbyLBtypebyFinYearPES(stateCode, type, level, localbodycode, financialYear);
			
			
			httpSession.setAttribute("getList", getList);
			if (type == 'P' || type == 'T') {
				for (GetLocalBodyListbyLBtypebyState list : getList) {
					if (level == 'D') {
						Integer childCount = list.getChildCount();
						if (childCount != null){
							ipCount += childCount;
						Integer grandChildCount = list.getGrandChildCount();
						
						if (grandChildCount != null){
							vpCount += grandChildCount;
						}	
						}
					} else if (level == 'I') {
						Integer iChildCount = list.getChildCount();
						if (iChildCount != null){
							vpCount += iChildCount;
						}	
					}
				}
			}
			
			//mav.addObject("stack", stack); 
			/**
			 * @author Maneesh Kumar
			 * @Since 19/05/2014
			 * @Param include Other information of panchayat by including reports/otherPesAppReport.jsp and set following Parameter. 
			 */
			String typeName="";
			String otherInfotitle="";
			if(Message!=null && Message!="" && Message.indexOf(" ")!=-1){
				typeName=Message.substring(0, Message.indexOf(" ",Message.indexOf(" ")+1));
				if(Message.indexOf("of")!=-1 && Message.indexOf(")")!=-1){
				otherInfotitle=Message.substring(Message.indexOf("of"),(Message.indexOf(")")+1));
				}
			}
			if((level=='I' || level=='V' || level=='D')){
				request.setAttribute("typeName", typeName);
				request.setAttribute("otherInfotitle", otherInfotitle);
				request.setAttribute("DefaultLanguageCode", 1);
				request.setAttribute("stateCode", stateCode);
			}
			
			/***
			 * End 
			 */
			consolidateReportLBRPT.setState_code(stateCode);
			consolidateReportLBRPT.setLbtype(type);
			consolidateReportLBRPT.setIp_count(ipCount);
			consolidateReportLBRPT.setVp_count(vpCount);
			mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
			model.addAttribute("consolidateLBList", getList);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	@RequestMapping(value = "/rptBacktoParentConsolidateReportPES", method = RequestMethod.POST)
	public ModelAndView rptBacktoParentConsolidateReportPES(
			@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB,
			@ModelAttribute("stack") MyStackList<ConsolidatePanchaytReportVariable> stack,
			BindingResult result, Model model, HttpSession httpSession,
			HttpServletRequest request
			) {

		ModelAndView mav = null;
		int ipCount = 0;
		int vpCount = 0;
		String financialYear = consolidateReportForRuralLB.getFinancialYear();
		consolidateReportForRuralLB.setFinancialYear(financialYear);
		try {

			if(!stack.isEmpty())
			{
				
				ConsolidatePanchaytReportVariable consolidatePanchaytReportVariable=stack.pop();
				consolidateReportForRuralLB.setOtherInformationFlag(consolidatePanchaytReportVariable.getOtherInformationFlag());
				if(consolidatePanchaytReportVariable!=null)
				{
					String Message="";
					char level=consolidatePanchaytReportVariable.getLevel();
					Integer stateCode=consolidateReportForRuralLB.getStateId();
					Integer localbodycode=null;
					
					if(level!='D')
					{
						localbodycode=consolidatePanchaytReportVariable.getCode();
					}
					else
					{
						localbodycode=0;
					}
					request.setAttribute("lbCode",localbodycode);
					
					switch(level)
					{
					
					case 'S':
								mav = new ModelAndView("rptConsolidateforRuralLBPES");
								stack =new  MyStackList<ConsolidatePanchaytReportVariable>();		
								List<ConsolidateReportForRuralLB> consolidateLBList = reportService.getConsolidatedRptForRuralLBsPES(financialYear);
								consolidateReportForRuralLB.setParentLevel('S');
								consolidateReportForRuralLB.setParentCode(0);
								httpSession.setAttribute("flag", 'D');
								List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();
								int state_code = -1;
								ConsolidateReportLBRPT consolidateReportLBRPT = null;
								
									for (ConsolidateReportForRuralLB consReporLBObj : consolidateLBList) 
									{
										int count = consReporLBObj.getCount();
										
										if (state_code != consReporLBObj.getStateCode()) 
										{
											if (consolidateReportLBRPT != null) 
											{
											reportList.add(consolidateReportLBRPT);
											}
										
											state_code = consReporLBObj.getStateCode();
										consolidateReportLBRPT = new ConsolidateReportLBRPT();
										consolidateReportLBRPT.setState_code(consReporLBObj.getStateCode());
										consolidateReportLBRPT.setState_name_english(consReporLBObj.getStateNameEnglish());
										
											if (consReporLBObj.getLevel() == 'D')
											{
												consolidateReportLBRPT.setDp_count(count);
											} else if (consReporLBObj.getLevel() == 'I') 
											{
												consolidateReportLBRPT.setIp_count(count);
											} else if (consReporLBObj.getLevel() == 'V') 
											{
												consolidateReportLBRPT.setVp_count(count);
											}
										} else
										{
											if (consReporLBObj.getLevel() == 'D')
											{
											consolidateReportLBRPT.setDp_count(count);
											} else if (consReporLBObj.getLevel() == 'I') 
											{
											consolidateReportLBRPT.setIp_count(count);
											}
											else if (consReporLBObj.getLevel() == 'V')
											{
											consolidateReportLBRPT.setVp_count(count);
											}
									}
								}
								if (consolidateReportLBRPT != null){
									reportList.add(consolidateReportLBRPT);
								}	
								model.addAttribute("get", false);
								model.addAttribute("consolidateLBList", reportList);
								mav.addObject("consolidateReportLBRPT",
										consolidateReportForRuralLB);
								
								break;
								
					
					
					case 'D':
						mav = new ModelAndView("rptConsolidateforPanbyDLevelRuralPES");
						Message=reportService.getDistrictPanchayatNomenclatureNameWithStateNamePES(stateCode);
						consolidateReportForRuralLB.setState_name_english(Message);
						consolidateReportForRuralLB.setParentLevel(level);
						consolidateReportForRuralLB.setParentCode(stateCode);
						break;
					case 'I':
						mav = new ModelAndView("rptConsolidateforPanbyILevelRuralPES");
						if(localbodycode==0)
						{
							Message=reportService.getDistrictPanchayatNomenclatureNameWithStateNamePES(stateCode);
							consolidateReportForRuralLB.setState_name_english(Message);
							consolidateReportForRuralLB.setOtherInformationFlag(false);
						}
						else
						{	
						Message = reportService.getLocalBodyNameByCodePES(localbodycode);
						consolidateReportForRuralLB.setState_name_english(Message);
						}
						consolidateReportForRuralLB.setParentLevel(level);
						consolidateReportForRuralLB.setParentCode(localbodycode);
						
						break;
					case 'V':
						mav = new ModelAndView("rptConsolidateforPanbyVLevelRuralPES");
						if(localbodycode==0)
						{
							Message=reportService.getDistrictPanchayatNomenclatureNameWithStateNamePES(stateCode);
							consolidateReportForRuralLB.setState_name_english(Message);
						}
						else
						{	
						Message = reportService.getLocalBodyNameByCodePES(localbodycode);
						consolidateReportForRuralLB.setState_name_english(Message);
						consolidateReportForRuralLB.setParentLevel(level);
						consolidateReportForRuralLB.setParentCode(localbodycode);
						}
						break;
					
					case 'M':
					mav = new ModelAndView("rptConsolidateforPanbyMLevelPES");
						break;
						
						
					}
					/**
					 * @author Maneesh Kumar
					 * @Since 19/05/2014
					 * @Param include Other information of panchayat by including reports/otherPesAppReport.jsp and set following Parameter. 
					 */
					String typeName="";
					String otherInfotitle="";
					if(Message!=null && Message!="" && Message.indexOf(" ")!=-1){
						typeName=Message.substring(0, Message.indexOf(" ",Message.indexOf(" ")+1));
						if(Message.indexOf("of")!=-1 && Message.indexOf(")")!=-1){
							otherInfotitle=Message.substring(Message.indexOf("of"),(Message.indexOf(")")+1));
							}
					}
					if((level=='I' || level=='V' || level=='D')){
						request.setAttribute("typeName", typeName);
						request.setAttribute("otherInfotitle", otherInfotitle);
						request.setAttribute("DefaultLanguageCode", 1);
						request.setAttribute("stateCode", stateCode);
					}
					
					/***
					 * End 
					 */
					
					if(level!='S')
					{
						List<GetLocalBodyListbyLBtypebyState> getList = reportService.getLocalBodyListbyLBtypebyFinYearPES(stateCode, 'P', level, localbodycode, financialYear);
					httpSession.setAttribute("getList", getList);
		
						for (GetLocalBodyListbyLBtypebyState list : getList) {
							if (level == 'D') {
								Integer childCount = list.getChildCount();
								if (childCount != null){
									ipCount += childCount;
								Integer grandChildCount = list.getGrandChildCount();
								if (grandChildCount != null){
									vpCount += grandChildCount;
								}	
								}
							} else if (level == 'I') {
								Integer iChildCount = list.getChildCount();
								if (iChildCount != null){
									vpCount += iChildCount;
								}	
							}
						}
				
						consolidateReportForRuralLB.setState_code(stateCode);
						consolidateReportForRuralLB.setLbtype('P');
						consolidateReportForRuralLB.setIp_count(ipCount);
						consolidateReportForRuralLB.setVp_count(vpCount);
					mav.addObject("consolidateReportLBRPT", consolidateReportForRuralLB);
					model.addAttribute("consolidateLBList", getList);
					}
					
					
				}
				
			}
			else
			{
				mav = new ModelAndView("rptConsolidateforRuralLBPES");
				stack =new  MyStackList<ConsolidatePanchaytReportVariable>();		
				List<ConsolidateReportForRuralLB> consolidateLBList = reportService.getConsolidatedRptForRuralLBsPES(financialYear);
				consolidateReportForRuralLB.setParentLevel('S');
				consolidateReportForRuralLB.setParentCode(0);
				httpSession.setAttribute("flag", 'D');
				List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();
				int state_code = -1;
				ConsolidateReportLBRPT consolidateReportLBRPT = null;
				
					for (ConsolidateReportForRuralLB consReporLBObj : consolidateLBList) 
					{
						int count = consReporLBObj.getCount();
						
						if (state_code != consReporLBObj.getStateCode()) 
						{
							if (consolidateReportLBRPT != null) 
							{
							reportList.add(consolidateReportLBRPT);
							}
						
							state_code = consReporLBObj.getStateCode();
						consolidateReportLBRPT = new ConsolidateReportLBRPT();
						consolidateReportLBRPT.setState_code(consReporLBObj.getStateCode());
						consolidateReportLBRPT.setState_name_english(consReporLBObj.getStateNameEnglish());
						
							if (consReporLBObj.getLevel() == 'D')
							{
								consolidateReportLBRPT.setDp_count(count);
							} else if (consReporLBObj.getLevel() == 'I') 
							{
								consolidateReportLBRPT.setIp_count(count);
							} else if (consReporLBObj.getLevel() == 'V') 
							{
								consolidateReportLBRPT.setVp_count(count);
							}
						} else
						{
							if (consReporLBObj.getLevel() == 'D')
							{
							consolidateReportLBRPT.setDp_count(count);
							} else if (consReporLBObj.getLevel() == 'I') 
							{
							consolidateReportLBRPT.setIp_count(count);
							}
							else if (consReporLBObj.getLevel() == 'V')
							{
							consolidateReportLBRPT.setVp_count(count);
							}
					}
					}
				if (consolidateReportLBRPT != null){
					reportList.add(consolidateReportLBRPT);
				}	
				model.addAttribute("get", false);
				model.addAttribute("consolidateLBList", reportList);
				mav.addObject("consolidateReportLBRPT",
						consolidateReportForRuralLB);
				
			}
			return mav;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}
	
	
	@RequestMapping(value = "/rptBacktoParentConsolidateReport", method = RequestMethod.POST)
	public ModelAndView rptBacktoParentConsolidateReport(
			@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB,
			@ModelAttribute("stack") MyStackList<ConsolidatePanchaytReportVariable> stack,
			BindingResult result, Model model, HttpSession httpSession,
			HttpServletRequest request
			) {

		ModelAndView mav = null;
		int ipCount = 0;
		int vpCount = 0;
		String financialYear = consolidateReportForRuralLB.getFinancialYear();
		consolidateReportForRuralLB.setFinancialYear(financialYear);
		try {

			if(!stack.isEmpty())
			{
				
				ConsolidatePanchaytReportVariable consolidatePanchaytReportVariable=stack.pop();
				consolidateReportForRuralLB.setOtherInformationFlag(consolidatePanchaytReportVariable.getOtherInformationFlag());
				if(consolidatePanchaytReportVariable!=null)
				{
					String Message="";
					char level=consolidatePanchaytReportVariable.getLevel();
					Integer stateCode=consolidateReportForRuralLB.getStateId();
					Integer localbodycode=null;
					
					if(level!='D')
					{
						localbodycode=consolidatePanchaytReportVariable.getCode();
					}
					else
					{
						localbodycode=0;
					}
					request.setAttribute("lbCode",localbodycode);
					
					switch(level)
					{
					
					case 'S':
								mav = new ModelAndView("rptConsolidateforRuralLB");
								stack =new  MyStackList<ConsolidatePanchaytReportVariable>();		
								List<ConsolidateReportForRuralLB> consolidateLBList = reportService.getConsolidatedRptForRuralLBs(financialYear);
								consolidateReportForRuralLB.setParentLevel('S');
								consolidateReportForRuralLB.setParentCode(0);
								httpSession.setAttribute("flag", 'D');
								List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();
								int state_code = -1;
								ConsolidateReportLBRPT consolidateReportLBRPT = null;
								
									for (ConsolidateReportForRuralLB consReporLBObj : consolidateLBList) 
									{
										int count = consReporLBObj.getCount();
										
										if (state_code != consReporLBObj.getStateCode()) 
										{
											if (consolidateReportLBRPT != null) 
											{
											reportList.add(consolidateReportLBRPT);
											}
										
											state_code = consReporLBObj.getStateCode();
										consolidateReportLBRPT = new ConsolidateReportLBRPT();
										consolidateReportLBRPT.setState_code(consReporLBObj.getStateCode());
										consolidateReportLBRPT.setState_name_english(consReporLBObj.getStateNameEnglish());
										
											if (consReporLBObj.getLevel() == 'D')
											{
												consolidateReportLBRPT.setDp_count(count);
											} else if (consReporLBObj.getLevel() == 'I') 
											{
												consolidateReportLBRPT.setIp_count(count);
											} else if (consReporLBObj.getLevel() == 'V') 
											{
												consolidateReportLBRPT.setVp_count(count);
											}
										} else
										{
											if (consReporLBObj.getLevel() == 'D')
											{
											consolidateReportLBRPT.setDp_count(count);
											} else if (consReporLBObj.getLevel() == 'I') 
											{
											consolidateReportLBRPT.setIp_count(count);
											}
											else if (consReporLBObj.getLevel() == 'V')
											{
											consolidateReportLBRPT.setVp_count(count);
											}
									}
								}
								if (consolidateReportLBRPT != null){
									reportList.add(consolidateReportLBRPT);
								}	
								model.addAttribute("get", false);
								model.addAttribute("consolidateLBList", reportList);
								mav.addObject("consolidateReportLBRPT",
										consolidateReportForRuralLB);
								
								break;
								
					
					
					case 'D':
						mav = new ModelAndView("rptConsolidateforPanbyDLevelRural");
						Message=reportService.getDistrictPanchayatNomenclatureNameWithStateName(stateCode);
						consolidateReportForRuralLB.setState_name_english(Message);
						consolidateReportForRuralLB.setParentLevel(level);
						consolidateReportForRuralLB.setParentCode(stateCode);
						break;
					case 'I':
						mav = new ModelAndView("rptConsolidateforPanbyILevelRural");
						if(localbodycode==0)
						{
							Message=reportService.getDistrictPanchayatNomenclatureNameWithStateName(stateCode);
							consolidateReportForRuralLB.setState_name_english(Message);
							consolidateReportForRuralLB.setOtherInformationFlag(false);
						}
						else
						{	
						Message = reportService.getLocalBodyNameByCode(localbodycode);
						consolidateReportForRuralLB.setState_name_english(Message);
						}
						consolidateReportForRuralLB.setParentLevel(level);
						consolidateReportForRuralLB.setParentCode(localbodycode);
						
						break;
					case 'V':
						mav = new ModelAndView("rptConsolidateforPanbyVLevelRural");
						if(localbodycode==0)
						{
							Message=reportService.getDistrictPanchayatNomenclatureNameWithStateName(stateCode);
							consolidateReportForRuralLB.setState_name_english(Message);
						}
						else
						{	
						Message = reportService.getLocalBodyNameByCode(localbodycode);
						consolidateReportForRuralLB.setState_name_english(Message);
						consolidateReportForRuralLB.setParentLevel(level);
						consolidateReportForRuralLB.setParentCode(localbodycode);
						}
						break;
					
					case 'M':
					mav = new ModelAndView("rptConsolidateforPanbyMLevel");
						break;
						
						
					}
					/**
					 * @author Maneesh Kumar
					 * @Since 19/05/2014
					 * @Param include Other information of panchayat by including reports/otherPesAppReport.jsp and set following Parameter. 
					 */
					String typeName="";
					String otherInfotitle="";
					if(Message!=null && Message!="" && Message.indexOf(" ")!=-1){
						typeName=Message.substring(0, Message.indexOf(" ",Message.indexOf(" ")+1));
						if(Message.indexOf("of")!=-1 && Message.indexOf(")")!=-1){
							otherInfotitle=Message.substring(Message.indexOf("of"),(Message.indexOf(")")+1));
							}
					}
					if((level=='I' || level=='V' || level=='D')){
						request.setAttribute("typeName", typeName);
						request.setAttribute("otherInfotitle", otherInfotitle);
						request.setAttribute("DefaultLanguageCode", 1);
						request.setAttribute("stateCode", stateCode);
					}
					
					/***
					 * End 
					 */
					
					if(level!='S')
					{
						List<GetLocalBodyListbyLBtypebyState> getList = reportService.getLocalBodyListbyLBtypebyFinYear(stateCode, 'P', level, localbodycode, financialYear);
					httpSession.setAttribute("getList", getList);
		
						for (GetLocalBodyListbyLBtypebyState list : getList) {
							if (level == 'D') {
								Integer childCount = list.getChildCount();
								if (childCount != null){
									ipCount += childCount;
								Integer grandChildCount = list.getGrandChildCount();
								if (grandChildCount != null){
									vpCount += grandChildCount;
								}	
								}
							} else if (level == 'I') {
								Integer iChildCount = list.getChildCount();
								if (iChildCount != null){
									vpCount += iChildCount;
								}	
							}
						}
				
						consolidateReportForRuralLB.setState_code(stateCode);
						consolidateReportForRuralLB.setLbtype('P');
						consolidateReportForRuralLB.setIp_count(ipCount);
						consolidateReportForRuralLB.setVp_count(vpCount);
					mav.addObject("consolidateReportLBRPT", consolidateReportForRuralLB);
					model.addAttribute("consolidateLBList", getList);
					}
					
					
				}
				
			}
			else
			{
				mav = new ModelAndView("rptConsolidateforRuralLB");
				stack =new  MyStackList<ConsolidatePanchaytReportVariable>();		
				List<ConsolidateReportForRuralLB> consolidateLBList = reportService.getConsolidatedRptForRuralLBs(financialYear);
				consolidateReportForRuralLB.setParentLevel('S');
				consolidateReportForRuralLB.setParentCode(0);
				httpSession.setAttribute("flag", 'D');
				List<ConsolidateReportLBRPT> reportList = new ArrayList<ConsolidateReportLBRPT>();
				int state_code = -1;
				ConsolidateReportLBRPT consolidateReportLBRPT = null;
				
					for (ConsolidateReportForRuralLB consReporLBObj : consolidateLBList) 
					{
						int count = consReporLBObj.getCount();
						
						if (state_code != consReporLBObj.getStateCode()) 
						{
							if (consolidateReportLBRPT != null) 
							{
							reportList.add(consolidateReportLBRPT);
							}
						
							state_code = consReporLBObj.getStateCode();
						consolidateReportLBRPT = new ConsolidateReportLBRPT();
						consolidateReportLBRPT.setState_code(consReporLBObj.getStateCode());
						consolidateReportLBRPT.setState_name_english(consReporLBObj.getStateNameEnglish());
						
							if (consReporLBObj.getLevel() == 'D')
							{
								consolidateReportLBRPT.setDp_count(count);
							} else if (consReporLBObj.getLevel() == 'I') 
							{
								consolidateReportLBRPT.setIp_count(count);
							} else if (consReporLBObj.getLevel() == 'V') 
							{
								consolidateReportLBRPT.setVp_count(count);
							}
						} else
						{
							if (consReporLBObj.getLevel() == 'D')
							{
							consolidateReportLBRPT.setDp_count(count);
							} else if (consReporLBObj.getLevel() == 'I') 
							{
							consolidateReportLBRPT.setIp_count(count);
							}
							else if (consReporLBObj.getLevel() == 'V')
							{
							consolidateReportLBRPT.setVp_count(count);
							}
					}
					}
				if (consolidateReportLBRPT != null){
					reportList.add(consolidateReportLBRPT);
				}	
				model.addAttribute("get", false);
				model.addAttribute("consolidateLBList", reportList);
				mav.addObject("consolidateReportLBRPT",
						consolidateReportForRuralLB);
				
			}
			return mav;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

	}
	
	
	
	
	@RequestMapping(value = "/unmapLBWardforPCAC", method = RequestMethod.GET)
	public ModelAndView unmapLBWardforPCAC(
			@ModelAttribute("statewiseUnmappedVillagesForm") VillageDataForm statewiseUnmappedVillagesForm,
			Model model, HttpServletRequest request, HttpSession session) {
			ModelAndView mav = null;
			List<State> stateList = null;
			stateList = new ArrayList<State>();
			try {
					stateList = stateService.getStateSourceList();
			if (stateList.size() > 0) {
				mav = new ModelAndView("UnmappedLB_PCACWardmapping");
				model.addAttribute("stateSourceList", stateList);
				model.addAttribute("statewiseUnmappedVillagesForm",statewiseUnmappedVillagesForm);

			} else {

				mav = new ModelAndView("errorConfigshow");
				request.setAttribute("message", "state not found in list");

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	@RequestMapping(value = "/unmapLBWardforPCACPOST", method = RequestMethod.POST)
	public ModelAndView unmapLBWardforPCACPOST(
			@ModelAttribute("statewiseUnmappedVillagesForm") VillageDataForm statewiseUnmappedVillagesForm,
			Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		List<State> stateList = null;
		List<UnmappedLbPcAcWard> stateWiseGPtoVillageMappingList = null;
		stateWiseGPtoVillageMappingList = new ArrayList<UnmappedLbPcAcWard>();
		Integer stateCode = null;
		stateList = new ArrayList<State>();
		boolean messageFlag = false;
		try {

			stateList = stateService.getStateSourceList();

			mav = new ModelAndView("UnmappedLB_PCACWardmappingResult");
			model.addAttribute("stateSourceList", stateList);
			model.addAttribute("statewiseUnmappedVillagesForm",
					statewiseUnmappedVillagesForm);
			String captchaAnswer = statewiseUnmappedVillagesForm
					.getCaptchaAnswers();
			CaptchaValidator captchaValidator = new CaptchaValidator();

			if (captchaAnswer != null){
				messageFlag = captchaValidator.validateCaptcha(httpSession,
						captchaAnswer);
			}	

			statewiseUnmappedVillagesForm.setCaptchaAnswers(null);

			if (messageFlag == true) {
				if (statewiseUnmappedVillagesForm.getStateNameEnglish() != null){
					stateCode = Integer.parseInt(statewiseUnmappedVillagesForm
							.getStateNameEnglish());
				}	
				if (stateCode != null) {

					stateWiseGPtoVillageMappingList = reportService
							.getunmappedLbPcAcWard(stateCode);
					model.addAttribute("StatewiseGPtoVillageMappingList",
							stateWiseGPtoVillageMappingList);
									return mav;
				} else {
					mav = new ModelAndView("errorConfigshow");
					request.setAttribute("message", "Problem to reading data");
				}
			}

			else {
				
				model.addAttribute("captchaSuccess2", messageFlag);
			}

			statewiseUnmappedVillagesForm.setStateNameEnglish(null);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}


	@RequestMapping(value = "/lbWiseWardforCitizen", method = RequestMethod.GET)
	public ModelAndView displayWardReport(Model model, HttpSession httpSession, HttpServletRequest request) {
		
		try {
			model.addAttribute("enableSearch",true);
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			model.addAttribute("lbTypeWiseWards", new LocalbodyWard());
			return new ModelAndView("lb_wise_ward_details");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
	}
		
	@RequestMapping(value = "/lbTypeWiseWardsData", method = RequestMethod.POST)
	public ModelAndView wardsPerLBType(Model model, 
									   HttpSession httpSession, 
									   HttpServletRequest request,
									   @RequestParam(value="state") Integer state,
									   @RequestParam(value="captchaAnswer") String captchaAnswer,
									   Boolean isBack) {
		try {
			ModelAndView modelview = new ModelAndView("lb_wise_ward_details");
			model.addAttribute("lbTypeWiseWards", new LocalbodyWard());
			model.addAttribute("stateSourceList", stateService.getStateSourceList());
			model.addAttribute("state", state);
			if(state == null || state == -1){
				model.addAttribute("enableSearch",true);
				model.addAttribute("errorMsgState", "Invalid State, Please select a valid state.");
				return modelview;
			}
			
			if(isBack == null){
				CaptchaValidator captchaValidator = new CaptchaValidator();
		      	boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
				if(!messageFlag){
					model.addAttribute("enableSearch",true);
					model.addAttribute("invalidCaptcha", true);
					return modelview;
				}
			}
			
			model.addAttribute("enableLBTypeWise",true);
			model.addAttribute("listLBTypeWiseWards", reportService.getWardsPerLBType(state));
			return modelview;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
	}
	
	@RequestMapping(value = "/localBodyWiseWards", method = RequestMethod.POST)
	public ModelAndView wardsPerLocalBody(Model model, 
									      HttpSession httpSession, 
									      HttpServletRequest request,
									      @RequestParam(value="state") Integer state,
									      @RequestParam(value="lbTypeCode") Integer lbTypeCode) {
		try {
			Object[] obj = reportService.lbWardIsParentAndCount(state, lbTypeCode);
			Boolean parent = (Boolean) obj[0];
			Boolean gparent = (Boolean) obj[1];
			model.addAttribute("state", state);
			model.addAttribute("lbTypeCode", lbTypeCode);
			model.addAttribute("showParent", parent);
			model.addAttribute("showGrandParent", gparent);
			return new ModelAndView("per_lb_wise_ward_details");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
	}
	
	
	@RequestMapping(value = "/fetchDataTableValues", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody void /*String*/ getMapURL(HttpSession httpSession, 
										  HttpServletRequest request,
										  HttpServletResponse response,
										  @RequestParam("state") Integer state,
										  @RequestParam("lbTypeCode") Integer lbTypeCode) {
		JsonObject jsonResponse = new JsonObject();		
		try{
			JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);
			jsonResponse.addProperty("sEcho", param.sEcho);
			BigInteger totalListRecords = reportService.countWardsPerLBObjects(state, lbTypeCode, param.sSearch);
			jsonResponse.addProperty("iTotalRecords", totalListRecords);
			jsonResponse.addProperty("iTotalDisplayRecords", totalListRecords);
			List<LocalBodyWiseWards> wardsPerLB = reportService.getWardsPerLBObjects(state, lbTypeCode, param.iDisplayLength, param.iDisplayStart, param.sSearch);
			
			jsonResponse.add("aaData", new Gson().toJsonTree(wardsPerLB));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonResponse.toString()); 
		}catch (Exception e) {
			e.printStackTrace();
			List<LocalBodyWiseWards> noresult = new ArrayList<LocalBodyWiseWards>();
			jsonResponse.addProperty("iTotalDisplayRecords", noresult.size());
			jsonResponse.add("aaData", new Gson().toJsonTree(noresult));
			log.error("Data Table Error : ", e);
		}
		return; //jsonResponse.toString();
	}
	
	@RequestMapping(value = "/showWardDetails", method = RequestMethod.POST)
	public ModelAndView showWardDetails(Model model, 
									   HttpSession httpSession, 
									   HttpServletRequest request,
									   @RequestParam(value="lblcCode") Integer lblcCode,
									   @RequestParam(value="state") Integer state,
									   @RequestParam(value="lbTypeCode") Integer lbTypeCode,
									   @RequestParam(value="localBodyCode") Integer localBodyCode) {
		try {
			ModelAndView modelview = new ModelAndView("show_ward_details");
			model.addAttribute("lisWardDetails", reportService.getWardsDetails(lblcCode));
			model.addAttribute("state", state);
			model.addAttribute("lbTypeCode", lbTypeCode);
			model.addAttribute("lbTypeHierarchy", reportService.getLBNameByTypeCode(localBodyCode));
			return modelview;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
	}
	
	@RequestMapping(value = "/globalWardBack", method = RequestMethod.POST)
	public ModelAndView showWardDetails(Model model, 
										HttpSession httpSession,
										HttpServletRequest request,
										@RequestParam(value="state") Integer state,
										@RequestParam(value="lbTypeCode") Integer lbTypeCode,
										@RequestParam(value="flow") String flow) {
		try {
			if("WARD_DETAILS".equalsIgnoreCase(flow)){
				//back event for ward details form
				return wardsPerLocalBody(model, httpSession, request, state, lbTypeCode);
			}else if("LB_WARD_DETAILS".equalsIgnoreCase(flow)){
				return wardsPerLBType(model, httpSession, request, state, null, true);
			}
			
			return null;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return new ModelAndView(redirectPath);
		}
	}
	/**
	 * @author Yetendra
	 * @param ReportForMappedGPAndWardsToPCAC
	 * @param model
	 * @param request
	 * @param httpSession
	 * @input constituency_code and constituency_type 'P'or'A'
	 * @return the list of MappedGPAndWardsToPCAC
	 */
	@RequestMapping(value = "/rptMappedGPNWardforPCAC", method = RequestMethod.GET)
	public ModelAndView reportMappedLocalBodyAndWardsToPCAC(@ModelAttribute("rptMappedGPNWardforPCACForm") MappedLBWARDTOPCAC rptMappedGPNWardforPCACForm, Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView("rptMappedGPNWardforPCAC");
		List<State> stateList = null;
		try {

			stateList = stateService.getStateSourceList();
			if (!stateList.isEmpty()) {

				model.addAttribute("stateSourceList", stateList);
				model.addAttribute("rptMappedGPNWardforPCACForm", rptMappedGPNWardforPCACForm);
			} else {

				mav = new ModelAndView("errorConfigshow");
				request.setAttribute("message", "state not found in list");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/rptMappedGPNWardforPCAC", method = RequestMethod.POST)
	public ModelAndView rptMappedGPNWardforPCACPOST(@ModelAttribute("rptMappedGPNWardforPCACForm") MappedLBWARDTOPCAC rptMappedGPNWardforPCACForm, Model model, HttpServletRequest request, HttpSession httpSession) {
			
		ModelAndView  mav = new ModelAndView("rptMappedGPNWardforPCAC");
		boolean messageFlagac=false,messageFlagpc=false;
			try {
				String captchaAnswer = rptMappedGPNWardforPCACForm.getCaptchaAnswer();
				String captchaAnswers = rptMappedGPNWardforPCACForm.getCaptchaAnswers();
				CaptchaValidator captchaValidator = new CaptchaValidator();
				char type='P';
				String entityCode=null;
				Integer index=null;
				
				if (!"".equals(captchaAnswer)){
					messageFlagpc = captchaValidator.validateCaptcha(httpSession,captchaAnswer);
				}	
				if (!"".equals(captchaAnswers)){
					messageFlagac = captchaValidator.validateCaptcha(httpSession,captchaAnswers);
					rptMappedGPNWardforPCACForm.setCaptchaAnswers(null);
				}	
				if (messageFlagac == true || messageFlagpc==true) {
					if("".equals(rptMappedGPNWardforPCACForm.getAcNameEnglish())) {
						
						 entityCode=rptMappedGPNWardforPCACForm.getParlimentNameEnglish();
						 index=entityCode.indexOf(',');
						 	if(index>=0) {
						 		entityCode=entityCode.substring(0,index);
						}
						type='P';
					}
					else {
							entityCode=rptMappedGPNWardforPCACForm.getAcNameEnglish();
							index=entityCode.indexOf(',');
								if(index>=0) {
									entityCode=entityCode.substring(0,index);
						}
							type='A';
					}
					List<PCACMappingforLBWard> pcACMappingforLBWardList=reportService.getPCACMappingforLBWardDetails(Integer.parseInt(entityCode),type);
					if(pcACMappingforLBWardList.isEmpty() && type == 'P' ) {
						 model.addAttribute("flag3",1);
						List<State> stateList =stateService.getStateSourceList();
						model.addAttribute("rptMappedGPNWardforPCACForm",rptMappedGPNWardforPCACForm);
						model.addAttribute("stateSourceList", stateList);
					}
					
					if(pcACMappingforLBWardList.isEmpty() && type == 'A' ) {
						 model.addAttribute("flag2",1);
						List<State> stateList =stateService.getStateSourceList();
						model.addAttribute("rptMappedGPNWardforPCACForm",rptMappedGPNWardforPCACForm);
						model.addAttribute("stateSourceList", stateList);
					}
					if(!pcACMappingforLBWardList.isEmpty()) {
						 model.addAttribute("flag1",0);
						mav.addObject("pcACMappingforLBWardList",pcACMappingforLBWardList);
						return mav;
					}
					else {
						List<State> stateList =stateService.getStateSourceList();
						model.addAttribute("rptMappedGPNWardforPCACForm",rptMappedGPNWardforPCACForm);
						model.addAttribute("stateSourceList", stateList);
					}
					
				}
					else {
							List<State> stateList =stateService.getStateSourceList();
							model.addAttribute("rptMappedGPNWardforPCACForm",rptMappedGPNWardforPCACForm);
							model.addAttribute("stateSourceList", stateList);
							mav.addObject("flag1", 1);
							model.addAttribute("captchaSuccess1", messageFlagac);
							model.addAttribute("captchaSuccess2", messageFlagpc);
							model.addAttribute("get", true);
						}
		} 
			catch (Exception e) {
					IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
					String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
					mav = new ModelAndView(redirectPath);
					return mav;
				}
			return mav;
	}
	
	/**
	 * @author Yetendra
	 * @param reportOnViewUnMappedLocalBodies
	 * @param model
	 * @param request
	 * @param httpSession
	 * @RequestParam(Value = "Select State") int StateCode
	 * @RequestParam(Value = "LocalBodyType") int LocalBodyTypeCode
	 * @return "viewUnMappedLBlist" list type 
	 */
	@RequestMapping(value = "/rptViewUnmappedLocalBodies", method = RequestMethod.GET)
	public ModelAndView reportOnViewUnmappedLocalBodies(@ModelAttribute("rptViewUnMappedLocalBodies") LocalGovtBodyForm rptViewUnMappedLocalBodies,  Model model, HttpServletRequest request, HttpSession httpSession) {
		
		ModelAndView mav = new ModelAndView("rptViewUnmappedLocalBodies");
		List<State> stateList = null;
		try {
				stateList = stateService.getStateSourceList();
				if (!stateList.isEmpty()) {
						
					model.addAttribute("stateSourceList", stateList);
					model.addAttribute("rptViewUnMappedLocalBodies", rptViewUnMappedLocalBodies);
					
				} else {
						
					mav = new ModelAndView("errorConfigshow");
					request.setAttribute("message", "state not found in list");
				}
		} catch (Exception e) {
				
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/rptViewUnmappedLocalBodies", method = RequestMethod.POST)
	public ModelAndView reportOnViewUnmappedLocalBodiesPOST(@ModelAttribute("rptViewUnMappedLocalBodies") LocalGovtBodyForm rptViewUnMappedLocalBodies,  Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("rptViewUnmappedLocalBodies");
		boolean messageFlag=false;
		List<State> stateList  = new ArrayList<State>();
		String stateName =null;
		try {
			String captchaAnswer = rptViewUnMappedLocalBodies.getCaptchaAnswer();
            CaptchaValidator captchaValidator = new CaptchaValidator();
            if (!"".equals(captchaAnswer)){
				messageFlag = captchaValidator.validateCaptcha(httpSession,captchaAnswer);
            	rptViewUnMappedLocalBodies.setCaptchaAnswer(null);
            }	
			if (messageFlag == true ) {
				
				 String stateCode=rptViewUnMappedLocalBodies.getStateNameEnglish();
				 String lbdetails=rptViewUnMappedLocalBodies.getLgd_LBTypeName();
				 Integer lbTypeCode=null;
				 stateList = stateService.getStateSourceList(Integer.parseInt(stateCode));
				 if(stateList.size()>0){
					 stateName =stateList.get(0).getStateNameEnglish();
				 }	 
				 if(!"".equals(stateCode) && !"".equals(lbdetails))
				 {
					 String lbdetailsArray[]=lbdetails.split(":");
					 lbTypeCode=Integer.parseInt(lbdetailsArray[0]);
										 
					 List<ViewUnMappedLocalBodies> viewUnMappedLBlist = reportService.getViewUnMappedLBDetails(Integer.parseInt(stateCode),lbTypeCode);
					 if(viewUnMappedLBlist.isEmpty()) {
						 model.addAttribute("flag2",1);
						 
						    stateList =stateService.getStateSourceList();
							model.addAttribute("rptViewUnMappedLocalBodies",rptViewUnMappedLocalBodies);
							model.addAttribute("stateSourceList", stateList);
					}
					 
					 if(!viewUnMappedLBlist.isEmpty()) {
						 model.addAttribute("flag1",0);
						mav.addObject("viewUnMappedLBlist",viewUnMappedLBlist);
						mav.addObject("stateNameEng",stateName);
						return mav;
					} else {
							
						 stateList = stateService.getStateSourceList();
						rptViewUnMappedLocalBodies.setStateNameEnglish(null);
						model.addAttribute("rptViewUnMappedLocalBodies",rptViewUnMappedLocalBodies);
						model.addAttribute("stateSourceList", stateList);
					}
				 }
					
			}
			else
			{
				   stateList = stateService.getStateSourceList();
					rptViewUnMappedLocalBodies.setStateNameEnglish(null);	
					model.addAttribute("stateSourceList", stateList);
					model.addAttribute("rptViewUnMappedLocalBodies", rptViewUnMappedLocalBodies);
					model.addAttribute("stateSourceList", stateList);
					mav.addObject("flag1", 1);
					model.addAttribute("captchaSuccess1", messageFlag);
					
				
			}
		return mav;
		} catch (Exception e) {
			
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
  }
	@RequestMapping(value = "/districtWiseLBReport", method = RequestMethod.GET)
	public ModelAndView districtWiseLBReport( @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			HttpServletRequest request, Model model,HttpSession httpSession) {
		ModelAndView mav = null;

		try {

			List<State> statelist = new ArrayList<State>();
			mav = new ModelAndView("rptDistrictWiseLBReport");
			statelist = villageReportService.getConsolidatedRptForVillage();
			model.addAttribute("statelist", statelist);
			model.addAttribute("consolidateReport",
					new ConsolidateReportLBRPT());

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/districtWiseLocalBodyReport", method = RequestMethod.POST)
	public ModelAndView districtWiseLocalBodyReport(
			@ModelAttribute("consolidateReport") ConsolidateReportLBRPT consolidateReport,
			BindingResult result, Model model, HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = null;
		try {
		
			String captchaAnswer = consolidateReport.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session,
					captchaAnswer);
			if (messageFlag) {
				mav = new ModelAndView("rptDistrictWiseLBReportDetail");
							comboFillingService.getComboValuesforApp('D', "S", Integer.parseInt(consolidateReport.getState_name_english()),	Integer.parseInt(consolidateReport.getDistrictPName()));

				//Integer sCode = Integer.parseInt(request.getParameter("stateCode"));
				Integer disCode = Integer.parseInt(request
						.getParameter("distCode"));
				List<DistrictWiseLBReportBean> reportList = new ArrayList<DistrictWiseLBReportBean>();
				reportList = districtService.DistrictWiseLBReportDetail(disCode);
				if (reportList.isEmpty()) {
					String saveMessage = "No Local Body Record Found";
					model.addAttribute("saveMsg", saveMessage);
				}
				model.addAttribute("reportList", reportList);
				if(reportList.size()==0){
					mav.addObject("recordlength",0);
				}	
			} 
			else {
				mav = new ModelAndView("rptDistrictWiseLBReport");
				List<State> statelist = new ArrayList<State>();
				statelist = villageReportService.getConsolidatedRptForVillage();
				model.addAttribute("statelist", statelist);
				model.addAttribute("captchaSuccess2", messageFlag);
				
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;

	}
	
	@RequestMapping(value = "/rptDistrictWiseInvalidatedVillage", method = RequestMethod.GET)
	public ModelAndView rptDistrictWiseInvalidatedVillage(
			@ModelAttribute("districtInvalidate") ConsolidateReportLBRPT districtInvalidate,
			HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mav = null;

		try {

			List<State> statelist = new ArrayList<State>();
			mav = new ModelAndView("rptDistrictWiseInvalidatedVillage");
			statelist = villageReportService.getConsolidatedRptForVillage();
			model.addAttribute("statelist", statelist);
			

		} catch (Exception e) {
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/rptDistrictWiseInvalidatedVillageDetail", method = RequestMethod.POST)
	public ModelAndView rptDistrictWiseInvalidatedVillageDetail(
			@ModelAttribute("districtInvalidate") ConsolidateReportLBRPT districtInvalidate,
			BindingResult result, Model model, HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("districtInvalidate");
		/*List<State> statelist = new ArrayList<State>();*/
		String districtCode = districtInvalidate.getDistrictPName();
		String stateName=request.getParameter("stateName");
		String districtName=request.getParameter("districtName");
		String message="";
		if(stateName!=null && districtName!=null)
		{
			message+=", "+districtName.trim()+", "+stateName.trim();
		}
		try {
			mav = new ModelAndView(
					"rptDistrictWiseInvalidatedVillageDetail");
			String captchaAnswer = districtInvalidate.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session,captchaAnswer);
			if (messageFlag== true) {

				List<DistrictWiseInvalidatedVillage> invalidatetList = new ArrayList<DistrictWiseInvalidatedVillage>();
				invalidatetList = reportService.getDistrictWiseInvalidatedVillage(Integer.parseInt(districtCode));

				if (invalidatetList.isEmpty()) {
					model.addAttribute("flag1", 1);
					/*String saveMessage = "No Local Body Record Found";
					model.addAttribute("saveMsg", saveMessage);*/
					mav = new ModelAndView("rptDistrictWiseInvalidatedVillage");
					List<State> statelist = new ArrayList<State>();
					statelist = villageReportService.getConsolidatedRptForVillage();
					model.addAttribute("statelist", statelist);
					model.addAttribute("captchaSuccess2", messageFlag);
				} else {
					
					model.addAttribute("invalidatetList", invalidatetList);
					model.addAttribute("message",message);
				}
			}
			 else {
				mav = new ModelAndView("rptDistrictWiseInvalidatedVillage");
				List<State> statelist = new ArrayList<State>();
				statelist = villageReportService.getConsolidatedRptForVillage();
				model.addAttribute("statelist", statelist);
				model.addAttribute("captchaSuccess2", messageFlag);
				

			}
		} catch (Exception e) {
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);

		}
		return mav;

	}
	
	@RequestMapping("/rptConsolidateLbMappedVillage")
	public ModelAndView showConsolidateLbMappedVillage(@ModelAttribute("consolidateReportLBRPT") ConsolidateReportLBRPT consolidateReportLBRPT, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		/* Added by Sushil on 02-06-2015 */
		if(bindingResult.hasErrors()) {
			log.debug(bindingResult.getFieldError().getDefaultMessage());
			FieldError fieldError = bindingResult.getFieldError("parentLevel");
			if(fieldError != null) {
				consolidateReportLBRPT.setParentLevel('d');
			}
		}
		/*boolean isVillageCouncilBack =consolidateReportLBRPT.getVillageCouncilBack();*/
		String hierarchy = request.getParameter("hierarchylevel");
		String parentLbDetail = null;
		Integer localbodycode = consolidateReportLBRPT.getLocalbodycode();
		ConsolidatePanchaytReportVariable consolidatePanchaytReportVariable=new ConsolidatePanchaytReportVariable();
		consolidatePanchaytReportVariable.setCode(consolidateReportLBRPT.getParentCode());
		consolidatePanchaytReportVariable.setLevel(consolidateReportLBRPT.getParentLevel());
		mav = new ModelAndView("rptConsolidateLBVillageDetails");
		List<MappedVillageByLBCodeBean> mappedVillageByLBCode = new ArrayList<MappedVillageByLBCodeBean>();
		try {
			parentLbDetail = stateDao.getLBHierarchy(consolidateReportLBRPT.getLocalbodycode());
			mappedVillageByLBCode = reportService.getMappedVillageByLBCode(localbodycode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("consolidateReportLBRPT", consolidateReportLBRPT);
		model.addAttribute("consolidateLBList", mappedVillageByLBCode);
		model.addAttribute("parentLbDetail", parentLbDetail);
		model.addAttribute("hierarchylevel",hierarchy);
		return mav;
		
	}
	
	
	@RequestMapping(value="/districtWiseDetailReport",method = RequestMethod.GET)
	public ModelAndView showDistrictWiseDetailReport(@ModelAttribute("districtWiseDetail") DistrictWiseDetail districtWiseDetail,
														Model model,
														HttpServletRequest request){
		ModelAndView mav = new ModelAndView("districtWiseDetailReprot");
		try{
			model.addAttribute("stateList",stateService.getStateSourceList());
			mav.addObject("districtWiseDetail",districtWiseDetail);
			
			return mav;
		}catch (Exception e) {
		IExceptionHandler expHandler = ExceptionHandlerFactory
				.getInstance().create();
		String redirectPath = expHandler.handleSaveRedirectException(
				request, "", "label.lgd", 1, e);
		mav = new ModelAndView(redirectPath);
		return mav;}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/districtWiseDetailReport",method = RequestMethod.POST)
	public ModelAndView showDistrictWiseDetailReportPost(@ModelAttribute("districtWiseDetail") DistrictWiseDetail districtWiseDetail,
														BindingResult result,
														Model model,
														HttpServletRequest request,
														HttpSession session){
		ModelAndView mav = new ModelAndView("districtWiseDetailReprot");
		List<DistrictWiseDetail> districtWiseDetailList=null;
		try{
			String captchaAnswer = districtWiseDetail.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session,captchaAnswer);
			if (messageFlag== true) {
				Integer districtCode=districtWiseDetail.getSelectDistrictCode();
				Integer stateCode=districtWiseDetail.getSelectStateCode();
				if(districtCode!=null && stateCode!=null){
					Object[] objectArray=reportService.getDistrictWiseDetails(stateCode,districtCode);
					if(objectArray!=null ){
						districtWiseDetailList=(List<DistrictWiseDetail>)objectArray[0];
						StringBuilder message=new StringBuilder(" Detail Report of ");
						message.append(objectArray[2].toString()+" District(");
						message.append(objectArray[1].toString()+")");
						model.addAttribute("message" ,message);
						model.addAttribute("dataExist", true);
						model.addAttribute("resultList", districtWiseDetailList);
					}else{
						model.addAttribute("dataExist", false);
					}
				}
			}else{
				model.addAttribute("captchaSuccess2", messageFlag);
				model.addAttribute("stateList",stateService.getStateSourceList());
				districtWiseDetail.setCaptchaAnswer(null);
				districtWiseDetail.setSelectStateCode(null);
				
			}
		return mav;
		}catch (Exception e) {
			e.printStackTrace();
		IExceptionHandler expHandler = ExceptionHandlerFactory
				.getInstance().create();
		String redirectPath = expHandler.handleSaveRedirectException(
				request, "", "label.lgd", 1, e);
		mav = new ModelAndView(redirectPath);
		return mav;}
		
	}
	
	
	@RequestMapping("/rptConsolidateforPanbyLevelRuralExternal")
	public ModelAndView showConsolidateforPanbyLevelRuralForExternalUrl(@RequestParam("isState") String isState,
																		@RequestParam("code") Integer code,
																		Model model, 
																		HttpServletRequest request, 
																		HttpSession httpSession) {
		ModelAndView mav = null;
		Character type='P';
		Character level=null;
		Integer ipCount = 0;
		Integer vpCount = 0;
		String Message="";
		Integer stateCode=null;
		Integer lbCode=null;
		Boolean isStateFlag=false;
		Boolean isLocalbody=false;
    try{
		
    	if(isState!=null && "Y".equalsIgnoreCase(isState.trim())){
    		stateCode=code;
    		lbCode=0;
    		level='D';
    		isStateFlag=stateService.checkStateExist(stateCode);
   		   Message=reportService.getDistrictPanchayatNomenclatureNameWithStateName(stateCode);
    	}else if(isState!=null && "N".equalsIgnoreCase(isState.trim())){
    		lbCode=code;
    		List<LocalBodyDetails> localBodyDetailsList=localGovtBodyService.getGovtLocalBodyDetails(lbCode);
    		if(localBodyDetailsList!=null && !localBodyDetailsList.isEmpty()){
    			LocalBodyDetails localBodyDetails=localBodyDetailsList.get(0);
    			stateCode=localBodyDetails.getStateCode();
    			String ctype=localBodyDetails.getCategory();
    			String clevel=localBodyDetails.getLevel();
    			level= reportService.getNextLevellbCode(lbCode,stateCode,ctype,clevel);
    			 Message = reportService.getLocalBodyNameByCode(lbCode);
    			 isLocalbody=localGovtBodyService.checkLocalbodyExist(lbCode);
    		}
    	}
    	
    	if((isState!=null &&  "Y".equalsIgnoreCase(isState.trim()) && isStateFlag) ||  (isState!=null &&  "N".equalsIgnoreCase(isState.trim()) && isLocalbody) )
    	{
    	  if(level==null){
    		  mav = new ModelAndView("rptConsolidateforExternalMV");
    		  List<MappedVillageByLBCodeBean> mappedVillageByLBCode= reportService.getMappedVillageByLBCode(lbCode);
    		  Message = reportService.getLocalBodyName(lbCode,0);
    		  model.addAttribute("consolidateLBList", mappedVillageByLBCode);
    	  }else{
    	  List<GetLocalBodyListbyLBtypebyState> getList  = reportService.getLocalBodyListbyLBtypebyState(stateCode, type, level, lbCode);
    	  if (type == 'P' || type == 'T') {
				for (GetLocalBodyListbyLBtypebyState list : getList) {
					if (level == 'D') {
						Integer childCount = list.getChildCount();
						if (childCount != null){
							ipCount += childCount;
						Integer grandChildCount = list.getGrandChildCount();
						
						if (grandChildCount != null){
							vpCount += grandChildCount;
						}	
						}
					} 
				}
			
    	if(level=='D'){
				mav = new ModelAndView("rptConsolidateforExternalZP");
    	}else if(level=='I'){
    		mav = new ModelAndView("rptConsolidateforExternalBP");
    	}else if(level=='V'){
    		mav = new ModelAndView("rptConsolidateforExternalGP");
    	}
    	  model.addAttribute("ipCount",ipCount);
    	  model.addAttribute("vpCount",vpCount);
    	 model.addAttribute("consolidateLBList", getList);
    	
    	  }
    	  
      }
    	  model.addAttribute("Message", Message);
      
    	}
    	else{
    		String erroMsg="";
    		if(isState==null || code==null){
    			erroMsg="Parameter is not Correct";
    			
    		}else if(!("Y".equalsIgnoreCase(isState.trim()) || "N".equalsIgnoreCase(isState.trim())) )
    		{
    			erroMsg="isState parameter can accept only 'Y' or 'N' value";
    		}
    		else if("Y".equalsIgnoreCase(isState.trim()) && !isStateFlag){
    			erroMsg="state does not exists";
    		}
    		else if("N".equalsIgnoreCase(isState.trim()) && !isLocalbody)
    		{
    			erroMsg="Localbody does not exists";
    		}
    		model.addAttribute("errorMsg",erroMsg);
    		mav = new ModelAndView("rptConsolidateforExternalError");
    	}
		
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
		}
	
	

	
	@RequestMapping(value = "/dpwardConstituencyWiseVP", method = RequestMethod.GET)
	public ModelAndView DPwardConstituencyWiseVP(
			@ModelAttribute("dpwardConstituencyWiseVPForm") VillageDataForm dpwardConstituencyWiseVPForm,
			Model model, HttpServletRequest request, HttpSession session) {
			ModelAndView mav = null;
			List<State> stateList = null;
			stateList = new ArrayList<State>();
			try {
					stateList = stateService.getStateSourceList();
			if (stateList.size() > 0) {
				mav = new ModelAndView("dpwardConstituencyWiseVP");
				model.addAttribute("stateSourceList", stateList);
				model.addAttribute("dpwardConstituencyWiseVPForm",dpwardConstituencyWiseVPForm);

			} else {

				mav = new ModelAndView("errorConfigshow");
				request.setAttribute("message", "state not found in list");

			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/dpwardConstituencyWiseVPPOST", method = RequestMethod.POST)
	public ModelAndView dpwardConstituencyWiseVPPOST(
			@ModelAttribute("dpwardConstituencyWiseVPForm") VillageDataForm dpwardConstituencyWiseVPForm,
			Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		List<State> stateList = null;
		List<DPwardConstituencyWiseVP> dpwardConstituencyWiseVPList = null;
		dpwardConstituencyWiseVPList = new ArrayList<DPwardConstituencyWiseVP>();
		Integer stateCode = null;
		stateList = new ArrayList<State>();
		boolean messageFlag = false;
		try {

			stateList = stateService.getStateSourceList();
			
			mav = new ModelAndView("dpwardConstituencyWiseVP");
			model.addAttribute("stateSourceList", stateList);
			model.addAttribute("DPwardConstituencyWiseVPForm",
					dpwardConstituencyWiseVPForm);
			String captchaAnswer = dpwardConstituencyWiseVPForm
					.getCaptchaAnswers();
			CaptchaValidator captchaValidator = new CaptchaValidator();

			if (captchaAnswer != null){
				messageFlag = captchaValidator.validateCaptcha(httpSession,
						captchaAnswer);
			}	

			dpwardConstituencyWiseVPForm.setCaptchaAnswers(null);

			if (messageFlag == true) {
				if (dpwardConstituencyWiseVPForm.getStateNameEnglish() != null){
					stateCode = Integer.parseInt(dpwardConstituencyWiseVPForm
							.getStateNameEnglish());
				}	
				if (stateCode != null) {
					
					dpwardConstituencyWiseVPList = reportService.getDPwardConstituencyWiseVP(stateCode);
					String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
					mav.addObject("stateName",stateName);
					httpSession.setAttribute("dpwardConstituencyWiseVPList", dpwardConstituencyWiseVPList);
					httpSession.setAttribute("stateName", stateName);
					model.addAttribute("DPwardConstituencyWiseVPList", dpwardConstituencyWiseVPList);
					if(dpwardConstituencyWiseVPList.isEmpty())
					{
						model.addAttribute("flag1", 1);
						stateList = stateService.getStateSourceList();
						model.addAttribute("dpwardConstituencyWiseVPForm",dpwardConstituencyWiseVPForm);
					}
					return mav;
				} else {
					mav = new ModelAndView("errorConfigshow");
					request.setAttribute("message", "Problem to reading data");
				}
			}

			else {
				
				model.addAttribute("captchaSuccess2", messageFlag);
			}

			dpwardConstituencyWiseVPForm.setStateNameEnglish(null);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	
	
	
	
	/**
	 * All Birt Reports using Birt viewer  
	 * @throws Exception
     */
	
	
	/**
	 * Report on Updation Status(Home Page) Added by : Anchal Todariya Added on
	 * : 13-02-2015
	 * 
	 *//*
	@RequestMapping(value = "/stateFreezeReport", method = RequestMethod.GET)
	public ModelAndView getFreezeReport(Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("stateFreezeReport");
			request.setAttribute("report_design", "state_freez_report.rptdesign");
			model.addAttribute("serverLoc", serverLoc);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	*/
	
	/**
	 * Report on Organization Unit(Report No 28.)
	 * @author Anchal Todariya
	 * @return Organization unit birt report page
	 * */
	@RequestMapping(value="/departOrganizationUnit", method = RequestMethod.GET)
	public ModelAndView processToBuildHierarchy(HttpSession session, @ModelAttribute("viewDeptforadmin")MinistryForm viewDept, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
			List<State> statelist = new ArrayList<State>();
			statelist = villageReportService.getConsolidatedRptForVillage();
			model.addAttribute("statelist", statelist);
			mav = new ModelAndView("ReportondeptOrgUnit");
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		
		return mav;
	}
	
	
	/**
	 * Report on Organization Unit(Report No 28.)
	 * @param Added by Anchal
	 * @param on 10-1-2015
	 * @returncontroller Post for viewOrganizationUnit
	 */
		@RequestMapping(value ="/viewOrganizationUnitReport", method = RequestMethod.POST)
		public ModelAndView getOrgUnitDetails(@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit,BindingResult result,HttpServletRequest request,HttpSession session,Model model)
		{
			ModelAndView mav = null;
			try {
				mav = new ModelAndView("view_reportonOrgUnit");
					boolean messageFlag = false;
					String captchaAnswer = orggunit.getCaptchaAnswer();
					CaptchaValidator captchaValidator = new CaptchaValidator();
					if (captchaAnswer != null) {
						messageFlag = captchaValidator.validateCaptcha(session,	captchaAnswer);
					}
					orggunit.setCaptchaAnswer(null);
					
					if (messageFlag == false) {
						List<State> statelist = villageReportService.getConsolidatedRptForVillage();
						model.addAttribute("statelist", statelist);
						result.rejectValue("captchaAnswer", "captcha.errorMessage");
						mav.addObject("captchaAnswer", messageFlag);	
						mav=new ModelAndView("ReportondeptOrgUnit");
						return mav;
					}
					if (messageFlag == true) {
						model.addAttribute("serverLoc", serverLoc);
						request.setAttribute("report_design", "org_unit_report.rptdesign");
						if(orggunit.getRadioUser().equals("1")) {
							request.setAttribute("orgCode", String.valueOf(orggunit.getOrgCode()));
						} else {
							request.setAttribute("orgCode", String.valueOf(orggunit.getOrgUnitCode()));
						}
						request.setAttribute("stateCode", String.valueOf(orggunit.getCode()));
						request.setAttribute("orgztn", orggunit.getOrgztn());
						request.setAttribute("orgLvl", orggunit.getOrgLevel());
						request.setAttribute("orgLvlCode", orggunit.getUnitLvlDept());
					}
			}
			catch (Exception e)
			{
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
		
		
		
		/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
		@RequestMapping(value ="/habitation", method = RequestMethod.GET)
		public ModelAndView habitation(@ModelAttribute("habitation1")HabitationForm habitation,HttpServletRequest request,HttpSession httpSession) {
			
			ModelAndView mav = null;
			List<State> stateList =null;
			stateList=new ArrayList<State>(); 
			try {
				
				mav = new ModelAndView("habitation_report");
				stateList= stateService.getStateSourceList();
				mav.addObject("slc",stateList);
				
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
		
		/*added by Ashish Dhupia on 06/02/2015 for Habitation use case*/
		@RequestMapping(value = "/habitationpost", method = RequestMethod.POST)
		public ModelAndView habitationpots(@ModelAttribute("habitation1")HabitationForm habitation, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
			ModelAndView mav = null;
			try {

				boolean messageFlag = false;
				String captchaAnswer = habitation.getCaptchaAnswer();
				CaptchaValidator captchaValidator = new CaptchaValidator();
				if (captchaAnswer != null) {
					messageFlag = captchaValidator.validateCaptcha(session,	captchaAnswer);
				}
				habitation.setCaptchaAnswer(null);
				//List<Localbody> getLbPesa = new ArrayList<Localbody>();
				if (messageFlag == true) {
					mav = new ModelAndView("habitation_report");
				}

			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;

		}
		
		

		/*added by Ashish Dhupia on 06/02/2015 for Habitation use case*/
		@RequestMapping(value = "/birtReportPost", method = RequestMethod.POST)
		public ModelAndView setReportHome(@RequestParam(required = false) String param,@ModelAttribute("habitation1") HabitationForm habitation,Model model,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
			ModelAndView mav = null;
			List<State> stateList = null;
			stateList = new ArrayList<State>();
			try {
				mav = new ModelAndView("habitation_report");
				boolean messageFlag = true;
				String captchaAnswer = habitation.getCaptchaAnswer();
				CaptchaValidator captchaValidator = new CaptchaValidator();
				if (captchaAnswer != null) {
					messageFlag = captchaValidator.validateCaptcha(session,	captchaAnswer);
				}
				habitation.setCaptchaAnswer(null);
				if (messageFlag == false) {
					stateList = stateService.getStateSourceList();
					mav.addObject("captchaAnswer", messageFlag);
					mav.addObject("slc", stateList);
				}
				if (messageFlag == true) {
					model.addAttribute("serverLoc", serverLoc);
					request.setAttribute("report_design", "habitation.rptdesign");
					mav = new ModelAndView("reportPage");
					if (("V").equals(habitation.getParentType())) {
						habitation.setParentCode(habitation.getVparentCode());
					} else {
						habitation.setParentCode(habitation.getParentCode());
					}
					request.setAttribute("parenttype", String.valueOf(habitation.getParentCode()));
					request.setAttribute("ptype", String.valueOf(habitation.getParentType()));
				}
				}  catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			

			return mav;
		}
		
		
		/**
		 * Nomenclature of subdistrict birt report
		 * Added by : Pooja Sharma
		 * Added on : 28-04-2015
		 * 
		 * */
		@RequestMapping(value = "/nomenClatureSubdistrictReport",method = RequestMethod.GET)
		public ModelAndView getFreezeReport(HttpServletRequest request,Model model) {
			ModelAndView mav = null;
			try {
				mav = new ModelAndView("nomenClatureSubdistrictReport");
				model.addAttribute("serverLoc", serverLoc);
				request.setAttribute("report_design", "nomen_clature_subdistrict.rptdesign");
				
			}
			catch(Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
		
	

		/**
		 *	Department Designation Org Level birt report
		 * 	Added by : Pooja Sharma
		 * 	Added on : 26-05-2015
		 * */
		@RequestMapping(value="/departDesigOrgLevel", method = RequestMethod.GET)
		public ModelAndView getDepartDesigOrgLevel(HttpSession session, @ModelAttribute("viewDeptforadmin")MinistryForm viewDept, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		ModelAndView mav = null;
		try {
				List<State> statelist = new ArrayList<State>();
				statelist = villageReportService.getConsolidatedRptForVillage();
				model.addAttribute("statelist", statelist);
				mav = new ModelAndView("reportOnDeptDesigOrgLevel");
				
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			
			return mav;
		}

		
		
		/**
	 * 
	 * @Author Pooja
	 * @since 26-5-2015
	 * @returncontroller Post for viewDepartmentDesignationReport
	 */
		@RequestMapping(value ="/viewDepartmentDesignationReport", method = RequestMethod.POST)
		public ModelAndView getOrgDeptDesigDetails(@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit,BindingResult result,HttpServletRequest request,HttpSession session,Model model)
		{
			ModelAndView mav = null;
			try {
				mav = new ModelAndView("viewReportOnDeptDesigOrgLevel");
					boolean messageFlag = false;
					String captchaAnswer = orggunit.getCaptchaAnswer();
					CaptchaValidator captchaValidator = new CaptchaValidator();
					if (captchaAnswer != null) {
						messageFlag = captchaValidator.validateCaptcha(session,	captchaAnswer);
					}
					orggunit.setCaptchaAnswer(null);
					
					if (messageFlag == false) {
						List<State> statelist = villageReportService.getConsolidatedRptForVillage();
						model.addAttribute("statelist", statelist);
						result.rejectValue("captchaAnswer", "captcha.errorMessage");
						mav.addObject("captchaAnswer", messageFlag);	
						mav=new ModelAndView("reportOnDeptDesigOrgLevel");
						return mav;
					}
					if (messageFlag == true) {
						model.addAttribute("serverLoc", serverLoc);
						model.addAttribute("stateCode", String.valueOf(orggunit.getCode()));
						request.setAttribute("report_design", "dept_desig_org_level_report.rptdesign");
						if(orggunit.getRadioUser().equals("1")) {
							request.setAttribute("orgCode", String.valueOf(orggunit.getOrgCode()));
						} else {
							request.setAttribute("orgCode", String.valueOf(orggunit.getOrgUnitCode()));
						}
						request.setAttribute("orgLvlCode", orggunit.getUnitLvlDept());
						request.setAttribute("orgztn", orggunit.getOrgztn());
					}
			}
			catch (Exception e)
			{
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
	
	

		/**
		 * Ward to Halka Mapping Report (Jharkhand State) 
		 * Added By Pooja on 20-08-2015
		 */
		@RequestMapping(value = "/wardToHalkaMappingReport", method = RequestMethod.GET)
		public ModelAndView wardToHalkaMappingReportGet(@ModelAttribute("downloadView") SearchForm downloadView, Model model, HttpSession session, HttpServletRequest request) {
			ModelAndView mav = null;
			try {
				mav = new ModelAndView("wardToHalkaMappingReport");
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}

		@RequestMapping(value = "/wardToHalkaMappingReport", method = RequestMethod.POST)
		public ModelAndView wardToHalkaMappingReportPost(@ModelAttribute("downloadView") SearchForm downloadView, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {
			ModelAndView mav = null;
			try {
				mav = new ModelAndView("reportPageForWardHalkaMapping");
				String captchaAnswer = downloadView.getCaptchaAnswer();
				CaptchaValidator captchaValidator = new CaptchaValidator();

				boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
				if (messageFlag) {
					mav = new ModelAndView("reportPageForWardHalkaMapping");
					model.addAttribute("serverLoc", serverLoc);
					request.setAttribute("format", downloadView.getDownloadOption());
					request.setAttribute("report_design", "ward_to_halka_mapping_jharkhand.rptdesign");
				} else {
					mav = new ModelAndView("wardToHalkaMappingReport");
					downloadView.setCaptchaAnswer(null);
					result.rejectValue("captchaAnswer", "captcha.errorMessage");
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
		 * Halka to Village Panchayat Mapping Report(Jharkhand State)
		 * @author Pooja @since 08-10-2015
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/halkaToVillageMappingReport", method = RequestMethod.GET)
		public ModelAndView halkaToVillageMappingReportGet(@ModelAttribute("downloadView") SearchForm downloadView, Model model, HttpSession session, HttpServletRequest request) {
			ModelAndView mav = null;
			try {
				List<State> statelist = new ArrayList<State>();
				List<District> districtList = new ArrayList<District>();
				mav = new ModelAndView("halkaToVillageMappingReport");
				statelist = stateService.getStateSourceList();
				districtList = districtService.getDistrictList(20);//District List for JHARKHAND State
				downloadView.setStateNameEnglish("20");//Set State JHARKHAND
				model.addAttribute("statelist", statelist);
				model.addAttribute("districtList", districtList);
			} catch (Exception e) {
				log.debug("Exception" + e);
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
		@RequestMapping(value = "/halkaToVillageMappingReport", method = RequestMethod.POST)
		public ModelAndView halkaToVillageMappingReportPost(@ModelAttribute("downloadView") SearchForm downloadView, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {
			ModelAndView mav = null;
			try {
				mav = new ModelAndView("reportPageForHalkaToVillageMapping");
				String captchaAnswer = downloadView.getCaptchaAnswer();
				CaptchaValidator captchaValidator = new CaptchaValidator();

				boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
				if (messageFlag) {
					String format = downloadView.getDownloadOption();	
					String districtCode = downloadView.getDistrictNameEnglish();
					String districtName = (String)request.getParameter("disttname");
					mav = new ModelAndView("reportPageForHalkaToVillageMapping");
					model.addAttribute("serverLoc", serverLoc);
					request.setAttribute("format", format);
					request.setAttribute("districtCode",Integer.parseInt(districtCode));
					request.setAttribute("districtName", districtName);
					request.setAttribute("report_design", "halka_to_village_panchayat_mapping_jharkhand.rptdesign");
				} else {
					List<State> statelist = new ArrayList<State>();
					List<District> districtList = new ArrayList<District>();
					mav = new ModelAndView("halkaToVillageMappingReport");
					statelist = stateService.getStateSourceList();
					downloadView.setStateNameEnglish("20");//Set State JHARKHAND
					districtList = districtService.getDistrictList(20);//District List for JHARKHAND State
					model.addAttribute("statelist", statelist);
					model.addAttribute("districtList", districtList);
					downloadView.setCaptchaAnswer(null);                           
					result.rejectValue("captchaAnswer", "captcha.errorMessage");
				}
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
		
		
		/*
		    StateWise GIS Mapped Local Body Report
		    Added By Pranav Tiwari
		  * Aug,31,2016
		*/

		@RequestMapping(value="/globalViewStatewiseLocalBody",method = RequestMethod.GET)
		public ModelAndView showGlobalStatewiseLocalBody(Model model,HttpServletRequest request){
			ModelAndView mav = new ModelAndView("globalView_Statewise_LocalBodyReport");
			try {
				mav.addObject("reportingStatewiseGISMappedLBEntity",new ReportingStatewiseGISMappedLBEntity());
				model.addAttribute("showSearchResult", Boolean.TRUE);
				
			} catch (Exception e) {
				IExceptionHandler exceptionHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = exceptionHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				return new ModelAndView(redirectPath);
			}
			return mav;
			
		}
		
		@RequestMapping(value= "/viewGlobalStateWiseLocalBodyReport", method=RequestMethod.POST)
		public ModelAndView showGlobalStatewiseLocalBodyReport(@ModelAttribute("reportingStatewiseGISMappedLBEntity") ReportingStatewiseGISMappedLBEntity reportingStatewiseGISMappedLBEntity,
																Model model,HttpServletRequest httpRequest,HttpSession httpSession){
			ModelAndView mav = new ModelAndView("globalView_Statewise_LocalBodyReport");
			
			try {
				String captchaAnswer = reportingStatewiseGISMappedLBEntity.getCaptchaAnswer();
				CaptchaValidator captchaValidator = new CaptchaValidator();
				boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
				if(!messageFlag){
					mav.addObject("captchaFailedMesseg",messageFlag);
					reportingStatewiseGISMappedLBEntity.setCaptchaAnswer(null);
					model.addAttribute("showSearchResult", Boolean.TRUE);
					return mav;
				}
				
				
				List<ReportingStatewiseGISMappedLBEntity> localBodyEntityDetails = reportService.getStatewiseGISMappedLocalBody();
				model.addAttribute("SEARCH_LOCALBODY_KEY",localBodyEntityDetails);
				model.addAttribute("showSearchResult", Boolean.FALSE);
				
			} catch (Exception e) {
				log.error("View details of the controller :",e);
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleException(httpRequest, "", "label.lgd", 1, e);
				mav.setViewName(redirectPath);
				return mav;
			}
			
			
			return mav;
		}
		
		
		/*
		 * Report on Local bodies mapped with districts
		   Added By Pranav Tiwari
		   Oct,07,2016
		  
		 */
		
		@RequestMapping(value = "/globalViewLocalBodyMappedToDistricts",method = RequestMethod.GET)
		public ModelAndView viewLocalBodyMappedToDistricts(Model model,HttpServletRequest request){
			ModelAndView mav = new ModelAndView("globalview_localbody_districts_mapped_report");
			try {
				mav.addObject("localBodyMappedToDistrictEntity",new LocalBodyMappedToDistrictsEntity());
				model.addAttribute("stateSourceList", stateService.getStateSourceList());
				model.addAttribute("showSearchHierarchy", Boolean.TRUE);
			} catch (Exception e) {
				IExceptionHandler exceptionHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = exceptionHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				return new  ModelAndView(redirectPath);
			}
			
			return mav;
		}
		
		@RequestMapping(value = "/viewLocalBodyMappedToDistricts", method = RequestMethod.POST)
		public ModelAndView localBodiesMappedToDistricts(@ModelAttribute("localBodyMappedToDistrictEntity")LocalBodyMappedToDistrictsEntity localBodyMappedToDistrictEntity,
															Model model,HttpServletRequest httpRequest,HttpServletResponse httpServletResponse,HttpSession httpSession){
			ModelAndView mav = new ModelAndView("globalview_localbody_districts_mapped_report");
			try {
				 String captchaAnswer = localBodyMappedToDistrictEntity.getCaptchaAnswer();
				 CaptchaValidator captchaValidator = new CaptchaValidator();
				 boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
				 if(!messageFlag){
					 mav.addObject("captchaFailedMesseg",messageFlag);
					 localBodyMappedToDistrictEntity.setCaptchaAnswer(null);
					 model.addAttribute("stateSourceList", stateService.getStateSourceList());
					 model.addAttribute("showSearchHierarchy", Boolean.TRUE);
					 return mav;
				 }
				 Integer stateCode = localBodyMappedToDistrictEntity.getParamStateCode();
				 List<LocalBodyMappedToDistrictsEntity> localBodylist = reportService.getLocalBodyMappedWithDistricts(stateCode);
				 model.addAttribute("SEARCH_LOCALBODY_KEY",localBodylist);
				 model.addAttribute("showSearchResult",Boolean.FALSE);
				 
				 
			} catch (Exception e) {
				log.error("View Details of controller :",e);
				IExceptionHandler exceptionHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = exceptionHandler.handleSaveRedirectException(httpRequest, "", "label.lgd", 1, e);
				mav.setViewName(redirectPath);
				return mav;
			}
			return mav;
		}
		

			
		
	@PostConstruct
	public void initIt() throws Exception {
		
		InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");

		Properties properties = new Properties();
		properties.load(inputStreamPro);
		serverLoc=properties.getProperty("birt.server.location");
	}
	
	@RequestMapping(value = "/stateWisefreezedDistrict")
	public ModelAndView viewStateWiseFreezedDistrict(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("stateWisefreezedDistrict");
		try {
			List<Object> freezedDistrictlist = reportService.getFreezedStatusList();
			mav.addObject("freezedDistrictlist", freezedDistrictlist);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	/* added by Sunita 0n 26-11-2016 */
	@RequestMapping(value = "/downloadStateDistrictWiseReportFile")
	public void downloadStateDistrictWiseReportFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("filename") String filename) throws Exception {
		String isSuccess = localBodyUtil.downloadFileFromServer(filename,DOWNLOAD_FILE_ID, request, response);
		if(!"SUCCESS".equals(isSuccess)){
			throw new Exception(isSuccess);
		}
		return;
	}
	
	
	@RequestMapping(value="/stateWisePopulation", method = RequestMethod.GET)
	public ModelAndView getPopulationReport(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
			List<State> statelist = new ArrayList<State>();
			statelist = villageReportService.getConsolidatedRptForVillage();
			model.addAttribute("statelist", statelist);
			mav = new ModelAndView("stateWisePopulation");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/stateWisePopulation", method = RequestMethod.POST)
	public ModelAndView reportPopulationReport(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
		
		mav = new ModelAndView("stateWisePopulation");
		mav.addObject("entityName", orggunit.getCode());
		mav.addObject("serverLoc", serverLoc);
		mav.addObject("report_design", "stateWisePopulation.rptdesign");
		mav.addObject("dataExists", true);
		List<State> statelist = new ArrayList<State>();
		statelist = villageReportService.getConsolidatedRptForVillage();
		for (State state : statelist) {
			if(state.getStatePK().getStateCode() == orggunit.getCode()){
				mav.addObject("stateName", state.getStateNameEnglish());
			}
		}
		model.addAttribute("statelist", statelist);
	}
	catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/changesOccureEntity", method = RequestMethod.GET)
	public ModelAndView changeOccureGivenTime(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
			mav = new ModelAndView("changesOccureEntityDetails");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/changesOccureEntity", method = RequestMethod.POST)
	public ModelAndView changeOccureGivenTimeReport(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
		
		mav = new ModelAndView("changesOccureEntityDetails");
		mav.addObject("entityType", orggunit.getEntityType());
		mav.addObject("serverLoc", serverLoc);
		mav.addObject("report_design", "changedInGivenTime.rptdesign");
		mav.addObject("dataExists", true);
	}
	catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/nofnStates", method = RequestMethod.GET)
	public ModelAndView getNofnState(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
			mav = new ModelAndView("nofnStateList");
			List<State> statelist = new ArrayList<State>();
			statelist = villageReportService.getNofnStateList();
			model.addAttribute("statelist", statelist);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/nofnStates", method = RequestMethod.POST)
	public ModelAndView getNofnStates(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
		mav = new ModelAndView("nofnStateDetails");
		List<State> statelist = new ArrayList<State>();
		statelist = villageReportService.getNofnStateList();
		model.addAttribute("statelist", statelist);
		mav.addObject("entityName", orggunit.getCode());
		String captchaAnswer = orggunit.getCaptchaAnswer();
		
		CaptchaValidator captchaValidator = new CaptchaValidator();
		boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
		if ( !messageFlag ) {
			mav = new ModelAndView("nofnStateList");
			mav.addObject("captchaFaliedMessage", messageFlag);
			orggunit.setCaptchaAnswer(null);
			return mav;
		}
		
		mav.addObject("serverLoc", serverLoc);
		mav.addObject("report_design", "nofnStateList.rptdesign");
		mav.addObject("dataExists", true);
		
		for (Object state : statelist) {
			int stateCode=(Integer)((Object[]) state)[0];
			int code=orggunit.getCode();
			if(stateCode == code){
				mav.addObject("stateName", ((Object[]) state)[1]);
				break;
			}
		}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/changedEntity", method = RequestMethod.GET)
	public ModelAndView chnagedEntitys(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
			mav = new ModelAndView("changedEntity");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/changedEntity", method = RequestMethod.POST)
	public ModelAndView getChangedEntity(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	
	try {
		mav = new ModelAndView("entityChangesDetails");
		
		String captchaAnswer = orggunit.getCaptchaAnswer();
		CaptchaValidator captchaValidator = new CaptchaValidator();
		boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
		if ( !messageFlag ) {
			mav = new ModelAndView("changedEntity");
			mav.addObject("captchaFaliedMessage", messageFlag);
			orggunit.setCaptchaAnswer(null);
			return mav;
		}
		Date d1 = new Date();

		String fromDate = orggunit.getFromDate();
		String toDate = orggunit.getToDate();
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		d1 = dt.parse(fromDate);
		fromDate = dta.format(d1);
		d1 = dt.parse(toDate);
		toDate = dta.format(d1);
		mav.addObject("fromDate", fromDate);
		mav.addObject("toDate", toDate);
		mav.addObject("serverLoc", serverLoc);
		mav.addObject("report_design", "changedInGivenTime.rptdesign");
		mav.addObject("dataExists", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/gisMapVerificationStatus", method = RequestMethod.GET)
	public ModelAndView gisMapVerificationStatus(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	ModelAndView mav = null;
	try {
			mav = new ModelAndView("gis_map_verification_status");
			mav.addObject("initialState", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/gisMapVerificationStatus", method = RequestMethod.POST)
	public ModelAndView gisMapVerificationStatusPost(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
		mav = new ModelAndView("gis_map_verification_status");
		String captchaAnswer = orggunit.getCaptchaAnswer();
		
		CaptchaValidator captchaValidator = new CaptchaValidator();
		boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
		if ( !messageFlag ) {
			mav = new ModelAndView("gis_map_verification_status");
			mav.addObject("captchaFaliedMessage", messageFlag);
			orggunit.setCaptchaAnswer(null);
			mav.addObject("initialState", true);
			return mav;
		}
		
		mav.addObject("serverLoc", serverLoc);
		mav.addObject("report_design", "gisMapVerificationStatus.rptdesign");
		mav.addObject("dataExists", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/transactionListReport", method = RequestMethod.GET)
	public ModelAndView changedEntityTransaction(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
			mav = new ModelAndView("changedEntityTransaction");
			mav.addObject("initialState", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/transactionListReport", method = RequestMethod.POST)
	public ModelAndView changedEntityTransactionPost(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
		mav = new ModelAndView("changedEntityTransaction");
		String captchaAnswer = orggunit.getCaptchaAnswer();
		
		Date d1 = new Date();

		String fromDate = orggunit.getFromDate();
		String toDate = orggunit.getToDate();
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		d1 = dt.parse(fromDate);
		fromDate = dta.format(d1);
		d1 = dt.parse(toDate);
		toDate = dta.format(d1);
		mav.addObject("fromDate", fromDate);
		mav.addObject("toDate", toDate);
		CaptchaValidator captchaValidator = new CaptchaValidator();
		boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
		if ( !messageFlag ) {
			mav = new ModelAndView("changedEntityTransaction");
			mav.addObject("captchaFaliedMessage", messageFlag);
			orggunit.setCaptchaAnswer(null);
			mav.addObject("initialState", true);
			return mav;
		}
		
		mav.addObject("serverLoc", serverLoc);
		mav.addObject("report_design", "districtofSpecificState.rptdesign");
		mav.addObject("dataExists", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/registredUserIpReport", method = RequestMethod.GET)
	public ModelAndView registredUserIpReport(HttpSession session,
			@ModelAttribute("viewDeptforadmin") OrganizationUnitForm orggunit, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("registredUserIpAddress").addObject("initialState", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/registredUserIpReport", method = RequestMethod.POST)
	public ModelAndView registredUserIpReportPost(HttpSession session,
			@ModelAttribute("viewDeptforadmin") OrganizationUnitForm orggunit, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("registredUserIpAddress");
			String captchaAnswer = orggunit.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if (!messageFlag) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				orggunit.setCaptchaAnswer(null);
				mav.addObject("initialState", true);
				return mav;
			}
			mav.addObject("serverLoc", serverLoc);
			mav.addObject("report_design", "registredUserIp.rptdesign");
			mav.addObject("dataExists", true);
			/*
			 * mav.addObject("serverLoc", serverLoc).addObject("report_design",
			 * "registredUserIp.rptdesign").addObject("dataExists", true);
			 */
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/getStatewiseEntitiesCount", method = RequestMethod.GET)
	public ModelAndView getStatewiseEntitiesCount(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseEntitiesCount");
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("showReport", Boolean.FALSE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/getStatewiseEntitiesCount", method = RequestMethod.POST)
	public ModelAndView getStatewiseEntitiesCountPOST(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseEntitiesCount");
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showReport", Boolean.FALSE);
				return mav;
			} 
			Gson gson = new Gson();
			List<StatewiseEntitiesCount> statewiseEntitiesCountList =initialService.getDataFromJsonFile();
			Comparator<StatewiseEntitiesCount> comp = new CompareStatewiseEntitiesCount() ;
	        Collections.sort(statewiseEntitiesCountList, comp);
			/*StatewiseEntitiesCount[] statewiseEntitiesCountArray = gson.fromJson(new FileReader(getCommonJsonFilePath()+File.separator+"lgd_statewise_entities_count.json"), StatewiseEntitiesCount[].class);
			List<StatewiseEntitiesCount> statewiseEntitiesCountList = Arrays.asList(statewiseEntitiesCountArray);*/
			
			model.addAttribute("statewiseEntitiesCountList",statewiseEntitiesCountList);
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("showReport", Boolean.TRUE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/getGISStatewiseEntitiesCount", method = RequestMethod.GET)
	public ModelAndView getGISShowStatewiseEntitiesCount(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		return new ModelAndView("gisStatewiseEntitiesCount");
	}
	
		@RequestMapping(value="/getStateWiseUrbanLB", method = RequestMethod.GET)
	public ModelAndView getStateWiseUrbanLocalBody(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	ModelAndView mav = null;
	try {
			mav = new ModelAndView("stateWiseLocalBodyUrbanCount");
			mav.addObject("initialState", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/getStateWiseUrbanLB", method = RequestMethod.POST)
	public ModelAndView getStateWiseUrbanLB(HttpSession session,@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpServletRequest request, HttpServletResponse response, Model model) {
	
	ModelAndView mav = null;
	try {
		mav = new ModelAndView("stateWiseLocalBodyUrbanCount");
		String captchaAnswer = orggunit.getCaptchaAnswer();
		
		CaptchaValidator captchaValidator = new CaptchaValidator();
		boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
		if ( !messageFlag ) {
			mav.addObject("captchaFaliedMessage", messageFlag);
			orggunit.setCaptchaAnswer(null);
			mav.addObject("initialState", true);
			return mav;
		}
		mav.addObject("serverLoc", serverLoc);
		mav.addObject("report_design", "StateWiseNumberOfBodies.rptdesign");
		mav.addObject("dataExists", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/getStatewiseLastupdated", method = RequestMethod.GET)
	public ModelAndView getStatewiseLastupdated(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseEntityLastUpdated");
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("showReport", Boolean.FALSE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/getStatewiseLastupdated", method = RequestMethod.POST)
	public ModelAndView getStatewiseLastupdatedPOST(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseEntityLastUpdated");
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showReport", Boolean.FALSE);
				return mav;
			} 
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			mav.addObject("serverLoc", serverLoc);
			mav.addObject("report_design", "statewiseEntityLastUpdated.rptdesign");
			model.addAttribute("showReport", Boolean.TRUE);
			model.addAttribute("screenWidth",genericReportingEntity.getScreenWidth());
			model.addAttribute("screenHeight",genericReportingEntity.getScreenHeight());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/getStatewiseLGDDataConfirmation", method = RequestMethod.GET)
	public ModelAndView getStatewiseLGDDataConfirmation(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseLGDDataConfirmation");
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("showReport", Boolean.FALSE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/getStatewiseLGDDataConfirmation", method = RequestMethod.POST)
	public ModelAndView getStatewiseLGDDataConfirmationPOST(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseLGDDataConfirmation");
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showReport", Boolean.FALSE);
				return mav;
			} 
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			mav.addObject("serverLoc", serverLoc);
			mav.addObject("report_design", "statewiseLGDDataConfirmation.rptdesign");
			model.addAttribute("showReport", Boolean.TRUE);
			model.addAttribute("screenWidth",genericReportingEntity.getScreenWidth());
			model.addAttribute("screenHeight",genericReportingEntity.getScreenHeight());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/getStatewiseTotalGPNPartlyMappedGP", method = RequestMethod.GET)
	public ModelAndView getStatewiseTotalGPNPartlyMappedGP(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseTotalGPsNPartMappedVillageGPs");
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("showReport", Boolean.FALSE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/getStatewiseTotalGPNPartlyMappedGP", method = RequestMethod.POST)
	public ModelAndView getStatewiseTotalGPNPartlyMappedGPPost(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseTotalGPsNPartMappedVillageGPs");
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showReport", Boolean.FALSE);
				return mav;
			} 
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			mav.addObject("serverLoc", serverLoc);
			mav.addObject("report_design", "statewisetotalGPNpartlyMappedGP.rptdesign");
			model.addAttribute("showReport", Boolean.TRUE);
			model.addAttribute("screenWidth",genericReportingEntity.getScreenWidth());
			model.addAttribute("screenHeight",genericReportingEntity.getScreenHeight());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/getStatewiseVillageMappedCount", method = RequestMethod.GET)
	public ModelAndView getStateWiseVillage(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseVillageMappedCount");
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			model.addAttribute("showReport", Boolean.FALSE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/getStatewiseVillageMappedCount", method = RequestMethod.POST)
	public ModelAndView getStateWiseVillage(@ModelAttribute("genericReportingEntity") GenericReportingEntity genericReportingEntity, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("statewiseVillageMappedCount");
			String captchaAnswer = genericReportingEntity.getCaptchaAnswer();
			
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if ( !messageFlag ) {
				mav.addObject("captchaFaliedMessage", messageFlag);
				genericReportingEntity.setCaptchaAnswer(null);
				model.addAttribute("showReport", Boolean.FALSE);
				return mav;
			} 
			mav.addObject("genericReportingEntity", new GenericReportingEntity());
			mav.addObject("serverLoc", serverLoc);
			mav.addObject("report_design", "stateWiseVillageCount.rptdesign");
			model.addAttribute("showReport", Boolean.TRUE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/stateWiseUnmappedVillages", method = RequestMethod.GET)
	public ModelAndView stateWiseUnmappedVillages( HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {	
			mav=new ModelAndView("stateWiseUnmappedVillages");
			mav.addObject("viewDeptforadmin", new OrganizationUnitForm());
			List<State> statelist = new ArrayList<State>();
			statelist = villageReportService.getNofnStateList();
			model.addAttribute("stateList", statelist);
			model.addAttribute("showReport", Boolean.FALSE);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	@RequestMapping(value="/stateWiseUnmappedVillages", method = RequestMethod.POST)
	public ModelAndView stateWiseUnmappedVillages(@ModelAttribute("viewDeptforadmin")OrganizationUnitForm orggunit, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("stateWiseUnmappedVillageList");
			List<State> statelist = new ArrayList<State>();
			statelist = villageReportService.getNofnStateList();
			model.addAttribute("statelist", statelist);
			mav.addObject("entityName", orggunit.getCode());
			/*
			 * String captchaAnswer = orggunit.getCaptchaAnswer();
			 * 
			 * CaptchaValidator captchaValidator = new CaptchaValidator(); boolean
			 * messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer); if (
			 * !messageFlag ) { mav = new ModelAndView("stateWiseUnmappedVillages");
			 * mav.addObject("captchaFaliedMessage", messageFlag);
			 * orggunit.setCaptchaAnswer(null); return mav; }
			 * 
			 * mav.addObject("serverLoc", serverLoc); mav.addObject("report_design",
			 * "stateWiseUnmappedVillage.rptdesign"); mav.addObject("dataExists", true);
			 */
			if ((request.getParameter("captchaAnswer") != null) && (!request.getParameter("captchaAnswer").isEmpty()))
		      {
		        String captchaAnswer = request.getParameter("captchaAnswer");
		        CaptchaValidator captchaValidator = new CaptchaValidator();
		        boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
		        if (!messageFlag)
		        {
		          mav = new ModelAndView("stateWiseUnmappedVillages");
		          mav.addObject("captchaFaliedMessage", Boolean.valueOf(messageFlag));
		          orggunit.setCaptchaAnswer(null);
		          return mav;
		        }
		       }
		        mav = new ModelAndView("stateWiseUnmappedVillageList");
		        mav.addObject("serverLoc", serverLoc);
				mav.addObject("report_design", "stateWiseUnmappedVillage.rptdesign");
				mav.addObject("dataExists", true);
				
			for (Object state : statelist) {
				int stateCode=(Integer)((Object[]) state)[0];
				int code=orggunit.getCode();
				if(stateCode == code){
					mav.addObject("stateName", ((Object[]) state)[1]);
					break;
				}
			}
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
	}
	
	private String getCommonJsonFilePath()throws Exception{
		AttachmentMaster attachmentMaster=reportService.getUploadFileConfigurationDetails(6L);
		 File dir = new File(attachmentMaster.getFileLocation());
		 if(!dir.exists()){
		    	dir.mkdirs();
		   }
		return dir.getAbsolutePath();
	}
	
	
	@RequestMapping(value = "/stateWiseGPtoVillageMappingReportB", method = RequestMethod.GET)
	public ModelAndView getStateWiseGPtoVillageMappingB(
			@ModelAttribute("stateWiseGPtoVillageMappingForm") VillageDataForm stateWiseGPtoVillageMappingForm,
			Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;

		try {

			// stateList.get(0).getStateCode();
			mav = new ModelAndView("stateWiseGPtoVillageMappingReportB");
			// model.addAttribute("localGovtBodyForm", new LocalGovtBodyForm());

			// localGovtBodyForm.setStateDetail(stateList);
			model.addAttribute("stateWiseGPtoVillageMappingForm",
					stateWiseGPtoVillageMappingForm);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/stateWiseGPtoVillageMappingReportB", method = RequestMethod.POST)
	public ModelAndView getStateWiseGPtoVillageMappingPostB(
			@ModelAttribute("stateWiseGPtoVillageMappingForm") VillageDataForm stateWiseGPtoVillageMappingForm,
			Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		List<StatewiseGPtoVillageMapped> stateWiseGPtoVillageMappingList = null;
		stateWiseGPtoVillageMappingList = new ArrayList<StatewiseGPtoVillageMapped>();

		boolean messageFlag = false;
		try {

			mav = new ModelAndView("stateWiseGPtoVillageMappingReportB");

			model.addAttribute("stateWiseGPtoVillageMappingForm",
					stateWiseGPtoVillageMappingForm);
			String captchaAnswer = stateWiseGPtoVillageMappingForm
					.getCaptchaAnswers();
			CaptchaValidator captchaValidator = new CaptchaValidator();

			if (captchaAnswer != null){
				messageFlag = captchaValidator.validateCaptcha(session,
						captchaAnswer);
			}	
			stateWiseGPtoVillageMappingForm.setCaptchaAnswers(null);

			if (messageFlag == true) {

				stateWiseGPtoVillageMappingList = reportService
						.getStateWiseGPtoVillageMappingB();
				model.addAttribute("StatewiseGPtoVillageMappingList",
						stateWiseGPtoVillageMappingList);
				return mav;
			}

			else {
				mav.addObject("flag1", 1);
				model.addAttribute("captchaSuccess2", messageFlag);
			}

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value="/exceptionalReports", method = RequestMethod.GET)
	public ModelAndView exceptionalReports(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm,  HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("exceptionalReportsCapcha");
	} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value="/exceptionalReports", method = RequestMethod.POST)
	public ModelAndView exceptionalReportsPOST(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm,  HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {
			String captchaAnswer = exceptionalReportsForm.getCaptchaAnswer();
			
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if ( !messageFlag ) {
				mav = new ModelAndView("exceptionalReportsCapcha");
				mav.addObject("captchaFaliedMessage", messageFlag);
				exceptionalReportsForm.setCaptchaAnswer(null);
				return mav;
			} 
			
			mav = new ModelAndView("exceptionalBirtReports");
			model.addAttribute("report_design", (exceptionalReportsForm.getFileName()+".rptdesign"));
			model.addAttribute("serverLoc", serverLoc);
	} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	

	@RequestMapping(value = "/showGisView")
	public ModelAndView showGisView(@RequestParam("lvCode")Integer lvCode,@RequestParam("inParam")String inParam,@RequestParam("localGovBodyType")String localGovBodyType,
			HttpServletRequest request, Model model, HttpSession session){
		ModelAndView mav = new ModelAndView("GISMap");
		try {
			/*mav.addObject("lvCode",lvCode);
			mav.addObject("inParam",inParam);
			mav.addObject("localGovBodyType",localGovBodyType);*/
			List<Object[]> mapObject = mapService.mapConfigurations(lvCode, localGovBodyType, null, null);
			mav.addObject("parentbaseURL",mapObject.get(0)[3]);
			mav.addObject("baseURL",mapObject.get(0)[2]);
			mav.addObject("paramName",mapObject.get(0)[4]);
			mav.addObject("attributeName",mapObject.get(0)[5]);
			mav.addObject("token",mapObject.get(0)[6]);
			mav.addObject("attributeCodeName",mapObject.get(0)[7]);
			mav.addObject("inParam",inParam);
			if(("V").equals(localGovBodyType) && inParam.length()>0){
			mav.addObject("subDistCensus2011Code",reportService.getSubdistictCensusCodebyVillageCode(Integer.parseInt(inParam)));
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/localBodyInvalidation", method = RequestMethod.GET)
	public ModelAndView getLocalBodyInvalidation(@ModelAttribute("localBodyInvalidation") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request) {
		
		ModelAndView mav = null;
		try {
				mav = new ModelAndView("local_Body_Invalidation");
				model.addAttribute("stateList", stateService.getStateSourceList());
				model.addAttribute("localGovtBodyForm", localGovtBodyForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	@RequestMapping(value="/localBodyInvalidation", method = RequestMethod.POST)
	public ModelAndView localBodyInvalidationPOST(@ModelAttribute("localBodyInvalidation") LocalGovtBodyForm localGovtBodyForm,  HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try {  
			String captchaAnswer = localGovtBodyForm.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if( !messageFlag ){
				mav = new ModelAndView("local_Body_Invalidation");
				model.addAttribute("stateList", stateService.getStateSourceList());
				mav.addObject("captchaFaliedMessage", messageFlag);
				model.addAttribute("showSearchHierarchy", Boolean.TRUE);
				localGovtBodyForm.setCaptchaAnswer(null);
				return mav;
			} 
			mav = new ModelAndView("local_Body_Invalidation_New");
			mav.addObject("stateCode",localGovtBodyForm.getSlc());
			Integer lbTypeCode=Integer.parseInt(localGovtBodyForm.getRurallbTypeName().split(":")[0]);			
			mav.addObject("lblc", lbTypeCode);
			mav.addObject("stateNameEng", localGovtBodyForm.getStateNameEnglish().trim());
			mav.addObject("lbTypeName", localGovtBodyForm.getRurallbTypeName().split(":")[5].trim());
			mav.addObject("invalidate_localBody","invalidate_localBody.rptdesign");
			mav.addObject("serverLoc",this.serverLoc);
	}catch (Exception e){
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
      return mav;
	}

	@RequestMapping(value = "/exceptionReportForFullyMappedCover", method = RequestMethod.GET)
	public ModelAndView exceptionReportForFullyMappedCover(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm,HttpServletRequest request, Map<String, Object> model, HttpSession session) {
		ModelAndView mav = null;
		try{
			mav = new ModelAndView("exception_Report_For_Fully_Mapped");

			
			
			mav.addObject("stateList", stateService.getStateSourceList());

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/exceptionReportForFullyMappedCoverCaptcha", method = RequestMethod.POST)
	public ModelAndView repotStateWiseParticiptionPost(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm,HttpServletRequest request, Map<String, Object> model, HttpSession session) {
		ModelAndView mav = null;
		CaptchaValidator captchaValidator = new CaptchaValidator();
		try{
			Integer state =0;
			
			if(request.getParameter("captchaAnswer")!= null && !(request.getParameter("captchaAnswer")).isEmpty())
			{
			String captchaAnswer = request.getParameter("captchaAnswer");
		
			
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if ( !messageFlag ) {
				mav = new ModelAndView("exception_Report_For_Fully_Mapped");
				mav.addObject("captchaFaliedMessage", messageFlag);
				mav.addObject("stateList", stateService.getStateSourceList());
				exceptionalReportsForm.setCaptchaAnswer(null);
				return mav;
			}
			}
			else
			{
				mav = new ModelAndView("exception_Report_For_Fully_Mapped");
				mav.addObject("captchaFaliedMessage", "please enter the captcha correctly");
				mav.addObject("stateList", stateService.getStateSourceList());
				exceptionalReportsForm.setCaptchaAnswer(null);
				return mav;
			}
			if(request.getParameter("stateNameEnglish")!= null && !(request.getParameter("stateNameEnglish")).isEmpty())
			{
				 state = Integer.parseInt(String.valueOf(request.getParameter("stateNameEnglish")));	
			}
			 
			mav = new ModelAndView("exceptionReportForFullyMappedCoverCaptcha");
			mav.addObject("report_design", "exceptionReportForFullyMappedCover.rptdesign");
			mav.addObject("state_code", state);
			mav.addObject("serverLoc", serverLoc);
		
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	
	@RequestMapping(value="/exceptionReportBasedonYear", method = RequestMethod.GET)
	public ModelAndView exceptionReportBasedonYear(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm,  HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try{
			mav = new ModelAndView("exceptionReportBasedonYearCapcha");
            mav.addObject("stateList", stateService.getStateSourceList());

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
        return mav;
	}
	
	@RequestMapping(value="/exceptionReportBasedonYear", method = RequestMethod.POST)
	public ModelAndView exceptionReportBasedonYearPOST(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm,  HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mav = null;
		try{
			Integer state =0;
			if(request.getParameter("captchaAnswer")!= null  && !(request.getParameter("captchaAnswer")).isEmpty())
			{
			String captchaAnswer = request.getParameter("captchaAnswer");
		
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if ( !messageFlag ) {
				mav = new ModelAndView("exceptionReportBasedonYearCapcha");
				mav.addObject("captchaFaliedMessage", messageFlag);
				mav.addObject("stateList", stateService.getStateSourceList());
                return mav;
			}
			else {
				mav = new ModelAndView("exceptionReportBasedonYearCapcha");
				mav.addObject("captchaFaliedMessage", "please enter the captcha correctly");
				mav.addObject("stateList", stateService.getStateSourceList());
                }
			} 
			if(request.getParameter("stateNameEnglish")!= null&& !(request.getParameter("stateNameEnglish")).isEmpty())
			{
				 state = Integer.parseInt(request.getParameter("stateNameEnglish").toString());	
			}
			else
			{
				state= 0;
			}
			
			mav = new ModelAndView("exceptionReportBasedonYear");
			mav.addObject("report_design", "exceptionReportBasedonYear.rptdesign");
			mav.addObject("state_code", state);
			mav.addObject("serverLoc", serverLoc);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
	return mav;
	}
	
	@RequestMapping(value = "/exceptionalReportOnStateSelection", method = RequestMethod.GET)
	public ModelAndView exceptionalReportOnStateSelection(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm,HttpServletRequest request, Map<String, Object> model, HttpSession session) {
		ModelAndView mav = null;
		try{
			mav = new ModelAndView("exceptionalReportOnStateSelection");
            mav.addObject("stateList", stateService.getStateSourceList());

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/exceptionalReportOnStateSelectionCaptcha", method = RequestMethod.POST)
	public ModelAndView exceptionalReportOnStateSelectionPost(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm,HttpServletRequest request, Map<String, Object> model, HttpSession session) {
		ModelAndView mav = null;

		try{
			Integer state =0;
			if(request.getParameter("captchaAnswer")!= null  && !(request.getParameter("captchaAnswer")).isEmpty())
			{
			String captchaAnswer = request.getParameter("captchaAnswer");
		
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			if ( !messageFlag ) {
				mav = new ModelAndView("exceptionalReportOnStateSelection");
				mav.addObject("captchaFaliedMessage", messageFlag);
				mav.addObject("stateList", stateService.getStateSourceList());

				return mav;
			}
			else {
				mav = new ModelAndView("exceptionalReportOnStateSelection");
				mav.addObject("captchaFaliedMessage", "please enter the captcha correctly");
				mav.addObject("stateList", stateService.getStateSourceList());

			}
			} 
			if(request.getParameter("stateNameEnglish")!= null&& !(request.getParameter("stateNameEnglish")).isEmpty())
			{
				 state = Integer.parseInt(request.getParameter("stateNameEnglish").toString());	
			}
			else
			{
				state= 0;
			}
			
			mav = new ModelAndView("exceptionalReportOnStateSelectionCaptcha");
			mav.addObject("report_design", "exceptionalReportOnStateSelection.rptdesign");
			mav.addObject("state_code", state);
			mav.addObject("serverLoc", serverLoc);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
		}
		return mav;
	}
	@RequestMapping(value="/exceptionalReportOnUrbanLbNoWard", method = RequestMethod.GET)
	  public ModelAndView exceptionalReportOnUrbanLbNoWard(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm, HttpServletRequest request, Map<String, Object> model, HttpSession session)
	  {
	    ModelAndView mav = null;
	    try
	    {
	      mav = new ModelAndView("exceptionalReportOnUrbanLbNoWard");
	    }
	    catch (Exception e)
	    {
	      IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
	      String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", Integer.valueOf(1), e);
	      return new ModelAndView(redirectPath);
	    }
	    return mav;
	  }
	  
	  @RequestMapping(value="/exceptionalReportOnUrbanLbNoWardCaptcha", method = RequestMethod.POST)
	  public ModelAndView exceptionalReportOnUrbanLbNoWardPost(@ModelAttribute("exceptionalReportsForm") ExceptionalReportsForm exceptionalReportsForm, HttpServletRequest request, Map<String, Object> model, HttpSession session)
	  {
	    ModelAndView mav = null;
	    try
	    {
	      if ((request.getParameter("captchaAnswer") != null) && (!request.getParameter("captchaAnswer").isEmpty()))
	      {
	        String captchaAnswer = request.getParameter("captchaAnswer");
	        
	        CaptchaValidator captchaValidator = new CaptchaValidator();
	        boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
	        if (!messageFlag)
	        {
	          mav = new ModelAndView("exceptionalReportOnUrbanLbNoWard");
	          mav.addObject("captchaFaliedMessage", Boolean.valueOf(messageFlag));
	          exceptionalReportsForm.setCaptchaAnswer(null);
	          

	          return mav;
	        }
	        mav = new ModelAndView("exceptionalReportOnUrbanLbNoWardCapcha");
	        mav.addObject("report_design", "exceptionalReportOnUrbanLbNoWardCapcha.rptdesign");
	        mav.addObject("serverLoc", this.serverLoc);
	      }
	      else
	      {
	        mav = new ModelAndView("exceptionalReportOnUrbanLbNoWard");
	        mav.addObject("nullCaptcha", "Kindly enter captcha");
	        exceptionalReportsForm.setCaptchaAnswer(null);
	      }
	    }
	    catch (Exception e)
	    {
	      IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
	      String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", Integer.valueOf(1), e);
	      mav = new ModelAndView(redirectPath);
	    }
	    return mav;
	  }
	  
	  @RequestMapping(value = "/getLocalBodyInDisturbedState", method = RequestMethod.GET)
		public ModelAndView getLocalBodyInDisturbedState(@ModelAttribute("getLocalBodyInDisturbedState") LocalGovtBodyForm getLocalBodyInDisturbedState,  Model model, HttpServletRequest request, HttpSession httpSession) {
			
			ModelAndView mav = new ModelAndView("getLocalBodyInDisturbedState");
			List<State> stateList = null;
			try {
					stateList = stateService.getStateSourceList();
					if (!stateList.isEmpty()) {
							
						model.addAttribute("stateSourceList", stateList);
						model.addAttribute("getLocalBodyInDisturbedState", getLocalBodyInDisturbedState);
						mav.addObject("ShowHideData", false);
						
					} else {
							
						mav = new ModelAndView("errorConfigshow");
						request.setAttribute("message", "state not found in list");
					}
			} catch (Exception e) {
					
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
	  
	  @RequestMapping(value = "/getLocalBodyInDisturbedState", method = RequestMethod.POST)
		public ModelAndView getLocalBodyInDisturbedStatePOST(@ModelAttribute("getLocalBodyInDisturbedState") LocalGovtBodyForm getLocalBodyInDisturbedState,  Model model, HttpServletRequest request, HttpSession httpSession) {
			ModelAndView mav = new ModelAndView("getLocalBodyInDisturbedState");
			boolean messageFlag=false;
			List<State> stateList  = new ArrayList<State>();
			String stateName =null;
			try {
				String captchaAnswer = getLocalBodyInDisturbedState.getCaptchaAnswer();
	            CaptchaValidator captchaValidator = new CaptchaValidator();
	            if (!"".equals(captchaAnswer)){
					messageFlag = captchaValidator.validateCaptcha(httpSession,captchaAnswer);
					getLocalBodyInDisturbedState.setCaptchaAnswer(null);
	            }	
				if (messageFlag == true ) {
					
					 String stateCode=getLocalBodyInDisturbedState.getStateNameEnglish();
					 String lbdetails=getLocalBodyInDisturbedState.getLgd_LBTypeName();
					 Integer lbTypeCode=null;
					 stateList = stateService.getStateSourceList(Integer.parseInt(stateCode));
					 if(stateList.size()>0){
						 stateName =stateList.get(0).getStateNameEnglish();
					 }	 
					 if(!"".equals(stateCode) && !"".equals(lbdetails))
					 {
						 String lbdetailsArray[]=lbdetails.split(":");
						    lbTypeCode=Integer.parseInt(lbdetailsArray[0]);
						 	mav.addObject("report_design", "getLocalBodyInDisturbedState.rptdesign");
							mav.addObject("state_code", stateCode);
							mav.addObject("lbTypeCode", lbTypeCode);
							mav.addObject("serverLoc", serverLoc);
							mav.addObject("ShowHideData", true);
							

						
					 }
						
				}
				else
				{
					   stateList = stateService.getStateSourceList();
					   getLocalBodyInDisturbedState.setStateNameEnglish(null);	
						model.addAttribute("stateSourceList", stateList);
						model.addAttribute("rptViewUnMappedLocalBodies", getLocalBodyInDisturbedState);
						model.addAttribute("stateSourceList", stateList);
						mav.addObject("flag1", 1);
						model.addAttribute("captchaSuccess1", messageFlag);
						
					
				}
			return mav;
			} catch (Exception e) {
				
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
	  }
	  
	  @RequestMapping(value = "/stateFreezeReport", method = RequestMethod.GET)
		public ModelAndView getFreezeReport(Model model, HttpServletRequest request) {
			return new ModelAndView("stateFreezeReport");
		}
	  
	  @RequestMapping(value = "/getLGDUpdation", method = RequestMethod.POST)
		public @ResponseBody List<LGDUpdationEntity> getLGDUpdation(@RequestBody Integer stateCode) {
			return reportService.getLGDUpdation(stateCode);
		}
	  
	  
	 
	  
	  @RequestMapping(value = "/rptVillageConvertedRuralToUrban", method = RequestMethod.GET)
	  public ModelAndView rptVillageConvertedRuralToUrban(HttpSession session,@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB,Model model, HttpServletRequest request) {
		  	return new ModelAndView("village_converted_rural_to_urban");
	  }
	  
	  
	  @RequestMapping(value="/rptVillageConvertedRuralToUrbanView", method = RequestMethod.POST)
	  public ModelAndView rptVillageConvertedRuralToUrbanView(HttpSession session,@ModelAttribute("consolidateReportForRuralLB") ConsolidateReportLBRPT consolidateReportForRuralLB, Model model, HttpServletRequest request)
	  {
	    ModelAndView mav = null;
	    String financialYear = null;
	    try
	    {
	       financialYear =request.getParameter("financialYear");
	       if(financialYear!= null && !financialYear.isEmpty()){
	    	   financialYear =String.valueOf(request.getParameter("financialYear"));
			}
	      
	      
	      if ((request.getParameter("captchaAnswer") != null) && (!request.getParameter("captchaAnswer").isEmpty()))
	      {
	        String captchaAnswer = request.getParameter("captchaAnswer");
	        CaptchaValidator captchaValidator = new CaptchaValidator();
	        boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
	        if (!messageFlag)
	        {
	          mav = new ModelAndView("village_converted_rural_to_urban");
	          mav.addObject("captchaFaliedMessage", Boolean.valueOf(messageFlag));
	          consolidateReportForRuralLB.setCaptchaAnswer(null);
	          return mav;
	        }
			
	        mav = new ModelAndView("village_converted_rural_to_urban_view");
	        mav.addObject("financialYear",financialYear);
	        mav.addObject("report_design", "villageConvertedFromRuralToUrban.rptdesign");
	        mav.addObject("serverLoc", this.serverLoc);
	        mav.addObject("dataExists", true);
	      }
	      else
	      {
	        mav = new ModelAndView("village_converted_rural_to_urban");
	        mav.addObject("nullCaptcha", "Kindly enter captcha");
	        consolidateReportForRuralLB.setCaptchaAnswer(null);
	      }
	    }
	    catch (Exception e)
	    {
	      IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
	      String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", Integer.valueOf(1), e);
	      mav = new ModelAndView(redirectPath);
	    }
	    return mav;
	  }
	  
	  
}
