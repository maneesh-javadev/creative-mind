package in.nic.pes.lgd.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.BlockWiseVillagesAndUlb;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.LandRegionDetail;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.PesApplicationMaster;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatewisePesaPanchyat;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.common.CaptchaValidator;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.BlockDataForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.StateDataForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.service.BlockService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.OrganizationService;
import in.nic.pes.lgd.service.ParliamentService;
import in.nic.pes.lgd.service.ReportService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.CommonUtil;
import in.nic.pes.lgd.validator.CitizenValidator;


@Controller
public class CitizenController { // NO_UCD (unused code)
	
	private static final Logger log = Logger.getLogger(CitizenController.class);
	
	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	DistrictService districtService;
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
	CitizenValidator citizenValidator;
	@Autowired
	ParliamentService parliamentService;
	@Autowired
	OrganizationService organisationService;
	@Autowired
	ReportService reportService;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	StateDAO stateDao;
	int stateCode = 1;

	// View District Part

	@RequestMapping(value = "/viewdistrictforcitizen", method = RequestMethod.GET)
	public ModelAndView showDistrictPage(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("view_cdistrict");
		try {
			mav.addObject("districtbean", new DistrictForm());
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
	
	//@RequestMapping(value = "/globalviewdistrictforcitizen", method = RequestMethod.GET)
	/*public ModelAndView showDistrictPageglobal(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("globalview_cdistrict");
		try {
			mav.addObject("districtbean", new DistrictForm());
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
	}*/
	
	

	@RequestMapping(value = "/viewdistrictforcitizen", method = RequestMethod.POST)
	public ModelAndView getDistrictPage(
			@ModelAttribute("districtbean") DistrictForm districtbean,BindingResult result, 
			Model model, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("view_cdistrict");
		try {
			citizenValidator.validateforgsubdistic(districtbean, result);
			if (result.hasErrors()) {
				return mav;
			}
			//System.out.println("districtbean==============="
			//		+ districtbean.getCode());
			String districtCode = districtbean.getCode();
			String districtName = districtbean.getDistrictNameEnglish();
			//int recordsLimit = districtbean.getRecordsLimit();
			String statCode = districtbean.getStateNameEnglish();
			//System.out.println("statecode : " + statCode);
			int stateCode;
			if (statCode == null || statCode == "") {
				stateCode = 27;
			} else {
				stateCode = Integer.parseInt(statCode);
			}
			//System.out.println("districtCode : " + districtCode
			//		+ " districtName: " + districtName + " recordsLimit: "
			//		+ recordsLimit);
			String query = "";
			if (!districtName.equals("") && districtCode.equals("")) {
				query = "from District d where d.isactive=true and upper(d.districtNameEnglish) like '"
						+ districtName.trim().toUpperCase()
						+ "%' order by districtNameEnglish";
			} else if (districtName.equals("") && !districtCode.equals("")) {
				query = "from District d where d.isactive=true and d.districtPK.districtCode="
						+ districtCode;
			} else if (!districtName.equals("") && !districtCode.equals("")) {
				query = "from District d where d.isactive=true and d.districtPK.districtCode="
						+ districtCode
						+ " and upper(d.districtNameEnglish) like '"
						+ districtName.trim().toUpperCase()
						+ "%' order by districtNameEnglish";
			} else if (districtName.equals("") && districtCode.equals("")) {
				query = "from District d where d.state.statePK.stateCode='"
						+ stateCode
						+ "'and isactive=true order by districtNameEnglish";
			}
			List<District> listDistrictDetails = null;
			listDistrictDetails = new ArrayList<District>();
			listDistrictDetails = districtService.getDistrictViewList(query);
			model.addAttribute("SEARCH_DISTRICT_RESULTS_KEY",
					listDistrictDetails);
			districtbean.setListDistrictDetail(listDistrictDetails);

			mav.addObject("districtbean", districtbean);
			model.addAttribute("viewPage", "abc");
			return mav;
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	/*
	 * // View District Detail
	 * 
	 * @RequestMapping(value = "/viewDistrictDetail", method =
	 * RequestMethod.GET) public ModelAndView viewDistrictDetailList(
	 * 
	 * @ModelAttribute("districtView") DistrictForm districtView, Model model,
	 * @RequestParam("id") Integer districtCode) { List<DistrictDataForm>
	 * listDistrictDetails = districtService
	 * .getDistrictDetailsModify(districtCode); ModelAndView mv = new
	 * ModelAndView("view_districtdetail");
	 * districtView.setListDistrictDetails(listDistrictDetails); return mv; }
	 */

	// View Village Part
	@RequestMapping(value = "/viewvillageforcitizen", method = RequestMethod.GET)
	public ModelAndView showVillagePage(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("view_cvillage");
		try {
			mav.addObject("villagebean", new VillageDataForm());
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
	
	//@RequestMapping(value = "/globalviewvillageforcitizen", method = RequestMethod.GET)
	/*public ModelAndView showVillagePageglobal(Model model, HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("globalview_cvillage");
		try 
		{
			mav.addObject("villagebean", new VillageDataForm());
			model.addAttribute("viewPage", "");
		} catch (Exception e) 
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}*/


	@RequestMapping(value = "/viewvillageforcitizen", method = RequestMethod.POST)
	public ModelAndView getVillagePage(
			@ModelAttribute("villagebean") VillageDataForm villagebean,BindingResult result, 
			Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			citizenValidator.validateforgvillage(villagebean, result);
			if (result.hasErrors()) {
				return mav;
			}
			mav = new ModelAndView("view_cvillage");
			/*System.out
					.println("village===============" + villagebean.getCode());*/
			String villageCode = villagebean.getCode();
			String villageName = villagebean.getVillageNameEnglish();
			String subDistrictCode = villagebean.getSubdistrictNameEnglish();
			//System.out.println("subdistrictCode : " + subDistrictCode);
			int subdistrictCode;
			if (subDistrictCode == null || subDistrictCode == "") {
				subdistrictCode = 532;
			} else {
				subdistrictCode = Integer.parseInt(subDistrictCode);
			}
			//int recordsLimit = villagebean.getRecordsLimit();
			// int subdistrictCode =532;
			//System.out.println("villageCode : " + villageCode
			//		+ " villageName: " + villageName + " recordsLimit: "
			//		+ recordsLimit);
			String query = "";
			if (!villageName.equals("") && villageCode.equals("")) {
				query = "from Village d where d.isactive=true and upper(d.villageNameEnglish) like'"
						+ villageName.trim().toUpperCase() + "%'";
			} else if (villageName.equals("") && !villageCode.equals("")) {
				query = "from Village d where d.isactive=true and d.villagePK.villageCode="
						+ villageCode;
			} else if (!villageName.equals("") && !villageCode.equals("")) {
				query = "from Village d where d.isactive=true and d.villagePK.villageCode="
						+ villageCode
						+ " and upper(d.villageNameEnglish) like '"
						+ villageName.trim().toUpperCase() + "%'";
			} else if (villageName.equals("") && villageCode.equals("")) {
				query = "from Village d where d.subdistrict.subdistrictPK.subdistrictCode='"
						+ subdistrictCode
						+ "'and d.isactive=true order by villageNameEnglish";
			} 
			List<Village> listVillageDetail = null;
			listVillageDetail = new ArrayList<Village>();
			listVillageDetail = villageService.getVillageViewList(query);
			model.addAttribute("SEARCH_VILLAGE_RESULTS_KEY", listVillageDetail);
			villagebean.setListVillageDetail(listVillageDetail);
			model.addAttribute("viewPage", "abc");
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

	@ModelAttribute("stateSourceList")
	public List<State> populateSourceStateList(HttpServletRequest request) {

		try {
			return stateService.getStateSourceList();
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);

			return null;
		}

	}

	/*
	 * // View Village Detail
	 * 
	 * @RequestMapping(value = "/viewVillageDetail", method = RequestMethod.GET)
	 * public ModelAndView viewVillageDetail(
	 * 
	 * @ModelAttribute("villageView") VillageForm villageView, Model model,
	 * @RequestParam("id") Integer villageCode) { List<VillageDataForm>
	 * listVillageDetails = villageService
	 * .getVillageDetailsModify(villageCode); ModelAndView mv = new
	 * ModelAndView("view_villagedetail");
	 * villageView.setListVillageDetails(listVillageDetails); return mv; }
	 */

	// View SubDistrict Part
	@RequestMapping(value = "/viewsubdistrictforcitizen", method = RequestMethod.GET)
	public ModelAndView showSubDistrictPage(Model model) {
		ModelAndView mav = new ModelAndView("view_csubdistrict");
		mav.addObject("subdistrictbean", new SubDistrictForm());
		model.addAttribute("viewPage", "");
		return mav;
	}
	
	//@RequestMapping(value = "/globalviewsubdistrictforcitizen", method = RequestMethod.GET)
	/*public ModelAndView showSubDistrictPageglobal(Model model) {
		ModelAndView mav = new ModelAndView("globalview_csubdistrict");
		mav.addObject("subdistrictbean", new SubDistrictForm());
		model.addAttribute("viewPage", "");
		return mav;
	}*/

	
	@RequestMapping(value = "/viewsubdistrictforcitizen", method = RequestMethod.POST)
	public ModelAndView getSubDistrictPage(
			@ModelAttribute("subdistrictbean") SubDistrictForm subdistrictbean,BindingResult result, 
			Model model, HttpServletRequest request) throws Exception {
		ModelAndView mav = null;
		try {
			citizenValidator.validateforgdistic(subdistrictbean, result);
			if (result.hasErrors()) {
				return mav;
			}
			mav = new ModelAndView("view_csubdistrict");
			//System.out.println("subdistrict==============="
			//		+ subdistrictbean.getCode());
			String subdistrictCode = subdistrictbean.getCode();
			String subdistrictName = subdistrictbean
					.getSubdistrictNameEnglish();
			//int recordsLimit = subdistrictbean.getRecordsLimit();
			String distCode = subdistrictbean.getDistrictNameEnglish();
			//System.out.println("districtcode : " + distCode);
			int districtCode;
			if (distCode == null || distCode == "") {
				districtCode = 27;
			} else {
				districtCode = Integer.parseInt(distCode);
			}

			String query = "";
			if (!subdistrictName.equals("") && subdistrictCode.equals("")) {
				query = "from Subdistrict d where d.isactive=true and upper(d.subdistrictNameEnglish) like '"
						+ subdistrictName.trim().toUpperCase() + "%'";
			} else if (subdistrictName.equals("")
					&& !subdistrictCode.equals("")) {
				query = "from Subdistrict d where d.isactive=true and d.subdistrictPK.subdistrictCode="
						+ subdistrictCode;
			} else if (!subdistrictName.equals("")
					&& !subdistrictCode.equals("")) {
				query = "from Subdistrict d where d.isactive=true and d.subdistrictPK.subdistrictCode="
						+ subdistrictCode
						+ " and upper(d.subdistrictNameEnglish) like '"
						+ subdistrictName.trim().toUpperCase() + "%'";
			} else if (subdistrictName.equals("") && subdistrictCode.equals("")) {
				query = "from Subdistrict d where d.district.districtPK.districtCode='"
						+ districtCode
						+ "'and d.isactive=true order by subdistrictNameEnglish";
			} 
			List<Subdistrict> listSubDistrictDetail = null;
			listSubDistrictDetail = new ArrayList<Subdistrict>();
			listSubDistrictDetail = subDistrictService
					.getSubDistrictViewList(query);
			model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",
					listSubDistrictDetail);
			mav.addObject("subdistrictbean", subdistrictbean);
			model.addAttribute("viewPage", "abc");
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

	/*
	 * // View SubDistrict Detail
	 * 
	 * @RequestMapping(value = "/viewSubDistrictDetail", method =
	 * RequestMethod.GET) public ModelAndView viewSubDistrictDetail(
	 * 
	 * @ModelAttribute("modifySubDistrictCmd") SubDistrictForm
	 * modifySubDistrictCmd, Model model, @RequestParam("id") Integer
	 * subdistrictCode) { List<SubdistrictDataForm> listSubdistrictDetails =
	 * subDistrictService .getSubdistrictDetailsModify(subdistrictCode);
	 * ModelAndView mv = new ModelAndView("view_subdistrictdetail");
	 * 
	 * modifySubDistrictCmd.setListSubdistrictDetails(listSubdistrictDetails);
	 * return mv; }
	 */

	/*
	 * // View Block Part
	 * 
	 * @RequestMapping(value = "/viewblock", method = RequestMethod.GET) public
	 * ModelAndView showBlockPage(Model model) { ModelAndView mav = new
	 * ModelAndView("view_block"); mav.addObject("blockView", new BlockForm());
	 * model.addAttribute("viewPage", ""); return mav; }
	 * 
	 * @RequestMapping(value = "/viewblock", method = RequestMethod.POST) public
	 * ModelAndView getBlockPage(
	 * 
	 * @ModelAttribute("blockView") BlockForm blockView, Model model) { try {
	 * ModelAndView mav = new ModelAndView("view_block");
	 * System.out.println("Block===============" + blockView.getCode()); String
	 * blockCode = blockView.getCode(); String blockName =
	 * blockView.getBlockNameEnglish(); int recordsLimit =
	 * blockView.getRecordsLimit(); int districtCode = 1;
	 * System.out.println("blockCode : " + blockCode + " blockName: " +
	 * blockName + " recordsLimit: " + recordsLimit); String query = ""; if
	 * (!blockName.equals("") && blockCode.equals("")) { query =
	 * "from Block d where d.isactive=true and upper(d.blockNameEnglish) like'"
	 * + blockName.trim().toUpperCase() + "%'"; } else if (blockName.equals("")
	 * && !blockCode.equals("")) { query =
	 * "from Block d where d.isactive=true and d.blockPK.blockCode=" +
	 * blockCode; } else if (!blockName.equals("") && !blockCode.equals("")) {
	 * query = "from Block d where d.isactive=true and d.blockPK.blockCode=" +
	 * blockCode + " and upper(d.blockNameEnglish) like '" +
	 * blockName.trim().toUpperCase() + "%'"; } else if (blockName.equals("") &&
	 * blockCode.equals("")) { query =
	 * "from Block d where d.district.districtPK.districtCode='" + districtCode
	 * + "'and isactive=true order by blockCode"; } else {
	 * System.out.println("Condition not matched"); } List<Block>
	 * listBlockDetails = null; listBlockDetails = new ArrayList<Block>();
	 * listBlockDetails = blockService.getBlockViewList(query);
	 * model.addAttribute("SEARCH_BLOCK_RESULTS_KEY", listBlockDetails);
	 * blockView.setListBlockDetail(listBlockDetails);
	 * mav.addObject("blockView", blockView); model.addAttribute("viewPage",
	 * "abc"); return mav; } catch (Exception e) {
	 * System.out.println(e.getMessage()); return null; } }
	 * 
	 * // View Block Detail
	 * 
	 * @RequestMapping(value = "/viewBlockDetail", method = RequestMethod.GET)
	 * public ModelAndView viewBlockDetail(
	 * 
	 * @ModelAttribute("blockView") BlockForm blockView, Model model,
	 * 
	 * @RequestParam("id") Integer blockCode) { List<BlockDataForm>
	 * listBlockDetails = blockService .getBlockDetailsModify(blockCode);
	 * ModelAndView mv = new ModelAndView("view_blockdetail");
	 * blockView.setListBlockDetails(listBlockDetails); return mv; }
	 */

	/*
	 * // View Local Govt Type Part
	 * 
	 * @RequestMapping(value = "/viewlocalgovttype", method = RequestMethod.GET)
	 * public ModelAndView showLocalGovtTypePage(Model model) { ModelAndView mav
	 * = new ModelAndView("view_localgovttype"); mav.addObject("viewGovtType",
	 * new LocalGovtTypeDataForm()); model.addAttribute("viewPage", ""); return
	 * mav; }
	 * 
	 * @RequestMapping(value = "/viewlocalgovttype", method =
	 * RequestMethod.POST) public ModelAndView getLocalGovtTypePage(
	 * 
	 * @ModelAttribute("viewGovtType") LocalGovtTypeDataForm viewGovtType, Model
	 * model) { try { ModelAndView mav = new ModelAndView("view_localgovttype");
	 * String localBodyTypeCode = viewGovtType.getCode(); char category =
	 * viewGovtType.getCategory();
	 * 
	 * String query = ""; if (category != 'N' && category == 'P' &&
	 * localBodyTypeCode.equals("")) { query =
	 * "from LocalBodyType d where d.category ='R' and  ispartixgovt=true and d.isactive=true order by localBodyTypeName"
	 * ; } else if (category != 'N' && category == 'T' &&
	 * localBodyTypeCode.equals("")) { query =
	 * "from LocalBodyType d where d.category ='R' and  ispartixgovt=false and d.isactive=true order by localBodyTypeName"
	 * ; } else if (category != 'N' && category == 'R' &&
	 * localBodyTypeCode.equals("")) { query =
	 * "from LocalBodyType d where d.category ='R' and d.isactive=true order by localBodyTypeName"
	 * ; } else if (category != 'N' && category == 'U' &&
	 * localBodyTypeCode.equals("")) { query =
	 * "from LocalBodyType d where d.category ='U' and d.isactive=true order by localBodyTypeName "
	 * ; } else if (category == 'N' && !localBodyTypeCode.equals("")) { query =
	 * "from LocalBodyType d where d.isactive=true and d.localBodyTypeCode=" +
	 * localBodyTypeCode; } else if (category != 'N' &&
	 * !localBodyTypeCode.equals("")) { query =
	 * "from LocalBodyType d where d.isactive=true and d.localBodyTypeCode=" +
	 * localBodyTypeCode + " and d.category ='" + category + "'"; } else if
	 * (category == 'N' && localBodyTypeCode.equals("")) { query =
	 * "from LocalBodyType d where d.isactive=true order by localBodyTypeName";
	 * } else { System.out.println("Condition not matched"); }
	 * List<LocalBodyType> listGovtTypeDetails = null; listGovtTypeDetails = new
	 * ArrayList<LocalBodyType>(); listGovtTypeDetails = localGovtBodyService
	 * .getGovtTypeViewList(query);
	 * model.addAttribute("SEARCH_GOVTTYPE_RESULTS_KEY", listGovtTypeDetails);
	 * viewGovtType.setListGovtTypeDetail(listGovtTypeDetails);
	 * mav.addObject("viewGovtType", viewGovtType);
	 * model.addAttribute("viewPage", "abc"); return mav; } catch (Exception e)
	 * { System.out.println(e.getMessage()); return null; } }
	 * 
	 * // View Local Body Type Details
	 * 
	 * @RequestMapping(value = "/viewLocalBodyTypeDetail", method =
	 * RequestMethod.GET) public ModelAndView viewBlockDetail(
	 * 
	 * @ModelAttribute("viewBodyType") LocalGovtTypeDataForm viewBodyType, Model
	 * model, @RequestParam("id") Integer localBodyTypeCode) {
	 * List<LocalGovtTypeDataForm> listGovtTypeDetails = localGovtBodyService
	 * .getLocalBodyTypeDetails(localBodyTypeCode); ModelAndView mv = new
	 * ModelAndView("view_bodytypedetail");
	 * viewBodyType.setListGovtTypeDetails(listGovtTypeDetails); return mv; }
	 */

	// View State Part
	@RequestMapping(value = "/viewstateforcitizen", method = RequestMethod.GET)
	public ModelAndView showStatePage(Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		try 
		{
			mav = new ModelAndView("view_cstate");
			mav.addObject("stateView", new StateDataForm());
			model.addAttribute("viewPage", "");
			return mav;
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
	}

	/*@RequestMapping(value = "/globalviewstateforcitizen", method = RequestMethod.GET)
	public ModelAndView showStatePageglobal(Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			mav = new ModelAndView("globalview_cstate");
			mav.addObject("stateView", new StateDataForm());
			model.addAttribute("viewPage", "");
			return mav;
		}
		catch (Exception e) 
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}*/

	
	@RequestMapping(value = "/viewstateforcitizen", method = RequestMethod.POST)
	public ModelAndView getStatePage(
			@ModelAttribute("stateView") StateDataForm stateView,
			HttpServletRequest request, Model model) {
		ModelAndView mav = null;
		try {
			mav = new ModelAndView("view_cstate");

			List<State> listStateDetails = new ArrayList<State>();
			listStateDetails = stateService.getStateViewList(stateView);
			model.addAttribute("SEARCH_STATE_RESULTS_KEY", listStateDetails);
			stateView.setListStateDetail(listStateDetails);
			mav.addObject("stateView", stateView);
			model.addAttribute("viewPage", "abc");
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
	
		
	//@RequestMapping(value = "/globalviewBlockforcitizen", method = RequestMethod.GET)
	/*public ModelAndView showBlockPage(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("globalview_cBlock");
		try {
			mav.addObject("blockbean", new BlockForm());
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
	}*/
	
	@RequestMapping(value = "/globalViewLGDRegistration")
	public String showLGDRegistration(Model model, HttpServletRequest request) {
		PesApplicationMaster application	=	new PesApplicationMaster();
		String url="";
		try {
			application=villageService.getApplicationURL(3);
			//System.out.println("url:::::"+application.getPesApplicationUrl().replaceAll("switchunit.do", "globalSaveRegister.htm"));
			url	=	application.getPesApplicationUrl().replaceAll("switchunit.do", "globalSaveRegister.htm");
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return "redirect:"+url+"";
	}
	
	//View Local Government Body by Tanuj
	
	/*	@RequestMapping(value = "/globalViewLocalBodyForCitizen", method = RequestMethod.GET)
		public ModelAndView showLocalBodyPageGlobal(Model model, HttpServletRequest request) {
			ModelAndView mav = new ModelAndView("globalview_clocalbody");
			try {
				mav.addObject("localbodybean", new LocalGovtBodyForm());
				model.addAttribute("viewPage", "");
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
		
	
	//@RequestMapping(value = "/globalViewLocalBodyForCitizen", method = RequestMethod.GET)
	public ModelAndView showLocalBodyPageGlobal(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		List<State> stateList = null;
		stateList = new ArrayList<State>();

		try {
			stateList = stateService.getStateSourceList();
			if (stateList.size() > 0) {
				// stateList.get(0).getStateCode();
				mav = new ModelAndView("globalview_clocalbody");
				// model.addAttribute("localGovtBodyForm", new
				// LocalGovtBodyForm());
				model.addAttribute("stateList", stateList);
				// localGovtBodyForm.setStateDetail(stateList);
				model.addAttribute("localGovtBodyForm", localGovtBodyForm);
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
	
		@RequestMapping(value = "/viewWard", method = RequestMethod.GET)
		public ModelAndView viewWard(
				@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
				Model model, HttpServletRequest request, HttpSession session) {

			ModelAndView mav = null;
			List<State> stateList=null;
			stateList=new ArrayList<State>(); 
			
			try {
			
				stateList= stateService.getStateSourceList();
				if(stateList.size()>0)
				{
				
					//stateList.get(0).getStateCode();
					mav = new ModelAndView("viewWards");
					//model.addAttribute("localGovtBodyForm", new LocalGovtBodyForm());
					model.addAttribute("stateList", stateList);
				//	localGovtBodyForm.setStateDetail(stateList);
					model.addAttribute("localGovtBodyForm", localGovtBodyForm);
					

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
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/viewWard", method = RequestMethod.POST)
		public ModelAndView viewWardPost(
				@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
				Model model, HttpServletRequest request, HttpSession session) {

			ModelAndView mav = null;
			List<State> stateList=null;
			stateList=new ArrayList<State>(); 
			Integer localBodyCode=null;
			try {
				mav = new ModelAndView("viewWards");
				
				boolean messageFlag=false;
				String captchaAnswer = localGovtBodyForm.getCaptchaAnswer();
				CaptchaValidator captchaValidator = new CaptchaValidator();
				if(captchaAnswer!=null){
						messageFlag = captchaValidator.validateCaptcha(session,captchaAnswer);
				}				
				stateList= stateService.getStateSourceList();
				localGovtBodyForm.setCaptchaAnswer(null);
				if (messageFlag == true)
				{
				
				
				if(stateList.size()>0)
				{
				
					//stateList.get(0).getStateCode();
					
					//model.addAttribute("localGovtBodyForm", new LocalGovtBodyForm());
				//String type=null;
				//String stateName=null;
				String localBodyName=null;
				String message=null;
					//localGovtBodyForm.setStateDetail(stateList);
					
					
					List<LocalbodyWard> localbodyWardList=new ArrayList<LocalbodyWard>();
					localBodyCode=localGovtBodyForm.getLblc();
					int offset=0;
					int limit=25;
					int lblc=localGovtBodyDAO.getlblc(localBodyCode);
					localGovtBodyForm.setLimit(limit);
					localGovtBodyForm.setOffset(offset);
					session.setAttribute("limit", localGovtBodyForm.getLimit());
					
					session.setAttribute("offset", localGovtBodyForm.getOffset());
					session.setAttribute("lblc", lblc);
					localbodyWardList=localGovtBodyService.getWardDetails(lblc/*,offset,limit*/);
					
					LocalbodyWard[] localbodyWardarray = localbodyWardList.toArray(new LocalbodyWard[localbodyWardList.size()]);
					Arrays.sort(localbodyWardarray,new LocalbodyWard());
				/*	int counter=localGovtBodyService.countWardDetails(lblc);*/
					/*session.setAttribute("counter",counter);*/
					List<LocalbodyWard> sortlocalbodyWardList = Arrays.asList(localbodyWardarray);  
				
					if(localBodyCode!=null)
					{
					
				   localBodyName=localGovtBodyService.getLocalBodyTypeMessagebyLocalbodyCode(localBodyCode);
				   if(localBodyName!=null){
					  message="Wards of "+localBodyName.trim();
				   }	  
					}
					/*else
					{
						mav = new ModelAndView("errorConfigshow");
						request.setAttribute("message", "Problem to read value of wards");
						
					}*/
					
					model.addAttribute("message", message);
					model.addAttribute("stateList", stateList);
					model.addAttribute("localGovtBodyForm", localGovtBodyForm);
					model.addAttribute("localbodyWardList", sortlocalbodyWardList);
					model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",sortlocalbodyWardList);
					model.addAttribute("wardsize", sortlocalbodyWardList.size());
					/*model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",localbodyWardList);
					model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
					model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) );
					model.addAttribute("counter",counter);*/
					/*if(offset==(counter/limit))
						movenext=false;
					else
						movenext=true;
					model.addAttribute("movenext",movenext);
					model.addAttribute("wardCount","Page "
					+ (Integer.parseInt(session.getAttribute("offset").toString())+1)
					+ " of "
					+ (Integer.parseInt(session.getAttribute("counter").toString())
					/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));*/
					
					if(sortlocalbodyWardList.size()<=0)
					{
						model.addAttribute("listnull","Wards not present in "+localBodyName.trim());
					}
					
					
					
					

				} else {

					
					mav = new ModelAndView("errorConfigshow");
					request.setAttribute("message", "state not found in list");

				}
				
				}
				else
				{
					mav.addObject("flag1", 1);
					model.addAttribute("captchaSuccess1", messageFlag);
					model.addAttribute("stateList", stateList);
				mav = new ModelAndView("viewWards");
				//result.rejectValue("captchaAnswer", "Error.MatchCaptcha");
				
				
				
				
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
		
		
	/*	@RequestMapping(value = "/viewWardPagination", method = RequestMethod.POST)
		public ModelAndView getWardPagination(
				@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,
				Model model, HttpServletRequest request, HttpSession httpSession) {
			
			ModelAndView mav = null;
			mav = new ModelAndView("viewWards");
			boolean movenext=true;
			int offset=Integer.parseInt(httpSession.getAttribute("offset").toString());
			int limit=Integer.parseInt(httpSession.getAttribute("limit").toString());
			int counter=Integer.parseInt(httpSession.getAttribute("counter").toString());
			int direction=localGovtBodyForm.getDirection();
			
			
			List<State> stateList=null;
			stateList=new ArrayList<State>(); 
			
			try
			{
			stateList= stateService.getStateSourceList();
			if(stateList.size()>0)
			{
			
			
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
			List<LocalbodyWard> localbodyWardList=new ArrayList<LocalbodyWard>();
			int lblc=Integer.parseInt(httpSession.getAttribute("lblc").toString());
			localbodyWardList=localGovtBodyService.getWardDetails(lblc,((offset*limit)+1),limit);
			if(offset==(counter/limit))
				movenext=false;
			else
				movenext=true;
			if(localbodyWardList.size()<=0)
			{
				offset=offset-(direction);
			}
			model.addAttribute("stateList", stateList);
			model.addAttribute("localGovtBodyForm", localGovtBodyForm);
			model.addAttribute("localbodyWardList", localbodyWardList);
			model.addAttribute("wardsize", localbodyWardList.size());
			model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",localbodyWardList);
			//villagebean.setStateWiseEntityDetails(stateWiseEntityDetails);
			model.addAttribute("offsets", offset);
			model.addAttribute("limits", Integer.parseInt(httpSession.getAttribute("limit").toString()));
			model.addAttribute("counter",counter);
			model.addAttribute("movenext",movenext);
			
			model.addAttribute("wardCount","Page "
					+ (offset+1)
					+ " of "
					+ ((counter
					/ limit )+ 1));
			}
			
			model.addAttribute("stateList", stateList);
			model.addAttribute("localGovtBodyForm", localGovtBodyForm);
			model.addAttribute("localbodyWardList", localbodyWardList);
			model.addAttribute("wardsize", localbodyWardList.size());
			model.addAttribute("SEARCH_SUBDISTRICT_RESULTS_KEY",localbodyWardList);
			model.addAttribute("limits", Integer.parseInt(session.getAttribute("limit").toString()));
			model.addAttribute("offsets",Integer.parseInt(session.getAttribute("offset").toString()) - 1);
			model.addAttribute("counter",counter);
			
			model.addAttribute("wardCount","Page "
			+ Integer.parseInt(session.getAttribute("offset").toString())
			+ " of "
			+ (Integer.parseInt(session.getAttribute("counter").toString())
			/ Integer.parseInt(session.getAttribute("limit").toString()) + 1));
			}
			catch(Exception e)
			{
				log.debug("Exception" + e);
			}
			return mav;
			
		}*/
		
		@RequestMapping(value = "/viewGovtOrder", method = RequestMethod.POST)
		public void fileDownload(HttpServletRequest request, HttpServletResponse response, Model model) {
			try {
				String filePath = request.getParameter("govfilePath");
				String fileDisplayType = request.getParameter("fileDisplayType");
				if(filePath != null && !"".equals(filePath)) {
					CommonUtil.fileDownload(filePath, response, fileDisplayType);
				}
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			}
		}
		
		//@RequestMapping(value = "/globalviewblockwiseVillageandUlbforcitizen", method = RequestMethod.GET)
		
		public ModelAndView showBlockWiseVillagesandULBPageglobal(Model model, HttpServletRequest request,
				@ModelAttribute("blockbean") BlockDataForm blockForm)
		{
			ModelAndView mav = new ModelAndView("globalview_blockwisevillagesandulb");
			
			try 
			{
				
				model.addAttribute("viewPage", "");
			} catch (Exception e) 
			{
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
		
		//@RequestMapping(value = "/globalviewblockwiseVillageandUlbforcitizen", method = RequestMethod.POST)
		public ModelAndView showBlockWiseVillagesandULBPageglobalPOST(Model model, HttpServletRequest request,HttpSession session,
		@ModelAttribute("blockbean") BlockDataForm blockForm)
		{
			ModelAndView mav = new ModelAndView("globalview_blockwisevillagesandulb");
			Integer blc=null,dlc=null;
			List<BlockWiseVillagesAndUlb> blockWiseEntityList=null;
			
			try 
			{
				boolean messageFlag=false;
				String captchaAnswer = blockForm.getCaptchaAnswers();
				CaptchaValidator captchaValidator = new CaptchaValidator();
				blockForm.setStateNameEnglish(null);
				if(captchaAnswer!=null){
						messageFlag = captchaValidator.validateCaptcha(session,captchaAnswer);
				}		
			
				blockForm.setCaptchaAnswers(null);
				
					if (messageFlag == true)
					{
				
						if(blockForm.getBlockNameEnglish()!=null){
							blc=Integer.parseInt(blockForm.getBlockNameEnglish());
						}	
						if(blc==null)
						{
							
							return mav;
						}
						
						if(blockForm.getDistrictNameEnglish()!=null)
						{
							dlc=Integer.parseInt(blockForm.getDistrictNameEnglish().trim());
						}
				
						if(dlc!=null)
						{
							Block block=new Block();
							block=blockService.getSingleBlockDetailsMaxVersion(blc);
							List<LandRegionDetail> landregionDetail=null;
							landregionDetail=new ArrayList<LandRegionDetail>();
							landregionDetail=reportService.landRegionDetail('D', dlc);
							if(landregionDetail.size()>0){
								model.addAttribute("message","Block Wise Villages or ULB of Block "+block.getBlockNameEnglish()+" District "+landregionDetail.get(0).getDistrictNameEnglish()+"("+landregionDetail.get(0).getStateNameEnglish()+")");
							}	
						}
						
						
						blockWiseEntityList=new ArrayList<BlockWiseVillagesAndUlb>();
						blockWiseEntityList=blockService.getBlockWiseVillagesAndUlbDetail(blc);
						model.addAttribute("blockWiseEntityList", blockWiseEntityList);
						model.addAttribute("size",blockWiseEntityList.size());
						
					}
					else
					{
						mav.addObject("flag1", 1);
						model.addAttribute("captchaSuccess1", messageFlag);
					
				
					}
				
			
			} catch (Exception e) 
			{
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
		
		@RequestMapping(value = "/listOfPesaPanchyat", method = RequestMethod.GET)
		public ModelAndView listOfPesaLb(Model model, HttpServletRequest request) {
			ModelAndView mav = null;
		
			try {
				List<State> statesList = new ArrayList<State>();
				mav = new ModelAndView("globalview_pesalocalbody");
				model.addAttribute("localGovtBodyForm",new LocalGovtBodyForm());
				statesList = localGovtBodyService.getStateWisePesa();
				if (statesList.size() == 0) {
					
					mav = new ModelAndView("globalview_pesalocalbody");
					model.addAttribute("flag", 1);
					model.addAttribute("dataflag", 0);
					
				}
				model.addAttribute("statesList",statesList);
				model.addAttribute("flag", 1);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
				return mav;
			}
			return mav;
		}
		/**Change by maneesh*/
	@RequestMapping(value = "/listOfPesaPanchyat", method = RequestMethod.POST)
	public ModelAndView listOfPesaLbPost(
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = null;
		String message=null;
		try {
			List<State> statesList = new ArrayList<State>();
			int slc = localGovtBodyForm.getSlc();
			//int parentlblc = Integer.parseInt(localGovtBodyForm.getParentLBCode());
			boolean messageFlag = false;
			String captchaAnswer = localGovtBodyForm.getCaptchaAnswer();
			CaptchaValidator captchaValidator = new CaptchaValidator();
			if (captchaAnswer != null) {
				messageFlag = captchaValidator.validateCaptcha(session,	captchaAnswer);
			}
			mav = new ModelAndView("globalview_pesalocalbody");
			localGovtBodyForm.setCaptchaAnswer(null);
			List<StatewisePesaPanchyat>  statewisePesaPanchyatList= new ArrayList<StatewisePesaPanchyat>();
			if (messageFlag == true) {
			/*if (true) {*/

				statewisePesaPanchyatList = localGovtBodyService.getactiveLbPesa(slc);
				//message = stateDao.getLBHierarchy(parentlblc);
				if (statewisePesaPanchyatList.size() > 0) {

					model.addAttribute("localGovtBodyForm", localGovtBodyForm);
					model.addAttribute("getLbPesa", statewisePesaPanchyatList);
					model.addAttribute("flag", 2);
					model.addAttribute("message", message);
					session.setAttribute("slc",localGovtBodyForm.getSlc());
					session.setAttribute("coordinates",localGovtBodyForm.getCoordinates());

				} else {
					mav = new ModelAndView("globalview_pesalocalbody");
					statesList = localGovtBodyService.getStateWisePesa();
					model.addAttribute("statesList",statesList);
					model.addAttribute("flag", 1);
					model.addAttribute("dataflag", 0);

				}

			} else {
				model.addAttribute("localGovtBodyForm",new LocalGovtBodyForm());
				statesList = localGovtBodyService.getStateWisePesa();
				model.addAttribute("statesList",statesList);
				model.addAttribute("captchaSuccess", messageFlag);
				model.addAttribute("getLbPesa", statewisePesaPanchyatList);
			}
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}	
	
	/**Change by maneesh
		**/
	@RequestMapping(value = "/PrintPesaReport", method = RequestMethod.GET)
	public String PesaPrint(
			@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm,Model model, HttpServletRequest request, HttpSession session) {
		//ModelAndView mav = null;
		
		try {
			int statecode = (Integer)(session.getAttribute("slc"));
			String stateName = (String)session.getAttribute("coordinates");
			List<StatewisePesaPanchyat> statewisePesaPanchyatList = new ArrayList<StatewisePesaPanchyat>();
			statewisePesaPanchyatList = localGovtBodyService.getactiveLbPesa(statecode);
			model.addAttribute("stateName",stateName);
			model.addAttribute("getLbPesa",statewisePesaPanchyatList);
			
			return "govtbody/PrintPesaReport";
			
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			//mav = new ModelAndView(redirectPath);
			return "govtbody/PrintPesaReport";
		}
	}	
	
	@RequestMapping(value = "/copyRightPolicy", method = RequestMethod.GET)
	public ModelAndView showContentPolicy(Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			mav = new ModelAndView("copyrightpolicy");
			
			return mav;
		}
		catch (Exception e) 
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	@RequestMapping(value = "/privacyPolicy", method = RequestMethod.GET)
	public ModelAndView showPrivacyPolicy(Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			mav = new ModelAndView("privacyPolicy");
			
			return mav;
		}
		catch (Exception e) 
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}
	
	@RequestMapping(value = "/termsconditions", method = RequestMethod.GET)
	public ModelAndView showTerm(Model model, HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			mav = new ModelAndView("termsconditions");
			
			return mav;
		}
		catch (Exception e) 
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;	
		}
	}
}