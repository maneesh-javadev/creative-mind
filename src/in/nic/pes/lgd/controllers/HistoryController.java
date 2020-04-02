package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.DistrictHistory;
import in.nic.pes.lgd.bean.LocalBodyHistory;
import in.nic.pes.lgd.bean.StateHistory;
import in.nic.pes.lgd.bean.SubdistrictHistory;
import in.nic.pes.lgd.bean.VillageHistory;
import in.nic.pes.lgd.constant.CommanConstant;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.ReportService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.CommonUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

/**
 * @Author Anju Gupta added on 25 Feb 2015 for State History
 * 
 */

@Controller
public class HistoryController { // NO_UCD (unused code)

	@Autowired
	private StateService stateService;

	@Autowired
	DistrictService districtService;

	@Autowired
	SubDistrictService subDistrictService;

	@Autowired
	private VillageService villageService;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	ReportService reportService;

	/***
	 * @author Anju Gupta
	 * @since 25Feb2015
	 * @param stateForm
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewStateHistoryReport")
	public ModelAndView viewStateVersionDetail(@ModelAttribute("stateForm") StateForm stateForm, HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView mav = null;
		// String id = stateForm.getGlobalstateId().toString();
		Integer stateCode = (stateForm.getStateId() == null) ? stateForm.getGlobalstateId() : stateForm.getStateId();
		try {
			mav = new ModelAndView("globalViewStateHistory");
			char stateNameEnglish = 'S';
			List<StateHistory> stateHistoryDetail = stateService.getStateHistoryDetail(stateNameEnglish, stateCode);
			model.addAttribute("stateHistoryDetail", stateHistoryDetail);
			stateForm.setStateHistoryDetail(stateHistoryDetail);
			mav.addObject("stateForm", stateForm);
			model.addAttribute("viewPage", "abc");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/**
	 * @Author Pooja Sharma added on 3 March 2015 for District History Report
	 * 
	 */

	@RequestMapping(value = "/viewDistrictHistoryReport")
	public ModelAndView viewDistrictHistoryReport(@ModelAttribute("districtbean") DistrictForm districtbean, HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView mav = null;
		Integer districtCode = districtbean.getGlobaldistrictId();
		try {
			mav = new ModelAndView("viewGlobalDistrictHistory");
			char districtNameEnglish = 'D';
			List<DistrictHistory> listDistrictDetail = districtService.getDistrictHistoryReport(districtNameEnglish, districtCode);
			model.addAttribute("listDistrictDetail", listDistrictDetail);
			districtbean.setDistrictHistoryDetail(listDistrictDetail);
			mav.addObject("districtbean", districtbean);
			model.addAttribute("viewPage", "abc");
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
	 * @author Maneesh Kumar
	 * @param request
	 * @param model
	 * @param Change
	 *            in Method
	 * @return
	 */
	@RequestMapping(value = "/fileToDownloads", method = RequestMethod.POST)
	public void reportFileDownloads(HttpServletRequest request, HttpServletResponse response, Model model) {
		String fileLocation = "", fileName = "";
		// String id = stateForm.getGlobalstateId().toString();

		if ((request.getParameter("fileLocation") != null && request.getParameter("fileLocation") != "") && (request.getParameter("timeStatmp") != null && request.getParameter("timeStatmp") != "")) {
			fileLocation = request.getParameter("fileLocation");
			fileName = request.getParameter("timeStatmp");
		}

		try {
			CommonUtil.getFileDownload(fileLocation, fileName, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause().getMessage();
		}
	}

	@RequestMapping(value = "/viewSubDistrictHistoryReport")
	public ModelAndView viewSubDistrictVersionDetail(@ModelAttribute("subDistrictForm") SubDistrictForm subDistrictForm, HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView mav = null;
		Integer subdistrictcode = subDistrictForm.getGlobalsubdistrictId();
		try {
			mav = new ModelAndView("globalViewSubDistrictHistory");
			char stateNameEnglish = 'T';
			List<SubdistrictHistory> subDistrictHistoryDetail = subDistrictService.getSubDistHistoryReport(stateNameEnglish, subdistrictcode);
			model.addAttribute("subDistrictHistoryDetail", subDistrictHistoryDetail);
			subDistrictForm.setSubDistrictHistoryDetail(subDistrictHistoryDetail);
			mav.addObject("subDistrictForm", subDistrictForm);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/**
	 * Localbody History show in Report no 8 
	 * @author Maneesh Kumar 06Apr2016
	 * @param lbCode
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewLocalBodyHistoryReports")
	public ModelAndView viewLocalBodyVersionDetail(@RequestParam("lbCode") Integer lbCode, HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView("globalViewLocalBodyHistory");
		try {
			model.addAttribute("localBodyHistoryDetail", reportService.getLocalBodyHistoryReport(CommanConstant.GOVERMENT_ORDER_ENTITY_TYPE_LOCALBODY.toString().charAt(0), lbCode));
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
}
		return mav;

	}

	/*
	 * @author Anju Gupta added on 4-03-2015 for View Village History
	 * 
	 */

	@RequestMapping(value = "/viewVillageHistoryReport")
	public ModelAndView viewVillageVersionDetail(@ModelAttribute("villageForm") VillageForm villageForm, HttpServletRequest request, Model model, HttpSession session) {
		ModelAndView mav = null;
		// String id = villageForm.getGlobalvillageId().toString();
		Integer villageCode = (villageForm.getVillageId() == null) ? villageForm.getGlobalvillageId() : villageForm.getVillageId();
		try {
			mav = new ModelAndView("globalViewVillageHistory");
			char villageNameEnglish = 'V';
			List<VillageHistory> villageHistoryDetail = villageService.getVillageHistoryDetail(villageNameEnglish, villageCode);
			model.addAttribute("villageHistoryDetail", villageHistoryDetail);
			mav.addObject("villageForm", villageForm);
			model.addAttribute("viewPage", "abc");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}
}
