package in.nic.pes.lgd.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
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

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.NodalOfficer;
import in.nic.pes.lgd.bean.ParliamentConstituency;
import in.nic.pes.lgd.bean.ParliamentConstituencymodify;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.common.EsapiEncoder;
import in.nic.pes.lgd.common.FileUploadUtility;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.NodalOfficerForm;
import in.nic.pes.lgd.forms.ParliamentDataForm;
import in.nic.pes.lgd.forms.ParliamentForm;
import in.nic.pes.lgd.service.AssemblyService;
import in.nic.pes.lgd.service.DistrictService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.LocalGovtBodyService;
import in.nic.pes.lgd.service.MapService;
import in.nic.pes.lgd.service.ParliamentService;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.service.SubDistrictService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.validator.CommonValidatorImpl;
import in.nic.pes.lgd.validator.ParliamentValidator;
import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AddAttachmentHandler;
import pes.attachment.util.AttachedFilesForm;

@Controller
public class ParliamentController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(ParliamentController.class);
	// private static final Session Session = null;

	@Autowired
	CommonValidatorImpl commonValidator;

	@Autowired
	ParliamentValidator parcValidator;

	@Autowired
	ParliamentService parliamentService;

	@Autowired
	GovernmentOrderService govtOrderService;

	@Autowired
	DistrictService districtService;

	@Autowired
	private SubDistrictService subdistrictService;

	@Autowired
	LocalGovtBodyService localGovtBodyService;

	@Autowired
	VillageService villageService;

	@Autowired
	MapService MapService;

	@Autowired
	GovernmentOrderService governmentOrderService;

	@Autowired
	AssemblyService AssemblyService;

	@Autowired
	StateService stateService;

	@Autowired
	FileUploadUtility fileUploadUtility;

	@Autowired
	SessionFactory sessionFactory;

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
	
	/**
	 * 
	 * @param session
	 */
	private void setGlobalParams(HttpSession session){
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		stateCode = sessionObject.getStateId();
		districtCode = sessionObject.getDistrictCode();
		userId = sessionObject.getUserId();
	}

	@RequestMapping(value = "/parliament_Constituency", method = RequestMethod.GET)
	public ModelAndView showform(HttpSession httpSession, @ModelAttribute("parliamentConstituency") ParliamentForm parliamentform, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			ParliamentForm parliamentConstituency = new ParliamentForm();
			List<ParliamentConstituency> distList = stateService.getParliamentSourceList(stateCode);

			Boolean mapConfig = parliamentService.checkMapConfigurationforConstituenc(stateCode, 'P');
			if (mapConfig != null) {
				mav.addObject("hideMap", mapConfig);
				model.addAttribute("hideMap", mapConfig);
				model.addAttribute("distList", distList);
				parliamentform.setStatecode(stateCode.toString());
				mav.addObject("parliamentConstituency", parliamentConstituency);
				mav = new ModelAndView("parliament");
				mav.addObject("parliamentConstituency", new ParliamentForm());
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

	@RequestMapping(value = "/Modifyparliament_Constituency", method = RequestMethod.GET)
	public ModelAndView modifyform(HttpSession httpSession, @ModelAttribute("parliamentbean") ParliamentForm parliamentform, BindingResult bindingResult, Model model, HttpServletRequest request) {
		ModelAndView mav = null;
		try {

			char EntityType = ' ';
			String statecode = null;

			EntityType = 'P';

			List<ParliamentConstituencymodify> ParliamentConstituencymodify = null;
			httpSession.setAttribute("limit", 10);

			parliamentform.setOffset(1);
			httpSession.setAttribute("offset", parliamentform.getOffset());

			ParliamentConstituencymodify = districtService.getPcCode(stateCode, EntityType, null, null, null);
			httpSession.setAttribute("counter", ParliamentConstituencymodify.size());
			ParliamentConstituencymodify = districtService.getPcCode(stateCode, EntityType, null,
					httpSession.getAttribute("limit") == null ? null : Integer.parseInt(httpSession.getAttribute("limit").toString()), 0);
			model.addAttribute("SEARCH_PARLIAMENT_RESULTS_KEY", ParliamentConstituencymodify);
			parliamentform.setParliamentConstituencymodify(ParliamentConstituencymodify);
			model.addAttribute("viewPage", "abc");
			model.addAttribute("offsets", Integer.parseInt(httpSession.getAttribute("offset").toString()) - 1);
			model.addAttribute("limits", Integer.parseInt(httpSession.getAttribute("limit").toString()));
			model.addAttribute("parliamentCount",
					"Page " + Integer.parseInt(httpSession.getAttribute("offset").toString()) + " of " + (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1));
			mav = new ModelAndView("Modifyparliament");

		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewParliamentConstituency")
	public ModelAndView viewParliament(@ModelAttribute("modifyParliamentConstituencyCmd") ParliamentForm parliamentform, BindingResult result, SessionStatus status, Model model, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb) {
		ModelAndView mav = new ModelAndView();
		int parliamentCode = parliamentform.getParliamentId();
		try {
			List<ParliamentDataForm> listSubdistrictDetails = subdistrictService.getParliamentConstituencyDetail(parliamentCode);

			mav = new ModelAndView("viewParliamentDetailmodify");
			model.addAttribute("size", listSubdistrictDetails.size());
			model.addAttribute("listSubdistrictDetails", listSubdistrictDetails);
			model.addAttribute("disturb", disturb);

			parliamentform.setListParliamentFormDetail(listSubdistrictDetails);
			mav.addObject("modify_Parliement_Constituency", parliamentform);
		}

		catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/modifyParliamentConstituency")
	public ModelAndView modifyVillage(@ModelAttribute("modifyParliamentConstituencyCmd") ParliamentForm parliamentform, BindingResult result, SessionStatus status, Model model, HttpServletRequest request, HttpSession httpSession,
			@RequestParam(value = "disturb", required = false) String disturb) {
		ModelAndView mav = new ModelAndView();
		int ParliamentCode = parliamentform.getParliamentId();
		String parliamentName = null;
		Integer eciCode = null;
		try {

			
			EsapiEncoder.encode(parliamentform);
			List<ParliamentDataForm> listSubdistrictDetails = subdistrictService.getParliamentConstituencyDetail(ParliamentCode);
			mav = new ModelAndView("modify_Parliement_Constituency");
			model.addAttribute("size", listSubdistrictDetails.size());
			model.addAttribute("listSubdistrictDetails", listSubdistrictDetails);
			model.addAttribute("disturb", disturb);
			if (listSubdistrictDetails.size() > 0) {
				parliamentName = listSubdistrictDetails.get(0).getPcNameEnglish();
				eciCode = listSubdistrictDetails.get(0).getEciCode();
				mav.addObject("parliamentName", parliamentName);
				mav.addObject("eciCode", eciCode);
			}
			parliamentform.setListParliamentFormDetail(listSubdistrictDetails);
			mav.addObject("modify_Parliement_Constituency", parliamentform);
			mav.addObject("stateCode", stateCode);
			return mav;
		} catch (Exception e) {

			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;

		}
	}

	@RequestMapping(value = "/modifyParliamentConstituencytAction", method = RequestMethod.POST)
	public String modifySubdistrict(@ModelAttribute("modifyParliamentConstituencyCmd") ParliamentForm parliamentForm, BindingResult result, SessionStatus status, Model model, HttpServletRequest request,
			@RequestParam(value = "disturb", required = false) String disturb, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		String viewPcdetail = null;
		try {
			
			parliamentForm.setState_code(stateCode);
			mav = new ModelAndView("modify_Parliement_Constituency");

			parcValidator.modifyvalidate(parliamentForm, result);
			if (result.hasErrors()) {
				mav = new ModelAndView("modify_Parliement_Constituency");
				
				EsapiEncoder.encode(parliamentForm);
				List<ParliamentDataForm> listSubdistrictDetails = parliamentForm.getListParliamentFormDetail();
				mav = new ModelAndView("modify_Parliement_Constituency");
				model.addAttribute("size", listSubdistrictDetails.size());
				model.addAttribute("listSubdistrictDetails", listSubdistrictDetails);
				model.addAttribute("disturb", disturb);
				if (listSubdistrictDetails.size() > 0) {

					Integer eciCode = listSubdistrictDetails.get(0).getEciCode();
					mav.addObject("parliamentName", parliamentForm);
					mav.addObject("eciCode", eciCode);
				}
				parliamentForm.setListParliamentFormDetail(listSubdistrictDetails);
				mav.addObject("modify_Parliement_Constituency", parliamentForm);
				mav.addObject("stateCode", stateCode);
				return "modify_Parliement_Constituency";
			}
			subdistrictService.modifyParliamentConstituencyInfo(parliamentForm, request, httpSession);
			int pcCode = 0;
			int pcVersion = 0;
			List<ParliamentDataForm> listSubdistrict = new ArrayList<ParliamentDataForm>();
			listSubdistrict = parliamentForm.getListParliamentFormDetail();
			Iterator<ParliamentDataForm> itr = listSubdistrict.iterator();
			while (itr.hasNext()) {
				ParliamentDataForm element = (ParliamentDataForm) itr.next();
				pcCode = element.getPcCode();
				pcVersion = element.getPcVersion();
			}
			if (disturb.equals("true")) {
				viewPcdetail = "redirect:viewResolveEntitiesinDisturbedState.htm?resolved=" + pcCode + "," + pcVersion;
			} else {
				viewPcdetail = "redirect:viewParliamentDetailformodify.htm?id=" + pcCode + "&type=S";
			}
			return viewPcdetail;
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);

			return redirectPath;
		}

	}

	@RequestMapping(value = "/viewParliamentDetailformodify", method = RequestMethod.GET)
	public ModelAndView viewSubDistrictDetail(@ModelAttribute("modifyParliamentConstituencyCmd") ParliamentForm parliamentForm, HttpServletRequest request, Model model, @RequestParam("id") Integer subdistrictCode, @RequestParam("type") String type) {
		ModelAndView mav = new ModelAndView();

		try {
			List<ParliamentDataForm> listParliamentFormDetail = subdistrictService.getParliamentConstituencyDetail(subdistrictCode);
			mav = new ModelAndView("viewParliamentDetailformodify");
			parliamentForm.setListParliamentFormDetail(listParliamentFormDetail);
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/viewparliamentCocnstituencyPagination", method = RequestMethod.POST)
	public ModelAndView getSubDistrictPagination(@ModelAttribute("parliamentbean") ParliamentForm parliamentform, HttpServletRequest request, Model model, HttpSession httpSession) {
		ModelAndView mav = null;
		try {

			char EntityType = ' ';

			// EsapiEncoder.encode(parliamentform);
			EntityType = 'P';
			
			
			if (Integer.parseInt(httpSession.getAttribute("offset").toString()) != (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1)
					&& Integer.parseInt(httpSession.getAttribute("offset").toString()) + parliamentform.getDirection() > 0) {
				parliamentform.setOffset(Integer.parseInt(httpSession.getAttribute("offset").toString()) + parliamentform.getDirection());
				httpSession.setAttribute("offset", parliamentform.getOffset());
			} else if (Integer.parseInt(httpSession.getAttribute("offset").toString()) == (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1)
					&& parliamentform.getDirection() == -1 && Integer.parseInt(httpSession.getAttribute("offset").toString()) + parliamentform.getDirection() > 0) {
				parliamentform.setOffset(Integer.parseInt(httpSession.getAttribute("offset").toString()) + parliamentform.getDirection());
				httpSession.setAttribute("offset", parliamentform.getOffset());
			}
			List<ParliamentConstituencymodify> ParliamentConstituencymodify = null;
			ParliamentConstituencymodify = districtService.getPcCode(stateCode, EntityType, null, httpSession.getAttribute("limit") == null ? null : Integer.parseInt(httpSession.getAttribute("limit").toString()),
					Integer.parseInt(httpSession.getAttribute("limit").toString()) * (Integer.parseInt(httpSession.getAttribute("offset").toString()) - 1));
			model.addAttribute("SEARCH_PARLIAMENT_RESULTS_KEY", ParliamentConstituencymodify);
			parliamentform.setParliamentConstituencymodify(ParliamentConstituencymodify);
			model.addAttribute("viewPage", "abc");
			model.addAttribute("offsets", Integer.parseInt(httpSession.getAttribute("offset").toString()) - 1);
			model.addAttribute("limits", Integer.parseInt(httpSession.getAttribute("limit").toString()));
			model.addAttribute("parliamentCount",
					"Page " + Integer.parseInt(httpSession.getAttribute("offset").toString()) + " of " + (Integer.parseInt(httpSession.getAttribute("counter").toString()) / Integer.parseInt(httpSession.getAttribute("limit").toString()) + 1));
			mav = new ModelAndView("Modifyparliament");
			return mav;
		} catch (Exception e) {
			// return null;
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}
	}

	@RequestMapping(value = "/addParliament", method = RequestMethod.POST)
	public ModelAndView configmap2(@ModelAttribute("parliamentConstituency") ParliamentForm parliamentConstituency, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession httpSession) throws Exception {
		AddAttachmentBean addAttachmentBean2 = null;
		boolean success = false;
		// String govtOrderConfig = null;
		AddAttachmentHandler attachmentHandler = new AddAttachmentHandler();
		ModelAndView mv = null;
		Session session = null;
		Transaction tx = null;
		List<AttachedFilesForm> metaInfoOfToBeAttachedMapList = null;
		// ParliamentValidator parcValidator = new ParliamentValidator();

		commonValidator.isValidMime(bindingResult, request, parliamentConstituency.getAttachFile1());

		try {

			if (stateCode != null) {

				List<ParliamentConstituency> distList = stateService.getParliamentSourceList(stateCode);

				parcValidator.validate(parliamentConstituency, bindingResult);
				if (bindingResult.hasErrors()) {
					mv = new ModelAndView("parliament");
					model.addAttribute("distList", distList);
					mv.addObject("parliamentConstituency", parliamentConstituency);
				} else {
					List<CommonsMultipartFile> attachedFile = parliamentConstituency.getAttachFile1();
					String[] titles = parliamentConstituency.getFileTitle1();
					addAttachmentBean2 = govtOrderService.getAttachmentBeanforMap(attachedFile, titles, request);

					if (addAttachmentBean2 != null) {
						String validateAttachment1 = attachmentHandler.validateAttachment(addAttachmentBean2);
						metaInfoOfToBeAttachedMapList = attachmentHandler.getMetaDataListOfFiles(addAttachmentBean2);
						if (!validateAttachment1.equalsIgnoreCase("validationSuccessFullyDone")) {
							mv = new ModelAndView("parliament");
							mv.addObject("parliamentConstituency", parliamentConstituency);
							model.addAttribute("distList", distList);
						}
					}

					int mapAttachmentid = 0;
					session = sessionFactory.openSession();
					tx = session.beginTransaction();

					if (parliamentConstituency.getAttachFile1() != null) {
						List<AttachedFilesForm> validFileMap = fileUploadUtility.ValidateAndUploadFileMapParliament(request, parliamentConstituency, bindingResult, mv);
						httpSession.setAttribute("validFileMap", validFileMap);
					}
					if (httpSession.getAttribute("validFileMap") != null) {
						@SuppressWarnings("unchecked")
						List<AttachedFilesForm> validGovermentUpload = (List<AttachedFilesForm>) httpSession.getAttribute("validFileMap");
						mapAttachmentid = parliamentService.saveDataInMapAttach(parliamentConstituency, validGovermentUpload, httpSession, session);
					}

					success = parliamentService.addParliament(parliamentConstituency, stateCode, metaInfoOfToBeAttachedMapList, mapAttachmentid, session);
					tx.commit();
					if (metaInfoOfToBeAttachedMapList != null && metaInfoOfToBeAttachedMapList.size() != 0) {
						attachmentHandler.saveMetaDataINFileSystem(metaInfoOfToBeAttachedMapList, addAttachmentBean2);
					}
					if (success == true) {
						model.addAttribute("msgid", "The new Parliament Constituency is saved successfully");
						mv = new ModelAndView("success");
					} else {
						model.addAttribute("msgid", "Some Technical problem Occur!Please Try After sometime!!!!!!!!");
						mv = new ModelAndView("success");
					}
				}

			} else {
				model.addAttribute("msgid", "Please First Select State");
				mv = new ModelAndView("success");

			}
		} catch (Exception e) {

			log.debug("Exception" + e);
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

	/** The local body typelist for panchayat. */
	List<LocalbodyforStateWise> localBodyTypelist = new ArrayList<LocalbodyforStateWise>();

	/** The local body typelist for traditional. */
	List<LocalbodyforStateWise> localBodyTypelistTrad = new ArrayList<LocalbodyforStateWise>();

	/** The local body typelist for Urban. */
	List<LocalbodyforStateWise> localBodyTypelistUrban = new ArrayList<LocalbodyforStateWise>();

	/** The district panchayat list. */
	List<LocalbodyListbyState> districtPanchayatList = new ArrayList<LocalbodyListbyState>();

	/** The district panchayat list for traditional. */
	List<LocalbodyListbyState> districtPanchayatListTrad = new ArrayList<LocalbodyListbyState>();

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/map_constitutionBody", method = RequestMethod.GET)
	public ModelAndView viewWardForPanchayat(HttpSession httpSession, @ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		try {
			setGlobalParams(session);
			model.addAttribute("districtCode", districtCode);
			List<ParliamentConstituency> distList = stateService.getParliamentSourceList(stateCode);

			localGovtBodyForm.setLgd_lbCategory("P");
			/*
			 * int stateCode = getStateCode(session, request);
			 * request.setAttribute("stateCode", stateCode);
			 */
			if (stateCode != 0) {
				if (session.getAttribute("formbean") != null) {
					session.removeAttribute("formbean");
					session.removeValue("formbean");
				}
				List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodystatelist('a', stateCode);
				List<LocalbodyforStateWise> lbTypelist = new ArrayList<LocalbodyforStateWise>();
				lbTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
				if (lbTypelist.size() > 2) {
					model.addAttribute("Tier", 3);
				}
				districtPanchayatList = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');

				if (!CollectionUtils.isEmpty(localBodyTypelist)) {
					localGovtBodyForm = setLBtype(localBodyTypelist, request, localGovtBodyForm);
				}
				if (districtPanchayatList.size() > 0) {
					model.addAttribute("districtPanchayatList", districtPanchayatList);
				}
				if (stateCode == 34) {
					localGovtBodyForm.setLgd_LBNomenclatureDist(localGovtBodyForm.getLgd_LBNomenclatureInter());
					localGovtBodyForm.setLgd_LBNomenclatureInter(localGovtBodyForm.getLgd_LBNomenclatureVillage());
					/*
					 * localGovtBodyForm.setLgd_LBNomenclatureDist(
					 * "Commune Panchayat");
					 * localGovtBodyForm.setLgd_LBNomenclatureInter(
					 * "Village Panchayat");
					 */
				}
				mav.addObject("stateCode", stateCode);	
				mav = new ModelAndView("MapOfParliament");
				model.addAttribute("distList", distList);
				model.addAttribute("localbodyTypelist", localBodyTypelist);
				mav.addObject("localGovtBodyForm", localGovtBodyForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	public LocalGovtBodyForm setLBtype(List<LocalbodyforStateWise> localBodyTypelist, HttpServletRequest request, LocalGovtBodyForm localGovtBodyForm) {
		try {
			for (int j = 0; j < localBodyTypelist.size(); j++) {
				String govtBodyType = localBodyTypelist.get(j).getLevel();
				String category = localBodyTypelist.get(j).getCategory();
				if (govtBodyType != null) {
					if (govtBodyType.equalsIgnoreCase("D") && category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {

							localGovtBodyForm.setLgd_LBNomenclatureDist(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("I") && category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {

							localGovtBodyForm.setLgd_LBNomenclatureInter(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("V") && category.equalsIgnoreCase("P")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {

							localGovtBodyForm.setLgd_LBNomenclatureVillage(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
					if (govtBodyType.equalsIgnoreCase("U")) {
						if (localBodyTypelist.get(j).getNomenclatureEnglish() != null) {

							localGovtBodyForm.setLgd_LBNomenclatureUrban(localBodyTypelist.get(j).getNomenclatureEnglish());
						}
					}
				}

			}
		} catch (Exception e) {
			// return null;
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);

		}
		return localGovtBodyForm;
	}

	/**
	 * @author Sourabh Rai
	 * @param localGovtBodyForm
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/MapofParliament", method = RequestMethod.POST)
	public ModelAndView configmap3(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) throws Exception {

		ModelAndView mv = null;
		try {
				if(localGovtBodyForm.getLbFullMap()!=null && localGovtBodyForm.getLbFullMap()!="" || ((localGovtBodyForm.getLbPartMap()!=null && localGovtBodyForm.getLbPartMap()!="" )
				&& (localGovtBodyForm.getVillageMap()!=null && localGovtBodyForm.getVillageMap()!="")) ||  ((localGovtBodyForm.getLbPartMap()!=null && localGovtBodyForm.getLbPartMap()!="" ) 
				&& (localGovtBodyForm.getWardMap()!=null && localGovtBodyForm.getWardMap()!="")) || (localGovtBodyForm.getDeleteMap()!=null && localGovtBodyForm.getDeleteMap()!="")
				){
					
					
					SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
					Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
					localGovtBodyForm.setUserId(userId);
					boolean success = MapService.savenUpdateConstituencyCoverage(localGovtBodyForm);
					//success = MapService.mapParliament(localGovtBodyForm, session);
					if (success)
						model.addAttribute("msgid", "New Parliament Map Details have been saved !");
					else
						model.addAttribute("msgid", "Data Insertion Failure, Please Check Logs !");
					mv = new ModelAndView("success");
				}else{
					model.addAttribute("msgid", "Data Insertion Failure, Please Check Logs !");
					mv = new ModelAndView("success");
				}
				
			//}
		} catch (Exception e) {

			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mv = new ModelAndView(redirectPath);
			return mv;
		}
		return mv;

	}

	@RequestMapping(value = "/mapZpWardConsituency", method = RequestMethod.GET)
	public ModelAndView mapZpConsituency(HttpSession httpSession, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		List<LocalbodyListbyState> districtPanchayatListnew = new ArrayList<LocalbodyListbyState>();
		try {

			LocalGovtBodyForm localGovtBodyForm = new LocalGovtBodyForm();
			localGovtBodyForm.setLgd_lbCategory("P");
			if (stateCode != 0) {
				if (httpSession.getAttribute("formbean") != null) {
					httpSession.removeAttribute("formbean");

				}
				mav = new ModelAndView("mapZpConstituency");
				districtPanchayatListnew = localGovtBodyService.getExistingLBListbyStateandCategory(stateCode, 'P');
				mav.addObject("districtPanchayatList", districtPanchayatListnew);
				List<LocalbodyforStateWise> localBodyTypelist = localGovtBodyService.getLocalBodyListStateWise('P', stateCode);
				if (localBodyTypelist.size() == 2) {
					if (localBodyTypelist.get(0).getLocalBodyTypeCode() == 1 && localBodyTypelist.get(1).getLocalBodyTypeCode() == 3) {
						model.addAttribute("twoTierStructure", true);
						mav.addObject("localGovtBodyForm", localGovtBodyForm);
						return mav;
					}
				}

				mav.addObject("localGovtBodyForm", localGovtBodyForm);

				model.addAttribute("twoTierStructure", false);
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			mav = new ModelAndView(redirectPath);
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/mapZpConsituencyData", method = RequestMethod.POST)
	public ModelAndView mapZpConsituencyData(@ModelAttribute("localGovtBodyForm") LocalGovtBodyForm localGovtBodyForm, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session) throws Exception {

		ModelAndView mav = null;
		System.out.println("Dta");
		;
		String wardNo = localGovtBodyForm.getWard_number();
		String gpList = localGovtBodyForm.getListformat();
		String vilList = localGovtBodyForm.getContVillage();
		String deletedLBList = localGovtBodyForm.getLgd_hiddenLbcList();
		String deltedVilList = localGovtBodyForm.getVillagePanchayat();
		if (gpList.isEmpty())
			gpList = null;
		if (vilList.equals(""))
			System.out.println("chek");

		if (vilList.isEmpty())
			vilList = null;

		try {
			boolean success = localGovtBodyService.mapZpConsituencyDetail(wardNo, gpList, vilList, deletedLBList, deltedVilList);
			if (success) {
				model.addAttribute("msgid", "Mapping Done Succesfully ");
				mav = new ModelAndView("success");
			} else {
				model.addAttribute("msgid", "Error Faced In  Mapping");
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
	/**
	 * @author Sunita Dagar
	 * @Date 18-11-2016
	 */
@RequestMapping(value = "/nodalOfficerDetails", method = RequestMethod.GET)
public ModelAndView nodalOfficerDetails(HttpSession httpSession, @ModelAttribute("nodalOfficerForm") NodalOfficerForm nodalOfficerForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
	ModelAndView mav = new ModelAndView();
	try {
			mav = new ModelAndView("nodalOfficerDetails");
			mav.addObject("nodalOfficerForm", nodalOfficerForm);
			List<NodalOfficer> getNodalOfficerDetails = new ArrayList<NodalOfficer>();
			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			userId = sessionObject.getUserId();
			int createdBy = userId.intValue();
			getNodalOfficerDetails=localGovtBodyService.getNodalOfficerDetails(createdBy,districtCode);
			NodalOfficer nodalOfficer=new NodalOfficer();
			if(getNodalOfficerDetails.size()>0){
				nodalOfficer=getNodalOfficerDetails.get(0);
				nodalOfficerForm.setNodalUserName(nodalOfficer.getNodalUserName());
				nodalOfficerForm.setNodalUserDesignation(nodalOfficer.getNodalUserDesignation());
				nodalOfficerForm.setNodalUserEmail(nodalOfficer.getNodalUserEmail());
				nodalOfficerForm.setNodalUserMobile(nodalOfficer.getNodalUserMobile());
				nodalOfficerForm.setNodalUserId(nodalOfficer.getNodalOfficerPK().getNodalUserId());
				nodalOfficerForm.setNodalUserVersion(nodalOfficer.getNodalOfficerPK().getNodalUserVersion());
				nodalOfficerForm.setCreatedOn(nodalOfficer.getCreatedOn());
			}
			
	} catch (Exception e) {
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		String redirectPath = expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
		mav = new ModelAndView(redirectPath);
		return mav;
	}

	return mav;
}

@RequestMapping(value = "/nodalOfficerDetails", method = RequestMethod.POST)
public ModelAndView nodalOfficerDetailsPOST(HttpSession httpSession, @ModelAttribute("nodalOfficerForm") NodalOfficerForm nodalOfficerForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
	ModelAndView mav = new ModelAndView();
	try {
			mav = new ModelAndView("nodalOfficerDetails");
			List<NodalOfficer> getNodalOfficerDetails = new ArrayList<NodalOfficer>();
			SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
			districtCode = sessionObject.getDistrictCode();
			nodalOfficerForm.setNodalUserDistrict(districtCode);
			userId = sessionObject.getUserId();
			int createdBy = userId.intValue();
			getNodalOfficerDetails=localGovtBodyService.getNodalOfficerDetails(createdBy,districtCode);
			if(getNodalOfficerDetails.size()>0){
				localGovtBodyService.UpdateIsactiveStatue(createdBy,districtCode);
			}
			nodalOfficerForm.setCreatedBy(createdBy);
			boolean saveFlag=localGovtBodyService.saveNodalOfficerDetails(nodalOfficerForm);
			if(saveFlag){
				mav.addObject("nodalOfficerForm", nodalOfficerForm);
				model.addAttribute("msgid", "Data Save Successfully");
				mav = new ModelAndView("success");
			}else{
				model.addAttribute("msgid", "Data not save");
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

}
