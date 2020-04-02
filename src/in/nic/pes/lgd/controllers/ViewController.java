package in.nic.pes.lgd.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.services.LocalBodyService;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.AllSearch;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.BlockHistory;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictHistory;
import in.nic.pes.lgd.bean.DistrictLineDepartment;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.LandRegionDetail;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTypeHistory;
import in.nic.pes.lgd.bean.LocalBodyTypeWiseDepartment;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.LocalbodyforStateWiseFinal;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.Organization;
import in.nic.pes.lgd.bean.OrganizationByCentreLevel;
import in.nic.pes.lgd.bean.ParentWiseLBList;
import in.nic.pes.lgd.bean.ParentWiseLocalBodies;
import in.nic.pes.lgd.bean.Search;
import in.nic.pes.lgd.bean.SearchElectionConstituency;
import in.nic.pes.lgd.bean.SearchLocalGovtBody;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StateHistory;
import in.nic.pes.lgd.bean.StateLineDepartment;
import in.nic.pes.lgd.bean.StateWiseEntityDetails;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.SubdistrictHistory;
import in.nic.pes.lgd.bean.VillageHistory;
import in.nic.pes.lgd.common.CaptchaValidator;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.common.SubAttachmentHandler;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.draft.dao.DraftSubdistrictDao;
import in.nic.pes.lgd.forms.BlockDataForm;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.DistrictDataForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.LocalGovtBodyDataForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;
import in.nic.pes.lgd.forms.MinistryForm;
import in.nic.pes.lgd.forms.OrganizationUnitForm;
import in.nic.pes.lgd.forms.SearchForm;
import in.nic.pes.lgd.forms.StateDataForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.BlockService;
import in.nic.pes.lgd.service.ComboFillingService;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.ConfigGovtOrderService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.LocalGovtSetupService;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.service.ParliamentService;
import in.nic.pes.lgd.service.ReportService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageReportService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.ApplicationConstant;
import in.nic.pes.lgd.validator.BlockValidator;
import in.nic.pes.lgd.validator.CitizenValidator;
import in.nic.pes.lgd.validator.DistrictValidator;
import in.nic.pes.lgd.validator.GovtOrderValidator;
import in.nic.pes.lgd.validator.MinistryValidator;
import in.nic.pes.lgd.validator.SubDistrictValidator;
import in.nic.pes.lgd.validator.ViewValidatorLB;
import in.nic.pes.lgd.validator.VillageValidator;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AttachedFilesForm;

@Controller
public class ViewController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(ViewController.class);
	private static final String VIEW_LOCAL_BODY_LIST = "viewLocalBodyList";
	private static final String VIEW_LOCAL_BODY_LIST_OTHER = "view_localgovtbody";
	private static final String VIEW_LOCAL_BODY_LIST_TRADITIONAL = "viewLocalBodyListTraditional";
	private static final String VIEW_LOCAL_BODY_LIST_URBAN = "viewLocalBodyListUrban";


	@Autowired
	DistrictService districtService;
	@Autowired
	DistrictValidator districtValidator;
	@Autowired
	SubDistrictValidator subDistrictValidator;
	@Autowired
	VillageValidator villageValidator;
	@Autowired
	BlockValidator blockValidator;
	@Autowired
	MinistryValidator ministryValidator;
	@Autowired
	StateService stateService;
	@Autowired
	VillageService villageService;
	@Autowired
	SubDistrictService subDistrictService;
	@Autowired
	BlockService blockService;
	@Autowired
	LocalGovtBodyService localGovtBodyService;
	@Autowired
	ParliamentService parliamentService;
	@Autowired
	OrganizationService organisationService;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	CitizenValidator citizenValidator;
	@Autowired
	VillageValidator viewValidator;
	@Autowired
	ViewValidatorLB viewValidatorLB;
	@Autowired
	ConfigGovtOrderService configGovtOrderService;

	@Autowired
	ComboFillingService comboFillingService;
	
	@Autowired
	GovernmentOrderService govtOrderService;
	
	/** The local govt setup service. */
	@Autowired
	LocalGovtSetupService localGovtSetupService;
	@Autowired
	StateDAO stateDao;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	GovtOrderValidator govtOrderValidator;
	
	@Autowired
	FileUploadUtility fileUploadUtility;
	
	@Autowired
	VillageReportService villageReportService;
	
	@Autowired
	ReportService reportService;
	
	@Autowired
	private LocalBodyService localBodyService;
	
	@Autowired
	private DraftSubdistrictDao draftSubdistrictDao;
	
	
	public static final String MESSAGE_RESOURCE_FILE_PATH = "/Messages.properties";

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "label.lgd","userId", 1, e);
		}
	}
	
	public int getStateCode(HttpSession session) {
		int stateCode = 0;
		if (session.getAttribute("stateCode") != null && !session.getAttribute("stateCode").equals(""))
		{
			String statecode = (String) session.getAttribute("stateCode");
			stateCode = Integer.parseInt(statecode);
			/*SessionObject sessionObject=(SessionObject)session.getAttribute("sessionObject");*/
			//slc=sessionObject.getSlc();
		}
		return stateCode;
	}
	public int getDistrictId(HttpSession session) 
	{
		Integer districtId = 0;
		try 
		{
			if (session.getAttribute("districtId") != null)
			{
				districtId = (Integer)session.getAttribute("districtId");
				//districtId=Integer.parseInt(districtS);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return districtId;
	}
	
	/*@RequestMapping(value = "/globalviewdistrict", method = RequestMethod.POST)
	public ModelAndView showglobalDistrictPage(@ModelAttribute("districtbean") DistrictForm districtbean, BindingResult result, Model model, HttpServletRequest request, SessionStatus status, HttpSession httpSession) {

		ModelAndView mav = null;
		httpSession.setAttribute("redirectDistrictBean", districtbean);
		try {
			mav = new ModelAndView("globalview_cdistrict");
			String captchaAnswer = districtbean.getCaptchaAnswer();
			String captchaAnswers = districtbean.getCaptchaAnswers();

			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
			boolean messageFlag2 = captchaValidator.validateCaptcha(httpSession, captchaAnswers);
			// System.out.println("============messageFlag============:"+messageFlag);
			if (messageFlag == false && messageFlag2 == false) {
				if (captchaAnswer != "") {
					mav.addObject("flag1", 1);
					mav.addObject("captchaSuccess1", messageFlag);
				} else {
					mav.addObject("flag2", 2);
					mav.addObject("captchaSuccess2", messageFlag);
				}

				districtbean.setCaptchaAnswer(null);
				districtbean.setCaptchaAnswers(null);
				return mav;
			} else {
				Integer offset=1;
				Integer limit=districtbean.getLimit();
				char EntityType = ' ';
				String statecode = null;
				districtValidator.viewValidateDistrict(districtbean, result);
				if (result.hasErrors()) {
					result.getAllErrors();
					mav = new ModelAndView("globalview_district");
					return mav;
				} else {
					if (districtbean.getPageType().equals("D")) {
						mav = new ModelAndView("globalview_district");
						EntityType = 'D';
					} else if (districtbean.getPageType().equals("CD")) {
						mav = new ModelAndView("globalview_cdistrict");
						EntityType = 'D';

						// Set State
						if (districtbean.getCode() != null || districtbean.getDistrictNameEnglish() != null) {
							httpSession.setAttribute("stateCode", null);
						}
						if (httpSession.getAttribute("stateCode") == null || httpSession.getAttribute("stateCode") != districtbean.getStateNameEnglish()) {
							httpSession.setAttribute("stateCode", districtbean.getStateNameEnglish() == null ? null : districtbean.getStateNameEnglish());
						}
					}

					httpSession.setAttribute("stateCode", httpSession.getAttribute("stateCode").equals("") == true ? null : httpSession.getAttribute("stateCode").toString());
					statecode = (String) httpSession.getAttribute("stateCode");

					Integer districteCode = null;

					if (districtbean.getCode().length() > 0 && !districtbean.getCode().isEmpty() && districtbean.getCode() != null) {

						districteCode = Integer.parseInt(districtbean.getCode());
					}
					String districtName = districtbean.getDistrictNameEnglish();
					List<StateWiseEntityDetails> stateWiseEntityDetails = null;

					httpSession.setAttribute("limit", limit);
					districtbean.setOffset(offset);
					httpSession.setAttribute("offset", offset);
					httpSession.setAttribute("villageCode", districteCode);
					httpSession.setAttribute("villageName", districtName);

					stateWiseEntityDetails = villageService.getStateWiseVillageList(statecode == null ? null : Integer.parseInt(httpSession.getAttribute("stateCode").toString()), EntityType, districteCode, districtName, null, null);
					Integer counter=stateWiseEntityDetails.size();
					httpSession.setAttribute("counter", counter);
					modified by pooja on 29-10-2015
					if((districteCode == null) && ("".equals(districtName))){
					stateWiseEntityDetails = villageService.getStateWiseVillageList(statecode == null ? null : Integer.parseInt(httpSession.getAttribute("stateCode").toString()), EntityType, districteCode, districtName, limit,0);
					}
					stateWiseEntityDetails = villageService.getStateWiseVillageList(statecode == null ? null : Integer.parseInt(httpSession.getAttribute("stateCode").toString()), EntityType, httpSession.getAttribute("villageCode") == null ? null
							: Integer.parseInt(httpSession.getAttribute("villageCode").toString()), httpSession.getAttribute("villageName") == null ? null : httpSession.getAttribute("villageName").toString(),
							httpSession.getAttribute("limit") == null ? null : Integer.parseInt(httpSession.getAttribute("limit").toString()), 0);
					if (statecode != null) {
						List<LandRegionDetail> landregionDetail = null;
						landregionDetail = new ArrayList<LandRegionDetail>();
						landregionDetail = downloadDirectoryService.landRegionDetail('S', Integer.parseInt(statecode));
						if (landregionDetail.size() > 0) {
							model.addAttribute("message", "Districts of " + landregionDetail.get(0).getStateNameEnglish() + " State");
						}
					}

					model.addAttribute("SEARCH_DISTRICT_RESULTS_KEY", stateWiseEntityDetails);
					districtbean.setStateWiseEntityDetails(stateWiseEntityDetails);
					model.addAttribute("viewPage", "abc");
					model.addAttribute("offsets", offset - 1);
					model.addAttribute("limits",limit);
					model.addAttribute("villageCount",	"Page " + offset + " of " +(counter% limit!=0? (counter/ limit)+1:(counter/ limit))); 
					districtbean.setPageRows(limit);
				}
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			districtbean.setCaptchaAnswer(null);
			districtbean.setCaptchaAnswers(null);
			return mav;
		}
		districtbean.setCaptchaAnswer(null);
		districtbean.setCaptchaAnswers(null);
		return mav;
	}*/

	
	@RequestMapping(value = "/viewDistrictPagination", method = RequestMethod.POST)
	public ModelAndView getSubDistrictPagination(
			@ModelAttribute("districtbean") DistrictForm districtbean,
			Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = null;
		try {

			char EntityType = ' ';

			if (districtbean.getPageType().equals("D")) {
				mav = new ModelAndView("view_district");
				EntityType = 'D';
			} else if (districtbean.getPageType().equals("CD")) {
				mav = new ModelAndView("view_cdistrict");
				EntityType = 'D';
			}

			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer
					.parseInt(httpSession.getAttribute("counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + districtbean.getDirection() > 0) {
				districtbean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ districtbean.getDirection());
				httpSession.setAttribute("offset", districtbean.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset")
					.toString()) == (Integer.parseInt(httpSession.getAttribute(
					"counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& districtbean.getDirection() == -1
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + districtbean.getDirection() > 0) {
				districtbean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ districtbean.getDirection());
				httpSession.setAttribute("offset", districtbean.getOffset());
			}

			List<StateWiseEntityDetails> stateWiseEntityDetails = null;
			stateWiseEntityDetails = villageService.getStateWiseVillageList(
					httpSession.getAttribute("stateCode") == null ? null
							: Integer.parseInt(httpSession.getAttribute(
									"stateCode").toString()),
					EntityType,
					httpSession.getAttribute("villageCode") == null ? null
							: Integer.parseInt(httpSession.getAttribute(
									"villageCode").toString()),
					httpSession.getAttribute("villageName") == null ? null
							: httpSession.getAttribute("villageName")
									.toString(),
					httpSession.getAttribute("limit") == null ? null : Integer
							.parseInt(httpSession.getAttribute("limit")
									.toString()),
					Integer.parseInt(httpSession.getAttribute("limit")
							.toString())
							* (Integer.parseInt(httpSession.getAttribute(
									"offset").toString()) - 1));
			model.addAttribute("SEARCH_DISTRICT_RESULTS_KEY",
					stateWiseEntityDetails);
			districtbean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
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
	
	@RequestMapping(value = "/globalviewDistrictPagination", method = RequestMethod.POST)
	public ModelAndView getGlobalSubDistrictPagination(@ModelAttribute("districtbean") DistrictForm districtbean, Model model, HttpServletRequest request, HttpSession httpSession) {
		ModelAndView mav = null;
		try {

			char EntityType = ' ';

			if (districtbean.getPageType().equals("D")) {
				mav = new ModelAndView("globalview_district");
				EntityType = 'D';
			} else if (districtbean.getPageType().equals("CD")) {
				mav = new ModelAndView("globalview_cdistrict");
				EntityType = 'D';
			}

			Integer offset=Integer.parseInt(httpSession.getAttribute("offset").toString());
			Integer counter=Integer.parseInt(httpSession.getAttribute("counter").toString());
			Integer limit=Integer.parseInt(httpSession.getAttribute("limit").toString());
			Integer direction=districtbean.getDirection();
			Integer lastLimit=counter % limit!=0?(counter / limit)+ 1:(counter / limit);
			if (offset != lastLimit	&& offset + direction > 0) {
				offset=offset+direction;
			} else if (offset == lastLimit	&& direction == -1 && offset + direction > 0) {
				offset=offset+direction;
			}
			districtbean.setOffset(offset );
			httpSession.setAttribute("offset", offset);
			List<StateWiseEntityDetails> stateWiseEntityDetails = null;
			stateWiseEntityDetails = villageService.getStateWiseVillageList(httpSession.getAttribute("stateCode") == null ? null : Integer.parseInt(httpSession.getAttribute("stateCode").toString()), EntityType, httpSession
					.getAttribute("villageCode") == null ? null : Integer.parseInt(httpSession.getAttribute("villageCode").toString()), httpSession.getAttribute("villageName") == null ? null : httpSession.getAttribute("villageName").toString(),
					httpSession.getAttribute("limit") == null ? null : limit,
					limit * (offset - 1));
			/*
			 * modified by pooja on 03-07-2015 for show heading message after
			 * pagination
			 */
			String statecode = (String) httpSession.getAttribute("stateCode");
			if (statecode != null) {
				List<LandRegionDetail> landregionDetail = null;
				landregionDetail = new ArrayList<LandRegionDetail>();
				landregionDetail = reportService.landRegionDetail('S', Integer.parseInt(statecode));
				if (landregionDetail.size() > 0) {
					model.addAttribute("message", "Districts of " + landregionDetail.get(0).getStateNameEnglish() + " State");
				}
			}
			model.addAttribute("SEARCH_DISTRICT_RESULTS_KEY", stateWiseEntityDetails);
			districtbean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
			model.addAttribute("offsets", offset - 1);
			model.addAttribute("limits", limit);
			model.addAttribute("villageCount",	"Page " + offset + " of " +(counter% limit!=0? (counter/ limit)+1:(counter/ limit))); 
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	
	// ---------------------------------------------

	// View District History
/*	@RequestMapping(value = "/viewDistrictHistory", method = RequestMethod.GET)
	public ModelAndView viewDistrictHistoryDetail(
			@ModelAttribute("districtForm") DistrictForm districtForm,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer districtCode)*/
	
	@RequestMapping(value = "/viewDistrictHistory")
	public ModelAndView viewDistrictHistoryDetail(
			@ModelAttribute("districtForm") DistrictForm districtForm,
			HttpServletRequest request, Model model)
	{
		ModelAndView mav = null;
		String id = request.getParameter("id");
		int districtCode=(districtForm.getDistrictId()==null)?Integer.parseInt(id):districtForm.getDistrictId();
		//int districtCode=districtForm.getDistrictId();
		try {
			mav = new ModelAndView("view_districthistory");
			char districtNameEnglish = 'D';
			List<DistrictHistory> districtHistoryDetail = districtService
					.getDistrictHistoryDetail(districtNameEnglish, districtCode);
			model.addAttribute("districtHistory", districtHistoryDetail);
			districtForm.setDistrictHistoryDetail(districtHistoryDetail);
			mav.addObject("districtForm", districtForm);
			model.addAttribute("viewPage", "abc");
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

	
	// View District History
	@RequestMapping(value = "/globalviewDistrictHistory", method = RequestMethod.GET)
	public ModelAndView viewDistrictHistoryDetailGlobal(
			@ModelAttribute("districtForm") DistrictForm districtForm,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer districtCode) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("globalview_districthistory");
			char districtNameEnglish = 'D';
			List<DistrictHistory> districtHistoryDetail = districtService
					.getDistrictHistoryDetail(districtNameEnglish, districtCode);
			model.addAttribute("districtHistory", districtHistoryDetail);
			districtForm.setDistrictHistoryDetail(districtHistoryDetail);
			mav.addObject("districtForm", districtForm);
			model.addAttribute("viewPage", "abc");
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

	
	@RequestMapping(value = "/viewdistrictbystatecode", method = RequestMethod.GET)
	public ModelAndView getDistrictByStateCode(HttpSession session,
			@ModelAttribute("districtbean") DistrictForm districtbean,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer stateCode) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_district");
			String query = "from District d where d.state.statePK.stateCode='"
					+ stateCode + "'and isactive=true order by districtCode";
			List<District> listDistrictDetails = null;
			listDistrictDetails = new ArrayList<District>();
			listDistrictDetails = districtService.getDistrictViewList(query);
			model.addAttribute("SEARCH_DISTRICT_RESULTS_KEY",
					listDistrictDetails);
			districtbean.setListDistrictDetail(listDistrictDetails);
			mav.addObject("districtForm", districtbean);
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

	// View District Detail
	/*@RequestMapping(value = "/viewDistrictDetail", method = RequestMethod.GET)
	public ModelAndView viewDistrictDetailList(
			@ModelAttribute("districtView") DistrictForm districtView,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer districtCode)*/
	@RequestMapping(value = "/viewDistrictDetail")
	public ModelAndView viewDistrictDetailList(
			@ModelAttribute("districtView") DistrictForm districtView,
			HttpServletRequest request, Model model)
	{
		ModelAndView mav = null;
		String id = request.getParameter("id");
		int districtCode=(districtView.getDistrictId()==null)?Integer.parseInt(id):districtView.getDistrictId();
		//int districtCode=districtView.getDistrictId();
		try {
			List<DistrictDataForm> listDistrictDetails = districtService
					.getDistrictDetailsModify(districtCode);
			mav = new ModelAndView("view_districtdetail");
			districtView.setListDistrictDetails(listDistrictDetails);
			
			
		    char districtNameEnglish = 'D';
			List<DistrictHistory> districtHistoryDetail = districtService
					.getDistrictHistoryDetail(districtNameEnglish, districtCode);
			model.addAttribute("districtHistory", districtHistoryDetail);
			districtView.setDistrictHistoryDetail(districtHistoryDetail);
		
			
			
			
			
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

	// View District Detail
		/*@RequestMapping(value = "/globalviewDistrictDetail")
		public ModelAndView globalviewDistrictDetailList(
				@ModelAttribute("districtView") DistrictForm districtView,
				HttpServletRequest request, Model model)
		{
			ModelAndView mav = null;
			int districtCode=districtView.getGlobaldistrictId();
			try {
				List<DistrictDataForm> listDistrictDetails = districtService
						.getDistrictDetailsModify(districtCode);
				mav = new ModelAndView("globalview_districtdetail");
				districtView.setListDistrictDetails(listDistrictDetails);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}

			return mav;
		}*/

	//Adding 2 columns to view government order details and the view map
	
	// View District Detail
	@RequestMapping(value = "/globalviewDistrictDetail")
	public ModelAndView globalviewDistrictDetailList(@ModelAttribute("districtView") DistrictForm districtView, BindingResult result, HttpServletRequest request, Model model,@RequestParam("globaldistrictId") Integer districtCode) {
		ModelAndView mav = null;
		
		
		//int districtCode = districtView.getGlobaldistrictId();
		try {
			List<DistrictDataForm> listDistrictDetails = districtService.getDistrictDetailsModify(districtCode);
			if (listDistrictDetails.size() > 0) {
				// Add Govt order details and the view map option
				for (int i = 0; i < listDistrictDetails.size(); i++) {
					if (listDistrictDetails.get(i).getOrderCodecr() != null) {
						int orderValue = listDistrictDetails.get(i).getOrderCodecr();
						if (orderValue > 0) {
							/*
							 * Retrieving the Map details attachment from the
							 * database
							 */

							/*
							 * List<EntityWiseMapDetailsPojo>
							 * EntityWiseMapDetails = districtService
							 * .getEntityWiseMapDetails('D', districtCode);
							 */
							// List<EntityWiseMapDetailsPojo>
							// EntityWiseMapDetails =
							// districtService.getEntityWiseMapDetails('D',86);
							/*
							 * if (!EntityWiseMapDetails.isEmpty()) {
							 * Iterator<EntityWiseMapDetailsPojo> itrMap =
							 * EntityWiseMapDetails .iterator(); while
							 * (itrMap.hasNext()) { EntityWiseMapDetailsPojo
							 * mapDetails = (EntityWiseMapDetailsPojo) itrMap
							 * .next();
							 * 
							 * String path = mapDetails.getImagePath(); String
							 * fileName = new File(path).getName();
							 * model.addAttribute("fileName", fileName);
							 * System.out.println("Inside the entitycode" +
							 * mapDetails.getEntityCode()); }
							 * model.addAttribute("mapAttachmentList",
							 * EntityWiseMapDetails);
							 */

							List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
							mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(districtCode, 'D');

							if (mapAttachmentList.size() > 0) {

								model.addAttribute("mapAttachmentList", mapAttachmentList);
							} else {
								mav = new ModelAndView("globalview_districtdetail");
								result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
								districtView.setListDistrictDetails(listDistrictDetails);
							}
							/*
							 * Retrieving the Govt order details attachment from
							 * the database
							 */

							List<Attachment> attachmentList = districtService.getAttachmentsbyOrderCode(orderValue);
							// List<Attachment> attachmentList =
							// districtService.getAttachmentsbyOrderCode(200);
							if (!attachmentList.isEmpty()) {
								mav = new ModelAndView("globalview_districtdetail");
								model.addAttribute("attachmentList", attachmentList);
								districtView.setListDistrictDetails(listDistrictDetails);
								return mav;
							} else {
								mav = new ModelAndView("globalview_districtdetail");
								result.rejectValue("noAttachmentRecord", "Error.NOATTACHMENTDETAIL");
								districtView.setListDistrictDetails(listDistrictDetails);
								return mav;
							}
						} else {
							mav = new ModelAndView("globalview_districtdetail");
							result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
							districtView.setListDistrictDetails(listDistrictDetails);
							return mav;
						}
					} else {
						mav = new ModelAndView("globalview_districtdetail");
						result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
						/*
						 * Retrieving the Map details attachment from the
						 * database
						 */

						List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
						mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(districtCode, 'D');

						if (mapAttachmentList.size() > 0) {

							model.addAttribute("mapAttachmentList", mapAttachmentList);
						} else {
							mav = new ModelAndView("globalview_districtdetail");
							result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
							districtView.setListDistrictDetails(listDistrictDetails);
							return mav;
						}
						districtView.setListDistrictDetails(listDistrictDetails);
						return mav;
					}
				}
			} else {
				mav = new ModelAndView("globalview_districtdetail");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}		
	
		// Shifted to VillageController
		/*@RequestMapping(value = "/viewvillage", method = RequestMethod.GET)
		public ModelAndView showVillagePage(Model model,
				HttpServletRequest request, HttpSession httpSession) {
			ModelAndView mav = null;
			try {
				mav = new ModelAndView("view_village");
				VillageDataForm villageDataForm = new VillageDataForm();
				villageDataForm.setStateNameEnglish(httpSession.getAttribute(
						"stateCode").toString());
				mav.addObject("villagebean", villageDataForm);
				request.setAttribute("stateCode", httpSession.getAttribute(
						"stateCode").toString());
				model.addAttribute("viewPage", "");
				model.addAttribute("style0", "visibility: hidden; display: none;");
				model.addAttribute("style1", "visibility: hidden; display: none;");		
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

		@RequestMapping(value = "/viewvillage", method = RequestMethod.POST)
		public ModelAndView getVillagePage(
				@ModelAttribute("villagebean") VillageDataForm villagebean,
				BindingResult result, HttpServletRequest request, Model model,
				HttpSession httpSession) {  
			ModelAndView mav = null;
			try {
				if(!(villagebean.getDistrictNameEnglish()==null) && !(villagebean.getSubdistrictNameEnglish()==null))
				{	
					comboFillingService.getComboValuesforApp('D', "S", Integer.parseInt(httpSession.getAttribute("stateCode").toString()), Integer.parseInt(villagebean.getDistrictNameEnglish()));
					comboFillingService.getComboValuesforApp('T', "D", Integer.parseInt(villagebean.getDistrictNameEnglish()), Integer.parseInt(villagebean.getSubdistrictNameEnglish()));
				}
				char EntityType = ' ';
				String statecode = null;
	            String filterValue = request.getParameter("filterOption");
				villageValidator.viewValidateVillage(villagebean, result);
				if (result.hasErrors()) {
					result.getAllErrors();
					// mav.addObject("districtbean", districtbean);
					mav = new ModelAndView("view_village");
					return mav;
				} else {
					if (villagebean.getPageType().equals("V")) {
						mav = new ModelAndView("view_village");
						EntityType = 'V';
						statecode = (String) httpSession.getAttribute("stateCode");
					} else if (villagebean.getPageType().equals("CV")) {
						mav = new ModelAndView("view_cvillage");
						EntityType = 'V';
					}

					Integer villageCode = null;

					if (villagebean.getCode().length() > 0
							&& !villagebean.getCode().isEmpty()
							&& villagebean.getCode() != null) {

						villageCode = Integer.parseInt(villagebean.getCode());
					}
					String villageName = villagebean.getVillageNameEnglish();
					String subDistrictCode = villagebean
							.getSubdistrictNameEnglish();
					System.out.println("subDistrictCode : " + subDistrictCode);
					// //////////////////////////////////////////According to State
					// Code///////////////////////////

					List<StateWiseEntityDetails> stateWiseEntityDetails = null;

					httpSession.setAttribute("limit", villagebean.getLimit());
					villagebean.setOffset(1);
					httpSession.setAttribute("offset", villagebean.getOffset());
					httpSession.setAttribute("villageCode", villageCode);
					httpSession.setAttribute("villageName", villageName);
					httpSession.setAttribute("subDistrictCode", subDistrictCode);

					if (subDistrictCode != null && !subDistrictCode.equals("")) {

						System.out.println("districtCode :: " + subDistrictCode);
						stateWiseEntityDetails = villageService.getParentWiseList(
								'T', Integer.parseInt(subDistrictCode), null, null);
						httpSession.setAttribute("counter",
								stateWiseEntityDetails.size());

						stateWiseEntityDetails = villageService.getParentWiseList(
								'T',
								Integer.parseInt(subDistrictCode),
								httpSession.getAttribute("limit") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("limit").toString()),
								0);

					} else {
						stateWiseEntityDetails = villageService
								.getStateWiseVillageList(
										statecode == null ? null : Integer
												.parseInt(httpSession.getAttribute(
														"stateCode").toString()),
										EntityType, villageCode, villageName, null,
										null);
						httpSession.setAttribute("counter",
								stateWiseEntityDetails.size());

						stateWiseEntityDetails = villageService
								.getStateWiseVillageList(
										statecode == null ? null : Integer
												.parseInt(httpSession.getAttribute(
														"stateCode").toString()),
										EntityType,
										httpSession.getAttribute("villageCode") == null ? null
												: Integer
														.parseInt(httpSession
																.getAttribute(
																		"villageCode")
																.toString()),
										httpSession.getAttribute("villageName") == null ? null
												: httpSession.getAttribute(
														"villageName").toString(),
										httpSession.getAttribute("limit") == null ? null
												: Integer.parseInt(httpSession
														.getAttribute("limit")
														.toString()), 0);
					}
					httpSession.setAttribute("SEARCH_VILLAGE_RESULTS_KEY", stateWiseEntityDetails);
					model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY",
							stateWiseEntityDetails);
					villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
					model.addAttribute("viewPage", "abc");
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
													.getAttribute("limit")
													.toString()) + 1));
					villagebean.setPageRows(Integer.parseInt(httpSession
							.getAttribute("limit").toString()));
					model.addAttribute("filterValue", filterValue);
					model.addAttribute("districtId", villagebean.getDistrictNameEnglish());
					model.addAttribute("subDistrictId", villagebean.getSubdistrictNameEnglish());
					if(filterValue.equals("0")){
						model.addAttribute("style0", "visibility: visible; display: block;");
						model.addAttribute("style1", "visibility: hidden; display: none;");	
					
					}
					
					if(filterValue.equals("1")){
						model.addAttribute("style0", "visibility: hidden; display: none;");
						model.addAttribute("style1", "visibility: visible; display: block;");	
					}
					
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

			return mav;
		}*/
	
	

	/*@RequestMapping(value = "/globalviewvillage", method = RequestMethod.POST)
	public ModelAndView getglobalVillagePage(
			@ModelAttribute("villagebean") VillageDataForm villagebean,
			BindingResult result, HttpServletRequest request, Model model,
			HttpSession httpSession) {
		ModelAndView mav = null;
		httpSession.setAttribute("redirectVillageBean", villagebean);
		try {
			mav = new ModelAndView("globalview_cvillage");
			String captchaAnswer = villagebean.getCaptchaAnswer();
			String captchaAnswers = villagebean.getCaptchaAnswers();
		      	
			villagebean.setStateNameEnglish(null);
		      	CaptchaValidator captchaValidator = new CaptchaValidator();
		      	boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
		    	boolean messageFlag2 = captchaValidator.validateCaptcha(httpSession, captchaAnswers);
		      	//System.out.println("============messageFlag============:"+messageFlag);
		    	if(messageFlag == false && messageFlag2 == false) {
		      		if(captchaAnswer!="")
		      		{
		      					mav.addObject("flag1",1);
		      					mav.addObject("captchaSuccess1", messageFlag);
		      		}
		      		else
		      		{
		      			mav.addObject("flag2",2);
		      		mav.addObject("captchaSuccess2", messageFlag);
		      		}
		      		villagebean.setCaptchaAnswer(null);
		      		villagebean.setCaptchaAnswers(null);
		      		return mav;
		      	}
		      	else
		
		      	{			char EntityType = ' ';
			String statecode = null;

			villageValidator.viewValidateVillage(villagebean, result);
			if (result.hasErrors()) {
				result.getAllErrors();
				mav = new ModelAndView("globalview_village");
				villagebean.setCaptchaAnswer(null);
				villagebean.setCaptchaAnswers(null);
				return mav;
			} else {
				if (villagebean.getPageType().equals("V")) {
					mav = new ModelAndView("globalview_village");
					EntityType = 'V';
					statecode = (String) httpSession.getAttribute("stateCode");
				} else if (villagebean.getPageType().equals("CV")) {
					mav = new ModelAndView("globalview_cvillage");
					EntityType = 'V';
				}

				Integer villageCode = null;

				if (villagebean.getCode().length() > 0
						&& !villagebean.getCode().isEmpty()
						&& villagebean.getCode() != null) {

					villageCode = Integer.parseInt(villagebean.getCode());
				}
				String villageName = villagebean.getVillageNameEnglish();
				String subDistrictCode = villagebean
						.getSubdistrictNameEnglish();
				//System.out.println("subDistrictCode : " + subDistrictCode);
				List<StateWiseEntityDetails> stateWiseEntityDetails = null;
				httpSession.setAttribute("limit", villagebean.getLimit());
				villagebean.setOffset(1);
				httpSession.setAttribute("offset", villagebean.getOffset());
				httpSession.setAttribute("villageCode", villageCode);
				httpSession.setAttribute("villageName", villageName);
				httpSession.setAttribute("subDistrictCode", subDistrictCode);

				if (subDistrictCode != null && !subDistrictCode.equals("")) {

					//System.out.println("districtCode :: " + subDistrictCode);
					stateWiseEntityDetails = villageService.getParentWiseList(
							'T', Integer.parseInt(subDistrictCode), null, null);
					httpSession.setAttribute("counter",
							(stateWiseEntityDetails!=null && !stateWiseEntityDetails.isEmpty()?stateWiseEntityDetails.size():0));

					stateWiseEntityDetails = villageService.getParentWiseList(
							'T',
							Integer.parseInt(subDistrictCode),
							httpSession.getAttribute("limit") == null ? null
									: Integer.parseInt(httpSession
											.getAttribute("limit").toString()),
							0);

				} else {
					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									statecode == null ? null : Integer
											.parseInt(httpSession.getAttribute(
													"stateCode").toString()),
									EntityType, villageCode, villageName, null,
									null);
					httpSession.setAttribute("counter",
							(stateWiseEntityDetails!=null && !stateWiseEntityDetails.isEmpty()?stateWiseEntityDetails.size():0));

					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									statecode == null ? null : Integer
											.parseInt(httpSession.getAttribute(
													"stateCode").toString()),
									EntityType,
									httpSession.getAttribute("villageCode") == null ? null
											: Integer
													.parseInt(httpSession
															.getAttribute(
																	"villageCode")
															.toString()),
									httpSession.getAttribute("villageName") == null ? null
											: httpSession.getAttribute(
													"villageName").toString(),
									httpSession.getAttribute("limit") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("limit")
													.toString()), 0);
				}
				
				if(subDistrictCode!=null && subDistrictCode!="")
				{
					List<LandRegionDetail> landregionDetail=null;
					landregionDetail=new ArrayList<LandRegionDetail>();
					landregionDetail=downloadDirectoryService.landRegionDetail('T', Integer.parseInt(subDistrictCode));
					if(landregionDetail.size()>0){
						model.addAttribute("message","Villages of "+landregionDetail.get(0).getSubdistrictNameEnglish()+" Sub District("+landregionDetail.get(0).getDistrictNameEnglish()+"-"+landregionDetail.get(0).getStateNameEnglish()+")");
					}	
				}
				
				
				httpSession.setAttribute("SEARCH_VILLAGE_RESULTS_KEY", stateWiseEntityDetails);
				model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY",
						stateWiseEntityDetails);
				villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
				model.addAttribute("viewPage", "abc");
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
												.getAttribute("limit")
												.toString()) + 1));
				villagebean.setPageRows(Integer.parseInt(httpSession
						.getAttribute("limit").toString()));
				model.addAttribute("display",true);
			}
		}} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			villagebean.setCaptchaAnswer(null);
			villagebean.setCaptchaAnswers(null);
			return mav;
		}
		villagebean.setCaptchaAnswer(null);
		villagebean.setCaptchaAnswers(null);
		return mav;
	}*/
	
	// Shifted to VillageController
		/*@RequestMapping(value = "/viewVillagePagination", method = RequestMethod.POST)
		public ModelAndView getVillagePagination(
				@ModelAttribute("villagebean") VillageDataForm villagebean,
				HttpServletRequest request, Model model, HttpSession httpSession) {
			ModelAndView mav = null;
			try {
				char EntityType = ' ';

				if (villagebean.getPageType().equals("V")) {
					mav = new ModelAndView("view_village");
					EntityType = 'V';
				} else if (villagebean.getPageType().equals("CV")) {
					mav = new ModelAndView("view_cvillage");
					EntityType = 'V';
				}

				if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer
						.parseInt(httpSession.getAttribute("counter").toString())
						/ Integer.parseInt(httpSession.getAttribute("limit")
								.toString()) + 1)
						&& Integer.parseInt(httpSession.getAttribute("offset")
								.toString()) + villagebean.getDirection() > 0) {
					villagebean.setOffset(Integer.parseInt(httpSession
							.getAttribute("offset").toString())
							+ villagebean.getDirection());
					httpSession.setAttribute("offset", villagebean.getOffset());
				} else if (Integer.parseInt(httpSession.getAttribute("offset")
						.toString()) == (Integer.parseInt(httpSession.getAttribute(
						"counter").toString())
						/ Integer.parseInt(httpSession.getAttribute("limit")
								.toString()) + 1)
						&& villagebean.getDirection() == -1
						&& Integer.parseInt(httpSession.getAttribute("offset")
								.toString()) + villagebean.getDirection() > 0) {
					villagebean.setOffset(Integer.parseInt(httpSession
							.getAttribute("offset").toString())
							+ villagebean.getDirection());
					httpSession.setAttribute("offset", villagebean.getOffset());
				}

				List<StateWiseEntityDetails> stateWiseEntityDetails = null;

				if (httpSession.getAttribute("subDistrictCode") != null
						&& !httpSession.getAttribute("subDistrictCode").equals("")) {

					System.out.println("districtCode :: "
							+ httpSession.getAttribute("subDistrictCode"));
					stateWiseEntityDetails = villageService.getParentWiseList(
							'T',
							Integer.parseInt(httpSession.getAttribute(
									"subDistrictCode").toString()), null, null);
					httpSession.setAttribute("counter",
							stateWiseEntityDetails.size());

					stateWiseEntityDetails = villageService.getParentWiseList(
							'T',
							Integer.parseInt(httpSession.getAttribute(
									"subDistrictCode").toString()),
							httpSession.getAttribute("limit") == null ? null
									: Integer.parseInt(httpSession.getAttribute(
											"limit").toString()),
							Integer.parseInt(httpSession.getAttribute("limit")
									.toString())
									* (Integer.parseInt(httpSession.getAttribute(
											"offset").toString()) - 1));

				} else {
					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									httpSession.getAttribute("stateCode") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("stateCode")
													.toString()),
									EntityType,
									httpSession.getAttribute("villageCode") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("villageCode")
													.toString()),
									httpSession.getAttribute("villageName") == null ? null
											: httpSession.getAttribute(
													"villageName").toString(),
									null, null);
					httpSession.setAttribute("counter",
							stateWiseEntityDetails.size());

					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									httpSession.getAttribute("stateCode") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("stateCode")
													.toString()),
									EntityType,
									httpSession.getAttribute("villageCode") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("villageCode")
													.toString()),
									httpSession.getAttribute("villageName") == null ? null
											: httpSession.getAttribute(
													"villageName").toString(),
									httpSession.getAttribute("limit") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("limit")
													.toString()),
									Integer.parseInt(httpSession.getAttribute(
											"limit").toString())
											* (Integer.parseInt(httpSession
													.getAttribute("offset")
													.toString()) - 1));
				}

				model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY",
						stateWiseEntityDetails);
				villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
				model.addAttribute("viewPage", "abc");
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
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}

			return mav;
		}*/
	
	@RequestMapping(value = "/globalviewVillagePagination",method = RequestMethod.POST)
	public ModelAndView getGlobalVillagePagination(
			@ModelAttribute("villagebean") VillageDataForm villagebean, Model model,
			HttpServletRequest request,HttpSession httpSession)
	{
		ModelAndView mav = null;
		try {
			char EntityType = ' ';

			if (villagebean.getPageType().equals("V")) {
				mav = new ModelAndView("globalview_village");
				EntityType = 'V';
			} else if (villagebean.getPageType().equals("CV")) {
				mav = new ModelAndView("globalview_cvillage");
				EntityType = 'V';
			}

			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer
					.parseInt(httpSession.getAttribute("counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset")
					.toString()) == (Integer.parseInt(httpSession.getAttribute(
					"counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& villagebean.getDirection() == -1
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			}

			List<StateWiseEntityDetails> stateWiseEntityDetails = null;

			if (httpSession.getAttribute("subDistrictCode") != null
					&& !httpSession.getAttribute("subDistrictCode").equals("")) {

				//System.out.println("districtCode :: "
				//		+ httpSession.getAttribute("subDistrictCode"));
				stateWiseEntityDetails = villageService.getParentWiseList(
						'T',
						Integer.parseInt(httpSession.getAttribute(
								"subDistrictCode").toString()), null, null);
				httpSession.setAttribute("counter",
						stateWiseEntityDetails.size());

				stateWiseEntityDetails = villageService.getParentWiseList(
						'T',
						Integer.parseInt(httpSession.getAttribute(
								"subDistrictCode").toString()),
						httpSession.getAttribute("limit") == null ? null
								: Integer.parseInt(httpSession.getAttribute(
										"limit").toString()),
						Integer.parseInt(httpSession.getAttribute("limit")
								.toString())
								* (Integer.parseInt(httpSession.getAttribute(
										"offset").toString()) - 1));

			} else {
				stateWiseEntityDetails = villageService
						.getStateWiseVillageList(
								httpSession.getAttribute("stateCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("stateCode")
												.toString()),
								EntityType,
								httpSession.getAttribute("villageCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("villageCode")
												.toString()),
								httpSession.getAttribute("villageName") == null ? null
										: httpSession.getAttribute(
												"villageName").toString(),
								null, null);
				httpSession.setAttribute("counter",
						stateWiseEntityDetails.size());

				stateWiseEntityDetails = villageService
						.getStateWiseVillageList(
								httpSession.getAttribute("stateCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("stateCode")
												.toString()),
								EntityType,
								httpSession.getAttribute("villageCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("villageCode")
												.toString()),
								httpSession.getAttribute("villageName") == null ? null
										: httpSession.getAttribute(
												"villageName").toString(),
								httpSession.getAttribute("limit") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("limit")
												.toString()),
								Integer.parseInt(httpSession.getAttribute(
										"limit").toString())
										* (Integer.parseInt(httpSession
												.getAttribute("offset")
												.toString()) - 1));
			}
			/*added by pooja on 06-07-2015 to display heading msg in pagination*/
			String subDistrictCode = (String)httpSession.getAttribute("subDistrictCode");
			if(subDistrictCode!=null && subDistrictCode!="")
			{
				List<LandRegionDetail> landregionDetail=null;
				landregionDetail=new ArrayList<LandRegionDetail>();
				landregionDetail=reportService.landRegionDetail('T', Integer.parseInt(subDistrictCode));
				if(landregionDetail.size()>0){
					model.addAttribute("message","Villages of "+landregionDetail.get(0).getSubdistrictNameEnglish()+" Sub District("+landregionDetail.get(0).getDistrictNameEnglish()+"-"+landregionDetail.get(0).getStateNameEnglish()+")");
				}	
			}
			httpSession.setAttribute("SEARCH_VILLAGE_RESULTS_KEY", stateWiseEntityDetails);
			model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY",
					stateWiseEntityDetails);
			villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
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
			villagebean.setPageRows(Integer.parseInt(httpSession
					.getAttribute("limit").toString()));
			model.addAttribute("display",true);
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

	

	// Shifted to VillageController
		/*@RequestMapping(value = "/viewvillagebysubdistrictCode", method = RequestMethod.GET)
		public ModelAndView getVillagesbySubdistrict(HttpSession session,
				@ModelAttribute("villagebean") VillageDataForm villagebean,
				HttpServletRequest request, Model model,
				@RequestParam("id") Integer subdistrictCode) {

			ModelAndView mav = null;
			try {
				mav = new ModelAndView("view_village");
				// int recordsLimit=villagebean.getRecordsLimit();

				String query = "from Village d where d.subdistrict.subdistrictPK.subdistrictCode='"
						+ subdistrictCode
						+ "'and d.isactive=true order by villageNameEnglish";
				List<Village> listVillageDetail = null;

				listVillageDetail = new ArrayList<Village>();

				listVillageDetail = villageService.getVillageViewList(query,
						villagebean.getPageRows(), 0);

				model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY", listVillageDetail);
				villagebean.setListVillageDetail(listVillageDetail);
				mav.addObject("villagebean", villagebean);
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
	 	
	 	

		@ModelAttribute("districtList")
		public List<District> getDistrictList(HttpSession session,
				HttpServletRequest request) {
			try {
				int stateCode = getStateCode(session);
				return districtService.getDistrictList(stateCode);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				// mav = new ModelAndView(redirectPath);
				return null;
			}
		}

		// View Village Detail
		@RequestMapping(value = "/viewVillageDetail")
		public ModelAndView viewVillageDetail(
				@ModelAttribute("villageView") VillageForm villageView,
				HttpServletRequest request, Model model)
		{
			ModelAndView mav = null;
			
			String id = request.getParameter("id");
			int villageCode=(villageView.getVillageId()==null)?Integer.parseInt(id):villageView.getVillageId();
			//int villageCode=villageView.getVillageId();
			try {
				List<VillageDataForm> listVillageDetails = villageService
						.getVillageDetailsModify(villageCode);
				mav = new ModelAndView("view_villagedetail");
				villageView.setListVillageDetails(listVillageDetails);
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
	*/
	/*// View Village Detail
		@RequestMapping(value = "/globalviewVillageDetail")
		public ModelAndView globalviewVillageDetail(
				@ModelAttribute("villageView") VillageForm villageView,
				HttpServletRequest request, Model model)
		{
			ModelAndView mav = null;
			int villageCode=villageView.getGlobalvillageId();
			try {
				List<VillageDataForm> listVillageDetails = villageService
						.getVillageDetailsModify(villageCode);
				mav = new ModelAndView("globalview_villagedetail");
				villageView.setListVillageDetails(listVillageDetails);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}

			return mav;
		}*/
	
	//Added 2 columns for the government order details and the view map
	// View Village Detail
	@RequestMapping(value = "/globalviewVillageDetail")
	public ModelAndView globalviewVillageDetail(@ModelAttribute("villageView") VillageForm villageView, BindingResult result, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		String id = request.getParameter("id");
		int villageCode = villageView.getGlobalvillageId() == null ? Integer.parseInt(id) : villageView.getGlobalvillageId();
		try {
			List<VillageDataForm> listVillageDetails = villageService.getVillageDetailsModify(villageCode);
			if (listVillageDetails.size() > 0) {
				// Add Govt order details and the view map option
				for (int i = 0; i < listVillageDetails.size(); i++) {
					if (listVillageDetails.get(i).getOrderCodecr() != null) {
						int orderValue = listVillageDetails.get(i).getOrderCodecr();
						if (orderValue > 0) {
							/*
							 * Retrieving the Map details attachment from the
							 * database
							 */

							/*
							 * List<EntityWiseMapDetailsPojo>
							 * EntityWiseMapDetails = villageService
							 * .getEntityWiseMapDetails('V', villageCode);
							 */
							// List<EntityWiseMapDetailsPojo>
							// EntityWiseMapDetails =
							// villageService.getEntityWiseMapDetails('V',27462);
							/*
							 * if (!EntityWiseMapDetails.isEmpty()) {
							 * Iterator<EntityWiseMapDetailsPojo> itrMap =
							 * EntityWiseMapDetails .iterator(); while
							 * (itrMap.hasNext()) { EntityWiseMapDetailsPojo
							 * mapDetails = (EntityWiseMapDetailsPojo) itrMap
							 * .next();
							 * 
							 * String path = mapDetails.getImagePath(); String
							 * fileName = new File(path).getName();
							 * model.addAttribute("fileName", fileName);
							 * System.out.println("Inside the entitycode" +
							 * mapDetails.getEntityCode()); }
							 * model.addAttribute("mapAttachmentList",
							 * EntityWiseMapDetails);
							 */
							List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
							mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(villageCode, 'V');

							if (mapAttachmentList.size() > 0) {

								model.addAttribute("mapAttachmentList", mapAttachmentList);
							} else {
								mav = new ModelAndView("globalview_villagedetail");
								result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
								villageView.setListVillageDetails(listVillageDetails);
							}
							/*
							 * Retrieving the Govt order details attachment from
							 * the database
							 */

							List<Attachment> attachmentList = villageService.getAttachmentsbyOrderCode(orderValue);
							// List<Attachment> attachmentList =
							// villageService.getAttachmentsbyOrderCode(200);
							if (!attachmentList.isEmpty()) {
								mav = new ModelAndView("globalview_villagedetail");
								model.addAttribute("attachmentList", attachmentList);
								villageView.setListVillageDetails(listVillageDetails);
								return mav;
							} else {
								mav = new ModelAndView("globalview_villagedetail");
								result.rejectValue("noAttachmentRecord", "Error.NOATTACHMENTDETAIL");
								villageView.setListVillageDetails(listVillageDetails);
								return mav;
							}
						} else {
							mav = new ModelAndView("globalview_villagedetail");
							result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
							villageView.setListVillageDetails(listVillageDetails);
							return mav;
						}
					} else {
						mav = new ModelAndView("globalview_villagedetail");
						result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
						/*
						 * Retrieving the Map details attachment from the
						 * database
						 */

						List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
						mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(villageCode, 'V');

						if (mapAttachmentList.size() > 0) {

							model.addAttribute("mapAttachmentList", mapAttachmentList);
						} else {
							mav = new ModelAndView("globalview_villagedetail");
							result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
							villageView.setListVillageDetails(listVillageDetails);
							return mav;
						}
						villageView.setListVillageDetails(listVillageDetails);
						return mav;
					}
				}
			} else {
				mav = new ModelAndView("globalview_villagedetail");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
		
		// Shifted to VillageController
		/*@RequestMapping(value = "/viewVillageHistory")
		public ModelAndView viewVillageHistoryDetail(
				@ModelAttribute("villageForm") VillageForm villageForm,
				HttpServletRequest request, Model model)
		{

			ModelAndView mav = null;
			int villageCode=villageForm.getVillageId();
			try {
				mav = new ModelAndView("view_villagehistory");
				char villageNameEnglish = 'V';
				List<VillageHistory> villageHistoryDetail = villageService
						.getVillageHistoryDetail(villageNameEnglish, villageCode);
				model.addAttribute("villageHistory", villageHistoryDetail);
				villageForm.setVillageHistoryDetail(villageHistoryDetail);
				mav.addObject("villageForm", villageForm);
				model.addAttribute("viewPage", "abc");
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}

			return mav;
		}*/

	// View Global Village History
	@RequestMapping(value = "/globalviewVillageHistory", method = RequestMethod.GET)
	public ModelAndView viewVillageHistoryDetailGlobal(
			@ModelAttribute("villageForm") VillageForm villageForm,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer villageCode) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("globalview_villagehistory");
			char villageNameEnglish = 'V';
			List<VillageHistory> villageHistoryDetail = villageService
					.getVillageHistoryDetail(villageNameEnglish, villageCode);
			model.addAttribute("villageHistory", villageHistoryDetail);
			villageForm.setVillageHistoryDetail(villageHistoryDetail);
			mav.addObject("villageForm", villageForm);
			model.addAttribute("viewPage", "abc");
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
	
	// View SubDistrict Part
	/*@RequestMapping(value = "/viewsubdistrict", method = RequestMethod.GET)
	public ModelAndView showSubDistrictPage(Model model,
			HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_subdistrict");
			mav.addObject("subdistrictbean", new SubDistrictForm());
			model.addAttribute("viewPage", "");
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

	@RequestMapping(value = "/viewsubdistrict", method = RequestMethod.POST)
	public ModelAndView getSubDistrictPage(
			@ModelAttribute("subdistrictbean") SubDistrictForm subdistrictbean,
			BindingResult result, HttpServletRequest request, Model model,
			HttpSession httpSession) {

		ModelAndView mav = null;
		try {
			String statecode = (String) httpSession.getAttribute("stateCode");
			stateCode = Integer.parseInt(statecode);
		//	if(!httpSession.getAttribute("stateCode").equals(""))
			if(!subdistrictbean.getDistrictNameEnglish().equals(""))	
			{	
				comboFillingService.getComboValuesforApp('D', "S", Integer.parseInt(httpSession.getAttribute("stateCode").toString()), Integer.parseInt(subdistrictbean.getDistrictNameEnglish()));
			}
			//comboFillingService.getComboValuesforApp('T', "D", Integer.parseInt(subdistrictbean.getDistrictNameEnglish()), Integer.parseInt(subdistrictbean.getSubdistrictNameEnglish()));
			char EntityType = ' ';
			String statecode = null;

			subDistrictValidator.viewValidateSubDistrict(subdistrictbean,
					result);
			if (result.hasErrors()) {
				result.getAllErrors();
				// mav.addObject("districtbean", districtbean);
				mav = new ModelAndView("view_subdistrict");
				return mav;
			} else {

				if (subdistrictbean.getPageType().equals("T")) {
					mav = new ModelAndView("view_subdistrict");
					EntityType = 'T';
					statecode = (String) httpSession.getAttribute("stateCode");
				} else if (subdistrictbean.getPageType().equals("CT")) {
					mav = new ModelAndView("view_csubdistrict");
					EntityType = 'T';
				}

				Integer subDistricteCode = null;

				if (subdistrictbean.getCode().length() > 0
						&& !subdistrictbean.getCode().isEmpty()
						&& subdistrictbean.getCode() != null) {

					subDistricteCode = Integer.parseInt(subdistrictbean
							.getCode());
				}
				String subDistrictName = subdistrictbean
						.getSubdistrictNameEnglish();

				String districtCode = subdistrictbean.getDistrictNameEnglish(); // districtCode

				// //////////////////////////////////////////According to State
				// Code///////////////////////////

				List<StateWiseEntityDetails> stateWiseEntityDetails = null;

				httpSession.setAttribute("limit", subdistrictbean.getLimit());
				subdistrictbean.setOffset(1);
				httpSession.setAttribute("offset", subdistrictbean.getOffset());
				httpSession.setAttribute("villageCode", subDistricteCode);
				httpSession.setAttribute("villageName", subDistrictName);
				httpSession.setAttribute("districtCode", districtCode);

				if (!districtCode.equals("")) {

					System.out.println("districtCode :: " + districtCode);
					stateWiseEntityDetails = villageService.getParentWiseList(
							'D', Integer.parseInt(districtCode), null, null);
					httpSession.setAttribute("counter",
							stateWiseEntityDetails.size());

					stateWiseEntityDetails = villageService.getParentWiseList(
							'D',
							Integer.parseInt(districtCode),
							httpSession.getAttribute("limit") == null ? null
									: Integer.parseInt(httpSession
											.getAttribute("limit").toString()),
							0);

				} else {
					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									statecode == null ? null : Integer
											.parseInt(httpSession.getAttribute(
													"stateCode").toString()),
									EntityType, subDistricteCode,
									subDistrictName, null, null);
					httpSession.setAttribute("counter",
							stateWiseEntityDetails.size());

					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									statecode == null ? null : Integer
											.parseInt(httpSession.getAttribute(
													"stateCode").toString()),
									EntityType,
									httpSession.getAttribute("villageCode") == null ? null
											: Integer
													.parseInt(httpSession
															.getAttribute(
																	"villageCode")
															.toString()),
									httpSession.getAttribute("villageName") == null ? null
											: httpSession.getAttribute(
													"villageName").toString(),
									httpSession.getAttribute("limit") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("limit")
													.toString()), 0);
				}

				model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",
						stateWiseEntityDetails);
				subdistrictbean
						.setStateWiseEntityDetails(stateWiseEntityDetails);
				model.addAttribute("viewPage", "abc");
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
												.getAttribute("limit")
												.toString()) + 1));
				subdistrictbean.setPageRows(Integer.parseInt(httpSession
						.getAttribute("limit").toString()));
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

		return mav;
	}
*/
	/*@RequestMapping(value = "/globalviewsubdistrict", method = RequestMethod.POST)
	public ModelAndView getglobalSubDistrictPage(
			@ModelAttribute("subdistrictbean") SubDistrictForm subdistrictbean,
			BindingResult result, HttpServletRequest request, Model model,
			HttpSession httpSession) {
		
	ModelAndView mav = null;
	httpSession.setAttribute("redirectSubDistrictBean", subdistrictbean);
		
		try {
			mav = new ModelAndView("globalview_csubdistrict");
			String captchaAnswer = subdistrictbean.getCaptchaAnswer();
			String captchaAnswers = subdistrictbean.getCaptchaAnswers();
		      	
		      	
		      	CaptchaValidator captchaValidator = new CaptchaValidator();
		      	boolean messageFlag = captchaValidator.validateCaptcha(httpSession, captchaAnswer);
		    	boolean messageFlag2 = captchaValidator.validateCaptcha(httpSession, captchaAnswers);
		    	subdistrictbean.setStateNameEnglish(null);
		      	//System.out.println("============messageFlag============:"+messageFlag);
		    	if(messageFlag == false && messageFlag2 == false) {
		      	 		if(captchaAnswer!="")
		      		{
		      					mav.addObject("flag1",1);
		      					mav.addObject("captchaSuccess1", messageFlag);
		      		}
		      		else
		      		{
		      			mav.addObject("flag2",2);
		      		mav.addObject("captchaSuccess2", messageFlag);
		      		}
		      		
		      		subdistrictbean.setCaptchaAnswer(null);
		      		subdistrictbean.setCaptchaAnswers(null);
		      		return mav;
		      	}
		      	else
		      	{
			
			char EntityType = ' ';
			String statecode = null;

			subDistrictValidator.viewValidateSubDistrict(subdistrictbean,
					result);
			if (result.hasErrors()) {
				result.getAllErrors();
								mav = new ModelAndView("globalview_subdistrict");
				return mav;
			} else {

				if (subdistrictbean.getPageType().equals("T")) {
					mav = new ModelAndView("globalview_subdistrict");
					EntityType = 'T';
					statecode = (String) httpSession.getAttribute("stateCode");
				} else if (subdistrictbean.getPageType().equals("CT")) {
					mav = new ModelAndView("globalview_csubdistrict");
					EntityType = 'T';
				}

				Integer subDistricteCode = null;

				if (subdistrictbean.getCode().length() > 0
						&& !subdistrictbean.getCode().isEmpty()
						&& subdistrictbean.getCode() != null) {

					subDistricteCode = Integer.parseInt(subdistrictbean
							.getCode());
				}
				String subDistrictName = subdistrictbean
						.getSubdistrictNameEnglish();

				String districtCode = subdistrictbean.getDistrictNameEnglish(); // districtCode
				List<StateWiseEntityDetails> stateWiseEntityDetails = null;

				httpSession.setAttribute("limit", subdistrictbean.getLimit());
				subdistrictbean.setOffset(1);
				httpSession.setAttribute("offset", subdistrictbean.getOffset());
				httpSession.setAttribute("villageCode", subDistricteCode);
				httpSession.setAttribute("villageName", subDistrictName);
				httpSession.setAttribute("districtCode", districtCode);

				if (!districtCode.equals("")) {

					//System.out.println("districtCode :: " + districtCode);
					stateWiseEntityDetails = villageService.getParentWiseList(
							'D', Integer.parseInt(districtCode), null, null);
					httpSession.setAttribute("counter",
							(stateWiseEntityDetails!=null && !stateWiseEntityDetails.isEmpty()?stateWiseEntityDetails.size():0));

					stateWiseEntityDetails = villageService.getParentWiseList(
							'D',
							Integer.parseInt(districtCode),
							httpSession.getAttribute("limit") == null ? null
									: Integer.parseInt(httpSession
											.getAttribute("limit").toString()),
							0);

				} else {
					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									statecode == null ? null : Integer
											.parseInt(httpSession.getAttribute(
													"stateCode").toString()),
									EntityType, subDistricteCode,
									subDistrictName, null, null);
					httpSession.setAttribute("counter",
							(stateWiseEntityDetails!=null && !stateWiseEntityDetails.isEmpty()?stateWiseEntityDetails.size():0));

					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									statecode == null ? null : Integer
											.parseInt(httpSession.getAttribute(
													"stateCode").toString()),
									EntityType,
									httpSession.getAttribute("villageCode") == null ? null
											: Integer
													.parseInt(httpSession
															.getAttribute(
																	"villageCode")
															.toString()),
									httpSession.getAttribute("villageName") == null ? null
											: httpSession.getAttribute(
													"villageName").toString(),
									httpSession.getAttribute("limit") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("limit")
													.toString()), 0);
				}
				
				if(districtCode!=null && districtCode!="")
				{
				List<LandRegionDetail> landregionDetail=null;
				landregionDetail=new ArrayList<LandRegionDetail>();
				landregionDetail=downloadDirectoryService.landRegionDetail('D', Integer.parseInt(districtCode));
				if(landregionDetail.size()>0){
					model.addAttribute("message","Sub Districts of "+landregionDetail.get(0).getDistrictNameEnglish()+" District("+landregionDetail.get(0).getStateNameEnglish()+")");
				}	
				}

				model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",
						stateWiseEntityDetails);
				subdistrictbean
						.setStateWiseEntityDetails(stateWiseEntityDetails);
				model.addAttribute("viewPage", "abc");
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
												.getAttribute("limit")
												.toString()) + 1));
				subdistrictbean.setPageRows(Integer.parseInt(httpSession
						.getAttribute("limit").toString()));
			}
		} }catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			subdistrictbean.setCaptchaAnswer(null);
      		subdistrictbean.setCaptchaAnswers(null);
			return mav;
		}
		subdistrictbean.setCaptchaAnswer(null);
  		subdistrictbean.setCaptchaAnswers(null);
		return mav;
	}*/
	
	
	
	@RequestMapping(value = "/viewSubDistrictPagination", method = RequestMethod.POST)
	public ModelAndView getSubDistrictPagination(
			@ModelAttribute("subdistrictbean") SubDistrictForm villagebean,
			Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		try {
			char EntityType = ' ';

			if (villagebean.getPageType().equals("T")) {
				mav = new ModelAndView("view_subdistrict");
				EntityType = 'T';
			} else if (villagebean.getPageType().equals("CT")) {
				mav = new ModelAndView("view_csubdistrict");
				EntityType = 'T';
			}

			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer
					.parseInt(httpSession.getAttribute("counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset")
					.toString()) == (Integer.parseInt(httpSession.getAttribute(
					"counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& villagebean.getDirection() == -1
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			}

			List<StateWiseEntityDetails> stateWiseEntityDetails = null;

			if (httpSession.getAttribute("districtCode") != null
					&& !httpSession.getAttribute("districtCode").equals("")) {

				//System.out.println("districtCode :: "
				//		+ httpSession.getAttribute("districtCode"));
				stateWiseEntityDetails = villageService.getParentWiseList(
						'D',
						Integer.parseInt(httpSession.getAttribute(
								"districtCode").toString()), null, null);
				httpSession.setAttribute("counter",
						stateWiseEntityDetails.size());

				stateWiseEntityDetails = villageService.getParentWiseList(
						'D',
						Integer.parseInt(httpSession.getAttribute(
								"districtCode").toString()),
						httpSession.getAttribute("limit") == null ? null
								: Integer.parseInt(httpSession.getAttribute(
										"limit").toString()),
						Integer.parseInt(httpSession.getAttribute("limit")
								.toString())
								* (Integer.parseInt(httpSession.getAttribute(
										"offset").toString()) - 1));

			} else {

				stateWiseEntityDetails = villageService
						.getStateWiseVillageList(
								httpSession.getAttribute("stateCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("stateCode")
												.toString()),
								EntityType,
								httpSession.getAttribute("villageCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("villageCode")
												.toString()),
								httpSession.getAttribute("villageName") == null ? null
										: httpSession.getAttribute(
												"villageName").toString(),
								httpSession.getAttribute("limit") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("limit")
												.toString()),
								Integer.parseInt(httpSession.getAttribute(
										"limit").toString())
										* (Integer.parseInt(httpSession
												.getAttribute("offset")
												.toString()) - 1));
			}
			model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",
					stateWiseEntityDetails);
			villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
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

	
	@RequestMapping(value = "/globalviewSubDistrictPagination", method = RequestMethod.POST)
	public ModelAndView getGlobalSubDistrictPagination(
			@ModelAttribute("subdistrictbean") SubDistrictForm villagebean,
			Model model, HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		try {
			char EntityType = ' ';

			if (villagebean.getPageType().equals("T")) {
				mav = new ModelAndView("globalview_subdistrict");
				EntityType = 'T';
			} else if (villagebean.getPageType().equals("CT")) {
				mav = new ModelAndView("globalview_csubdistrict");
				EntityType = 'T';
			}

			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer
					.parseInt(httpSession.getAttribute("counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset")
					.toString()) == (Integer.parseInt(httpSession.getAttribute(
					"counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& villagebean.getDirection() == -1
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			}

			List<StateWiseEntityDetails> stateWiseEntityDetails = null;

			if (httpSession.getAttribute("districtCode") != null
					&& !httpSession.getAttribute("districtCode").equals("")) {

				//System.out.println("districtCode :: "
				//		+ httpSession.getAttribute("districtCode"));
				stateWiseEntityDetails = villageService.getParentWiseList(
						'D',
						Integer.parseInt(httpSession.getAttribute(
								"districtCode").toString()), null, null);
				httpSession.setAttribute("counter",
						stateWiseEntityDetails.size());

				stateWiseEntityDetails = villageService.getParentWiseList(
						'D',
						Integer.parseInt(httpSession.getAttribute(
								"districtCode").toString()),
						httpSession.getAttribute("limit") == null ? null
								: Integer.parseInt(httpSession.getAttribute(
										"limit").toString()),
						Integer.parseInt(httpSession.getAttribute("limit")
								.toString())
								* (Integer.parseInt(httpSession.getAttribute(
										"offset").toString()) - 1));

			} else {

				stateWiseEntityDetails = villageService
						.getStateWiseVillageList(
								httpSession.getAttribute("stateCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("stateCode")
												.toString()),
								EntityType,
								httpSession.getAttribute("villageCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("villageCode")
												.toString()),
								httpSession.getAttribute("villageName") == null ? null
										: httpSession.getAttribute(
												"villageName").toString(),
								httpSession.getAttribute("limit") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("limit")
												.toString()),
								Integer.parseInt(httpSession.getAttribute(
										"limit").toString())
										* (Integer.parseInt(httpSession
												.getAttribute("offset")
												.toString()) - 1));
			}
			/*added by pooja on 06-07-2015 to display heading message in pagination*/
			String districtCode = (String)httpSession.getAttribute("districtCode");
			if(districtCode!=null && districtCode!="")
			{
			List<LandRegionDetail> landregionDetail=null;
			landregionDetail=new ArrayList<LandRegionDetail>();
			landregionDetail=reportService.landRegionDetail('D', Integer.parseInt(districtCode));
			if(landregionDetail.size()>0){
				model.addAttribute("message","Sub Districts of "+landregionDetail.get(0).getDistrictNameEnglish()+" District("+landregionDetail.get(0).getStateNameEnglish()+")");
			}	
			}
			model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",
					stateWiseEntityDetails);
			villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
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
	
	
	@RequestMapping(value = "/viewsubdistrictbydistrictCode", method = RequestMethod.GET)
	public ModelAndView getsubdistrictbydistrictCode(HttpSession session,
			@ModelAttribute("subdistrictbean") SubDistrictForm subdistrictbean,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer districtCode) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_subdistrict");
			// int recordsLimit=villagebean.getRecordsLimit();
			String query = "from Subdistrict d where d.district.districtPK.districtCode='"
					+ districtCode
					+ "'and d.isactive=true order by subdistrictCode";
			List<Subdistrict> listSubDistrictDetail = null;
			listSubDistrictDetail = new ArrayList<Subdistrict>();
			listSubDistrictDetail = subDistrictService
					.getSubDistrictViewList(query);
			model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",
					listSubDistrictDetail);
			mav.addObject("subdistrictbean", subdistrictbean);
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

	// View SubDistrict Detail
	@RequestMapping(value = "/viewSubDistrictDetail")
	public ModelAndView viewSubDistrictDetail(
			@ModelAttribute("modifySubDistrictCmd") SubDistrictForm modifySubDistrictCmd,
			Model model, HttpServletRequest request,HttpSession session)
	{

		ModelAndView mav = null;
		//int subdistrictCode=modifySubDistrictCmd.getSubdistrictId();
		int subdistrictCode=0;
		
		
		if(modifySubDistrictCmd.getSubdistrictId() !=null && modifySubDistrictCmd.getSubdistrictId() !=0) 
		{	
			session.setAttribute("actionFlagSess",modifySubDistrictCmd.getSubdistrictId());
		}
	
		if(modifySubDistrictCmd.getSubdistrictId() ==null || modifySubDistrictCmd.getSubdistrictId() ==0) 
		{
			if(request.getParameter("id")!=null){
				subdistrictCode=Integer.parseInt(request.getParameter("id"));	
			}else{
				subdistrictCode=(Integer)session.getAttribute("actionFlagSess");
			}
			
		}
		else
		{
			subdistrictCode = modifySubDistrictCmd.getSubdistrictId();
		}
		
		try {
			List<SubdistrictDataForm> listSubdistrictDetails = subDistrictService
					.getSubdistrictDetailsModify(subdistrictCode);
			
			List<Subdistrict> subDistrictHistoryDetail =draftSubdistrictDao.getSubDistrictDetails(subdistrictCode);
			model.addAttribute("subDistrictHistory", subDistrictHistoryDetail );
			mav = new ModelAndView("view_subdistrictdetail");

			modifySubDistrictCmd
					.setListSubdistrictDetails(listSubdistrictDetails);
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

	// View SubDistrict Detail
	/*@RequestMapping(value = "/globalviewSubDistrictDetail")
	public ModelAndView globalviewSubDistrictDetail(
			@ModelAttribute("modifySubDistrictCmd") SubDistrictForm modifySubDistrictCmd,
			Model model, HttpServletRequest request)
	
	{

		ModelAndView mav = null;
		int subdistrictCode= modifySubDistrictCmd.getGlobalsubdistrictId();
		try
		{
			List<SubdistrictDataForm> listSubdistrictDetails = subDistrictService
					.getSubdistrictDetailsModify(subdistrictCode);
			mav = new ModelAndView("globalview_subdistrictdetail");

			modifySubDistrictCmd
					.setListSubdistrictDetails(listSubdistrictDetails);
		}
		catch (Exception e) 
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}*/
	
	// View SubDistrict Detail
	//Addition of 2 columns for the government order details and the view map
	
	@RequestMapping(value = "/globalviewSubDistrictDetail")
	public ModelAndView globalviewSubDistrictDetail(@ModelAttribute("modifySubDistrictCmd") SubDistrictForm modifySubDistrictCmd, BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		String id = request.getParameter("id");
		int subdistrictCode = modifySubDistrictCmd.getGlobalsubdistrictId() == null ? Integer.parseInt(id) : modifySubDistrictCmd.getGlobalsubdistrictId();
		try {
			List<SubdistrictDataForm> listSubdistrictDetails = subDistrictService.getSubdistrictDetailsModify(subdistrictCode);
			if (listSubdistrictDetails.size() > 0) {
				// Add Govt order details and the view map option
				for (int i = 0; i < listSubdistrictDetails.size(); i++) {
					if (listSubdistrictDetails.get(i).getOrderCodecr() != null) {
						int orderValue = listSubdistrictDetails.get(i).getOrderCodecr();
						if (orderValue > 0) {
							/*
							 * Retrieving the Map details attachment from the
							 * database
							 */

							/*
							 * List<EntityWiseMapDetailsPojo>
							 * EntityWiseMapDetails = subDistrictService
							 * .getEntityWiseMapDetails('T', subdistrictCode);
							 */
							// List<EntityWiseMapDetailsPojo>
							// EntityWiseMapDetails =
							// subDistrictService.getEntityWiseMapDetails('T',5927);
							/*
							 * if (!EntityWiseMapDetails.isEmpty()) {
							 * Iterator<EntityWiseMapDetailsPojo> itrMap =
							 * EntityWiseMapDetails .iterator(); while
							 * (itrMap.hasNext()) { EntityWiseMapDetailsPojo
							 * mapDetails = (EntityWiseMapDetailsPojo) itrMap
							 * .next();
							 * 
							 * String path = mapDetails.getImagePath(); String
							 * fileName = new File(path).getName();
							 * model.addAttribute("fileName", fileName);
							 * System.out.println("Inside the entitycode" +
							 * mapDetails.getEntityCode()); }
							 * model.addAttribute("mapAttachmentList",
							 * EntityWiseMapDetails);
							 */
							List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
							mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(subdistrictCode, 'T');

							if (mapAttachmentList.size() > 0) {

								model.addAttribute("mapAttachmentList", mapAttachmentList);

							} else {
								mav = new ModelAndView("globalview_subdistrictdetail");
								result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
								modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrictDetails);
								;
							}
							/*
							 * Retrieving the Govt order details attachment from
							 * the database
							 */

							List<Attachment> attachmentList = subDistrictService.getAttachmentsbyOrderCode(orderValue);
							// List<Attachment> attachmentList =
							// subDistrictService.getAttachmentsbyOrderCode(200);
							if (!attachmentList.isEmpty()) {
								mav = new ModelAndView("globalview_subdistrictdetail");
								model.addAttribute("attachmentList", attachmentList);
								modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrictDetails);
								return mav;
							} else {
								mav = new ModelAndView("globalview_subdistrictdetail");
								result.rejectValue("noAttachmentRecord", "Error.NOATTACHMENTDETAIL");
								modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrictDetails);
								return mav;
							}
						} else {
							mav = new ModelAndView("globalview_subdistrictdetail");
							result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
							modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrictDetails);
							return mav;
						}
					} else {
						mav = new ModelAndView("globalview_subdistrictdetail");
						result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
						/*
						 * Retrieving the Map details attachment from the
						 * database
						 */

						List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
						mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(subdistrictCode, 'T');

						if (mapAttachmentList.size() > 0) {

							model.addAttribute("mapAttachmentList", mapAttachmentList);
						} else {
							mav = new ModelAndView("globalview_subdistrictdetail");
							result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
							modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrictDetails);
							return mav;
						}
						modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrictDetails);
						return mav;
					}
				}
			} else {
				mav = new ModelAndView("globalview_subdistrictdetail");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	
	// View SubDistrict History
	
	@RequestMapping(value = "/viewSubDistrictHistory")
	public ModelAndView viewSubDistrictHistoryDetail(
			@ModelAttribute("subDistrictForm") SubDistrictForm subDistrictForm,
			Model model, HttpServletRequest request)
	{

		ModelAndView mav = null;
		int subdistrictCode=subDistrictForm.getSubdistrictId()!=null?subDistrictForm.getSubdistrictId():request.getParameter("id")!=null?Integer.parseInt(request.getParameter("id")):null;
		try {
			mav = new ModelAndView("view_subdistricthistory");
			/*
			 * model.addAttribute("lgtSubdistrictHistory",session.getAttribute(
			 * "subDistrictHistory"));
			 */
			char subdistrictNameEnglish = 'T';
			List<SubdistrictHistory> subDistrictHistoryDetail = subDistrictService
					.getSubDistrictHistoryDetail(subdistrictNameEnglish,
							subdistrictCode);
			model.addAttribute("subDistrictHistory", subDistrictHistoryDetail);
			subDistrictForm
					.setSubDistrictHistoryDetail(subDistrictHistoryDetail);
			mav.addObject("subDistrictForm", subDistrictForm);
			model.addAttribute("viewPage", "abc");
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

	// View SubDistrict History
		@RequestMapping(value = "/globalviewSubDistrictHistory", method = RequestMethod.GET)
		public ModelAndView viewSubDistrictHistoryDetailGlobal(
				@ModelAttribute("subDistrictForm") SubDistrictForm subDistrictForm,
				Model model, HttpServletRequest request,
				@RequestParam("id") Integer subdistrictCode) {

			ModelAndView mav = null;
			try {
				mav = new ModelAndView("globalview_subdistricthistory");
				/*
				 * model.addAttribute("lgtSubdistrictHistory",session.getAttribute(
				 * "subDistrictHistory"));
				 */
				char subdistrictNameEnglish = 'T';
				List<SubdistrictHistory> subDistrictHistoryDetail = subDistrictService
						.getSubDistrictHistoryDetail(subdistrictNameEnglish,
								subdistrictCode);
				model.addAttribute("subDistrictHistory", subDistrictHistoryDetail);
				subDistrictForm
						.setSubDistrictHistoryDetail(subDistrictHistoryDetail);
				mav.addObject("subDistrictForm", subDistrictForm);
				model.addAttribute("viewPage", "abc");
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

		int stateCode = 0;
	

	// /////////////////pagination
	@RequestMapping(value = "/viewBlockPagination", method = RequestMethod.POST)
	public ModelAndView getBlockPagination(
			@ModelAttribute("blockView") BlockForm villagebean, Model model,
			HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		try {
			char EntityType = ' ';

			if (villagebean.getPageType().equals("B")) {
				mav = new ModelAndView("view_block");
				EntityType = 'B';
			} else if (villagebean.getPageType().equals("CB")) {
				mav = new ModelAndView("view_cblock");
				EntityType = 'B';
			}

			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer
					.parseInt(httpSession.getAttribute("counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset")
					.toString()) == (Integer.parseInt(httpSession.getAttribute(
					"counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& villagebean.getDirection() == -1
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			}

			List<StateWiseEntityDetails> stateWiseEntityDetails = null;
			Integer blockCode = null;
			if(villagebean.getBlockNameEnglish().equals(""))
			{
				//blockCode =  Integer.parseInt(villagebean.getDistrictNameEnglish());	
			EntityType ='d';
				stateWiseEntityDetails = villageService.getParentWiseList(
						
						EntityType,	httpSession.getAttribute("villageCode") == null ? null
								: Integer.parseInt(httpSession.getAttribute(
										"villageCode").toString()),				
						httpSession.getAttribute("limit") == null ? null : Integer
								.parseInt(httpSession.getAttribute("limit")
										.toString()),
						Integer.parseInt(httpSession.getAttribute("limit")
								.toString())
								* (Integer.parseInt(httpSession.getAttribute(
										"offset").toString()) - 1));
			}
			else
			{
			stateWiseEntityDetails = villageService.getStateWiseVillageList(
					httpSession.getAttribute("stateCode") == null ? null
							: Integer.parseInt(httpSession.getAttribute(
									"stateCode").toString()),
					EntityType,
					httpSession.getAttribute("villageCode") == null ? null
							: Integer.parseInt(httpSession.getAttribute(
									"villageCode").toString()),
					httpSession.getAttribute("villageName") == null ? null
							: httpSession.getAttribute("villageName")
									.toString(),
					httpSession.getAttribute("limit") == null ? null : Integer
							.parseInt(httpSession.getAttribute("limit")
									.toString()),
					Integer.parseInt(httpSession.getAttribute("limit")
							.toString())
							* (Integer.parseInt(httpSession.getAttribute(
									"offset").toString()) - 1));
			}
			model.addAttribute("SEARCH_BLOCK_RESULTS_KEY",
					stateWiseEntityDetails);
			villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
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

	// View Block Detail
	// View Block Detail
		@RequestMapping(value = "/viewBlockDetail")
		public ModelAndView viewBlockDetail(
				@ModelAttribute("blockView") BlockForm blockView,
				HttpServletRequest request, Model model)
		{

			ModelAndView mav = null;
			int blockCode=blockView.getBlockId();
			try {
				List<BlockDataForm> listBlockDetails = blockService
						.getBlockDetailsModify(blockCode);
				mav = new ModelAndView("view_blockdetail");
				blockView.setListBlockDetails(listBlockDetails);
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
	// View Block History

	@RequestMapping(value = "/viewBlockHistory")
	public ModelAndView viewBlockHistoryDetail(
			@ModelAttribute("blockForm") BlockForm blockForm,
			HttpServletRequest request, Model model) 
	{

		ModelAndView mav = null;
		int blockCode=blockForm.getBlockId();
		try {
			mav = new ModelAndView("view_blockhistory");
			char blockNameEnglish = 'B';
			List<BlockHistory> blockHistoryDetail = blockService
					.getBlockHistoryDetail(blockNameEnglish, blockCode);
			model.addAttribute("blockHistory", blockHistoryDetail);
			blockForm.setBlockHistoryDetail(blockHistoryDetail);
			mav.addObject("blockForm", blockForm);
			model.addAttribute("viewPage", "abc");
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

	// View Local Body Type Part
	@RequestMapping(value = "/viewlocalgovttype", method = RequestMethod.GET)
	public ModelAndView showLocalGovtTypePage(Model model) {
		ModelAndView mav = new ModelAndView("view_localgovttype");
		mav.addObject("viewGovtType", new LocalGovtTypeDataForm());
		model.addAttribute("viewPage", "");
		return mav;
	}

	@RequestMapping(value = "/viewlocalgovttype", method = RequestMethod.POST)
	public ModelAndView getLocalGovtTypePage(
			@ModelAttribute("viewGovtType") LocalGovtTypeDataForm viewGovtType,
			Model model) {
		try {
			ModelAndView mav = new ModelAndView("view_localgovttype");
			// String localBodyTypeCode = viewGovtType.getCode();
			char category = viewGovtType.getCategory();

			String query = "";
			if (category != 'N' && category == 'P') {
				query = "from LocalBodyType d where d.category ='R' and  ispartixgovt=true and d.isactive=true order by localBodyTypeName";
			} else if (category != 'N' && category == 'T') {
				query = "from LocalBodyType d where d.category ='R' and  ispartixgovt=false and d.isactive=true order by localBodyTypeName";
			} else if (category != 'N' && category == 'R') {
				query = "from LocalBodyType d where d.category ='R' and d.isactive=true order by localBodyTypeName";
			} else if (category != 'N' && category == 'U') {
				query = "from LocalBodyType d where d.category ='U' and d.isactive=true order by localBodyTypeName ";
			} /*
			 * else if (category == 'N' && !localBodyTypeCode.equals("")) {
			 * query =
			 * "from LocalBodyType d where d.isactive=true and d.localBodyTypeCode="
			 * + localBodyTypeCode; } else if (category != 'N' &&
			 * !localBodyTypeCode.equals("")) { query =
			 * "from LocalBodyType d where d.isactive=true and d.localBodyTypeCode="
			 * + localBodyTypeCode + " and d.category ='" + category + "'";
			 */
			else if (category == 'N') {
				query = "from LocalBodyType d where d.isactive=true order by localBodyTypeName";
			} 
			List<LocalBodyType> listGovtTypeDetails = null;
			listGovtTypeDetails = new ArrayList<LocalBodyType>();
			listGovtTypeDetails = localGovtBodyService
					.getGovtTypeViewList(query);
			model.addAttribute("SEARCH_GOVTTYPE_RESULTS_KEY",
					listGovtTypeDetails);
			viewGovtType.setListGovtTypeDetail(listGovtTypeDetails);
			mav.addObject("viewGovtType", viewGovtType);
			model.addAttribute("viewPage", "abc");
			return mav;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return null;
		}
	}

	// //////////////
	// /////////////////pagination
	@RequestMapping(value = "/viewLBTPagination", method = RequestMethod.POST)
	public ModelAndView getLBTPagination(
			@ModelAttribute("viewGovtType") LocalGovtTypeDataForm villagebean,
			HttpServletRequest request, Model model, HttpSession httpSession) {

		ModelAndView mav = null;
		try {
			char EntityType = ' ';

			if (villagebean.getPageType().equals("L")) {
				mav = new ModelAndView("view_localgovttype");
				EntityType = 'L';
			} else if (villagebean.getPageType().equals("CL")) {
				mav = new ModelAndView("view_clocalgovttype");
				EntityType = 'L';
			}

			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer
					.parseInt(httpSession.getAttribute("counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset")
					.toString()) == (Integer.parseInt(httpSession.getAttribute(
					"counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& villagebean.getDirection() == -1
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + villagebean.getDirection() > 0) {
				villagebean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ villagebean.getDirection());
				httpSession.setAttribute("offset", villagebean.getOffset());
			}

			List<StateWiseEntityDetails> stateWiseEntityDetails = null;
			stateWiseEntityDetails = villageService.getStateWiseVillageList(
					httpSession.getAttribute("stateCode") == null ? null
							: Integer.parseInt(httpSession.getAttribute(
									"stateCode").toString()),
					EntityType,
					null,
					httpSession.getAttribute("villageCode") == null ? null
							: httpSession.getAttribute("villageCode")
									.toString(),
					httpSession.getAttribute("limit") == null ? null : Integer
							.parseInt(httpSession.getAttribute("limit")
									.toString()),
					Integer.parseInt(httpSession.getAttribute("limit")
							.toString())
							* (Integer.parseInt(httpSession.getAttribute(
									"offset").toString()) - 1));
			model.addAttribute("SEARCH_GOVTTYPE_RESULTS_KEY",
					stateWiseEntityDetails);
			villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
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

	// View Local Body Type Details
	@RequestMapping(value = "/viewLocalBodyTypeDetail")
	public ModelAndView viewBlockDetail(
			@ModelAttribute("viewBodyType") LocalGovtTypeDataForm viewBodyType,
			Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		String id = request.getParameter("id");
		int localBodyTypeCode=(viewBodyType.getLocalgovtId()==null)?Integer.parseInt(id):viewBodyType.getLocalgovtId();
		try 
		{
			List<LocalGovtTypeDataForm> listGovtTypeDetails = localGovtBodyService.getLocalBodyTypeDetails(localBodyTypeCode);
			mav = new ModelAndView("view_bodytypedetail");
			viewBodyType.setListGovtTypeDetails(listGovtTypeDetails);
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

	// View LocalBodyType History
	@RequestMapping(value = "/viewLocalBodyTypeHistory")
	public ModelAndView viewLocalGovtTypeHistory(
			@ModelAttribute("viewGovtType") LocalGovtTypeDataForm viewGovtType,
			Model model, HttpServletRequest request
			) 
	{

		ModelAndView mav = null;
		int localBodyTypeCode=viewGovtType.getLocalgovtId();
		try {
			mav = new ModelAndView("view_localgovttypehistory");
			List<LocalBodyTypeHistory> listLocalBodyTypeHistory = null;
			listLocalBodyTypeHistory = new ArrayList<LocalBodyTypeHistory>();
			String query = "from LocalBodyTypeHistory d where d.localBodyType.localBodyTypeCode="
					+ localBodyTypeCode;
			listLocalBodyTypeHistory = localGovtBodyService
					.getLocalBodyTypeHistory(query);
			model.addAttribute("SEARCH_GOVTTYPE_HISTORY_KEY",
					listLocalBodyTypeHistory);

			viewGovtType.setListLocalBodyTypeHistory(listLocalBodyTypeHistory);
			mav.addObject("viewGovtType", viewGovtType);
			model.addAttribute("viewPage", "abc");
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

	/*// View State Part
	@RequestMapping(value = "/viewstate", method = RequestMethod.GET)
	public ModelAndView showStatePage(Model model, HttpServletRequest request,
			HttpSession httpSession) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_state");
			String statecode = (String) httpSession.getAttribute("stateCode");
			if (statecode != null) {
				StateDataForm stateView = new StateDataForm();
				stateView.setCode(statecode);

				List<State> listStateDetails = new ArrayList<State>();
				listStateDetails = stateService.getStateViewList(stateView);
				model.addAttribute("SEARCH_STATE_RESULTS_KEY", listStateDetails);
				stateView.setListStateDetail(listStateDetails);
				mav.addObject("stateView", stateView);
				model.addAttribute("viewPage", "abc");
			} else {

				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);

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
	}*/

	
	// View State History
	@RequestMapping(value = "/globalviewStateHistory", method = RequestMethod.GET)
	public ModelAndView viewStateHistoryDetailGlobal(
			@ModelAttribute("stateForm") StateForm stateForm,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer stateCode) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("globalview_statehistory");
			char stateNameEnglish = 'S';
			List<StateHistory> stateHistoryDetail = stateService
					.getStateHistoryDetail(stateNameEnglish, stateCode);
			model.addAttribute("stateHistory", stateHistoryDetail);
			stateForm.setStateHistoryDetail(stateHistoryDetail);
			mav.addObject("stateForm", stateForm);
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
	
	// View State History
	@RequestMapping(value = "/viewStateHistory", method = RequestMethod.GET)
	public ModelAndView viewStateHistoryDetail(
			@ModelAttribute("stateForm") StateForm stateForm,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer stateCode) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_statehistory");
			char stateNameEnglish = 'S';
			List<StateHistory> stateHistoryDetail = stateService
					.getStateHistoryDetail(stateNameEnglish, stateCode);
			model.addAttribute("stateHistory", stateHistoryDetail);
			stateForm.setStateHistoryDetail(stateHistoryDetail);
			mav.addObject("stateForm", stateForm);
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
	
	
	// View State Detail
	@RequestMapping(value = "/viewStateDetail", method = RequestMethod.GET)
	public ModelAndView viewStateDetailList(
			@ModelAttribute("stateForm") StateForm stateForm,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer stateCode) {

		ModelAndView mav = null;
		try {
			List<StateDataForm> listStateDetails = stateService
					.getStateDetailsModify(stateCode);
			mav = new ModelAndView("view_statedetail");
			stateForm.setListStateDetails(listStateDetails);
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
	
	
	
	/*@RequestMapping(value = "/globalviewStateDetail", method = RequestMethod.GET)
	public ModelAndView globalviewStateDetailList(
			@ModelAttribute("stateForm") StateForm stateForm,
			HttpServletRequest request, Model model,
			@RequestParam("id") Integer stateCode) 
	*/
	/*@RequestMapping(value = "/globalviewStateDetail")
	public ModelAndView globalviewStateDetailList(
			@ModelAttribute("stateForm") StateForm stateForm,
			HttpServletRequest request, Model model) 
	{
		ModelAndView mav = null;
		String id = request.getParameter("id");
		int stateCode=(stateForm.getGlobalstateId()==null)?Integer.parseInt(id):stateForm.getGlobalstateId();
		try
		{
			List<StateDataForm> listStateDetails = stateService.getStateDetailsModify(stateCode);
			mav = new ModelAndView("globalview_statedetail");
			stateForm.setListStateDetails(listStateDetails);
		}
		catch (Exception e)
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}*/
	
	//Adding 2 columns to view the government order details and the view map
	
	@RequestMapping(value = "/globalviewStateDetail")
	public ModelAndView globalviewStateDetailList(@ModelAttribute("stateForm") StateForm stateForm, BindingResult result, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		String id = request.getParameter("id");
		int stateCode = (stateForm.getGlobalstateId() == null) ? Integer.parseInt(id) : stateForm.getGlobalstateId();
		try {
			List<StateDataForm> listStateDetails = stateService.getStateDetailsModify(stateCode);
			if (listStateDetails.size() > 0) {
				// Add Govt order details and the view map option
				for (int i = 0; i < listStateDetails.size(); i++) {
					if (listStateDetails.get(i).getOrderCodecr() != null) {
						int orderValue = listStateDetails.get(i).getOrderCodecr();
						if (orderValue > 0) {
							/*
							 * Retrieving the Map details attachment from the
							 * database
							 */

							/*
							 * List<EntityWiseMapDetailsPojo>
							 * EntityWiseMapDetails = stateService
							 * .getEntityWiseMapDetails('S', stateCode);
							 */
							// List<EntityWiseMapDetailsPojo>
							// EntityWiseMapDetails =
							// stateService.getEntityWiseMapDetails('S',1);
							/*
							 * if (!EntityWiseMapDetails.isEmpty()) {
							 * Iterator<EntityWiseMapDetailsPojo> itrMap =
							 * EntityWiseMapDetails .iterator(); while
							 * (itrMap.hasNext()) { EntityWiseMapDetailsPojo
							 * mapDetails = (EntityWiseMapDetailsPojo) itrMap
							 * .next();
							 * 
							 * String path = mapDetails.getImagePath(); String
							 * fileName = new File(path).getName();
							 * model.addAttribute("fileName", fileName);
							 * System.out.println("Inside the entitycode" +
							 * mapDetails.getEntityCode()); }
							 */
							List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
							mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(stateCode, 'S');
							//mapAttachmentList.get(0).getFileTimestamp();
							if (mapAttachmentList.size() > 0) {

								model.addAttribute("mapAttachmentList", mapAttachmentList);
							} else {
								mav = new ModelAndView("globalview_statedetail");
								result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
								stateForm.setListStateDetails(listStateDetails);
							}
							/*
							 * Retrieving the Govt order details attachment from
							 * the database
							 */

							List<Attachment> attachmentList = stateService.getAttachmentsbyOrderCode(orderValue);
							// List<Attachment> attachmentList =
							// stateService.getAttachmentsbyOrderCode(200);
							if (!attachmentList.isEmpty()) {
								mav = new ModelAndView("globalview_statedetail");
								model.addAttribute("attachmentList", attachmentList);
								stateForm.setListStateDetails(listStateDetails);
								return mav;
							} else {
								mav = new ModelAndView("globalview_statedetail");
								result.rejectValue("noAttachmentRecord", "Error.NOATTACHMENTDETAIL");
								stateForm.setListStateDetails(listStateDetails);
								return mav;
							}
						} else {
							mav = new ModelAndView("globalview_statedetail");
							result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
							stateForm.setListStateDetails(listStateDetails);
							return mav;
						}
					} else {
						mav = new ModelAndView("globalview_statedetail");
						result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
						/*
						 * Retrieving the Map details attachment from the
						 * database
						 */
						List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
						mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(stateCode, 'S');

						if (mapAttachmentList.size() > 0) {

							model.addAttribute("mapAttachmentList", mapAttachmentList);
						} else {
							mav = new ModelAndView("globalview_statedetail");
							result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
							stateForm.setListStateDetails(listStateDetails);
							return mav;
						}
						stateForm.setListStateDetails(listStateDetails);
						return mav;
					}
				}
			} else {
				mav = new ModelAndView("globalview_statedetail");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
				return mav;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	
	
	List<GetLocalGovtSetup> getLocalGovtSetupList = new ArrayList<GetLocalGovtSetup>();
	List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
	List<LocalbodyforStateWiseFinal> localBodyTypelist1 = new ArrayList<LocalbodyforStateWiseFinal>();
	List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();

	// View Local Govt Body Details modified by sushil on 30-01-2013
	
	//Methods have been shifted to LocalGovtBodyController.java ----on 07/03/2013---by Arnab----Start
/*	@RequestMapping(value = "/viewLocalBodyforPRI", method = RequestMethod.GET)
	public ModelAndView viewLocalBodyforPanchayat(HttpSession session, @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, Model model) {
		String skip = request.getParameter("skip");
		ModelAndView mav = null;
		if(skip == null) {
			LocalGovtBodyDraftController draftController = new LocalGovtBodyDraftController();
			mav  = draftController.getDraftedLGObjectList(model, request);
			if(mav != null && mav.getModel() != null) {
				Map<String, Object> map = mav.getModel();
				if(map.containsKey("fileList")) {
					mav.addObject("fileList", map.get("fileList"));
					mav.setViewName("viewLocalBodyDraftList");
					return mav;
				}
			}
		}
		
		List<LocalbodyListbyState> intermediatePanchayatList = new ArrayList<LocalbodyListbyState>();
		try {
			int stateCode = getStateCode(session);
			int districtCode = getDistrictId(session);

			if (stateCode != 0) {
				if(log.isDebugEnabled()) {
					log.debug("state code-->"+stateCode);
				}
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(stateCode, 'P');
				if (getLocalGovtSetupList != null && !getLocalGovtSetupList.isEmpty()) {
					log.info("isStateSetp has level -->"+getLocalGovtSetupList.size());
					char lbType = 'P';
					int operationCode = 45;
					Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);

					String govtOrderConfig = hMap.get("govtOrder");
					String mapConfig = hMap.get("mapUpload");
					String message = hMap.get("message");
					if (message != null) {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
						return mav;
					}  else {
						//mav = new ModelAndView("view_localgovtbody");
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
						mav.addObject("mapConfig", mapConfig);
						mav.addObject("govtOrderConfig", govtOrderConfig);
					}
					
					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList", getLocalGovtSetupList);

					localGovtBodyForm.setLgd_lbCategory("P");
					request.setAttribute("stateCode", stateCode);
					localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
					String type = localBodyTypelist.get(0).getLevel();

					int lbtype = localBodyTypelist.get(0).getLocalBodyTypeCode();
					localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);

					if (districtCode != 0) {
						List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);
						localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
						model.addAttribute("districtPanchayatList", districtPanchayatList);
					} else {
						List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'P', 'D');

						localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
						if (districtPanchayatList.size() > 0) {
							model.addAttribute("districtPanchayatList", districtPanchayatList);
						}
					}
					if (districtCode != 0) {
						intermediatePanchayatList = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);
						model.addAttribute("localBodyforSubDistList", intermediatePanchayatList);
					} else {
						intermediatePanchayatList = localGovtBodyService.getExistingLBListbyStateandCategoryInter(stateCode, 'P');
						if (intermediatePanchayatList.size() > 0) {
							model.addAttribute("localBodyforSubDistList", intermediatePanchayatList);
						}
					}
					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
					}
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("lbType", lbType);
					localGovtBodyForm.setLbtypeLevel('P');
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					return mav;
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid", "Please first Set Up Local Government for this State");
				}
			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}*/
	
	//Methods have been shifted to LocalGovtBodyController.java ----on 07/03/2013---by Arnab----End
	
	// View TraditionalLocal Govt Body Details modified by Arnab on 07-02-2013
	//Method have been shifted to LocalGovtBodyController.java ----on 07/03/2013---by Arnab----Start
	/*@RequestMapping(value = "/viewLocalBodyforTraditional", method = RequestMethod.GET)
	public ModelAndView viewLocalBodyforTraditional(
			HttpSession session,
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		List<LocalbodyListbyState> intermediatePanchayatList = new ArrayList<LocalbodyListbyState>();
		try {
		
			int stateCode = getStateCode(session);
			int districtCode = getDistrictId(session);
			
			if (stateCode != 0) {
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(
						stateCode, 'T');

				if (getLocalGovtSetupList != null
						&& !getLocalGovtSetupList.isEmpty()) {
					
					char lbType = 'T';
					int operationCode = 47;
					Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
					String govtOrderConfig = hMap.get("govtOrder");
					String mapConfig = hMap.get("mapUpload");
					String message = hMap.get("message");
					if (message != null) {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
						return mav;
					}  else {
						//mav = new ModelAndView("view_localgovtbody");
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
						mav.addObject("mapConfig", mapConfig);
						mav.addObject("govtOrderConfig", govtOrderConfig);
					}
					
					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList",
							getLocalGovtSetupList);
					localGovtBodyForm.setLgd_lbCategory("T");
					localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
					localBodyTypelist = localGovtBodyService
							.getLocalBodyListStateWise('T', stateCode);
					String type = localBodyTypelist.get(0).getLevel();

					int lbtype = localBodyTypelist.get(0).getLocalBodyTypeCode();
					localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);
					
					if (districtCode != 0) {
						List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);
						localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
						model.addAttribute("districtPanchayatList", districtPanchayatList);
					} else {
						List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'T', 'D');

						localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
						if (districtPanchayatList.size() > 0) {
							model.addAttribute("districtPanchayatList", districtPanchayatList);
						}
					}
					
					if (districtCode != 0) {
						intermediatePanchayatList = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);
						model.addAttribute("localBodyforSubDistList", intermediatePanchayatList);
					} else {
						intermediatePanchayatList = localGovtBodyService.getExistingLBListbyStateandCategoryInter(stateCode, 'T');
						if (intermediatePanchayatList.size() > 0) {
							model.addAttribute("localBodyforSubDistList", intermediatePanchayatList);
						}
					}
					
					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
					}
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					model.addAttribute("districtCode", districtCode);
					model.addAttribute("lbType",lbType);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);

					return mav;
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid",
							"Please first Set Up Local Government for this State");
				}
			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
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
	}*/
	//Methods have been shifted to LocalGovtBodyController.java ----on 07/03/2013---by Arnab----End
	
	// View Urban Local Govt Body Details modified by Arnab on 07-02-2013
	//Method have been shifted to LocalGovtBodyController.java ----on 07/03/2013---by Arnab----Start
	/*@RequestMapping(value = "/viewLocalBodyforUrban", method = RequestMethod.GET)
	public ModelAndView viewLocalBodyforUrban(
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			Model model, HttpServletRequest request, HttpSession session) {

		ModelAndView mav = null;
		try {
			int stateCode = getStateCode(session);

			if (stateCode != 0) {
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(
						stateCode, 'U');

				if (getLocalGovtSetupList != null
						&& !getLocalGovtSetupList.isEmpty()) {
					
					char lbType = 'U';
					int operationCode = 46;
					Map<String, String> hMap = govtOrderService.getMapAndGovtOrderConfiguration(stateCode, operationCode, lbType);
					String govtOrderConfig = hMap.get("govtOrder");
					String mapConfig = hMap.get("mapUpload");
					String message = hMap.get("message");
					if (message != null) {
						mav = new ModelAndView("success");
						mav.addObject("msgid", message);
						return mav;
					}  else {
						//mav = new ModelAndView("view_localgovtbody");
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
						mav.addObject("mapConfig", mapConfig);
						mav.addObject("govtOrderConfig", govtOrderConfig);
					}
					
					model.addAttribute("stateCode", stateCode);
					request.setAttribute("localSetUpList",
							getLocalGovtSetupList);

					localGovtBodyForm.setLgd_lbCategory("U");
					localBodyTypelist = new ArrayList<LocalbodyforStateWise>();
					localBodyTypelist = localGovtBodyService
							.getLocalBodyListStateWise('U', stateCode);

					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist,
								localGovtBodyForm);
					}
					//mav = new ModelAndView("view_localgovtbody");
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid",
							"Please first Set Up Local Government for this State");

				}
			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
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
	}*/
	//Methods have been shifted to LocalGovtBodyController.java ----on 07/03/2013---by Arnab----End
	
	public LocalGovtBodyForm setLBtype(
			List<LocalbodyforStateWise> localBodyTypelist,
			LocalGovtBodyForm localGovtBodyForm) {
		for (int j = 0; j < localBodyTypelist.size(); j++) {
			String govtBodyType = localBodyTypelist.get(j).getLevel();
			String category = localBodyTypelist.get(j).getCategory();
			if (govtBodyType != null) {
				if (govtBodyType.equalsIgnoreCase("D")) {
					if (localBodyTypelist.get(j).getNomenclatureEnglish() != null){

						localGovtBodyForm
								.setLgd_LBNomenclatureDist(localBodyTypelist
										.get(j).getNomenclatureEnglish());
					}	
				}
				if (govtBodyType.equalsIgnoreCase("I")) {
					if (localBodyTypelist.get(j).getNomenclatureEnglish() != null){
						
					localGovtBodyForm
							.setLgd_LBNomenclatureInter(localBodyTypelist
									.get(j).getNomenclatureEnglish());
					}
				}
				if (govtBodyType.equalsIgnoreCase("V")) {
					if (localBodyTypelist.get(j).getNomenclatureEnglish() != null){
						
					localGovtBodyForm
							.setLgd_LBNomenclatureVillage(localBodyTypelist
									.get(j).getNomenclatureEnglish());
					}
				}

			}

		}
		return localGovtBodyForm;
	}

	
	public LocalGovtBodyForm setLBtypeFinal(
			List<LocalbodyforStateWiseFinal> localBodyTypelist1,
			LocalGovtBodyForm localGovtBodyForm) {
		for (int j = 0; j < localBodyTypelist1.size(); j++) {
			String govtBodyType = localBodyTypelist1.get(j).getLevel();
			String category = localBodyTypelist1.get(j).getCategory();
			if (govtBodyType != null) {
				if (govtBodyType.equalsIgnoreCase("D")) {
					if (localBodyTypelist1.get(j).getNomenclatureEnglish() != null){

						localGovtBodyForm
								.setLgd_LBNomenclatureDist(localBodyTypelist1
										.get(j).getNomenclatureEnglish());
					}
				}
				if (govtBodyType.equalsIgnoreCase("I")) {
					if (localBodyTypelist1.get(j).getNomenclatureEnglish() != null){
						
					localGovtBodyForm
							.setLgd_LBNomenclatureInter(localBodyTypelist1
									.get(j).getNomenclatureEnglish());
					}
				}
				if (govtBodyType.equalsIgnoreCase("V")) {
					if (localBodyTypelist1.get(j).getNomenclatureEnglish() != null){
						
					localGovtBodyForm
							.setLgd_LBNomenclatureVillage(localBodyTypelist1
									.get(j).getNomenclatureEnglish());
					}
				}

			}

		}
		return localGovtBodyForm;
	}
/*	*//**
	 * On click of get button
	 * @modified by sushil on 12-02-2013 
	 *//*
	//Methods have been shifted to LocalGovtBodyController.java ----on 07/03/2013---by Arnab----Start
	@RequestMapping(value = "/modifyLocalBodyforPRI", method = RequestMethod.POST)
	public ModelAndView viewLocalBodyList(HttpSession session, @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult result, HttpServletRequest request, Model model) {
		
		ModelAndView mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_OTHER);
		try {
			int localBodyTypeCode = 0;
			int stateCode = getStateCode(session);
			int districtCode = getDistrictId(session);

			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);
			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);		
			List<GovernmentOrder> govtList = govtOrderService.getGovtOrederDetails();
			
		
			List<ParentWiseLocalBodies> parentWiseLocalBodies = null;

			String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
			String Catageryforwad = localGovtBodyForm.getLgd_lbCategory();
			String strArray[] = localBodyTypeCodeDup.split(":");
			String typeCode = strArray[0];
			String type = strArray[1];
			String categoryDropDown = strArray[2];
			String parentLB = strArray[4];
			char lbType=localGovtBodyForm.getLbtypeLevel();
			String lbName = null;
			Integer lbtype = Integer.parseInt(typeCode);
			String intermediatePanchayatCodes1 = localGovtBodyForm.getLocalBodyNameEnglishListSource();
			List<LocalbodyListbyState> districtPanchayatList = null;
			if (localGovtBodyForm.getLgd_lbCategory() != null && localGovtBodyForm.getLgd_lbCategory().length() > 0) {
				List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0), stateCode);

				if (localBodyTypelist != null && localBodyTypelist.size() > 0) {

					String districtPanchayatCodes = localGovtBodyForm.getLocalBodyNameEnglishList();
					String intermediatePanchayatCodes = localGovtBodyForm.getLocalBodyNameEnglishListSource();
					String villagepanchayatCodes = localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg();
					String districtPanchayatCode = districtPanchayatCodes;
					String intermediatePanchayatCode = intermediatePanchayatCodes;
					Integer districtcode = (Integer) session.getAttribute("districtId");
					int offset = 0;
					int limit = 1000;

					if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("U")) {
						// /////////////////////////////pagination///////////////////////////////
						List<Localbody> lgdLocalGovtBodyList = null;
						List<UlbBean> lgdLocalGovtBodyListUrban = null;
						localGovtBodyForm.setLimit(limit);
						localGovtBodyForm.setOffset(offset);
						session.setAttribute("limit", localGovtBodyForm.getLimit());
						boolean movenext = true;
						session.setAttribute("offset", localGovtBodyForm.getOffset());
						session.setAttribute("lbtype", lbtype);
						if(districtcode==0){	
							lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode, Integer.parseInt(typeCode), offset, limit);
						}	
						else{
							lgdLocalGovtBodyListUrban = localGovtBodyService.getPanchayatListbylocalbodyUrban(districtcode, Integer.parseInt(typeCode), offset, limit);
						}
								
						int counter = localGovtBodyService.countLocalBodyDetails(lbtype, stateCode);
						session.setAttribute("counter", counter);
						model.addAttribute("localGovtBodyForm", localGovtBodyForm);
						if(districtcode==0){
							model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
						}
						else{
							model.addAttribute("localbodysize", lgdLocalGovtBodyListUrban.size());
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyListUrban);
						}
						
						model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
						model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
						model.addAttribute("counter", counter);
						if (offset == (counter / limit))
							movenext = false;
						else
							movenext = true;
						model.addAttribute("movenext", movenext);
						model.addAttribute(
								"LocalBodyCount",
								"Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
										+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
						
						if(districtcode==0){
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

							localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
							if (lgdLocalGovtBodyList.size() <= 0) {
								model.addAttribute("listnull", "LocalBody not present");
							}
						}
						else{
							model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyListUrban);

							//localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyListUrban);
							if (lgdLocalGovtBodyListUrban.size() <= 0) {
								model.addAttribute("listnull", "LocalBody not present");
							}
						}			
						// /////////////////////////////pagination///////////////////////////////

					} else {
						if (type.equalsIgnoreCase("D")) {
							if (districtcode != 0) {
								List<LocalbodyListbyState> lgdLocalGovtBodyList = localGovtBodyService.getLandRegionWisedistrict(type, districtcode, lbtype);
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

								localGovtBodyForm.setLgdLocalGovtBodyList(lgdLocalGovtBodyList);
							}

							else {

								// /////////////////////////////pagination///////////////////////////////

								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext = true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode, Integer.parseInt(typeCode), offset, limit);
								int counter = localGovtBodyService.countLocalBodyDetails(lbtype, stateCode);
								session.setAttribute("counter", counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
								model.addAttribute("counter", counter);
								if (offset == (counter / limit))
									movenext = false;
								else
									movenext = true;
								model.addAttribute("movenext", movenext);
								model.addAttribute("LocalBodyCount", "Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
										+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

								localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
								if (lgdLocalGovtBodyList.size() <= 0) {
									model.addAttribute("listnull", "LocalBody not present");
								}

								// /////////////////////////////pagination///////////////////////////////

							}
						}

						if (type.equalsIgnoreCase("I")) {
							if (districtPanchayatCodes != null) {

								// /////////////////////////////pagination///////////////////////////////
								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext = true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								
								session.setAttribute("localBCode", localGovtBodyForm.getLocalBodyNameEnglishList());
								
								List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), districtPanchayatCodes, offset, limit);
								int counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, districtPanchayatCodes);
								session.setAttribute("counter", counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
								model.addAttribute("counter", counter);
								if (offset == (counter / limit))
									movenext = false;
								else
									movenext = true;
								model.addAttribute("movenext", movenext);
								model.addAttribute("LocalBodyCount", "Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
										+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);

								localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
								if (lgdLocalGovtBodyList.size() <= 0) {
									model.addAttribute("listnull", "LocalBody not present");
								}
								model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

								// /////////////////////////////pagination///////////////////////////////

							} else {
								int localbodyCode = Integer.parseInt(districtPanchayatCodes);
								List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService.getchildlocalbodiesByParentcode(localbodyCode);
								model.addAttribute("LocalGovtBodyList", localBodyViewChild);

								localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
								List<LocalbodyListbyState> localBodyViewChild= localGovtBodyService.getPanchayatListbylblcCode(stateCode,Integer.parseInt(typeCode));
								model.addAttribute("LocalGovtBodyList", localBodyViewChild);
								localGovtBodyForm.setLgdLocalGovtBodyList(localBodyViewChild);
								//localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
								
							}
						}
						int localBodyCode1 = 0;
						String lbCode=null;
						if (type.equalsIgnoreCase("V")) {
							if (districtPanchayatCodes != null) {
								if (intermediatePanchayatCodes != null) {
									localBodyCode1 = Integer.parseInt(intermediatePanchayatCodes);
									lbCode=intermediatePanchayatCodes;
								} else {
									localBodyCode1 = Integer.parseInt(districtPanchayatCodes);
									lbCode=districtPanchayatCodes;
								}

								// /////////////////////////////pagination///////////////////////////////

								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext = true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								session.setAttribute("localBCode", localGovtBodyForm.getLocalBodyNameEnglishListSource());
								
								List<Localbody> localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), intermediatePanchayatCodes, offset, limit);
								int counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, intermediatePanchayatCodes);
								
								List<Localbody> localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), lbCode, offset, limit);
								int counter = localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, lbCode);
								
								session.setAttribute("counter", counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", localBodyViewChild.size());
								model.addAttribute("LocalGovtBodyList", localBodyViewChild);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets", Integer.parseInt(session.getAttribute("offset").toString()));
								model.addAttribute("counter", counter);
								if (offset == (counter / limit))
									movenext = false;
								else
									movenext = true;
								model.addAttribute("movenext", movenext);
								model.addAttribute("LocalBodyCount", "Page " + (Integer.parseInt(session.getAttribute("offset").toString()) + 1) + " of "
										+ (Integer.parseInt(session.getAttribute("counter").toString()) / Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList", localBodyViewChild);
								model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);
								model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
								localGovtBodyForm.setLocalbodyDetails(localBodyViewChild);
								if (localBodyViewChild.size() <= 0) {
									model.addAttribute("listnull", "LocalBody not present");
								}

								// /////////////////////////////pagination///////////////////////////////
							} else {
								if (localGovtBodyForm.getLocalBodyNameEnglishListSource() != null) {

									localBodyCode1 = Integer.parseInt(intermediatePanchayatCodes);
									List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService.getchildlocalbodiesByParentcode(localBodyCode1);
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);
									localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

									model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
								}
								if (localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() != null) {
									localBodyCode1 = Integer.parseInt(districtPanchayatCodes);
									List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService.getchildlocalbodiesByParentcode(localBodyCode1);
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);
									localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

									model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
								}
								if (localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() == null) {
									localBodyCode1 = Integer.parseInt(typeCode);
									List<LocalbodyListbyState> localBodyViewChild = localGovtBodyService.getPanchayatListbyStateandlbTypeCode(stateCode, Integer.parseInt(typeCode));
									model.addAttribute("LocalGovtBodyList", localBodyViewChild);
									// localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute("hdnDistrictPanchayat", districtPanchayatCodes);

									model.addAttribute("hdnIntermediatePanchayat", intermediatePanchayatCodes);
								}

							}
						}
					}
					session.setAttribute("typeCode", typeCode);
					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist, localGovtBodyForm);
					}
					if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("T")) {
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
						if (districtCode != 0) {
							List<LocalbodyListbyState> districtPanchayatList1 = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);

							localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList1);
						} else {
							districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'T', 'D');
						}
					} else if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("P")) {
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
						if (districtCode != 0) {
							List<LocalbodyListbyState> districtPanchayatList1 = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);

							localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList1);
						} else {
							districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'P', 'D');
						}
					} else if (localGovtBodyForm.getLgd_lbCategory().equalsIgnoreCase("U")) {
						mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
						if (districtCode != 0) {
							List<LocalbodyListbyState> districtPanchayatList1 = localGovtBodyService.getLandRegionWisedistrict(type, districtCode, lbtype);

							localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);
							model.addAttribute("districtPanchayatList", districtPanchayatList1);
						} else {
							districtPanchayatList = localGovtBodyService.getPanchayatListbyStateandCategory(stateCode, 'P', 'D');
						}
					}

					session.setAttribute("lgtLocalBodyType", localBodyTypelist);

					model.addAttribute("districtPanchayatList", districtPanchayatList);
					session.setAttribute("districtPanchayatList", districtPanchayatList);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
					session.setAttribute("districtPanchayatCodes", districtPanchayatCode);
					session.setAttribute("intermediatePanchayatCodes", intermediatePanchayatCode);
				}
				
				localGovtBodyForm.setLbtypeLevel(lbType);
				localGovtBodyForm.setParentLB(parentLB);
				localGovtBodyForm.setHiddenLevel(type);
				localGovtBodyForm.setDistrictCode(Integer.toString(districtCode));
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				
				session.setAttribute("localGovtBodyForm", localGovtBodyForm);
				model.addAttribute("districtCode", districtCode);
				model.addAttribute("lbType", lbType);
				model.addAttribute("lbtypeLevel",type);
				model.addAttribute("hiddenLevel",type);
				
				session.setAttribute("Selected", localBodyTypeCodeDup);
				session.setAttribute("Catagery", Catageryforwad);
				session.setAttribute("intermediatePanchayatCodes", intermediatePanchayatCodes1);
				session.setAttribute("lbtype", lbtype);

			}
			// }

		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}
	//Methods have been shifted to LocalGovtBodyController.java ----on 07/03/2013---by Arnab----End
*/	
	@RequestMapping(value = "/viewLocalbodyPagination", method = RequestMethod.POST)
	public ModelAndView getlocalbodyPagination(
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,BindingResult result,
			HttpServletRequest request, HttpSession httpSession,Model model) {
		ModelAndView mav = null;
		mav = new ModelAndView("view_localgovtbody");
		boolean movenext=true;
		int offset=Integer.parseInt(httpSession.getAttribute("offset").toString());
		int limit=Integer.parseInt(httpSession.getAttribute("limit").toString());
		
		int counter=Integer.parseInt(httpSession.getAttribute("counter").toString());
		int direction=localGovtBodyForm.getDirection();
		int stateCode = getStateCode(httpSession);
		localGovtBodyForm.setParentCategory('v');
		String intermediatePanchayatCode=(String) httpSession.getAttribute("intermediatePanchayatCodes");
		List<Localbody> localbodyList=null;
		localbodyList=new ArrayList<Localbody>(); 
		
		try
		{
			if(intermediatePanchayatCode==null){
			
		
				if ((offset != (counter/ limit + 1))  && (direction==1) && ((offset + direction) > 0))
				{
					localGovtBodyForm.setOffset(offset+direction);
					httpSession.setAttribute("offset", localGovtBodyForm.getOffset());
		
				}
				else
					if ((offset != (counter/limit+1)) && (direction==-1) &&((offset+direction)>=0))
				 {
					localGovtBodyForm.setOffset(offset+direction);
					httpSession.setAttribute("offset", localGovtBodyForm.getOffset());
				 }
		
		offset= Integer.parseInt(httpSession.getAttribute("offset").toString());
		List<Localbody> localbodyListDetails=new ArrayList<Localbody>();
		int lblc=Integer.parseInt(httpSession.getAttribute("lbtype").toString());
		localbodyListDetails=localGovtBodyService.getPanchayatListbylocalbody(stateCode,lblc,((offset*limit)+1),limit);
		if(offset==(counter/limit))
			movenext=false;
		else
			movenext=true;
				
		model.addAttribute("localGovtBodyForm", localGovtBodyForm);
		model.addAttribute("localbodyListDetails", localbodyListDetails);
		model.addAttribute("localbodysize", localbodyListDetails.size());
		model.addAttribute("LocalGovtBodyList",localbodyListDetails);
		
		model.addAttribute("offsets", offset);
		model.addAttribute("limits", Integer.parseInt(httpSession.getAttribute("limit").toString()));
		model.addAttribute("counter",counter);
		model.addAttribute("movenext",movenext);
		
		model.addAttribute("LocalBodyCount","Page "
				+ (offset+1)
				+ " of "
				+ ((counter
				/ limit )+ 1));
		}
			else{
				if ((offset != (counter/ limit + 1))  && (direction==1) && ((offset + direction) > 0))
				{
					localGovtBodyForm.setOffset(offset+direction);
					httpSession.setAttribute("offset", localGovtBodyForm.getOffset());
		
				}
				else
					if ((offset != (counter/limit+1)) && (direction==-1) &&((offset+direction)>=0))
				 {
					localGovtBodyForm.setOffset(offset+direction);
					httpSession.setAttribute("offset", localGovtBodyForm.getOffset());
				 }
		
		offset= Integer.parseInt(httpSession.getAttribute("offset").toString());
		List<Localbody> localbodyListDetails=new ArrayList<Localbody>();
		int lblc=Integer.parseInt(httpSession.getAttribute("lbtype").toString());
		localbodyListDetails=localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode,lblc,intermediatePanchayatCode,((offset*limit)+1),limit);
		if(offset==(counter/limit))
			movenext=false;
		else
			movenext=true;
				
		model.addAttribute("localGovtBodyForm", localGovtBodyForm);
		model.addAttribute("localbodyListDetails", localbodyListDetails);
		model.addAttribute("localbodysize", localbodyListDetails.size());
		model.addAttribute("LocalGovtBodyList",localbodyListDetails);
		
		model.addAttribute("offsets", offset);
		model.addAttribute("limits", Integer.parseInt(httpSession.getAttribute("limit").toString()));
		model.addAttribute("counter",counter);
		model.addAttribute("movenext",movenext);
		
		model.addAttribute("LocalBodyCount","Page "
				+ (offset+1)
				+ " of "
				+ ((counter
				/ limit )+ 1));
			}
		}
		
		
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		return mav;
		
	}
	
	//Methods have been shifted to LocalGovtBodyController.java ----on 13/03/2013---by Arnab----Start
	
/*	@RequestMapping(value = "/ForwardCoveredLocalBodyforPRI")
	public ModelAndView CorrectcoveredareaLocalBodyList(
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			BindingResult result, HttpServletRequest request,HttpSession session, Model model) {

		ModelAndView mav = null;
		try {
			int localBodyTypeCode = 0;
			int stateCode = getStateCode(session);
			
			String localBodyTypeCodeDup=(String) session.getAttribute("Selectedvalue");
			String Catageryforwad=(String) session.getAttribute("Catageryforwad");
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);//Only Used For Combo Field Validation
			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);//Only Used For Combo Field Validation
			localGovtBodyForm.setLgd_lbCategory(Catageryforwad);		
			localGovtBodyForm.setLgd_LBTypeName(localBodyTypeCodeDup);
			//List<GovernmentOrder> govtList = govtOrderService.getGovtOrederDetails();
			//viewValidatorLB.validateView(localGovtBodyForm, result);
			if (result.hasErrors()) {
				if(localGovtBodyForm.getLgd_lbCategory().equals("P"))
				{	
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
				}
				else if(localGovtBodyForm.getLgd_lbCategory().equals("T"))
				{
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
				}
				else if(localGovtBodyForm.getLgd_lbCategory().equals("U"))
				{
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
				}
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

				//mav = new ModelAndView("view_localgovtbody");
				if(localGovtBodyForm.getLgd_lbCategory().equals("P"))
				{	
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
				}
				else if(localGovtBodyForm.getLgd_lbCategory().equals("T"))
				{
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
				}
				else if(localGovtBodyForm.getLgd_lbCategory().equals("U"))
				{
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
				}
				List<ParentWiseLocalBodies> parentWiseLocalBodies = null;
				//String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
				String strArray[] = localBodyTypeCodeDup.split(":");
				String typeCode = strArray[0];
				String type = strArray[1];
				String categoryDropDown = strArray[2];
				String lbName = null;
				List<LocalbodyListbyState> districtPanchayatList = null;
				if (Catageryforwad != null
						&& Catageryforwad.length() > 0) {
					List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService
							.getLocalBodyListStateWise(Catageryforwad.charAt(0), stateCode);

					if (localBodyTypelist != null
							&& localBodyTypelist.size() > 0) {

						String districtPanchayatCodes = (String) session.getAttribute("districtPanchayatCodes");
						String intermediatePanchayatCodes = (String) session.getAttribute("intermediatePanchayatCodes");
						Integer districtcode=(Integer)session.getAttribute("districtId");
						Integer lbtype=(Integer)session.getAttribute("lbtype");
						int offset=0;
						int limit=25;

						if (localGovtBodyForm.getLgd_lbCategory()
								.equalsIgnoreCase("U")) {
///////////////////////////////pagination///////////////////////////////
							
								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext=true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode,Integer.parseInt(typeCode),offset,limit);
								int counter=localGovtBodyService.countLocalBodyDetails(lbtype,stateCode);
								session.setAttribute("counter",counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
								model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) );
								model.addAttribute("counter",counter);
								if(offset==(counter/limit))
								movenext=false;
								else
								movenext=true;
								model.addAttribute("movenext",movenext);
								model.addAttribute("LocalBodyCount","Page "
								+ (Integer.parseInt(session.getAttribute("offset").toString())+1)
								+ " of "
								+ (Integer.parseInt(session.getAttribute("counter").toString())
								/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
								
								localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
								if(lgdLocalGovtBodyList.size()<=0)
								{
									model.addAttribute("listnull","LocalBody not present");
								}
								

								///////////////////////////////pagination///////////////////////////////
							
												
							
						} else {
							if (type.equalsIgnoreCase("D")) {
								 if(districtcode!=0)
								{
									 List<LocalbodyListbyState> lgdLocalGovtBodyList=localGovtBodyService. getLandRegionWisedistrict( type,districtcode,lbtype);
									 model.addAttribute("LocalGovtBodyList",
												lgdLocalGovtBodyList);
									
										localGovtBodyForm.setLgdLocalGovtBodyList(lgdLocalGovtBodyList);
								}

								 else{
															
	                    ///////////////////////////////pagination///////////////////////////////
								
								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext=true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode,Integer.parseInt(typeCode),offset,limit);
								int counter=localGovtBodyService.countLocalBodyDetails(lbtype,stateCode);
								session.setAttribute("counter",counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
								model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) );
								model.addAttribute("counter",counter);
								if(offset==(counter/limit))
								movenext=false;
								else
								movenext=true;
								model.addAttribute("movenext",movenext);
								model.addAttribute("LocalBodyCount","Page "
								+ (Integer.parseInt(session.getAttribute("offset").toString())+1)
								+ " of "
								+ (Integer.parseInt(session.getAttribute("counter").toString())
								/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
								
								localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
								if(lgdLocalGovtBodyList.size()<=0)
								{
									model.addAttribute("listnull","LocalBody not present");
								}
								

								///////////////////////////////pagination///////////////////////////////
								
							}
							}

							
							if (type.equalsIgnoreCase("I")) {
								if (districtPanchayatCodes != null) {
											
																		 ///////////////////////////////pagination///////////////////////////////									
									localGovtBodyForm.setLimit(limit);
									localGovtBodyForm.setOffset(offset);
									session.setAttribute("limit", localGovtBodyForm.getLimit());
									boolean movenext=true;
									session.setAttribute("offset", localGovtBodyForm.getOffset());
									session.setAttribute("lbtype", lbtype);
									List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode,Integer.parseInt(typeCode),districtPanchayatCodes,offset,limit);
									int counter=localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype,stateCode,districtPanchayatCodes);
									session.setAttribute("counter",counter);
									model.addAttribute("localGovtBodyForm", localGovtBodyForm);
									model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
									model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
									model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
									model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) );
									model.addAttribute("counter",counter);
									if(offset==(counter/limit))
									movenext=false;
									else
									movenext=true;
									model.addAttribute("movenext",movenext);
									model.addAttribute("LocalBodyCount","Page "
									+ (Integer.parseInt(session.getAttribute("offset").toString())+1)
									+ " of "
									+ (Integer.parseInt(session.getAttribute("counter").toString())
									/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));
									model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
									
									localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
									if(lgdLocalGovtBodyList.size()<=0)
									{
										model.addAttribute("listnull","LocalBody not present");
									}
									model.addAttribute("hdnDistrictPanchayat",	districtPanchayatCodes);

									///////////////////////////////pagination///////////////////////////////
								
								}
								else{
									int localbodyCode = Integer	.parseInt(districtPanchayatCodes);
									List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService
											.getchildlocalbodiesByParentcode(localbodyCode);
									model.addAttribute("LocalGovtBodyList",
											localBodyViewChild);
									
									localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
								}
							}
							int localBodyCode1 =0;
							if (type.equalsIgnoreCase("V"))
							{
								if (districtPanchayatCodes != null)
								{
									if (intermediatePanchayatCodes != null)
									{
										localBodyCode1=Integer.parseInt(intermediatePanchayatCodes);
									}
									else
									{
										localBodyCode1=Integer.parseInt(districtPanchayatCodes);
									}	
									
									
                                 ///////////////////////////////pagination///////////////////////////////
									
									localGovtBodyForm.setLimit(limit);
									localGovtBodyForm.setOffset(offset);
									session.setAttribute("limit", localGovtBodyForm.getLimit());
									boolean movenext=true;
									session.setAttribute("offset", localGovtBodyForm.getOffset());
									session.setAttribute("lbtype", lbtype);
									List<Localbody> localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode,Integer.parseInt(typeCode),intermediatePanchayatCodes,offset,limit);
									int counter=localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype,stateCode,intermediatePanchayatCodes);
									session.setAttribute("counter",counter);
									model.addAttribute("localGovtBodyForm", localGovtBodyForm);
									model.addAttribute("localbodysize", localBodyViewChild.size());
									model.addAttribute("LocalGovtBodyList",localBodyViewChild);
									model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
									model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) );
									model.addAttribute("counter",counter);
									if(offset==(counter/limit))
									movenext=false;
									else
									movenext=true;
									model.addAttribute("movenext",movenext);
									model.addAttribute("LocalBodyCount","Page "
									+ (Integer.parseInt(session.getAttribute("offset").toString())+1)
									+ " of "
									+ (Integer.parseInt(session.getAttribute("counter").toString())
									/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));
									model.addAttribute("LocalGovtBodyList",localBodyViewChild);
									model.addAttribute("hdnDistrictPanchayat",districtPanchayatCodes);
									model.addAttribute(	"hdnIntermediatePanchayat",intermediatePanchayatCodes);
									localGovtBodyForm.setLocalbodyDetails(localBodyViewChild);
									if(localBodyViewChild.size()<=0)
									{
										model.addAttribute("listnull","LocalBody not present");
									}
									

									///////////////////////////////pagination///////////////////////////////
								}
								else
								{
									if (localGovtBodyForm.getLocalBodyNameEnglishListSource() != null)
									{
										
										localBodyCode1=Integer.parseInt(intermediatePanchayatCodes);
										List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService
												.getchildlocalbodiesByParentcode(localBodyCode1);
									model.addAttribute("LocalGovtBodyList",
											localBodyViewChild);
									localGovtBodyForm
											.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute(
												"hdnDistrictPanchayat",
												districtPanchayatCodes);

									model.addAttribute(
												"hdnIntermediatePanchayat",
												intermediatePanchayatCodes);
									}
									if(localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() !=null)
									{
										localBodyCode1=Integer.parseInt(districtPanchayatCodes);
										List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService
												.getchildlocalbodiesByParentcode(localBodyCode1);
									model.addAttribute("LocalGovtBodyList",
											localBodyViewChild);
									localGovtBodyForm
											.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute(
												"hdnDistrictPanchayat",
												districtPanchayatCodes);
															

									model.addAttribute(
												"hdnIntermediatePanchayat",
												intermediatePanchayatCodes);
									}	
									if(localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() ==null)
									{
										localBodyCode1=Integer.parseInt(typeCode);
										List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService
												.getPanchayatListbyStateandlbTypeCode(stateCode, Integer.parseInt(typeCode));
										List<LocalbodyListbyState> localBodyViewChild = localGovtBodyService
												.getPanchayatListbyStateandlbTypeCode(
														stateCode,
														Integer.parseInt(typeCode));
									model.addAttribute("LocalGovtBodyList",
											localBodyViewChild);
									//localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute(
												"hdnDistrictPanchayat",
												districtPanchayatCodes);

									model.addAttribute(
												"hdnIntermediatePanchayat",
												intermediatePanchayatCodes);
									}
									
								}
							}
						}
						session.setAttribute("typeCode", typeCode);
						if (localBodyTypelist.size() > 0) {
							localGovtBodyForm = setLBtype(localBodyTypelist,
									localGovtBodyForm);
						}
						if (localGovtBodyForm.getLgd_lbCategory()
								.equalsIgnoreCase("T")) {
							 if(districtcode!=0)
			                    {
			                    	List<LocalbodyListbyState> districtPanchayatList1 =localGovtBodyService. getLandRegionWisedistrict( type,districtcode,lbtype);
			    					
			    					localGovtBodyForm
			    							.setDistrictPanchayatList(districtPanchayatList);
			    					model.addAttribute("districtPanchayatList",
			    							districtPanchayatList1);
			                    }
							 else{
							districtPanchayatList = localGovtBodyService
									.getPanchayatListbyStateandCategory(
											stateCode, 'T','D');
							 }
						} else if (localGovtBodyForm.getLgd_lbCategory()
								.equalsIgnoreCase("P")) {
							if(districtcode!=0)
		                    {
		                    	List<LocalbodyListbyState> districtPanchayatList1 =localGovtBodyService. getLandRegionWisedistrict( type,districtcode,lbtype);
		    					
		    					localGovtBodyForm
		    							.setDistrictPanchayatList(districtPanchayatList);
		    					model.addAttribute("districtPanchayatList",
		    							districtPanchayatList1);
		                    }
							else{
							districtPanchayatList = localGovtBodyService
									.getPanchayatListbyStateandCategory(
										stateCode, 'P','D');
						}
						}

						session.setAttribute("lgtLocalBodyType",
								localBodyTypelist);

						model.addAttribute("districtPanchayatList",
								districtPanchayatList);
						session.setAttribute("districtPanchayatList",
								districtPanchayatList);
						model.addAttribute("lgtLocalBodyType",
								localBodyTypelist);
						
					}
					
					// localGovtBodyForm.setHdnType(localBodyTypeCodeDup);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					session.setAttribute("localGovtBodyForm", localGovtBodyForm);
					mav.addObject("msgid","This LocalBody already Have the CoveredArea So Go to Change CoveredArea");
				}
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

		return mav;
	}*/
	
	//Methods have been shifted to LocalGovtBodyController.java ----on 13/03/2013---by Arnab----End
	
	@RequestMapping(value = "/ForwadCorrectcoveredareaLocalBody")
	public ModelAndView ForwadCorrectcoveredareaLocalBodyList(
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			BindingResult result, HttpServletRequest request,HttpSession session, Model model) {

		ModelAndView mav = null;
		try {
			int localBodyTypeCode = 0;
			int stateCode = getStateCode(session);
			
			String localBodyTypeCodeDup=(String) session.getAttribute("Selectedvalue");
			String Catageryforwad=(String) session.getAttribute("Catageryforwad");
			localGovtBodyForm.setLocalBodyTypelist(localBodyTypelist);//Only Used For Combo Field Validation
			localGovtBodyForm.setDistrictPanchayatList(districtPanchayatList);//Only Used For Combo Field Validation
			localGovtBodyForm.setLgd_lbCategory(Catageryforwad);	
			localGovtBodyForm.setLgd_LBDistrictAtInter((String) session.getAttribute("lbDistrictAtInter"));
			localGovtBodyForm.setLgd_LBDistrictAtVillage((String) session.getAttribute("lbDistrictAtVillage"));
			localGovtBodyForm.setLgd_LBTypeName(localBodyTypeCodeDup);
			List<GovernmentOrder> govtList = govtOrderService.getGovtOrederDetails();
			//viewValidatorLB.validateView(localGovtBodyForm, result);
			if (result.hasErrors()) {
				if(localGovtBodyForm.getLgd_lbCategory().equals("P"))
				{	
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
				}
				else if(localGovtBodyForm.getLgd_lbCategory().equals("T"))
				{
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
				}
				else if(localGovtBodyForm.getLgd_lbCategory().equals("U"))
				{
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
				}

				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			} else {

			
				if(localGovtBodyForm.getLgd_lbCategory().equals("P"))
				{	
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST);
				}
				else if(localGovtBodyForm.getLgd_lbCategory().equals("T"))
				{
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_TRADITIONAL);
				}
				else if(localGovtBodyForm.getLgd_lbCategory().equals("U"))
				{
					mav = new ModelAndView(VIEW_LOCAL_BODY_LIST_URBAN);
				}
				List<ParentWiseLocalBodies> parentWiseLocalBodies = null;
				//String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
				String strArray[] = localBodyTypeCodeDup.split(":");
				String typeCode = strArray[0];
				String type = strArray[1];
				String categoryDropDown = strArray[2];
				String lbName = null;
				List<LocalbodyListbyState> districtPanchayatList = null;
				if (Catageryforwad != null
						&& Catageryforwad.length() > 0) {
					List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService
							.getLocalBodyListStateWise(Catageryforwad.charAt(0), stateCode);

					if (localBodyTypelist != null
							&& localBodyTypelist.size() > 0) {

						String districtPanchayatCodes = (String) session.getAttribute("districtPanchayatCodes");
						String intermediatePanchayatCodes = (String) session.getAttribute("intermediatePanchayatCodes");

						Integer districtcode=(Integer)session.getAttribute("districtId");
						Integer lbtype=(Integer)session.getAttribute("lbtype");
						int offset=0;
						int limit=25;

						if (localGovtBodyForm.getLgd_lbCategory()
								.equalsIgnoreCase("U")) {
///////////////////////////////pagination///////////////////////////////
							
								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext=true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode,Integer.parseInt(typeCode),offset,limit);
								int counter=localGovtBodyService.countLocalBodyDetails(lbtype,stateCode);
								session.setAttribute("counter",counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
								model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) );
								model.addAttribute("counter",counter);
								if(offset==(counter/limit))
								movenext=false;
								else
								movenext=true;
								model.addAttribute("movenext",movenext);
								model.addAttribute("LocalBodyCount","Page "
								+ (Integer.parseInt(session.getAttribute("offset").toString())+1)
								+ " of "
								+ (Integer.parseInt(session.getAttribute("counter").toString())
								/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
								
								localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
								if(lgdLocalGovtBodyList.size()<=0)
								{
									model.addAttribute("listnull","LocalBody not present");
								}
								

								///////////////////////////////pagination///////////////////////////////
							
												
							
						} else if(localGovtBodyForm.getLgd_lbCategory()
								.equalsIgnoreCase("P")){
							if (type.equalsIgnoreCase("D")) {
								 if(districtcode!=0)
								{
									 List<LocalbodyListbyState> lgdLocalGovtBodyList=localGovtBodyService. getLandRegionWisedistrict( type,districtcode,lbtype);
									 model.addAttribute("LocalGovtBodyList",
												lgdLocalGovtBodyList);
									
										localGovtBodyForm.setLgdLocalGovtBodyList(lgdLocalGovtBodyList);
								}

								 else{
															
	                    ///////////////////////////////pagination///////////////////////////////
								
								localGovtBodyForm.setLimit(limit);
								localGovtBodyForm.setOffset(offset);
								session.setAttribute("limit", localGovtBodyForm.getLimit());
								boolean movenext=true;
								session.setAttribute("offset", localGovtBodyForm.getOffset());
								session.setAttribute("lbtype", lbtype);
								List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbody(stateCode,Integer.parseInt(typeCode),offset,limit);
								int counter=localGovtBodyService.countLocalBodyDetails(lbtype,stateCode);
								session.setAttribute("counter",counter);
								model.addAttribute("localGovtBodyForm", localGovtBodyForm);
								model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
								model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
								model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
								model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) );
								model.addAttribute("counter",counter);
								if(offset==(counter/limit))
								movenext=false;
								else
								movenext=true;
								model.addAttribute("movenext",movenext);
								model.addAttribute("LocalBodyCount","Page "
								+ (Integer.parseInt(session.getAttribute("offset").toString())+1)
								+ " of "
								+ (Integer.parseInt(session.getAttribute("counter").toString())
								/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));
								model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
								
								localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
								if(lgdLocalGovtBodyList.size()<=0)
								{
									model.addAttribute("listnull","LocalBody not present");
								}
								

								///////////////////////////////pagination///////////////////////////////
								
							}
							}

							
							if (type.equalsIgnoreCase("I")) {
								if (districtPanchayatCodes != null) {
											
																		 ///////////////////////////////pagination///////////////////////////////									
									localGovtBodyForm.setLimit(limit);
									localGovtBodyForm.setOffset(offset);
									session.setAttribute("limit", localGovtBodyForm.getLimit());
									boolean movenext=true;
									session.setAttribute("offset", localGovtBodyForm.getOffset());
									session.setAttribute("lbtype", lbtype);
									List<Localbody> lgdLocalGovtBodyList = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode,Integer.parseInt(typeCode),districtPanchayatCodes,offset,limit);
									int counter=localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype,stateCode,districtPanchayatCodes);
									session.setAttribute("counter",counter);
									model.addAttribute("localGovtBodyForm", localGovtBodyForm);
									model.addAttribute("localbodysize", lgdLocalGovtBodyList.size());
									model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
									model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
									model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) );
									model.addAttribute("counter",counter);
									if(offset==(counter/limit))
									movenext=false;
									else
									movenext=true;
									model.addAttribute("movenext",movenext);
									model.addAttribute("LocalBodyCount","Page "
									+ (Integer.parseInt(session.getAttribute("offset").toString())+1)
									+ " of "
									+ (Integer.parseInt(session.getAttribute("counter").toString())
									/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));
									model.addAttribute("LocalGovtBodyList",lgdLocalGovtBodyList);
									
									localGovtBodyForm.setLocalbodyDetails(lgdLocalGovtBodyList);
									if(lgdLocalGovtBodyList.size()<=0)
									{
										model.addAttribute("listnull","LocalBody not present");
									}
									model.addAttribute("hdnDistrictPanchayat",	districtPanchayatCodes);

									///////////////////////////////pagination///////////////////////////////
								
								}
								else{
									int localbodyCode = Integer	.parseInt(districtPanchayatCodes);
									List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService
											.getchildlocalbodiesByParentcode(localbodyCode);
									model.addAttribute("LocalGovtBodyList",
											localBodyViewChild);
									
									localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
								}
							}
							int localBodyCode1 =0;
							if (type.equalsIgnoreCase("V"))
							{
								if (districtPanchayatCodes != null)
								{
									if (intermediatePanchayatCodes != null)
									{
										localBodyCode1=Integer.parseInt(intermediatePanchayatCodes);
									}
									else
									{
										localBodyCode1=Integer.parseInt(districtPanchayatCodes);
									}	
									
									
                                 ///////////////////////////////pagination///////////////////////////////
									
									localGovtBodyForm.setLimit(limit);
									localGovtBodyForm.setOffset(offset);
									session.setAttribute("limit", localGovtBodyForm.getLimit());
									boolean movenext=true;
									session.setAttribute("offset", localGovtBodyForm.getOffset());
									session.setAttribute("lbtype", lbtype);
									List<Localbody> localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode,Integer.parseInt(typeCode),intermediatePanchayatCodes,offset,limit);
									int counter=localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype,stateCode,intermediatePanchayatCodes);
									session.setAttribute("counter",counter);
									model.addAttribute("localGovtBodyForm", localGovtBodyForm);
									model.addAttribute("localbodysize", localBodyViewChild.size());
									model.addAttribute("LocalGovtBodyList",localBodyViewChild);
									model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
									model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) );
									model.addAttribute("counter",counter);
									if(offset==(counter/limit))
									movenext=false;
									else
									movenext=true;
									model.addAttribute("movenext",movenext);
									model.addAttribute("LocalBodyCount","Page "
									+ (Integer.parseInt(session.getAttribute("offset").toString())+1)
									+ " of "
									+ (Integer.parseInt(session.getAttribute("counter").toString())
									/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));
									model.addAttribute("LocalGovtBodyList",localBodyViewChild);
									model.addAttribute("hdnDistrictPanchayat",districtPanchayatCodes);
									model.addAttribute(	"hdnIntermediatePanchayat",intermediatePanchayatCodes);
									localGovtBodyForm.setLocalbodyDetails(localBodyViewChild);
									if(localBodyViewChild.size()<=0)
									{
										model.addAttribute("listnull","LocalBody not present");
									}
									

									///////////////////////////////pagination///////////////////////////////
								}
								else
								{
									if (localGovtBodyForm.getLocalBodyNameEnglishListSource() != null)
									{
										
										localBodyCode1=Integer.parseInt(intermediatePanchayatCodes);
										List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService
												.getchildlocalbodiesByParentcode(localBodyCode1);
									model.addAttribute("LocalGovtBodyList",
											localBodyViewChild);
									localGovtBodyForm
											.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute(
												"hdnDistrictPanchayat",
												districtPanchayatCodes);

									model.addAttribute(
												"hdnIntermediatePanchayat",
												intermediatePanchayatCodes);
									}
									if(localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() !=null)
									{
										localBodyCode1=Integer.parseInt(districtPanchayatCodes);
										List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService
												.getchildlocalbodiesByParentcode(localBodyCode1);
									model.addAttribute("LocalGovtBodyList",
											localBodyViewChild);
									localGovtBodyForm
											.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute(
												"hdnDistrictPanchayat",
												districtPanchayatCodes);
															

									model.addAttribute(
												"hdnIntermediatePanchayat",
												intermediatePanchayatCodes);
									}	
									if(localGovtBodyForm.getLocalBodyNameEnglishListSource() == null && localGovtBodyForm.getLocalBodyNameEnglishList() ==null)
									{
										localBodyCode1=Integer.parseInt(typeCode);
										/*List<ParentWiseLocalBodies> localBodyViewChild = localGovtBodyService
												.getPanchayatListbyStateandlbTypeCode(stateCode, Integer.parseInt(typeCode));*/
										List<LocalbodyListbyState> localBodyViewChild = localGovtBodyService
												.getPanchayatListbyStateandlbTypeCode(
														stateCode,
														Integer.parseInt(typeCode));
									model.addAttribute("LocalGovtBodyList",
											localBodyViewChild);
									//localGovtBodyForm.setLocalBodyViewChild(localBodyViewChild);
									model.addAttribute(
												"hdnDistrictPanchayat",
												districtPanchayatCodes);

									model.addAttribute(
												"hdnIntermediatePanchayat",
												intermediatePanchayatCodes);
									}
									
								}
							}
						}
						session.setAttribute("typeCode", typeCode);
						if (localBodyTypelist.size() > 0) {
							localGovtBodyForm = setLBtype(localBodyTypelist,
									localGovtBodyForm);
						}
						
						if(localGovtBodyForm.getLgd_lbCategory()
								.equalsIgnoreCase("T")){
							int  lbdistrictAtInter=0;
							int lbdistrictAtVillage=0;
							if(localGovtBodyForm.getLgd_LBDistrictAtInter()!=null &&  !("".equalsIgnoreCase(localGovtBodyForm.getLgd_LBDistrictAtInter()))){
								lbdistrictAtInter=Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtInter());
							}
							if(localGovtBodyForm.getLgd_LBDistrictAtVillage() !=null && !("".equalsIgnoreCase(localGovtBodyForm.getLgd_LBDistrictAtVillage()))){
								lbdistrictAtVillage=Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtVillage());
							}
							List<Localbody> localBodyViewChild=null;
							int counter=0;
							boolean movenext = true;
							if(lbdistrictAtVillage>0){
								localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), localGovtBodyForm.getLgd_LBDistrictAtVillage(), offset, limit);
								counter=localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, localGovtBodyForm.getLgd_LBDistrictAtVillage());
								session.setAttribute("localBCode", localGovtBodyForm.getLgd_LBDistrictAtVillage());
							}
							if(lbdistrictAtVillage==0 && lbdistrictAtInter>0){
								localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbodyVillagepanchayat(stateCode, Integer.parseInt(typeCode), localGovtBodyForm.getLgd_LBDistrictAtInter(), offset, limit);
								counter=localGovtBodyService.countLocalBodyDetailsforVillagePanchayat(lbtype, stateCode, localGovtBodyForm.getLgd_LBDistrictAtInter());
								session.setAttribute("localBCode", localGovtBodyForm.getLgd_LBDistrictAtInter());
							}
							if(lbdistrictAtVillage==0 && lbdistrictAtInter==0){
								localBodyViewChild = localGovtBodyService.getPanchayatListbylocalbody(stateCode, Integer.parseInt(typeCode), offset, limit);
								counter = localGovtBodyService.countLocalBodyDetails(lbtype, stateCode);
							}
							List<LocalbodyListbyState> intermediatePanchayatList = new ArrayList<LocalbodyListbyState>();
							intermediatePanchayatList = localGovtBodyService.getExistingLBListbyStateandCategoryInter(stateCode, 'T');
							if (intermediatePanchayatList.size() > 0) {
								model.addAttribute("localBodyforSubDistList", intermediatePanchayatList);
							}
							session.setAttribute("counter", counter);
							model.addAttribute("localGovtBodyForm", localGovtBodyForm);
							model.addAttribute("localbodysize", localBodyViewChild.size());
							model.addAttribute("LocalGovtBodyList", localBodyViewChild);
							model.addAttribute("hdnDistrictPanchayat", lbdistrictAtInter);
							model.addAttribute("hdnIntermediatePanchayat", lbdistrictAtVillage);
							model.addAttribute("lgd_LBDistrictAtInter",lbdistrictAtInter);
							model.addAttribute("lgd_LBDistrictAtVillage",lbdistrictAtVillage);
						}
						if (localGovtBodyForm.getLgd_lbCategory()
								.equalsIgnoreCase("T")) {
							 if(districtcode!=0)
			                    {
			                    	List<LocalbodyListbyState> districtPanchayatList1 =localGovtBodyService. getLandRegionWisedistrict( type,districtcode,lbtype);
			    					
			    					localGovtBodyForm
			    							.setDistrictPanchayatList(districtPanchayatList);
			    					model.addAttribute("districtPanchayatList",
			    							districtPanchayatList1);
			                    }
							 else{
							districtPanchayatList = localGovtBodyService
									.getPanchayatListbyStateandCategory(
											stateCode, 'T','D');
							 }
						} else if (localGovtBodyForm.getLgd_lbCategory()
								.equalsIgnoreCase("P")) {
							if(districtcode!=0)
		                    {
		                    	List<LocalbodyListbyState> districtPanchayatList1 =localGovtBodyService. getLandRegionWisedistrict( type,districtcode,lbtype);
		    					
		    					localGovtBodyForm
		    							.setDistrictPanchayatList(districtPanchayatList);
		    					model.addAttribute("districtPanchayatList",
		    							districtPanchayatList1);
		                    }
							else{
							districtPanchayatList = localGovtBodyService
									.getPanchayatListbyStateandCategory(
										stateCode, 'P','D');
						}
						}

						session.setAttribute("lgtLocalBodyType",
								localBodyTypelist);

						model.addAttribute("districtPanchayatList",
								districtPanchayatList);
						session.setAttribute("districtPanchayatList",
								districtPanchayatList);
						model.addAttribute("lgtLocalBodyType",
								localBodyTypelist);
						
					}
					// localGovtBodyForm.setHdnType(localBodyTypeCodeDup);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					session.setAttribute("localGovtBodyForm", localGovtBodyForm);
					mav.addObject("msgid","Go to Correct Covered area add covered area First");
				}
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

		return mav;
	}
	
	/**
	 * This method is for view Local govt body details
	 * @param session
	 * @param viewlocalbody
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 * @author Modified by Sushil
	 */
	
	//Method moved to LocalGovtBodyController.java----on 07/03/2013 ---by Arnab-----START
	/*@RequestMapping(value = "/ViewLocalBodyforPRIPost")
	public ModelAndView viewLocalBodyListforView(HttpSession session, @ModelAttribute("viewlocalbody") LocalGovtBodyForm viewlocalbody, BindingResult result, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		try {
			Integer localBodyCode = viewlocalbody.getParentwiseId();
			mav = new ModelAndView("view_localgovtbodyDeatils");
			List<LocalGovtBody> viewlocalbody1 = localGovtBodyService.viewlocalbodyDetails(localBodyCode);
			model.addAttribute("viewlocalbody", viewlocalbody1.get(0));
		} catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}*/
	//Method moved to LocalGovtBodyController.java----on 07/03/2013 ---by Arnab-----END
	 
	@RequestMapping(value = "/viewChildLocalBody")
	public ModelAndView viewChildlocalbody(
			HttpSession session,
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		int localBodyCode=localGovtBodyForm.getParentwiseId();
		try {
			mav = new ModelAndView("view_localgovtbody");
			model.addAttribute("lgtLocalBodyType",
					session.getAttribute("lgtLocalBodyType"));
			model.addAttribute("districtPanchayatList",
					session.getAttribute("districtPanchayatList"));

			localGovtBodyForm = (LocalGovtBodyForm) session
					.getAttribute("localGovtBodyForm");
			int stateCode = getStateCode(session);
			String category = localGovtBodyForm.getLgd_lbCategory();
			char ctgry = category.charAt(0);
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService
					.getLocalBodyListStateWise(ctgry, stateCode);

			model.addAttribute("lgtLocalBodyType", localBodyTypelist);
			List<ParentWiseLocalBodies> lgdLocalGovtBodyList = localGovtBodyService
					.getchildlocalbodiesByParentcode(localBodyCode);
			// localGovtBodyForm.setLgd_LBTypeName(typeCode);

			model.addAttribute("LocalGovtBodyList", lgdLocalGovtBodyList);
			localGovtBodyForm.setLocalBodyViewChild(lgdLocalGovtBodyList);
			mav.addObject("localGovtBodyForm", localGovtBodyForm);
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
	
	

	

	// Search Entity
	@RequestMapping(value = "/searchentity", method = RequestMethod.GET)
	public ModelAndView searchEntityDetail(Model model,
			HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("search_view");
			mav.addObject("searchView", new SearchForm());
			model.addAttribute("viewPage", "");
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

	@RequestMapping(value = "/searchentity", method = RequestMethod.POST)
	public ModelAndView getSearchDetail(
			@ModelAttribute("searchView") SearchForm searchView,BindingResult result,
			HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		try {
			String entityName = searchView.getEntityName();
			mav = new ModelAndView("search_view");
			if (entityName == "" || entityName == null) {
				// TODO we need to set error message here
				mav = new ModelAndView("search_view");
				return mav;
			}
			viewValidator.validateSearch(searchView, result);
			if(result.hasErrors())
			{
				mav = new ModelAndView("search_view");
				return mav;				
			}
			 
			// Land Region Entity
			boolean state = searchView.isStateChecked();
			boolean district = searchView.isDistrictChecked();
			boolean subdistrict = searchView.isSubDistrictChecked();
			boolean village = searchView.isVillageChecked();
			boolean rural = searchView.isRuralChecked();
			boolean urban = searchView.isUrbanChecked();
			String query = "";
			// Election Constituency Entity
			boolean ac = searchView.isAcChecked();
			boolean pc = searchView.isPcChecked();
			// Local Govt Entity
			boolean panchayat = searchView.isPanchayatChecked();
			boolean tradtional = searchView.isTraditionalChecked();

			String entityCode = "";
			StringBuilder sb = null;
			sb = new StringBuilder();
			if (state == true) {
				sb.append("S,");
			}
			if (district == true) {
				sb.append("D,");
			}
			if (subdistrict == true) {
				sb.append("T,");
			}
			if (village == true) {
				sb.append("V,");
			}
			if (rural == true) {
				sb.append("R,");
			}
			if (panchayat == true) {
				sb.append("P,");
			}
			if (tradtional == true) {
				sb.append("T,");
			}
			if (urban == true) {
				sb.append("U,");
			}
			if (ac == true) {
				sb.append("A,");
			}
			if (pc == true) {
				sb.append("P,");
			}
			entityCode = sb.substring(0, sb.length() - 1);
			// Land Region Entity
			List<Search> searchState = null;
			searchState = new ArrayList<Search>();
			if (state == true || district == true || subdistrict == true
					|| village == true){
				searchState = stateService.getStateSearchDetail(entityName,
						entityCode);
			}	
			// Election Constituency Entity
			List<SearchElectionConstituency> searchEC = null;
			searchEC = new ArrayList<SearchElectionConstituency>();
			if (ac == true || pc == true){
				searchEC = parliamentService
						.getElectionConstituencySearchDetail(entityName,
								entityCode);
			}	
			// Local Govt Body Entity
			List<SearchLocalGovtBody> searchLGB = null;
			searchLGB = new ArrayList<SearchLocalGovtBody>();
			if (rural == true || urban == true || tradtional == true
					|| panchayat == true){
				searchLGB = localGovtBodyService.getLocalGovtBodySearchDetail(
						entityName, entityCode);
			}	
			if (entityCode.contains("S")) {
				model.addAttribute("search_s", searchState);

			}
			if (entityCode.contains("D")) {
				model.addAttribute("search_d", searchState);

			}
			if (entityCode.contains("T")) {
				model.addAttribute("search_sd", searchState);
			}
			if (entityCode.contains("V")) {
				model.addAttribute("search_v", searchState);
			}
			if (entityCode.contains("R")) {
				model.addAttribute("search_r", searchLGB);
			}
			if (entityCode.contains("P")) {
				model.addAttribute("search_p", searchLGB);
			}
			if (entityCode.contains("T")) {
				model.addAttribute("search_t", searchLGB);
			}
			if (entityCode.contains("U")) {
				model.addAttribute("search_u", searchLGB);
			}
			if (entityCode.contains("A")) {
				model.addAttribute("search_a", searchEC);
			}
			if (entityCode.contains("P")) {
				model.addAttribute("search_pc", searchEC);
			}
			mav.addObject("searchView", searchView);
			model.addAttribute("viewPage", "abc");
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

	// Search Entity
	@RequestMapping(value = "/globalsearchentity", method = RequestMethod.GET)
	public ModelAndView searchEntityDetailGlobal(Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("globalsearch_view");
			mav.addObject("globalsearchView", new SearchForm());
			model.addAttribute("initialStep", true);
			model.addAttribute("viewPage", "");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/globalsearchentity", method = RequestMethod.POST)
	public ModelAndView getSearchDetailGlobal(@ModelAttribute("globalsearchView") SearchForm searchView, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		ModelAndView mav = null;
		try {
			/* New Captcha code */
			String captchaAnswer = searchView.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(session, captchaAnswer);
			boolean isBycode=true;
			if (messageFlag) {
				/* End of New captcha code */
				citizenValidator.validateforgsearch(searchView, result); 
				if (result.hasErrors()) {
					result.getAllErrors();
					mav = new ModelAndView("globalsearch_view");
					return mav;
				} else {
					String entityName = searchView.getEntityName();
					Integer entityCodeforsearch=searchView.getEntityCode();
					mav = new ModelAndView("globalsearch_view");
					int flag=0;
					if(entityName != ""){
						flag=1;
						isBycode=false;
					}
						
					else if(entityCodeforsearch!= null)
						flag=1;
					if (flag==0) {
						mav = new ModelAndView("globalsearch_view");
						return mav;
					}
					viewValidator.validateSearch(searchView, result);
				
					// Land Region Entity
					boolean state = searchView.isStateChecked();
					boolean district = searchView.isDistrictChecked();
					boolean subdistrict = searchView.isSubDistrictChecked();
					boolean village = searchView.isVillageChecked();
					boolean rural = searchView.isRuralChecked();
					boolean urban = searchView.isUrbanChecked();
					String query = "";
					// Election Constituency Entity
				//	boolean ac = searchView.isAcChecked();
				//	boolean pc = searchView.isPcChecked();
					// Local Govt Entity
					//boolean panchayat = searchView.isPanchayatChecked();
				//	boolean tradtional = searchView.isTraditionalChecked();

					String entityCode = "";
					StringBuilder sb = null;
					sb = new StringBuilder();
					if (state == true) {
						sb.append("S,");
					}
					if (district == true) {
						sb.append("D,");
					}
					if (subdistrict == true) {
						sb.append("T,");
					}
					if (village == true) {
						sb.append("V,");
					}
					if (urban == true) {
						sb.append("U,");
					}
					if (rural == true) {
						sb.append("R,");
					}
					if(searchView.isOrganistionChecked()){
						sb.append("O,");
					}
					if(searchView.isOrgUnitChecked()){
						sb.append("X,");
					}
					if(searchView.isBlockChecked()){
						sb.append("B,");
					}
					/*if (panchayat == true) {
						sb.append("P,");
					}
					if (tradtional == true) {
						sb.append("T,");
					}*/
					
				/*	if (ac == true) {
						sb.append("A,");
					}
					if (pc == true) {
						sb.append("P,");
					}*/
					entityCode = sb.substring(0, sb.length() - 1);
					List<AllSearch> allSerachDeatail=null;
					if(entityName!="")	
					 allSerachDeatail=stateDao.getAllSearchDetailByCode(entityName,entityCode,isBycode);
					else
					allSerachDeatail=stateDao.getAllSearchDetailByCode(entityCodeforsearch.toString(),entityCode,isBycode);	
					// Land Region Entity
					/*List<Search> searchState = new ArrayList<Search>();
					List<Search> searchStateL = new ArrayList<Search>();
					List<Search> searchDistrict = new ArrayList<Search>();
					List<Search> searchSubdistrict = new ArrayList<Search>();
					List<Search> searchVillage = new ArrayList<Search>();*/
					
					//System.out.println("ssssssssssssssssssss"+allSerachDeatail);
					/*if (state == true || district == true || subdistrict == true || village == true){
						if(null !=entityCodeforsearch)						
							searchState = stateService.getStateSearchDetailByCode(entityCodeforsearch, entityCode);						
						else if(entityName!="" || entityName!=null)
						searchState = stateService.getStateSearchDetail(entityName, entityCode);
						*/
						/*for(int i=0;i<searchState.size();i++){
							if(searchState.get(i).getType()=='S')
							searchStateL.add(searchState.get(i));
						
							if(searchState.get(i).getType()=='D')
								searchDistrict.add(searchState.get(i));
							if(searchState.get(i).getType()=='T')
								searchSubdistrict.add(searchState.get(i));
							if(searchState.get(i).getType()=='V')
								searchVillage.add(searchState.get(i));
						}*/
						/*model.addAttribute("searchStateL", searchStateL);
						model.addAttribute("searchDistrict", searchDistrict);
						model.addAttribute("searchSubdistrict", searchSubdistrict);
						model.addAttribute("searchVillage", searchVillage);
					
					}	*/
					// Election Constituency Entity
					/*List<SearchElectionConstituency> searchEC = new ArrayList<SearchElectionConstituency>();
					List<SearchElectionConstituency> searchAC = new ArrayList<SearchElectionConstituency>();
					List<SearchElectionConstituency> searchPC = new ArrayList<SearchElectionConstituency>();
					if (ac == true || pc == true){
						
						if(null !=entityCodeforsearch)	
							searchEC = parliamentService.getElectionConstituencySearchDetailByCode(entityCodeforsearch, entityCode);
						else if(entityName!="" || entityName!=null)
							searchEC = parliamentService.getElectionConstituencySearchDetail(entityName, entityCode);
						for(int i=0;i<searchEC.size();i++){
							if(searchEC.get(i).getType()=='A')
								searchAC.add(searchEC.get(i));						
							if(searchEC.get(i).getType()=='P')
								searchPC.add(searchEC.get(i));							
						}
						model.addAttribute("searchAC", searchAC);
						model.addAttribute("searchPC", searchPC);	
					}	*/
					// Local Govt Body Entity
				/*	List<SearchLocalGovtBody> searchLGB = new ArrayList<SearchLocalGovtBody>();
					List<SearchLocalGovtBody> searchRural = new ArrayList<SearchLocalGovtBody>();
					List<SearchLocalGovtBody> searchUrban = new ArrayList<SearchLocalGovtBody>();
					if (rural == true || urban == true || tradtional == true || panchayat == true){
						if(null !=entityCodeforsearch)	
							searchLGB = localGovtBodyService.getLocalGovtBodySearchDetailByCode(entityCodeforsearch, entityCode);
						else if(entityName!="" || entityName!=null)
							searchLGB = localGovtBodyService.getLocalGovtBodySearchDetail(entityName, entityCode);
						
						for(int i=0;i<searchLGB.size();i++){
							if((searchLGB.get(i).getCategory()=='P')||(searchLGB.get(i).getCategory()=='T'))
								searchRural.add(searchLGB.get(i));						
							if(searchLGB.get(i).getCategory()=='U')
								searchUrban.add(searchLGB.get(i));							
						}
						model.addAttribute("searchUrban", searchUrban);
						model.addAttribute("searchRural", searchRural);	
					}	
					if (entityCode.contains("S")) {
						model.addAttribute("search_s", searchState);
					}
					if (entityCode.contains("D")) {
						model.addAttribute("search_d", searchState);
					}
					if (entityCode.contains("T") && subdistrict) {
						model.addAttribute("search_sd", searchState);
					}
					if (entityCode.contains("V")) {
						model.addAttribute("search_v", searchState);
					}
					if (entityCode.contains("R")) {
						model.addAttribute("search_r", searchLGB);
					}
					if (entityCode.contains("P")) {
						model.addAttribute("search_p", searchLGB);
					}
					if (entityCode.contains("T") && tradtional) {
						model.addAttribute("search_t", searchLGB);
					}
					if (entityCode.contains("U")) {
						model.addAttribute("search_u", searchLGB);
					}*/
					/*if (entityCode.contains("A")) {
						model.addAttribute("search_a", searchEC);
					}
					if (entityCode.contains("P")) {
						model.addAttribute("search_pc", searchEC);
					}
*/					
					/*if(searchState.isEmpty() && searchLGB.isEmpty() && searchEC.isEmpty()) {
						mav.addObject("nodata", "No maching data found");
					}*/
					model.addAttribute("allSerachDeatail", allSerachDeatail);
					
					mav.addObject("globalsearchView", searchView);
					model.addAttribute("viewPage", "abc");
					model.addAttribute("closeFlag",true);
					searchView.setCaptchaAnswer(null);
				}
			}/* New Captcha code */
			else {
				mav = new ModelAndView("globalsearch_view");
				result.rejectValue("captchaAnswer", "captcha.errorMessage");
				mav.addObject("globalsearchView", searchView);
				model.addAttribute("viewPage", "abc");
				searchView.setCaptchaAnswer(null);
				return mav;
			}
			/* End of Captcha code */
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	
	// Search Entity inside application
	
			@RequestMapping(value = "/searchentityapplication", method = RequestMethod.GET)
			public ModelAndView searchEntityApplication(Model model,HttpServletRequest request)
			{

				ModelAndView mav = null;
				try {
					mav = new ModelAndView("globalsearch_view");
					mav.addObject("globalsearchView", new SearchForm());
					model.addAttribute("viewPage", "");
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
		
		
		@RequestMapping(value = "/searchentityapplication", method = RequestMethod.POST)
		public ModelAndView searchEntityApplication(@ModelAttribute("globalsearchView") SearchForm searchView,BindingResult result,HttpServletRequest request, Model model)
		{
			ModelAndView mav = null;
			try {
				String entityName = searchView.getEntityName();
				mav = new ModelAndView("globalsearch_view");
				if (entityName == "" || entityName == null) {
					// TODO we need to set error message here
					mav = new ModelAndView("globalsearch_view");
					return mav;
				}
				viewValidator.validateSearch(searchView, result);
				if(result.hasErrors())
				{
					mav = new ModelAndView("globalsearch_view");
					return mav;				
				}
				 
				// Land Region Entity
				boolean state = searchView.isStateChecked();
				boolean district = searchView.isDistrictChecked();
				boolean subdistrict = searchView.isSubDistrictChecked();
				boolean village = searchView.isVillageChecked();
				boolean rural = searchView.isRuralChecked();
				boolean urban = searchView.isUrbanChecked();
				String query = "";
				// Election Constituency Entity
				boolean ac = searchView.isAcChecked();
				boolean pc = searchView.isPcChecked();
				// Local Govt Entity
				boolean panchayat = searchView.isPanchayatChecked();
				boolean tradtional = searchView.isTraditionalChecked();

				String entityCode = "";
				StringBuilder sb = null;
				sb = new StringBuilder();
				if (state == true) {
					sb.append("S,");
				}
				if (district == true) {
					sb.append("D,");
				}
				if (subdistrict == true) {
					sb.append("T,");
				}
				if (village == true) {
					sb.append("V,");
				}
				if (rural == true) {
					sb.append("R,");
				}
				if (panchayat == true) {
					sb.append("P,");
				}
				if (tradtional == true) {
					sb.append("T,");
				}
				if (urban == true) {
					sb.append("U,");
				}
				if (ac == true) {
					sb.append("A,");
				}
				if (pc == true) {
					sb.append("P,");
				}
				entityCode = sb.substring(0, sb.length() - 1);
				// Land Region Entity
				List<Search> searchState = null;
				searchState = new ArrayList<Search>();
				if (state == true || district == true || subdistrict == true
						|| village == true){
					searchState = stateService.getStateSearchDetail(entityName,
							entityCode);
				}	
				// Election Constituency Entity
				List<SearchElectionConstituency> searchEC = null;
				searchEC = new ArrayList<SearchElectionConstituency>();
				if (ac == true || pc == true){
					searchEC = parliamentService
							.getElectionConstituencySearchDetail(entityName,
									entityCode);
				}	
				// Local Govt Body Entity
				List<SearchLocalGovtBody> searchLGB = null;
				searchLGB = new ArrayList<SearchLocalGovtBody>();
				if (rural == true || urban == true || tradtional == true
						|| panchayat == true){
					searchLGB = localGovtBodyService.getLocalGovtBodySearchDetail(
							entityName, entityCode);
				}	
				if (entityCode.contains("S")) {
					model.addAttribute("search_s", searchState);

				}
				if (entityCode.contains("D")) {
					model.addAttribute("search_d", searchState);

				}
				if (entityCode.contains("T")) {
					model.addAttribute("search_sd", searchState);
				}
				if (entityCode.contains("V")) {
					model.addAttribute("search_v", searchState);
				}
				if (entityCode.contains("R")) {
					model.addAttribute("search_r", searchLGB);
				}
				if (entityCode.contains("P")) {
					model.addAttribute("search_p", searchLGB);
				}
				if (entityCode.contains("T")) {
					model.addAttribute("search_t", searchLGB);
				}
				if (entityCode.contains("U")) {
					model.addAttribute("search_u", searchLGB);
				}
				if (entityCode.contains("A")) {
					model.addAttribute("search_a", searchEC);
				}
				if (entityCode.contains("P")) {
					model.addAttribute("search_pc", searchEC);
				}
				mav.addObject("globalsearchView", searchView);
				model.addAttribute("viewPage", "abc");
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

		
	// View Ministry
	@RequestMapping(value = "/viewministry", method = RequestMethod.GET)
	public ModelAndView showMinistryPage(Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_ministryList");
			mav.addObject("viewMinistry", new MinistryForm());
			model.addAttribute("viewPage", "");
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

	@RequestMapping(value = "/viewministrypost", method = RequestMethod.POST)
	public ModelAndView getMinistryPage(
			@ModelAttribute("viewMinistry") MinistryForm viewMinistry,
			BindingResult result, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		try {

			ministryValidator.viewValidateMinistry(viewMinistry, result);
			if (result.hasErrors()) {
				result.getAllErrors();
				// mav.addObject("districtbean", districtbean);
				mav = new ModelAndView("view_ministryList");
				return mav;
			} else {
				String ministryCode = viewMinistry.getCode();
				String ministryName = viewMinistry.getMinistryName();
				/* int recordsLimit=viewMinistry.getRecordsLimit(); */
				String statName = viewMinistry.getStateNameEnglish();
				String query = "";
				if (!ministryName.equals("") && ministryCode.equals("")) {
					query = "from Organization org where org.organizationType.orgTypeCode=1 and isactive=true and upper(org.orgName) like '"
							+ ministryName.trim().toUpperCase() + "%'";
				} else if (ministryName.equals("") && !ministryCode.equals("")) {
					query = "from Organization org where org.organizationType.orgTypeCode=1 and isactive=true and org.organizationPK.orgCode="
							+ ministryCode;
				} else if (!ministryName.equals("") && !ministryCode.equals("")) {
					query = "from Organization org where org.organizationType.orgTypeCode=1 and isactive=true and org.organizationPK.orgCode="
							+ ministryCode
							+ "and upper(org.orgName) like '"
							+ ministryName.trim().toUpperCase() + "%'";
				} else if (ministryName.equals("") && ministryCode.equals("")) {
					query = "from Organization org where org.organizationType.orgTypeCode=1 and isactive=true order by orgName";
				} 
				List<Organization> listMinistryDetails = null;
				listMinistryDetails = new ArrayList<Organization>();
				listMinistryDetails = organisationService
						.getMinistryDetailList(query);
				model.addAttribute("ministrydetail", listMinistryDetails);
				viewMinistry.setListMinistryDetail(listMinistryDetails);
				mav = new ModelAndView("view_ministryList");
				/* mav.addObject("ministrydetail", listMinistryDetails); */

				// mav.addObject("viewMinistry", viewMinistry);
				model.addAttribute("viewPage", "abc");

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

	// View Ministry Detail
	@RequestMapping(value = "/viewMinistryDetail")
	public ModelAndView viewMinistryDetail(
			@ModelAttribute("viewMinistry") MinistryForm viewMinistry,
			Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		
		String id = request.getParameter("id");
		int orgCode=(viewMinistry.getMinistryId()==null)?Integer.parseInt(id):viewMinistry.getMinistryId();
		//int orgCode=viewMinistry.getMinistryId();
		try {
			List<MinistryForm> listMinistry = null;
			listMinistry = new ArrayList<MinistryForm>();
			listMinistry = organisationService.getMinistryDetails(orgCode);
			mav = new ModelAndView("view_ministrydetail");
			model.addAttribute("listMinistry", listMinistry);
			viewMinistry.setListMinistry(listMinistry);
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

	// View Department
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewdepartment", method = RequestMethod.GET)
	public ModelAndView showDepartmentPage(Model model, HttpSession session,
			@ModelAttribute("viewDept") MinistryForm viewDept,
			HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			int stateCode = getStateCode(session);
			mav = new ModelAndView("view_departmentList");
			request.setAttribute("stateCode", stateCode);
			model.addAttribute("viewPage", "");
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

	// View Department FOR STATE
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewdepartmentforstate", method = RequestMethod.GET)
	public ModelAndView showDepartmentPage1(Model model, HttpSession session,
			@ModelAttribute("viewDeptforstate") MinistryForm viewDept,
			HttpServletRequest request) {
		//System.out.println("sivvvvvvvvvvvvvvvvvvvvvvvvv in organization state");
		ModelAndView mav = null;
		try {
			int stateCode = getStateCode(session);
			mav = new ModelAndView("view_departmentList1");
			request.setAttribute("stateCode", stateCode);
			model.addAttribute("viewPage", "");
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
	
	
	
	
	@ModelAttribute("ministryList")
	public List<Organization> populateMinistryList(HttpServletRequest request) {
		try {
			return organisationService.getMinistryList();
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}
	}

	@ModelAttribute("localBodyType")
	public List<LocalBodyType> populateLBTypeList(Model model,
			HttpSession session, HttpServletRequest request) {

		try {
			int stateCode = getStateCode(session);
			model.addAttribute("stateCode", stateCode);
			return localGovtBodyService.getLGTypeForGovtBody();
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			// mav = new ModelAndView(redirectPath);
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/viewdepartmentbyministrycode", method = RequestMethod.POST)
	public ModelAndView getDeptByMinCode(HttpSession session, @ModelAttribute("viewDept") MinistryForm viewDept, HttpServletRequest request, Model model) {

		ModelAndView mav = null;
		Session session1=sessionFactory.openSession();
		try {
			char entityCode = 'C';
			char level = ' ';
			char levelCode = 'S';
			List<DistrictLineDepartment> listDistDept = null;
			mav = new ModelAndView("view_departmentList");
			char category = viewDept.getCategory();

			String statecode = (String) session.getAttribute("stateCode");
			int lBTypeCode = viewDept.getLocalBodyTypeCode();
			int lBCode = viewDept.getLocalBodyCode();

			statecode = (String) session.getAttribute("stateCode");

			List<LocalBodyTypeWiseDepartment> listLBTWiseDeptList = null;
			if (lBTypeCode != 0) {
				listLBTWiseDeptList = localGovtBodyService.getLocalBodyTypeWiseDeptList(Integer.parseInt(statecode), lBTypeCode, levelCode);
			}
			if (lBCode != 0) {
				listLBTWiseDeptList = localGovtBodyService.getLocalBodyWiseDeptList(Integer.parseInt(statecode), lBCode, levelCode);
			}
			model.addAttribute("listLBTWiseDeptList", listLBTWiseDeptList);
			viewDept.setListLBTWiseDeptList(listLBTWiseDeptList);

			if (category != 'N' && category == 'S') {
				// String statecode=(String)session.getAttribute("stateCode");
				int deptCode = 2;
				char entityType = 'S';
				List<StateLineDepartment> listDept = null;
				listDept = new ArrayList<StateLineDepartment>();
				listDept = organisationService.getStateLineDepartmentList(Integer.parseInt(statecode), deptCode, entityType);
				model.addAttribute("listDept", listDept);
				viewDept.setListDept(listDept);
				session.setAttribute("category", "S");
				// ////////
				if (listDept.size() > 0) {
					mav = new ModelAndView("view_departmentList");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Department List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// ////////
			} else if (category != 'N' && category == 'D') {
				level = 'D';
				int districtCode = viewDept.getDistrictCode();
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getDistrictLineDepartmentList(districtCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				session.setAttribute("category", "D");
				// /////////
				if (listDistDept.size() > 0) {
					mav = new ModelAndView("view_departmentList");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Department List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// ////////
			} else if (category != 'N' && category == 'T') {
				level = 'T';
				int subDistrictCode = Integer.parseInt(viewDept.getSubdistrictNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getSubDistrictLineDepartmentList(subDistrictCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				// //////
				if (listDistDept.size() > 0) {
					mav = new ModelAndView("view_departmentList");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Department List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// /////
			} else if (category != 'N' && category == 'B') {
				level = 'V';
				int blockCode = Integer.parseInt(viewDept.getBlockNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getBlockLineDepartmentList(blockCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				// ////////
				if (listDistDept.size() > 0) {
					mav = new ModelAndView("view_departmentList");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Department List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// ////////
			} else if (category != 'N' && category == 'V') {
				level = 'V';
				int villageCode = Integer.parseInt(viewDept.getVillageNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getVillageLineDepartmentList(villageCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				// ///////////
				if (listDistDept.size() > 0) {
					mav = new ModelAndView("view_departmentList");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Department List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// ///////////

			}
			/*
			 * System.out.println("Level : "+level); int
			 * districtCode=viewDept.getDistrictCode();
			 * System.out.println("districtCode :: "+districtCode);
			 * if(districtCode!=0 && category != 'N') { listDistDept = new
			 * ArrayList<DistrictLineDepartment>(); listDistDept =
			 * organisationService
			 * .getDistrictLineDepartmentList(districtCode,level);
			 * model.addAttribute("listDistDept", listDistDept);
			 * viewDept.setListDistDept(listDistDept); }
			 */

			if (!viewDept.getMinistryName().equals("")) {
				int orgCode = Integer.parseInt(viewDept.getMinistryName());

				String query = "from Organization org where org.parentorgcode=" + orgCode + "and org.isactive=true order by orgName";

				List<Organization> listDeptDetails = null;
				List<Organization> listOrg = null;
				session.setAttribute("category", "S");
				listDeptDetails = new ArrayList<Organization>();

				listDeptDetails = organisationService.getDepartmentList(query);

				model.addAttribute("listDepartment", listDeptDetails);
				viewDept.setListDeptDetails(listDeptDetails);

				listOrg = new ArrayList<Organization>();
				if (viewDept.getMinistryName() != null) {
					listOrg = session1.createQuery("from Organization org where org.organizationType.orgTypeCode=1 and org.isactive=true order by orgName").list();
				}
				/*
				 * else { listOrg=sessionFactory.openSession().createQuery(
				 * "from Organization org where isactive=true and org.parentorgcode="
				 * + orgCode).list(); }
				 */
				model.addAttribute("ministryList", listOrg);

			}
			/*
			 * else if(viewDept.getMinistryName().equals("")) {
			 * List<DepartmentByCentreLevel> listDeptDetails= null;
			 * 
			 * listDeptDetails = new ArrayList<DepartmentByCentreLevel>();
			 * 
			 * listDeptDetails=organisationService.getDepartmentListByCentreLevel
			 * ( entityCode); model.addAttribute("listDepartment",
			 * listDeptDetails); }
			 */
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
		}
		return mav;
	}
	
	
	/**
	 * @Modified by sushil on 16-05-2013 for delete center level department
	 * @return mav
	 */
	@RequestMapping(value = "/abolishDepartment")
	public ModelAndView abolishDepartment(@ModelAttribute("viewDept") MinistryForm viewDept, BindingResult result, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		
		try {
			Object id= session.getAttribute("centerOrgId");
			int deptId = id != null ? Integer.valueOf(id.toString()) : 0;
			viewDept.setDeptCode(deptId);

			boolean isCommitted = false;
			if(deptId > 0) {
				GovernmentOrder governmentOrder = organisationService.getGovtOrderObject(viewDept);
				governmentOrder.setGovtOrderConfig("govtOrderUpload");
				
				List<CommonsMultipartFile> list = viewDept.getAttachFile1();
				if(list != null && list.size() > 0) {
					AddAttachmentBean attachmentBean = new AddAttachmentBean();
					attachmentBean.setUploadLocation(ApplicationConstant.getDefaultFilePath());
					attachmentBean.setCurrentlyUploadFileList(viewDept.getAttachFile1());
					SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
					String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
					List<AttachedFilesForm> metaInfoList  = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
					
					if (metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment)) {
						String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoList, attachmentBean);
						if("saveSuccessFully".equals(saveAttachment)) {
							AttachedFilesForm attachedFilesForm = metaInfoList.get(0);
							Attachment attachment = new Attachment();
							attachment.setFileLocation(attachmentBean.getUploadLocation().replaceAll("\\\\", "/")+attachedFilesForm.getFileTimestampName());
							attachment.setFileName(attachedFilesForm.getFileName());
							attachment.setFileTimestamp(attachedFilesForm.getFileTimestampName());
							attachment.setFileTitle(attachedFilesForm.getFileTitle());
							attachment.setFileSize(attachedFilesForm.getFileSize());
							attachment.setFileContentType(attachedFilesForm.getFileType());
							List<Attachment> attachlist = new ArrayList<Attachment>();
							attachlist.add(attachment);
						}
					}	
				}
			
				govtOrderValidator.validate(governmentOrder, result);
				if(result.hasErrors()) {
					mav = new ModelAndView("Deletedepartmentforcenter");
					List<MinistryForm> listMinistry =  new ArrayList<MinistryForm>();
					listMinistry = organisationService.getMinistryDetails(deptId);
					viewDept.setListMinistry(listMinistry);
					model.addAttribute("listMinistry", listMinistry);
					mav.addObject("viewDept", viewDept);
					return mav;
				}
				viewDept.setGovernmentOrder(governmentOrder);
				isCommitted = organisationService.abolishDepartment(deptId);
				session.removeAttribute("centerOrgId");
			}

			if (isCommitted) {
				String aMessage = "The Selected Department Has Delete.!";
				mav = new ModelAndView("success");
				mav.addObject("msgid", aMessage);
				return mav;
			} else {
				model.addAttribute("msgid", "The Selected Department Has Failed To Delete. !");
				mav = new ModelAndView("success");
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

	
	// view Organization Part

	@RequestMapping(value = "/vieworganization", method = RequestMethod.GET)
	public ModelAndView showOrganizationPage(Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_organizationList");
			mav.addObject("viewDept", new MinistryForm());
			List<Organization> list = organisationService.getMinistryList();
			model.addAttribute("userMinistryList", list);
			model.addAttribute("isSearchPage", true);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
	
	
	
	
		
		

	// view Organization Part for state
	@RequestMapping(value = "/vieworganizationforstate", method = RequestMethod.GET)
	public ModelAndView showOrganizationPage1(Model model,HttpServletRequest request,HttpSession httpSession)
	{
		//System.out.println("******************************vieworganizationforstate************************************");
		ModelAndView mav = null;
		Integer stateCode=null;
		Integer slc=null;
		try
		{
			if(httpSession.getAttribute("stateCode")!=null)
			{
				stateCode=Integer.parseInt(httpSession.getAttribute("stateCode").toString());
			}
			
			slc=commonService.getSlc(stateCode);
			mav = new ModelAndView("view_organizationList1");
			
			mav.addObject("viewDeptforstate", new MinistryForm());
			model.addAttribute("viewPage", "");
			model.addAttribute("stateDeptList", organisationService.getDepartmentList(
					"from Organization o where o.organizationType.orgTypeCode=2 and o.isactive=true and o.slc="+slc));
			
		} 
		catch (Exception e) 
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			
			return mav;
		}
		return mav;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/vieworganizationbydepartmentcodeforState", method = RequestMethod.POST)
	public ModelAndView getOrgByDeptCodeforStateLevel(HttpSession session,@ModelAttribute("viewDeptforstate") MinistryForm viewDept,HttpServletRequest request,HttpSession httpSession, Model model)
	{
		ModelAndView mav = null;
		Integer orgCode=null;
		Integer stateCode=null;
		Integer slc=null;
		List<Organization> orgList=null;
		List<DistrictLineDepartment> listDistDept = null;
		try {
			
			if(httpSession.getAttribute("stateCode")!=null)
			{
				stateCode=Integer.parseInt(httpSession.getAttribute("stateCode").toString());
			}
			
			slc=commonService.getSlc(stateCode);
			
			char category = viewDept.getCategory();
			session.setAttribute("category", category);
			Integer districtCode = viewDept.getDistrictCode();
			
			Integer subDistrictCode=null; 
			if(viewDept.getSubdistrictNameEnglish()!=null)
			{
				subDistrictCode=Integer.parseInt(viewDept.getSubdistrictNameEnglish());
			}
			
			Integer villageCode=null;
			if(viewDept.getVillageNameEnglish()!=null)
			{
				villageCode=Integer.parseInt(viewDept.getVillageNameEnglish());
			}
		
			           
			Integer blockCode=null;
			if(viewDept.getBlockNameEnglish()!=null)
			{
				blockCode=Integer.parseInt(viewDept.getBlockNameEnglish());
			}
			
			Integer entityCode=null;
			if (viewDept.getDeptName().length() > 0){
				orgCode = Integer.parseInt(viewDept.getDeptName()); // org code
																	// of
																	// selected
																	// department
			}	
			if (orgCode != null) {
				orgList = organisationService
						.getDepartmentListByMinistry(orgCode);
				
				if(orgList.size()>0)
				{
					if(category=='S') 
					{
					
						
						model.addAttribute("listDeptstate", orgList);
					}
					else
					{
						switch (category)
						{
						case 'A' :
							entityCode=viewDept.getAdminUnitCode();
							break;
						case 'D':
							entityCode=districtCode;
							break;
						case 'T':
							entityCode=subDistrictCode;
							break;
						case 'V':
							entityCode=villageCode;
							break;
						case 'B':	
							entityCode=blockCode;
							break;
						}
						listDistDept = organisationService.getLandRegionLineDepartmentListforOrganization(entityCode, category,orgList);
						if(listDistDept.isEmpty())
						{
							mav = new ModelAndView("error");
							String errorMsg = "Organization List is not available";
							model.addAttribute("message", errorMsg);
							return mav;
						}
						model.addAttribute("listDept", listDistDept);
						

					}
					
					model.addAttribute("stateDeptList", organisationService.getDepartmentList(
							"from Organization o where o.organizationType.orgTypeCode=2 and o.isactive=true and o.slc="+slc));
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				}
				

				 else {
					mav = new ModelAndView("success");
					model.addAttribute("msgid", "Organization List is not available");
					return mav;
				}

			} else {
				mav = new ModelAndView("error");
				String errorMsg = "Organization List is not available";
				model.addAttribute("message", errorMsg);
				return mav;
			}
			
			
			/*	model.addAttribute("listDept", listDept);
				viewDept.setListDept(listDept);
				// //////
				if (listDept.size() > 0) {
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				
				
				
			}
			
			
			
			char entityCode = 'C';
			mav = new ModelAndView("view_organizationList1");

			List<Organization> listOrg = new ArrayList<Organization>();
			List<Organization> deptList = new ArrayList<Organization>();
			if (viewDept.getDeptName() != null) {
				int orgCode = Integer.parseInt(viewDept.getDeptName());

				String query = "from Organization org where isactive=true and org.parentorgcode="
						+ orgCode;

				List<Organization> listOrganizationDetails = null;

				listOrganizationDetails = new ArrayList<Organization>();

				listOrganizationDetails = organisationService
						.getOrganizationList(query);

				listOrg = new ArrayList<Organization>();
				if (viewDept.getDeptName() != null) {
					listOrg = sessionFactory
							.openSession()
							.createQuery(
									"from Organization o where o.isactive=true and "
											+ "o.organizationType.orgTypeCode=1")
							.list();
				}
				model.addAttribute("ministryList", listOrg);
				model.addAttribute("departmentList", deptList);
				model.addAttribute("listOrganization", listOrganizationDetails);
				viewDept.setListOrgDetails(listOrganizationDetails);
				model.addAttribute("viewPage", "abc");
			} else {
				List<OrganizationByCentreLevel> listOrganizationDetails = null;

				listOrganizationDetails = new ArrayList<OrganizationByCentreLevel>();

				listOrganizationDetails = organisationService
						.getOrganizationListByCentreLevel(entityCode);
				model.addAttribute("listOrganization", listOrganizationDetails);
			}

			// //////////////////////////////////////////////////////////////////
			char level = ' ';
			char levelCode = 'S';
			List<DistrictLineDepartment> listDistDept = null;
			char category = viewDept.getCategory();

			String statecode = (String) session.getAttribute("stateCode");
			int lBTypeCode = viewDept.getLocalBodyTypeCode();
			int lBCode = viewDept.getLocalBodyCode();

			statecode = (String) session.getAttribute("stateCode");

			List<LocalBodyTypeWiseDepartment> listLBTWiseDeptList = null;
			if (lBTypeCode != 0) {
				listLBTWiseDeptList = localGovtBodyService
						.getLocalBodyTypeWiseDeptList(
								Integer.parseInt(statecode), lBTypeCode,
								levelCode);
			}
			if (lBCode != 0) {
				listLBTWiseDeptList = localGovtBodyService
						.getLocalBodyWiseDeptList(Integer.parseInt(statecode),
								lBCode, levelCode);
			}
			model.addAttribute("listLBTWiseDeptList", listLBTWiseDeptList);
			viewDept.setListLBTWiseDeptList(listLBTWiseDeptList);

			if (category != 'N' && category == 'S') 
			{
				// String statecode=(String)session.getAttribute("stateCode");
				int deptCode = 2;
				char entityType = 'S';
				List<StateLineDepartment> listDept = null;
				listDept = new ArrayList<StateLineDepartment>();
				listDept = organisationService.getStateLineDepartmentList(
						Integer.parseInt(statecode), deptCode, entityType);
				model.addAttribute("listDept", listDept);
				viewDept.setListDept(listDept);
				// //////
				if (listDept.size() > 0) {
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// //////
			}
			else if (category != 'N' && category == 'D')
			{
				level = 'D';
				int districtCode = viewDept.getDistrictCode();
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService
						.getDistrictLineDepartmentList(districtCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				// //////
				if (listDistDept.size() > 0)
				{
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				}
				else 
				{
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
			} 
			else if (category != 'N' && category == 'T') 
			{
				level = 'T';
				int subDistrictCode = Integer.parseInt(viewDept
						.getSubdistrictNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService
						.getSubDistrictLineDepartmentList(subDistrictCode,
								level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				// //////
				if (listDistDept.size() > 0)
				{
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				}
				else
				{
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// //////
			}
			else if (category != 'N' && category == 'B') 
			{
				level = 'V';
				int blockCode = Integer
						.parseInt(viewDept.getBlockNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getBlockLineDepartmentList(
						blockCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				model.addAttribute("viewPage", "def");
			} 
			else if (category != 'N' && category == 'V')
			{
				level = 'V';
				int villageCode = Integer.parseInt(viewDept.getVillageNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getVillageLineDepartmentList(villageCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				if (listDistDept.size() > 0)
				{
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				}
				else
				{
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
			}*/
		}
		catch (Exception e)
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		
	}


	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/vieworganizationbydepartmentcode", method = RequestMethod.POST)
	public ModelAndView getOrgByDeptCode(HttpSession session,@ModelAttribute("viewDeptforstate") MinistryForm viewDept,	HttpServletRequest request, Model model)
	{
		ModelAndView mav = null;
		try {
			char entityCode = 'C';
			mav = new ModelAndView("view_organizationList1");

			List<Organization> listOrg = new ArrayList<Organization>();
			List<Organization> deptList = new ArrayList<Organization>();
			if (viewDept.getDeptName() != null) {
				int orgCode = Integer.parseInt(viewDept.getDeptName());

				String query = "from Organization org where isactive=true and org.parentorgcode="
						+ orgCode;

				List<Organization> listOrganizationDetails = null;

				listOrganizationDetails = new ArrayList<Organization>();

				listOrganizationDetails = organisationService
						.getOrganizationList(query);

				listOrg = new ArrayList<Organization>();
				if (viewDept.getDeptName() != null) {
					listOrg = sessionFactory
							.openSession()
							.createQuery(
									"from Organization o where o.isactive=true and "
											+ "o.organizationType.orgTypeCode=1")
							.list();
				}
				model.addAttribute("ministryList", listOrg);
				model.addAttribute("departmentList", deptList);
				model.addAttribute("listOrganization", listOrganizationDetails);
				viewDept.setListOrgDetails(listOrganizationDetails);
				model.addAttribute("viewPage", "abc");
			} else {
				List<OrganizationByCentreLevel> listOrganizationDetails = null;

				listOrganizationDetails = new ArrayList<OrganizationByCentreLevel>();

				listOrganizationDetails = organisationService
						.getOrganizationListByCentreLevel(entityCode);
				model.addAttribute("listOrganization", listOrganizationDetails);
			}

			// //////////////////////////////////////////////////////////////////
			char level = ' ';
			char levelCode = 'S';
			List<DistrictLineDepartment> listDistDept = null;
			char category = viewDept.getCategory();

			String statecode = (String) session.getAttribute("stateCode");
			int lBTypeCode = viewDept.getLocalBodyTypeCode();
			int lBCode = viewDept.getLocalBodyCode();

			statecode = (String) session.getAttribute("stateCode");

			List<LocalBodyTypeWiseDepartment> listLBTWiseDeptList = null;
			if (lBTypeCode != 0) {
				listLBTWiseDeptList = localGovtBodyService
						.getLocalBodyTypeWiseDeptList(
								Integer.parseInt(statecode), lBTypeCode,
								levelCode);
			}
			if (lBCode != 0) {
				listLBTWiseDeptList = localGovtBodyService
						.getLocalBodyWiseDeptList(Integer.parseInt(statecode),
								lBCode, levelCode);
			}
			model.addAttribute("listLBTWiseDeptList", listLBTWiseDeptList);
			viewDept.setListLBTWiseDeptList(listLBTWiseDeptList);

			if (category != 'N' && category == 'S') 
			{
				// String statecode=(String)session.getAttribute("stateCode");
				int deptCode = 2;
				char entityType = 'S';
				List<StateLineDepartment> listDept = null;
				listDept = new ArrayList<StateLineDepartment>();
				listDept = organisationService.getStateLineDepartmentList(
						Integer.parseInt(statecode), deptCode, entityType);
				model.addAttribute("listDept", listDept);
				viewDept.setListDept(listDept);
				// //////
				if (listDept.size() > 0) {
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// //////
			}
			else if (category != 'N' && category == 'D')
			{
				level = 'D';
				int districtCode = viewDept.getDistrictCode();
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService
						.getDistrictLineDepartmentList(districtCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				// //////
				if (listDistDept.size() > 0)
				{
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				}
				else 
				{
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
			} 
			else if (category != 'N' && category == 'T') 
			{
				level = 'T';
				int subDistrictCode = Integer.parseInt(viewDept
						.getSubdistrictNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService
						.getSubDistrictLineDepartmentList(subDistrictCode,
								level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				// //////
				if (listDistDept.size() > 0)
				{
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				}
				else
				{
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// //////
			}
			else if (category != 'N' && category == 'B') 
			{
				level = 'V';
				int blockCode = Integer
						.parseInt(viewDept.getBlockNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getBlockLineDepartmentList(
						blockCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				model.addAttribute("viewPage", "def");
			} 
			else if (category != 'N' && category == 'V')
			{
				level = 'V';
				int villageCode = Integer.parseInt(viewDept.getVillageNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getVillageLineDepartmentList(villageCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				if (listDistDept.size() > 0)
				{
					mav = new ModelAndView("view_organizationList1");
					model.addAttribute("viewPage", "abc");
					return mav;
				}
				else
				{
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
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

	/* Modified by sushil on 20-05-2013*/
	@RequestMapping(value = "/vieworganizationbydepartmentcodeCenter", method = RequestMethod.POST)
	public ModelAndView getOrgByDeptCodeCenter(HttpSession session, @ModelAttribute("viewDept") MinistryForm viewDept, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView("view_organizationList");
		try {
			char entityCode = 'C';
			if (viewDept.getDeptName() != null) {
				int orgCode = Integer.parseInt(viewDept.getDeptName());
				List<Organization> list = organisationService.getMinistryList();
				List<Organization> deptList = organisationService.getDepartmentListByMinistry(Integer.valueOf(viewDept.getMinistryName()));
				List<Organization> orgList = organisationService.getDepartmentListByMinistry(orgCode);
						
				model.addAttribute("userMinistryList", list);
				model.addAttribute("departmentList", deptList);
				model.addAttribute("listOrganization", orgList);
				model.addAttribute("isSearchPage", false);
			} else {
				List<OrganizationByCentreLevel> listOrganizationDetails = null;
				listOrganizationDetails = new ArrayList<OrganizationByCentreLevel>();
				listOrganizationDetails = organisationService.getOrganizationListByCentreLevel(entityCode);
				model.addAttribute("listOrganization", listOrganizationDetails);
			}

			// //////////////////////////////////////////////////////////////////
			char level = ' ';
			char levelCode = 'S';
			List<DistrictLineDepartment> listDistDept = null;
			char category = viewDept.getCategory();

			String statecode = (String) session.getAttribute("stateCode");
			int lBTypeCode = viewDept.getLocalBodyTypeCode();
			int lBCode = viewDept.getLocalBodyCode();

			statecode = (String) session.getAttribute("stateCode");

			List<LocalBodyTypeWiseDepartment> listLBTWiseDeptList = null;
			if (lBTypeCode != 0) {
				listLBTWiseDeptList = localGovtBodyService.getLocalBodyTypeWiseDeptList(Integer.parseInt(statecode), lBTypeCode, levelCode);
			}
			if (lBCode != 0) {
				listLBTWiseDeptList = localGovtBodyService.getLocalBodyWiseDeptList(Integer.parseInt(statecode), lBCode, levelCode);
			}
			model.addAttribute("listLBTWiseDeptList", listLBTWiseDeptList);
			viewDept.setListLBTWiseDeptList(listLBTWiseDeptList);

			if (category != 'N' && category == 'C') {
				// String statecode=(String)session.getAttribute("stateCode");
				int deptCode = 2;
				char entityType = 'S';
				List<StateLineDepartment> listDept = null;
				listDept = new ArrayList<StateLineDepartment>();
				listDept = organisationService.getStateLineDepartmentList(Integer.parseInt(statecode), deptCode, entityType);
				model.addAttribute("listDept", listDept);
				viewDept.setListDept(listDept);
				// //////
				if (listDept.size() > 0) {
					mav = new ModelAndView("view_organizationList");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// //////
			} else if (category != 'N' && category == 'D') {
				level = 'D';
				int districtCode = viewDept.getDistrictCode();
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getDistrictLineDepartmentList(districtCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				// //////
				if (listDistDept.size() > 0) {
					mav = new ModelAndView("view_organizationList");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
			} else if (category != 'N' && category == 'T') {
				level = 'T';
				int subDistrictCode = Integer.parseInt(viewDept.getSubdistrictNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getSubDistrictLineDepartmentList(subDistrictCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				// //////
				if (listDistDept.size() > 0) {
					mav = new ModelAndView("view_organizationList");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
				}
				// //////
			} else if (category != 'N' && category == 'B') {
				level = 'V';
				int blockCode = Integer.parseInt(viewDept.getBlockNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getBlockLineDepartmentList(blockCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				model.addAttribute("viewPage", "def");
			} else if (category != 'N' && category == 'V') {
				level = 'V';
				int villageCode = Integer.parseInt(viewDept.getVillageNameEnglish());
				listDistDept = new ArrayList<DistrictLineDepartment>();
				listDistDept = organisationService.getVillageLineDepartmentList(villageCode, level);
				model.addAttribute("listDistDept", listDistDept);
				viewDept.setListDistDept(listDistDept);
				if (listDistDept.size() > 0) {
					mav = new ModelAndView("view_organizationList");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Organization List is not available";
					model.addAttribute("message", errorMsg);
					return mav;
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

	// View Organization Details for State
	@RequestMapping(value = "/viewOrganizationStateDetail")
	public ModelAndView viewOrgStateDetail(@ModelAttribute("viewOrganizationStateDetail") MinistryForm viewOrgState,Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		int orgCode=viewOrgState.getOrganizationId();
		try
		{
			char category=viewOrgState.getCategory();
			mav = new ModelAndView("view_orgStatedetail");
			if(category=='S')
			{
			List<Organization> listOrganizationDetails = null;
			listOrganizationDetails = new ArrayList<Organization>();
			listOrganizationDetails=organisationService.getOrganizationDetails(orgCode);
			//model.addAttribute("listMinistry", listOrganizationDetails);
			mav.addObject("listMinistry", listOrganizationDetails);
			}
			else
			{
				List<OrgLocatedAtLevels> listOrganizationDetails =new ArrayList<OrgLocatedAtLevels>();
				listOrganizationDetails=organisationService.getOrganizationDetailsLowLevel(orgCode);
				if(!listOrganizationDetails.isEmpty())
				{
				OrgLocatedAtLevels orgLocatedAtLevelsDetails=listOrganizationDetails.get(0);
				List<Organization>organizationList=new ArrayList<Organization>();
			
				Organization element=new Organization();
				element.setOrgName(orgLocatedAtLevelsDetails.getOrgLevelSpecificName());
				element.setOrgNameLocal(orgLocatedAtLevelsDetails.getOrgLevelSpecificNameLocal());
				element.setShortName(orgLocatedAtLevelsDetails.getOrgLevelSpecificShortName());
				organizationList.add(0, element);
				
				mav.addObject("listMinistry", organizationList);
				}
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
	
	// View Organization Details for Center
	@RequestMapping(value = "/viewOrganizationCenterDetail")
	public ModelAndView viewOrgCenterDetail(@ModelAttribute("viewOrganizationCenterDetail") MinistryForm viewOrgCenter,Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		int orgCode= viewOrgCenter.getOrganizationId();
		try
		{
			mav = new ModelAndView("view_orgCenterdetail");
			List<Organization> listOrganizationDetails = null;
			listOrganizationDetails = new ArrayList<Organization>();
			listOrganizationDetails=organisationService.getOrganizationDetails(orgCode);
			//model.addAttribute("listMinistry", listOrganizationDetails);
			mav.addObject("listMinistry", listOrganizationDetails);
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
	 * Modified by sushil on 14-06-2013
	 */
	@RequestMapping(value = "/abolishOrganization")
	public ModelAndView abolishOrganization(@ModelAttribute("viewDept") MinistryForm viewDept, BindingResult result, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		Integer id = (Integer) session.getAttribute("centerOrgId");
		try {
			List<Organization> listDeptDetails = null;
			listDeptDetails = new ArrayList<Organization>();
			mav = new ModelAndView("DeleteOrganizationState");
			boolean isCommitted = false;
			if(id > 0) {
				/* added by sushil on 14-06-2013*/
				GovernmentOrder governmentOrder = organisationService.getGovtOrderObject(viewDept);
				governmentOrder.setGovtOrderConfig("govtOrderUpload");
				
				List<CommonsMultipartFile> list = viewDept.getAttachFile1();
				if(list != null && list.size() > 0) {
					AddAttachmentBean attachmentBean = new AddAttachmentBean();
					attachmentBean.setUploadLocation(ApplicationConstant.getDefaultFilePath());
					attachmentBean.setCurrentlyUploadFileList(viewDept.getAttachFile1());
					SubAttachmentHandler attachmentHandler = new SubAttachmentHandler();
					String validateAttachment = attachmentHandler.validateAttachment(attachmentBean);
					List<AttachedFilesForm> metaInfoList  = attachmentHandler.getMetaDataListOfFiles(attachmentBean);
					
					if (metaInfoList != null && "validationSuccessFullyDone".equals(validateAttachment)) {
						String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoList, attachmentBean);
						if("saveSuccessFully".equals(saveAttachment)) {
							AttachedFilesForm attachedFilesForm = metaInfoList.get(0);
							Attachment attachment = new Attachment();
							attachment.setFileLocation(attachmentBean.getUploadLocation().replaceAll("\\\\", "/")+attachedFilesForm.getFileTimestampName());
							attachment.setFileName(attachedFilesForm.getFileName());
							attachment.setFileTimestamp(attachedFilesForm.getFileTimestampName());
							attachment.setFileTitle(attachedFilesForm.getFileTitle());
							attachment.setFileSize(attachedFilesForm.getFileSize());
							attachment.setFileContentType(attachedFilesForm.getFileType());
							List<Attachment> attachlist = new ArrayList<Attachment>();
							attachlist.add(attachment);
						}
					}	
				}
			
				govtOrderValidator.validate(governmentOrder, result);
				if(result.hasErrors()) {
					mav = new ModelAndView("DeleteOrganizationCenterDetail");
					List<Organization> listOrganizationDetails =  new ArrayList<Organization>();
					listOrganizationDetails = organisationService.getOrganizationDetails(id);
					mav.addObject("listMinistry", listOrganizationDetails);
					return mav;
				}
				viewDept.setGovernmentOrder(governmentOrder);
				isCommitted = organisationService.abolishDepartment(id);
			}
			
			session.removeAttribute("centerOrgId");
			if (isCommitted == true) {
				int olc = id;
				String query = "from Organization org where isactive=true and org.parentorgcode=" + olc;
				listDeptDetails = organisationService.getDepartmentList(query);
				model.addAttribute("listDepartment", listDeptDetails);
				viewDept.setListDeptDetails(listDeptDetails);
				mav.addObject("viewDept", viewDept);
				model.addAttribute("msgid", "The Selected Department Has Successfully Deleted. !");
				mav = new ModelAndView("success");
			} else {
				model.addAttribute("msgid", "The Selected Department Has Failed To Delete. !");
				mav = new ModelAndView("success");
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

	// /////////////////// View Ward/////////////////////////////

	// This functions of View Ward has been shifted to LocalGovtBodyController   Start-------ARNAB
	/*
	@RequestMapping(value = "/viewwardforPRI", method = RequestMethod.GET)
	public ModelAndView viewWardForPanchayat(
			HttpSession session,
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			Model model, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		try {
			String localbodyCategory = "P";
			String statecode = (String) session.getAttribute("stateCode");
			if (statecode != null) {

				getLocalGovtSetupList = localGovtSetupService.isStateSetup(
						Integer.parseInt(statecode), 'P');

				if (getLocalGovtSetupList != null
						&& !getLocalGovtSetupList.isEmpty()) {

					model.addAttribute("stateCode", statecode);
					request.setAttribute("localSetUpList",
							getLocalGovtSetupList);

					localGovtBodyForm.setLgd_lbCategory("P");
					request.setAttribute("stateCode",
							Integer.parseInt(statecode));
					List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService
							.getLocalBodyListStateWise('P',
									Integer.parseInt(statecode));
					List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService
							.getPanchayatListbyStateandCategory(
									Integer.parseInt(statecode), 'P');

					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist,
								localGovtBodyForm);
					}
					if (districtPanchayatList.size() > 0) {
						model.addAttribute("districtPanchayatList",
								districtPanchayatList);
					}
					mav = new ModelAndView("view_ward");
					model.addAttribute("localBodyTypelist", localBodyTypelist);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					model.addAttribute("viewPage", "");
					return mav;
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid",
							"Please first Set Up Local Government for this State");
				}

			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
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

	@RequestMapping(value = "/viewwardforTraditional", method = RequestMethod.GET)
	public ModelAndView viewWardForTraditional(
			HttpSession session,
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			String localbodyCategory = "T";
			String statecode = (String) session.getAttribute("stateCode");
			if (statecode != null) {

				getLocalGovtSetupList = localGovtSetupService.isStateSetup(
						Integer.parseInt(statecode), 'T');

				if (getLocalGovtSetupList != null
						&& !getLocalGovtSetupList.isEmpty()) {

					model.addAttribute("stateCode", statecode);
					request.setAttribute("localSetUpList",
							getLocalGovtSetupList);

					localGovtBodyForm.setLgd_lbCategory("T");
					List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService
							.getLocalBodyListStateWise('T',
									Integer.parseInt(statecode));
					List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService
							.getPanchayatListbyStateandCategory(
									Integer.parseInt(statecode), 'T');
					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist,
								localGovtBodyForm);
					}
					if (districtPanchayatList.size() > 0) {
						model.addAttribute("districtPanchayatList",
								districtPanchayatList);
					}
					mav = new ModelAndView("view_ward");
					model.addAttribute("localBodyTypelist", localBodyTypelist);
					mav.addObject("localGovtBodyForm", localGovtBodyForm);
					return mav;
				} else {
					mav = new ModelAndView("success");
					mav.addObject("msgid",
							"Please first Set Up Local Government for this State");
				}
			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
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

	@RequestMapping(value = "/viewwardforUrban", method = RequestMethod.GET)
	public ModelAndView viewWardForUrban(
			HttpSession session,
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			Model model, HttpServletRequest request) {
		ModelAndView mv = null;
		try {
			String statecode = (String) session.getAttribute("stateCode");
			String localbodyCategory = "U";
			localGovtBodyForm.setLgd_lbCategory("U");
			if (statecode != null) {
				getLocalGovtSetupList = localGovtSetupService.isStateSetup(
						Integer.parseInt(statecode), 'U');

				if (getLocalGovtSetupList != null
						&& !getLocalGovtSetupList.isEmpty()) {

					model.addAttribute("stateCode", statecode);
					request.setAttribute("localSetUpList",
							getLocalGovtSetupList);

					List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService
							.getLocalBodyListStateWise('U',
									Integer.parseInt(statecode));
					if (localBodyTypelist.size() > 0) {
						localGovtBodyForm = setLBtype(localBodyTypelist,
								localGovtBodyForm);
					}
					mv = new ModelAndView("view_urbanward");
					model.addAttribute("localBodyTypelist", localBodyTypelist);
					mv.addObject("localGovtBodyForm", localGovtBodyForm);
					return mv;
				} else {
					mv = new ModelAndView("success");
					mv.addObject("msgid",
							"Please first Set Up Local Government for this State");
				}
			} else {
				mv = new ModelAndView("success");
				mv.addObject("msgid",
						"Please first Set Up Local Government for this State");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}*/

	// This functions of View Ward has been shifted to LocalGovtBodyController   End-------ARNAB
	
	public LocalGovtBodyForm setWardtype(
			List<LocalbodyforStateWise> localBodyTypelist,
			LocalGovtBodyForm localGovtBodyForm) {

		try {
			for (int j = 0; j < localBodyTypelist.size(); j++) {
				String govtBodyType = localBodyTypelist.get(j).getLevel();
				String category = localBodyTypelist.get(j).getCategory();
				if (govtBodyType != null) {
					if (govtBodyType.equalsIgnoreCase("D")
							&& category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null){
				
							localGovtBodyForm
								.setLgd_LBNomenclatureDist(localBodyTypelist
										.get(j).getNomenclatureEnglish());
						}	
					}
					if (govtBodyType.equalsIgnoreCase("I")
							&& category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null){
					
							localGovtBodyForm
								.setLgd_LBNomenclatureInter(localBodyTypelist
										.get(j).getNomenclatureEnglish());
						}	
					}
					if (govtBodyType.equalsIgnoreCase("V")
							&& category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null){
				
							localGovtBodyForm
								.setLgd_LBNomenclatureVillage(localBodyTypelist
										.get(j).getNomenclatureEnglish());
						}	
					}
					if (govtBodyType.equalsIgnoreCase("U")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null){
							
						localGovtBodyForm
								.setLgd_LBNomenclatureUrban(localBodyTypelist
										.get(j).getNomenclatureEnglish());
						}
					}
				}

			}
			return localGovtBodyForm;
		} catch (Exception e) {

			log.debug("Exception" + e);
			return null;
		}
	}

	//This function has been shifted to LocalGovtBodyContoller -----Start------Arnab
/*	@RequestMapping(value = "/viewWardPRIAndTRI", method = RequestMethod.POST)
	public ModelAndView viewWardList(
			HttpSession session,
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			String localbdytype1=localGovtBodyForm.getLgd_LBTypeName().substring(0,1);
			int localbdytype=Integer.parseInt(localbdytype1);
					//Integer.parseInt(request.getParameter("localbdytyp").toString());
			String statecode = (String) session.getAttribute("stateCode");
			int stateCode = Integer.parseInt(statecode);
				
	///////////////code for drop down validation/////////////////////////////////
			
				if (localbdytype==1)
				{
					comboFillingService.getComboValuesforApp('L', "S#1", stateCode, Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()));
					
				
				//		comboFillingService.getComboValuesforApp('W', null,Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()),null);
				}
				
				
				
				if (localbdytype==2)
				{	
					
					comboFillingService.getComboValuesforApp('L',"L",Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtInterCA()),Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList()));
					
					
					
				}
				if (localbdytype==3)
				{
					comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()));
					//comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishListSourceVillg()), Integer.parseInt(localGovtBodyForm.getLgd_LBVillageSourceAtVillageCA()));
					
			}
///////////////end code for drop down validation/////////////////////////////////
			
			String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
			String strArray[] = localBodyTypeCodeDup.split(":");
			String typeCode = strArray[0];
			String type = strArray[1];
			String categoryDropDown = strArray[2];
			
			
	//////////code for government order checking///////////////////////////////////
							Map<String, String> hMap = new HashMap<String, String>();
							// Please first check your operation code then set it in place of 10
							hMap = configGovtOrderService.checkMapAndGovtOrderConfiguration(stateCode, 10, 'V');// 10 is operation code for create new
					//////////code for government order checking///////////////////////////////////
			List<LocalGovtBodyWard> listWardDetails = new ArrayList<LocalGovtBodyWard>();
			List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService
					.getLocalBodyListStateWise(localGovtBodyForm.getLgd_lbCategory().charAt(0),
							Integer.parseInt(statecode));
			List<LocalbodyListbyState> districtPanchayatList = localGovtBodyService
					.getPanchayatListbyStateandCategory(
							Integer.parseInt(statecode), localGovtBodyForm.getLgd_lbCategory().charAt(0));
			if (type.equalsIgnoreCase("D")
					&& localGovtBodyForm.getLocalBodyNameEnglishList() != null) {
				listWardDetails = new ArrayList<LocalGovtBodyWard>();
				listWardDetails = localGovtBodyService
						.getlocalGovtBodyWardList(Integer
								.parseInt(localGovtBodyForm
										.getLocalBodyNameEnglishList()));

			}

			if (type.equalsIgnoreCase("I")
					&& localGovtBodyForm.getLocalBodyNameEnglishListSource() != null) {
				listWardDetails = new ArrayList<LocalGovtBodyWard>();
				listWardDetails = localGovtBodyService
						.getlocalGovtBodyWardList(Integer
								.parseInt(localGovtBodyForm
										.getLocalBodyNameEnglishListSource()));

			}

			if (type.equalsIgnoreCase("V")
					&& localGovtBodyForm
							.getLocalBodyNameEnglishListSourceVillg() != null) {
				listWardDetails = new ArrayList<LocalGovtBodyWard>();
				listWardDetails = localGovtBodyService
						.getlocalGovtBodyWardList(Integer.parseInt(localGovtBodyForm
								.getLocalBodyNameEnglishListSourceVillg()));
			}

			if (categoryDropDown.equalsIgnoreCase("U")
					&& localGovtBodyForm.getLocalBodyNameEnglishList() != null) {
				// ALERT State Code Hard Code for testing
				listWardDetails = new ArrayList<LocalGovtBodyWard>();
				listWardDetails = localGovtBodyService
						.getlocalGovtBodyWardList(Integer
								.parseInt(localGovtBodyForm
										.getLocalBodyNameEnglishList()));
			}
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				localGovtBodyForm.setLocalGovtBodyWardList(listWardDetails);
				model.addAttribute("wardList", listWardDetails);
				model.addAttribute("districtPanchayatList", districtPanchayatList);
				if (listWardDetails.size() > 0) {
					mav = new ModelAndView("view_urbanward");
					model.addAttribute("viewPage", "abc");
					return mav;
				} else {
					mav = new ModelAndView("error");
					String errorMsg = "Ward List is not available";
					mav.addObject("message", errorMsg);
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
		 
	}*/
	
	
/*	@RequestMapping(value = "/invalidateWard")
	public ModelAndView abolishWard(
			HttpSession session,
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			Model model, HttpServletRequest request)
	{

		ModelAndView mav = null;
		int wardCode=localGovtBodyForm.getUrbanwardId();
		Long userid=(Long)(session).getAttribute("userid");
		Integer userId = new Integer(userid.intValue());
		
		try {
			boolean isCommitted = false;
			//isCommitted = localGovtBodyService.invalidateWard(wardCode, userId);
			isCommitted = localGovtBodyService.deleteWard(wardCode, userId);
			if (isCommitted == true) {
				List<LocalGovtBodyWard> listWardDetails = null;
				listWardDetails = new ArrayList<LocalGovtBodyWard>();
				listWardDetails = localGovtBodyService
						.getlocalGovtBodyWardList(1);
				model.addAttribute("wardList", listWardDetails);
				model.addAttribute("viewPage", "abc");
				mav = new ModelAndView("view_ward");
			} else {
				model.addAttribute("msgid",
						"The selected Ward has been deleted. !");
				mav = new ModelAndView("success");
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
	}*/
	
	//This function has been shifted to LocalGovtBodyContoller -----End------Arnab
	// ////////////////////////////////////////Urban Ward
	// List/////////////////////////////////////////////////////
	//Function shifted to LocalGovtController------Start-----Arnab
	/*@RequestMapping(value = "/viewUrbanWard", method = RequestMethod.GET)
	public ModelAndView createWardforUrban(
			HttpSession session,
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			Model model, HttpServletRequest request) {

		ModelAndView mav = null;
		try {
			String statecode = (String) session.getAttribute("stateCode");
			if (statecode != null) {
				List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService
						.getLocalBodyListStateWise('U',
								Integer.parseInt(statecode));

				if (localBodyTypelist.size() > 0) {

					localGovtBodyForm = setLBtype(localBodyTypelist,
							localGovtBodyForm);
				}

				mav = new ModelAndView("view_urbanward");
				model.addAttribute("localBodyTypelist", localBodyTypelist);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
				return mav;
			} else {
				mav = new ModelAndView("configview");
				String message = "Please First Select State";
				mav.addObject("msgid", message);
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
	}*/

	
	/*@RequestMapping(value = "/viewUrbanWard", method = RequestMethod.POST)
	public ModelAndView viewUrbanWard(
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
			BindingResult result, SessionStatus status, Model model,
			HttpServletRequest request,HttpSession session) {

		
		 * String localBodyTypeCodeDup = localGovtBodyForm.getLgd_LBTypeName();
		 * String strArray[] = localBodyTypeCodeDup.split(":"); String typeCode
		 * = strArray[0]; String type = strArray[1];
		 
		ModelAndView mav = null;
		try {
			String localbdytype1=localGovtBodyForm.getLgd_LBTypeName().substring(0,1);
			int localbdytype=Integer.parseInt(localbdytype1);
					//Integer.parseInt(request.getParameter("localbdytyp").toString());
			String statecode = (String) session.getAttribute("stateCode");
			int stateCode = Integer.parseInt(statecode);
				
	///////////////code for drop down validation/////////////////////////////////
			
				if (localbdytype==1)
				{
					comboFillingService.getComboValuesforApp('L', "S#1", stateCode, Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()));
					
				
				//		comboFillingService.getComboValuesforApp('W', null,Integer.parseInt(localGovtBodyForm.getLocalBodyNameEnglishList()),null);
				}
				
				
				
				if (localbdytype==2)
				{	
					
					comboFillingService.getComboValuesforApp('L',"L",Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtInterCA()),Integer.parseInt(localGovtBodyForm.getLgd_LBInterPSourceList()));
					
					
					
				}
				if (localbdytype==3)
				{
					comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()));
					comboFillingService.getComboValuesforApp('L', "L", Integer.parseInt(localGovtBodyForm.getLgd_LBInterListAtVillageCA()), Integer.parseInt(localGovtBodyForm.getLgd_LBVillageSourceAtVillageCA()));
				
			}
///////////////end code for drop down validation/////////////////////////////////
			List<LocalGovtBodyWard> listWardDetails = new ArrayList<LocalGovtBodyWard>();

			if (localGovtBodyForm.getLb_lgdPanchayatName() != null) {
				// ALERT State Code Hard Code for testing
				listWardDetails = new ArrayList<LocalGovtBodyWard>();
				listWardDetails = localGovtBodyService
						.getlocalGovtBodyWardList(Integer
								.parseInt(localGovtBodyForm
										.getLb_lgdPanchayatName()));
			}

			model.addAttribute("wardList", listWardDetails);
			localGovtBodyForm.setLocalGovtBodyWardList(listWardDetails);
			if (listWardDetails.size() > 0) {
				mav = new ModelAndView("view_urbanward");
				model.addAttribute("viewPage", "abc");
			} else {
				mav = new ModelAndView("error");
				String errorMsg = "Ward List is not available";
				model.addAttribute("message", errorMsg);
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
	}*/
	
	//Function shifted to LocalGovtController------End-----Arnab


	
/*	@RequestMapping(value = "/viewWardDetails")
	public ModelAndView viewWardDetail(
			@ModelAttribute("wardViewDetails") LocalGovtBodyForm wardView,
			HttpServletRequest request, Model model)
	{
		ModelAndView mav = null;
		int wardCode=wardView.getUrbanwardId();
		try {
			List<LocalbodyWard> listWardDetails = localGovtBodyService.getWardByWardCode(wardCode);
					 
			mav = new ModelAndView("view_warddetail");
			wardView.setListLBWard(listWardDetails);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}*/
	//Function shifted to LocalGovtController------End-----Arnab

			 
			 
	/*@RequestMapping(value = "/globalviewBlock", method = RequestMethod.POST)
	public ModelAndView getglobalBlockPage(
			@ModelAttribute("blockbean") BlockForm blockbean,
			BindingResult result, Model model, HttpServletRequest request,
			HttpSession httpSession) {
		ModelAndView mav = null;

		try {
			
			
			blockbean.setStateNameEnglish(null);
			mav = new ModelAndView("globalview_cBlock");
			String captchaAnswer = blockbean.getCaptchaAnswer();
			String captchaAnswers = blockbean.getCaptchaAnswers();

			CaptchaValidator captchaValidator = new CaptchaValidator();
			boolean messageFlag = captchaValidator.validateCaptcha(httpSession,
					captchaAnswer);
			boolean messageFlag2 = captchaValidator.validateCaptcha(
					httpSession, captchaAnswers);
			//System.out.println("============messageFlag============:"
			//		+ messageFlag);
			if (messageFlag == false && messageFlag2 == false) {

				if (captchaAnswer != "") {
					mav.addObject("flag1", 1);
					mav.addObject("captchaSuccess1", messageFlag);
				} else {
					mav.addObject("flag2", 2);
					mav.addObject("captchaSuccess2", messageFlag);
				}

				blockbean.setCaptchaAnswer(null);
				blockbean.setCaptchaAnswers(null);
				return mav;
			} else {

				char EntityType = ' ';
				String statecode = null;
				blockValidator.viewValidateBlock(blockbean, result);
				if (result.hasErrors()) {
					result.getAllErrors();
					mav = new ModelAndView("globalview_cBlock");
					return mav;
				} else {

					if (blockbean.getPageType().equals("B")) {
						mav = new ModelAndView("globalview_cBlock");
						EntityType = 'B';
						statecode = (String) httpSession
								.getAttribute("stateCode");
					} else if (blockbean.getPageType().equals("CT")) {
						mav = new ModelAndView("globalview_cBlock");
						EntityType = 'B';
					}

					Integer blockCode = null;

					if (blockbean.getCode().length() > 0
							&& !blockbean.getCode().isEmpty()
							&& blockbean.getCode() != null) {

						blockCode = Integer.parseInt(blockbean.getCode());
					}
					String blockName = blockbean.getBlockNameEnglish();

					String districtCode = blockbean.getDistrictNameEnglish(); // districtCode

					List<StateWiseEntityDetails> stateWiseEntityDetails = null;

					httpSession.setAttribute("limit", blockbean.getLimit());
					blockbean.setOffset(1);
					httpSession.setAttribute("offset", blockbean.getOffset());
					httpSession.setAttribute("blockCode", blockCode);
					httpSession.setAttribute("blockName", blockName);
					httpSession.setAttribute("districtCode", districtCode);
					
					if (!districtCode.equals("")) {
						//System.out.println("Value of dist"
						//		+ request.getParameter("ddSourceDistrict"));

						//System.out.println("districtCode :: " + districtCode);
						stateWiseEntityDetails = blockService
								.getParentWiseList('d',
										Integer.parseInt(districtCode), null,
										null);
						httpSession.setAttribute("counter",
								stateWiseEntityDetails.size());
						stateWiseEntityDetails = blockService
								.getParentWiseList(
										'd',
										Integer.parseInt(districtCode),
										httpSession.getAttribute("limit") == null ? null
												: Integer.parseInt(httpSession
														.getAttribute("limit")
														.toString()), 0);

					} else {
						stateWiseEntityDetails = blockService
								.getStateWiseBlockList(
										statecode == null ? null : Integer
												.parseInt(httpSession
														.getAttribute(
																"stateCode")
														.toString()),
										EntityType, blockCode, blockName, null,
										null);

						httpSession.setAttribute("counter",
								stateWiseEntityDetails.size());

						stateWiseEntityDetails = blockService
								.getStateWiseBlockList(
										statecode == null ? null : Integer
												.parseInt(httpSession
														.getAttribute(
																"stateCode")
														.toString()),
										EntityType,
										httpSession.getAttribute("blockCode") == null ? null
												: Integer.parseInt(httpSession
														.getAttribute(
																"blockCode")
														.toString()),
										httpSession.getAttribute("blockName") == null ? null
												: httpSession.getAttribute(
														"blockName").toString(),
										httpSession.getAttribute("limit") == null ? null
												: Integer.parseInt(httpSession
														.getAttribute("limit")
														.toString()), 0);
					}

					
					if(districtCode!=null && districtCode!="")
					{
						List<LandRegionDetail> landregionDetail=null;
						landregionDetail=new ArrayList<LandRegionDetail>();
						landregionDetail=downloadDirectoryService.landRegionDetail('D', Integer.parseInt(districtCode));
						if(landregionDetail.size()>0){
							model.addAttribute("message","Bloks of "+landregionDetail.get(0).getDistrictNameEnglish()+" District("+landregionDetail.get(0).getStateNameEnglish()+")");
						}	
					}
					model.addAttribute("SEARCH_BLOCK_RESULTS_KEY",
							stateWiseEntityDetails);
					blockbean.setStateWiseEntityDetails(stateWiseEntityDetails);
					model.addAttribute("viewPage", "abc");
					model.addAttribute("offsets", Integer.parseInt(httpSession
							.getAttribute("offset").toString()) - 1);
					model.addAttribute("limits", Integer.parseInt(httpSession
							.getAttribute("limit").toString()));
					model.addAttribute(
							"blockCount",
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
					blockbean.setPageRows(Integer.parseInt(httpSession
							.getAttribute("limit").toString()));
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			blockbean.setCaptchaAnswer(null);
			blockbean.setCaptchaAnswers(null);
			return mav;
		}
		blockbean.setCaptchaAnswer(null);
		blockbean.setCaptchaAnswers(null);
		return mav;
	}*/

	
	@RequestMapping(value = "/viewglobalBlockDetail")
	public ModelAndView viewBlockDetailS(
			@ModelAttribute("blockbean") BlockForm blockbean,BindingResult result,
			HttpServletRequest request, Model model,@RequestParam("globalblockId") Integer blockCode)
	{

		
		ModelAndView mav = null;
	/*	Integer blockCode = null;
		blockCode= Integer.parseInt(request.getParameter("blockCode"));*/
		
		try {
			List<BlockDataForm> listBlockDetails = blockService
					.getBlockDetailsModify(blockCode);
			mav = new ModelAndView("view_Gblockdetail");
			blockbean.setListBlockDetails(listBlockDetails);
			if (listBlockDetails.size() > 0) {
				// Add Govt order details and the view map option
				for (int i = 0; i < listBlockDetails.size(); i++) {
					if (listBlockDetails.get(i).getOrderCodecr() != null) {
						int orderValue = listBlockDetails.get(i)
								.getOrderCodecr();
						if (orderValue > 0) {
							
							
							List<MapAttachment> mapAttachmentList=new ArrayList<MapAttachment>();
							mapAttachmentList=govtOrderService.getMapAttachmentListbyEntity(blockCode,'B');
							
							if(mapAttachmentList.size()>0)
							{
							
								model.addAttribute("mapAttachmentList",
										mapAttachmentList);
							} else {
								mav = new ModelAndView(
										"view_Gblockdetail");
								result.rejectValue("noMapRecord",
										"Error.NOMAPRECORD");
								blockbean
										.setListBlockDetails(listBlockDetails);
							}
							
							List<Attachment> attachmentList = villageService
									.getAttachmentsbyOrderCode(orderValue);
							if (!attachmentList.isEmpty()) {
								mav = new ModelAndView(
										"view_Gblockdetail");
								model.addAttribute("attachmentList",
										attachmentList);
								blockbean
										.setListBlockDetails(listBlockDetails);
								return mav;
							} else {
								mav = new ModelAndView(
										"view_Gblockdetail");
								result.rejectValue("noAttachmentRecord",
										"Error.NOATTACHMENTDETAIL");
								blockbean
										.setListBlockDetails(listBlockDetails);
								return mav;
							}
							
						} else {
							mav = new ModelAndView("view_Gblockdetail");
							result.rejectValue("noOrderRecord",
									"error.NOGOVTORDERDETAILSFOUND");
							blockbean
									.setListBlockDetails(listBlockDetails);
							return mav;
						}
					} else {
						mav = new ModelAndView("view_Gblockdetail");
						result.rejectValue("noOrderRecord",
								"error.NOGOVTORDERDETAILSFOUND");
						List<MapAttachment> mapAttachmentList=new ArrayList<MapAttachment>();
						mapAttachmentList=govtOrderService.getMapAttachmentListbyEntity(blockCode, 'B');
						
						if(mapAttachmentList.size()>0)
						{
						
							model.addAttribute("mapAttachmentList",
									mapAttachmentList);
						} else {
							mav = new ModelAndView("view_Gblockdetail");
							result.rejectValue("noMapRecord",
									"Error.NOMAPRECORD");
							blockbean
									.setListBlockDetails(listBlockDetails);
							return mav;
						}
						blockbean.setListBlockDetails(listBlockDetails);
						return mav;
					}
				}
			} else {
				mav = new ModelAndView("view_Gblockdetail");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
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
	
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public ModelAndView closeButton(
			@ModelAttribute("villagebean") VillageDataForm villagebean,
			BindingResult result, HttpServletRequest request, Model model,
			HttpSession httpSession)
	{
		ModelAndView mav = null;
		

		return mav;
	}
	
	/* public static String getApplicationBundle(String key, Object... params) {
         String message = null;
         String sessionCrossApp	=	null;
         HttpServletRequest request =	((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
         String realPath  =	request.getContextPath();
         String strPath	=	realPath	+	"/WEB-INF/messages";
         System.out.println("The real path would be as printed::::"+realPath);
         InputStream inputStream = ViewController.class.getResourceAsStream("/Messages.properties");
         Properties properties = new Properties();
         try {
         properties.load(inputStream);
         }catch(Exception e)
         {
        	 log.debug("Exception" + e);
         }
         message = properties.getProperty("Error.GOVTORDERNOTUPLOADED");
 		
 		//String hasLussi=request.getParameter(sessionCrossApp);
         return message;
   }*/
	
	@RequestMapping(value = "/viewglobalBlockPagination", method = RequestMethod.POST)
	public ModelAndView getglobalBlockPagination(
			@ModelAttribute("blockbean") BlockForm blockbean, Model model,
			HttpServletRequest request, HttpSession httpSession) {

		ModelAndView mav = null;
		try {
			char EntityType = ' ';
				if (blockbean.getPageType().equals("B")) {
				mav = new ModelAndView("globalview_cBlock");
				EntityType = 'B';
			
			} else if (blockbean.getPageType().equals("CT")) {
				mav = new ModelAndView("globalview_cBlock");
				EntityType = 'B';
			}
			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer
					.parseInt(httpSession.getAttribute("counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + blockbean.getDirection() > 0) {
				blockbean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ blockbean.getDirection());
				httpSession.setAttribute("offset", blockbean.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset")
					.toString()) == (Integer.parseInt(httpSession.getAttribute(
					"counter").toString())
					/ Integer.parseInt(httpSession.getAttribute("limit")
							.toString()) + 1)
					&& blockbean.getDirection() == -1
					&& Integer.parseInt(httpSession.getAttribute("offset")
							.toString()) + blockbean.getDirection() > 0) {
				blockbean.setOffset(Integer.parseInt(httpSession
						.getAttribute("offset").toString())
						+ blockbean.getDirection());
				httpSession.setAttribute("offset", blockbean.getOffset());
			}

			List<StateWiseEntityDetails> stateWiseEntityDetails = null;

			if (httpSession.getAttribute("districtCode") != null
					&& !httpSession.getAttribute("districtCode").equals("")) {

				//System.out.println("districtCode :: "
				//		+ httpSession.getAttribute("districtCode"));
				stateWiseEntityDetails = blockService.getParentWiseList(
						'd',
						Integer.parseInt(httpSession.getAttribute(
								"districtCode").toString()), null, null);
				httpSession.setAttribute("counter",
						stateWiseEntityDetails.size());

				stateWiseEntityDetails = blockService.getParentWiseList(
						'd',
						Integer.parseInt(httpSession.getAttribute(
								"districtCode").toString()),
						httpSession.getAttribute("limit") == null ? null
								: Integer.parseInt(httpSession.getAttribute(
										"limit").toString()),
						Integer.parseInt(httpSession.getAttribute("limit")
								.toString())
								* (Integer.parseInt(httpSession.getAttribute(
										"offset").toString()) - 1));

			} else {
				stateWiseEntityDetails = blockService.getStateWiseBlockList(
								httpSession.getAttribute("stateCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("stateCode")
												.toString()),
								EntityType,
								httpSession.getAttribute("blockCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("blockCode")
												.toString()),
								httpSession.getAttribute("blockName") == null ? null
										: httpSession.getAttribute(
												"blockName").toString(),
								null, null);
				httpSession.setAttribute("counter",
						stateWiseEntityDetails.size());

				stateWiseEntityDetails = blockService.getStateWiseBlockList(
								httpSession.getAttribute("stateCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("stateCode")
												.toString()),
								EntityType,
								httpSession.getAttribute("blockCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("blockCode")
												.toString()),
								httpSession.getAttribute("blockName") == null ? null
										: httpSession.getAttribute(
												"blockName").toString(),
								httpSession.getAttribute("limit") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("limit")
												.toString()),
								Integer.parseInt(httpSession.getAttribute(
										"limit").toString())
										* (Integer.parseInt(httpSession
												.getAttribute("offset")
												.toString()) - 1));
			}
			/*added by pooja on 03-07-2015 for display heading message in pagination*/
			String districtCode = (String)httpSession.getAttribute("districtCode");
			if(districtCode!=null && districtCode!="")
			{
				List<LandRegionDetail> landregionDetail=null;
				landregionDetail=new ArrayList<LandRegionDetail>();
				landregionDetail=reportService.landRegionDetail('D', Integer.parseInt(districtCode));
				if(landregionDetail.size()>0){
					model.addAttribute("message","Bloks of "+landregionDetail.get(0).getDistrictNameEnglish()+" District("+landregionDetail.get(0).getStateNameEnglish()+")");
				}	
			}
			model.addAttribute("SEARCH_BLOCK_RESULTS_KEY",
					stateWiseEntityDetails);
			blockbean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
			model.addAttribute("offsets", Integer.parseInt(httpSession
					.getAttribute("offset").toString()) - 1);
			model.addAttribute("limits", Integer.parseInt(httpSession
					.getAttribute("limit").toString()));
			model.addAttribute(
					"blockCount",
					"Page "
							+ Integer.parseInt(httpSession.getAttribute(
									"offset").toString())
							+ " of "
							+ (Integer.parseInt(httpSession.getAttribute(
									"counter").toString())
									/ Integer.parseInt(httpSession
											.getAttribute("limit").toString()) + 1));
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
	/* download uploaded file */

	@RequestMapping(value = "/reportFileDownloads", method = RequestMethod.POST)
	public ModelAndView reportFileDownloads(
			@ModelAttribute("villageView") VillageForm villageView,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("globalview_villagedetail");
		//System.out.println("m in download");
		AttachmentMaster attachmentList = govtOrderService
				.getUploadFileConfigurationDetails(1);
		String message;
		try {
			getFileDownload(attachmentList, request, response);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		return null;
	}

	public String getFileDownload(AttachmentMaster attachmentMasterDetail,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String message = "SUCCESS";
		// Getting Master Table Data.

		String fileUploadLocation = attachmentMasterDetail.getFileLocation();
		fileUploadLocation = fileUploadLocation.replace("\\\\", "/");
		String filename = fileUploadLocation.replace("\\", "/") + "/"
				+ request.getParameter("fileNameToDownload");
		File file = null;
		FileInputStream fileIn = null;
		ServletOutputStream out = null;
		try {
			file = new File(filename);

			if (file.exists()) {
				fileIn = new FileInputStream(file);

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + file.getName());
				out = response.getOutputStream();

				byte[] outputByte = new byte[4096];
				// copy binary contect to output stream
				while (fileIn.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
			} else {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + file.getName());
				message = "ERROR : File has been Deleted";
				byte[] outputByte = message.getBytes();
				out = response.getOutputStream();
				out.write(outputByte, 0, message.length());
				
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			//System.out.println(e);
		} finally {
			if (fileIn != null){
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.debug("Exception" + e);
				}
			}	
			if (out != null) {
				out.flush();
				out.close();
			}

		}
		return message;
	}

	/* End of download uploaded file */

	/* download Map uploaded file */

	@RequestMapping(value = "mapFileDownloads", method = RequestMethod.POST)
	public String mapFileDownloads(
			@ModelAttribute("villageView") VillageForm villageView,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		//System.out.println("m in download");
		AttachmentMaster attachmentList = govtOrderService
				.getUploadFileConfigurationDetails(2);
		// MapAttachment mapAttach =
		// mapAttachmentService.getUploadFileConfigurationDetails();
		String message;
		try {
			String saveMessage = getFileDownload(attachmentList, request,
					response);
			model.addAttribute("saveMsg", saveMessage);

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			return redirectPath;
		}

		return null;
	}
	
	@RequestMapping(value = "/globalviewGovtOrderDetail")
	public String globalviewGovtOrderDetail(
			@ModelAttribute("villagebean") VillageDataForm villagebean,
			BindingResult result, HttpServletRequest request, Model model,HttpSession session) {
		ModelAndView mav = null;
		try
		{
			mav = new ModelAndView("globalview_cvillage");
			int villCode	=	Integer.parseInt(request.getParameter("villageCode"));
			List<VillageDataForm> listVillageDetails = villageService.getVillageDetailsModify(villCode);
			if (listVillageDetails.size() > 0)
			{
				// Add Govt order details and the view map option
				for (int i = 0; i < listVillageDetails.size(); i++) 
				{
					if (listVillageDetails.get(i).getOrderCodecr() != null)
					{
						int orderValue = listVillageDetails.get(i).getOrderCodecr();
						if (orderValue > 0) 
						{
								/*
								 * Retrieving the Govt order details attachment from
								 * the database
								 */
								List<Attachment> attachmentList = villageService
										.getAttachmentsbyOrderCode(orderValue);
									if (!attachmentList.isEmpty()) 
									{
										String fileName	=	"";
										try
										{
											/*List<Attachment> listOFMetaData = (List<Attachment>) request.getAttribute("attachmentList");
											Iterator<Attachment> it = listOFMetaData.iterator();*/
											Iterator<Attachment> it = attachmentList.iterator();
											if (it.hasNext()) 
											{
													Attachment abc = (Attachment) it.next();
													fileName	=	abc.getFileTimestamp();
											}
										} 
										catch (Exception e) 
										{
											log.debug("Exception" + e);
										}
										model.addAttribute("fileName",fileName);
										session.setAttribute("file", fileName);
										model.addAttribute("display",true);
										//System.out.println("successfull"+villCode);
										return "redirect:reportAttachDownloads.do";
									}
									else 
									{
											mav = new ModelAndView("globalview_cvillage");
											result.rejectValue("noAttachmentRecord","Error.NOATTACHMENTDETAIL");
											return "redirect:reportAttachDownloads.do";
									}
						}
						else 
						{
								mav = new ModelAndView("globalview_cvillage");
								result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
								return "redirect:reportAttachDownloads.do";
						}
					}
					else 
					{
						mav = new ModelAndView("globalview_cvillage");
						result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
					}
				}
			}
			else
			{
				mav = new ModelAndView("globalview_cvillage");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
				return "redirect:reportAttachDownloads.do";
			}
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		return "redirect:reportAttachDownloads.do";
	}
	/* download uploaded file */

	@RequestMapping(value = "/reportAttachDownloads", method = RequestMethod.GET)
	public String reportAttachDownloads(
			@ModelAttribute("villagebean") VillageDataForm villagebean,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("globalview_cvillage");
		//System.out.println("m in download");
		AttachmentMaster attachmentList = govtOrderService
				.getUploadFileConfigurationDetails(1);
		String message="";
		try {
			message	=	getAttachDownload(attachmentList, result,model,request, response, session);
			if(message.equals("1") )
			{
				message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
				//model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY", session.getAttribute("SEARCH_VILLAGE_RESULTS_KEY"));
				model.addAttribute("saveMsg",message);
				//model.addAttribute("display",true);
				return "redirect:globalviewVillageGet.do";
			}
			
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		
		return null;
	}

	public String getAttachDownload(AttachmentMaster attachmentMasterDetail,BindingResult result,Model model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException {
		String message = "SUCCESS";
		// Getting Master Table Data.

		String fileUploadLocation = attachmentMasterDetail.getFileLocation();
		String fileNameToDownload = (String)session.getAttribute("file");
		fileUploadLocation = fileUploadLocation.replace("\\\\", "/");
		String filename = fileUploadLocation.replace("\\", "/") + "/"
				+ fileNameToDownload;
		ModelAndView mav = new ModelAndView("globalview_cvillage");
		File file = null;
		FileInputStream fileIn = null;
		ServletOutputStream out = null;
		try {
			file = new File(filename);

			if (file.exists()) {
				fileIn = new FileInputStream(file);

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + file.getName());
				out = response.getOutputStream();

				byte[] outputByte = new byte[4096];
				// copy binary contect to output stream
				while (fileIn.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
			} else {
				//System.out.println("########## File Not Found #####################"+filename);
				message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
  				//model.addAttribute("saveMsg",message);
				message="1";
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			//System.out.println(e);
		} finally {
			if (fileIn != null){
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.debug("Exception" + e);
				}
			}	
			if (out != null) {
				out.flush();
				out.close();
			}

		}
		return message;
	}

	/* End of download uploaded file */
	
	/*Redirect to the same page when govt. order not uploaded alert received*/
	@RequestMapping(value = "/globalviewVillageGet", method = RequestMethod.GET)
	public ModelAndView redirectglobalVillagePage(
			@ModelAttribute("villagebean") VillageDataForm villagebean,
			BindingResult result, HttpServletRequest request, Model model,
			HttpSession httpSession) {
		ModelAndView mav = null;
		String getCategoryType = "";
		int localBodyTypeCode = 0;
		int stateCode = 0;
		int localBodyCode = 0;
		String message	=	"";
		villagebean =  (VillageDataForm) httpSession.getAttribute("redirectVillageBean");
		try {
			message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e1);
		}
		 model.addAttribute("saveMsg",message);
		 model.addAttribute("display",true);
		try {
			mav = new ModelAndView("globalview_cvillage");
		      	{			
	      		char EntityType = ' ';
		      	String statecode = null;

			villageValidator.viewValidateVillage(villagebean, result);
			if (result.hasErrors()) {
				result.getAllErrors();
				mav = new ModelAndView("globalview_village");
				villagebean.setCaptchaAnswer(null);
				villagebean.setCaptchaAnswers(null);
				return mav;
			} else {
				if (villagebean.getPageType().equals("V")) {
					mav = new ModelAndView("globalview_village");
					EntityType = 'V';
					statecode = (String) httpSession.getAttribute("stateCode");
				} else if (villagebean.getPageType().equals("CV")) {
					mav = new ModelAndView("globalview_cvillage");
					EntityType = 'V';
				}

				Integer villageCode = null;

				if (villagebean.getCode().length() > 0
						&& !villagebean.getCode().isEmpty()
						&& villagebean.getCode() != null) {

					villageCode = Integer.parseInt(villagebean.getCode());
				}
				String villageName = villagebean.getVillageNameEnglish();
				String subDistrictCode = villagebean
						.getSubdistrictNameEnglish();
				//System.out.println("subDistrictCode : " + subDistrictCode);
				List<StateWiseEntityDetails> stateWiseEntityDetails = null;
				httpSession.setAttribute("limit", villagebean.getLimit());
				villagebean.setOffset(1);
				httpSession.setAttribute("offset", villagebean.getOffset());
				httpSession.setAttribute("villageCode", villageCode);
				httpSession.setAttribute("villageName", villageName);
				httpSession.setAttribute("subDistrictCode", subDistrictCode);

				if (subDistrictCode != null && !subDistrictCode.equals("")) {

					//System.out.println("districtCode :: " + subDistrictCode);
					stateWiseEntityDetails = villageService.getParentWiseList(
							'T', Integer.parseInt(subDistrictCode), null, null);
					httpSession.setAttribute("counter",
							stateWiseEntityDetails.size());

					stateWiseEntityDetails = villageService.getParentWiseList(
							'T',
							Integer.parseInt(subDistrictCode),
							httpSession.getAttribute("limit") == null ? null
									: Integer.parseInt(httpSession
											.getAttribute("limit").toString()),
							0);

				} else {
					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									statecode == null ? null : Integer
											.parseInt(httpSession.getAttribute(
													"stateCode").toString()),
									EntityType, villageCode, villageName, null,
									null);
					httpSession.setAttribute("counter",
							stateWiseEntityDetails.size());

					stateWiseEntityDetails = villageService
							.getStateWiseVillageList(
									statecode == null ? null : Integer
											.parseInt(httpSession.getAttribute(
													"stateCode").toString()),
									EntityType,
									httpSession.getAttribute("villageCode") == null ? null
											: Integer
													.parseInt(httpSession
															.getAttribute(
																	"villageCode")
															.toString()),
									httpSession.getAttribute("villageName") == null ? null
											: httpSession.getAttribute(
													"villageName").toString(),
									httpSession.getAttribute("limit") == null ? null
											: Integer.parseInt(httpSession
													.getAttribute("limit")
													.toString()), 0);
				}
				httpSession.setAttribute("SEARCH_VILLAGE_RESULTS_KEY", stateWiseEntityDetails);
				model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY",
						stateWiseEntityDetails);
				villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
				model.addAttribute("viewPage", "abc");
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
												.getAttribute("limit")
												.toString()) + 1));
				villagebean.setPageRows(Integer.parseInt(httpSession
						.getAttribute("limit").toString()));
				model.addAttribute("display",true);
			}
		}} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			villagebean.setCaptchaAnswer(null);
			villagebean.setCaptchaAnswers(null);
			return mav;
		}
		villagebean.setCaptchaAnswer(null);
		villagebean.setCaptchaAnswers(null);
		return mav;
	}
	
	/*code for the govt. order upload for sub-district*/
	@RequestMapping(value = "/globalviewGovtOrderDetailForSD")
	public String globalviewGovtOrderDetailForSD(
			@ModelAttribute("subdistrictbean") SubDistrictForm subdistrictbean,
			BindingResult result, HttpServletRequest request, Model model,HttpSession session) {
		ModelAndView mav = null;
		try
		{
			mav = new ModelAndView("globalview_csubdistrict");
			//System.out.println("Value of hidden field is::::::"+request.getParameter("sdCode1"));
			int subDistrictCode	=	Integer.parseInt(request.getParameter("sdCode1"));
			List<SubdistrictDataForm> listSubDistrictDetails = subDistrictService.getSubdistrictDetailsModify(subDistrictCode);
			if (listSubDistrictDetails.size() > 0)
			{
				// Add Govt order details and the view map option
				for (int i = 0; i < listSubDistrictDetails.size(); i++) 
				{
					if (listSubDistrictDetails.get(i).getOrderCodecr() != null)
					{
						int orderValue = listSubDistrictDetails.get(i).getOrderCodecr();
						if (orderValue > 0) 
						{
								/*
								 * Retrieving the Govt order details attachment from
								 * the database
								 */
								List<Attachment> attachmentList = subDistrictService
										.getAttachmentsbyOrderCode(orderValue);
									if (!attachmentList.isEmpty()) 
									{
										String fileName	=	"";
										try
										{
											/*List<Attachment> listOFMetaData = (List<Attachment>) request.getAttribute("attachmentList");
											Iterator<Attachment> it = listOFMetaData.iterator();*/
											Iterator<Attachment> it = attachmentList.iterator();
											if (it.hasNext()) 
											{
													Attachment abc = (Attachment) it.next();
													fileName	=	abc.getFileTimestamp();
											}
										} 
										catch (Exception e) 
										{
											log.debug("Exception" + e);
										}
										model.addAttribute("fileName",fileName);
										session.setAttribute("file", fileName);
										model.addAttribute("display",true);
										//System.out.println("successfull"+subDistrictCode);
										return "redirect:reportAttachDownloadsForSD.do";
									}
									else 
									{
											mav = new ModelAndView("globalview_csubdistrict");
											result.rejectValue("noAttachmentRecord","Error.NOATTACHMENTDETAIL");
											return "redirect:reportAttachDownloadsForSD.do";
									}
						}
						else 
						{
								mav = new ModelAndView("globalview_csubdistrict");
								result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
								return "redirect:reportAttachDownloadsForSD.do";
						}
					}
					else 
					{
						mav = new ModelAndView("globalview_csubdistrict");
						result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
					}
				}
			}
			else
			{
				mav = new ModelAndView("globalview_csubdistrict");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
				return "redirect:reportAttachDownloadsForSD.do";
			}
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		return "redirect:reportAttachDownloadsForSD.do";
	}
	/*end of the govt order upload for subdistrict*/
	
	/* download uploaded file */

	@RequestMapping(value = "/reportAttachDownloadsForSD", method = RequestMethod.GET)
	public String reportAttachDownloadsForSD(
			@ModelAttribute("subdistrictbean") SubDistrictForm subdistrictbean,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("globalview_csubdistrict");
		//System.out.println("m in download");
		AttachmentMaster attachmentList = govtOrderService
				.getUploadFileConfigurationDetails(1);
		String message="";
		try {
			message	=	getAttachDownloadSD(attachmentList, result,model,request, response, session);
			if(message.equals("1") )
			{
				message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
				model.addAttribute("saveMsg",message);
				model.addAttribute("display",true);
				return "redirect:globalviewsubDistrictGet.do";
			}
			
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		model.addAttribute("display",true);
		return null;
	}

	public String getAttachDownloadSD(AttachmentMaster attachmentMasterDetail,BindingResult result,Model model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException {
		String message = "SUCCESS";
		// Getting Master Table Data.

		String fileUploadLocation = attachmentMasterDetail.getFileLocation();
		String fileNameToDownload = (String)session.getAttribute("file");
		fileUploadLocation = fileUploadLocation.replace("\\\\", "/");
		String filename = fileUploadLocation.replace("\\", "/") + "/"
				+ fileNameToDownload;
		ModelAndView mav = new ModelAndView("globalview_csubdistrict");
		File file = null;
		FileInputStream fileIn = null;
		ServletOutputStream out = null;
		try {
			file = new File(filename);

			if (file.exists()) {
				fileIn = new FileInputStream(file);

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + file.getName());
				out = response.getOutputStream();

				byte[] outputByte = new byte[4096];
				// copy binary contect to output stream
				while (fileIn.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
			} else {
				message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
				message="1";
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			//System.out.println(e);
		} finally {
			if (fileIn != null){
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.debug("Exception" + e);
				}
			}	
			if (out != null) {
				out.flush();
				out.close();
			}

		}
		return message;
	}

	/* End of download uploaded file */
	
	/*Redirect to the same page when govt. order not uploaded alert received*/
	
	@RequestMapping(value = "/globalviewsubDistrictGet", method = RequestMethod.GET)
	public ModelAndView redirectglobalSubDistrictPage(
			@ModelAttribute("subdistrictbean") SubDistrictForm subdistrictbean,
			BindingResult result, HttpServletRequest request, Model model,
			HttpSession httpSession) {
		
	ModelAndView mav = null;
		
	try {
		mav = new ModelAndView("globalview_csubdistrict");
		subdistrictbean =  (SubDistrictForm) httpSession.getAttribute("redirectSubDistrictBean");
		String message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
		model.addAttribute("saveMsg",message);
	    model.addAttribute("display",true);
		char EntityType = ' ';
		String statecode = null;

		subDistrictValidator.viewValidateSubDistrict(subdistrictbean,
				result);
		if (result.hasErrors()) {
			result.getAllErrors();
							mav = new ModelAndView("globalview_subdistrict");
			return mav;
		} else {

			if (subdistrictbean.getPageType().equals("T")) {
				mav = new ModelAndView("globalview_subdistrict");
				EntityType = 'T';
				statecode = (String) httpSession.getAttribute("stateCode");
			} else if (subdistrictbean.getPageType().equals("CT")) {
				mav = new ModelAndView("globalview_csubdistrict");
				EntityType = 'T';
			}

			Integer subDistricteCode = null;

			if (subdistrictbean.getCode().length() > 0
					&& !subdistrictbean.getCode().isEmpty()
					&& subdistrictbean.getCode() != null) {

				subDistricteCode = Integer.parseInt(subdistrictbean
						.getCode());
			}
			String subDistrictName = subdistrictbean
					.getSubdistrictNameEnglish();

			String districtCode = subdistrictbean.getDistrictNameEnglish(); // districtCode
			List<StateWiseEntityDetails> stateWiseEntityDetails = null;

			httpSession.setAttribute("limit", subdistrictbean.getLimit());
			subdistrictbean.setOffset(1);
			httpSession.setAttribute("offset", subdistrictbean.getOffset());
			httpSession.setAttribute("villageCode", subDistricteCode);
			httpSession.setAttribute("villageName", subDistrictName);
			httpSession.setAttribute("districtCode", districtCode);

			if (!districtCode.equals("")) {

				//System.out.println("districtCode :: " + districtCode);
				stateWiseEntityDetails = villageService.getParentWiseList(
						'D', Integer.parseInt(districtCode), null, null);
				httpSession.setAttribute("counter",
						stateWiseEntityDetails.size());

				stateWiseEntityDetails = villageService.getParentWiseList(
						'D',
						Integer.parseInt(districtCode),
						httpSession.getAttribute("limit") == null ? null
								: Integer.parseInt(httpSession
										.getAttribute("limit").toString()),
						0);

			} else {
				stateWiseEntityDetails = villageService
						.getStateWiseVillageList(
								statecode == null ? null : Integer
										.parseInt(httpSession.getAttribute(
												"stateCode").toString()),
								EntityType, subDistricteCode,
								subDistrictName, null, null);
				httpSession.setAttribute("counter",
						stateWiseEntityDetails.size());

				stateWiseEntityDetails = villageService
						.getStateWiseVillageList(
								statecode == null ? null : Integer
										.parseInt(httpSession.getAttribute(
												"stateCode").toString()),
								EntityType,
								httpSession.getAttribute("villageCode") == null ? null
										: Integer
												.parseInt(httpSession
														.getAttribute(
																"villageCode")
														.toString()),
								httpSession.getAttribute("villageName") == null ? null
										: httpSession.getAttribute(
												"villageName").toString(),
								httpSession.getAttribute("limit") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("limit")
												.toString()), 0);
			}

			model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",
					stateWiseEntityDetails);
			subdistrictbean
					.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("viewPage", "abc");
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
											.getAttribute("limit")
											.toString()) + 1));
			subdistrictbean.setPageRows(Integer.parseInt(httpSession
					.getAttribute("limit").toString()));
		}
	 }catch (Exception e) {
		IExceptionHandler expHandler = ExceptionHandlerFactory
				.getInstance().create();
		String redirectPath = expHandler.handleSaveRedirectException(
				request, "", "label.lgd", 1, e);
		mav = new ModelAndView(redirectPath);
		subdistrictbean.setCaptchaAnswer(null);
  		subdistrictbean.setCaptchaAnswers(null);
		return mav;
	}
	subdistrictbean.setCaptchaAnswer(null);
		subdistrictbean.setCaptchaAnswers(null);
	return mav;
}
	/*code for the govt. order upload for District*/
	
	@RequestMapping(value = "/globalviewGovtOrderDetailForDist")
	public String globalviewGovtOrderDetailForDist(
			@ModelAttribute("districtbean") DistrictForm districtbean,
			BindingResult result, HttpServletRequest request, Model model,HttpSession session) {
		ModelAndView mav = null;
		try
		{
			mav = new ModelAndView("globalview_cdistrict");
			//System.out.println("Value of hidden field is::::::"+request.getParameter("distCode1"));
			int districtCode	=	Integer.parseInt(request.getParameter("distCode1"));
			List<DistrictDataForm> listDistrictDetails = districtService.getDistrictDetailsModify(districtCode);
			if (listDistrictDetails.size() > 0)
			{
				// Add Govt order details and the view map option
				for (int i = 0; i < listDistrictDetails.size(); i++) 
				{
					if (listDistrictDetails.get(i).getOrderCodecr() != null)
					{
						int orderValue = listDistrictDetails.get(i).getOrderCodecr();
						if (orderValue > 0) 
						{
								/*
								 * Retrieving the Govt order details attachment from
								 * the database
								 */
								List<Attachment> attachmentList = districtService
										.getAttachmentsbyOrderCode(orderValue);
									if (!attachmentList.isEmpty()) 
									{
										String fileName	=	"";
										try
										{
											/*List<Attachment> listOFMetaData = (List<Attachment>) request.getAttribute("attachmentList");
											Iterator<Attachment> it = listOFMetaData.iterator();*/
											Iterator<Attachment> it = attachmentList.iterator();
											if (it.hasNext()) 
											{
													Attachment abc = (Attachment) it.next();
													fileName	=	abc.getFileTimestamp();
											}
										} 
										catch (Exception e) 
										{
											log.debug("Exception" + e);
										}
										model.addAttribute("fileName",fileName);
										session.setAttribute("file", fileName);
										model.addAttribute("display",true);
										//System.out.println("successfull"+districtCode);
										return "redirect:reportAttachDownloadsForDist.do";
									}
									else 
									{
											mav = new ModelAndView("globalview_cdistrict");
											result.rejectValue("noAttachmentRecord","Error.NOATTACHMENTDETAIL");
											return "redirect:reportAttachDownloadsForDist.do";
									}
						}
						else 
						{
								mav = new ModelAndView("globalview_cdistrict");
								result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
								return "redirect:reportAttachDownloadsForDist.do";
						}
					}
					else 
					{
						mav = new ModelAndView("globalview_cdistrict");
						result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
					}
				}
			}
			else
			{
				mav = new ModelAndView("globalview_cdistrict");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
				return "redirect:reportAttachDownloadsForDist.do";
			}
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		return "redirect:reportAttachDownloadsForDist.do";
	}
	/*end of the govt order upload for district*/
	
	/* download uploaded file */

	@RequestMapping(value = "/reportAttachDownloadsForDist", method = RequestMethod.GET)
	public String reportAttachDownloadsForDist(
			@ModelAttribute("districtbean") DistrictForm districtbean,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("globalview_cdistrict");
		//System.out.println("m in download");
		AttachmentMaster attachmentList = govtOrderService
				.getUploadFileConfigurationDetails(1);
		String message="";
		try {
			message	=	getAttachDownloadDist(attachmentList, result,model,request, response, session);
			if(message.equals("1") )
			{
				message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
				model.addAttribute("saveMsg",message);
				model.addAttribute("display",true);
				return "redirect:globalviewDistrictGet.do";
			}
			
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		model.addAttribute("display",true);
		return null;
	}

	public String getAttachDownloadDist(AttachmentMaster attachmentMasterDetail,BindingResult result,Model model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException {
		String message = "SUCCESS";
		// Getting Master Table Data.

		String fileUploadLocation = attachmentMasterDetail.getFileLocation();
		String fileNameToDownload = (String)session.getAttribute("file");
		fileUploadLocation = fileUploadLocation.replace("\\\\", "/");
		String filename = fileUploadLocation.replace("\\", "/") + "/"
				+ fileNameToDownload;
		ModelAndView mav = new ModelAndView("globalview_cdistrict");
		File file = null;
		FileInputStream fileIn = null;
		ServletOutputStream out = null;
		try {
			file = new File(filename);

			if (file.exists()) {
				fileIn = new FileInputStream(file);

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + file.getName());
				out = response.getOutputStream();

				byte[] outputByte = new byte[4096];
				// copy binary contect to output stream
				while (fileIn.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
			} else {
				message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
				message	=	"1";
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			//System.out.println(e);
		} finally {
			if (fileIn != null){
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.debug("Exception" + e);
				}
			}	
			if (out != null) {
				out.flush();
				out.close();
			}

		}
		return message;
	}

	/* End of download uploaded file */
	
	/*Redirect to the same page when govt. order not uploaded alert received*/
	@RequestMapping(value = "/globalviewDistrictGet", method = RequestMethod.GET)
	public ModelAndView redirectglobalDistrictPage(
			@ModelAttribute("districtbean") DistrictForm districtbean,
			BindingResult result, Model model, HttpServletRequest request,
			 SessionStatus status,HttpSession httpSession)

	{
		
		
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("globalview_cdistrict");
			char EntityType = ' ';
			String statecode = null;
			districtbean =  (DistrictForm) httpSession.getAttribute("redirectDistrictBean");
			String message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
			model.addAttribute("saveMsg",message);
		    model.addAttribute("display",true);
			districtValidator.viewValidateDistrict(districtbean, result);
			if (result.hasErrors()) {
				result.getAllErrors();
					mav = new ModelAndView("globalview_district");
				return mav;
			} else {
				if (districtbean.getPageType().equals("D")) {
					mav = new ModelAndView("globalview_district");
					EntityType = 'D';
				} else if (districtbean.getPageType().equals("CD")) {
					mav = new ModelAndView("globalview_cdistrict");
					EntityType = 'D';

					// Set State
					if (districtbean.getCode() != null
							|| districtbean.getDistrictNameEnglish() != null) {
						httpSession.setAttribute("stateCode", null);
					}
					if (httpSession.getAttribute("stateCode") == null
							|| httpSession.getAttribute("stateCode") != districtbean
									.getStateNameEnglish()) {
						httpSession.setAttribute("stateCode", districtbean
								.getStateNameEnglish() == null ? null
								: districtbean.getStateNameEnglish());
					}
				}

				httpSession
						.setAttribute("stateCode",
								httpSession.getAttribute("stateCode")
										.equals("") == true ? null
										: httpSession.getAttribute("stateCode")
												.toString());
				statecode = (String) httpSession.getAttribute("stateCode");

				Integer districteCode = null;

				if (districtbean.getCode().length() > 0
						&& !districtbean.getCode().isEmpty()
						&& districtbean.getCode() != null) {

					districteCode = Integer.parseInt(districtbean.getCode());
				}
				String districtName = districtbean.getDistrictNameEnglish();
				List<StateWiseEntityDetails> stateWiseEntityDetails = null;

				httpSession.setAttribute("limit", districtbean.getLimit());
				districtbean.setOffset(1);
				httpSession.setAttribute("offset", districtbean.getOffset());
				httpSession.setAttribute("villageCode", districteCode);
				httpSession.setAttribute("villageName", districtName);

				stateWiseEntityDetails = villageService
						.getStateWiseVillageList(
								statecode == null ? null : Integer
										.parseInt(httpSession.getAttribute(
												"stateCode").toString()),
								EntityType, districteCode, districtName, null,
								null);
				httpSession.setAttribute("counter",
						stateWiseEntityDetails.size());

				stateWiseEntityDetails = villageService
						.getStateWiseVillageList(
								statecode == null ? null : Integer
										.parseInt(httpSession.getAttribute(
												"stateCode").toString()),
								EntityType,
								httpSession.getAttribute("villageCode") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("villageCode")
												.toString()),
								httpSession.getAttribute("villageName") == null ? null
										: httpSession.getAttribute(
												"villageName").toString(),
								httpSession.getAttribute("limit") == null ? null
										: Integer.parseInt(httpSession
												.getAttribute("limit")
												.toString()), 0);
				model.addAttribute("SEARCH_DISTRICT_RESULTS_KEY",
						stateWiseEntityDetails);
				districtbean.setStateWiseEntityDetails(stateWiseEntityDetails);
				model.addAttribute("viewPage", "abc");
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
												.getAttribute("limit")
												.toString()) + 1));
				districtbean.setPageRows(Integer.parseInt(httpSession
						.getAttribute("limit").toString()));
			}
		}catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			districtbean.setCaptchaAnswer(null);
      		districtbean.setCaptchaAnswers(null);
			return mav;
		}
		districtbean.setCaptchaAnswer(null);
  		districtbean.setCaptchaAnswers(null);
		return mav;
	}
	
/*code for the govt. order upload for state*/
	
	@RequestMapping(value = "/globalviewGovtOrderDetailForState")
	public String globalviewGovtOrderDetailForState(
			@ModelAttribute("statebean") StateForm statebean,
			BindingResult result, HttpServletRequest request, Model model,HttpSession session) {
		ModelAndView mav = null;
		try
		{
			mav = new ModelAndView("globalview_cstate");
			//System.out.println("Value of hidden field is::::::"+request.getParameter("stateCode1"));
			int stateCode	=	Integer.parseInt(request.getParameter("stateCode1"));
			List<StateDataForm> listStateDetails = stateService.getStateDetailsModify(stateCode);
			if (listStateDetails.size() > 0)
			{
				// Add Govt order details and the view map option
				for (int i = 0; i < listStateDetails.size(); i++) 
				{
					if (listStateDetails.get(i).getOrderCodecr() != null)
					{
						int orderValue = listStateDetails.get(i).getOrderCodecr();
						if (orderValue > 0) 
						{
								/*
								 * Retrieving the Govt order details attachment from
								 * the database
								 */
								List<Attachment> attachmentList = stateService
										.getAttachmentsbyOrderCode(orderValue);
									if (!attachmentList.isEmpty()) 
									{
										String fileName	=	"";
										try
										{
											/*List<Attachment> listOFMetaData = (List<Attachment>) request.getAttribute("attachmentList");
											Iterator<Attachment> it = listOFMetaData.iterator();*/
											Iterator<Attachment> it = attachmentList.iterator();
											if (it.hasNext()) 
											{
													Attachment abc = (Attachment) it.next();
													fileName	=	abc.getFileTimestamp();
											}
										} 
										catch (Exception e) 
										{
											log.debug("Exception" + e);
										}
										model.addAttribute("fileName",fileName);
										session.setAttribute("file", fileName);
										model.addAttribute("display",true);
										//System.out.println("successfull"+stateCode);
										return "redirect:reportAttachDownloadsForState.do";
									}
									else 
									{
											mav = new ModelAndView("globalview_cstate");
											result.rejectValue("noAttachmentRecord","Error.NOATTACHMENTDETAIL");
											return "redirect:reportAttachDownloadsForState.do";
									}
						}
						else 
						{
								mav = new ModelAndView("globalview_cstate");
								result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
								return "redirect:reportAttachDownloadsForState.do";
						}
					}
					else 
					{
						mav = new ModelAndView("globalview_cstate");
						result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
					}
				}
			}
			else
			{
				mav = new ModelAndView("globalview_cstate");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
				return "redirect:reportAttachDownloadsForState.do";
			}
		}
		catch(Exception e)
		{
			log.debug("Exception" + e);
		}
		return "redirect:reportAttachDownloadsForState.do";
	}
	/*end of the govt order upload for state*/
	
	/* download uploaded file */

	@RequestMapping(value = "/reportAttachDownloadsForState", method = RequestMethod.GET)
	public String reportAttachDownloadsForState(
			@ModelAttribute("statebean") StateForm statebean,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("globalview_cstate");
		//System.out.println("m in download");
		AttachmentMaster attachmentList = govtOrderService
				.getUploadFileConfigurationDetails(1);
		String message="";
		try {
			message	=	getAttachDownloadState(attachmentList, result,model,request, response, session);
			if(message.equals("1") )
			{
				message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
				model.addAttribute("saveMsg",message);
				model.addAttribute("display",true);
				return "redirect:globalviewStateGet.do";
			}
			
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		model.addAttribute("display",true);
		return null;
	}

	public String getAttachDownloadState(AttachmentMaster attachmentMasterDetail,BindingResult result,Model model,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException {
		String message = "SUCCESS";
		// Getting Master Table Data.

		String fileUploadLocation = attachmentMasterDetail.getFileLocation();
		String fileNameToDownload = (String)session.getAttribute("file");
		fileUploadLocation = fileUploadLocation.replace("\\\\", "/");
		String filename = fileUploadLocation.replace("\\", "/") + "/"
				+ fileNameToDownload;
		ModelAndView mav = new ModelAndView("globalview_cstate");
		File file = null;
		FileInputStream fileIn = null;
		ServletOutputStream out = null;
		try {
			file = new File(filename);

			if (file.exists()) {
				fileIn = new FileInputStream(file);

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + file.getName());
				out = response.getOutputStream();

				byte[] outputByte = new byte[4096];
				// copy binary contect to output stream
				while (fileIn.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
			} else {
				message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
				message="1";
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			//System.out.println(e);
		} finally {
			if (fileIn != null){
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.debug("Exception" + e);
				}
			}	
			if (out != null) {
				out.flush();
				out.close();
			}

		}
		return message;
	}
	/*Redirect to the same page when govt. order not uploaded alert received*/
	
	@RequestMapping(value = "/globalviewStateGet", method = RequestMethod.GET)
	public ModelAndView getglobalStatePage(
			@ModelAttribute("stateView") StateDataForm stateView,BindingResult result,SessionStatus status,HttpSession session,
			HttpServletRequest request, Model model) {		
		ModelAndView mav = null;		
		stateView =  (StateDataForm) session.getAttribute("redirectStateBean");
		String message="";
		try {
			message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e1);
		}
		model.addAttribute("saveMsg",message);
	    model.addAttribute("display",true);
		try
		{
			mav = new ModelAndView("globalview_cstate");

			List<State> listStateDetails = new ArrayList<State>();
			listStateDetails = stateService.getStateViewList(stateView);
			model.addAttribute("SEARCH_STATE_RESULTS_KEY", listStateDetails);
			stateView.setListStateDetail(listStateDetails);
			mav.addObject("stateView", stateView);
			model.addAttribute("viewPage", "abc");
			stateView.setCaptchaAnswer(null);
			return mav;
			
			}catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			stateView.setCaptchaAnswer(null);
			return mav;
		}
		
	}
	
	/***
	 * @author Maneesh Kumar
	 * @since 16Jan2014
	 * @param localGovtBodyForm
	 * @param result
	 * @param model
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/globalviewlocalbody", method = RequestMethod.POST)
	public ModelAndView showgloballocalbodyPage(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
												BindingResult result,
												Model model, 
												HttpServletRequest request,
												HttpSession httpSession) {
		ModelAndView mav = null;
		List<LocalbodyforStateWiseFinal> localBodyTypelist = new ArrayList<LocalbodyforStateWiseFinal>();
		try {
			mav = new ModelAndView("globalview_clocalbody");
			model.addAttribute("stateList", stateService.getStateSourceList());
			String message = null;
			String captchaAnswer = null;
			/**validate Capcha Code**/
			boolean messageFlag = false;
			if (!localGovtBodyForm.getLgd_LBTypeName().isEmpty())
				captchaAnswer = localGovtBodyForm.getCaptchaAnswer();
			else
				captchaAnswer = localGovtBodyForm.getCaptchaAnswers();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			if (captchaAnswer != null){
				messageFlag = captchaValidator.validateCaptcha(httpSession,captchaAnswer);
			}	
			if (!messageFlag) {
				if (!localGovtBodyForm.getLgd_LBTypeName().isEmpty()) {
						mav.addObject("flag1", 1);
						mav.addObject("captchaSuccess1", messageFlag);
					} else {
						mav.addObject("flag1", 2);
						mav.addObject("captchaSuccess2", messageFlag);
					}
				localGovtBodyForm.setCaptchaAnswer(null);
				localGovtBodyForm.setCaptchaAnswers(null);
				
				return mav;
			
			}
			
				String temp[] = localGovtBodyForm.getLgd_LBTypeName().toString().trim().split(":");
				Integer stateCodeValue=null;
				Integer parentLblc=null;
				Integer typeCode = null;
				String type="";
					if (!localGovtBodyForm.getLgd_LBTypeName().isEmpty()) {
						stateCode = Integer.parseInt(temp[2]);
						type = temp[5];
						typeCode=localGovtBodyForm.getFlagCode();
						String stateName = stateDao.getStateNameEnglishbyStateCode(stateCode);
						stateCodeValue = Integer.parseInt(localGovtBodyForm.getStateNameEnglish());
						message = type.trim() + " of " + stateName.trim();
						Integer GtaList=null;
						if(localGovtBodyForm.getGtaList()!=null && !localGovtBodyForm.getGtaList().isEmpty()){
							GtaList = Integer.parseInt(localGovtBodyForm.getGtaList());
						}
						
						Integer localBodyCode=null;
						Integer villageLblc=null;
						Integer intermediateLblc= null;
						Integer GtaInterPanch = null;
						String vilLblc = localGovtBodyForm.getLgd_LBInterListAtVillageCA();
						if(localGovtBodyForm.getLgd_LBInterListAtVillageCA()!=null && !("").equals(localGovtBodyForm.getLgd_LBInterListAtVillageCA().trim())){
						villageLblc =Integer.parseInt(vilLblc) ;}
						if(localGovtBodyForm.getLgd_LBDistListAtVillageCA()!=null && !("").equals(localGovtBodyForm.getLgd_LBDistListAtVillageCA())){
							intermediateLblc =Integer.parseInt(localGovtBodyForm.getLgd_LBDistListAtVillageCA()) ;}
						if(villageLblc!=null && villageLblc>0){
							localBodyCode = villageLblc;
						}else if(intermediateLblc!=null && intermediateLblc>0 ){
							localBodyCode = intermediateLblc;
						}else if(localGovtBodyForm.getGtaInterPanch()!=null && !("").equals(localGovtBodyForm.getGtaInterPanch())){
							localBodyCode=Integer.parseInt(localGovtBodyForm.getGtaInterPanch());
						}
						
						if(localBodyCode!=null){
							String localBodyMessage=localGovtBodyService.getLocalBodyTypeMessagebyLocalbodyCode(localBodyCode);
						    parentLblc=localGovtBodyService.getLblc(localBodyCode);
						    
					       if(localBodyMessage!=null)
						   message=type.trim() + " of " + localBodyMessage.trim();
						}
						
						if(GtaList!=null){
							if(localGovtBodyForm.getGtaInterPanch()!=null && ! ("".equalsIgnoreCase(localGovtBodyForm.getGtaInterPanch()))){
								
								String localBodyMessage=localGovtBodyService.getLocalBodyTypeMessagebyLocalbodyCode(localBodyCode);
							    parentLblc=localGovtBodyService.getLblc(Integer.parseInt(localGovtBodyForm.getGtaInterPanch()));	
								
							}
							else{
								String localBodyMessage=localGovtBodyService.getLocalBodyTypeMessagebyLocalbodyCode(localBodyCode);
							    parentLblc=localGovtBodyService.getLblc(Integer.parseInt(localGovtBodyForm.getGtaList()));	
							}
							
						}
					}else{
						message = "Local Body Details";
					}
						
					Integer lbEnteredCode= localGovtBodyForm.getCode().isEmpty()==true?null:Integer.parseInt(localGovtBodyForm.getCode().trim());
					String lbName = localGovtBodyForm.getDistrictNameEnglish()==""?null:localGovtBodyForm.getDistrictNameEnglish();
					if("20".equalsIgnoreCase(localGovtBodyForm.getParentList()) && localGovtBodyForm.getGtaList()!=null){
						if(localGovtBodyForm.getGtaInterPanch()!=null){
							localBodyTypelist = localGovtBodyService.getLocalBodyListStateCategoryWise(Integer.parseInt(temp[0]),stateCodeValue,parentLblc, null,lbName);	
						}else
						localBodyTypelist = localGovtBodyService.getLocalBodyListStateCategoryWise(Integer.parseInt(temp[0]),stateCodeValue,parentLblc, null,lbName);	
						
					}
					else
					localBodyTypelist = localGovtBodyService.getLocalBodyListStateCategoryWise(typeCode,stateCodeValue, parentLblc, lbEnteredCode,lbName);
					model.addAttribute("recfound", localBodyTypelist!=null && !localBodyTypelist.isEmpty() && localBodyTypelist.size()>0?true:false);
					model.addAttribute("message", message);
					model.addAttribute("lgtLocalBodyType", localBodyTypelist);
			return mav;
		 } catch (Exception e) {
			log.debug("Exception" + e);
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;}
	}

	// View LocalBody Detail modified by sushil on 14-03-2013
	@RequestMapping(value = "/globalviewLocalBodyDetail")
	public ModelAndView globalviewLocalBodyDetailList(@ModelAttribute("localbodybean") LocalGovtBodyForm localbodybean, BindingResult result, HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		String id = request.getParameter("id");
		int localBodyCode = localbodybean.getGloballocalbodyId() == null ? Integer.parseInt(id) : localbodybean.getGloballocalbodyId();
		try {
			List<LocalGovtBodyDataForm> listLocalBodyDetails = localGovtBodyService.getLocalBodyDetailsModify(localBodyCode);
			if (listLocalBodyDetails.size() > 0) {
				// Add Govt order details and the view map option
				for (int i = 0; i < listLocalBodyDetails.size(); i++) {
					if (listLocalBodyDetails.get(i).getOrderCodecr() != null) {
						int orderValue = listLocalBodyDetails.get(i).getOrderCodecr();
						if (orderValue > 0) {
							/*
							 * Retrieving the Map details attachment from the
							 * database
							 */

							List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
							mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(localBodyCode, 'G');

							if (mapAttachmentList.size() > 0) {
								model.addAttribute("mapAttachmentList", mapAttachmentList);
							} else {
								mav = new ModelAndView("globalview_localbodydetail");
								result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
								localbodybean.setListLocalBodyDetails(listLocalBodyDetails);
							}
							/*
							 * Retrieving the Govt order details attachment from
							 * the database
							 */

							List<Attachment> attachmentList = localGovtBodyService.getAttachmentsbyOrderCode(orderValue);
							if (!attachmentList.isEmpty()) {
								mav = new ModelAndView("globalview_localbodydetail");
								model.addAttribute("attachmentList", attachmentList);
								String file = null;
								if (attachmentList.size() > 0) {
									file=attachmentList.get(0).getFileLocation();
								}
								model.addAttribute("filenameattch", file);
								localbodybean.setListLocalBodyDetails(listLocalBodyDetails);
								return mav;
							} else {
								mav = new ModelAndView("globalview_localbodydetail");
								result.rejectValue("noAttachmentRecord", "Error.NOATTACHMENTDETAIL");
								localbodybean.setListLocalBodyDetails(listLocalBodyDetails);
								return mav;
							}
						} else {
							mav = new ModelAndView("globalview_localbodydetail");
							result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
							localbodybean.setListLocalBodyDetails(listLocalBodyDetails);
							return mav;
						}
					} else {
						mav = new ModelAndView("globalview_localbodydetail");
						result.rejectValue("noOrderRecord", "error.NOGOVTORDERDETAILSFOUND");
						/*
						 * Retrieving the Map details attachment from the
						 * database
						 */
						List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
						mapAttachmentList = govtOrderService.getMapAttachmentListbyEntity(localBodyCode, 'G');

						if (mapAttachmentList.size() > 0) {
							model.addAttribute("mapAttachmentList", mapAttachmentList);
						} else {
							mav = new ModelAndView("globalview_localbodydetail");
							result.rejectValue("noMapRecord", "Error.NOMAPRECORD");
							localbodybean.setListLocalBodyDetails(listLocalBodyDetails);
							return mav;
						}
						localbodybean.setListLocalBodyDetails(listLocalBodyDetails);
						return mav;
					}
				}
			} else {
				mav = new ModelAndView("globalview_localbodydetail");
				String saveMessage = "No State Details Found";
				model.addAttribute("saveMsg", saveMessage);
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
		
		@RequestMapping(value = "/globalviewGovtOrderDetailForLB")
		public String globalviewGovtOrderDetail(
				@ModelAttribute("localbodybean") LocalGovtBodyForm localbodybean,
				BindingResult result, HttpServletRequest request, Model model,HttpSession session) {
			ModelAndView mav = null;
			try
			{
				mav = new ModelAndView("globalview_clocalbody");
				//System.out.println("Value of hidden field is::::::"+request.getParameter("lbCode1"));
				int localBodyCode	=	Integer.parseInt(request.getParameter("lbCode1"));
				List<LocalGovtBodyDataForm> listLocalBodyDetails = localGovtBodyService.getLocalBodyDetailsModify(localBodyCode);
				if (listLocalBodyDetails.size() > 0)
				{
					// Add Govt order details and the view map option
					for (int i = 0; i < listLocalBodyDetails.size(); i++) 
					{
						if (listLocalBodyDetails.get(i).getOrderCodecr() != null)
						{
							int orderValue = listLocalBodyDetails.get(i).getOrderCodecr();
							if (orderValue > 0) 
							{
									/*
									 * Retrieving the Govt order details attachment from
									 * the database
									 */
									List<Attachment> attachmentList = localGovtBodyService
											.getAttachmentsbyOrderCode(orderValue);
										if (!attachmentList.isEmpty()) 
										{
											String fileName	=	"";
											try
											{
												/*List<Attachment> listOFMetaData = (List<Attachment>) request.getAttribute("attachmentList");
												Iterator<Attachment> it = listOFMetaData.iterator();*/
												Iterator<Attachment> it = attachmentList.iterator();
												if (it.hasNext()) 
												{
														Attachment abc = (Attachment) it.next();
														fileName	=	abc.getFileName();
												}
											
											} 
											catch (Exception e) 
											{
												log.debug("Exception" + e);
											}
											model.addAttribute("fileName",fileName);
											session.setAttribute("file", fileName);
											model.addAttribute("display",true);
											//System.out.println("successfull"+localBodyCode);
											return "redirect:reportAttachDownloadsForLB.do";
										}
										else 
										{
												mav = new ModelAndView("globalview_clocalbody");
												result.rejectValue("noAttachmentRecord","Error.NOATTACHMENTDETAIL");
												return "redirect:reportAttachDownloadsForLB.do";
										}
							}
							else 
							{
									mav = new ModelAndView("globalview_clocalbody");
									result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
									return "redirect:reportAttachDownloadsForLB.do";
							}
						}
						else 
						{
							mav = new ModelAndView("globalview_clocalbody");
							result.rejectValue("noOrderRecord","error.NOGOVTORDERDETAILSFOUND");
						}
					}
				}
				else
				{
					mav = new ModelAndView("globalview_clocalbody");
					String saveMessage = "No State Details Found";
					model.addAttribute("saveMsg", saveMessage);
					return "redirect:reportAttachDownloadsForLB.do";
				}
			}
			catch(Exception e)
			{
				log.debug("Exception" + e);
			}
			return "redirect:reportAttachDownloadsForLB.do";
		}
		
		@RequestMapping(value = "/reportAttachDownloadsForLB", method = RequestMethod.GET)
		public String reportAttachDownloadsForLB(
				@ModelAttribute("localbodybean") LocalGovtBodyForm localbodybean,
				BindingResult result, Model model, HttpServletRequest request,
				HttpServletResponse response,HttpSession session) throws Exception {
			ModelAndView mav = new ModelAndView("globalview_clocalbody");
			//System.out.println("m in download");
			AttachmentMaster attachmentList = govtOrderService
					.getUploadFileConfigurationDetails(1);
			String message="";
			try {
				message	=	getAttachDownloadForLocalBody(attachmentList, result,model,request, response, session);
				if(message=="ERROR : Government order not uploaded" || message.equals("Government order not uploaded") )
				{
					model.addAttribute("saveMsg",message);
					model.addAttribute("display",true);
					return "redirect:globalviewlocalbodyGet.do";
				}
				
			} catch (Exception e) {
				log.debug("Exception" + e);
			}
			model.addAttribute("display",true);
			return null;
		}

		public String getAttachDownloadForLocalBody(AttachmentMaster attachmentMasterDetail,BindingResult result,Model model,
				HttpServletRequest request, HttpServletResponse response,HttpSession session)
				throws IOException {
			String message = "SUCCESS";
			// Getting Master Table Data.

			String fileUploadLocation = attachmentMasterDetail.getFileLocation();
			String fileNameToDownload = (String)session.getAttribute("file");
			fileUploadLocation = fileUploadLocation.replace("\\\\", "/");
			String filename = fileUploadLocation.replace("\\", "/") + "/"
					+ fileNameToDownload;
			ModelAndView mav = new ModelAndView("globalview_clocalbody");
			File file = null;
			FileInputStream fileIn = null;
			ServletOutputStream out = null;
			try {
				file = new File(filename);

				if (file.exists()) {
					fileIn = new FileInputStream(file);

					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition",
							"attachment;filename=" + file.getName());
					out = response.getOutputStream();

					byte[] outputByte = new byte[4096];
					// copy binary contect to output stream
					while (fileIn.read(outputByte, 0, 4096) != -1) {
						out.write(outputByte, 0, 4096);
					}
				} else {
					message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
					message	=	"1";
				}

			} catch (Exception e) {
				log.debug("Exception" + e);
				//System.out.println(e);
			} finally {
				if (fileIn != null){
					try {
						fileIn.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						log.debug("Exception" + e);
					}
				}	
				if (out != null) {
					out.flush();
					out.close();
				}

			}
			return message;
		}
		
		//Redirect for file not uploaded
		
		@RequestMapping(value = "/globalviewlocalbodyGet", method = RequestMethod.GET)
		public ModelAndView redirectgloballocalbodyPage(
				@ModelAttribute("localbodybean") LocalGovtBodyForm localbodybean,
				BindingResult result, Model model, HttpServletRequest request,
				SessionStatus status, HttpSession httpSession) {
			ModelAndView mav = null;
			String getCategoryType = "";
			int localBodyTypeCode = 0;
			int stateCode = 0;
			int localBodyCode = 0;
			 localbodybean =  (LocalGovtBodyForm) httpSession.getAttribute("redirectLocalBodyBean");
			 String message="";
			try {
				message = commonService.getApplicationBundle("Error.GOVTORDERNOTUPLOADED");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				log.debug("Exception" + e1);
			}
			 model.addAttribute("saveMsg",message);
			 model.addAttribute("display",true);
			try {
				mav = new ModelAndView("globalview_clocalbody");
						if (localbodybean.getCode().toString().equalsIgnoreCase("")
								&& localbodybean.getLocalBodyNameEnglish()
										.toString().equalsIgnoreCase("")) {
							stateCode = Integer.parseInt(localbodybean
									.getStateNameEnglish());
							localBodyTypeCode = Integer.parseInt(localbodybean
									.getLocalBodyName());
							getCategoryType = localGovtBodyService
									.getCategoryFromLocalBodyTypeCode(localBodyTypeCode);
							//System.out.println("value of the category would be>>>>"
							//		+ getCategoryType);
							if (stateCode != 0) {
								if (getCategoryType.toString()
										.equalsIgnoreCase("U")) {
									//System.out.println("test");
									getLocalGovtSetupList = localGovtSetupService
											.isStateSetup(stateCode, 'U');
									if (getLocalGovtSetupList != null
											&& !getLocalGovtSetupList.isEmpty()) {
										model.addAttribute("stateCode", stateCode);
										request.setAttribute("localSetUpList",
												getLocalGovtSetupList);
										localbodybean.setLgd_lbCategory("U");
										localBodyTypelist1 = new ArrayList<LocalbodyforStateWiseFinal>();
										localBodyTypelist1 = localGovtBodyService
												.getLocalBodyListStateCategoryWise(
														localBodyTypeCode,
														stateCode,null,null,null);

										if (localBodyTypelist1.size() > 0) {
											localbodybean = setLBtypeFinal(
													localBodyTypelist1,
													localbodybean);
										}
										mav = new ModelAndView(
												"globalview_clocalbody");
										model.addAttribute("lgtLocalBodyType",
												localBodyTypelist1);
										mav.addObject("localbodybean",
												localbodybean);
										model.addAttribute("display",true);
									} else {
										mav = new ModelAndView("success");
										mav.addObject("msgid",
												"Please first Set Up Local Government for this State");
									}
								}
								if (getCategoryType.toString()
										.equalsIgnoreCase("T")) {
									//System.out.println("test");
									getLocalGovtSetupList = localGovtSetupService
											.isStateSetup(stateCode, 'T');
									if (getLocalGovtSetupList != null
											&& !getLocalGovtSetupList.isEmpty()) {
										model.addAttribute("stateCode", stateCode);
										request.setAttribute("localSetUpList",
												getLocalGovtSetupList);
										localbodybean.setLgd_lbCategory("T");
										localBodyTypelist1 = new ArrayList<LocalbodyforStateWiseFinal>();
										localBodyTypelist1 = localGovtBodyService
												.getLocalBodyListStateCategoryWise(
														localBodyTypeCode,
														stateCode,null,null,null);

										if (localBodyTypelist1.size() > 0) {
											localbodybean = setLBtypeFinal(
													localBodyTypelist1,
													localbodybean);
										}
										mav = new ModelAndView(
												"globalview_clocalbody");
										model.addAttribute("lgtLocalBodyType",
												localBodyTypelist);
										mav.addObject("localbodybean",
												localbodybean);
									} else {
										mav = new ModelAndView("success");
										mav.addObject("msgid",
												"Please first Set Up Local Government for this State");
									}
								}
								if (getCategoryType.toString()
										.equalsIgnoreCase("P")) {
									//System.out.println("test");
									getLocalGovtSetupList = localGovtSetupService
											.isStateSetup(stateCode, 'P');
									if (getLocalGovtSetupList != null
											&& !getLocalGovtSetupList.isEmpty()) {
										model.addAttribute("stateCode", stateCode);
										request.setAttribute("localSetUpList",
												getLocalGovtSetupList);
										localbodybean.setLgd_lbCategory("P");
										String lbNameCode	=	localbodybean.getLocalBodyName();
										if(Integer.parseInt(lbNameCode)==1)
										{
											int lbCode	=	Integer.parseInt(httpSession.getAttribute("redirectLbCodeForselectedLocalBodyListCode").toString());
											List<ParentWiseLBList> parentWiseLBList = new ArrayList<ParentWiseLBList>();
											parentWiseLBList = localGovtBodyService
													.getParentWiseLBList(lbCode);
											if (parentWiseLBList.size() > 0) {
												for (int j = 0; j < parentWiseLBList
														.size(); j++) {
													String govtBodyName = parentWiseLBList.get(j).getLocalBodyNameEng();
													if (govtBodyName != null) {
														if (parentWiseLBList.get(j).getLocalBodyNameEng() != null){
															localbodybean
																.setLgd_LBNomenclatureInter(parentWiseLBList.get(j).getLocalBodyNameEng());
														}
													}
												}
												mav = new ModelAndView(
														"globalview_clocalbody");
												model.addAttribute("lgtLocalBodyType",
														parentWiseLBList);
												mav.addObject("localbodybean",
														localbodybean);
											}
										}
										if(Integer.parseInt(lbNameCode)==2)
										{
											int lbCode	=	Integer.parseInt(httpSession.getAttribute("redirectLbCodeFordpListCode").toString());
											List<ParentWiseLBList> parentWiseLBList = new ArrayList<ParentWiseLBList>();
											parentWiseLBList = localGovtBodyService
													.getParentWiseLBList(lbCode);
											if (parentWiseLBList.size() > 0) {
												for (int j = 0; j < parentWiseLBList
														.size(); j++) {
													String govtBodyName = parentWiseLBList.get(j).getLocalBodyNameEng();
													if (govtBodyName != null) {
														if (parentWiseLBList.get(j).getLocalBodyNameEng() != null){
															localbodybean
																.setLgd_LBNomenclatureInter(parentWiseLBList.get(j).getLocalBodyNameEng());
														}	
													}
												}
												mav = new ModelAndView(
														"globalview_clocalbody");
												model.addAttribute("lgtLocalBodyType",
														parentWiseLBList);
												mav.addObject("localbodybean",
														localbodybean);
											}
										}
										if(Integer.parseInt(lbNameCode)==3)
										{
											int lbCode	=	Integer.parseInt(httpSession.getAttribute("redirectLbCode").toString());
											List<ParentWiseLBList> parentWiseLBList = new ArrayList<ParentWiseLBList>();
											parentWiseLBList = localGovtBodyService
													.getParentWiseLBList(lbCode);
											if (parentWiseLBList.size() > 0) {
												for (int j = 0; j < parentWiseLBList
														.size(); j++) {
													String govtBodyName = parentWiseLBList.get(j).getLocalBodyNameEng();
													if (govtBodyName != null) {
														if (parentWiseLBList.get(j).getLocalBodyNameEng() != null){
															localbodybean
																.setLgd_LBNomenclatureInter(parentWiseLBList.get(j).getLocalBodyNameEng());
														}	
													}
												}
												mav = new ModelAndView(
														"globalview_clocalbody");
												model.addAttribute("lgtLocalBodyType",
														parentWiseLBList);
												mav.addObject("localbodybean",
														localbodybean);
											}
										}
									} else {
										mav = new ModelAndView("success");
										mav.addObject("msgid",
												"Please first Set Up Local Government for this State");
									}
								}
							} else {
								mav = new ModelAndView("configview");
								message = "Please First Select State";
								mav.addObject("msgid", message);
							}
						} else if (localbodybean.getCode().length() > 0
								&& !localbodybean.getCode().isEmpty()
								&& localbodybean.getCode() != null) {

							localBodyCode = Integer.parseInt(localbodybean
									.getCode());
							localBodyTypeCode = localGovtBodyService
									.getLBTypeCodeFromLBCode(localBodyCode);
							List<Localbody> localBodyList = new ArrayList<Localbody>();
							localBodyList = localGovtBodyService
									.getLocalGovSetupWiseLocalbodyList(localBodyCode);
							mav = new ModelAndView("globalview_clocalbody");
							model.addAttribute("lgtLocalBody", localBodyList);
							//httpSession.setAttribute("lgtLocalBody", localBodyList);
							model.addAttribute("display", true);
							mav.addObject("localbodybean", localbodybean);

						} else if (localbodybean.getLocalBodyNameEnglish() != null
								&& localbodybean.getLocalBodyNameEnglish().length() > 0
								&& !localbodybean.getLocalBodyNameEnglish()
										.isEmpty()) {

							String localBodyTypeName = localbodybean
									.getLocalBodyNameEnglish();
							List<Localbody> localBodyList = new ArrayList<Localbody>();
							localBodyList = localGovtBodyService
									.getLocalGovSetupWiseLocalbodyListByName(localBodyTypeName);
							mav = new ModelAndView("globalview_clocalbody");
							model.addAttribute("lgtLocalBody", localBodyList);
							mav.addObject("localbodybean", localbodybean);

						}
			} catch (Exception e) {
				log.debug("Exception" + e);
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				localbodybean.setCaptchaAnswer(null);
				localbodybean.setCaptchaAnswers(null);
				return mav;
			}
			localbodybean.setCaptchaAnswer(null);
			localbodybean.setCaptchaAnswers(null);
			return mav;
		}
		
		
		@ModelAttribute("stateSourceList")
		public List<State> populateSourceStateList(HttpServletRequest request) {

			try {
				return stateService.getStateSourceList();
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				// mav = new ModelAndView(redirectPath);
				return null;
			}

		} 
		
		
	/*
		  @modified by Maneesh on 24-05-2013 
		 */
@RequestMapping(value = "/viewdepartmentbyministrycodeforstate", method = RequestMethod.POST)
public ModelAndView getDeptByMinCodeforstate(HttpSession httpSession,
		@ModelAttribute("viewDeptforstate") MinistryForm viewDept,
		HttpServletRequest request, Model model) {

	ModelAndView mav =  new ModelAndView("manage_StateDepartment");
	try {

		Integer entityCode=null;
		char category = viewDept.getCategory();
		httpSession.setAttribute("category", category);
		
		if(category=='S')
		{
			if(httpSession.getAttribute("stateCode")!=null)
			{
				entityCode=Integer.parseInt(httpSession.getAttribute("stateCode").toString());
				viewDept.setEntityCode(entityCode);
			}
			
			List<DistrictLineDepartment> listDistDept = organisationService.getLandRegionLineDepartmentList(entityCode,null,category);
			if(!listDistDept.isEmpty())
			{
				model.addAttribute("listDistDept", listDistDept);
				httpSession.setAttribute("entityCode", entityCode);
			}
			else
			{
				mav = new ModelAndView("error");
				String errorMsg = "Department List is not available";
				model.addAttribute("message", errorMsg);
				return mav;
			}
			
			
		}
		else
		{
			switch(category)
			{
			case 'A' :
				entityCode=viewDept.getAdminUnitCode();
				break;
			case 'D':	
				entityCode=viewDept.getDistrictCode();
				break;
				
			case 'T':
				String strSubDistrictCode=viewDept.getSubdistrictNameEnglish();
				if(strSubDistrictCode!=null){
					entityCode = Integer.parseInt(strSubDistrictCode);
				}	
				break;
				
			case 'B':	
				String strBlockCode=viewDept.getBlockNameEnglish();
				if(strBlockCode!=null){
					entityCode = Integer.parseInt(strBlockCode);
				}	
				break;
			case 'V':	
				String strVillageCode=viewDept.getVillageNameEnglish();
				if(strVillageCode!=null){
					entityCode = Integer.parseInt(strVillageCode);
				}	
				break;
			}
			
			viewDept.setEntityCode(entityCode);
			httpSession.setAttribute("entityCode", entityCode);
			List<DistrictLineDepartment> listDistDept = organisationService.getLandRegionLineDepartmentList(entityCode,null, category);
			if(!listDistDept.isEmpty())
			{
				model.addAttribute("listDistDept", listDistDept);
			}
			else
			{
				mav = new ModelAndView("error");
				String errorMsg = "Department List is not available";
				model.addAttribute("message", errorMsg);
				return mav;
			}
			
		}
		
		
		
		/*int villageCode = Integer.parseInt(viewDept
				.getVillageNameEnglish());
		List<StateLineDepartment> listDistDept = null;
		
		listDistDept = organisationService
				.getDistrictLineDepartmentList(entityCode, category);
		
		
		model.addAttribute("listDistDept", listDistDept);
		session.setAttribute("category", 'S');
		
		
		if (category != 'N' && category == 'S') {
			// String statecode=(String)session.getAttribute("stateCode");
			int deptCode = 2;
			char entityType = 'S';
			List<StateLineDepartment> listDept = null;
			listDept = new ArrayList<StateLineDepartment>();
			listDept = organisationService.getStateLineDepartmentList(
					Integer.parseInt(statecode), deptCode, entityType);
			model.addAttribute("listDept", listDept);
			session.setAttribute("category", 'S');
		
			char level = ' ';
		char levelCode = 'S';
		List<DistrictLineDepartment> listDistDept = null;
		mav = new ModelAndView("view_departmentList1");
		
		model.addAttribute("category",category);
		String statecode = (String) session.getAttribute("stateCode");
		int lBTypeCode = viewDept.getLocalBodyTypeCode();
		int lBCode = viewDept.getLocalBodyCode();
		viewDept.setLimit(2000);
		viewDept.setOffset(0);
		statecode = (String) session.getAttribute("stateCode");

		List<LocalBodyTypeWiseDepartment> listLBTWiseDeptList = null;
		if (lBTypeCode != 0) {
			listLBTWiseDeptList = localGovtBodyService
					.getLocalBodyTypeWiseDeptList(
							Integer.parseInt(statecode), lBTypeCode,
							levelCode);
		}
		if (lBCode != 0) {
			listLBTWiseDeptList = localGovtBodyService
					.getLocalBodyWiseDeptList(Integer.parseInt(statecode),
							lBCode, levelCode);
		}
		model.addAttribute("listLBTWiseDeptList", listLBTWiseDeptList);
		viewDept.setListLBTWiseDeptList(listLBTWiseDeptList);

		if (category != 'N' && category == 'S') {
			// String statecode=(String)session.getAttribute("stateCode");
			int deptCode = 2;
			char entityType = 'S';
			List<StateLineDepartment> listDept = null;
			listDept = new ArrayList<StateLineDepartment>();
			listDept = organisationService.getStateLineDepartmentList(
					Integer.parseInt(statecode), deptCode, entityType);
			model.addAttribute("listDept", listDept);
			session.setAttribute("category", 'S');
			
			// ////////
			if (listDept.size() > 0) {
				mav = new ModelAndView("view_departmentList1");
				model.addAttribute("viewPage", "abc");
				return mav;
			} else {
				mav = new ModelAndView("error");
				String errorMsg = "Department List is not available";
				model.addAttribute("message", errorMsg);
				return mav;
			}
			// ////////
		} else if (category != 'N' && category == 'D') {
			level = 'D';
			int districtCode = viewDept.getDistrictCode();
			listDistDept = new ArrayList<DistrictLineDepartment>();
			listDistDept = organisationService
					.getDistrictLineDepartmentList(districtCode, level);
			model.addAttribute("listDistDept", listDistDept);
			viewDept.setListDistDept(listDistDept);
			session.setAttribute("category", 'D');

			// /////////
			if (listDistDept.size() > 0) {
				mav = new ModelAndView("view_departmentList1");
				model.addAttribute("viewPage", "abc");
				return mav;
			} else {
				mav = new ModelAndView("error");
				String errorMsg = "Department List is not available";
				model.addAttribute("message", errorMsg);
				return mav;
			}
			// ////////
		} else if (category != 'N' && category == 'T') {
			level = 'T';
			int subDistrictCode = Integer.parseInt(viewDept
					.getSubdistrictNameEnglish());
			listDistDept = new ArrayList<DistrictLineDepartment>();
			listDistDept = organisationService
					.getSubDistrictLineDepartmentList(subDistrictCode,
							level);
			model.addAttribute("listDistDept", listDistDept);
			viewDept.setListDistDept(listDistDept);
			session.setAttribute("category", 'T');
			// //////
			if (listDistDept.size() > 0) {
				mav = new ModelAndView("view_departmentList1");
				model.addAttribute("viewPage", "abc");
				return mav;
			} else {
				mav = new ModelAndView("error");
				String errorMsg = "Department List is not available";
				model.addAttribute("message", errorMsg);
				return mav;
			}
			// /////
		} else if (category != 'N' && category == 'B') {
			level = 'V';
			int blockCode = Integer
					.parseInt(viewDept.getBlockNameEnglish());
			listDistDept = new ArrayList<DistrictLineDepartment>();
			listDistDept = organisationService.getBlockLineDepartmentList(
					blockCode, level);
			model.addAttribute("listDistDept", listDistDept);
			viewDept.setListDistDept(listDistDept);
			session.setAttribute("category", 'B');
			// ////////
			if (listDistDept.size() > 0) {
				mav = new ModelAndView("view_departmentList1");
				model.addAttribute("viewPage", "abc");
				return mav;
			} else {
				mav = new ModelAndView("error");
				String errorMsg = "Department List is not available";
				model.addAttribute("message", errorMsg);
				return mav;
			}
			// ////////
		} else if (category != 'N' && category == 'V') {
			level = 'V';
			int villageCode = Integer.parseInt(viewDept
					.getVillageNameEnglish());
			listDistDept = new ArrayList<DistrictLineDepartment>();
			listDistDept = organisationService
					.getVillageLineDepartmentList(villageCode, level);
			model.addAttribute("listDistDept", listDistDept);
			viewDept.setListDistDept(listDistDept);
			session.setAttribute("category", 'V');
			// ///////////
			if (listDistDept.size() > 0) {
				mav = new ModelAndView("view_departmentList1");
				model.addAttribute("viewPage", "abc");
				return mav;
			} else {
				mav = new ModelAndView("error");
				String errorMsg = "Department List is not available";
				model.addAttribute("message", errorMsg);
				return mav;
			}
			// ///////////

		}
		
		 * System.out.println("Level : "+level); int
		 * districtCode=viewDept.getDistrictCode();
		 * System.out.println("districtCode :: "+districtCode);
		 * if(districtCode!=0 && category != 'N') { listDistDept = new
		 * ArrayList<DistrictLineDepartment>(); listDistDept =
		 * organisationService
		 * .getDistrictLineDepartmentList(districtCode,level);
		 * model.addAttribute("listDistDept", listDistDept);
		 * viewDept.setListDistDept(listDistDept); }
		 

		if (!viewDept.getMinistryName().equals("")) {
			int orgCode = Integer.parseInt(viewDept.getMinistryName());

			String query = "from Organization org where org.parentorgcode="
					+ orgCode + "and org.isactive=true order by orgName";

			List<Organization> listDeptDetails = null;
			List<Organization> listOrg = null;

			listDeptDetails = new ArrayList<Organization>();

			listDeptDetails = organisationService.getDepartmentList(query);

			model.addAttribute("listDepartment", listDeptDetails);
			viewDept.setListDeptDetails(listDeptDetails);

			listOrg = new ArrayList<Organization>();
			if (viewDept.getMinistryName() != null) {
				listOrg = sessionFactory
						.openSession()
						.createQuery(
								"from Organization org where org.organizationType.orgTypeCode=1 and org.isactive=true order by orgName")
						.list();
			}
			
			 * else { listOrg=sessionFactory.openSession().createQuery(
			 * "from Organization org where isactive=true and org.parentorgcode="
			 * + orgCode).list(); }
			 
			model.addAttribute("ministryList", listOrg);

		}
		
		 * else if(viewDept.getMinistryName().equals("")) {
		 * List<DepartmentByCentreLevel> listDeptDetails= null;
		 * 
		 * listDeptDetails = new ArrayList<DepartmentByCentreLevel>();
		 * 
		 * listDeptDetails=organisationService.getDepartmentListByCentreLevel
		 * ( entityCode); model.addAttribute("listDepartment",
		 * listDeptDetails); }
		 */
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
@modified by Maneesh on 24-05-2013 
*/
	@RequestMapping(value = "/viewDepartment")
	public ModelAndView viewDepartmentDetail(@ModelAttribute("viewMinistry") MinistryForm viewDepartment, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("view_department");
		int orgCode = viewDepartment.getDepartmentId();
		Integer entityCode = viewDepartment.getEntityCode();
		char category = viewDepartment.getCategory();
		try {
			if(entityCode == null) {
				List<MinistryForm> listMinistry = null;
				listMinistry = new ArrayList<MinistryForm>();
				listMinistry = organisationService.getMinistryDetails(orgCode);
				mav = new ModelAndView("view_departmentdetail");
				model.addAttribute("listMinistry", listMinistry);
				mav.addObject("viewDepartment", viewDepartment);
				viewDepartment.setListMinistry(listMinistry);
				return mav;
			} else {
				List<DistrictLineDepartment> listDistDept = organisationService.getLandRegionLineDepartmentList(entityCode, orgCode, category);
				model.addAttribute("listDistDept", listDistDept);
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
			 * This method to get localbody detail in popup dialog and also show from extenal by calling url
			 * @author Maneesh Kumar
			 * @param isState
			 * @param code
			 * @param model
			 * @param request
			 * @param httpSession
			 * @since 18DEc2014
			 * @return
			 */
				@RequestMapping(value = "/viewEntityDetail")
				public ModelAndView viewEntityDetail(@RequestParam("isState") String isState,@RequestParam("code") Integer code,
						Model model, 
						HttpServletRequest request, 
						HttpSession httpSession) {
					   ModelAndView	mav =new ModelAndView("entity_detail");
					try {
						if(code!=null){
						List<LocalGovtBodyDataForm> localGovtBodyDataFormList = localGovtBodyService.getLocalBodyDetailsModify(code);
						model.addAttribute("localGovtBodyDataFormList",localGovtBodyDataFormList);
						model.addAttribute("lbHierchicalDetail",reportService.getLbHierchicalDetail(code,null,false));
						model.addAttribute("completedCoverageDetails", localBodyService.fetchExistingCoverage(code));
						if (!localGovtBodyDataFormList.isEmpty()) {
							
							/*
							 * Retrieving the Map details attachment from the
							 * database
							 */
								model.addAttribute("mapAttachmentList", govtOrderService.getMapAttachmentListbyEntity(code, 'G'));
							// Add Govt order details and the view map option
							for(LocalGovtBodyDataForm localGovtBodyDataForm:localGovtBodyDataFormList){
								Integer orderCode=localGovtBodyDataForm.getOrderCodecr();
								model.addAttribute("orderCode",orderCode);
								if(orderCode!=null && orderCode>0){
										model.addAttribute("attachmentList", localGovtBodyService.getAttachmentsbyOrderCode(orderCode));
								}
								
							}
						 } 
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
				
				@RequestMapping(value = "/viewBlockDetailInDialog")
				public ModelAndView viewBlockDetailInDialog(
						@ModelAttribute("blockView") BlockForm blockView,
						HttpServletRequest request, Model model)
				{

					ModelAndView mav = null;
					int blockCode=blockView.getBlockId();
					try {
						mav = new ModelAndView("view_blockdetail_dialog");
						List<BlockDataForm> blockDataFormList=blockService	.getBlockDetailsModify(blockCode);
						
						blockView.setListBlockDetails(blockDataFormList);
						model.addAttribute("blockHistory", blockService.getBlockHistoryDetail('B', blockCode));
						if(blockDataFormList!=null && !blockDataFormList.isEmpty()){
							Integer orderCode=blockDataFormList.get(0).getOrderCodecr();
							if(orderCode!=null) {
								List<Attachment> attachmentList=localGovtBodyService.getAttachmentsbyOrderCode(orderCode);
								if(attachmentList!=null && !attachmentList.isEmpty()){
									model.addAttribute("attachment",attachmentList.get(0));
								}
							}
							
							
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
				
				
				
} 