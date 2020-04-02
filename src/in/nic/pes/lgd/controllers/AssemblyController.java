package in.nic.pes.lgd.controllers;

import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.ParliamentConstituencymodify;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.forms.AssemblyDataForm;
import in.nic.pes.lgd.forms.AssemblyForm;
import in.nic.pes.lgd.service.AssemblyService;
import in.nic.pes.lgd.service.ConfigMapService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.ParliamentService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.validator.AssemblyModifyValidator;
import in.nic.pes.lgd.validator.AssemblyValidator;
import in.nic.pes.lgd.validator.CommonValidatorImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Controller
public class AssemblyController { // NO_UCD (unused code)

	@Autowired
	CommonValidatorImpl commonValidator;

	@Autowired
	AssemblyService AssemblyService;

	@Autowired
	StateService stateService;

	@Autowired
	AssemblyValidator AssembcValidator;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	DistrictService districtService;

	@Autowired
	private SubDistrictService subdistrictService;

	@Autowired
	private FileUploadUtility fileUploadUtility;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ConfigMapService configMapService;

	@Autowired
	ParliamentService parliamentService;

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

	@RequestMapping(value = "/assembly_Constituency", method = RequestMethod.GET)
	public ModelAndView showform(HttpSession httpSession, @ModelAttribute("assemblyConstituency") AssemblyForm assemblyForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {

			// Map<String,String> hMap= new HashMap<String, String>();
			AssemblyForm assemblyconstituencyform = new AssemblyForm();
			
			Boolean mapConfig = parliamentService.checkMapConfigurationforConstituenc(stateCode, 'A');
			if (mapConfig != null) {
				mav.addObject("hideMap", mapConfig);
				model.addAttribute("hideMap", mapConfig);

				List<ParliamentConstituency> distList = stateService.getParliamentSourceList(stateCode);
				model.addAttribute("distList", distList);
				mav.addObject("assemblyConstituency", assemblyconstituencyform);
				mav = new ModelAndView("assembly");
				mav.addObject("assemblyConstituency", new AssemblyForm());
				mav.addObject("stateCode", stateCode);

			} else {
				model.addAttribute("msgid", "First Configure the Constituency Map for this state!");
				mav = new ModelAndView("success");
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
	 * @RequestMapping(value="/assembly_Constituency", method=RequestMethod.GET)
	 * public ModelAndView configmap() { ModelAndView mv=new
	 * ModelAndView("assembly"); AssemblyForm assemblyConstituency = new
	 * AssemblyForm();
	 * 
	 * mv.addObject("assemblyConstituency",assemblyConstituency);
	 * 
	 * System.out.println("Assembly constituency---"); return mv; }
	 */

	/*
	 * @ModelAttribute("stateSourceList") public List<State>
	 * populateSourceStateList() {
	 * 
	 * return stateService.getStateSourceList();
	 * 
	 * }
	 */

	/*
	 * @ModelAttribute("stateSourceParliament") public
	 * List<AssemblyConstituency> populateSourceParliamentList() {
	 * 
	 * return stateService.getAssemblySourceList();
	 * 
	 * }
	 */
	@RequestMapping(value = "/addAssembly", method = RequestMethod.POST)
	public ModelAndView configmap2(@ModelAttribute("assemblyConstituency") AssemblyForm assemblyConstituency, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession httpSession) throws Exception {

		boolean success = false;
		// String govtOrderConfig = null;
		AddAttachmentBean addAttachmentBean2 = null;
		AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
		List<AttachedFilesForm> metaInfoOfToBeAttachedMapList = null;
		ModelAndView mav = null;
		Session session = null;
		Transaction tx = null;

		try {
			/* AssemblyValidator cValidator = new AssemblyValidator(); */
			AssembcValidator.validate(assemblyConstituency, bindingResult);
			if (stateCode != null) {
				
				List<ParliamentConstituency> distList = stateService.getParliamentSourceList(stateCode);

				commonValidator.isValidMime(bindingResult, request, assemblyConstituency.getAttachFile1());

				if (bindingResult.hasErrors()) {

					model.addAttribute("distList", distList);
					mav = new ModelAndView("assembly");
				} else {

					List<CommonsMultipartFile> attachedFile = assemblyConstituency.getAttachFile1();
					if (attachedFile != null) {
						if (attachedFile.get(0).getSize() > 0) {
							String[] titles = assemblyConstituency.getFileTitle1();
							addAttachmentBean2 = govtOrderService.getAttachmentBeanforMap(attachedFile, titles, request);

							if (addAttachmentBean2 != null) {
								String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
								metaInfoOfToBeAttachedMapList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
								if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) {
									mav = new ModelAndView("assembly");
									model.addAttribute("distList", distList);
									request.setAttribute("validationErrorOne1", validateAttachment1);

									return mav;
								}
							}
						}
					}
					int mapAttachmentid = 0;
					session = sessionFactory.openSession();
					tx = session.beginTransaction();

					if (assemblyConstituency.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapAssembly(request, assemblyConstituency, bindingResult, mav);
						httpSession.setAttribute("validFileMap", validFileMap);
					}
					if (httpSession.getAttribute("validFileMap") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
						mapAttachmentid = AssemblyService.saveDataInMapAttach(assemblyConstituency, validGovermentUpload, httpSession, session);
					}

					success = AssemblyService.addassembly(assemblyConstituency, stateCode, metaInfoOfToBeAttachedMapList, mapAttachmentid, session);
					tx.commit();
					if (metaInfoOfToBeAttachedMapList != null && metaInfoOfToBeAttachedMapList.size() != 0) {
						@SuppressWarnings("unused")
						String saveAttachment = attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedMapList, addAttachmentBean2);
					}
					if (success == true) {
						model.addAttribute("msgid", "The new Assembly Constituency is saved successfully");
						mav = new ModelAndView("success");
					} else {
						model.addAttribute("msgid", "Some Technical problem Occur!Please Try After sometime!!!!!!!!");
						mav = new ModelAndView("success");
					}
				}
			} else {
				model.addAttribute("msgid", "Please First Select State");
				mav = new ModelAndView("success");
			}
		} catch (Exception e) {

			// e.printStackTrace();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mav;
	}

	@RequestMapping(value = "/ModifyAssembly_Constituency", method = RequestMethod.GET)
	public ModelAndView showAssembly(HttpSession httpSession, @ModelAttribute("AssemblyForm") AssemblyForm assemblyForm, BindingResult bindingResult, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		try {
			AssemblyForm assemblyconstituencyform = new AssemblyForm();
			EsapiEncoder.encode(assemblyForm);
			List<ParliamentConstituency> distList = stateService.getParliamentSourceList(stateCode);
			model.addAttribute("distList", distList);
			mav.addObject("AssemblyForm", assemblyconstituencyform);
			mav = new ModelAndView("Modifyassembly");
			mav.addObject("AssemblyForm", new AssemblyForm());
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
		return mav;
	}

	/*
	 * @RequestMapping(value = "/ModifyAssembly_Constituency", method =
	 * RequestMethod.GET) public ModelAndView showAssembly(Model model,
	 * HttpSession httpSession) { ModelAndView mav = new
	 * ModelAndView("Modifyassembly"); AssemblyDataForm assemblyDataForm = new
	 * AssemblyDataForm();
	 * assemblyDataForm.setNewAssemblyNameEnglish(httpSession
	 * .getAttribute("stateCode").toString()); mav.addObject("AssemblyForm",
	 * assemblyDataForm); model.addAttribute("viewPage", ""); return mav; }
	 */

	@RequestMapping(value = "/ModifyAssembly_Constituency", method = RequestMethod.POST)
	public ModelAndView modifyform(HttpSession httpSession, @ModelAttribute("AssemblyForm") AssemblyForm AssemblyForm, BindingResult bindingResult, HttpServletRequest request, Model model) {
		ModelAndView mv = null;
		// String govtOrderConfig = null;
		// Errors errors = null;
		try {

			AssemblyModifyValidator cValidator = new AssemblyModifyValidator();
			cValidator.validate(AssemblyForm, bindingResult);
			if (bindingResult.hasErrors()) {
				List<ParliamentConstituency> distList = stateService.getParliamentSourceList(stateCode);
				model.addAttribute("distList", distList);
				mv = new ModelAndView("Modifyassembly");
			} else {
				char EntityType = ' ';
				EntityType = 'A';
				EsapiEncoder.encode(AssemblyForm);
				List<ParliamentConstituencymodify> ParliamentConstituencymodify = null;
				int pcCode = AssemblyForm.getSelectedAssembly();
				httpSession.setAttribute("limit", 10);
				AssemblyForm.setOffset(1);
				httpSession.setAttribute("offset", AssemblyForm.getOffset());
				ParliamentConstituencymodify = districtService.getPcCode(stateCode, EntityType, pcCode, null, null);
				httpSession.setAttribute("counter", ParliamentConstituencymodify.size());
				httpSession.setAttribute("pcCode", pcCode);
				ParliamentConstituencymodify = districtService.getPcCode(stateCode, EntityType, pcCode,
						httpSession.getAttribute("limit") == null ? null : Integer.parseInt(httpSession.getAttribute("limit").toString()), 0);
				model.addAttribute("SEARCH_Assembly_RESULTS_KEY", ParliamentConstituencymodify);
				AssemblyForm.setParliamentConstituencymodify(ParliamentConstituencymodify);
				model.addAttribute("viewPage", "abc");
				model.addAttribute("offsets", Integer.parseInt(httpSession.getAttribute("offset").toString()) - 1);
				model.addAttribute("limits", Integer.parseInt(httpSession.getAttribute("limit").toString()));
				model.addAttribute("AssemblyCount",
						"Page " + Integer.parseInt(httpSession.getAttribute("offset").toString()) + " of " + (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1));
				mv = new ModelAndView("ModifyassemblyConstituency");
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}

		return mv;
	}

	@RequestMapping(value = "/viewAssemblyConstituency", method = RequestMethod.GET)
	public ModelAndView viewParliament(@ModelAttribute("modifyassemblyConstituencyCmd") AssemblyForm assemblyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, @RequestParam("id") Integer assemblyCode,
			@RequestParam(value = "disturb", required = false) String disturb) {
		ModelAndView mv = null;
		try {
			List<AssemblyDataForm> listAssemblyDetails = subdistrictService.getAssemblyConstituencyDetail(assemblyCode);
			EsapiEncoder.encode(assemblyForm);
			mv = new ModelAndView("viewAssemblyDetailmodify");
			model.addAttribute("size", listAssemblyDetails.size());
			model.addAttribute("listSubdistrictDetails", listAssemblyDetails);
			model.addAttribute("disturb", disturb);
			/* System.out.println(modifySubDistrictCmd.toString()); */
			assemblyForm.setListAssemblyFormDetail(listAssemblyDetails);
			// mv.addObject("modify_Parliement_Constituency", parliamentform);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/modifyAssemblyConstituency", method = RequestMethod.GET)
	public ModelAndView modifyVillage(@ModelAttribute("modifyassemblyConstituencyCmd") AssemblyForm assemblyForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpSession,
			@RequestParam("id") Integer ParliamentCode, @RequestParam(value = "disturb", required = false) String disturb) {
		ModelAndView mv = null;
		String parliamentName = null;
		Integer eciCode = null;
		Integer pcCode = null;
		try {
			List<AssemblyDataForm> listSubdistrictDetails = subdistrictService.getAssemblyConstituencyDetail(ParliamentCode);
			EsapiEncoder.encode(assemblyForm);
			mv = new ModelAndView("modify_Assembly_Constituency");
			model.addAttribute("size", listSubdistrictDetails.size());
			model.addAttribute("listSubdistrictDetails", listSubdistrictDetails);
			model.addAttribute("disturb", disturb);
			/* System.out.println(modifySubDistrictCmd.toString()); */
			assemblyForm.setListAssemblyFormDetail(listSubdistrictDetails);
			pcCode = (Integer) httpSession.getAttribute("pcCode");
			if (listSubdistrictDetails.size() > 0) {
				parliamentName = listSubdistrictDetails.get(0).getAcNameEnglish();
				eciCode = listSubdistrictDetails.get(0).getEciCode();
				mv.addObject("parliamentName", parliamentName);
				mv.addObject("eciCode", eciCode);
			}
			mv.addObject("parliamentCCode", pcCode);
			mv.addObject("modify_Assembly_Constituency", assemblyForm);
			mv.addObject("stateCode", stateCode);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			// return mv;
		} finally {
			httpSession.removeAttribute("pcCode");
		}
		return mv;
	}

	@RequestMapping(value = "/modifyAssemblyConstituencytAction", method = RequestMethod.POST)
	public String modifySubdistrict(@ModelAttribute("modifyassemblyConstituencyCmd") AssemblyForm modifySubDistrictCmd, BindingResult result, SessionStatus status, Model model, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb, HttpSession httpSession) {
		ModelAndView mv = null;
		try {
			
			modifySubDistrictCmd.setState_code(stateCode);
			AssembcValidator.modifyvalidate(modifySubDistrictCmd, result);
			if (result.hasErrors()) {
				List<AssemblyDataForm> listSubdistrictDetails = modifySubDistrictCmd.getListAssemblyFormDetail();

				EsapiEncoder.encode(modifySubDistrictCmd);
				mv = new ModelAndView("modify_Assembly_Constituency");
				model.addAttribute("size", listSubdistrictDetails.size());
				model.addAttribute("listSubdistrictDetails", listSubdistrictDetails);
				model.addAttribute("disturb", disturb);
				/* System.out.println(modifySubDistrictCmd.toString()); */
				modifySubDistrictCmd.setListAssemblyFormDetail(listSubdistrictDetails);
				Integer pcCode = (Integer) httpSession.getAttribute("pcCode");
				if (listSubdistrictDetails.size() > 0) {
					String parliamentName = listSubdistrictDetails.get(0).getAcNameEnglish();
					Integer eciCode = listSubdistrictDetails.get(0).getEciCode();
					mv.addObject("parliamentName", parliamentName);
					mv.addObject("eciCode", eciCode);
				}
				mv.addObject("parliamentCCode", pcCode);
				mv.addObject("modify_Assembly_Constituency", modifySubDistrictCmd);
				mv.addObject("stateCode", stateCode);
				return "modify_Assembly_Constituency";
			}
			String viewSubdistrictdetail = null;
			mv = new ModelAndView("modify_Assembly_Constituency");

			subdistrictService.modifyAssemblyConstituencyInfo(modifySubDistrictCmd, request, httpSession);
			EsapiEncoder.encode(modifySubDistrictCmd);
			int subdistrictCode = 0;
			int subdistrictVersion = 0;

			List<AssemblyDataForm> listSubdistrict = new ArrayList<AssemblyDataForm>();
			listSubdistrict = modifySubDistrictCmd.getListAssemblyFormDetail();
			Iterator<AssemblyDataForm> itr = listSubdistrict.iterator();
			while (itr.hasNext()) {
				AssemblyDataForm element = (AssemblyDataForm) itr.next();
				subdistrictCode = element.getAcCode();
				subdistrictVersion = element.getAcVersion();
			}
			if (disturb.equals("true")) {
				viewSubdistrictdetail = "redirect:viewResolveEntitiesinDisturbedState.htm?resolved=" + subdistrictCode + "," + subdistrictVersion;
			} else {
				viewSubdistrictdetail = "redirect:viewAssemblyDetailformodify.htm?id=" + subdistrictCode + "&type=S";
			}
			return viewSubdistrictdetail;

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			// mv = new ModelAndView(redirectPath);
			return redirectPath;
		}
	}

	@RequestMapping(value = "/viewAssemblyDetailformodify", method = RequestMethod.GET)
	public ModelAndView viewSubDistrictDetail(@ModelAttribute("modifyassemblyConstituencyCmd") AssemblyForm modifySubDistrictCmd, Model model, HttpSession httpSession, HttpServletRequest request, @RequestParam("id") Integer subdistrictCode,
			@RequestParam("type") String type) {
		ModelAndView mv = null;
		try {
			List<AssemblyDataForm> listParliamentFormDetail = subdistrictService.getAssemblyConstituencyDetail(subdistrictCode);
			EsapiEncoder.encode(modifySubDistrictCmd);
			mv = new ModelAndView("viewAssemblyDetailmodify");
			modifySubDistrictCmd.setListAssemblyFormDetail(listParliamentFormDetail);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/viewAssemblyCocnstituencyPagination", method = RequestMethod.POST)
	public ModelAndView getSubDistrictPagination(@ModelAttribute("AssemblyForm") AssemblyForm assemblyForm, Model model, HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = null;
		try {
			char EntityType = ' ';
			// String statecode = null;
			EntityType = 'A';
			int pcCode = assemblyForm.getSelectedAssembly();
			
			EsapiEncoder.encode(assemblyForm);
			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset").toString()) + assemblyForm.getDirection() > 0) {
				assemblyForm.setOffset(Integer.parseInt(httpSession.getAttribute("offset").toString()) + assemblyForm.getDirection());
				httpSession.setAttribute("offset", assemblyForm.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset").toString()) == (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1)
					&& assemblyForm.getDirection() == -1 && Integer.parseInt(httpSession.getAttribute("offset").toString()) + assemblyForm.getDirection() > 0) {
				assemblyForm.setOffset(Integer.parseInt(httpSession.getAttribute("offset").toString()) + assemblyForm.getDirection());
				httpSession.setAttribute("offset", assemblyForm.getOffset());
			}
			List<ParliamentConstituencymodify> ParliamentConstituencymodify = null;
			ParliamentConstituencymodify = districtService.getPcCode(stateCode, EntityType, pcCode,
					httpSession.getAttribute("limit") == null ? null : Integer.parseInt(httpSession.getAttribute("limit").toString()),
					Integer.parseInt(httpSession.getAttribute("limit").toString()) * (Integer.parseInt(httpSession.getAttribute("offset").toString()) - 1));
			model.addAttribute("SEARCH_Assembly_RESULTS_KEY", ParliamentConstituencymodify);
			assemblyForm.setParliamentConstituencymodify(ParliamentConstituencymodify);
			model.addAttribute("viewPage", "abc");
			model.addAttribute("offsets", Integer.parseInt(httpSession.getAttribute("offset").toString()) - 1);
			model.addAttribute("limits", Integer.parseInt(httpSession.getAttribute("limit").toString()));
			model.addAttribute("AssemblyCount",
					"Page " + Integer.parseInt(httpSession.getAttribute("offset").toString()) + " of " + (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1));
			mav = new ModelAndView("ModifyassemblyConstituency");
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

}
