package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.Operations;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.forms.GovtOrderTemplateForm;
import in.nic.pes.lgd.service.ComboFillingService;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.GovtOrderTemplateService;
import in.nic.pes.lgd.utils.CurrentDateTime;
import in.nic.pes.lgd.validator.TemplateValidator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

@Controller
public class GovtOrderTemplateController { // NO_UCD (unused code)

	@Autowired
	GovtOrderTemplateService govtOrderTemplateService;
	@Autowired
	private GovernmentOrderService govtOrderService;
	@Autowired
	private TemplateValidator templateValidator;
	
	@Autowired
	ComboFillingService comboFillingService;
	
	@Autowired
	CommonService commonservice;
	
	
	private Integer stateCode;

	private Integer districtCode;

	private Long userId;

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
	}
	
	

	@RequestMapping(value = "/generateGovtTemplate", method = RequestMethod.GET)
	public ModelAndView generateGovtTemplate(
			@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,
			HttpSession session,
			@RequestParam(value = "mode", required = false) String value,
			@RequestParam(value = "tc", required = false) String tc,
			HttpServletRequest request) {
		// UC-50
		List<Operations> oprList = null;
		ModelAndView mav;
		try {
			mav = null;
			char category=govtOrderTemplateForm.getCategory();
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);
			mav = new ModelAndView("generateGovtTemplate");
			mav.addObject("oprList", oprList);
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

	/*@RequestMapping(value = "/createGovtTemplate", method = RequestMethod.GET)
	public ModelAndView createGovtTemplateView(
			@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,
			HttpSession session,
			@RequestParam(value = "mode", required = true) String value,
			@RequestParam(value = "tc", required = false) String tc,
			HttpServletRequest request) {
		// UC-50
		ModelAndView mav = null;
		try {
			List<Operations> oprList =  new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOpeartionsList();
			if (value.indexOf("editmode") != -1) {
				int templCode = 0;
				if (tc != null || tc != "") {
					templCode = Integer.parseInt(tc);
				}
				session.setAttribute("templCode", templCode);
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();

				templateList = govtOrderTemplateService
						.getTemplateDetailsByTemplateCode(templCode);
				if (templateList.size() == 1) {
					govtOrderTemplateForm.setTemplateBodySrc(templateList
							.get(0).getTemplateRegional());
					govtOrderTemplateForm.setOperations(templateList.get(0)
							.getOperations().toString());
					String oType = Character.toString(templateList.get(0)
							.getTemplateType());
					govtOrderTemplateForm.setOrderType(oType);
					if (templateList.get(0).getTemplateDescription() != null) {
						govtOrderTemplateForm.setTemplateBodySrc(templateList
								.get(0).getTemplateDescription());
						govtOrderTemplateForm.setTemplateLanguage("English");
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						govtOrderTemplateForm.setTemplateBodySrc(templateList
								.get(0).getTemplateRegional());
						govtOrderTemplateForm.setTemplateLanguage("Local");
					}
				}
				govtOrderTemplateForm.setTemplateCode(templCode);
				govtOrderTemplateForm.setTemplateNameEnglish(templateList
						.get(0).getTemplateNameEnglish());

				mav = new ModelAndView("createGovtTemplate");
				mav.addObject("oprList", oprList);
				mav.addObject(govtOrderTemplateForm);
				mav.addObject("editMode", "editMode");
			} else if (value.indexOf("createmode") != -1) {
				mav = new ModelAndView("createGovtTemplate");
				mav.addObject("oprList", oprList);
				mav.addObject(govtOrderTemplateForm);
				mav.addObject("createMode", "createMode");
			} else {
				String message = "Unknown ULR...";
				mav = new ModelAndView("error");
				mav.addObject("message", message);
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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
	
	@RequestMapping(value = "/createGovtTemplatePri", method = RequestMethod.GET)
	public ModelAndView createGovtTemplateViewPri(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,Model model,HttpSession session,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			char category='P';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);

			mav = new ModelAndView("createGovtTemplate");
			mav.addObject("oprList", oprList);
			model.addAttribute("category", "P");
			mav.addObject(govtOrderTemplateForm);			 
		} 
		catch (NumberFormatException e)
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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
	
	@RequestMapping(value = "/createGovtTemplateUrban", method = RequestMethod.GET)
	public ModelAndView createGovtTemplateViewUrban(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,Model model,HttpSession session,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			char category='U';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);

			mav = new ModelAndView("createGovtTemplate");
			mav.addObject("oprList", oprList);
			model.addAttribute("category", "U");
			mav.addObject(govtOrderTemplateForm);			 
		} 
		catch (NumberFormatException e)
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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
	
	@RequestMapping(value = "/createGovtTemplateTra", method = RequestMethod.GET)
	public ModelAndView createGovtTemplateViewTrad(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,Model model,HttpSession session,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			char category='T';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);

			mav = new ModelAndView("createGovtTemplate");
			mav.addObject("oprList", oprList);
			model.addAttribute("category", "T");
			mav.addObject(govtOrderTemplateForm);			 
		} 
		catch (NumberFormatException e)
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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
	
	@RequestMapping(value = "/createGovtTemplateBlk", method = RequestMethod.GET)
	public ModelAndView createGovtTemplateViewBlk(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,Model model,HttpSession session,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			char category='B';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);

			mav = new ModelAndView("createGovtTemplate");
			mav.addObject("oprList", oprList);
			model.addAttribute("category", "B");
			mav.addObject(govtOrderTemplateForm);			 
		} 
		catch (NumberFormatException e)
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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
	
	@RequestMapping(value = "/createGovtTemplateCenter", method = RequestMethod.GET)
	public ModelAndView createGovtTemplateViewCenter(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,Model model,HttpSession session,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			char category='C';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);

			mav = new ModelAndView("createGovtTemplate");
			mav.addObject("oprList", oprList);
			model.addAttribute("category", "C");
			mav.addObject(govtOrderTemplateForm);			 
		} 
		catch (NumberFormatException e)
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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
	
	@RequestMapping(value = "/createGovtTemplateLR", method = RequestMethod.GET)
	public ModelAndView createGovtTemplateViewLR(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,Model model,HttpSession session,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try
		{
			char category='L';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);

			mav = new ModelAndView("createGovtTemplate");
			mav.addObject("oprList", oprList);
			model.addAttribute("category", "L");
			mav.addObject(govtOrderTemplateForm);			 
		} 
		catch (NumberFormatException e)
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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

	@RequestMapping(value = "/modifyGovtTemplate")
	public ModelAndView modifyGovtTemplateView(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,BindingResult result,Model model,HttpSession session,HttpServletRequest request) 
	{
		ModelAndView mav = null;
		int templCode=govtOrderTemplateForm.getTemplateId();
		
		char category=govtOrderTemplateForm.getCategory();
	/*	String cat=(String)request.getAttribute("category");
		char category = cat.charAt(0);   */ 
		
		try {
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);

			/*if (tc != null || tc != "") {
				templCode = Integer.parseInt(tc);
			}*/
			session.setAttribute("templCode", templCode);
			List<GovernmentOrderTemplate> templateList = null;
			templateList = new ArrayList<GovernmentOrderTemplate>();

			templateList = govtOrderTemplateService
					.getTemplateDetailsByTemplateCode(templCode);
			if (templateList.size() == 1) {
				govtOrderTemplateForm.setTemplateBodySrc(templateList.get(0)
						.getTemplateRegional());
				govtOrderTemplateForm.setOperations(templateList.get(0)
						.getOperations().toString());
				String oType = Character.toString(templateList.get(0)
						.getTemplateType());
				govtOrderTemplateForm.setOrderType(oType);
				if (templateList.get(0).getTemplateDescription() != null) {
					govtOrderTemplateForm.setTemplateBodySrc(templateList
							.get(0).getTemplateDescription());
					govtOrderTemplateForm.setTemplateLanguage("English");
				}
				if (templateList.get(0).getTemplateRegional() != null) {
					govtOrderTemplateForm.setTemplateBodySrc(templateList
							.get(0).getTemplateRegional());
					govtOrderTemplateForm.setTemplateLanguage("Local");
				}
			}
			govtOrderTemplateForm.setTemplateCode(templCode);
			govtOrderTemplateForm.setTemplateNameEnglish(templateList.get(0)
					.getTemplateNameEnglish());

			mav = new ModelAndView("modifyGovtTemplate");
			mav.addObject("oprList", oprList);
			mav.addObject(govtOrderTemplateForm);
		 

		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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

	@RequestMapping(value = "/viewtemplate")
	public ModelAndView viewtemplate(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,BindingResult result, Model model,HttpSession session,HttpServletRequest request)
	{
		ModelAndView mav = null;
		//int tc=govtOrderTemplateForm.getTemplateId();
		String id = request.getParameter("tc");
		int tc=(govtOrderTemplateForm.getTemplateId()==null)?Integer.parseInt(id):govtOrderTemplateForm.getTemplateId();
		try {
			//int templCode = Integer.parseInt(value);
		
			List<GovernmentOrderTemplate> templateList = null;
			templateList = new ArrayList<GovernmentOrderTemplate>();

			templateList = govtOrderTemplateService
					.getTemplateDetailsByTemplateCode(tc);
			if (templateList != null) {
				if (templateList.size() == 1) {
					govtOrderTemplateForm.setTemplateBodySrc(templateList
							.get(0).getTemplateRegional());
					govtOrderTemplateForm.setOperations(templateList.get(0)
							.getOperations().toString());
					String oType = Character.toString(templateList.get(0)
							.getTemplateType());

					if (oType.equalsIgnoreCase("A")) {
						oType = "Addendum";
						govtOrderTemplateForm.setOrderType(oType);
					}
					if (oType.equalsIgnoreCase("C")) {
						oType = "Corrigendum";
						govtOrderTemplateForm.setOrderType(oType);
					}
					if (oType.equalsIgnoreCase("O")) {
						oType = "Government Order";
						govtOrderTemplateForm.setOrderType(oType);
					}
					if (templateList.get(0).getTemplateDescription() != null) {
						govtOrderTemplateForm.setTemplateBodySrc(templateList
								.get(0).getTemplateDescription());
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						govtOrderTemplateForm.setTemplateBodySrc(templateList
								.get(0).getTemplateRegional());
					}
				}
			}
			if(templateList.get(0) != null){
				govtOrderTemplateForm.setTemplateNameEnglish(templateList.get(0).getTemplateNameEnglish());
			}	
			mav = new ModelAndView("viewtemplate");
			mav.addObject(govtOrderTemplateForm);
			mav.addObject("templateList", templateList);
			
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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

	@RequestMapping(value = "/deletetemplate")
	public ModelAndView deletetemplate(
			@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,BindingResult result,Model model,HttpSession session,HttpServletRequest request) {
		// UC-050.2
		ModelAndView mav = null;
		int templCode=govtOrderTemplateForm.getTemplateId();
		try {
			/*int templCode = Integer.parseInt(value);*/
			boolean success = false;
			
			comboFillingService.getComboValuesforApp('X', null, null, Integer.parseInt(govtOrderTemplateForm.getOperations()));
			
			/*List<GovernmentOrderTemplate> templateList = null;
			templateList = new ArrayList<GovernmentOrderTemplate>();*/

			success = govtOrderTemplateService.invalidateTemplate(templCode);
			if (success) {
				mav = new ModelAndView("success");
				mav.addObject("msgid", "Template Deleted ");
			} else {
				mav = new ModelAndView("error");
				mav.addObject("message",
						"Server Error Please Contact Administrator ");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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

	@RequestMapping(value = "/viewListOfTemplatesPri", method = RequestMethod.GET)
	public ModelAndView ViewListOfTemplatesPri(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,HttpSession session,Model model,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try 
		{
			char category='P';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);
			if (oprList != null) 
			{
				mav = new ModelAndView("viewTemplateList");
				model.addAttribute("category",category);
				mav.addObject("oprList", oprList);
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
	
	@RequestMapping(value = "/viewListOfTemplatesUrban", method = RequestMethod.GET)
	public ModelAndView ViewListOfTemplatesUrban(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,HttpSession session,Model model,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try 
		{
			char category='U';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);
			if (oprList != null) 
			{
				mav = new ModelAndView("viewTemplateList");
				model.addAttribute("category",category);
				mav.addObject("oprList", oprList);
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
	
	@RequestMapping(value = "/viewListOfTemplatesTra", method = RequestMethod.GET)
	public ModelAndView ViewListOfTemplatesTra(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,HttpSession session,Model model,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try 
		{
			char category='T';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);
			if (oprList != null) 
			{
				mav = new ModelAndView("viewTemplateList");
				model.addAttribute("category",category);
				mav.addObject("oprList", oprList);
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
	
	@RequestMapping(value = "/viewListOfTemplatesBlk", method = RequestMethod.GET)
	public ModelAndView ViewListOfTemplatesBlk(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,HttpSession session,Model model,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try 
		{
			char category='B';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);
			if (oprList != null) 
			{
				mav = new ModelAndView("viewTemplateList");
				model.addAttribute("category",category);
				mav.addObject("oprList", oprList);
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
	
	@RequestMapping(value = "/viewListOfTemplatesCenter", method = RequestMethod.GET)
	public ModelAndView ViewListOfTemplatesCenter(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,HttpSession session,Model model,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try 
		{
			char category='C';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);
			if (oprList != null) 
			{
				mav = new ModelAndView("viewTemplateList");
				//mav.addObject("category",category);
				model.addAttribute("category",category);
				mav.addObject("oprList", oprList);
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
	
	@RequestMapping(value = "/viewListOfTemplatesLR", method = RequestMethod.GET)
	public ModelAndView ViewListOfTemplatesLR(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,HttpSession session,Model model,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try 
		{
			char category='L';
			govtOrderTemplateForm.setCategory(category);
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);
			if (oprList != null) 
			{
				mav = new ModelAndView("viewTemplateList");
				model.addAttribute("category",category);
				mav.addObject("oprList", oprList);
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
	

	@RequestMapping(value = "/createTemplate", method = RequestMethod.POST)
	public ModelAndView createGovtTemplate(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,BindingResult result,Model model,HttpSession httpSession,HttpServletRequest request)
	{
		ModelAndView mav = null;
		try 
		{
			
			int slc=0;
			char category=govtOrderTemplateForm.getCategory();
			
			//String path=request.getRealPath("/WEB-INF/classes");
			//Policy policy = new org.owasp.esapi.antisamy.util.ESAPIUtil().loadPolicy(path+"/antisamy-tinymce-1.4.4.xml");
			//AntiSamy sanitizer = new AntiSamy();
			
			//Code commented to implement the image inserting in the pdf file-----by Arnab on 19/03/2013
			//comboFillingService.getComboValuesforApp('X', null, null, Integer.parseInt(govtOrderTemplateForm.getOperations()));			
			//CleanResults results = sanitizer.scan(govtOrderTemplateForm.getTemplateBodySrc(), policy);
			//String safeMarkup = results.getCleanHTML();
			//govtOrderTemplateForm.setTemplateBodySrc(safeMarkup);
			
			int templateCode = 0;
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			
		 	templateValidator.createTemplateValidation(govtOrderTemplateForm,result);
			if (result.hasErrors())
			{
				mav = new ModelAndView("createGovtTemplate");
				oprList = govtOrderTemplateService.getOperationsList(category);
				mav.addObject("oprList", oprList);
				model.addAttribute("category",category);
				mav.addObject(govtOrderTemplateForm);
				mav.addObject("editMode", "editMode");

			}
			else 
			{ 
				if (govtOrderTemplateForm.getTemplateBodySrc().toUpperCase()
						.contains("SCRIPT")) {
					String replacedAlert = govtOrderTemplateForm
							.getTemplateBodySrc().toUpperCase()
							.replaceAll("SCRIPT", " ");
					govtOrderTemplateForm.setTemplateBodySrc(replacedAlert);
				}
				templateCode = govtOrderTemplateService.createTemplate(
						govtOrderTemplateForm, stateCode, userId ,slc);
				mav = new ModelAndView("redirect:viewtemplate.htm?tc="
						+ templateCode + "");
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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

	@RequestMapping(value = "/updateTemplate", method = RequestMethod.POST)
	public ModelAndView updateTemplate(
			@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,
			BindingResult result, HttpSession httpSession,
			HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			
			comboFillingService.getComboValuesforApp('X', null, null, Integer.parseInt(govtOrderTemplateForm.getOperations()));
			char category = govtOrderTemplateForm.getCategory();
			boolean success = false;
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			
			//String path=request.getRealPath("/WEB-INF/classes");
			//Policy policy = new org.owasp.esapi.antisamy.util.ESAPIUtil().loadPolicy(path+"/antisamy-tinymce-1.4.4.xml");
			//AntiSamy sanitizer = new AntiSamy();
			
			//Code commented to implement the image inserting in the pdf file-----by Arnab on 19/03/2013
			/*CleanResults results = sanitizer.scan(govtOrderTemplateForm.getTemplateBodySrc(), policy);
			String safeMarkup = results.getCleanHTML();
			govtOrderTemplateForm.setTemplateBodySrc(safeMarkup);*/
			
			templateValidator.updateTemplateValidation(govtOrderTemplateForm,result);

			if (result.hasErrors()) {
				oprList = govtOrderTemplateService.getOperationsList(category);
				mav = new ModelAndView("modifyGovtTemplate");
				mav.addObject("oprList", oprList);
				mav.addObject(govtOrderTemplateForm);
				//mav.addObject("editMode", "editMode");
			} else {
				if (govtOrderTemplateForm != null) {
					success = govtOrderTemplateService.updateTemplate(
							govtOrderTemplateForm, stateCode, userId);
					if (success) {
						mav = new ModelAndView("redirect:viewtemplate.htm?tc="
								+ govtOrderTemplateForm.getTemplateCode() + "");
					} else {
						String message = "Unknown Server Error...";
						mav = new ModelAndView("error");
						mav.addObject("message", message);
					}
				}
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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

	@RequestMapping(value = "/listTemplate", method = RequestMethod.POST)
	public ModelAndView listTemplate(@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,BindingResult result,Model model,HttpSession session, HttpServletRequest request) 
	{
		ModelAndView mav = null;
		List<Operations> oprList = new ArrayList<Operations>();
		try
		{
			char category=govtOrderTemplateForm.getCategory();
			govtOrderTemplateForm.setCategory(category);
			if(Integer.parseInt(govtOrderTemplateForm.getOperations()) !=0)
			{	
				comboFillingService.getComboValuesforApp('X', null, null, Integer.parseInt(govtOrderTemplateForm.getOperations()));	
			}
			List<GovernmentOrderTemplate> templateList = new ArrayList<GovernmentOrderTemplate>();
			templateValidator.listTemplateValidation(govtOrderTemplateForm,	result);
 			if (result.hasErrors()) 
			{
				oprList = govtOrderTemplateService.getOperationsList(category);
				mav = new ModelAndView("viewTemplateList");
				mav.addObject("oprList", oprList);
				model.addAttribute("category",category);
				mav.addObject(govtOrderTemplateForm);
				mav.addObject("editMode", "editMode");
			}
			else
			{
				int oprCode = Integer.parseInt(govtOrderTemplateForm.getOperations());
		
				oprList = govtOrderTemplateService.getOperationsList(category);
				templateList = govtOrderTemplateService.getTemplateListByOperationCode(oprCode);
				mav = new ModelAndView("viewTemplateList");
				govtOrderTemplateForm.setOperations(govtOrderTemplateForm.getOperations());
				mav.addObject("oprList", oprList);
				model.addAttribute("category",category);
				mav.addObject("templateList", templateList);
				mav.addObject("templateSize", templateList.size());
				mav.addObject(govtOrderTemplateForm);
			}
		}
		catch (NumberFormatException e) 
		{
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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
	
	@RequestMapping(value = "/govtordertemplatepreviewupdate", method = RequestMethod.POST)
	public ModelAndView createGovtTemplatePreviewUpdate(
			@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,
			BindingResult result, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = null;
		List<Operations> oprList =  new ArrayList<Operations>();
		int templCode=govtOrderTemplateForm.getTemplateCode();

		try {

			int oprCode = 0;
			char category= govtOrderTemplateForm.getCategory();
			//String path=request.getRealPath("/WEB-INF/classes");
			//Policy policy = new org.owasp.esapi.antisamy.util.ESAPIUtil().loadPolicy(path+"/antisamy-tinymce-1.4.4.xml");
			//AntiSamy sanitizer = new AntiSamy();
			
			//Code commented to implement the image inserting in the pdf file-----by Arnab on 19/03/2013
			/*CleanResults results = sanitizer.scan(govtOrderTemplateForm.getTemplateBodySrc(), policy);
			String safeMarkup = results.getCleanHTML();
			govtOrderTemplateForm.setTemplateBodySrc(safeMarkup);*/
			session.setAttribute("templCode", templCode);
			templateValidator.previewTemplateValidation(govtOrderTemplateForm,
					result);
			
			if (result.hasErrors()) {			
				mav = new ModelAndView("createGovtTemplate");				
				oprList = govtOrderTemplateService.getOperationsList(category);
				mav.addObject("oprList", oprList);
				mav.addObject(govtOrderTemplateForm);
				mav.addObject("editMode", "editMode");

			} else {
				mav = new ModelAndView("govtOrderTemplatePreviewUpdate");
				if (govtOrderTemplateForm.getOperations() != null) {
					oprCode = Integer.parseInt(govtOrderTemplateForm
							.getOperations());
					String operationName = null;
					operationName = govtOrderTemplateService
							.getOperationNamebyOperationCode(oprCode);
					govtOrderTemplateForm.setOperationName(operationName);
					String tempSrc = govtOrderTemplateForm.getTemplateBodySrc() ;
					
				 
					if (tempSrc.contains("NewVillageName")) {
						tempSrc = tempSrc.replace("NewVillageName",
								"NewVillageName");
					}
					String oType = govtOrderTemplateForm.getOrderType();

					if (oType.equalsIgnoreCase("A")) {
						oType = "Addendum";
						govtOrderTemplateForm.setOrderType(oType);
					}
					if (oType.equalsIgnoreCase("C")) {
						oType = "Corrigendum";
						govtOrderTemplateForm.setOrderType(oType);
					}
					if (oType.equalsIgnoreCase("O")) {
						oType = "Government Order";
						govtOrderTemplateForm.setOrderType(oType);
					}

					govtOrderTemplateForm.setTemplateCode(templCode);
					govtOrderTemplateForm.setTemplateBodySrc(tempSrc);
					mav.addObject(govtOrderTemplateForm);
				} else {
					String message = "Unknown Operation...";
					mav = new ModelAndView("error");
					mav.addObject("message", message);
				}
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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
	
	@RequestMapping(value = "/govtordertemplatepreview", method = RequestMethod.POST)
	public ModelAndView createGovtTemplatePreview(
			@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,
			BindingResult result, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = null;
		List<Operations> oprList =  new ArrayList<Operations>();
		try {

			int oprCode = 0;
			char category= govtOrderTemplateForm.getCategory();
			//String path=request.getRealPath("/WEB-INF/classes");
			//Policy policy = new org.owasp.esapi.antisamy.util.ESAPIUtil().loadPolicy(path+"/antisamy-tinymce-1.4.4.xml");
			//AntiSamy sanitizer = new AntiSamy();
			
			//Code commented to implement the image inserting in the pdf file-----by Arnab on 19/03/2013
			/*CleanResults results = sanitizer.scan(govtOrderTemplateForm.getTemplateBodySrc(), policy);
			String safeMarkup = results.getCleanHTML();
			govtOrderTemplateForm.setTemplateBodySrc(safeMarkup);*/
			
			templateValidator.previewTemplateValidation(govtOrderTemplateForm,
					result);
			
			if (result.hasErrors()) {			
				mav = new ModelAndView("createGovtTemplate");				
				oprList = govtOrderTemplateService.getOperationsList(category);
				mav.addObject("oprList", oprList);
				mav.addObject(govtOrderTemplateForm);
				mav.addObject("editMode", "editMode");

			} else {
				mav = new ModelAndView("govtOrderTemplatePreview");
				if (govtOrderTemplateForm.getOperations() != null) {
					oprCode = Integer.parseInt(govtOrderTemplateForm
							.getOperations());
					String operationName = null;
					operationName = govtOrderTemplateService
							.getOperationNamebyOperationCode(oprCode);
					govtOrderTemplateForm.setOperationName(operationName);
					String tempSrc = govtOrderTemplateForm.getTemplateBodySrc() ;
					
				 
					if (tempSrc.contains("NewVillageName")) {
						tempSrc = tempSrc.replace("NewVillageName",
								"NewVillageName");
					}
					String oType = govtOrderTemplateForm.getOrderType();

					if (oType.equalsIgnoreCase("A")) {
						oType = "Addendum";
						govtOrderTemplateForm.setOrderType(oType);
					}
					if (oType.equalsIgnoreCase("C")) {
						oType = "Corrigendum";
						govtOrderTemplateForm.setOrderType(oType);
					}
					if (oType.equalsIgnoreCase("O")) {
						oType = "Government Order";
						govtOrderTemplateForm.setOrderType(oType);
					}

					
					
					govtOrderTemplateForm.setTemplateBodySrc(tempSrc);
					mav.addObject(govtOrderTemplateForm);
				} else {
					String message = "Unknown Operation...";
					mav = new ModelAndView("error");
					mav.addObject("message", message);
				}
			}
		} catch (NumberFormatException e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory
					.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(
					request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
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

	@RequestMapping(value = "/createGovtTemplateAfterPreview", method = RequestMethod.POST)
	public ModelAndView createGovtTemplateAfterPreview(
			@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,
			HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			//boolean success = false;
			char category=govtOrderTemplateForm.getCategory();
			List<Operations> oprList = null;
			oprList = new ArrayList<Operations>();
			oprList = govtOrderTemplateService.getOperationsList(category);
			
			//String path=request.getRealPath("/WEB-INF/classes");
			//Policy policy = new org.owasp.esapi.antisamy.util.ESAPIUtil().loadPolicy(path+"/antisamy-tinymce-1.4.4.xml");
			//AntiSamy sanitizer = new AntiSamy();
			
			//Code commented to implement the image inserting in the pdf file-----by Arnab on 19/03/2013
			/*CleanResults results = sanitizer.scan(govtOrderTemplateForm.getTemplateBodySrc(), policy);
			String safeMarkup = results.getCleanHTML();
			govtOrderTemplateForm.setTemplateBodySrc(safeMarkup);*/
		
			mav = new ModelAndView("createGovtTemplate");
			mav.addObject(govtOrderTemplateForm);
			mav.addObject("oprList", oprList);
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

	@RequestMapping(value = "/generateDraftTemplate", method = RequestMethod.POST)
	public ModelAndView generateDraftTemplate(
			@ModelAttribute("governmentOrderTemplateForm") GovtOrderTemplateForm govtOrderTemplateForm,
			HttpSession session, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			//boolean success = false;
			//char category=govtOrderTemplateForm.getCategory();
			Timestamp time = null;
			time = CurrentDateTime.getCurrentTimestamp();
			//List<Operations> oprList = null;
			//oprList = new ArrayList<Operations>();
			//oprList = govtOrderTemplateService.getOperationsList(category);
			List<GovernmentOrder> goList = null;
			goList = new ArrayList<GovernmentOrder>();

			GovernmentOrder governmentOrderBean = null;
			governmentOrderBean = new GovernmentOrder();

			int templCode = 45;// hard code
			int orderCode = 86;

			try {
				List<GovernmentOrderTemplate> templateList = null;
				templateList = new ArrayList<GovernmentOrderTemplate>();
				String templateBodySrc = null;
				templateList = govtOrderTemplateService
						.getTemplateDetailsByTemplateCode(templCode);

				if (templateList != null && templateList.get(0).getTemplateDescription() != null) {
					templateBodySrc = templateList.get(0)
							.getTemplateDescription();
				}
				if (templateList.get(0).getTemplateRegional() != null) {
					templateBodySrc = templateList.get(0).getTemplateRegional();
				}
				// select order number details
				goList = govtOrderService.getOrderDetailsbyOrderCode(orderCode);

					if (templateList != null && goList != null) {
					if (templateList.size() == 1 && goList.size() == 1) {
						govtOrderTemplateForm.setTemplateBodySrc(templateList
								.get(0).getTemplateRegional());
						govtOrderTemplateForm.setOperations(templateList.get(0)
								.getOperations().toString());
						String oType = Character.toString(templateList.get(0)
								.getTemplateType());

						if (oType.equalsIgnoreCase("A")) {
							oType = "Addendum";
							govtOrderTemplateForm.setOrderType(oType);
						}
						if (oType.equalsIgnoreCase("C")) {
							oType = "Corrigendum";
							govtOrderTemplateForm.setOrderType(oType);
						}
						if (oType.equalsIgnoreCase("O")) {
							oType = "Government Order";
							govtOrderTemplateForm.setOrderType(oType);
						}

						templateBodySrc = templateBodySrc.replace("{OrderNo}",
								goList.get(0).getOrderNo());
						templateBodySrc = templateBodySrc.replace(
								"{OrderDate}", goList.get(0).getOrderDate()
										.toString());
						templateBodySrc = templateBodySrc.replace(
								"{NewVillageName}",
								"Village for Template Generation");
						templateBodySrc = templateBodySrc.replace(
								"{EffectiveDate}", goList.get(0)
										.getEffectiveDate().toString());
						templateBodySrc = templateBodySrc.replace(
								"{GazettedPublicationDate}", goList.get(0)
										.getGazPubDate().toString());
						govtOrderTemplateForm
								.setTemplateBodySrc(templateBodySrc);
						govtOrderTemplateForm
								.setTemplateNameEnglish(templateList.get(0)
										.getTemplateNameEnglish());
						// updating govt order table
						governmentOrderBean.setCreatedby(userId);
						governmentOrderBean.setCreatedon(time);
						governmentOrderBean
								.setDescription("Draft Templated Generated for the first Time");
						governmentOrderBean.setEffectiveDate(goList.get(0)
								.getEffectiveDate());
						governmentOrderBean.setGazPubDate(goList.get(0)
								.getGazPubDate());
						governmentOrderBean.setIssuedBy("chandan");
						governmentOrderBean.setLevel("varchar(10)");
						governmentOrderBean.setOrderCode(goList.get(0)
								.getOrderCode());
						governmentOrderBean.setOrderDate(goList.get(0)
								.getOrderDate());
						governmentOrderBean.setOrderNo(goList.get(0)
								.getOrderNo());
						governmentOrderBean.setOrderPath(null);
						governmentOrderBean.setStatus('U');
						governmentOrderBean.setUserId(userId);
						governmentOrderBean.setXmlOrderPath(null);
						governmentOrderBean.setXmlDbPath(null);
						// govtOrderService.update(governmentOrderBean);
					}
				}
				// ModelAndView mav=null;
				mav = new ModelAndView("viewtemplate");
				mav.addObject(govtOrderTemplateForm);
				// mav.addObject("templateList", templateList);
			} catch (Exception e) {
				IExceptionHandler expHandler = ExceptionHandlerFactory
						.getInstance().create();
				String redirectPath = expHandler.handleSaveRedirectException(
						request, "", "label.lgd", 1, e);
				mav = new ModelAndView(redirectPath);
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

}
